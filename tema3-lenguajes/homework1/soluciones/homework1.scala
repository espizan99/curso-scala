package org.hablapps.fpinscala
package lenguajes
package soluciones

object Homework1{
  import FS.Syntax._, Monad.Syntax._

  /** 
   * Parte I.
   *
   * Implementa un programa que mueve un fichero de un sitio a otro.
   */
  import FSPrograms.Pure.copy 

  def move[F[_]: FS: Monad](orig: String, dest: String): F[Unit] =
    for {
      _ <- copy(orig, dest)
      _ <- deleteFile(orig)
    } yield ()

  /** 
   * Parte II.
   *
   * Implementa un programa que junta el contenido de dos archivos en un tercero.
   */
  def merge2[F[_]: FS: Monad](orig1: String, orig2: String, dest: String): F[Unit] =
    for {
      contents1 <- readFile(orig1)
      contents2 <- readFile(orig2)
      _ <- writeFile(dest)(contents1 + contents2)
    } yield ()

  /** 
   * Parte III.
   *
   * Implementa un programa que junta el contenido de dos archivos en un tercero,
   * y además devuelve la longitud del archivo resultante.
   */
  def merge2bis[F[_]: FS: Monad](orig1: String, orig2: String, dest: String): F[Int] =
    for {
      contents1 <- readFile(orig1)
      contents2 <- readFile(orig2)
      _ <- writeFile(dest)(contents1 + contents2)
    } yield contents1.length + contents2.length
    // for {
    //   _ <- merge2(orig1, orig2, dest)
    //   contents <- readFile(dest)
    // } yield contents.length

  /** 
   * Parte IV.
   *
   * Implementa un programa similar a `merge2`, pero que junte N archivos.
   */
  def mergeN[F[_]: FS: Monad](origs: Seq[String], dest: String): F[Unit] = {
    val wholeContent: F[String] = origs.foldLeft(returns(""))(
      (acc, orig) =>
        for {
          a <- acc
          contents <- readFile(orig)
        } yield a + contents)
    for {
      contents <- wholeContent
      _ <- writeFile(dest)(contents)
    } yield ()
  }

}