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
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_LeaveType
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_HR_LeaveType 
{

    /** TableName=HR_LeaveType */
    public static final String Table_Name = "HR_LeaveType";

    /** AD_Table_ID=53677 */
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

    /** Column name AdjacentHolidayType */
    public static final String COLUMNNAME_AdjacentHolidayType = "AdjacentHolidayType";

	/** Set Adjacent Holidays Type.
	  * Adjacent Holidays Type
	  */
	public void setAdjacentHolidayType (String AdjacentHolidayType);

	/** Get Adjacent Holidays Type.
	  * Adjacent Holidays Type
	  */
	public String getAdjacentHolidayType();

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

    /** Column name DateNextRun */
    public static final String COLUMNNAME_DateNextRun = "DateNextRun";

	/** Set Date next run.
	  * Date the process will run next
	  */
	public void setDateNextRun (Timestamp DateNextRun);

	/** Get Date next run.
	  * Date the process will run next
	  */
	public Timestamp getDateNextRun();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name HR_LeaveType_ID */
    public static final String COLUMNNAME_HR_LeaveType_ID = "HR_LeaveType_ID";

	/** Set Leave Type.
	  * Leave Type for an Employee
	  */
	public void setHR_LeaveType_ID (int HR_LeaveType_ID);

	/** Get Leave Type.
	  * Leave Type for an Employee
	  */
	public int getHR_LeaveType_ID();

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

    /** Column name IsAdjacentHolidayAsLeave */
    public static final String COLUMNNAME_IsAdjacentHolidayAsLeave = "IsAdjacentHolidayAsLeave";

	/** Set Consider Adjacent Holidays As Leave?.
	  * Consider Adjacent Holidays As Leave?
	  */
	public void setIsAdjacentHolidayAsLeave (boolean IsAdjacentHolidayAsLeave);

	/** Get Consider Adjacent Holidays As Leave?.
	  * Consider Adjacent Holidays As Leave?
	  */
	public boolean isAdjacentHolidayAsLeave();

    /** Column name IsAllowedEnCashment */
    public static final String COLUMNNAME_IsAllowedEnCashment = "IsAllowedEnCashment";

	/** Set Allowed En Cashment?.
	  * Allowed En Cashment? 
	  */
	public void setIsAllowedEnCashment (boolean IsAllowedEnCashment);

	/** Get Allowed En Cashment?.
	  * Allowed En Cashment? 
	  */
	public boolean isAllowedEnCashment();

    /** Column name IsHalfDayLeaveAllowed */
    public static final String COLUMNNAME_IsHalfDayLeaveAllowed = "IsHalfDayLeaveAllowed";

	/** Set Half Day Leave Allowed ?.
	  * Half Day Leave Allowed ?
	  */
	public void setIsHalfDayLeaveAllowed (boolean IsHalfDayLeaveAllowed);

	/** Get Half Day Leave Allowed ?.
	  * Half Day Leave Allowed ?
	  */
	public boolean isHalfDayLeaveAllowed();

    /** Column name IsLeaveRepeated */
    public static final String COLUMNNAME_IsLeaveRepeated = "IsLeaveRepeated";

	/** Set Is Leave Repeated?.
	  * Is Leave Repeated?
	  */
	public void setIsLeaveRepeated (boolean IsLeaveRepeated);

	/** Get Is Leave Repeated?.
	  * Is Leave Repeated?
	  */
	public boolean isLeaveRepeated();

    /** Column name IsLeavesForwardToNextYear */
    public static final String COLUMNNAME_IsLeavesForwardToNextYear = "IsLeavesForwardToNextYear";

	/** Set Leaves forward to next year.
	  * Leaves forward to next year
	  */
	public void setIsLeavesForwardToNextYear (boolean IsLeavesForwardToNextYear);

	/** Get Leaves forward to next year.
	  * Leaves forward to next year
	  */
	public boolean isLeavesForwardToNextYear();

    /** Column name IsProvidentFund */
    public static final String COLUMNNAME_IsProvidentFund = "IsProvidentFund";

	/** Set Is Provident Fund Employee?.
	  * Is Provident Fund Employee?
	  */
	public void setIsProvidentFund (boolean IsProvidentFund);

	/** Get Is Provident Fund Employee?.
	  * Is Provident Fund Employee?
	  */
	public boolean isProvidentFund();

    /** Column name LeaveCreditTimeType */
    public static final String COLUMNNAME_LeaveCreditTimeType = "LeaveCreditTimeType";

	/** Set Leave Credit Time Type.
	  * Leave Credit Time Type
	  */
	public void setLeaveCreditTimeType (String LeaveCreditTimeType);

	/** Get Leave Credit Time Type.
	  * Leave Credit Time Type
	  */
	public String getLeaveCreditTimeType();

    /** Column name MaxContinousLeaves */
    public static final String COLUMNNAME_MaxContinousLeaves = "MaxContinousLeaves";

	/** Set Maximum continous leaves.
	  * Maximum continous leaves
	  */
	public void setMaxContinousLeaves (BigDecimal MaxContinousLeaves);

	/** Get Maximum continous leaves.
	  * Maximum continous leaves
	  */
	public BigDecimal getMaxContinousLeaves();

    /** Column name MaxLeavesToForward */
    public static final String COLUMNNAME_MaxLeavesToForward = "MaxLeavesToForward";

	/** Set Maximum leaves carry forwarded.
	  * Maximum leaves carry forwarded
	  */
	public void setMaxLeavesToForward (BigDecimal MaxLeavesToForward);

	/** Get Maximum leaves carry forwarded.
	  * Maximum leaves carry forwarded
	  */
	public BigDecimal getMaxLeavesToForward();

    /** Column name MinAttendanceRequire */
    public static final String COLUMNNAME_MinAttendanceRequire = "MinAttendanceRequire";

	/** Set Minimum Attendance Require.
	  * Minimum Attendance Require to elgibile leave
	  */
	public void setMinAttendanceRequire (BigDecimal MinAttendanceRequire);

	/** Get Minimum Attendance Require.
	  * Minimum Attendance Require to elgibile leave
	  */
	public BigDecimal getMinAttendanceRequire();

    /** Column name MinForencashmentDays */
    public static final String COLUMNNAME_MinForencashmentDays = "MinForencashmentDays";

	/** Set Minimum Encashment Days in a Request.
	  * Minimum Encashment Days in a Request
	  */
	public void setMinForencashmentDays (BigDecimal MinForencashmentDays);

	/** Get Minimum Encashment Days in a Request.
	  * Minimum Encashment Days in a Request
	  */
	public BigDecimal getMinForencashmentDays();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name NoOfLeavesAllocated */
    public static final String COLUMNNAME_NoOfLeavesAllocated = "NoOfLeavesAllocated";

	/** Set Number of Leaves Allocated.
	  * Number of Leaves Allocated
	  */
	public void setNoOfLeavesAllocated (BigDecimal NoOfLeavesAllocated);

	/** Get Number of Leaves Allocated.
	  * Number of Leaves Allocated
	  */
	public BigDecimal getNoOfLeavesAllocated();

    /** Column name RepeatedType */
    public static final String COLUMNNAME_RepeatedType = "RepeatedType";

	/** Set Repeated type.
	  * Repeated type
	  */
	public void setRepeatedType (String RepeatedType);

	/** Get Repeated type.
	  * Repeated type
	  */
	public String getRepeatedType();

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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
