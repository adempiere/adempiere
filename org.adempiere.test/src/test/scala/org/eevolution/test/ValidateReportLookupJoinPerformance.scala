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
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.sql.ResultSet
import java.time.LocalDateTime
import java.util.Locale

import org.compiere.model.MQuery
import org.compiere.print.{DataEngine, MPrintFormat}
import org.compiere.util.{DB, Env, Language, Trx}
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
  private val scale: Int = sys.env.get("ADEMPIERE_BENCHMARK_SCALE").flatMap(v => Try(v.trim.toInt).toOption).filter(_ >= 1).getOrElse(200)

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
  private def inRolledBackTrx[T](body: Trx => T): T = {
    val trx = Trx.get(Trx.createTrxName("RLJBenchmark_"), true)
    try {
      val connection = trx.getConnection
      if (connection == null || connection.getAutoCommit)
        throw new IllegalStateException("Refusing to run: the benchmark transaction would not be rolled back (autocommit connection)")
      body(trx)
    } finally {
      trx.rollback()
      trx.close()
    }
  }

  private def count(table: String, trx: Trx = null): Int =
    DB.getSQLValue(if (trx == null) null else trx.getTrxName, s"SELECT COUNT(*) FROM $table")

  private def median(values: Seq[Double]): Double = {
    val sorted = values.sorted
    val n = sorted.size
    if (n % 2 == 1) sorted(n / 2) else (sorted(n / 2 - 1) + sorted(n / 2)) / 2
  }

  private def percentile95(values: Seq[Double]): Double = {
    val sorted = values.sorted
    sorted(math.ceil(0.95 * sorted.size).toInt - 1)
  }

  private def loadData(format: MPrintFormat, join: Boolean, trx: Trx) = {
    val engine = new DataEngine(language, trx.getTrxName)
    engine.setLookupJoin(join)
    engine.getPrintData(Env.getCtx, format, new MQuery(), false, 0)
  }

  private val SubPlanHeader = """^SubPlan \d+\b.*""".r
  private val Loops = """loops=(\d+)""".r
  private val Time = """(Planning|Execution) Time: ([0-9.]+) ms""".r
  private val SharedBuffers = """(?:hit|read)=(\d+)""".r

  /** EXPLAIN (ANALYZE, BUFFERS) of the SQL as the database receives it, through the connection of the transaction */
  private def explain(sql: String, trx: Trx): Option[Explain] =
    if (!DB.isPostgreSQL) None
    else {
      val statement = trx.getConnection.createStatement()
      var rs: ResultSet = null
      try {
        rs = statement.executeQuery("EXPLAIN (ANALYZE, BUFFERS) " + DB.getDatabase.convertStatement(sql))
        val lines = Iterator.continually(rs).takeWhile(_.next()).map(_.getString(1)).toVector
        val times = lines.flatMap(l => Time.findFirstMatchIn(l).map(m => m.group(1) -> m.group(2).toDouble)).toMap
        val subPlanStarts = lines.indices.filter(i => SubPlanHeader.matches(lines(i).trim))
        // the loops of a SubPlan are the loops of its first child node
        val loops = subPlanStarts.map { i =>
          lines.drop(i + 1).find(_.contains("->")).map { child =>
            if (child.contains("never executed")) 0L else Loops.findFirstMatchIn(child).map(_.group(1).toLong).getOrElse(0L)
          }.getOrElse(0L)
        }
        // shared buffers (hit + read) of the top plan node: the whole statement, SubPlans included
        val buffers = lines.find(_.trim.startsWith("Buffers:")).map { l =>
          SharedBuffers.findAllMatchIn(l.substring(l.indexOf("shared")).takeWhile(_ != ',')).map(_.group(1).toLong).sum
        }.getOrElse(0L)
        Some(Explain(times.getOrElse("Planning", 0.0), times.getOrElse("Execution", 0.0), subPlanStarts.size, loops.sum, buffers))
      } finally DB.close(rs, statement)
    }

  /** Executes the SQL and reads every cell of every row. Only the execution and the fetch are timed. */
  private def fetchAll(sql: String, trx: Trx): (Int, Double) = {
    val statement = DB.prepareStatement(sql, trx.getTrxName)
    var rs: ResultSet = null
    var rows = 0
    try {
      val start = System.nanoTime
      rs = statement.executeQuery()
      val columns = rs.getMetaData.getColumnCount
      while (rs.next()) {
        var i = 1
        while (i <= columns) {
          rs.getObject(i)
          i += 1
        }
        rows += 1
      }
      (rows, (System.nanoTime - start) / 1e6)
    } finally DB.close(rs, statement)
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
  private def discardCachedPlans(trx: Trx): Unit = if (DB.isPostgreSQL) {
    val statement = trx.getConnection.createStatement()
    try statement.execute("DISCARD PLANS")
    finally statement.close()
  }

  private def measure(target: Target, volume: String, trx: Trx): Comparison = {
    discardCachedPlans(trx)
    val format = MPrintFormat.get(Env.getCtx, target.formatId, true)
    val legacyData = loadData(format, join = false, trx)
    val joinData = loadData(format, join = true, trx)
    val legacySql = legacyData.getSQL
    val joinSql = joinData.getSQL
    dumpSql(target, legacySql, joinSql)
    val legacyExplain = explain(legacySql, trx)
    val joinExplain = explain(joinSql, trx)

    // warmup, then measured iterations interleaved legacy/join to spread the cache effects over both paths
    (1 to WarmupRuns).foreach { _ =>
      fetchAll(legacySql, trx)
      fetchAll(joinSql, trx)
    }
    val legacyFetch = ArrayBuffer.empty[Double]
    val joinFetch = ArrayBuffer.empty[Double]
    var legacyRows = 0
    var joinRows = 0
    (1 to MeasuredRuns).foreach { _ =>
      val (lr, lt) = fetchAll(legacySql, trx)
      val (jr, jt) = fetchAll(joinSql, trx)
      legacyRows = lr
      joinRows = jr
      legacyFetch += lt
      joinFetch += jt
    }

    val legacyEndToEnd = ArrayBuffer.empty[Double]
    val joinEndToEnd = ArrayBuffer.empty[Double]
    (1 to EndToEndRuns).foreach { _ =>
      val l0 = System.nanoTime
      loadData(format, join = false, trx)
      legacyEndToEnd += (System.nanoTime - l0) / 1e6
      val j0 = System.nanoTime
      loadData(format, join = true, trx)
      joinEndToEnd += (System.nanoTime - j0) / 1e6
    }

    Comparison(
      target,
      volume,
      Measure(legacyRows, legacyExplain, Fetch(legacyRows, median(legacyFetch.toSeq), percentile95(legacyFetch.toSeq)), median(legacyEndToEnd.toSeq)),
      Measure(joinRows, joinExplain, Fetch(joinRows, median(joinFetch.toSeq), percentile95(joinFetch.toSeq)), median(joinEndToEnd.toSeq))
    )
  }

  private def assertSameRows(c: Comparison): Unit =
    assert(
      c.legacy.rows == c.join.rows,
      s"format ${c.target.formatId} (${c.volume}): legacy fetched ${c.legacy.rows} rows, join fetched ${c.join.rows}"
    )

  private def regression(c: Comparison): Option[String] = {
    val legacy = c.legacy.fetch.medianMs
    val join = c.join.fetch.medianMs
    if (join > legacy * RegressionFactor && join - legacy > RegressionMillis)
      Some(f"format ${c.target.formatId} ${c.target.table} (${c.volume}): join fetch median $join%.2f ms vs legacy $legacy%.2f ms")
    else None
  }

  private def assertNoRegression(cs: Seq[Comparison]): Unit = {
    val regressions = cs.flatMap(regression)
    assert(regressions.isEmpty, "join path regression: " + regressions.mkString("; "))
  }

  /** Copies every row of the table scale-1 more times with new keys above the existing ones. Foreign keys stay valid
    * because they are copied as they are. Returns the rows inserted. Only DML: no DDL, no ANALYZE (it would update pg_class outside the transaction).
    */
  private def scaleTable(table: ScaleTable, trx: Trx): Int = {
    val connection = trx.getConnection
    if (connection.getAutoCommit) throw new IllegalStateException(s"Refusing to scale ${table.name}: the transaction would not be rolled back")
    val statement = connection.createStatement()
    try {
      val metaData = statement.executeQuery(s"SELECT * FROM ${table.name} WHERE 1=0").getMetaData
      val columns = (1 to metaData.getColumnCount).map(metaData.getColumnName)
      val rows = count(table.name, trx)
      val maxKey = DB.getSQLValueBD(trx.getTrxName, s"SELECT MAX(${table.key}) FROM ${table.name}").longValue
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
        val comparison = inRolledBackTrx(trx => measure(target, "natural", trx))
        comparisons += comparison
        Then("both paths return the same number of rows and the join path is not clearly slower")
        info(s"${comparison.legacy.rows} rows, fetch median legacy ${fmt(comparison.legacy.fetch.medianMs)} ms, join ${fmt(comparison.join.fetch.medianMs)} ms")
        assertSameRows(comparison)
        assertNoRegression(Seq(comparison))
      }
    }

    Scenario("Scaled volume: rows multiplied inside a transaction that is always rolled back") {
      requireEnabled()
      assume(DB.isPostgreSQL, "The scaled scenario needs PostgreSQL (generate_series); skipped on this database")
      Given(s"${scaleTables.map(_.name).mkString(" and ")} multiplied by $scale with INSERT ... SELECT in the test transaction")
      val before = scaleTables.map(t => t.name -> count(t.name))
      val outcome = Try {
        inRolledBackTrx { trx =>
          val inserted = scaleTables.map { t =>
            val rows = scaleTable(t, trx)
            val outside = count(t.name)
            // a second connection must not see the rows: this proves that nothing was committed
            if (outside != before.toMap.apply(t.name))
              throw new IllegalStateException(s"Refusing to continue: ${t.name} rows are visible outside the transaction ($outside)")
            t.name -> rows
          }
          val scaledCounts = scaleTables.map(t => t.name -> count(t.name, trx))
          notes += s"Scale $scale: " + scaleTables.indices.map { i =>
            s"${scaleTables(i).name} ${before(i)._2} -> ${scaledCounts(i)._2} rows (+${inserted(i)._2})"
          }.mkString(", ")
          targets.map(target => measure(target, s"scaled x$scale", trx))
        }
      }
      val after = scaleTables.map(t => t.name -> count(t.name))
      Then("no row is left behind: the counts before and after the rolled back transaction are equal")
      notes += "Row counts outside the transaction before: " + before.map { case (t, c) => s"$t=$c" }.mkString(", ") +
        "; after the rollback: " + after.map { case (t, c) => s"$t=$c" }.mkString(", ")
      assert(after == before, s"rows left in the database: before $before, after $after")
      val scaled = outcome.get
      comparisons ++= scaled
      And("both paths return the same number of rows and the join path is not clearly slower")
      scaled.foreach(c => info(s"${c.target.table}: ${c.legacy.rows} rows, fetch median legacy ${fmt(c.legacy.fetch.medianMs)} ms, join ${fmt(c.join.fetch.medianMs)} ms"))
      scaled.foreach(assertSameRows)
      assertNoRegression(scaled)
    }
  }

  private def fmt(value: Double): String = String.format(Locale.ROOT, "%.2f", Double.box(value))

  private def ratio(legacy: Double, join: Double): String = if (join <= 0) "n/a" else String.format(Locale.ROOT, "%.2fx", Double.box(legacy / join))

  private def explainCell(e: Option[Explain])(f: Explain => String): String = e.map(f).getOrElse("n/a")

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
    notes.foreach(n => line(s"* $n"))
    line("* Speedup = legacy / join (greater than 1.00x: the join path is faster)")
    line()
    line("## Summary")
    line()
    line("| Format | Table | Volume | Rows | Fetch median speedup | Fetch p95 speedup | Execution speedup | End-to-end speedup |")
    line("|---|---|---|---:|---:|---:|---:|---:|")
    comparisons.foreach { c =>
      val executionSpeedup = (c.legacy.explain, c.join.explain) match {
        case (Some(l), Some(j)) => ratio(l.executionMs, j.executionMs)
        case _ => "n/a"
      }
      line(s"| ${c.target.formatId} | ${c.target.table} | ${c.volume} | ${c.legacy.rows} | ${ratio(c.legacy.fetch.medianMs, c.join.fetch.medianMs)} | " +
        s"${ratio(c.legacy.fetch.p95Ms, c.join.fetch.p95Ms)} | $executionSpeedup | ${ratio(c.legacy.endToEndMs, c.join.endToEndMs)} |")
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
        rows.foreach { c =>
          Seq("legacy" -> c.legacy, "join" -> c.join).foreach { case (path, m) =>
            line(s"| ${c.volume} | $path | ${m.rows} | ${explainCell(m.explain)(_.subPlans.toString)} | ${explainCell(m.explain)(_.subPlanLoops.toString)} | " +
              s"${explainCell(m.explain)(_.buffers.toString)} | ${explainCell(m.explain)(e => fmt(e.planningMs))} | ${explainCell(m.explain)(e => fmt(e.executionMs))} | " +
              s"${fmt(m.fetch.medianMs)} | ${fmt(m.fetch.p95Ms)} | ${fmt(m.endToEndMs)} |")
          }
          val (planning, execution) = (c.legacy.explain, c.join.explain) match {
            case (Some(l), Some(j)) => (ratio(l.planningMs, j.planningMs), ratio(l.executionMs, j.executionMs))
            case _ => ("n/a", "n/a")
          }
          line(s"| ${c.volume} | **speedup** | | | | | $planning | $execution | ${ratio(c.legacy.fetch.medianMs, c.join.fetch.medianMs)} | " +
            s"${ratio(c.legacy.fetch.p95Ms, c.join.fetch.p95Ms)} | ${ratio(c.legacy.endToEndMs, c.join.endToEndMs)} |")
        }
      }
    }
    out.toString
  }

  private def writeReport(): Unit = if (comparisons.nonEmpty) {
    val text = report
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
