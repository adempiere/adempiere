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

/** Generated Model for WM_Strategy_Detail
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_WM_Strategy_Detail extends PO implements I_WM_Strategy_Detail, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_WM_Strategy_Detail (Properties ctx, int WM_Strategy_Detail_ID, String trxName)
    {
      super (ctx, WM_Strategy_Detail_ID, trxName);
      /** if (WM_Strategy_Detail_ID == 0)
        {
			setWM_Strategy_Detail_ID (0);
        } */
    }

    /** Load Constructor */
    public X_WM_Strategy_Detail (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_WM_Strategy_Detail[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
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

	public org.eevolution.model.I_WM_Rule getWM_Rule() throws RuntimeException
    {
		return (org.eevolution.model.I_WM_Rule)MTable.get(getCtx(), org.eevolution.model.I_WM_Rule.Table_Name)
			.getPO(getWM_Rule_ID(), get_TrxName());	}

	/** Set Inbound & Outbound Rule.
		@param WM_Rule_ID Inbound & Outbound Rule	  */
	public void setWM_Rule_ID (int WM_Rule_ID)
	{
		if (WM_Rule_ID < 1) 
			set_Value (COLUMNNAME_WM_Rule_ID, null);
		else 
			set_Value (COLUMNNAME_WM_Rule_ID, Integer.valueOf(WM_Rule_ID));
	}

	/** Get Inbound & Outbound Rule.
		@return Inbound & Outbound Rule	  */
	public int getWM_Rule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_Rule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Inbound & Outbound Strategy Detail ID.
		@param WM_Strategy_Detail_ID Inbound & Outbound Strategy Detail ID	  */
	public void setWM_Strategy_Detail_ID (int WM_Strategy_Detail_ID)
	{
		if (WM_Strategy_Detail_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_WM_Strategy_Detail_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_WM_Strategy_Detail_ID, Integer.valueOf(WM_Strategy_Detail_ID));
	}

	/** Get Inbound & Outbound Strategy Detail ID.
		@return Inbound & Outbound Strategy Detail ID	  */
	public int getWM_Strategy_Detail_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_Strategy_Detail_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_WM_Strategy getWM_Strategy() throws RuntimeException
    {
		return (org.eevolution.model.I_WM_Strategy)MTable.get(getCtx(), org.eevolution.model.I_WM_Strategy.Table_Name)
			.getPO(getWM_Strategy_ID(), get_TrxName());	}

	/** Set Warehouse Managamet Strategy.
		@param WM_Strategy_ID Warehouse Managamet Strategy	  */
	public void setWM_Strategy_ID (int WM_Strategy_ID)
	{
		if (WM_Strategy_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_WM_Strategy_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_WM_Strategy_ID, Integer.valueOf(WM_Strategy_ID));
	}

	/** Get Warehouse Managamet Strategy.
		@return Warehouse Managamet Strategy	  */
	public int getWM_Strategy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_Strategy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}