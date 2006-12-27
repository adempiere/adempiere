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
/** Generated Model for R_GroupUpdates
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_R_GroupUpdates extends PO
{
/** Standard Constructor
@param ctx context
@param R_GroupUpdates_ID id
@param trxName transaction
*/
public X_R_GroupUpdates (Properties ctx, int R_GroupUpdates_ID, String trxName)
{
super (ctx, R_GroupUpdates_ID, trxName);
/** if (R_GroupUpdates_ID == 0)
{
setAD_User_ID (0);
setIsSelfService (false);
setR_Group_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_R_GroupUpdates (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=786 */
public static final int Table_ID=MTable.getTable_ID("R_GroupUpdates");

/** TableName=R_GroupUpdates */
public static final String Table_Name="R_GroupUpdates";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"R_GroupUpdates");

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
StringBuffer sb = new StringBuffer ("X_R_GroupUpdates[").append(get_ID()).append("]");
return sb.toString();
}
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID < 1) throw new IllegalArgumentException ("AD_User_ID is mandatory.");
set_ValueNoCheck ("AD_User_ID", Integer.valueOf(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Self-Service.
@param IsSelfService This is a Self-Service entry or this entry can be changed via Self-Service */
public void setIsSelfService (boolean IsSelfService)
{
set_Value ("IsSelfService", Boolean.valueOf(IsSelfService));
}
/** Get Self-Service.
@return This is a Self-Service entry or this entry can be changed via Self-Service */
public boolean isSelfService() 
{
Object oo = get_Value("IsSelfService");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Group.
@param R_Group_ID Request Group */
public void setR_Group_ID (int R_Group_ID)
{
if (R_Group_ID < 1) throw new IllegalArgumentException ("R_Group_ID is mandatory.");
set_ValueNoCheck ("R_Group_ID", Integer.valueOf(R_Group_ID));
}
/** Get Group.
@return Request Group */
public int getR_Group_ID() 
{
Integer ii = (Integer)get_Value("R_Group_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
