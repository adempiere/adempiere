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
/** Generated Model for C_PaySchedule
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:58.046 */
public class X_C_PaySchedule extends PO
{
/** Standard Constructor
@param ctx context
@param C_PaySchedule_ID id
@param trxName transaction
*/
public X_C_PaySchedule (Properties ctx, int C_PaySchedule_ID, String trxName)
{
super (ctx, C_PaySchedule_ID, trxName);
/** if (C_PaySchedule_ID == 0)
{
setC_PaySchedule_ID (0);
setC_PaymentTerm_ID (0);
setDiscount (Env.ZERO);
setDiscountDays (0);
setGraceDays (0);
setIsValid (false);
setNetDays (0);
setPercentage (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_PaySchedule (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=548 */
public static final int Table_ID=548;

/** TableName=C_PaySchedule */
public static final String Table_Name="C_PaySchedule";

protected static KeyNamePair Model = new KeyNamePair(548,"C_PaySchedule");

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
StringBuffer sb = new StringBuffer ("X_C_PaySchedule[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Payment Schedule.
@param C_PaySchedule_ID Payment Schedule Template */
public void setC_PaySchedule_ID (int C_PaySchedule_ID)
{
if (C_PaySchedule_ID < 1) throw new IllegalArgumentException ("C_PaySchedule_ID is mandatory.");
set_ValueNoCheck ("C_PaySchedule_ID", new Integer(C_PaySchedule_ID));
}
/** Get Payment Schedule.
@return Payment Schedule Template */
public int getC_PaySchedule_ID() 
{
Integer ii = (Integer)get_Value("C_PaySchedule_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Payment Term.
@param C_PaymentTerm_ID The terms of Payment (timing, discount) */
public void setC_PaymentTerm_ID (int C_PaymentTerm_ID)
{
if (C_PaymentTerm_ID < 1) throw new IllegalArgumentException ("C_PaymentTerm_ID is mandatory.");
set_ValueNoCheck ("C_PaymentTerm_ID", new Integer(C_PaymentTerm_ID));
}
/** Get Payment Term.
@return The terms of Payment (timing, discount) */
public int getC_PaymentTerm_ID() 
{
Integer ii = (Integer)get_Value("C_PaymentTerm_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_PaymentTerm_ID()));
}
/** Set Discount %.
@param Discount Discount in percent */
public void setDiscount (BigDecimal Discount)
{
if (Discount == null) throw new IllegalArgumentException ("Discount is mandatory.");
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
/** Set Discount Days.
@param DiscountDays Number of days from invoice date to be eligible for discount */
public void setDiscountDays (int DiscountDays)
{
set_Value ("DiscountDays", new Integer(DiscountDays));
}
/** Get Discount Days.
@return Number of days from invoice date to be eligible for discount */
public int getDiscountDays() 
{
Integer ii = (Integer)get_Value("DiscountDays");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Grace Days.
@param GraceDays Days after due date to send first dunning letter */
public void setGraceDays (int GraceDays)
{
set_Value ("GraceDays", new Integer(GraceDays));
}
/** Get Grace Days.
@return Days after due date to send first dunning letter */
public int getGraceDays() 
{
Integer ii = (Integer)get_Value("GraceDays");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Valid.
@param IsValid Element is valid */
public void setIsValid (boolean IsValid)
{
set_Value ("IsValid", new Boolean(IsValid));
}
/** Get Valid.
@return Element is valid */
public boolean isValid() 
{
Object oo = get_Value("IsValid");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}

/** NetDay AD_Reference_ID=167 */
public static final int NETDAY_AD_Reference_ID=167;
/** Monday = 1 */
public static final String NETDAY_Monday = "1";
/** Tuesday = 2 */
public static final String NETDAY_Tuesday = "2";
/** Wednesday = 3 */
public static final String NETDAY_Wednesday = "3";
/** Thursday = 4 */
public static final String NETDAY_Thursday = "4";
/** Friday = 5 */
public static final String NETDAY_Friday = "5";
/** Saturday = 6 */
public static final String NETDAY_Saturday = "6";
/** Sunday = 7 */
public static final String NETDAY_Sunday = "7";
/** Set Net Day.
@param NetDay Day when payment is due net */
public void setNetDay (String NetDay)
{
if (NetDay == null) throw new IllegalArgumentException ("NetDay is mandatory");
if (NetDay == null || NetDay.equals("1") || NetDay.equals("2") || NetDay.equals("3") || NetDay.equals("4") || NetDay.equals("5") || NetDay.equals("6") || NetDay.equals("7"));
 else throw new IllegalArgumentException ("NetDay Invalid value - " + NetDay + " - Reference_ID=167 - 1 - 2 - 3 - 4 - 5 - 6 - 7");
if (NetDay != null && NetDay.length() > 1)
{
log.warning("Length > 1 - truncated");
NetDay = NetDay.substring(0,0);
}
set_Value ("NetDay", NetDay);
}
/** Get Net Day.
@return Day when payment is due net */
public String getNetDay() 
{
return (String)get_Value("NetDay");
}
/** Set Net Days.
@param NetDays Net Days in which payment is due */
public void setNetDays (int NetDays)
{
set_Value ("NetDays", new Integer(NetDays));
}
/** Get Net Days.
@return Net Days in which payment is due */
public int getNetDays() 
{
Integer ii = (Integer)get_Value("NetDays");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Percentage.
@param Percentage Percent of the entire amount */
public void setPercentage (BigDecimal Percentage)
{
if (Percentage == null) throw new IllegalArgumentException ("Percentage is mandatory.");
set_Value ("Percentage", Percentage);
}
/** Get Percentage.
@return Percent of the entire amount */
public BigDecimal getPercentage() 
{
BigDecimal bd = (BigDecimal)get_Value("Percentage");
if (bd == null) return Env.ZERO;
return bd;
}
}
