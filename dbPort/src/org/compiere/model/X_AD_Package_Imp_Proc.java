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
/** Generated Model for AD_Package_Imp_Proc
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.2 - $Id$ */
public class X_AD_Package_Imp_Proc extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Package_Imp_Proc_ID id
@param trxName transaction
*/
public X_AD_Package_Imp_Proc (Properties ctx, int AD_Package_Imp_Proc_ID, String trxName)
{
super (ctx, AD_Package_Imp_Proc_ID, trxName);
/** if (AD_Package_Imp_Proc_ID == 0)
{
setAD_Package_Imp_Proc_ID (0);
setAD_Package_Source_Type (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Package_Imp_Proc (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=1000008 */
public static final int Table_ID=MTable.getTable_ID("AD_Package_Imp_Proc");

/** TableName=AD_Package_Imp_Proc */
public static final String Table_Name="AD_Package_Imp_Proc";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Package_Imp_Proc");

protected BigDecimal accessLevel = new BigDecimal(4);
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
StringBuffer sb = new StringBuffer ("X_AD_Package_Imp_Proc[").append(get_ID()).append("]");
return sb.toString();
}
/** Set AD_Override_Dict.
@param AD_Override_Dict AD_Override_Dict */
public void setAD_Override_Dict (boolean AD_Override_Dict)
{
set_Value ("AD_Override_Dict", new Boolean(AD_Override_Dict));
}
/** Get AD_Override_Dict.
@return AD_Override_Dict */
public boolean isAD_Override_Dict() 
{
Object oo = get_Value("AD_Override_Dict");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set AD_Package_Dir.
@param AD_Package_Dir AD_Package_Dir */
public void setAD_Package_Dir (String AD_Package_Dir)
{
if (AD_Package_Dir != null && AD_Package_Dir.length() > 255)
{
log.warning("Length > 255 - truncated");
AD_Package_Dir = AD_Package_Dir.substring(0,254);
}
set_Value ("AD_Package_Dir", AD_Package_Dir);
}
/** Get AD_Package_Dir.
@return AD_Package_Dir */
public String getAD_Package_Dir() 
{
return (String)get_Value("AD_Package_Dir");
}
/** Set AD_Package_Imp_Proc_ID.
@param AD_Package_Imp_Proc_ID AD_Package_Imp_Proc_ID */
public void setAD_Package_Imp_Proc_ID (int AD_Package_Imp_Proc_ID)
{
if (AD_Package_Imp_Proc_ID < 1) throw new IllegalArgumentException ("AD_Package_Imp_Proc_ID is mandatory.");
set_ValueNoCheck ("AD_Package_Imp_Proc_ID", new Integer(AD_Package_Imp_Proc_ID));
}
/** Get AD_Package_Imp_Proc_ID.
@return AD_Package_Imp_Proc_ID */
public int getAD_Package_Imp_Proc_ID() 
{
Integer ii = (Integer)get_Value("AD_Package_Imp_Proc_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set AD_Package_Source.
@param AD_Package_Source AD_Package_Source */
public void setAD_Package_Source (String AD_Package_Source)
{
if (AD_Package_Source != null && AD_Package_Source.length() > 255)
{
log.warning("Length > 255 - truncated");
AD_Package_Source = AD_Package_Source.substring(0,254);
}
set_Value ("AD_Package_Source", AD_Package_Source);
}
/** Get AD_Package_Source.
@return AD_Package_Source */
public String getAD_Package_Source() 
{
return (String)get_Value("AD_Package_Source");
}

/** AD_Package_Source_Type AD_Reference_ID=1000005 */
public static final int AD_PACKAGE_SOURCE_TYPE_AD_Reference_ID=1000005;
/** File = File */
public static final String AD_PACKAGE_SOURCE_TYPE_File = "File";
/** WebService = WS */
public static final String AD_PACKAGE_SOURCE_TYPE_WebService = "WS";
/** Set AD_Package_Source_Type.
@param AD_Package_Source_Type AD_Package_Source_Type */
public void setAD_Package_Source_Type (String AD_Package_Source_Type)
{
if (AD_Package_Source_Type == null) throw new IllegalArgumentException ("AD_Package_Source_Type is mandatory");
if (AD_Package_Source_Type.equals("File") || AD_Package_Source_Type.equals("WS"));
 else throw new IllegalArgumentException ("AD_Package_Source_Type Invalid value - " + AD_Package_Source_Type + " - Reference_ID=1000005 - File - WS");
if (AD_Package_Source_Type.length() > 10)
{
log.warning("Length > 10 - truncated");
AD_Package_Source_Type = AD_Package_Source_Type.substring(0,9);
}
set_Value ("AD_Package_Source_Type", AD_Package_Source_Type);
}
/** Get AD_Package_Source_Type.
@return AD_Package_Source_Type */
public String getAD_Package_Source_Type() 
{
return (String)get_Value("AD_Package_Source_Type");
}
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", new Boolean(Processing));
}
/** Get Process Now.
@return Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
}
