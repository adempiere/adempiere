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
/** Generated Model for R_RequestProcessorLog
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:56:03.187 */
public class X_R_RequestProcessorLog extends PO
{
/** Standard Constructor
@param ctx context
@param R_RequestProcessorLog_ID id
@param trxName transaction
*/
public X_R_RequestProcessorLog (Properties ctx, int R_RequestProcessorLog_ID, String trxName)
{
super (ctx, R_RequestProcessorLog_ID, trxName);
/** if (R_RequestProcessorLog_ID == 0)
{
setIsError (false);
setR_RequestProcessorLog_ID (0);
setR_RequestProcessor_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_R_RequestProcessorLog (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=659 */
public static final int Table_ID=659;

/** TableName=R_RequestProcessorLog */
public static final String Table_Name="R_RequestProcessorLog";

protected static KeyNamePair Model = new KeyNamePair(659,"R_RequestProcessorLog");

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
StringBuffer sb = new StringBuffer ("X_R_RequestProcessorLog[").append(get_ID()).append("]");
return sb.toString();
}
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
set_Value ("IsError", new Boolean(IsError));
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
/** Set Request Processor Log.
@param R_RequestProcessorLog_ID Result of the execution of the Request Processor */
public void setR_RequestProcessorLog_ID (int R_RequestProcessorLog_ID)
{
if (R_RequestProcessorLog_ID < 1) throw new IllegalArgumentException ("R_RequestProcessorLog_ID is mandatory.");
set_ValueNoCheck ("R_RequestProcessorLog_ID", new Integer(R_RequestProcessorLog_ID));
}
/** Get Request Processor Log.
@return Result of the execution of the Request Processor */
public int getR_RequestProcessorLog_ID() 
{
Integer ii = (Integer)get_Value("R_RequestProcessorLog_ID");
if (ii == null) return 0;
return ii.intValue();
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
}
