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
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Payment Print/Export model.
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: MPaySelectionCheck.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 297 ] Payment Selection must be like ADempiere Document
 *		@see https://github.com/adempiere/adempiere/issues/297
 *		<a href="https://github.com/adempiere/adempiere/issues/1089">
 * 		@see FR [ 1089 ] Current the Print Pay allows print a check in multiples times</a>
 *	@author  victor.perez , victor.perez@e-evolution.com http://www.e-evolution.com
 * 		<li> FR [ 297 ] Apply ADempiere best Pratice
 *		@see https://github.com/adempiere/adempiere/issues/297
 */
public final class MPaySelectionCheck extends X_C_PaySelectionCheck
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5890511999934551763L;

	/**
	 * 	Get Check for Payment
	 *	@param ctx context
	 *	@param paymentId id
	 *	@param trxName transaction
	 *	@return pay selection check for payment or null
	 */
	public static MPaySelectionCheck getOfPayment (Properties ctx, int paymentId, String trxName)
	{
		MPaySelectionCheck retValue = null;
		List<MPaySelectionCheck> paySelectionChecks = new Query(ctx, MPaySelectionCheck.Table_Name , MPaySelectionCheck.COLUMNNAME_C_Payment_ID + "=?", trxName)
				.setClient_ID()
				.setParameters(paymentId)
				.list();

		int count = 0;
		for (MPaySelectionCheck paySelectionCheck : paySelectionChecks)
		{
			if (retValue == null)
				retValue = paySelectionCheck;
			else if (!retValue.isProcessed() && paySelectionCheck.isProcessed())
				retValue = paySelectionCheck;
			count++;
		}
		if (count > 1)
			logger.warning ("More then one for C_Payment_ID=" + paymentId);
		return retValue;
	}	//	getOfPayment

	/**
	 * 	Create Check for Payment
	 *	@param ctx context
	 *	@param paymentId id
	 *	@param trxName transaction
	 *	@return pay selection check for payment or null
	 */
	public static MPaySelectionCheck createForPayment (Properties ctx, int paymentId, String trxName)
	{
		if (paymentId == 0)
			return null;
		MPayment payment = new MPayment (ctx, paymentId, null);
		//	Map Payment Rule <- Tender Type
		String paymentRule = PAYMENTRULE_Check;
		if (payment.getTenderType().equals(X_C_Payment.TENDERTYPE_CreditCard))
			paymentRule = PAYMENTRULE_CreditCard;
		else if (payment.getTenderType().equals(X_C_Payment.TENDERTYPE_DirectDebit))
			paymentRule = PAYMENTRULE_DirectDebit;
		else if (payment.getTenderType().equals(X_C_Payment.TENDERTYPE_DirectDeposit))
			paymentRule = PAYMENTRULE_DirectDeposit;
	//	else if (payment.getTenderType().equals(MPayment.TENDERTYPE_Check))
	//		PaymentRule = MPaySelectionCheck.PAYMENTRULE_Check;
		
		//	Create new PaySelection
		MPaySelection paySelection = new MPaySelection(ctx, 0, trxName);
		paySelection.setC_DocType_ID();
		paySelection.setAD_Org_ID(payment.getAD_Org_ID());
		paySelection.setC_BankAccount_ID (payment.getC_BankAccount_ID());
		//	FR [ 297 ]
		paySelection.setDescription(Msg.parseTranslation(ctx,
				"@CreateFrom@ @C_Payment_ID@ " + payment.getDocumentNo()));
		paySelection.setDescription(payment.getDescription());
		paySelection.setPayDate (payment.getDateTrx());
		paySelection.setTotalAmt (payment.getPayAmt());
		paySelection.setIsApproved (true);
		paySelection.saveEx();
		
		//	Create new PaySelection Check
		MPaySelectionCheck paySelectionCheck = new MPaySelectionCheck(paySelection, paymentRule);
		paySelectionCheck.setC_BPartner_ID (payment.getC_BPartner_ID());
		paySelectionCheck.setC_Payment_ID(payment.getC_Payment_ID());
		paySelectionCheck.setIsReceipt(payment.isReceipt());
		paySelectionCheck.setPayAmt (payment.getPayAmt());
		paySelectionCheck.setDiscountAmt(payment.getDiscountAmt());
		paySelectionCheck.setQty (1);
		paySelectionCheck.setDocumentNo(payment.getDocumentNo());
		paySelectionCheck.setProcessed(true);
		// afalcone - [ 1871567 ] Wrong value in Payment document
		paySelectionCheck.setIsGeneratedDraft( ! payment.isProcessed() );
		//
		paySelectionCheck.saveEx();
		
		//	Create new PaySelection Line
		MPaySelectionLine paySelectionLine = null;
		if (payment.getC_Invoice_ID() != 0)
		{
			paySelectionLine = new MPaySelectionLine (paySelection, 10, paymentRule);
			//	FR [ 297 ]
			//	For Order
			if(payment.getC_Order_ID() != 0) {
				paySelectionLine.setC_Order_ID (payment.getC_Order_ID());
			}
			//	For Charge
			if(payment.getC_Charge_ID() != 0) {
				paySelectionLine.setC_Charge_ID (payment.getC_Charge_ID());
			}
			//	For Conversion Type
			if(payment.getC_ConversionType_ID() != 0) {
				paySelectionLine.setC_ConversionType_ID(payment.getC_ConversionType_ID());
			}
			//	For Invoice
			if(payment.getC_Invoice_ID() != 0) {
				paySelectionLine.setC_Invoice_ID (payment.getC_Invoice_ID());
			}
			//	For all
			paySelectionLine.setIsPrepayment(payment.isPrepayment());
			//	
			paySelectionLine.setC_BPartner_ID(payment.getC_BPartner_ID());
			paySelectionLine.setIsSOTrx (payment.isReceipt());
			paySelectionLine.setAmtSource(payment.getPayAmt());
			paySelectionLine.setOpenAmt(payment.getPayAmt().add(payment.getDiscountAmt()));
			paySelectionLine.setPayAmt (payment.getPayAmt());
			paySelectionLine.setDiscountAmt(payment.getDiscountAmt());
			paySelectionLine.setDifferenceAmt (Env.ZERO);
			paySelectionLine.setC_PaySelectionCheck_ID(paySelectionCheck.getC_PaySelectionCheck_ID());
			paySelectionLine.setProcessed(true);
			paySelectionLine.saveEx();
		} else {
			// globalqss - CarlosRuiz - fix bug [ 1803054 ] Empty Remittance lines on payments
			// look for existance of C_PaymentAllocate records
			//	Allocate to multiple Payments based on entry
			final String paymentRuleFinal = paymentRule;
			MPaymentAllocate[] paymentAllocates = MPaymentAllocate.get(payment);
			if (paymentAllocates.length != 0) {
				AtomicInteger numInv = new AtomicInteger();
				//for (int i = 0; i < paymentAllocates.length; i++) {
				Arrays.stream(paymentAllocates)
						.filter(paymentAllocate -> paymentAllocate != null && paymentAllocate.getC_Invoice_ID() != 0)
						.forEach(paymentAllocate -> {
						MPaySelectionLine newPaySelectionLine = null;
						newPaySelectionLine = new MPaySelectionLine (paySelection, 10 * (numInv.get()+1), paymentRuleFinal);
						//	FR [ 297 ]
						//	For Order
						if(payment.getC_Order_ID() != 0) {
							newPaySelectionLine.setC_Order_ID (payment.getC_Order_ID());
						}
						//	For Charge
						if(payment.getC_Charge_ID() != 0) {
							newPaySelectionLine.setC_Charge_ID (payment.getC_Charge_ID());
						}
						//	For Conversion Type
						if(payment.getC_ConversionType_ID() != 0) {
							newPaySelectionLine.setC_ConversionType_ID(payment.getC_ConversionType_ID());
						}
						//	For Invoice
						if(payment.getC_Invoice_ID() != 0) {
							newPaySelectionLine.setC_Invoice_ID (payment.getC_Invoice_ID());
						}
						//	For all
						newPaySelectionLine.setIsPrepayment(payment.isPrepayment());
						//	
						newPaySelectionLine.setIsSOTrx (payment.isReceipt());
						newPaySelectionLine.setOpenAmt(paymentAllocate.getAmount().add(paymentAllocate.getDiscountAmt()));
						newPaySelectionLine.setPayAmt (paymentAllocate.getAmount());
						newPaySelectionLine.setDiscountAmt(paymentAllocate.getDiscountAmt());
						newPaySelectionLine.setDifferenceAmt (Env.ZERO);
						newPaySelectionLine.setC_PaySelectionCheck_ID(paySelectionCheck.getC_PaySelectionCheck_ID());
						newPaySelectionLine.setProcessed(true);
						newPaySelectionLine.saveEx();
						numInv.updateAndGet(line -> line ++);
				});
				if (numInv.get() > 0) {
					paySelectionCheck.setQty (numInv.get());
					paySelectionCheck.saveEx();
				}
			}
		}
		//	Indicate Done
		paySelection.processIt(DocAction.ACTION_Complete);
		paySelection.saveEx();
		return paySelectionCheck;
	}	//	createForPayment

	
	/**************************************************************************
	 *  Get Checks of Payment Selection
	 *
	 *  @param paySelectionId Payment Selection
	 *  @param paymentRule Payment Rule
	 *  @param startDocumentNo start document no
	 *	@param trxName transaction
	 *  @return array of checks
	 */
	static public List<MPaySelectionCheck> get (int paySelectionId,
		String paymentRule, int startDocumentNo, String trxName)
	{
		logger.fine("C_PaySelection_ID=" + paySelectionId
			+ ", PaymentRule=" +  paymentRule + ", startDocumentNo=" + startDocumentNo);
		List<MPaySelectionCheck> paySelectionChecks = new ArrayList<MPaySelectionCheck>();

		//int docNo = startDocumentNo;
		AtomicInteger docNo = new AtomicInteger(startDocumentNo);
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MPaySelectionCheck.COLUMNNAME_C_PaySelection_ID).append("=? AND ")
				   .append(MPaySelectionCheck.COLUMNNAME_PaymentRule).append("=? ")
				   .append("AND (C_PaySelectionCheck.C_Payment_ID IS NULL "
				   		+ "			OR "
				   		+ "		EXISTS(SELECT 1 FROM C_Payment p "
				   		+ "				WHERE p.C_Payment_ID = C_PaySelectionCheck.C_Payment_ID "
				   		+ "				AND p.DocStatus NOT IN('CO', 'CL'))"
				   		+ ")");

		List<MPaySelectionCheck> draftPaySelectionChecks = new Query(Env.getCtx(), MPaySelectionCheck.Table_Name , whereClause.toString(), trxName)
				.setClient_ID()
				.setParameters(paySelectionId, paymentRule)
				.list();

		draftPaySelectionChecks.stream()
				.filter(paySelectionCheck -> paySelectionCheck != null).forEach(paySelectionCheck -> {
			paySelectionCheck.setDocumentNo(String.valueOf(docNo.get()));
			docNo.updateAndGet(no -> no + 1);
			paySelectionCheck.saveEx();
			paySelectionChecks.add(paySelectionCheck);
		});
		return paySelectionChecks;
	}   //  get
	
	/**************************************************************************
	 *  Get all Checks of Payment Selection
	 *  @param ctx context
	 *  @param paySelectionId Payment Selection
	 *	@param trxName transaction
	 *  @return array of checks
	 *  FR [ 297 ]
	 */
	public static List<MPaySelectionCheck> get(Properties ctx, int paySelectionId, String trxName) {
		logger.fine("C_PaySelection_ID=" + paySelectionId);
		
		return new Query(ctx,
				I_C_PaySelectionCheck.Table_Name, 
				I_C_PaySelectionCheck.COLUMNNAME_C_PaySelection_ID + " = ?", trxName)
			.setParameters(paySelectionId)
			.list();
	}   //  get

	
	/**************************************************************************
	 * 	Confirm Print.
	 * 	Create Payments the first time 
	 * 	@param paySelectionChecks checks
	 * 	@param batch batch
	 * 	@return last Document number or 0 if nothing printed
	 */
	public static int confirmPrint (List<MPaySelectionCheck> paySelectionChecks, MPaymentBatch batch)
	{
		AtomicInteger lastDocumentNo = new AtomicInteger();
		paySelectionChecks.stream().filter(psc -> psc != null).forEach(paySelectionCheck ->
		{
			MPayment payment = new MPayment(paySelectionCheck.getCtx(), paySelectionCheck.getC_Payment_ID(), paySelectionCheck.get_TrxName());
			//	Existing Payment
			if (paySelectionCheck.getC_Payment_ID() != 0
					&& (payment.getDocStatus().equals(DocAction.STATUS_Completed)
								|| payment.getDocStatus().equals(DocAction.STATUS_Closed)))
			{
				//	Update check number
				if (paySelectionCheck.getPaymentRule().equals(PAYMENTRULE_Check))
				{
					payment.setCheckNo(paySelectionCheck.getDocumentNo());
					payment.saveEx();
				}
			} else {	//	New Payment
				I_C_PaySelection paySelection =  paySelectionCheck.getC_PaySelection();
				MDocType documentType = MDocType.get(paySelectionCheck.getCtx(), paySelection.getC_DocType_ID());
				int docTypeId = documentType.getC_DocTypePayment_ID();
				//	
				payment = new MPayment(paySelectionCheck.getCtx(), 0, paySelectionCheck.get_TrxName());
				payment.setAD_Org_ID(paySelectionCheck.getAD_Org_ID());
				if (paySelectionCheck.getPaymentRule().equals(PAYMENTRULE_Check)) {
					payment.setBankCheck (paySelectionCheck.getParent().getC_BankAccount_ID(), false, paySelectionCheck.getDocumentNo());
				} else if (paySelectionCheck.getPaymentRule().equals(PAYMENTRULE_CreditCard)) {
					payment.setTenderType(X_C_Payment.TENDERTYPE_CreditCard);
				} else if (paySelectionCheck.getPaymentRule().equals(PAYMENTRULE_DirectDeposit)
					|| paySelectionCheck.getPaymentRule().equals(PAYMENTRULE_DirectDebit)) {
					payment.setBankACH(paySelectionCheck);
				} else {
					logger.log(Level.SEVERE, "Unsupported Payment Rule=" + paySelectionCheck.getPaymentRule());
					throw  new AdempiereException("Unsupported Payment Rule=" + paySelectionCheck.getPaymentRule());
					//continue;
				}
				payment.setTrxType(X_C_Payment.TRXTYPE_CreditPayment);
				if (docTypeId > 0) {
					payment.setC_DocType_ID(docTypeId);
				}
				payment.setAmount(paySelectionCheck.getParent().getC_Currency_ID(), paySelectionCheck.getPayAmt());
				payment.setDiscountAmt(paySelectionCheck.getDiscountAmt());
				payment.setDateTrx(paySelectionCheck.getParent().getPayDate());
				payment.setDateAcct(payment.getDateTrx()); // globalqss [ 2030685 ]
				payment.setC_BPartner_ID(paySelectionCheck.getC_BPartner_ID());
				//	Link to Batch
				if (batch != null) {
					if (batch.getC_PaymentBatch_ID() == 0)
						batch.saveEx();	//	new
					payment.setC_PaymentBatch_ID(batch.getC_PaymentBatch_ID());
				}
				List<MPaySelectionLine> paySelectionLines = paySelectionCheck.getPaySelectionLinesAsList(false);
				logger.fine("confirmPrint - " + paySelectionCheck + " (#SelectionLines=" + (paySelectionLines != null? paySelectionLines.size(): 0) + ")");
				//	For bank Transfer
				if(documentType.isBankTransfer()) {
					payment.setC_Invoice_ID(-1);
					payment.setC_Order_ID(-1);
					payment.setTenderType(MPayment.TENDERTYPE_DirectDeposit);
					payment.saveEx();
					if(paySelectionLines != null) {
						for(MPaySelectionLine line : paySelectionLines) {
							if(line.getC_BankAccountTo_ID() == 0) {
								throw new AdempiereException("@C_BankAccountTo_ID@ @NotFound@");
							}
							//	For all
							MPayment receiptAccount = new MPayment(paySelectionCheck.getCtx(), 0, paySelectionCheck.get_TrxName());
							PO.copyValues(payment, receiptAccount);
							//	Set default values
							receiptAccount.setC_BankAccount_ID(line.getC_BankAccountTo_ID());
							receiptAccount.setIsReceipt(!payment.isReceipt());
							receiptAccount.setC_DocType_ID(!payment.isReceipt());
							receiptAccount.setRelatedPayment_ID(payment.getC_Payment_ID());
							receiptAccount.setTenderType(MPayment.TENDERTYPE_DirectDeposit);
							receiptAccount.saveEx();
							receiptAccount.processIt(DocAction.ACTION_Complete);
							receiptAccount.saveEx();
							payment.setRelatedPayment_ID(receiptAccount.getC_Payment_ID());
						}
					}
				} else {
					//	Link to Invoice
					if (paySelectionCheck.getQty() == 1 && paySelectionLines != null && paySelectionLines.size() == 1) {
						MPaySelectionLine paySelectionLine = paySelectionLines.get(0);
						logger.fine("Map to Invoice " + paySelectionLine);
						//
						//	FR [ 297 ]
						//	For Order
						if(paySelectionLine.getC_Order_ID() != 0) {
							payment.setC_Order_ID (paySelectionLine.getC_Order_ID());
						}
						//	For Charge
						if (paySelectionLine.getC_Charge_ID() != 0) {
							payment.setC_Charge_ID(paySelectionLine.getC_Charge_ID());
							if (paySelectionLine.getHR_Movement_ID() > 0) {
								payment.setC_Project_ID(paySelectionLine.getHRMovement().getC_Project_ID());
							}

						}
						//	For Conversion Type
						if(paySelectionLine.getC_ConversionType_ID() != 0) {
							payment.setC_ConversionType_ID(paySelectionLine.getC_ConversionType_ID());
						}
						//	For Invoice
						if(paySelectionLine.getC_Invoice_ID() != 0) {
							payment.setC_Invoice_ID (paySelectionLine.getC_Invoice_ID());
						}
						//	For all
						payment.setIsPrepayment(paySelectionLine.isPrepayment());
						//	
						payment.setDiscountAmt (paySelectionLine.getDiscountAmt());
						payment.setWriteOffAmt(paySelectionLine.getDifferenceAmt());
						BigDecimal overUnder = paySelectionLine.getOpenAmt().subtract(paySelectionLine.getPayAmt())
							.subtract(paySelectionLine.getDiscountAmt()).subtract(paySelectionLine.getDifferenceAmt());
						payment.setOverUnderAmt(overUnder);
					} else {
						payment.setDiscountAmt(Env.ZERO);
					}
				}
				payment.setWriteOffAmt(Env.ZERO);
				payment.saveEx();
				//	
				paySelectionCheck.setC_Payment_ID (payment.getC_Payment_ID());
				paySelectionCheck.saveEx();	//	Payment process needs it
				//	Should start WF
				payment.processIt(DocAction.ACTION_Complete);
				payment.saveEx();
			}	//	new Payment

			//	Get Check Document No
			try
			{
				int no = Integer.parseInt(paySelectionCheck.getDocumentNo());
				if (lastDocumentNo.get() < no)
					lastDocumentNo.set(no);
			}
			catch (NumberFormatException ex)
			{
				logger.log(Level.SEVERE, "DocumentNo=" + paySelectionCheck.getDocumentNo(), ex);
			}
			paySelectionCheck.setIsPrinted(true);
			paySelectionCheck.setProcessed(true);
			paySelectionCheck.saveEx();
		});	//	all checks

		logger.fine("Last Document No = " + lastDocumentNo.get());
		return lastDocumentNo.get();
	}	//	confirmPrint

	/** Logger								*/
	static private CLogger logger = CLogger.getCLogger (MPaySelectionCheck.class);

	/**************************************************************************
	 *	Constructor
	 *  @param ctx context
	 *  @param paySelectionCheckId C_PaySelectionCheck_ID
	 *	@param trxName transaction
	 */
	public MPaySelectionCheck (Properties ctx, int paySelectionCheckId, String trxName)
	{
		super(ctx, paySelectionCheckId, trxName);
		if (paySelectionCheckId == 0)
		{
		//	setC_PaySelection_ID (0);
		//	setC_BPartner_ID (0);
		//	setPaymentRule (null);
			setPayAmt (Env.ZERO);
			setDiscountAmt(Env.ZERO);
			setIsPrinted (false);
			setIsReceipt (false);
			setQty (0);
		}
	}   //  MPaySelectionCheck
	
	/**
	 *	Load Constructor
	 *  @param ctx context
	 *  @param rs result set
	 *	@param trxName transaction
	 */
	public MPaySelectionCheck(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}   //  MPaySelectionCheck
	
	/**
	 * 	Create from Line
	 *	@param paySelectionLine payment selection
	 *	@param paymentRule tender type
	 *	FR [ 297 ] Change by Tender Type
	 */
	public MPaySelectionCheck (MPaySelectionLine paySelectionLine, String paymentRule)
	{
		this (paySelectionLine.getCtx(), 0, paySelectionLine.get_TrxName());
		setClientOrg(paySelectionLine);
		setC_PaySelection_ID (paySelectionLine.getC_PaySelection_ID());
		//	FR [ 297 ] BP is now in line
		int partnerId = paySelectionLine.getC_BPartner_ID();
		setC_BPartner_ID (partnerId);
		//	Valid Tender Type
		if(paymentRule == null) {
			paymentRule = paySelectionLine.getPaymentRule();
		}
		//	Add default account
		if(paySelectionLine.getC_BP_BankAccount_ID() != 0) {
			setC_BP_BankAccount_ID(paySelectionLine.getC_BP_BankAccount_ID());
		} 
		else if (X_C_Order.PAYMENTRULE_DirectDebit.equals(paymentRule))
		{
			List<MBPBankAccount> partnerBankAccounts = MBPBankAccount.getByPartner(paySelectionLine.getCtx(), partnerId);
			partnerBankAccounts.stream()
					.filter(partnerBankAccount -> partnerBankAccount != null && partnerBankAccount.isDirectDebit())
					.findFirst()
					.ifPresent( partnerBankAccount -> setC_BP_BankAccount_ID(partnerBankAccount.getC_BP_BankAccount_ID()));
		}
		else if (X_C_Order.PAYMENTRULE_DirectDeposit.equals(paymentRule))
		{
			List<MBPBankAccount> partnerBankAccounts = MBPBankAccount.getByPartner(paySelectionLine.getCtx(), partnerId);
			partnerBankAccounts.stream()
					.filter(partnerBankAccount -> partnerBankAccount != null && partnerBankAccount.isDirectDeposit())
					.findFirst()
					.ifPresent( partnerBankAccount -> setC_BP_BankAccount_ID(partnerBankAccount.getC_BP_BankAccount_ID()));
		}
		setPaymentRule (paymentRule);
		//
		setIsReceipt(paySelectionLine.isSOTrx());
		setPayAmt (paySelectionLine.getPayAmt());
		setDiscountAmt(paySelectionLine.getDiscountAmt());
		setQty (1);
	}	//	MPaySelectionCheck

	/**
	 * 	Create from Pay Selection
	 *	@param paySelection payment selection
	 *	@param paymentRule payment rule
	 */
	public MPaySelectionCheck (MPaySelection paySelection, String paymentRule)
	{
		this (paySelection.getCtx(), 0, paySelection.get_TrxName());
		setClientOrg(paySelection);
		setC_PaySelection_ID (paySelection.getC_PaySelection_ID());
		setPaymentRule (paymentRule);
	}	//	MPaySelectionCheck
	
	
	/**	Parent					*/
	private MPaySelection			m_parent = null;
	/**	Payment Selection lines of this check	*/
	private List<MPaySelectionLine>		paySelectionLines = null;

	
	/**
	 * 	Add Payment Selection Line
	 *	@param paySelectionLine line
	 */
	public void addLine (MPaySelectionLine paySelectionLine)
	{
		//	FR [ 297 ] Change to Message
		if (getC_BPartner_ID() != paySelectionLine.getC_BPartner_ID())
			throw new IllegalArgumentException("@BPartnerDiff@");
		//
		if (isReceipt() == paySelectionLine.isSOTrx())
		{
			setPayAmt (getPayAmt().add(paySelectionLine.getPayAmt()));
			setDiscountAmt(getDiscountAmt().add(paySelectionLine.getDiscountAmt()));
		}
		else
		{
			setPayAmt (getPayAmt().subtract(paySelectionLine.getPayAmt()));
			setDiscountAmt(getDiscountAmt().subtract(paySelectionLine.getDiscountAmt()));
		}
		setQty (getQty()+1);
	}	//	addLine
	
	/**
	 * 	Get Parent
	 *	@return parent
	 */
	public MPaySelection getParent()
	{
		if (m_parent == null)
			m_parent = new MPaySelection (getCtx(), getC_PaySelection_ID(), get_TrxName());
		return m_parent;
	}	//	getParent

	/**
	 * 	Is this a valid Prepared Payment
	 *	@return true if valid
	 */
	public boolean isValid()
	{
		if (getC_BP_BankAccount_ID() != 0)
			return true;
		return !isDirect();
	}	//	isValid
	
	/**
	 * 	Is this a direct Debit or Deposit
	 *	@return true if direct
	 */
	public boolean isDirect()
	{
		return (X_C_Order.PAYMENTRULE_DirectDeposit.equals(getPaymentRule())
			|| X_C_Order.PAYMENTRULE_DirectDebit.equals(getPaymentRule()));
	}	//	isDirect
	
	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MPaymentCheck[");
		sb.append(get_ID()).append("-").append(getDocumentNo())
			.append("-").append(getPayAmt())
			.append(",PaymetRule=").append(getPaymentRule())
			.append(",Qty=").append(getQty())
			.append("]");
		return sb.toString();
	}	//	toString

	public MPaySelectionLine[] getPaySelectionLines (boolean reQuery)
	{
		List<MPaySelectionLine> paySelectionLinesAsList =  getPaySelectionLinesAsList(reQuery);
		MPaySelectionLine[] paySelectionLine = new MPaySelectionLine[paySelectionLinesAsList.size()];
		return paySelectionLinesAsList.toArray(paySelectionLine); // fill the array
	}

	/**
	 * 	Get Payment Selection Lines of this check
	 *	@param reQuery reQuery
	 * 	@return array of peyment selection lines
	 */
	public List<MPaySelectionLine> getPaySelectionLinesAsList (boolean reQuery)
	{
		if (paySelectionLines != null && !reQuery) {
			paySelectionLines.stream().filter(paySelectionLine -> paySelectionLine != null).forEach(paySelectionLine -> paySelectionLine.set_TrxName(get_TrxName()));
			return paySelectionLines;
		}

		paySelectionLines = new Query(getCtx(), MPaySelectionLine.Table_Name,  MPaySelectionLine.COLUMNNAME_C_PaySelectionCheck_ID + "=?", get_TrxName())
				.setClient_ID()
				.setOrderBy(MPaySelectionLine.COLUMNNAME_Line)
				.setParameters(getC_PaySelectionCheck_ID())
				.list();
		return paySelectionLines;
	}	//	getPaySelectionLines

	
	/**
	 *	Delete Payment Selection when generated as Draft (Print Preview) 
	 *	@param ctx context
	 *	@param C_Payment_ID id
	 *	@param trxName transaction
	 * @return
	 */
	public static boolean deleteGeneratedDraft(Properties ctx, int C_Payment_ID, String trxName)
	{
		
		MPaySelectionCheck paySelectionCheck = MPaySelectionCheck.getOfPayment (ctx, C_Payment_ID, trxName);
		
		if (paySelectionCheck != null && paySelectionCheck.isGeneratedDraft())
		{
			MPaySelection paySelection = new MPaySelection(ctx, paySelectionCheck.getC_PaySelection_ID(),trxName);
			List<MPaySelectionLine> paySelectionLines = paySelection.getLines(true);

			paySelectionLines.stream()
					.filter(paySelectionLine -> paySelectionLine != null)
					.forEach(paySelectionLine -> paySelectionLine.deleteEx(true));

			// Delete Pay Selection lines
			/*for (MPaySelectionLine paySelectionLine : paySelectionLines)
			{
				paySelectionLine.deleteEx(true);
			}*/

			// Delete Pay Selection Check
			if (!paySelectionCheck.delete(true, trxName))
				return false;
			
			// Delete Pay Selection
			if (!paySelection.delete(true, trxName))
				return false;
		}
	return true;	
	}
	
}   //  MPaySelectionCheck
