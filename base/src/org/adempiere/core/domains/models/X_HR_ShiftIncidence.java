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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for HR_ShiftIncidence
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_HR_ShiftIncidence extends PO implements I_HR_ShiftIncidence, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220801L;

    /** Standard Constructor */
    public X_HR_ShiftIncidence (Properties ctx, int HR_ShiftIncidence_ID, String trxName)
    {
      super (ctx, HR_ShiftIncidence_ID, trxName);
      /** if (HR_ShiftIncidence_ID == 0)
        {
			setEventType (null);
// I
			setHR_Concept_ID (0);
			setHR_ShiftIncidence_ID (0);
			setHR_WorkShift_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_ShiftIncidence (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_ShiftIncidence[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.adempiere.core.domains.models.I_AD_Rule getAD_Rule() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Rule)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Rule.Table_Name)
			.getPO(getAD_Rule_ID(), get_TrxName());	}

	/** Set Rule.
		@param AD_Rule_ID Rule	  */
	public void setAD_Rule_ID (int AD_Rule_ID)
	{
		if (AD_Rule_ID < 1) 
			set_Value (COLUMNNAME_AD_Rule_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Rule_ID, Integer.valueOf(AD_Rule_ID));
	}

	/** Get Rule.
		@return Rule	  */
	public int getAD_Rule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Rule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Beginning Time.
		@param BeginningTime 
		Beginning Time for evaluate incidence
	  */
	public void setBeginningTime (Timestamp BeginningTime)
	{
		set_Value (COLUMNNAME_BeginningTime, BeginningTime);
	}

	/** Get Beginning Time.
		@return Beginning Time for evaluate incidence
	  */
	public Timestamp getBeginningTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_BeginningTime);
	}

	/** Set Default Amount.
		@param DefaultAmt 
		Default Amount for it Record
	  */
	public void setDefaultAmt (BigDecimal DefaultAmt)
	{
		set_Value (COLUMNNAME_DefaultAmt, DefaultAmt);
	}

	/** Get Default Amount.
		@return Default Amount for it Record
	  */
	public BigDecimal getDefaultAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DefaultAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Default Quantity.
		@param DefaultQty 
		Default Quantity for it Record
	  */
	public void setDefaultQty (BigDecimal DefaultQty)
	{
		set_Value (COLUMNNAME_DefaultQty, DefaultQty);
	}

	/** Get Default Quantity.
		@return Default Quantity for it Record
	  */
	public BigDecimal getDefaultQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DefaultQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** EventType AD_Reference_ID=54066 */
	public static final int EVENTTYPE_AD_Reference_ID=54066;
	/** Attendance = A */
	public static final String EVENTTYPE_Attendance = "A";
	/** Entrance = I */
	public static final String EVENTTYPE_Entrance = "I";
	/** Leave = L */
	public static final String EVENTTYPE_Leave = "L";
	/** Egress = O */
	public static final String EVENTTYPE_Egress = "O";
	/** Set Event Type.
		@param EventType 
		Type of Event
	  */
	public void setEventType (String EventType)
	{

		set_Value (COLUMNNAME_EventType, EventType);
	}

	/** Get Event Type.
		@return Type of Event
	  */
	public String getEventType () 
	{
		return (String)get_Value(COLUMNNAME_EventType);
	}

	/** Set Fixed Amount.
		@param FixedAmt 
		Fixed Amount for it Record
	  */
	public void setFixedAmt (BigDecimal FixedAmt)
	{
		set_Value (COLUMNNAME_FixedAmt, FixedAmt);
	}

	/** Get Fixed Amount.
		@return Fixed Amount for it Record
	  */
	public BigDecimal getFixedAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FixedAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Fixed Quantity.
		@param FixedQty 
		Fixed Quantity for it Record
	  */
	public void setFixedQty (BigDecimal FixedQty)
	{
		set_Value (COLUMNNAME_FixedQty, FixedQty);
	}

	/** Get Fixed Quantity.
		@return Fixed Quantity for it Record
	  */
	public BigDecimal getFixedQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FixedQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Global Payroll Concept.
		@param HR_Concept_ID 
		The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public void setHR_Concept_ID (int HR_Concept_ID)
	{
		if (HR_Concept_ID < 1) 
			set_Value (COLUMNNAME_HR_Concept_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Concept_ID, Integer.valueOf(HR_Concept_ID));
	}

	/** Get Global Payroll Concept.
		@return The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public int getHR_Concept_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Concept_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Shift Incidence.
		@param HR_ShiftIncidence_ID 
		Shift Incidence Configuration
	  */
	public void setHR_ShiftIncidence_ID (int HR_ShiftIncidence_ID)
	{
		if (HR_ShiftIncidence_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_ShiftIncidence_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_ShiftIncidence_ID, Integer.valueOf(HR_ShiftIncidence_ID));
	}

	/** Get Shift Incidence.
		@return Shift Incidence Configuration
	  */
	public int getHR_ShiftIncidence_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_ShiftIncidence_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Work Shift.
		@param HR_WorkShift_ID 
		Work Shift
	  */
	public void setHR_WorkShift_ID (int HR_WorkShift_ID)
	{
		if (HR_WorkShift_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_WorkShift_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_WorkShift_ID, Integer.valueOf(HR_WorkShift_ID));
	}

	/** Get Work Shift.
		@return Work Shift
	  */
	public int getHR_WorkShift_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_WorkShift_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Entrance / Egress Anticipated.
		@param IsAnticipatedRecord 
		Entrance / Egress Anticipated allows define what is the range for get time diff
	  */
	public void setIsAnticipatedRecord (boolean IsAnticipatedRecord)
	{
		set_Value (COLUMNNAME_IsAnticipatedRecord, Boolean.valueOf(IsAnticipatedRecord));
	}

	/** Get Entrance / Egress Anticipated.
		@return Entrance / Egress Anticipated allows define what is the range for get time diff
	  */
	public boolean isAnticipatedRecord () 
	{
		Object oo = get_Value(COLUMNNAME_IsAnticipatedRecord);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_Value (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Invoiced.
		@param IsInvoiced 
		Is this invoiced?
	  */
	public void setIsInvoiced (boolean IsInvoiced)
	{
		set_Value (COLUMNNAME_IsInvoiced, Boolean.valueOf(IsInvoiced));
	}

	/** Get Invoiced.
		@return Is this invoiced?
	  */
	public boolean isInvoiced () 
	{
		Object oo = get_Value(COLUMNNAME_IsInvoiced);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Mandatory Range.
		@param IsMandatoryRange 
		Mandatory Range for Attendance
	  */
	public void setIsMandatoryRange (boolean IsMandatoryRange)
	{
		set_Value (COLUMNNAME_IsMandatoryRange, Boolean.valueOf(IsMandatoryRange));
	}

	/** Get Mandatory Range.
		@return Mandatory Range for Attendance
	  */
	public boolean isMandatoryRange () 
	{
		Object oo = get_Value(COLUMNNAME_IsMandatoryRange);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Time Report.
		@param IsTimeReport 
		Line is a time report only (no expense)
	  */
	public void setIsTimeReport (boolean IsTimeReport)
	{
		set_Value (COLUMNNAME_IsTimeReport, Boolean.valueOf(IsTimeReport));
	}

	/** Get Time Report.
		@return Line is a time report only (no expense)
	  */
	public boolean isTimeReport () 
	{
		Object oo = get_Value(COLUMNNAME_IsTimeReport);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Variable Calculation.
		@param IsVariableCalculation 
		Variable Calculation for Shift Incidence
	  */
	public void setIsVariableCalculation (boolean IsVariableCalculation)
	{
		set_Value (COLUMNNAME_IsVariableCalculation, Boolean.valueOf(IsVariableCalculation));
	}

	/** Get Variable Calculation.
		@return Variable Calculation for Shift Incidence
	  */
	public boolean isVariableCalculation () 
	{
		Object oo = get_Value(COLUMNNAME_IsVariableCalculation);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.adempiere.core.domains.models.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_M_Product)MTable.get(getCtx(), org.adempiere.core.domains.models.I_M_Product.Table_Name)
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

	/** Set Friday.
		@param OnFriday 
		Available on Fridays
	  */
	public void setOnFriday (boolean OnFriday)
	{
		set_Value (COLUMNNAME_OnFriday, Boolean.valueOf(OnFriday));
	}

	/** Get Friday.
		@return Available on Fridays
	  */
	public boolean isOnFriday () 
	{
		Object oo = get_Value(COLUMNNAME_OnFriday);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Monday.
		@param OnMonday 
		Available on Mondays
	  */
	public void setOnMonday (boolean OnMonday)
	{
		set_Value (COLUMNNAME_OnMonday, Boolean.valueOf(OnMonday));
	}

	/** Get Monday.
		@return Available on Mondays
	  */
	public boolean isOnMonday () 
	{
		Object oo = get_Value(COLUMNNAME_OnMonday);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Saturday.
		@param OnSaturday 
		Available on Saturday
	  */
	public void setOnSaturday (boolean OnSaturday)
	{
		set_Value (COLUMNNAME_OnSaturday, Boolean.valueOf(OnSaturday));
	}

	/** Get Saturday.
		@return Available on Saturday
	  */
	public boolean isOnSaturday () 
	{
		Object oo = get_Value(COLUMNNAME_OnSaturday);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sunday.
		@param OnSunday 
		Available on Sundays
	  */
	public void setOnSunday (boolean OnSunday)
	{
		set_Value (COLUMNNAME_OnSunday, Boolean.valueOf(OnSunday));
	}

	/** Get Sunday.
		@return Available on Sundays
	  */
	public boolean isOnSunday () 
	{
		Object oo = get_Value(COLUMNNAME_OnSunday);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Thursday.
		@param OnThursday 
		Available on Thursdays
	  */
	public void setOnThursday (boolean OnThursday)
	{
		set_Value (COLUMNNAME_OnThursday, Boolean.valueOf(OnThursday));
	}

	/** Get Thursday.
		@return Available on Thursdays
	  */
	public boolean isOnThursday () 
	{
		Object oo = get_Value(COLUMNNAME_OnThursday);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Tuesday.
		@param OnTuesday 
		Available on Tuesdays
	  */
	public void setOnTuesday (boolean OnTuesday)
	{
		set_Value (COLUMNNAME_OnTuesday, Boolean.valueOf(OnTuesday));
	}

	/** Get Tuesday.
		@return Available on Tuesdays
	  */
	public boolean isOnTuesday () 
	{
		Object oo = get_Value(COLUMNNAME_OnTuesday);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Wednesday.
		@param OnWednesday 
		Available on Wednesdays
	  */
	public void setOnWednesday (boolean OnWednesday)
	{
		set_Value (COLUMNNAME_OnWednesday, Boolean.valueOf(OnWednesday));
	}

	/** Get Wednesday.
		@return Available on Wednesdays
	  */
	public boolean isOnWednesday () 
	{
		Object oo = get_Value(COLUMNNAME_OnWednesday);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Time (From).
		@param TimeFrom 
		Starting Time
	  */
	public void setTimeFrom (Timestamp TimeFrom)
	{
		set_Value (COLUMNNAME_TimeFrom, TimeFrom);
	}

	/** Get Time (From).
		@return Starting Time
	  */
	public Timestamp getTimeFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_TimeFrom);
	}

	/** Set Time (To).
		@param TimeTo 
		Ending Time
	  */
	public void setTimeTo (Timestamp TimeTo)
	{
		set_Value (COLUMNNAME_TimeTo, TimeTo);
	}

	/** Get Time (To).
		@return Ending Time
	  */
	public Timestamp getTimeTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_TimeTo);
	}

	/** TimeUnit AD_Reference_ID=53376 */
	public static final int TIMEUNIT_AD_Reference_ID=53376;
	/** Day = D */
	public static final String TIMEUNIT_Day = "D";
	/** Week = W */
	public static final String TIMEUNIT_Week = "W";
	/** Month = M */
	public static final String TIMEUNIT_Month = "M";
	/** Quarter = Q */
	public static final String TIMEUNIT_Quarter = "Q";
	/** Year = Y */
	public static final String TIMEUNIT_Year = "Y";
	/** Hour = H */
	public static final String TIMEUNIT_Hour = "H";
	/** Minute = I */
	public static final String TIMEUNIT_Minute = "I";
	/** Set Time Unit.
		@param TimeUnit 
		The unit of time for grouping chart data.
	  */
	public void setTimeUnit (String TimeUnit)
	{

		set_Value (COLUMNNAME_TimeUnit, TimeUnit);
	}

	/** Get Time Unit.
		@return The unit of time for grouping chart data.
	  */
	public String getTimeUnit () 
	{
		return (String)get_Value(COLUMNNAME_TimeUnit);
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