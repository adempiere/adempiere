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
/** Generated Model for R_RequestUpdate
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_R_RequestUpdate extends PO
{
/** Standard Constructor
@param ctx context
@param R_RequestUpdate_ID id
@param trxName transaction
*/
public X_R_RequestUpdate (Properties ctx, int R_RequestUpdate_ID, String trxName)
{
super (ctx, R_RequestUpdate_ID, trxName);
/** if (R_RequestUpdate_ID == 0)
{
setConfidentialTypeEntry (null);
setR_RequestUpdate_ID (0);
setR_Request_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_R_RequestUpdate (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=802 */
public static final int Table_ID=MTable.getTable_ID("R_RequestUpdate");

/** TableName=R_RequestUpdate */
public static final String Table_Name="R_RequestUpdate";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"R_RequestUpdate");

protected BigDecimal accessLevel = BigDecimal.valueOf(7);
/** AccessLevel
@return 7 - System - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_R_RequestUpdate[").append(get_ID()).append("]");
return sb.toString();
}

/** ConfidentialTypeEntry AD_Reference_ID=340 */
public static final int CONFIDENTIALTYPEENTRY_AD_Reference_ID=340;
/** Public Information = A */
public static final String CONFIDENTIALTYPEENTRY_PublicInformation = "A";
/** Partner Confidential = C */
public static final String CONFIDENTIALTYPEENTRY_PartnerConfidential = "C";
/** Internal = I */
public static final String CONFIDENTIALTYPEENTRY_Internal = "I";
/** Private Information = P */
public static final String CONFIDENTIALTYPEENTRY_PrivateInformation = "P";
/** Set Entry Confidentiality.
@param ConfidentialTypeEntry Confidentiality of the individual entry */
public void setConfidentialTypeEntry (String ConfidentialTypeEntry)
{
if (ConfidentialTypeEntry == null) throw new IllegalArgumentException ("ConfidentialTypeEntry is mandatory");
if (ConfidentialTypeEntry.equals("A") || ConfidentialTypeEntry.equals("C") || ConfidentialTypeEntry.equals("I") || ConfidentialTypeEntry.equals("P"));
 else throw new IllegalArgumentException ("ConfidentialTypeEntry Invalid value - " + ConfidentialTypeEntry + " - Reference_ID=340 - A - C - I - P");
if (ConfidentialTypeEntry.length() > 1)
{
log.warning("Length > 1 - truncated");
ConfidentialTypeEntry = ConfidentialTypeEntry.substring(0,0);
}
set_Value ("ConfidentialTypeEntry", ConfidentialTypeEntry);
}
/** Get Entry Confidentiality.
@return Confidentiality of the individual entry */
public String getConfidentialTypeEntry() 
{
return (String)get_Value("ConfidentialTypeEntry");
}
/** Column name ConfidentialTypeEntry */
public static final String COLUMNNAME_ConfidentialTypeEntry = "ConfidentialTypeEntry";
/** Set End Time.
@param EndTime End of the time span */
public void setEndTime (Timestamp EndTime)
{
set_Value ("EndTime", EndTime);
}
/** Get End Time.
@return End of the time span */
public Timestamp getEndTime() 
{
return (Timestamp)get_Value("EndTime");
}
/** Column name EndTime */
public static final String COLUMNNAME_EndTime = "EndTime";

/** M_ProductSpent_ID AD_Reference_ID=162 */
public static final int M_PRODUCTSPENT_ID_AD_Reference_ID=162;
/** Set Product Used.
@param M_ProductSpent_ID Product/Resource/Service used in Request */
public void setM_ProductSpent_ID (int M_ProductSpent_ID)
{
if (M_ProductSpent_ID <= 0) set_Value ("M_ProductSpent_ID", null);
 else 
set_Value ("M_ProductSpent_ID", Integer.valueOf(M_ProductSpent_ID));
}
/** Get Product Used.
@return Product/Resource/Service used in Request */
public int getM_ProductSpent_ID() 
{
Integer ii = (Integer)get_Value("M_ProductSpent_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_ProductSpent_ID */
public static final String COLUMNNAME_M_ProductSpent_ID = "M_ProductSpent_ID";
/** Set Quantity Invoiced.
@param QtyInvoiced Invoiced Quantity */
public void setQtyInvoiced (BigDecimal QtyInvoiced)
{
set_Value ("QtyInvoiced", QtyInvoiced);
}
/** Get Quantity Invoiced.
@return Invoiced Quantity */
public BigDecimal getQtyInvoiced() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyInvoiced");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtyInvoiced */
public static final String COLUMNNAME_QtyInvoiced = "QtyInvoiced";
/** Set Quantity Used.
@param QtySpent Quantity used for this event */
public void setQtySpent (BigDecimal QtySpent)
{
set_Value ("QtySpent", QtySpent);
}
/** Get Quantity Used.
@return Quantity used for this event */
public BigDecimal getQtySpent() 
{
BigDecimal bd = (BigDecimal)get_Value("QtySpent");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtySpent */
public static final String COLUMNNAME_QtySpent = "QtySpent";
/** Set Request Update.
@param R_RequestUpdate_ID Request Updates */
public void setR_RequestUpdate_ID (int R_RequestUpdate_ID)
{
if (R_RequestUpdate_ID < 1) throw new IllegalArgumentException ("R_RequestUpdate_ID is mandatory.");
set_ValueNoCheck ("R_RequestUpdate_ID", Integer.valueOf(R_RequestUpdate_ID));
}
/** Get Request Update.
@return Request Updates */
public int getR_RequestUpdate_ID() 
{
Integer ii = (Integer)get_Value("R_RequestUpdate_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getR_RequestUpdate_ID()));
}
/** Column name R_RequestUpdate_ID */
public static final String COLUMNNAME_R_RequestUpdate_ID = "R_RequestUpdate_ID";
/** Set Request.
@param R_Request_ID Request from a Business Partner or Prospect */
public void setR_Request_ID (int R_Request_ID)
{
if (R_Request_ID < 1) throw new IllegalArgumentException ("R_Request_ID is mandatory.");
set_ValueNoCheck ("R_Request_ID", Integer.valueOf(R_Request_ID));
}
/** Get Request.
@return Request from a Business Partner or Prospect */
public int getR_Request_ID() 
{
Integer ii = (Integer)get_Value("R_Request_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_Request_ID */
public static final String COLUMNNAME_R_Request_ID = "R_Request_ID";
/** Set Result.
@param Result Result of the action taken */
public void setResult (String Result)
{
if (Result != null && Result.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Result = Result.substring(0,1999);
}
set_ValueNoCheck ("Result", Result);
}
/** Get Result.
@return Result of the action taken */
public String getResult() 
{
return (String)get_Value("Result");
}
/** Column name Result */
public static final String COLUMNNAME_Result = "Result";
/** Set Start Time.
@param StartTime Time started */
public void setStartTime (Timestamp StartTime)
{
set_Value ("StartTime", StartTime);
}
/** Get Start Time.
@return Time started */
public Timestamp getStartTime() 
{
return (Timestamp)get_Value("StartTime");
}
/** Column name StartTime */
public static final String COLUMNNAME_StartTime = "StartTime";
}
