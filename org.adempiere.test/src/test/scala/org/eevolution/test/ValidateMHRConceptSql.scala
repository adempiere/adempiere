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

  // MHRConcept references its HR_Concept_Acct columns through the generated
  // I_HR_Concept_Acct interface, which declares the runtime column names.
  private val interfacePath = "base/src/org/adempiere/core/domains/models/I_HR_Concept_Acct.java"

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

  private def readInterface: String =
    Files.readString(repoRoot.resolve(interfacePath), StandardCharsets.UTF_8)

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

  /** Resolves the runtime column value of a COLUMNNAME_* constant as declared
    * in the generated I_HR_Concept_Acct interface.
    */
  private def columnValue(interfaceSource: String, column: String): String = {
    val m = Pattern.compile("COLUMNNAME_" + column + "\\s*=\\s*\"([^\"]*)\"").matcher(interfaceSource)
    assert(m.find(), s"expected a COLUMNNAME_$column declaration in $interfacePath")
    m.group(1)
  }

  /** Replaces each I_HR_Concept_Acct.COLUMNNAME_* reference in an SQL-building
    * portion with its declared column name, so the assembled query reflects the
    * runtime tokens instead of dropping the identifiers like raw string literals do.
    */
  private def withColumnNames(portion: String): String = {
    val m = Pattern.compile("(?:I_[A-Za-z0-9_]+\\.)?COLUMNNAME_[A-Za-z0-9_]+").matcher(portion)
    val sb = new java.lang.StringBuilder()
    var last = 0
    while (m.find()) {
      sb.append(portion, last, m.start())
      val full = m.group()
      val column = full.substring(full.lastIndexOf("COLUMNNAME_") + "COLUMNNAME_".length)
      sb.append("\"").append(columnValue(readInterface, column)).append("\"")
      last = m.end()
    }
    sb.append(portion, last, portion.length)
    sb.toString
  }

  private def nonLetterStart(literal: String): Boolean =
    literal.isEmpty || !Character.isLetter(literal.charAt(0))

  private val portions = Seq(
    "getHR_Concept_AcctQuery whereClause" ->
      "StringBuilder whereClause = new StringBuilder\\(\\);(?s:.*?)paramenters.add\\(partnerGroupId\\);"
  )

  Feature("MHRConcept keeps PostgreSQL 15/16-parseable query strings") {
    portions.foreach { case (name, regex) =>
      Scenario(s"$name concatenates its SQL literals without a token glued to a keyword") {
        val query = concatenated(withColumnNames(portionOf(readSource, regex)))
        assert(!Pattern.compile("\\)(?=\\p{L})").matcher(query).find(),
          s"$name: a closing parenthesis is directly followed by a keyword letter")
        assert(!Pattern.compile("\\?(?=\\p{L})").matcher(query).find(),
          s"$name: a '?' placeholder is directly followed by a keyword letter")
      }
    }
  }

  Feature("the PG 15/16 SQL spacing fix stays in place") {
    portions.foreach { case (name, regex) =>
      Scenario(s"$name never leaves a placeholder or closing paren glued to the next SQL token") {
        val literals = stringLiterals(withColumnNames(portionOf(readSource, regex)))
        for (i <- 0 until literals.length - 1) {
          val cur = literals(i)
          val next = literals(i + 1)
          if (cur.endsWith("?") || cur.endsWith(")"))
            assert(nonLetterStart(next),
              s"$name: literal [$cur] is directly followed by a letter-leading literal [$next]")
        }
      }
    }
  }
}