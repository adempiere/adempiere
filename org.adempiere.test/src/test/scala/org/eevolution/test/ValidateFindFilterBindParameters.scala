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

import java.math.BigDecimal
import java.sql.Timestamp

import org.compiere.model.{FindCount, GridField, GridTab, GridTable, GridWindow, GridWindowVO, MQuery, MRole}
import org.compiere.util.{DB, DisplayType, Env}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters._

/** The filters of the Find dialogs return, through the bound MQuery path, the rows of the literal path.
  *
  * For the header tab of each window it builds the restrictions the way `Find`/`FindWindow` do (String as
  * `UPPER(column) LIKE` with the upper-cased value, dates as a range, numbers with `=` and `>=`, Yes/No, lists and
  * lookups with `=`, plus `IS NULL`/`IS NOT NULL` for nullable columns) from values of real rows, and compares:
  *  - the keys of `GridTab.query` with the MQuery (`getBoundWhereClause`),
  *  - the keys and the count of the literal statement (`getWhereClause(true)` with the tab where clause and the role
  *    access, executed through `DB`),
  *  - the count of `FindCount`, the bound count of the Find dialogs.
  *
  * It only reads: nothing is saved.
  */
class ValidateFindFilterBindParameters extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val transactionName: String = null
  private def context = Env.getCtx
  private var windowNumber = 5000

  /** Fields taken besides the selection columns, when the header tab has them */
  private val additionalColumns = Set("DateOrdered", "DateInvoiced", "DateAcct", "GrandTotal", "C_BPartner_ID", "DocStatus")
  private val windows = Seq(
    "Sales Order" -> 143, "Purchase Order" -> 181, "Invoice (Customer)" -> 167, "Invoice (Vendor)" -> 183,
    "Business Partner" -> 123, "Product" -> 140, "Payment" -> 195, "Shipment (Customer)" -> 169)

  /** One restriction of a window */
  private case class FilterCase(column: String, operator: String, value: String, build: () => MQuery)

  private case class Mismatch(windowName: String, filter: FilterCase, detail: String)

  private val testedByWindow = scala.collection.mutable.LinkedHashMap[String, Int]()
  private val mismatchesByWindow = scala.collection.mutable.LinkedHashMap[String, ArrayBuffer[Mismatch]]()

  private def privateText(table: GridTable, name: String): String = {
    val field = table.getClass.getDeclaredField(name)
    field.setAccessible(true)
    String.valueOf(field.get(table))
  }

  private def startOfDay(timestamp: Timestamp): Timestamp = {
    val calendar = java.util.Calendar.getInstance()
    calendar.setTime(timestamp)
    calendar.set(java.util.Calendar.HOUR_OF_DAY, 0)
    calendar.set(java.util.Calendar.MINUTE, 0)
    calendar.set(java.util.Calendar.SECOND, 0)
    calendar.set(java.util.Calendar.MILLISECOND, 0)
    new Timestamp(calendar.getTimeInMillis)
  }

  private def endOfDay(timestamp: Timestamp): Timestamp =
    new Timestamp(startOfDay(timestamp).getTime + 86400000L - 1L)

  private def restriction(tableName: String)(add: MQuery => Unit): () => MQuery = () => {
    val query = new MQuery(tableName)
    add(query)
    query
  }

  /** First, middle and last distinct values of a column in the rows of the tab */
  private def sampleValues(tab: GridTab, columnName: String): Vector[AnyRef] = {
    val distinct = (0 until tab.getRowCount).map(row => tab.getValue(row, columnName))
      .filter(value => value != null && value.toString.trim.nonEmpty).distinct.toVector
    if (distinct.size <= 3) distinct
    else Vector(distinct.head, distinct(distinct.size / 2), distinct.last)
  }

  private def isText(displayType: Int): Boolean = displayType == DisplayType.String || displayType == DisplayType.Text
  private def isNumber(displayType: Int): Boolean =
    Set(DisplayType.Amount, DisplayType.Number, DisplayType.Integer, DisplayType.Quantity, DisplayType.CostPrice).contains(displayType)
  private def isLookup(displayType: Int): Boolean =
    displayType == DisplayType.TableDir || displayType == DisplayType.Table || displayType == DisplayType.Search

  /** The restrictions of one field, with the values of the rows of the tab */
  private def casesOf(tab: GridTab, field: GridField): Seq[FilterCase] = {
    val tableName = tab.getTableName
    val columnName = field.getColumnName
    val displayType = field.getDisplayType
    val values = sampleValues(tab, columnName)
    val cases = ArrayBuffer[FilterCase]()
    if (isText(displayType) && values.nonEmpty) {
      val upper = values.map(_.toString.toUpperCase)
      val exact = upper.head
      val like = (value: String) => FilterCase(columnName, MQuery.LIKE, value,
        restriction(tableName)(_.addRestriction(s"UPPER($columnName)", MQuery.LIKE, value)))
      cases += like(exact.take(2) + "%")
      cases += like(exact + "%")
      if (exact.length >= 3) cases += like(exact.head + "%" + exact.last + "%")
      if (upper.size > 1) cases += like(upper.last.take(1) + "%")
      cases += FilterCase(columnName, MQuery.EQUAL, exact,
        restriction(tableName)(_.addRestriction(s"UPPER($columnName)", MQuery.EQUAL, exact)))
      cases += like("ZZQ%NOMATCH%")
    } else if (DisplayType.isDate(displayType) && values.nonEmpty) {
      val stamps = values.collect { case timestamp: Timestamp => timestamp }
      stamps.foreach { stamp =>
        cases += FilterCase(columnName, MQuery.BETWEEN, s"${startOfDay(stamp)} .. ${endOfDay(stamp)}",
          restriction(tableName)(_.addRangeRestriction(columnName, startOfDay(stamp), endOfDay(stamp))))
        cases += FilterCase(columnName, MQuery.GREATER_EQUAL, stamp.toString,
          restriction(tableName)(_.addRestriction(columnName, MQuery.GREATER_EQUAL, stamp)))
      }
      if (stamps.size > 1) {
        val (from, to) = (stamps.minBy(_.getTime), stamps.maxBy(_.getTime))
        cases += FilterCase(columnName, MQuery.BETWEEN, s"${startOfDay(from)} .. ${endOfDay(to)}",
          restriction(tableName)(_.addRangeRestriction(columnName, startOfDay(from), endOfDay(to))))
      }
      val future = Timestamp.valueOf("2999-01-01 00:00:00")
      cases += FilterCase(columnName, MQuery.BETWEEN, s"$future .. $future",
        restriction(tableName)(_.addRangeRestriction(columnName, future, future)))
    } else if (isNumber(displayType) && values.nonEmpty) {
      values.collect { case number: Number => new BigDecimal(number.toString) }.foreach { number =>
        cases += FilterCase(columnName, MQuery.EQUAL, number.toString,
          restriction(tableName)(_.addRestriction(columnName, MQuery.EQUAL, number)))
        cases += FilterCase(columnName, MQuery.GREATER_EQUAL, number.toString,
          restriction(tableName)(_.addRestriction(columnName, MQuery.GREATER_EQUAL, number)))
      }
      val none = new BigDecimal("-987654321")
      cases += FilterCase(columnName, MQuery.EQUAL, none.toString, restriction(tableName)(_.addRestriction(columnName, MQuery.EQUAL, none)))
    } else if (displayType == DisplayType.YesNo) {
      Seq(java.lang.Boolean.TRUE, java.lang.Boolean.FALSE).foreach { flag =>
        cases += FilterCase(columnName, MQuery.EQUAL, flag.toString,
          restriction(tableName)(_.addRestriction(columnName, MQuery.EQUAL, flag)))
      }
    } else if (displayType == DisplayType.List && values.nonEmpty) {
      values.map(_.toString).foreach { code =>
        cases += FilterCase(columnName, MQuery.EQUAL, code, restriction(tableName)(_.addRestriction(columnName, MQuery.EQUAL, code)))
      }
      cases += FilterCase(columnName, MQuery.EQUAL, "ZZZ", restriction(tableName)(_.addRestriction(columnName, MQuery.EQUAL, "ZZZ")))
    } else if (isLookup(displayType) && values.nonEmpty) {
      values.collect { case number: Number => Integer.valueOf(number.intValue) }.foreach { id =>
        cases += FilterCase(columnName, MQuery.EQUAL, id.toString,
          restriction(tableName)(_.addRestriction(columnName, MQuery.EQUAL, id)))
      }
      val none = Integer.valueOf(-987654)
      cases += FilterCase(columnName, MQuery.EQUAL, none.toString, restriction(tableName)(_.addRestriction(columnName, MQuery.EQUAL, none)))
    }
    if (!field.isMandatory(false) && (cases.nonEmpty || isLookup(displayType))) {
      cases += FilterCase(columnName, MQuery.NULL, "", restriction(tableName)(_.addRestriction(columnName, MQuery.NULL, null)))
      cases += FilterCase(columnName, MQuery.NOT_NULL, "", restriction(tableName)(_.addRestriction(columnName, MQuery.NOT_NULL, null)))
    }
    cases.toSeq
  }

  /** What each path returned for one restriction */
  private case class Outcome(keys: Vector[Int], count: Int, sql: String, parameters: Vector[Any])

  private def keysOf(tab: GridTab): Vector[Int] = {
    val keyColumn = tab.getKeyColumnName
    (0 until tab.getRowCount).map(row => tab.getValue(row, keyColumn).asInstanceOf[Number].intValue).sorted.toVector
  }

  private def boundOutcome(tab: GridTab, query: MQuery): Outcome = {
    tab.setQuery(query)
    tab.query(false)
    val table = tab.getTableModel
    val keys = keysOf(tab)
    Outcome(keys, keys.size, privateText(table, "m_SQL"), table.getSelectWhereParameters.asScala.toVector)
  }

  /** Literal statement: tab where clause, `getWhereClause(true)` and the role access, nothing bound */
  private def literalStatement(select: String, tab: GridTab, query: MQuery): String = {
    val tableName = tab.getTableName
    val tabWhere = Option(tab.getWhereExtended).map(_.trim).getOrElse("")
    val parsedWhere = if (tabWhere.isEmpty) "" else Env.parseContext(context, tab.getWindowNo, tabWhere, false)
    val conditions = Seq(parsedWhere, s"(${query.getWhereClause(true)})").filter(_.trim.nonEmpty)
    MRole.getDefault(context, false).addAccessSQL(
      s"$select FROM $tableName WHERE ${conditions.mkString(" AND ")}", tableName, MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO)
  }

  private def literalOutcome(tab: GridTab, query: MQuery): Outcome = {
    val keySql = literalStatement(s"SELECT ${tab.getTableName}.${tab.getKeyColumnName}", tab, query)
    val countSql = literalStatement("SELECT COUNT(*)", tab, query)
    val statement = DB.prepareStatement(keySql, transactionName)
    val keys = ArrayBuffer[Int]()
    try {
      val resultSet = statement.executeQuery()
      try while (resultSet.next()) keys += resultSet.getInt(1)
      finally resultSet.close()
    } finally statement.close()
    Outcome(keys.sorted.toVector, DB.getSQLValue(transactionName, countSql), keySql, Vector.empty)
  }

  private def findCountOutcome(tab: GridTab, query: MQuery): (Int, String, Vector[Any]) = {
    val statement = FindCount.getStatement(context, tab.getWindowNo, MRole.getDefault(context, false),
      tab.getTableName, tab.getWhereExtended, query)
    (FindCount.count(statement), statement.getSql, statement.getParameters.asScala.toVector)
  }

  /** Compares the three paths for one restriction; returns the description of the difference, if any */
  private def compare(tab: GridTab, filter: FilterCase): Option[String] = {
    val bound = boundOutcome(tab, filter.build())
    val literal = literalOutcome(tab, filter.build())
    val (findCount, findSql, findParameters) = findCountOutcome(tab, filter.build())
    val isSame = bound.keys == literal.keys && literal.count == literal.keys.size && findCount == literal.count
    if (isSame) None
    else Some(
      s"""bound rows=${bound.count} keys=${bound.keys.take(20)}
         |  bound SQL: ${bound.sql}
         |  bound parameters: ${bound.parameters}
         |literal rows=${literal.keys.size} count=${literal.count} keys=${literal.keys.take(20)}
         |  literal SQL: ${literal.sql}
         |  literal parameters: none
         |FindCount=$findCount
         |  FindCount SQL: $findSql
         |  FindCount parameters: $findParameters""".stripMargin)
  }

  private def sweep(windowName: String, windowId: Int): Unit = {
    windowNumber += 1
    val windowVO = GridWindowVO.create(context, windowNumber, windowId)
    if (windowVO == null) fail(s"The window $windowName ($windowId) cannot be created")
    val window = new GridWindow(windowVO)
    val mismatches = mismatchesByWindow.getOrElseUpdate(windowName, ArrayBuffer[Mismatch]())
    try {
      window.initTab(0)
      val tab = window.getTab(0)
      tab.setQuery(null)
      tab.query(false)
      info(s"$windowName: tab ${tab.getName} (${tab.getTableName}) has ${tab.getRowCount} rows")
      val fields = tab.getFields.toSeq.filter { field =>
        !field.isVirtualColumn && (field.isSelectionColumn || additionalColumns.contains(field.getColumnName))
      }
      val cases = fields.flatMap(field => casesOf(tab, field))
      // two restrictions together, as the Find dialog does when several fields are filled
      val pairs = cases.filter(filter => filter.operator != MQuery.BETWEEN)
        .groupBy(_.column).values.map(_.head).toSeq.sliding(2).collect { case Seq(first, second) =>
          FilterCase(s"${first.column} + ${second.column}", "AND", s"${first.value} | ${second.value}", () => {
            val query = first.build()
            val other = second.build()
            (0 until other.getRestrictionCount).foreach { index =>
              query.addRestriction(other.getColumnName(index), other.getOperator(index), other.getCode(index))
            }
            query
          })
        }.toSeq
      var tested = 0
      (cases ++ pairs).foreach { filter =>
        tested += 1
        val difference = try compare(tab, filter) catch {
          case exception: Throwable => Some(s"exception ${exception.getClass.getName}: ${exception.getMessage}")
        }
        difference.foreach { detail =>
          mismatches += Mismatch(windowName, filter, detail)
          println(s"MISMATCH window=$windowName column=${filter.column} operator=${filter.operator} value=${filter.value}\n$detail")
        }
      }
      testedByWindow(windowName) = testedByWindow.getOrElse(windowName, 0) + tested
      info(s"$windowName: $tested restrictions, ${mismatches.size} mismatches")
      assert(tested > 0, s"$windowName: no restrictions were built")
      if (mismatches.nonEmpty)
        fail(s"$windowName: ${mismatches.size} mismatches, first: ${mismatches.head.filter.column} ${mismatches.head.filter.operator} ${mismatches.head.filter.value}")
    } finally window.dispose()
  }

  Feature("The Find filters return the same rows through the bound and the literal path") {
    windows.foreach { case (windowName, windowId) =>
      Scenario(s"$windowName ($windowId): selection columns and main filters") {
        Given(s"the header tab of the window $windowName")
        When("every restriction runs through GridTab.query, the literal statement and FindCount")
        Then("the rows and the counts are the same")
        sweep(windowName, windowId)
      }
    }

    Scenario("Summary") {
      val header = f"${"Window"}%-22s ${"Restrictions"}%12s ${"Mismatches"}%11s"
      val rows = windows.map { case (windowName, _) =>
        f"$windowName%-22s ${testedByWindow.getOrElse(windowName, 0)}%12d ${mismatchesByWindow.get(windowName).map(_.size).getOrElse(0)}%11d"
      }
      val totalTested = testedByWindow.values.sum
      val totalMismatches = mismatchesByWindow.values.map(_.size).sum
      println((Seq(header) ++ rows :+ f"${"Total"}%-22s $totalTested%12d $totalMismatches%11d").mkString("\n"))
      assert(totalMismatches == 0, s"$totalMismatches mismatches in $totalTested restrictions")
    }
  }
}
