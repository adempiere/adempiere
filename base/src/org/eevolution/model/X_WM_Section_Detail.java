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

/** Generated Model for WM_Section_Detail
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_WM_Section_Detail extends PO implements I_WM_Section_Detail, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_WM_Section_Detail (Properties ctx, int WM_Section_Detail_ID, String trxName)
    {
      super (ctx, WM_Section_Detail_ID, trxName);
      /** if (WM_Section_Detail_ID == 0)
        {
			setM_Locator_ID (0);
			setWM_Section_Detail_ID (0);
			setWM_Section_ID (0);
        } */
    }

    /** Load Constructor */
    public X_WM_Section_Detail (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_WM_Section_Detail[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_M_Locator getM_Locator() throws RuntimeException
    {
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
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

	/** Set Warehouse Section Detail.
		@param WM_Section_Detail_ID 
		Define the Locations that belong to this section
	  */
	public void setWM_Section_Detail_ID (int WM_Section_Detail_ID)
	{
		if (WM_Section_Detail_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_WM_Section_Detail_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_WM_Section_Detail_ID, Integer.valueOf(WM_Section_Detail_ID));
	}

	/** Get Warehouse Section Detail.
		@return Define the Locations that belong to this section
	  */
	public int getWM_Section_Detail_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_Section_Detail_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_WM_Section getWM_Section() throws RuntimeException
    {
		return (org.eevolution.model.I_WM_Section)MTable.get(getCtx(), org.eevolution.model.I_WM_Section.Table_Name)
			.getPO(getWM_Section_ID(), get_TrxName());	}

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
}