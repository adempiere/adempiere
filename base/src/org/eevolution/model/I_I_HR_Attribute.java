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
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_HR_Attribute
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_I_HR_Attribute 
{

    /** TableName=I_HR_Attribute */
    public static final String Table_Name = "I_HR_Attribute";

    /** AD_Table_ID=53900 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

	public org.compiere.model.I_AD_Org getAD_OrgTrx() throws RuntimeException;

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AD_Rule_ID */
    public static final String COLUMNNAME_AD_Rule_ID = "AD_Rule_ID";

	/** Set Rule	  */
	public void setAD_Rule_ID (int AD_Rule_ID);

	/** Get Rule	  */
	public int getAD_Rule_ID();

	public org.compiere.model.I_AD_Rule getAD_Rule() throws RuntimeException;

    /** Column name ActivityValue */
    public static final String COLUMNNAME_ActivityValue = "ActivityValue";

	/** Set Activity Value	  */
	public void setActivityValue (String ActivityValue);

	/** Get Activity Value	  */
	public String getActivityValue();

    /** Column name Amount */
    public static final String COLUMNNAME_Amount = "Amount";

	/** Set Amount.
	  * Amount in a defined currency
	  */
	public void setAmount (BigDecimal Amount);

	/** Get Amount.
	  * Amount in a defined currency
	  */
	public BigDecimal getAmount();

    /** Column name C_Activity_ID */
    public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";

	/** Set Activity.
	  * Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID);

	/** Get Activity.
	  * Business Activity
	  */
	public int getC_Activity_ID();

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException;

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Campaign_ID */
    public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";

	/** Set Campaign.
	  * Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID);

	/** Get Campaign.
	  * Marketing Campaign
	  */
	public int getC_Campaign_ID();

	public org.compiere.model.I_C_Campaign getC_Campaign() throws RuntimeException;

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException;

    /** Column name C_DocType_ID */
    public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";

	/** Set Document Type.
	  * Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID);

	/** Get Document Type.
	  * Document type or rules
	  */
	public int getC_DocType_ID();

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

    /** Column name CampaignValue */
    public static final String COLUMNNAME_CampaignValue = "CampaignValue";

	/** Set Campaign Value	  */
	public void setCampaignValue (String CampaignValue);

	/** Get Campaign Value	  */
	public String getCampaignValue();

    /** Column name CareerLevelValue */
    public static final String COLUMNNAME_CareerLevelValue = "CareerLevelValue";

	/** Set Career Level Value.
	  * The Career Level Value for this position
	  */
	public void setCareerLevelValue (String CareerLevelValue);

	/** Get Career Level Value.
	  * The Career Level Value for this position
	  */
	public String getCareerLevelValue();

    /** Column name ConceptValue */
    public static final String COLUMNNAME_ConceptValue = "ConceptValue";

	/** Set Concept Value.
	  * Value of the Concept
	  */
	public void setConceptValue (String ConceptValue);

	/** Get Concept Value.
	  * Value of the Concept
	  */
	public String getConceptValue();

    /** Column name ContractValue */
    public static final String COLUMNNAME_ContractValue = "ContractValue";

	/** Set Payroll Contract Value.
	  * Payroll Contract Value
	  */
	public void setContractValue (String ContractValue);

	/** Get Payroll Contract Value.
	  * Payroll Contract Value
	  */
	public String getContractValue();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DegreeValue */
    public static final String COLUMNNAME_DegreeValue = "DegreeValue";

	/** Set Degree Value.
	  * Degree Value for an Employee Import
	  */
	public void setDegreeValue (String DegreeValue);

	/** Get Degree Value.
	  * Degree Value for an Employee Import
	  */
	public String getDegreeValue();

    /** Column name DepartmentValue */
    public static final String COLUMNNAME_DepartmentValue = "DepartmentValue";

	/** Set Department Value.
	  * Department Value
	  */
	public void setDepartmentValue (String DepartmentValue);

	/** Get Department Value.
	  * Department Value
	  */
	public String getDepartmentValue();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DesignationValue */
    public static final String COLUMNNAME_DesignationValue = "DesignationValue";

	/** Set Designation Value.
	  * Designation Value is a nationally recognized level
	  */
	public void setDesignationValue (String DesignationValue);

	/** Get Designation Value.
	  * Designation Value is a nationally recognized level
	  */
	public String getDesignationValue();

    /** Column name EmployeeStatus */
    public static final String COLUMNNAME_EmployeeStatus = "EmployeeStatus";

	/** Set Employee Status	  */
	public void setEmployeeStatus (String EmployeeStatus);

	/** Get Employee Status	  */
	public String getEmployeeStatus();

    /** Column name EmployeeTypeValue */
    public static final String COLUMNNAME_EmployeeTypeValue = "EmployeeTypeValue";

	/** Set Employee Type Value.
	  * Employee Type Value
	  */
	public void setEmployeeTypeValue (String EmployeeTypeValue);

	/** Get Employee Type Value.
	  * Employee Type Value
	  */
	public String getEmployeeTypeValue();

    /** Column name GradeValue */
    public static final String COLUMNNAME_GradeValue = "GradeValue";

	/** Set Grade Value.
	  * Grade Value for Impor Employee
	  */
	public void setGradeValue (String GradeValue);

	/** Get Grade Value.
	  * Grade Value for Impor Employee
	  */
	public String getGradeValue();

    /** Column name HR_Attribute_ID */
    public static final String COLUMNNAME_HR_Attribute_ID = "HR_Attribute_ID";

	/** Set Payroll Employee Attribute.
	  * Employee Attribute allows to add any metadata of type (text, date , quantity and amount ) of an Employee.
	  */
	public void setHR_Attribute_ID (int HR_Attribute_ID);

	/** Get Payroll Employee Attribute.
	  * Employee Attribute allows to add any metadata of type (text, date , quantity and amount ) of an Employee.
	  */
	public int getHR_Attribute_ID();

	public org.eevolution.model.I_HR_Attribute getHR_Attribute() throws RuntimeException;

    /** Column name HR_CareerLevel_ID */
    public static final String COLUMNNAME_HR_CareerLevel_ID = "HR_CareerLevel_ID";

	/** Set Career Level.
	  * The Career Level for this position
	  */
	public void setHR_CareerLevel_ID (int HR_CareerLevel_ID);

	/** Get Career Level.
	  * The Career Level for this position
	  */
	public int getHR_CareerLevel_ID();

	public org.eevolution.model.I_HR_CareerLevel getHR_CareerLevel() throws RuntimeException;

    /** Column name HR_Concept_ID */
    public static final String COLUMNNAME_HR_Concept_ID = "HR_Concept_ID";

	/** Set Global Payroll Concept.
	  * The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public void setHR_Concept_ID (int HR_Concept_ID);

	/** Get Global Payroll Concept.
	  * The Payroll Concept allows to define all the perception and deductions elements needed to define a payroll.
	  */
	public int getHR_Concept_ID();

	public org.eevolution.model.I_HR_Concept getHR_Concept() throws RuntimeException;

    /** Column name HR_Contract_ID */
    public static final String COLUMNNAME_HR_Contract_ID = "HR_Contract_ID";

	/** Set Payroll Contract	  */
	public void setHR_Contract_ID (int HR_Contract_ID);

	/** Get Payroll Contract	  */
	public int getHR_Contract_ID();

	public org.eevolution.model.I_HR_Contract getHR_Contract() throws RuntimeException;

    /** Column name HR_Degree_ID */
    public static final String COLUMNNAME_HR_Degree_ID = "HR_Degree_ID";

	/** Set Degree.
	  * Degree for an Employee
	  */
	public void setHR_Degree_ID (int HR_Degree_ID);

	/** Get Degree.
	  * Degree for an Employee
	  */
	public int getHR_Degree_ID();

	public org.eevolution.model.I_HR_Degree getHR_Degree() throws RuntimeException;

    /** Column name HR_Department_ID */
    public static final String COLUMNNAME_HR_Department_ID = "HR_Department_ID";

	/** Set Payroll Department	  */
	public void setHR_Department_ID (int HR_Department_ID);

	/** Get Payroll Department	  */
	public int getHR_Department_ID();

	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException;

    /** Column name HR_Designation_ID */
    public static final String COLUMNNAME_HR_Designation_ID = "HR_Designation_ID";

	/** Set Designation.
	  * Designation is a nationally recognized level
	  */
	public void setHR_Designation_ID (int HR_Designation_ID);

	/** Get Designation.
	  * Designation is a nationally recognized level
	  */
	public int getHR_Designation_ID();

	public org.eevolution.model.I_HR_Designation getHR_Designation() throws RuntimeException;

    /** Column name HR_EmployeeType_ID */
    public static final String COLUMNNAME_HR_EmployeeType_ID = "HR_EmployeeType_ID";

	/** Set Employee Type.
	  * Employee Type
	  */
	public void setHR_EmployeeType_ID (int HR_EmployeeType_ID);

	/** Get Employee Type.
	  * Employee Type
	  */
	public int getHR_EmployeeType_ID();

	public org.eevolution.model.I_HR_EmployeeType getHR_EmployeeType() throws RuntimeException;

    /** Column name HR_Employee_ID */
    public static final String COLUMNNAME_HR_Employee_ID = "HR_Employee_ID";

	/** Set Payroll Employee	  */
	public void setHR_Employee_ID (int HR_Employee_ID);

	/** Get Payroll Employee	  */
	public int getHR_Employee_ID();

	public org.eevolution.model.I_HR_Employee getHR_Employee() throws RuntimeException;

    /** Column name HR_Grade_ID */
    public static final String COLUMNNAME_HR_Grade_ID = "HR_Grade_ID";

	/** Set Grade.
	  * Grade
	  */
	public void setHR_Grade_ID (int HR_Grade_ID);

	/** Get Grade.
	  * Grade
	  */
	public int getHR_Grade_ID();

	public org.eevolution.model.I_HR_Grade getHR_Grade() throws RuntimeException;

    /** Column name HR_JobEducation_ID */
    public static final String COLUMNNAME_HR_JobEducation_ID = "HR_JobEducation_ID";

	/** Set Job Education.
	  * The Job Education for this position
	  */
	public void setHR_JobEducation_ID (int HR_JobEducation_ID);

	/** Get Job Education.
	  * The Job Education for this position
	  */
	public int getHR_JobEducation_ID();

	public org.eevolution.model.I_HR_JobEducation getHR_JobEducation() throws RuntimeException;

    /** Column name HR_JobType_ID */
    public static final String COLUMNNAME_HR_JobType_ID = "HR_JobType_ID";

	/** Set Job Type.
	  * The Job Type for a Job Openings
	  */
	public void setHR_JobType_ID (int HR_JobType_ID);

	/** Get Job Type.
	  * The Job Type for a Job Openings
	  */
	public int getHR_JobType_ID();

	public org.eevolution.model.I_HR_JobType getHR_JobType() throws RuntimeException;

    /** Column name HR_Job_ID */
    public static final String COLUMNNAME_HR_Job_ID = "HR_Job_ID";

	/** Set Payroll Job	  */
	public void setHR_Job_ID (int HR_Job_ID);

	/** Get Payroll Job	  */
	public int getHR_Job_ID();

	public org.eevolution.model.I_HR_Job getHR_Job() throws RuntimeException;

    /** Column name HR_Payroll_ID */
    public static final String COLUMNNAME_HR_Payroll_ID = "HR_Payroll_ID";

	/** Set Payroll	  */
	public void setHR_Payroll_ID (int HR_Payroll_ID);

	/** Get Payroll	  */
	public int getHR_Payroll_ID();

	public org.eevolution.model.I_HR_Payroll getHR_Payroll() throws RuntimeException;

    /** Column name HR_Race_ID */
    public static final String COLUMNNAME_HR_Race_ID = "HR_Race_ID";

	/** Set Race.
	  * Race
	  */
	public void setHR_Race_ID (int HR_Race_ID);

	/** Get Race.
	  * Race
	  */
	public int getHR_Race_ID();

	public org.eevolution.model.I_HR_Race getHR_Race() throws RuntimeException;

    /** Column name HR_SalaryRange_ID */
    public static final String COLUMNNAME_HR_SalaryRange_ID = "HR_SalaryRange_ID";

	/** Set Salary Range.
	  * The Salary Rage is use in Job Openings
	  */
	public void setHR_SalaryRange_ID (int HR_SalaryRange_ID);

	/** Get Salary Range.
	  * The Salary Rage is use in Job Openings
	  */
	public int getHR_SalaryRange_ID();

	public org.eevolution.model.I_HR_SalaryRange getHR_SalaryRange() throws RuntimeException;

    /** Column name HR_SalaryStructure_ID */
    public static final String COLUMNNAME_HR_SalaryStructure_ID = "HR_SalaryStructure_ID";

	/** Set Salary Structure.
	  * Salary Structure of an Employee
	  */
	public void setHR_SalaryStructure_ID (int HR_SalaryStructure_ID);

	/** Get Salary Structure.
	  * Salary Structure of an Employee
	  */
	public int getHR_SalaryStructure_ID();

	public org.eevolution.model.I_HR_SalaryStructure getHR_SalaryStructure() throws RuntimeException;

    /** Column name HR_ShiftGroup_ID */
    public static final String COLUMNNAME_HR_ShiftGroup_ID = "HR_ShiftGroup_ID";

	/** Set Shift Group.
	  * Shift Group
	  */
	public void setHR_ShiftGroup_ID (int HR_ShiftGroup_ID);

	/** Get Shift Group.
	  * Shift Group
	  */
	public int getHR_ShiftGroup_ID();

	public org.eevolution.model.I_HR_ShiftGroup getHR_ShiftGroup() throws RuntimeException;

    /** Column name HR_SkillType_ID */
    public static final String COLUMNNAME_HR_SkillType_ID = "HR_SkillType_ID";

	/** Set Skill Type.
	  * Skill Type for an Employee
	  */
	public void setHR_SkillType_ID (int HR_SkillType_ID);

	/** Get Skill Type.
	  * Skill Type for an Employee
	  */
	public int getHR_SkillType_ID();

	public org.eevolution.model.I_HR_SkillType getHR_SkillType() throws RuntimeException;

    /** Column name HR_WorkGroup_ID */
    public static final String COLUMNNAME_HR_WorkGroup_ID = "HR_WorkGroup_ID";

	/** Set Work Group.
	  * Work Group
	  */
	public void setHR_WorkGroup_ID (int HR_WorkGroup_ID);

	/** Get Work Group.
	  * Work Group
	  */
	public int getHR_WorkGroup_ID();

	public org.eevolution.model.I_HR_WorkGroup getHR_WorkGroup() throws RuntimeException;

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_HR_Attribute_ID */
    public static final String COLUMNNAME_I_HR_Attribute_ID = "I_HR_Attribute_ID";

	/** Set I_HR_Attribute	  */
	public void setI_HR_Attribute_ID (int I_HR_Attribute_ID);

	/** Get I_HR_Attribute	  */
	public int getI_HR_Attribute_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsPrinted */
    public static final String COLUMNNAME_IsPrinted = "IsPrinted";

	/** Set Printed.
	  * Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted);

	/** Get Printed.
	  * Indicates if this document / line is printed
	  */
	public boolean isPrinted();

    /** Column name JobEducationValue */
    public static final String COLUMNNAME_JobEducationValue = "JobEducationValue";

	/** Set Job Education Value.
	  * Job Education Value of an Employee
	  */
	public void setJobEducationValue (String JobEducationValue);

	/** Get Job Education Value.
	  * Job Education Value of an Employee
	  */
	public String getJobEducationValue();

    /** Column name JobTypeValue */
    public static final String COLUMNNAME_JobTypeValue = "JobTypeValue";

	/** Set Job Type Value.
	  * The Job Type Value for a Job Openings
	  */
	public void setJobTypeValue (String JobTypeValue);

	/** Get Job Type Value.
	  * The Job Type Value for a Job Openings
	  */
	public String getJobTypeValue();

    /** Column name JobValue */
    public static final String COLUMNNAME_JobValue = "JobValue";

	/** Set Job Value.
	  * Job Value
	  */
	public void setJobValue (String JobValue);

	/** Get Job Value.
	  * Job Value
	  */
	public String getJobValue();

    /** Column name MaxValue */
    public static final String COLUMNNAME_MaxValue = "MaxValue";

	/** Set Max Value	  */
	public void setMaxValue (int MaxValue);

	/** Get Max Value	  */
	public int getMaxValue();

    /** Column name MinValue */
    public static final String COLUMNNAME_MinValue = "MinValue";

	/** Set Min Value	  */
	public void setMinValue (int MinValue);

	/** Get Min Value	  */
	public int getMinValue();

    /** Column name OrgTrxValue */
    public static final String COLUMNNAME_OrgTrxValue = "OrgTrxValue";

	/** Set Trx Org Key.
	  * Key of the Transaction Organization
	  */
	public void setOrgTrxValue (String OrgTrxValue);

	/** Get Trx Org Key.
	  * Key of the Transaction Organization
	  */
	public String getOrgTrxValue();

    /** Column name PayrollValue */
    public static final String COLUMNNAME_PayrollValue = "PayrollValue";

	/** Set Payroll Value.
	  * Define the a Search key of a payroll
	  */
	public void setPayrollValue (String PayrollValue);

	/** Get Payroll Value.
	  * Define the a Search key of a payroll
	  */
	public String getPayrollValue();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name ProjectValue */
    public static final String COLUMNNAME_ProjectValue = "ProjectValue";

	/** Set Project Key.
	  * Key of the Project
	  */
	public void setProjectValue (String ProjectValue);

	/** Get Project Key.
	  * Key of the Project
	  */
	public String getProjectValue();

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();

    /** Column name RaceValue */
    public static final String COLUMNNAME_RaceValue = "RaceValue";

	/** Set Race Value.
	  * Race Value
	  */
	public void setRaceValue (String RaceValue);

	/** Get Race Value.
	  * Race Value
	  */
	public String getRaceValue();

    /** Column name ReferenceNo */
    public static final String COLUMNNAME_ReferenceNo = "ReferenceNo";

	/** Set Reference No.
	  * Your customer or vendor number at the Business Partner's site
	  */
	public void setReferenceNo (String ReferenceNo);

	/** Get Reference No.
	  * Your customer or vendor number at the Business Partner's site
	  */
	public String getReferenceNo();

    /** Column name SalaryRangeValue */
    public static final String COLUMNNAME_SalaryRangeValue = "SalaryRangeValue";

	/** Set Salary Range Value.
	  * The Salary Rage Value is use in Job Openings
	  */
	public void setSalaryRangeValue (String SalaryRangeValue);

	/** Get Salary Range Value.
	  * The Salary Rage Value is use in Job Openings
	  */
	public String getSalaryRangeValue();

    /** Column name SalaryStructureValue */
    public static final String COLUMNNAME_SalaryStructureValue = "SalaryStructureValue";

	/** Set Salary Structure Value.
	  * Salary Structure Value of an Employee
	  */
	public void setSalaryStructureValue (String SalaryStructureValue);

	/** Get Salary Structure Value.
	  * Salary Structure Value of an Employee
	  */
	public String getSalaryStructureValue();

    /** Column name ServiceDate */
    public static final String COLUMNNAME_ServiceDate = "ServiceDate";

	/** Set Service date.
	  * Date service was provided
	  */
	public void setServiceDate (Timestamp ServiceDate);

	/** Get Service date.
	  * Date service was provided
	  */
	public Timestamp getServiceDate();

    /** Column name ShiftGroupValue */
    public static final String COLUMNNAME_ShiftGroupValue = "ShiftGroupValue";

	/** Set Shift Group Value.
	  * Shift Group Value
	  */
	public void setShiftGroupValue (String ShiftGroupValue);

	/** Get Shift Group Value.
	  * Shift Group Value
	  */
	public String getShiftGroupValue();

    /** Column name SkillTypeValue */
    public static final String COLUMNNAME_SkillTypeValue = "SkillTypeValue";

	/** Set Skill Type Value.
	  * Skill Type Value
	  */
	public void setSkillTypeValue (String SkillTypeValue);

	/** Get Skill Type Value.
	  * Skill Type Value
	  */
	public String getSkillTypeValue();

    /** Column name TextMsg */
    public static final String COLUMNNAME_TextMsg = "TextMsg";

	/** Set Text Message.
	  * Text Message
	  */
	public void setTextMsg (String TextMsg);

	/** Get Text Message.
	  * Text Message
	  */
	public String getTextMsg();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name ValidFrom */
    public static final String COLUMNNAME_ValidFrom = "ValidFrom";

	/** Set Valid from.
	  * Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom);

	/** Get Valid from.
	  * Valid from including this date (first day)
	  */
	public Timestamp getValidFrom();

    /** Column name ValidTo */
    public static final String COLUMNNAME_ValidTo = "ValidTo";

	/** Set Valid to.
	  * Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo);

	/** Get Valid to.
	  * Valid to including this date (last day)
	  */
	public Timestamp getValidTo();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();

    /** Column name WorkGroupValue */
    public static final String COLUMNNAME_WorkGroupValue = "WorkGroupValue";

	/** Set Work Group Value.
	  * Work Group Value used for import
	  */
	public void setWorkGroupValue (String WorkGroupValue);

	/** Get Work Group Value.
	  * Work Group Value used for import
	  */
	public String getWorkGroupValue();
}
