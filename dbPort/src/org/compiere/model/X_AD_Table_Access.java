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
/** Generated Model for AD_Table_Access
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_AD_Table_Access extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Table_Access_ID id
@param trxName transaction
*/
public X_AD_Table_Access (Properties ctx, int AD_Table_Access_ID, String trxName)
{
super (ctx, AD_Table_Access_ID, trxName);
/** if (AD_Table_Access_ID == 0)
{
setAD_Role_ID (0);
setAD_Table_ID (0);
setAccessTypeRule (null);	// A
setIsCanExport (false);
setIsCanReport (false);
setIsExclude (true);	// Y
setIsReadOnly (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Table_Access (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=565 */
public static final int Table_ID=MTable.getTable_ID("AD_Table_Access");

/** TableName=AD_Table_Access */
public static final String Table_Name="AD_Table_Access";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Table_Access");

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
StringBuffer sb = new StringBuffer ("X_AD_Table_Access[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Role.
@param AD_Role_ID Responsibility Role */
public void setAD_Role_ID (int AD_Role_ID)
{
if (AD_Role_ID < 0) throw new IllegalArgumentException ("AD_Role_ID is mandatory.");
set_ValueNoCheck ("AD_Role_ID", Integer.valueOf(AD_Role_ID));
}
/** Get Role.
@return Responsibility Role */
public int getAD_Role_ID() 
{
Integer ii = (Integer)get_Value("AD_Role_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Role_ID */
public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_Table_ID()));
}
/** Column name AD_Table_ID */
public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

/** AccessTypeRule AD_Reference_ID=293 */
public static final int ACCESSTYPERULE_AD_Reference_ID=293;
/** Accessing = A */
public static final String ACCESSTYPERULE_Accessing = "A";
/** Exporting = E */
public static final String ACCESSTYPERULE_Exporting = "E";
/** Reporting = R */
public static final String ACCESSTYPERULE_Reporting = "R";
/** Set Access Type.
@param AccessTypeRule The type of access for this rule */
public void setAccessTypeRule (String AccessTypeRule)
{
if (AccessTypeRule == null) throw new IllegalArgumentException ("AccessTypeRule is mandatory");
if (AccessTypeRule.equals("A") || AccessTypeRule.equals("E") || AccessTypeRule.equals("R"));
 else throw new IllegalArgumentException ("AccessTypeRule Invalid value - " + AccessTypeRule + " - Reference_ID=293 - A - E - R");
if (AccessTypeRule.length() > 1)
{
log.warning("Length > 1 - truncated");
AccessTypeRule = AccessTypeRule.substring(0,0);
}
set_ValueNoCheck ("AccessTypeRule", AccessTypeRule);
}
/** Get Access Type.
@return The type of access for this rule */
public String getAccessTypeRule() 
{
return (String)get_Value("AccessTypeRule");
}
/** Column name AccessTypeRule */
public static final String COLUMNNAME_AccessTypeRule = "AccessTypeRule";
/** Set Can Export.
@param IsCanExport Users with this role can export data */
public void setIsCanExport (boolean IsCanExport)
{
set_Value ("IsCanExport", Boolean.valueOf(IsCanExport));
}
/** Get Can Export.
@return Users with this role can export data */
public boolean isCanExport() 
{
Object oo = get_Value("IsCanExport");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsCanExport */
public static final String COLUMNNAME_IsCanExport = "IsCanExport";
/** Set Can Report.
@param IsCanReport Users with this role can create reports */
public void setIsCanReport (boolean IsCanReport)
{
set_Value ("IsCanReport", Boolean.valueOf(IsCanReport));
}
/** Get Can Report.
@return Users with this role can create reports */
public boolean isCanReport() 
{
Object oo = get_Value("IsCanReport");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsCanReport */
public static final String COLUMNNAME_IsCanReport = "IsCanReport";
/** Set Exclude.
@param IsExclude Exclude access to the data - if not selected Include access to the data */
public void setIsExclude (boolean IsExclude)
{
set_Value ("IsExclude", Boolean.valueOf(IsExclude));
}
/** Get Exclude.
@return Exclude access to the data - if not selected Include access to the data */
public boolean isExclude() 
{
Object oo = get_Value("IsExclude");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsExclude */
public static final String COLUMNNAME_IsExclude = "IsExclude";
/** Set Read Only.
@param IsReadOnly Field is read only */
public void setIsReadOnly (boolean IsReadOnly)
{
set_Value ("IsReadOnly", Boolean.valueOf(IsReadOnly));
}
/** Get Read Only.
@return Field is read only */
public boolean isReadOnly() 
{
Object oo = get_Value("IsReadOnly");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsReadOnly */
public static final String COLUMNNAME_IsReadOnly = "IsReadOnly";
}
