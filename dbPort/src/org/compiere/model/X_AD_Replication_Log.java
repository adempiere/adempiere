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
/** Generated Model for AD_Replication_Log
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_AD_Replication_Log extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Replication_Log_ID id
@param trxName transaction
*/
public X_AD_Replication_Log (Properties ctx, int AD_Replication_Log_ID, String trxName)
{
super (ctx, AD_Replication_Log_ID, trxName);
/** if (AD_Replication_Log_ID == 0)
{
setAD_Replication_Log_ID (0);
setAD_Replication_Run_ID (0);
setIsReplicated (false);	// N
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Replication_Log (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=604 */
public static final int Table_ID=MTable.getTable_ID("AD_Replication_Log");

/** TableName=AD_Replication_Log */
public static final String Table_Name="AD_Replication_Log";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Replication_Log");

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
StringBuffer sb = new StringBuffer ("X_AD_Replication_Log[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Replication Table.
@param AD_ReplicationTable_ID Data Replication Strategy Table Info */
public void setAD_ReplicationTable_ID (int AD_ReplicationTable_ID)
{
if (AD_ReplicationTable_ID <= 0) set_Value ("AD_ReplicationTable_ID", null);
 else 
set_Value ("AD_ReplicationTable_ID", Integer.valueOf(AD_ReplicationTable_ID));
}
/** Get Replication Table.
@return Data Replication Strategy Table Info */
public int getAD_ReplicationTable_ID() 
{
Integer ii = (Integer)get_Value("AD_ReplicationTable_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Replication Log.
@param AD_Replication_Log_ID Data Replication Log Details */
public void setAD_Replication_Log_ID (int AD_Replication_Log_ID)
{
if (AD_Replication_Log_ID < 1) throw new IllegalArgumentException ("AD_Replication_Log_ID is mandatory.");
set_ValueNoCheck ("AD_Replication_Log_ID", Integer.valueOf(AD_Replication_Log_ID));
}
/** Get Replication Log.
@return Data Replication Log Details */
public int getAD_Replication_Log_ID() 
{
Integer ii = (Integer)get_Value("AD_Replication_Log_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Replication Run.
@param AD_Replication_Run_ID Data Replication Run */
public void setAD_Replication_Run_ID (int AD_Replication_Run_ID)
{
if (AD_Replication_Run_ID < 1) throw new IllegalArgumentException ("AD_Replication_Run_ID is mandatory.");
set_ValueNoCheck ("AD_Replication_Run_ID", Integer.valueOf(AD_Replication_Run_ID));
}
/** Get Replication Run.
@return Data Replication Run */
public int getAD_Replication_Run_ID() 
{
Integer ii = (Integer)get_Value("AD_Replication_Run_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_Replication_Run_ID()));
}
/** Set Replicated.
@param IsReplicated The data is successfully replicated */
public void setIsReplicated (boolean IsReplicated)
{
set_Value ("IsReplicated", Boolean.valueOf(IsReplicated));
}
/** Get Replicated.
@return The data is successfully replicated */
public boolean isReplicated() 
{
Object oo = get_Value("IsReplicated");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Process Message.
@param P_Msg Process Message */
public void setP_Msg (String P_Msg)
{
if (P_Msg != null && P_Msg.length() > 2000)
{
log.warning("Length > 2000 - truncated");
P_Msg = P_Msg.substring(0,1999);
}
set_Value ("P_Msg", P_Msg);
}
/** Get Process Message.
@return Process Message */
public String getP_Msg() 
{
return (String)get_Value("P_Msg");
}
}
