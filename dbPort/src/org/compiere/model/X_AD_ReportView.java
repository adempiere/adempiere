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
/** Generated Model for AD_ReportView
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_AD_ReportView extends PO
{
/** Standard Constructor
@param ctx context
@param AD_ReportView_ID id
@param trxName transaction
*/
public X_AD_ReportView (Properties ctx, int AD_ReportView_ID, String trxName)
{
super (ctx, AD_ReportView_ID, trxName);
/** if (AD_ReportView_ID == 0)
{
setAD_ReportView_ID (0);
setAD_Table_ID (0);
setEntityType (null);	// U
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_ReportView (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=361 */
public static final int Table_ID=MTable.getTable_ID("AD_ReportView");

/** TableName=AD_ReportView */
public static final String Table_Name="AD_ReportView";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_ReportView");

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
StringBuffer sb = new StringBuffer ("X_AD_ReportView[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Report View.
@param AD_ReportView_ID View used to generate this report */
public void setAD_ReportView_ID (int AD_ReportView_ID)
{
if (AD_ReportView_ID < 1) throw new IllegalArgumentException ("AD_ReportView_ID is mandatory.");
set_ValueNoCheck ("AD_ReportView_ID", Integer.valueOf(AD_ReportView_ID));
}
/** Get Report View.
@return View used to generate this report */
public int getAD_ReportView_ID() 
{
Integer ii = (Integer)get_Value("AD_ReportView_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 60)
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getName());
}
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
}
