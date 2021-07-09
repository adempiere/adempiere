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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBankAccount;
import org.compiere.model.MBankStatement;
import org.compiere.model.MPayment;
import org.compiere.process.DocAction;
import org.compiere.util.Util;

/**
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1446">
 * 		@see FR [ 1446 ] Smart Browse for Deposit from cash</a>
 *
 */
public class DepositFromCash extends DepositFromCashAbstract {
	
	/**	Source with withdrawal reference	*/
	Map<Integer, Integer> withdrawalLinkPayments = new HashMap<Integer, Integer>();
	/**	Source with deposit reference	*/
	Map<Integer, Integer> depositLinkPayments = new HashMap<Integer, Integer>();
	/**	Deposits	*/
	Map<String, MPayment> payments = new HashMap<String, MPayment>();
	/**	Created	*/
	AtomicInteger created = new AtomicInteger();
	
	@Override
	protected void prepare() {
		super.prepare();
		if(Util.isEmpty(getTenderType())) {
			setTenderType(MPayment.TENDERTYPE_DirectDeposit);
		}
	}
	
	@Override
	protected String doIt() throws Exception {
		if(!isSplitDeposits()) {
			if(Util.isEmpty(getDocumentNo())) {
				throw new AdempiereException("@DocumentNo@ @IsMandatory@");
			}
		}
		//	Process
  	  	getSelectionKeys().forEach(key -> {
  	  		int paymentId = getSelectionAsInt(key, "CP_C_Payment_ID");
  	  		//	get references from receipt
	  		MPayment sourcePayment = new MPayment(getCtx(), paymentId, get_TrxName());
	  		//	
	  		String paymentKey = getKey(String.valueOf(sourcePayment.getC_Payment_ID()), sourcePayment.getC_BankAccount_ID(), sourcePayment.isReceipt(), sourcePayment.getC_Currency_ID(), sourcePayment.getC_ConversionType_ID());
	  		payments.put(paymentKey, sourcePayment);
	  		String documentNo = getDocumentNo();
	  		if(isSplitDeposits()) {
	  			documentNo = sourcePayment.getDocumentNo();
	  		}
	  		MPayment bankDeposit = addPayment(documentNo, getBankAccountId(), true, sourcePayment.getPayAmt(), sourcePayment.getTenderType(), sourcePayment.getC_Currency_ID(), sourcePayment.getC_ConversionType_ID());
	  		//	Set Reference
	  		depositLinkPayments.put(sourcePayment.getC_Payment_ID(), bankDeposit.getC_Payment_ID());
	  		//	Create withdrawal
	  		MPayment cashWithdrawal = addPayment(getDocumentNo(), sourcePayment.getC_BankAccount_ID(), false, sourcePayment.getPayAmt(), sourcePayment.getTenderType(), sourcePayment.getC_Currency_ID(), sourcePayment.getC_ConversionType_ID());
	  		if(sourcePayment.getC_POS_ID() > 0) {
	  			cashWithdrawal.setC_POS_ID(sourcePayment.getC_POS_ID());
	  			cashWithdrawal.saveEx();
	  		}
	  		//	Add references
	  		withdrawalLinkPayments.put(sourcePayment.getC_Payment_ID(), cashWithdrawal.getC_Payment_ID());
  	  	});
  	  	//	
  	  	StringBuffer msg = new StringBuffer();
  	  	payments.entrySet().forEach(entry -> {
  	  		MPayment payment = entry.getValue();
  	  		//	Link to Withdrawal
  	  		Integer referenceId = withdrawalLinkPayments.get(payment.getC_Payment_ID());
	  	  	Optional.ofNullable(referenceId).ifPresent(theReferenceId -> {
	            payment.setRef_Payment_ID(theReferenceId);
	            payment.saveEx();               
	        });
  	  		//	Link to deposit
  	  		Integer relatedId = depositLinkPayments.get(payment.getC_Payment_ID());
	  	  	Optional.ofNullable(relatedId).ifPresent(theRelatedId -> {
	  	  	payment.setRelatedPayment_ID(theRelatedId);
  			payment.saveEx();
	        });
  	  		//	Complete
  	  		if(payment.getDocStatus().equals(MPayment.DOCSTATUS_Drafted)) {
	  	  		payment.processIt(DocAction.ACTION_Complete);
				payment.saveEx();
				if(msg.length() > 0) {
					msg.append(", ");
				}
				//	
				msg.append("[" + payment.getDocumentNo() + "]");
				//	Count it
				created.addAndGet(1);
  	  		}
  	  		//	Auto Reconcile
  	  		if(isAutoReconciled()) {
  	  			MBankStatement.addPayment(payment);
  	  		}
  	  	});
  	  	//	
  	  	return "@Created@: (" + created.get() + ") " + msg.toString();
	}
	
