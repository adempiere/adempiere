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
/** Generated Model for M_ForecastLine
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_M_ForecastLine extends PO
{
/** Standard Constructor
@param ctx context
@param M_ForecastLine_ID id
@param trxName transaction
*/
public X_M_ForecastLine (Properties ctx, int M_ForecastLine_ID, String trxName)
{
super (ctx, M_ForecastLine_ID, trxName);
/** if (M_ForecastLine_ID == 0)
{
setC_Period_ID (0);
setM_ForecastLine_ID (0);
setM_Forecast_ID (0);
setM_Product_ID (0);
setQty (Env.ZERO);
setQtyCalculated (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_ForecastLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=M_ForecastLine */
public static final String Table_Name="M_ForecastLine";

/** AD_Table_ID=722 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

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
StringBuffer sb = new StringBuffer ("X_M_ForecastLine[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Period.
@param C_Period_ID Period of the Calendar */
public void setC_Period_ID (int C_Period_ID)
{
if (C_Period_ID < 1) throw new IllegalArgumentException ("C_Period_ID is mandatory.");
set_ValueNoCheck ("C_Period_ID", Integer.valueOf(C_Period_ID));
}
/** Get Period.
@return Period of the Calendar */
public int getC_Period_ID() 
{
Integer ii = (Integer)get_Value("C_Period_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_Period_ID()));
}
/** Column name C_Period_ID */
public static final String COLUMNNAME_C_Period_ID = "C_Period_ID";
/** Set Forecast Line.
@param M_ForecastLine_ID Forecast Line */
public void setM_ForecastLine_ID (int M_ForecastLine_ID)
{
if (M_ForecastLine_ID < 1) throw new IllegalArgumentException ("M_ForecastLine_ID is mandatory.");
set_ValueNoCheck ("M_ForecastLine_ID", Integer.valueOf(M_ForecastLine_ID));
}
/** Get Forecast Line.
@return Forecast Line */
public int getM_ForecastLine_ID() 
{
Integer ii = (Integer)get_Value("M_ForecastLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_ForecastLine_ID */
public static final String COLUMNNAME_M_ForecastLine_ID = "M_ForecastLine_ID";
/** Set Forecast.
@param M_Forecast_ID Material Forecast */
public void setM_Forecast_ID (int M_Forecast_ID)
{
if (M_Forecast_ID < 1) throw new IllegalArgumentException ("M_Forecast_ID is mandatory.");
set_ValueNoCheck ("M_Forecast_ID", Integer.valueOf(M_Forecast_ID));
}
/** Get Forecast.
@return Material Forecast */
public int getM_Forecast_ID() 
{
Integer ii = (Integer)get_Value("M_Forecast_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Forecast_ID */
public static final String COLUMNNAME_M_Forecast_ID = "M_Forecast_ID";
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
/** Column name M_Product_ID */
public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";
/** Set Quantity.
@param Qty Quantity */
public void setQty (BigDecimal Qty)
{
if (Qty == null) throw new IllegalArgumentException ("Qty is mandatory.");
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
/** Set Calculated Quantity.
@param QtyCalculated Calculated Quantity */
public void setQtyCalculated (BigDecimal QtyCalculated)
{
if (QtyCalculated == null) throw new IllegalArgumentException ("QtyCalculated is mandatory.");
set_Value ("QtyCalculated", QtyCalculated);
}
/** Get Calculated Quantity.
@return Calculated Quantity */
public BigDecimal getQtyCalculated() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyCalculated");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtyCalculated */
public static final String COLUMNNAME_QtyCalculated = "QtyCalculated";
}
