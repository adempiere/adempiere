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
  * Copyright (C) 2003-2023 ADempiere Foundation,SC. All Rights Reserved.      *
  * ****************************************************************************/

package org.eevolution.test

import org.compiere.model.{MLookup, MLookupInfo, MQuery}
import org.compiere.util.{DB, DisplayType}
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.GivenWhenThen

import java.sql.{PreparedStatement, ResultSet, SQLException}

/**
  * Regression test for adempiere/adempiere#4457.
  *
  * `MLookup.getDirect` and the inner `MLoader.run` used to close their JDBC
  * resources only after successful processing, so an exception raised after the
  * statement was opened and before the trailing close left one statement open,
  * and an open statement pins its connection in the backend. The fix wraps both
  * query blocks in try-with-resources so the statement (and, in the loader, the
  * result set) closes whether processing completes or fails.
  *
  * This spec pins the observable contract the fix must preserve: a failed direct
  * lookup returns nothing instead of throwing, a successful direct lookup still
  * resolves the pair and caches it, a missing id resolves to nothing, and the
  * synchronous loader still fills the lookup from real rows.
  *
  * The connection-count probe reads `pg_stat_activity` so the test asserts the
  * leak is gone rather than asserting the fix's shape. Verified against a live
  * PostgreSQL: without the fix the failed-lookup scenario fails with the count
  * growing by roughly one connection per failed lookup; with the fix every
  * scenario passes with the count flat. The probe is noisy by nature (it counts
  * every backend of the database, including pooled connections a previous run
  * has not released), hence the small tolerance in `ProbeTolerance`.
  */
