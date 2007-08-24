/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import org.compiere.util.*;

/** Generated Model for GL_JournalBatch
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_GL_JournalBatch extends PO implements I_GL_JournalBatch, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_GL_JournalBatch (Properties ctx, int GL_JournalBatch_ID, String trxName)
    {
      super (ctx, GL_JournalBatch_ID, trxName);
      /** if (GL_JournalBatch_ID == 0)        {			setC_DocType_ID (0);
			setDescription (null);
			setDocAction (null);
// CO
			setDocStatus (null);
// DR
			setDocumentNo (null);
			setGL_JournalBatch_ID (0);
			setPostingType (null);
// A
			setProcessed (false);
			setProcessing (false);
			setTotalCr (Env.ZERO);
			setTotalDr (Env.ZERO);
} */
    }

    /** Load Constructor */
    public X_GL_JournalBatch (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_GL_JournalBatch[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Currency getI_C_Currency() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_Currency.Table_Name);
        I_C_Currency result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Currency)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Currency_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID <= 0) 		set_Value (COLUMNNAME_C_Currency_ID, null);
 else 
		set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_DocType getI_C_DocType() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_DocType.Table_Name);
        I_C_DocType result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_DocType)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_DocType_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Document Type.
		@param C_DocType_ID 
		Document type or rules
	  */
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0)
			 throw new IllegalArgumentException ("C_DocType_ID is mandatory.");
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

