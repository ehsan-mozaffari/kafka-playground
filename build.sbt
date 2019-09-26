name := "kafka-playground"

version := "0.1"

scalaVersion := "2.12.8"

lazy val root = (project in file("."))

libraryDependencies += "com.typesafe" % "config" % "1.3.1"
libraryDependencies += "com.typesafe.akka" %% "akka-stream-kafka" % "1.0.5"