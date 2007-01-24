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
/** Generated Model for C_RfQ_Topic
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_C_RfQ_Topic extends PO
{
/** Standard Constructor
@param ctx context
@param C_RfQ_Topic_ID id
@param trxName transaction
*/
public X_C_RfQ_Topic (Properties ctx, int C_RfQ_Topic_ID, String trxName)
{
super (ctx, C_RfQ_Topic_ID, trxName);
/** if (C_RfQ_Topic_ID == 0)
{
setC_RfQ_Topic_ID (0);
setIsSelfService (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_RfQ_Topic (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=671 */
public static final int Table_ID=MTable.getTable_ID("C_RfQ_Topic");

/** TableName=C_RfQ_Topic */
public static final String Table_Name="C_RfQ_Topic";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_RfQ_Topic");

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
StringBuffer sb = new StringBuffer ("X_C_RfQ_Topic[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Print Format.
@param AD_PrintFormat_ID Data Print Format */
public void setAD_PrintFormat_ID (int AD_PrintFormat_ID)
{
if (AD_PrintFormat_ID <= 0) set_Value ("AD_PrintFormat_ID", null);
 else 
set_Value ("AD_PrintFormat_ID", Integer.valueOf(AD_PrintFormat_ID));
}
/** Get Print Format.
@return Data Print Format */
public int getAD_PrintFormat_ID() 
{
Integer ii = (Integer)get_Value("AD_PrintFormat_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set RfQ Topic.
@param C_RfQ_Topic_ID Topic for Request for Quotations */
public void setC_RfQ_Topic_ID (int C_RfQ_Topic_ID)
{
if (C_RfQ_Topic_ID < 1) throw new IllegalArgumentException ("C_RfQ_Topic_ID is mandatory.");
set_ValueNoCheck ("C_RfQ_Topic_ID", Integer.valueOf(C_RfQ_Topic_ID));
}
/** Get RfQ Topic.
@return Topic for Request for Quotations */
public int getC_RfQ_Topic_ID() 
{
Integer ii = (Integer)get_Value("C_RfQ_Topic_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Self-Service.
@param IsSelfService This is a Self-Service entry or this entry can be changed via Self-Service */
public void setIsSelfService (boolean IsSelfService)
{
set_Value ("IsSelfService", Boolean.valueOf(IsSelfService));
}
/** Get Self-Service.
@return This is a Self-Service entry or this entry can be changed via Self-Service */
public boolean isSelfService() 
{
Object oo = get_Value("IsSelfService");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
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
}
