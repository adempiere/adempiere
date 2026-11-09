/** ****************************************************************************
  * Product: ADempiere ERP & CRM Smart Business Solution                       *
  * Copyright (C) 2006-2026 ADempiere Foundation, All Rights Reserved.         *
  * This program is free software, you can redistribute it and/or modify it    *
  * under the terms version 2 of the GNU General Public License as published   *
  * by the Free Software Foundation. This program is distributed in the hope   *
  * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
  * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
  * See the GNU General Public License for more details.                       *
  * You should have received a copy of the GNU General Public License along    *
  * with this program, if not, write to the Free Software Foundation, Inc.,    *
  * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
  * For the text or an alternative of this public license, you may reach us    *
  * or via info@adempiere.net or http://www.adempiere.net/license.html         *
  * ****************************************************************************/

package org.adempiere.test

import java.math.BigDecimal
import java.sql.Savepoint

import org.adempiere.core.domains.models.{I_AD_User, I_C_BankAccount, I_C_BPartner, I_C_Payment, X_C_Payment}
import org.compiere.model.{MBankAccount, MBPartner, MOrgInfo, MPInstance, MPayment, MUser, Query}
import org.compiere.process.ProcessInfo
import org.compiere.util.{Env, Msg}
import org.eevolution.test.AdempiereTestCase
import org.scalatest.BeforeAndAfterEach
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.spin.process.{PaymentIdentify, PaymentIdentifyAbstract}

import scala.collection.mutable.ArrayBuffer
import scala.util.Try

/**
  * Regression tests for the Collecting Agent tenant/active guard added to the
  * Identify Payment process (adempiere/adempiere issue #4464, PR #4465).
  *
  * Before `identifiedPayment.saveEx()` the process must resolve the
  * `CollectingAgent_ID` parameter inside the identified payment's tenant
  * considering active users only:
  * {{{
  * AD_User_ID = ? AND AD_Client_ID = ? AND IsActive = 'Y'
  * }}}
  * and must raise <code>@CollectingAgent_ID@ @NotFound@</code> when the user
  * cannot be resolved there, so a caller cannot attach another tenant's user,
  * or a disabled user, through the raw process parameter.
  *
  * The process is always started through its public interface
  * ([[org.compiere.process.ProcessInfo]] + [[org.spin.process.PaymentIdentify#startProcess]])
  * and the assertions only read observable effects: the reported process error
  * and the persisted `C_Payment` columns read back with a fresh
  * [[org.compiere.model.Query]]/[[org.compiere.model.MPayment]]. No internal
  * process state is inspected.
  *
  * The spec follows the repository's Scala convention: a ScalaTest
  * `AnyFeatureSpec` over the shared [[AdempiereTestCase]] trait, which starts
  * ADempiere in `beforeAll`, owns `trxName`/`Transaction`/`Context`/`Today`
  * and rolls the suite transaction back in `afterAll`. Every scenario opens
  * its own savepoint and rolls back to it afterwards, so no fixture of one
  * scenario is visible to the next and nothing reaches the database.
  *
  * @author ADempiere Foundation
  */
