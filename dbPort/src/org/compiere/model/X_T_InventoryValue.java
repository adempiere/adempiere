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
/** Generated Model for T_InventoryValue
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_T_InventoryValue extends PO
{
/** Standard Constructor
@param ctx context
@param T_InventoryValue_ID id
@param trxName transaction
*/
public X_T_InventoryValue (Properties ctx, int T_InventoryValue_ID, String trxName)
{
super (ctx, T_InventoryValue_ID, trxName);
/** if (T_InventoryValue_ID == 0)
{
setAD_PInstance_ID (0);
setM_AttributeSetInstance_ID (0);
setM_Product_ID (0);
setM_Warehouse_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_T_InventoryValue (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=478 */
public static final int Table_ID=MTable.getTable_ID("T_InventoryValue");

/** TableName=T_InventoryValue */
public static final String Table_Name="T_InventoryValue";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"T_InventoryValue");

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
StringBuffer sb = new StringBuffer ("X_T_InventoryValue[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Process Instance.
@param AD_PInstance_ID Instance of the process */
public void setAD_PInstance_ID (int AD_PInstance_ID)
{
if (AD_PInstance_ID < 1) throw new IllegalArgumentException ("AD_PInstance_ID is mandatory.");
set_ValueNoCheck ("AD_PInstance_ID", Integer.valueOf(AD_PInstance_ID));
}
/** Get Process Instance.
@return Instance of the process */
public int getAD_PInstance_ID() 
{
Integer ii = (Integer)get_Value("AD_PInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_PInstance_ID */
public static final String COLUMNNAME_AD_PInstance_ID = "AD_PInstance_ID";
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID <= 0) set_Value ("C_Currency_ID", null);
 else 
set_Value ("C_Currency_ID", Integer.valueOf(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Currency_ID */
public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";
/** Set Cost.
@param Cost Cost information */
public void setCost (BigDecimal Cost)
{
set_Value ("Cost", Cost);
}
/** Get Cost.
@return Cost information */
public BigDecimal getCost() 
{
BigDecimal bd = (BigDecimal)get_Value("Cost");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Cost */
public static final String COLUMNNAME_Cost = "Cost";
/** Set Cost Value.
@param CostAmt Value with Cost */
public void setCostAmt (BigDecimal CostAmt)
{
set_Value ("CostAmt", CostAmt);
}
/** Get Cost Value.
@return Value with Cost */
public BigDecimal getCostAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("CostAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name CostAmt */
public static final String COLUMNNAME_CostAmt = "CostAmt";
/** Set Standard Cost.
@param CostStandard Standard Costs */
public void setCostStandard (BigDecimal CostStandard)
{
set_Value ("CostStandard", CostStandard);
}
/** Get Standard Cost.
@return Standard Costs */
public BigDecimal getCostStandard() 
{
BigDecimal bd = (BigDecimal)get_Value("CostStandard");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name CostStandard */
public static final String COLUMNNAME_CostStandard = "CostStandard";
/** Set Standard Cost Value.
@param CostStandardAmt Value in Standard Costs */
public void setCostStandardAmt (BigDecimal CostStandardAmt)
{
set_Value ("CostStandardAmt", CostStandardAmt);
}
/** Get Standard Cost Value.
@return Value in Standard Costs */
public BigDecimal getCostStandardAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("CostStandardAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name CostStandardAmt */
public static final String COLUMNNAME_CostStandardAmt = "CostStandardAmt";
/** Set Valuation Date.
@param DateValue Date of valuation */
public void setDateValue (Timestamp DateValue)
{
set_Value ("DateValue", DateValue);
}
/** Get Valuation Date.
@return Date of valuation */
public Timestamp getDateValue() 
{
return (Timestamp)get_Value("DateValue");
}
/** Column name DateValue */
public static final String COLUMNNAME_DateValue = "DateValue";
/** Set Attribute Set Instance.
@param M_AttributeSetInstance_ID Product Attribute Set Instance */
public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
{
if (M_AttributeSetInstance_ID < 0) throw new IllegalArgumentException ("M_AttributeSetInstance_ID is mandatory.");
set_Value ("M_AttributeSetInstance_ID", Integer.valueOf(M_AttributeSetInstance_ID));
}
/** Get Attribute Set Instance.
@return Product Attribute Set Instance */
public int getM_AttributeSetInstance_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSetInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_AttributeSetInstance_ID */
public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";
/** Set Cost Element.
@param M_CostElement_ID Product Cost Element */
public void setM_CostElement_ID (int M_CostElement_ID)
{
if (M_CostElement_ID <= 0) set_Value ("M_CostElement_ID", null);
 else 
set_Value ("M_CostElement_ID", Integer.valueOf(M_CostElement_ID));
}
/** Get Cost Element.
@return Product Cost Element */
public int getM_CostElement_ID() 
{
Integer ii = (Integer)get_Value("M_CostElement_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_CostElement_ID */
public static final String COLUMNNAME_M_CostElement_ID = "M_CostElement_ID";
/** Set Price List Version.
@param M_PriceList_Version_ID Identifies a unique instance of a Price List */
public void setM_PriceList_Version_ID (int M_PriceList_Version_ID)
{
if (M_PriceList_Version_ID <= 0) set_Value ("M_PriceList_Version_ID", null);
 else 
set_Value ("M_PriceList_Version_ID", Integer.valueOf(M_PriceList_Version_ID));
}
/** Get Price List Version.
@return Identifies a unique instance of a Price List */
public int getM_PriceList_Version_ID() 
{
Integer ii = (Integer)get_Value("M_PriceList_Version_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_PriceList_Version_ID */
public static final String COLUMNNAME_M_PriceList_Version_ID = "M_PriceList_Version_ID";
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
set_Value ("M_Product_ID", Integer.valueOf(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Product_ID */
public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";
/** Set Warehouse.
@param M_Warehouse_ID Storage Warehouse and Service Point */
public void setM_Warehouse_ID (int M_Warehouse_ID)
{
if (M_Warehouse_ID < 1) throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
set_Value ("M_Warehouse_ID", Integer.valueOf(M_Warehouse_ID));
}
/** Get Warehouse.
@return Storage Warehouse and Service Point */
public int getM_Warehouse_ID() 
{
Integer ii = (Integer)get_Value("M_Warehouse_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Warehouse_ID */
public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";
/** Set Limit Price.
@param PriceLimit Lowest price for a product */
public void setPriceLimit (BigDecimal PriceLimit)
{
set_Value ("PriceLimit", PriceLimit);
}
/** Get Limit Price.
@return Lowest price for a product */
public BigDecimal getPriceLimit() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceLimit");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PriceLimit */
public static final String COLUMNNAME_PriceLimit = "PriceLimit";
/** Set Limit price Value.
@param PriceLimitAmt Value with limit price */
public void setPriceLimitAmt (BigDecimal PriceLimitAmt)
{
set_Value ("PriceLimitAmt", PriceLimitAmt);
}
/** Get Limit price Value.
@return Value with limit price */
public BigDecimal getPriceLimitAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceLimitAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PriceLimitAmt */
public static final String COLUMNNAME_PriceLimitAmt = "PriceLimitAmt";
/** Set List Price.
@param PriceList List Price */
public void setPriceList (BigDecimal PriceList)
{
set_Value ("PriceList", PriceList);
}
/** Get List Price.
@return List Price */
public BigDecimal getPriceList() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceList");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PriceList */
public static final String COLUMNNAME_PriceList = "PriceList";
/** Set List price Value.
@param PriceListAmt Valuation with List Price */
public void setPriceListAmt (BigDecimal PriceListAmt)
{
set_Value ("PriceListAmt", PriceListAmt);
}
/** Get List price Value.
@return Valuation with List Price */
public BigDecimal getPriceListAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceListAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PriceListAmt */
public static final String COLUMNNAME_PriceListAmt = "PriceListAmt";
/** Set PO Price.
@param PricePO Price based on a purchase order */
public void setPricePO (BigDecimal PricePO)
{
set_Value ("PricePO", PricePO);
}
/** Get PO Price.
@return Price based on a purchase order */
public BigDecimal getPricePO() 
{
BigDecimal bd = (BigDecimal)get_Value("PricePO");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PricePO */
public static final String COLUMNNAME_PricePO = "PricePO";
/** Set PO Price Value.
@param PricePOAmt Valuation with PO Price */
public void setPricePOAmt (BigDecimal PricePOAmt)
{
set_Value ("PricePOAmt", PricePOAmt);
}
/** Get PO Price Value.
@return Valuation with PO Price */
public BigDecimal getPricePOAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("PricePOAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PricePOAmt */
public static final String COLUMNNAME_PricePOAmt = "PricePOAmt";
/** Set Standard Price.
@param PriceStd Standard Price */
public void setPriceStd (BigDecimal PriceStd)
{
set_Value ("PriceStd", PriceStd);
}
/** Get Standard Price.
@return Standard Price */
public BigDecimal getPriceStd() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceStd");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PriceStd */
public static final String COLUMNNAME_PriceStd = "PriceStd";
/** Set Std Price Value.
@param PriceStdAmt Valuation with standard price */
public void setPriceStdAmt (BigDecimal PriceStdAmt)
{
set_Value ("PriceStdAmt", PriceStdAmt);
}
/** Get Std Price Value.
@return Valuation with standard price */
public BigDecimal getPriceStdAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceStdAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PriceStdAmt */
public static final String COLUMNNAME_PriceStdAmt = "PriceStdAmt";
/** Set On Hand Quantity.
@param QtyOnHand On Hand Quantity */
public void setQtyOnHand (BigDecimal QtyOnHand)
{
set_Value ("QtyOnHand", QtyOnHand);
}
/** Get On Hand Quantity.
@return On Hand Quantity */
public BigDecimal getQtyOnHand() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyOnHand");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtyOnHand */
public static final String COLUMNNAME_QtyOnHand = "QtyOnHand";
}
