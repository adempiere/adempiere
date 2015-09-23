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
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

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
		this(ctx, m_Order, MPOS.get(ctx, m_M_POS_ID));
	}
	
	/**
	 * 
	 * *** Constructor ***
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> Sep 20, 2015, 11:15:51 PM
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
	
	/**
	 * Add New Collect
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param detail
	 * @return void
	 */
	public void addCollect(CollectDetail detail) {
		m_Collects.add(detail);
	}
	
	/**
	 * Remove a Collect  Detail
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param detail
	 * @return void
	 */
	public void removeCollect(CollectDetail detail) {
		m_Collects.remove(detail);
	}
	
	/**
	 * Get Payment Amount
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
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
		MPayment payment = createPayment(MPayment.TENDERTYPE_Check);
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
	public String processPayment() {
		String error = null;
		try {
			for(CollectDetail m_Collect : m_Collects) {
				if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Cash)
						|| m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Account)) {
					payCash(m_Collect.getPayAmt());
				} else if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_Check)) {
					payCheck(m_Collect.getPayAmt(), null, null, m_Collect.getReferenceNo());
				} else if(m_Collect.getTenderType().equals(X_C_Payment.TENDERTYPE_CreditCard)) {
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
					//	Pay from Credit Card
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
			error = e.getMessage();
		}
		//	Error
		return error;
	}  // processPayment
	

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
}