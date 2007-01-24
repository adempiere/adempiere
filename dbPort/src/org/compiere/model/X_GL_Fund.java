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
/** Generated Model for GL_Fund
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_GL_Fund extends PO
{
/** Standard Constructor
@param ctx context
@param GL_Fund_ID id
@param trxName transaction
*/
public X_GL_Fund (Properties ctx, int GL_Fund_ID, String trxName)
{
super (ctx, GL_Fund_ID, trxName);
/** if (GL_Fund_ID == 0)
{
setAmt (Env.ZERO);
setC_AcctSchema_ID (0);
setGL_Fund_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_GL_Fund (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=823 */
public static final int Table_ID=MTable.getTable_ID("GL_Fund");

/** TableName=GL_Fund */
public static final String Table_Name="GL_Fund";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"GL_Fund");

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
StringBuffer sb = new StringBuffer ("X_GL_Fund[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Amount.
@param Amt Amount */
public void setAmt (BigDecimal Amt)
{
if (Amt == null) throw new IllegalArgumentException ("Amt is mandatory.");
set_Value ("Amt", Amt);
}
/** Get Amount.
@return Amount */
public BigDecimal getAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("Amt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Accounting Schema.
@param C_AcctSchema_ID Rules for accounting */
public void setC_AcctSchema_ID (int C_AcctSchema_ID)
{
if (C_AcctSchema_ID < 1) throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
set_Value ("C_AcctSchema_ID", Integer.valueOf(C_AcctSchema_ID));
}
/** Get Accounting Schema.
@return Rules for accounting */
public int getC_AcctSchema_ID() 
{
Integer ii = (Integer)get_Value("C_AcctSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Date From.
@param DateFrom Starting date for a range */
public void setDateFrom (Timestamp DateFrom)
{
set_Value ("DateFrom", DateFrom);
}
/** Get Date From.
@return Starting date for a range */
public Timestamp getDateFrom() 
{
return (Timestamp)get_Value("DateFrom");
}
/** Set Date To.
@param DateTo End date of a date range */
public void setDateTo (Timestamp DateTo)
{
set_Value ("DateTo", DateTo);
}
/** Get Date To.
@return End date of a date range */
public Timestamp getDateTo() 
{
return (Timestamp)get_Value("DateTo");
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
/** Set GL Fund.
@param GL_Fund_ID General Ledger Funds Control */
public void setGL_Fund_ID (int GL_Fund_ID)
{
if (GL_Fund_ID < 1) throw new IllegalArgumentException ("GL_Fund_ID is mandatory.");
set_ValueNoCheck ("GL_Fund_ID", Integer.valueOf(GL_Fund_ID));
}
/** Get GL Fund.
@return General Ledger Funds Control */
public int getGL_Fund_ID() 
{
Integer ii = (Integer)get_Value("GL_Fund_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 120)
{
log.warning("Length > 120 - truncated");
Name = Name.substring(0,119);
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
}
