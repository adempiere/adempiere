/**
 * 
 */
package org.compiere.FA.model;

import java.util.Properties;

import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.I_A_Asset_Reval;
import org.compiere.model.MAssetReval;
import org.compiere.model.MDepreciationWorkfile;


/**
 * @author Anca Bradau www.arhipac.ro
 *
 */
public class CalloutA_Asset_Reval extends CalloutEngine
{
	public String asset(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())
			return "";
		//
	    I_A_Asset_Reval model = GridTabWrapper.create(mTab, I_A_Asset_Reval.class);
	    if (model.getA_Asset_ID() <= 0)
	    {
	    	return "";
	    }
    	MDepreciationWorkfile amount = MDepreciationWorkfile.get(ctx, model.getA_Asset_ID(), model.getPostingType(), null);
    	if (amount == null)
    	{
    		return "@NotFound@ @A_Asset_ID@";
    	}
    	// 
    	model.setA_Asset_Cost(amount.getA_Asset_Cost());
    	model.setA_Asset_Cost_Change(amount.getA_Asset_Cost());
    	
    	model.setA_Accumulated_Depr(amount.getA_Accumulated_Depr());
    	model.setA_Change_Acumulated_Depr(amount.getA_Accumulated_Depr());
    	
  		return "";
    	}
    public String dateDoc(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
    	{
    if (isCalloutActive() || value == null)
    			return "";
    		
    mTab.setValue(MAssetReval.COLUMNNAME_DateAcct, value);
     return "";
    	}
}
	
