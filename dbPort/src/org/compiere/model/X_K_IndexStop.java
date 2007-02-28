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
/** Generated Model for K_IndexStop
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_K_IndexStop extends PO
{
/** Standard Constructor
@param ctx context
@param K_IndexStop_ID id
@param trxName transaction
*/
public X_K_IndexStop (Properties ctx, int K_IndexStop_ID, String trxName)
{
super (ctx, K_IndexStop_ID, trxName);
/** if (K_IndexStop_ID == 0)
{
setIsManual (true);	// Y
setK_IndexStop_ID (0);
setKeyword (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_K_IndexStop (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=901 */
public static final int Table_ID=MTable.getTable_ID("K_IndexStop");

/** TableName=K_IndexStop */
public static final String Table_Name="K_IndexStop";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"K_IndexStop");

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
StringBuffer sb = new StringBuffer ("X_K_IndexStop[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Web Project.
@param CM_WebProject_ID A web project is the main data container for Containers, URLs, Ads, Media etc. */
public void setCM_WebProject_ID (int CM_WebProject_ID)
{
if (CM_WebProject_ID <= 0) set_Value ("CM_WebProject_ID", null);
 else 
set_Value ("CM_WebProject_ID", Integer.valueOf(CM_WebProject_ID));
}
/** Get Web Project.
@return A web project is the main data container for Containers, URLs, Ads, Media etc. */
public int getCM_WebProject_ID() 
{
Integer ii = (Integer)get_Value("CM_WebProject_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CM_WebProject_ID */
public static final String COLUMNNAME_CM_WebProject_ID = "CM_WebProject_ID";
/** Set Document Type.
@param C_DocType_ID Document type or rules */
public void setC_DocType_ID (int C_DocType_ID)
{
if (C_DocType_ID <= 0) set_Value ("C_DocType_ID", null);
 else 
set_Value ("C_DocType_ID", Integer.valueOf(C_DocType_ID));
}
/** Get Document Type.
@return Document type or rules */
public int getC_DocType_ID() 
{
Integer ii = (Integer)get_Value("C_DocType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_DocType_ID */
public static final String COLUMNNAME_C_DocType_ID = "C_DocType_ID";
/** Set Manual.
@param IsManual This is a manual process */
public void setIsManual (boolean IsManual)
{
set_Value ("IsManual", Boolean.valueOf(IsManual));
}
/** Get Manual.
@return This is a manual process */
public boolean isManual() 
{
Object oo = get_Value("IsManual");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsManual */
public static final String COLUMNNAME_IsManual = "IsManual";
/** Set Index Stop.
@param K_IndexStop_ID Keyword not to be indexed */
public void setK_IndexStop_ID (int K_IndexStop_ID)
{
if (K_IndexStop_ID < 1) throw new IllegalArgumentException ("K_IndexStop_ID is mandatory.");
set_ValueNoCheck ("K_IndexStop_ID", Integer.valueOf(K_IndexStop_ID));
}
/** Get Index Stop.
@return Keyword not to be indexed */
public int getK_IndexStop_ID() 
{
Integer ii = (Integer)get_Value("K_IndexStop_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name K_IndexStop_ID */
public static final String COLUMNNAME_K_IndexStop_ID = "K_IndexStop_ID";
/** Set Keyword.
@param Keyword Case insensitive keyword */
public void setKeyword (String Keyword)
{
if (Keyword == null) throw new IllegalArgumentException ("Keyword is mandatory.");
if (Keyword.length() > 255)
{
log.warning("Length > 255 - truncated");
Keyword = Keyword.substring(0,254);
}
set_Value ("Keyword", Keyword);
}
/** Get Keyword.
@return Case insensitive keyword */
public String getKeyword() 
{
return (String)get_Value("Keyword");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getKeyword());
}
/** Column name Keyword */
public static final String COLUMNNAME_Keyword = "Keyword";
/** Set Request Type.
@param R_RequestType_ID Type of request (e.g. Inquiry, Complaint, ..) */
public void setR_RequestType_ID (int R_RequestType_ID)
{
if (R_RequestType_ID <= 0) set_Value ("R_RequestType_ID", null);
 else 
set_Value ("R_RequestType_ID", Integer.valueOf(R_RequestType_ID));
}
/** Get Request Type.
@return Type of request (e.g. Inquiry, Complaint, ..) */
public int getR_RequestType_ID() 
{
Integer ii = (Integer)get_Value("R_RequestType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name R_RequestType_ID */
public static final String COLUMNNAME_R_RequestType_ID = "R_RequestType_ID";
}
