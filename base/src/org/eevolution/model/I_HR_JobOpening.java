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

/** Generated Interface for HR_JobOpening
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_HR_JobOpening 
{

    /** TableName=HR_JobOpening */
    public static final String Table_Name = "HR_JobOpening";

    /** AD_Table_ID=53707 */
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

    /** Column name DateDoc */
    public static final String COLUMNNAME_DateDoc = "DateDoc";

	/** Set Document Date.
	  * Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc);

	/** Get Document Date.
	  * Date of the Document
	  */
	public Timestamp getDateDoc();

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

    /** Column name DocAction */
    public static final String COLUMNNAME_DocAction = "DocAction";

	/** Set Document Action.
	  * The targeted status of the document
	  */
	public void setDocAction (String DocAction);

	/** Get Document Action.
	  * The targeted status of the document
	  */
	public String getDocAction();

    /** Column name DocStatus */
    public static final String COLUMNNAME_DocStatus = "DocStatus";

	/** Set Document Status.
	  * The current status of the document
	  */
	public void setDocStatus (String DocStatus);

	/** Get Document Status.
	  * The current status of the document
	  */
	public String getDocStatus();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

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

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

    /** Column name IsHRApproved */
    public static final String COLUMNNAME_IsHRApproved = "IsHRApproved";

	/** Set HR Approved.
	  * HR Approved
	  */
	public void setIsHRApproved (boolean IsHRApproved);

	/** Get HR Approved.
	  * HR Approved
	  */
	public boolean isHRApproved();

    /** Column name IsManagerApproved */
    public static final String COLUMNNAME_IsManagerApproved = "IsManagerApproved";

	/** Set Manager Approved.
	  * Manager Approved indicates if this document was approved by Manager.
	  */
	public void setIsManagerApproved (boolean IsManagerApproved);

	/** Get Manager Approved.
	  * Manager Approved indicates if this document was approved by Manager.
	  */
	public boolean isManagerApproved();

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

    /** Column name OpenPositions */
    public static final String COLUMNNAME_OpenPositions = "OpenPositions";

	/** Set Open Positions.
	  * Number of places for this position.
	  */
	public void setOpenPositions (int OpenPositions);

	/** Get Open Positions.
	  * Number of places for this position.
	  */
	public int getOpenPositions();

    /** Column name PrimarySkills */
    public static final String COLUMNNAME_PrimarySkills = "PrimarySkills";

	/** Set Primary Skills.
	  * Define of Primary Skills for this position
	  */
	public void setPrimarySkills (String PrimarySkills);

	/** Get Primary Skills.
	  * Define of Primary Skills for this position
	  */
	public String getPrimarySkills();

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

    /** Column name PubDate */
    public static final String COLUMNNAME_PubDate = "PubDate";

	/** Set Publication Date.
	  * Date on which this article will / should get published
	  */
	public void setPubDate (Timestamp PubDate);

	/** Get Publication Date.
	  * Date on which this article will / should get published
	  */
	public Timestamp getPubDate();

    /** Column name Responsibilities */
    public static final String COLUMNNAME_Responsibilities = "Responsibilities";

	/** Set Responsibilities.
	  * Role and responsibilities of the position.
	  */
	public void setResponsibilities (String Responsibilities);

	/** Get Responsibilities.
	  * Role and responsibilities of the position.
	  */
	public String getResponsibilities();

    /** Column name SecondarySkills */
    public static final String COLUMNNAME_SecondarySkills = "SecondarySkills";

	/** Set Secondary Skills.
	  * Define of Secondary Skills for this position
	  */
	public void setSecondarySkills (String SecondarySkills);

	/** Get Secondary Skills.
	  * Define of Secondary Skills for this position
	  */
	public String getSecondarySkills();

    /** Column name Supervisor_ID */
    public static final String COLUMNNAME_Supervisor_ID = "Supervisor_ID";

	/** Set Supervisor.
	  * Supervisor for this user/organization - used for escalation and approval
	  */
	public void setSupervisor_ID (int Supervisor_ID);

	/** Get Supervisor.
	  * Supervisor for this user/organization - used for escalation and approval
	  */
	public int getSupervisor_ID();

	public org.compiere.model.I_AD_User getSupervisor() throws RuntimeException;

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

    /** Column name WFState */
    public static final String COLUMNNAME_WFState = "WFState";

	/** Set Workflow State.
	  * State of the execution of the workflow
	  */
	public void setWFState (String WFState);

	/** Get Workflow State.
	  * State of the execution of the workflow
	  */
	public String getWFState();
}
