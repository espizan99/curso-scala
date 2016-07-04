package org.hablapps
package fpinscala.scalaz


object IOPrograms{
  
  // Ahora ya podemos implementar los programas de IO mediante for-comprehensions
  // y reutilizar todos los métodos genéricos que podemos definir para cualquier
  // tipo monádico (ifM, repeatUntil, etc.)
  import org.hablapps.fpinscala.lenguajes.IO, IO.Syntax._

  object WithOurOwnMonad{
    import fpinscala.lenguajes.Monad

    def sayWhat[F[_]: IO: Monad]: F[String] = 
      Monad[F].flatMap(write("Say what?")){ _ => 
        Monad[F].flatMap(read){ msg => 
          Monad[F].returns(msg)
        }
      }
  }
  
  object WithScalazMonad{
    import scalaz.Monad

    def sayWhat[F[_]: IO: Monad]: F[String] = 
      Monad[F].bind(write("Say what?")){ _ => 
        Monad[F].bind(read){ msg => 
          Monad[F].point(msg)
        }
      }

    def sayWhat_withMap[F[_]: IO: Monad]: F[String] = 
      Monad[F].bind(write("Say what?")){ _ => 
        Monad[F].map(read){ msg => 
          msg
        }
      }
  }

  object WithForComprehension{
    import scalaz.Monad, scalaz.syntax.monad._

    def sayWhat[F[_]: IO: Monad]: F[String] = for{
      _ <- write("Say what?")
      msg <- read
    } yield msg

  }

}