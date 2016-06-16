package org.hablapps.fpinscala.hofs
package test

import org.scalatest._
import homework.Exercise3._

class Ej3Spec extends FlatSpec with Matchers {

  val e1 = Times(Add(IntVal(1), IntVal(8)), Subtract(IntVal(5), IntVal(2)))

  "Pretty" should "funcionar de forma correcta para IntExpr" in {
    PartII.pretty(e1) shouldBe "((1 + 8) * (5 - 2))"
  }

  "Depth" should "funcionar de forma correcta para IntExpr" in {
    PartII.depth(e1) shouldBe 2
  }

  "Eval" should "funcionar de forma correcta para IntExpr" in {
    PartII.eval(e1) shouldBe 27
  }

  "PrettyFold" should "funcionar de forma correcta para IntExpr" in {
    PartIII.pretty(e1) shouldBe "((1 + 8) * (5 - 2))"
  }

  "DepthFold" should "funcionar de forma correcta para IntExpr" in {
    PartIII.depth(e1) shouldBe 2
  }

  "EvalFold" should "funcionar de forma correcta para IntExpr" in {
    PartIII.eval(e1) shouldBe 27
  }

}
