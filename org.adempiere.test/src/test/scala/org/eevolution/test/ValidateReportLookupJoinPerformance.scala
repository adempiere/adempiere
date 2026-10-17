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

import java.io.File
import java.lang.management.ManagementFactory
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.sql.{Connection, ResultSet}
import java.time.LocalDateTime
import java.util.Locale

import org.compiere.model.MQuery
import org.compiere.print.{DataEngine, MPrintFormat}
import org.compiere.print.ReportDefinitionCache
import org.compiere.util.{CacheMgt, DB, Env, Language, Trx}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.collection.mutable.ArrayBuffer
import scala.util.Try

/** Opt-in performance benchmark of the report lookup strategy: correlated subselects (setLookupJoin(false))
  * against LEFT OUTER JOINs (setLookupJoin(true)).
  *
  * It runs only with ADEMPIERE_BENCHMARK=true; otherwise every scenario is cancelled, so a plain `sbt test` stays fast.
  *
  * Per print format and per path it measures:
  *  - EXPLAIN (ANALYZE, BUFFERS) of the exact PrintData SQL (PostgreSQL only): planning and execution time, SubPlans;
  *  - the JDBC full fetch of that SQL: warmup, then interleaved legacy/join iterations, median and p95;
  *  - the end-to-end DataEngine.getPrintData time.
  *
  * The natural volume of the demo database is tiny, so a scaled scenario multiplies the rows of Fact_Acct and
  * C_OrderLine with INSERT ... SELECT inside a transaction that is always rolled back (no DDL, no commit).
  * The report is written to ADEMPIERE_BENCHMARK_REPORT or org.adempiere.test/target/report-lookup-join-benchmark.md.
  *
  * The test fails only on a clear regression (join fetch median > legacy x 1.20 and more than 20 ms worse) or when
  * both paths return a different number of rows. Improvements are reported, never asserted.
  */
