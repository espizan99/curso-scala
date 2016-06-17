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


  // Composición

  def sumaIntBis(l: List[Int]): Int = ???
    
  def concatBis(l: List[String]): String = ???

}
