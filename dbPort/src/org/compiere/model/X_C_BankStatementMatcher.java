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
/** Generated Model for C_BankStatementMatcher
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_C_BankStatementMatcher extends PO
{
/** Standard Constructor
@param ctx context
@param C_BankStatementMatcher_ID id
@param trxName transaction
*/
public X_C_BankStatementMatcher (Properties ctx, int C_BankStatementMatcher_ID, String trxName)
{
super (ctx, C_BankStatementMatcher_ID, trxName);
/** if (C_BankStatementMatcher_ID == 0)
{
setC_BankStatementMatcher_ID (0);
setClassname (null);
setName (null);
setSeqNo (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_BankStatementMatcher (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=658 */
public static final int Table_ID=MTable.getTable_ID("C_BankStatementMatcher");

/** TableName=C_BankStatementMatcher */
public static final String Table_Name="C_BankStatementMatcher";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_BankStatementMatcher");

protected BigDecimal accessLevel = BigDecimal.valueOf(2);
/** AccessLevel
@return 2 - Client 
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
StringBuffer sb = new StringBuffer ("X_C_BankStatementMatcher[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Bank Statement Matcher.
@param C_BankStatementMatcher_ID Algorithm to match Bank Statement Info to Business Partners, Invoices and Payments */
public void setC_BankStatementMatcher_ID (int C_BankStatementMatcher_ID)
{
if (C_BankStatementMatcher_ID < 1) throw new IllegalArgumentException ("C_BankStatementMatcher_ID is mandatory.");
set_ValueNoCheck ("C_BankStatementMatcher_ID", Integer.valueOf(C_BankStatementMatcher_ID));
}
/** Get Bank Statement Matcher.
@return Algorithm to match Bank Statement Info to Business Partners, Invoices and Payments */
public int getC_BankStatementMatcher_ID() 
{
Integer ii = (Integer)get_Value("C_BankStatementMatcher_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BankStatementMatcher_ID */
public static final String COLUMNNAME_C_BankStatementMatcher_ID = "C_BankStatementMatcher_ID";
/** Set Classname.
@param Classname Java Classname */
public void setClassname (String Classname)
{
if (Classname == null) throw new IllegalArgumentException ("Classname is mandatory.");
if (Classname.length() > 60)
{
log.warning("Length > 60 - truncated");
Classname = Classname.substring(0,59);
}
set_Value ("Classname", Classname);
}
/** Get Classname.
@return Java Classname */
public String getClassname() 
{
return (String)get_Value("Classname");
}
/** Column name Classname */
public static final String COLUMNNAME_Classname = "Classname";
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
/** Set Sequence.
@param SeqNo Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_Value ("SeqNo", Integer.valueOf(SeqNo));
}
/** Get Sequence.
@return Method of ordering records;
 lowest number comes first */
public int getSeqNo() 
{
Integer ii = (Integer)get_Value("SeqNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name SeqNo */
public static final String COLUMNNAME_SeqNo = "SeqNo";
}
