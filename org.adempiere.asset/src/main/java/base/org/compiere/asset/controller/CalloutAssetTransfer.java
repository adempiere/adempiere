/**
 * 
 */
package org.compiere.asset.controller;

import java.util.Properties;

import org.adempiere.core.domains.models.I_A_Asset_Transfer;
import org.adempiere.model.GridTabWrapper;
import org.compiere.asset.model.MAssetAcct;
import org.compiere.asset.model.MAssetTransfer;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;



/**
 * @author Anca Bradau, www.arhipac.ro
 *
 */
public class CalloutAssetTransfer extends CalloutEngine
{

	public String asset(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		if (isCalloutActive())
			return "";
		//
	    I_A_Asset_Transfer assetTransfer = GridTabWrapper.create(gridTab, I_A_Asset_Transfer.class);
	    if (assetTransfer.getA_Asset_ID() <= 0)
	    {
	    	return "";
	    }
    	MAssetAcct acct = MAssetAcct.forA_Asset_ID(ctx, assetTransfer.getA_Asset_ID(), assetTransfer.getPostingType(), assetTransfer.getDateAcct(), null);
    	if (acct == null)
    	{
    		return "@NotFound@ @A_Asset_Acct_ID@";
    	}
    	// Asset Acct
    	assetTransfer.setA_Asset_Acct(acct.getA_Asset_Acct());
    	assetTransfer.setA_Asset_New_Acct(acct.getA_Asset_Acct());
    	
    	//Accumulated Depreciation Account
    	assetTransfer.setA_Accumdepreciation_Acct(acct.getA_Accumdepreciation_Acct());
    	assetTransfer.setA_Accumdepreciation_New_Acct(acct.getA_Accumdepreciation_Acct());
    	
    	//Depreciation Account
    	assetTransfer.setA_Depreciation_Acct(acct.getA_Depreciation_Acct());
    	assetTransfer.setA_Depreciation_New_Acct(acct.getA_Depreciation_Acct());
    	
    	//Disposal revenue
    	assetTransfer.setA_Disposal_Revenue_Acct(acct.getA_Disposal_Revenue_Acct());
    	assetTransfer.setA_Disposal_Revenue_New_Acct(acct.getA_Disposal_Revenue_Acct());
    	
    	//Disposal Loss Account
    	assetTransfer.setA_Disposal_Loss_Acct(acct.getA_Disposal_Loss_Acct());
    	assetTransfer.setA_Disposal_Loss_New_Acct(acct.getA_Disposal_Loss_Acct());
      	
    	
		return "";
	}
	public String dateDoc(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		
		gridTab.setValue(MAssetTransfer.COLUMNNAME_DateAcct, value);
		return "";
	}
}

