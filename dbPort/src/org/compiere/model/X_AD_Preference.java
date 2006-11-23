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
/** Generated Model for AD_Preference
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_AD_Preference extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Preference_ID id
@param trxName transaction
*/
public X_AD_Preference (Properties ctx, int AD_Preference_ID, String trxName)
{
super (ctx, AD_Preference_ID, trxName);
/** if (AD_Preference_ID == 0)
{
setAD_Preference_ID (0);
setAttribute (null);
setValue (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Preference (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=195 */
public static final int Table_ID=195;

/** TableName=AD_Preference */
public static final String Table_Name="AD_Preference";

protected static KeyNamePair Model = new KeyNamePair(195,"AD_Preference");

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
StringBuffer sb = new StringBuffer ("X_AD_Preference[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Preference.
@param AD_Preference_ID Personal Value Preference */
public void setAD_Preference_ID (int AD_Preference_ID)
{
if (AD_Preference_ID < 1) throw new IllegalArgumentException ("AD_Preference_ID is mandatory.");
set_ValueNoCheck ("AD_Preference_ID", new Integer(AD_Preference_ID));
}
/** Get Preference.
@return Personal Value Preference */
public int getAD_Preference_ID() 
{
Integer ii = (Integer)get_Value("AD_Preference_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID <= 0) set_Value ("AD_User_ID", null);
 else 
set_Value ("AD_User_ID", new Integer(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Window.
@param AD_Window_ID Data entry or display window */
public void setAD_Window_ID (int AD_Window_ID)
{
if (AD_Window_ID <= 0) set_Value ("AD_Window_ID", null);
 else 
set_Value ("AD_Window_ID", new Integer(AD_Window_ID));
}
/** Get Window.
@return Data entry or display window */
public int getAD_Window_ID() 
{
Integer ii = (Integer)get_Value("AD_Window_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Attribute.
@param Attribute Attribute */
public void setAttribute (String Attribute)
{
if (Attribute == null) throw new IllegalArgumentException ("Attribute is mandatory.");
if (Attribute.length() > 60)
{
log.warning("Length > 60 - truncated");
Attribute = Attribute.substring(0,59);
}
set_Value ("Attribute", Attribute);
}
/** Get Attribute.
@return Attribute */
public String getAttribute() 
{
return (String)get_Value("Attribute");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getAttribute());
}
/** Set Search Key.
@param Value Search key for the record in the format required - must be unique */
public void setValue (String Value)
{
if (Value == null) throw new IllegalArgumentException ("Value is mandatory.");
if (Value.length() > 60)
{
log.warning("Length > 60 - truncated");
Value = Value.substring(0,59);
}
set_Value ("Value", Value);
}
/** Get Search Key.
@return Search key for the record in the format required - must be unique */
public String getValue() 
{
return (String)get_Value("Value");
}
}
