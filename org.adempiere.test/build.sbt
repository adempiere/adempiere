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

scalaVersion := "2.13.6"

resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"

fork := true
// Legacy standalone build (Scala 2.13). The supported way to run the Scala tests is the root build.sbt, see
// README.md. Paths come from the environment instead of a personal home directory.
val sourceAdempiere = sys.env
  .get("ADEMPIERE_SOURCE_DIR")
  .getOrElse(file("..").getAbsoluteFile.toPath.normalize.toString)
val adempiereProperties = "-DPropertyFile=" + sys.env
  .getOrElse(
    "ADEMPIERE_PROPERTY_FILE",
    sourceAdempiere + "/install/build/Adempiere/Adempiere.properties"
  )
//scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation", "-encoding" , "utf8")
javaOptions in Test := Seq(adempiereProperties)

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.13.6",
  "org.scalactic" %% "scalactic" % "3.2.9",
  "org.scalatest" %% "scalatest" % "3.2.9" % "test",
  "org.scalatest" %% "scalatest-freespec" % "3.2.9" % "test",
  "org.scalatest" %% "scalatest-featurespec" % "3.2.9" % "test"
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

assemblyOption in assembly := (assemblyOption in assembly).value
  .copy(includeScala = true, includeDependency = false)

lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "org.eevolution",
  scalaVersion := "2.13.1"
)

//val sourceDirectoryTest = "org.adempiere.test"

//System.setProperty("PropertyFile", adempiereProperties)

//javaSource in Compile := baseDirectory.value  / sourceDirectoryTest / "src" / "main" / "java"
//javaSource in Test := baseDirectory.value  / sourceDirectoryTest / "src" / "test" / "java"

//scalaSource in Compile := baseDirectory.value / sourceDirectoryTest / "src" / "main" / "scala"
//scalaSource in Test := baseDirectory.value / sourceDirectoryTest / "src" / "test" / "scala"

unmanagedClasspath in Compile += file(sourceAdempiere + "/bin")
unmanagedClasspath in Compile += file(
  sourceAdempiere + "/zkwebui/WEB-INF/classes"
)
unmanagedClasspath in Compile += file(
  sourceAdempiere + "/target/scala-2.13/classes"
)
unmanagedClasspath in Compile += file(
  sourceAdempiere + "/target/scala-2.13/test-classes"
)

//unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar"))

unmanagedJars in Compile ++= (file(
  sourceAdempiere + "/zkwebui/WEB-INF/lib"
) * "*.jar").classpath
unmanagedJars in Compile ++= (file(
  sourceAdempiere + "/lib"
) * "*.jar").classpath
unmanagedJars in Compile ++= (file(
  sourceAdempiere + "/tools/lib"
) * "*.jar").classpath
unmanagedJars in Compile ++= (file(
  sourceAdempiere + "/packages"
) * "*.jar").classpath

//-oD to show test duration
testOptions in Test += Tests.Argument("-oD")

lazy val adempiereTestSuite = (project in file("."))
  .settings(commonSettings: _*)
  .settings(
  )
