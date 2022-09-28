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

import org.adempiere.core.domains.models.X_FM_TransactionType_Acct;
import org.compiere.acct.DocLine;
import org.compiere.util.Env;
import org.spin.investment.model.MFMAgreement;
import org.spin.investment.model.MFMBatch;
import org.spin.investment.model.MFMTransaction;
import org.spin.investment.model.MFMTransactionType;

/**
 * Post Batch Document Transaction
 * <pre>
 *  Table:              FM_Batch (??)
 *  Document Types:     FM_Batch
 * </pre>
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *      <li> FR [ 1678 ] Add Fact Accounting for Agreement Batch
 *		@see https://github.com/adempiere/adempiere/issues/1678
 */
public class DocLine_FMBatch extends DocLine {
	
	/**
	 *  Constructor
	 *  @param Transaction from batch line
	 *  @param doc header
	 */
	public DocLine_FMBatch (MFMTransaction transaction, Doc_FMBatch doc) {
		super (transaction, doc);
		MFMTransactionType transactionType = MFMTransactionType.getById(getCtx(), transaction.getFM_TransactionType_ID());
		//
		transactionTypeId = transactionType.getFM_TransactionType_ID();
		batchId = transaction.getFM_Batch_ID();
		MFMAgreement agreement = (MFMAgreement) ((MFMBatch) doc.getPO()).getFM_Account().getFM_Agreement();
		bPartnerId = agreement.getC_BPartner_ID();
		amount = transaction.getAmount();
		//	Reference
		orgId = transaction.getAD_Org_ID();
		//	
		setAmount(amount);
	}   //  DocLine_Payroll

	//  References
	private int batchId  = 0;
	private int transactionTypeId  = 0;
	private int bPartnerId  = 0;
	private int orgId = 0;
	private BigDecimal amount  = Env.ZERO;
	
	public int getFM_Batch_ID(){
		return batchId;
	}
	
	public int getFM_TransactionType_ID(){
		return transactionTypeId;
	}
	
	public int getC_BPartner_ID(){
		return bPartnerId;
	}
	
	public int getAD_Org_ID() {
		return orgId;
	}
	
	public BigDecimal getAmount()	{
		return amount;
	}
	
	/**
	 * Get Transaction Type Account
	 * @param acctSchemaId
	 * @return
	 */
	public X_FM_TransactionType_Acct getTransactionTypeAcct(int acctSchemaId) {
		MFMTransactionType transactionType = MFMTransactionType.getById(getCtx(), getFM_TransactionType_ID());
		if(transactionType != null) {
			return transactionType.getTransactionTypeAcct(acctSchemaId);
		}
		//	Default null
		return null;
	}

}   //  DocLine_FMBatch
