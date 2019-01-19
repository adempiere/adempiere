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
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_LeaveAssign
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_LeaveAssign extends PO implements I_HR_LeaveAssign, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_LeaveAssign (Properties ctx, int HR_LeaveAssign_ID, String trxName)
    {
      super (ctx, HR_LeaveAssign_ID, trxName);
      /** if (HR_LeaveAssign_ID == 0)
        {
			setC_BPartner_ID (0);
			setC_Year_ID (0);
			setHR_LeaveAssign_ID (0);
			setHR_LeaveType_ID (0);
			setNoOfLeavesAllocated (Env.ZERO);
			setTotalLeaves (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_HR_LeaveAssign (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_LeaveAssign[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Balance.
		@param Balance Balance	  */
	public void setBalance (BigDecimal Balance)
	{
		set_Value (COLUMNNAME_Balance, Balance);
	}

	/** Get Balance.
		@return Balance	  */
	public BigDecimal getBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Balance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Year getC_Year() throws RuntimeException
    {
		return (org.compiere.model.I_C_Year)MTable.get(getCtx(), org.compiere.model.I_C_Year.Table_Name)
			.getPO(getC_Year_ID(), get_TrxName());	}

	/** Set Year.
		@param C_Year_ID 
		Calendar Year
	  */
	public void setC_Year_ID (int C_Year_ID)
	{
		if (C_Year_ID < 1) 
			set_Value (COLUMNNAME_C_Year_ID, null);
		else 
			set_Value (COLUMNNAME_C_Year_ID, Integer.valueOf(C_Year_ID));
	}

	/** Get Year.
		@return Calendar Year
	  */
	public int getC_Year_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Year_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Employee getHR_Employee() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Employee)MTable.get(getCtx(), org.eevolution.model.I_HR_Employee.Table_Name)
			.getPO(getHR_Employee_ID(), get_TrxName());	}

	/** Set Payroll Employee.
		@param HR_Employee_ID Payroll Employee	  */
	public void setHR_Employee_ID (int HR_Employee_ID)
	{
		if (HR_Employee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Employee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Employee_ID, Integer.valueOf(HR_Employee_ID));
	}

	/** Get Payroll Employee.
		@return Payroll Employee	  */
	public int getHR_Employee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Employee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHR_Employee_ID()));
    }

	/** Set Leave Assign.
		@param HR_LeaveAssign_ID 
		Leave Assign
	  */
	public void setHR_LeaveAssign_ID (int HR_LeaveAssign_ID)
	{
		if (HR_LeaveAssign_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_LeaveAssign_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_LeaveAssign_ID, Integer.valueOf(HR_LeaveAssign_ID));
	}

	/** Get Leave Assign.
		@return Leave Assign
	  */
	public int getHR_LeaveAssign_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_LeaveAssign_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_LeaveType getHR_LeaveType() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_LeaveType)MTable.get(getCtx(), org.eevolution.model.I_HR_LeaveType.Table_Name)
			.getPO(getHR_LeaveType_ID(), get_TrxName());	}

	/** Set Leave Type.
		@param HR_LeaveType_ID 
		Leave Type for an Employee
	  */
	public void setHR_LeaveType_ID (int HR_LeaveType_ID)
	{
		if (HR_LeaveType_ID < 1) 
			set_Value (COLUMNNAME_HR_LeaveType_ID, null);
		else 
			set_Value (COLUMNNAME_HR_LeaveType_ID, Integer.valueOf(HR_LeaveType_ID));
	}

	/** Get Leave Type.
		@return Leave Type for an Employee
	  */
	public int getHR_LeaveType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_LeaveType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Number of Leaves Allocated.
		@param NoOfLeavesAllocated 
		Number of Leaves Allocated
	  */
	public void setNoOfLeavesAllocated (BigDecimal NoOfLeavesAllocated)
	{
		set_Value (COLUMNNAME_NoOfLeavesAllocated, NoOfLeavesAllocated);
	}

	/** Get Number of Leaves Allocated.
		@return Number of Leaves Allocated
	  */
	public BigDecimal getNoOfLeavesAllocated () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NoOfLeavesAllocated);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Total of Leaves.
		@param TotalLeaves 
		Total of Leaves
	  */
	public void setTotalLeaves (BigDecimal TotalLeaves)
	{
		set_Value (COLUMNNAME_TotalLeaves, TotalLeaves);
	}

	/** Get Total of Leaves.
		@return Total of Leaves
	  */
	public BigDecimal getTotalLeaves () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalLeaves);
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

	/** Set Used Leaves.
		@param UsedLeaves 
		Used Leaves
	  */
	public void setUsedLeaves (BigDecimal UsedLeaves)
	{
		set_Value (COLUMNNAME_UsedLeaves, UsedLeaves);
	}

	/** Get Used Leaves.
		@return Used Leaves
	  */
	public BigDecimal getUsedLeaves () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UsedLeaves);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}