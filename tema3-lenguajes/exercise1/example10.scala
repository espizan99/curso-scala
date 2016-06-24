package org.hablapps.fpinscala
package lenguajes

// What about functions defined over a type class? Provided that the components of 
// the type class are pure, the resulting expression will be pure as well. 

object Example10{
  import typeclasses.TypeClasses.Monoide

  // pure function: when passed a "pure" monoid, no side-effect will be observed
  def reduce[T](l: List[T])(implicit T: Monoide[T]) =
    l.foldLeft(T.zero)(T.append(_,_))

  // Impure monoid
  object ImpureIntMonoid extends Monoide[Int]{
    val zero = {println(s"returning zero"); 0}
    def append(x1: Int, x2: Int) = x1+x2
  }

  // Non-referentially transparent expression, since the arguments are not pure
  val i1: Int = reduce(List(1,2,3))(ImpureIntMonoid)

  // Referentially transparent expression
  import typeclasses.TypeClasses.intMonoid
  
  val i2: Int = reduce(List(1,2,3))(intMonoid)

}

