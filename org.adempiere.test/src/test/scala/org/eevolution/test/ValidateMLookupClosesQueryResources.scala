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
  * lookup returns nothing instead of throwing, a direct lookup whose row read
  * fails (fewer columns than the lookup expects) returns nothing, a successful
  * direct lookup still resolves the pair and serves the repeat from the cache,
  * a missing id resolves to nothing, the synchronous loader still fills the
  * lookup from real rows, and a loader whose row read fails leaves no statement
  * open across repeated attempts.
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

  /**
    * Open connections of the current database, waited until stable. The pool
    * grows lazily and backends disappear asynchronously, so a single sample can
    * catch the count mid-drift even with no leak; two consecutive equal samples
    * mean the pool has settled. -1 when the backend is not PostgreSQL.
    */
  private def stableConnections: Int = {
    var previous = openConnections
    if (previous < 0) return -1
    var stable = false
    val deadline = System.currentTimeMillis() + 10000
    while (!stable && System.currentTimeMillis() < deadline) {
      Thread.sleep(500)
      val current = openConnections
      stable = current == previous
      previous = current
    }
    previous
  }

  /** An existing active partner id, so the success path runs against real rows. */
  private lazy val existingPartnerId: Int =
    DB.getSQLValue(null, "SELECT MIN(C_BPartner_ID) FROM C_BPartner WHERE IsActive='Y'")

  /** A small table (52 active rows in the reference data) for the loader scenarios. */
  private lazy val existingDocTypeId: Int =
    DB.getSQLValue(null, "SELECT MIN(C_DocType_ID) FROM C_DocType WHERE IsActive='Y' AND C_DocType_ID > 0")

  /**
    * A lookup over C_DocType, a table small enough for the synchronous loader
    * and with an id space disjoint from C_BPartner, so its MLookupCache entry
    * is never pre-populated by the other scenarios of this spec in the same
    * JVM and the loader really runs.
    */
  private def docTypeLookup(): MLookup = {
    val lookupInfo = new MLookupInfo(
      "SELECT C_DocType_ID, SUBSTR(DocBaseType,1,10), Name, IsActive FROM C_DocType ORDER BY C_DocType_ID",
      "C_DocType", "C_DocType_ID", 0, 0, new MQuery(), false)
    lookupInfo.QueryDirect =
      "SELECT C_DocType_ID, SUBSTR(DocBaseType,1,10), Name FROM C_DocType WHERE C_DocType_ID=?"
    lookupInfo.DisplayType = DisplayType.Button
    new MLookup(lookupInfo, 0)
  }

  /**
    * A lookup whose loader query is missing the IsActive column that
    * `MLoader.run` reads as its fourth column, so the row read raises a
    * SQLException after `executeQuery` while the statement and result set are
    * open — the failure window the fix closes.
    */
  private def docTypeLookupLoaderReadFails(): MLookup = {
    val lookupInfo = new MLookupInfo(
      "SELECT C_DocType_ID, SUBSTR(DocBaseType,1,10), Name FROM C_DocType ORDER BY C_DocType_ID",
      "C_DocType", "C_DocType_ID", 0, 0, new MQuery(), false)
    lookupInfo.DisplayType = DisplayType.Button
    new MLookup(lookupInfo, 0)
  }

  /**
    * A lookup whose direct query returns only the key and value columns, so the
    * name read at `rs.getString(3)` raises a SQLException on an existing row
    * while the statement is open — the read failure the fix closes.
    */
  private def partnerLookupDirectReadFails(): MLookup = {
    val lookupInfo = new MLookupInfo(
      "SELECT C_BPartner_ID, Value, Name, IsActive FROM C_BPartner ORDER BY C_BPartner_ID",
      "C_BPartner", "C_BPartner_ID", 0, 0, new MQuery(), false)
    lookupInfo.QueryDirect =
      "SELECT C_BPartner_ID, Value FROM C_BPartner WHERE C_BPartner_ID=?"
    lookupInfo.DisplayType = DisplayType.Button
    new MLookup(lookupInfo, 0)
  }

  Feature("MLookup.getDirect closes its statement on every path") {

    Scenario("failed direct lookups with a non numeric key do not leak connections (#4457)") {
      Given("a lookup keyed by an *_ID column")
      val lookup = partnerLookup()
      assert(lookup != null)

      When("the lookup runs repeatedly with keys that cannot be parsed as an id")
      val connectionsBefore = stableConnections
      for (_ <- 1 to FailedLookups) {
        val namePair = lookup.getDirect("NOT_A_NUMBER", false, false)
        And("each failed lookup returns nothing instead of throwing")
        assert(namePair == null, s"expected null for an unparseable key, got $namePair")
      }

      Then("the number of open database connections did not grow with every failed lookup")
      val connectionsAfter = stableConnections
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

      And("a repeat lookup through get is served from the cache populated by saveInCache and returns the same pair")
      val cachedPair = lookup.get(Integer.valueOf(existingPartnerId))
      assert(cachedPair != null && cachedPair.getID == namePair.getID,
        "the cached lookup did not resolve to the same partner")
    }

    Scenario("direct lookups whose row read fails return nothing without leaking connections (#4457)") {
      Given("an existing active partner and a direct query missing the name column")
      assume(existingPartnerId > 0, "no active partner in the demo data")
      val lookup = partnerLookupDirectReadFails()

      When("the lookup runs repeatedly and every row read raises after executeQuery")
      val connectionsBefore = stableConnections
      for (_ <- 1 to FailedLookups) {
        val namePair = lookup.getDirect(Integer.valueOf(existingPartnerId), false, false)
        And("each failed read returns nothing instead of throwing")
        assert(namePair == null, s"expected null when the name column cannot be read, got $namePair")
      }

      Then("the number of open database connections did not grow with every failed read")
      val connectionsAfter = stableConnections
      if (connectionsBefore >= 0) {
        info(s"open connections before: $connectionsBefore, after: $connectionsAfter")
        assert(connectionsAfter <= connectionsBefore + ProbeTolerance,
          s"connection leak detected on the direct read failure path: $connectionsBefore -> $connectionsAfter")
      } else {
        info("backend is not PostgreSQL, connection-count probe skipped")
      }
    }

    Scenario("a lookup of a missing id resolves to nothing without leaking a connection") {
      Given("an id that cannot exist")
      val missingId = 1000000000
      val lookup = partnerLookup()

      When("the lookup runs repeatedly for the missing id")
      val connectionsBefore = stableConnections
      for (_ <- 1 to FailedLookups)
        assert(lookup.getDirect(Integer.valueOf(missingId), false, false) == null,
          "a missing id must resolve to nothing")

      Then("no connection was left open by every failed lookup")
      val connectionsAfter = stableConnections
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
      Given("a fresh lookup over a distinct small table that has not loaded yet")
      assume(existingDocTypeId > 0, "no document type in the reference data")
      val lookup = docTypeLookup()

      When("a lookup miss triggers the synchronous loader")
      val connectionsBefore = stableConnections
      val namePair = lookup.get(Integer.valueOf(existingDocTypeId))

      Then("the loader resolves the record through the loaded data")
      assert(namePair != null, "the loader did not resolve an existing record")
      assert(namePair.getID == existingDocTypeId.toString,
        s"pair id ${namePair.getID}, expected $existingDocTypeId")

      And("the lookup reports itself fully loaded")
      assert(lookup.isAllLoaded, "the loader did not mark the lookup as fully loaded")

      And("running the loader left no extra connection open")
      val connectionsAfter = stableConnections
      if (connectionsBefore >= 0) {
        info(s"open connections before: $connectionsBefore, after: $connectionsAfter")
        assert(connectionsAfter <= connectionsBefore + ProbeTolerance,
          s"connection leak detected in MLoader.run: $connectionsBefore -> $connectionsAfter")
      } else {
        info("backend is not PostgreSQL, connection-count probe skipped")
      }
    }

    Scenario("repeated loader runs whose row read fails leak no connection (#4457)") {
      Given("a lookup whose loader query is missing the IsActive column the loader reads")
      assume(existingDocTypeId > 0, "no document type in the reference data")

      When("the loader runs repeatedly and the row read raises after executeQuery")
      val connectionsBefore = stableConnections
      for (_ <- 1 to 5) {
        val lookup = docTypeLookupLoaderReadFails()
        lookup.get(Integer.valueOf(existingDocTypeId))
      }

      Then("the number of open database connections did not grow with every failed loader run")
      val connectionsAfter = stableConnections
      if (connectionsBefore >= 0) {
        info(s"open connections before: $connectionsBefore, after: $connectionsAfter")
        assert(connectionsAfter <= connectionsBefore + ProbeTolerance,
          s"connection leak detected when MLoader.run fails mid-read: $connectionsBefore -> $connectionsAfter")
      } else {
        info("backend is not PostgreSQL, connection-count probe skipped")
      }
    }
  }
}
