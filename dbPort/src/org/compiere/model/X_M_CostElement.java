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
/** Generated Model for M_CostElement
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_M_CostElement extends PO
{
/** Standard Constructor
@param ctx context
@param M_CostElement_ID id
@param trxName transaction
*/
public X_M_CostElement (Properties ctx, int M_CostElement_ID, String trxName)
{
super (ctx, M_CostElement_ID, trxName);
/** if (M_CostElement_ID == 0)
{
setCostElementType (null);
setIsCalculated (false);
setM_CostElement_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_CostElement (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=770 */
public static final int Table_ID=MTable.getTable_ID("M_CostElement");

/** TableName=M_CostElement */
public static final String Table_Name="M_CostElement";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_CostElement");

protected BigDecimal accessLevel = BigDecimal.valueOf(2);
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
StringBuffer sb = new StringBuffer ("X_M_CostElement[").append(get_ID()).append("]");
return sb.toString();
}

/** CostElementType AD_Reference_ID=338 */
public static final int COSTELEMENTTYPE_AD_Reference_ID=338;
/** Burden (M.Overhead) = B */
public static final String COSTELEMENTTYPE_BurdenMOverhead = "B";
/** Material = M */
public static final String COSTELEMENTTYPE_Material = "M";
/** Overhead = O */
public static final String COSTELEMENTTYPE_Overhead = "O";
/** Resource = R */
public static final String COSTELEMENTTYPE_Resource = "R";
/** Outside Processing = X */
public static final String COSTELEMENTTYPE_OutsideProcessing = "X";
/** Set Cost Element Type.
@param CostElementType Type of Cost Element */
public void setCostElementType (String CostElementType)
{
if (CostElementType == null) throw new IllegalArgumentException ("CostElementType is mandatory");
if (CostElementType.equals("B") || CostElementType.equals("M") || CostElementType.equals("O") || CostElementType.equals("R") || CostElementType.equals("X"));
 else throw new IllegalArgumentException ("CostElementType Invalid value - " + CostElementType + " - Reference_ID=338 - B - M - O - R - X");
if (CostElementType.length() > 1)
{
log.warning("Length > 1 - truncated");
CostElementType = CostElementType.substring(0,0);
}
set_Value ("CostElementType", CostElementType);
}
/** Get Cost Element Type.
@return Type of Cost Element */
public String getCostElementType() 
{
return (String)get_Value("CostElementType");
}
/** Column name CostElementType */
public static final String COLUMNNAME_CostElementType = "CostElementType";

/** CostingMethod AD_Reference_ID=122 */
public static final int COSTINGMETHOD_AD_Reference_ID=122;
/** Average PO = A */
public static final String COSTINGMETHOD_AveragePO = "A";
/** Fifo = F */
public static final String COSTINGMETHOD_Fifo = "F";
/** Average Invoice = I */
public static final String COSTINGMETHOD_AverageInvoice = "I";
/** Lifo = L */
public static final String COSTINGMETHOD_Lifo = "L";
/** Standard Costing = S */
public static final String COSTINGMETHOD_StandardCosting = "S";
/** User Defined = U */
public static final String COSTINGMETHOD_UserDefined = "U";
/** Last Invoice = i */
public static final String COSTINGMETHOD_LastInvoice = "i";
/** Last PO Price = p */
public static final String COSTINGMETHOD_LastPOPrice = "p";
/** _ = x */
public static final String COSTINGMETHOD__ = "x";
/** Set Costing Method.
@param CostingMethod Indicates how Costs will be calculated */
public void setCostingMethod (String CostingMethod)
{
if (CostingMethod == null || CostingMethod.equals("A") || CostingMethod.equals("F") || CostingMethod.equals("I") || CostingMethod.equals("L") || CostingMethod.equals("S") || CostingMethod.equals("U") || CostingMethod.equals("i") || CostingMethod.equals("p") || CostingMethod.equals("x"));
 else throw new IllegalArgumentException ("CostingMethod Invalid value - " + CostingMethod + " - Reference_ID=122 - A - F - I - L - S - U - i - p - x");
if (CostingMethod != null && CostingMethod.length() > 1)
{
log.warning("Length > 1 - truncated");
CostingMethod = CostingMethod.substring(0,0);
}
set_Value ("CostingMethod", CostingMethod);
}
/** Get Costing Method.
@return Indicates how Costs will be calculated */
public String getCostingMethod() 
{
return (String)get_Value("CostingMethod");
}
/** Column name CostingMethod */
public static final String COLUMNNAME_CostingMethod = "CostingMethod";
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
/** Set Calculated.
@param IsCalculated The value is calculated by the system */
public void setIsCalculated (boolean IsCalculated)
{
set_Value ("IsCalculated", Boolean.valueOf(IsCalculated));
}
/** Get Calculated.
@return The value is calculated by the system */
public boolean isCalculated() 
{
Object oo = get_Value("IsCalculated");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsCalculated */
public static final String COLUMNNAME_IsCalculated = "IsCalculated";
/** Set Cost Element.
@param M_CostElement_ID Product Cost Element */
public void setM_CostElement_ID (int M_CostElement_ID)
{
if (M_CostElement_ID < 1) throw new IllegalArgumentException ("M_CostElement_ID is mandatory.");
set_ValueNoCheck ("M_CostElement_ID", Integer.valueOf(M_CostElement_ID));
}
/** Get Cost Element.
@return Product Cost Element */
public int getM_CostElement_ID() 
{
Integer ii = (Integer)get_Value("M_CostElement_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_CostElement_ID */
public static final String COLUMNNAME_M_CostElement_ID = "M_CostElement_ID";
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
