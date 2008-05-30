/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_DiscountSchemaLine
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_M_DiscountSchemaLine 
{

    /** TableName=M_DiscountSchemaLine */
    public static final String Table_Name = "M_DiscountSchemaLine";

    /** AD_Table_ID=477 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

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

	public I_C_BPartner getC_BPartner() throws Exception;

    /** Column name C_ConversionType_ID */
    public static final String COLUMNNAME_C_ConversionType_ID = "C_ConversionType_ID";

	/** Set Currency Type.
	  * Currency Conversion Rate Type
	  */
	public void setC_ConversionType_ID (int C_ConversionType_ID);

	/** Get Currency Type.
	  * Currency Conversion Rate Type
	  */
	public int getC_ConversionType_ID();

	public I_C_ConversionType getC_ConversionType() throws Exception;

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

    /** Column name ConversionDate */
    public static final String COLUMNNAME_ConversionDate = "ConversionDate";

	/** Set Conversion Date.
	  * Date for selecting conversion rate
	  */
	public void setConversionDate (Timestamp ConversionDate);

	/** Get Conversion Date.
	  * Date for selecting conversion rate
	  */
	public Timestamp getConversionDate();

    /** Column name Group1 */
    public static final String COLUMNNAME_Group1 = "Group1";

	/** Set Group1	  */
	public void setGroup1 (String Group1);

	/** Get Group1	  */
	public String getGroup1();

    /** Column name Group2 */
    public static final String COLUMNNAME_Group2 = "Group2";

	/** Set Group2	  */
	public void setGroup2 (String Group2);

	/** Get Group2	  */
	public String getGroup2();

    /** Column name Limit_AddAmt */
    public static final String COLUMNNAME_Limit_AddAmt = "Limit_AddAmt";

	/** Set Limit price Surcharge Amount.
	  * Amount added to the converted/copied price before multiplying
	  */
	public void setLimit_AddAmt (BigDecimal Limit_AddAmt);

	/** Get Limit price Surcharge Amount.
	  * Amount added to the converted/copied price before multiplying
	  */
	public BigDecimal getLimit_AddAmt();

    /** Column name Limit_Base */
    public static final String COLUMNNAME_Limit_Base = "Limit_Base";

	/** Set Limit price Base.
	  * Base price for calculation of the new price
	  */
	public void setLimit_Base (String Limit_Base);

	/** Get Limit price Base.
	  * Base price for calculation of the new price
	  */
	public String getLimit_Base();

    /** Column name Limit_Discount */
    public static final String COLUMNNAME_Limit_Discount = "Limit_Discount";

	/** Set Limit price Discount %.
	  * Discount in percent to be subtracted from base, if negative it will be added to base price
	  */
	public void setLimit_Discount (BigDecimal Limit_Discount);

	/** Get Limit price Discount %.
	  * Discount in percent to be subtracted from base, if negative it will be added to base price
	  */
	public BigDecimal getLimit_Discount();

    /** Column name Limit_Fixed */
    public static final String COLUMNNAME_Limit_Fixed = "Limit_Fixed";

	/** Set Fixed Limit Price.
	  * Fixed Limit Price (not calculated)
	  */
	public void setLimit_Fixed (BigDecimal Limit_Fixed);

	/** Get Fixed Limit Price.
	  * Fixed Limit Price (not calculated)
	  */
	public BigDecimal getLimit_Fixed();

    /** Column name Limit_MaxAmt */
    public static final String COLUMNNAME_Limit_MaxAmt = "Limit_MaxAmt";

	/** Set Limit price max Margin.
	  * Maximum difference to original limit price;
 ignored if zero
	  */
	public void setLimit_MaxAmt (BigDecimal Limit_MaxAmt);

	/** Get Limit price max Margin.
	  * Maximum difference to original limit price;
 ignored if zero
	  */
	public BigDecimal getLimit_MaxAmt();

    /** Column name Limit_MinAmt */
    public static final String COLUMNNAME_Limit_MinAmt = "Limit_MinAmt";

	/** Set Limit price min Margin.
	  * Minimum difference to original limit price;
 ignored if zero
	  */
	public void setLimit_MinAmt (BigDecimal Limit_MinAmt);

	/** Get Limit price min Margin.
	  * Minimum difference to original limit price;
 ignored if zero
	  */
	public BigDecimal getLimit_MinAmt();

    /** Column name Limit_Rounding */
    public static final String COLUMNNAME_Limit_Rounding = "Limit_Rounding";

	/** Set Limit price Rounding.
	  * Rounding of the final result
	  */
	public void setLimit_Rounding (String Limit_Rounding);

	/** Get Limit price Rounding.
	  * Rounding of the final result
	  */
	public String getLimit_Rounding();

    /** Column name List_AddAmt */
    public static final String COLUMNNAME_List_AddAmt = "List_AddAmt";

	/** Set List price Surcharge Amount.
	  * List Price Surcharge Amount
	  */
	public void setList_AddAmt (BigDecimal List_AddAmt);

	/** Get List price Surcharge Amount.
	  * List Price Surcharge Amount
	  */
	public BigDecimal getList_AddAmt();

    /** Column name List_Base */
    public static final String COLUMNNAME_List_Base = "List_Base";

	/** Set List price Base.
	  * Price used as the basis for price list calculations
	  */
	public void setList_Base (String List_Base);

	/** Get List price Base.
	  * Price used as the basis for price list calculations
	  */
	public String getList_Base();

    /** Column name List_Discount */
    public static final String COLUMNNAME_List_Discount = "List_Discount";

	/** Set List price Discount %.
	  * Discount from list price as a percentage
	  */
	public void setList_Discount (BigDecimal List_Discount);

	/** Get List price Discount %.
	  * Discount from list price as a percentage
	  */
	public BigDecimal getList_Discount();

    /** Column name List_Fixed */
    public static final String COLUMNNAME_List_Fixed = "List_Fixed";

	/** Set Fixed List Price.
	  * Fixes List Price (not calculated)
	  */
	public void setList_Fixed (BigDecimal List_Fixed);

	/** Get Fixed List Price.
	  * Fixes List Price (not calculated)
	  */
	public BigDecimal getList_Fixed();

    /** Column name List_MaxAmt */
    public static final String COLUMNNAME_List_MaxAmt = "List_MaxAmt";

	/** Set List price max Margin.
	  * Maximum margin for a product
	  */
	public void setList_MaxAmt (BigDecimal List_MaxAmt);

	/** Get List price max Margin.
	  * Maximum margin for a product
	  */
	public BigDecimal getList_MaxAmt();

    /** Column name List_MinAmt */
    public static final String COLUMNNAME_List_MinAmt = "List_MinAmt";

	/** Set List price min Margin.
	  * Minimum margin for a product
	  */
	public void setList_MinAmt (BigDecimal List_MinAmt);

	/** Get List price min Margin.
	  * Minimum margin for a product
	  */
	public BigDecimal getList_MinAmt();

    /** Column name List_Rounding */
    public static final String COLUMNNAME_List_Rounding = "List_Rounding";

	/** Set List price Rounding.
	  * Rounding rule for final list price
	  */
	public void setList_Rounding (String List_Rounding);

	/** Get List price Rounding.
	  * Rounding rule for final list price
	  */
	public String getList_Rounding();

    /** Column name M_DiscountSchemaLine_ID */
    public static final String COLUMNNAME_M_DiscountSchemaLine_ID = "M_DiscountSchemaLine_ID";

	/** Set Discount Pricelist.
	  * Line of the pricelist trade discount schema
	  */
	public void setM_DiscountSchemaLine_ID (int M_DiscountSchemaLine_ID);

	/** Get Discount Pricelist.
	  * Line of the pricelist trade discount schema
	  */
	public int getM_DiscountSchemaLine_ID();

    /** Column name M_DiscountSchema_ID */
    public static final String COLUMNNAME_M_DiscountSchema_ID = "M_DiscountSchema_ID";

	/** Set Discount Schema.
	  * Schema to calculate the trade discount percentage
	  */
	public void setM_DiscountSchema_ID (int M_DiscountSchema_ID);

	/** Get Discount Schema.
	  * Schema to calculate the trade discount percentage
	  */
	public int getM_DiscountSchema_ID();

	public I_M_DiscountSchema getM_DiscountSchema() throws Exception;

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

	public I_M_Product_Category getM_Product_Category() throws Exception;

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

	public I_M_Product getM_Product() throws Exception;

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

    /** Column name Std_AddAmt */
    public static final String COLUMNNAME_Std_AddAmt = "Std_AddAmt";

	/** Set Standard price Surcharge Amount.
	  * Amount added to a price as a surcharge
	  */
	public void setStd_AddAmt (BigDecimal Std_AddAmt);

	/** Get Standard price Surcharge Amount.
	  * Amount added to a price as a surcharge
	  */
	public BigDecimal getStd_AddAmt();

    /** Column name Std_Base */
    public static final String COLUMNNAME_Std_Base = "Std_Base";

	/** Set Standard price Base.
	  * Base price for calculating new standard price
	  */
	public void setStd_Base (String Std_Base);

	/** Get Standard price Base.
	  * Base price for calculating new standard price
	  */
	public String getStd_Base();

    /** Column name Std_Discount */
    public static final String COLUMNNAME_Std_Discount = "Std_Discount";

	/** Set Standard price Discount %.
	  * Discount percentage to subtract from base price
	  */
	public void setStd_Discount (BigDecimal Std_Discount);

	/** Get Standard price Discount %.
	  * Discount percentage to subtract from base price
	  */
	public BigDecimal getStd_Discount();

    /** Column name Std_Fixed */
    public static final String COLUMNNAME_Std_Fixed = "Std_Fixed";

	/** Set Fixed Standard Price.
	  * Fixed Standard Price (not calculated)
	  */
	public void setStd_Fixed (BigDecimal Std_Fixed);

	/** Get Fixed Standard Price.
	  * Fixed Standard Price (not calculated)
	  */
	public BigDecimal getStd_Fixed();

    /** Column name Std_MaxAmt */
    public static final String COLUMNNAME_Std_MaxAmt = "Std_MaxAmt";

	/** Set Standard max Margin.
	  * Maximum margin allowed for a product
	  */
	public void setStd_MaxAmt (BigDecimal Std_MaxAmt);

	/** Get Standard max Margin.
	  * Maximum margin allowed for a product
	  */
	public BigDecimal getStd_MaxAmt();

    /** Column name Std_MinAmt */
    public static final String COLUMNNAME_Std_MinAmt = "Std_MinAmt";

	/** Set Standard price min Margin.
	  * Minimum margin allowed for a product
	  */
	public void setStd_MinAmt (BigDecimal Std_MinAmt);

	/** Get Standard price min Margin.
	  * Minimum margin allowed for a product
	  */
	public BigDecimal getStd_MinAmt();

    /** Column name Std_Rounding */
    public static final String COLUMNNAME_Std_Rounding = "Std_Rounding";

	/** Set Standard price Rounding.
	  * Rounding rule for calculated price
	  */
	public void setStd_Rounding (String Std_Rounding);

	/** Get Standard price Rounding.
	  * Rounding rule for calculated price
	  */
	public String getStd_Rounding();
}
