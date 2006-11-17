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
/** Generated Model for C_InvoiceBatch
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:57.546 */
public class X_C_InvoiceBatch extends PO
{
/** Standard Constructor
@param ctx context
@param C_InvoiceBatch_ID id
@param trxName transaction
*/
public X_C_InvoiceBatch (Properties ctx, int C_InvoiceBatch_ID, String trxName)
{
super (ctx, C_InvoiceBatch_ID, trxName);
/** if (C_InvoiceBatch_ID == 0)
{
setC_Currency_ID (0);	// @$C_Currency_ID@
setC_InvoiceBatch_ID (0);
setControlAmt (Env.ZERO);	// 0
setDateDoc (new Timestamp(System.currentTimeMillis()));	// @#Date@
setDocumentAmt (Env.ZERO);
setDocumentNo (null);
setIsSOTrx (false);	// N
setProcessed (false);
setSalesRep_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_InvoiceBatch (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=767 */
public static final int Table_ID=767;

/** TableName=C_InvoiceBatch */
public static final String Table_Name="C_InvoiceBatch";

protected static KeyNamePair Model = new KeyNamePair(767,"C_InvoiceBatch");

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
StringBuffer sb = new StringBuffer ("X_C_InvoiceBatch[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Currency Type.
@param C_ConversionType_ID Currency Conversion Rate Type */
public void setC_ConversionType_ID (int C_ConversionType_ID)
{
if (C_ConversionType_ID <= 0) set_Value ("C_ConversionType_ID", null);
 else 
set_Value ("C_ConversionType_ID", new Integer(C_ConversionType_ID));
}
/** Get Currency Type.
@return Currency Conversion Rate Type */
public int getC_ConversionType_ID() 
{
Integer ii = (Integer)get_Value("C_ConversionType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID < 1) throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
set_Value ("C_Currency_ID", new Integer(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Invoice Batch.
@param C_InvoiceBatch_ID Expense Invoice Batch Header */
public void setC_InvoiceBatch_ID (int C_InvoiceBatch_ID)
{
if (C_InvoiceBatch_ID < 1) throw new IllegalArgumentException ("C_InvoiceBatch_ID is mandatory.");
set_ValueNoCheck ("C_InvoiceBatch_ID", new Integer(C_InvoiceBatch_ID));
}
/** Get Invoice Batch.
@return Expense Invoice Batch Header */
public int getC_InvoiceBatch_ID() 
{
Integer ii = (Integer)get_Value("C_InvoiceBatch_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Control Amount.
@param ControlAmt If not zero, the Debit amount of the document must be equal this amount */
public void setControlAmt (BigDecimal ControlAmt)
{
if (ControlAmt == null) throw new IllegalArgumentException ("ControlAmt is mandatory.");
set_Value ("ControlAmt", ControlAmt);
}
/** Get Control Amount.
@return If not zero, the Debit amount of the document must be equal this amount */
public BigDecimal getControlAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("ControlAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Document Date.
@param DateDoc Date of the Document */
public void setDateDoc (Timestamp DateDoc)
{
if (DateDoc == null) throw new IllegalArgumentException ("DateDoc is mandatory.");
set_Value ("DateDoc", DateDoc);
}
/** Get Document Date.
@return Date of the Document */
public Timestamp getDateDoc() 
{
return (Timestamp)get_Value("DateDoc");
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
/** Set Document Amt.
@param DocumentAmt Document Amount */
public void setDocumentAmt (BigDecimal DocumentAmt)
{
if (DocumentAmt == null) throw new IllegalArgumentException ("DocumentAmt is mandatory.");
set_ValueNoCheck ("DocumentAmt", DocumentAmt);
}
/** Get Document Amt.
@return Document Amount */
public BigDecimal getDocumentAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("DocumentAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Document No.
@param DocumentNo Document sequence number of the document */
public void setDocumentNo (String DocumentNo)
{
if (DocumentNo == null) throw new IllegalArgumentException ("DocumentNo is mandatory.");
if (DocumentNo.length() > 30)
{
log.warning("Length > 30 - truncated");
DocumentNo = DocumentNo.substring(0,29);
}
set_Value ("DocumentNo", DocumentNo);
}
/** Get Document No.
@return Document sequence number of the document */
public String getDocumentNo() 
{
return (String)get_Value("DocumentNo");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getDocumentNo());
}
/** Set Sales Transaction.
@param IsSOTrx This is a Sales Transaction */
public void setIsSOTrx (boolean IsSOTrx)
{
set_Value ("IsSOTrx", new Boolean(IsSOTrx));
}
/** Get Sales Transaction.
@return This is a Sales Transaction */
public boolean isSOTrx() 
{
Object oo = get_Value("IsSOTrx");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
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
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", new Boolean(Processing));
}
/** Get Process Now.
@return Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}

/** SalesRep_ID AD_Reference_ID=190 */
public static final int SALESREP_ID_AD_Reference_ID=190;
/** Set Sales Representative.
@param SalesRep_ID Sales Representative or Company Agent */
public void setSalesRep_ID (int SalesRep_ID)
{
if (SalesRep_ID < 1) throw new IllegalArgumentException ("SalesRep_ID is mandatory.");
set_Value ("SalesRep_ID", new Integer(SalesRep_ID));
}
/** Get Sales Representative.
@return Sales Representative or Company Agent */
public int getSalesRep_ID() 
{
Integer ii = (Integer)get_Value("SalesRep_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
