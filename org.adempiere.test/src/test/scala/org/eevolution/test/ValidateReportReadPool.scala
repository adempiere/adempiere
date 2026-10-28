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

import java.lang.reflect.{InvocationHandler, Method, Proxy}
import java.sql.{Connection, SQLException}
import java.util.concurrent.{Callable, Executors, TimeUnit}
import java.util.logging.{Handler, Level, LogRecord}
import javax.sql.DataSource

import com.zaxxer.hikari.HikariDataSource
import org.compiere.db.{CConnection, DB_PostgreSQL}
import org.compiere.model.MQuery
import org.compiere.print.{DataEngine, MPrintFormat, PrintData, PrintDataElement}
import org.compiere.util.{CLogger, DB, Env, Language, Trx}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.collection.mutable.ArrayBuffer
import scala.util.Try

/** The report engine reads its data from the read only short running pool (AdempiereSRDS) when the report does not run
  * inside a transaction of the caller; inside one it keeps that transaction.
  *
  * The pool is observed from the database, through pg_stat_activity: the backend that executes the report SQL (found by
  * the report tag; a pg_sleep in the restriction keeps the statement active while it is looked for, the server cuts the text of long statements, so the sleep itself is not matched) carries the
  * application name of its pool. The scenarios that need PostgreSQL are cancelled on another database.
  */
