/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *                                                                            *
 *  @author Michael McKay                                                     * 
 *  	<li>BF3441324  - Partially paid invoice does not appear in payment    *
 *                       selection                                            *
 *****************************************************************************/
package org.compiere.model;

import org.adempiere.core.domains.models.I_HR_Movement;
import org.adempiere.core.domains.models.X_C_PaySelectionLine;
import org.adempiere.core.domains.models.X_HR_Employee;
import org.adempiere.core.domains.models.X_HR_Movement;
import org.adempiere.core.domains.models.X_HR_Payroll;
import org.adempiere.core.domains.models.X_HR_Process;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import org.compiere.util.Env;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Properties;


/**
 *	Payment Selection Line Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MPaySelectionLine.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 297 ] Payment Selection must be like ADempiere Document
 *		@see https://github.com/adempiere/adempiere/issues/297
 *	@author  victor.perez , victor.perez@e-evolution.com http://www.e-evolution.com
 * 		<li> FR [ 297 ] Apply ADempiere best Pratice
 *		@see https://github.com/adempiere/adempiere/issues/297
 */
public class MPaySelectionLine extends X_C_PaySelectionLine
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3486055138810301789L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param payselectionlineId id
	 *	@param trxName transaction
	 */
	public MPaySelectionLine (Properties ctx, int payselectionlineId, String trxName)
	{
		super(ctx, payselectionlineId, trxName);
		if (payselectionlineId == 0)
		{
		//	setC_PaySelection_ID (0);
		//	setPaymentRule (null);	// S
		//	setLine (0);	// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM C_PaySelectionLine WHERE C_PaySelection_ID=@C_PaySelection_ID@
		//	setC_Invoice_ID (0);
			setIsSOTrx (false);
			setOpenAmt(Env.ZERO);
			setPayAmt (Env.ZERO);
			setDiscountAmt(Env.ZERO);
			setDifferenceAmt (Env.ZERO);
			setIsManual (false);
		}
	}	//	MPaySelectionLine

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MPaySelectionLine(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MPaySelectionLine

	/**
	 * 	Parent Constructor
	 *	@param paySelection parent
	 *	@param Line line
	 *	@param paymentRule payment rule
	 */
	public MPaySelectionLine (MPaySelection paySelection, int Line, String paymentRule)
	{
		this (paySelection.getCtx(), 0, paySelection.get_TrxName());
		setClientOrg(paySelection);
		setC_PaySelection_ID(paySelection.getC_PaySelection_ID());
		setLine(Line);
		//	FR [ 297 ]
		if(paymentRule != null)
			setPaymentRule(paymentRule);
	}	//	MPaySelectionLine

	/**	Invoice					*/
	private MInvoice invoice = null;
	/**	Order					*/
	private MOrder order = null;
	/**	HR Movement				*/
	private X_HR_Movement movement = null;
	/**	Parent					*/
	private MPaySelection parent = null;
	
	/**
	 * 	Set Invoice Info
	 *	@param invoiceId invoice
	 *  @param invoicepayscheduleId invoice pay schedule
	 *	@param isSOTrx sales trx
	 *	@param payAmt payment
	 *	@param openAmt open
	 *	@param discountAmt discount
	 */
	public void setInvoice (int invoiceId, int invoicepayscheduleId, boolean isSOTrx,
		BigDecimal openAmt, BigDecimal payAmt, BigDecimal discountAmt)
	{
		setC_Invoice_ID (invoiceId);
		set_ValueOfColumn("C_InvoicePaySchedule_ID",invoicepayscheduleId);
		setIsSOTrx(isSOTrx);
		MInvoice invoice = new MInvoice(getCtx(), invoiceId, get_TrxName());
		setC_BPartner_ID(invoice.getC_BPartner_ID());
		setOpenAmt(openAmt);
		setPayAmt (payAmt);
		if(invoice.getC_ConversionType_ID() > 0) {
			setC_ConversionType_ID(invoice.getC_ConversionType_ID());
		}
		setDiscountAmt(discountAmt);
		setDifferenceAmt(openAmt.subtract(payAmt).subtract(discountAmt));
		setDescription(invoice.getDescription());
		if(getPaymentRule() == null && invoice.getPaymentRule() != null)
			setPaymentRule(invoice.getPaymentRule());

		//	Only a direct deposit or a direct debit pays through a partner bank account, and
		//	then only through one whose IsACH flag and BPBankAcctUse purpose serve that tender.
		setC_BP_BankAccount_ID(payableBankAccountId(getPaymentRule(), invoice.getC_BPartner_ID()));

	}	//	setInvoive	
	/**
	 *	Account the payment rule may pay through, or 0 when none may.
	 *
	 *	The rule decides, with the predicate the product already has on MBPBankAccount:
	 *	isDirectDeposit and isDirectDebit. Only a direct deposit and a direct debit are paid
	 *	through a bank account, so any other rule takes none, and a payroll account is only
	 *	used by a payroll line. This is the same rule MPaySelectionCheck applies under its own
	 *	"Add default account" comment; applying it here too is what keeps the account written
	 *	onto the grid row one that the generated payment would accept, instead of the first
	 *	active account whatever its IsACH flag or its BPBankAcctUse purpose.
	 *
	 *	The candidate, when one is given, is kept only if it really serves the partner and the
	 *	rule. Otherwise it is re-resolved rather than trusted: MPaySelectionCheck used to take
	 *	any non zero account from the line and isValid then accepted it, so an account set by a
	 *	previous document, a stale one, or a manually typed one reached the payment unfiltered.
	 *
	 *	Among the eligible accounts the lowest id wins: MBPBankAccount.getByPartner sets no
	 *	ORDER BY, so without an explicit tie break the same input could pay a different account
	 *	every time.
	 *
	 *	@param ctx context
	 *	@param partnerId partner to pay
	 *	@param paymentRule payment rule of the document being paid
	 *	@param isPayrollLine true when the line is a payroll line (HR_Movement_ID set)
	 *	@param candidateId account already on the line, 0 when there is none
	 *	@param trxName transaction
	 *	@return account to pay through, 0 when the rule pays through none
	 */
	public static int payableBankAccountId(Properties ctx, int partnerId, String paymentRule,
			boolean isPayrollLine, int candidateId, String trxName)
	 {
		boolean isDirectDeposit = PAYMENTRULE_DirectDeposit.equals(paymentRule);
		boolean isDirectDebit = PAYMENTRULE_DirectDebit.equals(paymentRule);
		//	A check, a wire outside ACH, cash, credit card, on credit or a mixed tender is not
		//	paid through a partner bank account.
		if (!isDirectDeposit && !isDirectDebit)
			return 0;
		if (partnerId <= 0)
			return 0;

		if (candidateId > 0) {
			MBPBankAccount candidate = new MBPBankAccount(ctx, candidateId, trxName);
			if (candidate.getC_BPartner_ID() == partnerId
					&& candidate.isActive()
					&& servesRule(candidate, isDirectDeposit, isDirectDebit)
					&& (!candidate.isPayrollAccount() || isPayrollLine))
				return candidateId;
		}

		int selected = 0;
		for (MBPBankAccount bank : MBPBankAccount.getByPartner(ctx, partnerId, null, trxName)) {
			if (bank == null || bank.getC_BP_BankAccount_ID() <= 0)
				continue;
			if (!servesRule(bank, isDirectDeposit, isDirectDebit))
				continue;
			if (bank.isPayrollAccount() && !isPayrollLine)
				continue;
			if (selected == 0 || bank.getC_BP_BankAccount_ID() < selected)
				selected = bank.getC_BP_BankAccount_ID();
		}
		return selected;
	}	//	payableBankAccountId

	/**
	 *	True when the account serves the direct rule asked for.
	 *	@param account bank account
	 *	@param isDirectDeposit the tender is a direct deposit
	 *	@param isDirectDebit the tender is a direct debit
	 *	@return true when the account may be paid through that tender
	 */
	private static boolean servesRule(MBPBankAccount account,
			boolean isDirectDeposit, boolean isDirectDebit)
	 {
		if (isDirectDeposit)
			return account.isDirectDeposit();
		if (isDirectDebit)
			return account.isDirectDebit();
		return false;
	}	//	servesRule

	/**
	 *	Account the payment rule of this line may pay through, keeping an account already set.
	 *	@return account id, 0 when the rule pays through none
	 */
	private int payableBankAccountId(String paymentRule, int partnerId) {
		return payableBankAccountId(getCtx(), partnerId, paymentRule,
				getHR_Movement_ID() > 0, getC_BP_BankAccount_ID(), get_TrxName());
	}	//	payableBankAccountId

	/**
	 * Set Invoice Info
	 * @param invoiceId
	 * @param invoicePayScheduleId
	 * @param amtSource
	 * @param openAmt
	 * @param payAmt
	 * @param discountAmt
	 */
	public void setInvoice(int invoiceId, int invoicePayScheduleId, BigDecimal amtSource,
			BigDecimal openAmt, BigDecimal payAmt, BigDecimal discountAmt) {
		MInvoice invoice = new MInvoice(getCtx(), invoiceId, get_TrxName());
		setC_Invoice_ID (invoiceId);
		setIsSOTrx(invoice.isSOTrx());
		setC_BPartner_ID(invoice.getC_BPartner_ID());
		//	Set Payment Rule
		if(getPaymentRule() == null
				&& invoice.getPaymentRule() != null)
			setPaymentRule(invoice.getPaymentRule());
		setAmtSource(amtSource);
		setOpenAmt(openAmt);
		setPayAmt (payAmt);
		if(invoice.getC_ConversionType_ID() > 0) {
			setC_ConversionType_ID(invoice.getC_ConversionType_ID());
		}
		setDiscountAmt(discountAmt);
		setDifferenceAmt(openAmt.subtract(payAmt).subtract(discountAmt));
		setDescription(invoice.getDescription());
		//	Resolved in the current transaction, so an account saved earlier in the same
		//	transaction is visible, and on every call: an account that cannot serve this
		//	payment rule, or that belongs to the partner of a previous document, is cleared
		//	rather than carried into the generated payment.
		setC_BP_BankAccount_ID(payableBankAccountId(getPaymentRule(), invoice.getC_BPartner_ID()));
	}	//	setInvoice

	/**
	 * Set Order Info
	 * @param orderId
	 * @param amtSource
	 * @param openAmt
	 * @param payAmt
	 * @param discountAmt
	 */
	public void setOrder(int orderId, BigDecimal amtSource,
			BigDecimal openAmt, BigDecimal payAmt, BigDecimal discountAmt) {
		setC_Order_ID(orderId);
		MOrder order = new MOrder(getCtx(), orderId, get_TrxName());
		setIsSOTrx(order.isSOTrx());
		setC_BPartner_ID(order.getC_BPartner_ID());
		setIsPrepayment(true);
		//	Set Payment Rule
		if(getPaymentRule() == null
				&& order.getPaymentRule() != null)
			setPaymentRule(order.getPaymentRule());
		//	
		setAmtSource(amtSource);
		setOpenAmt(openAmt);
		setPayAmt (payAmt);
		if(order.getC_ConversionType_ID() > 0) {
			setC_ConversionType_ID(order.getC_ConversionType_ID());
		}
		setDiscountAmt(discountAmt);
		setDifferenceAmt(openAmt.subtract(payAmt).subtract(discountAmt));
		setDescription(order.getDescription());
		//	The partner comes from the order, so any account already on the line belongs to
		//	the previous document. It is kept only when it serves this order partner and this
		//	payment rule, and the lookup runs in the current transaction so an account saved
		//	earlier in the same transaction is seen.
		setC_BP_BankAccount_ID(payableBankAccountId(getPaymentRule(), order.getC_BPartner_ID()));
	}	//	setOrder
	
	/**
	 * Based on movement
	 * @param movement
	 * @param sourceAmount
	 * @param convertedAmount
	 */
	public void setHRMovement(I_HR_Movement movement, BigDecimal sourceAmount, BigDecimal convertedAmount) {
		X_HR_Process payrollProcess = new X_HR_Process(getCtx(), movement.getHR_Process_ID(), get_TrxName());
		setHRMovement(movement, payrollProcess.getC_ConversionType_ID(), sourceAmount, convertedAmount);
	}
	
	/**
	 * Set Payroll Movement Info
	 * @param movement
	 * @param conversionTypeId
	 * @param sourceAmount
	 * @param convertedAmount
	 */
	public void setHRMovement(I_HR_Movement movement, int conversionTypeId, BigDecimal sourceAmount, BigDecimal convertedAmount) {
		setHR_Movement_ID(movement.getHR_Movement_ID());
		setC_BPartner_ID(movement.getC_BPartner_ID());
		//	Set Payment Rule
		X_HR_Employee employee = new X_HR_Employee(getCtx(), movement.getHR_Employee_ID(), get_TrxName());
		if(employee != null 
				&& employee.getPaymentRule() != null) {
			setPaymentRule(employee.getPaymentRule());
		}
		//	From Payroll
		if(getPaymentRule() == null) {
			X_HR_Payroll payroll = new X_HR_Payroll(getCtx(), movement.getHR_Payroll_ID(), get_TableName());
			if(payroll.getPaymentRule() != null) {
				setPaymentRule(payroll.getPaymentRule());
			}
		}
		//	From BPartner
		if(getPaymentRule() == null) {
			MBPartner partner = MBPartner.get(getCtx(), movement.getC_BPartner_ID());
			if(partner.getPaymentRulePO() != null) {
				setPaymentRule(partner.getPaymentRulePO());
			}
		}
		//	Default
		if(getPaymentRule() == null) {
			setPaymentRule(X_C_PaySelectionLine.PAYMENTRULE_Check);
		}
		//	Get Conversion Type
		setIsSOTrx(false);
		setAmtSource(sourceAmount);
		setOpenAmt(Optional.ofNullable(convertedAmount).orElseGet(() -> sourceAmount));
		setPayAmt(Optional.ofNullable(convertedAmount).orElseGet(() -> sourceAmount));
		setC_ConversionType_ID(conversionTypeId);
		setDiscountAmt(Env.ZERO);
		setDifferenceAmt(Env.ZERO);
	}	//	setHRMovement
	
	/**
	 * Set Payment Selection Line from parent line
	 * @param payselectionlineId
	 * @param payAmt
	 * @param discountAmt
	 */
	public void setPaySelectionLineParent(int payselectionlineId, BigDecimal payAmt, BigDecimal discountAmt) {
		setC_PaySelectionLine_Parent_ID(payselectionlineId);
		//	Get values
		MPaySelectionLine paySelectionLine = new MPaySelectionLine(getCtx(), payselectionlineId, get_TrxName());
		//	Set Reference
		setC_BPartner_ID(paySelectionLine.getC_BPartner_ID());
		setC_BP_BankAccount_ID(paySelectionLine.getC_BP_BankAccount_ID());
		setC_Order_ID(paySelectionLine.getC_Order_ID());
		setC_Invoice_ID(paySelectionLine.getC_Invoice_ID());
		setC_InvoicePaySchedule_ID(paySelectionLine.getC_InvoicePaySchedule_ID());
		setHR_Movement_ID(paySelectionLine.getHR_Movement_ID());
		setC_Charge_ID(paySelectionLine.getC_Charge_ID());
		setC_ConversionType_ID(paySelectionLine.getC_ConversionType_ID());
		setC_Conversion_Rate_ID(paySelectionLine.getC_Conversion_Rate_ID());
		setIsPrepayment(paySelectionLine.isPrepayment());
		//	Set Payment Rule
		setPaymentRule(paySelectionLine.getPaymentRule());
		setIsSOTrx(paySelectionLine.isSOTrx());
		setAmtSource(paySelectionLine.getAmtSource());
		setOpenAmt(paySelectionLine.getOpenAmt());
		setPayAmt(payAmt);
		setDiscountAmt(discountAmt);
		setDifferenceAmt(paySelectionLine.getOpenAmt().subtract(payAmt).subtract(discountAmt));
	}	//	setPaySelectionLineParent
	
	/**
	 * Set Charge Info
	 * @param chargeId
	 * @param partnerId
	 * @param paymentRule
	 * @param payAmt
	 * @param isSOTrx
	 */
	public void setCharge(int chargeId, int partnerId,
			String paymentRule, BigDecimal payAmt, boolean isSOTrx) {
		setC_Charge_ID(chargeId);
		MCharge charge = MCharge.get(getCtx(), chargeId);
		//	Set BPartner
		if(partnerId == 0)
			partnerId = charge.getC_BPartner_ID();
		//	
		setC_BPartner_ID(partnerId);
		setPaymentRule(paymentRule);
		setIsSOTrx(isSOTrx);
		setAmtSource(payAmt);
		setOpenAmt(payAmt);
		setPayAmt (payAmt);
		setDiscountAmt(Env.ZERO);
		setDifferenceAmt(Env.ZERO);
	}	//	setCharge
	
	/**
	 * 	Get Invoice
	 *	@return invoice
	 */
	public MInvoice getInvoice()
	{
		if (invoice == null)
			invoice = new MInvoice (getCtx(), getC_Invoice_ID(), get_TrxName());
		return invoice;
	}	//	getInvoice
	
	/**
	 * 	Get Order
	 *	@return order
	 *	FR [ 297 ]
	 */
	public MOrder getOrder() {
		if (order == null)
			order = new MOrder (getCtx(), getC_Order_ID(), get_TrxName());
		return order;
	}	//	getOrder
	
	/**
	 * Get Movement of payroll
	 * FR [ 297 ]
	 * @return
	 */
	public X_HR_Movement getHRMovement() {
		if (movement == null) {
			movement = new X_HR_Movement(getCtx(), getHR_Movement_ID(), get_TrxName());
		}
		return movement;
	}	//	getHRMovement
	
	/**
	 * Get Parent
	 * @return
	 */
	public MPaySelection getParent() {
		if(parent == null)
			parent = new MPaySelection(getCtx(), getC_PaySelection_ID(), get_TrxName());
		return parent;
	}

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		validateBPartner();
		//	Set values from type
		setDocumentValues();
		//	FR [ 297 ]
		if(getC_BPartner_ID() == 0)
			throw new AdempiereException("@C_BPartner_ID@ @NotFound@");

		if (getC_Invoice_ID() > 0 && getOpenAmt().subtract(getPayAmt()).subtract(getDiscountAmt()).signum() < 0 )
			throw new AdempiereException("@PayAmt@ > @C_Invoice_ID@ @Amount@ @OpenAmt@ ");

		return true;
	}	//	beforeSave
	
	/**
	 * Set values from references
	 * FR [ 297 ]
	 */
	private void setDocumentValues() {
		boolean resetConversion = false;
		BigDecimal difference = Env.ZERO;
		BigDecimal discountAmt = getDiscountAmt();
		BigDecimal openAmt = getOpenAmt();
		//	Only set difference amount when is from a document
		if((is_ValueChanged("C_Invoice_ID")
				|| is_ValueChanged("C_Order_ID")
				|| is_ValueChanged("HR_Movement_ID"))
			&& (getC_Invoice_ID() != 0
				|| getC_Order_ID() != 0
				|| getHR_Movement_ID() != 0)) {
			difference = openAmt.subtract(getPayAmt()).subtract(discountAmt);
			//	Set pre-payment
			setIsPrepayment(getC_Order_ID() != 0);
			resetConversion = getHR_Movement_ID() != 0;
			//	For same currency
			if(getC_Order_ID() != 0) {
				resetConversion = getOrder().getC_Currency_ID() == getParent().getC_Currency_ID();
				setC_BPartner_ID(getOrder().getC_BPartner_ID());
			} else if(getC_Invoice_ID() != 0) {
				resetConversion = getInvoice().getC_Currency_ID() == getParent().getC_Currency_ID();
				setC_BPartner_ID(getInvoice().getC_BPartner_ID());
			} else if(getHR_Movement_ID() != 0
					&& getC_Charge_ID() == 0) {
				X_HR_Movement movement = new X_HR_Movement(getCtx(), getHR_Movement_ID(), get_TrxName());
				X_HR_Payroll payroll = new X_HR_Payroll(getCtx(), movement.getHR_Payroll_ID(), get_TrxName());
				//	MHRPayroll payroll = MHRPayroll.get(getCtx(), movement.getHR_Payroll_ID());
				if(payroll.getHR_Payroll_ID() == 0)
					throw new AdempiereException("@HR_Payroll_ID@ @NotFound@");
				if(payroll.getC_Charge_ID() == 0)
					throw new AdempiereException("@C_Charge_ID@ @NotFound@");
				//	Set charge
				setC_Charge_ID(payroll.getC_Charge_ID());
				setC_BPartner_ID(movement.getC_BPartner_ID());
			}
		} else if(is_ValueChanged("C_Charge_ID") 
				&& getC_Charge_ID() != 0){
			discountAmt = Env.ZERO;
			openAmt = Env.ZERO;
			setIsPrepayment(false);
		} else if(getC_Invoice_ID() == 0 
				&& getC_Order_ID() == 0
				&& getHR_Movement_ID() == 0) {
			discountAmt = Env.ZERO;
			openAmt = Env.ZERO;
		}
		//	For changes
		setDifferenceAmt(difference);
		setOpenAmt(openAmt);
		//	Set null when is not from document
		if(resetConversion) {
			setC_ConversionType_ID(0);
			setC_Conversion_Rate_ID(0);
		}
		//	Set default payment rule
		if(is_ValueChanged("PaymentRule")) {
			//	For payment rule unsupported
			if (!getPaymentRule().equals(PAYMENTRULE_Check)
					&& !getPaymentRule().equals(PAYMENTRULE_CreditCard) 
					&& !getPaymentRule().equals(PAYMENTRULE_DirectDeposit) 
					&& !getPaymentRule().equals(PAYMENTRULE_DirectDebit)) {
				//	Set
				setPaymentRule(PAYMENTRULE_DirectDeposit);
			}
		}
	}
	
	/**
	 * Validate if the BP is the same for Documet and record
	 */
	private void validateBPartner() {
		//	Validate when BP is changed
		if(is_ValueChanged("C_BPartner_ID")) {
			int partnerId = getC_BPartner_ID();
			//	For invoice
			if(getC_Invoice_ID() != 0) {
				partnerId = getInvoice().getC_BPartner_ID();
			} else if(getC_Order_ID() != 0) {
				partnerId = getC_Order().getC_BPartner_ID();
			} else if(getHR_Movement_ID() != 0) {
				partnerId = getHRMovement().getC_BPartner_ID();
			}
			//	Validate BP
			if(partnerId != getC_BPartner_ID()) {
				throw new AdempiereException("@BPartnerDiff@");	//	TODO translate it "business partner different"
			}
		} else if(getC_Invoice_ID() != 0) {	//	else then set from document
			setC_BPartner_ID(getInvoice().getC_BPartner_ID());
		} else if(getC_Order_ID() != 0) {
			setC_BPartner_ID(getOrder().getC_BPartner_ID());
		} else if(getHR_Movement_ID() != 0) {
			setC_BPartner_ID(getHRMovement().getC_BPartner_ID());
		}
	}
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		setHeader();
		return success;
	}	//	afterSave
	
	/**
	 * 	After Delete
	 *	@param success success
	 *	@return sucess
	 */
	protected boolean afterDelete (boolean success)
	{
		setHeader();
		return success;
	}	//	afterDelete
	
	/**
	 * 	Recalculate Header Sum
	 */
	private void setHeader()
	{
		//	Update Header
		String sql = "UPDATE C_PaySelection ps "
			+ "SET TotalAmt = (SELECT COALESCE(SUM(psl.PayAmt),0) "
				+ "FROM C_PaySelectionLine psl "
				+ "WHERE ps.C_PaySelection_ID=psl.C_PaySelection_ID AND psl.IsActive='Y') "
			+ "WHERE C_PaySelection_ID=" + getC_PaySelection_ID();
		DB.executeUpdate(sql, get_TrxName());
	}	//	setHeader
	
	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MPaySelectionLine[");
		sb.append(get_ID()).append(",C_Invoice_ID=").append(getC_Invoice_ID())
			.append(",PayAmt=").append(getPayAmt())
			.append(",DifferenceAmt=").append(getDifferenceAmt())
			.append("]");
		return sb.toString();
	}	//	toString
	
	/**
	 * Verify if is paid
	 * @return
	 */
	public boolean isPaid() {
		int noGeneratedPayments = DB.getSQLValue(get_TrxName(), 
				"SELECT 1 "
				+ "FROM C_PaySelectionCheck psc "
				+ "WHERE psc.C_PaySelectionCheck_ID = ? "
				+ "AND EXISTS(SELECT 1 FROM C_Payment p "
				+ "			WHERE p.C_Payment_ID = psc.C_Payment_ID"
				+ "			AND p.DocStatus NOT IN('VO', 'RE'))", getC_PaySelectionCheck_ID());
		//	Valid
		return noGeneratedPayments > 0;
	}
	
	/**
	 * Verify if is used
	 * @return
	 */
	public boolean isUsed() {
		int noGeneratedSelections = DB.getSQLValue(get_TrxName(), 
				"SELECT 1 "
				+ "FROM C_PaySelection cps "
				+ "INNER JOIN C_PaySelectionLine cpsl ON(cpsl.C_PaySelection_ID = cps.C_PaySelection_ID)"
				+ "WHERE cps.DocStatus NOT IN('VO', 'RE')"
				+ "AND cpsl.C_PaySelectionLine_Parent_ID = ?", getC_PaySelectionLine_ID());
		//	Valid
		return noGeneratedSelections > 0;
	}
	
	/**
	 * Validate conversion
	 * @return
	 */
	public boolean isValidConversion() {
		//	Don't need conversion, it are in same currency
		if(getC_Invoice_ID() == 0
				&& getC_Order_ID() == 0)
			return true;
		//	
		int currencyDocumentId = 0;
		int currencyId = getC_Currency_ID();
		//	Valid currency
		if(getC_Invoice_ID() != 0) {		//	For Invoice
			currencyDocumentId = DB.getSQLValue(get_TrxName(),
					"SELECT i.C_Currency_ID FROM C_Invoice i WHERE i.C_Invoice_ID = ?", 
					getC_Invoice_ID());
		} else if(getC_Order_ID() != 0) {	//	For Order
			currencyDocumentId = DB.getSQLValue(get_TrxName(),
					"SELECT o.C_Currency_ID FROM C_Order o WHERE o.C_Order_ID = ?", 
					getC_Order_ID());
		}
		//	For same currency
		if(currencyDocumentId == currencyId)
			return true;
		//	For custom rate
		if(getC_Conversion_Rate_ID() != 0) {
			MConversionRate conversionRate = MConversionRate.get(getCtx(), getC_Conversion_Rate_ID());
			//	Valid
			if(conversionRate == null)
				return false;
			//	For when no exists conversion
			if(conversionRate.getC_Currency_ID() == currencyDocumentId
					&& conversionRate.getC_Currency_ID_To() == currencyId)
				return true;
			else 
				return false;
		}
		//	For all
		BigDecimal CurrencyRate = MConversionRate.getRate (currencyDocumentId,
					currencyId, getPayDate(), getC_ConversionType_ID(), getAD_Client_ID(),
					getAD_Org_ID());
		//	Return
		return CurrencyRate != null;
	}
	
	/**
	 * Get Parent currency
	 * @return
	 */
	private int getC_Currency_ID() {
		return getParent().getC_Currency_ID();
	}
	
	/**
	 * Get Parent payment date
	 * @return
	 */
	private Timestamp getPayDate() {
		return getParent().getPayDate();
	}
	
	@Override
	public void setPaymentRule(String paymentRule) {
		if (paymentRule == null
				|| (
				getPaymentRule() != null
						&& !getPaymentRule().equals(PAYMENTRULE_Check)
						&& !getPaymentRule().equals(PAYMENTRULE_CreditCard)
						&& !getPaymentRule().equals(PAYMENTRULE_DirectDeposit)
						&& !getPaymentRule().equals(PAYMENTRULE_DirectDebit)
		)
		) {
			//	Set from Standard
			paymentRule = PAYMENTRULE_DirectDeposit;
		}
		//	Set
		super.setPaymentRule(paymentRule);
	}

}	//	MPaySelectionLine
