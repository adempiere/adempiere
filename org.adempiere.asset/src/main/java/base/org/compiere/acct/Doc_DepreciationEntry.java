package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import org.compiere.model.I_A_Asset;
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
	 * 	@param accountSchemas accounting schemata
	 * 	@param rs record
	 * 	@parem trxName trx
	 */
	public Doc_DepreciationEntry (MAcctSchema[] accountSchemas, ResultSet rs, String trxName)
	{
		super(accountSchemas, MDepreciationEntry.class, rs, null, trxName);
	}	//	Doc_A_Depreciation_Entry

	/** Posting Type				*/
	private String postingType = null;
	private int acctSchemaId = 0;
	
	
	protected String loadDocumentDetails ()
	{
		MDepreciationEntry entry = (MDepreciationEntry)getPO();
		postingType = entry.getPostingType();
		acctSchemaId = entry.getC_AcctSchema_ID();
		
		return null;
	}
	
	private DocLine createLine(MDepreciationExp depreciationExp)
	{
		if (!depreciationExp.isProcessed())
			return null;
		DocLine docLine = new DocLine (depreciationExp, this);
		return docLine;
	}
	
	
	public BigDecimal getBalance()
	{
		BigDecimal retValue = Env.ZERO;
		return retValue;
	}   //  getBalance

	/**
	 * Create Facts
	 * @param acctSchema
	 * @return
	 */
	public ArrayList<Fact> createFacts (MAcctSchema acctSchema)
	{
		ArrayList<Fact> facts = new ArrayList<Fact>();
		//	Other Acct Schema
		if (acctSchema.getC_AcctSchema_ID() != acctSchemaId)
			return facts;
		
		//  create Fact Header
		Fact fact = new Fact (this, acctSchema, postingType);

		MDepreciationEntry entry = (MDepreciationEntry)getPO();
		Iterator<MDepreciationExp> it = entry.getLinesIterator(false);
		while(it.hasNext())
		{
			MDepreciationExp depreciationExp = it.next();
			I_A_Asset asset = depreciationExp.getA_Asset();
			DocLine docLine = createLine(depreciationExp);
			BigDecimal expenseAmt = depreciationExp.getExpense();
			//
			MAccount drAccounting = MAccount.getValidCombination(getCtx(), depreciationExp.getDR_Account_ID(), getTrxName());
			MAccount crAccounting = MAccount.getValidCombination(getCtx(), depreciationExp.getCR_Account_ID() ,getTrxName());
			FactLine[] factLines = FactUtil.createSimpleOperation(fact, docLine, drAccounting, crAccounting, acctSchema.getC_Currency_ID(), expenseAmt, false);
			for (FactLine factLine : factLines) {
				StringBuilder description = new StringBuilder(factLine.getDescription() != null ? factLine.getDescription() : "");
				description.append(" ").append(asset.getValue()).append(" ").append(asset.getName());
				factLine.setDescription(description.toString());
				factLine.setC_Project_ID(asset.getC_Project_ID());
				factLine.setC_Activity_ID(asset.getC_Activity_ID());
				factLine.setC_BPartner_ID(asset.getC_BPartner_ID());
				factLine.setLocationFromBPartner(asset.getC_BPartner_Location_ID(), true);
			}
		}
		//
		facts.add(fact);
		return facts;
	}
}

