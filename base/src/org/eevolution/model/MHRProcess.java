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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MPeriod;
import org.compiere.model.MRule;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.Scriptlet;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * HR Process Model
 *
 * @author e-Evolution, Mexico
 * @author Cristina Ghita, SC ARHIPAC SERVICE SRL
 * 				<li>BF [ 2031197 ] Skips saving
 */
public class MHRProcess extends X_HR_Process implements DocAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static int m_process   = 0;
	public static int m_bpartner  = 0;
	public static int m_concept   = 0;
	public static int m_period    = 0;
	public static int m_payroll   = 0;
	//public static int m_department= 0;
	public static int m_attribute = 0;
	public static String m_columnType   = "";
	public static String m_columnRef    = "";	// When is Info (I), then it's a parameter that doesn't have business partner
	public static Timestamp m_dateFrom   = new Timestamp (System.currentTimeMillis());
	public static Timestamp m_dateTo     = new Timestamp (System.currentTimeMillis());	
	public static String m_From          = "";
	public static String m_To            = "";
	public static Hashtable<Integer,MHRMovement> m_movement = new Hashtable<Integer, MHRMovement>();
	public static MHRPayrollConcept[] linesConcept;
	public static MBPartner[] linesEmployee;
	
	/**	Lines					*/
	private MHRMovement[]		m_lines = null;

	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MHREmployee.class);

    /**************************************************************************
     *  Default Constructor
     *  @param ctx context
     *  @param  HR_Process_ID    To load, (0 create new order)
     */
    public MHRProcess(Properties ctx, int HR_Process_ID, String trxName) {
        super(ctx, HR_Process_ID,trxName);
        if (HR_Process_ID == 0) {
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
    public MHRProcess(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs,trxName);
    }	//	MHRProcess
    
    /**
     * 	Set Processed.
     *	@param processed processed
     */
    public void setProcessed(boolean processed) {
        super.setProcessed(processed);
        if (get_ID() == 0)
            return;
        String set = "SET Processed='"
        	+ (processed ? "Y" : "N")
        	+ "' WHERE HR_Process_ID=" + getHR_Process_ID();
        int noLine = DB.executeUpdate("UPDATE HR_Process " + set , get_TrxName());
    }	//	setProcessed
    
    
    /**
     * 	before Save
     * 	Create lines From concept payment
     */    
    protected boolean beforeSave(boolean newRecord) {
        if (getAD_Client_ID() == 0) {
            m_processMsg = "AD_Client_ID = 0";
            return false;
        }
        if (getAD_Org_ID() == 0) {
            int context_AD_Org_ID = Env.getAD_Org_ID(getCtx());
            if (context_AD_Org_ID == 0) {
                m_processMsg = "AD_Org_ID = 0";
                return false;
            }
            setAD_Org_ID(context_AD_Org_ID);
            log.warning("beforeSave - Changed Org to Context=" + context_AD_Org_ID);
        }
        setC_DocType_ID(getC_DocTypeTarget_ID());
        
    	return true;
    }       
    
    
    /**************************************************************************
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
    public boolean unlockIt() {
        log.info("unlockIt - " + toString());
        setProcessing(false);
        return true;
    }	//	unlockIt
    
    
    /**
     * 	Invalidate Document
     * 	@return true if success
     */
    public boolean invalidateIt() {
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
        
        X_HR_Period period = new X_HR_Period(Env.getCtx(),getHR_Period_ID(),get_TrxName());
 		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		org.compiere.model.MDocType dt = MDocType.get(getCtx(), getC_DocTypeTarget_ID());

		//	Std Period open?
		MPeriod.testPeriodOpen(getCtx(), period.getDateAcct(), dt.getDocBaseType());
		
		//	New or in Progress/Invalid
		if (   DOCSTATUS_Drafted.equals(getDocStatus()) 
			|| DOCSTATUS_InProgress.equals(getDocStatus())
			|| DOCSTATUS_Invalid.equals(getDocStatus()) 
			|| getC_DocType_ID() == 0)
		{
			setC_DocType_ID(getC_DocTypeTarget_ID()); 
		}
        
        m_justPrepared = true;

        m_period = getHR_Period_ID();
        m_payroll = getHR_Payroll_ID();
        
		Env.setContext(Env.getCtx(), "_Process", getHR_Process_ID());
		Env.setContext(Env.getCtx(), "_Period", getHR_Period_ID());
		Env.setContext(Env.getCtx(), "_Payroll", getHR_Payroll_ID());
		Env.setContext(Env.getCtx(), "_Department", getHR_Department_ID());
		
		log.info("info data - " + " Process: " +getHR_Process_ID()+ ", Period: " +getHR_Period_ID()+ ", Payroll: " +getHR_Payroll_ID()+ ", Department: " +getHR_Department_ID());
		// X_HR_Period pPayroll;
		// info Period
		if (getHR_Period_ID() > 0)
		{
			// pPayroll   = new X_HR_Period(Env.getCtx(),getHR_Period_ID(),get_TrxName());
			m_dateFrom = period.getStartDate();
			m_dateTo   = period.getEndDate();
			m_From     = DB.TO_DATE(period.getStartDate());
			m_To       = DB.TO_DATE(period.getEndDate());
			Env.setContext(Env.getCtx(), "_From", period.getStartDate());
			Env.setContext(Env.getCtx(), "_To", period.getEndDate());				
		}
	
		// RE-Process, delete movement exept concept type Incidence 
		int delete = DB.executeUpdate("DELETE FROM HR_Movement m WHERE HR_Process_ID = " +getHR_Process_ID()+ " AND IsRegistered != 'Y' ", get_TrxName());
		log.info("info HR_Movement deleted = "+ delete + " records");
		// Concepts
		linesConcept = MHRPayrollConcept.getPayrollConcepts(this);
		// Employees 
		linesEmployee = new MHREmployee(Env.getCtx(),0,get_TrxName()).getEmployees(this);
		//
		int count = 1;
		for(MBPartner bp: linesEmployee)	//=============================================================== Employee
		{
			log.info("Employee " + count + "  ---------------------- " + bp.getName());
			count++;
			m_bpartner             = bp.getC_BPartner_ID();
			int employee_ID=0;
			employee_ID        = DB.getSQLValue(get_TrxName(), "SELECT HR_Employee_ID FROM HR_Employee WHERE  HR_Employee.IsActive='Y' AND 	C_BPartner_ID="+m_bpartner);
			X_HR_Employee employee = new X_HR_Employee(Env.getCtx(),employee_ID,get_TrxName());
			//Env.setContext(Env.getCtx(), "_DateBirth", employee.getDateBirth());
			Env.setContext(Env.getCtx(), "_DateStart", employee.getStartDate());
			Env.setContext(Env.getCtx(), "_DateEnd", employee.getEndDate() == null ? "2999-12-31 00:00:00 0.0" : employee.getEndDate().toString());
			Env.setContext(Env.getCtx(), "_Days", org.compiere.util.TimeUtil.getDaysBetween(period.getStartDate(),period.getEndDate())+1);
			Env.setContext(Env.getCtx(), "_C_BPartner_ID", bp.getC_BPartner_ID());
			
			m_movement.clear();
			movement();
			//
			for(MHRPayrollConcept pc : linesConcept) // ==================================================== Concept
			{	
				m_concept          = pc.getHR_Concept_ID();
				MHRConcept concept = new MHRConcept(getCtx(), m_concept, get_TrxName());
				m_columnType       = concept.getColumnType();
				m_columnRef        = concept.getType();
				// Check the concept is within a valid range for the attribute
				String attSql = "SELECT att.HR_Attribute_ID FROM HR_Attribute att" 
				 		 + " WHERE " + m_From + ">= att.ValidFrom AND (" + m_To + " <= att.ValidTo OR att.ValidTo IS NULL)"
				 		 + " AND att.HR_Concept_ID =" + m_concept
				 		 + " AND EXISTS (SELECT * FROM HR_Concept conc WHERE conc.HR_Concept_ID = att.HR_Concept_ID )";
				if (concept.isEmployee())
					attSql += " AND att.C_BPartner_ID = " + employee.getC_BPartner_ID();

				m_attribute = DB.getSQLValue(get_TrxName(),attSql);
				if (m_attribute < 0 || concept.isRegistered())
				{
					log.info("Skip concept "+concept+" - attribute not found");
					continue;
				}
				MHRAttribute att =  new MHRAttribute(Env.getCtx(),m_attribute,get_TrxName());
			
				if(!concept.getType().equals(MHRConcept.TYPE_RuleEngine)) // Not Rule Engine - Only put HashTable
				{
					log.info("Concept - " + concept.getName());
					MHRMovement movement = new MHRMovement (Env.getCtx(), 0, get_TrxName());
					movement.setQty(att.getQty()); 
					movement.setAmount(att.getAmount());
					movement.setTextMsg(att.getTextMsg());						
					movement.setServiceDate(att.getServiceDate());
					movement.setC_BPartner_ID(m_bpartner);
					movement.setHR_Concept_ID(m_concept);
					movement.setHR_Concept_Category_ID(concept.getHR_Concept_Category_ID());
					movement.setHR_Process_ID(getHR_Process_ID());
					movement.setHR_Department_ID(employee.getHR_Department_ID());
					movement.setHR_Job_ID(employee.getHR_Job_ID());
					movement.setColumnType(m_columnType);
					movement.setAD_Rule_ID(att.getAD_Rule_ID());
					movement.setValidFrom(m_dateFrom);
					movement.setValidTo(m_dateTo);
					movement.setIsPrinted(att.isPrinted());
					movement.setIsRegistered(concept.isRegistered());
					movement.setC_Activity_ID(employee.getC_Activity_ID());
					movement.setProcessed(true);
					m_movement.put(new Integer(m_concept), movement);
				}
				else												// Rule Engine, Process and put HashTable
				{
					MRule rulee = MRule.get(getCtx(), att.getAD_Rule_ID());
					Object result = null;
					try
					{
						String text = "";
						if(rulee.getScript() != null)
							text = rulee.getScript().trim();//.replace("get", "org.eevolution.model.MHRProcess.get");
						String execute = 
							 " import org.eevolution.model.*;"
							+" import org.compiere.util.DB;"
							+" import java.math.*;"
							+" import java.sql.*;"
							+" double result = 0;"
							+ text;
						Scriptlet m_script = new Scriptlet (Scriptlet.VARIABLE, ";", Env.getCtx(), 0);	
						m_script.setScript(execute);
						Exception ex = m_script.execute();
						if (ex != null)
							throw ex;
						result = m_script.getResult(false);
					} catch	(Exception e) {
						m_processMsg = e.toString() + ", @AD_Rule_ID@="+rulee.getValue();
					    return DocAction.STATUS_Invalid;
					}
					if(result == null)
					{
						log.warning("Variable (result) is null");
						continue;
					}
					MHRMovement movement = new MHRMovement (Env.getCtx(), 0, get_TrxName());
					// According to the concept type, it's saved in the column specified for the purpose
					if (concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity))
					{
						BigDecimal qty = java.math.BigDecimal.valueOf(Double.parseDouble(result.toString()));
						movement.setQty(qty);
						movement.setAmount(Env.ZERO);
					} 
					else if(concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Amount))
					{
						BigDecimal amount = java.math.BigDecimal.valueOf(Double.parseDouble(result.toString())).setScale(2, BigDecimal.ROUND_HALF_UP);	
						movement.setAmount(amount);
						movement.setQty(Env.ZERO);
					} 
					else if(concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Text))
						movement.setTextMsg( rulee.getScript().toString().trim());
					else if(concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Date))
						movement.setServiceDate( Timestamp.valueOf( rulee.getScript().toString().trim().substring(0, 10)+ " 00:00:00.0"));	
					movement.setC_BPartner_ID(m_bpartner);
					movement.setHR_Concept_ID(m_concept);
					movement.setHR_Concept_Category_ID(concept.getHR_Concept_Category_ID());
					movement.setHR_Process_ID(getHR_Process_ID());
					movement.setHR_Department_ID(employee.getHR_Department_ID());
					movement.setHR_Job_ID(employee.getHR_Job_ID());
					movement.setColumnType(m_columnType);
					movement.setAD_Rule_ID(att.getAD_Rule_ID());
					movement.setValidFrom(m_dateFrom);
					movement.setValidTo(m_dateTo);
					movement.setIsPrinted(att.isPrinted());
					movement.setIsRegistered(concept.isRegistered());
					movement.setC_Activity_ID(employee.getC_Activity_ID());
					movement.setProcessed(true);
					m_movement.put(new Integer(m_concept), movement);
				} // Attribute Rule Engine
			} // concept

			
			for (MHRPayrollConcept pc: linesConcept)
			{
				MHRMovement m =  m_movement.get(new Integer(pc.getHR_Concept_ID()));
				MHRConcept  c = new MHRConcept(Env.getCtx(),pc.getHR_Concept_ID(),get_TrxName());
				if(m == null)
					continue;
				if( !c.isRegistered() &&
						(m.getQty().signum() > 0
						|| m.getAmount().signum() > 0
						|| !Util.isEmpty(m.getTextMsg()))
					)
				{	
					m.saveEx();
				}
				else
				{
					log.fine("Skip saving "+m);
				}
			}			
		} // employee
		
        return DocAction.STATUS_InProgress;
    }	//	prepareIt
    
    

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
    
    
    /**************************************************************************
     * 	Complete Document
     * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
     */
    public String completeIt() {
 		if (DOCACTION_Prepare.equals(getDocAction()))
		{
			setProcessed(false);
			return DocAction.STATUS_InProgress;
		}
 		//
	   	X_HR_Period period = new X_HR_Period(getCtx(),getHR_Period_ID(),get_TrxName());
    	period.setProcessed(true);
    	period.save();
		setProcessed(true);	
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed; 
    }	//	completeIt
    
    
    /**
     * 	Post Document - nothing
     * 	@return true if success
     */
    public boolean postIt() {
        log.info("postIt - " + toString());
        return false;
    }	//	postIt
    
    
    /**
     * 	Void Document.
     * 	Set Qtys to 0 - Sales: reverse all documents
     * 	@return true if success
     */
    public boolean voidIt() {
        log.info("voidIt - " + toString());
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
    	if (isProcessed())
    	{
    		log.info(toString());
    		setProcessed(true);
    		setDocAction(DOCACTION_None);
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
        return voidIt();
    }	//	reverseCorrectionIt

    
    /**
     * 	Reverse Accrual - none
     * 	@return true if success
     */
    public boolean reverseAccrualIt() {
        log.info("reverseAccrualIt - " + toString());
        return false;
    }	//	reverseAccrualIt
    
    
    /**
     * 	Re-activate.
     * 	@return true if success
     */
    public boolean reActivateIt() {
        log.info("reActivateIt - " + toString());
        
        org.compiere.model.MDocType dt = org.compiere.model.MDocType.get(getCtx(), getC_DocType_ID());
        String DocSubTypeSO = dt.getDocSubTypeSO();
        
        //	Reverse Direct Documents
        if (   MDocType.DOCSUBTYPESO_OnCreditOrder.equals(DocSubTypeSO)		//	(W)illCall(I)nvoice
        	|| MDocType.DOCSUBTYPESO_WarehouseOrder.equals(DocSubTypeSO)	//	(W)illCall(P)ickup
        	|| MDocType.DOCSUBTYPESO_POSOrder.equals(DocSubTypeSO))			//	(W)alkIn(R)eceipt
        {
             return false;
        }
        else {
            log.fine("reActivateIt - Existing documents not modified - SubType=" + DocSubTypeSO);
        }
       
 		//	Delete 
		String sql = "DELETE FROM HR_Movement WHERE HR_Process_ID =" + this.getHR_Process_ID() + " AND IsRegistered = 'N'" ;
		int no = DB.executeUpdate(sql, get_TrxName());
		log.fine("HR_Process deleted #" + no);
        
        setDocAction(DOCACTION_Complete);
        setProcessed(false);
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
    public java.math.BigDecimal getApprovalAmt() {
        return new BigDecimal(0);
    }	//	getApprovalAmt
    
    public int getC_Currency_ID() {
        return 0;
    }
    
    public String getProcessMsg() {
        return m_processMsg;
    }
    
    public String getSummary(){
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
	public MHRMovement[] getLines (boolean requery, int HR_Process_ID)
	{
		//if (m_lines != null && !requery)
		//	return m_lines;
		
		ArrayList<MHRMovement> list = new ArrayList<MHRMovement>();
		
		String sql = "SELECT * FROM HR_Movement m" +
		  " INNER JOIN HR_Concept_Acct ca ON (ca.HR_Concept_ID=m.HR_Concept_ID AND ca.IsActive = 'Y')"
		 +" INNER JOIN HR_Concept      c  ON (c.HR_Concept_ID=m.HR_Concept_ID AND c.IsActive = 'Y')"
		 +" INNER JOIN C_BPartner      bp ON (bp.C_BPartner_ID = m.C_BPartner_ID)"
		 +" INNER JOIN C_BP_Group      pg ON (pg.C_BP_Group_ID = bp.C_BP_Group_ID)"
		 +" WHERE m.HR_Process_ID=? AND (m.Qty <> 0 OR m.Amount <> 0) AND c.AccountSign != 'N' AND ca.IsBalancing != 'Y'"
		 +" ORDER BY pg.C_BP_Group_ID";
		 
		/*String sql = "SELECT * "+ //m.HR_Concept_id,bp.c_bp_group_id,MAX(c.Name),SUM(m.Amount),MAX(c.AccountSign),MAX(CA.IsBalancing)" + // 1,2,3,4,5,6
	  	" FROM HR_Movement m" +
			" INNER JOIN HR_Concept_Acct ca ON (ca.HR_Concept_ID=m.HR_Concept_ID AND ca.IsActive = 'Y')" +
			" INNER JOIN HR_Concept      c  ON (c.HR_Concept_ID=m.HR_Concept_ID AND c.IsActive = 'Y')" +
			" INNER JOIN C_BPartner      bp ON (bp.C_BPartner_ID = m.C_BPartner_ID)" +
			" WHERE m.HR_Process_ID=? AND (m.Qty <> 0 OR m.Amount <> 0) AND c.AccountSign != 'N' AND ca.IsBalancing != 'Y'"+
			" GROUP BY m.hr_concept_id,bp.c_BP_group_ID ";*/
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, HR_Process_ID);
			ResultSet rs = pstmt.executeQuery ();
			
			while (rs.next ())
			{
				list.add (new MHRMovement (getCtx(), rs, get_TrxName()));
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		
		m_lines = new MHRMovement[list.size ()];
		list.toArray (m_lines);
		return m_lines;
	}	//	getLines
	
	
	/**
	 * 	Get Lines
	 *	@param requery requery
	 *	@return lines
	 */
	public BigDecimal getAmount (int HR_Process_ID)
	{
		String sql = "SELECT SUM(m.Amount) FROM HR_Movement m" +
		 " INNER JOIN HR_Concept_Acct ca ON (ca.HR_Concept_ID=m.HR_Concept_ID AND ca.IsActive = 'Y')"+
		 " WHERE m.HR_Process_ID=? AND (m.Qty <> 0 OR m.Amount <> 0)";
		return DB.getSQLValueBD(get_TrxName(), sql, HR_Process_ID);
	}	//	getTotalAcct
	
	
	public static double getConcept (String pconcept)
	{
		String sqlConcept = "SELECT HR_Concept_ID FROM HR_Concept WHERE TRIM(VALUE) = ?";
		int HR_Concept_ID = DB.getSQLValue("HR_Concept", sqlConcept, pconcept.trim());
		if (HR_Concept_ID < 0)
			return 0;
		MHRMovement m = m_movement.get(new Integer(HR_Concept_ID));
		if (m == null)
			return 0;
		String type = m.getColumnType();
		Double value = type.equals(MHRMovement.COLUMNTYPE_Amount) ? m.getAmount().doubleValue() : m.getQty().doubleValue();
		return value;
	}
	
	public static void setConcept (String pconcept,long value)
	{
		try
		{
			String sqlConcept = "SELECT HR_Concept_ID FROM HR_Concept WHERE TRIM(VALUE) = ?";
			int HR_Concept_ID = DB.getSQLValue("HR_Concept", sqlConcept, pconcept.trim());
			if (HR_Concept_ID < 0)
				return;
			MHRConcept c = new MHRConcept(Env.getCtx(),HR_Concept_ID,null); 
			MHRMovement m = new MHRMovement(Env.getCtx(),0,null);
			m.setColumnType(c.getColumnType());
			if (c.getColumnType().equals(MHRConcept.COLUMNTYPE_Amount))
				m.setAmount(BigDecimal.valueOf(value));
			else if (c.getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity))
				m.setQty(BigDecimal.valueOf(value));
			else
				return;
			m.setHR_Process_ID(m_process);
			m.setHR_Concept_ID(HR_Concept_ID);
			m.setC_BPartner_ID(m_bpartner);
			m.setDescription("Added From Rule");
			m.setValidFrom(m_dateTo);
			m.setValidTo(m_dateTo);
			m.save();
			if (m == null || m.getHR_Concept_ID() == 0)
				s_log.warning("setConcept: Not Saved");
		} catch(Exception e)
		{
			s_log.warning(e.getMessage());
		}
	}
	
	public static double getConceptGroup (String pconcept)
	{
		String sqlConcept = "SELECT MAX(HR_Concept_Category_ID) FROM HR_Concept_Category WHERE TRIM(VALUE) = ?";
		int HR_Concept_Category_ID = DB.getSQLValue(null, sqlConcept, pconcept.trim());
		if (HR_Concept_Category_ID < 0)
			return 0;
		Double value = Env.ZERO.doubleValue();
		for(MHRPayrollConcept pc : linesConcept)
		{
			MHRConcept con = new MHRConcept(Env.getCtx(),pc.getHR_Concept_ID(),null);
			if(con.getHR_Concept_Category_ID() == HR_Concept_Category_ID)
			{
				if(m_movement.get(new Integer(pc.getHR_Concept_ID())).getColumnType().equals(MHRConcept.COLUMNTYPE_Amount))
					value += m_movement.get(new Integer(pc.getHR_Concept_ID())).getAmount().doubleValue();
				else if (m_movement.get(new Integer(pc.getHR_Concept_ID())).getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity))
					value += m_movement.get(new Integer(pc.getHR_Concept_ID())).getAmount().doubleValue();
			}
		}
		return value;
	}
	
	
	/**
	 * 	Get Concept [get concept to search key ]
	 * Parameter:		pList 		- Value List
	 * 						amount	- Amount to search
	 * 						column	- Number of column to return (1.......8)
	 * Return:			The amount corresponding to the designated column 'column'
	 */
	public static double getList (String pList, double amount, String column) // tax, getconceptgroup("ing"),"2")
	{
		double Value = 0;
		if (m_columnType.equals(MHRConcept.COLUMNTYPE_Amount))
		{
			column = column.toString().length() == 1 ? "Col_"+column : "Amount"+column;
			String sqlList = "SELECT (SELECT " +column+ " FROM HR_ListLine ll WHERE ll.HR_ListVersion_ID=lv.HR_ListVersion_ID AND "
				  + " ( " +amount+ " BETWEEN ll.MinValue AND ll.MaxValue) ) "
				  + " FROM HR_List l "
				  + " INNER JOIN HR_ListVersion lv ON (lv.HR_List_ID=l.HR_List_ID)"
				  + " WHERE l.Value = '" +pList+ "' AND l.AD_Client_ID = ? AND (" +m_From+ " BETWEEN  lv.ValidFrom AND lv.ValidTo ) ";
			Value = DB.getSQLValueBD(null,sqlList,Env.getAD_Client_ID(Env.getCtx())).doubleValue();
		}
		
	    if (Value < 0)
	    	throw new IllegalStateException("getList Out of Range");
		return Value;
	} // getConcept
	
	
	/**
	 * 	Get Attribute [get Attribute to search key concept ]
	 * Parameter	:		pConcept - Value to Concept
	 * Return:				Amount of concept, applying to employee
	 */ 
	public static double getAttribute (String pConcept)
	{
		BigDecimal Value = Env.ZERO;
		int HR_Concept_ID = DB.getSQLValue(null, "SELECT HR_Concept_ID FROM HR_Concept WHERE TRIM(VALUE) = ?", pConcept.trim());
		if (HR_Concept_ID < 0)
			return 0;
		MHRConcept concept = new MHRConcept(Env.getCtx(), HR_Concept_ID, null);
		String field = concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity) ? "SELECT MAX(att.QTY) " : "SELECT MAX(att.AMOUNT) ";
		String sqlQ = " FROM HR_Attribute att"
			+" INNER JOIN HR_Concept c ON (c.HR_Concept_ID=att.HR_Concept_ID)"
			+" WHERE c.Value = '" +pConcept+ "' AND att.AD_Client_ID = ? "
			+" AND " +m_From+ " >= att.ValidFrom "; //AND " +To+ " <= att.ValidFrom "; Put final date always in attributes
		if (!concept.getType().equals(MHRConcept.TYPE_Information))
			sqlQ += " AND att.C_BPartner_ID = "+m_bpartner;
		Value = DB.getSQLValueBD(null,field+sqlQ,Env.getAD_Client_ID(Env.getCtx()));	
		if(Value == null)
			return 0;		
		return Value.doubleValue();
	} // getAttribute
	
	
	/**
	 * 	Get Attribute [get Attribute to search key concept ]
	 * 
	 */ 
	/*public static Timestamp getAttributeDate (String pConcept)//(int HR_Process_ID,int HR_Employee_ID,String vConcept)
	{
		Timestamp valueDate = new Timestamp (System.currentTimeMillis());
		//if(m_columnType.equals(MHRConcept.COLUMNTYPE_Date))
		//{
			String valueString = "";
			//if(m_columnType.equals(MHRConcept.COLUMNTYPE_Date)){
				String sqlA = "SELECT ServiceDate FROM HR_Attributes att"
					+" INNER JOIN HR_Concept c ON (c.HR_Concept_ID=att.HR_Concept_ID)"
					+" WHERE c.Value = '" +pConcept+ "' AND att.AD_Client_ID = "+Env.getAD_Client_ID(Env.getCtx())
					+" AND att.C_BPartner_ID = ?";
					//+" AND " +From+ ">= att.ValidFrom AND " +To+ " >= att.ValidFrom "; // I dropped it, but it must be added (?)				
				valueString = DB.getSQLValueString(null,sqlA,m_bpartner);
				valueDate = Timestamp.valueOf( valueString.substring(0, 10)+ " 00:00:00.0" );
		//}
		return valueDate;
	}
	*/

	public static Timestamp getAttributeDate (String pConcept)//(int HR_Process_ID,int HR_Employee_ID,String vConcept)
	{
		Timestamp valueDate = new Timestamp (System.currentTimeMillis());
		int HR_Concept_ID = DB.getSQLValue(null, "SELECT HR_Concept_ID FROM HR_Concept WHERE TRIM(VALUE) = ?", pConcept.trim());
		if (HR_Concept_ID < 0)
			return null;
		MHRConcept concept = new MHRConcept(Env.getCtx(),HR_Concept_ID,null);
		//if(m_columnType.equals(MHRConcept.COLUMNTYPE_Date))
		//{
			String valueString = "";
			//if(m_columnType.equals(MHRConcept.COLUMNTYPE_Date)){
				String sqlA = "SELECT ServiceDate FROM HR_Attribute att"
					+" INNER JOIN HR_Concept c ON (c.HR_Concept_ID=att.HR_Concept_ID)"
					+" WHERE c.Value = '" +pConcept+ "' AND att.AD_Client_ID = ?";
				if (!concept.getType().equals(MHRConcept.TYPE_Information))
					sqlA=sqlA+" AND att.C_BPartner_ID = " + m_bpartner;
					//+" AND " +From+ ">= att.ValidFrom AND " +To+ " >= att.ValidFrom "; // I dropped it, but it must be added (?)		
				valueString = DB.getSQLValueString(null,sqlA,Env.getAD_Client_ID(Env.getCtx()));
				valueDate = Timestamp.valueOf( valueString.substring(0, 10)+ " 00:00:00.0" );
		//}
		return valueDate;
	}
	
	/**
	 * 	Get Days, Date in Format Timestamp
	 */ 
	public static int getDays (Timestamp date1, Timestamp date2)//(int HR_Process_ID,int HR_Employee_ID,String vConcept)
	{		
		return org.compiere.util.TimeUtil.getDaysBetween(date1,date2)+1;
	}
	
	
	/**
	 * 	Get Days, Date in Format String
	 */ 
	public static int getDays (String date1, String date2)//(int HR_Process_ID,int HR_Employee_ID,String vConcept)
	{		
		Timestamp dat1 = Timestamp.valueOf(date1);
		Timestamp dat2 = Timestamp.valueOf(date2);
		return org.compiere.util.TimeUtil.getDaysBetween(dat1,dat2)+1;
	}
	
	/**
	 * 	Get Months, Date in Format Timestamp
	 */ 
	public static int getMonths(Timestamp start,Timestamp end)
	{
		boolean negative = false;
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
	}
	
	
	/* Concept for a range from-to in periods
	 *  -- periods with values of 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 *  Value of HR_Movement
	 *  pFrom and pTo the search is done by the period value, it helps to search from previous years
	 */
	public static double getConcept (String pConcept, int periodFrom,int periodTo)
	{
		BigDecimal Value = Env.ZERO;
		String sqlConcept = "SELECT HR_Concept_ID FROM HR_Concept WHERE TRIM(VALUE) = ?";
		int HR_Concept_ID = DB.getSQLValue(null, sqlConcept, pConcept.trim());
		if (HR_Concept_ID < 0)
			return 0;
		X_HR_Period p = new X_HR_Period(Env.getCtx(), m_period, null);
		MHRConcept concept = new MHRConcept(Env.getCtx(),HR_Concept_ID,null);
		String field = concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity) ? "SUM(QTY)" : "SUM(AMOUNT)";
		String sql = "SELECT " +field+ " FROM HR_Movement m"
				+" INNER JOIN hr_process p ON p.hr_process_id=m.hr_process_id"
				+" INNER JOIN hr_period  pr ON pr.hr_period_id=p.hr_period_id"
				+" WHERE m.C_BPartner_ID=" +m_bpartner + " AND p.HR_Payroll_ID=?"
				+" AND m.HR_Concept_ID = " +HR_Concept_ID+ " AND m.AD_Client_ID =" + Env.getAD_Client_ID(Env.getCtx());
		if (periodFrom < 0)
			sql += " AND pr.PeriodNo >= " +p.getPeriodNo() +periodFrom;
		if (periodTo > 0)
			sql += " AND pr.PeriodNo <= " +p.getPeriodNo() +periodTo;
		//
		int record = DB.getSQLValue(null,sql,m_payroll);
		if(record > 0)
			Value = DB.getSQLValueBD(null,sql,m_payroll);
		return Value.doubleValue();
	} // getConcept
	
	/* Concept by range from-to in periods from a different payroll
	 *  -- periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 *  Value of HR_Movement
	 *  pFrom and pTo the search is done by the period value, it helps to search from previous years
	 *  pPayroll is the value of the payroll.
	 */
	public static double getConcept (String pConcept, String pPayroll,int periodFrom,int periodTo)
	{
		BigDecimal Value = Env.ZERO;
		String sqlConcept = "SELECT HR_Concept_ID FROM HR_Concept WHERE TRIM(VALUE) = ?";
		int HR_Concept_ID = DB.getSQLValue(null, sqlConcept, pConcept.trim());
		if (HR_Concept_ID < 0)
			return 0;
		X_HR_Period p = new X_HR_Period(Env.getCtx(),m_period,null);
		MHRConcept concept = new MHRConcept(Env.getCtx(),HR_Concept_ID,null);
		String field = concept.getColumnType().equals(MHRConcept.COLUMNTYPE_Quantity) ? "SUM(QTY)" : "SUM(AMOUNT)";
		String sql = "SELECT " +field+ " FROM HR_Movement m"
				+" INNER JOIN hr_process p ON p.hr_process_id=m.hr_process_id"
				+" INNER JOIN hr_period  pr ON pr.hr_period_id=p.hr_period_id"
				+" WHERE m.C_BPartner_ID=" +m_bpartner + " AND p.HR_Payroll_ID=?"
				+" AND m.HR_Concept_ID = " +HR_Concept_ID+ " AND m.AD_Client_ID =" + Env.getAD_Client_ID(Env.getCtx());
		if (periodFrom < 0)
			sql += " AND pr.PeriodNo >= " +p.getPeriodNo() +periodFrom;
		if (periodTo > 0)
			sql += " AND pr.PeriodNo <= " +p.getPeriodNo() +periodTo;
		//
		int record = DB.getSQLValue(null,sql,DB.getSQLValue(null,"SELECT HR_PAYROLL_ID FROM HR_PAYROLL WHERE VALUE=?", pPayroll));
		if (record > 0)
			Value = DB.getSQLValueBD(null,sql,DB.getSQLValue(null,"SELECT HR_PAYROLL_ID FROM HR_PAYROLL WHERE VALUE=?", pPayroll));
		return Value.doubleValue();
	} // getConcept
	
	/* Attribute that had from some date to another to date,
	 *   if it finds just one period it's seen for the attribute of such period 
	 *   if there are two or more attributes 
	   based on the days */
	public double getAttribute (Properties ctx, String vAttribute, Timestamp dateFrom, Timestamp m_dateTo)
	{
		// TODO ???
		return 0;
	}
	
	/* Attribute that had from some period to another to period,
	 *  -- periods with values 0 -1 1, etc. actual previous one period, next period
	 *  0 corresponds to actual period
	 *  Value of HR_Attribute
	 *   if it finds just one period it's seen for the attribute of such period 
	 *   if there are two or more attributes 
     *  pFrom and pTo the search is done by the period value, it helps to search from previous years
	   based on the days	   */
	public double getAttribute (Properties ctx, String vAttribute, int periodFrom,int periodTo,
			String pFrom,String pTo)
	{
		// TODO ???
		return 0;
	}
		
	public static double getDays (int period)
	{
		return Env.getContextAsInt(Env.getCtx(), "_DaysPeriod") + 1;
	}
	
	public void movement ()
	{
		String sql = "SELECT HR_Movement_ID FROM HR_Movement WHERE HR_Process_ID = " + getHR_Process_ID()
							+ " AND C_BPartner_ID = " + m_bpartner;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MHRMovement mvm = new MHRMovement(Env.getCtx(), rs.getInt(1), get_TrxName());
				if(m_movement.containsKey(new Integer(mvm.getHR_Concept_ID())))
				{
					MHRMovement lastM = m_movement.get(new Integer(mvm.getHR_Concept_ID()));
					String columntype = lastM.getColumnType();
					if (columntype.equals(MHRConcept.COLUMNTYPE_Amount))
						mvm.setAmount(mvm.getAmount().add(lastM.getAmount()));
					else if (columntype.equals(MHRConcept.COLUMNTYPE_Quantity))
						mvm.setQty(mvm.getQty().add(lastM.getQty()));
				}
				
				m_movement.put(new Integer(mvm.getHR_Concept_ID()), mvm);
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log (Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		return;
	}
	
}	//	MHRProcess
