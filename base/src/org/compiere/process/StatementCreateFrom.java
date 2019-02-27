/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBankAccount;
import org.compiere.model.MBankStatement;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.MPayment;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;


/** Generated Process for (Create From Statement (Process))
 *  @author ADempiere (generated)
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *	@author Victor Perez , victor.perez@e-evolution.com, http://e-evolution.com
 *  @version Release 3.8.0
 */
public class StatementCreateFrom extends StatementCreateFromAbstract {
	@Override
	protected void prepare() {
		super.prepare();
	}

	@Override
	protected String doIt() throws Exception {
		//	Copied from CreateFromStatement old class
		//  fixed values
		if (getRecord_ID() <= 0)
			throw new AdempiereException("@C_BankStatement_ID@ @NotFound@");

		MBankStatement bankStatement = new MBankStatement (Env.getCtx(), getRecord_ID(), get_TrxName());
		//	Get Bank Account
		MBankAccount bankAccount = bankStatement.getBankAccount();
		//	Created
		AtomicInteger created = new AtomicInteger(0);
		//	Total Amount
		AtomicReference<BigDecimal> totalAmt = new AtomicReference<>(BigDecimal.ZERO);
		log.config(bankStatement.toString());
		//  Lines
		getSelectionKeys().stream().forEach( key -> {
			Timestamp dateTransaction 	= getSelectionAsTimestamp(key, "P_DateTrx");  			//  1-DateTrx
			int paymentId 	= getSelectionAsInt(key, "P_C_Payment_ID");				//	2-Payment
			int currencyId 	= getSelectionAsInt(key, "P_C_Currency_ID");			//  3-Currency
			BigDecimal transactionAmount = getSelectionAsBigDecimal(key, "P_ConvertedAmount");	//  5- Converted Amount
			//	Log
			log.fine("Line Date=" + dateTransaction + ", Payment=" + paymentId + ", Currency=" + currencyId + ", Amt=" + transactionAmount);
			MBankStatementLine bankStatementLine = new MBankStatementLine (bankStatement);
			// BF3439695 - Create from for Statement Line picks wrong date
			bankStatementLine.setDateAcct(bankStatement.getStatementDate());
			bankStatementLine.setStatementLineDate(bankStatement.getStatementDate());
			bankStatementLine.setValutaDate(dateTransaction);
			MPayment payment = new MPayment(Env.getCtx(), paymentId, get_TrxName());
			//	Set Payment
			bankStatementLine.setPayment(payment);
			//	Set Reference
			bankStatementLine.setTrxAmt(transactionAmount);
			bankStatementLine.setStmtAmt(transactionAmount);
			bankStatementLine.setC_Currency_ID(bankAccount.getC_Currency_ID());
			//	Save Line with exception
			bankStatementLine.saveEx();
			//	Add to created
			created.updateAndGet(createNo -> createNo + 1);
			//	Add Amount
			totalAmt.updateAndGet(amt -> amt.add(payment.isReceipt() ? payment.getPayAmt() : payment.getPayAmt().negate()));
		});   //  for all rows
		//	Return Created
		return "@Created@ = " + created.get() + " - @PayAmt@ = " + DisplayType.getNumberFormat(DisplayType.Amount).format(totalAmt.get());
	}
}