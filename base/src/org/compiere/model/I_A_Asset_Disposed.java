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

/** Generated Interface for A_Asset_Disposed
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_A_Asset_Disposed 
{

    /** TableName=A_Asset_Disposed */
    public static final String Table_Name = "A_Asset_Disposed";

    /** AD_Table_ID=53127 */
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

    /** Column name A_Accumulated_Depr */
    public static final String COLUMNNAME_A_Accumulated_Depr = "A_Accumulated_Depr";

	/** Set Accumulated Depreciation	  */
	public void setA_Accumulated_Depr (BigDecimal A_Accumulated_Depr);

	/** Get Accumulated Depreciation	  */
	public BigDecimal getA_Accumulated_Depr();

    /** Column name A_Accumulated_Depr_Delta */
    public static final String COLUMNNAME_A_Accumulated_Depr_Delta = "A_Accumulated_Depr_Delta";

	/** Set Accumulated Depreciation (delta)	  */
	public void setA_Accumulated_Depr_Delta (BigDecimal A_Accumulated_Depr_Delta);

	/** Get Accumulated Depreciation (delta)	  */
	public BigDecimal getA_Accumulated_Depr_Delta();

    /** Column name A_Activation_Method */
    public static final String COLUMNNAME_A_Activation_Method = "A_Activation_Method";

	/** Set Activation Method	  */
	public void setA_Activation_Method (String A_Activation_Method);

	/** Get Activation Method	  */
	public String getA_Activation_Method();

    /** Column name A_Asset_Cost */
    public static final String COLUMNNAME_A_Asset_Cost = "A_Asset_Cost";

	/** Set Fixed Asset Cost.
	  * Cost of acquisition of the Fixed Asset
	  */
	public void setA_Asset_Cost (BigDecimal A_Asset_Cost);

	/** Get Fixed Asset Cost.
	  * Cost of acquisition of the Fixed Asset
	  */
	public BigDecimal getA_Asset_Cost();

    /** Column name A_Asset_Disposed_ID */
    public static final String COLUMNNAME_A_Asset_Disposed_ID = "A_Asset_Disposed_ID";

	/** Set Asset Disposed	  */
	public void setA_Asset_Disposed_ID (int A_Asset_Disposed_ID);

	/** Get Asset Disposed	  */
	public int getA_Asset_Disposed_ID();

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Fixed Asset.
	  * Fixed Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Fixed Asset.
	  * Fixed Asset used internally or by customers
	  */
	public int getA_Asset_ID();

	public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException;

    /** Column name A_Asset_Status */
    public static final String COLUMNNAME_A_Asset_Status = "A_Asset_Status";

	/** Set Asset Status	  */
	public void setA_Asset_Status (String A_Asset_Status);

	/** Get Asset Status	  */
	public String getA_Asset_Status();

    /** Column name A_Asset_Trade_ID */
    public static final String COLUMNNAME_A_Asset_Trade_ID = "A_Asset_Trade_ID";

	/** Set Asset Trade	  */
	public void setA_Asset_Trade_ID (int A_Asset_Trade_ID);

	/** Get Asset Trade	  */
	public int getA_Asset_Trade_ID();

	public org.compiere.model.I_A_Asset getA_Asset_Trade() throws RuntimeException;

    /** Column name A_Disposal_Amt */
    public static final String COLUMNNAME_A_Disposal_Amt = "A_Disposal_Amt";

	/** Set Disposal Amount	  */
	public void setA_Disposal_Amt (BigDecimal A_Disposal_Amt);

	/** Get Disposal Amount	  */
	public BigDecimal getA_Disposal_Amt();

    /** Column name A_Disposed_Date */
    public static final String COLUMNNAME_A_Disposed_Date = "A_Disposed_Date";

	/** Set Disposed Date	  */
	public void setA_Disposed_Date (Timestamp A_Disposed_Date);

	/** Get Disposed Date	  */
	public Timestamp getA_Disposed_Date();

    /** Column name A_Disposed_Method */
    public static final String COLUMNNAME_A_Disposed_Method = "A_Disposed_Method";

	/** Set Disposed Method	  */
	public void setA_Disposed_Method (String A_Disposed_Method);

	/** Get Disposed Method	  */
	public String getA_Disposed_Method();

    /** Column name A_Disposed_Reason */
    public static final String COLUMNNAME_A_Disposed_Reason = "A_Disposed_Reason";

	/** Set Disposed Reason	  */
	public void setA_Disposed_Reason (String A_Disposed_Reason);

	/** Get Disposed Reason	  */
	public String getA_Disposed_Reason();

    /** Column name A_Proceeds */
    public static final String COLUMNNAME_A_Proceeds = "A_Proceeds";

	/** Set Asset Proceeds	  */
	public void setA_Proceeds (BigDecimal A_Proceeds);

	/** Get Asset Proceeds	  */
	public BigDecimal getA_Proceeds();

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

    /** Column name C_InvoiceLine_ID */
    public static final String COLUMNNAME_C_InvoiceLine_ID = "C_InvoiceLine_ID";

	/** Set Invoice Line.
	  * Invoice Detail Line
	  */
	public void setC_InvoiceLine_ID (int C_InvoiceLine_ID);

	/** Get Invoice Line.
	  * Invoice Detail Line
	  */
	public int getC_InvoiceLine_ID();

	public org.compiere.model.I_C_InvoiceLine getC_InvoiceLine() throws RuntimeException;

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException;

    /** Column name C_Period_ID */
    public static final String COLUMNNAME_C_Period_ID = "C_Period_ID";

	/** Set Period.
	  * Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID);

	/** Get Period.
	  * Period of the Calendar
	  */
	public int getC_Period_ID();

	public org.compiere.model.I_C_Period getC_Period() throws RuntimeException;

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

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

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

    /** Column name Expense */
    public static final String COLUMNNAME_Expense = "Expense";

	/** Set Expense	  */
	public void setExpense (BigDecimal Expense);

	/** Get Expense	  */
	public BigDecimal getExpense();

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

    /** Column name IsDisposed */
    public static final String COLUMNNAME_IsDisposed = "IsDisposed";

	/** Set Disposed.
	  * The asset is disposed
	  */
	public void setIsDisposed (boolean IsDisposed);

	/** Get Disposed.
	  * The asset is disposed
	  */
	public boolean isDisposed();

    /** Column name Posted */
    public static final String COLUMNNAME_Posted = "Posted";

	/** Set Posted.
	  * Posting status
	  */
	public void setPosted (boolean Posted);

	/** Get Posted.
	  * Posting status
	  */
	public boolean isPosted();

    /** Column name PostingType */
    public static final String COLUMNNAME_PostingType = "PostingType";

	/** Set Posting Type.
	  * The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType);

	/** Get Posting Type.
	  * The type of posted amount for the transaction
	  */
	public String getPostingType();

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

    /** Column name ProcessedOn */
    public static final String COLUMNNAME_ProcessedOn = "ProcessedOn";

	/** Set Processed On.
	  * The date+time (expressed in decimal format) when the document has been processed
	  */
	public void setProcessedOn (BigDecimal ProcessedOn);

	/** Get Processed On.
	  * The date+time (expressed in decimal format) when the document has been processed
	  */
	public BigDecimal getProcessedOn();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

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
