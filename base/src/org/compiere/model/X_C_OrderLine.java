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
/** Generated Model for C_OrderLine
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_C_OrderLine extends PO
{
/** Standard Constructor
@param ctx context
@param C_OrderLine_ID id
@param trxName transaction
*/
public X_C_OrderLine (Properties ctx, int C_OrderLine_ID, String trxName)
{
super (ctx, C_OrderLine_ID, trxName);
/** if (C_OrderLine_ID == 0)
{
setC_BPartner_Location_ID (0);	// @C_BPartner_Location_ID@
setC_Currency_ID (0);	// @C_Currency_ID@
setC_OrderLine_ID (0);
setC_Order_ID (0);
setC_Tax_ID (0);
setC_UOM_ID (0);	// @#C_UOM_ID@
setDateOrdered (new Timestamp(System.currentTimeMillis()));	// @DateOrdered@
setFreightAmt (Env.ZERO);
setIsDescription (false);	// N
setLine (0);	// @SQL=SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM C_OrderLine WHERE C_Order_ID=@C_Order_ID@
setLineNetAmt (Env.ZERO);
setM_AttributeSetInstance_ID (0);
setM_Warehouse_ID (0);	// @M_Warehouse_ID@
setPriceActual (Env.ZERO);
setPriceEntered (Env.ZERO);
setPriceLimit (Env.ZERO);
setPriceList (Env.ZERO);
setProcessed (false);
setQtyDelivered (Env.ZERO);
setQtyEntered (Env.ZERO);	// 1
setQtyInvoiced (Env.ZERO);
setQtyLostSales (Env.ZERO);
setQtyOrdered (Env.ZERO);	// 1
setQtyReserved (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_OrderLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=C_OrderLine */
public static final String Table_Name="C_OrderLine";

/** AD_Table_ID=260 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

protected BigDecimal accessLevel = BigDecimal.valueOf(1);
/** AccessLevel
@return 1 - Org 
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
StringBuffer sb = new StringBuffer ("X_C_OrderLine[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_OrgTrx_ID AD_Reference_ID=130 */
public static final int AD_ORGTRX_ID_AD_Reference_ID=130;
/** Set Trx Organization.
@param AD_OrgTrx_ID Performing or initiating organization */
public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
{
if (AD_OrgTrx_ID <= 0) set_Value ("AD_OrgTrx_ID", null);
 else 
set_Value ("AD_OrgTrx_ID", Integer.valueOf(AD_OrgTrx_ID));
}
/** Get Trx Organization.
@return Performing or initiating organization */
public int getAD_OrgTrx_ID() 
{
Integer ii = (Integer)get_Value("AD_OrgTrx_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_OrgTrx_ID */
public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";
/** Set Activity.
@param C_Activity_ID Business Activity */
public void setC_Activity_ID (int C_Activity_ID)
{
if (C_Activity_ID <= 0) set_Value ("C_Activity_ID", null);
 else 
set_Value ("C_Activity_ID", Integer.valueOf(C_Activity_ID));
}
/** Get Activity.
@return Business Activity */
public int getC_Activity_ID() 
{
Integer ii = (Integer)get_Value("C_Activity_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Activity_ID */
public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_ValueNoCheck ("C_BPartner_ID", null);
 else 
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
/** Column name C_BPartner_ID */
public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";
/** Set Partner Location.
@param C_BPartner_Location_ID Identifies the (ship to) address for this Business Partner */
public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
{
if (C_BPartner_Location_ID < 1) throw new IllegalArgumentException ("C_BPartner_Location_ID is mandatory.");
set_Value ("C_BPartner_Location_ID", Integer.valueOf(C_BPartner_Location_ID));
}
/** Get Partner Location.
@return Identifies the (ship to) address for this Business Partner */
public int getC_BPartner_Location_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_Location_ID */
public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";
/** Set Campaign.
@param C_Campaign_ID Marketing Campaign */
public void setC_Campaign_ID (int C_Campaign_ID)
{
if (C_Campaign_ID <= 0) set_Value ("C_Campaign_ID", null);
 else 
set_Value ("C_Campaign_ID", Integer.valueOf(C_Campaign_ID));
}
/** Get Campaign.
@return Marketing Campaign */
public int getC_Campaign_ID() 
{
Integer ii = (Integer)get_Value("C_Campaign_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Campaign_ID */
public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";
/** Set Charge.
@param C_Charge_ID Additional document charges */
public void setC_Charge_ID (int C_Charge_ID)
{
if (C_Charge_ID <= 0) set_Value ("C_Charge_ID", null);
 else 
set_Value ("C_Charge_ID", Integer.valueOf(C_Charge_ID));
}
/** Get Charge.
@return Additional document charges */
public int getC_Charge_ID() 
{
Integer ii = (Integer)get_Value("C_Charge_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Charge_ID */
public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID < 1) throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
set_ValueNoCheck ("C_Currency_ID", Integer.valueOf(C_Currency_ID));
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
/** Set Sales Order Line.
@param C_OrderLine_ID Sales Order Line */
public void setC_OrderLine_ID (int C_OrderLine_ID)
{
if (C_OrderLine_ID < 1) throw new IllegalArgumentException ("C_OrderLine_ID is mandatory.");
set_ValueNoCheck ("C_OrderLine_ID", Integer.valueOf(C_OrderLine_ID));
}
/** Get Sales Order Line.
@return Sales Order Line */
public int getC_OrderLine_ID() 
{
Integer ii = (Integer)get_Value("C_OrderLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_OrderLine_ID */
public static final String COLUMNNAME_C_OrderLine_ID = "C_OrderLine_ID";
/** Set Order.
@param C_Order_ID Order */
public void setC_Order_ID (int C_Order_ID)
{
if (C_Order_ID < 1) throw new IllegalArgumentException ("C_Order_ID is mandatory.");
set_ValueNoCheck ("C_Order_ID", Integer.valueOf(C_Order_ID));
}
/** Get Order.
@return Order */
public int getC_Order_ID() 
{
Integer ii = (Integer)get_Value("C_Order_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_Order_ID()));
}
/** Column name C_Order_ID */
public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";
/** Set Project Phase.
@param C_ProjectPhase_ID Phase of a Project */
public void setC_ProjectPhase_ID (int C_ProjectPhase_ID)
{
if (C_ProjectPhase_ID <= 0) set_ValueNoCheck ("C_ProjectPhase_ID", null);
 else 
set_ValueNoCheck ("C_ProjectPhase_ID", Integer.valueOf(C_ProjectPhase_ID));
}
/** Get Project Phase.
@return Phase of a Project */
public int getC_ProjectPhase_ID() 
{
Integer ii = (Integer)get_Value("C_ProjectPhase_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ProjectPhase_ID */
public static final String COLUMNNAME_C_ProjectPhase_ID = "C_ProjectPhase_ID";
/** Set Project Task.
@param C_ProjectTask_ID Actual Project Task in a Phase */
public void setC_ProjectTask_ID (int C_ProjectTask_ID)
{
if (C_ProjectTask_ID <= 0) set_ValueNoCheck ("C_ProjectTask_ID", null);
 else 
set_ValueNoCheck ("C_ProjectTask_ID", Integer.valueOf(C_ProjectTask_ID));
}
/** Get Project Task.
@return Actual Project Task in a Phase */
public int getC_ProjectTask_ID() 
{
Integer ii = (Integer)get_Value("C_ProjectTask_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ProjectTask_ID */
public static final String COLUMNNAME_C_ProjectTask_ID = "C_ProjectTask_ID";
/** Set Project.
@param C_Project_ID Financial Project */
public void setC_Project_ID (int C_Project_ID)
{
if (C_Project_ID <= 0) set_Value ("C_Project_ID", null);
 else 
set_Value ("C_Project_ID", Integer.valueOf(C_Project_ID));
}
/** Get Project.
@return Financial Project */
public int getC_Project_ID() 
{
Integer ii = (Integer)get_Value("C_Project_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Project_ID */
public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";
/** Set Tax.
@param C_Tax_ID Tax identifier */
public void setC_Tax_ID (int C_Tax_ID)
{
if (C_Tax_ID < 1) throw new IllegalArgumentException ("C_Tax_ID is mandatory.");
set_Value ("C_Tax_ID", Integer.valueOf(C_Tax_ID));
}
/** Get Tax.
@return Tax identifier */
public int getC_Tax_ID() 
{
Integer ii = (Integer)get_Value("C_Tax_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Tax_ID */
public static final String COLUMNNAME_C_Tax_ID = "C_Tax_ID";
/** Set UOM.
@param C_UOM_ID Unit of Measure */
public void setC_UOM_ID (int C_UOM_ID)
{
if (C_UOM_ID < 1) throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
set_ValueNoCheck ("C_UOM_ID", Integer.valueOf(C_UOM_ID));
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
/** Set Date Delivered.
@param DateDelivered Date when the product was delivered */
public void setDateDelivered (Timestamp DateDelivered)
{
set_ValueNoCheck ("DateDelivered", DateDelivered);
}
/** Get Date Delivered.
@return Date when the product was delivered */
public Timestamp getDateDelivered() 
{
return (Timestamp)get_Value("DateDelivered");
}
/** Column name DateDelivered */
public static final String COLUMNNAME_DateDelivered = "DateDelivered";
/** Set Date Invoiced.
@param DateInvoiced Date printed on Invoice */
public void setDateInvoiced (Timestamp DateInvoiced)
{
set_ValueNoCheck ("DateInvoiced", DateInvoiced);
}
/** Get Date Invoiced.
@return Date printed on Invoice */
public Timestamp getDateInvoiced() 
{
return (Timestamp)get_Value("DateInvoiced");
}
/** Column name DateInvoiced */
public static final String COLUMNNAME_DateInvoiced = "DateInvoiced";
/** Set Date Ordered.
@param DateOrdered Date of Order */
public void setDateOrdered (Timestamp DateOrdered)
{
if (DateOrdered == null) throw new IllegalArgumentException ("DateOrdered is mandatory.");
set_Value ("DateOrdered", DateOrdered);
}
/** Get Date Ordered.
@return Date of Order */
public Timestamp getDateOrdered() 
{
return (Timestamp)get_Value("DateOrdered");
}
/** Column name DateOrdered */
public static final String COLUMNNAME_DateOrdered = "DateOrdered";
/** Set Date Promised.
@param DatePromised Date Order was promised */
public void setDatePromised (Timestamp DatePromised)
{
set_Value ("DatePromised", DatePromised);
}
/** Get Date Promised.
@return Date Order was promised */
public Timestamp getDatePromised() 
{
return (Timestamp)get_Value("DatePromised");
}
/** Column name DatePromised */
public static final String COLUMNNAME_DatePromised = "DatePromised";
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
/** Set Discount %.
@param Discount Discount in percent */
public void setDiscount (BigDecimal Discount)
{
set_Value ("Discount", Discount);
}
/** Get Discount %.
@return Discount in percent */
public BigDecimal getDiscount() 
{
BigDecimal bd = (BigDecimal)get_Value("Discount");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Discount */
public static final String COLUMNNAME_Discount = "Discount";
/** Set Freight Amount.
@param FreightAmt Freight Amount  */
public void setFreightAmt (BigDecimal FreightAmt)
{
if (FreightAmt == null) throw new IllegalArgumentException ("FreightAmt is mandatory.");
set_Value ("FreightAmt", FreightAmt);
}
/** Get Freight Amount.
@return Freight Amount  */
public BigDecimal getFreightAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("FreightAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name FreightAmt */
public static final String COLUMNNAME_FreightAmt = "FreightAmt";
/** Set Description Only.
@param IsDescription if true, the line is just description and no transaction */
public void setIsDescription (boolean IsDescription)
{
set_Value ("IsDescription", Boolean.valueOf(IsDescription));
}
/** Get Description Only.
@return if true, the line is just description and no transaction */
public boolean isDescription() 
{
Object oo = get_Value("IsDescription");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsDescription */
public static final String COLUMNNAME_IsDescription = "IsDescription";
/** Set Line No.
@param Line Unique line for this document */
public void setLine (int Line)
{
set_Value ("Line", Integer.valueOf(Line));
}
/** Get Line No.
@return Unique line for this document */
public int getLine() 
{
Integer ii = (Integer)get_Value("Line");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Line */
public static final String COLUMNNAME_Line = "Line";
/** Set Line Amount.
@param LineNetAmt Line Extended Amount (Quantity * Actual Price) without Freight and Charges */
public void setLineNetAmt (BigDecimal LineNetAmt)
{
if (LineNetAmt == null) throw new IllegalArgumentException ("LineNetAmt is mandatory.");
set_ValueNoCheck ("LineNetAmt", LineNetAmt);
}
/** Get Line Amount.
@return Line Extended Amount (Quantity * Actual Price) without Freight and Charges */
public BigDecimal getLineNetAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("LineNetAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name LineNetAmt */
public static final String COLUMNNAME_LineNetAmt = "LineNetAmt";
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
/** Set Shipper.
@param M_Shipper_ID Method or manner of product delivery */
public void setM_Shipper_ID (int M_Shipper_ID)
{
if (M_Shipper_ID <= 0) set_Value ("M_Shipper_ID", null);
 else 
set_Value ("M_Shipper_ID", Integer.valueOf(M_Shipper_ID));
}
/** Get Shipper.
@return Method or manner of product delivery */
public int getM_Shipper_ID() 
{
Integer ii = (Integer)get_Value("M_Shipper_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Shipper_ID */
public static final String COLUMNNAME_M_Shipper_ID = "M_Shipper_ID";

/** M_Warehouse_ID AD_Reference_ID=197 */
public static final int M_WAREHOUSE_ID_AD_Reference_ID=197;
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
/** Set Unit Price.
@param PriceActual Actual Price  */
public void setPriceActual (BigDecimal PriceActual)
{
if (PriceActual == null) throw new IllegalArgumentException ("PriceActual is mandatory.");
set_ValueNoCheck ("PriceActual", PriceActual);
}
/** Get Unit Price.
@return Actual Price  */
public BigDecimal getPriceActual() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceActual");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PriceActual */
public static final String COLUMNNAME_PriceActual = "PriceActual";
/** Set Cost Price.
@param PriceCost Price per Unit of Measure including all indirect costs (Freight, etc.) */
public void setPriceCost (BigDecimal PriceCost)
{
set_Value ("PriceCost", PriceCost);
}
/** Get Cost Price.
@return Price per Unit of Measure including all indirect costs (Freight, etc.) */
public BigDecimal getPriceCost() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceCost");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PriceCost */
public static final String COLUMNNAME_PriceCost = "PriceCost";
/** Set Price.
@param PriceEntered Price Entered - the price based on the selected/base UoM */
public void setPriceEntered (BigDecimal PriceEntered)
{
if (PriceEntered == null) throw new IllegalArgumentException ("PriceEntered is mandatory.");
set_Value ("PriceEntered", PriceEntered);
}
/** Get Price.
@return Price Entered - the price based on the selected/base UoM */
public BigDecimal getPriceEntered() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceEntered");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PriceEntered */
public static final String COLUMNNAME_PriceEntered = "PriceEntered";
/** Set Limit Price.
@param PriceLimit Lowest price for a product */
public void setPriceLimit (BigDecimal PriceLimit)
{
if (PriceLimit == null) throw new IllegalArgumentException ("PriceLimit is mandatory.");
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
if (PriceList == null) throw new IllegalArgumentException ("PriceList is mandatory.");
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
/** Set Delivered Quantity.
@param QtyDelivered Delivered Quantity */
public void setQtyDelivered (BigDecimal QtyDelivered)
{
if (QtyDelivered == null) throw new IllegalArgumentException ("QtyDelivered is mandatory.");
set_ValueNoCheck ("QtyDelivered", QtyDelivered);
}
/** Get Delivered Quantity.
@return Delivered Quantity */
public BigDecimal getQtyDelivered() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyDelivered");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtyDelivered */
public static final String COLUMNNAME_QtyDelivered = "QtyDelivered";
/** Set Quantity.
@param QtyEntered The Quantity Entered is based on the selected UoM */
public void setQtyEntered (BigDecimal QtyEntered)
{
if (QtyEntered == null) throw new IllegalArgumentException ("QtyEntered is mandatory.");
set_Value ("QtyEntered", QtyEntered);
}
/** Get Quantity.
@return The Quantity Entered is based on the selected UoM */
public BigDecimal getQtyEntered() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyEntered");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtyEntered */
public static final String COLUMNNAME_QtyEntered = "QtyEntered";
/** Set Quantity Invoiced.
@param QtyInvoiced Invoiced Quantity */
public void setQtyInvoiced (BigDecimal QtyInvoiced)
{
if (QtyInvoiced == null) throw new IllegalArgumentException ("QtyInvoiced is mandatory.");
set_ValueNoCheck ("QtyInvoiced", QtyInvoiced);
}
/** Get Quantity Invoiced.
@return Invoiced Quantity */
public BigDecimal getQtyInvoiced() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyInvoiced");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtyInvoiced */
public static final String COLUMNNAME_QtyInvoiced = "QtyInvoiced";
/** Set Lost Sales Qty.
@param QtyLostSales Quantity of potential sales */
public void setQtyLostSales (BigDecimal QtyLostSales)
{
if (QtyLostSales == null) throw new IllegalArgumentException ("QtyLostSales is mandatory.");
set_Value ("QtyLostSales", QtyLostSales);
}
/** Get Lost Sales Qty.
@return Quantity of potential sales */
public BigDecimal getQtyLostSales() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyLostSales");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtyLostSales */
public static final String COLUMNNAME_QtyLostSales = "QtyLostSales";
/** Set Ordered Quantity.
@param QtyOrdered Ordered Quantity */
public void setQtyOrdered (BigDecimal QtyOrdered)
{
if (QtyOrdered == null) throw new IllegalArgumentException ("QtyOrdered is mandatory.");
set_Value ("QtyOrdered", QtyOrdered);
}
/** Get Ordered Quantity.
@return Ordered Quantity */
public BigDecimal getQtyOrdered() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyOrdered");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtyOrdered */
public static final String COLUMNNAME_QtyOrdered = "QtyOrdered";
/** Set Reserved Quantity.
@param QtyReserved Reserved Quantity */
public void setQtyReserved (BigDecimal QtyReserved)
{
if (QtyReserved == null) throw new IllegalArgumentException ("QtyReserved is mandatory.");
set_ValueNoCheck ("QtyReserved", QtyReserved);
}
/** Get Reserved Quantity.
@return Reserved Quantity */
public BigDecimal getQtyReserved() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyReserved");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtyReserved */
public static final String COLUMNNAME_QtyReserved = "QtyReserved";
/** Set Revenue Recognition Amt.
@param RRAmt Revenue Recognition Amount */
public void setRRAmt (BigDecimal RRAmt)
{
set_Value ("RRAmt", RRAmt);
}
/** Get Revenue Recognition Amt.
@return Revenue Recognition Amount */
public BigDecimal getRRAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("RRAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name RRAmt */
public static final String COLUMNNAME_RRAmt = "RRAmt";
/** Set Revenue Recognition Start.
@param RRStartDate Revenue Recognition Start Date */
public void setRRStartDate (Timestamp RRStartDate)
{
set_Value ("RRStartDate", RRStartDate);
}
/** Get Revenue Recognition Start.
@return Revenue Recognition Start Date */
public Timestamp getRRStartDate() 
{
return (Timestamp)get_Value("RRStartDate");
}
/** Column name RRStartDate */
public static final String COLUMNNAME_RRStartDate = "RRStartDate";

/** Ref_OrderLine_ID AD_Reference_ID=271 */
public static final int REF_ORDERLINE_ID_AD_Reference_ID=271;
/** Set Referenced Order Line.
@param Ref_OrderLine_ID Reference to corresponding Sales/Purchase Order */
public void setRef_OrderLine_ID (int Ref_OrderLine_ID)
{
if (Ref_OrderLine_ID <= 0) set_Value ("Ref_OrderLine_ID", null);
 else 
set_Value ("Ref_OrderLine_ID", Integer.valueOf(Ref_OrderLine_ID));
}
/** Get Referenced Order Line.
@return Reference to corresponding Sales/Purchase Order */
public int getRef_OrderLine_ID() 
{
Integer ii = (Integer)get_Value("Ref_OrderLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Ref_OrderLine_ID */
public static final String COLUMNNAME_Ref_OrderLine_ID = "Ref_OrderLine_ID";
/** Set Resource Assignment.
@param S_ResourceAssignment_ID Resource Assignment */
public void setS_ResourceAssignment_ID (int S_ResourceAssignment_ID)
{
if (S_ResourceAssignment_ID <= 0) set_Value ("S_ResourceAssignment_ID", null);
 else 
set_Value ("S_ResourceAssignment_ID", Integer.valueOf(S_ResourceAssignment_ID));
}
/** Get Resource Assignment.
@return Resource Assignment */
public int getS_ResourceAssignment_ID() 
{
Integer ii = (Integer)get_Value("S_ResourceAssignment_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name S_ResourceAssignment_ID */
public static final String COLUMNNAME_S_ResourceAssignment_ID = "S_ResourceAssignment_ID";

/** User1_ID AD_Reference_ID=134 */
public static final int USER1_ID_AD_Reference_ID=134;
/** Set User List 1.
@param User1_ID User defined list element #1 */
public void setUser1_ID (int User1_ID)
{
if (User1_ID <= 0) set_Value ("User1_ID", null);
 else 
set_Value ("User1_ID", Integer.valueOf(User1_ID));
}
/** Get User List 1.
@return User defined list element #1 */
public int getUser1_ID() 
{
Integer ii = (Integer)get_Value("User1_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name User1_ID */
public static final String COLUMNNAME_User1_ID = "User1_ID";

/** User2_ID AD_Reference_ID=137 */
public static final int USER2_ID_AD_Reference_ID=137;
/** Set User List 2.
@param User2_ID User defined list element #2 */
public void setUser2_ID (int User2_ID)
{
if (User2_ID <= 0) set_Value ("User2_ID", null);
 else 
set_Value ("User2_ID", Integer.valueOf(User2_ID));
}
/** Get User List 2.
@return User defined list element #2 */
public int getUser2_ID() 
{
Integer ii = (Integer)get_Value("User2_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name User2_ID */
public static final String COLUMNNAME_User2_ID = "User2_ID";
}
