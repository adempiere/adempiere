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
/** Generated Model for C_Country
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_C_Country extends PO
{
/** Standard Constructor
@param ctx context
@param C_Country_ID id
@param trxName transaction
*/
public X_C_Country (Properties ctx, int C_Country_ID, String trxName)
{
super (ctx, C_Country_ID, trxName);
/** if (C_Country_ID == 0)
{
setC_Country_ID (0);
setCountryCode (null);
setDisplaySequence (null);	// @C@, @R@ @P@
setHasPostal_Add (false);
setHasRegion (false);
setIsAddressLinesLocalReverse (false);
setIsAddressLinesReverse (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Country (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=170 */
public static final int Table_ID=MTable.getTable_ID("C_Country");

/** TableName=C_Country */
public static final String Table_Name="C_Country";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_Country");

protected BigDecimal accessLevel = BigDecimal.valueOf(6);
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
StringBuffer sb = new StringBuffer ("X_C_Country[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_Language AD_Reference_ID=106 */
public static final int AD_LANGUAGE_AD_Reference_ID=106;
/** Set Language.
@param AD_Language Language for this entity */
public void setAD_Language (String AD_Language)
{
if (AD_Language != null && AD_Language.length() > 6)
{
log.warning("Length > 6 - truncated");
AD_Language = AD_Language.substring(0,5);
}
set_Value ("AD_Language", AD_Language);
}
/** Get Language.
@return Language for this entity */
public String getAD_Language() 
{
return (String)get_Value("AD_Language");
}
/** Column name AD_Language */
public static final String COLUMNNAME_AD_Language = "AD_Language";
/** Set Country.
@param C_Country_ID Country  */
public void setC_Country_ID (int C_Country_ID)
{
if (C_Country_ID < 1) throw new IllegalArgumentException ("C_Country_ID is mandatory.");
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
/** Column name C_Country_ID */
public static final String COLUMNNAME_C_Country_ID = "C_Country_ID";
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID <= 0) set_Value ("C_Currency_ID", null);
 else 
set_Value ("C_Currency_ID", Integer.valueOf(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Currency_ID */
public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";
/** Set ISO Country Code.
@param CountryCode Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html */
public void setCountryCode (String CountryCode)
{
if (CountryCode == null) throw new IllegalArgumentException ("CountryCode is mandatory.");
if (CountryCode.length() > 2)
{
log.warning("Length > 2 - truncated");
CountryCode = CountryCode.substring(0,1);
}
set_Value ("CountryCode", CountryCode);
}
/** Get ISO Country Code.
@return Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html */
public String getCountryCode() 
{
return (String)get_Value("CountryCode");
}
/** Column name CountryCode */
public static final String COLUMNNAME_CountryCode = "CountryCode";
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
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set Address Print Format.
@param DisplaySequence Format for printing this Address */
public void setDisplaySequence (String DisplaySequence)
{
if (DisplaySequence == null) throw new IllegalArgumentException ("DisplaySequence is mandatory.");
if (DisplaySequence.length() > 20)
{
log.warning("Length > 20 - truncated");
DisplaySequence = DisplaySequence.substring(0,19);
}
set_Value ("DisplaySequence", DisplaySequence);
}
/** Get Address Print Format.
@return Format for printing this Address */
public String getDisplaySequence() 
{
return (String)get_Value("DisplaySequence");
}
/** Column name DisplaySequence */
public static final String COLUMNNAME_DisplaySequence = "DisplaySequence";
/** Set Local Address Format.
@param DisplaySequenceLocal Format for printing this Address locally */
public void setDisplaySequenceLocal (String DisplaySequenceLocal)
{
if (DisplaySequenceLocal != null && DisplaySequenceLocal.length() > 20)
{
log.warning("Length > 20 - truncated");
DisplaySequenceLocal = DisplaySequenceLocal.substring(0,19);
}
set_Value ("DisplaySequenceLocal", DisplaySequenceLocal);
}
/** Get Local Address Format.
@return Format for printing this Address locally */
public String getDisplaySequenceLocal() 
{
return (String)get_Value("DisplaySequenceLocal");
}
/** Column name DisplaySequenceLocal */
public static final String COLUMNNAME_DisplaySequenceLocal = "DisplaySequenceLocal";
/** Set Bank Account No Format.
@param ExpressionBankAccountNo Format of the Bank Account */
public void setExpressionBankAccountNo (String ExpressionBankAccountNo)
{
if (ExpressionBankAccountNo != null && ExpressionBankAccountNo.length() > 20)
{
log.warning("Length > 20 - truncated");
ExpressionBankAccountNo = ExpressionBankAccountNo.substring(0,19);
}
set_Value ("ExpressionBankAccountNo", ExpressionBankAccountNo);
}
/** Get Bank Account No Format.
@return Format of the Bank Account */
public String getExpressionBankAccountNo() 
{
return (String)get_Value("ExpressionBankAccountNo");
}
/** Column name ExpressionBankAccountNo */
public static final String COLUMNNAME_ExpressionBankAccountNo = "ExpressionBankAccountNo";
/** Set Bank Routing No Format.
@param ExpressionBankRoutingNo Format of the Bank Routing Number */
public void setExpressionBankRoutingNo (String ExpressionBankRoutingNo)
{
if (ExpressionBankRoutingNo != null && ExpressionBankRoutingNo.length() > 20)
{
log.warning("Length > 20 - truncated");
ExpressionBankRoutingNo = ExpressionBankRoutingNo.substring(0,19);
}
set_Value ("ExpressionBankRoutingNo", ExpressionBankRoutingNo);
}
/** Get Bank Routing No Format.
@return Format of the Bank Routing Number */
public String getExpressionBankRoutingNo() 
{
return (String)get_Value("ExpressionBankRoutingNo");
}
/** Column name ExpressionBankRoutingNo */
public static final String COLUMNNAME_ExpressionBankRoutingNo = "ExpressionBankRoutingNo";
/** Set Phone Format.
@param ExpressionPhone Format of the phone;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09" */
public void setExpressionPhone (String ExpressionPhone)
{
if (ExpressionPhone != null && ExpressionPhone.length() > 20)
{
log.warning("Length > 20 - truncated");
ExpressionPhone = ExpressionPhone.substring(0,19);
}
set_Value ("ExpressionPhone", ExpressionPhone);
}
/** Get Phone Format.
@return Format of the phone;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09" */
public String getExpressionPhone() 
{
return (String)get_Value("ExpressionPhone");
}
/** Column name ExpressionPhone */
public static final String COLUMNNAME_ExpressionPhone = "ExpressionPhone";
/** Set Postal Code Format.
@param ExpressionPostal Format of the postal code;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09" */
public void setExpressionPostal (String ExpressionPostal)
{
if (ExpressionPostal != null && ExpressionPostal.length() > 20)
{
log.warning("Length > 20 - truncated");
ExpressionPostal = ExpressionPostal.substring(0,19);
}
set_Value ("ExpressionPostal", ExpressionPostal);
}
/** Get Postal Code Format.
@return Format of the postal code;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09" */
public String getExpressionPostal() 
{
return (String)get_Value("ExpressionPostal");
}
/** Column name ExpressionPostal */
public static final String COLUMNNAME_ExpressionPostal = "ExpressionPostal";
/** Set Additional Postal Format.
@param ExpressionPostal_Add Format of the value;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09" */
public void setExpressionPostal_Add (String ExpressionPostal_Add)
{
if (ExpressionPostal_Add != null && ExpressionPostal_Add.length() > 20)
{
log.warning("Length > 20 - truncated");
ExpressionPostal_Add = ExpressionPostal_Add.substring(0,19);
}
set_Value ("ExpressionPostal_Add", ExpressionPostal_Add);
}
/** Get Additional Postal Format.
@return Format of the value;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09" */
public String getExpressionPostal_Add() 
{
return (String)get_Value("ExpressionPostal_Add");
}
/** Column name ExpressionPostal_Add */
public static final String COLUMNNAME_ExpressionPostal_Add = "ExpressionPostal_Add";
/** Set Additional Postal code.
@param HasPostal_Add Has Additional Postal Code */
public void setHasPostal_Add (boolean HasPostal_Add)
{
set_Value ("HasPostal_Add", Boolean.valueOf(HasPostal_Add));
}
/** Get Additional Postal code.
@return Has Additional Postal Code */
public boolean isHasPostal_Add() 
{
Object oo = get_Value("HasPostal_Add");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name HasPostal_Add */
public static final String COLUMNNAME_HasPostal_Add = "HasPostal_Add";
/** Set Country has Region.
@param HasRegion Country contains Regions */
public void setHasRegion (boolean HasRegion)
{
set_Value ("HasRegion", Boolean.valueOf(HasRegion));
}
/** Get Country has Region.
@return Country contains Regions */
public boolean isHasRegion() 
{
Object oo = get_Value("HasRegion");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name HasRegion */
public static final String COLUMNNAME_HasRegion = "HasRegion";
/** Set Reverse Local Address Lines.
@param IsAddressLinesLocalReverse Print Local Address in reverse Order */
public void setIsAddressLinesLocalReverse (boolean IsAddressLinesLocalReverse)
{
set_Value ("IsAddressLinesLocalReverse", Boolean.valueOf(IsAddressLinesLocalReverse));
}
/** Get Reverse Local Address Lines.
@return Print Local Address in reverse Order */
public boolean isAddressLinesLocalReverse() 
{
Object oo = get_Value("IsAddressLinesLocalReverse");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsAddressLinesLocalReverse */
public static final String COLUMNNAME_IsAddressLinesLocalReverse = "IsAddressLinesLocalReverse";
/** Set Reverse Address Lines.
@param IsAddressLinesReverse Print Address in reverse Order */
public void setIsAddressLinesReverse (boolean IsAddressLinesReverse)
{
set_Value ("IsAddressLinesReverse", Boolean.valueOf(IsAddressLinesReverse));
}
/** Get Reverse Address Lines.
@return Print Address in reverse Order */
public boolean isAddressLinesReverse() 
{
Object oo = get_Value("IsAddressLinesReverse");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsAddressLinesReverse */
public static final String COLUMNNAME_IsAddressLinesReverse = "IsAddressLinesReverse";
/** Set Media Size.
@param MediaSize Java Media Size */
public void setMediaSize (String MediaSize)
{
if (MediaSize != null && MediaSize.length() > 40)
{
log.warning("Length > 40 - truncated");
MediaSize = MediaSize.substring(0,39);
}
set_Value ("MediaSize", MediaSize);
}
/** Get Media Size.
@return Java Media Size */
public String getMediaSize() 
{
return (String)get_Value("MediaSize");
}
/** Column name MediaSize */
public static final String COLUMNNAME_MediaSize = "MediaSize";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Region.
@param RegionName Name of the Region */
public void setRegionName (String RegionName)
{
if (RegionName != null && RegionName.length() > 60)
{
log.warning("Length > 60 - truncated");
RegionName = RegionName.substring(0,59);
}
set_Value ("RegionName", RegionName);
}
/** Get Region.
@return Name of the Region */
public String getRegionName() 
{
return (String)get_Value("RegionName");
}
/** Column name RegionName */
public static final String COLUMNNAME_RegionName = "RegionName";
}
