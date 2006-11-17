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
/** Generated Model for C_Conversion_Rate
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:57.078 */
public class X_C_Conversion_Rate extends PO
{
/** Standard Constructor
@param ctx context
@param C_Conversion_Rate_ID id
@param trxName transaction
*/
public X_C_Conversion_Rate (Properties ctx, int C_Conversion_Rate_ID, String trxName)
{
super (ctx, C_Conversion_Rate_ID, trxName);
/** if (C_Conversion_Rate_ID == 0)
{
setC_ConversionType_ID (0);
setC_Conversion_Rate_ID (0);
setC_Currency_ID (0);
setC_Currency_ID_To (0);
setDivideRate (Env.ZERO);
setMultiplyRate (Env.ZERO);
setValidFrom (new Timestamp(System.currentTimeMillis()));
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Conversion_Rate (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=140 */
public static final int Table_ID=140;

/** TableName=C_Conversion_Rate */
public static final String Table_Name="C_Conversion_Rate";

protected static KeyNamePair Model = new KeyNamePair(140,"C_Conversion_Rate");

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
StringBuffer sb = new StringBuffer ("X_C_Conversion_Rate[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Currency Type.
@param C_ConversionType_ID Currency Conversion Rate Type */
public void setC_ConversionType_ID (int C_ConversionType_ID)
{
if (C_ConversionType_ID < 1) throw new IllegalArgumentException ("C_ConversionType_ID is mandatory.");
set_Value ("C_ConversionType_ID", new Integer(C_ConversionType_ID));
}
/** Get Currency Type.
@return Currency Conversion Rate Type */
public int getC_ConversionType_ID() 
{
Integer ii = (Integer)get_Value("C_ConversionType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Conversion Rate.
@param C_Conversion_Rate_ID Rate used for converting currencies */
public void setC_Conversion_Rate_ID (int C_Conversion_Rate_ID)
{
if (C_Conversion_Rate_ID < 1) throw new IllegalArgumentException ("C_Conversion_Rate_ID is mandatory.");
set_ValueNoCheck ("C_Conversion_Rate_ID", new Integer(C_Conversion_Rate_ID));
}
/** Get Conversion Rate.
@return Rate used for converting currencies */
public int getC_Conversion_Rate_ID() 
{
Integer ii = (Integer)get_Value("C_Conversion_Rate_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_Conversion_Rate_ID()));
}

/** C_Currency_ID AD_Reference_ID=112 */
public static final int C_CURRENCY_ID_AD_Reference_ID=112;
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID < 1) throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
set_Value ("C_Currency_ID", new Integer(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** C_Currency_ID_To AD_Reference_ID=112 */
public static final int C_CURRENCY_ID_TO_AD_Reference_ID=112;
/** Set Currency To.
@param C_Currency_ID_To Target currency */
public void setC_Currency_ID_To (int C_Currency_ID_To)
{
set_Value ("C_Currency_ID_To", new Integer(C_Currency_ID_To));
}
/** Get Currency To.
@return Target currency */
public int getC_Currency_ID_To() 
{
Integer ii = (Integer)get_Value("C_Currency_ID_To");
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
/** Set Valid from.
@param ValidFrom Valid from including this date (first day) */
public void setValidFrom (Timestamp ValidFrom)
{
if (ValidFrom == null) throw new IllegalArgumentException ("ValidFrom is mandatory.");
set_Value ("ValidFrom", ValidFrom);
}
/** Get Valid from.
@return Valid from including this date (first day) */
public Timestamp getValidFrom() 
{
return (Timestamp)get_Value("ValidFrom");
}
/** Set Valid to.
@param ValidTo Valid to including this date (last day) */
public void setValidTo (Timestamp ValidTo)
{
set_Value ("ValidTo", ValidTo);
}
/** Get Valid to.
@return Valid to including this date (last day) */
public Timestamp getValidTo() 
{
return (Timestamp)get_Value("ValidTo");
}
}
