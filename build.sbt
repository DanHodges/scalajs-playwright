name := "scalajs-playwright"

organization := "com.github.danhodges"

version := "0.9"

scalaVersion := "2.13.4"

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .settings(
    scalaVersion := "2.13.4",
    licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")),
    bintrayRepository := "maven",
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    libraryDependencies ++= Seq("org.scala-js"      %%% "scalajs-dom" % "1.1.0"),
    npmDependencies in Compile ++= Seq("playwright" -> "1.8.0"),
    name := "root",
    bintrayReleaseOnPublish := !isSnapshot.value
  )
