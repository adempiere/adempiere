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

import org.adempiere.exceptions.DBException
import org.adempiere.model.{GenericZoomProvider, MRelationType}
import org.compiere.model.{MOrder, MBPartner, MInvoice, MQuery, PO, Query}
import org.compiere.util.{DB, Env}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.jdk.CollectionConverters._

/** The zoom family runs the restrictions of the zoom `MQuery` with bind parameters: `DB.isSOTrx` (the PO window of the
  * zoom), the zoom across (`Query`) and the relation types. The generic zoom provider builds raw restrictions only, so
  * it keeps the literal text.
  *
  * The reference of every check is the statement of before: the same restriction written as literal text with
  * `MQuery.getWhereClause(false)`.
  */
class ValidateZoomBindParameters extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private def check(condition: Boolean, message: => String = "assertion failed"): Unit =
    if (!condition) fail(message)

  private def keyOf(sql: String): Int = DB.getSQLValue(null, sql)

  /** The zoom queries: a sales and a purchase order and an order line of each, with the key as an Integer and as a text (R3) */
  private def zoomQueries: Seq[(String, String, MQuery)] = {
    val salesOrder = keyOf("SELECT MIN(C_Order_ID) FROM C_Order WHERE IsSOTrx='Y'")
    val purchaseOrder = keyOf("SELECT MIN(C_Order_ID) FROM C_Order WHERE IsSOTrx='N'")
    val salesLine = keyOf("SELECT MIN(l.C_OrderLine_ID) FROM C_OrderLine l INNER JOIN C_Order o ON o.C_Order_ID=l.C_Order_ID WHERE o.IsSOTrx='Y'")
    val purchaseLine = keyOf("SELECT MIN(l.C_OrderLine_ID) FROM C_OrderLine l INNER JOIN C_Order o ON o.C_Order_ID=l.C_Order_ID WHERE o.IsSOTrx='N'")
    check(salesOrder > 0 && purchaseOrder > 0 && salesLine > 0 && purchaseLine > 0, "GardenWorld has no sales and purchase orders")
    Seq(
      ("sales order, Integer", "C_Order", MQuery.getEqualQuery("C_Order_ID", Integer.valueOf(salesOrder))),
      ("purchase order, Integer", "C_Order", MQuery.getEqualQuery("C_Order_ID", Integer.valueOf(purchaseOrder))),
      ("sales order, String (R3)", "C_Order", MQuery.getEqualQuery("C_Order_ID", salesOrder.toString)),
      ("purchase order, String (R3)", "C_Order", MQuery.getEqualQuery("C_Order_ID", purchaseOrder.toString)),
      ("sales order line, Integer", "C_OrderLine", MQuery.getEqualQuery("C_OrderLine_ID", Integer.valueOf(salesLine))),
      ("purchase order line, String (R3)", "C_OrderLine", MQuery.getEqualQuery("C_OrderLine_ID", purchaseLine.toString)))
  }

  Feature("DB.isSOTrx binds the values of the zoom query") {

    Scenario("the bound overload gives the same answer as the literal text") {
      var salesAnswers = 0
      var purchaseAnswers = 0
      zoomQueries.foreach { case (label, tableName, query) =>
        val literal = DB.isSOTrx(tableName, query.getWhereClause(false))
        val bound = query.getBoundWhereClause(false)
        check(bound.getParameters.size == 1 && bound.getSql.contains("?"), s"$label: $bound")
        Then(s"$label: the same answer")
        check(DB.isSOTrx(tableName, bound) == literal, s"$label: bound ${DB.isSOTrx(tableName, bound)}, literal $literal")
        if (literal) salesAnswers += 1 else purchaseAnswers += 1
      }
      And("both answers happen, so the check is not trivial")
      check(salesAnswers >= 2 && purchaseAnswers >= 2, s"sales $salesAnswers, purchase $purchaseAnswers")
      check(!DB.isSOTrx("C_Order", MQuery.getEqualQuery("C_Order_ID", keyOf("SELECT MIN(C_Order_ID) FROM C_Order WHERE IsSOTrx='N'").toString).getBoundWhereClause(false)))
    }

    Scenario("the guards and the table without IsSOTrx are as the literal ones") {
      val query = MQuery.getEqualQuery("C_BPartner_ID", Integer.valueOf(keyOf("SELECT MIN(C_BPartner_ID) FROM C_BPartner")))
      check(DB.isSOTrx("C_BPartner", query.getBoundWhereClause(false)) == DB.isSOTrx("C_BPartner", query.getWhereClause(false)))
      check(DB.isSOTrx("C_Order", new MQuery("C_Order").getBoundWhereClause(false)))
      check(DB.isSOTrx(null, query.getBoundWhereClause(false)))
      check(DB.isSOTrx("C_Order", null.asInstanceOf[MQuery.BoundWhereClause]))
    }

    Scenario("the literal overload is unchanged") {
      val purchaseOrder = keyOf("SELECT MIN(C_Order_ID) FROM C_Order WHERE IsSOTrx='N'")
      check(!DB.isSOTrx("C_Order", s"C_Order_ID=$purchaseOrder"))
      check(DB.isSOTrx("C_Order", ""))
    }
  }

  Feature("The zoom across finds the record with bind parameters") {

    Scenario("Query with the bound where clause returns the record of the literal Query") {
      zoomQueries.filter(_._2 == "C_Order").foreach { case (label, tableName, query) =>
        val literal = new Query(Env.getCtx, tableName, query.getWhereClause, null).first[PO]()
        val bound = query.getBoundWhereClause(false)
        val found = new Query(Env.getCtx, tableName, bound.getSql, null).setQueryParameters(bound.getParameters).first[PO]()
        check(literal != null, s"$label: the literal reference finds nothing")
        check(found != null && found.get_ID == literal.get_ID, s"$label: bound $found, literal $literal")
      }
    }

    Scenario("the zoom across of Order, Invoice and Business Partner keeps the record") {
      val orderId = keyOf("SELECT MIN(C_Order_ID) FROM C_Order WHERE IsSOTrx='Y'")
      val partnerId = keyOf("SELECT MIN(C_BPartner_ID) FROM C_BPartner")
      val invoiceId = keyOf("SELECT MIN(C_Invoice_ID) FROM C_Invoice")
      Seq(("C_Order", "C_Order_ID", orderId), ("C_BPartner", "C_BPartner_ID", partnerId), ("C_Invoice", "C_Invoice_ID", invoiceId)).foreach {
        case (tableName, columnName, id) =>
          val query = MQuery.getEqualQuery(columnName, Integer.valueOf(id))
          val bound = query.getBoundWhereClause(false)
          val po = new Query(Env.getCtx, tableName, bound.getSql, null).setQueryParameters(bound.getParameters).first[PO]()
          check(po != null && po.get_ID == id, s"$tableName: $po")
      }
    }

    Scenario("a String on a numeric column needs the untyped binder (R3)") {
      val orderId = keyOf("SELECT MIN(C_Order_ID) FROM C_Order")
      val query = MQuery.getEqualQuery("C_Order_ID", orderId.toString)
      val bound = query.getBoundWhereClause(false)
      val found = new Query(Env.getCtx, "C_Order", bound.getSql, null).setQueryParameters(bound.getParameters).first[PO]()
      check(found != null && found.get_ID == orderId, s"bound with the untyped binder: $found")
      if (DB.isPostgreSQL) {
        And("Query.setParameters, typed, would fail on PostgreSQL: the reason of the opt-in")
        intercept[DBException](new Query(Env.getCtx, "C_Order", bound.getSql, null).setParameters(bound.getParameters.toArray: _*).first[PO]())
      }
    }

    Scenario("Query without the opt-in is unchanged") {
      val orderId = keyOf("SELECT MIN(C_Order_ID) FROM C_Order")
      val found = new Query(Env.getCtx, "C_Order", "C_Order_ID=?", null).setParameters(Integer.valueOf(orderId)).first[PO]()
      check(found != null && found.get_ID == orderId)
      val other = new Query(Env.getCtx, "C_Order", "C_Order_ID=? AND IsActive=?", null).setParameters(Integer.valueOf(orderId), "Y").first[PO]()
      check(other != null && other.get_ID == orderId)
    }
  }

  Feature("The relation types evaluate their query with bind parameters") {

    Scenario("count and first key equal the literal statements") {
      var compared = 0
      var withRecords = 0
      val pos: Seq[PO] =
        DB.getIDsEx(null, "SELECT C_Order_ID FROM C_Order ORDER BY C_Order_ID").toSeq.map(id => new MOrder(Env.getCtx, id, null): PO) ++
          DB.getIDsEx(null, "SELECT C_Invoice_ID FROM C_Invoice ORDER BY C_Invoice_ID").toSeq.map(id => new MInvoice(Env.getCtx, id, null): PO) ++
          DB.getIDsEx(null, "SELECT C_BPartner_ID FROM C_BPartner ORDER BY C_BPartner_ID").toSeq.take(5).map(id => new MBPartner(Env.getCtx, id, null): PO)
      pos.foreach { po =>
        MRelationType.retrieveZoomInfos(po, 0).asScala.foreach { zoomInfo =>
          val query = zoomInfo.query
          val where = query.getWhereClause(false)
          if (where.nonEmpty) {
            val common = " FROM " + query.getZoomTableName + " WHERE " + where
            val count = DB.getSQLValueEx(null, "SELECT COUNT(*)" + common)
            check(query.getRecordCount == count, s"${zoomInfo.destinationDisplay} of $po: count ${query.getRecordCount}, literal $count")
            if (count > 0) {
              val firstKey = DB.getSQLValueEx(null, "SELECT " + query.getZoomColumnName + common)
              check(query.getZoomValue == firstKey, s"${zoomInfo.destinationDisplay} of $po: first key ${query.getZoomValue}, literal $firstKey")
              withRecords += 1
            }
            check(query.getBoundWhereClause(false).getParameters.isEmpty, s"The relation type restriction is raw: ${query.getBoundWhereClause(false)}")
            compared += 1
          }
        }
      }
      check(compared > 0, "No relation type applies to the orders, invoices and partners")
      check(withRecords > 0, "No relation type returns records")
    }
  }

  Feature("The generic zoom provider stays raw") {

    Scenario("its queries have no parameters and the text of the literal where clause") {
      val orderId = keyOf("SELECT MIN(C_Order_ID) FROM C_Order WHERE IsSOTrx='Y'")
      val partnerId = keyOf("SELECT MIN(C_BPartner_ID) FROM C_BPartner")
      val pos: Seq[PO] = Seq(new MOrder(Env.getCtx, orderId, null), new MBPartner(Env.getCtx, partnerId, null))
      var queries = 0
      pos.foreach { po =>
        new GenericZoomProvider().retrieveZoomInfos(po).asScala.foreach { zoomInfo =>
          val bound = zoomInfo.query.getBoundWhereClause(false)
          check(bound.getParameters.isEmpty, s"${zoomInfo.destinationDisplay}: ${bound.getParameters}")
          check(bound.getSql == zoomInfo.query.getWhereClause(false), s"${zoomInfo.destinationDisplay}: ${bound.getSql}")
          queries += 1
        }
      }
      check(queries > 0, "The generic zoom provider returns nothing")
    }
  }
}
