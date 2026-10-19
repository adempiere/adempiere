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

import org.adempiere.exceptions.DBException
import org.compiere.model.{FindCount, GridTab, GridWindow, GridWindowVO, MQuery, MRole}
import org.compiere.util.{DB, Env}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.jdk.CollectionConverters._

/** Find, the initial query and the zoom to a detail tab count and read with bind parameters, and the text that
  * stays in the context (`_TabInfo_FindSQL`, `_TabInfo_SQL`) has the values rendered instead of the `?`.
  *
  * The reference of every check is the statement of before: the same restriction written as literal text with
  * `MQuery.getWhereClause`, with the access and the context parsed over it.
  */
class ValidateFindBindParameters extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val SalesOrderWindow = 143
  private var windowNumber = 4000

  private def check(condition: Boolean, message: => String = "assertion failed"): Unit =
    if (!condition) fail(message)

  private def placeholders(sql: String): Int = sql.count(_ == '?')

  /** The count as the Find dialog ran it before: literal text, then the access and the context over it */
  private def literalCount(tableName: String, whereExtended: String, query: MQuery): Int = {
    val sql = new StringBuilder("SELECT COUNT(*) FROM ").append(tableName)
    var hasWhere = false
    if (whereExtended != null && whereExtended.nonEmpty) {
      sql.append(" WHERE ").append(whereExtended)
      hasWhere = true
    }
    if (query != null && query.isActive)
      sql.append(if (hasWhere) " AND " else " WHERE ").append(query.getWhereClause)
    val access = MRole.getDefault.addAccessSQL(sql.toString, tableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO)
    val finalSql = Env.parseContext(Env.getCtx, 0, access, false)
    DB.getSQLValue(null, finalSql)
  }

  private def statementOf(tableName: String, whereExtended: String, query: MQuery): FindCount.CountStatement =
    FindCount.getStatement(Env.getCtx, 0, MRole.getDefault, tableName, whereExtended, query)

  private def boundCount(statement: FindCount.CountStatement): Int = FindCount.count(statement)

  private def timestampOf(tableName: String, columnName: String): Timestamp =
    DB.getSQLValueTSEx(null, s"SELECT MIN($columnName) FROM $tableName WHERE $columnName IS NOT NULL")

  private def textOf(tableName: String, columnName: String): String =
    DB.getSQLValueString(null, s"SELECT MIN($columnName) FROM $tableName WHERE $columnName IS NOT NULL")

  private def restriction(tableName: String)(add: MQuery => Unit): MQuery = {
    val query = new MQuery(tableName)
    add(query)
    query
  }

  Feature("Find counts the records with bind parameters") {

    Scenario("the count equals the literal count for several windows and restrictions") {
      Given("the first order, partner and product of the database")
      val orderId = DB.getSQLValue(null, "SELECT MIN(C_Order_ID) FROM C_Order")
      val created = timestampOf("C_Order", "Created")
      val documentNo = textOf("C_Order", "DocumentNo")
      val partnerName = textOf("C_BPartner", "Name")
      val productValue = textOf("M_Product", "Value")
      val productId = DB.getSQLValue(null, "SELECT MIN(M_Product_ID) FROM M_Product")
      check(orderId > 0 && productId > 0, "No order or product")
      val clientFilter = "AD_Client_ID=@#AD_Client_ID@"
      val cases: Seq[(String, String, String, () => MQuery)] = Seq(
        ("String LIKE", "C_Order", null, () => restriction("C_Order")(_.addRestriction("DocumentNo", MQuery.LIKE, documentNo.take(2) + "%"))),
        ("date range", "C_Order", null, () => restriction("C_Order")(_.addRangeRestriction("Created", created, new Timestamp(created.getTime + 86400000L)))),
        ("number", "C_Order", null, () => restriction("C_Order")(_.addRestriction("GrandTotal", MQuery.GREATER_EQUAL, new java.math.BigDecimal("0")))),
        ("text on an ID column (R3)", "C_Order", null, () => MQuery.getEqualQuery("C_Order_ID", orderId.toString)),
        ("raw whereExtended with @context@", "C_Order", clientFilter, () => restriction("C_Order")(_.addRestriction("DocumentNo", MQuery.LIKE, documentNo.take(1) + "%"))),
        ("raw whereExtended alone", "M_Product", clientFilter + " AND IsActive='Y'", () => null),
        ("partner name LIKE and text ID", "C_BPartner", clientFilter, () => restriction("C_BPartner")(query => {
          query.addRestriction("Name", MQuery.LIKE, partnerName.take(2) + "%")
          query.addRestriction("C_BPartner_ID", MQuery.GREATER_EQUAL, "0")
        })),
        ("product equal on a text ID", "M_Product", null, () => MQuery.getEqualQuery("M_Product_ID", productId.toString)),
        ("product value LIKE and IS NOT NULL", "M_Product", clientFilter, () => restriction("M_Product")(query => {
          query.addRestriction("Value", MQuery.LIKE, productValue.take(1) + "%")
          query.addRestriction("Description", MQuery.NOT_NULL, null)
        })),
        ("no restriction", "C_Order", null, () => null))
      When("each statement is counted bound and literal")
      cases.foreach { case (label, tableName, whereExtended, original) =>
        val query = original()
        val statement = statementOf(tableName, whereExtended, query)
        val expected = literalCount(tableName, whereExtended, query)
        Then(s"$label: the same number")
        check(expected >= 0, s"$label: the literal reference failed")
        check(boundCount(statement) == expected, s"$label: bound ${boundCount(statement)}, literal $expected, ${statement.getSql} ${statement.getParameters}")
        check(placeholders(statement.getSql) == statement.getParameters.size, s"$label: placeholders and parameters differ in ${statement.getSql}")
        And(s"$label: the literal text has the values and gives the same number")
        check(placeholders(statement.getLiteralSql) == 0, s"$label: ${statement.getLiteralSql}")
        check(DB.getSQLValue(null, statement.getLiteralSql) == expected, s"$label: ${statement.getLiteralSql}")
      }
      And("the restrictions with a value really bind")
      check(statementOf("C_Order", null, cases.head._4()).getParameters.size == 1)
      check(statementOf("C_Order", null, cases(1)._4()).getParameters.size == 2)
      check(cases.filter(_._1 == "text on an ID column (R3)").forall(c => statementOf(c._2, c._3, c._4()).getParameters.asScala == Seq("" + orderId)))
      And("some of them count more than zero")
      check(boundCount(statementOf("C_Order", null, cases(3)._4())) == 1)
      check(boundCount(statementOf("M_Product", cases(5)._3, null)) > 0)
    }

    Scenario("the raw whereExtended stays text and its context is parsed") {
      val statement = statementOf("C_Order", "AD_Client_ID=@#AD_Client_ID@", restriction("C_Order")(_.addRestriction("DocumentNo", MQuery.LIKE, "1%")))
      check(!statement.getSql.contains("@"), statement.getSql)
      check(statement.getSql.contains(s"AD_Client_ID=${Env.getAD_Client_ID(Env.getCtx)}"), statement.getSql)
      check(placeholders(statement.getSql) == 1, statement.getSql)
    }
  }

  Feature("The initial query and the zoom to a detail tab bind their restriction") {

    Scenario("initialQuery counts without access and with the extended where already parsed") {
      val documentNo = textOf("C_Order", "DocumentNo")
      val parsedExtended = Env.parseContext(Env.getCtx, 0, "AD_Client_ID=@#AD_Client_ID@", false)
      val query = restriction("C_Order")(_.addRestriction("DocumentNo", MQuery.LIKE, documentNo.take(2) + "%"))
      val statement = FindCount.getStatement(null, 0, null, "C_Order", parsedExtended, query)
      val literal = DB.getSQLValue(null, s"SELECT COUNT(*) FROM C_Order WHERE $parsedExtended AND ${query.getWhereClause}")
      check(literal > 0, "The reference returns nothing")
      check(DB.getSQLValueQuery(null, statement.getSql, statement.getParameters) == literal)
      check(placeholders(statement.getSql) == 1 && statement.getParameters.size == 1, statement.getSql)
      And("a text on a numeric column compares as the literal text did")
      val textId = MQuery.getEqualQuery("C_Order_ID", DB.getSQLValue(null, "SELECT MIN(C_Order_ID) FROM C_Order").toString)
      val textStatement = FindCount.getStatement(null, 0, null, "C_Order", null, textId)
      check(DB.getSQLValueQuery(null, textStatement.getSql, textStatement.getParameters) == 1)
      And("an inactive query counts all the records of the where")
      check(DB.getSQLValueQuery(null, FindCount.getStatement(null, 0, null, "C_Order", parsedExtended, new MQuery("C_Order")).getSql, new java.util.ArrayList[Object]())
        == DB.getSQLValue(null, s"SELECT COUNT(*) FROM C_Order WHERE $parsedExtended"))
    }

    Scenario("the link value of the zoom is the same as the literal one") {
      val lineId = DB.getSQLValue(null, "SELECT MIN(C_OrderLine_ID) FROM C_OrderLine")
      check(lineId > 0, "No order line")
      val expected = DB.getSQLValue(null, s"SELECT C_Order_ID FROM C_OrderLine WHERE C_OrderLine_ID=$lineId")
      check(expected > 0)
      Seq(("an integer", MQuery.getEqualQuery("C_OrderLine_ID", Integer.valueOf(lineId))),
          ("a text (R3)", MQuery.getEqualQuery("C_OrderLine_ID", lineId.toString))).foreach { case (label, query) =>
        val literal = DB.getSQLValue(null, "SELECT C_Order_ID FROM C_OrderLine WHERE " + query.getWhereClause)
        check(literal == expected, s"$label: the literal reference gives $literal")
        check(FindCount.getLinkValue("C_Order_ID", "C_OrderLine", query) == literal, s"$label")
      }
      And("a query without a record gives -1 as the literal does")
      check(FindCount.getLinkValue("C_Order_ID", "C_OrderLine", MQuery.getEqualQuery("C_OrderLine_ID", Integer.valueOf(-5)))
        == DB.getSQLValue(null, "SELECT C_Order_ID FROM C_OrderLine WHERE " + MQuery.getEqualQuery("C_OrderLine_ID", Integer.valueOf(-5)).getWhereClause))
    }
  }

  Feature("inlineQueryParameters renders the values as the literal text") {

    Scenario("it renders every type") {
      val day = Timestamp.valueOf("2026-01-30 10:20:30")
      val rendered = DB.inlineQueryParameters("a=? b=? c=? d=? e=? f=? g=? h=?",
        java.util.Arrays.asList[Object]("it's", day, Integer.valueOf(5), new java.math.BigDecimal("1.50"), null, java.lang.Boolean.TRUE, java.lang.Boolean.FALSE, java.lang.Long.valueOf(7)))
      check(rendered == s"a=${DB.TO_STRING("it's")} b=${DB.TO_DATE(day, false)} c=5 d=1.50 e=NULL f='Y' g='N' h=7", rendered)
      check(rendered.contains("'it''s'"), rendered)
      check(DB.inlineQueryParameters("x=?", java.util.Arrays.asList[Object](new java.math.BigDecimal("1E+3"))) == "x=1000")
    }

    Scenario("it ignores the question marks inside a string literal") {
      val rendered = DB.inlineQueryParameters("SELECT '?' AS a, 'it''s ?' AS b, ? AS c FROM Dual WHERE x LIKE '%?'",
        java.util.Arrays.asList[Object](Integer.valueOf(7)))
      check(rendered == "SELECT '?' AS a, 'it''s ?' AS b, 7 AS c FROM Dual WHERE x LIKE '%?'", rendered)
      And("a value that holds a question mark is not read again")
      check(DB.inlineQueryParameters("a=? AND b=?", java.util.Arrays.asList[Object]("x?y", Integer.valueOf(1))) == "a='x?y' AND b=1")
    }

    Scenario("the placeholders and the values must match") {
      intercept[IllegalArgumentException](DB.inlineQueryParameters("a=? AND b=?", java.util.Arrays.asList[Object](Integer.valueOf(1))))
      intercept[IllegalArgumentException](DB.inlineQueryParameters("a=?", java.util.Arrays.asList[Object](Integer.valueOf(1), Integer.valueOf(2))))
      intercept[DBException](DB.inlineQueryParameters("a=?", java.util.Arrays.asList[Object](new java.util.Date())))
      check(DB.inlineQueryParameters("a=1", null) == "a=1")
    }

    Scenario("the text gives the same rows as the bound execution") {
      val orderId = DB.getSQLValue(null, "SELECT MIN(C_Order_ID) FROM C_Order")
      val created = DB.getSQLValueTSEx(null, "SELECT Created FROM C_Order WHERE C_Order_ID=?", orderId)
      val documentNo = DB.getSQLValueString(null, "SELECT DocumentNo FROM C_Order WHERE C_Order_ID=?", orderId)
      val query = new MQuery("C_Order")
      query.addRestriction("DocumentNo", MQuery.LIKE, documentNo.take(2) + "%")
      query.addRangeRestriction("Created", created, new Timestamp(created.getTime + 86400000L))
      query.addRestriction("GrandTotal", MQuery.GREATER_EQUAL, new java.math.BigDecimal("0"))
      query.addRestriction("C_Order_ID", MQuery.GREATER_EQUAL, "0")
      val bound = query.getBoundWhereClause(true)
      check(bound.getParameters.size >= 5, bound.toString)
      val sql = "SELECT C_Order_ID FROM C_Order WHERE " + bound.getSql + " ORDER BY C_Order_ID"
      val statement = DB.prepareStatement(sql, null)
      val boundKeys = scala.collection.mutable.ArrayBuffer[Int]()
      try {
        DB.setQueryParameters(statement, bound.getParameters)
        val resultSet = statement.executeQuery()
        while (resultSet.next()) boundKeys += resultSet.getInt(1)
        resultSet.close()
      } finally statement.close()
      check(boundKeys.nonEmpty, "The reference returns no rows")
      val inlined = DB.inlineQueryParameters(sql, bound.getParameters)
      check(placeholders(inlined) == 0, inlined)
      val textStatement = DB.createStatement()
      val textKeys = scala.collection.mutable.ArrayBuffer[Int]()
      try {
        val resultSet = textStatement.executeQuery(inlined)
        while (resultSet.next()) textKeys += resultSet.getInt(1)
        resultSet.close()
      } finally textStatement.close()
      check(textKeys == boundKeys, s"text ${textKeys.size} rows, bound ${boundKeys.size} rows")
    }
  }

  Feature("The tab context keeps the SQL as text") {

    Scenario("_TabInfo_SQL has the values and no question mark after a query with restrictions") {
      windowNumber += 1
      val window = new GridWindow(GridWindowVO.create(Env.getCtx, windowNumber, SalesOrderWindow))
      try {
        window.initTab(0)
        val orderTab = window.getTab(0)
        orderTab.setQuery(null)
        orderTab.query(false)
        check(orderTab.getRowCount > 0, "No orders")
        val orderId = orderTab.getValue(0, "C_Order_ID").asInstanceOf[Integer]
        val created = orderTab.getValue(0, "Created").asInstanceOf[Timestamp]
        val query = new MQuery("C_Order")
        query.addRestriction("C_Order_ID", MQuery.EQUAL, orderId)
        query.addRangeRestriction("Created", created, new Timestamp(created.getTime + 86400000L))
        query.addRestriction("DocumentNo", MQuery.LIKE, "%")
        orderTab.setQuery(query)
        orderTab.query(false)
        val orderRows = orderTab.getRowCount
        check(orderRows > 0, "The restriction returns no order")
        assertContextSql(orderTab, orderRows)
        And("a child tab holds the link value in the text")
        val lineTabNo = (0 until window.getTabCount).find(window.getTab(_).getTableName == "C_OrderLine").get
        window.initTab(lineTabNo)
        val lineTab = window.getTab(lineTabNo)
        lineTab.setQuery(restriction("C_OrderLine")(_.addRestriction("Line", MQuery.GREATER_EQUAL, "0")))
        lineTab.query(false)
        assertContextSql(lineTab, lineTab.getRowCount)
        check(Env.getContext(Env.getCtx, lineTab.getWindowNo, lineTab.getTabNo, GridTab.CTX_SQL).contains(s"C_OrderLine.C_Order_ID=$orderId"),
          Env.getContext(Env.getCtx, lineTab.getWindowNo, lineTab.getTabNo, GridTab.CTX_SQL))
      } finally window.dispose()
    }
  }

  private def assertContextSql(tab: GridTab, expectedRows: Int): Unit = {
    val sql = Env.getContext(Env.getCtx, tab.getWindowNo, tab.getTabNo, GridTab.CTX_SQL)
    check(sql != null && sql.nonEmpty, "No _TabInfo_SQL")
    check(placeholders(sql) == 0, sql)
    check(DB.getSQLValue(null, s"SELECT COUNT(*) FROM ($sql) t") == expectedRows, s"$expectedRows rows expected for $sql")
  }
}
