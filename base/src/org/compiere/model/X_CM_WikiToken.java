/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.KeyNamePair;

/** Generated Model for CM_WikiToken
 *  @author Adempiere (generated) 
 *  @version Release 3.4.0s - $Id$ */
public class X_CM_WikiToken extends PO implements I_CM_WikiToken, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_CM_WikiToken (Properties ctx, int CM_WikiToken_ID, String trxName)
    {
      super (ctx, CM_WikiToken_ID, trxName);
      /** if (CM_WikiToken_ID == 0)
        {
			setCM_WikiToken_ID (0);
			setName (null);
			setTokenType (null);
// I
        } */
    }

    /** Load Constructor */
    public X_CM_WikiToken (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_CM_WikiToken[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Table getAD_Table() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_Table.Table_Name);
        I_AD_Table result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_Table)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_Table_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Table_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Wiki Token.
		@param CM_WikiToken_ID 
		Wiki Token
	  */
	public void setCM_WikiToken_ID (int CM_WikiToken_ID)
	{
		if (CM_WikiToken_ID < 1)
			 throw new IllegalArgumentException ("CM_WikiToken_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_CM_WikiToken_ID, Integer.valueOf(CM_WikiToken_ID));
	}

	/** Get Wiki Token.
		@return Wiki Token
	  */
	public int getCM_WikiToken_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CM_WikiToken_ID);
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

		if (Description != null && Description.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			Description = Description.substring(0, 255);
		}
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Macro.
		@param Macro 
		Macro
	  */
	public void setMacro (String Macro)
	{

		if (Macro != null && Macro.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			Macro = Macro.substring(0, 2000);
		}
		set_Value (COLUMNNAME_Macro, Macro);
	}

	/** Get Macro.
		@return Macro
	  */
	public String getMacro () 
	{
		return (String)get_Value(COLUMNNAME_Macro);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");

		if (Name.length() > 120)
		{
			log.warning("Length > 120 - truncated");
			Name = Name.substring(0, 120);
		}
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Sql SELECT.
		@param SelectClause 
		SQL SELECT clause
	  */
	public void setSelectClause (String SelectClause)
	{

		if (SelectClause != null && SelectClause.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			SelectClause = SelectClause.substring(0, 2000);
		}
		set_Value (COLUMNNAME_SelectClause, SelectClause);
	}

	/** Get Sql SELECT.
		@return SQL SELECT clause
	  */
	public String getSelectClause () 
	{
		return (String)get_Value(COLUMNNAME_SelectClause);
	}

	/** TokenType AD_Reference_ID=397 */
	public static final int TOKENTYPE_AD_Reference_ID=397;
	/** SQL Command = Q */
	public static final String TOKENTYPE_SQLCommand = "Q";
	/** Internal Link = I */
	public static final String TOKENTYPE_InternalLink = "I";
	/** External Link = E */
	public static final String TOKENTYPE_ExternalLink = "E";
	/** Style = S */
	public static final String TOKENTYPE_Style = "S";
	/** Set TokenType.
		@param TokenType 
		Wiki Token Type
	  */
	public void setTokenType (String TokenType)
	{
		if (TokenType == null) throw new IllegalArgumentException ("TokenType is mandatory");
		if (TokenType.equals("Q") || TokenType.equals("I") || TokenType.equals("E") || TokenType.equals("S")); else throw new IllegalArgumentException ("TokenType Invalid value - " + TokenType + " - Reference_ID=397 - Q - I - E - S");
		if (TokenType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			TokenType = TokenType.substring(0, 1);
		}
		set_Value (COLUMNNAME_TokenType, TokenType);
	}

	/** Get TokenType.
		@return Wiki Token Type
	  */
	public String getTokenType () 
	{
		return (String)get_Value(COLUMNNAME_TokenType);
	}

	/** Set Sql WHERE.
		@param WhereClause 
		Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause)
	{

		if (WhereClause != null && WhereClause.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			WhereClause = WhereClause.substring(0, 2000);
		}
		set_Value (COLUMNNAME_WhereClause, WhereClause);
	}

	/** Get Sql WHERE.
		@return Fully qualified SQL WHERE clause
	  */
	public String getWhereClause () 
	{
		return (String)get_Value(COLUMNNAME_WhereClause);
	}
}