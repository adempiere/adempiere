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
package org.compiere.asset.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.X_A_Asset_Group;
import org.compiere.util.CCache;
import org.compiere.util.DB;

/**
 * Asset Group Model
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class MAssetGroup extends X_A_Asset_Group
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1364948077775028283L;
	
	/** Cache: ID -> MAssetGroup */
	private static CCache<Integer, MAssetGroup> s_cache = new CCache<Integer, MAssetGroup>(Table_Name, 10, 0);
	
	/**
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param A_Asset_Group_ID
	 */
	public MAssetGroup (Properties ctx, int A_Asset_Group_ID, String trxName)
	{
		super (ctx,A_Asset_Group_ID, trxName);
	}	//	MAssetGroup
	
	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MAssetGroup (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs,  trxName);
	}	//	MAssetGroup
	
	/**
	 * Get Asset Group [CACHE]
	 * @param ctx context
	 * @param A_Asset_Group_ID	asset group id
	 * @return asset group or null
	 */
	public static MAssetGroup get(Properties ctx, int A_Asset_Group_ID)
	{
		if (A_Asset_Group_ID <= 0)
			return null;
		// Try cache
		MAssetGroup ag = s_cache.get(A_Asset_Group_ID);
		if (ag != null)
			return ag;
		// Load
		ag = new MAssetGroup(ctx, A_Asset_Group_ID, null);
		if (ag != null && ag.get_ID() != A_Asset_Group_ID)
			ag = null;
		//
		return ag;
	}
	
	/**
	 * Get default asset group ID for given model.
	 * WARNING: trxName = null.
	 * @param m	reference model (used to get AD_Client_ID)
	 * @return default asset group ID or 0 if not found
	 */
	public static int getDefault_ID(SetGetModel m)
	{
		int AD_Client_ID = SetGetUtil.get_AttrValueAsInt(m, "AD_Client_ID");
		/* commented by @win
		int A_AssetType_ID = SetGetUtil.get_AttrValueAsInt(m, MAssetType.COLUMNNAME_A_Asset_Type_ID);
		*/
		final String sql = "SELECT "+COLUMNNAME_A_Asset_Group_ID
				+ " FROM "+Table_Name
				+ " WHERE AD_Client_ID=?"
			//	+ " AND NVL("+COLUMNNAME_A_Asset_Type_ID+",0) IN (0,?)" //commented by @win
				+ " ORDER BY "+COLUMNNAME_IsDefault+" DESC"
							+", "+COLUMNNAME_A_Asset_Group_ID+" ASC" // default first, older first
		;
		/* modify by @win
		int id = DB.getSQLValueEx(null, sql, AD_Client_ID, A_AssetType_ID);
		*/
		int id = DB.getSQLValueEx(null, sql, AD_Client_ID);
		// modify by @win
		
		return id;
	}
	
	/**
	 * Update Asset
	 * - updates asset M_AssetGroup_ID if is null
	 */
	public static void updateAsset(SetGetModel m, int A_Asset_Group_ID)
	{
		/* commented by @win
		int i = (Integer) m.get_AttrValue(MAsset.COLUMNNAME_A_Asset_Type_ID);
		MAssetType type = MAssetType.get(m.getCtx(), m.get_AttrValue(MAsset.COLUMNNAME_A_Asset_Type_ID));
		if (type == null)
			return;
		
		if (A_Asset_Group_ID > 0)
		{
			if (!type.isFixedAsset())
			{
				return;
			}
			MAssetGroup assetGrp = MAssetGroup.get(m.getCtx(), A_Asset_Group_ID);
			int A_Asset_Class_ID = assetGrp.getA_Asset_Class_ID();
			if (A_Asset_Class_ID > 0)
			{
				m.set_AttrValue(MAsset.COLUMNNAME_A_Asset_Class_ID, A_Asset_Class_ID);
			}
		}
		else
		{
			A_Asset_Group_ID = MAssetGroup.getDefault_ID(SetGetUtil.wrap(m));
			m.set_AttrValue(MAsset.COLUMNNAME_A_Asset_Group_ID, A_Asset_Group_ID);
		}
		*/ 
		if (A_Asset_Group_ID < 0) {
			A_Asset_Group_ID = MAssetGroup.getDefault_ID(SetGetUtil.wrap(m));
			m.set_AttrValue(MAsset.COLUMNNAME_A_Asset_Group_ID, A_Asset_Group_ID);
		}
		
		//end modify by @win
	}

	
	protected boolean beforeSave (boolean newRecord)
	{
		/* commented by @win
		MAssetType type = MAssetType.get(getCtx(), getA_Asset_Type_ID());
		if (type != null)
		{
			type.update(SetGetUtil.wrap(this), newRecord == true);
		}
		*/
		//end commented by @win
		
		return true;
	}
	
	
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if(!success)
		{
			return false;
		}
		//
		if (newRecord)
		{
			// If this is not the default group, then copy accounting settings from default group
			int default_id = getDefault_ID(SetGetUtil.wrap(this));
			if (default_id > 0 && default_id != get_ID())
			{
				for (MAssetGroupAcct acct : MAssetGroupAcct.forA_Asset_Group_ID(getCtx(), default_id))
				{
					MAssetGroupAcct newAcct = acct.copy(this);
					newAcct.saveEx(get_TrxName());
				}
			}
		}
		//
		return true;
	}
}	//	MAssetGroup

