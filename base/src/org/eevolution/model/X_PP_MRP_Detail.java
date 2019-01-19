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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for PP_MRP_Detail
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_PP_MRP_Detail extends PO implements I_PP_MRP_Detail, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_PP_MRP_Detail (Properties ctx, int PP_MRP_Detail_ID, String trxName)
    {
      super (ctx, PP_MRP_Detail_ID, trxName);
      /** if (PP_MRP_Detail_ID == 0)
        {
			setPP_MRP_Detail_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PP_MRP_Detail (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PP_MRP_Detail[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Demand No.
		@param MRP_Demand_ID 
		MRP Demand No
	  */
	public void setMRP_Demand_ID (int MRP_Demand_ID)
	{
		if (MRP_Demand_ID < 1) 
			set_Value (COLUMNNAME_MRP_Demand_ID, null);
		else 
			set_Value (COLUMNNAME_MRP_Demand_ID, Integer.valueOf(MRP_Demand_ID));
	}

	/** Get Demand No.
		@return MRP Demand No
	  */
	public int getMRP_Demand_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MRP_Demand_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Supply No.
		@param MRP_Supply_ID 
		MRP Supply No
	  */
	public void setMRP_Supply_ID (int MRP_Supply_ID)
	{
		if (MRP_Supply_ID < 1) 
			set_Value (COLUMNNAME_MRP_Supply_ID, null);
		else 
			set_Value (COLUMNNAME_MRP_Supply_ID, Integer.valueOf(MRP_Supply_ID));
	}

	/** Get Supply No.
		@return MRP Supply No
	  */
	public int getMRP_Supply_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MRP_Supply_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set MRP Detail ID.
		@param PP_MRP_Detail_ID 
		Contains the supplies chronological relationships for each demand.
	  */
	public void setPP_MRP_Detail_ID (int PP_MRP_Detail_ID)
	{
		if (PP_MRP_Detail_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_MRP_Detail_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_MRP_Detail_ID, Integer.valueOf(PP_MRP_Detail_ID));
	}

	/** Get MRP Detail ID.
		@return Contains the supplies chronological relationships for each demand.
	  */
	public int getPP_MRP_Detail_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_MRP_Detail_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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