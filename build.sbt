import Dependencies._

ThisBuild / scalaVersion     := "2.13.2"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "dev.rocky"

lazy val root = (project in file("."))
  .settings(
    name := "trips",
    libraryDependencies += scalaTest % Test
  )
