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
/** Generated Model for AD_TaskInstance
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:54.25 */
public class X_AD_TaskInstance extends PO
{
/** Standard Constructor
@param ctx context
@param AD_TaskInstance_ID id
@param trxName transaction
*/
public X_AD_TaskInstance (Properties ctx, int AD_TaskInstance_ID, String trxName)
{
super (ctx, AD_TaskInstance_ID, trxName);
/** if (AD_TaskInstance_ID == 0)
{
setAD_TaskInstance_ID (0);
setAD_Task_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_TaskInstance (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=125 */
public static final int Table_ID=125;

/** TableName=AD_TaskInstance */
public static final String Table_Name="AD_TaskInstance";

protected static KeyNamePair Model = new KeyNamePair(125,"AD_TaskInstance");

protected BigDecimal accessLevel = new BigDecimal(6);
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
StringBuffer sb = new StringBuffer ("X_AD_TaskInstance[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Task Instance.
@param AD_TaskInstance_ID Task Instance */
public void setAD_TaskInstance_ID (int AD_TaskInstance_ID)
{
if (AD_TaskInstance_ID < 1) throw new IllegalArgumentException ("AD_TaskInstance_ID is mandatory.");
set_ValueNoCheck ("AD_TaskInstance_ID", new Integer(AD_TaskInstance_ID));
}
/** Get Task Instance.
@return Task Instance */
public int getAD_TaskInstance_ID() 
{
Integer ii = (Integer)get_Value("AD_TaskInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_TaskInstance_ID()));
}
/** Set OS Task.
@param AD_Task_ID Operation System Task */
public void setAD_Task_ID (int AD_Task_ID)
{
if (AD_Task_ID < 1) throw new IllegalArgumentException ("AD_Task_ID is mandatory.");
set_Value ("AD_Task_ID", new Integer(AD_Task_ID));
}
/** Get OS Task.
@return Operation System Task */
public int getAD_Task_ID() 
{
Integer ii = (Integer)get_Value("AD_Task_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
