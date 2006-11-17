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
/** Generated Model for M_ProductionPlan
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:56:01.984 */
public class X_M_ProductionPlan extends PO
{
/** Standard Constructor
@param ctx context
@param M_ProductionPlan_ID id
@param trxName transaction
*/
public X_M_ProductionPlan (Properties ctx, int M_ProductionPlan_ID, String trxName)
{
super (ctx, M_ProductionPlan_ID, trxName);
/** if (M_ProductionPlan_ID == 0)
{
setLine (0);	// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM M_ProductionPlan WHERE M_Production_ID=@M_Production_ID@
setM_Locator_ID (0);	// @M_Locator_ID@
setM_Product_ID (0);
setM_ProductionPlan_ID (0);
setM_Production_ID (0);
setProcessed (false);
setProductionQty (Env.ZERO);	// 1
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_ProductionPlan (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=385 */
public static final int Table_ID=385;

/** TableName=M_ProductionPlan */
public static final String Table_Name="M_ProductionPlan";

protected static KeyNamePair Model = new KeyNamePair(385,"M_ProductionPlan");

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
StringBuffer sb = new StringBuffer ("X_M_ProductionPlan[").append(get_ID()).append("]");
return sb.toString();
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getLine()));
}
/** Set Locator.
@param M_Locator_ID Warehouse Locator */
public void setM_Locator_ID (int M_Locator_ID)
{
if (M_Locator_ID < 1) throw new IllegalArgumentException ("M_Locator_ID is mandatory.");
set_Value ("M_Locator_ID", new Integer(M_Locator_ID));
}
/** Get Locator.
@return Warehouse Locator */
public int getM_Locator_ID() 
{
Integer ii = (Integer)get_Value("M_Locator_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** M_Product_ID AD_Reference_ID=211 */
public static final int M_PRODUCT_ID_AD_Reference_ID=211;
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
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
/** Set Production Plan.
@param M_ProductionPlan_ID Plan for how a product is produced */
public void setM_ProductionPlan_ID (int M_ProductionPlan_ID)
{
if (M_ProductionPlan_ID < 1) throw new IllegalArgumentException ("M_ProductionPlan_ID is mandatory.");
set_ValueNoCheck ("M_ProductionPlan_ID", new Integer(M_ProductionPlan_ID));
}
/** Get Production Plan.
@return Plan for how a product is produced */
public int getM_ProductionPlan_ID() 
{
Integer ii = (Integer)get_Value("M_ProductionPlan_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Production.
@param M_Production_ID Plan for producing a product */
public void setM_Production_ID (int M_Production_ID)
{
if (M_Production_ID < 1) throw new IllegalArgumentException ("M_Production_ID is mandatory.");
set_ValueNoCheck ("M_Production_ID", new Integer(M_Production_ID));
}
/** Get Production.
@return Plan for producing a product */
public int getM_Production_ID() 
{
Integer ii = (Integer)get_Value("M_Production_ID");
if (ii == null) return 0;
return ii.intValue();
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
/** Set Production Quantity.
@param ProductionQty Quantity of products to produce */
public void setProductionQty (BigDecimal ProductionQty)
{
if (ProductionQty == null) throw new IllegalArgumentException ("ProductionQty is mandatory.");
set_Value ("ProductionQty", ProductionQty);
}
/** Get Production Quantity.
@return Quantity of products to produce */
public BigDecimal getProductionQty() 
{
BigDecimal bd = (BigDecimal)get_Value("ProductionQty");
if (bd == null) return Env.ZERO;
return bd;
}
}
