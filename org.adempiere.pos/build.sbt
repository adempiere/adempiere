import _root_.sbtassembly.AssemblyPlugin.autoImport._

name := "adempierePOS"
version := "1.0.0"


scalaVersion := "2.11.8"

fork := true
val adempiereProperties = "-DPropertyFile=/Users/e-Evolution/Desktop/Develop/Adempiere.properties"
//scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation", "-encoding" , "utf8")
javaOptions in Test := Seq (adempiereProperties)


lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "org.adempiere.pos",
  scalaVersion := "2.11.8"
)

val sourceAdempiere = "/Users/e-Evolution/Documents/Develop/ADempiere/adempiere"

unmanagedSourceDirectories in Compile <++= baseDirectory { base =>
  Seq(
    base / "src" / "main" / "java" / "base",
    base / "src" / "main" / "java" / "ui" / "swing" ,
    base / "src" / "main" / "java" / "ui" / "zk"
  )
}

//javaSource in Test := baseDirectory.value / "org.adempiere.pos"  / "src" / "test" / "java"

scalaSource in Compile := baseDirectory.value / "src" / "main" / "scala"
scalaSource in Test := baseDirectory.value / "src" / "test" / "scala"

val additionalClasses = file(sourceAdempiere + "/zkwebui/WEB-INF/classes")

unmanagedClasspath in Compile += additionalClasses
unmanagedClasspath in Runtime += additionalClasses

unmanagedJars in Compile ++= (file(sourceAdempiere + "/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/target/scala-2.11") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/zkwebui/WEB-INF/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/tools/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/packages") * "*.jar").classpath


assemblyJarName in assembly := "ADempierePOS.jar"

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = true, includeDependency = false)

lazy val adempierePOS = (project in file(".")).
  settings(commonSettings: _*).
  settings(
  )


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
  "org.scalatest" % "scalatest_2.11" % "2.2.6"
)
