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
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_LeaveType
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_LeaveType extends PO implements I_HR_LeaveType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_LeaveType (Properties ctx, int HR_LeaveType_ID, String trxName)
    {
      super (ctx, HR_LeaveType_ID, trxName);
      /** if (HR_LeaveType_ID == 0)
        {
			setHR_LeaveType_ID (0);
			setLeaveCreditTimeType (null);
			setMinAttendanceRequire (Env.ZERO);
			setName (null);
			setNoOfLeavesAllocated (Env.ZERO);
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

	/** Set Consider Adjacent Holidays As Leave?.
		@param IsAdjacentHolidayAsLeave 
		Consider Adjacent Holidays As Leave?
	  */
	public void setIsAdjacentHolidayAsLeave (boolean IsAdjacentHolidayAsLeave)
	{
		set_Value (COLUMNNAME_IsAdjacentHolidayAsLeave, Boolean.valueOf(IsAdjacentHolidayAsLeave));
	}

	/** Get Consider Adjacent Holidays As Leave?.
		@return Consider Adjacent Holidays As Leave?
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

	/** Set Allowed En Cashment?.
		@param IsAllowedEnCashment 
		Allowed En Cashment? 
	  */
	public void setIsAllowedEnCashment (boolean IsAllowedEnCashment)
	{
		set_Value (COLUMNNAME_IsAllowedEnCashment, Boolean.valueOf(IsAllowedEnCashment));
	}

	/** Get Allowed En Cashment?.
		@return Allowed En Cashment? 
	  */
	public boolean isAllowedEnCashment () 
	{
		Object oo = get_Value(COLUMNNAME_IsAllowedEnCashment);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Half Day Leave Allowed ?.
		@param IsHalfDayLeaveAllowed 
		Half Day Leave Allowed ?
	  */
	public void setIsHalfDayLeaveAllowed (boolean IsHalfDayLeaveAllowed)
	{
		set_Value (COLUMNNAME_IsHalfDayLeaveAllowed, Boolean.valueOf(IsHalfDayLeaveAllowed));
	}

	/** Get Half Day Leave Allowed ?.
		@return Half Day Leave Allowed ?
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

	/** Set Is Leave Repeated?.
		@param IsLeaveRepeated 
		Is Leave Repeated?
	  */
	public void setIsLeaveRepeated (boolean IsLeaveRepeated)
	{
		set_Value (COLUMNNAME_IsLeaveRepeated, Boolean.valueOf(IsLeaveRepeated));
	}

	/** Get Is Leave Repeated?.
		@return Is Leave Repeated?
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

	/** Set Is Provident Fund Employee?.
		@param IsProvidentFund 
		Is Provident Fund Employee?
	  */
	public void setIsProvidentFund (boolean IsProvidentFund)
	{
		set_Value (COLUMNNAME_IsProvidentFund, Boolean.valueOf(IsProvidentFund));
	}

	/** Get Is Provident Fund Employee?.
		@return Is Provident Fund Employee?
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

	/** Set Maximum continous leaves.
		@param MaxContinousLeaves 
		Maximum continous leaves
	  */
	public void setMaxContinousLeaves (BigDecimal MaxContinousLeaves)
	{
		set_Value (COLUMNNAME_MaxContinousLeaves, MaxContinousLeaves);
	}

	/** Get Maximum continous leaves.
		@return Maximum continous leaves
	  */
	public BigDecimal getMaxContinousLeaves () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxContinousLeaves);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Maximum leaves carry forwarded.
		@param MaxLeavesToForward 
		Maximum leaves carry forwarded
	  */
	public void setMaxLeavesToForward (BigDecimal MaxLeavesToForward)
	{
		set_Value (COLUMNNAME_MaxLeavesToForward, MaxLeavesToForward);
	}

	/** Get Maximum leaves carry forwarded.
		@return Maximum leaves carry forwarded
	  */
	public BigDecimal getMaxLeavesToForward () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxLeavesToForward);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Minimum Attendance Require.
		@param MinAttendanceRequire 
		Minimum Attendance Require to elgibile leave
	  */
	public void setMinAttendanceRequire (BigDecimal MinAttendanceRequire)
	{
		set_Value (COLUMNNAME_MinAttendanceRequire, MinAttendanceRequire);
	}

	/** Get Minimum Attendance Require.
		@return Minimum Attendance Require to elgibile leave
	  */
	public BigDecimal getMinAttendanceRequire () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MinAttendanceRequire);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Minimum Encashment Days in a Request.
		@param MinForencashmentDays 
		Minimum Encashment Days in a Request
	  */
	public void setMinForencashmentDays (BigDecimal MinForencashmentDays)
	{
		set_Value (COLUMNNAME_MinForencashmentDays, MinForencashmentDays);
	}

	/** Get Minimum Encashment Days in a Request.
		@return Minimum Encashment Days in a Request
	  */
	public BigDecimal getMinForencashmentDays () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MinForencashmentDays);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
	public void setNoOfLeavesAllocated (BigDecimal NoOfLeavesAllocated)
	{
		set_Value (COLUMNNAME_NoOfLeavesAllocated, NoOfLeavesAllocated);
	}

	/** Get Number of Leaves Allocated.
		@return Number of Leaves Allocated
	  */
	public BigDecimal getNoOfLeavesAllocated () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NoOfLeavesAllocated);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** RepeatedType AD_Reference_ID=53607 */
	public static final int REPEATEDTYPE_AD_Reference_ID=53607;
	/** Monthly = Monthly */
	public static final String REPEATEDTYPE_Monthly = "Monthly";
	/** Quarterly = Quarterly */
	public static final String REPEATEDTYPE_Quarterly = "Quarterly";
	/** Yearly = Yearly */
	public static final String REPEATEDTYPE_Yearly = "Yearly";
	/** Set Repeated type.
		@param RepeatedType 
		Repeated type
	  */
	public void setRepeatedType (String RepeatedType)
	{

		set_Value (COLUMNNAME_RepeatedType, RepeatedType);
	}

	/** Get Repeated type.
		@return Repeated type
	  */
	public String getRepeatedType () 
	{
		return (String)get_Value(COLUMNNAME_RepeatedType);
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