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

import java.sql.Connection
import java.util.Properties
import java.util.concurrent.{Callable, Executors, TimeUnit}

import org.adempiere.core.domains.models.{I_AD_Column, I_AD_PrintFormatItem, I_AD_Ref_Table, X_AD_Ref_Table, X_AD_ReportView, X_AD_ReportView_Col}
import org.compiere.model.{MBPartner, MColumn, MOrder, MQuery, MTable, Query}
import org.compiere.print.{DataEngine, MPrintFormat, MPrintFormatItem, PrintData, PrintDataElement, ReportDefinitionCache}
import org.compiere.util.{CacheChangeListener, CacheMgt, DB, Env, Language, Trx}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters.*
import scala.util.Try

/** Tests of the report definition cache of DataEngine (AD_PrintFormat, AD_PrintFormatItem and the dictionary behind them).
  *
  * The oracle of every scenario is the same engine with the cache switched off (setDefinitionCache(false)): the SQL
  * and the PrintData of a cache hit must be byte-identical to a definition read from the dictionary.
  *
  * "Fewer queries" is observed through pg_stat_statements (PostgreSQL), never through the storage of the cache:
  * the scenario is cancelled when the extension is not available.
  *
  * Print formats that the scenarios change are created for the scenario (committed, so the PO events reach the
  * cache like they do in production) and deleted in a finally block; the net effect on the database is nil, apart from
  * the consumed sequence numbers.
  */
