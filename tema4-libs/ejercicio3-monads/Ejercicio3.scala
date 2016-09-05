package org.hablapps
package fpinscala.scalaz

import org.hablapps.fpinscala.lenguajes.IO, IO.Syntax._
import scalaz.Monad
import scalaz.syntax.monad._

object Ejercicio3{
  
  // Considérense los siguientes programas de IO implementados mediante 
  // for-comprehensions
  import fpinscala.lenguajes.IO, IO.Syntax._

  object WithForComprehension{
    import scalaz.Monad, scalaz.syntax.monad._

    def sayWhat[F[_]: IO: Monad]: F[String] = for{
      _ <- write("Say what?")
      msg <- read
    } yield msg

    def echo[F[_]: IO: Monad]: F[Unit] = for{
      msg <- read
      _ <- write(msg)
    } yield ()

    def echo2[F[_]: IO: Monad]: F[String] = for{
      msg <- read
      _ <- write(msg)
    } yield msg

  }

  // Se pide implementar estos programas mediante los operadores
  // monádicos que ofrece Scalaz: >>, >>= y >>!
  object PartI{
    
    // Utilizar `>>`
    def sayWhat[F[_]: IO: Monad]: F[String] =
      write("Say what?") >> read

    // Utilizar `>>=`
    def pureEcho[F[_]: IO: Monad]: F[Unit] =
      read >>= (write(_))

    // Utilizar `>>!`
    def pureEcho2[F[_]: IO: Monad]: F[String] =
      read >>! (write(_))
  }
  
}