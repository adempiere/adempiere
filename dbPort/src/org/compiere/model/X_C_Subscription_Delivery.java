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
/** Generated Model for C_Subscription_Delivery
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_C_Subscription_Delivery extends PO
{
/** Standard Constructor
@param ctx context
@param C_Subscription_Delivery_ID id
@param trxName transaction
*/
public X_C_Subscription_Delivery (Properties ctx, int C_Subscription_Delivery_ID, String trxName)
{
super (ctx, C_Subscription_Delivery_ID, trxName);
/** if (C_Subscription_Delivery_ID == 0)
{
setC_Subscription_Delivery_ID (0);
setC_Subscription_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Subscription_Delivery (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=667 */
public static final int Table_ID=667;

/** TableName=C_Subscription_Delivery */
public static final String Table_Name="C_Subscription_Delivery";

protected static KeyNamePair Model = new KeyNamePair(667,"C_Subscription_Delivery");

protected BigDecimal accessLevel = new BigDecimal(3);
/** AccessLevel
@return 3 - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_C_Subscription_Delivery[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Subscription Delivery.
@param C_Subscription_Delivery_ID Optional Delivery Record for a Subscription */
public void setC_Subscription_Delivery_ID (int C_Subscription_Delivery_ID)
{
if (C_Subscription_Delivery_ID < 1) throw new IllegalArgumentException ("C_Subscription_Delivery_ID is mandatory.");
set_ValueNoCheck ("C_Subscription_Delivery_ID", new Integer(C_Subscription_Delivery_ID));
}
/** Get Subscription Delivery.
@return Optional Delivery Record for a Subscription */
public int getC_Subscription_Delivery_ID() 
{
Integer ii = (Integer)get_Value("C_Subscription_Delivery_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_Subscription_Delivery_ID()));
}
/** Set Subscription.
@param C_Subscription_ID Subscription of a Business Partner of a Product to renew */
public void setC_Subscription_ID (int C_Subscription_ID)
{
if (C_Subscription_ID < 1) throw new IllegalArgumentException ("C_Subscription_ID is mandatory.");
set_ValueNoCheck ("C_Subscription_ID", new Integer(C_Subscription_ID));
}
/** Get Subscription.
@return Subscription of a Business Partner of a Product to renew */
public int getC_Subscription_ID() 
{
Integer ii = (Integer)get_Value("C_Subscription_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
