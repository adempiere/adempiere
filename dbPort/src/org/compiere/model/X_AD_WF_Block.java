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
/** Generated Model for AD_WF_Block
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_AD_WF_Block extends PO
{
/** Standard Constructor
@param ctx context
@param AD_WF_Block_ID id
@param trxName transaction
*/
public X_AD_WF_Block (Properties ctx, int AD_WF_Block_ID, String trxName)
{
super (ctx, AD_WF_Block_ID, trxName);
/** if (AD_WF_Block_ID == 0)
{
setAD_WF_Block_ID (0);
setAD_Workflow_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_WF_Block (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=647 */
public static final int Table_ID=MTable.getTable_ID("AD_WF_Block");

/** TableName=AD_WF_Block */
public static final String Table_Name="AD_WF_Block";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_WF_Block");

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
StringBuffer sb = new StringBuffer ("X_AD_WF_Block[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Workflow Block.
@param AD_WF_Block_ID Workflow Transaction Execution Block */
public void setAD_WF_Block_ID (int AD_WF_Block_ID)
{
if (AD_WF_Block_ID < 1) throw new IllegalArgumentException ("AD_WF_Block_ID is mandatory.");
set_ValueNoCheck ("AD_WF_Block_ID", Integer.valueOf(AD_WF_Block_ID));
}
/** Get Workflow Block.
@return Workflow Transaction Execution Block */
public int getAD_WF_Block_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_Block_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_WF_Block_ID */
public static final String COLUMNNAME_AD_WF_Block_ID = "AD_WF_Block_ID";
/** Set Workflow.
@param AD_Workflow_ID Workflow or combination of tasks */
public void setAD_Workflow_ID (int AD_Workflow_ID)
{
if (AD_Workflow_ID < 1) throw new IllegalArgumentException ("AD_Workflow_ID is mandatory.");
set_ValueNoCheck ("AD_Workflow_ID", Integer.valueOf(AD_Workflow_ID));
}
/** Get Workflow.
@return Workflow or combination of tasks */
public int getAD_Workflow_ID() 
{
Integer ii = (Integer)get_Value("AD_Workflow_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Workflow_ID */
public static final String COLUMNNAME_AD_Workflow_ID = "AD_Workflow_ID";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
}
