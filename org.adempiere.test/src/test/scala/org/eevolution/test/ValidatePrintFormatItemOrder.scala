/** ****************************************************************************
  * Product: Adempiere ERP & CRM Smart Business Solution                       *
  * This program is free software; you can redistribute it and/or modify it    *
  * under the terms version 2 of the GNU General Public License as published   *
  * by the Free Software Foundation. This program is distributed in the hope   *
  * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
  * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
  * See the GNU General Public License for more details.                       *
  * You should have received a copy of the GNU General Public License along    *
  * with this program; if not, write to the Free Software Foundation, Inc.,    *
  * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
  * ****************************************************************************/
package org.eevolution.test

import org.compiere.model.MQuery
import org.compiere.print.{DataEngine, MPrintFormat}
import org.compiere.util.{DB, Env, Language, Trx}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Print format items that share the same SeqNo must always come in the same order, AD_PrintFormatItem_ID.
  * Without that tie-break the database returns them in scan order, which changes after an update of the rows
  * or with another query plan, so the columns of a report could swap between two runs.
  */
class ValidatePrintFormatItemOrder extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val TiedSeqNo = 999999

  /** A table-based print format with two printed String field items: (format, lower item ID, higher item ID). */
  private def formatWithTwoPrintedItems(): (Int, Int, Int) = {
    val sql =
      "SELECT pfi.AD_PrintFormat_ID, MIN(pfi.AD_PrintFormatItem_ID), MAX(pfi.AD_PrintFormatItem_ID) " +
        "FROM AD_PrintFormatItem pfi" +
        " INNER JOIN AD_PrintFormat pf ON (pf.AD_PrintFormat_ID=pfi.AD_PrintFormat_ID)" +
        " INNER JOIN AD_Table t ON (t.AD_Table_ID=pf.AD_Table_ID)" +
        " INNER JOIN AD_Column c ON (c.AD_Column_ID=pfi.AD_Column_ID) " +
        "WHERE pf.IsActive='Y' AND pf.IsTableBased='Y' AND pf.AD_Client_ID=11" +
        " AND pfi.IsActive='Y' AND pfi.IsPrinted='Y' AND pfi.PrintFormatType='F'" +
        " AND c.AD_Reference_ID=10 AND c.IsKey='N' AND c.ColumnSQL IS NULL" +
        " AND t.TableName NOT LIKE 'T!_%' ESCAPE '!' AND t.TableName NOT LIKE 'AD!_PInstance%' ESCAPE '!' " +
        "GROUP BY pfi.AD_PrintFormat_ID HAVING COUNT(*) >= 2 " +
        "ORDER BY pfi.AD_PrintFormat_ID"
    val statement = DB.prepareStatement(sql, null)
    try {
      val resultSet = statement.executeQuery()
      try {
        assert(resultSet.next(), "GardenWorld has no print format with two printed String items")
        (resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3))
      } finally resultSet.close()
    } finally statement.close()
  }

  /** Both items get the same SeqNo, the lower ID last, so its row version is the newest one in the table. */
  private def tie(lowerItem: Int, higherItem: Int, transactionName: String): Unit = {
    DB.executeUpdateEx("UPDATE AD_PrintFormatItem SET SeqNo=? WHERE AD_PrintFormatItem_ID=?",
      Array[AnyRef](Integer.valueOf(TiedSeqNo), Integer.valueOf(higherItem)), transactionName)
    DB.executeUpdateEx("UPDATE AD_PrintFormatItem SET SeqNo=? WHERE AD_PrintFormatItem_ID=?",
      Array[AnyRef](Integer.valueOf(TiedSeqNo), Integer.valueOf(lowerItem)), transactionName)
  }

  private def inRolledBackTransaction[T](body: String => T): T = {
    val transaction = Trx.get(Trx.createTrxName("PrintFormatItemOrder"), true)
    try body(transaction.getTrxName)
    finally {
      transaction.rollback()
      transaction.close()
    }
  }

  Feature("Print format items with the same SeqNo keep the AD_PrintFormatItem_ID order") {

    Scenario("the report columns of tied items follow AD_PrintFormatItem_ID, with lookup joins and subselects") {
      val (formatId, lowerItem, higherItem) = formatWithTwoPrintedItems()
      Given(s"print format $formatId with items $lowerItem and $higherItem tied at SeqNo $TiedSeqNo")
      inRolledBackTransaction { transactionName =>
        tie(lowerItem, higherItem, transactionName)
        Seq(true, false).foreach { join =>
          When(s"the report data is built (lookup join $join, no definition cache)")
          val format = new MPrintFormat(Env.getCtx, formatId, transactionName)
          val engine = new DataEngine(Language.getLanguage("en_US"), transactionName)
          engine.setLookupJoin(join)
          engine.setDefinitionCache(false)
          val query = new MQuery(format.getAD_Table_ID)
          query.addRestriction("1=2")
          val printData = engine.getPrintData(Env.getCtx, format, query, false, 0)
          assert(printData != null, s"no print data for format $formatId")
          val items = printData.getColumnInfo.toVector.map(_.getPrinformatItemId)
          Then("the lower AD_PrintFormatItem_ID comes first")
          assert(items.contains(lowerItem) && items.contains(higherItem), s"columns $items")
          assert(items.indexOf(lowerItem) < items.indexOf(higherItem),
            s"join=$join: item $lowerItem must come before item $higherItem, columns $items")
        }
      }
    }

    Scenario("MPrintFormat.getItems returns tied items in AD_PrintFormatItem_ID order") {
      val (formatId, lowerItem, higherItem) = formatWithTwoPrintedItems()
      Given(s"print format $formatId with items $lowerItem and $higherItem tied at SeqNo $TiedSeqNo")
      inRolledBackTransaction { transactionName =>
        tie(lowerItem, higherItem, transactionName)
        When("the items of the print format are loaded")
        val items = new MPrintFormat(Env.getCtx, formatId, transactionName).getItems.toVector.map(_.getAD_PrintFormatItem_ID)
        Then("the lower AD_PrintFormatItem_ID comes first")
        assert(items.indexOf(lowerItem) >= 0 && items.indexOf(higherItem) >= 0, s"items $items")
        assert(items.indexOf(lowerItem) < items.indexOf(higherItem),
          s"item $lowerItem must come before item $higherItem, items $items")
      }
    }
  }
}
