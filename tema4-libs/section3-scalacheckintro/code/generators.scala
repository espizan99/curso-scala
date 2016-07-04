package org.hablapps
package fpinscala.scalacheck

object Generators {
  import org.scalacheck._, Arbitrary._, Gen._

  // Generadores simples
  object SimpleTypes{

    // Proporcionados de manera estándar por Scalacheck

    val intGen: Gen[Int] = arbitrary[Int]
    val stringGen: Gen[String] = arbitrary[String]
    
    // Creación de generadores a partir de otros generadores

    val intsAsStringGen: Gen[String] = intGen map (_.toString)
    
    // Más generadores simples
    
    case class One(i: Int)

    val oneGen: Gen[One] = intGen map One.apply
  }
    
  // Productos
  object Products {
    import SimpleTypes.{intGen, stringGen}

    case class User(name: String, age: Int)

    // Generación de usuarios arbitrarios (raw)

    val userGen_raw: Gen[User] =
      stringGen flatMap { name => 
        intGen flatMap { age =>
          const(User(name, age))
        }
      }

    // Con flatMap y map
    val userGen0: Gen[User] =
      stringGen flatMap { name => 
        intGen map { age =>
          User(name, age)
        }
      }

    // Con for-comprehensions
    val userGen: Gen[User] =
      for {
        name <- stringGen
        age <- intGen
      } yield User(name, age)

    // Con restricciones (suchThat)
    val userGen2: Gen[User] =
      for {
        name <- stringGen suchThat (_.length > 10)
        age <- intGen
      } yield User(name, age)

    // Con restricciones (if)
    val userGen3: Gen[User] =
      for {
        name <- stringGen if name.length > 10
        age <- intGen
      } yield User(name, age)

    // Generación predefinida de listas

    val userListNGen: Gen[List[User]] = listOfN(10, userGen)
    
    val userListGen: Gen[List[User]] = listOf(userGen)
  }

  // Sumas de tipos
  object Sums {
    import SimpleTypes.{intGen, stringGen}

    // Vamos a crear primero generadores para tipos no genéricos
    object SpecificTypes{
      // Option[Int] = None + Some[Int]
      
      // Generadores para cada variante individual
      val someIntGen: Gen[Option[Int]] = intGen map Option.apply

      val noneIntGen: Gen[Option[Int]] = const(Option.empty[Int])

      // Generador para la suma de variantes
      val optionIntGen: Gen[Option[Int]] = oneOf(someIntGen, noneIntGen)
    }

    // Y ahora para tipos genéricos T[A]: para poder generar valores de
    // tipo `T[A]` necesitamos disponer de generadores de tipo `A`
    object GenericTypes{
      // Option[A] = None + Some[A]

      // Variantes individuales

      def someGen[A](implicit aGen: Gen[A]): Gen[Option[A]] = 
        aGen map Option.apply

      def noneGen[A]: Gen[Option[A]] = 
        const(Option.empty[A])

      // Suma de variantes
      def optionGen[A](implicit aGen: Gen[A]): Gen[Option[A]] = 
        oneOf(someGen, noneGen)

      // Con una frecuencia determinada
      def optionFrequencyGen[A](implicit aGen: Gen[A]): Gen[Option[A]] = 
        frequency((2, someGen), (1, noneGen))
    }


  }

}
