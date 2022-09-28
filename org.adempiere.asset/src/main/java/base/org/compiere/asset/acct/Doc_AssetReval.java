package org.compiere.asset.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.acct.Doc;
import org.compiere.acct.Fact;
import org.compiere.asset.model.MAssetAcct;
import org.compiere.asset.model.MAssetReval;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MDocType;
import org.compiere.util.Env;


/**
 * @author Anca Bradau www.arhipac.ro
 *
 */
public class Doc_AssetReval extends Doc  
{

	
	private final String POSTINGTYPE_Actual = "A";
	public Doc_AssetReval (MAcctSchema[] as, ResultSet rs, String trxName)
	{
		super(as, MAssetReval.class, rs, MDocType.DOCBASETYPE_GLJournal, trxName);
	}

	
	public ArrayList<Fact> createFacts(MAcctSchema acctSchema)
	{
		MAssetAcct assetAccount = getAssetAcct();
		MAssetReval assetRevaluation = getAssetReval();
		
		ArrayList<Fact> facts = new ArrayList<Fact>();
		Fact fact = new Fact(this, acctSchema, assetAccount.getPostingType());
		facts.add(fact);
		
		MAccount dr = MAccount.getValidCombination(getCtx(), assetAccount.getA_Asset_Acct(), getTrxName());
		MAccount cr = MAccount.getValidCombination(getCtx(), assetAccount.getA_Reval_Cost_Offset_Acct() , getTrxName());
		FactUtil.createSimpleOperation(fact, null, dr, cr, acctSchema.getC_Currency_ID(),
				assetRevaluation.getA_Asset_Cost_Change().subtract(assetRevaluation.getA_Asset_Cost()), false);
		
			
		MAccount drd = MAccount.getValidCombination(getCtx(), assetAccount.getA_Reval_Cost_Offset_Acct() , getTrxName());
		MAccount crd = MAccount.getValidCombination(getCtx(), assetAccount.getA_Accumdepreciation_Acct() ,  getTrxName());
		FactUtil.createSimpleOperation(fact, null, drd, crd, acctSchema.getC_Currency_ID(),
				assetRevaluation.getA_Change_Acumulated_Depr().subtract(assetRevaluation.getA_Accumulated_Depr()), false);
		return facts;
	}

	
	public BigDecimal getBalance() 
	{
		return  Env.ZERO;
	}
	
	
	protected String loadDocumentDetails() 
	{
		return null;
	}
	public String getPostingType() 
	{
		return POSTINGTYPE_Actual;
	}
	
	private MAssetAcct getAssetAcct()
	{
		return MAssetAcct.forA_Asset_ID(getCtx(), getA_Asset_ID(), getPostingType() , getDateAcct(), null);
	}
	private MAssetReval getAssetReval()
	{
		return (MAssetReval)getPO();
	}
	
	/**
	 * Get A_Asset_ID
	 * @return Asset
	 */
	public int getA_Asset_ID()
	{
		int index = p_po.get_ColumnIndex("A_Asset_ID");
		if (index != -1)
		{
			Integer ii = (Integer)p_po.get_Value(index);
			if (ii != null)
				return ii.intValue();
		}
		return 0;
	}

}
