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
/** Generated Model for M_MovementLine
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_M_MovementLine extends PO
{
/** Standard Constructor
@param ctx context
@param M_MovementLine_ID id
@param trxName transaction
*/
public X_M_MovementLine (Properties ctx, int M_MovementLine_ID, String trxName)
{
super (ctx, M_MovementLine_ID, trxName);
/** if (M_MovementLine_ID == 0)
{
setLine (0);	// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM M_MovementLine WHERE M_Movement_ID=@M_Movement_ID@
setM_AttributeSetInstance_ID (0);
setM_LocatorTo_ID (0);	// @M_LocatorTo_ID@
setM_Locator_ID (0);	// @M_Locator_ID@
setM_MovementLine_ID (0);
setM_Movement_ID (0);
setM_Product_ID (0);
setMovementQty (Env.ZERO);	// 1
setProcessed (false);
setTargetQty (Env.ZERO);	// 0
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_MovementLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=324 */
public static final int Table_ID=MTable.getTable_ID("M_MovementLine");

/** TableName=M_MovementLine */
public static final String Table_Name="M_MovementLine";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_MovementLine");

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
StringBuffer sb = new StringBuffer ("X_M_MovementLine[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Confirmed Quantity.
@param ConfirmedQty Confirmation of a received quantity */
public void setConfirmedQty (BigDecimal ConfirmedQty)
{
set_Value ("ConfirmedQty", ConfirmedQty);
}
/** Get Confirmed Quantity.
@return Confirmation of a received quantity */
public BigDecimal getConfirmedQty() 
{
BigDecimal bd = (BigDecimal)get_Value("ConfirmedQty");
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
/** Set Attribute Set Instance To.
@param M_AttributeSetInstanceTo_ID Target Product Attribute Set Instance */
public void setM_AttributeSetInstanceTo_ID (int M_AttributeSetInstanceTo_ID)
{
if (M_AttributeSetInstanceTo_ID <= 0) set_ValueNoCheck ("M_AttributeSetInstanceTo_ID", null);
 else 
set_ValueNoCheck ("M_AttributeSetInstanceTo_ID", Integer.valueOf(M_AttributeSetInstanceTo_ID));
}
/** Get Attribute Set Instance To.
@return Target Product Attribute Set Instance */
public int getM_AttributeSetInstanceTo_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSetInstanceTo_ID");
if (ii == null) return 0;
return ii.intValue();
}
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

/** M_LocatorTo_ID AD_Reference_ID=191 */
public static final int M_LOCATORTO_ID_AD_Reference_ID=191;
/** Set Locator To.
@param M_LocatorTo_ID Location inventory is moved to */
public void setM_LocatorTo_ID (int M_LocatorTo_ID)
{
if (M_LocatorTo_ID < 1) throw new IllegalArgumentException ("M_LocatorTo_ID is mandatory.");
set_Value ("M_LocatorTo_ID", Integer.valueOf(M_LocatorTo_ID));
}
/** Get Locator To.
@return Location inventory is moved to */
public int getM_LocatorTo_ID() 
{
Integer ii = (Integer)get_Value("M_LocatorTo_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Locator.
@param M_Locator_ID Warehouse Locator */
public void setM_Locator_ID (int M_Locator_ID)
{
if (M_Locator_ID < 1) throw new IllegalArgumentException ("M_Locator_ID is mandatory.");
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
/** Set Move Line.
@param M_MovementLine_ID Inventory Move document Line */
public void setM_MovementLine_ID (int M_MovementLine_ID)
{
if (M_MovementLine_ID < 1) throw new IllegalArgumentException ("M_MovementLine_ID is mandatory.");
set_ValueNoCheck ("M_MovementLine_ID", Integer.valueOf(M_MovementLine_ID));
}
/** Get Move Line.
@return Inventory Move document Line */
public int getM_MovementLine_ID() 
{
Integer ii = (Integer)get_Value("M_MovementLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Inventory Move.
@param M_Movement_ID Movement of Inventory */
public void setM_Movement_ID (int M_Movement_ID)
{
if (M_Movement_ID < 1) throw new IllegalArgumentException ("M_Movement_ID is mandatory.");
set_ValueNoCheck ("M_Movement_ID", Integer.valueOf(M_Movement_ID));
}
/** Get Inventory Move.
@return Movement of Inventory */
public int getM_Movement_ID() 
{
Integer ii = (Integer)get_Value("M_Movement_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** M_Product_ID AD_Reference_ID=171 */
public static final int M_PRODUCT_ID_AD_Reference_ID=171;
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
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
/** Set Movement Quantity.
@param MovementQty Quantity of a product moved. */
public void setMovementQty (BigDecimal MovementQty)
{
if (MovementQty == null) throw new IllegalArgumentException ("MovementQty is mandatory.");
set_Value ("MovementQty", MovementQty);
}
/** Get Movement Quantity.
@return Quantity of a product moved. */
public BigDecimal getMovementQty() 
{
BigDecimal bd = (BigDecimal)get_Value("MovementQty");
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
/** Set Scrapped Quantity.
@param ScrappedQty The Quantity scrapped due to QA issues */
public void setScrappedQty (BigDecimal ScrappedQty)
{
set_Value ("ScrappedQty", ScrappedQty);
}
/** Get Scrapped Quantity.
@return The Quantity scrapped due to QA issues */
public BigDecimal getScrappedQty() 
{
BigDecimal bd = (BigDecimal)get_Value("ScrappedQty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Target Quantity.
@param TargetQty Target Movement Quantity */
public void setTargetQty (BigDecimal TargetQty)
{
if (TargetQty == null) throw new IllegalArgumentException ("TargetQty is mandatory.");
set_Value ("TargetQty", TargetQty);
}
/** Get Target Quantity.
@return Target Movement Quantity */
public BigDecimal getTargetQty() 
{
BigDecimal bd = (BigDecimal)get_Value("TargetQty");
if (bd == null) return Env.ZERO;
return bd;
}
}
