package org.hablapps.fpinscala
package typeclasses

trait Value {
  def getInt: Int
  def getBool: Boolean
}

case class VInt(i: Int) extends Value {
  def getInt: Int = i
  def getBool: Boolean = ???
}

case class VBool(b: Boolean) extends Value {
  def getInt: Int = ???
  def getBool: Boolean = b
}

  