	/**
	 * Add Payment to list
	 * @param documentNo
	 * @param bankAccountId
	 * @param isReceipt
	 * @param paymentAmount
	 * @param tenderType
	 * @param currencyId
	 * @param conversionTypeId
	 * @return
	 */
	private MPayment addPayment(String documentNo, int bankAccountId, boolean isReceipt, BigDecimal paymentAmount, String tenderType, int currencyId, int conversionTypeId) {
		String key = getKey(documentNo, bankAccountId, isReceipt, currencyId, conversionTypeId);
		MPayment payment = payments.get(key);
		if(payment != null) {
			BigDecimal amount = payment.getPayAmt().add(paymentAmount);
			payment.setPayAmt(amount);
			payment.saveEx();
		} else {
			payment = createPayment(documentNo, bankAccountId, isReceipt, paymentAmount, tenderType, currencyId, conversionTypeId);
		}
		//	Set payment
		payments.put(key, payment);
		return payment;
	}
	
	/**
	 * Get key for search
	 * @param documentNo
	 * @param bankAccountId
	 * @param isReceipt
	 * @param currencyId
	 * @param conversionTypeId
	 * @return
	 */
	private String getKey(String documentNo, int bankAccountId, boolean isReceipt, int currencyId, int conversionTypeId) {
		return documentNo + "|" + bankAccountId + "|" + isReceipt + "|" + currencyId + "|" + conversionTypeId;
	}
	
	
	/**
	 * Create a payment
	 * @param documentNo
	 * @param bankAccountId
	 * @param isReceipt
	 * @param paymentAmount
	 * @param tenderType
	 * @param currencyId
	 * @param conversionTypeId
	 * @return
	 */
	private MPayment createPayment(String documentNo, int bankAccountId, boolean isReceipt, BigDecimal paymentAmount, String tenderType, int currencyId, int conversionTypeId) {
		MBankAccount bankAccount = MBankAccount.get(getCtx(), bankAccountId);
		MPayment payment = new MPayment(getCtx(), 0, get_TrxName());
	  	//	Set Value
		payment.setC_BPartner_ID(getBPartnerId());
		payment.setC_BankAccount_ID(bankAccountId);
		payment.setIsReceipt(isReceipt);
		payment.setTenderType(tenderType != null? tenderType: getTenderType());
		payment.setDateTrx(getDateTrx());
		payment.setDateAcct(getDateTrx());
		if(!Util.isEmpty(documentNo)) {
			payment.setDocumentNo(documentNo);
		}
		if(currencyId != 0) {
			payment.setC_Currency_ID(currencyId);
		} else {
			payment.setC_Currency_ID(bankAccount.getC_Currency_ID());
		}
		//	Set conversion type for payment
		if(conversionTypeId != 0) {
			payment.setC_ConversionType_ID(conversionTypeId);
		}
		payment.setC_Charge_ID(getChargeId());
		payment.setDocStatus(MPayment.DOCSTATUS_Drafted);
		if(paymentAmount != null) {
			payment.setPayAmt(paymentAmount);
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
		return payment;
	}
}
