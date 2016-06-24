package org.hablapps.fpinscala.hofs;

object EjerciciosClase {

  // Ejercicio Diagrams: Implementar las figuras del tetris con la
  // librería de diagrams
  object Tetris {
    import diagrams._, Shapes._, StyleSheets._, Pictures._

    // Se proporciona una Picture que representa un cuadrado de 100 x 100
    // Utiliza estos "bloques" para crear las figuras
    def block(c: Color): Picture =
      Place(FillColor(c) :: Nil, Rectangle(100, 100))

    val I: Picture =
      block(Maroon) above
      block(Maroon) above
      block(Maroon) above
      block(Maroon)

    val J: Picture =
      (block(Alpha)     beside block(LightGray)) above
      (block(Alpha)     beside block(LightGray)) above
      (block(LightGray) beside block(LightGray))

    // Utiliza el color `Purple` para esta figura
    val L: Picture =
      (block(Purple) beside block(Alpha)) above
      (block(Purple) beside block(Alpha)) above
      (block(Purple) beside block(Purple))

    // Utiliza el color `NavyBlue` para esta figura
    val O: Picture =
      (block(NavyBlue) beside block(NavyBlue)) above
      (block(NavyBlue) beside block(NavyBlue))

    // Utiliza el color `DarkGreen` para esta figura
    val S: Picture =
      (block(Alpha) beside block(DarkGreen) beside block(DarkGreen)) above
      (block(DarkGreen) beside block(DarkGreen) beside block(Alpha))

    // Utiliza el color `Brown` para esta figura
    val T: Picture =
      (block(Brown) beside block(Brown) beside block(Brown)) above
      (block(Alpha) beside block(Brown) beside block(Alpha))

    // Utiliza el color `Teal` para esta figura
    val Z: Picture =
      (block(Teal) beside block(Teal) beside block(Alpha)) above
      (block(Alpha) beside block(Teal) beside block(Teal))

    def comprobacion = {
      println("L:")
      println(SVG.toSVG(Drawings.drawPicture(L)))
      println("O:")
      println(SVG.toSVG(Drawings.drawPicture(O)))
      println("S:")
      println(SVG.toSVG(Drawings.drawPicture(S)))
      println("T:")
      println(SVG.toSVG(Drawings.drawPicture(T)))
      println("Z:")
      println(SVG.toSVG(Drawings.drawPicture(Z)))
    }
  }

  // Ejercicio composición de funciones: Implementar
  // 'compose' y después implementar 'andThen' a partir de 'compose':
  object ComposicionFunciones {
    def compose[A,B,C](g: B => C, f: A => B): A => C = (a: A) => g(f(a))
    def andThen[A,B,C](f: A => B, g: B => C): A => C = compose(g, f)

    def comprobacion = {
      def square(i:Int) = i * i
      def toQuotedString(i:Int) = "'" + i.toString + "'"
      assert(compose(toQuotedString, square)(10) == "'100'")
      assert(andThen(square, toQuotedString)(10) == "'100'")
    }
  }

  // Ejercicio Catamorfismos: Dado el método 'fold' para listas,
  // implementar 'filter' en listas usando 'fold'.
  object FilterEnFuncionDeFoldParaListas {

    // Esta es la lista de la transparencia 48
    sealed abstract class List[A]
    object List {
      def apply[A](as: A*):List[A] = {
        if (as.isEmpty) Nil()
        else Cons(as.head, apply(as.tail: _*))
      }
    }
    case class Cons[A](head: A, tail: List[A]) extends List[A]
    case class Nil[A]() extends List[A]

    // Este es el fold de la transparencia 50
    def fold[A,B](l: List[A])(nil: B, cons: (A,B) => B): B = l match {
      case Nil() => nil
      case Cons(h, t) => cons(h, fold(t)(nil, cons))
    }

    def filter[A](l: List[A])(p: A => Boolean): List[A] = {
      fold[A, List[A]](l)(List(), (a: A, b: List[A]) => if(p(a))  Cons(a, b) else b)
    }
    
    def comprobacion = {
      val l1 = List("","hola","que","","tal")
      val l2 = List(1,2,3,4,5)
      assert(filter(l1)(!_.isEmpty) == List("hola", "que", "tal"))
      assert(filter(l2)(_ % 2 != 0) == List(1, 3, 5))
    }
  }

  // Descomenta la siguiente línea para comprobar el Ejercicio1
  Tetris.comprobacion

  // Descomenta la siguiente línea para comprobar el Ejercicio2
  //ComposicionFunciones.comprobacion

  // Descomenta la siguiente línea para comprobar el Ejercicio3
  FilterEnFuncionDeFoldParaListas.comprobacion

}