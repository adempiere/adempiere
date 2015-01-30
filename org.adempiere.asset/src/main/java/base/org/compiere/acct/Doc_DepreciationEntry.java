package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MDepreciationEntry;
import org.compiere.model.MDepreciationExp;
import org.compiere.util.Env;


/**
 *  @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *  @version  $Id$
 *
 */
public class Doc_DepreciationEntry extends Doc
{
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@parem trxName trx
	 */
	public Doc_DepreciationEntry (MAcctSchema[] as, ResultSet rs, String trxName)
	{
		super(as, MDepreciationEntry.class, rs, null, trxName);
	}	//	Doc_A_Depreciation_Entry

	/** Posting Type				*/
	private String						m_PostingType = null;
	private int							m_C_AcctSchema_ID = 0;
	
	
	protected String loadDocumentDetails ()
	{
		MDepreciationEntry entry = (MDepreciationEntry)getPO();
		m_PostingType = entry.getPostingType();
		m_C_AcctSchema_ID = entry.getC_AcctSchema_ID();
		
		return null;
	}
	
	private DocLine createLine(MDepreciationExp depexp)
	{
		if (!depexp.isProcessed())
			return null;
		DocLine docLine = new DocLine (depexp, this);
		return docLine;
	}
	
	
	public BigDecimal getBalance()
	{
		BigDecimal retValue = Env.ZERO;
		return retValue;
	}   //  getBalance

	
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		ArrayList<Fact> facts = new ArrayList<Fact>();
		//	Other Acct Schema
		if (as.getC_AcctSchema_ID() != m_C_AcctSchema_ID)
			return facts;
		
		//  create Fact Header
		Fact fact = new Fact (this, as, m_PostingType);

		MDepreciationEntry entry = (MDepreciationEntry)getPO();
		Iterator<MDepreciationExp> it = entry.getLinesIterator(false);
		while(it.hasNext())
		{
			MDepreciationExp depexp = it.next();
			DocLine line = createLine(depexp);
			BigDecimal expenseAmt = depexp.getExpense();
			//
			MAccount dr_acct = MAccount.get(getCtx(), depexp.getDR_Account_ID());
			MAccount cr_acct = MAccount.get(getCtx(), depexp.getCR_Account_ID());
			FactUtil.createSimpleOperation(fact, line, dr_acct, cr_acct, as.getC_Currency_ID(), expenseAmt, false);
		}
		//
		facts.add(fact);
		return facts;
	}
}

