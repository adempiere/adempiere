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
/** Generated Model for CM_Container_URL
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_CM_Container_URL extends PO
{
/** Standard Constructor
@param ctx context
@param CM_Container_URL_ID id
@param trxName transaction
*/
public X_CM_Container_URL (Properties ctx, int CM_Container_URL_ID, String trxName)
{
super (ctx, CM_Container_URL_ID, trxName);
/** if (CM_Container_URL_ID == 0)
{
setCM_Container_ID (0);
setCM_Container_URL_ID (0);
setChecked (new Timestamp(System.currentTimeMillis()));
setLast_Result (null);
setStatus (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_CM_Container_URL (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=865 */
public static final int Table_ID=MTable.getTable_ID("CM_Container_URL");

/** TableName=CM_Container_URL */
public static final String Table_Name="CM_Container_URL";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"CM_Container_URL");

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
StringBuffer sb = new StringBuffer ("X_CM_Container_URL[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Web Container.
@param CM_Container_ID Web Container contains content like images, text etc. */
public void setCM_Container_ID (int CM_Container_ID)
{
if (CM_Container_ID < 1) throw new IllegalArgumentException ("CM_Container_ID is mandatory.");
set_Value ("CM_Container_ID", Integer.valueOf(CM_Container_ID));
}
/** Get Web Container.
@return Web Container contains content like images, text etc. */
public int getCM_Container_ID() 
{
Integer ii = (Integer)get_Value("CM_Container_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CM_Container_ID */
public static final String COLUMNNAME_CM_Container_ID = "CM_Container_ID";
/** Set Container URL.
@param CM_Container_URL_ID Contains info on used URLs */
public void setCM_Container_URL_ID (int CM_Container_URL_ID)
{
if (CM_Container_URL_ID < 1) throw new IllegalArgumentException ("CM_Container_URL_ID is mandatory.");
set_ValueNoCheck ("CM_Container_URL_ID", Integer.valueOf(CM_Container_URL_ID));
}
/** Get Container URL.
@return Contains info on used URLs */
public int getCM_Container_URL_ID() 
{
Integer ii = (Integer)get_Value("CM_Container_URL_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CM_Container_URL_ID */
public static final String COLUMNNAME_CM_Container_URL_ID = "CM_Container_URL_ID";
/** Set Last Checked.
@param Checked Info when we did the last check */
public void setChecked (Timestamp Checked)
{
if (Checked == null) throw new IllegalArgumentException ("Checked is mandatory.");
set_Value ("Checked", Checked);
}
/** Get Last Checked.
@return Info when we did the last check */
public Timestamp getChecked() 
{
return (Timestamp)get_Value("Checked");
}
/** Column name Checked */
public static final String COLUMNNAME_Checked = "Checked";
/** Set Last Result.
@param Last_Result Contains data on the last check result */
public void setLast_Result (String Last_Result)
{
if (Last_Result == null) throw new IllegalArgumentException ("Last_Result is mandatory.");
if (Last_Result.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Last_Result = Last_Result.substring(0,1999);
}
set_Value ("Last_Result", Last_Result);
}
/** Get Last Result.
@return Contains data on the last check result */
public String getLast_Result() 
{
return (String)get_Value("Last_Result");
}
/** Column name Last_Result */
public static final String COLUMNNAME_Last_Result = "Last_Result";
/** Set Status.
@param Status Status of the currently running check */
public void setStatus (String Status)
{
if (Status == null) throw new IllegalArgumentException ("Status is mandatory.");
if (Status.length() > 2)
{
log.warning("Length > 2 - truncated");
Status = Status.substring(0,1);
}
set_Value ("Status", Status);
}
/** Get Status.
@return Status of the currently running check */
public String getStatus() 
{
return (String)get_Value("Status");
}
/** Column name Status */
public static final String COLUMNNAME_Status = "Status";
}
