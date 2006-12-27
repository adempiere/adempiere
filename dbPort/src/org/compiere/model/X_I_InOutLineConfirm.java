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
/** Generated Model for I_InOutLineConfirm
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_I_InOutLineConfirm extends PO
{
/** Standard Constructor
@param ctx context
@param I_InOutLineConfirm_ID id
@param trxName transaction
*/
public X_I_InOutLineConfirm (Properties ctx, int I_InOutLineConfirm_ID, String trxName)
{
super (ctx, I_InOutLineConfirm_ID, trxName);
/** if (I_InOutLineConfirm_ID == 0)
{
setConfirmationNo (null);
setConfirmedQty (Env.ZERO);
setDifferenceQty (Env.ZERO);
setI_InOutLineConfirm_ID (0);
setI_IsImported (false);
setM_InOutLineConfirm_ID (0);
setScrappedQty (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_I_InOutLineConfirm (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=740 */
public static final int Table_ID=MTable.getTable_ID("I_InOutLineConfirm");

/** TableName=I_InOutLineConfirm */
public static final String Table_Name="I_InOutLineConfirm";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"I_InOutLineConfirm");

protected BigDecimal accessLevel = new BigDecimal(2);
/** AccessLevel
@return 2 - Client 
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
StringBuffer sb = new StringBuffer ("X_I_InOutLineConfirm[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Confirmation No.
@param ConfirmationNo Confirmation Number */
public void setConfirmationNo (String ConfirmationNo)
{
if (ConfirmationNo == null) throw new IllegalArgumentException ("ConfirmationNo is mandatory.");
if (ConfirmationNo.length() > 20)
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
/** Set Import Error Message.
@param I_ErrorMsg Messages generated from import process */
public void setI_ErrorMsg (String I_ErrorMsg)
{
if (I_ErrorMsg != null && I_ErrorMsg.length() > 2000)
{
log.warning("Length > 2000 - truncated");
I_ErrorMsg = I_ErrorMsg.substring(0,1999);
}
set_Value ("I_ErrorMsg", I_ErrorMsg);
}
/** Get Import Error Message.
@return Messages generated from import process */
public String getI_ErrorMsg() 
{
return (String)get_Value("I_ErrorMsg");
}
/** Set Ship/Receipt Confirmation Import Line.
@param I_InOutLineConfirm_ID Material Shipment or Receipt Confirmation Import Line */
public void setI_InOutLineConfirm_ID (int I_InOutLineConfirm_ID)
{
if (I_InOutLineConfirm_ID < 1) throw new IllegalArgumentException ("I_InOutLineConfirm_ID is mandatory.");
set_ValueNoCheck ("I_InOutLineConfirm_ID", Integer.valueOf(I_InOutLineConfirm_ID));
}
/** Get Ship/Receipt Confirmation Import Line.
@return Material Shipment or Receipt Confirmation Import Line */
public int getI_InOutLineConfirm_ID() 
{
Integer ii = (Integer)get_Value("I_InOutLineConfirm_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getI_InOutLineConfirm_ID()));
}
/** Set Imported.
@param I_IsImported Has this import been processed */
public void setI_IsImported (boolean I_IsImported)
{
set_Value ("I_IsImported", Boolean.valueOf(I_IsImported));
}
/** Get Imported.
@return Has this import been processed */
public boolean isI_IsImported() 
{
Object oo = get_Value("I_IsImported");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Ship/Receipt Confirmation Line.
@param M_InOutLineConfirm_ID Material Shipment or Receipt Confirmation Line */
public void setM_InOutLineConfirm_ID (int M_InOutLineConfirm_ID)
{
if (M_InOutLineConfirm_ID < 1) throw new IllegalArgumentException ("M_InOutLineConfirm_ID is mandatory.");
set_Value ("M_InOutLineConfirm_ID", Integer.valueOf(M_InOutLineConfirm_ID));
}
/** Get Ship/Receipt Confirmation Line.
@return Material Shipment or Receipt Confirmation Line */
public int getM_InOutLineConfirm_ID() 
{
Integer ii = (Integer)get_Value("M_InOutLineConfirm_ID");
if (ii == null) return 0;
return ii.intValue();
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
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", Boolean.valueOf(Processing));
}
/** Get Process Now.
@return Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
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
}
