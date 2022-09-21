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

/** Generated Model for HR_AttendanceRecord
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_HR_AttendanceRecord extends PO implements I_HR_AttendanceRecord, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220801L;

    /** Standard Constructor */
    public X_HR_AttendanceRecord (Properties ctx, int HR_AttendanceRecord_ID, String trxName)
    {
      super (ctx, HR_AttendanceRecord_ID, trxName);
      /** if (HR_AttendanceRecord_ID == 0)
        {
			setAttendanceTime (new Timestamp( System.currentTimeMillis() ));
			setHR_AttendanceBatch_ID (0);
			setHR_AttendanceRecord_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_AttendanceRecord (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_AttendanceRecord[")
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAttendanceTime()));
    }

	public org.adempiere.core.domains.models.I_C_Project getC_Project() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_C_Project)MTable.get(getCtx(), org.adempiere.core.domains.models.I_C_Project.Table_Name)
			.getPO(getC_Project_ID(), get_TrxName());	}

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_Value (COLUMNNAME_C_Project_ID, null);
		else 
			set_Value (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Attendance Batch.
		@param HR_AttendanceBatch_ID Attendance Batch	  */
	public void setHR_AttendanceBatch_ID (int HR_AttendanceBatch_ID)
	{
		if (HR_AttendanceBatch_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_AttendanceBatch_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_AttendanceBatch_ID, Integer.valueOf(HR_AttendanceBatch_ID));
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

	/** Set Attendance Record.
		@param HR_AttendanceRecord_ID 
		Attendance Record
	  */
	public void setHR_AttendanceRecord_ID (int HR_AttendanceRecord_ID)
	{
		if (HR_AttendanceRecord_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_AttendanceRecord_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_AttendanceRecord_ID, Integer.valueOf(HR_AttendanceRecord_ID));
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