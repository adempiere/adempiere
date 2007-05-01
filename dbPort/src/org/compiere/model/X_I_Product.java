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
/** Generated Model for I_Product
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_I_Product extends PO
{
/** Standard Constructor
@param ctx context
@param I_Product_ID id
@param trxName transaction
*/
public X_I_Product (Properties ctx, int I_Product_ID, String trxName)
{
super (ctx, I_Product_ID, trxName);
/** if (I_Product_ID == 0)
{
setI_IsImported (false);
setI_Product_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_I_Product (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=532 */
public static final int Table_ID=MTable.getTable_ID("I_Product");

/** TableName=I_Product */
public static final String Table_Name="I_Product";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"I_Product");

protected BigDecimal accessLevel = BigDecimal.valueOf(2);
/** AccessLevel
@return 2 - Client 
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
StringBuffer sb = new StringBuffer ("X_I_Product[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Business Partner Key.
@param BPartner_Value The Key of the Business Partner */
public void setBPartner_Value (String BPartner_Value)
{
if (BPartner_Value != null && BPartner_Value.length() > 40)
{
log.warning("Length > 40 - truncated");
BPartner_Value = BPartner_Value.substring(0,39);
}
set_Value ("BPartner_Value", BPartner_Value);
}
/** Get Business Partner Key.
@return The Key of the Business Partner */
public String getBPartner_Value() 
{
return (String)get_Value("BPartner_Value");
}
/** Column name BPartner_Value */
public static final String COLUMNNAME_BPartner_Value = "BPartner_Value";
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_Value ("C_BPartner_ID", null);
 else 
