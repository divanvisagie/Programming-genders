name := """programming-genders"""

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
    "com.github.wookietreiber" %% "scala-chart" % "latest.integration",
    "org.jfree" % "jfreesvg" % "3.0",
    "com.nrinaudo" %% "kantan.csv" % "0.1.18",
    "com.nrinaudo" %% "kantan.csv-generic" % "0.1.18"
)

resolvers += Resolver.bintrayRepo("zamblauskas", "maven")