/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

/** Generated Model - DO NOT CHANGE */
import java.util.*;
import java.sql.*;
import java.math.*;
import org.compiere.util.*;
/** Generated Model for M_Product_Category_Acct
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_M_Product_Category_Acct extends PO
{
/** Standard Constructor
@param ctx context
@param M_Product_Category_Acct_ID id
@param trxName transaction
*/
public X_M_Product_Category_Acct (Properties ctx, int M_Product_Category_Acct_ID, String trxName)
{
super (ctx, M_Product_Category_Acct_ID, trxName);
/** if (M_Product_Category_Acct_ID == 0)
{
setC_AcctSchema_ID (0);
setM_Product_Category_ID (0);
setP_Asset_Acct (0);
setP_COGS_Acct (0);
setP_CostAdjustment_Acct (0);
setP_Expense_Acct (0);
setP_InventoryClearing_Acct (0);
setP_InvoicePriceVariance_Acct (0);
setP_PurchasePriceVariance_Acct (0);
setP_Revenue_Acct (0);
setP_TradeDiscountGrant_Acct (0);
setP_TradeDiscountRec_Acct (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_Product_Category_Acct (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=401 */
public static final int Table_ID=MTable.getTable_ID("M_Product_Category_Acct");

/** TableName=M_Product_Category_Acct */
public static final String Table_Name="M_Product_Category_Acct";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_Product_Category_Acct");

protected BigDecimal accessLevel = BigDecimal.valueOf(3);
/** AccessLevel
@return 3 - Client - Org 
*/
protected int get_AccessLevel()
{
return accessLevel.intValue();
}
/** Load Meta Data
@param ctx context
@return PO Info
*/
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
/** Info
@return info
*/
public String toString()
{
StringBuffer sb = new StringBuffer ("X_M_Product_Category_Acct[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Accounting Schema.
@param C_AcctSchema_ID Rules for accounting */
public void setC_AcctSchema_ID (int C_AcctSchema_ID)
{
if (C_AcctSchema_ID < 1) throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
set_ValueNoCheck ("C_AcctSchema_ID", Integer.valueOf(C_AcctSchema_ID));
}
/** Get Accounting Schema.
@return Rules for accounting */
public int getC_AcctSchema_ID() 
{
Integer ii = (Integer)get_Value("C_AcctSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_AcctSchema_ID */
public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

/** CostingLevel AD_Reference_ID=355 */
public static final int COSTINGLEVEL_AD_Reference_ID=355;
/** Batch/Lot = B */
public static final String COSTINGLEVEL_BatchLot = "B";
/** Client = C */
public static final String COSTINGLEVEL_Client = "C";
/** Organization = O */
public static final String COSTINGLEVEL_Organization = "O";
/** Set Costing Level.
@param CostingLevel The lowest level to accumulate Costing Information */
public void setCostingLevel (String CostingLevel)
{
if (CostingLevel == null || CostingLevel.equals("B") || CostingLevel.equals("C") || CostingLevel.equals("O"));
 else throw new IllegalArgumentException ("CostingLevel Invalid value - " + CostingLevel + " - Reference_ID=355 - B - C - O");
if (CostingLevel != null && CostingLevel.length() > 1)
{
log.warning("Length > 1 - truncated");
CostingLevel = CostingLevel.substring(0,0);
}
set_Value ("CostingLevel", CostingLevel);
}
/** Get Costing Level.
@return The lowest level to accumulate Costing Information */
public String getCostingLevel() 
{
return (String)get_Value("CostingLevel");
}
/** Column name CostingLevel */
public static final String COLUMNNAME_CostingLevel = "CostingLevel";

/** CostingMethod AD_Reference_ID=122 */
public static final int COSTINGMETHOD_AD_Reference_ID=122;
/** Average PO = A */
public static final String COSTINGMETHOD_AveragePO = "A";
/** Fifo = F */
public static final String COSTINGMETHOD_Fifo = "F";
/** Average Invoice = I */
public static final String COSTINGMETHOD_AverageInvoice = "I";
/** Lifo = L */
public static final String COSTINGMETHOD_Lifo = "L";
/** Standard Costing = S */
public static final String COSTINGMETHOD_StandardCosting = "S";
/** User Defined = U */
public static final String COSTINGMETHOD_UserDefined = "U";
/** Last Invoice = i */
public static final String COSTINGMETHOD_LastInvoice = "i";
/** Last PO Price = p */
public static final String COSTINGMETHOD_LastPOPrice = "p";
/** _ = x */
public static final String COSTINGMETHOD__ = "x";
/** Set Costing Method.
@param CostingMethod Indicates how Costs will be calculated */
public void setCostingMethod (String CostingMethod)
{
if (CostingMethod == null || CostingMethod.equals("A") || CostingMethod.equals("F") || CostingMethod.equals("I") || CostingMethod.equals("L") || CostingMethod.equals("S") || CostingMethod.equals("U") || CostingMethod.equals("i") || CostingMethod.equals("p") || CostingMethod.equals("x"));
 else throw new IllegalArgumentException ("CostingMethod Invalid value - " + CostingMethod + " - Reference_ID=122 - A - F - I - L - S - U - i - p - x");
if (CostingMethod != null && CostingMethod.length() > 1)
{
log.warning("Length > 1 - truncated");
CostingMethod = CostingMethod.substring(0,0);
}
set_Value ("CostingMethod", CostingMethod);
}
/** Get Costing Method.
@return Indicates how Costs will be calculated */
public String getCostingMethod() 
{
return (String)get_Value("CostingMethod");
}
/** Column name CostingMethod */
public static final String COLUMNNAME_CostingMethod = "CostingMethod";
/** Set Product Category.
@param M_Product_Category_ID Category of a Product */
public void setM_Product_Category_ID (int M_Product_Category_ID)
{
if (M_Product_Category_ID < 1) throw new IllegalArgumentException ("M_Product_Category_ID is mandatory.");
set_ValueNoCheck ("M_Product_Category_ID", Integer.valueOf(M_Product_Category_ID));
}
/** Get Product Category.
@return Category of a Product */
public int getM_Product_Category_ID() 
{
Integer ii = (Integer)get_Value("M_Product_Category_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Product_Category_ID */
public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";
/** Set Product Asset.
@param P_Asset_Acct Account for Product Asset (Inventory) */
public void setP_Asset_Acct (int P_Asset_Acct)
{
set_Value ("P_Asset_Acct", Integer.valueOf(P_Asset_Acct));
}
/** Get Product Asset.
@return Account for Product Asset (Inventory) */
public int getP_Asset_Acct() 
{
Integer ii = (Integer)get_Value("P_Asset_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name P_Asset_Acct */
public static final String COLUMNNAME_P_Asset_Acct = "P_Asset_Acct";
/** Set Product COGS.
@param P_COGS_Acct Account for Cost of Goods Sold */
public void setP_COGS_Acct (int P_COGS_Acct)
{
set_Value ("P_COGS_Acct", Integer.valueOf(P_COGS_Acct));
}
/** Get Product COGS.
@return Account for Cost of Goods Sold */
public int getP_COGS_Acct() 
{
Integer ii = (Integer)get_Value("P_COGS_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name P_COGS_Acct */
public static final String COLUMNNAME_P_COGS_Acct = "P_COGS_Acct";
/** Set Cost Adjustment.
@param P_CostAdjustment_Acct Product Cost Adjustment Account */
public void setP_CostAdjustment_Acct (int P_CostAdjustment_Acct)
{
set_Value ("P_CostAdjustment_Acct", Integer.valueOf(P_CostAdjustment_Acct));
}
/** Get Cost Adjustment.
@return Product Cost Adjustment Account */
public int getP_CostAdjustment_Acct() 
{
Integer ii = (Integer)get_Value("P_CostAdjustment_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name P_CostAdjustment_Acct */
public static final String COLUMNNAME_P_CostAdjustment_Acct = "P_CostAdjustment_Acct";
/** Set Product Expense.
@param P_Expense_Acct Account for Product Expense */
public void setP_Expense_Acct (int P_Expense_Acct)
{
set_Value ("P_Expense_Acct", Integer.valueOf(P_Expense_Acct));
}
/** Get Product Expense.
@return Account for Product Expense */
public int getP_Expense_Acct() 
{
Integer ii = (Integer)get_Value("P_Expense_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name P_Expense_Acct */
public static final String COLUMNNAME_P_Expense_Acct = "P_Expense_Acct";
/** Set Inventory Clearing.
@param P_InventoryClearing_Acct Product Inventory Clearing Account */
public void setP_InventoryClearing_Acct (int P_InventoryClearing_Acct)
{
set_Value ("P_InventoryClearing_Acct", Integer.valueOf(P_InventoryClearing_Acct));
}
/** Get Inventory Clearing.
@return Product Inventory Clearing Account */
public int getP_InventoryClearing_Acct() 
{
Integer ii = (Integer)get_Value("P_InventoryClearing_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name P_InventoryClearing_Acct */
public static final String COLUMNNAME_P_InventoryClearing_Acct = "P_InventoryClearing_Acct";
/** Set Invoice Price Variance.
@param P_InvoicePriceVariance_Acct Difference between Costs and Invoice Price (IPV) */
public void setP_InvoicePriceVariance_Acct (int P_InvoicePriceVariance_Acct)
{
set_Value ("P_InvoicePriceVariance_Acct", Integer.valueOf(P_InvoicePriceVariance_Acct));
}
/** Get Invoice Price Variance.
@return Difference between Costs and Invoice Price (IPV) */
public int getP_InvoicePriceVariance_Acct() 
{
Integer ii = (Integer)get_Value("P_InvoicePriceVariance_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name P_InvoicePriceVariance_Acct */
public static final String COLUMNNAME_P_InvoicePriceVariance_Acct = "P_InvoicePriceVariance_Acct";
/** Set Purchase Price Variance.
@param P_PurchasePriceVariance_Acct Difference between Standard Cost and Purchase Price (PPV) */
public void setP_PurchasePriceVariance_Acct (int P_PurchasePriceVariance_Acct)
{
set_Value ("P_PurchasePriceVariance_Acct", Integer.valueOf(P_PurchasePriceVariance_Acct));
}
/** Get Purchase Price Variance.
@return Difference between Standard Cost and Purchase Price (PPV) */
public int getP_PurchasePriceVariance_Acct() 
{
Integer ii = (Integer)get_Value("P_PurchasePriceVariance_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name P_PurchasePriceVariance_Acct */
public static final String COLUMNNAME_P_PurchasePriceVariance_Acct = "P_PurchasePriceVariance_Acct";
/** Set Product Revenue.
@param P_Revenue_Acct Account for Product Revenue (Sales Account) */
public void setP_Revenue_Acct (int P_Revenue_Acct)
{
set_Value ("P_Revenue_Acct", Integer.valueOf(P_Revenue_Acct));
}
/** Get Product Revenue.
@return Account for Product Revenue (Sales Account) */
public int getP_Revenue_Acct() 
{
Integer ii = (Integer)get_Value("P_Revenue_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name P_Revenue_Acct */
public static final String COLUMNNAME_P_Revenue_Acct = "P_Revenue_Acct";
/** Set Trade Discount Granted.
@param P_TradeDiscountGrant_Acct Trade Discount Granted Account */
public void setP_TradeDiscountGrant_Acct (int P_TradeDiscountGrant_Acct)
{
set_Value ("P_TradeDiscountGrant_Acct", Integer.valueOf(P_TradeDiscountGrant_Acct));
}
/** Get Trade Discount Granted.
@return Trade Discount Granted Account */
public int getP_TradeDiscountGrant_Acct() 
{
Integer ii = (Integer)get_Value("P_TradeDiscountGrant_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name P_TradeDiscountGrant_Acct */
public static final String COLUMNNAME_P_TradeDiscountGrant_Acct = "P_TradeDiscountGrant_Acct";
/** Set Trade Discount Received.
@param P_TradeDiscountRec_Acct Trade Discount Receivable Account */
public void setP_TradeDiscountRec_Acct (int P_TradeDiscountRec_Acct)
{
set_Value ("P_TradeDiscountRec_Acct", Integer.valueOf(P_TradeDiscountRec_Acct));
}
/** Get Trade Discount Received.
@return Trade Discount Receivable Account */
public int getP_TradeDiscountRec_Acct() 
{
Integer ii = (Integer)get_Value("P_TradeDiscountRec_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name P_TradeDiscountRec_Acct */
public static final String COLUMNNAME_P_TradeDiscountRec_Acct = "P_TradeDiscountRec_Acct";
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", Boolean.valueOf(Processing));
}
/** Get Process Now.
@return Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Processing */
public static final String COLUMNNAME_Processing = "Processing";
}
