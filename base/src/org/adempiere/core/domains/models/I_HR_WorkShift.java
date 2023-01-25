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
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_WorkShift
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4
 */
public interface I_HR_WorkShift 
{

    /** TableName=HR_WorkShift */
    public static final String Table_Name = "HR_WorkShift";

    /** AD_Table_ID=53681 */
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

    /** Column name BreakEndTime */
    public static final String COLUMNNAME_BreakEndTime = "BreakEndTime";

	/** Set Break End Time.
	  * End Time for Break
	  */
	public void setBreakEndTime (Timestamp BreakEndTime);

	/** Get Break End Time.
	  * End Time for Break
	  */
	public Timestamp getBreakEndTime();

    /** Column name BreakHoursNo */
    public static final String COLUMNNAME_BreakHoursNo = "BreakHoursNo";

	/** Set Break Hours No.
	  * Break Hours Number
	  */
	public void setBreakHoursNo (BigDecimal BreakHoursNo);

	/** Get Break Hours No.
	  * Break Hours Number
	  */
	public BigDecimal getBreakHoursNo();

    /** Column name BreakStartTime */
    public static final String COLUMNNAME_BreakStartTime = "BreakStartTime";

	/** Set Break Start Time.
	  * Start Time for Break
	  */
	public void setBreakStartTime (Timestamp BreakStartTime);

	/** Get Break Start Time.
	  * Start Time for Break
	  */
	public Timestamp getBreakStartTime();

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

    /** Column name HR_ShiftGroup_ID */
    public static final String COLUMNNAME_HR_ShiftGroup_ID = "HR_ShiftGroup_ID";

	/** Set Shift Group.
	  * Shift Group
	  */
	public void setHR_ShiftGroup_ID (int HR_ShiftGroup_ID);

	/** Get Shift Group.
	  * Shift Group
	  */
	public int getHR_ShiftGroup_ID();

	public org.adempiere.core.domains.models.I_HR_ShiftGroup getHR_ShiftGroup() throws RuntimeException;

    /** Column name HR_WorkShift_ID */
    public static final String COLUMNNAME_HR_WorkShift_ID = "HR_WorkShift_ID";

	/** Set Work Shift.
	  * Work Shift
	  */
	public void setHR_WorkShift_ID (int HR_WorkShift_ID);

	/** Get Work Shift.
	  * Work Shift
	  */
	public int getHR_WorkShift_ID();

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

    /** Column name IsHasFixedWorkgroup */
    public static final String COLUMNNAME_IsHasFixedWorkgroup = "IsHasFixedWorkgroup";

	/** Set Has fixed Workgroup?	  */
	public void setIsHasFixedWorkgroup (boolean IsHasFixedWorkgroup);

	/** Get Has fixed Workgroup?	  */
	public boolean isHasFixedWorkgroup();

    /** Column name IsOverTimeApplicable */
    public static final String COLUMNNAME_IsOverTimeApplicable = "IsOverTimeApplicable";

	/** Set Over Time Applicable.
	  * Is over time applicable to this Shift?
	  */
	public void setIsOverTimeApplicable (boolean IsOverTimeApplicable);

	/** Get Over Time Applicable.
	  * Is over time applicable to this Shift?
	  */
	public boolean isOverTimeApplicable();

    /** Column name IsVariableEntrance */
    public static final String COLUMNNAME_IsVariableEntrance = "IsVariableEntrance";

	/** Set Variable Entrance.
	  * Variable Entrance
	  */
	public void setIsVariableEntrance (boolean IsVariableEntrance);

	/** Get Variable Entrance.
	  * Variable Entrance
	  */
	public boolean isVariableEntrance();

    /** Column name MinAttendanceRequire */
    public static final String COLUMNNAME_MinAttendanceRequire = "MinAttendanceRequire";

	/** Set Minimum Attendance Require.
	  * Minimum Attendance Require to elgibile leave
	  */
	public void setMinAttendanceRequire (int MinAttendanceRequire);

