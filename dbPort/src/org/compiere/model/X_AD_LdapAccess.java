/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
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
/** Generated Model for AD_LdapAccess
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_AD_LdapAccess extends PO
{
/** Standard Constructor
@param ctx context
@param AD_LdapAccess_ID id
@param trxName transaction
*/
public X_AD_LdapAccess (Properties ctx, int AD_LdapAccess_ID, String trxName)
{
super (ctx, AD_LdapAccess_ID, trxName);
/** if (AD_LdapAccess_ID == 0)
{
setAD_LdapAccess_ID (0);
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
public X_AD_LdapAccess (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=904 */
public static final int Table_ID=904;

/** TableName=AD_LdapAccess */
public static final String Table_Name="AD_LdapAccess";

protected static KeyNamePair Model = new KeyNamePair(904,"AD_LdapAccess");

protected BigDecimal accessLevel = new BigDecimal(7);
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
StringBuffer sb = new StringBuffer ("X_AD_LdapAccess[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Ldap Access.
@param AD_LdapAccess_ID Ldap Access Log */
public void setAD_LdapAccess_ID (int AD_LdapAccess_ID)
{
if (AD_LdapAccess_ID < 1) throw new IllegalArgumentException ("AD_LdapAccess_ID is mandatory.");
set_ValueNoCheck ("AD_LdapAccess_ID", new Integer(AD_LdapAccess_ID));
}
/** Get Ldap Access.
@return Ldap Access Log */
public int getAD_LdapAccess_ID() 
{
Integer ii = (Integer)get_Value("AD_LdapAccess_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Ldap Processor.
@param AD_LdapProcessor_ID LDAP Server to authenticate and authorize external systems based on Compiere */
public void setAD_LdapProcessor_ID (int AD_LdapProcessor_ID)
{
if (AD_LdapProcessor_ID < 1) throw new IllegalArgumentException ("AD_LdapProcessor_ID is mandatory.");
set_ValueNoCheck ("AD_LdapProcessor_ID", new Integer(AD_LdapProcessor_ID));
}
/** Get Ldap Processor.
@return LDAP Server to authenticate and authorize external systems based on Compiere */
public int getAD_LdapProcessor_ID() 
{
Integer ii = (Integer)get_Value("AD_LdapProcessor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID <= 0) set_ValueNoCheck ("AD_User_ID", null);
 else 
set_ValueNoCheck ("AD_User_ID", new Integer(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_User_ID()));
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
/** Set Error.
@param IsError An Error occured in the execution */
public void setIsError (boolean IsError)
{
set_ValueNoCheck ("IsError", new Boolean(IsError));
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
/** Set Interest Area.
@param R_InterestArea_ID Interest Area or Topic */
public void setR_InterestArea_ID (int R_InterestArea_ID)
{
if (R_InterestArea_ID <= 0) set_ValueNoCheck ("R_InterestArea_ID", null);
 else 
set_ValueNoCheck ("R_InterestArea_ID", new Integer(R_InterestArea_ID));
}
/** Get Interest Area.
@return Interest Area or Topic */
public int getR_InterestArea_ID() 
{
Integer ii = (Integer)get_Value("R_InterestArea_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Summary.
@param Summary Textual summary of this request */
public void setSummary (String Summary)
{
if (Summary != null && Summary.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Summary = Summary.substring(0,1999);
}
set_ValueNoCheck ("Summary", Summary);
}
/** Get Summary.
@return Textual summary of this request */
public String getSummary() 
{
return (String)get_Value("Summary");
}
}
