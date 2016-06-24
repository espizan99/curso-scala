package org.hablapps.fpinscala
package typeclasses


/**
  Scala ofrece facilidades para trabajar con type classes, basadas principalmente
  en el mecanismo de implícitos.
*/
object Implicits{

  import TypeClasses.Monoide

  object ImplicitParameters{

    // Pasar las operaciones del monoide una a una era engorroso, pero 
    // si nos podemos ahorrar incluso pasar el monoide manualmente 
    // mejor aún: declaramos el argumento como `implicit` y dejamos
    // que el compilador busque la instancia correspondiente.

    def collapse[T](l: List[T])(
      implicit monoide: Monoide[T]): T = 
      l match {
        case Nil => monoide.zero
        case x :: r => 
          monoide.append(x, collapse(r)(monoide))
      }  

    // Para que el compilador encuentre automáticamente las evidencias
    // tenemos que anotar las declaraciones como implícitas. 

    implicit val intM: Monoide[Int] =
      TypeClasses.intMonoid

    // Ambigüedad de implícitos
    // implicit val intM2: Monoide[Int] = ???
  
    // La composición ya no requiere pasar ni siquiera la instancia
    // del monoide. Se puede activar la opción "-Xprint:typer" del compilador
    // para ver exactamente qué instancia se ha insertado automáticamente.

    import TypeClasses._

    // el compilador encuentra la instancia `intMonoid` automáticamente
    def sumaInt(t: List[Int]): Int =
      collapse(t)

  }

  
  object ContextBounds{
    
    // Lo anterior se puede escribir también mediante `context bounds`,
    // pero entonces hay que acceder a la evidencia implícita mediante
    // la utilidad `implicitly[_]`

    def collapse[A: Monoide](l: List[A]): A = {
      val monoide = implicitly[Monoide[A]]
      l match {
        case Nil => monoide.zero
        case x :: r => 
          monoide.append(x, collapse(r))
      }
    }
  }

  object AvoidingImplicitly{

    // Para evitar `implicitly`, podemos utilizar algo de azúcar sintáctico.
    // Para las operaciones binarias podemos utilizar `implicit class`,
    // y para las operaciones que devuelven valores estáticos, helper 
    // methods.

    object MonoidSyntax{
      def zero[T](implicit M: Monoide[T]): T = M.zero
      def append[T](t1: T, t2: T)(
          implicit M: Monoide[T]): T = M.append(t1,t2)

      implicit class InfixOps[T](t1: T)(
        implicit M: Monoide[T]){
        def |+|(t2: T): T = M.append(t1,t2)
      }

    }

    // Con estas utilidades, podemos escribir nuestra función de una forma
    // más limpia.
    import MonoidSyntax._

    def collapse[A: Monoide](l: List[A]): A =
      l match {
        case Nil => zero
        case x :: r => 
          x |+| collapse(r)
      }
  }
  
  
}












