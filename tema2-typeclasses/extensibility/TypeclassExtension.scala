package org.hablapps.fpinscala
package typeclasses

object TypeclassExtensions{
  
  // Probemos ahora con type classes, empezamos con nuestra
  // funcionalidad inicial

  object Initial{

    // La type class `Exp` implementa la clase de los tipos `E` que 
    // pueden representar expresiones aritméticas. La funcionalidad que 
    // proporciona esta type class consiste precisamente en los constructores
    // de las expresiones aritméticas. Puede entenderse, por tanto, como
    // una factoría abstracta de expresiones. 

    trait Exp[E]{
      def lit(i: Int): E
      def add(e1: E, e2: E): E
    }


    object Exp{

      // El tipo de expresiones con el que estuvimos trabajando en los ejemplos
      // anteriores es un miembro de esta type class

      import ADTExtensions.{Initial=>adt}, adt.{Lit, Add}

      object ADTExp extends Exp[adt.Exp]{
        def lit(i: Int): adt.Exp = Lit(i)
        def add(e1: adt.Exp, e2: adt.Exp): adt.Exp =
          Add(e1,e2)
      }

      // Helper methods para trabajar con la factoría más cómodamente.
      
      object Syntax{
        def lit[E](i: Int)(implicit E: Exp[E]) = 
          E.lit(i)

        implicit class BinOps[E](e1: E)(
          implicit E: Exp[E]){
          def +(e2: E) = E.add(e1,e2)
        }
      }

      // Ejemplo de expresión definida de manera abstracta
      object AbstractExpressions{
        import Syntax._

        def expr[E: Exp]: E = lit(3) + lit(7)
      }
    
      // Si queremos evaluar una expresión aritmética definida de manera abstracta
      // por medio de la factoría, podemos crear una instancia de `ADTExpr` mediante
      // esta type class y después evaluarla con el propio intérprete de `ADTExpr`.

      object IndirectEvaluation{
        import AbstractExpressions._

        val v: Value = adt.eval(expr(ADTExp))
      }

      // Podemos comparar el resultado con la evaluación directa utilizando ADTs
      // y clases de objetos

      object DirectEvaluationWithADTs{

        val expr: adt.Exp = adt.Add(adt.Lit(1), adt.Lit(3))

        val v1: Value = adt.eval(expr)
      }

      object DirectEvaluationWithObjectClasses{
        import OOExtensions.{Initial=>oo}
        
        val expr: oo.Exp = oo.Add(oo.Lit(1), oo.Lit(3))

        val v1: Value = expr.eval
      }

      // ¡Pero no tenemos por qué dar este rodeo! Podemos implementar directamente el 
      // intérprete como una instancia de la type class `Exp`

      implicit object eval extends Eval

      trait Eval extends Exp[Value] {
        def lit(i: Int): Value = VInt(i)
        def add(e1: Value, e2: Value): Value =
          VInt(e1.getInt + e2.getInt)
      }

      object DirectEvaluation{
        import Syntax._, AbstractExpressions._

        val v1: Value = expr(eval)

        // or directly
        // val v: Value = lit(1) + lit(3)
      }

    }

  }

  // Dar una interpretación pretty print es tan fácil como crear una
  // nueva instancia para nuestra type class (no necesitamos acceso al código)

  object FunctionalityExtensions{
    import Initial._

    object Exp{
      object print extends Print

      trait Print extends Exp[String] {
        def lit(i: Int): String = s"Lit($i)"
        def add(e1: String, e2: String): String =
          s"Add(${e1}, ${e2})"
      }
    }
  }

  // Ahora toca extender el tipo de datos con nuevos constructores. 

  object DataExtensions{
    import Initial._, FunctionalityExtensions._

    // Como vemos a continuación, podemos extender la type class sencillamente.

    trait IntBoolExp[A] extends Exp[A] {
      def bool(b: Boolean): A
      def iff(e1: A, e2: A, e3: A): A
    }

    // Y con el mismo mecanismo podemos extender los intérpretes para que trabajen
    // con los nuevos constructores. 

    object IntBoolExp{
      object eval extends Eval

      trait Eval extends IntBoolExp[Value] 
        with Initial.Exp.Eval{
        def bool(b: Boolean): Value = ???
        def iff(e1: Value, e2: Value, e3: Value): Value = ???
      }

      trait Print extends IntBoolExp[String] 
        with FunctionalityExtensions.Exp.Print{
        def bool(b: Boolean): String = ???
        def iff(e1: String, e2: String, e3: String): String = ???
      }
    }
  }

}
