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
package org.compiere.process;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.acct.Doc;
import org.compiere.db.CConnection;
import org.compiere.interfaces.Server;
import org.compiere.model.I_C_Order;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MBankStatement;
import org.compiere.model.MCash;
import org.compiere.model.MClient;
import org.compiere.model.MColumn;
import org.compiere.model.MInOut;
import org.compiere.model.MInventory;
import org.compiere.model.MInvoice;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalBatch;
import org.compiere.model.MMovement;
import org.compiere.model.MPayment;
import org.compiere.model.MProduction;
import org.compiere.model.MProductionBatch;
import org.compiere.model.MRequisition;
import org.compiere.model.MRole;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.I_DD_Order;
import org.eevolution.model.I_HR_Process;
import org.eevolution.model.I_PP_Cost_Collector;
import org.eevolution.model.I_PP_Order;

/**
 *	Document Action Engine
 *	
 *  @author Jorg Janke 
 *  @author Karsten Thiemann FR [ 1782412 ]
 *  @author victor.perez@e-evolution.com www.e-evolution.com FR [ 1866214 ]  http://sourceforge.net/tracker/index.php?func=detail&aid=1866214&group_id=176962&atid=879335
 *			<li>Implement Reverse Accrual for all document https://github.com/adempiere/adempiere/issues/1348</>
 *  @version $Id: DocumentEngine.java,v 1.2 2006/07/30 00:54:44 jjanke Exp $
 */
public class DocumentEngine implements DocAction
{
    /**
     *  Doc Engine - basic constructor
     */
    public DocumentEngine ()
    {
        document = null;
        docStatus = null;
    }

	/**
	 * 	Doc Engine (Drafted)
	 * 	@param po document
	 */
	public DocumentEngine (DocAction po)
	{
		this (po, null);
	}
	
	/**
	 * 	Doc Engine
	 * 	@param po document
	 * 	@param status initial document status
	 */
	public DocumentEngine (DocAction po, String status)
	{
		document = requireNonNull(po);
		docStatus = Optional.ofNullable(status).orElse(STATUS_Drafted);
		ctx = document.getCtx();
		clientId = Env.getAD_Client_ID(ctx);
		tableId = document.get_Table_ID();
		recordId = document.get_ID();
		trxName = document.get_TrxName();
		
	}	

	/** Persistent Document 	*/
	private DocAction	document;
	/** Document Status			*/
	private String		docStatus = STATUS_Drafted;
	/**	Process Message 		*/
	private String		message = null;
	/** Actual Doc Action		*/
	private String		action = null;
    protected Properties ctx = null;
    protected Integer clientId = null;
    protected Integer tableId = null;
    protected Integer recordId = null;
    protected String trxName = null;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(DocumentEngine.class);
	
	protected CLogger getLogger() {
    
        return log;
    
    }

    /**
	 * 	Get Doc Status
	 *	@return document status
	 */
	public String getDocStatus()
	{
		return docStatus;
	}	//	getDocStatus

	/**
	 * 	Set Doc Status - Ignored
	 *	@param ignored Status is not set directly
	 * @see org.compiere.process.DocAction#setDocStatus(String)
	 */
	public void setDocStatus(String ignored)
	{
	    // Ignored as docStatus is not set directly
	}

	/**
	 * 	Document is Drafted
	 *	@return true if drafted
	 */
	public boolean isDrafted()
	{
		return STATUS_Drafted.equals(docStatus);
	}
	
	/**
	 * 	Document is Invalid
	 *	@return true if Invalid
	 */
	public boolean isInvalid()
	{
		return STATUS_Invalid.equals(docStatus);
	}
	
	/**
	 * 	Document is In Progress
	 *	@return true if In Progress
	 */
	public boolean isInProgress()
	{
		return STATUS_InProgress.equals(docStatus);
	}
	
	/**
	 * 	Document is Approved
	 *	@return true if Approved
	 */
	public boolean isApproved()
	{
		return STATUS_Approved.equals(docStatus);
	}
	
	/**
	 * 	Document is Not Approved
	 *	@return true if Not Approved
	 */
	public boolean isNotApproved()
	{
		return STATUS_NotApproved.equals(docStatus);
	}
	
	/**
	 * 	Document is Waiting Payment or Confirmation
	 *	@return true if Waiting Payment
	 */
	public boolean isWaiting()
	{
		return STATUS_WaitingPayment.equals(docStatus)
			|| STATUS_WaitingConfirmation.equals(docStatus);
	}
	
	/**
	 * 	Document is Completed
	 *	@return true if Completed
	 */
	public boolean isCompleted()
	{
		return STATUS_Completed.equals(docStatus);
	}
	
	/**
	 * 	Document is Reversed
	 *	@return true if Reversed
	 */
	public boolean isReversed()
	{
		return STATUS_Reversed.equals(docStatus);
	}
	
	/**
	 * 	Document is Closed
	 *	@return true if Closed
	 */
	public boolean isClosed()
	{
		return STATUS_Closed.equals(docStatus);
	}
	
	/**
	 * 	Document is Voided
	 *	@return true if Voided
	 */
	public boolean isVoided()
	{
		return STATUS_Voided.equals(docStatus);
	}
	
