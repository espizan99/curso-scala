package org.hablapps.fpinscala
package lenguajes.test

import org.scalatest._
import lenguajes.io._

class Ejercicio2 extends FlatSpec with Matchers {

  import IOS._

  "WriteANumber" should "leer el número 3 y decir que es impar" in {
    val ios = IOS("3" :: Nil, Nil)
    Program1.Pure.writeANumber[IOState].exec(ios) shouldBe IOS(Nil, "3 es un número impar" :: Nil)
  }

  it should "leer el número 4 y decir que es par" in {
    val ios = IOS("4" :: Nil, Nil)
    Program1.Pure.writeANumber[IOState].exec(ios) shouldBe IOS(Nil, "4 es un número par" :: Nil)
  }

  "WriteANumber2" should "leer el número 3 y decir que es impar" in {
    val ios = IOS("3" :: Nil, Nil)
    Program2.Pure.writeANumber[IOState].exec(ios) shouldBe IOS(Nil, "3 es un número impar" :: "Introduce un número por favor:" :: Nil)
  }

  it should "leer el número 4 y decir que es par" in {
    val ios = IOS("4" :: Nil, Nil)
    Program2.Pure.writeANumber[IOState].exec(ios) shouldBe IOS(Nil, "4 es un número par" :: "Introduce un número por favor:" :: Nil)
  }

  "ReadUntilExit" should "leer cadenas de texto hasta encontrar `exit`" in {
    val ios = IOS("uno" :: "dos" :: "tres" :: "exit" :: Nil, Nil)
    Program3.Pure.readUntilExit[IOState].exec(ios) shouldBe IOS(Nil, Nil)
  }

  it should "parar de leer cuando encuentra un `exit`" in {
    val ios = IOS("uno" :: "dos" :: "tres" :: "exit" :: "otro" :: Nil, Nil)
    Program3.Pure.readUntilExit[IOState].exec(ios) shouldBe IOS("otro" :: Nil, Nil)
  }

  "ReadUntilExit2" should "leer cadenas de texto hasta encontrar `exit`" in {
    val ios = IOS("uno" :: "dos" :: "tres" :: "exit" :: Nil, Nil)
    Program4.Pure.readUntilExit[IOState].exec(ios) shouldBe IOS(Nil, "exit" :: "tres" :: "dos" :: "uno" :: Nil)
  }

  it should "parar de leer cuando encuentra un `exit`" in {
    val ios = IOS("uno" :: "dos" :: "tres" :: "exit" :: "otro" :: Nil, Nil)
    Program4.Pure.readUntilExit[IOState].exec(ios) shouldBe IOS("otro" :: Nil, "exit" :: "tres" :: "dos" :: "uno" :: Nil)
  }

}
