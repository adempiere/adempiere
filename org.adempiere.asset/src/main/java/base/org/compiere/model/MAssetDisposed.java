package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Env;
import org.compiere.FA.exceptions.AssetAlreadyDepreciatedException;
import org.compiere.FA.exceptions.AssetNotImplementedException;
import org.compiere.FA.exceptions.AssetNotSupportedException;
import org.compiere.FA.exceptions.AssetStatusChangedException;
import org.adempiere.util.POCacheLocal;


/**
 * Asset Disposal Model
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 
 * 
 */
public class MAssetDisposed extends X_A_Asset_Disposed
implements DocAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1763997880662445638L;

	public MAssetDisposed (Properties ctx, int A_Asset_Disposed_ID, String trxName)
	{
		super (ctx, A_Asset_Disposed_ID, trxName);
		if (A_Asset_Disposed_ID == 0)
		{
			setProcessed (false);
			setProcessing (false);
		}
		
	}
	
	//@win: autocreate asset disposal from ar invoice
	public static MAssetDisposed createAssetDisposed (MInvoiceLine invLine) {
		MAssetDisposed assetDisposed = new MAssetDisposed(invLine);
		assetDisposed.dump();
		return assetDisposed;
	}
	
	private MAssetDisposed (MInvoiceLine invLine) {
		this(invLine.getCtx(),0,invLine.get_TrxName());
		if (log.isLoggable(Level.FINEST)) log.finest("Entering: Project=" + invLine);
		setAD_Org_ID(invLine.getAD_Org_ID());
		setPostingType(POSTINGTYPE_Actual);
		setDateDoc(invLine.getC_Invoice().getDateInvoiced());
		setDateAcct(invLine.getC_Invoice().getDateInvoiced());
		setA_Disposed_Date(invLine.getC_Invoice().getDateInvoiced());
		setA_Disposed_Method(A_DISPOSED_METHOD_Trade);
		setA_Asset_ID(invLine.getA_Asset_ID());
		set_ValueNoCheck("C_Invoice_ID", invLine.getC_Invoice_ID());
		setM_InvoiceLine(invLine);
		saveEx(invLine.get_TrxName());
	}

	private final POCacheLocal<MInvoiceLine> m_cacheInvoiceLine = POCacheLocal.newInstance(this, MInvoiceLine.class);
	public MInvoiceLine getM_InvoiceLine(boolean requery)
	{
		return m_cacheInvoiceLine.get(requery);
	}
	private void setM_InvoiceLine(MInvoiceLine invLine)
	{
		set_Value("C_InvoiceLine_ID", invLine.get_ID());
		m_cacheInvoiceLine.set(invLine);
	}
	//end @win: autocreate asset disposal from ar invoice
	
	public MAssetDisposed (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}
	
	public MAsset getAsset()
	{
		return MAsset.get(getCtx(), getA_Asset_ID(), null);
	}
	
	
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

	
	public boolean unlockIt()
	{
		setProcessing(false);
		return true;
	}	//	unlockIt
	
	
	public boolean invalidateIt()
	{
		return false;
	}	//	invalidateIt
	
	
	public String prepareIt()
	{
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
		{
			return DocAction.STATUS_Invalid;
		}
		
		MPeriod.testPeriodOpen(getCtx(), getDateAcct(), "FAD", getAD_Org_ID());

		//saveEx() //commented by @win
		updateFromAsset(this);
		saveEx(get_TrxName()); //added by @win
		if (is_Changed())
		{
			throw new AssetStatusChangedException();
		}
		
		// Check that the FA is not just depreciated
		MDepreciationWorkfile assetwk = MDepreciationWorkfile.get(getCtx(), getA_Asset_ID(), getPostingType(), get_TrxName());
		if (assetwk.isDepreciated(getDateAcct()))
		{
			throw new AssetAlreadyDepreciatedException();
		}
		MDepreciationExp.checkExistsNotProcessedEntries(getCtx(), getA_Asset_ID(), getDateAcct(), getPostingType(), get_TrxName());
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
		{
			return DocAction.STATUS_Invalid;
		}
		//
		m_justPrepared = true;
		setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}	//	prepareIt
	
	
	public boolean approveIt()
	{
		if (log.isLoggable(Level.INFO)) log.info("approveIt - " + toString());
		setIsApproved(true);
		return true;
	}	//	approveIt
	
	
	public boolean rejectIt()
	{
		if (log.isLoggable(Level.INFO)) log.info("rejectIt - " + toString());
		setIsApproved(false);
		return true;
	}	//	rejectIt
	
	
	public String completeIt()
	{
		//	Re-Check
		if (!m_justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}
		
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		
		//	Implicit Approval
		if (!isApproved())
			approveIt();
		if (log.isLoggable(Level.INFO)) log.info(toString());
		//
		
		//loading asset
		MAsset asset = getAsset();
		if (log.isLoggable(Level.FINE)) log.fine("asset=" + asset);

		// Activation
		if(!isDisposal())
		{
			String method = getA_Activation_Method();
			if(method.equals(A_ACTIVATION_METHOD_Activation))
			{ // reactivation
				asset.changeStatus(MAsset.A_ASSET_STATUS_Activated, getDateDoc());
			}
			else
			{
				throw new AssetNotSupportedException(COLUMNNAME_A_Activation_Method, method);
			}
		}
		// Preservation/Partial Retirement/etc
		else
		{
			String method = getA_Disposed_Method();
			if (A_DISPOSED_METHOD_Preservation.equals(method))
			{
				asset.changeStatus(MAsset.A_ASSET_STATUS_Preservation, getDateDoc());
			}
			// Goodwill BF: Disposed Method fix
			else if (A_DISPOSED_METHOD_Simple_.equals(method)
					|| A_DISPOSED_METHOD_Trade.equals(method)
				)
			{
				asset.changeStatus(MAsset.A_ASSET_STATUS_Disposed, null);
				setA_Disposal_Amt(getA_Asset_Cost());
				setA_Accumulated_Depr_Delta(getA_Accumulated_Depr());
				setExpense(getA_Disposal_Amt().subtract(getA_Accumulated_Depr_Delta()));
				createDisposal();
			}
			else if (A_DISPOSED_METHOD_PartialRetirement.equals(method))
			{
				createDisposal();
			}
			else
			{
				throw new AssetNotSupportedException(COLUMNNAME_A_Disposed_Method, method);
			}
		}
		
		// Goodwill - Disposal will update Product and Asset's Quantities
		MAssetProduct assetProduct = MAssetProduct.getCreate(getCtx(), 
				getA_Asset_ID(), asset.getM_Product_ID(), 
				asset.getM_AttributeSetInstance_ID(), get_TrxName());
		assetProduct.setA_QTY_Current(Env.ZERO);
		assetProduct.setAD_Org_ID(asset.getAD_Org_ID());
		assetProduct.saveEx();
		assetProduct.updateAsset(asset);
		asset.setA_QTY_Current(Env.ZERO);
		asset.setQty(Env.ZERO);
		// End - Update
		
		asset.saveEx(get_TrxName());
		
		//	User Validation
		valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		
		// Done
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt
	
	
	public boolean voidIt()
	{
		throw new AssetNotImplementedException("");
	}	//	voidIt
	
	
	public boolean closeIt()
	{
		setDocAction(DOCACTION_None);
		return true;
	}	//	closeIt
	
	
	public boolean reverseCorrectIt()
	{
		throw new AssetNotImplementedException("");
	}	//	reverseCorrectionIt
	
	
	public boolean reverseAccrualIt()
	{
		throw new AssetNotImplementedException("");
	}
	
	
	public boolean reActivateIt()
	{
		throw new AssetNotImplementedException("");
	}
	
	
	
	public String getSummary()
	{
		return new StringBuffer()
				.append(getDocumentNo()).append("/").append(getDateDoc())
				.toString();
	}

	
	public String getProcessMsg()
	{
		return m_processMsg;
	}	//	getProcessMsg
	
	
	public int getDoc_User_ID()
	{
		return getCreatedBy();
	}

	
	public BigDecimal getApprovalAmt()
	{
		return Env.ZERO;
	} 
	
	
	public int getC_Currency_ID()
	{
		return MClient.get(getCtx(), getAD_Client_ID()).getAcctSchema().getC_Currency_ID();
	}
	
	
	protected boolean beforeSave (boolean newRecord)
	{
		if (getDateAcct() == null)
		{
			setDateAcct(getDateDoc());
		}
		if (newRecord || is_ValueChanged(COLUMNNAME_DateAcct))
		{
			setC_Period_ID(MPeriod.get(getCtx(), getDateAcct(), getAD_Org_ID()).get_ID());
		}
		if (getA_Disposed_Date() == null)
		{
			setA_Disposed_Date(getDateAcct());
		}
		/* commented by @win - asset type
		if (!MAssetType.isFixedAsset(getA_Asset_ID()))
		{
			throw new AssetException("This is not a Fixed Asset!");
		}
		*/
		return true;
	}
	
	/**
	 * Copy fields from A_Asset
	 * @param model
	 * @param A_Asset_ID
	 */
	public static void updateFromAsset(I_A_Asset_Disposed bean)
	{
		int asset_id = bean.getA_Asset_ID();
		SetGetUtil.copyValues(
				SetGetUtil.wrap(bean),
				MAsset.Table_Name, asset_id,
				new String[] {
					MAsset.COLUMNNAME_IsDisposed,
					MAsset.COLUMNNAME_A_Asset_Status,
					"AD_Org_ID",
				}
		);
		
		MDepreciationWorkfile wk = MDepreciationWorkfile.get(Env.getCtx(), asset_id, bean.getPostingType(), null);
		if (wk != null)
		{
			bean.setA_Asset_Cost(wk.getA_Asset_Cost());
			bean.setA_Accumulated_Depr(wk.getA_Accumulated_Depr());
		}
		else
		{
			bean.setA_Asset_Cost(Env.ZERO);
			bean.setA_Accumulated_Depr(Env.ZERO);
		}
	}
	
	
	public File createPDF ()
	{
		return null;
	}	//	createPDF
	
	
	public String getDocumentInfo()
	{
		return getDocumentNo();
	}	//	getDocumentInfo
	
	/**
	 * Check if this is a disposal (if the asset is not disposed)
	 * @return true if is disposal
	 */
	public boolean isDisposal()
	{
		return !isDisposed();
	}
	
	public static void setA_Disposal_Amt(I_A_Asset_Disposed bean)
	{
		int precision = 2;
		BigDecimal A_Asset_Cost = bean.getA_Asset_Cost();
		BigDecimal A_Disposal_Amt = bean.getA_Disposal_Amt();
		BigDecimal coef = Env.ZERO;
		if (A_Asset_Cost.signum() != 0)
		{
			coef = A_Disposal_Amt.divide(A_Asset_Cost, 12, RoundingMode.HALF_UP);
		}
		//
		BigDecimal A_Accumulated_Depr = bean.getA_Accumulated_Depr();
		BigDecimal A_Accumulated_Depr_Delta = A_Accumulated_Depr.multiply(coef).setScale(precision, RoundingMode.HALF_UP);
		BigDecimal Expense = A_Disposal_Amt.subtract(A_Accumulated_Depr_Delta);
		//
		bean.setA_Accumulated_Depr_Delta(A_Accumulated_Depr_Delta);
		bean.setExpense(Expense);
	}
	
	private void createDisposal()
	{
		MDepreciationWorkfile assetwk = MDepreciationWorkfile.get(getCtx(), getA_Asset_ID(), getPostingType(), get_TrxName());
		//assetwk.adjustCost(getA_Disposal_Amt().negate(), Env.ZERO, false);
		
		// Goodwill - Disposed Asset must not have quantity
		assetwk.adjustCost(getA_Disposal_Amt().negate(), assetwk.getA_QTY_Current().negate(), false);
		
		assetwk.adjustAccumulatedDepr(getA_Accumulated_Depr_Delta().negate(), getA_Accumulated_Depr_Delta().negate(), false);
		assetwk.saveEx();
		assetwk.buildDepreciation();
		//
		// Delete not processed expense entries
		List<MDepreciationExp> list = MDepreciationExp.getNotProcessedEntries(getCtx(), getA_Asset_ID(), getPostingType(), get_TrxName());
		for (MDepreciationExp ex : list)
		{
			ex.deleteEx(false);
		}
		
		// Goodwill - Update Asset History
		MAssetChange.createDisposal(this, assetwk);
	}
}
