package org.hablapps.fpinscala.hofs
package test

import org.scalatest._
import EjerciciosClase._
import diagrams.{StyleSheets, Pictures}, StyleSheets._, Pictures._

class ClaseSpec extends FlatSpec with Matchers {

  def square(i:Int) = i * i
  def toQuotedString(i:Int) = "'" + i.toString + "'"

  "Tetris" should "L tiene la forma correcta" in {
    Tetris.L shouldBe
      Above(
        Above(
          Beside(Tetris.block(Purple), Tetris.block(Alpha)),
          Beside(Tetris.block(Purple), Tetris.block(Alpha))),
        Beside(Tetris.block(Purple), Tetris.block(Purple)))
  }

  it should "O tiene la forma correcta" in {
    Tetris.O shouldBe
      Above(
        Beside(Tetris.block(NavyBlue), Tetris.block(NavyBlue)),
        Beside(Tetris.block(NavyBlue), Tetris.block(NavyBlue)))
  }

  it should "S tiene la forma correcta" in {
    Tetris.S shouldBe
      Above(
        Beside(
          Beside(Tetris.block(Alpha), Tetris.block(DarkGreen)),
          Tetris.block(DarkGreen)),
        Beside(
          Beside(Tetris.block(DarkGreen), Tetris.block(DarkGreen)),
          Tetris.block(Alpha)))
  }

  it should "T tiene la forma correcta" in {
    Tetris.T shouldBe
      Above(
        Beside(
          Beside(Tetris.block(Brown), Tetris.block(Brown)),
          Tetris.block(Brown)),
        Beside(
          Beside(Tetris.block(Alpha), Tetris.block(Brown)),
          Tetris.block(Alpha)))
  }

  it should "Z tiene la forma correcta" in {
    Tetris.Z shouldBe
      Above(
        Beside(
          Beside(Tetris.block(Teal), Tetris.block(Teal)),
          Tetris.block(Alpha)),
        Beside(
          Beside(Tetris.block(Alpha), Tetris.block(Teal)),
          Tetris.block(Teal)))
  }

  "ComposiciónFunciones" should "componer dos funciones" in {
    ComposicionFunciones.compose(toQuotedString, square)(10) shouldBe "'100'"
    ComposicionFunciones.andThen(square, toQuotedString)(10) shouldBe "'100'"
  }

  "FilterEnFuncionDeFold" should "funcionar como el filter original" in {
    val l1 = FilterEnFuncionDeFoldParaListas.List(1, 2, 3)
    val l2 = FilterEnFuncionDeFoldParaListas.List(1, 3)
    FilterEnFuncionDeFoldParaListas.filter(l1)(_ % 2 != 0) shouldBe l2
  }

}
