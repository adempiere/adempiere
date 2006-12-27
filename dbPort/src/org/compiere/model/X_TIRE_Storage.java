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
/** Generated Model for TIRE_Storage
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_TIRE_Storage extends PO
{
/** Standard Constructor
@param ctx context
@param TIRE_Storage_ID id
@param trxName transaction
*/
public X_TIRE_Storage (Properties ctx, int TIRE_Storage_ID, String trxName)
{
super (ctx, TIRE_Storage_ID, trxName);
/** if (TIRE_Storage_ID == 0)
{
setDateReceived (new Timestamp(System.currentTimeMillis()));	// @#Date@
setIsReturned (false);
setIsStored (false);
setName (null);
setTIRE_Storage_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_TIRE_Storage (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=384 */
public static final int Table_ID=MTable.getTable_ID("TIRE_Storage");

/** TableName=TIRE_Storage */
public static final String Table_Name="TIRE_Storage";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"TIRE_Storage");

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
StringBuffer sb = new StringBuffer ("X_TIRE_Storage[").append(get_ID()).append("]");
return sb.toString();
}
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID <= 0) set_Value ("AD_User_ID", null);
 else 
set_Value ("AD_User_ID", Integer.valueOf(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_Value ("C_BPartner_ID", null);
 else 
set_Value ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Date received.
@param DateReceived Date a product was received */
public void setDateReceived (Timestamp DateReceived)
{
if (DateReceived == null) throw new IllegalArgumentException ("DateReceived is mandatory.");
set_Value ("DateReceived", DateReceived);
}
/** Get Date received.
@return Date a product was received */
public Timestamp getDateReceived() 
{
return (Timestamp)get_Value("DateReceived");
}
/** Set Date returned.
@param DateReturned Date a product was returned */
public void setDateReturned (Timestamp DateReturned)
{
set_Value ("DateReturned", DateReturned);
}
/** Get Date returned.
@return Date a product was returned */
public Timestamp getDateReturned() 
{
return (Timestamp)get_Value("DateReturned");
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
/** Set Returned.
@param IsReturned Returned */
public void setIsReturned (boolean IsReturned)
{
set_Value ("IsReturned", Boolean.valueOf(IsReturned));
}
/** Get Returned.
@return Returned */
public boolean isReturned() 
{
Object oo = get_Value("IsReturned");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Moved to storage.
@param IsStored Moved to storage */
public void setIsStored (boolean IsStored)
{
set_Value ("IsStored", Boolean.valueOf(IsStored));
}
/** Get Moved to storage.
@return Moved to storage */
public boolean isStored() 
{
Object oo = get_Value("IsStored");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Locator.
@param M_Locator_ID Warehouse Locator */
public void setM_Locator_ID (int M_Locator_ID)
{
if (M_Locator_ID <= 0) set_Value ("M_Locator_ID", null);
 else 
set_Value ("M_Locator_ID", Integer.valueOf(M_Locator_ID));
}
/** Get Locator.
@return Warehouse Locator */
public int getM_Locator_ID() 
{
Integer ii = (Integer)get_Value("M_Locator_ID");
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
/** Set Registration.
@param Registration Vehicle registration */
public void setRegistration (String Registration)
{
if (Registration != null && Registration.length() > 20)
{
log.warning("Length > 20 - truncated");
Registration = Registration.substring(0,19);
}
set_Value ("Registration", Registration);
}
/** Get Registration.
@return Vehicle registration */
public String getRegistration() 
{
return (String)get_Value("Registration");
}
/** Set Remark.
@param Remark Remark */
public void setRemark (String Remark)
{
if (Remark != null && Remark.length() > 60)
{
log.warning("Length > 60 - truncated");
Remark = Remark.substring(0,59);
}
set_Value ("Remark", Remark);
}
/** Get Remark.
@return Remark */
public String getRemark() 
{
return (String)get_Value("Remark");
}
/** Set Rim.
@param Rim Stored rim */
public void setRim (String Rim)
{
if (Rim != null && Rim.length() > 20)
{
log.warning("Length > 20 - truncated");
Rim = Rim.substring(0,19);
}
set_Value ("Rim", Rim);
}
/** Get Rim.
@return Stored rim */
public String getRim() 
{
return (String)get_Value("Rim");
}
/** Set Rim Back.
@param Rim_B Rim Back */
public void setRim_B (String Rim_B)
{
if (Rim_B != null && Rim_B.length() > 20)
{
log.warning("Length > 20 - truncated");
Rim_B = Rim_B.substring(0,19);
}
set_Value ("Rim_B", Rim_B);
}
/** Get Rim Back.
@return Rim Back */
public String getRim_B() 
{
return (String)get_Value("Rim_B");
}
/** Set Tire Storage.
@param TIRE_Storage_ID Tire Storage */
public void setTIRE_Storage_ID (int TIRE_Storage_ID)
{
if (TIRE_Storage_ID < 1) throw new IllegalArgumentException ("TIRE_Storage_ID is mandatory.");
set_ValueNoCheck ("TIRE_Storage_ID", Integer.valueOf(TIRE_Storage_ID));
}
/** Get Tire Storage.
@return Tire Storage */
public int getTIRE_Storage_ID() 
{
Integer ii = (Integer)get_Value("TIRE_Storage_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Tire Quality.
@param TireQuality Tire Quality */
public void setTireQuality (String TireQuality)
{
if (TireQuality != null && TireQuality.length() > 20)
{
log.warning("Length > 20 - truncated");
TireQuality = TireQuality.substring(0,19);
}
set_Value ("TireQuality", TireQuality);
}
/** Get Tire Quality.
@return Tire Quality */
public String getTireQuality() 
{
return (String)get_Value("TireQuality");
}
/** Set Tire Quality Back.
@param TireQuality_B Tire Quality Back */
public void setTireQuality_B (String TireQuality_B)
{
if (TireQuality_B != null && TireQuality_B.length() > 20)
{
log.warning("Length > 20 - truncated");
TireQuality_B = TireQuality_B.substring(0,19);
}
set_Value ("TireQuality_B", TireQuality_B);
}
/** Get Tire Quality Back.
@return Tire Quality Back */
public String getTireQuality_B() 
{
return (String)get_Value("TireQuality_B");
}
/** Set Tire size (L/R).
@param TireSize Tire size (L/R) */
public void setTireSize (String TireSize)
{
if (TireSize != null && TireSize.length() > 20)
{
log.warning("Length > 20 - truncated");
TireSize = TireSize.substring(0,19);
}
set_Value ("TireSize", TireSize);
}
/** Get Tire size (L/R).
@return Tire size (L/R) */
public String getTireSize() 
{
return (String)get_Value("TireSize");
}
/** Set Tire size Back.
@param TireSize_B Tire size Back */
public void setTireSize_B (String TireSize_B)
{
if (TireSize_B != null && TireSize_B.length() > 20)
{
log.warning("Length > 20 - truncated");
TireSize_B = TireSize_B.substring(0,19);
}
set_Value ("TireSize_B", TireSize_B);
}
/** Get Tire size Back.
@return Tire size Back */
public String getTireSize_B() 
{
return (String)get_Value("TireSize_B");
}
/** Set Tire type.
@param TireType Tire type */
public void setTireType (String TireType)
{
if (TireType != null && TireType.length() > 20)
{
log.warning("Length > 20 - truncated");
TireType = TireType.substring(0,19);
}
set_Value ("TireType", TireType);
}
/** Get Tire type.
@return Tire type */
public String getTireType() 
{
return (String)get_Value("TireType");
}
/** Set Tire type Back.
@param TireType_B Tire type Back */
public void setTireType_B (String TireType_B)
{
if (TireType_B != null && TireType_B.length() > 20)
{
log.warning("Length > 20 - truncated");
TireType_B = TireType_B.substring(0,19);
}
set_Value ("TireType_B", TireType_B);
}
/** Get Tire type Back.
@return Tire type Back */
public String getTireType_B() 
{
return (String)get_Value("TireType_B");
}
/** Set Vehicle.
@param Vehicle Vehicle */
public void setVehicle (String Vehicle)
{
if (Vehicle != null && Vehicle.length() > 20)
{
log.warning("Length > 20 - truncated");
Vehicle = Vehicle.substring(0,19);
}
set_Value ("Vehicle", Vehicle);
}
/** Get Vehicle.
@return Vehicle */
public String getVehicle() 
{
return (String)get_Value("Vehicle");
}
}
