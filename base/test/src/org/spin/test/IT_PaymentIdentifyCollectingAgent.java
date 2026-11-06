/******************************************************************************
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
 *****************************************************************************/
package org.spin.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.core.domains.models.I_AD_User;
import org.adempiere.core.domains.models.I_C_BankAccount;
import org.adempiere.core.domains.models.I_C_BPartner;
import org.adempiere.core.domains.models.I_C_Payment;
import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MBankAccount;
import org.compiere.model.MBPartner;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPInstance;
import org.compiere.model.MPayment;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.spin.process.PaymentIdentify;
import org.spin.process.PaymentIdentifyAbstract;

/**
 * Regression tests for the Collecting Agent tenant/active guard added to the
 * Identify Payment process (adempiere/adempiere issue #4464, PR #4465).
 * <p>
 * Before {@code identifiedPayment.saveEx()} the process must resolve the
 * {@code CollectingAgent_ID} parameter inside the identified payment's tenant
 * considering active users only:
 * <pre>
 * AD_User_ID = ? AND AD_Client_ID = ? AND IsActive = 'Y'
 * </pre>
 * and must raise <code>@CollectingAgent_ID@ @NotFound@</code> when the user
 * cannot be resolved there, so a caller cannot attach another tenant's user,
 * or a disabled user, through the raw process parameter.
 * <p>
 * The process is always started through its public interface
 * ({@link ProcessInfo} + {@link PaymentIdentify#startProcess}) and the
 * assertions only read observable effects: the reported process error and the
 * persisted {@code C_Payment} columns read back with a fresh
 * {@link Query}/{@link MPayment}. No internal process state is inspected.
 *
 * @author ADempiere Foundation
 */
@Tag("Payment")
@Tag("PaymentIdentify")
class IT_PaymentIdentifyCollectingAgent extends CommonGWSetup {

	/**	Identify Payment process (AD_Process_ID 54306).	*/
	private static final int PROCESS_ID = PaymentIdentifyAbstract.getProcessId();
	/**	TrxType parameter value that reuses an already identified payment.	*/
	private static final String TRXTYPE_SELECTED_PAYMENT = "S";
	/**	Amount shared by the unidentified receipt and its identified counterpart.	*/
	private static final BigDecimal PAY_AMT = new BigDecimal("100.00");

	/**	Unidentified completed receipt the process is started against.	*/
	private int unidentifiedPaymentId;
	/**	Completed identified payment sent through RelatedPayment_ID.	*/
	private int identifiedPaymentId;

	/**	Fixtures created inside the test transaction, cleaned up afterwards.	*/
	private final List<Integer> createdPaymentIds = new ArrayList<>();
	/**	Created users, cleaned up after every test.	*/
	private final List<Integer> createdUserIds = new ArrayList<>();

	/**
	 * S3: a collecting agent that belongs to another tenant must never be
	 * assigned. The process must fail with the guard message and no payment
	 * may end up holding that user id.
	 */
	@Test
	void testForeignTenantCollectingAgentIsRejected() {
		createPaymentFixture();
		int foreignUserId = resolveForeignTenantUserId();
		assertTrue(foreignUserId != 0, "a foreign tenant user id is required by this spec");

		ProcessInfo processInfo = runIdentifyPayment(foreignUserId);

		assertTrue(processInfo.isError(),
				"Identify Payment must fail for a foreign tenant collecting agent");
		assertCollectingAgentNotFound(processInfo.getSummary());
		//	persisted state: the rejected user must not be stored anywhere
		assertEquals(0, countPaymentsWithCollectingAgent(foreignUserId),
				"no payment may be assigned the foreign tenant collecting agent " + foreignUserId);
		assertIdentifiedPaymentUnchanged();
	}