	/**
	 * 	Document Status is Unknown
	 *	@return true if unknown
	 */
	public boolean isUnknown()
	{
		return STATUS_Unknown.equals(docStatus) || 
			!(isDrafted() || isInvalid() || isInProgress() || isNotApproved()
				|| isApproved() || isWaiting() || isCompleted()
				|| isReversed() || isClosed() || isVoided() );
	}

	/**
	 * 	Process actual document.
	 * 	Checks if user (document) action is valid and then process action 
	 * 	Calls the individual actions which call the document action
	 *	@param processAction document action based on workflow
	 *	@param docAction document action based on document
	 *	@return true if performed
	 */
	public boolean processIt (String processAction, String docAction)
	{
		message = null;
		action = null;
		//	Std User Workflows - see MWFNodeNext.isValidFor
		
		if (isValidAction(processAction))	//	WF Selection first
			action = processAction;
		//
		else if (isValidAction(docAction))	//	User Selection second
			action = docAction;
		//	Nothing to do
		else if (processAction.equals(ACTION_None)
			|| docAction.equals(ACTION_None))
		{
			if (document != null)
				document.get_Logger().info ("**** No Action (Prc=" + processAction + "/Doc=" + docAction + ") " + document);
			return true;	
		}
		else
		{
			throw new IllegalStateException("Status=" + getDocStatus() 
				+ " - Invalid Actions: Process="  + processAction + ", Doc=" + docAction);
		}
		if (document != null)
			document.get_Logger().info ("**** Action=" + action + " (Prc=" + processAction + "/Doc=" + docAction + ") " + document);
		boolean success = processIt (action);
		if (document != null)
			document.get_Logger().fine("**** Action=" + action + " - Success=" + success);
		return success;
	}	//	process
	
	/**
	 * 	Process actual document - do not call directly.
	 * 	Calls the individual actions which call the document action
	 *	@param docAction document action
	 *	@return true if performed
	 */
	public boolean processIt (String docAction)
	{
		message = null;
		action = docAction;
		//
		if (ACTION_Unlock.equals(action))
			return unlockIt();
		if (ACTION_Invalidate.equals(action))
			return invalidateIt();
		if (ACTION_Prepare.equals(action))
			return STATUS_InProgress.equals(prepareIt());
		if (ACTION_Approve.equals(action))
			return approveIt();
		if (ACTION_Reject.equals(action))
			return rejectIt();
		if (ACTION_Complete.equals(action) 
		        || ACTION_WaitComplete.equals(action))
			return prepareThenCompleteIt();
		if (ACTION_ReActivate.equals(action))
			return reActivateIt();
		if (ACTION_Reverse_Accrual.equals(action))
			return reverseAccrualIt();
		if (ACTION_Reverse_Correct.equals(action))
			return reverseCorrectIt();
		if (ACTION_Close.equals(action))
			return closeIt();
		if (ACTION_Void.equals(action))
			return voidIt();
		if (ACTION_Post.equals(action))
			return postIt();
		//
		return false;
	}	//	processDocument

    private boolean prepareThenCompleteIt() {

        String status = null;
        if (isDrafted() || isInvalid())		//	prepare if not prepared yet
        {
        	status = prepareIt();
        	if (!STATUS_InProgress.equals(status))
        		return false;
        }
        status = completeIt();
        boolean ok =   STATUS_Completed.equals(status)
        			|| STATUS_InProgress.equals(status)
        			|| STATUS_WaitingPayment.equals(status)
        			|| STATUS_WaitingConfirmation.equals(status);
        if (document != null && ok)
        {
            
        	ArrayList<PO> docsPostProcess = postProcessDocument();
        	postTheDocAndAnyPostProcessDocs(status, docsPostProcess);

        }
        return ok;

    }

    protected void postTheDocAndAnyPostProcessDocs(String status,
            ArrayList<PO> docsPostProcess) {

        if (STATUS_Completed.equals(status) && isClientAccountingImmediate())
        {
        	document.saveEx();
        		postIt();
        	
        	if (!docsPostProcess.isEmpty()) {
        		for (PO docafter : docsPostProcess) {
        			getNewDocumentEngine()
        			    .withContext(docafter.getCtx())
        			    .withAD_Client_ID(docafter.getAD_Client_ID())
        			    .withAD_Table_ID(docafter.get_Table_ID())
        			    .withRecord_ID(docafter.get_ID())
        			    .withTrxName(docafter.get_TrxName())
        			    .postImmediate(true); // Force
        		}
        	}
        }

    }

    protected static DocumentEngine get() {

        return new DocumentEngine();

    }

    protected static DocumentEngine get(DocAction doc, String docStatus) {
    
        return new DocumentEngine(doc, docStatus);
    
    }

    protected DocumentEngine getNewDocumentEngine() {

        return new DocumentEngine();

    }

    boolean isClientAccountingImmediate() {

        return MClient.isClientAccountingImmediate();

    }

