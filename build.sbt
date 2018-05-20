import scala.sys.process.Process
import sbt.complete.DefaultParsers._

lazy val akkaHttpVersion = "10.1.1"
lazy val akkaVersion    = "2.5.12"
lazy val scalaTestVersion = "3.0.1"
lazy val slickVersion = "3.2.3"
lazy val slf4jVersion = "1.6.4"

lazy val updateNpm = taskKey[Unit]("Update npm")
lazy val npmTask = inputKey[Unit]("Run npm with arguments")

lazy val commonSettings = Seq(
    organization := "it.ingegnati",
    name := "jubilant-eureka",
    version := "0.0.2-SNAPSHOT",
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
       "com.typesafe.akka"   %% "akka-http"            % akkaHttpVersion,
       "com.typesafe.akka"   %% "akka-http-spray-json" % akkaHttpVersion,
       "com.typesafe.akka"   %% "akka-http-xml"        % akkaHttpVersion,
       "com.typesafe.akka"   %% "akka-stream"          % akkaVersion,

       "com.typesafe.akka"   %% "akka-http-testkit"    % akkaHttpVersion   % Test,
       "com.typesafe.akka"   %% "akka-testkit"         % akkaVersion       % Test,
       "com.typesafe.akka"   %% "akka-stream-testkit"  % akkaVersion       % Test,
       "org.scalatest"       %% "scalatest"            % scalaTestVersion  % Test,

       "com.typesafe.slick"  %% "slick"                % slickVersion,
       "org.slf4j"           % "slf4j-nop"             % slf4jVersion,
       "com.h2database"      % "h2"                    % "1.4.192"
    )
  )

lazy val ui = (project in file("ui"))
  .settings(
    commonSettings,
    // other settings
  )

