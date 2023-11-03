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

/** Generated Interface for I_Requisition
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4
 */
public interface I_I_Requisition 
{

    /** TableName=I_Requisition */
    public static final String Table_Name = "I_Requisition";

    /** AD_Table_ID=54868 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

    /** Load Meta Data */

    /** Column name ActivityValue */
    public static final String COLUMNNAME_ActivityValue = "ActivityValue";

	/** Set Activity Value	  */
	public void setActivityValue (String ActivityValue);

	/** Get Activity Value	  */
	public String getActivityValue();

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

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

	public org.adempiere.core.domains.models.I_AD_Org getAD_OrgTrx() throws RuntimeException;

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.adempiere.core.domains.models.I_AD_User getAD_User() throws RuntimeException;

    /** Column name AttributeSetInstanceValue */
    public static final String COLUMNNAME_AttributeSetInstanceValue = "AttributeSetInstanceValue";

	/** Set Attribute Set Instance Value.
	  * Value of the Attribute Set Instance
	  */
	public void setAttributeSetInstanceValue (String AttributeSetInstanceValue);

	/** Get Attribute Set Instance Value.
	  * Value of the Attribute Set Instance
	  */
	public String getAttributeSetInstanceValue();

    /** Column name BPartnerAddress_Value */
    public static final String COLUMNNAME_BPartnerAddress_Value = "BPartnerAddress_Value";

	/** Set Business Partner Address Key.
	  * Business Partner Address Key
	  */
	public void setBPartnerAddress_Value (String BPartnerAddress_Value);

	/** Get Business Partner Address Key.
	  * Business Partner Address Key
	  */
	public String getBPartnerAddress_Value();

    /** Column name BPartnerValue */
    public static final String COLUMNNAME_BPartnerValue = "BPartnerValue";

	/** Set Business Partner Key.
	  * Key of the Business Partner
	  */
	public void setBPartnerValue (String BPartnerValue);

	/** Get Business Partner Key.
	  * Key of the Business Partner
	  */
	public String getBPartnerValue();

    /** Column name C_Activity_ID */
    public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";

	/** Set Activity.
	  * Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID);

	/** Get Activity.
	  * Business Activity
	  */
	public int getC_Activity_ID();

	public org.adempiere.core.domains.models.I_C_Activity getC_Activity() throws RuntimeException;

    /** Column name CampaignValue */
    public static final String COLUMNNAME_CampaignValue = "CampaignValue";

	/** Set Campaign Value	  */
	public void setCampaignValue (String CampaignValue);

	/** Get Campaign Value	  */
	public String getCampaignValue();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.adempiere.core.domains.models.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID();

	public org.adempiere.core.domains.models.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

    /** Column name C_Campaign_ID */
    public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";

	/** Set Campaign.
	  * Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID);

	/** Get Campaign.
	  * Marketing Campaign
	  */
	public int getC_Campaign_ID();

	public org.adempiere.core.domains.models.I_C_Campaign getC_Campaign() throws RuntimeException;

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

	public org.adempiere.core.domains.models.I_C_Charge getC_Charge() throws RuntimeException;

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

	public org.adempiere.core.domains.models.I_C_DocType getC_DocType() throws RuntimeException;

    /** Column name ChargeName */
    public static final String COLUMNNAME_ChargeName = "ChargeName";

	/** Set Charge Name.
	  * Name of the Charge
	  */
	public void setChargeName (String ChargeName);

	/** Get Charge Name.
	  * Name of the Charge
	  */
	public String getChargeName();

    /** Column name Classification */
    public static final String COLUMNNAME_Classification = "Classification";

	/** Set Classification.
	  * Classification for grouping
	  */
	public void setClassification (String Classification);

	/** Get Classification.
	  * Classification for grouping
	  */
	public String getClassification();

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

	public org.adempiere.core.domains.models.I_C_Project getC_Project() throws RuntimeException;

    /** Column name C_ProjectPhase_ID */
    public static final String COLUMNNAME_C_ProjectPhase_ID = "C_ProjectPhase_ID";

	/** Set Project Phase.
	  * Phase of a Project
	  */
	public void setC_ProjectPhase_ID (int C_ProjectPhase_ID);

