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
import java.util.logging.Level

import org.adempiere.Adempiere
import org.adempiere.core.domains.models.{I_C_PaySelectionLine, X_C_BP_BankAccount, X_C_DocType, X_C_Invoice, X_C_Order, X_C_PaySelectionLine}
import org.compiere.model.{CalloutPaySelection, GridField, GridTab, GridTabVO, GridWindow, GridWindowVO, MInvoice, MInvoicePaySchedule, MBPartner, MBPBankAccount, MBPartnerLocation, MPaySelection, MPaySelectionLine, MPaymentTerm, MPriceList, MCurrency, MOrder, MTab, Query}
import org.compiere.util.{CLogMgt, DB, Env, Ini, Login, Trx}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Regression tests for the five review findings reported on
  * adempiere/adempiere#4490, against the pay selection callout
  * (CalloutPaySelection) and the pay selection line model
  * (MPaySelectionLine.setInvoice / setOrder).
  *
  * F1  CalloutPaySelection.invoice writes the invoice payment rule straight
  *     into the grid. A null rule lands as null: GridTab saves the line with
  *     set_ValueNoCheck, so MPaySelectionLine.setPaymentRule never runs and
  *     its direct deposit fallback never applies. The callout must resolve
  *     the null itself.
  * F2  CalloutPaySelection.invoice and MPaySelectionLine.setInvoice set the
  *     partner bank account only when the partner owns one. When the newly
  *     selected document belongs to a partner without an active account, the
  *     account of the previously selected partner stays on the line, and
  *     MPaySelectionCheck copies that foreign account to the payment.
  * F3  CalloutPaySelection.order and MPaySelectionLine.setOrder change the
  *     business partner without clearing the bank account, with the same
  *     consequence.
  * F4  MPaySelectionLine.setInvoice and setOrder read the partner accounts
  *     through the two argument MBPBankAccount.getByPartner overload, which
  *     passes a null transaction name, so an account saved earlier in the
  *     same transaction is invisible to them.
  * F5  The payment rule copy from the document to the line is pinned here
  *     with a null pre-copy state on the line and a different rule on the
  *     document, so removing the copy fails the suite. The scenario that
  *     shipped with the PR could not fail: the line started with the check
  *     rule and the document carried the same rule, so the assertion held
  *     with the copy removed.
  *
  * Every scenario runs inside one rolled back transaction. Committed demo
  * rows are only read, never changed: the fixtures that need a partner
  * without an account, or a new account, use rows created inside that same
  * transaction or partners that already own no account.
  */
