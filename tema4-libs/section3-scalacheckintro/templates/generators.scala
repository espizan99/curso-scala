package org.hablapps
package fpinscala.scalacheck.templates

object Generators {
  import org.scalacheck._, Arbitrary._, Gen._

  // Generadores simples
  object SimpleTypes{

    // Proporcionados de manera estándar por Scalacheck

    val intGen: Gen[Int] = arbitrary[Int]
    val stringGen: Gen[String] = arbitrary[String]
    
    // Creación de generadores a partir de otros generadores

    val intsAsStringGen: Gen[String] = ???
    
    // Más generadores simples
    
    case class One(i: Int)

    val oneGen: Gen[One] = ???
  }
    
  // Productos
  object Products {
    import SimpleTypes.{intGen, stringGen}

    case class User(name: String, age: Int)

    // Generación de usuarios arbitrarios (raw)

    val userGen_raw: Gen[User] = ???

    // Con flatMap y map
    val userGen0: Gen[User] = ???

    // Con for-comprehensions
    val userGen: Gen[User] = ???

    // Con restricciones (suchThat)
    val userGen2: Gen[User] = ???

    // Con restricciones (if)
    val userGen3: Gen[User] = ???

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
      val someIntGen: Gen[Option[Int]] = ???

      val noneIntGen: Gen[Option[Int]] = ???

      // Generador para la suma de variantes
      val optionIntGen: Gen[Option[Int]] = ???
    }

    // Y ahora para tipos genéricos T[A]: para poder generar valores de
    // tipo `T[A]` necesitamos disponer de generadores de tipo `A`
    object GenericTypes{
      // Option[A] = None + Some[A]

      // Variantes individuales

      
      // Suma de variantes
      
      // Con una frecuencia determinada
      def optionFrequencyGen[A](implicit aGen: Gen[A]): Gen[Option[A]] = ???
    }


  }

}
