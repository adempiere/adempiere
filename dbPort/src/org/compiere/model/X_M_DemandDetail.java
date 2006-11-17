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
/** Generated Model for M_DemandDetail
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:56:00.765 */
public class X_M_DemandDetail extends PO
{
/** Standard Constructor
@param ctx context
@param M_DemandDetail_ID id
@param trxName transaction
*/
public X_M_DemandDetail (Properties ctx, int M_DemandDetail_ID, String trxName)
{
super (ctx, M_DemandDetail_ID, trxName);
/** if (M_DemandDetail_ID == 0)
{
setM_DemandDetail_ID (0);
setM_DemandLine_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_DemandDetail (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=721 */
public static final int Table_ID=721;

/** TableName=M_DemandDetail */
public static final String Table_Name="M_DemandDetail";

protected static KeyNamePair Model = new KeyNamePair(721,"M_DemandDetail");

protected BigDecimal accessLevel = new BigDecimal(2);
/** AccessLevel
@return 2 - Client 
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
StringBuffer sb = new StringBuffer ("X_M_DemandDetail[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Sales Order Line.
@param C_OrderLine_ID Sales Order Line */
public void setC_OrderLine_ID (int C_OrderLine_ID)
{
if (C_OrderLine_ID <= 0) set_Value ("C_OrderLine_ID", null);
 else 
set_Value ("C_OrderLine_ID", new Integer(C_OrderLine_ID));
}
/** Get Sales Order Line.
@return Sales Order Line */
public int getC_OrderLine_ID() 
{
Integer ii = (Integer)get_Value("C_OrderLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Demand Detail.
@param M_DemandDetail_ID Material Demand Line Source Detail */
public void setM_DemandDetail_ID (int M_DemandDetail_ID)
{
if (M_DemandDetail_ID < 1) throw new IllegalArgumentException ("M_DemandDetail_ID is mandatory.");
set_ValueNoCheck ("M_DemandDetail_ID", new Integer(M_DemandDetail_ID));
}
/** Get Demand Detail.
@return Material Demand Line Source Detail */
public int getM_DemandDetail_ID() 
{
Integer ii = (Integer)get_Value("M_DemandDetail_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getM_DemandDetail_ID()));
}
/** Set Demand Line.
@param M_DemandLine_ID Material Demand Line */
public void setM_DemandLine_ID (int M_DemandLine_ID)
{
if (M_DemandLine_ID < 1) throw new IllegalArgumentException ("M_DemandLine_ID is mandatory.");
set_ValueNoCheck ("M_DemandLine_ID", new Integer(M_DemandLine_ID));
}
/** Get Demand Line.
@return Material Demand Line */
public int getM_DemandLine_ID() 
{
Integer ii = (Integer)get_Value("M_DemandLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Forecast Line.
@param M_ForecastLine_ID Forecast Line */
public void setM_ForecastLine_ID (int M_ForecastLine_ID)
{
if (M_ForecastLine_ID <= 0) set_Value ("M_ForecastLine_ID", null);
 else 
set_Value ("M_ForecastLine_ID", new Integer(M_ForecastLine_ID));
}
/** Get Forecast Line.
@return Forecast Line */
public int getM_ForecastLine_ID() 
{
Integer ii = (Integer)get_Value("M_ForecastLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Requisition Line.
@param M_RequisitionLine_ID Material Requisition Line */
public void setM_RequisitionLine_ID (int M_RequisitionLine_ID)
{
if (M_RequisitionLine_ID <= 0) set_Value ("M_RequisitionLine_ID", null);
 else 
set_Value ("M_RequisitionLine_ID", new Integer(M_RequisitionLine_ID));
}
/** Get Requisition Line.
@return Material Requisition Line */
public int getM_RequisitionLine_ID() 
{
Integer ii = (Integer)get_Value("M_RequisitionLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
