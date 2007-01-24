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
/** Generated Model for C_CommissionDetail
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_C_CommissionDetail extends PO
{
/** Standard Constructor
@param ctx context
@param C_CommissionDetail_ID id
@param trxName transaction
*/
public X_C_CommissionDetail (Properties ctx, int C_CommissionDetail_ID, String trxName)
{
super (ctx, C_CommissionDetail_ID, trxName);
/** if (C_CommissionDetail_ID == 0)
{
setActualAmt (Env.ZERO);
setActualQty (Env.ZERO);
setC_CommissionAmt_ID (0);
setC_CommissionDetail_ID (0);
setC_Currency_ID (0);
setConvertedAmt (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_CommissionDetail (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=437 */
public static final int Table_ID=MTable.getTable_ID("C_CommissionDetail");

/** TableName=C_CommissionDetail */
public static final String Table_Name="C_CommissionDetail";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_CommissionDetail");

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
StringBuffer sb = new StringBuffer ("X_C_CommissionDetail[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Actual Amount.
@param ActualAmt The actual amount */
public void setActualAmt (BigDecimal ActualAmt)
{
if (ActualAmt == null) throw new IllegalArgumentException ("ActualAmt is mandatory.");
set_Value ("ActualAmt", ActualAmt);
}
/** Get Actual Amount.
@return The actual amount */
public BigDecimal getActualAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("ActualAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Actual Quantity.
@param ActualQty The actual quantity */
public void setActualQty (BigDecimal ActualQty)
{
if (ActualQty == null) throw new IllegalArgumentException ("ActualQty is mandatory.");
set_Value ("ActualQty", ActualQty);
}
/** Get Actual Quantity.
@return The actual quantity */
public BigDecimal getActualQty() 
{
BigDecimal bd = (BigDecimal)get_Value("ActualQty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Commission Amount.
@param C_CommissionAmt_ID Generated Commission Amount  */
public void setC_CommissionAmt_ID (int C_CommissionAmt_ID)
{
if (C_CommissionAmt_ID < 1) throw new IllegalArgumentException ("C_CommissionAmt_ID is mandatory.");
set_ValueNoCheck ("C_CommissionAmt_ID", Integer.valueOf(C_CommissionAmt_ID));
}
/** Get Commission Amount.
@return Generated Commission Amount  */
public int getC_CommissionAmt_ID() 
{
Integer ii = (Integer)get_Value("C_CommissionAmt_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Commission Detail.
@param C_CommissionDetail_ID Supporting information for Commission Amounts */
public void setC_CommissionDetail_ID (int C_CommissionDetail_ID)
{
if (C_CommissionDetail_ID < 1) throw new IllegalArgumentException ("C_CommissionDetail_ID is mandatory.");
set_ValueNoCheck ("C_CommissionDetail_ID", Integer.valueOf(C_CommissionDetail_ID));
}
/** Get Commission Detail.
@return Supporting information for Commission Amounts */
public int getC_CommissionDetail_ID() 
{
Integer ii = (Integer)get_Value("C_CommissionDetail_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID < 1) throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
set_Value ("C_Currency_ID", Integer.valueOf(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Invoice Line.
@param C_InvoiceLine_ID Invoice Detail Line */
public void setC_InvoiceLine_ID (int C_InvoiceLine_ID)
{
if (C_InvoiceLine_ID <= 0) set_ValueNoCheck ("C_InvoiceLine_ID", null);
 else 
set_ValueNoCheck ("C_InvoiceLine_ID", Integer.valueOf(C_InvoiceLine_ID));
}
/** Get Invoice Line.
@return Invoice Detail Line */
public int getC_InvoiceLine_ID() 
{
Integer ii = (Integer)get_Value("C_InvoiceLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Sales Order Line.
@param C_OrderLine_ID Sales Order Line */
public void setC_OrderLine_ID (int C_OrderLine_ID)
{
if (C_OrderLine_ID <= 0) set_ValueNoCheck ("C_OrderLine_ID", null);
 else 
set_ValueNoCheck ("C_OrderLine_ID", Integer.valueOf(C_OrderLine_ID));
}
/** Get Sales Order Line.
@return Sales Order Line */
public int getC_OrderLine_ID() 
{
Integer ii = (Integer)get_Value("C_OrderLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Converted Amount.
@param ConvertedAmt Converted Amount */
public void setConvertedAmt (BigDecimal ConvertedAmt)
{
if (ConvertedAmt == null) throw new IllegalArgumentException ("ConvertedAmt is mandatory.");
set_Value ("ConvertedAmt", ConvertedAmt);
}
/** Get Converted Amount.
@return Converted Amount */
public BigDecimal getConvertedAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("ConvertedAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Info.
@param Info Information */
public void setInfo (String Info)
{
if (Info != null && Info.length() > 60)
{
log.warning("Length > 60 - truncated");
Info = Info.substring(0,59);
}
set_Value ("Info", Info);
}
/** Get Info.
@return Information */
public String getInfo() 
{
return (String)get_Value("Info");
}
/** Set Reference.
@param Reference Reference for this record */
public void setReference (String Reference)
{
if (Reference != null && Reference.length() > 60)
{
log.warning("Length > 60 - truncated");
Reference = Reference.substring(0,59);
}
set_Value ("Reference", Reference);
}
/** Get Reference.
@return Reference for this record */
public String getReference() 
{
return (String)get_Value("Reference");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getReference());
}
}