    protected ArrayList<PO> postProcessDocument() {

        ArrayList<PO> docsPostProcess = new ArrayList<>();
        
        //  PostProcess documents when invoice or inout (this is to postprocess 
        //  the generated MatchPO and MatchInv if any)

    	if (document instanceof MInvoice) {
    		docsPostProcess  = ((MInvoice) document).getDocsPostProcess();
    	}
    	if (document instanceof MInOut) {
    		docsPostProcess  = ((MInOut) document).getDocsPostProcess();
    	}
        if (!docsPostProcess.isEmpty()) {
        	// Process (this is to update the ProcessedOn flag with 
            // a timestamp after the original document to ensure
            // postings are completed in order)
        	for (PO docafter : docsPostProcess) {
        		docafter.setProcessedOn("Processed", true, false);
        		docafter.saveEx();
        	}
        }
        return docsPostProcess;

    }
	
	/**
	 * 	Unlock Document.
	 * 	Status: Drafted
	 * 	@return true if success 
	 * 	@see org.compiere.process.DocAction#unlockIt()
	 */
	public boolean unlockIt()
	{
		if (!isValidAction(ACTION_Unlock))
			return false;
		if (document != null)
		{
			if (document.unlockIt())
			{
				docStatus = STATUS_Drafted;
				document.setDocStatus(docStatus);
				return true;
			}
			return false;
		}
		docStatus = STATUS_Drafted;
		return true;
	}	//	unlockIt
	
	/**
	 * 	Invalidate Document.
	 * 	Status: Invalid
	 * 	@return true if success 
	 * 	@see org.compiere.process.DocAction#invalidateIt()
	 */
	public boolean invalidateIt()
	{
		if (!isValidAction(ACTION_Invalidate))
			return false;
		if (document != null)
		{
			if (document.invalidateIt())
			{
				docStatus = STATUS_Invalid;
				document.setDocStatus(docStatus);
				return true;
			}
			return false;
		}
		docStatus = STATUS_Invalid;
		return true;
	}	//	invalidateIt
	
	/**
	 *	Process Document.
	 * 	Status is set by process
	 * 	@return new status (In Progress or Invalid) 
	 * 	@see org.compiere.process.DocAction#prepareIt()
	 */
	public String prepareIt()
	{
		if (!isValidAction(ACTION_Prepare))
			return docStatus;
		if (document != null)
		{
			docStatus = document.prepareIt();
			document.setDocStatus(docStatus);
		}
		return docStatus;
	}	//	processIt

	/**
	 * 	Approve Document.
	 * 	Status: Approved
	 * 	@return true if success 
	 * 	@see org.compiere.process.DocAction#approveIt()
	 */
	public boolean  approveIt()
	{
		if (!isValidAction(ACTION_Approve))
			return false;
		if (document != null)
		{
			if (document.approveIt())
			{
				docStatus = STATUS_Approved;
				document.setDocStatus(docStatus);
				return true;
			}
			return false;
		}
		docStatus = STATUS_Approved;
		return true;
	}	//	approveIt
	
	/**
	 * 	Reject Approval.
	 * 	Status: Not Approved
	 * 	@return true if success 
	 * 	@see org.compiere.process.DocAction#rejectIt()
	 */
	public boolean rejectIt()
	{
		if (!isValidAction(ACTION_Reject))
			return false;
		if (document != null)
		{
			if (document.rejectIt())
			{
				docStatus = STATUS_NotApproved;
				document.setDocStatus(docStatus);
				return true;
			}
			return false;
		}
		docStatus = STATUS_NotApproved;
		return true;
	}	//	rejectIt
	
	/**
	 * 	Complete Document.
	 * 	Status is set by process
	 * 	@return new document status (Complete, In Progress, Invalid, Waiting ..)
	 * 	@see org.compiere.process.DocAction#completeIt()
	 */
	public String completeIt()
	{
		if (!isValidAction(ACTION_Complete))
			return docStatus;
		if (document != null)
		{
			docStatus = document.completeIt();
			document.setDocStatus(docStatus);
		}
		return docStatus;
	}	//	completeIt
	
	/**
	 * 	Post Document
	 * 	Does not change status
	 * 	@return true if success 
	 */
	public boolean postIt()
	{
		if (!isValidAction(ACTION_Post) 
			|| document == null)
			return false;
		boolean force = true;
		String error = postImmediate(force);
		return (error == null);
	}	//	postIt
	
	/**
	 * 	Void Document.
	 * 	Status: Voided
	 * 	@return true if success 
	 * 	@see org.compiere.process.DocAction#voidIt()
	 */
	public boolean voidIt()
	{
		if (!isValidAction(ACTION_Void))
			return false;
		if (document != null)
		{
			if (document.voidIt())
			{
				docStatus = STATUS_Voided;
				document.setDocStatus(docStatus);
				return true;
			}
			return false;
		}
		docStatus = STATUS_Voided;
		return true;
	}	//	voidIt
	
