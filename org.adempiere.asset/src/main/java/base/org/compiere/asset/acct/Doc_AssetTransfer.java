package org.compiere.asset.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.acct.Doc;
import org.compiere.acct.Fact;
import org.compiere.asset.model.MAssetTransfer;
import org.compiere.asset.model.MDepreciationWorkfile;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MDocType;
import org.compiere.util.Env;


/**
 * @author Anca Bradau www.arhipac.ro
 *
 */
public class Doc_AssetTransfer extends Doc 
{

	public Doc_AssetTransfer (MAcctSchema[] as, ResultSet rs, String trxName)
	{
		super(as, MAssetTransfer.class, rs, MDocType.DOCBASETYPE_GLJournal, trxName);
	}

	
	protected String loadDocumentDetails()
	{
		// Fix C_Period_ID
//		MAssetTransfer assetTr = getAssetTransfer();
//		assetTr.setC_Period_ID();
//		assetTr.saveEx();
		
		return null;
	}
	
	
	public BigDecimal getBalance() {
    	return Env.ZERO;
	}
	/**
	 * Produce inregistrarea:
	 * <pre>
	 *	20.., 21..[A_Asset_New_Acct]			=	23..[A_Asset_Acct]		
	 * </pre>
	 */
	
	public ArrayList<Fact> createFacts(MAcctSchema as)
	{
		MAssetTransfer assetTr = getAssetTransfer();
		MDepreciationWorkfile wk = getAssetWorkfile();
	    //MDepreciationExp exp = getExpense();
		
		ArrayList<Fact> facts = new ArrayList<Fact>();
		Fact fact = new Fact(this, as, assetTr.getPostingType());
		facts.add(fact);
		//
		// Change Asset Account
		if (assetTr.getA_Asset_New_Acct() != assetTr.getA_Asset_Acct())
		{
			MAccount dr = MAccount.getValidCombination(getCtx(), assetTr.getA_Asset_New_Acct(), getTrxName());
			MAccount cr = MAccount.getValidCombination(getCtx(), assetTr.getA_Asset_Acct() , getTrxName());
			FactUtil.createSimpleOperation(fact, null, dr, cr, as.getC_Currency_ID(),
					wk.getA_Asset_Cost(), false);
		}
		//
		// Change Asset Accum. Depr. Account
		if (assetTr.getA_Accumdepreciation_New_Acct() != assetTr.getA_Accumdepreciation_Acct())
		{
			MAccount cr = MAccount.getValidCombination(getCtx(), assetTr.getA_Accumdepreciation_New_Acct() , getTrxName());
			MAccount dr = MAccount.getValidCombination(getCtx(), assetTr.getA_Accumdepreciation_Acct() , getTrxName());
			FactUtil.createSimpleOperation(fact, null, dr, cr, as.getC_Currency_ID(),
					wk.getA_Accumulated_Depr(), false);
			        //exp.getA_Accumulated_Depr(), false);
		}
		//
		return facts;
	}
	/*private MDepreciationExp getExpense() {
		
		return MDepreciationExp.get(getCtx(), 1712112);
	}*/

	private MAssetTransfer getAssetTransfer()
	{
		return (MAssetTransfer)getPO();
	}
	private MDepreciationWorkfile getAssetWorkfile()
	{
		MAssetTransfer assetTr = getAssetTransfer();
		return MDepreciationWorkfile.get(getCtx(), assetTr.getA_Asset_ID(), assetTr.getPostingType(), getTrxName());
	}
	
}
