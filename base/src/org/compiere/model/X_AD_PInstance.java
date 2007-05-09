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
/** Generated Model for AD_PInstance
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_AD_PInstance extends PO
{
/** Standard Constructor
@param ctx context
@param AD_PInstance_ID id
@param trxName transaction
*/
public X_AD_PInstance (Properties ctx, int AD_PInstance_ID, String trxName)
{
super (ctx, AD_PInstance_ID, trxName);
/** if (AD_PInstance_ID == 0)
{
setAD_PInstance_ID (0);
setAD_Process_ID (0);
setIsProcessing (false);
setRecord_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_PInstance (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=282 */
public static final int Table_ID=MTable.getTable_ID("AD_PInstance");

/** TableName=AD_PInstance */
public static final String Table_Name="AD_PInstance";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_PInstance");

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
StringBuffer sb = new StringBuffer ("X_AD_PInstance[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Process Instance.
@param AD_PInstance_ID Instance of the process */
public void setAD_PInstance_ID (int AD_PInstance_ID)
{
if (AD_PInstance_ID < 1) throw new IllegalArgumentException ("AD_PInstance_ID is mandatory.");
set_ValueNoCheck ("AD_PInstance_ID", Integer.valueOf(AD_PInstance_ID));
}
/** Get Process Instance.
@return Instance of the process */
public int getAD_PInstance_ID() 
{
Integer ii = (Integer)get_Value("AD_PInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getAD_PInstance_ID()));
}
/** Column name AD_PInstance_ID */
public static final String COLUMNNAME_AD_PInstance_ID = "AD_PInstance_ID";
/** Set Process.
@param AD_Process_ID Process or Report */
public void setAD_Process_ID (int AD_Process_ID)
{
if (AD_Process_ID < 1) throw new IllegalArgumentException ("AD_Process_ID is mandatory.");
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
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID <= 0) set_Value ("AD_User_ID", null);
 else 
set_Value ("AD_User_ID", Integer.valueOf(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_User_ID */
public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";
/** Set Error Msg.
@param ErrorMsg Error Msg */
public void setErrorMsg (String ErrorMsg)
{
if (ErrorMsg != null && ErrorMsg.length() > 2000)
{
log.warning("Length > 2000 - truncated");
ErrorMsg = ErrorMsg.substring(0,1999);
}
set_Value ("ErrorMsg", ErrorMsg);
}
/** Get Error Msg.
@return Error Msg */
public String getErrorMsg() 
{
return (String)get_Value("ErrorMsg");
}
/** Column name ErrorMsg */
public static final String COLUMNNAME_ErrorMsg = "ErrorMsg";
/** Set Processing.
@param IsProcessing Processing */
public void setIsProcessing (boolean IsProcessing)
{
set_Value ("IsProcessing", Boolean.valueOf(IsProcessing));
}
/** Get Processing.
@return Processing */
public boolean isProcessing() 
{
Object oo = get_Value("IsProcessing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsProcessing */
public static final String COLUMNNAME_IsProcessing = "IsProcessing";
/** Set Record ID.
@param Record_ID Direct internal record ID */
public void setRecord_ID (int Record_ID)
{
if (Record_ID < 0) throw new IllegalArgumentException ("Record_ID is mandatory.");
set_ValueNoCheck ("Record_ID", Integer.valueOf(Record_ID));
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
/** Set Result.
@param Result Result of the action taken */
public void setResult (int Result)
{
set_Value ("Result", Integer.valueOf(Result));
}
/** Get Result.
@return Result of the action taken */
public int getResult() 
{
Integer ii = (Integer)get_Value("Result");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name Result */
public static final String COLUMNNAME_Result = "Result";
}
