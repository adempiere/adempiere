/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_AttachmentReference
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4
 */
public interface I_AD_AttachmentReference 
{

    /** TableName=AD_AttachmentReference */
    public static final String Table_Name = "AD_AttachmentReference";

    /** AD_Table_ID=54626 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Archive_ID */
    public static final String COLUMNNAME_AD_Archive_ID = "AD_Archive_ID";

	/** Set Archive.
	  * Document and Report Archive
	  */
	public void setAD_Archive_ID (int AD_Archive_ID);

	/** Get Archive.
	  * Document and Report Archive
	  */
	public int getAD_Archive_ID();

	public org.adempiere.core.domains.models.I_AD_Archive getAD_Archive() throws RuntimeException;

    /** Column name AD_Attachment_ID */
    public static final String COLUMNNAME_AD_Attachment_ID = "AD_Attachment_ID";

	/** Set Attachment.
	  * Attachment for the document
	  */
	public void setAD_Attachment_ID (int AD_Attachment_ID);

	/** Get Attachment.
	  * Attachment for the document
	  */
	public int getAD_Attachment_ID();

	public org.adempiere.core.domains.models.I_AD_Attachment getAD_Attachment() throws RuntimeException;

    /** Column name AD_AttachmentReference_ID */
    public static final String COLUMNNAME_AD_AttachmentReference_ID = "AD_AttachmentReference_ID";

	/** Set Attachment Reference.
	  * Used for save reference for all attachment files
	  */
	public void setAD_AttachmentReference_ID (int AD_AttachmentReference_ID);

	/** Get Attachment Reference.
	  * Used for save reference for all attachment files
	  */
	public int getAD_AttachmentReference_ID();

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Image_ID */
    public static final String COLUMNNAME_AD_Image_ID = "AD_Image_ID";

	/** Set Image.
	  * Image or Icon
	  */
	public void setAD_Image_ID (int AD_Image_ID);

	/** Get Image.
	  * Image or Icon
	  */
	public int getAD_Image_ID();

	public org.adempiere.core.domains.models.I_AD_Image getAD_Image() throws RuntimeException;

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

    /** Column name FileHandler_ID */
    public static final String COLUMNNAME_FileHandler_ID = "FileHandler_ID";

	/** Set File Handler.
	  * File Handler Registered as App Registration for handle all file system
	  */
	public void setFileHandler_ID (int FileHandler_ID);

	/** Get File Handler.
	  * File Handler Registered as App Registration for handle all file system
	  */
	public int getFileHandler_ID();

	public org.adempiere.core.domains.models.I_AD_AppRegistration getFileHandler() throws RuntimeException;

    /** Column name FileName */
    public static final String COLUMNNAME_FileName = "FileName";

	/** Set File Name.
	  * Name of the local file or URL
	  */
	public void setFileName (String FileName);

	/** Get File Name.
	  * Name of the local file or URL
	  */
	public String getFileName();

    /** Column name FileSize */
    public static final String COLUMNNAME_FileSize = "FileSize";

	/** Set File Size.
	  * Size of the File in bytes
	  */
	public void setFileSize (BigDecimal FileSize);

	/** Get File Size.
	  * Size of the File in bytes
	  */
	public BigDecimal getFileSize();

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

    /** Column name TextMsg */
    public static final String COLUMNNAME_TextMsg = "TextMsg";

	/** Set Text Message.
	  * Text Message
	  */
	public void setTextMsg (String TextMsg);

	/** Get Text Message.
	  * Text Message
	  */
	public String getTextMsg();

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

    /** Column name URL */
    public static final String COLUMNNAME_URL = "URL";

	/** Set URL.
	  * Full URL address - e.g. http://www.adempiere.org
	  */
	public void setURL (String URL);

	/** Get URL.
	  * Full URL address - e.g. http://www.adempiere.org
	  */
	public String getURL();

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
}
