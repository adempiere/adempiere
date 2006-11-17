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
/** Generated Model for R_StandardResponse
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:56:03.484 */
public class X_R_StandardResponse extends PO
{
/** Standard Constructor
@param ctx context
@param R_StandardResponse_ID id
@param trxName transaction
*/
public X_R_StandardResponse (Properties ctx, int R_StandardResponse_ID, String trxName)
{
super (ctx, R_StandardResponse_ID, trxName);
/** if (R_StandardResponse_ID == 0)
{
setName (null);
setR_StandardResponse_ID (0);
setResponseText (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_R_StandardResponse (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=775 */
public static final int Table_ID=775;

/** TableName=R_StandardResponse */
public static final String Table_Name="R_StandardResponse";

protected static KeyNamePair Model = new KeyNamePair(775,"R_StandardResponse");

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
StringBuffer sb = new StringBuffer ("X_R_StandardResponse[").append(get_ID()).append("]");
return sb.toString();
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
/** Set Standard Response.
@param R_StandardResponse_ID Request Standard Response  */
public void setR_StandardResponse_ID (int R_StandardResponse_ID)
{
if (R_StandardResponse_ID < 1) throw new IllegalArgumentException ("R_StandardResponse_ID is mandatory.");
set_ValueNoCheck ("R_StandardResponse_ID", new Integer(R_StandardResponse_ID));
}
/** Get Standard Response.
@return Request Standard Response  */
public int getR_StandardResponse_ID() 
{
Integer ii = (Integer)get_Value("R_StandardResponse_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Response Text.
@param ResponseText Request Response Text */
public void setResponseText (String ResponseText)
{
if (ResponseText == null) throw new IllegalArgumentException ("ResponseText is mandatory.");
if (ResponseText.length() > 2000)
{
log.warning("Length > 2000 - truncated");
ResponseText = ResponseText.substring(0,1999);
}
set_Value ("ResponseText", ResponseText);
}
/** Get Response Text.
@return Request Response Text */
public String getResponseText() 
{
return (String)get_Value("ResponseText");
}
}
