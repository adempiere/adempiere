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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for M_ProductionBatchLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_M_ProductionBatchLine extends PO implements I_M_ProductionBatchLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_M_ProductionBatchLine (Properties ctx, int M_ProductionBatchLine_ID, String trxName)
    {
      super (ctx, M_ProductionBatchLine_ID, trxName);
      /** if (M_ProductionBatchLine_ID == 0)
        {
			setIsEndProduct (false);
// N
			setM_Product_ID (0);
			setM_ProductionBatchLine_ID (0);
			setM_ProductionBatch_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_ProductionBatchLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_ProductionBatchLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set End Product.
		@param IsEndProduct 
		End Product of production
	  */
	public void setIsEndProduct (boolean IsEndProduct)
	{
		set_Value (COLUMNNAME_IsEndProduct, Boolean.valueOf(IsEndProduct));
	}

	/** Get End Product.
		@return End Product of production
	  */
	public boolean isEndProduct () 
	{
		Object oo = get_Value(COLUMNNAME_IsEndProduct);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
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

	/** Set Production Batch Line.
		@param M_ProductionBatchLine_ID Production Batch Line	  */
	public void setM_ProductionBatchLine_ID (int M_ProductionBatchLine_ID)
	{
		if (M_ProductionBatchLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_ProductionBatchLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_ProductionBatchLine_ID, Integer.valueOf(M_ProductionBatchLine_ID));
	}

	/** Get Production Batch Line.
		@return Production Batch Line	  */
	public int getM_ProductionBatchLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductionBatchLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_ProductionBatch getM_ProductionBatch() throws RuntimeException
    {
		return (org.compiere.model.I_M_ProductionBatch)MTable.get(getCtx(), org.compiere.model.I_M_ProductionBatch.Table_Name)
			.getPO(getM_ProductionBatch_ID(), get_TrxName());	}

	/** Set Production Batch.
		@param M_ProductionBatch_ID Production Batch	  */
	public void setM_ProductionBatch_ID (int M_ProductionBatch_ID)
	{
		if (M_ProductionBatch_ID < 1) 
			set_Value (COLUMNNAME_M_ProductionBatch_ID, null);
		else 
			set_Value (COLUMNNAME_M_ProductionBatch_ID, Integer.valueOf(M_ProductionBatch_ID));
	}

	/** Get Production Batch.
		@return Production Batch	  */
	public int getM_ProductionBatch_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductionBatch_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reserved Quantity.
		@param QtyReserved 
		Reserved Quantity
	  */
	public void setQtyReserved (BigDecimal QtyReserved)
	{
		set_Value (COLUMNNAME_QtyReserved, QtyReserved);
	}

	/** Get Reserved Quantity.
		@return Reserved Quantity
	  */
	public BigDecimal getQtyReserved () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyReserved);
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