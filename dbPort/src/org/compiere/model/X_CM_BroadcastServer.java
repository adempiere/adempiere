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
/** Generated Model for CM_BroadcastServer
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_CM_BroadcastServer extends PO
{
/** Standard Constructor
@param ctx context
@param CM_BroadcastServer_ID id
@param trxName transaction
*/
public X_CM_BroadcastServer (Properties ctx, int CM_BroadcastServer_ID, String trxName)
{
super (ctx, CM_BroadcastServer_ID, trxName);
/** if (CM_BroadcastServer_ID == 0)
{
setCM_BroadcastServer_ID (0);
setIP_Address (null);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_CM_BroadcastServer (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=893 */
public static final int Table_ID=MTable.getTable_ID("CM_BroadcastServer");

/** TableName=CM_BroadcastServer */
public static final String Table_Name="CM_BroadcastServer";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"CM_BroadcastServer");

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
StringBuffer sb = new StringBuffer ("X_CM_BroadcastServer[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Broadcast Server.
@param CM_BroadcastServer_ID Web Broadcast Server */
public void setCM_BroadcastServer_ID (int CM_BroadcastServer_ID)
{
if (CM_BroadcastServer_ID < 1) throw new IllegalArgumentException ("CM_BroadcastServer_ID is mandatory.");
set_ValueNoCheck ("CM_BroadcastServer_ID", Integer.valueOf(CM_BroadcastServer_ID));
}
/** Get Broadcast Server.
@return Web Broadcast Server */
public int getCM_BroadcastServer_ID() 
{
Integer ii = (Integer)get_Value("CM_BroadcastServer_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CM_BroadcastServer_ID */
public static final String COLUMNNAME_CM_BroadcastServer_ID = "CM_BroadcastServer_ID";
/** Set Web Project.
@param CM_WebProject_ID A web project is the main data container for Containers, URLs, Ads, Media etc. */
public void setCM_WebProject_ID (int CM_WebProject_ID)
{
if (CM_WebProject_ID <= 0) set_Value ("CM_WebProject_ID", null);
 else 
set_Value ("CM_WebProject_ID", Integer.valueOf(CM_WebProject_ID));
}
/** Get Web Project.
@return A web project is the main data container for Containers, URLs, Ads, Media etc. */
public int getCM_WebProject_ID() 
{
Integer ii = (Integer)get_Value("CM_WebProject_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CM_WebProject_ID */
public static final String COLUMNNAME_CM_WebProject_ID = "CM_WebProject_ID";
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
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Column name Help */
public static final String COLUMNNAME_Help = "Help";
/** Set IP Address.
@param IP_Address Defines the IP address to transfer data to */
public void setIP_Address (String IP_Address)
{
if (IP_Address == null) throw new IllegalArgumentException ("IP_Address is mandatory.");
if (IP_Address.length() > 20)
{
log.warning("Length > 20 - truncated");
IP_Address = IP_Address.substring(0,19);
}
set_Value ("IP_Address", IP_Address);
}
/** Get IP Address.
@return Defines the IP address to transfer data to */
public String getIP_Address() 
{
return (String)get_Value("IP_Address");
}
/** Column name IP_Address */
public static final String COLUMNNAME_IP_Address = "IP_Address";
/** Set Last Synchronized.
@param LastSynchronized Date when last synchronized */
public void setLastSynchronized (Timestamp LastSynchronized)
{
set_Value ("LastSynchronized", LastSynchronized);
}
/** Get Last Synchronized.
@return Date when last synchronized */
public Timestamp getLastSynchronized() 
{
return (Timestamp)get_Value("LastSynchronized");
}
/** Column name LastSynchronized */
public static final String COLUMNNAME_LastSynchronized = "LastSynchronized";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
}
