package org.eevolution.test

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util.regex.Pattern

import org.scalatest.featurespec.AnyFeatureSpec

/** Guards MHRConcept's HR_Concept_Acct query whereClause against the PostgreSQL
  * 15/16 strict-token parsing failures fixed by trailing spaces after '?' placeholders
  * and closing ')' SQL literals. */
class ValidateMHRConceptSql extends AnyFeatureSpec {

  private val sourcePath =
    "org.eevolution.hr_and_payroll/src/main/java/base/org/eevolution/hr/model/MHRConcept.java"

  // The forked test JVM may start from any working directory (repo root or subproject),
  // so resolve the source relative to the repository root instead of the CWD.
  private def repoRoot: java.nio.file.Path = {
    var dir = Paths.get("").toAbsolutePath()
    while (dir != null) {
      if (Files.exists(dir.resolve(sourcePath)))
        return dir
      dir = dir.getParent
    }
    fail("repository root containing MHRConcept.java was not found")
  }

  private def readSource: String =
    Files.readString(repoRoot.resolve(sourcePath), StandardCharsets.UTF_8)

  /** Extracts the SQL-building code of one statement with a (?s) regex. */
  private def portionOf(source: String, regex: String): String = {
    val m = Pattern.compile("(?s)" + regex).matcher(source)
    assert(m.find(), s"expected SQL-building code matching [$regex] in $sourcePath")
    m.group()
  }

  private def stringLiterals(portion: String): Seq[String] = {
    val m = Pattern.compile("\"([^\"]*)\"").matcher(portion)
    val out = scala.collection.mutable.ArrayBuffer.empty[String]
    while (m.find()) out += m.group(1)
    out.toSeq
  }

  private def concatenated(portion: String): String =
    stringLiterals(portion).mkString("")

  private val portions = Seq(
    "getHR_Concept_AcctQuery whereClause" ->
      "StringBuilder whereClause = new StringBuilder\\(\\);(?s:.*?)paramenters.add\\(partnerGroupId\\);"
  )

  Feature("MHRConcept keeps PostgreSQL 15/16-parseable query strings") {
    portions.foreach { case (name, regex) =>
      Scenario(s"$name concatenates its SQL literals without a token glued to a keyword") {
        val query = concatenated(portionOf(readSource, regex))
        assert(!Pattern.compile("\\)(?=\\p{L})").matcher(query).find(),
          s"$name: a closing parenthesis is directly followed by a keyword letter")
        assert(!Pattern.compile("\\?(?=\\p{L})").matcher(query).find(),
          s"$name: a '?' placeholder is directly followed by a keyword letter")
      }
    }
  }

  Feature("the PG 15/16 SQL spacing fix stays in place") {
    portions.foreach { case (name, regex) =>
      Scenario(s"$name keeps every '?' placeholder and closing ')' spaced") {
        val literals = stringLiterals(portionOf(readSource, regex))
        for (lit <- literals)
          if (lit.endsWith("?") || lit.endsWith(")"))
            assert(lit.endsWith("? ") || lit.endsWith(") "),
              s"$name: placeholder [$lit] ends bare '?' or ')' without a trailing space")
      }
    }
  }
}