class ValidateReportReadPool extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val English = Language.getLanguage("en_US")
  private val OrderFormat = 1000003 // C_Order
  private val FactAcctFormat = 1000007 // Fact_Acct
  private val Formats = Vector(OrderFormat, 1000004, 1000008, FactAcctFormat)
  private val SleepSeconds = "1.37" // keeps the statement of this test active long enough to be seen
  private val SleepRestriction = s"(SELECT 1 FROM pg_sleep($SleepSeconds)) = 1"
  private val ReportPool = "ADempiere AdempiereSRDS"
  private val ServerPool = "ADempiere AdempiereDS"
  private val KeyGap = 1000000L

  private def check(condition: Boolean, message: => String = "assertion failed"): Unit =
    if (!condition) fail(message)

  private def format(id: Int): MPrintFormat = MPrintFormat.get(Env.getCtx, id, true)

  private def read(formatId: Int, transactionName: String, restriction: Option[String] = None, fetchSize: Option[Int] = None): PrintData = {
    val engine = new DataEngine(English, transactionName)
    engine.setLookupJoin(true)
    fetchSize.foreach(size => engine.setFetchSize(size))
    val query = new MQuery(format(formatId).getAD_Table_ID)
    restriction.foreach(query.addRestriction)
    engine.getPrintData(Env.getCtx, format(formatId), query, false, 0)
  }

  /** SQL, columns and every cell in order */
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

  /** a connection that does not come from the pools, so looking does not disturb what is looked at */
  private def withDriverConnection[T](body: Connection => T): T = {
    val connection = DB.getDatabase.getDriverConnection(CConnection.get())
    try body(connection) finally connection.close()
  }

  /** application_name of the backends that are executing the report SQL of `formatId` right now */
  private def reportBackends(formatId: Int): Vector[String] = withDriverConnection { connection =>
    val statement = connection.prepareStatement(
      "SELECT application_name FROM pg_stat_activity WHERE datname = current_database() AND pid <> pg_backend_pid() " +
        "AND state = 'active' AND query LIKE ?")
    try {
      statement.setString(1, s"%AD_PrintFormat_ID=$formatId */%")
      val resultSet = statement.executeQuery()
      try Iterator.continually(resultSet).takeWhile(_.next()).map(_.getString(1)).toVector finally resultSet.close()
    } finally statement.close()
  }

  /** runs the report in another thread and returns the application names of the backends seen executing its SQL */
  private def observeReport(formatId: Int, transactionName: String): Vector[String] = {
    val executor = Executors.newSingleThreadExecutor()
    try {
      val report = executor.submit(new Callable[PrintData] {
        override def call(): PrintData = {
          read(formatId, transactionName, Some(SleepRestriction))
        }
      })
      val deadline = System.currentTimeMillis() + 20000
      var seen = Vector.empty[String]
      while (seen.isEmpty && !report.isDone && System.currentTimeMillis() < deadline) {
        seen = reportBackends(formatId)
        if (seen.isEmpty) Thread.sleep(25)
      }
      val data = report.get(30, TimeUnit.SECONDS)
      check(data.getRowCount > 0, "the observed report returned no row")
      seen
    } finally executor.shutdownNow()
  }

  private def shortRunningPool(): Option[HikariDataSource] =
    Option(DB.getDatabase.asInstanceOf[DB_PostgreSQL].getDataSourceShortRunning(CConnection.get())).collect { case pool: HikariDataSource => pool }

  private def activeTransactions: Set[String] = Trx.getActiveTransactions.map(_.getTrxName).toSet

  /** replaces the short running pool of the database of this JVM while `body` runs */
  private def withShortRunningPool[T](replacement: DataSource)(body: => T): T = {
    val field = classOf[DB_PostgreSQL].getDeclaredField("datasourceShortRunning")
    field.setAccessible(true)
    val database = DB.getDatabase.asInstanceOf[DB_PostgreSQL]
    val original = field.get(database)
    field.set(database, replacement)
    try body finally field.set(database, original)
  }

  /** a pool that cannot give a connection */
  private def unavailablePool: DataSource = Proxy.newProxyInstance(getClass.getClassLoader, Array[Class[_]](classOf[DataSource]),
    new InvocationHandler {
      override def invoke(proxy: Any, method: Method, args: Array[AnyRef]): AnyRef =
        throw new SQLException("the short running pool is down (test)")
    }).asInstanceOf[DataSource]

  /** WARNING and SEVERE records that CConnection logs while `body` runs */
  private def connectionWarnings[T](body: => T): (T, Vector[String]) = {
    val logger = CLogger.getCLogger(classOf[CConnection])
    val records = ArrayBuffer.empty[LogRecord]
    val handler = new Handler {
      override def publish(record: LogRecord): Unit = if (record.getLevel.intValue >= Level.WARNING.intValue) records.synchronized(records += record)
      override def flush(): Unit = ()
      override def close(): Unit = ()
    }
    val level = logger.getLevel
    logger.setLevel(Level.WARNING)
    logger.addHandler(handler)
    try (body, records.synchronized(records.toVector.map(_.getMessage)))
    finally { logger.removeHandler(handler); logger.setLevel(level) }
  }

  Feature("The report data query runs on the read only short running pool") {

    Scenario("A report outside a transaction is executed by a backend named after AdempiereSRDS") {
      assume(DB.isPostgreSQL, "pg_stat_activity needs PostgreSQL")
      When("the report runs without a transaction")
      val seen = observeReport(OrderFormat, null)
      Then(s"the backend that executes the report SQL is named '$ReportPool'")
      info(s"backends executing the report SQL: ${seen.mkString(", ")}")
      check(seen.nonEmpty, "the report SQL was never seen active")
      assert(seen.forall(_ == ReportPool), s"application names: ${seen.mkString(", ")}")
    }

    Scenario("Rows, columns and order are the same as the read of a transaction, with and without batches") {
      Formats.foreach { id =>
        Given(s"print format $id")
        val viaTransaction = read(id, trxName, fetchSize = Some(0))
        val expected = content(viaTransaction)
        check(expected.nonEmpty, s"format $id returns no row")
        Seq(0, 1000, 7).foreach { size =>
          val viaPool = read(id, null, fetchSize = Some(size))
          check(viaPool.getSQL == viaTransaction.getSQL, s"SQL differs for $id, fetch size $size")
          check(viaPool.getRowCount == viaTransaction.getRowCount, s"row count $id, fetch size $size: ${viaPool.getRowCount} <> ${viaTransaction.getRowCount}")
          check(content(viaPool) == expected, s"content differs for $id, fetch size $size")
        }
        info(s"format $id: ${viaTransaction.getRowCount} rows identical through the short running pool for fetch size 0, 1000 and 7")
      }
    }

    Scenario("A report outside a transaction leaves no transaction and no pool connection behind, also when it fails") {
      assume(DB.isPostgreSQL, "the pool is the PostgreSQL one")
      val pool = shortRunningPool()
      check(pool.isDefined, "the short running pool could not be built")
      read(OrderFormat, null) // the pool exists and is warm
      val transactions = activeTransactions
      def inUse: Int = pool.get.getHikariPoolMXBean.getActiveConnections
      Given(s"${transactions.size} registered transactions and $inUse connections in use")
      When("reports run, and one fails in the database")
      Formats.foreach(id => read(id, null, fetchSize = Some(7)))
      val broken = Try(read(OrderFormat, null, Some("no_such_column=1")))
      info(s"the failing report: ${broken.map(_ => "ended").recover { case throwable => throwable.getClass.getSimpleName }.get}")
      Then("no transaction stays registered and every connection went back to the pool")
      assert(activeTransactions == transactions)
      assert(inUse == 0, s"$inUse connections of the short running pool still in use")
    }

    Scenario("The connection of the short running pool refuses to write and declares autoCommit as asked") {
      assume(DB.isPostgreSQL, "the pool is the PostgreSQL one")
      Seq(true, false).foreach { autoCommit =>
        val connection = DB.getConnectionShortRunning(autoCommit)
        check(connection != null, "no connection from the short running pool")
        try {
          check(connection.getAutoCommit == autoCommit)
          check(connection.getTransactionIsolation == Connection.TRANSACTION_READ_COMMITTED)
          val statement = connection.createStatement()
          try {
            val resultSet = statement.executeQuery("SELECT application_name FROM pg_stat_activity WHERE pid = pg_backend_pid()")
            resultSet.next()
            check(resultSet.getString(1) == ReportPool, s"application_name = ${resultSet.getString(1)}")
            resultSet.close()
            val write = Try(statement.execute("CREATE TEMPORARY TABLE rlj_report_pool_probe (id INTEGER)"))
            check(write.isFailure, s"a write must be rejected (autoCommit $autoCommit)")
          } finally statement.close()
        } finally {
          if (!connection.getAutoCommit) connection.rollback()
          connection.close()
        }
      }
    }
  }

  Feature("A report inside a transaction of the caller keeps that transaction") {

    Scenario("The backend that executes the report SQL is the one of the transaction, not the short running pool") {
      assume(DB.isPostgreSQL, "pg_stat_activity needs PostgreSQL")
      val seen = observeReport(OrderFormat, trxName)
      info(s"backends executing the report SQL: ${seen.mkString(", ")}")
      check(seen.nonEmpty, "the report SQL was never seen active")
      assert(seen.forall(_ == ServerPool), s"application names: ${seen.mkString(", ")}")
    }

    Scenario("The report sees a row inserted by the transaction and not committed; the short running pool does not") {
      assume(DB.isPostgreSQL, "the scaled data needs PostgreSQL")
      val outside = DB.getSQLValue(null, "SELECT COUNT(*) FROM Fact_Acct")
      val transaction = Trx.get(Trx.createTrxName("RLJReadPool_"), true)
      try {
        val connection = transaction.getConnection
        val before = read(FactAcctFormat, transaction.getTrxName).getRowCount
        val maxKey = DB.getSQLValueBD(transaction.getTrxName, "SELECT MAX(Fact_Acct_ID) FROM Fact_Acct").longValue
        val statement = connection.createStatement()
        try {
          val metaData = statement.executeQuery("SELECT * FROM Fact_Acct WHERE 1=0").getMetaData
          val columns = (1 to metaData.getColumnCount).map(metaData.getColumnName)
          val select = columns.map(columnName => if (columnName.equalsIgnoreCase("Fact_Acct_ID")) s"${maxKey + KeyGap}" else s"s.$columnName")
          statement.executeUpdate(
            s"INSERT INTO Fact_Acct (${columns.mkString(",")}) SELECT ${select.mkString(",")} " +
              "FROM Fact_Acct s WHERE s.Fact_Acct_ID = (SELECT MIN(Fact_Acct_ID) FROM Fact_Acct)")
        } finally statement.close()
        Given(s"a Fact_Acct row inserted in the transaction ($before rows seen by it before)")
        When("the report runs with the transaction and without it")
        val inside = read(FactAcctFormat, transaction.getTrxName).getRowCount
        val viaPool = read(FactAcctFormat, null).getRowCount
        info(s"rows: with the transaction $inside (before $before), through the pool $viaPool")
        Then("the transaction sees its row and the short running pool sees only committed data")
        assert(inside == before + 1)
        assert(viaPool == before)
      } finally {
        transaction.rollback()
        transaction.close()
      }
      assert(DB.getSQLValue(null, "SELECT COUNT(*) FROM Fact_Acct") == outside, "the inserted row must be gone")
    }
  }

  Feature("A short running pool that gives no connection does not stop the report") {

    Scenario("The report falls back to the connection it used before, with one warning") {
      assume(DB.isPostgreSQL, "the pool is the PostgreSQL one")
      val expected = content(read(OrderFormat, null))
      check(expected.nonEmpty, "the report returns no row")
      val (runs, warnings) = connectionWarnings {
        withShortRunningPool(unavailablePool) {
          (1 to 3).map(_ => read(OrderFormat, null))
        }
      }
      info(s"warnings logged by 3 reports: ${warnings.mkString(" | ")}")
      Then("every report returned the same rows and the failure was logged once")
      runs.foreach(run => assert(content(run) == expected))
      assert(warnings.size == 1, s"warnings: $warnings")
      assert(warnings.head.contains("AdempiereSRDS"))
      And("the pool is used again when it is back, and a new failure is warned again")
      val seen = observeReport(OrderFormat, null)
      assert(seen.forall(_ == ReportPool), s"application names: ${seen.mkString(", ")}")
      val (_, again) = connectionWarnings(withShortRunningPool(unavailablePool)(read(OrderFormat, null)))
      assert(again.size == 1, s"warnings after the pool came back: $again")
    }
  }
}
