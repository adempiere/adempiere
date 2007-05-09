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
/** Generated Model for M_Product
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_M_Product extends PO
{
/** Standard Constructor
@param ctx context
@param M_Product_ID id
@param trxName transaction
*/
public X_M_Product (Properties ctx, int M_Product_ID, String trxName)
{
super (ctx, M_Product_ID, trxName);
/** if (M_Product_ID == 0)
{
setC_TaxCategory_ID (0);
setC_UOM_ID (0);
setIsBOM (false);	// N
setIsDropShip (false);
setIsExcludeAutoDelivery (false);	// N
setIsInvoicePrintDetails (false);
setIsPickListPrintDetails (false);
setIsPurchased (true);	// Y
setIsSelfService (true);	// Y
setIsSold (true);	// Y
setIsStocked (true);	// Y
setIsSummary (false);
setIsVerified (false);	// N
setIsWebStoreFeatured (false);
setM_AttributeSetInstance_ID (0);
setM_Product_Category_ID (0);
setM_Product_ID (0);
setName (null);
setProductType (null);	// I
setValue (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_Product (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=208 */
public static final int Table_ID=MTable.getTable_ID("M_Product");

/** TableName=M_Product */
public static final String Table_Name="M_Product";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_Product");

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
StringBuffer sb = new StringBuffer ("X_M_Product[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Revenue Recognition.
@param C_RevenueRecognition_ID Method for recording revenue */
public void setC_RevenueRecognition_ID (int C_RevenueRecognition_ID)
{
if (C_RevenueRecognition_ID <= 0) set_Value ("C_RevenueRecognition_ID", null);
 else 
set_Value ("C_RevenueRecognition_ID", Integer.valueOf(C_RevenueRecognition_ID));
}
/** Get Revenue Recognition.
@return Method for recording revenue */
public int getC_RevenueRecognition_ID() 
{
Integer ii = (Integer)get_Value("C_RevenueRecognition_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_RevenueRecognition_ID */
public static final String COLUMNNAME_C_RevenueRecognition_ID = "C_RevenueRecognition_ID";
/** Set Subscription Type.
@param C_SubscriptionType_ID Type of subscription */
public void setC_SubscriptionType_ID (int C_SubscriptionType_ID)
{
if (C_SubscriptionType_ID <= 0) set_Value ("C_SubscriptionType_ID", null);
 else 
set_Value ("C_SubscriptionType_ID", Integer.valueOf(C_SubscriptionType_ID));
}
/** Get Subscription Type.
@return Type of subscription */
public int getC_SubscriptionType_ID() 
{
Integer ii = (Integer)get_Value("C_SubscriptionType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_SubscriptionType_ID */
public static final String COLUMNNAME_C_SubscriptionType_ID = "C_SubscriptionType_ID";
/** Set Tax Category.
@param C_TaxCategory_ID Tax Category */
public void setC_TaxCategory_ID (int C_TaxCategory_ID)
{
if (C_TaxCategory_ID < 1) throw new IllegalArgumentException ("C_TaxCategory_ID is mandatory.");
set_Value ("C_TaxCategory_ID", Integer.valueOf(C_TaxCategory_ID));
}
/** Get Tax Category.
@return Tax Category */
public int getC_TaxCategory_ID() 
{
Integer ii = (Integer)get_Value("C_TaxCategory_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_TaxCategory_ID */
public static final String COLUMNNAME_C_TaxCategory_ID = "C_TaxCategory_ID";
/** Set UOM.
@param C_UOM_ID Unit of Measure */
public void setC_UOM_ID (int C_UOM_ID)
{
if (C_UOM_ID < 1) throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
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
/** Set Guarantee Days.
@param GuaranteeDays Number of days the product is guaranteed or available */
public void setGuaranteeDays (int GuaranteeDays)
{
set_Value ("GuaranteeDays", Integer.valueOf(GuaranteeDays));
}
/** Get Guarantee Days.
@return Number of days the product is guaranteed or available */
public int getGuaranteeDays() 
{
Integer ii = (Integer)get_Value("GuaranteeDays");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name GuaranteeDays */
public static final String COLUMNNAME_GuaranteeDays = "GuaranteeDays";
/** Set Min Guarantee Days.
@param GuaranteeDaysMin Minumum number of guarantee days */
public void setGuaranteeDaysMin (int GuaranteeDaysMin)
{
set_Value ("GuaranteeDaysMin", Integer.valueOf(GuaranteeDaysMin));
}
/** Get Min Guarantee Days.
@return Minumum number of guarantee days */
public int getGuaranteeDaysMin() 
{
Integer ii = (Integer)get_Value("GuaranteeDaysMin");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name GuaranteeDaysMin */
public static final String COLUMNNAME_GuaranteeDaysMin = "GuaranteeDaysMin";
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
/** Set Bill of Materials.
@param IsBOM Bill of Materials */
public void setIsBOM (boolean IsBOM)
{
set_Value ("IsBOM", Boolean.valueOf(IsBOM));
}
/** Get Bill of Materials.
@return Bill of Materials */
public boolean isBOM() 
{
Object oo = get_Value("IsBOM");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsBOM */
public static final String COLUMNNAME_IsBOM = "IsBOM";
/** Set Drop Shipment.
@param IsDropShip Drop Shipments are sent from the Vendor directly to the Customer */
public void setIsDropShip (boolean IsDropShip)
{
set_Value ("IsDropShip", Boolean.valueOf(IsDropShip));
}
/** Get Drop Shipment.
@return Drop Shipments are sent from the Vendor directly to the Customer */
public boolean isDropShip() 
{
Object oo = get_Value("IsDropShip");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsDropShip */
public static final String COLUMNNAME_IsDropShip = "IsDropShip";
/** Set Exclude Auto Delivery.
@param IsExcludeAutoDelivery Exclude from automatic Delivery */
public void setIsExcludeAutoDelivery (boolean IsExcludeAutoDelivery)
{
set_Value ("IsExcludeAutoDelivery", Boolean.valueOf(IsExcludeAutoDelivery));
}
/** Get Exclude Auto Delivery.
@return Exclude from automatic Delivery */
public boolean isExcludeAutoDelivery() 
{
Object oo = get_Value("IsExcludeAutoDelivery");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsExcludeAutoDelivery */
public static final String COLUMNNAME_IsExcludeAutoDelivery = "IsExcludeAutoDelivery";
/** Set Print detail records on invoice .
@param IsInvoicePrintDetails Print detail BOM elements on the invoice */
public void setIsInvoicePrintDetails (boolean IsInvoicePrintDetails)
{
set_Value ("IsInvoicePrintDetails", Boolean.valueOf(IsInvoicePrintDetails));
}
/** Get Print detail records on invoice .
@return Print detail BOM elements on the invoice */
public boolean isInvoicePrintDetails() 
{
Object oo = get_Value("IsInvoicePrintDetails");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsInvoicePrintDetails */
public static final String COLUMNNAME_IsInvoicePrintDetails = "IsInvoicePrintDetails";
/** Set Print detail records on pick list.
@param IsPickListPrintDetails Print detail BOM elements on the pick list */
public void setIsPickListPrintDetails (boolean IsPickListPrintDetails)
{
set_Value ("IsPickListPrintDetails", Boolean.valueOf(IsPickListPrintDetails));
}
/** Get Print detail records on pick list.
@return Print detail BOM elements on the pick list */
public boolean isPickListPrintDetails() 
{
Object oo = get_Value("IsPickListPrintDetails");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPickListPrintDetails */
public static final String COLUMNNAME_IsPickListPrintDetails = "IsPickListPrintDetails";
/** Set Purchased.
@param IsPurchased Organization purchases this product */
public void setIsPurchased (boolean IsPurchased)
{
set_Value ("IsPurchased", Boolean.valueOf(IsPurchased));
}
/** Get Purchased.
@return Organization purchases this product */
public boolean isPurchased() 
{
Object oo = get_Value("IsPurchased");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPurchased */
public static final String COLUMNNAME_IsPurchased = "IsPurchased";
/** Set Self-Service.
@param IsSelfService This is a Self-Service entry or this entry can be changed via Self-Service */
public void setIsSelfService (boolean IsSelfService)
{
set_Value ("IsSelfService", Boolean.valueOf(IsSelfService));
}
/** Get Self-Service.
@return This is a Self-Service entry or this entry can be changed via Self-Service */
public boolean isSelfService() 
{
Object oo = get_Value("IsSelfService");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSelfService */
public static final String COLUMNNAME_IsSelfService = "IsSelfService";
/** Set Sold.
@param IsSold Organization sells this product */
public void setIsSold (boolean IsSold)
{
set_Value ("IsSold", Boolean.valueOf(IsSold));
}
/** Get Sold.
@return Organization sells this product */
public boolean isSold() 
{
Object oo = get_Value("IsSold");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSold */
public static final String COLUMNNAME_IsSold = "IsSold";
/** Set Stocked.
@param IsStocked Organization stocks this product */
public void setIsStocked (boolean IsStocked)
{
set_Value ("IsStocked", Boolean.valueOf(IsStocked));
}
/** Get Stocked.
@return Organization stocks this product */
public boolean isStocked() 
{
Object oo = get_Value("IsStocked");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsStocked */
public static final String COLUMNNAME_IsStocked = "IsStocked";
/** Set Summary Level.
@param IsSummary This is a summary entity */
public void setIsSummary (boolean IsSummary)
{
set_Value ("IsSummary", Boolean.valueOf(IsSummary));
}
/** Get Summary Level.
@return This is a summary entity */
public boolean isSummary() 
{
Object oo = get_Value("IsSummary");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSummary */
public static final String COLUMNNAME_IsSummary = "IsSummary";
/** Set Verified.
@param IsVerified The BOM configuration has been verified */
public void setIsVerified (boolean IsVerified)
{
set_ValueNoCheck ("IsVerified", Boolean.valueOf(IsVerified));
}
/** Get Verified.
@return The BOM configuration has been verified */
public boolean isVerified() 
{
Object oo = get_Value("IsVerified");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsVerified */
public static final String COLUMNNAME_IsVerified = "IsVerified";
/** Set Featured in Web Store.
@param IsWebStoreFeatured If selected, the product is displayed in the inital or any empy search */
public void setIsWebStoreFeatured (boolean IsWebStoreFeatured)
{
set_Value ("IsWebStoreFeatured", Boolean.valueOf(IsWebStoreFeatured));
}
/** Get Featured in Web Store.
@return If selected, the product is displayed in the inital or any empy search */
public boolean isWebStoreFeatured() 
{
Object oo = get_Value("IsWebStoreFeatured");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsWebStoreFeatured */
public static final String COLUMNNAME_IsWebStoreFeatured = "IsWebStoreFeatured";
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
/** Set Attribute Set.
@param M_AttributeSet_ID Product Attribute Set */
public void setM_AttributeSet_ID (int M_AttributeSet_ID)
{
if (M_AttributeSet_ID <= 0) set_Value ("M_AttributeSet_ID", null);
 else 
set_Value ("M_AttributeSet_ID", Integer.valueOf(M_AttributeSet_ID));
}
/** Get Attribute Set.
@return Product Attribute Set */
public int getM_AttributeSet_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSet_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_AttributeSet_ID */
public static final String COLUMNNAME_M_AttributeSet_ID = "M_AttributeSet_ID";
/** Set Freight Category.
@param M_FreightCategory_ID Category of the Freight */
public void setM_FreightCategory_ID (int M_FreightCategory_ID)
{
if (M_FreightCategory_ID <= 0) set_Value ("M_FreightCategory_ID", null);
 else 
set_Value ("M_FreightCategory_ID", Integer.valueOf(M_FreightCategory_ID));
}
/** Get Freight Category.
@return Category of the Freight */
public int getM_FreightCategory_ID() 
{
Integer ii = (Integer)get_Value("M_FreightCategory_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_FreightCategory_ID */
public static final String COLUMNNAME_M_FreightCategory_ID = "M_FreightCategory_ID";
/** Set Locator.
@param M_Locator_ID Warehouse Locator */
public void setM_Locator_ID (int M_Locator_ID)
{
if (M_Locator_ID <= 0) set_Value ("M_Locator_ID", null);
 else 
set_Value ("M_Locator_ID", Integer.valueOf(M_Locator_ID));
}
/** Get Locator.
@return Warehouse Locator */
public int getM_Locator_ID() 
{
Integer ii = (Integer)get_Value("M_Locator_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Locator_ID */
public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

/** M_Product_Category_ID AD_Reference_ID=163 */
public static final int M_PRODUCT_CATEGORY_ID_AD_Reference_ID=163;
/** Set Product Category.
@param M_Product_Category_ID Category of a Product */
public void setM_Product_Category_ID (int M_Product_Category_ID)
{
if (M_Product_Category_ID < 1) throw new IllegalArgumentException ("M_Product_Category_ID is mandatory.");
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
/** Column name M_Product_ID */
public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 60)
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getName());
}
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
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
if (ProductType == null) throw new IllegalArgumentException ("ProductType is mandatory");
if (ProductType.equals("E") || ProductType.equals("I") || ProductType.equals("O") || ProductType.equals("R") || ProductType.equals("S"));
 else throw new IllegalArgumentException ("ProductType Invalid value - " + ProductType + " - Reference_ID=270 - E - I - O - R - S");
if (ProductType.length() > 1)
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
/** Set Mail Template.
@param R_MailText_ID Text templates for mailings */
public void setR_MailText_ID (int R_MailText_ID)
{
if (R_MailText_ID <= 0) set_Value ("R_MailText_ID", null);
 else 
set_Value ("R_MailText_ID", Integer.valueOf(R_MailText_ID));
}
/** Get Mail Template.
@return Text templates for mailings */
public int getR_MailText_ID() 
{
Integer ii = (Integer)get_Value("R_MailText_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_MailText_ID */
public static final String COLUMNNAME_R_MailText_ID = "R_MailText_ID";
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
/** Set Expense Type.
@param S_ExpenseType_ID Expense report type */
public void setS_ExpenseType_ID (int S_ExpenseType_ID)
{
if (S_ExpenseType_ID <= 0) set_ValueNoCheck ("S_ExpenseType_ID", null);
 else 
set_ValueNoCheck ("S_ExpenseType_ID", Integer.valueOf(S_ExpenseType_ID));
}
/** Get Expense Type.
@return Expense report type */
public int getS_ExpenseType_ID() 
{
Integer ii = (Integer)get_Value("S_ExpenseType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name S_ExpenseType_ID */
public static final String COLUMNNAME_S_ExpenseType_ID = "S_ExpenseType_ID";
/** Set Resource.
@param S_Resource_ID Resource */
public void setS_Resource_ID (int S_Resource_ID)
{
if (S_Resource_ID <= 0) set_ValueNoCheck ("S_Resource_ID", null);
 else 
set_ValueNoCheck ("S_Resource_ID", Integer.valueOf(S_Resource_ID));
}
/** Get Resource.
@return Resource */
public int getS_Resource_ID() 
{
Integer ii = (Integer)get_Value("S_Resource_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name S_Resource_ID */
public static final String COLUMNNAME_S_Resource_ID = "S_Resource_ID";

/** SalesRep_ID AD_Reference_ID=190 */
public static final int SALESREP_ID_AD_Reference_ID=190;
/** Set Sales Representative.
@param SalesRep_ID Sales Representative or Company Agent */
public void setSalesRep_ID (int SalesRep_ID)
{
if (SalesRep_ID <= 0) set_Value ("SalesRep_ID", null);
 else 
set_Value ("SalesRep_ID", Integer.valueOf(SalesRep_ID));
}
/** Get Sales Representative.
@return Sales Representative or Company Agent */
public int getSalesRep_ID() 
{
Integer ii = (Integer)get_Value("SalesRep_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name SalesRep_ID */
public static final String COLUMNNAME_SalesRep_ID = "SalesRep_ID";
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
if (Value == null) throw new IllegalArgumentException ("Value is mandatory.");
if (Value.length() > 40)
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
/** Column name Value */
public static final String COLUMNNAME_Value = "Value";
/** Set Version No.
@param VersionNo Version Number */
public void setVersionNo (String VersionNo)
{
if (VersionNo != null && VersionNo.length() > 20)
{
log.warning("Length > 20 - truncated");
VersionNo = VersionNo.substring(0,19);
}
set_Value ("VersionNo", VersionNo);
}
/** Get Version No.
@return Version Number */
public String getVersionNo() 
{
return (String)get_Value("VersionNo");
}
/** Column name VersionNo */
public static final String COLUMNNAME_VersionNo = "VersionNo";
/** Set Volume.
@param Volume Volume of a product */
public void setVolume (BigDecimal Volume)
{
set_Value ("Volume", Volume);
}
/** Get Volume.
@return Volume of a product */
public BigDecimal getVolume() 
{
BigDecimal bd = (BigDecimal)get_Value("Volume");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Volume */
public static final String COLUMNNAME_Volume = "Volume";
/** Set Weight.
@param Weight Weight of a product */
public void setWeight (BigDecimal Weight)
{
set_Value ("Weight", Weight);
}
/** Get Weight.
@return Weight of a product */
public BigDecimal getWeight() 
{
BigDecimal bd = (BigDecimal)get_Value("Weight");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Weight */
public static final String COLUMNNAME_Weight = "Weight";
}
