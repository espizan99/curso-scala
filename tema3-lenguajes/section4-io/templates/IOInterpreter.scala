package org.hablapps.fpinscala
package lenguajes.io
package templates

object Interpreter{

  // Interpreter

  import scala.io.StdIn._, typeclasses.Id

  trait ConsoleIO extends lenguajes.ImpureIO.IO{
    def read(): String = readLine
    def write(msg: String): Unit = println(msg)
  }

  // Ejemplo de ejecución

  object Program extends lenguajes.ImpureIO.Logic with ConsoleIO

  def runEcho: String = Program.echo

}

