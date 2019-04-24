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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_LeaveTypeCombination
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_LeaveTypeCombination extends PO implements I_HR_LeaveTypeCombination, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_LeaveTypeCombination (Properties ctx, int HR_LeaveTypeCombination_ID, String trxName)
    {
      super (ctx, HR_LeaveTypeCombination_ID, trxName);
      /** if (HR_LeaveTypeCombination_ID == 0)
        {
			setAllowedLeaveType_ID (0);
			setHR_LeaveTypeCombination_ID (0);
			setHR_LeaveType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_LeaveTypeCombination (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_LeaveTypeCombination[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.eevolution.model.I_HR_LeaveType getAllowedLeaveType() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_LeaveType)MTable.get(getCtx(), org.eevolution.model.I_HR_LeaveType.Table_Name)
			.getPO(getAllowedLeaveType_ID(), get_TrxName());	}

	/** Set Allow  Leave with.
		@param AllowedLeaveType_ID 
		Allow  Leave with
	  */
	public void setAllowedLeaveType_ID (int AllowedLeaveType_ID)
	{
		if (AllowedLeaveType_ID < 1) 
			set_Value (COLUMNNAME_AllowedLeaveType_ID, null);
		else 
			set_Value (COLUMNNAME_AllowedLeaveType_ID, Integer.valueOf(AllowedLeaveType_ID));
	}

	/** Get Allow  Leave with.
		@return Allow  Leave with
	  */
	public int getAllowedLeaveType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AllowedLeaveType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Leave Type Combination.
		@param HR_LeaveTypeCombination_ID 
		Leave Type Combination
	  */
	public void setHR_LeaveTypeCombination_ID (int HR_LeaveTypeCombination_ID)
	{
		if (HR_LeaveTypeCombination_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_LeaveTypeCombination_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_LeaveTypeCombination_ID, Integer.valueOf(HR_LeaveTypeCombination_ID));
	}

	/** Get Leave Type Combination.
		@return Leave Type Combination
	  */
	public int getHR_LeaveTypeCombination_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_LeaveTypeCombination_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_LeaveType getHR_LeaveType() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_LeaveType)MTable.get(getCtx(), org.eevolution.model.I_HR_LeaveType.Table_Name)
			.getPO(getHR_LeaveType_ID(), get_TrxName());	}

	/** Set Leave Type.
		@param HR_LeaveType_ID 
		Leave Type for an Employee
	  */
	public void setHR_LeaveType_ID (int HR_LeaveType_ID)
	{
		if (HR_LeaveType_ID < 1) 
			set_Value (COLUMNNAME_HR_LeaveType_ID, null);
		else 
			set_Value (COLUMNNAME_HR_LeaveType_ID, Integer.valueOf(HR_LeaveType_ID));
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getHR_LeaveType_ID()));
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