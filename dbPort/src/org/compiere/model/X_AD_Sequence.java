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
/** Generated Model for AD_Sequence
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_AD_Sequence extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Sequence_ID id
@param trxName transaction
*/
public X_AD_Sequence (Properties ctx, int AD_Sequence_ID, String trxName)
{
super (ctx, AD_Sequence_ID, trxName);
/** if (AD_Sequence_ID == 0)
{
setAD_Sequence_ID (0);
setCurrentNext (0);	// 1000000
setCurrentNextSys (0);	// 100
setIncrementNo (0);	// 1
setIsAutoSequence (false);
setName (null);
setStartNo (0);	// 1000000
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Sequence (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=115 */
public static final int Table_ID=MTable.getTable_ID("AD_Sequence");

/** TableName=AD_Sequence */
public static final String Table_Name="AD_Sequence";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Sequence");

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
StringBuffer sb = new StringBuffer ("X_AD_Sequence[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Sequence.
@param AD_Sequence_ID Document Sequence */
public void setAD_Sequence_ID (int AD_Sequence_ID)
{
if (AD_Sequence_ID < 1) throw new IllegalArgumentException ("AD_Sequence_ID is mandatory.");
set_ValueNoCheck ("AD_Sequence_ID", Integer.valueOf(AD_Sequence_ID));
}
/** Get Sequence.
@return Document Sequence */
public int getAD_Sequence_ID() 
{
Integer ii = (Integer)get_Value("AD_Sequence_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Sequence_ID */
public static final String COLUMNNAME_AD_Sequence_ID = "AD_Sequence_ID";
/** Set Current Next.
@param CurrentNext The next number to be used */
public void setCurrentNext (int CurrentNext)
{
set_Value ("CurrentNext", Integer.valueOf(CurrentNext));
}
/** Get Current Next.
@return The next number to be used */
public int getCurrentNext() 
{
Integer ii = (Integer)get_Value("CurrentNext");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CurrentNext */
public static final String COLUMNNAME_CurrentNext = "CurrentNext";
/** Set Current Next (System).
@param CurrentNextSys Next sequence for system use */
public void setCurrentNextSys (int CurrentNextSys)
{
set_Value ("CurrentNextSys", Integer.valueOf(CurrentNextSys));
}
/** Get Current Next (System).
@return Next sequence for system use */
public int getCurrentNextSys() 
{
Integer ii = (Integer)get_Value("CurrentNextSys");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CurrentNextSys */
public static final String COLUMNNAME_CurrentNextSys = "CurrentNextSys";
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
/** Set Increment.
@param IncrementNo The number to increment the last document number by */
public void setIncrementNo (int IncrementNo)
{
set_Value ("IncrementNo", Integer.valueOf(IncrementNo));
}
/** Get Increment.
@return The number to increment the last document number by */
public int getIncrementNo() 
{
Integer ii = (Integer)get_Value("IncrementNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name IncrementNo */
public static final String COLUMNNAME_IncrementNo = "IncrementNo";
/** Set Activate Audit.
@param IsAudited Activate Audit Trail of what numbers are generated */
public void setIsAudited (boolean IsAudited)
{
set_Value ("IsAudited", Boolean.valueOf(IsAudited));
}
/** Get Activate Audit.
@return Activate Audit Trail of what numbers are generated */
public boolean isAudited() 
{
Object oo = get_Value("IsAudited");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsAudited */
public static final String COLUMNNAME_IsAudited = "IsAudited";
/** Set Auto numbering.
@param IsAutoSequence Automatically assign the next number */
public void setIsAutoSequence (boolean IsAutoSequence)
{
set_Value ("IsAutoSequence", Boolean.valueOf(IsAutoSequence));
}
/** Get Auto numbering.
@return Automatically assign the next number */
public boolean isAutoSequence() 
{
Object oo = get_Value("IsAutoSequence");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsAutoSequence */
public static final String COLUMNNAME_IsAutoSequence = "IsAutoSequence";
/** Set Used for Record ID.
@param IsTableID The document number  will be used as the record key */
public void setIsTableID (boolean IsTableID)
{
set_Value ("IsTableID", Boolean.valueOf(IsTableID));
}
/** Get Used for Record ID.
@return The document number  will be used as the record key */
public boolean isTableID() 
{
Object oo = get_Value("IsTableID");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsTableID */
public static final String COLUMNNAME_IsTableID = "IsTableID";
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
/** Set Prefix.
@param Prefix Prefix before the sequence number */
public void setPrefix (String Prefix)
{
if (Prefix != null && Prefix.length() > 10)
{
log.warning("Length > 10 - truncated");
Prefix = Prefix.substring(0,9);
}
set_Value ("Prefix", Prefix);
}
/** Get Prefix.
@return Prefix before the sequence number */
public String getPrefix() 
{
return (String)get_Value("Prefix");
}
/** Column name Prefix */
public static final String COLUMNNAME_Prefix = "Prefix";
/** Set Restart sequence every Year.
@param StartNewYear Restart the sequence with Start on every 1/1 */
public void setStartNewYear (boolean StartNewYear)
{
set_Value ("StartNewYear", Boolean.valueOf(StartNewYear));
}
/** Get Restart sequence every Year.
@return Restart the sequence with Start on every 1/1 */
public boolean isStartNewYear() 
{
Object oo = get_Value("StartNewYear");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name StartNewYear */
public static final String COLUMNNAME_StartNewYear = "StartNewYear";
/** Set Start No.
@param StartNo Starting number/position */
public void setStartNo (int StartNo)
{
set_Value ("StartNo", Integer.valueOf(StartNo));
}
/** Get Start No.
@return Starting number/position */
public int getStartNo() 
{
Integer ii = (Integer)get_Value("StartNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name StartNo */
public static final String COLUMNNAME_StartNo = "StartNo";
/** Set Suffix.
@param Suffix Suffix after the number */
public void setSuffix (String Suffix)
{
if (Suffix != null && Suffix.length() > 10)
{
log.warning("Length > 10 - truncated");
Suffix = Suffix.substring(0,9);
}
set_Value ("Suffix", Suffix);
}
/** Get Suffix.
@return Suffix after the number */
public String getSuffix() 
{
return (String)get_Value("Suffix");
}
/** Column name Suffix */
public static final String COLUMNNAME_Suffix = "Suffix";
/** Set Value Format.
@param VFormat Format of the value;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09" */
public void setVFormat (String VFormat)
{
if (VFormat != null && VFormat.length() > 40)
{
log.warning("Length > 40 - truncated");
VFormat = VFormat.substring(0,39);
}
set_Value ("VFormat", VFormat);
}
/** Get Value Format.
@return Format of the value;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09" */
public String getVFormat() 
{
return (String)get_Value("VFormat");
}
/** Column name VFormat */
public static final String COLUMNNAME_VFormat = "VFormat";
}
