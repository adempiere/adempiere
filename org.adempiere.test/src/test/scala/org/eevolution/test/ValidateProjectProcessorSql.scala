package org.eevolution.test

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util.regex.Pattern

import org.scalatest.featurespec.AnyFeatureSpec

/** Guards the ProjectProcessor schedule alert whereClauses (processDueAlert and
  * processOverDueAlert) against the PostgreSQL 15/16 strict-token parsing
  * failures fixed by the trailing space after the ')' closing the C_ProjectType_ID
  * filter and the project phase EXISTS(...) literal. */
class ValidateProjectProcessorSql extends AnyFeatureSpec {

  private val sourcePath = "serverRoot/src/main/server/org/compiere/server/ProjectProcessor.java"

  // The forked test JVM may start from any working directory (repo root or subproject),
  // so resolve the source relative to the repository root instead of the CWD.
  private def repoRoot: java.nio.file.Path = {
    var dir = Paths.get("").toAbsolutePath()
    while (dir != null) {
      if (Files.exists(dir.resolve(sourcePath)))
        return dir
      dir = dir.getParent
    }
    fail(s"repository root containing $sourcePath was not found")
  }

  private def readSource: String =
    Files.readString(repoRoot.resolve(sourcePath), StandardCharsets.UTF_8)

  private def stringLiterals(portion: String): Seq[String] = {
    val m = Pattern.compile("\"([^\"]*)\"").matcher(portion)
    val out = scala.collection.mutable.ArrayBuffer.empty[String]
    while (m.find()) out += m.group(1)
    out.toSeq
  }

  private def concatenated(portion: String): String =
    stringLiterals(portion).mkString("")

  private def nonLetterStart(literal: String): Boolean =
    literal.isEmpty || !Character.isLetter(literal.charAt(0))

  /** Collects every occurrence of a SQL-building snippet in the source. */
  private def portionsOf(source: String, regex: String): Seq[String] = {
    val m = Pattern.compile("(?s)" + regex).matcher(source)
    val out = scala.collection.mutable.ArrayBuffer.empty[String]
    while (m.find()) out += m.group()
    assert(out.nonEmpty, s"expected SQL-building code matching [$regex] in $sourcePath")
    out.toSeq
  }

  // processDueAlert and processOverDueAlert each build a Project block
  // ("AND  (DateFinish IS NULL) " + type filter) and a Project Phase block
  // ("AND  (EndDate IS NULL) " + type EXISTS filter); the matched occurrences
  // are ordered: due Project, overdue Project / due Phase, overdue Phase.
  private val projectBlock =
    "whereClause = \"AND  \\(DateFinish IS NULL\\) \";\\s*" +
    "if \\(m_model\\.getC_ProjectType_ID\\(\\)!=0\\) \\{\\s*" +
    "whereClause\\+= \"[^\"]*\";"

  private val phaseBlock =
    "whereClause = \"AND  \\(EndDate IS NULL\\) \";\\s*" +
    "if \\(m_model\\.getC_ProjectType_ID\\(\\)!=0\\)\\s*" +
    "whereClause\\+= \"[^\"]*\";"

  private val projectBlocks = portionsOf(readSource, projectBlock)
  assert(projectBlocks.length == 2,
    s"expected 2 Project type-filter blocks (one per alert method), found ${projectBlocks.length}")

  private val phaseBlocks = portionsOf(readSource, phaseBlock)
  assert(phaseBlocks.length == 2,
    s"expected 2 Project Phase EXISTS type-filter blocks (one per alert method), found ${phaseBlocks.length}")

  private val portions: Seq[(String, String)] = Seq(
    ("processDueAlert/Project C_ProjectType_ID filter", projectBlocks(0)),
    ("processDueAlert/Project Phase EXISTS type filter", phaseBlocks(0)),
    ("processOverDueAlert/Project C_ProjectType_ID filter", projectBlocks(1)),
    ("processOverDueAlert/Project Phase EXISTS type filter", phaseBlocks(1))
  )

  Feature("ProjectProcessor keeps PostgreSQL 15/16-parseable schedule alert queries") {
    portions.foreach { case (name, portion) =>
      Scenario(s"$name concatenates its SQL literals without a token glued to a keyword") {
        val query = concatenated(portion)
        assert(!Pattern.compile("\\)(?=\\p{L})").matcher(query).find(),
          s"$name: a closing parenthesis is directly followed by a keyword letter")
        assert(!Pattern.compile("\\?(?=\\p{L})").matcher(query).find(),
          s"$name: a '?' placeholder is directly followed by a keyword letter")
      }
    }
  }

  Feature("the PG 15/16 SQL spacing fix stays in place") {
    portions.foreach { case (name, portion) =>
      Scenario(s"$name never leaves a placeholder or closing paren glued to the next SQL token") {
        val literals = stringLiterals(portion)
        for (i <- 0 until literals.length - 1) {
          val cur = literals(i)
          val next = literals(i + 1)
          if (cur.endsWith("?") || cur.endsWith(")"))
            assert(nonLetterStart(next),
              s"$name: literal [$cur] is directly followed by a letter-leading literal [$next]")
        }
      }
      Scenario(s"$name ends its type filter literal with a trailing space") {
        val last = stringLiterals(portion).last
        assert(last.endsWith("?) "),
          s"$name: the type filter literal [$last] lost its trailing space")
      }
    }
  }
}
