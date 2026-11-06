package org.eevolution.test

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util.regex.Pattern

import org.scalatest.featurespec.AnyFeatureSpec

/** Guards Viewer's ASP client-level SQL filter against the PostgreSQL 15/16
  * strict-token parsing failures fixed by a trailing space after the closing
  * ')' of AND ce.ASP_Status = 'S'. */
class ValidateViewerSql extends AnyFeatureSpec {

  private val sourcePath = "client/src/org/compiere/print/Viewer.java"

  // The forked test JVM may start from any working directory (repo root or subproject),
  // so resolve the source relative to the repository root instead of the CWD.
  private def repoRoot: java.nio.file.Path = {
    var dir = Paths.get("").toAbsolutePath()
    while (dir != null) {
      if (Files.exists(dir.resolve(sourcePath)))
        return dir
      dir = dir.getParent
    }
    fail("repository root containing Viewer.java was not found")
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
    "aSPFilter minus-hide block" ->
      "\\+ \"\\s*EXISTS\\(SELECT 1 FROM ASP_ClientException ce .*?'S'\\)\\s*\".*?\\+ \"AND EXISTS\\(SELECT 1 FROM ASP_ClientException ce \""
  )

  Feature("Viewer keeps PostgreSQL 15/16-parseable query strings") {
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
      Scenario(s"$name ends a glue-potential closing ')' with a trailing space") {
        val literals = stringLiterals(portionOf(readSource, regex))
        for (i <- 0 until literals.length) {
          val lit = literals(i)
          val hasSuccessor = i < literals.length - 1
          if (lit.endsWith(")") && hasSuccessor && !literals(i + 1).startsWith(" "))
            assert(lit.endsWith(") "),
              s"$name: literal [$lit] ends in a bare ')' but SQL continues after it")
        }
      }
    }
  }
}