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
/** Generated Model for M_Cost
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_M_Cost extends PO
{
/** Standard Constructor
@param ctx context
@param M_Cost_ID id
@param trxName transaction
*/
public X_M_Cost (Properties ctx, int M_Cost_ID, String trxName)
{
super (ctx, M_Cost_ID, trxName);
/** if (M_Cost_ID == 0)
{
setC_AcctSchema_ID (0);
setCurrentCostPrice (Env.ZERO);
setCurrentQty (Env.ZERO);
setFutureCostPrice (Env.ZERO);
setM_AttributeSetInstance_ID (0);
setM_CostElement_ID (0);
setM_CostType_ID (0);
setM_Product_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_Cost (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=771 */
public static final int Table_ID=771;

/** TableName=M_Cost */
public static final String Table_Name="M_Cost";

protected static KeyNamePair Model = new KeyNamePair(771,"M_Cost");

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
StringBuffer sb = new StringBuffer ("X_M_Cost[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Accounting Schema.
@param C_AcctSchema_ID Rules for accounting */
public void setC_AcctSchema_ID (int C_AcctSchema_ID)
{
if (C_AcctSchema_ID < 1) throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
set_ValueNoCheck ("C_AcctSchema_ID", new Integer(C_AcctSchema_ID));
}
/** Get Accounting Schema.
@return Rules for accounting */
public int getC_AcctSchema_ID() 
{
Integer ii = (Integer)get_Value("C_AcctSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}

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
throw new IllegalArgumentException ("CostingMethod is virtual column");
}
/** Get Costing Method.
@return Indicates how Costs will be calculated */
public String getCostingMethod() 
{
return (String)get_Value("CostingMethod");
}
/** Set Accumulated Amt.
@param CumulatedAmt Total Amount */
public void setCumulatedAmt (BigDecimal CumulatedAmt)
{
set_ValueNoCheck ("CumulatedAmt", CumulatedAmt);
}
/** Get Accumulated Amt.
@return Total Amount */
public BigDecimal getCumulatedAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("CumulatedAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Accumulated Qty.
@param CumulatedQty Total Quantity */
public void setCumulatedQty (BigDecimal CumulatedQty)
{
set_ValueNoCheck ("CumulatedQty", CumulatedQty);
}
/** Get Accumulated Qty.
@return Total Quantity */
public BigDecimal getCumulatedQty() 
{
BigDecimal bd = (BigDecimal)get_Value("CumulatedQty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Current Cost Price.
@param CurrentCostPrice The currently used cost price */
public void setCurrentCostPrice (BigDecimal CurrentCostPrice)
{
if (CurrentCostPrice == null) throw new IllegalArgumentException ("CurrentCostPrice is mandatory.");
set_Value ("CurrentCostPrice", CurrentCostPrice);
}
/** Get Current Cost Price.
@return The currently used cost price */
public BigDecimal getCurrentCostPrice() 
{
BigDecimal bd = (BigDecimal)get_Value("CurrentCostPrice");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Current Quantity.
@param CurrentQty Current Quantity */
public void setCurrentQty (BigDecimal CurrentQty)
{
if (CurrentQty == null) throw new IllegalArgumentException ("CurrentQty is mandatory.");
set_Value ("CurrentQty", CurrentQty);
}
/** Get Current Quantity.
@return Current Quantity */
public BigDecimal getCurrentQty() 
{
BigDecimal bd = (BigDecimal)get_Value("CurrentQty");
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
/** Set Future Cost Price.
@param FutureCostPrice Future Cost Price */
public void setFutureCostPrice (BigDecimal FutureCostPrice)
{
if (FutureCostPrice == null) throw new IllegalArgumentException ("FutureCostPrice is mandatory.");
set_Value ("FutureCostPrice", FutureCostPrice);
}
/** Get Future Cost Price.
@return Future Cost Price */
public BigDecimal getFutureCostPrice() 
{
BigDecimal bd = (BigDecimal)get_Value("FutureCostPrice");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Attribute Set Instance.
@param M_AttributeSetInstance_ID Product Attribute Set Instance */
public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
{
if (M_AttributeSetInstance_ID < 0) throw new IllegalArgumentException ("M_AttributeSetInstance_ID is mandatory.");
set_ValueNoCheck ("M_AttributeSetInstance_ID", new Integer(M_AttributeSetInstance_ID));
}
/** Get Attribute Set Instance.
@return Product Attribute Set Instance */
public int getM_AttributeSetInstance_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSetInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Cost Element.
@param M_CostElement_ID Product Cost Element */
public void setM_CostElement_ID (int M_CostElement_ID)
{
if (M_CostElement_ID < 1) throw new IllegalArgumentException ("M_CostElement_ID is mandatory.");
set_ValueNoCheck ("M_CostElement_ID", new Integer(M_CostElement_ID));
}
/** Get Cost Element.
@return Product Cost Element */
public int getM_CostElement_ID() 
{
Integer ii = (Integer)get_Value("M_CostElement_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Cost Type.
@param M_CostType_ID Type of Cost (e.g. Current, Plan, Future) */
public void setM_CostType_ID (int M_CostType_ID)
{
if (M_CostType_ID < 1) throw new IllegalArgumentException ("M_CostType_ID is mandatory.");
set_ValueNoCheck ("M_CostType_ID", new Integer(M_CostType_ID));
}
/** Get Cost Type.
@return Type of Cost (e.g. Current, Plan, Future) */
public int getM_CostType_ID() 
{
Integer ii = (Integer)get_Value("M_CostType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
set_ValueNoCheck ("M_Product_ID", new Integer(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Percent.
@param Percent Percentage */
public void setPercent (int Percent)
{
set_Value ("Percent", new Integer(Percent));
}
/** Get Percent.
@return Percentage */
public int getPercent() 
{
Integer ii = (Integer)get_Value("Percent");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
throw new IllegalArgumentException ("Processed is virtual column");
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
}
