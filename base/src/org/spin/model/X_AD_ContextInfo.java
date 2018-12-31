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

/** Generated Model for AD_ContextInfo
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_AD_ContextInfo extends PO implements I_AD_ContextInfo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_AD_ContextInfo (Properties ctx, int AD_ContextInfo_ID, String trxName)
    {
      super (ctx, AD_ContextInfo_ID, trxName);
      /** if (AD_ContextInfo_ID == 0)
        {
			setAD_ContextInfo_ID (0);
			setAD_Message_ID (0);
			setName (null);
			setSQLStatement (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_AD_ContextInfo (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 4 - System 
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
      StringBuffer sb = new StringBuffer ("X_AD_ContextInfo[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Context Info.
		@param AD_ContextInfo_ID 
		Context Info Maintaining
	  */
	public void setAD_ContextInfo_ID (int AD_ContextInfo_ID)
	{
		if (AD_ContextInfo_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ContextInfo_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ContextInfo_ID, Integer.valueOf(AD_ContextInfo_ID));
	}

	/** Get Context Info.
		@return Context Info Maintaining
	  */
	public int getAD_ContextInfo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ContextInfo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Message getAD_Message() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Message)MTable.get(getCtx(), org.compiere.model.I_AD_Message.Table_Name)
			.getPO(getAD_Message_ID(), get_TrxName());	}

	/** Set Message.
		@param AD_Message_ID 
		System Message
	  */
	public void setAD_Message_ID (int AD_Message_ID)
	{
		if (AD_Message_ID < 1) 
			set_Value (COLUMNNAME_AD_Message_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Message_ID, Integer.valueOf(AD_Message_ID));
	}

	/** Get Message.
		@return System Message
	  */
	public int getAD_Message_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Message_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set SQLStatement.
		@param SQLStatement SQLStatement	  */
	public void setSQLStatement (String SQLStatement)
	{
		set_Value (COLUMNNAME_SQLStatement, SQLStatement);
	}

	/** Get SQLStatement.
		@return SQLStatement	  */
	public String getSQLStatement () 
	{
		return (String)get_Value(COLUMNNAME_SQLStatement);
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