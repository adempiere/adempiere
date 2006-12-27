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
/** Generated Model for M_BOMProduct
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_M_BOMProduct extends PO
{
/** Standard Constructor
@param ctx context
@param M_BOMProduct_ID id
@param trxName transaction
*/
public X_M_BOMProduct (Properties ctx, int M_BOMProduct_ID, String trxName)
{
super (ctx, M_BOMProduct_ID, trxName);
/** if (M_BOMProduct_ID == 0)
{
setBOMProductType (null);	// S
setBOMQty (Env.ZERO);	// 1
setIsPhantom (false);
setLeadTimeOffset (0);
setLine (0);	// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM M_BOMProduct WHERE M_BOM_ID=@M_BOM_ID@
setM_BOMProduct_ID (0);
setM_BOM_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_BOMProduct (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=801 */
public static final int Table_ID=MTable.getTable_ID("M_BOMProduct");

/** TableName=M_BOMProduct */
public static final String Table_Name="M_BOMProduct";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_BOMProduct");

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
StringBuffer sb = new StringBuffer ("X_M_BOMProduct[").append(get_ID()).append("]");
return sb.toString();
}

/** BOMProductType AD_Reference_ID=349 */
public static final int BOMPRODUCTTYPE_AD_Reference_ID=349;
/** Alternative = A */
public static final String BOMPRODUCTTYPE_Alternative = "A";
/** Alternative (Default) = D */
public static final String BOMPRODUCTTYPE_AlternativeDefault = "D";
/** Optional Product = O */
public static final String BOMPRODUCTTYPE_OptionalProduct = "O";
/** Standard Product = S */
public static final String BOMPRODUCTTYPE_StandardProduct = "S";
/** Outside Processing = X */
public static final String BOMPRODUCTTYPE_OutsideProcessing = "X";
/** Set Component Type.
@param BOMProductType BOM Product Type */
public void setBOMProductType (String BOMProductType)
{
if (BOMProductType == null) throw new IllegalArgumentException ("BOMProductType is mandatory");
if (BOMProductType.equals("A") || BOMProductType.equals("D") || BOMProductType.equals("O") || BOMProductType.equals("S") || BOMProductType.equals("X"));
 else throw new IllegalArgumentException ("BOMProductType Invalid value - " + BOMProductType + " - Reference_ID=349 - A - D - O - S - X");
if (BOMProductType.length() > 1)
{
log.warning("Length > 1 - truncated");
BOMProductType = BOMProductType.substring(0,0);
}
set_Value ("BOMProductType", BOMProductType);
}
/** Get Component Type.
@return BOM Product Type */
public String getBOMProductType() 
{
return (String)get_Value("BOMProductType");
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
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Set Phantom.
@param IsPhantom Phantom Component */
public void setIsPhantom (boolean IsPhantom)
{
set_Value ("IsPhantom", Boolean.valueOf(IsPhantom));
}
/** Get Phantom.
@return Phantom Component */
public boolean isPhantom() 
{
Object oo = get_Value("IsPhantom");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Lead Time Offset.
@param LeadTimeOffset Optional Lead Time offest before starting production */
public void setLeadTimeOffset (int LeadTimeOffset)
{
set_Value ("LeadTimeOffset", Integer.valueOf(LeadTimeOffset));
}
/** Get Lead Time Offset.
@return Optional Lead Time offest before starting production */
public int getLeadTimeOffset() 
{
Integer ii = (Integer)get_Value("LeadTimeOffset");
if (ii == null) return 0;
return ii.intValue();
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getLine()));
}
/** Set Attribute Set Instance.
@param M_AttributeSetInstance_ID Product Attribute Set Instance */
public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
{
if (M_AttributeSetInstance_ID <= 0) set_Value ("M_AttributeSetInstance_ID", null);
 else 
set_Value ("M_AttributeSetInstance_ID", Integer.valueOf(M_AttributeSetInstance_ID));
}
/** Get Attribute Set Instance.
@return Product Attribute Set Instance */
public int getM_AttributeSetInstance_ID() 
{
Integer ii = (Integer)get_Value("M_AttributeSetInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Alternative Group.
@param M_BOMAlternative_ID Product BOM Alternative Group */
public void setM_BOMAlternative_ID (int M_BOMAlternative_ID)
{
if (M_BOMAlternative_ID <= 0) set_Value ("M_BOMAlternative_ID", null);
 else 
set_Value ("M_BOMAlternative_ID", Integer.valueOf(M_BOMAlternative_ID));
}
/** Get Alternative Group.
@return Product BOM Alternative Group */
public int getM_BOMAlternative_ID() 
{
Integer ii = (Integer)get_Value("M_BOMAlternative_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set BOM Component.
@param M_BOMProduct_ID Bill of Material Component (Product) */
public void setM_BOMProduct_ID (int M_BOMProduct_ID)
{
if (M_BOMProduct_ID < 1) throw new IllegalArgumentException ("M_BOMProduct_ID is mandatory.");
set_ValueNoCheck ("M_BOMProduct_ID", Integer.valueOf(M_BOMProduct_ID));
}
/** Get BOM Component.
@return Bill of Material Component (Product) */
public int getM_BOMProduct_ID() 
{
Integer ii = (Integer)get_Value("M_BOMProduct_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set BOM.
@param M_BOM_ID Bill of Material */
public void setM_BOM_ID (int M_BOM_ID)
{
if (M_BOM_ID < 1) throw new IllegalArgumentException ("M_BOM_ID is mandatory.");
set_ValueNoCheck ("M_BOM_ID", Integer.valueOf(M_BOM_ID));
}
/** Get BOM.
@return Bill of Material */
public int getM_BOM_ID() 
{
Integer ii = (Integer)get_Value("M_BOM_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Change Notice.
@param M_ChangeNotice_ID Bill of Materials (Engineering) Change Notice (Version) */
public void setM_ChangeNotice_ID (int M_ChangeNotice_ID)
{
if (M_ChangeNotice_ID <= 0) set_Value ("M_ChangeNotice_ID", null);
 else 
set_Value ("M_ChangeNotice_ID", Integer.valueOf(M_ChangeNotice_ID));
}
/** Get Change Notice.
@return Bill of Materials (Engineering) Change Notice (Version) */
public int getM_ChangeNotice_ID() 
{
Integer ii = (Integer)get_Value("M_ChangeNotice_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** M_ProductBOM_ID AD_Reference_ID=162 */
public static final int M_PRODUCTBOM_ID_AD_Reference_ID=162;
/** Set BOM Product.
@param M_ProductBOM_ID Bill of Material Component Product */
public void setM_ProductBOM_ID (int M_ProductBOM_ID)
{
if (M_ProductBOM_ID <= 0) set_Value ("M_ProductBOM_ID", null);
 else 
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
/** Set Product Operation.
@param M_ProductOperation_ID Product Manufacturing Operation */
public void setM_ProductOperation_ID (int M_ProductOperation_ID)
{
if (M_ProductOperation_ID <= 0) set_Value ("M_ProductOperation_ID", null);
 else 
set_Value ("M_ProductOperation_ID", Integer.valueOf(M_ProductOperation_ID));
}
/** Get Product Operation.
@return Product Manufacturing Operation */
public int getM_ProductOperation_ID() 
{
Integer ii = (Integer)get_Value("M_ProductOperation_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Sequence.
@param SeqNo Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_Value ("SeqNo", Integer.valueOf(SeqNo));
}
/** Get Sequence.
@return Method of ordering records;
 lowest number comes first */
public int getSeqNo() 
{
Integer ii = (Integer)get_Value("SeqNo");
if (ii == null) return 0;
return ii.intValue();
}
}
