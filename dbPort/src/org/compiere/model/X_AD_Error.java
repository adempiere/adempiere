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
/** Generated Model for AD_Error
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:52.75 */
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
public static final int Table_ID=380;

/** TableName=AD_Error */
public static final String Table_Name="AD_Error";

protected static KeyNamePair Model = new KeyNamePair(380,"AD_Error");

protected BigDecimal accessLevel = new BigDecimal(6);
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
set_ValueNoCheck ("AD_Error_ID", new Integer(AD_Error_ID));
}
/** Get Error.
@return Error */
public int getAD_Error_ID() 
{
Integer ii = (Integer)get_Value("AD_Error_ID");
if (ii == null) return 0;
return ii.intValue();
}

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
