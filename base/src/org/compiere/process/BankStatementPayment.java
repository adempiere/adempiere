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
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.adempiere.core.domains.models.X_I_BankStatement;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBank;
import org.compiere.model.MBankStatement;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPayment;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 *	Create Payment from Bank Statement Info
 *	
 *  @author Jorg Janke
 *  @version $Id: BankStatementPayment.java,v 1.3 2006/07/30 00:51:01 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *  Add support to unidentified payments
 *  https://github.com/adempiere/adempiere/issues/2785
 *  @author Victor Perez, victor.perez@e-evolution.com , http://e-evolution.com
 *  [Bug Report] Transaction blocking error when creating payment from the account statement and immediate posting #3429
 *  https://github.com/adempiere/adempiere/issues/3429
 */
public class BankStatementPayment extends BankStatementPaymentAbstract {

	/**
	 *  Perform process.
	 *  @return Message 
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception {
		int tableId = getTable_ID();
		int recordId = getRecord_ID();
		log.info ("Table_ID=" + tableId + ", Record_ID=" + recordId);
		if(isSelection()) {
			String transactionType = getParameterAsString("TrxType");
			if(Util.isEmpty(transactionType)) {
				throw new AdempiereException("@TrxType@ @NotFound@");
			}
			//	
			int chargeId = getParameterAsInt("C_Charge_ID");
			int bPartnerId = getParameterAsInt("C_BPartner_ID");
			int created = 0;
			for(int key : getSelectionKeys()) {
				int bankStatementLineId = getSelectionAsInt(key, "BSL_C_BankStatementLine_ID");
				MBankStatementLine bankStatementLine = new MBankStatementLine(getCtx(), bankStatementLineId, get_TrxName());
				if(transactionType.equals("B")) {
					MBank bank = MBank.get(getCtx(), bankStatementLine.getParent().getBankAccount().getC_Bank_ID());
					if(bank.getC_BPartner_ID() == 0) {
						throw new AdempiereException("@C_Bank_ID@ @C_BPartner_ID@ @NotFound@");
					}
					bPartnerId = bank.getC_BPartner_ID();
				} else if(transactionType.equals("U")) {
					bPartnerId = MOrgInfo.get(getCtx(), bankStatementLine.getAD_Org_ID(), get_TrxName()).getUnidentifiedBPartner_ID();
					if(bPartnerId == 0) {
						throw new AdempiereException("@AD_Org_ID@ @UnidentifiedBPartner_ID@ @NotFound@");
					}
				}
				if(bPartnerId != 0) {
					bankStatementLine.setC_BPartner_ID(bPartnerId);
				}
				if(chargeId != 0) {
					bankStatementLine.setC_Charge_ID(chargeId);
				}
				createPayment(bankStatementLine);
				created++;
			}
			return "@Created@: " + created;
		} else {
			if (tableId == X_I_BankStatement.Table_ID) {
				return createPayment (new X_I_BankStatement(getCtx(), recordId, get_TrxName()));
			} else if (tableId == MBankStatementLine.Table_ID) {
				return createPayment (new MBankStatementLine(getCtx(), recordId, get_TrxName()));
			}
		}
		return "Ok";
	}	//	doIt

	/**
	 * 	Create Payment for Import
	 *	@param importBankStatement import bank statement
	 *	@return Message
	 *  @throws Exception if not successful
	 */
	private String createPayment (X_I_BankStatement importBankStatement) throws Exception {
		if (importBankStatement == null || importBankStatement.getC_Payment_ID() != 0)
			return "--";
		log.fine(importBankStatement.toString());
		if (importBankStatement.getC_Invoice_ID() == 0 && importBankStatement.getC_BPartner_ID() == 0)
			throw new AdempiereUserError ("@NotFound@ @C_Invoice_ID@ / @C_BPartner_ID@");
		if (importBankStatement.getC_BankAccount_ID() == 0)
			throw new AdempiereUserError ("@NotFound@ @C_BankAccount_ID@");
		//
		String documentNo = importBankStatement.getReferenceNo();
		if(Util.isEmpty(documentNo)) {
			documentNo = importBankStatement.getEftReference();
		}
		String checkNo = importBankStatement.getEftCheckNo();
		MPayment payment = createPayment (importBankStatement.getC_Invoice_ID(), importBankStatement.getC_BPartner_ID(),
			importBankStatement.getC_Currency_ID(), importBankStatement.getStmtAmt(), importBankStatement.getTrxAmt(), 
			importBankStatement.getC_BankAccount_ID(), importBankStatement.getStatementLineDate() == null ? importBankStatement.getStatementDate() : importBankStatement.getStatementLineDate(), 
			importBankStatement.getDateAcct(), importBankStatement.getDescription(), importBankStatement.getAD_Org_ID(), importBankStatement.getC_Charge_ID(), false, documentNo, checkNo);
		
		importBankStatement.setC_Payment_ID(payment.getC_Payment_ID());
		importBankStatement.setC_Currency_ID (payment.getC_Currency_ID());
		importBankStatement.setTrxAmt(payment.getPayAmt(true));
		importBankStatement.saveEx();
		//
		String retString = "@C_Payment_ID@ = " + payment.getDocumentNo();
		if (payment.getOverUnderAmt().signum() != 0)
			retString += " - @OverUnderAmt@=" + payment.getOverUnderAmt();
		return retString;
	}	//	createPayment - Import
	