	/**
	 * S4: an inactive user of the same tenant must never be assigned either;
	 * the guard filters <code>IsActive = 'Y'</code>.
	 */
	@Test
	void testInactiveCollectingAgentIsRejected() {
		createPaymentFixture();
		int inactiveUserId = createInactiveTenantUser();

		ProcessInfo processInfo = runIdentifyPayment(inactiveUserId);

		assertTrue(processInfo.isError(),
				"Identify Payment must fail for an inactive collecting agent");
		assertCollectingAgentNotFound(processInfo.getSummary());
		assertEquals(0, countPaymentsWithCollectingAgent(inactiveUserId),
				"no payment may be assigned the inactive collecting agent " + inactiveUserId);
		assertIdentifiedPaymentUnchanged();
		//	the fixture user is still there and still inactive
		MUser storedUser = new MUser(getCtx(), inactiveUserId, getTrxName());
		assertEquals(inactiveUserId, storedUser.getAD_User_ID(),
				"inactive fixture user must still exist in tenant " + AD_CLIENT_ID);
		assertFalse(storedUser.isActive(),
				"the rejected collecting agent must remain inactive, a rejected process may not re-activate it");
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
	void testAbsentCollectingAgentDoesNotRaiseNotFound() {
		createPaymentFixture();

		ProcessInfo processInfo = runIdentifyPayment(0);

		assertFalse(isCollectingAgentNotFound(processInfo.getSummary()),
				"an absent CollectingAgent_ID must not raise the collecting agent guard, but was: "
						+ processInfo.getSummary());
		//	the guard is skipped, so the payment keeps its current (empty) agent
		MPayment identified = new MPayment(getCtx(), identifiedPaymentId, getTrxName());
		assertEquals(identifiedPaymentId, identified.getC_Payment_ID(),
				"identified payment fixture must still exist");
		assertEquals(0, identified.getCollectingAgent_ID(),
				"an absent CollectingAgent_ID must not assign any collecting agent");
	}

	/**************************************************************************
	 * Fixture
	 *************************************************************************/

	/**
	 * Creates the pair of receipts the Identify Payment process expects in the
	 * GardenWorld tenant and inside the test transaction:
	 * <ul>
	 * <li>an unidentified completed receipt (the record the process runs
	 * against), with no charge and no reverse payment yet</li>
	 * <li>a completed identified receipt with the same receipt flag, bank
	 * account, currency and amount, offered through RelatedPayment_ID</li>
	 * </ul>
	 * <p>
	 * Both receipts are flagged <code>DocStatus = 'CO'</code> directly instead
	 * of being run through the document engine: the guard under test is
	 * evaluated before the process completes anything, so no accounting entry
	 * is generated by these fixtures and the assertions do not depend on a
	 * posting configuration being available.
	 */
	private void createPaymentFixture() {
		unidentifiedPaymentId = createCompletedReceipt(true);
		identifiedPaymentId = createCompletedReceipt(false);
	}

	/**
	 * Create a completed receipt on the tenant's bank account.
	 * @param isUnidentified true for the unidentified receipt, false for the
	 *            identified counterpart
	 * @return the persisted C_Payment_ID
	 */
	private int createCompletedReceipt(boolean isUnidentified) {
		MBankAccount bankAccount = resolveBankAccount();
		MPayment payment = new MPayment(getCtx(), 0, getTrxName());
		payment.setAD_Org_ID(bankAccount.getAD_Org_ID() != 0? bankAccount.getAD_Org_ID(): AD_ORG_ID);
		payment.setIsReceipt(true);
		payment.setC_BPartner_ID(resolveBPartnerId());
		payment.setC_BankAccount_ID(bankAccount.getC_BankAccount_ID());
		payment.setC_Currency_ID(bankAccount.getC_Currency_ID());
		payment.setTenderType(MPayment.TENDERTYPE_DirectDeposit);
		payment.setPayAmt(PAY_AMT);
		payment.setDiscountAmt(Env.ZERO);
		payment.setOverUnderAmt(Env.ZERO);
		payment.setDateTrx(today);
		payment.setDateAcct(today);
		payment.setIsUnidentifiedPayment(isUnidentified);
		if (isUnidentified) {
			//	unidentified receipts use the organization's unidentified document type
			MOrgInfo organizationInfo = MOrgInfo.get(getCtx(), payment.getAD_Org_ID(), getTrxName());
			int unidentifiedDocumentTypeId = organizationInfo.getUnidentifiedDocumentType(true);
			if (unidentifiedDocumentTypeId != 0) {
				payment.setC_DocType_ID(unidentifiedDocumentTypeId);
			} else {
				payment.setC_DocType_ID(true);
			}
		} else {
			payment.setC_DocType_ID(true);
		}
		payment.saveEx(getTrxName());
		createdPaymentIds.add(payment.getC_Payment_ID());
		//	fixture shortcut, see createPaymentFixture()
		payment.setDocStatus(MPayment.DOCSTATUS_Completed);
		payment.saveEx(getTrxName());
		return payment.getC_Payment_ID();
	}

	/**
	 * Start Identify Payment through its public interface.
	 * @param collectingAgentId the CollectingAgent_ID parameter; when 0 the
	 *            parameter is not sent at all, which the process reads as 0
	 * @return the process info carrying the observable outcome
	 */
	private ProcessInfo runIdentifyPayment(int collectingAgentId) {
		ProcessInfo processInfo = new ProcessInfo(PaymentIdentifyAbstract.getProcessName(),
				PROCESS_ID, I_C_Payment.Table_ID, unidentifiedPaymentId);
		processInfo.addParameter(PaymentIdentifyAbstract.TRXTYPE, TRXTYPE_SELECTED_PAYMENT, null);
		processInfo.addParameter(PaymentIdentifyAbstract.RELATEDPAYMENT_ID, identifiedPaymentId, null);
		processInfo.addParameter(PaymentIdentifyAbstract.DATETRX, today, null);
		if (collectingAgentId != 0) {
			processInfo.addParameter(PaymentIdentifyAbstract.COLLECTINGAGENT_ID, collectingAgentId, null);
		}

		MPInstance instance = new MPInstance(getCtx(), PROCESS_ID, unidentifiedPaymentId);
		instance.saveEx(getTrxName());
		processInfo.setAD_PInstance_ID(instance.getAD_PInstance_ID());

		//	the fixture transaction is handed to the process so it can read the
		//	uncommitted fixtures; CommonGWSetup rolls it back after each test
		PaymentIdentify process = new PaymentIdentify();
		process.startProcess(getCtx(), processInfo, trx);
		return processInfo;
	}

	/**************************************************************************
	 * Lookups and assertions over persisted data
	 *************************************************************************/

	/**
	 * @return an active bank account of the tested tenant
	 */
	private MBankAccount resolveBankAccount() {
		MBankAccount bankAccount = new Query(getCtx(), I_C_BankAccount.Table_Name,
				"AD_Client_ID = ? AND IsActive = 'Y'", getTrxName())
				.setParameters(AD_CLIENT_ID)
				.setOrderBy("C_BankAccount_ID")
				.first();
		assertNotNull(bankAccount,
				"GardenWorld seed must provide an active bank account for tenant " + AD_CLIENT_ID);
		return bankAccount;
	}

	/**
	 * @return an active business partner of the tested tenant, required when a
	 *         receipt is saved without a charge
	 */
	private int resolveBPartnerId() {
		MBPartner partner = new Query(getCtx(), I_C_BPartner.Table_Name,
				"AD_Client_ID = ? AND IsActive = 'Y'", getTrxName())
				.setParameters(AD_CLIENT_ID)
				.setOrderBy("C_BPartner_ID")
				.first();
		assertNotNull(partner,
				"GardenWorld seed must provide an active business partner for tenant " + AD_CLIENT_ID);
		return partner.getC_BPartner_ID();
	}

	/**
	 * @return an active user of another tenant, or an id that cannot belong to
	 *         any tenant when this database only holds GardenWorld
	 */
	private int resolveForeignTenantUserId() {
		MUser foreignUser = new Query(getCtx(), I_AD_User.Table_Name,
				"AD_Client_ID <> ? AND IsActive = 'Y'", getTrxName())
				.setParameters(AD_CLIENT_ID)
				.setOrderBy("AD_User_ID")
				.first();
		if (foreignUser != null) {
			return foreignUser.getAD_User_ID();
		}
		//	PO.setAD_Client_ID is not public, so a second tenant cannot be created
		//	from here; an unresolvable id exercises the same guard branch
		return Integer.MAX_VALUE;
	}

	/**
	 * Create an inactive user inside the tested tenant.
	 * @return the persisted AD_User_ID
	 */
	private int createInactiveTenantUser() {
		String suffix = Long.toString(System.nanoTime());
		MUser user = new MUser(getCtx(), 0, getTrxName());
		user.setAD_Org_ID(AD_ORG_ID);
		user.setName("IT Inactive Agent " + suffix);
		user.setValue("IT-INACT-" + suffix);
		user.setEMail("it.inactive." + suffix + "@adempiere.test");
		user.setIsActive(false);
		user.saveEx(getTrxName());
		createdUserIds.add(user.getAD_User_ID());
		return user.getAD_User_ID();
	}

	/**
	 * @param collectingAgentId user id to look for
	 * @return number of persisted payments holding that collecting agent
	 */
	private int countPaymentsWithCollectingAgent(int collectingAgentId) {
		return new Query(getCtx(), I_C_Payment.Table_Name,
				"CollectingAgent_ID = ?", getTrxName())
				.setParameters(collectingAgentId)
				.count();
	}

	/**
	 * A rejected process must leave the identified payment exactly as it was:
	 * the guard throws before the assignment and before the payment is linked
	 * to the unidentified receipt, so nothing may be persisted.
	 */
	private void assertIdentifiedPaymentUnchanged() {
		MPayment identified = new MPayment(getCtx(), identifiedPaymentId, getTrxName());
		assertEquals(identifiedPaymentId, identified.getC_Payment_ID(),
				"identified payment fixture must still exist");
		assertEquals(0, identified.getCollectingAgent_ID(),
				"a rejected process must not persist any collecting agent");
		assertEquals(0, identified.getRef_Payment_ID(),
				"a rejected process must not link the identified payment to the unidentified receipt");
		assertFalse(identified.isReconciled(),
				"a rejected process must not reconcile the identified payment");
		assertEquals(MPayment.DOCSTATUS_Completed, identified.getDocStatus(),
				"a rejected process must not change the identified payment document status");
	}

	/**
	 * @param summary process summary as reported by the process info
	 * @return true when the summary is the collecting agent guard message
	 */
	private boolean isCollectingAgentNotFound(String summary) {
		if (summary == null) {
			return false;
		}
		//	SvrProcess translates the message before storing it on the process
		//	info; accept both the translated form and the raw dictionary keys
		String translated = Msg.parseTranslation(getCtx(),
				"@" + PaymentIdentifyAbstract.COLLECTINGAGENT_ID + "@ @NotFound@");
		return summary.contains(translated)
				|| (summary.contains("@" + PaymentIdentifyAbstract.COLLECTINGAGENT_ID + "@")
						&& summary.contains("@NotFound@"));
	}

	/**
	 * @param summary process summary as reported by the process info
	 */
	private void assertCollectingAgentNotFound(String summary) {
		assertTrue(isCollectingAgentNotFound(summary),
				"process must report @CollectingAgent_ID@ @NotFound@, but reported: " + summary);
	}

	/**
	 * Remove the rows created by each test. {@code CommonGWSetup} rolls the
	 * whole test transaction back, so failures here (for example a foreign key
	 * from an allocation created by the S2 run) are not a problem.
	 */
	@AfterEach
	void removeFixtureRows() {
		for (Integer paymentId : createdPaymentIds) {
			try {
				new MPayment(getCtx(), paymentId, getTrxName()).deleteEx(true, getTrxName());
			} catch (Exception e) {
				//	rolled back by CommonGWSetup
			}
		}
		createdPaymentIds.clear();
		for (Integer userId : createdUserIds) {
			try {
				new MUser(getCtx(), userId, getTrxName()).deleteEx(true, getTrxName());
			} catch (Exception e) {
				//	rolled back by CommonGWSetup
			}
		}
		createdUserIds.clear();
	}
}
