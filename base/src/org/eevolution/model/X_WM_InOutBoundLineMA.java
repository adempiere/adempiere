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

/** Generated Model for WM_InOutBoundLineMA
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_WM_InOutBoundLineMA extends PO implements I_WM_InOutBoundLineMA, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_WM_InOutBoundLineMA (Properties ctx, int WM_InOutBoundLineMA_ID, String trxName)
    {
      super (ctx, WM_InOutBoundLineMA_ID, trxName);
      /** if (WM_InOutBoundLineMA_ID == 0)
        {
			setWM_InOutBoundLineMA_ID (0);
			setWM_InOutBoundLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_WM_InOutBoundLineMA (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_WM_InOutBoundLineMA[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException
    {
		return (I_M_AttributeSetInstance)MTable.get(getCtx(), I_M_AttributeSetInstance.Table_Name)
			.getPO(getM_AttributeSetInstance_ID(), get_TrxName());	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0) 
			set_ValueNoCheck (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_InOutLine getM_InOutLine() throws RuntimeException
    {
		return (org.compiere.model.I_M_InOutLine)MTable.get(getCtx(), org.compiere.model.I_M_InOutLine.Table_Name)
			.getPO(getM_InOutLine_ID(), get_TrxName());	}

	/** Set Shipment/Receipt Line.
		@param M_InOutLine_ID 
		Line on Shipment or Receipt document
	  */
	public void setM_InOutLine_ID (int M_InOutLine_ID)
	{
		if (M_InOutLine_ID < 1) 
			set_Value (COLUMNNAME_M_InOutLine_ID, null);
		else 
			set_Value (COLUMNNAME_M_InOutLine_ID, Integer.valueOf(M_InOutLine_ID));
	}

	/** Get Shipment/Receipt Line.
		@return Line on Shipment or Receipt document
	  */
	public int getM_InOutLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InOutLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Movement Quantity.
		@param MovementQty 
		Quantity of a product moved.
	  */
	public void setMovementQty (BigDecimal MovementQty)
	{
		set_Value (COLUMNNAME_MovementQty, MovementQty);
	}

	/** Get Movement Quantity.
		@return Quantity of a product moved.
	  */
	public BigDecimal getMovementQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MovementQty);
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

	/** Set Inbound & Outbound Order Line MA ID.
		@param WM_InOutBoundLineMA_ID Inbound & Outbound Order Line MA ID	  */
	public void setWM_InOutBoundLineMA_ID (int WM_InOutBoundLineMA_ID)
	{
		if (WM_InOutBoundLineMA_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_WM_InOutBoundLineMA_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_WM_InOutBoundLineMA_ID, Integer.valueOf(WM_InOutBoundLineMA_ID));
	}

	/** Get Inbound & Outbound Order Line MA ID.
		@return Inbound & Outbound Order Line MA ID	  */
	public int getWM_InOutBoundLineMA_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_InOutBoundLineMA_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_WM_InOutBoundLine getWM_InOutBoundLine() throws RuntimeException
    {
		return (org.eevolution.model.I_WM_InOutBoundLine)MTable.get(getCtx(), org.eevolution.model.I_WM_InOutBoundLine.Table_Name)
			.getPO(getWM_InOutBoundLine_ID(), get_TrxName());	}

	/** Set Inbound & Outbound Order Line.
		@param WM_InOutBoundLine_ID Inbound & Outbound Order Line	  */
	public void setWM_InOutBoundLine_ID (int WM_InOutBoundLine_ID)
	{
		if (WM_InOutBoundLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_WM_InOutBoundLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_WM_InOutBoundLine_ID, Integer.valueOf(WM_InOutBoundLine_ID));
	}

	/** Get Inbound & Outbound Order Line.
		@return Inbound & Outbound Order Line	  */
	public int getWM_InOutBoundLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_InOutBoundLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}