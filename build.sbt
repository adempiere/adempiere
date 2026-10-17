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
  scalaVersion := "3.3.1"
)

scalaVersion := "3.3.1"
resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"

fork := true

val sourceDirectoryTest = "org.adempiere.test"

/** Build configuration. Every value can be overridden with an environment variable or a JVM system property
  * (for example `sbt -Dadempiere.propertyFile=/path/Adempiere.properties`). See org.adempiere.test/README.md.
  *
  *   - ADEMPIERE_SOURCE_DIR    / adempiere.sourceDir    : ADempiere source tree, default the sbt build root
  *   - ADEMPIERE_PROPERTY_FILE / adempiere.propertyFile : Adempiere.properties for the tests, default
  *                                                       <source dir>/install/build/Adempiere/Adempiere.properties
  *   - ADEMPIERE_JAVA_HOME     / adempiere.javaHome     : JDK 17 used to compile and to run the forked tests,
  *                                                       default the JDK that runs sbt
  *   - ADEMPIERE_HOME          / adempiere.home         : log directory of the tests, default <source dir>/target/adempiere-home
  *   - ADEMPIERE_TEST_CLASSPATH/ adempiere.testClasspath: extra classpath entries (separated by the OS path
  *                                                       separator) added to the test run, e.g. validator classes
  */
def adempiereSetting(env: String, prop: String): Option[String] =
  sys.props.get(prop).orElse(sys.env.get(env)).map(_.trim).filter(_.nonEmpty)

val sourceAdempiere: String = adempiereSetting("ADEMPIERE_SOURCE_DIR", "adempiere.sourceDir")
  .getOrElse(file(".").getAbsoluteFile.toPath.normalize.toString)
val adempierePropertyFile: String =
  adempiereSetting("ADEMPIERE_PROPERTY_FILE", "adempiere.propertyFile")
    .getOrElse(sourceAdempiere + "/install/build/Adempiere/Adempiere.properties")
val adempiereJavaHome: Option[File] =
  adempiereSetting("ADEMPIERE_JAVA_HOME", "adempiere.javaHome").map(file)
val adempiereTestClasspath: Seq[File] =
  adempiereSetting("ADEMPIERE_TEST_CLASSPATH", "adempiere.testClasspath").toSeq
    .flatMap(_.split(java.io.File.pathSeparator).toSeq)
    .filter(_.nonEmpty)
    .map(file)
val adempiereProperties = "-DPropertyFile=" + adempierePropertyFile
// Directory for the ADempiere logs of the forked test JVM (Ini.getAdempiereHome, otherwise "/Adempiere")
val adempiereHome: String = adempiereSetting("ADEMPIERE_HOME", "adempiere.home")
  .getOrElse(file(sourceAdempiere + "/target/adempiere-home").getPath)

val requiredJavaVersion = "17"

/** Fails fast when the JDK used to compile or to run the tests is not JDK 17. */
def checkJavaVersion(): Unit = {
  val version = adempiereJavaHome match {
    case Some(home) =>
      val release = home / "release"
      if (!release.isFile)
        sys.error(s"ADEMPIERE_JAVA_HOME=$home is not a JDK home (no release file).")
      IO.readLines(release)
        .find(_.startsWith("JAVA_VERSION="))
        .map(_.stripPrefix("JAVA_VERSION=").replace("\"", ""))
        .getOrElse("unknown")
    case None => sys.props("java.version")
  }
  if (version.split('.').head != requiredJavaVersion)
    sys.error(
      s"ADempiere needs JDK $requiredJavaVersion but found $version. " +
        s"Start sbt with a JDK $requiredJavaVersion (JAVA_HOME) or set ADEMPIERE_JAVA_HOME to one."
    )
}

/** ADempiere Java modules compiled by sbt (zinc, incremental) for the tests: the Gradle modules without the web
  * and server ones. The model validators registered in AD_ModelValidator must be on the classpath to log in. */
val adempiereJavaSources = Seq(
  "tools/src",
  "base/src",
  "client/src",
  "extend/src",
  "migration/src",
  "JasperReports/src",
  "org.adempiere.request/src",
  "org.adempiere.project/src",
  "org.adempiere.production/src",
  "org.eevolution.cashflow/src",
  "org.eevolution.distribution/src/main/java/base",
  "org.eevolution.freight/src",
  "org.eevolution.manufacturing/src/main/java/base",
  "org.eevolution.warehouse/src/main/java/base",
  "org.eevolution.hr_and_payroll/src/main/java/base",
  "org.adempiere.crm/src/main/java",
  "org.adempiere.asset/src/main/java/base",
  "org.adempiere.pos/src/main/java/base",
  "org.spin.hr_time_and_attendance/src/main/java/base",
  "org.spin.finance_management/src/main/java/base",
  "org.spin.loan_management/src/main/java/base",
  "org.spin.store/src/main/java",
  "org.spin.authentication/src/main/java/base",
  "org.spin.notifier.discord/src/main/java/base",
  "org.spin.notifier.telegram/src/main/java/base"
)

