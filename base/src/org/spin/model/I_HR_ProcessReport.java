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
package org.spin.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_ProcessReport
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2
 */
public interface I_HR_ProcessReport 
{

    /** TableName=HR_ProcessReport */
    public static final String Table_Name = "HR_ProcessReport";

    /** AD_Table_ID=54326 */
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

    /** Column name AD_PrintFormat_ID */
    public static final String COLUMNNAME_AD_PrintFormat_ID = "AD_PrintFormat_ID";

	/** Set Print Format.
	  * Data Print Format
	  */
	public void setAD_PrintFormat_ID (int AD_PrintFormat_ID);

	/** Get Print Format.
	  * Data Print Format
	  */
	public int getAD_PrintFormat_ID();

	public org.compiere.model.I_AD_PrintFormat getAD_PrintFormat() throws RuntimeException;

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

    /** Column name FileExportClass */
    public static final String COLUMNNAME_FileExportClass = "FileExportClass";

	/** Set File Export Class.
	  * Used for Export Data
	  */
	public void setFileExportClass (String FileExportClass);

	/** Get File Export Class.
	  * Used for Export Data
	  */
	public String getFileExportClass();

    /** Column name HR_ProcessReport_ID */
    public static final String COLUMNNAME_HR_ProcessReport_ID = "HR_ProcessReport_ID";

	/** Set Payroll Process Report	  */
	public void setHR_ProcessReport_ID (int HR_ProcessReport_ID);

	/** Get Payroll Process Report	  */
	public int getHR_ProcessReport_ID();

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

    /** Column name IsCanExport */
    public static final String COLUMNNAME_IsCanExport = "IsCanExport";

	/** Set Can Export.
	  * Users with this role can export data
	  */
	public void setIsCanExport (boolean IsCanExport);

	/** Get Can Export.
	  * Users with this role can export data
	  */
	public boolean isCanExport();

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

    /** Column name PrintName */
    public static final String COLUMNNAME_PrintName = "PrintName";

	/** Set Print Text.
	  * The label text to be printed on a document or correspondence.
	  */
	public void setPrintName (String PrintName);

	/** Get Print Text.
	  * The label text to be printed on a document or correspondence.
	  */
	public String getPrintName();

    /** Column name ReceiptFooterMsg */
    public static final String COLUMNNAME_ReceiptFooterMsg = "ReceiptFooterMsg";

	/** Set Receipt Footer Msg.
	  * This message will be displayed at the bottom of a receipt when doing a sales or purchase
	  */
	public void setReceiptFooterMsg (String ReceiptFooterMsg);

	/** Get Receipt Footer Msg.
	  * This message will be displayed at the bottom of a receipt when doing a sales or purchase
	  */
	public String getReceiptFooterMsg();

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
}
