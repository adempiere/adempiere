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
package org.adempiere.core.api;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_ShiftIncidence
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3
 */
public interface I_HR_ShiftIncidence 
{

    /** TableName=HR_ShiftIncidence */
    public static final String Table_Name = "HR_ShiftIncidence";

    /** AD_Table_ID=54502 */
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

    /** Column name AD_Rule_ID */
    public static final String COLUMNNAME_AD_Rule_ID = "AD_Rule_ID";

	/** Set Rule	  */
	public void setAD_Rule_ID (int AD_Rule_ID);

	/** Get Rule	  */
	public int getAD_Rule_ID();

	public org.compiere.model.I_AD_Rule getAD_Rule() throws RuntimeException;

    /** Column name BeginningTime */
    public static final String COLUMNNAME_BeginningTime = "BeginningTime";

	/** Set Beginning Time.
	  * Beginning Time for evaluate incidence
	  */
	public void setBeginningTime (Timestamp BeginningTime);

	/** Get Beginning Time.
	  * Beginning Time for evaluate incidence
	  */
	public Timestamp getBeginningTime();

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

    /** Column name DefaultAmt */
    public static final String COLUMNNAME_DefaultAmt = "DefaultAmt";

	/** Set Default Amount.
	  * Default Amount for it Record
	  */
	public void setDefaultAmt (BigDecimal DefaultAmt);

	/** Get Default Amount.
	  * Default Amount for it Record
	  */
	public BigDecimal getDefaultAmt();

    /** Column name DefaultQty */
    public static final String COLUMNNAME_DefaultQty = "DefaultQty";

	/** Set Default Quantity.
	  * Default Quantity for it Record
	  */
	public void setDefaultQty (BigDecimal DefaultQty);

	/** Get Default Quantity.
	  * Default Quantity for it Record
	  */
	public BigDecimal getDefaultQty();

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

    /** Column name EventType */
    public static final String COLUMNNAME_EventType = "EventType";

	/** Set Event Type.
	  * Type of Event
	  */
	public void setEventType (String EventType);

	/** Get Event Type.
	  * Type of Event
	  */
	public String getEventType();

    /** Column name FixedAmt */
    public static final String COLUMNNAME_FixedAmt = "FixedAmt";

	/** Set Fixed Amount.
	  * Fixed Amount for it Record
	  */
	public void setFixedAmt (BigDecimal FixedAmt);

	/** Get Fixed Amount.
	  * Fixed Amount for it Record
	  */
	public BigDecimal getFixedAmt();

    /** Column name FixedQty */
    public static final String COLUMNNAME_FixedQty = "FixedQty";

	/** Set Fixed Quantity.
	  * Fixed Quantity for it Record
	  */
	public void setFixedQty (BigDecimal FixedQty);

	/** Get Fixed Quantity.
	  * Fixed Quantity for it Record
	  */
	public BigDecimal getFixedQty();

    /** Column name HR_Concept_ID */
    public static final String COLUMNNAME_HR_Concept_ID = "HR_Concept_ID";

	/** Set Global Payroll Concept.
	  * The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public void setHR_Concept_ID (int HR_Concept_ID);

	/** Get Global Payroll Concept.
	  * The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public int getHR_Concept_ID();

    /** Column name HR_ShiftIncidence_ID */
    public static final String COLUMNNAME_HR_ShiftIncidence_ID = "HR_ShiftIncidence_ID";

	/** Set Shift Incidence.
	  * Shift Incidence Configuration
	  */
	public void setHR_ShiftIncidence_ID (int HR_ShiftIncidence_ID);

	/** Get Shift Incidence.
	  * Shift Incidence Configuration
	  */
	public int getHR_ShiftIncidence_ID();

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

    /** Column name IsAnticipatedRecord */
    public static final String COLUMNNAME_IsAnticipatedRecord = "IsAnticipatedRecord";

	/** Set Entrance / Egress Anticipated.
	  * Entrance / Egress Anticipated allows define what is the range for get time diff
	  */
	public void setIsAnticipatedRecord (boolean IsAnticipatedRecord);

	/** Get Entrance / Egress Anticipated.
	  * Entrance / Egress Anticipated allows define what is the range for get time diff
	  */
	public boolean isAnticipatedRecord();

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

    /** Column name IsInvoiced */
    public static final String COLUMNNAME_IsInvoiced = "IsInvoiced";

	/** Set Invoiced.
	  * Is this invoiced?
	  */
	public void setIsInvoiced (boolean IsInvoiced);

	/** Get Invoiced.
	  * Is this invoiced?
	  */
	public boolean isInvoiced();

    /** Column name IsMandatoryRange */
    public static final String COLUMNNAME_IsMandatoryRange = "IsMandatoryRange";

	/** Set Mandatory Range.
	  * Mandatory Range for Attendance
	  */
	public void setIsMandatoryRange (boolean IsMandatoryRange);

	/** Get Mandatory Range.
	  * Mandatory Range for Attendance
	  */
	public boolean isMandatoryRange();

    /** Column name IsTimeReport */
    public static final String COLUMNNAME_IsTimeReport = "IsTimeReport";

	/** Set Time Report.
	  * Line is a time report only (no expense)
	  */
	public void setIsTimeReport (boolean IsTimeReport);

	/** Get Time Report.
	  * Line is a time report only (no expense)
	  */
	public boolean isTimeReport();

    /** Column name IsVariableCalculation */
    public static final String COLUMNNAME_IsVariableCalculation = "IsVariableCalculation";

	/** Set Variable Calculation.
	  * Variable Calculation for Shift Incidence
	  */
	public void setIsVariableCalculation (boolean IsVariableCalculation);

	/** Get Variable Calculation.
	  * Variable Calculation for Shift Incidence
	  */
	public boolean isVariableCalculation();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

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

    /** Column name TimeFrom */
    public static final String COLUMNNAME_TimeFrom = "TimeFrom";

	/** Set Time (From).
	  * Starting Time
	  */
	public void setTimeFrom (Timestamp TimeFrom);

	/** Get Time (From).
	  * Starting Time
	  */
	public Timestamp getTimeFrom();

    /** Column name TimeTo */
    public static final String COLUMNNAME_TimeTo = "TimeTo";

	/** Set Time (To).
	  * Ending Time
	  */
	public void setTimeTo (Timestamp TimeTo);

	/** Get Time (To).
	  * Ending Time
	  */
	public Timestamp getTimeTo();

    /** Column name TimeUnit */
    public static final String COLUMNNAME_TimeUnit = "TimeUnit";

	/** Set Time Unit.
	  * The unit of time for grouping chart data.
	  */
	public void setTimeUnit (String TimeUnit);

	/** Get Time Unit.
	  * The unit of time for grouping chart data.
	  */
	public String getTimeUnit();

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
}
