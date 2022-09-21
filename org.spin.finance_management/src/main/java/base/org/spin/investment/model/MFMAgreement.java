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
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_FM_AccountProduct;
import org.adempiere.core.domains.models.X_FM_Agreement;
import org.adempiere.core.domains.models.X_I_FM_Agreement;
import org.compiere.model.*;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.spin.investment.util.FinancialSetting;

/** Generated Model for FM_Agreement
 *  @author Adempiere (generated) 
 *  @version Release 3.9.0 - $Id$ */
public class MFMAgreement extends X_FM_Agreement implements DocAction, DocOptions {

	/**
	 *
	 */
	private static final long serialVersionUID = 20180228L;

    /** Standard Constructor */
    public MFMAgreement (Properties ctx, int FM_Agreement_ID, String trxName)
    {
      super (ctx, FM_Agreement_ID, trxName);
    }

    /** Load Constructor */
    public MFMAgreement (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
    
    /** Static Cache */
	private static CCache<Integer, MFMAgreement> agreementCacheIds = new CCache<Integer, MFMAgreement>(Table_Name, 30);
	
	
	/**
	 * Get/Load Functional Product [CACHED]
	 * @param ctx context
	 * @param agreementId
	 * @return activity or null
	 */
	public static MFMAgreement getById(Properties ctx, int agreementId, String trxName) {
		if (agreementId <= 0)
			return null;

		MFMAgreement agreement = agreementCacheIds.get(agreementId);
		if (agreement != null && agreement.get_ID() > 0)
			return agreement;

		agreement = new Query(ctx , Table_Name , COLUMNNAME_FM_Agreement_ID + "=?" , trxName)
				.setClient_ID()
				.setParameters(agreementId)
				.first();
		if (agreement != null && agreement.get_ID() > 0) {
			agreementCacheIds.put(agreement.get_ID(), agreement);
		}
		return agreement;
	}
	
	/**
	 * Get By ID
	 * @param ctx
	 * @param agreementId
	 * @return
	 */
	public static MFMAgreement getById(Properties ctx, int agreementId) {
		return getById(ctx, agreementId, null);
	}
	
    
    /**
     * Instance from import
     * @param agreementImport
     */
    public MFMAgreement(X_I_FM_Agreement agreementImport) {
    	super(agreementImport.getCtx(), agreementImport.getFM_Agreement_ID(), agreementImport.get_TrxName());
    	//	Set Default Values
    	setDocumentNo(agreementImport.getDocumentNo());
    	setC_BPartner_ID(agreementImport.getC_BPartner_ID());
    	setFM_Product_ID(agreementImport.getFM_Product_ID());
    	setDescription(agreementImport.getDescription());
    	setText(agreementImport.getText());
    	setC_DocType_ID(agreementImport.getC_DocType_ID());
    	setFM_AgreementType_ID(agreementImport.getFM_AgreementType_ID());
    	setDateDoc(agreementImport.getDateDoc());
    	//	Valid From
    	if(agreementImport.getValidFrom() == null) {
    		setValidFrom(agreementImport.getDateDoc());
    	} else {
    		setValidFrom(agreementImport.getValidFrom());
    	}
    	//	Valid To
    	if(agreementImport.getValidTo() == null) {
    		setValidTo(agreementImport.getDateDoc());
    	} else {
    		setValidTo(agreementImport.getValidTo());
    	}
    	setIsSOTrx(agreementImport.isSOTrx());
    }
    
    
    private List<MFMAccountProduct> products = null;

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
		//	Financial Setting
		for(MFMAccountProduct products : getAccountProducts()) {
			m_processMsg = FinancialSetting.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE, products.getFM_Product_ID());
			if (m_processMsg != null)
				return DocAction.STATUS_Invalid;
		}
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
		//	Financial Setting
		for(MFMAccountProduct products : getAccountProducts()) {
			m_processMsg = FinancialSetting.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE, products.getFM_Product_ID());
			if (m_processMsg != null)
				return DocAction.STATUS_Invalid;
		}
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
		//	Financial Setting
		for(MFMAccountProduct products : getAccountProducts()) {
			m_processMsg = FinancialSetting.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE, products.getFM_Product_ID());
			if (m_processMsg != null)
				return DocAction.STATUS_Invalid;
		}
		//	Implicit Approval
		if (!isApproved())
			approveIt();
		log.info(toString());
		//
		
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		//	Financial Setting
		for(MFMAccountProduct products : getAccountProducts()) {
			m_processMsg = FinancialSetting.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE, products.getFM_Product_ID());
			if (m_processMsg != null)
				return DocAction.STATUS_Invalid;
		}
		//	Set Definitive Document No
		setDefiniteDocumentNo();

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
		// Before Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (m_processMsg != null)
			return false;
		
		if (DOCSTATUS_Closed.equals(getDocStatus())
			|| DOCSTATUS_Reversed.equals(getDocStatus())
			|| DOCSTATUS_Voided.equals(getDocStatus())) {
			m_processMsg = "Document Closed: " + getDocStatus();
			return false;
		}	
		// After Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (m_processMsg != null)
			return false;		
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
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
	 * 	Reverse Correction
	 * 	@return true if success 
	 */
	public boolean reverseCorrectIt()
	{
		log.info("reverseCorrectIt - " + toString());
		return false;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual - none
	 * 	@return true if success 
	 */
	public boolean reverseAccrualIt()
	{
		log.info("reverseAccrualIt - " + toString());
		return false;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return true if success 
	 */
	public boolean reActivateIt()
	{
		log.info("reActivateIt - " + toString());
		setProcessed(false);
		setDocAction(DOCACTION_Complete);
		//if (reverseCorrectIt())
			//return true;
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

    @Override
    public String toString()
    {
      StringBuffer sb = new StringBuffer ("MFMAgreement[")
        .append(getSummary()).append("]");
      return sb.toString();
    }
    
    @Override
    protected boolean beforeSave(boolean newRecord) {
    	if(newRecord && Util.isEmpty(getText())) {
    		if(getFM_AgreementType_ID() != 0) {
        		MFMAgreementType agreementType = MFMAgreementType.getById(getCtx(), getFM_AgreementType_ID());
        		if(agreementType.getText() != null) {
        			setText(agreementType.getText());
        		}
        	}
    	}
    	return super.beforeSave(newRecord);
    }
    
    /**
     * Get Accounts
     * @return
     */
    public List<MFMAccount> getAccounts() {
    	return MFMAccount.getAccountFromAgreement(this);
    }
    
    /**
     * Get Account Products
     * @return
     */
    public List<MFMAccountProduct> getAccountProducts() {
    	if(products != null) {
    		return products;
    	}
    	//	
    	products = new Query(getCtx(), I_FM_AccountProduct.Table_Name, "EXISTS(SELECT 1 FROM FM_Account a "
    			+ "WHERE a.FM_Account_ID = FM_AccountProduct.FM_Account_ID "
    			+ "AND a.FM_Agreement_ID = ?)", get_TrxName())
				.setClient_ID()
				.setParameters(getFM_Agreement_ID())
				.setOnlyActiveRecords(true)
				.<MFMAccountProduct>list();
    	// return 
    	return products;
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
					options[index++] = DocumentEngine.ACTION_ReActivate;
					options[index++] = DocumentEngine.ACTION_Void;
				}
		}
		//	Default
		return index;
	}
    
    /**
     * Add any Agreement Type
     */
    public void setFM_AgreementType_ID() {
    	MFMAgreementType agreementType = MFMAgreementType.getDefault(Env.getCtx());
    	if(agreementType != null) {
    		setFM_AgreementType_ID(agreementType.getFM_AgreementType_ID());
    	}
    }
    
	/**
	 * 	get Document Type from Document Base Type
	 * 	@param docBaseType
	 */
	public void setC_DocType_ID (String docBaseType) {
		int documentTypeId = MDocType.getDocType(docBaseType);
		if (documentTypeId <= 0) {
			log.severe ("Not found for AD_Client_ID=" + getAD_Client_ID () + ", DocBaseTypeType=" + docBaseType);
		} else {
			log.fine("(SO) - " + docBaseType);
			setC_DocType_ID(documentTypeId);
		}
	}	//	setC_DocTypeTarget_ID
}