/** Copyright (C) 2003-2026, e-Evolution Consultants S.A. , http://www.e-evolution.com
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU Affero General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Affero General Public License for more details.
  * You should have received a copy of the GNU Affero General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
  * Created by victor.perez@e-evolution.com , www.e-evolution.com
  */

package org.eevolution.test

import java.sql.{Connection, PreparedStatement, ResultSet, Timestamp}

import org.compiere.model.MQuery
import org.compiere.print.{DataEngine, MPrintFormat, PrintData, PrintDataElement}
import org.compiere.util.{DB, Env, Language, Trx}
import org.postgresql.PGStatement
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters._

/** Does a report with bind parameters run faster than the same report with the values written in the SQL text?
  *
  * The report data path is the real one (`DataEngine` with `PrintData.getSQLParameters`). The baseline of before F1 is
  * the same restriction written as literals, so every value is a new SQL text.
  *
  * The scenario that compares the rows always runs. The timing scenarios run only with `F1_BENCHMARK=Y`; otherwise they
  * are canceled, so the timing never makes the suite flaky. Each series pins one connection (a transaction of its own,
  * read only, rolled back) so that the pgjdbc prepareThreshold counts the executions of one statement on one connection,
  * as the pool does for a user that repeats a report. The server settings are never changed.
  */