class ValidateReportDefinitionCache extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val English = "en_US"
  private val Spanish = "es_MX"
  private val OrderTable = "C_Order"
  private val ProfileFormats = Vector(1000003, 1000004, 1000008) // C_Order, RV_OrderDetail, C_OrderLine: no dictionary table in their data SQL
  private val DictionaryTables = Vector("AD_PrintFormat", "AD_PrintFormatItem", "AD_Table", "AD_Column", "AD_Ref_Table", "AD_ReportView")

  private case class Cell(name: String, key: String, value: String, display: String, pKey: Boolean, displayType: Int)
  private case class Snapshot(sql: String, columns: Vector[(Int, String, String, Boolean, Int)], rows: Vector[Vector[Cell]], functionRows: Vector[Int])

  private def language(name: String) = Language.getLanguage(name)

  /** assert without the dump of the operands: the SQL of a report has thousands of characters */
  private def check(condition: Boolean, message: => String = "assertion failed"): Unit =
    if (!condition) fail(message)

  private def cells(pd: PrintData): Vector[Vector[Cell]] =
    (0 until pd.getRowCount).map { row =>
      pd.setRowIndex(row)
      (0 until pd.getNodeCount).map { index =>
        pd.getNode(index) match {
          case e: PrintDataElement =>
            Cell(e.getColumnName, e.getValueKey, String.valueOf(e.getValue), e.getValueAsString, e.isPKey, e.getDisplayType)
          case other => Cell("#", "", String.valueOf(other), "", false, -1)
        }
      }.toVector
    }.toVector

  private def snapshot(pd: PrintData): Snapshot =
    Snapshot(
      pd.getSQL,
      pd.getColumnInfo.toVector.map(c => (c.getAD_Column_ID, c.getColumnName, c.getAlias, c.hasAlias, c.getDisplayType)),
      cells(pd),
      (0 until pd.getRowCount).filter(i => pd.isFunctionRow(i)).toVector
    )

  private def multiset[T](values: Vector[T]): Map[T, Int] = values.groupBy(identity).view.mapValues(_.size).toMap

  private def storedFormat(id: Int, trx: String = null): MPrintFormat =
    if (trx == null) MPrintFormat.get(Env.getCtx, id, true) else new MPrintFormat(Env.getCtx, id, trx)

  /** One report run. cache = Some(flag) forces the definition cache, None keeps the SysConfig default (Y). */
  private def run(
      format: MPrintFormat,
      lang: String,
      join: Boolean,
      cache: Option[Boolean],
      trx: String = null,
      restrict: MQuery => Unit = _ => (),
      ctx: Properties = Env.getCtx
  ): Option[PrintData] = {
    val query = new MQuery()
    restrict(query)
    val engine = new DataEngine(language(lang), trx)
    engine.setLookupJoin(join)
    cache.foreach(flag => engine.setDefinitionCache(flag))
    Option(engine.getPrintData(ctx, format, query, false, 0))
  }

  private def sqlOf(formatId: Int, lang: String, join: Boolean, cache: Option[Boolean], trx: String = null): String =
    run(storedFormat(formatId, trx), lang, join, cache, trx).map(_.getSQL).getOrElse("")

  private def resetDefinitions(): Unit = DictionaryTables.foreach(t => CacheMgt.get().reset(t))

  private def ids(sql: String): Vector[Int] = {
    val statement = DB.prepareStatement(sql, null)
    val rs = statement.executeQuery()
    try Iterator.continually(rs).takeWhile(_.next()).map(_.getInt(1)).toVector
    finally DB.close(rs, statement)
  }

  /** Active table based print formats of clients 0 and 11, T_Report* and AD_PInstance* excluded */
  private lazy val tableBasedFormats: Vector[Int] = ids(
    "SELECT pf.AD_PrintFormat_ID FROM AD_PrintFormat pf" +
      " INNER JOIN AD_Table t ON (t.AD_Table_ID=pf.AD_Table_ID)" +
      " LEFT OUTER JOIN AD_ReportView rv ON (rv.AD_ReportView_ID=pf.AD_ReportView_ID)" +
      " LEFT OUTER JOIN AD_Table rt ON (rt.AD_Table_ID=rv.AD_Table_ID) " +
      "WHERE pf.IsActive='Y' AND pf.IsTableBased='Y' AND pf.AD_Client_ID IN (0,11)" +
      " AND t.TableName NOT LIKE 'T\\_Report%' AND t.TableName NOT LIKE 'AD\\_PInstance%'" +
      " AND COALESCE(rt.TableName,t.TableName) NOT LIKE 'T\\_Report%'" +
      " AND COALESCE(rt.TableName,t.TableName) NOT LIKE 'AD\\_PInstance%' " +
      "ORDER BY pf.AD_PrintFormat_ID"
  )

  /**
    * The values of the columns that a second run without cache also changes (views that select the current timestamp)
    * cannot be compared; every other column must have the same values. Without volatile columns this is a plain comparison.
    */
  private def differsOutsideVolatile(expected: Snapshot, actual: Snapshot, control: Snapshot): Boolean = {
    def column(snapshot: Snapshot, index: Int) = multiset(snapshot.rows.map(row => row.lift(index).map(_.value)))
    val width = (expected.rows ++ actual.rows ++ control.rows).map(_.size).maxOption.getOrElse(0)
    val stable = (0 until width).filter(index => column(expected, index) == column(control, index))
    def project(snapshot: Snapshot) = multiset(snapshot.rows.map(row => stable.map(index => row.lift(index))))
    project(expected) != project(actual)
  }

  /** Same data, or (views that select the current timestamp) the same data as a second run without cache */
  private def sameData(expected: Snapshot, actual: Snapshot, control: () => Snapshot): Vector[String] = {
    val problems = ArrayBuffer.empty[String]
    if (expected.sql != actual.sql) problems += {
      val at = expected.sql.zip(actual.sql).indexWhere { case (a, b) => a != b } match { case -1 => math.min(expected.sql.length, actual.sql.length) case i => i }
      s"SQL differs at $at: off ...${expected.sql.slice(at - 30, at + 70)} / on ...${actual.sql.slice(at - 30, at + 70)}"
    }
    if (expected.columns != actual.columns) problems += "columns differ"
    if (expected.functionRows != actual.functionRows) problems += "function rows differ"
    if (expected.rows.size != actual.rows.size) problems += s"row count ${expected.rows.size} <> ${actual.rows.size}"
    else if (multiset(expected.rows) != multiset(actual.rows) && differsOutsideVolatile(expected, actual, control()))
      problems += "cell values differ"
    else if (expected.rows != actual.rows && multiset(expected.rows) == multiset(actual.rows)) {
      // same cells in another order: only a problem when the control keeps the order
      if (expected.rows == control().rows) problems += "row order differs"
    }
    problems.toVector
  }

  // ---- pg_stat_statements --------------------------------------------------------------------------------------

  private case class Stat(query: String, calls: Long, ms: Double)

  private def withStatConnection[T](body: Connection => T): T = {
    val connection = DB.createConnection(true, Connection.TRANSACTION_READ_COMMITTED)
    try body(connection)
    finally connection.close()
  }

  /** statements of this database, by query id; None when pg_stat_statements is not available */
  private def statements(): Option[Map[Long, Stat]] =
    if (!DB.isPostgreSQL) None
    else Try(withStatConnection { connection =>
      val statement = connection.createStatement()
      val rs = statement.executeQuery(
        "SELECT queryid, query, calls, total_exec_time FROM pg_stat_statements " +
          "WHERE dbid = (SELECT oid FROM pg_database WHERE datname = current_database()) AND query NOT ILIKE '%pg_stat_statements%'")
      try Iterator.continually(rs).takeWhile(_.next()).map(r => r.getLong(1) -> Stat(r.getString(2), r.getLong(3), r.getDouble(4))).toMap
      finally { rs.close(); statement.close() }
    }).toOption

  private def delta(before: Map[Long, Stat], after: Map[Long, Stat]): Vector[Stat] =
    after.toVector.flatMap { case (id, now) =>
      val was = before.get(id)
      val calls = now.calls - was.map(_.calls).getOrElse(0L)
      if (calls > 0) Some(Stat(now.query, calls, now.ms - was.map(_.ms).getOrElse(0.0))) else None
    }

  private val DictionaryPattern = """(?i)\b(ad_printformat|ad_printformatitem|ad_reportview|ad_reportview_col|ad_ref_table|ad_column|ad_table)\b""".r

  /** the data statement of a joined report carries the tag; it can read a dictionary table as a lookup (AD_Table_ID of Fact_Acct) */
  private def isDictionary(stat: Stat): Boolean = DictionaryPattern.findFirstIn(stat.query).isDefined && !stat.query.contains("/* AD_PrintFormat_ID=")

  private def kind(stat: Stat): String = {
    val q = stat.query.toLowerCase
    if (q.contains("ad_printformatitem") && q.contains("ad_printformat ")) "print format + items + columns + report view columns"
    else if (q.contains("ad_reportview ")) "AD_Table + AD_ReportView"
    else if (q.contains("ad_ref_table")) "getTableReference"
    else if (q.contains("isidentifier")) "getTableDirEmbed"
    else if (q.contains("isview")) "TableDirEmbed.isJoinable"
    else "MTable / MColumn / other dictionary"
  }

  private def profile(title: String, deltas: Vector[Stat]): Unit = {
    val dictionary = deltas.filter(isDictionary)
    val byKind = dictionary.groupBy(kind).toVector.sortBy(-_._2.map(_.calls).sum)
    info(f"$title: ${deltas.map(_.calls).sum} statements (${deltas.map(_.ms).sum}%.1f ms), ${dictionary.map(_.calls).sum} dictionary statements (${dictionary.map(_.ms).sum}%.1f ms)")
    byKind.foreach { case (k, stats) => info(f"    $k: ${stats.map(_.calls).sum} calls, ${stats.map(_.ms).sum}%.2f ms") }
    deltas.filterNot(isDictionary).sortBy(-_.calls).take(4).foreach(s => info(f"    other: ${s.calls} x ${s.query.take(90).replace('\n', ' ')}"))
  }

  private def measureRun(formats: Seq[MPrintFormat], lang: String, join: Boolean, cache: Boolean): Option[Vector[Stat]] =
    statements().map { before =>
      formats.foreach(f => run(f, lang, join, Some(cache)))
      delta(before, statements().get)
    }

  // ---- fixtures ------------------------------------------------------------------------------------------------

  /** A committed print format of C_Order, deleted by the body's caller; items are created like the print format window does */
  private def withTemporaryFormat[T](body: (Int, String) => T): T = {
    val format = MPrintFormat.createFromTable(Env.getCtx, MTable.getTable_ID(OrderTable), 0, null)
    check(format != null && format.get_ID > 0, "the temporary print format could not be created")
    val id = format.get_ID
    try body(id, OrderTable)
    finally {
      Try(new Query(Env.getCtx, I_AD_PrintFormatItem.Table_Name, "AD_PrintFormat_ID=?", null).setParameters(Integer.valueOf(id)).list[MPrintFormatItem]().asScala.foreach(_.deleteEx(true)))
      Try(new MPrintFormat(Env.getCtx, id, null).deleteEx(true))
      CacheMgt.get().reset("AD_PrintFormat")
    }
  }

  private def itemOf(formatId: Int, column: String, trx: String = null): MPrintFormatItem = {
    val columnId = MColumn.getColumn_ID(OrderTable, column)
    new Query(Env.getCtx, I_AD_PrintFormatItem.Table_Name, "AD_PrintFormat_ID=? AND AD_Column_ID=?", trx)
      .setParameters(Integer.valueOf(formatId), Integer.valueOf(columnId)).first[MPrintFormatItem]()
  }

  /** The SQL of the temporary format: join path and legacy path, cache on, after a warm-up run, against the oracle */
  private def assertMatchesDictionary(formatId: Int, why: String): Unit = {
    Seq(true, false).foreach { join =>
      Seq(English, Spanish).foreach { lang =>
        val oracle = snapshot(run(storedFormat(formatId), lang, join, Some(false)).get)
        val cached = snapshot(run(storedFormat(formatId), lang, join, Some(true)).get)
        val problems = sameData(oracle, cached, () => snapshot(run(storedFormat(formatId), lang, join, Some(false)).get))
        check(problems.isEmpty, s"$why ($lang, join=$join): the cached definition is stale: ${problems.mkString("; ")}")
      }
    }
  }

  private def orderByOf(sql: String): String = """[ \n]ORDER BY """.r.findAllMatchIn(sql).toVector.lastOption.map(m => sql.substring(m.start + 1)).getOrElse("")

  private def printedColumns(sql: String): Vector[String] =
    """(?:C_Order\.|AS )(\w+)""".r.findAllMatchIn(sql.substring(0, """\sFROM\s""".r.findFirstMatchIn(sql).get.start)).map(_.group(1)).toVector

  Feature("Report definition cache") {

    Scenario("The comparison of the data ignores the columns that change between two runs and no other") {
      Given("a view whose first column is the current timestamp and whose second column is stable")
      def snap(stamp: String, value: String) = Snapshot("SELECT", Vector.empty,
        Vector(Vector(Cell("Now", "", stamp, stamp, false, 0), Cell("Name", "", value, value, false, 0))), Vector.empty)
      val expected = snap("t1", "A")
      val control = () => snap("t2", "A")
      Then("a new timestamp is not a difference")
      check(sameData(expected, snap("t3", "A"), control).isEmpty)
      And("a changed stable column is a difference, even when the timestamp changed too")
      check(sameData(expected, snap("t3", "B"), control).contains("cell values differ"))
      check(sameData(expected, snap("t1", "B"), control).contains("cell values differ"))
    }

    Scenario("Cache on, cache off and a cache hit generate the same SQL and the same data for every table based print format") {
      Given("the active table based print formats of clients 0 and 11, in en_US and es_MX, with subselects and with joins")
      check(tableBasedFormats.nonEmpty)
      val problems = ArrayBuffer.empty[String]
      var compared = 0
      var rows = 0L
      for (lang <- Seq(English, Spanish); join <- Seq(false, true)) {
        resetDefinitions()
        tableBasedFormats.foreach { id =>
          val format = storedFormat(id)
          val off = Try(run(format, lang, join, Some(false)))
          val cold = Try(run(format, lang, join, Some(true)))
          val warm = Try(run(format, lang, join, Some(true)))
          (off.toOption.flatten, cold.toOption.flatten, warm.toOption.flatten) match {
            case (Some(o), Some(c), Some(w)) =>
              compared += 1
              rows += o.getRowCount
              val expected = snapshot(o)
              val control = () => snapshot(run(format, lang, join, Some(false)).get)
              sameData(expected, snapshot(c), control).foreach(p => problems += s"format $id $lang join=$join cold: $p")
              sameData(expected, snapshot(w), control).foreach(p => problems += s"format $id $lang join=$join warm: $p")
            case (None, None, None) => ()
            case other => problems += s"format $id $lang join=$join: off=${off.map(_.isDefined)} cold=${cold.map(_.isDefined)} warm=${warm.map(_.isDefined)}"
          }
        }
      }
      info(s"$compared print format runs compared ($rows rows) in en_US and es_MX, legacy and join")
      Then("every cold build and every cache hit equals the definition read from the dictionary")
      check(compared > 0)
      check(problems.isEmpty, problems.take(10).mkString("\n  "))
    }

    Scenario("A cached definition carries the OFFSET 0 fence of the join statement") {
      Given("the sales order print format with a cold and then a warm definition cache")
      resetDefinitions()
      val cold = sqlOf(1000003, English, join = true, Some(true))
      val warm = sqlOf(1000003, English, join = true, Some(true))
      Then("the warm statement is the cold one and closes its derived table with OFFSET 0 on PostgreSQL only")
      check(cold.nonEmpty && cold == warm)
      check(warm.contains(" OFFSET 0) C_Order\n") == DB.isPostgreSQL, warm)
      And("the legacy statement has no OFFSET")
      check(!sqlOf(1000003, English, join = false, Some(true)).contains("OFFSET"))
    }

    Scenario("A cached definition carries the key ORDER BY of a report without sort columns") {
      Given("the table based print formats whose legacy statement has no ORDER BY and no GROUP BY")
      resetDefinitions()
      val unsorted = tableBasedFormats.filter { id =>
        val legacy = sqlOf(id, English, join = false, Some(false))
        legacy.nonEmpty && !legacy.contains(" ORDER BY ") && !legacy.contains(" GROUP BY ")
      }
      val keyed = unsorted.filter(id => sqlOf(id, English, join = true, Some(false)).matches("(?s).*[ \n]ORDER BY \\w+\\.\\w+$"))
      info(s"${unsorted.size} print formats without sort columns, ${keyed.size} of them ordered by the key of the base table")
      check(keyed.nonEmpty)
      Then("a cold and a warm cache give the statement of the dictionary, key included")
      keyed.foreach { id =>
        val off = sqlOf(id, English, join = true, Some(false))
        check(sqlOf(id, English, join = true, Some(true)) == off && sqlOf(id, English, join = true, Some(true)) == off, s"format $id")
      }
    }

    Scenario("A warm getPrintData runs no dictionary query for the definition") {
      val stats = statements()
      assume(stats.isDefined, "pg_stat_statements is not available: the number of queries cannot be observed outside the cache")
      Given(s"print formats ${ProfileFormats.mkString(", ")} (no dictionary table in their data SQL), in en_US, with joins")
      val formats = ProfileFormats.map(id => storedFormat(id))
      resetDefinitions()
      When("the reports run once with the cache off (the dictionary is read every time) and once cold with the cache on")
      val off = measureRun(formats, English, join = true, cache = false).get
      resetDefinitions()
      val cold = measureRun(formats, English, join = true, cache = true).get
      And("again with the cache warm")
      val warm = measureRun(formats, English, join = true, cache = true).get
      val warmDefault = statements().flatMap { before =>
        formats.foreach(f => run(f, English, join = true, None)) // SysConfig default: the cache is on
        statements().map(after => delta(before, after))
      }.get
      profile("cache off (dictionary read on every run)", off)
      profile("cache on, cold", cold)
      profile("cache on, warm", warm)
      profile("cache on by default (no override), warm", warmDefault)
      Then("a warm run reads nothing from the dictionary and the data query is all that remains")
      check(off.filter(isDictionary).map(_.calls).sum > cold.filter(isDictionary).map(_.calls).sum || cold.filter(isDictionary).nonEmpty)
      check(warm.filter(isDictionary).isEmpty, "dictionary statements on a warm run:\n  " + warm.filter(isDictionary).map(s => s"${s.calls} x ${s.query.take(120)}").mkString("\n  "))
      check(warmDefault.filter(isDictionary).isEmpty, "the cache must be on by default")
      And("with the cache off every run reads the dictionary again")
      val offAgain = measureRun(formats, English, join = true, cache = false).get
      check(offAgain.filter(isDictionary).nonEmpty)
    }

    Scenario("An update, an insert and a delete of a print format item are visible in the next run") {
      withTemporaryFormat { (formatId, _) =>
        Given("a warm cache for a print format of C_Order")
        assertMatchesDictionary(formatId, "baseline")
        val baseline = sqlOf(formatId, English, join = true, Some(true))
        check(printedColumns(baseline).contains("Description"))

        When("IsPrinted of Description is switched off")
        val description = itemOf(formatId, "Description")
        description.setIsPrinted(false)
        description.saveEx()
        Then("the next run does not select the column")
        val hidden = sqlOf(formatId, English, join = true, Some(true))
        check(hidden != baseline && !printedColumns(hidden).contains("Description"), "IsPrinted was not reflected")
        assertMatchesDictionary(formatId, "update IsPrinted")

        When("the SeqNo of DocumentNo is moved to the end")
        val before = printedColumns(sqlOf(formatId, English, join = true, Some(true)))
        val documentNo = itemOf(formatId, "DocumentNo")
        documentNo.setSeqNo(9999)
        documentNo.saveEx()
        val after = printedColumns(sqlOf(formatId, English, join = true, Some(true)))
        Then("the column order follows the SeqNo")
        check(after != before && after.indexOf("DocumentNo") > before.indexOf("DocumentNo"), s"SeqNo was not reflected: $before -> $after")
        assertMatchesDictionary(formatId, "update SeqNo")

        When("DocumentNo becomes the last sort column (SortNo)")
        val orderBefore = orderByOf(sqlOf(formatId, English, join = true, Some(true)))
        check(!orderBefore.endsWith("C_Order.DocumentNo"), s"precondition: $orderBefore")
        documentNo.setIsOrderBy(true)
        documentNo.setSortNo(5000)
        documentNo.saveEx()
        Then("the ORDER BY follows the SortNo")
        val orderAfter = orderByOf(sqlOf(formatId, English, join = true, Some(true)))
        check(orderAfter.endsWith("C_Order.DocumentNo") && orderAfter != orderBefore, s"SortNo was not reflected: $orderBefore -> $orderAfter")
        assertMatchesDictionary(formatId, "update SortNo")

        When("a new item is inserted (Description printed again, as a new record)")
        val template = itemOf(formatId, "Description")
        val reprinted = new MPrintFormatItem(Env.getCtx, 0, null)
        reprinted.setAD_PrintFormat_ID(formatId)
        reprinted.setAD_Column_ID(MColumn.getColumn_ID(OrderTable, "Description"))
        reprinted.setName(template.getName)
        reprinted.setPrintName(template.getPrintName)
        reprinted.setPrintAreaType(template.getPrintAreaType)
        reprinted.setPrintFormatType(template.getPrintFormatType)
        reprinted.setFieldAlignmentType(template.getFieldAlignmentType)
        reprinted.setLineAlignmentType(template.getLineAlignmentType)
        reprinted.setSeqNo(10000)
        reprinted.setIsPrinted(true)
        // the first item of the column is deleted, the new one is the only item of the column
        template.deleteEx(true)
        val withoutDescription = sqlOf(formatId, English, join = true, Some(true))
        Then("a deleted item is not selected any more")
        check(!printedColumns(withoutDescription).contains("Description"), "the deleted item is still in the definition")
        assertMatchesDictionary(formatId, "delete")
        reprinted.saveEx()
        And("the inserted item is selected by the next run")
        val withDescription = sqlOf(formatId, English, join = true, Some(true))
        check(printedColumns(withDescription).contains("Description"), "the new item is not in the definition (insert did not invalidate)")
        assertMatchesDictionary(formatId, "insert")
      }
    }

    Scenario("A change of a print format item translation does not change the SQL and the next run is still correct") {
      withTemporaryFormat { (formatId, _) =>
        Given("a warm cache and a translation of the item DocumentNo")
        assertMatchesDictionary(formatId, "baseline")
        val baseline = sqlOf(formatId, Spanish, join = true, Some(true))
        val documentNo = itemOf(formatId, "DocumentNo")
        When("its AD_PrintFormatItem_Trl PrintName changes")
        val updated = DB.executeUpdateEx("UPDATE AD_PrintFormatItem_Trl SET PrintName='Numero RLJ' WHERE AD_PrintFormatItem_ID=? AND AD_Language=?",
          Array[Object](Integer.valueOf(documentNo.get_ID), Spanish), null)
        info(s"translation rows updated: $updated")
        CacheMgt.get().reset("AD_PrintFormatItem")
        Then("the SQL does not depend on the translation and the run equals the oracle")
        check(sqlOf(formatId, Spanish, join = true, Some(true)) == baseline)
        assertMatchesDictionary(formatId, "AD_PrintFormatItem_Trl")
      }
    }

    Scenario("A change of a dictionary column or reference used by the report is visible in the next run") {
      withTemporaryFormat { (formatId, _) =>
        Given("a warm cache for a print format of C_Order, whose C_PaymentTerm_ID display comes from the identifier columns of C_PaymentTerm")
        assertMatchesDictionary(formatId, "baseline")
        val baseline = sqlOf(formatId, English, join = true, Some(true))
        val candidate = new Query(Env.getCtx, I_AD_Column.Table_Name,
          "AD_Table_ID=? AND IsIdentifier='N' AND ColumnName='Description' AND IsActive='Y'", null)
          .setParameters(Integer.valueOf(MTable.getTable_ID("C_PaymentTerm"))).first[MColumn]()
        assume(candidate != null, "C_PaymentTerm.Description is not a plain column in this dictionary")
        val originalSeqNo = candidate.getSeqNo
        try {
          When("C_PaymentTerm.Description becomes an identifier column (AD_Column)")
          candidate.setIsIdentifier(true)
          candidate.setSeqNo(900)
          candidate.saveEx()
          Then("the display expression of the lookup changes in the next run")
          val changed = sqlOf(formatId, English, join = true, Some(true))
          check(changed != baseline && changed.contains("PaymentTerm_15.Description"), "the AD_Column change was not reflected")
          assertMatchesDictionary(formatId, "AD_Column")
        } finally {
          candidate.setIsIdentifier(false)
          candidate.setSeqNo(originalSeqNo)
          candidate.saveEx()
        }
        And("the dictionary is back to the original state")
        check(sqlOf(formatId, English, join = true, Some(true)) == baseline)
        assertMatchesDictionary(formatId, "AD_Column restored")

        Given("the Table reference of SalesRep_ID (AD_Ref_Table)")
        val salesRep = MColumn.get(Env.getCtx, MColumn.getColumn_ID(OrderTable, "SalesRep_ID"))
        val referenceId = salesRep.getAD_Reference_Value_ID
        val refTable = new Query(Env.getCtx, I_AD_Ref_Table.Table_Name, "AD_Reference_ID=?", null).setParameters(Integer.valueOf(referenceId)).first[X_AD_Ref_Table]()
        assume(refTable != null, "SalesRep_ID has no AD_Ref_Table")
        val original = refTable.isValueDisplayed
        try {
          val tableBaseline = sqlOf(formatId, English, join = true, Some(true))
          When("IsValueDisplayed of the reference changes")
          refTable.setIsValueDisplayed(!original)
          refTable.saveEx()
          Then("the display of the Table column changes in the next run")
          val changed = sqlOf(formatId, English, join = true, Some(true))
          check(changed != tableBaseline, "the AD_Ref_Table change was not reflected")
          assertMatchesDictionary(formatId, "AD_Ref_Table")
        } finally {
          refTable.setIsValueDisplayed(original)
          refTable.saveEx()
        }
        assertMatchesDictionary(formatId, "AD_Ref_Table restored")
      }
    }

    Scenario("A dictionary change inside a transaction that is rolled back leaves no stale definition") {
      withTemporaryFormat { (formatId, _) =>
        Given("a warm cache for the committed definition")
        assertMatchesDictionary(formatId, "baseline")
        val committed = sqlOf(formatId, English, join = true, Some(true))
        check(printedColumns(committed).contains("Description"))

        When("an item is hidden inside a transaction and the report runs in that transaction")
        val trx = Trx.createTrxName("ReportDefinitionCache")
        var inside = ""
        try {
          val item = itemOf(formatId, "Description", trx)
          item.setIsPrinted(false)
          item.saveEx()
          inside = sqlOf(formatId, English, join = true, Some(true), trx)
          Then("the report in the transaction sees the uncommitted definition")
          check(!printedColumns(inside).contains("Description"), "the transaction does not see its own change")
          And("another connection still sees the committed definition while the transaction is open")
          check(sqlOf(formatId, English, join = true, Some(true)) == committed, "uncommitted state leaked to another connection")
        } finally {
          Option(Trx.get(trx, false)).foreach { t => t.rollback(); t.close() }
        }
        Then("after the rollback the next run shows the committed definition")
        check(sqlOf(formatId, English, join = true, Some(true)) == committed, "a rolled back change left a stale definition")
        assertMatchesDictionary(formatId, "after rollback")

        When("a committed change follows the rollback, the cache works again")
        val item = itemOf(formatId, "Description")
        item.setIsPrinted(false)
        item.saveEx()
        check(!printedColumns(sqlOf(formatId, English, join = true, Some(true))).contains("Description"))
        assertMatchesDictionary(formatId, "commit after rollback")
      }
    }

    Scenario("A change committed inside a transaction is visible after the commit") {
      withTemporaryFormat { (formatId, _) =>
        Given("a warm cache and a transaction that hides an item")
        assertMatchesDictionary(formatId, "baseline")
        val trx = Trx.createTrxName("ReportDefinitionCacheCommit")
        try {
          val item = itemOf(formatId, "Description", trx)
          item.setIsPrinted(false)
          item.saveEx()
          // another connection reads (and may cache) the committed state while the transaction is open
          check(printedColumns(sqlOf(formatId, English, join = true, Some(true))).contains("Description"))
          When("the transaction commits")
          Trx.get(trx, false).commit(true)
        } finally Option(Trx.get(trx, false)).foreach(_.close())
        Then("the next run does not select the column")
        check(!printedColumns(sqlOf(formatId, English, join = true, Some(true))).contains("Description"), "the committed change is not visible")
        assertMatchesDictionary(formatId, "after commit")
      }
    }

    Scenario("A report view column inserted for a cached report view is visible in the next run") {
      withTemporaryFormat { (formatId, _) =>
        Given("a print format of C_Order on an AD_ReportView without group functions, with a warm cache")
        val reportView = new X_AD_ReportView(Env.getCtx, 0, null)
        reportView.setName("RDC test view")
        reportView.setAD_Table_ID(MTable.getTable_ID(OrderTable))
        reportView.saveEx()
        try {
          val format = new MPrintFormat(Env.getCtx, formatId, null)
          format.setAD_ReportView_ID(reportView.get_ID)
          format.saveEx()
          assertMatchesDictionary(formatId, "report view without columns")
          val plain = sqlOf(formatId, English, join = true, Some(true))
          check(!plain.contains(" GROUP BY "), "precondition: the report view has no group function")
          When("an AD_ReportView_Col with a group function is inserted (a new record, no class hook)")
          val column = new X_AD_ReportView_Col(Env.getCtx, 0, null)
          column.setAD_ReportView_ID(reportView.get_ID)
          column.setAD_Column_ID(MColumn.getColumn_ID(OrderTable, "GrandTotal"))
          column.setIsGroupFunction(true)
          column.setFunctionColumn("SUM(@)")
          column.saveEx()
          Then("the next run groups the report")
          val grouped = sqlOf(formatId, English, join = true, Some(true))
          check(grouped.contains(" GROUP BY ") && grouped.contains("SUM(C_Order.GrandTotal)"), "the new AD_ReportView_Col is not in the definition (insert did not invalidate)")
          assertMatchesDictionary(formatId, "AD_ReportView_Col insert")
          And("deleting it removes the grouping again")
          column.deleteEx(true)
          check(!sqlOf(formatId, English, join = true, Some(true)).contains(" GROUP BY "), "the deleted AD_ReportView_Col is still in the definition")
          assertMatchesDictionary(formatId, "AD_ReportView_Col delete")
        } finally {
          Try(new MPrintFormat(Env.getCtx, formatId, null)).foreach { f => f.setAD_ReportView_ID(0); Try(f.saveEx()) }
          Try(new Query(Env.getCtx, "AD_ReportView_Col", "AD_ReportView_ID=?", null).setParameters(Integer.valueOf(reportView.get_ID)).list[X_AD_ReportView_Col]().asScala.foreach(_.deleteEx(true)))
          Try(reportView.deleteEx(true))
          CacheMgt.get().reset("AD_ReportView")
        }
      }
    }

    Scenario("An entry expires after its time to live") {
      val stats = statements()
      assume(stats.isDefined, "pg_stat_statements is not available: the number of queries cannot be observed outside the cache")
      val ttl = ReportDefinitionCache.getExpireMillis
      Given("a time to live of 300 ms and a warm cache")
      val formats = Seq(storedFormat(1000003))
      try {
        ReportDefinitionCache.setExpireMillis(300)
        resetDefinitions()
        measureRun(formats, English, join = true, cache = true)
        val warm = measureRun(formats, English, join = true, cache = true).get
        check(warm.filter(isDictionary).isEmpty, "the cache is not warm")
        When("the entries are older than the time to live")
        Thread.sleep(600)
        Then("the definition is read from the dictionary again, and then cached again")
        val expired = measureRun(formats, English, join = true, cache = true).get
        check(expired.filter(isDictionary).nonEmpty, "an expired entry was served")
        ReportDefinitionCache.setExpireMillis(ttl)
        val again = measureRun(formats, English, join = true, cache = true).get
        val warmAgain = measureRun(formats, English, join = true, cache = true).get
        check(warmAgain.filter(isDictionary).isEmpty, "the definition was not cached after it expired")
        info(s"default time to live: ${ttl / 60000} minutes; dictionary statements: expired run ${expired.count(isDictionary)}, re-read ${again.count(isDictionary)}, warm ${warmAgain.count(isDictionary)}")
      } finally ReportDefinitionCache.setExpireMillis(ttl)
    }

    Scenario("Threads running the same and different print formats with a cold cache get the single-thread result") {
      val formats = Vector(1000003, 1000004, 1000008, 1000007, 1000005) ++ tableBasedFormats.take(4)
      val variants = for (f <- formats.distinct; lang <- Seq(English, Spanish); join <- Seq(true, false)) yield (f, lang, join)
      Given(s"${variants.size} format/language/path variants, computed once without cache as the single-thread baseline")
      val baseline = variants.map { case (f, lang, join) => (f, lang, join) -> snapshot(run(storedFormat(f), lang, join, Some(false)).get) }.toMap
      val threads = 8
      val runsPerThread = 25
      Seq("the same print format", "different print formats").foreach { mode =>
        When(s"$threads threads run getPrintData $runsPerThread times on $mode, with the cache cold")
        resetDefinitions()
        val pool = Executors.newFixedThreadPool(threads)
        try {
          val tasks = (0 until threads).map { t =>
            pool.submit(new Callable[Vector[String]] {
              def call(): Vector[String] = (0 until runsPerThread).flatMap { i =>
                val variant = if (mode == "the same print format") variants.filter(_._1 == formats.head)(i % 4) else variants((t * 7 + i) % variants.size)
                val (f, lang, join) = variant
                val problems = Try {
                  val result = snapshot(run(storedFormat(f), lang, join, Some(true)).get)
                  val expected = baseline(variant)
                  if (result.sql != expected.sql) Some(s"SQL differs for $variant")
                  else if (result.columns != expected.columns || result.functionRows != expected.functionRows || multiset(result.rows) != multiset(expected.rows))
                    Some(s"data differs for $variant")
                  else None
                }.recover { case e: Throwable => Some(s"exception for $variant: $e") }.get
                problems
              }.toVector
            })
          }
          val problems = tasks.flatMap(_.get(5, TimeUnit.MINUTES))
          Then(s"all ${threads * runsPerThread} runs equal the baseline and none raised an exception")
          check(problems.isEmpty, problems.distinct.take(10).mkString("\n  "))
        } finally pool.shutdownNow()
      }
    }

    Scenario("Concurrent saves of print format items and cold reports of several clients raise no exception and leave a correct cache") {
      withTemporaryFormat { (formatId, _) =>
        val clients = Vector(11, 0, 900001, 900002) // GardenWorld, System and two clients that never ran a report
        val formats = Vector(1000003, 1000004, 1000008, formatId)
        val itemColumns = Vector("DocumentNo", "Description", "DateOrdered", "GrandTotal")
        val savers = 4
        val reporters = 4
        val iterations = 40
        def contextOf(client: Int): Properties = {
          val ctx = new Properties()
          ctx.putAll(Env.getCtx)
          Env.setContext(ctx, "#AD_Client_ID", client)
          ctx
        }
        Given(s"${formats.size} print formats x ${clients.size} client contexts, computed once without cache as the baseline")
        val variants = for (f <- formats; c <- clients) yield (f, c)
        val baseline = variants.flatMap { case (f, c) =>
          Try(snapshot(run(storedFormat(f), English, join = true, Some(false), ctx = contextOf(c)).get)).toOption.map((f, c) -> _)
        }.toMap
        check(baseline.size == variants.size, s"baseline failed for ${variants.filterNot(baseline.contains)}")
        val seqNos = itemColumns.map(c => c -> itemOf(formatId, c).getSeqNo)
        When(s"$savers threads save AD_PrintFormatItem changes, each in its own transaction that is rolled back, while $reporters threads run cold getPrintData, $iterations iterations each")
        resetDefinitions()
        val pool = Executors.newFixedThreadPool(savers + reporters)
        try {
          val saverTasks = (0 until savers).map { t =>
            pool.submit(new Callable[Vector[String]] {
              def call(): Vector[String] = (0 until iterations).flatMap { i =>
                val trx = Trx.createTrxName("RDCSave")
                try {
                  val item = itemOf(formatId, itemColumns(t), trx)
                  item.setSeqNo(item.getSeqNo + 1 + i % 3)
                  item.saveEx()
                  None
                } catch { case e: Throwable => Some(s"saver $t iteration $i: $e") }
                finally Option(Trx.get(trx, false)).foreach { x => x.rollback(); x.close() }
              }.toVector
            })
          }
          val reporterTasks = (0 until reporters).map { t =>
            pool.submit(new Callable[Vector[String]] {
              def call(): Vector[String] = (0 until iterations).flatMap { i =>
                if (t == 0 && i % 5 == 0) resetDefinitions() // cold again
                val variant = variants((t * 5 + i) % variants.size)
                Try {
                  val result = snapshot(run(storedFormat(variant._1), English, join = true, Some(true), ctx = contextOf(variant._2)).get)
                  val expected = baseline(variant)
                  if (result.sql != expected.sql) Some(s"reporter $t: SQL differs for $variant")
                  else if (result.columns != expected.columns || multiset(result.rows) != multiset(expected.rows)) Some(s"reporter $t: data differs for $variant")
                  else None
                }.recover { case e: Throwable => Some(s"reporter $t iteration $i: $e") }.get
              }.toVector
            })
          }
          val problems = (saverTasks ++ reporterTasks).flatMap(_.get(5, TimeUnit.MINUTES))
          Then("no saver and no reporter raised an exception, and every report equals the baseline")
          check(problems.isEmpty, problems.distinct.take(10).mkString("\n  "))
        } finally pool.shutdownNow()
        And("the rolled back saves left the dictionary and the cache as they were")
        check(itemColumns.map(c => c -> itemOf(formatId, c).getSeqNo) == seqNos)
        assertMatchesDictionary(formatId, "after concurrent saves")
        variants.foreach { case (f, c) =>
          val cached = snapshot(run(storedFormat(f), English, join = true, Some(true), ctx = contextOf(c)).get)
          check(cached.sql == baseline((f, c)).sql, s"stale SQL after the saves for $f/$c")
        }
      }
    }

    /** A C_Order saved in the transaction: a table that no report definition depends on */
    def saveOrder(trx: String): MOrder = {
      val order = new MOrder(Env.getCtx, 0, trx)
      order.setAD_Org_ID(11)
      order.setBPartner(new MBPartner(Env.getCtx, 117, trx))
      order.setC_DocTypeTarget_ID(MOrder.DocSubTypeSO_Standard)
      order.setIsSOTrx(true)
      order.setM_Warehouse_ID(103)
      order.saveEx()
      order
    }

    Scenario("Saving and deleting records of other tables leaves the cached definitions alone") {
      val stats = statements()
      assume(stats.isDefined, "pg_stat_statements is not available: the number of queries cannot be observed outside the cache")
      Given(s"a warm cache for print formats ${ProfileFormats.mkString(", ")} and a listener that records every change")
      val formats = ProfileFormats.map(id => storedFormat(id))
      resetDefinitions()
      measureRun(formats, English, join = true, cache = true)
      check(measureRun(formats, English, join = true, cache = true).get.filter(isDictionary).isEmpty, "the cache is not warm")
      val seen = ArrayBuffer.empty[(String, Boolean, Boolean)]
      val listener = new CacheChangeListener {
        override def changed(tableName: String, recordId: Int, trxName: String, newRecord: Boolean, deleted: Boolean): Unit =
          seen.synchronized { seen += ((tableName, newRecord, deleted)) }
      }
      CacheMgt.get().addChangeListener(listener)
      val trx = Trx.createTrxName("RDCOrder")
      try {
        When("a C_Order is inserted, updated and deleted inside a transaction that is rolled back")
        val order = saveOrder(trx)
        order.setDescription("RDC no side effect")
        order.saveEx()
        order.deleteEx(true)
      } finally {
        CacheMgt.get().removeChangeListener(listener)
        Option(Trx.get(trx, false)).foreach { t => t.rollback(); t.close() }
      }
      val changes = seen.synchronized(seen.toVector)
      info(s"changes seen by the listener: ${changes.distinct.mkString(", ")}")
      check(changes.contains(("C_Order", true, false)) && changes.contains(("C_Order", false, false)) && changes.contains(("C_Order", false, true)),
        s"the listener did not see the insert, the update and the delete: $changes")
      Then("the next warm getPrintData still runs no dictionary statement")
      val warm = measureRun(formats, English, join = true, cache = true).get
      check(warm.filter(isDictionary).isEmpty, "dictionary statements after saving a C_Order:\n  " + warm.filter(isDictionary).map(s => s"${s.calls} x ${s.query.take(120)}").mkString("\n  "))
    }

    Scenario("A listener that throws does not fail the save and does not stop the other listeners") {
      Given("a listener that throws and a listener that counts")
      var counted = 0
      val failing = new CacheChangeListener {
        override def changed(tableName: String, recordId: Int, trxName: String, newRecord: Boolean, deleted: Boolean): Unit =
          throw new IllegalStateException("listener failure on purpose")
      }
      val counting = new CacheChangeListener {
        override def changed(tableName: String, recordId: Int, trxName: String, newRecord: Boolean, deleted: Boolean): Unit =
          if (tableName == "C_Order") counted += 1
      }
      val manager = CacheMgt.get()
      check(manager.addChangeListener(failing) && manager.addChangeListener(counting))
      val trx = Trx.createTrxName("RDCFailing")
      try {
        When("a record is saved and deleted")
        val order = saveOrder(trx)
        Then("the save succeeded")
        check(order.get_ID > 0, "the save failed because of the listener")
        order.setDescription("RDC failing listener")
        check(order.save(), "the update failed because of the listener")
        And("the delete succeeded")
        check(order.delete(true), "the delete failed because of the listener")
        And("the listener after the failing one was still called for the insert, the update and the delete")
        check(counted == 3, s"the counting listener saw $counted changes instead of 3")
      } finally {
        manager.removeChangeListener(failing)
        manager.removeChangeListener(counting)
        Option(Trx.get(trx, false)).foreach { t => t.rollback(); t.close() }
      }
      check(!manager.removeChangeListener(failing), "the listener was not removed")
    }

    Scenario("With the cache switched off the engine reads the dictionary on every run and generates the same SQL") {
      val stats = statements()
      assume(stats.isDefined, "pg_stat_statements is not available: the number of queries cannot be observed")
      Given("a print format run twice with setDefinitionCache(false)")
      val format = storedFormat(1000003)
      resetDefinitions()
      val first = measureRun(Seq(format), English, join = true, cache = false).get
      val second = measureRun(Seq(format), English, join = true, cache = false).get
      Then("the second run reads the definition again")
      check(second.exists(s => kind(s) == "print format + items + columns + report view columns" && isDictionary(s)))
      check(second.filter(isDictionary).map(_.calls).sum >= 3, "toggle off: expected the definition queries on every run")
      And("the SQL equals the SQL of the cached run")
      check(sqlOf(1000003, English, join = true, Some(false)) == sqlOf(1000003, English, join = true, Some(true)))
      check(sqlOf(1000003, English, join = false, Some(false)) == sqlOf(1000003, English, join = false, Some(true)))
      info(s"definition statements, first run ${first.filter(isDictionary).map(_.calls).sum}, second run ${second.filter(isDictionary).map(_.calls).sum}")
    }
  }
}
