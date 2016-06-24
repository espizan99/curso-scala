package org.hablapps.fpinscala
package lenguajes.io
package soluciones

/**
 * Complica el programa 1 un poco, haciendo que antes de nada se
 * escriba por pantalla "Introduce un número por favor:".
 *
 * Ejemplo de ejecución:
 *
 *   scala> runWriteANumber2
 *   Introduce un número por favor:
 *   (Escribe "8")
 *   8 es un número par
 *
 * Para probar estos programas puedes ejecutar el comando de sbt: 
 *   `test-tema3-ejercicio2`
 */
object Program2{

  // Versión impura
  import lenguajes.ImpureIO
  
  trait Impure{ IO: ImpureIO.IO with Program1.Impure => 
    import IO._

    def writeANumber: Unit = {
      write("Introduce un número por favor:")
      writeANumber
    }

  }

  // Versión pura
  object Pure{
    import IO.Syntax._

    def writeANumber[F[_]: IO]: F[Unit] =
      for {
        _ <- write("Introduce un número por favor:")
        _ <- Program1.Pure.writeANumber[F]
      } yield ()
  }

}
