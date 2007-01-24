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
/** Generated Model for M_CostDetail
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_M_CostDetail extends PO
{
/** Standard Constructor
@param ctx context
@param M_CostDetail_ID id
@param trxName transaction
*/
public X_M_CostDetail (Properties ctx, int M_CostDetail_ID, String trxName)
{
super (ctx, M_CostDetail_ID, trxName);
/** if (M_CostDetail_ID == 0)
{
setAmt (Env.ZERO);
setC_AcctSchema_ID (0);
setIsSOTrx (false);
setM_AttributeSetInstance_ID (0);
setM_CostDetail_ID (0);
setM_Product_ID (0);
setProcessed (false);
setQty (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_CostDetail (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=808 */
public static final int Table_ID=MTable.getTable_ID("M_CostDetail");

/** TableName=M_CostDetail */
public static final String Table_Name="M_CostDetail";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_CostDetail");

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
StringBuffer sb = new StringBuffer ("X_M_CostDetail[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Amount.
@param Amt Amount */
public void setAmt (BigDecimal Amt)
{
if (Amt == null) throw new IllegalArgumentException ("Amt is mandatory.");
set_Value ("Amt", Amt);
}
/** Get Amount.
@return Amount */
public BigDecimal getAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("Amt");
if (bd == null) return Env.ZERO;
return bd;
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
/** Set Invoice Line.
@param C_InvoiceLine_ID Invoice Detail Line */
public void setC_InvoiceLine_ID (int C_InvoiceLine_ID)
{
if (C_InvoiceLine_ID <= 0) set_ValueNoCheck ("C_InvoiceLine_ID", null);
 else 
set_ValueNoCheck ("C_InvoiceLine_ID", Integer.valueOf(C_InvoiceLine_ID));
}
/** Get Invoice Line.
@return Invoice Detail Line */
public int getC_InvoiceLine_ID() 
{
Integer ii = (Integer)get_Value("C_InvoiceLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Sales Order Line.
@param C_OrderLine_ID Sales Order Line */
public void setC_OrderLine_ID (int C_OrderLine_ID)
{
if (C_OrderLine_ID <= 0) set_ValueNoCheck ("C_OrderLine_ID", null);
 else 
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
/** Set Project Issue.
@param C_ProjectIssue_ID Project Issues (Material, Labor) */
public void setC_ProjectIssue_ID (int C_ProjectIssue_ID)
{
if (C_ProjectIssue_ID <= 0) set_Value ("C_ProjectIssue_ID", null);
 else 
set_Value ("C_ProjectIssue_ID", Integer.valueOf(C_ProjectIssue_ID));
}
/** Get Project Issue.
@return Project Issues (Material, Labor) */
public int getC_ProjectIssue_ID() 
{
Integer ii = (Integer)get_Value("C_ProjectIssue_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Delta Amount.
@param DeltaAmt Difference Amount */
public void setDeltaAmt (BigDecimal DeltaAmt)
{
set_Value ("DeltaAmt", DeltaAmt);
}
/** Get Delta Amount.
@return Difference Amount */
public BigDecimal getDeltaAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("DeltaAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Delta Quantity.
@param DeltaQty Quantity Difference */
public void setDeltaQty (BigDecimal DeltaQty)
{
set_Value ("DeltaQty", DeltaQty);
}
/** Get Delta Quantity.
@return Quantity Difference */
public BigDecimal getDeltaQty() 
{
BigDecimal bd = (BigDecimal)get_Value("DeltaQty");
if (bd == null) return Env.ZERO;
return bd;
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
/** Set Sales Transaction.
@param IsSOTrx This is a Sales Transaction */
public void setIsSOTrx (boolean IsSOTrx)
{
set_Value ("IsSOTrx", Boolean.valueOf(IsSOTrx));
}
/** Get Sales Transaction.
@return This is a Sales Transaction */
public boolean isSOTrx() 
{
Object oo = get_Value("IsSOTrx");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Attribute Set Instance.
@param M_AttributeSetInstance_ID Product Attribute Set Instance */
public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
{
if (M_AttributeSetInstance_ID < 0) throw new IllegalArgumentException ("M_AttributeSetInstance_ID is mandatory.");
set_ValueNoCheck ("M_AttributeSetInstance_ID", Integer.valueOf(M_AttributeSetInstance_ID));
}
/** Get Attribute Set Instance.
@return Product Attribute Set Instance */
public int getM_AttributeSetInstance_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSetInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Cost Detail.
@param M_CostDetail_ID Cost Detail Information */
public void setM_CostDetail_ID (int M_CostDetail_ID)
{
if (M_CostDetail_ID < 1) throw new IllegalArgumentException ("M_CostDetail_ID is mandatory.");
set_ValueNoCheck ("M_CostDetail_ID", Integer.valueOf(M_CostDetail_ID));
}
/** Get Cost Detail.
@return Cost Detail Information */
public int getM_CostDetail_ID() 
{
Integer ii = (Integer)get_Value("M_CostDetail_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Cost Element.
@param M_CostElement_ID Product Cost Element */
public void setM_CostElement_ID (int M_CostElement_ID)
{
if (M_CostElement_ID <= 0) set_ValueNoCheck ("M_CostElement_ID", null);
 else 
set_ValueNoCheck ("M_CostElement_ID", Integer.valueOf(M_CostElement_ID));
}
/** Get Cost Element.
@return Product Cost Element */
public int getM_CostElement_ID() 
{
Integer ii = (Integer)get_Value("M_CostElement_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Shipment/Receipt Line.
@param M_InOutLine_ID Line on Shipment or Receipt document */
public void setM_InOutLine_ID (int M_InOutLine_ID)
{
if (M_InOutLine_ID <= 0) set_ValueNoCheck ("M_InOutLine_ID", null);
 else 
set_ValueNoCheck ("M_InOutLine_ID", Integer.valueOf(M_InOutLine_ID));
}
/** Get Shipment/Receipt Line.
@return Line on Shipment or Receipt document */
public int getM_InOutLine_ID() 
{
Integer ii = (Integer)get_Value("M_InOutLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Phys.Inventory Line.
@param M_InventoryLine_ID Unique line in an Inventory document */
public void setM_InventoryLine_ID (int M_InventoryLine_ID)
{
if (M_InventoryLine_ID <= 0) set_Value ("M_InventoryLine_ID", null);
 else 
set_Value ("M_InventoryLine_ID", Integer.valueOf(M_InventoryLine_ID));
}
/** Get Phys.Inventory Line.
@return Unique line in an Inventory document */
public int getM_InventoryLine_ID() 
{
Integer ii = (Integer)get_Value("M_InventoryLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Move Line.
@param M_MovementLine_ID Inventory Move document Line */
public void setM_MovementLine_ID (int M_MovementLine_ID)
{
if (M_MovementLine_ID <= 0) set_Value ("M_MovementLine_ID", null);
 else 
set_Value ("M_MovementLine_ID", Integer.valueOf(M_MovementLine_ID));
}
/** Get Move Line.
@return Inventory Move document Line */
public int getM_MovementLine_ID() 
{
Integer ii = (Integer)get_Value("M_MovementLine_ID");
if (ii == null) return 0;
return ii.intValue();
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
/** Set Production Line.
@param M_ProductionLine_ID Document Line representing a production */
public void setM_ProductionLine_ID (int M_ProductionLine_ID)
{
if (M_ProductionLine_ID <= 0) set_Value ("M_ProductionLine_ID", null);
 else 
set_Value ("M_ProductionLine_ID", Integer.valueOf(M_ProductionLine_ID));
}
/** Get Production Line.
@return Document Line representing a production */
public int getM_ProductionLine_ID() 
{
Integer ii = (Integer)get_Value("M_ProductionLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Price.
@param Price Price */
public void setPrice (BigDecimal Price)
{
throw new IllegalArgumentException ("Price is virtual column");
}
/** Get Price.
@return Price */
public BigDecimal getPrice() 
{
BigDecimal bd = (BigDecimal)get_Value("Price");
if (bd == null) return Env.ZERO;
return bd;
}
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
/** Set Quantity.
@param Qty Quantity */
public void setQty (BigDecimal Qty)
{
if (Qty == null) throw new IllegalArgumentException ("Qty is mandatory.");
set_Value ("Qty", Qty);
}
/** Get Quantity.
@return Quantity */
public BigDecimal getQty() 
{
BigDecimal bd = (BigDecimal)get_Value("Qty");
if (bd == null) return Env.ZERO;
return bd;
}
}
