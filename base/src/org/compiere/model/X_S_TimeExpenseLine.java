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
/** Generated Model for S_TimeExpenseLine
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_S_TimeExpenseLine extends PO
{
/** Standard Constructor
@param ctx context
@param S_TimeExpenseLine_ID id
@param trxName transaction
*/
public X_S_TimeExpenseLine (Properties ctx, int S_TimeExpenseLine_ID, String trxName)
{
super (ctx, S_TimeExpenseLine_ID, trxName);
/** if (S_TimeExpenseLine_ID == 0)
{
setDateExpense (new Timestamp(System.currentTimeMillis()));	// @DateExpense@;
@DateReport@
setIsInvoiced (false);
setIsTimeReport (false);
setLine (0);	// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM S_TimeExpenseLine WHERE S_TimeExpense_ID=@S_TimeExpense_ID@
setProcessed (false);
setS_TimeExpenseLine_ID (0);
setS_TimeExpense_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_S_TimeExpenseLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=S_TimeExpenseLine */
public static final String Table_Name="S_TimeExpenseLine";

/** AD_Table_ID=488 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

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
StringBuffer sb = new StringBuffer ("X_S_TimeExpenseLine[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Activity.
@param C_Activity_ID Business Activity */
public void setC_Activity_ID (int C_Activity_ID)
{
if (C_Activity_ID <= 0) set_Value ("C_Activity_ID", null);
 else 
set_Value ("C_Activity_ID", Integer.valueOf(C_Activity_ID));
}
/** Get Activity.
@return Business Activity */
public int getC_Activity_ID() 
{
Integer ii = (Integer)get_Value("C_Activity_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Activity_ID */
public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_Value ("C_BPartner_ID", null);
 else 
set_Value ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_ID */
public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";
/** Set Campaign.
@param C_Campaign_ID Marketing Campaign */
public void setC_Campaign_ID (int C_Campaign_ID)
{
if (C_Campaign_ID <= 0) set_Value ("C_Campaign_ID", null);
 else 
set_Value ("C_Campaign_ID", Integer.valueOf(C_Campaign_ID));
}
/** Get Campaign.
@return Marketing Campaign */
public int getC_Campaign_ID() 
{
Integer ii = (Integer)get_Value("C_Campaign_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Campaign_ID */
public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID <= 0) set_Value ("C_Currency_ID", null);
 else 
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
/** Column name C_Currency_ID */
public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";
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
/** Column name C_InvoiceLine_ID */
public static final String COLUMNNAME_C_InvoiceLine_ID = "C_InvoiceLine_ID";
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
/** Column name C_OrderLine_ID */
public static final String COLUMNNAME_C_OrderLine_ID = "C_OrderLine_ID";
/** Set Project Phase.
@param C_ProjectPhase_ID Phase of a Project */
public void setC_ProjectPhase_ID (int C_ProjectPhase_ID)
{
if (C_ProjectPhase_ID <= 0) set_Value ("C_ProjectPhase_ID", null);
 else 
set_Value ("C_ProjectPhase_ID", Integer.valueOf(C_ProjectPhase_ID));
}
/** Get Project Phase.
@return Phase of a Project */
public int getC_ProjectPhase_ID() 
{
Integer ii = (Integer)get_Value("C_ProjectPhase_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ProjectPhase_ID */
public static final String COLUMNNAME_C_ProjectPhase_ID = "C_ProjectPhase_ID";
/** Set Project Task.
@param C_ProjectTask_ID Actual Project Task in a Phase */
public void setC_ProjectTask_ID (int C_ProjectTask_ID)
{
if (C_ProjectTask_ID <= 0) set_Value ("C_ProjectTask_ID", null);
 else 
set_Value ("C_ProjectTask_ID", Integer.valueOf(C_ProjectTask_ID));
}
/** Get Project Task.
@return Actual Project Task in a Phase */
public int getC_ProjectTask_ID() 
{
Integer ii = (Integer)get_Value("C_ProjectTask_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_ProjectTask_ID */
public static final String COLUMNNAME_C_ProjectTask_ID = "C_ProjectTask_ID";
/** Set Project.
@param C_Project_ID Financial Project */
public void setC_Project_ID (int C_Project_ID)
{
if (C_Project_ID <= 0) set_Value ("C_Project_ID", null);
 else 
set_Value ("C_Project_ID", Integer.valueOf(C_Project_ID));
}
/** Get Project.
@return Financial Project */
public int getC_Project_ID() 
{
Integer ii = (Integer)get_Value("C_Project_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Project_ID */
public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";
/** Set UOM.
@param C_UOM_ID Unit of Measure */
public void setC_UOM_ID (int C_UOM_ID)
{
if (C_UOM_ID <= 0) set_Value ("C_UOM_ID", null);
 else 
set_Value ("C_UOM_ID", Integer.valueOf(C_UOM_ID));
}
/** Get UOM.
@return Unit of Measure */
public int getC_UOM_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_UOM_ID */
public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";
/** Set Converted Amount.
@param ConvertedAmt Converted Amount */
public void setConvertedAmt (BigDecimal ConvertedAmt)
{
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
/** Column name ConvertedAmt */
public static final String COLUMNNAME_ConvertedAmt = "ConvertedAmt";
/** Set Expense Date.
@param DateExpense Date of expense */
public void setDateExpense (Timestamp DateExpense)
{
if (DateExpense == null) throw new IllegalArgumentException ("DateExpense is mandatory.");
set_Value ("DateExpense", DateExpense);
}
/** Get Expense Date.
@return Date of expense */
public Timestamp getDateExpense() 
{
return (Timestamp)get_Value("DateExpense");
}
/** Column name DateExpense */
public static final String COLUMNNAME_DateExpense = "DateExpense";
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
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set Expense Amount.
@param ExpenseAmt Amount for this expense */
public void setExpenseAmt (BigDecimal ExpenseAmt)
{
set_Value ("ExpenseAmt", ExpenseAmt);
}
/** Get Expense Amount.
@return Amount for this expense */
public BigDecimal getExpenseAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("ExpenseAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name ExpenseAmt */
public static final String COLUMNNAME_ExpenseAmt = "ExpenseAmt";
/** Set Invoice Price.
@param InvoicePrice Unit price to be invoiced or 0 for default price */
public void setInvoicePrice (BigDecimal InvoicePrice)
{
set_Value ("InvoicePrice", InvoicePrice);
}
/** Get Invoice Price.
@return Unit price to be invoiced or 0 for default price */
public BigDecimal getInvoicePrice() 
{
BigDecimal bd = (BigDecimal)get_Value("InvoicePrice");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name InvoicePrice */
public static final String COLUMNNAME_InvoicePrice = "InvoicePrice";
/** Set Invoiced.
@param IsInvoiced Is this invoiced? */
public void setIsInvoiced (boolean IsInvoiced)
{
set_Value ("IsInvoiced", Boolean.valueOf(IsInvoiced));
}
/** Get Invoiced.
@return Is this invoiced? */
public boolean isInvoiced() 
{
Object oo = get_Value("IsInvoiced");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsInvoiced */
public static final String COLUMNNAME_IsInvoiced = "IsInvoiced";
/** Set Time Report.
@param IsTimeReport Line is a time report only (no expense) */
public void setIsTimeReport (boolean IsTimeReport)
{
set_Value ("IsTimeReport", Boolean.valueOf(IsTimeReport));
}
/** Get Time Report.
@return Line is a time report only (no expense) */
public boolean isTimeReport() 
{
Object oo = get_Value("IsTimeReport");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsTimeReport */
public static final String COLUMNNAME_IsTimeReport = "IsTimeReport";
/** Set Line No.
@param Line Unique line for this document */
public void setLine (int Line)
{
set_Value ("Line", Integer.valueOf(Line));
}
/** Get Line No.
@return Unique line for this document */
public int getLine() 
{
Integer ii = (Integer)get_Value("Line");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getLine()));
}
/** Column name Line */
public static final String COLUMNNAME_Line = "Line";
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_Value ("M_Product_ID", null);
 else 
set_Value ("M_Product_ID", Integer.valueOf(M_Product_ID));
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
/** Set Note.
@param Note Optional additional user defined information */
public void setNote (String Note)
{
if (Note != null && Note.length() > 255)
{
log.warning("Length > 255 - truncated");
Note = Note.substring(0,254);
}
set_Value ("Note", Note);
}
/** Get Note.
@return Optional additional user defined information */
public String getNote() 
{
return (String)get_Value("Note");
}
/** Column name Note */
public static final String COLUMNNAME_Note = "Note";
/** Set Price Invoiced.
@param PriceInvoiced The priced invoiced to the customer (in the currency of the customer's AR price list) - 0 for default price */
public void setPriceInvoiced (BigDecimal PriceInvoiced)
{
set_Value ("PriceInvoiced", PriceInvoiced);
}
/** Get Price Invoiced.
@return The priced invoiced to the customer (in the currency of the customer's AR price list) - 0 for default price */
public BigDecimal getPriceInvoiced() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceInvoiced");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PriceInvoiced */
public static final String COLUMNNAME_PriceInvoiced = "PriceInvoiced";
/** Set Price Reimbursed.
@param PriceReimbursed The reimbursed price (in currency of the employee's AP price list) */
public void setPriceReimbursed (BigDecimal PriceReimbursed)
{
set_Value ("PriceReimbursed", PriceReimbursed);
}
/** Get Price Reimbursed.
@return The reimbursed price (in currency of the employee's AP price list) */
public BigDecimal getPriceReimbursed() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceReimbursed");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name PriceReimbursed */
public static final String COLUMNNAME_PriceReimbursed = "PriceReimbursed";
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", Boolean.valueOf(Processed));
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
/** Column name Processed */
public static final String COLUMNNAME_Processed = "Processed";
/** Set Quantity.
@param Qty Quantity */
public void setQty (BigDecimal Qty)
{
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
/** Column name Qty */
public static final String COLUMNNAME_Qty = "Qty";
/** Set Quantity Invoiced.
@param QtyInvoiced Invoiced Quantity */
public void setQtyInvoiced (BigDecimal QtyInvoiced)
{
set_Value ("QtyInvoiced", QtyInvoiced);
}
/** Get Quantity Invoiced.
@return Invoiced Quantity */
public BigDecimal getQtyInvoiced() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyInvoiced");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtyInvoiced */
public static final String COLUMNNAME_QtyInvoiced = "QtyInvoiced";
/** Set Quantity Reimbursed.
@param QtyReimbursed The reimbursed quantity */
public void setQtyReimbursed (BigDecimal QtyReimbursed)
{
set_Value ("QtyReimbursed", QtyReimbursed);
}
/** Get Quantity Reimbursed.
@return The reimbursed quantity */
public BigDecimal getQtyReimbursed() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyReimbursed");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name QtyReimbursed */
public static final String COLUMNNAME_QtyReimbursed = "QtyReimbursed";
/** Set Resource Assignment.
@param S_ResourceAssignment_ID Resource Assignment */
public void setS_ResourceAssignment_ID (int S_ResourceAssignment_ID)
{
if (S_ResourceAssignment_ID <= 0) set_Value ("S_ResourceAssignment_ID", null);
 else 
set_Value ("S_ResourceAssignment_ID", Integer.valueOf(S_ResourceAssignment_ID));
}
/** Get Resource Assignment.
@return Resource Assignment */
public int getS_ResourceAssignment_ID() 
{
Integer ii = (Integer)get_Value("S_ResourceAssignment_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name S_ResourceAssignment_ID */
public static final String COLUMNNAME_S_ResourceAssignment_ID = "S_ResourceAssignment_ID";
/** Set Expense Line.
@param S_TimeExpenseLine_ID Time and Expense Report Line */
public void setS_TimeExpenseLine_ID (int S_TimeExpenseLine_ID)
{
if (S_TimeExpenseLine_ID < 1) throw new IllegalArgumentException ("S_TimeExpenseLine_ID is mandatory.");
set_ValueNoCheck ("S_TimeExpenseLine_ID", Integer.valueOf(S_TimeExpenseLine_ID));
}
/** Get Expense Line.
@return Time and Expense Report Line */
public int getS_TimeExpenseLine_ID() 
{
Integer ii = (Integer)get_Value("S_TimeExpenseLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name S_TimeExpenseLine_ID */
public static final String COLUMNNAME_S_TimeExpenseLine_ID = "S_TimeExpenseLine_ID";
/** Set Expense Report.
@param S_TimeExpense_ID Time and Expense Report */
public void setS_TimeExpense_ID (int S_TimeExpense_ID)
{
if (S_TimeExpense_ID < 1) throw new IllegalArgumentException ("S_TimeExpense_ID is mandatory.");
set_ValueNoCheck ("S_TimeExpense_ID", Integer.valueOf(S_TimeExpense_ID));
}
/** Get Expense Report.
@return Time and Expense Report */
public int getS_TimeExpense_ID() 
{
Integer ii = (Integer)get_Value("S_TimeExpense_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name S_TimeExpense_ID */
public static final String COLUMNNAME_S_TimeExpense_ID = "S_TimeExpense_ID";
/** Set Time Type.
@param S_TimeType_ID Type of time recorded */
public void setS_TimeType_ID (int S_TimeType_ID)
{
if (S_TimeType_ID <= 0) set_Value ("S_TimeType_ID", null);
 else 
set_Value ("S_TimeType_ID", Integer.valueOf(S_TimeType_ID));
}
/** Get Time Type.
@return Type of time recorded */
public int getS_TimeType_ID() 
{
Integer ii = (Integer)get_Value("S_TimeType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name S_TimeType_ID */
public static final String COLUMNNAME_S_TimeType_ID = "S_TimeType_ID";
}
