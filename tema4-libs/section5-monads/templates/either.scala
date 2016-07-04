package org.hablapps.fpinscala.scalaz.templates

object UsingEitherMonad {

  object Impure{
  
    def factorial(n: Int): Int = {
      @scala.annotation.tailrec
      def go(_n: Int, acc: Int): Int =
        if (_n > 1) go(_n-1, acc*_n)
        else acc

      if (n >= 0) go(n, 1)
      else throw new RuntimeException(
        "No se puede calcular el factorial de un número negativo"
      )
    }

    def toInt(s: String): Int =
      try{ s.toInt }
      catch{ 
        case _: Throwable => throw new RuntimeException(s"$s no es un entero válido") 
      }

    def strToFactorial(s: String): Int = {
      val i = toInt(s)
      factorial(i)
    }
  }

  
  object Pure{
    import scalaz.\/, \/.{left, right}
    import scalaz.syntax.monad._
    
    type Error = RuntimeException

    def factorial(n: Int): \/[Error, Int] = {
      @scala.annotation.tailrec
      def go(_n: Int, acc: Int): Int =
        if (_n > 1) go(_n-1, acc*_n)
        else acc

      if (n >= 0) ???
      else ???
    }

    def toInt(s: String): \/[Error, Int] =
      try{ right(s.toInt) }
      catch { case _: Throwable => 
        ???
      }

    def strToFactorial(s: String): \/[Error, Int] =
      ???
  }

}
