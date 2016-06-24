package org.hablapps.fpinscala
package typeclasses

object ADTExtensions{

  // Versión con ADTs y pattern matching externo (Los datos están separados de los intérpretes)
  // Fácil añadir nuevos intérpretes, tedioso de añadir nuevos datos

  object Initial{

    sealed trait Exp
    case class Lit(x: Int) extends Exp
    case class Add(l: Exp, r: Exp) extends Exp

    def eval(e: Exp): Value = e match {
      case Lit(i) => VInt(i)
      case Add(l, r) => VInt(eval(l).getInt + eval(r).getInt)
    }
  }

  // Sin embargo, para añadir nuevos datos sí que necesitamos modificar nuestro ADT
  // y todos nuestros intérpretes

  object DataExtensions{

    sealed trait Exp
    case class Lit(x: Int) extends Exp
    case class Add(l: Exp, r: Exp) extends Exp
    case class Bool(b: Boolean) extends Exp

    def eval(e: Exp): Value = e match {
      case Lit(i) => VInt(i)
      case Add(l, r) => VInt(eval(l).getInt + eval(r).getInt)
      case Bool(b) => VBool(b)
    }

  }

  // Para añadir el pretty printer solo necesitamos una función, no tenemos que tocar
  // codigo antiguo, ni siquiera necesitamos acceso a él

  object FunctionalityExtensions{
    import Initial._

    def print(e: Exp): String = e match {
      case Lit(i) => i.toString
      case Add(l, r) => s"${print(l)} + ${print(r)}"
    }    
  }

}

