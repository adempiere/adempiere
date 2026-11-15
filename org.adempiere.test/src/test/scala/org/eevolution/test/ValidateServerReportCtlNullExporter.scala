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
  * For the text or an alternative of this public license, you may reach us    *
  * Copyright (C) 2003-2023 ADempiere Foundation,SC. All Rights Reserved.      *
  * ****************************************************************************/

package org.eevolution.test

import org.compiere.model.{MOrder, MOrderLine, MPriceList, MQuery, PrintInfo}
import org.compiere.print.{MPrintFormat, ReportEngine, ServerReportCtl}
import org.compiere.process.{DocAction, ProcessInfo}
import org.compiere.util.{DB, Env}
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.GivenWhenThen
import org.spin.util.ReportExportHandler

import java.lang.reflect.{InvocationTargetException, Method}

/**
  * Regression test for adempiere/adempiere#4214.
  *
  * `ServerReportCtl.createOutput` asked `ReportExportHandler` for the exporter
  * matching `ProcessInfo.getReportType()` and used the result without checking
  * it. `getExporterFromExtension` returns `null` for any extension the report
  * does not offer, so the very next call — `exporter.getExtension()`, reached
  * while building the temporary file name — threw a NullPointerException. The
  * `catch` clause only covers `IOException`, so the exception escaped and the
  * background process died instead of reporting a usable outcome.
  *
  * The fix guards the exporter before it is dereferenced: when no exporter
  * matches the requested type, the export is skipped and the process info is
  * left without a report file rather than blowing up.
  *
  * This spec pins the observable contract:
  *  - an unsupported report type completes without throwing and sets no file;
  *  - every unsupported type behaves the same way, not just the reported one;
  *  - the guard does not hide the supported path — each extension the standard
  *    export offers still resolves to its own exporter, so a supported type is
  *    never silently downgraded to "no report";
  *  - a null exporter only ever comes from an unsupported extension, never from
  *    a handler that failed to load.
  *
  * Skipping the export is only half of it: `startDocumentPrint` confirms the
  * document as printed (`IsPrinted = 'Y'`) right after `createOutput`, so a
  * skipped export must not reach that confirmation nor report success. A
  * second feature pins that flow against a real unprinted order, inside the
  * rolled-back test transaction.
  *
  * `createOutput` is private and package-internal, so it is reached through
  * reflection, following the precedent of
  * `ValidateReportEngineClosesFallbackStatement`. The report engine is built
  * from a shipped print format whose `AD_ReportView_ID` is zero, which makes
  * `ReportExportHandler` fall back to the standard `ReportExport` and its
  * fixed list of extensions: the scenario is deterministic and creates no data.
  */
