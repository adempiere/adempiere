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
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_DiscountSchemaLine
 *  @author Adempiere (generated) 
 *  @version Release 3.5.4a - $Id$ */
public class X_M_DiscountSchemaLine extends PO implements I_M_DiscountSchemaLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20090915L;

    /** Standard Constructor */
    public X_M_DiscountSchemaLine (Properties ctx, int M_DiscountSchemaLine_ID, String trxName)
    {
      super (ctx, M_DiscountSchemaLine_ID, trxName);
      /** if (M_DiscountSchemaLine_ID == 0)
        {
			setC_ConversionType_ID (0);
			setConversionDate (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setLimit_AddAmt (Env.ZERO);
			setLimit_Base (null);
// X
			setLimit_Discount (Env.ZERO);
			setLimit_MaxAmt (Env.ZERO);
			setLimit_MinAmt (Env.ZERO);
			setLimit_Rounding (null);
// C
			setList_AddAmt (Env.ZERO);
			setList_Base (null);
// L
			setList_Discount (Env.ZERO);
			setList_MaxAmt (Env.ZERO);
			setList_MinAmt (Env.ZERO);
			setList_Rounding (null);
// C
			setM_DiscountSchema_ID (0);
			setM_DiscountSchemaLine_ID (0);
			setSeqNo (0);
// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM M_DiscountSchemaLine WHERE M_DiscountSchema_ID=@M_DiscountSchema_ID@
			setStd_AddAmt (Env.ZERO);
			setStd_Base (null);
// S
			setStd_Discount (Env.ZERO);
			setStd_MaxAmt (Env.ZERO);
			setStd_MinAmt (Env.ZERO);
			setStd_Rounding (null);
// C
        } */
    }

    /** Load Constructor */
    public X_M_DiscountSchemaLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_DiscountSchemaLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (I_C_BPartner)MTable.get(getCtx(), I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	public I_C_ConversionType getC_ConversionType() throws RuntimeException
    {
		return (I_C_ConversionType)MTable.get(getCtx(), I_C_ConversionType.Table_Name)
			.getPO(getC_ConversionType_ID(), get_TrxName());	}

	/** Set Currency Type.
		@param C_ConversionType_ID 
		Currency Conversion Rate Type
	  */
	public void setC_ConversionType_ID (int C_ConversionType_ID)
	{
		if (C_ConversionType_ID < 1) 
			set_Value (COLUMNNAME_C_ConversionType_ID, null);
		else 
			set_Value (COLUMNNAME_C_ConversionType_ID, Integer.valueOf(C_ConversionType_ID));
	}

	/** Get Currency Type.
		@return Currency Conversion Rate Type
	  */
	public int getC_ConversionType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ConversionType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Classification.
		@param Classification 
		Classification for grouping
	  */
	public void setClassification (String Classification)
	{
		set_Value (COLUMNNAME_Classification, Classification);
	}

	/** Get Classification.
		@return Classification for grouping
	  */
	public String getClassification () 
	{
		return (String)get_Value(COLUMNNAME_Classification);
	}

	/** Set Conversion Date.
		@param ConversionDate 
		Date for selecting conversion rate
	  */
	public void setConversionDate (Timestamp ConversionDate)
	{
		set_Value (COLUMNNAME_ConversionDate, ConversionDate);
	}

	/** Get Conversion Date.
		@return Date for selecting conversion rate
	  */
	public Timestamp getConversionDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ConversionDate);
	}

	/** Set Group1.
		@param Group1 Group1	  */
	public void setGroup1 (String Group1)
	{
		set_Value (COLUMNNAME_Group1, Group1);
	}

	/** Get Group1.
		@return Group1	  */
	public String getGroup1 () 
	{
		return (String)get_Value(COLUMNNAME_Group1);
	}

	/** Set Group2.
		@param Group2 Group2	  */
	public void setGroup2 (String Group2)
	{
		set_Value (COLUMNNAME_Group2, Group2);
	}

	/** Get Group2.
		@return Group2	  */
	public String getGroup2 () 
	{
		return (String)get_Value(COLUMNNAME_Group2);
	}

	/** Set Limit price Surcharge Amount.
		@param Limit_AddAmt 
		Amount added to the converted/copied price before multiplying
	  */
	public void setLimit_AddAmt (BigDecimal Limit_AddAmt)
	{
		set_Value (COLUMNNAME_Limit_AddAmt, Limit_AddAmt);
	}

	/** Get Limit price Surcharge Amount.
		@return Amount added to the converted/copied price before multiplying
	  */
	public BigDecimal getLimit_AddAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Limit_AddAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Limit_Base AD_Reference_ID=194 */
	public static final int LIMIT_BASE_AD_Reference_ID=194;
	/** List Price = L */
	public static final String LIMIT_BASE_ListPrice = "L";
	/** Standard Price = S */
	public static final String LIMIT_BASE_StandardPrice = "S";
	/** Limit (PO) Price = X */
	public static final String LIMIT_BASE_LimitPOPrice = "X";
	/** Fixed Price = F */
	public static final String LIMIT_BASE_FixedPrice = "F";
	/** Set Limit price Base.
		@param Limit_Base 
		Base price for calculation of the new price
	  */
	public void setLimit_Base (String Limit_Base)
	{

		set_Value (COLUMNNAME_Limit_Base, Limit_Base);
	}

	/** Get Limit price Base.
		@return Base price for calculation of the new price
	  */
	public String getLimit_Base () 
	{
		return (String)get_Value(COLUMNNAME_Limit_Base);
	}

	/** Set Limit price Discount %.
		@param Limit_Discount 
		Discount in percent to be subtracted from base, if negative it will be added to base price
	  */
	public void setLimit_Discount (BigDecimal Limit_Discount)
	{
		set_Value (COLUMNNAME_Limit_Discount, Limit_Discount);
	}

	/** Get Limit price Discount %.
		@return Discount in percent to be subtracted from base, if negative it will be added to base price
	  */
	public BigDecimal getLimit_Discount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Limit_Discount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Fixed Limit Price.
		@param Limit_Fixed 
		Fixed Limit Price (not calculated)
	  */
	public void setLimit_Fixed (BigDecimal Limit_Fixed)
	{
		set_Value (COLUMNNAME_Limit_Fixed, Limit_Fixed);
	}

	/** Get Fixed Limit Price.
		@return Fixed Limit Price (not calculated)
	  */
	public BigDecimal getLimit_Fixed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Limit_Fixed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Limit price max Margin.
		@param Limit_MaxAmt 
		Maximum difference to original limit price; ignored if zero
	  */
	public void setLimit_MaxAmt (BigDecimal Limit_MaxAmt)
	{
		set_Value (COLUMNNAME_Limit_MaxAmt, Limit_MaxAmt);
	}

	/** Get Limit price max Margin.
		@return Maximum difference to original limit price; ignored if zero
	  */
	public BigDecimal getLimit_MaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Limit_MaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Limit price min Margin.
		@param Limit_MinAmt 
		Minimum difference to original limit price; ignored if zero
	  */
	public void setLimit_MinAmt (BigDecimal Limit_MinAmt)
	{
		set_Value (COLUMNNAME_Limit_MinAmt, Limit_MinAmt);
	}

	/** Get Limit price min Margin.
		@return Minimum difference to original limit price; ignored if zero
	  */
	public BigDecimal getLimit_MinAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Limit_MinAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Limit_Rounding AD_Reference_ID=155 */
	public static final int LIMIT_ROUNDING_AD_Reference_ID=155;
	/** Whole Number .00 = 0 */
	public static final String LIMIT_ROUNDING_WholeNumber00 = "0";
	/** No Rounding = N */
	public static final String LIMIT_ROUNDING_NoRounding = "N";
	/** Quarter .25 .50 .75 = Q */
	public static final String LIMIT_ROUNDING_Quarter255075 = "Q";
	/** Dime .10, .20, .30, ... = D */
	public static final String LIMIT_ROUNDING_Dime102030 = "D";
	/** Nickel .05, .10, .15, ... = 5 */
	public static final String LIMIT_ROUNDING_Nickel051015 = "5";
	/** Ten 10.00, 20.00, .. = T */
	public static final String LIMIT_ROUNDING_Ten10002000 = "T";
	/** Currency Precision = C */
	public static final String LIMIT_ROUNDING_CurrencyPrecision = "C";
	/** Ending in 9/5 = 9 */
	public static final String LIMIT_ROUNDING_EndingIn95 = "9";
	/** Set Limit price Rounding.
		@param Limit_Rounding 
		Rounding of the final result
	  */
	public void setLimit_Rounding (String Limit_Rounding)
	{

		set_Value (COLUMNNAME_Limit_Rounding, Limit_Rounding);
	}

	/** Get Limit price Rounding.
		@return Rounding of the final result
	  */
	public String getLimit_Rounding () 
	{
		return (String)get_Value(COLUMNNAME_Limit_Rounding);
	}

	/** Set List price Surcharge Amount.
		@param List_AddAmt 
		List Price Surcharge Amount
	  */
	public void setList_AddAmt (BigDecimal List_AddAmt)
	{
		set_Value (COLUMNNAME_List_AddAmt, List_AddAmt);
	}

	/** Get List price Surcharge Amount.
		@return List Price Surcharge Amount
	  */
	public BigDecimal getList_AddAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_List_AddAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** List_Base AD_Reference_ID=194 */
	public static final int LIST_BASE_AD_Reference_ID=194;
	/** List Price = L */
	public static final String LIST_BASE_ListPrice = "L";
	/** Standard Price = S */
	public static final String LIST_BASE_StandardPrice = "S";
	/** Limit (PO) Price = X */
	public static final String LIST_BASE_LimitPOPrice = "X";
	/** Fixed Price = F */
	public static final String LIST_BASE_FixedPrice = "F";
	/** Set List price Base.
		@param List_Base 
		Price used as the basis for price list calculations
	  */
	public void setList_Base (String List_Base)
	{

		set_Value (COLUMNNAME_List_Base, List_Base);
	}

	/** Get List price Base.
		@return Price used as the basis for price list calculations
	  */
	public String getList_Base () 
	{
		return (String)get_Value(COLUMNNAME_List_Base);
	}

	/** Set List price Discount %.
		@param List_Discount 
		Discount from list price as a percentage
	  */
	public void setList_Discount (BigDecimal List_Discount)
	{
		set_Value (COLUMNNAME_List_Discount, List_Discount);
	}

	/** Get List price Discount %.
		@return Discount from list price as a percentage
	  */
	public BigDecimal getList_Discount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_List_Discount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Fixed List Price.
		@param List_Fixed 
		Fixes List Price (not calculated)
	  */
	public void setList_Fixed (BigDecimal List_Fixed)
	{
		set_Value (COLUMNNAME_List_Fixed, List_Fixed);
	}

	/** Get Fixed List Price.
		@return Fixes List Price (not calculated)
	  */
	public BigDecimal getList_Fixed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_List_Fixed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set List price max Margin.
		@param List_MaxAmt 
		Maximum margin for a product
	  */
	public void setList_MaxAmt (BigDecimal List_MaxAmt)
	{
		set_Value (COLUMNNAME_List_MaxAmt, List_MaxAmt);
	}

	/** Get List price max Margin.
		@return Maximum margin for a product
	  */
	public BigDecimal getList_MaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_List_MaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set List price min Margin.
		@param List_MinAmt 
		Minimum margin for a product
	  */
	public void setList_MinAmt (BigDecimal List_MinAmt)
	{
		set_Value (COLUMNNAME_List_MinAmt, List_MinAmt);
	}

	/** Get List price min Margin.
		@return Minimum margin for a product
	  */
	public BigDecimal getList_MinAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_List_MinAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** List_Rounding AD_Reference_ID=155 */
	public static final int LIST_ROUNDING_AD_Reference_ID=155;
	/** Whole Number .00 = 0 */
	public static final String LIST_ROUNDING_WholeNumber00 = "0";
	/** No Rounding = N */
	public static final String LIST_ROUNDING_NoRounding = "N";
	/** Quarter .25 .50 .75 = Q */
	public static final String LIST_ROUNDING_Quarter255075 = "Q";
	/** Dime .10, .20, .30, ... = D */
	public static final String LIST_ROUNDING_Dime102030 = "D";
	/** Nickel .05, .10, .15, ... = 5 */
	public static final String LIST_ROUNDING_Nickel051015 = "5";
	/** Ten 10.00, 20.00, .. = T */
	public static final String LIST_ROUNDING_Ten10002000 = "T";
	/** Currency Precision = C */
	public static final String LIST_ROUNDING_CurrencyPrecision = "C";
	/** Ending in 9/5 = 9 */
	public static final String LIST_ROUNDING_EndingIn95 = "9";
	/** Set List price Rounding.
		@param List_Rounding 
		Rounding rule for final list price
	  */
	public void setList_Rounding (String List_Rounding)
	{

		set_Value (COLUMNNAME_List_Rounding, List_Rounding);
	}

	/** Get List price Rounding.
		@return Rounding rule for final list price
	  */
	public String getList_Rounding () 
	{
		return (String)get_Value(COLUMNNAME_List_Rounding);
	}

	public I_M_DiscountSchema getM_DiscountSchema() throws RuntimeException
    {
		return (I_M_DiscountSchema)MTable.get(getCtx(), I_M_DiscountSchema.Table_Name)
			.getPO(getM_DiscountSchema_ID(), get_TrxName());	}

	/** Set Discount Schema.
		@param M_DiscountSchema_ID 
		Schema to calculate the trade discount percentage
	  */
	public void setM_DiscountSchema_ID (int M_DiscountSchema_ID)
	{
		if (M_DiscountSchema_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_DiscountSchema_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_DiscountSchema_ID, Integer.valueOf(M_DiscountSchema_ID));
	}

	/** Get Discount Schema.
		@return Schema to calculate the trade discount percentage
	  */
	public int getM_DiscountSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_DiscountSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Discount Pricelist.
		@param M_DiscountSchemaLine_ID 
		Line of the pricelist trade discount schema
	  */
	public void setM_DiscountSchemaLine_ID (int M_DiscountSchemaLine_ID)
	{
		if (M_DiscountSchemaLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_DiscountSchemaLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_DiscountSchemaLine_ID, Integer.valueOf(M_DiscountSchemaLine_ID));
	}

	/** Get Discount Pricelist.
		@return Line of the pricelist trade discount schema
	  */
	public int getM_DiscountSchemaLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_DiscountSchemaLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product_Category getM_Product_Category() throws RuntimeException
    {
		return (I_M_Product_Category)MTable.get(getCtx(), I_M_Product_Category.Table_Name)
			.getPO(getM_Product_Category_ID(), get_TrxName());	}

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Category_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product getM_Product() throws RuntimeException
    {
		return (I_M_Product)MTable.get(getCtx(), I_M_Product.Table_Name)
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

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getSeqNo()));
    }

	/** Set Standard price Surcharge Amount.
		@param Std_AddAmt 
		Amount added to a price as a surcharge
	  */
	public void setStd_AddAmt (BigDecimal Std_AddAmt)
	{
		set_Value (COLUMNNAME_Std_AddAmt, Std_AddAmt);
	}

	/** Get Standard price Surcharge Amount.
		@return Amount added to a price as a surcharge
	  */
	public BigDecimal getStd_AddAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Std_AddAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Std_Base AD_Reference_ID=194 */
	public static final int STD_BASE_AD_Reference_ID=194;
	/** List Price = L */
	public static final String STD_BASE_ListPrice = "L";
	/** Standard Price = S */
	public static final String STD_BASE_StandardPrice = "S";
	/** Limit (PO) Price = X */
	public static final String STD_BASE_LimitPOPrice = "X";
	/** Fixed Price = F */
	public static final String STD_BASE_FixedPrice = "F";
	/** Set Standard price Base.
		@param Std_Base 
		Base price for calculating new standard price
	  */
	public void setStd_Base (String Std_Base)
	{

		set_Value (COLUMNNAME_Std_Base, Std_Base);
	}

	/** Get Standard price Base.
		@return Base price for calculating new standard price
	  */
	public String getStd_Base () 
	{
		return (String)get_Value(COLUMNNAME_Std_Base);
	}

	/** Set Standard price Discount %.
		@param Std_Discount 
		Discount percentage to subtract from base price
	  */
	public void setStd_Discount (BigDecimal Std_Discount)
	{
		set_Value (COLUMNNAME_Std_Discount, Std_Discount);
	}

	/** Get Standard price Discount %.
		@return Discount percentage to subtract from base price
	  */
	public BigDecimal getStd_Discount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Std_Discount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Fixed Standard Price.
		@param Std_Fixed 
		Fixed Standard Price (not calculated)
	  */
	public void setStd_Fixed (BigDecimal Std_Fixed)
	{
		set_Value (COLUMNNAME_Std_Fixed, Std_Fixed);
	}

	/** Get Fixed Standard Price.
		@return Fixed Standard Price (not calculated)
	  */
	public BigDecimal getStd_Fixed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Std_Fixed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Standard max Margin.
		@param Std_MaxAmt 
		Maximum margin allowed for a product
	  */
	public void setStd_MaxAmt (BigDecimal Std_MaxAmt)
	{
		set_Value (COLUMNNAME_Std_MaxAmt, Std_MaxAmt);
	}

	/** Get Standard max Margin.
		@return Maximum margin allowed for a product
	  */
	public BigDecimal getStd_MaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Std_MaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Standard price min Margin.
		@param Std_MinAmt 
		Minimum margin allowed for a product
	  */
	public void setStd_MinAmt (BigDecimal Std_MinAmt)
	{
		set_Value (COLUMNNAME_Std_MinAmt, Std_MinAmt);
	}

	/** Get Standard price min Margin.
		@return Minimum margin allowed for a product
	  */
	public BigDecimal getStd_MinAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Std_MinAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Std_Rounding AD_Reference_ID=155 */
	public static final int STD_ROUNDING_AD_Reference_ID=155;
	/** Whole Number .00 = 0 */
	public static final String STD_ROUNDING_WholeNumber00 = "0";
	/** No Rounding = N */
	public static final String STD_ROUNDING_NoRounding = "N";
	/** Quarter .25 .50 .75 = Q */
	public static final String STD_ROUNDING_Quarter255075 = "Q";
	/** Dime .10, .20, .30, ... = D */
	public static final String STD_ROUNDING_Dime102030 = "D";
	/** Nickel .05, .10, .15, ... = 5 */
	public static final String STD_ROUNDING_Nickel051015 = "5";
	/** Ten 10.00, 20.00, .. = T */
	public static final String STD_ROUNDING_Ten10002000 = "T";
	/** Currency Precision = C */
	public static final String STD_ROUNDING_CurrencyPrecision = "C";
	/** Ending in 9/5 = 9 */
	public static final String STD_ROUNDING_EndingIn95 = "9";
	/** Set Standard price Rounding.
		@param Std_Rounding 
		Rounding rule for calculated price
	  */
	public void setStd_Rounding (String Std_Rounding)
	{

		set_Value (COLUMNNAME_Std_Rounding, Std_Rounding);
	}

	/** Get Standard price Rounding.
		@return Rounding rule for calculated price
	  */
	public String getStd_Rounding () 
	{
		return (String)get_Value(COLUMNNAME_Std_Rounding);
	}
}