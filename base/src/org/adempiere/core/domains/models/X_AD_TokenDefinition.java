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
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_TokenDefinition
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_AD_TokenDefinition extends PO implements I_AD_TokenDefinition, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20201118L;

    /** Standard Constructor */
    public X_AD_TokenDefinition (Properties ctx, int AD_TokenDefinition_ID, String trxName)
    {
      super (ctx, AD_TokenDefinition_ID, trxName);
      /** if (AD_TokenDefinition_ID == 0)
        {
			setAD_TokenDefinition_ID (0);
			setName (null);
			setTokenType (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_AD_TokenDefinition (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_TokenDefinition[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Token Definition.
		@param AD_TokenDefinition_ID 
		Token Definition, used for define generator class for token
	  */
	public void setAD_TokenDefinition_ID (int AD_TokenDefinition_ID)
	{
		if (AD_TokenDefinition_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_TokenDefinition_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_TokenDefinition_ID, Integer.valueOf(AD_TokenDefinition_ID));
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

	/** Set Classname.
		@param Classname 
		Java Classname
	  */
	public void setClassname (String Classname)
	{
		set_Value (COLUMNNAME_Classname, Classname);
	}

	/** Get Classname.
		@return Java Classname
	  */
	public String getClassname () 
	{
		return (String)get_Value(COLUMNNAME_Classname);
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

	/** Set Expiration Time.
		@param ExpirationTime 
		Expiration Time for Token in milliseconds, default 5 minutes
	  */
	public void setExpirationTime (BigDecimal ExpirationTime)
	{
		set_Value (COLUMNNAME_ExpirationTime, ExpirationTime);
	}

	/** Get Expiration Time.
		@return Expiration Time for Token in milliseconds, default 5 minutes
	  */
	public BigDecimal getExpirationTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ExpirationTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Has Expire Date.
		@param IsHasExpireDate 
		Has Expire Date for generated token
	  */
	public void setIsHasExpireDate (boolean IsHasExpireDate)
	{
		set_Value (COLUMNNAME_IsHasExpireDate, Boolean.valueOf(IsHasExpireDate));
	}

	/** Get Has Expire Date.
		@return Has Expire Date for generated token
	  */
	public boolean isHasExpireDate () 
	{
		Object oo = get_Value(COLUMNNAME_IsHasExpireDate);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** TokenType AD_Reference_ID=54071 */
	public static final int TOKENTYPE_AD_Reference_ID=54071;
	/** SMS (Short Message Service)  = SMS */
	public static final String TOKENTYPE_SMSShortMessageService = "SMS";
	/** EMail (E-Mail for User with Code) = EMA */
	public static final String TOKENTYPE_EMailE_MailForUserWithCode = "EMA";
	/** URL (Token used as URL) = URL */
	public static final String TOKENTYPE_URLTokenUsedAsURL = "URL";
	/** Third Party Access = TPA */
	public static final String TOKENTYPE_ThirdPartyAccess = "TPA";
	/** Manual = MNA */
	public static final String TOKENTYPE_Manual = "MNA";
	/** Set TokenType.
		@param TokenType 
		Wiki Token Type
	  */
	public void setTokenType (String TokenType)
	{

		set_ValueNoCheck (COLUMNNAME_TokenType, TokenType);
	}

	/** Get TokenType.
		@return Wiki Token Type
	  */
	public String getTokenType () 
	{
		return (String)get_Value(COLUMNNAME_TokenType);
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }
}