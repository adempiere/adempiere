/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms either version 2 of the  License, 						  *
 * or (at your option) any later version.									  *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.PeriodClosedException;
import org.compiere.model.MBPartner;
import org.compiere.model.MCommission;
import org.compiere.model.MDocType;
import org.compiere.model.MFactAcct;
import org.compiere.model.MPeriod;
import org.compiere.model.MPeriodControl;
import org.compiere.model.MRule;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.Scriptlet;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.process.DocumentReversalEnabled;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;
import org.eevolution.service.HRProcessActionMsg;

import javax.script.ScriptEngine;

/**
 * HR Process Model
 *
 *  @author oscar.gomez@e-evolution.com, e-Evolution http://www.e-evolution.com
 *			<li> Original contributor of Payroll Functionality
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org 
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962
 *  @author Cristina Ghita, www.arhipac.ro
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/761">
 * 		@see FR [ 761 ] Add Payroll variables for Scripts</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/762">
 * 		@see FR [ 762 ] getConcept return NPE</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/765">
 * 		@see FR [ 765 ] Method getAttribute is inconsistent</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/834">
 * 		@see FR [ 834 ] add break date for getAttribute</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/766">
 * 		@see FR [ 766 ] Improve Commission Calculation</a>
 */
public class MHRProcess extends X_HR_Process implements DocAction , DocumentReversalEnabled {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8553627333715790110L;
	
	public int partnerId = 0;
	public int userId = 0;
	public int payrollConceptId = 0;
	public int payrollId = 0;
	public int departmentId = 0;
	public int jobId = 0;
	public String columnType = "";
	public Timestamp dateFrom;
	public Timestamp dateTo;
	/** HR_Concept_ID->MHRMovement */
	public Hashtable<Integer, MHRMovement> movements = new Hashtable<Integer, MHRMovement>();
	public MHRPayrollConcept[] payrollConcepts;
	/** The employee being processed */
	private MHREmployee employee;
	/** the context for rules */
	HashMap<String, Object> scriptCtx = new HashMap<String, Object>();
	/* stack of concepts executing rules - to check loop in recursion */
	private List<MHRConcept> activeConceptRule = new ArrayList<MHRConcept>();

	/**	Static Logger	*/
	private static CLogger logger = CLogger.getCLogger (MHRProcess.class);
	public static final String CONCEPT_PP_COST_COLLECTOR_LABOR = "PP_COST_COLLECTOR_LABOR"; // HARDCODED
	private Object description = null;
	//	Action Scope
	private HRProcessActionMsg actionScope = null;
	
	/**	Script to import	*/
	private static StringBuffer s_scriptImport = new StringBuffer(	 " import org.eevolution.model.*;" 
			+ Env.NL + "import org.compiere.model.*;"
			+ Env.NL + "import org.adempiere.model.*;"
			+ Env.NL + "import org.compiere.util.*;"
			+ Env.NL + "import java.util.*;" 
			+ Env.NL + "import java.math.*;"
			+ Env.NL + "import java.sql.*;");

	public static void addScriptImportPackage(String packageName)
	{
		s_scriptImport.append(" import ").append(packageName).append(";");
	}

	/**************************************************************************
	 *  Default Constructor
	 *  @param ctx context
	 *  @param  HR_Process_ID    To load, (0 create new order)
	 */
	public MHRProcess(Properties ctx, int HR_Process_ID, String trxName) 
	{
		super(ctx, HR_Process_ID,trxName);
		if (HR_Process_ID == 0)
		{
			setDocStatus(DOCSTATUS_Drafted);
			setDocAction(DOCACTION_Prepare);
			setC_DocType_ID(0);
			set_ValueNoCheck ("DocumentNo", null);
			setProcessed(false);
			setProcessing(false);
			setPosted(false);
			setHR_Department_ID(0);
			setC_BPartner_ID(0);
		}
	}

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MHRProcess(Properties ctx, ResultSet rs, String trxName) 
	{
		super(ctx, rs,trxName);
	}	//	MHRProcess

	@Override
	public final void setProcessed(boolean processed)
	{
		super.setProcessed(processed);
		if (get_ID() <= 0)
		{
			return;
		}
		final String sql = "UPDATE HR_Process SET Processed=? WHERE HR_Process_ID=?";
		DB.executeUpdateEx(sql, new Object[]{processed, get_ID()}, get_TrxName());
	}	//	setProcessed