	/** Get Project Phase.
	  * Phase of a Project
	  */
	public int getC_ProjectPhase_ID();

	public org.adempiere.core.domains.models.I_C_ProjectPhase getC_ProjectPhase() throws RuntimeException;

    /** Column name C_ProjectTask_ID */
    public static final String COLUMNNAME_C_ProjectTask_ID = "C_ProjectTask_ID";

	/** Set Project Task.
	  * Actual Project Task in a Phase
	  */
	public void setC_ProjectTask_ID (int C_ProjectTask_ID);

	/** Get Project Task.
	  * Actual Project Task in a Phase
	  */
	public int getC_ProjectTask_ID();

	public org.adempiere.core.domains.models.I_C_ProjectTask getC_ProjectTask() throws RuntimeException;

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

    /** Column name C_TaxCategory_ID */
    public static final String COLUMNNAME_C_TaxCategory_ID = "C_TaxCategory_ID";

	/** Set Tax Category.
	  * Tax Category
	  */
	public void setC_TaxCategory_ID (int C_TaxCategory_ID);

	/** Get Tax Category.
	  * Tax Category
	  */
	public int getC_TaxCategory_ID();

	public org.adempiere.core.domains.models.I_C_TaxCategory getC_TaxCategory() throws RuntimeException;

    /** Column name C_Tax_ID */
    public static final String COLUMNNAME_C_Tax_ID = "C_Tax_ID";

	/** Set Tax.
	  * Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID);

	/** Get Tax.
	  * Tax identifier
	  */
	public int getC_Tax_ID();

	public org.adempiere.core.domains.models.I_C_Tax getC_Tax() throws RuntimeException;

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public org.adempiere.core.domains.models.I_C_UOM getC_UOM() throws RuntimeException;

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

    /** Column name DateRequired */
    public static final String COLUMNNAME_DateRequired = "DateRequired";

	/** Set Date Required.
	  * Date when required
	  */
	public void setDateRequired (Timestamp DateRequired);

	/** Get Date Required.
	  * Date when required
	  */
	public Timestamp getDateRequired();

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

    /** Column name DescriptionURL */
    public static final String COLUMNNAME_DescriptionURL = "DescriptionURL";

	/** Set Description URL.
	  * URL for the description
	  */
	public void setDescriptionURL (String DescriptionURL);

	/** Get Description URL.
	  * URL for the description
	  */
	public String getDescriptionURL();

    /** Column name DocTypeName */
    public static final String COLUMNNAME_DocTypeName = "DocTypeName";

	/** Set Document Type Name.
	  * Name of the Document Type
	  */
	public void setDocTypeName (String DocTypeName);

	/** Get Document Type Name.
	  * Name of the Document Type
	  */
	public String getDocTypeName();

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

    /** Column name GuaranteeDate */
    public static final String COLUMNNAME_GuaranteeDate = "GuaranteeDate";

	/** Set Guarantee Date.
	  * Date when guarantee expires
	  */
	public void setGuaranteeDate (Timestamp GuaranteeDate);

