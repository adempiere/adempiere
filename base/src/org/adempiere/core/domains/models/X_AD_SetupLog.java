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

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_SetupLog
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_AD_SetupLog extends PO implements I_AD_SetupLog, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210104L;

    /** Standard Constructor */
    public X_AD_SetupLog (Properties ctx, int AD_SetupLog_ID, String trxName)
    {
      super (ctx, AD_SetupLog_ID, trxName);
      /** if (AD_SetupLog_ID == 0)
        {
			setAD_SetupDefinition_ID (0);
			setAD_SetupLog_ID (0);
			setIsFuncionalityApplied (false);
// N
        } */
    }

    /** Load Constructor */
    public X_AD_SetupLog (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_SetupLog[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.adempiere.core.domains.models.I_AD_SetupDefinition getAD_SetupDefinition() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_SetupDefinition)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_SetupDefinition.Table_Name)
			.getPO(getAD_SetupDefinition_ID(), get_TrxName());	}

	/** Set Setup Definition.
		@param AD_SetupDefinition_ID 
		Setup Definition
	  */
	public void setAD_SetupDefinition_ID (int AD_SetupDefinition_ID)
	{
		if (AD_SetupDefinition_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_SetupDefinition_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_SetupDefinition_ID, Integer.valueOf(AD_SetupDefinition_ID));
	}

	/** Get Setup Definition.
		@return Setup Definition
	  */
	public int getAD_SetupDefinition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_SetupDefinition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_SetupDefinition_ID()));
    }

	/** Set Setup Log.
		@param AD_SetupLog_ID 
		Setup Lob by Client
	  */
	public void setAD_SetupLog_ID (int AD_SetupLog_ID)
	{
		if (AD_SetupLog_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_SetupLog_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_SetupLog_ID, Integer.valueOf(AD_SetupLog_ID));
	}

	/** Get Setup Log.
		@return Setup Lob by Client
	  */
	public int getAD_SetupLog_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_SetupLog_ID);
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

	/** Set Applied.
		@param IsFuncionalityApplied 
		Change Applied
	  */
	public void setIsFuncionalityApplied (boolean IsFuncionalityApplied)
	{
		set_Value (COLUMNNAME_IsFuncionalityApplied, Boolean.valueOf(IsFuncionalityApplied));
	}

	/** Get Applied.
		@return Change Applied
	  */
	public boolean isFuncionalityApplied () 
	{
		Object oo = get_Value(COLUMNNAME_IsFuncionalityApplied);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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