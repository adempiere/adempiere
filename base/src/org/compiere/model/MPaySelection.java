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
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Util;

/**
 *	AP Payment Selection
 *	
 *  @author Jorg Janke
 *  @version $Id: MPaySelection.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 297 ] Payment Selection must be like ADempiere Document
 *		@see https://github.com/adempiere/adempiere/issues/297
 */
public class MPaySelection extends X_C_PaySelection implements DocAction, DocOptions {

    /**
     * 
     */
    private static final long serialVersionUID = -6521282913549455131L;
    
    /** Standard documents	*/
	public static final String		DocBaseType_Standard = "APS";

	/**
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param C_PaySelection_ID id
	 *	@param trxName transaction
	 */
	public MPaySelection (Properties ctx, int C_PaySelection_ID, String trxName)
	{
		super(ctx, C_PaySelection_ID, trxName);
		if (C_PaySelection_ID == 0)
		{
		//	setC_BankAccount_ID (0);
		//	setName (null);	// @#Date@
		//	setPayDate (new Timestamp(System.currentTimeMillis()));	// @#Date@
			setTotalAmt (Env.ZERO);
			setIsApproved (false);
			setProcessed (false);
		}
	}	//	MPaySelection

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MPaySelection(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MPaySelection

	/**	Lines						*/
	private MPaySelectionLine[]	m_lines = null;
	
	/**
	 * 	Get Lines
	 *	@param requery requery
	 *	@return lines
	 */
	public MPaySelectionLine[] getLines(boolean requery)
	{
		if (m_lines != null && !requery) {
			set_TrxName(m_lines, get_TrxName());
			return m_lines;
		}
		//	
		return getLines(null, null);
	}	//	getLines
	
	/**
	 * Set Document Type
	 */
	public void setC_DocType_ID() {
		String sql = "SELECT C_DocType_ID FROM C_DocType "
			+ "WHERE AD_Client_ID = ? AND AD_Org_ID IN (0," + getAD_Org_ID()
			+ ") AND DocBaseType = ? "
			+ " AND IsActive = 'Y' "
			+ "ORDER BY AD_Org_ID, IsDefault DESC";
		int C_DocType_ID = DB.getSQLValue(null, sql, getAD_Client_ID(), DocBaseType_Standard);
		if (C_DocType_ID <= 0) {
			log.severe ("Not found for AD_Client_ID=" + getAD_Client_ID () + ", DocBaseType=" + DocBaseType_Standard);
		} else {
			log.fine("(APS) - " + DocBaseType_Standard);
			setC_DocType_ID(C_DocType_ID);
		}
	}	//	setC_DocTypeTarget_ID
	
	/**
	 * Get Lines with where clause and order by clause, re-query
	 * @param whereClause
	 * @param orderClause
	 * @return
	 */
	public MPaySelectionLine[] getLines(String whereClause, String orderClause) {
		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		StringBuffer whereClauseFinal = new StringBuffer(I_C_PaySelection.COLUMNNAME_C_PaySelection_ID+" = ? ");
		if (!Util.isEmpty(whereClause, true))
			whereClauseFinal.append(whereClause);
		if (Util.isEmpty(orderClause, true))
			orderClause = I_C_PaySelectionLine.COLUMNNAME_Line;
		List <MPaySelectionLine> list = new Query(getCtx(), 
				I_C_PaySelectionLine.Table_Name, whereClauseFinal.toString(), get_TrxName())
			.setParameters(getC_PaySelection_ID())
			.setOrderBy(orderClause)
			.list();
		//
		m_lines = new MPaySelectionLine[list.size ()];
		list.toArray (m_lines);
		return m_lines;
	}
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MPaySelection[");
		sb.append(get_ID()).append(",").append(getDocumentInfo())
			.append("]");
		return sb.toString();
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
	/**	Cache for validation for Lines Paid		*/
	private HashMap<Integer, Boolean> 	m_LinesPaid = null;
	/**	Cache for validation for Lines Used		*/
	private HashMap<Integer, Boolean> 	m_LinesUsed = null;
	/**	Paid completely							*/
	private boolean		m_IsPaidCompletely = false;
	/**	Used completely							*/
	private boolean		m_IsUsedCompletely = false;
	/**	Is Paid									*/
	private boolean 	m_IsPaid = false;
	/**	Is Used									*/
	private boolean 	m_IsUsed = false;
	
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
		//	Validate Amount
		if(getTotalAmt() == null
				|| getTotalAmt().equals(Env.ZERO)) {
			m_processMsg = "@TotalAmt@ = 0";
			return DocAction.STATUS_Invalid;
		}
		//	Validate conversion
		String erroMsg = validateConversion();
		if(erroMsg != null) {
			m_processMsg = erroMsg;
			return DocAction.STATUS_Invalid;
		}
		//	Create Checks
		createChecks();
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		//	Set Definitive Document No
		setDefiniteDocumentNo();

		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt
	
	/**
	 * Set Processed to Line and header
	 */
	public void setProcessed(boolean processed) {
		super.setProcessed(processed);
		//	Change Processed in line
		for(MPaySelectionLine line : getLines(true)) {
			if(!processed
					&& (isUsed(line.getC_PaySelectionLine_ID()) || isPaid(line.getC_PaySelectionLine_ID())))
				continue;
			//	
			line.setProcessed(processed);
			line.saveEx();
		}
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

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(is_ValueChanged("C_Currency_ID")
				|| is_ValueChanged("PayDate")) {
			int retValue = DB.getSQLValue(get_TrxName(), "SELECT 1 "
					+ "FROM C_PaySelection s "
					+ "INNER JOIN C_PaySelectionLine l ON(l.C_PaySelection_ID = s.C_PaySelection_ID) "
					+ "WHERE s.C_PaySelection_ID = ? "
					+ "AND ("
					+ "		(l.C_Order_ID IS NOT NULL AND EXISTS(SELECT 1 FROM C_Order o WHERE o.C_Order_ID = l.C_Order_ID AND o.C_Currency_ID <> s.C_Currency_ID)) "
					+ "		OR "
					+ "		(l.C_Invoice_ID IS NOT NULL AND EXISTS(SELECT 1 FROM C_Invoice i WHERE i.C_Invoice_ID = l.C_Invoice_ID AND i.C_Currency_ID <> s.C_Currency_ID))"
					+ ")", getC_PaySelection_ID());
			//	Validate if exists documents in other currency
			if(retValue > 0) {
				throw new AdempiereException("@PSDocConverted@ (@C_Currency_ID@)");
			}
		}
		return super.beforeSave(newRecord);
	}
	
	/**
	 * 	Void Document.
	 * 	Same as Close.
	 * 	@return true if success 
	 */
	public boolean voidIt()
	{
		log.info("voidIt - " + toString());
		//	Valid if is used
		if(isUsed())
			throw new AdempiereException("@C_PaySelection_ID@ @Processed@");
		//	Valid if is paid
		if(isPaid())
			throw new AdempiereException("@C_PaySelection_ID@ @IsPaid@");
		//	
		setProcessed(true);
		setDocAction(DOCACTION_None);
		setDocStatus(DOCSTATUS_Voided);
		return closeIt();
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
		//	Valid if is completely used
		if(isCompletelyUsed())
			throw new AdempiereException("@C_PaySelection_ID@ @Processed@");
		//	Valid if is completely paid
		if(isCompletelyPaid())
			throw new AdempiereException("@C_PaySelection_ID@ @IsPaid@");
		//	
		setProcessed(false);
		//	Delete check
		deleteChecks();
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
	 * Validate conversion of line
	 */
	private String validateConversion() {
		StringBuffer errorMsg = new StringBuffer();
		MPaySelectionLine [] lines = getLines(null, "C_BPartner_ID, PaymentRule, "
				+ "C_BP_BankAccount_ID, IsPrepayment, C_Charge_ID");
		//	Try to verify
		for (MPaySelectionLine line : lines) {
			//	Ignore processed
			if(line.isProcessed())
				continue;
			//	
			if(!line.isValidConversion()) {
				//	Add new line
				if(errorMsg.length() > 0)
					errorMsg.append(Env.NL);
				//	
				MBPartner partner = MBPartner.get(getCtx(), line.getC_BPartner_ID());
				errorMsg
					.append("@C_Conversion_Rate_ID@ @NotFound@ [@Line@ ").append(line.getLine())
					.append(" @C_BPartner_ID@ ").append(partner.getName()).append("]");
			}
		}
		//	Return
		if(errorMsg.length() > 0)
			return errorMsg.toString();
		//	Default
		return null;
	}
	
	/**
	 * 	Create Check from line
	 *	@param line
	 *	@throws Exception for invalid bank accounts
	 *	FR [ 297 ] Copied from org.compiere.process.PaySelectionCreateCheck
	 */
	private void createChecks() {
		//	Don't have account
		if(getC_BankAccount_ID() == 0)
			return;
		//	Values
		int m_C_Charge_ID = 0;
		boolean isPrepayment = false;
		MPaySelectionCheck check = null;
		MPaySelectionLine [] lines = getLines(null, "C_BPartner_ID, PaymentRule, "
				+ "C_BP_BankAccount_ID, IsPrepayment, C_Charge_ID");
		//	Try to find one
		for (MPaySelectionLine line : lines) {
			//	already check reference
			if(line.getC_PaySelectionCheck_ID() != 0)
				continue;
			//	Instance new
			if(check == null
					|| check.getC_BPartner_ID() != line.getC_BPartner_ID()
					|| check.getC_BP_BankAccount_ID() != line.getC_BP_BankAccount_ID()
					|| !check.getPaymentRule().equals(line.getPaymentRule())
					|| m_C_Charge_ID != line.getC_Charge_ID()
					|| isPrepayment != line.isPrepayment()) {
				check = new MPaySelectionCheck(line, line.getPaymentRule());
				//	Set current values
				m_C_Charge_ID = line.getC_Charge_ID();
				isPrepayment = line.isPrepayment();
			} else {
				check.addLine(line);
			}
			//	Save and reference
			check.saveEx();
			line.setC_PaySelectionCheck_ID(check.getC_PaySelectionCheck_ID());
			line.saveEx();
		}
	}	//	createCheck
	
	/**
	 * Delete Checks if the payments is not generated
	 */
	private void deleteChecks() {
		MPaySelectionCheck [] m_checks = MPaySelectionCheck.get(getCtx(), getC_PaySelection_ID(), get_TrxName());
		for(MPaySelectionCheck check : m_checks) {
			//	Valid Payment
			if(check.getC_Payment_ID() != 0) {
				MPayment payment = new MPayment(getCtx(), check.getC_Payment_ID(), get_TrxName());
				if(!payment.getDocStatus().equals(X_C_Payment.DOCSTATUS_Voided)
						&& !payment.getDocStatus().equals(X_C_Payment.DOCSTATUS_Reversed)) {
					continue;
				}
			}
			//	Update reference
			DB.executeUpdate("UPDATE C_PaySelectionLine "
					+ "SET C_PaySelectionCheck_ID = NULL "
					+ "WHERE C_PaySelectionCheck_ID = ?", check.getC_PaySelectionCheck_ID(), get_TrxName());
			//	Delete Check
			check.deleteEx(true);
		}
	}
	
	/**
	 * Verify if is not paid
	 */
	private void verifyIsPaid() {
		if(m_LinesPaid != null)
			return;
		//	Get
		m_LinesPaid = new HashMap<Integer, Boolean>();
		//	Get from Validation
		KeyNamePair [] pairs = DB.getKeyNamePairs(
				"SELECT psl.C_PaySelectionLine_ID, "
				+ "(CASE "
				+ "		WHEN COALESCE("
				+ "					SUM(CASE "
				+ "							WHEN p.DocStatus NOT IN('VO', 'RE') "
				+ "							THEN 1 "
				+ "							ELSE 0 "
				+ "							END"
				+ "					), 0"
				+ "			) > 0 "
				+ "		THEN 'Y' "
				+ "		ELSE 'N' "
				+ "		END"
				+ ") IsPaid "
				+ "FROM C_PaySelectionLine psl "
				+ "LEFT JOIN C_PaySelectionCheck psc ON(psc.C_PaySelectionCheck_ID = psl.C_PaySelectionCheck_ID) "
				+ "LEFT JOIN C_Payment p ON(p.C_Payment_ID = psc.C_Payment_ID) "
				+ "WHERE psl.C_PaySelection_ID = ? "
				+ "GROUP BY psl.C_PaySelectionLine_ID", false, getC_PaySelection_ID());
		//	Get Values
		boolean paidCompletely = true;
		boolean paid = false;
		for(KeyNamePair pair : pairs) {
			m_LinesPaid.put(pair.getKey(), pair.getName().equals("Y"));
			//	Fill Use
			if(!pair.getName().equals("Y")
					&& paidCompletely) {
				paidCompletely = false;
			} else if(pair.getName().equals("Y")
					&& !paid) {
				paid = true;
			}
		}
		//	
		m_IsPaidCompletely = paidCompletely;
		m_IsPaid = paid;
	}
	
	/**
	 * Verify if is used
	 */
	private void verifyIsUsed() {
		if(m_LinesUsed != null)
			return;
		//	Get
		m_LinesUsed = new HashMap<Integer, Boolean>();
		//	Get from Validation
		KeyNamePair [] pairs = DB.getKeyNamePairs(
				"SELECT psl.C_PaySelectionLine_ID, "
				+ "(CASE "
				+ "		WHEN COALESCE("
				+ "					SUM(CASE "
				+ "							WHEN cps.DocStatus NOT IN('VO', 'RE') "
				+ "							THEN 1 "
				+ "							ELSE 0 "
				+ "							END"
				+ "					), 0"
				+ "			) > 0 "
				+ "		THEN 'Y' "
				+ "		ELSE 'N' "
				+ "		END"
				+ ") IsUsed "
				+ "FROM C_PaySelectionLine psl "
				+ "LEFT JOIN C_PaySelectionLine cpsl ON(cpsl.C_PaySelectionLine_Parent_ID = psl.C_PaySelectionLine_ID) "
				+ "LEFT JOIN C_PaySelection cps ON(cps.C_PaySelection_ID = cpsl.C_PaySelection_ID) "
				+ "WHERE psl.C_PaySelection_ID = ? "
				+ "GROUP BY psl.C_PaySelectionLine_ID", false, getC_PaySelection_ID());
		//	Get Values
		boolean usedCompletely = true;
		boolean used = false;
		for(KeyNamePair pair : pairs) {
			m_LinesUsed.put(pair.getKey(), pair.getName().equals("Y"));
			//	Fill Use
			if(!pair.getName().equals("Y")
					&& usedCompletely) {
				usedCompletely = false;
			} else if(pair.getName().equals("Y")
					&& !used) {
				used = true;
			}
		}
		//	
		m_IsUsedCompletely = usedCompletely;
		m_IsUsed = used;
	}
	
	/**
	 * Verify if is completely payment
	 * @return
	 */
	public boolean isCompletelyPaid() {
		//	Load
		verifyIsPaid();
		return m_IsPaidCompletely;
	}
	
	/**
	 * Verify if is completely used by other payment selection
	 * @return
	 */
	public boolean isCompletelyUsed() {
		//	Load
		verifyIsUsed();
		return m_IsUsedCompletely;
	}
	
	/**
	 * Verify if is paid
	 * @return
	 */
	public boolean isPaid() {
		verifyIsPaid();
		return m_IsPaid;
	}
	
	/**
	 * Verify if is used
	 * @return
	 */
	public boolean isUsed() {
		verifyIsUsed();
		return m_IsUsed;
	}
	
	/**
	 * Verify if a line is paid
	 * @param C_PaySelectionLine_ID
	 * @return
	 */
	private boolean isPaid(int C_PaySelectionLine_ID) {
		//	Load
		verifyIsPaid();
		Boolean isPaid = m_LinesPaid.get(C_PaySelectionLine_ID);
		//	
		if(isPaid == null)
			return false;
		//	default not nul
		return isPaid;
	}
	
	/**
	 * Verify if a line is used
	 * @param C_PaySelectionLine_ID
	 * @return
	 */
	private boolean isUsed(int C_PaySelectionLine_ID) {
		//	Load
		verifyIsUsed();
		Boolean isUsed = m_LinesUsed.get(C_PaySelectionLine_ID);
		//	
		if(isUsed == null)
			return false;
		//	default not nul
		return isUsed;
	}
	
	/**
	 * Get Last line No
	 * @return
	 */
	public int getLastLineNo() {
		int lastLineNo = DB.getSQLValue(get_TrxName(), 
				"SELECT MAX(psl.Line) As Line "
				+ "FROM C_PaySelectionLine psl "
				+ "WHERE psl.C_PaySelection_ID = ?", getC_PaySelection_ID());
		if(lastLineNo == -1)
			lastLineNo = 0;
		//	Return
		return lastLineNo;
	}
	
}	//	MPaySelection
