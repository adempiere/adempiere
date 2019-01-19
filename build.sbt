/**
  * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU Affero General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Affero General Public License for more details.
  * You should have received a copy of the GNU Affero General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
  * Created by victor.perez@e-evolution.com , www.e-evolution.com
  */

name := "org.adempiere.jetty"

lazy val commonSettings = Seq(
  organization := "org.adempiere.net",
  version := "3.9.0-SNAPSHOT",
  scalaVersion := "2.12.8"
)

scalaVersion := "2.12.8"
resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

fork := true
val adempiereProperties = "-DPropertyFile=/Users/e-Evolution/AdempierePG.properties"
//scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation", "-encoding" , "utf8")
javaOptions in Test := Seq (adempiereProperties)

libraryDependencies ++= Seq(
  "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided",
  "com.typesafe" % "config" % "1.2.0",
  "org.scala-lang" % "scala-reflect" % "2.12.8",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)


//Documentation here ~compile https://github.com/earldouglas/xsbt-web-plugin/blob/master/docs/2.0.md
//execute with sbt ~jetty:start
javaOptions in Jetty ++= Seq(
  adempiereProperties,
  "-Xdebug",
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
)

assemblyJarName in assembly := "AdempiereTestSuite.jar"
test in assembly := {}

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = true, includeDependency =false)

val sourceDirectoryTest = "org.adempiere.test"
val sourceAdempiere = "/Users/e-Evolution/Develop/ADempiere/391"

//javaSource in Compile := baseDirectory.value  / sourceDirectoryTest / "src" / "main" / "java"
//javaSource in Test := baseDirectory.value  / sourceDirectoryTest / "src" / "test" / "java"

scalaSource in Compile := baseDirectory.value / sourceDirectoryTest / "src" / "main" / "scala"
scalaSource in Test := baseDirectory.value / sourceDirectoryTest / "src" / "test" / "scala"

unmanagedClasspath in Compile += file(sourceAdempiere + "/bin")
unmanagedClasspath in Compile += file(sourceAdempiere + "/zkwebui/WEB-INF/classes")
unmanagedClasspath in Compile += file(sourceAdempiere + "/target/scala-2.12/classes")
unmanagedClasspath in Compile += file(sourceAdempiere + "/target/scala-2.12/test-classes")

unmanagedJars in Compile ++= (file(sourceAdempiere + "/zkwebui/WEB-INF/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/tools/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/packages") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/zkpackages") * "*.jar").classpath

testOptions in Test += Tests.Argument("-oD")

enablePlugins(JettyPlugin)
enablePlugins(WebappPlugin)
containerLibs in Jetty := Seq("org.eclipse.jetty" % "jetty-runner" % "9.2.1.v20140609" intransitive())
containerMain in Jetty := "org.eclipse.jetty.runner.Runner"
//containerForkOptions := new ForkOptions(runJVMOptions = Seq("-Dh2g2=42"))
containerPort := 9090
containerShutdownOnExit := true

sourceDirectory in webappPrepare := (sourceDirectory in Compile).value / "zkwebui"
//sourceDirectory in webappPrepare := (sourceDirectory in Compile).value / "serverRoot" / "src" / "web"

//target in webappPrepare := target.value / "admin"
//inheritJarManifest := true

webappPostProcess := {
  webappDir: File =>
    IO.copyDirectory(baseDirectory.value / "bin", webappDir / "WEB-INF" / "classes")
    IO.copyDirectory(baseDirectory.value / "org.eevolution.manufacturing/src/main/java/ui/zk",webappDir / "WEB-INF" / "classes")
    IO.copyDirectory(baseDirectory.value / "org.eevolution.hr_and_payroll/src/main/java/ui/zk",webappDir / "WEB-INF" / "classes")
    IO.copyDirectory(baseDirectory.value / "zkwebui", webappDir)
    //IO.copyDirectory(baseDirectory.value / "serverRoot" / "src" / "web", webappDir)
    IO.copyDirectory(baseDirectory.value / "lib", webappDir / "WEB-INF" / "lib")
    IO.copyDirectory(baseDirectory.value / "packages", webappDir / "WEB-INF" / "lib")
    IO.copyDirectory(baseDirectory.value / "zkpackages", webappDir / "WEB-INF" / "lib")
    IO.copyDirectory(baseDirectory.value / "zkwebui/WEB-INF/classes", webappDir / "WEB-INF" / "classes")
}
