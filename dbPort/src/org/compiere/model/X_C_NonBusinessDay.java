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
/** Generated Model for C_NonBusinessDay
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_C_NonBusinessDay extends PO
{
/** Standard Constructor
@param ctx context
@param C_NonBusinessDay_ID id
@param trxName transaction
*/
public X_C_NonBusinessDay (Properties ctx, int C_NonBusinessDay_ID, String trxName)
{
super (ctx, C_NonBusinessDay_ID, trxName);
/** if (C_NonBusinessDay_ID == 0)
{
setC_Calendar_ID (0);
setC_NonBusinessDay_ID (0);
setDate1 (new Timestamp(System.currentTimeMillis()));
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_NonBusinessDay (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=163 */
public static final int Table_ID=MTable.getTable_ID("C_NonBusinessDay");

/** TableName=C_NonBusinessDay */
public static final String Table_Name="C_NonBusinessDay";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_NonBusinessDay");

protected BigDecimal accessLevel = BigDecimal.valueOf(6);
/** AccessLevel
@return 6 - System - Client 
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
StringBuffer sb = new StringBuffer ("X_C_NonBusinessDay[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Calendar.
@param C_Calendar_ID Accounting Calendar Name */
public void setC_Calendar_ID (int C_Calendar_ID)
{
if (C_Calendar_ID < 1) throw new IllegalArgumentException ("C_Calendar_ID is mandatory.");
set_ValueNoCheck ("C_Calendar_ID", Integer.valueOf(C_Calendar_ID));
}
/** Get Calendar.
@return Accounting Calendar Name */
public int getC_Calendar_ID() 
{
Integer ii = (Integer)get_Value("C_Calendar_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Non Business Day.
@param C_NonBusinessDay_ID Day on which business is not transacted */
public void setC_NonBusinessDay_ID (int C_NonBusinessDay_ID)
{
if (C_NonBusinessDay_ID < 1) throw new IllegalArgumentException ("C_NonBusinessDay_ID is mandatory.");
set_ValueNoCheck ("C_NonBusinessDay_ID", Integer.valueOf(C_NonBusinessDay_ID));
}
/** Get Non Business Day.
@return Day on which business is not transacted */
public int getC_NonBusinessDay_ID() 
{
Integer ii = (Integer)get_Value("C_NonBusinessDay_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Date.
@param Date1 Date when business is not conducted */
public void setDate1 (Timestamp Date1)
{
if (Date1 == null) throw new IllegalArgumentException ("Date1 is mandatory.");
set_Value ("Date1", Date1);
}
/** Get Date.
@return Date when business is not conducted */
public Timestamp getDate1() 
{
return (Timestamp)get_Value("Date1");
}
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name != null && Name.length() > 60)
{
log.warning("Length > 60 - truncated");
Name = Name.substring(0,59);
}
set_Value ("Name", Name);
}
/** Get Name.
@return Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getName());
}
}
