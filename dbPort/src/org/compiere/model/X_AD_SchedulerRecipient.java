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
/** Generated Model for AD_SchedulerRecipient
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_AD_SchedulerRecipient extends PO
{
/** Standard Constructor
@param ctx context
@param AD_SchedulerRecipient_ID id
@param trxName transaction
*/
public X_AD_SchedulerRecipient (Properties ctx, int AD_SchedulerRecipient_ID, String trxName)
{
super (ctx, AD_SchedulerRecipient_ID, trxName);
/** if (AD_SchedulerRecipient_ID == 0)
{
setAD_SchedulerRecipient_ID (0);
setAD_Scheduler_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_SchedulerRecipient (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=704 */
public static final int Table_ID=704;

/** TableName=AD_SchedulerRecipient */
public static final String Table_Name="AD_SchedulerRecipient";

protected static KeyNamePair Model = new KeyNamePair(704,"AD_SchedulerRecipient");

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
StringBuffer sb = new StringBuffer ("X_AD_SchedulerRecipient[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Role.
@param AD_Role_ID Responsibility Role */
public void setAD_Role_ID (int AD_Role_ID)
{
if (AD_Role_ID <= 0) set_Value ("AD_Role_ID", null);
 else 
set_Value ("AD_Role_ID", new Integer(AD_Role_ID));
}
/** Get Role.
@return Responsibility Role */
public int getAD_Role_ID() 
{
Integer ii = (Integer)get_Value("AD_Role_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Scheduler Recipient.
@param AD_SchedulerRecipient_ID Recipient of the Scheduler Notification */
public void setAD_SchedulerRecipient_ID (int AD_SchedulerRecipient_ID)
{
if (AD_SchedulerRecipient_ID < 1) throw new IllegalArgumentException ("AD_SchedulerRecipient_ID is mandatory.");
set_ValueNoCheck ("AD_SchedulerRecipient_ID", new Integer(AD_SchedulerRecipient_ID));
}
/** Get Scheduler Recipient.
@return Recipient of the Scheduler Notification */
public int getAD_SchedulerRecipient_ID() 
{
Integer ii = (Integer)get_Value("AD_SchedulerRecipient_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Scheduler.
@param AD_Scheduler_ID Schedule Processes */
public void setAD_Scheduler_ID (int AD_Scheduler_ID)
{
if (AD_Scheduler_ID < 1) throw new IllegalArgumentException ("AD_Scheduler_ID is mandatory.");
set_ValueNoCheck ("AD_Scheduler_ID", new Integer(AD_Scheduler_ID));
}
/** Get Scheduler.
@return Schedule Processes */
public int getAD_Scheduler_ID() 
{
Integer ii = (Integer)get_Value("AD_Scheduler_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID <= 0) set_Value ("AD_User_ID", null);
 else 
set_Value ("AD_User_ID", new Integer(AD_User_ID));
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
