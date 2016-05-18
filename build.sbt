name := "funcourseinscala"

scalaVersion := "2.11.7"

organization := "org.hablapps"

unmanagedSourceDirectories in Compile ++= Seq(
  baseDirectory.value / "tema0-scalaintro"
)

resourceDirectory in Compile := baseDirectory.value / "resources"

resolvers ++= Seq(
  "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
  "rediscala" at "http://dl.bintray.com/etaty/maven",
  "spray repo" at "http://repo.spray.io",
  Resolver.sonatypeRepo("releases")
)

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.7.1")

libraryDependencies ++= Seq(
  "org.spire-math" %% "cats" % "0.3.0",
  "org.scalaz" %% "scalaz-core" % "7.2.0",
  "org.scalaz" %% "scalaz-scalacheck-binding" % "7.2.0",
  "com.typesafe.akka" %% "akka-actor" % "2.3.2",
  "com.etaty.rediscala" %% "rediscala" % "1.3.1",
  "io.spray" %% "spray-client" % "1.3.3",
  "io.spray" %% "spray-json" % "1.3.2",
  "org.scalatest" %% "scalatest" % "2.2.6",
  "org.scalacheck" %% "scalacheck" % "1.12.5"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  // "-Xprint:typer",
  // "-Xlog-implicit-conversions",
  "-language:postfixOps",
  "-language:higherKinds")

initialCommands in console := """
  | import org.hablapps.scalaintro._
  | import oo._
  | import funcional._
  | import azucar._
  | import implicitos._
  | import genericidad._
  |""".stripMargin
