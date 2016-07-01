package org.hablapps.fpinscala.scalaz
package test

import org.scalacheck._, Gen._, Arbitrary._
import scalaz.scalacheck.ScalazProperties.monoid
import scalaz.{Equal, Monoid}

object Ejercicio1Props extends Properties("Ejercicio1") {

  // PARA (String, +)
  implicit val stringMonoid: Monoid[String] = Ejercicio1.stringMonoid
  implicit val stringEqual: Equal[String] = scalaz.std.string.stringInstance
  
  property("stringMonoid is a monoid") = monoid.laws[String]

}
