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
/** Generated Model for AD_Language
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_AD_Language extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Language_ID id
@param trxName transaction
*/
public X_AD_Language (Properties ctx, int AD_Language_ID, String trxName)
{
super (ctx, AD_Language_ID, trxName);
/** if (AD_Language_ID == 0)
{
setAD_Language (null);
setAD_Language_ID (0);	// @SQL=SELECT NVL(MAX(AD_Language_ID),0)+1 AS DefaultValue FROM AD_Language
setIsBaseLanguage (false);	// N
setIsDecimalPoint (false);
setIsSystemLanguage (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Language (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=111 */
public static final int Table_ID=MTable.getTable_ID("AD_Language");

/** TableName=AD_Language */
public static final String Table_Name="AD_Language";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Language");

protected BigDecimal accessLevel = BigDecimal.valueOf(4);
/** AccessLevel
@return 4 - System 
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
StringBuffer sb = new StringBuffer ("X_AD_Language[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Language.
@param AD_Language Language for this entity */
public void setAD_Language (String AD_Language)
{
if (AD_Language == null) throw new IllegalArgumentException ("AD_Language is mandatory.");
if (AD_Language.length() > 6)
{
log.warning("Length > 6 - truncated");
AD_Language = AD_Language.substring(0,5);
}
set_ValueNoCheck ("AD_Language", AD_Language);
}
/** Get Language.
@return Language for this entity */
public String getAD_Language() 
{
return (String)get_Value("AD_Language");
}
/** Column name AD_Language */
public static final String COLUMNNAME_AD_Language = "AD_Language";
/** Set Language ID.
@param AD_Language_ID Language ID */
public void setAD_Language_ID (int AD_Language_ID)
{
if (AD_Language_ID < 1) throw new IllegalArgumentException ("AD_Language_ID is mandatory.");
set_ValueNoCheck ("AD_Language_ID", Integer.valueOf(AD_Language_ID));
}
/** Get Language ID.
@return Language ID */
public int getAD_Language_ID() 
{
Integer ii = (Integer)get_Value("AD_Language_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Language_ID */
public static final String COLUMNNAME_AD_Language_ID = "AD_Language_ID";
/** Set ISO Country Code.
@param CountryCode Upper-case two-letter alphanumeric ISO Country code according to ISO 3166-1 - http://www.chemie.fu-berlin.de/diverse/doc/ISO_3166.html */
public void setCountryCode (String CountryCode)
{
if (CountryCode != null && CountryCode.length() > 2)
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
/** Set Date Pattern.
@param DatePattern Java Date Pattern */
public void setDatePattern (String DatePattern)
{
if (DatePattern != null && DatePattern.length() > 20)
{
log.warning("Length > 20 - truncated");
DatePattern = DatePattern.substring(0,19);
}
set_Value ("DatePattern", DatePattern);
}
/** Get Date Pattern.
@return Java Date Pattern */
public String getDatePattern() 
{
return (String)get_Value("DatePattern");
}
/** Column name DatePattern */
public static final String COLUMNNAME_DatePattern = "DatePattern";
/** Set Base Language.
@param IsBaseLanguage The system information is maintained in this language */
public void setIsBaseLanguage (boolean IsBaseLanguage)
{
set_ValueNoCheck ("IsBaseLanguage", Boolean.valueOf(IsBaseLanguage));
}
/** Get Base Language.
@return The system information is maintained in this language */
public boolean isBaseLanguage() 
{
Object oo = get_Value("IsBaseLanguage");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsBaseLanguage */
public static final String COLUMNNAME_IsBaseLanguage = "IsBaseLanguage";
/** Set Decimal Point.
@param IsDecimalPoint The number notation has a decimal point (no decimal comma) */
public void setIsDecimalPoint (boolean IsDecimalPoint)
{
set_Value ("IsDecimalPoint", Boolean.valueOf(IsDecimalPoint));
}
/** Get Decimal Point.
@return The number notation has a decimal point (no decimal comma) */
public boolean isDecimalPoint() 
{
Object oo = get_Value("IsDecimalPoint");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsDecimalPoint */
public static final String COLUMNNAME_IsDecimalPoint = "IsDecimalPoint";
/** Set System Language.
@param IsSystemLanguage The screens, etc. are maintained in this Language */
public void setIsSystemLanguage (boolean IsSystemLanguage)
{
set_Value ("IsSystemLanguage", Boolean.valueOf(IsSystemLanguage));
}
/** Get System Language.
@return The screens, etc. are maintained in this Language */
public boolean isSystemLanguage() 
{
Object oo = get_Value("IsSystemLanguage");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSystemLanguage */
public static final String COLUMNNAME_IsSystemLanguage = "IsSystemLanguage";
/** Set ISO Language Code.
@param LanguageISO Lower-case two-letter ISO-3166 code - http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt  */
public void setLanguageISO (String LanguageISO)
{
if (LanguageISO != null && LanguageISO.length() > 2)
{
log.warning("Length > 2 - truncated");
LanguageISO = LanguageISO.substring(0,1);
}
set_Value ("LanguageISO", LanguageISO);
}
/** Get ISO Language Code.
@return Lower-case two-letter ISO-3166 code - http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt  */
public String getLanguageISO() 
{
return (String)get_Value("LanguageISO");
}
/** Column name LanguageISO */
public static final String COLUMNNAME_LanguageISO = "LanguageISO";
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
/** Column name Processing */
public static final String COLUMNNAME_Processing = "Processing";
/** Set Time Pattern.
@param TimePattern Java Time Pattern */
public void setTimePattern (String TimePattern)
{
if (TimePattern != null && TimePattern.length() > 20)
{
log.warning("Length > 20 - truncated");
TimePattern = TimePattern.substring(0,19);
}
set_Value ("TimePattern", TimePattern);
}
/** Get Time Pattern.
@return Java Time Pattern */
public String getTimePattern() 
{
return (String)get_Value("TimePattern");
}
/** Column name TimePattern */
public static final String COLUMNNAME_TimePattern = "TimePattern";
}