class ValidatePaySelectionLineDocumentDefaults
    extends AnyFeatureSpec
    with AdempiereTestCase
    with GivenWhenThen {

  /** Payment Selection window (206) and its Payment Selection Line tab (353). */
  private val WindowNo = 981
  private val PaySelectionWindow_ID = 206
  private val PaySelectionLine_Tab_ID = 353

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
  }

  private def runQuietly(body: => Unit): Option[Throwable] =
    scala.util.control.Exception.nonFatalCatch.either(body).left.toOption

  /** True when the grid value carries no account, either null or zero. */
  private def noAccount(value: Any): Boolean = value match {
    case null => true
    case i: java.lang.Integer => i.intValue == 0
    case _ => false
  }

  /** The account id held by a grid value, or zero when it holds none. */
  private def accountIdOf(value: Any): Int = value match {
    case i: java.lang.Integer => i.intValue
    case _ => 0
  }

  /**
    * Partners owning a committed bank account, captured once in beforeAll.
    *
    * AdempiereTestCase wraps the whole suite in a single transaction
    * (BeforeAndAfterAll, not BeforeAndAfterEach), so accounts created by one
    * scenario stay visible to the next. A helper resolving "a partner owning an
    * account" inside that transaction therefore starts returning partners whose
    * account only exists in the transaction, which a callout reading with a null
    * transaction name cannot see. That makes a scenario pass or fail depending on
    * the order scenarios ran in. The committed ids are snapshotted before any
    * scenario writes, so every fixture is anchored to data the callout can read.
    */
  private var committedAccountPartnerIds: List[Int] = Nil

  /** Keeps fixture account names unique inside the suite. */
  private var accountSeq = 0

  override def beforeAll(): Unit = {
    super.beforeAll()
    val ids = new java.util.ArrayList[Integer]()
    val pstmt = DB.prepareStatement(
      "SELECT DISTINCT C_BPartner_ID FROM C_BP_BankAccount WHERE IsActive='Y'" +
        " AND AD_Client_ID=? ORDER BY C_BPartner_ID", null)
    var rs: java.sql.ResultSet = null
    try {
      pstmt.setInt(1, Env.getAD_Client_ID(Context))
      rs = pstmt.executeQuery()
      while (rs.next())
        ids.add(rs.getInt(1))
    } catch {
      case e: java.sql.SQLException =>
        throw new AssertionError("the committed partner accounts must be readable", e)
    } finally
      DB.close(rs, pstmt)
    committedAccountPartnerIds = ids.toArray.toList.map(_.asInstanceOf[Integer].intValue)
  }

  /**
    * Ids of every partner owning an active account right now: committed rows and
    * rows created by earlier scenarios of this suite alike, since the whole suite
    * shares one transaction.
    */
  private def partnersOwningAnyAccount: List[Int] = {
    val ids = new java.util.ArrayList[Integer]()
    val pstmt = DB.prepareStatement(
      "SELECT DISTINCT C_BPartner_ID FROM C_BP_BankAccount WHERE AD_Client_ID=?", trxName)
    var rs: java.sql.ResultSet = null
    try {
      pstmt.setInt(1, Env.getAD_Client_ID(Context))
      rs = pstmt.executeQuery()
      while (rs.next())
        ids.add(rs.getInt(1))
    } catch {
      case e: java.sql.SQLException =>
        throw new AssertionError("the partner accounts must be readable", e)
    } finally
      DB.close(rs, pstmt)
    ids.toArray.toList.map(_.asInstanceOf[Integer].intValue)
  }

  /**
    * A partner owning no account at all, committed or in transaction.
    *
    * The suite shares one transaction, so an account saved by an earlier scenario is
    * still visible to the next, and MBPBankAccount.getByPartner sets no ORDER BY, so
    * once a partner owns two accounts the one resolved by banks.get(0) is arbitrary.
    * Every scenario that asserts a specific account is resolved, kept, or cleared
    * takes a partner that is still clean when it runs, so the outcome never depends
    * on the order scenarios happened to execute in.
    */
  private def partnerWithNoAccountYet: MBPartner = {
    val owned = partnersOwningAnyAccount
    if (owned.isEmpty)
      promoteVendor(partnerMatching("1=1"))
    else
      promoteVendor(partnerMatching(
        "C_BPartner_ID NOT IN (" + owned.mkString(",") + ")"))
  }

  /** First committed active partner matching extraWhere, ordered by id. */
  private def partnerMatching(extraWhere: String): MBPartner =
    new Query(Context, "C_BPartner",
      "IsActive='Y' AND AD_Client_ID=? AND " + extraWhere, trxName)
      .setParameters(Env.getAD_Client_ID(Context))
      .setOrderBy("C_BPartner_ID")
      .first()

  /**
    * Active partner owning at least one committed bank account, promoted to vendor
    * inside the test transaction. This client keeps exactly one bank account and
    * its owner is not a vendor, while a payment selection pays vendors, so the
    * fixture promotes it in the rolled back transaction instead of depending on
    * demo data that does not exist. The account must be a committed one: the
    * callout reads the partner accounts with a null transaction name.
    */
  private def vendorWithAccount: MBPartner = {
    assert(committedAccountPartnerIds.nonEmpty,
      "this client must own at least one committed partner bank account")
    promoteVendor(partnerMatching(
      "C_BPartner_ID IN (" + committedAccountPartnerIds.mkString(",") + ")"))
  }

  /** Active partner owning no committed bank account, promoted to vendor in trx. */
  private def vendorWithoutAccount: MBPartner = {
    assert(committedAccountPartnerIds.nonEmpty,
      "the committed account snapshot must be taken before filtering partners out")
    promoteVendor(partnerMatching(
      "C_BPartner_ID NOT IN (" + committedAccountPartnerIds.mkString(",") + ")"))
  }

  /** Mark the partner as vendor inside the test transaction and reload it. */
  private def promoteVendor(partner: MBPartner): MBPartner = {
    if (partner != null && !partner.isVendor) {
      partner.setIsVendor(true)
      partner.saveEx(trxName)
      new MBPartner(Context, partner.getC_BPartner_ID, trxName)
    } else
      partner
  }

  /**
    * A pay selection line exposing the value level write GridTable.dataSavePO
    * uses to copy a callout value back into the persistent object. That path
    * skips the model setter, so it is how a null reaches this mandatory column
    * without MPaySelectionLine.setPaymentRule ever being consulted.
    */
  private def newGridSavedLine(): GridSavedLine = new GridSavedLine(Context, trxName)

  /**
    * A pay selection line whose payment rule starts empty.
    *
    * A new line carries the dictionary default S (Check), and Check pays through no bank
    * account, so on a fresh line the copy from the document never applies and any account
    * assertion would be decided by that default instead of by the document under test. The
    * empty state is written the way the grid saves it back, through set_ValueNoCheck, which
    * is also how a null reaches this mandatory column in production.
    */
  private def lineWithNoRule(): MPaySelectionLine = {
    val line = newGridSavedLine()
    line.writeLikeGrid(I_C_PaySelectionLine.COLUMNNAME_PaymentRule, null)
    assert(line.getPaymentRule == null,
      "the line payment rule must start empty so the document rule is the one copied")
    line
  }

  /**
    * The payment rule a committed document carries, read from the database. A grid row
    * starts with the dictionary default, so a callout scenario has to put the document's
    * own rule on the row for the tender under test to be the document's.
    */
  private def committedDocRule(table: String, id: Int): String =
    DB.getSQLValueString(trxName,
      "SELECT PaymentRule FROM " + table + " WHERE " + table + "_ID=?", id)

  /** First active account of the partner, committed data only. */
  private def accountsOf(partner: MBPartner): java.util.List[MBPBankAccount] =
    MBPBankAccount.getByPartner(Context, partner.getC_BPartner_ID)

  /**
    * Active accounts of the partner as the fixed code reads them, in the current
    * transaction. MBPBankAccount.getByPartner carries no ORDER BY, so the caller
    * must not assume which account comes first: these guards assert the account is
    * present and belongs to the partner, never a particular id across queries.
    */
  private def accountsOfInTrx(partner: MBPartner): java.util.List[MBPBankAccount] =
    MBPBankAccount.getByPartner(Context, partner.getC_BPartner_ID, null, trxName)

  /** True when accountId is one of the given accounts. */
  private def belongsToPartner(accountId: Int,
      accounts: java.util.List[MBPBankAccount]): Boolean = {
    var found = false
    val cursor = accounts.iterator
    while (cursor.hasNext && !found)
      found = cursor.next.getC_BP_BankAccount_ID == accountId
    found
  }

  /**
    * Bank account for the partner inside the test transaction.
    *
    * IsACH and BPBankAcctUse decide what the account may be paid through: it is eligible
    * for a direct deposit only when it is an ACH account whose purpose is Direct Deposit or
    * Both, and the same for a direct debit. MBPBankAccount's constructor leaves a new
    * account non ACH, so an account created without saying so is eligible for nothing, and
    * any scenario expecting one to be selected has to say so here.
    */
  private def newAccountInTrx(partner: MBPartner, tag: String = "acct",
      isACH: Boolean = false,
      use: String = X_C_BP_BankAccount.BPBANKACCTUSE_Both,
      payroll: Boolean = false): MBPBankAccount = {
    accountSeq += 1
    val name = "PR4490-" + tag + "-" + accountSeq
    val account = new MBPBankAccount(Context, 0, trxName)
    account.setC_BPartner_ID(partner.getC_BPartner_ID)
    account.setA_Name(name)
    account.setAccountNo(name)
    account.setBankAccountType(X_C_BP_BankAccount.BANKACCOUNTTYPE_Checking)
    account.setIsACH(isACH)
    account.setBPBankAcctUse(use)
    account.setIsPayrollAccount(payroll)
    account.setIsActive(true)
    account.saveEx(trxName)
    account
  }

  /**
    * True when the account may be paid automatically under the payment rule, using the
    * product's own predicate rather than a restatement of it.
    */
  private def eligibleFor(account: MBPBankAccount, paymentRule: String): Boolean =
    paymentRule match {
      case X_C_Order.PAYMENTRULE_DirectDeposit => account.isDirectDeposit
      case X_C_Order.PAYMENTRULE_DirectDebit => account.isDirectDebit
      case _ => false
    }

  /** Accounts of the partner that the payment rule may pay automatically. */
  private def eligibleAccounts(partner: MBPartner, paymentRule: String)
      : java.util.List[MBPBankAccount] = {
    val out = new java.util.ArrayList[MBPBankAccount]()
    val cursor = accountsOfInTrx(partner).iterator
    while (cursor.hasNext) {
      val account = cursor.next
      if (eligibleFor(account, paymentRule))
        out.add(account)
    }
    out
  }

  /** Currency of the fixture: 102 when it exists, else the client accounting currency. */
  private def apCurrencyId: Int = {
    val currency: MCurrency = new Query(Context, "C_Currency",
      "C_Currency_ID=? AND IsActive='Y'", trxName)
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

  /** Accounts payable invoice document type of the client or template. */
  private def apInvoiceDocType: X_C_DocType = {
    val whereClause = "DocBaseType=? AND IsSOTrx='N' AND IsActive='Y' AND AD_Client_ID IN (0,?)"
    new Query(Context, "C_DocType", whereClause, trxName)
      .setParameters(X_C_DocType.DOCBASETYPE_APInvoice, Env.getAD_Client_ID(Context))
      .setOrderBy("AD_Client_ID DESC, C_DocType_ID")
      .first()
  }

  /** First active bill to location of the partner. */
  private def partnerLocationId(partner: MBPartner): Int = {
    val location: MBPartnerLocation = new Query(Context, "C_BPartner_Location",
      "C_BPartner_ID=? AND IsActive='Y'", trxName)
      .setParameters(partner.getC_BPartner_ID)
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

  /**
    * Accounts payable invoice header for the given vendor, saved inside the
    * test transaction. The dictionary marks partner location, payment term and
    * price list as mandatory, so the fixture resolves them from the logged in
    * client instead of leaving them at zero, which would abort the save.
    */
  private def newApInvoice(vendor: MBPartner, grandTotal: BigDecimal,
      paymentRule: String): MInvoice = {
    val invoice = new MInvoice(Context, 0, trxName)
    invoice.setIsSOTrx(false)
    val docType = apInvoiceDocType
    invoice.setC_DocType_ID(docType.getC_DocType_ID)
    invoice.setC_DocTypeTarget_ID(docType.getC_DocType_ID)
    invoice.setBPartner(vendor)
    invoice.setC_Currency_ID(apCurrencyId)
    invoice.setDateInvoiced(new Timestamp(System.currentTimeMillis()))
    invoice.setDescription("PR4490 fixture")
    invoice.setGrandTotal(grandTotal)
    if (invoice.getC_BPartner_Location_ID == 0)
      invoice.setC_BPartner_Location_ID(partnerLocationId(vendor))
    if (invoice.getC_PaymentTerm_ID == 0)
      invoice.setC_PaymentTerm_ID(apPaymentTermId)
    if (invoice.getM_PriceList_ID == 0)
      invoice.setM_PriceList_ID(apPriceListId)
    if (paymentRule != null)
      invoice.setPaymentRule(paymentRule)
    invoice.saveEx(trxName)
    invoice.load(trxName)
    invoice
  }

  /** Purchase order document type of the logged in client. */
  private def purchaseOrderDocTypeId: Int =
    DB.getSQLValue(trxName,
      "SELECT C_DocType_ID FROM C_DocType WHERE DocBaseType=? AND IsActive='Y'" +
        " AND AD_Client_ID IN (0,?) ORDER BY AD_Client_ID DESC, C_DocType_ID",
      X_C_DocType.DOCBASETYPE_PurchaseOrder, Env.getAD_Client_ID(Context))

  /**
    * Purchase order header for the given vendor, saved inside the test
    * transaction. This client keeps no committed order of a vendor without a
    * bank account, so the fixture owns its own order instead of depending on
    * demo data. Warehouse, sales representative and vendor price list are
    * mandatory columns resolved from the logged in client.
    */
  private def newApOrder(vendor: MBPartner, grandTotal: BigDecimal,
      paymentRule: String): MOrder = {
    val warehouseId = DB.getSQLValue(trxName,
      "SELECT M_Warehouse_ID FROM M_Warehouse WHERE IsActive='Y' AND AD_Client_ID IN (0,?)" +
        " ORDER BY AD_Client_ID DESC, M_Warehouse_ID",
      Env.getAD_Client_ID(Context))
    val salesRepId = DB.getSQLValue(trxName,
      "SELECT AD_User_ID FROM AD_User WHERE IsActive='Y' AND AD_Client_ID IN (0,?)" +
        " ORDER BY AD_Client_ID DESC, AD_User_ID",
      Env.getAD_Client_ID(Context))
    val now = new Timestamp(System.currentTimeMillis())
    val order = new MOrder(Context, 0, trxName)
    order.setIsSOTrx(false)
    order.setC_DocType_ID(purchaseOrderDocTypeId)
    order.setC_DocTypeTarget_ID(purchaseOrderDocTypeId)
    order.setC_BPartner_ID(vendor.getC_BPartner_ID)
    order.setC_BPartner_Location_ID(partnerLocationId(vendor))
    order.setC_Currency_ID(apCurrencyId)
    order.setM_PriceList_ID(apPriceListId)
    order.setC_PaymentTerm_ID(apPaymentTermId)
    order.setM_Warehouse_ID(warehouseId)
    order.setSalesRep_ID(salesRepId)
    order.setDateOrdered(now)
    order.setDatePromised(now)
    order.setDateAcct(now)
    order.setGrandTotal(grandTotal)
    order.setTotalLines(grandTotal)
    if (paymentRule != null)
      order.setPaymentRule(paymentRule)
    order.saveEx(trxName)
    order.load(trxName)
    order
  }

  /** Payment schedule of the fixture invoice, due 30 days after the invoice date. */
  private def newApPaySchedule(invoice: MInvoice): MInvoicePaySchedule = {
    val calendar = java.util.Calendar.getInstance()
    calendar.setTime(invoice.getDateInvoiced)
    calendar.add(java.util.Calendar.DAY_OF_MONTH, 30)
    val schedule = new MInvoicePaySchedule(Context, 0, trxName)
    schedule.setC_Invoice_ID(invoice.getC_Invoice_ID)
    schedule.setDueDate(new Timestamp(calendar.getTimeInMillis))
    schedule.setDiscountDate(invoice.getDateInvoiced)
    schedule.setDueAmt(invoice.getGrandTotal)
    schedule.setDiscountAmt(BigDecimal.ZERO)
    schedule.saveEx(trxName)
    schedule.load(trxName)
    schedule
  }

  /**
    * A live Payment Selection Line grid tab of window 206, opened with one new
    * row ready to be filled, so CalloutPaySelection can write into it exactly
    * as the Payment Selection window does. The header is saved inside the test
    * transaction; the tab is never saved, so no line row is written.
    */
  private def newPaySelectionLineTab(): GridTab = {
    val paySelection = new MPaySelection(Context, 0, trxName)
    paySelection.setName("PR4490-" + System.currentTimeMillis())
    paySelection.setDescription("PR4490 review findings")
    paySelection.setC_Currency_ID(apCurrencyId)
    paySelection.setPayDate(new Timestamp(System.currentTimeMillis()))
    paySelection.saveEx(trxName)

    Env.setContext(Context, WindowNo, "C_PaySelection_ID", paySelection.getC_PaySelection_ID)
    Env.setContext(Context, WindowNo, "C_Currency_ID", apCurrencyId)
    Env.setContext(Context, WindowNo, "PayDate", new Timestamp(System.currentTimeMillis()))
    Env.setContext(Context, WindowNo, "DateTrx", new Timestamp(System.currentTimeMillis()))
    Env.setContext(Context, WindowNo, "IsSOTrx", "N")

    val windowVO = GridWindowVO.create(Context, WindowNo, PaySelectionWindow_ID)
    assert(windowVO != null, "the Payment Selection window value object must load")
    val tabVO = GridTabVO.create(windowVO, 1, MTab.get(Context, PaySelectionLine_Tab_ID), false, false)
    assert(tabVO != null, "the Payment Selection Line tab value object must load")
    val tab = new GridTab(tabVO, new GridWindow(windowVO))
    assert(tab.initTab(false), "the Payment Selection Line tab must initialize")
    assert(tab.dataNew(false), "the Payment Selection Line tab must add a new row")
    assert(tab.getTableModel.open(100), "the Payment Selection Line table must open")
    tab.setCurrentRow(0)
    assert(tab.getCurrentRow == 0, "the new pay selection line row must be current")
    tab
  }

  Feature("Pay selection line takes the payment rule and bank account of the selected document") {
    info("F1: CalloutPaySelection.invoice must never write a null payment rule into the grid")

    Scenario("F1 the invoice payment rule reaches the grid as direct deposit when it resolves to null") {
      Given("a live pay selection line grid row")
      val tab = newPaySelectionLineTab()
      And("an accounts payable invoice whose payment rule is not readable outside its transaction")
      val vendor = vendorWithAccount
      assert(vendor != null, "the logged in client must expose a vendor owning a bank account")
      val invoice = newApInvoice(vendor, new BigDecimal("100.00"),
        X_C_Invoice.PAYMENTRULE_DirectDebit)
      // The callout reloads the invoice with a null transaction name, so a
      // header created in this transaction loads as an empty record and its
      // payment rule reads back as null. That is the null the grid receives.
      val asReadByCallout = new MInvoice(Context, invoice.getC_Invoice_ID, null)
      assert(asReadByCallout.getPaymentRule == null,
        "the invoice reloaded outside the test transaction must read a null payment rule")
      When("the invoice callout runs on that row")
      val error = runQuietly(
        new CalloutPaySelection().invoice(Context, WindowNo, tab,
          tab.getField(I_C_PaySelectionLine.COLUMNNAME_C_Invoice_ID),
          Integer.valueOf(invoice.getC_Invoice_ID)))
      Then("no exception is raised")
      assert(error.isEmpty, "the invoice callout must not raise")
      And("the grid payment rule is never null")
      assert(tab.getValue(I_C_PaySelectionLine.COLUMNNAME_PaymentRule) != null,
        "the callout must not leave the grid payment rule null")
      And("the grid payment rule is the standard direct deposit fallback")
      assert(tab.getValue(I_C_PaySelectionLine.COLUMNNAME_PaymentRule) ==
        X_C_PaySelectionLine.PAYMENTRULE_DirectDeposit,
        "a null invoice rule must resolve to direct deposit in the grid")
    }

    Scenario("F1 the grid save path writes a null payment rule, so the callout must resolve it") {
      Given("a new pay selection line")
      val line = newGridSavedLine()
      When("a null payment rule is written the way GridTable.dataSavePO saves it back")
      val rejected = runQuietly(line.writeLikeGrid(
        I_C_PaySelectionLine.COLUMNNAME_PaymentRule, null))
      Then("the write is accepted, because set_ValueNoCheck skips the mandatory check")
      assert(rejected.isEmpty,
        "the grid value level write must accept null on this mandatory column")
      And("the column stays null because the model fallback is bypassed")
      assert(line.getPaymentRule == null,
        "the grid write must bypass MPaySelectionLine.setPaymentRule and leave the rule null")
      When("the same null is written through the model setter")
      line.setPaymentRule(null)
      Then("the model substitutes the standard direct deposit rule")
      assert(line.getPaymentRule == X_C_PaySelectionLine.PAYMENTRULE_DirectDeposit,
        "MPaySelectionLine.setPaymentRule must fall back to direct deposit")
    }

    info("F2: the bank account of a partner without an account must be cleared")

    Scenario("F2 the invoice callout clears the bank account of a partner without one") {
      Given("a live pay selection line row already holding another partner's bank account")
      val tab = newPaySelectionLineTab()
      val withAccount = vendorWithAccount
      assert(withAccount != null, "the logged in client must expose a vendor owning a bank account")
      val accounts = accountsOf(withAccount)
      assert(!accounts.isEmpty, "that vendor must own an active bank account")
      tab.setValue(I_C_PaySelectionLine.COLUMNNAME_C_BP_BankAccount_ID,
        Integer.valueOf(accounts.get(0).getC_BP_BankAccount_ID))
      And("a committed invoice of a partner that owns no active bank account")
      val invoiceId = DB.getSQLValue(trxName,
        "SELECT i.C_Invoice_ID FROM C_Invoice i WHERE i.IsActive='Y' AND i.IsSOTrx='N'" +
          " AND i.DocStatus='CO' AND i.AD_Client_ID=?" +
          " AND i.C_BPartner_ID NOT IN (SELECT C_BPartner_ID FROM C_BP_BankAccount WHERE IsActive='Y')" +
          " ORDER BY i.C_Invoice_ID",
        Env.getAD_Client_ID(Context))
      assume(invoiceId > 0, "no committed invoice of a partner without a bank account")
      //	The row starts with the dictionary default S (Check), which pays through no
      //	account, so the invoice's own rule is put on the row: the tender under test is
      //	the invoice's, and the only reason left to clear the account is the partner.
      val docRule = committedDocRule("C_Invoice", invoiceId)
      assume(docRule != null, "the committed invoice must carry a payment rule")
      tab.setValue(I_C_PaySelectionLine.COLUMNNAME_PaymentRule, docRule)
      When("the invoice callout runs on that row")
      val error = runQuietly(
        new CalloutPaySelection().invoice(Context, WindowNo, tab,
          tab.getField(I_C_PaySelectionLine.COLUMNNAME_C_Invoice_ID),
          Integer.valueOf(invoiceId)))
      Then("no exception is raised")
      assert(error.isEmpty, "the invoice callout must not raise")
      And("the grid carries the invoice partner")
      assert(tab.getValue(I_C_PaySelectionLine.COLUMNNAME_C_BPartner_ID) ==
        Integer.valueOf(DB.getSQLValue(trxName,
          "SELECT C_BPartner_ID FROM C_Invoice WHERE C_Invoice_ID=?", invoiceId)),
        "the callout must set the invoice partner on the row")
      And("the stale bank account of the previous partner is cleared")
      assert(noAccount(tab.getValue(I_C_PaySelectionLine.COLUMNNAME_C_BP_BankAccount_ID)),
        "the callout must clear the bank account when the new partner owns none")
    }

    Scenario("F2 setInvoice clears a bank account left by a previous document") {
      Given("a pay selection line already holding a bank account of another partner")
      val withAccount = vendorWithAccount
      assert(withAccount != null, "the logged in client must expose a vendor owning a bank account")
      val accounts = accountsOf(withAccount)
      assert(!accounts.isEmpty, "that vendor must own an active bank account")
      val line = lineWithNoRule()
      line.setC_BP_BankAccount_ID(accounts.get(0).getC_BP_BankAccount_ID)
      And("an invoice of a partner owning no account at all")
      val withoutAccount = partnerWithNoAccountYet
      assume(withoutAccount != null, "no active partner without a bank account in this client")
      val invoice = newApInvoice(withoutAccount, new BigDecimal("80.00"),
        X_C_Invoice.PAYMENTRULE_DirectDeposit)
      val schedule = newApPaySchedule(invoice)
      When("the line is linked to that invoice")
      val openAmt = invoice.getGrandTotal
      val error = runQuietly(
        line.setInvoice(invoice.getC_Invoice_ID, schedule.getC_InvoicePaySchedule_ID,
          false, openAmt, openAmt, BigDecimal.ZERO))
      Then("no non-fatal exception is raised")
      assert(error.isEmpty, "setInvoice must not raise")
      And("the line points to the invoice partner")
      assert(line.getC_BPartner_ID == invoice.getC_BPartner_ID,
        "the line partner must be the invoice partner")
      And("the bank account of the previous partner is cleared")
      assert(line.getC_BP_BankAccount_ID == 0,
        "setInvoice must clear the bank account when the invoice partner owns none")
    }

    info("F3: switching the document to another partner must clear the bank account")

    Scenario("F3 the order callout clears the bank account when the order partner changes") {
      Given("a live pay selection line row already holding a partner's bank account")
      val tab = newPaySelectionLineTab()
      val withAccount = vendorWithAccount
      assert(withAccount != null, "the logged in client must expose a vendor owning a bank account")
      val accounts = accountsOf(withAccount)
      assert(!accounts.isEmpty, "that vendor must own an active bank account")
      tab.setValue(I_C_PaySelectionLine.COLUMNNAME_C_BP_BankAccount_ID,
        Integer.valueOf(accounts.get(0).getC_BP_BankAccount_ID))
      And("a committed order of a partner that owns no active bank account")
      val orderId = DB.getSQLValue(trxName,
        "SELECT o.C_Order_ID FROM C_Order o WHERE o.IsActive='Y' AND o.AD_Client_ID=?" +
          " AND o.C_BPartner_ID NOT IN (SELECT C_BPartner_ID FROM C_BP_BankAccount WHERE IsActive='Y')" +
          " ORDER BY o.C_Order_ID",
        Env.getAD_Client_ID(Context))
      assume(orderId > 0, "no committed order of a partner without a bank account")
      //	The row starts with the dictionary default S (Check), so the order's own rule is
      //	put on the row: the tender under test is the order's, not that default.
      val docRule = committedDocRule("C_Order", orderId)
      assume(docRule != null, "the committed order must carry a payment rule")
      tab.setValue(I_C_PaySelectionLine.COLUMNNAME_PaymentRule, docRule)
      When("the order callout runs on that row")
      val error = runQuietly(
        new CalloutPaySelection().order(Context, WindowNo, tab,
          tab.getField(I_C_PaySelectionLine.COLUMNNAME_C_Order_ID),
          Integer.valueOf(orderId)))
      Then("no exception is raised")
      assert(error.isEmpty, "the order callout must not raise")
      And("the stale bank account is cleared")
      assert(noAccount(tab.getValue(I_C_PaySelectionLine.COLUMNNAME_C_BP_BankAccount_ID)),
        "the order callout must clear the bank account left by a previous document")
    }

    Scenario("F3 setOrder clears a bank account left by a previous document") {
      Given("a pay selection line already holding a bank account of another partner")
      val withAccount = vendorWithAccount
      assert(withAccount != null, "the logged in client must expose a vendor owning a bank account")
      val accounts = accountsOf(withAccount)
      assert(!accounts.isEmpty, "that vendor must own an active bank account")
      val line = lineWithNoRule()
      line.setC_BP_BankAccount_ID(accounts.get(0).getC_BP_BankAccount_ID)
      And("a purchase order of a partner owning no account at all")
      val withoutAccount = partnerWithNoAccountYet
      assume(withoutAccount != null, "no active partner without a bank account in this client")
      val order = newApOrder(withoutAccount, new BigDecimal("60.00"),
        X_C_Order.PAYMENTRULE_OnCredit)
      When("the line is linked to that order")
      val openAmt = new BigDecimal("60.00")
      val error = runQuietly(
        line.setOrder(order.getC_Order_ID, openAmt, openAmt, openAmt, BigDecimal.ZERO))
      Then("no non-fatal exception is raised")
      assert(error.isEmpty, "setOrder must not raise")
      And("the line points to the order partner")
      assert(line.getC_BPartner_ID == withoutAccount.getC_BPartner_ID,
        "the line partner must be the order partner")
      And("the bank account of the previous partner is cleared")
      assert(line.getC_BP_BankAccount_ID == 0,
        "setOrder must clear the bank account when the order partner owns none")
    }

    info("F4: the partner account lookup must run in the current transaction")

    Scenario("F4 setInvoice sees a bank account saved in the same transaction") {
      Given("a vendor owning no bank account at all")
      val vendor = partnerWithNoAccountYet
      assume(vendor != null, "no active partner without a bank account in this client")
      assert(accountsOf(vendor).isEmpty,
        "the lookup must start with no committed account for this vendor")
      And("an invoice of that vendor")
      val invoice = newApInvoice(vendor, new BigDecimal("100.00"),
        X_C_Invoice.PAYMENTRULE_DirectDeposit)
      val schedule = newApPaySchedule(invoice)
      And("an ACH account saved for that vendor inside this transaction only")
      val account = newAccountInTrx(vendor, "f4dep", isACH = true,
        use = X_C_BP_BankAccount.BPBANKACCTUSE_DirectDeposit)
      When("the pay selection line is linked to that invoice")
      val line = lineWithNoRule()
      val openAmt = invoice.getGrandTotal
      val error = runQuietly(
        line.setInvoice(invoice.getC_Invoice_ID, schedule.getC_InvoicePaySchedule_ID,
          false, openAmt, openAmt, BigDecimal.ZERO))
      Then("no non-fatal exception is raised")
      assert(error.isEmpty, "setInvoice must not raise")
      And("the account created in this transaction reaches the line")
      assert(line.getC_BP_BankAccount_ID == account.getC_BP_BankAccount_ID,
        "setInvoice must read the partner accounts in the current transaction")
    }

    Scenario("F4 setOrder sees a bank account saved in the same transaction") {
      Given("a vendor owning no bank account at all, and an order of its own")
      val vendor = partnerWithNoAccountYet
      assume(vendor != null, "no active partner without a bank account in this client")
      assert(accountsOf(vendor).isEmpty,
        "the lookup must start with no committed account for this vendor")
      val order = newApOrder(vendor, new BigDecimal("60.00"),
        X_C_Order.PAYMENTRULE_DirectDebit)
      And("an ACH account saved for that vendor inside this transaction only")
      val account = newAccountInTrx(vendor, "f4debit", isACH = true,
        use = X_C_BP_BankAccount.BPBANKACCTUSE_DirectDebit)
      When("the pay selection line is linked to that order")
      val line = lineWithNoRule()
      val openAmt = new BigDecimal("60.00")
      val error = runQuietly(
        line.setOrder(order.getC_Order_ID, openAmt, openAmt, openAmt, BigDecimal.ZERO))
      Then("no non-fatal exception is raised")
      assert(error.isEmpty, "setOrder must not raise")
      And("the account created in this transaction reaches the line")
      assert(line.getC_BP_BankAccount_ID == account.getC_BP_BankAccount_ID,
        "setOrder must read the partner accounts in the current transaction")
    }

    info("F5: the payment rule copy from the document to the line must be observable")

    Scenario("F5 setInvoice copies the invoice payment rule from a null starting state") {
      Given("an accounts payable invoice with a direct debit payment rule")
      val vendor = vendorWithAccount
      assert(vendor != null, "the logged in client must expose a vendor owning a bank account")
      val invoice = newApInvoice(vendor, new BigDecimal("100.00"),
        X_C_Invoice.PAYMENTRULE_DirectDebit)
      val schedule = newApPaySchedule(invoice)
      And("a pay selection line whose payment rule starts empty")
      val line = newGridSavedLine()
      // A new line carries the dictionary default, so the empty starting state
      // is written the way the grid saves it back, through set_ValueNoCheck.
      line.writeLikeGrid(I_C_PaySelectionLine.COLUMNNAME_PaymentRule, null)
      assert(line.getPaymentRule == null,
        "the line payment rule must start empty so the copy is observable")
      When("the line is linked to that invoice")
      val openAmt = invoice.getGrandTotal
      val error = runQuietly(
        line.setInvoice(invoice.getC_Invoice_ID, schedule.getC_InvoicePaySchedule_ID,
          false, openAmt, openAmt, BigDecimal.ZERO))
      Then("no non-fatal exception is raised")
      assert(error.isEmpty, "setInvoice must not raise")
      And("the invoice payment rule is copied to the line")
      assert(line.getPaymentRule == X_C_Invoice.PAYMENTRULE_DirectDebit,
        "removing the payment rule copy must fail this scenario")
    }

    Scenario("F5 setOrder copies the order payment rule from a null starting state") {
      Given("a purchase order carrying the on credit payment rule")
      val vendor = vendorWithAccount
      assert(vendor != null, "the logged in client must expose a partner owning a bank account")
      val order = newApOrder(vendor, new BigDecimal("60.00"),
        X_C_Order.PAYMENTRULE_OnCredit)
      assert(order.getPaymentRule == X_C_Order.PAYMENTRULE_OnCredit,
        "the order must carry a rule different from the line default")
      And("a pay selection line whose payment rule starts empty")
      val line = newGridSavedLine()
      line.writeLikeGrid(I_C_PaySelectionLine.COLUMNNAME_PaymentRule, null)
      assert(line.getPaymentRule == null,
        "the line payment rule must start empty so the copy is observable")
      When("the line is linked to that order")
      val openAmt = new BigDecimal("60.00")
      val error = runQuietly(
        line.setOrder(order.getC_Order_ID, openAmt, openAmt, openAmt, BigDecimal.ZERO))
      Then("no non-fatal exception is raised")
      assert(error.isEmpty, "setOrder must not raise")
      And("the order payment rule is copied to the line")
      assert(line.getPaymentRule == order.getPaymentRule,
        "removing the payment rule copy must fail this scenario")
    }

    info("N1: clearing must only happen when the partner owns no account")

    Scenario("N1 the invoice callout keeps an account when the partner owns one") {
      Given("a live pay selection line row")
      val tab = newPaySelectionLineTab()
      And("a partner owning an active bank account that is not an ACH account")
      val vendor = vendorWithAccount
      assert(vendor != null, "the logged in client must expose a partner owning a bank account")
      val accounts = accountsOf(vendor)
      assert(!accounts.isEmpty, "that partner must own a committed active account")
      And("a committed invoice of that partner, paid by a tender that is not direct")
      //	The callout reads C_Invoice_v with a null transaction name, so it can only see
      //	committed data: both the invoice and its rule come from the database, and the rule
      //	is not chosen here but read from the invoice the guard runs on.
      val invoiceId = DB.getSQLValue(trxName,
        "SELECT i.C_Invoice_ID FROM C_Invoice_v i WHERE i.C_BPartner_ID=?" +
          " AND i.DocStatus='CO' ORDER BY i.C_Invoice_ID",
        vendor.getC_BPartner_ID)
      assume(invoiceId > 0, "no committed invoice of a partner owning a bank account")
      val docRule = committedDocRule("C_Invoice", invoiceId)
      assume(docRule != null, "the committed invoice must carry a payment rule")
      assume(!eligibleFor(accounts.iterator.next, docRule),
        "this guard needs a tender that pays through no account, rule " + docRule)
      //	The row starts with the dictionary default, so the invoice's rule is put on it:
      //	the tender under test is the invoice's.
      tab.setValue(I_C_PaySelectionLine.COLUMNNAME_PaymentRule, docRule)
      When("the invoice callout runs on that row")
      val error = runQuietly(
        new CalloutPaySelection().invoice(Context, WindowNo, tab,
          tab.getField(I_C_PaySelectionLine.COLUMNNAME_C_Invoice_ID),
          Integer.valueOf(invoiceId)))
      Then("no exception is raised")
      assert(error.isEmpty, "the invoice callout must not raise")
      And("the row carries the invoice partner")
      assert(tab.getValue(I_C_PaySelectionLine.COLUMNNAME_C_BPartner_ID) ==
        Integer.valueOf(vendor.getC_BPartner_ID),
        "the callout must set the invoice partner on the row")
      And("no account is assigned, because that tender is not paid through one")
      val value = tab.getValue(I_C_PaySelectionLine.COLUMNNAME_C_BP_BankAccount_ID)
      assert(noAccount(value),
        "payment rule " + docRule + " must not auto assign a partner account")
      And("the payment rule is never null on this path")
      assert(tab.getValue(I_C_PaySelectionLine.COLUMNNAME_PaymentRule) != null,
        "a committed invoice exposes its rule, so the grid rule must not be null")
    }

    Scenario("N1 setInvoice keeps an account when the partner owns an eligible one") {
      Given("a pay selection line whose payment rule starts empty")
      val line = lineWithNoRule()
      And("a partner owning a committed account that is not an ACH account")
      val vendor = vendorWithAccount
      assert(vendor != null, "the logged in client must expose a partner owning a bank account")
      assert(!eligibleFor(accountsOf(vendor).iterator.next, X_C_Order.PAYMENTRULE_DirectDeposit),
        "this guard needs a committed account no direct rule may pay")
      And("an ACH account saved for that partner in this transaction")
      val eligible = newAccountInTrx(vendor, "n1dep", isACH = true,
        use = X_C_BP_BankAccount.BPBANKACCTUSE_DirectDeposit)
      val accounts = accountsOfInTrx(vendor)
      assert(accounts.size >= 2,
        "the partner must own at least two active accounts in this transaction")
      And("an invoice of that partner paid by direct deposit")
      val invoice = newApInvoice(vendor, new BigDecimal("100.00"),
        X_C_Invoice.PAYMENTRULE_DirectDeposit)
      val schedule = newApPaySchedule(invoice)
      When("the line is linked to that invoice")
      val openAmt = invoice.getGrandTotal
      val error = runQuietly(
        line.setInvoice(invoice.getC_Invoice_ID, schedule.getC_InvoicePaySchedule_ID,
          false, openAmt, openAmt, BigDecimal.ZERO))
      Then("no non-fatal exception is raised")
      assert(error.isEmpty, "setInvoice must not raise")
      And("the line carries an account, it is not cleared")
      assert(line.getC_BP_BankAccount_ID != 0,
        "setInvoice must resolve an account when the partner owns an eligible one")
      And("that account belongs to the partner")
      assert(belongsToPartner(line.getC_BP_BankAccount_ID, accounts),
        "the resolved account must belong to the invoice partner")
      And("it is the eligible one, not the non ACH account")
      assert(line.getC_BP_BankAccount_ID == eligible.getC_BP_BankAccount_ID,
        "a direct deposit must resolve the ACH account, never the non ACH one")
      assert(eligible.getC_BP_BankAccount_ID > 0,
        "the eligible account must have been created for this guard to mean anything")
    }

    Scenario("N1 setOrder keeps an account when the partner owns an eligible one") {
      Given("a pay selection line whose payment rule starts empty")
      val line = lineWithNoRule()
      And("a partner owning a committed account that is not an ACH account")
      val vendor = vendorWithAccount
      assert(vendor != null, "the logged in client must expose a partner owning a bank account")
      assert(!eligibleFor(accountsOf(vendor).iterator.next, X_C_Order.PAYMENTRULE_DirectDebit),
        "this guard needs a committed account no direct rule may pay")
      And("an ACH account saved for that partner in this transaction")
      val eligible = newAccountInTrx(vendor, "n1debit", isACH = true,
        use = X_C_BP_BankAccount.BPBANKACCTUSE_DirectDebit)
      val accounts = accountsOfInTrx(vendor)
      assert(accounts.size >= 2,
        "the partner must own at least two active accounts in this transaction")
      And("a purchase order of that partner paid by direct debit")
      val order = newApOrder(vendor, new BigDecimal("60.00"),
        X_C_Order.PAYMENTRULE_DirectDebit)
      When("the line is linked to that order")
      val openAmt = new BigDecimal("60.00")
      val error = runQuietly(
        line.setOrder(order.getC_Order_ID, openAmt, openAmt, openAmt, BigDecimal.ZERO))
      Then("no non-fatal exception is raised")
      assert(error.isEmpty, "setOrder must not raise")
      And("the line carries an account, it is not cleared")
      assert(line.getC_BP_BankAccount_ID != 0,
        "setOrder must resolve an account when the partner owns an eligible one")
      And("that account belongs to the order partner")
      assert(belongsToPartner(line.getC_BP_BankAccount_ID, accounts),
        "the resolved account must belong to the order partner")
      And("it is the eligible one, not the non ACH account")
      assert(line.getC_BP_BankAccount_ID == eligible.getC_BP_BankAccount_ID,
        "a direct debit must resolve the ACH account, never the non ACH one")
    }

    info("S9: the default account is selected with the product's own predicate")

    /**
      * The High finding on adempiere/adempiere#4490: the account taken automatically was
      * the first active one, whatever its IsACH flag or its BPBankAcctUse purpose. The
      * product already states the rule, as isDirectDeposit and isDirectDebit, and
      * MPaySelectionCheck already applies it under its own "Add default account" comment.
      * These scenarios pin that the callout and the model apply the same rule, so the
      * account they write is one the payment would accept.
      */
    Scenario("S9 setInvoice selects the ACH account, not the first account") {
      Given("a partner owning no account at all")
      val vendor = partnerWithNoAccountYet
      assume(vendor != null, "no active partner without a bank account in this client")
      And("a non ACH account created first, so it is the first one found")
      val ineligible = newAccountInTrx(vendor, "s9off",
        isACH = false, use = X_C_BP_BankAccount.BPBANKACCTUSE_None)
      assert(!eligibleFor(ineligible, X_C_Order.PAYMENTRULE_DirectDeposit),
        "the first account must not be payable by direct deposit")
      And("an ACH direct deposit account created second")
      val eligible = newAccountInTrx(vendor, "s9on", isACH = true,
        use = X_C_BP_BankAccount.BPBANKACCTUSE_DirectDeposit)
      And("an invoice of that partner paid by direct deposit")
      val invoice = newApInvoice(vendor, new BigDecimal("100.00"),
        X_C_Invoice.PAYMENTRULE_DirectDeposit)
      val schedule = newApPaySchedule(invoice)
      When("the line is linked to that invoice")
      val line = lineWithNoRule()
      val openAmt = invoice.getGrandTotal
      runQuietly(
        line.setInvoice(invoice.getC_Invoice_ID, schedule.getC_InvoicePaySchedule_ID,
          false, openAmt, openAmt, BigDecimal.ZERO))
      Then("the ACH account is the one on the line")
      assert(line.getC_BP_BankAccount_ID == eligible.getC_BP_BankAccount_ID,
        "a direct deposit must select the ACH account, not the first active account")
    }

    Scenario("S9 setOrder selects the ACH account, not the first account") {
      Given("a partner owning no account at all")
      val vendor = partnerWithNoAccountYet
      assume(vendor != null, "no active partner without a bank account in this client")
      And("an account usable for nothing, created first")
      val ineligible = newAccountInTrx(vendor, "s9o2",
        isACH = false, use = X_C_BP_BankAccount.BPBANKACCTUSE_None)
      assert(!eligibleFor(ineligible, X_C_Order.PAYMENTRULE_DirectDebit),
        "the first account must not be payable by direct debit")
      And("an ACH direct debit account created second")
      val eligible = newAccountInTrx(vendor, "s9d2", isACH = true,
        use = X_C_BP_BankAccount.BPBANKACCTUSE_DirectDebit)
      And("a purchase order of that partner paid by direct debit")
      val order = newApOrder(vendor, new BigDecimal("60.00"),
        X_C_Order.PAYMENTRULE_DirectDebit)
      When("the line is linked to that order")
      val line = lineWithNoRule()
      val openAmt = new BigDecimal("60.00")
      runQuietly(line.setOrder(order.getC_Order_ID, openAmt, openAmt, openAmt,
        BigDecimal.ZERO))
      Then("the ACH account is the one on the line")
      assert(line.getC_BP_BankAccount_ID == eligible.getC_BP_BankAccount_ID,
        "a direct debit must select the ACH account, not the first active account")
    }

    Scenario("S9 a payment rule that is not direct assigns no account") {
      Given("a partner owning no account at all, and an ACH account usable for both")
      val vendor = partnerWithNoAccountYet
      assume(vendor != null, "no active partner without a bank account in this client")
      val eligible = newAccountInTrx(vendor, "s9both", isACH = true,
        use = X_C_BP_BankAccount.BPBANKACCTUSE_Both)
      assert(eligibleFor(eligible, X_C_Order.PAYMENTRULE_DirectDeposit),
        "this account is payable, so clearing it proves the rule chose, not the data")
      And("an invoice of that partner paid by check")
      val invoice = newApInvoice(vendor, new BigDecimal("100.00"),
        X_C_Invoice.PAYMENTRULE_Check)
      val schedule = newApPaySchedule(invoice)
      When("the line is linked to that invoice")
      val line = lineWithNoRule()
      val openAmt = invoice.getGrandTotal
      runQuietly(
        line.setInvoice(invoice.getC_Invoice_ID, schedule.getC_InvoicePaySchedule_ID,
          false, openAmt, openAmt, BigDecimal.ZERO))
      Then("no account is assigned, because a check is not paid through one")
      assert(line.getC_BP_BankAccount_ID == 0,
        "only direct deposit and direct debit may auto assign an account, as in MPaySelectionCheck")
      And("the rule copied to the line is the invoice rule")
      assert(line.getPaymentRule == X_C_Order.PAYMENTRULE_Check,
        "the rule must still be copied, only the account is withheld")
    }

    Scenario("S10 the selection is deterministic when several accounts are eligible") {
      Given("a partner owning no account at all")
      val vendor = partnerWithNoAccountYet
      assume(vendor != null, "no active partner without a bank account in this client")
      And("two ACH direct deposit accounts")
      val first = newAccountInTrx(vendor, "s10a", isACH = true,
        use = X_C_BP_BankAccount.BPBANKACCTUSE_DirectDeposit)
      val second = newAccountInTrx(vendor, "s10b", isACH = true,
        use = X_C_BP_BankAccount.BPBANKACCTUSE_DirectDeposit)
      val lowest = math.min(first.getC_BP_BankAccount_ID, second.getC_BP_BankAccount_ID)
      And("an invoice of that partner paid by direct deposit")
      val invoice = newApInvoice(vendor, new BigDecimal("100.00"),
        X_C_Invoice.PAYMENTRULE_DirectDeposit)
      val schedule = newApPaySchedule(invoice)
      When("two separate lines are linked to that same invoice")
      val openAmt = invoice.getGrandTotal
      val a = lineWithNoRule()
      val b = lineWithNoRule()
      runQuietly(a.setInvoice(invoice.getC_Invoice_ID,
        schedule.getC_InvoicePaySchedule_ID, false, openAmt, openAmt, BigDecimal.ZERO))
      runQuietly(b.setInvoice(invoice.getC_Invoice_ID,
        schedule.getC_InvoicePaySchedule_ID, false, openAmt, openAmt, BigDecimal.ZERO))
      Then("both lines resolve the same account")
      assert(a.getC_BP_BankAccount_ID == b.getC_BP_BankAccount_ID,
        "MBPBankAccount.getByPartner sets no ORDER BY, so the selection must be ordered here")
      And("it is the lowest eligible id")
      assert(a.getC_BP_BankAccount_ID == lowest,
        "the tie break is the lowest account id, so the same input always pays the same account")
    }

    Scenario("S11 a payroll account is not used for a line that is not payroll") {
      Given("a partner owning no account at all")
      val vendor = partnerWithNoAccountYet
      assume(vendor != null, "no active partner without a bank account in this client")
      And("a payroll account created first, eligible for direct deposit")
      val payroll = newAccountInTrx(vendor, "s11pay", isACH = true,
        use = X_C_BP_BankAccount.BPBANKACCTUSE_DirectDeposit, payroll = true)
      assert(payroll.isPayrollAccount && payroll.isDirectDeposit,
        "the payroll account must be eligible, so excluding it must be the payroll filter")
      And("a regular ACH direct deposit account created second")
      val regular = newAccountInTrx(vendor, "s11reg", isACH = true,
        use = X_C_BP_BankAccount.BPBANKACCTUSE_DirectDeposit)
      And("an invoice of that partner paid by direct deposit")
      val invoice = newApInvoice(vendor, new BigDecimal("100.00"),
        X_C_Invoice.PAYMENTRULE_DirectDeposit)
      val schedule = newApPaySchedule(invoice)
      When("a line that is not a payroll line is linked to that invoice")
      val line = lineWithNoRule()
      assert(line.getHR_Movement_ID == 0,
        "the line must not be a payroll line for this guard to mean anything")
      val openAmt = invoice.getGrandTotal
      runQuietly(
        line.setInvoice(invoice.getC_Invoice_ID, schedule.getC_InvoicePaySchedule_ID,
          false, openAmt, openAmt, BigDecimal.ZERO))
      Then("the regular account is selected, not the payroll account")
      assert(line.getC_BP_BankAccount_ID == regular.getC_BP_BankAccount_ID,
        "a payroll account must not pay an ordinary supplier, as MPaySelectionCheck already requires")
    }

    Scenario("S12 a payroll line is paid through a payroll account, not an ordinary one") {
      Given("a partner owning an ordinary direct deposit account and, after it, a payroll one")
      val vendor = partnerWithNoAccountYet
      assume(vendor != null, "no active partner without a bank account in this client")
      val regular = newAccountInTrx(vendor, "s12reg", isACH = true,
        use = X_C_BP_BankAccount.BPBANKACCTUSE_DirectDeposit)
      val payroll = newAccountInTrx(vendor, "s12pay", isACH = true,
        use = X_C_BP_BankAccount.BPBANKACCTUSE_DirectDeposit, payroll = true)
      assert(regular.getC_BP_BankAccount_ID < payroll.getC_BP_BankAccount_ID,
        "the ordinary account must hold the lowest id, so a selector that ignores payroll would " +
          "take it by its own tie break and this guard would prove nothing")

      When("the rule is resolved for a payroll line with no account on it yet")
      val resolved = MPaySelectionLine.payableBankAccountId(Context,
        vendor.getC_BPartner_ID, X_C_Order.PAYMENTRULE_DirectDeposit,
        true, 0, trxName)

      Then("the payroll account is selected")
      assert(resolved == payroll.getC_BP_BankAccount_ID,
        "a payroll line must be paid into a payroll account; the pay selection check filter it " +
          "replaced kept an account only when it was a payroll one or the line was not payroll, " +
          "so it never handed a payroll line an ordinary account")

      When("an ordinary account is already on the payroll line")
      val reResolved = MPaySelectionLine.payableBankAccountId(Context,
        vendor.getC_BPartner_ID, X_C_Order.PAYMENTRULE_DirectDeposit,
        true, regular.getC_BP_BankAccount_ID, trxName)

      Then("it is re-resolved to the payroll account rather than trusted")
      assert(reResolved == payroll.getC_BP_BankAccount_ID,
        "a candidate that cannot receive a payroll payment must not be kept on trust")

      And("the same ordinary account is still kept on a line that is not payroll")
      assert(MPaySelectionLine.payableBankAccountId(Context,
        vendor.getC_BPartner_ID, X_C_Order.PAYMENTRULE_DirectDeposit,
        false, regular.getC_BP_BankAccount_ID, trxName) == regular.getC_BP_BankAccount_ID,
        "S11 must keep holding: an ordinary account pays an ordinary line")
    }
  }
}

/**
  * A pay selection line that exposes the value level write GridTable.dataSavePO
  * uses to copy a callout value back into the persistent object. That path calls
  * PO.set_ValueNoCheck, which skips the mandatory check and never consults the
  * model setter, so it is how a null payment rule reaches this mandatory column
  * while MPaySelectionLine.setPaymentRule, with its direct deposit fallback,
  * stays out of the way.
  */
private class GridSavedLine(ctx: java.util.Properties, trxName: String)
    extends MPaySelectionLine(ctx, 0, trxName) {

  /** Write the column exactly as the grid save path does. */
  def writeLikeGrid(columnName: String, value: AnyRef): Boolean =
    set_ValueNoCheck(columnName, value)
}
