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
  * ****************************************************************************/

package org.eevolution.test

import java.math.BigDecimal
import java.sql.{ResultSet, Timestamp}
import java.util.Calendar

import org.compiere.util.{DB, Env, Trx}
import org.scalatest.{BeforeAndAfterAll, GivenWhenThen}
import org.scalatest.featurespec.AnyFeatureSpec

import scala.collection.mutable

/**
  * Database-backed regression test for adempiere/adempiere#4213.
  *
  * Financial reports used to wrap the accounting date in TRUNC() inside the
  * date-range predicates (TRUNC(DateAcct, 'DD')), which stops PostgreSQL from
  * using the Fact_Acct.DateAcct indexes. The fix compares DateAcct directly
  * against the range bounds.
  *
  * This spec proves, against a live database, the two things that matter:
  *
  *  1. The new direct predicate selects exactly the facts inside the period,
  *     including the period END date (the upper bound is inclusive) and
  *     excluding the day after it, and the summed amounts are correct.
  *  2. The new direct predicate, the legacy TRUNC-wrapped predicate and the
  *     half-open form all return the SAME row set and the SAME totals, so
  *     removing TRUNC changes nothing the reports report. DateAcct holds
  *     date-only values, which is exactly why those forms are equivalent;
  *     this spec asserts it on real data instead of assuming it.
  *
  * The spec seeds its own Fact_Acct rows on an open period and deletes them
  * in a finally block, so the shared demo database is left untouched.
  */
