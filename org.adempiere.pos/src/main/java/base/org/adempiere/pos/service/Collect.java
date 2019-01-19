/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.pos.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MBankStatement;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentAllocate;
import org.compiere.model.MPaymentProcessor;
import org.compiere.model.MPaymentValidate;
import org.compiere.model.MSysConfig;
import org.compiere.model.X_C_Payment;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;


/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author Victor Perez <victor.perez@e-evolution.com>,  eEvolution http://www.e-evolution.com
 *
 */
public class Collect {
	
	/**
	 * 
	 * *** Constructor ***
	 * @param ctx
	 * @param order
	 * @param posId
	 */
	public Collect(Properties ctx, MOrder order, int posId) {
		this(ctx, order, MPOS.get(ctx, posId));
	}
	
	/**
	 * 
	 * *** Constructor ***
	 * @param ctx
	 * @param order
	 * @param entityPOS
	 */
	public Collect(Properties ctx, MOrder order, MPOS entityPOS) {
		//	Instance Collects
		collectDetails = new ArrayList<CollectDetail>();
		//	Instance POS
		this.entityPOS = entityPOS;
		//	Set Order
		if (order != null) {
			this.order = order;
			this.partnerId = order.getC_BPartner_ID();
			this.bankAccountId = entityPOS.getC_BankAccount_ID();
			this.dateTrx = order.getDateOrdered();
			this.trxName = order.get_TrxName();
		}
	}

	public void load(Properties ctx, MOrder order, MPOS entityPOS) {
		//	Instance Collects
		collectDetails = new ArrayList<CollectDetail>();
		//	Instance POS
		this.entityPOS = entityPOS;
		//	Set Order
		if (order != null) {
			this.order = order;
			this.partnerId = order.getC_BPartner_ID();
			this.bankAccountId = entityPOS.getC_BankAccount_ID();
			this.dateTrx = order.getDateOrdered();
			this.trxName = order.get_TrxName();
		}
	}

	/**	Transaction				*/
	private String 				trxName;
	/**	Order					*/
	private MOrder 				order;
	/**	POS						*/
	private MPOS 				entityPOS;
	/**	Business Partner		*/
	private int 				partnerId;
	/**	Bank Account			*/
	private int 				bankAccountId;
	/**	Date for Collect		*/
	private Timestamp 			dateTrx;
	/**	Collects				*/
	private List<CollectDetail> collectDetails;
	/**	Credit Order			*/
	private boolean 			isCreditOrder = false;
	/**	Payment Term			*/
	private int 				paymentTermId = 0;
	/**	Error Message			*/
	private StringBuffer 		errorMsg = new StringBuffer();

	/**
	 * Add New Collect
	 * @param detail
	 * @return void
	 */
	public void addCollect(CollectDetail detail) {
		collectDetails.add(detail);
	}
	
	/**
	 * Remove a Collect  Detail
	 * @param detail
	 * @return void
	 */
	public void removeCollect(CollectDetail detail) {
		collectDetails.remove(detail);
	}
	
	/**
	 * Remove all Collect Details
	 * @param none
	 * @return void
	 */
	public void removeAllCollectDetail() {
		int lenght = collectDetails.size();
		for(int i = lenght - 1; i >= 0; i--) {
			collectDetails.remove(i);
		}
	}
	
	/**
	 * Get Payment Amount from Collect Details
	 * @return
	 * @return BigDecimal
	 */
	public BigDecimal getCollectDetailAmt() {
		BigDecimal payAmt = Env.ZERO;
		//	Get from List
		for(CollectDetail detail : collectDetails) {
			payAmt = payAmt.add(detail.getPayAmt());
		}
		//	Default Return
		return payAmt;
	}

	/**
	 * Get Balance
	 * @return
	 * @return BigDecimal
	 */
	protected BigDecimal getBalance(BigDecimal openAmt) {
		BigDecimal totalPayAmt = getCollectDetailAmt();
		if (order != null)
			return openAmt.subtract(totalPayAmt);
		return Env.ZERO;
	}
	
