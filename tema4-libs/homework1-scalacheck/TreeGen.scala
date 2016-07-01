package org.hablapps.fpinscala.scalacheck

import org.scalacheck._, Gen._
import scalaz.Monoid
import scalaz.syntax.monoid._

object TreeGen {

  // Tree[A]
  sealed trait Tree[A]
  case class Node[A](children: List[Tree[A]], value: A) extends Tree[A]
  case class Empty[A]() extends Tree[A]

  // Instancia de monoide
  def treeMonoid[A: Monoid]: Monoid[Tree[A]] = new Monoid[Tree[A]] {
    val zero: Tree[A] = Empty()
    def append(t1: Tree[A], t2: => Tree[A]): Tree[A] = (t1, t2) match {
      case (Empty(), node) => node
      case (node, Empty()) => node
      case (Node(c1,a1), Node(c2,a2)) => Node(c1 ++ c2, a1 |+| a2)
    }
  }

  // Generador de `Empty`s
  def emptyGen[A]: Gen[Tree[A]] =
    const(Empty[A]())

  // Generador de `Node`s dados la profundidad y el grado de los nodos máximo.
  def nodeGen[A](depth: Int, degree: Int)(implicit ga: Gen[A]): Gen[Tree[A]] =
    if (depth <= 0) fail
    else for {
      ts <- listOfN(degree, treeGen[A](depth-1, degree)) 
      a <- ga
    } yield Node(ts, a)

  // Generador de `Tree`s dados la profundidad y el grado de los nodos máximo. 
  def treeGen[A](depth: Int, degree: Int)(implicit ga: Gen[A]): Gen[Tree[A]] =
    if (depth <= 0) emptyGen[A]
    else oneOf(emptyGen[A], nodeGen(depth, degree))

  // Generador de `Tree`s que va cambiando la profundidad y el grado de los
  // nodos de manera automática
  def treeGen[A](implicit ga: Gen[A]): Gen[Tree[A]] =
    sized { n =>
      val depth = n % 6
      val degree = (n % 3) + 1
      treeGen(depth, degree)
    }

}
