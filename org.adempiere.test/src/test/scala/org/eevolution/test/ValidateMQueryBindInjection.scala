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

import java.lang.reflect.{InvocationHandler, Method, Proxy}
import java.util.regex.Pattern

import org.adempiere.model.{IDocumentStatus, MDocumentStatus}
import org.compiere.model.{FindCount, GridTab, GridWindow, GridWindowVO, MProduct, MQuery, MRole}
import org.compiere.print.{DataEngine, MPrintFormat}
import org.compiere.util.{DB, Env, Language}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.collection.mutable.ArrayBuffer
import scala.jdk.CollectionConverters._
import scala.util.{Failure, Success, Try}

/** Does a value entered by the user stay data when the MQuery restriction is bound?
  *
  * Every consumer path gets harmless SELECT-tautology payloads (quotes, comment marker, backslash, double quote, unicode
  * quotes) as the value of a String restriction. For each path the bound statement must not fail, must return exactly the
  * rows that literally match the payload (the Scala side computes them from the data, normally none) and never all the
  * rows, and the table must keep its row count. The legacy literal path (`getWhereClause`) is rendered and executed for
  * the same payload, to show what it did before F1.
  *
  * The last scenarios are informational: they show the surfaces that F1 keeps as raw SQL text by design and whether a
  * value of the user can still reach the SQL text there. They assert nothing about that and only read.
  */
