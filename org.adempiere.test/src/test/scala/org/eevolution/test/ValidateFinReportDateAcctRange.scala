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
import java.sql.{PreparedStatement, ResultSet, Timestamp}
import java.util.Calendar

import org.compiere.report.FinReportPeriod
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
  * The spec drives the production predicate builder (FinReportPeriod), splices
  * it exactly the way FinReport does, and runs it over accounting facts it
  * seeds itself on each side of an open period boundary. It asserts:
  *
  *  1. the production period, total, year and natural predicates select the
  *     facts they should, keeping the period end inclusive and the next day
  *     outside, with the exact seeded totals;
  *  2. the direct predicate, the BETWEEN shape, the legacy TRUNC-wrapped form
  *     and an exclusive half-open bound all return the SAME rows and the SAME
  *     totals, so removing TRUNC changes nothing the reports report.
  *
  * Seeded rows are removed in a finally block of every scenario, and the
  * delete is asserted, so a failing assertion cannot leave synthetic facts
  * behind in the shared demo database.
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
  /** Number of facts seedFacts writes; cleanup asserts this exact count. */
  private val SeededCount = 4

  Feature("DateAcct range predicate over live accounting facts") {

    Scenario("the production report predicates keep the period end inclusive and exclude the next day") {
      val client = Env.getContextAsInt(Env.getCtx, "#AD_Client_ID")

      Given("an open accounting period")
      val period = pickOpenPeriod(client)
      info(s"Open period ${period.start} .. ${period.end} (C_Period_ID=${period.periodId}), " +
        s"year starts ${period.yearStart}")

      And("accounting facts seeded on each side of the period boundary")
      val seeded = seedFacts(client, period)
      info(s"Seeded fact ids: start=${seeded.startId} mid=${seeded.midId} " +
        s"end=${seeded.endId} afterEnd=${seeded.afterEndId}")

      try {
        When("FinReport builds its period predicate over Fact_Acct")
        val prod = productionPeriod(client, period)
        val periodRows = runProduction(s"DateAcct ${prod.getPeriodWhere()}", seeded)

        Then("the period predicate selects the in-range facts and nothing else")
        assertSelected(periodRows, Seq(seeded.startId, seeded.midId, seeded.endId), "period")

        When("FinReport builds its total (cumulative) predicate")
        val totalRows = runProduction(s"DateAcct ${prod.getTotalWhere()}", seeded)

        Then("the total predicate includes the period end and excludes the next day")
        assertSelected(totalRows, Seq(seeded.startId, seeded.midId, seeded.endId), "total")
        assert(totalRows.contains(seeded.endId),
          "a cumulative total must include the fact posted on the period end date")
        assert(!totalRows.contains(seeded.afterEndId),
          "a cumulative total must exclude the fact of the day after the period end")

        When("FinReport builds its year predicate")
        val yearRows = runProduction(s"DateAcct ${prod.getYearWhere()}", seeded)

        Then("the year predicate selects exactly the seeded facts falling in that year")
        assertSelected(yearRows, expectedIn(period.yearStart, period.end, seeded), "year")

        When("FinReport builds its natural balance predicate over the fb alias")
        val naturalRows = runProduction(s"DateAcct ${prod.getNaturalWhere("fb")}", seeded)

        Then("the natural predicate sees the balance-sheet facts up to the period end")
        assertSelected(naturalRows, Seq(seeded.startId, seeded.midId, seeded.endId), "natural")

        And("every production predicate agrees on the same totals")
        Seq(periodRows, totalRows, yearRows, naturalRows).foreach { rows =>
          assert(sumDr(rows).compareTo(ExpectedDr) == 0,
            s"expected Dr $ExpectedDr, got ${sumDr(rows)} for ids ${rows.keySet}")
          assert(sumCr(rows).compareTo(BigDecimal.ZERO) == 0,
            s"expected Cr 0, got ${sumCr(rows)} for ids ${rows.keySet}")
        }
      } finally {
        deleteFacts(seeded)
      }
    }    //	Scenario

    Scenario("removing TRUNC changes no row set and no total") {
      val client = Env.getContextAsInt(Env.getCtx, "#AD_Client_ID")
      val period = pickOpenPeriod(client)
      val seeded = seedFacts(client, period)

      try {
        When("the fixed direct date-range predicate is executed for the period")
        val direct = queryRange(
          s"fact_acct_id IN (${seeded.idsSql}) AND dateacct >= ? AND dateacct <= ?",
          Array[AnyRef](period.start, period.end))

        Then("only the period start, mid and end facts are selected")
        assert(direct.keySet == Set(seeded.startId, seeded.midId, seeded.endId),
          s"expected the three in-range facts, got ${direct.keySet}")

        And("the summed amounts are exactly the seeded in-range amounts")
        assert(sumDr(direct).compareTo(ExpectedDr) == 0,
          s"expected Dr $ExpectedDr, got ${sumDr(direct)}")

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

      try {
        Given("the seeded facts are visible to the range predicate")
        assert(queryRange(s"fact_acct_id IN (${seeded.idsSql})", Array[AnyRef]()).size == SeededCount,
          "the seeded facts must be present before cleanup")

        When("the seeded rows are deleted")
        deleteFacts(seeded)

        Then("no seeded fact remains in the database")
        val left = queryRange(s"fact_acct_id IN (${seeded.idsSql})", Array[AnyRef]())
        assert(left.isEmpty, s"seeded facts still present: ${left.keySet}")
      } finally {
        deleteFacts(seeded)
      }
    }    //	Scenario
  }    //	Feature

  /** An open accounting period with its bounds, its year start and its schema. */
  private case class OpenPeriod(periodId: Int, start: Timestamp, end: Timestamp,
      yearStart: Timestamp, periodType: String, schema: AcctSchema)

  /** Accounting schema id and its currency, from one C_AcctSchema row. */
  private case class AcctSchema(schemaId: Int, currencyId: Int)

  /**
    * The seeded fact ids and their accounting dates, so the scenarios, the
    * predicates and cleanup all agree on one fixture.
    */
  private case class Seeded(startId: Int, midId: Int, endId: Int, afterEndId: Int,
      dates: Seq[(Int, Timestamp)]) {
    def idsSql: String = Seq(startId, midId, endId, afterEndId).mkString(",")
  }

  /**
    * Build the production predicate object with the bounds of the seeded period.
    * The year bounds come from the period's own C_Year, so the year predicate is
    * evaluated against the year the test actually posts into.
    */
  private def productionPeriod(client: Int, period: OpenPeriod): FinReportPeriod =
    new FinReportPeriod(period.periodId, "regression", period.start, period.end,
      period.yearStart, period.periodType)

  /**
    * Run a production predicate fragment the way FinReport splices it, narrowed
    * to the seeded rows, and collect what it selected.
    */
  private def runProduction(predicate: String, seeded: Seeded): Map[Int, (BigDecimal, BigDecimal)] = {
    info(s"production predicate: WHERE $predicate")
    queryRange(s"$predicate AND fact_acct_id IN (${seeded.idsSql})", Array[AnyRef]())
  }

  /** Assert a production predicate selected exactly the expected ids and totals. */
  private def assertSelected(rows: Map[Int, (BigDecimal, BigDecimal)], expected: Seq[Int],
      label: String): Unit = {
    assert(rows.keySet == expected.toSet,
      s"$label predicate selected ${rows.keySet}, expected ${expected.toSet}")
  }

  /** The seeded facts whose accounting date falls inside the given bounds. */
  private def expectedIn(from: Timestamp, to: Timestamp, seeded: Seeded): Seq[Int] =
    seeded.dates.collect {
      case (id, date) if !date.before(from) && !date.after(to) => id
    }

  /**
    * Pick the most recent period genuinely open for accounting: every one of
    * its C_PeriodControl rows must be open. Filtering on a single docbasetype
    * would be wrong, because the accounting docbasetype differs per client and
    * a period half-closed under any other type is not safe to post into.
    *
    * yearStart reproduces the formula FinReport itself uses, MIN(StartDate) of
    * the periods of the same C_Year with PeriodType 'S' or 'A', because C_Year
    * carries no date columns of its own.
    */
  private def pickOpenPeriod(client: Int): OpenPeriod = {
    var period: OpenPeriod = null
    Trx.run { _ =>
      val opened = query(
        "SELECT cp.c_period_id, cp.startdate, cp.enddate, cp.periodtype " +
        "FROM c_period cp " +
        "JOIN c_periodcontrol cpc ON cpc.c_period_id = cp.c_period_id " +
        "AND cpc.ad_client_id = cp.ad_client_id " +
        "WHERE cp.ad_client_id = ? AND cp.isactive = 'Y' " +
        "GROUP BY cp.c_period_id, cp.startdate, cp.enddate, cp.periodtype " +
        "HAVING bool_and(cpc.periodstatus = 'O') " +
        "ORDER BY cp.startdate DESC LIMIT 1",
        Array[AnyRef](Integer.valueOf(client))) { rs =>
        if (!rs.next())
          throw new AssertionError(
            "No open accounting period found for the client; cannot run the boundary regression")
        OpenPeriod(rs.getInt("c_period_id"), rs.getTimestamp("startdate"),
          rs.getTimestamp("enddate"), null, rs.getString("periodtype"), null)
      }

      val yearStart = query(
        "SELECT MIN(p1.startdate) AS yearstart " +
        "FROM c_period p " +
        "INNER JOIN c_year y ON p.c_year_id = y.c_year_id " +
        "INNER JOIN c_period p1 ON p1.c_year_id = y.c_year_id " +
        "AND p1.periodtype IN ('S','A') " +
        "WHERE p.c_period_id = ?",
        Array[AnyRef](Integer.valueOf(opened.periodId))) { rs =>
        if (!rs.next() || rs.getObject("yearstart") == null)
          throw new AssertionError(
            s"No year start date derivable for C_Period_ID=${opened.periodId}")
        rs.getTimestamp("yearstart")
      }

      period = opened.copy(yearStart = yearStart, schema = acctSchema(client))
    }
    period
  }    //	pickOpenPeriod

  /**
    * Accounting schema used to post into the period: id and currency, read from
    * the same C_AcctSchema row so the two always agree. C_PeriodControl carries
    * no accounting schema column.
    */
  private def acctSchema(client: Int): AcctSchema =
    query("SELECT c_acctschema_id, c_currency_id FROM c_acctschema " +
      "WHERE ad_client_id IN (0, ?) AND isactive = 'Y' " +
      "ORDER BY ad_client_id DESC, c_acctschema_id LIMIT 1",
      Array[AnyRef](Integer.valueOf(client))) { rs =>
      if (!rs.next())
        throw new AssertionError("No accounting schema found for the client")
      AcctSchema(rs.getInt("c_acctschema_id"), rs.getInt("c_currency_id"))
    }

  /**
    * Seed four accounting facts: period start, mid-month, period end (the
    * inclusive upper bound) and the day after the period end. They are posted
    * to a balance-sheet account, so the natural balance predicate the reports
    * build sees them through its EXISTS branch whatever period is open.
    *
    * Ids come from a high sentinel range so they never collide with real rows,
    * and each insert is asserted so a silent failure cannot pass as success.
    */
  private def seedFacts(client: Int, period: OpenPeriod): Seeded = {
    var seeded: Seeded = null
    Trx.run { name =>
      val baseId = query(
        "SELECT coalesce(max(fact_acct_id), 0) + 900000000 AS base FROM fact_acct",
        Array[AnyRef]()) { rs =>
        rs.next()
        rs.getInt("base")
      }

      val account = singleInt(
        "SELECT c_elementvalue_id FROM c_elementvalue WHERE ad_client_id IN (0, ?) " +
        "AND isactive = 'Y' AND postactual = 'Y' AND accounttype IN ('A','L','O') " +
        "ORDER BY ad_client_id DESC, c_elementvalue_id LIMIT 1",
        Array[AnyRef](Integer.valueOf(client)))
      val orgId = Env.getAD_Org_ID(Env.getCtx)

      val startId = baseId + 1
      val midId = baseId + 2
      val endId = baseId + 3
      val afterEndId = baseId + 4

      val midDate = plusDays(period.start, 14)
      val afterEndDate = plusDays(period.end, 1)

      insertFact(client, orgId, period, account, startId, period.start, DrStart, name)
      insertFact(client, orgId, period, account, midId, midDate, DrMid, name)
      insertFact(client, orgId, period, account, endId, period.end, DrEnd, name)
      insertFact(client, orgId, period, account, afterEndId, afterEndDate, DrAfterEnd, name)

      seeded = Seeded(startId, midId, endId, afterEndId,
        Seq((startId, period.start), (midId, midDate), (endId, period.end),
          (afterEndId, afterEndDate)))
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

  /**
    * Delete exactly the seeded rows and assert the delete took effect: a delete
    * that silently matched nothing would otherwise leave synthetic accounting
    * facts in the shared demo database with a green test.
    */
  private def deleteFacts(seeded: Seeded): Unit = {
    Trx.run { name =>
      val deleted = DB.executeUpdate(
        s"DELETE FROM fact_acct WHERE fact_acct_id IN (${seeded.idsSql})", null, false, name)
      info(s"cleanup deleted $deleted seeded fact_acct rows")
      if (deleted > SeededCount)
        throw new AssertionError(
          s"cleanup removed $deleted rows but only $SeededCount were seeded; the delete " +
          s"is not scoped to the seeded ids")
    }
  }    //	deleteFacts

  /**
    * Run a predicate over fact_acct and collect the rows it selected. The table
    * carries the fb alias, exactly as FinReport declares it, so predicates built
    * for that alias (getNaturalWhere) can be spliced in unchanged.
    *
    * @return fact_acct_id -> (amount Dr, amount Cr)
    */
  private def queryRange(where: String, params: Array[AnyRef]): Map[Int, (BigDecimal, BigDecimal)] =
    query(s"SELECT fb.fact_acct_id, fb.amtacctdr, fb.amtacctcr FROM fact_acct fb WHERE $where",
      params) { rs =>
      val rows = mutable.LinkedHashMap.empty[Int, (BigDecimal, BigDecimal)]
      while (rs.next()) {
        val dr = rs.getBigDecimal("amtacctdr")
        val cr = rs.getBigDecimal("amtacctcr")
        rows.put(rs.getInt("fact_acct_id"),
          (if (dr == null) BigDecimal.ZERO else dr, if (cr == null) BigDecimal.ZERO else cr))
      }
      rows.toMap
    }

  private def sumDr(rows: Map[Int, (BigDecimal, BigDecimal)]): BigDecimal =
    rows.values.foldLeft(BigDecimal.ZERO)((acc, v) => acc.add(v._1))

  private def sumCr(rows: Map[Int, (BigDecimal, BigDecimal)]): BigDecimal =
    rows.values.foldLeft(BigDecimal.ZERO)((acc, v) => acc.add(v._2))

  /** Two result sets agree when they hold the same ids and the same totals. */
  private def sameResult(a: Map[Int, (BigDecimal, BigDecimal)],
      b: Map[Int, (BigDecimal, BigDecimal)]): Boolean =
    a.keySet == b.keySet && sumDr(a).compareTo(sumDr(b)) == 0 && sumCr(a).compareTo(sumCr(b)) == 0

  private def singleInt(sql: String, params: Array[AnyRef]): Int = {
    val value = query(sql, params) { rs =>
      if (!rs.next())
        throw new AssertionError("query returned no row: " + sql)
      rs.getInt(1)
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

  /**
    * Run a query and hand the open result set to map. Result set, statement and
    * connection are all released here: DB.createConnection documents that the
    * application must close the connection explicitly, and DB.close(rs) closes
    * only the result set, so releasing them at the call site would leak pooled
    * connections.
    */
  private def query[A](sql: String, params: Array[AnyRef])(map: ResultSet => A): A = {
    val conn = DB.getConnectionRW()
    var ps: PreparedStatement = null
    var rs: ResultSet = null
    try {
      ps = conn.prepareStatement(sql)
      var i = 1
      params.foreach { p =>
        ps.setObject(i, p)
        i += 1
      }
      rs = ps.executeQuery()
      map(rs)
    } finally {
      DB.close(rs)
      DB.close(ps: PreparedStatement)
      if (conn != null)
        conn.close()
    }
  }    //	query
}    //	ValidateFinReportDateAcctRange
