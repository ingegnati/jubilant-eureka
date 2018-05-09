import scala.sys.process.Process
import sbt.complete.DefaultParsers._

val Http4sVersion = "0.18.10"
val Specs2Version = "4.1.0"
val LogbackVersion = "1.2.3"

lazy val updateNpm = taskKey[Unit]("Update npm")
lazy val npmTask = inputKey[Unit]("Run npm with arguments")

lazy val commonSettings = Seq(
    organization := "it.ingegnati",
    name := "jubilant-eureka",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.6"
)

lazy val rootSettings = Seq (
    updateNpm := {
        println("Updating npm dependencies")
        haltOnCmdResultError(Process("npm install", baseDirectory.value / "ui").!)
      },
      npmTask := {
        val taskName = spaceDelimited("<arg>").parsed.mkString(" ")
        updateNpm.value
        val localNpmCommand = "npm " + taskName
        def buildWebpack() =
          Process(localNpmCommand, baseDirectory.value / "ui").!
        println("Building with Webpack : " + taskName)
        haltOnCmdResultError(buildWebpack())
    }
)

def haltOnCmdResultError(result: Int) {
  if (result != 0) {
    throw new Exception("Build failed.")
  }
}

lazy val root = (project in file("."))
  .settings(rootSettings)
  .aggregate(backend, ui)

lazy val backend = (project in file("backend"))
  .settings(
    commonSettings,
    // other settings
     libraryDependencies ++= Seq(
      "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s"      %% "http4s-circe"        % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
      "org.specs2"      %% "specs2-core"          % Specs2Version % "test",
      "ch.qos.logback"  %  "logback-classic"     % LogbackVersion
    )
  )

lazy val ui = (project in file("ui"))
  .settings(
    commonSettings,
    // other settings
  )



