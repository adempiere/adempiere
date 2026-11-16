package org.eevolution.test

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util.regex.Pattern

import org.scalatest.featurespec.AnyFeatureSpec

/** Guards the installer default Java options against the reduced JVM jar signature file size limit. */
class ValidateJarSignatureJavaOptions extends AnyFeatureSpec {

  private val templatePath = "install/Adempiere/AdempiereEnvTemplate.properties"
  private val configurationDataPath = "install/src/org/compiere/install/ConfigurationData.java"

  // The forked test JVM may start from any working directory (repo root or subproject),
  // so resolve the sources relative to the repository root instead of the CWD.
  private def repoRoot: java.nio.file.Path = {
    var dir = Paths.get("").toAbsolutePath()
    while (dir != null) {
      if (Files.exists(dir.resolve(templatePath)))
        return dir
      dir = dir.getParent
    }
    fail(s"repository root containing $templatePath was not found")
  }

  private def read(path: String): String =
    new String(Files.readAllBytes(repoRoot.resolve(path)), StandardCharsets.UTF_8)

  Feature("Installer default Java options raise the jar signature file size limit") {

    Scenario("AdempiereEnvTemplate.properties carries the flag on its single ADEMPIERE_JAVA_OPTIONS line") {
      val lines = read(templatePath).split("\r?\n").filter(_.startsWith("ADEMPIERE_JAVA_OPTIONS="))
      assert(lines.length == 1,
        s"$templatePath: expected exactly 1 ADEMPIERE_JAVA_OPTIONS line, found ${lines.length}")
      // In a properties file the '=' of the -D flag is escaped as '\='.
      assert(lines(0).contains("-Djdk.jar.maxSignatureFileSize\\=20000000"),
        s"$templatePath: ADEMPIERE_JAVA_OPTIONS must carry -Djdk.jar.maxSignatureFileSize=20000000 " +
          s"(properties-escaped), found: ${lines(0)}")
    }

    Scenario("ConfigurationData default Java options carry the flag instead of bare heap sizing") {
      val matcher = Pattern
        .compile("setProperty\\(ADEMPIERE_JAVA_OPTIONS,\\s*\"([^\"]*)\"\\)")
        .matcher(read(configurationDataPath))
      val defaults = scala.collection.mutable.ArrayBuffer.empty[String]
      while (matcher.find()) defaults += matcher.group(1)
      assert(defaults.size == 1,
        s"$configurationDataPath: expected exactly 1 default ADEMPIERE_JAVA_OPTIONS assignment, found ${defaults.size}")
      // In the Java source the '=' of the -D flag is escaped as "\\=" inside the string literal.
      assert(defaults(0).contains("-Djdk.jar.maxSignatureFileSize\\\\=20000000"),
        s"$configurationDataPath: the default Java options must carry -Djdk.jar.maxSignatureFileSize " +
          s"(Java-escaped), found: ${defaults(0)}")
    }
  }
}
