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


    // Para que el compilador encuentre automáticamente las evidencias
    // tenemos que anotar las declaraciones como implícitas. 

    implicit val intMonoid: Monoide[Int] = ???
  
    // La composición ya no requiere pasar ni siquiera la instancia
    // del monoide. Se puede activar la opción "-Xprint:typer" del compilador
    // para ver exactamente qué instancia se ha insertado automáticamente.

    import TypeClasses._

    // el compilador encuentra la instancia `intMonoid` automáticamente
    def sumaInt(t: List[Int]): Int = ???
  }

  
  object ContextBounds{
    
    // Lo anterior se puede escribir también mediante `context bounds`,
    // pero entonces hay que acceder a la evidencia implícita mediante
    // la utilidad `implicitly[_]`

    def collapse[A: Monoide](t: List[A]): A = ???
  }

  object AvoidingImplicitly{

    // Para evitar `implicitly`, podemos utilizar algo de azúcar sintáctico.
    // Para las operaciones binarias podemos utilizar `implicit class`,
    // y para las operaciones que devuelven valores estáticos, helper 
    // methods.

    object MonoidSyntax{
  
    }

    // Con estas utilidades, podemos escribir nuestra función de una forma
    // más limpia.
    import MonoidSyntax._

    def collapse[A: Monoide](t: List[A]): A = ???
  }
  
  
}












