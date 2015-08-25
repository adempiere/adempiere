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
package org.compiere.pos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentAllocate;
import org.compiere.model.MPaymentValidate;
import org.compiere.model.X_C_Payment;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com Aug 24, 2015, 10:17:04 PM
 *
 */
public class Collect {
	
	/**
	 * 
	 * *** Constructor ***
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param ctx
	 * @param m_Order
	 * @param m_M_POS_ID
	 * @param m_C_BPartner_ID
	 * @param m_C_BankAccount_ID
	 * @param m_DateTrx
	 * @param trxName
	 */
	public Collect(Properties ctx, MOrder m_Order, int m_M_POS_ID) {
		//	Instance Collects
		m_Collects = new ArrayList<CollectDetail>();
		//	Instance POS
		m_POS = MPOS.get(ctx, m_M_POS_ID);
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
	
	/**
	 * Add Cash Collect
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
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
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
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
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
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
	 * Find Cash
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
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
	 * 	Process Payment
	 * 
	 * @return true if payment processed correctly; otherwise false
	 * 
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
	 * 	Payment with check
	 * 
	 * @return true if payment processed correctly; otherwise false
	 * 
	 */
	public boolean payCheck(BigDecimal amt, String accountNo, String routingNo, String checkNo) {
		MPayment payment = createPayment(MPayment.TENDERTYPE_Cash);
		payment.setC_CashBook_ID(m_POS.getC_CashBook_ID());
		payment.setAmount(m_Order.getC_Currency_ID(), amt);
		payment.setC_BankAccount_ID(m_POS.getC_BankAccount_ID());
		payment.setAccountNo(accountNo);
		payment.setRoutingNo(routingNo);
		payment.setCheckNo(checkNo);
		payment.setDescription("No de cheque:" +checkNo);
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
	 * 	Payment with credit card
	 * 
	 * @return true if payment processed correctly; otherwise false
	 * 
	 */
	public boolean payCreditCard(BigDecimal amt, String accountName, int month, int year,
			String cardNo, String cvc, String cardtype) {

		MPayment payment = createPayment(MPayment.TENDERTYPE_Check);
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
	 *  Refer to invoice if there is an invoice
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
			payment.setDescription(Msg.getMsg(Env.getCtx(), "Invoice No") + inv.getDocumentNo());
		} else {
			payment.setC_Order_ID(m_Order.getC_Order_ID());
		}
			
		return payment;
	}
	
	/**
	 * Processes different kinds of payment types
	 * 
	 */
	public void processPayment() {
		try {
			for(CollectDetail m_Collect : m_Collects) {
				if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Cash)
						|| m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Account)) {
					payCash(m_Collect.getPayAmt());
				} else if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Check)) {
					payCheck(m_Collect.getPayAmt(), null, null, m_Collect.getReferenceNo());
				} else if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_CreditCard)) {
					String error = null;
					error = MPaymentValidate.validateCreditCardExp(m_Collect.getCreditCardExpMM());
					if(error != null && !error.isEmpty()) {
						throw new AdempierePOSException(error);
					}
					int month = MPaymentValidate.getCreditCardExpMM(m_Collect.getCreditCardExpMM());
					int year = MPaymentValidate.getCreditCardExpYY(m_Collect.getCreditCardExpYY());

					error = MPaymentValidate.validateCreditCardNumber(m_Collect.getCreditCardNumber(), m_Collect.getCreditCardType());
					if(error != null && !error.isEmpty()) {
						throw new AdempierePOSException(error);
					}
					//	PAy from Credit Card
					payCreditCard(m_Collect.getPayAmt(), m_Collect.getA_Name(),
							month, year, m_Collect.getCreditCardNumber(), m_Collect.getCreditCardVV(), m_Collect.getCreditCardType());
				} 
//				else if(m_Collect.getTenderType().equals("F")) {
//					String ID = ((ValueNamePair) fCreditNotes.getSelectedItem()).getValue();
//					MInvoice cn = new MInvoice(Env.getCtx(), Integer.parseInt(ID), trxName);
//					payCreditNote(cn, m_Collect.getPayAmt());
//				} 
				else {
					throw new AdempierePOSException("Unsupported payment type");
				}
			}
		} catch (Exception e ) {
			throw new AdempierePOSException("Payment processing failed : " + e.getMessage());
		}
	}  // processPayment
	
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
}