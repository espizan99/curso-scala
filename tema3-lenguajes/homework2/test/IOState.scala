package org.hablapps.fpinscala
package lenguajes
package test

import org.scalatest._

import _root_.scalaz.{State, StateT, MonadState, Monad => MonadZ}

import IOS._

object PureIO extends IO[IOState] {
  implicit val monad: MonadState[IOState,IOS] = 
    StateT.stateMonad[IOS]

  def read: IOState[String] =
    for {
      s <- monad.get
      _ <- monad.put(s.copy(reads = s.reads.tail))
    } yield s.reads.head

  def write(msg: String): IOState[Unit] =
    for {
      s <- monad.get
      _ <- monad.put(s.copy(writes = msg :: s.writes))
    } yield ()
}
  