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
/** Generated Model for AD_WF_ProcessData
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_AD_WF_ProcessData extends PO
{
/** Standard Constructor
@param ctx context
@param AD_WF_ProcessData_ID id
@param trxName transaction
*/
public X_AD_WF_ProcessData (Properties ctx, int AD_WF_ProcessData_ID, String trxName)
{
super (ctx, AD_WF_ProcessData_ID, trxName);
/** if (AD_WF_ProcessData_ID == 0)
{
setAD_WF_ProcessData_ID (0);
setAD_WF_Process_ID (0);
setAttributeName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_WF_ProcessData (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=648 */
public static final int Table_ID=MTable.getTable_ID("AD_WF_ProcessData");

/** TableName=AD_WF_ProcessData */
public static final String Table_Name="AD_WF_ProcessData";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_WF_ProcessData");

protected BigDecimal accessLevel = BigDecimal.valueOf(7);
/** AccessLevel
@return 7 - System - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_AD_WF_ProcessData[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Workflow Process Data.
@param AD_WF_ProcessData_ID Workflow Process Context */
public void setAD_WF_ProcessData_ID (int AD_WF_ProcessData_ID)
{
if (AD_WF_ProcessData_ID < 1) throw new IllegalArgumentException ("AD_WF_ProcessData_ID is mandatory.");
set_ValueNoCheck ("AD_WF_ProcessData_ID", Integer.valueOf(AD_WF_ProcessData_ID));
}
/** Get Workflow Process Data.
@return Workflow Process Context */
public int getAD_WF_ProcessData_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_ProcessData_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_WF_ProcessData_ID */
public static final String COLUMNNAME_AD_WF_ProcessData_ID = "AD_WF_ProcessData_ID";
/** Set Workflow Process.
@param AD_WF_Process_ID Actual Workflow Process Instance */
public void setAD_WF_Process_ID (int AD_WF_Process_ID)
{
if (AD_WF_Process_ID < 1) throw new IllegalArgumentException ("AD_WF_Process_ID is mandatory.");
set_ValueNoCheck ("AD_WF_Process_ID", Integer.valueOf(AD_WF_Process_ID));
}
/** Get Workflow Process.
@return Actual Workflow Process Instance */
public int getAD_WF_Process_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_Process_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_WF_Process_ID()));
}
/** Column name AD_WF_Process_ID */
public static final String COLUMNNAME_AD_WF_Process_ID = "AD_WF_Process_ID";
/** Set Attribute Name.
@param AttributeName Name of the Attribute */
public void setAttributeName (String AttributeName)
{
if (AttributeName == null) throw new IllegalArgumentException ("AttributeName is mandatory.");
if (AttributeName.length() > 60)
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
/** Column name AttributeName */
public static final String COLUMNNAME_AttributeName = "AttributeName";
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
/** Column name AttributeValue */
public static final String COLUMNNAME_AttributeValue = "AttributeValue";
}
