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
/** Generated Model for C_LandedCostAllocation
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_C_LandedCostAllocation extends PO
{
/** Standard Constructor
@param ctx context
@param C_LandedCostAllocation_ID id
@param trxName transaction
*/
public X_C_LandedCostAllocation (Properties ctx, int C_LandedCostAllocation_ID, String trxName)
{
super (ctx, C_LandedCostAllocation_ID, trxName);
/** if (C_LandedCostAllocation_ID == 0)
{
setAmt (Env.ZERO);
setBase (Env.ZERO);
setC_InvoiceLine_ID (0);
setC_LandedCostAllocation_ID (0);
setM_CostElement_ID (0);
setM_Product_ID (0);
setQty (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_LandedCostAllocation (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=C_LandedCostAllocation */
public static final String Table_Name="C_LandedCostAllocation";

/** AD_Table_ID=760 */
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
StringBuffer sb = new StringBuffer ("X_C_LandedCostAllocation[").append(get_ID()).append("]");
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
/** Column name Amt */
public static final String COLUMNNAME_Amt = "Amt";
/** Set Base.
@param Base Calculation Base */
public void setBase (BigDecimal Base)
{
if (Base == null) throw new IllegalArgumentException ("Base is mandatory.");
set_Value ("Base", Base);
}
/** Get Base.
@return Calculation Base */
public BigDecimal getBase() 
{
BigDecimal bd = (BigDecimal)get_Value("Base");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Base */
public static final String COLUMNNAME_Base = "Base";
/** Set Invoice Line.
@param C_InvoiceLine_ID Invoice Detail Line */
public void setC_InvoiceLine_ID (int C_InvoiceLine_ID)
{
if (C_InvoiceLine_ID < 1) throw new IllegalArgumentException ("C_InvoiceLine_ID is mandatory.");
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_InvoiceLine_ID()));
}
/** Column name C_InvoiceLine_ID */
public static final String COLUMNNAME_C_InvoiceLine_ID = "C_InvoiceLine_ID";
/** Set Landed Cost Allocation.
@param C_LandedCostAllocation_ID Allocation for Land Costs */
public void setC_LandedCostAllocation_ID (int C_LandedCostAllocation_ID)
{
if (C_LandedCostAllocation_ID < 1) throw new IllegalArgumentException ("C_LandedCostAllocation_ID is mandatory.");
set_ValueNoCheck ("C_LandedCostAllocation_ID", Integer.valueOf(C_LandedCostAllocation_ID));
}
/** Get Landed Cost Allocation.
@return Allocation for Land Costs */
public int getC_LandedCostAllocation_ID() 
{
Integer ii = (Integer)get_Value("C_LandedCostAllocation_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_LandedCostAllocation_ID */
public static final String COLUMNNAME_C_LandedCostAllocation_ID = "C_LandedCostAllocation_ID";
/** Set Attribute Set Instance.
@param M_AttributeSetInstance_ID Product Attribute Set Instance */
public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
{
if (M_AttributeSetInstance_ID <= 0) set_ValueNoCheck ("M_AttributeSetInstance_ID", null);
 else 
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
/** Column name M_AttributeSetInstance_ID */
public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";
/** Set Cost Element.
@param M_CostElement_ID Product Cost Element */
public void setM_CostElement_ID (int M_CostElement_ID)
{
if (M_CostElement_ID < 1) throw new IllegalArgumentException ("M_CostElement_ID is mandatory.");
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
/** Column name Qty */
public static final String COLUMNNAME_Qty = "Qty";
}
