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
  * Contributor(s): Victor Perez www.e-evolution.com                           *
  * ****************************************************************************/

package org.eevolution.test

import java.sql.{ResultSet, SQLException}
import java.util.concurrent.atomic.AtomicInteger

import io.vavr.collection.{List => VavrList}
import io.vavr.control.Try
import io.vavr.Function4
import org.compiere.util.{DB, Env, KeyNamePair, Login, ResultSetRunnable}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Regression test for issue #4460: Login.loadPreferences() must read each
  * result set and release its connection before running the queries that
  * depend on those rows.
  *
  * Before the fix, the accounting-schema callback processed its rows while
  * its own result set was still open, and the default-column callback called
  * loadDefault() — which opens another query through the same
  * DB.runResultSetFunction — from inside its callback. One sign-in could
  * therefore hold several connections at once and exhaust small pools under
  * concurrent logins.
  *
  * The test replaces the public static DB.runResultSetFunction with a
  * recording decorator that tracks how deep each query is nested inside
  * another still-open query, then restores the original in a finally block.
  * With the fix, no loadPreferences query ever runs at nesting depth greater
  * than one, and a failing accounting-schema query keeps its own message as
  * the returned error instead of being replaced by "NoValidAcctInfo".
  */
class ValidateLoginReleasesResultsBeforeFollowUpQueries extends AnyFeatureSpec
  with AdempiereTestCase
  with GivenWhenThen {

  private type RunResultSet = Function4[String, String, VavrList[Object], ResultSetRunnable[ResultSet], Try[Void]]

  /** SQL fragments identifying each query issued by loadPreferences. */
  private val AcctSchemaSql = "C_AcctSchema1_ID"
  private val DefaultColumnsSql = "ColumnName = 'IsDefault'"
  private val LoadDefaultSql = "IsDefault = ? AND IsActive = ?"

  private def login(): Login = {
    val context = Env.getCtx
    new Login(context)
  }

  private def currentOrg: KeyNamePair =
    new KeyNamePair(Env.getContextAsInt(Env.getCtx, "#AD_Org_ID"), "HQ")

  private def currentWarehouse: KeyNamePair = {
    val warehouse = Warehouse
    new KeyNamePair(warehouse.getM_Warehouse_ID, warehouse.getName)
  }

  /**
    * Installs a decorator that records (sql, nesting depth) for every
    * DB.runResultSetFunction call while body runs, and returns the recorded
    * calls plus the maximum observed nesting depth.
    */
  private def recordQueryNesting(failSqlContaining: Option[String] = None)(body: => String): (Seq[(String, Int)], Int, String) = {
    val original: RunResultSet = DB.runResultSetFunction
    val depth = new AtomicInteger(0)
    val maxDepth = new AtomicInteger(0)
    val calls = scala.collection.mutable.ArrayBuffer.empty[(String, Int)]
    DB.runResultSetFunction = (trxName, sql, parameters, callback) => {
      val currentDepth = depth.incrementAndGet()
      try {
        maxDepth.updateAndGet((max: Int) => math.max(max, currentDepth))
        calls += ((sql, currentDepth))
        if (failSqlContaining.exists(sql.contains))
          Try.failure[Void](new SQLException("injected query failure"))
        else
          original.apply(trxName, sql, parameters, callback)
      } finally {
        depth.decrementAndGet()
      }
    }
    try {
      val result = body
      (calls.toVector, maxDepth.get(), result)
    } finally {
      DB.runResultSetFunction = original
    }
  }

  Feature("Login releases query results before follow-up queries") {
    info("Issue #4460: sign-in must not hold a result set open while running the queries that consume its rows")

    Scenario("No loadPreferences query runs nested inside another open result set") {
      Given("a decorator recording the nesting depth of every login query")
      When("loadPreferences loads the preferences of the logged-in organization")
      val (calls, maxDepth, result) = recordQueryNesting() {
        login().loadPreferences(currentOrg, currentWarehouse, null, null)
      }

      Then("the preference load completes without error")
      assert(result == "", s"loadPreferences returned an unexpected error: $result")

      And("the accounting schema query ran and its rows were applied to the context")
      assert(calls.exists(_._1.contains(AcctSchemaSql)),
        "the accounting schema query never ran")
      assert(Env.getContext(Env.getCtx, "$C_AcctSchema_ID").nonEmpty,
        "loadPreferences did not apply the accounting schema rows to the context")

      And("no query ever ran nested inside another open result set")
      assert(maxDepth == 1,
        s"a login query ran at nesting depth $maxDepth while an outer result set was still open: " +
          calls.map { case (sql, d) => s"depth=$d sql=${sql.take(80)}" }.mkString(" | "))

      And("the default-column query completed before any loadDefault ran")
      val loadDefaultCalls = calls.filter(_._1.contains(LoadDefaultSql))
      val defaultColumnCalls = calls.filter(_._1.contains(DefaultColumnsSql))
      assert(defaultColumnCalls.nonEmpty, "the default-column query never ran")
      assert(loadDefaultCalls.nonEmpty, "loadDefault never ran")
      // Every loadDefault must appear after the default-column query that
      // produced its arguments, and none may share the callback of an
      // open query (depth 1 only, asserted above).
      val firstDefaultColumnIndex = calls.indexWhere(_._1.contains(DefaultColumnsSql))
      val firstLoadDefaultIndex = calls.indexWhere(_._1.contains(LoadDefaultSql))
      assert(firstLoadDefaultIndex > firstDefaultColumnIndex,
        "loadDefault ran before the default-column query completed")
    }

    Scenario("A failing accounting-schema query keeps its own error message") {
      Given("an accounting-schema query that fails")
      When("loadPreferences loads the preferences of the logged-in organization")
      val (_, _, result) = recordQueryNesting(Some(AcctSchemaSql)) {
        login().loadPreferences(currentOrg, currentWarehouse, null, null)
      }

      Then("the query failure is returned instead of the missing-schema message")
      assert(result == "injected query failure",
        s"expected the query failure to be preserved, got: $result")
      assert(result != "NoValidAcctInfo",
        "a query failure must not be replaced by the missing-account-schema message")
    }
  }
}
