name := "AD-POS-WebUI"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.2"

resolvers ++= Seq(
  "Spray repository" at "http://repo.spray.io",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Scaladin Snapshots" at "http://henrikerola.github.io/repository/snapshots/"
)

libraryDependencies ++= Seq(
  "com.vaadin" % "vaadin-server" % "7.5.6",
  "com.vaadin" % "vaadin-client-compiled" % "7.5.6",
  "com.vaadin" % "vaadin-themes" % "7.5.6",
  "vaadin.scala" %% "scaladin" % "3.1-SNAPSHOT",
  "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
)
