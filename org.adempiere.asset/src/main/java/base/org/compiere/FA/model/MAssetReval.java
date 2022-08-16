/**
 * 
 */
package org.compiere.FA.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MPeriod;
import org.compiere.model.ModelValidationEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;


/**
 * @author Anca Bradau www.arhipac.ro
 *
 */
public class MAssetReval extends X_A_Asset_Reval
implements DocAction
{
	private static final long serialVersionUID = 1L;
	
	private boolean		m_justPrepared = false;

	public MAssetReval(Properties ctx, int X_A_Asset_Reval_ID, String trxName)
	{
		super(ctx, X_A_Asset_Reval_ID, trxName);
		if (X_A_Asset_Reval_ID == 0)
		{
		    setDocStatus(DOCSTATUS_Drafted);
			setDocAction(DOCACTION_Complete);
			setProcessed(false);
		}
	}
	public MAssetReval(Properties ctx, ResultSet rs, String trxName)
	{
       super (ctx, rs, trxName);
    }
	
	public boolean approveIt() 
	{
			return false;
	}
	
	public boolean closeIt() {
		setDocAction(DOCACTION_None);
		return true;
	}
	
	public String prepareIt()
	{
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
		{
			return DocAction.STATUS_Invalid;
		}
		// test if period is open
		MPeriod.testPeriodOpen(getCtx(), getDateAcct(), MDocType.DOCBASETYPE_GLJournal, getAD_Org_ID());
		
		// test if asset is already Depreciated
		MDepreciationWorkfile assetwk = MDepreciationWorkfile.get(getCtx(), getA_Asset_ID(), getPostingType());
		
		if (!assetwk.isDepreciated(getDateAcct()))
		{	
			throw new AdempiereException("Asset is not depreciated at this moment");
			
		}
		
		// test if Asset Cost and Accumulated Depreciation are changed 
		if (assetwk.getA_Asset_Cost().equals(getA_Asset_Cost_Change()) 
				&& assetwk.getA_Accumulated_Depr().equals(getA_Change_Acumulated_Depr()))
		{
			throw new AdempiereException("Nothing has changed");  
		}
		
		 //test if Asset Cost is changed
		if (assetwk.getA_Asset_Cost().equals(getA_Asset_Cost_Change()) 
				&& !assetwk.getA_Accumulated_Depr().equals(getA_Change_Acumulated_Depr())) 
		{
			throw new AdempiereException("It has changed the cost of Asset");  
		}
		
		// test if Accumulated depreciation is changed
		if (!assetwk.getA_Asset_Cost().equals(getA_Asset_Cost_Change()) 
				&& assetwk.getA_Accumulated_Depr().equals(getA_Change_Acumulated_Depr()))
		{
			throw new AdempiereException("It has changed the cumulative depreciation");  
		}
	
	    if (!isLastDepreciated(getDateAcct()))
	    {
	    	throw new AdempiereException("It can only review the last month processed");
	    }
	    
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

			m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}
    //return true if is last record depreciated
	public boolean isLastDepreciated(Timestamp date)
	{
		MDepreciationWorkfile assetwk = MDepreciationWorkfile.get(getCtx(), getA_Asset_ID(), getPostingType());
		Timestamp lastActionDate = assetwk.getLastActionDate();
		boolean isLastDepr = TimeUtil.getMonthLastDay(date).equals(lastActionDate);		
		return isLastDepr;
		
	}
	
	public String completeIt() {

		if (!m_justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}
		
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		MDepreciationWorkfile assetwk = MDepreciationWorkfile.get(getCtx(), getA_Asset_ID(), getPostingType(), get_TrxName());
		assetwk.setA_Asset_Cost(getA_Asset_Cost_Change());
		assetwk.setA_Accumulated_Depr(getA_Change_Acumulated_Depr());
		assetwk.saveEx();
		MAsset asset = MAsset.get(getCtx(), getA_Asset_ID(), get_TrxName());
		asset.setA_Asset_RevalDate(this.getDateDoc());
		asset.saveEx();
		//rebuild depreciation
		/* commented out by @win, deprecating existing design
		assetwk.buildDepreciation();
		*/
		
        //	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		
		// Set the definite document number after completed (if needed)
		//setDefiniteDocumentNo();

		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}
	
	public File createPDF() 
	{
		return null;
	}
	
	public BigDecimal getApprovalAmt() 
	{
		return Env.ZERO;
	}
	
	public int getC_Currency_ID() 
	{
		return 0;
	}
	
	public int getDoc_User_ID()
	{
		return getCreatedBy();
	}
	
	public String getDocumentInfo()
    {
		return getDocumentNo() + "/" + getDateAcct();
	}
	
	public String getProcessMsg() {
		return m_processMsg;
	}
	private String m_processMsg = null;
	
	
	public String getSummary() 
	{
		StringBuffer sb = new StringBuffer();
		sb.append("@DocumentNo@ #").append(getDocumentNo());
		return sb.toString();
	}
	
	public boolean invalidateIt()
	{
    	return false;
	}
	
	public boolean processIt(String action) throws Exception
	{
			m_processMsg = null;
			DocumentEngine engine = new DocumentEngine (this, getDocStatus());
			return engine.processIt (action, getDocAction());
	}
	
	
	public boolean reActivateIt() 
	{
		return false;
	}
	
	public boolean rejectIt() 
	{
		return false;
	}
	
	public boolean reverseAccrualIt() 
	{
		return false;
	}
	
	public boolean reverseCorrectIt() 
	{
		return false;
	}
	
	public boolean unlockIt() 
	{
		return false;
	}
	
	public boolean voidIt() 
	{		
		return false;
	}
	public String getDocumentNo() 
	{
		return null;
	}
	/**	Before save
	 *	@param	newRecord
	 *	@return true on success
	 */
	//protected boolean beforeSave (boolean newRecord)
	//{
		//return true;
	//}
 
}
