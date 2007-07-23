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
/** Generated Model for AD_Scheduler_Para
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_AD_Scheduler_Para extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Scheduler_Para_ID id
@param trxName transaction
*/
public X_AD_Scheduler_Para (Properties ctx, int AD_Scheduler_Para_ID, String trxName)
{
super (ctx, AD_Scheduler_Para_ID, trxName);
/** if (AD_Scheduler_Para_ID == 0)
{
setAD_Process_Para_ID (0);
setAD_Scheduler_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Scheduler_Para (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=AD_Scheduler_Para */
public static final String Table_Name="AD_Scheduler_Para";

/** AD_Table_ID=698 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

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
StringBuffer sb = new StringBuffer ("X_AD_Scheduler_Para[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Process Parameter.
@param AD_Process_Para_ID Process Parameter */
public void setAD_Process_Para_ID (int AD_Process_Para_ID)
{
if (AD_Process_Para_ID < 1) throw new IllegalArgumentException ("AD_Process_Para_ID is mandatory.");
set_ValueNoCheck ("AD_Process_Para_ID", Integer.valueOf(AD_Process_Para_ID));
}
/** Get Process Parameter.
@return Process Parameter */
public int getAD_Process_Para_ID() 
{
Integer ii = (Integer)get_Value("AD_Process_Para_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Process_Para_ID */
public static final String COLUMNNAME_AD_Process_Para_ID = "AD_Process_Para_ID";
/** Set Scheduler.
@param AD_Scheduler_ID Schedule Processes */
public void setAD_Scheduler_ID (int AD_Scheduler_ID)
{
if (AD_Scheduler_ID < 1) throw new IllegalArgumentException ("AD_Scheduler_ID is mandatory.");
set_ValueNoCheck ("AD_Scheduler_ID", Integer.valueOf(AD_Scheduler_ID));
}
/** Get Scheduler.
@return Schedule Processes */
public int getAD_Scheduler_ID() 
{
Integer ii = (Integer)get_Value("AD_Scheduler_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Scheduler_ID */
public static final String COLUMNNAME_AD_Scheduler_ID = "AD_Scheduler_ID";
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
/** Set Default Parameter.
@param ParameterDefault Default value of the parameter */
public void setParameterDefault (String ParameterDefault)
{
if (ParameterDefault != null && ParameterDefault.length() > 60)
{
log.warning("Length > 60 - truncated");
ParameterDefault = ParameterDefault.substring(0,59);
}
set_Value ("ParameterDefault", ParameterDefault);
}
/** Get Default Parameter.
@return Default value of the parameter */
public String getParameterDefault() 
{
return (String)get_Value("ParameterDefault");
}
/** Column name ParameterDefault */
public static final String COLUMNNAME_ParameterDefault = "ParameterDefault";
}