	/**
	 * 	Close Document.
	 * 	Status: Closed
	 * 	@return true if success 
	 * 	@see org.compiere.process.DocAction#closeIt()
	 */
	public boolean closeIt()
	{
		if (document != null 	//	orders can be closed at any time
			&& document.get_Table_ID() == I_C_Order.Table_ID)
			;
		else if (!isValidAction(ACTION_Close))
			return false;
		if (document != null)
		{
			if (document.closeIt())
			{
				docStatus = STATUS_Closed;
				document.setDocStatus(docStatus);
				return true;
			}
			return false;
		}
		docStatus = STATUS_Closed;
		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correct Document.
	 * 	Status: Reversed
	 * 	@return true if success 
	 * 	@see org.compiere.process.DocAction#reverseCorrectIt()
	 */
	public boolean reverseCorrectIt()
	{
		if (!isValidAction(ACTION_Reverse_Correct))
			return false;
		if (document != null)
		{
			if (document.reverseCorrectIt())
			{
				docStatus = STATUS_Reversed;
				document.setDocStatus(docStatus);
				return true;
			}
			return false;
		}
		docStatus = STATUS_Reversed;
		return true;
	}	//	reverseCorrectIt
	
	/**
	 * 	Reverse Accrual Document.
	 * 	Status: Reversed
	 * 	@return true if success 
	 * 	@see org.compiere.process.DocAction#reverseAccrualIt()
	 */
	public boolean reverseAccrualIt()
	{
		if (!isValidAction(ACTION_Reverse_Accrual))
			return false;
		if (document != null)
		{
			if (document.reverseAccrualIt())
			{
				docStatus = STATUS_Reversed;
				document.setDocStatus(docStatus);
				return true;
			}
			return false;
		}
		docStatus = STATUS_Reversed;
		return true;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate Document.
	 * 	Status: In Progress
	 * 	@return true if success 
	 * 	@see org.compiere.process.DocAction#reActivateIt()
	 */
	public boolean reActivateIt()
	{
		if (!isValidAction(ACTION_ReActivate))
			return false;
		if (document != null)
		{
			if (document.reActivateIt())
			{
				docStatus = STATUS_InProgress;
				document.setDocStatus(docStatus);
				return true;
			}
			return false;
		}
		docStatus = STATUS_InProgress;
		return true;
	}	//	reActivateIt

	
	/**
	 * 	Set Document Status to new Status
	 *	@param newStatus new status
	 */
	void setStatus (String newStatus)
	{
		docStatus = newStatus;
	}	//	setStatus

	
	/**************************************************************************
	 * 	Get Action Options based on current Status
	 *	@return array of actions
	 */
	public String[] getActionOptions()
	{
		if (isInvalid())
			return new String[] {ACTION_Prepare, ACTION_Invalidate, 
				ACTION_Unlock, ACTION_Void};

		if (isDrafted())
			return new String[] {ACTION_Prepare, ACTION_Invalidate, ACTION_Complete, 
				ACTION_Unlock, ACTION_Void};
		
		if (isInProgress() || isApproved())
			return new String[] {ACTION_Complete, ACTION_WaitComplete, 
				ACTION_Approve, ACTION_Reject, 
				ACTION_Unlock, ACTION_Void, ACTION_Prepare};
		
		if (isNotApproved())
			return new String[] {ACTION_Reject, ACTION_Prepare, 
				ACTION_Unlock, ACTION_Void};
		
		if (isWaiting())
			return new String[] {ACTION_Complete, ACTION_WaitComplete,
				ACTION_ReActivate, ACTION_Void, ACTION_Close};
		
		if (isCompleted())
			return new String[] {ACTION_Close, ACTION_ReActivate, 
				ACTION_Reverse_Accrual, ACTION_Reverse_Correct, 
				ACTION_Post, ACTION_Void};
		
		if (isClosed())
			return new String[] {ACTION_Post, ACTION_ReOpen};
		
		if (isReversed() || isVoided())
			return new String[] {ACTION_Post};
		
		return new String[] {};
	}	//	getActionOptions

	/**
	 * 	Is The Action Valid based on current state
	 *	@param action action
	 *	@return true if valid
	 */
	public boolean isValidAction (String action)
	{
		String[] options = getActionOptions();
		for (int i = 0; i < options.length; i++)
		{
			if (options[i].equals(action))
				return true;
		}
		return false;
	}	//	isValidAction

	/**
	 * 	Get Process Message
	 *	@return clear text error message
	 */
	public String getProcessMsg ()
	{
		return message;
	}	//	getProcessMsg
	
	/**
	 * 	Get Process Message
	 *	@param msg clear text error message
	 */
	public void setProcessMsg (String msg)
	{
		message = msg;
	}	//	setProcessMsg
	
	
	/**	Document Exception Message		*/
	private static final String EXCEPTION_MSG = "Document Engine is not a Document"; 
	
	/*************************************************************************
	 * 	Get Summary
	 *	@return throw exception
	 */
	public String getSummary()
	{
		throw new IllegalStateException(EXCEPTION_MSG);
	}
	
	/**
	 * 	Get Document No
	 *	@return throw exception
	 */
	public String getDocumentNo()
	{
		throw new IllegalStateException(EXCEPTION_MSG);
	}

	/**
	 * 	Get Document Info
	 *	@return throw exception
	 */
	public String getDocumentInfo()
	{
		throw new IllegalStateException(EXCEPTION_MSG);
	}

	/**
	 * 	Get Document Owner
	 *	@return throw exception
	 */
	public int getDoc_User_ID()
	{
		throw new IllegalStateException(EXCEPTION_MSG);
	}
	
	/**
	 * 	Get Document Currency
	 *	@return throw exception
	 */
	public int getC_Currency_ID()
	{
		throw new IllegalStateException(EXCEPTION_MSG);
	}

	/**
	 * 	Get Document Approval Amount
	 *	@return throw exception
	 */
	public BigDecimal getApprovalAmt()
	{
		throw new IllegalStateException(EXCEPTION_MSG);
	}

	/**
	 * 	Get Document Client
	 *	@return throw exception
	 */
	public int getAD_Client_ID()
	{
		throw new IllegalStateException(EXCEPTION_MSG);
	}

	/**
	 * 	Get Document Organization
	 *	@return throw exception
	 */
	public int getAD_Org_ID()
	{
		throw new IllegalStateException(EXCEPTION_MSG);
	}
	
	/**
	 * 	Get Doc Action
	 *	@return Document Action
	 */
	public String getDocAction()
	{
		return action;
	}

	/**
	 * 	Save Document
	 *	@return throw exception
	 */
	public boolean save()
	{
		throw new IllegalStateException(EXCEPTION_MSG);
	}
	
	/**
	 * 	Save Document
	 *	@return throw exception
	 */
	public void saveEx()
	{
		throw new IllegalStateException(EXCEPTION_MSG);
	}
	
	/**
	 * 	Get Context
	 *	@return context
	 */
	public Properties getCtx()
	{
		if (document != null)
			return document.getCtx();
		throw new IllegalStateException(EXCEPTION_MSG);
	}	//	getCtx

	/**
	 * 	Get ID of record
	 *	@return ID
	 */
	public int get_ID()
	{
		if (document != null)
			return document.get_ID();
		throw new IllegalStateException(EXCEPTION_MSG);
	}	//	get_ID
	
	/**
	 * 	Get AD_Table_ID
	 *	@return AD_Table_ID
	 */
	public int get_Table_ID()
	{
		if (document != null)
			return document.get_Table_ID();
		throw new IllegalStateException(EXCEPTION_MSG);
	}	//	get_Table_ID
	
	/**
	 * 	Get Logger
	 *	@return logger
	 */
	public CLogger get_Logger()
	{
		if (document != null)
			return document.get_Logger();
		throw new IllegalStateException(EXCEPTION_MSG);
	}	//	get_Logger

	/**
	 * 	Get Transaction
	 *	@return trx name
	 */
	public String get_TrxName()
	{
		return null;
	}	//	get_TrxName

	/**
	 * 	CreatePDF
	 *	@return null
	 */
	public File createPDF ()
	{
		return null;
	}
	
	/**
	 * Get list of valid document action into the options array parameter. 
	 * Set default document action into the docAction array parameter.
	 * @param docStatus
	 * @param processing
	 * @param orderType
	 * @param isSOTrx
	 * @param tableId
	 * @param docAction
	 * @param options
	 * @return Number of valid options
	 */
	public static int getValidActions(String docStatus, Object processing, 
			String orderType, String isSOTrx, int tableId, String[] docAction, 
			String[] options)
	{
		if (options == null)
			throw new IllegalArgumentException("Option array parameter is null");
		if (docAction == null)
			throw new IllegalArgumentException("Doc action array parameter is null");
		
		int index = 0;
		
		//		Locked
		if (processing != null)
		{
			boolean locked = "Y".equals(processing);
			if (!locked && processing instanceof Boolean)
				locked = ((Boolean)processing).booleanValue();
			if (locked)
				options[index++] = ACTION_Unlock;
		}

		//	Approval required           ..  NA
		if (docStatus.equals(STATUS_NotApproved))
		{
			options[index++] = ACTION_Prepare;
			options[index++] = ACTION_Void;
		}
		//	Draft/Invalid				..  DR/IN
		else if (docStatus.equals(STATUS_Drafted)
			|| docStatus.equals(STATUS_Invalid))
		{
			options[index++] = ACTION_Complete;
			options[index++] = ACTION_Prepare;
			options[index++] = ACTION_Void;
		}
		//	In Process                  ..  IP
		else if (docStatus.equals(STATUS_InProgress)
			|| docStatus.equals(STATUS_Approved))
		{
			options[index++] = ACTION_Complete;
			options[index++] = ACTION_Void;
		}
		//	Complete                    ..  CO
		else if (docStatus.equals(STATUS_Completed))
		{
			options[index++] = ACTION_Close;
		}
		//	Waiting Payment
		else if (docStatus.equals(STATUS_WaitingPayment)
			|| docStatus.equals(STATUS_WaitingConfirmation))
		{
			options[index++] = ACTION_Void;
			options[index++] = ACTION_Prepare;
		}
		//	Closed, Voided, REversed    ..  CL/VO/RE
		else if (docStatus.equals(STATUS_Closed) 
			|| docStatus.equals(STATUS_Voided) 
			|| docStatus.equals(STATUS_Reversed))
			return 0;

		/********************
		 *  Order
		 */
		if (tableId == I_C_Order.Table_ID)
		{
			//	Draft                       ..  DR/IP/IN
			if (docStatus.equals(STATUS_Drafted)
				|| docStatus.equals(STATUS_InProgress)
				|| docStatus.equals(STATUS_Invalid))
			{
				options[index++] = ACTION_Prepare;
				options[index++] = ACTION_Close;
				//	Draft Sales Order Quote/Proposal - Process
				if ("Y".equals(isSOTrx)
					&& ("OB".equals(orderType) || "ON".equals(orderType)))
					docAction[0] = ACTION_Prepare;
			}
			//	Complete                    ..  CO
			else if (docStatus.equals(STATUS_Completed))
			{
				options[index++] = ACTION_Void;
				options[index++] = ACTION_ReActivate;
			}
			else if (docStatus.equals(STATUS_WaitingPayment))
			{
				options[index++] = ACTION_ReActivate;
				options[index++] = ACTION_Close;
			}
		}
		
		else if (tableId == MRequisition.Table_ID) {
			//	Draft                       ..  DR/IP/IN
			if (docStatus.equals(STATUS_Drafted)
				|| docStatus.equals(STATUS_InProgress)
				|| docStatus.equals(STATUS_Invalid)) {
				options[index++] = ACTION_Prepare;
				options[index++] = ACTION_Close;
			}
			//	Complete                    ..  CO
			else if (docStatus.equals(STATUS_Completed)) {
				options[index++] = ACTION_Void;
				options[index++] = ACTION_ReActivate;
			} else if (docStatus.equals(STATUS_WaitingPayment)) {
				options[index++] = ACTION_ReActivate;
				options[index++] = ACTION_Close;
			}

		}
		
		/********************
		 *  Shipment
		 */
		else if (tableId == MInOut.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(STATUS_Completed))
			{
				options[index++] = ACTION_Void;
				options[index++] = ACTION_Reverse_Correct;
				options[index++] = ACTION_Reverse_Accrual;
			}
		}
		/********************
		 *  Invoice
		 */
		else if (tableId == MInvoice.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(STATUS_Completed))
			{
				options[index++] = ACTION_Void;
				options[index++] = ACTION_Reverse_Correct;
				options[index++] = ACTION_Reverse_Accrual;
			}
		}
		/********************
		 *  Payment
		 */
		else if (tableId == MPayment.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(STATUS_Completed))
			{
				options[index++] = ACTION_Void;
				options[index++] = ACTION_Reverse_Correct;
				options[index++] = ACTION_Reverse_Accrual;
			}
		}
		/********************
		 *  GL Journal
		 */
		else if (tableId == MJournal.Table_ID || tableId == MJournalBatch.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(STATUS_Completed))
			{
				options[index++] = ACTION_Reverse_Correct;
				options[index++] = ACTION_Reverse_Accrual;
				options[index++] = ACTION_ReActivate;
			}
		}
		/********************
		 *  Allocation
		 */
		else if (tableId == MAllocationHdr.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(STATUS_Completed))
			{
				options[index++] = ACTION_Void;
				options[index++] = ACTION_Reverse_Correct;
				options[index++] = ACTION_Reverse_Accrual;
			}
		}
		//[ 1782412 ]
		/********************
		 *  Cash
		 */
		else if (tableId == MCash.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(STATUS_Completed))
			{
				options[index++] = ACTION_Void;
			}
		}
		/********************
		 *  Bank Statement
		 */
		else if (tableId == MBankStatement.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(STATUS_Completed))
			{
				options[index++] = ACTION_Void;
			}
		}
		/********************
		 *  Inventory Movement, Physical Inventory
		 */
		else if (tableId == MMovement.Table_ID
			|| tableId == MInventory.Table_ID)
		{
			//	Complete                    ..  CO
			if (docStatus.equals(STATUS_Completed))
			{
				options[index++] = ACTION_Void;
				options[index++] = ACTION_Reverse_Correct;
				options[index++] = ACTION_Reverse_Accrual;
			}
		}
		/********************
		 *  Production
		 */
		else if (tableId == MProduction.Table_ID)
		{
			//	Draft                       ..  DR/IP/IN
			if (docStatus.equals(STATUS_Drafted)
					|| docStatus.equals(STATUS_InProgress)
					|| docStatus.equals(STATUS_Invalid))
			{
				options[index++] = ACTION_Prepare;
			}
			//	Complete                    ..  CO
			else if (docStatus.equals(STATUS_Completed))
			{
				options[index++] = ACTION_Void;
				options[index++] = ACTION_Reverse_Correct;
				options[index++] = ACTION_Reverse_Accrual;
			}

		}
		
		/********************
		 * Production Batch
		 */
		else if (tableId == MProductionBatch.Table_ID)
		{
			// Complete .. CO
			if (docStatus.equals(STATUS_Completed))
			{
				options[index++] = ACTION_Void;
			}
		}

		/********************
		 *  Manufacturing Order
		 */
		else if (tableId == I_PP_Order.Table_ID)
		{
			if (docStatus.equals(STATUS_Drafted)
					|| docStatus.equals(STATUS_InProgress)
					|| docStatus.equals(STATUS_Invalid))
				{
					options[index++] = ACTION_Prepare;
					options[index++] = ACTION_Close;
				}
				//	Complete                    ..  CO
				else if (docStatus.equals(STATUS_Completed))
				{
					options[index++] = ACTION_Void;
					options[index++] = ACTION_ReActivate;
				}
		}
		/********************
		 *  Manufacturing Cost Collector
		 */
		else if (tableId == I_PP_Cost_Collector.Table_ID)
		{
			if (docStatus.equals(STATUS_Drafted)
					|| docStatus.equals(STATUS_InProgress)
					|| docStatus.equals(STATUS_Invalid))
				{
					options[index++] = ACTION_Prepare;
					options[index++] = ACTION_Close;
				}
				//	Complete                    ..  CO
				else if (docStatus.equals(STATUS_Completed))
				{
					options[index++] = ACTION_Void;
					options[index++] = ACTION_Reverse_Correct;
				}
		}
		/********************
		 *  Distribution Order
		 */
		else if (tableId == I_DD_Order.Table_ID)
		{
			if (docStatus.equals(STATUS_Drafted)
					|| docStatus.equals(STATUS_InProgress)
					|| docStatus.equals(STATUS_Invalid))
				{
					options[index++] = ACTION_Prepare;
					options[index++] = ACTION_Close;
				}
				//	Complete                    ..  CO
				else if (docStatus.equals(STATUS_Completed))
				{
					options[index++] = ACTION_Void;
					options[index++] = ACTION_ReActivate;
				}
		}
		/********************
		 *  Payroll Process
		 */
		else if (tableId == I_HR_Process.Table_ID)
		{
			if (docStatus.equals(STATUS_Drafted)
					|| docStatus.equals(STATUS_InProgress)
					|| docStatus.equals(STATUS_Invalid))
				{
					options[index++] = ACTION_Prepare;
					options[index++] = ACTION_Close;
				}
				//	Complete                    ..  CO
				else if (docStatus.equals(STATUS_Completed))
				{
					options[index++] = ACTION_Void;
					options[index++] = ACTION_ReActivate;
					options[index++] = ACTION_Reverse_Correct;
					options[index++] = ACTION_Reverse_Accrual;
				}
		}
		return index;
	}
	
	/**
	 * Fill Vector with DocAction Ref_List(135) values
	 * @param v_value
	 * @param v_name
	 * @param v_description
	 */
	public static void readReferenceList(ArrayList<String> v_value, ArrayList<String> v_name,
			ArrayList<String> v_description)
	{
		if (v_value == null) 
			throw new IllegalArgumentException("v_value parameter is null");
		if (v_name == null)
			throw new IllegalArgumentException("v_name parameter is null");
		if (v_description == null)
			throw new IllegalArgumentException("v_description parameter is null");
		
		String sql;
		if (Env.isBaseLanguage(Env.getCtx(), "AD_Ref_List"))
			sql = "SELECT Value, Name, Description FROM AD_Ref_List "
				+ "WHERE AD_Reference_ID=? ORDER BY Name";
		else
			sql = "SELECT l.Value, t.Name, t.Description "
				+ "FROM AD_Ref_List l, AD_Ref_List_Trl t "
				+ "WHERE l.AD_Ref_List_ID=t.AD_Ref_List_ID"
				+ " AND t.AD_Language='" + Env.getAD_Language(Env.getCtx()) + "'"
				+ " AND l.AD_Reference_ID=? ORDER BY t.Name";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try
		{
			preparedStatement = DB.prepareStatement(sql, null);
			preparedStatement.setInt(1, DocAction.AD_REFERENCE_ID);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				String value = resultSet.getString(1);
				String name = resultSet.getString(2);
				String description = resultSet.getString(3);
				if (description == null)
					description = "";
				//
				v_value.add(value);
				v_name.add(name);
				v_description.add(description);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(resultSet, preparedStatement);
			resultSet = null;
			preparedStatement = null;
		}

	}

	/**
	 * Checks the access rights of the given role/client for the given document actions.
	 * @param clientId
	 * @param roleId
	 * @param docTypeId
	 * @param options
	 * @param maxIndex
	 * @return number of valid actions in the String[] options
	 */
	public static int checkActionAccess(int clientId, int roleId, int docTypeId, String[] options, int maxIndex) {
		return MRole.get(Env.getCtx(), roleId).checkActionAccess(clientId, docTypeId, options, maxIndex);
	}
	
	/**
	 *  Post Immediate
	 *
	 *	@param	ctx Client Context
	 *  @param  clientId    Client ID of Document
	 *  @param  AD_Table_ID     Table ID of Document
	 *  @param  Record_ID       Record ID of this document
	 *  @param  force           force posting
	 *  @param  trxName			ignore, retained for backward compatibility
	 *  @return null, if success or error message
	 */
	public static String postImmediate (Properties ctx, 
		int clientId, int tableId, int recordId, boolean force, String trxName)
	{
	    
	    DocumentEngine docEngine = get()
	            .withContext(ctx)
	            .withAD_Client_ID(clientId)
	            .withAD_Table_ID(tableId)
	            .withRecord_ID(recordId)
	            .withTrxName(trxName);
	    
	    return docEngine.postImmediate(force);
	    
	}

	public DocumentEngine withTrxName(String trxName) {

        if(document == null)
            this.trxName = trxName;
	    return this;

    }

    public DocumentEngine withRecord_ID(int recordId) {

        if(document == null)
            this.recordId = recordId;
        return this;

    }

    public DocumentEngine withAD_Table_ID(int tableId) {

        if(document == null)
            this.tableId = tableId;
        return this;

    }

    public DocumentEngine withAD_Client_ID(int clientId) {
	    
        if(document == null)
            this.clientId = clientId;
        return this;

    }

    public DocumentEngine withContext(Properties ctx) {

        if(document == null)
            this.ctx = ctx;
        return this;

    }

    /**
	 * Process document.  This replaces DocAction.processIt().
	 * @param doc
	 * @param processAction
	 * @return true if performed
	 */
	public static boolean processIt(DocAction doc, String processAction) {
		boolean success = false;

		DocumentEngine engine = get(doc, doc.getDocStatus());
		success = engine.processIt(processAction, doc.getDocAction());

		return success;
	}

	
	   /**
     *  Post Immediate
     *
     *  @param  force           force posting
     *  @return null, if success or error message
     */
    public String postImmediate (boolean force)
    {
        
        requireNonNull(ctx);
        requireNonNull(clientId);
        requireNonNull(tableId);
        requireNonNull(recordId);
        
        String tableName = MTable.getTableName(ctx, tableId);
        
        //  Ensure the table has Posted column / i.e. GL_JournalBatch can be 
        //  completed but not posted
        if (MColumn.getColumn_ID(tableName, "Posted") <= 0)
            return null;
            
        String error = null;
        if (MClient.isClientAccounting()) {
            getLogger().info ("Table=" + tableName + ", Record=" + recordId);
            MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(ctx, clientId);
            Doc doc = getDoc(ass, tableName, recordId, trxName);
			if (doc != null) {
				error = doc.postImmediate(force);
			} else {
				error = EXCEPTION_MSG;
			}
            return error;
        }
        
        //  try to get from Server when enabled
        CConnection serverConnection = getServerConnection();
        if (serverConnection.isAppsServerOK(true))
        {
            getLogger().config("trying server");
            try
            {
                Server server = getServer();
                if (server != null)
                {
                    Properties p = Env.getRemoteCallCtx(Env.getCtx());
                    error = server.postImmediate(p, clientId,
                        tableId, recordId, force, null); // don't pass transaction to server
                    getLogger().config("from Server: " + (error== null ? "OK" : error));
                }
                else
                {
                    error = "NoAppsServer";
                }
            }
            catch (Exception e)
            {
                getLogger().log(Level.WARNING, "(RE)", e);
                error = e.getMessage();
            }
        }
        
        return error;
    }   //  postImmediate

    /**
     * Get the server connection.  Used for testing only.
     * @return
     */
    // Public access required for testing
    public CConnection getServerConnection() {

        return CConnection.get();

    }

	/**
     * Get the server.  Used for testing only.
     * @return
     */
    // Public access required for testing
    public Server getServer() {
    
        return getServerConnection().getServer();
    
    }

    /**
     *  Create Posting document
     *  @param acctSchemas accounting schema
     *  @param tableId Table ID of Documents
     *  @param recordId record ID to load
     *  @param trxName transaction name
     *  @return Document or null
     */
    public Doc getDoc (MAcctSchema[] acctSchemas, String tableName, int recordId, String trxName)
    {
        Doc doc = null;
        String sql = "SELECT * FROM " + tableName
                +" WHERE " + tableName + "_ID=? AND Processed='Y'";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            pstmt = DB.prepareStatement (sql, trxName);
            pstmt.setInt (1, recordId);
            rs = pstmt.executeQuery ();
            if (rs.next ())
            {
                doc = getDoc (acctSchemas, tableName, rs, trxName);
            }
            else
                getLogger().severe("Not Found: " + tableName + "_ID=" + recordId);
        }
        catch (Exception e)
        {
            getLogger().log (Level.SEVERE, sql, e);
        }
        finally
        {
            DB.close(rs, pstmt);
            rs = null; 
            pstmt = null;
        }
        return doc;
    }

    /**
     *  Create Posting document
     *  @param acctSchemas accounting schema
     *  @param tableId Table ID of Documents
     *  @param rs ResultSet
     *  @param trxName transaction name
     *  @return Document
     * @throws AdempiereUserError 
     */
    public Doc getDoc (MAcctSchema[] acctSchemas, String tableName, ResultSet rs, String trxName) throws AdempiereUserError
    {
        Doc doc = null;
        
        String packageName = "org.compiere.acct";
        String className = null;

        int firstUnderscore = tableName.indexOf("_");
        if (firstUnderscore == 1)
            className = packageName + ".Doc_" + tableName.substring(2).replace("_", "");
        else
            className = packageName + ".Doc_" + tableName.replace("_", "");
        
        try
        {
            Class<?> cClass = Class.forName(className);
            Constructor<?> cnstr = cClass.getConstructor(MAcctSchema[].class, ResultSet.class, String.class);
            doc = (Doc) cnstr.newInstance(acctSchemas, rs, trxName);
        }
        catch (Exception e)
        {
            getLogger().log(Level.SEVERE, "Doc Class invalid: " + className + " (" + e.toString() + ")");
            throw new AdempiereUserError("Doc Class invalid: " + className + " (" + e.toString() + ")");
        }

        if (doc == null)
            getLogger().log(Level.SEVERE, "Unknown table =" + tableName);
        
        return doc;
    }

    // For testing
    protected Object getDocument() {

        return document;

    }


}	//	DocumentEnine
