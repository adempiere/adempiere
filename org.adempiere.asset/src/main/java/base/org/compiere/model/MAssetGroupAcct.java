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
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.FA.feature.UseLife;
import org.compiere.FA.feature.UseLifeImpl;

/**
 * Asset Group Accounting Model
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class MAssetGroupAcct extends X_A_Asset_Group_Acct
	implements UseLife
{
	/**
	 *
	 */
	private static final long serialVersionUID = 1097065220838511473L;

	/**
	 * Get Asset Group Accountings for given group
	 */
	public static List<MAssetGroupAcct> forA_Asset_Group_ID(Properties ctx, int A_Asset_Group_ID)
	{
		return new Query(ctx, Table_Name, COLUMNNAME_A_Asset_Group_ID+"=?", null)
					.setParameters(new Object[]{A_Asset_Group_ID})
					.list();
	}
	
	/**
	 * Get Asset Group Accountings for given group
	 */
	public static MAssetGroupAcct forA_Asset_Group_ID(Properties ctx, int A_Asset_Group_ID, String postingType)
	{
		final String whereClause = COLUMNNAME_A_Asset_Group_ID+"=? AND "+COLUMNNAME_PostingType+"=?";
		return new Query(ctx, Table_Name, whereClause, null)
					.setParameters(new Object[]{A_Asset_Group_ID, postingType})
					.firstOnly();
	}
	
	/**
	 * 	Default ConstructorX_A_Asset_Group_Acct
	 *	@param ctx context
	 *	@param X_A_Asset_Group_Acct_ID id
	 */
	public MAssetGroupAcct (Properties ctx, int X_A_Asset_Group_Acct_ID, String trxName)
	{
		super (ctx,X_A_Asset_Group_Acct_ID, trxName);
	}	//	MAssetGroupAcct
	
	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MAssetGroupAcct (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs,  trxName);
	}	//	MAssetGroupAcct
	
	/**	Asset Group	*/
	private MAssetGroup m_parent = null;
	
	/**
	 * Get Asset Group
	 */
	public MAssetGroup getParent()
	{
		if (m_parent == null)
		{
			int A_Asset_Group_ID = getA_Asset_Group_ID();
			if (is_new())
			{
				m_parent = new MAssetGroup(getCtx(), A_Asset_Group_ID, get_TrxName());
			}
			else
			{
				m_parent = MAssetGroup.get(getCtx(), A_Asset_Group_ID);
			}
		}
		return m_parent;
	}
	
	/* commented by @win
	public int getA_Asset_Class_ID()
	{
		return getParent().getA_Asset_Class_ID();
	}
	*/
	
	public Timestamp getAssetServiceDate()
	{
		return null;
	}
	
	/**
	 * Clone this object, using specified group
	 * @param grp	the new asset group
	 * @return new asset group accounting (NOTE: it's not saved)
	 */
	public MAssetGroupAcct copy(MAssetGroup grp)
	{
		MAssetGroupAcct newAcct = new MAssetGroupAcct(grp.getCtx(), 0, grp.get_TrxName());
		copyValues(this, newAcct, grp.getAD_Client_ID(), grp.getAD_Org_ID());
		newAcct.setA_Asset_Group_ID(grp.getA_Asset_Group_ID());
		return newAcct;
	}

	public boolean beforeSave(boolean newRecord)
	{
		if (! UseLifeImpl.get(this).validate())
		{
			return false;
		}
		return true;
	}
	
	public boolean set_AttrValue(String ColumnName, Object value) {
		int index = get_ColumnIndex(ColumnName);
		if (index < 0)
			return false;
		return set_ValueNoCheck(ColumnName, value);
	}
	public Object get_AttrValue(String ColumnName) {
		int index = get_ColumnIndex(ColumnName);
		if (index < 0)
			return null;
		return get_Value(index);
	}
	public boolean is_AttrValueChanged(String ColumnName) {
		int index = get_ColumnIndex(ColumnName);
		if (index < 0)
			return false;
		return is_ValueChanged(index);
	}
}	//	MAssetGroupAcct
