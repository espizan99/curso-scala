package org.hablapps.fpinscala
package typeclasses

/** 
 * En la solución mediante type classes, puedo conseguir algo similar a lo realizado
 * en la versión con GADTs. Para ello, parametrizamos la type class mediante un constructor
 * de tipos, en lugar de un tipo normal.
 */
object SafeTypeclasses{

  // El parámetro `E[T]` puede entenderse como el tipo de una expresión aritmética 
  // que cuando se evalúe devolverá un valor de tipo `T`; el tipo `E[_]`, como un
  // tipo que permite representar expresiones aritméticas, es decir, como un lenguaje; 
  // y la type class `Expr[_[_]]`, por tanto, como la clase de los lenguajes de 
  // expresiones aritméticas.

  trait Expr[E[_]] 

  // Con esta nueva versión, ya no puedo crear expresiones artiméticas erroneas:

  // def estoNoCompila[E[_]](implicit E: Expr[E]): E[Int] =
  //   E.add(E.lit(3), E.bool(true))

  def estoSiCompila[E[_]](implicit E: Expr[E]): E[Int] = ???

  // Para implementar los intérpretes tengo que proporcionar un constructor de tipos.
  // En el caso de la evaluación, el objetivo es evaluar una expresión del tipo 
  // `E[Int]` a `Int`, y una expresión de tipo `E[String]` a `String`. Por tanto, 
  // lo que nos hace falta es un constructor "identidad".

  implicit object Eval 

  // Interpretación
  val bar: Int = ???

  // En el caso de la interpretación a Strings, independientemente del tipo que 
  // devuelva la expresión aritmética, el resultado tiene que ser un `String`. 
  // Por tanto, puedo utilizar un constructor de tipos "constante".
  
  type StringF[A] = String

  implicit object Print

  // Interpretación
  val foo: String = ???

}