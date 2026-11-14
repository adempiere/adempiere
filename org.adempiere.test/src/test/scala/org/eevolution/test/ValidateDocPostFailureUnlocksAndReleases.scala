/** ****************************************************************************
  * Product: Adempiere ERP & CRM Smart Business Solution                       *
  * This program is free software; you can redistribute it and/or modify it     *
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

import java.math.BigDecimal
import java.sql.ResultSet
import java.util.ArrayList
import java.util.logging.Level

import org.adempiere.Adempiere
import org.compiere.acct.{Doc, Fact}
import org.compiere.model.{MAcctSchema, MDocType, MInvoice, MTable}
import org.compiere.util.{CLogMgt, DB, Env, Ini, Login, Trx}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.util.Try

/**
  * Behavioral counterpart of the two source guards in
  * ValidateDocPostingTrxBoundaries for adempiere/adempiere#4433.
  *
  * Those guards read Doc.java and assert the calls are in place, so they fail
  * when someone deletes a line. They cannot prove the calls do what they claim.
  * These two scenarios drive the real exits and assert the observable effects:
  *
  *  - a post refused by loadDocumentDetails must leave the record unlocked, so
  *    the document posts again on a plain re-submit. lock() commits
  *    Processing='Y' on its own connection when the Doc manages its local
  *    transaction, so nothing but unlock() takes it back;
  *  - a Doc whose PO cannot be built must leave nothing behind, because the Trx
  *    cache is a CCache created with no expiration and post() never sees a
  *    document the constructor rejected.
  *
  * See adempiere/adempiere#4433 for the requirements these scenarios pin.
  */
