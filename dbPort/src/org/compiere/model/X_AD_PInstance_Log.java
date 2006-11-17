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
/** Generated Model for AD_PInstance_Log
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:53.281 */
public class X_AD_PInstance_Log extends PO
{
/** Standard Constructor
@param ctx context
@param AD_PInstance_Log_ID id
@param trxName transaction
*/
public X_AD_PInstance_Log (Properties ctx, int AD_PInstance_Log_ID, String trxName)
{
super (ctx, AD_PInstance_Log_ID, trxName);
/** if (AD_PInstance_Log_ID == 0)
{
setAD_PInstance_ID (0);
setLog_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_PInstance_Log (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=578 */
public static final int Table_ID=578;

/** TableName=AD_PInstance_Log */
public static final String Table_Name="AD_PInstance_Log";

protected static KeyNamePair Model = new KeyNamePair(578,"AD_PInstance_Log");

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
StringBuffer sb = new StringBuffer ("X_AD_PInstance_Log[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Process Instance.
@param AD_PInstance_ID Instance of the process */
public void setAD_PInstance_ID (int AD_PInstance_ID)
{
if (AD_PInstance_ID < 1) throw new IllegalArgumentException ("AD_PInstance_ID is mandatory.");
set_ValueNoCheck ("AD_PInstance_ID", new Integer(AD_PInstance_ID));
}
/** Get Process Instance.
@return Instance of the process */
public int getAD_PInstance_ID() 
{
Integer ii = (Integer)get_Value("AD_PInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Log.
@param Log_ID Log */
public void setLog_ID (int Log_ID)
{
if (Log_ID < 1) throw new IllegalArgumentException ("Log_ID is mandatory.");
set_ValueNoCheck ("Log_ID", new Integer(Log_ID));
}
/** Get Log.
@return Log */
public int getLog_ID() 
{
Integer ii = (Integer)get_Value("Log_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Process Date.
@param P_Date Process Parameter */
public void setP_Date (Timestamp P_Date)
{
set_ValueNoCheck ("P_Date", P_Date);
}
/** Get Process Date.
@return Process Parameter */
public Timestamp getP_Date() 
{
return (Timestamp)get_Value("P_Date");
}
/** Set Process ID.
@param P_ID Process ID */
public void setP_ID (int P_ID)
{
if (P_ID <= 0) set_ValueNoCheck ("P_ID", null);
 else 
set_ValueNoCheck ("P_ID", new Integer(P_ID));
}
/** Get Process ID.
@return Process ID */
public int getP_ID() 
{
Integer ii = (Integer)get_Value("P_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Process Message.
@param P_Msg Process Message */
public void setP_Msg (String P_Msg)
{
if (P_Msg != null && P_Msg.length() > 2000)
{
log.warning("Length > 2000 - truncated");
P_Msg = P_Msg.substring(0,1999);
}
set_ValueNoCheck ("P_Msg", P_Msg);
}
/** Get Process Message.
@return Process Message */
public String getP_Msg() 
{
return (String)get_Value("P_Msg");
}
/** Set Process Number.
@param P_Number Process Parameter */
public void setP_Number (BigDecimal P_Number)
{
set_ValueNoCheck ("P_Number", P_Number);
}
/** Get Process Number.
@return Process Parameter */
public BigDecimal getP_Number() 
{
BigDecimal bd = (BigDecimal)get_Value("P_Number");
if (bd == null) return Env.ZERO;
return bd;
}
}
