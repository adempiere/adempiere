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
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for I_Product_ASI
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_I_Product_ASI extends PO implements I_I_Product_ASI, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_I_Product_ASI (Properties ctx, int I_Product_ASI_ID, String trxName)
    {
      super (ctx, I_Product_ASI_ID, trxName);
      /** if (I_Product_ASI_ID == 0)
        {
			setI_Product_ASI_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_Product_ASI (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_I_Product_ASI[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Attribute Name.
		@param AttributeName 
		Name of the Attribute
	  */
	public void setAttributeName (String AttributeName)
	{
		set_Value (COLUMNNAME_AttributeName, AttributeName);
	}

	/** Get Attribute Name.
		@return Name of the Attribute
	  */
	public String getAttributeName () 
	{
		return (String)get_Value(COLUMNNAME_AttributeName);
	}

	/** Set Attribute Search Name.
		@param AttributeSearchName 
		Name of the Attribute Search
	  */
	public void setAttributeSearchName (String AttributeSearchName)
	{
		set_Value (COLUMNNAME_AttributeSearchName, AttributeSearchName);
	}

	/** Get Attribute Search Name.
		@return Name of the Attribute Search
	  */
	public String getAttributeSearchName () 
	{
		return (String)get_Value(COLUMNNAME_AttributeSearchName);
	}

	/** Set Attribute Set Name.
		@param AttributeSetName 
		Name of the Attribute Set
	  */
	public void setAttributeSetName (String AttributeSetName)
	{
		set_Value (COLUMNNAME_AttributeSetName, AttributeSetName);
	}

	/** Get Attribute Set Name.
		@return Name of the Attribute Set
	  */
	public String getAttributeSetName () 
	{
		return (String)get_Value(COLUMNNAME_AttributeSetName);
	}

	/** Set Attribute Value.
		@param AttributeValue 
		Value of the Attribute
	  */
	public void setAttributeValue (String AttributeValue)
	{
		set_Value (COLUMNNAME_AttributeValue, AttributeValue);
	}

	/** Get Attribute Value.
		@return Value of the Attribute
	  */
	public String getAttributeValue () 
	{
		return (String)get_Value(COLUMNNAME_AttributeValue);
	}

	/** AttributeValueType AD_Reference_ID=326 */
	public static final int ATTRIBUTEVALUETYPE_AD_Reference_ID=326;
	/** String (max 40) = S */
	public static final String ATTRIBUTEVALUETYPE_StringMax40 = "S";
	/** Number = N */
	public static final String ATTRIBUTEVALUETYPE_Number = "N";
	/** List = L */
	public static final String ATTRIBUTEVALUETYPE_List = "L";
	/** Set Attribute Value Type.
		@param AttributeValueType 
		Type of Attribute Value
	  */
	public void setAttributeValueType (String AttributeValueType)
	{

		set_Value (COLUMNNAME_AttributeValueType, AttributeValueType);
	}

	/** Get Attribute Value Type.
		@return Type of Attribute Value
	  */
	public String getAttributeValueType () 
	{
		return (String)get_Value(COLUMNNAME_AttributeValueType);
	}

	/** Set Element Name.
		@param ElementName 
		Name of the Element
	  */
	public void setElementName (String ElementName)
	{
		set_Value (COLUMNNAME_ElementName, ElementName);
	}

	/** Get Element Name.
		@return Name of the Element
	  */
	public String getElementName () 
	{
		return (String)get_Value(COLUMNNAME_ElementName);
	}

	/** Set Guarantee Days.
		@param GuaranteeDays 
		Number of days the product is guaranteed or available
	  */
	public void setGuaranteeDays (int GuaranteeDays)
	{
		set_Value (COLUMNNAME_GuaranteeDays, Integer.valueOf(GuaranteeDays));
	}

	/** Get Guarantee Days.
		@return Number of days the product is guaranteed or available
	  */
	public int getGuaranteeDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GuaranteeDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Import Product Attribute Set Instance ID.
		@param I_Product_ASI_ID Import Product Attribute Set Instance ID	  */
	public void setI_Product_ASI_ID (int I_Product_ASI_ID)
	{
		if (I_Product_ASI_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_Product_ASI_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_Product_ASI_ID, Integer.valueOf(I_Product_ASI_ID));
	}

	/** Get Import Product Attribute Set Instance ID.
		@return Import Product Attribute Set Instance ID	  */
	public int getI_Product_ASI_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_Product_ASI_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Guarantee Date.
		@param IsGuaranteeDate 
		Product has Guarantee or Expiry Date
	  */
	public void setIsGuaranteeDate (boolean IsGuaranteeDate)
	{
		set_Value (COLUMNNAME_IsGuaranteeDate, Boolean.valueOf(IsGuaranteeDate));
	}

	/** Get Guarantee Date.
		@return Product has Guarantee or Expiry Date
	  */
	public boolean isGuaranteeDate () 
	{
		Object oo = get_Value(COLUMNNAME_IsGuaranteeDate);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Mandatory Guarantee Date.
		@param IsGuaranteeDateMandatory 
		The entry of a Guarantee Date is mandatory when creating a Product Instance
	  */
	public void setIsGuaranteeDateMandatory (boolean IsGuaranteeDateMandatory)
	{
		set_Value (COLUMNNAME_IsGuaranteeDateMandatory, Boolean.valueOf(IsGuaranteeDateMandatory));
	}

	/** Get Mandatory Guarantee Date.
		@return The entry of a Guarantee Date is mandatory when creating a Product Instance
	  */
	public boolean isGuaranteeDateMandatory () 
	{
		Object oo = get_Value(COLUMNNAME_IsGuaranteeDateMandatory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Instance Attribute.
		@param IsInstanceAttribute 
		The product attribute is specific to the instance (like Serial No, Lot or Guarantee Date)
	  */
	public void setIsInstanceAttribute (boolean IsInstanceAttribute)
	{
		set_Value (COLUMNNAME_IsInstanceAttribute, Boolean.valueOf(IsInstanceAttribute));
	}

	/** Get Instance Attribute.
		@return The product attribute is specific to the instance (like Serial No, Lot or Guarantee Date)
	  */
	public boolean isInstanceAttribute () 
	{
		Object oo = get_Value(COLUMNNAME_IsInstanceAttribute);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Lot.
		@param IsLot 
		The product instances have a Lot Number
	  */
	public void setIsLot (boolean IsLot)
	{
		set_Value (COLUMNNAME_IsLot, Boolean.valueOf(IsLot));
	}

	/** Get Lot.
		@return The product instances have a Lot Number
	  */
	public boolean isLot () 
	{
		Object oo = get_Value(COLUMNNAME_IsLot);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Mandatory Lot.
		@param IsLotMandatory 
		The entry of Lot info is mandatory when creating a Product Instance
	  */
	public void setIsLotMandatory (boolean IsLotMandatory)
	{
		set_Value (COLUMNNAME_IsLotMandatory, Boolean.valueOf(IsLotMandatory));
	}

	/** Get Mandatory Lot.
		@return The entry of Lot info is mandatory when creating a Product Instance
	  */
	public boolean isLotMandatory () 
	{
		Object oo = get_Value(COLUMNNAME_IsLotMandatory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Mandatory.
		@param IsMandatory 
		Data entry is required in this column
	  */
	public void setIsMandatory (boolean IsMandatory)
	{
		set_Value (COLUMNNAME_IsMandatory, Boolean.valueOf(IsMandatory));
	}

	/** Get Mandatory.
		@return Data entry is required in this column
	  */
	public boolean isMandatory () 
	{
		Object oo = get_Value(COLUMNNAME_IsMandatory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Serial No.
		@param IsSerNo 
		The product instances have Serial Numbers
	  */
	public void setIsSerNo (boolean IsSerNo)
	{
		set_Value (COLUMNNAME_IsSerNo, Boolean.valueOf(IsSerNo));
	}

	/** Get Serial No.
		@return The product instances have Serial Numbers
	  */
	public boolean isSerNo () 
	{
		Object oo = get_Value(COLUMNNAME_IsSerNo);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Mandatory Serial No.
		@param IsSerNoMandatory 
		The entry of a Serial No is mandatory when creating a Product Instance
	  */
	public void setIsSerNoMandatory (boolean IsSerNoMandatory)
	{
		set_Value (COLUMNNAME_IsSerNoMandatory, Boolean.valueOf(IsSerNoMandatory));
	}

	/** Get Mandatory Serial No.
		@return The entry of a Serial No is mandatory when creating a Product Instance
	  */
	public boolean isSerNoMandatory () 
	{
		Object oo = get_Value(COLUMNNAME_IsSerNoMandatory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_M_AttributeSearch getM_AttributeSearch() throws RuntimeException
    {
		return (org.compiere.model.I_M_AttributeSearch)MTable.get(getCtx(), org.compiere.model.I_M_AttributeSearch.Table_Name)
			.getPO(getM_AttributeSearch_ID(), get_TrxName());	}

	/** Set Attribute Search.
		@param M_AttributeSearch_ID 
		Common Search Attribute 
	  */
	public void setM_AttributeSearch_ID (int M_AttributeSearch_ID)
	{
		if (M_AttributeSearch_ID < 1) 
			set_Value (COLUMNNAME_M_AttributeSearch_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSearch_ID, Integer.valueOf(M_AttributeSearch_ID));
	}

	/** Get Attribute Search.
		@return Common Search Attribute 
	  */
	public int getM_AttributeSearch_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSearch_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException
    {
		return (I_M_AttributeSetInstance)MTable.get(getCtx(), I_M_AttributeSetInstance.Table_Name)
			.getPO(getM_AttributeSetInstance_ID(), get_TrxName());	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0) 
			set_ValueNoCheck (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_AttributeSet getM_AttributeSet() throws RuntimeException
    {
		return (org.compiere.model.I_M_AttributeSet)MTable.get(getCtx(), org.compiere.model.I_M_AttributeSet.Table_Name)
			.getPO(getM_AttributeSet_ID(), get_TrxName());	}

	/** Set Attribute Set.
		@param M_AttributeSet_ID 
		Product Attribute Set
	  */
	public void setM_AttributeSet_ID (int M_AttributeSet_ID)
	{
		if (M_AttributeSet_ID < 0) 
			set_Value (COLUMNNAME_M_AttributeSet_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSet_ID, Integer.valueOf(M_AttributeSet_ID));
	}

	/** Get Attribute Set.
		@return Product Attribute Set
	  */
	public int getM_AttributeSet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_AttributeValue getM_AttributeValue() throws RuntimeException
    {
		return (org.compiere.model.I_M_AttributeValue)MTable.get(getCtx(), org.compiere.model.I_M_AttributeValue.Table_Name)
			.getPO(getM_AttributeValue_ID(), get_TrxName());	}

	/** Set Attribute Value.
		@param M_AttributeValue_ID 
		Product Attribute Value
	  */
	public void setM_AttributeValue_ID (int M_AttributeValue_ID)
	{
		if (M_AttributeValue_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_AttributeValue_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_AttributeValue_ID, Integer.valueOf(M_AttributeValue_ID));
	}

	/** Get Attribute Value.
		@return Product Attribute Value
	  */
	public int getM_AttributeValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Attribute getM_Attribute() throws RuntimeException
    {
		return (org.compiere.model.I_M_Attribute)MTable.get(getCtx(), org.compiere.model.I_M_Attribute.Table_Name)
			.getPO(getM_Attribute_ID(), get_TrxName());	}

	/** Set Attribute.
		@param M_Attribute_ID 
		Product Attribute
	  */
	public void setM_Attribute_ID (int M_Attribute_ID)
	{
		if (M_Attribute_ID < 1) 
			set_Value (COLUMNNAME_M_Attribute_ID, null);
		else 
			set_Value (COLUMNNAME_M_Attribute_ID, Integer.valueOf(M_Attribute_ID));
	}

	/** Get Attribute.
		@return Product Attribute
	  */
	public int getM_Attribute_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Attribute_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** MandatoryType AD_Reference_ID=324 */
	public static final int MANDATORYTYPE_AD_Reference_ID=324;
	/** Not Mandatory = N */
	public static final String MANDATORYTYPE_NotMandatory = "N";
	/** Always Mandatory = Y */
	public static final String MANDATORYTYPE_AlwaysMandatory = "Y";
	/** When Shipping = S */
	public static final String MANDATORYTYPE_WhenShipping = "S";
	/** Set Mandatory Type.
		@param MandatoryType 
		The specification of a Product Attribute Instance is mandatory
	  */
	public void setMandatoryType (String MandatoryType)
	{

		set_Value (COLUMNNAME_MandatoryType, MandatoryType);
	}

	/** Get Mandatory Type.
		@return The specification of a Product Attribute Instance is mandatory
	  */
	public String getMandatoryType () 
	{
		return (String)get_Value(COLUMNNAME_MandatoryType);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Product Key.
		@param ProductValue 
		Key of the Product
	  */
	public void setProductValue (String ProductValue)
	{
		set_Value (COLUMNNAME_ProductValue, ProductValue);
	}

	/** Get Product Key.
		@return Key of the Product
	  */
	public String getProductValue () 
	{
		return (String)get_Value(COLUMNNAME_ProductValue);
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