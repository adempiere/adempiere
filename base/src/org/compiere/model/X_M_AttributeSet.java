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
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_AttributeSet
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_M_AttributeSet extends PO implements I_M_AttributeSet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_M_AttributeSet (Properties ctx, int M_AttributeSet_ID, String trxName)
    {
      super (ctx, M_AttributeSet_ID, trxName);
      /** if (M_AttributeSet_ID == 0)
        {
			setIsGuaranteeDate (false);
			setIsGuaranteeDateMandatory (false);
			setIsInstanceAttribute (false);
			setIsLot (false);
			setIsLotMandatory (false);
			setIsSerNo (false);
			setIsSerNoMandatory (false);
			setM_AttributeSet_ID (0);
			setMandatoryType (null);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_M_AttributeSet (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_M_AttributeSet[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Lot Char End Overwrite.
		@param LotCharEOverwrite 
		Lot/Batch End Indicator overwrite - default »
	  */
	public void setLotCharEOverwrite (String LotCharEOverwrite)
	{
		set_Value (COLUMNNAME_LotCharEOverwrite, LotCharEOverwrite);
	}

	/** Get Lot Char End Overwrite.
		@return Lot/Batch End Indicator overwrite - default »
	  */
	public String getLotCharEOverwrite () 
	{
		return (String)get_Value(COLUMNNAME_LotCharEOverwrite);
	}

	/** Set Lot Char Start Overwrite.
		@param LotCharSOverwrite 
		Lot/Batch Start Indicator overwrite - default «
	  */
	public void setLotCharSOverwrite (String LotCharSOverwrite)
	{
		set_Value (COLUMNNAME_LotCharSOverwrite, LotCharSOverwrite);
	}

	/** Get Lot Char Start Overwrite.
		@return Lot/Batch Start Indicator overwrite - default «
	  */
	public String getLotCharSOverwrite () 
	{
		return (String)get_Value(COLUMNNAME_LotCharSOverwrite);
	}

	/** Set Attribute Set.
		@param M_AttributeSet_ID 
		Product Attribute Set
	  */
	public void setM_AttributeSet_ID (int M_AttributeSet_ID)
	{
		if (M_AttributeSet_ID < 0) 
			set_ValueNoCheck (COLUMNNAME_M_AttributeSet_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_AttributeSet_ID, Integer.valueOf(M_AttributeSet_ID));
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

	public org.compiere.model.I_M_LotCtl getM_LotCtl() throws RuntimeException
    {
		return (org.compiere.model.I_M_LotCtl)MTable.get(getCtx(), org.compiere.model.I_M_LotCtl.Table_Name)
			.getPO(getM_LotCtl_ID(), get_TrxName());	}

	/** Set Lot Control.
		@param M_LotCtl_ID 
		Product Lot Control
	  */
	public void setM_LotCtl_ID (int M_LotCtl_ID)
	{
		if (M_LotCtl_ID < 1) 
			set_Value (COLUMNNAME_M_LotCtl_ID, null);
		else 
			set_Value (COLUMNNAME_M_LotCtl_ID, Integer.valueOf(M_LotCtl_ID));
	}

	/** Get Lot Control.
		@return Product Lot Control
	  */
	public int getM_LotCtl_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_LotCtl_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_SerNoCtl getM_SerNoCtl() throws RuntimeException
    {
		return (org.compiere.model.I_M_SerNoCtl)MTable.get(getCtx(), org.compiere.model.I_M_SerNoCtl.Table_Name)
			.getPO(getM_SerNoCtl_ID(), get_TrxName());	}

	/** Set Serial No Control.
		@param M_SerNoCtl_ID 
		Product Serial Number Control
	  */
	public void setM_SerNoCtl_ID (int M_SerNoCtl_ID)
	{
		if (M_SerNoCtl_ID < 1) 
			set_Value (COLUMNNAME_M_SerNoCtl_ID, null);
		else 
			set_Value (COLUMNNAME_M_SerNoCtl_ID, Integer.valueOf(M_SerNoCtl_ID));
	}

	/** Get Serial No Control.
		@return Product Serial Number Control
	  */
	public int getM_SerNoCtl_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_SerNoCtl_ID);
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set SerNo Char End Overwrite.
		@param SerNoCharEOverwrite 
		Serial Number End Indicator overwrite - default empty
	  */
	public void setSerNoCharEOverwrite (String SerNoCharEOverwrite)
	{
		set_Value (COLUMNNAME_SerNoCharEOverwrite, SerNoCharEOverwrite);
	}

	/** Get SerNo Char End Overwrite.
		@return Serial Number End Indicator overwrite - default empty
	  */
	public String getSerNoCharEOverwrite () 
	{
		return (String)get_Value(COLUMNNAME_SerNoCharEOverwrite);
	}

	/** Set SerNo Char Start Overwrite.
		@param SerNoCharSOverwrite 
		Serial Number Start Indicator overwrite - default #
	  */
	public void setSerNoCharSOverwrite (String SerNoCharSOverwrite)
	{
		set_Value (COLUMNNAME_SerNoCharSOverwrite, SerNoCharSOverwrite);
	}

	/** Get SerNo Char Start Overwrite.
		@return Serial Number Start Indicator overwrite - default #
	  */
	public String getSerNoCharSOverwrite () 
	{
		return (String)get_Value(COLUMNNAME_SerNoCharSOverwrite);
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