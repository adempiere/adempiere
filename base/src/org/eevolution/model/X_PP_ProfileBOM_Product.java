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
/** Generated Model for PP_ProfileBOM_Product
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_PP_ProfileBOM_Product extends PO
{
/** Standard Constructor
@param ctx context
@param PP_ProfileBOM_Product_ID id
@param trxName transaction
*/
public X_PP_ProfileBOM_Product (Properties ctx, int PP_ProfileBOM_Product_ID, String trxName)
{
super (ctx, PP_ProfileBOM_Product_ID, trxName);
/** if (PP_ProfileBOM_Product_ID == 0)
{
setM_Product_ID (0);
setPP_ProfileBOM_ID (0);
setPP_ProfileBOM_Product_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_PP_ProfileBOM_Product (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=PP_ProfileBOM_Product */
public static final String Table_Name="PP_ProfileBOM_Product";

/** AD_Table_ID=50012 */
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
StringBuffer sb = new StringBuffer ("X_PP_ProfileBOM_Product[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Alias.
@param Alias Defines an alternate method of indicating an account combination. */
public void setAlias (boolean Alias)
{
set_Value ("Alias", Boolean.valueOf(Alias));
}
/** Get Alias.
@return Defines an alternate method of indicating an account combination. */
public boolean isAlias() 
{
Object oo = get_Value("Alias");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Alias */
public static final String COLUMNNAME_Alias = "Alias";
/** Set UOM.
@param C_UOM_ID Unit of Measure */
public void setC_UOM_ID (int C_UOM_ID)
{
if (C_UOM_ID <= 0) set_Value ("C_UOM_ID", null);
 else 
set_Value ("C_UOM_ID", Integer.valueOf(C_UOM_ID));
}
/** Get UOM.
@return Unit of Measure */
public int getC_UOM_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_UOM_ID */
public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";
/** Set Generate To.
@param GenerateTo Generate To */
public void setGenerateTo (String GenerateTo)
{
if (GenerateTo != null && GenerateTo.length() > 1)
{
log.warning("Length > 1 - truncated");
GenerateTo = GenerateTo.substring(0,0);
}
set_Value ("GenerateTo", GenerateTo);
}
/** Get Generate To.
@return Generate To */
public String getGenerateTo() 
{
return (String)get_Value("GenerateTo");
}
/** Column name GenerateTo */
public static final String COLUMNNAME_GenerateTo = "GenerateTo";
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
/** Column name Line */
public static final String COLUMNNAME_Line = "Line";
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
set_Value ("M_Product_ID", Integer.valueOf(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Product_ID */
public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";
/** Set Maximum Length.
@param MaxLength Maximum Length of Data */
public void setMaxLength (BigDecimal MaxLength)
{
set_Value ("MaxLength", MaxLength);
}
/** Get Maximum Length.
@return Maximum Length of Data */
public BigDecimal getMaxLength() 
{
BigDecimal bd = (BigDecimal)get_Value("MaxLength");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name MaxLength */
public static final String COLUMNNAME_MaxLength = "MaxLength";
/** Set Minimum Quantity.
@param MinQty Minimum quantity for the business partner */
public void setMinQty (BigDecimal MinQty)
{
set_Value ("MinQty", MinQty);
}
/** Get Minimum Quantity.
@return Minimum quantity for the business partner */
public BigDecimal getMinQty() 
{
BigDecimal bd = (BigDecimal)get_Value("MinQty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name MinQty */
public static final String COLUMNNAME_MinQty = "MinQty";
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
/** Set Profile BOM Product.
@param PP_ProfileBOM_Product_ID Profile BOM Product */
public void setPP_ProfileBOM_Product_ID (int PP_ProfileBOM_Product_ID)
{
if (PP_ProfileBOM_Product_ID < 1) throw new IllegalArgumentException ("PP_ProfileBOM_Product_ID is mandatory.");
set_ValueNoCheck ("PP_ProfileBOM_Product_ID", Integer.valueOf(PP_ProfileBOM_Product_ID));
}
/** Get Profile BOM Product.
@return Profile BOM Product */
public int getPP_ProfileBOM_Product_ID() 
{
Integer ii = (Integer)get_Value("PP_ProfileBOM_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PP_ProfileBOM_Product_ID */
public static final String COLUMNNAME_PP_ProfileBOM_Product_ID = "PP_ProfileBOM_Product_ID";
/** Set List Price.
@param PriceList List Price */
public void setPriceList (BigDecimal PriceList)
{
set_Value ("PriceList", PriceList);
}
/** Get List Price.
@return List Price */
public BigDecimal getPriceList() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceList");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PriceList */
public static final String COLUMNNAME_PriceList = "PriceList";
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", Boolean.valueOf(Processing));
}
/** Get Process Now.
@return Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Processing */
public static final String COLUMNNAME_Processing = "Processing";
/** Set Quantity.
@param Qty Quantity */
public void setQty (BigDecimal Qty)
{
set_Value ("Qty", Qty);
}
/** Get Quantity.
@return Quantity */
public BigDecimal getQty() 
{
BigDecimal bd = (BigDecimal)get_Value("Qty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Qty */
public static final String COLUMNNAME_Qty = "Qty";
/** Set Search Key.
@param Value Search key for the record in the format required - must be unique */
public void setValue (String Value)
{
if (Value != null && Value.length() > 80)
{
log.warning("Length > 80 - truncated");
Value = Value.substring(0,79);
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
}