	/**
	 * 	Create Payment for BankStatement
	 *	@param bankStatementLine bank statement Line
	 *	@return Message
	 *  @throws Exception if not successful
	 */
	private String createPayment (MBankStatementLine bankStatementLine) throws Exception {
		if (bankStatementLine == null || bankStatementLine.getC_Payment_ID() != 0)
			return "--";
		log.fine(bankStatementLine.toString());
		if (bankStatementLine.getC_Invoice_ID() == 0 && bankStatementLine.getC_BPartner_ID() == 0)
			throw new AdempiereUserError ("@NotFound@ @C_Invoice_ID@ / @C_BPartner_ID@");
		//
		MBankStatement bankStatement = new MBankStatement (getCtx(), bankStatementLine.getC_BankStatement_ID(), get_TrxName());
		String documentNo = bankStatementLine.getReferenceNo();
		if(Util.isEmpty(documentNo)) {
			documentNo = bankStatementLine.getEftReference();
		}
		String checkNo = bankStatementLine.getEftCheckNo();
		boolean isUnidentified = false;
		if(!Util.isEmpty(getParameterAsString("TrxType"))
				&& getParameterAsString("TrxType").equals("U")) {
			isUnidentified = true;
		}
		//
		MPayment payment = createPayment (bankStatementLine.getC_Invoice_ID(), bankStatementLine.getC_BPartner_ID(),
			bankStatementLine.getC_Currency_ID(), bankStatementLine.getStmtAmt(), bankStatementLine.getTrxAmt(), 
			bankStatement.getC_BankAccount_ID(), bankStatementLine.getStatementLineDate(), bankStatementLine.getDateAcct(),
			bankStatementLine.getDescription(), bankStatementLine.getAD_Org_ID(), bankStatementLine.getC_Charge_ID(), isUnidentified, documentNo, checkNo);
		//	update statement
		bankStatementLine.setPayment(payment);
		if(isUnidentified) {
			bankStatementLine.setC_Charge_ID(-1);
		}
		bankStatementLine.saveEx();
		//
		String retString = "@C_Payment_ID@ = " + payment.getDocumentNo();
		if (payment.getOverUnderAmt().signum() != 0) {
			retString += " - @OverUnderAmt@=" + payment.getOverUnderAmt();
		}
		return retString;
	}	//	createPayment


