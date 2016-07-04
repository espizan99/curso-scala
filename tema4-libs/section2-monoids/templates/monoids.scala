package org.hablapps.fpinscala.scalaz.templates

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

    val intMultMonoid: Monoid[Int] = ???

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
    def collapse[A: Monoid](t: List[A]): A = ???
  }
  
  object CreatingDerivedInstances{
    import scalaz.Monoid,
      scalaz.syntax.monoid._

    // Creamos instancias a partir de otras instancias
    
    def tupleMonoid[T1: Monoid, T2: Monoid]: Monoid[(T1,T2)] = ???
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
