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

import java.util.logging.{Handler, Level, LogRecord}

import org.compiere.model.MQuery
import org.compiere.print.{DataEngine, MPrintFormat, PrintData}
import org.compiere.util.{CLogger, DB, Env, Language}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.collection.mutable.ArrayBuffer

/**
  * A report with group columns whose query returns no rows ends like any other report without rows:
  * no "ResultSet not positioned properly" and no SEVERE (so no AD_Issue). With rows it keeps its group and total rows.
  *
  * Format 1000413 (RV_M_Transaction_Costing, group columns and functions) is used, with the lookup joins on and off.
  */
class ValidateReportGroupWithoutRows extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val GroupedFormat = 1000413
  private val UngroupedFormat = 1000003 // C_Order, no group columns
  private val English = Language.getLanguage("en_US")

  /** Collects what the print classes log at WARNING or above. */
  private class Collector extends Handler {
    val records = ArrayBuffer.empty[LogRecord]
    override def publish(record: LogRecord): Unit = if (record.getLevel.intValue >= Level.WARNING.intValue) records.synchronized(records += record)
    override def flush(): Unit = ()
    override def close(): Unit = ()
    def severe: Vector[String] = records.synchronized(records.toVector.filter(_.getLevel == Level.SEVERE).map(_.getMessage))
  }

  private def issueCount(): Int = DB.getSQLValue(null, "SELECT COUNT(*) FROM AD_Issue")

  /** Runs the report and returns its data, the SEVERE messages logged by the engine and the new AD_Issue rows. */
  private def run(formatId: Int, join: Boolean, restriction: Option[String]): (PrintData, Vector[String], Int) = {
    val collector = new Collector
    val logger = CLogger.getCLogger(classOf[DataEngine])
    val issuesBefore = issueCount()
    logger.addHandler(collector)
    try {
      val format = MPrintFormat.get(Env.getCtx, formatId, true)
      val query = new MQuery(format.getAD_Table_ID)
      restriction.foreach(query.addRestriction)
      val engine = new DataEngine(English, null)
      engine.setLookupJoin(join)
      val printData = engine.getPrintData(Env.getCtx, format, query, false, 0)
      (printData, collector.severe, issueCount() - issuesBefore)
    } finally logger.removeHandler(collector)
  }

  private def functionRows(printData: PrintData): Vector[Int] = (0 until printData.getRowCount).filter(printData.isFunctionRow).toVector

  Feature("A grouped report that returns no rows") {

    Seq(true, false).foreach { join =>
      Scenario(s"ends without error and without rows (lookup join $join)") {
        Given("a print format with group columns and a restriction that no row satisfies")
        When("the report data is built")
        val (printData, severe, issues) = run(GroupedFormat, join, Some("1=2"))
        Then("there is print data with its columns, no rows, no SEVERE and no AD_Issue")
        assert(printData != null, "the report returned no print data")
        assert(printData.getColumnInfo.nonEmpty)
        assert(severe.isEmpty, s"SEVERE: $severe")
        assert(issues == 0, s"$issues AD_Issue rows created")
        assert(printData.getRowCount == 0, s"${printData.getRowCount} rows for a query without rows")
        assert(printData.isEmpty || printData.getNodeCount == 0)
      }
    }

    Scenario("ends the same as a report without group columns and without rows") {
      Given("a grouped and an ungrouped print format, both restricted to no rows")
      val (grouped, groupedSevere, _) = run(GroupedFormat, join = true, Some("1=2"))
      val (ungrouped, ungroupedSevere, _) = run(UngroupedFormat, join = true, Some("1=2"))
      Then("both have the same empty outcome")
      assert(groupedSevere.isEmpty && ungroupedSevere.isEmpty)
      assert(grouped.getRowCount == ungrouped.getRowCount)
      assert(grouped.getNodeCount == ungrouped.getNodeCount)
    }
  }

  Feature("A grouped report that returns rows keeps its group and total rows") {

    Scenario("the group break and function rows are the same with joins and subselects") {
      Given("the same print format without restriction")
      val (joined, joinedSevere, _) = run(GroupedFormat, join = true, None)
      val (legacy, legacySevere, _) = run(GroupedFormat, join = false, None)
      Then("it has data rows and function rows, no SEVERE, and both paths agree")
      assert(joinedSevere.isEmpty && legacySevere.isEmpty, s"SEVERE: $joinedSevere $legacySevere")
      assert(joined.getRowCount > 0, "the format returned no rows; the check below would prove nothing")
      assert(functionRows(joined).nonEmpty, "no group or total rows")
      assert(joined.getRowCount == legacy.getRowCount)
      assert(functionRows(joined) == functionRows(legacy))
      assert((0 until joined.getRowCount).count(rowIndex => !joined.isFunctionRow(rowIndex)) > 0)
    }

    Scenario("the last group is still closed after the final row") {
      Given("the grouped print format without restriction")
      val (printData, _, _) = run(GroupedFormat, join = true, None)
      Then("the last row of the report is a function row")
      assert(printData.isFunctionRow(printData.getRowCount - 1), "the final forced group change did not add its function rows")
    }
  }
}
