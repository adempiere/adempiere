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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Journal Batch Model
 *
 *	@author Jorg Janke
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * 			<li>FR [ 1948157  ]  Is necessary the reference for document reverse
 *  		@see http://sourceforge.net/tracker/?func=detail&atid=879335&aid=1948157&group_id=176962
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org 
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962 	
 *  @author Teo Sarca, www.arhipac.ro
 * 			<li>FR [ 1776045 ] Add ReActivate action to GL Journal
 *	@version $Id: MJournalBatch.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com 2015-05-25, 18:20
 * 			<a href="https://github.com/adempiere/adempiere/issues/1073">
 * 			@see BR [ 1073 ] Duplicate key when try reverse a Journal Batch</a>
 * 			<a href="https://github.com/adempiere/adempiere/issues/887">
 * 			@see FR [ 887 ] System Config reversal invoice DocNo</a>
 */
public class MJournalBatch extends X_GL_JournalBatch implements DocAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2494833602067696046L;

	/**
	 * 	Create new Journal Batch by copying
	 * 	@param ctx context
	 *	@param GL_JournalBatch_ID journal batch
	 * 	@param dateDoc date of the document date
	 *	@param trxName transaction
	 *	@return Journal Batch
	 */
	public static MJournalBatch copyFrom (Properties ctx, int GL_JournalBatch_ID, 
		Timestamp dateDoc, String trxName)
	{
		MJournalBatch from = new MJournalBatch (ctx, GL_JournalBatch_ID, trxName);
		if (from.getGL_JournalBatch_ID() == 0)
			throw new IllegalArgumentException ("From Journal Batch not found GL_JournalBatch_ID=" + GL_JournalBatch_ID);
		//
		MJournalBatch to = new MJournalBatch (ctx, 0, trxName);
		PO.copyValues(from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
		to.set_ValueNoCheck ("DocumentNo", null);
		to.set_ValueNoCheck ("C_Period_ID", null);
		to.setDateAcct(dateDoc);
		to.setDateDoc(dateDoc);
		to.setDocStatus(DOCSTATUS_Drafted);
		to.setDocAction(DOCACTION_Complete);
		to.setIsApproved(false);
		to.setProcessed (false);
		//
		to.saveEx();

		if (to.copyDetailsFrom(from) == 0)
			throw new IllegalStateException("Could not create Journal Batch Details");

		return to;
	}	//	copyFrom
	
	
	/**************************************************************************
	 * 	Standard Construvtore
	 *	@param ctx context
	 *	@param GL_JournalBatch_ID id if 0 - create actual batch
	 *	@param trxName transaction
	 */
	public MJournalBatch (Properties ctx, int GL_JournalBatch_ID, String trxName)
	{
		super (ctx, GL_JournalBatch_ID, trxName);
		if (GL_JournalBatch_ID == 0)
		{
		//	setGL_JournalBatch_ID (0);	PK
		//	setDescription (null);
		//	setDocumentNo (null);
		//	setC_DocType_ID (0);
			setPostingType (POSTINGTYPE_Actual);
			setDocAction (DOCACTION_Complete);
			setDocStatus (DOCSTATUS_Drafted);
			setTotalCr (Env.ZERO);
			setTotalDr (Env.ZERO);
			setProcessed (false);
			setProcessing (false);
			setIsApproved(false);
		}
	}	//	MJournalBatch

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MJournalBatch (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MJournalBatch

	/**
	 * 	Copy Constructor.
	 * 	Dos not copy: Dates/Period
	 *	@param original original
	 */
	public MJournalBatch (MJournalBatch original)
	{
		this (original.getCtx(), 0, original.get_TrxName());
		setClientOrg(original);
		//
	//	setC_AcctSchema_ID(original.getC_AcctSchema_ID());
	//	setGL_Budget_ID(original.getGL_Budget_ID());
		setGL_Category_ID(original.getGL_Category_ID());
		setPostingType(original.getPostingType());
		setDescription(original.getDescription());
		setC_DocType_ID(original.getC_DocType_ID());
		setControlAmt(original.getControlAmt());
		//
		setC_Currency_ID(original.getC_Currency_ID());
	//	setC_ConversionType_ID(original.getC_ConversionType_ID());
	//	setCurrencyRate(original.getCurrencyRate());
		
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
			log.warning("Period not found");
		else
			setC_Period_ID(C_Period_ID);
	}	//	setDateAcct

	/**
	 * 	Get Journal Lines
	 * 	@param requery requery
	 *	@return Array of lines
	 */
	public MJournal[] getJournals (boolean requery)
	{
		ArrayList<MJournal> list = new ArrayList<MJournal>();
		String sql = "SELECT * FROM GL_Journal WHERE GL_JournalBatch_ID=? ORDER BY DocumentNo";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getGL_JournalBatch_ID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MJournal (getCtx(), rs, get_TrxName()));
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE, sql, ex);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
		}
		catch (SQLException ex1)
		{
		}
		pstmt = null;
		//
		MJournal[] retValue = new MJournal[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getJournals

	/**
	 * 	Copy Journal/Lines from other Journal Batch
	 *	@param jb Journal Batch
	 *	@return number of journals + lines copied
	 */
	public int copyDetailsFrom (MJournalBatch jb)
	{
		if (isProcessed() || jb == null)
			return 0;
		int count = 0;
		int lineCount = 0;
		MJournal[] fromJournals = jb.getJournals(false);
		for (int i = 0; i < fromJournals.length; i++)
		{
			MJournal toJournal = new MJournal (getCtx(), 0, jb.get_TrxName());
			PO.copyValues(fromJournals[i], toJournal, getAD_Client_ID(), getAD_Org_ID());
			toJournal.setGL_JournalBatch_ID(getGL_JournalBatch_ID());
			toJournal.set_ValueNoCheck ("DocumentNo", null);	//	create new
			toJournal.set_ValueNoCheck ("C_Period_ID", null);
			toJournal.setDateDoc(getDateDoc());		//	dates from this Batch
			toJournal.setDateAcct(getDateAcct());
			toJournal.setDocStatus(MJournal.DOCSTATUS_Drafted);
			toJournal.setDocAction(MJournal.DOCACTION_Complete);
			toJournal.setTotalCr(Env.ZERO);
			toJournal.setTotalDr(Env.ZERO);
			toJournal.setIsApproved(false);
			toJournal.setIsPrinted(false);
			toJournal.setPosted(false);
			toJournal.setProcessed(false);
			if (toJournal.save())
			{
				count++;
				lineCount += toJournal.copyLinesFrom(fromJournals[i], getDateAcct(), 'x');
			}
		}
		if (fromJournals.length != count)
			log.log(Level.SEVERE, "Line difference - Journals=" + fromJournals.length + " <> Saved=" + count);

		return count + lineCount;
	}	//	copyLinesFrom

	
	/**************************************************************************
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	public boolean processIt (String processAction)
	{
		processMessage = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	process
	
	/**	Process Message 			*/
	private String processMessage = null;
	/**	Just Prepared Flag			*/
	private boolean justPrepared = false;

	/**
	 * 	Unlock Document.
	 * 	@return true if success 
	 */
	public boolean unlockIt()
	{
		log.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	}	//	unlockIt
	
	/**
	 * 	Invalidate Document
	 * 	@return true if success 
	 */
	public boolean invalidateIt()
	{
		log.info("invalidateIt - " + toString());
		return true;
	}	//	invalidateIt
	
	/**
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid) 
	 */
	public String prepareIt()
	{
		log.info(toString());
		processMessage = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (processMessage != null)
			return DocAction.STATUS_Invalid;
		MDocType docType = MDocType.get(getCtx(), getC_DocType_ID());
		MPeriod currentPeriod = MPeriod.get(getCtx() , getC_Period_ID());
		assert currentPeriod != null;
		if (MPeriod.PERIODTYPE_AdjustmentPeriod.equals(currentPeriod.getPeriodType())) {
			boolean open = currentPeriod.isOpen(docType.getDocBaseType() , getDateAcct());
			if (!open)
			{
				log.warning(currentPeriod.getName() + ": Not open for " + docType.getDocBaseType() + " (" + getDateAcct() + ")");
				processMessage = "@PeriodClosed@";
				return DocAction.STATUS_Invalid;
			}
		} else if (!MPeriod.isOpen(getCtx(), getDateAcct(), docType.getDocBaseType(), getAD_Org_ID() , get_TrxName())) {  //	Std Period open?
			processMessage = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		
		//	Add up Amounts & prepare them
		MJournal[] journals = getJournals(false);
		if (journals.length == 0)
		{
			processMessage = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}
		
		BigDecimal totalDr = Env.ZERO;
		BigDecimal totalCr = Env.ZERO;
		for (MJournal journal : journals) {
			if (!journal.isActive())
				continue;
			//	Prepare if not closed
			if (DOCSTATUS_Closed.equals(journal.getDocStatus())
					|| DOCSTATUS_Voided.equals(journal.getDocStatus())
					|| DOCSTATUS_Reversed.equals(journal.getDocStatus())
					|| DOCSTATUS_Completed.equals(journal.getDocStatus()))
				;
			else {
				String status = journal.prepareIt();
				if (!DocAction.STATUS_InProgress.equals(status)) {
					journal.setDocStatus(status);
					journal.saveEx();
					processMessage = journal.getProcessMsg();
					return status;
				}
				journal.setDocStatus(DOCSTATUS_InProgress);
				journal.saveEx();
			}
			//
			totalDr = totalDr.add(journal.getTotalDr());
			totalCr = totalCr.add(journal.getTotalCr());
		}
		setTotalDr(totalDr);
		setTotalCr(totalCr);
		
		//	Control Amount
		if (Env.ZERO.compareTo(getControlAmt()) != 0
			&& getControlAmt().compareTo(getTotalDr()) != 0)
		{
			processMessage = "@ControlAmtError@";
			return DocAction.STATUS_Invalid;
		}
		
//		 Bug 1353695 Currency Rate and COnbversion Type should get copied from journal to lines
		for (MJournal journal : journals) {
			MJournalLine[] journalLines = journal.getLines(true);
			if (journal.getCurrencyRate() != null && journal.getCurrencyRate().compareTo(Env.ZERO) != 0) {
				for (MJournalLine journalLine : journalLines) {
					journalLine.setCurrencyRate(journal.getCurrencyRate());
					journalLine.saveEx();
				}
			}
			if (journal.getC_ConversionType_ID() > 0) {
				for (MJournalLine journalLine : journalLines) {
					journalLine.setC_ConversionType_ID(journal.getC_ConversionType_ID());
					journalLine.saveEx();
				}
			}
		}
		
		processMessage = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (processMessage != null)
			return DocAction.STATUS_Invalid;
		
		//	Add up Amounts
		justPrepared = true;
		return DocAction.STATUS_InProgress;
	}	//	prepareIt
	
	/**
	 * 	Approve Document
	 * 	@return true if success 
	 */
	public boolean  approveIt()
	{
		log.info("approveIt - " + toString());
		setIsApproved(true);
		return true;
	}	//	approveIt
	
	/**
	 * 	Reject Approval
	 * 	@return true if success 
	 */
	public boolean rejectIt()
	{
		log.info("rejectIt - " + toString());
		setIsApproved(false);
		return true;
	}	//	rejectIt
	
	/**
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt()
	{
		log.info("completeIt - " + toString());
		//	Re-Check
		if (!justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}
		
		processMessage = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (processMessage != null)
			return DocAction.STATUS_Invalid;
		
		//	Implicit Approval
		approveIt();

		//	Add up Amounts & complete them
		MJournal[] journals = getJournals(true);
		BigDecimal TotalDr = Env.ZERO;
		BigDecimal TotalCr = Env.ZERO;		
		for (int i = 0; i < journals.length; i++)
		{
			MJournal journal = journals[i];
			if (!journal.isActive())
			{
				journal.setProcessed(true);
				journal.setDocStatus(DOCSTATUS_Voided);
				journal.setDocAction(DOCACTION_None);
				journal.saveEx();
				continue;
			}
			//	Complete if not closed
			if (DOCSTATUS_Closed.equals(journal.getDocStatus())
				|| DOCSTATUS_Voided.equals(journal.getDocStatus())
				|| DOCSTATUS_Reversed.equals(journal.getDocStatus())
				|| DOCSTATUS_Completed.equals(journal.getDocStatus()))
				;
			else
			{
				journal.processIt(DocAction.ACTION_Complete);
				journal.saveEx();
				if (!DocAction.STATUS_Completed.equals(journal.getDocStatus()))
				{
					processMessage = journal.getProcessMsg();
					return journal.getDocStatus();
				}
			}
			//
			TotalDr = TotalDr.add(journal.getTotalDr());
			TotalCr = TotalCr.add(journal.getTotalCr());
		}
		setTotalDr(TotalDr);
		setTotalCr(TotalCr);
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			processMessage = valid;
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
	 * 	@return false 
	 */
	public boolean voidIt()
	{
		log.info("voidIt - " + toString());
		// Before Void
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (processMessage != null)
			return false;
		// After Void
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (processMessage != null)
			return false;
		
		return false;
	}	//	voidIt
	
	/**
	 * 	Close Document.
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		log.info("closeIt - " + toString());
		// Before Close
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (processMessage != null)
			return false;
		
		MJournal[] journals = getJournals(true);
		for (int i = 0; i < journals.length; i++)
		{
			MJournal journal = journals[i];
			if (!journal.isActive() && !journal.isProcessed())
			{
				journal.setProcessed(true);
				journal.setDocStatus(DOCSTATUS_Voided);
				journal.setDocAction(DOCACTION_None);
				journal.saveEx();
				continue;
			}
			if (DOCSTATUS_Drafted.equals(journal.getDocStatus())
				|| DOCSTATUS_InProgress.equals(journal.getDocStatus())
				|| DOCSTATUS_Invalid.equals(journal.getDocStatus()))
			{
				processMessage = "Journal not Completed: " + journal.getSummary();
				return false;
			}
			
			//	Close if not closed
			if (DOCSTATUS_Closed.equals(journal.getDocStatus())
				|| DOCSTATUS_Voided.equals(journal.getDocStatus())
				|| DOCSTATUS_Reversed.equals(journal.getDocStatus()))
				;
			else
			{
				if (!journal.closeIt())
				{
					processMessage = "Cannot close: " + journal.getSummary();
					return false;
				}
				journal.saveEx();
			}
		}
		// After Close
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (processMessage != null)
			return false;
		
		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction.
	 * 	As if nothing happened - same date
	 * 	@return true if success 
	 */
	public boolean reverseCorrectIt()
	{
		log.info("reverseCorrectIt - " + toString());
		// Before reverseCorrect
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (processMessage != null)
			return false;
		
		boolean isOk = reverseBatch(false);
		if(!isOk) {
			return false;
		}
		
		// After reverseCorrect
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (processMessage != null)
			return false;
		//	
		return true;
	}	//	reverseCorrectionIt
	
	/**
	 * Reverse Journal Batch
	 * @return
	 */
	private boolean reverseBatch(boolean isAccrual) {
		MJournal [] journals = getJournals(true);
		//	check prerequisites
		for (MJournal journal : journals) {
			if (!journal.isActive())
				continue;
			//	All need to be closed/Completed
			if (DOCSTATUS_Completed.equals(journal.getDocStatus()))
				;
			else
			{
				processMessage = "All Journals need to be Completed: " + journal.getSummary();
				return false;
			}
		}
		
		//	Reverse it
		MJournalBatch reverse = new MJournalBatch (this);
		if(isAccrual) {
			reverse.setC_Period_ID(0);
			reverse.setDateDoc(new Timestamp(System.currentTimeMillis()));
			reverse.setDateAcct(reverse.getDateDoc());
		} else {
			reverse.setDateDoc(getDateDoc());
			reverse.setC_Period_ID(getC_Period_ID());
			reverse.setDateAcct(getDateAcct());
		}
		reverse.addDescription("** (->" + getDocumentNo() + ") **");
		//[ 1948157  ]
		reverse.setReversal_ID(getGL_JournalBatch_ID());
		reverse.setControlAmt(getControlAmt().negate());
		reverse.set_ValueNoCheck("DocumentNo", null);
		MDocType docType = MDocType.get(getCtx(), getC_DocType_ID());
		//	Set Document No from flag
		if(docType.isCopyDocNoOnReversal()) {
			reverse.setDocumentNo(getDocumentNo() + Msg.getMsg(reverse.getCtx(), "^"));
		}
		reverse.saveEx();
		//
		
		//	Reverse Journals
		for (int i = 0; i < journals.length; i++)
		{
			MJournal journal = journals[i];
			if (!journal.isActive())
				continue;
			if(isAccrual) {
				if (journal.reverseAccrualIt(reverse.getGL_JournalBatch_ID()) == null)
				{
					processMessage = "Could not reverse " + journal;
					return false;
				}
			} else {
				if (journal.reverseCorrectIt(reverse.getGL_JournalBatch_ID()) == null) {
					processMessage = "Could not reverse " + journal;
					return false;
				}
			}
			//	Save if is ok
			journal.saveEx();
		}
		
		//[ 1948157  ]
		reverse.setDocAction(DOCACTION_None);
		reverse.setDocStatus(DOCSTATUS_Reversed);
		reverse.setProcessed(true);
		reverse.saveEx();
		addDescription("** (" + reverse.getDocumentNo() + "<-) **");
		//	
		setReversal_ID(reverse.getGL_JournalBatch_ID());
		setDocAction(DOCACTION_None);
		saveEx();
		//	Is Ok
		return true;
	}
	
	/**
	 * 	Add to Description
	 *	@param description text
	 */
	public void addDescription (String description) {
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
			setDescription(desc + " | " + description);
	}	//	addDescription
	
	/**
	 * 	Reverse Accrual.
	 * 	Flip Dr/Cr - Use Today's date
	 * 	@return true if success 
	 */
	public boolean reverseAccrualIt()
	{
		log.info("reverseAccrualIt - " + toString());
		// Before reverseAccrual
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (processMessage != null)
			return false;
		
		boolean isOk = reverseBatch(true);
		if(!isOk) {
			return false;
		}
		
		// After reverseAccrual
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (processMessage != null)
			return false;
				
		return true;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate - same as reverse correct
	 * 	@return true if success 
	 */
	public boolean reActivateIt()
	{
		log.info("reActivateIt - " + toString());
		
		// Before reActivate
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (processMessage != null)
			return false;	
		
		for (MJournal journal : getJournals(true))
		{
			if (DOCSTATUS_Completed.equals(journal.getDocStatus()))
			{
				if (journal.processIt(DOCACTION_Re_Activate))
				{
					journal.saveEx();
				}
				else
				{
					throw new AdempiereException(journal.getProcessMsg());
				}
			}
		}
		setProcessed(false);
		setDocAction(DOCACTION_Complete);

		// After reActivate
		processMessage = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (processMessage != null)
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
		.append(" (#").append(getJournals(false).length).append(")");
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
		StringBuffer sb = new StringBuffer ("MJournalBatch[");
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
		return processMessage;
	}	//	getProcessMsg
	
	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID (Created By)
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
	
}	//	MJournalBatch
