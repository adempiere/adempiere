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
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_WorkShift
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_HR_WorkShift extends PO implements I_HR_WorkShift, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220801L;

    /** Standard Constructor */
    public X_HR_WorkShift (Properties ctx, int HR_WorkShift_ID, String trxName)
    {
      super (ctx, HR_WorkShift_ID, trxName);
      /** if (HR_WorkShift_ID == 0)
        {
			setHR_WorkShift_ID (0);
			setIsHasFixedWorkgroup (false);
			setIsOverTimeApplicable (false);
			setMinAttendanceRequire (0);
// 2
			setName (null);
			setShiftFromTime (new Timestamp( System.currentTimeMillis() ));
			setShiftToTime (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_HR_WorkShift (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_WorkShift[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Break End Time.
		@param BreakEndTime 
		End Time for Break
	  */
	public void setBreakEndTime (Timestamp BreakEndTime)
	{
		set_Value (COLUMNNAME_BreakEndTime, BreakEndTime);
	}

	/** Get Break End Time.
		@return End Time for Break
	  */
	public Timestamp getBreakEndTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_BreakEndTime);
	}

	/** Set Break Hours No.
		@param BreakHoursNo 
		Break Hours Number
	  */
	public void setBreakHoursNo (BigDecimal BreakHoursNo)
	{
		set_Value (COLUMNNAME_BreakHoursNo, BreakHoursNo);
	}

	/** Get Break Hours No.
		@return Break Hours Number
	  */
	public BigDecimal getBreakHoursNo () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BreakHoursNo);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Break Start Time.
		@param BreakStartTime 
		Start Time for Break
	  */
	public void setBreakStartTime (Timestamp BreakStartTime)
	{
		set_Value (COLUMNNAME_BreakStartTime, BreakStartTime);
	}

	/** Get Break Start Time.
		@return Start Time for Break
	  */
	public Timestamp getBreakStartTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_BreakStartTime);
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

	/** Set Shift Group.
		@param HR_ShiftGroup_ID 
		Shift Group
	  */
	public void setHR_ShiftGroup_ID (int HR_ShiftGroup_ID)
	{
		if (HR_ShiftGroup_ID < 1) 
			set_Value (COLUMNNAME_HR_ShiftGroup_ID, null);
		else 
			set_Value (COLUMNNAME_HR_ShiftGroup_ID, Integer.valueOf(HR_ShiftGroup_ID));
	}

	/** Get Shift Group.
		@return Shift Group
	  */
	public int getHR_ShiftGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_ShiftGroup_ID);
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

	/** Set Has fixed Workgroup?.
		@param IsHasFixedWorkgroup Has fixed Workgroup?	  */
	public void setIsHasFixedWorkgroup (boolean IsHasFixedWorkgroup)
	{
		set_Value (COLUMNNAME_IsHasFixedWorkgroup, Boolean.valueOf(IsHasFixedWorkgroup));
	}

	/** Get Has fixed Workgroup?.
		@return Has fixed Workgroup?	  */
	public boolean isHasFixedWorkgroup () 
	{
		Object oo = get_Value(COLUMNNAME_IsHasFixedWorkgroup);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Over Time Applicable.
		@param IsOverTimeApplicable 
		Is over time applicable to this Shift?
	  */
	public void setIsOverTimeApplicable (boolean IsOverTimeApplicable)
	{
		set_Value (COLUMNNAME_IsOverTimeApplicable, Boolean.valueOf(IsOverTimeApplicable));
	}

	/** Get Over Time Applicable.
		@return Is over time applicable to this Shift?
	  */
	public boolean isOverTimeApplicable () 
	{
		Object oo = get_Value(COLUMNNAME_IsOverTimeApplicable);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Variable Entrance.
		@param IsVariableEntrance 
		Variable Entrance
	  */
	public void setIsVariableEntrance (boolean IsVariableEntrance)
	{
		set_Value (COLUMNNAME_IsVariableEntrance, Boolean.valueOf(IsVariableEntrance));
	}

	/** Get Variable Entrance.
		@return Variable Entrance
	  */
	public boolean isVariableEntrance () 
	{
		Object oo = get_Value(COLUMNNAME_IsVariableEntrance);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Number of Hours.
		@param NoOfHours 
		Number of Hours This Shift Contains
	  */
	public void setNoOfHours (BigDecimal NoOfHours)
	{
		set_Value (COLUMNNAME_NoOfHours, NoOfHours);
	}

	/** Get Number of Hours.
		@return Number of Hours This Shift Contains
	  */
	public BigDecimal getNoOfHours () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NoOfHours);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Over Time Amount.
		@param OverTimeAmount 
		Is Over time Applicable  Then How Much Amount
	  */
	public void setOverTimeAmount (BigDecimal OverTimeAmount)
	{
		set_Value (COLUMNNAME_OverTimeAmount, OverTimeAmount);
	}

	/** Get Over Time Amount.
		@return Is Over time Applicable  Then How Much Amount
	  */
	public BigDecimal getOverTimeAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OverTimeAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Shift From Time.
		@param ShiftFromTime 
		Shift Starting Time
	  */
	public void setShiftFromTime (Timestamp ShiftFromTime)
	{
		set_Value (COLUMNNAME_ShiftFromTime, ShiftFromTime);
	}

	/** Get Shift From Time.
		@return Shift Starting Time
	  */
	public Timestamp getShiftFromTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ShiftFromTime);
	}

	/** Set Shift To Time.
		@param ShiftToTime 
		Shift Ending At Time
	  */
	public void setShiftToTime (Timestamp ShiftToTime)
	{
		set_Value (COLUMNNAME_ShiftToTime, ShiftToTime);
	}

	/** Get Shift To Time.
		@return Shift Ending At Time
	  */
	public Timestamp getShiftToTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ShiftToTime);
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