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
/** Generated Model for K_CategoryValue
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_K_CategoryValue extends PO
{
/** Standard Constructor
@param ctx context
@param K_CategoryValue_ID id
@param trxName transaction
*/
public X_K_CategoryValue (Properties ctx, int K_CategoryValue_ID, String trxName)
{
super (ctx, K_CategoryValue_ID, trxName);
/** if (K_CategoryValue_ID == 0)
{
setK_CategoryValue_ID (0);
setK_Category_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_K_CategoryValue (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=614 */
public static final int Table_ID=MTable.getTable_ID("K_CategoryValue");

/** TableName=K_CategoryValue */
public static final String Table_Name="K_CategoryValue";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"K_CategoryValue");

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
StringBuffer sb = new StringBuffer ("X_K_CategoryValue[").append(get_ID()).append("]");
return sb.toString();
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
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set Category Value.
@param K_CategoryValue_ID The value of the category */
public void setK_CategoryValue_ID (int K_CategoryValue_ID)
{
if (K_CategoryValue_ID < 1) throw new IllegalArgumentException ("K_CategoryValue_ID is mandatory.");
set_ValueNoCheck ("K_CategoryValue_ID", Integer.valueOf(K_CategoryValue_ID));
}
/** Get Category Value.
@return The value of the category */
public int getK_CategoryValue_ID() 
{
Integer ii = (Integer)get_Value("K_CategoryValue_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name K_CategoryValue_ID */
public static final String COLUMNNAME_K_CategoryValue_ID = "K_CategoryValue_ID";
/** Set Knowledge Category.
@param K_Category_ID Knowledge Category */
public void setK_Category_ID (int K_Category_ID)
{
if (K_Category_ID < 1) throw new IllegalArgumentException ("K_Category_ID is mandatory.");
set_ValueNoCheck ("K_Category_ID", Integer.valueOf(K_Category_ID));
}
/** Get Knowledge Category.
@return Knowledge Category */
public int getK_Category_ID() 
{
Integer ii = (Integer)get_Value("K_Category_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name K_Category_ID */
public static final String COLUMNNAME_K_Category_ID = "K_Category_ID";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
}
