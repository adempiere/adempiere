package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAssetAcct;
import org.compiere.model.MAssetReval;
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

	
	public ArrayList<Fact> createFacts(MAcctSchema as)
	{
		MAssetAcct assetAcct = getAssetAcct();
		MAssetReval assetRe = getAssetReval();
		
		ArrayList<Fact> facts = new ArrayList<Fact>();
		Fact fact = new Fact(this, as, assetAcct.getPostingType());
		facts.add(fact);
		
		MAccount dr = MAccount.get(getCtx(), assetAcct.getA_Asset_Acct());  
		MAccount cr = MAccount.get(getCtx(), assetAcct.getA_Reval_Cost_Offset_Acct());
		FactUtil.createSimpleOperation(fact, null, dr, cr, as.getC_Currency_ID(),
				assetRe.getA_Asset_Cost_Change().subtract(assetRe.getA_Asset_Cost()), false);
		
			
		MAccount drd = MAccount.get(getCtx(), assetAcct.getA_Reval_Cost_Offset_Acct());  
		MAccount crd = MAccount.get(getCtx(), assetAcct.getA_Accumdepreciation_Acct());
		FactUtil.createSimpleOperation(fact, null, drd, crd, as.getC_Currency_ID(),
				assetRe.getA_Change_Acumulated_Depr().subtract(assetRe.getA_Accumulated_Depr()), false);
		
		
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
