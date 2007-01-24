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
/** Generated Model for C_Subscription
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_C_Subscription extends PO
{
/** Standard Constructor
@param ctx context
@param C_Subscription_ID id
@param trxName transaction
*/
public X_C_Subscription (Properties ctx, int C_Subscription_ID, String trxName)
{
super (ctx, C_Subscription_ID, trxName);
/** if (C_Subscription_ID == 0)
{
setC_BPartner_ID (0);
setC_SubscriptionType_ID (0);
setC_Subscription_ID (0);
setIsDue (false);
setM_Product_ID (0);
setName (null);
setPaidUntilDate (new Timestamp(System.currentTimeMillis()));
setRenewalDate (new Timestamp(System.currentTimeMillis()));
setStartDate (new Timestamp(System.currentTimeMillis()));
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Subscription (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=669 */
public static final int Table_ID=MTable.getTable_ID("C_Subscription");

/** TableName=C_Subscription */
public static final String Table_Name="C_Subscription";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_Subscription");

protected BigDecimal accessLevel = BigDecimal.valueOf(3);
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
StringBuffer sb = new StringBuffer ("X_C_Subscription[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID < 1) throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
set_Value ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Subscription Type.
@param C_SubscriptionType_ID Type of subscription */
public void setC_SubscriptionType_ID (int C_SubscriptionType_ID)
{
if (C_SubscriptionType_ID < 1) throw new IllegalArgumentException ("C_SubscriptionType_ID is mandatory.");
set_Value ("C_SubscriptionType_ID", Integer.valueOf(C_SubscriptionType_ID));
}
/** Get Subscription Type.
@return Type of subscription */
public int getC_SubscriptionType_ID() 
{
Integer ii = (Integer)get_Value("C_SubscriptionType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Subscription.
@param C_Subscription_ID Subscription of a Business Partner of a Product to renew */
public void setC_Subscription_ID (int C_Subscription_ID)
{
if (C_Subscription_ID < 1) throw new IllegalArgumentException ("C_Subscription_ID is mandatory.");
set_ValueNoCheck ("C_Subscription_ID", Integer.valueOf(C_Subscription_ID));
}
/** Get Subscription.
@return Subscription of a Business Partner of a Product to renew */
public int getC_Subscription_ID() 
{
Integer ii = (Integer)get_Value("C_Subscription_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Due.
@param IsDue Subscription Renewal is Due */
public void setIsDue (boolean IsDue)
{
set_Value ("IsDue", Boolean.valueOf(IsDue));
}
/** Get Due.
@return Subscription Renewal is Due */
public boolean isDue() 
{
Object oo = get_Value("IsDue");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
set_Value ("M_Product_ID", Integer.valueOf(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 60)
{
log.warning("Length > 60 - truncated");
Name = Name.substring(0,59);
}
set_Value ("Name", Name);
}
/** Get Name.
@return Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getName());
}
/** Set Paid Until.
@param PaidUntilDate Subscription is paid/valid until this date */
public void setPaidUntilDate (Timestamp PaidUntilDate)
{
if (PaidUntilDate == null) throw new IllegalArgumentException ("PaidUntilDate is mandatory.");
set_Value ("PaidUntilDate", PaidUntilDate);
}
/** Get Paid Until.
@return Subscription is paid/valid until this date */
public Timestamp getPaidUntilDate() 
{
return (Timestamp)get_Value("PaidUntilDate");
}
/** Set Renewal Date.
@param RenewalDate Renewal Date */
public void setRenewalDate (Timestamp RenewalDate)
{
if (RenewalDate == null) throw new IllegalArgumentException ("RenewalDate is mandatory.");
set_Value ("RenewalDate", RenewalDate);
}
/** Get Renewal Date.
@return Renewal Date */
public Timestamp getRenewalDate() 
{
return (Timestamp)get_Value("RenewalDate");
}
/** Set Start Date.
@param StartDate First effective day (inclusive) */
public void setStartDate (Timestamp StartDate)
{
if (StartDate == null) throw new IllegalArgumentException ("StartDate is mandatory.");
set_Value ("StartDate", StartDate);
}
/** Get Start Date.
@return First effective day (inclusive) */
public Timestamp getStartDate() 
{
return (Timestamp)get_Value("StartDate");
}
}
