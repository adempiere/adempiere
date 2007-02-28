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
/** Generated Model for C_RfQ_TopicSubscriberOnly
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_C_RfQ_TopicSubscriberOnly extends PO
{
/** Standard Constructor
@param ctx context
@param C_RfQ_TopicSubscriberOnly_ID id
@param trxName transaction
*/
public X_C_RfQ_TopicSubscriberOnly (Properties ctx, int C_RfQ_TopicSubscriberOnly_ID, String trxName)
{
super (ctx, C_RfQ_TopicSubscriberOnly_ID, trxName);
/** if (C_RfQ_TopicSubscriberOnly_ID == 0)
{
setC_RfQ_TopicSubscriberOnly_ID (0);
setC_RfQ_TopicSubscriber_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_RfQ_TopicSubscriberOnly (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=747 */
public static final int Table_ID=MTable.getTable_ID("C_RfQ_TopicSubscriberOnly");

/** TableName=C_RfQ_TopicSubscriberOnly */
public static final String Table_Name="C_RfQ_TopicSubscriberOnly";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_RfQ_TopicSubscriberOnly");

protected BigDecimal accessLevel = BigDecimal.valueOf(2);
/** AccessLevel
@return 2 - Client 
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
StringBuffer sb = new StringBuffer ("X_C_RfQ_TopicSubscriberOnly[").append(get_ID()).append("]");
return sb.toString();
}
/** Set RfQ Topic Subscriber Restriction.
@param C_RfQ_TopicSubscriberOnly_ID Include Subscriber only for certain products or product categories */
public void setC_RfQ_TopicSubscriberOnly_ID (int C_RfQ_TopicSubscriberOnly_ID)
{
if (C_RfQ_TopicSubscriberOnly_ID < 1) throw new IllegalArgumentException ("C_RfQ_TopicSubscriberOnly_ID is mandatory.");
set_ValueNoCheck ("C_RfQ_TopicSubscriberOnly_ID", Integer.valueOf(C_RfQ_TopicSubscriberOnly_ID));
}
/** Get RfQ Topic Subscriber Restriction.
@return Include Subscriber only for certain products or product categories */
public int getC_RfQ_TopicSubscriberOnly_ID() 
{
Integer ii = (Integer)get_Value("C_RfQ_TopicSubscriberOnly_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_RfQ_TopicSubscriberOnly_ID */
public static final String COLUMNNAME_C_RfQ_TopicSubscriberOnly_ID = "C_RfQ_TopicSubscriberOnly_ID";
/** Set RfQ Subscriber.
@param C_RfQ_TopicSubscriber_ID Request for Quotation Topic Subscriber */
public void setC_RfQ_TopicSubscriber_ID (int C_RfQ_TopicSubscriber_ID)
{
if (C_RfQ_TopicSubscriber_ID < 1) throw new IllegalArgumentException ("C_RfQ_TopicSubscriber_ID is mandatory.");
set_ValueNoCheck ("C_RfQ_TopicSubscriber_ID", Integer.valueOf(C_RfQ_TopicSubscriber_ID));
}
/** Get RfQ Subscriber.
@return Request for Quotation Topic Subscriber */
public int getC_RfQ_TopicSubscriber_ID() 
{
Integer ii = (Integer)get_Value("C_RfQ_TopicSubscriber_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_RfQ_TopicSubscriber_ID */
public static final String COLUMNNAME_C_RfQ_TopicSubscriber_ID = "C_RfQ_TopicSubscriber_ID";
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
/** Set Product Category.
@param M_Product_Category_ID Category of a Product */
public void setM_Product_Category_ID (int M_Product_Category_ID)
{
if (M_Product_Category_ID <= 0) set_Value ("M_Product_Category_ID", null);
 else 
set_Value ("M_Product_Category_ID", Integer.valueOf(M_Product_Category_ID));
}
/** Get Product Category.
@return Category of a Product */
public int getM_Product_Category_ID() 
{
Integer ii = (Integer)get_Value("M_Product_Category_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getM_Product_Category_ID()));
}
/** Column name M_Product_Category_ID */
public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_Value ("M_Product_ID", null);
 else 
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
/** Column name M_Product_ID */
public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";
}
