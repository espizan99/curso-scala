name := "funcourseinscala"

scalaVersion := "2.11.7"

organization := "org.hablapps"

unmanagedSourceDirectories in Compile ++= Seq(
  baseDirectory.value / "tema0-scalaintro",
  baseDirectory.value / "tema1-hofs",
  baseDirectory.value / "tema2-typeclasses",
  baseDirectory.value / "tema3-lenguajes",
  baseDirectory.value / "tema4-libs",
  baseDirectory.value / "tema4-spark"
)

unmanagedSourceDirectories in Test ++= Seq(
  baseDirectory.value / "tema0-scalaintro" / "test",
  baseDirectory.value / "tema1-hofs" / "test",
  baseDirectory.value / "tema2-typeclasses" / "test",
  baseDirectory.value / "tema3-lenguajes" / "exercise2" / "test", 
  baseDirectory.value / "tema3-lenguajes" / "homework1" / "test",
  baseDirectory.value / "tema3-lenguajes" / "homework2" / "test",
  baseDirectory.value / "tema4-libs"
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
  "org.apache.spark" %% "spark-core" % "1.6.1",
  "org.scalatest" %% "scalatest" % "2.2.6"
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
  | import org.hablapps.fpinscala._
  | import typeclasses._, diagrams._
  | import lenguajes._
  |""".stripMargin
