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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;

/** Generated Model for C_BPartner_Product
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_C_BPartner_Product extends PO implements I_C_BPartner_Product, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_C_BPartner_Product (Properties ctx, int C_BPartner_Product_ID, String trxName)
    {
      super (ctx, C_BPartner_Product_ID, trxName);
      /** if (C_BPartner_Product_ID == 0)
        {
			setC_BPartner_ID (0);
			setIsManufacturer (false);
// N
			setM_Product_ID (0);
			setShelfLifeMinDays (0);
			setShelfLifeMinPct (0);
        } */
    }

    /** Load Constructor */
    public X_C_BPartner_Product (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_BPartner_Product[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
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

	/** Set Is Manufacturer.
		@param IsManufacturer 
		Indicate role of this Business partner as Manufacturer
	  */
	public void setIsManufacturer (boolean IsManufacturer)
	{
		set_Value (COLUMNNAME_IsManufacturer, Boolean.valueOf(IsManufacturer));
	}

	/** Get Is Manufacturer.
		@return Indicate role of this Business partner as Manufacturer
	  */
	public boolean isManufacturer () 
	{
		Object oo = get_Value(COLUMNNAME_IsManufacturer);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
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

	/** Set Manufacturer.
		@param Manufacturer 
		Manufacturer of the Product
	  */
	public void setManufacturer (String Manufacturer)
	{
		set_Value (COLUMNNAME_Manufacturer, Manufacturer);
	}

	/** Get Manufacturer.
		@return Manufacturer of the Product
	  */
	public String getManufacturer () 
	{
		return (String)get_Value(COLUMNNAME_Manufacturer);
	}

	/** Set Quality Rating.
		@param QualityRating 
		Method for rating vendors
	  */
	public void setQualityRating (BigDecimal QualityRating)
	{
		set_Value (COLUMNNAME_QualityRating, QualityRating);
	}

	/** Get Quality Rating.
		@return Method for rating vendors
	  */
	public BigDecimal getQualityRating () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QualityRating);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Min Shelf Life Days.
		@param ShelfLifeMinDays 
		Minimum Shelf Life in days based on Product Instance Guarantee Date
	  */
	public void setShelfLifeMinDays (int ShelfLifeMinDays)
	{
		set_Value (COLUMNNAME_ShelfLifeMinDays, Integer.valueOf(ShelfLifeMinDays));
	}

	/** Get Min Shelf Life Days.
		@return Minimum Shelf Life in days based on Product Instance Guarantee Date
	  */
	public int getShelfLifeMinDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ShelfLifeMinDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Min Shelf Life %.
		@param ShelfLifeMinPct 
		Minimum Shelf Life in percent based on Product Instance Guarantee Date
	  */
	public void setShelfLifeMinPct (int ShelfLifeMinPct)
	{
		set_Value (COLUMNNAME_ShelfLifeMinPct, Integer.valueOf(ShelfLifeMinPct));
	}

	/** Get Min Shelf Life %.
		@return Minimum Shelf Life in percent based on Product Instance Guarantee Date
	  */
	public int getShelfLifeMinPct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ShelfLifeMinPct);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Partner Category.
		@param VendorCategory 
		Product Category of the Business Partner
	  */
	public void setVendorCategory (String VendorCategory)
	{
		set_Value (COLUMNNAME_VendorCategory, VendorCategory);
	}

	/** Get Partner Category.
		@return Product Category of the Business Partner
	  */
	public String getVendorCategory () 
	{
		return (String)get_Value(COLUMNNAME_VendorCategory);
	}

	/** Set Partner Product Key.
		@param VendorProductNo 
		Product Key of the Business Partner
	  */
	public void setVendorProductNo (String VendorProductNo)
	{
		set_Value (COLUMNNAME_VendorProductNo, VendorProductNo);
	}

	/** Get Partner Product Key.
		@return Product Key of the Business Partner
	  */
	public String getVendorProductNo () 
	{
		return (String)get_Value(COLUMNNAME_VendorProductNo);
	}
}