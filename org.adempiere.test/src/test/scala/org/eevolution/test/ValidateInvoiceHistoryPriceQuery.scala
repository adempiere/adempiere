/** ****************************************************************************
  * Product: Adempiere ERP & CRM Smart Business Solution                       *
  * This program is free software; you can redistribute it and/or modify it   *
  * under the terms version 2 of the GNU General Public License as published   *
  * by the Free Software Foundation. This program is distributed in the hope  *
  * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
  * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
  * See the GNU General Public License for more details.                       *
  * You should have received a copy of the GNU General Public License along   *
  * with this program; if not, write to the Free Software Foundation, Inc.,   *
  * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
  * ****************************************************************************/

package org.eevolution.test

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths}
import java.sql.ResultSet
import java.util.logging.Level
import java.util.regex.Pattern

import org.adempiere.Adempiere
import org.compiere.util.{CLogMgt, DB, Env, Ini, Login}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Functional regression test for issue adempiere/adempiere#4372:
  * the "Price History" tab of the Product Info window stayed empty because the
  * invoice price-history SQL concatenated the product id and the ORDER BY
  * clause without a separating space, so the database rejected the statement
  * and the tab silently showed nothing.
  *
  * Unlike the companion source-scanner spec, this spec extracts the invoice
  * price-history SQL from the production sources themselves (joining the Java
  * string literals and substituting the product id), executes it against the
  * database, and asserts that it runs and returns the posted invoice lines
  * ordered by DateInvoiced DESC. If the separating space before ORDER BY is
  * ever removed again, the extracted statement becomes invalid SQL and this
  * spec fails. The spec is read-only: it only queries existing demo data.
  */
