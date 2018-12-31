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

/** Generated Model for I_HR_Attribute
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_I_HR_Attribute extends PO implements I_I_HR_Attribute, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_I_HR_Attribute (Properties ctx, int I_HR_Attribute_ID, String trxName)
    {
      super (ctx, I_HR_Attribute_ID, trxName);
      /** if (I_HR_Attribute_ID == 0)
        {
			setI_HR_Attribute_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_HR_Attribute (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_I_HR_Attribute[")
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

	public org.compiere.model.I_AD_Rule getAD_Rule() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Rule)MTable.get(getCtx(), org.compiere.model.I_AD_Rule.Table_Name)
			.getPO(getAD_Rule_ID(), get_TrxName());	}

	/** Set Rule.
		@param AD_Rule_ID Rule	  */
	public void setAD_Rule_ID (int AD_Rule_ID)
	{
		if (AD_Rule_ID < 1) 
			set_Value (COLUMNNAME_AD_Rule_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Rule_ID, Integer.valueOf(AD_Rule_ID));
	}

	/** Get Rule.
		@return Rule	  */
	public int getAD_Rule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Rule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Activity Value.
		@param ActivityValue Activity Value	  */
	public void setActivityValue (String ActivityValue)
	{
		set_Value (COLUMNNAME_ActivityValue, ActivityValue);
	}

	/** Get Activity Value.
		@return Activity Value	  */
	public String getActivityValue () 
	{
		return (String)get_Value(COLUMNNAME_ActivityValue);
	}

	/** Set Amount.
		@param Amount 
		Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount)
	{
		set_Value (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getC_Charge_ID(), get_TrxName());	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocType_ID(), get_TrxName());	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_Value (COLUMNNAME_C_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
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

	/** Set Campaign Value.
		@param CampaignValue Campaign Value	  */
	public void setCampaignValue (String CampaignValue)
	{
		set_Value (COLUMNNAME_CampaignValue, CampaignValue);
	}

	/** Get Campaign Value.
		@return Campaign Value	  */
	public String getCampaignValue () 
	{
		return (String)get_Value(COLUMNNAME_CampaignValue);
	}

	/** Set Career Level Value.
		@param CareerLevelValue 
		The Career Level Value for this position
	  */
	public void setCareerLevelValue (String CareerLevelValue)
	{
		set_Value (COLUMNNAME_CareerLevelValue, CareerLevelValue);
	}

	/** Get Career Level Value.
		@return The Career Level Value for this position
	  */
	public String getCareerLevelValue () 
	{
		return (String)get_Value(COLUMNNAME_CareerLevelValue);
	}

	/** Set Concept Value.
		@param ConceptValue 
		Value of the Concept
	  */
	public void setConceptValue (String ConceptValue)
	{
		set_Value (COLUMNNAME_ConceptValue, ConceptValue);
	}

	/** Get Concept Value.
		@return Value of the Concept
	  */
	public String getConceptValue () 
	{
		return (String)get_Value(COLUMNNAME_ConceptValue);
	}

	/** Set Payroll Contract Value.
		@param ContractValue 
		Payroll Contract Value
	  */
	public void setContractValue (String ContractValue)
	{
		set_Value (COLUMNNAME_ContractValue, ContractValue);
	}

	/** Get Payroll Contract Value.
		@return Payroll Contract Value
	  */
	public String getContractValue () 
	{
		return (String)get_Value(COLUMNNAME_ContractValue);
	}

	/** Set Degree Value.
		@param DegreeValue 
		Degree Value for an Employee Import
	  */
	public void setDegreeValue (String DegreeValue)
	{
		set_Value (COLUMNNAME_DegreeValue, DegreeValue);
	}

	/** Get Degree Value.
		@return Degree Value for an Employee Import
	  */
	public String getDegreeValue () 
	{
		return (String)get_Value(COLUMNNAME_DegreeValue);
	}

	/** Set Department Value.
		@param DepartmentValue 
		Department Value
	  */
	public void setDepartmentValue (String DepartmentValue)
	{
		set_Value (COLUMNNAME_DepartmentValue, DepartmentValue);
	}

	/** Get Department Value.
		@return Department Value
	  */
	public String getDepartmentValue () 
	{
		return (String)get_Value(COLUMNNAME_DepartmentValue);
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

	/** Set Designation Value.
		@param DesignationValue 
		Designation Value is a nationally recognized level
	  */
	public void setDesignationValue (String DesignationValue)
	{
		set_Value (COLUMNNAME_DesignationValue, DesignationValue);
	}

	/** Get Designation Value.
		@return Designation Value is a nationally recognized level
	  */
	public String getDesignationValue () 
	{
		return (String)get_Value(COLUMNNAME_DesignationValue);
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

	/** Set Employee Type Value.
		@param EmployeeTypeValue 
		Employee Type Value
	  */
	public void setEmployeeTypeValue (String EmployeeTypeValue)
	{
		set_Value (COLUMNNAME_EmployeeTypeValue, EmployeeTypeValue);
	}

	/** Get Employee Type Value.
		@return Employee Type Value
	  */
	public String getEmployeeTypeValue () 
	{
		return (String)get_Value(COLUMNNAME_EmployeeTypeValue);
	}

	/** Set Grade Value.
		@param GradeValue 
		Grade Value for Impor Employee
	  */
	public void setGradeValue (String GradeValue)
	{
		set_Value (COLUMNNAME_GradeValue, GradeValue);
	}

	/** Get Grade Value.
		@return Grade Value for Impor Employee
	  */
	public String getGradeValue () 
	{
		return (String)get_Value(COLUMNNAME_GradeValue);
	}

	public org.eevolution.model.I_HR_Attribute getHR_Attribute() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Attribute)MTable.get(getCtx(), org.eevolution.model.I_HR_Attribute.Table_Name)
			.getPO(getHR_Attribute_ID(), get_TrxName());	}

	/** Set Payroll Employee Attribute.
		@param HR_Attribute_ID 
		Employee Attribute allows to add any metadata of type (text, date , quantity and amount ) of an Employee.
	  */
	public void setHR_Attribute_ID (int HR_Attribute_ID)
	{
		if (HR_Attribute_ID < 1) 
			set_Value (COLUMNNAME_HR_Attribute_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Attribute_ID, Integer.valueOf(HR_Attribute_ID));
	}

	/** Get Payroll Employee Attribute.
		@return Employee Attribute allows to add any metadata of type (text, date , quantity and amount ) of an Employee.
	  */
	public int getHR_Attribute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Attribute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.eevolution.model.I_HR_Concept getHR_Concept() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Concept)MTable.get(getCtx(), org.eevolution.model.I_HR_Concept.Table_Name)
			.getPO(getHR_Concept_ID(), get_TrxName());	}

	/** Set Global Payroll Concept.
		@param HR_Concept_ID 
		The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public void setHR_Concept_ID (int HR_Concept_ID)
	{
		if (HR_Concept_ID < 1) 
			set_Value (COLUMNNAME_HR_Concept_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Concept_ID, Integer.valueOf(HR_Concept_ID));
	}

	/** Get Global Payroll Concept.
		@return The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public int getHR_Concept_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Concept_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Contract getHR_Contract() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Contract)MTable.get(getCtx(), org.eevolution.model.I_HR_Contract.Table_Name)
			.getPO(getHR_Contract_ID(), get_TrxName());	}

	/** Set Payroll Contract.
		@param HR_Contract_ID Payroll Contract	  */
	public void setHR_Contract_ID (int HR_Contract_ID)
	{
		if (HR_Contract_ID < 1) 
			set_Value (COLUMNNAME_HR_Contract_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Contract_ID, Integer.valueOf(HR_Contract_ID));
	}

	/** Get Payroll Contract.
		@return Payroll Contract	  */
	public int getHR_Contract_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Contract_ID);
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

	public org.eevolution.model.I_HR_Employee getHR_Employee() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Employee)MTable.get(getCtx(), org.eevolution.model.I_HR_Employee.Table_Name)
			.getPO(getHR_Employee_ID(), get_TrxName());	}

	/** Set Payroll Employee.
		@param HR_Employee_ID Payroll Employee	  */
	public void setHR_Employee_ID (int HR_Employee_ID)
	{
		if (HR_Employee_ID < 1) 
			set_Value (COLUMNNAME_HR_Employee_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Employee_ID, Integer.valueOf(HR_Employee_ID));
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

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set I_HR_Attribute.
		@param I_HR_Attribute_ID I_HR_Attribute	  */
	public void setI_HR_Attribute_ID (int I_HR_Attribute_ID)
	{
		if (I_HR_Attribute_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_HR_Attribute_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_HR_Attribute_ID, Integer.valueOf(I_HR_Attribute_ID));
	}

	/** Get I_HR_Attribute.
		@return I_HR_Attribute	  */
	public int getI_HR_Attribute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_HR_Attribute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted)
	{
		set_Value (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public boolean isPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Job Education Value.
		@param JobEducationValue 
		Job Education Value of an Employee
	  */
	public void setJobEducationValue (String JobEducationValue)
	{
		set_Value (COLUMNNAME_JobEducationValue, JobEducationValue);
	}

	/** Get Job Education Value.
		@return Job Education Value of an Employee
	  */
	public String getJobEducationValue () 
	{
		return (String)get_Value(COLUMNNAME_JobEducationValue);
	}

	/** Set Job Type Value.
		@param JobTypeValue 
		The Job Type Value for a Job Openings
	  */
	public void setJobTypeValue (String JobTypeValue)
	{
		set_Value (COLUMNNAME_JobTypeValue, JobTypeValue);
	}

	/** Get Job Type Value.
		@return The Job Type Value for a Job Openings
	  */
	public String getJobTypeValue () 
	{
		return (String)get_Value(COLUMNNAME_JobTypeValue);
	}

	/** Set Job Value.
		@param JobValue 
		Job Value
	  */
	public void setJobValue (String JobValue)
	{
		set_Value (COLUMNNAME_JobValue, JobValue);
	}

	/** Get Job Value.
		@return Job Value
	  */
	public String getJobValue () 
	{
		return (String)get_Value(COLUMNNAME_JobValue);
	}

	/** Set Max Value.
		@param MaxValue Max Value	  */
	public void setMaxValue (int MaxValue)
	{
		set_Value (COLUMNNAME_MaxValue, Integer.valueOf(MaxValue));
	}

	/** Get Max Value.
		@return Max Value	  */
	public int getMaxValue () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MaxValue);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Min Value.
		@param MinValue Min Value	  */
	public void setMinValue (int MinValue)
	{
		set_Value (COLUMNNAME_MinValue, Integer.valueOf(MinValue));
	}

	/** Get Min Value.
		@return Min Value	  */
	public int getMinValue () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_MinValue);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Trx Org Key.
		@param OrgTrxValue 
		Key of the Transaction Organization
	  */
	public void setOrgTrxValue (String OrgTrxValue)
	{
		set_Value (COLUMNNAME_OrgTrxValue, OrgTrxValue);
	}

	/** Get Trx Org Key.
		@return Key of the Transaction Organization
	  */
	public String getOrgTrxValue () 
	{
		return (String)get_Value(COLUMNNAME_OrgTrxValue);
	}

	/** Set Payroll Value.
		@param PayrollValue 
		Define the a Search key of a payroll
	  */
	public void setPayrollValue (String PayrollValue)
	{
		set_Value (COLUMNNAME_PayrollValue, PayrollValue);
	}

	/** Get Payroll Value.
		@return Define the a Search key of a payroll
	  */
	public String getPayrollValue () 
	{
		return (String)get_Value(COLUMNNAME_PayrollValue);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_ValueNoCheck (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Project Key.
		@param ProjectValue 
		Key of the Project
	  */
	public void setProjectValue (String ProjectValue)
	{
		set_Value (COLUMNNAME_ProjectValue, ProjectValue);
	}

	/** Get Project Key.
		@return Key of the Project
	  */
	public String getProjectValue () 
	{
		return (String)get_Value(COLUMNNAME_ProjectValue);
	}

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Race Value.
		@param RaceValue 
		Race Value
	  */
	public void setRaceValue (String RaceValue)
	{
		set_Value (COLUMNNAME_RaceValue, RaceValue);
	}

	/** Get Race Value.
		@return Race Value
	  */
	public String getRaceValue () 
	{
		return (String)get_Value(COLUMNNAME_RaceValue);
	}

	/** Set Reference No.
		@param ReferenceNo 
		Your customer or vendor number at the Business Partner's site
	  */
	public void setReferenceNo (String ReferenceNo)
	{
		set_Value (COLUMNNAME_ReferenceNo, ReferenceNo);
	}

	/** Get Reference No.
		@return Your customer or vendor number at the Business Partner's site
	  */
	public String getReferenceNo () 
	{
		return (String)get_Value(COLUMNNAME_ReferenceNo);
	}

	/** Set Salary Range Value.
		@param SalaryRangeValue 
		The Salary Rage Value is use in Job Openings
	  */
	public void setSalaryRangeValue (String SalaryRangeValue)
	{
		set_Value (COLUMNNAME_SalaryRangeValue, SalaryRangeValue);
	}

	/** Get Salary Range Value.
		@return The Salary Rage Value is use in Job Openings
	  */
	public String getSalaryRangeValue () 
	{
		return (String)get_Value(COLUMNNAME_SalaryRangeValue);
	}

	/** Set Salary Structure Value.
		@param SalaryStructureValue 
		Salary Structure Value of an Employee
	  */
	public void setSalaryStructureValue (String SalaryStructureValue)
	{
		set_Value (COLUMNNAME_SalaryStructureValue, SalaryStructureValue);
	}

	/** Get Salary Structure Value.
		@return Salary Structure Value of an Employee
	  */
	public String getSalaryStructureValue () 
	{
		return (String)get_Value(COLUMNNAME_SalaryStructureValue);
	}

	/** Set Service date.
		@param ServiceDate 
		Date service was provided
	  */
	public void setServiceDate (Timestamp ServiceDate)
	{
		set_Value (COLUMNNAME_ServiceDate, ServiceDate);
	}

	/** Get Service date.
		@return Date service was provided
	  */
	public Timestamp getServiceDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ServiceDate);
	}

	/** Set Shift Group Value.
		@param ShiftGroupValue 
		Shift Group Value
	  */
	public void setShiftGroupValue (String ShiftGroupValue)
	{
		set_Value (COLUMNNAME_ShiftGroupValue, ShiftGroupValue);
	}

	/** Get Shift Group Value.
		@return Shift Group Value
	  */
	public String getShiftGroupValue () 
	{
		return (String)get_Value(COLUMNNAME_ShiftGroupValue);
	}

	/** Set Skill Type Value.
		@param SkillTypeValue 
		Skill Type Value
	  */
	public void setSkillTypeValue (String SkillTypeValue)
	{
		set_Value (COLUMNNAME_SkillTypeValue, SkillTypeValue);
	}

	/** Get Skill Type Value.
		@return Skill Type Value
	  */
	public String getSkillTypeValue () 
	{
		return (String)get_Value(COLUMNNAME_SkillTypeValue);
	}

	/** Set Text Message.
		@param TextMsg 
		Text Message
	  */
	public void setTextMsg (String TextMsg)
	{
		set_Value (COLUMNNAME_TextMsg, TextMsg);
	}

	/** Get Text Message.
		@return Text Message
	  */
	public String getTextMsg () 
	{
		return (String)get_Value(COLUMNNAME_TextMsg);
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

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo 
		Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
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

	/** Set Work Group Value.
		@param WorkGroupValue 
		Work Group Value used for import
	  */
	public void setWorkGroupValue (String WorkGroupValue)
	{
		set_Value (COLUMNNAME_WorkGroupValue, WorkGroupValue);
	}

	/** Get Work Group Value.
		@return Work Group Value used for import
	  */
	public String getWorkGroupValue () 
	{
		return (String)get_Value(COLUMNNAME_WorkGroupValue);
	}
}