import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._

name := "scalajs-playwright-facade"

version := "0.11"

scalaVersion := "2.13.4"

organization := "com.lifeway.consumersolutions"

resolvers += "Artifactory" at "https://artifactory.prod.lifeway.com/artifactory/contentplatform/"

credentials += Credentials(
    "Artifactory Realm",
    "artifactory.prod.lifeway.com",
    sys.env.getOrElse("ARTIFACTORY_LW_USER", "bad user"),
    sys.env.getOrElse("ARTIFACTORY_LW_KEY", "bad key")
)

publishMavenStyle := true

publishTo := {
    if (version.value.trim.endsWith("SNAPSHOT"))
        Some(
            "Artifactory Realm" at
              "https://artifactory.prod.lifeway.com/artifactory/contentplatform;build.timestamp=" +
                new java.util.Date().getTime
        )
    else
        Some("Artifactory Realm" at "https://artifactory.prod.lifeway.com/artifactory/contentplatform")
}

pomExtra := {
  <scm>
    <url>git@github.com:LifewayIT/scalajs-playwright-facade.git</url>
    <connection>scm:git:git@github.com:LifewayIT/scalajs-playwright-facade.git</connection>
  </scm>
    <developers>
      <developer>
        <id>lifeway</id>
        <name>LifeWay Christian Resources</name>
        <url>https://www.lifeway.com</url>
      </developer>
    </developers>
}
pomIncludeRepository := { _ =>
  false
}

addCommandAlias("build", "; reload; clean; compile; reload")

scalaVersion := "2.13.4"
licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))
scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) }
libraryDependencies ++= Seq("org.scala-js"      %%% "scalajs-dom" % "1.1.0")
npmDependencies in Compile ++= Seq("playwright" -> "1.8.0")
enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)

releaseTagName := s"${(version in ThisBuild).value}"
releaseCrossBuild := true
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  releaseStepCommand("build"),
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  setNextVersion,
  commitNextVersion,
  pushChanges
)
