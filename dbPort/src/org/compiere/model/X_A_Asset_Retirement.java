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
/** Generated Model for A_Asset_Retirement
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_A_Asset_Retirement extends PO
{
/** Standard Constructor
@param ctx context
@param A_Asset_Retirement_ID id
@param trxName transaction
*/
public X_A_Asset_Retirement (Properties ctx, int A_Asset_Retirement_ID, String trxName)
{
super (ctx, A_Asset_Retirement_ID, trxName);
/** if (A_Asset_Retirement_ID == 0)
{
setA_Asset_ID (0);
setA_Asset_Retirement_ID (0);
setAssetMarketValueAmt (Env.ZERO);
setAssetValueAmt (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_A_Asset_Retirement (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=540 */
public static final int Table_ID=540;

/** TableName=A_Asset_Retirement */
public static final String Table_Name="A_Asset_Retirement";

protected static KeyNamePair Model = new KeyNamePair(540,"A_Asset_Retirement");

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
StringBuffer sb = new StringBuffer ("X_A_Asset_Retirement[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Asset.
@param A_Asset_ID Asset used internally or by customers */
public void setA_Asset_ID (int A_Asset_ID)
{
if (A_Asset_ID < 1) throw new IllegalArgumentException ("A_Asset_ID is mandatory.");
set_ValueNoCheck ("A_Asset_ID", new Integer(A_Asset_ID));
}
/** Get Asset.
@return Asset used internally or by customers */
public int getA_Asset_ID() 
{
Integer ii = (Integer)get_Value("A_Asset_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Asset Retirement.
@param A_Asset_Retirement_ID Internally used asset is not longer used. */
public void setA_Asset_Retirement_ID (int A_Asset_Retirement_ID)
{
if (A_Asset_Retirement_ID < 1) throw new IllegalArgumentException ("A_Asset_Retirement_ID is mandatory.");
set_ValueNoCheck ("A_Asset_Retirement_ID", new Integer(A_Asset_Retirement_ID));
}
/** Get Asset Retirement.
@return Internally used asset is not longer used. */
public int getA_Asset_Retirement_ID() 
{
Integer ii = (Integer)get_Value("A_Asset_Retirement_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getA_Asset_Retirement_ID()));
}
/** Set Market value Amount.
@param AssetMarketValueAmt Market value of the asset */
public void setAssetMarketValueAmt (BigDecimal AssetMarketValueAmt)
{
if (AssetMarketValueAmt == null) throw new IllegalArgumentException ("AssetMarketValueAmt is mandatory.");
set_Value ("AssetMarketValueAmt", AssetMarketValueAmt);
}
/** Get Market value Amount.
@return Market value of the asset */
public BigDecimal getAssetMarketValueAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("AssetMarketValueAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Asset value.
@param AssetValueAmt Book Value of the asset */
public void setAssetValueAmt (BigDecimal AssetValueAmt)
{
if (AssetValueAmt == null) throw new IllegalArgumentException ("AssetValueAmt is mandatory.");
set_Value ("AssetValueAmt", AssetValueAmt);
}
/** Get Asset value.
@return Book Value of the asset */
public BigDecimal getAssetValueAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("AssetValueAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Invoice Line.
@param C_InvoiceLine_ID Invoice Detail Line */
public void setC_InvoiceLine_ID (int C_InvoiceLine_ID)
{
if (C_InvoiceLine_ID <= 0) set_Value ("C_InvoiceLine_ID", null);
 else 
set_Value ("C_InvoiceLine_ID", new Integer(C_InvoiceLine_ID));
}
/** Get Invoice Line.
@return Invoice Detail Line */
public int getC_InvoiceLine_ID() 
{
Integer ii = (Integer)get_Value("C_InvoiceLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
