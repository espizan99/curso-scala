package org.hablapps.fpinscala
package typeclasses

/**
  Para evitar tener que pasar una y otra vez la misma información sobre un 
  determinado parámetro, podemos agrupar la información requerida en una 
  nueva estructura y pasar únicamente una instancia de dicha estructura.
*/

object TypeClasses{

  // Las operaciones que nos hacen falta son las correspondientes a un monoide.
  // Un monoide es un tipo de datos para el que podemos dar un zero y 
  // una operación binaria de composición (suma). Estas operaciones deben
  // satisfacer determinadas leyes: 1) elemento neutro y 2) asociatividad.
  // Un monoide define una clase de tipos (type class): aquellos para los que hay 
  // un zero y una operación binaria con esas propiedades.

  trait Monoide[T]{
    val zero: T
    def append(t1: T, t2: T): T
  }

  object Monoide{
    implicit val IntM: Monoide[Int] = intMonoid
    implicit val StringM : Monoide[String] = stringMonoid
  }

  // Una vez hecho esto, podemos proporcionar esta información de "golpe"

  def collapse[T](l: List[T])(monoide: Monoide[T]): T = 
    l match {
      case Nil => monoide.zero
      case x :: r => 
        monoide.append(x, collapse(r)(monoide))
    }  

  
  // Para poder utilizar la estructura debemos instanciar la typeclass `Monoid`
  // con los tipos correspondientes. La composición ya solo requiere pasar 
  // la instancia correspondiente
  val intMonoid: Monoide[Int] = new Monoide[Int]{
    val zero: Int = 0
    def append(t1: Int, t2: Int): Int = t1+t2
  }

  val stringMonoid: Monoide[String] = new Monoide[String]{
    val zero: String = ""
    def append(t1: String, t2: String): String = t1+t2
  }
  
  def sumaInt(l: List[Int]): Int =
    collapse(l)(intMonoid: Monoide[Int])

  def concatBis(l: List[String]): String =
    collapse(l)(stringMonoid: Monoide[String])


}