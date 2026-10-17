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

import java.sql.ResultSet

import org.compiere.model.{MColumn, MQuery, MTable}
import org.compiere.print.{DataEngine, MPrintFormat, PrintData, PrintDataElement, ReportLookupAlias}
import org.compiere.util.{DB, DisplayType, Env, Language, Trx}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.collection.mutable.ArrayBuffer
import scala.util.Try

/** Differential regression test of the report lookup strategy.
  *
  * The same MPrintFormat + MQuery is loaded twice through the public DataEngine API: with the legacy
  * correlated subselects (setLookupJoin(false)) and with LEFT OUTER JOINs (setLookupJoin(true)).
  * Both PrintData must be identical, NULL included.
  *
  * Fixtures that the GardenWorld dictionary does not have (group function ReportView, group break print format,
  * a product without translation) are created or changed inside a transaction that is always rolled back.
  */
class ValidateReportLookupJoinRegression extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val Tag = "SELECT /* AD_PrintFormat_ID="
  private val English = "en_US"
  private val Spanish = "es_MX"
  private val GardenWorldUserRole = 103
  private val DeliveryDetailFormat = 1000005 // RV_InOutDetails, ReportView 151: IsSOTrx='Y' (unqualified)
  private val SalesOrderFormat = 1000003 // C_Order
  private val QualifiedWhereFormat = 50016 // A_Depreciation_Exp, ReportView 53009: A_Depreciation_Exp.A_Entry_Type='TRN'
  private val PaymentViewReportView = 155 // C_Payment_v: DocStatus IN ('CO','CL') AND IsAllocated='N' (unqualified)

  private case class Cell(name: String, key: String, value: String, display: String, pKey: Boolean, displayType: Int)
  private case class Run(pd: Option[PrintData], error: Option[String]) {
    def sql: String = pd.map(_.getSQL).getOrElse("")
  }
  private case class Result(formatId: Int, legacy: Run, join: Run, problems: Vector[String]) {
    def compared: Boolean = legacy.pd.isDefined && join.pd.isDefined
  }

  private def language(name: String) = Language.getLanguage(name)

  private def runEngine(
      format: () => MPrintFormat,
      lang: Language,
      join: Option[Boolean],
      restrict: MQuery => Unit = _ => (),
      reportViewId: Int = 0,
      trx: String = null
  ): Run =
    Try {
      val query = new MQuery()
      restrict(query)
      val engine = new DataEngine(lang, trx)
      join.foreach(flag => engine.setLookupJoin(flag))
      Run(Option(engine.getPrintData(Env.getCtx, format(), query, false, reportViewId)), None)
    }.recover { case e: Throwable => Run(None, Some(e.toString)) }.get

  private def storedFormat(id: Int): () => MPrintFormat = () => MPrintFormat.get(Env.getCtx, id, true)

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

  private def functionRows(pd: PrintData): Vector[Int] = (0 until pd.getRowCount).filter(i => pd.isFunctionRow(i)).toVector

  /** ORDER BY clause of the statement (the outer one): same entries, same sequence, same DESC, same positions */
  private def orderBy(sql: String): String = if (sql.contains(" ORDER BY ")) sql.substring(sql.lastIndexOf(" ORDER BY ")) else ""

  private def multiset[T](values: Vector[T]): Map[T, Int] = values.groupBy(identity).view.mapValues(_.size).toMap

  private def columns(pd: PrintData) =
    pd.getColumnInfo.toVector.map(c => (c.getAD_Column_ID, c.getColumnName, c.getAlias, c.hasAlias, c.getDisplayType))

  /** true when the statement is rejected by the database */
  private def sqlFails(sql: String): Boolean = Try {
    val statement = DB.prepareStatement(sql, null)
    var rs: ResultSet = null
    try {
      rs = statement.executeQuery()
    } finally DB.close(rs, statement)
  }.isFailure

  /** Printed TableDir / Search (without reference value) columns that the join strategy resolves at top level */
  private def tableDirColumns(pd: PrintData) =
    pd.getColumnInfo.toVector.filter { c =>
      c.getAlias != DataEngine.KEY && c.hasAlias && {
        val column = MColumn.get(Env.getCtx, c.getAD_Column_ID)
        !column.isKey && (column.getAD_Reference_ID == DisplayType.TableDir ||
          (column.getAD_Reference_ID == DisplayType.Search && column.getAD_Reference_Value_ID == 0))
      }
    }

  private val LookupJoin = """LEFT OUTER JOIN (\w+(?<!_Trl)) (\w+_\d+) ON \(""".r
  private val TranslationJoin = """LEFT OUTER JOIN (\w+_Trl) (\w+_Trl_\d+) ON \(""".r

  /** Compare the PrintData built by both strategies. Returns the problems found (empty = identical). */
  private def differences(legacy: Run, join: Run): Vector[String] = {
    val problems = ArrayBuffer.empty[String]
    (legacy.pd, join.pd) match {
      case (None, None) => ()
      case (Some(_), None) => problems += s"join path returned no PrintData (${join.error.getOrElse("null")}), legacy did"
      case (None, Some(_)) => problems += s"legacy path returned no PrintData (${legacy.error.getOrElse("null")}), join path did"
      case (Some(l), Some(j)) =>
        if (columns(l) != columns(j)) problems += s"columns differ: ${columns(l)} <> ${columns(j)}"
        if (l.getRowCount != j.getRowCount) problems += s"row count differs: ${l.getRowCount} <> ${j.getRowCount}"
        else {
          val legacyRows = cells(l)
          val joinRows = cells(j)
          // The ORDER BY key sequence must be identical (DESC and display sorts included). Rows that share the same
          // key are a tie group: neither path defines their order, so each tie group is compared as a multiset.
          val sortColumns = l.getColumnInfo.toVector.filter(_.getSortOrderIndex > 0).sortBy(_.getSortOrderIndex).map(_.getColumnName)
          def sortKey(row: Vector[Cell]) = sortColumns.map(name => row.find(_.name == name).map(c => (c.key, c.display)))
          val legacyKeys = legacyRows.map(sortKey)
          val joinKeys = joinRows.map(sortKey)
          if (legacyKeys != joinKeys) problems += s"sort order differs by ${sortColumns.mkString(",")}"
          else {
            val ties = legacyKeys.indices.foldLeft(Vector.empty[(Int, Int)]) { (groups, i) =>
              if (groups.nonEmpty && legacyKeys(i) == legacyKeys(i - 1)) groups.init :+ ((groups.last._1, i + 1)) else groups :+ ((i, i + 1))
            }
            val differing = ties.filter { case (from, until) => multiset(legacyRows.slice(from, until)) != multiset(joinRows.slice(from, until)) }
            if (differing.nonEmpty)
              problems += s"cells differ in ${differing.size} of ${ties.size} tie groups, first rows ${differing.head._1}..${differing.head._2 - 1}: " +
                s"legacy ${legacyRows.slice(differing.head._1, differing.head._2).headOption} <> join ${joinRows.slice(differing.head._1, differing.head._2).headOption}"
          }
          // group breaks and function rows (PrintDataGroup) are on the same rows
          if (functionRows(l) != functionRows(j)) problems += s"function rows differ: ${functionRows(l)} <> ${functionRows(j)}"
        }
        if (orderBy(l.getSQL) != orderBy(j.getSQL)) problems += s"ORDER BY differs: ${orderBy(l.getSQL)} <> ${orderBy(j.getSQL)}"
        if (l.getRowCount == 0 && sqlFails(l.getSQL) != sqlFails(j.getSQL))
          problems += s"legacy SQL fails=${sqlFails(l.getSQL)} but join SQL fails=${sqlFails(j.getSQL)}"
    }
    problems.toVector
  }

  /** SQL contract of the join path. Returns the problems found. */
  private def joinSqlProblems(formatId: Int, run: Run): Vector[String] = {
    val problems = ArrayBuffer.empty[String]
    run.pd.foreach { pd =>
      val sql = pd.getSQL
      if (!sql.startsWith(s"$Tag$formatId */ ")) problems += s"SQL does not start with the tag: ${sql.take(60)}"
      if (sql.contains(" AS ")) {
        // semantic aliases only: TableName_n / TableName_Trl_n, at most 30 characters
        val baseTable = pd.getTableName
        LookupJoin.findAllMatchIn(sql).foreach { m =>
          val (table, alias) = (m.group(1), m.group(2))
          val n = alias.substring(alias.lastIndexOf('_') + 1).toInt
          if (alias != ReportLookupAlias.lookup(table, n, baseTable)) problems += s"unexpected lookup alias $alias for $table"
          if (alias.length > 30) problems += s"alias longer than 30 characters: $alias"
          if (alias.equalsIgnoreCase(baseTable)) problems += s"alias equals the base table: $alias"
        }
        TranslationJoin.findAllMatchIn(sql).foreach { m =>
          val (trlTable, alias) = (m.group(1), m.group(2))
          val n = alias.substring(alias.lastIndexOf('_') + 1).toInt
          val table = trlTable.substring(0, trlTable.length - "_Trl".length)
          if (alias != ReportLookupAlias.translation(table, n, baseTable)) problems += s"unexpected translation alias $alias for $trlTable"
          if (alias.length > 30) problems += s"alias longer than 30 characters: $alias"
        }
      }
      // every top-level TableDir column is either a join CASE or (fallback) the legacy subselect, never missing
      tableDirColumns(pd).foreach { c =>
        if (!sql.contains(s"END AS ${c.getAlias},") && !sql.contains(s") AS ${c.getAlias},"))
          problems += s"TableDir column ${c.getColumnName} is not resolved as ${c.getAlias}"
      }
    }
    problems.toVector
  }

  private def joinedLookups(run: Run): Int = run.pd.map(pd => tableDirColumns(pd).count(c => run.sql.contains(s"END AS ${c.getAlias},"))).getOrElse(0)
  private def subselectLookups(run: Run): Int = run.pd.map(pd => tableDirColumns(pd).count(c => run.sql.contains(s") AS ${c.getAlias},"))).getOrElse(0)

  private def compare(
      format: () => MPrintFormat,
      formatId: Int,
      lang: Language,
      restrict: MQuery => Unit = _ => (),
      reportViewId: Int = 0,
      trx: String = null
  ): Result = {
    val legacy = runEngine(format, lang, Some(false), restrict, reportViewId, trx)
    val join = runEngine(format, lang, Some(true), restrict, reportViewId, trx)
    var problems = differences(legacy, join)
    // views that select the current timestamp differ between any two runs: the legacy path itself is the control
    if (problems.exists(p => p.startsWith("cells differ") || p.startsWith("sort order"))) {
      val control = runEngine(format, lang, Some(false), restrict, reportViewId, trx)
      if (differences(legacy, control).nonEmpty) problems = problems.filterNot(p => p.startsWith("cells differ") || p.startsWith("sort order"))
    }
    Result(formatId, legacy, join, problems ++ joinSqlProblems(formatId, join))
  }

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

  private val sweeps = scala.collection.mutable.Map.empty[String, Vector[Result]]

  private def sweep(lang: String, role: Option[Int] = None): Vector[Result] =
    sweeps.getOrElseUpdate(s"$lang/${role.getOrElse(0)}", {
      withRole(role) {
        tableBasedFormats.map(id => compare(storedFormat(id), id, language(lang)))
      }
    })

  private def withRole[T](role: Option[Int])(body: => T): T = {
    val ctx = Env.getCtx
    val current = Env.getContext(ctx, "#AD_Role_ID")
    role.foreach(id => Env.setContext(ctx, "#AD_Role_ID", id))
    try body
    finally role.foreach(_ => Env.setContext(ctx, "#AD_Role_ID", current))
  }

  /** Rows of the join path where the lookup has no value (foreign key NULL): the cell is absent as it is with the subselect */
  private def nullLookupCells(results: Vector[Result]): Int =
    results.filter(_.compared).map { r =>
      val names = tableDirColumns(r.join.pd.get).map(_.getColumnName)
      cells(r.join.pd.get).map(row => names.count(name => !row.exists(_.name == name))).sum
    }.sum

  private def report(title: String, results: Vector[Result]): Int = {
    val compared = results.filter(_.compared)
    val bothNull = results.count(r => r.legacy.pd.isEmpty && r.join.pd.isEmpty)
    val joined = compared.map(r => joinedLookups(r.join)).sum
    val fallbacks = compared.map(r => subselectLookups(r.join)).sum
    val rows = compared.map(_.legacy.pd.get.getRowCount.toLong).sum
    info(
      s"$title: ${results.size} print formats, ${compared.size} compared ($rows rows), " +
        s"$bothNull skipped because both paths return no PrintData, " +
        s"$joined TableDir lookups joined, $fallbacks kept as subselect (fallback)"
    )
    val nulls = nullLookupCells(results)
    info(s"$title: $nulls TableDir cells are NULL in the join path (identical to the subselect path)")
    nulls
  }

  private def assertNoProblems(results: Vector[Result]): Unit = {
    val failing = results.filter(_.problems.nonEmpty)
    assert(
      failing.isEmpty,
      failing.map(r => s"\n  format ${r.formatId}: ${r.problems.mkString("; ")}").mkString
    )
  }

  Feature("Report lookups resolved with LEFT OUTER JOIN instead of correlated subselects") {
    info("The report data and the NULL values must be the same as the legacy subselects")

    Scenario("With the join strategy off the report keeps the correlated subselects") {
      Given("every table based print format generated with setLookupJoin(false)")
      val legacy = tableBasedFormats.map(id => (id, runEngine(storedFormat(id), language(English), Some(false)))).filter(_._2.pd.isDefined)
      Then("the SQL has no tag, no derived table around the statement and no semantic lookup join")
      assert(legacy.nonEmpty)
      val offending = legacy.collect {
        case (id, run) if run.sql.startsWith(Tag) || LookupJoin.findFirstIn(run.sql).isDefined || run.sql.contains(" END AS ") => id
      }
      assert(offending.isEmpty, s"join SQL found with the strategy off for ${offending.mkString(", ")}")
      And("the TableDir display columns are subselects")
      assert(legacy.exists { case (_, run) => run.sql.contains("(SELECT NVL(") })
    }

    Scenario("All table based print formats return the same data in English") {
      Given("the active table based print formats of clients 0 and 11")
      assert(tableBasedFormats.nonEmpty)
      When("they are generated with subselects and with joins")
      val results = sweep(English)
      Then("rows, columns, values, display values and NULLs are identical")
      val nulls = report("en_US", results)
      assertNoProblems(results)
      And("NULL foreign keys are part of the comparison")
      assert(nulls > 0)
      And("the join path resolved lookups")
      assert(results.map(r => joinedLookups(r.join)).sum > 0)
    }

    Scenario("All table based print formats return the same data in the es_MX translation") {
      Given("the system language es_MX")
      When("the print formats are generated with subselects and with joins")
      val results = sweep(Spanish)
      Then("the translated display values are identical")
      report("es_MX", results)
      assertNoProblems(results)
      And("the translation tables are joined by language")
      val translated = results.filter(_.compared).flatMap(r => TranslationJoin.findAllMatchIn(r.join.sql).map(_.matched))
      info(s"${translated.size} translation joins, e.g. ${translated.headOption.getOrElse("none")}")
      assert(translated.nonEmpty)
      assert(results.filter(_.compared).forall(r => TranslationJoin.findAllMatchIn(r.join.sql).forall { m =>
        r.join.sql.contains(s"${m.group(2)}.AD_Language='$Spanish'")
      }))
    }

    Scenario("A ReportView raw WHERE without table qualification returns the same rows") {
      Given("ReportView 151 with the raw WHERE IsSOTrx='Y' on RV_InOutDetails")
      When("the delivery print format is generated by both strategies")
      val result = compare(storedFormat(DeliveryDetailFormat), DeliveryDetailFormat, language(English))
      Then("the data is identical and the WHERE is inside the derived table")
      assertNoProblems(Vector(result))
      assert(result.compared)
      assert(result.legacy.sql.contains("IsSOTrx='Y'"))
      assert(result.join.sql.contains("IsSOTrx='Y'"))
      assert(result.join.sql.indexOf("IsSOTrx='Y'") < result.join.sql.indexOf(") RV_InOutDetails"))
      info(s"rows=${result.legacy.pd.get.getRowCount}, lookups joined=${joinedLookups(result.join)}")
    }

    Scenario("A second unqualified ReportView raw WHERE (C_Payment_v) returns the same rows") {
      Given("ReportView 155 with DocStatus IN ('CO','CL') AND IsAllocated='N' on C_Payment_v")
      val trx = Trx_name("payment")
      val tableId = MTable.getTable_ID("C_Payment_v")
      val format = MPrintFormat.createFromTable(Env.getCtx, tableId, 0, trx)
      assert(format != null)
      try {
        When("a print format of the view is generated with the ReportView WHERE")
        val result = compare(() => format, format.get_ID, language(English), reportViewId = PaymentViewReportView, trx = trx)
        Then("the data is identical")
        assertNoProblems(Vector(result))
        assert(result.compared)
        assert(result.join.sql.contains("DocStatus IN ('CO','CL')"))
        info(s"rows=${result.legacy.pd.get.getRowCount}, lookups joined=${joinedLookups(result.join)}")
      } finally org.compiere.util.Trx.get(trx, false).rollback()
    }

    Scenario("A ReportView WHERE qualified with the table name returns the same rows") {
      Given("ReportView 53009 with the WHERE A_Depreciation_Exp.A_Entry_Type='TRN'")
      val result = compare(storedFormat(QualifiedWhereFormat), QualifiedWhereFormat, language(English))
      Then("the data is identical")
      assertNoProblems(Vector(result))
      assert(result.join.sql.contains("A_Depreciation_Exp.A_Entry_Type='TRN'"))
    }

    Scenario("An MQuery restriction is applied to the base table") {
      Given("the sales order print format restricted to completed orders")
      val restrict = (query: MQuery) => query.addRestriction("DocStatus", MQuery.EQUAL, "CO")
      val result = compare(storedFormat(SalesOrderFormat), SalesOrderFormat, language(English), restrict)
      Then("the data is identical and the restriction is inside the derived table")
      assertNoProblems(Vector(result))
      assert(result.compared)
      assert(result.join.sql.contains("C_Order.DocStatus"))
      assert(result.join.sql.indexOf("C_Order.DocStatus") < result.join.sql.indexOf(") C_Order"))
      info(s"rows=${result.legacy.pd.get.getRowCount}")
    }

    Scenario("A non administrator role gets the same rows with both strategies") {
      Given(s"the role GardenWorld User ($GardenWorldUserRole)")
      When("the print formats are generated")
      val results = sweep(English, Some(GardenWorldUserRole))
      Then("the data restricted by the role is identical")
      report("GardenWorld User", results)
      assertNoProblems(results)
    }

    Scenario("The join SQL is readable by a DBA") {
      Given("the delivery and sales order print formats")
      val results = Vector(DeliveryDetailFormat, SalesOrderFormat).map(id => compare(storedFormat(id), id, language(Spanish)))
      Then("the statement has the tag, a derived table and one LEFT OUTER JOIN per TableDir lookup with semantic aliases")
      assertNoProblems(results)
      results.foreach { result =>
        val sql = result.join.sql
        val pd = result.join.pd.get
        val lookups = tableDirColumns(pd)
        info(sql)
        assert(sql.startsWith(s"$Tag${result.formatId} */ SELECT") || sql.startsWith(s"$Tag${result.formatId} */ "))
        assert(sql.contains(s" FROM (SELECT "))
        assert(lookups.nonEmpty)
        assert(subselectLookups(result.join) == 0, "no top level TableDir display subselect expected")
        assert(joinedLookups(result.join) == lookups.size)
        val joins = LookupJoin.findAllMatchIn(sql).toVector
        // join reuse: one join per distinct foreign key, never more than lookups
        assert(joins.nonEmpty && joins.size <= lookups.size)
        assert(joins.map(_.group(2)).distinct.size == joins.size)
      }
      And("the lookups are joined by foreign key with the domain word of the table as alias, e.g. LEFT OUTER JOIN C_BPartner BPartner_1")
      val joinOn = """LEFT OUTER JOIN (\w+) (\w+_\d+) ON \((\w+)\.(\w+)=(\w+_\d+)\.(\w+)\)""".r
      results.foreach { result =>
        val matches = joinOn.findAllMatchIn(result.join.sql).toVector
        assert(matches.nonEmpty)
        matches.foreach { m =>
          assert(m.group(3) == result.join.pd.get.getTableName, s"join must start from the derived table: ${m.matched}")
          assert(m.group(5) == m.group(2) && m.group(4) == m.group(6), m.matched)
          val word = ReportLookupAlias.word(m.group(1))
          assert(m.group(2).startsWith(word.take(ReportLookupAlias.MAX_LENGTH - s"_${m.group(2).split("_").last}".length)), m.matched)
        }
      }
      val bpartner = ReportLookupAlias.word("C_BPartner")
      info(s"alias word of C_BPartner in this dictionary: $bpartner")
      assert(results.exists(r => s"""LEFT OUTER JOIN C_BPartner ${bpartner}_\\d+ ON \\(\\w+\\.C_BPartner_ID=${bpartner}_\\d+\\.C_BPartner_ID\\)""".r.findFirstIn(r.join.sql).isDefined))
      And("the SQL uses an alias without AS for the lookup tables")
      assert(results.forall(r => LookupJoin.findFirstIn(r.join.sql).isDefined))
      assert(results.forall(r => !r.join.sql.contains(" AS C_") || !r.join.sql.matches("(?s).*LEFT OUTER JOIN \\w+ AS .*")))
    }

    Scenario("A ReportView with group functions groups and orders as the subselect path does") {
      withTrx { trx =>
        Given("a ReportView of C_Order with SUM(GrandTotal) as group function, grouped by business partner and warehouse")
        val reportView = insertReportView(trx, "RLJ Group Function", "C_Order", Seq(("GrandTotal", "SUM(@)")))
        val formatId = insertFormat(trx, "RLJ Group Function", "C_Order", reportView, Seq(
          Item("C_BPartner_ID", seqNo = 10, sortNo = 10),
          Item("M_Warehouse_ID", seqNo = 20, sortNo = 20, isDesc = true),
          Item("GrandTotal", seqNo = 30)
        ))
        When("it is generated by both strategies in the same transaction")
        val result = compare(() => new MPrintFormat(Env.getCtx, formatId, trx), formatId, language(English), trx = trx)
        Then("groups, values and order are identical")
        assertNoProblems(Vector(result))
        assert(result.compared)
        val pd = result.legacy.pd.get
        info(s"groups=${pd.getRowCount}")
        assert(pd.getRowCount > 1)
        And("the grouping is in the derived table, the outer statement has no GROUP BY")
        val sql = result.join.sql
        assert(sql.contains(" GROUP BY ") && sql.indexOf(" GROUP BY ") == sql.lastIndexOf(" GROUP BY "))
        assert(sql.indexOf(" GROUP BY ") < sql.indexOf(") C_Order"))
        assert(sql.contains("SUM(C_Order.GrandTotal)") && sql.contains(" DESC"))
        And("the TableDir column of the grouping is joined and the legacy GROUP BY list is untouched")
        assert(joinedLookups(result.join) == 2)
        val groupBy = " GROUP BY C_Order.C_BPartner_ID,C_Order.M_Warehouse_ID"
        assert(result.legacy.sql.substring(result.legacy.sql.indexOf(" GROUP BY ")).startsWith(groupBy))
        assert(sql.substring(sql.indexOf(" GROUP BY ")).startsWith(groupBy))
        And("the sums are the ones of the dictionary data")
        val total = cells(pd).flatMap(_.find(_.name == "GrandTotal")).map(c => BigDecimal(c.value)).sum
        assert(total == BigDecimal(scalarDecimal("SELECT SUM(GrandTotal) FROM C_Order WHERE AD_Client_ID=" + Env.getAD_Client_ID(Env.getCtx))))
      }
    }

    Scenario("A print format with IsGroupBy, IsSummarized and IsCounted gets the same break and function rows") {
      withTrx { trx =>
        Given("a print format of C_Order grouped by business partner (descending), summarized and counted")
        val formatId = insertFormat(trx, "RLJ Group Break", "C_Order", 0, Seq(
          Item("C_BPartner_ID", seqNo = 10, sortNo = 10, isDesc = true, groupBy = true),
          Item("DocumentNo", seqNo = 20, sortNo = 20),
          Item("GrandTotal", seqNo = 30, summarized = true, counted = true)
        ))
        When("it is generated by both strategies")
        val result = compare(() => new MPrintFormat(Env.getCtx, formatId, trx), formatId, language(English), trx = trx)
        Then("rows, group break rows and function rows are identical")
        assertNoProblems(Vector(result))
        val (legacy, join) = (result.legacy.pd.get, result.join.pd.get)
        info(s"rows=${legacy.getRowCount}, function rows=${functionRows(legacy).size}")
        assert(functionRows(legacy).nonEmpty && functionRows(legacy) == functionRows(join))
        assert(result.legacy.sql.contains(" ORDER BY ") && result.join.sql.contains(" DESC"))
        assert(joinedLookups(result.join) == 1)
      }
    }

    Scenario("ORDER BY on a TableDir display and DESC keeps the same key sequence") {
      withTrx { trx =>
        Given("a print format of C_Order sorted by the business partner display (DESC) and the document number")
        val formatId = insertFormat(trx, "RLJ Order By", "C_Order", 0, Seq(
          Item("C_BPartner_ID", seqNo = 10, sortNo = 10, isDesc = true),
          Item("DocumentNo", seqNo = 20, sortNo = 20),
          Item("DocStatus", seqNo = 30)
        ))
        val result = compare(() => new MPrintFormat(Env.getCtx, formatId, trx), formatId, language(English), trx = trx)
        Then("the ORDER BY clause and the key sequence are identical")
        assertNoProblems(Vector(result))
        val clause = orderBy(result.join.sql)
        info(s"ORDER BY${clause.drop(" ORDER BY".length)}")
        assert(clause == orderBy(result.legacy.sql) && clause.contains(" DESC") && clause.contains("C_BPartner_ID"))
        val keys = cells(result.join.pd.get).flatMap(_.find(_.name == "C_BPartner_ID")).map(_.display)
        assert(keys.distinct.size > 1)
        assert(keys == cells(result.legacy.pd.get).flatMap(_.find(_.name == "C_BPartner_ID")).map(_.display))
      }
    }

    Scenario("A product without translation shows NULL on both paths") {
      Given("the delivery print format in es_MX that prints M_Product_ID as TableDir")
      withTrx { trx =>
        val format = () => new MPrintFormat(Env.getCtx, DeliveryDetailFormat, trx)
        val before = compare(format, DeliveryDetailFormat, language(Spanish), trx = trx)
        assertNoProblems(Vector(before))
        def productCells(run: Run) = cells(run.pd.get).map(_.find(_.name == "M_Product_ID"))
        val shown = productCells(before.legacy).flatten
        assert(shown.nonEmpty)
        val product = shown.head
        val rowsOfProduct = productCells(before.legacy).count(_.exists(_.key == product.key))
        When(s"the es_MX translation of product ${product.key} is deleted inside the transaction")
        assert(DB.executeUpdateEx("DELETE FROM M_Product_Trl WHERE M_Product_ID=? AND AD_Language=?", Array[Object](Integer.valueOf(product.key), Spanish), trx) == 1)
        val after = compare(format, DeliveryDetailFormat, language(Spanish), trx = trx)
        Then("the display is NULL on both paths: the lookup cell is absent in the same rows")
        assertNoProblems(Vector(after))
        assert(productCells(after.legacy).count(_.isEmpty) == productCells(before.legacy).count(_.isEmpty) + rowsOfProduct)
        assert(productCells(after.join).count(_.isEmpty) == productCells(after.legacy).count(_.isEmpty))
        And("the join keeps the translation guard of the lookup, the same as the inner join of the subselect")
        val alias = """LEFT OUTER JOIN M_Product_Trl (\w+) ON \(""".r.findFirstMatchIn(after.join.sql).map(_.group(1))
        assert(alias.isDefined, "M_Product translation is not joined")
        assert(after.join.sql.contains(s"AND ${alias.get}.M_Product_ID IS NOT NULL THEN"))
        info(s"rows of the product: $rowsOfProduct")
      }
    }

    Scenario("Only a table with a single key column named as the foreign key is joined") {
      val english = language(English)
      Then("a plain TableDir lookup is joinable")
      val bpartner = org.compiere.model.MLookupFactory.getTableDirEmbed(english, "C_BPartner_ID")
      assert(bpartner.getTableName == "C_BPartner" && bpartner.isJoinable && !bpartner.hasVirtualColumn)
      And("a foreign key that is mapped to another key column keeps the subselect (SalesRep_ID -> AD_User_ID)")
      val salesRep = org.compiere.model.MLookupFactory.getTableDirEmbed(english, "SalesRep_ID")
      assert(salesRep.getKeyColumn == "AD_User_ID" && !salesRep.isJoinable)
      And("the embedded SELECT of the legacy API is built from the same definition")
      val embedded = org.compiere.model.MLookupFactory.getLookup_TableDirEmbed(english, "C_BPartner_ID", "C_Order")
      assert(embedded.startsWith("SELECT ") && embedded.endsWith(" WHERE C_Order.C_BPartner_ID=C_BPartner.C_BPartner_ID"))
      assert(bpartner.getDisplayExpression("C_BPartner", "C_BPartner_Trl") != null && embedded.contains(bpartner.getDisplayExpression("C_BPartner", "C_BPartner_Trl")))
    }

    Scenario("Without an override the REPORT_LOOKUP_JOIN SysConfig default (Y) uses joins") {
      val run = runEngine(storedFormat(SalesOrderFormat), language(English), None)
      assert(run.sql.startsWith(s"$Tag$SalesOrderFormat */ "))
    }

    Scenario("Lookup aliases are built from the dictionary name of the table and limited to 30 characters") {
      Given("dictionary names with spaces, punctuation and accents")
      Then("the word is the CamelCase of the name")
      assert(ReportLookupAlias.word("C_BPartner", "Business Partner") == "BusinessPartner")
      assert(ReportLookupAlias.word("AD_User", "User/Contact") == "UserContact")
      assert(ReportLookupAlias.word("C_UOM", "UOM") == "UOM")
      assert(ReportLookupAlias.word("PP_Order", "Manufacturing Order") == "ManufacturingOrder")
      assert(ReportLookupAlias.word("C_Order", "Order") == "Order")
      assert(ReportLookupAlias.word("X_Quote", "Cotización de Línea") == "CotizacionDeLinea")
      And("without a usable name the table name without its prefix is used")
      assert(ReportLookupAlias.word("C_BPartner_Location", null) == "BPartner_Location")
      assert(ReportLookupAlias.word("C_BPartner", "") == "BPartner")
      assert(ReportLookupAlias.word("C_Foo", "123 !!") == "Foo")
      assert(ReportLookupAlias.word("Order", "...") == "Order")
      And("the word of a real table is derived from its dictionary name")
      val bpartner = MTable.get(Env.getCtx, "C_BPartner")
      assert(ReportLookupAlias.word("C_BPartner") == ReportLookupAlias.word("C_BPartner", bpartner.getName))
      And("the alias is the word, the lookup number and _Trl for the translation table")
      val word = ReportLookupAlias.word("M_Product")
      assert(ReportLookupAlias.lookup("M_Product", 3, "C_Invoice") == s"${word}_3")
      assert(ReportLookupAlias.translation("M_Product", 3, "C_Invoice") == s"${word}_Trl_3")
      And("the alias is never equal to the base table")
      assert(!ReportLookupAlias.lookup("C_BPartner", 1, s"${ReportLookupAlias.word("C_BPartner")}_1").equalsIgnoreCase(s"${ReportLookupAlias.word("C_BPartner")}_1"))
      Given("LMX_DocType, whose dictionary name has 32 characters once in CamelCase")
      val longWord = ReportLookupAlias.word("LMX_DocType")
      assert(longWord == "DocumentTypeForLMXLocalizationID")
      When("the aliases are generated for lookup 12")
      val lookup = ReportLookupAlias.lookup("LMX_DocType", 12, "C_Invoice")
      val translation = ReportLookupAlias.translation("LMX_DocType", 12, "C_Invoice")
      Then("the word is truncated and the suffix with the lookup number is kept")
      assert(lookup == "DocumentTypeForLMXLocalizat_12")
      assert(translation == "DocumentTypeForLMXLocal_Trl_12")
      assert(lookup.length == 30 && translation.length == 30)
    }

    Scenario("A printed TableDir column that points to a table with a long name gets a truncated alias") {
      Given("the LMX_Comprobante print format that prints LMX_DocType_ID")
      val result = compare(storedFormat(50268), 50268, language(English))
      Then("the data is identical")
      assertNoProblems(Vector(result))
      assert(result.compared)
      val alias = """LEFT OUTER JOIN LMX_DocType (\w+) ON \(""".r.findFirstMatchIn(result.join.sql).map(_.group(1))
      info(s"LMX_DocType alias: ${alias.getOrElse("not joined (fallback to subselect)")}")
      alias.foreach { name =>
        assert(name.length <= 30)
        assert(name.startsWith("DocumentTypeForLMX") && name.matches("\\w+_\\d+"))
      }
    }
  }

  private case class Item(
      column: String,
      seqNo: Int,
      sortNo: Int = 0,
      isDesc: Boolean = false,
      groupBy: Boolean = false,
      summarized: Boolean = false,
      counted: Boolean = false
  )

  /** Runs the body in a new transaction that is always rolled back */
  private def withTrx[T](body: String => T): T = {
    val trxName = Trx.createTrxName("ReportLookupJoin")
    val trx = Trx.get(trxName, true)
    try body(trxName)
    finally {
      trx.rollback()
      trx.close()
    }
  }

  private def scalarDecimal(sql: String): java.math.BigDecimal = {
    val statement = DB.prepareStatement(sql, null)
    val rs = statement.executeQuery()
    try { rs.next(); rs.getBigDecimal(1) } finally DB.close(rs, statement)
  }

  private def insert(sql: String, trx: String, params: Object*): Unit =
    DB.executeUpdateEx(sql, params.toArray, trx)

  private def columnId(table: String, column: String): Integer =
    Integer.valueOf(ids(s"SELECT c.AD_Column_ID FROM AD_Column c INNER JOIN AD_Table t ON (t.AD_Table_ID=c.AD_Table_ID) WHERE t.TableName='$table' AND c.ColumnName='$column'").head)

  private def insertReportView(trx: String, name: String, table: String, groupFunctions: Seq[(String, String)]): Int = {
    val client = Env.getAD_Client_ID(Env.getCtx)
    val id = DB.getNextID(client, "AD_ReportView", null)
    insert("INSERT INTO AD_ReportView (AD_ReportView_ID,AD_Client_ID,AD_Org_ID,CreatedBy,UpdatedBy,Name,AD_Table_ID) " +
      "VALUES (?,?,0,100,100,?,?)", trx, Integer.valueOf(id), Integer.valueOf(client), name, Integer.valueOf(MTable.getTable_ID(table)))
    groupFunctions.foreach { case (column, function) =>
      insert("INSERT INTO AD_ReportView_Col (AD_ReportView_Col_ID,AD_Client_ID,AD_Org_ID,CreatedBy,UpdatedBy,AD_ReportView_ID,AD_Column_ID,IsGroupFunction,FunctionColumn) " +
        "VALUES (?,?,0,100,100,?,?,'Y',?)", trx, Integer.valueOf(DB.getNextID(client, "AD_ReportView_Col", null)),
        Integer.valueOf(client), Integer.valueOf(id), columnId(table, column), function)
    }
    id
  }

  private def insertFormat(trx: String, name: String, table: String, reportView: Int, items: Seq[Item]): Int = {
    val client = Env.getAD_Client_ID(Env.getCtx)
    val id = DB.getNextID(client, "AD_PrintFormat", null)
    insert("INSERT INTO AD_PrintFormat (AD_PrintFormat_ID,AD_Client_ID,AD_Org_ID,CreatedBy,UpdatedBy,Name,AD_Table_ID,AD_ReportView_ID," +
      "AD_PrintPaper_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintTableFormat_ID,HeaderMargin,FooterMargin,IsTableBased) " +
      "VALUES (?,?,0,100,100,?,?,?,(SELECT MIN(AD_PrintPaper_ID) FROM AD_PrintPaper WHERE IsDefault='Y')," +
      "(SELECT MIN(AD_PrintColor_ID) FROM AD_PrintColor WHERE IsDefault='Y'),(SELECT MIN(AD_PrintFont_ID) FROM AD_PrintFont WHERE IsDefault='Y')," +
      "(SELECT MIN(AD_PrintTableFormat_ID) FROM AD_PrintFormat WHERE AD_PrintTableFormat_ID IS NOT NULL),0,0,'Y')",
      trx, Integer.valueOf(id), Integer.valueOf(client), name, Integer.valueOf(MTable.getTable_ID(table)),
      if (reportView > 0) Integer.valueOf(reportView) else null)
    items.foreach { item =>
      insert("INSERT INTO AD_PrintFormatItem (AD_PrintFormatItem_ID,AD_Client_ID,AD_Org_ID,CreatedBy,UpdatedBy,AD_PrintFormat_ID,AD_Column_ID,Name," +
        "PrintAreaType,SeqNo,PrintFormatType,XSpace,YSpace,XPosition,YPosition,MaxWidth,MaxHeight,FieldAlignmentType,LineAlignmentType," +
        "SortNo,IsPrinted,IsDesc,IsGroupBy,IsSummarized,IsCounted) " +
        "VALUES (?,?,0,100,100,?,?,?,'C',?,'F',0,0,0,0,0,0,'D','X',?,'Y',?,?,?,?)",
        trx, Integer.valueOf(DB.getNextID(client, "AD_PrintFormatItem", null)), Integer.valueOf(client), Integer.valueOf(id),
        columnId(table, item.column), item.column, Integer.valueOf(item.seqNo), Integer.valueOf(item.sortNo),
        yn(item.isDesc), yn(item.groupBy), yn(item.summarized), yn(item.counted))
    }
    id
  }

  private def yn(flag: Boolean): String = if (flag) "Y" else "N"

  private def Trx_name(name: String): String = org.compiere.util.Trx.createTrxName(s"ReportLookupJoin_$name")
}
