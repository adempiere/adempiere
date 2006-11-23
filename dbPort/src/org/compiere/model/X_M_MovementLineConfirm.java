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
/** Generated Model for M_MovementLineConfirm
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_M_MovementLineConfirm extends PO
{
/** Standard Constructor
@param ctx context
@param M_MovementLineConfirm_ID id
@param trxName transaction
*/
public X_M_MovementLineConfirm (Properties ctx, int M_MovementLineConfirm_ID, String trxName)
{
super (ctx, M_MovementLineConfirm_ID, trxName);
/** if (M_MovementLineConfirm_ID == 0)
{
setConfirmedQty (Env.ZERO);
setDifferenceQty (Env.ZERO);
setM_MovementConfirm_ID (0);
setM_MovementLineConfirm_ID (0);
setM_MovementLine_ID (0);
setProcessed (false);
setScrappedQty (Env.ZERO);
setTargetQty (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_MovementLineConfirm (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=737 */
public static final int Table_ID=737;

/** TableName=M_MovementLineConfirm */
public static final String Table_Name="M_MovementLineConfirm";

protected static KeyNamePair Model = new KeyNamePair(737,"M_MovementLineConfirm");

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
StringBuffer sb = new StringBuffer ("X_M_MovementLineConfirm[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Confirmed Quantity.
@param ConfirmedQty Confirmation of a received quantity */
public void setConfirmedQty (BigDecimal ConfirmedQty)
{
if (ConfirmedQty == null) throw new IllegalArgumentException ("ConfirmedQty is mandatory.");
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
/** Set Difference.
@param DifferenceQty Difference Quantity */
public void setDifferenceQty (BigDecimal DifferenceQty)
{
if (DifferenceQty == null) throw new IllegalArgumentException ("DifferenceQty is mandatory.");
set_Value ("DifferenceQty", DifferenceQty);
}
/** Get Difference.
@return Difference Quantity */
public BigDecimal getDifferenceQty() 
{
BigDecimal bd = (BigDecimal)get_Value("DifferenceQty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Phys.Inventory Line.
@param M_InventoryLine_ID Unique line in an Inventory document */
public void setM_InventoryLine_ID (int M_InventoryLine_ID)
{
if (M_InventoryLine_ID <= 0) set_Value ("M_InventoryLine_ID", null);
 else 
set_Value ("M_InventoryLine_ID", new Integer(M_InventoryLine_ID));
}
/** Get Phys.Inventory Line.
@return Unique line in an Inventory document */
public int getM_InventoryLine_ID() 
{
Integer ii = (Integer)get_Value("M_InventoryLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Move Confirm.
@param M_MovementConfirm_ID Inventory Move Confirmation */
public void setM_MovementConfirm_ID (int M_MovementConfirm_ID)
{
if (M_MovementConfirm_ID < 1) throw new IllegalArgumentException ("M_MovementConfirm_ID is mandatory.");
set_ValueNoCheck ("M_MovementConfirm_ID", new Integer(M_MovementConfirm_ID));
}
/** Get Move Confirm.
@return Inventory Move Confirmation */
public int getM_MovementConfirm_ID() 
{
Integer ii = (Integer)get_Value("M_MovementConfirm_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getM_MovementConfirm_ID()));
}
/** Set Move Line Confirm.
@param M_MovementLineConfirm_ID Inventory Move Line Confirmation */
public void setM_MovementLineConfirm_ID (int M_MovementLineConfirm_ID)
{
if (M_MovementLineConfirm_ID < 1) throw new IllegalArgumentException ("M_MovementLineConfirm_ID is mandatory.");
set_ValueNoCheck ("M_MovementLineConfirm_ID", new Integer(M_MovementLineConfirm_ID));
}
/** Get Move Line Confirm.
@return Inventory Move Line Confirmation */
public int getM_MovementLineConfirm_ID() 
{
Integer ii = (Integer)get_Value("M_MovementLineConfirm_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Move Line.
@param M_MovementLine_ID Inventory Move document Line */
public void setM_MovementLine_ID (int M_MovementLine_ID)
{
if (M_MovementLine_ID < 1) throw new IllegalArgumentException ("M_MovementLine_ID is mandatory.");
set_Value ("M_MovementLine_ID", new Integer(M_MovementLine_ID));
}
/** Get Move Line.
@return Inventory Move document Line */
public int getM_MovementLine_ID() 
{
Integer ii = (Integer)get_Value("M_MovementLine_ID");
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
/** Set Scrapped Quantity.
@param ScrappedQty The Quantity scrapped due to QA issues */
public void setScrappedQty (BigDecimal ScrappedQty)
{
if (ScrappedQty == null) throw new IllegalArgumentException ("ScrappedQty is mandatory.");
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
