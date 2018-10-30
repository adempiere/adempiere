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
 * Copyright (C) 2003-2008 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.process;


import java.sql.Timestamp;

import org.compiere.model.MBankAccount;
import org.compiere.model.MBankStatement;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.MPayment;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
 
/**
 *  Bank Transfer. Generate two Payments entry
 *  
 *  For Bank Transfer From Bank Account "A" 
 *                 
 *	@author victor.perez@e-evoltuion.com
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
*		<a href="https://github.com/adempiere/adempiere/issues/850">
* 		@see FR [ 850 ] Bank transfer does not have document no to</a>
 *	
 **/
public class BankTransfer extends BankTransferAbstract {
	
	/**
	 *  Perform process.
	 *  @return Message (translated text)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception {
		return generateBankTransfer();
	}	//	doIt
	

	/**
	 * Generate BankTransfer()
	 *
	 */
	private String generateBankTransfer() {
		Timestamp statementDate = getStatementDate();
		Timestamp dateAcct = getDateAcct();
		String documentNoTo = getDocumentNoTo();
		if(documentNoTo == null
				|| documentNoTo.trim().length() == 0) {
			documentNoTo = getDocumentNo();
		}
		//	Login Date
		if (statementDate == null) {
			statementDate = Env.getContextAsDate(getCtx(), "#Date");
		}
		if (statementDate == null) {
			statementDate = new Timestamp(System.currentTimeMillis());			
		}
		//	
		if (dateAcct == null) {
			dateAcct = statementDate;
		}

		MBankAccount mBankFrom = MBankAccount.get(getCtx(), getCBankAccountId());
		MBankAccount mBankTo = MBankAccount.get(getCtx(), getBankAccountToId());
		
		MPayment paymentBankFrom = new MPayment(getCtx(), 0 ,  get_TrxName());
		paymentBankFrom.setC_BankAccount_ID(mBankFrom.getC_BankAccount_ID());
		paymentBankFrom.setDocumentNo(getDocumentNo());
		paymentBankFrom.setDateAcct(dateAcct);
		paymentBankFrom.setDateTrx(statementDate);
		paymentBankFrom.setTenderType(MPayment.TENDERTYPE_DirectDeposit);
		paymentBankFrom.setDescription(getDescription());
		paymentBankFrom.setC_BPartner_ID (getBPartnerId());
		paymentBankFrom.setC_Currency_ID(getCurrencyId());
		if(getConversionTypeId() > 0) {
			paymentBankFrom.setC_ConversionType_ID(getConversionTypeId());	
		}
		paymentBankFrom.setPayAmt(getAmount());
		paymentBankFrom.setOverUnderAmt(Env.ZERO);
		paymentBankFrom.setC_DocType_ID(false);
		paymentBankFrom.setC_Charge_ID(getChargeId());
		paymentBankFrom.saveEx();
		//	
		MPayment paymentBankTo = new MPayment(getCtx(), 0 ,  get_TrxName());
		paymentBankTo.setC_BankAccount_ID(mBankTo.getC_BankAccount_ID());
		paymentBankTo.setDocumentNo(documentNoTo);
		paymentBankTo.setDateAcct(dateAcct);
		paymentBankTo.setDateTrx(statementDate);
		paymentBankTo.setTenderType(MPayment.TENDERTYPE_DirectDeposit);
		paymentBankTo.setDescription(getDescription());
		paymentBankTo.setC_BPartner_ID (getBPartnerId());
		paymentBankTo.setC_Currency_ID(getCurrencyId());
		if(getConversionTypeId() > 0) {
			paymentBankFrom.setC_ConversionType_ID(getConversionTypeId());	
		}
		paymentBankTo.setPayAmt(getAmount());
		paymentBankTo.setOverUnderAmt(Env.ZERO);
		paymentBankTo.setC_DocType_ID(true);
		paymentBankTo.setC_Charge_ID(getChargeId());
		paymentBankTo.saveEx();

		paymentBankFrom.setRelatedPayment_ID(paymentBankTo.getC_Payment_ID());
		paymentBankFrom.saveEx();
		paymentBankFrom.processIt(MPayment.DOCACTION_Complete);
		paymentBankFrom.saveEx();
		//	Add to current bank statement for account
		if(isAutoReconciled()) {
			MBankStatementLine bsl = MBankStatement.addPayment(paymentBankFrom);
			if(bsl != null) {
				addLog("@C_Payment_ID@: " + paymentBankFrom.getDocumentNo()
						+ " @Added@ @to@ [@AccountNo@ " + paymentBankFrom.getC_BankAccount().getAccountNo()
						+ " @C_BankStatement_ID@ " + bsl.getC_BankStatement().getName() + "]");
			}
		}
		paymentBankTo.setRelatedPayment_ID(paymentBankFrom.getC_Payment_ID());
		paymentBankTo.saveEx();
		paymentBankTo.processIt(MPayment.DOCACTION_Complete);
		paymentBankTo.saveEx();
		//	Add to current bank statement for account
		if(isAutoReconciled()) {
			MBankStatementLine bsl = MBankStatement.addPayment(paymentBankTo);
			if(bsl != null) {
				addLog("@C_Payment_ID@: " + paymentBankTo.getDocumentNo() 
						+ " @Added@ @to@ [@AccountNo@ " + paymentBankTo.getC_BankAccount().getAccountNo() 
						+ " @C_BankStatement_ID@ " + bsl.getC_BankStatement().getName() + "]");
			}
		}
		//	Return
		return "@Created@ (1) @From@ " + mBankFrom.getAccountNo()+ " @To@ " + mBankTo.getAccountNo() + " @Amt@ " + DisplayType.getNumberFormat(DisplayType.Amount).format(getAmount());
	}  //  createCashLines
	
}	//	ImmediateBankTransfer
