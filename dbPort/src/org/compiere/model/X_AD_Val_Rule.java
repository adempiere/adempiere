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
/** Generated Model for AD_Val_Rule
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_AD_Val_Rule extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Val_Rule_ID id
@param trxName transaction
*/
public X_AD_Val_Rule (Properties ctx, int AD_Val_Rule_ID, String trxName)
{
super (ctx, AD_Val_Rule_ID, trxName);
/** if (AD_Val_Rule_ID == 0)
{
setAD_Val_Rule_ID (0);
setEntityType (null);	// U
setName (null);
setType (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Val_Rule (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=108 */
public static final int Table_ID=108;

/** TableName=AD_Val_Rule */
public static final String Table_Name="AD_Val_Rule";

protected static KeyNamePair Model = new KeyNamePair(108,"AD_Val_Rule");

protected BigDecimal accessLevel = new BigDecimal(4);
/** AccessLevel
@return 4 - System 
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
StringBuffer sb = new StringBuffer ("X_AD_Val_Rule[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Dynamic Validation.
@param AD_Val_Rule_ID Dynamic Validation Rule */
public void setAD_Val_Rule_ID (int AD_Val_Rule_ID)
{
if (AD_Val_Rule_ID < 1) throw new IllegalArgumentException ("AD_Val_Rule_ID is mandatory.");
set_ValueNoCheck ("AD_Val_Rule_ID", new Integer(AD_Val_Rule_ID));
}
/** Get Dynamic Validation.
@return Dynamic Validation Rule */
public int getAD_Val_Rule_ID() 
{
Integer ii = (Integer)get_Value("AD_Val_Rule_ID");
if (ii == null) return 0;
return ii.intValue();
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

/** EntityType AD_Reference_ID=389 */
public static final int ENTITYTYPE_AD_Reference_ID=389;
/** Set Entity Type.
@param EntityType Dictionary Entity Type;
 Determines ownership and synchronization */
public void setEntityType (String EntityType)
{
if (EntityType.length() > 4)
{
log.warning("Length > 4 - truncated");
EntityType = EntityType.substring(0,3);
}
set_Value ("EntityType", EntityType);
}
/** Get Entity Type.
@return Dictionary Entity Type;
 Determines ownership and synchronization */
public String getEntityType() 
{
return (String)get_Value("EntityType");
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

/** Type AD_Reference_ID=101 */
public static final int TYPE_AD_Reference_ID=101;
/** Java Script = E */
public static final String TYPE_JavaScript = "E";
/** Java Language = J */
public static final String TYPE_JavaLanguage = "J";
/** SQL = S */
public static final String TYPE_SQL = "S";
/** Set Type.
@param Type Type of Validation (SQL, Java Script, Java Language) */
public void setType (String Type)
{
if (Type == null) throw new IllegalArgumentException ("Type is mandatory");
if (Type.equals("E") || Type.equals("J") || Type.equals("S"));
 else throw new IllegalArgumentException ("Type Invalid value - " + Type + " - Reference_ID=101 - E - J - S");
if (Type.length() > 1)
{
log.warning("Length > 1 - truncated");
Type = Type.substring(0,0);
}
set_Value ("Type", Type);
}
/** Get Type.
@return Type of Validation (SQL, Java Script, Java Language) */
public String getType() 
{
return (String)get_Value("Type");
}
}
