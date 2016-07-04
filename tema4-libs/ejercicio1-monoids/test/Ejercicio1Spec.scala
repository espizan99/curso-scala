package org.hablapps.fpinscala
package scalaz
package test

import org.scalatest._
import Ejercicio1._
import _root_.scalaz.Monoid
import _root_.scalaz.std.anyVal.intInstance

class Ejercicio1Spec extends FlatSpec with Matchers {

  val intMonoidAddition: Monoid[Int] = intInstance
  val intMonoidMultiplication = new Monoid[Int] {
    def zero: Int = 1
    def append(f1: Int, f2: => Int): Int = f1 * f2
  }

  "Multiplicar" should "devolver el elemento neutro si multiplicamos por 0" in {
    multiplicar(3, 0)(intMonoidAddition) shouldBe 0
    multiplicar(3, 0)(intMonoidMultiplication) shouldBe 1
  }

  it should "devolver el propio elemento si multiplicamos por 1" in {
    multiplicar(3, 1)(intMonoidAddition) shouldBe 3
    multiplicar(3, 1)(intMonoidMultiplication) shouldBe 3
  }

  it should "funcionar correctamente en cualquier otro caso" in {
    multiplicar(3, 5)(intMonoidAddition) shouldBe 15
    multiplicar(3, 5)(intMonoidMultiplication) shouldBe 243
  }

  "FoldMap" should "devolver el elemento neutro si la lista está vacía" in {
    foldMap(List.empty[String])(_.length)(intMonoidAddition) shouldBe 0
    foldMap(List.empty[String])(_.length)(intMonoidMultiplication) shouldBe 1
  }

  it should "aplicar la funcion al elemento si la lista tiene longitud 1" in {
    foldMap("hola" :: Nil)(_.length)(intMonoidAddition) shouldBe 4
    foldMap("hola" :: Nil)(_.length)(intMonoidMultiplication) shouldBe 4
  }

  it should "funcionar correctamente en cualquier otro caso" in {
    foldMap("uno" :: "dos" :: "tres" :: Nil)(_.length)(intMonoidAddition) shouldBe 10
    foldMap("uno" :: "dos" :: "tres" :: Nil)(_.length)(intMonoidMultiplication) shouldBe 36
  }

}