class ValidateDocPostFailureUnlocksAndReleases
    extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  /** What the forced detail read reports, asserted verbatim. */
  private val ForcedDetailError = "spec: forced detail read error"

  /** Prefix of every transaction a Doc opens for itself. */
  private val PostingTrxPrefix = "Post_"

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

  /**
    * A document whose detail read reports an error, so post() takes the refusal
    * after lock() already committed Processing='Y'.
    */
  private class ForcedErrorDoc(ass: Array[MAcctSchema], rs: ResultSet)
      extends Doc(ass, classOf[MInvoice], rs, null, null) {

    override protected def loadDocumentDetails(): String = ForcedDetailError

    override def getBalance(): BigDecimal = Env.ZERO

    override def createFacts(as: MAcctSchema): ArrayList[Fact] = new ArrayList[Fact]()
  }    //	ForcedErrorDoc

  /**
    * A document class with no (Properties, ResultSet, String) constructor, so the
    * Doc constructor fails after it has registered its own transaction.
    */
  private class UnbuildableDoc(ass: Array[MAcctSchema])
      extends Doc(ass, classOf[String], null, null, null) {

    override protected def loadDocumentDetails(): String = null

    override def getBalance(): BigDecimal = Env.ZERO

    override def createFacts(as: MAcctSchema): ArrayList[Fact] = new ArrayList[Fact]()
  }    //	UnbuildableDoc

  Feature("a posting failure leaves the document postable and the transaction cache clean") {

    Scenario("a post refused by the detail read unlocks the record and releases its transaction") {
      val client = Env.getContextAsInt(Context, "#AD_Client_ID")
      val invoiceId = seedInvoice(client)
      var trxName: String = null

      try {
        Given("a document whose detail read reports an error")
        val ps = DB.prepareStatement(
          "SELECT * FROM C_Invoice WHERE C_Invoice_ID = ?", null)
        ps.setObject(1, Integer.valueOf(invoiceId))
        val rs = ps.executeQuery()
        assert(rs.next(), "seed invoice " + invoiceId + " not readable")
        val doc = new ForcedErrorDoc(MAcctSchema.getClientAcctSchema(Context, client), rs)
        DB.close(rs, ps)
        trxName = doc.getTrxName()
        assert(trxName != null && trxName.startsWith(PostingTrxPrefix),
          "a Doc without a caller transaction must open its own, got: " + trxName)

        When("the post is refused by the detail read")
        val error = doc.post(false, false)

        Then("the caller gets the reported reason, not a lock refusal")
        assert(error != null && error.contains(ForcedDetailError), "got: " + error)

        And("the record is not left locked, because lock() committed Processing='Y' " +
          "outside the document transaction")
        assert(!processingIsY(invoiceId),
          "the refusal left Processing='Y' committed on C_Invoice_ID=" + invoiceId)

        And("the transaction the Doc opened is gone from the cache, which never expires one")
        assert(Trx.get(trxName, false) == null,
          "the refused post left transaction " + trxName + " open")

        And("a plain re-submit reaches the detail read again instead of being refused by the lock")
        val resubmit = doc.post(false, false)
        assert(resubmit != null && resubmit.contains(ForcedDetailError),
          "a refused document only posts again with force, got: " + resubmit)
      } finally {
        closeTrx(trxName)
        cleanup(client, invoiceId)
      }
    }    //	Scenario

    Scenario("a document whose PO cannot be constructed leaves no transaction cached") {
      Given("no posting transaction of this shape cached yet")
      val ass = MAcctSchema.getClientAcctSchema(Context,
        Env.getContextAsInt(Context, "#AD_Client_ID"))
      val before = cachedPostingTrxNames

      When("the constructor cannot build the PO")
      val rejected = try {
        new UnbuildableDoc(ass)
        false
      } catch {
        case _: IllegalArgumentException => true
      }

      Then("the document is reported as unbuildable")
      assert(rejected,
        "the constructor accepted a class with no (Properties,ResultSet,String) constructor")

      And("nothing is left in the Trx cache, which never expires an entry")
      val leaked = cachedPostingTrxNames -- before
      assert(leaked.isEmpty,
        leaked.size + " posting transaction(s) left behind by a rejected document: " +
          leaked.mkString(", "))
    }    //	Scenario
  }    //	Feature

  /**
    * Posting transactions currently in the Trx cache. The cache is created
    * lazily, so a suite that has not opened one yet has none rather than one
    * fewer than an exception.
    */
  private def cachedPostingTrxNames: Set[String] =
    Try(Trx.getActiveTransactions.map(_.getTrxName()).toSet)
      .getOrElse(Set.empty[String])
      .filter(_.startsWith(PostingTrxPrefix))

  private def processingIsY(invoiceId: Int): Boolean = {
    val rs = execQuery("SELECT processing FROM c_invoice WHERE c_invoice_id = ?",
      Array[AnyRef](Integer.valueOf(invoiceId)))
    try {
      rs.next() && "Y".equals(rs.getString(1))
    } finally {
      DB.close(rs)
    }
  }    //	processingIsY

  private def closeTrx(trxName: String): Unit = {
    if (trxName == null) return
    val trx = Trx.get(trxName, false)
    if (trx != null)
      trx.close()
  }    //	closeTrx

  /**
    * Seed a committed, processed, unposted and unlocked invoice, the state a
    * nightly post takes, so lock() succeeds and post() reaches the detail read.
    */
  private def seedInvoice(client: Int): Int = {
    val seed = readSeedParams(client)
    Trx.run { name =>
      val orgId = Organization.getAD_Org_ID
      val docType = MDocType.getDocType("ARI", orgId)
      val dateAcct = Today
      val rows = DB.executeUpdate(
        "INSERT INTO c_invoice (c_invoice_id, ad_client_id, ad_org_id, createdby, updatedby, " +
        "documentno, docstatus, docaction, processed, posted, processing, isactive, " +
        "c_doctype_id, c_doctypetarget_id, c_bpartner_id, c_bpartner_location_id, " +
        "c_currency_id, paymentrule, c_paymentterm_id, m_pricelist_id, dateinvoiced, dateacct) " +
        "VALUES (?, ?, ?, 101, 101, ?, ?, 'CL', ?, ?, ?, 'Y', ?, ?, ?, ?, ?, 'P', ?, ?, ?, ?)",
        Array[AnyRef](
          Integer.valueOf(seed.invoiceId),
          Integer.valueOf(client),
          Integer.valueOf(orgId),
          "INV-TEST-4492B-" + seed.invoiceId,
          "CO",
          "Y",
          "N",
          "N",
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
      assert(rows == 1, "seed invoice not inserted")
    }
    seed.invoiceId
  }    //	seedInvoice

  private case class InvoiceSeed(invoiceId: Int, currency: Int, bpartnerLocation: Int,
                                 paymentTerm: Int, priceList: Int, bpartner: Int)

  private def readSeedParams(client: Int): InvoiceSeed = {
    val rs = execQuery(
      "SELECT (SELECT coalesce(max(c_invoice_id), 0) + 3000000 FROM c_invoice), " +
      "(SELECT a.c_currency_id FROM c_acctschema a " +
      "WHERE a.ad_client_id = ? AND a.isactive = 'Y' ORDER BY a.c_acctschema_id LIMIT 1), " +
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
        Integer.valueOf(client)))
    try {
      rs.next()
      InvoiceSeed(
        rs.getInt(1),
        rs.getInt(2),
        rs.getInt(3),
        rs.getInt(4),
        rs.getInt(5),
        rs.getInt(6))
    } finally {
      DB.close(rs)
    }
  }    //	readSeedParams

  /** The cleanup deletes accounting rows by table, so it cannot touch another document's. */
  private def cleanup(client: Int, invoiceId: Int): Unit = {
    if (invoiceId == 0) return
    Trx.run { name =>
      DB.executeUpdate(
        "DELETE FROM fact_acct WHERE ad_client_id = ? AND ad_table_id = ? AND record_id = ?",
        Array[AnyRef](
          Integer.valueOf(client),
          Integer.valueOf(MTable.get(Env.getCtx, "C_Invoice").getAD_Table_ID()),
          Integer.valueOf(invoiceId)),
        false, name)
      DB.executeUpdate(
        "DELETE FROM c_invoice WHERE c_invoice_id = ?",
        Array[AnyRef](Integer.valueOf(invoiceId)),
        false, name)
    }
  }    //	cleanup

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

}    //	ValidateDocPostFailureUnlocksAndReleases