/** C_Period_ID AD_Reference_ID=275 */
public static final int C_PERIOD_ID_AD_Reference_ID=275;
	/** Set Period.
		@param C_Period_ID 
		Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID)
	{
		if (C_Period_ID <= 0) 		set_Value (COLUMNNAME_C_Period_ID, null);
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

	/** Set Control Amount.
		@param ControlAmt 
		If not zero, the Debit amount of the document must be equal this amount
	  */
	public void setControlAmt (BigDecimal ControlAmt)
	{
		set_Value (COLUMNNAME_ControlAmt, ControlAmt);
	}

	/** Get Control Amount.
		@return If not zero, the Debit amount of the document must be equal this amount
	  */
	public BigDecimal getControlAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ControlAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Copy From.
		@param CopyFrom 
		Copy From Record
	  */
	public void setCopyFrom (String CopyFrom)
	{
		if (CopyFrom != null && CopyFrom.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			CopyFrom = CopyFrom.substring(0, 0);
		}
		set_Value (COLUMNNAME_CopyFrom, CopyFrom);
	}

	/** Get Copy From.
		@return Copy From Record
	  */
	public String getCopyFrom () 
	{
		return (String)get_Value(COLUMNNAME_CopyFrom);
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
		if (Description == null)
			throw new IllegalArgumentException ("Description is mandatory.");
		if (Description.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			Description = Description.substring(0, 254);
		}
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
public static final int DOCACTION_AD_Reference_ID=135;/** <None> = -- */
public static final String DOCACTION_None = "--";/** Approve = AP */
public static final String DOCACTION_Approve = "AP";/** Close = CL */
public static final String DOCACTION_Close = "CL";/** Complete = CO */
public static final String DOCACTION_Complete = "CO";/** Invalidate = IN */
public static final String DOCACTION_Invalidate = "IN";/** Post = PO */
public static final String DOCACTION_Post = "PO";/** Prepare = PR */
public static final String DOCACTION_Prepare = "PR";/** Reverse - Accrual = RA */
public static final String DOCACTION_Reverse_Accrual = "RA";/** Reverse - Correct = RC */
public static final String DOCACTION_Reverse_Correct = "RC";/** Re-activate = RE */
public static final String DOCACTION_Re_Activate = "RE";/** Reject = RJ */
public static final String DOCACTION_Reject = "RJ";/** Void = VO */
public static final String DOCACTION_Void = "VO";/** Wait Complete = WC */
public static final String DOCACTION_WaitComplete = "WC";/** Unlock = XL */
public static final String DOCACTION_Unlock = "XL";
	/** Set Document Action.
		@param DocAction 
		The targeted status of the document
	  */
	public void setDocAction (String DocAction)
	{
if (DocAction == null) throw new IllegalArgumentException ("DocAction is mandatory");if (DocAction.equals("--") || DocAction.equals("AP") || DocAction.equals("CL") || DocAction.equals("CO") || DocAction.equals("IN") || DocAction.equals("PO") || DocAction.equals("PR") || DocAction.equals("RA") || DocAction.equals("RC") || DocAction.equals("RE") || DocAction.equals("RJ") || DocAction.equals("VO") || DocAction.equals("WC") || DocAction.equals("XL")); else throw new IllegalArgumentException ("DocAction Invalid value - " + DocAction + " - Reference_ID=135 - -- - AP - CL - CO - IN - PO - PR - RA - RC - RE - RJ - VO - WC - XL");		if (DocAction.length() > 2)
		{
			log.warning("Length > 2 - truncated");
			DocAction = DocAction.substring(0, 1);
		}
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
public static final int DOCSTATUS_AD_Reference_ID=131;/** Unknown = ?? */
public static final String DOCSTATUS_Unknown = "??";/** Approved = AP */
public static final String DOCSTATUS_Approved = "AP";/** Closed = CL */
public static final String DOCSTATUS_Closed = "CL";/** Completed = CO */
public static final String DOCSTATUS_Completed = "CO";/** Drafted = DR */
public static final String DOCSTATUS_Drafted = "DR";/** Invalid = IN */
public static final String DOCSTATUS_Invalid = "IN";/** In Progress = IP */
public static final String DOCSTATUS_InProgress = "IP";/** Not Approved = NA */
public static final String DOCSTATUS_NotApproved = "NA";/** Reversed = RE */
public static final String DOCSTATUS_Reversed = "RE";/** Voided = VO */
public static final String DOCSTATUS_Voided = "VO";/** Waiting Confirmation = WC */
public static final String DOCSTATUS_WaitingConfirmation = "WC";/** Waiting Payment = WP */
public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Set Document Status.
		@param DocStatus 
		The current status of the document
	  */
	public void setDocStatus (String DocStatus)
	{
if (DocStatus == null) throw new IllegalArgumentException ("DocStatus is mandatory");if (DocStatus.equals("??") || DocStatus.equals("AP") || DocStatus.equals("CL") || DocStatus.equals("CO") || DocStatus.equals("DR") || DocStatus.equals("IN") || DocStatus.equals("IP") || DocStatus.equals("NA") || DocStatus.equals("RE") || DocStatus.equals("VO") || DocStatus.equals("WC") || DocStatus.equals("WP")); else throw new IllegalArgumentException ("DocStatus Invalid value - " + DocStatus + " - Reference_ID=131 - ?? - AP - CL - CO - DR - IN - IP - NA - RE - VO - WC - WP");		if (DocStatus.length() > 2)
		{
			log.warning("Length > 2 - truncated");
			DocStatus = DocStatus.substring(0, 1);
		}
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
		if (DocumentNo == null)
			throw new IllegalArgumentException ("DocumentNo is mandatory.");
		if (DocumentNo.length() > 30)
		{
			log.warning("Length > 30 - truncated");
			DocumentNo = DocumentNo.substring(0, 29);
		}
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getDocumentNo());
    }

	public I_GL_Category getI_GL_Category() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_GL_Category.Table_Name);
        I_GL_Category result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_GL_Category)constructor.newInstance(new Object[] {getCtx(), new Integer(getGL_Category_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set GL Category.
		@param GL_Category_ID 
		General Ledger Category
	  */
	public void setGL_Category_ID (int GL_Category_ID)
	{
		if (GL_Category_ID <= 0) 		set_Value (COLUMNNAME_GL_Category_ID, null);
 else 
		set_Value (COLUMNNAME_GL_Category_ID, Integer.valueOf(GL_Category_ID));
	}

	/** Get GL Category.
		@return General Ledger Category
	  */
	public int getGL_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Journal Batch.
		@param GL_JournalBatch_ID 
		General Ledger Journal Batch
	  */
	public void setGL_JournalBatch_ID (int GL_JournalBatch_ID)
	{
		if (GL_JournalBatch_ID < 1)
			 throw new IllegalArgumentException ("GL_JournalBatch_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_GL_JournalBatch_ID, Integer.valueOf(GL_JournalBatch_ID));
	}

	/** Get Journal Batch.
		@return General Ledger Journal Batch
	  */
	public int getGL_JournalBatch_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_JournalBatch_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

/** PostingType AD_Reference_ID=125 */
public static final int POSTINGTYPE_AD_Reference_ID=125;/** Actual = A */
public static final String POSTINGTYPE_Actual = "A";/** Budget = B */
public static final String POSTINGTYPE_Budget = "B";/** Commitment = E */
public static final String POSTINGTYPE_Commitment = "E";/** Reservation = R */
public static final String POSTINGTYPE_Reservation = "R";/** Statistical = S */
public static final String POSTINGTYPE_Statistical = "S";
	/** Set PostingType.
		@param PostingType 
		The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType)
	{
if (PostingType == null) throw new IllegalArgumentException ("PostingType is mandatory");if (PostingType.equals("A") || PostingType.equals("B") || PostingType.equals("E") || PostingType.equals("R") || PostingType.equals("S")); else throw new IllegalArgumentException ("PostingType Invalid value - " + PostingType + " - Reference_ID=125 - A - B - E - R - S");		if (PostingType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			PostingType = PostingType.substring(0, 0);
		}
		set_Value (COLUMNNAME_PostingType, PostingType);
	}

	/** Get PostingType.
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

	/** Set Total Credit.
		@param TotalCr 
		Total Credit in document currency
	  */
	public void setTotalCr (BigDecimal TotalCr)
	{
		if (TotalCr == null)
			throw new IllegalArgumentException ("TotalCr is mandatory.");
		set_ValueNoCheck (COLUMNNAME_TotalCr, TotalCr);
	}

	/** Get Total Credit.
		@return Total Credit in document currency
	  */
	public BigDecimal getTotalCr () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalCr);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Debit.
		@param TotalDr 
		Total debit in document currency
	  */
	public void setTotalDr (BigDecimal TotalDr)
	{
		if (TotalDr == null)
			throw new IllegalArgumentException ("TotalDr is mandatory.");
		set_ValueNoCheck (COLUMNNAME_TotalDr, TotalDr);
	}

	/** Get Total Debit.
		@return Total debit in document currency
	  */
	public BigDecimal getTotalDr () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalDr);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}