set_Value ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_ID */
public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";
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
/** Column name C_UOM_ID */
public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";
/** Set Classification.
@param Classification Classification for grouping */
public void setClassification (String Classification)
{
if (Classification != null && Classification.length() > 1)
{
log.warning("Length > 1 - truncated");
Classification = Classification.substring(0,0);
}
set_Value ("Classification", Classification);
}
/** Get Classification.
@return Classification for grouping */
public String getClassification() 
{
return (String)get_Value("Classification");
}
/** Column name Classification */
public static final String COLUMNNAME_Classification = "Classification";
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
/** Column name CostPerOrder */
public static final String COLUMNNAME_CostPerOrder = "CostPerOrder";
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
/** Column name DeliveryTime_Promised */
public static final String COLUMNNAME_DeliveryTime_Promised = "DeliveryTime_Promised";
/** Set Description.
@param Description Optional short description of the record */
public void setDescription (String Description)
{
if (Description != null && Description.length() > 255)
{
log.warning("Length > 255 - truncated");
Description = Description.substring(0,254);
}
set_Value ("Description", Description);
}
/** Get Description.
@return Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set Description URL.
@param DescriptionURL URL for the description */
public void setDescriptionURL (String DescriptionURL)
{
if (DescriptionURL != null && DescriptionURL.length() > 120)
{
log.warning("Length > 120 - truncated");
DescriptionURL = DescriptionURL.substring(0,119);
}
set_Value ("DescriptionURL", DescriptionURL);
}
/** Get Description URL.
@return URL for the description */
public String getDescriptionURL() 
{
return (String)get_Value("DescriptionURL");
}
/** Column name DescriptionURL */
public static final String COLUMNNAME_DescriptionURL = "DescriptionURL";
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
/** Column name Discontinued */
public static final String COLUMNNAME_Discontinued = "Discontinued";
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
/** Column name DiscontinuedBy */
public static final String COLUMNNAME_DiscontinuedBy = "DiscontinuedBy";
/** Set Document Note.
@param DocumentNote Additional information for a Document */
public void setDocumentNote (String DocumentNote)
{
if (DocumentNote != null && DocumentNote.length() > 2000)
{
log.warning("Length > 2000 - truncated");
DocumentNote = DocumentNote.substring(0,1999);
}
set_Value ("DocumentNote", DocumentNote);
}
/** Get Document Note.
@return Additional information for a Document */
public String getDocumentNote() 
{
return (String)get_Value("DocumentNote");
}
/** Column name DocumentNote */
public static final String COLUMNNAME_DocumentNote = "DocumentNote";
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Column name Help */
public static final String COLUMNNAME_Help = "Help";
/** Set ISO Currency Code.
@param ISO_Code Three letter ISO 4217 Code of the Currency */
public void setISO_Code (String ISO_Code)
{
if (ISO_Code != null && ISO_Code.length() > 3)
{
log.warning("Length > 3 - truncated");
ISO_Code = ISO_Code.substring(0,2);
}
set_Value ("ISO_Code", ISO_Code);
}
/** Get ISO Currency Code.
@return Three letter ISO 4217 Code of the Currency */
public String getISO_Code() 
{
return (String)get_Value("ISO_Code");
}
/** Column name ISO_Code */
public static final String COLUMNNAME_ISO_Code = "ISO_Code";
/** Set Import Error Message.
@param I_ErrorMsg Messages generated from import process */
public void setI_ErrorMsg (String I_ErrorMsg)
{
if (I_ErrorMsg != null && I_ErrorMsg.length() > 2000)
{
log.warning("Length > 2000 - truncated");
I_ErrorMsg = I_ErrorMsg.substring(0,1999);
}
set_Value ("I_ErrorMsg", I_ErrorMsg);
}
/** Get Import Error Message.
@return Messages generated from import process */
public String getI_ErrorMsg() 
{
return (String)get_Value("I_ErrorMsg");
}
/** Column name I_ErrorMsg */
public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";
/** Set Imported.
@param I_IsImported Has this import been processed */
public void setI_IsImported (boolean I_IsImported)
{
set_Value ("I_IsImported", Boolean.valueOf(I_IsImported));
}
/** Get Imported.
@return Has this import been processed */
public boolean isI_IsImported() 
{
Object oo = get_Value("I_IsImported");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name I_IsImported */
public static final String COLUMNNAME_I_IsImported = "I_IsImported";
/** Set Import Product.
@param I_Product_ID Import Item or Service */
public void setI_Product_ID (int I_Product_ID)
{
if (I_Product_ID < 1) throw new IllegalArgumentException ("I_Product_ID is mandatory.");
set_ValueNoCheck ("I_Product_ID", Integer.valueOf(I_Product_ID));
}
/** Get Import Product.
@return Import Item or Service */
public int getI_Product_ID() 
{
Integer ii = (Integer)get_Value("I_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name I_Product_ID */
public static final String COLUMNNAME_I_Product_ID = "I_Product_ID";
/** Set Image URL.
@param ImageURL URL of  image */
public void setImageURL (String ImageURL)
{
if (ImageURL != null && ImageURL.length() > 120)
{
log.warning("Length > 120 - truncated");
ImageURL = ImageURL.substring(0,119);
}
set_Value ("ImageURL", ImageURL);
}
/** Get Image URL.
@return URL of  image */
public String getImageURL() 
{
return (String)get_Value("ImageURL");
}
/** Column name ImageURL */
public static final String COLUMNNAME_ImageURL = "ImageURL";
/** Set Product Category.
@param M_Product_Category_ID Category of a Product */
public void setM_Product_Category_ID (int M_Product_Category_ID)
{
if (M_Product_Category_ID <= 0) set_Value ("M_Product_Category_ID", null);
 else 
set_Value ("M_Product_Category_ID", Integer.valueOf(M_Product_Category_ID));
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
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_Value ("M_Product_ID", null);
 else 
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
/** Column name Manufacturer */
public static final String COLUMNNAME_Manufacturer = "Manufacturer";
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name != null && Name.length() > 60)
{
log.warning("Length > 60 - truncated");
Name = Name.substring(0,59);
}
set_Value ("Name", Name);
}
/** Get Name.
@return Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
}
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Minimum Order Qty.
@param Order_Min Minimum order quantity in UOM */
public void setOrder_Min (int Order_Min)
{
set_Value ("Order_Min", Integer.valueOf(Order_Min));
}
/** Get Minimum Order Qty.
@return Minimum order quantity in UOM */
public int getOrder_Min() 
{
Integer ii = (Integer)get_Value("Order_Min");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Order_Min */
public static final String COLUMNNAME_Order_Min = "Order_Min";
/** Set Order Pack Qty.
@param Order_Pack Package order size in UOM (e.g. order set of 5 units) */
public void setOrder_Pack (int Order_Pack)
{
set_Value ("Order_Pack", Integer.valueOf(Order_Pack));
}
/** Get Order Pack Qty.
@return Package order size in UOM (e.g. order set of 5 units) */
public int getOrder_Pack() 
{
Integer ii = (Integer)get_Value("Order_Pack");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Order_Pack */
public static final String COLUMNNAME_Order_Pack = "Order_Pack";
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
/** Column name PriceEffective */
public static final String COLUMNNAME_PriceEffective = "PriceEffective";
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
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", Boolean.valueOf(Processed));
}
/** Get Processed.
@return The document has been processed */
public boolean isProcessed() 
{
Object oo = get_Value("Processed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Processed */
public static final String COLUMNNAME_Processed = "Processed";
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
/** Set Product Category Key.
@param ProductCategory_Value Product Category Key */
public void setProductCategory_Value (String ProductCategory_Value)
{
if (ProductCategory_Value != null && ProductCategory_Value.length() > 40)
{
log.warning("Length > 40 - truncated");
ProductCategory_Value = ProductCategory_Value.substring(0,39);
}
set_Value ("ProductCategory_Value", ProductCategory_Value);
}
/** Get Product Category Key.
@return Product Category Key */
public String getProductCategory_Value() 
{
return (String)get_Value("ProductCategory_Value");
}
/** Column name ProductCategory_Value */
public static final String COLUMNNAME_ProductCategory_Value = "ProductCategory_Value";

/** ProductType AD_Reference_ID=270 */
public static final int PRODUCTTYPE_AD_Reference_ID=270;
/** Expense type = E */
public static final String PRODUCTTYPE_ExpenseType = "E";
/** Item = I */
public static final String PRODUCTTYPE_Item = "I";
/** Online = O */
public static final String PRODUCTTYPE_Online = "O";
/** Resource = R */
public static final String PRODUCTTYPE_Resource = "R";
/** Service = S */
public static final String PRODUCTTYPE_Service = "S";
/** Set Product Type.
@param ProductType Type of product */
public void setProductType (String ProductType)
{
if (ProductType == null || ProductType.equals("E") || ProductType.equals("I") || ProductType.equals("O") || ProductType.equals("R") || ProductType.equals("S"));
 else throw new IllegalArgumentException ("ProductType Invalid value - " + ProductType + " - Reference_ID=270 - E - I - O - R - S");
if (ProductType != null && ProductType.length() > 1)
{
log.warning("Length > 1 - truncated");
ProductType = ProductType.substring(0,0);
}
set_Value ("ProductType", ProductType);
}
/** Get Product Type.
@return Type of product */
public String getProductType() 
{
return (String)get_Value("ProductType");
}
/** Column name ProductType */
public static final String COLUMNNAME_ProductType = "ProductType";
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
/** Column name RoyaltyAmt */
public static final String COLUMNNAME_RoyaltyAmt = "RoyaltyAmt";
/** Set SKU.
@param SKU Stock Keeping Unit */
public void setSKU (String SKU)
{
if (SKU != null && SKU.length() > 30)
{
log.warning("Length > 30 - truncated");
SKU = SKU.substring(0,29);
}
set_Value ("SKU", SKU);
}
/** Get SKU.
@return Stock Keeping Unit */
public String getSKU() 
{
return (String)get_Value("SKU");
}
/** Column name SKU */
public static final String COLUMNNAME_SKU = "SKU";
/** Set Shelf Depth.
@param ShelfDepth Shelf depth required */
public void setShelfDepth (int ShelfDepth)
{
set_Value ("ShelfDepth", Integer.valueOf(ShelfDepth));
}
/** Get Shelf Depth.
@return Shelf depth required */
public int getShelfDepth() 
{
Integer ii = (Integer)get_Value("ShelfDepth");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name ShelfDepth */
public static final String COLUMNNAME_ShelfDepth = "ShelfDepth";
/** Set Shelf Height.
@param ShelfHeight Shelf height required */
public void setShelfHeight (int ShelfHeight)
{
set_Value ("ShelfHeight", Integer.valueOf(ShelfHeight));
}
/** Get Shelf Height.
@return Shelf height required */
public int getShelfHeight() 
{
Integer ii = (Integer)get_Value("ShelfHeight");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name ShelfHeight */
public static final String COLUMNNAME_ShelfHeight = "ShelfHeight";
/** Set Shelf Width.
@param ShelfWidth Shelf width required */
public void setShelfWidth (int ShelfWidth)
{
set_Value ("ShelfWidth", Integer.valueOf(ShelfWidth));
}
/** Get Shelf Width.
@return Shelf width required */
public int getShelfWidth() 
{
Integer ii = (Integer)get_Value("ShelfWidth");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name ShelfWidth */
public static final String COLUMNNAME_ShelfWidth = "ShelfWidth";
/** Set UPC/EAN.
@param UPC Bar Code (Universal Product Code or its superset European Article Number) */
public void setUPC (String UPC)
{
if (UPC != null && UPC.length() > 30)
{
log.warning("Length > 30 - truncated");
UPC = UPC.substring(0,29);
}
set_Value ("UPC", UPC);
}
/** Get UPC/EAN.
@return Bar Code (Universal Product Code or its superset European Article Number) */
public String getUPC() 
{
return (String)get_Value("UPC");
}
/** Column name UPC */
public static final String COLUMNNAME_UPC = "UPC";
/** Set Units Per Pallet.
@param UnitsPerPallet Units Per Pallet */
public void setUnitsPerPallet (int UnitsPerPallet)
{
set_Value ("UnitsPerPallet", Integer.valueOf(UnitsPerPallet));
}
/** Get Units Per Pallet.
@return Units Per Pallet */
public int getUnitsPerPallet() 
{
Integer ii = (Integer)get_Value("UnitsPerPallet");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name UnitsPerPallet */
public static final String COLUMNNAME_UnitsPerPallet = "UnitsPerPallet";
/** Set Search Key.
@param Value Search key for the record in the format required - must be unique */
public void setValue (String Value)
{
if (Value != null && Value.length() > 40)
{
log.warning("Length > 40 - truncated");
Value = Value.substring(0,39);
}
set_Value ("Value", Value);
}
/** Get Search Key.
@return Search key for the record in the format required - must be unique */
public String getValue() 
{
return (String)get_Value("Value");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getValue());
}
/** Column name Value */
public static final String COLUMNNAME_Value = "Value";
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
/** Column name VendorCategory */
public static final String COLUMNNAME_VendorCategory = "VendorCategory";
/** Set Partner Product Key.
@param VendorProductNo Product Key of the Business Partner */
public void setVendorProductNo (String VendorProductNo)
{
if (VendorProductNo != null && VendorProductNo.length() > 30)
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
/** Column name VendorProductNo */
public static final String COLUMNNAME_VendorProductNo = "VendorProductNo";
/** Set Volume.
@param Volume Volume of a product */
public void setVolume (int Volume)
{
set_Value ("Volume", Integer.valueOf(Volume));
}
/** Get Volume.
@return Volume of a product */
public int getVolume() 
{
Integer ii = (Integer)get_Value("Volume");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Volume */
public static final String COLUMNNAME_Volume = "Volume";
/** Set Weight.
@param Weight Weight of a product */
public void setWeight (int Weight)
{
set_Value ("Weight", Integer.valueOf(Weight));
}
/** Get Weight.
@return Weight of a product */
public int getWeight() 
{
Integer ii = (Integer)get_Value("Weight");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Weight */
public static final String COLUMNNAME_Weight = "Weight";
/** Set UOM Code.
@param X12DE355 UOM EDI X12 Code */
public void setX12DE355 (String X12DE355)
{
if (X12DE355 != null && X12DE355.length() > 4)
{
log.warning("Length > 4 - truncated");
X12DE355 = X12DE355.substring(0,3);
}
set_Value ("X12DE355", X12DE355);
}
/** Get UOM Code.
@return UOM EDI X12 Code */
public String getX12DE355() 
{
return (String)get_Value("X12DE355");
}
/** Column name X12DE355 */
public static final String COLUMNNAME_X12DE355 = "X12DE355";
}
