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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Msg;


/**
 *	User Org Access
 *	
 *  @author Jorg Janke
 *  @version $Id: MUserOrgAccess.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MUserOrgAccess extends X_AD_User_OrgAccess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 11601583764711895L;


	/**
	 * 	Get Organizational Access of User
	 *	@param ctx context
	 *	@param AD_User_ID user
	 *	@return array of User Org Access
	 */
	public static MUserOrgAccess[] getOfUser (Properties ctx, int AD_User_ID)
	{
		return get (ctx, "SELECT * FROM AD_User_OrgAccess WHERE AD_User_ID=?", AD_User_ID);	
	}	//	getOfUser

	/**
	 * 	Get Organizational Info
	 *	@param ctx context
	 *	@param sql sql command
	 *	@param id id
	 *	@return array of User Org Access
	 */
	private static MUserOrgAccess[] get (Properties ctx, String sql, int id)
	{
		ArrayList<MUserOrgAccess> list = new ArrayList<MUserOrgAccess>();
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, id);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MUserOrgAccess(ctx, rs, null));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
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
		MUserOrgAccess[] retValue = new MUserOrgAccess[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	get
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MUserOrgAccess.class);

	
	/**************************************************************************
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MUserOrgAccess (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MUserOrgAccess

	/**
	 * 	Persistency Constructor
	 *	@param ctx context
	 *	@param ignored ignored
	 *	@param trxName transaction
	 */
	public MUserOrgAccess (Properties ctx, int ignored, String trxName)
	{
		super(ctx, 0, trxName);
		if (ignored != 0)
			throw new IllegalArgumentException("Multi-Key");
		setIsReadOnly(false);
	}	//	MUserOrgAccess
	
	/**
	 * 	Organization Constructor
	 *	@param org org
	 *	@param AD_User_ID role
	 */
	public MUserOrgAccess (MOrg org, int AD_User_ID)
	{
		this (org.getCtx(), 0, org.get_TrxName());
		setClientOrg (org);
		setAD_User_ID (AD_User_ID);
	}	//	MUserOrgAccess

	/**
	 * 	User Constructor
	 *	param user user
	 *	param AD_Org_ID org
	 *
	public MUserOrgAccess (MUser user, int AD_Org_ID)
	{
		this (user.getCtx(), 0, user.get_TrxName());
		setClientOrg (user.getAD_Client_ID(), AD_Org_ID);
		setAD_User_ID (user.getAD_User_ID());
	}	//	MUserOrgAccess

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MUserOrgAccess[");
		sb.append("AD_User_ID=").append(getAD_User_ID())
			.append(",AD_Client_ID=").append(getAD_Client_ID())
			.append(",AD_Org_ID=").append(getAD_Org_ID())
			.append(",RO=").append(isReadOnly());	
		sb.append("]");
		return sb.toString();
	}	//	toString

	
	/**************************************************************************
	 * 	Extended String Representation
	 * 	@param ctx context
	 *	@return extended info
	 */
	public String toStringX (Properties ctx)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(Msg.translate(ctx, "AD_Client_ID")).append("=").append(getClientName()).append(" - ")
			.append(Msg.translate(ctx, "AD_Org_ID")).append("=").append(getOrgName());	
		return sb.toString();
	}	//	toStringX

	private String	m_clientName;
	private String	m_orgName;
	
	/**
	 * 	Get Client Name
	 *	@return name
	 */
	public String getClientName()
	{
		if (m_clientName == null)
		{
			String sql = "SELECT c.Name, o.Name "
				+ "FROM AD_Client c INNER JOIN AD_Org o ON (c.AD_Client_ID=o.AD_Client_ID) "
				+ "WHERE o.AD_Org_ID=?";
			PreparedStatement pstmt = null;
			try
			{
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, getAD_Org_ID());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
				{
					m_clientName = rs.getString(1);
					m_orgName = rs.getString(2);
				}
				rs.close();
				pstmt.close();
				pstmt = null;
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			try
			{
				if (pstmt != null)
					pstmt.close();
				pstmt = null;
			}
			catch (Exception e)
			{
				pstmt = null;
			}
		}
		return m_clientName;
	}	//	getClientName
	
	/**
	 * 	Get Client Name
	 *	@return name
	 */
	public String getOrgName()
	{
		if (m_orgName == null)
			getClientName();
		return m_orgName;
	}	//	getOrgName

}	//	MUserOrgAccess
