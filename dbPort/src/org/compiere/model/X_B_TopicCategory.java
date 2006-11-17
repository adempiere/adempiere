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
/** Generated Model for B_TopicCategory
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:55.468 */
public class X_B_TopicCategory extends PO
{
/** Standard Constructor
@param ctx context
@param B_TopicCategory_ID id
@param trxName transaction
*/
public X_B_TopicCategory (Properties ctx, int B_TopicCategory_ID, String trxName)
{
super (ctx, B_TopicCategory_ID, trxName);
/** if (B_TopicCategory_ID == 0)
{
setB_TopicCategory_ID (0);
setB_TopicType_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_B_TopicCategory (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=691 */
public static final int Table_ID=691;

/** TableName=B_TopicCategory */
public static final String Table_Name="B_TopicCategory";

protected static KeyNamePair Model = new KeyNamePair(691,"B_TopicCategory");

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
StringBuffer sb = new StringBuffer ("X_B_TopicCategory[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Topic Category.
@param B_TopicCategory_ID Auction Topic Category */
public void setB_TopicCategory_ID (int B_TopicCategory_ID)
{
if (B_TopicCategory_ID < 1) throw new IllegalArgumentException ("B_TopicCategory_ID is mandatory.");
set_ValueNoCheck ("B_TopicCategory_ID", new Integer(B_TopicCategory_ID));
}
/** Get Topic Category.
@return Auction Topic Category */
public int getB_TopicCategory_ID() 
{
Integer ii = (Integer)get_Value("B_TopicCategory_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Topic Type.
@param B_TopicType_ID Auction Topic Type */
public void setB_TopicType_ID (int B_TopicType_ID)
{
if (B_TopicType_ID < 1) throw new IllegalArgumentException ("B_TopicType_ID is mandatory.");
set_ValueNoCheck ("B_TopicType_ID", new Integer(B_TopicType_ID));
}
/** Get Topic Type.
@return Auction Topic Type */
public int getB_TopicType_ID() 
{
Integer ii = (Integer)get_Value("B_TopicType_ID");
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
