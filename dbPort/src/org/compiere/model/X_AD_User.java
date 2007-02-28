/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

/** Generated Model - DO NOT CHANGE */
import java.util.*;
import java.sql.*;
import java.math.*;
import org.compiere.util.*;
/** Generated Model for AD_User
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_AD_User extends PO
{
/** Standard Constructor
@param ctx context
@param AD_User_ID id
@param trxName transaction
*/
public X_AD_User (Properties ctx, int AD_User_ID, String trxName)
{
super (ctx, AD_User_ID, trxName);
/** if (AD_User_ID == 0)
{
setAD_User_ID (0);
setIsFullBPAccess (true);	// Y
setName (null);
setNotificationType (null);	// E
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_User (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=114 */
public static final int Table_ID=MTable.getTable_ID("AD_User");

/** TableName=AD_User */
public static final String Table_Name="AD_User";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_User");

protected BigDecimal accessLevel = BigDecimal.valueOf(7);
/** AccessLevel
@return 7 - System - Client - Org 
*/
protected int get_AccessLevel()
{
return accessLevel.intValue();
}
/** Load Meta Data
@param ctx context
@return PO Info
*/
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
/** Info
@return info
*/
public String toString()
{
StringBuffer sb = new StringBuffer ("X_AD_User[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_OrgTrx_ID AD_Reference_ID=130 */
public static final int AD_ORGTRX_ID_AD_Reference_ID=130;
/** Set Trx Organization.
@param AD_OrgTrx_ID Performing or initiating organization */
public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
{
if (AD_OrgTrx_ID <= 0) set_Value ("AD_OrgTrx_ID", null);
 else 
set_Value ("AD_OrgTrx_ID", Integer.valueOf(AD_OrgTrx_ID));
}
/** Get Trx Organization.
@return Performing or initiating organization */
public int getAD_OrgTrx_ID() 
{
Integer ii = (Integer)get_Value("AD_OrgTrx_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_OrgTrx_ID */
public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID < 1) throw new IllegalArgumentException ("AD_User_ID is mandatory.");
set_ValueNoCheck ("AD_User_ID", Integer.valueOf(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_User_ID */
public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";
/** Set Birthday.
@param Birthday Birthday or Anniversary day */
public void setBirthday (Timestamp Birthday)
{
set_Value ("Birthday", Birthday);
}
/** Get Birthday.
@return Birthday or Anniversary day */
public Timestamp getBirthday() 
{
return (Timestamp)get_Value("Birthday");
}
/** Column name Birthday */
public static final String COLUMNNAME_Birthday = "Birthday";
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_Value ("C_BPartner_ID", null);
 else 
set_Value ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_ID */
public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";
/** Set Partner Location.
@param C_BPartner_Location_ID Identifies the (ship to) address for this Business Partner */
public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
{
if (C_BPartner_Location_ID <= 0) set_Value ("C_BPartner_Location_ID", null);
 else 
set_Value ("C_BPartner_Location_ID", Integer.valueOf(C_BPartner_Location_ID));
}
/** Get Partner Location.
@return Identifies the (ship to) address for this Business Partner */
public int getC_BPartner_Location_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_Location_ID */
public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";
/** Set Greeting.
@param C_Greeting_ID Greeting to print on correspondence */
public void setC_Greeting_ID (int C_Greeting_ID)
{
if (C_Greeting_ID <= 0) set_Value ("C_Greeting_ID", null);
 else 
set_Value ("C_Greeting_ID", Integer.valueOf(C_Greeting_ID));
}
/** Get Greeting.
@return Greeting to print on correspondence */
public int getC_Greeting_ID() 
{
Integer ii = (Integer)get_Value("C_Greeting_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Greeting_ID */
public static final String COLUMNNAME_C_Greeting_ID = "C_Greeting_ID";
/** Set Position.
@param C_Job_ID Job Position */
public void setC_Job_ID (int C_Job_ID)
{
if (C_Job_ID <= 0) set_Value ("C_Job_ID", null);
 else 
set_Value ("C_Job_ID", Integer.valueOf(C_Job_ID));
}
/** Get Position.
@return Job Position */
public int getC_Job_ID() 
{
Integer ii = (Integer)get_Value("C_Job_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Job_ID */
public static final String COLUMNNAME_C_Job_ID = "C_Job_ID";
/** Set Comments.
@param Comments Comments or additional information */
public void setComments (String Comments)
{
if (Comments != null && Comments.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Comments = Comments.substring(0,1999);
}
set_Value ("Comments", Comments);
}
/** Get Comments.
@return Comments or additional information */
public String getComments() 
{
return (String)get_Value("Comments");
}
/** Column name Comments */
public static final String COLUMNNAME_Comments = "Comments";

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
@param ConnectionProfile How a Java Client connects to the server(s) */
public void setConnectionProfile (String ConnectionProfile)
{
if (ConnectionProfile == null || ConnectionProfile.equals("L") || ConnectionProfile.equals("T") || ConnectionProfile.equals("V") || ConnectionProfile.equals("W"));
 else throw new IllegalArgumentException ("ConnectionProfile Invalid value - " + ConnectionProfile + " - Reference_ID=364 - L - T - V - W");
if (ConnectionProfile != null && ConnectionProfile.length() > 1)
{
log.warning("Length > 1 - truncated");
ConnectionProfile = ConnectionProfile.substring(0,0);
}
set_Value ("ConnectionProfile", ConnectionProfile);
}
/** Get Connection Profile.
@return How a Java Client connects to the server(s) */
public String getConnectionProfile() 
{
return (String)get_Value("ConnectionProfile");
}
/** Column name ConnectionProfile */
public static final String COLUMNNAME_ConnectionProfile = "ConnectionProfile";
/** Set Description.
@param Description Optional short description of the record */
public void setDescription (String Description)
{
if (Description != null && Description.length() > 255)
{
log.warning("Length > 255 - truncated");
Description = Description.substring(0,254);
}
set_Value ("Description", Description);
}
/** Get Description.
@return Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set EMail Address.
@param EMail Electronic Mail Address */
public void setEMail (String EMail)
{
if (EMail != null && EMail.length() > 60)
{
log.warning("Length > 60 - truncated");
EMail = EMail.substring(0,59);
}
set_Value ("EMail", EMail);
}
/** Get EMail Address.
@return Electronic Mail Address */
public String getEMail() 
{
return (String)get_Value("EMail");
}
/** Column name EMail */
public static final String COLUMNNAME_EMail = "EMail";
/** Set EMail User ID.
@param EMailUser User Name (ID) in the Mail System */
public void setEMailUser (String EMailUser)
{
if (EMailUser != null && EMailUser.length() > 60)
{
log.warning("Length > 60 - truncated");
EMailUser = EMailUser.substring(0,59);
}
set_Value ("EMailUser", EMailUser);
}
/** Get EMail User ID.
@return User Name (ID) in the Mail System */
public String getEMailUser() 
{
return (String)get_Value("EMailUser");
}
/** Column name EMailUser */
public static final String COLUMNNAME_EMailUser = "EMailUser";
/** Set EMail User Password.
@param EMailUserPW Password of your email user id */
public void setEMailUserPW (String EMailUserPW)
{
if (EMailUserPW != null && EMailUserPW.length() > 20)
{
log.warning("Length > 20 - truncated");
EMailUserPW = EMailUserPW.substring(0,19);
}
set_ValueE ("EMailUserPW", EMailUserPW);
}
/** Get EMail User Password.
@return Password of your email user id */
public String getEMailUserPW() 
{
return (String)get_ValueE("EMailUserPW");
}
/** Column name EMailUserPW */
public static final String COLUMNNAME_EMailUserPW = "EMailUserPW";
/** Set Verification Info.
@param EMailVerify Verification information of EMail Address */
public void setEMailVerify (String EMailVerify)
{
if (EMailVerify != null && EMailVerify.length() > 40)
{
log.warning("Length > 40 - truncated");
EMailVerify = EMailVerify.substring(0,39);
}
set_ValueNoCheck ("EMailVerify", EMailVerify);
}
/** Get Verification Info.
@return Verification information of EMail Address */
public String getEMailVerify() 
{
return (String)get_Value("EMailVerify");
}
/** Column name EMailVerify */
public static final String COLUMNNAME_EMailVerify = "EMailVerify";
/** Set EMail Verify.
@param EMailVerifyDate Date Email was verified */
public void setEMailVerifyDate (Timestamp EMailVerifyDate)
{
set_ValueNoCheck ("EMailVerifyDate", EMailVerifyDate);
}
/** Get EMail Verify.
@return Date Email was verified */
public Timestamp getEMailVerifyDate() 
{
return (Timestamp)get_Value("EMailVerifyDate");
}
/** Column name EMailVerifyDate */
public static final String COLUMNNAME_EMailVerifyDate = "EMailVerifyDate";
/** Set Fax.
@param Fax Facsimile number */
public void setFax (String Fax)
{
if (Fax != null && Fax.length() > 40)
{
log.warning("Length > 40 - truncated");
Fax = Fax.substring(0,39);
}
set_Value ("Fax", Fax);
}
/** Get Fax.
@return Facsimile number */
public String getFax() 
{
return (String)get_Value("Fax");
}
/** Column name Fax */
public static final String COLUMNNAME_Fax = "Fax";
/** Set Full BP Access.
@param IsFullBPAccess The user/concat has full access to Business Partner information and resources */
public void setIsFullBPAccess (boolean IsFullBPAccess)
{
set_Value ("IsFullBPAccess", Boolean.valueOf(IsFullBPAccess));
}
/** Get Full BP Access.
@return The user/concat has full access to Business Partner information and resources */
public boolean isFullBPAccess() 
{
Object oo = get_Value("IsFullBPAccess");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsFullBPAccess */
public static final String COLUMNNAME_IsFullBPAccess = "IsFullBPAccess";
/** Set LDAP User Name.
@param LDAPUser User Name used for authorization via LDAP (directory) services */
public void setLDAPUser (String LDAPUser)
{
if (LDAPUser != null && LDAPUser.length() > 60)
{
log.warning("Length > 60 - truncated");
LDAPUser = LDAPUser.substring(0,59);
}
set_Value ("LDAPUser", LDAPUser);
}
/** Get LDAP User Name.
@return User Name used for authorization via LDAP (directory) services */
public String getLDAPUser() 
{
return (String)get_Value("LDAPUser");
}
/** Column name LDAPUser */
public static final String COLUMNNAME_LDAPUser = "LDAPUser";
/** Set Last Contact.
@param LastContact Date this individual was last contacted */
public void setLastContact (Timestamp LastContact)
{
set_Value ("LastContact", LastContact);
}
/** Get Last Contact.
@return Date this individual was last contacted */
public Timestamp getLastContact() 
{
return (Timestamp)get_Value("LastContact");
}
/** Column name LastContact */
public static final String COLUMNNAME_LastContact = "LastContact";
/** Set Last Result.
@param LastResult Result of last contact */
public void setLastResult (String LastResult)
{
if (LastResult != null && LastResult.length() > 255)
{
log.warning("Length > 255 - truncated");
LastResult = LastResult.substring(0,254);
}
set_Value ("LastResult", LastResult);
}
/** Get Last Result.
@return Result of last contact */
public String getLastResult() 
{
return (String)get_Value("LastResult");
}
/** Column name LastResult */
public static final String COLUMNNAME_LastResult = "LastResult";
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 60)
{
log.warning("Length > 60 - truncated");
Name = Name.substring(0,59);
}
set_Value ("Name", Name);
}
/** Get Name.
@return Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getName());
}
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";

/** NotificationType AD_Reference_ID=344 */
public static final int NOTIFICATIONTYPE_AD_Reference_ID=344;
/** EMail+Notice = B */
public static final String NOTIFICATIONTYPE_EMailPlusNotice = "B";
/** EMail = E */
public static final String NOTIFICATIONTYPE_EMail = "E";
/** Notice = N */
public static final String NOTIFICATIONTYPE_Notice = "N";
/** None = X */
public static final String NOTIFICATIONTYPE_None = "X";
/** Set Notification Type.
@param NotificationType Type of Notifications */
public void setNotificationType (String NotificationType)
{
if (NotificationType == null) throw new IllegalArgumentException ("NotificationType is mandatory");
if (NotificationType.equals("B") || NotificationType.equals("E") || NotificationType.equals("N") || NotificationType.equals("X"));
 else throw new IllegalArgumentException ("NotificationType Invalid value - " + NotificationType + " - Reference_ID=344 - B - E - N - X");
if (NotificationType.length() > 1)
{
log.warning("Length > 1 - truncated");
NotificationType = NotificationType.substring(0,0);
}
set_Value ("NotificationType", NotificationType);
}
/** Get Notification Type.
@return Type of Notifications */
public String getNotificationType() 
{
return (String)get_Value("NotificationType");
}
/** Column name NotificationType */
public static final String COLUMNNAME_NotificationType = "NotificationType";
/** Set Password.
@param Password Password of any length (case sensitive) */
public void setPassword (String Password)
{
if (Password != null && Password.length() > 40)
{
log.warning("Length > 40 - truncated");
Password = Password.substring(0,39);
}
set_ValueE ("Password", Password);
}
/** Get Password.
@return Password of any length (case sensitive) */
public String getPassword() 
{
return (String)get_ValueE("Password");
}
/** Column name Password */
public static final String COLUMNNAME_Password = "Password";
/** Set Phone.
@param Phone Identifies a telephone number */
public void setPhone (String Phone)
{
if (Phone != null && Phone.length() > 40)
{
log.warning("Length > 40 - truncated");
Phone = Phone.substring(0,39);
}
set_Value ("Phone", Phone);
}
/** Get Phone.
@return Identifies a telephone number */
public String getPhone() 
{
return (String)get_Value("Phone");
}
/** Column name Phone */
public static final String COLUMNNAME_Phone = "Phone";
/** Set 2nd Phone.
@param Phone2 Identifies an alternate telephone number. */
public void setPhone2 (String Phone2)
{
if (Phone2 != null && Phone2.length() > 40)
{
log.warning("Length > 40 - truncated");
Phone2 = Phone2.substring(0,39);
}
set_Value ("Phone2", Phone2);
}
/** Get 2nd Phone.
@return Identifies an alternate telephone number. */
public String getPhone2() 
{
return (String)get_Value("Phone2");
}
/** Column name Phone2 */
public static final String COLUMNNAME_Phone2 = "Phone2";
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", Boolean.valueOf(Processing));
}
/** Get Process Now.
@return Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Processing */
public static final String COLUMNNAME_Processing = "Processing";

/** Supervisor_ID AD_Reference_ID=110 */
public static final int SUPERVISOR_ID_AD_Reference_ID=110;
/** Set Supervisor.
@param Supervisor_ID Supervisor for this user/organization - used for escalation and approval */
public void setSupervisor_ID (int Supervisor_ID)
{
if (Supervisor_ID <= 0) set_Value ("Supervisor_ID", null);
 else 
set_Value ("Supervisor_ID", Integer.valueOf(Supervisor_ID));
}
/** Get Supervisor.
@return Supervisor for this user/organization - used for escalation and approval */
public int getSupervisor_ID() 
{
Integer ii = (Integer)get_Value("Supervisor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Supervisor_ID */
public static final String COLUMNNAME_Supervisor_ID = "Supervisor_ID";
/** Set Title.
@param Title Name this entity is referred to as */
public void setTitle (String Title)
{
if (Title != null && Title.length() > 40)
{
log.warning("Length > 40 - truncated");
Title = Title.substring(0,39);
}
set_Value ("Title", Title);
}
/** Get Title.
@return Name this entity is referred to as */
public String getTitle() 
{
return (String)get_Value("Title");
}
/** Column name Title */
public static final String COLUMNNAME_Title = "Title";
/** Set Search Key.
@param Value Search key for the record in the format required - must be unique */
public void setValue (String Value)
{
if (Value != null && Value.length() > 40)
{
log.warning("Length > 40 - truncated");
Value = Value.substring(0,39);
}
set_Value ("Value", Value);
}
/** Get Search Key.
@return Search key for the record in the format required - must be unique */
public String getValue() 
{
return (String)get_Value("Value");
}
/** Column name Value */
public static final String COLUMNNAME_Value = "Value";
}
