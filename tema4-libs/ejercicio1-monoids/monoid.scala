package org.hablapps.fpinscala.scalaz

import scalaz.Monoid
import scalaz.syntax.monoid._

/** 
 * El objetivo de este ejercicio es utilizar la type class Monoid definida
 * en Scalaz. Para ello crearemos instancias de los monoides que ya tenemos
 * implementados, y utilizaremos los operadores que esta type class
 * proporcionas (mzero, |+|).
 */
object Ejercicio1 {

  /**
   * PARTE I.
   *
   * Proporciona instancias de Monoid para String, Option[A] y `Tree[A]`
   */

  // (String, +)
  val stringMonoid: Monoid[String] = ???

  // Option[A]
  import scalaz.Semigroup

  implicit def optionMonoid[A: Semigroup]: Monoid[Option[A]] = ???

  /**
   * PARTE II.
   *
   * A continuación se pide implementar los métodos `multiplicar` y `foldMap`
   * utilizando los operadores que nos proporciona Monoid (mzero, |+|)
   *
   * PISTA: utiliza los siguientes métodos: 
   * 
   * - `List.fill[A](n: Int)(a: A)`, que genera una lista de `a`s de longitud `n`
   * - `_.foldLeft[A,B](b: B)(f: (B,A)=>B): B, para listas de tipo `List[A]`.
   */
  def multiplicar[A: Monoid](a: A, n: Int): A = ???
  
  def foldMap[A, B: Monoid](l: List[A])(f: A=>B): B = ???

}

