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
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_EmployeeDependent
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_HR_EmployeeDependent extends PO implements I_HR_EmployeeDependent, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150223L;

    /** Standard Constructor */
    public X_HR_EmployeeDependent (Properties ctx, int HR_EmployeeDependent_ID, String trxName)
    {
      super (ctx, HR_EmployeeDependent_ID, trxName);
      /** if (HR_EmployeeDependent_ID == 0)
        {
			setBirthday (new Timestamp( System.currentTimeMillis() ));
// @Date@
			setHR_EmployeeDependent_ID (0);
			setHR_Employee_ID (0);
			setHR_Relationship_ID (0);
			setIsCollectCash (false);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_EmployeeDependent (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_EmployeeDependent[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Age.
		@param Age 
		Age of a person
	  */
	public void setAge (String Age)
	{
		set_Value (COLUMNNAME_Age, Age);
	}

	/** Get Age.
		@return Age of a person
	  */
	public String getAge () 
	{
		return (String)get_Value(COLUMNNAME_Age);
	}

	/** Set Birthday.
		@param Birthday 
		Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday)
	{
		set_Value (COLUMNNAME_Birthday, Birthday);
	}

	/** Get Birthday.
		@return Birthday or Anniversary day
	  */
	public Timestamp getBirthday () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Birthday);
	}

	/** Set Employee Dependents.
		@param HR_EmployeeDependent_ID 
		Employee Dependents
	  */
	public void setHR_EmployeeDependent_ID (int HR_EmployeeDependent_ID)
	{
		if (HR_EmployeeDependent_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_EmployeeDependent_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_EmployeeDependent_ID, Integer.valueOf(HR_EmployeeDependent_ID));
	}

	/** Get Employee Dependents.
		@return Employee Dependents
	  */
	public int getHR_EmployeeDependent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EmployeeDependent_ID);
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

	public org.eevolution.model.I_HR_Relationship getHR_Relationship() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Relationship)MTable.get(getCtx(), org.eevolution.model.I_HR_Relationship.Table_Name)
			.getPO(getHR_Relationship_ID(), get_TrxName());	}

	/** Set Employee Relationship.
		@param HR_Relationship_ID 
		Employee Relationship Identifies an employee relations
	  */
	public void setHR_Relationship_ID (int HR_Relationship_ID)
	{
		if (HR_Relationship_ID < 1) 
			set_Value (COLUMNNAME_HR_Relationship_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Relationship_ID, Integer.valueOf(HR_Relationship_ID));
	}

	/** Get Employee Relationship.
		@return Employee Relationship Identifies an employee relations
	  */
	public int getHR_Relationship_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Relationship_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Collect Cash?.
		@param IsCollectCash 
		Is Collect Cash?
	  */
	public void setIsCollectCash (boolean IsCollectCash)
	{
		set_Value (COLUMNNAME_IsCollectCash, Boolean.valueOf(IsCollectCash));
	}

	/** Get Is Collect Cash?.
		@return Is Collect Cash?
	  */
	public boolean isCollectCash () 
	{
		Object oo = get_Value(COLUMNNAME_IsCollectCash);
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
}