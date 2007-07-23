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
/** Generated Model for AD_Client
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_AD_Client extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Client_ID id
@param trxName transaction
*/
public X_AD_Client (Properties ctx, int AD_Client_ID, String trxName)
{
super (ctx, AD_Client_ID, trxName);
/** if (AD_Client_ID == 0)
{
setAutoArchive (null);	// N
setIsCostImmediate (false);	// N
setIsMultiLingualDocument (false);
setIsPostImmediate (false);	// N
setIsServerEMail (false);
setIsSmtpAuthorization (false);	// N
setIsUseBetaFunctions (true);	// Y
setMMPolicy (null);	// F
setName (null);
setStoreArchiveOnFileSystem (false);
setStoreAttachmentsOnFileSystem (false);
setValue (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Client (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=AD_Client */
public static final String Table_Name="AD_Client";

/** AD_Table_ID=112 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

protected BigDecimal accessLevel = BigDecimal.valueOf(6);
/** AccessLevel
@return 6 - System - Client 
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
StringBuffer sb = new StringBuffer ("X_AD_Client[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_Language AD_Reference_ID=327 */
public static final int AD_LANGUAGE_AD_Reference_ID=327;
/** Set Language.
@param AD_Language Language for this entity */
public void setAD_Language (String AD_Language)
{
if (AD_Language != null && AD_Language.length() > 6)
{
log.warning("Length > 6 - truncated");
AD_Language = AD_Language.substring(0,5);
}
set_Value ("AD_Language", AD_Language);
}
/** Get Language.
@return Language for this entity */
public String getAD_Language() 
{
return (String)get_Value("AD_Language");
}
/** Column name AD_Language */
public static final String COLUMNNAME_AD_Language = "AD_Language";

/** AutoArchive AD_Reference_ID=334 */
public static final int AUTOARCHIVE_AD_Reference_ID=334;
/** All (Reports, Documents) = 1 */
public static final String AUTOARCHIVE_AllReportsDocuments = "1";
/** Documents = 2 */
public static final String AUTOARCHIVE_Documents = "2";
/** External Documents = 3 */
public static final String AUTOARCHIVE_ExternalDocuments = "3";
/** None = N */
public static final String AUTOARCHIVE_None = "N";
/** Set Auto Archive.
@param AutoArchive Enable and level of automatic Archive of documents */
public void setAutoArchive (String AutoArchive)
{
if (AutoArchive == null) throw new IllegalArgumentException ("AutoArchive is mandatory");
if (AutoArchive.equals("1") || AutoArchive.equals("2") || AutoArchive.equals("3") || AutoArchive.equals("N"));
 else throw new IllegalArgumentException ("AutoArchive Invalid value - " + AutoArchive + " - Reference_ID=334 - 1 - 2 - 3 - N");
if (AutoArchive.length() > 1)
{
log.warning("Length > 1 - truncated");
AutoArchive = AutoArchive.substring(0,0);
}
set_Value ("AutoArchive", AutoArchive);
}
/** Get Auto Archive.
@return Enable and level of automatic Archive of documents */
public String getAutoArchive() 
{
return (String)get_Value("AutoArchive");
}
/** Column name AutoArchive */
public static final String COLUMNNAME_AutoArchive = "AutoArchive";
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
/** Set Document Directory.
@param DocumentDir Directory for documents from the application server */
public void setDocumentDir (String DocumentDir)
{
if (DocumentDir != null && DocumentDir.length() > 60)
{
log.warning("Length > 60 - truncated");
DocumentDir = DocumentDir.substring(0,59);
}
set_Value ("DocumentDir", DocumentDir);
}
/** Get Document Directory.
@return Directory for documents from the application server */
public String getDocumentDir() 
{
return (String)get_Value("DocumentDir");
}
/** Column name DocumentDir */
public static final String COLUMNNAME_DocumentDir = "DocumentDir";
/** Set EMail Test.
@param EMailTest Test EMail */
public void setEMailTest (String EMailTest)
{
if (EMailTest != null && EMailTest.length() > 1)
{
log.warning("Length > 1 - truncated");
EMailTest = EMailTest.substring(0,0);
}
set_Value ("EMailTest", EMailTest);
}
/** Get EMail Test.
@return Test EMail */
public String getEMailTest() 
{
return (String)get_Value("EMailTest");
}
/** Column name EMailTest */
public static final String COLUMNNAME_EMailTest = "EMailTest";
/** Set Cost Immediately.
@param IsCostImmediate Update Costs immediately for testing */
public void setIsCostImmediate (boolean IsCostImmediate)
{
set_Value ("IsCostImmediate", Boolean.valueOf(IsCostImmediate));
}
/** Get Cost Immediately.
@return Update Costs immediately for testing */
public boolean isCostImmediate() 
{
Object oo = get_Value("IsCostImmediate");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsCostImmediate */
public static final String COLUMNNAME_IsCostImmediate = "IsCostImmediate";
/** Set Multi Lingual Documents.
@param IsMultiLingualDocument Documents are Multi Lingual */
public void setIsMultiLingualDocument (boolean IsMultiLingualDocument)
{
set_Value ("IsMultiLingualDocument", Boolean.valueOf(IsMultiLingualDocument));
}
/** Get Multi Lingual Documents.
@return Documents are Multi Lingual */
public boolean isMultiLingualDocument() 
{
Object oo = get_Value("IsMultiLingualDocument");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsMultiLingualDocument */
public static final String COLUMNNAME_IsMultiLingualDocument = "IsMultiLingualDocument";
/** Set Post Immediately.
@param IsPostImmediate Post the accounting immediately for testing */
public void setIsPostImmediate (boolean IsPostImmediate)
{
set_Value ("IsPostImmediate", Boolean.valueOf(IsPostImmediate));
}
/** Get Post Immediately.
@return Post the accounting immediately for testing */
public boolean isPostImmediate() 
{
Object oo = get_Value("IsPostImmediate");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPostImmediate */
public static final String COLUMNNAME_IsPostImmediate = "IsPostImmediate";
/** Set Server EMail.
@param IsServerEMail Send EMail from Server */
public void setIsServerEMail (boolean IsServerEMail)
{
set_Value ("IsServerEMail", Boolean.valueOf(IsServerEMail));
}
/** Get Server EMail.
@return Send EMail from Server */
public boolean isServerEMail() 
{
Object oo = get_Value("IsServerEMail");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsServerEMail */
public static final String COLUMNNAME_IsServerEMail = "IsServerEMail";
/** Set SMTP Authentication.
@param IsSmtpAuthorization Your mail server requires Authentication */
public void setIsSmtpAuthorization (boolean IsSmtpAuthorization)
{
set_Value ("IsSmtpAuthorization", Boolean.valueOf(IsSmtpAuthorization));
}
/** Get SMTP Authentication.
@return Your mail server requires Authentication */
public boolean isSmtpAuthorization() 
{
Object oo = get_Value("IsSmtpAuthorization");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSmtpAuthorization */
public static final String COLUMNNAME_IsSmtpAuthorization = "IsSmtpAuthorization";
/** Set Use Beta Functions.
@param IsUseBetaFunctions Enable the use of Beta Functionality */
public void setIsUseBetaFunctions (boolean IsUseBetaFunctions)
{
set_Value ("IsUseBetaFunctions", Boolean.valueOf(IsUseBetaFunctions));
}
/** Get Use Beta Functions.
@return Enable the use of Beta Functionality */
public boolean isUseBetaFunctions() 
{
Object oo = get_Value("IsUseBetaFunctions");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsUseBetaFunctions */
public static final String COLUMNNAME_IsUseBetaFunctions = "IsUseBetaFunctions";

/** MMPolicy AD_Reference_ID=335 */
public static final int MMPOLICY_AD_Reference_ID=335;
/** FiFo = F */
public static final String MMPOLICY_FiFo = "F";
/** LiFo = L */
public static final String MMPOLICY_LiFo = "L";
/** Set Material Policy.
@param MMPolicy Material Movement Policy */
public void setMMPolicy (String MMPolicy)
{
if (MMPolicy == null) throw new IllegalArgumentException ("MMPolicy is mandatory");
if (MMPolicy.equals("F") || MMPolicy.equals("L"));
 else throw new IllegalArgumentException ("MMPolicy Invalid value - " + MMPolicy + " - Reference_ID=335 - F - L");
if (MMPolicy.length() > 1)
{
log.warning("Length > 1 - truncated");
MMPolicy = MMPolicy.substring(0,0);
}
set_Value ("MMPolicy", MMPolicy);
}
/** Get Material Policy.
@return Material Movement Policy */
public String getMMPolicy() 
{
return (String)get_Value("MMPolicy");
}
/** Column name MMPolicy */
public static final String COLUMNNAME_MMPolicy = "MMPolicy";
/** Set Model Validation Classes.
@param ModelValidationClasses List of data model validation classes separated by ;
 */
public void setModelValidationClasses (String ModelValidationClasses)
{
if (ModelValidationClasses != null && ModelValidationClasses.length() > 255)
{
log.warning("Length > 255 - truncated");
ModelValidationClasses = ModelValidationClasses.substring(0,254);
}
set_Value ("ModelValidationClasses", ModelValidationClasses);
}
/** Get Model Validation Classes.
@return List of data model validation classes separated by ;
 */
public String getModelValidationClasses() 
{
return (String)get_Value("ModelValidationClasses");
}
/** Column name ModelValidationClasses */
public static final String COLUMNNAME_ModelValidationClasses = "ModelValidationClasses";
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
/** Set Request EMail.
@param RequestEMail EMail address to send automated mails from or receive mails for automated processing (fully qualified) */
public void setRequestEMail (String RequestEMail)
{
if (RequestEMail != null && RequestEMail.length() > 60)
{
log.warning("Length > 60 - truncated");
RequestEMail = RequestEMail.substring(0,59);
}
set_Value ("RequestEMail", RequestEMail);
}
/** Get Request EMail.
@return EMail address to send automated mails from or receive mails for automated processing (fully qualified) */
public String getRequestEMail() 
{
return (String)get_Value("RequestEMail");
}
/** Column name RequestEMail */
public static final String COLUMNNAME_RequestEMail = "RequestEMail";
/** Set Request Folder.
@param RequestFolder EMail folder to process incoming emails;
 if empty INBOX is used */
public void setRequestFolder (String RequestFolder)
{
if (RequestFolder != null && RequestFolder.length() > 20)
{
log.warning("Length > 20 - truncated");
RequestFolder = RequestFolder.substring(0,19);
}
set_Value ("RequestFolder", RequestFolder);
}
/** Get Request Folder.
@return EMail folder to process incoming emails;
 if empty INBOX is used */
public String getRequestFolder() 
{
return (String)get_Value("RequestFolder");
}
/** Column name RequestFolder */
public static final String COLUMNNAME_RequestFolder = "RequestFolder";
/** Set Request User.
@param RequestUser User Name (ID) of the email owner */
public void setRequestUser (String RequestUser)
{
if (RequestUser != null && RequestUser.length() > 60)
{
log.warning("Length > 60 - truncated");
RequestUser = RequestUser.substring(0,59);
}
set_Value ("RequestUser", RequestUser);
}
/** Get Request User.
@return User Name (ID) of the email owner */
public String getRequestUser() 
{
return (String)get_Value("RequestUser");
}
/** Column name RequestUser */
public static final String COLUMNNAME_RequestUser = "RequestUser";
/** Set Request User Password.
@param RequestUserPW Password of the user name (ID) for mail processing */
public void setRequestUserPW (String RequestUserPW)
{
if (RequestUserPW != null && RequestUserPW.length() > 20)
{
log.warning("Length > 20 - truncated");
RequestUserPW = RequestUserPW.substring(0,19);
}
set_Value ("RequestUserPW", RequestUserPW);
}
/** Get Request User Password.
@return Password of the user name (ID) for mail processing */
public String getRequestUserPW() 
{
return (String)get_Value("RequestUserPW");
}
/** Column name RequestUserPW */
public static final String COLUMNNAME_RequestUserPW = "RequestUserPW";
/** Set Mail Host.
@param SMTPHost Hostname of Mail Server for SMTP and IMAP */
public void setSMTPHost (String SMTPHost)
{
if (SMTPHost != null && SMTPHost.length() > 60)
{
log.warning("Length > 60 - truncated");
SMTPHost = SMTPHost.substring(0,59);
}
set_Value ("SMTPHost", SMTPHost);
}
/** Get Mail Host.
@return Hostname of Mail Server for SMTP and IMAP */
public String getSMTPHost() 
{
return (String)get_Value("SMTPHost");
}
/** Column name SMTPHost */
public static final String COLUMNNAME_SMTPHost = "SMTPHost";
/** Set Store Archive On File System.
@param StoreArchiveOnFileSystem Store Archive On File System */
public void setStoreArchiveOnFileSystem (boolean StoreArchiveOnFileSystem)
{
set_Value ("StoreArchiveOnFileSystem", Boolean.valueOf(StoreArchiveOnFileSystem));
}
/** Get Store Archive On File System.
@return Store Archive On File System */
public boolean isStoreArchiveOnFileSystem() 
{
Object oo = get_Value("StoreArchiveOnFileSystem");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name StoreArchiveOnFileSystem */
public static final String COLUMNNAME_StoreArchiveOnFileSystem = "StoreArchiveOnFileSystem";
/** Set Store Attachments On File System.
@param StoreAttachmentsOnFileSystem Store Attachments On File System */
public void setStoreAttachmentsOnFileSystem (boolean StoreAttachmentsOnFileSystem)
{
set_Value ("StoreAttachmentsOnFileSystem", Boolean.valueOf(StoreAttachmentsOnFileSystem));
}
/** Get Store Attachments On File System.
@return Store Attachments On File System */
public boolean isStoreAttachmentsOnFileSystem() 
{
Object oo = get_Value("StoreAttachmentsOnFileSystem");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name StoreAttachmentsOnFileSystem */
public static final String COLUMNNAME_StoreAttachmentsOnFileSystem = "StoreAttachmentsOnFileSystem";
/** Set Unix Archive Path.
@param UnixArchivePath Unix Archive Path */
public void setUnixArchivePath (String UnixArchivePath)
{
if (UnixArchivePath != null && UnixArchivePath.length() > 255)
{
log.warning("Length > 255 - truncated");
UnixArchivePath = UnixArchivePath.substring(0,254);
}
set_Value ("UnixArchivePath", UnixArchivePath);
}
/** Get Unix Archive Path.
@return Unix Archive Path */
public String getUnixArchivePath() 
{
return (String)get_Value("UnixArchivePath");
}
/** Column name UnixArchivePath */
public static final String COLUMNNAME_UnixArchivePath = "UnixArchivePath";
/** Set Unix Attachment Path.
@param UnixAttachmentPath Unix Attachment Path */
public void setUnixAttachmentPath (String UnixAttachmentPath)
{
if (UnixAttachmentPath != null && UnixAttachmentPath.length() > 255)
{
log.warning("Length > 255 - truncated");
UnixAttachmentPath = UnixAttachmentPath.substring(0,254);
}
set_Value ("UnixAttachmentPath", UnixAttachmentPath);
}
/** Get Unix Attachment Path.
@return Unix Attachment Path */
public String getUnixAttachmentPath() 
{
return (String)get_Value("UnixAttachmentPath");
}
/** Column name UnixAttachmentPath */
public static final String COLUMNNAME_UnixAttachmentPath = "UnixAttachmentPath";
/** Set Search Key.
@param Value Search key for the record in the format required - must be unique */
public void setValue (String Value)
{
if (Value == null) throw new IllegalArgumentException ("Value is mandatory.");
if (Value.length() > 40)
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
/** Set Windows Archive Path.
@param WindowsArchivePath Windows Archive Path */
public void setWindowsArchivePath (String WindowsArchivePath)
{
if (WindowsArchivePath != null && WindowsArchivePath.length() > 255)
{
log.warning("Length > 255 - truncated");
WindowsArchivePath = WindowsArchivePath.substring(0,254);
}
set_Value ("WindowsArchivePath", WindowsArchivePath);
}
/** Get Windows Archive Path.
@return Windows Archive Path */
public String getWindowsArchivePath() 
{
return (String)get_Value("WindowsArchivePath");
}
/** Column name WindowsArchivePath */
public static final String COLUMNNAME_WindowsArchivePath = "WindowsArchivePath";
/** Set Windows Attachment Path.
@param WindowsAttachmentPath Windows Attachment Path */
public void setWindowsAttachmentPath (String WindowsAttachmentPath)
{
if (WindowsAttachmentPath != null && WindowsAttachmentPath.length() > 255)
{
log.warning("Length > 255 - truncated");
WindowsAttachmentPath = WindowsAttachmentPath.substring(0,254);
}
set_Value ("WindowsAttachmentPath", WindowsAttachmentPath);
}
/** Get Windows Attachment Path.
@return Windows Attachment Path */
public String getWindowsAttachmentPath() 
{
return (String)get_Value("WindowsAttachmentPath");
}
/** Column name WindowsAttachmentPath */
public static final String COLUMNNAME_WindowsAttachmentPath = "WindowsAttachmentPath";
}
