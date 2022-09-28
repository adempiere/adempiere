package org.compiere.asset.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.asset.model.MAssetAcct;
import org.compiere.asset.model.MAssetDisposed;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.acct.Doc;
import org.compiere.acct.Fact;
import org.compiere.model.MDocType;
import org.compiere.util.Env;


/**
 * @author Teo_Sarca, SC ARHIPAC SERVICE SRL
 */
public class Doc_AssetDisposed extends Doc
{
	/**
	 * @param ass
	 * @param clazz
	 * @param rs
	 * @param defaultDocumentType
	 * @param trxName
	 */
	public Doc_AssetDisposed (MAcctSchema[] as, ResultSet rs, String trxName)
	{
		super(as, MAssetDisposed.class, rs, MDocType.DOCBASETYPE_GLDocument, trxName);
	}

	
	protected String loadDocumentDetails()
	{
		return null;
	}
	
	
	public BigDecimal getBalance()
	{
		return Env.ZERO;
	}

	
	public ArrayList<Fact> createFacts(MAcctSchema as)
	{
		MAssetDisposed assetDisp = (MAssetDisposed)getPO();
		
		ArrayList<Fact> facts = new ArrayList<Fact>();
		Fact fact = new Fact(this, as, assetDisp.getPostingType());
		facts.add(fact);
		//
		fact.createLine(null, getAccount(MAssetAcct.COLUMNNAME_A_Asset_Acct)
				, as.getC_Currency_ID()
				, Env.ZERO, assetDisp.getA_Disposal_Amt());
		fact.createLine(null, getAccount(MAssetAcct.COLUMNNAME_A_Accumdepreciation_Acct)
				, as.getC_Currency_ID()
				, assetDisp.getA_Accumulated_Depr_Delta(), Env.ZERO);
		fact.createLine(null, getAccount(MAssetAcct.COLUMNNAME_A_Disposal_Loss_Acct)
				, as.getC_Currency_ID()
				, assetDisp.getExpense(), Env.ZERO);
		//
		return facts;
	}

	/**
	 * Get Account
	 * @param accountName
	 * @return
	 */
	private MAccount getAccount(String accountName)
	{
		MAssetDisposed assetDisposed = (MAssetDisposed)getPO();
		MAssetAcct assetAcct = MAssetAcct.forA_Asset_ID(getCtx(), assetDisposed.getA_Asset_ID(), assetDisposed.getPostingType(), assetDisposed.getDateAcct(),getTrxName());
		int accountId = (Integer)assetAcct.get_Value(accountName);
		return MAccount.getValidCombination(getCtx(), accountId , getTrxName());
	}

}