/** tools/lib subdirectories that must not be on the classpath: *-support-legacy jars repackage classes that now
  * live in the modules (they shadow and collide with the sources), the others are test-only jars. */
val excludedJarDirs = Set("legacy", "junit", "junit-old", "testing")

/** Jars bundled in the repository (all subdirectories but the excluded ones), without the *-sources jars. */
def adempiereJars(dirs: Seq[File]): Seq[Attributed[File]] =
  dirs
    .flatMap(dir => (dir ** "*.jar").get)
    .filterNot(f => f.getName.contains("source") || f.getName.contains("_src"))
    .filterNot(f => f.getPath.split(java.io.File.separatorChar).exists(excludedJarDirs.contains))
    .map(Attributed.blank)

lazy val adempiereCore = (project in file("target/adempiere-core"))
  .settings(
    name := "adempiere-core",
    scalaVersion := "3.3.1",
    autoScalaLibrary := false,
    crossPaths := false,
    Compile / unmanagedSourceDirectories :=
      adempiereJavaSources.map(d => file(sourceAdempiere) / d),
    // some module trees (org.adempiere.project/src/test) also hold Scala files: compile Java only
    Compile / unmanagedSources / includeFilter := "*.java",
    Compile / unmanagedResourceDirectories :=
      adempiereJavaSources.map(d => file(sourceAdempiere) / d),
    Compile / unmanagedResources / excludeFilter := "*.java",
    // modules share some resource paths (license.html, package.html): the first directory wins
    Compile / unmanagedResources := {
      val seen = scala.collection.mutable.Set[String]()
      val dirs = (Compile / unmanagedResourceDirectories).value
      val all = (Compile / unmanagedResources).value
      all.filter { f =>
        val rel = dirs.flatMap(d => IO.relativize(d, f)).headOption.getOrElse(f.getName)
        seen.add(rel)
      }
    },
    Compile / compile / javacOptions ++= Seq(
      "-source", requiredJavaVersion,
      "-target", requiredJavaVersion,
      "-proc:none",
      "-nowarn",
      "-encoding", "UTF-8"
    ),
    javaHome := adempiereJavaHome,
    Compile / unmanagedJars := adempiereJars(
      Seq("lib", "tools/lib", "zkwebui/WEB-INF/lib", "JasperReportsTools/lib", "packages", "zkpackages")
        .map(d => file(sourceAdempiere) / d)
    ),
    Compile / compile := (Compile / compile).dependsOn(Def.task(checkJavaVersion())).value
  )

/*scalacOptions ++= Seq(
  "-feature",
  "-unchecked",
  "-deprecation",
  "-encoding",
  "utf8",
  "-Ytasty-reader"
)*/

val adempiereExports = Seq(
  "--add-exports=java.base/sun.security.util=ALL-UNNAMED",
  "--add-exports=java.base/sun.security.x509=ALL-UNNAMED"
)

Test / javaOptions := Seq(adempiereProperties, "-DADEMPIERE_HOME=" + adempiereHome) ++ adempiereExports
Test / javaHome := adempiereJavaHome
// Without ADEMPIERE_APPS_TYPE the database layer refuses to create its connection pool; "standalone" is the
// value for a plain JVM (no application server / JNDI).
Test / envVars += "ADEMPIERE_APPS_TYPE" -> sys.env.getOrElse("ADEMPIERE_APPS_TYPE", "standalone")
Test / unmanagedClasspath ++= adempiereTestClasspath.map(Attributed.blank)
Test / unmanagedJars += Attributed.blank(
  file(sourceAdempiere) / "tools" / "lib" / "testing" / "SuperCSV-with_src-1.52.jar"
)
// definedTests is used by both `test` and `testOnly`: check the environment before any test is forked
Test / definedTests := (Test / definedTests).dependsOn(Def.task {
  checkJavaVersion()
  if (!file(adempierePropertyFile).isFile)
    sys.error(
      s"ADempiere property file not found: $adempierePropertyFile. " +
        "Set ADEMPIERE_PROPERTY_FILE (see org.adempiere.test/README.md)."
    )
  IO.createDirectory(file(adempiereHome))
}).value

lazy val root = (project in file(".")).dependsOn(adempiereCore)

libraryDependencies ++= Seq(
  "javax.servlet" % "javax.servlet-api" % "4.0.1" % "provided"
  //"org.scala-lang" % "scala-reflect" % "3.0.0"
)

// JUnit 5 and Mockito: same versions as org.adempiere.test/build.gradle
libraryDependencies ++= Seq(
  "org.junit.jupiter" % "junit-jupiter-api" % "5.7.0" % Test,
  "org.mockito" % "mockito-junit-jupiter" % "3.6.0" % Test,
  "org.junit.platform" % "junit-platform-console-standalone" % "1.7.0" % Test
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

// The ZK UI classes of org.eevolution.warehouse need the zkwebui classes, which sbt does not build:
// the root project only compiles the Scala sources of org.adempiere.test (ADempiere Java is in adempiereCore).
Compile / unmanagedSourceDirectories := Seq((Compile / scalaSource).value)

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
