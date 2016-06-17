package org.hablapps.fpinscala.typeclasses
package test

import _root_.org.scalatest._
import EjerciciosTypeClasses._
import diagrams._, HomeworkExtensibility._, Transformations._, StyleSheets._, Shapes._

class HomeExtensibilitySpec extends FlatSpec with Matchers {

  "DrawPicture" should "convertir a Drawings la imagen" in {
    poc[Drawings.Drawing](Picture.drawPicture) shouldBe
      List(
        (Identity, List(FillColor(Red)), Circle(10.0)),
        (Identity, List(FillColor(Blue)), Triangle(10.0)))
  }

  "StringPicture" should "pretty print la imagen" in {
    poc[String](Picture.stringPicture) shouldBe
      "Beneath(Place(List(FillColor(Red)), Circle(10.0)), Place(List(FillColor(Blue)), Triangle(10.0)))"
  }

}