	/**
	 * Add Cash Collect
	 * @param m_PayAmt
	 * @return void
	 */
	public void addCash(BigDecimal m_PayAmt) {
		int position = findCash();
		CollectDetail collectDetail = null;
		if(position != -1) {
			collectDetail = collectDetails.get(position);
			collectDetail.setPayAmt(m_PayAmt);
			collectDetails.set(position, collectDetail);
			return;
		} else {
			collectDetail = CollectDetail.createCash(m_PayAmt);
			collectDetail.setDateTrx(getDateTrx());
		}
		//	Default Add Cash
		collectDetails.add(collectDetail);
	}
	
	/**
	 * Add Check Collect
	 * @param payAmt
	 * @param checkNo
	 * @param bankId
	 * @param dateTrx
	 * @return void
	 */
	public void addCheck(BigDecimal payAmt, String checkNo,
			int bankId, Timestamp dateTrx) {
		int position = findCheck(checkNo);
		CollectDetail collectDetail = null;
		if(position != -1) {
			collectDetail = collectDetails.get(position);
			collectDetail.setPayAmt(payAmt);
			collectDetails.set(position, collectDetail);
			return;
		} else {
			collectDetail = CollectDetail.createCheck(payAmt, checkNo, bankId, dateTrx);
		}
		//	Default Add
		collectDetails.add(collectDetail);
	}
	
