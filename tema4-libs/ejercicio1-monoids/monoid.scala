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
  val stringMonoid: Monoid[String] = new Monoid[String]{
    def zero = "a"
    def append(f1: String, f2: => String): String = f1 + f2
  }

  // Option[A]
  import scalaz.Semigroup

  implicit def optionMonoid[A: Semigroup] = new Monoid[Option[A]]{
    def zero = None
    
    def append(o1: Option[A], o2: => Option[A]): Option[A] = 
      ((o1,o2): Tuple2[Option[A],Option[A]]) match {
        case (None, o2) => o2 
        case (o1,None) => o1
        case (Some(e1), Some(e2)) => Some(e1 |+| e2)
      }
  }

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
  def multiplicar[A: Monoid](a: A, n: Int): A = 
    List.fill(n)(a).foldLeft(mzero[A])(
      (acc: A, a: A) => acc |+| a
    ) 
  
  def foldMap[A, B: Monoid](l: List[A])(f: A=>B): B = ???

}

