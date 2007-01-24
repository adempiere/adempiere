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
/** Generated Model for AD_ClientShare
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_AD_ClientShare extends PO
{
/** Standard Constructor
@param ctx context
@param AD_ClientShare_ID id
@param trxName transaction
*/
public X_AD_ClientShare (Properties ctx, int AD_ClientShare_ID, String trxName)
{
super (ctx, AD_ClientShare_ID, trxName);
/** if (AD_ClientShare_ID == 0)
{
setAD_ClientShare_ID (0);
setAD_Table_ID (0);
setName (null);
setShareType (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_ClientShare (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=827 */
public static final int Table_ID=MTable.getTable_ID("AD_ClientShare");

/** TableName=AD_ClientShare */
public static final String Table_Name="AD_ClientShare";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_ClientShare");

protected BigDecimal accessLevel = BigDecimal.valueOf(2);
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
StringBuffer sb = new StringBuffer ("X_AD_ClientShare[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Client Share.
@param AD_ClientShare_ID Force (not) sharing of client/org entities */
public void setAD_ClientShare_ID (int AD_ClientShare_ID)
{
if (AD_ClientShare_ID < 1) throw new IllegalArgumentException ("AD_ClientShare_ID is mandatory.");
set_ValueNoCheck ("AD_ClientShare_ID", Integer.valueOf(AD_ClientShare_ID));
}
/** Get Client Share.
@return Force (not) sharing of client/org entities */
public int getAD_ClientShare_ID() 
{
Integer ii = (Integer)get_Value("AD_ClientShare_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID < 1) throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
set_Value ("AD_Table_ID", Integer.valueOf(AD_Table_ID));
}
/** Get Table.
@return Database Table information */
public int getAD_Table_ID() 
{
Integer ii = (Integer)get_Value("AD_Table_ID");
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
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 120)
{
log.warning("Length > 120 - truncated");
Name = Name.substring(0,119);
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

/** ShareType AD_Reference_ID=365 */
public static final int SHARETYPE_AD_Reference_ID=365;
/** Client (all shared) = C */
public static final String SHARETYPE_ClientAllShared = "C";
/** Org (not shared) = O */
public static final String SHARETYPE_OrgNotShared = "O";
/** Client or Org = x */
public static final String SHARETYPE_ClientOrOrg = "x";
/** Set Share Type.
@param ShareType Type of sharing */
public void setShareType (String ShareType)
{
if (ShareType == null) throw new IllegalArgumentException ("ShareType is mandatory");
if (ShareType.equals("C") || ShareType.equals("O") || ShareType.equals("x"));
 else throw new IllegalArgumentException ("ShareType Invalid value - " + ShareType + " - Reference_ID=365 - C - O - x");
if (ShareType.length() > 1)
{
log.warning("Length > 1 - truncated");
ShareType = ShareType.substring(0,0);
}
set_Value ("ShareType", ShareType);
}
/** Get Share Type.
@return Type of sharing */
public String getShareType() 
{
return (String)get_Value("ShareType");
}
}
