package org.eevolution.test

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util.regex.Pattern

import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Guards the inline isUsed SQL of MPaySelectionLine so the DocStatus
  * predicate keeps a space before the closing quote and stays separated
  * from the following AND clause ("NOT IN('VO', 'RE') AND
  * cpsl.C_PaySelectionLine_Parent_ID" instead of "NOT IN('VO', 'RE')AND
  * cpsl.C_PaySelectionLine_Parent_ID"). The no-space form is valid
  * PostgreSQL; the spacing is a defensive readability improvement that this
  * guard pins for the PostgreSQL 15+ regression (issue #4204).
  */
class ValidatePaySelectionLineIsUsedSqlSpacing extends AnyFeatureSpec {

  private val source = "base/src/org/compiere/model/MPaySelectionLine.java"

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

  Feature("MPaySelectionLine isUsed SQL separates the clauses") {
    Scenario(s"$source keeps a space between the DocStatus predicate and the following AND clause") {
      val text = Files.readString(repoRoot.resolve(source), StandardCharsets.UTF_8)

      // The DocStatus predicate must end with a space before the closing
      // quote, so the generated SQL reads "NOT IN('VO', 'RE') AND
      // cpsl.C_PaySelectionLine_Parent_ID" and never
      // "NOT IN('VO', 'RE')AND cpsl.C_PaySelectionLine_Parent_ID".
      val predicateThenAnd = Pattern.compile(
        "(?s)WHERE cps\\.DocStatus NOT IN\\('VO', 'RE'\\)(\\s*)\"[^\"]{0,120}?\\+\\s*\"AND cpsl\\.C_PaySelectionLine_Parent_ID = \\?"
      )
      val matcher = predicateThenAnd.matcher(text)
      assert(matcher.find(),
        s"$source: expected the isUsed DocStatus predicate followed by its AND cpsl.C_PaySelectionLine_Parent_ID clause")
      assert(matcher.group(1).endsWith(" "),
        s"$source: the isUsed DocStatus predicate must keep a trailing space before the following AND clause")
    }
  }
}
