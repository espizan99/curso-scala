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

  trait Monoide[T]

  // Una vez hecho esto, podemos proporcionar esta información de "golpe"

  
  // Para poder utilizar la estructura debemos instanciar la typeclass `Monoid`
  // con los tipos correspondientes. La composición ya solo requiere pasar 
  // la instancia correspondiente

  
  def sumaInt(l: List[Int]): Int = ???

  def concatBis(l: List[String]): String = ???

}