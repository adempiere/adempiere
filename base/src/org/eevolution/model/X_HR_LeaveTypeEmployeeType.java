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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_LeaveTypeEmployeeType
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0RC - $Id$ */
public class X_HR_LeaveTypeEmployeeType extends PO implements I_HR_LeaveTypeEmployeeType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20131208L;

    /** Standard Constructor */
    public X_HR_LeaveTypeEmployeeType (Properties ctx, int HR_LeaveTypeEmployeeType_ID, String trxName)
    {
      super (ctx, HR_LeaveTypeEmployeeType_ID, trxName);
      /** if (HR_LeaveTypeEmployeeType_ID == 0)
        {
			setHR_EmployeeType_ID (0);
			setHR_LeaveTypeEmployeeType_ID (0);
			setHR_LeaveType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_LeaveTypeEmployeeType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_LeaveTypeEmployeeType[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.eevolution.model.I_HR_EmployeeType getHR_EmployeeType() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_EmployeeType)MTable.get(getCtx(), org.eevolution.model.I_HR_EmployeeType.Table_Name)
			.getPO(getHR_EmployeeType_ID(), get_TrxName());	}

	/** Set Employee Type.
		@param HR_EmployeeType_ID 
		Employee Type
	  */
	public void setHR_EmployeeType_ID (int HR_EmployeeType_ID)
	{
		if (HR_EmployeeType_ID < 1) 
			set_Value (COLUMNNAME_HR_EmployeeType_ID, null);
		else 
			set_Value (COLUMNNAME_HR_EmployeeType_ID, Integer.valueOf(HR_EmployeeType_ID));
	}

	/** Get Employee Type.
		@return Employee Type
	  */
	public int getHR_EmployeeType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EmployeeType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Leave Type for employee type.
		@param HR_LeaveTypeEmployeeType_ID 
		Leave type for employee type
	  */
	public void setHR_LeaveTypeEmployeeType_ID (int HR_LeaveTypeEmployeeType_ID)
	{
		if (HR_LeaveTypeEmployeeType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_LeaveTypeEmployeeType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_LeaveTypeEmployeeType_ID, Integer.valueOf(HR_LeaveTypeEmployeeType_ID));
	}

	/** Get Leave Type for employee type.
		@return Leave type for employee type
	  */
	public int getHR_LeaveTypeEmployeeType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_LeaveTypeEmployeeType_ID);
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
}