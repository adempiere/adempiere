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

import java.sql.Timestamp
import java.util.logging.{Handler, Level, LogRecord}

import org.compiere.model.{MPInstance, MPInstancePara, MQuery, MSysConfig}
import org.compiere.print.{DataEngine, MPrintFormat, PrintData, PrintDataElement}
import org.compiere.util.{CLogger, DB, Env, Language}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters._

/** DataEngine runs the restrictions of the MQuery of a report as bind parameters (`?` and ordered values) and the
  * report returns what the literal statement returned.
  *
  * The reference is the same restriction written as a raw text restriction (`getWhereClause(true)` of the original
  * query), which is the literal path of before. Plan reuse (REPORT_PLAN_CACHE) is observed through
  * pg_prepared_statements of the connection of the test transaction, never through the driver internals; the
  * scenarios that need PostgreSQL are cancelled on another database.
  */
class ValidateReportBindParameters extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val English = "en_US"
  private val Runs = 7 // pgjdbc server-side prepares a statement at the 5th execution (prepareThreshold=5)
  private val OrderFormat = 1000003 // C_Order
  private val ProductFormat = 1000000 // M_Product
  private val InvoiceFormat = 1000390 // RV_C_Invoice
  private val InvoiceProcess = 127 // Invoice Details: DateInvoiced range
  private val ClientId = 11

  private def language = Language.getLanguage(English)

  private def check(condition: Boolean, message: => String = "assertion failed"): Unit =
    if (!condition) fail(message)

  private def format(id: Int): MPrintFormat = MPrintFormat.get(Env.getCtx, id, true)

  private def engine(transactionName: String, join: Boolean, planCache: Option[Boolean]): DataEngine = {
    val engine = new DataEngine(language, transactionName)
    engine.setLookupJoin(join)
    planCache.foreach(flag => engine.setPlanCache(flag))
    engine
  }

  private def read(formatId: Int, query: MQuery, join: Boolean = true, planCache: Option[Boolean] = None, transactionName: String = null): PrintData =
    engine(transactionName, join, planCache).getPrintData(Env.getCtx, format(formatId), query, false, 0)

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

  private def placeholders(sql: String): Int = sql.count(_ == '?')

  /** A restriction scenario: the factory builds a fresh query on every call, because the engine sets its table name */
  private case class Case(label: String, formatId: Int, table: String, original: () => MQuery)

  /** The same restriction as raw text: today's literal statement */
  private def literalQuery(testCase: Case): MQuery = {
    val query = testCase.original()
    query.setTableName(testCase.table)
    val literal = new MQuery(testCase.table)
    literal.addRestriction(query.getWhereClause(true))
    literal
  }

  private def committedInstance(processId: Int, parameters: (MPInstancePara => Unit)*): MQuery = {
    // MQuery.get reads the instance without a transaction: commit it and delete it right after
    val instance = new MPInstance(Env.getCtx, processId, 0)
    instance.saveEx()
    val id = Integer.valueOf(instance.getAD_PInstance_ID)
    try {
      parameters.zipWithIndex.foreach { case (set, index) =>
        val para = new MPInstancePara(instance, (index + 1) * 10)
        set(para)
        para.saveEx()
      }
      MQuery.get(Env.getCtx, instance.getAD_PInstance_ID, "RV_C_Invoice")
    } finally {
      DB.executeUpdateEx("DELETE FROM AD_PInstance_Para WHERE AD_PInstance_ID=?", Array[AnyRef](id), null)
      DB.executeUpdateEx("DELETE FROM AD_PInstance WHERE AD_PInstance_ID=?", Array[AnyRef](id), null)
    }
  }

  private def invoiceDays: Vector[Timestamp] = {
    val statement = DB.prepareStatement(s"SELECT DISTINCT DateInvoiced FROM C_Invoice WHERE AD_Client_ID=$ClientId ORDER BY 1", null)
    val days = ArrayBuffer.empty[Timestamp]
    try {
      val resultSet = statement.executeQuery()
      try while (resultSet.next()) days += resultSet.getTimestamp(1)
      finally resultSet.close()
    } finally statement.close()
    days.toVector
  }

  private def mostOrderedPartner: Integer = {
    val statement = DB.prepareStatement(s"SELECT C_BPartner_ID FROM C_Order WHERE AD_Client_ID=$ClientId GROUP BY C_BPartner_ID ORDER BY COUNT(*) DESC, C_BPartner_ID", null)
    try {
      val resultSet = statement.executeQuery()
      try { resultSet.next(); Integer.valueOf(resultSet.getInt(1)) }
      finally resultSet.close()
    } finally statement.close()
  }

  private lazy val cases: Vector[Case] = {
    val days = invoiceDays
    assert(days.size >= 6, "GardenWorld has too few invoice dates for the range checks")
    val partner = mostOrderedPartner
    Vector(
      Case("EQUAL on an ID", OrderFormat, "C_Order", () => {
        val query = new MQuery("C_Order")
        query.addRestriction("C_BPartner_ID", MQuery.EQUAL, partner)
        query
      }),
      Case("LIKE on a name", ProductFormat, "M_Product", () => {
        val query = new MQuery("M_Product")
        query.addRestriction("Name", MQuery.LIKE, "%e%")
        query
      }),
      Case("BETWEEN on numbers", OrderFormat, "C_Order", () => {
        val query = new MQuery("C_Order")
        query.addRangeRestriction("GrandTotal", Integer.valueOf(100), Integer.valueOf(1000))
        query
      }),
      Case("EQUAL and LIKE in an OR group", OrderFormat, "C_Order", () => {
        val query = new MQuery("C_Order")
        query.addRestriction("C_BPartner_ID", MQuery.EQUAL, partner, null, null, true, 1)
        query.addRestriction("DocumentNo", MQuery.LIKE, "1%", null, null, false, 1)
        query.addRestriction("IsSOTrx", MQuery.EQUAL, java.lang.Boolean.TRUE)
        query
      }),
      Case("date range of a process instance", InvoiceFormat, "RV_C_Invoice", () =>
        committedInstance(InvoiceProcess, para => {
          para.setParameterName("DateInvoiced")
          para.setP_Date(days(1))
          para.setP_Date_To(days(4))
        }))
    )
  }

  Feature("The report with bind parameters returns the rows of the literal statement") {
    Seq(true, false).foreach { join =>
      Scenario(s"Same rows, columns and order for every restriction (lookup join $join)") {
        cases.foreach { testCase =>
          Given(s"${testCase.label} on print format ${testCase.formatId}")
          val everything = read(testCase.formatId, new MQuery(testCase.table), join).getRowCount
          val expected = read(testCase.formatId, literalQuery(testCase), join)
          val actual = read(testCase.formatId, testCase.original(), join)
          Then("the statement is bound and the literal one is not")
          check(actual.getSQLParameters.size > 0, s"${testCase.label}: no parameters, the restriction was not bound")
          check(expected.getSQLParameters.isEmpty, s"${testCase.label}: the literal reference has parameters")
          And("the rows are the same")
          check(expected.getRowCount > 0, s"${testCase.label}: the literal statement returns no row; the check would prove nothing")
          check(expected.getRowCount < everything, s"${testCase.label}: the restriction removes no row of $everything; the check would prove nothing")
          check(actual.getRowCount == expected.getRowCount, s"${testCase.label}: ${actual.getRowCount} rows <> ${expected.getRowCount}")
          check(content(actual) == content(expected), s"${testCase.label}: content differs")
          check(actual.getColumnInfo.map(_.getColumnName).toVector == expected.getColumnInfo.map(_.getColumnName).toVector, s"${testCase.label}: columns differ")
          info(s"${testCase.label}: ${actual.getRowCount} of $everything rows identical")
        }
      }
    }
  }

  Feature("The statement holds placeholders and PrintData the matching values") {
    Scenario("One value per placeholder, in order, and the literal text only for the reference") {
      cases.foreach { testCase =>
        val actual = read(testCase.formatId, testCase.original())
        val expected = read(testCase.formatId, literalQuery(testCase))
        val sql = actual.getSQL
        info(s"${testCase.label}: ${placeholders(sql)} placeholders, parameters ${actual.getSQLParameters.asScala.toVector}")
        check(placeholders(sql) > 0, s"${testCase.label}: the SQL has no placeholder")
        check(placeholders(sql) == actual.getSQLParameters.size, s"${testCase.label}: ${placeholders(sql)} placeholders, ${actual.getSQLParameters.size} values")
        check(placeholders(expected.getSQL) == 0, s"${testCase.label}: the literal reference has a placeholder")
      }
    }

    Scenario("A report without restrictions has no parameter") {
      val printData = read(OrderFormat, new MQuery())
      assert(printData.getSQLParameters.isEmpty && placeholders(printData.getSQL) == 0)
    }

    Scenario("The values of the first restriction come before those of the second, whatever the access restriction adds") {
      val partner = mostOrderedPartner
      val query = new MQuery("C_Order")
      query.addRestriction("C_BPartner_ID", MQuery.EQUAL, partner)
      query.addRestriction("DocumentNo", MQuery.LIKE, "1%")
      val parameters = read(OrderFormat, query).getSQLParameters.asScala.toVector
      assert(parameters == Vector(partner, "1%"), parameters.toString)
    }

    Scenario("The final SQL is logged once, with its parameters") {
      val logger = CLogger.getCLogger(classOf[DataEngine])
      val messages = ArrayBuffer.empty[String]
      val handler = new Handler {
        override def publish(record: LogRecord): Unit = messages.synchronized(messages += String.valueOf(record.getMessage))
        override def flush(): Unit = ()
        override def close(): Unit = ()
      }
      val level = logger.getLevel
      logger.addHandler(handler)
      logger.setLevel(Level.FINE)
      try {
        val query = new MQuery("C_Order")
        query.addRestriction("C_BPartner_ID", MQuery.EQUAL, mostOrderedPartner)
        val printData = read(OrderFormat, query)
        val logged = messages.synchronized(messages.toVector).filter(_.contains(printData.getSQL))
        assert(logged.size == 1, s"${logged.size} records contain the SQL")
        assert(logged.head.contains(printData.getSQLParameters.toString), s"the record has no parameters: ${logged.head.takeRight(120)}")
      } finally {
        logger.removeHandler(handler)
        logger.setLevel(level)
      }
    }
  }

  private def preparedStatements(transactionName: String, like: String): Int =
    DB.getSQLValue(transactionName, "SELECT COUNT(*) FROM pg_prepared_statements WHERE statement LIKE ?", like)

  private val Tag = s"%/* AD_PrintFormat_ID=$OrderFormat */%"

  private def partnerQuery(): MQuery = {
    val query = new MQuery("C_Order")
    query.addRestriction("C_BPartner_ID", MQuery.EQUAL, mostOrderedPartner)
    query
  }

  private def runSeven(query: () => MQuery, planCache: Option[Boolean]): Int = {
    DB.executeUpdateEx("DEALLOCATE ALL", trxName)
    check(preparedStatements(trxName, Tag) == 0)
    val sqls = (1 to Runs).map(_ => read(OrderFormat, query(), planCache = planCache, transactionName = trxName).getSQL).toSet
    check(sqls.size == 1 && sqls.head.contains(Tag.stripPrefix("%").stripSuffix("%")), "the report SQL must carry its tag")
    val count = preparedStatements(trxName, Tag)
    info(s"pg_prepared_statements with the report tag after $Runs runs (plan cache $planCache): $count")
    count
  }

  Feature("The plan of a statement with parameters is reused when REPORT_PLAN_CACHE allows it") {
    Scenario("The switch defaults to Y and has an override") {
      val context = Env.getCtx
      val configured = MSysConfig.getBooleanValue(DataEngine.REPORT_PLAN_CACHE, true, Env.getAD_Client_ID(context))
      assert(DataEngine.REPORT_PLAN_CACHE == "REPORT_PLAN_CACHE")
      assert(new DataEngine(language, null).isPlanCache(context) == configured)
      assert(new DataEngine(language, null).setPlanCache(false).isPlanCache(context) == false)
      assert(new DataEngine(language, null).setPlanCache(true).isPlanCache(context) == true)
      assert(new DataEngine(language, null).setPlanCache(false).setPlanCache(null).isPlanCache(context) == configured)
    }

    Scenario("With parameters and reuse on, seven runs on one connection leave a server-side prepared statement") {
      assume(DB.isPostgreSQL, "pg_prepared_statements needs PostgreSQL")
      Given("a connection without prepared statements")
      When(s"the report with a bound restriction runs $Runs times with the plan cache on")
      Then("pg_prepared_statements holds the report statement")
      assert(runSeven(() => partnerQuery(), Some(true)) > 0)
    }

    Scenario("With parameters and reuse off, the statement is never server-side prepared") {
      assume(DB.isPostgreSQL, "pg_prepared_statements needs PostgreSQL")
      assert(runSeven(() => partnerQuery(), Some(false)) == 0)
    }

    Scenario("Without parameters it is never server-side prepared, whatever the switch says") {
      assume(DB.isPostgreSQL, "pg_prepared_statements needs PostgreSQL")
      assert(runSeven(() => new MQuery(), Some(true)) == 0)
      And("neither is the literal restriction, which has no parameter")
      assert(runSeven(() => { val query = new MQuery("C_Order"); query.addRestriction("C_Order.IsSOTrx='Y'"); query }, Some(true)) == 0)
    }

    Scenario("The reuse does not change the rows") {
      val on = read(OrderFormat, partnerQuery(), planCache = Some(true), transactionName = trxName)
      val off = read(OrderFormat, partnerQuery(), planCache = Some(false), transactionName = trxName)
      assert(on.getRowCount > 0 && content(on) == content(off))
    }
  }
}
