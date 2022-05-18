/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.spin.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for AD_User_Authentication
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_AD_User_Authentication extends PO implements I_AD_User_Authentication, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220518L;

    /** Standard Constructor */
    public X_AD_User_Authentication (Properties ctx, int AD_User_Authentication_ID, String trxName)
    {
      super (ctx, AD_User_Authentication_ID, trxName);
      /** if (AD_User_Authentication_ID == 0)
        {
			setAD_AppRegistration_ID (0);
			setAD_User_Authentication_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_User_Authentication (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_User_Authentication[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.spin.model.I_AD_AppRegistration getAD_AppRegistration() throws RuntimeException
    {
		return (org.spin.model.I_AD_AppRegistration)MTable.get(getCtx(), org.spin.model.I_AD_AppRegistration.Table_Name)
			.getPO(getAD_AppRegistration_ID(), get_TrxName());	}

	/** Set Application Registration.
		@param AD_AppRegistration_ID 
		External Application Registration
	  */
	public void setAD_AppRegistration_ID (int AD_AppRegistration_ID)
	{
		if (AD_AppRegistration_ID < 1) 
			set_Value (COLUMNNAME_AD_AppRegistration_ID, null);
		else 
			set_Value (COLUMNNAME_AD_AppRegistration_ID, Integer.valueOf(AD_AppRegistration_ID));
	}

	/** Get Application Registration.
		@return External Application Registration
	  */
	public int getAD_AppRegistration_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_AppRegistration_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User Authentication.
		@param AD_User_Authentication_ID User Authentication	  */
	public void setAD_User_Authentication_ID (int AD_User_Authentication_ID)
	{
		if (AD_User_Authentication_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_User_Authentication_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_User_Authentication_ID, Integer.valueOf(AD_User_Authentication_ID));
	}

	/** Get User Authentication.
		@return User Authentication	  */
	public int getAD_User_Authentication_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_Authentication_ID);
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

	/** Set Access Token.
		@param AccessToken Access Token	  */
	public void setAccessToken (String AccessToken)
	{
		set_Value (COLUMNNAME_AccessToken, AccessToken);
	}

	/** Get Access Token.
		@return Access Token	  */
	public String getAccessToken () 
	{
		return (String)get_Value(COLUMNNAME_AccessToken);
	}

	/** Set Refresh Token.
		@param RefreshToken Refresh Token	  */
	public void setRefreshToken (String RefreshToken)
	{
		set_Value (COLUMNNAME_RefreshToken, RefreshToken);
	}

	/** Get Refresh Token.
		@return Refresh Token	  */
	public String getRefreshToken () 
	{
		return (String)get_Value(COLUMNNAME_RefreshToken);
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