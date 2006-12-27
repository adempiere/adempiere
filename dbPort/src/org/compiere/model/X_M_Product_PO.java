/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
/** Generated Model for M_Product_PO
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_M_Product_PO extends PO
{
/** Standard Constructor
@param ctx context
@param M_Product_PO_ID id
@param trxName transaction
*/
public X_M_Product_PO (Properties ctx, int M_Product_PO_ID, String trxName)
{
super (ctx, M_Product_PO_ID, trxName);
/** if (M_Product_PO_ID == 0)
{
setC_BPartner_ID (0);	// 0
setIsCurrentVendor (true);	// Y
setM_Product_ID (0);	// @M_Product_ID@
setVendorProductNo (null);	// @Value@
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_Product_PO (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=210 */
public static final int Table_ID=MTable.getTable_ID("M_Product_PO");

/** TableName=M_Product_PO */
public static final String Table_Name="M_Product_PO";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_Product_PO");

protected BigDecimal accessLevel = new BigDecimal(3);
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
StringBuffer sb = new StringBuffer ("X_M_Product_PO[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID < 1) throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
set_ValueNoCheck ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set UOM.
@param C_UOM_ID Unit of Measure */
public void setC_UOM_ID (int C_UOM_ID)
{
if (C_UOM_ID <= 0) set_Value ("C_UOM_ID", null);
 else 
set_Value ("C_UOM_ID", Integer.valueOf(C_UOM_ID));
}
/** Get UOM.
@return Unit of Measure */
public int getC_UOM_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Cost per Order.
@param CostPerOrder Fixed Cost Per Order */
public void setCostPerOrder (BigDecimal CostPerOrder)
{
set_Value ("CostPerOrder", CostPerOrder);
}
/** Get Cost per Order.
@return Fixed Cost Per Order */
public BigDecimal getCostPerOrder() 
{
BigDecimal bd = (BigDecimal)get_Value("CostPerOrder");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Actual Delivery Time.
@param DeliveryTime_Actual Actual days between order and delivery */
public void setDeliveryTime_Actual (int DeliveryTime_Actual)
{
set_Value ("DeliveryTime_Actual", Integer.valueOf(DeliveryTime_Actual));
}
/** Get Actual Delivery Time.
@return Actual days between order and delivery */
public int getDeliveryTime_Actual() 
{
Integer ii = (Integer)get_Value("DeliveryTime_Actual");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Promised Delivery Time.
@param DeliveryTime_Promised Promised days between order and delivery */
public void setDeliveryTime_Promised (int DeliveryTime_Promised)
{
set_Value ("DeliveryTime_Promised", Integer.valueOf(DeliveryTime_Promised));
}
/** Get Promised Delivery Time.
@return Promised days between order and delivery */
public int getDeliveryTime_Promised() 
{
Integer ii = (Integer)get_Value("DeliveryTime_Promised");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Discontinued.
@param Discontinued This product is no longer available */
public void setDiscontinued (boolean Discontinued)
{
set_Value ("Discontinued", Boolean.valueOf(Discontinued));
}
/** Get Discontinued.
@return This product is no longer available */
public boolean isDiscontinued() 
{
Object oo = get_Value("Discontinued");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Discontinued by.
@param DiscontinuedBy Discontinued By */
public void setDiscontinuedBy (Timestamp DiscontinuedBy)
{
set_Value ("DiscontinuedBy", DiscontinuedBy);
}
/** Get Discontinued by.
@return Discontinued By */
public Timestamp getDiscontinuedBy() 
{
return (Timestamp)get_Value("DiscontinuedBy");
}
/** Set Current vendor.
@param IsCurrentVendor Use this Vendor for pricing and stock replenishment */
public void setIsCurrentVendor (boolean IsCurrentVendor)
{
set_Value ("IsCurrentVendor", Boolean.valueOf(IsCurrentVendor));
}
/** Get Current vendor.
@return Use this Vendor for pricing and stock replenishment */
public boolean isCurrentVendor() 
{
Object oo = get_Value("IsCurrentVendor");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
set_ValueNoCheck ("M_Product_ID", Integer.valueOf(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Manufacturer.
@param Manufacturer Manufacturer of the Product */
public void setManufacturer (String Manufacturer)
{
if (Manufacturer != null && Manufacturer.length() > 30)
{
log.warning("Length > 30 - truncated");
Manufacturer = Manufacturer.substring(0,29);
}
set_Value ("Manufacturer", Manufacturer);
}
/** Get Manufacturer.
@return Manufacturer of the Product */
public String getManufacturer() 
{
return (String)get_Value("Manufacturer");
}
/** Set Minimum Order Qty.
@param Order_Min Minimum order quantity in UOM */
public void setOrder_Min (BigDecimal Order_Min)
{
set_Value ("Order_Min", Order_Min);
}
/** Get Minimum Order Qty.
@return Minimum order quantity in UOM */
public BigDecimal getOrder_Min() 
{
BigDecimal bd = (BigDecimal)get_Value("Order_Min");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Order Pack Qty.
@param Order_Pack Package order size in UOM (e.g. order set of 5 units) */
public void setOrder_Pack (BigDecimal Order_Pack)
{
set_Value ("Order_Pack", Order_Pack);
}
/** Get Order Pack Qty.
@return Package order size in UOM (e.g. order set of 5 units) */
public BigDecimal getOrder_Pack() 
{
BigDecimal bd = (BigDecimal)get_Value("Order_Pack");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Price effective.
@param PriceEffective Effective Date of Price */
public void setPriceEffective (Timestamp PriceEffective)
{
set_Value ("PriceEffective", PriceEffective);
}
/** Get Price effective.
@return Effective Date of Price */
public Timestamp getPriceEffective() 
{
return (Timestamp)get_Value("PriceEffective");
}
/** Set Last Invoice Price.
@param PriceLastInv Price of the last invoice for the product */
public void setPriceLastInv (BigDecimal PriceLastInv)
{
set_ValueNoCheck ("PriceLastInv", PriceLastInv);
}
/** Get Last Invoice Price.
@return Price of the last invoice for the product */
public BigDecimal getPriceLastInv() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceLastInv");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Last PO Price.
@param PriceLastPO Price of the last purchase order for the product */
public void setPriceLastPO (BigDecimal PriceLastPO)
{
set_ValueNoCheck ("PriceLastPO", PriceLastPO);
}
/** Get Last PO Price.
@return Price of the last purchase order for the product */
public BigDecimal getPriceLastPO() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceLastPO");
if (bd == null) return Env.ZERO;
return bd;
}
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
/** Set Quality Rating.
@param QualityRating Method for rating vendors */
public void setQualityRating (int QualityRating)
{
set_Value ("QualityRating", Integer.valueOf(QualityRating));
}
/** Get Quality Rating.
@return Method for rating vendors */
public int getQualityRating() 
{
Integer ii = (Integer)get_Value("QualityRating");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Royalty Amount.
@param RoyaltyAmt (Included) Amount for copyright, etc. */
public void setRoyaltyAmt (BigDecimal RoyaltyAmt)
{
set_Value ("RoyaltyAmt", RoyaltyAmt);
}
/** Get Royalty Amount.
@return (Included) Amount for copyright, etc. */
public BigDecimal getRoyaltyAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("RoyaltyAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set UPC/EAN.
@param UPC Bar Code (Universal Product Code or its superset European Article Number) */
public void setUPC (String UPC)
{
if (UPC != null && UPC.length() > 20)
{
log.warning("Length > 20 - truncated");
UPC = UPC.substring(0,19);
}
set_Value ("UPC", UPC);
}
/** Get UPC/EAN.
@return Bar Code (Universal Product Code or its superset European Article Number) */
public String getUPC() 
{
return (String)get_Value("UPC");
}
/** Set Partner Category.
@param VendorCategory Product Category of the Business Partner */
public void setVendorCategory (String VendorCategory)
{
if (VendorCategory != null && VendorCategory.length() > 30)
{
log.warning("Length > 30 - truncated");
VendorCategory = VendorCategory.substring(0,29);
}
set_Value ("VendorCategory", VendorCategory);
}
/** Get Partner Category.
@return Product Category of the Business Partner */
public String getVendorCategory() 
{
return (String)get_Value("VendorCategory");
}
/** Set Partner Product Key.
@param VendorProductNo Product Key of the Business Partner */
public void setVendorProductNo (String VendorProductNo)
{
if (VendorProductNo == null) throw new IllegalArgumentException ("VendorProductNo is mandatory.");
if (VendorProductNo.length() > 30)
{
log.warning("Length > 30 - truncated");
VendorProductNo = VendorProductNo.substring(0,29);
}
set_Value ("VendorProductNo", VendorProductNo);
}
/** Get Partner Product Key.
@return Product Key of the Business Partner */
public String getVendorProductNo() 
{
return (String)get_Value("VendorProductNo");
}
}