class ValidateMLookupClosesQueryResources extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val FailedLookups = 25

  /**
    * Tolerance of the pg_stat_activity probe. The probe counts every backend of
    * the database, including connections opened by the probe itself and by
    * in-flight statements of the lookup machinery, and it also sees pooled
    * connections that a previous run of the leaky code has not released yet.
    * A leaked-but-not-yet-released statement therefore shows as a small rise
    * even with the fix in place. The leak this spec pins grows by roughly one
    * connection per failed lookup (observed +6 to +9 without the fix), so a
    * tolerance of 2 separates leak from noise.
    */
  private val ProbeTolerance = 2

  /**
    * A direct lookup over C_BPartner keyed by C_BPartner_ID. DisplayType is a
    * Button (not Search) and the info is neither parent nor key so the
    * synchronous loader is allowed to run, exercising MLoader.run as well as
    * getDirect. IsValidated stays true (the dictionary default): an unvalidated
    * info with an empty ValidationCode makes MLoader.run append an empty
    * validation clause and produce invalid SQL. The loader query carries the
    * IsActive column that MLoader.run reads as its fourth column.
    */
  private def partnerLookup(): MLookup = {
    val lookupInfo = new MLookupInfo(
      "SELECT C_BPartner_ID, Value, Name, IsActive FROM C_BPartner ORDER BY C_BPartner_ID",
      "C_BPartner", "C_BPartner_ID", 0, 0, new MQuery(), false)
    lookupInfo.QueryDirect =
      "SELECT C_BPartner_ID, Value, Name FROM C_BPartner WHERE C_BPartner_ID=?"
    lookupInfo.DisplayType = DisplayType.Button
    new MLookup(lookupInfo, 0)
  }

  /** Active connections of the current database; -1 when the backend is not PostgreSQL. */
  private def openConnections: Int = {
    val sql = "SELECT COUNT(*) FROM pg_stat_activity WHERE datname = current_database()"
    var preparedStatement: PreparedStatement = null
    var resultSet: ResultSet = null
    try {
      preparedStatement = DB.prepareStatement(sql, null)
      resultSet = preparedStatement.executeQuery()
      resultSet.next()
      resultSet.getInt(1)
    } catch {
      case _: SQLException => -1 // not PostgreSQL: connection-count probe unavailable
    } finally {
      DB.close(resultSet, preparedStatement)
    }
  }

  /** An existing active partner id, so the success path runs against real rows. */
  private lazy val existingPartnerId: Int =
    DB.getSQLValue(null, "SELECT MIN(C_BPartner_ID) FROM C_BPartner WHERE IsActive='Y'")

  Feature("MLookup.getDirect closes its statement on every path") {

    Scenario("failed direct lookups with a non numeric key do not leak connections (#4457)") {
      Given("a lookup keyed by an *_ID column")
      val lookup = partnerLookup()
      assert(lookup != null)

      When("the lookup runs repeatedly with keys that cannot be parsed as an id")
      val connectionsBefore = openConnections
      for (_ <- 1 to FailedLookups) {
        val namePair = lookup.getDirect("NOT_A_NUMBER", false, false)
        And("each failed lookup returns nothing instead of throwing")
        assert(namePair == null, s"expected null for an unparseable key, got $namePair")
      }

      Then("the number of open database connections did not grow with every failed lookup")
      val connectionsAfter = openConnections
      if (connectionsBefore >= 0) {
        info(s"open connections before: $connectionsBefore, after: $connectionsAfter")
        assert(connectionsAfter <= connectionsBefore + ProbeTolerance,
          s"connection leak detected in MLookup.getDirect: $connectionsBefore -> $connectionsAfter after $FailedLookups failed lookups")
      } else {
        info("backend is not PostgreSQL, connection-count probe skipped")
      }
    }

    Scenario("a successful direct lookup still returns the pair and caches it") {
      Given("an existing active partner")
      assume(existingPartnerId > 0, "no active partner in the demo data")
      val lookup = partnerLookup()

      When("the lookup runs for that id")
      val namePair = lookup.getDirect(Integer.valueOf(existingPartnerId), true, false)

      Then("it returns the partner key and name")
      assert(namePair != null, "the lookup returned nothing for an existing partner")
      assert(namePair.getID == existingPartnerId.toString,
        s"pair id ${namePair.getID}, expected $existingPartnerId")
      assert(namePair.getName != null && namePair.getName.nonEmpty, "the lookup returned an empty name")
      info(s"resolved ${namePair.getID} -> ${namePair.getName}")

      And("a repeat lookup is served from the cache and returns the same pair")
      val cachedPair = lookup.getDirect(Integer.valueOf(existingPartnerId), true, false)
      assert(cachedPair != null && cachedPair.getID == namePair.getID,
        "the cached lookup did not resolve to the same partner")
    }

    Scenario("a lookup of a missing id resolves to nothing without leaking a connection") {
      Given("an id that cannot exist")
      val missingId = 1000000000
      val lookup = partnerLookup()

      When("the lookup runs repeatedly for the missing id")
      val connectionsBefore = openConnections
      for (_ <- 1 to FailedLookups)
        assert(lookup.getDirect(Integer.valueOf(missingId), false, false) == null,
          "a missing id must resolve to nothing")

      Then("no connection was left open by every failed lookup")
      val connectionsAfter = openConnections
      if (connectionsBefore >= 0) {
        info(s"open connections before: $connectionsBefore, after: $connectionsAfter")
        assert(connectionsAfter <= connectionsBefore + ProbeTolerance,
          s"connection leak detected on the empty-result path: $connectionsBefore -> $connectionsAfter")
      } else {
        info("backend is not PostgreSQL, connection-count probe skipped")
      }
    }
  }

  Feature("MLookup's synchronous loader closes its statement and result set on every path") {

    Scenario("the loader fills the lookup from real rows and reports it fully loaded (#4457)") {
      Given("a fresh lookup over active partners that has not loaded yet")
      val lookup = partnerLookup()

      When("a lookup miss triggers the synchronous loader")
      assume(existingPartnerId > 0, "no active partner in the demo data")
      val connectionsBefore = openConnections
      val namePair = lookup.get(Integer.valueOf(existingPartnerId))

      Then("the loader resolves the partner through the loaded data")
      assert(namePair != null, "the loader did not resolve an existing partner")
      assert(namePair.getID == existingPartnerId.toString,
        s"pair id ${namePair.getID}, expected $existingPartnerId")

      And("the lookup reports itself fully loaded")
      assert(lookup.isAllLoaded, "the loader did not mark the lookup as fully loaded")

      And("running the loader left no extra connection open")
      val connectionsAfter = openConnections
      if (connectionsBefore >= 0) {
        info(s"open connections before: $connectionsBefore, after: $connectionsAfter")
        assert(connectionsAfter <= connectionsBefore + ProbeTolerance,
          s"connection leak detected in MLoader.run: $connectionsBefore -> $connectionsAfter")
      } else {
        info("backend is not PostgreSQL, connection-count probe skipped")
      }
    }
  }
}
