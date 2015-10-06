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

import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentAllocate;
import org.compiere.model.MPaymentProcessor;
import org.compiere.model.MPaymentValidate;
import org.compiere.model.X_C_Payment;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *
 */
public class Collect {
	
	/**
	 * 
	 * *** Constructor ***
	 * @param ctx
	 * @param m_Order
	 * @param m_M_POS_ID
	 * @param m_C_BPartner_ID
	 * @param m_C_BankAccount_ID
	 * @param m_DateTrx
	 * @param trxName
	 */
	public Collect(Properties ctx, MOrder m_Order, int m_M_POS_ID) {
		this(ctx, m_Order, MPOS.get(ctx, m_M_POS_ID));
	}
	
	/**
	 * 
	 * *** Constructor ***
	 * @param ctx
	 * @param m_Order
	 * @param m_POS
	 */
	public Collect(Properties ctx, MOrder m_Order, MPOS m_POS) {
		//	Instance Collects
		m_Collects = new ArrayList<CollectDetail>();
		//	Instance POS
		this.m_POS = m_POS;
		//	Set Order
		this.m_Order = m_Order;
		this.m_C_BPartner_ID = m_Order.getC_BPartner_ID();
		this.m_C_BankAccount_ID = m_POS.getC_BankAccount_ID();
		this.m_DateTrx = m_Order.getDateOrdered();
		this.trxName = m_Order.get_TrxName();

	}
	
	/**	Transaction				*/
	private String 				trxName;
	/**	Order					*/
	private MOrder 				m_Order;
	/**	POS						*/
	private MPOS				m_POS;
	/**	Business Partner		*/
	private int 				m_C_BPartner_ID;
	/**	Bank Account			*/
	private int 				m_C_BankAccount_ID;
	/**	Date for Collect		*/
	private Timestamp			m_DateTrx;
	/**	Collects				*/
	private List<CollectDetail> m_Collects;
	/**	Credit Order			*/
	private boolean				m_IsCreditOrder = false;
	/**	Pre-Payment Order		*/
	private boolean				m_IsPrePayOrder = false;
	/**	Payment Term			*/
	private int 				m_C_PaymentTerm_ID = 0;
	/**	Error Message			*/
	private StringBuffer		m_ErrorMsg = new StringBuffer();

	/**
	 * Add New Collect
	 * @param detail
	 * @return void
	 */
	public void addCollect(CollectDetail detail) {
		m_Collects.add(detail);
	}
	
	/**
	 * Remove a Collect  Detail
	 * @param detail
	 * @return void
	 */
	public void removeCollect(CollectDetail detail) {
		m_Collects.remove(detail);
	}
	
	/**
	 * Remove all Collect Details
	 * @param none
	 * @return void
	 */
	public void removeAllCollectDetails() {
		int lenght = m_Collects.size();
		for(int i = lenght - 1; i >= 0; i--) {
			m_Collects.remove(i);
		}
	}
	
	/**
	 * Get Payment Amount
	 * @return
	 * @return BigDecimal
	 */
	public BigDecimal getPayAmt() {
		BigDecimal m_PayAmt = Env.ZERO;
		//	Get from List
		for(CollectDetail detail : m_Collects) {
			m_PayAmt = m_PayAmt.add(detail.getPayAmt());
		}
		//	Default Return
		return m_PayAmt;
	}
	
	/**
	 * Add Cash Collect
	 * @param m_PayAmt
	 * @return void
	 */
	public void addCash(BigDecimal m_PayAmt) {
		int position = findCash();
		CollectDetail m_Collect = null;
		if(position != -1) {
			m_Collect = m_Collects.get(position);
			m_Collect.setPayAmt(m_PayAmt);
			m_Collects.set(position, m_Collect);
			return;
		} else {
			m_Collect = CollectDetail.createCash(m_PayAmt);
			m_Collect.setDateTrx(getDateTrx());
		}
		//	Default Add Cash
		m_Collects.add(m_Collect);
	}
	