class IdentifyPaymentCollectingAgentSpec extends AnyFeatureSpec
  with AdempiereTestCase
  with GivenWhenThen
  with BeforeAndAfterEach {

  /** Identify Payment process (AD_Process_ID 54306). */
  private val processId: Int = PaymentIdentifyAbstract.getProcessId()
  /** TrxType parameter value that reuses an already identified payment. */
  private val trxTypeSelectedPayment: String = "S"
  /** Amount shared by the unidentified receipt and its identified counterpart. */
  private val payAmt: BigDecimal = new BigDecimal("100.00")

  /** Tenant of the GardenWorld session started by [[AdempiereTestCase]]. */
  private def tenantId: Int = Env.getAD_Client_ID(Context)

  /** Organization of the GardenWorld session started by [[AdempiereTestCase]]. */
  private def organizationId: Int = Env.getAD_Org_ID(Context)

  /** Unidentified completed receipt the process is started against. */
  private var unidentifiedPaymentId: Int = 0
  /** Completed identified payment sent through RelatedPayment_ID. */
  private var identifiedPaymentId: Int = 0

  /** Fixtures created inside the scenario transaction, cleaned up afterwards. */
  private val createdPaymentIds = ArrayBuffer.empty[Int]
  /** Created users, cleaned up after every scenario. */
  private val createdUserIds = ArrayBuffer.empty[Int]

  /** Savepoint taken before each scenario, rolled back after it. */
  private var scenarioSavepoint: Savepoint = null

  Feature("The Identify Payment process guards CollectingAgent_ID by tenant and activity") {

    Scenario("an active same-tenant collecting agent is accepted and persisted (#4464)") {
      Given("a pair of completed receipts of the tested tenant")
      createPaymentFixture()
      val collectingAgentId = resolveActiveTenantUserId()
      assert(collectingAgentId != 0,
        s"an active user of tenant $tenantId is required by this spec")

      When("Identify Payment runs with that collecting agent")
      val processInfo = runIdentifyPayment(collectingAgentId)

      Then("the process reports no error")
      assert(!processInfo.isError,
        s"Identify Payment must succeed for an active same tenant collecting agent, but reported: ${processInfo.getSummary}")

      And("the identified payment persists the collecting agent")
      //	reloaded with a fresh Query, which reads the row back
      //	from the database instead of reusing the object the process mutated
      val stored: MPayment = new Query(Context, I_C_Payment.Table_Name,
        "C_Payment_ID = ?", trxName)
        .setParameters(identifiedPaymentId)
        .first()
      assert(stored != null,
        s"identified payment fixture $identifiedPaymentId must still exist")
      assert(stored.getCollectingAgent_ID == collectingAgentId,
        s"the identified payment must persist collecting agent $collectingAgentId")

      And("exactly one fixture payment holds that collecting agent")
      assert(countPaymentsWithCollectingAgent(collectingAgentId) == 1,
        s"exactly one fixture payment must hold the collecting agent $collectingAgentId")

      And("the identified payment is linked to the unidentified receipt")
      assert(stored.getRef_Payment_ID == unidentifiedPaymentId,
        "an accepted process must link the identified payment to the unidentified receipt")
    }

    Scenario("a foreign-tenant collecting agent is rejected (#4464)") {
      Given("a pair of completed receipts of the tested tenant")
      createPaymentFixture()
      And("a collecting agent belonging to another tenant")
      val foreignUserId = resolveForeignTenantUserId()
      assert(foreignUserId != 0, "a foreign tenant user id is required by this spec")

      When("Identify Payment runs with that foreign tenant collecting agent")
      val processInfo = runIdentifyPayment(foreignUserId)

      Then("the process fails with the collecting agent guard message")
      assert(processInfo.isError,
        "Identify Payment must fail for a foreign tenant collecting agent")
      assertCollectingAgentNotFound(processInfo.getSummary)

      And("no payment holds the rejected user id")
      assert(countPaymentsWithCollectingAgent(foreignUserId) == 0,
        s"no payment may be assigned the foreign tenant collecting agent $foreignUserId")

      And("the persisted identified payment is unchanged")
      assertIdentifiedPaymentUnchanged()
    }

    Scenario("an inactive collecting agent is rejected (#4464)") {
      Given("a pair of completed receipts of the tested tenant")
      createPaymentFixture()
      And("an inactive user of that same tenant")
      val inactiveUserId = createInactiveTenantUser()

      When("Identify Payment runs with that inactive collecting agent")
      val processInfo = runIdentifyPayment(inactiveUserId)

      Then("the process fails with the collecting agent guard message")
      assert(processInfo.isError,
        "Identify Payment must fail for an inactive collecting agent")
      assertCollectingAgentNotFound(processInfo.getSummary)

      And("no payment holds the rejected user id")
      assert(countPaymentsWithCollectingAgent(inactiveUserId) == 0,
        s"no payment may be assigned the inactive collecting agent $inactiveUserId")

      And("the persisted identified payment is unchanged")
      assertIdentifiedPaymentUnchanged()

      And("the fixture user is still there and still inactive")
      val storedUser = new MUser(Context, inactiveUserId, trxName)
      assert(storedUser.getAD_User_ID == inactiveUserId,
        s"inactive fixture user must still exist in tenant $tenantId")
      assert(!storedUser.isActive,
        "the rejected collecting agent must remain inactive, a rejected process may not re-activate it")
    }

    Scenario("an absent collecting agent leaves the payment untouched (#4464)") {
      Given("a pair of completed receipts of the tested tenant")
      createPaymentFixture()

      When("Identify Payment runs without a CollectingAgent_ID parameter")
      val processInfo = runIdentifyPayment(0)

      Then("the collecting agent guard does not raise")
      assert(!isCollectingAgentNotFound(processInfo.getSummary),
        s"an absent CollectingAgent_ID must not raise the collecting agent guard, but was: ${processInfo.getSummary}")

      And("the payment keeps its baseline collecting agent")
      //	the guard is skipped, so the payment keeps its baseline (empty) agent
      val identified = new MPayment(Context, identifiedPaymentId, trxName)
      assert(identified.getC_Payment_ID == identifiedPaymentId,
        "identified payment fixture must still exist")
      assert(identified.getCollectingAgent_ID == 0,
        "an absent CollectingAgent_ID must not change the payment's current collecting agent")
    }
  }

  /**************************************************************************
    * Scenario lifecycle
    *************************************************************************/

  /**
    * Open a savepoint per scenario, mirroring the per-test savepoint the Java
    * suite used: everything a scenario writes lives behind it and is undone in
    * [[afterEach]], so scenarios neither see each other's fixtures nor reach
    * the database.
    */
  override def beforeEach(): Unit = {
    super.beforeEach()
    unidentifiedPaymentId = 0
    identifiedPaymentId = 0
    scenarioSavepoint = Transaction.setSavepoint(s"Identify4464_${System.nanoTime}")
  }

  /**
    * Best-effort removal of the rows created by the scenario, then rollback to
    * the scenario savepoint. [[AdempiereTestCase]] rolls the whole suite
    * transaction back in `afterAll`, so a failure here (for example a foreign
    * key from an allocation created by the accepted run) is not a problem.
    */
  override def afterEach(): Unit = {
    removeFixtureRows()
    if (scenarioSavepoint != null) {
      Try(Transaction.rollback(scenarioSavepoint))
      scenarioSavepoint = null
    } else {
      Transaction.rollback()
    }
    super.afterEach()
  }

  /**************************************************************************
    * Fixture
    *************************************************************************/

  /**
    * Creates the pair of receipts the Identify Payment process expects in the
    * GardenWorld tenant and inside the scenario transaction:
    *  - an unidentified completed receipt (the record the process runs
    *    against), with no charge and no reverse payment yet
    *  - a completed identified receipt with the same receipt flag, bank
    *    account, currency and amount, offered through RelatedPayment_ID
    *
    * Both receipts are flagged `DocStatus = 'CO'` directly instead of being
    * run through the document engine: the guard under test is evaluated before
    * the process completes anything, so no accounting entry is generated by
    * these fixtures and the assertions do not depend on a posting
    * configuration being available.
    */
  private def createPaymentFixture(): Unit = {
    unidentifiedPaymentId = createCompletedReceipt(isUnidentified = true)
    identifiedPaymentId = createCompletedReceipt(isUnidentified = false)
  }

  /**
    * Create a completed receipt on the tenant's bank account.
    *
    * @param isUnidentified
    *   true for the unidentified receipt, false for the identified counterpart
    * @return the persisted C_Payment_ID
    */
  private def createCompletedReceipt(isUnidentified: Boolean): Int = {
    val bankAccount = resolveBankAccount()
    val payment = new MPayment(Context, 0, trxName)
    payment.setAD_Org_ID(if (bankAccount.getAD_Org_ID != 0) bankAccount.getAD_Org_ID else organizationId)
    payment.setIsReceipt(true)
    payment.setC_BPartner_ID(resolveBPartnerId())
    payment.setC_BankAccount_ID(bankAccount.getC_BankAccount_ID)
    payment.setC_Currency_ID(bankAccount.getC_Currency_ID)
    payment.setTenderType(X_C_Payment.TENDERTYPE_DirectDeposit)
    payment.setPayAmt(payAmt)
    payment.setDiscountAmt(Env.ZERO)
    payment.setOverUnderAmt(Env.ZERO)
    payment.setDateTrx(Today)
    payment.setDateAcct(Today)
    payment.setIsUnidentifiedPayment(isUnidentified)
    if (isUnidentified) {
      //	unidentified receipts use the organization's unidentified document type
      val organizationInfo = MOrgInfo.get(Context, payment.getAD_Org_ID, trxName)
      val unidentifiedDocumentTypeId = organizationInfo.getUnidentifiedDocumentType(true)
      if (unidentifiedDocumentTypeId != 0) {
        payment.setC_DocType_ID(unidentifiedDocumentTypeId)
      } else {
        payment.setC_DocType_ID(true)
      }
    } else {
      payment.setC_DocType_ID(true)
    }
    payment.saveEx(trxName)
    createdPaymentIds += payment.getC_Payment_ID
    //	fixture shortcut, see createPaymentFixture()
    payment.setDocStatus(X_C_Payment.DOCSTATUS_Completed)
    //	the C_Payment.CollectingAgent_ID dictionary default is @#AD_User_ID@
    //(migration 393lts-394lts/06550), so a fresh receipt is stamped with the
    //	running user; clear it here to pin a deterministic empty baseline
    payment.setCollectingAgent_ID(0)
    payment.saveEx(trxName)
    payment.getC_Payment_ID
  }

  /**
    * Start Identify Payment through its public interface.
    *
    * @param collectingAgentId
    *   the CollectingAgent_ID parameter; when 0 the parameter is not sent at
    *   all, which the process reads as 0
    * @return the process info carrying the observable outcome
    */
  private def runIdentifyPayment(collectingAgentId: Int): ProcessInfo = {
    val processInfo = new ProcessInfo(PaymentIdentifyAbstract.getProcessName,
      processId, I_C_Payment.Table_ID, unidentifiedPaymentId)
    processInfo.addParameter(PaymentIdentifyAbstract.TRXTYPE, trxTypeSelectedPayment, null)
    processInfo.addParameter(PaymentIdentifyAbstract.C_BPARTNER_ID, resolveBPartnerId(), null)
    processInfo.addParameter(PaymentIdentifyAbstract.RELATEDPAYMENT_ID, identifiedPaymentId, null)
    processInfo.addParameter(PaymentIdentifyAbstract.DATETRX, Today, null)
    if (collectingAgentId != 0) {
      processInfo.addParameter(PaymentIdentifyAbstract.COLLECTINGAGENT_ID, collectingAgentId, null)
    }

    val instance = new MPInstance(Context, processId, unidentifiedPaymentId)
    instance.saveEx(trxName)
    processInfo.setAD_PInstance_ID(instance.getAD_PInstance_ID)

    //	the scenario transaction is handed to the process so it can read the
    //	uncommitted fixtures; afterEach rolls it back to the scenario savepoint
    val process = new PaymentIdentify()
    process.startProcess(Context, processInfo, Transaction)
    processInfo
  }

  /**************************************************************************
    * Lookups and assertions over persisted data
    *************************************************************************/

  /** @return an active bank account of the tested tenant */
  private def resolveBankAccount(): MBankAccount = {
    val bankAccount: MBankAccount = new Query(Context, I_C_BankAccount.Table_Name,
      "AD_Client_ID = ? AND IsActive = 'Y'", trxName)
      .setParameters(tenantId)
      .setOrderBy("C_BankAccount_ID")
      .first()
    assert(bankAccount != null,
      s"GardenWorld seed must provide an active bank account for tenant $tenantId")
    bankAccount
  }

  /**
    * @return an active business partner of the tested tenant, required when a
    *         receipt is saved without a charge
    */
  private def resolveBPartnerId(): Int = {
    val partner: MBPartner = new Query(Context, I_C_BPartner.Table_Name,
      "AD_Client_ID = ? AND IsActive = 'Y'", trxName)
      .setParameters(tenantId)
      .setOrderBy("C_BPartner_ID")
      .first()
    assert(partner != null,
      s"GardenWorld seed must provide an active business partner for tenant $tenantId")
    partner.getC_BPartner_ID
  }

  /**
    * @return an active user of another tenant, or an id that cannot belong to
    *         any tenant when this database only holds GardenWorld
    */
  private def resolveForeignTenantUserId(): Int = {
    val foreignUser: MUser = new Query(Context, I_AD_User.Table_Name,
      "AD_User_ID > 0 AND AD_Client_ID <> ? AND IsActive = 'Y'", trxName)
      .setParameters(tenantId)
      .setOrderBy("AD_User_ID")
      .first()
    if (foreignUser != null) {
      foreignUser.getAD_User_ID
    } else {
      //	PO.setAD_Client_ID is not public, so a second tenant cannot be created
      //	from here; an unresolvable id exercises the same guard branch
      Integer.MAX_VALUE
    }
  }

  /**
    * @return an active user of the tested tenant, the collecting agent the
    *         guard is expected to accept for the fixture payments
    */
  private def resolveActiveTenantUserId(): Int = {
    val activeUser: MUser = new Query(Context, I_AD_User.Table_Name,
      "AD_User_ID > 0 AND AD_Client_ID = ? AND IsActive = 'Y'", trxName)
      .setParameters(tenantId)
      .setOrderBy("AD_User_ID")
      .first()
    assert(activeUser != null,
      s"GardenWorld seed must provide an active user for tenant $tenantId")
    activeUser.getAD_User_ID
  }

  /**
    * Create an inactive user inside the tested tenant.
    *
    * @return the persisted AD_User_ID
    */
  private def createInactiveTenantUser(): Int = {
    val suffix = java.lang.Long.toString(System.nanoTime)
    val user = new MUser(Context, 0, trxName)
    user.setAD_Org_ID(organizationId)
    user.setName(s"IT Inactive Agent $suffix")
    user.setValue(s"IT-INACT-$suffix")
    user.setEMail(s"it.inactive.$suffix@adempiere.test")
    user.setIsActive(false)
    user.saveEx(trxName)
    createdUserIds += user.getAD_User_ID
    user.getAD_User_ID
  }

  /**
    * @param collectingAgentId
    *   user id to look for
    * @return number of fixture payments holding that collecting agent
    */
  private def countPaymentsWithCollectingAgent(collectingAgentId: Int): Int = {
    new Query(Context, I_C_Payment.Table_Name,
      "C_Payment_ID IN (?,?) AND CollectingAgent_ID = ?", trxName)
      .setParameters(unidentifiedPaymentId, identifiedPaymentId, collectingAgentId)
      .count()
  }

  /**
    * A rejected process must leave the identified payment exactly as it was:
    * the guard throws before the assignment and before the payment is linked
    * to the unidentified receipt, so nothing may be persisted.
    */
  private def assertIdentifiedPaymentUnchanged(): Unit = {
    val identified = new MPayment(Context, identifiedPaymentId, trxName)
    assert(identified.getC_Payment_ID == identifiedPaymentId,
      "identified payment fixture must still exist")
    assert(identified.getCollectingAgent_ID == 0,
      "a rejected process must not persist any collecting agent")
    assert(identified.getRef_Payment_ID == 0,
      "a rejected process must not link the identified payment to the unidentified receipt")
    assert(!identified.isReconciled,
      "a rejected process must not reconcile the identified payment")
    assert(identified.getDocStatus == X_C_Payment.DOCSTATUS_Completed,
      "a rejected process must not change the identified payment document status")
  }

  /**
    * @param summary
    *   process summary as reported by the process info
    * @return true when the summary is the collecting agent guard message
    */
  private def isCollectingAgentNotFound(summary: String): Boolean = {
    if (summary == null) {
      false
    } else {
      //	SvrProcess translates the message before storing it on the process
      //	info; accept both the translated form and the raw dictionary keys
      val translated = Msg.parseTranslation(Context,
        s"@${PaymentIdentifyAbstract.COLLECTINGAGENT_ID}@ @NotFound@")
      summary.contains(translated) ||
      (summary.contains(s"@${PaymentIdentifyAbstract.COLLECTINGAGENT_ID}@")
        && summary.contains("@NotFound@"))
    }
  }

  /**
    * @param summary
    *   process summary as reported by the process info
    */
  private def assertCollectingAgentNotFound(summary: String): Unit = {
    assert(isCollectingAgentNotFound(summary),
      s"process must report @CollectingAgent_ID@ @NotFound@, but reported: $summary")
  }

  /**
    * Remove the rows created by each scenario. [[AdempiereTestCase]] rolls the
    * whole suite transaction back, so failures here (for example a foreign key
    * from an allocation created by the accepted run) are not a problem.
    */
  private def removeFixtureRows(): Unit = {
    createdPaymentIds.foreach { paymentId =>
      try {
        new MPayment(Context, paymentId, trxName).deleteEx(true, trxName)
      } catch {
        //	rolled back by the scenario savepoint
        case _: Exception => ()
      }
    }
    createdPaymentIds.clear()
    createdUserIds.foreach { userId =>
      try {
        new MUser(Context, userId, trxName).deleteEx(true, trxName)
      } catch {
        //	rolled back by the scenario savepoint
        case _: Exception => ()
      }
    }
    createdUserIds.clear()
  }
}
