package org.hablapps.fpinscala.scalacheck

import org.scalacheck._, Gen._

/**
 * El objetivo de este ejercicio es ser capaz de hacer pruebas en
 * con Scalacheck para la type class Monoide.
 *
 * ES NECESARIO QUE TENGAS HECHOS LOS EJERCICIOS DE MONOID EN SCALAZ
 */
object Ejercicio1 extends Properties("Ejercicio1") {

  /**
   * En el archivo TreeGen.scala se proporciona una estructura de árboles
   * multicamino, con una instancia de monoide y un generador de instancias.
   */
  import org.hablapps.fpinscala.scalacheck.TreeGen._

  /**
   * PARTE I.
   *
   * Se pide crear propiedades para probar que se cumplen determinadas
   * leyes de los monoides para la instancia de monoide que creamos en el
   * Ejercicio 1 Parte III de typeclasses para árboles multicamino.
   */

  // (t1 add t2) add t3 === t1 add (t2 add t3)
  property("Demuestra la propiedad de asociatividad para Tree") = ???

  // t1 add empty === empty add t1 === t1
  property("Demuestra la propiedad del elemento neutro para Tree") = ???

  /**
   * PARTE II.
   *
   * Las propiedades de los monoides ya están definidas en Scalaz, utilízalas
   * para comprobar que `treeMonoid` es un monoide.
   *
   * Para este ejercicio utilizaremos la instancia de Monoid[Tree] del Ejercicio4
   * de Scalaz.
   */

  import scalaz.scalacheck.ScalazProperties.monoid
  import scalaz.std.anyVal.intInstance
  import scalaz.Equal

  // Crea una instancia de Equal[Tree[Int]] para poder utilizarla más adelante
  val treeIntEqual: Equal[Tree[Int]] = ???

  // Crea la propiedad que comprueba que treeMonoid[Int] cumple las
  // leyes de los monoides.
  property("treeMonoid[Int] is a monoid") = ???

  // Some functions that we want to test
  /**
   * Vamos a añadir funcionalidad extra a nuestro Tree[A] para poder
   * hacer algunas pruebas con Scalacheck
   *
   * Se proporcionan los siguientes metodos sobre Tree[A]
   */
  implicit class TreeExtension[A](tree: Tree[A]){

    def foldLeft[B](zero: B)(f: (B, A) => B): B = {
      @scala.annotation.tailrec
      def aux(ts: Seq[Tree[A]], acc: B): B = ts match {
        case Node(tss, a) :: tsss => aux(tss ++ tsss, f(acc, a))
        case Empty() :: tss => aux(tss, acc)
        case Nil => acc
      }

      tree match {
        case Node(children, a) => aux(children, f(zero, a))
        case Empty() => zero
      }
    }

    def depth(): Int =
      tree match {
        case Empty() => 0
        case Node(children, _) =>
          children.map(_.depth).max + 1
      }

    val isEmpty: Boolean = tree match {
      case Node(_, _) => false
      case Empty() => true
    }

    def map[B](f: A => B): Tree[B] = tree match {
      case Node(children, value) => Node(children map (_ map f), f(value))
      case Empty() => Empty()
    }

    def pretty: String = {
      def prettyAux(identation: Int): String = {
        tree match {
          case Node(cs, a) => s"${" "*identation}Value: $a\n" + cs.map(_.pretty(identation + 2)).mkString("")
          case Empty() => "(Empty)"
        }
      }
      prettyAux(0)
    }

    def length[A]: Int =
      foldLeft(0)((acc, _) => acc+1)

    def sum(implicit ev: A =:= Int): Int =
      foldLeft(0)((acc, a) => acc+a)

    def max(implicit ev: A =:= Int): Option[Int] =
      if (isEmpty) None
      else Option(
        foldLeft(Int.MinValue)((res, a) => if (a > res) a else res)
      )

    def merge(tree: Tree[A], rootValue: A): Tree[A] =
      Node(List(tree, tree), rootValue)

    def preOrder: List[A] =
      foldLeft(List.empty[A])((acc, a) => a +: acc).reverse
  }

  /**
   * PARTE III.
   *
   * Diferentes propiedades que debe satisfacer el método `merge`.
   */

  property("La longitud de la fusión de 2 árboles es igual a la suma de las longitudes de los dos árboles + 1") = ???

  property("La suma de la fusión de 2 árboles es igual a la suma de la suma de los dos árboles") = ???

  property("El número máximo de la fusión de 2 árboles es igual al mayor de los números máximos de los dos árboles") = ???

  property("Los nodos en pre-orden de la fusión de 2 árboles es igual a la concatenación de los nodos en pre-orden de los 2 árboles") = ???

  /**
   * PARTE IV.
   *
   * Diferentes propiedades que debe satisfacer el método `preOrder`.
   */
  property("La lista de nodos en pre-orden debe tener el mismo número de elementos que el árbol") = ???

  property("La suma de los elementos de pre-orden debe ser igual a la suma del árbol") = ???

  property("El número máximo de pre-orden debe ser igual que el número máximo del árbol") = ???

}
