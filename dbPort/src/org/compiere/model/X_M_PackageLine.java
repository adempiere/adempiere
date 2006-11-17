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
/** Generated Model for M_PackageLine
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:56:01.656 */
public class X_M_PackageLine extends PO
{
/** Standard Constructor
@param ctx context
@param M_PackageLine_ID id
@param trxName transaction
*/
public X_M_PackageLine (Properties ctx, int M_PackageLine_ID, String trxName)
{
super (ctx, M_PackageLine_ID, trxName);
/** if (M_PackageLine_ID == 0)
{
setM_InOutLine_ID (0);
setM_PackageLine_ID (0);
setM_Package_ID (0);
setQty (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_PackageLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=663 */
public static final int Table_ID=663;

/** TableName=M_PackageLine */
public static final String Table_Name="M_PackageLine";

protected static KeyNamePair Model = new KeyNamePair(663,"M_PackageLine");

protected BigDecimal accessLevel = new BigDecimal(1);
/** AccessLevel
@return 1 - Org 
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
StringBuffer sb = new StringBuffer ("X_M_PackageLine[").append(get_ID()).append("]");
return sb.toString();
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
/** Set Shipment/Receipt Line.
@param M_InOutLine_ID Line on Shipment or Receipt document */
public void setM_InOutLine_ID (int M_InOutLine_ID)
{
if (M_InOutLine_ID < 1) throw new IllegalArgumentException ("M_InOutLine_ID is mandatory.");
set_ValueNoCheck ("M_InOutLine_ID", new Integer(M_InOutLine_ID));
}
/** Get Shipment/Receipt Line.
@return Line on Shipment or Receipt document */
public int getM_InOutLine_ID() 
{
Integer ii = (Integer)get_Value("M_InOutLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Package Line.
@param M_PackageLine_ID The detail content of the Package */
public void setM_PackageLine_ID (int M_PackageLine_ID)
{
if (M_PackageLine_ID < 1) throw new IllegalArgumentException ("M_PackageLine_ID is mandatory.");
set_ValueNoCheck ("M_PackageLine_ID", new Integer(M_PackageLine_ID));
}
/** Get Package Line.
@return The detail content of the Package */
public int getM_PackageLine_ID() 
{
Integer ii = (Integer)get_Value("M_PackageLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Package.
@param M_Package_ID Shipment Package */
public void setM_Package_ID (int M_Package_ID)
{
if (M_Package_ID < 1) throw new IllegalArgumentException ("M_Package_ID is mandatory.");
set_ValueNoCheck ("M_Package_ID", new Integer(M_Package_ID));
}
/** Get Package.
@return Shipment Package */
public int getM_Package_ID() 
{
Integer ii = (Integer)get_Value("M_Package_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getM_Package_ID()));
}
/** Set Quantity.
@param Qty Quantity */
public void setQty (BigDecimal Qty)
{
if (Qty == null) throw new IllegalArgumentException ("Qty is mandatory.");
set_Value ("Qty", Qty);
}
/** Get Quantity.
@return Quantity */
public BigDecimal getQty() 
{
BigDecimal bd = (BigDecimal)get_Value("Qty");
if (bd == null) return Env.ZERO;
return bd;
}
}
