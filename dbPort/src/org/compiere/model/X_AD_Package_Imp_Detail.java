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
/** Generated Model for AD_Package_Imp_Detail
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_AD_Package_Imp_Detail extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Package_Imp_Detail_ID id
@param trxName transaction
*/
public X_AD_Package_Imp_Detail (Properties ctx, int AD_Package_Imp_Detail_ID, String trxName)
{
super (ctx, AD_Package_Imp_Detail_ID, trxName);
/** if (AD_Package_Imp_Detail_ID == 0)
{
setAD_Original_ID (0);
setAD_Package_Imp_Detail_ID (0);
setAD_Package_Imp_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Package_Imp_Detail (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=50004 */
public static final int Table_ID=MTable.getTable_ID("AD_Package_Imp_Detail");

/** TableName=AD_Package_Imp_Detail */
public static final String Table_Name="AD_Package_Imp_Detail";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Package_Imp_Detail");

protected BigDecimal accessLevel = BigDecimal.valueOf(4);
/** AccessLevel
@return 4 - System 
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
StringBuffer sb = new StringBuffer ("X_AD_Package_Imp_Detail[").append(get_ID()).append("]");
return sb.toString();
}
/** Set AD_Original_ID.
@param AD_Original_ID AD_Original_ID */
public void setAD_Original_ID (int AD_Original_ID)
{
if (AD_Original_ID < 1) throw new IllegalArgumentException ("AD_Original_ID is mandatory.");
set_Value ("AD_Original_ID", Integer.valueOf(AD_Original_ID));
}
/** Get AD_Original_ID.
@return AD_Original_ID */
public int getAD_Original_ID() 
{
Integer ii = (Integer)get_Value("AD_Original_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set AD_Package_Imp_Detail_ID.
@param AD_Package_Imp_Detail_ID AD_Package_Imp_Detail_ID */
public void setAD_Package_Imp_Detail_ID (int AD_Package_Imp_Detail_ID)
{
if (AD_Package_Imp_Detail_ID < 1) throw new IllegalArgumentException ("AD_Package_Imp_Detail_ID is mandatory.");
set_ValueNoCheck ("AD_Package_Imp_Detail_ID", Integer.valueOf(AD_Package_Imp_Detail_ID));
}
/** Get AD_Package_Imp_Detail_ID.
@return AD_Package_Imp_Detail_ID */
public int getAD_Package_Imp_Detail_ID() 
{
Integer ii = (Integer)get_Value("AD_Package_Imp_Detail_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set AD_Package_Imp_ID.
@param AD_Package_Imp_ID AD_Package_Imp_ID */
public void setAD_Package_Imp_ID (int AD_Package_Imp_ID)
{
if (AD_Package_Imp_ID < 1) throw new IllegalArgumentException ("AD_Package_Imp_ID is mandatory.");
set_ValueNoCheck ("AD_Package_Imp_ID", Integer.valueOf(AD_Package_Imp_ID));
}
/** Get AD_Package_Imp_ID.
@return AD_Package_Imp_ID */
public int getAD_Package_Imp_ID() 
{
Integer ii = (Integer)get_Value("AD_Package_Imp_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID <= 0) set_Value ("AD_Table_ID", null);
 else 
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
/** Set Action.
@param Action Indicates the Action to be performed */
public void setAction (String Action)
{
if (Action != null && Action.length() > 20)
{
log.warning("Length > 20 - truncated");
Action = Action.substring(0,19);
}
set_Value ("Action", Action);
}
/** Get Action.
@return Indicates the Action to be performed */
public String getAction() 
{
return (String)get_Value("Action");
}
/** Set Ad_Backup_ID.
@param Ad_Backup_ID Ad_Backup_ID */
public void setAd_Backup_ID (int Ad_Backup_ID)
{
if (Ad_Backup_ID <= 0) set_Value ("Ad_Backup_ID", null);
 else 
set_Value ("Ad_Backup_ID", Integer.valueOf(Ad_Backup_ID));
}
/** Get Ad_Backup_ID.
@return Ad_Backup_ID */
public int getAd_Backup_ID() 
{
Integer ii = (Integer)get_Value("Ad_Backup_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name != null && Name.length() > 60)
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
/** Set Success.
@param Success Success */
public void setSuccess (String Success)
{
if (Success != null && Success.length() > 20)
{
log.warning("Length > 20 - truncated");
Success = Success.substring(0,19);
}
set_Value ("Success", Success);
}
/** Get Success.
@return Success */
public String getSuccess() 
{
return (String)get_Value("Success");
}
/** Set DB Table Name.
@param TableName Name of the table in the database */
public void setTableName (String TableName)
{
if (TableName != null && TableName.length() > 60)
{
log.warning("Length > 60 - truncated");
TableName = TableName.substring(0,59);
}
set_Value ("TableName", TableName);
}
/** Get DB Table Name.
@return Name of the table in the database */
public String getTableName() 
{
return (String)get_Value("TableName");
}
/** Set Type.
@param Type Type of Validation (SQL, Java Script, Java Language) */
public void setType (String Type)
{
if (Type != null && Type.length() > 60)
{
log.warning("Length > 60 - truncated");
Type = Type.substring(0,59);
}
set_Value ("Type", Type);
}
/** Get Type.
@return Type of Validation (SQL, Java Script, Java Language) */
public String getType() 
{
return (String)get_Value("Type");
}
/** Set Uninstall.
@param Uninstall Uninstall */
public void setUninstall (boolean Uninstall)
{
set_ValueNoCheck ("Uninstall", Boolean.valueOf(Uninstall));
}
/** Get Uninstall.
@return Uninstall */
public boolean isUninstall() 
{
Object oo = get_Value("Uninstall");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
}
