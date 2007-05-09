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
/** Generated Model for AD_Package_Imp_Backup
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_AD_Package_Imp_Backup extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Package_Imp_Backup_ID id
@param trxName transaction
*/
public X_AD_Package_Imp_Backup (Properties ctx, int AD_Package_Imp_Backup_ID, String trxName)
{
super (ctx, AD_Package_Imp_Backup_ID, trxName);
/** if (AD_Package_Imp_Backup_ID == 0)
{
setAD_Package_Imp_Backup_ID (0);
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
public X_AD_Package_Imp_Backup (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=50002 */
public static final int Table_ID=MTable.getTable_ID("AD_Package_Imp_Backup");

/** TableName=AD_Package_Imp_Backup */
public static final String Table_Name="AD_Package_Imp_Backup";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Package_Imp_Backup");

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
StringBuffer sb = new StringBuffer ("X_AD_Package_Imp_Backup[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_Column_ID AD_Reference_ID=251 */
public static final int AD_COLUMN_ID_AD_Reference_ID=251;
/** Set Column.
@param AD_Column_ID Column in the table */
public void setAD_Column_ID (int AD_Column_ID)
{
if (AD_Column_ID <= 0) set_Value ("AD_Column_ID", null);
 else 
set_Value ("AD_Column_ID", Integer.valueOf(AD_Column_ID));
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
/** Set AD_Package_Imp_Backup_ID.
@param AD_Package_Imp_Backup_ID AD_Package_Imp_Backup_ID */
public void setAD_Package_Imp_Backup_ID (int AD_Package_Imp_Backup_ID)
{
if (AD_Package_Imp_Backup_ID < 1) throw new IllegalArgumentException ("AD_Package_Imp_Backup_ID is mandatory.");
set_ValueNoCheck ("AD_Package_Imp_Backup_ID", Integer.valueOf(AD_Package_Imp_Backup_ID));
}
/** Get AD_Package_Imp_Backup_ID.
@return AD_Package_Imp_Backup_ID */
public int getAD_Package_Imp_Backup_ID() 
{
Integer ii = (Integer)get_Value("AD_Package_Imp_Backup_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_Package_Imp_Backup_ID()));
}
/** Column name AD_Package_Imp_Backup_ID */
public static final String COLUMNNAME_AD_Package_Imp_Backup_ID = "AD_Package_Imp_Backup_ID";
/** Set AD_Package_Imp_Bck_Dir.
@param AD_Package_Imp_Bck_Dir AD_Package_Imp_Bck_Dir */
public void setAD_Package_Imp_Bck_Dir (String AD_Package_Imp_Bck_Dir)
{
if (AD_Package_Imp_Bck_Dir != null && AD_Package_Imp_Bck_Dir.length() > 255)
{
log.warning("Length > 255 - truncated");
AD_Package_Imp_Bck_Dir = AD_Package_Imp_Bck_Dir.substring(0,254);
}
set_Value ("AD_Package_Imp_Bck_Dir", AD_Package_Imp_Bck_Dir);
}
/** Get AD_Package_Imp_Bck_Dir.
@return AD_Package_Imp_Bck_Dir */
public String getAD_Package_Imp_Bck_Dir() 
{
return (String)get_Value("AD_Package_Imp_Bck_Dir");
}
/** Column name AD_Package_Imp_Bck_Dir */
public static final String COLUMNNAME_AD_Package_Imp_Bck_Dir = "AD_Package_Imp_Bck_Dir";
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
/** Column name AD_Package_Imp_Detail_ID */
public static final String COLUMNNAME_AD_Package_Imp_Detail_ID = "AD_Package_Imp_Detail_ID";
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
/** Column name AD_Package_Imp_ID */
public static final String COLUMNNAME_AD_Package_Imp_ID = "AD_Package_Imp_ID";
/** Set AD_Package_Imp_Org_Dir.
@param AD_Package_Imp_Org_Dir AD_Package_Imp_Org_Dir */
public void setAD_Package_Imp_Org_Dir (String AD_Package_Imp_Org_Dir)
{
if (AD_Package_Imp_Org_Dir != null && AD_Package_Imp_Org_Dir.length() > 255)
{
log.warning("Length > 255 - truncated");
AD_Package_Imp_Org_Dir = AD_Package_Imp_Org_Dir.substring(0,254);
}
set_Value ("AD_Package_Imp_Org_Dir", AD_Package_Imp_Org_Dir);
}
/** Get AD_Package_Imp_Org_Dir.
@return AD_Package_Imp_Org_Dir */
public String getAD_Package_Imp_Org_Dir() 
{
return (String)get_Value("AD_Package_Imp_Org_Dir");
}
/** Column name AD_Package_Imp_Org_Dir */
public static final String COLUMNNAME_AD_Package_Imp_Org_Dir = "AD_Package_Imp_Org_Dir";

/** AD_Reference_ID AD_Reference_ID=1 */
public static final int AD_REFERENCE_ID_AD_Reference_ID=1;
/** Set Reference.
@param AD_Reference_ID System Reference and Validation */
public void setAD_Reference_ID (int AD_Reference_ID)
{
if (AD_Reference_ID <= 0) set_Value ("AD_Reference_ID", null);
 else 
set_Value ("AD_Reference_ID", Integer.valueOf(AD_Reference_ID));
}
/** Get Reference.
@return System Reference and Validation */
public int getAD_Reference_ID() 
{
Integer ii = (Integer)get_Value("AD_Reference_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Reference_ID */
public static final String COLUMNNAME_AD_Reference_ID = "AD_Reference_ID";
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
/** Column name AD_Table_ID */
public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";
/** Set ColValue.
@param ColValue ColValue */
public void setColValue (String ColValue)
{
if (ColValue != null && ColValue.length() > 2000)
{
log.warning("Length > 2000 - truncated");
ColValue = ColValue.substring(0,1999);
}
set_Value ("ColValue", ColValue);
}
/** Get ColValue.
@return ColValue */
public String getColValue() 
{
return (String)get_Value("ColValue");
}
/** Column name ColValue */
public static final String COLUMNNAME_ColValue = "ColValue";
/** Set Uninstall.
@param Uninstall Uninstall */
public void setUninstall (boolean Uninstall)
{
set_Value ("Uninstall", Boolean.valueOf(Uninstall));
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
/** Column name Uninstall */
public static final String COLUMNNAME_Uninstall = "Uninstall";
}
