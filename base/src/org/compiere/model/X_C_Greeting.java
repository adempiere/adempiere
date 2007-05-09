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
/** Generated Model for C_Greeting
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_C_Greeting extends PO
{
/** Standard Constructor
@param ctx context
@param C_Greeting_ID id
@param trxName transaction
*/
public X_C_Greeting (Properties ctx, int C_Greeting_ID, String trxName)
{
super (ctx, C_Greeting_ID, trxName);
/** if (C_Greeting_ID == 0)
{
setC_Greeting_ID (0);
setIsDefault (false);
setIsFirstNameOnly (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Greeting (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=346 */
public static final int Table_ID=MTable.getTable_ID("C_Greeting");

/** TableName=C_Greeting */
public static final String Table_Name="C_Greeting";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_Greeting");

protected BigDecimal accessLevel = BigDecimal.valueOf(3);
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
StringBuffer sb = new StringBuffer ("X_C_Greeting[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Greeting.
@param C_Greeting_ID Greeting to print on correspondence */
public void setC_Greeting_ID (int C_Greeting_ID)
{
if (C_Greeting_ID < 1) throw new IllegalArgumentException ("C_Greeting_ID is mandatory.");
set_ValueNoCheck ("C_Greeting_ID", Integer.valueOf(C_Greeting_ID));
}
/** Get Greeting.
@return Greeting to print on correspondence */
public int getC_Greeting_ID() 
{
Integer ii = (Integer)get_Value("C_Greeting_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Greeting_ID */
public static final String COLUMNNAME_C_Greeting_ID = "C_Greeting_ID";
/** Set Greeting.
@param Greeting For letters, e.g. "Dear 
{
0}
" or "Dear Mr. 
{
0}
" - At runtime, "
{
0}
" is replaced by the name */
public void setGreeting (String Greeting)
{
if (Greeting != null && Greeting.length() > 60)
{
log.warning("Length > 60 - truncated");
Greeting = Greeting.substring(0,59);
}
set_Value ("Greeting", Greeting);
}
/** Get Greeting.
@return For letters, e.g. "Dear 
{
0}
" or "Dear Mr. 
{
0}
" - At runtime, "
{
0}
" is replaced by the name */
public String getGreeting() 
{
return (String)get_Value("Greeting");
}
/** Column name Greeting */
public static final String COLUMNNAME_Greeting = "Greeting";
/** Set Default.
@param IsDefault Default value */
public void setIsDefault (boolean IsDefault)
{
set_Value ("IsDefault", Boolean.valueOf(IsDefault));
}
/** Get Default.
@return Default value */
public boolean isDefault() 
{
Object oo = get_Value("IsDefault");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsDefault */
public static final String COLUMNNAME_IsDefault = "IsDefault";
/** Set First name only.
@param IsFirstNameOnly Print only the first name in greetings */
public void setIsFirstNameOnly (boolean IsFirstNameOnly)
{
set_Value ("IsFirstNameOnly", Boolean.valueOf(IsFirstNameOnly));
}
/** Get First name only.
@return Print only the first name in greetings */
public boolean isFirstNameOnly() 
{
Object oo = get_Value("IsFirstNameOnly");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsFirstNameOnly */
public static final String COLUMNNAME_IsFirstNameOnly = "IsFirstNameOnly";
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 60)
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
}
