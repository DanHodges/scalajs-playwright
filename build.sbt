name := "scalajs-playwright"

version := "0.1"

scalaVersion := "2.13.3"


lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .settings(
    scalaVersion := "2.13.3",
    name := "root",
  )
