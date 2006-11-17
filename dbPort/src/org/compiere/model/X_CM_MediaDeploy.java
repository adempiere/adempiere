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
/** Generated Model for CM_MediaDeploy
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:55.875 */
public class X_CM_MediaDeploy extends PO
{
/** Standard Constructor
@param ctx context
@param CM_MediaDeploy_ID id
@param trxName transaction
*/
public X_CM_MediaDeploy (Properties ctx, int CM_MediaDeploy_ID, String trxName)
{
super (ctx, CM_MediaDeploy_ID, trxName);
/** if (CM_MediaDeploy_ID == 0)
{
setCM_MediaDeploy_ID (0);
setCM_Media_ID (0);
setCM_Media_Server_ID (0);
setIsDeployed (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_CM_MediaDeploy (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=892 */
public static final int Table_ID=892;

/** TableName=CM_MediaDeploy */
public static final String Table_Name="CM_MediaDeploy";

protected static KeyNamePair Model = new KeyNamePair(892,"CM_MediaDeploy");

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
StringBuffer sb = new StringBuffer ("X_CM_MediaDeploy[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Media Deploy.
@param CM_MediaDeploy_ID Media Deployment Log */
public void setCM_MediaDeploy_ID (int CM_MediaDeploy_ID)
{
if (CM_MediaDeploy_ID < 1) throw new IllegalArgumentException ("CM_MediaDeploy_ID is mandatory.");
set_ValueNoCheck ("CM_MediaDeploy_ID", new Integer(CM_MediaDeploy_ID));
}
/** Get Media Deploy.
@return Media Deployment Log */
public int getCM_MediaDeploy_ID() 
{
Integer ii = (Integer)get_Value("CM_MediaDeploy_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Media Item.
@param CM_Media_ID Contains media content like images, flash movies etc. */
public void setCM_Media_ID (int CM_Media_ID)
{
if (CM_Media_ID < 1) throw new IllegalArgumentException ("CM_Media_ID is mandatory.");
set_ValueNoCheck ("CM_Media_ID", new Integer(CM_Media_ID));
}
/** Get Media Item.
@return Contains media content like images, flash movies etc. */
public int getCM_Media_ID() 
{
Integer ii = (Integer)get_Value("CM_Media_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Media Server.
@param CM_Media_Server_ID Media Server list to which content should get transfered */
public void setCM_Media_Server_ID (int CM_Media_Server_ID)
{
if (CM_Media_Server_ID < 1) throw new IllegalArgumentException ("CM_Media_Server_ID is mandatory.");
set_ValueNoCheck ("CM_Media_Server_ID", new Integer(CM_Media_Server_ID));
}
/** Get Media Server.
@return Media Server list to which content should get transfered */
public int getCM_Media_Server_ID() 
{
Integer ii = (Integer)get_Value("CM_Media_Server_ID");
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
/** Set Deployed.
@param IsDeployed Entity is deployed */
public void setIsDeployed (boolean IsDeployed)
{
set_Value ("IsDeployed", new Boolean(IsDeployed));
}
/** Get Deployed.
@return Entity is deployed */
public boolean isDeployed() 
{
Object oo = get_Value("IsDeployed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
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
}
