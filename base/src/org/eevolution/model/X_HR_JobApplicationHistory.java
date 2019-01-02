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

/** Generated Model for HR_JobApplicationHistory
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_JobApplicationHistory extends PO implements I_HR_JobApplicationHistory, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_JobApplicationHistory (Properties ctx, int HR_JobApplicationHistory_ID, String trxName)
    {
      super (ctx, HR_JobApplicationHistory_ID, trxName);
      /** if (HR_JobApplicationHistory_ID == 0)
        {
			setHR_JobApplicationHistory_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_JobApplicationHistory (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_JobApplicationHistory[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Job Application History.
		@param HR_JobApplicationHistory_ID 
		Job Application History
	  */
	public void setHR_JobApplicationHistory_ID (int HR_JobApplicationHistory_ID)
	{
		if (HR_JobApplicationHistory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_JobApplicationHistory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_JobApplicationHistory_ID, Integer.valueOf(HR_JobApplicationHistory_ID));
	}

	/** Get Job Application History.
		@return Job Application History
	  */
	public int getHR_JobApplicationHistory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_JobApplicationHistory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_JobApplication getHR_JobApplication() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_JobApplication)MTable.get(getCtx(), org.eevolution.model.I_HR_JobApplication.Table_Name)
			.getPO(getHR_JobApplication_ID(), get_TrxName());	}

	/** Set Job Application.
		@param HR_JobApplication_ID 
		Job Application
	  */
	public void setHR_JobApplication_ID (int HR_JobApplication_ID)
	{
		if (HR_JobApplication_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_JobApplication_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_JobApplication_ID, Integer.valueOf(HR_JobApplication_ID));
	}

	/** Get Job Application.
		@return Job Application
	  */
	public int getHR_JobApplication_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_JobApplication_ID);
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