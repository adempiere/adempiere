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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for AD_Token
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_AD_Token extends PO implements I_AD_Token, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_AD_Token (Properties ctx, int AD_Token_ID, String trxName)
    {
      super (ctx, AD_Token_ID, trxName);
      /** if (AD_Token_ID == 0)
        {
			setAD_TokenDefinition_ID (0);
			setAD_Token_ID (0);
			setExpireDate (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_AD_Token (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_AD_Token[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.spin.model.I_AD_TokenDefinition getAD_TokenDefinition() throws RuntimeException
    {
		return (org.spin.model.I_AD_TokenDefinition)MTable.get(getCtx(), org.spin.model.I_AD_TokenDefinition.Table_Name)
			.getPO(getAD_TokenDefinition_ID(), get_TrxName());	}

	/** Set Token Definition.
		@param AD_TokenDefinition_ID 
		Token Definition, used for define generator class for token
	  */
	public void setAD_TokenDefinition_ID (int AD_TokenDefinition_ID)
	{
		if (AD_TokenDefinition_ID < 1) 
			set_Value (COLUMNNAME_AD_TokenDefinition_ID, null);
		else 
			set_Value (COLUMNNAME_AD_TokenDefinition_ID, Integer.valueOf(AD_TokenDefinition_ID));
	}

	/** Get Token Definition.
		@return Token Definition, used for define generator class for token
	  */
	public int getAD_TokenDefinition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_TokenDefinition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Token.
		@param AD_Token_ID 
		Token for validation and approval
	  */
	public void setAD_Token_ID (int AD_Token_ID)
	{
		if (AD_Token_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Token_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Token_ID, Integer.valueOf(AD_Token_ID));
	}

	/** Get Token.
		@return Token for validation and approval
	  */
	public int getAD_Token_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Token_ID);
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
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
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

	/** Set Expire Date.
		@param ExpireDate Expire Date	  */
	public void setExpireDate (Timestamp ExpireDate)
	{
		set_Value (COLUMNNAME_ExpireDate, ExpireDate);
	}

	/** Get Expire Date.
		@return Expire Date	  */
	public Timestamp getExpireDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ExpireDate);
	}

	/** Set Token Value.
		@param TokenValue 
		Value of Token generated
	  */
	public void setTokenValue (String TokenValue)
	{
		set_Value (COLUMNNAME_TokenValue, TokenValue);
	}

	/** Get Token Value.
		@return Value of Token generated
	  */
	public String getTokenValue () 
	{
		return (String)get_Value(COLUMNNAME_TokenValue);
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