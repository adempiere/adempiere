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

import org.adempiere.core.domains.models.{I_AD_User, I_C_BankAccount, I_C_BPartner, I_C_Payment, X_C_Payment}
import org.compiere.model.{MBankAccount, MBPartner, MOrgInfo, MPInstance, MPayment, MUser, Query}
import org.compiere.process.ProcessInfo
import org.compiere.util.{Env, Msg}
import org.junit.jupiter.api.Assertions.{assertEquals, assertFalse, assertNotNull, assertTrue}
import org.junit.jupiter.api.{AfterEach, Tag, Test}
import org.spin.process.{PaymentIdentify, PaymentIdentifyAbstract}

import scala.collection.mutable

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
  * @author ADempiere Foundation
  */
@Tag("Payment")
@Tag("PaymentIdentify")
class IdentifyPaymentCollectingAgentSpec extends CommonGWSetup {

  /** Identify Payment process (AD_Process_ID 54306). */
  private val processId: Int = PaymentIdentifyAbstract.getProcessId()
  /** TrxType parameter value that reuses an already identified payment. */
  private val trxTypeSelectedPayment: String = "S"
  /** Amount shared by the unidentified receipt and its identified counterpart. */
  private val payAmt: BigDecimal = new BigDecimal("100.00")

  /** Unidentified completed receipt the process is started against. */
  private var unidentifiedPaymentId: Int = 0
  /** Completed identified payment sent through RelatedPayment_ID. */
  private var identifiedPaymentId: Int = 0

  /** Fixtures created inside the test transaction, cleaned up afterwards. */
  private val createdPaymentIds = mutable.ArrayBuffer[Int]()
  /** Created users, cleaned up after every test. */
  private val createdUserIds = mutable.ArrayBuffer[Int]()

  /**
    * S3: a collecting agent that belongs to another tenant must never be
    * assigned. The process must fail with the guard message and no payment
    * may end up holding that user id.
    */
  @Test
  def testForeignTenantCollectingAgentIsRejected(): Unit = {
    createPaymentFixture()
    val foreignUserId = resolveForeignTenantUserId()
    assertTrue(foreignUserId != 0, "a foreign tenant user id is required by this spec")

    val processInfo = runIdentifyPayment(foreignUserId)

    assertTrue(processInfo.isError(),
      "Identify Payment must fail for a foreign tenant collecting agent")
    assertCollectingAgentNotFound(processInfo.getSummary())
    //	persisted state: the rejected user must not be stored anywhere
    assertEquals(0, countPaymentsWithCollectingAgent(foreignUserId),
      s"no payment may be assigned the foreign tenant collecting agent $foreignUserId")
    assertIdentifiedPaymentUnchanged()
  }

  /**
    * S4: an inactive user of the same tenant must never be assigned either;
    * the guard filters <code>IsActive = 'Y'</code>.
    */
  @Test
  def testInactiveCollectingAgentIsRejected(): Unit = {
    createPaymentFixture()
    val inactiveUserId = createInactiveTenantUser()

    val processInfo = runIdentifyPayment(inactiveUserId)

    assertTrue(processInfo.isError(),
      "Identify Payment must fail for an inactive collecting agent")
    assertCollectingAgentNotFound(processInfo.getSummary())
    assertEquals(0, countPaymentsWithCollectingAgent(inactiveUserId),
      s"no payment may be assigned the inactive collecting agent $inactiveUserId")
    assertIdentifiedPaymentUnchanged()
    //	the fixture user is still there and still inactive
    val storedUser = new MUser(getCtx(), inactiveUserId, getTrxName())
    assertEquals(inactiveUserId, storedUser.getAD_User_ID(),
      s"inactive fixture user must still exist in tenant ${CommonGWSetup.AD_CLIENT_ID}")
    assertFalse(storedUser.isActive(),
      "the rejected collecting agent must remain inactive, a rejected process may not re-activate it")
  }

