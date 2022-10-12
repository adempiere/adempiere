/**
 * 
 */
package org.compiere.asset.controller;

import java.util.Properties;

import org.adempiere.core.domains.models.I_A_Asset_Reval;
import org.adempiere.model.GridTabWrapper;
import org.compiere.asset.model.MAssetReval;
import org.compiere.asset.model.MDepreciationWorkfile;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;


/**
 * @author Anca Bradau www.arhipac.ro
 *
 */
public class CalloutAssetReval extends CalloutEngine
{
	public String asset(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		if (isCalloutActive())
			return "";
		//
	    I_A_Asset_Reval assetReval = GridTabWrapper.create(gridTab, I_A_Asset_Reval.class);
	    if (assetReval.getA_Asset_ID() <= 0)
	    {
	    	return "";
	    }
    	MDepreciationWorkfile amount = MDepreciationWorkfile.get(ctx, assetReval.getA_Asset_ID(), assetReval.getPostingType(), null);
    	if (amount == null)
    	{
    		return "@NotFound@ @A_Asset_ID@";
    	}
    	// 
    	assetReval.setA_Asset_Cost(amount.getA_Asset_Cost());
    	assetReval.setA_Asset_Cost_Change(amount.getA_Asset_Cost());
    	
    	assetReval.setA_Accumulated_Depr(amount.getA_Accumulated_Depr());
    	assetReval.setA_Change_Acumulated_Depr(amount.getA_Accumulated_Depr());
    	
  		return "";
    	}
    public String dateDoc(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
    	{
    if (isCalloutActive() || value == null)
    			return "";
    		
    gridTab.setValue(MAssetReval.COLUMNNAME_DateAcct, value);
     return "";
    	}
}
	
