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
package org.eevolution.model;

/** Generated Model - DO NOT CHANGE */
import org.compiere.model.*;
import java.util.*;
import java.sql.*;
import java.math.*;
import org.compiere.util.*;
/** Generated Model for PP_ProfileBOMLine
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_PP_ProfileBOMLine extends PO
{
/** Standard Constructor
@param ctx context
@param PP_ProfileBOMLine_ID id
@param trxName transaction
*/
public X_PP_ProfileBOMLine (Properties ctx, int PP_ProfileBOMLine_ID, String trxName)
{
super (ctx, PP_ProfileBOMLine_ID, trxName);
/** if (PP_ProfileBOMLine_ID == 0)
{
setM_Attribute_ID (0);
setPP_ProfileBOMLine_ID (0);
setPP_ProfileBOM_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_PP_ProfileBOMLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=PP_ProfileBOMLine */
public static final String Table_Name="PP_ProfileBOMLine";

/** AD_Table_ID=50016 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

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
StringBuffer sb = new StringBuffer ("X_PP_ProfileBOMLine[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Printed.
@param IsPrinted Indicates if this document / line is printed */
public void setIsPrinted (boolean IsPrinted)
{
set_Value ("IsPrinted", Boolean.valueOf(IsPrinted));
}
/** Get Printed.
@return Indicates if this document / line is printed */
public boolean isPrinted() 
{
Object oo = get_Value("IsPrinted");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPrinted */
public static final String COLUMNNAME_IsPrinted = "IsPrinted";
/** Set Attribute.
@param M_Attribute_ID Product Attribute */
public void setM_Attribute_ID (int M_Attribute_ID)
{
if (M_Attribute_ID < 1) throw new IllegalArgumentException ("M_Attribute_ID is mandatory.");
set_Value ("M_Attribute_ID", Integer.valueOf(M_Attribute_ID));
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
/** Set Profile BOM Line.
@param PP_ProfileBOMLine_ID Profile BOM Line */
public void setPP_ProfileBOMLine_ID (int PP_ProfileBOMLine_ID)
{
if (PP_ProfileBOMLine_ID < 1) throw new IllegalArgumentException ("PP_ProfileBOMLine_ID is mandatory.");
set_ValueNoCheck ("PP_ProfileBOMLine_ID", Integer.valueOf(PP_ProfileBOMLine_ID));
}
/** Get Profile BOM Line.
@return Profile BOM Line */
public int getPP_ProfileBOMLine_ID() 
{
Integer ii = (Integer)get_Value("PP_ProfileBOMLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PP_ProfileBOMLine_ID */
public static final String COLUMNNAME_PP_ProfileBOMLine_ID = "PP_ProfileBOMLine_ID";
/** Set Profile BOM.
@param PP_ProfileBOM_ID Profile BOM */
public void setPP_ProfileBOM_ID (int PP_ProfileBOM_ID)
{
if (PP_ProfileBOM_ID < 1) throw new IllegalArgumentException ("PP_ProfileBOM_ID is mandatory.");
set_ValueNoCheck ("PP_ProfileBOM_ID", Integer.valueOf(PP_ProfileBOM_ID));
}
/** Get Profile BOM.
@return Profile BOM */
public int getPP_ProfileBOM_ID() 
{
Integer ii = (Integer)get_Value("PP_ProfileBOM_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PP_ProfileBOM_ID */
public static final String COLUMNNAME_PP_ProfileBOM_ID = "PP_ProfileBOM_ID";
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
/** Column name Value */
public static final String COLUMNNAME_Value = "Value";
/** Set Max. Value.
@param ValueMax Maximum Value for a field */
public void setValueMax (BigDecimal ValueMax)
{
set_Value ("ValueMax", ValueMax);
}
/** Get Max. Value.
@return Maximum Value for a field */
public BigDecimal getValueMax() 
{
BigDecimal bd = (BigDecimal)get_Value("ValueMax");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name ValueMax */
public static final String COLUMNNAME_ValueMax = "ValueMax";
/** Set Min. Value.
@param ValueMin Minimum Value for a field */
public void setValueMin (BigDecimal ValueMin)
{
set_Value ("ValueMin", ValueMin);
}
/** Get Min. Value.
@return Minimum Value for a field */
public BigDecimal getValueMin() 
{
BigDecimal bd = (BigDecimal)get_Value("ValueMin");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name ValueMin */
public static final String COLUMNNAME_ValueMin = "ValueMin";
}
