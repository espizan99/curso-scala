package org.hablapps.fpinscala
package typeclasses

/**
  El polimorfismo paramétrico a veces no es suficiente porque hace falta
  información concreta sobre el parámetro. Esa información se puede abstraer 
  mediante parametrización de valores y/o funciones. 
 */
object HigherOrderFunctions{
  
  // Funciones monolíticas

  def sumaInt(l: List[Int]): Int = 
    l match {
      case Nil => 0
      case x :: r => x + sumaInt(r)
    }

  def concat(l: List[String]): String = 
    l match {
      case Nil => ""
      case x :: r => x + concat(r)
    }

  // Abstracción

  def collapse[T](l: List[T])(
    zero: T, append: (T,T) => T): T = 
    l match {
      case Nil => zero
      case x :: r => append(x, collapse(r)(zero,append))
    }  

  // Composición

  def sumaIntBis(l: List[Int]): Int =
    // collapse[Int](l)(0, (x: Int,y: Int) => x+y )
    collapse(l)(0, _+_ )
    
  def concatBis(l: List[String]): String =
    collapse[String](l)("", (x: String,y: String) => x+y)

}






