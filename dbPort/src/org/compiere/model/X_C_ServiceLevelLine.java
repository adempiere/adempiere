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
/** Generated Model for C_ServiceLevelLine
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_C_ServiceLevelLine extends PO
{
/** Standard Constructor
@param ctx context
@param C_ServiceLevelLine_ID id
@param trxName transaction
*/
public X_C_ServiceLevelLine (Properties ctx, int C_ServiceLevelLine_ID, String trxName)
{
super (ctx, C_ServiceLevelLine_ID, trxName);
/** if (C_ServiceLevelLine_ID == 0)
{
setC_ServiceLevelLine_ID (0);
setC_ServiceLevel_ID (0);
setServiceDate (new Timestamp(System.currentTimeMillis()));
setServiceLevelProvided (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_ServiceLevelLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=338 */
public static final int Table_ID=MTable.getTable_ID("C_ServiceLevelLine");

/** TableName=C_ServiceLevelLine */
public static final String Table_Name="C_ServiceLevelLine";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_ServiceLevelLine");

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
StringBuffer sb = new StringBuffer ("X_C_ServiceLevelLine[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Service Level Line.
@param C_ServiceLevelLine_ID Product Revenue Recognition Service Level Line */
public void setC_ServiceLevelLine_ID (int C_ServiceLevelLine_ID)
{
if (C_ServiceLevelLine_ID < 1) throw new IllegalArgumentException ("C_ServiceLevelLine_ID is mandatory.");
set_ValueNoCheck ("C_ServiceLevelLine_ID", Integer.valueOf(C_ServiceLevelLine_ID));
}
/** Get Service Level Line.
@return Product Revenue Recognition Service Level Line */
public int getC_ServiceLevelLine_ID() 
{
Integer ii = (Integer)get_Value("C_ServiceLevelLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ServiceLevelLine_ID */
public static final String COLUMNNAME_C_ServiceLevelLine_ID = "C_ServiceLevelLine_ID";
/** Set Service Level.
@param C_ServiceLevel_ID Product Revenue Recognition Service Level  */
public void setC_ServiceLevel_ID (int C_ServiceLevel_ID)
{
if (C_ServiceLevel_ID < 1) throw new IllegalArgumentException ("C_ServiceLevel_ID is mandatory.");
set_ValueNoCheck ("C_ServiceLevel_ID", Integer.valueOf(C_ServiceLevel_ID));
}
/** Get Service Level.
@return Product Revenue Recognition Service Level  */
public int getC_ServiceLevel_ID() 
{
Integer ii = (Integer)get_Value("C_ServiceLevel_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ServiceLevel_ID */
public static final String COLUMNNAME_C_ServiceLevel_ID = "C_ServiceLevel_ID";
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
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_ValueNoCheck ("Processed", Boolean.valueOf(Processed));
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
/** Set Service date.
@param ServiceDate Date service was provided */
public void setServiceDate (Timestamp ServiceDate)
{
if (ServiceDate == null) throw new IllegalArgumentException ("ServiceDate is mandatory.");
set_ValueNoCheck ("ServiceDate", ServiceDate);
}
/** Get Service date.
@return Date service was provided */
public Timestamp getServiceDate() 
{
return (Timestamp)get_Value("ServiceDate");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getServiceDate()));
}
/** Column name ServiceDate */
public static final String COLUMNNAME_ServiceDate = "ServiceDate";
/** Set Quantity Provided.
@param ServiceLevelProvided Quantity of service or product provided */
public void setServiceLevelProvided (BigDecimal ServiceLevelProvided)
{
if (ServiceLevelProvided == null) throw new IllegalArgumentException ("ServiceLevelProvided is mandatory.");
set_ValueNoCheck ("ServiceLevelProvided", ServiceLevelProvided);
}
/** Get Quantity Provided.
@return Quantity of service or product provided */
public BigDecimal getServiceLevelProvided() 
{
BigDecimal bd = (BigDecimal)get_Value("ServiceLevelProvided");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name ServiceLevelProvided */
public static final String COLUMNNAME_ServiceLevelProvided = "ServiceLevelProvided";
}
