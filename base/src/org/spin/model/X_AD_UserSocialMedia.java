/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.spin.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_UserSocialMedia
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_AD_UserSocialMedia extends PO implements I_AD_UserSocialMedia, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_AD_UserSocialMedia (Properties ctx, int AD_UserSocialMedia_ID, String trxName)
    {
      super (ctx, AD_UserSocialMedia_ID, trxName);
      /** if (AD_UserSocialMedia_ID == 0)
        {
			setAD_AppSupport_ID (0);
			setAD_UserSocialMedia_ID (0);
			setAD_User_ID (0);
			setAccountName (null);
			setApplicationType (null);
// SMN
        } */
    }

    /** Load Constructor */
    public X_AD_UserSocialMedia (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_AD_UserSocialMedia[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.spin.model.I_AD_AppSupport getAD_AppSupport() throws RuntimeException
    {
		return (org.spin.model.I_AD_AppSupport)MTable.get(getCtx(), org.spin.model.I_AD_AppSupport.Table_Name)
			.getPO(getAD_AppSupport_ID(), get_TrxName());	}

	/** Set App Support.
		@param AD_AppSupport_ID 
		App Support for External Connection
	  */
	public void setAD_AppSupport_ID (int AD_AppSupport_ID)
	{
		if (AD_AppSupport_ID < 1) 
			set_Value (COLUMNNAME_AD_AppSupport_ID, null);
		else 
			set_Value (COLUMNNAME_AD_AppSupport_ID, Integer.valueOf(AD_AppSupport_ID));
	}

	/** Get App Support.
		@return App Support for External Connection
	  */
	public int getAD_AppSupport_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_AppSupport_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Social Media.
		@param AD_UserSocialMedia_ID 
		User External Link for Social Media
	  */
	public void setAD_UserSocialMedia_ID (int AD_UserSocialMedia_ID)
	{
		if (AD_UserSocialMedia_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_UserSocialMedia_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_UserSocialMedia_ID, Integer.valueOf(AD_UserSocialMedia_ID));
	}

	/** Get Social Media.
		@return User External Link for Social Media
	  */
	public int getAD_UserSocialMedia_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_UserSocialMedia_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Account Name.
		@param AccountName Account Name	  */
	public void setAccountName (String AccountName)
	{
		set_Value (COLUMNNAME_AccountName, AccountName);
	}

	/** Get Account Name.
		@return Account Name	  */
	public String getAccountName () 
	{
		return (String)get_Value(COLUMNNAME_AccountName);
	}

	/** ApplicationType AD_Reference_ID=54081 */
	public static final int APPLICATIONTYPE_AD_Reference_ID=54081;
	/** Message Queue = MQS */
	public static final String APPLICATIONTYPE_MessageQueue = "MQS";
	/** WebDav Application = WDV */
	public static final String APPLICATIONTYPE_WebDavApplication = "WDV";
	/** Social Media = SMN */
	public static final String APPLICATIONTYPE_SocialMedia = "SMN";
	/** Set Application Type.
		@param ApplicationType 
		Application Type, used for identify a Application Type like Message Queue
	  */
	public void setApplicationType (String ApplicationType)
	{

		set_Value (COLUMNNAME_ApplicationType, ApplicationType);
	}

	/** Get Application Type.
		@return Application Type, used for identify a Application Type like Message Queue
	  */
	public String getApplicationType () 
	{
		return (String)get_Value(COLUMNNAME_ApplicationType);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getApplicationType()));
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}
}