	/** Get Minimum Attendance Require.
	  * Minimum Attendance Require to elgibile leave
	  */
	public int getMinAttendanceRequire();

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

    /** Column name NoOfHours */
    public static final String COLUMNNAME_NoOfHours = "NoOfHours";

	/** Set Number of Hours.
	  * Number of Hours This Shift Contains
	  */
	public void setNoOfHours (BigDecimal NoOfHours);

	/** Get Number of Hours.
	  * Number of Hours This Shift Contains
	  */
	public BigDecimal getNoOfHours();

    /** Column name OnFriday */
    public static final String COLUMNNAME_OnFriday = "OnFriday";

	/** Set Friday.
	  * Available on Fridays
	  */
	public void setOnFriday (boolean OnFriday);

	/** Get Friday.
	  * Available on Fridays
	  */
	public boolean isOnFriday();

    /** Column name OnMonday */
    public static final String COLUMNNAME_OnMonday = "OnMonday";

	/** Set Monday.
	  * Available on Mondays
	  */
	public void setOnMonday (boolean OnMonday);

	/** Get Monday.
	  * Available on Mondays
	  */
	public boolean isOnMonday();

    /** Column name OnSaturday */
    public static final String COLUMNNAME_OnSaturday = "OnSaturday";

	/** Set Saturday.
	  * Available on Saturday
	  */
	public void setOnSaturday (boolean OnSaturday);

	/** Get Saturday.
	  * Available on Saturday
	  */
	public boolean isOnSaturday();

    /** Column name OnSunday */
    public static final String COLUMNNAME_OnSunday = "OnSunday";

	/** Set Sunday.
	  * Available on Sundays
	  */
	public void setOnSunday (boolean OnSunday);

	/** Get Sunday.
	  * Available on Sundays
	  */
	public boolean isOnSunday();

    /** Column name OnThursday */
    public static final String COLUMNNAME_OnThursday = "OnThursday";

	/** Set Thursday.
	  * Available on Thursdays
	  */
	public void setOnThursday (boolean OnThursday);

	/** Get Thursday.
	  * Available on Thursdays
	  */
	public boolean isOnThursday();

    /** Column name OnTuesday */
    public static final String COLUMNNAME_OnTuesday = "OnTuesday";

	/** Set Tuesday.
	  * Available on Tuesdays
	  */
	public void setOnTuesday (boolean OnTuesday);

	/** Get Tuesday.
	  * Available on Tuesdays
	  */
	public boolean isOnTuesday();

    /** Column name OnWednesday */
    public static final String COLUMNNAME_OnWednesday = "OnWednesday";

	/** Set Wednesday.
	  * Available on Wednesdays
	  */
	public void setOnWednesday (boolean OnWednesday);

	/** Get Wednesday.
	  * Available on Wednesdays
	  */
	public boolean isOnWednesday();

    /** Column name OverTimeAmount */
    public static final String COLUMNNAME_OverTimeAmount = "OverTimeAmount";

	/** Set Over Time Amount.
	  * Is Over time Applicable  Then How Much Amount
	  */
	public void setOverTimeAmount (BigDecimal OverTimeAmount);

	/** Get Over Time Amount.
	  * Is Over time Applicable  Then How Much Amount
	  */
	public BigDecimal getOverTimeAmount();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name ShiftFromTime */
    public static final String COLUMNNAME_ShiftFromTime = "ShiftFromTime";

	/** Set Shift From Time.
	  * Shift Starting Time
	  */
	public void setShiftFromTime (Timestamp ShiftFromTime);

	/** Get Shift From Time.
	  * Shift Starting Time
	  */
	public Timestamp getShiftFromTime();

    /** Column name ShiftToTime */
    public static final String COLUMNNAME_ShiftToTime = "ShiftToTime";

	/** Set Shift To Time.
	  * Shift Ending At Time
	  */
	public void setShiftToTime (Timestamp ShiftToTime);

	/** Get Shift To Time.
	  * Shift Ending At Time
	  */
	public Timestamp getShiftToTime();

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
