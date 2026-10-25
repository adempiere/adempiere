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
        val query = concatenated(portionOf(readSource, regex))
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
      Scenario(s"$name ends every continuing '?'-literal with a trailing space") {
        val literals = stringLiterals(portionOf(readSource, regex))
        for (i <- literals.indices) {
          if (literals(i).endsWith("?") && i < literals.length - 1)
            assert(literals(i).endsWith("? "),
              s"$name: literal [${literals(i)}] ends in a bare '?' but SQL continues after it")
        }
      }
    }
  }
}