/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.									  *
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
package org.spin.investment.model;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.adempiere.core.domains.models.I_FM_Transaction;
import org.adempiere.core.domains.models.X_FM_Batch;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MConversionRate;
import org.compiere.model.MCurrency;
import org.compiere.model.MDocType;
import org.compiere.model.MPeriod;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;

/** Generated Model for FM_Batch
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class MFMBatch extends X_FM_Batch implements DocAction, DocOptions {

	/**
	 *
	 */
	private static final long serialVersionUID = 20180228L;

    /** Standard Constructor */
    public MFMBatch (Properties ctx, int FM_Batch_ID, String trxName)
    {
      super (ctx, FM_Batch_ID, trxName);
    }

    /** Load Constructor */
    public MFMBatch (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	}	//	getDocumentInfo
	
	/**	Transactions	*/
	private List<MFMTransaction> lines = null;

	/**
	 * 	Create PDF
	 *	@return File or null
	 */
	public File createPDF ()
	{
		try
		{
			File temp = File.createTempFile(get_TableName() + get_ID() +"_", ".pdf");
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
	}	//	processIt
	
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
		log.info("unlockIt - " + toString());
	//	setProcessing(false);
		return true;
	}	//	unlockIt
	
	/**
	 * 	Invalidate Document
	 * 	@return true if success 
	 */
	public boolean invalidateIt()
	{
		log.info("invalidateIt - " + toString());
		setDocAction(DOCACTION_Prepare);
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

		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateDoc(), dt.getDocBaseType(), getAD_Org_ID()))
		{
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		//	Add up Amounts
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
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
		//
		
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null) {
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		//	Update Heder
		updateHeader();
		
		//	Set Definitive Document No
		setDefiniteDocumentNo();

		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt
	
	/**
	 * Update Header Amount
	 */
	private void updateHeader() {
		//	Get
		BigDecimal amount = Env.ZERO;
		for(MFMTransaction transaction : getLines(true)) {
			amount = amount.add(transaction.getAmount());
		}
		//	Set value
		setTotalAmt(amount);
		saveEx();
	}
	
	/**
	 * 	Set the definite document number after completed
	 */
	private void setDefiniteDocumentNo() {
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		if (dt.isOverwriteDateOnComplete()) {
			setDateDoc(new Timestamp(System.currentTimeMillis()));
		}
		if (dt.isOverwriteSeqOnComplete()) {
			String value = null;
			int index = p_info.getColumnIndex("C_DocType_ID");
			if (index == -1)
				index = p_info.getColumnIndex("C_DocTypeTarget_ID");
			if (index != -1)		//	get based on Doc Type (might return null)
				value = DB.getDocumentNo(get_ValueAsInt(index), get_TrxName(), true);
			if (value != null) {
				setDocumentNo(value);
			}
		}
	}

	/**
	 * 	Void Document.
	 * 	Same as Close.
	 * 	@return true if success 
	 */
	public boolean voidIt() {
		log.info("voidIt - " + toString());
		if(getDocStatus().equals(DOCSTATUS_Drafted)) {
			setProcessed(true);
			setProcessing (false);
			setDocAction(DOCACTION_None);
			return true;
		}
		//	
		return reverseCorrectIt();
	}	//	voidIt
	
	/**
	 * 	Close Document.
	 * 	Cancel not delivered Qunatities
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		log.info("closeIt - " + toString());

		//	Close Not delivered Qty
		setDocAction(DOCACTION_None);
		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction - same void
	 * 	@return true if success
	 */
	public boolean reverseCorrectIt() {
		log.info("reverseCorrectIt - " + toString());
		// Before reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (m_processMsg != null)
			return false;

		MFMBatch reversal = reverseIt(false);
		if (reversal == null)
			return false;
		// After reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (m_processMsg != null)
			return false;
		//	
		return true;
	}			
	//	reverseCorrectionIt


	/**
	 * 	Reverse Accrual - none
	 * 	@return true if success
	 */
	public boolean reverseAccrualIt() {
		log.info("reverseAccrualIt - " + toString());
		// Before reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;

		MFMBatch reversal = reverseIt(true);
		if (reversal == null)
			return false;

		// After reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;
		
		return true;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return true if success 
	 */
	public boolean reActivateIt() {
		log.info("reActivateIt - " + toString());
		setProcessed(false);
		if (reverseCorrectIt())
			return true;
		return false;
	}	//	reActivateIt
	
	/**
	 * Set Processed to Line and header
	 */
	public void setProcessed(boolean processed) {
		super.setProcessed(processed);
		DB.executeUpdate("UPDATE FM_Transaction SET Processed = " + (processed? "'Y'": "'N'") 
				+ " WHERE FM_Batch_ID = ?", getFM_Batch_ID(), get_TrxName());
	}
	
	/**
	 *
	 * @param isAccrual
	 * @return
	 */
	public MFMBatch reverseIt(boolean isAccrual) {
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		Optional<Timestamp> loginDateOptional = Optional.of(Env.getContextAsDate(getCtx(),"#Date"));
		Timestamp reversalDate =  isAccrual ? loginDateOptional.orElse(currentDate) : getDateDoc();
		MPeriod.testPeriodOpen(getCtx(), reversalDate , getC_DocType_ID(), getAD_Org_ID());
		MFMBatch reversal = copyFrom(this, getDateDoc(), getC_DocType_ID());
		if (reversal == null) {
			return null;
		}

		reversal.setReversal_ID(getFM_Batch_ID());
		reversal.setProcessing (false);
		reversal.setDocStatus(DOCSTATUS_Reversed);
		reversal.setDocAction(DOCACTION_None);
		//	Update Header
		reversal.updateHeader();
		reversal.setProcessed(true);
		reversal.setProcessing(false);
		reversal.setPosted(false);
		reversal.saveEx(get_TrxName());
		setReversal_ID(reversal.getFM_Batch_ID());
		setDocStatus(DOCSTATUS_Reversed);	//	may come from void
		setDocAction(DOCACTION_None);
		setProcessed(true);
		setDocAction(DOCACTION_None);
		saveEx();
		//	Fact
//		reversal.processIt(ACTION_Post);
		return reversal;
	}
	
	/**
	 * Copy from
	 * @param from
	 * @param dateAcct
	 * @param documentTypeId
	 * @param counter
	 * @param trxName
	 * @param setOrder
	 * @return
	 */
	public static MFMBatch copyFrom (MFMBatch from, Timestamp dateAcct,
			int documentTypeId) {
		MFMBatch to = new MFMBatch (from.getCtx(), 0, from.get_TrxName());		
		PO.copyValues (from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
//		to.setReversal(true);
		to.set_ValueNoCheck ("DocumentNo", null);
		//
		to.setDocStatus (DOCSTATUS_Drafted);		//	Draft
		to.setDocAction(DOCACTION_Complete);
		//
		to.setC_DocType_ID(documentTypeId);
//		to.setDateAcct (dateAcct);
		//
		to.setPosted (false);
		to.setProcessed (false);
		to.setProcessing(false);
		to.saveEx();
		//	Lines
		to.copyLinesFrom(from);
		//	
		return to;
	}
	
	/**
	 * Copy Line from Movement
	 * @param from Human Resource Process
	 * @return return copy lines
	 */
	public int copyLinesFrom (MFMBatch from) {
		if (isProcessed() 
//				|| isPosted() 
				|| from == null)
			return 0;
		
		List<MFMTransaction> fromLines = from.getLines(false);
		for (MFMTransaction fromMovement: fromLines) {
			MFMTransaction toMovement = new MFMTransaction (getCtx(), 0, get_TrxName());
			PO.copyValues (fromMovement, toMovement, fromMovement.getAD_Client_ID(), fromMovement.getAD_Org_ID());
			//	
			toMovement.setFM_Batch_ID(getFM_Batch_ID());
			toMovement.setAmount(fromMovement.getAmount().negate());
			toMovement.setProcessed(false);
			toMovement.saveEx();		
		}		
		return fromLines.size();
	}	//	copyLinesFrom
	
	/**
	 * Get Lines
	 * @param requery
	 * @return
	 */
	public List<MFMTransaction> getLines(boolean requery) {
		if(requery
				|| lines == null) {
			lines = new Query(getCtx(), I_FM_Transaction.Table_Name, COLUMNNAME_FM_Batch_ID + "=" + getFM_Batch_ID(), get_TrxName())
				.<MFMTransaction>list();
		}
		//	Default
		return lines;
	}
	
	/**
	 * Get Lines
	 * @return
	 */
	public List<MFMTransaction> getLines() {
		return getLines(false);
	}
	
	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	public String getSummary()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
	//	sb.append(": ")
	//		.append(Msg.translate(getCtx(),"TotalLines")).append("=").append(getTotalLines())
	//		.append(" (#").append(getLines(false).length).append(")");
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append(" - ").append(getDescription());
		return sb.toString();
	}	//	getSummary

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
	 *	@return AD_User_ID
	 */
	public int getDoc_User_ID()
	{
	//	return getSalesRep_ID();
		return 0;
	}	//	getDoc_User_ID

	/**
	 * 	Get Document Approval Amount
	 *	@return amount
	 */
	public BigDecimal getApprovalAmt()
	{
		return null;	//getTotalLines();
	}	//	getApprovalAmt
	
	/**
	 * 	Get Document Currency
	 *	@return C_Currency_ID
	 */
	public int getC_Currency_ID()
	{
	//	MPriceList pl = MPriceList.get(getCtx(), getM_PriceList_ID());
	//	return pl.getC_Currency_ID();
		return 0;
	}	//	getC_Currency_ID
	
	/**
	 * 	get Document Type from Document Base Type
	 * 	@param docBaseType
	 */
	public void setC_DocType_ID () {
		int documentTypeId = MDocType.getDocType("FMB");
		if (documentTypeId <= 0) {
			log.severe ("Not found for AD_Client_ID=" + getAD_Client_ID () + ", DocBaseTypeType=" + "FMB");
		} else {
			log.fine("(SO) - " + "FMB");
			setC_DocType_ID(documentTypeId);
		}
	}	//	setC_DocTypeTarget_ID
	
	/**
	 * Add Transaction for batch
	 * This method assume that is the same currency of account, if you need convert to document use addTransaction(int transactionTypeId, int currenyId, int conversionTypeId, BigDecimal amount)
	 * @param transactionTypeId
	 * @param amount
	 */
	public MFMTransaction addTransaction(int transactionTypeId, BigDecimal amount) {
		return addTransaction(transactionTypeId, 0, 0, amount);
	}
	
	/**
	 * Add a transaction to batch with currency and optional conversion type
	 * @param transactionTypeId
	 * @param currenyId
	 * @param conversionTypeId
	 * @param amount
	 * @return
	 */
	public MFMTransaction addTransaction(int transactionTypeId, int currenyId, int conversionTypeId, BigDecimal amount) {
		//	Validate Type
		if(transactionTypeId <= 0) {
			return null;
		}
		//	
		MFMTransaction transaction = new MFMTransaction(this);
		transaction.setFM_TransactionType_ID(transactionTypeId);
		MFMAccount account = MFMAccount.getById(getCtx(), getFM_Account_ID(), get_TrxName());
		MCurrency currency = MCurrency.get(getCtx(), account.getC_Currency_ID());
		//	Convert it
		if(currency.getC_Currency_ID() != currenyId && currenyId > 0) {
			int conversionRateId = MConversionRate.getConversionRateId(currenyId, currency.getC_Currency_ID(), getDateAcct(), conversionTypeId, getAD_Client_ID(), getAD_Org_ID());
			if(conversionRateId <= 0) {
				throw new AdempiereException(MConversionRate.getErrorMessage(getCtx(), "ErrorConvertingDocumentCurrencyToBaseCurrency", currenyId, account.getC_Currency_ID(), conversionTypeId, getDateAcct(), get_TrxName()));
			}
			BigDecimal conversionRate = MConversionRate.getRate(getCtx(), conversionRateId);
			amount = amount.multiply(conversionRate);
		}
		//	Set values
		transaction.setAmount(amount.setScale(currency.getStdPrecision(), RoundingMode.HALF_UP));
		transaction.saveEx();
		return transaction;
	}
	
	@Override
	public int customizeValidActions(String docStatus, Object processing,
			String orderType, String isSOTrx, int AD_Table_ID,
			String[] docAction, String[] options, int index) {
		//	Valid Document Action
		if (AD_Table_ID == Table_ID){
			if (docStatus.equals(DocumentEngine.STATUS_Drafted)
					|| docStatus.equals(DocumentEngine.STATUS_InProgress)
					|| docStatus.equals(DocumentEngine.STATUS_Invalid)) {
					options[index++] = DocumentEngine.ACTION_Prepare;
				}
				//	Complete                    ..  CO
				else if (docStatus.equals(DocumentEngine.STATUS_Completed)) {
					options[index++] = DocumentEngine.ACTION_Reverse_Accrual;
					options[index++] = DocumentEngine.ACTION_Reverse_Correct;
				}
		}
		//	Default
		return index;
	}
	
    @Override
    public String toString()
    {
      StringBuffer sb = new StringBuffer ("MFMBatch[")
        .append(getSummary()).append("]");
      return sb.toString();
    }
}