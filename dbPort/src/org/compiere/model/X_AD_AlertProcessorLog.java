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
/** Generated Model for AD_AlertProcessorLog
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_AD_AlertProcessorLog extends PO
{
/** Standard Constructor
@param ctx context
@param AD_AlertProcessorLog_ID id
@param trxName transaction
*/
public X_AD_AlertProcessorLog (Properties ctx, int AD_AlertProcessorLog_ID, String trxName)
{
super (ctx, AD_AlertProcessorLog_ID, trxName);
/** if (AD_AlertProcessorLog_ID == 0)
{
setAD_AlertProcessorLog_ID (0);
setAD_AlertProcessor_ID (0);
setIsError (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_AlertProcessorLog (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=699 */
public static final int Table_ID=MTable.getTable_ID("AD_AlertProcessorLog");

/** TableName=AD_AlertProcessorLog */
public static final String Table_Name="AD_AlertProcessorLog";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_AlertProcessorLog");

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
StringBuffer sb = new StringBuffer ("X_AD_AlertProcessorLog[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Alert Processor Log.
@param AD_AlertProcessorLog_ID Result of the execution of the Alert Processor */
public void setAD_AlertProcessorLog_ID (int AD_AlertProcessorLog_ID)
{
if (AD_AlertProcessorLog_ID < 1) throw new IllegalArgumentException ("AD_AlertProcessorLog_ID is mandatory.");
set_ValueNoCheck ("AD_AlertProcessorLog_ID", Integer.valueOf(AD_AlertProcessorLog_ID));
}
/** Get Alert Processor Log.
@return Result of the execution of the Alert Processor */
public int getAD_AlertProcessorLog_ID() 
{
Integer ii = (Integer)get_Value("AD_AlertProcessorLog_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_AlertProcessorLog_ID */
public static final String COLUMNNAME_AD_AlertProcessorLog_ID = "AD_AlertProcessorLog_ID";
/** Set Alert Processor.
@param AD_AlertProcessor_ID Alert Processor/Server Parameter */
public void setAD_AlertProcessor_ID (int AD_AlertProcessor_ID)
{
if (AD_AlertProcessor_ID < 1) throw new IllegalArgumentException ("AD_AlertProcessor_ID is mandatory.");
set_ValueNoCheck ("AD_AlertProcessor_ID", Integer.valueOf(AD_AlertProcessor_ID));
}
/** Get Alert Processor.
@return Alert Processor/Server Parameter */
public int getAD_AlertProcessor_ID() 
{
Integer ii = (Integer)get_Value("AD_AlertProcessor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_AlertProcessor_ID */
public static final String COLUMNNAME_AD_AlertProcessor_ID = "AD_AlertProcessor_ID";
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
