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
/** Generated Model for K_EntryRelated
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_K_EntryRelated extends PO
{
/** Standard Constructor
@param ctx context
@param K_EntryRelated_ID id
@param trxName transaction
*/
public X_K_EntryRelated (Properties ctx, int K_EntryRelated_ID, String trxName)
{
super (ctx, K_EntryRelated_ID, trxName);
/** if (K_EntryRelated_ID == 0)
{
setK_EntryRelated_ID (0);
setK_Entry_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_K_EntryRelated (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=610 */
public static final int Table_ID=610;

/** TableName=K_EntryRelated */
public static final String Table_Name="K_EntryRelated";

protected static KeyNamePair Model = new KeyNamePair(610,"K_EntryRelated");

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
StringBuffer sb = new StringBuffer ("X_K_EntryRelated[").append(get_ID()).append("]");
return sb.toString();
}

/** K_EntryRelated_ID AD_Reference_ID=285 */
public static final int K_ENTRYRELATED_ID_AD_Reference_ID=285;
/** Set Related Entry.
@param K_EntryRelated_ID Related Entry for this Enntry */
public void setK_EntryRelated_ID (int K_EntryRelated_ID)
{
if (K_EntryRelated_ID < 1) throw new IllegalArgumentException ("K_EntryRelated_ID is mandatory.");
set_ValueNoCheck ("K_EntryRelated_ID", new Integer(K_EntryRelated_ID));
}
/** Get Related Entry.
@return Related Entry for this Enntry */
public int getK_EntryRelated_ID() 
{
Integer ii = (Integer)get_Value("K_EntryRelated_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getK_EntryRelated_ID()));
}
/** Set Entry.
@param K_Entry_ID Knowledge Entry */
public void setK_Entry_ID (int K_Entry_ID)
{
if (K_Entry_ID < 1) throw new IllegalArgumentException ("K_Entry_ID is mandatory.");
set_ValueNoCheck ("K_Entry_ID", new Integer(K_Entry_ID));
}
/** Get Entry.
@return Knowledge Entry */
public int getK_Entry_ID() 
{
Integer ii = (Integer)get_Value("K_Entry_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name != null && Name.length() > 60)
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
}
