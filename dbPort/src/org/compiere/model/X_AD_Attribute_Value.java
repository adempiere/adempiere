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
/** Generated Model for AD_Attribute_Value
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_AD_Attribute_Value extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Attribute_Value_ID id
@param trxName transaction
*/
public X_AD_Attribute_Value (Properties ctx, int AD_Attribute_Value_ID, String trxName)
{
super (ctx, AD_Attribute_Value_ID, trxName);
/** if (AD_Attribute_Value_ID == 0)
{
setAD_Attribute_ID (0);
setRecord_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Attribute_Value (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=406 */
public static final int Table_ID=406;

/** TableName=AD_Attribute_Value */
public static final String Table_Name="AD_Attribute_Value";

protected static KeyNamePair Model = new KeyNamePair(406,"AD_Attribute_Value");

protected BigDecimal accessLevel = new BigDecimal(7);
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
StringBuffer sb = new StringBuffer ("X_AD_Attribute_Value[").append(get_ID()).append("]");
return sb.toString();
}
/** Set System Attribute.
@param AD_Attribute_ID System Attribute */
public void setAD_Attribute_ID (int AD_Attribute_ID)
{
if (AD_Attribute_ID < 1) throw new IllegalArgumentException ("AD_Attribute_ID is mandatory.");
set_ValueNoCheck ("AD_Attribute_ID", new Integer(AD_Attribute_ID));
}
/** Get System Attribute.
@return System Attribute */
public int getAD_Attribute_ID() 
{
Integer ii = (Integer)get_Value("AD_Attribute_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Record ID.
@param Record_ID Direct internal record ID */
public void setRecord_ID (int Record_ID)
{
if (Record_ID < 0) throw new IllegalArgumentException ("Record_ID is mandatory.");
set_ValueNoCheck ("Record_ID", new Integer(Record_ID));
}
/** Get Record ID.
@return Direct internal record ID */
public int getRecord_ID() 
{
Integer ii = (Integer)get_Value("Record_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set V_Date.
@param V_Date V_Date */
public void setV_Date (Timestamp V_Date)
{
set_Value ("V_Date", V_Date);
}
/** Get V_Date.
@return V_Date */
public Timestamp getV_Date() 
{
return (Timestamp)get_Value("V_Date");
}
/** Set V_Number.
@param V_Number V_Number */
public void setV_Number (String V_Number)
{
if (V_Number != null && V_Number.length() > 22)
{
log.warning("Length > 22 - truncated");
V_Number = V_Number.substring(0,21);
}
set_Value ("V_Number", V_Number);
}
/** Get V_Number.
@return V_Number */
public String getV_Number() 
{
return (String)get_Value("V_Number");
}
/** Set V_String.
@param V_String V_String */
public void setV_String (String V_String)
{
if (V_String != null && V_String.length() > 2000)
{
log.warning("Length > 2000 - truncated");
V_String = V_String.substring(0,1999);
}
set_Value ("V_String", V_String);
}
/** Get V_String.
@return V_String */
public String getV_String() 
{
return (String)get_Value("V_String");
}
}
