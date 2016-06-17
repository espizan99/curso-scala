package org.hablapps.fpinscala
package typeclasses

object OOExtensions {

  // Empezamos representando expresiones que pueden ser evaluadas en una manera tradicional OO
  // delegando interpretación en las clases

  object Initial{

    trait Exp {
      def eval: Value
    }
  
    case class Lit(x: Int) extends Exp {
      def eval: Value = VInt(x)
    }
  
    case class Add(l: Exp, r: Exp) extends Exp {
      def eval: Value = VInt(l.eval.getInt + r.eval.getInt)
    }
  
  }

  // Ampliamos el lenguaje para soportar booleanos y expresciones condicionales
  // Con añadir una nueva clase basta

  object DataExtensions{
    import Initial._
    
    case class Bool(b: Boolean) extends Exp {
      def eval: Value = VBool(b)
    }
  }

  // Ampliamos la funcionalidad de nuestras expresiones, ahora queremos
  // ser capaces de escribirlas (pretty printer). Tenemos que modificar todas
  // nuestras clases

  object FunctionalityExtensions{
  
    trait Exp {
      def eval: Value
      def print: String
    }

    case class Lit(x: Int) extends Exp {
      def eval: Value = VInt(x)
      def print: String = x.toString
    }

    case class Add(l: Exp, r: Exp) extends Exp {
      def eval: Value = VInt(l.eval.getInt + r.eval.getInt)
      def print: String = s"${l.print} + ${r.print}"
    }

  }

}

