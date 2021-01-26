lazy val commonSettings = Seq(
  organization := "com.dkasza",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.12.12",
  scalacOptions += "-Xsource:2.11",
  resolvers ++= Seq(
    Resolver.sonatypeRepo("snapshots"),
    Resolver.sonatypeRepo("releases")
  ),
  libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full),
  libraryDependencies += "edu.berkeley.cs" %% "chisel3" % "3.4.0",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.4",
)

lazy val main = (project in file(".")).
  settings(name := "dank-formal").
  settings(commonSettings: _*)