  /**
    * S2: when the CollectingAgent_ID parameter is absent (the process reads
    * it as 0) the guard must not raise the NotFound error and must not touch
    * the payment's current collecting agent. The process itself may still
    * fail for unrelated fixture reasons (for example accounting of the
    * generated reverse payment); what is pinned here is that the failure is
    * never the collecting agent guard.
    */
  @Test
  def testAbsentCollectingAgentDoesNotRaiseNotFound(): Unit = {
    createPaymentFixture()

    val processInfo = runIdentifyPayment(0)

    assertFalse(isCollectingAgentNotFound(processInfo.getSummary()),
      s"an absent CollectingAgent_ID must not raise the collecting agent guard, but was: ${processInfo.getSummary()}")
    //	the guard is skipped, so the payment keeps its baseline (empty) agent
    val identified = new MPayment(getCtx(), identifiedPaymentId, getTrxName())
    assertEquals(identifiedPaymentId, identified.getC_Payment_ID(),
      "identified payment fixture must still exist")
    assertEquals(0, identified.getCollectingAgent_ID(),
      "an absent CollectingAgent_ID must not change the payment's current collecting agent")
  }

  /**
    * S1: an active user of the identified payment's tenant is a valid
    * collecting agent. The guard must let the process through and the user id
    * must reach the database, so removing the assignment is detected here.
    */
  @Test
  def testActiveSameTenantCollectingAgentIsAssigned(): Unit = {
    createPaymentFixture()
    val collectingAgentId = resolveActiveTenantUserId()
    assertTrue(collectingAgentId != 0,
      s"an active user of tenant ${CommonGWSetup.AD_CLIENT_ID} is required by this spec")

    val processInfo = runIdentifyPayment(collectingAgentId)

    assertFalse(processInfo.isError(),
      s"Identify Payment must succeed for an active same tenant collecting agent, but reported: ${processInfo.getSummary()}")
    //	persisted state: reloaded with a fresh Query, which reads the row back
    //	from the database instead of reusing the object the process mutated
    val stored: MPayment = new Query(getCtx(), I_C_Payment.Table_Name,
      "C_Payment_ID = ?", getTrxName())
      .setParameters(identifiedPaymentId)
      .first()
    assertNotNull(stored,
      s"identified payment fixture $identifiedPaymentId must still exist")
    assertEquals(collectingAgentId, stored.getCollectingAgent_ID(),
      s"the identified payment must persist collecting agent $collectingAgentId")
    assertEquals(1, countPaymentsWithCollectingAgent(collectingAgentId),
      s"exactly one fixture payment must hold the collecting agent $collectingAgentId")
    assertEquals(unidentifiedPaymentId, stored.getRef_Payment_ID(),
      "an accepted process must link the identified payment to the unidentified receipt")
  }

  /**************************************************************************
    * Fixture
    *************************************************************************/

