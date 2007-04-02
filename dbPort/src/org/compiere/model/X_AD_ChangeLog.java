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
/** Generated Model for AD_ChangeLog
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_AD_ChangeLog extends PO
{
/** Standard Constructor
@param ctx context
@param AD_ChangeLog_ID id
@param trxName transaction
*/
public X_AD_ChangeLog (Properties ctx, int AD_ChangeLog_ID, String trxName)
{
super (ctx, AD_ChangeLog_ID, trxName);
/** if (AD_ChangeLog_ID == 0)
{
setAD_ChangeLog_ID (0);
setAD_Column_ID (0);
setAD_Session_ID (0);
setAD_Table_ID (0);
setIsCustomization (false);
setRecord_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_ChangeLog (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=580 */
public static final int Table_ID=MTable.getTable_ID("AD_ChangeLog");

/** TableName=AD_ChangeLog */
public static final String Table_Name="AD_ChangeLog";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_ChangeLog");

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
StringBuffer sb = new StringBuffer ("X_AD_ChangeLog[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Change Log.
@param AD_ChangeLog_ID Log of data changes */
public void setAD_ChangeLog_ID (int AD_ChangeLog_ID)
{
if (AD_ChangeLog_ID < 1) throw new IllegalArgumentException ("AD_ChangeLog_ID is mandatory.");
set_ValueNoCheck ("AD_ChangeLog_ID", Integer.valueOf(AD_ChangeLog_ID));
}
/** Get Change Log.
@return Log of data changes */
public int getAD_ChangeLog_ID() 
{
Integer ii = (Integer)get_Value("AD_ChangeLog_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_ChangeLog_ID */
public static final String COLUMNNAME_AD_ChangeLog_ID = "AD_ChangeLog_ID";
/** Set Column.
@param AD_Column_ID Column in the table */
public void setAD_Column_ID (int AD_Column_ID)
{
if (AD_Column_ID < 1) throw new IllegalArgumentException ("AD_Column_ID is mandatory.");
set_ValueNoCheck ("AD_Column_ID", Integer.valueOf(AD_Column_ID));
}
/** Get Column.
@return Column in the table */
public int getAD_Column_ID() 
{
Integer ii = (Integer)get_Value("AD_Column_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Column_ID */
public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";
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
/** Column name AD_Session_ID */
public static final String COLUMNNAME_AD_Session_ID = "AD_Session_ID";
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
/** Set Customization.
@param IsCustomization The change is a customization of the data dictionary and can be applied after Migration */
public void setIsCustomization (boolean IsCustomization)
{
set_Value ("IsCustomization", Boolean.valueOf(IsCustomization));
}
/** Get Customization.
@return The change is a customization of the data dictionary and can be applied after Migration */
public boolean isCustomization() 
{
Object oo = get_Value("IsCustomization");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsCustomization */
public static final String COLUMNNAME_IsCustomization = "IsCustomization";
/** Set New Value.
@param NewValue New field value */
public void setNewValue (String NewValue)
{
if (NewValue != null && NewValue.length() > 2000)
{
log.warning("Length > 2000 - truncated");
NewValue = NewValue.substring(0,1999);
}
set_ValueNoCheck ("NewValue", NewValue);
}
/** Get New Value.
@return New field value */
public String getNewValue() 
{
return (String)get_Value("NewValue");
}
/** Column name NewValue */
public static final String COLUMNNAME_NewValue = "NewValue";
/** Set Old Value.
@param OldValue The old file data */
public void setOldValue (String OldValue)
{
if (OldValue != null && OldValue.length() > 2000)
{
log.warning("Length > 2000 - truncated");
OldValue = OldValue.substring(0,1999);
}
set_ValueNoCheck ("OldValue", OldValue);
}
/** Get Old Value.
@return The old file data */
public String getOldValue() 
{
return (String)get_Value("OldValue");
}
/** Column name OldValue */
public static final String COLUMNNAME_OldValue = "OldValue";
/** Set Record ID.
@param Record_ID Direct internal record ID */
public void setRecord_ID (int Record_ID)
{
if (Record_ID < 0) throw new IllegalArgumentException ("Record_ID is mandatory.");
set_ValueNoCheck ("Record_ID", Integer.valueOf(Record_ID));
}
/** Get Record ID.
@return Direct internal record ID */
public int getRecord_ID() 
{
Integer ii = (Integer)get_Value("Record_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Record_ID */
public static final String COLUMNNAME_Record_ID = "Record_ID";
/** Set Redo.
@param Redo Redo */
public void setRedo (String Redo)
{
if (Redo != null && Redo.length() > 1)
{
log.warning("Length > 1 - truncated");
Redo = Redo.substring(0,0);
}
set_Value ("Redo", Redo);
}
/** Get Redo.
@return Redo */
public String getRedo() 
{
return (String)get_Value("Redo");
}
/** Column name Redo */
public static final String COLUMNNAME_Redo = "Redo";
/** Set Transaction.
@param TrxName Name of the transaction */
public void setTrxName (String TrxName)
{
if (TrxName != null && TrxName.length() > 60)
{
log.warning("Length > 60 - truncated");
TrxName = TrxName.substring(0,59);
}
set_ValueNoCheck ("TrxName", TrxName);
}
/** Get Transaction.
@return Name of the transaction */
public String getTrxName() 
{
return (String)get_Value("TrxName");
}
/** Column name TrxName */
public static final String COLUMNNAME_TrxName = "TrxName";
/** Set Undo.
@param Undo Undo */
public void setUndo (String Undo)
{
if (Undo != null && Undo.length() > 1)
{
log.warning("Length > 1 - truncated");
Undo = Undo.substring(0,0);
}
set_Value ("Undo", Undo);
}
/** Get Undo.
@return Undo */
public String getUndo() 
{
return (String)get_Value("Undo");
}
/** Column name Undo */
public static final String COLUMNNAME_Undo = "Undo";
}
