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
/** Generated Model for M_AttributeInstance
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_M_AttributeInstance extends PO
{
/** Standard Constructor
@param ctx context
@param M_AttributeInstance_ID id
@param trxName transaction
*/
public X_M_AttributeInstance (Properties ctx, int M_AttributeInstance_ID, String trxName)
{
super (ctx, M_AttributeInstance_ID, trxName);
/** if (M_AttributeInstance_ID == 0)
{
setM_AttributeSetInstance_ID (0);
setM_Attribute_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_AttributeInstance (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=561 */
public static final int Table_ID=561;

/** TableName=M_AttributeInstance */
public static final String Table_Name="M_AttributeInstance";

protected static KeyNamePair Model = new KeyNamePair(561,"M_AttributeInstance");

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
StringBuffer sb = new StringBuffer ("X_M_AttributeInstance[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Attribute Set Instance.
@param M_AttributeSetInstance_ID Product Attribute Set Instance */
public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
{
if (M_AttributeSetInstance_ID < 0) throw new IllegalArgumentException ("M_AttributeSetInstance_ID is mandatory.");
set_ValueNoCheck ("M_AttributeSetInstance_ID", new Integer(M_AttributeSetInstance_ID));
}
/** Get Attribute Set Instance.
@return Product Attribute Set Instance */
public int getM_AttributeSetInstance_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSetInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Attribute Value.
@param M_AttributeValue_ID Product Attribute Value */
public void setM_AttributeValue_ID (int M_AttributeValue_ID)
{
if (M_AttributeValue_ID <= 0) set_Value ("M_AttributeValue_ID", null);
 else 
set_Value ("M_AttributeValue_ID", new Integer(M_AttributeValue_ID));
}
/** Get Attribute Value.
@return Product Attribute Value */
public int getM_AttributeValue_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeValue_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getM_AttributeValue_ID()));
}
/** Set Attribute.
@param M_Attribute_ID Product Attribute */
public void setM_Attribute_ID (int M_Attribute_ID)
{
if (M_Attribute_ID < 1) throw new IllegalArgumentException ("M_Attribute_ID is mandatory.");
set_ValueNoCheck ("M_Attribute_ID", new Integer(M_Attribute_ID));
}
/** Get Attribute.
@return Product Attribute */
public int getM_Attribute_ID() 
{
Integer ii = (Integer)get_Value("M_Attribute_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Search Key.
@param Value Search key for the record in the format required - must be unique */
public void setValue (String Value)
{
if (Value != null && Value.length() > 40)
{
log.warning("Length > 40 - truncated");
Value = Value.substring(0,39);
}
set_Value ("Value", Value);
}
/** Get Search Key.
@return Search key for the record in the format required - must be unique */
public String getValue() 
{
return (String)get_Value("Value");
}
/** Set Value.
@param ValueNumber Numeric Value */
public void setValueNumber (BigDecimal ValueNumber)
{
set_Value ("ValueNumber", ValueNumber);
}
/** Get Value.
@return Numeric Value */
public BigDecimal getValueNumber() 
{
BigDecimal bd = (BigDecimal)get_Value("ValueNumber");
if (bd == null) return Env.ZERO;
return bd;
}
}
