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
/** Generated Model for C_POSKeyLayout
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_C_POSKeyLayout extends PO
{
/** Standard Constructor
@param ctx context
@param C_POSKeyLayout_ID id
@param trxName transaction
*/
public X_C_POSKeyLayout (Properties ctx, int C_POSKeyLayout_ID, String trxName)
{
super (ctx, C_POSKeyLayout_ID, trxName);
/** if (C_POSKeyLayout_ID == 0)
{
setC_POSKeyLayout_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_POSKeyLayout (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=749 */
public static final int Table_ID=MTable.getTable_ID("C_POSKeyLayout");

/** TableName=C_POSKeyLayout */
public static final String Table_Name="C_POSKeyLayout";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_POSKeyLayout");

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
StringBuffer sb = new StringBuffer ("X_C_POSKeyLayout[").append(get_ID()).append("]");
return sb.toString();
}
/** Set POS Key Layout.
@param C_POSKeyLayout_ID POS Function Key Layout */
public void setC_POSKeyLayout_ID (int C_POSKeyLayout_ID)
{
if (C_POSKeyLayout_ID < 1) throw new IllegalArgumentException ("C_POSKeyLayout_ID is mandatory.");
set_ValueNoCheck ("C_POSKeyLayout_ID", Integer.valueOf(C_POSKeyLayout_ID));
}
/** Get POS Key Layout.
@return POS Function Key Layout */
public int getC_POSKeyLayout_ID() 
{
Integer ii = (Integer)get_Value("C_POSKeyLayout_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_POSKeyLayout_ID */
public static final String COLUMNNAME_C_POSKeyLayout_ID = "C_POSKeyLayout_ID";
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
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Column name Help */
public static final String COLUMNNAME_Help = "Help";
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
