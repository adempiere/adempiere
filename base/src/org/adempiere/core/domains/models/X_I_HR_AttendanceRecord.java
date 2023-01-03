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
import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.KeyNamePair;

/** Generated Model for I_HR_AttendanceRecord
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4 - $Id$ */
public class X_I_HR_AttendanceRecord extends PO implements I_I_HR_AttendanceRecord, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230102L;

    /** Standard Constructor */
    public X_I_HR_AttendanceRecord (Properties ctx, int I_HR_AttendanceRecord_ID, String trxName)
    {
      super (ctx, I_HR_AttendanceRecord_ID, trxName);
      /** if (I_HR_AttendanceRecord_ID == 0)
        {
			setI_HR_AttendanceRecord_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_HR_AttendanceRecord (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_HR_AttendanceRecord[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Attendance Time.
		@param AttendanceTime 
		Attendance Time for Employee
	  */
	public void setAttendanceTime (Timestamp AttendanceTime)
	{
		set_Value (COLUMNNAME_AttendanceTime, AttendanceTime);
	}

	/** Get Attendance Time.
		@return Attendance Time for Employee
	  */
	public Timestamp getAttendanceTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AttendanceTime);
	}

	/** Set Validation code.
		@param Code 
		Validation Code
	  */
	public void setCode (String Code)
	{
		set_Value (COLUMNNAME_Code, Code);
	}

	/** Get Validation code.
		@return Validation Code
	  */
	public String getCode () 
	{
		return (String)get_Value(COLUMNNAME_Code);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getCode());
    }

	/** Set Device Code.
		@param DeviceCode 
		Device Code
	  */
	public void setDeviceCode (String DeviceCode)
	{
		set_Value (COLUMNNAME_DeviceCode, DeviceCode);
	}

	/** Get Device Code.
		@return Device Code
	  */
	public String getDeviceCode () 
	{
		return (String)get_Value(COLUMNNAME_DeviceCode);
	}

	public org.adempiere.core.domains.models.I_HR_AttendanceBatch getHR_AttendanceBatch() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_HR_AttendanceBatch)MTable.get(getCtx(), org.adempiere.core.domains.models.I_HR_AttendanceBatch.Table_Name)
			.getPO(getHR_AttendanceBatch_ID(), get_TrxName());	}

	/** Set Attendance Batch.
		@param HR_AttendanceBatch_ID Attendance Batch	  */
	public void setHR_AttendanceBatch_ID (int HR_AttendanceBatch_ID)
	{
		if (HR_AttendanceBatch_ID < 1) 
			set_Value (COLUMNNAME_HR_AttendanceBatch_ID, null);
		else 
			set_Value (COLUMNNAME_HR_AttendanceBatch_ID, Integer.valueOf(HR_AttendanceBatch_ID));
	}

	/** Get Attendance Batch.
		@return Attendance Batch	  */
	public int getHR_AttendanceBatch_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_AttendanceBatch_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_HR_AttendanceRecord getHR_AttendanceRecord() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_HR_AttendanceRecord)MTable.get(getCtx(), org.adempiere.core.domains.models.I_HR_AttendanceRecord.Table_Name)
			.getPO(getHR_AttendanceRecord_ID(), get_TrxName());	}

	/** Set Attendance Record.
		@param HR_AttendanceRecord_ID 
		Attendance Record
	  */
	public void setHR_AttendanceRecord_ID (int HR_AttendanceRecord_ID)
	{
		if (HR_AttendanceRecord_ID < 1) 
			set_Value (COLUMNNAME_HR_AttendanceRecord_ID, null);
		else 
			set_Value (COLUMNNAME_HR_AttendanceRecord_ID, Integer.valueOf(HR_AttendanceRecord_ID));
	}

	/** Get Attendance Record.
		@return Attendance Record
	  */
	public int getHR_AttendanceRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_AttendanceRecord_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_HR_Employee getHR_Employee() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_HR_Employee)MTable.get(getCtx(), org.adempiere.core.domains.models.I_HR_Employee.Table_Name)
			.getPO(getHR_Employee_ID(), get_TrxName());	}

	/** Set Payroll Employee.
		@param HR_Employee_ID Payroll Employee	  */
	public void setHR_Employee_ID (int HR_Employee_ID)
	{
		if (HR_Employee_ID < 1) 
			set_Value (COLUMNNAME_HR_Employee_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Employee_ID, Integer.valueOf(HR_Employee_ID));
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

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Attendance Record Import.
		@param I_HR_AttendanceRecord_ID Attendance Record Import	  */
	public void setI_HR_AttendanceRecord_ID (int I_HR_AttendanceRecord_ID)
	{
		if (I_HR_AttendanceRecord_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_HR_AttendanceRecord_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_HR_AttendanceRecord_ID, Integer.valueOf(I_HR_AttendanceRecord_ID));
	}

	/** Get Attendance Record Import.
		@return Attendance Record Import	  */
	public int getI_HR_AttendanceRecord_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_HR_AttendanceRecord_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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