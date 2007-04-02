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
/** Generated Model for C_RfQResponseLineQty
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_C_RfQResponseLineQty extends PO
{
/** Standard Constructor
@param ctx context
@param C_RfQResponseLineQty_ID id
@param trxName transaction
*/
public X_C_RfQResponseLineQty (Properties ctx, int C_RfQResponseLineQty_ID, String trxName)
{
super (ctx, C_RfQResponseLineQty_ID, trxName);
/** if (C_RfQResponseLineQty_ID == 0)
{
setC_RfQLineQty_ID (0);
setC_RfQResponseLineQty_ID (0);
setC_RfQResponseLine_ID (0);
setPrice (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_RfQResponseLineQty (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=672 */
public static final int Table_ID=MTable.getTable_ID("C_RfQResponseLineQty");

/** TableName=C_RfQResponseLineQty */
public static final String Table_Name="C_RfQResponseLineQty";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_RfQResponseLineQty");

protected BigDecimal accessLevel = BigDecimal.valueOf(1);
/** AccessLevel
@return 1 - Org 
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
StringBuffer sb = new StringBuffer ("X_C_RfQResponseLineQty[").append(get_ID()).append("]");
return sb.toString();
}
/** Set RfQ Line Quantity.
@param C_RfQLineQty_ID Request for Quotation Line Quantity */
public void setC_RfQLineQty_ID (int C_RfQLineQty_ID)
{
if (C_RfQLineQty_ID < 1) throw new IllegalArgumentException ("C_RfQLineQty_ID is mandatory.");
set_ValueNoCheck ("C_RfQLineQty_ID", Integer.valueOf(C_RfQLineQty_ID));
}
/** Get RfQ Line Quantity.
@return Request for Quotation Line Quantity */
public int getC_RfQLineQty_ID() 
{
Integer ii = (Integer)get_Value("C_RfQLineQty_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_RfQLineQty_ID */
public static final String COLUMNNAME_C_RfQLineQty_ID = "C_RfQLineQty_ID";
/** Set RfQ Response Line Qty.
@param C_RfQResponseLineQty_ID Request for Quotation Response Line Quantity */
public void setC_RfQResponseLineQty_ID (int C_RfQResponseLineQty_ID)
{
if (C_RfQResponseLineQty_ID < 1) throw new IllegalArgumentException ("C_RfQResponseLineQty_ID is mandatory.");
set_ValueNoCheck ("C_RfQResponseLineQty_ID", Integer.valueOf(C_RfQResponseLineQty_ID));
}
/** Get RfQ Response Line Qty.
@return Request for Quotation Response Line Quantity */
public int getC_RfQResponseLineQty_ID() 
{
Integer ii = (Integer)get_Value("C_RfQResponseLineQty_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_RfQResponseLineQty_ID */
public static final String COLUMNNAME_C_RfQResponseLineQty_ID = "C_RfQResponseLineQty_ID";
/** Set RfQ Response Line.
@param C_RfQResponseLine_ID Request for Quotation Response Line */
public void setC_RfQResponseLine_ID (int C_RfQResponseLine_ID)
{
if (C_RfQResponseLine_ID < 1) throw new IllegalArgumentException ("C_RfQResponseLine_ID is mandatory.");
set_ValueNoCheck ("C_RfQResponseLine_ID", Integer.valueOf(C_RfQResponseLine_ID));
}
/** Get RfQ Response Line.
@return Request for Quotation Response Line */
public int getC_RfQResponseLine_ID() 
{
Integer ii = (Integer)get_Value("C_RfQResponseLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_RfQResponseLine_ID()));
}
/** Column name C_RfQResponseLine_ID */
public static final String COLUMNNAME_C_RfQResponseLine_ID = "C_RfQResponseLine_ID";
/** Set Discount %.
@param Discount Discount in percent */
public void setDiscount (BigDecimal Discount)
{
set_Value ("Discount", Discount);
}
/** Get Discount %.
@return Discount in percent */
public BigDecimal getDiscount() 
{
BigDecimal bd = (BigDecimal)get_Value("Discount");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Discount */
public static final String COLUMNNAME_Discount = "Discount";
/** Set Price.
@param Price Price */
public void setPrice (BigDecimal Price)
{
if (Price == null) throw new IllegalArgumentException ("Price is mandatory.");
set_Value ("Price", Price);
}
/** Get Price.
@return Price */
public BigDecimal getPrice() 
{
BigDecimal bd = (BigDecimal)get_Value("Price");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Price */
public static final String COLUMNNAME_Price = "Price";
/** Set Ranking.
@param Ranking Relative Rank Number */
public void setRanking (int Ranking)
{
set_Value ("Ranking", Integer.valueOf(Ranking));
}
/** Get Ranking.
@return Relative Rank Number */
public int getRanking() 
{
Integer ii = (Integer)get_Value("Ranking");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Ranking */
public static final String COLUMNNAME_Ranking = "Ranking";
}
