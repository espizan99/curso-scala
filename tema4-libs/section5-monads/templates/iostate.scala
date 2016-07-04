package org.hablapps
package fpinscala.scalaz.templates


object IOProgramsState{
  
  // Ahora ya podemos implementar los programas de IO mediante for-comprehensions
  // y reutilizar todos los métodos genéricos que podemos definir para cualquier
  // tipo monádico (ifM, repeatUntil, etc.)
  import fpinscala.lenguajes.IO, IO.Syntax._

  // IOTrans[T] = IOState => (IOState, T) = State[IOState,T]
  import fpinscala.lenguajes.test.{IOS => IOState}, 
    IOState.{IOState => IOTrans},
    fpinscala.lenguajes.test.{PureIO => IOTrans}
  
  // Estado inicial

  // Leemos algo

  
  // Escribimos algo

  
}