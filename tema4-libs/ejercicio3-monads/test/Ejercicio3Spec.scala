package org.hablapps.fpinscala
package scalaz
package test

import org.scalatest._
import IOPrograms._

class Ejercicio3Spec extends FlatSpec with Matchers {

  import lenguajes.test._, IOS._
  implicit val _ = lenguajes.test.PureIO

  "SayWhat" should "funcionar" in {
    val ios = IOS("hi there!" :: Nil, Nil)
    Ejercicio3.PartI.sayWhat[IOState].run(ios) shouldBe (IOS(Nil, "Say what?" :: Nil), "hi there!")
  }

  "PureEcho" should "funcionar" in {
    val ios = IOS("echo this" :: Nil, Nil)
    Ejercicio3.PartI.pureEcho[IOState].run(ios) shouldBe (IOS(Nil, "echo this" :: Nil), ())
  }

  "PureEcho2" should "funcionar" in {
    val ios = IOS("echo this" :: Nil, Nil)
    Ejercicio3.PartI.pureEcho2[IOState].run(ios) shouldBe (IOS(Nil, "echo this" :: Nil), "echo this")
  }

}
