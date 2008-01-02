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
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_Product_Acct
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_M_Product_Acct 
{

    /** TableName=M_Product_Acct */
    public static final String Table_Name = "M_Product_Acct";

    /** AD_Table_ID=273 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name C_AcctSchema_ID */
    public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

	/** Set Accounting Schema.
	  * Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID);

	/** Get Accounting Schema.
	  * Rules for accounting
	  */
	public int getC_AcctSchema_ID();

	public I_C_AcctSchema getC_AcctSchema() throws Exception;

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

    /** Column name P_Asset_Acct */
    public static final String COLUMNNAME_P_Asset_Acct = "P_Asset_Acct";

	/** Set Product Asset.
	  * Account for Product Asset (Inventory)
	  */
	public void setP_Asset_Acct (int P_Asset_Acct);

	/** Get Product Asset.
	  * Account for Product Asset (Inventory)
	  */
	public int getP_Asset_Acct();

    /** Column name P_COGS_Acct */
    public static final String COLUMNNAME_P_COGS_Acct = "P_COGS_Acct";

	/** Set Product COGS.
	  * Account for Cost of Goods Sold
	  */
	public void setP_COGS_Acct (int P_COGS_Acct);

	/** Get Product COGS.
	  * Account for Cost of Goods Sold
	  */
	public int getP_COGS_Acct();

    /** Column name P_CostAdjustment_Acct */
    public static final String COLUMNNAME_P_CostAdjustment_Acct = "P_CostAdjustment_Acct";

	/** Set Cost Adjustment.
	  * Product Cost Adjustment Account
	  */
	public void setP_CostAdjustment_Acct (int P_CostAdjustment_Acct);

	/** Get Cost Adjustment.
	  * Product Cost Adjustment Account
	  */
	public int getP_CostAdjustment_Acct();

    /** Column name P_Expense_Acct */
    public static final String COLUMNNAME_P_Expense_Acct = "P_Expense_Acct";

	/** Set Product Expense.
	  * Account for Product Expense
	  */
	public void setP_Expense_Acct (int P_Expense_Acct);

	/** Get Product Expense.
	  * Account for Product Expense
	  */
	public int getP_Expense_Acct();

    /** Column name P_InventoryClearing_Acct */
    public static final String COLUMNNAME_P_InventoryClearing_Acct = "P_InventoryClearing_Acct";

	/** Set Inventory Clearing.
	  * Product Inventory Clearing Account
	  */
	public void setP_InventoryClearing_Acct (int P_InventoryClearing_Acct);

	/** Get Inventory Clearing.
	  * Product Inventory Clearing Account
	  */
	public int getP_InventoryClearing_Acct();

    /** Column name P_InvoicePriceVariance_Acct */
    public static final String COLUMNNAME_P_InvoicePriceVariance_Acct = "P_InvoicePriceVariance_Acct";

	/** Set Invoice Price Variance.
	  * Difference between Costs and Invoice Price (IPV)
	  */
	public void setP_InvoicePriceVariance_Acct (int P_InvoicePriceVariance_Acct);

	/** Get Invoice Price Variance.
	  * Difference between Costs and Invoice Price (IPV)
	  */
	public int getP_InvoicePriceVariance_Acct();

    /** Column name P_PurchasePriceVariance_Acct */
    public static final String COLUMNNAME_P_PurchasePriceVariance_Acct = "P_PurchasePriceVariance_Acct";

	/** Set Purchase Price Variance.
	  * Difference between Standard Cost and Purchase Price (PPV)
	  */
	public void setP_PurchasePriceVariance_Acct (int P_PurchasePriceVariance_Acct);

	/** Get Purchase Price Variance.
	  * Difference between Standard Cost and Purchase Price (PPV)
	  */
	public int getP_PurchasePriceVariance_Acct();

    /** Column name P_Revenue_Acct */
    public static final String COLUMNNAME_P_Revenue_Acct = "P_Revenue_Acct";

	/** Set Product Revenue.
	  * Account for Product Revenue (Sales Account)
	  */
	public void setP_Revenue_Acct (int P_Revenue_Acct);

	/** Get Product Revenue.
	  * Account for Product Revenue (Sales Account)
	  */
	public int getP_Revenue_Acct();

    /** Column name P_TradeDiscountGrant_Acct */
    public static final String COLUMNNAME_P_TradeDiscountGrant_Acct = "P_TradeDiscountGrant_Acct";

	/** Set Trade Discount Granted.
	  * Trade Discount Granted Account
	  */
	public void setP_TradeDiscountGrant_Acct (int P_TradeDiscountGrant_Acct);

	/** Get Trade Discount Granted.
	  * Trade Discount Granted Account
	  */
	public int getP_TradeDiscountGrant_Acct();

    /** Column name P_TradeDiscountRec_Acct */
    public static final String COLUMNNAME_P_TradeDiscountRec_Acct = "P_TradeDiscountRec_Acct";

	/** Set Trade Discount Received.
	  * Trade Discount Receivable Account
	  */
	public void setP_TradeDiscountRec_Acct (int P_TradeDiscountRec_Acct);

	/** Get Trade Discount Received.
	  * Trade Discount Receivable Account
	  */
	public int getP_TradeDiscountRec_Acct();
}
