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
/** Generated Model for C_Location
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_C_Location extends PO
{
/** Standard Constructor
@param ctx context
@param C_Location_ID id
@param trxName transaction
*/
public X_C_Location (Properties ctx, int C_Location_ID, String trxName)
{
super (ctx, C_Location_ID, trxName);
/** if (C_Location_ID == 0)
{
setC_Country_ID (0);
setC_Location_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Location (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=162 */
public static final int Table_ID=MTable.getTable_ID("C_Location");

/** TableName=C_Location */
public static final String Table_Name="C_Location";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_Location");

protected BigDecimal accessLevel = BigDecimal.valueOf(7);
/** AccessLevel
@return 7 - System - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_C_Location[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Address 1.
@param Address1 Address line 1 for this location */
public void setAddress1 (String Address1)
{
if (Address1 != null && Address1.length() > 60)
{
log.warning("Length > 60 - truncated");
Address1 = Address1.substring(0,59);
}
set_Value ("Address1", Address1);
}
/** Get Address 1.
@return Address line 1 for this location */
public String getAddress1() 
{
return (String)get_Value("Address1");
}
/** Set Address 2.
@param Address2 Address line 2 for this location */
public void setAddress2 (String Address2)
{
if (Address2 != null && Address2.length() > 60)
{
log.warning("Length > 60 - truncated");
Address2 = Address2.substring(0,59);
}
set_Value ("Address2", Address2);
}
/** Get Address 2.
@return Address line 2 for this location */
public String getAddress2() 
{
return (String)get_Value("Address2");
}
/** Set Address 3.
@param Address3 Address Line 3 for the location */
public void setAddress3 (String Address3)
{
if (Address3 != null && Address3.length() > 60)
{
log.warning("Length > 60 - truncated");
Address3 = Address3.substring(0,59);
}
set_Value ("Address3", Address3);
}
/** Get Address 3.
@return Address Line 3 for the location */
public String getAddress3() 
{
return (String)get_Value("Address3");
}
/** Set Address 4.
@param Address4 Address Line 4 for the location */
public void setAddress4 (String Address4)
{
if (Address4 != null && Address4.length() > 60)
{
log.warning("Length > 60 - truncated");
Address4 = Address4.substring(0,59);
}
set_Value ("Address4", Address4);
}
/** Get Address 4.
@return Address Line 4 for the location */
public String getAddress4() 
{
return (String)get_Value("Address4");
}
/** Set City.
@param C_City_ID City */
public void setC_City_ID (int C_City_ID)
{
if (C_City_ID <= 0) set_Value ("C_City_ID", null);
 else 
set_Value ("C_City_ID", Integer.valueOf(C_City_ID));
}
/** Get City.
@return City */
public int getC_City_ID() 
{
Integer ii = (Integer)get_Value("C_City_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Country.
@param C_Country_ID Country  */
public void setC_Country_ID (int C_Country_ID)
{
if (C_Country_ID < 1) throw new IllegalArgumentException ("C_Country_ID is mandatory.");
set_Value ("C_Country_ID", Integer.valueOf(C_Country_ID));
}
/** Get Country.
@return Country  */
public int getC_Country_ID() 
{
Integer ii = (Integer)get_Value("C_Country_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Address.
@param C_Location_ID Location or Address */
public void setC_Location_ID (int C_Location_ID)
{
if (C_Location_ID < 1) throw new IllegalArgumentException ("C_Location_ID is mandatory.");
set_ValueNoCheck ("C_Location_ID", Integer.valueOf(C_Location_ID));
}
/** Get Address.
@return Location or Address */
public int getC_Location_ID() 
{
Integer ii = (Integer)get_Value("C_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Region.
@param C_Region_ID Identifies a geographical Region */
public void setC_Region_ID (int C_Region_ID)
{
if (C_Region_ID <= 0) set_Value ("C_Region_ID", null);
 else 
set_Value ("C_Region_ID", Integer.valueOf(C_Region_ID));
}
/** Get Region.
@return Identifies a geographical Region */
public int getC_Region_ID() 
{
Integer ii = (Integer)get_Value("C_Region_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set City.
@param City Identifies a City */
public void setCity (String City)
{
if (City != null && City.length() > 60)
{
log.warning("Length > 60 - truncated");
City = City.substring(0,59);
}
set_Value ("City", City);
}
/** Get City.
@return Identifies a City */
public String getCity() 
{
return (String)get_Value("City");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getCity());
}
/** Set ZIP.
@param Postal Postal code */
public void setPostal (String Postal)
{
if (Postal != null && Postal.length() > 10)
{
log.warning("Length > 10 - truncated");
Postal = Postal.substring(0,9);
}
set_Value ("Postal", Postal);
}
/** Get ZIP.
@return Postal code */
public String getPostal() 
{
return (String)get_Value("Postal");
}
/** Set -.
@param Postal_Add Additional ZIP or Postal code */
public void setPostal_Add (String Postal_Add)
{
if (Postal_Add != null && Postal_Add.length() > 10)
{
log.warning("Length > 10 - truncated");
Postal_Add = Postal_Add.substring(0,9);
}
set_Value ("Postal_Add", Postal_Add);
}
/** Get -.
@return Additional ZIP or Postal code */
public String getPostal_Add() 
{
return (String)get_Value("Postal_Add");
}
/** Set Region.
@param RegionName Name of the Region */
public void setRegionName (String RegionName)
{
if (RegionName != null && RegionName.length() > 40)
{
log.warning("Length > 40 - truncated");
RegionName = RegionName.substring(0,39);
}
set_Value ("RegionName", RegionName);
}
/** Get Region.
@return Name of the Region */
public String getRegionName() 
{
return (String)get_Value("RegionName");
}
}
