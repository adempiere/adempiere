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
/** Generated Model for M_InOutLineConfirm
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_M_InOutLineConfirm extends PO
{
/** Standard Constructor
@param ctx context
@param M_InOutLineConfirm_ID id
@param trxName transaction
*/
public X_M_InOutLineConfirm (Properties ctx, int M_InOutLineConfirm_ID, String trxName)
{
super (ctx, M_InOutLineConfirm_ID, trxName);
/** if (M_InOutLineConfirm_ID == 0)
{
setConfirmedQty (Env.ZERO);
setM_InOutConfirm_ID (0);
setM_InOutLineConfirm_ID (0);
setM_InOutLine_ID (0);
setProcessed (false);
setTargetQty (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_InOutLineConfirm (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=M_InOutLineConfirm */
public static final String Table_Name="M_InOutLineConfirm";

/** AD_Table_ID=728 */
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
StringBuffer sb = new StringBuffer ("X_M_InOutLineConfirm[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Invoice Line.
@param C_InvoiceLine_ID Invoice Detail Line */
public void setC_InvoiceLine_ID (int C_InvoiceLine_ID)
{
if (C_InvoiceLine_ID <= 0) set_Value ("C_InvoiceLine_ID", null);
 else 
set_Value ("C_InvoiceLine_ID", Integer.valueOf(C_InvoiceLine_ID));
}
/** Get Invoice Line.
@return Invoice Detail Line */
public int getC_InvoiceLine_ID() 
{
Integer ii = (Integer)get_Value("C_InvoiceLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_InvoiceLine_ID */
public static final String COLUMNNAME_C_InvoiceLine_ID = "C_InvoiceLine_ID";
/** Set Confirmation No.
@param ConfirmationNo Confirmation Number */
public void setConfirmationNo (String ConfirmationNo)
{
if (ConfirmationNo != null && ConfirmationNo.length() > 20)
{
log.warning("Length > 20 - truncated");
ConfirmationNo = ConfirmationNo.substring(0,19);
}
set_Value ("ConfirmationNo", ConfirmationNo);
}
/** Get Confirmation No.
@return Confirmation Number */
public String getConfirmationNo() 
{
return (String)get_Value("ConfirmationNo");
}
/** Column name ConfirmationNo */
public static final String COLUMNNAME_ConfirmationNo = "ConfirmationNo";
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
/** Column name ConfirmedQty */
public static final String COLUMNNAME_ConfirmedQty = "ConfirmedQty";
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
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set Difference.
@param DifferenceQty Difference Quantity */
public void setDifferenceQty (BigDecimal DifferenceQty)
{
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
/** Column name DifferenceQty */
public static final String COLUMNNAME_DifferenceQty = "DifferenceQty";
/** Set Ship/Receipt Confirmation.
@param M_InOutConfirm_ID Material Shipment or Receipt Confirmation */
public void setM_InOutConfirm_ID (int M_InOutConfirm_ID)
{
if (M_InOutConfirm_ID < 1) throw new IllegalArgumentException ("M_InOutConfirm_ID is mandatory.");
set_ValueNoCheck ("M_InOutConfirm_ID", Integer.valueOf(M_InOutConfirm_ID));
}
/** Get Ship/Receipt Confirmation.
@return Material Shipment or Receipt Confirmation */
public int getM_InOutConfirm_ID() 
{
Integer ii = (Integer)get_Value("M_InOutConfirm_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_InOutConfirm_ID */
public static final String COLUMNNAME_M_InOutConfirm_ID = "M_InOutConfirm_ID";
/** Set Ship/Receipt Confirmation Line.
@param M_InOutLineConfirm_ID Material Shipment or Receipt Confirmation Line */
public void setM_InOutLineConfirm_ID (int M_InOutLineConfirm_ID)
{
if (M_InOutLineConfirm_ID < 1) throw new IllegalArgumentException ("M_InOutLineConfirm_ID is mandatory.");
set_ValueNoCheck ("M_InOutLineConfirm_ID", Integer.valueOf(M_InOutLineConfirm_ID));
}
/** Get Ship/Receipt Confirmation Line.
@return Material Shipment or Receipt Confirmation Line */
public int getM_InOutLineConfirm_ID() 
{
Integer ii = (Integer)get_Value("M_InOutLineConfirm_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_InOutLineConfirm_ID */
public static final String COLUMNNAME_M_InOutLineConfirm_ID = "M_InOutLineConfirm_ID";
/** Set Shipment/Receipt Line.
@param M_InOutLine_ID Line on Shipment or Receipt document */
public void setM_InOutLine_ID (int M_InOutLine_ID)
{
if (M_InOutLine_ID < 1) throw new IllegalArgumentException ("M_InOutLine_ID is mandatory.");
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getM_InOutLine_ID()));
}
/** Column name M_InOutLine_ID */
public static final String COLUMNNAME_M_InOutLine_ID = "M_InOutLine_ID";
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
/** Column name M_InventoryLine_ID */
public static final String COLUMNNAME_M_InventoryLine_ID = "M_InventoryLine_ID";
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
/** Column name Processed */
public static final String COLUMNNAME_Processed = "Processed";
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
/** Column name ScrappedQty */
public static final String COLUMNNAME_ScrappedQty = "ScrappedQty";
/** Set Target Quantity.
@param TargetQty Target Movement Quantity */
public void setTargetQty (BigDecimal TargetQty)
{
if (TargetQty == null) throw new IllegalArgumentException ("TargetQty is mandatory.");
set_ValueNoCheck ("TargetQty", TargetQty);
}
/** Get Target Quantity.
@return Target Movement Quantity */
public BigDecimal getTargetQty() 
{
BigDecimal bd = (BigDecimal)get_Value("TargetQty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name TargetQty */
public static final String COLUMNNAME_TargetQty = "TargetQty";
}
