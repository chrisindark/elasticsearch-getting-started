name := "elasticsearch-getting-started"

version := "1.0.0"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  guice,
  
)

lazy val root = (project in file(".")).enablePlugins(PlayJava)