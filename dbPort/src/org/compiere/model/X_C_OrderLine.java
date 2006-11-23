/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
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
/** Generated Model for C_OrderLine
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
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
/** AD_Table_ID=260 */
public static final int Table_ID=260;

/** TableName=C_OrderLine */
public static final String Table_Name="C_OrderLine";

protected static KeyNamePair Model = new KeyNamePair(260,"C_OrderLine");

protected BigDecimal accessLevel = new BigDecimal(1);
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
set_Value ("AD_OrgTrx_ID", new Integer(AD_OrgTrx_ID));
}
/** Get Trx Organization.
@return Performing or initiating organization */
public int getAD_OrgTrx_ID() 
{
Integer ii = (Integer)get_Value("AD_OrgTrx_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Activity.
@param C_Activity_ID Business Activity */
public void setC_Activity_ID (int C_Activity_ID)
{
if (C_Activity_ID <= 0) set_Value ("C_Activity_ID", null);
 else 
set_Value ("C_Activity_ID", new Integer(C_Activity_ID));
}
/** Get Activity.
@return Business Activity */
public int getC_Activity_ID() 
{
Integer ii = (Integer)get_Value("C_Activity_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_ValueNoCheck ("C_BPartner_ID", null);
 else 
set_ValueNoCheck ("C_BPartner_ID", new Integer(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Partner Location.
@param C_BPartner_Location_ID Identifies the (ship to) address for this Business Partner */
public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
{
if (C_BPartner_Location_ID < 1) throw new IllegalArgumentException ("C_BPartner_Location_ID is mandatory.");
set_Value ("C_BPartner_Location_ID", new Integer(C_BPartner_Location_ID));
}
/** Get Partner Location.
@return Identifies the (ship to) address for this Business Partner */
public int getC_BPartner_Location_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Campaign.
@param C_Campaign_ID Marketing Campaign */
public void setC_Campaign_ID (int C_Campaign_ID)
{
if (C_Campaign_ID <= 0) set_Value ("C_Campaign_ID", null);
 else 
set_Value ("C_Campaign_ID", new Integer(C_Campaign_ID));
}
/** Get Campaign.
@return Marketing Campaign */
public int getC_Campaign_ID() 
{
Integer ii = (Integer)get_Value("C_Campaign_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Charge.
@param C_Charge_ID Additional document charges */
public void setC_Charge_ID (int C_Charge_ID)
{
if (C_Charge_ID <= 0) set_Value ("C_Charge_ID", null);
 else 
set_Value ("C_Charge_ID", new Integer(C_Charge_ID));
}
/** Get Charge.
@return Additional document charges */
public int getC_Charge_ID() 
{
Integer ii = (Integer)get_Value("C_Charge_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID < 1) throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
set_ValueNoCheck ("C_Currency_ID", new Integer(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Sales Order Line.
@param C_OrderLine_ID Sales Order Line */
public void setC_OrderLine_ID (int C_OrderLine_ID)
{
if (C_OrderLine_ID < 1) throw new IllegalArgumentException ("C_OrderLine_ID is mandatory.");
set_ValueNoCheck ("C_OrderLine_ID", new Integer(C_OrderLine_ID));
}
/** Get Sales Order Line.
@return Sales Order Line */
public int getC_OrderLine_ID() 
{
Integer ii = (Integer)get_Value("C_OrderLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Order.
@param C_Order_ID Order */
public void setC_Order_ID (int C_Order_ID)
{
if (C_Order_ID < 1) throw new IllegalArgumentException ("C_Order_ID is mandatory.");
set_ValueNoCheck ("C_Order_ID", new Integer(C_Order_ID));
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
/** Set Project Phase.
@param C_ProjectPhase_ID Phase of a Project */
public void setC_ProjectPhase_ID (int C_ProjectPhase_ID)
{
if (C_ProjectPhase_ID <= 0) set_ValueNoCheck ("C_ProjectPhase_ID", null);
 else 
set_ValueNoCheck ("C_ProjectPhase_ID", new Integer(C_ProjectPhase_ID));
}
/** Get Project Phase.
@return Phase of a Project */
public int getC_ProjectPhase_ID() 
{
Integer ii = (Integer)get_Value("C_ProjectPhase_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Project Task.
@param C_ProjectTask_ID Actual Project Task in a Phase */
public void setC_ProjectTask_ID (int C_ProjectTask_ID)
{
if (C_ProjectTask_ID <= 0) set_ValueNoCheck ("C_ProjectTask_ID", null);
 else 
set_ValueNoCheck ("C_ProjectTask_ID", new Integer(C_ProjectTask_ID));
}
/** Get Project Task.
@return Actual Project Task in a Phase */
public int getC_ProjectTask_ID() 
{
Integer ii = (Integer)get_Value("C_ProjectTask_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Project.
@param C_Project_ID Financial Project */
public void setC_Project_ID (int C_Project_ID)
{
if (C_Project_ID <= 0) set_Value ("C_Project_ID", null);
 else 
set_Value ("C_Project_ID", new Integer(C_Project_ID));
}
/** Get Project.
@return Financial Project */
public int getC_Project_ID() 
{
Integer ii = (Integer)get_Value("C_Project_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Tax.
@param C_Tax_ID Tax identifier */
public void setC_Tax_ID (int C_Tax_ID)
{
if (C_Tax_ID < 1) throw new IllegalArgumentException ("C_Tax_ID is mandatory.");
set_Value ("C_Tax_ID", new Integer(C_Tax_ID));
}
/** Get Tax.
@return Tax identifier */
public int getC_Tax_ID() 
{
Integer ii = (Integer)get_Value("C_Tax_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set UOM.
@param C_UOM_ID Unit of Measure */
public void setC_UOM_ID (int C_UOM_ID)
{
if (C_UOM_ID < 1) throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
set_ValueNoCheck ("C_UOM_ID", new Integer(C_UOM_ID));
}
/** Get UOM.
@return Unit of Measure */
public int getC_UOM_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Description Only.
@param IsDescription if true, the line is just description and no transaction */
public void setIsDescription (boolean IsDescription)
{
set_Value ("IsDescription", new Boolean(IsDescription));
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
/** Set Line No.
@param Line Unique line for this document */
public void setLine (int Line)
{
set_Value ("Line", new Integer(Line));
}
/** Get Line No.
@return Unique line for this document */
public int getLine() 
{
Integer ii = (Integer)get_Value("Line");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Attribute Set Instance.
@param M_AttributeSetInstance_ID Product Attribute Set Instance */
public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
{
if (M_AttributeSetInstance_ID < 0) throw new IllegalArgumentException ("M_AttributeSetInstance_ID is mandatory.");
set_Value ("M_AttributeSetInstance_ID", new Integer(M_AttributeSetInstance_ID));
}
/** Get Attribute Set Instance.
@return Product Attribute Set Instance */
public int getM_AttributeSetInstance_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSetInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_Value ("M_Product_ID", null);
 else 
set_Value ("M_Product_ID", new Integer(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Shipper.
@param M_Shipper_ID Method or manner of product delivery */
public void setM_Shipper_ID (int M_Shipper_ID)
{
if (M_Shipper_ID <= 0) set_Value ("M_Shipper_ID", null);
 else 
set_Value ("M_Shipper_ID", new Integer(M_Shipper_ID));
}
/** Get Shipper.
@return Method or manner of product delivery */
public int getM_Shipper_ID() 
{
Integer ii = (Integer)get_Value("M_Shipper_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** M_Warehouse_ID AD_Reference_ID=197 */
public static final int M_WAREHOUSE_ID_AD_Reference_ID=197;
/** Set Warehouse.
@param M_Warehouse_ID Storage Warehouse and Service Point */
public void setM_Warehouse_ID (int M_Warehouse_ID)
{
if (M_Warehouse_ID < 1) throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
set_Value ("M_Warehouse_ID", new Integer(M_Warehouse_ID));
}
/** Get Warehouse.
@return Storage Warehouse and Service Point */
public int getM_Warehouse_ID() 
{
Integer ii = (Integer)get_Value("M_Warehouse_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", new Boolean(Processed));
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

/** Ref_OrderLine_ID AD_Reference_ID=271 */
public static final int REF_ORDERLINE_ID_AD_Reference_ID=271;
/** Set Referenced Order Line.
@param Ref_OrderLine_ID Reference to corresponding Sales/Purchase Order */
public void setRef_OrderLine_ID (int Ref_OrderLine_ID)
{
if (Ref_OrderLine_ID <= 0) set_Value ("Ref_OrderLine_ID", null);
 else 
set_Value ("Ref_OrderLine_ID", new Integer(Ref_OrderLine_ID));
}
/** Get Referenced Order Line.
@return Reference to corresponding Sales/Purchase Order */
public int getRef_OrderLine_ID() 
{
Integer ii = (Integer)get_Value("Ref_OrderLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Resource Assignment.
@param S_ResourceAssignment_ID Resource Assignment */
public void setS_ResourceAssignment_ID (int S_ResourceAssignment_ID)
{
if (S_ResourceAssignment_ID <= 0) set_Value ("S_ResourceAssignment_ID", null);
 else 
set_Value ("S_ResourceAssignment_ID", new Integer(S_ResourceAssignment_ID));
}
/** Get Resource Assignment.
@return Resource Assignment */
public int getS_ResourceAssignment_ID() 
{
Integer ii = (Integer)get_Value("S_ResourceAssignment_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** User1_ID AD_Reference_ID=134 */
public static final int USER1_ID_AD_Reference_ID=134;
/** Set User List 1.
@param User1_ID User defined list element #1 */
public void setUser1_ID (int User1_ID)
{
if (User1_ID <= 0) set_Value ("User1_ID", null);
 else 
set_Value ("User1_ID", new Integer(User1_ID));
}
/** Get User List 1.
@return User defined list element #1 */
public int getUser1_ID() 
{
Integer ii = (Integer)get_Value("User1_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** User2_ID AD_Reference_ID=137 */
public static final int USER2_ID_AD_Reference_ID=137;
/** Set User List 2.
@param User2_ID User defined list element #2 */
public void setUser2_ID (int User2_ID)
{
if (User2_ID <= 0) set_Value ("User2_ID", null);
 else 
set_Value ("User2_ID", new Integer(User2_ID));
}
/** Get User List 2.
@return User defined list element #2 */
public int getUser2_ID() 
{
Integer ii = (Integer)get_Value("User2_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
