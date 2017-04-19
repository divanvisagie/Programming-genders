name := """programming-genders"""

version := "1.0"

scalaVersion := "2.12.0"

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
    "com.github.wookietreiber" %% "scala-chart" % "latest.integration",
    "org.jfree" % "jfreesvg" % "3.0",
    "zamblauskas" %% "scala-csv-parser" % "0.11.4"
)

resolvers += Resolver.bintrayRepo("zamblauskas", "maven")