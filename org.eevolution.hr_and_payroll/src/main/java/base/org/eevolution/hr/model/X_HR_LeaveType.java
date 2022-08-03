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
package org.eevolution.hr.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.core.api.I_HR_LeaveType;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_LeaveType
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_HR_LeaveType extends PO implements I_HR_LeaveType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220801L;

    /** Standard Constructor */
    public X_HR_LeaveType (Properties ctx, int HR_LeaveType_ID, String trxName)
    {
      super (ctx, HR_LeaveType_ID, trxName);
      /** if (HR_LeaveType_ID == 0)
        {
			setHR_LeaveType_ID (0);
			setLeaveCreditTimeType (null);
			setMinAttendanceRequire (0);
			setName (null);
			setNoOfLeavesAllocated (0);
        } */
    }

    /** Load Constructor */
    public X_HR_LeaveType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_LeaveType[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AdjacentHolidayType AD_Reference_ID=53609 */
	public static final int ADJACENTHOLIDAYTYPE_AD_Reference_ID=53609;
	/** Adjacent = AH */
	public static final String ADJACENTHOLIDAYTYPE_Adjacent = "AH";
	/** Included & Adjacent = IA */
	public static final String ADJACENTHOLIDAYTYPE_IncludedAdjacent = "IA";
	/** Included = IH */
	public static final String ADJACENTHOLIDAYTYPE_Included = "IH";
	/** None = NO */
	public static final String ADJACENTHOLIDAYTYPE_None = "NO";
	/** Set Adjacent Holidays Type.
		@param AdjacentHolidayType 
		Adjacent Holidays Type
	  */
	public void setAdjacentHolidayType (String AdjacentHolidayType)
	{

		set_Value (COLUMNNAME_AdjacentHolidayType, AdjacentHolidayType);
	}

	/** Get Adjacent Holidays Type.
		@return Adjacent Holidays Type
	  */
	public String getAdjacentHolidayType () 
	{
		return (String)get_Value(COLUMNNAME_AdjacentHolidayType);
	}

	/** Set Date next run.
		@param DateNextRun 
		Date the process will run next
	  */
	public void setDateNextRun (Timestamp DateNextRun)
	{
		set_Value (COLUMNNAME_DateNextRun, DateNextRun);
	}

	/** Get Date next run.
		@return Date the process will run next
	  */
	public Timestamp getDateNextRun () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateNextRun);
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

	/** FrequencyType AD_Reference_ID=221 */
	public static final int FREQUENCYTYPE_AD_Reference_ID=221;
	/** Minute = M */
	public static final String FREQUENCYTYPE_Minute = "M";
	/** Hour = H */
	public static final String FREQUENCYTYPE_Hour = "H";
	/** Day = D */
	public static final String FREQUENCYTYPE_Day = "D";
	/** Biweekly = B */
	public static final String FREQUENCYTYPE_Biweekly = "B";
	/** Monthly = N */
	public static final String FREQUENCYTYPE_Monthly = "N";
	/** Quarterly = Q */
	public static final String FREQUENCYTYPE_Quarterly = "Q";
	/** Weekly = W */
	public static final String FREQUENCYTYPE_Weekly = "W";
	/** Yearly = Y */
	public static final String FREQUENCYTYPE_Yearly = "Y";
	/** Does not repeat = X */
	public static final String FREQUENCYTYPE_DoesNotRepeat = "X";
	/** Secound = S */
	public static final String FREQUENCYTYPE_Secound = "S";
	/** Set Frequency Type.
		@param FrequencyType 
		Frequency of event
	  */
	public void setFrequencyType (String FrequencyType)
	{

		set_Value (COLUMNNAME_FrequencyType, FrequencyType);
	}

	/** Get Frequency Type.
		@return Frequency of event
	  */
	public String getFrequencyType () 
	{
		return (String)get_Value(COLUMNNAME_FrequencyType);
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

	/** Set Consider Adjacent Holidays As Leave.
		@param IsAdjacentHolidayAsLeave 
		Consider Adjacent Holidays As Leave
	  */
	public void setIsAdjacentHolidayAsLeave (boolean IsAdjacentHolidayAsLeave)
	{
		set_Value (COLUMNNAME_IsAdjacentHolidayAsLeave, Boolean.valueOf(IsAdjacentHolidayAsLeave));
	}

	/** Get Consider Adjacent Holidays As Leave.
		@return Consider Adjacent Holidays As Leave
	  */
	public boolean isAdjacentHolidayAsLeave () 
	{
		Object oo = get_Value(COLUMNNAME_IsAdjacentHolidayAsLeave);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Allowed Encashment.
		@param IsAllowedEncashment 
		Allowed Encashment
	  */
	public void setIsAllowedEncashment (boolean IsAllowedEncashment)
	{
		set_Value (COLUMNNAME_IsAllowedEncashment, Boolean.valueOf(IsAllowedEncashment));
	}

	/** Get Allowed Encashment.
		@return Allowed Encashment
	  */
	public boolean isAllowedEncashment () 
	{
		Object oo = get_Value(COLUMNNAME_IsAllowedEncashment);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Half Day Leave Allowed.
		@param IsHalfDayLeaveAllowed 
		Half Day Leave Allowed
	  */
	public void setIsHalfDayLeaveAllowed (boolean IsHalfDayLeaveAllowed)
	{
		set_Value (COLUMNNAME_IsHalfDayLeaveAllowed, Boolean.valueOf(IsHalfDayLeaveAllowed));
	}

	/** Get Half Day Leave Allowed.
		@return Half Day Leave Allowed
	  */
	public boolean isHalfDayLeaveAllowed () 
	{
		Object oo = get_Value(COLUMNNAME_IsHalfDayLeaveAllowed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Leave Repeated.
		@param IsLeaveRepeated 
		Is Leave Repeated
	  */
	public void setIsLeaveRepeated (boolean IsLeaveRepeated)
	{
		set_Value (COLUMNNAME_IsLeaveRepeated, Boolean.valueOf(IsLeaveRepeated));
	}

	/** Get Is Leave Repeated.
		@return Is Leave Repeated
	  */
	public boolean isLeaveRepeated () 
	{
		Object oo = get_Value(COLUMNNAME_IsLeaveRepeated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Leaves forward to next year.
		@param IsLeavesForwardToNextYear 
		Leaves forward to next year
	  */
	public void setIsLeavesForwardToNextYear (boolean IsLeavesForwardToNextYear)
	{
		set_Value (COLUMNNAME_IsLeavesForwardToNextYear, Boolean.valueOf(IsLeavesForwardToNextYear));
	}

	/** Get Leaves forward to next year.
		@return Leaves forward to next year
	  */
	public boolean isLeavesForwardToNextYear () 
	{
		Object oo = get_Value(COLUMNNAME_IsLeavesForwardToNextYear);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Provident Fund Employee.
		@param IsProvidentFund 
		Is Provident Fund Employee
	  */
	public void setIsProvidentFund (boolean IsProvidentFund)
	{
		set_Value (COLUMNNAME_IsProvidentFund, Boolean.valueOf(IsProvidentFund));
	}

	/** Get Is Provident Fund Employee.
		@return Is Provident Fund Employee
	  */
	public boolean isProvidentFund () 
	{
		Object oo = get_Value(COLUMNNAME_IsProvidentFund);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** LeaveCreditTimeType AD_Reference_ID=53608 */
	public static final int LEAVECREDITTIMETYPE_AD_Reference_ID=53608;
	/** Begining Of The Month = BP */
	public static final String LEAVECREDITTIMETYPE_BeginingOfTheMonth = "BP";
	/** End Of The Month = EP */
	public static final String LEAVECREDITTIMETYPE_EndOfTheMonth = "EP";
	/** Variable Date = VD */
	public static final String LEAVECREDITTIMETYPE_VariableDate = "VD";
	/** Set Leave Credit Time Type.
		@param LeaveCreditTimeType 
		Leave Credit Time Type
	  */
	public void setLeaveCreditTimeType (String LeaveCreditTimeType)
	{

		set_Value (COLUMNNAME_LeaveCreditTimeType, LeaveCreditTimeType);
	}

	/** Get Leave Credit Time Type.
		@return Leave Credit Time Type
	  */
	public String getLeaveCreditTimeType () 
	{
		return (String)get_Value(COLUMNNAME_LeaveCreditTimeType);
	}

	/** Set Leave Duration Time.
		@param LeaveDurationTime 
		Leave Duration Time
	  */
	public void setLeaveDurationTime (BigDecimal LeaveDurationTime)
	{
		set_Value (COLUMNNAME_LeaveDurationTime, LeaveDurationTime);
	}

	/** Get Leave Duration Time.
		@return Leave Duration Time
	  */
	public BigDecimal getLeaveDurationTime () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LeaveDurationTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Maximum continous leaves.
		@param MaxContinousLeaves 
		Maximum continous leaves
	  */
	public void setMaxContinousLeaves (int MaxContinousLeaves)
	{
		set_Value (COLUMNNAME_MaxContinousLeaves, Integer.valueOf(MaxContinousLeaves));
	}

	/** Get Maximum continous leaves.
		@return Maximum continous leaves
	  */
	public int getMaxContinousLeaves () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MaxContinousLeaves);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Maximum leaves carry forwarded.
		@param MaxLeavesToForward 
		Maximum leaves carry forwarded
	  */
	public void setMaxLeavesToForward (int MaxLeavesToForward)
	{
		set_Value (COLUMNNAME_MaxLeavesToForward, Integer.valueOf(MaxLeavesToForward));
	}

	/** Get Maximum leaves carry forwarded.
		@return Maximum leaves carry forwarded
	  */
	public int getMaxLeavesToForward () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MaxLeavesToForward);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Minimum Attendance Require.
		@param MinAttendanceRequire 
		Minimum Attendance Require to elgibile leave
	  */
	public void setMinAttendanceRequire (int MinAttendanceRequire)
	{
		set_Value (COLUMNNAME_MinAttendanceRequire, Integer.valueOf(MinAttendanceRequire));
	}

	/** Get Minimum Attendance Require.
		@return Minimum Attendance Require to elgibile leave
	  */
	public int getMinAttendanceRequire () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MinAttendanceRequire);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Minimum Encashment Days in a Request.
		@param MinForEncashmentDays 
		Minimum Encashment Days in a Request
	  */
	public void setMinForEncashmentDays (int MinForEncashmentDays)
	{
		set_Value (COLUMNNAME_MinForEncashmentDays, Integer.valueOf(MinForEncashmentDays));
	}

	/** Get Minimum Encashment Days in a Request.
		@return Minimum Encashment Days in a Request
	  */
	public int getMinForEncashmentDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MinForEncashmentDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Number of Leaves Allocated.
		@param NoOfLeavesAllocated 
		Number of Leaves Allocated
	  */
	public void setNoOfLeavesAllocated (int NoOfLeavesAllocated)
	{
		set_Value (COLUMNNAME_NoOfLeavesAllocated, Integer.valueOf(NoOfLeavesAllocated));
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}