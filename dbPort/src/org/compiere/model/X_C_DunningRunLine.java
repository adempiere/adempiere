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
/** Generated Model for C_DunningRunLine
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_C_DunningRunLine extends PO
{
/** Standard Constructor
@param ctx context
@param C_DunningRunLine_ID id
@param trxName transaction
*/
public X_C_DunningRunLine (Properties ctx, int C_DunningRunLine_ID, String trxName)
{
super (ctx, C_DunningRunLine_ID, trxName);
/** if (C_DunningRunLine_ID == 0)
{
setAmt (Env.ZERO);
setC_DunningRunEntry_ID (0);
setC_DunningRunLine_ID (0);
setConvertedAmt (Env.ZERO);
setDaysDue (0);
setFeeAmt (Env.ZERO);
setInterestAmt (Env.ZERO);
setIsInDispute (false);
setOpenAmt (Env.ZERO);
setProcessed (false);
setTimesDunned (0);
setTotalAmt (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_DunningRunLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=524 */
public static final int Table_ID=524;

/** TableName=C_DunningRunLine */
public static final String Table_Name="C_DunningRunLine";

protected static KeyNamePair Model = new KeyNamePair(524,"C_DunningRunLine");

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
StringBuffer sb = new StringBuffer ("X_C_DunningRunLine[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Amount.
@param Amt Amount */
public void setAmt (BigDecimal Amt)
{
if (Amt == null) throw new IllegalArgumentException ("Amt is mandatory.");
set_Value ("Amt", Amt);
}
/** Get Amount.
@return Amount */
public BigDecimal getAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("Amt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Dunning Run Entry.
@param C_DunningRunEntry_ID Dunning Run Entry */
public void setC_DunningRunEntry_ID (int C_DunningRunEntry_ID)
{
if (C_DunningRunEntry_ID < 1) throw new IllegalArgumentException ("C_DunningRunEntry_ID is mandatory.");
set_ValueNoCheck ("C_DunningRunEntry_ID", new Integer(C_DunningRunEntry_ID));
}
/** Get Dunning Run Entry.
@return Dunning Run Entry */
public int getC_DunningRunEntry_ID() 
{
Integer ii = (Integer)get_Value("C_DunningRunEntry_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Dunning Run Line.
@param C_DunningRunLine_ID Dunning Run Line */
public void setC_DunningRunLine_ID (int C_DunningRunLine_ID)
{
if (C_DunningRunLine_ID < 1) throw new IllegalArgumentException ("C_DunningRunLine_ID is mandatory.");
set_ValueNoCheck ("C_DunningRunLine_ID", new Integer(C_DunningRunLine_ID));
}
/** Get Dunning Run Line.
@return Dunning Run Line */
public int getC_DunningRunLine_ID() 
{
Integer ii = (Integer)get_Value("C_DunningRunLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Invoice.
@param C_Invoice_ID Invoice Identifier */
public void setC_Invoice_ID (int C_Invoice_ID)
{
if (C_Invoice_ID <= 0) set_Value ("C_Invoice_ID", null);
 else 
set_Value ("C_Invoice_ID", new Integer(C_Invoice_ID));
}
/** Get Invoice.
@return Invoice Identifier */
public int getC_Invoice_ID() 
{
Integer ii = (Integer)get_Value("C_Invoice_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_Invoice_ID()));
}
/** Set Payment.
@param C_Payment_ID Payment identifier */
public void setC_Payment_ID (int C_Payment_ID)
{
if (C_Payment_ID <= 0) set_Value ("C_Payment_ID", null);
 else 
set_Value ("C_Payment_ID", new Integer(C_Payment_ID));
}
/** Get Payment.
@return Payment identifier */
public int getC_Payment_ID() 
{
Integer ii = (Integer)get_Value("C_Payment_ID");
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
/** Set Days due.
@param DaysDue Number of days due (negative: due in number of days) */
public void setDaysDue (int DaysDue)
{
set_Value ("DaysDue", new Integer(DaysDue));
}
/** Get Days due.
@return Number of days due (negative: due in number of days) */
public int getDaysDue() 
{
Integer ii = (Integer)get_Value("DaysDue");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Fee Amount.
@param FeeAmt Fee amount in invoice currency */
public void setFeeAmt (BigDecimal FeeAmt)
{
if (FeeAmt == null) throw new IllegalArgumentException ("FeeAmt is mandatory.");
set_Value ("FeeAmt", FeeAmt);
}
/** Get Fee Amount.
@return Fee amount in invoice currency */
public BigDecimal getFeeAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("FeeAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Interest Amount.
@param InterestAmt Interest Amount */
public void setInterestAmt (BigDecimal InterestAmt)
{
if (InterestAmt == null) throw new IllegalArgumentException ("InterestAmt is mandatory.");
set_Value ("InterestAmt", InterestAmt);
}
/** Get Interest Amount.
@return Interest Amount */
public BigDecimal getInterestAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("InterestAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set In Dispute.
@param IsInDispute Document is in dispute */
public void setIsInDispute (boolean IsInDispute)
{
set_Value ("IsInDispute", new Boolean(IsInDispute));
}
/** Get In Dispute.
@return Document is in dispute */
public boolean isInDispute() 
{
Object oo = get_Value("IsInDispute");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Open Amount.
@param OpenAmt Open item amount */
public void setOpenAmt (BigDecimal OpenAmt)
{
if (OpenAmt == null) throw new IllegalArgumentException ("OpenAmt is mandatory.");
set_Value ("OpenAmt", OpenAmt);
}
/** Get Open Amount.
@return Open item amount */
public BigDecimal getOpenAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("OpenAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", new Boolean(Processed));
}
/** Get Processed.
@return The document has been processed */
public boolean isProcessed() 
{
Object oo = get_Value("Processed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Times Dunned.
@param TimesDunned Number of times dunned previously */
public void setTimesDunned (int TimesDunned)
{
set_Value ("TimesDunned", new Integer(TimesDunned));
}
/** Get Times Dunned.
@return Number of times dunned previously */
public int getTimesDunned() 
{
Integer ii = (Integer)get_Value("TimesDunned");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Total Amount.
@param TotalAmt Total Amount */
public void setTotalAmt (BigDecimal TotalAmt)
{
if (TotalAmt == null) throw new IllegalArgumentException ("TotalAmt is mandatory.");
set_Value ("TotalAmt", TotalAmt);
}
/** Get Total Amount.
@return Total Amount */
public BigDecimal getTotalAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("TotalAmt");
if (bd == null) return Env.ZERO;
return bd;
}
}
