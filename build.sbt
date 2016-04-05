/** ****************************************************************************
  * Product: Adempiere ERP & CRM Smart Business Solution                       *
  * This program is free software; you can redistribute it and/or modify it    *
  * under the terms version 2 of the GNU General Public License as published   *
  * by the Free Software Foundation. This program is distributed in the hope   *
  * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
  * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
  * See the GNU General Public License for more details.                       *
  * You should have received a copy of the GNU General Public License along    *
  * with this program; if not, write to the Free Software Foundation, Inc.,    *
  * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
  * For the text or an alternative of this public license, you may reach us    *
  * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
  * Contributor(s): Victor Perez www.e-evolution.com                           *
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>              *
  * ****************************************************************************/

name := "adempiereTestSuite"

organization := "e-Evolution"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.7"

fork := true
val adempiereProperties = "-DPropertyFile=/Users/e-Evolution/AdempiereTest.properties"
//scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation", "-encoding" , "utf8")
javaOptions in Test := Seq (adempiereProperties)


libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided"
libraryDependencies ++= Seq(
  "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided",
  "com.typesafe" % "config" % "1.2.0",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "provided"
)
javaOptions in Jetty ++= Seq(
  adempiereProperties,
  "-Xdebug",
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
)

assemblyJarName in assembly := "AdempiereTestSuite.jar"
test in assembly := {}



assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = true, includeDependency =false)

lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "org.eevolution",
  scalaVersion := "2.11.7"
)


val sourceDirectoryTest = "org.adempiere.test"
val sourceAdempiere = "./"

//System.setProperty("PropertyFile", adempiereProperties)

javaSource in Compile := baseDirectory.value / sourceDirectoryTest / "src" / "main" / "java"
javaSource in Test := baseDirectory.value / sourceDirectoryTest / "src" / "test" / "java"

scalaSource in Compile := baseDirectory.value / sourceDirectoryTest / "src" / "main" / "scala"
scalaSource in Test := baseDirectory.value / sourceDirectoryTest / "src" / "test" / "scala"

unmanagedClasspath in Compile += file(sourceAdempiere + "/bin")
unmanagedClasspath in Compile += file(sourceAdempiere + "/zkwebui/WEB-INF/classes")
unmanagedClasspath in Compile += file(sourceAdempiere + "/target/scala-2.11/classes")
unmanagedClasspath in Compile += file(sourceAdempiere + "/target/scala-2.11/test-classes")

//unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar"))

unmanagedJars in Compile ++= (file(sourceAdempiere + "/zkwebui/WEB-INF/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/tools/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/packages") * "*.jar").classpath

//-oD to show test duration
testOptions in Test += Tests.Argument("-oD")

lazy val adempiereTestSuite = (project in file(".")).
  settings(commonSettings: _*).
  settings(
  )


enablePlugins(JettyPlugin)
enablePlugins(WebappPlugin)
containerLibs in Jetty := Seq("org.eclipse.jetty" % "jetty-runner" % "9.2.1.v20140609" intransitive())

containerMain in Jetty := "org.eclipse.jetty.runner.Runner"

containerPort := 9090
containerShutdownOnExit := true

sourceDirectory in webappPrepare := (sourceDirectory in Compile).value / "../../zkwebui"


webappPostProcess := {
  webappDir =>
    def listFiles(level: Int)(f: File): Unit = {
      val indent = ((1 until level) map { _ => "  " }).mkString
      if (f.isDirectory) {
        streams.value.log.info(indent + f.getName + "/")
        f.listFiles foreach { listFiles(level + 1) }
      } else streams.value.log.info(indent + f.getName)
    }
    listFiles(1)(webappDir)
}

webappPostProcess := {
  webappDir: File =>
    IO.copyDirectory(baseDirectory.value / "packages", webappDir / "WEB-INF" / "lib")
}