class ValidateFinReportDateAcctRange
    extends AnyFeatureSpec with GivenWhenThen with BeforeAndAfterAll with AdempiereStartup {

  /**
    * AdempiereStartup only exposes startup(); it does not hook the suite
    * lifecycle itself. Without this the DB target is never set and every query
    * fails with a NullPointerException inside DB.createConnection.
    */
  override def beforeAll(): Unit = {
    startup()
  }

  /** Seeded debit amounts: 100 / 200 / 300 inside the range, 400 outside. */
  private val DrStart = new BigDecimal("100")
  private val DrMid = new BigDecimal("200")
  private val DrEnd = new BigDecimal("300")
  private val DrAfterEnd = new BigDecimal("400")
  /** Expected in-range totals: 100 + 200 + 300. */
  private val ExpectedDr = new BigDecimal("600")

  Feature("DateAcct range predicate over live accounting facts") {

    Scenario("the direct predicate keeps the period end inclusive and excludes the next day") {
      val client = Env.getContextAsInt(Env.getCtx, "#AD_Client_ID")

      Given("an open accounting period")
      val period = pickOpenPeriod(client)
      info(s"Open period ${period.start} .. ${period.end} (C_Period_ID=${period.periodId})")

      And("accounting facts seeded on each side of the period boundary")
      val seeded = seedFacts(client, period)
      info(s"Seeded fact ids: start=${seeded.startId} mid=${seeded.midId} " +
        s"end=${seeded.endId} afterEnd=${seeded.afterEndId}")

      try {
        When("the fixed direct date-range predicate is executed for the period")
        val direct = queryRange(
          s"fact_acct_id IN (${seeded.idsSql}) AND dateacct >= ? AND dateacct <= ?",
          Array[AnyRef](period.start, period.end))

        Then("only the period start, mid and end facts are selected")
        assert(direct.keySet == Set(seeded.startId, seeded.midId, seeded.endId),
          s"expected the three in-range facts, got ${direct.keySet}")

        And("the period end fact is included, because the upper bound is inclusive")
        assert(direct.contains(seeded.endId),
          "the fact on the period end date must be inside the range")

        And("the fact of the day after the period end is excluded")
        assert(!direct.contains(seeded.afterEndId),
          "the fact on the day after the period end must be outside the range")

        And("the summed amounts are exactly the seeded in-range amounts")
        assert(sumDr(direct).compareTo(ExpectedDr) == 0,
          s"expected Dr $ExpectedDr, got ${sumDr(direct)}")
        assert(sumCr(direct).compareTo(BigDecimal.ZERO) == 0,
          s"expected Cr 0, got ${sumCr(direct)}")

        When("the same range is expressed with the BETWEEN shape used by the reports")
        val between = queryRange(
          s"fact_acct_id IN (${seeded.idsSql}) AND dateacct BETWEEN ? AND ?",
          Array[AnyRef](period.start, period.end))

        Then("the BETWEEN form selects the same rows and totals")
        assert(sameResult(between, direct),
          s"BETWEEN ${between.keySet} != direct ${direct.keySet}")

        When("the legacy TRUNC-wrapped predicate is executed for the same range")
        val legacy = queryRange(
          s"fact_acct_id IN (${seeded.idsSql}) AND trunc(dateacct) >= ? AND trunc(dateacct) <= ?",
          Array[AnyRef](period.start, period.end))

        Then("the legacy form returns the same rows and totals as the fixed direct form")
        assert(sameResult(legacy, direct),
          s"TRUNC form ${legacy.keySet} != direct form ${direct.keySet}")

        When("the upper bound is expressed as an exclusive half-open bound")
        val halfOpen = queryRange(
          s"fact_acct_id IN (${seeded.idsSql}) AND dateacct >= ? AND dateacct < ?",
          Array[AnyRef](period.start, plusDays(period.end, 1)))

        Then("it selects the same rows and totals, so the fix changed no result")
        assert(sameResult(halfOpen, direct),
          s"half-open ${halfOpen.keySet} != inclusive ${direct.keySet}")
      } finally {
        deleteFacts(seeded)
      }
    }    //	Scenario

    Scenario("the seeded facts are gone after cleanup") {
      val client = Env.getContextAsInt(Env.getCtx, "#AD_Client_ID")
      val period = pickOpenPeriod(client)
      val seeded = seedFacts(client, period)

      Given("the seeded facts are visible to the range predicate")
      assert(queryRange(s"fact_acct_id IN (${seeded.idsSql})", Array[AnyRef]()).size == 4,
        "the four seeded facts must be present before cleanup")

      When("the seeded rows are deleted")
      deleteFacts(seeded)

      Then("no seeded fact remains in the database")
      val left = queryRange(s"fact_acct_id IN (${seeded.idsSql})", Array[AnyRef]())
      assert(left.isEmpty, s"seeded facts still present: ${left.keySet}")
    }    //	Scenario
  }    //	Feature

  /** An open accounting period with its bounds and its accounting schema. */
  private case class OpenPeriod(periodId: Int, start: Timestamp, end: Timestamp, schema: AcctSchema)

  /** The seeded fact ids, so the scenario and cleanup agree. */
  private case class Seeded(startId: Int, midId: Int, endId: Int, afterEndId: Int) {
    def idsSql: String = Seq(startId, midId, endId, afterEndId).mkString(",")
  }

  /**
    * Pick the most recent period genuinely open for accounting: every one of
    * its C_PeriodControl rows must be open. Filtering on a single docbasetype
    * would be wrong, because the accounting docbasetype differs per client and
    * a period half-closed under any other type is not safe to post into.
    */
  private def pickOpenPeriod(client: Int): OpenPeriod = {
    var period: OpenPeriod = null
    Trx.run { _ =>
      var periodId = 0
      var start: Timestamp = null
      var end: Timestamp = null
      val rs = execQuery(
        "SELECT cp.c_period_id, cp.startdate, cp.enddate " +
        "FROM c_period cp " +
        "JOIN c_periodcontrol cpc ON cpc.c_period_id = cp.c_period_id " +
        "AND cpc.ad_client_id = cp.ad_client_id " +
        "WHERE cp.ad_client_id = ? AND cp.isactive = 'Y' " +
        "GROUP BY cp.c_period_id, cp.startdate, cp.enddate " +
        "HAVING bool_and(cpc.periodstatus = 'O') " +
        "ORDER BY cp.startdate DESC LIMIT 1",
        Array[AnyRef](Integer.valueOf(client)))
      try {
        if (!rs.next())
          throw new AssertionError(
            "No open accounting period found for the client; cannot run the boundary regression")
        periodId = rs.getInt("c_period_id")
        start = rs.getTimestamp("startdate")
        end = rs.getTimestamp("enddate")
      } finally {
        DB.close(rs)
      }
      period = OpenPeriod(periodId, start, end, acctSchema(client))
    }
    period
  }    //	pickOpenPeriod

  /**
    * Accounting schema used to post into the period: id and currency, read from
    * the same C_AcctSchema row so the two always agree. C_PeriodControl carries
    * no accounting schema column.
    */
  private def acctSchema(client: Int): AcctSchema = {
    val rs = execQuery(
      "SELECT c_acctschema_id, c_currency_id FROM c_acctschema WHERE ad_client_id IN (0, ?) AND isactive = 'Y' " +
      "ORDER BY ad_client_id DESC, c_acctschema_id LIMIT 1",
      Array[AnyRef](Integer.valueOf(client)))
    try {
      if (!rs.next())
        throw new AssertionError("No accounting schema found for the client")
      AcctSchema(rs.getInt("c_acctschema_id"), rs.getInt("c_currency_id"))
    } finally {
      DB.close(rs)
    }
  }    //	acctSchema

  /** Accounting schema id and its currency, from one C_AcctSchema row. */
  private case class AcctSchema(schemaId: Int, currencyId: Int)

  /**
    * Seed four accounting facts: period start, mid-month, period end (the
    * inclusive upper bound) and the day after the period end. Ids come from a
    * high sentinel range so they never collide with real rows, and each insert
    * is asserted so a silent failure cannot pass as success.
    */
  private def seedFacts(client: Int, period: OpenPeriod): Seeded = {
    var seeded: Seeded = null
    Trx.run { name =>
      var baseId = 0
      val rs = execQuery("SELECT coalesce(max(fact_acct_id), 0) + 900000000 FROM fact_acct",
        Array[AnyRef]())
      try {
        rs.next()
        baseId = rs.getInt(1)
      } finally {
        DB.close(rs)
      }
      val account = singleInt(
        "SELECT c_elementvalue_id FROM c_elementvalue WHERE ad_client_id IN (0, ?) AND isactive = 'Y' " +
        "AND postactual = 'Y' AND accounttype IN ('A','L','O','R','M') " +
        "ORDER BY ad_client_id DESC, c_elementvalue_id LIMIT 1",
        Array[AnyRef](Integer.valueOf(client)))
      val orgId = Env.getAD_Org_ID(Env.getCtx)

      val startId = baseId + 1
      val midId = baseId + 2
      val endId = baseId + 3
      val afterEndId = baseId + 4

      insertFact(client, orgId, period, account, startId, period.start, DrStart, name)
      insertFact(client, orgId, period, account, midId, plusDays(period.start, 14), DrMid, name)
      insertFact(client, orgId, period, account, endId, period.end, DrEnd, name)
      insertFact(client, orgId, period, account, afterEndId, plusDays(period.end, 1), DrAfterEnd, name)

      seeded = Seeded(startId, midId, endId, afterEndId)
    }
    seeded
  }    //	seedFacts

  private def insertFact(client: Int, orgId: Int, period: OpenPeriod,
      account: Int, factId: Int, dateAcct: Timestamp, amountDr: BigDecimal, trxName: String): Unit = {
    val inserted = DB.executeUpdate(
      "INSERT INTO fact_acct (fact_acct_id, ad_client_id, ad_org_id, createdby, updatedby, " +
      "c_acctschema_id, account_id, c_currency_id, c_period_id, datetrx, dateacct, " +
      "ad_table_id, record_id, postingtype, amtsourcedr, amtsourcecr, amtacctdr, amtacctcr) " +
      "VALUES (?, ?, ?, 101, 101, ?, ?, ?, ?, ?, ?, 100, ?, 'P', ?, 0, ?, 0)",
      Array[AnyRef](
        Integer.valueOf(factId),
        Integer.valueOf(client),
        Integer.valueOf(orgId),
        Integer.valueOf(period.schema.schemaId),
        Integer.valueOf(account),
        Integer.valueOf(period.schema.currencyId),
        Integer.valueOf(period.periodId),
        dateAcct,
        dateAcct,
        Integer.valueOf(factId),
        amountDr,
        amountDr),
      false, trxName)
    if (inserted != 1)
      throw new AssertionError(s"expected to insert fact_acct_id=$factId, affected $inserted")
  }    //	insertFact

  /** Delete exactly the seeded rows; never touches anything else. */
  private def deleteFacts(seeded: Seeded): Unit = {
    Trx.run { name =>
      val deleted = DB.executeUpdate(
        s"DELETE FROM fact_acct WHERE fact_acct_id IN (${seeded.idsSql})", null, false, name)
      info(s"cleanup deleted $deleted seeded fact_acct rows")
    }
  }    //	deleteFacts

  /**
    * Run a predicate over fact_acct and collect the rows by id.
    *
    * @return fact_acct_id -> (amount Dr, amount Cr)
    */
  private def queryRange(where: String, params: Array[AnyRef]): Map[Int, (BigDecimal, BigDecimal)] = {
    val rows = mutable.LinkedHashMap.empty[Int, (BigDecimal, BigDecimal)]
    Trx.run { _ =>
      val rs = execQuery(
        s"SELECT fact_acct_id, amtacctdr, amtacctcr FROM fact_acct WHERE $where", params)
      try {
        while (rs.next()) {
          val dr = rs.getBigDecimal("amtacctdr")
          val cr = rs.getBigDecimal("amtacctcr")
          rows.put(rs.getInt("fact_acct_id"),
            (if (dr == null) BigDecimal.ZERO else dr, if (cr == null) BigDecimal.ZERO else cr))
        }
      } finally {
        DB.close(rs)
      }
    }
    rows.toMap
  }    //	queryRange

  private def sumDr(rows: Map[Int, (BigDecimal, BigDecimal)]): BigDecimal =
    rows.values.foldLeft(BigDecimal.ZERO)((acc, v) => acc.add(v._1))

  private def sumCr(rows: Map[Int, (BigDecimal, BigDecimal)]): BigDecimal =
    rows.values.foldLeft(BigDecimal.ZERO)((acc, v) => acc.add(v._2))

  /** Two result sets agree when they hold the same ids and the same totals. */
  private def sameResult(a: Map[Int, (BigDecimal, BigDecimal)],
      b: Map[Int, (BigDecimal, BigDecimal)]): Boolean =
    a.keySet == b.keySet && sumDr(a).compareTo(sumDr(b)) == 0 && sumCr(a).compareTo(sumCr(b)) == 0

  private def singleInt(sql: String, params: Array[AnyRef]): Int = {
    var value = 0
    Trx.run { _ =>
      val rs = execQuery(sql, params)
      try {
        if (!rs.next())
          throw new AssertionError("query returned no row: " + sql)
        value = rs.getInt(1)
      } finally {
        DB.close(rs)
      }
    }
    if (value <= 0)
      throw new AssertionError("expected a positive id from: " + sql)
    value
  }    //	singleInt

  private def plusDays(ts: Timestamp, days: Int): Timestamp = {
    val cal = Calendar.getInstance()
    cal.setTime(ts)
    cal.add(Calendar.DAY_OF_YEAR, days)
    new Timestamp(cal.getTimeInMillis)
  }    //	plusDays

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
}    //	ValidateFinReportDateAcctRange