	/**
	 * Add Check Collect
	 * @param m_PayAmt
	 * @param m_CheckNo
	 * @param m_C_Bank_ID
	 * @param m_DateTrx
	 * @return void
	 */
	public void addCheck(BigDecimal m_PayAmt, String m_CheckNo, 
			int m_C_Bank_ID, Timestamp m_DateTrx) {
		int position = findCheck(m_CheckNo);
		CollectDetail m_Collect = null;
		if(position != -1) {
			m_Collect = m_Collects.get(position);
			m_Collect.setPayAmt(m_PayAmt);
			m_Collects.set(position, m_Collect);
			return;
		} else {
			m_Collect = CollectDetail.createCheck(m_PayAmt, m_CheckNo, m_C_Bank_ID, m_DateTrx);
		}
		//	Default Add
		m_Collects.add(m_Collect);
	}
	
	/**
	 * Find a Check
	 * @param m_CheckNo
	 * @return
	 * @return int
	 */
	public int findCheck(String m_CheckNo) {
		for(int i = 0; i < m_Collects.size(); i++) {
			CollectDetail m_Collect = m_Collects.get(i);
			if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Check)
					&& m_Collect.getReferenceNo() != null
					&& m_Collect.getReferenceNo().equals(m_CheckNo)) {
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
		for(int i = 0; i < m_Collects.size(); i++) {
			CollectDetail m_Collect = m_Collects.get(i);
			if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Cash)) {
				return true;
			}
		}
		return false; // No Credit cash found
	}
	
	/**
	 * Is there only one Cash payment
	 * @param none
	 * @return true if there is only one payment and it is a cash
	 */
	public boolean isExistOnlyOneCash() {
		int count = 0;
		for(int i = 0; i < m_Collects.size(); i++) {
			CollectDetail m_Collect = m_Collects.get(i);
			if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Cash)) {
				count = count+1;
			}
		}
		return (count==1 && m_Collects.size()==1);
	}
	
	/**
	 * Is there a Credit Card payment
	 * @param none
	 * @return position of first credit card or -1
	 */
	public int isExistCreditCard() {
		for(int i = 0; i < m_Collects.size(); i++) {
			CollectDetail m_Collect = m_Collects.get(i);
			if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_CreditCard)) {
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
		for(int i = 0; i < m_Collects.size(); i++) {
			CollectDetail m_Collect = m_Collects.get(i);
			if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_CreditCard)) {
				count = count+1;
			}
		}
		return (count==1 && m_Collects.size()==1);
	}
	
	/**
	 * Is there a Check
	 * @param none
	 * @return position of first check or -1
	 */
	public int isExistCheck() {
		for(int i = 0; i < m_Collects.size(); i++) {
			CollectDetail m_Collect = m_Collects.get(i);
			if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Check)) {
				return i;
			}
		}
		return -1; // No Check found
	}
	
	/**
	 * Is there only one Check payment
	 * @param none
	 * @return true if there is only one payment and it is a check
	 */
	public boolean isExistOnlyOneCheck() {
		int count = 0;
		for(int i = 0; i < m_Collects.size(); i++) {
			CollectDetail m_Collect = m_Collects.get(i);
			if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Check)) {
				count = count+1;
			}
		}
		return (count==1 && m_Collects.size()==1);
	}
	
	/**
	 * Find Cash
	 * @return
	 * @return int
	 */
	public int findCash() {
		for(int i = 0; i < m_Collects.size(); i++) {
			CollectDetail m_Collect = m_Collects.get(i);
			if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Cash)) {
				return i;
			}
		}
		//	Return Position
		return -1;
	}

	/**
	 * Process Payment
	 * @param amt
	 * @return true if payment processed correctly; otherwise false
	 */
	public boolean payCash(BigDecimal amt) {

		MPayment payment = createPayment(MPayment.TENDERTYPE_Cash);
		payment.setC_CashBook_ID(m_POS.getC_CashBook_ID());
		payment.setAmount(m_Order.getC_Currency_ID(), amt);
		payment.setC_BankAccount_ID(m_POS.getC_BankAccount_ID());
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateTrx());
		payment.saveEx();
		payment.setDocAction(MPayment.DOCACTION_Complete);
		payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		if (payment.processIt(MPayment.DOCACTION_Complete)){
			payment.saveEx();
			return true;
		}
		else return false;
	} // payCash


	/**
	 * Payment with check
	 * @param amt
	 * @param accountNo
	 * @param routingNo
	 * @param checkNo
	 * @return true if payment processed correctly; otherwise false
	 */
	public boolean payCheck(BigDecimal amt, String accountNo, String routingNo, String checkNo) {
		MPayment payment = createPayment(MPayment.TENDERTYPE_Check);
		payment.setC_CashBook_ID(m_POS.getC_CashBook_ID());
		payment.setAmount(m_Order.getC_Currency_ID(), amt);
		payment.setC_BankAccount_ID(m_POS.getC_BankAccount_ID());
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
		payment.setC_CashBook_ID(m_POS.getC_CashBook_ID());
		payment.setAmount(m_Order.getC_Currency_ID(), amt);
		payment.setC_BankAccount_ID(m_POS.getC_BankAccount_ID());
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
	public boolean payCreditCard(BigDecimal amt, String accountName, int month, int year,
			String cardNo, String cvc, String cardtype) {

		MPayment payment = createPayment(MPayment.TENDERTYPE_CreditCard);
		payment.setAmount(m_Order.getC_Currency_ID(), amt);
		payment.setC_BankAccount_ID(m_POS.getC_BankAccount_ID());
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateTrx());
		payment.setCreditCard(MPayment.TRXTYPE_Sales, cardtype,
				cardNo, cvc, month, year);
		payment.saveEx();
		payment.setDocAction(MPayment.DOCACTION_Complete);
		payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		if (payment.processIt(MPayment.DOCACTION_Complete)) {
			payment.saveEx();
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
	public boolean payCreditNote(MInvoice creditNote, BigDecimal amt) {
		int m_C_Invoice_ID = m_Order.getC_Invoice_ID();
		if(m_C_Invoice_ID == 0)
			return false;
		MPayment payment = createPayment(MPayment.TENDERTYPE_Account);
		payment.setAmount(m_Order.getC_Currency_ID(), Env.ZERO);
		payment.setC_BankAccount_ID(m_POS.getC_BankAccount_ID());
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateTrx());
		payment.saveEx();
		//Invoice
		MPaymentAllocate pa = new MPaymentAllocate(Env.getCtx(), 0, trxName);
		pa.setC_Payment_ID(payment.getC_Payment_ID());
		pa.setC_Invoice_ID(m_C_Invoice_ID);
		pa.setAmount(amt);
		pa.saveEx();
		//CreditNote
		pa = new MPaymentAllocate(Env.getCtx(), 0, trxName);
		pa.setC_Payment_ID(payment.getC_Payment_ID());
		pa.setC_Invoice_ID(creditNote.getC_Invoice_ID());
		pa.setAmount(amt.negate());
		pa.saveEx();
		
		payment.setDocAction(MPayment.DOCACTION_Complete);
		payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		if(payment.processIt(MPayment.DOCACTION_Complete)) {
			payment.saveEx();
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
		payment.setAD_Org_ID(m_POS.getAD_Org_ID());
		payment.setTenderType(tenderType);
		payment.setIsReceipt(true);
		payment.setC_BPartner_ID(getC_BPartner_ID());
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateTrx());
		int m_C_Invoice_ID = m_Order.getC_Invoice_ID();
		if(m_C_Invoice_ID > 0) {
			payment.setC_Invoice_ID(m_C_Invoice_ID);
			MInvoice inv = new MInvoice(Env.getCtx(), payment.getC_Invoice_ID(), trxName);
			payment.setDescription(Msg.getMsg(Env.getCtx(), "Invoice No ") + inv.getDocumentNo());
		} else {
			payment.setC_Order_ID(m_Order.getC_Order_ID());
			payment.setDescription(Msg.getMsg(Env.getCtx(), "Order No ") + m_Order.getDocumentNo());
		}
			
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
		if(m_ErrorMsg.length() > 0) {
			m_ErrorMsg.append(Env.NL);
		} else {
			m_ErrorMsg
				.append("@ValidationError@")
				.append(Env.NL);
		}
		//	Add Error
		m_ErrorMsg.append(error);
	}
	
	/**
	 * Get Error Message after validate
	 * @return
	 * @return String
	 */
	public String getErrorMsg() {
		if(m_ErrorMsg.length() > 0) {
			return m_ErrorMsg.toString();
		}
		//	Default Return
		return null;
	}
	
	/**
	 * Reset error Message
	 * @return void
	 */
	private void cleanErrorMsg() {
		m_ErrorMsg = new StringBuffer();
	}
	
	/**
	 * Validate Payments
	 * @param p_OpenAmt
	 * @return
	 * @return String
	 */
	protected String validatePayment(BigDecimal p_OpenAmt) {
		cleanErrorMsg();
		if(p_OpenAmt.doubleValue() <= 0) {
			addErrorMsg("@POS.validatePayment.NoOpenAmt@");
		}
		//	For Prepay order
		if(!isPrePayOrder()
				&& p_OpenAmt.subtract(getPayAmt()).doubleValue() > 0) {
			addErrorMsg("@POS.OrderPayNotCompleted@");
		} else if(!isCreditOrder()
				&& p_OpenAmt.subtract(getPayAmt()).doubleValue() > 0) {
			addErrorMsg("@POS.OrderPayNotCompleted@");
		} else if(isCreditOrder()) {
			return null;
		}
		//	Local variables for not iterate again
		BigDecimal m_CashPayment = Env.ZERO;
		BigDecimal m_OtherPayment = Env.ZERO;
		//	Iterate Payments methods
		for(CollectDetail m_Collect : m_Collects) {
			if(m_Collect.getPayAmt() == null
					|| !(m_Collect.getPayAmt().doubleValue() > 0))
				addErrorMsg("@POS.validatePayment.ZeroAmount@");
			else if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Cash)) {	//	For Cash
				m_CashPayment = m_CashPayment.add(m_Collect.getPayAmt());
			} else if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Account)) {
				m_OtherPayment = m_OtherPayment.add(m_Collect.getPayAmt());
			} else if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_DirectDebit)) {	//	For Direct Debit 
				m_OtherPayment = m_OtherPayment.add(m_Collect.getPayAmt());
			} else if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Check)) {	//	For Check
				m_OtherPayment = m_OtherPayment.add(m_Collect.getPayAmt());
			} else if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_CreditCard)) {	//	For Credit
				m_OtherPayment = m_OtherPayment.add(m_Collect.getPayAmt());
				//	Valid Expedition
				String mmyy = m_Collect.getCreditCardExpMM() + m_Collect.getCreditCardExpYY();
				String processError = MPaymentValidate
						.validateCreditCardExp(mmyy);
				//	Validate Month and Year
				if(processError != null && !processError.isEmpty()) {
					addErrorMsg("@" + processError + "@");
				}
				//	
				processError = MPaymentValidate
						.validateCreditCardNumber(m_Collect.getCreditCardNumber(), m_Collect.getCreditCardType());
				//	Validate Card Number
				if(processError != null && !processError.isEmpty()) {
					addErrorMsg("@" + processError + "@");
				}
			} else {
				addErrorMsg("@POS.validatePayment.UnsupportedPaymentType@");
			}
		}
		//	Validate if payment consists credit card or cash -> payment amount must be exact
		BigDecimal m_ReturnAmt = p_OpenAmt.subtract(m_OtherPayment.add(m_CashPayment));
		if(m_ReturnAmt.signum() == -1) {
			if(m_ReturnAmt.abs().doubleValue() > m_CashPayment.doubleValue()) {
				addErrorMsg("@POS.validatePayment.PaymentBustBeExact@");
			}
		}
		//	Default
		return getErrorMsg();
	}  // processPayment
	
	/**
	 * Processes different kinds of payment types
	 * For Cash: if there is a return amount, modify the payment amount accordingly.
	 * @param trxName
	 * @param p_OpenAmt
	 */
	public void processPayment(String trxName, BigDecimal p_OpenAmt) {
		this.trxName = trxName;
		//	
		//	For Credit order
		if(isCreditOrder()) {
			//	Default Ok
			return;
		}
		BigDecimal m_CashPayment = Env.ZERO;
		BigDecimal m_OtherPayment = Env.ZERO;
		//	Iterate Payments methods
		for(CollectDetail m_Collect : m_Collects) {
			if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Cash)
					|| m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Account)) {	//	For Cash
				m_CashPayment = m_CashPayment.add(m_Collect.getPayAmt());
			} else if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_DirectDebit)) {	//	For Direct Debit
				m_OtherPayment = m_OtherPayment.add(m_Collect.getPayAmt());
				payDirectDebit(m_Collect.getPayAmt(), m_Collect.getRoutingNo(), 
						m_Collect.getA_Country(), m_Collect.getCreditCardVV());
			} else if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Check)) {	//	For Check
				m_OtherPayment = m_OtherPayment.add(m_Collect.getPayAmt());
				payCheck(m_Collect.getPayAmt(), null, null, m_Collect.getReferenceNo());
			} else if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_CreditCard)) {	//	For Credit
				m_OtherPayment = m_OtherPayment.add(m_Collect.getPayAmt());
				//	Valid Expedition
				String mmyy = m_Collect.getCreditCardExpMM() + m_Collect.getCreditCardExpYY();
				//	Valid Month and Year
				int month = MPaymentValidate.getCreditCardExpMM(mmyy);
				int year = MPaymentValidate.getCreditCardExpYY(mmyy);
				//	Pay from Credit Card
				payCreditCard(m_Collect.getPayAmt(), m_Collect.getA_Name(),
						month, year, m_Collect.getCreditCardNumber(), m_Collect.getCreditCardVV(), m_Collect.getCreditCardType());
			}
		}
		//	Save Cash Payment
		//	Validate if payment consists credit card or cash -> payment amount must be exact
		BigDecimal m_ReturnAmt = p_OpenAmt.subtract(m_OtherPayment.add(m_CashPayment));
		if(m_ReturnAmt.signum() == -1
				&& m_CashPayment.doubleValue() > 0) {
			if(m_ReturnAmt.abs().doubleValue() > m_CashPayment.doubleValue()) {
				addErrorMsg("@POS.validatePayment.PaymentBustBeExact@");
			} else {
				payCash(m_CashPayment.add(m_ReturnAmt));
			}
		} else if(m_CashPayment.doubleValue() > 0) {
			payCash(m_CashPayment);
		}
	}  // processPayment
	/**
	* Get PayAmt 
	*/
	public BigDecimal getPrePayAmt(){
		String sql ="SELECT Sum(PayAmt) FROM C_Order o"
				+ "	LEFT JOIN C_Payment p on p.C_order_ID = o.C_order_ID"
				+ "	WHERE o.C_Order_ID = ?";
		BigDecimal received = DB.getSQLValueBD(null, sql, m_Order.getC_Order_ID());
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
	public ValueNamePair[] getCreditCards (BigDecimal p_amt, int p_AD_Client_ID, int p_AD_Org_ID, int p_C_Currency_ID, String p_TrxName)
	{
		try
		{
			MPaymentProcessor[] m_mPaymentProcessors = MPaymentProcessor.find (Env.getCtx(), null, null, 
					p_AD_Client_ID,  p_AD_Org_ID, p_C_Currency_ID, p_amt, p_TrxName);
			//
			HashMap<String,ValueNamePair> map = new HashMap<String,ValueNamePair>(); //	to eliminate duplicates
			for (int i = 0; i < m_mPaymentProcessors.length; i++)
			{
				if (m_mPaymentProcessors[i].isAcceptAMEX ())
					map.put (MPayment.CREDITCARDTYPE_Amex, getCreditCardPair (MPayment.CREDITCARDTYPE_Amex));
				if (m_mPaymentProcessors[i].isAcceptDiners ())
					map.put (MPayment.CREDITCARDTYPE_Diners, getCreditCardPair (MPayment.CREDITCARDTYPE_Diners));
				if (m_mPaymentProcessors[i].isAcceptDiscover ())
					map.put (MPayment.CREDITCARDTYPE_Discover, getCreditCardPair (MPayment.CREDITCARDTYPE_Discover));
				if (m_mPaymentProcessors[i].isAcceptMC ())
					map.put (MPayment.CREDITCARDTYPE_MasterCard, getCreditCardPair (MPayment.CREDITCARDTYPE_MasterCard));
				if (m_mPaymentProcessors[i].isAcceptCorporate ())
					map.put (MPayment.CREDITCARDTYPE_PurchaseCard, getCreditCardPair (MPayment.CREDITCARDTYPE_PurchaseCard));
				if (m_mPaymentProcessors[i].isAcceptVisa ())
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
	 *	@param CreditCardType credit card Type
	 *	@return pair
	 */
	private ValueNamePair getCreditCardPair (String CreditCardType)
	{
		return new ValueNamePair (CreditCardType, getCreditCardName(CreditCardType));
	}	//	getCreditCardPair

	/**
	 * 
	 * Duplicated from MPayment
	 *	Get Name of Credit Card
	 * 	@param CreditCardType credit card type
	 *	@return Name
	 */
	public String getCreditCardName(String CreditCardType)
	{
		if (CreditCardType == null)
			return "--";
		else if (MPayment.CREDITCARDTYPE_MasterCard.equals(CreditCardType))
			return "MasterCard";
		else if (MPayment.CREDITCARDTYPE_Visa.equals(CreditCardType))
			return "Visa";
		else if (MPayment.CREDITCARDTYPE_Amex.equals(CreditCardType))
			return "Amex";
		else if (MPayment.CREDITCARDTYPE_ATM.equals(CreditCardType))
			return "ATM";
		else if (MPayment.CREDITCARDTYPE_Diners.equals(CreditCardType))
			return "Diners";
		else if (MPayment.CREDITCARDTYPE_Discover.equals(CreditCardType))
			return "Discover";
		else if (MPayment.CREDITCARDTYPE_PurchaseCard.equals(CreditCardType))
			return "PurchaseCard";
		return "?" + CreditCardType + "?";
	}	//	getCreditCardName

	/**
	 * @return the m_C_BPartner_ID
	 */
	public int getC_BPartner_ID() {
		return m_C_BPartner_ID;
	}
	
	/**
	 * @return the m_C_BankAccount_ID
	 */
	public int getC_BankAccount_ID() {
		return m_C_BankAccount_ID;
	}
	
	/**
	 * @return the m_DateTrx
	 */
	public Timestamp getDateTrx() {
		return m_DateTrx;
	}
	
	/**
	 * @param m_C_BPartner_ID the m_C_BPartner_ID to set
	 */
	public void setC_BPartner_ID(int m_C_BPartner_ID) {
		this.m_C_BPartner_ID = m_C_BPartner_ID;
	}
	
	/**
	 * @param m_C_BankAccount_ID the m_C_BankAccount_ID to set
	 */
	public void setC_BankAccount_ID(int m_C_BankAccount_ID) {
		this.m_C_BankAccount_ID = m_C_BankAccount_ID;
	}
	
	/**
	 * @param m_DateTrx the m_DateTrx to set
	 */
	public void setDateTrx(Timestamp m_DateTrx) {
		this.m_DateTrx = m_DateTrx;
	}
	
	/**
	 * Set Payment term
	 * @param p_C_PaymentTerm_ID
	 * @return void
	 */
	public void setC_PaymentTerm_ID(int p_C_PaymentTerm_ID) {
		m_C_PaymentTerm_ID = p_C_PaymentTerm_ID;
	}
	
	/**
	 * Get Payment Term
	 * @return
	 * @return int
	 */
	public int getC_PaymentTerm_ID() {
		return m_C_PaymentTerm_ID;
	}
	
	/**
	 * Verify if is Prepay Order
	 * @return
	 * @return boolean
	 */
	public boolean isCreditOrder() {
		return m_IsCreditOrder;
	}
	
	/**
	 * Set Is Credit Order
	 * @param isCreditOrder
	 * @return void
	 */
	public void setIsCreditOrder(boolean isCreditOrder) {
		this.m_IsCreditOrder = isCreditOrder;
		//	Negate Pre-Pay
		if(isCreditOrder) {
			m_IsPrePayOrder = !isCreditOrder;
		}
	}
	
	/**
	 * Is Pre-Payment Order
	 * @return
	 * @return boolean
	 */
	public boolean isPrePayOrder() {
		return m_IsPrePayOrder;
	}
	
	/**
	 * Set Is Pre-Payment Order
	 * @param isPrePayOrder
	 * @return void
	 */
	public void setIsPrePayOrder(boolean isPrePayOrder) {
		this.m_IsPrePayOrder = isPrePayOrder;
		//	Negate Credit Order
		if(isPrePayOrder) {
			m_IsCreditOrder = !isPrePayOrder;
		}
	}
	
	/**
	 * Get number of payment details
	 * @return Quantity of payment details
	 * @return int 
	 */
	public int getDetailQty() {
		return m_Collects.size();
	}
	
	/**
	 * Get all Collects detail
	 * @return
	 * @return List<CollectDetail>
	 */
	public List<CollectDetail> getCollectDetails() {
		return m_Collects;
	}
}
