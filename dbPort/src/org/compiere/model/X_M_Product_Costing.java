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
/** Generated Model for M_Product_Costing
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_M_Product_Costing extends PO
{
/** Standard Constructor
@param ctx context
@param M_Product_Costing_ID id
@param trxName transaction
*/
public X_M_Product_Costing (Properties ctx, int M_Product_Costing_ID, String trxName)
{
super (ctx, M_Product_Costing_ID, trxName);
/** if (M_Product_Costing_ID == 0)
{
setC_AcctSchema_ID (0);
setCostAverage (Env.ZERO);
setCostAverageCumAmt (Env.ZERO);
setCostAverageCumQty (Env.ZERO);
setCostStandard (Env.ZERO);
setCostStandardCumAmt (Env.ZERO);
setCostStandardCumQty (Env.ZERO);
setCostStandardPOAmt (Env.ZERO);
setCostStandardPOQty (Env.ZERO);
setCurrentCostPrice (Env.ZERO);
setFutureCostPrice (Env.ZERO);
setM_Product_ID (0);
setPriceLastInv (Env.ZERO);
setPriceLastPO (Env.ZERO);
setTotalInvAmt (Env.ZERO);
setTotalInvQty (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_Product_Costing (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=327 */
public static final int Table_ID=MTable.getTable_ID("M_Product_Costing");

/** TableName=M_Product_Costing */
public static final String Table_Name="M_Product_Costing";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_Product_Costing");

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
StringBuffer sb = new StringBuffer ("X_M_Product_Costing[").append(get_ID()).append("]");
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
/** Set Average Cost.
@param CostAverage Weighted average costs */
public void setCostAverage (BigDecimal CostAverage)
{
if (CostAverage == null) throw new IllegalArgumentException ("CostAverage is mandatory.");
set_ValueNoCheck ("CostAverage", CostAverage);
}
/** Get Average Cost.
@return Weighted average costs */
public BigDecimal getCostAverage() 
{
BigDecimal bd = (BigDecimal)get_Value("CostAverage");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Average Cost Amount Sum.
@param CostAverageCumAmt Cumulative average cost amounts (internal) */
public void setCostAverageCumAmt (BigDecimal CostAverageCumAmt)
{
if (CostAverageCumAmt == null) throw new IllegalArgumentException ("CostAverageCumAmt is mandatory.");
set_ValueNoCheck ("CostAverageCumAmt", CostAverageCumAmt);
}
/** Get Average Cost Amount Sum.
@return Cumulative average cost amounts (internal) */
public BigDecimal getCostAverageCumAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("CostAverageCumAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Average Cost Quantity Sum.
@param CostAverageCumQty Cumulative average cost quantities (internal) */
public void setCostAverageCumQty (BigDecimal CostAverageCumQty)
{
if (CostAverageCumQty == null) throw new IllegalArgumentException ("CostAverageCumQty is mandatory.");
set_ValueNoCheck ("CostAverageCumQty", CostAverageCumQty);
}
/** Get Average Cost Quantity Sum.
@return Cumulative average cost quantities (internal) */
public BigDecimal getCostAverageCumQty() 
{
BigDecimal bd = (BigDecimal)get_Value("CostAverageCumQty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Standard Cost.
@param CostStandard Standard Costs */
public void setCostStandard (BigDecimal CostStandard)
{
if (CostStandard == null) throw new IllegalArgumentException ("CostStandard is mandatory.");
set_ValueNoCheck ("CostStandard", CostStandard);
}
/** Get Standard Cost.
@return Standard Costs */
public BigDecimal getCostStandard() 
{
BigDecimal bd = (BigDecimal)get_Value("CostStandard");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Std Cost Amount Sum.
@param CostStandardCumAmt Standard Cost Invoice Amount Sum (internal) */
public void setCostStandardCumAmt (BigDecimal CostStandardCumAmt)
{
if (CostStandardCumAmt == null) throw new IllegalArgumentException ("CostStandardCumAmt is mandatory.");
set_ValueNoCheck ("CostStandardCumAmt", CostStandardCumAmt);
}
/** Get Std Cost Amount Sum.
@return Standard Cost Invoice Amount Sum (internal) */
public BigDecimal getCostStandardCumAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("CostStandardCumAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Std Cost Quantity Sum.
@param CostStandardCumQty Standard Cost Invoice Quantity Sum (internal) */
public void setCostStandardCumQty (BigDecimal CostStandardCumQty)
{
if (CostStandardCumQty == null) throw new IllegalArgumentException ("CostStandardCumQty is mandatory.");
set_ValueNoCheck ("CostStandardCumQty", CostStandardCumQty);
}
/** Get Std Cost Quantity Sum.
@return Standard Cost Invoice Quantity Sum (internal) */
public BigDecimal getCostStandardCumQty() 
{
BigDecimal bd = (BigDecimal)get_Value("CostStandardCumQty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Std PO Cost Amount Sum.
@param CostStandardPOAmt Standard Cost Purchase Order Amount Sum (internal) */
public void setCostStandardPOAmt (BigDecimal CostStandardPOAmt)
{
if (CostStandardPOAmt == null) throw new IllegalArgumentException ("CostStandardPOAmt is mandatory.");
set_ValueNoCheck ("CostStandardPOAmt", CostStandardPOAmt);
}
/** Get Std PO Cost Amount Sum.
@return Standard Cost Purchase Order Amount Sum (internal) */
public BigDecimal getCostStandardPOAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("CostStandardPOAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Std PO Cost Quantity Sum.
@param CostStandardPOQty Standard Cost Purchase Order Quantity Sum (internal) */
public void setCostStandardPOQty (BigDecimal CostStandardPOQty)
{
if (CostStandardPOQty == null) throw new IllegalArgumentException ("CostStandardPOQty is mandatory.");
set_ValueNoCheck ("CostStandardPOQty", CostStandardPOQty);
}
/** Get Std PO Cost Quantity Sum.
@return Standard Cost Purchase Order Quantity Sum (internal) */
public BigDecimal getCostStandardPOQty() 
{
BigDecimal bd = (BigDecimal)get_Value("CostStandardPOQty");
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
/** Set Last Invoice Price.
@param PriceLastInv Price of the last invoice for the product */
public void setPriceLastInv (BigDecimal PriceLastInv)
{
if (PriceLastInv == null) throw new IllegalArgumentException ("PriceLastInv is mandatory.");
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
if (PriceLastPO == null) throw new IllegalArgumentException ("PriceLastPO is mandatory.");
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
/** Set Total Invoice Amount.
@param TotalInvAmt Cumulative total lifetime invoice amount */
public void setTotalInvAmt (BigDecimal TotalInvAmt)
{
if (TotalInvAmt == null) throw new IllegalArgumentException ("TotalInvAmt is mandatory.");
set_ValueNoCheck ("TotalInvAmt", TotalInvAmt);
}
/** Get Total Invoice Amount.
@return Cumulative total lifetime invoice amount */
public BigDecimal getTotalInvAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("TotalInvAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Total Invoice Quantity.
@param TotalInvQty Cumulative total lifetime invoice quantity */
public void setTotalInvQty (BigDecimal TotalInvQty)
{
if (TotalInvQty == null) throw new IllegalArgumentException ("TotalInvQty is mandatory.");
set_ValueNoCheck ("TotalInvQty", TotalInvQty);
}
/** Get Total Invoice Quantity.
@return Cumulative total lifetime invoice quantity */
public BigDecimal getTotalInvQty() 
{
BigDecimal bd = (BigDecimal)get_Value("TotalInvQty");
if (bd == null) return Env.ZERO;
return bd;
}
}
