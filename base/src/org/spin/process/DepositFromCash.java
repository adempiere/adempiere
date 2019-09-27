/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpconsultoresyasociados.com               *
 *****************************************************************************/
package org.spin.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBankAccount;
import org.compiere.model.MPayment;
import org.compiere.process.DocAction;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1446">
 * 		@see FR [ 1446 ] Smart Browse for Deposit from cash</a>
 *
 */
public class DepositFromCash extends DepositFromCashAbstract {
	
	/**	Tender Type								*/
	private String 			defaultTenderType			=	MPayment.TENDERTYPE_Account;
	/**	Payments will to complete				*/
	private List<MPayment>	paymentList					= new ArrayList<MPayment>();
	
	@Override
	protected String doIt() throws Exception {
		if(!isSplitDeposits()) {
			if(Util.isEmpty(getDocumentNo())) {
				throw new AdempiereException("@DocumentNo@ @IsMandatory@");
			}
		}
		//	Local to Method
		BigDecimal payAmt = Env.ZERO;
		int bankAccountFromId = 0;
		//	get references from receipt
  	  	MPayment receiptReference = null;
  	  	MPayment inPayment = null;
  	  	MPayment outPayment = null;
  	  	boolean first = true;
  	  	boolean isPaymentCreated = false;
		//	Iterate It
  	  	for(int key : getSelectionKeys()) {
  	  		int paymentId = getSelectionAsInt(key, "CP_C_Payment_ID");
  	  		//	get references from receipt
  	  		receiptReference = new MPayment(getCtx(), paymentId, get_TrxName());
  	  		//	
  	  		if(bankAccountFromId == 0) {
  	  			bankAccountFromId = receiptReference.getC_BankAccount_ID();
  	  		}
  	  		//	New Payment
  	  		if(isSplitDeposits()) {
  	  			inPayment = createPayment(receiptReference.getDocumentNo(), getBankAccountId(), true, 
  	  					receiptReference.getPayAmt(), receiptReference.getTenderType());
  	  			//	Set Reference
  	  			receiptReference.setRef_Payment_ID(inPayment.getC_Payment_ID());
  	  			receiptReference.saveEx();
  	  			isPaymentCreated = true;
  	  		} else {
  	  			//	Create New and reference it
  	  			if(inPayment == null) {
  	  				inPayment = createPayment(getDocumentNo(), getBankAccountId(), true, null, null);
  	  				isPaymentCreated = true;
  	  			}
  	  			//	Set Reference
  	  			receiptReference.setRef_Payment_ID(inPayment.getC_Payment_ID());
  	  			receiptReference.saveEx();
  	  		}
  	  		payAmt = payAmt.add(receiptReference.getPayAmt());
  	  		//	Create out payment
  	  		if(first) {
  	  			first = false;
  	  			outPayment = createPayment(getDocumentNo(), bankAccountFromId, false, null, null);
  	  		}
  	  		//	If is created a in payment then set reference here
  	  		if(isPaymentCreated
  	  				&& outPayment != null) {
  	  			inPayment.setRef_Payment_ID(outPayment.getC_Payment_ID());
  	  			inPayment.saveEx();
  	  		}
		}
  	  	//	
  	  	StringBuffer msg = new StringBuffer();
  	  	//	Create payment
  	  	if(payAmt.compareTo(Env.ZERO) > 0
  	  			&& outPayment != null) {
  	  		//	
  	  		outPayment.setPayAmt(payAmt);
  	  		outPayment.saveEx();
  	  		if(!isSplitDeposits()
  	  				&& inPayment != null) {
  	  			inPayment.setPayAmt(payAmt);
  	  			inPayment.saveEx();
  	  		}
  	  		//	Complete Payments
  	  		for(MPayment payment : paymentList) {
  	  			payment.processIt(DocAction.ACTION_Complete);
  	  			payment.saveEx();
  	  			if(msg.length() > 0) {
  	  				msg.append(", ");
  	  			}
  	  			//	
  	  			msg.append("[" + payment.getDocumentNo() + "]");
  	  		}
  	  	}
  	  	//	
  	  	return "@Created@: (" + paymentList.size() + ") " + msg.toString();
	}
	
	/**
	 * Create a payment
	 * @param bankAccountId
	 * @param isReceipt
	 * @param payAmt
	 * @param tenderType
	 * @return
	 */
	private MPayment createPayment(String documentNo, int bankAccountId, boolean isReceipt, BigDecimal payAmt, String tenderType) {
		MBankAccount bankAccount = MBankAccount.get(getCtx(), bankAccountId);
		MPayment payment = new MPayment(getCtx(), 0, get_TrxName());
	  	//	Set Value
		payment.setC_BPartner_ID(getBPartnerId());
		payment.setC_BankAccount_ID(bankAccountId);
		payment.setIsReceipt(isReceipt);
		payment.setTenderType(tenderType != null? tenderType: defaultTenderType);
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateTrx());
		if(!Util.isEmpty(documentNo)) {
			payment.setDocumentNo(documentNo);
		}
		payment.setC_Currency_ID(bankAccount.getC_Currency_ID());
		payment.setC_Charge_ID(getChargeId());
		payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		if(payAmt != null) {
			payment.setPayAmt(payAmt);
		}
		if(isReceipt) {
			if(getDepositDocumentTypeId() != 0) {
				payment.setC_DocType_ID(getDepositDocumentTypeId());
			}
		} else {
			if(getWithdrawalDocumentTypeId() != 0) {
				payment.setC_DocType_ID(getWithdrawalDocumentTypeId());
			}
		}
		payment.saveEx();
  	  	//	payment list
  	  	paymentList.add(payment);
		return payment;
	}
}
