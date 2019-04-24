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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_JobOpeningHistory
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_JobOpeningHistory extends PO implements I_HR_JobOpeningHistory, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_JobOpeningHistory (Properties ctx, int HR_JobOpeningHistory_ID, String trxName)
    {
      super (ctx, HR_JobOpeningHistory_ID, trxName);
      /** if (HR_JobOpeningHistory_ID == 0)
        {
			setHR_JobOpeningHistory_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_JobOpeningHistory (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_JobOpeningHistory[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Current Status.
		@param CurrentStatus 
		Indicates the current status
	  */
	public void setCurrentStatus (String CurrentStatus)
	{
		set_Value (COLUMNNAME_CurrentStatus, CurrentStatus);
	}

	/** Get Current Status.
		@return Indicates the current status
	  */
	public String getCurrentStatus () 
	{
		return (String)get_Value(COLUMNNAME_CurrentStatus);
	}

	/** Set Transaction Date.
		@param DateTrx 
		Transaction Date
	  */
	public void setDateTrx (Timestamp DateTrx)
	{
		set_Value (COLUMNNAME_DateTrx, DateTrx);
	}

	/** Get Transaction Date.
		@return Transaction Date
	  */
	public Timestamp getDateTrx () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTrx);
	}

	/** Set Job Opening History.
		@param HR_JobOpeningHistory_ID Job Opening History	  */
	public void setHR_JobOpeningHistory_ID (int HR_JobOpeningHistory_ID)
	{
		if (HR_JobOpeningHistory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_JobOpeningHistory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_JobOpeningHistory_ID, Integer.valueOf(HR_JobOpeningHistory_ID));
	}

	/** Get Job Opening History.
		@return Job Opening History	  */
	public int getHR_JobOpeningHistory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_JobOpeningHistory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_JobOpening getHR_JobOpening() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_JobOpening)MTable.get(getCtx(), org.eevolution.model.I_HR_JobOpening.Table_Name)
			.getPO(getHR_JobOpening_ID(), get_TrxName());	}

	/** Set Job Openings .
		@param HR_JobOpening_ID 
		Job Openings for Recruitment Management
	  */
	public void setHR_JobOpening_ID (int HR_JobOpening_ID)
	{
		if (HR_JobOpening_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_JobOpening_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_JobOpening_ID, Integer.valueOf(HR_JobOpening_ID));
	}

	/** Get Job Openings .
		@return Job Openings for Recruitment Management
	  */
	public int getHR_JobOpening_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_JobOpening_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Remarks.
		@param Remarks 
		Remarks
	  */
	public void setRemarks (String Remarks)
	{
		set_Value (COLUMNNAME_Remarks, Remarks);
	}

	/** Get Remarks.
		@return Remarks
	  */
	public String getRemarks () 
	{
		return (String)get_Value(COLUMNNAME_Remarks);
	}

	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{
		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
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