/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  GL Journal Model
 *
 *	@author Jorg Janke
 *	@version $Id: MJournal.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>BF [ 1619150 ] Usability/Consistency: reversed gl journal description
 * 				<li>BF [ 1775358 ] GL Journal DateAcct/C_Period_ID issue
 * 				<li>FR [ 1776045 ] Add ReActivate action to GL Journal
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * 			<li>FR [ 1948157  ]  Is necessary the reference for document reverse
 *  		@see http://sourceforge.net/tracker/?func=detail&atid=879335&aid=1948157&group_id=176962
 *  		<li>FR: [ 2214883 ] Remove SQL code and Replace for Query 
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org 
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962 	
 */
public class MJournal extends X_GL_Journal implements DocAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -364132249042527640L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param GL_Journal_ID id
	 *	@param trxName transaction
	 */
	public MJournal (Properties ctx, int GL_Journal_ID, String trxName)
	{
		super (ctx, GL_Journal_ID, trxName);
		if (GL_Journal_ID == 0)
		{
		//	setGL_Journal_ID (0);		//	PK
		//	setC_AcctSchema_ID (0);
		//	setC_Currency_ID (0);
		//	setC_DocType_ID (0);
		//	setC_Period_ID (0);
			//
			setCurrencyRate (Env.ONE);
		//	setC_ConversionType_ID(0);
			setDateAcct (new Timestamp(System.currentTimeMillis()));
			setDateDoc (new Timestamp(System.currentTimeMillis()));
		//	setDescription (null);
			setDocAction (DOCACTION_Complete);
			setDocStatus (DOCSTATUS_Drafted);
		//	setDocumentNo (null);
		//	setGL_Category_ID (0);
			setPostingType (POSTINGTYPE_Actual);
			setTotalCr (Env.ZERO);
			setTotalDr (Env.ZERO);
			setIsApproved (false);
			setIsPrinted (false);
			setPosted (false);
			setProcessed(false);
		}
	}	//	MJournal

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MJournal (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MJournal

	/**
	 * 	Parent Constructor.
	 *	@param parent batch
	 */
	public MJournal (MJournalBatch parent)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setGL_JournalBatch_ID(parent.getGL_JournalBatch_ID());
		setC_DocType_ID(parent.getC_DocType_ID());
		setPostingType(parent.getPostingType());
		//
		setDateDoc(parent.getDateDoc());
		setC_Period_ID(parent.getC_Period_ID());
		setDateAcct(parent.getDateAcct());
		setC_Currency_ID(parent.getC_Currency_ID());
	}	//	MJournal
	
	/**
	 * 	Copy Constructor.
	 * 	Dos not copy: Dates/Period
	 *	@param original original
	 */
	public MJournal (MJournal original)
	{
		this (original.getCtx(), 0, original.get_TrxName());
		setClientOrg(original);
		setGL_JournalBatch_ID(original.getGL_JournalBatch_ID());
		//
		setC_AcctSchema_ID(original.getC_AcctSchema_ID());
		setGL_Budget_ID(original.getGL_Budget_ID());
		setGL_Category_ID(original.getGL_Category_ID());
		setPostingType(original.getPostingType());
		setDescription(original.getDescription());
		setC_DocType_ID(original.getC_DocType_ID());
		setControlAmt(original.getControlAmt());
		//
		setC_Currency_ID(original.getC_Currency_ID());
		setC_ConversionType_ID(original.getC_ConversionType_ID());
		setCurrencyRate(original.getCurrencyRate());
		
	//	setDateDoc(original.getDateDoc());
	//	setDateAcct(original.getDateAcct());
	//	setC_Period_ID(original.getC_Period_ID());
	}	//	MJournal
	
	
	/**
	 * 	Overwrite Client/Org if required
	 * 	@param AD_Client_ID client
	 * 	@param AD_Org_ID org
	 */
	public void setClientOrg (int AD_Client_ID, int AD_Org_ID)
	{
		super.setClientOrg(AD_Client_ID, AD_Org_ID);
	}	//	setClientOrg

	/**
	 * 	Set Accounting Date.
	 * 	Set also Period if not set earlier
	 *	@param DateAcct date
	 */
	public void setDateAcct (Timestamp DateAcct)
	{
		super.setDateAcct(DateAcct);
		if (DateAcct == null)
			return;
		if (getC_Period_ID() != 0)
			return;
		int C_Period_ID = MPeriod.getC_Period_ID(getCtx(), DateAcct, getAD_Org_ID());
		if (C_Period_ID == 0)
			log.warning("setDateAcct - Period not found");
		else
			setC_Period_ID(C_Period_ID);
	}	//	setDateAcct

	/**
	 * 	Set Currency Info
	 *	@param C_Currency_ID currenct
	 *	@param C_ConversionType_ID type
	 *	@param CurrencyRate rate
	 */
	public void setCurrency (int C_Currency_ID, int C_ConversionType_ID, BigDecimal CurrencyRate)
	{
		if (C_Currency_ID != 0)
			setC_Currency_ID(C_Currency_ID);
		if (C_ConversionType_ID != 0)
			setC_ConversionType_ID(C_ConversionType_ID);
		if (CurrencyRate != null && CurrencyRate.compareTo(Env.ZERO) == 0)
			setCurrencyRate(CurrencyRate);
	}	//	setCurrency

	/**
	 * Add to Description
	 * @param description text
	 * @since 3.1.4
	 */
	public void addDescription (String description)
	{
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
			setDescription(desc + " | " + description);
	}
	
	/**************************************************************************
	 * 	Get Journal Lines
	 * 	@param requery requery
	 *	@return Array of lines
	 */
	public MJournalLine[] getLines (boolean requery)
	{
		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		final String whereClause = "GL_Journal_ID=?";
		List <MJournalLine> list = new Query(getCtx(), I_GL_JournalLine.Table_Name, whereClause, get_TrxName())
			.setParameters(getGL_Journal_ID())
			.setOrderBy("Line")
			.list();
		//
		MJournalLine[] retValue = new MJournalLine[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getLines

	/**
	 * 	Copy Lines from other Journal
	 *	@param fromJournal Journal
	 *	@param dateAcct date used - if null original
	 *	@param typeCR type of copying (C)orrect=negate - (R)everse=flip dr/cr - otherwise just copy
	 *	@return number of lines copied
	 */
	public int copyLinesFrom (MJournal fromJournal, Timestamp dateAcct, char typeCR)
	{
		if (isProcessed() || fromJournal == null)
			return 0;
		int count = 0;
		MJournalLine[] fromLines = fromJournal.getLines(false);
		for (int i = 0; i < fromLines.length; i++)
		{
			MJournalLine toLine = new MJournalLine (getCtx(), 0, fromJournal.get_TrxName());
			PO.copyValues(fromLines[i], toLine, getAD_Client_ID(), getAD_Org_ID());
			toLine.setGL_Journal_ID(getGL_Journal_ID());
			//
			if (dateAcct != null)
				toLine.setDateAcct(dateAcct);
			//	Amounts
			if (typeCR == 'C')			//	correct
			{
				toLine.setAmtSourceDr(fromLines[i].getAmtSourceDr().negate());
				toLine.setAmtSourceCr(fromLines[i].getAmtSourceCr().negate());
			}
			else if (typeCR == 'R')		//	reverse
			{
				toLine.setAmtSourceDr(fromLines[i].getAmtSourceCr());
				toLine.setAmtSourceCr(fromLines[i].getAmtSourceDr());
			}
			toLine.setIsGenerated(true);
			toLine.setProcessed(false);
			if (toLine.save())
				count++;
		}
		if (fromLines.length != count)
			log.log(Level.SEVERE, "Line difference - JournalLines=" + fromLines.length + " <> Saved=" + count);

		return count;
	}	//	copyLinesFrom

	/**
	 * 	Set Processed.
	 * 	Propagate to Lines/Taxes
	 *	@param processed processed
	 */
	public void setProcessed (boolean processed)
	{
		super.setProcessed (processed);
		if (get_ID() == 0)
			return;
		String sql = "UPDATE GL_JournalLine SET Processed='"
			+ (processed ? "Y" : "N")
			+ "' WHERE GL_Journal_ID=" + getGL_Journal_ID();
		int noLine = DB.executeUpdate(sql, get_TrxName());
		log.fine(processed + " - Lines=" + noLine);
	}	//	setProcessed

	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Imported Journals may not have date
		if (getDateDoc() == null)
		{
			if (getDateAcct() == null)
				setDateDoc(new Timestamp(System.currentTimeMillis()));
			else
				setDateDoc(getDateAcct());
		}
		if (getDateAcct() == null)
			setDateAcct(getDateDoc());
		
		// Update DateAcct on lines - teo_sarca BF [ 1775358 ]
		if (is_ValueChanged(COLUMNNAME_DateAcct)) {
			int no = DB.executeUpdate(
					"UPDATE GL_JournalLine SET "+MJournalLine.COLUMNNAME_DateAcct+"=? WHERE GL_Journal_ID=?",
					new Object[]{getDateAcct(), getGL_Journal_ID()},
					false, get_TrxName());
			log.finest("Updated GL_JournalLine.DateAcct #" + no);
		}
		return true;
	}	//	beforeSave
	
	
	/**
	 * 	After Save.
	 * 	Update Batch Total
	 *	@param newRecord true if new record
	 *	@param success true if success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;		
		return updateBatch();
	}	//	afterSave
	
	/**
	 * 	After Delete
	 *	@param success true if deleted
	 *	@return true if success
	 */
	protected boolean afterDelete (boolean success)
	{
		if (!success)
			return success;
		return updateBatch();
	}	//	afterDelete
	
	/**
	 * 	Update Batch total
	 *	@return true if ok
	 */
	private boolean updateBatch()
	{
		String sql = "UPDATE GL_JournalBatch jb"
			+ " SET (TotalDr, TotalCr) = (SELECT COALESCE(SUM(TotalDr),0), COALESCE(SUM(TotalCr),0)"
				+ " FROM GL_Journal j WHERE j.IsActive='Y' AND jb.GL_JournalBatch_ID=j.GL_JournalBatch_ID) "
			+ "WHERE GL_JournalBatch_ID=" + getGL_JournalBatch_ID();
		int no = DB.executeUpdate(sql, get_TrxName());
		if (no != 1)
			log.warning("afterSave - Update Batch #" + no);
		return no == 1;
	}	//	updateBatch
	
	
	/**************************************************************************
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	public boolean processIt (String processAction)
	{
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	process
	
	/**	Process Message 			*/
	private String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean		m_justPrepared = false;

	/**
	 * 	Unlock Document.
	 * 	@return true if success 
	 */
	public boolean unlockIt()
	{
		log.info(toString());
		setProcessing(false);
		return true;
	}	//	unlockIt
	
	/**
	 * 	Invalidate Document
	 * 	@return true if success 
	 */
	public boolean invalidateIt()
	{
		log.info(toString());
		return true;
	}	//	invalidateIt
	
	/**
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid) 
	 */
	public String prepareIt()
	{
		log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());

		//	Get Period
		MPeriod period = MPeriod.get (getCtx(), getDateAcct(), getAD_Org_ID());
		if (period == null)
		{
			log.warning("No Period for " + getDateAcct());
			m_processMsg = "@PeriodNotFound@";
			return DocAction.STATUS_Invalid;
		}
		//	Standard Period
		if (period.getC_Period_ID() != getC_Period_ID()
			&& period.isStandardPeriod())
		{
			m_processMsg = "@PeriodNotValid@";
			return DocAction.STATUS_Invalid;
		}
		boolean open = period.isOpen(dt.getDocBaseType(), getDateAcct());
		if (!open)
		{
			log.warning(period.getName()
				+ ": Not open for " + dt.getDocBaseType() + " (" + getDateAcct() + ")");
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}

		//	Lines
		MJournalLine[] lines = getLines(true);
		if (lines.length == 0)
		{
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		
		//	Add up Amounts
		BigDecimal AmtSourceDr = Env.ZERO;
		BigDecimal AmtSourceCr = Env.ZERO;
		for (int i = 0; i < lines.length; i++)
		{
			MJournalLine line = lines[i];
			if (!isActive())
				continue;
			
			// bcahya, BF [2789319] No check of Actual, Budget, Statistical attribute
			if (!line.getAccountElementValue().isActive())
			{
				m_processMsg = "@InActiveAccount@ - @Line@=" + line.getLine()
				+ " - " + line.getAccountElementValue();
				return DocAction.STATUS_Invalid;
			}
			
			// Michael Judd (mjudd) BUG: [ 2678088 ] Allow posting to system accounts for non-actual postings
			if (line.isDocControlled() && 
					( getPostingType().equals(POSTINGTYPE_Actual)) ||
					  getPostingType().equals(POSTINGTYPE_Commitment) ||
					  getPostingType().equals(POSTINGTYPE_Reservation)
					 )
			{
				m_processMsg = "@DocControlledError@ - @Line@=" + line.getLine()
					+ " - " + line.getAccountElementValue();
				return DocAction.STATUS_Invalid;
			}
			//
			
			// bcahya, BF [2789319] No check of Actual, Budget, Statistical attribute
			if (getPostingType().equals(POSTINGTYPE_Actual) && !line.getAccountElementValue().isPostActual())
			{
				m_processMsg = "@PostingTypeActualError@ - @Line@=" + line.getLine()
				+ " - " + line.getAccountElementValue();
				return DocAction.STATUS_Invalid;
			}
			
			if (getPostingType().equals(POSTINGTYPE_Budget) && !line.getAccountElementValue().isPostBudget())
			{
				m_processMsg = "@PostingTypeBudgetError@ - @Line@=" + line.getLine()
				+ " - " + line.getAccountElementValue();
				return DocAction.STATUS_Invalid;
			}
			
			if (getPostingType().equals(POSTINGTYPE_Statistical) && !line.getAccountElementValue().isPostStatistical())
			{
				m_processMsg = "@PostingTypeStatisticalError@ - @Line@=" + line.getLine()
				+ " - " + line.getAccountElementValue();
				return DocAction.STATUS_Invalid;
			}
			// end BF [2789319] No check of Actual, Budget, Statistical attribute
			
			AmtSourceDr = AmtSourceDr.add(line.getAmtSourceDr());
			AmtSourceCr = AmtSourceCr.add(line.getAmtSourceCr());
		}
		setTotalDr(AmtSourceDr);
		setTotalCr(AmtSourceCr);

		//	Control Amount
		if (Env.ZERO.compareTo(getControlAmt()) != 0
			&& getControlAmt().compareTo(getTotalDr()) != 0)
		{
			m_processMsg = "@ControlAmtError@";
			return DocAction.STATUS_Invalid;
		}
		
		//	Unbalanced Jornal & Not Suspense
		if (AmtSourceDr.compareTo(AmtSourceCr) != 0)
		{
			MAcctSchemaGL gl = MAcctSchemaGL.get(getCtx(), getC_AcctSchema_ID());
			if (gl == null || !gl.isUseSuspenseBalancing())
			{
				m_processMsg = "@UnbalancedJornal@";
				return DocAction.STATUS_Invalid;
			}
		}
		
		if (!DOCACTION_Complete.equals(getDocAction())) 
			setDocAction(DOCACTION_Complete);
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		m_justPrepared = true;
		return DocAction.STATUS_InProgress;
	}	//	prepareIt
	
	/**
	 * 	Approve Document
	 * 	@return true if success 
	 */
	public boolean  approveIt()
	{
		log.info(toString());
		setIsApproved(true);
		return true;
	}	//	approveIt
	
	/**
	 * 	Reject Approval
	 * 	@return true if success 
	 */
	public boolean rejectIt()
	{
		log.info(toString());
		setIsApproved(false);
		return true;
	}	//	rejectIt
	
	/**
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt()
	{
		//	Re-Check
		if (!m_justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		//	Implicit Approval
		if (!isApproved())
			approveIt();
		log.info(toString());
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		// Set the definite document number after completed (if needed)
		setDefiniteDocumentNo();

		//
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt
	
	/**
	 * 	Set the definite document number after completed
	 */
	private void setDefiniteDocumentNo() {
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		if (dt.isOverwriteDateOnComplete()) {
			setDateDoc(new Timestamp (System.currentTimeMillis()));
		}
		if (dt.isOverwriteSeqOnComplete()) {
			String value = DB.getDocumentNo(getC_DocType_ID(), get_TrxName(), true, this);
			if (value != null)
				setDocumentNo(value);
		}
	}

	/**
	 * 	Void Document.
	 * 	@return true if success 
	 */
	public boolean voidIt()
	{
		log.info(toString());
		// Before Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (m_processMsg != null)
			return false;

		boolean ok_to_void = false;
		if (DOCSTATUS_Drafted.equals(getDocStatus()) 
			|| DOCSTATUS_Invalid.equals(getDocStatus()))
		{
			setProcessed(true);
			setDocAction(DOCACTION_None);
			ok_to_void = true;
		} else {
			return false;
		}
		
		// After Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (m_processMsg != null)
			return false;
		
		return ok_to_void;
	}	//	voidIt
	
	/**
	 * 	Close Document.
	 * 	Cancel not delivered Qunatities
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		log.info(toString());
		// Before Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
			return false;
		
		boolean ok_to_close = false;
		if (DOCSTATUS_Completed.equals(getDocStatus())) 
		{
			setProcessed(true);
			setDocAction(DOCACTION_None);
			ok_to_close = true;
		} else {
			return false;
		}
		
		// After Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
			return false;			

		return ok_to_close;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction (in same batch).
	 * 	As if nothing happened - same date
	 * 	@return true if success 
	 */
	public boolean reverseCorrectIt()
	{
		// Before reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (m_processMsg != null)
			return false;
		
		boolean ok_correct = (reverseCorrectIt(getGL_JournalBatch_ID()) != null);
		
		if (! ok_correct)
			return false;

		// After reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (m_processMsg != null)
			return false;
		
		return ok_correct;
	}	//	reverseCorrectIt

	/**
	 * 	Reverse Correction.
	 * 	As if nothing happened - same date
	 * 	@param GL_JournalBatch_ID reversal batch
	 * 	@return reversed Journal or null
	 */
	public MJournal reverseCorrectIt (int GL_JournalBatch_ID)
	{
		log.info(toString());
		//	Journal
		MJournal reverse = new MJournal (this);
		reverse.setGL_JournalBatch_ID(GL_JournalBatch_ID);
		reverse.setDateDoc(getDateDoc());
		reverse.setC_Period_ID(getC_Period_ID());
		reverse.setDateAcct(getDateAcct());
		//	Reverse indicator
		reverse.addDescription("(->" + getDocumentNo() + ")");
		//FR [ 1948157  ] 
		reverse.setReversal_ID(getGL_Journal_ID());
		if (!reverse.save())
			return null;
		addDescription("(" + reverse.getDocumentNo() + "<-)");
		
		//	Lines
		reverse.copyLinesFrom(this, null, 'C');
		//
		setProcessed(true);
		//FR [ 1948157  ] 
		setReversal_ID(reverse.getGL_Journal_ID());
		setDocAction(DOCACTION_None);
		return reverse;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual (sane batch).
	 * 	Flip Dr/Cr - Use Today's date
	 * 	@return true if success 
	 */
	public boolean reverseAccrualIt()
	{
		// Before reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;
		
		boolean ok_reverse = (reverseAccrualIt (getGL_JournalBatch_ID()) != null);
		
		if (! ok_reverse)
			return false;

		// After reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;
		
		return ok_reverse;
	}	//	reverseAccrualIt
	
	/**
	 * 	Reverse Accrual.
	 * 	Flip Dr/Cr - Use Today's date
	 * 	@param GL_JournalBatch_ID reversal batch
	 * 	@return reversed journal or null 
	 */
	public MJournal reverseAccrualIt (int GL_JournalBatch_ID)
	{
		log.info(toString());
		//	Journal
		MJournal reverse = new MJournal (this);
		reverse.setGL_JournalBatch_ID(GL_JournalBatch_ID);
		reverse.setDateDoc(new Timestamp(System.currentTimeMillis()));
		reverse.set_ValueNoCheck ("C_Period_ID", null);		//	reset
		reverse.setDateAcct(reverse.getDateDoc());
		//	Reverse indicator
		String description = reverse.getDescription();
		if (description == null)
			description = "** " + getDocumentNo() + " **";
		else
			description += " ** " + getDocumentNo() + " **";
		reverse.setDescription(description);
		if (!reverse.save())
			return null;
		
		//	Lines
		reverse.copyLinesFrom(this, reverse.getDateAcct(), 'R');
		//
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return reverse;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return true if success 
	 */
	public boolean reActivateIt()
	{
		log.info(toString());
		// Before reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (m_processMsg != null)
			return false;	
		
		// teo_sarca - FR [ 1776045 ] Add ReActivate action to GL Journal
		MPeriod.testPeriodOpen(getCtx(), getDateAcct(), getC_DocType_ID(), getAD_Org_ID());
		MFactAcct.deleteEx(MJournal.Table_ID, get_ID(), get_TrxName());
		setPosted(false);
		setProcessed(false);
		setDocAction(DOCACTION_Complete);
		
		// After reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (m_processMsg != null)
			return false;
		
		return true;
	}	//	reActivateIt
	
	
	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	public String getSummary()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
		//	: Total Lines = 123.00 (#1)
		sb.append(": ")
			.append(Msg.translate(getCtx(),"TotalDr")).append("=").append(getTotalDr())
			.append(" ")
			.append(Msg.translate(getCtx(),"TotalCR")).append("=").append(getTotalCr())
			.append(" (#").append(getLines(false).length).append(")");
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append(" - ").append(getDescription());
		return sb.toString();
	}	//	getSummary
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MJournal[");
		sb.append(get_ID()).append(",").append(getDescription())
			.append(",DR=").append(getTotalDr())
			.append(",CR=").append(getTotalCr())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	}	//	getDocumentInfo

	/**
	 * 	Create PDF
	 *	@return File or null
	 */
	public File createPDF ()
	{
		try
		{
			File temp = File.createTempFile(get_TableName()+get_ID()+"_", ".pdf");
			return createPDF (temp);
		}
		catch (Exception e)
		{
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
	}	//	getPDF

	/**
	 * 	Create PDF file
	 *	@param file output file
	 *	@return file if success
	 */
	public File createPDF (File file)
	{
	//	ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE, getC_Invoice_ID());
	//	if (re == null)
			return null;
	//	return re.getPDF(file);
	}	//	createPDF

	
	/**
	 * 	Get Process Message
	 *	@return clear text error message
	 */
	public String getProcessMsg()
	{
		return m_processMsg;
	}	//	getProcessMsg
	
	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID (Created)
	 */
	public int getDoc_User_ID()
	{
		return getCreatedBy();
	}	//	getDoc_User_ID

	/**
	 * 	Get Document Approval Amount
	 *	@return DR amount
	 */
	public BigDecimal getApprovalAmt()
	{
		return getTotalDr();
	}	//	getApprovalAmt

	/**
	 * 	Document Status is Complete or Closed
	 *	@return true if CO, CL or RE
	 */
	public boolean isComplete()
	{
		String ds = getDocStatus();
		return DOCSTATUS_Completed.equals(ds) 
			|| DOCSTATUS_Closed.equals(ds)
			|| DOCSTATUS_Reversed.equals(ds);
	}	//	isComplete

}	//	MJournal
