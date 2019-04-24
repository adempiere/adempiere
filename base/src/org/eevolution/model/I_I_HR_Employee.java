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

/** Generated Interface for I_HR_Employee
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_I_HR_Employee 
{

    /** TableName=I_HR_Employee */
    public static final String Table_Name = "I_HR_Employee";

    /** AD_Table_ID=54184 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException;

    /** Column name ActivityValue */
    public static final String COLUMNNAME_ActivityValue = "ActivityValue";

	/** Set Activity Value	  */
	public void setActivityValue (String ActivityValue);

	/** Get Activity Value	  */
	public String getActivityValue();

    /** Column name BPartnerValue */
    public static final String COLUMNNAME_BPartnerValue = "BPartnerValue";

	/** Set Business Partner Key.
	  * Key of the Business Partner
	  */
	public void setBPartnerValue (String BPartnerValue);

	/** Get Business Partner Key.
	  * Key of the Business Partner
	  */
	public String getBPartnerValue();

    /** Column name Birthday */
    public static final String COLUMNNAME_Birthday = "Birthday";

	/** Set Birthday.
	  * Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday);

	/** Get Birthday.
	  * Birthday or Anniversary day
	  */
	public Timestamp getBirthday();

    /** Column name BloodGroup */
    public static final String COLUMNNAME_BloodGroup = "BloodGroup";

	/** Set Blood Group	  */
	public void setBloodGroup (String BloodGroup);

	/** Get Blood Group	  */
	public String getBloodGroup();

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

    /** Column name C_SalesRegion_ID */
    public static final String COLUMNNAME_C_SalesRegion_ID = "C_SalesRegion_ID";

	/** Set Sales Region.
	  * Sales coverage region
	  */
	public void setC_SalesRegion_ID (int C_SalesRegion_ID);

	/** Get Sales Region.
	  * Sales coverage region
	  */
	public int getC_SalesRegion_ID();

	public org.compiere.model.I_C_SalesRegion getC_SalesRegion() throws RuntimeException;

    /** Column name CampaignValue */
    public static final String COLUMNNAME_CampaignValue = "CampaignValue";

	/** Set Campaign Value	  */
	public void setCampaignValue (String CampaignValue);

	/** Get Campaign Value	  */
	public String getCampaignValue();

    /** Column name CareerLevelName */
    public static final String COLUMNNAME_CareerLevelName = "CareerLevelName";

	/** Set Career Level Name.
	  * The Career Level Name for this position
	  */
	public void setCareerLevelName (String CareerLevelName);

	/** Get Career Level Name.
	  * The Career Level Name for this position
	  */
	public String getCareerLevelName();

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

    /** Column name Code */
    public static final String COLUMNNAME_Code = "Code";

	/** Set Validation code.
	  * Validation Code
	  */
	public void setCode (String Code);

	/** Get Validation code.
	  * Validation Code
	  */
	public String getCode();

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

    /** Column name DailySalary */
    public static final String COLUMNNAME_DailySalary = "DailySalary";

	/** Set Daily Salary.
	  * Daily Salary
	  */
	public void setDailySalary (BigDecimal DailySalary);

	/** Get Daily Salary.
	  * Daily Salary
	  */
	public BigDecimal getDailySalary();

    /** Column name DegreeName */
    public static final String COLUMNNAME_DegreeName = "DegreeName";

	/** Set Degree Name.
	  * Degree Name for an Employee Import
	  */
	public void setDegreeName (String DegreeName);

	/** Get Degree Name.
	  * Degree Name for an Employee Import
	  */
	public String getDegreeName();

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

    /** Column name DepartmentName */
    public static final String COLUMNNAME_DepartmentName = "DepartmentName";

	/** Set Department Name.
	  * Department Name
	  */
	public void setDepartmentName (String DepartmentName);

	/** Get Department Name.
	  * Department Name
	  */
	public String getDepartmentName();

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

    /** Column name EmployeeImage_ID */
    public static final String COLUMNNAME_EmployeeImage_ID = "EmployeeImage_ID";

	/** Set Employee Image.
	  * Employee Image
	  */
	public void setEmployeeImage_ID (int EmployeeImage_ID);

	/** Get Employee Image.
	  * Employee Image
	  */
	public int getEmployeeImage_ID();

    /** Column name EmployeeStatus */
    public static final String COLUMNNAME_EmployeeStatus = "EmployeeStatus";

	/** Set Employee Status	  */
	public void setEmployeeStatus (String EmployeeStatus);

	/** Get Employee Status	  */
	public String getEmployeeStatus();

    /** Column name EmployeeTypeName */
    public static final String COLUMNNAME_EmployeeTypeName = "EmployeeTypeName";

	/** Set Employee Type Name.
	  * Employee Type Name
	  */
	public void setEmployeeTypeName (String EmployeeTypeName);

	/** Get Employee Type Name.
	  * Employee Type Name
	  */
	public String getEmployeeTypeName();

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

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

    /** Column name FathersName */
    public static final String COLUMNNAME_FathersName = "FathersName";

	/** Set Father's Name.
	  * Father's Name
	  */
	public void setFathersName (String FathersName);

	/** Get Father's Name.
	  * Father's Name
	  */
	public String getFathersName();

    /** Column name File_Directory */
    public static final String COLUMNNAME_File_Directory = "File_Directory";

	/** Set File_Directory	  */
	public void setFile_Directory (String File_Directory);

	/** Get File_Directory	  */
	public String getFile_Directory();

    /** Column name Gender */
    public static final String COLUMNNAME_Gender = "Gender";

	/** Set Gender	  */
	public void setGender (String Gender);

	/** Get Gender	  */
	public String getGender();

    /** Column name GradeName */
    public static final String COLUMNNAME_GradeName = "GradeName";

	/** Set Grade Name.
	  * Grade Name for Impor Employee
	  */
	public void setGradeName (String GradeName);

	/** Get Grade Name.
	  * Grade Name for Impor Employee
	  */
	public String getGradeName();

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

    /** Column name I_HR_Employee_ID */
    public static final String COLUMNNAME_I_HR_Employee_ID = "I_HR_Employee_ID";

	/** Set I_HR_Employee ID	  */
	public void setI_HR_Employee_ID (int I_HR_Employee_ID);

	/** Get I_HR_Employee ID	  */
	public int getI_HR_Employee_ID();

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

    /** Column name IdentificationMark */
    public static final String COLUMNNAME_IdentificationMark = "IdentificationMark";

	/** Set Identification Mark.
	  * Identification Mark
	  */
	public void setIdentificationMark (String IdentificationMark);

	/** Get Identification Mark.
	  * Identification Mark
	  */
	public String getIdentificationMark();

    /** Column name ImageURL */
    public static final String COLUMNNAME_ImageURL = "ImageURL";

	/** Set Image URL.
	  * URL of  image
	  */
	public void setImageURL (String ImageURL);

	/** Get Image URL.
	  * URL of  image
	  */
	public String getImageURL();

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

    /** Column name IsManager */
    public static final String COLUMNNAME_IsManager = "IsManager";

	/** Set Manager.
	  * Defines employee as manager
	  */
	public void setIsManager (boolean IsManager);

	/** Get Manager.
	  * Defines employee as manager
	  */
	public boolean isManager();

    /** Column name JobEducationName */
    public static final String COLUMNNAME_JobEducationName = "JobEducationName";

	/** Set Job Education Name.
	  * Job Education Name of an Employee
	  */
	public void setJobEducationName (String JobEducationName);

	/** Get Job Education Name.
	  * Job Education Name of an Employee
	  */
	public String getJobEducationName();

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

    /** Column name JobName */
    public static final String COLUMNNAME_JobName = "JobName";

	/** Set Job Name.
	  * Job Name
	  */
	public void setJobName (String JobName);

	/** Get Job Name.
	  * Job Name
	  */
	public String getJobName();

    /** Column name JobTypeName */
    public static final String COLUMNNAME_JobTypeName = "JobTypeName";

	/** Set Job Type Name.
	  * The Job Type Name for a Job Openings
	  */
	public void setJobTypeName (String JobTypeName);

	/** Get Job Type Name.
	  * The Job Type Name for a Job Openings
	  */
	public String getJobTypeName();

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

    /** Column name Logo_ID */
    public static final String COLUMNNAME_Logo_ID = "Logo_ID";

	/** Set Logo	  */
	public void setLogo_ID (int Logo_ID);

	/** Get Logo	  */
	public int getLogo_ID();

    /** Column name MaritalStatus */
    public static final String COLUMNNAME_MaritalStatus = "MaritalStatus";

	/** Set Marital Status	  */
	public void setMaritalStatus (String MaritalStatus);

	/** Get Marital Status	  */
	public String getMaritalStatus();

    /** Column name MarriageAnniversaryDate */
    public static final String COLUMNNAME_MarriageAnniversaryDate = "MarriageAnniversaryDate";

	/** Set Marriage Anniversary Date.
	  * Marriage Anniversary Date
	  */
	public void setMarriageAnniversaryDate (Timestamp MarriageAnniversaryDate);

	/** Get Marriage Anniversary Date.
	  * Marriage Anniversary Date
	  */
	public Timestamp getMarriageAnniversaryDate();

    /** Column name MonthlySalary */
    public static final String COLUMNNAME_MonthlySalary = "MonthlySalary";

	/** Set Monthly Salary.
	  * Monthly Salary
	  */
	public void setMonthlySalary (BigDecimal MonthlySalary);

	/** Get Monthly Salary.
	  * Monthly Salary
	  */
	public BigDecimal getMonthlySalary();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Name2 */
    public static final String COLUMNNAME_Name2 = "Name2";

	/** Set Name 2.
	  * Additional Name
	  */
	public void setName2 (String Name2);

	/** Get Name 2.
	  * Additional Name
	  */
	public String getName2();

    /** Column name NationalCode */
    public static final String COLUMNNAME_NationalCode = "NationalCode";

	/** Set National Code	  */
	public void setNationalCode (String NationalCode);

	/** Get National Code	  */
	public String getNationalCode();

    /** Column name Nationality_ID */
    public static final String COLUMNNAME_Nationality_ID = "Nationality_ID";

	/** Set Nationality.
	  * Nationality
	  */
	public void setNationality_ID (int Nationality_ID);

	/** Get Nationality.
	  * Nationality
	  */
	public int getNationality_ID();

	public org.compiere.model.I_C_Country getNationality() throws RuntimeException;

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

    /** Column name OrgValue */
    public static final String COLUMNNAME_OrgValue = "OrgValue";

	/** Set Org Key.
	  * Key of the Organization
	  */
	public void setOrgValue (String OrgValue);

	/** Get Org Key.
	  * Key of the Organization
	  */
	public String getOrgValue();

    /** Column name PartnersBirthDate */
    public static final String COLUMNNAME_PartnersBirthDate = "PartnersBirthDate";

	/** Set Partners Birth Date.
	  * Partners Birth Date
	  */
	public void setPartnersBirthDate (Timestamp PartnersBirthDate);

	/** Get Partners Birth Date.
	  * Partners Birth Date
	  */
	public Timestamp getPartnersBirthDate();

    /** Column name PartnersName */
    public static final String COLUMNNAME_PartnersName = "PartnersName";

	/** Set Partner's Name.
	  * Partner's Name
	  */
	public void setPartnersName (String PartnersName);

	/** Get Partner's Name.
	  * Partner's Name
	  */
	public String getPartnersName();

    /** Column name PaymentRule */
    public static final String COLUMNNAME_PaymentRule = "PaymentRule";

	/** Set Payment Rule.
	  * How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule);

	/** Get Payment Rule.
	  * How you pay the invoice
	  */
	public String getPaymentRule();

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

    /** Column name PlaceOfBirth */
    public static final String COLUMNNAME_PlaceOfBirth = "PlaceOfBirth";

	/** Set Place of Birth.
	  * Place of Birth
	  */
	public void setPlaceOfBirth (String PlaceOfBirth);

	/** Get Place of Birth.
	  * Place of Birth
	  */
	public String getPlaceOfBirth();

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

    /** Column name RaceName */
    public static final String COLUMNNAME_RaceName = "RaceName";

	/** Set Race Name.
	  * Race Name
	  */
	public void setRaceName (String RaceName);

	/** Get Race Name.
	  * Race Name
	  */
	public String getRaceName();

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

    /** Column name SSCode */
    public static final String COLUMNNAME_SSCode = "SSCode";

	/** Set Social Security Code	  */
	public void setSSCode (String SSCode);

	/** Get Social Security Code	  */
	public String getSSCode();

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

    /** Column name SalesRegionValue */
    public static final String COLUMNNAME_SalesRegionValue = "SalesRegionValue";

	/** Set Sales Region Value	  */
	public void setSalesRegionValue (String SalesRegionValue);

	/** Get Sales Region Value	  */
	public String getSalesRegionValue();

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

    /** Column name SkillTypeName */
    public static final String COLUMNNAME_SkillTypeName = "SkillTypeName";

	/** Set Skill Type Name.
	  * Skill Type Name
	  */
	public void setSkillTypeName (String SkillTypeName);

	/** Get Skill Type Name.
	  * Skill Type Name
	  */
	public String getSkillTypeName();

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

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

    /** Column name ThumbImage_ID */
    public static final String COLUMNNAME_ThumbImage_ID = "ThumbImage_ID";

	/** Set Thumb Image.
	  * Thumb Image
	  */
	public void setThumbImage_ID (int ThumbImage_ID);

	/** Get Thumb Image.
	  * Thumb Image
	  */
	public int getThumbImage_ID();

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
