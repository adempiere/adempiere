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

/** Generated Interface for I_FixedAsset
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_I_FixedAsset 
{

    /** TableName=I_FixedAsset */
    public static final String Table_Name = "I_FixedAsset";

    /** AD_Table_ID=53277 */
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

    /** Column name A_Accumulated_Depr_F */
    public static final String COLUMNNAME_A_Accumulated_Depr_F = "A_Accumulated_Depr_F";

	/** Set Accumulated Depreciation (fiscal)	  */
	public void setA_Accumulated_Depr_F (BigDecimal A_Accumulated_Depr_F);

	/** Get Accumulated Depreciation (fiscal)	  */
	public BigDecimal getA_Accumulated_Depr_F();

    /** Column name A_Asset_Class_ID */
    public static final String COLUMNNAME_A_Asset_Class_ID = "A_Asset_Class_ID";

	/** Set Asset class	  */
	public void setA_Asset_Class_ID (int A_Asset_Class_ID);

	/** Get Asset class	  */
	public int getA_Asset_Class_ID();

	public org.compiere.model.I_A_Asset_Class getA_Asset_Class() throws RuntimeException;

    /** Column name A_Asset_Class_Value */
    public static final String COLUMNNAME_A_Asset_Class_Value = "A_Asset_Class_Value";

	/** Set Asset Class Key	  */
	public void setA_Asset_Class_Value (String A_Asset_Class_Value);

	/** Get Asset Class Key	  */
	public String getA_Asset_Class_Value();

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

    /** Column name A_Asset_Group_ID */
    public static final String COLUMNNAME_A_Asset_Group_ID = "A_Asset_Group_ID";

	/** Set Asset Group.
	  * Group of Assets
	  */
	public void setA_Asset_Group_ID (int A_Asset_Group_ID);

	/** Get Asset Group.
	  * Group of Assets
	  */
	public int getA_Asset_Group_ID();

	public org.compiere.model.I_A_Asset_Group getA_Asset_Group() throws RuntimeException;

    /** Column name A_Asset_Group_Value */
    public static final String COLUMNNAME_A_Asset_Group_Value = "A_Asset_Group_Value";

	/** Set Asset Group Key	  */
	public void setA_Asset_Group_Value (String A_Asset_Group_Value);

	/** Get Asset Group Key	  */
	public String getA_Asset_Group_Value();

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

    /** Column name A_Asset_Type_ID */
    public static final String COLUMNNAME_A_Asset_Type_ID = "A_Asset_Type_ID";

	/** Set Asset Type	  */
	public void setA_Asset_Type_ID (int A_Asset_Type_ID);

	/** Get Asset Type	  */
	public int getA_Asset_Type_ID();

	public org.compiere.model.I_A_Asset_Type getA_Asset_Type() throws RuntimeException;

    /** Column name A_Asset_Type_Value */
    public static final String COLUMNNAME_A_Asset_Type_Value = "A_Asset_Type_Value";

	/** Set Asset Type Value	  */
	public void setA_Asset_Type_Value (String A_Asset_Type_Value);

	/** Get Asset Type Value	  */
	public String getA_Asset_Type_Value();

    /** Column name A_Current_Period */
    public static final String COLUMNNAME_A_Current_Period = "A_Current_Period";

	/** Set Current Period	  */
	public void setA_Current_Period (int A_Current_Period);

	/** Get Current Period	  */
	public int getA_Current_Period();

    /** Column name A_Remaining_Period */
    public static final String COLUMNNAME_A_Remaining_Period = "A_Remaining_Period";

	/** Set Remaining Period	  */
	public void setA_Remaining_Period (int A_Remaining_Period);

	/** Get Remaining Period	  */
	public int getA_Remaining_Period();

    /** Column name A_Salvage_Value */
    public static final String COLUMNNAME_A_Salvage_Value = "A_Salvage_Value";

	/** Set Asset Salvage Value	  */
	public void setA_Salvage_Value (BigDecimal A_Salvage_Value);

	/** Get Asset Salvage Value	  */
	public BigDecimal getA_Salvage_Value();

    /** Column name AssetDepreciationDate */
    public static final String COLUMNNAME_AssetDepreciationDate = "AssetDepreciationDate";

	/** Set Asset Depreciation Date.
	  * Date of last depreciation
	  */
	public void setAssetDepreciationDate (Timestamp AssetDepreciationDate);

	/** Get Asset Depreciation Date.
	  * Date of last depreciation
	  */
	public Timestamp getAssetDepreciationDate();

    /** Column name AssetPeriodDepreciationAmt */
    public static final String COLUMNNAME_AssetPeriodDepreciationAmt = "AssetPeriodDepreciationAmt";

	/** Set Depreciation Amount / Period.
	  * Applies to Straight Line depreciation
	  */
	public void setAssetPeriodDepreciationAmt (BigDecimal AssetPeriodDepreciationAmt);

	/** Get Depreciation Amount / Period.
	  * Applies to Straight Line depreciation
	  */
	public BigDecimal getAssetPeriodDepreciationAmt();

    /** Column name AssetServiceDate */
    public static final String COLUMNNAME_AssetServiceDate = "AssetServiceDate";

	/** Set In Service Date.
	  * Date when Asset was put into service
	  */
	public void setAssetServiceDate (Timestamp AssetServiceDate);

	/** Get In Service Date.
	  * Date when Asset was put into service
	  */
	public Timestamp getAssetServiceDate();

    /** Column name BPartner_Value */
    public static final String COLUMNNAME_BPartner_Value = "BPartner_Value";

	/** Set Business Partner Key.
	  * The Key of the Business Partner
	  */
	public void setBPartner_Value (String BPartner_Value);

	/** Get Business Partner Key.
	  * The Key of the Business Partner
	  */
	public String getBPartner_Value();

    /** Column name C_BPartnerSR_ID */
    public static final String COLUMNNAME_C_BPartnerSR_ID = "C_BPartnerSR_ID";

	/** Set BPartner (Agent).
	  * Business Partner (Agent or Sales Rep)
	  */
	public void setC_BPartnerSR_ID (int C_BPartnerSR_ID);

	/** Get BPartner (Agent).
	  * Business Partner (Agent or Sales Rep)
	  */
	public int getC_BPartnerSR_ID();

	public org.compiere.model.I_C_BPartner getC_BPartnerSR() throws RuntimeException;

    /** Column name C_City_ID */
    public static final String COLUMNNAME_C_City_ID = "C_City_ID";

	/** Set City.
	  * City
	  */
	public void setC_City_ID (int C_City_ID);

	/** Get City.
	  * City
	  */
	public int getC_City_ID();

	public org.compiere.model.I_C_City getC_City() throws RuntimeException;

    /** Column name C_City_Value */
    public static final String COLUMNNAME_C_City_Value = "C_City_Value";

	/** Set City Value	  */
	public void setC_City_Value (String C_City_Value);

	/** Get City Value	  */
	public String getC_City_Value();

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

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException;

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

    /** Column name I_FixedAsset_ID */
    public static final String COLUMNNAME_I_FixedAsset_ID = "I_FixedAsset_ID";

	/** Set Imported Fixed Asset	  */
	public void setI_FixedAsset_ID (int I_FixedAsset_ID);

	/** Get Imported Fixed Asset	  */
	public int getI_FixedAsset_ID();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name InventoryNo */
    public static final String COLUMNNAME_InventoryNo = "InventoryNo";

	/** Set Inventory No	  */
	public void setInventoryNo (String InventoryNo);

	/** Get Inventory No	  */
	public String getInventoryNo();

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

    /** Column name LocatorValue */
    public static final String COLUMNNAME_LocatorValue = "LocatorValue";

	/** Set Locator Key.
	  * Key of the Warehouse Locator
	  */
	public void setLocatorValue (String LocatorValue);

	/** Get Locator Key.
	  * Key of the Warehouse Locator
	  */
	public String getLocatorValue();

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

	public org.compiere.model.I_M_Locator getM_Locator() throws RuntimeException;

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

    /** Column name OrgValue */
    public static final String COLUMNNAME_OrgValue = "OrgValue";

	/** Set Org Key.
	  * Key of the Organization
	  */
	public void setOrgValue (String OrgValue);

	/** Get Org Key.
	  * Key of the Organization
	  */
	public String getOrgValue();

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

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name UOMSymbol */
    public static final String COLUMNNAME_UOMSymbol = "UOMSymbol";

	/** Set Symbol.
	  * Symbol for a Unit of Measure
	  */
	public void setUOMSymbol (String UOMSymbol);

	/** Get Symbol.
	  * Symbol for a Unit of Measure
	  */
	public String getUOMSymbol();

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

    /** Column name UseLifeMonths */
    public static final String COLUMNNAME_UseLifeMonths = "UseLifeMonths";

	/** Set Usable Life - Months.
	  * Months of the usable life of the asset
	  */
	public void setUseLifeMonths (int UseLifeMonths);

	/** Get Usable Life - Months.
	  * Months of the usable life of the asset
	  */
	public int getUseLifeMonths();

    /** Column name UseLifeMonths_F */
    public static final String COLUMNNAME_UseLifeMonths_F = "UseLifeMonths_F";

	/** Set Use Life - Months (fiscal)	  */
	public void setUseLifeMonths_F (int UseLifeMonths_F);

	/** Get Use Life - Months (fiscal)	  */
	public int getUseLifeMonths_F();
}
