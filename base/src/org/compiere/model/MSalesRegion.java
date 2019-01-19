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
 *	Sales Region Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MSalesRegion.java,v 1.3 2006/07/30 00:54:54 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com 2015-09-09
 *  	<li>FR [ 9223372036854775807 ] Add Support to Dynamic Tree
 *  @see https://adempiere.atlassian.net/browse/ADEMPIERE-442
 */
public class MSalesRegion extends X_C_SalesRegion
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8582026748675153489L;


	/**
	 * 	Get SalesRegion from Cache
	 *	@param ctx context
	 *	@param salesRegionId id
	 *	@return MSalesRegion
	 */
	@Deprecated
	public static MSalesRegion get (Properties ctx, int salesRegionId) {
		return getById(ctx, salesRegionId, null);
	}	//	get


	/** Static Cache */
	private static CCache<Integer, MSalesRegion> salesRegionCacheIds = new CCache<Integer, MSalesRegion>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, MSalesRegion> salesRegionCacheValues = new CCache<String, MSalesRegion>(Table_Name, 30);

	/**
	 * Get/Load Campaign [CACHED]
	 * @param ctx context
	 * @param salesRegionId
	 * @param trxName
	 * @return activity or null
	 */
	public static MSalesRegion getById(Properties ctx, int salesRegionId, String trxName) {
		if (salesRegionId <= 0)
			return null;

		MSalesRegion salesRegion = salesRegionCacheIds.get(salesRegionId);
		if (salesRegion != null && salesRegion.get_ID() > 0)
			return salesRegion;

		salesRegion = new Query(ctx , Table_Name , COLUMNNAME_C_SalesRegion_ID + "=?" , trxName)
				.setClient_ID()
				.setParameters(salesRegionId)
				.first();
		if (salesRegion != null && salesRegion.get_ID() > 0)
		{
			int clientId = Env.getAD_Client_ID(ctx);
			String key = clientId + "#" + salesRegion.getValue();
			salesRegionCacheValues.put(key, salesRegion);
			salesRegionCacheIds.put(salesRegion.get_ID(), salesRegion);
		}
		return salesRegion;
	}

	/**
	 * get Activity By Value [CACHED]
	 * @param ctx
	 * @param salesRegionValue
	 * @param trxName
	 * @return
	 */
	public static MSalesRegion getByValue(Properties ctx, String salesRegionValue, String trxName) {
		if (salesRegionValue == null)
			return null;
		if (salesRegionCacheValues.size() == 0 )
			getAll(ctx, true, trxName);

		int clientId = Env.getAD_Client_ID(ctx);
		String key = clientId + "#" + salesRegionValue;
		MSalesRegion salesRegion = salesRegionCacheValues.get(key);
		if (salesRegion != null && salesRegion.get_ID() > 0 )
			return salesRegion;

		salesRegion =  new Query(ctx, Table_Name , COLUMNNAME_Value +  "=?", trxName)
				.setClient_ID()
				.setParameters(salesRegionValue)
				.first();

		if (salesRegion != null && salesRegion.get_ID() > 0) {
			salesRegionCacheValues.put(key, salesRegion);
			salesRegionCacheIds.put(salesRegion.get_ID() , salesRegion);
		}
		return salesRegion;
	}
	
	/**
	 * Get All Campaign
	 * @param ctx
	 * @param resetCache
	 * @param trxName
	 * @return
	 */
	public static List<MSalesRegion> getAll(Properties ctx, boolean resetCache, String trxName) {
		List<MSalesRegion> salesRegionList;
		if (resetCache || salesRegionCacheIds.size() > 0 ) {
			salesRegionList = new Query(Env.getCtx(), Table_Name, null , trxName)
					.setClient_ID()
					.setOrderBy(COLUMNNAME_Name)
					.list();
			salesRegionList.stream().forEach(salesRegion -> {
				int clientId = Env.getAD_Client_ID(ctx);
				String key = clientId + "#" + salesRegion.getValue();
				salesRegionCacheIds.put(salesRegion.getC_SalesRegion_ID(), salesRegion);
				salesRegionCacheValues.put(key, salesRegion);
			});
			return salesRegionList;
		}
		salesRegionList = salesRegionCacheIds.entrySet().stream()
				.map(salesRegion -> salesRegion.getValue())
				.collect(Collectors.toList());
		return  salesRegionList;
	}
	
	
	/**************************************************************************
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param C_SalesRegion_ID id
	 *	@param trxName transaction
	 */
	public MSalesRegion (Properties ctx, int C_SalesRegion_ID, String trxName)
	{
		super (ctx, C_SalesRegion_ID, trxName);
	}	//	MSalesRegion

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MSalesRegion (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MSalesRegion

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (getAD_Org_ID() != 0)
			setAD_Org_ID(0);
		return true;
	}	//	beforeSave

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
//			insert_Tree(MTree.TREETYPE_SalesRegion);
		//	End Yamel Senih
		//	Value/Name change
		if (!newRecord && (is_ValueChanged("Value") || is_ValueChanged("Name")))
			MAccount.updateValueDescription(getCtx(), "C_SalesRegion_ID=" + getC_SalesRegion_ID(), get_TrxName());

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
//			delete_Tree(MTree.TREETYPE_SalesRegion);
//		return success;
//	}	//	afterDelete
	//	End Yamel Senih
	
}	//	MSalesRegion