  /**
    * Creates the pair of receipts the Identify Payment process expects in the
    * GardenWorld tenant and inside the test transaction:
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
    val payment = new MPayment(getCtx(), 0, getTrxName())
    payment.setAD_Org_ID(if (bankAccount.getAD_Org_ID() != 0) bankAccount.getAD_Org_ID() else CommonGWSetup.AD_ORG_ID)
    payment.setIsReceipt(true)
    payment.setC_BPartner_ID(resolveBPartnerId())
    payment.setC_BankAccount_ID(bankAccount.getC_BankAccount_ID())
    payment.setC_Currency_ID(bankAccount.getC_Currency_ID())
    payment.setTenderType(X_C_Payment.TENDERTYPE_DirectDeposit)
    payment.setPayAmt(payAmt)
    payment.setDiscountAmt(Env.ZERO)
    payment.setOverUnderAmt(Env.ZERO)
    payment.setDateTrx(CommonGWSetup.today)
    payment.setDateAcct(CommonGWSetup.today)
    payment.setIsUnidentifiedPayment(isUnidentified)
    if (isUnidentified) {
      //	unidentified receipts use the organization's unidentified document type
      val organizationInfo = MOrgInfo.get(getCtx(), payment.getAD_Org_ID(), getTrxName())
      val unidentifiedDocumentTypeId = organizationInfo.getUnidentifiedDocumentType(true)
      if (unidentifiedDocumentTypeId != 0) {
        payment.setC_DocType_ID(unidentifiedDocumentTypeId)
      } else {
        payment.setC_DocType_ID(true)
      }
    } else {
      payment.setC_DocType_ID(true)
    }
    payment.saveEx(getTrxName())
    createdPaymentIds += payment.getC_Payment_ID()
    //	fixture shortcut, see createPaymentFixture()
    payment.setDocStatus(X_C_Payment.DOCSTATUS_Completed)
    //	the C_Payment.CollectingAgent_ID dictionary default is @#AD_User_ID@
    //(migration 393lts-394lts/06550), so a fresh receipt is stamped with the
    //	running user; clear it here to pin a deterministic empty baseline
    payment.setCollectingAgent_ID(0)
    payment.saveEx(getTrxName())
    payment.getC_Payment_ID()
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
    val processInfo = new ProcessInfo(PaymentIdentifyAbstract.getProcessName(),
      processId, I_C_Payment.Table_ID, unidentifiedPaymentId)
    processInfo.addParameter(PaymentIdentifyAbstract.TRXTYPE, trxTypeSelectedPayment, null)
    processInfo.addParameter(PaymentIdentifyAbstract.C_BPARTNER_ID, resolveBPartnerId(), null)
    processInfo.addParameter(PaymentIdentifyAbstract.RELATEDPAYMENT_ID, identifiedPaymentId, null)
    processInfo.addParameter(PaymentIdentifyAbstract.DATETRX, CommonGWSetup.today, null)
    if (collectingAgentId != 0) {
      processInfo.addParameter(PaymentIdentifyAbstract.COLLECTINGAGENT_ID, collectingAgentId, null)
    }

    val instance = new MPInstance(getCtx(), processId, unidentifiedPaymentId)
    instance.saveEx(getTrxName())
    processInfo.setAD_PInstance_ID(instance.getAD_PInstance_ID())

    //	the fixture transaction is handed to the process so it can read the
    //	uncommitted fixtures; CommonGWSetup rolls it back after each test
    val process = new PaymentIdentify()
    process.startProcess(getCtx(), processInfo, CommonGWSetup.trx)
    processInfo
  }

  /**************************************************************************
    * Lookups and assertions over persisted data
    *************************************************************************/

  /** @return an active bank account of the tested tenant */
  private def resolveBankAccount(): MBankAccount = {
    val bankAccount: MBankAccount = new Query(getCtx(), I_C_BankAccount.Table_Name,
      "AD_Client_ID = ? AND IsActive = 'Y'", getTrxName())
      .setParameters(CommonGWSetup.AD_CLIENT_ID)
      .setOrderBy("C_BankAccount_ID")
      .first()
    assertNotNull(bankAccount,
      s"GardenWorld seed must provide an active bank account for tenant ${CommonGWSetup.AD_CLIENT_ID}")
    bankAccount
  }

  /**
    * @return an active business partner of the tested tenant, required when a
    *         receipt is saved without a charge
    */
  private def resolveBPartnerId(): Int = {
    val partner: MBPartner = new Query(getCtx(), I_C_BPartner.Table_Name,
      "AD_Client_ID = ? AND IsActive = 'Y'", getTrxName())
      .setParameters(CommonGWSetup.AD_CLIENT_ID)
      .setOrderBy("C_BPartner_ID")
      .first()
    assertNotNull(partner,
      s"GardenWorld seed must provide an active business partner for tenant ${CommonGWSetup.AD_CLIENT_ID}")
    partner.getC_BPartner_ID()
  }

