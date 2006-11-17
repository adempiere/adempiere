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
/** Generated Model for AD_PInstance_Para
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:53.296 */
public class X_AD_PInstance_Para extends PO
{
/** Standard Constructor
@param ctx context
@param AD_PInstance_Para_ID id
@param trxName transaction
*/
public X_AD_PInstance_Para (Properties ctx, int AD_PInstance_Para_ID, String trxName)
{
super (ctx, AD_PInstance_Para_ID, trxName);
/** if (AD_PInstance_Para_ID == 0)
{
setAD_PInstance_ID (0);
setSeqNo (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_PInstance_Para (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=283 */
public static final int Table_ID=283;

/** TableName=AD_PInstance_Para */
public static final String Table_Name="AD_PInstance_Para";

protected static KeyNamePair Model = new KeyNamePair(283,"AD_PInstance_Para");

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
StringBuffer sb = new StringBuffer ("X_AD_PInstance_Para[").append(get_ID()).append("]");
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
/** Set Info.
@param Info Information */
public void setInfo (String Info)
{
if (Info != null && Info.length() > 60)
{
log.warning("Length > 60 - truncated");
Info = Info.substring(0,59);
}
set_Value ("Info", Info);
}
/** Get Info.
@return Information */
public String getInfo() 
{
return (String)get_Value("Info");
}
/** Set Info To.
@param Info_To Info To */
public void setInfo_To (String Info_To)
{
if (Info_To != null && Info_To.length() > 60)
{
log.warning("Length > 60 - truncated");
Info_To = Info_To.substring(0,59);
}
set_Value ("Info_To", Info_To);
}
/** Get Info To.
@return Info To */
public String getInfo_To() 
{
return (String)get_Value("Info_To");
}
/** Set Process Date.
@param P_Date Process Parameter */
public void setP_Date (Timestamp P_Date)
{
set_Value ("P_Date", P_Date);
}
/** Get Process Date.
@return Process Parameter */
public Timestamp getP_Date() 
{
return (Timestamp)get_Value("P_Date");
}
/** Set Process Date To.
@param P_Date_To Process Parameter */
public void setP_Date_To (Timestamp P_Date_To)
{
set_Value ("P_Date_To", P_Date_To);
}
/** Get Process Date To.
@return Process Parameter */
public Timestamp getP_Date_To() 
{
return (Timestamp)get_Value("P_Date_To");
}
/** Set Process Number.
@param P_Number Process Parameter */
public void setP_Number (BigDecimal P_Number)
{
set_Value ("P_Number", P_Number);
}
/** Get Process Number.
@return Process Parameter */
public BigDecimal getP_Number() 
{
BigDecimal bd = (BigDecimal)get_Value("P_Number");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Process Number To.
@param P_Number_To Process Parameter */
public void setP_Number_To (BigDecimal P_Number_To)
{
set_Value ("P_Number_To", P_Number_To);
}
/** Get Process Number To.
@return Process Parameter */
public BigDecimal getP_Number_To() 
{
BigDecimal bd = (BigDecimal)get_Value("P_Number_To");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Process String.
@param P_String Process Parameter */
public void setP_String (String P_String)
{
if (P_String != null && P_String.length() > 60)
{
log.warning("Length > 60 - truncated");
P_String = P_String.substring(0,59);
}
set_Value ("P_String", P_String);
}
/** Get Process String.
@return Process Parameter */
public String getP_String() 
{
return (String)get_Value("P_String");
}
/** Set Process String To.
@param P_String_To Process Parameter */
public void setP_String_To (String P_String_To)
{
if (P_String_To != null && P_String_To.length() > 60)
{
log.warning("Length > 60 - truncated");
P_String_To = P_String_To.substring(0,59);
}
set_Value ("P_String_To", P_String_To);
}
/** Get Process String To.
@return Process Parameter */
public String getP_String_To() 
{
return (String)get_Value("P_String_To");
}
/** Set Parameter Name.
@param ParameterName Parameter Name */
public void setParameterName (String ParameterName)
{
if (ParameterName != null && ParameterName.length() > 60)
{
log.warning("Length > 60 - truncated");
ParameterName = ParameterName.substring(0,59);
}
set_Value ("ParameterName", ParameterName);
}
/** Get Parameter Name.
@return Parameter Name */
public String getParameterName() 
{
return (String)get_Value("ParameterName");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getParameterName());
}
/** Set Sequence.
@param SeqNo Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_ValueNoCheck ("SeqNo", new Integer(SeqNo));
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
}
