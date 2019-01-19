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

/** Generated Interface for HR_JobApplication
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_HR_JobApplication 
{

    /** TableName=HR_JobApplication */
    public static final String Table_Name = "HR_JobApplication";

    /** AD_Table_ID=53700 */
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

    /** Column name ApplicantsNo */
    public static final String COLUMNNAME_ApplicantsNo = "ApplicantsNo";

	/** Set Applicants No.
	  * Applicants No for this position
	  */
	public void setApplicantsNo (String ApplicantsNo);

	/** Get Applicants No.
	  * Applicants No for this position
	  */
	public String getApplicantsNo();

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

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address.
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address.
	  * Location or Address
	  */
	public int getC_Location_ID();

	public I_C_Location getC_Location() throws RuntimeException;

    /** Column name City */
    public static final String COLUMNNAME_City = "City";

	/** Set City.
	  * Identifies a City
	  */
	public void setCity (String City);

	/** Get City.
	  * Identifies a City
	  */
	public String getCity();

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

    /** Column name Company */
    public static final String COLUMNNAME_Company = "Company";

	/** Set Company.
	  * Previous working Company Name(Organization)
	  */
	public void setCompany (String Company);

	/** Get Company.
	  * Previous working Company Name(Organization)
	  */
	public String getCompany();

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

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name FatherName */
    public static final String COLUMNNAME_FatherName = "FatherName";

	/** Set Father Name.
	  * Father Name of a person
	  */
	public void setFatherName (String FatherName);

	/** Get Father Name.
	  * Father Name of a person
	  */
	public String getFatherName();

    /** Column name FirstName */
    public static final String COLUMNNAME_FirstName = "FirstName";

	/** Set First Name.
	  * First Name of a person
	  */
	public void setFirstName (String FirstName);

	/** Get First Name.
	  * First Name of a person
	  */
	public String getFirstName();

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

    /** Column name HR_JobApplication_ID */
    public static final String COLUMNNAME_HR_JobApplication_ID = "HR_JobApplication_ID";

	/** Set Job Application.
	  * Job Application
	  */
	public void setHR_JobApplication_ID (int HR_JobApplication_ID);

	/** Get Job Application.
	  * Job Application
	  */
	public int getHR_JobApplication_ID();

    /** Column name HR_JobOpening_ID */
    public static final String COLUMNNAME_HR_JobOpening_ID = "HR_JobOpening_ID";

	/** Set Job Openings .
	  * Job Openings for Recruitment Management
	  */
	public void setHR_JobOpening_ID (int HR_JobOpening_ID);

	/** Get Job Openings .
	  * Job Openings for Recruitment Management
	  */
	public int getHR_JobOpening_ID();

	public org.eevolution.model.I_HR_JobOpening getHR_JobOpening() throws RuntimeException;

    /** Column name HighestEducation */
    public static final String COLUMNNAME_HighestEducation = "HighestEducation";

	/** Set Highest Education.
	  * Highest Education for this position
	  */
	public void setHighestEducation (String HighestEducation);

	/** Get Highest Education.
	  * Highest Education for this position
	  */
	public String getHighestEducation();

    /** Column name IdentityProof */
    public static final String COLUMNNAME_IdentityProof = "IdentityProof";

	/** Set Identity Proof.
	  * Defines the type of Identity Proof
	  */
	public void setIdentityProof (String IdentityProof);

	/** Get Identity Proof.
	  * Defines the type of Identity Proof
	  */
	public String getIdentityProof();

    /** Column name IdentityProofNo */
    public static final String COLUMNNAME_IdentityProofNo = "IdentityProofNo";

	/** Set Identity Proof No.
	  * Identity Proof No of applicant
	  */
	public void setIdentityProofNo (String IdentityProofNo);

	/** Get Identity Proof No.
	  * Identity Proof No of applicant
	  */
	public String getIdentityProofNo();

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

    /** Column name IsFormerEmployee */
    public static final String COLUMNNAME_IsFormerEmployee = "IsFormerEmployee";

	/** Set Are you a former employee?.
	  * Are you a former employee?
	  */
	public void setIsFormerEmployee (boolean IsFormerEmployee);

	/** Get Are you a former employee?.
	  * Are you a former employee?
	  */
	public boolean isFormerEmployee();

    /** Column name IsInterviewOver */
    public static final String COLUMNNAME_IsInterviewOver = "IsInterviewOver";

	/** Set Interview Over.
	  * Interview Over Indicates whether an applicant will be interviewed
	  */
	public void setIsInterviewOver (boolean IsInterviewOver);

	/** Get Interview Over.
	  * Interview Over Indicates whether an applicant will be interviewed
	  */
	public boolean isInterviewOver();

    /** Column name IsSelected */
    public static final String COLUMNNAME_IsSelected = "IsSelected";

	/** Set Selected	  */
	public void setIsSelected (boolean IsSelected);

	/** Get Selected	  */
	public boolean isSelected();

    /** Column name IsWillingToRelocate */
    public static final String COLUMNNAME_IsWillingToRelocate = "IsWillingToRelocate";

	/** Set Is Willing to Relocate?.
	  * Is Willing to Relocate?
	  */
	public void setIsWillingToRelocate (boolean IsWillingToRelocate);

	/** Get Is Willing to Relocate?.
	  * Is Willing to Relocate?
	  */
	public boolean isWillingToRelocate();

    /** Column name IsWillingToTravel */
    public static final String COLUMNNAME_IsWillingToTravel = "IsWillingToTravel";

	/** Set Is Willing to Travel?.
	  * Is Willing to Travel?
	  */
	public void setIsWillingToTravel (boolean IsWillingToTravel);

	/** Get Is Willing to Travel?.
	  * Is Willing to Travel?
	  */
	public boolean isWillingToTravel();

    /** Column name JobApplicationDate */
    public static final String COLUMNNAME_JobApplicationDate = "JobApplicationDate";

	/** Set Job Application Date.
	  * Job Application Date
	  */
	public void setJobApplicationDate (Timestamp JobApplicationDate);

	/** Get Job Application Date.
	  * Job Application Date
	  */
	public Timestamp getJobApplicationDate();

    /** Column name LastName */
    public static final String COLUMNNAME_LastName = "LastName";

	/** Set Last Name.
	  * Last Name of a person
	  */
	public void setLastName (String LastName);

	/** Get Last Name.
	  * Last Name of a person
	  */
	public String getLastName();

    /** Column name MiddleName */
    public static final String COLUMNNAME_MiddleName = "MiddleName";

	/** Set Middle Name.
	  * Middle Name of a person
	  */
	public void setMiddleName (String MiddleName);

	/** Get Middle Name.
	  * Middle Name of a person
	  */
	public String getMiddleName();

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Phone.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Phone.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name Phone2 */
    public static final String COLUMNNAME_Phone2 = "Phone2";

	/** Set 2nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2);

	/** Get 2nd Phone.
	  * Identifies an alternate telephone number.
	  */
	public String getPhone2();

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

    /** Column name RecruitedEmployee_ID */
    public static final String COLUMNNAME_RecruitedEmployee_ID = "RecruitedEmployee_ID";

	/** Set Recruited Employee.
	  * Recruited Employee for this Job Application
	  */
	public void setRecruitedEmployee_ID (int RecruitedEmployee_ID);

	/** Get Recruited Employee.
	  * Recruited Employee for this Job Application
	  */
	public int getRecruitedEmployee_ID();

	public org.compiere.model.I_C_BPartner getRecruitedEmployee() throws RuntimeException;

    /** Column name Ref_BPartner_ID */
    public static final String COLUMNNAME_Ref_BPartner_ID = "Ref_BPartner_ID";

	/** Set Employee Reference.
	  * Employee Reference
	  */
	public void setRef_BPartner_ID (int Ref_BPartner_ID);

	/** Get Employee Reference.
	  * Employee Reference
	  */
	public int getRef_BPartner_ID();

	public org.compiere.model.I_C_BPartner getRef_BPartner() throws RuntimeException;

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

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (String Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public String getStatus();

    /** Column name TerminationDate */
    public static final String COLUMNNAME_TerminationDate = "TerminationDate";

	/** Set Termination Date.
	  * Previous Termination Date
	  */
	public void setTerminationDate (Timestamp TerminationDate);

	/** Get Termination Date.
	  * Previous Termination Date
	  */
	public Timestamp getTerminationDate();

    /** Column name TotalRelevantExperience */
    public static final String COLUMNNAME_TotalRelevantExperience = "TotalRelevantExperience";

	/** Set Total Relevant Experience.
	  * Total Relevant Experience
	  */
	public void setTotalRelevantExperience (String TotalRelevantExperience);

	/** Get Total Relevant Experience.
	  * Total Relevant Experience
	  */
	public String getTotalRelevantExperience();

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

    /** Column name YearOfPassing */
    public static final String COLUMNNAME_YearOfPassing = "YearOfPassing";

	/** Set Year of Passing.
	  * Year of Passing
	  */
	public void setYearOfPassing (String YearOfPassing);

	/** Get Year of Passing.
	  * Year of Passing
	  */
	public String getYearOfPassing();
}
