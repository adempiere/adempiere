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
/** Generated Model for S_Training_Class
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_S_Training_Class extends PO
{
/** Standard Constructor
@param ctx context
@param S_Training_Class_ID id
@param trxName transaction
*/
public X_S_Training_Class (Properties ctx, int S_Training_Class_ID, String trxName)
{
super (ctx, S_Training_Class_ID, trxName);
/** if (S_Training_Class_ID == 0)
{
setEndDate (new Timestamp(System.currentTimeMillis()));
setM_Product_ID (0);
setS_Training_Class_ID (0);
setS_Training_ID (0);
setStartDate (new Timestamp(System.currentTimeMillis()));
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_S_Training_Class (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=537 */
public static final int Table_ID=MTable.getTable_ID("S_Training_Class");

/** TableName=S_Training_Class */
public static final String Table_Name="S_Training_Class";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"S_Training_Class");

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
StringBuffer sb = new StringBuffer ("X_S_Training_Class[").append(get_ID()).append("]");
return sb.toString();
}
/** Set End Date.
@param EndDate Last effective date (inclusive) */
public void setEndDate (Timestamp EndDate)
{
if (EndDate == null) throw new IllegalArgumentException ("EndDate is mandatory.");
set_Value ("EndDate", EndDate);
}
/** Get End Date.
@return Last effective date (inclusive) */
public Timestamp getEndDate() 
{
return (Timestamp)get_Value("EndDate");
}
/** Column name EndDate */
public static final String COLUMNNAME_EndDate = "EndDate";
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
/** Set Training Class.
@param S_Training_Class_ID The actual training class instance */
public void setS_Training_Class_ID (int S_Training_Class_ID)
{
if (S_Training_Class_ID < 1) throw new IllegalArgumentException ("S_Training_Class_ID is mandatory.");
set_ValueNoCheck ("S_Training_Class_ID", Integer.valueOf(S_Training_Class_ID));
}
/** Get Training Class.
@return The actual training class instance */
public int getS_Training_Class_ID() 
{
Integer ii = (Integer)get_Value("S_Training_Class_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name S_Training_Class_ID */
public static final String COLUMNNAME_S_Training_Class_ID = "S_Training_Class_ID";
/** Set Training.
@param S_Training_ID Repeated Training */
public void setS_Training_ID (int S_Training_ID)
{
if (S_Training_ID < 1) throw new IllegalArgumentException ("S_Training_ID is mandatory.");
set_ValueNoCheck ("S_Training_ID", Integer.valueOf(S_Training_ID));
}
/** Get Training.
@return Repeated Training */
public int getS_Training_ID() 
{
Integer ii = (Integer)get_Value("S_Training_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name S_Training_ID */
public static final String COLUMNNAME_S_Training_ID = "S_Training_ID";
/** Set Start Date.
@param StartDate First effective day (inclusive) */
public void setStartDate (Timestamp StartDate)
{
if (StartDate == null) throw new IllegalArgumentException ("StartDate is mandatory.");
set_Value ("StartDate", StartDate);
}
/** Get Start Date.
@return First effective day (inclusive) */
public Timestamp getStartDate() 
{
return (Timestamp)get_Value("StartDate");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getStartDate()));
}
/** Column name StartDate */
public static final String COLUMNNAME_StartDate = "StartDate";
}
