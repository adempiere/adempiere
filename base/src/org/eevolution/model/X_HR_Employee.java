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

/** Generated Model for HR_Employee
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_Employee extends PO implements I_HR_Employee, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_Employee (Properties ctx, int HR_Employee_ID, String trxName)
    {
      super (ctx, HR_Employee_ID, trxName);
      /** if (HR_Employee_ID == 0)
        {
			setC_BPartner_ID (0);
			setHR_Department_ID (0);
			setHR_Employee_ID (0);
			setHR_Job_ID (0);
			setStartDate (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_HR_Employee (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_Employee[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Org getAD_OrgTrx() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Org)MTable.get(getCtx(), org.compiere.model.I_AD_Org.Table_Name)
			.getPO(getAD_OrgTrx_ID(), get_TrxName());	}

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException
    {
		return (org.compiere.model.I_C_Activity)MTable.get(getCtx(), org.compiere.model.I_C_Activity.Table_Name)
			.getPO(getC_Activity_ID(), get_TrxName());	}

	/** Set Activity.
		@param C_Activity_ID 
		Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID)
	{
		if (C_Activity_ID < 1) 
			set_Value (COLUMNNAME_C_Activity_ID, null);
		else 
			set_Value (COLUMNNAME_C_Activity_ID, Integer.valueOf(C_Activity_ID));
	}

	/** Get Activity.
		@return Business Activity
	  */
	public int getC_Activity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Activity_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Campaign getC_Campaign() throws RuntimeException
    {
		return (org.compiere.model.I_C_Campaign)MTable.get(getCtx(), org.compiere.model.I_C_Campaign.Table_Name)
			.getPO(getC_Campaign_ID(), get_TrxName());	}

	/** Set Campaign.
		@param C_Campaign_ID 
		Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID)
	{
		if (C_Campaign_ID < 1) 
			set_Value (COLUMNNAME_C_Campaign_ID, null);
		else 
			set_Value (COLUMNNAME_C_Campaign_ID, Integer.valueOf(C_Campaign_ID));
	}

	/** Get Campaign.
		@return Marketing Campaign
	  */
	public int getC_Campaign_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Campaign_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException
    {
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_Name)
			.getPO(getC_Project_ID(), get_TrxName());	}

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_Value (COLUMNNAME_C_Project_ID, null);
		else 
			set_Value (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_SalesRegion getC_SalesRegion() throws RuntimeException
    {
		return (org.compiere.model.I_C_SalesRegion)MTable.get(getCtx(), org.compiere.model.I_C_SalesRegion.Table_Name)
			.getPO(getC_SalesRegion_ID(), get_TrxName());	}

	/** Set Sales Region.
		@param C_SalesRegion_ID 
		Sales coverage region
	  */
	public void setC_SalesRegion_ID (int C_SalesRegion_ID)
	{
		if (C_SalesRegion_ID < 1) 
			set_Value (COLUMNNAME_C_SalesRegion_ID, null);
		else 
			set_Value (COLUMNNAME_C_SalesRegion_ID, Integer.valueOf(C_SalesRegion_ID));
	}

	/** Get Sales Region.
		@return Sales coverage region
	  */
	public int getC_SalesRegion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_SalesRegion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Validation code.
		@param Code 
		Validation Code
	  */
	public void setCode (String Code)
	{
		set_Value (COLUMNNAME_Code, Code);
	}

	/** Get Validation code.
		@return Validation Code
	  */
	public String getCode () 
	{
		return (String)get_Value(COLUMNNAME_Code);
	}

	/** Set Daily Salary.
		@param DailySalary 
		Daily Salary
	  */
	public void setDailySalary (BigDecimal DailySalary)
	{
		set_Value (COLUMNNAME_DailySalary, DailySalary);
	}

	/** Get Daily Salary.
		@return Daily Salary
	  */
	public BigDecimal getDailySalary () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DailySalary);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Employee Image.
		@param EmployeeImage_ID 
		Employee Image
	  */
	public void setEmployeeImage_ID (int EmployeeImage_ID)
	{
		if (EmployeeImage_ID < 1) 
			set_Value (COLUMNNAME_EmployeeImage_ID, null);
		else 
			set_Value (COLUMNNAME_EmployeeImage_ID, Integer.valueOf(EmployeeImage_ID));
	}

	/** Get Employee Image.
		@return Employee Image
	  */
	public int getEmployeeImage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EmployeeImage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** EmployeeStatus AD_Reference_ID=53617 */
	public static final int EMPLOYEESTATUS_AD_Reference_ID=53617;
	/** Without Reason = 00 */
	public static final String EMPLOYEESTATUS_WithoutReason = "00";
	/** On Leave = 01 */
	public static final String EMPLOYEESTATUS_OnLeave = "01";
	/** Left Service = 02 */
	public static final String EMPLOYEESTATUS_LeftService = "02";
	/** Retired = 03 */
	public static final String EMPLOYEESTATUS_Retired = "03";
	/** Expired = 05 */
	public static final String EMPLOYEESTATUS_Expired = "05";
	/** Non Implemented Area = 06 */
	public static final String EMPLOYEESTATUS_NonImplementedArea = "06";
	/** Compliance by Immediate Ex = 07 */
	public static final String EMPLOYEESTATUS_ComplianceByImmediateEx = "07";
	/** Suspension of work = 08 */
	public static final String EMPLOYEESTATUS_SuspensionOfWork = "08";
	/** Strike/Lockout = 09 */
	public static final String EMPLOYEESTATUS_StrikeLockout = "09";
	/** Retrenchment = 10 */
	public static final String EMPLOYEESTATUS_Retrenchment = "10";
	/** No Work = 11 */
	public static final String EMPLOYEESTATUS_NoWork = "11";
	/** Doesnt Belong To This Employee = 12 */
	public static final String EMPLOYEESTATUS_DoesntBelongToThisEmployee = "12";
	/** Active = 13 */
	public static final String EMPLOYEESTATUS_Active = "13";
	/** Out of Coverage = OC */
	public static final String EMPLOYEESTATUS_OutOfCoverage = "OC";
	/** Set Employee Status.
		@param EmployeeStatus Employee Status	  */
	public void setEmployeeStatus (String EmployeeStatus)
	{

		set_Value (COLUMNNAME_EmployeeStatus, EmployeeStatus);
	}

	/** Get Employee Status.
		@return Employee Status	  */
	public String getEmployeeStatus () 
	{
		return (String)get_Value(COLUMNNAME_EmployeeStatus);
	}

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	public org.eevolution.model.I_HR_CareerLevel getHR_CareerLevel() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_CareerLevel)MTable.get(getCtx(), org.eevolution.model.I_HR_CareerLevel.Table_Name)
			.getPO(getHR_CareerLevel_ID(), get_TrxName());	}

	/** Set Career Level.
		@param HR_CareerLevel_ID 
		The Career Level for this position
	  */
	public void setHR_CareerLevel_ID (int HR_CareerLevel_ID)
	{
		if (HR_CareerLevel_ID < 1) 
			set_Value (COLUMNNAME_HR_CareerLevel_ID, null);
		else 
			set_Value (COLUMNNAME_HR_CareerLevel_ID, Integer.valueOf(HR_CareerLevel_ID));
	}

	/** Get Career Level.
		@return The Career Level for this position
	  */
	public int getHR_CareerLevel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_CareerLevel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Degree getHR_Degree() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Degree)MTable.get(getCtx(), org.eevolution.model.I_HR_Degree.Table_Name)
			.getPO(getHR_Degree_ID(), get_TrxName());	}

	/** Set Degree.
		@param HR_Degree_ID 
		Degree for an Employee
	  */
	public void setHR_Degree_ID (int HR_Degree_ID)
	{
		if (HR_Degree_ID < 1) 
			set_Value (COLUMNNAME_HR_Degree_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Degree_ID, Integer.valueOf(HR_Degree_ID));
	}

	/** Get Degree.
		@return Degree for an Employee
	  */
	public int getHR_Degree_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Degree_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Department)MTable.get(getCtx(), org.eevolution.model.I_HR_Department.Table_Name)
			.getPO(getHR_Department_ID(), get_TrxName());	}

	/** Set Payroll Department.
		@param HR_Department_ID Payroll Department	  */
	public void setHR_Department_ID (int HR_Department_ID)
	{
		if (HR_Department_ID < 1) 
			set_Value (COLUMNNAME_HR_Department_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Department_ID, Integer.valueOf(HR_Department_ID));
	}

	/** Get Payroll Department.
		@return Payroll Department	  */
	public int getHR_Department_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Department_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Designation getHR_Designation() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Designation)MTable.get(getCtx(), org.eevolution.model.I_HR_Designation.Table_Name)
			.getPO(getHR_Designation_ID(), get_TrxName());	}

	/** Set Designation.
		@param HR_Designation_ID 
		Designation is a nationally recognized level
	  */
	public void setHR_Designation_ID (int HR_Designation_ID)
	{
		if (HR_Designation_ID < 1) 
			set_Value (COLUMNNAME_HR_Designation_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Designation_ID, Integer.valueOf(HR_Designation_ID));
	}

	/** Get Designation.
		@return Designation is a nationally recognized level
	  */
	public int getHR_Designation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Designation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_EmployeeType getHR_EmployeeType() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_EmployeeType)MTable.get(getCtx(), org.eevolution.model.I_HR_EmployeeType.Table_Name)
			.getPO(getHR_EmployeeType_ID(), get_TrxName());	}

	/** Set Employee Type.
		@param HR_EmployeeType_ID 
		Employee Type
	  */
	public void setHR_EmployeeType_ID (int HR_EmployeeType_ID)
	{
		if (HR_EmployeeType_ID < 1) 
			set_Value (COLUMNNAME_HR_EmployeeType_ID, null);
		else 
			set_Value (COLUMNNAME_HR_EmployeeType_ID, Integer.valueOf(HR_EmployeeType_ID));
	}

	/** Get Employee Type.
		@return Employee Type
	  */
	public int getHR_EmployeeType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_EmployeeType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

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

	public org.eevolution.model.I_HR_Grade getHR_Grade() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Grade)MTable.get(getCtx(), org.eevolution.model.I_HR_Grade.Table_Name)
			.getPO(getHR_Grade_ID(), get_TrxName());	}

	/** Set Grade.
		@param HR_Grade_ID 
		Grade
	  */
	public void setHR_Grade_ID (int HR_Grade_ID)
	{
		if (HR_Grade_ID < 1) 
			set_Value (COLUMNNAME_HR_Grade_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Grade_ID, Integer.valueOf(HR_Grade_ID));
	}

	/** Get Grade.
		@return Grade
	  */
	public int getHR_Grade_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Grade_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_JobEducation getHR_JobEducation() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_JobEducation)MTable.get(getCtx(), org.eevolution.model.I_HR_JobEducation.Table_Name)
			.getPO(getHR_JobEducation_ID(), get_TrxName());	}

	/** Set Job Education.
		@param HR_JobEducation_ID 
		The Job Education for this position
	  */
	public void setHR_JobEducation_ID (int HR_JobEducation_ID)
	{
		if (HR_JobEducation_ID < 1) 
			set_Value (COLUMNNAME_HR_JobEducation_ID, null);
		else 
			set_Value (COLUMNNAME_HR_JobEducation_ID, Integer.valueOf(HR_JobEducation_ID));
	}

	/** Get Job Education.
		@return The Job Education for this position
	  */
	public int getHR_JobEducation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_JobEducation_ID);
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
			set_Value (COLUMNNAME_HR_JobOpening_ID, null);
		else 
			set_Value (COLUMNNAME_HR_JobOpening_ID, Integer.valueOf(HR_JobOpening_ID));
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

	public org.eevolution.model.I_HR_JobType getHR_JobType() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_JobType)MTable.get(getCtx(), org.eevolution.model.I_HR_JobType.Table_Name)
			.getPO(getHR_JobType_ID(), get_TrxName());	}

	/** Set Job Type.
		@param HR_JobType_ID 
		The Job Type for a Job Openings
	  */
	public void setHR_JobType_ID (int HR_JobType_ID)
	{
		if (HR_JobType_ID < 1) 
			set_Value (COLUMNNAME_HR_JobType_ID, null);
		else 
			set_Value (COLUMNNAME_HR_JobType_ID, Integer.valueOf(HR_JobType_ID));
	}

	/** Get Job Type.
		@return The Job Type for a Job Openings
	  */
	public int getHR_JobType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_JobType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Job getHR_Job() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_Name)
			.getPO(getHR_Job_ID(), get_TrxName());	}

	/** Set Payroll Job.
		@param HR_Job_ID Payroll Job	  */
	public void setHR_Job_ID (int HR_Job_ID)
	{
		if (HR_Job_ID < 1) 
			set_Value (COLUMNNAME_HR_Job_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Job_ID, Integer.valueOf(HR_Job_ID));
	}

	/** Get Payroll Job.
		@return Payroll Job	  */
	public int getHR_Job_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Job_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Payroll getHR_Payroll() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Payroll)MTable.get(getCtx(), org.eevolution.model.I_HR_Payroll.Table_Name)
			.getPO(getHR_Payroll_ID(), get_TrxName());	}

	/** Set Payroll.
		@param HR_Payroll_ID Payroll	  */
	public void setHR_Payroll_ID (int HR_Payroll_ID)
	{
		if (HR_Payroll_ID < 1) 
			set_Value (COLUMNNAME_HR_Payroll_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Payroll_ID, Integer.valueOf(HR_Payroll_ID));
	}

	/** Get Payroll.
		@return Payroll	  */
	public int getHR_Payroll_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Payroll_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Race getHR_Race() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Race)MTable.get(getCtx(), org.eevolution.model.I_HR_Race.Table_Name)
			.getPO(getHR_Race_ID(), get_TrxName());	}

	/** Set Race.
		@param HR_Race_ID 
		Race
	  */
	public void setHR_Race_ID (int HR_Race_ID)
	{
		if (HR_Race_ID < 1) 
			set_Value (COLUMNNAME_HR_Race_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Race_ID, Integer.valueOf(HR_Race_ID));
	}

	/** Get Race.
		@return Race
	  */
	public int getHR_Race_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Race_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_SalaryRange getHR_SalaryRange() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_SalaryRange)MTable.get(getCtx(), org.eevolution.model.I_HR_SalaryRange.Table_Name)
			.getPO(getHR_SalaryRange_ID(), get_TrxName());	}

	/** Set Salary Range.
		@param HR_SalaryRange_ID 
		The Salary Rage is use in Job Openings
	  */
	public void setHR_SalaryRange_ID (int HR_SalaryRange_ID)
	{
		if (HR_SalaryRange_ID < 1) 
			set_Value (COLUMNNAME_HR_SalaryRange_ID, null);
		else 
			set_Value (COLUMNNAME_HR_SalaryRange_ID, Integer.valueOf(HR_SalaryRange_ID));
	}

	/** Get Salary Range.
		@return The Salary Rage is use in Job Openings
	  */
	public int getHR_SalaryRange_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_SalaryRange_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_SalaryStructure getHR_SalaryStructure() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_SalaryStructure)MTable.get(getCtx(), org.eevolution.model.I_HR_SalaryStructure.Table_Name)
			.getPO(getHR_SalaryStructure_ID(), get_TrxName());	}

	/** Set Salary Structure.
		@param HR_SalaryStructure_ID 
		Salary Structure of an Employee
	  */
	public void setHR_SalaryStructure_ID (int HR_SalaryStructure_ID)
	{
		if (HR_SalaryStructure_ID < 1) 
			set_Value (COLUMNNAME_HR_SalaryStructure_ID, null);
		else 
			set_Value (COLUMNNAME_HR_SalaryStructure_ID, Integer.valueOf(HR_SalaryStructure_ID));
	}

	/** Get Salary Structure.
		@return Salary Structure of an Employee
	  */
	public int getHR_SalaryStructure_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_SalaryStructure_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.eevolution.model.I_HR_SkillType getHR_SkillType() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_SkillType)MTable.get(getCtx(), org.eevolution.model.I_HR_SkillType.Table_Name)
			.getPO(getHR_SkillType_ID(), get_TrxName());	}

	/** Set Skill Type.
		@param HR_SkillType_ID 
		Skill Type for an Employee
	  */
	public void setHR_SkillType_ID (int HR_SkillType_ID)
	{
		if (HR_SkillType_ID < 1) 
			set_Value (COLUMNNAME_HR_SkillType_ID, null);
		else 
			set_Value (COLUMNNAME_HR_SkillType_ID, Integer.valueOf(HR_SkillType_ID));
	}

	/** Get Skill Type.
		@return Skill Type for an Employee
	  */
	public int getHR_SkillType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_SkillType_ID);
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
			set_Value (COLUMNNAME_HR_WorkGroup_ID, null);
		else 
			set_Value (COLUMNNAME_HR_WorkGroup_ID, Integer.valueOf(HR_WorkGroup_ID));
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

	/** Set Identification Mark.
		@param IdentificationMark 
		Identification Mark
	  */
	public void setIdentificationMark (String IdentificationMark)
	{
		set_Value (COLUMNNAME_IdentificationMark, IdentificationMark);
	}

	/** Get Identification Mark.
		@return Identification Mark
	  */
	public String getIdentificationMark () 
	{
		return (String)get_Value(COLUMNNAME_IdentificationMark);
	}

	/** Set Image URL.
		@param ImageURL 
		URL of  image
	  */
	public void setImageURL (String ImageURL)
	{
		set_Value (COLUMNNAME_ImageURL, ImageURL);
	}

	/** Get Image URL.
		@return URL of  image
	  */
	public String getImageURL () 
	{
		return (String)get_Value(COLUMNNAME_ImageURL);
	}

	/** Set Manager.
		@param IsManager 
		Defines employee as manager
	  */
	public void setIsManager (boolean IsManager)
	{
		set_Value (COLUMNNAME_IsManager, Boolean.valueOf(IsManager));
	}

	/** Get Manager.
		@return Defines employee as manager
	  */
	public boolean isManager () 
	{
		Object oo = get_Value(COLUMNNAME_IsManager);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Logo.
		@param Logo_ID Logo	  */
	public void setLogo_ID (int Logo_ID)
	{
		if (Logo_ID < 1) 
			set_Value (COLUMNNAME_Logo_ID, null);
		else 
			set_Value (COLUMNNAME_Logo_ID, Integer.valueOf(Logo_ID));
	}

	/** Get Logo.
		@return Logo	  */
	public int getLogo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Logo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** MaritalStatus AD_Reference_ID=53614 */
	public static final int MARITALSTATUS_AD_Reference_ID=53614;
	/** Divorced = D */
	public static final String MARITALSTATUS_Divorced = "D";
	/** Live-in = L */
	public static final String MARITALSTATUS_Live_In = "L";
	/** Married = M */
	public static final String MARITALSTATUS_Married = "M";
	/** Single = S */
	public static final String MARITALSTATUS_Single = "S";
	/** Widow = W */
	public static final String MARITALSTATUS_Widow = "W";
	/** Windower = X */
	public static final String MARITALSTATUS_Windower = "X";
	/** Set Marital Status.
		@param MaritalStatus Marital Status	  */
	public void setMaritalStatus (String MaritalStatus)
	{

		set_Value (COLUMNNAME_MaritalStatus, MaritalStatus);
	}

	/** Get Marital Status.
		@return Marital Status	  */
	public String getMaritalStatus () 
	{
		return (String)get_Value(COLUMNNAME_MaritalStatus);
	}

	/** Set Marriage Anniversary Date.
		@param MarriageAnniversaryDate 
		Marriage Anniversary Date
	  */
	public void setMarriageAnniversaryDate (Timestamp MarriageAnniversaryDate)
	{
		set_Value (COLUMNNAME_MarriageAnniversaryDate, MarriageAnniversaryDate);
	}

	/** Get Marriage Anniversary Date.
		@return Marriage Anniversary Date
	  */
	public Timestamp getMarriageAnniversaryDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_MarriageAnniversaryDate);
	}

	/** Set Monthly Salary.
		@param MonthlySalary 
		Monthly Salary
	  */
	public void setMonthlySalary (BigDecimal MonthlySalary)
	{
		set_Value (COLUMNNAME_MonthlySalary, MonthlySalary);
	}

	/** Get Monthly Salary.
		@return Monthly Salary
	  */
	public BigDecimal getMonthlySalary () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MonthlySalary);
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

	/** Set Name 2.
		@param Name2 
		Additional Name
	  */
	public void setName2 (String Name2)
	{
		set_Value (COLUMNNAME_Name2, Name2);
	}

	/** Get Name 2.
		@return Additional Name
	  */
	public String getName2 () 
	{
		return (String)get_Value(COLUMNNAME_Name2);
	}

	/** Set National Code.
		@param NationalCode National Code	  */
	public void setNationalCode (String NationalCode)
	{
		set_Value (COLUMNNAME_NationalCode, NationalCode);
	}

	/** Get National Code.
		@return National Code	  */
	public String getNationalCode () 
	{
		return (String)get_Value(COLUMNNAME_NationalCode);
	}

	public org.compiere.model.I_C_Country getNationality() throws RuntimeException
    {
		return (org.compiere.model.I_C_Country)MTable.get(getCtx(), org.compiere.model.I_C_Country.Table_Name)
			.getPO(getNationality_ID(), get_TrxName());	}

	/** Set Nationality.
		@param Nationality_ID 
		Nationality
	  */
	public void setNationality_ID (int Nationality_ID)
	{
		if (Nationality_ID < 1) 
			set_Value (COLUMNNAME_Nationality_ID, null);
		else 
			set_Value (COLUMNNAME_Nationality_ID, Integer.valueOf(Nationality_ID));
	}

	/** Get Nationality.
		@return Nationality
	  */
	public int getNationality_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nationality_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Partners Birth Date.
		@param PartnersBirthDate 
		Partners Birth Date
	  */
	public void setPartnersBirthDate (Timestamp PartnersBirthDate)
	{
		set_Value (COLUMNNAME_PartnersBirthDate, PartnersBirthDate);
	}

	/** Get Partners Birth Date.
		@return Partners Birth Date
	  */
	public Timestamp getPartnersBirthDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PartnersBirthDate);
	}

	/** Set Partner's Name.
		@param PartnersName 
		Partner's Name
	  */
	public void setPartnersName (String PartnersName)
	{
		set_Value (COLUMNNAME_PartnersName, PartnersName);
	}

	/** Get Partner's Name.
		@return Partner's Name
	  */
	public String getPartnersName () 
	{
		return (String)get_Value(COLUMNNAME_PartnersName);
	}

	/** PaymentRule AD_Reference_ID=195 */
	public static final int PAYMENTRULE_AD_Reference_ID=195;
	/** Cash = B */
	public static final String PAYMENTRULE_Cash = "B";
	/** Credit Card = K */
	public static final String PAYMENTRULE_CreditCard = "K";
	/** Direct Deposit = T */
	public static final String PAYMENTRULE_DirectDeposit = "T";
	/** Check = S */
	public static final String PAYMENTRULE_Check = "S";
	/** On Credit = P */
	public static final String PAYMENTRULE_OnCredit = "P";
	/** Direct Debit = D */
	public static final String PAYMENTRULE_DirectDebit = "D";
	/** Mixed = M */
	public static final String PAYMENTRULE_Mixed = "M";
	/** Set Payment Rule.
		@param PaymentRule 
		How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule)
	{

		set_Value (COLUMNNAME_PaymentRule, PaymentRule);
	}

	/** Get Payment Rule.
		@return How you pay the invoice
	  */
	public String getPaymentRule () 
	{
		return (String)get_Value(COLUMNNAME_PaymentRule);
	}

	/** Set Social Security Code.
		@param SSCode Social Security Code	  */
	public void setSSCode (String SSCode)
	{
		set_Value (COLUMNNAME_SSCode, SSCode);
	}

	/** Get Social Security Code.
		@return Social Security Code	  */
	public String getSSCode () 
	{
		return (String)get_Value(COLUMNNAME_SSCode);
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}

	/** Set Thumb Image.
		@param ThumbImage_ID 
		Thumb Image
	  */
	public void setThumbImage_ID (int ThumbImage_ID)
	{
		if (ThumbImage_ID < 1) 
			set_Value (COLUMNNAME_ThumbImage_ID, null);
		else 
			set_Value (COLUMNNAME_ThumbImage_ID, Integer.valueOf(ThumbImage_ID));
	}

	/** Get Thumb Image.
		@return Thumb Image
	  */
	public int getThumbImage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ThumbImage_ID);
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