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
package org.spin.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_ShiftSchedule
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_HR_ShiftSchedule extends PO implements I_HR_ShiftSchedule, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_HR_ShiftSchedule (Properties ctx, int HR_ShiftSchedule_ID, String trxName)
    {
      super (ctx, HR_ShiftSchedule_ID, trxName);
      /** if (HR_ShiftSchedule_ID == 0)
        {
			setHR_Period_ID (0);
			setHR_ShiftSchedule_ID (0);
			setHR_WorkGroup_ID (0);
			setHR_WorkShift_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_ShiftSchedule (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_ShiftSchedule[")
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getDescription()));
    }

	public org.eevolution.model.I_HR_Period getHR_Period() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Period)MTable.get(getCtx(), org.eevolution.model.I_HR_Period.Table_Name)
			.getPO(getHR_Period_ID(), get_TrxName());	}

	/** Set Payroll Period.
		@param HR_Period_ID Payroll Period	  */
	public void setHR_Period_ID (int HR_Period_ID)
	{
		if (HR_Period_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Period_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Period_ID, Integer.valueOf(HR_Period_ID));
	}

	/** Get Payroll Period.
		@return Payroll Period	  */
	public int getHR_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Shift Schedule.
		@param HR_ShiftSchedule_ID 
		Shift Schedule
	  */
	public void setHR_ShiftSchedule_ID (int HR_ShiftSchedule_ID)
	{
		if (HR_ShiftSchedule_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_ShiftSchedule_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_ShiftSchedule_ID, Integer.valueOf(HR_ShiftSchedule_ID));
	}

	/** Get Shift Schedule.
		@return Shift Schedule
	  */
	public int getHR_ShiftSchedule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_ShiftSchedule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_WorkGroup getHR_WorkGroup() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_WorkGroup)MTable.get(getCtx(), org.eevolution.model.I_HR_WorkGroup.Table_Name)
			.getPO(getHR_WorkGroup_ID(), get_TrxName());	}

	/** Set Work Group.
		@param HR_WorkGroup_ID 
		Work Group
	  */
	public void setHR_WorkGroup_ID (int HR_WorkGroup_ID)
	{
		if (HR_WorkGroup_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_WorkGroup_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_WorkGroup_ID, Integer.valueOf(HR_WorkGroup_ID));
	}

	/** Get Work Group.
		@return Work Group
	  */
	public int getHR_WorkGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_WorkGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_WorkShift getHR_WorkShift() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_WorkShift)MTable.get(getCtx(), org.eevolution.model.I_HR_WorkShift.Table_Name)
			.getPO(getHR_WorkShift_ID(), get_TrxName());	}

	/** Set Work Shift.
		@param HR_WorkShift_ID 
		Work Shift
	  */
	public void setHR_WorkShift_ID (int HR_WorkShift_ID)
	{
		if (HR_WorkShift_ID < 1) 
			set_Value (COLUMNNAME_HR_WorkShift_ID, null);
		else 
			set_Value (COLUMNNAME_HR_WorkShift_ID, Integer.valueOf(HR_WorkShift_ID));
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