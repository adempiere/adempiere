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
/** Generated Model for GL_Journal
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_GL_Journal extends PO
{
/** Standard Constructor
@param ctx context
@param GL_Journal_ID id
@param trxName transaction
*/
public X_GL_Journal (Properties ctx, int GL_Journal_ID, String trxName)
{
super (ctx, GL_Journal_ID, trxName);
/** if (GL_Journal_ID == 0)
{
setC_AcctSchema_ID (0);	// @$C_AcctSchema_ID@
setC_ConversionType_ID (0);
setC_Currency_ID (0);	// @C_Currency_ID@
setC_DocType_ID (0);	// @C_DocType_ID@
setC_Period_ID (0);	// @C_Period_ID@
setCurrencyRate (Env.ZERO);	// 1
setDateAcct (new Timestamp(System.currentTimeMillis()));	// @DateAcct@
setDateDoc (new Timestamp(System.currentTimeMillis()));	// @DateDoc@
setDescription (null);
setDocAction (null);	// CO
setDocStatus (null);	// DR
setDocumentNo (null);
setGL_Category_ID (0);	// @GL_Category_ID@
setGL_Journal_ID (0);
setIsApproved (true);	// Y
setIsPrinted (false);	// N
setPosted (false);	// N
setPostingType (null);	// @PostingType@
setTotalCr (Env.ZERO);	// 0
setTotalDr (Env.ZERO);	// 0
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_GL_Journal (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=224 */
public static final int Table_ID=224;

/** TableName=GL_Journal */
public static final String Table_Name="GL_Journal";

protected static KeyNamePair Model = new KeyNamePair(224,"GL_Journal");

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
StringBuffer sb = new StringBuffer ("X_GL_Journal[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Accounting Schema.
@param C_AcctSchema_ID Rules for accounting */
public void setC_AcctSchema_ID (int C_AcctSchema_ID)
{
if (C_AcctSchema_ID < 1) throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
set_ValueNoCheck ("C_AcctSchema_ID", new Integer(C_AcctSchema_ID));
}
/** Get Accounting Schema.
@return Rules for accounting */
public int getC_AcctSchema_ID() 
{
Integer ii = (Integer)get_Value("C_AcctSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Currency Type.
@param C_ConversionType_ID Currency Conversion Rate Type */
public void setC_ConversionType_ID (int C_ConversionType_ID)
{
if (C_ConversionType_ID < 1) throw new IllegalArgumentException ("C_ConversionType_ID is mandatory.");
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
/** Set Document Type.
@param C_DocType_ID Document type or rules */
public void setC_DocType_ID (int C_DocType_ID)
{
if (C_DocType_ID < 0) throw new IllegalArgumentException ("C_DocType_ID is mandatory.");
set_Value ("C_DocType_ID", new Integer(C_DocType_ID));
}
/** Get Document Type.
@return Document type or rules */
public int getC_DocType_ID() 
{
Integer ii = (Integer)get_Value("C_DocType_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** C_Period_ID AD_Reference_ID=275 */
public static final int C_PERIOD_ID_AD_Reference_ID=275;
/** Set Period.
@param C_Period_ID Period of the Calendar */
public void setC_Period_ID (int C_Period_ID)
{
if (C_Period_ID < 1) throw new IllegalArgumentException ("C_Period_ID is mandatory.");
set_Value ("C_Period_ID", new Integer(C_Period_ID));
}
/** Get Period.
@return Period of the Calendar */
public int getC_Period_ID() 
{
Integer ii = (Integer)get_Value("C_Period_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Control Amount.
@param ControlAmt If not zero, the Debit amount of the document must be equal this amount */
public void setControlAmt (BigDecimal ControlAmt)
{
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
/** Set Rate.
@param CurrencyRate Currency Conversion Rate */
public void setCurrencyRate (BigDecimal CurrencyRate)
{
if (CurrencyRate == null) throw new IllegalArgumentException ("CurrencyRate is mandatory.");
set_Value ("CurrencyRate", CurrencyRate);
}
/** Get Rate.
@return Currency Conversion Rate */
public BigDecimal getCurrencyRate() 
{
BigDecimal bd = (BigDecimal)get_Value("CurrencyRate");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Account Date.
@param DateAcct Accounting Date */
public void setDateAcct (Timestamp DateAcct)
{
if (DateAcct == null) throw new IllegalArgumentException ("DateAcct is mandatory.");
set_Value ("DateAcct", DateAcct);
}
/** Get Account Date.
@return Accounting Date */
public Timestamp getDateAcct() 
{
return (Timestamp)get_Value("DateAcct");
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
if (Description == null) throw new IllegalArgumentException ("Description is mandatory.");
if (Description.length() > 255)
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

/** DocAction AD_Reference_ID=135 */
public static final int DOCACTION_AD_Reference_ID=135;
/** <None> = -- */
public static final String DOCACTION_None = "--";
/** Approve = AP */
public static final String DOCACTION_Approve = "AP";
/** Close = CL */
public static final String DOCACTION_Close = "CL";
/** Complete = CO */
public static final String DOCACTION_Complete = "CO";
/** Invalidate = IN */
public static final String DOCACTION_Invalidate = "IN";
/** Post = PO */
public static final String DOCACTION_Post = "PO";
/** Prepare = PR */
public static final String DOCACTION_Prepare = "PR";
/** Reverse - Accrual = RA */
public static final String DOCACTION_Reverse_Accrual = "RA";
/** Reverse - Correct = RC */
public static final String DOCACTION_Reverse_Correct = "RC";
/** Re-activate = RE */
public static final String DOCACTION_Re_Activate = "RE";
/** Reject = RJ */
public static final String DOCACTION_Reject = "RJ";
/** Void = VO */
public static final String DOCACTION_Void = "VO";
/** Wait Complete = WC */
public static final String DOCACTION_WaitComplete = "WC";
/** Unlock = XL */
public static final String DOCACTION_Unlock = "XL";
/** Set Document Action.
@param DocAction The targeted status of the document */
public void setDocAction (String DocAction)
{
if (DocAction == null) throw new IllegalArgumentException ("DocAction is mandatory");
if (DocAction.equals("--") || DocAction.equals("AP") || DocAction.equals("CL") || DocAction.equals("CO") || DocAction.equals("IN") || DocAction.equals("PO") || DocAction.equals("PR") || DocAction.equals("RA") || DocAction.equals("RC") || DocAction.equals("RE") || DocAction.equals("RJ") || DocAction.equals("VO") || DocAction.equals("WC") || DocAction.equals("XL"));
 else throw new IllegalArgumentException ("DocAction Invalid value - " + DocAction + " - Reference_ID=135 - -- - AP - CL - CO - IN - PO - PR - RA - RC - RE - RJ - VO - WC - XL");
if (DocAction.length() > 2)
{
log.warning("Length > 2 - truncated");
DocAction = DocAction.substring(0,1);
}
set_Value ("DocAction", DocAction);
}
/** Get Document Action.
@return The targeted status of the document */
public String getDocAction() 
{
return (String)get_Value("DocAction");
}

/** DocStatus AD_Reference_ID=131 */
public static final int DOCSTATUS_AD_Reference_ID=131;
/** Unknown = ?? */
public static final String DOCSTATUS_Unknown = "??";
/** Approved = AP */
public static final String DOCSTATUS_Approved = "AP";
/** Closed = CL */
public static final String DOCSTATUS_Closed = "CL";
/** Completed = CO */
public static final String DOCSTATUS_Completed = "CO";
/** Drafted = DR */
public static final String DOCSTATUS_Drafted = "DR";
/** Invalid = IN */
public static final String DOCSTATUS_Invalid = "IN";
/** In Progress = IP */
public static final String DOCSTATUS_InProgress = "IP";
/** Not Approved = NA */
public static final String DOCSTATUS_NotApproved = "NA";
/** Reversed = RE */
public static final String DOCSTATUS_Reversed = "RE";
/** Voided = VO */
public static final String DOCSTATUS_Voided = "VO";
/** Waiting Confirmation = WC */
public static final String DOCSTATUS_WaitingConfirmation = "WC";
/** Waiting Payment = WP */
public static final String DOCSTATUS_WaitingPayment = "WP";
/** Set Document Status.
@param DocStatus The current status of the document */
public void setDocStatus (String DocStatus)
{
if (DocStatus == null) throw new IllegalArgumentException ("DocStatus is mandatory");
if (DocStatus.equals("??") || DocStatus.equals("AP") || DocStatus.equals("CL") || DocStatus.equals("CO") || DocStatus.equals("DR") || DocStatus.equals("IN") || DocStatus.equals("IP") || DocStatus.equals("NA") || DocStatus.equals("RE") || DocStatus.equals("VO") || DocStatus.equals("WC") || DocStatus.equals("WP"));
 else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=131 - ?? - AP - CL - CO - DR - IN - IP - NA - RE - VO - WC - WP");
if (DocStatus.length() > 2)
{
log.warning("Length > 2 - truncated");
DocStatus = DocStatus.substring(0,1);
}
set_Value ("DocStatus", DocStatus);
}
/** Get Document Status.
@return The current status of the document */
public String getDocStatus() 
{
return (String)get_Value("DocStatus");
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
set_ValueNoCheck ("DocumentNo", DocumentNo);
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
/** Set Budget.
@param GL_Budget_ID General Ledger Budget */
public void setGL_Budget_ID (int GL_Budget_ID)
{
if (GL_Budget_ID <= 0) set_Value ("GL_Budget_ID", null);
 else 
set_Value ("GL_Budget_ID", new Integer(GL_Budget_ID));
}
/** Get Budget.
@return General Ledger Budget */
public int getGL_Budget_ID() 
{
Integer ii = (Integer)get_Value("GL_Budget_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set GL Category.
@param GL_Category_ID General Ledger Category */
public void setGL_Category_ID (int GL_Category_ID)
{
if (GL_Category_ID < 1) throw new IllegalArgumentException ("GL_Category_ID is mandatory.");
set_Value ("GL_Category_ID", new Integer(GL_Category_ID));
}
/** Get GL Category.
@return General Ledger Category */
public int getGL_Category_ID() 
{
Integer ii = (Integer)get_Value("GL_Category_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Journal Batch.
@param GL_JournalBatch_ID General Ledger Journal Batch */
public void setGL_JournalBatch_ID (int GL_JournalBatch_ID)
{
if (GL_JournalBatch_ID <= 0) set_ValueNoCheck ("GL_JournalBatch_ID", null);
 else 
set_ValueNoCheck ("GL_JournalBatch_ID", new Integer(GL_JournalBatch_ID));
}
/** Get Journal Batch.
@return General Ledger Journal Batch */
public int getGL_JournalBatch_ID() 
{
Integer ii = (Integer)get_Value("GL_JournalBatch_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Journal.
@param GL_Journal_ID General Ledger Journal */
public void setGL_Journal_ID (int GL_Journal_ID)
{
if (GL_Journal_ID < 1) throw new IllegalArgumentException ("GL_Journal_ID is mandatory.");
set_ValueNoCheck ("GL_Journal_ID", new Integer(GL_Journal_ID));
}
/** Get Journal.
@return General Ledger Journal */
public int getGL_Journal_ID() 
{
Integer ii = (Integer)get_Value("GL_Journal_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Approved.
@param IsApproved Indicates if this document requires approval */
public void setIsApproved (boolean IsApproved)
{
set_ValueNoCheck ("IsApproved", new Boolean(IsApproved));
}
/** Get Approved.
@return Indicates if this document requires approval */
public boolean isApproved() 
{
Object oo = get_Value("IsApproved");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Printed.
@param IsPrinted Indicates if this document / line is printed */
public void setIsPrinted (boolean IsPrinted)
{
set_ValueNoCheck ("IsPrinted", new Boolean(IsPrinted));
}
/** Get Printed.
@return Indicates if this document / line is printed */
public boolean isPrinted() 
{
Object oo = get_Value("IsPrinted");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Posted.
@param Posted Posting status */
public void setPosted (boolean Posted)
{
set_ValueNoCheck ("Posted", new Boolean(Posted));
}
/** Get Posted.
@return Posting status */
public boolean isPosted() 
{
Object oo = get_Value("Posted");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}

/** PostingType AD_Reference_ID=125 */
public static final int POSTINGTYPE_AD_Reference_ID=125;
/** Actual = A */
public static final String POSTINGTYPE_Actual = "A";
/** Budget = B */
public static final String POSTINGTYPE_Budget = "B";
/** Commitment = E */
public static final String POSTINGTYPE_Commitment = "E";
/** Reservation = R */
public static final String POSTINGTYPE_Reservation = "R";
/** Statistical = S */
public static final String POSTINGTYPE_Statistical = "S";
/** Set PostingType.
@param PostingType The type of posted amount for the transaction */
public void setPostingType (String PostingType)
{
if (PostingType == null) throw new IllegalArgumentException ("PostingType is mandatory");
if (PostingType.equals("A") || PostingType.equals("B") || PostingType.equals("E") || PostingType.equals("R") || PostingType.equals("S"));
 else throw new IllegalArgumentException ("PostingType Invalid value - " + PostingType + " - Reference_ID=125 - A - B - E - R - S");
if (PostingType.length() > 1)
{
log.warning("Length > 1 - truncated");
PostingType = PostingType.substring(0,0);
}
set_Value ("PostingType", PostingType);
}
/** Get PostingType.
@return The type of posted amount for the transaction */
public String getPostingType() 
{
return (String)get_Value("PostingType");
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
/** Set Total Credit.
@param TotalCr Total Credit in document currency */
public void setTotalCr (BigDecimal TotalCr)
{
if (TotalCr == null) throw new IllegalArgumentException ("TotalCr is mandatory.");
set_ValueNoCheck ("TotalCr", TotalCr);
}
/** Get Total Credit.
@return Total Credit in document currency */
public BigDecimal getTotalCr() 
{
BigDecimal bd = (BigDecimal)get_Value("TotalCr");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Total Debit.
@param TotalDr Total debit in document currency */
public void setTotalDr (BigDecimal TotalDr)
{
if (TotalDr == null) throw new IllegalArgumentException ("TotalDr is mandatory.");
set_ValueNoCheck ("TotalDr", TotalDr);
}
/** Get Total Debit.
@return Total debit in document currency */
public BigDecimal getTotalDr() 
{
BigDecimal bd = (BigDecimal)get_Value("TotalDr");
if (bd == null) return Env.ZERO;
return bd;
}
}