	@Override
	protected boolean beforeSave(boolean newRecord)
	{
		if (getAD_Client_ID() == 0)
		{
			throw new AdempiereException("@AD_Client_ID@ = 0");
		}
		if (getAD_Org_ID() == 0)
		{
			int context_AD_Org_ID = getAD_Org_ID();
			if (context_AD_Org_ID == 0)
			{
				throw new AdempiereException("@AD_Org_ID@ = *");
			}
			setAD_Org_ID(context_AD_Org_ID);
			logger.warning("Changed Org to Context=" + context_AD_Org_ID);
		}
		setC_DocType_ID(getC_DocTypeTarget_ID());

		return true;
	}

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
		logger.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	}	//	unlockIt

	/**
	 * 	Invalidate Document
	 * 	@return true if success
	 */
	public boolean invalidateIt() 
	{
		logger.info("invalidateIt - " + toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	}	//	invalidateIt


	/**************************************************************************
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid)
	 */
	public String prepareIt()
	{
		logger.info("prepareIt - " + toString());

		processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (processMsg != null)
			return DocAction.STATUS_Invalid;

		reActivateIt();
		//	Std Period open?
		MHRPeriod period = MHRPeriod.getById(getCtx(), getHR_Period_ID(), get_TrxName());
		MPeriod.testPeriodOpen(getCtx(), getHR_Period_ID() > 0 ? period.getDateAcct():getDateAcct(), getC_DocTypeTarget_ID(), getAD_Org_ID());

		//	New or in Progress/Invalid
		if (   DOCSTATUS_Drafted.equals(getDocStatus()) 
				|| DOCSTATUS_InProgress.equals(getDocStatus())
				|| DOCSTATUS_Invalid.equals(getDocStatus()) 
				|| getC_DocType_ID() == 0)
		{
			setC_DocType_ID(getC_DocTypeTarget_ID()); 
		}

		try
		{
			createMovements();
		}
		catch (Exception exception)
		{
			deleteMovements();
			throw new AdempiereException(exception);
		}
		
		processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (processMsg != null)
			return DocAction.STATUS_Invalid;
		//
		justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);

		return DocAction.STATUS_InProgress;
	}	//	prepareIt


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

		//	User Validation
		processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (processMsg != null)
		{
			return DocAction.STATUS_Invalid;
		}
		//
		setProcessed(true);	
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt

	/**
	 * 	Approve Document
	 * 	@return true if success
	 */
	public boolean  approveIt() {
		return true;
	}	//	approveIt


	/**
	 * 	Reject Approval
	 * 	@return true if success
	 */
	public boolean rejectIt() {
		logger.info("rejectIt - " + toString());
		return true;
	}	//	rejectIt

	/**
	 * 	Post Document - nothing
	 * 	@return true if success
	 */
	public boolean postIt() {
		logger.info("postIt - " + toString());
		return false;
	}	//	postIt


	/**
	* Void Document.
	* Set Movement Line Amount to 0
	* @return true if success
	*/
	public boolean voidIt() 
	{

		logger.info("voidIt - " + toString());
		// Before Void
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (processMsg != null)
			return false;
	
	
	
		if (DOCSTATUS_Closed.equals(getDocStatus())
		|| DOCSTATUS_Reversed.equals(getDocStatus())
		|| DOCSTATUS_Voided.equals(getDocStatus()))
		{
			processMsg = "Document Closed: " + getDocStatus();
			return false;
		}
	
		//		Not Processed
		if (DOCSTATUS_Drafted.equals(getDocStatus())
		|| DOCSTATUS_Invalid.equals(getDocStatus())
		|| DOCSTATUS_InProgress.equals(getDocStatus())
		|| DOCSTATUS_Approved.equals(getDocStatus())
		|| DOCSTATUS_NotApproved.equals(getDocStatus()) )
		{
			//Set lines to 0
			List<MHRMovement> lines = MHRMovement.findByProcess(this);
			for (MHRMovement movement : lines)
			{
		
				BigDecimal oldAmount = movement.getAmount();
		
				if (oldAmount.signum() != 0)
			
				{	
					movement.setAmount(Env.ZERO);
					movement.setDescription("Void (" + oldAmount + ")");
					movement.saveEx();
				}
			}
			//
			setProcessed(true);
			setDocStatus(DOCSTATUS_Voided); // need to set & save docstatus to be able to check it in MHRProcess.voidIt()
			saveEx();
		}
		else
		{

			boolean isAccrual = false;
			try
			{
				MPeriod.testPeriodOpen(getCtx(), getDateAcct(), getC_DocType_ID(), getAD_Org_ID());
			}
			catch (PeriodClosedException periodClosedException)
			{
				isAccrual = true;
			}

			if (isAccrual)
				return reverseAccrualIt();
			else
				return reverseCorrectIt();
		}
		// After Void
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (processMsg != null)
			return false;

		return true;
	}	//	voidIt


	/**
	 * 	Close Document.
	 * 	Cancel not delivered Qunatities
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		if (isProcessed())
		{
			logger.info(toString());
			
			// Before Close
			processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
			if (processMsg != null)
				return false;
			
			setProcessed(true);
			setDocAction(DOCACTION_None);
			
			// After Close
			processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
			if (processMsg != null)
				return false;
				
			return true;	

		}     	
		return false;
	}	//	closeIt


	/**
	 *
	 * @param isAccrual
	 * @return
	 */
	public MHRProcess reverseIt(boolean isAccrual)
	{
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		Optional<Timestamp> loginDateOptional = Optional.of(Env.getContextAsDate(getCtx(),"#Date"));
		Timestamp reversalDate =  isAccrual ? loginDateOptional.orElse(currentDate) : getDateAcct();
		MPeriod.testPeriodOpen(getCtx(), reversalDate , getC_DocType_ID(), getAD_Org_ID());
		MHRProcess reversal = copyFrom (this, getDateAcct(), getC_DocType_ID(), false, get_TrxName() , true);
		if (reversal == null)
		{
			processMsg = "Could not create Payroll Process Reversal";
			return null;
		}

		reversal.setReversal_ID(getHR_Process_ID());
		reversal.setProcessing (false);
		reversal.setDocStatus(DOCSTATUS_Reversed);
		reversal.setDocAction(DOCACTION_None);
		reversal.setProcessed(true);
		reversal.setName("("+reversal.getDocumentNo()+" -> "+getDocumentNo()+")");
		reversal.saveEx();

		processMsg = reversal.getDocumentNo();
		setProcessed(true);
		setReversal_ID(reversal.getHR_Process_ID());
		setDocStatus(DOCSTATUS_Reversed);	//	may come from void
		setDocAction(DOCACTION_None);
		setProcessed(true);
		setDocAction(DOCACTION_None);
		setName("(" + getName() + " <- "+reversal.getDocumentNo() + ")");
		saveEx();

		return  reversal;
	}

	/**
	 * 	Reverse Correction - same void
	 * 	@return true if success
	 */
	public boolean reverseCorrectIt() {
		logger.info("reverseCorrectIt - " + toString());
		// Before reverseCorrect
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (processMsg != null)
			return false;

		MHRProcess reversal = reverseIt(false);
		if (reversal == null)
			return false;

		// After reverseCorrect
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (processMsg != null)
			return false;
		
				return true;
	}			
	//	reverseCorrectionIt


	/**
	 * 	Reverse Accrual - none
	 * 	@return true if success
	 */
	public boolean reverseAccrualIt() {
		logger.info("reverseAccrualIt - " + toString());
		// Before reverseAccrual
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (processMsg != null)
			return false;

		MHRProcess reversal = reverseIt(true);
		if (reversal == null)
			return false;

		// After reverseAccrual
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (processMsg != null)
			return false;
		
		return true;
	}	//	reverseAccrualIt


	/**
	 * 	Re-activate.
	 * 	@return true if success
	 */
	public boolean reActivateIt() {
		logger.info("reActivateIt - " + toString());

		// Before reActivate
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (processMsg != null)
			return false;
		
		//	Can we delete posting
		MPeriod.testPeriodOpen(getCtx(), getDateAcct(), MPeriodControl.DOCBASETYPE_Payroll, getAD_Org_ID());

		//	Delete
		int no =  deleteMovements();
		logger.fine("HR_Process deleted #" + no);

		//	Delete Posting
		no = MFactAcct.deleteEx(MHRProcess.Table_ID, getHR_Process_ID(), get_TrxName());
		logger.fine("Fact_Acct deleted #" + no);

		setProcessed(false);
		setDocAction(DOCACTION_Complete);
				
		// After reActivate
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (processMsg != null)
			return false;
		return true;
	}	//	reActivateIt


	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID
	 */
	public int getDoc_User_ID() {
		return 0;
	}	//	getDoc_User_ID


	/**
	 * 	Get Document Approval Amount
	 *	@return amount
	 */
	public java.math.BigDecimal getApprovalAmt() 
	{
		return BigDecimal.ZERO;
	}	//	getApprovalAmt

	/**
	 * 
	 */
	public int getC_Currency_ID() 
	{
		return 0;
	}

	public String getProcessMsg() 
	{
		return processMsg;
	}

	public String getSummary()
	{
		return "";
	}

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
			logger.severe("Could not create PDF - " + e.getMessage());
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
		ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.ORDER, 0);
		if (re == null)
			return null;
		return re.getPDF(file);
	}	//	createPDF

	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		org.compiere.model.MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	}	//	getDocumentInfo


	/**
	 * 	Get Lines
	 *	@param requery requery
	 *	@return lines
	 */
	public MHRMovement[] getLines (boolean requery)
	{
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		// For HR_Process:
		whereClause.append(MHRMovement.COLUMNNAME_HR_Process_ID+"=?");
		params.add(getHR_Process_ID());
		// With Qty or Amounts
		whereClause.append("AND (Qty <> 0 OR Amount <> 0)"); // TODO: it's really needed ?
		// Only Active Concepts
		whereClause.append(" AND EXISTS(SELECT 1 FROM HR_Concept c WHERE c.HR_Concept_ID=HR_Movement.HR_Concept_ID"
				+" AND c.IsActive=?"
				+" AND c.AccountSign<>?)"); // TODO : why ?
		params.add(true);
		params.add(MHRConcept.ACCOUNTSIGN_Natural); // TODO : why ?
		// Concepts with accounting
		whereClause.append(" AND EXISTS(SELECT 1 FROM HR_Concept_Acct ca WHERE ca.HR_Concept_ID=HR_Movement.HR_Concept_ID"
				+" AND ca.IsActive=? AND ca.IsBalancing<>?)");
		params.add(true);
		params.add(true);
		// BPartner field is filled
		whereClause.append(" AND C_BPartner_ID IS NOT NULL");
		//
		// ORDER BY
		StringBuffer orderByClause = new StringBuffer();
		orderByClause.append("(SELECT bp.C_BP_Group_ID FROM C_BPartner bp WHERE bp.C_BPartner_ID=HR_Movement.C_BPartner_ID)");
		//
		List<MHRMovement> list = new Query (getCtx(), MHRMovement.Table_Name, whereClause.toString(), get_TrxName())
		.setParameters(params)
		.setOrderBy(orderByClause.toString())
		.list();
		return list.toArray(new MHRMovement[list.size()]);
	}

	/**
	 * Load HR_Movements and store them in a HR_Concept_ID->MHRMovement hashtable
	 * @param movements hashtable
	 * @param partnerId
	 */
	private void loadMovements(Hashtable<Integer,MHRMovement> movements, int partnerId)
	{
		final String whereClause = MHRMovement.COLUMNNAME_HR_Process_ID+"=?"
		+" AND "+MHRMovement.COLUMNNAME_C_BPartner_ID+"=?";
		List<MHRMovement> list = new Query(getCtx(), MHRMovement.Table_Name, whereClause, get_TrxName())
		.setParameters(getHR_Process_ID(), partnerId)
		.list();

		for (MHRMovement movement : list)
		{
			if(movements.containsKey(movement.getHR_Concept_ID()))
			{
				MHRMovement lastM = movements.get(movement.getHR_Concept_ID());
				MHRConcept concept = MHRConcept.getById(getCtx(), lastM.getHR_Concept_ID() , get_TrxName());
				String columntype = concept.getColumnType();
				if (columntype.equals(MHRConcept.COLUMNTYPE_Amount))
				{
					movement.addAmount(lastM.getAmount());
				}
				else if (columntype.equals(MHRConcept.COLUMNTYPE_Quantity))
				{
					movement.addQty(lastM.getQty());
				}
			}
			movements.put(movement.getHR_Concept_ID(), movement);
		}
	}

	private Object executeScriptEngine(MHRConcept concept , MRule rule, String columnType)
	{
		Object result = null;
		try {
			String text = "";
			if (rule.getScript() != null)
			{
				text = rule.getScript().trim().replaceAll("\\bget", "process.get")
						.replace(".process.get", ".get");
			}
			String resultType = "double";
			//	Yamel Senih Add DefValue to another Types
			String defValue = "0";
			if  (MHRAttribute.COLUMNTYPE_Date.equals(columnType)) {
				resultType = "Timestamp";
				defValue = "null";
			} else if  (MHRAttribute.COLUMNTYPE_Text.equals(columnType)) {
				resultType = "String";
				defValue = "null";
			}
			final String script =
					s_scriptImport.toString()
							+ Env.NL + resultType + " result = "+ defValue +";"
							+ Env.NL + "String description = null;"
							+ Env.NL + text;

			ScriptEngine engine = rule.getScriptEngine();
			//MRule.setContext(engine, concept.getCtx(), 0);  // no window
			scriptCtx.entrySet().stream().forEach( entry -> engine.put(entry.getKey(), entry.getValue()));
			result = engine.eval(script);
			if (result != null && "@Error@".equals(result.toString())) {
				throw new AdempiereException("@AD_Rule_ID@ @HR_Concept_ID@ "+ concept.getValue() + "" + concept.getName()+ "	 @@Error@ " + result);
			}
			//	
			description = engine.get("description");

		}
		catch (Exception e)
		{
			throw new AdempiereException(e.getLocalizedMessage());
		}
		return  result;
	}

	/**
	 * Execute the script
	 * @param ruleId
	 * @param columnType Column Type
	 * @return Object
	 */
	private Object executeScript(MHRConcept concept , int ruleId, String columnType)
	{
		MRule rule = MRule.get(getCtx(), ruleId);
		Object result = null;
		description = null;
		try {
			if (rule == null) {
				logger.log(Level.WARNING, " @AD_Rule_ID@ @NotFound@");
			}
			if (!(rule.getEventType().equals(MRule.EVENTTYPE_HumanResourcePayroll)
					&& rule.getRuleType().equals(MRule.RULETYPE_JSR223ScriptingAPIs))) {
				logger.log(Level.WARNING, " must be of type JSR 223 and event human resource");
			}

			if (rule.getEngineName() != null)
				return  executeScriptEngine(concept, rule , columnType);

			String text = "";
			if (rule.getScript() != null) {
				text = rule.getScript().trim().replaceAll("\\bget", "process.get")
				.replace(".process.get", ".get");
			}
			String resultType = "double";
			//	Yamel Senih Add DefValue to another Types
			String defValue = "0";
			if  (MHRAttribute.COLUMNTYPE_Date.equals(columnType)) {
				resultType = "Timestamp";
				defValue = "null";
			} else if  (MHRAttribute.COLUMNTYPE_Text.equals(columnType)) {
				resultType = "String";
				defValue = "null";
			}
			final String script =
					s_scriptImport.toString()
							+ Env.NL + resultType + " result = "+ defValue +";"
							+ Env.NL + "String description = null;"
							+ Env.NL + text;
			Scriptlet engine = new Scriptlet (Scriptlet.VARIABLE, script, scriptCtx);
			Exception ex = engine.execute();
			if (ex != null) {
				throw ex;
			}
			result = engine.getResult(false);
			description = engine.getDescription();
		} catch (Exception e) {
			throw new AdempiereException("@HR_Employee_ID@ : " + employee.getC_BPartner().getName() + " " + employee.getC_BPartner().getName2() 
			+ " \n @HR_Concept_ID@ " + concept.getValue() + " -> " + concept.getName()
			+ " \n @AD_Rule_ID@=" + rule.getValue() + "\n  Script : " + rule.getScript() + " \n Execution error : \n" + e.getLocalizedMessage());
		}
		return result;
	}

	/**
	 * creates movements for concepts related to labor
	 * @param partnerId
	 * @param period
	 */
	private void createCostCollectorMovements(int partnerId, MHRPeriod period)
	{
		List<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		whereClause.append("EXISTS (SELECT 1 FROM AD_User u WHERE u.AD_User_ID=PP_Cost_Collector.AD_User_ID AND u.C_BPartner_ID=?)");
		params.add(partnerId);
		whereClause.append(" AND "+MPPCostCollector.COLUMNNAME_MovementDate + ">=?");
		params.add(period.getStartDate());
		whereClause.append(" AND "+MPPCostCollector.COLUMNNAME_MovementDate + "<=?");
		params.add(period.getEndDate());
		whereClause.append(" AND "+MPPCostCollector.COLUMNNAME_DocStatus + " IN (?,?)");
		params.add(MPPCostCollector.DOCSTATUS_Completed);
		params.add(MPPCostCollector.DOCSTATUS_Closed);

		List<MPPCostCollector> listCollector = new Query(getCtx(), MPPCostCollector.Table_Name, whereClause.toString(), get_TrxName())
		.setOnlyActiveRecords(true)
		.setParameters(params)
		.setOrderBy(MPPCostCollector.COLUMNNAME_PP_Cost_Collector_ID+" DESC") 
		.list();


		for (MPPCostCollector costCollector : listCollector)
		{
			createMovementForCostCollector(partnerId, costCollector);
		}
	}

	/**
	 * create movement for cost collector
	 * @param partnerId
	 * @param costCollector
	 * @return
	 */
	private MHRMovement createMovementForCostCollector(int partnerId, MPPCostCollector costCollector)
	{
		//get the concept that should store the labor
		MHRConcept concept = MHRConcept.getByValue(getCtx(), CONCEPT_PP_COST_COLLECTOR_LABOR, costCollector.get_TrxName());

		//get the attribute for specific concept
		List<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		whereClause.append("? >= ValidFrom AND ( ? <= ValidTo OR ValidTo IS NULL)");
		params.add(dateFrom);
		params.add(dateTo);
		
		if(payrollId > 0){
			whereClause.append(" AND (HR_Payroll_ID=? OR HR_Payroll_ID IS NULL)");
			params.add(payrollId);
		}
		if(departmentId > 0){
			whereClause.append(" AND (HR_Department_ID=? OR HR_Payroll_ID IS NULL)");
			params.add(departmentId);	}
		if(jobId > 0){
			whereClause.append(" AND (HR_Job_ID=? OR HR_Job_ID IS NULL)");
			params.add(jobId);
		}					
		
		whereClause.append(" AND HR_Concept_ID = ? ");
		params.add(concept.get_ID());
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Concept conc WHERE conc.HR_Concept_ID = HR_Attribute.HR_Concept_ID )");
		MHRAttribute attribute = new Query(getCtx(), MHRAttribute.Table_Name, whereClause.toString(), get_TrxName())
		.setParameters(params)
		.setOnlyActiveRecords(true)
		.setOrderBy(MHRAttribute.COLUMNNAME_ValidFrom + " DESC")
		.first();
		if (attribute == null) {
			throw new AdempiereException(); // TODO ?? is necessary
		}

		if (MHRConcept.TYPE_RuleEngine.equals(concept.getType())) {
			Object result;
			scriptCtx.put("_CostCollector", costCollector);
			try {
				result = executeScript(concept , attribute.getAD_Rule_ID(), attribute.getColumnType());
				logger.info(Msg.parseTranslation(getCtx(), "@ScriptResult@ -> @HR_Concept_ID@ @Name@ ") + concept.getName() + " = " + result);
			}
			finally {
				scriptCtx.remove("_CostCollector");
			}

			//get employee
			MHREmployee employee = MHREmployee.getActiveEmployee(getCtx(), partnerId, get_TrxName());
			//create movement
			MHRMovement movement = new MHRMovement(this, concept);
			movement.setHR_Attribute_ID(attribute.getHR_Attribute_ID());
			movement.setC_BPartner_ID(partnerId);
			movement.setDescription(attribute.getDescription());
			movement.setReferenceNo(attribute.getReferenceNo());
			movement.setC_BP_Relation_ID(attribute.getC_BP_Relation_ID());
			movement.setAD_Rule_ID(attribute.getAD_Rule_ID());
			movement.setValidFrom(dateFrom);
			movement.setValidTo(dateTo);
			movement.setPP_Cost_Collector_ID(costCollector.getPP_Cost_Collector_ID());
			movement.setIsManual(true);
			movement.setColumnValue(result);
			movement.setProcessed(true);
			int bpGroupId = DB.getSQLValue(get_TrxName(), "SELECT C_BP_Group_ID FROM C_BPartner WHERE C_BPartner_ID=?", this.partnerId);
			movement.setC_BP_Group_ID(bpGroupId);
			movement.setEmployee(employee);
			movement.saveEx();
			return movement;
		}
		else
		{
			throw new AdempiereException(); //TODO ?? is necessary
		}

	}
	
	/**
	 * create Movements for corresponding process , period
	 */
	private void createMovements() throws Exception
	{
		scriptCtx.clear();
		//	
		logger.info("info data - " +
				Msg.parseTranslation(getCtx(), "@HR_Process_ID@ ") +getHR_Process_ID()+
				Msg.parseTranslation(getCtx(), ", @HR_Period_ID@ :") +getHR_Period_ID()+
				Msg.parseTranslation(getCtx(), ", @HR_Payroll_ID@ : ") +getHR_Payroll_ID()+
				Msg.parseTranslation(getCtx(), ", @HR_Department_ID@ : ") + getHR_Department_ID());
		
		MHRPeriod payrollPeriod;
		
		if (getHR_Period_ID() > 0)
		{
			payrollPeriod = MHRPeriod.getById(getCtx(),  getHR_Period_ID(), get_TrxName());
		}
		else
		{
			payrollPeriod = new MHRPeriod(getCtx() , 0 , get_TrxName());
			MPeriod period = MPeriod.get(getCtx(),  getDateAcct() , getAD_Org_ID());	
			if(period != null)
			{
				payrollPeriod.setStartDate(period.getStartDate());
				payrollPeriod.setEndDate(period.getEndDate());
			}
			else
			{
				payrollPeriod.setStartDate(getDateAcct());
				payrollPeriod.setEndDate(getDateAcct());
			}
		}

		dateFrom = payrollPeriod.getStartDate();
		dateTo   = payrollPeriod.getEndDate();
		MHRPayroll payroll = MHRPayroll.getById(getCtx(), getHR_Payroll_ID(), get_TrxName());
		//	Put variables
		scriptCtx.put("process", this);
		scriptCtx.put("_Process", getHR_Process_ID());
		scriptCtx.put("_Period", getHR_Period_ID());
		scriptCtx.put("_Payroll", getHR_Payroll_ID());
		scriptCtx.put("_Department", getHR_Department_ID());
		scriptCtx.put("_From", dateFrom);
		scriptCtx.put("_To", dateTo);
		scriptCtx.put("_Period", payrollPeriod.getPeriodNo());
		scriptCtx.put("_HR_Payroll_Value", payroll.getValue());
		//	Scope
		scriptCtx.put("SCOPE_PROCESS", HRProcessActionMsg.SCOPE_PROCESS);
		scriptCtx.put("SCOPE_EMPLOYEE", HRProcessActionMsg.SCOPE_EMPLOYEE);
		scriptCtx.put("SCOPE_CONCEPT", HRProcessActionMsg.SCOPE_CONCEPT);
		scriptCtx.put("PERSISTENCE_SAVE", HRProcessActionMsg.PERSISTENCE_SAVE);
		scriptCtx.put("PERSISTENCE_IGNORE", HRProcessActionMsg.PERSISTENCE_IGNORE);
		scriptCtx.put("ACTION_BREAK", HRProcessActionMsg.ACTION_BREAK);
		//	
		if(getHR_Payroll_ID() > 0)
			payrollId = getHR_Payroll_ID();
		if(getHR_Department_ID() > 0)
			departmentId = getHR_Department_ID();
		if(getHR_Job_ID() > 0)
			jobId = getHR_Job_ID();

		payrollConcepts = MHRPayrollConcept.getPayrollConcepts(this);
		//	Instance Scope
		actionScope = new HRProcessActionMsg();
		//	
		for(MBPartner employee : MHREmployee.getEmployees(this)) {
			calculateMovements(employee, payrollPeriod);
			//	Validate action
			if(actionScope.isProcessScope()
					&& actionScope.isBreakRunning()) {
				actionScope.clearAction();
				actionScope.clearScope();
				actionScope.clearPersistence();
				break;
			}
		}

		// Save period & finish
		if (getHR_Period_ID() > 0) {
			payrollPeriod.setProcessed(true);
			payrollPeriod.saveEx();
		}
	}

	/**
	 * Set scope action
	 * @param scope
	 * @return
	 */
	public HRProcessActionMsg scope(int scope) {
		return actionScope.scope(scope);
	}
	
	/**
	 * Calaculate Movements
	 * @param partner
	 * @param payrollPeriod
	 */
	private void  calculateMovements(MBPartner partner, MHRPeriod payrollPeriod)
	{
		logger.info(Msg.parseTranslation(getCtx() , "@HR_Employee_ID@ # ") 
				+ Msg.parseTranslation(getCtx() , " @BPValue@ ") + partner.getValue() 
				+  Msg.parseTranslation(getCtx(), " @BPName@ ") + partner.getName() +  " " + partner.getName2());
		partnerId = partner.get_ID();
		employee = MHREmployee.getActiveEmployee(getCtx(), partnerId, get_TrxName());
		if(employee == null) {
			return;
		}
		String employeePayrollValue = null;
		if(employee.getHR_Payroll_ID() != 0) {
			MHRPayroll employeePayroll = MHRPayroll.getById(getCtx(), employee.getHR_Payroll_ID(), get_TrxName());
			employeePayrollValue = employeePayroll.getValue();
		}
		Timestamp employeeValidFrom = dateFrom;
		Timestamp employeeValidTo = dateTo;
		//	Valid from for employee
		if(employee.getStartDate() != null && dateFrom != null && employee.getStartDate().getTime() > dateFrom.getTime()) {
			employeeValidFrom = employee.getStartDate();
		}
		//  Valid to for employee
		if(employee.getEndDate() != null && dateTo != null && employee.getEndDate().getTime() < dateTo.getTime()) {
			employeeValidTo = employee.getEndDate();
		}
		scriptCtx.remove("_DateStart");
		scriptCtx.remove("_DateEnd");
		scriptCtx.remove("_Days");
		scriptCtx.remove("_C_BPartner_ID");
		scriptCtx.remove("_HR_Employee_ID");
		scriptCtx.remove("_C_BPartner");
		scriptCtx.remove("_HR_Employee");
		scriptCtx.remove("_HR_Employee_ValidFrom");
		scriptCtx.remove("_HR_Employee_ValidTo");
		scriptCtx.remove("_HR_Employee_Payroll_Value");

		scriptCtx.put("_DateStart", employee.getStartDate());
		scriptCtx.put("_DateEnd", employee.getEndDate() == null ? dateTo == null ? getDateAcct() : dateTo : employee.getEndDate());
		scriptCtx.put("_Days", TimeUtil.getDaysBetween(payrollPeriod.getStartDate(),payrollPeriod.getEndDate()) + 1);
		scriptCtx.put("_C_BPartner_ID", partner.getC_BPartner_ID());
		scriptCtx.put("_HR_Employee_ID", employee.getHR_Employee_ID());
		scriptCtx.put("_C_BPartner", partner);
		scriptCtx.put("_HR_Employee", employee);
		scriptCtx.put("_HR_Employee_Payroll_Value", employeePayrollValue);
		//	Get Employee valid from and to
		scriptCtx.put("_HR_Employee_ValidFrom", employeeValidFrom);
		scriptCtx.put("_HR_Employee_ValidTo", employeeValidTo);
		//	
		if(getHR_Period_ID() > 0) {
			createCostCollectorMovements(partner.get_ID(), payrollPeriod);
		}
		//	Clear movements
		movements.clear();
		loadMovements(movements, partnerId);
		//
		for(MHRPayrollConcept payrollConcept : payrollConcepts) // ==================================================== Concept
		{
			//	Validate action
			if(actionScope.isConceptScope()
					&& actionScope.isBreakRunning()) {
				actionScope.clearAction();
				actionScope.clearScope();
				actionScope.clearPersistence();
				continue;
			}
			payrollConceptId = payrollConcept.getHR_Concept_ID();
			MHRConcept concept = MHRConcept.getById(getCtx(), payrollConceptId, get_TrxName());
			boolean printed = payrollConcept.isPrinted() || concept.isPrinted();
			MHRMovement movement = movements.get(concept.get_ID()); // as it's now recursive, it can happen that the concept is already generated
			if (movement == null) {
				scriptCtx.remove("_HR_Concept_ID");
				scriptCtx.remove("_HR_Concept");
				scriptCtx.put("_HR_Concept_ID", concept.getHR_Concept_ID());
				scriptCtx.put("_HR_Concept", concept);
				scriptCtx.remove("_HR_PayrollConcept_ID");
				scriptCtx.put("_HR_PayrollConcept_ID", payrollConcept.getHR_PayrollConcept_ID());
				createMovementFromConcept(concept, printed);
				movement = movements.get(concept.get_ID());
				//	Validate null
				if (movement == null)
					throw new AdempiereException("Concept " + concept.getValue() + " not created");
				//	
				movement.setHR_Payroll_ID(payrollConcept.getHR_Payroll_ID());
				movement.setHR_PayrollConcept_ID(payrollConcept.getHR_PayrollConcept_ID());
				movement.setPeriodNo(payrollPeriod.getPeriodNo());
			}
			//	Validate action
			if((actionScope.isEmployeeScope()
					|| actionScope.isProcessScope())
					&& actionScope.isBreakRunning()) {
				//	Clear Scope for employee
				if(actionScope.isEmployeeScope()) {
					actionScope.clearScope();
					actionScope.clearAction();
				}
				break;
			}
		} // concept
		//	Validate action
		if(actionScope.isIgnorePersistence()) {
			actionScope.clearPersistence();
			return;
		}
		// Save movements:
		for (MHRMovement movement: movements.values()) {
			MHRConcept concept = MHRConcept.getById(getCtx() , movement.getHR_Concept_ID() , get_TrxName());
			if (concept != null && concept.get_ID() > 0) {
				if (concept.isManual()) {
					logger.fine("Skip saving " + movement);
				} else {
					boolean saveThisRecord = (concept.isSaveInHistoric() 
													|| movement.isPrinted() 
													|| concept.isPaid() 
													|| concept.isPrinted()) 
											&& (!concept.isNotSaveInHistoryIfNull() || !movement.isEmpty());
					if (saveThisRecord)
						movement.saveEx();
				}
			}
		}
		//	Clear persistence
		actionScope.clearPersistence();
	}

	private int deleteMovements()
	{
		// RE-Process, delete movement except concept type Incidence
		int no = DB.executeUpdateEx("DELETE FROM HR_Movement m WHERE HR_Process_ID=? AND IsManual<>?", new Object[]{getHR_Process_ID(), true}, get_TrxName());
		logger.info(Msg.parseTranslation(getCtx() , "@HR_Movement_ID@ @Deleted@ #") + no);
		return  no;
	}


	/**
	 * Method use to create a movemeningBuffer whereClause = new StringBuffer();
		StringBuffer orderByClause = new StringBuffer(MHRAttribute.COLUMNNAME_ValidFrom).append(ORDERVALUE);
		//	Add for updated
		orderByClause.append(", " + MHRAttribute.COLUMNNAME_Updated).append(ORDERVALUE);t
	 * @param concept
	 * @param isPrinted
	 * @return
	 */
	private void createMovementFromConcept(MHRConcept concept, boolean isPrinted) {
		logger.info("Calculating -> "+ Msg.parseTranslation(getCtx(), " @HR_Concept_ID@ "+ concept.getValue() + " -> " + concept.getName()));
		columnType = concept.getColumnType();
		MHRAttribute attribute = MHRAttribute.getByConceptAndEmployee(concept , employee, getHR_Payroll_ID(),  dateFrom ,dateTo);
		if (attribute == null || concept.isManual())
		{
			createDummyMovement(concept);
			return;
		}

		logger.info(Msg.parseTranslation(getCtx(), "@HR_Concept_ID@ : ") + concept.getName());
		MHRMovement movement = createMovement(concept, attribute, isPrinted);
		if (MHRConcept.TYPE_RuleEngine.equals(concept.getType()))
		{
			logger.info(Msg.parseTranslation(getCtx() , "@Processing@ -> @AD_Rule_ID@ @To@ @HR_Concept_ID@ ") + concept.getValue());
			if (activeConceptRule.contains(concept)) {
				throw new AdempiereException("Recursion loop detected in concept " + concept.getValue());
			}
			activeConceptRule.add(concept);
			Object result = executeScript(concept , attribute.getAD_Rule_ID(), attribute.getColumnType());
			logger.info(Msg.parseTranslation(getCtx(), "@ScriptResult@ -> @HR_Concept_ID@ @Name@ ") + concept.getName() + " = " + result);
			activeConceptRule.remove(concept);
			movement.setColumnValue(result); // double rounded in MHRMovement.setColumnValue
			if (description != null)
				movement.setDescription(description.toString());
		}
		else
		{
			movement.setQty(attribute.getQty());
			movement.setAmount(attribute.getAmount());
			movement.setTextMsg(attribute.getTextMsg());
			movement.setServiceDate(attribute.getServiceDate());
		}
		movement.setProcessed(true);
		movements.put(concept.getHR_Concept_ID(), movement);
	}

	private void createDummyMovement(MHRConcept concept)
	{
		logger.info("Skip concept "+concept+" - attribute not found");
		MHRMovement dummyMovement = new MHRMovement (getCtx(), 0, concept.get_TrxName());
		dummyMovement.setSeqNo(concept.getSeqNo());
		dummyMovement.setIsManual(true); // to avoid landing on movement table
		movements.put(concept.getHR_Concept_ID(), dummyMovement);
		return;
	}

	/**
	 * Get Monthly Salary
	 * @return
	 */
	public double getMonthlySalary(String trxName) {
		BigDecimal monthtlySalary = employee.getMonthlySalary();
		if(monthtlySalary != null
				&& !monthtlySalary.equals(Env.ZERO)) {
			return monthtlySalary.doubleValue();
		}
		//	if not exists
		if(employee.getHR_Payroll_ID() != 0) {
			MHRPayroll payroll = MHRPayroll.getById(getCtx(), employee.getHR_Payroll_ID(), trxName);
			MHRContract contract = MHRContract.getById(getCtx(), payroll.getHR_Contract_ID(), trxName);
			if(contract != null
					&& contract.getMonthlySalary_ID() != 0) {
				MHRConcept concept = MHRConcept.getById(getCtx(), contract.getMonthlySalary_ID() , trxName);
				//	Get from attribute
				return getAttribute(concept.getValue());
			}
		}
		//	Default
		return 0;
	}
	
	/**
	 * Get Daily Salary
	 * @return
	 */
	public double getDailySalary() {
		BigDecimal dailySalary = employee.getDailySalary();
		if(dailySalary != null
				&& !dailySalary.equals(Env.ZERO)) {
			return dailySalary.doubleValue();
		}
		//	if not exists
		if(employee.getHR_Payroll_ID() != 0) {
			MHRPayroll payroll = MHRPayroll.getById(getCtx(), employee.getHR_Payroll_ID(), get_TrxName());
			MHRContract contract = MHRContract.getById(getCtx(), payroll.getHR_Contract_ID(), get_TrxName());
			if(contract != null
					&& contract.getDailySalary_ID() != 0) {
				MHRConcept concept = MHRConcept.getById(getCtx(), contract.getDailySalary_ID(), get_TrxName());
				//	Get from attribute
				return getAttribute(concept.getValue());
			}
		}
		//	Default
		return 0;
	}
	
	/**
	 * Create movement based on concept , attribute and is printed
	 * @param concept
	 * @param attribute
	 * @param isPrinted
	 * @return
	 */
	private MHRMovement createMovement(MHRConcept concept, MHRAttribute attribute, boolean isPrinted) {
		I_HR_Period payrollPeriod = getHR_Period();
		MHRMovement movement = new MHRMovement (getCtx(), 0, get_TrxName());
		movement.setAD_Org_ID(employee.getAD_Org_ID());
		movement.setSeqNo(concept.getSeqNo());
		movement.setHR_Attribute_ID(attribute.getHR_Attribute_ID());
		movement.setDescription(attribute.getDescription());
		movement.setReferenceNo(attribute.getReferenceNo());
		Optional.ofNullable(payrollPeriod).ifPresent(period -> movement.setPeriodNo(period.getPeriodNo()));
		movement.setC_BPartner_ID(partnerId);
		movement.setC_BP_Relation_ID(attribute.getC_BP_Relation_ID());
		movement.setHR_Concept_ID(concept.getHR_Concept_ID());
		movement.setHR_Concept_Category_ID(concept.getHR_Concept_Category_ID());
		movement.setHR_Process_ID(getHR_Process_ID());
		movement.setAD_Rule_ID(attribute.getAD_Rule_ID());
		movement.setValidFrom(dateFrom);
		movement.setValidTo(dateTo);
		movement.setIsPrinted(isPrinted);
		movement.setIsManual(concept.isManual());
		movement.setC_BP_Group_ID(employee.getC_BPartner().getC_BP_Group_ID());
		movement.setEmployee(employee);
		return movement;

	}

	// Helper methods -------------------------------------------------------------------------------

	/**
	 * Helper Method : get the value of the concept
	 * @param conceptValue
	 * @return
	 */
	public double getConcept (String conceptValue)
	{
		MHRConcept concept = MHRConcept.getByValue(getCtx(), conceptValue.trim(), get_TrxName());
		if (concept == null)
			return 0;

		MHRMovement movement = movements.get(concept.get_ID());
		if (movement == null) {
			createMovementFromConcept(concept, concept.isPrinted());
			movement = movements.get(concept.get_ID());
		}
		if (movement == null)
			throw new AdempiereException("Concept " + concept.getValue() + " not created");

		String type = concept.getColumnType();
		if (MHRConcept.COLUMNTYPE_Amount.equals(type))
			return movement.getAmount().doubleValue();
		else if (MHRConcept.COLUMNTYPE_Quantity.equals(type))
			return movement.getQty().doubleValue();
		else
			return 0;
	} // getConcept

	/**
	 * Helper Method : get the value of the concept string type
	 * @param pconcept
	 * @return String value of concept
	 */
	public String getConceptString (String pconcept)
	{
		MHRConcept concept = MHRConcept.getByValue(getCtx(), pconcept.trim(), get_TrxName());
		if (concept == null)
			return null;

		MHRMovement movement = movements.get(concept.get_ID());
		if (movement == null) {
			createMovementFromConcept(concept, concept.isPrinted());
			movement = movements.get(concept.get_ID());
		}

		String type = concept.getColumnType();
		if (MHRConcept.COLUMNTYPE_Text.equals(type))
			return movement.getTextMsg();
		else
			return null;
	} // getConceptString

	/**
	 * Helper Method : get the value of the concept date type
	 * @param conceptValue
	 * @return Timestamp value of concept
	 */
	public Timestamp getConceptDate (String conceptValue)
	{
		MHRConcept concept = MHRConcept.getByValue(getCtx(), conceptValue.trim(), get_TrxName());
		if (concept == null)
			return null;

		MHRMovement movement = movements.get(concept.get_ID());
		if (movement == null) {
			createMovementFromConcept(concept, concept.isPrinted());
			movement = movements.get(concept.get_ID());
		}

		String type = concept.getColumnType();
		if (MHRConcept.COLUMNTYPE_Date.equals(type))
			return movement.getServiceDate();
		else
			return null;
	} // getConceptDate

	/**
	 * Helper Method : sets the value of a concept
	 * @param conceptValue
	 * @param value
	 */
	@Deprecated
	public void setConcept (String conceptValue, double value)
	{
		createMovement(conceptValue , value);
	}

	/* Helper Method : sets the value of a concept and set if isManual
	* @param conceptValue
	* @param value
	* @param isManual
	*/
	@Deprecated
	public void setConcept (String conceptValue, double value , boolean isManual)
	{
		createMovement(conceptValue , value , isManual);
	}

	/**
	 * Create Movement Movement
	 * @param conceptValue
	 * @param value
	 */
	public void createMovement (String conceptValue, double value)
	{
		MHRConcept concept = MHRConcept.getByValue(getCtx(), conceptValue, get_TrxName());
		if (concept == null)
			return;

		MHRAttribute attribute = MHRAttribute.getByConceptAndEmployee(concept , employee, getHR_Payroll_ID(),  dateFrom ,dateTo);
		if (attribute == null || concept.isManual())
		{
			createDummyMovement(concept);
			return;
		}

		MHRMovement movement = createMovement(concept, attribute , concept.isPrinted());
		movement.setColumnValue(BigDecimal.valueOf(value));
		movement.saveEx();
		movements.put(concept.getHR_Concept_ID(), movement);
	} // setConcept

	/**
	 * Create Movement Movement
	 * @param conceptValue
	 * @param value
	 * @param isManual
	 */
	public void createMovement(String conceptValue, double value , boolean isManual)
	{
		MHRConcept concept = MHRConcept.getByValue(getCtx(), conceptValue, get_TrxName());
		if (concept == null)
			return;

		MHRAttribute attribute = MHRAttribute.getByConceptAndEmployee(concept , employee, getHR_Payroll_ID(),  dateFrom ,dateTo);
		if (attribute == null || concept.isManual())
		{
			createDummyMovement(concept);
			return;
		}

		MHRMovement movement = createMovement(concept, attribute , concept.isPrinted());
		if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Amount))
			movement.setAmount(BigDecimal.valueOf(value));
		else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity))
			movement.setQty(BigDecimal.valueOf(value));
		else
			return;

		movement.setIsManual(isManual);
		movement.saveEx();
		movements.put(movement.getHR_Movement_ID() , movement);
	} // setConcept

	/**
	 * Method use to save of calculate on the fly when payroll process is completed
	 * @param concept
	 * @param qty
	 * @param amount
	 * @param referenceNo
	 * @param description
	 * @return
	 */
	public MHRMovement createMovement(MHRConcept concept, BigDecimal qty , BigDecimal amount , String referenceNo , String description)
	{
		if (concept == null || concept.getHR_Concept_ID() == 0)
			throw new AdempiereException("@HR_Concept_ID@ @NotFound@");
		//Delete previous calculated
		MHRMovement.findByProcessAndConceptIdAndPartnerId(this, concept.getHR_Concept_ID() , partnerId, referenceNo , description)
				.stream()
				.filter(movement -> movement != null)
				.forEach(movement -> movement.deleteEx(true));
		//Create Calculated Movement
		MHRAttribute attribute = MHRAttribute.getByConceptAndEmployee(concept , employee, getHR_Payroll_ID(),  dateFrom ,dateTo);
		MHRMovement movement = createMovement(concept, attribute , concept.isPrinted());
		Optional.ofNullable(qty).ifPresent(q -> movement.setAmount(q));
		Optional.ofNullable(amount).ifPresent(a -> movement.setAmount(a));
		Optional.ofNullable(referenceNo).ifPresent(rn -> movement.setReferenceNo(rn));
		Optional.ofNullable(description).ifPresent(d -> movement.setDescription(d));
		movement.saveEx();
		movements.put(concept.getHR_Concept_ID(), movement);
		return movement;
	}

	/**
	 * Method use to save of calculate on the fly when payroll process is completed
	 * @param conceptValue
	 * @param qty
	 * @param amount
	 * @param referenceNo
	 * @param description
	 * @return
	 */
	public MHRMovement createMovement(String conceptValue , BigDecimal qty , BigDecimal amount , String referenceNo , String description)
	{
		if (conceptValue == null || conceptValue.length() == 0)
			throw new AdempiereException("@HR_Concept_ID@ @NotFound@");
		MHRConcept concept = MHRConcept.getByValue(getCtx() , conceptValue, get_TrxName());
		if (concept == null || concept.getHR_Concept_ID() == 0)
			throw new AdempiereException("@HR_Concept_ID@ @NotFound@");

		return createMovement(concept , qty , amount , referenceNo , description);
	}

	/**
	 * Helper Method : get the sum of the concept values, grouped by the concept type
	 * @param typeValue
	 * @return
	 */
	public double getConceptType (String typeValue)
	{
		final MHRConceptType conceptType = MHRConceptType.getByValue(getCtx(), typeValue, get_TrxName());
		if (conceptType == null)
			return 0.0;

		double value = 0.0;
		for(MHRPayrollConcept payrollConcept : payrollConcepts)
		{
			MHRConcept concept = MHRConcept.getById(getCtx(), payrollConcept.getHR_Concept_ID() , get_TrxName());
			if(concept.getHR_Concept_Type_ID() == conceptType.get_ID())
			{
				MHRMovement movement = movements.get(payrollConcept.getHR_Concept_ID());
				if (movement == null)
					createMovementFromConcept(concept, concept.isPrinted());
				movement = movements.get(concept.get_ID());

				if (movement != null)
				{
					String columnType = concept.getColumnType();
					if(MHRConcept.COLUMNTYPE_Amount.equals(columnType))
					{
						value += movement.getAmount().doubleValue();
					}
					else if (MHRConcept.COLUMNTYPE_Quantity.equals(columnType))
					{
						value += movement.getQty().doubleValue();
					}
				}
			}
		}
		return value;
	} // Get Concept Type


	/**
	 * Helper Method : get the sum of the concept values, grouped by the Category
	 * @param categoryValue
	 * @return
	 * @Deprecated
	 */
	public double getConceptGroup (String categoryValue)
	{
		return getConceptCategory(categoryValue);
	} // getConceptGroup

	/**
	 * Helper Method : get the sum of the concept values, grouped by the Category
	 * @param categoryValue
	 * @return
	 */
	public double getConceptCategory (String categoryValue)
	{
		final MHRConceptCategory conceptCategory = MHRConceptCategory.getByValue(getCtx(), categoryValue, get_TrxName());
		if (conceptCategory == null)
			return 0.0;

		double value = 0.0;
		for(MHRPayrollConcept payrollConcept : payrollConcepts)
		{
			MHRConcept concept = MHRConcept.getById(getCtx(), payrollConcept.getHR_Concept_ID() , get_TrxName());
			if(concept.getHR_Concept_Category_ID() == conceptCategory.get_ID())
			{
				MHRMovement movement = movements.get(payrollConcept.getHR_Concept_ID());
				if (movement == null)
					createMovementFromConcept(concept, concept.isPrinted());
				movement = movements.get(concept.get_ID());

				if (movement != null)
				{
					String columnType = concept.getColumnType();
					if(MHRConcept.COLUMNTYPE_Amount.equals(columnType))
					{
						value += movement.getAmount().doubleValue();
					}
					else if (MHRConcept.COLUMNTYPE_Quantity.equals(columnType))
					{
						value += movement.getQty().doubleValue();
					}
				}
			}
		}
		return value;
	} // getConceptCategory

	/**
	 * Helper Method : Get Concept [get concept to search key ]
	 * @param listSearchKey Value List
	 * @param amount Amount to search
	 * @param columnParam Number of column to return (1.......8)
	 * @return The amount corresponding to the designated column 'column'
	 */
	public double getList (String listSearchKey, double amount, String columnParam)
	{
		return getList (listSearchKey, dateFrom, amount, columnParam , columnType);
	}

	/**
	 * Helper Method : Get Concept [get concept to search key ]
	 * @param listSearchKey
	 * @param from
	 * @param amount
	 * @param columnParam
     * @return The amount corresponding to the designated column 'column'
     */
	public double getList (String listSearchKey, Timestamp from , double amount, String columnParam)
	{
		return getList (listSearchKey, from , amount, columnParam , null);
	}

	/**
	 * Helper Method : Get Concept [get concept to search key ]
	 * @param listSearchKey Value List
	 * @from from date to valid list
	 * @param amount Amount to search
	 * @param columnParam Number of column to return (1.......8)
	 * @param columnType can be Amount or null
	 * @return The amount corresponding to the designated column 'column'
	 */
	public double getList (String listSearchKey,Timestamp from, double amount, String columnParam , String columnType)
	{
		BigDecimal value = Env.ZERO;
		String column = columnParam;
		if (MHRConcept.COLUMNTYPE_Amount.equals(columnType) || columnType == null )
		{
			column = column.toString().length() == 1 ? "Col_"+column : "Amount"+column;
			ArrayList<Object> params = new ArrayList<Object>();
			String sqlList = "SELECT " +column+
				" FROM HR_List l " +
				"INNER JOIN HR_ListVersion lv ON (lv.HR_List_ID=l.HR_List_ID) " +
				"INNER JOIN HR_ListLine ll ON (ll.HR_ListVersion_ID=lv.HR_ListVersion_ID) " +
				"WHERE l.IsActive='Y' AND lv.IsActive='Y' AND ll.IsActive='Y' AND l.Value = ? AND " +
				"l.AD_Client_ID in (?,0) AND " +
				"(? BETWEEN lv.ValidFrom AND lv.ValidTo ) AND " +
				"(? BETWEEN ll.MinValue AND	ll.MaxValue)" +
				" ORDER BY l.AD_CLIENT_ID desc ";
			params.add(listSearchKey);
			params.add(getAD_Client_ID());
			params.add(from);
			params.add(BigDecimal.valueOf(amount));

			value = DB.getSQLValueBDEx(get_TrxName(),sqlList,params);
		}
		//
		if (value == null)
		{
			throw new IllegalStateException("getList Out of Range");
		}
		return value.doubleValue();
	} // getList

	
	/**
	 * Get Attribute PO for current busines partner
	 * @param conceptValue
	 * @return
	 */
	public MHRAttribute getAttributeInstance(String conceptValue) {
		return getAttributeInstance(conceptValue, 0, null);
	}
	
	/**
	 * Overload get Attribute Instance
	 * @param conceptValue
	 * @param partnerId
	 * @return
	 */
	public MHRAttribute getAttributeInstance(String conceptValue, int partnerId, Timestamp breakDate) {
		MHRConcept concept = MHRConcept.getByValue(getCtx(), conceptValue, get_TrxName());
		return getAttributeInstance(concept, partnerId, breakDate);
	}
	
	/**
	 * Get attribute like MHRAttribute
	 * @param concept
	 * @param partnerId
	 * @param breakDate
	 * @return
	 */
	public MHRAttribute getAttributeInstance(MHRConcept concept, int partnerId, Timestamp breakDate) {
		if (concept == null)
			return null;
		//	validate break date
		if(breakDate == null)
			breakDate = dateFrom;
		//	BPartner
		if(partnerId <= 0)
			partnerId = this.partnerId;
		//	
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		// check ValidFrom:
		whereClause.append(MHRAttribute.COLUMNNAME_ValidFrom + "<=?");
		params.add(breakDate);
		//check client
		whereClause.append(" AND AD_Client_ID = ?");
		params.add(getAD_Client_ID());
		//	Add criteria by payroll
		if(payrollId > 0) {
			whereClause.append(" AND (HR_Payroll_ID=? OR HR_Payroll_ID IS NULL)");
			params.add(payrollId);
		}
		//	by department
		if(departmentId > 0) {
			whereClause.append(" AND (HR_Department_ID=? OR HR_Department_ID IS NULL)");
			params.add(departmentId);
		}
		//	by job
		if(jobId > 0) {
			whereClause.append(" AND (HR_Job_ID=? OR HR_Job_ID IS NULL)");
			params.add(jobId);
		}
		//check concept
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Concept c WHERE c.HR_Concept_ID = HR_Attribute.HR_Concept_ID" 
				+ " AND c.Value = ?)");
		params.add(concept.getValue());
		//
		if (!concept.getType().equals(MHRConcept.TYPE_Information)) {
			whereClause.append(" AND " + MHRAttribute.COLUMNNAME_C_BPartner_ID + " = ?");
			params.add(partnerId);
		}
		//	Get from query
		MHRAttribute attribute = new Query(getCtx(), MHRAttribute.Table_Name, whereClause.toString(), get_TrxName())
			.setParameters(params)
			.setOrderBy(MHRAttribute.COLUMNNAME_ValidFrom + " DESC")
			.setOnlyActiveRecords(true)
			.first();
		//	Return
		return attribute;
	}
	
	/**
	 * Helper Method : Get Attribute [get Attribute to search key concept ]
	 * @param conceptValue - Value to Concept
	 * @return	Amount of concept, applying to employee
	 */ 
	public double getAttribute (String conceptValue) {
		return getAttribute(conceptValue, null);
	} // 
	
	/**
	 * Helper Method : Get Attribute [get Attribute to search key concept and break date]
	 * @param conceptValue
	 * @param breakDate
	 * @return
	 */
	public double getAttribute (String conceptValue, Timestamp breakDate) {
		MHRConcept concept = MHRConcept.getByValue(getCtx(), conceptValue, get_TrxName());
		if (concept == null)
			return 0.0;
		//	Get from PO
		MHRAttribute attribute = getAttributeInstance(concept, 0, breakDate);
		if (attribute == null)
			return 0.0;

		// if column type is Quantity return quantity
		if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity))
			return attribute.getQty().doubleValue();

		// if column type is Amount return amount
		if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Amount))
			return attribute.getAmount().doubleValue();

		//something else
		return 0.0; //TODO throw exception ?? 
	} // 


	/**
	 * 	Helper Method : Get Attribute [get Attribute to search key concept ]
	 *  @param conceptValue
	 *  @return ServiceDate
	 */ 
	public Timestamp getAttributeDate (String conceptValue) {
		return getAttributeDate(conceptValue, null);
	} // getAttributeDate
	
	/**
	 * 	Helper Method : Get Attribute [get Attribute to search key concept and break date]
	 *  @param conceptValue
	 *  @return ServiceDate
	 */ 
	public Timestamp getAttributeDate (String conceptValue, Timestamp breakDate) {
		//	Get from PO
		MHRAttribute attribute = getAttributeInstance(conceptValue, 0, breakDate);
		if (attribute == null)
			return null;
		//	
		return attribute.getServiceDate();
	} // getAttributeDate

	/**
	 * 	Helper Method : Get Attribute [get Attribute to search key concept ]
	 *  @param conceptValue
	 *  @return TextMsg
	 */ 
	public String getAttributeString (String conceptValue) {
		return getAttributeString(conceptValue, null);
	} // getAttributeString

	/**
	 * 	Helper Method : Get Attribute [get Attribute to search key concept and break date]
	 *  @param conceptValue
	 *  @return TextMsg
	 */ 
	public String getAttributeString (String conceptValue, Timestamp breakDate) {
		MHRAttribute attribute = getAttributeInstance(conceptValue, 0, breakDate);
		if (attribute == null)
			return null;
		//	
		return attribute.getTextMsg();
	} // getAttributeString
	
	/**
	 * 	Helper Method : Get the number of days between start and end, in Timestamp format
	 *  @param date1 
	 *  @param date2
	 *  @return no. of days
	 */ 
	public int getDays (Timestamp date1, Timestamp date2)
	{		
		// adds one for the last day
		return org.compiere.util.TimeUtil.getDaysBetween(date1,date2) + 1;
	} // getDays


	/**
	 * 	Helper Method : Get the number of days between start and end, in String format
	 *  @param date1 
	 *  @param date2
	 *  @return no. of days
	 */  
	public  int getDays (String date1, String date2)
	{		
		Timestamp dat1 = Timestamp.valueOf(date1);
		Timestamp dat2 = Timestamp.valueOf(date2);
		return getDays(dat1, dat2);
	}  // getDays

	/**
	 * 	Helper Method : Get Months, Date in Format Timestamp
	 *  @param startParam
	 *  @param endParam
	 *  @return no. of month between two dates
	 */ 
	public int getMonths(Timestamp startParam,Timestamp endParam)
	{
		boolean negative = false;
		Timestamp start = startParam;
		Timestamp end = endParam;
		if (end.before(start))
		{
			negative = true;
			Timestamp temp = start;
			start = end;
			end = temp;
		}

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(start);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		GregorianCalendar calEnd = new GregorianCalendar();

		calEnd.setTime(end);
		calEnd.set(Calendar.HOUR_OF_DAY, 0);
		calEnd.set(Calendar.MINUTE, 0);
		calEnd.set(Calendar.SECOND, 0);
		calEnd.set(Calendar.MILLISECOND, 0);

		if (cal.get(Calendar.YEAR) == calEnd.get(Calendar.YEAR))
		{
			if (negative)
				return (calEnd.get(Calendar.MONTH) - cal.get(Calendar.MONTH)) * -1;
			return calEnd.get(Calendar.MONTH) - cal.get(Calendar.MONTH);
		}

		//	not very efficient, but correct
		int counter = 0;
		while (calEnd.after(cal))
		{
			cal.add (Calendar.MONTH, 1);
			counter++;
		}
		if (negative)
			return counter * -1;
		return counter;
	} // getMonths

	/**
	 * Helper Method : Concept for a period.
	 * Periods with values of 0 -1 1, etc. actual previous one period, next period
	 * 0 corresponds to actual period.
	 * @param conceptValue concept key(value)
	 * @param period search is done by the period value, it helps to search from previous years
	 */
	public double getConcept (String conceptValue, int period) {
		return getConcept(conceptValue, null, period,period, true);
	} // getConcept


	/**
	 * Helper Method : Concept for a range from-to in periods.
	 * Periods with values of 0 -1 1, etc. actual previous one period, next period
	 * 0 corresponds to actual period.
	 * @param conceptValue concept key(value)
	 * @param periodFrom the search is done by the period value, it helps to search from previous years
	 * @param periodTo
	 */
	public double getConcept (String conceptValue, int periodFrom, int periodTo) {
		return getConcept(conceptValue, null, periodFrom,periodTo, true);
	} // getConcept

	/**
	 *  Helper Method : Concept by range from-to in periods from a different payroll
	 *  periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 * @param conceptValue
	 * @param payrollValue is the value of the payroll.
	 * @param periodFrom
	 * @param periodTo the search is done by the period value, it helps to search from previous years
	 */
	public double getConcept(String conceptValue, String payrollValue, int periodFrom, int periodTo) {
		return getConcept(conceptValue, payrollValue, periodFrom , periodTo , true);
	}

	/**
	 *  Helper Method : Concept by range from-to in periods from a different payroll
	 *  periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 * @param conceptValue
	 *  @param payrollValue is the value of the payroll.
	 * @param periodFrom
	 * @param periodTo the search is done by the period value, it helps to search from previous years
	 * @param includeInProcess
	 */
	public double getConcept(String conceptValue, String payrollValue, int periodFrom, int periodTo, boolean includeInProcess) {
		int payrollId;
		if (payrollValue == null) {
			payrollId = getHR_Payroll_ID();
		} else {
			MHRPayroll payroll = MHRPayroll.getByValue(getCtx(), payrollValue, get_TrxName());
			if(payroll == null)
				return 0.0;
			//	
			payrollId = payroll.get_ID();
		}
		//	Get from Movement helper method
		return MHRMovement.getConceptSum(getCtx(), conceptValue, payrollId,
				partnerId, getHR_Period_ID(), periodFrom, periodTo, includeInProcess, get_TrxName());
	} // getConcept

	/**
	 *  Helper Method : Concept Average by range from-to in periods from a different payroll
	 *  periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 * @param conceptValue
	 * @param payrollValue is the value of the payroll.
	 * @param periodFrom
	 * @param periodTo the search is done by the period value, it helps to search from previous years
	 */
	public double getConceptAvg(String conceptValue, String payrollValue, int periodFrom, int periodTo) {
		return getConceptAvg(conceptValue,payrollValue,periodFrom, periodTo,true);
	}

	/**
	 *  Helper Method : Concept Average by range from-to in periods from a different payroll
	 *  periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 * @param conceptValue
	 * @param payrollValue is the value of the payroll.
	 * @param periodFrom
	 * @param periodTo the search is done by the period value, it helps to search from previous years
	 * @param includeInProcess
	 */
	public double getConceptAvg(String conceptValue, String payrollValue, int periodFrom, int periodTo, boolean includeInProcess) {
		int payrollId;
		if (payrollValue == null) {
			payrollId = getHR_Payroll_ID();
		} else {
			MHRPayroll payroll = MHRPayroll.getByValue(getCtx(), payrollValue, get_TrxName());
			if(payroll == null)
				return 0.0;
			//	
			payrollId = payroll.get_ID();
		}
		//	Get from Movement helper method
		return MHRMovement.getConceptAvg(getCtx(), conceptValue, payrollId, 
				partnerId, getHR_Period_ID(), periodFrom, periodTo, includeInProcess, get_TrxName());
	} // getConcept
	
	/**
	 * Get last concept without break date
	 * @param conceptValue
	 * @param payrollValue
	 * @return
	 */
	public double getLastConcept(String conceptValue, String payrollValue) {
		return getLastConcept(conceptValue, payrollValue, dateFrom);
	}
	
	/**
	 * Get Last concept for a employee
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @return
	 */
	public double getLastConcept(String conceptValue, String payrollValue, Timestamp breakDate) {
		int payrollId;
		if (payrollValue == null) {
			payrollId = getHR_Payroll_ID();
		} else {
			MHRPayroll payroll = MHRPayroll.getByValue(getCtx(), payrollValue, get_TrxName());
			if(payroll == null)
				return 0.0;
			//	
			payrollId = payroll.get_ID();
		}
		//	Get from Movement helper method
		return MHRMovement.getLastConcept(getCtx(), conceptValue, payrollId, 
				partnerId, breakDate, get_TrxName());
	} // getConcept
	
	/**
	 * Get Last concept date for a employee
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @return
	 */
	public Timestamp getLastConceptDate(String conceptValue, String payrollValue, Timestamp breakDate) {
		int payrollId;
		if (payrollValue == null) {
			payrollId = getHR_Payroll_ID();
		} else {
			MHRPayroll payroll = MHRPayroll.getByValue(getCtx(), payrollValue, get_TrxName());
			if(payroll == null)
				return null;
			//	
			payrollId = payroll.get_ID();
		}
		//	Get from Movement helper method
		return MHRMovement.getLastConceptDate(getCtx(), conceptValue, payrollId, 
				partnerId, breakDate, get_TrxName());
	} // getConcept
	
	/**
	 * Get Last concept String for a employee
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @return
	 */
	public String getLastConceptString(String conceptValue, String payrollValue, Timestamp breakDate) {
		int payrollId;
		if (payrollValue == null) {
			payrollId = getHR_Payroll_ID();
		} else {
			MHRPayroll payroll = MHRPayroll.getByValue(getCtx(), payrollValue, get_TrxName());
			if(payroll == null)
				return null;
			//	
			payrollId = payroll.get_ID();
		}
		//	Get from Movement helper method
		MHRMovement lastMovement = MHRMovement.getLastMovement(getCtx(), conceptValue, payrollId, 
				partnerId, breakDate, get_TrxName());
		//	
		if(lastMovement == null) {
			return null;
		}
		//	For all
		if(!Util.isEmpty(lastMovement.getTextMsg())) {
			return lastMovement.getTextMsg();
		}
		//	For Description (optional)
		return lastMovement.getDescription();
	} // getConcept
	
	/**
	 * Get Last concept Valid From for a employee
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @return
	 */
	public Timestamp getLastConceptValidFrom(String conceptValue, String payrollValue, Timestamp breakDate) {
		int payrollId;
		if (payrollValue == null) {
			payrollId = getHR_Payroll_ID();
		} else {
			MHRPayroll payroll = MHRPayroll.getByValue(getCtx(), payrollValue, get_TrxName());
			if(payroll == null)
				return null;
			//	
			payrollId = payroll.get_ID();
		}
		//	Get from Movement helper method
		MHRMovement lastMovement = MHRMovement.getLastMovement(getCtx(), conceptValue, payrollId, 
				partnerId, breakDate, get_TrxName());
		//	
		if(lastMovement == null) {
			return null;
		}
		//	Default
		return lastMovement.getValidFrom();
	} // getConcept
	
	/**
	 * Get Last concept Valid To for a employee
	 * @param conceptValue
	 * @param payrollValue
	 * @param breakDate
	 * @return
	 */
	public Timestamp getLastConceptValidTo(String conceptValue, String payrollValue, Timestamp breakDate) {
		int payrollId;
		if (payrollValue == null) {
			payrollId = getHR_Payroll_ID();
		} else {
			MHRPayroll payroll = MHRPayroll.getByValue(getCtx(), payrollValue, get_TrxName());
			if(payroll == null)
				return null;
			//	
			payrollId = payroll.get_ID();
		}
		//	Get from Movement helper method
		MHRMovement lastMovement = MHRMovement.getLastMovement(getCtx(), conceptValue, payrollId, 
				partnerId, breakDate, get_TrxName());
		//	
		if(lastMovement == null) {
			return null;
		}
		//	Default
		return lastMovement.getValidTo();
	} // getConcept
	
	/**
	 * Get Concept for this payroll for range dates
	 * @param conceptValue
	 * @param from
	 * @param to
	 * @return
	 */
	public double getConcept (String conceptValue ,Timestamp from,Timestamp to)
	{
		return getConcept(conceptValue, null , from , to, true);
	}

	/**
	 * Helper Method: gets Concept value of a payrroll between 2 dates
	 * @param conceptValue
	 * @param payrollValue
	 * @param from
	 * @param to
	 * */
	public double getConcept(String conceptValue, String payrollValue, Timestamp from, Timestamp to) {
		return getConcept(conceptValue, payrollValue , from , to , true);
	}

	/**
	 * Helper Method: gets Concept value of a payrroll between 2 dates
	 * @param conceptValue
	 * @param payrollValue
	 * @param from
	 * @param to
	 * @param includeInProcess
	 * */
	public double getConcept(String conceptValue, String payrollValue, Timestamp from, Timestamp to, boolean includeInProcess) {
		int payrollId;
		if (payrollValue == null) {
			payrollId = getHR_Payroll_ID();
		} else {
			MHRPayroll payroll = MHRPayroll.getByValue(getCtx(), payrollValue, get_TrxName());
			if(payroll == null)
				return 0.0;
			//	
			payrollId = payroll.get_ID();
		}
		//	Get from Movement helper method
		return MHRMovement.getConceptSum(getCtx(), conceptValue, payrollId, partnerId, from, to, includeInProcess, get_TrxName());
	} // getConcept

	/**
	 * Helper Method: gets Concept Average value of a payrroll between 2 dates
	 * @param conceptValue
	 * @param payrollValue
	 * @param from
	 * @param to
	 * @return
	 */
	public double getConceptAvg(String conceptValue, String payrollValue, Timestamp from, Timestamp to) {
		return getConceptAvg(conceptValue, payrollValue , from , to , true);
	}

	/**
	 * Helper Method: gets Concept Average value of a payrroll between 2 dates
	 * @param conceptValue
	 * @param payrollValue
	 * @param from
	 * @param to
	 * @param includeInProcess
	 * */
	public double getConceptAvg(String conceptValue, String payrollValue, Timestamp from, Timestamp to, boolean includeInProcess) {
		int payrollId;
		if (payrollValue == null) {
			payrollId = getHR_Payroll_ID();
		} else {
			MHRPayroll payroll = MHRPayroll.getByValue(getCtx(), payrollValue, get_TrxName());
			if(payroll == null)
				return 0.0;
			//	
			payrollId = payroll.get_ID();
		}
		//	Get from Movement helper method
		return MHRMovement.getConceptAvg(getCtx(), conceptValue, payrollId, partnerId, from, to , includeInProcess, get_TrxName());
	} // getConcept
	
	
	/**
	 * Helper Method : Attribute that had from some date to another to date,
	 * if it finds just one period it's seen for the attribute of such period 
	 * if there are two or more attributes based on the days
	 * @param ctx
	 * @param vAttribute
	 * @param dateFrom
	 * @param dateTo
	 * @return attribute value
	 */
	public double getAttribute (Properties ctx, String vAttribute, Timestamp dateFrom, Timestamp dateTo)
	{
		// TODO ???
		logger.warning("not implemented yet -> getByConceptAndPartnerId (Properties, String, Timestamp, Timestamp)");
		return 0;
	} // getByConceptAndPartnerId

	/**
	 *  Helper Method : Attribute that had from some period to another to period,
	 *   periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 *  Value of HR_Attribute
	 *  if it finds just one period it's seen for the attribute of such period 
	 *  if there are two or more attributes 
	 *  pFrom and pTo the search is done by the period value, it helps to search 
	 *  from previous year based on the days
	 *  @param ctx
	 *  @param vAttribute
	 *  @param periodFrom
	 *  @param periodTo
	 *  @param pFrom
	 *  @param pTo
	 *  @return attribute value	  
	 */
	public double getAttribute (Properties ctx, String vAttribute, int periodFrom,int periodTo,
			String pFrom,String pTo)
	{
		// TODO ???
		logger.warning("not implemented yet -> getByConceptAndPartnerId (Properties, String, int, int, String, String)");
		return 0;
	} // getByConceptAndPartnerId
	
	
		
	/**
	 * Helper Method : Get AttributeInvoice 
	 * @param conceptValue - Value to Concept
	 * @return	C_Invoice_ID, 0 if does't
	 */ 
	public int getAttributeInvoice (String conceptValue) {
		MHRAttribute attribute = getAttributeInstance(conceptValue);
		if (attribute == null)
			return 0;
		//	Get invoice
		return attribute.get_ValueAsInt("C_Invoice_ID");
	} // getAttributeInvoice
		
	/**
	 * Helper Method : Get AttributeDocType
	 * @param conceptValue - Value to Concept
	 * @return	C_DocType_ID, 0 if does't
	 */ 
	public int getAttributeDocType (String conceptValue) {
		MHRAttribute attribute = getAttributeInstance(conceptValue);
		if (attribute == null)
			return 0;
		//	
		return attribute.get_ValueAsInt("C_DocType_ID"); 
	} // getAttributeDocType

	/**
	 * Get attribute by employee
	 * @param conceptValue
	 * @param partnerId
	 * @return
	 */
	public BigDecimal getAttributeByPartnerId(String conceptValue, int partnerId, Timestamp breakDate) {
		MHRConcept concept = MHRConcept.getByValue(getCtx(), conceptValue, get_TrxName());
		//	
		MHRAttribute attribute = getAttributeInstance(conceptValue, partnerId, breakDate);
		if (attribute == null)
			return BigDecimal.ZERO;

		// if column type is Quantity return quantity
		if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity))
			return attribute.getQty();

		// if column type is Amount return amount
		if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Amount))
			return attribute.getAmount();
		//	
		return BigDecimal.ZERO;
	}
	
	/**
	 * Overload attribute by employee
	 * @param conceptValue
	 * @param partnerId
	 * @return
	 */
	public BigDecimal getAttributeByPartnerId(String conceptValue, int partnerId) {
		return getAttributeByPartnerId(conceptValue, partnerId, null);
	}
	
	/**
	 * Get attribute by employee
	 * @param conceptValue
	 * @param partnerId
	 * @return
	 */
	@Deprecated
	public BigDecimal getAttributeBPartner(String conceptValue, int partnerId) {
		return getAttributeByPartnerId(conceptValue, partnerId);
	} // getByConceptAndPartnerId

	/**
	 * Helper Method : get days from specific period
	 * @param period
	 * @return no. of days
	 */
	public double getDays (int period)
	{
		/* TODO: This getter could have an error as it's not using the parameter, and it doesn't what is specified in help */
		logger.warning("instead of using getDays in the formula it's recommended to use _DaysPeriod+1");
		return Env.getContextAsInt(getCtx(), "_DaysPeriod") + 1;
	} // getDays
	
	
	public static MHRProcess copyFrom (MHRProcess from, Timestamp dateAcct,
			int docTypeTargetId, boolean counter, String trxName, boolean setOrder)
	{
		MHRProcess to = new MHRProcess (from.getCtx(), 0, trxName);		
		PO.copyValues (from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
		to.setReversal(true);
		to.set_ValueNoCheck ("DocumentNo", null);
		//
		to.setDocStatus (DOCSTATUS_Drafted);		//	Draft
		to.setDocAction(DOCACTION_Complete);
		//
		to.setName(from.getDocumentNo());
		to.setC_DocType_ID(docTypeTargetId);
		to.setC_DocTypeTarget_ID (docTypeTargetId);
		to.setDateAcct (dateAcct);
		//
		to.setHR_Job_ID(from.getHR_Job_ID());
		to.setHR_Department_ID(from.getHR_Department_ID());
		to.setHR_Payroll_ID(from.getHR_Payroll_ID());
		to.setHR_Period_ID(from.getHR_Period_ID());
		to.setC_BPartner_ID(from.getC_BPartner_ID());
		to.setHR_Employee_ID(from.getHR_Employee_ID());
		//
		to.setPosted (false);
		to.setProcessed (false);
		to.setProcessing(false);
		to.saveEx();
		//	Lines
		if (to.copyLinesFrom(from) == 0)
			throw new IllegalStateException("Could not create Payroll Lines");

		return to;
	}
	
	/**
	 * Copy Line from Movement
	 * @param from Human Resource Process
	 * @return return copy lines
	 */
	public int copyLinesFrom (MHRProcess from)
	{
		if (isProcessed() || isPosted() || from == null)
			return 0;
		
		List<MHRMovement> fromLines = MHRMovement.findByProcess(from);
		for (MHRMovement fromMovement: fromLines)
		{
			MHRMovement toMovement = new MHRMovement (getCtx(), 0, from.get_TrxName());
			PO.copyValues (fromMovement, toMovement, fromMovement.getAD_Client_ID(), fromMovement.getAD_Org_ID());
			//toMovement
			toMovement.setIsManual(fromMovement.isManual());
			toMovement.setHR_Concept_Category_ID(fromMovement.getHR_Concept_Category_ID());
			toMovement.setHR_Process_ID(getHR_Process_ID());
			toMovement.setC_BPartner_ID(fromMovement.getC_BPartner_ID());
			toMovement.setHR_Concept_ID(fromMovement.getHR_Concept_ID());
			toMovement.setDescription(fromMovement.getDescription());
			toMovement.setHR_Department_ID(fromMovement.getHR_Department_ID());
			toMovement.setHR_Job_ID(fromMovement.getHR_Job_ID());
			toMovement.setIsPrinted(fromMovement.isPrinted());
			toMovement.setQty(fromMovement.getQty().negate());
			toMovement.setServiceDate(fromMovement.getServiceDate());
			toMovement.setTextMsg(fromMovement.getTextMsg());
			toMovement.setValidFrom(fromMovement.getValidFrom());
			toMovement.setValidTo(fromMovement.getValidTo());
			toMovement.setAD_Rule_ID(fromMovement.getAD_Rule_ID());
			toMovement.setAmount(fromMovement.getAmount().negate());
			toMovement.setC_Activity_ID(fromMovement.getC_Activity_ID());
			toMovement.setC_Campaign_ID(fromMovement.getC_Campaign_ID());
			toMovement.setAD_OrgTrx_ID(fromMovement.getAD_OrgTrx_ID());
			toMovement.setC_ProjectPhase_ID(fromMovement.getC_ProjectPhase_ID());
			toMovement.setC_ProjectPhase_ID(fromMovement.getC_ProjectPhase_ID());
			toMovement.setC_Project_ID(fromMovement.getC_Project_ID());
			toMovement.setUser1_ID(fromMovement.getUser1_ID());
			toMovement.setUser2_ID(fromMovement.getUser2_ID());
			toMovement.setUser3_ID(fromMovement.getUser3_ID());
			toMovement.setUser4_ID(fromMovement.getUser4_ID());
			toMovement.setProcessed(false);
			toMovement.setC_BP_Group_ID(fromMovement.getC_BP_Group_ID());
			toMovement.setHR_Employee_ID(fromMovement.getHR_Employee_ID());
			toMovement.setHR_EmployeeType_ID(fromMovement.getHR_EmployeeType_ID());
			toMovement.setHR_SkillType_ID(fromMovement.getHR_SkillType_ID());
			toMovement.setHR_Payroll_ID(fromMovement.getHR_Payroll_ID());
			toMovement.setHR_Contract_ID(fromMovement.getHR_Payroll().getHR_Contract_ID());

			toMovement.saveEx();		
		}		
		return fromLines.size();
	}	//	copyLinesFrom

	/**
	 * Method use for testing setting the variables to execute rule
	 * @param partnerValue
	 * @param conceptValue
     */
	public MHREmployee setEmployee(String partnerValue,String  conceptValue)
	{

		MBPartner partner = MBPartner.get(getCtx() , partnerValue);
		if (partner == null)
			throw new AdempiereException("@C_BPartner_ID@ @NotFound@ " + partnerValue);
		partnerId = partner.get_ID();

		MHRConcept concept = MHRConcept.getByValue(getCtx(), conceptValue, get_TrxName());
		if (concept == null)
			throw  new AdempiereException("@HR_Concept_ID@ @NotFound@ " +  conceptValue);
		payrollConceptId = concept.get_ID();
		columnType = concept.getColumnType();
		MHRPeriod  payrollPeriod;
		employee = MHREmployee.getActiveEmployee(getCtx(), partnerId, get_TrxName());
		if(getHR_Payroll_ID() > 0)
		{
			payrollId =getHR_Payroll_ID();
		}
		if(getHR_Department_ID() > 0)
		{
			departmentId =getHR_Department_ID();
		}
		if(getHR_Job_ID() > 0)
		{
			jobId =getHR_Job_ID();
		}

		if (getHR_Period_ID() > 0) {
			payrollPeriod = MHRPeriod.getById(getCtx(),  getHR_Period_ID(), get_TrxName());
		} else {
			payrollPeriod = new MHRPeriod(getCtx() , 0 , get_TrxName());
			MPeriod period = MPeriod.get(getCtx(),  getDateAcct() , getAD_Org_ID());
			if(period != null)
			{
				payrollPeriod.setStartDate(period.getStartDate());
				payrollPeriod.setEndDate(period.getEndDate());
			}
			else
			{
				payrollPeriod.setStartDate(getDateAcct());
				payrollPeriod.setEndDate(getDateAcct());
			}
		}
		dateFrom = payrollPeriod.getStartDate();
		dateTo = payrollPeriod.getEndDate();

		// Setting Script context for calcualte rule
		scriptCtx.clear();
		scriptCtx.put("process", this);
		scriptCtx.put("_Process", getHR_Process_ID());
		scriptCtx.put("_Period", payrollPeriod.getHR_Period_ID());
		scriptCtx.put("_Payroll", getHR_Payroll_ID());
		scriptCtx.put("_PayrollValue", Optional.ofNullable(getHR_Payroll().getValue()).orElse(null));
		scriptCtx.put("_Department", getHR_Department_ID());

		logger.info("info data - " +
				Msg.parseTranslation(getCtx(), "@HR_Process_ID@ ") +getHR_Process_ID()+
				Msg.parseTranslation(getCtx(), ", @HR_Period_ID@ :") +getHR_Period_ID()+
				Msg.parseTranslation(getCtx(), ", @HR_Payroll_ID@ : ") +getHR_Payroll_ID()+
				Msg.parseTranslation(getCtx(), ", @HR_Department_ID@ : ") + getHR_Department_ID());

		scriptCtx.put("_From", dateFrom);
		scriptCtx.put("_To", dateTo);
		scriptCtx.put("_Period", payrollPeriod.getPeriodNo());

		scriptCtx.remove("_DateStart");
		scriptCtx.remove("_DateEnd");
		scriptCtx.remove("_Days");
		scriptCtx.remove("_C_BPartner_ID");
		scriptCtx.remove("_HR_Employee_ID");

		scriptCtx.put("_DateStart", employee.getStartDate());
		scriptCtx.put("_DateEnd", employee.getEndDate() == null ? TimeUtil.getDay(2999, 12, 31) : employee.getEndDate());
		scriptCtx.put("_Days", TimeUtil.getDaysBetween(payrollPeriod.getStartDate(),payrollPeriod.getEndDate()) + 1);
		scriptCtx.put("_C_BPartner_ID", employee.getC_BPartner_ID());
		scriptCtx.put("_HR_Employee_ID", employee.getHR_Employee_ID());
		scriptCtx.put("_Employee", employee);

		scriptCtx.remove("_HR_Concept_ID");
		scriptCtx.remove("_HR_Concept");
		scriptCtx.put("_HR_Concept_ID", concept.getHR_Concept_ID());
		scriptCtx.put("_HR_Concept", concept);
		scriptCtx.remove("_HR_PayrollConcept_ID");
		//m_scriptCtx.put("_HR_PayrollConcept_ID", payrollConcept.getHR_PayrollConcept_ID());
		//Define movement cache
		movements = new Hashtable<Integer, MHRMovement>();
		//Load Payroll Concept
		payrollConcepts = MHRPayrollConcept.getPayrollConcepts(this);
		//Load the Manual movement
		loadMovements(movements, employee.getC_BPartner_ID());
		//Remove movement if this is calculated this way can be calculated again
		if (!concept.isManual())
			movements.remove(concept.get_ID());

		return employee;
	}
	
	/**********************************************************************************
	 * Helper Method for Get Amount from commission                                   *
	 **********************************************************************************/
	
	/**
	 * Get Commission of Employee sales representative from Commission Run
	 * @param bPartnerId
	 * @param from
	 * @param to
	 * @param docBasisType
	 * @return
	 */
	public double getCommissionAmt(int bPartnerId, Timestamp from, Timestamp to, String docBasisType) {
		BigDecimal value = MCommission.getCommissionAmt(bPartnerId, from, to, docBasisType);
		//	Validate value
		if(value == null)
			return 0.0;
		//	Default
		return value.doubleValue();
	}
	
	/**
	 * Get commission amount for current employee
	 * @param from
	 * @param to
	 * @param docBasisType
	 * @return
	 */
	public double getCommissionAmt(Timestamp from, Timestamp to, String docBasisType) {
		return getCommissionAmt(partnerId, from, to, docBasisType);
	}
	
	/**
	 * Get commission amount for current employee on current period with a doc basis type
	 * @param docBasisType
	 * @return
	 */
	public double getCommissionAmt(String docBasisType) {
		return getCommissionAmt(dateFrom, dateTo, docBasisType);
	}
	
	/**
	 * Get commission amount for current employee and current period and all doc basis type
	 * @return
	 */
	public double getCommissionAmt() {
		return getCommissionAmt(null);
	}

	/** Reversal Flag		*/
	private boolean isReversal = false;

	/**
	 * 	Set Reversal
	 *	@param isReversal reversal
	 */
	public void setReversal(boolean isReversal)
	{
		this.isReversal = isReversal;
	}	//	setReversal

	/**
	 * 	Is Reversal
	 *	@return reversal
	 */
	public boolean isReversal()
	{
		return isReversal;
	}	//	isReversal
	
}	//	MHRProcess
