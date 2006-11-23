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
/** Generated Model for R_RequestProcessor_Route
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_R_RequestProcessor_Route extends PO
{
/** Standard Constructor
@param ctx context
@param R_RequestProcessor_Route_ID id
@param trxName transaction
*/
public X_R_RequestProcessor_Route (Properties ctx, int R_RequestProcessor_Route_ID, String trxName)
{
super (ctx, R_RequestProcessor_Route_ID, trxName);
/** if (R_RequestProcessor_Route_ID == 0)
{
setAD_User_ID (0);
setR_RequestProcessor_ID (0);
setR_RequestProcessor_Route_ID (0);
setSeqNo (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_R_RequestProcessor_Route (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=474 */
public static final int Table_ID=474;

/** TableName=R_RequestProcessor_Route */
public static final String Table_Name="R_RequestProcessor_Route";

protected static KeyNamePair Model = new KeyNamePair(474,"R_RequestProcessor_Route");

protected BigDecimal accessLevel = new BigDecimal(2);
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
StringBuffer sb = new StringBuffer ("X_R_RequestProcessor_Route[").append(get_ID()).append("]");
return sb.toString();
}
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID < 1) throw new IllegalArgumentException ("AD_User_ID is mandatory.");
set_Value ("AD_User_ID", new Integer(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Keyword.
@param Keyword Case insensitive keyword */
public void setKeyword (String Keyword)
{
if (Keyword != null && Keyword.length() > 60)
{
log.warning("Length > 60 - truncated");
Keyword = Keyword.substring(0,59);
}
set_Value ("Keyword", Keyword);
}
/** Get Keyword.
@return Case insensitive keyword */
public String getKeyword() 
{
return (String)get_Value("Keyword");
}
/** Set Request Processor.
@param R_RequestProcessor_ID Processor for Requests */
public void setR_RequestProcessor_ID (int R_RequestProcessor_ID)
{
if (R_RequestProcessor_ID < 1) throw new IllegalArgumentException ("R_RequestProcessor_ID is mandatory.");
set_ValueNoCheck ("R_RequestProcessor_ID", new Integer(R_RequestProcessor_ID));
}
/** Get Request Processor.
@return Processor for Requests */
public int getR_RequestProcessor_ID() 
{
Integer ii = (Integer)get_Value("R_RequestProcessor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Request Routing.
@param R_RequestProcessor_Route_ID Automatic routing of requests */
public void setR_RequestProcessor_Route_ID (int R_RequestProcessor_Route_ID)
{
if (R_RequestProcessor_Route_ID < 1) throw new IllegalArgumentException ("R_RequestProcessor_Route_ID is mandatory.");
set_ValueNoCheck ("R_RequestProcessor_Route_ID", new Integer(R_RequestProcessor_Route_ID));
}
/** Get Request Routing.
@return Automatic routing of requests */
public int getR_RequestProcessor_Route_ID() 
{
Integer ii = (Integer)get_Value("R_RequestProcessor_Route_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Request Type.
@param R_RequestType_ID Type of request (e.g. Inquiry, Complaint, ..) */
public void setR_RequestType_ID (int R_RequestType_ID)
{
if (R_RequestType_ID <= 0) set_Value ("R_RequestType_ID", null);
 else 
set_Value ("R_RequestType_ID", new Integer(R_RequestType_ID));
}
/** Get Request Type.
@return Type of request (e.g. Inquiry, Complaint, ..) */
public int getR_RequestType_ID() 
{
Integer ii = (Integer)get_Value("R_RequestType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Sequence.
@param SeqNo Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_Value ("SeqNo", new Integer(SeqNo));
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getSeqNo()));
}
}
