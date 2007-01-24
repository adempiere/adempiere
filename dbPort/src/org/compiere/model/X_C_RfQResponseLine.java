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
/** Generated Model for C_RfQResponseLine
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_C_RfQResponseLine extends PO
{
/** Standard Constructor
@param ctx context
@param C_RfQResponseLine_ID id
@param trxName transaction
*/
public X_C_RfQResponseLine (Properties ctx, int C_RfQResponseLine_ID, String trxName)
{
super (ctx, C_RfQResponseLine_ID, trxName);
/** if (C_RfQResponseLine_ID == 0)
{
setC_RfQLine_ID (0);
setC_RfQResponseLine_ID (0);
setC_RfQResponse_ID (0);
setIsSelectedWinner (false);
setIsSelfService (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_RfQResponseLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=673 */
public static final int Table_ID=MTable.getTable_ID("C_RfQResponseLine");

/** TableName=C_RfQResponseLine */
public static final String Table_Name="C_RfQResponseLine";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_RfQResponseLine");

protected BigDecimal accessLevel = BigDecimal.valueOf(1);
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
StringBuffer sb = new StringBuffer ("X_C_RfQResponseLine[").append(get_ID()).append("]");
return sb.toString();
}
/** Set RfQ Line.
@param C_RfQLine_ID Request for Quotation Line */
public void setC_RfQLine_ID (int C_RfQLine_ID)
{
if (C_RfQLine_ID < 1) throw new IllegalArgumentException ("C_RfQLine_ID is mandatory.");
set_ValueNoCheck ("C_RfQLine_ID", Integer.valueOf(C_RfQLine_ID));
}
/** Get RfQ Line.
@return Request for Quotation Line */
public int getC_RfQLine_ID() 
{
Integer ii = (Integer)get_Value("C_RfQLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set RfQ Response Line.
@param C_RfQResponseLine_ID Request for Quotation Response Line */
public void setC_RfQResponseLine_ID (int C_RfQResponseLine_ID)
{
if (C_RfQResponseLine_ID < 1) throw new IllegalArgumentException ("C_RfQResponseLine_ID is mandatory.");
set_ValueNoCheck ("C_RfQResponseLine_ID", Integer.valueOf(C_RfQResponseLine_ID));
}
/** Get RfQ Response Line.
@return Request for Quotation Response Line */
public int getC_RfQResponseLine_ID() 
{
Integer ii = (Integer)get_Value("C_RfQResponseLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set RfQ Response.
@param C_RfQResponse_ID Request for Quotation Response from a potential Vendor */
public void setC_RfQResponse_ID (int C_RfQResponse_ID)
{
if (C_RfQResponse_ID < 1) throw new IllegalArgumentException ("C_RfQResponse_ID is mandatory.");
set_ValueNoCheck ("C_RfQResponse_ID", Integer.valueOf(C_RfQResponse_ID));
}
/** Get RfQ Response.
@return Request for Quotation Response from a potential Vendor */
public int getC_RfQResponse_ID() 
{
Integer ii = (Integer)get_Value("C_RfQResponse_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Work Complete.
@param DateWorkComplete Date when work is (planned to be) complete */
public void setDateWorkComplete (Timestamp DateWorkComplete)
{
set_Value ("DateWorkComplete", DateWorkComplete);
}
/** Get Work Complete.
@return Date when work is (planned to be) complete */
public Timestamp getDateWorkComplete() 
{
return (Timestamp)get_Value("DateWorkComplete");
}
/** Set Work Start.
@param DateWorkStart Date when work is (planned to be) started */
public void setDateWorkStart (Timestamp DateWorkStart)
{
set_Value ("DateWorkStart", DateWorkStart);
}
/** Get Work Start.
@return Date when work is (planned to be) started */
public Timestamp getDateWorkStart() 
{
return (Timestamp)get_Value("DateWorkStart");
}
/** Set Delivery Days.
@param DeliveryDays Number of Days (planned) until Delivery */
public void setDeliveryDays (int DeliveryDays)
{
set_Value ("DeliveryDays", Integer.valueOf(DeliveryDays));
}
/** Get Delivery Days.
@return Number of Days (planned) until Delivery */
public int getDeliveryDays() 
{
Integer ii = (Integer)get_Value("DeliveryDays");
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
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Set Selected Winner.
@param IsSelectedWinner The resonse is the selected winner */
public void setIsSelectedWinner (boolean IsSelectedWinner)
{
set_Value ("IsSelectedWinner", Boolean.valueOf(IsSelectedWinner));
}
/** Get Selected Winner.
@return The resonse is the selected winner */
public boolean isSelectedWinner() 
{
Object oo = get_Value("IsSelectedWinner");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
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
}
