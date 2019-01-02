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

/** Generated Model for HR_WorkShift
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_WorkShift extends PO implements I_HR_WorkShift, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_WorkShift (Properties ctx, int HR_WorkShift_ID, String trxName)
    {
      super (ctx, HR_WorkShift_ID, trxName);
      /** if (HR_WorkShift_ID == 0)
        {
			setHR_WorkShift_ID (0);
			setIsHasFixedWorkgroup (false);
			setIsOverTimeApplicable (false);
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

	public org.eevolution.model.I_HR_ShiftGroup getHR_ShiftGroup() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_ShiftGroup)MTable.get(getCtx(), org.eevolution.model.I_HR_ShiftGroup.Table_Name)
			.getPO(getHR_ShiftGroup_ID(), get_TrxName());	}

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