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
/** Generated Model for M_Product_BOM
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_M_Product_BOM extends PO
{
/** Standard Constructor
@param ctx context
@param M_Product_BOM_ID id
@param trxName transaction
*/
public X_M_Product_BOM (Properties ctx, int M_Product_BOM_ID, String trxName)
{
super (ctx, M_Product_BOM_ID, trxName);
/** if (M_Product_BOM_ID == 0)
{
setBOMQty (Env.ZERO);	// 1
setLine (0);	// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM M_Product_BOM WHERE M_Product_ID=@M_Product_ID@
setM_ProductBOM_ID (0);
setM_Product_BOM_ID (0);
setM_Product_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_Product_BOM (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=383 */
public static final int Table_ID=MTable.getTable_ID("M_Product_BOM");

/** TableName=M_Product_BOM */
public static final String Table_Name="M_Product_BOM";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_Product_BOM");

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
StringBuffer sb = new StringBuffer ("X_M_Product_BOM[").append(get_ID()).append("]");
return sb.toString();
}
/** Set BOM Quantity.
@param BOMQty Bill of Materials Quantity */
public void setBOMQty (BigDecimal BOMQty)
{
if (BOMQty == null) throw new IllegalArgumentException ("BOMQty is mandatory.");
set_Value ("BOMQty", BOMQty);
}
/** Get BOM Quantity.
@return Bill of Materials Quantity */
public BigDecimal getBOMQty() 
{
BigDecimal bd = (BigDecimal)get_Value("BOMQty");
if (bd == null) return Env.ZERO;
return bd;
}

/** BOMType AD_Reference_ID=279 */
public static final int BOMTYPE_AD_Reference_ID=279;
/** In alternative Group 1 = 1 */
public static final String BOMTYPE_InAlternativeGroup1 = "1";
/** In alternative Group 2 = 2 */
public static final String BOMTYPE_InAlternativeGroup2 = "2";
/** In alternaltve Group 3 = 3 */
public static final String BOMTYPE_InAlternaltveGroup3 = "3";
/** In alternative Group 4 = 4 */
public static final String BOMTYPE_InAlternativeGroup4 = "4";
/** In alternative Group 5 = 5 */
public static final String BOMTYPE_InAlternativeGroup5 = "5";
/** In alternative Group 6 = 6 */
public static final String BOMTYPE_InAlternativeGroup6 = "6";
/** In alternative Group 7 = 7 */
public static final String BOMTYPE_InAlternativeGroup7 = "7";
/** In alternative Group 8 = 8 */
public static final String BOMTYPE_InAlternativeGroup8 = "8";
/** In alternative Group 9 = 9 */
public static final String BOMTYPE_InAlternativeGroup9 = "9";
/** Optional Part = O */
public static final String BOMTYPE_OptionalPart = "O";
/** Standard Part = P */
public static final String BOMTYPE_StandardPart = "P";
/** Set BOM Type.
@param BOMType Type of BOM */
public void setBOMType (String BOMType)
{
if (BOMType == null || BOMType.equals("1") || BOMType.equals("2") || BOMType.equals("3") || BOMType.equals("4") || BOMType.equals("5") || BOMType.equals("6") || BOMType.equals("7") || BOMType.equals("8") || BOMType.equals("9") || BOMType.equals("O") || BOMType.equals("P"));
 else throw new IllegalArgumentException ("BOMType Invalid value - " + BOMType + " - Reference_ID=279 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - O - P");
if (BOMType != null && BOMType.length() > 1)
{
log.warning("Length > 1 - truncated");
BOMType = BOMType.substring(0,0);
}
set_Value ("BOMType", BOMType);
}
/** Get BOM Type.
@return Type of BOM */
public String getBOMType() 
{
return (String)get_Value("BOMType");
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
/** Set Line No.
@param Line Unique line for this document */
public void setLine (int Line)
{
set_Value ("Line", Integer.valueOf(Line));
}
/** Get Line No.
@return Unique line for this document */
public int getLine() 
{
Integer ii = (Integer)get_Value("Line");
if (ii == null) return 0;
return ii.intValue();
}

/** M_ProductBOM_ID AD_Reference_ID=162 */
public static final int M_PRODUCTBOM_ID_AD_Reference_ID=162;
/** Set BOM Product.
@param M_ProductBOM_ID Bill of Material Component Product */
public void setM_ProductBOM_ID (int M_ProductBOM_ID)
{
if (M_ProductBOM_ID < 1) throw new IllegalArgumentException ("M_ProductBOM_ID is mandatory.");
set_Value ("M_ProductBOM_ID", Integer.valueOf(M_ProductBOM_ID));
}
/** Get BOM Product.
@return Bill of Material Component Product */
public int getM_ProductBOM_ID() 
{
Integer ii = (Integer)get_Value("M_ProductBOM_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getM_ProductBOM_ID()));
}
/** Set BOM Line.
@param M_Product_BOM_ID BOM Line */
public void setM_Product_BOM_ID (int M_Product_BOM_ID)
{
if (M_Product_BOM_ID < 1) throw new IllegalArgumentException ("M_Product_BOM_ID is mandatory.");
set_ValueNoCheck ("M_Product_BOM_ID", Integer.valueOf(M_Product_BOM_ID));
}
/** Get BOM Line.
@return BOM Line */
public int getM_Product_BOM_ID() 
{
Integer ii = (Integer)get_Value("M_Product_BOM_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
set_ValueNoCheck ("M_Product_ID", Integer.valueOf(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
