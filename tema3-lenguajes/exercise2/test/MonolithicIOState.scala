package org.hablapps.fpinscala.lenguajes
package test

import org.scalatest._

import _root_.scalaz.{State, StateT, MonadState}

// IO Utils
case class IOS(reads: List[String], writes: List[String])

object IOS{

  type IOState[A] = State[IOS, A]

  implicit object PureIO extends io.IO[IOState] {
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

    def doAndThen[A, B](f: IOState[A])(cont: A => IOState[B]): IOState[B] = 
      monad.bind(f)(cont)
    
    def returns[A](a: A): IOState[A] =
      monad.pure(a)
  }
}
  