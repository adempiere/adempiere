/** Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
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

name := "org.adempiere.server"

lazy val commonSettings = Seq(
  organization := "org.adempiere.net",
  version := "3.9.4-SNAPSHOT",
  scalaVersion := "3.2.1"
)

scalaVersion := "3.2.1"
resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"

fork := true

val sourceDirectoryTest = "org.adempiere.test"
val sourceAdempiere = "/Users/e-Evolution/Develop/ADempiere/394"
val adempiereProperties =
  "-DPropertyFile=" + sourceAdempiere + "/install/build/Adempiere/Adempiere.properties"
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
  "javax.servlet" % "javax.servlet-api" % "4.0.1" % "provided"
  //"org.scala-lang" % "scala-reflect" % "3.0.0"
)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10"
libraryDependencies += "org.scalatest" %% "scalatest-core" % "3.2.10" % Test
libraryDependencies += "org.scalatest" %% "scalatest-featurespec" % "3.2.10" % Test
Test / logBuffered := false

assembly / assemblyJarName := "AdempiereTestSuite.jar"
assembly / test := {}

assembly / assemblyOption := (assembly / assemblyOption).value
  .copy(includeScala = true, includeDependency = false)

/** Compile / javaSource := baseDirectory.value / "serverRoot" / "src" / "main" / "server"
  * Compile / javaSource := baseDirectory.value / "serverRoot" / "src" / "main" / "servlet"
  */

Test / javaSource := baseDirectory.value / sourceDirectoryTest / "src" / "test" / "java"

Compile / scalaSource := baseDirectory.value / sourceDirectoryTest / "src" / "main" / "scala"
Test / scalaSource := baseDirectory.value / sourceDirectoryTest / "src" / "test" / "scala"

Compile / javaSource := baseDirectory.value / "org.eevolution.warehouse" / "src" / "main" / "java" / "ui" / "zk"

Compile / unmanagedClasspath += file(sourceAdempiere + "/bin")
Compile / unmanagedClasspath += file(
  sourceAdempiere + "/zkwebui/WEB-INF/classes"
)
Compile / unmanagedClasspath += file(
  sourceAdempiere + "/target/scala-3.0.2/classes"
)
Compile / unmanagedClasspath += file(
  sourceAdempiere + "/target/scala-3.0.2/test-classes"
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

enablePlugins(WebappPlugin)

// Enable to Tomcat Server
//execute with sbt ~tomcat:start
enablePlugins(TomcatPlugin)
Tomcat / containerLibs := Seq(
  "com.heroku" % "webapp-runner" % "9.0.41.0" intransitive ()
)
Tomcat / containerMain := "webapp.runner.launch.Main"
//Documentation here ~compile https://github.com/earldouglas/xsbt-web-plugin/blob/master/docs/2.0.md

Tomcat / javaOptions ++= Seq(
  adempiereProperties,
  "--add-exports=java.base/jdk.internal.misc=ALL-UNNAMED",
  "--add-exports=java.desktop/sun.swing=ALL-UNNAMED",
  "--add-exports=java.desktop/sun.awt=ALL-UNNAMED",
  "--add-exports=java.base/sun.security.provider=ALL-UNNAMED",
  "--add-exports=java.naming/com.sun.jndi.ldap=ALL-UNNAMED",
  "--add-exports=jdk.naming.dns/com.sun.jndi.dns=java.naming",
  "--add-opens=java.base/java.lang=ALL-UNNAMED",
  "-Xdebug",
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
)

Tomcat / containerArgs := Seq(
  "--context-xml",
  sourceAdempiere + "/install/build/Adempiere/tomcat/conf/context.xml",
  "--enable-naming",
  "--access-log",
  "--access-log-pattern",
  "common"
)

// Enable to Jetty Server
//execute with sbt ~jetty:start

enablePlugins(JettyPlugin)
Jetty / containerLibs := Seq(
  "org.eclipse.jetty" % "jetty-runner" % "10.0.12" intransitive ()
)
Jetty / containerMain := "org.eclipse.jetty.runner.Runner"
//containerForkOptions := new ForkOptions(runJVMOptions = Seq("-Dh2g2=42"))

Jetty / javaOptions ++= Seq(
  adempiereProperties,
  "--add-exports=java.desktop/sun.swing=ALL-UNNAMED",
  "--add-exports=java.desktop/sun.awt=ALL-UNNAMED",
  "--add-exports=java.base/sun.security.provider=ALL-UNNAMED",
  "--add-exports=java.naming/com.sun.jndi.ldap=ALL-UNNAMED",
  "-Xdebug",
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
)

Jetty / containerArgs := Seq(
  "--config",
  sourceAdempiere + "/install/build/Adempiere/jetty/jetty-ds.xml",
  "--jar",
  sourceAdempiere + "/tools/lib/HikariCP-5.0.1.jar",
  "--jar",
  sourceAdempiere + "/tools/lib/postgresql.jar"
)

containerPort := 9090
containerShutdownOnExit := true
// deployment webui
webappPrepare / sourceDirectory := (Compile / sourceDirectory).value / "zkwebui"

// Deployment Server Root
//webappPrepare / sourceDirectory := (sourceDirectory in Compile).value / "serverRoot" / "src" / "web"
//webappPrepare / target := target.value / "admin"
inheritJarManifest := true

webappPostProcess := {
  val log = streams.value.log
  webappDir: File =>
    IO.copyDirectory(
      baseDirectory.value / "bin",
      webappDir / "WEB-INF" / "classes"
    )
    IO.copyDirectory(
      baseDirectory.value / "org.eevolution.manufacturing/src/main/java/ui/zk",
      webappDir / "WEB-INF" / "classes"
    )
    IO.copyDirectory(
      baseDirectory.value / "org.eevolution.warehouse/src/main/java/ui/zk",
      webappDir / "WEB-INF" / "classes"
    )
    IO.copyDirectory(
      baseDirectory.value / "org.eevolution.hr_and_payroll/src/main/java/ui/zk",
      webappDir / "WEB-INF" / "classes"
    )
    IO.copyDirectory(baseDirectory.value / "lib", webappDir / "WEB-INF" / "lib")
    IO.copyDirectory(
      baseDirectory.value / "tools/lib/HikariCP-5.0.1.jar",
      webappDir / "WEB-INF" / "lib"
    )

    /** IO.copyDirectory(
      *      baseDirectory.value / "serverRoot" / "src" / "web",
      *      webappDir
      *    )*
      */

    IO.copyDirectory(baseDirectory.value / "zkwebui", webappDir)
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
