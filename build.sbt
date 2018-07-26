import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.navneetgupta",
      scalaVersion := "2.12.6",
      version      := "0.1.0"
    )),
    name := "akka-http-kafka",
    libraryDependencies ++= {
	  val akkaVersion = "2.5.13"
	  Seq(
	  	"com.navneetgupta" %% "akka-common" % "0.1.0",
		"com.typesafe.akka" %% "akka-stream-kafka" % "0.22"
	  )
	}
  )
