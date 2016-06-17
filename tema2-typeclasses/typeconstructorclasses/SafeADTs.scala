package org.hablapps.fpinscala
package typeclasses

/** 
 * Para solucionarlo podemos parametrizar el tipo de datos, utilizando 
 * lo que se denomina un Generalized Algebraic Data Type (GADT).
 */
object SafeADTs{

  // El parámetro A puede tomar tipos distintos en cada variante
  // del ADT. En eso consisten los GADTs.

  sealed trait Exp[A]
  case class Lit(x: Int) extends Exp[Int]
  case class Add(l: Exp[Int], r: Exp[Int]) extends Exp[Int]
  case class Bool(b: Boolean) extends Exp[Boolean]

  // Y ya no puedo crear expresiones erroneas
  // val estoNoCompila: Exp[Int] = Add(Lit(3), Bool(true))

  // ¡Y Ya no necesitamos el tipo `Value` para el intérprete `eval`!

  def eval[A](e: Exp[A]): A = e match {
    case Lit(i) => i
    case Add(l, r) => eval(l) + eval(r)
    case Bool(b) => b
  }

  def print[A](e: Exp[A]): String = e match {
    case Lit(i) => i.toString
    case Add(l, r) => s"${print(l)} + ${print(r)}"
    case Bool(b) => b.toString
  }
  
}


