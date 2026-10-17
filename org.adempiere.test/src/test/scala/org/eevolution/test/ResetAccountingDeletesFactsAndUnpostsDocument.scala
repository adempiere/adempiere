/** ****************************************************************************
  * Product: Adempiere ERP & CRM Smart Business Solution                       *
  * This program is free software; you can redistribute it and/or modify it   *
  * under the terms version 2 of the GNU General Public License as published   *
  * by the Free Software Foundation. This program is distributed in the hope   *
  * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
  * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
  * See the GNU General Public License for more details.                       *
  * You should have received a copy of the GNU General Public License along   *
  * with this program; if not, write to the Free Software Foundation, Inc.,   *
  * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
  * ****************************************************************************/

package org.eevolution.test

import java.sql.{ResultSet, Timestamp}
import java.util.Calendar
import java.util.logging.Level

import org.adempiere.Adempiere
import org.compiere.model.MDocType
import org.compiere.process.FactAcctResetAbstract
import org.compiere.util.{CLogMgt, DB, Env, Ini, Login, Trx}
import org.eevolution.dsl.Date
import org.eevolution.services.dsl.ProcessBuilder
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Regression test for adempiere/adempiere#4435:
  * Running Reset Accounting with "Delete existing Accounting Entries" must leave
  * the document marked as not posted (Posted='N') after its Fact_Acct rows are
  * deleted, so the document can be accounted again.
  *
  * The spec seeds its own posted sales invoice and accounting facts on an open
  * month that has no other sales invoices or facts, so the process execution
  * only touches the seeded rows. The seeded rows are removed at the end.
  */