class ValidateServerReportCtlNullExporter extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  /**
    * The extensions the standard `ReportExport` offers out of the box. A type
    * outside this list is exactly what makes `getExporterFromExtension` return
    * null, which is the path this spec pins.
    */
  private val SupportedExtensions = Seq("ps", "xml", "pdf", "html", "txt", "ssv", "csv", "xls", "xlsx", "arxml")

  /** Report types no exporter offers; each one used to raise a NullPointerException. */
  private val UnsupportedExtensions = Seq("docx", "json", "rtf", "odt")

  /**
    * Core tables to look for a shipped print format on, in preference order.
    * `M_Product` first: it is part of every ADempiere dictionary, and a query
    * for its shipped client returns no rows, so the export is a small
    * deterministic header without touching business data.
    */
  private val FixtureTables = Seq("M_Product", "AD_Ref_List")

  /**
    * A shipped print format (client 0, active, no report view) over one of the
    * fixture tables. No report view is what keeps `ReportExportHandler` on the
    * standard `ReportExport` and its fixed extension list.
    */
  private def fixturePrintFormat: Option[(Int, String)] =
    FixtureTables.iterator
      .map { tn =>
        val id = DB.getSQLValue(trxName,
          "SELECT MIN(pf.AD_PrintFormat_ID) FROM AD_PrintFormat pf" +
            " JOIN AD_Table t ON t.AD_Table_ID = pf.AD_Table_ID" +
            " WHERE pf.AD_Client_ID = 0 AND pf.IsActive = 'Y'" +
            " AND COALESCE(pf.AD_ReportView_ID, 0) = 0 AND t.TableName = ?", tn)
        (id, tn)
      }
      .find { case (id, _) => id > 0 }

  /**
    * A report engine over a shipped print format, backed by a query on that
    * format's own table. The query is what makes `setQuery` load `PrintData`:
    * an engine built with a null query has no data and every export format
    * fails inside `createDelimitedFile`, which is why the supported export
    * path has to be exercised against an engine that actually carries data.
    */
  private def reportEngine(): ReportEngine = {
    val (printFormatId, tableName) = fixturePrintFormat.getOrElse(
      fail(s"no shipped print format without a report view over ${FixtureTables.mkString(", ")}"))
    val engine = new ReportEngine(Env.getCtx,
      new MPrintFormat(Env.getCtx, printFormatId, trxName),
      {
        val query = new MQuery(tableName)
        query.addRestriction("AD_Client_ID", MQuery.EQUAL, Integer.valueOf(0))
        query
      },
      null.asInstanceOf[PrintInfo], trxName)
    assert(engine.getPrintData != null, s"the engine loaded no print data from $tableName")
    engine
  }

  /** A process info asking to export to the given report type. */
  private def exportProcessInfo(reportType: String): ProcessInfo = {
    val processInfo = new ProcessInfo("Export with an unsupported report type", 0)
    processInfo.setReportType(reportType)
    processInfo
  }

  /**
    * Invokes the private static `ServerReportCtl.createOutput` and returns what
    * it answers. A checked or runtime failure raised inside it arrives wrapped
    * in an InvocationTargetException; the cause is rethrown so the spec reports
    * the exception the caller would actually have seen.
    *
    * The answer is `Option[Boolean]`: before the fix the method is `void` and
    * reflection answers null, which is `None`; after the fix the scenarios assert
    * the exact `Some(true)` / `Some(false)` the guard must give its caller.
    */
  private def createOutput(engine: ReportEngine, processInfo: ProcessInfo): Option[Boolean] = {
    val method: Method = classOf[ServerReportCtl].getDeclaredMethod("createOutput",
      classOf[ReportEngine], classOf[ProcessInfo], classOf[String])
    method.setAccessible(true)
    try Option(method.invoke(null, engine, processInfo, null)).map(_.asInstanceOf[Boolean])
    catch {
      case e: InvocationTargetException => throw e.getCause
    }
  }

  Feature("ServerReportCtl.createOutput survives a report type with no exporter") {
    info("adempiere/adempiere#4214: getExporterFromExtension returns null for an unsupported extension")

    Scenario("exporting to a report type no exporter offers does not throw") {
      Given("a report process asking for a report type nothing can produce")
      val engine = reportEngine()
      val processInfo = exportProcessInfo("docx")
      assert(new ReportExportHandler(Env.getCtx, engine).getExporterFromExtension("docx") == null,
        "the scenario is only meaningful while docx resolves to no exporter")

      When("the server creates the output")
      val produced = createOutput(engine, processInfo)

      Then("no exception escaped and no report file was produced")
      assert(processInfo.getReportAsFile == null,
        "an unsupported report type must not set a report file")
      And("the process was not marked as failed by the guard")
      assert(!processInfo.isError,
        s"skipping an unsupported export is not an error, summary was '${processInfo.getSummary}'")
      And("createOutput answers false, so the caller knows nothing was produced")
      assert(produced == Some(false),
        s"an export no exporter can serve must answer not produced, got '$produced'")
    }

    Scenario("every report type outside the supported list is skipped the same way") {
      Given("each report type that no exporter offers")
      assert(UnsupportedExtensions.forall(extension => !SupportedExtensions.contains(extension)),
        "the unsupported list must stay disjoint from the supported extensions")

      When("the server creates the output for each of them")
      UnsupportedExtensions.foreach { reportType =>
        val processInfo = exportProcessInfo(reportType)
        createOutput(reportEngine(), processInfo)

        Then(s"'$reportType' is skipped without throwing and without a report file")
        assert(processInfo.getReportAsFile == null, s"'$reportType' must not set a report file")
        assert(!processInfo.isError, s"'$reportType' must not mark the process as failed")
      }
    }

    Scenario("the guard does not hide the supported export path") {
      Given("the handler of a shipped print format")
      val handler = new ReportExportHandler(Env.getCtx, reportEngine())

      When("each supported extension is resolved")
      val resolved = SupportedExtensions.map(extension => extension -> handler.getExporterFromExtension(extension))

      Then("every supported extension still resolves to its own exporter")
      resolved.foreach { case (extension, exporter) =>
        assert(exporter != null, s"supported extension '$extension' must resolve to an exporter")
        assert(exporter.getExtension == extension,
          s"'$extension' resolved to an exporter for '${exporter.getExtension}'")
      }
      And("each exporter is distinct, so the list is not a single fallback")
      assert(resolved.map { case (_, exporter) => exporter }.distinct.size == SupportedExtensions.size,
        "each supported extension must resolve to its own exporter")
    }

    Scenario("a supported type reaches the exporter and produces the report file") {
      Given("a report process asking for a type the standard export offers")
      val engine = reportEngine()
      val processInfo = exportProcessInfo("csv")

      When("the server creates the output")
      val produced = createOutput(engine, processInfo)

      Then("an exporter was found, so the guarded branch was not taken")
      assert(produced == Some(true), s"a supported export must answer produced, got '$produced'")
      val reportFile = processInfo.getReportAsFile
      assert(reportFile != null,
        "a supported type must produce a report file, not fall through the null guard")
      And("the file carries the requested extension and was written")
      assert(reportFile.getName.endsWith(".csv"),
        s"report file was named '${reportFile.getName}'")
      assert(reportFile.length > 0,
        s"the export wrote an empty file (${reportFile.length} bytes)")
      And("the process was not marked as failed")
      assert(!processInfo.isError,
        s"the export reported no failure, summary was '${processInfo.getSummary}'")

      if (reportFile != null) reportFile.delete()
    }
  }

  Feature("ServerReportCtl.startDocumentPrint does not confirm a skipped export as printed") {
    info("adempiere/adempiere#4214 review: an order whose export was skipped must not be marked IsPrinted")

    Scenario("an order whose export no exporter can serve is not confirmed as printed") {
      Given("a non-Jasper order print format offered by the dictionary")
      //	startDocumentPrint only reaches the exporter guard on the non-Jasper
      //	branch, and ReportEngine.get prefers C_DocType.AD_PrintFormat_ID over the
      //	order format of AD_PrintForm. The format is therefore read from
      //	AD_PrintForm, pinned non-Jasper here, and handed to the call as
      //	customPrintFormat, so the branch under test is the one that runs no
      //	matter what the document type of the order resolves to later.
      val printFormatId = DB.getSQLValue(trxName,
        "SELECT pf.Order_PrintFormat_ID FROM AD_PrintForm pf" +
          " JOIN AD_PrintFormat f ON f.AD_PrintFormat_ID = pf.Order_PrintFormat_ID" +
          " WHERE pf.AD_Client_ID = ? AND COALESCE(pf.AD_Org_ID, 0) = 0" +
          " AND COALESCE(f.JasperProcess_ID, 0) = 0" +
          " ORDER BY pf.AD_PrintForm_ID", Env.getAD_Client_ID(Env.getCtx))
      assume(printFormatId > 0,
        s"client ${Env.getAD_Client_ID(Env.getCtx)} offers no non-Jasper order print"
          + " format, so the export branch of startDocumentPrint cannot be exercised"
          + " on this dictionary")

      And("a drafted order created by this scenario inside the rolled back transaction")
      //	The order is built here instead of being picked from the business data: a
      //	dictionary without unprinted orders still pins the regression, and a
      //	fixture built by the scenario cannot drift with the data of a developer.
      //	It lives only in this transaction, so AdempiereTestCase rolls it back.
      val clientId = Env.getAD_Client_ID(Env.getCtx)
      val partnerId = DB.getSQLValue(trxName,
        "SELECT C_BPartner_ID FROM C_BPartner WHERE AD_Client_ID = ? AND Value = ?" +
          " AND IsActive = 'Y'", clientId, "JoeBlock")
      val productId = DB.getSQLValue(trxName,
        "SELECT M_Product_ID FROM M_Product WHERE AD_Client_ID = ? AND Value = ?" +
          " AND IsActive = 'Y'", clientId, "Oak")
      val priceListId = DB.getSQLValue(trxName,
        "SELECT M_PriceList_ID FROM M_PriceList WHERE AD_Client_ID = ? AND IsSOPriceList = 'Y'" +
          " AND IsActive = 'Y' ORDER BY AD_Org_ID DESC", clientId)
      val docTypeId = DB.getSQLValue(trxName,
        "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID = ? AND DocBaseType = 'SOO'" +
          " AND DocSubTypeSO = 'SO' AND IsActive = 'Y' ORDER BY AD_Org_ID DESC", clientId)
      val warehouseId = Env.getContextAsInt(Env.getCtx, "#M_Warehouse_ID")
      //	#Date is the login date of the session; now() is the fallback, so the
      //	fixture does not depend on how the context happened to be prepared.
      val dateOrdered = Option(Env.getContextAsDate(Env.getCtx, "#Date"))
        .getOrElse(new java.sql.Timestamp(System.currentTimeMillis))
      assume(partnerId > 0 && productId > 0 && priceListId > 0 && docTypeId > 0
        && warehouseId > 0,
        s"the dictionary cannot build the fixture order (partner=$partnerId,"
          + s" product=$productId, priceList=$priceListId, docType=$docTypeId,"
          + s" warehouse=$warehouseId), so the export branch"
          + " of startDocumentPrint cannot be exercised on this dictionary")

      val order = new MOrder(Env.getCtx, 0, trxName)
      order.setAD_Org_ID(Env.getAD_Org_ID(Env.getCtx))
      order.setC_BPartner_ID(partnerId)
      order.setM_Warehouse_ID(warehouseId)
      order.setM_PriceList_ID(priceListId)
      order.setC_DocType_ID(docTypeId)
      order.setC_DocTypeTarget_ID(docTypeId)
      order.setDateOrdered(dateOrdered)
      order.setDocAction(DocAction.ACTION_Complete)
      order.saveEx()

      val line = new MOrderLine(order)
      line.setM_Product_ID(productId, true)
      line.setQty(new java.math.BigDecimal(1))
      line.setPrice(priceListId)
      line.saveEx()

      val orderId = order.getC_Order_ID
      assume(ReportEngine.get(Env.getCtx, ReportEngine.ORDER, orderId, trxName) != null,
        s"ReportEngine builds no print engine for the fixture order $orderId, so the"
          + " flow would stop before reaching the exporter guard")

      And("the order is not marked as printed")
      assert(!order.isPrinted,
        s"the fixture order $orderId was already printed before the scenario ran")

      val processInfo = exportProcessInfo("docx")
      processInfo.setTransactionName(trxName)

      When("the server prints the order asking for a report type no exporter offers")
      val completed = ServerReportCtl.startDocumentPrint(ReportEngine.ORDER,
        new MPrintFormat(Env.getCtx, printFormatId, trxName), orderId, null, processInfo)

      Then("the print does not report success")
      assert(!completed,
        "a document no exporter can produce must not report a completed print")
      And("no report file was produced")
      assert(processInfo.getReportAsFile == null,
        "the skipped export must not set a report file")
      And("the process is marked as failed, so the user is never told it printed")
      assert(processInfo.isError,
        s"a skipped export must fail the process, summary was '${processInfo.getSummary}'")
      And("the order keeps IsPrinted = 'N'")
      order.load(trxName)
      assert(!order.isPrinted,
        s"order $orderId must stay unprinted when its export was skipped")
    }
  }
}
