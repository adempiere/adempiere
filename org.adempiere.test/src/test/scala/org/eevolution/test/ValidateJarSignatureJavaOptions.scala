package org.eevolution.test

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util.regex.Pattern

import org.scalatest.featurespec.AnyFeatureSpec

/** Guards the installer default Java options against the reduced JVM jar signature file size limit. */
class ValidateJarSignatureJavaOptions extends AnyFeatureSpec {

  private val templatePath = "install/Adempiere/AdempiereEnvTemplate.properties"
  private val configurationDataPath = "install/src/org/compiere/install/ConfigurationData.java"

  private val effectiveOption = "-Djdk.jar.maxSignatureFileSize=20000000"

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

    Scenario("AdempiereEnvTemplate.properties delivers the flag to Properties.load") {
      val lines = read(templatePath).split("\r?\n").filter(_.startsWith("ADEMPIERE_JAVA_OPTIONS="))
      assert(lines.length == 1,
        s"$templatePath: expected exactly 1 ADEMPIERE_JAVA_OPTIONS line, found ${lines.length}")
      // Properties.load unescapes '\=' to '=', which is the effective value the JVM receives.
      val effective = lines(0).replace("\\=", "=")
      assert(effective.contains(effectiveOption),
        s"$templatePath: ADEMPIERE_JAVA_OPTIONS must load as an option carrying $effectiveOption, " +
          s"found: ${lines(0)}")
    }

    Scenario("ConfigurationData default Java options deliver the flag without a literal backslash") {
      val matcher = Pattern
        .compile("setProperty\\(ADEMPIERE_JAVA_OPTIONS,\\s*\"([^\"]*)\"\\)")
        .matcher(read(configurationDataPath))
      val defaults = scala.collection.mutable.ArrayBuffer.empty[String]
      while (matcher.find()) defaults += matcher.group(1)
      assert(defaults.size == 1,
        s"$configurationDataPath: expected exactly 1 default ADEMPIERE_JAVA_OPTIONS assignment, found ${defaults.size}")
      // Decode the Java string-literal escape: two source backslashes are one backslash in
      // the value. A backslash before '=' survives Properties persistence, so the JVM would
      // set a property named jdk.jar.maxSignatureFileSize\ and the limit would stay default.
      val effective = defaults(0).replace("\\\\", "\\")
      assert(effective.contains(effectiveOption),
        s"$configurationDataPath: the default must carry $effectiveOption with a plain '=', " +
          s"found: ${defaults(0)}")
    }
  }
}
