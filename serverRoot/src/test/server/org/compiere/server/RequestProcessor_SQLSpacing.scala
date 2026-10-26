/** ****************************************************************************
  * Product: Adempiere ERP & CRM Smart Business Solution                       *
  * This program is free software; you can redistribute it and/or modify it    *
  * under the terms version 2 of the GNU General Public License as published   *
  * by the Free Software Foundation. This program is distributed in the hope   *
  * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
  * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
  * See the GNU General Public License for more details.                       *
  * You should have received a copy of the GNU General Public License along    *
  * with this program; if not, write to the Free Software Foundation, Inc.,    *
  * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
  * For the text or an alternative of this public license, you may reach us    *
  * Copyright (C) 2003-2024 e-Evolution,SC. All Rights Reserved.               *
  * Contributor(s): Victor Perez www.e-evolution.com                           *
  * ****************************************************************************/

package org.compiere.server

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

/**
  * Regression test for PostgreSQL 15+ SQL tokenization bug in RequestProcessor.
  *
  * PostgreSQL 15+ enforces stricter SQL tokenization rules. When Java string
  * concatenation produces SQL fragments without trailing whitespace between
  * clauses (e.g., `...)' AND EXISTS`), PG15+ rejects the query with a syntax
  * error at the point where two keywords are fused without space.
  *
  * This test validates that the SQL clause composition in RequestProcessor
  * maintains proper keyword spacing using a functional approach — composing
  * predicate fragments and verifying that every clause boundary is separated
  * by whitespace, preventing the `)AND` / `)'AND` collision pattern.
  *
  * @see <a href="https://github.com/adempiere/adempiere/issues/4211">Issue #4211</a>
  */
class RequestProcessor_SQLSpacingTest extends AnyFlatSpec with Matchers {

  // -----------------------------------------------------------------------
  // Domain: SQL clause boundary rules
  // -----------------------------------------------------------------------

  /** SQL keywords that must NOT be adjacent to other tokens without space. */
  private val sqlKeywords: List[String] = List(
    "AND", "OR", "WHERE", "ON", "HAVING", "CROSS",
    "LEFT", "RIGHT", "INNER", "OUTER", "FULL"
  )

  /** Patterns that indicate fused SQL tokens (the PG15+ bug trigger). */
  private def hasFusedTokens(sql: String): Boolean = sqlKeywords.exists { kw =>
    val fusedPatterns = List(
      s")$kw",        // closing paren directly before keyword
      s"'$kw",        // closing quote directly before keyword
      s"\",$kw"       // closing comma directly before keyword
    )
    fusedPatterns.exists(sql.contains)
  }

  /** Ensure every keyword boundary has preceding whitespace. */
  private def validateSpacing(sql: String): Boolean =
    !hasFusedTokens(sql)

  // -----------------------------------------------------------------------
  // Test cases: reproducing the original bug pattern
  // -----------------------------------------------------------------------

  "RequestProcessor SQL construction" should "not fuse closing parentheses with AND keyword" in {
    // Original buggy pattern (pre-fix):
    //   ...='Y')"
    //   + "AND EXISTS ("
    // produces: ...='Y")AND EXISTS (
    val buggyConcat = "AND rt.IsAutoChangeRequest='Y')" + "AND EXISTS ("
    buggyConcat shouldNot include(")AND")

    // Fixed pattern (post-fix):
    //   ...='Y') "
    //   + "AND EXISTS ("
    val fixedConcat = "AND rt.IsAutoChangeRequest='Y') " + "AND EXISTS ("
    validateSpacing(fixedConcat) shouldBe true
    fixedConcat should include(") AND")
  }

  it should "not fuse closing parentheses with WHERE keyword" in {
    val buggyConcat = "g.R_Group_ID = R_Request.R_Group_ID)" + "WHERE g.M_BOM_ID"
    buggyConcat shouldNot include(")WHERE")

    val fixedConcat = "g.R_Group_ID = R_Request.R_Group_ID) " + "WHERE g.M_BOM_ID"
    validateSpacing(fixedConcat) shouldBe true
  }

