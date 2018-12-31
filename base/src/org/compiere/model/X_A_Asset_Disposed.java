/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for A_Asset_Disposed
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_A_Asset_Disposed extends PO implements I_A_Asset_Disposed, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_A_Asset_Disposed (Properties ctx, int A_Asset_Disposed_ID, String trxName)
    {
      super (ctx, A_Asset_Disposed_ID, trxName);
      /** if (A_Asset_Disposed_ID == 0)
        {
			setA_Accumulated_Depr (Env.ZERO);
// 0
			setA_Accumulated_Depr_Delta (Env.ZERO);
// 0
			setA_Activation_Method (null);
// 'AA'
			setA_Asset_Disposed_ID (0);
			setA_Asset_ID (0);
			setA_Disposal_Amt (Env.ZERO);
// 0
			setA_Disposed_Date (new Timestamp( System.currentTimeMillis() ));
			setA_Disposed_Method (null);
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
// @Date@
			setDateDoc (new Timestamp( System.currentTimeMillis() ));
// @Date@
			setDocumentNo (null);
			setExpense (Env.ZERO);
// 0
			setIsApproved (false);
// N
			setPosted (false);
// N
			setPostingType (null);
// A
			setProcessed (false);
			setProcessing (false);
        } */
    }

    /** Load Constructor */
    public X_A_Asset_Disposed (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_A_Asset_Disposed[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Accumulated Depreciation.
		@param A_Accumulated_Depr Accumulated Depreciation	  */
	public void setA_Accumulated_Depr (BigDecimal A_Accumulated_Depr)
	{
		set_Value (COLUMNNAME_A_Accumulated_Depr, A_Accumulated_Depr);
	}

	/** Get Accumulated Depreciation.
		@return Accumulated Depreciation	  */
	public BigDecimal getA_Accumulated_Depr () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Accumulated_Depr);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Accumulated Depreciation (delta).
		@param A_Accumulated_Depr_Delta Accumulated Depreciation (delta)	  */
	public void setA_Accumulated_Depr_Delta (BigDecimal A_Accumulated_Depr_Delta)
	{
		set_Value (COLUMNNAME_A_Accumulated_Depr_Delta, A_Accumulated_Depr_Delta);
	}

	/** Get Accumulated Depreciation (delta).
		@return Accumulated Depreciation (delta)	  */
	public BigDecimal getA_Accumulated_Depr_Delta () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Accumulated_Depr_Delta);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** A_Activation_Method AD_Reference_ID=53364 */
	public static final int A_ACTIVATION_METHOD_AD_Reference_ID=53364;
	/** Activation = AA */
	public static final String A_ACTIVATION_METHOD_Activation = "AA";
	/** Set Activation Method.
		@param A_Activation_Method Activation Method	  */
	public void setA_Activation_Method (String A_Activation_Method)
	{

		set_Value (COLUMNNAME_A_Activation_Method, A_Activation_Method);
	}

	/** Get Activation Method.
		@return Activation Method	  */
	public String getA_Activation_Method () 
	{
		return (String)get_Value(COLUMNNAME_A_Activation_Method);
	}

	/** Set Fixed Asset Cost.
		@param A_Asset_Cost 
		Cost of acquisition of the Fixed Asset
	  */
	public void setA_Asset_Cost (BigDecimal A_Asset_Cost)
	{
		set_Value (COLUMNNAME_A_Asset_Cost, A_Asset_Cost);
	}

	/** Get Fixed Asset Cost.
		@return Cost of acquisition of the Fixed Asset
	  */
	public BigDecimal getA_Asset_Cost () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Asset_Cost);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Asset Disposed.
		@param A_Asset_Disposed_ID Asset Disposed	  */
	public void setA_Asset_Disposed_ID (int A_Asset_Disposed_ID)
	{
		if (A_Asset_Disposed_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Disposed_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_A_Asset_Disposed_ID, Integer.valueOf(A_Asset_Disposed_ID));
	}

	/** Get Asset Disposed.
		@return Asset Disposed	  */
	public int getA_Asset_Disposed_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Disposed_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getA_Asset_Disposed_ID()));
    }

	public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException
    {
		return (org.compiere.model.I_A_Asset)MTable.get(getCtx(), org.compiere.model.I_A_Asset.Table_Name)
			.getPO(getA_Asset_ID(), get_TrxName());	}

	/** Set Fixed Asset.
		@param A_Asset_ID 
		Fixed Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1) 
			set_Value (COLUMNNAME_A_Asset_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
	}

	/** Get Fixed Asset.
		@return Fixed Asset used internally or by customers
	  */
	public int getA_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** A_Asset_Status AD_Reference_ID=53359 */
	public static final int A_ASSET_STATUS_AD_Reference_ID=53359;
	/** Activated = AC */
	public static final String A_ASSET_STATUS_Activated = "AC";
	/** Disposed = DI */
	public static final String A_ASSET_STATUS_Disposed = "DI";
	/** Depreciated = DP */
	public static final String A_ASSET_STATUS_Depreciated = "DP";
	/** New = NW */
	public static final String A_ASSET_STATUS_New = "NW";
	/** Preservation = PR */
	public static final String A_ASSET_STATUS_Preservation = "PR";
	/** Retired = RE */
	public static final String A_ASSET_STATUS_Retired = "RE";
	/** Sold = SO */
	public static final String A_ASSET_STATUS_Sold = "SO";
	/** Set Asset Status.
		@param A_Asset_Status Asset Status	  */
	public void setA_Asset_Status (String A_Asset_Status)
	{

		set_Value (COLUMNNAME_A_Asset_Status, A_Asset_Status);
	}

	/** Get Asset Status.
		@return Asset Status	  */
	public String getA_Asset_Status () 
	{
		return (String)get_Value(COLUMNNAME_A_Asset_Status);
	}

	public org.compiere.model.I_A_Asset getA_Asset_Trade() throws RuntimeException
    {
		return (org.compiere.model.I_A_Asset)MTable.get(getCtx(), org.compiere.model.I_A_Asset.Table_Name)
			.getPO(getA_Asset_Trade_ID(), get_TrxName());	}

	/** Set Asset Trade.
		@param A_Asset_Trade_ID Asset Trade	  */
	public void setA_Asset_Trade_ID (int A_Asset_Trade_ID)
	{
		if (A_Asset_Trade_ID < 1) 
			set_Value (COLUMNNAME_A_Asset_Trade_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_Trade_ID, Integer.valueOf(A_Asset_Trade_ID));
	}

	/** Get Asset Trade.
		@return Asset Trade	  */
	public int getA_Asset_Trade_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Trade_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Disposal Amount.
		@param A_Disposal_Amt Disposal Amount	  */
	public void setA_Disposal_Amt (BigDecimal A_Disposal_Amt)
	{
		set_Value (COLUMNNAME_A_Disposal_Amt, A_Disposal_Amt);
	}

	/** Get Disposal Amount.
		@return Disposal Amount	  */
	public BigDecimal getA_Disposal_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Disposal_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Disposed Date.
		@param A_Disposed_Date Disposed Date	  */
	public void setA_Disposed_Date (Timestamp A_Disposed_Date)
	{
		set_Value (COLUMNNAME_A_Disposed_Date, A_Disposed_Date);
	}

	/** Get Disposed Date.
		@return Disposed Date	  */
	public Timestamp getA_Disposed_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_A_Disposed_Date);
	}

	/** A_Disposed_Method AD_Reference_ID=53270 */
	public static final int A_DISPOSED_METHOD_AD_Reference_ID=53270;
	/** Cash = C */
	public static final String A_DISPOSED_METHOD_Cash = "C";
	/** Simple = S */
	public static final String A_DISPOSED_METHOD_Simple = "S";
	/** Trade = T1 */
	public static final String A_DISPOSED_METHOD_Trade = "T1";
	/** Trade w/cash = T2 */
	public static final String A_DISPOSED_METHOD_TradeWCash = "T2";
	/** Cash_ = C_ */
	public static final String A_DISPOSED_METHOD_Cash_ = "C_";
	/** Partial Retirement = PD */
	public static final String A_DISPOSED_METHOD_PartialRetirement = "PD";
	/** Preservation = PR */
	public static final String A_DISPOSED_METHOD_Preservation = "PR";
	/** Simple_ = S_ */
	public static final String A_DISPOSED_METHOD_Simple_ = "S_";
	/** Set Disposed Method.
		@param A_Disposed_Method Disposed Method	  */
	public void setA_Disposed_Method (String A_Disposed_Method)
	{

		set_Value (COLUMNNAME_A_Disposed_Method, A_Disposed_Method);
	}

	/** Get Disposed Method.
		@return Disposed Method	  */
	public String getA_Disposed_Method () 
	{
		return (String)get_Value(COLUMNNAME_A_Disposed_Method);
	}

	/** A_Disposed_Reason AD_Reference_ID=53269 */
	public static final int A_DISPOSED_REASON_AD_Reference_ID=53269;
	/** Charity = C */
	public static final String A_DISPOSED_REASON_Charity = "C";
	/** Destroyed = D */
	public static final String A_DISPOSED_REASON_Destroyed = "D";
	/** Scraped = S */
	public static final String A_DISPOSED_REASON_Scraped = "S";
	/** Sold = S1 */
	public static final String A_DISPOSED_REASON_Sold = "S1";
	/** Sold w/Trade = S2 */
	public static final String A_DISPOSED_REASON_SoldWTrade = "S2";
	/** Theft = T */
	public static final String A_DISPOSED_REASON_Theft = "T";
	/** Set Disposed Reason.
		@param A_Disposed_Reason Disposed Reason	  */
	public void setA_Disposed_Reason (String A_Disposed_Reason)
	{

		set_Value (COLUMNNAME_A_Disposed_Reason, A_Disposed_Reason);
	}

	/** Get Disposed Reason.
		@return Disposed Reason	  */
	public String getA_Disposed_Reason () 
	{
		return (String)get_Value(COLUMNNAME_A_Disposed_Reason);
	}

	/** Set Asset Proceeds.
		@param A_Proceeds Asset Proceeds	  */
	public void setA_Proceeds (BigDecimal A_Proceeds)
	{
		set_Value (COLUMNNAME_A_Proceeds, A_Proceeds);
	}

	/** Get Asset Proceeds.
		@return Asset Proceeds	  */
	public BigDecimal getA_Proceeds () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Proceeds);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException
    {
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_Name)
			.getPO(getC_DocType_ID(), get_TrxName());	}

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0) 
			set_Value (COLUMNNAME_C_DocType_ID, null);
		else 
			set_Value (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_InvoiceLine getC_InvoiceLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_InvoiceLine)MTable.get(getCtx(), org.compiere.model.I_C_InvoiceLine.Table_Name)
			.getPO(getC_InvoiceLine_ID(), get_TrxName());	}

	/** Set Invoice Line.
		@param C_InvoiceLine_ID 
		Invoice Detail Line
	  */
	public void setC_InvoiceLine_ID (int C_InvoiceLine_ID)
	{
		if (C_InvoiceLine_ID < 1) 
			set_Value (COLUMNNAME_C_InvoiceLine_ID, null);
		else 
			set_Value (COLUMNNAME_C_InvoiceLine_ID, Integer.valueOf(C_InvoiceLine_ID));
	}

	/** Get Invoice Line.
		@return Invoice Detail Line
	  */
	public int getC_InvoiceLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_InvoiceLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getC_Invoice_ID(), get_TrxName());	}

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Period getC_Period() throws RuntimeException
    {
		return (org.compiere.model.I_C_Period)MTable.get(getCtx(), org.compiere.model.I_C_Period.Table_Name)
			.getPO(getC_Period_ID(), get_TrxName());	}

	/** Set Period.
		@param C_Period_ID 
		Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID)
	{
		if (C_Period_ID < 1) 
			set_Value (COLUMNNAME_C_Period_ID, null);
		else 
			set_Value (COLUMNNAME_C_Period_ID, Integer.valueOf(C_Period_ID));
	}

	/** Get Period.
		@return Period of the Calendar
	  */
	public int getC_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Account Date.
		@param DateAcct 
		Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct)
	{
		set_Value (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
	}

	/** Set Document Date.
		@param DateDoc 
		Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc)
	{
		set_Value (COLUMNNAME_DateDoc, DateDoc);
	}

	/** Get Document Date.
		@return Date of the Document
	  */
	public Timestamp getDateDoc () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDoc);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** <None> = -- */
	public static final String DOCACTION_None = "--";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{

		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction () 
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus () 
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Expense.
		@param Expense Expense	  */
	public void setExpense (BigDecimal Expense)
	{
		set_Value (COLUMNNAME_Expense, Expense);
	}

	/** Get Expense.
		@return Expense	  */
	public BigDecimal getExpense () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Expense);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_Value (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Disposed.
		@param IsDisposed 
		The asset is disposed
	  */
	public void setIsDisposed (boolean IsDisposed)
	{
		set_Value (COLUMNNAME_IsDisposed, Boolean.valueOf(IsDisposed));
	}

	/** Get Disposed.
		@return The asset is disposed
	  */
	public boolean isDisposed () 
	{
		Object oo = get_Value(COLUMNNAME_IsDisposed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Posted.
		@param Posted 
		Posting status
	  */
	public void setPosted (boolean Posted)
	{
		set_Value (COLUMNNAME_Posted, Boolean.valueOf(Posted));
	}

	/** Get Posted.
		@return Posting status
	  */
	public boolean isPosted () 
	{
		Object oo = get_Value(COLUMNNAME_Posted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
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
	/** Statistical = S */
	public static final String POSTINGTYPE_Statistical = "S";
	/** Reservation = R */
	public static final String POSTINGTYPE_Reservation = "R";
	/** Set Posting Type.
		@param PostingType 
		The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType)
	{

		set_Value (COLUMNNAME_PostingType, PostingType);
	}

	/** Get Posting Type.
		@return The type of posted amount for the transaction
	  */
	public String getPostingType () 
	{
		return (String)get_Value(COLUMNNAME_PostingType);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Processed On.
		@param ProcessedOn 
		The date+time (expressed in decimal format) when the document has been processed
	  */
	public void setProcessedOn (BigDecimal ProcessedOn)
	{
		set_Value (COLUMNNAME_ProcessedOn, ProcessedOn);
	}

	/** Get Processed On.
		@return The date+time (expressed in decimal format) when the document has been processed
	  */
	public BigDecimal getProcessedOn () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ProcessedOn);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}
}