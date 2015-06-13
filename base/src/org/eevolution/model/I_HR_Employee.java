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
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Employee
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_HR_Employee 
{

    /** TableName=HR_Employee */
    public static final String Table_Name = "HR_Employee";

    /** AD_Table_ID=53086 */
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

    /** Column name Gender */
    public static final String COLUMNNAME_Gender = "Gender";

	/** Set Gender	  */
	public void setGender (String Gender);

	/** Get Gender	  */
	public String getGender();

    /** Column name HR_Department_ID */
    public static final String COLUMNNAME_HR_Department_ID = "HR_Department_ID";

	/** Set Department.
	  * Department of the organization
	  */
	public void setHR_Department_ID (int HR_Department_ID);

	/** Get Department.
	  * Department of the organization
	  */
	public int getHR_Department_ID();

	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException;

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

    /** Column name HR_Job_ID */
    public static final String COLUMNNAME_HR_Job_ID = "HR_Job_ID";

	/** Set Payroll Job	  */
	public void setHR_Job_ID (int HR_Job_ID);

	/** Get Payroll Job	  */
	public int getHR_Job_ID();

	public org.eevolution.model.I_HR_Job getHR_Job() throws RuntimeException;

    /** Column name HR_Payroll_ID */
    public static final String COLUMNNAME_HR_Payroll_ID = "HR_Payroll_ID";

	/** Set Payroll.
	  * The Payroll definition allows to define all the payroll concepts , year and periods, to  calculate a payroll.
	  */
	public void setHR_Payroll_ID (int HR_Payroll_ID);

	/** Get Payroll.
	  * The Payroll definition allows to define all the payroll concepts , year and periods, to  calculate a payroll.
	  */
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

    /** Column name SSCode */
    public static final String COLUMNNAME_SSCode = "SSCode";

	/** Set Social Security Code	  */
	public void setSSCode (String SSCode);

	/** Get Social Security Code	  */
	public String getSSCode();

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
}
