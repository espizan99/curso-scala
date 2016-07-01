package org.hablapps
package fpinscala.scalacheck.templates

import org.scalacheck.Properties

object TestingMonoids extends Properties("Monoid testing"){
  import scalaz.Monoid

  /**
   * Testeando una instancia simple
   */
  object SimpleInstance extends Properties("Testing new instances of monoids"){

    // Instance para `Int` (1, *)
    import fpinscala.scalaz.UsingMonoids.CreatingSimpleInstances.intMultMonoid

    // Instancias necesarias para el testeo
    import scalaz.std.anyVal.intInstance
    import org.scalacheck.Arbitrary.arbInt
    import scalaz.scalacheck.ScalazProperties.monoid

    property("int mult monoid") = ???
  }

  include(SimpleInstance)

  object DerivedInstance extends Properties("Testing derived instances"){
    import scalaz.scalacheck.ScalazProperties.monoid,
      scalaz.std.anyVal.intInstance, // Monoid[Int]
      fpinscala.scalaz.UsingMonoids.CreatingDerivedInstances.tupleMonoid,
      scalaz.std.tuple._, // Equal[(T1,T2)]
      org.scalacheck.Arbitrary, Arbitrary._ // Arbitrary[(T1,T2)], Arbitrary[Int]

      property("(Int,Int) monoid") = ???

  }

  include(DerivedInstance)
  
}
