package org.hablapps.fpinscala.hofs
package diagrams

object Pictures {
  import StyleSheets._, Shapes._

  // ADT
  sealed trait Picture
  case class Place(style: StyleSheet, shape: Shape) extends Picture
  case class Above(top: Picture, bottom: Picture) extends Picture
  case class Beside(left: Picture, right: Picture) extends Picture

  // SYNTAX
  implicit class PictureOps(p: Picture) {
    def above(other: Picture): Picture =
      Above(p, other)
    def beside(other: Picture): Picture =
      Beside(p, other)
    def *(other: Picture): Picture = beside(other)
  }

  // INSTANCES
  val body: Picture =
    Place(StrokeWidth(0.1) :: FillColor(Bisque) :: Nil, Circle(3))        above
    Place(StrokeWidth(0)   :: FillColor(Red)    :: Nil, Rectangle(10, 1)) above
    Place(StrokeWidth(0)   :: FillColor(Red)    :: Nil, Triangle(10))     above
   (Place(StrokeWidth(0)   :: FillColor(Blue)   :: Nil, Rectangle(1, 5))  beside
    Place(StrokeWidth(0)   :: FillColor(Alpha)  :: Nil, Rectangle(2, 5))  beside
    Place(StrokeWidth(0)   :: FillColor(Blue)   :: Nil, Rectangle(1, 5))) above
   (Place(StrokeWidth(0)   :: FillColor(Blue)   :: Nil, Rectangle(2, 1))  beside
    Place(StrokeWidth(0)   :: FillColor(Alpha)  :: Nil, Rectangle(2, 1))  beside
    Place(StrokeWidth(0)   :: FillColor(Blue)   :: Nil, Rectangle(2, 1)))

  val slideExample: Picture =
    Place(FillColor(Yellow) :: Nil, Circle(4)) above
    Place(FillColor(Blue) :: Nil, Triangle(10)) beside
    Place(FillColor(Red) :: Nil, Rectangle(20, 7))

}