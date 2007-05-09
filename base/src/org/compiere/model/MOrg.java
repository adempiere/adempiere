/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.util.*;

/**
 *	Organization Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MOrg.java,v 1.3 2006/07/30 00:58:04 jjanke Exp $
 */
public class MOrg extends X_AD_Org
{
	/**
	 * 	Get Organizations Of Client
	 *	@param po persistent object
	 *	@return array of orgs
	 */
	public static MOrg[] getOfClient (PO po)
	{
		ArrayList<MOrg> list = new ArrayList<MOrg>();
		String sql = "SELECT * FROM AD_Org WHERE AD_Client_ID=? ORDER BY Value";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, po.getAD_Client_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MOrg (po.getCtx(), rs, null));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		
		MOrg[] retValue = new MOrg[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getOfClient
	
	/**
	 * 	Get Org from Cache
	 *	@param ctx context
	 *	@param AD_Org_ID id
	 *	@return MOrg
	 */
	public static MOrg get (Properties ctx, int AD_Org_ID)
	{
		Integer key = new Integer (AD_Org_ID);
		MOrg retValue = (MOrg) s_cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MOrg (ctx, AD_Org_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get

	/**	Logger	*/
	private static CLogger 			s_log = CLogger.getCLogger (MOrg.class);
	/**	Cache						*/
	private static CCache<Integer,MOrg>	s_cache	= new CCache<Integer,MOrg>("AD_Org", 20);
	
	
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
	public MOrg (MClient client, String name)
	{
		this (client.getCtx(), 0, client.get_TrxName());
		setAD_Client_ID (client.getAD_Client_ID());
		setValue (name);
		setName (name);
	}	//	MOrg

	/**	Org Info						*/
	private MOrgInfo	m_info = null;
	/**	Linked Business Partner			*/
	private Integer 	m_linkedBPartner = null;

	/**
	 *	Get Org Info
	 *	@return Org Info
	 */
	public MOrgInfo getInfo()
	{
		if (m_info == null)
			m_info = MOrgInfo.get (getCtx(), getAD_Org_ID());
		return m_info;
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
			m_info = new MOrgInfo (this);
			m_info.save();
			//	Access
			MRoleOrgAccess.createForOrg (this);
			MRole.getDefault(getCtx(), true);	//	reload
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