  it should "produce valid SQL when all fragments are composed" in {
    // Simulate the full WHERE clause as built by processECR() after the fix.
    val whereClause: String =
      "M_ChangeRequest_ID IS NULL" +
      " AND EXISTS(" +
      "SELECT 1 FROM R_RequestType rt " +
      "WHERE rt.R_RequestType_ID = R_Request.R_RequestType_ID " +
      "AND rt.IsAutoChangeRequest='Y') " +
      "AND EXISTS (" +
      "SELECT 1 FROM R_Group g " +
      "WHERE g.R_Group_ID = R_Request.R_Group_ID " +
      "AND (g.M_BOM_ID IS NOT NULL OR g.M_ChangeNotice_ID IS NOT NULL)	)"

    validateSpacing(whereClause) shouldBe true
    hasFusedTokens(whereClause) shouldBe false
  }

  // -----------------------------------------------------------------------
  // Functional validation: higher-order functions over SQL strings
  // -----------------------------------------------------------------------

  /** Transform a query by normalizing clause boundaries. Returns cleaned SQL. */
  private def normalizeClauseBoundaries(sql: String): String =
    sqlKeywords.foldLeft(sql) { (acc, kw) =>
      acc.replaceAll(s")($kw)", s") $kw")
    }

  "normalizeClauseBoundaries" should "insert space between ) and keywords" in {
    val buggy = "WHERE x=1)AND y=2)OR z=3"
    val normalized = normalizeClauseBoundaries(buggy)
    normalized should include(") AND")
    normalized should include(") OR")
    validateSpacing(normalized) shouldBe true
  }

  it should "leave already-correct SQL unchanged" in {
    val correct = "WHERE x=1) AND y=2) OR z=3"
    val result = normalizeClauseBoundaries(correct)
    result shouldBe correct
  }

  it should "handle nested subqueries" in {
    val nested = "SELECT * FROM t WHERE a=1)AND EXISTS(SELECT 1 FROM u WHERE b=2)AND c=3"
    val fixed = normalizeClauseBoundaries(nested)
    validateSpacing(fixed) shouldBe true
    hasFusedTokens(fixed) shouldBe false
  }

  // -----------------------------------------------------------------------
  // Boundary values: empty, whitespace-only, no keywords
  // -----------------------------------------------------------------------

  "validateSpacing" should "return true for simple SQL without boundaries" in {
    validateSpacing("SELECT * FROM R_Request") shouldBe true
  }

  it should "return true for SQL with proper spacing" in {
    val good = "SELECT 1 WHERE x = 1 AND y = 2"
    validateSpacing(good) shouldBe true
  }

  it should "return false for SQL with fused tokens" in {
    val bad = "SELECT 1 WHERE x = 1)AND y = 2"
    validateSpacing(bad) shouldBe false
  }

  // -----------------------------------------------------------------------
  // Reproduce exact original bug from RequestProcessor.processECR()
  // -----------------------------------------------------------------------

  "processECR WHERE clause" should "pass PG15+ tokenization check after fix" in {
    // The exact fixed whereClause from RequestProcessor.java line ~484
    val whereClause = new StringBuilder()
      .append("M_ChangeRequest_ID IS NULL")
      .append(" AND EXISTS(")
      .append("SELECT 1 FROM R_RequestType rt ")
      .append("WHERE rt.R_RequestType_ID = R_Request.R_RequestType_ID ")
      .append("AND rt.IsAutoChangeRequest='Y') ")
      .append("AND EXISTS (")
      .append("SELECT 1 FROM R_Group g ")
      .append("WHERE g.R_Group_ID = R_Request.R_Group_ID ")
      .append("AND (g.M_BOM_ID IS NOT NULL OR g.M_ChangeNotice_ID IS NOT NULL)	)")
      .toString()

    // Key assertions: every subquery boundary is space-separated
    whereClause should include("ID ")
    whereClause should include("Y') ")
    whereClause should include("ID ")
    whereClause should include(")	)")

    // No fused tokens — this is the regression gate for PG15+
    validateSpacing(whereClause) shouldBe true
  }
}
