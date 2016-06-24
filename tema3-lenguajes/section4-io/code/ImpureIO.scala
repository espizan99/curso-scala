package org.hablapps.fpinscala
package lenguajes

import scala.io.StdIn._

object ImpureIO{

  // Interface

  trait IO{
    def read(): String
    def write(msg: String): Unit
  }

  // Logic 

  trait Logic{ IO: IO => 
    def echo: String = {
      val msg: String = IO.read
      IO.write(read)
      msg
    }
  }

  // Interpretation

  trait ConsoleIO extends IO{
    def read(): String = readLine
    def write(msg: String): Unit = println(msg)
  }

  // All together
  object Program extends Logic with ConsoleIO

}