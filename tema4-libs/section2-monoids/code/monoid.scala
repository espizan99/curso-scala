package org.hablapps.fpinscala.scalaz

object UsingMonoids{

  object UsingMonoidsGenerically{
    import scalaz.Monoid

    // Definición de función usando la type class `Monoid`
    def collapse[A: Monoid](t: List[A]): A = {
      t match {
        case Nil => Monoid[A].zero
        case a::t => Monoid[A].append(collapse(t),a)
      }
    }
  }

  object UsingSimpleInstances{
    import UsingMonoidsGenerically.collapse
    // Instancia para el tipo Int (0, +)
    import scalaz.std.anyVal.intInstance

    def reduce(l: List[Int]): Int = 
      collapse(l)(intInstance)
  }

  object CreatingSimpleInstances{
    import scalaz.Monoid

    // No queremos la instancia estándar, queremos la instancia (1, *)
    // (la cual está en Scalaz también, pero nos la crearemos nosotros)

    val intMultMonoid: Monoid[Int] = new Monoid[Int]{
      def zero: Int = 1
      def append(f1: Int, f2: => Int): Int = f1*f2
    }

  }

  object UsingOurInstances{
    import UsingMonoidsGenerically.collapse
    import CreatingSimpleInstances._

    def reduce(l: List[Int]): Int = 
      collapse(l)(intMultMonoid)
  }

  object WithSyntacticSugar{
    import scalaz.Monoid,
      scalaz.syntax.monoid._

    // Podemos utilizar azúcar sintáctico: mzero y |+|
    def collapse[A: Monoid](t: List[A]): A = {
      t match {
        case Nil => mzero
        case a::t => collapse(t) |+| a
      }
    }
  }
  
  object CreatingDerivedInstances{
    import scalaz.Monoid,
      scalaz.syntax.monoid._

    // Creamos instancias a partir de otras instancias
    
    def tupleMonoid[T1: Monoid, T2: Monoid]: Monoid[(T1,T2)] =
      new Monoid[(T1,T2)]{
        def zero: (T1,T2) = (mzero[T1], mzero[T2])
        def append(f1: (T1, T2), f2: => (T1, T2)): (T1,T2) = 
          (f1._1 |+| f2._1, f1._2 |+| f2._2)
      }
  }

  object UsingDerivedInstances{
    import UsingMonoidsGenerically.collapse

    // Usando la instancia de `Monoid`
    import scalaz.std.anyVal.intInstance,
      scalaz.std.string.stringInstance,
      scalaz.std.tuple._

    def reduce(t: List[(String,Int)]): (String, Int) = 
      collapse(t)

  }
  
}
