/**
 * 
 */
package org.eevolution.manufacturing.acct;

import java.util.HashMap;

import org.adempiere.core.domains.models.X_M_Product_Acct;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.acct.Doc;
import org.compiere.acct.DocLine;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCostElement;
import org.compiere.model.PO;
import org.compiere.model.ProductCost;
import org.compiere.util.DB;

/**
 * @author Teo Sarca, www.arhipac.ro
 *
 */
public class DocLine_CostCollector extends DocLine
{
	private static final HashMap<Integer, String> s_acctName = new HashMap<Integer, String>();
	static
	{
		s_acctName.put(ProductCost.ACCTTYPE_P_WorkInProcess, X_M_Product_Acct.COLUMNNAME_P_WIP_Acct);
		s_acctName.put(ProductCost.ACCTTYPE_P_MethodChangeVariance, X_M_Product_Acct.COLUMNNAME_P_MethodChangeVariance_Acct);
		s_acctName.put(ProductCost.ACCTTYPE_P_UsageVariance, X_M_Product_Acct.COLUMNNAME_P_UsageVariance_Acct);
		s_acctName.put(ProductCost.ACCTTYPE_P_RateVariance, X_M_Product_Acct.COLUMNNAME_P_RateVariance_Acct);
		s_acctName.put(ProductCost.ACCTTYPE_P_MixVariance, X_M_Product_Acct.COLUMNNAME_P_MixVariance_Acct);
		s_acctName.put(ProductCost.ACCTTYPE_P_FloorStock, X_M_Product_Acct.COLUMNNAME_P_FloorStock_Acct);
		s_acctName.put(ProductCost.ACCTTYPE_P_CostOfProduction, X_M_Product_Acct.COLUMNNAME_P_CostOfProduction_Acct);
		s_acctName.put(ProductCost.ACCTTYPE_P_Labor, X_M_Product_Acct.COLUMNNAME_P_Labor_Acct);
		s_acctName.put(ProductCost.ACCTTYPE_P_Burden, X_M_Product_Acct.COLUMNNAME_P_Burden_Acct);	
		s_acctName.put(ProductCost.ACCTTYPE_P_OutsideProcessing, X_M_Product_Acct.COLUMNNAME_P_OutsideProcessing_Acct);
		s_acctName.put(ProductCost.ACCTTYPE_P_Overhead, X_M_Product_Acct.COLUMNNAME_P_Overhead_Acct);	
		s_acctName.put(ProductCost.ACCTTYPE_P_Scrap, X_M_Product_Acct.COLUMNNAME_P_Scrap_Acct);
	}
	
	public DocLine_CostCollector(PO po, Doc doc)
	{
		super(po, doc);
	}
	
	public MAccount getAccount(MAcctSchema as, MCostElement element)
	{
		String costElementType = element.getCostElementType();
		int acctType;
		if (MCostElement.COSTELEMENTTYPE_Material.equals(costElementType))
		{
			acctType = ProductCost.ACCTTYPE_P_Asset;
		}
		else if (MCostElement.COSTELEMENTTYPE_Resource.equals(costElementType))
		{
			acctType = ProductCost.ACCTTYPE_P_Labor;
		}
		else if (MCostElement.COSTELEMENTTYPE_BurdenMOverhead.equals(costElementType))
		{
			acctType = ProductCost.ACCTTYPE_P_Burden;
		}
		else if (MCostElement.COSTELEMENTTYPE_Overhead.equals(costElementType))
		{
			acctType = ProductCost.ACCTTYPE_P_Overhead;
		}
		else if (MCostElement.COSTELEMENTTYPE_OutsideProcessing.equals(costElementType))
		{
			acctType = ProductCost.ACCTTYPE_P_OutsideProcessing;
		}
		else
		{
			throw new AdempiereException("@NotSupported@ "+element);
		}
		return getAccount(acctType, as);
	}

	@Override
	public MAccount getAccount(int acctType, MAcctSchema acctSchema)
	{
		String acctName = s_acctName.get(acctType);
		if (getM_Product_ID() == 0 || acctName == null)
		{
			return super.getAccount(acctType, acctSchema);
		}
		return getAccount(acctName, acctSchema);
	}

	/**
	 * Get Account
	 * @param acctName
	 * @param acctSchema
	 * @return
	 */
	public MAccount getAccount(String acctName, MAcctSchema acctSchema)
	{
		final String sql = 
			 " SELECT "
			+" COALESCE(pa."+acctName+",pca."+acctName+",asd."+acctName+")"
			+" FROM M_Product p" 
			+" INNER JOIN M_Product_Acct pa ON (pa.M_Product_ID=p.M_Product_ID)"
			+" INNER JOIN M_Product_Category_Acct pca ON (pca.M_Product_Category_ID=p.M_Product_Category_ID AND pca.C_AcctSchema_ID=pa.C_AcctSchema_ID)"
			+" INNER JOIN C_AcctSchema_Default asd ON (asd.C_AcctSchema_ID=pa.C_AcctSchema_ID)"
			+" WHERE pa.M_Product_ID=? AND pa.C_AcctSchema_ID=?";
		int validCombinationId = DB.getSQLValueEx(getTrxName(), sql, getM_Product_ID(), acctSchema.get_ID());
		if (validCombinationId  <= 0)
		{
			return null;
		}
		return MAccount.getValidCombination(acctSchema.getCtx(), validCombinationId, getTrxName());
	}
	
	
}
