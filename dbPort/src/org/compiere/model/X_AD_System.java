/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
/** Generated Model for AD_System
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_AD_System extends PO
{
/** Standard Constructor
@param ctx context
@param AD_System_ID id
@param trxName transaction
*/
public X_AD_System (Properties ctx, int AD_System_ID, String trxName)
{
super (ctx, AD_System_ID, trxName);
/** if (AD_System_ID == 0)
{
setAD_System_ID (0);	// 0
setInfo (null);
setIsAllowStatistics (false);
setIsAutoErrorReport (true);	// Y
setName (null);
setPassword (null);
setReplicationType (null);	// L
setSystemStatus (null);	// E
setUserName (null);
setVersion (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_System (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=531 */
public static final int Table_ID=MTable.getTable_ID("AD_System");

/** TableName=AD_System */
public static final String Table_Name="AD_System";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_System");

protected BigDecimal accessLevel = BigDecimal.valueOf(4);
/** AccessLevel
@return 4 - System 
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
StringBuffer sb = new StringBuffer ("X_AD_System[").append(get_ID()).append("]");
return sb.toString();
}
/** Set System.
@param AD_System_ID System Definition */
public void setAD_System_ID (int AD_System_ID)
{
if (AD_System_ID < 1) throw new IllegalArgumentException ("AD_System_ID is mandatory.");
set_ValueNoCheck ("AD_System_ID", Integer.valueOf(AD_System_ID));
}
/** Get System.
@return System Definition */
public int getAD_System_ID() 
{
Integer ii = (Integer)get_Value("AD_System_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Custom Prefix.
@param CustomPrefix Prefix for Custom entities */
public void setCustomPrefix (String CustomPrefix)
{
if (CustomPrefix != null && CustomPrefix.length() > 60)
{
log.warning("Length > 60 - truncated");
CustomPrefix = CustomPrefix.substring(0,59);
}
set_Value ("CustomPrefix", CustomPrefix);
}
/** Get Custom Prefix.
@return Prefix for Custom entities */
public String getCustomPrefix() 
{
return (String)get_Value("CustomPrefix");
}
/** Set DB Address.
@param DBAddress JDBC URL of the database server */
public void setDBAddress (String DBAddress)
{
if (DBAddress != null && DBAddress.length() > 255)
{
log.warning("Length > 255 - truncated");
DBAddress = DBAddress.substring(0,254);
}
set_Value ("DBAddress", DBAddress);
}
/** Get DB Address.
@return JDBC URL of the database server */
public String getDBAddress() 
{
return (String)get_Value("DBAddress");
}
/** Set Database Name.
@param DBInstance Database Name */
public void setDBInstance (String DBInstance)
{
if (DBInstance != null && DBInstance.length() > 60)
{
log.warning("Length > 60 - truncated");
DBInstance = DBInstance.substring(0,59);
}
set_Value ("DBInstance", DBInstance);
}
/** Get Database Name.
@return Database Name */
public String getDBInstance() 
{
return (String)get_Value("DBInstance");
}
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
/** Set Encryption Class.
@param EncryptionKey Encryption Class used for securing data content */
public void setEncryptionKey (String EncryptionKey)
{
if (EncryptionKey != null && EncryptionKey.length() > 255)
{
log.warning("Length > 255 - truncated");
EncryptionKey = EncryptionKey.substring(0,254);
}
set_ValueNoCheck ("EncryptionKey", EncryptionKey);
}
/** Get Encryption Class.
@return Encryption Class used for securing data content */
public String getEncryptionKey() 
{
return (String)get_Value("EncryptionKey");
}
/** Set ID Range End.
@param IDRangeEnd End if the ID Range used */
public void setIDRangeEnd (BigDecimal IDRangeEnd)
{
set_Value ("IDRangeEnd", IDRangeEnd);
}
/** Get ID Range End.
@return End if the ID Range used */
public BigDecimal getIDRangeEnd() 
{
BigDecimal bd = (BigDecimal)get_Value("IDRangeEnd");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set ID Range Start.
@param IDRangeStart Start of the ID Range used */
public void setIDRangeStart (BigDecimal IDRangeStart)
{
set_Value ("IDRangeStart", IDRangeStart);
}
/** Get ID Range Start.
@return Start of the ID Range used */
public BigDecimal getIDRangeStart() 
{
BigDecimal bd = (BigDecimal)get_Value("IDRangeStart");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Info.
@param Info Information */
public void setInfo (String Info)
{
if (Info == null) throw new IllegalArgumentException ("Info is mandatory.");
if (Info.length() > 255)
{
log.warning("Length > 255 - truncated");
Info = Info.substring(0,254);
}
set_ValueNoCheck ("Info", Info);
}
/** Get Info.
@return Information */
public String getInfo() 
{
return (String)get_Value("Info");
}
/** Set Maintain Statistics.
@param IsAllowStatistics Maintain general statistics */
public void setIsAllowStatistics (boolean IsAllowStatistics)
{
set_Value ("IsAllowStatistics", Boolean.valueOf(IsAllowStatistics));
}
/** Get Maintain Statistics.
@return Maintain general statistics */
public boolean isAllowStatistics() 
{
Object oo = get_Value("IsAllowStatistics");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Error Reporting.
@param IsAutoErrorReport Automatically report Errors */
public void setIsAutoErrorReport (boolean IsAutoErrorReport)
{
set_Value ("IsAutoErrorReport", Boolean.valueOf(IsAutoErrorReport));
}
/** Get Error Reporting.
@return Automatically report Errors */
public boolean isAutoErrorReport() 
{
Object oo = get_Value("IsAutoErrorReport");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Just Migrated.
@param IsJustMigrated Value set by Migration for post-Migation tasks. */
public void setIsJustMigrated (boolean IsJustMigrated)
{
set_Value ("IsJustMigrated", Boolean.valueOf(IsJustMigrated));
}
/** Get Just Migrated.
@return Value set by Migration for post-Migation tasks. */
public boolean isJustMigrated() 
{
Object oo = get_Value("IsJustMigrated");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set LDAP Domain.
@param LDAPDomain Directory service domain name - e.g. adempiere.org */
public void setLDAPDomain (String LDAPDomain)
{
if (LDAPDomain != null && LDAPDomain.length() > 255)
{
log.warning("Length > 255 - truncated");
LDAPDomain = LDAPDomain.substring(0,254);
}
set_Value ("LDAPDomain", LDAPDomain);
}
/** Get LDAP Domain.
@return Directory service domain name - e.g. adempiere.org */
public String getLDAPDomain() 
{
return (String)get_Value("LDAPDomain");
}
/** Set LDAP URL.
@param LDAPHost Connection String to LDAP server starting with ldap:// */
public void setLDAPHost (String LDAPHost)
{
if (LDAPHost != null && LDAPHost.length() > 60)
{
log.warning("Length > 60 - truncated");
LDAPHost = LDAPHost.substring(0,59);
}
set_Value ("LDAPHost", LDAPHost);
}
/** Get LDAP URL.
@return Connection String to LDAP server starting with ldap:// */
public String getLDAPHost() 
{
return (String)get_Value("LDAPHost");
}
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
/** Set Processors.
@param NoProcessors Number of Database Processors */
public void setNoProcessors (int NoProcessors)
{
set_Value ("NoProcessors", Integer.valueOf(NoProcessors));
}
/** Get Processors.
@return Number of Database Processors */
public int getNoProcessors() 
{
Integer ii = (Integer)get_Value("NoProcessors");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Old Name.
@param OldName Old Name */
public void setOldName (String OldName)
{
if (OldName != null && OldName.length() > 60)
{
log.warning("Length > 60 - truncated");
OldName = OldName.substring(0,59);
}
set_ValueNoCheck ("OldName", OldName);
}
/** Get Old Name.
@return Old Name */
public String getOldName() 
{
return (String)get_Value("OldName");
}
/** Set Password.
@param Password Password of any length (case sensitive) */
public void setPassword (String Password)
{
if (Password == null) throw new IllegalArgumentException ("Password is mandatory.");
if (Password.length() > 20)
{
log.warning("Length > 20 - truncated");
Password = Password.substring(0,19);
}
set_Value ("Password", Password);
}
/** Get Password.
@return Password of any length (case sensitive) */
public String getPassword() 
{
return (String)get_Value("Password");
}
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
/** Set Profile.
@param ProfileInfo Information to help profiling the system for solving support issues */
public void setProfileInfo (String ProfileInfo)
{
if (ProfileInfo != null && ProfileInfo.length() > 60)
{
log.warning("Length > 60 - truncated");
ProfileInfo = ProfileInfo.substring(0,59);
}
set_ValueNoCheck ("ProfileInfo", ProfileInfo);
}
/** Get Profile.
@return Information to help profiling the system for solving support issues */
public String getProfileInfo() 
{
return (String)get_Value("ProfileInfo");
}
/** Set Record ID.
@param Record_ID Direct internal record ID */
public void setRecord_ID (int Record_ID)
{
if (Record_ID <= 0) set_Value ("Record_ID", null);
 else 
set_Value ("Record_ID", Integer.valueOf(Record_ID));
}
/** Get Record ID.
@return Direct internal record ID */
public int getRecord_ID() 
{
Integer ii = (Integer)get_Value("Record_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Release No.
@param ReleaseNo Internal Release Number */
public void setReleaseNo (String ReleaseNo)
{
if (ReleaseNo != null && ReleaseNo.length() > 4)
{
log.warning("Length > 4 - truncated");
ReleaseNo = ReleaseNo.substring(0,3);
}
set_ValueNoCheck ("ReleaseNo", ReleaseNo);
}
/** Get Release No.
@return Internal Release Number */
public String getReleaseNo() 
{
return (String)get_Value("ReleaseNo");
}

/** ReplicationType AD_Reference_ID=126 */
public static final int REPLICATIONTYPE_AD_Reference_ID=126;
/** Local = L */
public static final String REPLICATIONTYPE_Local = "L";
/** Merge = M */
public static final String REPLICATIONTYPE_Merge = "M";
/** Reference = R */
public static final String REPLICATIONTYPE_Reference = "R";
/** Set Replication Type.
@param ReplicationType Type of Data Replication */
public void setReplicationType (String ReplicationType)
{
if (ReplicationType == null) throw new IllegalArgumentException ("ReplicationType is mandatory");
if (ReplicationType.equals("L") || ReplicationType.equals("M") || ReplicationType.equals("R"));
 else throw new IllegalArgumentException ("ReplicationType Invalid value - " + ReplicationType + " - Reference_ID=126 - L - M - R");
if (ReplicationType.length() > 1)
{
log.warning("Length > 1 - truncated");
ReplicationType = ReplicationType.substring(0,0);
}
set_Value ("ReplicationType", ReplicationType);
}
/** Get Replication Type.
@return Type of Data Replication */
public String getReplicationType() 
{
return (String)get_Value("ReplicationType");
}
/** Set Statistics.
@param StatisticsInfo Information to help profiling the system for solving support issues */
public void setStatisticsInfo (String StatisticsInfo)
{
if (StatisticsInfo != null && StatisticsInfo.length() > 60)
{
log.warning("Length > 60 - truncated");
StatisticsInfo = StatisticsInfo.substring(0,59);
}
set_ValueNoCheck ("StatisticsInfo", StatisticsInfo);
}
/** Get Statistics.
@return Information to help profiling the system for solving support issues */
public String getStatisticsInfo() 
{
return (String)get_Value("StatisticsInfo");
}
/** Set Summary.
@param Summary Textual summary of this request */
public void setSummary (String Summary)
{
if (Summary != null && Summary.length() > 255)
{
log.warning("Length > 255 - truncated");
Summary = Summary.substring(0,254);
}
set_Value ("Summary", Summary);
}
/** Get Summary.
@return Textual summary of this request */
public String getSummary() 
{
return (String)get_Value("Summary");
}
/** Set Support EMail.
@param SupportEMail EMail address to send support information and updates to */
public void setSupportEMail (String SupportEMail)
{
if (SupportEMail != null && SupportEMail.length() > 60)
{
log.warning("Length > 60 - truncated");
SupportEMail = SupportEMail.substring(0,59);
}
set_Value ("SupportEMail", SupportEMail);
}
/** Get Support EMail.
@return EMail address to send support information and updates to */
public String getSupportEMail() 
{
return (String)get_Value("SupportEMail");
}
/** Set Support Expires.
@param SupportExpDate Date when the Adempiere support expires */
public void setSupportExpDate (Timestamp SupportExpDate)
{
set_ValueNoCheck ("SupportExpDate", SupportExpDate);
}
/** Get Support Expires.
@return Date when the Adempiere support expires */
public Timestamp getSupportExpDate() 
{
return (Timestamp)get_Value("SupportExpDate");
}
/** Set Internal Users.
@param SupportUnits Number of Internal Users for Adempiere Support */
public void setSupportUnits (int SupportUnits)
{
set_ValueNoCheck ("SupportUnits", Integer.valueOf(SupportUnits));
}
/** Get Internal Users.
@return Number of Internal Users for Adempiere Support */
public int getSupportUnits() 
{
Integer ii = (Integer)get_Value("SupportUnits");
if (ii == null) return 0;
return ii.intValue();
}

/** SystemStatus AD_Reference_ID=374 */
public static final int SYSTEMSTATUS_AD_Reference_ID=374;
/** Evaluation = E */
public static final String SYSTEMSTATUS_Evaluation = "E";
/** Implementation = I */
public static final String SYSTEMSTATUS_Implementation = "I";
/** Production = P */
public static final String SYSTEMSTATUS_Production = "P";
/** Set System Status.
@param SystemStatus Status of the system - Support priority depends on system status */
public void setSystemStatus (String SystemStatus)
{
if (SystemStatus == null) throw new IllegalArgumentException ("SystemStatus is mandatory");
if (SystemStatus.equals("E") || SystemStatus.equals("I") || SystemStatus.equals("P"));
 else throw new IllegalArgumentException ("SystemStatus Invalid value - " + SystemStatus + " - Reference_ID=374 - E - I - P");
if (SystemStatus.length() > 1)
{
log.warning("Length > 1 - truncated");
SystemStatus = SystemStatus.substring(0,0);
}
set_Value ("SystemStatus", SystemStatus);
}
/** Get System Status.
@return Status of the system - Support priority depends on system status */
public String getSystemStatus() 
{
return (String)get_Value("SystemStatus");
}
/** Set Registered EMail.
@param UserName Email of the responsible for the System */
public void setUserName (String UserName)
{
if (UserName == null) throw new IllegalArgumentException ("UserName is mandatory.");
if (UserName.length() > 60)
{
log.warning("Length > 60 - truncated");
UserName = UserName.substring(0,59);
}
set_Value ("UserName", UserName);
}
/** Get Registered EMail.
@return Email of the responsible for the System */
public String getUserName() 
{
return (String)get_Value("UserName");
}
/** Set Version.
@param Version Version of the table definition */
public void setVersion (String Version)
{
if (Version == null) throw new IllegalArgumentException ("Version is mandatory.");
if (Version.length() > 20)
{
log.warning("Length > 20 - truncated");
Version = Version.substring(0,19);
}
set_ValueNoCheck ("Version", Version);
}
/** Get Version.
@return Version of the table definition */
public String getVersion() 
{
return (String)get_Value("Version");
}
}
