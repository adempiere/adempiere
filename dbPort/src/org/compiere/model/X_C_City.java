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
/** Generated Model for C_City
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_C_City extends PO
{
/** Standard Constructor
@param ctx context
@param C_City_ID id
@param trxName transaction
*/
public X_C_City (Properties ctx, int C_City_ID, String trxName)
{
super (ctx, C_City_ID, trxName);
/** if (C_City_ID == 0)
{
setC_City_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_City (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=186 */
public static final int Table_ID=MTable.getTable_ID("C_City");

/** TableName=C_City */
public static final String Table_Name="C_City";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_City");

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
StringBuffer sb = new StringBuffer ("X_C_City[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Area Code.
@param AreaCode Phone Area Code */
public void setAreaCode (String AreaCode)
{
if (AreaCode != null && AreaCode.length() > 10)
{
log.warning("Length > 10 - truncated");
AreaCode = AreaCode.substring(0,9);
}
set_Value ("AreaCode", AreaCode);
}
/** Get Area Code.
@return Phone Area Code */
public String getAreaCode() 
{
return (String)get_Value("AreaCode");
}
/** Set City.
@param C_City_ID City */
public void setC_City_ID (int C_City_ID)
{
if (C_City_ID < 1) throw new IllegalArgumentException ("C_City_ID is mandatory.");
set_ValueNoCheck ("C_City_ID", Integer.valueOf(C_City_ID));
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
if (C_Country_ID <= 0) set_ValueNoCheck ("C_Country_ID", null);
 else 
set_ValueNoCheck ("C_Country_ID", Integer.valueOf(C_Country_ID));
}
/** Get Country.
@return Country  */
public int getC_Country_ID() 
{
Integer ii = (Integer)get_Value("C_Country_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** C_Region_ID AD_Reference_ID=157 */
public static final int C_REGION_ID_AD_Reference_ID=157;
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
/** Set Coordinates.
@param Coordinates Location coordinate */
public void setCoordinates (String Coordinates)
{
if (Coordinates != null && Coordinates.length() > 15)
{
log.warning("Length > 15 - truncated");
Coordinates = Coordinates.substring(0,14);
}
set_Value ("Coordinates", Coordinates);
}
/** Get Coordinates.
@return Location coordinate */
public String getCoordinates() 
{
return (String)get_Value("Coordinates");
}
/** Set Locode.
@param Locode Location code - UN/LOCODE  */
public void setLocode (String Locode)
{
if (Locode != null && Locode.length() > 10)
{
log.warning("Length > 10 - truncated");
Locode = Locode.substring(0,9);
}
set_Value ("Locode", Locode);
}
/** Get Locode.
@return Location code - UN/LOCODE  */
public String getLocode() 
{
return (String)get_Value("Locode");
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
}
