package org.hablapps
package fpinscala.scalacheck

import org.scalacheck.Properties

/** 
 * El objetivo de este ejercicio es ser capaz de hacer pruebas en
 * con Scalacheck para la type class Monoide.
 *
 * Este ejercicio se puede probar desde la consola de `sbt` mediante
 * el alias `test-tema4-ej2`, o explícitamente mediante el comando:
 * 
 *  - testOnly org.hablapps.fpinscala.scalacheck.Ejercicio2
 */
object Ejercicio2 extends Properties("Ejercicio2") {

  /**
   * PARTE I.
   *
   * Crea un generador de tuplas dado un generador para cada
   * tipo de la tupla.
   */
  object PartI{
    import org.scalacheck.Gen, Gen._

    def tupleGen[A, B](implicit ga: Gen[A], gb: Gen[B]): Gen[(A, B)] =
      for {
        a <- ga
        b <- gb
      } yield (a,b)
  }

  /**
   * PARTE II.
   *
   * Se pide crear propiedades para probar que se cumplen determinadas
   * leyes de los monoides para la instancia de monoide que creamos en el
   * Ejercicio 1 Parte I de typeclasses para Option[T].
   */
  object PartII extends Properties("Monoid properties (from scratch)"){
    import org.scalacheck.Prop.forAll,
      scalaz.std.anyVal._, // Semigroup[Int]
      fpinscala.scalaz.Ejercicio1.optionMonoid, // Monoid[Option[Int]]
      scalaz.syntax.monoid._

      implicitly[org.scalacheck.Arbitrary[Option[Int]]]
    // t1 * 1 == 1 * t1 == t1
    property("Demuestra la propiedad del elemento neutro para Option[Int]") =
      forAll{ (o1: Option[Int]) => 
        (o1 |+| mzero[Option[Int]]) == o1
      }
    
    // (t1 * t2) * t3 == t1 * (t2 * t3)
    property("Demuestra la propiedad de asociatividad para Option[Int]") =
      forAll{ (o1: Option[Int], o2: Option[Int], o3: Option[Int]) => 
        (o1 |+| (o2 |+| o3)) == ((o1 |+| o2) |+| o3)
      }

  }

  include(PartII)

  /**
   * PARTE III.
   *
   * Las propiedades de los monoides ya están definidas en Scalaz, utilízalas
   * para comprobar que `optionMonoid` es un monoide.
   *
   */
  object PartIII extends Properties("Monoid properties for Option[T]"){
    import org.scalacheck.Prop.forAll,
      scalaz.scalacheck.ScalazProperties.monoid,
      scalaz.std.anyVal.intInstance, // Semigroup[Int]
      fpinscala.scalaz.Ejercicio1.optionMonoid, // Monoid[Option[Int]]
      org.scalacheck.Arbitrary._ // Arbirary[Option[Int]]
    
    // Crea una instancia de Option[T] para poder utilizarla más adelante
    import scalaz.Equal
    val optionIntEqual: Equal[Option[Int]] = new Equal[Option[Int]]{
      def equal(a1: Option[Int], a2: Option[Int]): Boolean = a1 == a2
    }

    // Crea la propiedad que comprueba que tupleMonoid[String, Int]
    // cumple las leyes de los monoides.
    property("Monoid[Option[Int]] is a monoid") =
      monoid.laws(
        optionMonoid,
        optionIntEqual,
        implicitly[org.scalacheck.Arbitrary[Option[Int]]])
  }

  include(PartIII)

}
