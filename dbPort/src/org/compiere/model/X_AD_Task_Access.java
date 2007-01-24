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
/** Generated Model for AD_Task_Access
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_AD_Task_Access extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Task_Access_ID id
@param trxName transaction
*/
public X_AD_Task_Access (Properties ctx, int AD_Task_Access_ID, String trxName)
{
super (ctx, AD_Task_Access_ID, trxName);
/** if (AD_Task_Access_ID == 0)
{
setAD_Role_ID (0);
setAD_Task_ID (0);
setIsReadWrite (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Task_Access (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=199 */
public static final int Table_ID=MTable.getTable_ID("AD_Task_Access");

/** TableName=AD_Task_Access */
public static final String Table_Name="AD_Task_Access";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Task_Access");

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
StringBuffer sb = new StringBuffer ("X_AD_Task_Access[").append(get_ID()).append("]");
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
/** Set OS Task.
@param AD_Task_ID Operation System Task */
public void setAD_Task_ID (int AD_Task_ID)
{
if (AD_Task_ID < 1) throw new IllegalArgumentException ("AD_Task_ID is mandatory.");
set_ValueNoCheck ("AD_Task_ID", Integer.valueOf(AD_Task_ID));
}
/** Get OS Task.
@return Operation System Task */
public int getAD_Task_ID() 
{
Integer ii = (Integer)get_Value("AD_Task_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Read Write.
@param IsReadWrite Field is read / write */
public void setIsReadWrite (boolean IsReadWrite)
{
set_Value ("IsReadWrite", Boolean.valueOf(IsReadWrite));
}
/** Get Read Write.
@return Field is read / write */
public boolean isReadWrite() 
{
Object oo = get_Value("IsReadWrite");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
}
