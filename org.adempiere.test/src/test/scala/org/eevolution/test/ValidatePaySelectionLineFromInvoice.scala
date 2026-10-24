/** ****************************************************************************
  * Product: Adempiere ERP & CRM Smart Business Solution                       *
  * This program is free software; you can redistribute it and/or modify it    *
  * under the terms version 2 of the GNU General Public License as published   *
  * by the Free Software Foundation. This program is distributed in the hope    *
  * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied  *
  * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
  * See the GNU General Public License for more details.                       *
  * You should have received a copy of the GNU General Public License along    *
  * with this program; if not, write to the Free Software Foundation, Inc.,    *
  * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
  * For the text or an alternative of this public license, you may reach us    *
  * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
  * Contributor(s): Victor Perez www.e-evolution.com                           *
  * ****************************************************************************/

package org.eevolution.test

import java.math.BigDecimal
import java.sql.Timestamp
import java.util.Calendar
import java.util.logging.Level

import org.adempiere.Adempiere
import org.adempiere.core.domains.models.{X_C_BP_BankAccount, X_C_DocType, X_C_Invoice, X_C_PaySelectionLine}
import org.compiere.model.{MInvoice, MInvoicePaySchedule, MBPartner, MBPBankAccount, MBPartnerLocation, MPaySelection, MPaySelectionLine, MPaymentTerm, MPriceList, MCurrency, Query}
import org.compiere.util.{CLogMgt, DB, Env, Ini, Login}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Regression test for adempiere/adempiere#4313, #4314 and #4317:
  * pay selection lines created from invoices/orders take the payment
  * rule, description and business partner bank account from the source
  * document, and creating the lines must never fail with a
  * NullPointerException in MPaySelectionLine.setPaymentRule.
  *
  * Before the fix, setPaymentRule dereferenced the current (null) value
  * of a new line, so the MPaySelectionLine(paySelection, line, rule)
  * constructor raised a NullPointerException. The isSOTrx setInvoice
  * overload read the still-null invoice field before its local invoice
  * was created, with the same failure.
  *
  * The scenarios run on in-memory models inside the rolled-back test
  * transaction: no pay selection line, invoice or bank account rows are
  * committed. The metadata scenario does write its accounts payable
  * fixture (invoice, payment schedule and, when needed, the vendor flag)
  * inside that same transaction, so the rollback removes it again.
  */
