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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for M_PBatch_Line
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public class X_M_PBatch_Line extends PO implements I_M_PBatch_Line, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20160412L;

    /** Standard Constructor */
    public X_M_PBatch_Line (Properties ctx, int M_PBatch_Line_ID, String trxName)
    {
      super (ctx, M_PBatch_Line_ID, trxName);
      /** if (M_PBatch_Line_ID == 0)
        {
			setIsEndProduct (false);
// N
			setM_PBatch_Line_ID (0);
			setM_Product_ID (0);
			setM_Production_Batch_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_PBatch_Line (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_PBatch_Line[")
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

	/** Set Production Batch Line ID.
		@param M_PBatch_Line_ID Production Batch Line ID	  */
	public void setM_PBatch_Line_ID (int M_PBatch_Line_ID)
	{
		if (M_PBatch_Line_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_PBatch_Line_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_PBatch_Line_ID, Integer.valueOf(M_PBatch_Line_ID));
	}

	/** Get Production Batch Line ID.
		@return Production Batch Line ID	  */
	public int getM_PBatch_Line_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PBatch_Line_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product getM_Product() throws RuntimeException
    {
		return (I_M_Product)MTable.get(getCtx(), I_M_Product.Table_Name)
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

	public I_M_Production_Batch getM_Production_Batch() throws RuntimeException
    {
		return (I_M_Production_Batch)MTable.get(getCtx(), I_M_Production_Batch.Table_Name)
			.getPO(getM_Production_Batch_ID(), get_TrxName());	}

	/** Set Production Batch.
		@param M_Production_Batch_ID Production Batch	  */
	public void setM_Production_Batch_ID (int M_Production_Batch_ID)
	{
		if (M_Production_Batch_ID < 1) 
			set_Value (COLUMNNAME_M_Production_Batch_ID, null);
		else 
			set_Value (COLUMNNAME_M_Production_Batch_ID, Integer.valueOf(M_Production_Batch_ID));
	}

	/** Get Production Batch.
		@return Production Batch	  */
	public int getM_Production_Batch_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Production_Batch_ID);
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
}