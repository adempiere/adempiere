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

import java.sql.ResultSet
import java.util.logging.Level

import org.adempiere.exceptions.DBException
import org.adempiere.Adempiere
import org.compiere.acct.Doc
import org.compiere.model.{MAcctSchema, MDocType, MInvoice}
import org.compiere.util.{CLogMgt, DB, Env, Ini, Login, Trx}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Regression test for adempiere/adempiere#4433:
  * Constructing a posting Doc for a record must assign a per-run unique
  * transaction (a deterministic name derived from the document type and
  * record ID reuses a stale leftover transaction after an aborted post and
  * shares one JDBC connection across concurrent posts of the same document),
  * must not inject that transaction into the PO, and the Doc's accounting
  * deletion must remove exactly the record's facts and rely on the
  * fail-fast DB.executeUpdateEx, so a failed DELETE FROM Fact_Acct aborts
  * the post instead of leaving duplicated fact lines behind.
  *
  * The spec seeds its own processed (not posted) sales invoice and, for the
  * deletion scenario, three accounting facts on the record. The seeded rows
  * are removed at the end.
  */
class ValidateDocPostingTransaction
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


  Feature("Doc posting transaction and accounting deletion") {

    Scenario("each Doc of the same record gets its own transaction name") {
      val client = Env.getContextAsInt(Context, "#AD_Client_ID")

      Given("a processed sales invoice")
      val invoiceId = seedProcessedInvoice(client)
      assert(invoiceId > 0)

      try {
        When("two Docs are constructed for the same record")
        withDoc(client, invoiceId) { doc1 =>
          withDoc(client, invoiceId) { doc2 =>
            Then("both Docs have a transaction name")
            assert(doc1.getTrxName() != null)
            assert(doc2.getTrxName() != null)

            And("the names are different, so no stale or shared transaction is reused")
            assert(doc1.getTrxName() != doc2.getTrxName())
          }
        }
      } finally {
        cleanup(client, invoiceId)
      }
    }    //	Scenario

    Scenario("the document and its lines post on the transaction the Doc owns") {
      val client = Env.getContextAsInt(Context, "#AD_Client_ID")

      Given("a processed sales invoice, posted with no transaction of its own")
      val invoiceId = seedProcessedInvoice(client)
      assert(invoiceId > 0)

      try {
        When("the accounting engine builds the Doc without a transaction")
        withDoc(client, invoiceId) { doc =>
          Then("the PO carries the transaction the Doc will commit")
          assert(doc.getPO() != null)
          assert(doc.getPO().get_TrxName() == doc.getTrxName(),
            "the PO carries " + doc.getPO().get_TrxName() + " while the Doc owns "
              + doc.getTrxName() + ", so the document writes outside its transaction")

          And("the invoice lines reached through the PO inherit it, so their reads " +
            "and the cost detail they write stay on the same connection")
          val lines = doc.getPO().asInstanceOf[MInvoice].getLines(true)
          assume(lines.nonEmpty, "seed invoice has no lines")
          lines.foreach { line =>
            assert(line.get_TrxName() == doc.getTrxName(),
              "C_InvoiceLine " + line.getC_InvoiceLine_ID() + " carries "
                + line.get_TrxName() + ", outside the posting transaction")
          }
        }
      } finally {
        cleanup(client, invoiceId)
      }
    }    //	Scenario

    Scenario("the accounting deletion removes the record's facts and fails fast on SQL error") {
      val client = Env.getContextAsInt(Context, "#AD_Client_ID")
      val invoiceTableId = adTableId("C_Invoice")

      Given("a processed sales invoice with three accounting facts on the record")
      val invoiceId = seedProcessedInvoice(client)
      seedFacts(client, invoiceId, invoiceTableId, 3)
      assert(countFacts(client, invoiceId, invoiceTableId) == 3)

      try {
        When("the Doc deletes the accounting facts of the record")
        withDoc(client, invoiceId) { doc =>
          val deleted = callDeleteAcct(doc)
          closeTrx(doc.getTrxName())    // commit the deletion before counting

          Then("the deletion returns the number of deleted facts")
          assert(deleted == 3)

          And("no accounting fact of the record remains")
          assert(countFacts(client, invoiceId, invoiceTableId) == 0)

          And("a failing fact deletion aborts with an exception instead of being swallowed")
          val failedTrx = Trx.createTrxName("ValidateDocPosting_")
          try {
            intercept[DBException](
              DB.executeUpdateEx(
                "DELETE Fact_Acct WHERE AD_Table_ID=1 AND Record_ID=1 WHERE",
                failedTrx))
          } finally {
            closeTrx(failedTrx)
          }
        }
      } finally {
        cleanup(client, invoiceId)
      }
    }    //	Scenario
  }    //	Feature

  /**
    * Seed a processed (not posted) sales invoice on the current date. The
    * row is inserted directly, with an explicit id, so it is committed and
    * visible to the separate transactions the Docs open.
    */
  private def seedProcessedInvoice(client: Int): Int = {
    val seed = readSeedParams(client)
    Trx.run { name =>
      val dateAcct = Today
      val orgId = Organization.getAD_Org_ID
      val docType = MDocType.getDocType("ARI", orgId)

      DB.executeUpdate(
        "INSERT INTO c_invoice (c_invoice_id, ad_client_id, ad_org_id, createdby, updatedby, " +
        "documentno, docstatus, docaction, processed, posted, c_doctype_id, c_doctypetarget_id, " +
        "c_bpartner_id, c_bpartner_location_id, c_currency_id, paymentrule, c_paymentterm_id, " +
        "m_pricelist_id, dateinvoiced, dateacct) " +
        "VALUES (?, ?, ?, 101, 101, 'INV-TEST-4433', 'CO', 'CL', 'Y', 'N', ?, ?, ?, ?, ?, 'P', ?, ?, ?, ?)",
        Array[AnyRef](
          Integer.valueOf(seed.invoiceId),
          Integer.valueOf(client),
          Integer.valueOf(orgId),
          Integer.valueOf(docType),
          Integer.valueOf(docType),
          Integer.valueOf(seed.bpartner),
          Integer.valueOf(seed.bpartnerLocation),
          Integer.valueOf(seed.currency),
          Integer.valueOf(seed.paymentTerm),
          Integer.valueOf(seed.priceList),
          dateAcct,
          dateAcct),
        false, name)

      DB.executeUpdate(
        "INSERT INTO c_invoiceline (c_invoiceline_id, ad_client_id, ad_org_id, c_invoice_id, " +
        "line, createdby, updatedby, m_product_id, c_uom_id, qtyinvoiced, qtyentered, " +
        "priceactual, priceentered, pricelist, linenetamt) " +
        "VALUES (?, ?, ?, ?, 10, 101, 101, ?, ?, 1, 1, 100, 100, 100, 100)",
        Array[AnyRef](
          Integer.valueOf(seed.invoiceLineId),
          Integer.valueOf(client),
          Integer.valueOf(orgId),
          Integer.valueOf(seed.invoiceId),
          Integer.valueOf(seed.product),
          Integer.valueOf(seed.uom)),
        false, name)
    }
    seed.invoiceId
  }    //	seedProcessedInvoice

  /**
    * Parameters needed to seed a processed invoice, read outside the write
    * transaction so the seeding stays immutable.
    */
  private case class InvoiceSeed(invoiceId: Int, currency: Int, bpartnerLocation: Int,
                                 paymentTerm: Int, priceList: Int, bpartner: Int,
                                 invoiceLineId: Int, product: Int, uom: Int)

  private def readSeedParams(client: Int): InvoiceSeed = {
    val rs = execQuery(
      "SELECT (SELECT coalesce(max(c_invoice_id), 0) + 1000000 FROM c_invoice), " +
      "(SELECT a.c_currency_id FROM c_acctschema a " +
      "WHERE a.ad_client_id = ? AND a.isactive = 'Y' ORDER BY a.c_acctschema_id LIMIT 1), " +
      "(SELECT min(c_bpartner_location_id) FROM c_bpartner_location " +
      "WHERE ad_client_id = ? AND isactive = 'Y'), " +
      "(SELECT min(c_paymentterm_id) FROM c_paymentterm " +
      "WHERE ad_client_id = ? AND isactive = 'Y'), " +
      "(SELECT min(m_pricelist_id) FROM m_pricelist " +
      "WHERE ad_client_id = ? AND isactive = 'Y'), " +
      "(SELECT min(c_bpartner_id) FROM c_bpartner " +
      "WHERE ad_client_id = ? AND isactive = 'Y'), " +
      "(SELECT coalesce(max(c_invoiceline_id), 0) + 2000000 FROM c_invoiceline), " +
      "(SELECT min(m_product_id) FROM m_product WHERE ad_client_id = ? AND isactive = 'Y'), " +
      "(SELECT min(c_uom_id) FROM c_uom WHERE ad_client_id IN (0, ?) AND isactive = 'Y')",
      Array[AnyRef](
        Integer.valueOf(client),
        Integer.valueOf(client),
        Integer.valueOf(client),
        Integer.valueOf(client),
        Integer.valueOf(client),
        Integer.valueOf(client),
        Integer.valueOf(client)))
    try {
      rs.next()
      InvoiceSeed(
        rs.getInt(1),
        rs.getInt(2),
        rs.getInt(3),
        rs.getInt(4),
        rs.getInt(5),
        rs.getInt(6),
        rs.getInt(7),
        rs.getInt(8),
        rs.getInt(9))
    } finally {
      DB.close(rs)
    }
  }    //	readSeedParams

  /**
    * Seed the given number of accounting facts on the record.
    */
  private def seedFacts(client: Int, invoiceId: Int, invoiceTableId: Int, count: Int): Unit = {
    Trx.run { name =>
      val rsPeriod = execQuery(
        "SELECT c_period_id FROM c_period " +
        "WHERE ad_client_id = ? AND startdate <= ?::date AND enddate >= ?::date",
        Array[AnyRef](
          Integer.valueOf(client),
          Today,
          Today))
      rsPeriod.next()
      val periodId = rsPeriod.getInt(1)
      DB.close(rsPeriod)

      val rsSchema = execQuery(
        "SELECT c.c_acctschema1_id, min(e.c_elementvalue_id) " +
        "FROM ad_clientinfo c, c_elementvalue e " +
        "WHERE c.ad_client_id = ? AND e.ad_client_id = ? AND e.isactive = 'Y' " +
        "GROUP BY c.c_acctschema1_id",
        Array[AnyRef](
          Integer.valueOf(client),
          Integer.valueOf(client)))
      rsSchema.next()
      val schemaId = rsSchema.getInt(1)
      val account = rsSchema.getInt(2)
      DB.close(rsSchema)

      val rsIds = execQuery(
        "SELECT coalesce(max(fact_acct_id), 0) + 1000000 FROM fact_acct",
        Array[AnyRef]())
      rsIds.next()
      var factId = rsIds.getInt(1)
      DB.close(rsIds)

      val dateAcct = Today
      val orgId = Organization.getAD_Org_ID
      val currency = currencyOf(client)
      for (_ <- 1 to count) {
        DB.executeUpdate(
          "INSERT INTO fact_acct (fact_acct_id, ad_client_id, ad_org_id, createdby, updatedby, " +
          "c_acctschema_id, account_id, c_currency_id, c_period_id, datetrx, dateacct, " +
          "ad_table_id, record_id, postingtype, amtsourcedr, amtsourcecr, amtacctdr, amtacctcr) " +
          "VALUES (?, ?, ?, 101, 101, ?, ?, ?, ?, ?, ?, ?, ?, 'P', 0, 0, 0, 0)",
          Array[AnyRef](
            Integer.valueOf(factId),
            Integer.valueOf(client),
            Integer.valueOf(orgId),
            Integer.valueOf(schemaId),
            Integer.valueOf(account),
            Integer.valueOf(currency),
            Integer.valueOf(periodId),
            dateAcct,
            dateAcct,
            Integer.valueOf(invoiceTableId),
            Integer.valueOf(invoiceId)),
          false, name)
        factId += 1
      }
    }
  }    //	seedFacts

  private def currencyOf(client: Int): Int = {
    var currency = 0
    Trx.run { name =>
      val rs = execQuery(
        "SELECT c_currency_id FROM c_acctschema " +
        "WHERE ad_client_id = ? AND isactive = 'Y' ORDER BY c_acctschema_id LIMIT 1",
        Array[AnyRef](Integer.valueOf(client)))
      rs.next()
      currency = rs.getInt(1)
      DB.close(rs)
    }
    currency
  }    //	currencyOf

  /**
    * Construct the posting Doc of the seeded invoice through the public
    * Doc factory, the same path the accounting engine uses.
    */
  private def newDocForInvoice(client: Int, invoiceId: Int): Doc = {
    val ass = MAcctSchema.getClientAcctSchema(Context, client)
    val doc = Doc.get(ass, "C_Invoice", invoiceId, null)
    assert(doc != null, "Doc not constructed for C_Invoice id=" + invoiceId)
    doc
  }    //	newDocForInvoice

  /**
    * Construct a Doc for the invoice, run the body with it, and always
    * release the Doc's transaction afterwards.
    */
  private def withDoc(client: Int, invoiceId: Int)(body: Doc => Unit): Unit = {
    val doc = newDocForInvoice(client, invoiceId)
    try
      body(doc)
    finally
      closeTrx(doc.getTrxName())
  }    //	withDoc

  /**
    * Call the package-private Doc.deleteAcct through reflection: the method
    * is internal, and its observable effect (the deleted fact rows and the
    * returned count) is asserted against the database.
    */
  private def callDeleteAcct(doc: Doc): Int = {
    val method = classOf[Doc].getDeclaredMethod("deleteAcct")
    method.setAccessible(true)
    method.invoke(doc).asInstanceOf[Integer]
  }    //	callDeleteAcct

  /**
    * Commit and close a Doc's own transaction. Trx.close commits an active
    * transaction before closing the connection.
    */
  private def closeTrx(trxName: String): Unit = {
    if (trxName == null) return
    val trx = Trx.get(trxName, false)
    if (trx != null)
      trx.close()
  }    //	closeTrx

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

  private def cleanup(client: Int, invoiceId: Int): Unit = {
    if (invoiceId == 0) return
    Trx.run { name =>
      DB.executeUpdate(
        "DELETE FROM fact_acct WHERE ad_client_id = ? AND record_id = ?",
        Array[AnyRef](
          Integer.valueOf(client),
          Integer.valueOf(invoiceId)),
        false, name)
      DB.executeUpdate(
        "DELETE FROM c_invoiceline WHERE c_invoice_id = ?",
        Array[AnyRef](Integer.valueOf(invoiceId)),
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

}    //	ValidateDocPostingTransaction