	/**
	 * 	Create actual Payment
	 *	@param invoiceId invoice
	 *	@param bPartnerId partner ignored when invoice exists
	 *	@param currencyId currency
	 *	@param statementAmount statement amount
	 *	@param transactionAmount transaction amt
	 *	@param bankAccountId bank account
	 *	@param transactionDate transaction date
	 *	@param accountingDate	accounting date
	 *	@param description description
	 *	@param organizationId org
	 *	@param chargeId charge
	 *	@param isUnidentified unidentified payment
	 *	@param documentNo
	 *	@param checkNo
	 *	@return payment
	 */
	private MPayment createPayment (int invoiceId, int bPartnerId, 
		int currencyId, BigDecimal statementAmount, BigDecimal transactionAmount,
		int bankAccountId, Timestamp transactionDate, Timestamp accountingDate, 
		String description, int organizationId, int chargeId, boolean isUnidentified, String documentNo, String checkNo) {
		//	Trx Amount = Payment overwrites Statement Amount if defined
		BigDecimal paymentAmount = transactionAmount;
		if (paymentAmount == null || Env.ZERO.compareTo(paymentAmount) == 0)
			paymentAmount = statementAmount;
		if (invoiceId == 0
			&& (paymentAmount == null || Env.ZERO.compareTo(paymentAmount) == 0)) {
			throw new IllegalStateException ("@PayAmt@ = 0");
		}
		if (paymentAmount == null) {
			paymentAmount = Env.ZERO;
		}
		MOrgInfo organizationInfo = MOrgInfo.get(getCtx(), organizationId, get_TrxName());
		//
		MPayment payment = new MPayment (getCtx(), 0, get_TrxName());
		payment.setAD_Org_ID(organizationId);
		payment.setC_BankAccount_ID(bankAccountId);
		payment.setTenderType(MPayment.TENDERTYPE_Check);
		if (transactionDate != null) {
			payment.setDateTrx(transactionDate);
		} else if (accountingDate != null) {
			payment.setDateTrx(accountingDate);
		}
		if (accountingDate != null) {
			payment.setDateAcct(accountingDate);
		} else {
			payment.setDateAcct(payment.getDateTrx());
		}
		payment.setDescription(description);
		//
		if (invoiceId != 0) {
			MInvoice invoice = new MInvoice (getCtx(), invoiceId, get_TrxName());
			payment.setC_DocType_ID(invoice.isSOTrx());		//	Receipt
			payment.setC_Invoice_ID(invoice.getC_Invoice_ID());
			payment.setC_BPartner_ID (invoice.getC_BPartner_ID());
			if (paymentAmount.signum() != 0) {	//	explicit Amount
				payment.setC_Currency_ID(currencyId);
				if (invoice.isSOTrx())
					payment.setPayAmt(paymentAmount);
				else	//	payment is likely to be negative
					payment.setPayAmt(paymentAmount.negate());
				payment.setOverUnderAmt(invoice.getGrandTotal(true).subtract(payment.getPayAmt()));
			}
			else {	//	set Pay Amount from Invoice
				payment.setC_Currency_ID(invoice.getC_Currency_ID());
				payment.setPayAmt(invoice.getGrandTotal(true));
			}
		} else if (bPartnerId != 0) {
			payment.setC_BPartner_ID(bPartnerId);
			payment.setC_Currency_ID(currencyId);
			if(chargeId != 0
					&& !isUnidentified) {
				payment.setC_Charge_ID(chargeId);
			}
			boolean isReceipt = paymentAmount.signum() > 0;
			payment.setPayAmt(paymentAmount.abs());
			//	Get from organization
			if(organizationInfo.getUnidentifiedDocumentType(isReceipt) != 0) {
				payment.setC_DocType_ID(organizationInfo.getUnidentifiedDocumentType(isReceipt));
			} else {
				payment.setC_DocType_ID(isReceipt);
			}
		} else {
			throw new AdempiereException("@C_Invoice_ID@ / @C_BPartner_ID@ @NotFound@");
		}
		if(!Util.isEmpty(documentNo)) {
			payment.setDocumentNo(documentNo);
		}
		if(!Util.isEmpty(checkNo)) {
			payment.setCheckNo(checkNo);
		}
		payment.setIsUnidentifiedPayment(isUnidentified);
		if(isUnidentified) {
			payment.addDescription(Msg.parseTranslation(getCtx(), "@UnidentifiedPayment@"));
		}
		payment.saveEx();
		//
		payment.processIt(MPayment.DOCACTION_Complete);
		payment.saveEx();
		return payment;		
	}	//	createPayment

}	//	BankStatementPayment
