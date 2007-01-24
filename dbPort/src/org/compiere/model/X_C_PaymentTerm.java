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
/** Generated Model for C_PaymentTerm
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_C_PaymentTerm extends PO
{
/** Standard Constructor
@param ctx context
@param C_PaymentTerm_ID id
@param trxName transaction
*/
public X_C_PaymentTerm (Properties ctx, int C_PaymentTerm_ID, String trxName)
{
super (ctx, C_PaymentTerm_ID, trxName);
/** if (C_PaymentTerm_ID == 0)
{
setAfterDelivery (false);
setC_PaymentTerm_ID (0);
setDiscount (Env.ZERO);
setDiscount2 (Env.ZERO);
setDiscountDays (0);
setDiscountDays2 (0);
setGraceDays (0);
setIsDueFixed (false);
setIsValid (false);
setName (null);
setNetDays (0);
setValue (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_PaymentTerm (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=113 */
public static final int Table_ID=MTable.getTable_ID("C_PaymentTerm");

/** TableName=C_PaymentTerm */
public static final String Table_Name="C_PaymentTerm";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_PaymentTerm");

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
StringBuffer sb = new StringBuffer ("X_C_PaymentTerm[").append(get_ID()).append("]");
return sb.toString();
}
/** Set After Delivery.
@param AfterDelivery Due after delivery rather than after invoicing */
public void setAfterDelivery (boolean AfterDelivery)
{
set_Value ("AfterDelivery", Boolean.valueOf(AfterDelivery));
}
/** Get After Delivery.
@return Due after delivery rather than after invoicing */
public boolean isAfterDelivery() 
{
Object oo = get_Value("AfterDelivery");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Payment Term.
@param C_PaymentTerm_ID The terms of Payment (timing, discount) */
public void setC_PaymentTerm_ID (int C_PaymentTerm_ID)
{
if (C_PaymentTerm_ID < 1) throw new IllegalArgumentException ("C_PaymentTerm_ID is mandatory.");
set_ValueNoCheck ("C_PaymentTerm_ID", Integer.valueOf(C_PaymentTerm_ID));
}
/** Get Payment Term.
@return The terms of Payment (timing, discount) */
public int getC_PaymentTerm_ID() 
{
Integer ii = (Integer)get_Value("C_PaymentTerm_ID");
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
/** Set Discount 2 %.
@param Discount2 Discount in percent */
public void setDiscount2 (BigDecimal Discount2)
{
if (Discount2 == null) throw new IllegalArgumentException ("Discount2 is mandatory.");
set_Value ("Discount2", Discount2);
}
/** Get Discount 2 %.
@return Discount in percent */
public BigDecimal getDiscount2() 
{
BigDecimal bd = (BigDecimal)get_Value("Discount2");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Discount Days.
@param DiscountDays Number of days from invoice date to be eligible for discount */
public void setDiscountDays (int DiscountDays)
{
set_Value ("DiscountDays", Integer.valueOf(DiscountDays));
}
/** Get Discount Days.
@return Number of days from invoice date to be eligible for discount */
public int getDiscountDays() 
{
Integer ii = (Integer)get_Value("DiscountDays");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Discount Days 2.
@param DiscountDays2 Number of days from invoice date to be eligible for discount */
public void setDiscountDays2 (int DiscountDays2)
{
set_Value ("DiscountDays2", Integer.valueOf(DiscountDays2));
}
/** Get Discount Days 2.
@return Number of days from invoice date to be eligible for discount */
public int getDiscountDays2() 
{
Integer ii = (Integer)get_Value("DiscountDays2");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Document Note.
@param DocumentNote Additional information for a Document */
public void setDocumentNote (String DocumentNote)
{
if (DocumentNote != null && DocumentNote.length() > 2000)
{
log.warning("Length > 2000 - truncated");
DocumentNote = DocumentNote.substring(0,1999);
}
set_Value ("DocumentNote", DocumentNote);
}
/** Get Document Note.
@return Additional information for a Document */
public String getDocumentNote() 
{
return (String)get_Value("DocumentNote");
}
/** Set Fix month cutoff.
@param FixMonthCutoff Last day to include for next due date */
public void setFixMonthCutoff (int FixMonthCutoff)
{
set_Value ("FixMonthCutoff", Integer.valueOf(FixMonthCutoff));
}
/** Get Fix month cutoff.
@return Last day to include for next due date */
public int getFixMonthCutoff() 
{
Integer ii = (Integer)get_Value("FixMonthCutoff");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Fix month day.
@param FixMonthDay Day of the month of the due date */
public void setFixMonthDay (int FixMonthDay)
{
set_Value ("FixMonthDay", Integer.valueOf(FixMonthDay));
}
/** Get Fix month day.
@return Day of the month of the due date */
public int getFixMonthDay() 
{
Integer ii = (Integer)get_Value("FixMonthDay");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Fix month offset.
@param FixMonthOffset Number of months (0=same, 1=following) */
public void setFixMonthOffset (int FixMonthOffset)
{
set_Value ("FixMonthOffset", Integer.valueOf(FixMonthOffset));
}
/** Get Fix month offset.
@return Number of months (0=same, 1=following) */
public int getFixMonthOffset() 
{
Integer ii = (Integer)get_Value("FixMonthOffset");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Grace Days.
@param GraceDays Days after due date to send first dunning letter */
public void setGraceDays (int GraceDays)
{
set_Value ("GraceDays", Integer.valueOf(GraceDays));
}
/** Get Grace Days.
@return Days after due date to send first dunning letter */
public int getGraceDays() 
{
Integer ii = (Integer)get_Value("GraceDays");
if (ii == null) return 0;
return ii.intValue();
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
/** Set Fixed due date.
@param IsDueFixed Payment is due on a fixed date */
public void setIsDueFixed (boolean IsDueFixed)
{
set_Value ("IsDueFixed", Boolean.valueOf(IsDueFixed));
}
/** Get Fixed due date.
@return Payment is due on a fixed date */
public boolean isDueFixed() 
{
Object oo = get_Value("IsDueFixed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Next Business Day.
@param IsNextBusinessDay Payment due on the next business day */
public void setIsNextBusinessDay (boolean IsNextBusinessDay)
{
set_Value ("IsNextBusinessDay", Boolean.valueOf(IsNextBusinessDay));
}
/** Get Next Business Day.
@return Payment due on the next business day */
public boolean isNextBusinessDay() 
{
Object oo = get_Value("IsNextBusinessDay");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Valid.
@param IsValid Element is valid */
public void setIsValid (boolean IsValid)
{
set_Value ("IsValid", Boolean.valueOf(IsValid));
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
set_Value ("NetDays", Integer.valueOf(NetDays));
}
/** Get Net Days.
@return Net Days in which payment is due */
public int getNetDays() 
{
Integer ii = (Integer)get_Value("NetDays");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Search Key.
@param Value Search key for the record in the format required - must be unique */
public void setValue (String Value)
{
if (Value == null) throw new IllegalArgumentException ("Value is mandatory.");
if (Value.length() > 40)
{
log.warning("Length > 40 - truncated");
Value = Value.substring(0,39);
}
set_Value ("Value", Value);
}
/** Get Search Key.
@return Search key for the record in the format required - must be unique */
public String getValue() 
{
return (String)get_Value("Value");
}
}
