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

import org.adempiere.model.{MDocumentStatus, ZoomInfoFactory}
import org.compiere.model.{FindCount, GridTab, GridTable, GridWindow, GridWindowVO, MBPartner, MLookup, MQuery, MRole, PO, Query}
import org.compiere.print.{MPrintFormat, PrintData, PrintDataElement, ReportEngine}
import org.compiere.util.{DB, DisplayType, Env}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters._

/** The manual checklist of the bound MQuery (F1, T10b) as automated scenarios, one per item.
  *
  * Each scenario runs the logic the dialog runs, without a screen: the restriction the Find/FindWindow build, the
  * query of the zoom as `AEnv.actionZoom`/`MLookup.getZoom` build it, the source record lookup of the zoom across, the
  * report of the toolbar and the count of `initialQuery`. The result of the bound path must equal the literal path
  * (`getWhereClause`) and the value that plain SQL gives at runtime. The value the person saw in the manual test is
  * reported, and asserted too when the data still matches.
  *
  * It only reads: nothing is saved, and no process instance is created.
  */
class ValidateMQueryBindsManualChecklist extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val transactionName: String = null
  private def context = Env.getCtx
  private var windowNumber = 7000

  private val salesOrderWindowId = 143
  private val purchaseOrderWindowId = 181
  private val orderPrintFormatId = 1000003 // GardenWorld -> Order, C_Order

  private def check(condition: Boolean, message: => String = "assertion failed"): Unit =
    if (!condition) fail(message)

  private def privateText(table: GridTable, name: String): String = {
    val field = table.getClass.getDeclaredField(name)
    field.setAccessible(true)
    String.valueOf(field.get(table))
  }

  private def quoted(text: String): String = "'" + text.replace("'", "''") + "'"

  /** Role access of the plain SQL: the client of the context and the system client */
  private def clientAccess(alias: String = ""): String = s"${alias}AD_Client_ID IN (0, ${Env.getAD_Client_ID(context)})"

  private def dbCount(sql: String): Int = DB.getSQLValueEx(transactionName, sql)

  private def dbKeys(sql: String): Vector[Int] = {
    val statement = DB.prepareStatement(sql, transactionName)
    val keys = ArrayBuffer[Int]()
    try {
      val resultSet = statement.executeQuery()
      try while (resultSet.next()) keys += resultSet.getInt(1)
      finally resultSet.close()
    } finally statement.close()
    keys.sorted.toVector
  }

  /** What the person saw in the manual test; asserted only when the database still holds the same data */
  private def manualValue(label: String, expectedByDatabase: Int, seenManually: Int, actual: Int): Unit =
    if (expectedByDatabase == seenManually) {
      info(s"$label: manual test $seenManually, database $expectedByDatabase, bound $actual")
      check(actual == seenManually, s"$label: manual test $seenManually, bound $actual")
    } else info(s"$label: the data changed since the manual test ($seenManually), database now $expectedByDatabase, bound $actual")

  private def withWindow[T](windowId: Int)(body: GridWindow => T): T = {
    windowNumber += 1
    val windowVO = GridWindowVO.create(context, windowNumber, windowId)
    if (windowVO == null) fail(s"The window $windowId cannot be created")
    val window = new GridWindow(windowVO)
    try body(window) finally window.dispose()
  }

  private def firstTab(window: GridWindow): GridTab = {
    window.initTab(0)
    val tab = window.getTab(0)
    tab.setQuery(null)
    tab.query(false)
    tab
  }

  private def keysOf(tab: GridTab): Vector[Int] = {
    val keyColumn = tab.getKeyColumnName
    (0 until tab.getRowCount).map(row => tab.getValue(row, keyColumn).asInstanceOf[Number].intValue).sorted.toVector
  }

  /** The dialog statement `Find`/`FindWindow` build: UPPER(column) LIKE with the upper-cased value and a closing `%` */
  private def likeQuery(tableName: String, columnName: String, typed: String): () => MQuery = () => {
    val value = typed.toUpperCase
    val query = new MQuery(tableName)
    query.addRestriction(s"UPPER($columnName)", MQuery.LIKE, if (value.endsWith("%")) value else value + "%")
    query
  }

  /** What each path returned for one restriction of the Find dialog */
  private case class Found(boundKeys: Vector[Int], boundSql: String, boundParameters: Vector[Any],
                           literalKeys: Vector[Int], literalCount: Int,
                           findCount: Int, findSql: String, findParameters: Vector[Any])

  /** Literal statement: tab where clause, `getWhereClause(true)` and the role access, nothing bound */
  private def literalStatement(select: String, tab: GridTab, query: MQuery): String = {
    val tableName = tab.getTableName
    val tabWhere = Option(tab.getWhereExtended).map(_.trim).getOrElse("")
    val parsedWhere = if (tabWhere.isEmpty) "" else Env.parseContext(context, tab.getWindowNo, tabWhere, false)
    val conditions = Seq(parsedWhere, s"(${query.getWhereClause(true)})").filter(_.trim.nonEmpty)
    MRole.getDefault(context, false).addAccessSQL(
      s"$select FROM $tableName WHERE ${conditions.mkString(" AND ")}", tableName, MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO)
  }

  /** Runs the restriction through GridTab.query (bound), the literal statement and FindCount, and requires the same rows */
  private def find(tab: GridTab, build: () => MQuery): Found = {
    tab.setQuery(build())
    tab.query(false)
    val table = tab.getTableModel
    val boundKeys = keysOf(tab)
    val boundSql = privateText(table, "m_SQL")
    val boundParameters = table.getSelectWhereParameters.asScala.toVector

    val literalKeys = dbKeys(literalStatement(s"SELECT ${tab.getTableName}.${tab.getKeyColumnName}", tab, build()))
    val literalCount = dbCount(literalStatement("SELECT COUNT(*)", tab, build()))

    // as Find/FindWindow count: FindCount with the role, the context and the extended where of the tab
    val statement = FindCount.getStatement(context, tab.getWindowNo, MRole.getDefault(context, false),
      tab.getTableName, tab.getWhereExtended, build())
    val findCount = FindCount.count(statement)
    info(s"bound SQL: $boundSql")
    info(s"bound parameters: $boundParameters")
    info(s"FindCount SQL: ${statement.getSql} parameters ${statement.getParameters.asScala.toVector}")
    info(s"rows: bound ${boundKeys.size}, literal ${literalKeys.size}, count literal $literalCount, FindCount $findCount")
    check(boundKeys == literalKeys, s"GridTab.query keys $boundKeys, literal keys $literalKeys")
    check(literalCount == literalKeys.size, s"literal count $literalCount, literal rows ${literalKeys.size}")
    check(findCount == literalCount, s"FindCount $findCount, literal count $literalCount")
    Found(boundKeys, boundSql, boundParameters, literalKeys, literalCount, findCount, statement.getSql,
      statement.getParameters.asScala.toVector)
  }

  /** The restriction is bound: placeholders in the statement and one value each, none embedded as text */
  private def checkBound(found: Found, value: Any): Unit = {
    check(found.boundParameters.nonEmpty && found.boundParameters.exists(_.toString == value.toString),
      s"the value $value is not among the bound parameters ${found.boundParameters}")
    check(found.findParameters.exists(_.toString == value.toString),
      s"the value $value is not among the FindCount parameters ${found.findParameters}")
    check(found.findSql.count(_ == '?') == found.findParameters.size, s"FindCount placeholders: ${found.findSql}")
    check(!found.boundSql.contains(quoted(value.toString)), s"the value is embedded in ${found.boundSql}")
  }

  private def childTabIndex(window: GridWindow, tabName: String): Int =
    (1 until window.getTabCount).find(index => window.getTab(index).getName == tabName)
      .getOrElse(fail(s"No tab $tabName"))

  /** The zoom query of the lookup of a field, as `AEnv.actionZoom` builds it, and the window `MLookup.getZoom` chooses */
  private def lookupZoom(field: org.compiere.model.GridField, value: Object): (MQuery, Int) = {
    val lookup = field.getLookup
    check(lookup != null, s"the field ${field.getColumnName} has no lookup")
    check(!(lookup.isInstanceOf[MLookup] && lookup.getDisplayType == DisplayType.List), "a list lookup")
    var column = lookup.getColumnName
    val zoomQuery = new MQuery()
    if (column.indexOf(".") > 0) {
      zoomQuery.setZoomTableName(column.substring(0, column.indexOf(".")))
      column = column.substring(column.indexOf(".") + 1)
      zoomQuery.setZoomColumnName(column)
    } else {
      zoomQuery.setZoomColumnName(column)
      zoomQuery.setZoomTableName(column.substring(0, column.length - 3))
    }
    zoomQuery.setZoomValue(value)
    zoomQuery.addRestriction(column, MQuery.EQUAL, value)
    zoomQuery.setRecordCount(1)
    (zoomQuery, lookup.getZoom(zoomQuery))
  }

  /** Replays `AbstractADWindowPanel.initPanel` and `zoomToDetailTab`/`doZoomToDetail`, as `ValidateZoomToDetailTab` does */
  private def zoomToDetail(windowId: Int, query: MQuery, parentTable: String, parentKey: String, expectedParent: Int): Unit =
    withWindow(windowId) { window =>
      val headerTab = window.getTab(0)
      check(!query.getZoomTableName.equalsIgnoreCase(headerTab.getTableName), "the zoom table is the header")
      window.initTab(0)
      val header = new MQuery()
      header.addRestriction("1=2") // initPanel: the header query becomes 1=2
      header.setRecordCount(0)
      headerTab.setQuery(header)
      headerTab.query(false)
      check(headerTab.getRowCount == 0, s"the header query 1=2 returned ${headerTab.getRowCount} rows")

      val childIndex = (1 until window.getTabCount).find { index =>
        val tab = window.getTab(index)
        !tab.isSortTab && tab.getTableName.equalsIgnoreCase(query.getZoomTableName)
      }.getOrElse(fail(s"No tab of ${query.getZoomTableName}"))
      val childTab = window.getTab(childIndex)
      window.initTab(childIndex)

      val linkColumn = childTab.getLinkColumnName
      val parentId = FindCount.getLinkValue(linkColumn, childTab.getTableName, query)
      val literalParentId = DB.getSQLValue(transactionName,
        s"SELECT $linkColumn FROM ${childTab.getTableName} WHERE ${query.getWhereClause(false)}")
      val databaseParentId = DB.getSQLValue(transactionName,
        s"SELECT $linkColumn FROM ${childTab.getTableName} WHERE ${query.getZoomColumnName}=${query.getZoomValue}")
      info(s"link value: bound $parentId, literal $literalParentId, database $databaseParentId")
      check(literalParentId == parentId, s"literal link value $literalParentId, bound $parentId")
      check(databaseParentId == parentId, s"database link value $databaseParentId, bound $parentId")
      check(parentId == expectedParent, s"getLinkValue($linkColumn, ${childTab.getTableName}) = $parentId, expected $expectedParent")

      val parentTab = window.getTab(0)
      val parentQuery = new MQuery(parentTab.getAD_Table_ID)
      parentQuery.addRestriction(linkColumn, "=", Integer.valueOf(parentId))
      parentTab.setQuery(parentQuery)
      parentTab.query(false)
      check(parentTab.getTableName == parentTable && parentTab.getRowCount == 1, s"parent rows ${parentTab.getRowCount}")
      parentTab.setCurrentRow(0)
      check(parentTab.getValue(0, parentKey).asInstanceOf[Number].intValue == parentId, "the current parent row is another record")

      val target = new MQuery(childTab.getAD_Table_ID)
      target.addRestriction(linkColumn, "=", Integer.valueOf(parentId))
      childTab.setQuery(target)
      childTab.query(false, 0, 0)
      val table = childTab.getTableModel
      val keys = (0 until table.getRowCount).map(table.getKeyID)
      info(s"child SQL: ${privateText(table, "m_SQL")}")
      info(s"child parameters: ${table.getSelectWhereParameters.asScala.toVector}")
      info(s"child rows: $keys")
      val databaseKeys = dbKeys(s"SELECT ${childTab.getKeyColumnName} FROM ${childTab.getTableName} WHERE $linkColumn=$parentId")
      check(keys.sorted == databaseKeys.filter(keys.contains(_)).sorted && keys.size == databaseKeys.size,
        s"child rows ${keys.sorted}, database rows $databaseKeys")
      check(keys.contains(query.getZoomValue.asInstanceOf[Integer].intValue), s"rows $keys do not contain ${query.getZoomValue}")
    }

  private def printContent(printData: PrintData): Vector[Vector[String]] =
    (0 until printData.getRowCount).map { row =>
      printData.setRowIndex(row)
      (0 until printData.getNodeCount).map { index =>
        printData.getNode(index) match {
          case element: PrintDataElement => s"${element.getColumnName}|${element.getValueKey}|${element.getValue}"
          case other => "#" + other
        }
      }.toVector
    }.toVector

  Feature("Find") {

    Scenario("Find Business Partner Name with % (%Tree% gives 1, %farm% gives 2)") {
      Given("the Business Partner window and the Name field of the Find dialog")
      withWindow(123) { window =>
        val tab = firstTab(window)
        Seq(("%Tree%", 1), ("%farm%", 2)).foreach { case (typed, seenManually) =>
          When(s"Name $typed is typed, as Find/FindWindow build UPPER(Name) LIKE")
          val found = find(tab, likeQuery("C_BPartner", "Name", typed))
          val expected = dbCount(s"SELECT COUNT(*) FROM C_BPartner WHERE UPPER(Name) LIKE ${quoted(typed.toUpperCase)} AND ${clientAccess()}")
          Then(s"GridTab.query and FindCount return $expected, as the database does")
          check(found.boundKeys.size == expected, s"$typed: GridTab.query ${found.boundKeys.size}, database $expected")
          check(found.findCount == expected, s"$typed: FindCount ${found.findCount}, database $expected")
          checkBound(found, typed.toUpperCase)
          manualValue(s"Name $typed", expected, seenManually, found.boundKeys.size)
        }
      }
    }

    Scenario("Find Sales Order DocumentNo 80% gives 3") {
      Given("the Sales Order window")
      withWindow(salesOrderWindowId) { window =>
        val tab = firstTab(window)
        When("DocumentNo 80% is typed")
        val found = find(tab, likeQuery("C_Order", "DocumentNo", "80%"))
        val expected = dbCount(s"SELECT COUNT(*) FROM C_Order WHERE IsSOTrx='Y' AND UPPER(DocumentNo) LIKE '80%' AND ${clientAccess()}")
        Then(s"the rows are the $expected of the database")
        check(found.boundKeys.size == expected, s"GridTab.query ${found.boundKeys.size}, database $expected")
        check(found.findCount == expected, s"FindCount ${found.findCount}, database $expected")
        check(found.boundKeys == dbKeys(s"SELECT C_Order_ID FROM C_Order WHERE IsSOTrx='Y' AND UPPER(DocumentNo) LIKE '80%' AND ${clientAccess()}"),
          "the keys differ from the database")
        checkBound(found, "80%")
        manualValue("DocumentNo 80%", expected, 3, found.boundKeys.size)
      }
    }

    Scenario("Find Sales Order date range (DateOrdered from/to)") {
      Given("the Sales Orders whose DocumentNo starts with 80")
      withWindow(salesOrderWindowId) { window =>
        val tab = firstTab(window)
        val range = DB.getSQLValueTSEx(transactionName,
          s"SELECT MIN(DateOrdered) FROM C_Order WHERE IsSOTrx='Y' AND DocumentNo LIKE '80%' AND ${clientAccess()}")
        val rangeEnd = DB.getSQLValueTSEx(transactionName,
          s"SELECT MAX(DateOrdered) FROM C_Order WHERE IsSOTrx='Y' AND DocumentNo LIKE '80%' AND ${clientAccess()}")
        check(range != null && rangeEnd != null, "no sales orders 80%")
        // the date editors give midnight; the range restriction is how FindWindow adds it (both values)
        val from = Timestamp.valueOf(range.toString.substring(0, 10) + " 00:00:00")
        val to = Timestamp.valueOf(rangeEnd.toString.substring(0, 10) + " 00:00:00")
        def keysBetween(condition: String): Vector[Int] =
          dbKeys(s"SELECT C_Order_ID FROM C_Order WHERE IsSOTrx='Y' AND $condition AND ${clientAccess()}")

        When(s"DateOrdered from $from to $to is entered, as a range restriction")
        val both = find(tab, () => {
          val query = new MQuery("C_Order")
          query.addRangeRestriction("DateOrdered", from, to, "DateOrdered", from.toString, to.toString)
          query
        })
        val expectedBoth = keysBetween(s"DateOrdered BETWEEN '$from' AND '$to'")
        Then(s"the rows are the ${expectedBoth.size} of the database")
        check(both.boundKeys == expectedBoth, s"range rows ${both.boundKeys}, database $expectedBoth")
        check(both.findCount == expectedBoth.size, s"FindCount ${both.findCount}, database ${expectedBoth.size}")
        check(both.boundParameters.size == 2 && both.findParameters.size == 2, s"two bound dates expected: ${both.boundParameters}")

        And("only the first value gives >=, as FindWindow case 2")
        val fromOnly = find(tab, () => {
          val query = new MQuery("C_Order")
          query.addRestriction("DateOrdered", MQuery.GREATER_EQUAL, from)
          query
        })
        check(fromOnly.boundKeys == keysBetween(s"DateOrdered >= '$from'"), s"from-only rows ${fromOnly.boundKeys}")

        And("only the second value gives <=, as FindWindow case 3")
        val toOnly = find(tab, () => {
          val query = new MQuery("C_Order")
          query.addRestriction("DateOrdered", MQuery.LESS_EQUAL, to)
          query
        })
        check(toOnly.boundKeys == keysBetween(s"DateOrdered <= '$to'"), s"to-only rows ${toOnly.boundKeys}")
        info(s"range $from .. $to: ${expectedBoth.size} rows, from only ${fromOnly.boundKeys.size}, to only ${toOnly.boundKeys.size}")
      }
    }

    Scenario("Find with an empty result (DocumentNo 99999 gives 0)") {
      Given("the Sales Order window")
      withWindow(salesOrderWindowId) { window =>
        val tab = firstTab(window)
        When("DocumentNo 99999 is typed, which the dialog turns into 99999%")
        val found = find(tab, likeQuery("C_Order", "DocumentNo", "99999"))
        val expected = dbCount(s"SELECT COUNT(*) FROM C_Order WHERE IsSOTrx='Y' AND UPPER(DocumentNo) LIKE '99999%' AND ${clientAccess()}")
        Then("the count is 0, there is no exception and the tab has no rows")
        check(found.findCount == 0 && found.literalCount == 0 && expected == 0, s"FindCount ${found.findCount}, database $expected")
        check(found.boundKeys.isEmpty && tab.getRowCount == 0, s"rows ${tab.getRowCount}")
        checkBound(found, "99999%")
        manualValue("DocumentNo 99999", expected, 0, found.boundKeys.size)
      }
    }
  }

  Feature("Child tabs") {

    Scenario("Child tabs switching between orders (Order Line shows the lines of the current order)") {
      Given("the Sales Order window and the child tab Order Line")
      withWindow(salesOrderWindowId) { window =>
        val headerTab = firstTab(window)
        val childIndex = childTabIndex(window, "Order Line")
        window.initTab(childIndex)
        val lineTab = window.getTab(childIndex)
        check(lineTab.getTableName == "C_OrderLine" && lineTab.getLinkColumnName == "C_Order_ID",
          s"${lineTab.getTableName} linked by ${lineTab.getLinkColumnName}")

        val orders = dbKeys(s"SELECT C_Order_ID FROM C_Order WHERE IsSOTrx='Y' AND DocumentNo IN ('80002', '80001') AND ${clientAccess()}")
        val byDocumentNo = orders.map(id => id -> DB.getSQLValueString(transactionName, "SELECT DocumentNo FROM C_Order WHERE C_Order_ID=" + id)).toMap
        val ordersByLines = Seq("80002", "80001").flatMap(documentNo => byDocumentNo.find(_._2 == documentNo).map(_._1))
        check(ordersByLines.size == 2, s"orders 80002 and 80001 not found: $byDocumentNo")
        val lineCounts = ordersByLines.map(id => dbCount(s"SELECT COUNT(*) FROM C_OrderLine WHERE C_Order_ID=$id"))
        check(lineCounts.distinct.size == 2, s"the orders have the same number of lines: $lineCounts")

        val statements = ArrayBuffer[String]()
        ordersByLines.zip(lineCounts).foreach { case (orderId, expected) =>
          When(s"the order ${byDocumentNo(orderId)} ($orderId) is made current and the child tab is queried")
          headerTab.setQuery(MQuery.getEqualQuery("C_Order_ID", Integer.valueOf(orderId)))
          headerTab.query(false)
          check(headerTab.getRowCount == 1, s"header rows ${headerTab.getRowCount}")
          headerTab.setCurrentRow(0)
          lineTab.setQuery(null)
          lineTab.query(false)
          val table = lineTab.getTableModel
          val sql = privateText(table, "m_SQL")
          val parameters = table.getSelectWhereParameters.asScala.toVector
          info(s"child SQL: $sql")
          info(s"child parameters: $parameters")
          Then(s"the lines are the $expected of the database and the link is bound once")
          check(lineTab.getRowCount == expected, s"Order Line rows ${lineTab.getRowCount}, database $expected")
          check("C_OrderLine\\.C_Order_ID=\\?".r.findAllIn(sql).size == 1, s"the link is not bound once: $sql")
          check(parameters.headOption.exists(_.toString == orderId.toString), s"the first parameter is not the parent $orderId: $parameters")
          check(!sql.contains(s"C_Order_ID=$orderId"), s"the parent id is embedded in $sql")
          statements += sql
        }
        And("the statement text is the same for both orders, so the statement cache is reused")
        check(statements.distinct.size == 1, s"the child statements differ: ${statements.distinct}")
        manualValue("lines of 80002", lineCounts.head, 3, lineCounts.head)
        manualValue("lines of 80001", lineCounts(1), 1, lineCounts(1))
      }
    }
  }

  Feature("Zoom") {

    Scenario("Zoom from a Purchase Order header field (C_BPartner_ID of PO 800000 opens the Business Partner window)") {
      Given("the C_BPartner_ID field of the Purchase Order window and the partner of PO 800000")
      val partnerId = DB.getSQLValueEx(transactionName, s"SELECT C_BPartner_ID FROM C_Order WHERE IsSOTrx='N' AND DocumentNo='800000' AND ${clientAccess()}")
      check(partnerId > 0, "PO 800000 not found")
      val expectedWindow = DB.getSQLValueEx(transactionName, "SELECT AD_Window_ID FROM AD_Table WHERE TableName='C_BPartner'")
      withWindow(purchaseOrderWindowId) { window =>
        (0 until window.getTabCount).foreach(window.initTab)
        val field = window.getTab(0).getField("C_BPartner_ID")
        When("AEnv.actionZoom builds the query and MLookup.getZoom chooses the window")
        val (zoomQuery, zoomWindowId) = lookupZoom(field, Integer.valueOf(partnerId))
        val bound = zoomQuery.getBoundWhereClause(false)
        info(s"zoom query: bound ${bound.getSql} ${bound.getParameters.asScala.toVector}; literal ${zoomQuery.getWhereClause(false)}")
        Then(s"the window is $expectedWindow (Business Partner)")
        check(zoomWindowId == expectedWindow, s"the zoom opens the window $zoomWindowId, expected $expectedWindow")
        check(!bound.getParameters.isEmpty && bound.getSql.contains("?"), s"the zoom query is not bound: $bound")

        And("the target tab, queried with the bound MQuery, returns that partner")
        withWindow(zoomWindowId) { target =>
          target.initTab(0)
          val tab = target.getTab(0)
          tab.setQuery(zoomQuery)
          tab.query(false)
          val table = tab.getTableModel
          info(s"target SQL: ${privateText(table, "m_SQL")}")
          info(s"target parameters: ${table.getSelectWhereParameters.asScala.toVector}")
          val keys = keysOf(tab)
          val literalKeys = dbKeys(s"SELECT C_BPartner_ID FROM C_BPartner WHERE ${zoomQuery.getWhereClause(false)} AND ${clientAccess()}")
          check(keys == Vector(partnerId), s"target rows $keys, expected Vector($partnerId)")
          check(keys == literalKeys, s"bound rows $keys, literal rows $literalKeys")
          check(privateText(table, "m_SQL").contains("?") && table.getSelectWhereParameters.asScala.exists(_.toString == partnerId.toString),
            "the target query is not bound")
        }
      }
    }

    Scenario("Zoom from a child tab (vendor invoice 10000003 line, field C_OrderLine_ID opens the Purchase Order on the PO line)") {
      Given("a line of the vendor invoice 10000003 that points at a PO line")
      val lines = DB.getSQLValueEx(transactionName,
        s"""SELECT MIN(il.C_InvoiceLine_ID) FROM C_InvoiceLine il INNER JOIN C_Invoice i ON i.C_Invoice_ID=il.C_Invoice_ID
           |WHERE i.IsSOTrx='N' AND i.DocumentNo='10000003' AND il.C_OrderLine_ID IS NOT NULL AND ${clientAccess("i.")}""".stripMargin)
      check(lines > 0, "vendor invoice 10000003 has no line with a PO line")
      val orderLineId = DB.getSQLValueEx(transactionName, s"SELECT C_OrderLine_ID FROM C_InvoiceLine WHERE C_InvoiceLine_ID=$lines")
      val orderId = DB.getSQLValueEx(transactionName, s"SELECT C_Order_ID FROM C_OrderLine WHERE C_OrderLine_ID=$orderLineId")
      val expectedWindow = DB.getSQLValueEx(transactionName, "SELECT PO_Window_ID FROM AD_Table WHERE TableName='C_OrderLine'")
      manualValue("PO line of the invoice line", orderLineId, 105, orderLineId)
      withWindow(183) { invoiceWindow =>
        (0 until invoiceWindow.getTabCount).foreach(invoiceWindow.initTab)
        val lineTab = (0 until invoiceWindow.getTabCount).map(invoiceWindow.getTab).find(_.getTableName == "C_InvoiceLine").get
        When("AEnv.actionZoom builds the query from the field and MLookup.getZoom chooses the window")
        val (zoomQuery, zoomWindowId) = lookupZoom(lineTab.getField("C_OrderLine_ID"), Integer.valueOf(orderLineId))
        info(s"zoom window $zoomWindowId (expected $expectedWindow), zoom table ${zoomQuery.getZoomTableName}, column ${zoomQuery.getZoomColumnName}, value ${zoomQuery.getZoomValue}")
        check(zoomWindowId == expectedWindow, s"the zoom opens the window $zoomWindowId, expected $expectedWindow")
        check(zoomWindowId == purchaseOrderWindowId, s"the window is not the Purchase Order window: $zoomWindowId")
        Then("the Purchase Order window opens on the header of that PO line with the line in the child tab")
        zoomToDetail(zoomWindowId, zoomQuery, "C_Order", "C_Order_ID", orderId)
      }
    }

    Scenario("Zoom across (Business Partner 114 Tree Farm to the Purchase Orders)") {
      Given("the Business Partner 114")
      val partnerId = 114
      val tableName = "C_BPartner"
      val sourceQuery = MQuery.getEqualQuery("C_BPartner_ID", Integer.valueOf(partnerId))

      When("the source record is found as WZoomAcross/AZoomAcross do, with Query.setQueryParameters")
      val bound = sourceQuery.getBoundWhereClause(false)
      val source = new Query(context, tableName, bound.getSql, null).setQueryParameters(bound.getParameters).first[PO]()
      val literal = new Query(context, tableName, sourceQuery.getWhereClause, null).first[PO]()
      info(s"source: ${bound.getSql} ${bound.getParameters.asScala.toVector}; literal ${sourceQuery.getWhereClause}")
      check(source != null && literal != null && source.get_ID == literal.get_ID && source.get_ID == partnerId, s"source bound $source, literal $literal")

      And("the zoom targets are retrieved; GenericZoomProvider counts stay raw by design")
      val zoomInfo = ZoomInfoFactory.retrieveZoomInfos(source, 123).asScala.find(_.windowId == purchaseOrderWindowId)
        .getOrElse(fail("no zoom target in the Purchase Order window"))
      val rawQuery = zoomInfo.query.getBoundWhereClause(false)
      info(s"zoom target ${zoomInfo.destinationDisplay} (#${zoomInfo.query.getRecordCount}): ${rawQuery.getSql} ${rawQuery.getParameters.asScala.toVector}")
      check(rawQuery.getParameters.isEmpty && rawQuery.getSql == zoomInfo.query.getWhereClause(false), "the generic zoom provider restriction is not raw")

      Then("the target window query, bound, finds the purchase orders of the partner")
      val expectedKeys = dbKeys(s"SELECT C_Order_ID FROM C_Order WHERE IsSOTrx='N' AND C_BPartner_ID=$partnerId AND ${clientAccess()}")
      withWindow(zoomInfo.windowId) { window =>
        window.initTab(0)
        val tab = window.getTab(0)
        tab.setQuery(zoomInfo.query)
        tab.query(false)
        val table = tab.getTableModel
        info(s"window SQL: ${privateText(table, "m_SQL")} ${table.getSelectWhereParameters.asScala.toVector}")
        val keys = keysOf(tab)
        check(keys == expectedKeys, s"window rows $keys, database $expectedKeys")
        val literalCount = dbCount(literalStatement("SELECT COUNT(*)", tab, zoomInfo.query))
        check(literalCount == keys.size, s"literal count $literalCount, window rows ${keys.size}")
        check(zoomInfo.query.getRecordCount >= keys.size, s"the zoom count ${zoomInfo.query.getRecordCount} is below the rows ${keys.size}")
        manualValue("purchase orders of Tree Farm", expectedKeys.size, 2, keys.size)
      }
    }
  }

  Feature("Report") {

    Scenario("Report with parameters (order print format with the MQuery C_Order_ID of PO 800000)") {
      Given("the order print format and the restriction the toolbar report adds for the current record")
      val orderId = DB.getSQLValueEx(transactionName, s"SELECT C_Order_ID FROM C_Order WHERE IsSOTrx='N' AND DocumentNo='800000' AND ${clientAccess()}")
      check(orderId > 0, "PO 800000 not found")
      val format = MPrintFormat.get(context, orderPrintFormatId, true)
      def query: MQuery = {
        val result = new MQuery("C_Order")
        result.addRestriction("C_Order_ID", MQuery.EQUAL, Integer.valueOf(orderId), "Order", "800000")
        result
      }
      val literalQuery = new MQuery("C_Order")
      literalQuery.addRestriction(query.getWhereClause(true))

      When("the ReportEngine loads the print data (DataEngine), without a process instance")
      val bound = new ReportEngine(context, format, query, null.asInstanceOf[org.compiere.model.PrintInfo]).getPrintData
      val literal = new ReportEngine(context, format, literalQuery, null.asInstanceOf[org.compiere.model.PrintInfo]).getPrintData
      val expected = dbCount(s"SELECT COUNT(*) FROM C_Order WHERE C_Order_ID=$orderId")
      info(s"bound SQL: ${bound.getSQL}")
      info(s"bound parameters: ${bound.getSQLParameters.asScala.toVector}")
      info(s"literal SQL parameters: ${literal.getSQLParameters.asScala.toVector}")

      Then("the rows equal the database count and the literal rows, and the value is a bind parameter")
      check(bound.getRowCount == expected, s"bound rows ${bound.getRowCount}, database $expected")
      check(literal.getRowCount == expected, s"literal rows ${literal.getRowCount}, database $expected")
      check(printContent(bound) == printContent(literal), "the cells of the bound and the literal report differ")
      check(bound.getSQLParameters.asScala.nonEmpty, "PrintData.getSQLParameters is empty")
      check(bound.getSQLParameters.asScala.exists(_.toString == orderId.toString), s"the order $orderId is not among ${bound.getSQLParameters}")
      check(bound.getSQL.contains("?") && bound.getSQL.count(_ == '?') == bound.getSQLParameters.size, s"the SQL does not carry the placeholders: ${bound.getSQL}")
      check(literal.getSQLParameters.isEmpty, "the literal report has parameters")
      manualValue("rows of the report", expected, 1, bound.getRowCount)
    }
  }

  Feature("initialQuery") {

    Scenario("initialQuery of the dashboard Pending Sales Orders") {
      Given("the document status Pending Sales Orders, whose indicator opens the Sales Order window with its restriction")
      // the indicators of the dashboard panel (WDocumentStatusPanel), for the user and role of the context
      val status = MDocumentStatus.getDocumentStatusIndicators(context, Env.getAD_User_ID(context), Env.getAD_Role_ID(context))
        .find(_.getName == "Pending Sales Orders").getOrElse(fail("the dashboard has no Pending Sales Orders indicator"))
      val tableName = org.compiere.model.MTable.getTableName(context, status.getAD_Table_ID)
      check(tableName == "C_Order" && status.getAD_Window_ID == salesOrderWindowId, s"$tableName window ${status.getAD_Window_ID}")
      def dashboardQuery: MQuery = {
        val result = new MQuery(status.getAD_Table_ID)
        result.addRestriction(MDocumentStatus.getWhereClause(status))
        result
      }
      val databaseWhere = s"AD_Client_ID=${status.getAD_Client_ID} AND IsSOTrx='Y' AND Processed='N'"
      val expected = dbCount(s"SELECT COUNT(*) FROM C_Order WHERE $databaseWhere")
      val expectedKeys = dbKeys(s"SELECT C_Order_ID FROM C_Order WHERE $databaseWhere AND ${clientAccess()}")
      info(s"dashboard where: ${MDocumentStatus.getWhereClause(status)}; counter ${MDocumentStatus.evaluate(status)}; database $expected")

      withWindow(salesOrderWindowId) { window =>
        window.initTab(0)
        val tab = window.getTab(0)
        val whereExtended = Env.parseContext(context, windowNumber, tab.getWhereExtended, false)

        When("initialQuery counts with FindCount.getStatement and DB.getSQLValueQuery, with whereExtended")
        val query = dashboardQuery
        val statement = FindCount.getStatement(null, windowNumber, null, tab.getTableName, whereExtended, query)
        val boundCount = DB.getSQLValueQuery(transactionName, statement.getSql, statement.getParameters)
        val literalCount = DB.getSQLValue(transactionName,
          s"SELECT COUNT(*) FROM ${tab.getTableName} WHERE ${if (whereExtended.nonEmpty) whereExtended + " AND " else ""}${query.getWhereClause}")
        info(s"count SQL: ${statement.getSql} ${statement.getParameters.asScala.toVector}")
        Then("the bound count equals the literal count and the database")
        check(boundCount == literalCount, s"bound $boundCount, literal $literalCount")
        check(boundCount == expected, s"bound $boundCount, database $expected")
        check(statement.getSql.contains(query.getBoundWhereClause(false).getSql), "the restriction is not in the count statement")
        manualValue("Pending Sales Orders", expected, 1, boundCount)

        And("without a query, the count is the one of the tab")
        val tabStatement = FindCount.getStatement(null, windowNumber, null, tab.getTableName, whereExtended, null)
        val tabCount = DB.getSQLValueQuery(transactionName, tabStatement.getSql, tabStatement.getParameters)
        check(tabCount == dbCount(s"SELECT COUNT(*) FROM C_Order WHERE IsSOTrx='Y'"), s"tab count $tabCount")

        And("GridTab.query with that MQuery returns the same rows")
        val found = find(tab, () => dashboardQuery)
        check(found.boundKeys == expectedKeys, s"rows ${found.boundKeys}, database $expectedKeys")
        check(found.boundKeys.size == boundCount, s"rows ${found.boundKeys.size}, count $boundCount")
      }
    }
  }
}
