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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_User
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_AD_User 
{

    /** TableName=AD_User */
    public static final String Table_Name = "AD_User";

    /** AD_Table_ID=114 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_EMailConfig_ID */
    public static final String COLUMNNAME_AD_EMailConfig_ID = "AD_EMailConfig_ID";

	/** Set EMail Configuration	  */
	public void setAD_EMailConfig_ID (int AD_EMailConfig_ID);

	/** Get EMail Configuration	  */
	public int getAD_EMailConfig_ID();

	public org.compiere.model.I_AD_EMailConfig getAD_EMailConfig() throws RuntimeException;

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

    /** Column name BPName */
    public static final String COLUMNNAME_BPName = "BPName";

	/** Set BP Name	  */
	public void setBPName (String BPName);

	/** Get BP Name	  */
	public String getBPName();

    /** Column name BP_Location_ID */
    public static final String COLUMNNAME_BP_Location_ID = "BP_Location_ID";

	/** Set BP Address.
	  * Address of the Business Partner
	  */
	public void setBP_Location_ID (int BP_Location_ID);

	/** Get BP Address.
	  * Address of the Business Partner
	  */
	public int getBP_Location_ID();

	public I_C_Location getBP_Location() throws RuntimeException;

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

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID();

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

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

    /** Column name C_Greeting_ID */
    public static final String COLUMNNAME_C_Greeting_ID = "C_Greeting_ID";

	/** Set Greeting.
	  * Greeting to print on correspondence
	  */
	public void setC_Greeting_ID (int C_Greeting_ID);

	/** Get Greeting.
	  * Greeting to print on correspondence
	  */
	public int getC_Greeting_ID();

	public org.compiere.model.I_C_Greeting getC_Greeting() throws RuntimeException;

    /** Column name C_Job_ID */
    public static final String COLUMNNAME_C_Job_ID = "C_Job_ID";

	/** Set Position.
	  * Job Position
	  */
	public void setC_Job_ID (int C_Job_ID);

	/** Get Position.
	  * Job Position
	  */
	public int getC_Job_ID();

	public org.compiere.model.I_C_Job getC_Job() throws RuntimeException;

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

	public org.compiere.model.I_C_Location getC_Location() throws RuntimeException;

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

    /** Column name ConnectionProfile */
    public static final String COLUMNNAME_ConnectionProfile = "ConnectionProfile";

	/** Set Connection Profile.
	  * How a Java Client connects to the server(s)
	  */
	public void setConnectionProfile (String ConnectionProfile);

	/** Get Connection Profile.
	  * How a Java Client connects to the server(s)
	  */
	public String getConnectionProfile();

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

    /** Column name EMailUser */
    public static final String COLUMNNAME_EMailUser = "EMailUser";

	/** Set EMail User ID.
	  * User Name (ID) in the Mail System
	  */
	public void setEMailUser (String EMailUser);

	/** Get EMail User ID.
	  * User Name (ID) in the Mail System
	  */
	public String getEMailUser();

    /** Column name EMailUserPW */
    public static final String COLUMNNAME_EMailUserPW = "EMailUserPW";

	/** Set EMail User Password.
	  * Password of your email user id
	  */
	public void setEMailUserPW (String EMailUserPW);

	/** Get EMail User Password.
	  * Password of your email user id
	  */
	public String getEMailUserPW();

    /** Column name EMailVerify */
    public static final String COLUMNNAME_EMailVerify = "EMailVerify";

	/** Set Verification Info.
	  * Verification information of EMail Address
	  */
	public void setEMailVerify (String EMailVerify);

	/** Get Verification Info.
	  * Verification information of EMail Address
	  */
	public String getEMailVerify();

    /** Column name EMailVerifyDate */
    public static final String COLUMNNAME_EMailVerifyDate = "EMailVerifyDate";

	/** Set EMail Verify.
	  * Date Email was verified
	  */
	public void setEMailVerifyDate (Timestamp EMailVerifyDate);

	/** Get EMail Verify.
	  * Date Email was verified
	  */
	public Timestamp getEMailVerifyDate();

    /** Column name Fax */
    public static final String COLUMNNAME_Fax = "Fax";

	/** Set Fax.
	  * Facsimile number
	  */
	public void setFax (String Fax);

	/** Get Fax.
	  * Facsimile number
	  */
	public String getFax();

    /** Column name HasRole */
    public static final String COLUMNNAME_HasRole = "HasRole";

	/** Set HasRole.
	  * Has Role Y/N
	  */
	public void setHasRole (String HasRole);

	/** Get HasRole.
	  * Has Role Y/N
	  */
	public String getHasRole();

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

    /** Column name IsFullBPAccess */
    public static final String COLUMNNAME_IsFullBPAccess = "IsFullBPAccess";

	/** Set Full BP Access.
	  * The user/contact has full access to Business Partner information and resources
	  */
	public void setIsFullBPAccess (boolean IsFullBPAccess);

	/** Get Full BP Access.
	  * The user/contact has full access to Business Partner information and resources
	  */
	public boolean isFullBPAccess();

    /** Column name IsInPayroll */
    public static final String COLUMNNAME_IsInPayroll = "IsInPayroll";

	/** Set Is In Payroll.
	  * Defined if any User Contact will be used for Calculate Payroll
	  */
	public void setIsInPayroll (boolean IsInPayroll);

	/** Get Is In Payroll.
	  * Defined if any User Contact will be used for Calculate Payroll
	  */
	public boolean isInPayroll();

    /** Column name IsInternalUser */
    public static final String COLUMNNAME_IsInternalUser = "IsInternalUser";

	/** Set Internal User.
	  * Is just for use internal
	  */
	public void setIsInternalUser (boolean IsInternalUser);

	/** Get Internal User.
	  * Is just for use internal
	  */
	public boolean isInternalUser();

    /** Column name IsLoginUser */
    public static final String COLUMNNAME_IsLoginUser = "IsLoginUser";

	/** Set Login User	  */
	public void setIsLoginUser (boolean IsLoginUser);

	/** Get Login User	  */
	public boolean isLoginUser();

    /** Column name IsProjectManager */
    public static final String COLUMNNAME_IsProjectManager = "IsProjectManager";

	/** Set Is Project Manager.
	  * Is Project Manager
	  */
	public void setIsProjectManager (boolean IsProjectManager);

	/** Get Is Project Manager.
	  * Is Project Manager
	  */
	public boolean isProjectManager();

    /** Column name IsProjectMember */
    public static final String COLUMNNAME_IsProjectMember = "IsProjectMember";

	/** Set Is Project Member.
	  * Is Project Member
	  */
	public void setIsProjectMember (boolean IsProjectMember);

	/** Get Is Project Member.
	  * Is Project Member
	  */
	public boolean isProjectMember();

    /** Column name IsSalesLead */
    public static final String COLUMNNAME_IsSalesLead = "IsSalesLead";

	/** Set Sales Lead.
	  * This contact is a sales lead
	  */
	public void setIsSalesLead (boolean IsSalesLead);

	/** Get Sales Lead.
	  * This contact is a sales lead
	  */
	public boolean isSalesLead();

    /** Column name IsWebstoreUser */
    public static final String COLUMNNAME_IsWebstoreUser = "IsWebstoreUser";

	/** Set Webstore User.
	  * Is a user for Webstore
	  */
	public void setIsWebstoreUser (boolean IsWebstoreUser);

	/** Get Webstore User.
	  * Is a user for Webstore
	  */
	public boolean isWebstoreUser();

    /** Column name LDAPUser */
    public static final String COLUMNNAME_LDAPUser = "LDAPUser";

	/** Set LDAP User Name.
	  * User Name used for authorization via LDAP (directory) services
	  */
	public void setLDAPUser (String LDAPUser);

	/** Get LDAP User Name.
	  * User Name used for authorization via LDAP (directory) services
	  */
	public String getLDAPUser();

    /** Column name LastContact */
    public static final String COLUMNNAME_LastContact = "LastContact";

	/** Set Last Contact.
	  * Date this individual was last contacted
	  */
	public void setLastContact (Timestamp LastContact);

	/** Get Last Contact.
	  * Date this individual was last contacted
	  */
	public Timestamp getLastContact();

    /** Column name LastResult */
    public static final String COLUMNNAME_LastResult = "LastResult";

	/** Set Last Result.
	  * Result of last contact
	  */
	public void setLastResult (String LastResult);

	/** Get Last Result.
	  * Result of last contact
	  */
	public String getLastResult();

    /** Column name LeadSource */
    public static final String COLUMNNAME_LeadSource = "LeadSource";

	/** Set Lead Source.
	  * The source of this lead/opportunity
	  */
	public void setLeadSource (String LeadSource);

	/** Get Lead Source.
	  * The source of this lead/opportunity
	  */
	public String getLeadSource();

    /** Column name LeadSourceDescription */
    public static final String COLUMNNAME_LeadSourceDescription = "LeadSourceDescription";

	/** Set Lead Source Description.
	  * Additional information on the source of this lead/opportunity
	  */
	public void setLeadSourceDescription (String LeadSourceDescription);

	/** Get Lead Source Description.
	  * Additional information on the source of this lead/opportunity
	  */
	public String getLeadSourceDescription();

    /** Column name LeadStatus */
    public static final String COLUMNNAME_LeadStatus = "LeadStatus";

	/** Set Lead Status.
	  * The status of this lead/opportunity in the sales cycle
	  */
	public void setLeadStatus (String LeadStatus);

	/** Get Lead Status.
	  * The status of this lead/opportunity in the sales cycle
	  */
	public String getLeadStatus();

    /** Column name LeadStatusDescription */
    public static final String COLUMNNAME_LeadStatusDescription = "LeadStatusDescription";

	/** Set Lead Status Description.
	  * Additional information on the status of this lead/opportunity
	  */
	public void setLeadStatusDescription (String LeadStatusDescription);

	/** Get Lead Status Description.
	  * Additional information on the status of this lead/opportunity
	  */
	public String getLeadStatusDescription();

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

    /** Column name NotificationType */
    public static final String COLUMNNAME_NotificationType = "NotificationType";

	/** Set Notification Type.
	  * Type of Notifications
	  */
	public void setNotificationType (String NotificationType);

	/** Get Notification Type.
	  * Type of Notifications
	  */
	public String getNotificationType();

    /** Column name Password */
    public static final String COLUMNNAME_Password = "Password";

	/** Set Password.
	  * Password of any length (case sensitive)
	  */
	public void setPassword (String Password);

	/** Get Password.
	  * Password of any length (case sensitive)
	  */
	public String getPassword();

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

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name RecentItemsMaxSaved */
    public static final String COLUMNNAME_RecentItemsMaxSaved = "RecentItemsMaxSaved";

	/** Set RecentItems Max Saved	  */
	public void setRecentItemsMaxSaved (int RecentItemsMaxSaved);

	/** Get RecentItems Max Saved	  */
	public int getRecentItemsMaxSaved();

    /** Column name RecentItemsMaxShown */
    public static final String COLUMNNAME_RecentItemsMaxShown = "RecentItemsMaxShown";

	/** Set RecentItems Max Shown	  */
	public void setRecentItemsMaxShown (int RecentItemsMaxShown);

	/** Get RecentItems Max Shown	  */
	public int getRecentItemsMaxShown();

    /** Column name SalesRep_ID */
    public static final String COLUMNNAME_SalesRep_ID = "SalesRep_ID";

	/** Set Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID);

	/** Get Sales Representative.
	  * Sales Representative or Company Agent
	  */
	public int getSalesRep_ID();

	public org.compiere.model.I_AD_User getSalesRep() throws RuntimeException;

    /** Column name Salt */
    public static final String COLUMNNAME_Salt = "Salt";

	/** Set Salt.
	  * Random data added to improve password hash effectiveness
	  */
	public void setSalt (String Salt);

	/** Get Salt.
	  * Random data added to improve password hash effectiveness
	  */
	public String getSalt();

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

    /** Column name Title */
    public static final String COLUMNNAME_Title = "Title";

	/** Set Title.
	  * Name this entity is referred to as
	  */
	public void setTitle (String Title);

	/** Get Title.
	  * Name this entity is referred to as
	  */
	public String getTitle();

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

    /** Column name UserPIN */
    public static final String COLUMNNAME_UserPIN = "UserPIN";

	/** Set User PIN	  */
	public void setUserPIN (String UserPIN);

	/** Get User PIN	  */
	public String getUserPIN();

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
}
