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

import org.compiere.model.{FindCount, GridTab, GridTable, GridWindow, GridWindowVO, MLookup, MQuery}
import org.compiere.util.DisplayType
import org.compiere.util.{DB, Env}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.jdk.CollectionConverters._

/** The zoom from a lookup to a record of a child tab finds the record with the bound MQuery.
  *
  * It replays, without ZK, `AbstractADWindowPanel.initPanel` (the header query becomes `1=2` when the zoom table is not
  * the one of tab 0) and `zoomToDetailTab`/`doZoomToDetail`: the link value comes from `FindCount.getLinkValue`, the
  * parent tab is queried by the link value and the child tab by `targetQuery`.
  */
class ValidateZoomToDetailTab extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private var windowNumber = 4000

  private def check(condition: Boolean, message: => String = "assertion failed"): Unit =
    if (!condition) fail(message)

  private def privateText(table: GridTable, name: String): String = {
    val field = table.getClass.getDeclaredField(name)
    field.setAccessible(true)
    String.valueOf(field.get(table))
  }

  /** The zoom query of a lookup zoom (`AEnv.zoom`) */
  private def zoomQuery(tableName: String, keyColumn: String, id: Int): MQuery = {
    val query = MQuery.getEqualQuery(keyColumn, Integer.valueOf(id))
    query.setZoomTableName(tableName)
    query.setZoomColumnName(keyColumn)
    query.setZoomValue(Integer.valueOf(id))
    query
  }

  /** Replays the panel; returns the SQL, the parameters and the keys of the child tab, and the parent id */
  private def zoomToDetail(windowId: Int, query: MQuery, parentTable: String, parentKey: String, expectedParent: Int): Unit = {
    windowNumber += 1
    val windowVO = GridWindowVO.create(Env.getCtx, windowNumber, windowId)
    if (windowVO == null) fail(s"The window $windowId cannot be created")
    val window = new GridWindow(windowVO)
    try {
      val headerTab = window.getTab(0)
      check(!query.getZoomTableName.equalsIgnoreCase(headerTab.getTableName), "the zoom table is the header")
      // initPanel: the header query becomes 1=2
      window.initTab(0)
      val header = new MQuery()
      header.addRestriction("1=2")
      header.setRecordCount(0)
      headerTab.setQuery(header)
      headerTab.query(false)

      val childIndex = (1 until window.getTabCount).find { index =>
        val tab = window.getTab(index)
        !tab.isSortTab && tab.getTableName.equalsIgnoreCase(query.getZoomTableName)
      }.getOrElse(fail(s"No tab of ${query.getZoomTableName}"))
      val childTab: GridTab = window.getTab(childIndex)
      window.initTab(childIndex)
      check(childTab.getFields.exists(_.getColumnName.equalsIgnoreCase(query.getZoomColumnName)), "the zoom column is not in the tab")

      val linkColumn = childTab.getLinkColumnName
      val parentId = FindCount.getLinkValue(linkColumn, childTab.getTableName, query)
      val literalSql = s"SELECT $linkColumn FROM ${childTab.getTableName} WHERE ${query.getWhereClause(false)}"
      val literalParentId = DB.getSQLValue(null, literalSql)
      info(s"link value: bound $parentId, literal $literalParentId ($literalSql)")
      check(literalParentId == parentId, s"literal link value $literalParentId, bound $parentId")
      Then(s"$linkColumn of the zoom record is $expectedParent")
      check(parentId == expectedParent, s"getLinkValue($linkColumn, ${childTab.getTableName}) = $parentId, expected $expectedParent")

      And("the parent tab is queried by the link value and made current")
      val parentTab = window.getTab(0)
      val parentQuery = new MQuery(parentTab.getAD_Table_ID)
      parentQuery.addRestriction(linkColumn, "=", Integer.valueOf(parentId))
      parentTab.setQuery(parentQuery)
      parentTab.query(false)
      check(parentTab.getTableName == parentTable && parentTab.getRowCount == 1, s"parent rows ${parentTab.getRowCount}")
      parentTab.setCurrentRow(0)
      check(parentTab.getValue(0, parentKey).asInstanceOf[Number].intValue == parentId, "the current parent row is another record")

      When("the child tab is queried with targetQuery")
      val target = new MQuery(childTab.getAD_Table_ID)
      target.addRestriction(linkColumn, "=", Integer.valueOf(parentId))
      childTab.setQuery(target)
      childTab.query(false, 0, 0)
      val table = childTab.getTableModel
      info(s"child SQL: ${privateText(table, "m_SQL")}")
      info(s"child parameters: ${table.getSelectWhereParameters.asScala.toVector}")
      val keys = (0 until table.getRowCount).map(table.getKeyID)
      info(s"child rows: $keys")
      Then("the child rows contain the zoomed record")
      check(keys.contains(query.getZoomValue.asInstanceOf[Integer].intValue), s"rows $keys do not contain ${query.getZoomValue}")
    } finally window.dispose()
  }

  Feature("Zoom to a record of a child tab") {

    Scenario("Purchase Order (181): C_OrderLine_ID=105 reaches C_Order_ID=104") {
      val query = zoomQuery("C_OrderLine", "C_OrderLine_ID", 105)
      check(!DB.isSOTrx("C_OrderLine", query.getBoundWhereClause(false)), "line 105 is a sales transaction")
      check(!DB.isSOTrx("C_OrderLine", query.getWhereClause(false)), "line 105 is a sales transaction (literal)")
      zoomToDetail(181, query, "C_Order", "C_Order_ID", 104)
    }

    Scenario("Invoice (Vendor) (183): C_InvoiceLine_ID=117 reaches C_Invoice_ID=106") {
      zoomToDetail(183, zoomQuery("C_InvoiceLine", "C_InvoiceLine_ID", 117), "C_Invoice", "C_Invoice_ID", 106)
    }
  }

  Feature("Zoom from the field of a lookup") {

    Scenario("Invoice (Vendor) line, field C_OrderLine_ID = 105: the zoom opens the Purchase Order window on the order line") {
      Given("the C_OrderLine_ID field of the C_InvoiceLine tab of the window 183")
      windowNumber += 1
      val invoiceWindow = new GridWindow(GridWindowVO.create(Env.getCtx, windowNumber, 183))
      try {
        val lineTab = (0 until invoiceWindow.getTabCount).map(invoiceWindow.getTab).find(_.getTableName == "C_InvoiceLine").get
        invoiceWindow.initTab(invoiceWindow.getTabCount - 1)
        (0 until invoiceWindow.getTabCount).foreach(invoiceWindow.initTab)
        val field = lineTab.getField("C_OrderLine_ID")
        val lookup = field.getLookup
        check(lookup != null, "the field has no lookup")
        val value: Object = Integer.valueOf(105)
        When("AEnv.actionZoom builds the query from the lookup, as the editor does")
        var column = lookup.getColumnName
        val zoomQuery = new MQuery()
        if (lookup.isInstanceOf[MLookup] && lookup.getDisplayType == DisplayType.List) fail("a list lookup")
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
        val bound = zoomQuery.getBoundWhereClause(false)
        val windowId = lookup.getZoom(zoomQuery)
        val m = lookup.asInstanceOf[MLookup]
        info(s"lookup column ${lookup.getColumnName}, zoom window ${m.getZoom()}, PO window ${DB.getSQLValue(null, "SELECT PO_Window_ID FROM AD_Table WHERE TableName='C_OrderLine'")}")
        info(s"query: tableName=${zoomQuery.getTableName} zoomTable=${zoomQuery.getZoomTableName} zoomColumn=${zoomQuery.getZoomColumnName} zoomValue=${zoomQuery.getZoomValue}")
        info(s"bound where: ${bound.getSql} params ${bound.getParameters.asScala.toVector}; literal where: ${zoomQuery.getWhereClause(false)}")
        info(s"chosen window: $windowId (bound isSOTrx ${DB.isSOTrx("C_OrderLine", bound)}, literal isSOTrx ${DB.isSOTrx("C_OrderLine", zoomQuery.getWhereClause(false))})")
        check(windowId == 181, s"the zoom opens the window $windowId, expected 181 (Purchase Order)")
        Then("the target window opens on the order line")
        zoomToDetail(windowId, zoomQuery, "C_Order", "C_Order_ID", 104)
      } finally invoiceWindow.dispose()
    }
  }
}
