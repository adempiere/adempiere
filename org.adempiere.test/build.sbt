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

name := "org.adempiere.test"

//scalacOptions in (Compile, doc) ++= Opts.doc.title("ADempiere Test Suite")

//organization := "e-Evolution"
//version := "0.1.0-SNAPSHOT"
scalaVersion := "2.12.8"

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

fork := true
val adempiereProperties = "-DPropertyFile=/Users/e-Evolution/AdempierePG.properties"
//scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation", "-encoding" , "utf8")
javaOptions in Test := Seq (adempiereProperties)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5"
)

//Documentation here ~compilehttps://github.com/earldouglas/xsbt-web-plugin/blob/master/docs/2.0.md
//execute with sbt ~jetty:start
/*javaOptions in Jetty ++= Seq(
  adempiereProperties,
  "-Xdebug",
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
)*/

assemblyJarName in assembly := "AdempiereTestSuite.jar"
test in assembly := {}

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = true, includeDependency =false)

lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "org.eevolution",
  scalaVersion := "2.12.8"
)


//val sourceDirectoryTest = "org.adempiere.test"
val sourceAdempiere = "/Users/e-Evolution/Develop/ADempiere/391"

//System.setProperty("PropertyFile", adempiereProperties)

//javaSource in Compile := baseDirectory.value  / sourceDirectoryTest / "src" / "main" / "java"
//javaSource in Test := baseDirectory.value  / sourceDirectoryTest / "src" / "test" / "java"

//scalaSource in Compile := baseDirectory.value / sourceDirectoryTest / "src" / "main" / "scala"
//scalaSource in Test := baseDirectory.value / sourceDirectoryTest / "src" / "test" / "scala"

unmanagedClasspath in Compile += file(sourceAdempiere + "/bin")
unmanagedClasspath in Compile += file(sourceAdempiere + "/zkwebui/WEB-INF/classes")
unmanagedClasspath in Compile += file(sourceAdempiere + "/target/scala-2.12/classes")
unmanagedClasspath in Compile += file(sourceAdempiere + "/target/scala-2.12/test-classes")

//unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar"))

unmanagedJars in Compile ++= (file(sourceAdempiere + "/zkwebui/WEB-INF/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/tools/lib") * "*.jar").classpath
unmanagedJars in Compile ++= (file(sourceAdempiere + "/packages") * "*.jar").classpath

//-oD to show test duration
testOptions in Test += Tests.Argument("-oD")

lazy val adempiereTestSuite = (project in file(".")).
  settings(commonSettings: _*).
  settings(
  )

