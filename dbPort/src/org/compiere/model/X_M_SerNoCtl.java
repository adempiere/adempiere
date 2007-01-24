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
/** Generated Model for M_SerNoCtl
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_M_SerNoCtl extends PO
{
/** Standard Constructor
@param ctx context
@param M_SerNoCtl_ID id
@param trxName transaction
*/
public X_M_SerNoCtl (Properties ctx, int M_SerNoCtl_ID, String trxName)
{
super (ctx, M_SerNoCtl_ID, trxName);
/** if (M_SerNoCtl_ID == 0)
{
setCurrentNext (0);	// 100
setIncrementNo (0);	// 1
setM_SerNoCtl_ID (0);
setName (null);
setStartNo (0);	// 100
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_SerNoCtl (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=555 */
public static final int Table_ID=MTable.getTable_ID("M_SerNoCtl");

/** TableName=M_SerNoCtl */
public static final String Table_Name="M_SerNoCtl";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_SerNoCtl");

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
StringBuffer sb = new StringBuffer ("X_M_SerNoCtl[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Current Next.
@param CurrentNext The next number to be used */
public void setCurrentNext (int CurrentNext)
{
set_Value ("CurrentNext", Integer.valueOf(CurrentNext));
}
/** Get Current Next.
@return The next number to be used */
public int getCurrentNext() 
{
Integer ii = (Integer)get_Value("CurrentNext");
if (ii == null) return 0;
return ii.intValue();
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
/** Set Increment.
@param IncrementNo The number to increment the last document number by */
public void setIncrementNo (int IncrementNo)
{
set_Value ("IncrementNo", Integer.valueOf(IncrementNo));
}
/** Get Increment.
@return The number to increment the last document number by */
public int getIncrementNo() 
{
Integer ii = (Integer)get_Value("IncrementNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Serial No Control.
@param M_SerNoCtl_ID Product Serial Number Control */
public void setM_SerNoCtl_ID (int M_SerNoCtl_ID)
{
if (M_SerNoCtl_ID < 1) throw new IllegalArgumentException ("M_SerNoCtl_ID is mandatory.");
set_ValueNoCheck ("M_SerNoCtl_ID", Integer.valueOf(M_SerNoCtl_ID));
}
/** Get Serial No Control.
@return Product Serial Number Control */
public int getM_SerNoCtl_ID() 
{
Integer ii = (Integer)get_Value("M_SerNoCtl_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Prefix.
@param Prefix Prefix before the sequence number */
public void setPrefix (String Prefix)
{
if (Prefix != null && Prefix.length() > 10)
{
log.warning("Length > 10 - truncated");
Prefix = Prefix.substring(0,9);
}
set_Value ("Prefix", Prefix);
}
/** Get Prefix.
@return Prefix before the sequence number */
public String getPrefix() 
{
return (String)get_Value("Prefix");
}
/** Set Start No.
@param StartNo Starting number/position */
public void setStartNo (int StartNo)
{
set_Value ("StartNo", Integer.valueOf(StartNo));
}
/** Get Start No.
@return Starting number/position */
public int getStartNo() 
{
Integer ii = (Integer)get_Value("StartNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Suffix.
@param Suffix Suffix after the number */
public void setSuffix (String Suffix)
{
if (Suffix != null && Suffix.length() > 10)
{
log.warning("Length > 10 - truncated");
Suffix = Suffix.substring(0,9);
}
set_Value ("Suffix", Suffix);
}
/** Get Suffix.
@return Suffix after the number */
public String getSuffix() 
{
return (String)get_Value("Suffix");
}
}
