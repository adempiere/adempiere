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
/** Generated Model for M_Storage
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_M_Storage extends PO
{
/** Standard Constructor
@param ctx context
@param M_Storage_ID id
@param trxName transaction
*/
public X_M_Storage (Properties ctx, int M_Storage_ID, String trxName)
{
super (ctx, M_Storage_ID, trxName);
/** if (M_Storage_ID == 0)
{
setM_AttributeSetInstance_ID (0);
setM_Locator_ID (0);
setM_Product_ID (0);
setQtyOnHand (Env.ZERO);
setQtyOrdered (Env.ZERO);
setQtyReserved (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_Storage (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=250 */
public static final int Table_ID=MTable.getTable_ID("M_Storage");

/** TableName=M_Storage */
public static final String Table_Name="M_Storage";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_Storage");

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
StringBuffer sb = new StringBuffer ("X_M_Storage[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Date last inventory count.
@param DateLastInventory Date of Last Inventory Count */
public void setDateLastInventory (Timestamp DateLastInventory)
{
set_Value ("DateLastInventory", DateLastInventory);
}
/** Get Date last inventory count.
@return Date of Last Inventory Count */
public Timestamp getDateLastInventory() 
{
return (Timestamp)get_Value("DateLastInventory");
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
/** Set Locator.
@param M_Locator_ID Warehouse Locator */
public void setM_Locator_ID (int M_Locator_ID)
{
if (M_Locator_ID < 1) throw new IllegalArgumentException ("M_Locator_ID is mandatory.");
set_ValueNoCheck ("M_Locator_ID", Integer.valueOf(M_Locator_ID));
}
/** Get Locator.
@return Warehouse Locator */
public int getM_Locator_ID() 
{
Integer ii = (Integer)get_Value("M_Locator_ID");
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
/** Set On Hand Quantity.
@param QtyOnHand On Hand Quantity */
public void setQtyOnHand (BigDecimal QtyOnHand)
{
if (QtyOnHand == null) throw new IllegalArgumentException ("QtyOnHand is mandatory.");
set_ValueNoCheck ("QtyOnHand", QtyOnHand);
}
/** Get On Hand Quantity.
@return On Hand Quantity */
public BigDecimal getQtyOnHand() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyOnHand");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Ordered Quantity.
@param QtyOrdered Ordered Quantity */
public void setQtyOrdered (BigDecimal QtyOrdered)
{
if (QtyOrdered == null) throw new IllegalArgumentException ("QtyOrdered is mandatory.");
set_ValueNoCheck ("QtyOrdered", QtyOrdered);
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
}
