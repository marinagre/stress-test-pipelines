name := "stress-test"

version := "0.1"

scalaVersion := "2.12.5"

libraryDependencies ++= {
  Seq(
    "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.3.0" intransitive(),
    "io.gatling" % "gatling-test-framework" % "2.3.0",
    "io.argonaut" %% "argonaut" % "6.2",
    "org.scalacheck" % "scalacheck_2.12" % "1.13.4",
    "com.github.alexarchambault" %% "scalacheck-shapeless_1.13" % "1.1.6",
    "com.clicktale.ai" % "gatling-kafka_2.12" % "0.1.4",
    "com.clicktale.ai" % "gatling-rabbitmq_2.12" % "0.0.5"
  )
}