	/**
	 * Find a Check
	 * @param checkNo
	 * @return
	 * @return int
	 */
	public int findCheck(String checkNo) {
		for(int i = 0; i < collectDetails.size(); i++) {
			CollectDetail collectDetail = collectDetails.get(i);
			if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_Check)
					&& collectDetail.getReferenceNo() != null
					&& collectDetail.getReferenceNo().equals(checkNo)) {
				return i;
			}
		}
		//	Return Position
		return -1;
	}
	
	/**
	 * Is there at least one cash payment
	 * @param none
	 * @return true if exists
	 */
	public boolean isExistCash() {
		for(CollectDetail collectDetail : collectDetails) {
			if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_Cash)) {
				return true;
			}
		}
		return false; // No Credit cash found
	}
	
	/**
	 * Is there only one Cash payment
	 * @return true if there is only one payment and it is a cash
	 */
	public boolean isExistOnlyOneCash() {
		int count = 0;
		for(CollectDetail collectDetail : collectDetails) {
			if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_Cash)) {
				count = count+1;
			}
		}
		return (count==1 && collectDetails.size()==1);
	}
	
	/**
	 * Is there a Credit Card payment
	 * @param none
	 * @return position of first credit card or -1
	 */
	public int isExistCreditCard() {
		for(int i = 0; i < collectDetails.size(); i++) {
			CollectDetail collectDetail = collectDetails.get(i);
			if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_CreditCard)) {
				return i;
			}
		}
		return -1; // No Credit card found
	}
	
	/**
	 * Is there only one Credit Card payment
	 * @param none
	 * @return true if there is only one payment and it is a credit card
	 */
	public boolean isExistOnlyOneCreditCard() {
		int count = 0;
		for(CollectDetail collectDetail : collectDetails) {
			if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_CreditCard)) {
				count = count+1;
			}
		}
		return (count==1 && collectDetails.size()==1);
	}
	
	/**
	 * Is there a Check
	 * @return position of first check or -1
	 */
	public int isExistCheck() {
		for(int i = 0; i < collectDetails.size(); i++) {
			CollectDetail collectDetail = collectDetails.get(i);
			if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_Check)) {
				return i;
			}
		}
		return -1; // No Check found
	}
	
	/**
	 * Is there only one Check payment
	 * @return true if there is only one payment and it is a check
	 */
	public boolean isExistOnlyOneCheck() {
		int count = 0;
		for(CollectDetail collectDetail : collectDetails) {
			if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_Check)) {
				count = count+1;
			}
		}
		return (count==1 && collectDetails.size()==1);
	}
	
	/**
	 * Find Cash
	 * @return
	 * @return int
	 */
	public int findCash() {
		for(int i = 0; i < collectDetails.size(); i++) {
			CollectDetail collectDetail = collectDetails.get(i);
			if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_Cash)) {
				return i;
			}
		}
		//	Return Position
		return -1;
	}

	/**
	 * Process Payment
	 * @param amount
	 * @return true if payment processed correctly; otherwise false
	 */
	public boolean payCash(BigDecimal amount, BigDecimal overUnderAmt) {

		MPayment payment = createPayment(MPayment.TENDERTYPE_Cash);
		if (payment.isCashTrx() && !MSysConfig.getBooleanValue("CASH_AS_PAYMENT", true , payment.getAD_Client_ID()))
			payment.setC_CashBook_ID(entityPOS.getC_CashBook_ID());
		else
			payment.setC_BankAccount_ID(entityPOS.getC_BankAccount_ID());

		payment.setAmount(order.getC_Currency_ID(), amount);
		payment.setC_BankAccount_ID(entityPOS.getC_BankAccount_ID());
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateTrx());
		payment.saveEx();

		payment.setOverUnderAmt(overUnderAmt);
		payment.saveEx();

		payment.setDocAction(MPayment.DOCACTION_Complete);
		payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		if (payment.processIt(MPayment.DOCACTION_Complete)){
			payment.saveEx();
			MBankStatement.addPayment(payment);
			return true;
		}
		else return false;
	} // payCash


	/**
	 * Payment with check
	 * @param amount
	 * @param accountNo
	 * @param routingNo
	 * @param checkNo
	 * @return true if payment processed correctly; otherwise false
	 */
	public boolean payCheck(BigDecimal amount, String accountNo, String routingNo, String checkNo) {
		MPayment payment = createPayment(MPayment.TENDERTYPE_Check);
		payment.setC_CashBook_ID(entityPOS.getC_CashBook_ID());
		payment.setAmount(order.getC_Currency_ID(), amount);
		payment.setC_BankAccount_ID(entityPOS.getC_BankAccount_ID());
		payment.setAccountNo(accountNo);
		payment.setRoutingNo(routingNo);
		payment.setCheckNo(checkNo);
		payment.setDescription(checkNo);
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateTrx());
		payment.saveEx();
		payment.setDocAction(MPayment.DOCACTION_Complete);
		payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		if(payment.processIt(MPayment.DOCACTION_Complete)) {
			payment.saveEx();
			MBankStatement.addPayment(payment);
			return true;
		} else { 
			return false;
		}
	} // payCheck
	
	/**
	 * 	Payment with direct debit
	 * @param amt
	 * @param routingNo
	 * @param accountCountry
	 * @param cVV
	 * @return true if payment processed correctly; otherwise false
	 */
	public boolean payDirectDebit(BigDecimal amt, String routingNo, String accountCountry, String cVV) {
		MPayment payment = createPayment(MPayment.TENDERTYPE_DirectDebit);
		payment.setC_CashBook_ID(entityPOS.getC_CashBook_ID());
		payment.setAmount(order.getC_Currency_ID(), amt);
		payment.setC_BankAccount_ID(entityPOS.getC_BankAccount_ID());
		payment.setRoutingNo(routingNo);
		payment.setA_Country(accountCountry);
		payment.setCreditCardVV(cVV);
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateTrx());
		payment.saveEx();
		payment.setDocAction(MPayment.DOCACTION_Complete);
		payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		if(payment.processIt(MPayment.DOCACTION_Complete)) {
			payment.saveEx();
			MBankStatement.addPayment(payment);
			return true;
		} else { 
			return false;
		}
	} // payDirectDebit


	/**
	 * 	Payment with credit card
	 * 
	 * @return true if payment processed correctly; otherwise false
	 * 
	 */
	public boolean payCreditCard(BigDecimal amount, String accountName, int month, int year,
			String cardNo, String cvc, String cardtype) {

		MPayment payment = createPayment(MPayment.TENDERTYPE_CreditCard);
		payment.setAmount(order.getC_Currency_ID(), amount);
		payment.setC_BankAccount_ID(entityPOS.getC_BankAccount_ID());
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateTrx());
		payment.setCreditCard(MPayment.TRXTYPE_Sales, cardtype,
				cardNo, cvc, month, year);
		payment.saveEx();
		payment.setDocAction(MPayment.DOCACTION_Complete);
		payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		if (payment.processIt(MPayment.DOCACTION_Complete)) {
			payment.saveEx();
			MBankStatement.addPayment(payment);
			return true;
		} else {
			return false;
		}
	} // payCheck


	/**
	 * 	Payment with credit note
	 * 
	 * @return true if payment processed correctly; otherwise false
	 * 
	 */
	public boolean payCreditMemo(MInvoice creditNote, BigDecimal amount) {
		int invoiceId = order.getC_Invoice_ID();
		if(invoiceId == 0)
			return false;
		MPayment payment = createPayment(MPayment.TENDERTYPE_Account);
		if(payment.getC_Invoice_ID() > 0 )
			payment.setC_Invoice_ID(0);
		if(payment.getC_Order_ID() > 0 )
			payment.setC_Order_ID(0);
		if(payment.getC_Charge_ID() > 0 )
			payment.setC_Charge_ID(0);
		
		payment.setAmount(order.getC_Currency_ID(), Env.ZERO);
		payment.setC_BankAccount_ID(entityPOS.getC_BankAccount_ID());
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateTrx());
		payment.saveEx();
		//Invoice
		MPaymentAllocate paymentAllocate = new MPaymentAllocate(Env.getCtx(), 0, trxName);
		paymentAllocate.setC_Payment_ID(payment.getC_Payment_ID());
		paymentAllocate.setC_Invoice_ID(invoiceId);
		paymentAllocate.setInvoiceAmt(amount);
		paymentAllocate.setAmount(amount);
		paymentAllocate.saveEx();
		//CreditNote
		paymentAllocate = new MPaymentAllocate(Env.getCtx(), 0, trxName);
		paymentAllocate.setC_Payment_ID(payment.getC_Payment_ID());
		paymentAllocate.setC_Invoice_ID(creditNote.getC_Invoice_ID());
		paymentAllocate.setAmount(amount.negate());
		paymentAllocate.setInvoiceAmt(amount.negate());
		paymentAllocate.saveEx();
		
		payment.setDocAction(MPayment.DOCACTION_Complete);
		payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		if(payment.processIt(MPayment.DOCACTION_Complete)) {
			payment.saveEx();
			MBankStatement.addPayment(payment);
			return true;
		} else {
			return false;
		}
	} // payCheck


	/**
	 * 	Create Payment object
	 *  Refer to invoice if there is an invoice.
	 *  Otherwise refer to order (it is a prepayment)
	 * 
	 * @return Payment object
	 * 
	 */
	private MPayment createPayment(String tenderType) {
		MPayment payment = new MPayment(Env.getCtx(), 0, trxName);
		payment.setAD_Org_ID(entityPOS.getAD_Org_ID());
		payment.setC_POS_ID(entityPOS.getC_POS_ID());
		payment.setTenderType(tenderType);
		payment.setIsReceipt(true);
		payment.setC_Order_ID(order.getC_Order_ID());
		payment.setIsPrepayment(true);
		payment.setC_BPartner_ID(getC_BPartner_ID());
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateTrx());
		int invoiceId = order.getC_Invoice_ID();
		if(invoiceId > 0) {
			payment.setC_Invoice_ID(invoiceId);
			MInvoice invoice = new MInvoice(Env.getCtx(), payment.getC_Invoice_ID(), trxName);
			payment.setDescription(Msg.getMsg(Env.getCtx(), "Invoice No ") + invoice.getDocumentNo());
		} else {
			payment.setDescription(Msg.getMsg(Env.getCtx(), "Order No ") + order.getDocumentNo());
		}
		order.setC_POS_ID(entityPOS.getC_POS_ID());
		order.saveEx(trxName);
		return payment;
	}

	/**
	 * Add Error to Message
	 * @param error
	 * @return void
	 */
	private void addErrorMsg(String error) {
		//	Valid Null error
		if(error == null
				|| error.length() == 0)
			return;
		//	
		if(errorMsg.length() > 0) {
			errorMsg.append(Env.NL);
		} else {
			errorMsg
				.append("@ValidationError@")
				.append(Env.NL);
		}
		//	Add Error
		errorMsg.append(error);
	}
	
	/**
	 * Get Error Message after validate
	 * @return
	 * @return String
	 */
	public String getErrorMsg() {
		if(errorMsg != null && errorMsg.length() > 0) {
			return errorMsg.toString();
		}
		//	Default Return
		return null;
	}
	
	/**
	 * Reset error Message
	 * @return void
	 */
	private void cleanErrorMsg() {
		errorMsg = new StringBuffer();
	}
	
	/**
	 * Validate Payments
	 * @param openAmt
	 * @return
	 * @return String
	 */
	protected String validateTenderTypes(BigDecimal openAmt) {
		cleanErrorMsg();
		if(openAmt.doubleValue() <= 0) {
			addErrorMsg("@POS.validatePayment.NoOpenAmt@");
		}
		//	For Prepay order
		if(isAllowsPartialPayment()) {
			return null;
		} else if(!isCreditOrder()
				&& openAmt.subtract(getCollectDetailAmt()).doubleValue() > 0) {
			addErrorMsg("@POS.OrderPayNotCompleted@");
		}
		//	Local variables for not iterate again
		BigDecimal cashPayment   = Env.ZERO;
		BigDecimal otherPayments = Env.ZERO;
		//	Iterate Payments methods
		for(CollectDetail collectDetail : collectDetails) {
			if(collectDetail.getPayAmt() == null
					|| !(collectDetail.getPayAmt().doubleValue() > 0))
				addErrorMsg("@POS.validatePayment.ZeroAmount@");
			else if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_Cash)) {	//	For Cash
				cashPayment = cashPayment.add(collectDetail.getPayAmt());
			} else if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_Account)) {
				otherPayments = otherPayments.add(collectDetail.getPayAmt());
			} else if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_DirectDebit)) {	//	For Direct Debit
				otherPayments = otherPayments.add(collectDetail.getPayAmt());
			} else if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_Check)) {	//	For Check
				otherPayments = otherPayments.add(collectDetail.getPayAmt());
			} else if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_CreditCard)) {	//	For Credit
				otherPayments = otherPayments.add(collectDetail.getPayAmt());
				//	Valid Expedition
				String mmyy = collectDetail.getCreditCardExpMM() + collectDetail.getCreditCardExpYY();
				String processError = MPaymentValidate.validateCreditCardExp(mmyy);
				//	Validate Month and Year
				if(processError != null && !processError.isEmpty()) {
					addErrorMsg("@" + processError + "@");
				}
				//	
				processError = MPaymentValidate
						.validateCreditCardNumber(collectDetail.getCreditCardNumber(), collectDetail.getCreditCardType());
				//	Validate Card Number
				if(processError != null && !processError.isEmpty()) {
					addErrorMsg("@" + processError + "@");
				}
			} else if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_CreditMemo)) {
				if(collectDetail.getC_Invoice_ID() == 0 )
					addErrorMsg("@POS.CreditMemoNotSelected@");
				BigDecimal amtCreditMemo = collectDetail.getOpenAmtCreditMemo();
				if(collectDetail.getPayAmt().compareTo(amtCreditMemo) > 0){
					addErrorMsg("@POS.OpenAmountCreditMemo@ < @POS.PayAmt@ ");
					collectDetail.setPayAmt(amtCreditMemo);
				}
				otherPayments = otherPayments.add(collectDetail.getPayAmt());
				
			} else {
				addErrorMsg("@POS.validatePayment.UnsupportedPaymentType@");
			}
		}

		// Validate that non-cash payments are less than the amount to be paid.
		if(otherPayments.subtract(openAmt).signum() == 1)
			addErrorMsg("@POS.validatePayment.NonCashPaymentIncorrect@");
		
		// Validate that cash is necessary
		if(otherPayments.subtract(openAmt).signum()== 0 && cashPayment.signum()==1)
			addErrorMsg("@POS.validatePayment.CashPaymentNotNeeded@");
		
		// Validate that there is enough cash for giving a return amount
		BigDecimal returnAmt = cashPayment.add(otherPayments.subtract(openAmt));
		if(returnAmt.subtract(cashPayment).signum() == 1) 			
				addErrorMsg("@POS.validatePayment.ChangeIncorrect@");

		//	Default
		return getErrorMsg();
	}  // processPayment
	
	/**
	 * Processes different kinds of payment types
	 * For Cash: if there is a return amount, modify the payment amount accordingly.
	 * If there are no payment methods, nothing happens
	 * @param trxName
	 * @param openAmt
	 */
	public void processTenderTypes(String trxName, BigDecimal openAmt) {
		cleanErrorMsg();
		this.trxName = trxName;
		boolean result;
		//
		BigDecimal totalPaid = Env.ZERO;
		BigDecimal cashPayment = Env.ZERO;
		BigDecimal otherPayment = Env.ZERO;
		//	Iterate Payments methods
		for(CollectDetail collectDetail : collectDetails) {
			if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_Cash)
					|| collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_Account)) {	//	For Cash
				cashPayment = cashPayment.add(collectDetail.getPayAmt());
			} else if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_DirectDebit)) {	//	For Direct Debit
				otherPayment = otherPayment.add(collectDetail.getPayAmt());
				result= payDirectDebit(collectDetail.getPayAmt(), collectDetail.getRoutingNo(),
						collectDetail.getA_Country(), collectDetail.getCreditCardVV());
				if (!result) {					
					addErrorMsg("@POS.ErrorPaymentDirectDebit@");
					return;
				}
			} else if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_Check)) {	//	For Check
				otherPayment = otherPayment.add(collectDetail.getPayAmt());
				result= payCheck(collectDetail.getPayAmt(), null, collectDetail.getRoutingNo(), collectDetail.getReferenceNo());
				if (!result) {					
					addErrorMsg("@POS.ErrorPaymentCheck@");
					return;
				}
			} else if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_CreditCard)) {	//	For Credit
				otherPayment = otherPayment.add(collectDetail.getPayAmt());
				//	Valid Expedition
				String mmyy = collectDetail.getCreditCardExpMM() + collectDetail.getCreditCardExpYY();
				//	Valid Month and Year
				int month = MPaymentValidate.getCreditCardExpMM(mmyy);
				int year = MPaymentValidate.getCreditCardExpYY(mmyy);
				//	Pay from Credit Card
				result= payCreditCard(collectDetail.getPayAmt(), collectDetail.getA_Name(),
						month, year, collectDetail.getCreditCardNumber(), collectDetail.getCreditCardVV(), collectDetail.getCreditCardType());
				if (!result) {					
					addErrorMsg("@POS.ErrorPaymentCreditCard@");
					return;
				}
			} else if(collectDetail.getTenderType().equals(X_C_Payment.TENDERTYPE_CreditMemo)) {
				if(isAllowsPartialPayment()) {
					addErrorMsg("@POS.PrePayment.NoCreditMemoAllowed@");
					return;
				}
				otherPayment = otherPayment.add(collectDetail.getPayAmt());
				result= payCreditMemo(collectDetail.getM_InvCreditMemo(), collectDetail.getPayAmt());
				if (!result) {					
					addErrorMsg("@POS.ErrorPaymentCreditMEmo@");
					return;
				}
			}
			totalPaid = totalPaid.add(collectDetail.getPayAmt());
		}

		//	Save Cash Payment
		//	Validate if payment consists credit card or cash -> payment amount must be exact
		BigDecimal amountRefunded = openAmt.subtract(otherPayment.add(cashPayment));
		if(amountRefunded.signum() == -1
				&& cashPayment.doubleValue() > 0) {
			if(amountRefunded.abs().doubleValue() > cashPayment.doubleValue()) {
				addErrorMsg("@POS.validatePayment.PaymentBustBeExact@");
			} else {
				result= payCash(cashPayment.add(amountRefunded), amountRefunded.negate());
				if (!result) {					
					addErrorMsg("@POS.ErrorPaymentCash@");
					return;
				}
			}
		} else if(cashPayment.signum() > 0) {
			result = payCash(cashPayment, amountRefunded.negate());
			if (!result) {					
				addErrorMsg("@POS.ErrorPaymentCash@");
				return;
			}
		}
		order.saveEx(trxName);
	}  // processPayment
	/**
	* Get PayAmt 
	*/
	public BigDecimal getPrePayAmt(){
		String sql ="SELECT Sum(PayAmt) FROM C_Order o"
				+ "	LEFT JOIN C_Payment p on p.C_order_ID = o.C_order_ID"
				+ "	WHERE o.C_Order_ID = ?";
		BigDecimal received = DB.getSQLValueBD(null, sql, order.getC_Order_ID());
		if ( received == null )
			received = Env.ZERO;

		return received;
	}
	/**
	 * Duplicated from MPayment
	 * 	Get Accepted Credit Cards for amount
	 *	@param amt trx amount
	 *	@return credit cards
	 */
	public ValueNamePair[] getCreditCards (BigDecimal amount, int clientId, int orgId, int currencyId, String trxName)
	{
		try
		{
			MPaymentProcessor[] paymentProcessors = MPaymentProcessor.find (Env.getCtx(), null, null,
					clientId,  orgId, currencyId, amount, trxName);
			//
			HashMap<String,ValueNamePair> map = new HashMap<String,ValueNamePair>(); //	to eliminate duplicates
			for (int i = 0; i < paymentProcessors.length; i++)
			{
				if (paymentProcessors[i].isAcceptAMEX ())
					map.put (MPayment.CREDITCARDTYPE_Amex, getCreditCardPair (MPayment.CREDITCARDTYPE_Amex));
				if (paymentProcessors[i].isAcceptDiners ())
					map.put (MPayment.CREDITCARDTYPE_Diners, getCreditCardPair (MPayment.CREDITCARDTYPE_Diners));
				if (paymentProcessors[i].isAcceptDiscover ())
					map.put (MPayment.CREDITCARDTYPE_Discover, getCreditCardPair (MPayment.CREDITCARDTYPE_Discover));
				if (paymentProcessors[i].isAcceptMC ())
					map.put (MPayment.CREDITCARDTYPE_MasterCard, getCreditCardPair (MPayment.CREDITCARDTYPE_MasterCard));
				if (paymentProcessors[i].isAcceptCorporate ())
					map.put (MPayment.CREDITCARDTYPE_PurchaseCard, getCreditCardPair (MPayment.CREDITCARDTYPE_PurchaseCard));
				if (paymentProcessors[i].isAcceptVisa ())
					map.put (MPayment.CREDITCARDTYPE_Visa, getCreditCardPair (MPayment.CREDITCARDTYPE_Visa));
			} //	for all payment processors
			//
			ValueNamePair[] retValue = new ValueNamePair[map.size ()];
			map.values ().toArray (retValue);
			return retValue;
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}	//	getCreditCards
	
	/**
	 * 
	 * Duplicated from MPayment
	 * 	Get Type and name pair
	 *	@param creditCardType credit card Type
	 *	@return pair
	 */
	private ValueNamePair getCreditCardPair (String creditCardType)
	{
		return new ValueNamePair (creditCardType, getCreditCardName(creditCardType));
	}	//	getCreditCardPair

	/**
	 * 
	 * Duplicated from MPayment
	 *	Get Name of Credit Card
	 * 	@param creditCardType credit card type
	 *	@return Name
	 */
	public String getCreditCardName(String creditCardType)
	{
		if (creditCardType == null)
			return "--";
		else if (MPayment.CREDITCARDTYPE_MasterCard.equals(creditCardType))
			return "MasterCard";
		else if (MPayment.CREDITCARDTYPE_Visa.equals(creditCardType))
			return "Visa";
		else if (MPayment.CREDITCARDTYPE_Amex.equals(creditCardType))
			return "Amex";
		else if (MPayment.CREDITCARDTYPE_ATM.equals(creditCardType))
			return "ATM";
		else if (MPayment.CREDITCARDTYPE_Diners.equals(creditCardType))
			return "Diners";
		else if (MPayment.CREDITCARDTYPE_Discover.equals(creditCardType))
			return "Discover";
		else if (MPayment.CREDITCARDTYPE_PurchaseCard.equals(creditCardType))
			return "PurchaseCard";
		return "?" + creditCardType + "?";
	}	//	getCreditCardName

	/**
	 * @return the partnerId
	 */
	public int getC_BPartner_ID() {
		return partnerId;
	}
	
	/**
	 * @return the bankAccountId
	 */
	public int getC_BankAccount_ID() {
		return bankAccountId;
	}
	
	/**
	 * @return the dateTrx
	 */
	public Timestamp getDateTrx() {
		return dateTrx;
	}
	
	/**
	 * @param partnerId the partnerId to set
	 */
	public void setC_BPartner_ID(int partnerId) {
		this.partnerId = partnerId;
	}
	
	/**
	 * @param bankAccountId the bankAccountId to set
	 */
	public void setC_BankAccount_ID(int bankAccountId) {
		this.bankAccountId = bankAccountId;
	}
	
	/**
	 * @param dateTrx the dateTrx to set
	 */
	public void setDateTrx(Timestamp dateTrx) {
		this.dateTrx = dateTrx;
	}
	
	/**
	 * Set Payment term
	 * @param paymentTermId
	 * @return void
	 */
	public void setC_PaymentTerm_ID(int paymentTermId) {
		this.paymentTermId = paymentTermId;
	}
	
	/**
	 * Get Payment Term
	 * @return
	 * @return int
	 */
	public int getC_PaymentTerm_ID() {
		return paymentTermId;
	}
	
	/**
	 * Verify if is Prepay Order
	 * @return
	 * @return boolean
	 */
	public boolean isCreditOrder() {
		return isCreditOrder;
	}
	
	/**
	 * Set Is Credit Order
	 * @param isCreditOrder
	 * @return void
	 */
	public void setIsCreditOrder(boolean isCreditOrder) {
		this.isCreditOrder = isCreditOrder;
	}
	
	/**
	 * isAllowsPartialPayment
	 * @return boolean when is Allows Partial Payment
	 */
	public boolean isAllowsPartialPayment() {
		if (order != null) {
			if (MOrder.DocSubTypeSO_POS.equals(order.getC_DocTypeTarget().getDocSubTypeSO())
					&& MOrder.DOCSTATUS_Completed.equals(order.getDocStatus()))
				return true;
		}

		return false;
	}
	
	/**
	 * Get number of payment details
	 * @return Quantity of payment details
	 * @return int 
	 */
	public int getDetailQty() {
		return collectDetails.size();
	}
	
	/**
	 * Get all Collects detail
	 * @return
	 * @return List<CollectDetail>
	 */
	public List<CollectDetail> getCollectDetails() {
		return collectDetails;
	}
}