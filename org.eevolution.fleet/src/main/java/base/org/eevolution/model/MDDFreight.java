/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software, you can redistribute it and/or modify it
 * under the terms version 2 of the GNU General Public License as published
 * or (at your option) any later version.
 * by the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along
 * with this program, if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 * For the text or an alternative of this public license, you may reach us
 * or via info@adempiere.net or http://www.adempiere.net/license.html
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.eevolution.model;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MPeriod;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Msg;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

/**
 * Domain Model for Freight Order
 * @author victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * Add support to document actions
 */
public class MDDFreight extends X_DD_Freight implements DocAction, DocOptions {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3012349387181755416L;

	/**
     * Constructor Freight Order
     * @param ctx
     * @param freightId
     * @param trxName
     */
    public MDDFreight(Properties ctx, int freightId, String trxName) {
        super(ctx, freightId, trxName);
    }

    /**
     * Constructor Freight Order
     * @param ctx
     * @param rs
     * @param trxName
     */
    public MDDFreight(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }


    /** Lines*/
    private List<MDDFreightLine> freightLines = null;
    /**	Process Message 			*/
    private String			processMsg = null;
    /**	Just Prepared Flag			*/
    private boolean 		justPrepared = false;

    /**
     * 	Get Lines
     *	@return Freightlines
     */
    public List<MDDFreightLine> getLines()
    {
        if (freightLines != null)
            return freightLines;

        final String whereClause = I_DD_FreightLine.COLUMNNAME_DD_Freight_ID+"=?";
        freightLines = new Query(getCtx(), I_DD_FreightLine.Table_Name, whereClause, get_TrxName())
                .setParameters(get_ID())
                .setOrderBy(I_DD_FreightLine.COLUMNNAME_Line)
                .list();
        return freightLines;
    }	//	getLines

    @Override
    protected boolean beforeDelete() {
        for (MDDFreightLine line : getLines()) {
            line.deleteEx(true);
        }
        return true;
    }

    @Override
    public boolean processIt(String action) throws Exception {
        processMsg = null;
        DocumentEngine engine = new DocumentEngine (this, getDocStatus());
        return engine.processIt (action, getDocAction());
    }

    @Override
    public boolean unlockIt() {
        log.info("unlockIt - " + toString());
        //setProcessing(false);
        return true;
    }

    @Override
    public boolean invalidateIt() {

        log.info("invalidateIt - " + toString());
        return true;
    }

    @Override
    public String prepareIt() {

        log.info(toString());
        processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
        if (processMsg != null)
            return DocAction.STATUS_Invalid;


        if(getLines().size() == 0)
        {
            throw new AdempiereException("@NoLines@");
        }

        //	Std Period open?
        MPeriod.testPeriodOpen(getCtx(), getDateDoc(), MDocType.DOCBASETYPE_DistributionOrder, getAD_Org_ID());

        processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
        if (processMsg != null)
            return DocAction.STATUS_Invalid;

        justPrepared = true;
        return DocAction.STATUS_InProgress;
    }

    @Override
    public boolean approveIt() {

        log.info("approveIt - " + toString());
        setIsApproved(true);
        return true;
    }

    @Override
    public boolean rejectIt() {

        log.info("rejectIt - " + toString());
        setIsApproved(false);
        return true;
    }