class ValidateInvoiceHistoryPriceQuery
    extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  /**
    * The two InvoiceHistory implementations that build the invoice price-history
    * query shown in the Price History tab (queryBPartner, invoice branch), and
    * the number of such queries each contains.
    */
  private val sources = Seq(
    "client/src/org/compiere/apps/search/InvoiceHistory.java" -> 1,
    "zkwebui/WEB-INF/src/org/adempiere/webui/panel/InvoiceHistory.java" -> 1
  )

  /**
    * Start Adempiere with the standard GardenWorld demo credentials.
    * The shared startup still logs in as SuperUser, which does not exist in
    * the standard 3.9 install of client GardenWorld.
    */
  override def startup(): Unit = {
    Adempiere.startup(false)
    CLogMgt.setLevel(Level.OFF)
    CLogMgt.setLoggerLevel(Level.OFF, null)

    Ini.setProperty(Ini.P_UID, "GardenAdmin")
    Ini.setProperty(Ini.P_PWD, "GardenAdmin")
    Ini.setProperty(Ini.P_ROLE, "GardenWorld Admin")
    Ini.setProperty(Ini.P_CLIENT, "GardenWorld")
    Ini.setProperty(Ini.P_ORG, "Store Central")
    Ini.setProperty(Ini.P_WAREHOUSE, "Store Central")
    Ini.setProperty(Ini.P_LANGUAGE, "English")

    val login = new Login(Env.getCtx)
    if (!login.batchLogin(null))
      throw new AssertionError("batchLogin failed for GardenAdmin/GardenWorld")
    CLogMgt.setLoggerLevel(Level.SEVERE, null)
    CLogMgt.setLevel(Level.SEVERE)
  }    //	startup

  Feature("Price history SQL extracted from the InvoiceHistory sources runs and orders by invoice date") {

    sources.foreach { case (relativePath, expectedQueries) =>
      Scenario(s"$relativePath produces a runnable invoice price-history query") {
        val source = Files.readString(repoRoot.resolve(relativePath), StandardCharsets.UTF_8)

        Given("the invoice price-history SQL built by the production source")
        val statements = invoicePriceStatements(relativePath, source)
        assert(statements.size == expectedQueries,
          s"$relativePath: expected $expectedQueries invoice price-history queries, found ${statements.size}")

        And("a product with posted invoice lines on at least two invoice dates")
        val productId = productWithPostedInvoiceLines
        assert(productId > 0, "the demo database has no product with posted invoice lines on two dates")

        When("each extracted SQL statement is executed for that product")
        statements.zipWithIndex.foreach { case (template, index) =>
          val sql = template.replace("l.M_Product_ID=", "l.M_Product_ID=" + productId)
          val rows = scala.collection.mutable.ListBuffer[(java.math.BigDecimal, java.sql.Timestamp)]()
          val rs = execQuery(sql, Array[AnyRef]())
          try
            while (rs.next())
              rows += ((rs.getBigDecimal(2), rs.getTimestamp(5)))
          finally
            DB.close(rs)

          Then(s"statement ${index + 1} returns the posted invoice lines of product $productId")
          val expected = postedInvoiceLines(productId)
          assert(rows.nonEmpty,
            s"$relativePath statement ${index + 1}: query returned no rows for product $productId")
          assert(rows.map(_._1).toSet == expected.map(_._1).toSet,
            s"$relativePath statement ${index + 1}: prices differ from C_InvoiceLine for product $productId")

          And(s"statement ${index + 1} keeps them ordered by DateInvoiced DESC")
          val dates = rows.map(_._2)
          assert(dates.size == expected.size,
            s"$relativePath statement ${index + 1}: expected ${expected.size} rows, got ${dates.size}")
          assert(dates.zip(dates.drop(1)).forall { case (a, b) => !a.before(b) },
            s"$relativePath statement ${index + 1}: rows are not ordered by DateInvoiced DESC: $dates")
        }
      }
    }
  }    //	Feature

  /** Repository root: the test JVM may start from the repo root or a subproject. */
  private def repoRoot: Path = {
    var dir = Paths.get("").toAbsolutePath()
    while (dir != null) {
      if (Files.exists(dir.resolve("client/src/org/compiere/apps/search/InvoiceHistory.java")))
        return dir
      dir = dir.getParent
    }
    fail("repository root containing the Invoice History sources was not found")
  }

  /**
    * Extract every invoice price-history SQL statement from the source, joining
    * the Java string literals in order. The product-id placeholder is left as
    * the trailing `l.M_Product_ID=` fragment so the caller can substitute a
    * concrete id; a missing space before ORDER BY survives the join and makes
    * the statement invalid, which is exactly what this spec must catch.
    */
  private def invoicePriceStatements(path: String, source: String): Seq[String] = {
    val blockPattern = Pattern.compile(
      """sql\s*=\s*"SELECT bp\.Name,l\.PriceActual.*?ORDER BY i\.DateInvoiced DESC"\s*;""",
      Pattern.DOTALL)
    val literalPattern = Pattern.compile("\"([^\"]*)\"")
    val statements = scala.collection.mutable.ListBuffer[String]()
    val blocks = blockPattern.matcher(source)
    while (blocks.find()) {
      val block = blocks.group()
      val builder = new StringBuilder
      val literals = literalPattern.matcher(block)
      while (literals.find())
        builder.append(literals.group(1))
      val sql = builder.toString
      assert(sql.contains("l.M_Product_ID="),
        s"$path: extracted statement lost the product id fragment: $sql")
      assert(sql.contains(" ORDER BY i.DateInvoiced DESC"),
        s"$path: extracted statement has no space before ORDER BY: $sql")
      statements += sql
    }
    statements.toSeq
  }

  /** First product with posted invoice lines on at least two different invoice dates. */
  private def productWithPostedInvoiceLines: Int = {
    val sql =
      "SELECT l.M_Product_ID FROM C_Invoice i " +
      "INNER JOIN C_InvoiceLine l ON (i.C_Invoice_ID=l.C_Invoice_ID) " +
      "WHERE i.DocStatus='CO' " +
      "GROUP BY l.M_Product_ID " +
      "HAVING count(DISTINCT i.DateInvoiced) >= 2 " +
      "ORDER BY count(DISTINCT i.DateInvoiced) DESC, l.M_Product_ID"
    val rs = execQuery(sql, Array[AnyRef]())
    try
      (if (rs.next()) rs.getInt(1) else 0)
    finally
      DB.close(rs)
  }

  /**
    * Invoice lines of the product as the database holds them, using the same
    * WHERE clause as the production price-history query (no DocStatus filter),
    * so the comparison is exact rather than approximate.
    */
  private def postedInvoiceLines(productId: Int): Seq[(java.math.BigDecimal, java.sql.Timestamp)] = {
    val sql =
      "SELECT l.PriceActual, i.DateInvoiced FROM C_Invoice i " +
      "INNER JOIN C_InvoiceLine l ON (i.C_Invoice_ID=l.C_Invoice_ID) " +
      "WHERE l.M_Product_ID=" + productId + " " +
      "ORDER BY i.DateInvoiced DESC"
    val rows = scala.collection.mutable.ListBuffer[(java.math.BigDecimal, java.sql.Timestamp)]()
    val rs = execQuery(sql, Array[AnyRef]())
    try
      while (rs.next())
        rows += ((rs.getBigDecimal(1), rs.getTimestamp(2)))
    finally
      DB.close(rs)
    rows.toSeq
  }

  /** Execute a parameterized query on a read-write connection. */
  private def execQuery(sql: String, params: Array[AnyRef]): ResultSet = {
    val conn = DB.getConnectionRW()
    val ps = conn.prepareStatement(sql)
    try {
      var i = 1
      params.foreach { p =>
        ps.setObject(i, p)
        i += 1
      }
      ps.executeQuery()
    } catch {
      case e: Throwable =>
        ps.close()
        throw e
    }
  }    //	execQuery
}    //	ValidateInvoiceHistoryPriceQuery
