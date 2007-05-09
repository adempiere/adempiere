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
/** Generated Model for AD_Error
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_AD_Error extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Error_ID id
@param trxName transaction
*/
public X_AD_Error (Properties ctx, int AD_Error_ID, String trxName)
{
super (ctx, AD_Error_ID, trxName);
/** if (AD_Error_ID == 0)
{
setAD_Error_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Error (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=380 */
public static final int Table_ID=MTable.getTable_ID("AD_Error");

/** TableName=AD_Error */
public static final String Table_Name="AD_Error";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Error");

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
StringBuffer sb = new StringBuffer ("X_AD_Error[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Error.
@param AD_Error_ID Error */
public void setAD_Error_ID (int AD_Error_ID)
{
if (AD_Error_ID < 1) throw new IllegalArgumentException ("AD_Error_ID is mandatory.");
set_ValueNoCheck ("AD_Error_ID", Integer.valueOf(AD_Error_ID));
}
/** Get Error.
@return Error */
public int getAD_Error_ID() 
{
Integer ii = (Integer)get_Value("AD_Error_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Error_ID */
public static final String COLUMNNAME_AD_Error_ID = "AD_Error_ID";

/** AD_Language AD_Reference_ID=106 */
public static final int AD_LANGUAGE_AD_Reference_ID=106;
/** Set Language.
@param AD_Language Language for this entity */
public void setAD_Language (String AD_Language)
{
if (AD_Language != null && AD_Language.length() > 6)
{
log.warning("Length > 6 - truncated");
AD_Language = AD_Language.substring(0,5);
}
set_Value ("AD_Language", AD_Language);
}
/** Get Language.
@return Language for this entity */
public String getAD_Language() 
{
return (String)get_Value("AD_Language");
}
/** Column name AD_Language */
public static final String COLUMNNAME_AD_Language = "AD_Language";
/** Set Validation code.
@param Code Validation Code */
public void setCode (String Code)
{
if (Code != null && Code.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Code = Code.substring(0,1999);
}
set_Value ("Code", Code);
}
/** Get Validation code.
@return Validation Code */
public String getCode() 
{
return (String)get_Value("Code");
}
/** Column name Code */
public static final String COLUMNNAME_Code = "Code";
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
