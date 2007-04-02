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
/** Generated Model for CM_AccessMedia
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_CM_AccessMedia extends PO
{
/** Standard Constructor
@param ctx context
@param CM_AccessMedia_ID id
@param trxName transaction
*/
public X_CM_AccessMedia (Properties ctx, int CM_AccessMedia_ID, String trxName)
{
super (ctx, CM_AccessMedia_ID, trxName);
/** if (CM_AccessMedia_ID == 0)
{
setCM_AccessProfile_ID (0);
setCM_Media_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_CM_AccessMedia (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=890 */
public static final int Table_ID=MTable.getTable_ID("CM_AccessMedia");

/** TableName=CM_AccessMedia */
public static final String Table_Name="CM_AccessMedia";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"CM_AccessMedia");

protected BigDecimal accessLevel = BigDecimal.valueOf(6);
/** AccessLevel
@return 6 - System - Client 
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
StringBuffer sb = new StringBuffer ("X_CM_AccessMedia[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Web Access Profile.
@param CM_AccessProfile_ID Web Access Profile */
public void setCM_AccessProfile_ID (int CM_AccessProfile_ID)
{
if (CM_AccessProfile_ID < 1) throw new IllegalArgumentException ("CM_AccessProfile_ID is mandatory.");
set_ValueNoCheck ("CM_AccessProfile_ID", Integer.valueOf(CM_AccessProfile_ID));
}
/** Get Web Access Profile.
@return Web Access Profile */
public int getCM_AccessProfile_ID() 
{
Integer ii = (Integer)get_Value("CM_AccessProfile_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CM_AccessProfile_ID */
public static final String COLUMNNAME_CM_AccessProfile_ID = "CM_AccessProfile_ID";
/** Set Media Item.
@param CM_Media_ID Contains media content like images, flash movies etc. */
public void setCM_Media_ID (int CM_Media_ID)
{
if (CM_Media_ID < 1) throw new IllegalArgumentException ("CM_Media_ID is mandatory.");
set_ValueNoCheck ("CM_Media_ID", Integer.valueOf(CM_Media_ID));
}
/** Get Media Item.
@return Contains media content like images, flash movies etc. */
public int getCM_Media_ID() 
{
Integer ii = (Integer)get_Value("CM_Media_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CM_Media_ID */
public static final String COLUMNNAME_CM_Media_ID = "CM_Media_ID";
}
