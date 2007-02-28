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
/** Generated Model for M_ProductDownload
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_M_ProductDownload extends PO
{
/** Standard Constructor
@param ctx context
@param M_ProductDownload_ID id
@param trxName transaction
*/
public X_M_ProductDownload (Properties ctx, int M_ProductDownload_ID, String trxName)
{
super (ctx, M_ProductDownload_ID, trxName);
/** if (M_ProductDownload_ID == 0)
{
setDownloadURL (null);
setM_ProductDownload_ID (0);
setM_Product_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_ProductDownload (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=777 */
public static final int Table_ID=MTable.getTable_ID("M_ProductDownload");

/** TableName=M_ProductDownload */
public static final String Table_Name="M_ProductDownload";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_ProductDownload");

protected BigDecimal accessLevel = BigDecimal.valueOf(3);
/** AccessLevel
@return 3 - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_M_ProductDownload[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Download URL.
@param DownloadURL URL of the Download files */
public void setDownloadURL (String DownloadURL)
{
if (DownloadURL == null) throw new IllegalArgumentException ("DownloadURL is mandatory.");
if (DownloadURL.length() > 120)
{
log.warning("Length > 120 - truncated");
DownloadURL = DownloadURL.substring(0,119);
}
set_Value ("DownloadURL", DownloadURL);
}
/** Get Download URL.
@return URL of the Download files */
public String getDownloadURL() 
{
return (String)get_Value("DownloadURL");
}
/** Column name DownloadURL */
public static final String COLUMNNAME_DownloadURL = "DownloadURL";
/** Set Product Download.
@param M_ProductDownload_ID Product downloads */
public void setM_ProductDownload_ID (int M_ProductDownload_ID)
{
if (M_ProductDownload_ID < 1) throw new IllegalArgumentException ("M_ProductDownload_ID is mandatory.");
set_ValueNoCheck ("M_ProductDownload_ID", Integer.valueOf(M_ProductDownload_ID));
}
/** Get Product Download.
@return Product downloads */
public int getM_ProductDownload_ID() 
{
Integer ii = (Integer)get_Value("M_ProductDownload_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_ProductDownload_ID */
public static final String COLUMNNAME_M_ProductDownload_ID = "M_ProductDownload_ID";
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
set_ValueNoCheck ("M_Product_ID", Integer.valueOf(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Product_ID */
public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
}
