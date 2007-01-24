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
/** Generated Model for C_TaxCategory
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_C_TaxCategory extends PO
{
/** Standard Constructor
@param ctx context
@param C_TaxCategory_ID id
@param trxName transaction
*/
public X_C_TaxCategory (Properties ctx, int C_TaxCategory_ID, String trxName)
{
super (ctx, C_TaxCategory_ID, trxName);
/** if (C_TaxCategory_ID == 0)
{
setC_TaxCategory_ID (0);
setIsDefault (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_TaxCategory (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=252 */
public static final int Table_ID=MTable.getTable_ID("C_TaxCategory");

/** TableName=C_TaxCategory */
public static final String Table_Name="C_TaxCategory";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_TaxCategory");

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
StringBuffer sb = new StringBuffer ("X_C_TaxCategory[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Tax Category.
@param C_TaxCategory_ID Tax Category */
public void setC_TaxCategory_ID (int C_TaxCategory_ID)
{
if (C_TaxCategory_ID < 1) throw new IllegalArgumentException ("C_TaxCategory_ID is mandatory.");
set_ValueNoCheck ("C_TaxCategory_ID", Integer.valueOf(C_TaxCategory_ID));
}
/** Get Tax Category.
@return Tax Category */
public int getC_TaxCategory_ID() 
{
Integer ii = (Integer)get_Value("C_TaxCategory_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Commodity Code.
@param CommodityCode Commodity code used for tax calculation */
public void setCommodityCode (String CommodityCode)
{
if (CommodityCode != null && CommodityCode.length() > 20)
{
log.warning("Length > 20 - truncated");
CommodityCode = CommodityCode.substring(0,19);
}
set_Value ("CommodityCode", CommodityCode);
}
/** Get Commodity Code.
@return Commodity code used for tax calculation */
public String getCommodityCode() 
{
return (String)get_Value("CommodityCode");
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
/** Set Default.
@param IsDefault Default value */
public void setIsDefault (boolean IsDefault)
{
set_Value ("IsDefault", Boolean.valueOf(IsDefault));
}
/** Get Default.
@return Default value */
public boolean isDefault() 
{
Object oo = get_Value("IsDefault");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
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
}
