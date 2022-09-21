/**
 * 
 */
package org.compiere.asset.exceptions;

import org.compiere.asset.model.MAsset;
import org.compiere.model.MRefList;


/**
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *
 */
public class AssetInvalidTransitionException extends AssetException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4356632909207763285L;
	
	private String oldStatus = null;
	private String newStatus = null;
	
	public AssetInvalidTransitionException(String oldStatus, String newStatus)
	{
		super("@AssetInvalidTransition@ @"+oldStatus+"@ -> @"+newStatus+"@");
		this.oldStatus = oldStatus;
		this.newStatus = newStatus;
	}

	
	public String getLocalizedMessage()
	{
		String msg = super.getLocalizedMessage();
		return msg.replace("@"+this.oldStatus+"@", MRefList.getListName(getCtx(), MAsset.A_ASSET_STATUS_AD_Reference_ID, this.oldStatus))
				.replace("@"+this.newStatus+"@",MRefList.getListName(getCtx(), MAsset.A_ASSET_STATUS_AD_Reference_ID, this.newStatus));
	}
	
	
}
