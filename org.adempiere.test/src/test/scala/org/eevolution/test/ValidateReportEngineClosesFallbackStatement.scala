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
  * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
  * Contributor(s): e-Evolution,SC. All Rights Reserved.                       *
  * ****************************************************************************/

package org.eevolution.test

import org.compiere.print.ReportEngine
import org.compiere.util.DB
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.GivenWhenThen

import java.sql.{PreparedStatement, ResultSet, SQLException}
import java.lang.reflect.Method

/**
  * Regression test for the leaked fallback statement of
  * `ReportEngine.getDocumentWhat` (adempiere/adempiere#4454, PR #4455).
  *
  * When the C_DocType_ID lookup of an order yields no DocSubTypeSO, the method
  * prepares a second query on C_DocTypeTarget_ID. Before the fix it replaced
  * the first PreparedStatement/ResultSet without closing them, so one open
  * connection leaked per lookup and the connection pool kept growing until it
  * was exhausted.
  *
  * An order id that does not exist in C_Order drives both lookups to zero rows,
  * which deterministically exercises the fallback branch without creating data.
  */
class ValidateReportEngineClosesFallbackStatement extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  /** Order id that cannot exist in C_Order, so both queries return zero rows. */
  private val missingOrder = 1000000000

  /** Calls the private static ReportEngine.getDocumentWhat via reflection. */
  private def getDocumentWhat(recordId: Int): Array[Int] = {
    val method: Method = classOf[ReportEngine].getDeclaredMethod("getDocumentWhat", classOf[Int], classOf[Int])
    method.setAccessible(true)
    method.invoke(null, java.lang.Integer.valueOf(ReportEngine.ORDER), java.lang.Integer.valueOf(recordId)).asInstanceOf[Array[Int]]
  }

  /**
    * Number of active connections of the current database, seen by PostgreSQL.
    * An open statement/result set pins its connection in the backend, so a leak
    * shows up as a growing count. Returns -1 when the backend is not PostgreSQL.
    */
  private def openConnections: Int = {
    val sql = "SELECT COUNT(*) FROM pg_stat_activity WHERE datname = current_database()"
    var pstmt: PreparedStatement = null
    var rs: ResultSet = null
    try {
      pstmt = DB.prepareStatement(sql, null)
      rs = pstmt.executeQuery()
      rs.next()
      rs.getInt(1)
    } catch {
      case _: SQLException => -1 // not PostgreSQL: leak probe unavailable
    } finally {
      DB.close(rs, pstmt)
    }
  }

  Feature("ReportEngine.getDocumentWhat closes the first statement before the C_DocTypeTarget_ID fallback") {

    Scenario("repeated fallback lookups do not leak database connections") {
      Given("an order id that does not exist in C_Order")
      assert(missingOrder > 0)

      When("the fallback branch is exercised repeatedly")
      val before = openConnections
      for (_ <- 1 to 15) {
        val what = getDocumentWhat(missingOrder)
        And("the lookup still resolves to the order itself")
        assert(what.length == 2 && what(0) == ReportEngine.ORDER && what(1) == missingOrder)
      }

      Then("the number of open database connections did not grow")
      val after = openConnections
      if (before >= 0) {
        info(s"open connections before: $before, after: $after")
        assert(after <= before + 1, s"connection leak detected in ReportEngine.getDocumentWhat fallback: $before -> $after")
      } else {
        info("backend is not PostgreSQL, connection count probe skipped")
      }
    }
  }
}
