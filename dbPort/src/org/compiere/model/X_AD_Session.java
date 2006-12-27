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
/** Generated Model for AD_Session
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_AD_Session extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Session_ID id
@param trxName transaction
*/
public X_AD_Session (Properties ctx, int AD_Session_ID, String trxName)
{
super (ctx, AD_Session_ID, trxName);
/** if (AD_Session_ID == 0)
{
setAD_Session_ID (0);
setProcessed (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Session (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=566 */
public static final int Table_ID=MTable.getTable_ID("AD_Session");

/** TableName=AD_Session */
public static final String Table_Name="AD_Session";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Session");

protected BigDecimal accessLevel = new BigDecimal(6);
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
StringBuffer sb = new StringBuffer ("X_AD_Session[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Session.
@param AD_Session_ID User Session Online or Web */
public void setAD_Session_ID (int AD_Session_ID)
{
if (AD_Session_ID < 1) throw new IllegalArgumentException ("AD_Session_ID is mandatory.");
set_ValueNoCheck ("AD_Session_ID", Integer.valueOf(AD_Session_ID));
}
/** Get Session.
@return User Session Online or Web */
public int getAD_Session_ID() 
{
Integer ii = (Integer)get_Value("AD_Session_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_Session_ID()));
}
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
/** Set Remote Addr.
@param Remote_Addr Remote Address */
public void setRemote_Addr (String Remote_Addr)
{
if (Remote_Addr != null && Remote_Addr.length() > 60)
{
log.warning("Length > 60 - truncated");
Remote_Addr = Remote_Addr.substring(0,59);
}
set_ValueNoCheck ("Remote_Addr", Remote_Addr);
}
/** Get Remote Addr.
@return Remote Address */
public String getRemote_Addr() 
{
return (String)get_Value("Remote_Addr");
}
/** Set Remote Host.
@param Remote_Host Remote host Info */
public void setRemote_Host (String Remote_Host)
{
if (Remote_Host != null && Remote_Host.length() > 120)
{
log.warning("Length > 120 - truncated");
Remote_Host = Remote_Host.substring(0,119);
}
set_ValueNoCheck ("Remote_Host", Remote_Host);
}
/** Get Remote Host.
@return Remote host Info */
public String getRemote_Host() 
{
return (String)get_Value("Remote_Host");
}
/** Set Web Session.
@param WebSession Web Session ID */
public void setWebSession (String WebSession)
{
if (WebSession != null && WebSession.length() > 40)
{
log.warning("Length > 40 - truncated");
WebSession = WebSession.substring(0,39);
}
set_ValueNoCheck ("WebSession", WebSession);
}
/** Get Web Session.
@return Web Session ID */
public String getWebSession() 
{
return (String)get_Value("WebSession");
}
}
