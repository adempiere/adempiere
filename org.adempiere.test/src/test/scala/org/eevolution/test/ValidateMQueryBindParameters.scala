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

import org.compiere.model.{MPInstance, MPInstancePara, MQuery}
import org.compiere.util.{DB, Env}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters._

/**
  * MQuery returns its restrictions as a where clause with `?` placeholders and the ordered values
  * (`getBoundWhereClause`), and the literal text of `getWhereClause` stays as it was.
  *
  * The bound form must return the same rows as the literal form. Process parameters of `MQuery.get` read as
  * `TRUNC(column, 'DD')` and `101.0` in the literal text; the bound form compares the plain column with the bounds
  * of the day and binds exact numbers.
  */
class ValidateMQueryBindParameters extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val InvoiceProcess = 127 // Invoice Details: DateInvoiced range, C_BPartner_ID, C_Invoice_ID, DocStatus
  private val SingleDateProcess = 119 // DateInvoiced without range
  private val ClientId = 11

  /** The where clause with `?`, its values and a count of the placeholders. */
  private case class Bound(sql: String, parameters: Vector[AnyRef]) {
    def placeholders: Int = sql.count(_ == '?')
  }

  private def bound(query: MQuery, fullyQualified: Boolean): Bound = {
    val clause = query.getBoundWhereClause(fullyQualified)
    Bound(clause.getSql, clause.getParameters.asScala.toVector)
  }

  private def literal(query: MQuery, fullyQualified: Boolean): String = query.getWhereClause(fullyQualified)

  private def timestamp(text: String): Timestamp = Timestamp.valueOf(text)

  private def number(text: String): BigDecimal = new BigDecimal(text)

  /** Key set of the rows that satisfy the literal where clause. */
  private def literalKeys(source: String, key: String, where: String): Vector[Int] = {
    val statement = DB.prepareStatement(s"SELECT $key FROM $source WHERE $where ORDER BY $key", null)
    try keys(statement.executeQuery())
    finally statement.close()
  }

  /** Key set of the rows that satisfy the bound where clause. */
  private def boundKeys(source: String, key: String, clause: Bound): Vector[Int] = {
    val statement = DB.prepareStatement(s"SELECT $key FROM $source WHERE ${clause.sql} ORDER BY $key", null)
    try {
      DB.setParameters(statement, clause.parameters.asJava)
      keys(statement.executeQuery())
    } finally statement.close()
  }

  private def keys(resultSet: java.sql.ResultSet): Vector[Int] = {
    val rows = ArrayBuffer.empty[Int]
    try while (resultSet.next()) rows += resultSet.getInt(1)
    finally resultSet.close()
    rows.toVector
  }

  /** Runs both forms, qualified and not, and requires the same rows. Returns the key set. */
  private def assertSameRows(source: String, key: String, query: MQuery, label: String): Vector[Int] = {
    val results = Seq(false, true).map { qualified =>
      val clause = bound(query, qualified)
      assert(clause.placeholders == clause.parameters.size, s"$label: ${clause.placeholders} placeholders, ${clause.parameters.size} values")
      val expected = literalKeys(source, key, literal(query, qualified))
      val actual = boundKeys(source, key, clause)
      assert(actual == expected, s"$label (qualified $qualified): bound ${actual.size} rows $actual, literal ${expected.size} rows $expected")
      actual
    }
    results.head
  }

  private def newQuery(table: String): MQuery = new MQuery(table)

  private def nullCode: AnyRef = null

  Feature("The bound where clause of every operator and value type") {

    Scenario("an equal restriction on an integer, a string with a quote and a boolean") {
      Given("restrictions with an Integer, a String with a quote and a Boolean value")
      val query = newQuery("C_Order")
      query.addRestriction("C_BPartner_ID", MQuery.EQUAL, Integer.valueOf(117))
      query.addRestriction("DocumentNo", MQuery.EQUAL, "O'Brien")
      query.addRestriction("IsSOTrx", MQuery.EQUAL, java.lang.Boolean.TRUE)
      Then("the literal text is unchanged and the bound text has one placeholder per value")
      assert(literal(query, false) == "(C_BPartner_ID=117 AND DocumentNo='O''Brien' AND IsSOTrx='Y')")
      assert(literal(query, true) == "(C_Order.C_BPartner_ID=117 AND C_Order.DocumentNo='O''Brien' AND C_Order.IsSOTrx='Y')")
      assert(bound(query, false) == Bound("(C_BPartner_ID=? AND DocumentNo=? AND IsSOTrx=?)", Vector(Integer.valueOf(117), "O'Brien", "Y")))
      assert(bound(query, true) == Bound("(C_Order.C_BPartner_ID=? AND C_Order.DocumentNo=? AND C_Order.IsSOTrx=?)", Vector(Integer.valueOf(117), "O'Brien", "Y")))
    }

    Scenario("not equal, like, greater equal and less equal") {
      val query = newQuery("C_BPartner")
      query.addRestriction("C_BP_Group_ID", MQuery.NOT_EQUAL, Integer.valueOf(5))
      query.addRestriction("Name", MQuery.LIKE, "J%")
      query.addRestriction("Value", MQuery.GREATER_EQUAL, "A")
      query.addRestriction("Value", MQuery.LESS_EQUAL, "Z")
      assert(literal(query, false) == "(C_BP_Group_ID!=5 AND Name LIKE 'J%' AND Value>='A' AND Value<='Z')")
      assert(bound(query, false) == Bound("(C_BP_Group_ID!=? AND Name LIKE ? AND Value>=? AND Value<=?)", Vector(Integer.valueOf(5), "J%", "A", "Z")))
      assert(bound(query, true).sql == "(C_BPartner.C_BP_Group_ID!=? AND C_BPartner.Name LIKE ? AND C_BPartner.Value>=? AND C_BPartner.Value<=?)")
    }

    Scenario("between binds both values") {
      val query = newQuery("C_Invoice")
      query.addRangeRestriction("GrandTotal", number("10"), number("20.50"))
      query.addRangeRestriction("DocumentNo", "100", "200")
      assert(literal(query, false) == "(GrandTotal BETWEEN 10 AND 20.50 AND DocumentNo BETWEEN '100' AND '200')")
      assert(bound(query, false) == Bound("(GrandTotal BETWEEN ? AND ? AND DocumentNo BETWEEN ? AND ?)", Vector(number("10"), number("20.50"), "100", "200")))
    }

    Scenario("null and the text NULL stay IS NULL and take no parameter") {
      val query = newQuery("C_Order")
      query.addRestriction("Description", MQuery.EQUAL, nullCode)
      query.addRestriction("POReference", MQuery.NOT_EQUAL, "null")
      query.addRestriction("C_Project_ID", MQuery.NULL, nullCode)
      query.addRestriction("C_Campaign_ID", MQuery.NOT_NULL, nullCode)
      val expected = "(Description IS NULL  AND POReference IS NOT NULL  AND C_Project_ID IS NULL  AND C_Campaign_ID IS NOT NULL )"
      assert(literal(query, false) == expected)
      assert(bound(query, false) == Bound(expected, Vector.empty))
    }

    Scenario("Double, Long and BigDecimal values bind as BigDecimal and the literal keeps its text") {
      val query = newQuery("C_Order")
      query.addRestriction("C_Currency_ID", MQuery.EQUAL, java.lang.Double.valueOf(101.0))
      query.addRestriction("AD_Org_ID", MQuery.NOT_EQUAL, java.lang.Long.valueOf(5L))
      query.addRestriction("GrandTotal", MQuery.GREATER, number("1.50"))
      assert(literal(query, false) == "(C_Currency_ID=101.0 AND AD_Org_ID!=5 AND GrandTotal>1.50)")
      val clause = bound(query, false)
      assert(clause.sql == "(C_Currency_ID=? AND AD_Org_ID!=? AND GrandTotal>?)")
      assert(clause.parameters == Vector(number("101.0"), number("5"), number("1.50")))
      assert(clause.parameters.forall(_.isInstanceOf[BigDecimal]))
    }

    Scenario("a Timestamp binds the day, as the literal compares only the day") {
      val query = newQuery("C_Invoice")
      query.addRestriction("DateInvoiced", MQuery.GREATER_EQUAL, timestamp("2024-01-22 15:30:45"))
      query.addRangeRestriction("DateAcct", timestamp("2024-01-22 10:00:00"), timestamp("2024-08-07 23:59:59"))
      assert(literal(query, false) == "(DateInvoiced>=TO_DATE('2024-01-22','YYYY-MM-DD') AND DateAcct BETWEEN TO_DATE('2024-01-22','YYYY-MM-DD') AND TO_DATE('2024-08-07','YYYY-MM-DD'))")
      assert(bound(query, false) == Bound("(DateInvoiced>=? AND DateAcct BETWEEN ? AND ?)",
        Vector(timestamp("2024-01-22 00:00:00"), timestamp("2024-01-22 00:00:00"), timestamp("2024-08-07 00:00:00"))))
    }

    Scenario("quotes around a string code are cleaned as in the literal") {
      val query = newQuery("C_Order")
      query.addRestriction("DocStatus", MQuery.EQUAL, "'CO'")
      // the Restriction constructor drops the quotes with a two character cut; both forms share the cleaned code
      val cleaned = literal(query, false).stripPrefix("(DocStatus='").stripSuffix("')")
      assert(bound(query, false).parameters == Vector(cleaned))
    }

    Scenario("an empty string compares as the literal NULL does") {
      val query = newQuery("C_Order")
      query.addRestriction("Description", MQuery.EQUAL, "")
      assert(literal(query, false) == "(Description=NULL)")
      assert(bound(query, false) == Bound("(Description=?)", Vector(null)))
    }

    Scenario("nesting with join depth, AND/OR and a raw restriction") {
      Given("a query with an OR group that holds a raw restriction")
      val query = newQuery("C_Order")
      query.addRestriction("C_BPartner_ID", MQuery.EQUAL, Integer.valueOf(117))
      query.addRestriction("Name", MQuery.LIKE, "J%", null, null, false, 1)
      query.addRestriction("C_Order.IsActive='Y'", true, 1)
      query.addRangeRestriction("GrandTotal", number("1"), number("2"), null, null, null, true, 0)
      Then("the nesting is the same in both forms and the raw text passes through")
      assert(literal(query, false) == "(C_BPartner_ID=117 OR (Name LIKE 'J%' AND C_Order.IsActive='Y' AND GrandTotal BETWEEN 1 AND 2))")
      assert(bound(query, false) == Bound("(C_BPartner_ID=? OR (Name LIKE ? AND C_Order.IsActive='Y' AND GrandTotal BETWEEN ? AND ?))",
        Vector(Integer.valueOf(117), "J%", number("1"), number("2"))))
      assert(bound(query, true).sql == "(C_Order.C_BPartner_ID=? OR (C_Order.Name LIKE ? AND C_Order.IsActive='Y' AND C_Order.GrandTotal BETWEEN ? AND ?))")
    }

    Scenario("a query without restrictions is empty in both forms") {
      val query = newQuery("C_Order")
      assert(literal(query, false) == "")
      assert(bound(query, false) == Bound("", Vector.empty))
    }

    Scenario("the parameters cannot be changed") {
      val query = newQuery("C_Order")
      query.addRestriction("C_BPartner_ID", MQuery.EQUAL, Integer.valueOf(117))
      val parameters = query.getBoundWhereClause(false).getParameters
      intercept[UnsupportedOperationException](parameters.add("x"))
      intercept[UnsupportedOperationException](parameters.clear())
    }

    Scenario("one restriction by index") {
      val query = newQuery("C_Order")
      query.addRestriction("C_BPartner_ID", MQuery.EQUAL, Integer.valueOf(117))
      query.addRestriction("Name", MQuery.LIKE, "J%")
      query.addRestriction("C_Order.IsActive='Y'")
      assert(query.getWhereClause(1) == "Name LIKE 'J%'")
      val clause = query.getBoundWhereClause(1)
      assert(clause.getSql == "Name LIKE ?" && clause.getParameters.asScala.toVector == Vector("J%"))
      val raw = query.getBoundWhereClause(2)
      assert(raw.getSql == "C_Order.IsActive='Y'" && raw.getParameters.isEmpty)
      assert(query.getBoundWhereClause(9).getSql == "" && query.getBoundWhereClause(9).getParameters.isEmpty)
    }
  }

  Feature("A raw restriction with a placeholder cannot be mixed with bound values") {

    Scenario("the query falls back to the literal where clause without parameters") {
      Given("a structured restriction and a raw restriction that has a ?")
      val query = newQuery("C_Order")
      query.addRestriction("C_BPartner_ID", MQuery.EQUAL, Integer.valueOf(117))
      query.addRestriction("DocumentNo IN (SELECT DocumentNo FROM C_Order WHERE C_BPartner_ID=?)")
      Then("the bound form is the literal text and has no parameters")
      val clause = bound(query, false)
      assert(clause.sql == literal(query, false))
      assert(clause.sql == "(C_BPartner_ID=117 AND DocumentNo IN (SELECT DocumentNo FROM C_Order WHERE C_BPartner_ID=?))")
      assert(clause.parameters.isEmpty)
    }

    Scenario("a ? inside a string literal is not a placeholder") {
      val query = newQuery("C_Order")
      query.addRestriction("C_BPartner_ID", MQuery.EQUAL, Integer.valueOf(117))
      query.addRestriction("Description = 'what?' AND POReference = 'it''s?'")
      assert(bound(query, false) == Bound("(C_BPartner_ID=? AND Description = 'what?' AND POReference = 'it''s?')", Vector(Integer.valueOf(117))))
    }

    Scenario("a value that cannot be bound also gives the literal text") {
      val query = newQuery("C_Order")
      query.addRestriction("C_BPartner_ID", MQuery.EQUAL, Integer.valueOf(117))
      query.addRestriction("Created", MQuery.EQUAL, new java.util.Date(0L))
      val clause = bound(query, false)
      assert(clause.sql == literal(query, false))
      assert(clause.parameters.isEmpty)
    }
  }

  private def invoiceDays: Vector[Timestamp] = {
    val statement = DB.prepareStatement(s"SELECT DISTINCT DateInvoiced FROM C_Invoice WHERE AD_Client_ID=$ClientId ORDER BY 1", null)
    val days = ArrayBuffer.empty[Timestamp]
    try {
      val resultSet = statement.executeQuery()
      try while (resultSet.next()) days += resultSet.getTimestamp(1)
      finally resultSet.close()
    } finally statement.close()
    days.toVector
  }

  Feature("The bound form returns the same rows as the literal form on GardenWorld") {

    Scenario("invoices by date, partner, amount, document status and type") {
      val days = invoiceDays
      assert(days.size >= 6, "GardenWorld has too few invoice dates for the range checks")
      val query = newQuery("C_Invoice")
      query.addRestriction("AD_Client_ID", MQuery.EQUAL, Integer.valueOf(ClientId))
      query.addRangeRestriction("DateInvoiced", days(1), days(4))
      query.addRestriction("DateAcct", MQuery.GREATER_EQUAL, days(1))
      query.addRestriction("DocStatus", MQuery.NOT_EQUAL, "DR")
      query.addRestriction("IsSOTrx", MQuery.EQUAL, java.lang.Boolean.TRUE)
      query.addRestriction("GrandTotal", MQuery.GREATER_EQUAL, java.lang.Double.valueOf(0.0))
      query.addRestriction("Description", MQuery.EQUAL, nullCode, null, null, false, 0)
      val rows = assertSameRows("C_Invoice", "C_Invoice_ID", query, "invoices")
      val all = literalKeys("C_Invoice", "C_Invoice_ID", "AD_Client_ID=" + ClientId)
      assert(rows.nonEmpty && rows.size < all.size, s"the filter returns ${rows.size} of ${all.size} invoices; the check would prove nothing")
    }

    Scenario("business partners by name with LIKE, a quote in the value and an OR group") {
      val query = newQuery("C_BPartner")
      query.addRestriction("AD_Client_ID", MQuery.EQUAL, Integer.valueOf(ClientId))
      query.addRestriction("Name", MQuery.LIKE, "J%", null, null, true, 1)
      query.addRestriction("Name", MQuery.LIKE, "%o'%", null, null, false, 1)
      query.addRestriction("C_BPartner.IsActive='Y'")
      val rows = assertSameRows("C_BPartner", "C_BPartner_ID", query, "partners by name")
      assert(rows.nonEmpty, "no partner name starts with J")
    }

    Scenario("orders by partner and date, nested") {
      val query = newQuery("C_Order")
      query.addRestriction("AD_Client_ID", MQuery.EQUAL, Integer.valueOf(ClientId))
      query.addRestriction("C_BPartner_ID", MQuery.EQUAL, Integer.valueOf(117), null, null, true, 1)
      query.addRestriction("DateOrdered", MQuery.LESS_EQUAL, timestamp("2030-01-01 00:00:00"), null, null, false, 1)
      query.addRestriction("IsSOTrx", MQuery.EQUAL, java.lang.Boolean.FALSE)
      assertSameRows("C_Order", "C_Order_ID", query, "orders")
    }
  }

  /**
    * MQuery.get of a process instance with the given parameters. MQuery.get reads the instance without a transaction,
    * so the rows are committed and deleted when the query is built.
    */
  private def queryOf(processId: Int, parameters: (MPInstancePara => Unit)*): MQuery = {
    val instance = new MPInstance(Env.getCtx, processId, 0)
    instance.saveEx()
    val id = Integer.valueOf(instance.getAD_PInstance_ID)
    try {
      parameters.zipWithIndex.foreach { case (set, index) =>
        val para = new MPInstancePara(instance, (index + 1) * 10)
        set(para)
        para.saveEx()
      }
      MQuery.get(Env.getCtx, instance.getAD_PInstance_ID, "C_Invoice")
    } finally {
      DB.executeUpdateEx("DELETE FROM AD_PInstance_Para WHERE AD_PInstance_ID=?", Array[AnyRef](id), null)
      DB.executeUpdateEx("DELETE FROM AD_PInstance WHERE AD_PInstance_ID=?", Array[AnyRef](id), null)
    }
  }

  private def dateRange(name: String, from: Option[Timestamp], to: Option[Timestamp]): MPInstancePara => Unit = para => {
    para.setParameterName(name)
    from.foreach(para.setP_Date)
    to.foreach(para.setP_Date_To)
  }

  private def numberPara(name: String, value: Int, isTo: Boolean = false): MPInstancePara => Unit = para => {
    para.setParameterName(name)
    if (isTo) para.setP_Number_To(value) else para.setP_Number(value)
  }

  private def stringPara(name: String, value: String): MPInstancePara => Unit = para => {
    para.setParameterName(name)
    para.setP_String(value)
  }

  private def day(time: Timestamp): String = time.toString.substring(0, 10)

  /** The invoices with a time of day, so that the last day of a range is not only midnight. */
  private val InvoicesWithTime =
    s"(SELECT C_Invoice_ID, C_BPartner_ID, DocStatus, DateInvoiced + INTERVAL '10 hours 30 minutes' AS DateInvoiced " +
      s"FROM C_Invoice WHERE AD_Client_ID=$ClientId) C_Invoice"

  /** Both forms return the same invoices, on the table and on the invoices with a time of day. Returns the latter. */
  private def assertSameInvoices(query: MQuery, label: String): Vector[Int] = {
    val clause = bound(query, true)
    assert(clause.placeholders == clause.parameters.size, s"$label: ${clause.placeholders} placeholders, ${clause.parameters.size} values")
    Seq("C_Invoice", InvoicesWithTime).map { source =>
      val expected = literalKeys(source, "C_Invoice_ID", literal(query, true))
      val actual = boundKeys(source, "C_Invoice_ID", clause)
      assert(actual == expected, s"$label on $source: bound $actual, literal $expected")
      actual
    }.last
  }

  Feature("MQuery.get from a process instance") {

    Scenario("a date range is half open and includes the last day with a time of day") {
      Given("a process instance with a date range")
      val days = invoiceDays
      val (from, to) = (days(1), days(4))
      val query = queryOf(InvoiceProcess, dateRange("DateInvoiced", Some(from), Some(to)))
      Then("the literal text keeps TRUNC")
      assert(literal(query, true) == s"(TRUNC(C_Invoice.DateInvoiced, 'DD') BETWEEN TO_DATE('${day(from)}','YYYY-MM-DD') AND TO_DATE('${day(to)}','YYYY-MM-DD'))")
      And("the bound text compares the plain column with the start of the first day and the start of the day after the last")
      val nextDay = Timestamp.valueOf(to.toLocalDateTime.toLocalDate.plusDays(1).atStartOfDay)
      assert(bound(query, true) == Bound("(C_Invoice.DateInvoiced >= ? AND C_Invoice.DateInvoiced < ?)", Vector(from, nextDay)))
      assert(bound(query, false).sql == "(DateInvoiced >= ? AND DateInvoiced < ?)")
      And("the rows are the same, with the invoices of the last day, that have 10:30 as time")
      val rows = assertSameInvoices(query, "range")
      val untilMidnight = literalKeys(InvoicesWithTime, "C_Invoice_ID", s"C_Invoice.DateInvoiced<=TO_DATE('${day(to)}','YYYY-MM-DD') AND C_Invoice.DateInvoiced>=TO_DATE('${day(from)}','YYYY-MM-DD')")
      assert(rows.nonEmpty && rows.size > untilMidnight.size, "no invoice of the last day with a time of day; the check would prove nothing")
    }

    Scenario("a date from, a date to and a single date") {
      val days = invoiceDays
      val from = days(2)
      val to = days(3)
      val nextTo = Timestamp.valueOf(to.toLocalDateTime.toLocalDate.plusDays(1).atStartOfDay)
      val nextFrom = Timestamp.valueOf(from.toLocalDateTime.toLocalDate.plusDays(1).atStartOfDay)

      When("only the first date of a range parameter is set")
      val fromQuery = queryOf(InvoiceProcess, dateRange("DateInvoiced", Some(from), None))
      assert(literal(query = fromQuery, fullyQualified = true) == s"(TRUNC(C_Invoice.DateInvoiced, 'DD')>=TO_DATE('${day(from)}','YYYY-MM-DD'))")
      assert(bound(fromQuery, true) == Bound("(C_Invoice.DateInvoiced >= ?)", Vector(from)))
      assertSameInvoices(fromQuery, "from")

      When("only the last date is set")
      val toQuery = queryOf(InvoiceProcess, dateRange("DateInvoiced", None, Some(to)))
      assert(literal(toQuery, true) == s"(TRUNC(C_Invoice.DateInvoiced, 'DD')<=TO_DATE('${day(to)}','YYYY-MM-DD'))")
      assert(bound(toQuery, true) == Bound("(C_Invoice.DateInvoiced < ?)", Vector(nextTo)))
      assertSameInvoices(toQuery, "to")

      When("a single date of a parameter without range is set")
      val singleQuery = queryOf(SingleDateProcess, dateRange("DateInvoiced", Some(from), None))
      assert(literal(singleQuery, true) == s"(TRUNC(C_Invoice.DateInvoiced, 'DD')=TO_DATE('${day(from)}','YYYY-MM-DD'))")
      assert(bound(singleQuery, true) == Bound("(C_Invoice.DateInvoiced >= ? AND C_Invoice.DateInvoiced < ?)", Vector(from, nextFrom)))
      val rows = assertSameInvoices(singleQuery, "single")
      assert(rows.nonEmpty, "no invoice on the single date; the check would prove nothing")
    }

    Scenario("number parameters keep 101.0 in the literal and bind exact numbers") {
      val equalQuery = queryOf(InvoiceProcess, numberPara("C_BPartner_ID", 114))
      assert(literal(equalQuery, true) == "(C_Invoice.C_BPartner_ID=114.0)")
      val equalBound = bound(equalQuery, true)
      assert(equalBound.sql == "(C_Invoice.C_BPartner_ID=?)")
      assert(equalBound.parameters.map { case value: BigDecimal => value.compareTo(number("114")) == 0; case _ => false } == Vector(true))
      assert(assertSameInvoices(equalQuery, "partner").nonEmpty)

      When("only the number to is set, the column stays under TRUNC: it is a number, not a date")
      val toQuery = queryOf(InvoiceProcess, numberPara("C_Invoice_ID", 103, isTo = true))
      assert(literal(toQuery, true) == "(TRUNC(C_Invoice.C_Invoice_ID)<=103.0)")
      val toBound = bound(toQuery, true)
      assert(toBound.sql == "(TRUNC(C_Invoice.C_Invoice_ID)<=?)")
      assert(toBound.parameters.size == 1 && toBound.parameters.head.isInstanceOf[BigDecimal])
      assert(assertSameInvoices(toQuery, "number to").nonEmpty)
    }

    Scenario("string parameters bind, with equal and like") {
      val equalQuery = queryOf(InvoiceProcess, stringPara("DocStatus", "CO"))
      assert(literal(equalQuery, true) == "(C_Invoice.DocStatus='CO')")
      assert(bound(equalQuery, true) == Bound("(C_Invoice.DocStatus=?)", Vector("CO")))
      assert(assertSameInvoices(equalQuery, "equal").nonEmpty)
      val likeQuery = queryOf(InvoiceProcess, stringPara("DocStatus", "C%"))
      assert(literal(likeQuery, true) == "(C_Invoice.DocStatus LIKE 'C%')")
      assert(bound(likeQuery, true) == Bound("(C_Invoice.DocStatus LIKE ?)", Vector("C%")))
      assert(assertSameInvoices(likeQuery, "like").nonEmpty)
    }

    Scenario("all kinds of parameters together return the same rows") {
      val days = invoiceDays
      val query = queryOf(InvoiceProcess,
        dateRange("DateInvoiced", Some(days(0)), Some(days(5))),
        numberPara("C_BPartner_ID", 114),
        stringPara("DocStatus", "CO"),
        numberPara("C_Invoice_ID", 200000, isTo = true))
      val clause = bound(query, true)
      assert(clause.parameters.size == 5, s"$clause")
      assert(literal(query, true).contains("TRUNC(C_Invoice.DateInvoiced, 'DD')") && literal(query, true).contains("114.0"))
      assert(!clause.sql.contains("DateInvoiced, 'DD'") && !clause.sql.contains("114"))
      assertSameInvoices(query, "all parameters")
    }
  }
}
