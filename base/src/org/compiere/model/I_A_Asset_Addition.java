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

/** Generated Interface for A_Asset_Addition
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_A_Asset_Addition 
{

    /** TableName=A_Asset_Addition */
    public static final String Table_Name = "A_Asset_Addition";

    /** AD_Table_ID=53137 */
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

    /** Column name A_Accumulated_Depr_Adjust */
    public static final String COLUMNNAME_A_Accumulated_Depr_Adjust = "A_Accumulated_Depr_Adjust";

	/** Set Adjust Accumulated Depreciation	  */
	public void setA_Accumulated_Depr_Adjust (boolean A_Accumulated_Depr_Adjust);

	/** Get Adjust Accumulated Depreciation	  */
	public boolean isA_Accumulated_Depr_Adjust();

    /** Column name A_Accumulated_Depr_F */
    public static final String COLUMNNAME_A_Accumulated_Depr_F = "A_Accumulated_Depr_F";

	/** Set Accumulated Depreciation (fiscal)	  */
	public void setA_Accumulated_Depr_F (BigDecimal A_Accumulated_Depr_F);

	/** Get Accumulated Depreciation (fiscal)	  */
	public BigDecimal getA_Accumulated_Depr_F();

    /** Column name A_Asset_Addition_ID */
    public static final String COLUMNNAME_A_Asset_Addition_ID = "A_Asset_Addition_ID";

	/** Set Asset Addition	  */
	public void setA_Asset_Addition_ID (int A_Asset_Addition_ID);

	/** Get Asset Addition	  */
	public int getA_Asset_Addition_ID();

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

    /** Column name A_CapvsExp */
    public static final String COLUMNNAME_A_CapvsExp = "A_CapvsExp";

	/** Set Capital/Expense	  */
	public void setA_CapvsExp (String A_CapvsExp);

	/** Get Capital/Expense	  */
	public String getA_CapvsExp();

    /** Column name A_CreateAsset */
    public static final String COLUMNNAME_A_CreateAsset = "A_CreateAsset";

	/** Set Create Asset	  */
	public void setA_CreateAsset (boolean A_CreateAsset);

	/** Get Create Asset	  */
	public boolean isA_CreateAsset();

    /** Column name A_Life_Period_Max */
    public static final String COLUMNNAME_A_Life_Period_Max = "A_Life_Period_Max";

	/** Set Life periods (max)	  */
	public void setA_Life_Period_Max (int A_Life_Period_Max);

	/** Get Life periods (max)	  */
	public int getA_Life_Period_Max();

    /** Column name A_Life_Period_Min */
    public static final String COLUMNNAME_A_Life_Period_Min = "A_Life_Period_Min";

	/** Set Life periods (min)	  */
	public void setA_Life_Period_Min (int A_Life_Period_Min);

	/** Get Life periods (min)	  */
	public int getA_Life_Period_Min();

    /** Column name A_Period_Start */
    public static final String COLUMNNAME_A_Period_Start = "A_Period_Start";

	/** Set A_Period_Start	  */
	public void setA_Period_Start (int A_Period_Start);

	/** Get A_Period_Start	  */
	public int getA_Period_Start();

    /** Column name A_QTY_Current */
    public static final String COLUMNNAME_A_QTY_Current = "A_QTY_Current";

	/** Set Fixed Asset Current Qty.
	  * Fixed Asset Current Quantity
	  */
	public void setA_QTY_Current (BigDecimal A_QTY_Current);

	/** Get Fixed Asset Current Qty.
	  * Fixed Asset Current Quantity
	  */
	public BigDecimal getA_QTY_Current();

    /** Column name A_Salvage_Value */
    public static final String COLUMNNAME_A_Salvage_Value = "A_Salvage_Value";

	/** Set Asset Salvage Value	  */
	public void setA_Salvage_Value (BigDecimal A_Salvage_Value);

	/** Get Asset Salvage Value	  */
	public BigDecimal getA_Salvage_Value();

    /** Column name A_SourceType */
    public static final String COLUMNNAME_A_SourceType = "A_SourceType";

	/** Set Source Type	  */
	public void setA_SourceType (String A_SourceType);

	/** Get Source Type	  */
	public String getA_SourceType();

    /** Column name AssetAmtEntered */
    public static final String COLUMNNAME_AssetAmtEntered = "AssetAmtEntered";

	/** Set Entered Amount	  */
	public void setAssetAmtEntered (BigDecimal AssetAmtEntered);

	/** Get Entered Amount	  */
	public BigDecimal getAssetAmtEntered();

    /** Column name AssetSourceAmt */
    public static final String COLUMNNAME_AssetSourceAmt = "AssetSourceAmt";

	/** Set Source Amount	  */
	public void setAssetSourceAmt (BigDecimal AssetSourceAmt);

	/** Get Source Amount	  */
	public BigDecimal getAssetSourceAmt();

    /** Column name AssetValueAmt */
    public static final String COLUMNNAME_AssetValueAmt = "AssetValueAmt";

	/** Set Asset value.
	  * Book Value of the asset
	  */
	public void setAssetValueAmt (BigDecimal AssetValueAmt);

	/** Get Asset value.
	  * Book Value of the asset
	  */
	public BigDecimal getAssetValueAmt();

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException;

    /** Column name C_ConversionType_ID */
    public static final String COLUMNNAME_C_ConversionType_ID = "C_ConversionType_ID";

	/** Set Currency Type.
	  * Currency Conversion Rate Type
	  */
	public void setC_ConversionType_ID (int C_ConversionType_ID);

	/** Get Currency Type.
	  * Currency Conversion Rate Type
	  */
	public int getC_ConversionType_ID();

	public org.compiere.model.I_C_ConversionType getC_ConversionType() throws RuntimeException;

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException;

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

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

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

    /** Column name CurrencyRate */
    public static final String COLUMNNAME_CurrencyRate = "CurrencyRate";

	/** Set Rate.
	  * Currency Conversion Rate
	  */
	public void setCurrencyRate (BigDecimal CurrencyRate);

	/** Get Rate.
	  * Currency Conversion Rate
	  */
	public BigDecimal getCurrencyRate();

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

    /** Column name DeltaUseLifeYears */
    public static final String COLUMNNAME_DeltaUseLifeYears = "DeltaUseLifeYears";

	/** Set Delta Use Life Years	  */
	public void setDeltaUseLifeYears (int DeltaUseLifeYears);

	/** Get Delta Use Life Years	  */
	public int getDeltaUseLifeYears();

    /** Column name DeltaUseLifeYears_F */
    public static final String COLUMNNAME_DeltaUseLifeYears_F = "DeltaUseLifeYears_F";

	/** Set Delta Use Life Years (fiscal).
	  * Delta Use Life Years (fiscal)
	  */
	public void setDeltaUseLifeYears_F (int DeltaUseLifeYears_F);

	/** Get Delta Use Life Years (fiscal).
	  * Delta Use Life Years (fiscal)
	  */
	public int getDeltaUseLifeYears_F();

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

    /** Column name GL_JournalBatch_ID */
    public static final String COLUMNNAME_GL_JournalBatch_ID = "GL_JournalBatch_ID";

	/** Set Journal Batch.
	  * General Ledger Journal Batch
	  */
	public void setGL_JournalBatch_ID (int GL_JournalBatch_ID);

	/** Get Journal Batch.
	  * General Ledger Journal Batch
	  */
	public int getGL_JournalBatch_ID();

	public org.compiere.model.I_GL_JournalBatch getGL_JournalBatch() throws RuntimeException;

    /** Column name I_FixedAsset_ID */
    public static final String COLUMNNAME_I_FixedAsset_ID = "I_FixedAsset_ID";

	/** Set Imported Fixed Asset	  */
	public void setI_FixedAsset_ID (int I_FixedAsset_ID);

	/** Get Imported Fixed Asset	  */
	public int getI_FixedAsset_ID();

	public org.compiere.model.I_I_FixedAsset getI_FixedAsset() throws RuntimeException;

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

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name M_AttributeSetInstance_ID */
    public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";

	/** Set Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID);

	/** Get Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID();

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException;

    /** Column name M_InOutLine_ID */
    public static final String COLUMNNAME_M_InOutLine_ID = "M_InOutLine_ID";

	/** Set Shipment/Receipt Line.
	  * Line on Shipment or Receipt document
	  */
	public void setM_InOutLine_ID (int M_InOutLine_ID);

	/** Get Shipment/Receipt Line.
	  * Line on Shipment or Receipt document
	  */
	public int getM_InOutLine_ID();

	public org.compiere.model.I_M_InOutLine getM_InOutLine() throws RuntimeException;

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

	public I_M_Locator getM_Locator() throws RuntimeException;

    /** Column name M_MatchInv_ID */
    public static final String COLUMNNAME_M_MatchInv_ID = "M_MatchInv_ID";

	/** Set Match Invoice.
	  * Match Shipment/Receipt to Invoice
	  */
	public void setM_MatchInv_ID (int M_MatchInv_ID);

	/** Get Match Invoice.
	  * Match Shipment/Receipt to Invoice
	  */
	public int getM_MatchInv_ID();

	public org.compiere.model.I_M_MatchInv getM_MatchInv() throws RuntimeException;

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

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
