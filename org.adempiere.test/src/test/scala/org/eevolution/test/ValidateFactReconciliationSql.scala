package org.eevolution.test

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util.regex.Pattern

import org.scalatest.featurespec.AnyFeatureSpec

/**
 * Guards the inline DELETE statement that FactReconciliation.doIt executes
 * against PostgreSQL, whose parser became stricter from version 15 on: every
 * SQL token must stay separated, and the correlated subquery that filters
 * T_Reconciliation rows must keep its exact shape.
 */
class ValidateFactReconciliationSql extends AnyFeatureSpec {

  private val sourcePath = "base/src/org/compiere/process/FactReconciliation.java"

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

  private val source: String = Files.readString(repoRoot.resolve(sourcePath), StandardCharsets.UTF_8)

  // Concatenates every string literal of the statement assigned to `sql`,
  // exactly as the Java compiler joins them with `+`.
  private def concatenatedStatement(javaStatementText: String): String = {
    val literals = Pattern.compile("\"([^\"]*)\"").matcher(javaStatementText)
    val sb = new StringBuilder
    while (literals.find())
      sb.append(literals.group(1))
    sb.toString
  }

  private def doItStatements: Seq[String] = {
    val assignment = Pattern.compile("(?s)sql = \"DELETE FROM T_Reconciliation.*?\";")
    val matches = assignment.matcher(source)
    val found = scala.collection.mutable.ArrayBuffer.empty[String]
    while (matches.find())
      found += concatenatedStatement(matches.group())
    found.toSeq
  }

  Feature("FactReconciliation.doIt keeps a valid DELETE statement for PostgreSQL 15+") {
    Scenario("defines exactly one DELETE FROM T_Reconciliation statement") {
      val statements = doItStatements
      assert(statements.size == 1,
        s"expected 1 DELETE FROM T_Reconciliation statement in doIt, found ${statements.size}")
    }

    Scenario("keeps every SQL token separated, so PostgreSQL 15/16 parses the statement") {
      val statement = doItStatements.head
      assert(!statement.contains("MatchCodeAND"),
        s"doIt DELETE statement must keep MatchCode separated from AND: $statement")
      assert(Pattern.matches(
        ".*WHERE r\\.MatchCode=t\\.MatchCode\\s+AND r\\.AD_PInstance_ID = t\\.AD_PInstance_ID\\) = 0 AND t\\.AD_PInstance_ID = \\?.*",
        statement),
        s"doIt DELETE statement lost its WHERE/AND token separation: $statement")
    }

    Scenario("filters balanced T_Reconciliation rows through the correlated Fact_Acct subquery") {
      val statement = doItStatements.head
      assert(statement.startsWith("DELETE FROM T_Reconciliation t WHERE (SELECT SUM(f.amtacctdr-f.amtacctcr)"),
        s"doIt DELETE statement no longer selects balanced rows: $statement")
      assert(statement.contains("INNER JOIN Fact_Acct f ON (f.Fact_Acct_ID = r.Fact_Acct_ID)"),
        s"doIt DELETE statement lost its Fact_Acct join: $statement")
      assert(statement.contains("r.MatchCode=t.MatchCode") &&
        statement.contains("AND t.AD_PInstance_ID = ?"),
        s"doIt DELETE statement lost its correlation keys: $statement")
    }
  }
}
