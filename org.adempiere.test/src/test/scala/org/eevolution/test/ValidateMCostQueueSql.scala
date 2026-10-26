package org.eevolution.test

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util.regex.Pattern

import org.scalatest.featurespec.AnyFeatureSpec

/** Guards MCostQueue's inline WHERE-clause SQL against the PostgreSQL 15/16
  * strict-token parsing failures fixed by trailing spaces after `?`
  * placeholders and before clause continuations. */
class ValidateMCostQueueSql extends AnyFeatureSpec {

  private val sourcePath = "base/src/org/compiere/model/MCostQueue.java"

  // MCostQueue inherits its COLUMNNAME_* constants from the generated
  // I_M_CostQueue interface, which is where the runtime column names are declared.
  private val interfacePath = "base/src/org/adempiere/core/domains/models/I_M_CostQueue.java"

  // The forked test JVM may start from any working directory (repo root or subproject),
  // so resolve the source relative to the repository root instead of the CWD.
  private def repoRoot: java.nio.file.Path = {
    var dir = Paths.get("").toAbsolutePath()
    while (dir != null) {
      if (Files.exists(dir.resolve(sourcePath)))
        return dir
      dir = dir.getParent
    }
    fail("repository root containing MCostQueue.java was not found")
  }

  private def readSource: String =
    Files.readString(repoRoot.resolve(sourcePath), StandardCharsets.UTF_8)

  private def readInterface: String =
    Files.readString(repoRoot.resolve(interfacePath), StandardCharsets.UTF_8)

  /** Extracts the SQL-building code of one method/statement with a (?s) regex. */
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
    * in the generated I_M_CostQueue interface.
    */
  private def columnValue(interfaceSource: String, column: String): String = {
    val m = Pattern.compile("COLUMNNAME_" + column + "\\s*=\\s*\"([^\"]*)\"").matcher(interfaceSource)
    assert(m.find(), s"expected a COLUMNNAME_$column declaration in $interfacePath")
    m.group(1)
  }

  /** Replaces each COLUMNNAME_* reference in an SQL-building portion with its
    * declared column name, so the assembled query reflects the runtime tokens
    * instead of dropping the identifiers the way raw string literals do.
    */
  private def withColumnNames(portion: String): String = {
    val m = Pattern.compile("COLUMNNAME_[A-Za-z0-9_]+").matcher(portion)
    val sb = new java.lang.StringBuilder()
    var last = 0
    while (m.find()) {
      sb.append(portion, last, m.start())
      val column = m.group().substring("COLUMNNAME_".length)
      sb.append("\"").append(columnValue(readInterface, column)).append("\"")
      last = m.end()
    }
    sb.append(portion, last, portion.length)
    sb.toString
  }

  private def nonLetterStart(literal: String): Boolean =
    literal.isEmpty || !Character.isLetter(literal.charAt(0))

  private val portions = Seq(
    "get" ->
      "public static MCostQueue get \\(MProduct.*?first\\(\\);",
    "getQueueForAdjustment" ->
      "public static MCostQueue getQueueForAdjustment.*?first\\(\\);",
    "getQueue (MCost cost)" ->
      "public static MCostQueue\\[\\] getQueue \\(MCost cost.*?\\.list\\(\\);",
    "getQueue (MProduct product)" ->
      "public static MCostQueue\\[\\] getQueue \\(MProduct.*?PreparedStatement pstmt = null;"
  )

  Feature("MCostQueue keeps PostgreSQL 15/16-parseable query strings") {
    portions.foreach { case (name, regex) =>
      Scenario(s"$name concatenates its SQL literals without a placeholder glued to a keyword") {
        val query = concatenated(withColumnNames(portionOf(readSource, regex)))
        assert(!Pattern.compile("\\?(?=\\p{L})").matcher(query).find(),
          s"$name: a '?' placeholder is directly followed by a keyword letter")
        assert(!Pattern.compile("=\\?(?=\\p{L})").matcher(query).find(),
          s"$name: an '=?' placeholder is directly followed by a keyword letter")
        val q = Pattern.compile("\\?").matcher(query)
        while (q.find()) {
          val after = q.start() + 1
          if (after < query.length)
            assert(!Character.isLetter(query.charAt(after)),
              s"$name: '?' at index ${q.start()} is not separated from the following token")
        }
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