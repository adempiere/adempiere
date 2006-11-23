/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
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
/** Generated Model for C_Bank
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_C_Bank extends PO
{
/** Standard Constructor
@param ctx context
@param C_Bank_ID id
@param trxName transaction
*/
public X_C_Bank (Properties ctx, int C_Bank_ID, String trxName)
{
super (ctx, C_Bank_ID, trxName);
/** if (C_Bank_ID == 0)
{
setC_Bank_ID (0);
setIsOwnBank (true);	// Y
setName (null);
setRoutingNo (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Bank (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=296 */
public static final int Table_ID=296;

/** TableName=C_Bank */
public static final String Table_Name="C_Bank";

protected static KeyNamePair Model = new KeyNamePair(296,"C_Bank");

protected BigDecimal accessLevel = new BigDecimal(3);
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
StringBuffer sb = new StringBuffer ("X_C_Bank[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Bank.
@param C_Bank_ID Bank */
public void setC_Bank_ID (int C_Bank_ID)
{
if (C_Bank_ID < 1) throw new IllegalArgumentException ("C_Bank_ID is mandatory.");
set_ValueNoCheck ("C_Bank_ID", new Integer(C_Bank_ID));
}
/** Get Bank.
@return Bank */
public int getC_Bank_ID() 
{
Integer ii = (Integer)get_Value("C_Bank_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Address.
@param C_Location_ID Location or Address */
public void setC_Location_ID (int C_Location_ID)
{
if (C_Location_ID <= 0) set_Value ("C_Location_ID", null);
 else 
set_Value ("C_Location_ID", new Integer(C_Location_ID));
}
/** Get Address.
@return Location or Address */
public int getC_Location_ID() 
{
Integer ii = (Integer)get_Value("C_Location_ID");
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
/** Set Own Bank.
@param IsOwnBank Bank for this Organization */
public void setIsOwnBank (boolean IsOwnBank)
{
set_Value ("IsOwnBank", new Boolean(IsOwnBank));
}
/** Get Own Bank.
@return Bank for this Organization */
public boolean isOwnBank() 
{
Object oo = get_Value("IsOwnBank");
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
/** Set Routing No.
@param RoutingNo Bank Routing Number */
public void setRoutingNo (String RoutingNo)
{
if (RoutingNo == null) throw new IllegalArgumentException ("RoutingNo is mandatory.");
if (RoutingNo.length() > 20)
{
log.warning("Length > 20 - truncated");
RoutingNo = RoutingNo.substring(0,19);
}
set_Value ("RoutingNo", RoutingNo);
}
/** Get Routing No.
@return Bank Routing Number */
public String getRoutingNo() 
{
return (String)get_Value("RoutingNo");
}
/** Set Swift code.
@param SwiftCode Swift Code or BIC */
public void setSwiftCode (String SwiftCode)
{
if (SwiftCode != null && SwiftCode.length() > 20)
{
log.warning("Length > 20 - truncated");
SwiftCode = SwiftCode.substring(0,19);
}
set_Value ("SwiftCode", SwiftCode);
}
/** Get Swift code.
@return Swift Code or BIC */
public String getSwiftCode() 
{
return (String)get_Value("SwiftCode");
}
}