	/** Get Guarantee Date.
	  * Date when guarantee expires
	  */
	public Timestamp getGuaranteeDate();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (String I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public String getI_IsImported();

    /** Column name ImageURL */
    public static final String COLUMNNAME_ImageURL = "ImageURL";

	/** Set Image URL.
	  * URL of  image
	  */
	public void setImageURL (String ImageURL);

	/** Get Image URL.
	  * URL of  image
	  */
	public String getImageURL();

    /** Column name I_Requisition_ID */
    public static final String COLUMNNAME_I_Requisition_ID = "I_Requisition_ID";

	/** Set Import Requisition	  */
	public void setI_Requisition_ID (int I_Requisition_ID);

	/** Get Import Requisition	  */
	public int getI_Requisition_ID();

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

    /** Column name LineDescription */
    public static final String COLUMNNAME_LineDescription = "LineDescription";

	/** Set Line Description.
	  * Description of the Line
	  */
	public void setLineDescription (String LineDescription);

	/** Get Line Description.
	  * Description of the Line
	  */
	public String getLineDescription();

    /** Column name Lot */
    public static final String COLUMNNAME_Lot = "Lot";

	/** Set Lot No.
	  * Lot number (alphanumeric)
	  */
	public void setLot (String Lot);

	/** Get Lot No.
	  * Lot number (alphanumeric)
	  */
	public String getLot();

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

    /** Column name M_PriceList_ID */
    public static final String COLUMNNAME_M_PriceList_ID = "M_PriceList_ID";

	/** Set Price List.
	  * Unique identifier of a Price List
	  */
	public void setM_PriceList_ID (int M_PriceList_ID);

	/** Get Price List.
	  * Unique identifier of a Price List
	  */
	public int getM_PriceList_ID();

	public org.adempiere.core.domains.models.I_M_PriceList getM_PriceList() throws RuntimeException;

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

	public org.adempiere.core.domains.models.I_M_Product_Category getM_Product_Category() throws RuntimeException;

    /** Column name M_Product_Class_ID */
    public static final String COLUMNNAME_M_Product_Class_ID = "M_Product_Class_ID";

	/** Set Product Class.
	  * Class of a Product
	  */
	public void setM_Product_Class_ID (int M_Product_Class_ID);

	/** Get Product Class.
	  * Class of a Product
	  */
	public int getM_Product_Class_ID();

	public org.adempiere.core.domains.models.I_M_Product_Class getM_Product_Class() throws RuntimeException;

    /** Column name M_Product_Classification_ID */
    public static final String COLUMNNAME_M_Product_Classification_ID = "M_Product_Classification_ID";

	/** Set Product Classification.
	  * Classification of a Product
	  */
	public void setM_Product_Classification_ID (int M_Product_Classification_ID);

	/** Get Product Classification.
	  * Classification of a Product
	  */
	public int getM_Product_Classification_ID();

	public org.adempiere.core.domains.models.I_M_Product_Classification getM_Product_Classification() throws RuntimeException;

    /** Column name M_Product_Group_ID */
    public static final String COLUMNNAME_M_Product_Group_ID = "M_Product_Group_ID";

	/** Set Product Group.
	  * Group of a Product
	  */
	public void setM_Product_Group_ID (int M_Product_Group_ID);

	/** Get Product Group.
	  * Group of a Product
	  */
	public int getM_Product_Group_ID();

	public org.adempiere.core.domains.models.I_M_Product_Group getM_Product_Group() throws RuntimeException;

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

	public org.adempiere.core.domains.models.I_M_Product getM_Product() throws RuntimeException;

    /** Column name M_Requisition_ID */
    public static final String COLUMNNAME_M_Requisition_ID = "M_Requisition_ID";

	/** Set Requisition.
	  * Material Requisition
	  */
	public void setM_Requisition_ID (int M_Requisition_ID);

	/** Get Requisition.
	  * Material Requisition
	  */
	public int getM_Requisition_ID();

	public org.adempiere.core.domains.models.I_M_Requisition getM_Requisition() throws RuntimeException;

    /** Column name M_RequisitionLine_ID */
    public static final String COLUMNNAME_M_RequisitionLine_ID = "M_RequisitionLine_ID";

	/** Set Requisition Line.
	  * Material Requisition Line
	  */
	public void setM_RequisitionLine_ID (int M_RequisitionLine_ID);

	/** Get Requisition Line.
	  * Material Requisition Line
	  */
	public int getM_RequisitionLine_ID();

	public org.adempiere.core.domains.models.I_M_RequisitionLine getM_RequisitionLine() throws RuntimeException;

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public org.adempiere.core.domains.models.I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name OrgTrxValue */
    public static final String COLUMNNAME_OrgTrxValue = "OrgTrxValue";

	/** Set Trx Org Key.
	  * Key of the Transaction Organization
	  */
	public void setOrgTrxValue (String OrgTrxValue);

	/** Get Trx Org Key.
	  * Key of the Transaction Organization
	  */
	public String getOrgTrxValue();

    /** Column name PriceActual */
    public static final String COLUMNNAME_PriceActual = "PriceActual";

	/** Set Unit Price.
	  * Actual Price 
	  */
	public void setPriceActual (BigDecimal PriceActual);

	/** Get Unit Price.
	  * Actual Price 
	  */
	public BigDecimal getPriceActual();

    /** Column name PriceListName */
    public static final String COLUMNNAME_PriceListName = "PriceListName";

	/** Set Price List Name	  */
	public void setPriceListName (String PriceListName);

	/** Get Price List Name	  */
	public String getPriceListName();

    /** Column name PriorityRule */
    public static final String COLUMNNAME_PriorityRule = "PriorityRule";

	/** Set Priority.
	  * Priority of a document
	  */
	public void setPriorityRule (String PriorityRule);

	/** Get Priority.
	  * Priority of a document
	  */
	public String getPriorityRule();

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

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name ProductCategory_Value */
    public static final String COLUMNNAME_ProductCategory_Value = "ProductCategory_Value";

	/** Set Product Category Key	  */
	public void setProductCategory_Value (String ProductCategory_Value);

	/** Get Product Category Key	  */
	public String getProductCategory_Value();

    /** Column name ProductClassification_Name */
    public static final String COLUMNNAME_ProductClassification_Name = "ProductClassification_Name";

	/** Set Product Classification Name.
	  * The key value for the product classification
	  */
	public void setProductClassification_Name (String ProductClassification_Name);

	/** Get Product Classification Name.
	  * The key value for the product classification
	  */
	public String getProductClassification_Name();

    /** Column name ProductClassification_Value */
    public static final String COLUMNNAME_ProductClassification_Value = "ProductClassification_Value";

	/** Set Product Classification Key.
	  * The key value for the product classification
	  */
	public void setProductClassification_Value (String ProductClassification_Value);

	/** Get Product Classification Key.
	  * The key value for the product classification
	  */
	public String getProductClassification_Value();

    /** Column name ProductClass_Name */
    public static final String COLUMNNAME_ProductClass_Name = "ProductClass_Name";

	/** Set Product Class Name.
	  * The key value for the product class
	  */
	public void setProductClass_Name (String ProductClass_Name);

	/** Get Product Class Name.
	  * The key value for the product class
	  */
	public String getProductClass_Name();

    /** Column name ProductClass_Value */
    public static final String COLUMNNAME_ProductClass_Value = "ProductClass_Value";

	/** Set Product Class Key.
	  * The key value for the product class
	  */
	public void setProductClass_Value (String ProductClass_Value);

	/** Get Product Class Key.
	  * The key value for the product class
	  */
	public String getProductClass_Value();

    /** Column name ProductDescription */
    public static final String COLUMNNAME_ProductDescription = "ProductDescription";

	/** Set Product Description.
	  * Product Description
	  */
	public void setProductDescription (String ProductDescription);

	/** Get Product Description.
	  * Product Description
	  */
	public String getProductDescription();

    /** Column name ProductGroup_Name */
    public static final String COLUMNNAME_ProductGroup_Name = "ProductGroup_Name";

	/** Set Product Group Name.
	  * The key value for the product group
	  */
	public void setProductGroup_Name (String ProductGroup_Name);

	/** Get Product Group Name.
	  * The key value for the product group
	  */
	public String getProductGroup_Name();

    /** Column name ProductGroup_Value */
    public static final String COLUMNNAME_ProductGroup_Value = "ProductGroup_Value";

	/** Set Product Group Key.
	  * The key value for the product group
	  */
	public void setProductGroup_Value (String ProductGroup_Value);

	/** Get Product Group Key.
	  * The key value for the product group
	  */
	public String getProductGroup_Value();

    /** Column name ProductName */
    public static final String COLUMNNAME_ProductName = "ProductName";

	/** Set Product Name.
	  * Name of the Product
	  */
	public void setProductName (String ProductName);

	/** Get Product Name.
	  * Name of the Product
	  */
	public String getProductName();

    /** Column name ProductType */
    public static final String COLUMNNAME_ProductType = "ProductType";

	/** Set Product Type.
	  * Type of product
	  */
	public void setProductType (String ProductType);

	/** Get Product Type.
	  * Type of product
	  */
	public String getProductType();

    /** Column name ProductValue */
    public static final String COLUMNNAME_ProductValue = "ProductValue";

	/** Set Product Key.
	  * Key of the Product
	  */
	public void setProductValue (String ProductValue);

	/** Get Product Key.
	  * Key of the Product
	  */
	public String getProductValue();

    /** Column name ProjectPhaseName */
    public static final String COLUMNNAME_ProjectPhaseName = "ProjectPhaseName";

	/** Set Project Phase.
	  * Name of the Project Phase
	  */
	public void setProjectPhaseName (String ProjectPhaseName);

	/** Get Project Phase.
	  * Name of the Project Phase
	  */
	public String getProjectPhaseName();

    /** Column name ProjectValue */
    public static final String COLUMNNAME_ProjectValue = "ProjectValue";

	/** Set Project Key.
	  * Key of the Project
	  */
	public void setProjectValue (String ProjectValue);

	/** Get Project Key.
	  * Key of the Project
	  */
	public String getProjectValue();

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();

    /** Column name SerNo */
    public static final String COLUMNNAME_SerNo = "SerNo";

	/** Set Serial No.
	  * Product Serial Number 
	  */
	public void setSerNo (String SerNo);

	/** Get Serial No.
	  * Product Serial Number 
	  */
	public String getSerNo();

    /** Column name ShelfDepth */
    public static final String COLUMNNAME_ShelfDepth = "ShelfDepth";

	/** Set Shelf Depth.
	  * Shelf depth required
	  */
	public void setShelfDepth (int ShelfDepth);

	/** Get Shelf Depth.
	  * Shelf depth required
	  */
	public int getShelfDepth();

    /** Column name ShelfHeight */
    public static final String COLUMNNAME_ShelfHeight = "ShelfHeight";

	/** Set Shelf Height.
	  * Shelf height required
	  */
	public void setShelfHeight (BigDecimal ShelfHeight);

	/** Get Shelf Height.
	  * Shelf height required
	  */
	public BigDecimal getShelfHeight();

    /** Column name ShelfWidth */
    public static final String COLUMNNAME_ShelfWidth = "ShelfWidth";

	/** Set Shelf Width.
	  * Shelf width required
	  */
	public void setShelfWidth (int ShelfWidth);

	/** Get Shelf Width.
	  * Shelf width required
	  */
	public int getShelfWidth();

    /** Column name SKU */
    public static final String COLUMNNAME_SKU = "SKU";

	/** Set SKU.
	  * Stock Keeping Unit
	  */
	public void setSKU (String SKU);

	/** Get SKU.
	  * Stock Keeping Unit
	  */
	public String getSKU();

    /** Column name TaxCategoryName */
    public static final String COLUMNNAME_TaxCategoryName = "TaxCategoryName";

	/** Set Tax Category Name.
	  * Name of tax category
	  */
	public void setTaxCategoryName (String TaxCategoryName);

	/** Get Tax Category Name.
	  * Name of tax category
	  */
	public String getTaxCategoryName();

    /** Column name Tax_Value */
    public static final String COLUMNNAME_Tax_Value = "Tax_Value";

	/** Set Tax Rate Key.
	  * Tax Rate Key for Document
	  */
	public void setTax_Value (String Tax_Value);

	/** Get Tax Rate Key.
	  * Tax Rate Key for Document
	  */
	public String getTax_Value();

    /** Column name UnitsPerPallet */
    public static final String COLUMNNAME_UnitsPerPallet = "UnitsPerPallet";

	/** Set Units Per Pallet.
	  * Units Per Pallet
	  */
	public void setUnitsPerPallet (BigDecimal UnitsPerPallet);

	/** Get Units Per Pallet.
	  * Units Per Pallet
	  */
	public BigDecimal getUnitsPerPallet();

    /** Column name UPC */
    public static final String COLUMNNAME_UPC = "UPC";

	/** Set UPC/EAN.
	  * Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public void setUPC (String UPC);

	/** Get UPC/EAN.
	  * Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public String getUPC();

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

    /** Column name User1_ID */
    public static final String COLUMNNAME_User1_ID = "User1_ID";

	/** Set User List 1.
	  * User defined list element #1
	  */
	public void setUser1_ID (int User1_ID);

	/** Get User List 1.
	  * User defined list element #1
	  */
	public int getUser1_ID();

	public org.adempiere.core.domains.models.I_C_ElementValue getUser1() throws RuntimeException;

    /** Column name User2_ID */
    public static final String COLUMNNAME_User2_ID = "User2_ID";

	/** Set User List 2.
	  * User defined list element #2
	  */
	public void setUser2_ID (int User2_ID);

	/** Get User List 2.
	  * User defined list element #2
	  */
	public int getUser2_ID();

	public org.adempiere.core.domains.models.I_C_ElementValue getUser2() throws RuntimeException;

    /** Column name User3_ID */
    public static final String COLUMNNAME_User3_ID = "User3_ID";

	/** Set User List 3.
	  * User defined list element #3
	  */
	public void setUser3_ID (int User3_ID);

	/** Get User List 3.
	  * User defined list element #3
	  */
	public int getUser3_ID();

	public org.adempiere.core.domains.models.I_C_ElementValue getUser3() throws RuntimeException;

    /** Column name User4_ID */
    public static final String COLUMNNAME_User4_ID = "User4_ID";

	/** Set User List 4.
	  * User defined list element #4
	  */
	public void setUser4_ID (int User4_ID);

	/** Get User List 4.
	  * User defined list element #4
	  */
	public int getUser4_ID();

	public org.adempiere.core.domains.models.I_C_ElementValue getUser4() throws RuntimeException;

    /** Column name UserAgent */
    public static final String COLUMNNAME_UserAgent = "UserAgent";

	/** Set User Agent.
	  * Browser Used
	  */
	public void setUserAgent (String UserAgent);

	/** Get User Agent.
	  * Browser Used
	  */
	public String getUserAgent();

    /** Column name UserValue1 */
    public static final String COLUMNNAME_UserValue1 = "UserValue1";

	/** Set User List Value 1.
	  * User value defined list element #1
	  */
	public void setUserValue1 (String UserValue1);

	/** Get User List Value 1.
	  * User value defined list element #1
	  */
	public String getUserValue1();

    /** Column name UserValue2 */
    public static final String COLUMNNAME_UserValue2 = "UserValue2";

	/** Set User List Value 2.
	  * User value defined list element #2
	  */
	public void setUserValue2 (String UserValue2);

	/** Get User List Value 2.
	  * User value defined list element #2
	  */
	public String getUserValue2();

    /** Column name UserValue3 */
    public static final String COLUMNNAME_UserValue3 = "UserValue3";

	/** Set User List Value 3.
	  * User value defined list element #3
	  */
	public void setUserValue3 (String UserValue3);

	/** Get User List Value 3.
	  * User value defined list element #3
	  */
	public String getUserValue3();

    /** Column name UserValue4 */
    public static final String COLUMNNAME_UserValue4 = "UserValue4";

	/** Set User List Value 4.
	  * User value defined list element #3
	  */
	public void setUserValue4 (String UserValue4);

	/** Get User List Value 4.
	  * User value defined list element #3
	  */
	public String getUserValue4();

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

    /** Column name Volume */
    public static final String COLUMNNAME_Volume = "Volume";

	/** Set Volume.
	  * Volume of a product
	  */
	public void setVolume (BigDecimal Volume);

	/** Get Volume.
	  * Volume of a product
	  */
	public BigDecimal getVolume();

    /** Column name WarehouseValue */
    public static final String COLUMNNAME_WarehouseValue = "WarehouseValue";

	/** Set Warehouse Key.
	  * Key of the Warehouse
	  */
	public void setWarehouseValue (String WarehouseValue);

	/** Get Warehouse Key.
	  * Key of the Warehouse
	  */
	public String getWarehouseValue();

    /** Column name Weight */
    public static final String COLUMNNAME_Weight = "Weight";

	/** Set Weight.
	  * Weight of a product
	  */
	public void setWeight (BigDecimal Weight);

	/** Get Weight.
	  * Weight of a product
	  */
	public BigDecimal getWeight();

    /** Column name X12DE355 */
    public static final String COLUMNNAME_X12DE355 = "X12DE355";

	/** Set UOM Code.
	  * UOM EDI X12 Code
	  */
	public void setX12DE355 (String X12DE355);

	/** Get UOM Code.
	  * UOM EDI X12 Code
	  */
	public String getX12DE355();
}
