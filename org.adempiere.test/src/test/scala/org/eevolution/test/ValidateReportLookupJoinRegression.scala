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
import java.util.logging.{Handler, Level, LogRecord}

import org.compiere.model.{MColumn, MQuery, MTable}
import org.compiere.print.{DataEngine, MPrintFormat, PrintData, PrintDataElement, ReportLookupAlias}
import org.compiere.util.{CLogger, DB, DisplayType, Env, Language, Trx}
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
  private val OrderLineFormat = 1000008 // C_OrderLine: S_ContractLine_ID chains S_Contract, C_BPartner and C_DocType
  private val OrderDetailFormat = 1000004 // RV_OrderDetail: M_Lot_ID chains M_Product (translated)
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
  private def orderBy(sql: String): String = OrderByStart.findAllMatchIn(sql).toVector.lastOption.map(m => " ORDER BY " + sql.substring(m.end)).getOrElse("")

  /** Key column of the table when it has exactly one active real key column (dictionary oracle of the tie-break) */
  private def singleKey(table: String): Option[String] = {
    val statement = DB.prepareStatement(
      "SELECT c.ColumnName, c.ColumnSQL FROM AD_Column c INNER JOIN AD_Table t ON (t.AD_Table_ID=c.AD_Table_ID) " +
        "WHERE UPPER(t.TableName)=UPPER(?) AND c.IsKey='Y' AND c.IsActive='Y'", null)
    statement.setString(1, table)
    val rs = statement.executeQuery()
    val keys = try Iterator.continually(rs).takeWhile(_.next()).map(r => (r.getString(1), Option(r.getString(2)).exists(_.trim.nonEmpty))).toVector
    finally DB.close(rs, statement)
    keys match {
      case Vector((column, false)) => Some(column)
      case _ => None
    }
  }

  /** ORDER BY of the join path for a legacy statement without sort and without GROUP BY: the single key of the base table */
  private def tieBreak(legacy: PrintData): Option[String] =
    if (orderBy(legacy.getSQL).nonEmpty || legacy.getSQL.contains(" GROUP BY ")) None
    else singleKey(legacy.getTableName).map(key => s" ORDER BY ${legacy.getTableName}.$key")

  /** ORDER BY starts after a space (legacy statement) or at the start of a line (join statement) */
  private val OrderByStart = """[ \n]ORDER BY """.r

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
  private val TranslationJoin = """LEFT OUTER JOIN (\w+_Trl) (\w+_Trl_\d+(?:_\d+)*) ON \(""".r
  private val AnyJoin = """LEFT OUTER JOIN (\w+(?<!_Trl)) (\w+) ON \((\w+)\.(\w+)=(\w+)\.(\w+)\)""".r
  private val AliasPath = """^(.+?)((?:_\d+)+)$""".r
  private val TranslationPath = """_Trl_(\d+(?:_\d+)*)$""".r

  /** Join of a lookup table with a semantic alias <Word>_<n>[_<m>[_<k>]]; a nested one hangs on the alias of its parent */
  private case class JoinRow(table: String, alias: String, parentAlias: String, column: String, key: String) {
    val path: String = alias match {
      case AliasPath(_, numbers) => numbers.drop(1)
      case _ => ""
    }
    def isLookup: Boolean = path.nonEmpty
    def depth: Int = if (path.isEmpty) 0 else path.split("_").length
  }

  private def lookupJoins(sql: String): Vector[JoinRow] =
    AnyJoin.findAllMatchIn(sql).map(m => JoinRow(m.group(1), m.group(2), m.group(3), m.group(4), m.group(6))).toVector.filter(_.isLookup)

  /** The join of a lookup table: top level (parent = the derived table) or nested on the alias of its parent */
  private def joinOf(sql: String, table: String, parentAlias: String): JoinRow =
    lookupJoins(sql).find(j => j.table == table && j.parentAlias == parentAlias).getOrElse(fail(s"$table is not joined on $parentAlias"))

  /** Nested TableDir / Search(_ID) identifier columns of a table that the join path chains (dictionary oracle, base language) */
  private val nestedColumnCache = scala.collection.mutable.Map.empty[String, Vector[String]]
  private def nestedColumns(table: String): Vector[String] = nestedColumnCache.getOrElseUpdate(table, {
    val statement = DB.prepareStatement(
      "SELECT c.ColumnName FROM AD_Table t INNER JOIN AD_Column c ON (c.AD_Table_ID=t.AD_Table_ID) " +
        "WHERE t.TableName=? AND c.IsIdentifier='Y' AND c.AD_Reference_ID IN (19,30) AND c.ColumnName LIKE '%\\_ID' " +
        "AND (c.ColumnSQL IS NULL OR TRIM(c.ColumnSQL)='') ORDER BY c.SeqNo", null)
    statement.setString(1, table)
    val rs = statement.executeQuery()
    val columns = try Iterator.continually(rs).takeWhile(_.next()).map(_.getString(1)).toVector finally DB.close(rs, statement)
    columns.filter { column =>
      val embed = org.compiere.model.MLookupFactory.getTableDirEmbed(language(English), column)
      embed != null && embed.isJoinable && !embed.hasVirtualColumn
    }
  })

  /** The nested TableDir columns of every joined lookup are LEFT OUTER JOINs up to depth 3 and correlated subselects below it */
  private def nestedJoinProblems(sql: String): Vector[String] = {
    val problems = ArrayBuffer.empty[String]
    val rows = lookupJoins(sql)
    rows.foreach { parent =>
      nestedColumns(parent.table).foreach { column =>
        val child = rows.find(r => r.parentAlias == parent.alias && r.column == column)
        val subselect = sql.contains(s" WHERE ${parent.alias}.$column=")
        if (parent.depth < 3) {
          if (child.isEmpty) problems += s"${parent.alias}.$column is not joined"
          if (subselect) problems += s"${parent.alias}.$column keeps a nested display subselect"
          child.foreach { c =>
            val expected = s"${parent.path}_${rows.filter(_.parentAlias == parent.alias).indexOf(c) + 1}"
            if (c.path != expected) problems += s"${c.alias} should have the path $expected"
            if (c.key != c.column) problems += s"${c.alias} is joined by ${c.key}, not by $column"
          }
        } else {
          if (child.isDefined) problems += s"${parent.alias}.$column is joined below the depth limit"
          if (!subselect) problems += s"${parent.alias}.$column must stay a subselect correlated to ${parent.alias}"
        }
      }
    }
    problems.toVector
  }

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
          val sortColumns = l.getColumnInfo.toVector.filter(_.getSortOrderIndex > 0).sortBy(_.getSortOrderIndex)
            .map(c => (c.getColumnName, c.hasAlias && c.getAlias != DataEngine.KEY))
          // The ORDER BY sorts a lookup column by its display (synthetic alias), not by its ID: two IDs with the same
          // display (e.g. two cost elements named "Material") are tied, so the key of a lookup column is its display
          // only. Other columns sort by their value. The per-tie-group multiset below still checks the ID/display pairs.
          def sortKey(row: Vector[Cell]) = sortColumns.map { case (name, isLookup) =>
            row.find(_.name == name).map(c => if (isLookup) c.value else (c.key, c.value))
          }
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
        // a format without sort columns gets the key of the base table on the join path (S26), nothing else changes
        val expectedOrderBy = tieBreak(l).getOrElse(orderBy(l.getSQL))
        if (expectedOrderBy != orderBy(j.getSQL)) problems += s"ORDER BY differs: expected $expectedOrderBy, join ${orderBy(j.getSQL)}"
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
      // the derived table is closed by OFFSET 0 on PostgreSQL, and only there
      if (sql.contains("\nFROM (") && sql.contains(s" OFFSET 0) ${pd.getTableName}\n") != DB.isPostgreSQL)
        problems += s"OFFSET 0 closing the derived table: expected ${DB.isPostgreSQL}, SQL ${sql.take(60)}"
      if (sql.contains(" AS ")) {
        // semantic aliases only: TableName_n / TableName_Trl_n, at most 30 characters
        val baseTable = pd.getTableName
        lookupJoins(sql).foreach { row =>
          if (row.alias != ReportLookupAlias.lookup(row.table, row.path, baseTable)) problems += s"unexpected lookup alias ${row.alias} for ${row.table}"
          if (row.alias.length > 30) problems += s"alias longer than 30 characters: ${row.alias}"
          if (row.alias.equalsIgnoreCase(baseTable)) problems += s"alias equals the base table: ${row.alias}"
          if (row.depth > 3) problems += s"join deeper than the depth limit: ${row.alias}"
          if (row.depth > 1 && !lookupJoins(sql).exists(p => p.alias == row.parentAlias && p.path == row.path.substring(0, row.path.lastIndexOf('_'))))
            problems += s"nested join ${row.alias} is not on a parent join: ${row.parentAlias}"
        }
        TranslationJoin.findAllMatchIn(sql).foreach { m =>
          val (trlTable, alias) = (m.group(1), m.group(2))
          val path = TranslationPath.findFirstMatchIn(alias).map(_.group(1)).getOrElse("")
          val table = trlTable.substring(0, trlTable.length - "_Trl".length)
          if (alias != ReportLookupAlias.translation(table, path, baseTable)) problems += s"unexpected translation alias $alias for $trlTable"
          if (alias.length > 30) problems += s"alias longer than 30 characters: $alias"
        }
      }
      // every top-level TableDir column is either a join CASE or (fallback) the legacy subselect, never missing
      tableDirColumns(pd).foreach { c =>
        if (!isJoined(sql, c.getAlias) && !sql.contains(s") AS ${c.getAlias},"))
          problems += s"TableDir column ${c.getColumnName} is not resolved as ${c.getAlias}"
      }
    }
    problems.toVector
  }

  /** The lookup alias is selected from a join: as a CASE guard or, for a NOT NULL single column, as the bare column of the join */
  private def isJoined(sql: String, alias: String): Boolean =
    sql.contains(s"END AS $alias,") || (sql.contains(s" AS $alias,") && itemText(sql, alias).matches(BareLookupText))

  private val BareLookupText = """\w+_\d+(?:_\d+)*\.\w+"""

  /** Text of the select item that ends with ` AS alias,`, up to the previous comma that is not inside parentheses */
  private def itemText(sql: String, alias: String): String = {
    val end = sql.indexOf(s" AS $alias,")
    assert(end > 0, s"$alias is not selected in $sql")
    var depth = 0
    var i = end
    while (i > 0) {
      sql.charAt(i - 1) match {
        case ')' => depth += 1
        case '(' => depth -= 1
        case ',' if depth == 0 => return sql.substring(i, end)
        case _ => if (depth == 0 && (sql.startsWith("SELECT ", i - 7) || sql.startsWith("*/ ", i - 3))) return sql.substring(i, end)
      }
      i -= 1
    }
    sql.substring(0, end)
  }

  /** The database declares the column NOT NULL, read from the JDBC catalog */
  private def isNotNullInDatabase(table: String, column: String): Boolean = {
    val connection = DB.getConnectionRO()
    try {
      val metadata = connection.getMetaData
      def fold(name: String) = if (metadata.storesUpperCaseIdentifiers) name.toUpperCase else if (metadata.storesLowerCaseIdentifiers) name.toLowerCase else name
      val rs = metadata.getColumns(null, DB.getDatabase.getSchema, fold(table), fold(column))
      try {
        val found = Iterator.continually(rs).takeWhile(_.next()).find(r => r.getString("TABLE_NAME").equalsIgnoreCase(table) && r.getString("COLUMN_NAME").equalsIgnoreCase(column))
        assert(found.isDefined, s"$table.$column is not in the database catalog")
        found.get.getString("IS_NULLABLE") == "NO"
      } finally rs.close()
    } finally connection.close()
  }

  private def joinedLookups(run: Run): Int = run.pd.map(pd => tableDirColumns(pd).count(c => isJoined(run.sql, c.getAlias))).getOrElse(0)
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

    Scenario("The names of the derived table stay unique and within 30 characters when output names repeat") {
      Given("select items whose output names repeat, are long, or equal the table of the report")
      val long = "A" * ReportLookupAlias.MAX_LENGTH
      val outputs = Seq("Name", "Name", long, long, long, "A" * (ReportLookupAlias.MAX_LENGTH - 2) + "_1", "C_Order", "C_Order")
      When("the plan names them in the inner select")
      val names = innerNames("C_Order", outputs)
      Then("every name is unique, has at most 30 characters and is not the table of the report")
      assert(names.map(_.toUpperCase).distinct.size == names.size, s"names repeat: ${names.mkString(", ")}")
      assert(names.forall(_.length <= ReportLookupAlias.MAX_LENGTH), s"name over 30 characters: ${names.mkString(", ")}")
      assert(!names.exists(_.equalsIgnoreCase("C_Order")), s"name equals the base table: ${names.mkString(", ")}")
      And("the names that fit keep the numbered form of the legacy statement")
      assert(names.take(2) == Seq("Name", "Name_1"))
      assert(names(2) == long)
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
      withTrx { trx =>
        val tableId = MTable.getTable_ID("C_Payment_v")
        val format = MPrintFormat.createFromTable(Env.getCtx, tableId, 0, trx)
        assert(format != null)
        When("a print format of the view is generated with the ReportView WHERE")
        val result = compare(() => format, format.get_ID, language(English), reportViewId = PaymentViewReportView, trx = trx)
        Then("the data is identical")
        assertNoProblems(Vector(result))
        assert(result.compared)
        assert(result.join.sql.contains("DocStatus IN ('CO','CL')"))
        info(s"rows=${result.legacy.pd.get.getRowCount}, lookups joined=${joinedLookups(result.join)}")
      }
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
        assert(sql.contains(s"\nFROM (SELECT "))
        assert(lookups.nonEmpty)
        assert(subselectLookups(result.join) == 0, "no top level TableDir display subselect expected")
        assert(joinedLookups(result.join) == lookups.size)
        val joins = lookupJoins(sql)
        val topLevel = joins.filter(_.depth == 1)
        // join reuse: one join per distinct foreign key, never more than lookups
        assert(topLevel.nonEmpty && topLevel.size <= lookups.size)
        assert(joins.map(_.alias).distinct.size == joins.size)
      }
      And("the lookups are joined by foreign key with the domain word of the table as alias, e.g. LEFT OUTER JOIN C_BPartner BPartner_1")
      results.foreach { result =>
        val joins = lookupJoins(result.join.sql)
        assert(joins.nonEmpty)
        joins.foreach { join =>
          if (join.depth == 1)
            assert(join.parentAlias == result.join.pd.get.getTableName, s"join must start from the derived table: ${join.alias}")
          else
            assert(joins.exists(_.alias == join.parentAlias), s"nested join must start from a join: ${join.alias}")
          assert(join.column == join.key, join.alias)
          val word = ReportLookupAlias.word(join.table)
          assert(join.alias.startsWith(word.take(ReportLookupAlias.MAX_LENGTH - s"_${join.path}".length)), join.alias)
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

    Scenario("A product without translation shows its base display on the join path and NULL on the legacy path") {
      Given("the delivery print format in es_MX that prints M_Product_ID as TableDir")
      withTrx { trx =>
        val format = () => new MPrintFormat(Env.getCtx, DeliveryDetailFormat, trx)
        val before = compare(format, DeliveryDetailFormat, language(Spanish), trx = trx)
        assertNoProblems(Vector(before))
        def productCells(run: Run) = cells(run.pd.get).map(_.find(_.name == "M_Product_ID"))
        val shown = productCells(before.legacy).flatten
        assert(shown.nonEmpty)
        val product = shown.head
        // the demo translation has the same text as the base name: give it its own text to tell them apart
        assert(DB.executeUpdateEx("UPDATE M_Product_Trl SET Name='RLJ Traducido' WHERE M_Product_ID=? AND AD_Language=?", Array[Object](Integer.valueOf(product.key), Spanish), trx) == 1)
        val translatedBefore = compare(format, DeliveryDetailFormat, language(Spanish), trx = trx)
        assertNoProblems(Vector(translatedBefore))
        val rowsOfProduct = productCells(translatedBefore.legacy).count(_.exists(_.key == product.key))
        When(s"the es_MX translation of product ${product.key} is deleted inside the transaction")
        assert(DB.executeUpdateEx("DELETE FROM M_Product_Trl WHERE M_Product_ID=? AND AD_Language=?", Array[Object](Integer.valueOf(product.key), Spanish), trx) == 1)
        val after = compare(format, DeliveryDetailFormat, language(Spanish), trx = trx)
        Then("the legacy path shows NULL there: the lookup cell is absent")
        assert(after.legacy.pd.isDefined && after.join.pd.isDefined)
        assert(productCells(after.legacy).count(_.isEmpty) == productCells(translatedBefore.legacy).count(_.isEmpty) + rowsOfProduct)
        And("the join path shows the display of the product in the base language (S25), the other rows are the same")
        val base = DB.getSQLValueStringEx(trx, "SELECT Value||' - '||Name FROM M_Product WHERE M_Product_ID=?", Integer.valueOf(product.key))
        assert(base != null && !base.endsWith("RLJ Traducido"), base)
        assert(productCells(translatedBefore.join).flatten.exists(c => c.key == product.key && c.value.endsWith("RLJ Traducido")))
        assert(productCells(after.join).count(_.isEmpty) == productCells(translatedBefore.join).count(_.isEmpty))
        assert(multiset(productCells(after.join).flatten.map(c => (c.key, c.value))) ==
          multiset(productCells(translatedBefore.join).flatten.map(c => (c.key, if (c.key == product.key) base else c.value))))
        And("the join keeps the guard of the lookup row only and falls back per translated column")
        val alias = """LEFT OUTER JOIN M_Product_Trl (\w+) ON \(""".r.findFirstMatchIn(after.join.sql).map(_.group(1))
        assert(alias.isDefined, "M_Product translation is not joined")
        assert(after.join.sql.contains(s"NVL(${alias.get}.Name,"))
        assert(!after.join.sql.contains(s"AND ${alias.get}.M_Product_ID IS NOT NULL THEN"))
        info(s"rows of the product: $rowsOfProduct")
      }
    }


    Scenario("Nested TableDir identifiers are chained LEFT OUTER JOINs") {
      Given("the order line, order detail and order print formats, whose lookups have TableDir identifier columns")
      val results = Vector(OrderLineFormat, OrderDetailFormat, SalesOrderFormat).map(id => compare(storedFormat(id), id, language(English)))
      Then("the data is identical")
      assertNoProblems(results)
      And("no nested display subselect is left up to depth 3, and the joins hang on the alias of their parent")
      results.foreach { result =>
        val joins = lookupJoins(result.join.sql)
        info(s"format ${result.formatId}: ${joins.count(_.depth == 1)} joins, ${joins.count(_.depth == 2)} nested, ${joins.count(_.depth == 3)} at depth 3")
        assert(joins.exists(_.depth >= 2), s"format ${result.formatId} has no nested join")
        assert(nestedJoinProblems(result.join.sql).isEmpty, nestedJoinProblems(result.join.sql).mkString("; "))
        assert(!result.join.sql.substring(result.join.sql.indexOf(") " + result.join.pd.get.getTableName)).contains("(SELECT"),
          "the outer statement must not have subselects for these formats")
      }
      And("S_ContractLine chains S_Contract, which chains C_BPartner and C_DocType, as <Word>_<n>_<m> aliases")
      val sql = results.head.join.sql
      val joins = lookupJoins(sql)
      val line = joinOf(sql, "S_ContractLine", "C_OrderLine")
      val contract = joinOf(sql, "S_Contract", line.alias)
      val partner = joinOf(sql, "C_BPartner", contract.alias)
      val docType = joinOf(sql, "C_DocType", contract.alias)
      assert(line.depth == 1 && contract.depth == 2 && partner.depth == 3 && docType.depth == 3)
      assert(contract.alias == ReportLookupAlias.lookup("S_Contract", s"${line.path}_1", "C_OrderLine"))
      assert(partner.alias == ReportLookupAlias.lookup("C_BPartner", s"${line.path}_1_1", "C_OrderLine"))
      assert(docType.alias == ReportLookupAlias.lookup("C_DocType", s"${line.path}_1_2", "C_OrderLine"))
      assert(sql.contains(s"LEFT OUTER JOIN S_Contract ${contract.alias} ON (${line.alias}.S_Contract_ID=${contract.alias}.S_Contract_ID)"))
      info(s"${line.alias} -> ${contract.alias} -> ${partner.alias}, ${docType.alias}")
    }

    Scenario("Every table based print format chains its nested lookups the same way") {
      val results = sweep(English)
      val problems = results.filter(_.compared).flatMap(r => nestedJoinProblems(r.join.sql).map(p => s"format ${r.formatId}: $p"))
      assert(problems.isEmpty, problems.mkString("\n  "))
      val nested = results.filter(_.compared).map(r => lookupJoins(r.join.sql).count(_.depth >= 2)).sum
      info(s"$nested nested joins in ${results.count(_.compared)} print formats")
      assert(nested > 0)
    }

    Scenario("A nested lookup whose foreign key is NULL shows what the subselect shows") {
      withTrx { trx =>
        Given("order lines of projects whose C_ProjectStatus_ID is NULL, and of a project that has a status")
        val status = DB.getNextID(Env.getAD_Client_ID(Env.getCtx), "C_ProjectStatus", null)
        insert("INSERT INTO C_ProjectStatus (C_ProjectStatus_ID,AD_Client_ID,AD_Org_ID,Created,CreatedBy,Updated,UpdatedBy,Value,Name,SeqNo) VALUES (?,?,0,CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,'RLJ','RLJ Status',7)",
          trx, Integer.valueOf(status), Integer.valueOf(Env.getAD_Client_ID(Env.getCtx)))
        assert(DB.executeUpdateEx("UPDATE C_Project SET C_ProjectStatus_ID=? WHERE C_Project_ID=101", Array[Object](Integer.valueOf(status)), trx) == 1)
        assert(DB.executeUpdateEx("UPDATE C_OrderLine SET C_Project_ID=100 WHERE C_OrderLine_ID=105", Array[Object](), trx) == 1)
        assert(DB.executeUpdateEx("UPDATE C_OrderLine SET C_Project_ID=101 WHERE C_OrderLine_ID=106", Array[Object](), trx) == 1)
        When("the order line print format is generated by both strategies")
        val result = compare(() => new MPrintFormat(Env.getCtx, OrderLineFormat, trx), OrderLineFormat, language(English), trx = trx)
        Then("the data is identical, NULL and empty display values included")
        assertNoProblems(Vector(result))
        val programs = cells(result.legacy.pd.get).flatMap(_.find(_.name == "C_Project_ID")).map(_.value)
        info(s"project displays: ${programs.distinct.mkString(" | ")}")
        assert(programs.contains("Standard - Standard -  - "), "the project without status shows an empty nested display")
        assert(programs.exists(_.contains(" - RLJ - RLJ Status - ")), "the project with status shows the nested display")
        And("the status is joined below the project, with the CASE guard that keeps the NULL of the subselect")
        val project = joinOf(result.join.sql, "C_Project", "C_OrderLine")
        val nested = joinOf(result.join.sql, "C_ProjectStatus", project.alias)
        assert(result.join.sql.contains(s"NVL(CASE WHEN ${nested.alias}.C_ProjectStatus_ID IS NOT NULL THEN "))
        assert(result.join.sql.contains(s"LEFT OUTER JOIN C_ProjectStatus ${nested.alias} ON (${project.alias}.C_ProjectStatus_ID=${nested.alias}.C_ProjectStatus_ID)"))
        assert(!result.join.sql.contains(s"INNER JOIN C_ProjectStatus"))
      }
    }

    Scenario("A nested translated identifier is joined by language and falls back to the base value in es_MX") {
      withTrx { trx =>
        Given("a lot of a translated product used by the only order detail row with an attribute set instance")
        val client = Env.getAD_Client_ID(Env.getCtx)
        val lot = DB.getNextID(client, "M_Lot", null)
        insert("INSERT INTO M_Lot (M_Lot_ID,AD_Client_ID,AD_Org_ID,Created,CreatedBy,Updated,UpdatedBy,Name,M_Product_ID) VALUES (?,?,0,CURRENT_TIMESTAMP,100,CURRENT_TIMESTAMP,100,'RLJ Lot',134)", trx, Integer.valueOf(lot), Integer.valueOf(client))
        assert(DB.executeUpdateEx("UPDATE M_AttributeSetInstance SET M_Lot_ID=? WHERE M_AttributeSetInstance_ID=1000000", Array[Object](Integer.valueOf(lot)), trx) == 1)
        // the demo translation has the same text as the base name: give it its own text to tell them apart
        assert(DB.executeUpdateEx("UPDATE M_Product_Trl SET Name='RLJ Traducido' WHERE M_Product_ID=134 AND AD_Language=?", Array[Object](Spanish), trx) == 1)
        When("the order detail print format is generated in es_MX by both strategies")
        val format = () => new MPrintFormat(Env.getCtx, OrderDetailFormat, trx)
        val before = compare(format, OrderDetailFormat, language(Spanish), trx = trx)
        Then("the data is identical and the product of the lot is read from M_Product_Trl")
        assertNoProblems(Vector(before))
        val lotJoin = joinOf(before.join.sql, "M_Lot", "RV_OrderDetail")
        val product = joinOf(before.join.sql, "M_Product", lotJoin.alias)
        val trl = s"""LEFT OUTER JOIN M_Product_Trl (\\w+) ON \\(${product.alias}\\.M_Product_ID=""".r.findFirstMatchIn(before.join.sql).map(_.group(1))
        assert(trl.contains(ReportLookupAlias.translation("M_Product", product.path, "RV_OrderDetail")), trl.toString)
        assert(before.join.sql.contains(s"${trl.get}.AD_Language='$Spanish'"))
        assert(before.join.sql.contains(s"CASE WHEN ${product.alias}.M_Product_ID IS NOT NULL THEN "))
        assert(before.join.sql.contains(s"NVL(${trl.get}.Name,${product.alias}.Name)"))
        val lots = cells(before.join.pd.get).flatMap(_.find(_.name == "M_Lot_ID")).map(_.value)
        info(s"lot display: ${lots.mkString(" | ")}")
        assert(lots.exists(_.contains("RLJ Lot")) && lots.exists(_.contains("RLJ Traducido")))
        And("without the es_MX translation of the product the join path shows the base name and the legacy path the empty display")
        assert(DB.executeUpdateEx("DELETE FROM M_Product_Trl WHERE M_Product_ID=134 AND AD_Language=?", Array[Object](Spanish), trx) == 1)
        val after = compare(format, OrderDetailFormat, language(Spanish), trx = trx)
        assert(after.compared && joinSqlProblems(OrderDetailFormat, after.join).isEmpty)
        val lotsAfter = cells(after.join.pd.get).flatMap(_.find(_.name == "M_Lot_ID")).map(_.value)
        info(s"lot display without translation: ${lotsAfter.mkString(" | ")}")
        val lotsLegacy = cells(after.legacy.pd.get).flatMap(_.find(_.name == "M_Lot_ID")).map(_.value)
        assert(lotsAfter != lots && lotsLegacy != lots, "the missing translation changes the display")
        assert(lotsAfter.exists(_.contains("RLJ Lot")))
        val baseName = DB.getSQLValueStringEx(trx, "SELECT Name FROM M_Product WHERE M_Product_ID=134")
        assert(lotsAfter.exists(_.contains(baseName)) && !lotsLegacy.exists(_.contains(baseName)), s"$lotsAfter / $lotsLegacy")
      }
    }

    Scenario("Nested lookups below depth 3 stay subselects correlated to the deepest alias") {
      withTrx { trx =>
        Given("a print format of RV_M_Transaction_Costing that prints M_CostDetail_ID, whose identifier chains C_LandedCostAllocation, C_InvoiceLine and C_Invoice")
        val formatId = insertFormat(trx, "RLJ Depth", "RV_M_Transaction_Costing", 0, Seq(
          Item("M_CostDetail_ID", seqNo = 10), Item("M_Product_ID", seqNo = 20)))
        val result = compare(() => new MPrintFormat(Env.getCtx, formatId, trx), formatId, language(English), trx = trx)
        Then("the data is identical")
        assertNoProblems(Vector(result))
        val joins = lookupJoins(result.join.sql)
        val costDetail = joinOf(result.join.sql, "M_CostDetail", "RV_M_Transaction_Costing")
        val allocation = joinOf(result.join.sql, "C_LandedCostAllocation", costDetail.alias)
        val invoiceLine = joinOf(result.join.sql, "C_InvoiceLine", allocation.alias)
        info(s"${costDetail.alias} -> ${allocation.alias} -> ${invoiceLine.alias}")
        assert(Seq(costDetail, allocation, invoiceLine).map(_.depth) == Seq(1, 2, 3))
        And("C_Invoice, the fourth level, is a subselect correlated to the depth 3 alias, not a join")
        assert(!joins.exists(_.table == "C_Invoice"))
        assert(result.join.sql.contains(s"WHERE ${invoiceLine.alias}.C_Invoice_ID=C_Invoice.C_Invoice_ID"))
        assert(joins.forall(_.depth <= 3))
        assert(nestedJoinProblems(result.join.sql).isEmpty, nestedJoinProblems(result.join.sql).mkString("; "))
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

    Scenario("A display of one NOT NULL column is the bare column and every other display keeps its guard") {
      withTrx { trx =>
        Given("a payment print format with lookups of one NOT NULL column, of a nullable column and of several columns, one payment with a charge")
        val charge = ids("SELECT MIN(C_Charge_ID) FROM C_Charge").head
        assert(DB.executeUpdateEx("UPDATE C_Payment SET C_Charge_ID=? WHERE C_Payment_ID=(SELECT MIN(C_Payment_ID) FROM C_Payment)",
          Array[Object](Integer.valueOf(charge)), trx) == 1)
        val formatId = insertFormat(trx, "RLJ Not Null", "C_Payment", 0, Seq(
          Item("DocumentNo", seqNo = 10), Item("C_Currency_ID", seqNo = 20), Item("C_BPartner_ID", seqNo = 30),
          Item("C_Charge_ID", seqNo = 40), Item("C_PaymentBatch_ID", seqNo = 50), Item("C_Project_ID", seqNo = 60)
        ))
        assert(isNotNullInDatabase("C_Currency", "ISO_Code") && isNotNullInDatabase("C_BPartner", "Name") && isNotNullInDatabase("C_Charge", "Name"))
        assert(!isNotNullInDatabase("C_PaymentBatch", "DocumentNo"), "the dictionary fixture needs a nullable identifier column")
        def itemOf(run: Run, column: String) = itemText(run.sql, run.pd.get.getColumnInfo.find(_.getColumnName == column).get.getAlias)
        Seq(English, Spanish).foreach { lang =>
          When(s"it is generated by both strategies in $lang")
          val result = compare(() => new MPrintFormat(Env.getCtx, formatId, trx), formatId, language(lang), trx = trx)
          info(result.join.sql)
          Then("the values, the NULL foreign keys and the NULL of a missing display are identical to the subselects")
          assertNoProblems(Vector(result))
          assert(nullLookupCells(Vector(result)) > 0, "payments without charge, batch or project are part of the comparison")
          assert(cells(result.join.pd.get).exists(_.exists(_.name == "C_Charge_ID")), "the payment with a charge shows it")
          And("a NOT NULL single column is the bare column of its join")
          assert(itemOf(result.join, "C_Currency_ID").matches(BareLookupText), itemOf(result.join, "C_Currency_ID"))
          assert(itemOf(result.join, "C_BPartner_ID").matches(BareLookupText), itemOf(result.join, "C_BPartner_ID"))
          And("a nullable column and a display of several columns keep today's guard")
          val batch = itemOf(result.join, "C_PaymentBatch_ID")
          assert(batch.startsWith("CASE WHEN ") && batch.contains(" IS NOT NULL THEN NVL(") && batch.endsWith(" END"), batch)
          val project = itemOf(result.join, "C_Project_ID")
          assert(project.startsWith("CASE WHEN ") && project.contains("||' - '||") && project.endsWith(" END"), project)
          And("the legacy statement keeps its subselects")
          assert(!result.legacy.sql.contains(" END AS ") && result.legacy.sql.contains("(SELECT NVL(C_Currency.ISO_Code,'')"))
          val charged = itemOf(result.join, "C_Charge_ID")
          if (lang == English)
            assert(charged.matches(BareLookupText), charged)
          else {
            And("a translated display keeps the guard on the row of the lookup and falls back to its column")
            assert(charged.startsWith("CASE WHEN ") && charged.contains("_Trl_") && charged.contains("THEN NVL(") && !charged.contains(" AND "), charged)
          }
        }
      }
    }

    Scenario("Every single column guard left in any print format protects a nullable column") {
      Given("the join SQL of every table based print format, in English and in es_MX")
      val results = (sweep(English) ++ sweep(Spanish)).filter(_.join.pd.isDefined)
      val SingleGuard = """CASE WHEN (\w+)\.(\w+) IS NOT NULL THEN NVL\((\w+)\.(\w+),''\) END""".r
      val guarded = results.flatMap { r =>
        val tables = AnyJoin.findAllMatchIn(r.join.sql).map(m => m.group(2) -> m.group(1)).toMap
        SingleGuard.findAllMatchIn(r.join.sql).filter(m => m.group(1) == m.group(3)).map(m => (tables(m.group(3)), m.group(4)))
      }.distinct
      Then("none of the guarded columns is NOT NULL in the database")
      val removable = guarded.filter { case (table, column) => isNotNullInDatabase(table, column) }
      assert(removable.isEmpty, s"NOT NULL columns that still have a guard: $removable")
      And("the bare column is used and the guard of a display of several columns is still there")
      val bare = results.map(r => tableDirColumns(r.join.pd.get).count(c => itemText(r.join.sql, c.getAlias).matches(BareLookupText))).sum
      info(s"bare lookups: $bare, guarded single columns: ${guarded.size}")
      assert(bare > 0)
      assert(results.exists(_.join.sql.contains("||' - '||")))
    }

    Scenario("The outer statement of the join SQL has a line break before FROM, LEFT OUTER JOIN and ORDER BY") {
      Given("print formats with joins, with translation joins, with an ORDER BY and with a GROUP BY")
      withTrx { trx =>
        val grouped = insertFormat(trx, "RLJ Lines", "C_Order", insertReportView(trx, "RLJ Lines", "C_Order", Seq(("GrandTotal", "SUM(@)"))), Seq(
          Item("C_BPartner_ID", seqNo = 10, sortNo = 10), Item("GrandTotal", seqNo = 20)))
        val formats = Vector(SalesOrderFormat, DeliveryDetailFormat, OrderLineFormat, grouped)
        formats.foreach { formatId =>
          Seq(English, Spanish).foreach { lang =>
            When(s"format $formatId is generated in $lang")
            val result = compare(() => new MPrintFormat(Env.getCtx, formatId, trx), formatId, language(lang), trx = trx)
            Then("the data is the same and the statement is accepted by the database")
            assertNoProblems(Vector(result))
            val sql = result.join.sql
            val lines = sql.split("\n", -1).toVector
            And("the first line is the tagged SELECT list and every other line starts with FROM, LEFT OUTER JOIN or ORDER BY")
            assert(lines.head.startsWith(s"$Tag$formatId */ "), lines.head.take(80))
            assert(lines.tail.forall(l => l.startsWith("FROM (SELECT ") || l.startsWith("LEFT OUTER JOIN ") || l.startsWith("ORDER BY ")), sql)
            assert(lines.tail.head.startsWith("FROM (SELECT "), "FROM is on the second line")
            assert(lines.count(_.startsWith("FROM ")) == 1)
            And("there is one line per join and one for the ORDER BY, none inside the statement of the report")
            assert(lines.count(_.startsWith("LEFT OUTER JOIN ")) == lookupJoins(sql).size + TranslationJoin.findAllMatchIn(sql).size)
            assert(lines.count(_.startsWith("ORDER BY ")) == (if (result.legacy.sql.contains(" ORDER BY ") || tieBreak(result.legacy.pd.get).isDefined) 1 else 0))
            assert(!sql.contains("\r") && !sql.contains("\n\n"))
            assert(lines.tail.head.contains(") " + result.join.pd.get.getTableName), "the statement of the report is on the FROM line")
          }
        }
      }
    }

    Scenario("The legacy statement has no line breaks") {
      Given("every table based print format generated with setLookupJoin(false)")
      val legacy = sweep(English).filter(_.legacy.pd.isDefined)
      Then("the statement is a single line, as before")
      assert(legacy.nonEmpty)
      assert(legacy.forall(r => !r.legacy.sql.contains("\n") && !r.legacy.sql.contains("\r")))
    }

    Scenario("The final SQL is logged once, at FINE, by DataEngine.getPrintDataInfo") {
      Given("a handler on the logger of DataEngine at FINE")
      val logger = CLogger.getCLogger(classOf[DataEngine])
      case class Logged(level: Level, message: String, method: String)
      val records = ArrayBuffer.empty[Logged]
      val handler = new Handler {
        // the caller is inferred from the stack of the logging call, so it is read here
        override def publish(record: LogRecord): Unit = records.synchronized(records += Logged(record.getLevel, record.getMessage, record.getSourceMethodName))
        override def flush(): Unit = ()
        override def close(): Unit = ()
      }
      val level = logger.getLevel
      logger.addHandler(handler)
      logger.setLevel(Level.FINE)
      try {
        Seq(true, false).foreach { join =>
          records.clear()
          When(s"a report is generated (lookup join $join)")
          val run = runEngine(storedFormat(SalesOrderFormat), language(English), Some(join))
          Then("the statement appears in exactly one record")
          val sql = run.pd.get.getSQL
          val logged = records.synchronized(records.toVector).filter(_.message != null).filter(_.message.contains(sql))
          assert(logged.size == 1, s"${logged.size} records contain the SQL: ${logged.map(r => s"${r.level} ${r.method}")}")
          And("it is logged at FINE by getPrintDataInfo")
          assert(logged.head.level == Level.FINE && logged.head.method == "getPrintDataInfo", s"${logged.head.level} ${logged.head.method}")
          assert(!records.exists(r => r.message != null && r.message.startsWith("SQL: ")), "loadPrintData does not repeat the SQL")
        }
      } finally {
        logger.removeHandler(handler)
        logger.setLevel(level)
      }
    }
  }

  Feature("The derived table of the join statement is a planning fence on PostgreSQL") {

    Scenario("On PostgreSQL the derived table is closed by OFFSET 0 and the report keeps its rows") {
      withTrx { trx =>
        Given("print formats with joins, with an ORDER BY and with a GROUP BY, in English and in es_MX")
        val grouped = insertFormat(trx, "RLJ Fence", "C_Order", insertReportView(trx, "RLJ Fence", "C_Order", Seq(("GrandTotal", "SUM(@)"))), Seq(
          Item("C_BPartner_ID", seqNo = 10, sortNo = 10), Item("GrandTotal", seqNo = 20)))
        Vector(SalesOrderFormat, DeliveryDetailFormat, OrderLineFormat, grouped).foreach { formatId =>
          Seq(English, Spanish).foreach { lang =>
            When(s"format $formatId is generated in $lang by both strategies")
            val result = compare(() => new MPrintFormat(Env.getCtx, formatId, trx), formatId, language(lang), trx = trx)
            assertNoProblems(Vector(result))
            val sql = result.join.sql
            val table = result.join.pd.get.getTableName
            val closing = s" OFFSET 0) $table\n"
            Then("the legacy statement has no OFFSET")
            assert(!result.legacy.sql.contains("OFFSET"))
            if (!DB.isPostgreSQL) {
              And("no other database gets it")
              assert(!sql.contains("OFFSET"))
            } else {
              And("the join statement closes the derived table with it, after the GROUP BY and before the outer joins and ORDER BY")
              assert(sql.contains(closing), sql)
              assert("OFFSET".r.findAllMatchIn(sql).size == 1, sql)
              assert(sql.indexOf(closing) < sql.indexOf("\nLEFT OUTER JOIN "), sql)
              if (sql.contains(" GROUP BY ")) assert(sql.indexOf(" GROUP BY ") < sql.indexOf(closing), sql)
              if (sql.contains("\nORDER BY ")) assert(sql.indexOf(closing) < sql.indexOf("\nORDER BY "), sql)
              And("the tag and the line breaks of the statement are kept")
              assert(sql.startsWith(s"$Tag$formatId */ ") && sql.split("\n", -1).tail.head.startsWith("FROM (SELECT "))
              And("the conversion of the database port leaves it in the statement")
              val converted = DB.getDatabase.convertStatement(sql)
              assert(converted.contains(s" OFFSET 0) $table"), converted)
              And("the rows are the same without it")
              val without = sql.replace(" OFFSET 0)", ")")
              assert(without != sql)
              assert(multiset(rowsOf(sql)) == multiset(rowsOf(without)))
            }
          }
        }
      }
    }
  }

  Feature("A missing translation falls back to the base name on the join path") {
    info("The legacy path keeps dropping the row (List) or showing NULL (translated lookup)")

    /** C_Order print format with a List column (DocStatus, mandatory), a translated lookup (C_DocType_ID) and a sort */
    def fallbackFormat(trx: String): Int =
      insertFormat(trx, "RLJ Fallback", "C_Order", 0, Seq(
        Item("DocumentNo", seqNo = 10, sortNo = 10), Item("DocStatus", seqNo = 20), Item("C_DocType_ID", seqNo = 30)))

    def text(trx: String, sql: String, params: Object*): String = DB.getSQLValueStringEx(trx, sql, params: _*)

    /** (DocumentNo, display of the column) of every row, None where the cell is absent (NULL) */
    def pairs(run: Run, column: String): Vector[(Option[String], Option[String])] =
      cells(run.pd.get).map(row => (row.find(_.name == "DocumentNo").map(_.display), row.find(_.name == column).map(_.value)))

    lazy val DocStatusReference = MColumn.get(Env.getCtx, MColumn.getColumn_ID("C_Order", "DocStatus")).getAD_Reference_Value_ID
    val ListName = "SELECT %s FROM AD_Ref_List l LEFT OUTER JOIN AD_Ref_List_Trl t ON (t.AD_Ref_List_ID=l.AD_Ref_List_ID AND t.AD_Language='" + Spanish + "') " +
      "WHERE l.AD_Reference_ID=? AND l.Value='DR'"
    val Draft = "DocStatus"

    Scenario("A list value without translation keeps its row and shows the base name") {
      withTrx { trx =>
        Given("the order print format with a mandatory List column in es_MX, and the es_MX name of the draft status")
        val formatId = fallbackFormat(trx)
        val format = () => new MPrintFormat(Env.getCtx, formatId, trx)
        val before = compare(format, formatId, language(Spanish), trx = trx)
        assertNoProblems(Vector(before))
        val translated = text(trx, ListName.format("t.Name"), Integer.valueOf(DocStatusReference))
        val base = text(trx, ListName.format("l.Name"), Integer.valueOf(DocStatusReference))
        assert(translated != null && base != null && translated != base)
        val shown = pairs(before.legacy, Draft)
        val draftRows = shown.count(_._2.contains(translated))
        assert(draftRows > 0 && shown.forall(_._2.isDefined))
        When("the es_MX translation of the draft status is deleted inside the transaction")
        assert(DB.executeUpdateEx("DELETE FROM AD_Ref_List_Trl WHERE AD_Language=? AND AD_Ref_List_ID=" +
          "(SELECT AD_Ref_List_ID FROM AD_Ref_List WHERE AD_Reference_ID=? AND Value='DR')", Array[Object](Spanish, Integer.valueOf(DocStatusReference)), trx) == 1)
        val legacy = runEngine(format, language(Spanish), Some(false), trx = trx)
        val join = runEngine(format, language(Spanish), Some(true), trx = trx)
        Then("the join path still reports the rows, with the base name")
        assert(joinSqlProblems(formatId, join).isEmpty, joinSqlProblems(formatId, join).toString)
        assert(multiset(pairs(join, Draft)) == multiset(shown.map { case (document, status) => (document, if (status.contains(translated)) Some(base) else status) }))
        And("the legacy path drops them, as before")
        assert(multiset(pairs(legacy, Draft)) == multiset(shown.filterNot(_._2.contains(translated))))
        assert(legacy.pd.get.getRowCount == before.legacy.pd.get.getRowCount - draftRows)
        And("the translation is a LEFT OUTER JOIN, the list value is still an INNER JOIN and the display falls back to the list name")
        assert("""LEFT OUTER JOIN\s+AD_Ref_List_Trl """.r.findFirstIn(join.sql).isDefined, join.sql)
        assert("""INNER JOIN\s+AD_Ref_List_Trl """.r.findFirstIn(join.sql).isEmpty, join.sql)
        assert("""INNER JOIN\s+AD_Ref_List X\w+ ON""".r.findFirstIn(join.sql).isDefined, join.sql)
        assert("""NVL\((\w+)\.Name,X\1\.Name\) AS \1Name""".r.findFirstIn(join.sql).isDefined, join.sql)
        assert("""INNER JOIN\s+AD_Ref_List_Trl """.r.findFirstIn(legacy.sql).isDefined && !legacy.sql.contains("NVL(A.Name,XA.Name)"))
      }
    }

    Scenario("The access SQL of a role parses the join of the list translation and the report keeps the rows") {
      withTrx { trx =>
        Given("the order print format in es_MX, run by a non administrator role")
        val formatId = fallbackFormat(trx)
        val format = () => new MPrintFormat(Env.getCtx, formatId, trx)
        withRole(Some(GardenWorldUserRole)) {
          val result = compare(format, formatId, language(Spanish), trx = trx)
          assertNoProblems(Vector(result))
          Then("the statement of the report, up to the derived table alias, has the three tables for the role")
          val sql = result.join.sql
          val inner = sql.substring(sql.indexOf("\nFROM (") + "\nFROM (".length, sql.indexOf(s") ${result.join.pd.get.getTableName}\n"))
          val parser = new org.compiere.model.AccessSqlParser(inner.replace(" OFFSET 0", ""))
          assert(parser.parse() || parser.getNoSqlStatments > 0)
          val tables = parser.getTableInfo(parser.getMainSqlIndex).map(_.getTableName).toVector
          assert(Seq("C_Order", "AD_Ref_List", "AD_Ref_List_Trl").forall(tables.contains), tables.toString)
          And("the role gets the rows of the join path, which are at least the rows of the legacy path, when a translation is missing")
          assert(DB.executeUpdateEx("DELETE FROM AD_Ref_List_Trl WHERE AD_Language=? AND AD_Ref_List_ID=" +
            "(SELECT AD_Ref_List_ID FROM AD_Ref_List WHERE AD_Reference_ID=? AND Value='DR')", Array[Object](Spanish, Integer.valueOf(DocStatusReference)), trx) == 1)
          val legacy = runEngine(format, language(Spanish), Some(false), trx = trx)
          val join = runEngine(format, language(Spanish), Some(true), trx = trx)
          assert(join.pd.get.getRowCount >= legacy.pd.get.getRowCount)
        }
      }
    }

    Scenario("A grouped report groups by the list name it selects") {
      withTrx { trx =>
        Given("a report view with SUM(GrandTotal) and the List column DocStatus, in es_MX")
        val reportView = insertReportView(trx, "RLJ Fallback Group", "C_Order", Seq(("GrandTotal", "SUM(@)")))
        val formatId = insertFormat(trx, "RLJ Fallback Group", "C_Order", reportView, Seq(
          Item("DocStatus", seqNo = 10, sortNo = 10), Item("GrandTotal", seqNo = 20)))
        When("the statement is generated (the database rejects a List column in a grouped view with both strategies: the key column DocStatus is not grouped)")
        val join = runEngine(() => new MPrintFormat(Env.getCtx, formatId, trx), language(Spanish), Some(true), trx = trx)
        Then("the selected display and the grouped expression are the same fallback")
        assert(join.pd.isDefined, join.error.toString)
        val sql = join.sql
        val display = """NVL\((\w+)\.Name,X\1\.Name\)""".r.findFirstIn(sql)
        assert(display.isDefined, sql)
        assert(sql.contains(s"${display.get} AS "), sql)
        val groupBy = sql.substring(sql.indexOf(" GROUP BY ")).takeWhile(_ != '\n')
        assert(groupBy.contains(display.get), groupBy)
      }
    }

    Scenario("A translated lookup without translation shows the base value, a missing lookup row stays NULL") {
      withTrx { trx =>
        Given("the order print format in es_MX, whose C_DocType_ID is a translated lookup, and an order whose document type does not exist")
        assert(DB.executeUpdateEx("UPDATE C_Order SET C_DocType_ID=99999999 WHERE C_Order_ID=(SELECT MIN(C_Order_ID) FROM C_Order WHERE AD_Client_ID=?)",
          Array[Object](Integer.valueOf(Env.getAD_Client_ID(Env.getCtx))), trx) == 1)
        val formatId = fallbackFormat(trx)
        val format = () => new MPrintFormat(Env.getCtx, formatId, trx)
        val before = compare(format, formatId, language(Spanish), trx = trx)
        assertNoProblems(Vector(before))
        val shown = pairs(before.legacy, "C_DocType_ID")
        assert(shown.count(_._2.isEmpty) == 1, "the order of the missing document type is NULL")
        val docType = cells(before.legacy.pd.get).flatMap(_.find(_.name == "C_DocType_ID")).head
        val base = text(trx, "SELECT Name FROM C_DocType WHERE C_DocType_ID=?", Integer.valueOf(docType.key))
        assert(base != null && base != docType.value, s"${docType.value} / $base")
        val rows = shown.count(_._2.contains(docType.value))
        When(s"the es_MX translation of document type ${docType.key} is deleted inside the transaction")
        assert(DB.executeUpdateEx("DELETE FROM C_DocType_Trl WHERE C_DocType_ID=? AND AD_Language=?", Array[Object](Integer.valueOf(docType.key), Spanish), trx) == 1)
        val legacy = runEngine(format, language(Spanish), Some(false), trx = trx)
        val join = runEngine(format, language(Spanish), Some(true), trx = trx)
        Then("the join path shows the base name in those rows")
        assert(joinSqlProblems(formatId, join).isEmpty)
        assert(multiset(pairs(join, "C_DocType_ID")) == multiset(shown.map { case (document, name) => (document, if (name.contains(docType.value)) Some(base) else name) }))
        And("the legacy path shows NULL there, as before")
        assert(multiset(pairs(legacy, "C_DocType_ID")) == multiset(shown.map { case (document, name) => (document, if (name.contains(docType.value)) None else name) }))
        And("the order of the missing document type row is NULL on both paths")
        assert(pairs(join, "C_DocType_ID").count(_._2.isEmpty) == 1)
        assert(pairs(legacy, "C_DocType_ID").count(_._2.isEmpty) == 1 + rows)
        And("the guard checks the row of the lookup only and the display falls back to its column")
        assert("""CASE WHEN (\w+)\.C_DocType_ID IS NOT NULL THEN NVL\((\w+_Trl_\d+)\.Name,\1\.Name\) END""".r.findFirstIn(join.sql).isDefined, join.sql)
        assert("""AND \w+_Trl_\d+\.C_DocType_ID IS NOT NULL""".r.findFirstIn(join.sql).isEmpty)
        assert(legacy.sql.contains("C_DocType_Trl.Name"))
      }
    }
  }

  Feature("A report without sort columns has a deterministic order on the join path") {

    Scenario("A format without sort columns ends with ORDER BY on the single key column of its base table") {
      withTrx { trx =>
        Given("print formats of C_Order without sort columns (with lookups and without), a sorted one and a grouped one")
        val withLookups = insertFormat(trx, "RLJ Tie Lookups", "C_Order", 0, Seq(Item("DocumentNo", seqNo = 10), Item("C_BPartner_ID", seqNo = 20)))
        val plain = insertFormat(trx, "RLJ Tie Plain", "C_Order", 0, Seq(Item("DocumentNo", seqNo = 10), Item("GrandTotal", seqNo = 20)))
        val sorted = insertFormat(trx, "RLJ Tie Sorted", "C_Order", 0, Seq(Item("DocumentNo", seqNo = 10, sortNo = 10, isDesc = true), Item("C_BPartner_ID", seqNo = 20)))
        val groupedView = insertReportView(trx, "RLJ Tie Grouped", "C_Order", Seq(("GrandTotal", "SUM(@)")))
        val grouped = insertFormat(trx, "RLJ Tie Grouped", "C_Order", groupedView, Seq(Item("C_BPartner_ID", seqNo = 10), Item("GrandTotal", seqNo = 20)))
        assert(singleKey("C_Order").contains("C_Order_ID"))
        def compareFormat(formatId: Int, lang: String) = compare(() => new MPrintFormat(Env.getCtx, formatId, trx), formatId, language(lang), trx = trx)
        Seq(English, Spanish).foreach { lang =>
          When(s"they are generated in $lang by both strategies")
          val lookups = compareFormat(withLookups, lang)
          val flat = compareFormat(plain, lang)
          val ordered = compareFormat(sorted, lang)
          val summed = compareFormat(grouped, lang)
          assertNoProblems(Vector(lookups, flat, ordered, summed))
          Then("the statement with lookups ends with the key of the base table on its own line")
          assert(lookups.join.sql.endsWith("\nORDER BY C_Order.C_Order_ID"), lookups.join.sql)
          assert(lookups.join.sql.indexOf("ORDER BY") == lookups.join.sql.lastIndexOf("ORDER BY"))
          And("so does the statement without lookups, on the same line")
          assert(flat.join.sql.endsWith(" ORDER BY C_Order.C_Order_ID") && !flat.join.sql.contains("\n"), flat.join.sql)
          And("the rows come in the order of the key")
          val documents = cells(lookups.join.pd.get).flatMap(_.find(_.name == "DocumentNo")).map(_.value)
          val byKey = documentsByKey.filter(documents.toSet.contains)
          assert(documents.distinct.size == documents.size && documents == byKey, s"$documents / $byKey")
          And("a sorted format keeps its ORDER BY, without the key")
          assert(orderBy(ordered.join.sql) == orderBy(ordered.legacy.sql) && !orderBy(ordered.join.sql).contains("C_Order_ID"), ordered.join.sql)
          And("a grouped format gets nothing")
          assert(!summed.join.sql.contains("ORDER BY") && !summed.legacy.sql.contains("ORDER BY"), summed.join.sql)
          And("the legacy statements are as before: no ORDER BY without sort columns")
          assert(!lookups.legacy.sql.contains("ORDER BY") && !flat.legacy.sql.contains("ORDER BY"))
        }
      }
    }

    Scenario("A view without a single key column gets no ORDER BY, a table with one gets it, in every table based format") {
      Given("the print formats without sort columns and GROUP BY, in English and in es_MX")
      val results = (sweep(English) ++ sweep(Spanish)).filter(_.compared).filter(r => !r.legacy.sql.contains(" ORDER BY ") && !r.legacy.sql.contains(" GROUP BY "))
      assert(results.nonEmpty)
      Then("those whose base table has one key column end with it, the others have no ORDER BY")
      val (keyed, keyless) = results.partition(r => singleKey(r.join.pd.get.getTableName).isDefined)
      info(s"${keyed.size} statements with the key of the base table, ${keyless.size} without a single key column")
      assert(keyed.nonEmpty && keyless.nonEmpty)
      assert(keyed.forall(r => orderBy(r.join.sql) == tieBreak(r.legacy.pd.get).get), keyed.map(_.formatId).toString)
      assert(keyless.forall(r => !r.join.sql.contains("ORDER BY")), keyless.map(_.formatId).toString)
      assertNoProblems(results)
    }
  }

  /** DocumentNo of every order of the clients 0 and 11 by C_Order_ID */
  private def documentsByKey: Vector[String] = {
    val statement = DB.prepareStatement("SELECT DocumentNo FROM C_Order WHERE AD_Client_ID IN (0,11) ORDER BY C_Order_ID", null)
    val rs = statement.executeQuery()
    try Iterator.continually(rs).takeWhile(_.next()).map(_.getString(1)).toVector finally DB.close(rs, statement)
  }

  /** Every row of the statement as text, the statement is run outside of any transaction */
  private def rowsOf(sql: String): Vector[Vector[String]] = {
    val statement = DB.prepareStatement(sql, null)
    val rs = statement.executeQuery()
    try {
      val width = rs.getMetaData.getColumnCount
      Iterator.continually(rs).takeWhile(_.next()).map(row => (1 to width).map(i => String.valueOf(row.getString(i))).toVector).toVector
    } finally DB.close(rs, statement)
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

  /** Names that ReportLookupPlan.innerSelect gives to select items, through reflection: the plan is package private */
  private def innerNames(baseTable: String, outputNames: Seq[String]): Seq[String] = {
    val itemClass = Class.forName("org.compiere.print.ReportLookupItem")
    val planClass = Class.forName("org.compiere.print.ReportLookupPlan")
    val itemConstructor = itemClass.getDeclaredConstructor(classOf[String], classOf[String], classOf[String])
    itemConstructor.setAccessible(true)
    val planConstructor = planClass.getDeclaredConstructor(classOf[Language], classOf[String])
    planConstructor.setAccessible(true)
    val items = new java.util.ArrayList[Object]()
    outputNames.foreach(name => items.add(itemConstructor.newInstance(s"$baseTable.$name", null, name).asInstanceOf[Object]))
    val innerSelect = planClass.getDeclaredMethod("innerSelect", classOf[java.util.List[_]])
    innerSelect.setAccessible(true)
    innerSelect.invoke(planConstructor.newInstance(Language.getBaseLanguage, baseTable), items)
    val innerName = itemClass.getDeclaredField("innerName")
    innerName.setAccessible(true)
    (0 until items.size).map(i => innerName.get(items.get(i)).asInstanceOf[String])
  }
}
