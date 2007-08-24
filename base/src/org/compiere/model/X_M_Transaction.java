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

/** Generated Model for M_Transaction
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_M_Transaction extends PO implements I_M_Transaction, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_Transaction (Properties ctx, int M_Transaction_ID, String trxName)
    {
      super (ctx, M_Transaction_ID, trxName);
      /** if (M_Transaction_ID == 0)        {			setM_AttributeSetInstance_ID (0);
			setM_Locator_ID (0);
			setM_Product_ID (0);
			setM_Transaction_ID (0);
			setMovementDate (new Timestamp(System.currentTimeMillis()));
			setMovementQty (Env.ZERO);
			setMovementType (null);
} */
    }

    /** Load Constructor */
    public X_M_Transaction (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_M_Transaction[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Project Issue.
		@param C_ProjectIssue_ID 
		Project Issues (Material, Labor)
	  */
	public void setC_ProjectIssue_ID (int C_ProjectIssue_ID)
	{
		if (C_ProjectIssue_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_C_ProjectIssue_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_C_ProjectIssue_ID, Integer.valueOf(C_ProjectIssue_ID));
	}

	/** Get Project Issue.
		@return Project Issues (Material, Labor)
	  */
	public int getC_ProjectIssue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectIssue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0)
			 throw new IllegalArgumentException ("M_AttributeSetInstance_ID is mandatory.");
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

	/** Set Shipment/Receipt Line.
		@param M_InOutLine_ID 
		Line on Shipment or Receipt document
	  */
	public void setM_InOutLine_ID (int M_InOutLine_ID)
	{
		if (M_InOutLine_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_M_InOutLine_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_M_InOutLine_ID, Integer.valueOf(M_InOutLine_ID));
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

	/** Set Phys.Inventory Line.
		@param M_InventoryLine_ID 
		Unique line in an Inventory document
	  */
	public void setM_InventoryLine_ID (int M_InventoryLine_ID)
	{
		if (M_InventoryLine_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_M_InventoryLine_ID, null);
 else 
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

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1)
			 throw new IllegalArgumentException ("M_Locator_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
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

	/** Set Move Line.
		@param M_MovementLine_ID 
		Inventory Move document Line
	  */
	public void setM_MovementLine_ID (int M_MovementLine_ID)
	{
		if (M_MovementLine_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_M_MovementLine_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_M_MovementLine_ID, Integer.valueOf(M_MovementLine_ID));
	}

	/** Get Move Line.
		@return Inventory Move document Line
	  */
	public int getM_MovementLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_MovementLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			 throw new IllegalArgumentException ("M_Product_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
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

	/** Set Production Line.
		@param M_ProductionLine_ID 
		Document Line representing a production
	  */
	public void setM_ProductionLine_ID (int M_ProductionLine_ID)
	{
		if (M_ProductionLine_ID <= 0) 		set_ValueNoCheck (COLUMNNAME_M_ProductionLine_ID, null);
 else 
		set_ValueNoCheck (COLUMNNAME_M_ProductionLine_ID, Integer.valueOf(M_ProductionLine_ID));
	}

	/** Get Production Line.
		@return Document Line representing a production
	  */
	public int getM_ProductionLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductionLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Inventory Transaction.
		@param M_Transaction_ID Inventory Transaction	  */
	public void setM_Transaction_ID (int M_Transaction_ID)
	{
		if (M_Transaction_ID < 1)
			 throw new IllegalArgumentException ("M_Transaction_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Transaction_ID, Integer.valueOf(M_Transaction_ID));
	}

	/** Get Inventory Transaction.
@return Inventory Transaction	  */
	public int getM_Transaction_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Transaction_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Movement Date.
		@param MovementDate 
		Date a product was moved in or out of inventory
	  */
	public void setMovementDate (Timestamp MovementDate)
	{
		if (MovementDate == null)
			throw new IllegalArgumentException ("MovementDate is mandatory.");
		set_ValueNoCheck (COLUMNNAME_MovementDate, MovementDate);
	}

	/** Get Movement Date.
		@return Date a product was moved in or out of inventory
	  */
	public Timestamp getMovementDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_MovementDate);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getMovementDate()));
    }

	/** Set Movement Quantity.
		@param MovementQty 
		Quantity of a product moved.
	  */
	public void setMovementQty (BigDecimal MovementQty)
	{
		if (MovementQty == null)
			throw new IllegalArgumentException ("MovementQty is mandatory.");
		set_ValueNoCheck (COLUMNNAME_MovementQty, MovementQty);
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

/** MovementType AD_Reference_ID=189 */
public static final int MOVEMENTTYPE_AD_Reference_ID=189;/** Customer Returns = C+ */
public static final String MOVEMENTTYPE_CustomerReturns = "C+";/** Customer Shipment = C- */
public static final String MOVEMENTTYPE_CustomerShipment = "C-";/** Inventory In = I+ */
public static final String MOVEMENTTYPE_InventoryIn = "I+";/** Inventory Out = I- */
public static final String MOVEMENTTYPE_InventoryOut = "I-";/** Movement To = M+ */
public static final String MOVEMENTTYPE_MovementTo = "M+";/** Movement From = M- */
public static final String MOVEMENTTYPE_MovementFrom = "M-";/** Production + = P+ */
public static final String MOVEMENTTYPE_ProductionPlus = "P+";/** Production - = P- */
public static final String MOVEMENTTYPE_Production_ = "P-";/** Vendor Receipts = V+ */
public static final String MOVEMENTTYPE_VendorReceipts = "V+";/** Vendor Returns = V- */
public static final String MOVEMENTTYPE_VendorReturns = "V-";/** Work Order + = W+ */
public static final String MOVEMENTTYPE_WorkOrderPlus = "W+";/** Work Order - = W- */
public static final String MOVEMENTTYPE_WorkOrder_ = "W-";
	/** Set Movement Type.
		@param MovementType 
		Method of moving the inventory
	  */
	public void setMovementType (String MovementType)
	{
if (MovementType == null) throw new IllegalArgumentException ("MovementType is mandatory");if (MovementType.equals("C+") || MovementType.equals("C-") || MovementType.equals("I+") || MovementType.equals("I-") || MovementType.equals("M+") || MovementType.equals("M-") || MovementType.equals("P+") || MovementType.equals("P-") || MovementType.equals("V+") || MovementType.equals("V-") || MovementType.equals("W+") || MovementType.equals("W-")); else throw new IllegalArgumentException ("MovementType Invalid value - " + MovementType + " - Reference_ID=189 - C+ - C- - I+ - I- - M+ - M- - P+ - P- - V+ - V- - W+ - W-");		if (MovementType.length() > 2)
		{
			log.warning("Length > 2 - truncated");
			MovementType = MovementType.substring(0, 1);
		}
		set_ValueNoCheck (COLUMNNAME_MovementType, MovementType);
	}

	/** Get Movement Type.
		@return Method of moving the inventory
	  */
	public String getMovementType () 
	{
		return (String)get_Value(COLUMNNAME_MovementType);
	}
}