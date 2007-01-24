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
/** Generated Model for AD_Role_OrgAccess
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_AD_Role_OrgAccess extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Role_OrgAccess_ID id
@param trxName transaction
*/
public X_AD_Role_OrgAccess (Properties ctx, int AD_Role_OrgAccess_ID, String trxName)
{
super (ctx, AD_Role_OrgAccess_ID, trxName);
/** if (AD_Role_OrgAccess_ID == 0)
{
setAD_Role_ID (0);
setIsReadOnly (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Role_OrgAccess (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=422 */
public static final int Table_ID=MTable.getTable_ID("AD_Role_OrgAccess");

/** TableName=AD_Role_OrgAccess */
public static final String Table_Name="AD_Role_OrgAccess";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Role_OrgAccess");

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
StringBuffer sb = new StringBuffer ("X_AD_Role_OrgAccess[").append(get_ID()).append("]");
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
/** Set Read Only.
@param IsReadOnly Field is read only */
public void setIsReadOnly (boolean IsReadOnly)
{
set_Value ("IsReadOnly", Boolean.valueOf(IsReadOnly));
}
/** Get Read Only.
@return Field is read only */
public boolean isReadOnly() 
{
Object oo = get_Value("IsReadOnly");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
}
