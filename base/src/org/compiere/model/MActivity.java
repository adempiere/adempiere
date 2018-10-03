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
 *	Activity Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MActivity.java,v 1.2 2006/07/30 00:51:03 jjanke Exp $
 * 
 * 	@author Teo Sarca, www.arhipac.ro
 * 			<li>FR [ 2736867 ] Add caching support to MActivity
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com 2015-09-09
 *  	<li>FR [ 9223372036854775807 ] Add Support to Dynamic Tree
 *  @see https://adempiere.atlassian.net/browse/ADEMPIERE-442
 */
public class MActivity extends X_C_Activity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3014706648686670575L;
	
	/** Static Cache */
	private static CCache<Integer, MActivity> activityCacheIds = new CCache<Integer, MActivity>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, MActivity> activityCacheValues = new CCache<String, MActivity>(Table_Name, 30);

	/**
	 *
	 * @param ctx
	 * @param activityId
	 * @return
	 */
	@Deprecated
	public static MActivity get(Properties ctx, int activityId)
	{
		return getById(ctx, activityId, null);
	}

	/**
	 * Get/Load Activity [CACHED]
	 * @param ctx context
	 * @param activityId
	 * @param trxName
	 * @return activity or null
	 */
	public static MActivity getById(Properties ctx, int activityId, String trxName)
	{
		if (activityId <= 0)
			return null;

		MActivity activity = activityCacheIds.get(activityId);
		if (activity != null && activity.get_ID() > 0)
			return activity;

		activity = new Query(ctx , Table_Name , COLUMNNAME_C_Activity_ID + "=?" , trxName)
				.setClient_ID()
				.setParameters(activityId)
				.first();
		if (activity != null && activity.get_ID() > 0)
		{
			int clientId = Env.getAD_Client_ID(ctx);
			String key = clientId + "#" + activity.getValue();
			activityCacheValues.put(key, activity);
			activityCacheIds.put(activity.get_ID(), activity);
		}
		return activity;
	}

	/**
	 * get Activity By Value [CACHED]
	 * @param ctx
	 * @param activityvalue
	 * @param trxName
	 * @return
	 */
	public static MActivity getByValue(Properties ctx, String activityvalue, String trxName)
	{
		if (activityvalue == null)
			return null;
		if (activityCacheValues.size() == 0 )
			getAll(ctx, true, trxName);

		int clientId = Env.getAD_Client_ID(ctx);
		String key = clientId + "#" + activityvalue;
		MActivity activity = activityCacheValues.get(key);
		if (activity != null && activity.get_ID() > 0 )
			return activity;

		activity =  new Query(ctx, Table_Name , COLUMNNAME_Value +  "=?", trxName)
				.setClient_ID()
				.setParameters(activityvalue)
				.first();

		if (activity != null && activity.get_ID() > 0) {
			activityCacheValues.put(key, activity);
			activityCacheIds.put(activity.get_ID() , activity);
		}
		return activity;
	}

	/**
	 * Get All Activity
	 * @param ctx
	 * @param resetCache
	 * @param trxName
	 * @return
	 */
	public static List<MActivity> getAll(Properties ctx, boolean resetCache, String trxName) {
		List<MActivity> activitiesList;
		if (resetCache || activityCacheIds.size() > 0 ) {
			activitiesList = new Query(Env.getCtx(), Table_Name, null , trxName)
					.setClient_ID()
					.setOrderBy(COLUMNNAME_Name)
					.list();
			activitiesList.stream().forEach(activity -> {
				int clientId = Env.getAD_Client_ID(ctx);
				String key = clientId + "#" + activity.getValue();
				activityCacheIds.put(activity.getC_Activity_ID(), activity);
				activityCacheValues.put(key, activity);
			});
			return activitiesList;
		}
		activitiesList = activityCacheIds.entrySet().stream()
				.map(activity -> activity.getValue())
				.collect(Collectors.toList());
		return  activitiesList;
	}

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Activity_ID id
	 *	@param trxName transaction
	 */
	public MActivity (Properties ctx, int C_Activity_ID, String trxName)
	{
		super (ctx, C_Activity_ID, trxName);
	}	//	MActivity

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MActivity (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MActivity
	
	
	/**
	 * 	After Save.
	 * 	Insert
	 * 	- create tree
	 *	@param newRecord insert
	 *	@param success save success
	 *	@return true if saved
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		//	Yamel Senih [ 9223372036854775807 ]
		//	Change to PO
//		if (newRecord)
//			insert_Tree(MTree.TREETYPE_Activity);
		//	End Yamel Senih
		//	Value/Name change
		if (!newRecord && (is_ValueChanged("Value") || is_ValueChanged("Name")))
			MAccount.updateValueDescription(getCtx(), "C_Activity_ID=" + getC_Activity_ID(), get_TrxName());
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
//			delete_Tree(MTree.TREETYPE_Activity);
//		return success;
//	}	//	afterDelete
	//	End Yamel Senih

}	//	MActivity
