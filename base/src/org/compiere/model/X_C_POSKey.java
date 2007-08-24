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
package org.compiere.model;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import org.compiere.util.*;

/** Generated Model for C_POSKey
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_C_POSKey extends PO implements I_C_POSKey, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_POSKey (Properties ctx, int C_POSKey_ID, String trxName)
    {
      super (ctx, C_POSKey_ID, trxName);
      /** if (C_POSKey_ID == 0)        {			setC_POSKeyLayout_ID (0);
			setC_POSKey_ID (0);
			setM_Product_ID (0);
			setName (null);
			setQty (Env.ZERO);
			setSeqNo (0);
} */
    }

    /** Load Constructor */
    public X_C_POSKey (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_C_POSKey[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_PrintColor getI_AD_PrintColor() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_PrintColor.Table_Name);
        I_AD_PrintColor result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_PrintColor)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_PrintColor_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Print Color.
		@param AD_PrintColor_ID 
		Color used for printing and display
	  */
	public void setAD_PrintColor_ID (int AD_PrintColor_ID)
	{
		if (AD_PrintColor_ID <= 0) 		set_Value (COLUMNNAME_AD_PrintColor_ID, null);
 else 
		set_Value (COLUMNNAME_AD_PrintColor_ID, Integer.valueOf(AD_PrintColor_ID));
	}

	/** Get Print Color.
		@return Color used for printing and display
	  */
	public int getAD_PrintColor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintColor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_POSKeyLayout getI_C_POSKeyLayout() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_POSKeyLayout.Table_Name);
        I_C_POSKeyLayout result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_POSKeyLayout)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_POSKeyLayout_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set POS Key Layout.
		@param C_POSKeyLayout_ID 
		POS Function Key Layout
	  */
	public void setC_POSKeyLayout_ID (int C_POSKeyLayout_ID)
	{
		if (C_POSKeyLayout_ID < 1)
			 throw new IllegalArgumentException ("C_POSKeyLayout_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_POSKeyLayout_ID, Integer.valueOf(C_POSKeyLayout_ID));
	}

	/** Get POS Key Layout.
		@return POS Function Key Layout
	  */
	public int getC_POSKeyLayout_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_POSKeyLayout_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set POS Key.
		@param C_POSKey_ID 
		POS Function Key
	  */
	public void setC_POSKey_ID (int C_POSKey_ID)
	{
		if (C_POSKey_ID < 1)
			 throw new IllegalArgumentException ("C_POSKey_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_POSKey_ID, Integer.valueOf(C_POSKey_ID));
	}

	/** Get POS Key.
		@return POS Function Key
	  */
	public int getC_POSKey_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_POSKey_ID);
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
		if (Description != null && Description.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			Description = Description.substring(0, 254);
		}
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
		set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		if (Name == null)
			throw new IllegalArgumentException ("Name is mandatory.");
		if (Name.length() > 60)
		{
			log.warning("Length > 60 - truncated");
			Name = Name.substring(0, 59);
		}
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

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		if (Qty == null)
			throw new IllegalArgumentException ("Qty is mandatory.");
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
}