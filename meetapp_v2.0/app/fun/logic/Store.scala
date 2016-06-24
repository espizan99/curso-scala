package org.hablapps.meetup
package fun
package logic

import common.logic.Domain._

trait Store[F[_]]{
  def putUser(user: User): F[Int]
  def putGroup(group: Group): F[Int]
  def getGroup(gid: Int): F[Group]
  def getUser(uid: Int): F[User]
  def putJoin(join: JoinRequest): F[JoinRequest]
  def putMember(member: Member): F[Member]

  // combinators

  def doAndThen[A, B](f: F[A])(cont: A => F[B]): F[B]
  def returns[A](a: A): F[A]

  // derived combinators

  def map[A,B](f: F[A])(m: A=>B): F[B] = 
    doAndThen(f)(m andThen returns)

  def cond[X, Y](
    test: => Boolean,
    left: => F[X],
    right: => F[Y]): F[Either[X,Y]] = 
    if (test)
      map[X,Either[X,Y]](left)(Left(_))
    else 
      map[Y,Either[X,Y]](right)(Right(_))

}

object Store {

  object Syntax{
    def putUser[F[_]](user: User)(implicit S: Store[F]): F[Int] = S.putUser(user)
    def putGroup[F[_]](group: Group)(implicit S: Store[F]): F[Int] = S.putGroup(group)
    def getGroup[F[_]](gid: Int)(implicit S: Store[F]): F[Group] = S.getGroup(gid)
    def getUser[F[_]](uid: Int)(implicit S: Store[F]): F[User] = S.getUser(uid)
    def putJoin[F[_]](join: JoinRequest)(implicit S: Store[F]): F[JoinRequest] = S.putJoin(join)
    def putMember[F[_]](member: Member)(implicit S: Store[F]): F[Member] = S.putMember(member)

    def returns[F[_], A](a: A)(implicit S: Store[F]): F[A] = S.returns(a)
    
    // combinators
    implicit class StoreOps[F[_], A](f: F[A])(implicit S: Store[F]){
      def flatMap[B](cont: A => F[B]): F[B] = S.doAndThen(f)(cont)
      def map[B](m: A=>B): F[B] = S.doAndThen(f)(m andThen S.returns)
    }

    def cond[F[_], X, Y](
      test: => Boolean,
      left: => F[X],
      right: => F[Y])(implicit S: Store[F]) = S.cond(test,left,right)
  }

}
