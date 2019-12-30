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
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for K_EntryCategory
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_K_EntryCategory extends PO implements I_K_EntryCategory, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_K_EntryCategory (Properties ctx, int K_EntryCategory_ID, String trxName)
    {
      super (ctx, K_EntryCategory_ID, trxName);
      /** if (K_EntryCategory_ID == 0)
        {
			setK_CategoryValue_ID (0);
			setK_Category_ID (0);
			setK_Entry_ID (0);
        } */
    }

    /** Load Constructor */
    public X_K_EntryCategory (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_K_EntryCategory[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_K_CategoryValue getK_CategoryValue() throws RuntimeException
    {
		return (org.compiere.model.I_K_CategoryValue)MTable.get(getCtx(), org.compiere.model.I_K_CategoryValue.Table_Name)
			.getPO(getK_CategoryValue_ID(), get_TrxName());	}

	/** Set Category Value.
		@param K_CategoryValue_ID 
		The value of the category
	  */
	public void setK_CategoryValue_ID (int K_CategoryValue_ID)
	{
		if (K_CategoryValue_ID < 1) 
			set_Value (COLUMNNAME_K_CategoryValue_ID, null);
		else 
			set_Value (COLUMNNAME_K_CategoryValue_ID, Integer.valueOf(K_CategoryValue_ID));
	}

	/** Get Category Value.
		@return The value of the category
	  */
	public int getK_CategoryValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_K_CategoryValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getK_CategoryValue_ID()));
    }

	public org.compiere.model.I_K_Category getK_Category() throws RuntimeException
    {
		return (org.compiere.model.I_K_Category)MTable.get(getCtx(), org.compiere.model.I_K_Category.Table_Name)
			.getPO(getK_Category_ID(), get_TrxName());	}

	/** Set Knowledge Category.
		@param K_Category_ID 
		Knowledge Category
	  */
	public void setK_Category_ID (int K_Category_ID)
	{
		if (K_Category_ID < 1) 
			set_Value (COLUMNNAME_K_Category_ID, null);
		else 
			set_Value (COLUMNNAME_K_Category_ID, Integer.valueOf(K_Category_ID));
	}

	/** Get Knowledge Category.
		@return Knowledge Category
	  */
	public int getK_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_K_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_K_Entry getK_Entry() throws RuntimeException
    {
		return (org.compiere.model.I_K_Entry)MTable.get(getCtx(), org.compiere.model.I_K_Entry.Table_Name)
			.getPO(getK_Entry_ID(), get_TrxName());	}

	/** Set Entry.
		@param K_Entry_ID 
		Knowledge Entry
	  */
	public void setK_Entry_ID (int K_Entry_ID)
	{
		if (K_Entry_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_K_Entry_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_K_Entry_ID, Integer.valueOf(K_Entry_ID));
	}

	/** Get Entry.
		@return Knowledge Entry
	  */
	public int getK_Entry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_K_Entry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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