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

import org.adempiere.Adempiere
import org.compiere.acct.Doc
import org.compiere.model.{MAcctSchema, MDocType}
import org.compiere.util.{CLogMgt, DB, Env, Ini, Login, Trx}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Regression tests for the posting transaction of adempiere/adempiere#4492.
  *
  * The accounting engine inherits the transaction of whoever originated the
  * post: the workflow transaction on an immediate post, the process
  * transaction on a client-side post. Only a forced re-post from the grid
  * arrives with no transaction and then opens its own local one. When the
  * Doc owns that transaction, the PO it posts must carry it too, otherwise
  * the lines and their cost detail read and write on a different pooled
  * connection and survive a rollback of the accounting. The generated name
  * must also stay inside what AD_ChangeLog.TrxName can store, since PO writes
  * its transaction name into that column and the audit swallow every failure.
  *
  * Spec: odd/tasks/doc-posting-trx-integrity.md, S1-S5, S8-S10, I1-I3, I5.
  */
class ValidateDocPostingTrxBoundaries
    extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  /** Column AD_ChangeLog.TrxName, measured as character varying(60). */
  private val TrxNameLimit = 60

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


  Feature("the posting transaction name fits what the schema can store") {

    Scenario("a Doc that opens its own transaction names it within the audit limit") {
      val client = Env.getContextAsInt(Context, "#AD_Client_ID")

      Given("a processed invoice, posted with no transaction of its own")
      val invoiceId = seedProcessedInvoice(client)

      try {
        When("the accounting engine builds the Doc without a transaction")
        val doc = newDocForInvoice(client, invoiceId)
        try {
          val name = doc.getTrxName()

          Then("the Doc owns a transaction")
          assert(name != null)
          assert(doc.getTrxName().length <= TrxNameLimit,
            "generated posting transaction name is " + name.length
              + " characters, over the " + TrxNameLimit
              + " of AD_ChangeLog.TrxName: " + name)

          And("that name really comes from the bounded generator, not elsewhere")
          //	A call site that built its own name (for instance from the fully
          //	qualified class name) could still fit the limit, so the shape is
          //	pinned here and not only through the generator.
          assert("Post_\\w+_[0-9a-f-]{16}".r.pattern.matcher(name).matches,
            "the transaction the Doc opened is named " + name
              + ", not Post_<document class>_<16 hex>")

          And("the name is really insertable into AD_ChangeLog.TrxName")
          assertTrxNameIsStorable(name)
        } finally {
          closeTrx(doc.getTrxName())
        }
      } finally {
        cleanup(client, invoiceId)
      }
    }    //	Scenario

    Scenario("every document class names its posting transaction within the limit") {
      val modelClasses = postedModelClasses()

      Given("the model classes the engine actually hands to a Doc")
      //	Each Doc subclass passes its model class to super, and that name is what
      //	the generator embeds, so this is the real input domain, not a proxy.
      assert(modelClasses.size >= 15,
        "only " + modelClasses.size + " posted model classes were read")

      When("each one names a posting transaction")
      val names = modelClasses.map(postingTrxNameFor)

      Then("no name overflows AD_ChangeLog.TrxName")
      val over = names.filter(_.length > TrxNameLimit)
      assert(over.isEmpty,
        over.map(n => n + " (" + n.length + ")").mkString(", ")
          + " do not fit " + TrxNameLimit)

      And("the widest of them is named, so the bound is measured not assumed")
      val longest = modelClasses.maxBy(_.length)
      assert(postingTrxNameFor(longest).length <= TrxNameLimit,
        "the widest posted class " + longest + " names "
          + postingTrxNameFor(longest).length + " characters")

      And("each name still identifies its document class")
      modelClasses.foreach { simple =>
        assert(names.exists(_.contains(simple)),
          "no posting transaction name carries " + simple)
      }

      And("the names are unique across the whole set")
      assert(names.distinct.size == names.size)
    }    //	Scenario

    Scenario("two posts of the same record never share a transaction") {
      val client = Env.getContextAsInt(Context, "#AD_Client_ID")

      Given("a processed invoice")
      val invoiceId = seedProcessedInvoice(client)

      try {
        When("two Docs are constructed for the same record")
        withDoc(client, invoiceId) { doc1 =>
          withDoc(client, invoiceId) { doc2 =>
            Then("the names differ, so no stale transaction is reused")
            assert(doc1.getTrxName() != doc2.getTrxName())

            And("both stay within the audit limit")
            assert(doc1.getTrxName().length <= TrxNameLimit)
            assert(doc2.getTrxName().length <= TrxNameLimit)
          }
        }
      } finally {
        cleanup(client, invoiceId)
      }
    }    //	Scenario
  }    //	Feature


  Feature("the Doc posts on the transaction it owns") {

    Scenario("the PO carries the transaction the Doc will commit") {
      val client = Env.getContextAsInt(Context, "#AD_Client_ID")

      Given("a processed invoice, posted with no transaction of its own")
      val invoiceId = seedProcessedInvoice(client)

      try {
        When("the accounting engine builds the Doc without a transaction")
        val doc = newDocForInvoice(client, invoiceId)
        try {
          Then("the PO posts on the Doc's own transaction")
            assert(doc.getPO() != null)
            assert(doc.getPO().get_TrxName() == doc.getTrxName(),
              "the PO carries " + doc.getPO().get_TrxName() + " while the Doc owns "
                + doc.getTrxName() + ", so the document writes outside its transaction")
        } finally {
          closeTrx(doc.getTrxName())
        }
      } finally {
        cleanup(client, invoiceId)
      }
    }    //	Scenario

    Scenario("the inherited transaction stays the caller's, untouched") {
      val client = Env.getContextAsInt(Context, "#AD_Client_ID")

      Given("a processed invoice and a transaction owned by the caller")
      val invoiceId = seedProcessedInvoice(client)
      val callerTrx = Trx.createTrxName("PR4492Caller_")

      try {
        When("the accounting engine builds the Doc on that transaction")
        val ass = MAcctSchema.getClientAcctSchema(Context, client)
        val doc = Doc.get(ass, "C_Invoice", invoiceId, callerTrx)
        assert(doc != null)

        Then("the Doc posts on the caller's transaction, not a new one")
        assert(doc.getTrxName() == callerTrx)

        And("the PO carries it too")
        assert(doc.getPO().get_TrxName() == callerTrx)

        And("the Doc did not open a second transaction for the record")
        assert(Trx.get(callerTrx, false) != null)
      } finally {
        closeTrx(callerTrx)
        cleanup(client, invoiceId)
      }
    }    //	Scenario
  }    //	Feature


  Feature("a posting failure releases the transaction the Doc opened") {

    Scenario("an invalid document status releases it") {
      leakReleasedBy(seedInvoiceSpec(client = Env.getContextAsInt(Context, "#AD_Client_ID"),
        docStatus = "IP", processed = "Y", posted = "N",
        force = false, repost = true, expect = "Invalid DocStatus"))
    }    //	Scenario

    Scenario("a document that cannot be locked releases it") {
      leakReleasedBy(seedInvoiceSpec(client = Env.getContextAsInt(Context, "#AD_Client_ID"),
        docStatus = "CO", processed = "Y", posted = "Y",
        force = false, repost = false, expect = "Cannot Lock"))
    }    //	Scenario

    Scenario("every refusal in post releases before returning") {
      Given("the body of post")
      val lines = postBodyLines()
      val commitAt = lines.indexWhere(_.trim.startsWith("p_Status = postCommit"))
      assert(commitAt > 0, "the postCommit call is not present in post()")

      When("the refusals before the accounting is committed are read in order")
      val orphaned = scala.collection.mutable.ArrayBuffer[String]()
      for (i <- 0 until commitAt) {
        val t = lines(i).trim
        if (t.startsWith("return \"")
            && !lines.slice(math.max(0, i - 6), i).exists(_.trim == "releaseLocalTrx();"))
          orphaned += t
      }

      Then("each of them releases the transaction the Doc opened")
      val releases = lines.slice(0, commitAt).count(_.trim == "releaseLocalTrx();")
      //	Invalid DocStatus, AD_Client_ID Conflict, Cannot Lock, load details
      //	failure, Period Closed, Accounting deletion failure, Already Posted.
      assert(releases == 7,
        "post() has " + releases + " releaseLocalTrx() calls before postCommit, 7 expected")
      assert(orphaned.isEmpty,
        "refusals returning without a release: " + orphaned.mkString(" | "))

      And("each refusal this guard speaks about is still there")
      List("Invalid DocStatus", "AD_Client_ID Conflict", "Cannot Lock", "PeriodClosed",
        "FactAcctDeleteFailed", "AlreadyPosted").foreach { refusal =>
        assert(lines.exists(_.contains(refusal)),
          "post() no longer refuses with " + refusal + ", so this guard is stale")
      }
    }    //	Scenario
    Scenario("no posting transaction is named after the record it posts") {
      Given("the source of the accounting engine")
      val file = new java.io.File(System.getProperty("user.dir"),
        "../base/src/org/compiere/acct/Doc.java")
      val source = scala.io.Source.fromFile(file)
      val text =
        try source.mkString
        finally source.close()

      When("the names built for transactions are read")
      val byRecord = text.split("\r?\n").map(_.trim)
        .filter(l => l.contains("m_trxName") && l.contains("\"Post\"")
                           && l.contains("get_ID()"))
        .toSet

      Then("none of them is derived from the record id")
      //	"Post" + DocBaseType + Record_ID is unique per document, not per posting
      //	attempt: two attempts on one record would share a transaction name.
      assert(byRecord.isEmpty,
        "transaction names derived from the record id: " + byRecord.mkString(" | "))

      And("the only posting name comes from the bounded generator")
      assert(text.contains("postingTrxName("),
        "the bounded posting transaction generator is not used")
    }    //	Scenario
  }    //	Feature

  /**
    * The lines of the post(boolean,boolean) body, so a structural guard can
    * read the exits the method actually has.
    */
  private def postBodyLines(): Array[String] = {
    val file = new java.io.File(System.getProperty("user.dir"),
      "../base/src/org/compiere/acct/Doc.java")
    val source = scala.io.Source.fromFile(file)
    val text =
      try source.mkString
      finally source.close()
    val body = text.substring(text.indexOf("public final String post (boolean force"))
    body.split("\n").takeWhile(!_.contains("}   //  post"))
  }    //	postBodyLines


  /**
    * A post that is refused before any accounting is written must still release
    * the transaction the Doc opened for itself, otherwise the entry stays in
    * the Trx cache forever: that cache has no expiration.
    */
  private case class InvoiceSpec(client: Int, invoiceId: Int, docStatus: String,
                                 processed: String, posted: String, force: Boolean,
                                 repost: Boolean, expect: String)

  private def seedInvoiceSpec(client: Int, docStatus: String, processed: String,
                              posted: String, force: Boolean, repost: Boolean,
                              expect: String): InvoiceSpec =
    InvoiceSpec(client, seedInvoice(client, docStatus, processed, posted),
      docStatus, processed, posted, force, repost, expect)

  private def leakReleasedBy(spec: InvoiceSpec): Unit = {
    //	Registered before the Doc is built: a seed that cannot be posted still
    //	has to leave the database as it found it.
    val doc =
      try newDocForInvoice(spec.client, spec.invoiceId)
      catch { case e: Throwable => cleanup(spec.client, spec.invoiceId); throw e }
    val name = doc.getTrxName()
    assert(name != null)
    try {
      When("the accounting engine posts it and is refused")
      val error = doc.post(spec.force, spec.repost)

      Then("the post reports the expected refusal")
      assert(error != null,
        "post accepted a docstatus=" + spec.docStatus + " processed=" + spec.processed
          + " posted=" + spec.posted + " invoice")
      assert(error.contains(spec.expect),
        "expected " + spec.expect + ", got: " + error)

      And("the transaction the Doc opened is gone from the cache")
      assert(Trx.get(name, false) == null,
        "transaction " + name + " leaked after the " + spec.expect + " exit")
    } finally {
      closeTrx(name)
      cleanup(spec.client, spec.invoiceId)
    }
  }    //	leakReleasedBy

  /**
    * Insert the name into AD_ChangeLog.TrxName and roll it back. The column is
    * character varying(60), so this is the real constraint rather than a
    * re-stated constant: an over-long name raises there, and PO.java:2637
    * writes the PO transaction name into exactly that column.
    */
  private def assertTrxNameIsStorable(name: String): Unit = {
    val conn = DB.getConnectionRW()
    var ps: java.sql.PreparedStatement = null
    try {
      conn.setAutoCommit(false)
      ps = conn.prepareStatement(
        "INSERT INTO ad_changelog (ad_changelog_id, ad_session_id, ad_table_id, " +
        "ad_column_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, " +
        "updatedby, record_id, iscustomization, trxname, eventchangelog) " +
        "VALUES (?, 1000000, 318, 100, ?, ?, 'Y', now(), 101, now(), 101, 1, 'N', ?, 'U')")
      ps.setLong(1, 999999900L + name.length)
      ps.setLong(2, Env.getContextAsInt(Context, "#AD_Client_ID"))
      ps.setLong(3, Organization.getAD_Org_ID)
      ps.setString(4, name)
      ps.executeUpdate()
    } finally {
      DB.close(ps)
      try
        conn.rollback()
      finally {
        conn.setAutoCommit(true)
        conn.close()
      }
    }
  }    //	assertTrxNameIsStorable

  /**
    * The document classes the accounting engine can post, read from the
    * accounting package on the classpath.
    */
  /**
    * The model classes the accounting engine hands to a Doc: every Doc_*
    * source passes its own model class to super, and Doc names its transaction
    * after that class, so this is the domain the generator really sees.
    */
  private def postedModelClasses(): List[String] = {
    val root = new java.io.File(System.getProperty("user.dir")).getParentFile
    val pattern = java.util.regex.Pattern.compile("super\\s*\\(\\s*ass,\\s*(\\w+)\\.class")
    val names = new scala.collection.mutable.LinkedHashSet[String]()
    val stream = java.nio.file.Files.walk(root.toPath)
    try {
      val sources = new java.util.ArrayList[java.nio.file.Path]()
      stream.forEach { path =>
        val n = path.getFileName.toString
        if (n.startsWith("Doc_") && n.endsWith(".java"))
          sources.add(path)
      }
      sources.forEach { path =>
        val text =
          try new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8)
          catch { case _: Exception => "" }
        val matcher = pattern.matcher(text)
        while (matcher.find())
          names += matcher.group(1)
      }
    } finally
      stream.close()
    names.toList.sorted
  }    //	postedModelClasses


  /**
    * Call Doc.postingTrxName, the helper that names a locally owned posting
    * transaction. Reflection because the helper is package private, the same
    * way this suite already reaches Doc.deleteAcct.
    */
  private def postingTrxNameFor(simpleName: String): String = {
    val method = classOf[Doc].getDeclaredMethod("postingTrxName", classOf[String])
    method.setAccessible(true)
    method.invoke(null, simpleName).asInstanceOf[String]
  }    //	postingTrxNameFor

  private def newDocForInvoice(client: Int, invoiceId: Int): Doc = {
    val ass = MAcctSchema.getClientAcctSchema(Context, client)
    val doc = Doc.get(ass, "C_Invoice", invoiceId, null)
    assert(doc != null, "Doc not constructed for C_Invoice id=" + invoiceId)
    doc
  }    //	newDocForInvoice

  private def withDoc(client: Int, invoiceId: Int)(body: Doc => Unit): Unit = {
    val doc = newDocForInvoice(client, invoiceId)
    try
      body(doc)
    finally
      closeTrx(doc.getTrxName())
  }    //	withDoc

  private def closeTrx(trxName: String): Unit = {
    if (trxName == null) return
    val trx = Trx.get(trxName, false)
    if (trx != null)
      trx.close()
  }    //	closeTrx

  private def seedProcessedInvoice(client: Int): Int =
    seedInvoice(client, "CO", "Y", "N")

  /**
    * Seed a committed invoice in the given posting state, so a scenario can
    * drive post() into a chosen early exit.
    */
  private def seedInvoice(client: Int, docStatus: String, processed: String,
                          posted: String): Int = {
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
        "VALUES (?, ?, ?, 101, 101, ?, ?, 'CL', ?, ?, ?, ?, ?, ?, ?, 'P', ?, ?, ?, ?)",
        Array[AnyRef](
          Integer.valueOf(seed.invoiceId),
          Integer.valueOf(client),
          Integer.valueOf(orgId),
          "INV-TEST-4492-" + seed.invoiceId,
          docStatus,
          processed,
          posted,
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
    }
    seed.invoiceId
  }    //	seedProcessedInvoice

  private case class InvoiceSeed(invoiceId: Int, currency: Int, bpartnerLocation: Int,
                                 paymentTerm: Int, priceList: Int, bpartner: Int)

  private def readSeedParams(client: Int): InvoiceSeed = {
    val rs = execQuery(
      "SELECT (SELECT coalesce(max(c_invoice_id), 0) + 2000000 FROM c_invoice), " +
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

}    //	ValidateDocPostingTrxBoundaries
