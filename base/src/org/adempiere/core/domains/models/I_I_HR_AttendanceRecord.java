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
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_HR_AttendanceRecord
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2
 */
public interface I_I_HR_AttendanceRecord 
{

    /** TableName=I_HR_AttendanceRecord */
    public static final String Table_Name = "I_HR_AttendanceRecord";

    /** AD_Table_ID=54500 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AttendanceTime */
    public static final String COLUMNNAME_AttendanceTime = "AttendanceTime";

	/** Set Attendance Time.
	  * Attendance Time for Employee
	  */
	public void setAttendanceTime (Timestamp AttendanceTime);

	/** Get Attendance Time.
	  * Attendance Time for Employee
	  */
	public Timestamp getAttendanceTime();

    /** Column name Code */
    public static final String COLUMNNAME_Code = "Code";

	/** Set Validation code.
	  * Validation Code
	  */
	public void setCode (String Code);

	/** Get Validation code.
	  * Validation Code
	  */
	public String getCode();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DeviceCode */
    public static final String COLUMNNAME_DeviceCode = "DeviceCode";

	/** Set Device Code.
	  * Device Code
	  */
	public void setDeviceCode (String DeviceCode);

	/** Get Device Code.
	  * Device Code
	  */
	public String getDeviceCode();

    /** Column name HR_AttendanceBatch_ID */
    public static final String COLUMNNAME_HR_AttendanceBatch_ID = "HR_AttendanceBatch_ID";

	/** Set Attendance Batch	  */
	public void setHR_AttendanceBatch_ID (int HR_AttendanceBatch_ID);

	/** Get Attendance Batch	  */
	public int getHR_AttendanceBatch_ID();

	public org.adempiere.core.domains.models.I_HR_AttendanceBatch getHR_AttendanceBatch() throws RuntimeException;

    /** Column name HR_AttendanceRecord_ID */
    public static final String COLUMNNAME_HR_AttendanceRecord_ID = "HR_AttendanceRecord_ID";

	/** Set Attendance Record.
	  * Attendance Record
	  */
	public void setHR_AttendanceRecord_ID (int HR_AttendanceRecord_ID);

	/** Get Attendance Record.
	  * Attendance Record
	  */
	public int getHR_AttendanceRecord_ID();

	public org.adempiere.core.domains.models.I_HR_AttendanceRecord getHR_AttendanceRecord() throws RuntimeException;

    /** Column name HR_Employee_ID */
    public static final String COLUMNNAME_HR_Employee_ID = "HR_Employee_ID";

	/** Set Payroll Employee	  */
	public void setHR_Employee_ID (int HR_Employee_ID);

	/** Get Payroll Employee	  */
	public int getHR_Employee_ID();

	// public org.eevolution.model.I_HR_Employee getHR_Employee() throws RuntimeException;

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_HR_AttendanceRecord_ID */
    public static final String COLUMNNAME_I_HR_AttendanceRecord_ID = "I_HR_AttendanceRecord_ID";

	/** Set Attendance Record Import	  */
	public void setI_HR_AttendanceRecord_ID (int I_HR_AttendanceRecord_ID);

	/** Get Attendance Record Import	  */
	public int getI_HR_AttendanceRecord_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
