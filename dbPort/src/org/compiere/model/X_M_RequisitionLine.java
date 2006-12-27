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
/** Generated Model for M_RequisitionLine
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_M_RequisitionLine extends PO
{
/** Standard Constructor
@param ctx context
@param M_RequisitionLine_ID id
@param trxName transaction
*/
public X_M_RequisitionLine (Properties ctx, int M_RequisitionLine_ID, String trxName)
{
super (ctx, M_RequisitionLine_ID, trxName);
/** if (M_RequisitionLine_ID == 0)
{
setLine (0);	// @SQL=SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM M_RequisitionLine WHERE M_Requisition_ID=@M_Requisition_ID@
setLineNetAmt (Env.ZERO);
setM_RequisitionLine_ID (0);
setM_Requisition_ID (0);
setPriceActual (Env.ZERO);
setQty (Env.ZERO);	// 1
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_RequisitionLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=703 */
public static final int Table_ID=MTable.getTable_ID("M_RequisitionLine");

/** TableName=M_RequisitionLine */
public static final String Table_Name="M_RequisitionLine";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_RequisitionLine");

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
StringBuffer sb = new StringBuffer ("X_M_RequisitionLine[").append(get_ID()).append("]");
return sb.toString();
}
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
/** Set Sales Order Line.
@param C_OrderLine_ID Sales Order Line */
public void setC_OrderLine_ID (int C_OrderLine_ID)
{
if (C_OrderLine_ID <= 0) set_Value ("C_OrderLine_ID", null);
 else 
set_Value ("C_OrderLine_ID", Integer.valueOf(C_OrderLine_ID));
}
/** Get Sales Order Line.
@return Sales Order Line */
public int getC_OrderLine_ID() 
{
Integer ii = (Integer)get_Value("C_OrderLine_ID");
if (ii == null) return 0;
return ii.intValue();
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getLine()));
}
/** Set Line Amount.
@param LineNetAmt Line Extended Amount (Quantity * Actual Price) without Freight and Charges */
public void setLineNetAmt (BigDecimal LineNetAmt)
{
if (LineNetAmt == null) throw new IllegalArgumentException ("LineNetAmt is mandatory.");
set_Value ("LineNetAmt", LineNetAmt);
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
if (M_AttributeSetInstance_ID <= 0) set_Value ("M_AttributeSetInstance_ID", null);
 else 
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
/** Set Requisition Line.
@param M_RequisitionLine_ID Material Requisition Line */
public void setM_RequisitionLine_ID (int M_RequisitionLine_ID)
{
if (M_RequisitionLine_ID < 1) throw new IllegalArgumentException ("M_RequisitionLine_ID is mandatory.");
set_ValueNoCheck ("M_RequisitionLine_ID", Integer.valueOf(M_RequisitionLine_ID));
}
/** Get Requisition Line.
@return Material Requisition Line */
public int getM_RequisitionLine_ID() 
{
Integer ii = (Integer)get_Value("M_RequisitionLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Requisition.
@param M_Requisition_ID Material Requisition */
public void setM_Requisition_ID (int M_Requisition_ID)
{
if (M_Requisition_ID < 1) throw new IllegalArgumentException ("M_Requisition_ID is mandatory.");
set_ValueNoCheck ("M_Requisition_ID", Integer.valueOf(M_Requisition_ID));
}
/** Get Requisition.
@return Material Requisition */
public int getM_Requisition_ID() 
{
Integer ii = (Integer)get_Value("M_Requisition_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Unit Price.
@param PriceActual Actual Price  */
public void setPriceActual (BigDecimal PriceActual)
{
if (PriceActual == null) throw new IllegalArgumentException ("PriceActual is mandatory.");
set_Value ("PriceActual", PriceActual);
}
/** Get Unit Price.
@return Actual Price  */
public BigDecimal getPriceActual() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceActual");
if (bd == null) return Env.ZERO;
return bd;
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
