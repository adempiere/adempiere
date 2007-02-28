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
/** Generated Model for M_Attribute
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_M_Attribute extends PO
{
/** Standard Constructor
@param ctx context
@param M_Attribute_ID id
@param trxName transaction
*/
public X_M_Attribute (Properties ctx, int M_Attribute_ID, String trxName)
{
super (ctx, M_Attribute_ID, trxName);
/** if (M_Attribute_ID == 0)
{
setAttributeValueType (null);	// S
setIsInstanceAttribute (false);
setIsMandatory (false);
setM_Attribute_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_Attribute (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=562 */
public static final int Table_ID=MTable.getTable_ID("M_Attribute");

/** TableName=M_Attribute */
public static final String Table_Name="M_Attribute";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_Attribute");

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
StringBuffer sb = new StringBuffer ("X_M_Attribute[").append(get_ID()).append("]");
return sb.toString();
}

/** AttributeValueType AD_Reference_ID=326 */
public static final int ATTRIBUTEVALUETYPE_AD_Reference_ID=326;
/** List = L */
public static final String ATTRIBUTEVALUETYPE_List = "L";
/** Number = N */
public static final String ATTRIBUTEVALUETYPE_Number = "N";
/** String (max 40) = S */
public static final String ATTRIBUTEVALUETYPE_StringMax40 = "S";
/** Set Attribute Value Type.
@param AttributeValueType Type of Attribute Value */
public void setAttributeValueType (String AttributeValueType)
{
if (AttributeValueType == null) throw new IllegalArgumentException ("AttributeValueType is mandatory");
if (AttributeValueType.equals("L") || AttributeValueType.equals("N") || AttributeValueType.equals("S"));
 else throw new IllegalArgumentException ("AttributeValueType Invalid value - " + AttributeValueType + " - Reference_ID=326 - L - N - S");
if (AttributeValueType.length() > 1)
{
log.warning("Length > 1 - truncated");
AttributeValueType = AttributeValueType.substring(0,0);
}
set_Value ("AttributeValueType", AttributeValueType);
}
/** Get Attribute Value Type.
@return Type of Attribute Value */
public String getAttributeValueType() 
{
return (String)get_Value("AttributeValueType");
}
/** Column name AttributeValueType */
public static final String COLUMNNAME_AttributeValueType = "AttributeValueType";
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
/** Set Instance Attribute.
@param IsInstanceAttribute The product attribute is specific to the instance (like Serial No, Lot or Guarantee Date) */
public void setIsInstanceAttribute (boolean IsInstanceAttribute)
{
set_Value ("IsInstanceAttribute", Boolean.valueOf(IsInstanceAttribute));
}
/** Get Instance Attribute.
@return The product attribute is specific to the instance (like Serial No, Lot or Guarantee Date) */
public boolean isInstanceAttribute() 
{
Object oo = get_Value("IsInstanceAttribute");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsInstanceAttribute */
public static final String COLUMNNAME_IsInstanceAttribute = "IsInstanceAttribute";
/** Set Mandatory.
@param IsMandatory Data entry is required in this column */
public void setIsMandatory (boolean IsMandatory)
{
set_Value ("IsMandatory", Boolean.valueOf(IsMandatory));
}
/** Get Mandatory.
@return Data entry is required in this column */
public boolean isMandatory() 
{
Object oo = get_Value("IsMandatory");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsMandatory */
public static final String COLUMNNAME_IsMandatory = "IsMandatory";
/** Set Attribute Search.
@param M_AttributeSearch_ID Common Search Attribute  */
public void setM_AttributeSearch_ID (int M_AttributeSearch_ID)
{
if (M_AttributeSearch_ID <= 0) set_Value ("M_AttributeSearch_ID", null);
 else 
set_Value ("M_AttributeSearch_ID", Integer.valueOf(M_AttributeSearch_ID));
}
/** Get Attribute Search.
@return Common Search Attribute  */
public int getM_AttributeSearch_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSearch_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_AttributeSearch_ID */
public static final String COLUMNNAME_M_AttributeSearch_ID = "M_AttributeSearch_ID";
/** Set Attribute.
@param M_Attribute_ID Product Attribute */
public void setM_Attribute_ID (int M_Attribute_ID)
{
if (M_Attribute_ID < 1) throw new IllegalArgumentException ("M_Attribute_ID is mandatory.");
set_ValueNoCheck ("M_Attribute_ID", Integer.valueOf(M_Attribute_ID));
}
/** Get Attribute.
@return Product Attribute */
public int getM_Attribute_ID() 
{
Integer ii = (Integer)get_Value("M_Attribute_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Attribute_ID */
public static final String COLUMNNAME_M_Attribute_ID = "M_Attribute_ID";
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
