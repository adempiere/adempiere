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
/** Generated Model for R_IssueStatus
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_R_IssueStatus extends PO
{
/** Standard Constructor
@param ctx context
@param R_IssueStatus_ID id
@param trxName transaction
*/
public X_R_IssueStatus (Properties ctx, int R_IssueStatus_ID, String trxName)
{
super (ctx, R_IssueStatus_ID, trxName);
/** if (R_IssueStatus_ID == 0)
{
setName (null);
setR_IssueStatus_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_R_IssueStatus (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=838 */
public static final int Table_ID=MTable.getTable_ID("R_IssueStatus");

/** TableName=R_IssueStatus */
public static final String Table_Name="R_IssueStatus";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"R_IssueStatus");

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
StringBuffer sb = new StringBuffer ("X_R_IssueStatus[").append(get_ID()).append("]");
return sb.toString();
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
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 120)
{
log.warning("Length > 120 - truncated");
Name = Name.substring(0,119);
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Issue Status.
@param R_IssueStatus_ID Status of an Issue */
public void setR_IssueStatus_ID (int R_IssueStatus_ID)
{
if (R_IssueStatus_ID < 1) throw new IllegalArgumentException ("R_IssueStatus_ID is mandatory.");
set_ValueNoCheck ("R_IssueStatus_ID", Integer.valueOf(R_IssueStatus_ID));
}
/** Get Issue Status.
@return Status of an Issue */
public int getR_IssueStatus_ID() 
{
Integer ii = (Integer)get_Value("R_IssueStatus_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_IssueStatus_ID */
public static final String COLUMNNAME_R_IssueStatus_ID = "R_IssueStatus_ID";
}
