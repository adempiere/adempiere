package org.eevolution.test

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util.regex.Pattern

import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Guards the inline ASP filter SQL of MProcess.getASPParameters() so the
  * client-exception show predicate keeps a space before the closing quote
  * and stays separated from the following AND EXISTS clause ("'S') AND
  * EXISTS" instead of "'S')AND EXISTS"). The no-space form is valid
  * PostgreSQL; the spacing is a defensive readability improvement that this
  * guard pins.
  */
class ValidateMProcessASPParametersSqlSpacing extends AnyFeatureSpec {

  private val source = "base/src/org/compiere/model/MProcess.java"

  // The forked test JVM may start from any working directory (repo root or
  // subproject), so resolve the source relative to the repository root instead
  // of the CWD.
  private def repoRoot: java.nio.file.Path = {
    var dir = Paths.get("").toAbsolutePath()
    while (dir != null) {
      if (Files.exists(dir.resolve(source)))
        return dir
      dir = dir.getParent
    }
    fail(s"repository root containing $source was not found")
  }

  Feature("MProcess ASP parameters SQL separates the clauses") {
    Scenario(s"$source keeps a space between the show predicate and the following AND EXISTS") {
      val text = Files.readString(repoRoot.resolve(source), StandardCharsets.UTF_8)

      // The client-exception "show" predicate must end with a space before the
      // closing quote, so the generated SQL reads "'S') AND EXISTS(...)" and
      // never "'S')AND EXISTS(...)".
      val showThenHide = Pattern.compile(
        "(?s)AND ce\\.ASP_Status = 'S'\\)(\\s*)\"[^\"]{0,120}?\\+\\s*\"AND EXISTS\\(SELECT 1 FROM ASP_ClientException"
      )
      val matcher = showThenHide.matcher(text)
      assert(matcher.find(),
        s"$source: expected the client-exception show predicate followed by its AND EXISTS clause")
      assert(matcher.group(1).endsWith(" "),
        s"$source: the show predicate must keep a trailing space before the following AND EXISTS")
    }
  }
}
