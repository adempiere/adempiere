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
import org.compiere.util.KeyNamePair;

/** Generated Model for M_Product_BOM
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_M_Product_BOM extends PO implements I_M_Product_BOM, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_M_Product_BOM (Properties ctx, int M_Product_BOM_ID, String trxName)
    {
      super (ctx, M_Product_BOM_ID, trxName);
      /** if (M_Product_BOM_ID == 0)
        {
			setBOMQty (Env.ZERO);
// 1
			setLine (0);
// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM M_Product_BOM WHERE M_Product_ID=@M_Product_ID@
			setM_ProductBOM_ID (0);
			setM_Product_BOM_ID (0);
			setM_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_M_Product_BOM (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_Product_BOM[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BOM Quantity.
		@param BOMQty 
		Bill of Materials Quantity
	  */
	public void setBOMQty (BigDecimal BOMQty)
	{
		set_Value (COLUMNNAME_BOMQty, BOMQty);
	}

	/** Get BOM Quantity.
		@return Bill of Materials Quantity
	  */
	public BigDecimal getBOMQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BOMQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** BOMType AD_Reference_ID=279 */
	public static final int BOMTYPE_AD_Reference_ID=279;
	/** Standard Part = P */
	public static final String BOMTYPE_StandardPart = "P";
	/** Optional Part = O */
	public static final String BOMTYPE_OptionalPart = "O";
	/** In alternative Group 1 = 1 */
	public static final String BOMTYPE_InAlternativeGroup1 = "1";
	/** In alternative Group 2 = 2 */
	public static final String BOMTYPE_InAlternativeGroup2 = "2";
	/** In alternaltve Group 3 = 3 */
	public static final String BOMTYPE_InAlternaltveGroup3 = "3";
	/** In alternative Group 4 = 4 */
	public static final String BOMTYPE_InAlternativeGroup4 = "4";
	/** In alternative Group 5 = 5 */
	public static final String BOMTYPE_InAlternativeGroup5 = "5";
	/** In alternative Group 6 = 6 */
	public static final String BOMTYPE_InAlternativeGroup6 = "6";
	/** In alternative Group 7 = 7 */
	public static final String BOMTYPE_InAlternativeGroup7 = "7";
	/** In alternative Group 8 = 8 */
	public static final String BOMTYPE_InAlternativeGroup8 = "8";
	/** In alternative Group 9 = 9 */
	public static final String BOMTYPE_InAlternativeGroup9 = "9";
	/** Set BOM Type.
		@param BOMType 
		Type of BOM
	  */
	public void setBOMType (String BOMType)
	{

		set_Value (COLUMNNAME_BOMType, BOMType);
	}

	/** Get BOM Type.
		@return Type of BOM
	  */
	public String getBOMType () 
	{
		return (String)get_Value(COLUMNNAME_BOMType);
	}

	/** Set Standard Cost.
		@param CostStandard 
		Standard Costs
	  */
	public void setCostStandard (BigDecimal CostStandard)
	{
		throw new IllegalArgumentException ("CostStandard is virtual column");	}

	/** Get Standard Cost.
		@return Standard Costs
	  */
	public BigDecimal getCostStandard () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostStandard);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Std Cost Amount Sum.
		@param CostStandardCumAmt 
		Standard Cost Invoice Amount Sum (internal)
	  */
	public void setCostStandardCumAmt (BigDecimal CostStandardCumAmt)
	{
		throw new IllegalArgumentException ("CostStandardCumAmt is virtual column");	}

	/** Get Std Cost Amount Sum.
		@return Standard Cost Invoice Amount Sum (internal)
	  */
	public BigDecimal getCostStandardCumAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CostStandardCumAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Bill of Materials.
		@param IsBillOfMaterial 
		Bill of Materials
	  */
	public void setIsBillOfMaterial (boolean IsBillOfMaterial)
	{
		throw new IllegalArgumentException ("IsBillOfMaterial is virtual column");	}

	/** Get Bill of Materials.
		@return Bill of Materials
	  */
	public boolean isBillOfMaterial () 
	{
		Object oo = get_Value(COLUMNNAME_IsBillOfMaterial);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_PartType getM_PartType() throws RuntimeException
    {
		return (org.compiere.model.I_M_PartType)MTable.get(getCtx(), org.compiere.model.I_M_PartType.Table_Name)
			.getPO(getM_PartType_ID(), get_TrxName());	}

	/** Set Part Type.
		@param M_PartType_ID Part Type	  */
	public void setM_PartType_ID (int M_PartType_ID)
	{
		throw new IllegalArgumentException ("M_PartType_ID is virtual column");	}

	/** Get Part Type.
		@return Part Type	  */
	public int getM_PartType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PartType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_ProductBOM() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_ProductBOM_ID(), get_TrxName());	}

	/** Set BOM Product.
		@param M_ProductBOM_ID 
		Bill of Material Component Product
	  */
	public void setM_ProductBOM_ID (int M_ProductBOM_ID)
	{
		if (M_ProductBOM_ID < 1) 
			set_Value (COLUMNNAME_M_ProductBOM_ID, null);
		else 
			set_Value (COLUMNNAME_M_ProductBOM_ID, Integer.valueOf(M_ProductBOM_ID));
	}

	/** Get BOM Product.
		@return Bill of Material Component Product
	  */
	public int getM_ProductBOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductBOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getM_ProductBOM_ID()));
    }

	/** Set BOM Line.
		@param M_Product_BOM_ID BOM Line	  */
	public void setM_Product_BOM_ID (int M_Product_BOM_ID)
	{
		if (M_Product_BOM_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Product_BOM_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Product_BOM_ID, Integer.valueOf(M_Product_BOM_ID));
	}

	/** Get BOM Line.
		@return BOM Line	  */
	public int getM_Product_BOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_BOM_ID);
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		throw new IllegalArgumentException ("Value is virtual column");	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}