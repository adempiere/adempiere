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
/** Generated Model for AD_AlertRecipient
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_AD_AlertRecipient extends PO
{
/** Standard Constructor
@param ctx context
@param AD_AlertRecipient_ID id
@param trxName transaction
*/
public X_AD_AlertRecipient (Properties ctx, int AD_AlertRecipient_ID, String trxName)
{
super (ctx, AD_AlertRecipient_ID, trxName);
/** if (AD_AlertRecipient_ID == 0)
{
setAD_AlertRecipient_ID (0);
setAD_Alert_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_AlertRecipient (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=592 */
public static final int Table_ID=MTable.getTable_ID("AD_AlertRecipient");

/** TableName=AD_AlertRecipient */
public static final String Table_Name="AD_AlertRecipient";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_AlertRecipient");

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
StringBuffer sb = new StringBuffer ("X_AD_AlertRecipient[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Alert Recipient.
@param AD_AlertRecipient_ID Recipient of the Alert Notification */
public void setAD_AlertRecipient_ID (int AD_AlertRecipient_ID)
{
if (AD_AlertRecipient_ID < 1) throw new IllegalArgumentException ("AD_AlertRecipient_ID is mandatory.");
set_ValueNoCheck ("AD_AlertRecipient_ID", Integer.valueOf(AD_AlertRecipient_ID));
}
/** Get Alert Recipient.
@return Recipient of the Alert Notification */
public int getAD_AlertRecipient_ID() 
{
Integer ii = (Integer)get_Value("AD_AlertRecipient_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Alert.
@param AD_Alert_ID Adempiere Alert */
public void setAD_Alert_ID (int AD_Alert_ID)
{
if (AD_Alert_ID < 1) throw new IllegalArgumentException ("AD_Alert_ID is mandatory.");
set_ValueNoCheck ("AD_Alert_ID", Integer.valueOf(AD_Alert_ID));
}
/** Get Alert.
@return Adempiere Alert */
public int getAD_Alert_ID() 
{
Integer ii = (Integer)get_Value("AD_Alert_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Role.
@param AD_Role_ID Responsibility Role */
public void setAD_Role_ID (int AD_Role_ID)
{
if (AD_Role_ID <= 0) set_Value ("AD_Role_ID", null);
 else 
set_Value ("AD_Role_ID", Integer.valueOf(AD_Role_ID));
}
/** Get Role.
@return Responsibility Role */
public int getAD_Role_ID() 
{
Integer ii = (Integer)get_Value("AD_Role_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID <= 0) set_Value ("AD_User_ID", null);
 else 
set_Value ("AD_User_ID", Integer.valueOf(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_User_ID()));
}
}
