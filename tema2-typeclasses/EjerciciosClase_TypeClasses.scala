package org.hablapps.fpinscala.typeclasses

/**
 * Se quiere poder ordenar listas de elementos en orden ascendente o descendente.
 * Vamos a ver como usando type classes podemos evitar tener que implementar
 * esas funciones para cada tipo, como se hace dentro de 'object Monolitico'.
 */
object EjerciciosTypeClasses {

  /** 
   * PARTE I: Programas monolíticos
   *
   * Versiones monolíticas de los programas que queremos modularizar.
   */
  object ParteI{
    
    // Variantes de `sortAscending`

    def sortAscendingChar(l: List[Char]): List[Char] =
      l.sortWith( (c1: Char, c2: Char) => c1 < c2 )

    def sortAscendingInt(l: List[Int]): List[Int] =
      l.sortWith( (c1: Int, c2: Int) => c1 < c2 )

    def sortAscendingString(l: List[String]): List[String] =
      l.sortWith( (c1: String, c2: String) => c1 < c2 )
    
  }

  /**
   * Parte II: Parte abstracta
   *
   * Crear una type class que sirva para comparar elementos e implementar
   * la función genérica que permita ordenar de forma ascendente listas de
   * elementos comparables.
   */
  object ParteII{

    /**
     * Completa la implementación de la type class
     */
    trait Comparable[T] {
      // Operaciones primitivas
    
      // lower than zero if t1 < t2, 0 if t1 == t2, 
      // greater than zero if t1 > t2
      def compare(t1: T, t2: T): Int

      // Operaciones derivadas
      def greaterThan(t1:T, t2:T): Boolean =
        compare(t1,t2) > 0 

      def equalThan(t1:T, t2:T): Boolean =
        compare(t1,t2) == 0

      def lowerThan(t1:T, t2:T): Boolean =
        compare(t1,t2) < 0
    }

    /**
     * Facilidades sintácticas
     */
    object Comparable{
      object Syntax{
        implicit class ComparableSyntax[T](t1: T)(
          implicit C: Comparable[T]){
          def compare(t2: T): Int = 
            C.compare(t1,t2)

          def greaterThan(t2: T): Boolean =
            C.greaterThan(t1,t2)

          def equalThan(t2: T): Boolean =
            C.equalThan(t1,t2)

          def lowerThan(t2: T): Boolean =
            C.lowerThan(t1,t2)
        }
      }
    }

    /**
     * Implementa la función genérica.
     */
    import Comparable.Syntax._

    def sortAscending[T: Comparable](l:List[T]): List[T] =
      // l.sortWith( (t1: T,t2: T) => t1.lowerThan(t2) )
      // l.sortWith( (t1,t2) => t1.lowerThan(t2) )
      // l.sortWith( (t1,t2) => t1 lowerThan t2 )
      l.sortWith( _ lowerThan _)

      // l.sortWith( C.lowerThan )
      // l.sortWith( (t1,t2) => C.lowerThan(t1,t2) )

  }


  /**
   * Parte III.
   *
   * Modularización de las funciones monolíticas de ordenación para los 
   * tipos Int, Char y String.
   */
  object ParteIII{
    import ParteII._
    
    /**
     * Implementa las instancias de la type class para los tipos Int, Char y String
     */
    implicit object IntComparator extends Comparable[Int] {
      def compare(i1: Int, i2: Int): Int = i1-i2
    }
    
    implicit object CharComparator extends Comparable[Char] {
      def compare(c1: Char, c2: Char): Int = 
        c1.toInt - c2.toInt
    }
    
    implicit object StringComparator extends Comparable[String] {
      def compare(i1: String, i2: String): Int = 
        if (i1 == i2) 0
        else if (i1 < i2) -1
        else 1
    }

    /**
     * Modulariza las funciones monolíticas de la Parte I.
     */
    def sortAscendingChar(l: List[Char]): List[Char] =
      sortAscending[Char](l)(CharComparator)

    def sortAscendingInt(l: List[Int]): List[Int] = 
      sortAscending(l)

    def sortAscendingString(l: List[String]): List[String] =
      sortAscending(l)

  }
}
