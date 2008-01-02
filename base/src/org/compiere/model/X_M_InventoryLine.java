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

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_InventoryLine
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1t - $Id$ */
public class X_M_InventoryLine extends PO implements I_M_InventoryLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_InventoryLine (Properties ctx, int M_InventoryLine_ID, String trxName)
    {
      super (ctx, M_InventoryLine_ID, trxName);
      /** if (M_InventoryLine_ID == 0)
        {
			setInventoryType (null);
// D
			setM_AttributeSetInstance_ID (0);
			setM_InventoryLine_ID (0);
			setM_Inventory_ID (0);
			setM_Locator_ID (0);
// @M_Locator_ID@
			setM_Product_ID (0);
			setProcessed (false);
			setQtyBook (Env.ZERO);
			setQtyCount (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_M_InventoryLine (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_M_InventoryLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Charge getC_Charge() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_Charge.Table_Name);
        I_C_Charge result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Charge)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Charge_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID <= 0) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
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
			Description = Description.substring(0, 255);
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

	/** InventoryType AD_Reference_ID=292 */
	public static final int INVENTORYTYPE_AD_Reference_ID=292;
	/** Inventory Difference = D */
	public static final String INVENTORYTYPE_InventoryDifference = "D";
	/** Charge Account = C */
	public static final String INVENTORYTYPE_ChargeAccount = "C";
	/** Set Inventory Type.
		@param InventoryType 
		Type of inventory difference
	  */
	public void setInventoryType (String InventoryType)
	{
		if (InventoryType == null) throw new IllegalArgumentException ("InventoryType is mandatory");
		if (InventoryType.equals("D") || InventoryType.equals("C")); else throw new IllegalArgumentException ("InventoryType Invalid value - " + InventoryType + " - Reference_ID=292 - D - C");
		if (InventoryType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			InventoryType = InventoryType.substring(0, 1);
		}
		set_Value (COLUMNNAME_InventoryType, InventoryType);
	}

	/** Get Inventory Type.
		@return Type of inventory difference
	  */
	public String getInventoryType () 
	{
		return (String)get_Value(COLUMNNAME_InventoryType);
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getLine()));
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

	/** Set Phys.Inventory Line.
		@param M_InventoryLine_ID 
		Unique line in an Inventory document
	  */
	public void setM_InventoryLine_ID (int M_InventoryLine_ID)
	{
		if (M_InventoryLine_ID < 1)
			 throw new IllegalArgumentException ("M_InventoryLine_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_InventoryLine_ID, Integer.valueOf(M_InventoryLine_ID));
	}

	/** Get Phys.Inventory Line.
		@return Unique line in an Inventory document
	  */
	public int getM_InventoryLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InventoryLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Inventory getM_Inventory() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_Inventory.Table_Name);
        I_M_Inventory result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Inventory)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Inventory_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Phys.Inventory.
		@param M_Inventory_ID 
		Parameters for a Physical Inventory
	  */
	public void setM_Inventory_ID (int M_Inventory_ID)
	{
		if (M_Inventory_ID < 1)
			 throw new IllegalArgumentException ("M_Inventory_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Inventory_ID, Integer.valueOf(M_Inventory_ID));
	}

	/** Get Phys.Inventory.
		@return Parameters for a Physical Inventory
	  */
	public int getM_Inventory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Inventory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1)
			 throw new IllegalArgumentException ("M_Locator_ID is mandatory.");
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

	/** M_Product_ID AD_Reference_ID=171 */
	public static final int M_PRODUCT_ID_AD_Reference_ID=171;
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

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Quantity book.
		@param QtyBook 
		Book Quantity
	  */
	public void setQtyBook (BigDecimal QtyBook)
	{
		if (QtyBook == null)
			throw new IllegalArgumentException ("QtyBook is mandatory.");
		set_ValueNoCheck (COLUMNNAME_QtyBook, QtyBook);
	}

	/** Get Quantity book.
		@return Book Quantity
	  */
	public BigDecimal getQtyBook () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyBook);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity count.
		@param QtyCount 
		Counted Quantity
	  */
	public void setQtyCount (BigDecimal QtyCount)
	{
		if (QtyCount == null)
			throw new IllegalArgumentException ("QtyCount is mandatory.");
		set_Value (COLUMNNAME_QtyCount, QtyCount);
	}

	/** Get Quantity count.
		@return Counted Quantity
	  */
	public BigDecimal getQtyCount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyCount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Internal Use Qty.
		@param QtyInternalUse 
		Internal Use Quantity removed from Inventory
	  */
	public void setQtyInternalUse (BigDecimal QtyInternalUse)
	{
		set_Value (COLUMNNAME_QtyInternalUse, QtyInternalUse);
	}

	/** Get Internal Use Qty.
		@return Internal Use Quantity removed from Inventory
	  */
	public BigDecimal getQtyInternalUse () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyInternalUse);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set UPC/EAN.
		@param UPC 
		Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public void setUPC (String UPC)
	{
		throw new IllegalArgumentException ("UPC is virtual column");	}

	/** Get UPC/EAN.
		@return Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public String getUPC () 
	{
		return (String)get_Value(COLUMNNAME_UPC);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		throw new IllegalArgumentException ("Value is virtual column");	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}