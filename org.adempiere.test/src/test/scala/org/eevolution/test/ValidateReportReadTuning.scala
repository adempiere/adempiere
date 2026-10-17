/** Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
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

import org.compiere.model.{MQuery, MSysConfig}
import org.compiere.print.{DataEngine, MPrintFormat, PrintData, PrintDataElement}
import org.compiere.util.{DB, Env, Language, Trx}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/** Tests of how DataEngine reads the data of a report: the SQL is never server-side prepared (PostgreSQL) and the
  * result is fetched in batches (REPORT_FETCH_SIZE) without changing data, order or row count.
  *
  * Statement caching is observed through pg_prepared_statements of the very connection that runs the report (the
  * transaction of the test), never through the driver internals; the scenarios that need PostgreSQL are cancelled
  * on another database.
  */
class ValidateReportReadTuning extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val English = "en_US"
  private val Runs = 7 // pgjdbc server-side prepares a statement at the 5th execution (prepareThreshold=5)
  private val OrderFormat = 1000003 // C_Order
  private val OrderLineFormat = 1000008 // C_OrderLine
  private val FactAcctFormat = 1000007 // Fact_Acct
  private val Formats = Vector(OrderFormat, OrderLineFormat, 1000004, FactAcctFormat)
  private val KeyGap = 1000000L

  private def language = Language.getLanguage(English)

  private def check(condition: Boolean, message: => String = "assertion failed"): Unit =
    if (!condition) fail(message)

  private def format(id: Int): MPrintFormat = MPrintFormat.get(Env.getCtx, id, true)

  private def engine(trx: String, fetchSize: Option[Int]): DataEngine = {
    val engine = new DataEngine(language, trx)
    engine.setLookupJoin(true)
    fetchSize.foreach(size => engine.setFetchSize(size))
    engine
  }

  private def read(formatId: Int, trx: String, fetchSize: Option[Int]): PrintData =
    engine(trx, fetchSize).getPrintData(Env.getCtx, format(formatId), new MQuery(), false, 0)

  /** SQL, columns and every cell in order: what must not change with the fetch size */
  private def content(pd: PrintData): Vector[Vector[String]] =
    (0 until pd.getRowCount).map { row =>
      pd.setRowIndex(row)
      (0 until pd.getNodeCount).map { index =>
        pd.getNode(index) match {
          case e: PrintDataElement => s"${e.getColumnName}|${e.getValueKey}|${e.getValue}|${e.getValueAsString}"
          case other => "#" + other
        }
      }.toVector
    }.toVector

  private def preparedStatements(trx: String, like: String): Int =
    DB.getSQLValue(trx, "SELECT COUNT(*) FROM pg_prepared_statements WHERE statement LIKE ?", like)

  Feature("The one-off report SQL is never server-side prepared") {
    Scenario("Seven runs of the same report in one transaction leave no prepared statement of the report") {
      assume(DB.isPostgreSQL, "pg_prepared_statements needs PostgreSQL")
      Given("a connection without prepared statements")
      DB.executeUpdateEx("DEALLOCATE ALL", trxName)
      val tag = s"%/* AD_PrintFormat_ID=$OrderFormat */%"
      check(preparedStatements(trxName, tag) == 0)

      When(s"the report runs $Runs times through the connection of the transaction")
      val sqls = (1 to Runs).map(_ => read(OrderFormat, trxName, Some(0)).getSQL).toSet
      check(sqls.size == 1 && sqls.head.contains(tag.stripPrefix("%").stripSuffix("%")), "the report SQL must carry its tag")

      Then("pg_prepared_statements holds no statement with the report tag")
      val reportStatements = preparedStatements(trxName, tag)
      info(s"pg_prepared_statements with the report tag after $Runs runs: $reportStatements")
      assert(reportStatements == 0)
    }

    Scenario("Control: an ordinary statement does reach the driver default and is server-side prepared") {
      assume(DB.isPostgreSQL, "pg_prepared_statements needs PostgreSQL")
      Given("a connection without prepared statements")
      DB.executeUpdateEx("DEALLOCATE ALL", trxName)
      When(s"a dictionary-like query runs $Runs times through the same connection")
      (1 to Runs).foreach(_ => DB.getSQLValue(trxName, "SELECT COUNT(*) /* RLJControl */ FROM AD_Table WHERE AD_Table_ID=?", 259))
      Then("the driver prepared it (so the detector of the previous scenario works and other statements keep the default)")
      val control = preparedStatements(trxName, "%RLJControl%")
      info(s"pg_prepared_statements with the control tag after $Runs runs: $control")
      assert(control > 0)
    }
  }

  Feature("The report read fetches in batches without changing the result") {
    Scenario("The fetch size comes from REPORT_FETCH_SIZE (default 1000) and a public override") {
      val ctx = Env.getCtx
      val configured = MSysConfig.getIntValue(DataEngine.REPORT_FETCH_SIZE, -1, Env.getAD_Client_ID(ctx))
      val expected = if (configured >= 0) configured else DataEngine.DEFAULT_FETCH_SIZE
      assert(DataEngine.DEFAULT_FETCH_SIZE == 1000)
      assert(new DataEngine(language, null).getFetchSize(ctx) == expected)
      assert(new DataEngine(language, null).setFetchSize(0).getFetchSize(ctx) == 0)
      assert(new DataEngine(language, null).setFetchSize(25).getFetchSize(ctx) == 25)
      assert(new DataEngine(language, null).setFetchSize(-5).getFetchSize(ctx) == 0)
      assert(new DataEngine(language, null).setFetchSize(25).setFetchSize(null).getFetchSize(ctx) == expected)
    }

    Scenario("Same SQL, columns, rows and order with fetch size 0, 1000 and 7, with and without a transaction") {
      Formats.foreach { id =>
        Given(s"print format $id")
        Seq(None, Some(trxName)).foreach { trx =>
          val oracle = read(id, trx.orNull, Some(0))
          val expected = content(oracle)
          check(expected.nonEmpty, s"format $id returns no row")
          Seq(1000, 7).foreach { size =>
            val actual = read(id, trx.orNull, Some(size))
            check(actual.getSQL == oracle.getSQL, s"SQL differs for $id, fetch size $size")
            check(actual.getRowCount == oracle.getRowCount, s"row count $id, fetch size $size, trx ${trx.isDefined}: ${actual.getRowCount} <> ${oracle.getRowCount}")
            // views that select the current timestamp differ between runs: compare the order of the keys and the other cells
            check(content(actual) == expected, s"content differs for $id, fetch size $size, trx ${trx.isDefined}")
          }
          info(s"format $id, trx=${trx.isDefined}: ${oracle.getRowCount} rows identical for fetch size 0, 1000 and 7")
        }
      }
    }

    Scenario("A read without a transaction leaves no transaction behind, also when the report fails") {
      def activeTrx: Set[String] = Trx.getActiveTransactions.map(_.getTrxName).toSet
      val before = activeTrx
      Given(s"${before.size} registered transactions")
      When("reports run without a transaction with a fetch size")
      Formats.foreach(id => read(id, null, Some(7)))
      Then("no transaction was left registered")
      val after = activeTrx
      info(s"registered transactions before ${before.size}, after ${after.size}")
      assert(after == before)
      And("a failing report read also releases it")
      val broken = new MQuery()
      broken.addRestriction("no_such_column=1")
      try engine(null, Some(7)).getPrintData(Env.getCtx, format(OrderFormat), broken, false, 0)
      catch { case _: Exception => () }
      assert(activeTrx == before)
    }

    Scenario("A report that reads more rows than the fetch size returns every row (scaled data inside a rolled back transaction)") {
      assume(DB.isPostgreSQL, "The scaled data needs PostgreSQL (generate_series)")
      val outside = DB.getSQLValue(null, "SELECT COUNT(*) FROM Fact_Acct")
      val trx = Trx.get(Trx.createTrxName("RLJReadTuning_"), true)
      try {
        val connection = trx.getConnection
        assume(!connection.getAutoCommit, "the transaction must not autocommit")
        val natural = read(FactAcctFormat, trx.getTrxName, Some(0)).getRowCount
        Given(s"Fact_Acct multiplied by 20 inside the transaction ($natural rows read before)")
        val rows = outside
        val maxKey = DB.getSQLValueBD(trx.getTrxName, "SELECT MAX(Fact_Acct_ID) FROM Fact_Acct").longValue
        val statement = connection.createStatement()
        try {
          val metaData = statement.executeQuery("SELECT * FROM Fact_Acct WHERE 1=0").getMetaData
          val columns = (1 to metaData.getColumnCount).map(metaData.getColumnName)
          val select = columns.map(c => if (c.equalsIgnoreCase("Fact_Acct_ID")) s"${maxKey + KeyGap} + (g.n - 1) * $rows + s.rn" else s"s.$c")
          statement.executeUpdate(
            s"INSERT INTO Fact_Acct (${columns.mkString(",")}) SELECT ${select.mkString(",")} " +
              "FROM (SELECT x.*, ROW_NUMBER() OVER (ORDER BY x.Fact_Acct_ID) AS rn FROM Fact_Acct x) s " +
              "CROSS JOIN generate_series(1, 19) AS g(n)")
        } finally statement.close()
        check(DB.getSQLValue(null, "SELECT COUNT(*) FROM Fact_Acct") == outside, "the scaled rows must not be visible outside the transaction")

        When("the report reads them with fetch size 0, 1000 and 100 (several batches)")
        val whole = read(FactAcctFormat, trx.getTrxName, Some(0))
        check(whole.getRowCount > 2000, s"expected more than 2000 rows, got ${whole.getRowCount}")
        val expected = content(whole)
        Then("every row comes back, in the same order")
        Seq(1000, 100).foreach { size =>
          val actual = read(FactAcctFormat, trx.getTrxName, Some(size))
          info(s"fetch size $size: ${actual.getRowCount} rows (fetch size 0: ${whole.getRowCount})")
          assert(actual.getRowCount == whole.getRowCount)
          check(content(actual) == expected, s"content differs for fetch size $size")
        }
      } finally {
        trx.rollback()
        trx.close()
      }
      assert(DB.getSQLValue(null, "SELECT COUNT(*) FROM Fact_Acct") == outside, "the rolled back rows must be gone")
    }
  }
}
