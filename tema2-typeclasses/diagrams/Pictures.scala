package org.hablapps.fpinscala.typeclasses
package diagrams

object Pictures {
  import StyleSheets._, Shapes._, Drawings._

  // Language
  trait Picture[P] {
    def place(style: StyleSheet, shape: Shape): P
    def above(top: P, bottom: P): P
    def beside(left: P, right: P): P
  }

  // Instances
  object Picture {
    implicit object drawPicture extends DrawPicture
    trait DrawPicture extends Picture[Drawing] {
      def place(ss: StyleSheet, s: Shape): Drawing = drawShape(ss, s)
      def above(top: Drawing, bottom: Drawing): Drawing = top above bottom
      def beside(left: Drawing, right: Drawing): Drawing = left beside right
    }
  }

  // Syntax
  implicit class PictureOps[P](p: P)(implicit P: Picture[P]) {
    def above(other: P): P =
      P.above(p, other)
    def beside(other: P): P =
      P.beside(p, other)
    def *(other: P): P = beside(other)
  }

  // Programs
  def body[P](implicit P: Picture[P]): P =
    P.place(StrokeWidth(0.1) :: FillColor(Bisque) :: Nil, Circle(3))        above
    P.place(StrokeWidth(0)   :: FillColor(Red)    :: Nil, Rectangle(10, 1)) above
    P.place(StrokeWidth(0)   :: FillColor(Red)    :: Nil, Triangle(10))     above
   (P.place(StrokeWidth(0)   :: FillColor(Blue)   :: Nil, Rectangle(1, 5))  beside
    P.place(StrokeWidth(0)   :: FillColor(Alpha)  :: Nil, Rectangle(2, 5))  beside
    P.place(StrokeWidth(0)   :: FillColor(Blue)   :: Nil, Rectangle(1, 5))) above
   (P.place(StrokeWidth(0)   :: FillColor(Blue)   :: Nil, Rectangle(2, 1))  beside
    P.place(StrokeWidth(0)   :: FillColor(Alpha)  :: Nil, Rectangle(2, 1))  beside
    P.place(StrokeWidth(0)   :: FillColor(Blue)   :: Nil, Rectangle(2, 1)))

  def slideExample[P](implicit P: Picture[P]): P =
    P.place(FillColor(Yellow) :: Nil, Circle(4))        above
    P.place(FillColor(Blue)   :: Nil, Triangle(10))     beside
    P.place(FillColor(Red)    :: Nil, Rectangle(20, 7))

}