class ValidateReportBindPerformance extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val English = "en_US"
  private val ClientId = 11
  private val OrderFormat = 1000003 // GardenWorld -> Order, C_Order
  private val AccountingFormat = 1000384 // GardenWorld -> Accounting Entry Details, RV_Fact_Acct
  private val WarmUps = 10
  private val Executions = 50
  private val PlanSamples = 20

  private def isBenchmark: Boolean = sys.env.get("F1_BENCHMARK").contains("Y")

  private def language = Language.getLanguage(English)

  private def check(condition: Boolean, message: => String = "assertion failed"): Unit =
    if (!condition) fail(message)

  private def format(id: Int): MPrintFormat = MPrintFormat.get(Env.getCtx, id, true)

  private def read(formatId: Int, query: MQuery, planCache: Option[Boolean] = None, transactionName: String = null): PrintData = {
    val engine = new DataEngine(language, transactionName)
    planCache.foreach(flag => engine.setPlanCache(flag))
    engine.getPrintData(Env.getCtx, format(formatId), query, false, 0)
  }

  /** SQL columns and every cell in order: what must not change */
  private def content(printData: PrintData): Vector[Vector[String]] =
    (0 until printData.getRowCount).map { row =>
      printData.setRowIndex(row)
      (0 until printData.getNodeCount).map { index =>
        printData.getNode(index) match {
          case element: PrintDataElement => s"${element.getColumnName}|${element.getValueKey}|${element.getValue}|${element.getValueAsString}"
          case other => "#" + other
        }
      }.toVector
    }.toVector

  private def queryValues(sql: String): Vector[AnyRef] = {
    val statement = DB.prepareStatement(sql, null)
    try {
      val resultSet = statement.executeQuery()
      try {
        val values = ArrayBuffer.empty[AnyRef]
        while (resultSet.next()) values += resultSet.getObject(1)
        values.toVector
      } finally resultSet.close()
    } finally statement.close()
  }

  private lazy val orderIds: Vector[Integer] =
    queryValues(s"SELECT C_Order_ID FROM C_Order WHERE AD_Client_ID=$ClientId ORDER BY 1").map(value => Integer.valueOf(value.asInstanceOf[Number].intValue))

  private lazy val accountIds: Vector[Integer] =
    queryValues(s"SELECT DISTINCT Account_ID FROM Fact_Acct WHERE AD_Client_ID=$ClientId ORDER BY 1").map(value => Integer.valueOf(value.asInstanceOf[Number].intValue))

  private lazy val firstAccountingDate: Timestamp =
    queryValues(s"SELECT MIN(DateAcct) FROM Fact_Acct WHERE AD_Client_ID=$ClientId").head.asInstanceOf[Timestamp]

  /** A report with a restriction that takes another value on every execution */
  private case class ReportCase(label: String, formatId: Int, tableName: String, restrictions: String, queryFor: Int => MQuery)

  private lazy val cases: Vector[ReportCase] = Vector(
    ReportCase("Order print format, C_Order_ID = each order", OrderFormat, "C_Order", "C_Order_ID", index => {
      val query = new MQuery("C_Order")
      query.addRestriction("C_Order_ID", MQuery.EQUAL, orderIds(index % orderIds.size))
      query
    }),
    ReportCase("Accounting entry details (RV_Fact_Acct), Account_ID and DateAcct range", AccountingFormat, "RV_Fact_Acct", "Account_ID, DateAcct", index => {
      val from = new Timestamp(firstAccountingDate.getTime + (index * 13L % 600L) * 86400000L)
      val to = new Timestamp(from.getTime + 120L * 86400000L)
      val query = new MQuery("RV_Fact_Acct")
      query.addRestriction("Account_ID", MQuery.EQUAL, accountIds(index % accountIds.size))
      query.addRangeRestriction("DateAcct", from, to)
      query
    }))

  /** The same restriction as raw text: what the report did before F1 */
  private def literalQuery(testCase: ReportCase, index: Int): MQuery = {
    val query = testCase.queryFor(index)
    query.setTableName(testCase.tableName)
    val literal = new MQuery(testCase.tableName)
    literal.addRestriction(query.getWhereClause(true))
    literal
  }

  Feature("The report with bind parameters returns the rows of the literal statement for every measured value") {
    cases.foreach { testCase =>
      Scenario(s"Same rows for ${Executions + WarmUps} values: ${testCase.label}") {
        Given(s"print format ${testCase.formatId} with a restriction on ${testCase.restrictions}")
        var nonEmpty = 0
        (0 until Executions + WarmUps).foreach { index =>
          val actual = read(testCase.formatId, testCase.queryFor(index))
          val expected = read(testCase.formatId, literalQuery(testCase, index))
          check(actual.getSQLParameters.size > 0, s"value $index: the statement is not bound")
          check(expected.getSQLParameters.isEmpty, s"value $index: the literal reference has parameters")
          check(actual.getRowCount == expected.getRowCount, s"value $index: ${actual.getRowCount} rows <> ${expected.getRowCount}")
          check(content(actual) == content(expected), s"value $index: content differs")
          if (expected.getRowCount > 0) nonEmpty += 1
        }
        Then("bound and literal return the same content")
        info(s"${testCase.label}: $nonEmpty of ${Executions + WarmUps} values return rows, all identical")
        check(nonEmpty > 0, s"${testCase.label}: no value returns a row; the check would prove nothing")
      }
    }
  }

  // ---- benchmark ----

  private case class Series(name: String, times: Vector[Double]) {
    private def sorted = times.sorted
    def median: Double = { val values = sorted; (values((values.size - 1) / 2) + values(values.size / 2)) / 2 }
    def p95: Double = { val values = sorted; values(math.ceil(0.95 * values.size).toInt - 1) }
    def text: String = f"$median%8.2f / $p95%8.2f"
  }

  private case class PlanEvidence(statements: Int, genericPlans: Int, customPlans: Int)

  private case class Outcome(label: String, series: Vector[Series], plan: Double, execution: Double, evidence: Map[String, PlanEvidence])

  private val outcomes = ArrayBuffer.empty[Outcome]

  private def withConnection[A](body: (String, Connection) => A): A = {
    val transactionName = Trx.createTrxName("ReportBench_")
    val transaction = Trx.get(transactionName, true)
    try {
      DB.executeUpdateEx("DEALLOCATE ALL", transactionName)
      body(transactionName, transaction.getConnection)
    } finally {
      transaction.rollback()
      transaction.close()
    }
  }

  private def measure(name: String)(execute: Int => Unit): Series = {
    val times = ArrayBuffer.empty[Double]
    (0 until WarmUps + Executions).foreach { index =>
      val start = System.nanoTime()
      execute(index)
      val elapsed = (System.nanoTime() - start) / 1000000.0
      if (index >= WarmUps) times += elapsed
    }
    Series(name, times.toVector)
  }

  private def drain(resultSet: ResultSet): Int = {
    val columns = resultSet.getMetaData.getColumnCount
    var rows = 0
    while (resultSet.next()) {
      (1 to columns).foreach(column => resultSet.getObject(column))
      rows += 1
    }
    rows
  }

  private def evidence(connection: Connection, formatId: Int): PlanEvidence = {
    val statement = connection.createStatement()
    try {
      val resultSet = statement.executeQuery(
        s"SELECT COUNT(*), COALESCE(SUM(generic_plans),0), COALESCE(SUM(custom_plans),0) FROM pg_prepared_statements WHERE statement LIKE '%/* AD_PrintFormat_ID=$formatId */%'")
      try { resultSet.next(); PlanEvidence(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3)) }
      finally resultSet.close()
    } finally statement.close()
  }

  private def scalar(connection: Connection, sql: String): String = {
    val statement = connection.createStatement()
    try {
      val resultSet = statement.executeQuery(sql)
      try { resultSet.next(); resultSet.getString(1) } finally resultSet.close()
    } finally statement.close()
  }

  /** Planning and execution milliseconds of `EXPLAIN (ANALYZE, SUMMARY)` for one literal statement */
  private def explain(connection: Connection, literalSql: String): (Double, Double) = {
    val statement = connection.createStatement()
    try {
      val resultSet = statement.executeQuery("EXPLAIN (ANALYZE, SUMMARY) " + literalSql)
      try {
        var planning, execution = 0.0
        while (resultSet.next()) {
          val line = resultSet.getString(1).trim
          if (line.startsWith("Planning Time:")) planning = line.split("\\s+")(2).toDouble
          if (line.startsWith("Execution Time:")) execution = line.split("\\s+")(2).toDouble
        }
        (planning, execution)
      } finally resultSet.close()
    } finally statement.close()
  }

  private def median(values: Seq[Double]): Double = {
    val sorted = values.sorted
    (sorted((sorted.size - 1) / 2) + sorted(sorted.size / 2)) / 2
  }

  private def benchmark(testCase: ReportCase): Outcome = withConnection { (transactionName, connection) =>
    val total = WarmUps + Executions
    // the SQL and the values the engine builds for each value of the restriction
    val statements = (0 until total).map { index =>
      val printData = read(testCase.formatId, testCase.queryFor(index), transactionName = transactionName)
      (DB.getDatabase.convertStatement(printData.getSQL), printData.getSQLParameters)
    }
    val literals = statements.map { case (sql, parameters) => DB.inlineQueryParameters(sql, parameters) }
    val evidences = scala.collection.mutable.LinkedHashMap.empty[String, PlanEvidence]

    // a connection that is already warm for this report, so the first series is not the one that pays for it
    (0 until WarmUps).foreach(index => read(testCase.formatId, literalQuery(testCase, index), transactionName = transactionName))

    def engineSeries(name: String, planCache: Option[Boolean], queryFor: Int => MQuery, observe: Boolean): Series = {
      DB.executeUpdateEx("DEALLOCATE ALL", transactionName)
      val series = measure(name)(index => read(testCase.formatId, queryFor(index), planCache, transactionName))
      if (observe) evidences(name) = evidence(connection, testCase.formatId)
      series
    }

    def boundSql(name: String, isPlanCache: Boolean): Series = {
      DB.executeUpdateEx("DEALLOCATE ALL", transactionName)
      val series = measure(name) { index =>
        val (sql, parameters) = statements(index)
        val statement: PreparedStatement = connection.prepareStatement(sql)
        try {
          if (!isPlanCache) statement.unwrap(classOf[PGStatement]).setPrepareThreshold(0)
          DB.setQueryParameters(statement, parameters)
          val resultSet = statement.executeQuery()
          try drain(resultSet) finally resultSet.close()
        } finally statement.close()
      }
      evidences(name) = evidence(connection, testCase.formatId)
      series
    }

    val engineLiteral = engineSeries("engine literal (before F1)", None, index => literalQuery(testCase, index), observe = true)
    val engineBound = engineSeries("engine bound, plan cache on", Some(true), testCase.queryFor, observe = true)
    val engineBoundOff = engineSeries("engine bound, plan cache off", Some(false), testCase.queryFor, observe = true)
    val sqlBound = boundSql("SQL bound, prepareThreshold 5", isPlanCache = true)
    val sqlBoundOff = boundSql("SQL bound, prepareThreshold 0", isPlanCache = false)
    DB.executeUpdateEx("DEALLOCATE ALL", transactionName)
    val sqlLiteral = measure("SQL literal, plain Statement") { index =>
      val statement = connection.createStatement()
      try {
        val resultSet = statement.executeQuery(literals(index))
        try drain(resultSet) finally resultSet.close()
      } finally statement.close()
    }

    val splits = (WarmUps until WarmUps + PlanSamples).map(index => explain(connection, literals(index)))
    Outcome(testCase.label, Vector(engineLiteral, engineBound, engineBoundOff, sqlLiteral, sqlBound, sqlBoundOff),
      median(splits.map(_._1)), median(splits.map(_._2)), evidences.toMap)
  }

  Feature("Bind parameters make the report statement cheaper to repeat (benchmark, F1_BENCHMARK=Y)") {
    Scenario("The server and the driver settings that decide the plan reuse") {
      assume(isBenchmark, "set F1_BENCHMARK=Y to run the timing scenarios")
      assume(DB.isPostgreSQL, "pg_prepared_statements needs PostgreSQL")
      withConnection { (_, connection) =>
        val driver = connection.getMetaData.getDriverVersion
        val threshold = connection.unwrap(classOf[org.postgresql.PGConnection]).getPrepareThreshold
        val lines = Seq(
          s"server_version=${scalar(connection, "SHOW server_version")}",
          s"plan_cache_mode=${scalar(connection, "SHOW plan_cache_mode")}",
          s"jit=${scalar(connection, "SHOW jit")}",
          s"pgjdbc=$driver prepareThreshold=$threshold",
          s"REPORT_PLAN_CACHE=${new DataEngine(language, null).isPlanCache(Env.getCtx)}",
          s"C_Order rows=${orderIds.size} Fact_Acct accounts=${accountIds.size} from ${firstAccountingDate}",
          s"RV_Fact_Acct rows=${scalar(connection, s"SELECT COUNT(*) FROM RV_Fact_Acct WHERE AD_Client_ID=$ClientId")}")
        lines.foreach(line => println("BENCH-ENV " + line))
        lines.foreach(line => info(line))
      }
    }

    cases.foreach { testCase =>
      Scenario(s"Bound against literal: ${testCase.label}") {
        assume(isBenchmark, "set F1_BENCHMARK=Y to run the timing scenarios")
        assume(DB.isPostgreSQL, "pg_prepared_statements needs PostgreSQL")
        Given(s"$Executions measured executions after $WarmUps warm-up ones, each with another value")
        val outcome = benchmark(testCase)
        outcomes += outcome
        Then("the milliseconds of each path are reported")
        println(s"BENCH-CASE ${outcome.label}")
        outcome.series.foreach(series => println(f"BENCH   ${series.name}%-34s median / p95 ms: ${series.text}"))
        println(f"BENCH   EXPLAIN (ANALYZE, SUMMARY) literal, median of $PlanSamples: planning ${outcome.plan}%.3f ms, execution ${outcome.execution}%.3f ms")
        outcome.evidence.foreach { case (name, found) =>
          println(s"BENCH   pg_prepared_statements after '$name': statements=${found.statements} generic_plans=${found.genericPlans} custom_plans=${found.customPlans}")
        }
        outcome.series.foreach(series => info(f"${series.name}%-34s ${series.text}"))
        info(f"planning ${outcome.plan}%.3f ms, execution ${outcome.execution}%.3f ms")
      }
    }
  }
}
