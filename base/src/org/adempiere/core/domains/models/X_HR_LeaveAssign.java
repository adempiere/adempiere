/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.adempiere.core.domains.models;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_LeaveAssign
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_HR_LeaveAssign extends PO implements I_HR_LeaveAssign, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220801L;

    /** Standard Constructor */
    public X_HR_LeaveAssign (Properties ctx, int HR_LeaveAssign_ID, String trxName)
    {
      super (ctx, HR_LeaveAssign_ID, trxName);
      /** if (HR_LeaveAssign_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_LeaveAssign_ID (0);
			setHR_LeaveType_ID (0);
			setNoOfLeavesAllocated (0);
			setTotalLeaves (0);
			setValidFrom (new Timestamp( System.currentTimeMillis() ));
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
	public void setBalance (int Balance)
	{
		set_Value (COLUMNNAME_Balance, Integer.valueOf(Balance));
	}

	/** Get Balance.
		@return Balance	  */
	public int getBalance () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Balance);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_C_BPartner)MTable.get(getCtx(), org.adempiere.core.domains.models.I_C_BPartner.Table_Name)
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

	/** Set Date last run.
		@param DateLastRun 
		Date the process was last run.
	  */
	public void setDateLastRun (Timestamp DateLastRun)
	{
		set_Value (COLUMNNAME_DateLastRun, DateLastRun);
	}

	/** Get Date last run.
		@return Date the process was last run.
	  */
	public Timestamp getDateLastRun () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateLastRun);
	}

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

	/** Set Leave Type.
		@param HR_LeaveType_ID 
		Leave Type for an Employee
	  */
	public void setHR_LeaveType_ID (int HR_LeaveType_ID)
	{
		if (HR_LeaveType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_LeaveType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_LeaveType_ID, Integer.valueOf(HR_LeaveType_ID));
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
	public void setNoOfLeavesAllocated (int NoOfLeavesAllocated)
	{
		set_ValueNoCheck (COLUMNNAME_NoOfLeavesAllocated, Integer.valueOf(NoOfLeavesAllocated));
	}

	/** Get Number of Leaves Allocated.
		@return Number of Leaves Allocated
	  */
	public int getNoOfLeavesAllocated () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NoOfLeavesAllocated);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
	public void setTotalLeaves (int TotalLeaves)
	{
		set_Value (COLUMNNAME_TotalLeaves, Integer.valueOf(TotalLeaves));
	}

	/** Get Total of Leaves.
		@return Total of Leaves
	  */
	public int getTotalLeaves () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_TotalLeaves);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Used Leaves.
		@param UsedLeaves 
		Used Leaves
	  */
	public void setUsedLeaves (int UsedLeaves)
	{
		set_Value (COLUMNNAME_UsedLeaves, Integer.valueOf(UsedLeaves));
	}

	/** Get Used Leaves.
		@return Used Leaves
	  */
	public int getUsedLeaves () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UsedLeaves);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo 
		Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}
}