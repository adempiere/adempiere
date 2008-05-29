/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
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

/** Generated Model for PP_Order_BOMLineMA
 *  @author Adempiere (generated) 
 *  @version Release 3.5.1a - $Id$ */
public class X_PP_Order_BOMLineMA extends PO implements I_PP_Order_BOMLineMA, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PP_Order_BOMLineMA (Properties ctx, int PP_Order_BOMLineMA_ID, String trxName)
    {
      super (ctx, PP_Order_BOMLineMA_ID, trxName);
      /** if (PP_Order_BOMLineMA_ID == 0)
        {
			setM_AttributeSetInstance_ID (0);
			setMovementQty (Env.ZERO);
			setPP_Order_BOMLineMA_ID (0);
			setPP_Order_BOMLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PP_Order_BOMLineMA (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_PP_Order_BOMLineMA[")
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
			 throw new IllegalArgumentException ("M_AttributeSetInstance_ID is mandatory.");
		set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
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
		if (MovementQty == null)
			throw new IllegalArgumentException ("MovementQty is mandatory.");
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

	/** Set PP_Order_BOMLineMA_ID.
		@param PP_Order_BOMLineMA_ID PP_Order_BOMLineMA_ID	  */
	public void setPP_Order_BOMLineMA_ID (int PP_Order_BOMLineMA_ID)
	{
		if (PP_Order_BOMLineMA_ID < 1)
			 throw new IllegalArgumentException ("PP_Order_BOMLineMA_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PP_Order_BOMLineMA_ID, Integer.valueOf(PP_Order_BOMLineMA_ID));
	}

	/** Get PP_Order_BOMLineMA_ID.
		@return PP_Order_BOMLineMA_ID	  */
	public int getPP_Order_BOMLineMA_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_BOMLineMA_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_Order_BOMLine getPP_Order_BOMLine() throws Exception 
    {
        Class<?> clazz = MTable.getClass(org.eevolution.model.I_PP_Order_BOMLine.Table_Name);
        org.eevolution.model.I_PP_Order_BOMLine result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (org.eevolution.model.I_PP_Order_BOMLine)constructor.newInstance(new Object[] {getCtx(), new Integer(getPP_Order_BOMLine_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set PP_Order_BOMLine_ID.
		@param PP_Order_BOMLine_ID PP_Order_BOMLine_ID	  */
	public void setPP_Order_BOMLine_ID (int PP_Order_BOMLine_ID)
	{
		if (PP_Order_BOMLine_ID < 1)
			 throw new IllegalArgumentException ("PP_Order_BOMLine_ID is mandatory.");
		set_Value (COLUMNNAME_PP_Order_BOMLine_ID, Integer.valueOf(PP_Order_BOMLine_ID));
	}

	/** Get PP_Order_BOMLine_ID.
		@return PP_Order_BOMLine_ID	  */
	public int getPP_Order_BOMLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_BOMLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}