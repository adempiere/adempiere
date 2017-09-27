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

import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Organization Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MOrg.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 */
public class MOrg extends X_AD_Org
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5604686137606338725L;


	/**
	 * 	Get Active Organizations Of Client
	 *	@param po persistent object
	 *	@return array of orgs
	 */
	public static MOrg[] getOfClient (PO po)
	{
		List<MOrg> list = new Query(po.getCtx(), Table_Name, "AD_Client_ID=?", null)
								.setOrderBy(COLUMNNAME_Value)
								.setOnlyActiveRecords(true)
								.setParameters(po.getAD_Client_ID())
								.list();
		for (MOrg org : list)
		{
			s_cache.put(org.get_ID(), org);
		}
		return list.toArray(new MOrg[list.size()]);
	}	//	getOfClient
	
	/**
	 * 	Get Org from Cache
	 *	@param ctx context
	 *	@param AD_Org_ID id
	 *	@return MOrg
	 */
	public static MOrg get (Properties ctx, int AD_Org_ID)
	{
		MOrg retValue = s_cache.get (AD_Org_ID);
		if (retValue != null)
			return retValue;
		retValue = new MOrg (ctx, AD_Org_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (AD_Org_ID, retValue);
		return retValue;
	}	//	get

	/**	Cache						*/
	private static CCache<Integer,MOrg>	s_cache	= new CCache<Integer,MOrg>(Table_Name, 50);
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_Org_ID id
	 *	@param trxName transaction
	 */
	public MOrg (Properties ctx, int AD_Org_ID, String trxName)
	{
		super(ctx, AD_Org_ID, trxName);
		if (AD_Org_ID == 0)
		{
		//	setValue (null);
		//	setName (null);
			setIsSummary (false);
		}
	}	//	MOrg

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MOrg (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MOrg

	/**
	 * 	Parent Constructor
	 *	@param client client
	 *	@param name name
	 */
	public MOrg (MClient client, String value, String name)
	{
		this (client.getCtx(), 0, client.get_TrxName());
		setAD_Client_ID (client.getAD_Client_ID());
		setValue (value);
		setName (name);
	}	//	MOrg

	/**	Linked Business Partner			*/
	private Integer 	m_linkedBPartner = null;

	/**
	 *	Get Org Info
	 *	@return Org Info
	 */
	public MOrgInfo getInfo()
	{
		return MOrgInfo.get(getCtx(), getAD_Org_ID(), get_TrxName());
	}	//	getMOrgInfo


	
	/**
	 * 	After Save
	 *	@param newRecord new Record
	 *	@param success save success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		if (newRecord)
		{
			//	Info
			MOrgInfo info = new MOrgInfo (this);
			info.saveEx();
			//	Access
			MRoleOrgAccess.createForOrg (this);
			MRole role = MRole.getDefault(getCtx(), true);	//	reload
			role.set_TrxName(get_TrxName());
			role.loadAccess(true); // reload org access within transaction
			//	TreeNode
			insert_Tree(MTree_Base.TREETYPE_Organization);
		}
		//	Value/Name change
		if (!newRecord && (is_ValueChanged("Value") || is_ValueChanged("Name")))
		{
			MAccount.updateValueDescription(getCtx(), "AD_Org_ID=" + getAD_Org_ID(), get_TrxName());
			if ("Y".equals(Env.getContext(getCtx(), "$Element_OT"))) 
				MAccount.updateValueDescription(getCtx(), "AD_OrgTrx_ID=" + getAD_Org_ID(), get_TrxName());
		}
		
		return true;
	}	//	afterSave
	
	/**
	 * 	After Delete
	 *	@param success
	 *	@return deleted
	 */
	protected boolean afterDelete (boolean success)
	{
		if (success)
			delete_Tree(MTree_Base.TREETYPE_Organization);
		return success;
	}	//	afterDelete


	/**
	 * 	Get Linked BPartner
	 *	@return C_BPartner_ID
	 */
	public int getLinkedC_BPartner_ID(String trxName)
	{
		if (m_linkedBPartner == null)
		{
			int C_BPartner_ID = DB.getSQLValue(trxName,
				"SELECT C_BPartner_ID FROM C_BPartner WHERE AD_OrgBP_ID=?",
				getAD_Org_ID());
			if (C_BPartner_ID < 0)	//	not found = -1
				C_BPartner_ID = 0;
			m_linkedBPartner = new Integer (C_BPartner_ID);
		}
		return m_linkedBPartner.intValue();
	}	//	getLinkedC_BPartner_ID
	
}	//	MOrg
