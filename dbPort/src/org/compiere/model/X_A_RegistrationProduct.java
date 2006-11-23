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
/** Generated Model for A_RegistrationProduct
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_A_RegistrationProduct extends PO
{
/** Standard Constructor
@param ctx context
@param A_RegistrationProduct_ID id
@param trxName transaction
*/
public X_A_RegistrationProduct (Properties ctx, int A_RegistrationProduct_ID, String trxName)
{
super (ctx, A_RegistrationProduct_ID, trxName);
/** if (A_RegistrationProduct_ID == 0)
{
setA_RegistrationAttribute_ID (0);
setM_Product_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_A_RegistrationProduct (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=715 */
public static final int Table_ID=715;

/** TableName=A_RegistrationProduct */
public static final String Table_Name="A_RegistrationProduct";

protected static KeyNamePair Model = new KeyNamePair(715,"A_RegistrationProduct");

protected BigDecimal accessLevel = new BigDecimal(2);
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
StringBuffer sb = new StringBuffer ("X_A_RegistrationProduct[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Registration Attribute.
@param A_RegistrationAttribute_ID Asset Registration Attribute */
public void setA_RegistrationAttribute_ID (int A_RegistrationAttribute_ID)
{
if (A_RegistrationAttribute_ID < 1) throw new IllegalArgumentException ("A_RegistrationAttribute_ID is mandatory.");
set_ValueNoCheck ("A_RegistrationAttribute_ID", new Integer(A_RegistrationAttribute_ID));
}
/** Get Registration Attribute.
@return Asset Registration Attribute */
public int getA_RegistrationAttribute_ID() 
{
Integer ii = (Integer)get_Value("A_RegistrationAttribute_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
set_ValueNoCheck ("M_Product_ID", new Integer(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
