package org.hablapps.fpinscala.typeclasses
package test

import _root_.org.scalatest._
import EjerciciosTypeClasses._

class ClaseSpec extends FlatSpec with Matchers {

  val l1 = 1 :: 4 :: 3 :: 5 :: 2 :: Nil
  val l2 = '1' :: '4' :: '3' :: '5' :: '2' :: Nil
  val l3 = "1" :: "4" :: "3" :: "5" :: "2" :: Nil

  "SortAscendingInt" should "ordenar la lista de manera ascendente" in {
    ParteIII.sortAscendingInt(l1) shouldBe 1 :: 2 :: 3 :: 4 :: 5 :: Nil
  }

  "SortAscendingChar" should "ordenar la lista de manera ascendente" in {
    ParteIII.sortAscendingChar(l2) shouldBe '1' :: '2' :: '3' :: '4' :: '5' :: Nil
  }

  "SortAscendingString" should "ordenar la lista de manera ascendente" in {
    ParteIII.sortAscendingString(l3) shouldBe "1" :: "2" :: "3" :: "4" :: "5" :: Nil
  }

}
