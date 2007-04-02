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
/** Generated Model for AD_Ref_Table
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_AD_Ref_Table extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Ref_Table_ID id
@param trxName transaction
*/
public X_AD_Ref_Table (Properties ctx, int AD_Ref_Table_ID, String trxName)
{
super (ctx, AD_Ref_Table_ID, trxName);
/** if (AD_Ref_Table_ID == 0)
{
setAD_Display (0);
setAD_Key (0);
setAD_Reference_ID (0);
setAD_Table_ID (0);
setEntityType (null);	// U
setIsValueDisplayed (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Ref_Table (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=103 */
public static final int Table_ID=MTable.getTable_ID("AD_Ref_Table");

/** TableName=AD_Ref_Table */
public static final String Table_Name="AD_Ref_Table";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Ref_Table");

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
StringBuffer sb = new StringBuffer ("X_AD_Ref_Table[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_Display AD_Reference_ID=3 */
public static final int AD_DISPLAY_AD_Reference_ID=3;
/** Set Display column.
@param AD_Display Column that will display */
public void setAD_Display (int AD_Display)
{
set_Value ("AD_Display", Integer.valueOf(AD_Display));
}
/** Get Display column.
@return Column that will display */
public int getAD_Display() 
{
Integer ii = (Integer)get_Value("AD_Display");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Display */
public static final String COLUMNNAME_AD_Display = "AD_Display";

/** AD_Key AD_Reference_ID=3 */
public static final int AD_KEY_AD_Reference_ID=3;
/** Set Key column.
@param AD_Key Unique identifier of a record */
public void setAD_Key (int AD_Key)
{
set_Value ("AD_Key", Integer.valueOf(AD_Key));
}
/** Get Key column.
@return Unique identifier of a record */
public int getAD_Key() 
{
Integer ii = (Integer)get_Value("AD_Key");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Key */
public static final String COLUMNNAME_AD_Key = "AD_Key";
/** Set Reference.
@param AD_Reference_ID System Reference and Validation */
public void setAD_Reference_ID (int AD_Reference_ID)
{
if (AD_Reference_ID < 1) throw new IllegalArgumentException ("AD_Reference_ID is mandatory.");
set_ValueNoCheck ("AD_Reference_ID", Integer.valueOf(AD_Reference_ID));
}
/** Get Reference.
@return System Reference and Validation */
public int getAD_Reference_ID() 
{
Integer ii = (Integer)get_Value("AD_Reference_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_Reference_ID()));
}
/** Column name AD_Reference_ID */
public static final String COLUMNNAME_AD_Reference_ID = "AD_Reference_ID";
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
/** Set Display Value.
@param IsValueDisplayed Displays Value column with the Display column */
public void setIsValueDisplayed (boolean IsValueDisplayed)
{
set_Value ("IsValueDisplayed", Boolean.valueOf(IsValueDisplayed));
}
/** Get Display Value.
@return Displays Value column with the Display column */
public boolean isValueDisplayed() 
{
Object oo = get_Value("IsValueDisplayed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsValueDisplayed */
public static final String COLUMNNAME_IsValueDisplayed = "IsValueDisplayed";
/** Set Sql ORDER BY.
@param OrderByClause Fully qualified ORDER BY clause */
public void setOrderByClause (String OrderByClause)
{
if (OrderByClause != null && OrderByClause.length() > 2000)
{
log.warning("Length > 2000 - truncated");
OrderByClause = OrderByClause.substring(0,1999);
}
set_Value ("OrderByClause", OrderByClause);
}
/** Get Sql ORDER BY.
@return Fully qualified ORDER BY clause */
public String getOrderByClause() 
{
return (String)get_Value("OrderByClause");
}
/** Column name OrderByClause */
public static final String COLUMNNAME_OrderByClause = "OrderByClause";
/** Set Sql WHERE.
@param WhereClause Fully qualified SQL WHERE clause */
public void setWhereClause (String WhereClause)
{
if (WhereClause != null && WhereClause.length() > 2000)
{
log.warning("Length > 2000 - truncated");
WhereClause = WhereClause.substring(0,1999);
}
set_Value ("WhereClause", WhereClause);
}
/** Get Sql WHERE.
@return Fully qualified SQL WHERE clause */
public String getWhereClause() 
{
return (String)get_Value("WhereClause");
}
/** Column name WhereClause */
public static final String COLUMNNAME_WhereClause = "WhereClause";
}
