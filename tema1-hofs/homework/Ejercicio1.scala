package org.hablapps.fpinscala.hofs.homework

object Exercise1 {

  object PartI {
    // Implementa `filter` para el tipo `Option`
    def filter[A](o: Option[A])(p: A => Boolean): Option[A] = o match {
      case None => None;
      case Some(x) => if(p(x)) Some(x) else None;

    }

    // Implementa `map` para el tipo `Option`
    def map[A, B](o: Option[A])(f: A => B): Option[B] = o match {
      case Some(a) => Some(f(a))
      case None => None
    }

  }

  object PartII {
    // Implementa `fold` para el tipo `Option`
    def fold[A, B](o: Option[A])(b: B)(f: A => B): B = o match {
      case Some(a) => f(a)
      case None => b
    }

    // Implementa `filter` en términos de `fold`
    def filter[A](o: Option[A])(p: A => Boolean): Option[A] =
      fold[A, Option[A]](o)(None)(a =>
        if (p(a))
          Some(a)
        else
          None)

    // Implementa `map` en términos de `fold`
    def map[A, B](o: Option[A])(f: A => B): Option[B] =
      fold[A, Option[B]](o)(None)(a => Some(f(a)))

  }
}
