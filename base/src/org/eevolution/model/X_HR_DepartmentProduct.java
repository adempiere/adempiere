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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for HR_DepartmentProduct
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_DepartmentProduct extends PO implements I_HR_DepartmentProduct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_DepartmentProduct (Properties ctx, int HR_DepartmentProduct_ID, String trxName)
    {
      super (ctx, HR_DepartmentProduct_ID, trxName);
      /** if (HR_DepartmentProduct_ID == 0)
        {
			setActualQty (Env.ZERO);
			setDateFrom (new Timestamp( System.currentTimeMillis() ));
			setDateTo (new Timestamp( System.currentTimeMillis() ));
			setHR_DepartmentProduct_ID (0);
			setHR_Department_ID (0);
			setM_Product_ID (0);
			setQtyPlan (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_HR_DepartmentProduct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_DepartmentProduct[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Actual Quantity.
		@param ActualQty 
		The actual quantity
	  */
	public void setActualQty (BigDecimal ActualQty)
	{
		set_Value (COLUMNNAME_ActualQty, ActualQty);
	}

	/** Get Actual Quantity.
		@return The actual quantity
	  */
	public BigDecimal getActualQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ActualQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** Set Date From.
		@param DateFrom 
		Starting date for a range
	  */
	public void setDateFrom (Timestamp DateFrom)
	{
		set_Value (COLUMNNAME_DateFrom, DateFrom);
	}

	/** Get Date From.
		@return Starting date for a range
	  */
	public Timestamp getDateFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateFrom);
	}

	/** Set Date To.
		@param DateTo 
		End date of a date range
	  */
	public void setDateTo (Timestamp DateTo)
	{
		set_Value (COLUMNNAME_DateTo, DateTo);
	}

	/** Get Date To.
		@return End date of a date range
	  */
	public Timestamp getDateTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTo);
	}

	/** Set Department Consumption Limit.
		@param HR_DepartmentProduct_ID Department Consumption Limit	  */
	public void setHR_DepartmentProduct_ID (int HR_DepartmentProduct_ID)
	{
		if (HR_DepartmentProduct_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_DepartmentProduct_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_DepartmentProduct_ID, Integer.valueOf(HR_DepartmentProduct_ID));
	}

	/** Get Department Consumption Limit.
		@return Department Consumption Limit	  */
	public int getHR_DepartmentProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_DepartmentProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Department)MTable.get(getCtx(), org.eevolution.model.I_HR_Department.Table_Name)
			.getPO(getHR_Department_ID(), get_TrxName());	}

	/** Set Payroll Department.
		@param HR_Department_ID Payroll Department	  */
	public void setHR_Department_ID (int HR_Department_ID)
	{
		if (HR_Department_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Department_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Department_ID, Integer.valueOf(HR_Department_ID));
	}

	/** Get Payroll Department.
		@return Payroll Department	  */
	public int getHR_Department_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Department_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Quantity Plan.
		@param QtyPlan 
		Planned Quantity
	  */
	public void setQtyPlan (BigDecimal QtyPlan)
	{
		set_Value (COLUMNNAME_QtyPlan, QtyPlan);
	}

	/** Get Quantity Plan.
		@return Planned Quantity
	  */
	public BigDecimal getQtyPlan () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyPlan);
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