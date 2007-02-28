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
/** Generated Model for AD_ReplicationTable
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_AD_ReplicationTable extends PO
{
/** Standard Constructor
@param ctx context
@param AD_ReplicationTable_ID id
@param trxName transaction
*/
public X_AD_ReplicationTable (Properties ctx, int AD_ReplicationTable_ID, String trxName)
{
super (ctx, AD_ReplicationTable_ID, trxName);
/** if (AD_ReplicationTable_ID == 0)
{
setAD_ReplicationStrategy_ID (0);
setAD_ReplicationTable_ID (0);
setAD_Table_ID (0);
setEntityType (null);	// U
setReplicationType (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_ReplicationTable (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=601 */
public static final int Table_ID=MTable.getTable_ID("AD_ReplicationTable");

/** TableName=AD_ReplicationTable */
public static final String Table_Name="AD_ReplicationTable";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_ReplicationTable");

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
StringBuffer sb = new StringBuffer ("X_AD_ReplicationTable[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Replication Strategy.
@param AD_ReplicationStrategy_ID Data Replication Strategy */
public void setAD_ReplicationStrategy_ID (int AD_ReplicationStrategy_ID)
{
if (AD_ReplicationStrategy_ID < 1) throw new IllegalArgumentException ("AD_ReplicationStrategy_ID is mandatory.");
set_ValueNoCheck ("AD_ReplicationStrategy_ID", Integer.valueOf(AD_ReplicationStrategy_ID));
}
/** Get Replication Strategy.
@return Data Replication Strategy */
public int getAD_ReplicationStrategy_ID() 
{
Integer ii = (Integer)get_Value("AD_ReplicationStrategy_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_ReplicationStrategy_ID()));
}
/** Column name AD_ReplicationStrategy_ID */
public static final String COLUMNNAME_AD_ReplicationStrategy_ID = "AD_ReplicationStrategy_ID";
/** Set Replication Table.
@param AD_ReplicationTable_ID Data Replication Strategy Table Info */
public void setAD_ReplicationTable_ID (int AD_ReplicationTable_ID)
{
if (AD_ReplicationTable_ID < 1) throw new IllegalArgumentException ("AD_ReplicationTable_ID is mandatory.");
set_ValueNoCheck ("AD_ReplicationTable_ID", Integer.valueOf(AD_ReplicationTable_ID));
}
/** Get Replication Table.
@return Data Replication Strategy Table Info */
public int getAD_ReplicationTable_ID() 
{
Integer ii = (Integer)get_Value("AD_ReplicationTable_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_ReplicationTable_ID */
public static final String COLUMNNAME_AD_ReplicationTable_ID = "AD_ReplicationTable_ID";
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID < 1) throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
set_ValueNoCheck ("AD_Table_ID", Integer.valueOf(AD_Table_ID));
}
/** Get Table.
@return Database Table information */
public int getAD_Table_ID() 
{
Integer ii = (Integer)get_Value("AD_Table_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Table_ID */
public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

/** EntityType AD_Reference_ID=389 */
public static final int ENTITYTYPE_AD_Reference_ID=389;
/** Set Entity Type.
@param EntityType Dictionary Entity Type;
 Determines ownership and synchronization */
public void setEntityType (String EntityType)
{
if (EntityType.length() > 4)
{
log.warning("Length > 4 - truncated");
EntityType = EntityType.substring(0,3);
}
set_Value ("EntityType", EntityType);
}
/** Get Entity Type.
@return Dictionary Entity Type;
 Determines ownership and synchronization */
public String getEntityType() 
{
return (String)get_Value("EntityType");
}
/** Column name EntityType */
public static final String COLUMNNAME_EntityType = "EntityType";

/** ReplicationType AD_Reference_ID=126 */
public static final int REPLICATIONTYPE_AD_Reference_ID=126;
/** Local = L */
public static final String REPLICATIONTYPE_Local = "L";
/** Merge = M */
public static final String REPLICATIONTYPE_Merge = "M";
/** Reference = R */
public static final String REPLICATIONTYPE_Reference = "R";
/** Set Replication Type.
@param ReplicationType Type of Data Replication */
public void setReplicationType (String ReplicationType)
{
if (ReplicationType == null) throw new IllegalArgumentException ("ReplicationType is mandatory");
if (ReplicationType.equals("L") || ReplicationType.equals("M") || ReplicationType.equals("R"));
 else throw new IllegalArgumentException ("ReplicationType Invalid value - " + ReplicationType + " - Reference_ID=126 - L - M - R");
if (ReplicationType.length() > 1)
{
log.warning("Length > 1 - truncated");
ReplicationType = ReplicationType.substring(0,0);
}
set_Value ("ReplicationType", ReplicationType);
}
/** Get Replication Type.
@return Type of Data Replication */
public String getReplicationType() 
{
return (String)get_Value("ReplicationType");
}
/** Column name ReplicationType */
public static final String COLUMNNAME_ReplicationType = "ReplicationType";
}
