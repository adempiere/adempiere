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
/** Generated Model - DO NOT CHANGE */
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_AttachmentReference
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4 - $Id$ */
public class X_AD_AttachmentReference extends PO implements I_AD_AttachmentReference, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20230102L;

    /** Standard Constructor */
    public X_AD_AttachmentReference (Properties ctx, int AD_AttachmentReference_ID, String trxName)
    {
      super (ctx, AD_AttachmentReference_ID, trxName);
      /** if (AD_AttachmentReference_ID == 0)
        {
			setAD_AttachmentReference_ID (0);
			setFileHandler_ID (0);
			setFileName (null);
        } */
    }

    /** Load Constructor */
    public X_AD_AttachmentReference (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_AD_AttachmentReference[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.adempiere.core.domains.models.I_AD_Archive getAD_Archive() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Archive)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Archive.Table_Name)
			.getPO(getAD_Archive_ID(), get_TrxName());	}

	/** Set Archive.
		@param AD_Archive_ID 
		Document and Report Archive
	  */
	public void setAD_Archive_ID (int AD_Archive_ID)
	{
		if (AD_Archive_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Archive_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Archive_ID, Integer.valueOf(AD_Archive_ID));
	}

	/** Get Archive.
		@return Document and Report Archive
	  */
	public int getAD_Archive_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Archive_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_Attachment getAD_Attachment() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Attachment)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Attachment.Table_Name)
			.getPO(getAD_Attachment_ID(), get_TrxName());	}

	/** Set Attachment.
		@param AD_Attachment_ID 
		Attachment for the document
	  */
	public void setAD_Attachment_ID (int AD_Attachment_ID)
	{
		if (AD_Attachment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Attachment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Attachment_ID, Integer.valueOf(AD_Attachment_ID));
	}

	/** Get Attachment.
		@return Attachment for the document
	  */
	public int getAD_Attachment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Attachment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Attachment Reference.
		@param AD_AttachmentReference_ID 
		Used for save reference for all attachment files
	  */
	public void setAD_AttachmentReference_ID (int AD_AttachmentReference_ID)
	{
		if (AD_AttachmentReference_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_AttachmentReference_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_AttachmentReference_ID, Integer.valueOf(AD_AttachmentReference_ID));
	}

	/** Get Attachment Reference.
		@return Used for save reference for all attachment files
	  */
	public int getAD_AttachmentReference_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_AttachmentReference_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_AD_Image getAD_Image() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_Image)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_Image.Table_Name)
			.getPO(getAD_Image_ID(), get_TrxName());	}

	/** Set Image.
		@param AD_Image_ID 
		Image or Icon
	  */
	public void setAD_Image_ID (int AD_Image_ID)
	{
		if (AD_Image_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Image_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Image_ID, Integer.valueOf(AD_Image_ID));
	}

	/** Get Image.
		@return Image or Icon
	  */
	public int getAD_Image_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Image_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.adempiere.core.domains.models.I_AD_AppRegistration getFileHandler() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_AD_AppRegistration)MTable.get(getCtx(), org.adempiere.core.domains.models.I_AD_AppRegistration.Table_Name)
			.getPO(getFileHandler_ID(), get_TrxName());	}

	/** Set File Handler.
		@param FileHandler_ID 
		File Handler Registered as App Registration for handle all file system
	  */
	public void setFileHandler_ID (int FileHandler_ID)
	{
		if (FileHandler_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FileHandler_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FileHandler_ID, Integer.valueOf(FileHandler_ID));
	}

	/** Get File Handler.
		@return File Handler Registered as App Registration for handle all file system
	  */
	public int getFileHandler_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FileHandler_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set File Name.
		@param FileName 
		Name of the local file or URL
	  */
	public void setFileName (String FileName)
	{
		set_ValueNoCheck (COLUMNNAME_FileName, FileName);
	}

	/** Get File Name.
		@return Name of the local file or URL
	  */
	public String getFileName () 
	{
		return (String)get_Value(COLUMNNAME_FileName);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getFileName());
    }

	/** Set File Size.
		@param FileSize 
		Size of the File in bytes
	  */
	public void setFileSize (BigDecimal FileSize)
	{
		set_Value (COLUMNNAME_FileSize, FileSize);
	}

	/** Get File Size.
		@return Size of the File in bytes
	  */
	public BigDecimal getFileSize () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FileSize);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Text Message.
		@param TextMsg 
		Text Message
	  */
	public void setTextMsg (String TextMsg)
	{
		set_Value (COLUMNNAME_TextMsg, TextMsg);
	}

	/** Get Text Message.
		@return Text Message
	  */
	public String getTextMsg () 
	{
		return (String)get_Value(COLUMNNAME_TextMsg);
	}

	/** Set URL.
		@param URL 
		Full URL address - e.g. http://www.adempiere.org
	  */
	public void setURL (String URL)
	{
		set_Value (COLUMNNAME_URL, URL);
	}

	/** Get URL.
		@return Full URL address - e.g. http://www.adempiere.org
	  */
	public String getURL () 
	{
		return (String)get_Value(COLUMNNAME_URL);
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
}