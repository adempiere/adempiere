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

/** Generated Model for HR_LeaveReason
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_LeaveReason extends PO implements I_HR_LeaveReason, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_LeaveReason (Properties ctx, int HR_LeaveReason_ID, String trxName)
    {
      super (ctx, HR_LeaveReason_ID, trxName);
      /** if (HR_LeaveReason_ID == 0)
        {
			setHR_LeaveReason_ID (0);
			setLeaveReasonType (null);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_LeaveReason (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_LeaveReason[")
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

	/** Set Leave Reason.
		@param HR_LeaveReason_ID 
		Predefiend reasons
	  */
	public void setHR_LeaveReason_ID (int HR_LeaveReason_ID)
	{
		if (HR_LeaveReason_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_LeaveReason_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_LeaveReason_ID, Integer.valueOf(HR_LeaveReason_ID));
	}

	/** Get Leave Reason.
		@return Predefiend reasons
	  */
	public int getHR_LeaveReason_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_LeaveReason_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** LeaveReasonType AD_Reference_ID=53610 */
	public static final int LEAVEREASONTYPE_AD_Reference_ID=53610;
	/** Leave request disapproval = LRDR */
	public static final String LEAVEREASONTYPE_LeaveRequestDisapproval = "LRDR";
	/** Leave Request = LRRT */
	public static final String LEAVEREASONTYPE_LeaveRequest = "LRRT";
	/** Other Reason = ORRT */
	public static final String LEAVEREASONTYPE_OtherReason = "ORRT";
	/** Set Leave Reason Type.
		@param LeaveReasonType 
		Leave Reason Type
	  */
	public void setLeaveReasonType (String LeaveReasonType)
	{

		set_Value (COLUMNNAME_LeaveReasonType, LeaveReasonType);
	}

	/** Get Leave Reason Type.
		@return Leave Reason Type
	  */
	public String getLeaveReasonType () 
	{
		return (String)get_Value(COLUMNNAME_LeaveReasonType);
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