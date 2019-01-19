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
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for I_Product_ASI
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_I_Product_ASI 
{

    /** TableName=I_Product_ASI */
    public static final String Table_Name = "I_Product_ASI";

    /** AD_Table_ID=53775 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

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

    /** Column name AttributeName */
    public static final String COLUMNNAME_AttributeName = "AttributeName";

	/** Set Attribute Name.
	  * Name of the Attribute
	  */
	public void setAttributeName (String AttributeName);

	/** Get Attribute Name.
	  * Name of the Attribute
	  */
	public String getAttributeName();

    /** Column name AttributeSearchName */
    public static final String COLUMNNAME_AttributeSearchName = "AttributeSearchName";

	/** Set Attribute Search Name.
	  * Name of the Attribute Search
	  */
	public void setAttributeSearchName (String AttributeSearchName);

	/** Get Attribute Search Name.
	  * Name of the Attribute Search
	  */
	public String getAttributeSearchName();

    /** Column name AttributeSetName */
    public static final String COLUMNNAME_AttributeSetName = "AttributeSetName";

	/** Set Attribute Set Name.
	  * Name of the Attribute Set
	  */
	public void setAttributeSetName (String AttributeSetName);

	/** Get Attribute Set Name.
	  * Name of the Attribute Set
	  */
	public String getAttributeSetName();

    /** Column name AttributeValue */
    public static final String COLUMNNAME_AttributeValue = "AttributeValue";

	/** Set Attribute Value.
	  * Value of the Attribute
	  */
	public void setAttributeValue (String AttributeValue);

	/** Get Attribute Value.
	  * Value of the Attribute
	  */
	public String getAttributeValue();

    /** Column name AttributeValueType */
    public static final String COLUMNNAME_AttributeValueType = "AttributeValueType";

	/** Set Attribute Value Type.
	  * Type of Attribute Value
	  */
	public void setAttributeValueType (String AttributeValueType);

	/** Get Attribute Value Type.
	  * Type of Attribute Value
	  */
	public String getAttributeValueType();

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

    /** Column name ElementName */
    public static final String COLUMNNAME_ElementName = "ElementName";

	/** Set Element Name.
	  * Name of the Element
	  */
	public void setElementName (String ElementName);

	/** Get Element Name.
	  * Name of the Element
	  */
	public String getElementName();

    /** Column name GuaranteeDays */
    public static final String COLUMNNAME_GuaranteeDays = "GuaranteeDays";

	/** Set Guarantee Days.
	  * Number of days the product is guaranteed or available
	  */
	public void setGuaranteeDays (int GuaranteeDays);

	/** Get Guarantee Days.
	  * Number of days the product is guaranteed or available
	  */
	public int getGuaranteeDays();

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
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

    /** Column name I_Product_ASI_ID */
    public static final String COLUMNNAME_I_Product_ASI_ID = "I_Product_ASI_ID";

	/** Set Import Product Attribute Set Instance ID	  */
	public void setI_Product_ASI_ID (int I_Product_ASI_ID);

	/** Get Import Product Attribute Set Instance ID	  */
	public int getI_Product_ASI_ID();

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

    /** Column name IsGuaranteeDate */
    public static final String COLUMNNAME_IsGuaranteeDate = "IsGuaranteeDate";

	/** Set Guarantee Date.
	  * Product has Guarantee or Expiry Date
	  */
	public void setIsGuaranteeDate (boolean IsGuaranteeDate);

	/** Get Guarantee Date.
	  * Product has Guarantee or Expiry Date
	  */
	public boolean isGuaranteeDate();

    /** Column name IsGuaranteeDateMandatory */
    public static final String COLUMNNAME_IsGuaranteeDateMandatory = "IsGuaranteeDateMandatory";

	/** Set Mandatory Guarantee Date.
	  * The entry of a Guarantee Date is mandatory when creating a Product Instance
	  */
	public void setIsGuaranteeDateMandatory (boolean IsGuaranteeDateMandatory);

	/** Get Mandatory Guarantee Date.
	  * The entry of a Guarantee Date is mandatory when creating a Product Instance
	  */
	public boolean isGuaranteeDateMandatory();

    /** Column name IsInstanceAttribute */
    public static final String COLUMNNAME_IsInstanceAttribute = "IsInstanceAttribute";

	/** Set Instance Attribute.
	  * The product attribute is specific to the instance (like Serial No, Lot or Guarantee Date)
	  */
	public void setIsInstanceAttribute (boolean IsInstanceAttribute);

	/** Get Instance Attribute.
	  * The product attribute is specific to the instance (like Serial No, Lot or Guarantee Date)
	  */
	public boolean isInstanceAttribute();

    /** Column name IsLot */
    public static final String COLUMNNAME_IsLot = "IsLot";

	/** Set Lot.
	  * The product instances have a Lot Number
	  */
	public void setIsLot (boolean IsLot);

	/** Get Lot.
	  * The product instances have a Lot Number
	  */
	public boolean isLot();

    /** Column name IsLotMandatory */
    public static final String COLUMNNAME_IsLotMandatory = "IsLotMandatory";

	/** Set Mandatory Lot.
	  * The entry of Lot info is mandatory when creating a Product Instance
	  */
	public void setIsLotMandatory (boolean IsLotMandatory);

	/** Get Mandatory Lot.
	  * The entry of Lot info is mandatory when creating a Product Instance
	  */
	public boolean isLotMandatory();

    /** Column name IsMandatory */
    public static final String COLUMNNAME_IsMandatory = "IsMandatory";

	/** Set Mandatory.
	  * Data entry is required in this column
	  */
	public void setIsMandatory (boolean IsMandatory);

	/** Get Mandatory.
	  * Data entry is required in this column
	  */
	public boolean isMandatory();

    /** Column name IsSerNo */
    public static final String COLUMNNAME_IsSerNo = "IsSerNo";

	/** Set Serial No.
	  * The product instances have Serial Numbers
	  */
	public void setIsSerNo (boolean IsSerNo);

	/** Get Serial No.
	  * The product instances have Serial Numbers
	  */
	public boolean isSerNo();

    /** Column name IsSerNoMandatory */
    public static final String COLUMNNAME_IsSerNoMandatory = "IsSerNoMandatory";

	/** Set Mandatory Serial No.
	  * The entry of a Serial No is mandatory when creating a Product Instance
	  */
	public void setIsSerNoMandatory (boolean IsSerNoMandatory);

	/** Get Mandatory Serial No.
	  * The entry of a Serial No is mandatory when creating a Product Instance
	  */
	public boolean isSerNoMandatory();

    /** Column name M_AttributeSearch_ID */
    public static final String COLUMNNAME_M_AttributeSearch_ID = "M_AttributeSearch_ID";

	/** Set Attribute Search.
	  * Common Search Attribute 
	  */
	public void setM_AttributeSearch_ID (int M_AttributeSearch_ID);

	/** Get Attribute Search.
	  * Common Search Attribute 
	  */
	public int getM_AttributeSearch_ID();

	public org.compiere.model.I_M_AttributeSearch getM_AttributeSearch() throws RuntimeException;

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

    /** Column name M_AttributeSet_ID */
    public static final String COLUMNNAME_M_AttributeSet_ID = "M_AttributeSet_ID";

	/** Set Attribute Set.
	  * Product Attribute Set
	  */
	public void setM_AttributeSet_ID (int M_AttributeSet_ID);

	/** Get Attribute Set.
	  * Product Attribute Set
	  */
	public int getM_AttributeSet_ID();

	public org.compiere.model.I_M_AttributeSet getM_AttributeSet() throws RuntimeException;

    /** Column name M_AttributeValue_ID */
    public static final String COLUMNNAME_M_AttributeValue_ID = "M_AttributeValue_ID";

	/** Set Attribute Value.
	  * Product Attribute Value
	  */
	public void setM_AttributeValue_ID (int M_AttributeValue_ID);

	/** Get Attribute Value.
	  * Product Attribute Value
	  */
	public int getM_AttributeValue_ID();

	public org.compiere.model.I_M_AttributeValue getM_AttributeValue() throws RuntimeException;

    /** Column name M_Attribute_ID */
    public static final String COLUMNNAME_M_Attribute_ID = "M_Attribute_ID";

	/** Set Attribute.
	  * Product Attribute
	  */
	public void setM_Attribute_ID (int M_Attribute_ID);

	/** Get Attribute.
	  * Product Attribute
	  */
	public int getM_Attribute_ID();

	public org.compiere.model.I_M_Attribute getM_Attribute() throws RuntimeException;

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

    /** Column name MandatoryType */
    public static final String COLUMNNAME_MandatoryType = "MandatoryType";

	/** Set Mandatory Type.
	  * The specification of a Product Attribute Instance is mandatory
	  */
	public void setMandatoryType (String MandatoryType);

	/** Get Mandatory Type.
	  * The specification of a Product Attribute Instance is mandatory
	  */
	public String getMandatoryType();

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