class ValidateReportLookupJoinPerformance extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val English = "en_US"
  private val WarmupRuns = 3
  private val MeasuredRuns = 15
  private val EndToEndRuns = 5
  private val RegressionFactor = 1.20
  private val RegressionMillis = 20.0
  private val KeyGap = 1000000L // new keys start this far above the highest existing key

  private val enabled: Boolean = sys.env.get("ADEMPIERE_BENCHMARK").exists(_.trim.equalsIgnoreCase("true"))
  private val scale: Int = sys.env.get("ADEMPIERE_BENCHMARK_SCALE").flatMap(value => Try(value.trim.toInt).toOption).filter(_ >= 1).getOrElse(200)

  /** A print format under test and why it was chosen */
  private case class Target(formatId: Int, table: String, why: String)

  /** Chosen from the local GardenWorld database: table based formats with the most printed TableDir / Search (no reference
    * value) columns, which are the columns that the legacy path resolves with a correlated subselect per row.
    */
  private val targets = Vector(
    Target(1000007, "Fact_Acct",
      "19 top-level lookups on the accounting fact table, the table that grows fastest in a real installation (144 rows here); scaled"),
    Target(1000008, "C_OrderLine",
      "18 top-level lookups on the order line, the typical document line report (28 rows here); scaled"),
    Target(1000004, "RV_OrderDetail",
      "18 top-level lookups over a ReportView on a database view of the order lines; follows the C_OrderLine scale, shows the join cost on a view"),
    Target(1000003, "C_Order",
      "23 top-level lookups, the highest count of any format (10 rows here); not scaled, shows the fixed cost per row of many lookups")
  )

  /** Tables whose rows are multiplied in the scaled scenario */
  private case class ScaleTable(name: String, key: String)
  private val scaleTables = Vector(ScaleTable("Fact_Acct", "Fact_Acct_ID"), ScaleTable("C_OrderLine", "C_OrderLine_ID"))

  private case class Explain(planningMs: Double, executionMs: Double, subPlans: Int, subPlanLoops: Long, buffers: Long)
  private case class Fetch(rows: Int, medianMs: Double, p95Ms: Double)
  private case class Measure(rows: Int, explain: Option[Explain], fetch: Fetch, endToEndMs: Double)
  private case class Comparison(target: Target, volume: String, legacy: Measure, join: Measure)

  private val comparisons = ArrayBuffer.empty[Comparison]
  private val notes = ArrayBuffer.empty[String]

  override def beforeAll(): Unit = if (enabled) super.beforeAll()

  override def afterAll(): Unit = if (enabled) {
    try writeReport()
    finally super.afterAll()
  }

  private def requireEnabled(): Unit =
    assume(enabled, "Performance benchmark is opt-in: set ADEMPIERE_BENCHMARK=true to run it")

  private def language = Language.getLanguage(English)

  /** Runs the body in a fresh transaction that is always rolled back. Refuses to run when the connection autocommits. */
  private def inRolledBackTransaction[T](body: Trx => T): T = {
    val transaction = Trx.get(Trx.createTrxName("RLJBenchmark_"), true)
    try {
      val connection = transaction.getConnection
      if (connection == null || connection.getAutoCommit)
        throw new IllegalStateException("Refusing to run: the benchmark transaction would not be rolled back (autocommit connection)")
      body(transaction)
    } finally {
      transaction.rollback()
      transaction.close()
    }
  }

  private def count(table: String, transaction: Trx = null): Int =
    DB.getSQLValue(if (transaction == null) null else transaction.getTrxName, s"SELECT COUNT(*) FROM $table")

  private def median(values: Seq[Double]): Double = {
    val sorted = values.sorted
    val size = sorted.size
    if (size % 2 == 1) sorted(size / 2) else (sorted(size / 2 - 1) + sorted(size / 2)) / 2
  }

  private def percentile95(values: Seq[Double]): Double = {
    val sorted = values.sorted
    sorted(math.ceil(0.95 * sorted.size).toInt - 1)
  }

  private def loadData(format: MPrintFormat, join: Boolean, transaction: Trx) = {
    val engine = new DataEngine(language, transaction.getTrxName)
    engine.setLookupJoin(join)
    engine.setDefinitionCache(false) // the lookup strategies are compared with the definition read on every run, as before the cache existed
    engine.getPrintData(Env.getCtx, format, new MQuery(), false, 0)
  }

  private val SubPlanHeader = """^SubPlan \d+\b.*""".r
  private val Loops = """loops=(\d+)""".r
  private val Time = """(Planning|Execution) Time: ([0-9.]+) ms""".r
  private val SharedBuffers = """(?:hit|read)=(\d+)""".r

  /** EXPLAIN (ANALYZE, BUFFERS) of the SQL as the database receives it, through the connection of the transaction */
  private def explain(sql: String, transaction: Trx): Option[Explain] =
    if (!DB.isPostgreSQL) None
    else {
      val statement = transaction.getConnection.createStatement()
      var resultSet: ResultSet = null
      try {
        resultSet = statement.executeQuery("EXPLAIN (ANALYZE, BUFFERS) " + DB.getDatabase.convertStatement(sql))
        val lines = Iterator.continually(resultSet).takeWhile(_.next()).map(_.getString(1)).toVector
        val times = lines.flatMap(line => Time.findFirstMatchIn(line).map(timeMatch => timeMatch.group(1) -> timeMatch.group(2).toDouble)).toMap
        val subPlanStarts = lines.indices.filter(lineIndex => SubPlanHeader.matches(lines(lineIndex).trim))
        // the loops of a SubPlan are the loops of its first child node
        val loops = subPlanStarts.map { lineIndex =>
          lines.drop(lineIndex + 1).find(_.contains("->")).map { child =>
            if (child.contains("never executed")) 0L else Loops.findFirstMatchIn(child).map(_.group(1).toLong).getOrElse(0L)
          }.getOrElse(0L)
        }
        // shared buffers (hit + read) of the top plan node: the whole statement, SubPlans included
        val buffers = lines.find(_.trim.startsWith("Buffers:")).map { line =>
          SharedBuffers.findAllMatchIn(line.substring(line.indexOf("shared")).takeWhile(_ != ',')).map(_.group(1).toLong).sum
        }.getOrElse(0L)
        Some(Explain(times.getOrElse("Planning", 0.0), times.getOrElse("Execution", 0.0), subPlanStarts.size, loops.sum, buffers))
      } finally DB.close(resultSet, statement)
    }

  /** Executes the SQL and reads every cell of every row. Only the execution and the fetch are timed. */
  private def fetchAll(sql: String, transaction: Trx): (Int, Double) = {
    val statement = DB.prepareStatement(sql, transaction.getTrxName)
    var resultSet: ResultSet = null
    var rows = 0
    try {
      val start = System.nanoTime
      resultSet = statement.executeQuery()
      val columns = resultSet.getMetaData.getColumnCount
      while (resultSet.next()) {
        var columnIndex = 1
        while (columnIndex <= columns) {
          resultSet.getObject(columnIndex)
          columnIndex += 1
        }
        rows += 1
      }
      (rows, (System.nanoTime - start) / 1e6)
    } finally DB.close(resultSet, statement)
  }

  /** With ADEMPIERE_BENCHMARK_SQL_DIR the SQL of both paths, as the database receives it, is saved so a DBA can run EXPLAIN by hand */
  private def dumpSql(target: Target, legacySql: String, joinSql: String): Unit =
    sys.env.get("ADEMPIERE_BENCHMARK_SQL_DIR").filter(_.trim.nonEmpty).foreach { dir =>
      new File(dir).mkdirs()
      Files.write(new File(dir, s"${target.formatId}-legacy.sql").toPath, DB.getDatabase.convertStatement(legacySql).getBytes(StandardCharsets.UTF_8))
      Files.write(new File(dir, s"${target.formatId}-join.sql").toPath, DB.getDatabase.convertStatement(joinSql).getBytes(StandardCharsets.UTF_8))
    }

  /** pgjdbc keeps a server-side prepared statement per SQL text after 5 executions, on a pooled connection that outlives the
    * transaction. Its plan has no parameters, so it is never re-planned: a plan made for the natural volume would be reused on the
    * scaled data (hashed SubPlans and join strategies chosen for 27 rows). DISCARD PLANS only invalidates the plan cache of the session.
    */
  private def discardCachedPlans(transaction: Trx): Unit = if (DB.isPostgreSQL) {
    val statement = transaction.getConnection.createStatement()
    try statement.execute("DISCARD PLANS")
    finally statement.close()
  }

  private def measure(target: Target, volume: String, transaction: Trx): Comparison = {
    discardCachedPlans(transaction)
    val format = MPrintFormat.get(Env.getCtx, target.formatId, true)
    val legacyData = loadData(format, join = false, transaction)
    val joinData = loadData(format, join = true, transaction)
    val legacySql = legacyData.getSQL
    val joinSql = joinData.getSQL
    dumpSql(target, legacySql, joinSql)
    val legacyExplain = explain(legacySql, transaction)
    val joinExplain = explain(joinSql, transaction)

    // warmup, then measured iterations interleaved legacy/join to spread the cache effects over both paths
    (1 to WarmupRuns).foreach { _ =>
      fetchAll(legacySql, transaction)
      fetchAll(joinSql, transaction)
    }
    val legacyFetch = ArrayBuffer.empty[Double]
    val joinFetch = ArrayBuffer.empty[Double]
    var legacyRows = 0
    var joinRows = 0
    (1 to MeasuredRuns).foreach { _ =>
      val (legacyRowCount, legacyFetchMillis) = fetchAll(legacySql, transaction)
      val (joinRowCount, joinFetchMillis) = fetchAll(joinSql, transaction)
      legacyRows = legacyRowCount
      joinRows = joinRowCount
      legacyFetch += legacyFetchMillis
      joinFetch += joinFetchMillis
    }

    val legacyEndToEnd = ArrayBuffer.empty[Double]
    val joinEndToEnd = ArrayBuffer.empty[Double]
    (1 to EndToEndRuns).foreach { _ =>
      val legacyStart = System.nanoTime
      loadData(format, join = false, transaction)
      legacyEndToEnd += (System.nanoTime - legacyStart) / 1e6
      val joinStart = System.nanoTime
      loadData(format, join = true, transaction)
      joinEndToEnd += (System.nanoTime - joinStart) / 1e6
    }

    Comparison(
      target,
      volume,
      Measure(legacyRows, legacyExplain, Fetch(legacyRows, median(legacyFetch.toSeq), percentile95(legacyFetch.toSeq)), median(legacyEndToEnd.toSeq)),
      Measure(joinRows, joinExplain, Fetch(joinRows, median(joinFetch.toSeq), percentile95(joinFetch.toSeq)), median(joinEndToEnd.toSeq))
    )
  }

  private def assertSameRows(comparison: Comparison): Unit =
    assert(
      comparison.legacy.rows == comparison.join.rows,
      s"format ${comparison.target.formatId} (${comparison.volume}): legacy fetched ${comparison.legacy.rows} rows, join fetched ${comparison.join.rows}"
    )

  private def regression(comparison: Comparison): Option[String] = {
    val legacy = comparison.legacy.fetch.medianMs
    val join = comparison.join.fetch.medianMs
    if (join > legacy * RegressionFactor && join - legacy > RegressionMillis)
      Some(f"format ${comparison.target.formatId} ${comparison.target.table} (${comparison.volume}): join fetch median $join%.2f ms vs legacy $legacy%.2f ms")
    else None
  }

  private def assertNoRegression(comparisons: Seq[Comparison]): Unit = {
    val regressions = comparisons.flatMap(regression)
    assert(regressions.isEmpty, "join path regression: " + regressions.mkString("; "))
  }

  /** Copies every row of the table scale-1 more times with new keys above the existing ones. Foreign keys stay valid
    * because they are copied as they are. Returns the rows inserted. Only DML: no DDL, no ANALYZE (it would update pg_class outside the transaction).
    */
  private def scaleTable(table: ScaleTable, transaction: Trx): Int = {
    val connection = transaction.getConnection
    if (connection.getAutoCommit) throw new IllegalStateException(s"Refusing to scale ${table.name}: the transaction would not be rolled back")
    val statement = connection.createStatement()
    try {
      val metaData = statement.executeQuery(s"SELECT * FROM ${table.name} WHERE 1=0").getMetaData
      val columns = (1 to metaData.getColumnCount).map(metaData.getColumnName)
      val rows = count(table.name, transaction)
      val maxKey = DB.getSQLValueBD(transaction.getTrxName, s"SELECT MAX(${table.key}) FROM ${table.name}").longValue
      val base = maxKey + KeyGap
      val select = columns.map { column =>
        if (column.equalsIgnoreCase(table.key)) s"$base + (g.n - 1) * $rows + s.rn" else s"s.$column"
      }
      statement.executeUpdate(
        s"INSERT INTO ${table.name} (${columns.mkString(",")}) SELECT ${select.mkString(",")} " +
          s"FROM (SELECT x.*, ROW_NUMBER() OVER (ORDER BY x.${table.key}) AS rn FROM ${table.name} x) s " +
          s"CROSS JOIN generate_series(1, ${scale - 1}) AS g(n)"
      )
    } finally statement.close()
  }

  Feature("Performance of the report lookups: correlated subselects against LEFT OUTER JOINs") {
    info("Opt-in: set ADEMPIERE_BENCHMARK=true. The report is written to org.adempiere.test/target/report-lookup-join-benchmark.md")

    targets.foreach { target =>
      Scenario(s"Natural volume: ${target.table} (print format ${target.formatId})") {
        requireEnabled()
        Given(s"the print format ${target.formatId} on ${target.table}: ${target.why}")
        When("both paths are measured in a read-only transaction that is rolled back")
        val comparison = inRolledBackTransaction(transaction => measure(target, "natural", transaction))
        comparisons += comparison
        Then("both paths return the same number of rows and the join path is not clearly slower")
        info(s"${comparison.legacy.rows} rows, fetch median legacy ${formatNumber(comparison.legacy.fetch.medianMs)} ms, join ${formatNumber(comparison.join.fetch.medianMs)} ms")
        assertSameRows(comparison)
        assertNoRegression(Seq(comparison))
      }
    }

    Scenario("Scaled volume: rows multiplied inside a transaction that is always rolled back") {
      requireEnabled()
      assume(DB.isPostgreSQL, "The scaled scenario needs PostgreSQL (generate_series); skipped on this database")
      Given(s"${scaleTables.map(_.name).mkString(" and ")} multiplied by $scale with INSERT ... SELECT in the test transaction")
      val before = scaleTables.map(scaledTable => scaledTable.name -> count(scaledTable.name))
      val outcome = Try {
        inRolledBackTransaction { transaction =>
          val inserted = scaleTables.map { scaledTable =>
            val rows = scaleTable(scaledTable, transaction)
            val outside = count(scaledTable.name)
            // a second connection must not see the rows: this proves that nothing was committed
            if (outside != before.toMap.apply(scaledTable.name))
              throw new IllegalStateException(s"Refusing to continue: ${scaledTable.name} rows are visible outside the transaction ($outside)")
            scaledTable.name -> rows
          }
          val scaledCounts = scaleTables.map(scaledTable => scaledTable.name -> count(scaledTable.name, transaction))
          notes += s"Scale $scale: " + scaleTables.indices.map { tableIndex =>
            s"${scaleTables(tableIndex).name} ${before(tableIndex)._2} -> ${scaledCounts(tableIndex)._2} rows (+${inserted(tableIndex)._2})"
          }.mkString(", ")
          targets.map(target => measure(target, s"scaled x$scale", transaction))
        }
      }
      val after = scaleTables.map(scaledTable => scaledTable.name -> count(scaledTable.name))
      Then("no row is left behind: the counts before and after the rolled back transaction are equal")
      notes += "Row counts outside the transaction before: " + before.map { case (tableName, rowCount) => s"$tableName=$rowCount" }.mkString(", ") +
        "; after the rollback: " + after.map { case (tableName, rowCount) => s"$tableName=$rowCount" }.mkString(", ")
      assert(after == before, s"rows left in the database: before $before, after $after")
      val scaled = outcome.get
      comparisons ++= scaled
      And("both paths return the same number of rows and the join path is not clearly slower")
      scaled.foreach(comparison => info(s"${comparison.target.table}: ${comparison.legacy.rows} rows, fetch median legacy ${formatNumber(comparison.legacy.fetch.medianMs)} ms, join ${formatNumber(comparison.join.fetch.medianMs)} ms"))
      scaled.foreach(assertSameRows)
      assertNoRegression(scaled)
    }
  }

  // ---- report definition cache -------------------------------------------------------------------------------------

  private val DefinitionRuns = 20
  /** the data statement of a joined report starts with this tag; it may read AD_Table as a lookup (Fact_Acct), it is not a definition statement */
  private val DataTag = "/* AD_PrintFormat_ID="
  private val DictionaryTables = Vector("AD_PrintFormat", "AD_PrintFormatItem", "AD_Table", "AD_Column", "AD_Ref_Table", "AD_ReportView")
  private val DictionaryPattern = """(?i)\b(ad_printformat|ad_printformatitem|ad_reportview|ad_reportview_col|ad_ref_table|ad_column|ad_table)\b""".r

  /** definition = getPrintData of a query that returns no row (what is left is the definition), endToEnd = the real query */
  private case class CacheMeasure(mode: String, definitionMs: Double, endToEndMs: Double, dictionaryStatements: Option[Long], rows: Int)
  private case class CacheComparison(target: Target, off: CacheMeasure, cold: CacheMeasure, warm: CacheMeasure)
  private case class SweepRow(mode: String, formats: Int, totalMs: Double)

  private val cacheComparisons = ArrayBuffer.empty[CacheComparison]
  private val sweepRows = ArrayBuffer.empty[SweepRow]
  private val cacheNotes = ArrayBuffer.empty[String]

  private def emptyQuery(): MQuery = { val query = new MQuery(); query.addRestriction("1=2"); query }

  private def resetDefinitions(): Unit = DictionaryTables.foreach(tableName => CacheMgt.get().reset(tableName))

  private def statementCalls(): Option[Map[Long, (String, Long)]] =
    if (!DB.isPostgreSQL) None
    else Try {
      val connection = DB.createConnection(true, Connection.TRANSACTION_READ_COMMITTED)
      try {
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(
          "SELECT queryid, query, calls FROM pg_stat_statements WHERE dbid = (SELECT oid FROM pg_database WHERE datname = current_database()) " +
            "AND query NOT ILIKE '%pg_stat_statements%'")
        try Iterator.continually(resultSet).takeWhile(_.next()).map(row => row.getLong(1) -> (row.getString(2), row.getLong(3))).toMap
        finally { resultSet.close(); statement.close() }
      } finally connection.close()
    }.toOption

  /** dictionary statements (calls) between two snapshots of pg_stat_statements */
  private def dictionaryStatements(before: Option[Map[Long, (String, Long)]], after: Option[Map[Long, (String, Long)]]): Option[Long] =
    for (beforeSnapshot <- before; afterSnapshot <- after) yield afterSnapshot.toVector.map { case (id, (query, calls)) =>
      if (DictionaryPattern.findFirstIn(query).isDefined && !query.contains(DataTag)) calls - beforeSnapshot.get(id).map(_._2).getOrElse(0L) else 0L
    }.sum

  private def runDefinition(format: MPrintFormat, cache: Boolean, restrictToNothing: Boolean): (Int, Double) = {
    val query = new MQuery()
    if (restrictToNothing) query.addRestriction("1=2")
    val engine = new DataEngine(language, null)
    engine.setLookupJoin(true)
    engine.setDefinitionCache(cache)
    val start = System.nanoTime
    val data = engine.getPrintData(Env.getCtx, format, query, false, 0)
    (data.getRowCount, (System.nanoTime - start) / 1e6)
  }

  /** mode: off = the dictionary is read on every run, cold = the cache is emptied before every run, warm = cache hits */
  private def measureCache(target: Target, mode: String): CacheMeasure = {
    val format = MPrintFormat.get(Env.getCtx, target.formatId, true)
    val cache = mode != "off"
    def prepare(): Unit = if (mode == "cold") resetDefinitions()
    if (mode == "warm") { runDefinition(format, cache, restrictToNothing = true); runDefinition(format, cache, restrictToNothing = false) }
    val before = statementCalls()
    prepare()
    runDefinition(format, cache, restrictToNothing = true)
    val statements = dictionaryStatements(before, statementCalls())
    val definition = (1 to DefinitionRuns).map { _ => prepare(); runDefinition(format, cache, restrictToNothing = true)._2 }
    var rows = 0
    val endToEnd = (1 to EndToEndRuns).map { _ => prepare(); val (fetchedRows, elapsedMillis) = runDefinition(format, cache, restrictToNothing = false); rows = fetchedRows; elapsedMillis }
    CacheMeasure(mode, median(definition), median(endToEnd), statements, rows)
  }

  private lazy val tableBasedFormats: Vector[Int] = {
    val statement = DB.prepareStatement(
      "SELECT pf.AD_PrintFormat_ID FROM AD_PrintFormat pf" +
        " INNER JOIN AD_Table t ON (t.AD_Table_ID=pf.AD_Table_ID)" +
        " LEFT OUTER JOIN AD_ReportView rv ON (rv.AD_ReportView_ID=pf.AD_ReportView_ID)" +
        " LEFT OUTER JOIN AD_Table rt ON (rt.AD_Table_ID=rv.AD_Table_ID) " +
        "WHERE pf.IsActive='Y' AND pf.IsTableBased='Y' AND pf.AD_Client_ID IN (0,11)" +
        " AND t.TableName NOT LIKE 'T\\_Report%' AND t.TableName NOT LIKE 'AD\\_PInstance%'" +
        " AND COALESCE(rt.TableName,t.TableName) NOT LIKE 'T\\_Report%'" +
        " AND COALESCE(rt.TableName,t.TableName) NOT LIKE 'AD\\_PInstance%' " +
        "ORDER BY pf.AD_PrintFormat_ID", null)
    val resultSet = statement.executeQuery()
    try Iterator.continually(resultSet).takeWhile(_.next()).map(_.getInt(1)).toVector finally DB.close(resultSet, statement)
  }

  Feature("Report definition cache: reading the definition of the report from the dictionary against from the cache") {
    info("Opt-in: set ADEMPIERE_BENCHMARK=true. The report is written to org.adempiere.test/target/report-lookup-join-benchmark.md")

    targets.foreach { target =>
      Scenario(s"Definition cache: ${target.table} (print format ${target.formatId})") {
        requireEnabled()
        Given(s"the print format ${target.formatId} on ${target.table}: ${target.why}")
        When("the definition is read on every run (cache off), after emptying the cache before every run (cold) and from the cache (warm)")
        val off = measureCache(target, "off")
        val cold = measureCache(target, "cold")
        val warm = measureCache(target, "warm")
        cacheComparisons += CacheComparison(target, off, cold, warm)
        Then("the same rows are returned and the warm definition does not read the dictionary")
        info(s"definition: off ${formatNumber(off.definitionMs)} ms, cold ${formatNumber(cold.definitionMs)} ms, warm ${formatNumber(warm.definitionMs)} ms; " +
          s"dictionary statements: off ${off.dictionaryStatements.getOrElse("n/a")}, cold ${cold.dictionaryStatements.getOrElse("n/a")}, warm ${warm.dictionaryStatements.getOrElse("n/a")}")
        assert(off.rows == cold.rows && cold.rows == warm.rows, s"rows: off ${off.rows}, cold ${cold.rows}, warm ${warm.rows}")
        warm.dictionaryStatements.foreach(statementCount => assert(statementCount == 0, s"a warm run executed $statementCount dictionary statements"))
        assert(warm.definitionMs <= off.definitionMs * RegressionFactor + RegressionMillis, "a cache hit is slower than reading the dictionary")
      }
    }

    Scenario("Definition cache: every table based print format, one pass in en_US with joins") {
      requireEnabled()
      Given(s"the ${tableBasedFormats.size} active table based print formats of clients 0 and 11")
      val formats = tableBasedFormats.map(id => MPrintFormat.get(Env.getCtx, id, true))
      def pass(cache: Boolean): Double = {
        val start = System.nanoTime
        formats.foreach { format =>
          val engine = new DataEngine(language, null)
          engine.setLookupJoin(true)
          engine.setDefinitionCache(cache)
          scala.util.Try(engine.getPrintData(Env.getCtx, format, emptyQuery(), false, 0))
        }
        (System.nanoTime - start) / 1e6
      }
      pass(cache = false) // JIT and connection warm-up, not measured
      val off = (1 to 3).map(_ => pass(cache = false))
      val cold = (1 to 3).map { _ => resetDefinitions(); pass(cache = true) }
      val warm = (1 to 3).map(_ => pass(cache = true))
      sweepRows += SweepRow("off (dictionary read on every run)", formats.size, median(off))
      sweepRows += SweepRow("cold (cache emptied before the pass)", formats.size, median(cold))
      sweepRows += SweepRow("warm (cache hits)", formats.size, median(warm))
      Then("a warm pass is not slower than reading the dictionary")
      info(s"${formats.size} formats, no rows fetched: off ${formatNumber(median(off))} ms, cold ${formatNumber(median(cold))} ms, warm ${formatNumber(median(warm))} ms")
      assert(median(warm) <= median(off) * RegressionFactor + RegressionMillis)
      And("the memory held by the cache is reported")
      def used(): Long = { System.gc(); System.gc(); Thread.sleep(200); System.gc(); Runtime.getRuntime.totalMemory - Runtime.getRuntime.freeMemory }
      resetDefinitions()
      val heapBefore = used()
      Seq("en_US", "es_MX").foreach { languageCode =>
        Seq(true, false).foreach { join =>
          formats.foreach { format =>
            val engine = new DataEngine(Language.getLanguage(languageCode), null)
            engine.setLookupJoin(join)
            engine.setDefinitionCache(true)
            scala.util.Try(engine.getPrintData(Env.getCtx, format, emptyQuery(), false, 0))
          }
        }
      }
      val heapAfter = used()
      val entries = ReportDefinitionCache.size()
      cacheNotes += f"Memory: ${entries} cache entries after ${formats.size} formats x 2 languages x 2 paths, heap +${(heapAfter - heapBefore) / 1048576.0}%.1f MB (System.gc() before and after, indicative)"
      info(cacheNotes.last)
    }
  }

  // ---- report read: fetch size ---------------------------------------------------------------------------------------

  private val ReadRuns = 5
  private val ReadFetchSize = 1000
  private case class ReadMeasure(rows: Int, peakHeapMb: Double, allocatedMb: Double, endToEndMs: Double)
  private case class ReadComparison(target: Target, volume: String, whole: ReadMeasure, batched: ReadMeasure)

  private val readComparisons = ArrayBuffer.empty[ReadComparison]
  private val readNotes = ArrayBuffer.empty[String]

  private def usedHeap(): Long = { val runtime = Runtime.getRuntime; runtime.totalMemory - runtime.freeMemory }

  private val threadBean = ManagementFactory.getThreadMXBean match {
    case threadMxBean: com.sun.management.ThreadMXBean => Some(threadMxBean)
    case _ => None
  }

  /** One report read. Returns rows, peak heap above the heap in use before the read (a sampler thread polls the heap while
    * getPrintData runs; garbage is included), bytes allocated by this thread and the end-to-end time.
    */
  private def readOnce(format: MPrintFormat, fetchSize: Int, transactionName: String): (Int, Double, Double, Double) = {
    System.gc()
    val baseline = usedHeap()
    @volatile var peak = baseline
    @volatile var running = true
    val sampler = new Thread(() => while (running) { peak = math.max(peak, usedHeap()); Thread.`yield`() })
    sampler.setDaemon(true)
    val allocatedBefore = threadBean.map(_.getThreadAllocatedBytes(Thread.currentThread.getId)).getOrElse(0L)
    sampler.start()
    val start = System.nanoTime()
    val rows =
      try new DataEngine(language, transactionName).setLookupJoin(true).setFetchSize(fetchSize)
        .getPrintData(Env.getCtx, format, new MQuery(), false, 0).getRowCount
      finally running = false
    val elapsed = (System.nanoTime() - start) / 1e6
    sampler.join()
    val allocated = threadBean.map(_.getThreadAllocatedBytes(Thread.currentThread.getId)).getOrElse(0L) - allocatedBefore
    (rows, math.max(peak - baseline, 0L) / 1048576.0, allocated / 1048576.0, elapsed)
  }

  /** fetch size 0 and fetch size 1000 interleaved: median of ReadRuns runs each */
  private def measureRead(target: Target, volume: String, transactionName: String): ReadComparison = {
    val format = MPrintFormat.get(Env.getCtx, target.formatId, true)
    readOnce(format, 0, transactionName); readOnce(format, ReadFetchSize, transactionName) // warmup
    val runs = (1 to ReadRuns).map(_ => (readOnce(format, 0, transactionName), readOnce(format, ReadFetchSize, transactionName)))
    def summary(pick: ((Int, Double, Double, Double), (Int, Double, Double, Double)) => (Int, Double, Double, Double)) = {
      val values = runs.map(run => pick(run._1, run._2))
      ReadMeasure(values.head._1, median(values.map(_._2)), median(values.map(_._3)), median(values.map(_._4)))
    }
    ReadComparison(target, volume, summary((whole, _) => whole), summary((_, batched) => batched))
  }

  private def assertSameReadRows(comparison: ReadComparison): Unit =
    assert(comparison.whole.rows == comparison.batched.rows, s"${comparison.target.table} ${comparison.volume}: ${comparison.whole.rows} rows with fetch size 0, ${comparison.batched.rows} with $ReadFetchSize")

  Feature("Report read: fetch size 0 (whole result at once) against REPORT_FETCH_SIZE 1000") {
    info("Opt-in: set ADEMPIERE_BENCHMARK=true. Peak heap is sampled while DataEngine.getPrintData runs; end-to-end time is the median of 5 runs")

    targets.foreach { target =>
      Scenario(s"Report read, natural volume: ${target.table} (print format ${target.formatId})") {
        requireEnabled()
        Given("the production path: no transaction (with fetch size the engine reads inside a short transaction of its own)")
        val comparison = measureRead(target, "natural", null)
        readComparisons += comparison
        info(s"${comparison.whole.rows} rows, peak heap ${formatNumber(comparison.whole.peakHeapMb)} -> ${formatNumber(comparison.batched.peakHeapMb)} MB, " +
          s"time ${formatNumber(comparison.whole.endToEndMs)} -> ${formatNumber(comparison.batched.endToEndMs)} ms")
        Then("both read the same number of rows")
        assertSameReadRows(comparison)
      }
    }

    Scenario("Report read, scaled volume: rows multiplied inside a transaction that is always rolled back") {
      requireEnabled()
      assume(DB.isPostgreSQL, "The scaled scenario needs PostgreSQL (generate_series); skipped on this database")
      val before = scaleTables.map(scaledTable => scaledTable.name -> count(scaledTable.name))
      val outcome = Try {
        inRolledBackTransaction { transaction =>
          scaleTables.foreach { scaledTable =>
            scaleTable(scaledTable, transaction)
            if (count(scaledTable.name) != before.toMap.apply(scaledTable.name))
              throw new IllegalStateException(s"Refusing to continue: ${scaledTable.name} rows are visible outside the transaction")
          }
          readNotes += s"Scale $scale: " + scaleTables.map(scaledTable => s"${scaledTable.name} ${before.toMap.apply(scaledTable.name)} -> ${count(scaledTable.name, transaction)} rows").mkString(", ")
          targets.map(target => measureRead(target, s"scaled x$scale", transaction.getTrxName))
        }
      }
      val after = scaleTables.map(scaledTable => scaledTable.name -> count(scaledTable.name))
      Then("no row is left behind")
      assert(after == before, s"rows left in the database: before $before, after $after")
      val scaled = outcome.get
      readComparisons ++= scaled
      And("both read the same number of rows")
      scaled.foreach(comparison => info(s"${comparison.target.table}: ${comparison.whole.rows} rows, peak heap ${formatNumber(comparison.whole.peakHeapMb)} -> ${formatNumber(comparison.batched.peakHeapMb)} MB, time ${formatNumber(comparison.whole.endToEndMs)} -> ${formatNumber(comparison.batched.endToEndMs)} ms"))
      scaled.foreach(assertSameReadRows)
    }
  }

  private def formatNumber(value: Double): String = String.format(Locale.ROOT, "%.2f", Double.box(value))

  private def ratio(legacy: Double, join: Double): String = if (join <= 0) "n/a" else String.format(Locale.ROOT, "%.2fx", Double.box(legacy / join))

  private def explainCell(explain: Option[Explain])(projection: Explain => String): String = explain.map(projection).getOrElse("n/a")

  private def report: String = {
    val out = new StringBuilder
    def line(text: String = ""): Unit = out.append(text).append('\n')
    val database = if (DB.isPostgreSQL) DB.getSQLValueString(null, "SELECT version()") else DB.getDatabase.getName
    line("# Report lookup benchmark: correlated subselects (legacy) against LEFT OUTER JOINs (join)")
    line()
    line(s"* Date: ${LocalDateTime.now}")
    line(s"* Database: $database, language $English, Java ${System.getProperty("java.version")}")
    line(s"* Fetch: $WarmupRuns warmup runs, then $MeasuredRuns measured runs interleaved legacy/join; median and p95 (nearest rank)")
    line(s"* End-to-end: median of $EndToEndRuns `DataEngine.getPrintData` calls; EXPLAIN: `EXPLAIN (ANALYZE, BUFFERS)` of the exact PrintData SQL")
    line(s"* Scale factor ADEMPIERE_BENCHMARK_SCALE: $scale")
    line("* `DISCARD PLANS` runs before each format; planner statistics are not refreshed on the scaled rows (ANALYZE would change pg_class outside the transaction)")
    notes.foreach(note => line(s"* $note"))
    line("* Speedup = legacy / join (greater than 1.00x: the join path is faster)")
    line()
    line("## Summary")
    line()
    line("| Format | Table | Volume | Rows | Fetch median speedup | Fetch p95 speedup | Execution speedup | End-to-end speedup |")
    line("|---|---|---|---:|---:|---:|---:|---:|")
    comparisons.foreach { comparison =>
      val executionSpeedup = (comparison.legacy.explain, comparison.join.explain) match {
        case (Some(legacyExplain), Some(joinExplain)) => ratio(legacyExplain.executionMs, joinExplain.executionMs)
        case _ => "n/a"
      }
      line(s"| ${comparison.target.formatId} | ${comparison.target.table} | ${comparison.volume} | ${comparison.legacy.rows} | ${ratio(comparison.legacy.fetch.medianMs, comparison.join.fetch.medianMs)} | " +
        s"${ratio(comparison.legacy.fetch.p95Ms, comparison.join.fetch.p95Ms)} | $executionSpeedup | ${ratio(comparison.legacy.endToEndMs, comparison.join.endToEndMs)} |")
    }
    targets.foreach { target =>
      val rows = comparisons.filter(_.target == target)
      if (rows.nonEmpty) {
        line()
        line(s"## Print format ${target.formatId}: ${target.table}")
        line()
        line(s"Why: ${target.why}.")
        line()
        line("| Volume | Path | Rows | SubPlans | SubPlan loops | Buffers | Planning ms | Execution ms | Fetch median ms | Fetch p95 ms | End-to-end ms |")
        line("|---|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|")
        rows.foreach { comparison =>
          Seq("legacy" -> comparison.legacy, "join" -> comparison.join).foreach { case (path, measurement) =>
            line(s"| ${comparison.volume} | $path | ${measurement.rows} | ${explainCell(measurement.explain)(_.subPlans.toString)} | ${explainCell(measurement.explain)(_.subPlanLoops.toString)} | " +
              s"${explainCell(measurement.explain)(_.buffers.toString)} | ${explainCell(measurement.explain)(explain => formatNumber(explain.planningMs))} | ${explainCell(measurement.explain)(explain => formatNumber(explain.executionMs))} | " +
              s"${formatNumber(measurement.fetch.medianMs)} | ${formatNumber(measurement.fetch.p95Ms)} | ${formatNumber(measurement.endToEndMs)} |")
          }
          val (planning, execution) = (comparison.legacy.explain, comparison.join.explain) match {
            case (Some(legacyExplain), Some(joinExplain)) => (ratio(legacyExplain.planningMs, joinExplain.planningMs), ratio(legacyExplain.executionMs, joinExplain.executionMs))
            case _ => ("n/a", "n/a")
          }
          line(s"| ${comparison.volume} | **speedup** | | | | | $planning | $execution | ${ratio(comparison.legacy.fetch.medianMs, comparison.join.fetch.medianMs)} | " +
            s"${ratio(comparison.legacy.fetch.p95Ms, comparison.join.fetch.p95Ms)} | ${ratio(comparison.legacy.endToEndMs, comparison.join.endToEndMs)} |")
        }
      }
    }
    out.toString
  }

  private def cacheReport: String = {
    val out = new StringBuilder
    def line(text: String = ""): Unit = out.append(text).append('\n')
    line()
    line("# Report definition cache: dictionary read on every run (off) against the cache")
    line()
    line(s"* Definition = `DataEngine.getPrintData` of a query that returns no row (what remains is reading and building the definition): median of $DefinitionRuns runs")
    line(s"* End-to-end = the real query, median of $EndToEndRuns runs, joins, en_US, committed data, no transaction")
    line("* off: the dictionary is read on every run. cold: the cache (and the MTable/MColumn caches) is emptied before every run, so the definition is built and stored. warm: cache hits")
    line("* Dictionary statements = statements on AD_PrintFormat, AD_PrintFormatItem, AD_Column, AD_Table, AD_Ref_Table, AD_ReportView and AD_ReportView_Col of one run, from `pg_stat_statements` (n/a without it)")
    cacheNotes.foreach(note => line(s"* $note"))
    line("* Speedup = off / warm")
    line()
    line("| Format | Table | Mode | Dictionary statements | Definition ms | End-to-end ms | Rows |")
    line("|---|---|---|---:|---:|---:|---:|")
    cacheComparisons.foreach { comparison =>
      Seq(comparison.off, comparison.cold, comparison.warm).foreach { measurement =>
        line(s"| ${comparison.target.formatId} | ${comparison.target.table} | ${measurement.mode} | ${measurement.dictionaryStatements.map(_.toString).getOrElse("n/a")} | ${formatNumber(measurement.definitionMs)} | ${formatNumber(measurement.endToEndMs)} | ${measurement.rows} |")
      }
      line(s"| ${comparison.target.formatId} | ${comparison.target.table} | **speedup off/warm** | | ${ratio(comparison.off.definitionMs, comparison.warm.definitionMs)} | ${ratio(comparison.off.endToEndMs, comparison.warm.endToEndMs)} | |")
    }
    if (sweepRows.nonEmpty) {
      line()
      line("## All table based print formats, one pass (definition only)")
      line()
      line("| Mode | Print formats | Pass ms | Per format ms |")
      line("|---|---:|---:|---:|")
      sweepRows.foreach(sweepRow => line(s"| ${sweepRow.mode} | ${sweepRow.formats} | ${formatNumber(sweepRow.totalMs)} | ${formatNumber(sweepRow.totalMs / sweepRow.formats)} |"))
      if (sweepRows.size == 3) line(s"| **speedup off/warm** | | ${ratio(sweepRows.head.totalMs, sweepRows.last.totalMs)} | |")
    }
    out.toString
  }

  private def readReport: String = {
    val out = new StringBuilder
    def line(text: String = ""): Unit = out.append(text).append('\n')
    line()
    line(s"# Report read: whole result at once (fetch size 0) against fetch size $ReadFetchSize")
    line()
    line(s"* Natural volume: no transaction (the engine opens a short read transaction of its own for the fetch size). Scaled volume: inside the rolled back transaction of the scale (factor $scale)")
    line(s"* Median of $ReadRuns runs each, interleaved, after a warmup; `System.gc()` before each run")
    line("* Peak heap = the highest heap in use that a sampler thread saw while `getPrintData` ran, minus the heap in use before it (garbage included, so indicative); both runs keep the same `PrintData` result")
    line("* Allocated = bytes allocated by the thread that runs the report (`ThreadMXBean`); n/a when the JVM does not provide it")
    readNotes.foreach(note => line(s"* $note"))
    line("* Ratio = fetch size 0 / fetch size " + ReadFetchSize + " (greater than 1.00x: the fetch size is better)")
    line()
    line("| Format | Table | Volume | Rows | Peak heap MB (0) | Peak heap MB (1000) | Heap ratio | Allocated MB (0) | Allocated MB (1000) | Time ms (0) | Time ms (1000) | Time ratio |")
    line("|---|---|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|")
    readComparisons.foreach { comparison =>
      line(s"| ${comparison.target.formatId} | ${comparison.target.table} | ${comparison.volume} | ${comparison.whole.rows} | ${formatNumber(comparison.whole.peakHeapMb)} | ${formatNumber(comparison.batched.peakHeapMb)} | ${ratio(comparison.whole.peakHeapMb, comparison.batched.peakHeapMb)} | " +
        s"${formatNumber(comparison.whole.allocatedMb)} | ${formatNumber(comparison.batched.allocatedMb)} | ${formatNumber(comparison.whole.endToEndMs)} | ${formatNumber(comparison.batched.endToEndMs)} | ${ratio(comparison.whole.endToEndMs, comparison.batched.endToEndMs)} |")
    }
    out.toString
  }

  private def writeReport(): Unit = if (comparisons.nonEmpty || cacheComparisons.nonEmpty || readComparisons.nonEmpty) {
    val text = (if (comparisons.nonEmpty) report else "") + (if (cacheComparisons.nonEmpty) cacheReport else "") + (if (readComparisons.nonEmpty) readReport else "")
    val path = sys.env.get("ADEMPIERE_BENCHMARK_REPORT").filter(_.trim.nonEmpty).map(new File(_)).getOrElse {
      val module = new File("org.adempiere.test")
      new File(if (module.isDirectory) "org.adempiere.test/target" else "target", "report-lookup-join-benchmark.md")
    }
    Option(path.getAbsoluteFile.getParentFile).foreach(_.mkdirs())
    Files.write(path.toPath, text.getBytes(StandardCharsets.UTF_8))
    println(text)
    println(s"Benchmark report written to ${path.getAbsolutePath}")
  }
}
