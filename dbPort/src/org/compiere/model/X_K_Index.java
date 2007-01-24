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
/** Generated Model for K_Index
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_K_Index extends PO
{
/** Standard Constructor
@param ctx context
@param K_Index_ID id
@param trxName transaction
*/
public X_K_Index (Properties ctx, int K_Index_ID, String trxName)
{
super (ctx, K_Index_ID, trxName);
/** if (K_Index_ID == 0)
{
setAD_Table_ID (0);
setK_INDEX_ID (0);
setKeyword (null);
setRecord_ID (0);
setSourceUpdated (new Timestamp(System.currentTimeMillis()));
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_K_Index (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=900 */
public static final int Table_ID=MTable.getTable_ID("K_Index");

/** TableName=K_Index */
public static final String Table_Name="K_Index";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"K_Index");

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
StringBuffer sb = new StringBuffer ("X_K_Index[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID < 1) throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
set_ValueNoCheck ("AD_Table_ID", Integer.valueOf(AD_Table_ID));
}
/** Get Table.
@return Database Table information */
public int getAD_Table_ID() 
{
Integer ii = (Integer)get_Value("AD_Table_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Web Project.
@param CM_WebProject_ID A web project is the main data container for Containers, URLs, Ads, Media etc. */
public void setCM_WebProject_ID (int CM_WebProject_ID)
{
if (CM_WebProject_ID <= 0) set_ValueNoCheck ("CM_WebProject_ID", null);
 else 
set_ValueNoCheck ("CM_WebProject_ID", Integer.valueOf(CM_WebProject_ID));
}
/** Get Web Project.
@return A web project is the main data container for Containers, URLs, Ads, Media etc. */
public int getCM_WebProject_ID() 
{
Integer ii = (Integer)get_Value("CM_WebProject_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Document Type.
@param C_DocType_ID Document type or rules */
public void setC_DocType_ID (int C_DocType_ID)
{
if (C_DocType_ID <= 0) set_ValueNoCheck ("C_DocType_ID", null);
 else 
set_ValueNoCheck ("C_DocType_ID", Integer.valueOf(C_DocType_ID));
}
/** Get Document Type.
@return Document type or rules */
public int getC_DocType_ID() 
{
Integer ii = (Integer)get_Value("C_DocType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Excerpt.
@param Excerpt Surrounding text of the keyword */
public void setExcerpt (String Excerpt)
{
if (Excerpt != null && Excerpt.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Excerpt = Excerpt.substring(0,1999);
}
set_ValueNoCheck ("Excerpt", Excerpt);
}
/** Get Excerpt.
@return Surrounding text of the keyword */
public String getExcerpt() 
{
return (String)get_Value("Excerpt");
}
/** Set Index.
@param K_INDEX_ID Text Search Index */
public void setK_INDEX_ID (int K_INDEX_ID)
{
if (K_INDEX_ID < 1) throw new IllegalArgumentException ("K_INDEX_ID is mandatory.");
set_ValueNoCheck ("K_INDEX_ID", Integer.valueOf(K_INDEX_ID));
}
/** Get Index.
@return Text Search Index */
public int getK_INDEX_ID() 
{
Integer ii = (Integer)get_Value("K_INDEX_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
set_ValueNoCheck ("Keyword", Keyword);
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
/** Set Request Type.
@param R_RequestType_ID Type of request (e.g. Inquiry, Complaint, ..) */
public void setR_RequestType_ID (int R_RequestType_ID)
{
if (R_RequestType_ID <= 0) set_ValueNoCheck ("R_RequestType_ID", null);
 else 
set_ValueNoCheck ("R_RequestType_ID", Integer.valueOf(R_RequestType_ID));
}
/** Get Request Type.
@return Type of request (e.g. Inquiry, Complaint, ..) */
public int getR_RequestType_ID() 
{
Integer ii = (Integer)get_Value("R_RequestType_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Source Updated.
@param SourceUpdated Date the source document was updated */
public void setSourceUpdated (Timestamp SourceUpdated)
{
if (SourceUpdated == null) throw new IllegalArgumentException ("SourceUpdated is mandatory.");
set_Value ("SourceUpdated", SourceUpdated);
}
/** Get Source Updated.
@return Date the source document was updated */
public Timestamp getSourceUpdated() 
{
return (Timestamp)get_Value("SourceUpdated");
}
}
