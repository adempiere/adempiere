/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.compiere.util.CCache;
import org.compiere.util.Env;


/**
 *	Campaign model
 *	
 *  @author Jorg Janke
 *  @version $Id: MCampaign.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com 2015-09-09
 *  	<li>FR [ 9223372036854775807 ] Add Support to Dynamic Tree
 *  @see https://adempiere.atlassian.net/browse/ADEMPIERE-442
 */
public class MCampaign extends X_C_Campaign
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5881057827687596119L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Campaign_ID id
	 *	@param trxName transaction
	 */
	public MCampaign (Properties ctx, int C_Campaign_ID, String trxName)
	{
		super (ctx, C_Campaign_ID, trxName);
	}	//	MCampaign

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MCampaign (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MCampaign
	
	/** Static Cache */
	private static CCache<Integer, MCampaign> campaignCacheIds = new CCache<Integer, MCampaign>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, MCampaign> campaignCacheValues = new CCache<String, MCampaign>(Table_Name, 30);

	/**
	 * Get/Load Activity [CACHED]
	 * @param ctx context
	 * @param campaignId
	 * @param trxName
	 * @return activity or null
	 */
	public static MCampaign getById(Properties ctx, int campaignId, String trxName) {
		if (campaignId <= 0)
			return null;

		MCampaign campaign = campaignCacheIds.get(campaignId);
		if (campaign != null && campaign.get_ID() > 0)
			return campaign;

		campaign = new Query(ctx , Table_Name , COLUMNNAME_C_Campaign_ID + "=?" , trxName)
				.setClient_ID()
				.setParameters(campaignId)
				.first();
		if (campaign != null && campaign.get_ID() > 0)
		{
			int clientId = Env.getAD_Client_ID(ctx);
			String key = clientId + "#" + campaign.getValue();
			campaignCacheValues.put(key, campaign);
			campaignCacheIds.put(campaign.get_ID(), campaign);
		}
		return campaign;
	}

	/**
	 * get Activity By Value [CACHED]
	 * @param ctx
	 * @param campaignValue
	 * @param trxName
	 * @return
	 */
	public static MCampaign getByValue(Properties ctx, String campaignValue, String trxName) {
		if (campaignValue == null)
			return null;
		if (campaignCacheValues.size() == 0 )
			getAll(ctx, true);

		int clientId = Env.getAD_Client_ID(ctx);
		String key = clientId + "#" + campaignValue;
		MCampaign campaign = campaignCacheValues.get(key);
		if (campaign != null && campaign.get_ID() > 0 )
			return campaign;

		campaign =  new Query(ctx, Table_Name , COLUMNNAME_Value +  "=?", trxName)
				.setClient_ID()
				.setParameters(campaignValue)
				.first();

		if (campaign != null && campaign.get_ID() > 0) {
			campaignCacheValues.put(key, campaign);
			campaignCacheIds.put(campaign.get_ID() , campaign);
		}
		return campaign;
	}
	
	/**
	 * Get All Campaign
	 * @param ctx
	 * @param resetCache
	 * @return
	 */
	public static List<MCampaign> getAll(Properties ctx, boolean resetCache) {
		List<MCampaign> campaignList;
		if (resetCache || campaignCacheIds.size() > 0 ) {
			campaignList = new Query(Env.getCtx(), Table_Name, null , null)
					.setClient_ID()
					.setOrderBy(COLUMNNAME_Name)
					.list();
			campaignList.stream().forEach(campaign -> {
				int clientId = Env.getAD_Client_ID(ctx);
				String key = clientId + "#" + campaign.getValue();
				campaignCacheIds.put(campaign.getC_Campaign_ID(), campaign);
				campaignCacheValues.put(key, campaign);
			});
			return campaignList;
		}
		campaignList = campaignCacheIds.entrySet().stream()
				.map(campaign -> campaign.getValue())
				.collect(Collectors.toList());
		return  campaignList;
	}
	
	/**
	 * 	After Save.
	 * 	Insert
	 * 	- create tree
	 *	@param newRecord insert
	 *	@param success save success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		//	Yamel Senih [ 9223372036854775807 ]
		//	Change to PO
//		if (newRecord)
//			insert_Tree(MTree.TREETYPE_Campaign);
		//	End Yamel Senih
		//	Value/Name change
		if (!newRecord && (is_ValueChanged("Value") || is_ValueChanged("Name")))
			MAccount.updateValueDescription(getCtx(), "C_Campaign_ID=" + getC_Campaign_ID(), get_TrxName());

		return true;
	}	//	afterSave

	/**
	 * 	After Delete
	 *	@param success
	 *	@return deleted
	 */
	//	Yamel Senih [ 9223372036854775807 ]
	//	Change to PO
//	protected boolean afterDelete (boolean success)
//	{
//		if (success)
//			delete_Tree(MTree.TREETYPE_Campaign);
//		return success;
//	}	//	afterDelete
	//	End Yamel Senih

}	//	MCampaign
