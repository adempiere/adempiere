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
import java.text.SimpleDateFormat
import java.util.Collections

import org.compiere.model.{GridTab, GridTable, GridWindow, GridWindowVO, MQuery, MRole}
import org.compiere.util.{DB, Env}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.jdk.CollectionConverters._

/** A window tab runs the MQuery restrictions of Find, zoom and the link between tabs as bind parameters
  * (`?` and ordered values) and returns the rows that the literal statement returned.
  *
  * The reference is the same restriction written as a raw text restriction (`getWhereClause(true)` of the original
  * query): a restriction without values to bind, which is the literal path of before. Both run through the same
  * `GridTab.query`. The select SQL and the parameters of the `GridTable` are read after the query.
  */
class ValidateWindowBindParameters extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val SalesOrderWindow = 143
  private val BusinessPartnerWindow = 123
  private val ProductWindow = 140
  private var windowNumber = 3000

  private def check(condition: Boolean, message: => String = "assertion failed"): Unit =
    if (!condition) fail(message)

  private def openWindow(windowId: Int): GridWindow = {
    windowNumber += 1
    val windowVO = GridWindowVO.create(Env.getCtx, windowNumber, windowId)
    if (windowVO == null)
      fail(s"The window $windowId cannot be created")
    new GridWindow(windowVO)
  }

  private def openTab(window: GridWindow, tabNo: Int): GridTab = {
    window.initTab(tabNo)
    window.getTab(tabNo)
  }

  private def tabOf(window: GridWindow, tableName: String): Int =
    (0 until window.getTabCount).find(tabNo => window.getTab(tabNo).getTableName == tableName)
      .getOrElse(fail(s"No tab of $tableName"))

  private def placeholders(sql: String): Int = sql.count(_ == '?')

  private def privateText(table: GridTable, name: String): String = {
    val field = table.getClass.getDeclaredField(name)
    field.setAccessible(true)
    String.valueOf(field.get(table))
  }

  /** What one query of a tab left in its table: the rows (sorted keys), the statements and the values */
  private case class Outcome(keys: Vector[Int], selectSql: String, countSql: String, parameters: Vector[Any])

  private def keysOf(tab: GridTab): Vector[Int] = {
    val keyColumn = tab.getKeyColumnName
    (0 until tab.getRowCount).map(row => tab.getValue(row, keyColumn).asInstanceOf[Number].intValue).sorted.toVector
  }

  private def execute(tab: GridTab, query: MQuery): Outcome = {
    tab.setQuery(query)
    tab.query(false)
    val table = tab.getTableModel
    Outcome(keysOf(tab), privateText(table, "m_SQL"), privateText(table, "m_SQL_Count"),
      table.getSelectWhereParameters.asScala.toVector)
  }

  /** The restriction as raw text: nothing to bind */
  private def literalOf(tab: GridTab, query: MQuery): MQuery = {
    val literal = new MQuery(tab.getTableName)
    literal.addRestriction(query.getWhereClause(true))
    literal
  }

  /** Runs the restriction literal and bound, requires the same rows and returns the bound outcome */
  private def assertSameRows(tab: GridTab, label: String, requiresRows: Boolean, original: () => MQuery): Outcome = {
    val literal = execute(tab, literalOf(tab, original()))
    val bound = execute(tab, original())
    // the literal restriction binds nothing; a detail tab still binds its link value, ahead of the restriction
    check(literal.parameters.size == (if (tab.isDetail) 1 else 0), s"$label: the literal reference has parameters ${literal.parameters}")
    check(!requiresRows || literal.keys.nonEmpty, s"$label: the reference returns no rows")
    check(bound.keys == literal.keys, s"$label: bound ${bound.keys.size} rows ${bound.keys}, literal ${literal.keys.size} rows ${literal.keys}")
    bound
  }

  private def assertBinds(label: String, outcome: Outcome): Unit = {
    check(outcome.parameters.nonEmpty, s"$label: no parameters")
    check(placeholders(outcome.selectSql) == outcome.parameters.size,
      s"$label: ${placeholders(outcome.selectSql)} placeholders in ${outcome.selectSql}, parameters ${outcome.parameters}")
    check(placeholders(outcome.countSql) == outcome.parameters.size,
      s"$label: ${placeholders(outcome.countSql)} placeholders in ${outcome.countSql}, parameters ${outcome.parameters}")
  }

  private def loadAll(tab: GridTab): Unit = {
    tab.setQuery(null)
    tab.query(false)
  }

  private def valueOf(tab: GridTab, row: Int, columnName: String): AnyRef = tab.getValue(row, columnName)

  /** Restrictions on the first tab of a window, with the values of its first row */
  private def restrictionsOf(tab: GridTab, tableName: String, textColumn: String, nullColumn: String,
                             numberColumn: String): Seq[(String, Boolean, () => MQuery)] = {
    loadAll(tab)
    check(tab.getRowCount > 0, s"No rows in $tableName")
    val keyColumn = tab.getKeyColumnName
    val key = valueOf(tab, 0, keyColumn).asInstanceOf[Integer]
    val text = valueOf(tab, 0, textColumn).toString
    val created = valueOf(tab, 0, "Created").asInstanceOf[Timestamp]
    Seq(
      ("EQUAL on the key", true, () => { val query = new MQuery(tableName); query.addRestriction(keyColumn, MQuery.EQUAL, key); query }),
      ("LIKE on a text", true, () => { val query = new MQuery(tableName); query.addRestriction(textColumn, MQuery.LIKE, text.take(2) + "%"); query }),
      ("BETWEEN on a date", true, () => { val query = new MQuery(tableName); query.addRangeRestriction("Created", created, new Timestamp(created.getTime + 86400000L)); query }),
      ("BETWEEN on a number", false, () => { val query = new MQuery(tableName); query.addRangeRestriction(numberColumn, new java.math.BigDecimal("0"), new java.math.BigDecimal("100000000")); query }),
      ("IS NULL", false, () => { val query = new MQuery(tableName); query.addRestriction(nullColumn, MQuery.NULL, null); query }),
      ("IS NOT NULL and EQUAL", true, () => { val query = new MQuery(tableName); query.addRestriction(nullColumn, MQuery.NOT_NULL, null); query.addRestriction(keyColumn, MQuery.NOT_EQUAL, Integer.valueOf(0)); query }),
      ("EQUAL on the key with the text of a number", true, () => MQuery.getEqualQuery(keyColumn, key.toString)))
  }

  private def assertRestrictions(tab: GridTab, restrictions: Seq[(String, Boolean, () => MQuery)]): Unit =
    restrictions.foreach { case (label, requiresRows, original) =>
      val bound = assertSameRows(tab, label, requiresRows, original)
      // IS NULL / IS NOT NULL carry no value: only the restrictions with values must bind
      if (label != "IS NULL" && label != "IS NOT NULL and EQUAL")
        assertBinds(label, bound)
      else
        check(placeholders(bound.selectSql) == bound.parameters.size, s"$label: placeholders and parameters differ")
    }

  Feature("Windows read their rows with bind parameters") {

    Scenario("Sales Order: the restrictions return the rows of the literal statement") {
      val window = openWindow(SalesOrderWindow)
      try {
        val tab = openTab(window, 0)
        assertRestrictions(tab, restrictionsOf(tab, "C_Order", "DocumentNo", "POReference", "GrandTotal"))
      } finally window.dispose()
    }

    Scenario("Business Partner: the restrictions return the rows of the literal statement") {
      val window = openWindow(BusinessPartnerWindow)
      try {
        val tab = openTab(window, 0)
        assertRestrictions(tab, restrictionsOf(tab, "C_BPartner", "Name", "Description", "SO_CreditLimit"))
      } finally window.dispose()
    }

    Scenario("Product: the restrictions return the rows of the literal statement") {
      val window = openWindow(ProductWindow)
      try {
        val tab = openTab(window, 0)
        assertRestrictions(tab, restrictionsOf(tab, "M_Product", "Value", "Description", "Weight"))
      } finally window.dispose()
    }

    Scenario("a child tab combines its link column with the restriction") {
      Given("an order that has lines")
      val window = openWindow(SalesOrderWindow)
      try {
        val orderTab = openTab(window, 0)
        loadAll(orderTab)
        val orderIds = keysOf(orderTab)
        val orderId = DB.getSQLValue(null, s"SELECT MIN(C_Order_ID) FROM C_OrderLine WHERE C_Order_ID IN (${orderIds.mkString(",")})")
        check(orderId > 0, "No order with lines")
        val orderQuery = new MQuery("C_Order")
        orderQuery.addRestriction("C_Order_ID", MQuery.EQUAL, Integer.valueOf(orderId))
        orderTab.setQuery(orderQuery)
        orderTab.query(false)
        val lineTab = openTab(window, tabOf(window, "C_OrderLine"))
        loadAll(lineTab)
        check(lineTab.getRowCount > 0, "The order has no lines in its tab")
        val lineId = lineTab.getValue(0, lineTab.getKeyColumnName).asInstanceOf[Integer]
        When("the lines are restricted by key, by quantity and by a text")
        Then("the rows equal the literal ones and the link column stays in the text")
        val restrictions = Seq(
          ("line by key", () => { val query = new MQuery("C_OrderLine"); query.addRestriction("C_OrderLine_ID", MQuery.EQUAL, lineId); query }),
          ("line by quantity", () => { val query = new MQuery("C_OrderLine"); query.addRangeRestriction("QtyOrdered", new java.math.BigDecimal("0"), new java.math.BigDecimal("100000")); query }),
          ("line by number as text", () => { val query = new MQuery("C_OrderLine"); query.addRestriction("Line", MQuery.GREATER_EQUAL, "0"); query }))
        restrictions.foreach { case (label, original) =>
          val bound = assertSameRows(lineTab, label, requiresRows = true, original)
          assertBinds(label, bound)
          check(bound.selectSql.contains("C_OrderLine.C_Order_ID=?"), s"$label: the link column is not bound in ${bound.selectSql}")
          check(bound.parameters.head == new java.math.BigDecimal(orderId), s"$label: the link value is not the first parameter ${bound.parameters}")
          check(lineTab.getWhereExtended.contains(s"C_OrderLine.C_Order_ID=$orderId"), s"$label: ${lineTab.getWhereExtended}")
        }
      } finally window.dispose()
    }
  }

  Feature("A column that is not in the tab restricts through a subquery") {

    Scenario("the parameters are collected after the validation changed the query") {
      Given("a shipment of a sales order, a column that the order tab does not have")
      val window = openWindow(SalesOrderWindow)
      try {
        val tab = openTab(window, 0)
        loadAll(tab)
        val inOutId = DB.getSQLValue(null, s"SELECT MIN(M_InOut_ID) FROM M_InOut WHERE C_Order_ID IN (${keysOf(tab).mkString(",")})")
        val orderId = DB.getSQLValue(null, "SELECT C_Order_ID FROM M_InOut WHERE M_InOut_ID=?", inOutId)
        check(inOutId > 0 && orderId > 0, "No shipment of the orders")
        check(tab.getField("M_InOut_ID") == null, "The tab has M_InOut_ID")
        When("the tab is restricted by M_InOut_ID")
        val bound = execute(tab, MQuery.getEqualQuery("M_InOut_ID", Integer.valueOf(inOutId)))
        Then("the IN subquery holds the placeholder and the order is the one of the shipment")
        check(bound.selectSql.contains("C_Order.C_Order_ID IN (SELECT xx.C_Order_ID FROM M_InOut xx WHERE (xx.M_InOut_ID=?))"), bound.selectSql)
        check(bound.parameters == Vector(Integer.valueOf(inOutId)), bound.parameters.toString)
        check(placeholders(bound.countSql) == 1)
        check(bound.keys == Vector(orderId), s"${bound.keys} instead of $orderId")
      } finally window.dispose()
    }
  }

  Feature("A text value against a numeric or a date column (R3)") {

    Scenario("the text of an ID, a number and a date compares as the literal text did") {
      val window = openWindow(SalesOrderWindow)
      try {
        val tab = openTab(window, 0)
        loadAll(tab)
        val orderId = valueOf(tab, 0, "C_Order_ID").asInstanceOf[Integer]
        val dateOrdered = valueOf(tab, 0, "DateOrdered").asInstanceOf[Timestamp]
        val day = new SimpleDateFormat("yyyy-MM-dd").format(dateOrdered)
        val restrictions = Seq(
          ("an ID as text", () => MQuery.getEqualQuery("C_Order_ID", orderId.toString)),
          ("a number range as text", () => { val query = new MQuery("C_Order"); query.addRangeRestriction("GrandTotal", "0", "100000000"); query }),
          ("a date as text", () => { val query = new MQuery("C_Order"); query.addRestriction("DateOrdered", MQuery.EQUAL, day); query }),
          ("the constant 1 equal to the text 1", () => MQuery.getEqualQuery("1", "1")))
        restrictions.foreach { case (label, original) =>
          val bound = assertSameRows(tab, label, requiresRows = true, original)
          assertBinds(label, bound)
        }
        And("the constant 1 equal to the text 2 returns nothing in both forms")
        check(assertSameRows(tab, "1 is 2", requiresRows = false, () => MQuery.getEqualQuery("1", "2")).keys.isEmpty)
      } finally window.dispose()
    }
  }

  Feature("The requery decision compares the restriction, not the SQL shape") {

    Scenario("changing only the value runs the query again") {
      val window = openWindow(SalesOrderWindow)
      try {
        val tab = openTab(window, 0)
        loadAll(tab)
        val orderIds = keysOf(tab)
        check(orderIds.size >= 2, "Needs two orders")
        def equalTo(orderId: Int): MQuery = { val query = new MQuery("C_Order"); query.addRestriction("C_Order_ID", MQuery.EQUAL, Integer.valueOf(orderId)); query }
        val first = execute(tab, equalTo(orderIds.head))
        val second = execute(tab, equalTo(orderIds.last))
        val again = execute(tab, equalTo(orderIds.last))
        check(first.keys == Vector(orderIds.head), s"first ${first.keys}")
        check(second.keys == Vector(orderIds.last), s"second ${second.keys}")
        check(first.selectSql == second.selectSql, "the SQL shape must be the same")
        check(first.parameters == Vector(Integer.valueOf(orderIds.head)) && second.parameters == Vector(Integer.valueOf(orderIds.last)),
          s"parameters ${first.parameters} ${second.parameters}")
        Then("the same restriction again refreshes with the same parameters")
        check(again.keys == second.keys && again.parameters == second.parameters, s"again ${again.keys} ${again.parameters}")
        And("a refresh that retains the current row keeps the parameters in place")
        tab.getTableModel.dataRefreshAll(true, 0)
        val refreshed = tab.getTableModel
        check(placeholders(privateText(refreshed, "m_SQL")) == refreshed.getSelectWhereParameters.size, "refresh lost the parameters")
        check(refreshed.getRowCount == 1, s"refresh rows ${refreshed.getRowCount}")
      } finally window.dispose()
    }
  }

  Feature("GridTable binds Timestamp, Boolean and null in the order of the placeholders") {

    Scenario("the values bind and null advances the position; a new where clause resets them") {
      val window = openWindow(SalesOrderWindow)
      try {
        val tab = openTab(window, 0)
        loadAll(tab)
        val table = tab.getTableModel
        val day = valueOf(tab, 0, "DateOrdered").asInstanceOf[Timestamp]
        val literalWhere = s"C_Order.DateOrdered>=${DB.TO_DATE(day)} AND C_Order.IsSOTrx='Y' AND COALESCE(C_Order.POReference,'x')='x' AND C_Order.DocumentNo LIKE '%'"
        val boundWhere = "C_Order.DateOrdered>=? AND C_Order.IsSOTrx=? AND COALESCE(C_Order.POReference,'x')=COALESCE(?,'x') AND C_Order.DocumentNo LIKE ?"
        val parameters = java.util.Arrays.asList[Object](day, java.lang.Boolean.TRUE, null, "%")
        table.dataRequery(literalWhere, false, 0)
        val expectedRows = table.getRowCount
        check(expectedRows > 0, "The literal reference returns no rows")
        check(table.getSelectWhereParameters.isEmpty)
        When("the where clause comes with its parameters")
        table.dataRequery(boundWhere, parameters, false, 0)
        Then("the rows are the literal ones and the values are in the table")
        check(table.getRowCount == expectedRows, s"bound ${table.getRowCount}, literal $expectedRows")
        check(table.getSelectWhereParameters.asScala.toVector == Vector[Any](day, java.lang.Boolean.TRUE, null, "%"))
        And("the next where clause without parameters resets the list")
        table.dataRequery(literalWhere, false, 0)
        check(table.getSelectWhereParameters.isEmpty, "the parameters were kept")
        check(table.getRowCount == expectedRows)
        And("the parameters of the caller can change later without changing the table")
        val caller = new java.util.ArrayList[Object](parameters)
        table.dataRequery(boundWhere, caller, false, 0)
        caller.clear()
        check(table.getSelectWhereParameters.size == 4)
        And("the old overload of an closed table resets them too")
        table.close(false)
        table.setSelectWhereClause(boundWhere, parameters, false, 0)
        check(table.getSelectWhereParameters.size == 4)
        table.setSelectWhereClause(literalWhere, false, 0)
        check(table.getSelectWhereParameters.isEmpty)
        val unmodifiable = table.getSelectWhereParameters
        intercept[UnsupportedOperationException](unmodifiable.add("x"))
      } finally window.dispose()
    }
  }

  /** The first tab of the parent table and the child tab, with the child reached through the parent record */
  private def childRows(window: GridWindow, parentTab: GridTab, childTab: GridTab, parentRow: Int): Outcome = {
    parentTab.setCurrentRow(parentRow)
    childTab.setQuery(null)
    childTab.query(false)
    val table = childTab.getTableModel
    Outcome(keysOf(childTab), privateText(table, "m_SQL"), privateText(table, "m_SQL_Count"),
      table.getSelectWhereParameters.asScala.toVector)
  }

  /** The rows of the same child tab with the literal link value, which is what getWhereExtended holds */
  private def literalChildKeys(childTab: GridTab): Vector[Int] = {
    val table = childTab.getTableModel
    table.dataRequery(childTab.getWhereExtended, false, 0)
    keysOf(childTab)
  }

  /** The keys of a tab of the sweep; a tab whose key has no value is identified by its row count */
  private def rowsOf(tab: GridTab): Vector[String] = {
    val keyColumn = tab.getKeyColumnName
    (0 until tab.getRowCount).map { row =>
      val key = if (keyColumn == null || keyColumn.isEmpty) null else tab.getValue(row, keyColumn)
      if (key == null) "row" else key.toString
    }.sorted.toVector
  }

  private def assertChildLink(windowId: Int, parentTable: String, childTable: String, linkColumn: String): Unit = {
    val window = openWindow(windowId)
    try {
      val parentTab = openTab(window, 0)
      val childTab = openTab(window, tabOf(window, childTable))
      check(parentTab.getTableName == parentTable, s"The first tab is ${parentTab.getTableName}")
      check(childTab.isDetail, s"$childTable is not a detail tab")
      loadAll(parentTab)
      val parentKeys = (0 until parentTab.getRowCount).map(row => parentTab.getValue(row, parentTab.getKeyColumnName).asInstanceOf[Number].intValue)
      // the parents that have children in the table, the rows of the parent tab that show them
      val withChildren = parentKeys.zipWithIndex.filter { case (key, _) =>
        DB.getSQLValue(null, s"SELECT COUNT(*) FROM $childTable WHERE $linkColumn=?", key) > 0
      }
      check(withChildren.size >= 2, s"$parentTable has fewer than two records with $childTable")
      val Seq((firstKey, firstRow), (secondKey, secondRow)) = withChildren.take(2)
      When("the parent record is the first one and then the second one")
      val first = childRows(window, parentTab, childTab, firstRow)
      val firstExtended = childTab.getWhereExtended
      check(literalChildKeys(childTab) == first.keys, s"first parent: bound ${first.keys}, literal differs")
      val second = childRows(window, parentTab, childTab, secondRow)
      val secondExtended = childTab.getWhereExtended
      check(literalChildKeys(childTab) == second.keys, s"second parent: bound ${second.keys}, literal differs")
      Then("the SQL text is the same and the parameters differ in the link value")
      check(first.keys.nonEmpty && second.keys.nonEmpty, "a parent without children")
      check(first.selectSql == second.selectSql && first.countSql == second.countSql, s"the SQL changes with the parent: ${first.selectSql} / ${second.selectSql}")
      check(first.selectSql.contains(s"$childTable.$linkColumn=?"), first.selectSql)
      check(first.parameters.head == new java.math.BigDecimal(firstKey) && second.parameters.head == new java.math.BigDecimal(secondKey),
        s"parameters ${first.parameters} ${second.parameters}")
      check(placeholders(first.selectSql) == first.parameters.size && placeholders(first.countSql) == first.parameters.size, "placeholders and parameters differ")
      And("the rows are the ones of the parent, whatever the other parent has")
      check(first.keys.toSet != second.keys.toSet, "both parents show the same rows")
      And("getWhereExtended still holds the literal link value")
      check(firstExtended.contains(s"$childTable.$linkColumn=$firstKey") && !firstExtended.contains("?"), firstExtended)
      check(secondExtended.contains(s"$childTable.$linkColumn=$secondKey") && !secondExtended.contains("?"), secondExtended)
    } finally window.dispose()
  }

  Feature("A child tab binds the value of its link column") {

    Scenario("Sales Order, Order Line: switching the order keeps the SQL and changes the parameter") {
      assertChildLink(SalesOrderWindow, "C_Order", "C_OrderLine", "C_Order_ID")
    }

    Scenario("Business Partner, Location: switching the partner keeps the SQL and changes the parameter") {
      assertChildLink(BusinessPartnerWindow, "C_BPartner", "C_BPartner_Location", "C_BPartner_ID")
    }

    Scenario("the link value comes before the values of the restriction") {
      val window = openWindow(SalesOrderWindow)
      try {
        val parentTab = openTab(window, 0)
        val lineTab = openTab(window, tabOf(window, "C_OrderLine"))
        loadAll(parentTab)
        val orderId = DB.getSQLValue(null, s"SELECT MIN(C_Order_ID) FROM C_OrderLine WHERE C_Order_ID IN (${keysOf(parentTab).mkString(",")})")
        val row = (0 until parentTab.getRowCount).find(row => parentTab.getValue(row, "C_Order_ID") == Integer.valueOf(orderId)).get
        parentTab.setCurrentRow(row)
        // a new link value wipes the query of the tab (GridTab.query), so the tab sees the parent before the restriction
        loadAll(lineTab)
        val query = new MQuery("C_OrderLine")
        query.addRestriction("Line", MQuery.GREATER_EQUAL, Integer.valueOf(0))
        query.addRestriction("QtyOrdered", MQuery.GREATER_EQUAL, new java.math.BigDecimal("0"))
        val outcome = execute(lineTab, query)
        check(outcome.parameters == Vector(new java.math.BigDecimal(orderId), Integer.valueOf(0), new java.math.BigDecimal("0")), outcome.parameters.toString)
        check(outcome.selectSql.indexOf("C_OrderLine.C_Order_ID=?") < outcome.selectSql.indexOf("(Line>=?"), outcome.selectSql)
      } finally window.dispose()
    }
  }

  /** The tabs above a detail tab, from the top: the nearest earlier tab of each lower level */
  private def ancestorsOf(window: GridWindow, tabNo: Int): Seq[Int] = {
    var level = window.getTab(tabNo).getTabLevel
    var chain = List.empty[Int]
    var index = tabNo - 1
    while (level > 0 && index >= 0) {
      if (window.getTab(index).getTabLevel < level) {
        chain = index :: chain
        level = window.getTab(index).getTabLevel
      }
      index -= 1
    }
    chain
  }

  /** What a child tab shows for the first record of its parents, bound and literal: None when the parents have no rows */
  private def sweepChild(window: GridWindow, tabNo: Int): Option[(Vector[String], Vector[String], Outcome, String)] = {
    val ancestors = ancestorsOf(window, tabNo)
    val parentsHaveRows = ancestors.forall { ancestorNo =>
      val ancestor = openTab(window, ancestorNo)
      ancestor.setQuery(null)
      ancestor.query(false, 0, 50)
      ancestor.getRowCount > 0 && { ancestor.setCurrentRow(0); true }
    }
    if (!parentsHaveRows)
      return None
    val tab = openTab(window, tabNo)
    tab.setQuery(null)
    tab.query(false, 0, 0)
    val table = tab.getTableModel
    val boundKeys = rowsOf(tab)
    val bound = Outcome(Vector.empty, privateText(table, "m_SQL"), privateText(table, "m_SQL_Count"), table.getSelectWhereParameters.asScala.toVector)
    val extended = tab.getWhereExtended
    table.dataRequery(extended, false, 0)
    Some((boundKeys, rowsOf(tab), bound, extended))
  }

  Feature("Every window and tab returns the same count bound and literal") {

    Scenario("an EQUAL restriction on the key of the first row, in every window reachable without a screen") {
      val role = MRole.getDefault(Env.getCtx, false)
      val windowIds = DB.getSQLValueString(null, "SELECT string_agg(AD_Window_ID::text, ',' ORDER BY AD_Window_ID) FROM AD_Window WHERE IsActive='Y'")
        .split(",").map(_.toInt)
      var windows, tabs, compared, empty, noKey, synonym, tabErrors, windowErrors, noAccess = 0
      var childCompared, childEmpty, childRows, childContext = 0
      val mismatches = scala.collection.mutable.ArrayBuffer[String]()
      windowIds.foreach { windowId =>
        if (role.getWindowAccess(windowId) == null)
          noAccess += 1
        else {
          val window = try openWindow(windowId) catch { case _: Throwable => null }
          if (window == null)
            windowErrors += 1
          else {
            windows += 1
            try {
              (0 until window.getTabCount).foreach { tabNo =>
                tabs += 1
                try {
                  val tab = openTab(window, tabNo)
                  if (tab.isDetail)
                    sweepChild(window, tabNo) match {
                      case None => childEmpty += 1
                      case Some((boundKeys, literalKeys, bound, extended)) =>
                        childCompared += 1
                        childRows += boundKeys.size
                        val link = s"${tab.getTableName}.${tab.getLinkColumnName}"
                        // a link without value or without column runs 2=3 and binds nothing
                        // a dictionary WHERE with @context@ reads the context of the window, which the first query changes
                        // (the rows of the tab set AD_User_ID or C_BPartner_ID): the rows are not comparable, the SQL is
                        val contextual = extended.contains("@")
                        if (contextual)
                          childContext += 1
                        if (!bound.selectSql.contains("2=3") && ((!contextual && boundKeys != literalKeys) || extended.contains("?")
                          || placeholders(bound.selectSql) != bound.parameters.size || placeholders(bound.countSql) != bound.parameters.size
                          || !bound.selectSql.contains(s"$link=?")))
                          mismatches += s"W$windowId T$tabNo ${tab.getTableName} CHILD: bound ${boundKeys.size} rows ${bound.parameters}, literal ${literalKeys.size} rows; extended $extended"
                    }
                  val keyColumn = tab.getKeyColumnName
                  if (keyColumn == null || keyColumn.isEmpty)
                    noKey += 1
                  // GridTab.validateQuery rewrites a C_DocType* column to C_DocType_ID in the literal MQuery as well
                  else if (keyColumn.startsWith("C_DocType") && keyColumn != "C_DocType_ID")
                    synonym += 1
                  else {
                    tab.setQuery(null)
                    tab.query(false, 0, 50)
                    val key = if (tab.getRowCount > 0) tab.getValue(0, keyColumn) else null
                    if (!key.isInstanceOf[Integer])
                      empty += 1
                    else {
                      def equalKey(): MQuery = { val query = new MQuery(tab.getTableName); query.addRestriction(keyColumn, MQuery.EQUAL, key); query }
                      val literal = execute(tab, literalOf(tab, equalKey()))
                      val bound = execute(tab, equalKey())
                      compared += 1
                      if (bound.keys != literal.keys || bound.parameters.size != literal.parameters.size + 1 || bound.parameters.drop(literal.parameters.size).size != 1
                        || placeholders(bound.selectSql) != bound.parameters.size || placeholders(bound.countSql) != bound.parameters.size)
                        mismatches += s"W$windowId T$tabNo ${tab.getTableName}: bound ${bound.keys} ${bound.parameters}, literal ${literal.keys}"
                    }
                  }
                } catch { case error: Throwable => tabErrors += 1; mismatches += s"W$windowId T$tabNo ERROR $error" }
              }
            } finally window.dispose()
          }
        }
      }
      println(s"[window sweep] windows=${windowIds.length} noAccess=$noAccess opened=$windows notCreated=$windowErrors tabs=$tabs compared=$compared noRows=$empty noKey=$noKey docTypeSynonym=$synonym childTabs=$childCompared childNoParentRows=$childEmpty childRows=$childRows childContextSql=$childContext tabErrors=$tabErrors mismatches=${mismatches.size}")
      mismatches.foreach(line => println("[window sweep] MISMATCH " + line))
      check(mismatches.isEmpty, mismatches.take(10).mkString("\n"))
      check(compared > 0)
      check(childCompared > 0, "No child tab was compared")
    }
  }
}
