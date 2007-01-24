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
/** Generated Model for AD_WF_Node_Para
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_AD_WF_Node_Para extends PO
{
/** Standard Constructor
@param ctx context
@param AD_WF_Node_Para_ID id
@param trxName transaction
*/
public X_AD_WF_Node_Para (Properties ctx, int AD_WF_Node_Para_ID, String trxName)
{
super (ctx, AD_WF_Node_Para_ID, trxName);
/** if (AD_WF_Node_Para_ID == 0)
{
setAD_WF_Node_ID (0);
setAD_WF_Node_Para_ID (0);
setEntityType (null);	// U
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_WF_Node_Para (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=643 */
public static final int Table_ID=MTable.getTable_ID("AD_WF_Node_Para");

/** TableName=AD_WF_Node_Para */
public static final String Table_Name="AD_WF_Node_Para";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_WF_Node_Para");

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
StringBuffer sb = new StringBuffer ("X_AD_WF_Node_Para[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Process Parameter.
@param AD_Process_Para_ID Process Parameter */
public void setAD_Process_Para_ID (int AD_Process_Para_ID)
{
if (AD_Process_Para_ID <= 0) set_Value ("AD_Process_Para_ID", null);
 else 
set_Value ("AD_Process_Para_ID", Integer.valueOf(AD_Process_Para_ID));
}
/** Get Process Parameter.
@return Process Parameter */
public int getAD_Process_Para_ID() 
{
Integer ii = (Integer)get_Value("AD_Process_Para_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Node.
@param AD_WF_Node_ID Workflow Node (activity), step or process */
public void setAD_WF_Node_ID (int AD_WF_Node_ID)
{
if (AD_WF_Node_ID < 1) throw new IllegalArgumentException ("AD_WF_Node_ID is mandatory.");
set_ValueNoCheck ("AD_WF_Node_ID", Integer.valueOf(AD_WF_Node_ID));
}
/** Get Node.
@return Workflow Node (activity), step or process */
public int getAD_WF_Node_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_Node_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_WF_Node_ID()));
}
/** Set Workflow Node Parameter.
@param AD_WF_Node_Para_ID Workflow Node Execution Parameter */
public void setAD_WF_Node_Para_ID (int AD_WF_Node_Para_ID)
{
if (AD_WF_Node_Para_ID < 1) throw new IllegalArgumentException ("AD_WF_Node_Para_ID is mandatory.");
set_ValueNoCheck ("AD_WF_Node_Para_ID", Integer.valueOf(AD_WF_Node_Para_ID));
}
/** Get Workflow Node Parameter.
@return Workflow Node Execution Parameter */
public int getAD_WF_Node_Para_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_Node_Para_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Attribute Name.
@param AttributeName Name of the Attribute */
public void setAttributeName (String AttributeName)
{
if (AttributeName != null && AttributeName.length() > 60)
{
log.warning("Length > 60 - truncated");
AttributeName = AttributeName.substring(0,59);
}
set_Value ("AttributeName", AttributeName);
}
/** Get Attribute Name.
@return Name of the Attribute */
public String getAttributeName() 
{
return (String)get_Value("AttributeName");
}
/** Set Attribute Value.
@param AttributeValue Value of the Attribute */
public void setAttributeValue (String AttributeValue)
{
if (AttributeValue != null && AttributeValue.length() > 60)
{
log.warning("Length > 60 - truncated");
AttributeValue = AttributeValue.substring(0,59);
}
set_Value ("AttributeValue", AttributeValue);
}
/** Get Attribute Value.
@return Value of the Attribute */
public String getAttributeValue() 
{
return (String)get_Value("AttributeValue");
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
}
