package org.hablapps.fpinscala.typeclasses
package diagrams

object TextGraphs {
  import Drawings._, Shapes._, StyleSheets._, Pictures._

  def innerSpace[P](implicit P: Picture[P]): P =
    P.place(FillColor(Alpha) :: Nil, Rectangle(2, 10))

  def a[P](implicit P: Picture[P]): P =
    P.place(FillColor(Alpha) :: Nil, Rectangle(2, 2))  beside
    P.place(FillColor(Blue)  :: Nil, Rectangle(5, 2))  beside
    P.place(FillColor(Alpha) :: Nil, Rectangle(2, 2))  above
   (P.place(FillColor(Blue)  :: Nil, Rectangle(2, 8))  beside
    P.place(FillColor(Blue)  :: Nil, Rectangle(5, 2))  beside
    P.place(FillColor(Blue)  :: Nil, Rectangle(2, 8)))

  def b[P](implicit P: Picture[P]): P =
    P.place(FillColor(Blue)  :: Nil, Rectangle(7,  2))  beside
    P.place(FillColor(Alpha) :: Nil, Rectangle(2,  2))  above
   (P.place(FillColor(Blue)  :: Nil, Rectangle(2,  2))  beside
    P.place(FillColor(Alpha) :: Nil, Rectangle(3,  2))  beside
    P.place(FillColor(Blue)  :: Nil, Rectangle(2,  2))  beside
    P.place(FillColor(Alpha) :: Nil, Rectangle(2,  2))) above
    P.place(FillColor(Blue)  :: Nil, Rectangle(9,  2))  above
   (P.place(FillColor(Blue)  :: Nil, Rectangle(2,  2))  beside
    P.place(FillColor(Alpha) :: Nil, Rectangle(5,  2))  beside
    P.place(FillColor(Blue)  :: Nil, Rectangle(2,  2))) above
    P.place(FillColor(Blue)  :: Nil, Rectangle(9,  2))

  def c[P](implicit P: Picture[P]): P =
    P.place(FillColor(Blue)  :: Nil, Rectangle(2, 10)) beside
   (P.place(FillColor(Blue)  :: Nil, Rectangle(7,  2)) above
    P.place(FillColor(Alpha) :: Nil, Rectangle(7,  6)) above
    P.place(FillColor(Blue)  :: Nil, Rectangle(7,  2)))

  def d[P](implicit P: Picture[P]): P =
    P.place(FillColor(Blue)  :: Nil, Rectangle(2, 10))  beside
   (P.place(FillColor(Blue)  :: Nil, Rectangle(5,  2))  above
    P.place(FillColor(Alpha) :: Nil, Rectangle(5,  6))  above
    P.place(FillColor(Blue)  :: Nil, Rectangle(5,  2))) beside
    P.place(FillColor(Blue)  :: Nil, Rectangle(2,  6))

  def e[P](implicit P: Picture[P]): P =
    P.place(FillColor(Blue)  :: Nil, Rectangle(2, 10))  beside
   (P.place(FillColor(Blue)  :: Nil, Rectangle(3,  2))  above
    P.place(FillColor(Alpha) :: Nil, Rectangle(3,  2))  above
    P.place(FillColor(Blue)  :: Nil, Rectangle(3,  2))  above
    P.place(FillColor(Alpha) :: Nil, Rectangle(3,  2))  above
    P.place(FillColor(Blue)  :: Nil, Rectangle(3,  2))) beside
   (P.place(FillColor(Blue)  :: Nil, Rectangle(4,  2))  above
    P.place(FillColor(Alpha) :: Nil, Rectangle(4,  6))  above
    P.place(FillColor(Blue)  :: Nil, Rectangle(4,  2)))

  def h[P](implicit P: Picture[P]): P =
    P.place(FillColor(Blue) :: Nil, Rectangle(2, 10)) beside
    P.place(FillColor(Blue) :: Nil, Rectangle(5,  2)) beside
    P.place(FillColor(Blue) :: Nil, Rectangle(2, 10))

  def i[P](implicit P: Picture[P]): P =
    P.place(FillColor(Blue) :: Nil, Rectangle(9, 2)) above
    P.place(FillColor(Blue) :: Nil, Rectangle(2, 6)) above
    P.place(FillColor(Blue) :: Nil, Rectangle(9, 2))

  def k[P](implicit P: Picture[P]): P =
    P.place(FillColor(Blue)  :: Nil, Rectangle(2, 4)) beside
    P.place(FillColor(Alpha) :: Nil, Rectangle(3, 4)) beside
    P.place(FillColor(Blue)  :: Nil, Rectangle(2, 4)) beside
    P.place(FillColor(Alpha) :: Nil, Rectangle(2, 4)) above
    P.place(FillColor(Blue)  :: Nil, Rectangle(9, 2)) above
   (P.place(FillColor(Blue)  :: Nil, Rectangle(2, 4)) beside
    P.place(FillColor(Alpha) :: Nil, Rectangle(5, 4)) beside
    P.place(FillColor(Blue)  :: Nil, Rectangle(2, 4)))

  def l[P](implicit P: Picture[P]): P =
    P.place(FillColor(Blue)  :: Nil, Rectangle(2, 8)) beside
    P.place(FillColor(Alpha) :: Nil, Rectangle(7, 8)) above
    P.place(FillColor(Blue)  :: Nil, Rectangle(9, 2))

  def n[P](implicit P: Picture[P]): P =
    P.place(FillColor(Blue)  :: Nil, Rectangle(2, 10))  beside
   (P.place(FillColor(Blue)  :: Nil, Rectangle(5,  2))  above
    P.place(FillColor(Alpha) :: Nil, Rectangle(5,  8))) beside
   (P.place(FillColor(Alpha) :: Nil, Rectangle(2,  2))  above
    P.place(FillColor(Blue)  :: Nil, Rectangle(2,  8)))

  def p[P](implicit P: Picture[P]): P =
    P.place(FillColor(Blue)  :: Nil, Rectangle(9, 2))  above
   (P.place(FillColor(Blue)  :: Nil, Rectangle(2, 3))  beside
    P.place(FillColor(Alpha) :: Nil, Rectangle(5, 3))  beside
    P.place(FillColor(Blue)  :: Nil, Rectangle(2, 3))) above
    P.place(FillColor(Blue)  :: Nil, Rectangle(9, 2))  above
   (P.place(FillColor(Blue)  :: Nil, Rectangle(2, 3))  beside
    P.place(FillColor(Alpha) :: Nil, Rectangle(7, 3)))

  def whiteSpace[P](implicit P: Picture[P]): P =
    P.place(FillColor(Alpha) :: Nil, Rectangle(9, 10))

  def notFound[P](implicit P: Picture[P]): P =
    P.place(FillColor(Blue) :: Nil, Rectangle(9, 10))

  def abc[P](implicit P: Picture[P]): Map[Char, P] = Map(
    'a' -> a,
    'b' -> b,
    'c' -> c,
    'd' -> d,
    'e' -> e,
    'h' -> h,
    'i' -> i,
    'k' -> k,
    'l' -> l,
    'n' -> n,
    'p' -> p,
    ' ' -> whiteSpace)

  def write[P](s: String)(implicit P: Picture[P]): P =
    s map { c => abc.getOrElse(c.toLower, notFound) } reduce[P] {
      case (left, right) => left * innerSpace * right
    }

}
