/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for WM_InOutBoundLineMA
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a - $Id$ */
public class X_WM_InOutBoundLineMA extends PO implements I_WM_InOutBoundLineMA, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20081221L;

    /** Standard Constructor */
    public X_WM_InOutBoundLineMA (Properties ctx, int WM_InOutBoundLineMA_ID, String trxName)
    {
      super (ctx, WM_InOutBoundLineMA_ID, trxName);
      /** if (WM_InOutBoundLineMA_ID == 0)
        {
			setM_AttributeSetInstance_ID (0);
			setMovementQty (Env.ZERO);
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

	/** Set WM_InOutBoundLineMA ID.
		@param WM_InOutBoundLineMA_ID WM_InOutBoundLineMA ID	  */
	public void setWM_InOutBoundLineMA_ID (int WM_InOutBoundLineMA_ID)
	{
		if (WM_InOutBoundLineMA_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_WM_InOutBoundLineMA_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_WM_InOutBoundLineMA_ID, Integer.valueOf(WM_InOutBoundLineMA_ID));
	}

	/** Get WM_InOutBoundLineMA ID.
		@return WM_InOutBoundLineMA ID	  */
	public int getWM_InOutBoundLineMA_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WM_InOutBoundLineMA_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getWM_InOutBoundLineMA_ID()));
    }

	public org.eevolution.model.I_WM_InOutBoundLine getWM_InOutBoundLine() throws RuntimeException 
    {
        Class<?> clazz = MTable.getClass(org.eevolution.model.I_WM_InOutBoundLine.Table_Name);
        org.eevolution.model.I_WM_InOutBoundLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (org.eevolution.model.I_WM_InOutBoundLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getWM_InOutBoundLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw new RuntimeException( e );
        }
        return result;
    }

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