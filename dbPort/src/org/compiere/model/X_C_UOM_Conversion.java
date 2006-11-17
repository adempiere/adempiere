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
/** Generated Model for C_UOM_Conversion
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:58.89 */
public class X_C_UOM_Conversion extends PO
{
/** Standard Constructor
@param ctx context
@param C_UOM_Conversion_ID id
@param trxName transaction
*/
public X_C_UOM_Conversion (Properties ctx, int C_UOM_Conversion_ID, String trxName)
{
super (ctx, C_UOM_Conversion_ID, trxName);
/** if (C_UOM_Conversion_ID == 0)
{
setC_UOM_Conversion_ID (0);
setC_UOM_ID (0);
setC_UOM_To_ID (0);
setDivideRate (Env.ZERO);
setMultiplyRate (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_UOM_Conversion (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=175 */
public static final int Table_ID=175;

/** TableName=C_UOM_Conversion */
public static final String Table_Name="C_UOM_Conversion";

protected static KeyNamePair Model = new KeyNamePair(175,"C_UOM_Conversion");

protected BigDecimal accessLevel = new BigDecimal(6);
/** AccessLevel
@return 6 - System - Client 
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
StringBuffer sb = new StringBuffer ("X_C_UOM_Conversion[").append(get_ID()).append("]");
return sb.toString();
}
/** Set UOM Conversion.
@param C_UOM_Conversion_ID Unit of Measure Conversion */
public void setC_UOM_Conversion_ID (int C_UOM_Conversion_ID)
{
if (C_UOM_Conversion_ID < 1) throw new IllegalArgumentException ("C_UOM_Conversion_ID is mandatory.");
set_ValueNoCheck ("C_UOM_Conversion_ID", new Integer(C_UOM_Conversion_ID));
}
/** Get UOM Conversion.
@return Unit of Measure Conversion */
public int getC_UOM_Conversion_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_Conversion_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_UOM_Conversion_ID()));
}

/** C_UOM_ID AD_Reference_ID=114 */
public static final int C_UOM_ID_AD_Reference_ID=114;
/** Set UOM.
@param C_UOM_ID Unit of Measure */
public void setC_UOM_ID (int C_UOM_ID)
{
if (C_UOM_ID < 1) throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
set_Value ("C_UOM_ID", new Integer(C_UOM_ID));
}
/** Get UOM.
@return Unit of Measure */
public int getC_UOM_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** C_UOM_To_ID AD_Reference_ID=114 */
public static final int C_UOM_TO_ID_AD_Reference_ID=114;
/** Set UoM To.
@param C_UOM_To_ID Target or destination Unit of Measure */
public void setC_UOM_To_ID (int C_UOM_To_ID)
{
if (C_UOM_To_ID < 1) throw new IllegalArgumentException ("C_UOM_To_ID is mandatory.");
set_Value ("C_UOM_To_ID", new Integer(C_UOM_To_ID));
}
/** Get UoM To.
@return Target or destination Unit of Measure */
public int getC_UOM_To_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_To_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Divide Rate.
@param DivideRate To convert Source number to Target number, the Source is divided */
public void setDivideRate (BigDecimal DivideRate)
{
if (DivideRate == null) throw new IllegalArgumentException ("DivideRate is mandatory.");
set_Value ("DivideRate", DivideRate);
}
/** Get Divide Rate.
@return To convert Source number to Target number, the Source is divided */
public BigDecimal getDivideRate() 
{
BigDecimal bd = (BigDecimal)get_Value("DivideRate");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_Value ("M_Product_ID", null);
 else 
set_Value ("M_Product_ID", new Integer(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Multiply Rate.
@param MultiplyRate Rate to multiple the source by to calculate the target. */
public void setMultiplyRate (BigDecimal MultiplyRate)
{
if (MultiplyRate == null) throw new IllegalArgumentException ("MultiplyRate is mandatory.");
set_Value ("MultiplyRate", MultiplyRate);
}
/** Get Multiply Rate.
@return Rate to multiple the source by to calculate the target. */
public BigDecimal getMultiplyRate() 
{
BigDecimal bd = (BigDecimal)get_Value("MultiplyRate");
if (bd == null) return Env.ZERO;
return bd;
}
}
