name := "elasticsearch-getting-started"

version := "1.0.0"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  guice,
  "org.elasticsearch.client" % "elasticsearch-rest-high-level-client" % "7.5.2",
  "org.projectlombok" % "lombok" % "1.18.10" % "provided",
//  "com.fasterxml.jackson.core" % "jackson-databind" % "2.10.1"
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)