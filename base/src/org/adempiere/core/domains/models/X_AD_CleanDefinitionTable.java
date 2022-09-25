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

/** Generated Model for AD_CleanDefinitionTable
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_AD_CleanDefinitionTable extends PO implements I_AD_CleanDefinitionTable, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200212L;

    /** Standard Constructor */
    public X_AD_CleanDefinitionTable (Properties ctx, int AD_CleanDefinitionTable_ID, String trxName)
    {
      super (ctx, AD_CleanDefinitionTable_ID, trxName);
      /** if (AD_CleanDefinitionTable_ID == 0)
        {
			setAD_CleanDefinition_ID (0);
			setAD_CleanDefinitionTable_ID (0);
			setAD_Table_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_CleanDefinitionTable (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_AD_CleanDefinitionTable[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.adempiere.core.domains.models.I_AD_CleanDefinition getAD_CleanDefinition() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_CleanDefinition)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_CleanDefinition.Table_Name)
			.getPO(getAD_CleanDefinition_ID(), get_TrxName());	}

	/** Set Clean Definition.
		@param AD_CleanDefinition_ID 
		Clean Definition for a specific table
	  */
	public void setAD_CleanDefinition_ID (int AD_CleanDefinition_ID)
	{
		if (AD_CleanDefinition_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_CleanDefinition_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_CleanDefinition_ID, Integer.valueOf(AD_CleanDefinition_ID));
	}

	/** Get Clean Definition.
		@return Clean Definition for a specific table
	  */
	public int getAD_CleanDefinition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_CleanDefinition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Clean Definition Table.
		@param AD_CleanDefinitionTable_ID Clean Definition Table	  */
	public void setAD_CleanDefinitionTable_ID (int AD_CleanDefinitionTable_ID)
	{
		if (AD_CleanDefinitionTable_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_CleanDefinitionTable_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_CleanDefinitionTable_ID, Integer.valueOf(AD_CleanDefinitionTable_ID));
	}

	/** Get Clean Definition Table.
		@return Clean Definition Table	  */
	public int getAD_CleanDefinitionTable_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_CleanDefinitionTable_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Table)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Table.Table_Name)
			.getPO(getAD_Table_ID(), get_TrxName());	}

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
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