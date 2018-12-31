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

/** Generated Model for WM_Section_Type
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_WM_Section_Type extends PO implements I_WM_Section_Type, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_WM_Section_Type (Properties ctx, int WM_Section_Type_ID, String trxName)
    {
      super (ctx, WM_Section_Type_ID, trxName);
      /** if (WM_Section_Type_ID == 0)
        {
			setInOutBoundType (null);
			setName (null);
			setWM_Section_Type_ID (0);
        } */
    }

    /** Load Constructor */
    public X_WM_Section_Type (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_WM_Section_Type[")
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

	/** InOutBoundType AD_Reference_ID=53321 */
	public static final int INOUTBOUNDTYPE_AD_Reference_ID=53321;
	/** Outbound Operation = O */
	public static final String INOUTBOUNDTYPE_OutboundOperation = "O";
	/** Inbound Operation = I */
	public static final String INOUTBOUNDTYPE_InboundOperation = "I";
	/** Set Inbound & Outbound Type.
		@param InOutBoundType 
		Inbound & Outbound Type
	  */
	public void setInOutBoundType (String InOutBoundType)
	{

		set_Value (COLUMNNAME_InOutBoundType, InOutBoundType);
	}

	/** Get Inbound & Outbound Type.
		@return Inbound & Outbound Type
	  */
	public String getInOutBoundType () 
	{
		return (String)get_Value(COLUMNNAME_InOutBoundType);
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

	/** Set Warehouse Section Type.
		@param WM_Section_Type_ID Warehouse Section Type	  */
	public void setWM_Section_Type_ID (int WM_Section_Type_ID)
	{
		if (WM_Section_Type_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_WM_Section_Type_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_WM_Section_Type_ID, Integer.valueOf(WM_Section_Type_ID));
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