class ValidatePaySelectionLineFromInvoice
    extends AnyFeatureSpec
    with AdempiereTestCase
    with GivenWhenThen {

  private def newLine: MPaySelectionLine =
    new MPaySelectionLine(Context, 0, trxName)

  /**
    * Start Adempiere with the standard GardenWorld demo credentials.
    * The shared AdempiereStartup trait still logs in as SuperUser, which
    * does not exist in the standard 3.9 install of client GardenWorld.
    */
  override def startup(): Unit = {
    Adempiere.startup(false)
    CLogMgt.setLevel(Level.ALL)
    CLogMgt.setLoggerLevel(Level.ALL, null)

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
  }    //  startup

  private def runQuietly(body: => Unit): Option[Throwable] =
    scala.util.control.Exception.nonFatalCatch.either(body).left.toOption

  /**
    * Vendor of the accounts payable fixture: the named demo vendor when the
    * logged in client has it active with a committed bank account, otherwise
    * any active vendor that already owns an active bank account.
    * MPaySelectionLine.setInvoice reads the bank accounts through a query that
    * runs outside the test transaction, so only a committed bank account can
    * reach the pay selection line. The named vendor branch uses that same
    * lookup: a named vendor without a committed account falls through to a
    * vendor that has one, instead of failing in the Given step.
    */
  private def apVendor: MBPartner = {
    val named = MBPartner.get(Context, "C&K Wholesale Supply")
    if (named != null && named.isActive &&
        !MBPBankAccount.getByPartner(Context, named.getC_BPartner_ID).isEmpty)
      named
    else {
      val vendor = partnerWithBankAccount("IsVendor='Y'")
      if (vendor != null)
        vendor
      else {
        // No vendor of this demo client owns a bank account, so the fixture
        // promotes the partner that does to vendor inside the rolled back test
        // transaction: the committed bank account still reaches the line and
        // the vendor flag disappears with the rollback.
        val promoted = partnerWithBankAccount("IsVendor<>'Y'")
        if (promoted != null) {
          promoted.setIsVendor(true)
          promoted.saveEx(trxName)
        }
        promoted
      }
    }
  }

  /** First active partner matching extraWhere that owns an active bank account. */
  private def partnerWithBankAccount(extraWhere: String): MBPartner = {
    val whereClause = "IsActive='Y' AND " + extraWhere +
      " AND C_BPartner_ID IN (SELECT C_BPartner_ID FROM C_BP_BankAccount WHERE IsActive='Y')"
    val partner: MBPartner = new Query(Context, "C_BPartner", whereClause, trxName)
      .setOrderBy("C_BPartner_ID")
      .first()
    partner
  }

  /**
    * Active bank accounts of the vendor in the order MPaySelectionLine
    * .setInvoice reads them, creating one when the vendor has none.
    */
  private def bankAccountsOf(vendor: MBPartner): java.util.List[MBPBankAccount] = {
    val accounts = MBPBankAccount.getByPartner(Context, vendor.getC_BPartner_ID)
    if (!accounts.isEmpty)
      accounts
    else {
      val account = new MBPBankAccount(Context, 0, trxName)
      account.setC_BPartner_ID(vendor.getC_BPartner_ID)
      account.setA_Name("PR4313-Bank")
      account.setAccountNo("PR4313-Bank")
      account.setBankAccountType(X_C_BP_BankAccount.BANKACCOUNTTYPE_Checking)
      account.setIsActive(true)
      account.saveEx(trxName)
      MBPBankAccount.getByPartner(Context, vendor.getC_BPartner_ID)
    }
  }

  /** Active accounts payable invoice document type of the client or template. */
  private def apInvoiceDocType: X_C_DocType = {
    val whereClause = "DocBaseType=? AND IsSOTrx='N' AND IsActive='Y' AND AD_Client_ID IN (0,?)"
    val docType: X_C_DocType = new Query(Context, "C_DocType", whereClause, trxName)
      .setParameters(X_C_DocType.DOCBASETYPE_APInvoice, Env.getAD_Client_ID(Context))
      .setOrderBy("AD_Client_ID DESC, C_DocType_ID")
      .first()
    docType
  }

  /** Currency of the fixture: 102 when it exists, else the client accounting currency. */
  private def apCurrencyId: Int = {
    val currency: MCurrency = new Query(Context, "C_Currency", "C_Currency_ID=? AND IsActive='Y'", trxName)
      .setParameters(102)
      .first()
    if (currency != null)
      currency.getC_Currency_ID
    else
      DB.getSQLValue(trxName,
        "SELECT ca.C_Currency_ID FROM C_AcctSchema ca" +
          " JOIN AD_ClientInfo ci ON ci.C_AcctSchema1_ID = ca.C_AcctSchema_ID" +
          " WHERE ci.AD_Client_ID=?",
        Env.getAD_Client_ID(Context))
  }

  /**
    * Accounts payable invoice header for the metadata scenario. The dictionary
    * marks partner location, payment term and price list as mandatory, so the
    * fixture resolves them from the logged in client instead of leaving them at
    * zero, which would abort the save.
    */
  private def newApInvoice(vendor: MBPartner, docType: X_C_DocType,
      grandTotal: BigDecimal): MInvoice = {
    val invoice = new MInvoice(Context, 0, trxName)
    invoice.setIsSOTrx(false)
    invoice.setC_DocType_ID(docType.getC_DocType_ID)
    invoice.setC_DocTypeTarget_ID(docType.getC_DocType_ID)
    invoice.setBPartner(vendor)
    invoice.setC_Currency_ID(apCurrencyId)
    invoice.setDateInvoiced(new Timestamp(System.currentTimeMillis()))
    invoice.setDescription("PR4313 metadata fixture")
    invoice.setGrandTotal(grandTotal)
    if (invoice.getC_BPartner_Location_ID == 0)
      invoice.setC_BPartner_Location_ID(apPartnerLocationId(vendor))
    if (invoice.getC_PaymentTerm_ID == 0)
      invoice.setC_PaymentTerm_ID(apPaymentTermId)
    if (invoice.getM_PriceList_ID == 0)
      invoice.setM_PriceList_ID(apPriceListId)
    invoice.saveEx(trxName)
    invoice.load(trxName)
    invoice
  }

  /** Payment schedule of the fixture invoice, due 30 days after the invoice date. */
  private def newApPaySchedule(invoice: MInvoice): MInvoicePaySchedule = {
    val calendar = Calendar.getInstance()
    calendar.setTime(invoice.getDateInvoiced)
    calendar.add(Calendar.DAY_OF_MONTH, 30)
    val dueDate = new Timestamp(calendar.getTimeInMillis)
    val schedule = new MInvoicePaySchedule(Context, 0, trxName)
    schedule.setC_Invoice_ID(invoice.getC_Invoice_ID)
    schedule.setDueDate(dueDate)
    schedule.setDiscountDate(invoice.getDateInvoiced)
    schedule.setDueAmt(invoice.getGrandTotal)
    schedule.setDiscountAmt(BigDecimal.ZERO)
    schedule.saveEx(trxName)
    schedule.load(trxName)
    schedule
  }

  /** First active bill to location of the partner. */
  private def apPartnerLocationId(vendor: MBPartner): Int = {
    val location: MBPartnerLocation = new Query(Context, "C_BPartner_Location",
      "C_BPartner_ID=? AND IsActive='Y'", trxName)
      .setParameters(vendor.getC_BPartner_ID)
      .setOrderBy("C_BPartner_Location_ID")
      .first()
    if (location != null) location.getC_BPartner_Location_ID else 0
  }

  /** First active payment term of the logged in client or template. */
  private def apPaymentTermId: Int = {
    val term: MPaymentTerm = new Query(Context, "C_PaymentTerm",
      "IsActive='Y' AND AD_Client_ID IN (0,?)", trxName)
      .setParameters(Env.getAD_Client_ID(Context))
      .setOrderBy("AD_Client_ID DESC, C_PaymentTerm_ID")
      .first()
    if (term != null) term.getC_PaymentTerm_ID else 0
  }

  /** First active vendor price list of the logged in client or template. */
  private def apPriceListId: Int = {
    val priceList: MPriceList = new Query(Context, "M_PriceList",
      "IsActive='Y' AND IsSOPriceList='N' AND AD_Client_ID IN (0,?)", trxName)
      .setParameters(Env.getAD_Client_ID(Context))
      .setOrderBy("AD_Client_ID DESC, M_PriceList_ID")
      .first()
    if (priceList != null) priceList.getM_PriceList_ID else 0
  }

  Feature("Pay selection lines created from invoices") {
    info("setPaymentRule must be null-safe on a new line (NPE regression)")

    Scenario("setting a supported payment rule on a new line does not throw") {
      Given("a new pay selection line with the standard default payment rule")
      val line = newLine
      assert(line.getPaymentRule == X_C_PaySelectionLine.PAYMENTRULE_Check,
        "a new pay selection line must start with the standard check rule")
      When("the credit card payment rule is set")
      val error = runQuietly(
        line.setPaymentRule(X_C_PaySelectionLine.PAYMENTRULE_CreditCard)
      )
      Then("no exception is raised")
      assert(error.isEmpty, "setPaymentRule must not raise on a new line")
      Then("the line holds the credit card rule")
      assert(line.getPaymentRule == X_C_PaySelectionLine.PAYMENTRULE_CreditCard,
        "the incoming credit card rule must be kept")
    }

    Scenario("setting a null payment rule on a new line falls back to direct deposit") {
      Given("a new pay selection line with the standard default payment rule")
      val line = newLine
      When("the payment rule is set to null")
      val error = runQuietly(line.setPaymentRule(null))
      Then("no exception is raised")
      assert(error.isEmpty, "setPaymentRule(null) must not raise on a new line")
      Then("the standard direct deposit rule is set")
      assert(line.getPaymentRule == X_C_PaySelectionLine.PAYMENTRULE_DirectDeposit,
        "a null rule must fall back to the standard direct deposit rule")
    }

    Scenario("an incoming payment rule is kept while the current rule is supported") {
      Given("a new pay selection line with the standard default payment rule")
      val line = newLine
      When("the cash payment rule is set from an invoice")
      val error =
        runQuietly(line.setPaymentRule(X_C_PaySelectionLine.PAYMENTRULE_Cash))
      Then("no exception is raised")
      assert(error.isEmpty, "setPaymentRule must not raise on a new line")
      Then("the incoming cash rule is kept, preserving the current-value semantics")
      assert(line.getPaymentRule == X_C_PaySelectionLine.PAYMENTRULE_Cash,
        "an incoming rule must be kept while the current rule is a supported one")
    }

    info("the isSOTrx setInvoice overload must read an initialized invoice")

    Scenario("linking a new invoice through the isSOTrx overload does not throw") {
      Given("a new pay selection line whose invoice field is null")
      val line = newLine
      When("setInvoice with the isSOTrx flag is called with a new invoice")
      val error = runQuietly(
        line.setInvoice(
          0,
          0,
          false,
          new BigDecimal("100.00"),
          new BigDecimal("50.00"),
          BigDecimal.ZERO
        )
      )
      Then("no exception is raised")
      assert(error.isEmpty,
        "the isSOTrx setInvoice overload must not raise on a new line")
      Then("the difference amount is computed from the open and pay amounts")
      assert(line.getDifferenceAmt.compareTo(new BigDecimal("50.00")) == 0,
        "openAmt - payAmt - discountAmt must be 50.00")
    }

    info("the isSOTrx setInvoice overload must copy the invoice metadata")

    Scenario("invoice metadata is copied through the isSOTrx overload") {
      Given("an accounts payable invoice with a vendor, a bank account and a payment schedule")
      val vendor = apVendor
      assert(vendor != null,
        "the logged in client must expose an active vendor owning a bank account")
      val bankAccounts = bankAccountsOf(vendor)
      assert(!bankAccounts.isEmpty,
        "the vendor must own at least one active bank account")
      val grandTotal = new BigDecimal("100.00")
      val invoice = newApInvoice(vendor, apInvoiceDocType, grandTotal)
      val schedule = newApPaySchedule(invoice)
      When("the pay selection line is linked to that invoice through the isSOTrx overload")
      val line = newLine
      val openAmt = grandTotal
      val payAmt = new BigDecimal("95.00")
      val discountAmt = new BigDecimal("5.00")
      val error = runQuietly(
        line.setInvoice(
          invoice.getC_Invoice_ID,
          schedule.getC_InvoicePaySchedule_ID,
          false,
          openAmt,
          payAmt,
          discountAmt
        )
      )
      Then("no non-fatal exception is raised")
      assert(error.isEmpty,
        "the isSOTrx setInvoice overload must not raise on a real invoice")
      Then("the description is copied from the invoice")
      assert(line.getDescription == invoice.getDescription,
        "the line description must be the invoice description")
      And("the vendor is copied from the invoice")
      assert(line.getC_BPartner_ID == invoice.getC_BPartner_ID,
        "the line vendor must be the invoice vendor")
      And("the first bank account of the vendor is copied")
      assert(line.getC_BP_BankAccount_ID == bankAccounts.get(0).getC_BP_BankAccount_ID,
        "the line bank account must be the first active account of the vendor")
      And("the difference amount is the open amount minus pay and discount")
      assert(line.getDifferenceAmt.compareTo(
        openAmt.subtract(payAmt).subtract(discountAmt)) == 0,
        "openAmt - payAmt - discountAmt must be 5.00")
    }

    info("pay selection lines keep the invoice payment rule from the create-from-invoice path")

    Scenario("a pay selection line created from an invoice keeps its payment rule") {
      Given("an accounts payable invoice with a direct debit payment rule")
      val vendor = apVendor
      val invoice = newApInvoice(vendor, apInvoiceDocType, new BigDecimal("100.00"))
      invoice.setPaymentRule(X_C_Invoice.PAYMENTRULE_DirectDebit)
      invoice.saveEx(trxName)
      invoice.load(trxName)
      val schedule = newApPaySchedule(invoice)
      When("a pay selection line is created with the invoice rule and linked like PSCreateFromInvoice")
      val paySelection = new MPaySelection(Context, 0, trxName)
      val line = new MPaySelectionLine(paySelection, 10, invoice.getPaymentRule())
      val openAmt = invoice.getGrandTotal
      val payAmt = new BigDecimal("100.00")
      val discountAmt = BigDecimal.ZERO
      val error = runQuietly(
        line.setInvoice(
          invoice.getC_Invoice_ID,
          schedule.getC_InvoicePaySchedule_ID,
          openAmt,
          openAmt,
          payAmt,
          discountAmt
        )
      )
      Then("no non-fatal exception is raised")
      assert(error.isEmpty,
        "the create-from-invoice path must not raise on a real invoice")
      And("the line keeps the invoice payment rule")
      assert(line.getPaymentRule == X_C_Invoice.PAYMENTRULE_DirectDebit,
        "the line payment rule must be the invoice payment rule")
    }
  }
}