class ResetAccountingDeletesFactsAndUnpostsDocument
    extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  /**
    * Start Adempiere with the standard GardenWorld demo credentials.
    * The shared AdempiereStartup trait still logs in as SuperUser, which does
    * not exist in the standard 3.9 install of client GardenWorld.
    */
  override def startup(): Unit = {
    Adempiere.startup(false)
    CLogMgt.setLevel(Level.OFF)
    CLogMgt.setLoggerLevel(Level.OFF, null)

    Ini.setProperty(Ini.P_UID, "GardenAdmin")
    Ini.setProperty(Ini.P_PWD, "GardenAdmin")
    Ini.setProperty(Ini.P_ROLE, "GardenWorld Admin")
    Ini.setProperty(Ini.P_CLIENT, "GardenWorld")
    Ini.setProperty(Ini.P_ORG, "Store Central")
    Ini.setProperty(Ini.P_WAREHOUSE, "Store Central")
    Ini.setProperty(Ini.P_LANGUAGE, "English")

    val login = new Login(Env.getCtx)
    if (!login.batchLogin(null))
      throw new AssertionError("batchLogin failed for GardenAdmin/GardenWorld")
    CLogMgt.setLoggerLevel(Level.SEVERE, null)
    CLogMgt.setLevel(Level.SEVERE)
  }    //	startup


  Feature("Reset Accounting with Delete existing Accounting Entries") {

    Scenario("a posted sales invoice with accounting facts ends unposted after its facts are deleted") {
      val client = Env.getContextAsInt(Context, "#AD_Client_ID")
      val invoiceTableId = adTableId("C_Invoice")

      Given("an open accounting month without sales invoices or accounting facts")
      val (dateAcct, periodId) = pickOpenMonthWithoutPosting(client, invoiceTableId)

      Given("a posted sales invoice with accounting facts in that month")
      val invoiceId = seedPostedInvoiceWithFacts(client, dateAcct, periodId, invoiceTableId)

      try {
        When("the Reset Accounting process is executed with Delete existing Accounting Entries")
        val processInfo = ProcessBuilder.create(Context)
          .process(FactAcctResetAbstract.getProcessValue)
          .withParameter(FactAcctResetAbstract.AD_CLIENT_ID, client)
          .withParameter(FactAcctResetAbstract.AD_TABLE_ID, invoiceTableId)
          .withParameter(FactAcctResetAbstract.DATEACCT, dateAcct, dateAcct)
          .withParameter(FactAcctResetAbstract.DELETEPOSTING, true)
          .execute()

        Then("the process executed without errors")
        assert(!processInfo.isError)
        info("Process summary: " + processInfo.getSummary)

        And("the accounting facts of the invoice are deleted")
        assert(countFacts(client, invoiceId, invoiceTableId) == 0)

        And("the invoice is not posted anymore, so it can be accounted again")
        assert(!isInvoicePosted(invoiceId))
      } finally {
        cleanup(client, invoiceId, invoiceTableId)
      }
    }    //	Scenario
  }    //	Feature

  /**
    * Pick the most recent month whose period control is open for sales invoices,
    * inside the default schema open-future window, and without any sales invoice
    * or sales-invoice accounting fact of the client, so the seeded rows are the
    * only ones the process can touch.
    */
  private def pickOpenMonthWithoutPosting(client: Int, invoiceTableId: Int): (Date, Int) = {
    var startDate: Date = null
    var periodId = 0
    Trx.run { name =>
      val calendar = Calendar.getInstance()
      val rsSchema = execQuery(
        "SELECT s.period_openfuture FROM ad_clientinfo c " +
        "JOIN c_acctschema s ON s.c_acctschema_id = c.c_acctschema1_id " +
        "WHERE c.ad_client_id = ?",
        Array[AnyRef](Integer.valueOf(client)))
      rsSchema.next()
      val openFuture = rsSchema.getInt(1)
      DB.close(rsSchema)
      calendar.add(Calendar.DAY_OF_YEAR, openFuture - 15)
      val maxStart = new Timestamp(calendar.getTimeInMillis)

      val rs = execQuery(
        "SELECT cp.startdate, cp.c_period_id " +
        "FROM c_period cp " +
        "JOIN c_periodcontrol cpc ON cpc.c_period_id = cp.c_period_id " +
        "AND cpc.ad_client_id = ? AND cpc.docbasetype IN ('APC','API','ARI','ARC','ARF') " +
        "AND cpc.periodstatus = 'O' " +
        "WHERE cp.ad_client_id = ? AND cp.startdate <= ? " +
        "AND NOT EXISTS (SELECT 1 FROM c_invoice i WHERE i.ad_client_id = ? " +
        "AND i.dateacct >= cp.startdate AND i.dateacct <= cp.enddate) " +
        "AND NOT EXISTS (SELECT 1 FROM fact_acct f WHERE f.ad_client_id = ? " +
        "AND f.ad_table_id = ? AND f.dateacct >= cp.startdate AND f.dateacct <= cp.enddate) " +
        "ORDER BY cp.startdate DESC LIMIT 1",
        Array[AnyRef](
          Integer.valueOf(client),
          Integer.valueOf(client),
          maxStart,
          Integer.valueOf(client),
          Integer.valueOf(client),
          Integer.valueOf(invoiceTableId)))
      if (!rs.next())
        throw new AssertionError("No open month without sales invoices or accounting facts found; " +
          "cannot seed a safe regression scenario")
      startDate = rs.getTimestamp(1)
      periodId = rs.getInt(2)
      DB.close(rs)
    }
    val dateAcctCalendar = Calendar.getInstance()
    dateAcctCalendar.setTime(startDate)
    dateAcctCalendar.add(Calendar.DAY_OF_YEAR, 14)
    (new Date(dateAcctCalendar.getTimeInMillis), periodId)
  }    //	pickOpenMonthWithoutPosting

  /**
    * Seed a posted sales invoice with one accounting fact on the given date.
    * The rows are inserted directly, with explicit ids, so the seeded invoice
    * and its facts are the only sales-invoice rows of the open month.
    */
  private def seedPostedInvoiceWithFacts(client: Int, dateAcct: Date, periodId: Int, invoiceTableId: Int): Int = {
    var invoiceId = 0
    Trx.run { name =>
      val rs = execQuery(
        "SELECT (SELECT coalesce(max(c_invoice_id), 0) + 1000000 FROM c_invoice), " +
        "(SELECT coalesce(max(fact_acct_id), 0) + 1000000 FROM fact_acct), " +
        "(SELECT min(c_elementvalue_id) FROM c_elementvalue " +
        "WHERE ad_client_id = ? AND isactive = 'Y'), " +
        "(SELECT c_currency_id FROM fact_acct " +
        "WHERE ad_client_id = ? AND c_currency_id > 0 ORDER BY c_currency_id LIMIT 1), " +
        "(SELECT min(c_bpartner_location_id) FROM c_bpartner_location " +
        "WHERE ad_client_id = ? AND isactive = 'Y'), " +
        "(SELECT min(c_paymentterm_id) FROM c_paymentterm " +
        "WHERE ad_client_id = ? AND isactive = 'Y'), " +
        "(SELECT min(m_pricelist_id) FROM m_pricelist " +
        "WHERE ad_client_id = ? AND isactive = 'Y'), " +
        "(SELECT min(c_bpartner_id) FROM c_bpartner " +
        "WHERE ad_client_id = ? AND isactive = 'Y')",
        Array[AnyRef](
          Integer.valueOf(client),
          Integer.valueOf(client),
          Integer.valueOf(client),
          Integer.valueOf(client),
          Integer.valueOf(client),
          Integer.valueOf(client)))
      rs.next()
      invoiceId = rs.getInt(1)
      val factId = rs.getInt(2)
      val account = rs.getInt(3)
      val currency = rs.getInt(4)
      val bpartnerLocation = rs.getInt(5)
      val paymentTerm = rs.getInt(6)
      val priceList = rs.getInt(7)
      val bpartner = rs.getInt(8)
      DB.close(rs)

      val orgId = Organization.getAD_Org_ID
      val docType = MDocType.getDocType("ARI", orgId)

      DB.executeUpdate(
        "INSERT INTO c_invoice (c_invoice_id, ad_client_id, ad_org_id, createdby, updatedby, " +
        "documentno, docstatus, docaction, processed, posted, c_doctype_id, c_doctypetarget_id, " +
        "c_bpartner_id, c_bpartner_location_id, c_currency_id, paymentrule, c_paymentterm_id, " +
        "m_pricelist_id, dateinvoiced, dateacct) " +
        "VALUES (?, ?, ?, 101, 101, 'INV-TEST-4435', 'CO', 'CL', 'Y', 'Y', ?, ?, ?, ?, ?, 'P', ?, ?, ?, ?)",
        Array[AnyRef](
          Integer.valueOf(invoiceId),
          Integer.valueOf(client),
          Integer.valueOf(orgId),
          Integer.valueOf(docType),
          Integer.valueOf(docType),
          Integer.valueOf(bpartner),
          Integer.valueOf(bpartnerLocation),
          Integer.valueOf(currency),
          Integer.valueOf(paymentTerm),
          Integer.valueOf(priceList),
          dateAcct,
          dateAcct),
        false, name)

      DB.executeUpdate(
        "INSERT INTO fact_acct (fact_acct_id, ad_client_id, ad_org_id, createdby, updatedby, " +
        "c_acctschema_id, account_id, c_currency_id, c_period_id, datetrx, dateacct, " +
        "ad_table_id, record_id, postingtype, amtsourcedr, amtsourcecr, amtacctdr, amtacctcr) " +
        "VALUES (?, ?, ?, 101, 101, 101, ?, ?, ?, ?, ?, ?, ?, 'P', 0, 0, 0, 0)",
        Array[AnyRef](
          Integer.valueOf(factId),
          Integer.valueOf(client),
          Integer.valueOf(orgId),
          Integer.valueOf(account),
          Integer.valueOf(currency),
          Integer.valueOf(periodId),
          dateAcct,
          dateAcct,
          Integer.valueOf(invoiceTableId),
          Integer.valueOf(invoiceId)),
        false, name)
    }
    invoiceId
  }    //	seedPostedInvoiceWithFacts

  private def countFacts(client: Int, invoiceId: Int, invoiceTableId: Int): Int = {
    var count = -1
    Trx.run { name =>
      val rs = execQuery(
        "SELECT count(*) FROM fact_acct " +
        "WHERE ad_client_id = ? AND ad_table_id = ? AND record_id = ?",
        Array[AnyRef](
          Integer.valueOf(client),
          Integer.valueOf(invoiceTableId),
          Integer.valueOf(invoiceId)))
      rs.next()
      count = rs.getInt(1)
      DB.close(rs)
    }
    count
  }    //	countFacts

  private def isInvoicePosted(invoiceId: Int): Boolean = {
    var posted = false
    Trx.run { name =>
      val rs = execQuery(
        "SELECT posted FROM c_invoice WHERE c_invoice_id = ?",
        Array[AnyRef](Integer.valueOf(invoiceId)))
      rs.next()
      posted = "Y".equals(rs.getString(1))
      DB.close(rs)
    }
    posted
  }    //	isInvoicePosted

  private def cleanup(client: Int, invoiceId: Int, invoiceTableId: Int): Unit = {
    if (invoiceId == 0) return
    Trx.run { name =>
      DB.executeUpdate(
        "DELETE FROM fact_acct WHERE ad_client_id = ? AND ad_table_id = ? AND record_id = ?",
        Array[AnyRef](
          Integer.valueOf(client),
          Integer.valueOf(invoiceTableId),
          Integer.valueOf(invoiceId)),
        false, name)
      DB.executeUpdate(
        "DELETE FROM c_invoice WHERE c_invoice_id = ?",
        Array[AnyRef](Integer.valueOf(invoiceId)),
        false, name)
    }
  }    //	cleanup

  private def adTableId(tableName: String): Int = {
    var id = 0
    Trx.run { name =>
      val rs = execQuery("SELECT ad_table_id FROM ad_table WHERE tablename = ?",
        Array[AnyRef](tableName))
      rs.next()
      id = rs.getInt(1)
      DB.close(rs)
    }
    id
  }    //	adTableId

  private def execQuery(sql: String, params: Array[AnyRef]): ResultSet = {
    val conn = DB.getConnectionRW()
    val ps = conn.prepareStatement(sql)
    try {
      var i = 1
      params.foreach { p =>
        ps.setObject(i, p)
        i += 1
      }
      ps.executeQuery()
    } catch {
      case e: Throwable =>
        ps.close()
        throw e
    }
  }    //	execQuery

}    //	ResetAccountingDeletesFactsAndUnpostsDocument
