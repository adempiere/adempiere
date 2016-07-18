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

import org.compiere.model.MBankAccount;
import org.compiere.model.MBankStatement;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.MPayment;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

/** Generated Process for (Create From Statement (Process))
 *  @author ADempiere (generated) 
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
		MBankStatement bs = new MBankStatement (Env.getCtx(), getRecord_ID(), get_TrxName());
		//	Get Bank Account
		MBankAccount bankAccount = bs.getBankAccount();
		//	Created
		int created = 0;
		//	Total Amount
		BigDecimal totalAmt = Env.ZERO;
		//	
		log.config(bs.toString());
		//  Lines
		for (Integer key : getSelectionKeys()) {
			Timestamp trxDate 	= getSelectionAsTimestamp(key, "P_DateTrx");  			//  1-DateTrx
			int C_Payment_ID 	= getSelectionAsInt(key, "P_C_Payment_ID");				//	2-Payment
			int C_Currency_ID 	= getSelectionAsInt(key, "P_C_Currency_ID");			//  3-Currency
			BigDecimal TrxAmt 	= getSelectionAsBigDecimal(key, "P_ConvertedAmount");	//  5- Converted Amount
			//	Log
			log.fine("Line Date=" + trxDate
				+ ", Payment=" + C_Payment_ID + ", Currency=" + C_Currency_ID + ", Amt=" + TrxAmt);
			//	
			MBankStatementLine bsl = new MBankStatementLine (bs);
			// BF3439695 - Create from for Statement Line picks wrong date
			bsl.setDateAcct(bs.getStatementDate());
			bsl.setStatementLineDate(bs.getStatementDate());
			bsl.setValutaDate(trxDate);
			MPayment payment = new MPayment(Env.getCtx(), C_Payment_ID, get_TrxName());
			//	Set Payment
			bsl.setPayment(payment);
			//	Set Reference
			bsl.setTrxAmt(TrxAmt);
			bsl.setStmtAmt(TrxAmt);
			bsl.setC_Currency_ID(bankAccount.getC_Currency_ID()); 
			//	Save Line with exception
			bsl.saveEx();
			//	Add to created
			created++;
			//	Add Amount
			totalAmt = totalAmt.add(payment.getPayAmt());
		}   //  for all rows
		//	Return Created
		return "@Created@ = " + created 
				+ " - @PayAmt@ = " + DisplayType.getNumberFormat(DisplayType.Amount).format(totalAmt);
	}
}