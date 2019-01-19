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
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for WM_Section
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_WM_Section extends PO implements I_WM_Section, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_WM_Section (Properties ctx, int WM_Section_ID, String trxName)
    {
      super (ctx, WM_Section_ID, trxName);
      /** if (WM_Section_ID == 0)
        {
			setName (null);
			setWM_Area_ID (0);
			setWM_Section_ID (0);
			setWM_Section_Type_ID (0);
        } */
    }

    /** Load Constructor */
    public X_WM_Section (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_WM_Section[")
        .append(get_ID()).append("]");
      return sb.toString();
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
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

	public org.eevolution.model.I_WM_Area getWM_Area() throws RuntimeException
    {
		return (org.eevolution.model.I_WM_Area)MTable.get(getCtx(), org.eevolution.model.I_WM_Area.Table_Name)
			.getPO(getWM_Area_ID(), get_TrxName());	}

	/** Set Warehouse Area.
		@param WM_Area_ID 
		Warehouse Area allow grouping the Warehouse Section
	  */
	public void setWM_Area_ID (int WM_Area_ID)
	{
		if (WM_Area_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_WM_Area_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_WM_Area_ID, Integer.valueOf(WM_Area_ID));
	}

	/** Get Warehouse Area.
		@return Warehouse Area allow grouping the Warehouse Section
	  */
	public int getWM_Area_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_Area_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Warehouse Section.
		@param WM_Section_ID 
		The Warehouse Section is an grouping of Locators with similar features.
	  */
	public void setWM_Section_ID (int WM_Section_ID)
	{
		if (WM_Section_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_WM_Section_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_WM_Section_ID, Integer.valueOf(WM_Section_ID));
	}

	/** Get Warehouse Section.
		@return The Warehouse Section is an grouping of Locators with similar features.
	  */
	public int getWM_Section_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_Section_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_WM_Section_Type getWM_Section_Type() throws RuntimeException
    {
		return (org.eevolution.model.I_WM_Section_Type)MTable.get(getCtx(), org.eevolution.model.I_WM_Section_Type.Table_Name)
			.getPO(getWM_Section_Type_ID(), get_TrxName());	}

	/** Set Warehouse Section Type.
		@param WM_Section_Type_ID Warehouse Section Type	  */
	public void setWM_Section_Type_ID (int WM_Section_Type_ID)
	{
		if (WM_Section_Type_ID < 1) 
			set_Value (COLUMNNAME_WM_Section_Type_ID, null);
		else 
			set_Value (COLUMNNAME_WM_Section_Type_ID, Integer.valueOf(WM_Section_Type_ID));
	}

	/** Get Warehouse Section Type.
		@return Warehouse Section Type	  */
	public int getWM_Section_Type_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_Section_Type_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}