class ValidateMQueryBindInjection extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val transactionName: String = null
  private def context = Env.getCtx
  private val productWindowId = 140
  private val productPrintFormatId = 1000000 // GardenWorld -> Product, M_Product
  private val windowNumber = 8100
  private val probeWindowNumber = 8101

  private lazy val productWindow: GridWindow = {
    val windowVO = GridWindowVO.create(context, windowNumber, productWindowId)
    if (windowVO == null) fail(s"The window $productWindowId cannot be created")
    val window = new GridWindow(windowVO)
    window.initTab(0)
    window
  }

  private def productTab: GridTab = productWindow.getTab(0)

  override def afterAll(): Unit = {
    productWindow.dispose()
    super.afterAll()
  }

  /** Name and Value of every product: the Scala side of the expected rows */
  private lazy val products: Vector[(String, String)] = {
    val statement = DB.prepareStatement("SELECT Name, Value FROM M_Product ORDER BY M_Product_ID", transactionName)
    try {
      val resultSet = statement.executeQuery()
      try {
        val rows = ArrayBuffer.empty[(String, String)]
        while (resultSet.next()) rows += ((resultSet.getString(1), resultSet.getString(2)))
        rows.toVector
      } finally resultSet.close()
    } finally statement.close()
  }

  private def productCount: Int = DB.getSQLValue(transactionName, "SELECT COUNT(*) FROM M_Product")

  private def standardConformingStrings: String = {
    val statement = DB.createStatement()
    try {
      val resultSet = statement.executeQuery("SHOW standard_conforming_strings")
      try { resultSet.next(); resultSet.getString(1) } finally resultSet.close()
    } finally statement.close()
  }

  /** SQL LIKE pattern as a regular expression: `%` any text, `_` one character, `\` escapes the next character */
  private def likePattern(pattern: String): Pattern = {
    val regex = new StringBuilder
    var index = 0
    while (index < pattern.length) {
      pattern.charAt(index) match {
        case '\\' if index + 1 < pattern.length => index += 1; regex.append(Pattern.quote(pattern.charAt(index).toString))
        case '%' => regex.append(".*")
        case '_' => regex.append(".")
        case other => regex.append(Pattern.quote(other.toString))
      }
      index += 1
    }
    Pattern.compile(regex.toString, Pattern.DOTALL)
  }

  /** Harmless payloads: a SELECT tautology or a comment marker, never DML or DDL */
  private val payloads = Vector(
    "quote tautology" -> "x' OR '1'='1",
    "wildcard, tautology, comment" -> "%' OR '1'='1' --",
    "closing parenthesis, comment" -> "'); --",
    "backslash" -> "a\\b",
    "backslash before a quote" -> "x\\' OR 1=1 --",
    "double quote" -> "say \"hi\" OR \"1\"=\"1",
    "unicode quote" -> "x\u2019 OR \u20191\u2019=\u20191",
    "modifier letter apostrophe" -> "x\u02BC OR \u02BC1\u02BC=\u02BC1")

  private def likeUpperQuery(value: String): MQuery = {
    val query = new MQuery("M_Product")
    query.addRestriction("UPPER(Name)", MQuery.LIKE, value.toUpperCase)
    query
  }

  private def likeQuery(value: String): MQuery = {
    val query = new MQuery("M_Product")
    query.addRestriction("Name", MQuery.LIKE, value)
    query
  }

  private def equalQuery(value: String): MQuery = {
    val query = new MQuery("M_Product")
    query.addRestriction("Value", MQuery.EQUAL, value)
    query
  }

  private def zoomQuery(value: String): MQuery = MQuery.getEqualQuery("Value", value)

  private def findCountRows(query: MQuery): Int = {
    val statement = FindCount.getStatement(context, productTab.getWindowNo, MRole.getDefault(context, false),
      "M_Product", productTab.getWhereExtended, query)
    FindCount.count(statement)
  }

  private def gridTabRows(query: MQuery): Int = {
    productTab.setQuery(query)
    productTab.query(false)
    productTab.getRowCount
  }

  private def reportRows(query: MQuery): Int = {
    val format = MPrintFormat.get(context, productPrintFormatId, true)
    new DataEngine(Language.getLanguage("en_US"), transactionName).getPrintData(context, format, query, false, 0).getRowCount
  }

  private def zoomLinkRows(query: MQuery): Int =
    if (FindCount.getLinkValue("M_Product_ID", "M_Product", query) > 0) 1 else 0

  private def expectedLikeUpper(value: String): Int = {
    val pattern = likePattern(value.toUpperCase)
    products.count { case (name, _) => name != null && pattern.matcher(name.toUpperCase).matches() }
  }

  private def expectedLike(value: String): Int = {
    val pattern = likePattern(value)
    products.count { case (name, _) => name != null && pattern.matcher(name).matches() }
  }

  private def expectedEqual(value: String): Int = products.count { case (_, productValue) => productValue == value }

  /** A consumer path: what it runs for a value, the rows the data holds for it and the MQuery of the legacy literal path */
  private case class Probe(path: String, shape: String, run: MQuery => Int, build: String => MQuery, expected: String => Int)

  private val probes = Vector(
    Probe("FindCount", "UPPER(Name) LIKE", findCountRows, likeUpperQuery, expectedLikeUpper),
    Probe("GridTab.query", "UPPER(Name) LIKE", gridTabRows, likeUpperQuery, expectedLikeUpper),
    Probe("FindCount", "Value =", findCountRows, equalQuery, expectedEqual),
    Probe("GridTab.query", "Value =", gridTabRows, equalQuery, expectedEqual),
    Probe("DataEngine report", "Name LIKE", reportRows, likeQuery, expectedLike),
    Probe("DataEngine report", "Value =", reportRows, equalQuery, expectedEqual),
    Probe("GridTab.query zoom", "getEqualQuery(Value)", gridTabRows, zoomQuery, expectedEqual),
    Probe("FindCount.getLinkValue zoom", "getEqualQuery(Value)", zoomLinkRows, zoomQuery, expectedEqual))

  /** Rows of the legacy literal statement: `getWhereClause(true)` with the role access, through a plain statement */
  private def literalRows(query: MQuery): (String, Try[Int]) = {
    val where = Try(query.getWhereClause(true))
    where match {
      case Failure(exception) => ("<exception>", Failure(exception))
      case Success(text) =>
        val rows = Try {
          val sql = MRole.getDefault(context, false).addAccessSQL(
            s"SELECT COUNT(*) FROM M_Product WHERE $text", "M_Product", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO)
          val statement = DB.createStatement()
          try {
            val resultSet = statement.executeQuery(sql)
            try { resultSet.next(); resultSet.getInt(1) } finally resultSet.close()
          } finally statement.close()
        }
        (text, rows)
    }
  }

  private case class Finding(payloadLabel: String, payload: String, probe: Probe, bound: Try[Int], expected: Int,
                             literalText: String, literal: Try[Int], isTableUnchanged: Boolean)

  private val findings = ArrayBuffer.empty[Finding]

  private def exercise(payloadLabel: String, payload: String): Seq[Finding] =
    probes.map { probe =>
      val before = productCount
      val bound = Try(probe.run(probe.build(payload)))
      val (literalText, literal) = literalRows(probe.build(payload))
      Finding(payloadLabel, payload, probe, bound, probe.expected(payload), literalText, literal, productCount == before)
    }

  private def describe(finding: Finding): String = {
    val bound = finding.bound.fold(exception => s"EXCEPTION ${exception.getClass.getSimpleName}", rows => rows.toString)
    val literal = finding.literal.fold(exception => s"EXCEPTION ${exception.getClass.getSimpleName}", rows => rows.toString)
    f"INJECTION payload=[${finding.payload}] path=${finding.probe.path}%-28s shape=${finding.probe.shape}%-20s bound=$bound%-3s expected=${finding.expected}%-3d literal=$literal%-3s exception=${finding.bound.isFailure}%-5s tableUnchanged=${finding.isTableUnchanged}%-5s literalSql=[${finding.literalText}]"
  }

  Feature("A bound MQuery restriction keeps the value of the user as data") {
    payloads.foreach { case (label, payload) =>
      Scenario(s"Payload '$label' returns only the rows that literally match, on every consumer path") {
        Given(s"the payload [$payload] as the value of a String restriction")
        val total = products.size
        assert(total > 0, "M_Product has no row; the check would prove nothing")
        When("each consumer path runs the bound statement")
        val results = exercise(label, payload)
        findings ++= results
        results.foreach(result => println(describe(result)))
        Then("no path fails, none returns all the rows, and each returns the literal matches")
        results.foreach { result =>
          val name = s"${result.probe.path} ${result.probe.shape}"
          assert(result.bound.isSuccess, s"$name: ${result.bound.failed.map(_.getMessage).getOrElse("")}")
          assert(result.bound.get == result.expected, s"$name: ${result.bound.get} rows, expected ${result.expected}")
          assert(result.bound.get < total, s"$name: returned all $total rows")
          assert(result.isTableUnchanged, s"$name: M_Product changed its row count")
        }
        info(s"[$payload]: ${results.size} paths, bound rows ${results.map(_.bound.get).distinct.mkString(",")}, M_Product rows $total unchanged")
      }
    }

    Scenario("The paths do return rows for a value that exists, so the zero rows above are not an artifact") {
      val (name, value) = products.find { case (productName, productValue) => productName != null && productName.length >= 3 && productValue != null }.get
      val likeValue = name.take(3) + "%"
      Given(s"the existing product '$name' with value '$value'")
      val checks = probes.map { probe =>
        val input = if (probe.shape.contains("LIKE")) likeValue else value
        val expected = probe.expected(input)
        val bound = Try(probe.run(probe.build(input)))
        info(f"${probe.path}%-28s ${probe.shape}%-20s input [$input] bound=$bound expected=$expected")
        (probe, expected, bound)
      }
      checks.foreach { case (probe, expected, bound) =>
        assert(expected > 0, s"${probe.path}: the data holds no row for the control value")
        assert(bound.isSuccess && bound.get == expected, s"${probe.path} ${probe.shape}: $bound <> $expected")
      }
    }

    Scenario("The legacy literal path, for the same payloads: what it renders and whether it returns the same rows") {
      val setting = standardConformingStrings
      println(s"INJECTION standard_conforming_strings=$setting")
      info(s"standard_conforming_strings=$setting")
      val differences = ArrayBuffer.empty[String]
      payloads.foreach { case (label, payload) =>
        val rendered = exercise(label, payload)
        rendered.foreach { result =>
          val isSame = result.literal.isSuccess && result.bound.isSuccess && result.literal.get == result.bound.get
          if (!isSame) differences += s"[$payload] ${result.probe.path} ${result.probe.shape}: bound=${result.bound} literal=${result.literal}"
        }
        val sample = rendered.find(_.probe.shape == "Value =").get
        println(s"INJECTION legacy [${payload}] renders where: ${sample.literalText}")
        info(s"legacy [$payload] -> ${sample.literalText}")
      }
      differences.foreach(difference => println("INJECTION legacy-difference " + difference))
      info(s"${differences.size} of ${payloads.size * probes.size} bound/literal comparisons differ")
    }
  }

  // ---- informational: the surfaces that stay raw text by design ----

  private def countWhere(where: String): Try[Int] = Try {
    val statement = DB.createStatement()
    try {
      val resultSet = statement.executeQuery(s"SELECT COUNT(*) FROM M_Product WHERE $where")
      try { resultSet.next(); resultSet.getInt(1) } finally resultSet.close()
    } finally statement.close()
  }

  private def report(surface: String, template: String, rendered: String, count: Try[Int], total: Int): Unit = {
    val reachesText = rendered.contains("' OR '1'='1")
    val line = s"RAW surface=[$surface] template=[$template] rendered=[$rendered] rows=${count.fold(_.getClass.getSimpleName, _.toString)} of $total " +
      s"valueReachesSqlText=$reachesText ${if (count.isSuccess && count.get == total) "(the tautology matched every row)" else ""}"
    println(line)
    info(line)
  }

  Feature("Surfaces that F1 keeps as raw SQL text (informational, no failing assertion)") {
    val payload = "x' OR '1'='1"

    Scenario("Env.parseContext substitutes a context value into a dictionary WHERE clause as text") {
      val total = products.size
      Env.setContext(context, probeWindowNumber, "Name", payload)
      Env.setContext(context, "#F1InjectionProbe", payload)
      try {
        val window = Env.parseContext(context, probeWindowNumber, "Name='@Name@'", false)
        report("Env.parseContext window value", "Name='@Name@'", window, countWhere(window), total)
        val global = Env.parseContext(context, 0, "Name='@#F1InjectionProbe@'", true)
        report("Env.parseContext global # value", "Name='@#F1InjectionProbe@'", global, countWhere(global), total)
      } finally {
        Env.clearWinContext(context, probeWindowNumber)
        Env.setContext(context, "#F1InjectionProbe", null.asInstanceOf[String])
      }
    }

    Scenario("Env.parseVariable substitutes a value of the record into the text") {
      val total = products.size
      val product = new MProduct(context, 0, null) // in memory, never saved
      product.setName(payload)
      val rendered = Env.parseVariable("Name='@Name@'", product, null, false)
      report("Env.parseVariable record value", "Name='@Name@'", rendered, countWhere(rendered), total)
    }

    Scenario("addRestriction(String) and the zoom restriction GenericZoomProvider builds are raw text") {
      val total = products.size
      val raw = new MQuery("M_Product")
      raw.addRestriction("Name='" + payload + "'")
      val bound = raw.getBoundWhereClause(false)
      info(s"addRestriction(String): sql=[${bound.getSql}] parameters=${bound.getParameters.asScala.toVector}")
      report("MQuery.addRestriction(String)", "\"Name='\" + value + \"'\"", bound.getSql, countWhere(bound.getSql), total)
      // GenericZoomProvider.evaluateQuery: the tab where clause is parsed with the values of the record, then added raw
      val product = new MProduct(context, 0, null)
      product.setName(payload)
      val tabWhere = Env.parseVariable("Name='@Name@'", product, null, true)
      val zoom = new MQuery("M_Product")
      zoom.addRestriction("(" + tabWhere + ")")
      val zoomBound = zoom.getBoundWhereClause(false)
      report("GenericZoomProvider restriction (replica of evaluateQuery)", "(Name='@Name@') as tab where clause", zoomBound.getSql, countWhere(zoomBound.getSql), total)
    }

    Scenario("MDocumentStatus.getWhereClause parses the global context of the user into the dictionary text") {
      val total = products.size
      Env.setContext(context, "#F1InjectionProbe", payload)
      try {
        val handler = new InvocationHandler {
          override def invoke(proxy: Any, method: Method, arguments: Array[AnyRef]): AnyRef = method.getName match {
            case "getAD_Client_ID" => Integer.valueOf(Env.getAD_Client_ID(context))
            case "getWhereClause" => "Name='@#F1InjectionProbe@'"
            case _ if method.getReturnType == java.lang.Integer.TYPE => Integer.valueOf(0)
            case _ => null
          }
        }
        val documentStatus = Proxy.newProxyInstance(getClass.getClassLoader, Array[Class[_]](classOf[IDocumentStatus]), handler).asInstanceOf[IDocumentStatus]
        val rendered = MDocumentStatus.getWhereClause(documentStatus)
        report("MDocumentStatus.getWhereClause", "Name='@#F1InjectionProbe@'", rendered.trim, countWhere(rendered), total)
      } finally Env.setContext(context, "#F1InjectionProbe", null.asInstanceOf[String])
    }

    Scenario("How many dictionary rows hold @variables@, and how many of those variables are String or Text columns") {
      val sql =
        """WITH tokens AS (
          |  SELECT 'AD_Tab.WhereClause' AS source, m[1] AS token FROM AD_Tab, regexp_matches(WhereClause, '@([^@]+)@', 'g') m
          |  UNION ALL SELECT 'AD_Val_Rule.Code', m[1] FROM AD_Val_Rule, regexp_matches(Code, '@([^@]+)@', 'g') m
          |  UNION ALL SELECT 'AD_Ref_Table.WhereClause', m[1] FROM AD_Ref_Table, regexp_matches(WhereClause, '@([^@]+)@', 'g') m
          |  UNION ALL SELECT 'PA_DocumentStatus.WhereClause', m[1] FROM PA_DocumentStatus, regexp_matches(WhereClause, '@([^@]+)@', 'g') m)
          |SELECT source, COUNT(*), COUNT(*) FILTER (WHERE token !~ '^#'),
          |  COUNT(*) FILTER (WHERE token !~ '^#' AND EXISTS (SELECT 1 FROM AD_Column c WHERE c.ColumnName = split_part(tokens.token, '|', 1) AND c.AD_Reference_ID IN (10, 14)))
          |FROM tokens GROUP BY source ORDER BY source""".stripMargin
      val statement = DB.createStatement()
      try {
        val resultSet = statement.executeQuery(sql)
        try while (resultSet.next()) {
          val line = s"RAW dictionary ${resultSet.getString(1)}: variables=${resultSet.getInt(2)} notGlobal=${resultSet.getInt(3)} onStringOrTextColumn=${resultSet.getInt(4)}"
          println(line)
          info(line)
        } finally resultSet.close()
      } finally statement.close()
    }
  }
}