    @Override
    public String completeIt() {
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

        //	User Validation
        String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
        if (valid != null)
        {
            processMsg = valid;
            return DocAction.STATUS_Invalid;
        }

        // Set the definite document number after completed (if needed)
        setDefiniteDocumentNo();

        //
        setProcessed(true);
        setDocAction(ACTION_Close);
        return DocAction.STATUS_Completed;
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
            String value = DB.getDocumentNo(getC_DocType_ID(), get_TrxName(), true, this);
            if (value != null)
                setDocumentNo(value);
        }
    }

    @Override
	public int customizeValidActions(String docStatus, Object processing,
			String orderType, String isSOTrx, int AD_Table_ID,
			String[] docAction, String[] options, int index) {
		//	Valid Document Action
		if (AD_Table_ID == Table_ID) {
			if (docStatus.equals(DocumentEngine.STATUS_Drafted)
					|| docStatus.equals(DocumentEngine.STATUS_InProgress)
					|| docStatus.equals(DocumentEngine.STATUS_Invalid)) {
					options[index++] = DocumentEngine.ACTION_Prepare;
				}
				//	Complete                    ..  CO
				else if (docStatus.equals(DocumentEngine.STATUS_Completed)) {
					
					options[index++] = DocumentEngine.ACTION_Void;
					options[index++] = DocumentEngine.ACTION_ReActivate;
					options[index++] = DocumentEngine.ACTION_Close;
					
				} else if (docStatus.equals(DocumentEngine.STATUS_Closed)) {
					options[index++] = DocumentEngine.ACTION_None;
				}
			
		}
		
		return index;
	}

	/**
	 * 	Void Document.
	 * 	Same as Close.
	 * 	@return true if success 
	 */
	public boolean voidIt()
	{
		log.info("voidIt - " + toString());
		// Before Void
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (processMsg != null)
			return false;
		addDescription(Msg.getMsg(getCtx(), "Voided"));
		// After Void
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (processMsg != null)
			return false;

		setProcessed(true);
        setDocAction(DOCACTION_None);
		return true;
	}	//	voidIt
	
	/**
     *  Add to Description
     *  @param description text
     */
    public void addDescription (String description) {
        String desc = getDescription();
        if (desc == null)
            setDescription(description);
        else
            setDescription(desc + " | " + description);
    }   //  addDescription
	
	/**
	 * 	Close Document.
	 * 	Cancel not delivered Qunatities
	 * 	@return true if success 
	 */
	public boolean closeIt() {
		log.info("closeIt - " + toString());
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
	}	//	closeIt
	
	/**
	 * 	Reverse Correction
	 * 	@return true if success 
	 */
	public boolean reverseCorrectIt()
	{
		log.info("reverseCorrectIt - " + toString());
		// Before reverseCorrect
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (processMsg != null)
			return false;
		//	Void It
		voidIt();
		// After reverseCorrect
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (processMsg != null)
			return false;

		return false;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual - none
	 * 	@return true if success 
	 */
	public boolean reverseAccrualIt()
	{
		log.info("reverseAccrualIt - " + toString());
		// Before reverseAccrual
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (processMsg != null)
			return false;
		//	Void It
		voidIt();
		// After reverseAccrual
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (processMsg != null)
			return false;

		return false;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return true if success 
	 */
	public boolean reActivateIt()
	{
		log.info("reActivateIt - " + toString());
		// Before reActivate
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (processMsg != null)
			return false;
		// After reActivate
		processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (processMsg != null)
			return false;
		
		setDocAction(DOCACTION_Complete);
		setProcessed(false);
		return true;
	}	//	reActivateIt

    @Override
    public String getSummary() {

        StringBuffer sb = new StringBuffer();
        sb.append(getDocumentNo());
        //	 - User
        sb.append(" - ").append(getDocumentInfo());
        sb.append(": ")
                .append(" (#").append(getLines().size()).append(")");
        //	 - Description
        if (getDescription() != null && getDescription().length() > 0)
            sb.append(" - ").append(getDescription());
        return sb.toString();
    }

    @Override
    public String getDocumentInfo() {

        return Msg.getElement(getCtx(), COLUMNNAME_DD_Freight_ID) + " " + getDocumentNo();
    }

    @Override
    public File createPDF() {

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
    }

    /**
     * 	Create PDF file
     *	@param file output file
     *	@return file if success
     */
    public File createPDF (File file)
    {
        // ReportEngine re = ReportEngine.get (getCtx(), Freight , getDD_Freight_ID()); todo : Implement Freight Order Format
        //if (re == null)
        return null;
        //	return re.getPDF(file);
    }	//	createPDF

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

    @Override
    public String getProcessMsg() {
        return null;
    }

    @Override
    public int getDoc_User_ID() {
        return 0;
    }

    @Override
    public int getC_Currency_ID() {
        return 0;
    }

    @Override
    public BigDecimal getApprovalAmt() {
        return null;
    }
}
