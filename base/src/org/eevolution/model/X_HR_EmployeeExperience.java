/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_EmployeeExperience
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0RC - $Id$ */
public class X_HR_EmployeeExperience extends PO implements I_HR_EmployeeExperience, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20131208L;

    /** Standard Constructor */
    public X_HR_EmployeeExperience (Properties ctx, int HR_EmployeeExperience_ID, String trxName)
    {
      super (ctx, HR_EmployeeExperience_ID, trxName);
      /** if (HR_EmployeeExperience_ID == 0)
        {
			setCompany (null);
			setHR_EmployeeExperience_ID (0);
			setHR_Employee_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_EmployeeExperience (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_EmployeeExperience[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** Set Company.
		@param Company 
		Previous working Company Name(Organization)
	  */
	public void setCompany (String Company)
	{
		set_Value (COLUMNNAME_Company, Company);
	}

	/** Get Company.
		@return Previous working Company Name(Organization)
	  */
	public String getCompany () 
	{
		return (String)get_Value(COLUMNNAME_Company);
	}

	/** Set Date From.
		@param DateFrom 
		Starting date for a range
	  */
	public void setDateFrom (Timestamp DateFrom)
	{
		set_Value (COLUMNNAME_DateFrom, DateFrom);
	}

	/** Get Date From.
		@return Starting date for a range
	  */
	public Timestamp getDateFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateFrom);
	}

	/** Set Date To.
		@param DateTo 
		End date of a date range
	  */
	public void setDateTo (Timestamp DateTo)
	{
		set_Value (COLUMNNAME_DateTo, DateTo);
	}

	/** Get Date To.
		@return End date of a date range
	  */
	public Timestamp getDateTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTo);
	}

	/** Set Designation (When Left).
		@param DesignationWhenLeft 
		Designation (When Left)
	  */
	public void setDesignationWhenLeft (String DesignationWhenLeft)
	{
		set_Value (COLUMNNAME_DesignationWhenLeft, DesignationWhenLeft);
	}

	/** Get Designation (When Left).
		@return Designation (When Left)
	  */
	public String getDesignationWhenLeft () 
	{
		return (String)get_Value(COLUMNNAME_DesignationWhenLeft);
	}

	/** Set Designation (When Joined).
		@param DesignationWhereJoined 
		Designation (When Joined)
	  */
	public void setDesignationWhereJoined (String DesignationWhereJoined)
	{
		set_Value (COLUMNNAME_DesignationWhereJoined, DesignationWhereJoined);
	}

	/** Get Designation (When Joined).
		@return Designation (When Joined)
	  */
	public String getDesignationWhereJoined () 
	{
		return (String)get_Value(COLUMNNAME_DesignationWhereJoined);
	}

	/** Set Work Experience.
		@param HR_EmployeeExperience_ID 
		Work Experience
	  */
	public void setHR_EmployeeExperience_ID (int HR_EmployeeExperience_ID)
	{
		if (HR_EmployeeExperience_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_EmployeeExperience_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_EmployeeExperience_ID, Integer.valueOf(HR_EmployeeExperience_ID));
	}

	/** Get Work Experience.
		@return Work Experience
	  */
	public int getHR_EmployeeExperience_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EmployeeExperience_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Employee getHR_Employee() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Employee)MTable.get(getCtx(), org.eevolution.model.I_HR_Employee.Table_Name)
			.getPO(getHR_Employee_ID(), get_TrxName());	}

	/** Set Payroll Employee.
		@param HR_Employee_ID Payroll Employee	  */
	public void setHR_Employee_ID (int HR_Employee_ID)
	{
		if (HR_Employee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Employee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Employee_ID, Integer.valueOf(HR_Employee_ID));
	}

	/** Get Payroll Employee.
		@return Payroll Employee	  */
	public int getHR_Employee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Employee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}