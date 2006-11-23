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
/** Generated Model for S_ResourceType
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_S_ResourceType extends PO
{
/** Standard Constructor
@param ctx context
@param S_ResourceType_ID id
@param trxName transaction
*/
public X_S_ResourceType (Properties ctx, int S_ResourceType_ID, String trxName)
{
super (ctx, S_ResourceType_ID, trxName);
/** if (S_ResourceType_ID == 0)
{
setAllowUoMFractions (false);	// N
setC_TaxCategory_ID (0);
setC_UOM_ID (0);
setIsDateSlot (false);
setIsSingleAssignment (false);
setIsTimeSlot (false);
setM_Product_Category_ID (0);
setName (null);
setOnFriday (true);	// Y
setOnMonday (true);	// Y
setOnSaturday (false);
setOnSunday (false);
setOnThursday (true);	// Y
setOnTuesday (true);	// Y
setOnWednesday (true);	// Y
setS_ResourceType_ID (0);
setValue (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_S_ResourceType (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=480 */
public static final int Table_ID=480;

/** TableName=S_ResourceType */
public static final String Table_Name="S_ResourceType";

protected static KeyNamePair Model = new KeyNamePair(480,"S_ResourceType");

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
StringBuffer sb = new StringBuffer ("X_S_ResourceType[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Allow UoM Fractions.
@param AllowUoMFractions Allow Unit of Measure Fractions */
public void setAllowUoMFractions (boolean AllowUoMFractions)
{
set_Value ("AllowUoMFractions", new Boolean(AllowUoMFractions));
}
/** Get Allow UoM Fractions.
@return Allow Unit of Measure Fractions */
public boolean isAllowUoMFractions() 
{
Object oo = get_Value("AllowUoMFractions");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Tax Category.
@param C_TaxCategory_ID Tax Category */
public void setC_TaxCategory_ID (int C_TaxCategory_ID)
{
if (C_TaxCategory_ID < 1) throw new IllegalArgumentException ("C_TaxCategory_ID is mandatory.");
set_Value ("C_TaxCategory_ID", new Integer(C_TaxCategory_ID));
}
/** Get Tax Category.
@return Tax Category */
public int getC_TaxCategory_ID() 
{
Integer ii = (Integer)get_Value("C_TaxCategory_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Chargeable Quantity.
@param ChargeableQty Chargeable Quantity */
public void setChargeableQty (int ChargeableQty)
{
set_Value ("ChargeableQty", new Integer(ChargeableQty));
}
/** Get Chargeable Quantity.
@return Chargeable Quantity */
public int getChargeableQty() 
{
Integer ii = (Integer)get_Value("ChargeableQty");
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
/** Set Day Slot.
@param IsDateSlot Resource has day slot availability */
public void setIsDateSlot (boolean IsDateSlot)
{
set_Value ("IsDateSlot", new Boolean(IsDateSlot));
}
/** Get Day Slot.
@return Resource has day slot availability */
public boolean isDateSlot() 
{
Object oo = get_Value("IsDateSlot");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Single Assignment only.
@param IsSingleAssignment Only one assignment at a time (no double-booking or overlapping) */
public void setIsSingleAssignment (boolean IsSingleAssignment)
{
set_Value ("IsSingleAssignment", new Boolean(IsSingleAssignment));
}
/** Get Single Assignment only.
@return Only one assignment at a time (no double-booking or overlapping) */
public boolean isSingleAssignment() 
{
Object oo = get_Value("IsSingleAssignment");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Time Slot.
@param IsTimeSlot Resource has time slot availability */
public void setIsTimeSlot (boolean IsTimeSlot)
{
set_Value ("IsTimeSlot", new Boolean(IsTimeSlot));
}
/** Get Time Slot.
@return Resource has time slot availability */
public boolean isTimeSlot() 
{
Object oo = get_Value("IsTimeSlot");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Product Category.
@param M_Product_Category_ID Category of a Product */
public void setM_Product_Category_ID (int M_Product_Category_ID)
{
if (M_Product_Category_ID < 1) throw new IllegalArgumentException ("M_Product_Category_ID is mandatory.");
set_Value ("M_Product_Category_ID", new Integer(M_Product_Category_ID));
}
/** Get Product Category.
@return Category of a Product */
public int getM_Product_Category_ID() 
{
Integer ii = (Integer)get_Value("M_Product_Category_ID");
if (ii == null) return 0;
return ii.intValue();
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
/** Set Friday.
@param OnFriday Available on Fridays */
public void setOnFriday (boolean OnFriday)
{
set_Value ("OnFriday", new Boolean(OnFriday));
}
/** Get Friday.
@return Available on Fridays */
public boolean isOnFriday() 
{
Object oo = get_Value("OnFriday");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Monday.
@param OnMonday Available on Mondays */
public void setOnMonday (boolean OnMonday)
{
set_Value ("OnMonday", new Boolean(OnMonday));
}
/** Get Monday.
@return Available on Mondays */
public boolean isOnMonday() 
{
Object oo = get_Value("OnMonday");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Saturday.
@param OnSaturday Available on Saturday */
public void setOnSaturday (boolean OnSaturday)
{
set_Value ("OnSaturday", new Boolean(OnSaturday));
}
/** Get Saturday.
@return Available on Saturday */
public boolean isOnSaturday() 
{
Object oo = get_Value("OnSaturday");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Sunday.
@param OnSunday Available on Sundays */
public void setOnSunday (boolean OnSunday)
{
set_Value ("OnSunday", new Boolean(OnSunday));
}
/** Get Sunday.
@return Available on Sundays */
public boolean isOnSunday() 
{
Object oo = get_Value("OnSunday");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Thursday.
@param OnThursday Available on Thursdays */
public void setOnThursday (boolean OnThursday)
{
set_Value ("OnThursday", new Boolean(OnThursday));
}
/** Get Thursday.
@return Available on Thursdays */
public boolean isOnThursday() 
{
Object oo = get_Value("OnThursday");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Tuesday.
@param OnTuesday Available on Tuesdays */
public void setOnTuesday (boolean OnTuesday)
{
set_Value ("OnTuesday", new Boolean(OnTuesday));
}
/** Get Tuesday.
@return Available on Tuesdays */
public boolean isOnTuesday() 
{
Object oo = get_Value("OnTuesday");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Wednesday.
@param OnWednesday Available on Wednesdays */
public void setOnWednesday (boolean OnWednesday)
{
set_Value ("OnWednesday", new Boolean(OnWednesday));
}
/** Get Wednesday.
@return Available on Wednesdays */
public boolean isOnWednesday() 
{
Object oo = get_Value("OnWednesday");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Resource Type.
@param S_ResourceType_ID Resource Type */
public void setS_ResourceType_ID (int S_ResourceType_ID)
{
if (S_ResourceType_ID < 1) throw new IllegalArgumentException ("S_ResourceType_ID is mandatory.");
set_ValueNoCheck ("S_ResourceType_ID", new Integer(S_ResourceType_ID));
}
/** Get Resource Type.
@return Resource Type */
public int getS_ResourceType_ID() 
{
Integer ii = (Integer)get_Value("S_ResourceType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Slot End.
@param TimeSlotEnd Time when timeslot ends */
public void setTimeSlotEnd (Timestamp TimeSlotEnd)
{
set_Value ("TimeSlotEnd", TimeSlotEnd);
}
/** Get Slot End.
@return Time when timeslot ends */
public Timestamp getTimeSlotEnd() 
{
return (Timestamp)get_Value("TimeSlotEnd");
}
/** Set Slot Start.
@param TimeSlotStart Time when timeslot starts */
public void setTimeSlotStart (Timestamp TimeSlotStart)
{
set_Value ("TimeSlotStart", TimeSlotStart);
}
/** Get Slot Start.
@return Time when timeslot starts */
public Timestamp getTimeSlotStart() 
{
return (Timestamp)get_Value("TimeSlotStart");
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
