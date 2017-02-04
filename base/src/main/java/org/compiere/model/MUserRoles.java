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

import org.compiere.util.CLogger;

/**
 *	User Roles Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MUserRoles.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MUserRoles extends X_AD_User_Roles
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2659323298844769713L;


	/**
	 * 	Get User Roles Of Role
	 *	@param ctx context
	 *	@param AD_Role_ID role
	 *	@return array of user roles
	 */
	public static MUserRoles[] getOfRole (Properties ctx, int AD_Role_ID)
	{
		final String whereClause = I_AD_User_Roles.COLUMNNAME_AD_Role_ID+"=?";	
		List<MUserRoles> list = new Query(ctx,I_AD_User_Roles.Table_Name,whereClause,null)
		.setParameters(AD_Role_ID)
		.list();
		MUserRoles[] retValue = new MUserRoles[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getOfRole

	/**
	 * 	Get User Roles Of User
	 *	@param ctx context
	 *	@param AD_User_ID role
	 *	@return array of user roles
	 */
	public static MUserRoles[] getOfUser (Properties ctx, int AD_User_ID)
	{
		final String whereClause = I_AD_User_Roles.COLUMNNAME_AD_User_ID+"=?";	
		List<MUserRoles> list = new Query(ctx,I_AD_User_Roles.Table_Name,whereClause,null)
		.setParameters(AD_User_ID)
		.list();
		MUserRoles[] retValue = new MUserRoles[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getOfUser

	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MUserRoles.class);

	
	/**************************************************************************
	 * 	Persistence Constructor
	 *	@param ctx context
	 *	@param ignored invalid
	 *	@param trxName transaction
	 */
	public MUserRoles (Properties ctx, int ignored, String trxName)
	{
		super (ctx, ignored, trxName);
		if (ignored != 0)
			throw new IllegalArgumentException("Multi-Key");
	}	//	MUserRoles

	/**
	 * 	Load constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MUserRoles (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MUserRoles

	/**
	 * 	Full Constructor
	 *	@param ctx context
	 *	@param AD_User_ID user
	 *	@param AD_Role_ID role
	 *	@param trxName transaction
	 */
	public MUserRoles (Properties ctx, int AD_User_ID, int AD_Role_ID, String trxName)
	{
		this (ctx, 0, trxName);
		setAD_User_ID(AD_User_ID);
		setAD_Role_ID(AD_Role_ID);
	}	//	MUserRoles
	
	/** 
	 * 	Set User/Contact.
	 *	User within the system - Internal or Business Partner Contact
	 *	@param AD_User_ID user 
	 */
	public void setAD_User_ID (int AD_User_ID)
	{
		set_ValueNoCheck ("AD_User_ID", new Integer(AD_User_ID));
	}	//	setAD_User_ID
	
	/** 
	 * 	Set Role.
	 * 	Responsibility Role
	 * 	@param AD_Role_ID role 
	 **/
	public void setAD_Role_ID (int AD_Role_ID)
	{
		set_ValueNoCheck ("AD_Role_ID", new Integer(AD_Role_ID));
	}	//	setAD_Role_ID

}	//	MUserRoles
