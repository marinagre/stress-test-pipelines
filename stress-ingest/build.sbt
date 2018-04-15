name := "stress-ingest"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= {
  val akkaV = "2.4.1"
  val sprayV = "1.3.4"
  Seq(
    "io.spray" %% "spray-can" % sprayV,
    "io.spray" %% "spray-routing-shapeless23" % sprayV,
    "io.spray" %% "spray-json" % sprayV,
    "io.spray" %% "spray-caching" % sprayV,
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-slf4j" % akkaV,
    "com.github.pureconfig" %% "pureconfig" % "0.9.0",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.8.0"
  )
}