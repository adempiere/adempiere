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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.PeriodClosedException;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentReversalEnabled;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Payment Allocation Model.
 * 	Allocation Trigger update C_BPartner
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: MAllocationHdr.java,v 1.3 2006/07/30 00:51:03 jjanke Exp $
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 *  			<li>FR [ 1866214 ]  
 *				<li> http://sourceforge.net/tracker/index.php?func=detail&aid=1866214&group_id=176962&atid=879335
 * 				<li>FR [ 2520591 ] Support multiples calendar for Org 
 *				<li> http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962 
 *				<li>BF [ 2880182 ] Error you can allocate a payment to invoice that was paid
 *				<li> https://sourceforge.net/tracker/index.php?func=detail&aid=2880182&group_id=176962&atid=879332
 *				<li>Implement Reverse Accrual for all document https://github.com/adempiere/adempiere/issues/1348
 *				<li>Add document type for Payment Allocation #1469 https://github.com/adempiere/adempiere/issues/1469
*/
public final class MAllocationHdr extends X_C_AllocationHdr implements DocAction , DocumentReversalEnabled
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8726957992840702609L;

	private static final BigDecimal	TOLERANCE = BigDecimal.valueOf(0.02);


	/**
	 * 	Get Allocations of Payment
	 *	@param ctx context
	 *	@param C_Payment_ID payment
	 *	@return allocations of payment
	 *	@param trxName transaction
	 */
	public static MAllocationHdr[] getOfPayment (Properties ctx, int C_Payment_ID, String trxName)
	{
		String sql = "SELECT * FROM C_AllocationHdr h "
			+ "WHERE IsActive='Y'"
			+ " AND EXISTS (SELECT * FROM C_AllocationLine l "
				+ "WHERE h.C_AllocationHdr_ID=l.C_AllocationHdr_ID AND l.C_Payment_ID=?)";
		ArrayList<MAllocationHdr> list = new ArrayList<MAllocationHdr>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, C_Payment_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add (new MAllocationHdr(ctx, rs, trxName));
		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MAllocationHdr[] retValue = new MAllocationHdr[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getOfPayment

	/**
	 * 	Get Allocations of Invoice
	 *	@param ctx context
	 *	@param C_Invoice_ID payment
	 *	@return allocations of payment
	 *	@param trxName transaction
	 */
	public static MAllocationHdr[] getOfInvoice (Properties ctx, int C_Invoice_ID, String trxName)
	{
		String sql = "SELECT * FROM C_AllocationHdr h "
			+ "WHERE IsActive='Y'"
			+ " AND EXISTS (SELECT * FROM C_AllocationLine l "
				+ "WHERE h.C_AllocationHdr_ID=l.C_AllocationHdr_ID AND l.C_Invoice_ID=?)";
		ArrayList<MAllocationHdr> list = new ArrayList<MAllocationHdr>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, C_Invoice_ID);
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add (new MAllocationHdr(ctx, rs, trxName));
		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		MAllocationHdr[] retValue = new MAllocationHdr[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getOfInvoice
	
	//FR [ 1866214 ]
	/**
	 * 	Get Allocations of Cash
	 *	@param ctx context
	 *	@param C_Cash_ID Cash ID
	 *	@return allocations of payment
	 *	@param trxName transaction
	 */
	public static MAllocationHdr[] getOfCash (Properties ctx, int C_Cash_ID, String trxName)
	{
		final String whereClause = "IsActive='Y'"
			+ " AND EXISTS (SELECT 1 FROM C_CashLine cl, C_AllocationLine al "
				+ "where cl.C_Cash_ID=? and al.C_CashLine_ID=cl.C_CashLine_ID "
						+ "and C_AllocationHdr.C_AllocationHdr_ID=al.C_AllocationHdr_ID)";
		Query query = MTable.get(ctx, I_C_AllocationHdr.Table_ID)
							.createQuery(whereClause, trxName);
		query.setParameters(C_Cash_ID);
		List<MAllocationHdr> list = query.list();
		MAllocationHdr[] retValue = new MAllocationHdr[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getOfCash
	
	/**	Logger						*/
	private static CLogger logger = CLogger.getCLogger(MAllocationHdr.class);
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_AllocationHdr_ID id
	 *	@param trxName transaction
	 */
	public MAllocationHdr (Properties ctx, int C_AllocationHdr_ID, String trxName)
	{
		super (ctx, C_AllocationHdr_ID, trxName);
		if (C_AllocationHdr_ID == 0)
		{
		//	setDocumentNo (null);
			setDateTrx (new Timestamp(System.currentTimeMillis()));
			setDateAcct (getDateTrx());
			setDocAction (DOCACTION_Complete);	// CO
			setDocStatus (DOCSTATUS_Drafted);	// DR
		//	setC_Currency_ID (0);
			setApprovalAmt (Env.ZERO);
			setIsApproved (false);
			setIsManual (false);
			//
			setPosted (false);
			setProcessed (false);
			setProcessing(false);
		}
	}	//	MAllocation

	/**
	 * 	Mandatory New Constructor
	 *	@param ctx context
	 *	@param IsManual manual trx
	 *	@param DateTrx date (if null today)
	 *	@param C_Currency_ID currency
	 *	@param description description
	 *	@param trxName transaction
	 */
	public MAllocationHdr (Properties ctx, boolean IsManual, Timestamp DateTrx, 
		int C_Currency_ID, String description, String trxName)
	{
		this (ctx, 0, trxName);
		setIsManual(IsManual);
		if (DateTrx != null)
		{
			setDateTrx (DateTrx);
			setDateAcct (DateTrx);
		}
		setC_Currency_ID (C_Currency_ID);
		if (description != null)
			setDescription(description);
	}	//  create Allocation

	/** 
	 * 	Load Constructor
	 * 	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MAllocationHdr (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MAllocation

	/**	Lines						*/
	private MAllocationLine[]	m_lines = null;
	
	/**
	 * 	Get Lines
	 *	@param requery if true requery
	 *	@return lines
	 */
	public MAllocationLine[] getLines (boolean requery)
	{
		if (m_lines != null && m_lines.length != 0 && !requery) {
			set_TrxName(m_lines, get_TrxName());
			return m_lines;
		}
		//
		String sql = "SELECT * FROM C_AllocationLine WHERE C_AllocationHdr_ID=?";
		ArrayList<MAllocationLine> list = new ArrayList<MAllocationLine>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getC_AllocationHdr_ID());
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MAllocationLine line = new MAllocationLine(getCtx(), rs, get_TrxName());
				line.setParent(this);
				list.add (line);
			}
		} 
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		m_lines = new MAllocationLine[list.size ()];
		list.toArray (m_lines);
		return m_lines;
	}	//	getLines

	/**
	 * 	Set Processed
	 *	@param processed Processed
	 */
	public void setProcessed (boolean processed)
	{
		super.setProcessed (processed);
		if (get_ID() == 0)
			return;
		String sql = "UPDATE C_AllocationHdr SET Processed='"
			+ (processed ? "Y" : "N")
			+ "' WHERE C_AllocationHdr_ID=" + getC_AllocationHdr_ID();
		int no = DB.executeUpdate(sql, get_TrxName());
		m_lines = null;
		log.fine(processed + " - #" + no);
	}	//	setProcessed
	
	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord
	 *	@return save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (getC_DocType_ID() <= 0)
		{
			Optional<MDocType> doctypeOptional = Arrays.stream(MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_PaymentAllocation))
					.sorted((docType1, docType2) -> Boolean.compare(docType2.isDefault(), docType1.isDefault()))
					.findFirst();
			doctypeOptional.ifPresent(docType -> setC_DocType_ID(docType.getC_DocType_ID()));
			if (getC_DocType_ID() <= 0)
				throw new AdempiereException("@C_DocType_ID@ @FillMandatory@");

		}

		//	Changed from Not to Active
		if (!newRecord && is_ValueChanged("IsActive") && isActive())
		{
			log.severe ("Cannot Re-Activate deactivated Allocations");
			return false;
		}
		return true;
	}	//	beforeSave

	/**
	 * 	Before Delete.
	 *	@return true if acct was deleted
	 */
	protected boolean beforeDelete ()
	{
		String trxName = get_TrxName();
		if (trxName == null || trxName.length() == 0)
			log.warning ("No transaction");
		if (isPosted())
		{
			MPeriod.testPeriodOpen(getCtx(), getDateTrx(), MDocType.DOCBASETYPE_PaymentAllocation, getAD_Org_ID());
			setPosted(false);
			MFactAcct.deleteEx (Table_ID, get_ID(), trxName);
		}
		//	Mark as Inactive
		setIsActive(false);		//	updated DB for line delete/process
		String sql = "UPDATE C_AllocationHdr SET IsActive='N' WHERE C_AllocationHdr_ID=?";
		DB.executeUpdate(sql, getC_AllocationHdr_ID(), trxName);
		
		//	Unlink
		getLines(true);
		if (!updateBP(true))
			return false;

		Arrays.stream(getLines(false))
				.forEach( allocationLine -> allocationLine.deleteEx(true) );
		return true;
	}	//	beforeDelete

	/**
	 * 	After Save
	 *	@param newRecord
	 *	@param success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		return success;
	}	//	afterSave
	
	/**************************************************************************
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	public boolean processIt (String processAction)
	{
		processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	processIt
	
	/**	Process Message 			*/
	private String processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean justPrepared = false;

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
		processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (processMsg != null)
			return DocAction.STATUS_Invalid;

		//	Std Period open?
		MPeriod.testPeriodOpen(getCtx(), getDateAcct(), MDocType.DOCBASETYPE_PaymentAllocation, getAD_Org_ID());
		getLines(false);
		if (m_lines.length == 0)
		{
			processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}

		List<MAllocationLine> allocationLines = Arrays.asList(getLines(false));
		// Stop the Document Workflow if invoice to allocate is as paid
		if (!isReversal()) {
			allocationLines.stream()
					.filter(allocationLine -> allocationLine.getC_Invoice_ID() != 0)
					.forEach(allocationLine -> {
						final String whereClause = I_C_Invoice.COLUMNNAME_C_Invoice_ID + "=? AND "
								+ I_C_Invoice.COLUMNNAME_IsPaid + "=? AND "
								+ I_C_Invoice.COLUMNNAME_DocStatus + " NOT IN (?,?)";
						boolean InvoiceIsPaid = new Query(getCtx(), I_C_Invoice.Table_Name, whereClause, get_TrxName())
								.setClient_ID()
								.setParameters(allocationLine.getC_Invoice_ID(), "Y", X_C_Invoice.DOCSTATUS_Voided, X_C_Invoice.DOCSTATUS_Reversed)
								.match();
						if (InvoiceIsPaid)
							throw new AdempiereException("@ValidationError@ @C_Invoice_ID@ @IsPaid@");
					});
		}

		//	Add up Amounts & validate
		AtomicReference<BigDecimal> approval = new AtomicReference<>(Env.ZERO);
		allocationLines.stream()
				.forEach(allocationLine -> {
								approval.updateAndGet(approvalAmount -> approvalAmount.add(allocationLine.getWriteOffAmt()).add(allocationLine.getDiscountAmt()));
								if (allocationLine.getC_BPartner_ID() == 0) {
									processMsg = Msg.parseTranslation(getCtx(), "@C_BPartner_ID@ @NotFound@");
									throw new AdempiereException(processMsg);
								}
								if (allocationLine.getC_Invoice_ID() > 0) {
									I_C_Invoice invoice = allocationLine.getC_Invoice();
									if (invoice.getDateAcct().after(getDateAcct())) {
										processMsg = Msg.parseTranslation(getCtx(), "@ValidationError@  "
												+ " @C_Invoice_ID@ " + invoice.getDocumentNo() + " @DateAcct@" + invoice.getDateAcct()
												+ " @C_AllocationHdr_ID@ " + getDocumentInfo() + " @DateAcct@ " + getDateAcct());
										throw new AdempiereException(processMsg);
									}
								}

								if (allocationLine.getC_Payment_ID() > 0) {
									I_C_Payment payment = allocationLine.getC_Payment();
									if (payment.getDateAcct().after(getDateAcct())) {
										processMsg = Msg.parseTranslation(getCtx(), "@ValidationError@ "
												+ " @C_Payment_ID@ " + payment.getDocumentNo() + " @DateAcct@" + payment.getDateAcct()
												+ " @C_AllocationHdr_ID@ " + getDocumentInfo() + " @DateAcct@ " + getDateAcct());
										throw new AdempiereException(processMsg);
									}
								}
							});

		setApprovalAmt(approval.get());
		//
		processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (processMsg != null)
			return DocAction.STATUS_Invalid;
		
		justPrepared = true;
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
		if (!justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}

		processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (processMsg != null)
			return DocAction.STATUS_Invalid;
		
		//	Implicit Approval
		if (!isApproved())
			approveIt();
		log.info(toString());

		//	Link
		getLines(false);
		if(!updateBP(isReversal()))
			return DocAction.STATUS_Invalid;

		Arrays.stream(getLines(false))
				.forEach(allocationLine ->
						 allocationLine.processIt(isReversal()));

		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			processMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt
	
	/**
	 * 	Void Document.
	 * 	Same as Close.
	 * 	@return true if success 
	 */
	public boolean voidIt()
	{
		log.info(toString());
		boolean retValue = false;
		if (DOCSTATUS_Closed.equals(getDocStatus())
				|| DOCSTATUS_Reversed.equals(getDocStatus())
				|| DOCSTATUS_Voided.equals(getDocStatus()))
		{
			processMsg = "Document Closed: " + getDocStatus();
			setDocAction(DOCACTION_None);
			return false;
		}

		//	Not Processed
		if (DOCSTATUS_Drafted.equals(getDocStatus())
				|| DOCSTATUS_Invalid.equals(getDocStatus())
				|| DOCSTATUS_InProgress.equals(getDocStatus())
				|| DOCSTATUS_Approved.equals(getDocStatus()))

		{
			processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
			if (processMsg != null)
				return false;

			//	Set lines to 0
			List<MAllocationLine> allocationLines = Arrays.asList(getLines(true));
			if(!updateBP(true))
				return false;

			allocationLines.stream()
					.forEach(allocationLine -> {
						allocationLine.setAmount(Env.ZERO);
						allocationLine.setDiscountAmt(Env.ZERO);
						allocationLine.setWriteOffAmt(Env.ZERO);
						allocationLine.setOverUnderAmt(Env.ZERO);
						allocationLine.saveEx();
						// Unlink invoices
						allocationLine.processIt(true);

					});
			addDescription(Msg.getMsg(getCtx(), "Voided"));
			retValue = true;
		}
		else
		{
			boolean accrual = false;
			try
			{
				MPeriod.testPeriodOpen(getCtx(), getDateTrx(), MPeriodControl.DOCBASETYPE_PaymentAllocation, getAD_Org_ID());
			}
			catch (PeriodClosedException e)
			{
				accrual = true;
			}

			if (accrual)
				return reverseAccrualIt();
			else
				return reverseCorrectIt();
		}

		// After Void
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (processMsg != null)
			return false;
		
		setDocAction(DOCACTION_None);

		return retValue;
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
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (processMsg != null)
			return false;

		setDocAction(DOCACTION_None);

		// After Close
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (processMsg != null)
			return false;

		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction
	 * 	@return true if success 
	 */
	public boolean reverseCorrectIt()
	{
		log.info(toString());
		// Before reverseCorrect
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (processMsg != null)
			return false;
		
		MAllocationHdr reversal = reverseIt(false);
		if (reversal == null)
			return false;

		// After reverseCorrect
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (processMsg != null)
			return false;
		
		setDocAction(DOCACTION_None);
		return true;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual - none
	 * 	@return false 
	 */
	public boolean reverseAccrualIt()
	{
		log.info(toString());
		// Before reverseAccrual
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (processMsg != null)
			return false;
		
		MAllocationHdr reversal = reverseIt(true);
		if (reversal == null)
			return false;
		// After reverseAccrual
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (processMsg != null)
			return false;
		
		setDocAction(DOCACTION_None);
		return true;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return false 
	 */
	public boolean reActivateIt()
	{
		log.info(toString());
		// Before reActivate
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (processMsg != null)
			return false;	
		
		// After reActivate
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (processMsg != null)
			return false;
		
		return false;
	}	//	reActivateIt

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MAllocationHdr[");
		sb.append(get_ID()).append("-").append(getSummary()).append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		return Msg.getElement(getCtx(), "C_AllocationHdr_ID") + " " + getDocumentNo();
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
			.append(Msg.translate(getCtx(),"ApprovalAmt")).append("=").append(getApprovalAmt())
			.append(" (#").append(getLines(false).length).append(")");
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
		return processMsg;
	}	//	getProcessMsg
	
	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID
	 */
	public int getDoc_User_ID()
	{
		return getCreatedBy();
	}	//	getDoc_User_ID

	/**
	 * 	Add to Description
	 *	@param description text
	 */
	public void addDescription (String description)
	{
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
			setDescription(desc + " | " + description);
	}	//	addDescription

	
	/**************************************************************************
	 * 	Reverse Allocation.
	 * 	Period needs to be open
	 *	@return true if reversed
	 * @param isAccrual
	 */
	public MAllocationHdr reverseIt(boolean isAccrual)
	{
		if (!isActive() || getDocStatus().equals(DOCSTATUS_Voided) || getDocStatus().equals(DOCSTATUS_Reversed)) {
			// Goodwill: don't throw exception here
			//	BF: Reverse is not allowed at Payment void when Allocation is already reversed at Invoice void
			//throw new IllegalStateException("Allocation already reversed (not active)");
			log.warning("Allocation already reversed (not active)");
			return null;
		}

		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		Optional<Timestamp> loginDateOptional = Optional.of(Env.getContextAsDate(getCtx(),"#Date"));
		Timestamp reversalDate =  isAccrual ? loginDateOptional.orElse(currentDate) : getDateAcct();

		MAllocationHdr reversalAllocationHdr;

		//	Can we delete posting
		MPeriod.testPeriodOpen(getCtx(), reversalDate, MPeriodControl.DOCBASETYPE_PaymentAllocation, getAD_Org_ID());
		setReversal(true);
		if (isAccrual)
		{
			reversalAllocationHdr = copyFrom(this, reversalDate, reversalDate , get_TrxName());
			if (reversalAllocationHdr == null)
			{
				processMsg = "Could not create Payment Allocation Reversal";
				return null;
			}
			//	Reverse Line Amt
			Arrays.stream(reversalAllocationHdr.getLines(false))
					.forEach(reverseAllocationLine -> {
						reverseAllocationLine.setAmount(reverseAllocationLine.getAmount().negate());
						reverseAllocationLine.setDiscountAmt(reverseAllocationLine.getDiscountAmt().negate());
						reverseAllocationLine.setWriteOffAmt(reverseAllocationLine.getWriteOffAmt().negate());
						reverseAllocationLine.setOverUnderAmt(reverseAllocationLine.getOverUnderAmt().negate());
						reverseAllocationLine.saveEx(get_TrxName());
					});

			reversalAllocationHdr.setReversal(true);
			reversalAllocationHdr.setDocumentNo(getDocumentNo()+"^");
			reversalAllocationHdr.addDescription("{->" + getDocumentNo() + ")");
			reversalAllocationHdr.setReversal_ID(getC_AllocationHdr_ID());
			reversalAllocationHdr.saveEx();
			//
			if (!DocumentEngine.processIt(reversalAllocationHdr, DocAction.ACTION_Complete))
			{
				processMsg = "Reversal ERROR: " + reversalAllocationHdr.getProcessMsg();
				return null;
			}

			DocumentEngine.processIt(reversalAllocationHdr, DocAction.ACTION_Close);
			reversalAllocationHdr.setProcessing (false);
			reversalAllocationHdr.setDocStatus(DOCSTATUS_Reversed);
			reversalAllocationHdr.setDocAction(DOCACTION_None);
			reversalAllocationHdr.saveEx();

			processMsg = reversalAllocationHdr.getDocumentNo();
			addDescription("(" + reversalAllocationHdr.getDocumentNo() + "<-)");
			addDescription(Msg.getMsg(getCtx(), "Voided"));
			setReversal_ID(reversalAllocationHdr.getReversal_ID());
			setProcessed(true);
			setDocStatus(DOCSTATUS_Reversed);	//	may come from void
			setDocAction(DOCACTION_None);
			return reversalAllocationHdr;
		}
		else
		{
			//	Set Inactive
			setIsActive (false);
			if ( !isPosted() )
				setPosted(true);
			setDocumentNo(getDocumentNo()+"^");
			setDocStatus(DOCSTATUS_Reversed);	//	for direct calls
			if (!save() || isActive())
				throw new IllegalStateException("Cannot de-activate allocation");

			//	Delete Posting
			MFactAcct.deleteEx(MAllocationHdr.Table_ID, getC_AllocationHdr_ID(), get_TrxName());
			//	Unlink Invoices
			List<MAllocationLine> allocationLines = Arrays.asList(getLines(true));
			if (!updateBP(true))
				return null;

			allocationLines.stream()
					.forEach(allocationLine -> {
						allocationLine.setIsActive(false);
						allocationLine.setAmount(Env.ZERO);
						allocationLine.setDiscountAmt(Env.ZERO);
						allocationLine.setWriteOffAmt(Env.ZERO);
						allocationLine.setOverUnderAmt(Env.ZERO);
						allocationLine.saveEx();
						allocationLine.processIt(true);    //	reverse
					});

			addDescription(Msg.getMsg(getCtx(), "Voided"));
			setProcessed(true);
			setDocStatus(DOCSTATUS_Reversed);	//	may come from void
			setDocAction(DOCACTION_None);
			return this;
		}
	}	//	reverse




	/**
	 * 	Update Open Balance of BP's
	 *	@param bps list of business partners
	 */
	@Deprecated
	private void updateBP(HashSet<Integer> bps)
	{
		log.info("#" + bps.size());
		Iterator<Integer> it = bps.iterator();
		while (it.hasNext())
		{
			int C_BPartner_ID = it.next();
			MBPartner bp = new MBPartner(getCtx(), C_BPartner_ID, get_TrxName());
			bp.setTotalOpenBalance();		//	recalculates from scratch
		//	bp.setSOCreditStatus();			//	called automatically
			if (bp.save())
				log.fine(bp.toString());
			else
				log.log(Level.SEVERE, "BP not updated - " + bp);
		}
	}	//	updateBP

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

	/**
	 * Update Business Partners balance
	 * @param isReverse
	 * @return
	 */
	private boolean updateBP(boolean isReverse)
	{
		List<MAllocationLine> allocationLines = Arrays.asList(getLines(false));
		allocationLines.stream()
				.filter(allocationLine -> allocationLine.getC_BPartner_ID() != 0
						|| (allocationLine.getC_Invoice_ID() != 0) && (allocationLine.getC_Payment_ID() != 0))
				.forEach(allocationLine -> {

					boolean isSOTrxInvoice = false;
					MInvoice invoice = null;

					if (allocationLine.getC_Invoice_ID() > 0) {
						invoice = allocationLine.getC_Invoice_ID() > 0 ? new MInvoice(getCtx(), allocationLine.getC_Invoice_ID(), get_TrxName()) : null;
						isSOTrxInvoice = invoice.isSOTrx();
					}

					MBPartner partner = new MBPartner(getCtx(), allocationLine.getC_BPartner_ID(), get_TrxName());
					DB.getDatabase().forUpdate(partner, 0);

					BigDecimal allocationAmount = allocationLine.getAmount().add(allocationLine.getDiscountAmt()).add(allocationLine.getWriteOffAmt());
					BigDecimal openBalanceDifference = Env.ZERO;
					MClient client = MClient.get(getCtx(), getAD_Client_ID());

					boolean paymentProcessed = false;
					boolean paymentIsReceipt = false;

					// get payment information
					if (allocationLine.getC_Payment_ID() > 0) {
						int conversionTypeId = 0;
						Timestamp paymentDate = null;
						MPayment payment = new MPayment(getCtx(), allocationLine.getC_Payment_ID(), get_TrxName());
						conversionTypeId = payment.getC_ConversionType_ID();
						paymentDate = payment.getDateAcct();
						paymentProcessed = payment.isProcessed();
						paymentIsReceipt = payment.isReceipt();

						// Adjust open amount with allocated amount.
						if (paymentProcessed) {
							if (invoice != null) {
								// If payment is already processed, only adjust open balance by discount and write off amounts.
								BigDecimal amount = MConversionRate.convertBase(getCtx(), allocationLine.getWriteOffAmt().add(allocationLine.getDiscountAmt()),
										getC_Currency_ID(), paymentDate, conversionTypeId, getAD_Client_ID(), getAD_Org_ID());
								if (amount == null) {
									processMsg = MConversionRate.getErrorMessage(getCtx(), "ErrorConvertingAllocationCurrencyToBaseCurrency",
											getC_Currency_ID(), MClient.get(getCtx()).getC_Currency_ID(), conversionTypeId, paymentDate, get_TrxName());
									throw new AdempiereException(processMsg);
								}
								openBalanceDifference = openBalanceDifference.add(amount);
							} else {
								// Allocating payment to payment.
								BigDecimal amount = MConversionRate.convertBase(getCtx(), allocationAmount,
										getC_Currency_ID(), paymentDate, conversionTypeId, getAD_Client_ID(), getAD_Org_ID());
								if (amount == null) {
									processMsg = MConversionRate.getErrorMessage(getCtx(), "ErrorConvertingAllocationCurrencyToBaseCurrency",
											getC_Currency_ID(), MClient.get(getCtx()).getC_Currency_ID(), conversionTypeId, paymentDate, get_TrxName());
									throw new AdempiereException(processMsg);
								}
								openBalanceDifference = openBalanceDifference.add(amount);
							}
						} else {
							// If payment has not been processed, adjust open balance by entire allocated amount.
							BigDecimal allocationAmountBase = MConversionRate.convertBase(getCtx(), allocationAmount,
									getC_Currency_ID(), getDateAcct(), conversionTypeId, getAD_Client_ID(), getAD_Org_ID());
							if (allocationAmountBase == null) {
								processMsg = MConversionRate.getErrorMessage(getCtx(), "ErrorConvertingAllocationCurrencyToBaseCurrency",
										getC_Currency_ID(), MClient.get(getCtx()).getC_Currency_ID(), conversionTypeId, getDateAcct(), get_TrxName());
								throw new AdempiereException(processMsg);
							}

							openBalanceDifference = openBalanceDifference.add(allocationAmountBase);
						}
					} else if (invoice != null) {
						// adjust open balance by discount and write off amounts.
						BigDecimal amount = MConversionRate.convertBase(getCtx(), allocationLine.getWriteOffAmt().add(allocationLine.getDiscountAmt()),
								getC_Currency_ID(), invoice.getDateAcct(), invoice.getC_ConversionType_ID(), getAD_Client_ID(), getAD_Org_ID());
						if (amount == null) {
							processMsg = MConversionRate.getErrorMessage(getCtx(), "ErrorConvertingAllocationCurrencyToBaseCurrency",
									getC_Currency_ID(), MClient.get(getCtx()).getC_Currency_ID(), invoice.getC_ConversionType_ID(), invoice.getDateAcct(), get_TrxName());
							throw new AdempiereException(processMsg);
						}
						openBalanceDifference = openBalanceDifference.add(amount);
					}

					// Adjust open amount for currency gain/loss
					if ((invoice != null) &&
							((getC_Currency_ID() != client.getC_Currency_ID()) ||
									(getC_Currency_ID() != invoice.getC_Currency_ID()))) {
						if (getC_Currency_ID() != invoice.getC_Currency_ID()) {
							allocationAmount = MConversionRate.convert(getCtx(), allocationAmount,
									getC_Currency_ID(), invoice.getC_Currency_ID(), getDateAcct(), invoice.getC_ConversionType_ID(), getAD_Client_ID(), getAD_Org_ID());
							if (allocationAmount == null) {
								processMsg = MConversionRate.getErrorMessage(getCtx(), "ErrorConvertingAllocationCurrencyToInvoiceCurrency",
										getC_Currency_ID(), invoice.getC_Currency_ID(), invoice.getC_ConversionType_ID(), getDateAcct(), get_TrxName());
								throw new AdempiereException(processMsg);
							}
						}
						BigDecimal invoiceAmountAccounted = MConversionRate.convertBase(getCtx(), invoice.getGrandTotal(),
								invoice.getC_Currency_ID(), invoice.getDateAcct(), invoice.getC_ConversionType_ID(), getAD_Client_ID(), getAD_Org_ID());
						if (invoiceAmountAccounted == null) {
							processMsg = MConversionRate.getErrorMessage(getCtx(), "ErrorConvertingInvoiceCurrencyToBaseCurrency",
									invoice.getC_Currency_ID(), MClient.get(getCtx()).getC_Currency_ID(), invoice.getC_ConversionType_ID(), invoice.getDateAcct(), get_TrxName());
							throw new AdempiereException(processMsg);
						}

						BigDecimal allocationAmountAccounted = MConversionRate.convertBase(getCtx(), allocationAmount,
								invoice.getC_Currency_ID(), getDateAcct(), invoice.getC_ConversionType_ID(), getAD_Client_ID(), getAD_Org_ID());
						if (allocationAmountAccounted == null) {
							processMsg = MConversionRate.getErrorMessage(getCtx(), "ErrorConvertingInvoiceCurrencyToBaseCurrency",
									invoice.getC_Currency_ID(), MClient.get(getCtx()).getC_Currency_ID(), invoice.getC_ConversionType_ID(), getDateAcct(), get_TrxName());
							throw new AdempiereException(processMsg);
						}

						if (allocationAmount.compareTo(invoice.getGrandTotal()) == 0) {
							openBalanceDifference = openBalanceDifference.add(invoiceAmountAccounted).subtract(allocationAmountAccounted);
						} else {
							//	allocation as a percentage of the invoice
							double multiplier = allocationAmount.doubleValue() / invoice.getGrandTotal().doubleValue();
							//	Reduce Orig Invoice Accounted
							invoiceAmountAccounted = invoiceAmountAccounted.multiply(BigDecimal.valueOf(multiplier));
							//	Difference based on percentage of Orig Invoice
							openBalanceDifference = openBalanceDifference.add(invoiceAmountAccounted).subtract(allocationAmountAccounted);
							//	ignore Tolerance
							if (openBalanceDifference.abs().compareTo(TOLERANCE) < 0)
								openBalanceDifference = Env.ZERO;
							//	Round
							int precision = MCurrency.getStdPrecision(getCtx(), client.getC_Currency_ID());
							if (openBalanceDifference.scale() > precision)
								openBalanceDifference = openBalanceDifference.setScale(precision, BigDecimal.ROUND_HALF_UP);
						}
					}

					//	Total Balance
					BigDecimal newBalance = partner.getTotalOpenBalance();
					if (newBalance == null)
						newBalance = Env.ZERO;

					BigDecimal originalBalance = new BigDecimal(newBalance.toString());

					if (openBalanceDifference.signum() != 0) {
						if (isReverse)
							newBalance = newBalance.add(openBalanceDifference);
						else
							newBalance = newBalance.subtract(openBalanceDifference);
					}

					// Update BP Credit Used only for Customer Invoices and for payment-to-payment allocations.
					BigDecimal newCreditAmount = BigDecimal.ZERO;
					if (isSOTrxInvoice || (invoice == null && paymentIsReceipt && paymentProcessed)) {
						if (invoice == null)
							openBalanceDifference = openBalanceDifference.negate();

						newCreditAmount = partner.getSO_CreditUsed();

						if (isReverse) {
							if (newCreditAmount == null)
								newCreditAmount = openBalanceDifference;
							else
								newCreditAmount = newCreditAmount.add(openBalanceDifference);
						} else {
							if (newCreditAmount == null)
								newCreditAmount = openBalanceDifference.negate();
							else
								newCreditAmount = newCreditAmount.subtract(openBalanceDifference);
						}

						if (log.isLoggable(Level.FINE)) {
							log.fine("TotalOpenBalance=" + partner.getTotalOpenBalance() + "(" + openBalanceDifference
									+ ", Credit=" + partner.getSO_CreditUsed() + "->" + newCreditAmount
									+ ", Balance=" + partner.getTotalOpenBalance() + " -> " + newBalance);
						}
						partner.setSO_CreditUsed(newCreditAmount);
					} else {
						if (log.isLoggable(Level.FINE)) {
							log.fine("TotalOpenBalance=" + partner.getTotalOpenBalance() + "(" + openBalanceDifference
									+ ", Balance=" + partner.getTotalOpenBalance() + " -> " + newBalance);
						}
					}

					if (newBalance.compareTo(originalBalance) != 0)
						partner.setTotalOpenBalance(newBalance);

					partner.setSOCreditStatus();
					if (!partner.save(get_TrxName())) {
						processMsg = "Could not update Business Partner";
						throw new AdempiereException(processMsg);
					}
				}); // for all lines

		return true;
	}	//	updateBP

	// Goodwill.co.id
	/** Reversal Flag		*/
	private boolean isReversal = false;

	/**
	 * 	Set Reversal
	 *	@param reversal reversal
	 */
	public void setReversal(boolean reversal)
	{
		isReversal = reversal;
	}	//	setReversal

	/**
	 * 	Is Reversal
	 *	@return reversal
	 */
	public boolean isReversal()
	{
		return isReversal;
	}	//	isReversal


	/**
	 * 	Create new Allocation by copying
	 * 	@param allocationHdrFrom allocation
	 * 	@param dateAcct date of the document accounting date
	 *  @param dateTrx date of the document transaction.
	 * 	@param trxName
	 *	@return Allocation
	 */
	public static MAllocationHdr copyFrom (MAllocationHdr allocationHdrFrom, Timestamp dateAcct, Timestamp dateTrx, String trxName)
	{
		MAllocationHdr allocationHdrTo = new MAllocationHdr (allocationHdrFrom.getCtx(), 0, trxName);
		PO.copyValues (allocationHdrFrom, allocationHdrTo, allocationHdrFrom.getAD_Client_ID(), allocationHdrFrom.getAD_Org_ID());
		allocationHdrTo.set_ValueNoCheck ("DocumentNo", null);
		allocationHdrTo.setDocStatus (DOCSTATUS_Drafted);		//	Draft
		allocationHdrTo.setDocAction(DOCACTION_Complete);
		allocationHdrTo.setDateTrx (dateAcct);
		allocationHdrTo.setDateAcct (dateTrx);
		allocationHdrTo.setIsManual(false);
		allocationHdrTo.setIsApproved (false);
		allocationHdrTo.setPosted (false);
		allocationHdrTo.setProcessed (false);
		allocationHdrTo.saveEx();
		//	Lines
		if (allocationHdrTo.copyLinesFrom(allocationHdrFrom) == 0)
			throw new AdempiereException("Could not create Allocation Lines");

		return allocationHdrTo;
	}	//	copyFrom

	/**
	 * 	Copy Lines From other Allocation.
	 *	@param allocationHdrFrom allocation
	 *	@return number of lines copied
	 */
	public int copyLinesFrom (MAllocationHdr allocationHdrFrom)
	{
		if (isProcessed() || isPosted() || (allocationHdrFrom == null))
			return 0;

		AtomicInteger lines = new AtomicInteger();
		List<MAllocationLine> allocationLines = Arrays.asList(allocationHdrFrom.getLines(false));
		allocationLines.stream()
				.forEach(allocationLineFrom -> {
					MAllocationLine allocationLineTo = new MAllocationLine(getCtx(), 0, allocationLineFrom.get_TrxName());
					PO.copyValues(allocationLineFrom, allocationLineTo, allocationLineFrom.getAD_Client_ID(), allocationLineFrom.getAD_Org_ID());
					allocationLineTo.setC_AllocationHdr_ID(getC_AllocationHdr_ID());
					allocationLineTo.setParent(this);
					allocationLineTo.set_ValueNoCheck("C_AllocationLine_ID", I_ZERO);    // new
					if (allocationLineTo.getC_Payment_ID() != 0) {
						MPayment payment = new MPayment(getCtx(), allocationLineTo.getC_Payment_ID(), get_TrxName());
						if (DOCSTATUS_Reversed.equals(payment.getDocStatus())) {
							MPayment reversal = (MPayment) payment.getReversal();
							if (reversal != null) {
								allocationLineTo.setPaymentInfo(reversal.getC_Payment_ID(), 0);
							}
						}
					}
					allocationLineTo.saveEx();

					if (allocationHdrFrom.isReversal()) {
						allocationLineTo.setReversalLine_ID(allocationLineFrom.get_ID());
						allocationLineTo.saveEx();
						allocationLineFrom.setReversalLine_ID(allocationLineTo.get_ID());
						allocationLineFrom.saveEx();
					}
					lines.updateAndGet(count -> count + 1);
				});

		if (allocationLines.size() != lines.get())
			log.log(Level.WARNING, "Line difference - From=" + allocationLines.size() + " <> Saved=" + lines.get());
		return lines.get();
	}	//	copyLinesFrom
}   //  MAllocation
