package org.hablapps.fpinscala.typeclasses
package diagrams

object StyleSheets {

  type StyleSheet = List[Styling]

  // ADT
  sealed trait Styling
  case class FillColor(c: Color) extends Styling
  case class StrokeColor(c: Color) extends Styling
  case class StrokeWidth(w: Double) extends Styling

  // ADT
  sealed trait Color
  case object Red extends Color
  case object Blue extends Color
  case object Green extends Color
  case object Yellow extends Color
  case object Brown extends Color
  case object Black extends Color
  case object Bisque extends Color
  case object Alpha extends Color

}