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
/** Generated Model for C_InvoiceSchedule
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_C_InvoiceSchedule extends PO
{
/** Standard Constructor
@param ctx context
@param C_InvoiceSchedule_ID id
@param trxName transaction
*/
public X_C_InvoiceSchedule (Properties ctx, int C_InvoiceSchedule_ID, String trxName)
{
super (ctx, C_InvoiceSchedule_ID, trxName);
/** if (C_InvoiceSchedule_ID == 0)
{
setAmt (Env.ZERO);
setC_InvoiceSchedule_ID (0);
setInvoiceDay (0);	// 1
setInvoiceFrequency (null);
setInvoiceWeekDay (null);
setIsAmount (false);
setIsDefault (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_InvoiceSchedule (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=257 */
public static final int Table_ID=MTable.getTable_ID("C_InvoiceSchedule");

/** TableName=C_InvoiceSchedule */
public static final String Table_Name="C_InvoiceSchedule";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_InvoiceSchedule");

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
StringBuffer sb = new StringBuffer ("X_C_InvoiceSchedule[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Amount.
@param Amt Amount */
public void setAmt (BigDecimal Amt)
{
if (Amt == null) throw new IllegalArgumentException ("Amt is mandatory.");
set_Value ("Amt", Amt);
}
/** Get Amount.
@return Amount */
public BigDecimal getAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("Amt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Invoice Schedule.
@param C_InvoiceSchedule_ID Schedule for generating Invoices */
public void setC_InvoiceSchedule_ID (int C_InvoiceSchedule_ID)
{
if (C_InvoiceSchedule_ID < 1) throw new IllegalArgumentException ("C_InvoiceSchedule_ID is mandatory.");
set_ValueNoCheck ("C_InvoiceSchedule_ID", Integer.valueOf(C_InvoiceSchedule_ID));
}
/** Get Invoice Schedule.
@return Schedule for generating Invoices */
public int getC_InvoiceSchedule_ID() 
{
Integer ii = (Integer)get_Value("C_InvoiceSchedule_ID");
if (ii == null) return 0;
return ii.intValue();
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
/** Set Invoice on even weeks.
@param EvenInvoiceWeek Send invoices on even weeks */
public void setEvenInvoiceWeek (boolean EvenInvoiceWeek)
{
set_Value ("EvenInvoiceWeek", Boolean.valueOf(EvenInvoiceWeek));
}
/** Get Invoice on even weeks.
@return Send invoices on even weeks */
public boolean isEvenInvoiceWeek() 
{
Object oo = get_Value("EvenInvoiceWeek");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Invoice Day.
@param InvoiceDay Day of Invoice Generation */
public void setInvoiceDay (int InvoiceDay)
{
set_Value ("InvoiceDay", Integer.valueOf(InvoiceDay));
}
/** Get Invoice Day.
@return Day of Invoice Generation */
public int getInvoiceDay() 
{
Integer ii = (Integer)get_Value("InvoiceDay");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Invoice day cut-off.
@param InvoiceDayCutoff Last day for including shipments */
public void setInvoiceDayCutoff (int InvoiceDayCutoff)
{
set_Value ("InvoiceDayCutoff", Integer.valueOf(InvoiceDayCutoff));
}
/** Get Invoice day cut-off.
@return Last day for including shipments */
public int getInvoiceDayCutoff() 
{
Integer ii = (Integer)get_Value("InvoiceDayCutoff");
if (ii == null) return 0;
return ii.intValue();
}

/** InvoiceFrequency AD_Reference_ID=168 */
public static final int INVOICEFREQUENCY_AD_Reference_ID=168;
/** Daily = D */
public static final String INVOICEFREQUENCY_Daily = "D";
/** Monthly = M */
public static final String INVOICEFREQUENCY_Monthly = "M";
/** Twice Monthly = T */
public static final String INVOICEFREQUENCY_TwiceMonthly = "T";
/** Weekly = W */
public static final String INVOICEFREQUENCY_Weekly = "W";
/** Set Invoice Frequency.
@param InvoiceFrequency How often invoices will be generated */
public void setInvoiceFrequency (String InvoiceFrequency)
{
if (InvoiceFrequency == null) throw new IllegalArgumentException ("InvoiceFrequency is mandatory");
if (InvoiceFrequency.equals("D") || InvoiceFrequency.equals("M") || InvoiceFrequency.equals("T") || InvoiceFrequency.equals("W"));
 else throw new IllegalArgumentException ("InvoiceFrequency Invalid value - " + InvoiceFrequency + " - Reference_ID=168 - D - M - T - W");
if (InvoiceFrequency.length() > 1)
{
log.warning("Length > 1 - truncated");
InvoiceFrequency = InvoiceFrequency.substring(0,0);
}
set_Value ("InvoiceFrequency", InvoiceFrequency);
}
/** Get Invoice Frequency.
@return How often invoices will be generated */
public String getInvoiceFrequency() 
{
return (String)get_Value("InvoiceFrequency");
}

/** InvoiceWeekDay AD_Reference_ID=167 */
public static final int INVOICEWEEKDAY_AD_Reference_ID=167;
/** Monday = 1 */
public static final String INVOICEWEEKDAY_Monday = "1";
/** Tuesday = 2 */
public static final String INVOICEWEEKDAY_Tuesday = "2";
/** Wednesday = 3 */
public static final String INVOICEWEEKDAY_Wednesday = "3";
/** Thursday = 4 */
public static final String INVOICEWEEKDAY_Thursday = "4";
/** Friday = 5 */
public static final String INVOICEWEEKDAY_Friday = "5";
/** Saturday = 6 */
public static final String INVOICEWEEKDAY_Saturday = "6";
/** Sunday = 7 */
public static final String INVOICEWEEKDAY_Sunday = "7";
/** Set Invoice Week Day.
@param InvoiceWeekDay Day to generate invoices */
public void setInvoiceWeekDay (String InvoiceWeekDay)
{
if (InvoiceWeekDay == null) throw new IllegalArgumentException ("InvoiceWeekDay is mandatory");
if (InvoiceWeekDay.equals("1") || InvoiceWeekDay.equals("2") || InvoiceWeekDay.equals("3") || InvoiceWeekDay.equals("4") || InvoiceWeekDay.equals("5") || InvoiceWeekDay.equals("6") || InvoiceWeekDay.equals("7"));
 else throw new IllegalArgumentException ("InvoiceWeekDay Invalid value - " + InvoiceWeekDay + " - Reference_ID=167 - 1 - 2 - 3 - 4 - 5 - 6 - 7");
if (InvoiceWeekDay.length() > 1)
{
log.warning("Length > 1 - truncated");
InvoiceWeekDay = InvoiceWeekDay.substring(0,0);
}
set_Value ("InvoiceWeekDay", InvoiceWeekDay);
}
/** Get Invoice Week Day.
@return Day to generate invoices */
public String getInvoiceWeekDay() 
{
return (String)get_Value("InvoiceWeekDay");
}

/** InvoiceWeekDayCutoff AD_Reference_ID=167 */
public static final int INVOICEWEEKDAYCUTOFF_AD_Reference_ID=167;
/** Monday = 1 */
public static final String INVOICEWEEKDAYCUTOFF_Monday = "1";
/** Tuesday = 2 */
public static final String INVOICEWEEKDAYCUTOFF_Tuesday = "2";
/** Wednesday = 3 */
public static final String INVOICEWEEKDAYCUTOFF_Wednesday = "3";
/** Thursday = 4 */
public static final String INVOICEWEEKDAYCUTOFF_Thursday = "4";
/** Friday = 5 */
public static final String INVOICEWEEKDAYCUTOFF_Friday = "5";
/** Saturday = 6 */
public static final String INVOICEWEEKDAYCUTOFF_Saturday = "6";
/** Sunday = 7 */
public static final String INVOICEWEEKDAYCUTOFF_Sunday = "7";
/** Set Invoice weekday cutoff.
@param InvoiceWeekDayCutoff Last day in the week for shipments to be included */
public void setInvoiceWeekDayCutoff (String InvoiceWeekDayCutoff)
{
if (InvoiceWeekDayCutoff == null || InvoiceWeekDayCutoff.equals("1") || InvoiceWeekDayCutoff.equals("2") || InvoiceWeekDayCutoff.equals("3") || InvoiceWeekDayCutoff.equals("4") || InvoiceWeekDayCutoff.equals("5") || InvoiceWeekDayCutoff.equals("6") || InvoiceWeekDayCutoff.equals("7"));
 else throw new IllegalArgumentException ("InvoiceWeekDayCutoff Invalid value - " + InvoiceWeekDayCutoff + " - Reference_ID=167 - 1 - 2 - 3 - 4 - 5 - 6 - 7");
if (InvoiceWeekDayCutoff != null && InvoiceWeekDayCutoff.length() > 1)
{
log.warning("Length > 1 - truncated");
InvoiceWeekDayCutoff = InvoiceWeekDayCutoff.substring(0,0);
}
set_Value ("InvoiceWeekDayCutoff", InvoiceWeekDayCutoff);
}
/** Get Invoice weekday cutoff.
@return Last day in the week for shipments to be included */
public String getInvoiceWeekDayCutoff() 
{
return (String)get_Value("InvoiceWeekDayCutoff");
}
/** Set Amount Limit.
@param IsAmount Send invoices only if the amount exceeds the limit */
public void setIsAmount (boolean IsAmount)
{
set_Value ("IsAmount", Boolean.valueOf(IsAmount));
}
/** Get Amount Limit.
@return Send invoices only if the amount exceeds the limit */
public boolean isAmount() 
{
Object oo = get_Value("IsAmount");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Default.
@param IsDefault Default value */
public void setIsDefault (boolean IsDefault)
{
set_Value ("IsDefault", Boolean.valueOf(IsDefault));
}
/** Get Default.
@return Default value */
public boolean isDefault() 
{
Object oo = get_Value("IsDefault");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
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
}
