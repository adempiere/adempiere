/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
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

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
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
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;

/**
 * HR Process Model
 *
 *  @author oscar.gomez@e-evolution.com, e-Evolution http://www.e-evolution.com
 *			<li> Original contributor of Payroll Functionality
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org 
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962
 * @author Cristina Ghita, www.arhipac.ro
 */
public class MHRProcess extends X_HR_Process implements DocAction
{
	

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
	private static CLogger	s_log	= CLogger.getCLogger (MHRProcess.class);
	public static final String CONCEPT_PP_COST_COLLECTOR_LABOR = "PP_COST_COLLECTOR_LABOR"; // HARDCODED
	Object description = null;


	private static StringBuffer s_scriptImport = new StringBuffer(	 " import org.eevolution.model.*;" 
			+" import org.compiere.model.*;"
			+" import org.adempiere.model.*;"
			+" import org.compiere.util.*;"
			+" import java.math.*;"
			+" import java.sql.*;");

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
			log.warning("Changed Org to Context=" + context_AD_Org_ID);
		}
		setC_DocType_ID(getC_DocTypeTarget_ID());

		return true;
	}       
	
	/**
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	public boolean processIt(String processAction) 
	{
		DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(processAction, getDocAction());
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
		setDocAction(DOCACTION_Prepare);
		return true;
	}	//	invalidateIt


	/**************************************************************************
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid)
	 */
	public String prepareIt()
	{
		log.info("prepareIt - " + toString());

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
		{
			return DocAction.STATUS_Invalid;
		}

		//	Std Period open?
		MHRPeriod period = MHRPeriod.get(getCtx(), getHR_Period_ID());
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
			throw new AdempiereException(exception);
		}
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		//
		m_justPrepared = true;
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
		if (!m_justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		//	User Validation
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (m_processMsg != null)
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
		log.info("rejectIt - " + toString());
		return true;
	}	//	rejectIt

	/**
	 * 	Post Document - nothing
	 * 	@return true if success
	 */
	public boolean postIt() {
		log.info("postIt - " + toString());
		return false;
	}	//	postIt


	/**
	* Void Document.
	* Set Movement Line Amount to 0
	* @return true if success
	*/
	public boolean voidIt() 
	{

	log.info("voidIt - " + toString());
	// Before Void
	m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
	if (m_processMsg != null)
		return false;



	if (DOCSTATUS_Closed.equals(getDocStatus())
	|| DOCSTATUS_Reversed.equals(getDocStatus())
	|| DOCSTATUS_Voided.equals(getDocStatus()))
	{
		m_processMsg = "Document Closed: " + getDocStatus();
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
		List<MHRMovement> lines = MHRMovement.getLinesForProcess(this);
		for (MHRMovement movement : lines)
		{
	
			BigDecimal oldAmount = movement.getAmount();
	
			if (oldAmount.signum() != 0)
		
			{	
				movement.setAmount(Env.ZERO);
				movement.setDescription("Void (" + oldAmount + ")");
				movement.save(get_TrxName());
			}
		}
		//
		setProcessed(true);
		setDocStatus(DOCSTATUS_Voided); // need to set & save docstatus to be able to check it in MHRProcess.voidIt()
		saveEx();
	}
	else
	{
		return reverseCorrectIt();
	}
	// After Void
	m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
	if (m_processMsg != null)
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
			log.info(toString());
			
			// Before Close
			m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
			if (m_processMsg != null)
				return false;
			
			setProcessed(true);
			setDocAction(DOCACTION_None);
			
			// After Close
			m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
			if (m_processMsg != null)
				return false;
				
			return true;	

		}     	
		return false;
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

		MPeriod.testPeriodOpen(getCtx(), getDateAcct(), MPeriodControl.DOCBASETYPE_Payroll, getAD_Org_ID());
		
		MHRProcess reversal = copyFrom (this, getDateAcct(), getC_DocType_ID(), false, get_TrxName(), true);
		if (reversal == null)
		{
			m_processMsg = "Could not create Payroll Process Reversal";
			return false;
		}
		reversal.setReversal_ID(getHR_Process_ID());
		reversal.setProcessing (false);
		reversal.setDocStatus(DOCSTATUS_Reversed);
		reversal.setDocAction(DOCACTION_None);
		reversal.setProcessed(true);
		reversal.setName("("+reversal.getDocumentNo()+" -> "+getDocumentNo()+")");
		reversal.saveEx(get_TrxName());
		
		m_processMsg = reversal.getDocumentNo();
		setProcessed(true);
		setReversal_ID(reversal.getHR_Process_ID());
		setDocStatus(DOCSTATUS_Reversed);	//	may come from void
		setDocAction(DOCACTION_None);
		setProcessed(true);
		setDocAction(DOCACTION_None);
		setName("(" + getName() + " <- "+reversal.getDocumentNo() + ")");
		saveEx();

		// After reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (m_processMsg != null)
			return false;
		
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
		
		// After reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;
		
		return false;
	}	//	reverseAccrualIt


	/**
	 * 	Re-activate.
	 * 	@return true if success
	 */
	public boolean reActivateIt() {
		log.info("reActivateIt - " + toString());

		// Before reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (m_processMsg != null)
			return false;
		
		//	Can we delete posting
		MPeriod.testPeriodOpen(getCtx(), getDateAcct(), MPeriodControl.DOCBASETYPE_Payroll, getAD_Org_ID());

		//	Delete 
		String sql = "DELETE FROM HR_Movement WHERE HR_Process_ID =" + this.getHR_Process_ID() + " AND IsManual = 'N'" ;
		int no = DB.executeUpdateEx(sql, get_TrxName());
		log.fine("HR_Process deleted #" + no);

		//	Delete Posting
		no = MFactAcct.deleteEx(MHRProcess.Table_ID, getHR_Process_ID(), get_TrxName());
		log.fine("Fact_Acct deleted #" + no);

		setDocAction(DOCACTION_Complete);
		setProcessed(false);
				
		// After reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (m_processMsg != null)
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
		return m_processMsg;
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
		.setParameters(new Object[]{getHR_Process_ID(), partnerId})
		.list();
		for (MHRMovement movement : list)
		{
			if(movements.containsKey(movement.getHR_Concept_ID()))
			{
				MHRMovement lastM = movements.get(movement.getHR_Concept_ID());
				String columntype = lastM.getColumnType();
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
		try
		{
			String text = "";
			if (rule.getScript() != null)
			{
				text = rule.getScript().trim().replaceAll("\\bget", "process.get")
				.replace(".process.get", ".get");
			}
			String resultType = "double";
			if  (MHRAttribute.COLUMNTYPE_Date.equals(columnType))
				resultType = "Timestamp";
			else if  (MHRAttribute.COLUMNTYPE_Text.equals(columnType))
				resultType = "String";
			final String script =
				s_scriptImport.toString()
				+" " + resultType + " result = 0;"
				+" String description = null;"
				+ text;
			Scriptlet engine = new Scriptlet (Scriptlet.VARIABLE, script, scriptCtx);
			Exception ex = engine.execute();
			if (ex != null)
			{
				throw ex;
			}
			result = engine.getResult(false);
			description = engine.getDescription();
		}
		catch (Exception e)
		{
			throw new AdempiereException("@HR_Employee_ID@ : " + employee.getName() + " @HR_Concept_ID@ " + concept.getValue() + " -> "+ concept.getName() + " @AD_Rule_ID@=" + rule.getValue() + " Execution error " + e.getLocalizedMessage());
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

		List<MPPCostCollector> listCollector = new Query(getCtx(), MPPCostCollector.Table_Name,
				whereClause.toString(), get_TrxName())
		.setOnlyActiveRecords(true)
		.setParameters(params)
		.setOrderBy(MPPCostCollector.COLUMNNAME_PP_Cost_Collector_ID+" DESC") 
		.list();


		for (MPPCostCollector cc : listCollector)
		{
			createMovementForCostCollector(partnerId, cc);
		}
	}

	/**
	 * create movement for cost collector
	 * @param partnerId
	 * @param costCollector
	 * @return
	 */
	private MHRMovement createMovementForCostCollector(int partnerId, I_PP_Cost_Collector costCollector)
	{
		//get the concept that should store the labor
		MHRConcept concept = MHRConcept.forValue(getCtx(), CONCEPT_PP_COST_COLLECTOR_LABOR);

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
		if (attribute == null)
		{
			throw new AdempiereException(); // TODO ?? is necessary
		}

		if (MHRConcept.TYPE_RuleEngine.equals(concept.getType()))
		{
			Object result = null;

			scriptCtx.put("_CostCollector", costCollector);
			try
			{
				result = executeScript(concept , attribute.getAD_Rule_ID(), attribute.getColumnType());
				log.info(Msg.parseTranslation(getCtx(), "@ScriptResult@ -> @HR_Concept_ID@ @Name@ ") + concept.getName() + " = " + result.toString());
			}
			finally
			{
				scriptCtx.remove("_CostCollector");
			}
			if(result == null)
			{
				// TODO: throw exception ???
				log.warning("Variable (result) is null");
			}

			//get employee
			MHREmployee employee = MHREmployee.getActiveEmployee(getCtx(), partnerId, get_TrxName());
			//create movement
			MHRMovement movement = new MHRMovement(this, concept);
			movement.setHR_Attribute_ID(attribute.getHR_Attribute_ID());
			movement.setC_BPartner_ID(partnerId);
			movement.setC_BP_Relation_ID(attribute.getC_BP_Relation_ID());
			movement.setAD_Rule_ID(attribute.getAD_Rule_ID());
			movement.setValidFrom(dateFrom);
			movement.setValidTo(dateTo);
			movement.setPP_Cost_Collector_ID(costCollector.getPP_Cost_Collector_ID());
			movement.setIsManual(true);
			movement.setColumnValue(result);
			movement.setProcessed(true);
			int bpGroupId = DB.getSQLValue(null, "SELECT C_BP_Group_ID FROM C_BPartner WHERE C_BPartner_ID=?", this.partnerId);
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
		scriptCtx.put("process", this);
		scriptCtx.put("_Process", getHR_Process_ID());
		scriptCtx.put("_Period", getHR_Period_ID());
		scriptCtx.put("_Payroll", getHR_Payroll_ID());
		scriptCtx.put("_Department", getHR_Department_ID());

		log.info("info data - " +
				Msg.parseTranslation(getCtx(), "@HR_Process_ID@ ") +getHR_Process_ID()+
				Msg.parseTranslation(getCtx(), ", @HR_Period_ID@ :") +getHR_Period_ID()+
				Msg.parseTranslation(getCtx(), ", @HR_Payroll_ID@ : ") +getHR_Payroll_ID()+
				Msg.parseTranslation(getCtx(), ", @HR_Department_ID@ : ") + getHR_Department_ID());
		
		MHRPeriod payrollPeriod = null;
		
		if (getHR_Period_ID() > 0)
		{
			payrollPeriod = MHRPeriod.get(getCtx(),  getHR_Period_ID());
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
		scriptCtx.put("_From", dateFrom);
		scriptCtx.put("_To", dateTo);
		scriptCtx.put("_Period", payrollPeriod.getPeriodNo());
				
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

		// RE-Process, delete movement except concept type Incidence 
		int no = DB.executeUpdateEx("DELETE FROM HR_Movement m WHERE HR_Process_ID=? AND IsManual<>?",
				new Object[]{getHR_Process_ID(), true},
				get_TrxName());
		log.info(Msg.parseTranslation(getCtx() , "@HR_Movement_ID@ @Deleted@ #") + no);

		payrollConcepts = MHRPayrollConcept.getPayrollConcepts(this);
		MBPartner[] employeeList = MHREmployee.getEmployees(this);
		//
		int count = 1;
		for(MBPartner partner : employeeList)	//=============================================================== Employee
		{
			log.info(Msg.parseTranslation(getCtx() , "@HR_Employee_ID@ # ") + count +Msg.parseTranslation(getCtx() , " @BPValue@ ") + partner.getValue() +  Msg.parseTranslation(getCtx(), " @BPName@ ") + partner.getName());
			count++;
			partnerId = partner.get_ID();

			employee = MHREmployee.getActiveEmployee(getCtx(), partnerId, get_TrxName());
			scriptCtx.remove("_DateStart");
			scriptCtx.remove("_DateEnd");
			scriptCtx.remove("_Days");
			scriptCtx.remove("_C_BPartner_ID");
			scriptCtx.remove("_HR_Employee_ID");

			scriptCtx.put("_DateStart", employee.getStartDate());
			scriptCtx.put("_DateEnd", employee.getEndDate() == null ? TimeUtil.getDay(2999, 12, 31) : employee.getEndDate());
			scriptCtx.put("_Days", TimeUtil.getDaysBetween(payrollPeriod.getStartDate(),payrollPeriod.getEndDate()) + 1);
			scriptCtx.put("_C_BPartner_ID", partner.getC_BPartner_ID());
			scriptCtx.put("_HR_Employee_ID", employee.getHR_Employee_ID());

			if(getHR_Period_ID() > 0)
				createCostCollectorMovements(partner.get_ID(), payrollPeriod);

			movements.clear();
			loadMovements(movements, partnerId);
			//
			for(MHRPayrollConcept payrollConcept : payrollConcepts) // ==================================================== Concept
			{
				payrollConceptId = payrollConcept.getHR_Concept_ID();
				MHRConcept concept = MHRConcept.get(getCtx(), payrollConceptId);
				boolean printed = payrollConcept.isPrinted() || concept.isPrinted();
				MHRMovement movement = movements.get(concept.get_ID()); // as it's now recursive, it can happen that the concept is already generated
				if (movement == null) {
					scriptCtx.remove("_HR_Concept_ID");
					scriptCtx.put("_HR_Concept_ID", concept.getHR_Concept_ID());
					scriptCtx.remove("_HR_PayrollConcept_ID");
					scriptCtx.put("_HR_PayrollConcept_ID", payrollConcept.getHR_PayrollConcept_ID());
					createMovementFromConcept(concept, printed);
                    movement = movements.get(concept.get_ID());
					movement.setHR_Payroll_ID(payrollConcept.getHR_Payroll_ID());
					movement.setHR_PayrollConcept_ID(payrollConcept.getHR_PayrollConcept_ID());
					if (payrollPeriod != null)
						movement.setPeriodNo(payrollPeriod.getPeriodNo());
				}
				if (movement == null)
				{
					throw new AdempiereException("Concept " + concept.getValue() + " not created");
				}
			} // concept

			// Save movements:
			for (MHRMovement movement: movements.values())
			{
				MHRConcept concept = (MHRConcept) movement.getHR_Concept();
				if (concept != null && concept.get_ID() > 0) {
					if (concept.isManual()) {
						log.fine("Skip saving " + movement);
					} else {
						boolean saveThisRecord = concept.isSaveInHistoric() || movement.isPrinted() || concept.isPaid() || concept.isPrinted();
						if (saveThisRecord)
							movement.saveEx();
					}
				}
			}
		} // for each employee
		//
		// Save period & finish
		if(getHR_Period_ID()>0)
		{
			payrollPeriod.setProcessed(true);
			payrollPeriod.saveEx();
		}
	}

	/**
	 * Method use to save of calculate on the fly when payroll process is completed
	 * @param concept
	 * @return HR Movement
	 */
	public MHRMovement SaveCalculatedMovement(MHRConcept concept, BigDecimal qty ,BigDecimal amount , String description)
	{
		if (concept == null || concept.getHR_Concept_ID() == 0)
			throw new AdempiereException("@HR_Concept_ID@ @NotFound@");
		//Delete previous calculated
		MHRMovement.getBy(this, concept.getHR_Concept_ID() , partnerId)
				.stream()
				.filter(movement -> movement != null)
				.forEach(movement -> movement.deleteEx(true));
		//Create Calculated Movement
		MHRMovement calculatedMovement =  createMovementFromConcept(concept,false);
		Optional.ofNullable(qty).ifPresent(q -> calculatedMovement.setAmount(q));
		Optional.ofNullable(amount).ifPresent(a -> calculatedMovement.setAmount(a));
		Optional.ofNullable(description).ifPresent(d -> calculatedMovement.setDescription(d));
		calculatedMovement.saveEx();
		return  calculatedMovement;
	}

	/**
	 * Method use to create a movement
	 * @param concept
	 * @param isPrinted
	 * @return
	 */
	private MHRMovement createMovementFromConcept(MHRConcept concept, boolean isPrinted) {
		log.info("Calculating -> "+ Msg.parseTranslation(getCtx(), " @HR_Concept_ID@ "+ concept.getValue() + " -> " + concept.getName()));
		columnType = concept.getColumnType();
		MHRAttribute attribute = MHRAttribute.getAttribute(concept , employee, getHR_Payroll_ID(),  dateFrom ,dateTo);
		if (attribute == null || concept.isManual())
		{
			log.info("Skip concept "+concept+" - attribute not found");
			MHRMovement dummyMovement = new MHRMovement (getCtx(), 0, get_TrxName());
			dummyMovement.setSeqNo(concept.getSeqNo());
			dummyMovement.setIsManual(true); // to avoid landing on movement table
			movements.put(concept.getHR_Concept_ID(), dummyMovement);
			return dummyMovement;
		}
		I_HR_Period payrollPeriod = getHR_Period();

		log.info(Msg.parseTranslation(getCtx(), "@HR_Concept_ID@ : ")+ concept.getName());
		MHRMovement movement = new MHRMovement (getCtx(), 0, get_TrxName());
		movement.setAD_Org_ID(employee.getAD_Org_ID());
		movement.setSeqNo(concept.getSeqNo());
		movement.setHR_Attribute_ID(attribute.getHR_Attribute_ID());
		Optional.ofNullable(payrollPeriod).ifPresent(period -> movement.setPeriodNo(period.getPeriodNo()));
		movement.setC_BPartner_ID(partnerId);
		movement.setC_BP_Relation_ID(attribute.getC_BP_Relation_ID());
		movement.setHR_Concept_ID(concept.getHR_Concept_ID());
		movement.setHR_Concept_Category_ID(concept.getHR_Concept_Category_ID());
		movement.setHR_Process_ID(getHR_Process_ID());
		movement.setColumnType(columnType);
		movement.setAD_Rule_ID(attribute.getAD_Rule_ID());
		movement.setValidFrom(dateFrom);
		movement.setValidTo(dateTo);
		movement.setIsPrinted(isPrinted);
		movement.setIsManual(concept.isManual());
		int bpGroupId = DB.getSQLValue(null, "SELECT C_BP_Group_ID FROM C_BPartner WHERE C_BPartner_ID=?", partnerId);
		movement.setC_BP_Group_ID(bpGroupId);
		movement.setEmployee(employee);

		if (MHRConcept.TYPE_RuleEngine.equals(concept.getType()))
		{
			log.info(Msg.parseTranslation(getCtx() , "@Processing@ -> @AD_Rule_ID@ @To@ @HR_Concept_ID@ ") + concept.getValue());
			if (activeConceptRule.contains(concept)) {
				throw new AdempiereException("Recursion loop detected in concept " + concept.getValue());
			}
			activeConceptRule.add(concept);
			Object result = executeScript(concept , attribute.getAD_Rule_ID(), attribute.getColumnType());
			log.info(Msg.parseTranslation(getCtx(), "@ScriptResult@ -> @HR_Concept_ID@ @Name@ ") + concept.getName() + " = " + result.toString());
			activeConceptRule.remove(concept);
			if (result == null)
			{
				// TODO: throw exception ???
				log.warning("Variable (result) is null");
				return movement;
			}
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
		MHRConcept concept = MHRConcept.forValue(getCtx(), conceptValue.trim());

		if (concept == null)
		{
			return 0; // TODO throw exception ?
		}

		MHRMovement movement = movements.get(concept.get_ID());
		if (movement == null) {
			createMovementFromConcept(concept, concept.isPrinted());
			movement = movements.get(concept.get_ID());
		}
		if (movement == null)
		{
			throw new AdempiereException("Concept " + concept.getValue() + " not created");
		}

		String type = movement.getColumnType();
		if (MHRMovement.COLUMNTYPE_Amount.equals(type))
		{
			return movement.getAmount().doubleValue();
		}
		else if (MHRMovement.COLUMNTYPE_Quantity.equals(type))
		{
			return movement.getQty().doubleValue();
		}
		else
		{
			// TODO: throw exception ?
			return 0;
		}
	} // getConcept

	/**
	 * Helper Method : get the value of the concept string type
	 * @param pconcept
	 * @return String value of concept
	 */
	public String getConceptString (String pconcept)
	{
		MHRConcept concept = MHRConcept.forValue(getCtx(), pconcept.trim());

		if (concept == null)
		{
			return null; // TODO throw exception ?
		}

		MHRMovement m = movements.get(concept.get_ID());
		if (m == null) {
			createMovementFromConcept(concept, concept.isPrinted());
			m = movements.get(concept.get_ID());
		}

		String type = m.getColumnType();
		if (MHRMovement.COLUMNTYPE_Text.equals(type))
		{
			return m.getTextMsg();
		}
		else
		{
			// TODO: throw exception ?
			return null;
		}
	} // getConceptString

	/**
	 * Helper Method : get the value of the concept date type
	 * @param pconcept
	 * @return Timestamp value of concept
	 */
	public Timestamp getConceptDate (String pconcept)
	{
		MHRConcept concept = MHRConcept.forValue(getCtx(), pconcept.trim());

		if (concept == null)
		{
			return null; // TODO throw exception ?
		}

		MHRMovement m = movements.get(concept.get_ID());
		if (m == null) {
			createMovementFromConcept(concept, concept.isPrinted());
			m = movements.get(concept.get_ID());
		}

		String type = m.getColumnType();
		if (MHRMovement.COLUMNTYPE_Text.equals(type))
		{
			return m.getServiceDate();
		}
		else
		{
			// TODO: throw exception ?
			return null;
		}
	} // getConceptDate

	/**
	 * Helper Method : sets the value of a concept
	 * @param conceptValue
	 * @param value
	 */
	public void setConcept (String conceptValue, double value)
	{
		try
		{
			MHRConcept concept = MHRConcept.forValue(getCtx(), conceptValue);
			if (concept == null)
			{
				return; // TODO throw exception
			}
			MHRMovement movement = new MHRMovement(getCtx(), 0, get_TrxName());
			MHREmployee employee = MHREmployee.getActiveEmployee(getCtx(), partnerId, get_TrxName());
			movement.setSeqNo(concept.getSeqNo());
			movement.setColumnType(concept.getColumnType());
			movement.setColumnValue(BigDecimal.valueOf(value));

			movement.setHR_Process_ID(getHR_Process_ID());
			movement.setHR_Concept_ID(payrollConceptId);
			movement.setC_BPartner_ID(partnerId);
			movement.setDescription("Added From Rule"); // TODO: translate
			movement.setValidFrom(dateTo);
			movement.setValidTo(dateTo);

			movement.setHR_Concept_Category_ID(concept.getHR_Concept_Category_ID());
			movement.setIsManual(concept.isManual());
			int bpGroupId = DB.getSQLValue(null, "SELECT C_BP_Group_ID FROM C_BPartner WHERE C_BPartner_ID=?", partnerId);
			movement.setC_BP_Group_ID(bpGroupId);
			movement.setEmployee(employee);
			movement.saveEx();
		} 
		catch(Exception e)
		{
			s_log.warning(e.getMessage());
		}
	} // setConcept
	
	/* Helper Method : sets the value of a concept and set if isManual
	* @param conceptValue
	* @param value
	* @param isManual
	*/
	public void setConcept (String conceptValue,double value,boolean isManual)
	{
		try
		{
			MHRConcept concept = MHRConcept.forValue(getCtx(), conceptValue);
			if (concept == null)
			{
				return; // TODO throw exception
			}
			MHRMovement movement = new MHRMovement(Env.getCtx(),0,get_TrxName());
			MHREmployee employee = MHREmployee.getActiveEmployee(getCtx(), partnerId, get_TrxName());
			movement.setSeqNo(concept.getSeqNo());
			movement.setColumnType(concept.getColumnType());
			if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Amount))
				movement.setAmount(BigDecimal.valueOf(value));
			else if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity))
				movement.setQty(BigDecimal.valueOf(value));
			else
				return;
			movement.setHR_Process_ID(getHR_Process_ID());
			movement.setHR_Concept_ID(concept.getHR_Concept_ID());
			movement.setC_BPartner_ID(partnerId);
			movement.setDescription("Added From Rule"); // TODO: translate
			movement.setValidFrom(dateTo);
			movement.setValidTo(dateTo);
			movement.setIsManual(isManual);
			movement.setHR_Concept_Category_ID(concept.getHR_Concept_Category_ID());
			movement.setIsManual(concept.isManual());
			int bpGroupId = DB.getSQLValue(null, "SELECT C_BP_Group_ID FROM C_BPartner WHERE C_BPartner_ID=?", partnerId);
			movement.setC_BP_Group_ID(bpGroupId);
			movement.setEmployee(employee);
			movement.saveEx();
		} 
		catch(Exception e)
		{
			s_log.warning(e.getMessage());
		}
	} // setConcept

	/**
	 * Helper Method : get the sum of the concept values, grouped by the concept type
	 * @param typeValue
	 * @return
	 */
	public double getConceptType (String typeValue)
	{
		final MHRConceptType conceptType = MHRConceptType.forValue(getCtx(), typeValue);
		if (conceptType == null)
			return 0.0;

		double value = 0.0;
		for(MHRPayrollConcept payrollConcept : payrollConcepts)
		{
			MHRConcept concept = MHRConcept.get(getCtx(), payrollConcept.getHR_Concept_ID());
			if(concept.getHR_Concept_Type_ID() == conceptType.get_ID())
			{
				MHRMovement movement = movements.get(payrollConcept.getHR_Concept_ID());
				if (movement == null)
					createMovementFromConcept(concept, concept.isPrinted());
				movement = movements.get(concept.get_ID());

				if (movement != null)
				{
					String columnType = movement.getColumnType();
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
		final MHRConceptCategory conceptCategory = MHRConceptCategory.forValue(getCtx(), categoryValue);
		if (conceptCategory == null)
			return 0.0;

		double value = 0.0;
		for(MHRPayrollConcept payrollConcept : payrollConcepts)
		{
			MHRConcept concept = MHRConcept.get(getCtx(), payrollConcept.getHR_Concept_ID());
			if(concept.getHR_Concept_Category_ID() == conceptCategory.get_ID())
			{
				MHRMovement movement = movements.get(payrollConcept.getHR_Concept_ID());
				if (movement == null)
					createMovementFromConcept(concept, concept.isPrinted());
				movement = movements.get(concept.get_ID());

				if (movement != null)
				{
					String columnType = movement.getColumnType();
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
				"l.AD_Client_ID = ? AND " +
				"(? BETWEEN lv.ValidFrom AND lv.ValidTo ) AND " +
				"(? BETWEEN ll.MinValue AND	ll.MaxValue)";
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
	 * Helper Method : Get Attribute [get Attribute to search key concept ]
	 * @param conceptValue - Value to Concept
	 * @return	Amount of concept, applying to employee
	 */ 
	public double getAttribute (String conceptValue)
	{
		MHRConcept concept = MHRConcept.forValue(getCtx(), conceptValue);
		if (concept == null)
			return 0;

		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		// check ValidFrom:
		whereClause.append(MHRAttribute.COLUMNNAME_ValidFrom + "<=?");
		params.add(dateFrom);
		//check client
		whereClause.append(" AND AD_Client_ID = ?");
		params.add(getAD_Client_ID());
		
		if(payrollId > 0)
		{
			whereClause.append(" AND (HR_Payroll_ID=? OR HR_Payroll_ID IS NULL)");
			params.add(payrollId);
		}
		if(departmentId > 0)
		{
			whereClause.append(" AND (HR_Department_ID=? OR HR_Department_ID IS NULL)");
			params.add(departmentId);
		}
		if(jobId > 0)
		{
			whereClause.append(" AND (HR_Job_ID=? OR HR_Job_ID IS NULL)");
			params.add(jobId);
		}

		//check concept
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Concept c WHERE c.HR_Concept_ID=HR_Attribute.HR_Concept_ID" 
				+ " AND c.Value = ?)");
		params.add(conceptValue);
		//
		if (!concept.getType().equals(MHRConcept.TYPE_Information))
		{
			whereClause.append(" AND " + MHRAttribute.COLUMNNAME_C_BPartner_ID + " = ?");
			params.add(partnerId);
		}

		MHRAttribute attribute = new Query(getCtx(), MHRAttribute.Table_Name, whereClause.toString(), get_TrxName())
		.setParameters(params)
		.setOrderBy(MHRAttribute.COLUMNNAME_ValidFrom + " DESC")
		.first();
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
	} // getAttribute


	/**
	 * 	Helper Method : Get Attribute [get Attribute to search key concept ]
	 *  @param conceptValue
	 *  @return ServiceDate
	 */ 
	public Timestamp getAttributeDate (String conceptValue)
	{
		MHRConcept concept = MHRConcept.forValue(getCtx(), conceptValue);
		if (concept == null)
			return null;

		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		//check client
		whereClause.append("AD_Client_ID = ?");
		params.add(getAD_Client_ID());
		//check concept
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Concept c WHERE c.HR_Concept_ID=HR_Attribute.HR_Concept_ID" 
				+ " AND c.Value = ?)");
		params.add(conceptValue);
		//
		if (!concept.getType().equals(MHRConcept.TYPE_Information))
		{
			whereClause.append(" AND " + MHRAttribute.COLUMNNAME_C_BPartner_ID + " = ?");
			params.add(partnerId);
		}

		MHRAttribute attribute = new Query(getCtx(), MHRAttribute.Table_Name, whereClause.toString(), get_TrxName())
		.setParameters(params)
		.setOrderBy(MHRAttribute.COLUMNNAME_ValidFrom + " DESC")
		.first();
		if (attribute == null)
			return null;

		return attribute.getServiceDate();
	} // getAttributeDate

	/**
	 * 	Helper Method : Get Attribute [get Attribute to search key concept ]
	 *  @param conceptValue
	 *  @return TextMsg
	 */ 
	public String getAttributeString (String conceptValue)
	{
		MHRConcept concept = MHRConcept.forValue(getCtx(), conceptValue);
		if (concept == null)
			return null;

		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		//check client
		whereClause.append("AD_Client_ID = ?");
		params.add(getAD_Client_ID());
		//check concept
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Concept c WHERE c.HR_Concept_ID=HR_Attribute.HR_Concept_ID" 
				+ " AND c.Value = ?)");
		params.add(conceptValue);
		//
		if (!concept.getType().equals(MHRConcept.TYPE_Information))
		{
			whereClause.append(" AND " + MHRAttribute.COLUMNNAME_C_BPartner_ID + " = ?");
			params.add(partnerId);
		}

		MHRAttribute attribute = new Query(getCtx(), MHRAttribute.Table_Name, whereClause.toString(), get_TrxName())
		.setParameters(params)
		.setOrderBy(MHRAttribute.COLUMNNAME_ValidFrom + " DESC")
		.first();
		if (attribute == null)
			return null;

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
	 * Helper Method : Concept for a range from-to in periods.
	 * Periods with values of 0 -1 1, etc. actual previous one period, next period
	 * 0 corresponds to actual period.
	 * @param conceptValue concept key(value)
	 * @param periodFrom the search is done by the period value, it helps to search from previous years
	 * @param periodTo
	 */
	public double getConcept (String conceptValue, int periodFrom, int periodTo)
	{
		return getConcept(conceptValue, null, periodFrom,periodTo);
	} // getConcept

	/**
	 *  Helper Method : Concept by range from-to in periods from a different payroll
	 *  periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 *  @param conceptValue
	 *  @param payrollValue is the value of the payroll.
	 *  @param periodFrom
	 *  @param periodTo the search is done by the period value, it helps to search from previous years
	 */
	public double getConcept(String conceptValue, String payrollValue,int periodFrom,int periodTo)
	{
		int payroll_id;
		if (payrollValue == null)
		{
			payroll_id = getHR_Payroll_ID();
		}
		else
		{
			payroll_id = MHRPayroll.forValue(getCtx(), payrollValue).get_ID();
		}

		MHRConcept concept = MHRConcept.forValue(getCtx(), conceptValue);
		if (concept == null)
			return 0.0;
		//
		// Detect field name
		final String fieldName;
		if (MHRConcept.COLUMNTYPE_Quantity.equals(concept.getColumnType()))
		{
			fieldName = MHRMovement.COLUMNNAME_Qty;
		}
		else if (MHRConcept.COLUMNTYPE_Amount.equals(concept.getColumnType()))
		{
			fieldName = MHRMovement.COLUMNNAME_Amount;
		}
		else
		{
			return 0; // TODO: throw exception?
		}
		//
		MHRPeriod period = MHRPeriod.get(getCtx(), getHR_Period_ID());
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		//check client
		whereClause.append("AD_Client_ID = ?");
		params.add(getAD_Client_ID());
		//check concept
		whereClause.append(" AND " + MHRMovement.COLUMNNAME_HR_Concept_ID + "=?");
		params.add(concept.get_ID());
		//check partner
		whereClause.append(" AND " + MHRMovement.COLUMNNAME_C_BPartner_ID  + "=?");
		params.add(partnerId);
		//
		//check process and payroll
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Process p"
				+" INNER JOIN HR_Period pr ON (pr.HR_Period_id=p.HR_Period_ID)"
				+" WHERE HR_Movement.HR_Process_ID = p.HR_Process_ID" 
				+" AND p.HR_Payroll_ID=?");

		params.add(payroll_id);
		if (periodFrom < 0)
		{
			whereClause.append(" AND pr.PeriodNo >= ?");
			params.add(period.getPeriodNo() +periodFrom);
		}
		if (periodTo > 0)
		{
			whereClause.append(" AND pr.PeriodNo <= ?");
			params.add(period.getPeriodNo() +periodTo);
		}
		whereClause.append(")");
		//
		StringBuffer sql = new StringBuffer("SELECT COALESCE(SUM(").append(fieldName).append("),0) FROM ").append(MHRMovement.Table_Name)
		.append(" WHERE ").append(whereClause);
		BigDecimal value = DB.getSQLValueBDEx(get_TrxName(), sql.toString(), params);
		return value.doubleValue();

	} // getConcept

	/**
	 * Helper Method: gets Concept value of a payrroll between 2 dates
	 * @param conceptValue
	 * @param payrollValue
	 * @param from
	 * @param to
	 * */
	public double getConcept (String conceptValue, String payrollValue,Timestamp from,Timestamp to)
	{
		int payroll_id;
		if (payrollValue == null)
		{
			payroll_id = getHR_Payroll_ID();
		}
		else
		{
			payroll_id = MHRPayroll.forValue(getCtx(), payrollValue).get_ID();
		}
		
		MHRConcept concept = MHRConcept.forValue(getCtx(), conceptValue);
		if (concept == null)
			return 0.0;
		//
		// Detect field name
		final String fieldName;
		if (MHRConcept.COLUMNTYPE_Quantity.equals(concept.getColumnType()))
		{
			fieldName = MHRMovement.COLUMNNAME_Qty;
		}
		else if (MHRConcept.COLUMNTYPE_Amount.equals(concept.getColumnType()))
		{
			fieldName = MHRMovement.COLUMNNAME_Amount;
		}
		else
		{
			return 0; // TODO: throw exception?
		}
		//
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		//check client
		whereClause.append("AD_Client_ID = ?");
		params.add(getAD_Client_ID());
		//check concept
		whereClause.append(" AND " + MHRMovement.COLUMNNAME_HR_Concept_ID + "=?");
		params.add(concept.get_ID());
		//check partner
		whereClause.append(" AND " + MHRMovement.COLUMNNAME_C_BPartner_ID  + "=?");
		params.add(partnerId);
		//Adding dates 
		whereClause.append(" AND validTo BETWEEN ? AND ?");
		params.add(from);
		params.add(to);
		//
		//check process and payroll
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Process p"
							+" INNER JOIN HR_Period pr ON (pr.HR_Period_id=p.HR_Period_ID)"
							+" WHERE HR_Movement.HR_Process_ID = p.HR_Process_ID" 
							+" AND p.HR_Payroll_ID=?");

		params.add(payroll_id);
		
		whereClause.append(")");
		//
		StringBuffer sql = new StringBuffer("SELECT COALESCE(SUM(").append(fieldName).append("),0) FROM ").append(MHRMovement.Table_Name)
								.append(" WHERE ").append(whereClause);
		BigDecimal value = DB.getSQLValueBDEx(get_TrxName(), sql.toString(), params);
		return value.doubleValue();
		
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
		log.warning("not implemented yet -> getAttribute (Properties, String, Timestamp, Timestamp)");
		return 0;
	} // getAttribute

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
		log.warning("not implemented yet -> getAttribute (Properties, String, int, int, String, String)");
		return 0;
	} // getAttribute
	
	
		
	/**
	 * Helper Method : Get AttributeInvoice 
	 * @param conceptValue - Value to Concept
	 * @return	C_Invoice_ID, 0 if does't
	 */ 
	public int getAttributeInvoice (String conceptValue)
	{
		MHRConcept concept = MHRConcept.forValue(getCtx(), conceptValue);
		if (concept == null)
			return 0;
		
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		// check ValidFrom:
		whereClause.append(MHRAttribute.COLUMNNAME_ValidFrom + "<=?");
		params.add(dateFrom);
		//check client
		whereClause.append(" AND AD_Client_ID = ?");
		params.add(getAD_Client_ID());
		//check concept
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Concept c WHERE c.HR_Concept_ID=HR_Attribute.HR_Concept_ID" 
						   + " AND c.Value = ?)");
		params.add(conceptValue);
		//
		if (!MHRConcept.TYPE_Information.equals(concept.getType()))
		{
			whereClause.append(" AND " + MHRAttribute.COLUMNNAME_C_BPartner_ID + " = ?");
			params.add(partnerId);
		}
		
		MHRAttribute attribute = new Query(getCtx(), MHRAttribute.Table_Name, whereClause.toString(), get_TrxName())
		.setParameters(params)
		.setOrderBy(MHRAttribute.COLUMNNAME_ValidFrom + " DESC")
		.first();
		
		if(attribute!=null)
			return (Integer) attribute.get_Value("C_Invoice_ID");
		else
			return 0;
		
	} // getAttributeInvoice
		
	/**
	 * Helper Method : Get AttributeDocType
	 * @param conceptValue - Value to Concept
	 * @return	C_DocType_ID, 0 if does't
	 */ 
	public int getAttributeDocType (String conceptValue)
	{
		MHRConcept concept = MHRConcept.forValue(getCtx(), conceptValue);
		if (concept == null)
			return 0;
		
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		// check ValidFrom:
		whereClause.append(MHRAttribute.COLUMNNAME_ValidFrom + "<=?");
		params.add(dateFrom);
		//check client
		whereClause.append(" AND AD_Client_ID = ?");
		params.add(getAD_Client_ID());
		//check concept
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Concept c WHERE c.HR_Concept_ID=HR_Attribute.HR_Concept_ID" 
						   + " AND c.Value = ?)");
		params.add(conceptValue);
		//
		if (!MHRConcept.TYPE_Information.equals(concept.getType()))
		{
			whereClause.append(" AND " + MHRAttribute.COLUMNNAME_C_BPartner_ID + " = ?");
			params.add(partnerId);
		}
		
		MHRAttribute attribute = new Query(getCtx(), MHRAttribute.Table_Name, whereClause.toString(), get_TrxName())
		.setParameters(params)
		.setOrderBy(MHRAttribute.COLUMNNAME_ValidFrom + " DESC")
		.first();
		
		if(attribute!=null)
			return (Integer) attribute.get_Value("C_DocType_ID");
		else
			return 0;
		 
	} // getAttributeDocType

	/**
	 * Get attribute by employee
	 * @param conceptValue
	 * @param BPartnerId
	 * @return
	 */
	public BigDecimal getAttributeBPartner(String conceptValue, int BPartnerId) {
		MHRConcept concept = MHRConcept.forValue(getCtx(), conceptValue);
		if (concept == null)
			return BigDecimal.ZERO;

		ArrayList<Object> params = new ArrayList<>();
		StringBuffer whereClause = new StringBuffer();
		// check ValidFrom:
		whereClause.append(MHRAttribute.COLUMNNAME_ValidFrom).append("<=?");
		params.add(dateFrom);
		// check client
		whereClause.append(" AND AD_Client_ID = ?");
		params.add(getAD_Client_ID());
		if (payrollId > 0) {
			whereClause.append(" AND (HR_Payroll_ID=? OR HR_Payroll_ID IS NULL)");
			params.add(payrollId);
		}
		if (departmentId > 0) {
			whereClause.append(" AND (HR_Department_ID=? OR HR_Department_ID IS NULL)");
			params.add(departmentId);
		}
		if (jobId > 0) {
			whereClause.append(" AND (HR_Job_ID=? OR HR_Job_ID IS NULL)");
			params.add(jobId);
		}

		// check concept
		whereClause.append(" AND EXISTS (SELECT 1 FROM HR_Concept c WHERE c.HR_Concept_ID=HR_Attribute.HR_Concept_ID")
				   .append(" AND c.Value = ?)");
		params.add(concept);
		//
		if (!concept.getType().equals(MHRConcept.TYPE_Information)) {
			whereClause.append(" AND " + MHRAttribute.COLUMNNAME_C_BPartner_ID + " = ?");
			params.add(BPartnerId);
		}

		MHRAttribute attribute = new Query(getCtx(), MHRAttribute.Table_Name, whereClause.toString(),
				get_TrxName()).setParameters(params)
				.setOrderBy(MHRAttribute.COLUMNNAME_ValidFrom + " DESC").first();
		if (attribute == null)
			return BigDecimal.ZERO;

		// if column type is Quantity return quantity
		if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity))
			return attribute.getQty();

		// if column type is Amount return amount
		if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Amount))
			return attribute.getAmount();

		return BigDecimal.ZERO;
	} // getAttribute

	/**
	 * Helper Method : get days from specific period
	 * @param period
	 * @return no. of days
	 */
	public double getDays (int period)
	{
		/* TODO: This getter could have an error as it's not using the parameter, and it doesn't what is specified in help */
		log.warning("instead of using getDays in the formula it's recommended to use _DaysPeriod+1");
		return Env.getContextAsInt(getCtx(), "_DaysPeriod") + 1;
	} // getDays
	
	
	public static MHRProcess copyFrom (MHRProcess from, Timestamp dateAcct,
			int C_DocTypeTarget_ID, boolean counter, String trxName, boolean setOrder)
	{
		MHRProcess to = new MHRProcess (from.getCtx(), 0, trxName);		
		PO.copyValues (from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
		to.set_ValueNoCheck ("DocumentNo", null);
		//
		to.setDocStatus (DOCSTATUS_Drafted);		//	Draft
		to.setDocAction(DOCACTION_Complete);
		//
		to.setName(from.getDocumentNo());
		to.setC_DocType_ID(C_DocTypeTarget_ID);
		to.setC_DocTypeTarget_ID (C_DocTypeTarget_ID);
		to.setDateAcct (dateAcct);
		//
		to.setHR_Job_ID(from.getHR_Job_ID());
		to.setHR_Department_ID(from.getHR_Department_ID());
		to.setHR_Payroll_ID(from.getHR_Payroll_ID());
		to.setHR_Period_ID(from.getHR_Period_ID());
		to.setC_BPartner_ID(from.getC_BPartner_ID());
		to.setHR_Employee_ID(from.getHR_Employee_ID());
		to.setC_Charge_ID(from.getC_Charge_ID());
		//
		to.setPosted (false);
		to.setProcessed (false);
		to.setProcessing(false);
		to.saveEx(trxName);
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
		
		List<MHRMovement> fromLines = MHRMovement.getLinesForProcess(from);
		for (MHRMovement fromMovement: fromLines)
		{
			MHRMovement toMovement = new MHRMovement (getCtx(), 0, get_TrxName());
			PO.copyValues (fromMovement, toMovement, fromMovement.getAD_Client_ID(), fromMovement.getAD_Org_ID());
			toMovement.setIsManual(fromMovement.isManual());
			toMovement.setHR_Concept_Category_ID(fromMovement.getHR_Concept_Category_ID());
			toMovement.setHR_Process_ID(getHR_Process_ID());
			toMovement.setC_BPartner_ID(fromMovement.getC_BPartner_ID());
			toMovement.setHR_Concept_ID(fromMovement.getHR_Concept_ID());
			toMovement.setColumnType(fromMovement.getColumnType());
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
			toMovement.setPaymentRule(fromMovement.getPaymentRule());
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

		MHRConcept concept = MHRConcept.forValue(getCtx(), conceptValue);
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
			payrollPeriod = MHRPeriod.get(getCtx(),  getHR_Period_ID());
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
		scriptCtx.put("_Department", getHR_Department_ID());

		log.info("info data - " +
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

		scriptCtx.remove("_HR_Concept_ID");
		scriptCtx.put("_HR_Concept_ID", concept.getHR_Concept_ID());
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
}	//	MHRProcess
