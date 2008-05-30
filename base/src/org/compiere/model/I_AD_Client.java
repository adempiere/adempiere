/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Client
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_AD_Client 
{

    /** TableName=AD_Client */
    public static final String Table_Name = "AD_Client";

    /** AD_Table_ID=112 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Language */
    public static final String COLUMNNAME_AD_Language = "AD_Language";

	/** Set Language.
	  * Language for this entity
	  */
	public void setAD_Language (String AD_Language);

	/** Get Language.
	  * Language for this entity
	  */
	public String getAD_Language();

    /** Column name AD_ReplicationStrategy_ID */
    public static final String COLUMNNAME_AD_ReplicationStrategy_ID = "AD_ReplicationStrategy_ID";

	/** Set Replication Strategy.
	  * Data Replication Strategy
	  */
	public void setAD_ReplicationStrategy_ID (int AD_ReplicationStrategy_ID);

	/** Get Replication Strategy.
	  * Data Replication Strategy
	  */
	public int getAD_ReplicationStrategy_ID();

	public I_AD_ReplicationStrategy getAD_ReplicationStrategy() throws Exception;

    /** Column name AutoArchive */
    public static final String COLUMNNAME_AutoArchive = "AutoArchive";

	/** Set Auto Archive.
	  * Enable and level of automatic Archive of documents
	  */
	public void setAutoArchive (String AutoArchive);

	/** Get Auto Archive.
	  * Enable and level of automatic Archive of documents
	  */
	public String getAutoArchive();

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

    /** Column name DocumentDir */
    public static final String COLUMNNAME_DocumentDir = "DocumentDir";

	/** Set Document Directory.
	  * Directory for documents from the application server
	  */
	public void setDocumentDir (String DocumentDir);

	/** Get Document Directory.
	  * Directory for documents from the application server
	  */
	public String getDocumentDir();

    /** Column name EMailTest */
    public static final String COLUMNNAME_EMailTest = "EMailTest";

	/** Set EMail Test.
	  * Test EMail
	  */
	public void setEMailTest (String EMailTest);

	/** Get EMail Test.
	  * Test EMail
	  */
	public String getEMailTest();

    /** Column name IsCostImmediate */
    public static final String COLUMNNAME_IsCostImmediate = "IsCostImmediate";

	/** Set Cost Immediately.
	  * Update Costs immediately for testing
	  */
	public void setIsCostImmediate (boolean IsCostImmediate);

	/** Get Cost Immediately.
	  * Update Costs immediately for testing
	  */
	public boolean isCostImmediate();

    /** Column name IsMultiLingualDocument */
    public static final String COLUMNNAME_IsMultiLingualDocument = "IsMultiLingualDocument";

	/** Set Multi Lingual Documents.
	  * Documents are Multi Lingual
	  */
	public void setIsMultiLingualDocument (boolean IsMultiLingualDocument);

	/** Get Multi Lingual Documents.
	  * Documents are Multi Lingual
	  */
	public boolean isMultiLingualDocument();

    /** Column name IsPostImmediate */
    public static final String COLUMNNAME_IsPostImmediate = "IsPostImmediate";

	/** Set Post Immediately.
	  * Post the accounting immediately for testing
	  */
	public void setIsPostImmediate (boolean IsPostImmediate);

	/** Get Post Immediately.
	  * Post the accounting immediately for testing
	  */
	public boolean isPostImmediate();

    /** Column name IsServerEMail */
    public static final String COLUMNNAME_IsServerEMail = "IsServerEMail";

	/** Set Server EMail.
	  * Send EMail from Server
	  */
	public void setIsServerEMail (boolean IsServerEMail);

	/** Get Server EMail.
	  * Send EMail from Server
	  */
	public boolean isServerEMail();

    /** Column name IsSmtpAuthorization */
    public static final String COLUMNNAME_IsSmtpAuthorization = "IsSmtpAuthorization";

	/** Set SMTP Authentication.
	  * Your mail server requires Authentication
	  */
	public void setIsSmtpAuthorization (boolean IsSmtpAuthorization);

	/** Get SMTP Authentication.
	  * Your mail server requires Authentication
	  */
	public boolean isSmtpAuthorization();

    /** Column name IsUseASP */
    public static final String COLUMNNAME_IsUseASP = "IsUseASP";

	/** Set IsUseASP	  */
	public void setIsUseASP (boolean IsUseASP);

	/** Get IsUseASP	  */
	public boolean isUseASP();

    /** Column name IsUseBetaFunctions */
    public static final String COLUMNNAME_IsUseBetaFunctions = "IsUseBetaFunctions";

	/** Set Use Beta Functions.
	  * Enable the use of Beta Functionality
	  */
	public void setIsUseBetaFunctions (boolean IsUseBetaFunctions);

	/** Get Use Beta Functions.
	  * Enable the use of Beta Functionality
	  */
	public boolean isUseBetaFunctions();

    /** Column name MMPolicy */
    public static final String COLUMNNAME_MMPolicy = "MMPolicy";

	/** Set Material Policy.
	  * Material Movement Policy
	  */
	public void setMMPolicy (String MMPolicy);

	/** Get Material Policy.
	  * Material Movement Policy
	  */
	public String getMMPolicy();

    /** Column name ModelValidationClasses */
    public static final String COLUMNNAME_ModelValidationClasses = "ModelValidationClasses";

	/** Set Model Validation Classes.
	  * List of data model validation classes separated by ;

	  */
	public void setModelValidationClasses (String ModelValidationClasses);

	/** Get Model Validation Classes.
	  * List of data model validation classes separated by ;

	  */
	public String getModelValidationClasses();

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

    /** Column name RequestEMail */
    public static final String COLUMNNAME_RequestEMail = "RequestEMail";

	/** Set Request EMail.
	  * EMail address to send automated mails from or receive mails for automated processing (fully qualified)
	  */
	public void setRequestEMail (String RequestEMail);

	/** Get Request EMail.
	  * EMail address to send automated mails from or receive mails for automated processing (fully qualified)
	  */
	public String getRequestEMail();

    /** Column name RequestFolder */
    public static final String COLUMNNAME_RequestFolder = "RequestFolder";

	/** Set Request Folder.
	  * EMail folder to process incoming emails;
 if empty INBOX is used
	  */
	public void setRequestFolder (String RequestFolder);

	/** Get Request Folder.
	  * EMail folder to process incoming emails;
 if empty INBOX is used
	  */
	public String getRequestFolder();

    /** Column name RequestUser */
    public static final String COLUMNNAME_RequestUser = "RequestUser";

	/** Set Request User.
	  * User Name (ID) of the email owner
	  */
	public void setRequestUser (String RequestUser);

	/** Get Request User.
	  * User Name (ID) of the email owner
	  */
	public String getRequestUser();

    /** Column name RequestUserPW */
    public static final String COLUMNNAME_RequestUserPW = "RequestUserPW";

	/** Set Request User Password.
	  * Password of the user name (ID) for mail processing
	  */
	public void setRequestUserPW (String RequestUserPW);

	/** Get Request User Password.
	  * Password of the user name (ID) for mail processing
	  */
	public String getRequestUserPW();

    /** Column name SMTPHost */
    public static final String COLUMNNAME_SMTPHost = "SMTPHost";

	/** Set Mail Host.
	  * Hostname of Mail Server for SMTP and IMAP
	  */
	public void setSMTPHost (String SMTPHost);

	/** Get Mail Host.
	  * Hostname of Mail Server for SMTP and IMAP
	  */
	public String getSMTPHost();

    /** Column name StoreArchiveOnFileSystem */
    public static final String COLUMNNAME_StoreArchiveOnFileSystem = "StoreArchiveOnFileSystem";

	/** Set Store Archive On File System	  */
	public void setStoreArchiveOnFileSystem (boolean StoreArchiveOnFileSystem);

	/** Get Store Archive On File System	  */
	public boolean isStoreArchiveOnFileSystem();

    /** Column name StoreAttachmentsOnFileSystem */
    public static final String COLUMNNAME_StoreAttachmentsOnFileSystem = "StoreAttachmentsOnFileSystem";

	/** Set Store Attachments On File System	  */
	public void setStoreAttachmentsOnFileSystem (boolean StoreAttachmentsOnFileSystem);

	/** Get Store Attachments On File System	  */
	public boolean isStoreAttachmentsOnFileSystem();

    /** Column name UnixArchivePath */
    public static final String COLUMNNAME_UnixArchivePath = "UnixArchivePath";

	/** Set Unix Archive Path	  */
	public void setUnixArchivePath (String UnixArchivePath);

	/** Get Unix Archive Path	  */
	public String getUnixArchivePath();

    /** Column name UnixAttachmentPath */
    public static final String COLUMNNAME_UnixAttachmentPath = "UnixAttachmentPath";

	/** Set Unix Attachment Path	  */
	public void setUnixAttachmentPath (String UnixAttachmentPath);

	/** Get Unix Attachment Path	  */
	public String getUnixAttachmentPath();

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

    /** Column name WindowsArchivePath */
    public static final String COLUMNNAME_WindowsArchivePath = "WindowsArchivePath";

	/** Set Windows Archive Path	  */
	public void setWindowsArchivePath (String WindowsArchivePath);

	/** Get Windows Archive Path	  */
	public String getWindowsArchivePath();

    /** Column name WindowsAttachmentPath */
    public static final String COLUMNNAME_WindowsAttachmentPath = "WindowsAttachmentPath";

	/** Set Windows Attachment Path	  */
	public void setWindowsAttachmentPath (String WindowsAttachmentPath);

	/** Get Windows Attachment Path	  */
	public String getWindowsAttachmentPath();
}
