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
/** Generated Model for K_EntryCategory
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_K_EntryCategory extends PO
{
/** Standard Constructor
@param ctx context
@param K_EntryCategory_ID id
@param trxName transaction
*/
public X_K_EntryCategory (Properties ctx, int K_EntryCategory_ID, String trxName)
{
super (ctx, K_EntryCategory_ID, trxName);
/** if (K_EntryCategory_ID == 0)
{
setK_CategoryValue_ID (0);
setK_Category_ID (0);
setK_Entry_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_K_EntryCategory (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=611 */
public static final int Table_ID=MTable.getTable_ID("K_EntryCategory");

/** TableName=K_EntryCategory */
public static final String Table_Name="K_EntryCategory";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"K_EntryCategory");

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
StringBuffer sb = new StringBuffer ("X_K_EntryCategory[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Category Value.
@param K_CategoryValue_ID The value of the category */
public void setK_CategoryValue_ID (int K_CategoryValue_ID)
{
if (K_CategoryValue_ID < 1) throw new IllegalArgumentException ("K_CategoryValue_ID is mandatory.");
set_Value ("K_CategoryValue_ID", Integer.valueOf(K_CategoryValue_ID));
}
/** Get Category Value.
@return The value of the category */
public int getK_CategoryValue_ID() 
{
Integer ii = (Integer)get_Value("K_CategoryValue_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getK_CategoryValue_ID()));
}
/** Set Knowledge Category.
@param K_Category_ID Knowledge Category */
public void setK_Category_ID (int K_Category_ID)
{
if (K_Category_ID < 1) throw new IllegalArgumentException ("K_Category_ID is mandatory.");
set_Value ("K_Category_ID", Integer.valueOf(K_Category_ID));
}
/** Get Knowledge Category.
@return Knowledge Category */
public int getK_Category_ID() 
{
Integer ii = (Integer)get_Value("K_Category_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Entry.
@param K_Entry_ID Knowledge Entry */
public void setK_Entry_ID (int K_Entry_ID)
{
if (K_Entry_ID < 1) throw new IllegalArgumentException ("K_Entry_ID is mandatory.");
set_ValueNoCheck ("K_Entry_ID", Integer.valueOf(K_Entry_ID));
}
/** Get Entry.
@return Knowledge Entry */
public int getK_Entry_ID() 
{
Integer ii = (Integer)get_Value("K_Entry_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
