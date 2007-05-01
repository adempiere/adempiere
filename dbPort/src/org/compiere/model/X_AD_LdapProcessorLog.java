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
/** Generated Model for AD_LdapProcessorLog
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_AD_LdapProcessorLog extends PO
{
/** Standard Constructor
@param ctx context
@param AD_LdapProcessorLog_ID id
@param trxName transaction
*/
public X_AD_LdapProcessorLog (Properties ctx, int AD_LdapProcessorLog_ID, String trxName)
{
super (ctx, AD_LdapProcessorLog_ID, trxName);
/** if (AD_LdapProcessorLog_ID == 0)
{
setAD_LdapProcessorLog_ID (0);
setAD_LdapProcessor_ID (0);
setIsError (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_LdapProcessorLog (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=903 */
public static final int Table_ID=MTable.getTable_ID("AD_LdapProcessorLog");

/** TableName=AD_LdapProcessorLog */
public static final String Table_Name="AD_LdapProcessorLog";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_LdapProcessorLog");

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
StringBuffer sb = new StringBuffer ("X_AD_LdapProcessorLog[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Ldap Processor Log.
@param AD_LdapProcessorLog_ID LDAP Server Log */
public void setAD_LdapProcessorLog_ID (int AD_LdapProcessorLog_ID)
{
if (AD_LdapProcessorLog_ID < 1) throw new IllegalArgumentException ("AD_LdapProcessorLog_ID is mandatory.");
set_ValueNoCheck ("AD_LdapProcessorLog_ID", Integer.valueOf(AD_LdapProcessorLog_ID));
}
/** Get Ldap Processor Log.
@return LDAP Server Log */
public int getAD_LdapProcessorLog_ID() 
{
Integer ii = (Integer)get_Value("AD_LdapProcessorLog_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_LdapProcessorLog_ID */
public static final String COLUMNNAME_AD_LdapProcessorLog_ID = "AD_LdapProcessorLog_ID";
/** Set Ldap Processor.
@param AD_LdapProcessor_ID LDAP Server to authenticate and authorize external systems based on Adempiere */
public void setAD_LdapProcessor_ID (int AD_LdapProcessor_ID)
{
if (AD_LdapProcessor_ID < 1) throw new IllegalArgumentException ("AD_LdapProcessor_ID is mandatory.");
set_ValueNoCheck ("AD_LdapProcessor_ID", Integer.valueOf(AD_LdapProcessor_ID));
}
/** Get Ldap Processor.
@return LDAP Server to authenticate and authorize external systems based on Adempiere */
public int getAD_LdapProcessor_ID() 
{
Integer ii = (Integer)get_Value("AD_LdapProcessor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_LdapProcessor_ID */
public static final String COLUMNNAME_AD_LdapProcessor_ID = "AD_LdapProcessor_ID";
/** Set BinaryData.
@param BinaryData Binary Data */
public void setBinaryData (byte[] BinaryData)
{
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
/** Set Error.
@param IsError An Error occured in the execution */
public void setIsError (boolean IsError)
{
set_Value ("IsError", Boolean.valueOf(IsError));
}
/** Get Error.
@return An Error occured in the execution */
public boolean isError() 
{
Object oo = get_Value("IsError");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsError */
public static final String COLUMNNAME_IsError = "IsError";
/** Set Reference.
@param Reference Reference for this record */
public void setReference (String Reference)
{
if (Reference != null && Reference.length() > 60)
{
log.warning("Length > 60 - truncated");
Reference = Reference.substring(0,59);
}
set_Value ("Reference", Reference);
}
/** Get Reference.
@return Reference for this record */
public String getReference() 
{
return (String)get_Value("Reference");
}
/** Column name Reference */
public static final String COLUMNNAME_Reference = "Reference";
/** Set Summary.
@param Summary Textual summary of this request */
public void setSummary (String Summary)
{
if (Summary != null && Summary.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Summary = Summary.substring(0,1999);
}
set_Value ("Summary", Summary);
}
/** Get Summary.
@return Textual summary of this request */
public String getSummary() 
{
return (String)get_Value("Summary");
}
/** Column name Summary */
public static final String COLUMNNAME_Summary = "Summary";
/** Set Text Message.
@param TextMsg Text Message */
public void setTextMsg (String TextMsg)
{
if (TextMsg != null && TextMsg.length() > 2000)
{
log.warning("Length > 2000 - truncated");
TextMsg = TextMsg.substring(0,1999);
}
set_Value ("TextMsg", TextMsg);
}
/** Get Text Message.
@return Text Message */
public String getTextMsg() 
{
return (String)get_Value("TextMsg");
}
/** Column name TextMsg */
public static final String COLUMNNAME_TextMsg = "TextMsg";
}
