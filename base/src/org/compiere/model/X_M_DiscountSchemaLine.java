/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_DiscountSchemaLine
 *  @author Adempiere (generated) 
 *  @version Release 3.3.1t - $Id$ */
public class X_M_DiscountSchemaLine extends PO implements I_M_DiscountSchemaLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_DiscountSchemaLine (Properties ctx, int M_DiscountSchemaLine_ID, String trxName)
    {
      super (ctx, M_DiscountSchemaLine_ID, trxName);
      /** if (M_DiscountSchemaLine_ID == 0)
        {
			setC_ConversionType_ID (0);
			setConversionDate (new Timestamp(System.currentTimeMillis()));
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
			setM_DiscountSchemaLine_ID (0);
			setM_DiscountSchema_ID (0);
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

	public I_C_BPartner getC_BPartner() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_BPartner.Table_Name);
        I_C_BPartner result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_BPartner)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_BPartner_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID <= 0) 
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

	public I_C_ConversionType getC_ConversionType() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_ConversionType.Table_Name);
        I_C_ConversionType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_ConversionType)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_ConversionType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Currency Type.
		@param C_ConversionType_ID 
		Currency Conversion Rate Type
	  */
	public void setC_ConversionType_ID (int C_ConversionType_ID)
	{
		if (C_ConversionType_ID < 1)
			 throw new IllegalArgumentException ("C_ConversionType_ID is mandatory.");
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

	/** Set Conversion Date.
		@param ConversionDate 
		Date for selecting conversion rate
	  */
	public void setConversionDate (Timestamp ConversionDate)
	{
		if (ConversionDate == null)
			throw new IllegalArgumentException ("ConversionDate is mandatory.");
		set_Value (COLUMNNAME_ConversionDate, ConversionDate);
	}

	/** Get Conversion Date.
		@return Date for selecting conversion rate
	  */
	public Timestamp getConversionDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ConversionDate);
	}

	/** Set Limit price Surcharge Amount.
		@param Limit_AddAmt 
		Amount added to the converted/copied price before multiplying
	  */
	public void setLimit_AddAmt (BigDecimal Limit_AddAmt)
	{
		if (Limit_AddAmt == null)
			throw new IllegalArgumentException ("Limit_AddAmt is mandatory.");
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
		if (Limit_Base == null) throw new IllegalArgumentException ("Limit_Base is mandatory");
		if (Limit_Base.equals("L") || Limit_Base.equals("S") || Limit_Base.equals("X") || Limit_Base.equals("F")); else throw new IllegalArgumentException ("Limit_Base Invalid value - " + Limit_Base + " - Reference_ID=194 - L - S - X - F");
		if (Limit_Base.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			Limit_Base = Limit_Base.substring(0, 1);
		}
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
		if (Limit_Discount == null)
			throw new IllegalArgumentException ("Limit_Discount is mandatory.");
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
		if (Limit_MaxAmt == null)
			throw new IllegalArgumentException ("Limit_MaxAmt is mandatory.");
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
		if (Limit_MinAmt == null)
			throw new IllegalArgumentException ("Limit_MinAmt is mandatory.");
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
		if (Limit_Rounding == null) throw new IllegalArgumentException ("Limit_Rounding is mandatory");
		if (Limit_Rounding.equals("0") || Limit_Rounding.equals("N") || Limit_Rounding.equals("Q") || Limit_Rounding.equals("D") || Limit_Rounding.equals("5") || Limit_Rounding.equals("T") || Limit_Rounding.equals("C") || Limit_Rounding.equals("9")); else throw new IllegalArgumentException ("Limit_Rounding Invalid value - " + Limit_Rounding + " - Reference_ID=155 - 0 - N - Q - D - 5 - T - C - 9");
		if (Limit_Rounding.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			Limit_Rounding = Limit_Rounding.substring(0, 1);
		}
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
		if (List_AddAmt == null)
			throw new IllegalArgumentException ("List_AddAmt is mandatory.");
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
		if (List_Base == null) throw new IllegalArgumentException ("List_Base is mandatory");
		if (List_Base.equals("L") || List_Base.equals("S") || List_Base.equals("X") || List_Base.equals("F")); else throw new IllegalArgumentException ("List_Base Invalid value - " + List_Base + " - Reference_ID=194 - L - S - X - F");
		if (List_Base.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			List_Base = List_Base.substring(0, 1);
		}
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
		if (List_Discount == null)
			throw new IllegalArgumentException ("List_Discount is mandatory.");
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
		if (List_MaxAmt == null)
			throw new IllegalArgumentException ("List_MaxAmt is mandatory.");
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
		if (List_MinAmt == null)
			throw new IllegalArgumentException ("List_MinAmt is mandatory.");
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
		if (List_Rounding == null) throw new IllegalArgumentException ("List_Rounding is mandatory");
		if (List_Rounding.equals("0") || List_Rounding.equals("N") || List_Rounding.equals("Q") || List_Rounding.equals("D") || List_Rounding.equals("5") || List_Rounding.equals("T") || List_Rounding.equals("C") || List_Rounding.equals("9")); else throw new IllegalArgumentException ("List_Rounding Invalid value - " + List_Rounding + " - Reference_ID=155 - 0 - N - Q - D - 5 - T - C - 9");
		if (List_Rounding.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			List_Rounding = List_Rounding.substring(0, 1);
		}
		set_Value (COLUMNNAME_List_Rounding, List_Rounding);
	}

	/** Get List price Rounding.
		@return Rounding rule for final list price
	  */
	public String getList_Rounding () 
	{
		return (String)get_Value(COLUMNNAME_List_Rounding);
	}

	/** Set Discount Pricelist.
		@param M_DiscountSchemaLine_ID 
		Line of the pricelist trade discount schema
	  */
	public void setM_DiscountSchemaLine_ID (int M_DiscountSchemaLine_ID)
	{
		if (M_DiscountSchemaLine_ID < 1)
			 throw new IllegalArgumentException ("M_DiscountSchemaLine_ID is mandatory.");
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

	public I_M_DiscountSchema getM_DiscountSchema() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_DiscountSchema.Table_Name);
        I_M_DiscountSchema result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_DiscountSchema)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_DiscountSchema_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Discount Schema.
		@param M_DiscountSchema_ID 
		Schema to calculate the trade discount percentage
	  */
	public void setM_DiscountSchema_ID (int M_DiscountSchema_ID)
	{
		if (M_DiscountSchema_ID < 1)
			 throw new IllegalArgumentException ("M_DiscountSchema_ID is mandatory.");
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

	public I_M_Product_Category getM_Product_Category() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_Product_Category.Table_Name);
        I_M_Product_Category result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product_Category)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_Category_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID <= 0) 
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

	public I_M_Product getM_Product() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_Product.Table_Name);
        I_M_Product result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Product)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Product_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID <= 0) 
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
		if (Std_AddAmt == null)
			throw new IllegalArgumentException ("Std_AddAmt is mandatory.");
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
		if (Std_Base == null) throw new IllegalArgumentException ("Std_Base is mandatory");
		if (Std_Base.equals("L") || Std_Base.equals("S") || Std_Base.equals("X") || Std_Base.equals("F")); else throw new IllegalArgumentException ("Std_Base Invalid value - " + Std_Base + " - Reference_ID=194 - L - S - X - F");
		if (Std_Base.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			Std_Base = Std_Base.substring(0, 1);
		}
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
		if (Std_Discount == null)
			throw new IllegalArgumentException ("Std_Discount is mandatory.");
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
		if (Std_MaxAmt == null)
			throw new IllegalArgumentException ("Std_MaxAmt is mandatory.");
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
		if (Std_MinAmt == null)
			throw new IllegalArgumentException ("Std_MinAmt is mandatory.");
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
		if (Std_Rounding == null) throw new IllegalArgumentException ("Std_Rounding is mandatory");
		if (Std_Rounding.equals("0") || Std_Rounding.equals("N") || Std_Rounding.equals("Q") || Std_Rounding.equals("D") || Std_Rounding.equals("5") || Std_Rounding.equals("T") || Std_Rounding.equals("C") || Std_Rounding.equals("9")); else throw new IllegalArgumentException ("Std_Rounding Invalid value - " + Std_Rounding + " - Reference_ID=155 - 0 - N - Q - D - 5 - T - C - 9");
		if (Std_Rounding.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			Std_Rounding = Std_Rounding.substring(0, 1);
		}
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