  /**
    * @return an active user of another tenant, or an id that cannot belong to
    *         any tenant when this database only holds GardenWorld
    */
  private def resolveForeignTenantUserId(): Int = {
    val foreignUser: MUser = new Query(getCtx(), I_AD_User.Table_Name,
      "AD_User_ID > 0 AND AD_Client_ID <> ? AND IsActive = 'Y'", getTrxName())
      .setParameters(CommonGWSetup.AD_CLIENT_ID)
      .setOrderBy("AD_User_ID")
      .first()
    if (foreignUser != null) {
      foreignUser.getAD_User_ID()
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
    val activeUser: MUser = new Query(getCtx(), I_AD_User.Table_Name,
      "AD_User_ID > 0 AND AD_Client_ID = ? AND IsActive = 'Y'", getTrxName())
      .setParameters(CommonGWSetup.AD_CLIENT_ID)
      .setOrderBy("AD_User_ID")
      .first()
    assertNotNull(activeUser,
      s"GardenWorld seed must provide an active user for tenant ${CommonGWSetup.AD_CLIENT_ID}")
    activeUser.getAD_User_ID()
  }

  /**
    * Create an inactive user inside the tested tenant.
    *
    * @return the persisted AD_User_ID
    */
  private def createInactiveTenantUser(): Int = {
    val suffix = java.lang.Long.toString(System.nanoTime())
    val user = new MUser(getCtx(), 0, getTrxName())
    user.setAD_Org_ID(CommonGWSetup.AD_ORG_ID)
    user.setName(s"IT Inactive Agent $suffix")
    user.setValue(s"IT-INACT-$suffix")
    user.setEMail(s"it.inactive.$suffix@adempiere.test")
    user.setIsActive(false)
    user.saveEx(getTrxName())
    createdUserIds += user.getAD_User_ID()
    user.getAD_User_ID()
  }

  /**
    * @param collectingAgentId
    *   user id to look for
    * @return number of fixture payments holding that collecting agent
    */
  private def countPaymentsWithCollectingAgent(collectingAgentId: Int): Int = {
    new Query(getCtx(), I_C_Payment.Table_Name,
      "C_Payment_ID IN (?,?) AND CollectingAgent_ID = ?", getTrxName())
      .setParameters(unidentifiedPaymentId, identifiedPaymentId, collectingAgentId)
      .count()
  }

  /**
    * A rejected process must leave the identified payment exactly as it was:
    * the guard throws before the assignment and before the payment is linked
    * to the unidentified receipt, so nothing may be persisted.
    */
  private def assertIdentifiedPaymentUnchanged(): Unit = {
    val identified = new MPayment(getCtx(), identifiedPaymentId, getTrxName())
    assertEquals(identifiedPaymentId, identified.getC_Payment_ID(),
      "identified payment fixture must still exist")
    assertEquals(0, identified.getCollectingAgent_ID(),
      "a rejected process must not persist any collecting agent")
    assertEquals(0, identified.getRef_Payment_ID(),
      "a rejected process must not link the identified payment to the unidentified receipt")
    assertFalse(identified.isReconciled(),
      "a rejected process must not reconcile the identified payment")
    assertEquals(X_C_Payment.DOCSTATUS_Completed, identified.getDocStatus(),
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
      val translated = Msg.parseTranslation(getCtx(),
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
    assertTrue(isCollectingAgentNotFound(summary),
      s"process must report @CollectingAgent_ID@ @NotFound@, but reported: $summary")
  }

  /**
    * Remove the rows created by each test. `CommonGWSetup` rolls the whole
    * test transaction back, so failures here (for example a foreign key from
    * an allocation created by the S2 run) are not a problem.
    */
  @AfterEach
  def removeFixtureRows(): Unit = {
    createdPaymentIds.foreach { paymentId =>
      try {
        new MPayment(getCtx(), paymentId, getTrxName()).deleteEx(true, getTrxName())
      } catch {
        //	rolled back by CommonGWSetup
        case _: Exception => ()
      }
    }
    createdPaymentIds.clear()
    createdUserIds.foreach { userId =>
      try {
        new MUser(getCtx(), userId, getTrxName()).deleteEx(true, getTrxName())
      } catch {
        //	rolled back by CommonGWSetup
        case _: Exception => ()
      }
    }
    createdUserIds.clear()
  }
}
