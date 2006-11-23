/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
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
/** Generated Model for AD_WF_ActivityResult
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_AD_WF_ActivityResult extends PO
{
/** Standard Constructor
@param ctx context
@param AD_WF_ActivityResult_ID id
@param trxName transaction
*/
public X_AD_WF_ActivityResult (Properties ctx, int AD_WF_ActivityResult_ID, String trxName)
{
super (ctx, AD_WF_ActivityResult_ID, trxName);
/** if (AD_WF_ActivityResult_ID == 0)
{
setAD_WF_ActivityResult_ID (0);
setAD_WF_Activity_ID (0);
setAttributeName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_WF_ActivityResult (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=650 */
public static final int Table_ID=650;

/** TableName=AD_WF_ActivityResult */
public static final String Table_Name="AD_WF_ActivityResult";

protected static KeyNamePair Model = new KeyNamePair(650,"AD_WF_ActivityResult");

protected BigDecimal accessLevel = new BigDecimal(7);
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
StringBuffer sb = new StringBuffer ("X_AD_WF_ActivityResult[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Workflow Activity Result.
@param AD_WF_ActivityResult_ID Result of the Workflow Process Activity */
public void setAD_WF_ActivityResult_ID (int AD_WF_ActivityResult_ID)
{
if (AD_WF_ActivityResult_ID < 1) throw new IllegalArgumentException ("AD_WF_ActivityResult_ID is mandatory.");
set_ValueNoCheck ("AD_WF_ActivityResult_ID", new Integer(AD_WF_ActivityResult_ID));
}
/** Get Workflow Activity Result.
@return Result of the Workflow Process Activity */
public int getAD_WF_ActivityResult_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_ActivityResult_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Workflow Activity.
@param AD_WF_Activity_ID Workflow Activity */
public void setAD_WF_Activity_ID (int AD_WF_Activity_ID)
{
if (AD_WF_Activity_ID < 1) throw new IllegalArgumentException ("AD_WF_Activity_ID is mandatory.");
set_ValueNoCheck ("AD_WF_Activity_ID", new Integer(AD_WF_Activity_ID));
}
/** Get Workflow Activity.
@return Workflow Activity */
public int getAD_WF_Activity_ID() 
{
Integer ii = (Integer)get_Value("AD_WF_Activity_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_WF_Activity_ID()));
}
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
/** Set Attribute Value.
@param AttributeValue Value of the Attribute */
public void setAttributeValue (String AttributeValue)
{
if (AttributeValue != null && AttributeValue.length() > 2000)
{
log.warning("Length > 2000 - truncated");
AttributeValue = AttributeValue.substring(0,1999);
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
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
}
