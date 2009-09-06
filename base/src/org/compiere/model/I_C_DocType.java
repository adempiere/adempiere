/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_DocType
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_C_DocType 
{

    /** TableName=C_DocType */
    public static final String Table_Name = "C_DocType";

    /** AD_Table_ID=217 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

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

	public I_AD_PrintFormat getAD_PrintFormat() throws RuntimeException;

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

    /** Column name C_DocTypeDifference_ID */
    public static final String COLUMNNAME_C_DocTypeDifference_ID = "C_DocTypeDifference_ID";

	/** Set Difference Document.
	  * Document type for generating in dispute Shipments
	  */
	public void setC_DocTypeDifference_ID (int C_DocTypeDifference_ID);

	/** Get Difference Document.
	  * Document type for generating in dispute Shipments
	  */
	public int getC_DocTypeDifference_ID();

    /** Column name C_DocTypeInvoice_ID */
    public static final String COLUMNNAME_C_DocTypeInvoice_ID = "C_DocTypeInvoice_ID";

	/** Set Document Type for Invoice.
	  * Document type used for invoices generated from this sales document
	  */
	public void setC_DocTypeInvoice_ID (int C_DocTypeInvoice_ID);

	/** Get Document Type for Invoice.
	  * Document type used for invoices generated from this sales document
	  */
	public int getC_DocTypeInvoice_ID();

    /** Column name C_DocTypeProforma_ID */
    public static final String COLUMNNAME_C_DocTypeProforma_ID = "C_DocTypeProforma_ID";

	/** Set Document Type for ProForma.
	  * Document type used for pro forma invoices generated from this sales document
	  */
	public void setC_DocTypeProforma_ID (int C_DocTypeProforma_ID);

	/** Get Document Type for ProForma.
	  * Document type used for pro forma invoices generated from this sales document
	  */
	public int getC_DocTypeProforma_ID();

    /** Column name C_DocTypeShipment_ID */
    public static final String COLUMNNAME_C_DocTypeShipment_ID = "C_DocTypeShipment_ID";

	/** Set Document Type for Shipment.
	  * Document type used for shipments generated from this sales document
	  */
	public void setC_DocTypeShipment_ID (int C_DocTypeShipment_ID);

	/** Get Document Type for Shipment.
	  * Document type used for shipments generated from this sales document
	  */
	public int getC_DocTypeShipment_ID();

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

    /** Column name DefiniteSequence_ID */
    public static final String COLUMNNAME_DefiniteSequence_ID = "DefiniteSequence_ID";

	/** Set Definite Sequence	  */
	public void setDefiniteSequence_ID (int DefiniteSequence_ID);

	/** Get Definite Sequence	  */
	public int getDefiniteSequence_ID();

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

    /** Column name DocBaseType */
    public static final String COLUMNNAME_DocBaseType = "DocBaseType";

	/** Set Document BaseType.
	  * Logical type of document
	  */
	public void setDocBaseType (String DocBaseType);

	/** Get Document BaseType.
	  * Logical type of document
	  */
	public String getDocBaseType();

    /** Column name DocNoSequence_ID */
    public static final String COLUMNNAME_DocNoSequence_ID = "DocNoSequence_ID";

	/** Set Document Sequence.
	  * Document sequence determines the numbering of documents
	  */
	public void setDocNoSequence_ID (int DocNoSequence_ID);

	/** Get Document Sequence.
	  * Document sequence determines the numbering of documents
	  */
	public int getDocNoSequence_ID();

    /** Column name DocSubTypeSO */
    public static final String COLUMNNAME_DocSubTypeSO = "DocSubTypeSO";

	/** Set SO Sub Type.
	  * Sales Order Sub Type
	  */
	public void setDocSubTypeSO (String DocSubTypeSO);

	/** Get SO Sub Type.
	  * Sales Order Sub Type
	  */
	public String getDocSubTypeSO();

    /** Column name DocumentCopies */
    public static final String COLUMNNAME_DocumentCopies = "DocumentCopies";

	/** Set Document Copies.
	  * Number of copies to be printed
	  */
	public void setDocumentCopies (int DocumentCopies);

	/** Get Document Copies.
	  * Number of copies to be printed
	  */
	public int getDocumentCopies();

    /** Column name DocumentNote */
    public static final String COLUMNNAME_DocumentNote = "DocumentNote";

	/** Set Document Note.
	  * Additional information for a Document
	  */
	public void setDocumentNote (String DocumentNote);

	/** Get Document Note.
	  * Additional information for a Document
	  */
	public String getDocumentNote();

    /** Column name GL_Category_ID */
    public static final String COLUMNNAME_GL_Category_ID = "GL_Category_ID";

	/** Set GL Category.
	  * General Ledger Category
	  */
	public void setGL_Category_ID (int GL_Category_ID);

	/** Get GL Category.
	  * General Ledger Category
	  */
	public int getGL_Category_ID();

	public I_GL_Category getGL_Category() throws RuntimeException;

    /** Column name HasCharges */
    public static final String COLUMNNAME_HasCharges = "HasCharges";

	/** Set Charges.
	  * Charges can be added to the document
	  */
	public void setHasCharges (boolean HasCharges);

	/** Get Charges.
	  * Charges can be added to the document
	  */
	public boolean isHasCharges();

    /** Column name HasProforma */
    public static final String COLUMNNAME_HasProforma = "HasProforma";

	/** Set Pro forma Invoice.
	  * Indicates if Pro Forma Invoices can be generated from this document
	  */
	public void setHasProforma (boolean HasProforma);

	/** Get Pro forma Invoice.
	  * Indicates if Pro Forma Invoices can be generated from this document
	  */
	public boolean isHasProforma();

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

    /** Column name IsCreateCounter */
    public static final String COLUMNNAME_IsCreateCounter = "IsCreateCounter";

	/** Set Create Counter Document.
	  * Create Counter Document
	  */
	public void setIsCreateCounter (boolean IsCreateCounter);

	/** Get Create Counter Document.
	  * Create Counter Document
	  */
	public boolean isCreateCounter();

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

    /** Column name IsDefaultCounterDoc */
    public static final String COLUMNNAME_IsDefaultCounterDoc = "IsDefaultCounterDoc";

	/** Set Default Counter Document.
	  * The document type is the default counter document type
	  */
	public void setIsDefaultCounterDoc (boolean IsDefaultCounterDoc);

	/** Get Default Counter Document.
	  * The document type is the default counter document type
	  */
	public boolean isDefaultCounterDoc();

    /** Column name IsDocNoControlled */
    public static final String COLUMNNAME_IsDocNoControlled = "IsDocNoControlled";

	/** Set Document is Number Controlled.
	  * The document has a document sequence
	  */
	public void setIsDocNoControlled (boolean IsDocNoControlled);

	/** Get Document is Number Controlled.
	  * The document has a document sequence
	  */
	public boolean isDocNoControlled();

    /** Column name IsIndexed */
    public static final String COLUMNNAME_IsIndexed = "IsIndexed";

	/** Set Indexed.
	  * Index the document for the internal search engine
	  */
	public void setIsIndexed (boolean IsIndexed);

	/** Get Indexed.
	  * Index the document for the internal search engine
	  */
	public boolean isIndexed();

    /** Column name IsInTransit */
    public static final String COLUMNNAME_IsInTransit = "IsInTransit";

	/** Set In Transit.
	  * Movement is in transit
	  */
	public void setIsInTransit (boolean IsInTransit);

	/** Get In Transit.
	  * Movement is in transit
	  */
	public boolean isInTransit();

    /** Column name IsOverwriteDateOnComplete */
    public static final String COLUMNNAME_IsOverwriteDateOnComplete = "IsOverwriteDateOnComplete";

	/** Set Overwrite Date on Complete	  */
	public void setIsOverwriteDateOnComplete (boolean IsOverwriteDateOnComplete);

	/** Get Overwrite Date on Complete	  */
	public boolean isOverwriteDateOnComplete();

    /** Column name IsOverwriteSeqOnComplete */
    public static final String COLUMNNAME_IsOverwriteSeqOnComplete = "IsOverwriteSeqOnComplete";

	/** Set Overwrite Sequence on Complete	  */
	public void setIsOverwriteSeqOnComplete (boolean IsOverwriteSeqOnComplete);

	/** Get Overwrite Sequence on Complete	  */
	public boolean isOverwriteSeqOnComplete();

    /** Column name IsPickQAConfirm */
    public static final String COLUMNNAME_IsPickQAConfirm = "IsPickQAConfirm";

	/** Set Pick/QA Confirmation.
	  * Require Pick or QA Confirmation before processing
	  */
	public void setIsPickQAConfirm (boolean IsPickQAConfirm);

	/** Get Pick/QA Confirmation.
	  * Require Pick or QA Confirmation before processing
	  */
	public boolean isPickQAConfirm();

    /** Column name IsShipConfirm */
    public static final String COLUMNNAME_IsShipConfirm = "IsShipConfirm";

	/** Set Ship/Receipt Confirmation.
	  * Require Ship or Receipt Confirmation before processing
	  */
	public void setIsShipConfirm (boolean IsShipConfirm);

	/** Get Ship/Receipt Confirmation.
	  * Require Ship or Receipt Confirmation before processing
	  */
	public boolean isShipConfirm();

    /** Column name IsSOTrx */
    public static final String COLUMNNAME_IsSOTrx = "IsSOTrx";

	/** Set Sales Transaction.
	  * This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx);

	/** Get Sales Transaction.
	  * This is a Sales Transaction
	  */
	public boolean isSOTrx();

    /** Column name IsSplitWhenDifference */
    public static final String COLUMNNAME_IsSplitWhenDifference = "IsSplitWhenDifference";

	/** Set Split when Difference.
	  * Split document when there is a difference
	  */
	public void setIsSplitWhenDifference (boolean IsSplitWhenDifference);

	/** Get Split when Difference.
	  * Split document when there is a difference
	  */
	public boolean isSplitWhenDifference();

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
