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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.investment.acct;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.core.domains.models.I_FM_Transaction;
import org.adempiere.core.domains.models.X_FM_TransactionType_Acct;
import org.compiere.acct.Doc;
import org.compiere.acct.DocLine;
import org.compiere.acct.Fact;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.spin.investment.model.MFMAccount;
import org.spin.investment.model.MFMAgreement;
import org.spin.investment.model.MFMBatch;
import org.spin.investment.model.MFMTransaction;

/**
 * Post Batch Document
 * <pre>
 *  Table:              FM_Batch (??)
 *  Document Types:     FM_Batch
 * </pre>
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1678 ] Add Fact Accounting for Agreement Batch
 *		@see https://github.com/adempiere/adempiere/issues/1678
 */
public class   Doc_FMBatch extends Doc {
	/**	Batch	*/
	private MFMBatch batch = null;
	/**	Account	*/
	private MFMAccount account = null;
	/**	Agreement	*/
	private MFMAgreement agreement = null;
	/** Batch **/
	public static final String	DOCTYPE_Financial_Batch			= "FMB";
	/**
	 *  Constructor
	 * 	@param ass accounting schema
	 * 	@param rs record
	 * 	@parem trxName trx
	 */
	public Doc_FMBatch (MAcctSchema[] ass, ResultSet rs, String trxName) {
		super(ass, MFMBatch.class, rs, DOCTYPE_Financial_Batch, trxName);
	}	//	Doc_Payroll

	@Override
	protected String loadDocumentDetails () {
		batch = (MFMBatch)getPO();
		account = (MFMAccount) batch.getFM_Account();
		agreement = (MFMAgreement) account.getFM_Agreement();
		setDateDoc(getDateAcct());
		//	Contained Objects
		p_lines = loadLines(batch);
		log.fine("Lines=" + p_lines.length);
		return null;
	}   //  loadDocumentDetails

	@Override
	public Timestamp getDateAcct() {
		return batch.getDateAcct();
	}

	/**
	 *	Load Batch Transaction
	 *	@param Batch
	 *  @return DocLine Array
	 */
	private DocLine[] loadLines(MFMBatch batch) {
		ArrayList<DocLine> list = new ArrayList<DocLine>();
		//	Get Movements
		List<MFMTransaction> transactions = new Query(getCtx(), I_FM_Transaction.Table_Name, 
				"FM_Transaction.FM_Batch_ID = ? "
				+ "AND FM_Transaction.Amount <> 0 "
				+ "AND EXISTS(SELECT 1 FROM FM_TransactionType tt "
				+ "					WHERE tt.FM_TransactionType_ID = FM_Transaction.FM_TransactionType_ID)", getTrxName())
			.setParameters(batch.getFM_Batch_ID())
			.list();
		//	
		for (MFMTransaction transaction : transactions) {
			DocLine_FMBatch docLine = new DocLine_FMBatch(transaction, this);
			//
			log.fine(docLine.toString());
			list.add(docLine);
		}
		//	Convert to array
		DocLine[] dls = new DocLine[list.size()];
		list.toArray(dls);
		return dls;
	}	//	loadLines

	@Override
	public BigDecimal getBalance() {
		BigDecimal retValue = Env.ZERO;
		return retValue; 
	}   //  getBalance

	@Override
	public ArrayList<Fact> createFacts (MAcctSchema as) {
		Fact fact = new Fact(this, as, Fact.POST_Actual);		
		for(DocLine line : p_lines) {
			DocLine_FMBatch batchDocLine = (DocLine_FMBatch) line;
			//	Get Source Amount
			BigDecimal sumAmount = line.getAmtSource();
			// round amount according to currency
			sumAmount = sumAmount.setScale(as.getStdPrecision(), RoundingMode.HALF_UP);
			//	Get Concept Account
			X_FM_TransactionType_Acct transactionTypeAcct = batchDocLine.getTransactionTypeAcct(as.getC_AcctSchema_ID());
			if(transactionTypeAcct == null) {
				continue;
			}
			//	
			if (!agreement.isSOTrx()) {
				//	DR
				MAccount accountBPD = MAccount.getValidCombination (getCtx(), transactionTypeAcct.getFM_Expense_Acct(), getTrxName());
				fact.createLine(line, accountBPD, account.getC_Currency_ID(), sumAmount, null);
				//	CR
				MAccount accountBPC = MAccount.getValidCombination (getCtx(), transactionTypeAcct.getFM_Revenue_Acct(), getTrxName());
				fact.createLine(line,accountBPC, account.getC_Currency_ID(),null, sumAmount);
			} else {
				//	DR
				MAccount accountBPD = MAccount.getValidCombination (getCtx(), transactionTypeAcct.getFM_Expense_Acct(), getTrxName());
				fact.createLine(line, accountBPD, account.getC_Currency_ID(), sumAmount, null);
				//	CR
				MAccount accountBPC = MAccount.getValidCombination (getCtx(), transactionTypeAcct.getFM_Revenue_Acct(), getTrxName());
				fact.createLine(line,accountBPC, account.getC_Currency_ID(),null, sumAmount);
			}
		}
		ArrayList<Fact> facts = new ArrayList<Fact>();
		facts.add(fact);
		return facts;
	}
}   //  Doc_FMBatch
