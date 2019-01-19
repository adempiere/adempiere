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
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_User
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_AD_User extends PO implements I_AD_User, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_AD_User (Properties ctx, int AD_User_ID, String trxName)
    {
      super (ctx, AD_User_ID, trxName);
      /** if (AD_User_ID == 0)
        {
			setAD_User_ID (0);
			setIsFullBPAccess (true);
// Y
			setIsInPayroll (false);
// N
			setIsSalesLead (false);
// N
			setName (null);
			setNotificationType (null);
// E
        } */
    }

    /** Load Constructor */
    public X_AD_User (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 7 - System - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_AD_User[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_EMailConfig getAD_EMailConfig() throws RuntimeException
    {
		return (org.compiere.model.I_AD_EMailConfig)MTable.get(getCtx(), org.compiere.model.I_AD_EMailConfig.Table_Name)
			.getPO(getAD_EMailConfig_ID(), get_TrxName());	}

	/** Set EMail Configuration.
		@param AD_EMailConfig_ID EMail Configuration	  */
	public void setAD_EMailConfig_ID (int AD_EMailConfig_ID)
	{
		if (AD_EMailConfig_ID < 1) 
			set_Value (COLUMNNAME_AD_EMailConfig_ID, null);
		else 
			set_Value (COLUMNNAME_AD_EMailConfig_ID, Integer.valueOf(AD_EMailConfig_ID));
	}

	/** Get EMail Configuration.
		@return EMail Configuration	  */
	public int getAD_EMailConfig_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_EMailConfig_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
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

	/** Set BP Name.
		@param BPName BP Name	  */
	public void setBPName (String BPName)
	{
		set_Value (COLUMNNAME_BPName, BPName);
	}

	/** Get BP Name.
		@return BP Name	  */
	public String getBPName () 
	{
		return (String)get_Value(COLUMNNAME_BPName);
	}

	public I_C_Location getBP_Location() throws RuntimeException
    {
		return (I_C_Location)MTable.get(getCtx(), I_C_Location.Table_Name)
			.getPO(getBP_Location_ID(), get_TrxName());	}

	/** Set BP Address.
		@param BP_Location_ID 
		Address of the Business Partner
	  */
	public void setBP_Location_ID (int BP_Location_ID)
	{
		if (BP_Location_ID < 1) 
			set_Value (COLUMNNAME_BP_Location_ID, null);
		else 
			set_Value (COLUMNNAME_BP_Location_ID, Integer.valueOf(BP_Location_ID));
	}

	/** Get BP Address.
		@return Address of the Business Partner
	  */
	public int getBP_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BP_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner_Location)MTable.get(getCtx(), org.compiere.model.I_C_BPartner_Location.Table_Name)
			.getPO(getC_BPartner_Location_ID(), get_TrxName());	}

	/** Set Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
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

	public org.compiere.model.I_C_Greeting getC_Greeting() throws RuntimeException
    {
		return (org.compiere.model.I_C_Greeting)MTable.get(getCtx(), org.compiere.model.I_C_Greeting.Table_Name)
			.getPO(getC_Greeting_ID(), get_TrxName());	}

	/** Set Greeting.
		@param C_Greeting_ID 
		Greeting to print on correspondence
	  */
	public void setC_Greeting_ID (int C_Greeting_ID)
	{
		if (C_Greeting_ID < 1) 
			set_Value (COLUMNNAME_C_Greeting_ID, null);
		else 
			set_Value (COLUMNNAME_C_Greeting_ID, Integer.valueOf(C_Greeting_ID));
	}

	/** Get Greeting.
		@return Greeting to print on correspondence
	  */
	public int getC_Greeting_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Greeting_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Job getC_Job() throws RuntimeException
    {
		return (org.compiere.model.I_C_Job)MTable.get(getCtx(), org.compiere.model.I_C_Job.Table_Name)
			.getPO(getC_Job_ID(), get_TrxName());	}

	/** Set Position.
		@param C_Job_ID 
		Job Position
	  */
	public void setC_Job_ID (int C_Job_ID)
	{
		if (C_Job_ID < 1) 
			set_Value (COLUMNNAME_C_Job_ID, null);
		else 
			set_Value (COLUMNNAME_C_Job_ID, Integer.valueOf(C_Job_ID));
	}

	/** Get Position.
		@return Job Position
	  */
	public int getC_Job_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Job_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Location getC_Location() throws RuntimeException
    {
		return (org.compiere.model.I_C_Location)MTable.get(getCtx(), org.compiere.model.I_C_Location.Table_Name)
			.getPO(getC_Location_ID(), get_TrxName());	}

	/** Set Address.
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address.
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** ConnectionProfile AD_Reference_ID=364 */
	public static final int CONNECTIONPROFILE_AD_Reference_ID=364;
	/** LAN = L */
	public static final String CONNECTIONPROFILE_LAN = "L";
	/** Terminal Server = T */
	public static final String CONNECTIONPROFILE_TerminalServer = "T";
	/** VPN = V */
	public static final String CONNECTIONPROFILE_VPN = "V";
	/** WAN = W */
	public static final String CONNECTIONPROFILE_WAN = "W";
	/** Set Connection Profile.
		@param ConnectionProfile 
		How a Java Client connects to the server(s)
	  */
	public void setConnectionProfile (String ConnectionProfile)
	{

		set_Value (COLUMNNAME_ConnectionProfile, ConnectionProfile);
	}

	/** Get Connection Profile.
		@return How a Java Client connects to the server(s)
	  */
	public String getConnectionProfile () 
	{
		return (String)get_Value(COLUMNNAME_ConnectionProfile);
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

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set EMail User ID.
		@param EMailUser 
		User Name (ID) in the Mail System
	  */
	public void setEMailUser (String EMailUser)
	{
		set_Value (COLUMNNAME_EMailUser, EMailUser);
	}

	/** Get EMail User ID.
		@return User Name (ID) in the Mail System
	  */
	public String getEMailUser () 
	{
		return (String)get_Value(COLUMNNAME_EMailUser);
	}

	/** Set EMail User Password.
		@param EMailUserPW 
		Password of your email user id
	  */
	public void setEMailUserPW (String EMailUserPW)
	{
		set_Value (COLUMNNAME_EMailUserPW, EMailUserPW);
	}

	/** Get EMail User Password.
		@return Password of your email user id
	  */
	public String getEMailUserPW () 
	{
		return (String)get_Value(COLUMNNAME_EMailUserPW);
	}

	/** Set Verification Info.
		@param EMailVerify 
		Verification information of EMail Address
	  */
	public void setEMailVerify (String EMailVerify)
	{
		set_ValueNoCheck (COLUMNNAME_EMailVerify, EMailVerify);
	}

	/** Get Verification Info.
		@return Verification information of EMail Address
	  */
	public String getEMailVerify () 
	{
		return (String)get_Value(COLUMNNAME_EMailVerify);
	}

	/** Set EMail Verify.
		@param EMailVerifyDate 
		Date Email was verified
	  */
	public void setEMailVerifyDate (Timestamp EMailVerifyDate)
	{
		set_ValueNoCheck (COLUMNNAME_EMailVerifyDate, EMailVerifyDate);
	}

	/** Get EMail Verify.
		@return Date Email was verified
	  */
	public Timestamp getEMailVerifyDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EMailVerifyDate);
	}

	/** Set Fax.
		@param Fax 
		Facsimile number
	  */
	public void setFax (String Fax)
	{
		set_Value (COLUMNNAME_Fax, Fax);
	}

	/** Get Fax.
		@return Facsimile number
	  */
	public String getFax () 
	{
		return (String)get_Value(COLUMNNAME_Fax);
	}

	/** Set HasRole.
		@param HasRole 
		Has Role Y/N
	  */
	public void setHasRole (String HasRole)
	{
		throw new IllegalArgumentException ("HasRole is virtual column");	}

	/** Get HasRole.
		@return Has Role Y/N
	  */
	public String getHasRole () 
	{
		return (String)get_Value(COLUMNNAME_HasRole);
	}

	/** Set Full BP Access.
		@param IsFullBPAccess 
		The user/contact has full access to Business Partner information and resources
	  */
	public void setIsFullBPAccess (boolean IsFullBPAccess)
	{
		set_Value (COLUMNNAME_IsFullBPAccess, Boolean.valueOf(IsFullBPAccess));
	}

	/** Get Full BP Access.
		@return The user/contact has full access to Business Partner information and resources
	  */
	public boolean isFullBPAccess () 
	{
		Object oo = get_Value(COLUMNNAME_IsFullBPAccess);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is In Payroll.
		@param IsInPayroll 
		Defined if any User Contact will be used for Calculate Payroll
	  */
	public void setIsInPayroll (boolean IsInPayroll)
	{
		set_Value (COLUMNNAME_IsInPayroll, Boolean.valueOf(IsInPayroll));
	}

	/** Get Is In Payroll.
		@return Defined if any User Contact will be used for Calculate Payroll
	  */
	public boolean isInPayroll () 
	{
		Object oo = get_Value(COLUMNNAME_IsInPayroll);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Internal User.
		@param IsInternalUser 
		Is just for use internal
	  */
	public void setIsInternalUser (boolean IsInternalUser)
	{
		set_Value (COLUMNNAME_IsInternalUser, Boolean.valueOf(IsInternalUser));
	}

	/** Get Internal User.
		@return Is just for use internal
	  */
	public boolean isInternalUser () 
	{
		Object oo = get_Value(COLUMNNAME_IsInternalUser);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Login User.
		@param IsLoginUser Login User	  */
	public void setIsLoginUser (boolean IsLoginUser)
	{
		set_Value (COLUMNNAME_IsLoginUser, Boolean.valueOf(IsLoginUser));
	}

	/** Get Login User.
		@return Login User	  */
	public boolean isLoginUser () 
	{
		Object oo = get_Value(COLUMNNAME_IsLoginUser);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Project Manager.
		@param IsProjectManager 
		Is Project Manager
	  */
	public void setIsProjectManager (boolean IsProjectManager)
	{
		set_Value (COLUMNNAME_IsProjectManager, Boolean.valueOf(IsProjectManager));
	}

	/** Get Is Project Manager.
		@return Is Project Manager
	  */
	public boolean isProjectManager () 
	{
		Object oo = get_Value(COLUMNNAME_IsProjectManager);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Project Member.
		@param IsProjectMember 
		Is Project Member
	  */
	public void setIsProjectMember (boolean IsProjectMember)
	{
		set_Value (COLUMNNAME_IsProjectMember, Boolean.valueOf(IsProjectMember));
	}

	/** Get Is Project Member.
		@return Is Project Member
	  */
	public boolean isProjectMember () 
	{
		Object oo = get_Value(COLUMNNAME_IsProjectMember);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Sales Lead.
		@param IsSalesLead 
		This contact is a sales lead
	  */
	public void setIsSalesLead (boolean IsSalesLead)
	{
		set_Value (COLUMNNAME_IsSalesLead, Boolean.valueOf(IsSalesLead));
	}

	/** Get Sales Lead.
		@return This contact is a sales lead
	  */
	public boolean isSalesLead () 
	{
		Object oo = get_Value(COLUMNNAME_IsSalesLead);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Webstore User.
		@param IsWebstoreUser 
		Is a user for Webstore
	  */
	public void setIsWebstoreUser (boolean IsWebstoreUser)
	{
		set_Value (COLUMNNAME_IsWebstoreUser, Boolean.valueOf(IsWebstoreUser));
	}

	/** Get Webstore User.
		@return Is a user for Webstore
	  */
	public boolean isWebstoreUser () 
	{
		Object oo = get_Value(COLUMNNAME_IsWebstoreUser);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set LDAP User Name.
		@param LDAPUser 
		User Name used for authorization via LDAP (directory) services
	  */
	public void setLDAPUser (String LDAPUser)
	{
		set_Value (COLUMNNAME_LDAPUser, LDAPUser);
	}

	/** Get LDAP User Name.
		@return User Name used for authorization via LDAP (directory) services
	  */
	public String getLDAPUser () 
	{
		return (String)get_Value(COLUMNNAME_LDAPUser);
	}

	/** Set Last Contact.
		@param LastContact 
		Date this individual was last contacted
	  */
	public void setLastContact (Timestamp LastContact)
	{
		set_Value (COLUMNNAME_LastContact, LastContact);
	}

	/** Get Last Contact.
		@return Date this individual was last contacted
	  */
	public Timestamp getLastContact () 
	{
		return (Timestamp)get_Value(COLUMNNAME_LastContact);
	}

	/** Set Last Result.
		@param LastResult 
		Result of last contact
	  */
	public void setLastResult (String LastResult)
	{
		set_Value (COLUMNNAME_LastResult, LastResult);
	}

	/** Get Last Result.
		@return Result of last contact
	  */
	public String getLastResult () 
	{
		return (String)get_Value(COLUMNNAME_LastResult);
	}

	/** LeadSource AD_Reference_ID=53415 */
	public static final int LEADSOURCE_AD_Reference_ID=53415;
	/** Cold Call = CC */
	public static final String LEADSOURCE_ColdCall = "CC";
	/** Existing Customer = EC */
	public static final String LEADSOURCE_ExistingCustomer = "EC";
	/** Employee = EM */
	public static final String LEADSOURCE_Employee = "EM";
	/** Partner = PT */
	public static final String LEADSOURCE_Partner = "PT";
	/** Conference = CN */
	public static final String LEADSOURCE_Conference = "CN";
	/** Trade Show = TS */
	public static final String LEADSOURCE_TradeShow = "TS";
	/** Web Site = WS */
	public static final String LEADSOURCE_WebSite = "WS";
	/** Word of Mouth = WM */
	public static final String LEADSOURCE_WordOfMouth = "WM";
	/** Email = EL */
	public static final String LEADSOURCE_Email = "EL";
	/** Set Lead Source.
		@param LeadSource 
		The source of this lead/opportunity
	  */
	public void setLeadSource (String LeadSource)
	{

		set_Value (COLUMNNAME_LeadSource, LeadSource);
	}

	/** Get Lead Source.
		@return The source of this lead/opportunity
	  */
	public String getLeadSource () 
	{
		return (String)get_Value(COLUMNNAME_LeadSource);
	}

	/** Set Lead Source Description.
		@param LeadSourceDescription 
		Additional information on the source of this lead/opportunity
	  */
	public void setLeadSourceDescription (String LeadSourceDescription)
	{
		set_Value (COLUMNNAME_LeadSourceDescription, LeadSourceDescription);
	}

	/** Get Lead Source Description.
		@return Additional information on the source of this lead/opportunity
	  */
	public String getLeadSourceDescription () 
	{
		return (String)get_Value(COLUMNNAME_LeadSourceDescription);
	}

	/** LeadStatus AD_Reference_ID=53416 */
	public static final int LEADSTATUS_AD_Reference_ID=53416;
	/** New = N */
	public static final String LEADSTATUS_New = "N";
	/** Working = W */
	public static final String LEADSTATUS_Working = "W";
	/** Expired = E */
	public static final String LEADSTATUS_Expired = "E";
	/** Recycled = R */
	public static final String LEADSTATUS_Recycled = "R";
	/** Converted = C */
	public static final String LEADSTATUS_Converted = "C";
	/** Set Lead Status.
		@param LeadStatus 
		The status of this lead/opportunity in the sales cycle
	  */
	public void setLeadStatus (String LeadStatus)
	{

		set_Value (COLUMNNAME_LeadStatus, LeadStatus);
	}

	/** Get Lead Status.
		@return The status of this lead/opportunity in the sales cycle
	  */
	public String getLeadStatus () 
	{
		return (String)get_Value(COLUMNNAME_LeadStatus);
	}

	/** Set Lead Status Description.
		@param LeadStatusDescription 
		Additional information on the status of this lead/opportunity
	  */
	public void setLeadStatusDescription (String LeadStatusDescription)
	{
		set_Value (COLUMNNAME_LeadStatusDescription, LeadStatusDescription);
	}

	/** Get Lead Status Description.
		@return Additional information on the status of this lead/opportunity
	  */
	public String getLeadStatusDescription () 
	{
		return (String)get_Value(COLUMNNAME_LeadStatusDescription);
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

	/** NotificationType AD_Reference_ID=344 */
	public static final int NOTIFICATIONTYPE_AD_Reference_ID=344;
	/** EMail = E */
	public static final String NOTIFICATIONTYPE_EMail = "E";
	/** Notice = N */
	public static final String NOTIFICATIONTYPE_Notice = "N";
	/** None = X */
	public static final String NOTIFICATIONTYPE_None = "X";
	/** EMail+Notice = B */
	public static final String NOTIFICATIONTYPE_EMailPlusNotice = "B";
	/** Set Notification Type.
		@param NotificationType 
		Type of Notifications
	  */
	public void setNotificationType (String NotificationType)
	{

		set_Value (COLUMNNAME_NotificationType, NotificationType);
	}

	/** Get Notification Type.
		@return Type of Notifications
	  */
	public String getNotificationType () 
	{
		return (String)get_Value(COLUMNNAME_NotificationType);
	}

	/** Set Password.
		@param Password 
		Password of any length (case sensitive)
	  */
	public void setPassword (String Password)
	{
		set_Value (COLUMNNAME_Password, Password);
	}

	/** Get Password.
		@return Password of any length (case sensitive)
	  */
	public String getPassword () 
	{
		return (String)get_Value(COLUMNNAME_Password);
	}

	/** Set Phone.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (String Phone)
	{
		set_Value (COLUMNNAME_Phone, Phone);
	}

	/** Get Phone.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}

	/** Set 2nd Phone.
		@param Phone2 
		Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2)
	{
		set_Value (COLUMNNAME_Phone2, Phone2);
	}

	/** Get 2nd Phone.
		@return Identifies an alternate telephone number.
	  */
	public String getPhone2 () 
	{
		return (String)get_Value(COLUMNNAME_Phone2);
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

	/** Set RecentItems Max Saved.
		@param RecentItemsMaxSaved RecentItems Max Saved	  */
	public void setRecentItemsMaxSaved (int RecentItemsMaxSaved)
	{
		set_Value (COLUMNNAME_RecentItemsMaxSaved, Integer.valueOf(RecentItemsMaxSaved));
	}

	/** Get RecentItems Max Saved.
		@return RecentItems Max Saved	  */
	public int getRecentItemsMaxSaved () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RecentItemsMaxSaved);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set RecentItems Max Shown.
		@param RecentItemsMaxShown RecentItems Max Shown	  */
	public void setRecentItemsMaxShown (int RecentItemsMaxShown)
	{
		set_Value (COLUMNNAME_RecentItemsMaxShown, Integer.valueOf(RecentItemsMaxShown));
	}

	/** Get RecentItems Max Shown.
		@return RecentItems Max Shown	  */
	public int getRecentItemsMaxShown () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RecentItemsMaxShown);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_User getSalesRep() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getSalesRep_ID(), get_TrxName());	}

	/** Set Sales Representative.
		@param SalesRep_ID 
		Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID)
	{
		if (SalesRep_ID < 1) 
			set_Value (COLUMNNAME_SalesRep_ID, null);
		else 
			set_Value (COLUMNNAME_SalesRep_ID, Integer.valueOf(SalesRep_ID));
	}

	/** Get Sales Representative.
		@return Sales Representative or Company Agent
	  */
	public int getSalesRep_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesRep_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Salt.
		@param Salt 
		Random data added to improve password hash effectiveness
	  */
	public void setSalt (String Salt)
	{
		set_ValueNoCheck (COLUMNNAME_Salt, Salt);
	}

	/** Get Salt.
		@return Random data added to improve password hash effectiveness
	  */
	public String getSalt () 
	{
		return (String)get_Value(COLUMNNAME_Salt);
	}

	public org.compiere.model.I_AD_User getSupervisor() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getSupervisor_ID(), get_TrxName());	}

	/** Set Supervisor.
		@param Supervisor_ID 
		Supervisor for this user/organization - used for escalation and approval
	  */
	public void setSupervisor_ID (int Supervisor_ID)
	{
		if (Supervisor_ID < 1) 
			set_Value (COLUMNNAME_Supervisor_ID, null);
		else 
			set_Value (COLUMNNAME_Supervisor_ID, Integer.valueOf(Supervisor_ID));
	}

	/** Get Supervisor.
		@return Supervisor for this user/organization - used for escalation and approval
	  */
	public int getSupervisor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Supervisor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Title.
		@param Title 
		Name this entity is referred to as
	  */
	public void setTitle (String Title)
	{
		set_Value (COLUMNNAME_Title, Title);
	}

	/** Get Title.
		@return Name this entity is referred to as
	  */
	public String getTitle () 
	{
		return (String)get_Value(COLUMNNAME_Title);
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

	/** Set User PIN.
		@param UserPIN User PIN	  */
	public void setUserPIN (String UserPIN)
	{
		set_Value (COLUMNNAME_UserPIN, UserPIN);
	}

	/** Get User PIN.
		@return User PIN	  */
	public String getUserPIN () 
	{
		return (String)get_Value(COLUMNNAME_UserPIN);
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
}