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
  version := "3.9.4-SNAPSHOT",
  scalaVersion := "2.13.6"
)

scalaVersion := "2.13.6"
resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"

fork := true
val adempiereProperties =
  "-DPropertyFile=/Users/e-Evolution/AdempierePG.properties"
/*scalacOptions ++= Seq(
  "-feature",
  "-unchecked",
  "-deprecation",
  "-encoding",
  "utf8",
  "-Ytasty-reader"
)*/

Test / javaOptions := Seq(adempiereProperties)

libraryDependencies ++= Seq(
  "javax.servlet" % "javax.servlet-api" % "4.0.1" % "provided",
  "org.scala-lang" % "scala-reflect" % "2.13.6"
)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.9"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % Test
libraryDependencies += "org.scalatest" %% "scalatest-featurespec" % "3.2.9" % Test
Test / logBuffered := false

//Documentation here ~compile https://github.com/earldouglas/xsbt-web-plugin/blob/master/docs/2.0.md
//execute with sbt ~jetty:start
Jetty / javaOptions ++= Seq(
  adempiereProperties,
  "--add-exports=java.desktop/sun.swing=ALL-UNNAMED",
  "--add-exports=java.desktop/sun.awt=ALL-UNNAMED",
  "--add-exports=java.base/sun.security.provider=ALL-UNNAMED",
  "--add-exports=java.naming/com.sun.jndi.ldap=ALL-UNNAMED",
  "-Xdebug",
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
)

assembly / assemblyJarName := "AdempiereTestSuite.jar"
assembly / test := {}

assembly / assemblyOption := (assembly / assemblyOption).value
  .copy(includeScala = true, includeDependency = false)

val sourceDirectoryTest = "org.adempiere.test"
val sourceAdempiere = "/Users/e-Evolution/Develop/ADempiere/developEE"

//Compile / javaSource := baseDirectory.value / sourceDirectoryTest / "src" / "main" / "java"
//Test / javaSource := baseDirectory.value / sourceDirectoryTest / "src" / "test" / "java"

//Compile / scalaSource := baseDirectory.value / sourceDirectoryTest / "src" / "main" / "scala"
//Test scalaSource := baseDirectory.value / sourceDirectoryTest / "src" / "test" / "scala"

Compile / unmanagedClasspath += file(sourceAdempiere + "/bin")
Compile / unmanagedClasspath += file(
  sourceAdempiere + "/zkwebui/WEB-INF/classes"
)
Compile / unmanagedClasspath += file(
  sourceAdempiere + "/target/scala-2.13/classes"
)
Compile / unmanagedClasspath += file(
  sourceAdempiere + "/target/scala-2.13/test-classes"
)

Compile / unmanagedJars ++= (file(
  sourceAdempiere + "/zkwebui/WEB-INF/lib"
) * "*.jar").classpath
Compile / unmanagedJars ++= (file(
  sourceAdempiere + "/tools/lib"
) * "*.jar").classpath
Compile / unmanagedJars ++= (file(
  sourceAdempiere + "/JasperReportsTools/lib"
) * "*.jar").classpath
Compile / unmanagedJars ++= (file(
  sourceAdempiere + "/lib"
) * "*.jar").classpath
Compile / unmanagedJars ++= (file(
  sourceAdempiere + "/packages"
) * "*.jar").classpath
Compile / unmanagedJars ++= (file(
  sourceAdempiere + "/zkpackages"
) * "*.jar").classpath

Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oD")
Test / logBuffered := false

enablePlugins(JettyPlugin)
enablePlugins(WebappPlugin)

Jetty / containerLibs := Seq(
  "org.eclipse.jetty" % "jetty-runner" % "10.0.6" intransitive ()
)
Jetty / containerMain := "org.eclipse.jetty.runner.Runner"
//containerForkOptions := new ForkOptions(runJVMOptions = Seq("-Dh2g2=42"))
containerPort := 9090
containerShutdownOnExit := true

webappPrepare / sourceDirectory := (Compile / sourceDirectory).value / "zkwebui"
//webappPrepare / sourceDirectory := (sourceDirectory in Compile).value / "serverRoot" / "src" / "web"

//webappPrepare / target := target.value / "admin"
//inheritJarManifest := true

webappPostProcess := { webappDir: File =>
  IO.copyDirectory(
    baseDirectory.value / "bin",
    webappDir / "WEB-INF" / "classes"
  )
  IO.copyDirectory(
    baseDirectory.value / "org.eevolution.manufacturing/src/main/java/ui/zk",
    webappDir / "WEB-INF" / "classes"
  )
  IO.copyDirectory(
    baseDirectory.value / "org.eevolution.hr_and_payroll/src/main/java/ui/zk",
    webappDir / "WEB-INF" / "classes"
  )
  IO.copyDirectory(baseDirectory.value / "zkwebui", webappDir)
  //IO.copyDirectory(baseDirectory.value / "serverRoot" / "src" / "web", webappDir)
  IO.copyDirectory(baseDirectory.value / "lib", webappDir / "WEB-INF" / "lib")
  IO.copyDirectory(
    baseDirectory.value / "packages",
    webappDir / "WEB-INF" / "lib"
  )
  IO.copyDirectory(
    baseDirectory.value / "zkpackages",
    webappDir / "WEB-INF" / "lib"
  )
  IO.copyDirectory(
    baseDirectory.value / "zkwebui/WEB-INF/classes",
    webappDir / "WEB-INF" / "classes"
  )
}
