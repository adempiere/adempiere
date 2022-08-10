/**
 * 
 */
package org.compiere.FA.model;

import java.util.Properties;

import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.I_A_Asset_Transfer;
import org.compiere.model.MAssetAcct;
import org.compiere.model.MAssetTransfer;



/**
 * @author Anca Bradau, www.arhipac.ro
 *
 */
public class CalloutA_Asset_Transfer extends CalloutEngine
{

	public String asset(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())
			return "";
		//
	    I_A_Asset_Transfer model = GridTabWrapper.create(mTab, I_A_Asset_Transfer.class);
	    if (model.getA_Asset_ID() <= 0)
	    {
	    	return "";
	    }
    	MAssetAcct acct = MAssetAcct.forA_Asset_ID(ctx, model.getA_Asset_ID(), model.getPostingType(), model.getDateAcct(), null);
    	if (acct == null)
    	{
    		return "@NotFound@ @A_Asset_Acct_ID@";
    	}
    	// Asset Acct
    	model.setA_Asset_Acct(acct.getA_Asset_Acct());
    	model.setA_Asset_New_Acct(acct.getA_Asset_Acct());
    	
    	//Accumulated Depreciation Account
    	model.setA_Accumdepreciation_Acct(acct.getA_Accumdepreciation_Acct());
    	model.setA_Accumdepreciation_New_Acct(acct.getA_Accumdepreciation_Acct());
    	
    	//Depreciation Account
    	model.setA_Depreciation_Acct(acct.getA_Depreciation_Acct());
    	model.setA_Depreciation_New_Acct(acct.getA_Depreciation_Acct());
    	
    	//Disposal revenue
    	model.setA_Disposal_Revenue_Acct(acct.getA_Disposal_Revenue_Acct());
    	model.setA_Disposal_Revenue_New_Acct(acct.getA_Disposal_Revenue_Acct());
    	
    	//Disposal Loss Account
    	model.setA_Disposal_Loss_Acct(acct.getA_Disposal_Loss_Acct());
    	model.setA_Disposal_Loss_New_Acct(acct.getA_Disposal_Loss_Acct());
      	
    	
		return "";
	}
	public String dateDoc(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		
		mTab.setValue(MAssetTransfer.COLUMNNAME_DateAcct, value);
		return "";
	}
}

