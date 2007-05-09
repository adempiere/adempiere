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
/** Generated Model for AD_Archive
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_AD_Archive extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Archive_ID id
@param trxName transaction
*/
public X_AD_Archive (Properties ctx, int AD_Archive_ID, String trxName)
{
super (ctx, AD_Archive_ID, trxName);
/** if (AD_Archive_ID == 0)
{
setAD_Archive_ID (0);
setBinaryData (null);
setIsReport (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Archive (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=754 */
public static final int Table_ID=MTable.getTable_ID("AD_Archive");

/** TableName=AD_Archive */
public static final String Table_Name="AD_Archive";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Archive");

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
StringBuffer sb = new StringBuffer ("X_AD_Archive[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Archive.
@param AD_Archive_ID Document and Report Archive */
public void setAD_Archive_ID (int AD_Archive_ID)
{
if (AD_Archive_ID < 1) throw new IllegalArgumentException ("AD_Archive_ID is mandatory.");
set_ValueNoCheck ("AD_Archive_ID", Integer.valueOf(AD_Archive_ID));
}
/** Get Archive.
@return Document and Report Archive */
public int getAD_Archive_ID() 
{
Integer ii = (Integer)get_Value("AD_Archive_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Archive_ID */
public static final String COLUMNNAME_AD_Archive_ID = "AD_Archive_ID";
/** Set Process.
@param AD_Process_ID Process or Report */
public void setAD_Process_ID (int AD_Process_ID)
{
if (AD_Process_ID <= 0) set_Value ("AD_Process_ID", null);
 else 
set_Value ("AD_Process_ID", Integer.valueOf(AD_Process_ID));
}
/** Get Process.
@return Process or Report */
public int getAD_Process_ID() 
{
Integer ii = (Integer)get_Value("AD_Process_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Process_ID */
public static final String COLUMNNAME_AD_Process_ID = "AD_Process_ID";
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID <= 0) set_Value ("AD_Table_ID", null);
 else 
set_Value ("AD_Table_ID", Integer.valueOf(AD_Table_ID));
}
/** Get Table.
@return Database Table information */
public int getAD_Table_ID() 
{
Integer ii = (Integer)get_Value("AD_Table_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Table_ID */
public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";
/** Set BinaryData.
@param BinaryData Binary Data */
public void setBinaryData (byte[] BinaryData)
{
if (BinaryData == null) throw new IllegalArgumentException ("BinaryData is mandatory.");
set_Value ("BinaryData", BinaryData);
}
/** Get BinaryData.
@return Binary Data */
public byte[] getBinaryData() 
{
return (byte[])get_Value("BinaryData");
}
/** Column name BinaryData */
public static final String COLUMNNAME_BinaryData = "BinaryData";
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
/** Column name C_BPartner_ID */
public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";
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
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Column name Help */
public static final String COLUMNNAME_Help = "Help";
/** Set Report.
@param IsReport Indicates a Report record */
public void setIsReport (boolean IsReport)
{
set_Value ("IsReport", Boolean.valueOf(IsReport));
}
/** Get Report.
@return Indicates a Report record */
public boolean isReport() 
{
Object oo = get_Value("IsReport");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsReport */
public static final String COLUMNNAME_IsReport = "IsReport";
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
/** Set Record ID.
@param Record_ID Direct internal record ID */
public void setRecord_ID (int Record_ID)
{
if (Record_ID <= 0) set_Value ("Record_ID", null);
 else 
set_Value ("Record_ID", Integer.valueOf(Record_ID));
}
/** Get Record ID.
@return Direct internal record ID */
public int getRecord_ID() 
{
Integer ii = (Integer)get_Value("Record_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Record_ID */
public static final String COLUMNNAME_Record_ID = "Record_ID";
}
