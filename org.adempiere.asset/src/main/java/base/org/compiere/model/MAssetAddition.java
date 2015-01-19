package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProjectClose;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.FA.exceptions.AssetAlreadyDepreciatedException;
import org.compiere.FA.exceptions.AssetCheckDocumentException;
import org.compiere.FA.exceptions.AssetException;
import org.compiere.FA.exceptions.AssetNotImplementedException;
import org.compiere.FA.exceptions.AssetNotSupportedException;
import org.compiere.FA.feature.UseLifeImpl;
import org.adempiere.util.POCacheLocal;

/**
 *  Asset Addition Model
 *	@author Teo Sarca, SC ARHIPAC SERVICE SRL
 *
 * TODO: BUG: REG in depexp creates a zero if they have more sites Addition during 0?!
 */
public class MAssetAddition extends X_A_Asset_Addition
	implements DocAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5977180589101094202L;
	
	/** Static Logger */
	private static CLogger s_log = CLogger.getCLogger(MAssetAddition.class);

	public MAssetAddition (Properties ctx, int A_Asset_Addition_ID, String trxName)
	{
		super (ctx, A_Asset_Addition_ID, trxName);
		if (A_Asset_Addition_ID == 0)
		{
			setDocStatus(DOCSTATUS_Drafted);
			setDocAction(DOCACTION_Complete);
			setProcessed(false);
		}
	}	//	MAssetAddition
	public MAssetAddition (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MAAssetAddition

	
	protected boolean beforeSave (boolean newRecord)
	{
		setA_CreateAsset();
		/*
		if (getA_Asset().getM_Locator_ID() > 0) 
		{
			if (getM_Locator_ID() <= 0)
				setM_Locator_ID(getA_Asset().getM_Locator_ID());
			if (!getM_Locator().equals(getA_Asset().getM_Locator()))
				throw new AssetException("Asset and Addition's Locator are different");
		}*/
		
		//Goodwill - Set Useable Life according to Asset Group Accounting
		MAssetGroupAcct assetGrpAcct = MAssetGroupAcct.forA_Asset_Group_ID(getCtx(), getA_Asset().getA_Asset_Group_ID(), POSTINGTYPE_Actual);
		if (isA_CreateAsset())
		{
			setDeltaUseLifeYears(assetGrpAcct.getUseLifeYears());
			setDeltaUseLifeYears_F(assetGrpAcct.getUseLifeYears_F());
		}
		
		//Goodwill - Check for source invoice
		if (A_SOURCETYPE_Invoice.equals(getA_SourceType()))
		{
			MInvoiceLine iLine = new MInvoiceLine(getCtx(), getC_InvoiceLine_ID(), get_TrxName());
			if (A_CAPVSEXP_Capital.equals(iLine.getA_CapvsExp()) && iLine.getA_Asset_ID() == 0)
			{
				if (!iLine.get_ValueAsBoolean("IsCollectiveAsset")) 
					setA_QTY_Current(Env.ONE);
				else
					setA_QTY_Current(iLine.getQtyEntered());
			}
			if (A_CAPVSEXP_Capital.equals(iLine.getA_CapvsExp()) && iLine.getA_Asset_ID() > 0)
			{
				setA_QTY_Current(Env.ZERO);
				setA_CreateAsset(false);
			}
			setA_CapvsExp(iLine.getA_CapvsExp());
		}//Goodwill - End check

		if (isA_CreateAsset() && getA_QTY_Current().signum() == 0 && !A_SOURCETYPE_Invoice.equals(getA_SourceType()))
		{
			setA_QTY_Current(Env.ONE);
		}
		if (getC_Currency_ID() <= 0)
		{
			setC_Currency_ID(MClient.get(getCtx()).getAcctSchema().getC_Currency_ID());
		}
		if (getC_ConversionType_ID() <= 0)
		{
			setC_ConversionType_ID(MConversionType.getDefault(getAD_Client_ID()));
		}
		getDateAcct();
		setAssetValueAmt();
		
		if (isA_CreateAsset())
		{
			setA_CapvsExp(A_CAPVSEXP_Capital);
		}
		
		//
		// Check suspect asset values (UseLife, Amount etc):
		/* arhipac: teo_sarca: TODO need to integrate
		if (hasZeroValues() && (!m_confirmed_AssetValues && !isProcessed() && is_UserEntry()))
		{
			String msg = "@AssetValueAmt@="+getAssetValueAmt()
						+ "\n@DeltaUseLifeYears@="+getDeltaUseLifeYears()
							+ "\n@DeltaUseLifeYears_F@="+getDeltaUseLifeYears_F()+"\n";
			m_confirmed_AssetValues = UIFactory.getUI().ask(get_WindowNo(), null, "Confirm", Msg.parseTranslation(getCtx(), msg), true);
			if (!m_confirmed_AssetValues)
			{
				throw new AssetCheckDocumentException(msg);
			}
		}
		*/
		
		// set approved
		setIsApproved();
		
		return true;
	}	//	beforeSave
	
//	private boolean m_confirmed_AssetValues = false;

	/**
	 * Create Asset and asset Addition from MMatchInv.
	 * MAssetAddition is saved.
	 * @param match match invoice
	 * @return asset addition
	 */
	public static MAssetAddition createAsset(MMatchInv match)
	{
		MAssetAddition assetAdd = new MAssetAddition(match);
		assetAdd.dump();
		//@win add condition to prevent asset creation when expense addition or second addition
		if (MAssetAddition.A_CAPVSEXP_Capital.equals(assetAdd.getA_CapvsExp())
			&& match.getC_InvoiceLine().getA_Asset_ID() == 0 && assetAdd.isA_CreateAsset()) 
		{ 
		//end @win add condition to prevent asset creation when expense addition or second addition
			MAsset asset = assetAdd.createAsset();
			asset.dump();
			//@win add
			MAssetGroupAcct assetgrpacct = MAssetGroupAcct.forA_Asset_Group_ID(asset.getCtx(), asset.getA_Asset_Group_ID(), assetAdd.getPostingType());
			assetAdd.setDeltaUseLifeYears(assetgrpacct.getUseLifeYears());
			assetAdd.setDeltaUseLifeYears_F(assetgrpacct.getUseLifeYears_F());
		} 
		else {
			assetAdd.setA_Asset_ID(match.getC_InvoiceLine().getA_Asset_ID());
			assetAdd.setA_CreateAsset(false);
		}
		assetAdd.saveEx();
		return assetAdd;
	}
	
	/**
	 * Create Asset and asset Addition from MIFixedAsset. MAssetAddition is saved. 
	 * (@win note, not referenced from anywhere. incomplete feature)
	 * @param	match	match invoice
	 * @return asset addition
	 */
	public static MAssetAddition createAsset(MIFixedAsset ifa)
	{
		MAssetAddition assetAdd = new MAssetAddition(ifa);
		assetAdd.dump();
		//@win add condition to prevent asset creation when expense addition or second addition
		if (MAssetAddition.A_CAPVSEXP_Capital.equals(assetAdd.getA_CapvsExp())
				&& ifa.getA_Asset_ID() == 0) { 
		//end @win add condition to prevent asset creation when expense addition or second addition
			MAsset asset = assetAdd.createAsset();
			asset.dump();	
		}
		assetAdd.saveEx();
		return assetAdd;
	}
	
	//@win create asset from Project
	/**
	 * Create Asset and asset Addition from MProject. MAssetAddition is saved. 
	 * Addition from Project only allows initial addition (will definitely create new asset)
	 * @param	project
	 * @return asset addition
	 */
	public static MAssetAddition createAsset(MProject project, MProduct product)
	{
		MAssetAddition assetAdd = new MAssetAddition(project);
		assetAdd.dump();
		
		MAsset asset = assetAdd.createAsset();
		
		if (product != null) {
			asset.setM_Product_ID(product.getM_Product_ID());
			asset.setA_Asset_Group_ID(product.getA_Asset_Group_ID());
			MAttributeSetInstance asi = MAttributeSetInstance.create(Env.getCtx(), product, null);
			asset.setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
		}
		asset.setName(product.getName().concat(project.getName()));
		asset.setValue(product.getName().concat(project.getName()));	
		asset.saveEx();
		asset.dump();
		
		// Copy UseLife values from asset group to workfile
		MAssetGroupAcct assetgrpacct = MAssetGroupAcct.forA_Asset_Group_ID(asset.getCtx(), asset.getA_Asset_Group_ID(), assetAdd.getPostingType());
		assetAdd.setDeltaUseLifeYears(assetgrpacct.getUseLifeYears());
		assetAdd.setDeltaUseLifeYears_F(assetgrpacct.getUseLifeYears_F());
		assetAdd.setA_Asset(asset);
		assetAdd.saveEx();
		//@win add
		
		return assetAdd;
	}
	//end @win create asset from Project
	/**	
	 * Create Asset
	 */
	private MAsset createAsset()
	{
		MAsset asset = null;
		if (getA_Asset_ID() <= 0)
		{
			String sourceType = getA_SourceType();
			if (A_SOURCETYPE_Invoice.equals(sourceType))
			{
				asset = new MAsset(getMatchInv(false));
				asset.saveEx();
				
				//Goodwill - update asset name
				asset.setName(asset.getName() + "-" + asset.getInventoryNo());
				
				asset.saveEx();
				setA_Asset(asset);
			}
			else if (A_SOURCETYPE_Imported.equals(sourceType))
			{
				asset = new MAsset(getI_FixedAsset(false));
				asset.saveEx();
				setA_Asset(asset);
			}
			else if (A_SOURCETYPE_Project.equals(sourceType))
			{
				//@win add code for generate from Project
				asset = new MAsset(getC_Project(false));
				//end @win add code for generate from Project
			}
			else
			{
				throw new AssetNotSupportedException(COLUMNNAME_A_SourceType, sourceType);
			}
		}
		else
		{
			asset = getA_Asset(false);
		}
		//
		return asset;
	}
	
	/**
	 * Construct addition from match invoice 
	 * @param match	match invoice model
	 */
	private MAssetAddition (MMatchInv match)
	{
		this(match.getCtx(), 0, match.get_TrxName());
		setM_MatchInv(match);
		setC_DocType_ID();
	}
	
	//added by @win
	/**
	 * @author @win
	 * Construct addition from Project
	 * @param project 
	 */
	private MAssetAddition (MProject project)
	{
		this(project.getCtx(), 0, project.get_TrxName());
		if (log.isLoggable(Level.FINEST)) log.finest("Entering: Project=" + project);
		setAD_Org_ID(project.getAD_Org_ID());
		setPostingType(POSTINGTYPE_Actual);
		setA_SourceType(A_SOURCETYPE_Project);
		//
		setC_Currency_ID(project.getC_Currency_ID());
		if (project.get_ValueAsInt("C_ConversionType_ID")>0) {
			setC_ConversionType_ID(project.get_ValueAsInt("C_ConversionType_ID"));
		}
		setSourceAmt(project.getProjectBalanceAmt());
		setDateDoc(new Timestamp (System.currentTimeMillis()));
		setA_CreateAsset(true); //added by @win as create from project will certainly for createnew
		setDeltaUseLifeYears(I_ZERO);
		setDeltaUseLifeYears_F(I_ZERO);
		setC_DocType_ID();
		
		Timestamp dateAcct = new Timestamp (System.currentTimeMillis());
		if (dateAcct != null)
		{
			dateAcct = UseLifeImpl.getDateAcct(dateAcct, 1);
			if (log.isLoggable(Level.FINE)) log.fine("DateAcct=" + dateAcct);
			setDateAcct(dateAcct);
		}
		setC_Project(project);
	}
	
	private final POCacheLocal<MProject> m_cacheCProject = POCacheLocal.newInstance(this, MProject.class);
	public MProject getC_Project(boolean requery)
	{
		return m_cacheCProject.get(requery);
	}
	private void setC_Project(MProject project)
	{
		set_Value("C_Project_ID", project.get_ID());
		m_cacheCProject.set(project);
	}
	//end added by @win
	/**IDR
	 * Construct addition from import
	 * @param ifa	fixed asset import
	 */
	private MAssetAddition (MIFixedAsset ifa)
	{
		this(ifa.getCtx(), 0, ifa.get_TrxName());
		if (log.isLoggable(Level.FINEST)) log.finest("Entering: ifa=" + ifa);
		setAD_Org_ID(ifa.getAD_Org_ID());
		setPostingType(POSTINGTYPE_Actual);
		setA_SourceType(A_SOURCETYPE_Imported);
		//
		setM_Product_ID(ifa.getM_Product_ID());
		setSourceAmt(ifa.getA_Asset_Cost());
		setDateDoc(ifa.getAssetServiceDate());
		setM_Locator_ID(ifa.getM_Locator_ID());
		
		boolean isAccmDeprAdjust = (ifa.getA_Accumulated_Depr().compareTo(Env.ZERO) > 0) ? true : false;
		setA_Accumulated_Depr_Adjust(isAccmDeprAdjust);
		
		//Goodwill - if imported asset was already fully depreciated
		if (ifa.getA_Current_Period() == 0) 
		{
			setA_Period_Start(ifa.getUseLifeMonths() + 1);
			ifa.setA_Accumulated_Depr_F(ifa.getA_Accumulated_Depr());
			ifa.setUseLifeMonths_F(ifa.getUseLifeMonths());
		}
		//Goodwill - normal imported asset
		else 
			setA_Period_Start(ifa.getA_Current_Period());

		setA_Accumulated_Depr(ifa.getA_Accumulated_Depr());
		setA_Accumulated_Depr_F(ifa.getA_Accumulated_Depr_F());
		setDeltaUseLifeYears((int)(ifa.getUseLifeMonths() / 12));
		setDeltaUseLifeYears_F((int)(ifa.getUseLifeMonths_F() / 12));
		
		setA_CapvsExp(MAssetAddition.A_CAPVSEXP_Capital); //added by zuhri, import must be in Capital
		setA_CreateAsset(true); //added by zuhri, import must be create asset
		setA_Salvage_Value(ifa.getA_Salvage_Value());
		setC_DocType_ID();
		
		Timestamp dateAcct = ifa.getDateAcct();
		if (dateAcct != null)
		{
			//dateAcct = UseLifeImpl.getDateAcct(dateAcct, 1); //commented by @win -- i don't see why i should add 1 month
			if (log.isLoggable(Level.FINE)) log.fine("DateAcct=" + dateAcct);
			setDateAcct(dateAcct);
		}
		setI_FixedAsset(ifa);
	}
	
	/** Match Invoice Cache */
	private final POCacheLocal<MMatchInv> m_cacheMatchInv = POCacheLocal.newInstance(this, MMatchInv.class);

	/**
	 * @param requery
	 * @return
	 */
	private MMatchInv getMatchInv(boolean requery)
	{
		return m_cacheMatchInv.get(requery);
	}
	
	private void setM_MatchInv(MMatchInv mi)
	{
		MInvoiceLine iLine = new MInvoiceLine(getCtx(), mi.getC_InvoiceLine_ID(), get_TrxName());
		
		mi.load(get_TrxName());
		setAD_Org_ID(mi.getAD_Org_ID());
		setPostingType(POSTINGTYPE_Actual);
		setA_SourceType(A_SOURCETYPE_Invoice);
		setM_MatchInv_ID(mi.get_ID());
		
		if (MAssetAddition.A_CAPVSEXP_Capital.equals(mi.getC_InvoiceLine().getA_CapvsExp()))
		{
			if (mi.getC_InvoiceLine().getA_Asset_ID() == 0)
				setA_CreateAsset(true);
			if (mi.getC_InvoiceLine().getA_Asset_ID() > 0)
				setA_CreateAsset(false);
		}
		else 
			setA_CreateAsset(false);
		 
		setC_Invoice_ID(mi.getC_InvoiceLine().getC_Invoice_ID());
		setC_InvoiceLine_ID(mi.getC_InvoiceLine_ID());
		setM_InOutLine_ID(mi.getM_InOutLine_ID());
		setM_Product_ID(mi.getM_Product_ID());
		setM_AttributeSetInstance_ID(mi.getM_AttributeSetInstance_ID());
		setLine(mi.getC_InvoiceLine().getLine());
		setM_Locator_ID(mi.getM_InOutLine().getM_Locator_ID());
		setA_CapvsExp(mi.getC_InvoiceLine().getA_CapvsExp());
		setC_Currency_ID(mi.getC_InvoiceLine().getC_Invoice().getC_Currency_ID());
		setC_ConversionType_ID(mi.getC_InvoiceLine().getC_Invoice().getC_ConversionType_ID());
		setDateDoc(mi.getM_InOutLine().getM_InOut().getMovementDate());
		setDateAcct(mi.getM_InOutLine().getM_InOut().getMovementDate());
		
		//Goodwill - If the quantities were collective 
		if (MInvoiceLine.A_CAPVSEXP_Capital.equals(iLine.getA_CapvsExp()) && iLine.get_ValueAsBoolean("IsCollectiveAsset"))
		{
			setA_QTY_Current(mi.getQty());
			setAssetAmtEntered(mi.getC_InvoiceLine().getLineNetAmt());
			setAssetSourceAmt(mi.getC_InvoiceLine().getLineNetAmt());		
		}
		//Goodwill - If the quantities were not collective
		if (MInvoiceLine.A_CAPVSEXP_Capital.equals(iLine.getA_CapvsExp()) && !iLine.get_ValueAsBoolean("IsCollectiveAsset") && iLine.getA_Asset_ID() <= 0)
		{
			setA_QTY_Current(Env.ONE);
			setAssetAmtEntered(mi.getC_InvoiceLine().getLineNetAmt().divide(mi.getQty()));
			setAssetSourceAmt(mi.getC_InvoiceLine().getLineNetAmt().divide(mi.getQty()));
		}
		//Goodwill - If the invoice not create new asset
		// or if the invoice is an expense type
		if ((MInvoiceLine.A_CAPVSEXP_Capital.equals(iLine.getA_CapvsExp()) && iLine.getA_Asset_ID() > 0) || 
				MInvoiceLine.A_CAPVSEXP_Expense.equals(iLine.getA_CapvsExp()))
		{
			setA_QTY_Current(Env.ZERO);
			setAssetAmtEntered(mi.getC_InvoiceLine().getLineNetAmt());
			setAssetSourceAmt(mi.getC_InvoiceLine().getLineNetAmt());
		}
		m_cacheMatchInv.set(mi);
	}
	
	/**
	 * Copy fields from MatchInv+InvoiceLine+InOutLine
	 * @param model - to copy from
	 * @param M_MatchInv_ID - matching invoice id
	 * @param newRecord new object model is created
	 */
	public static boolean setM_MatchInv(SetGetModel model, int M_MatchInv_ID)
	{
		boolean newRecord = false;
		String trxName = null;
		if (model instanceof PO)
		{
			PO po = (PO)model;
			newRecord = po.is_new();
			trxName = po.get_TrxName();
			
		}
		
		if (s_log.isLoggable(Level.FINE)) s_log.fine("Entering: model=" + model + ", M_MatchInv_ID=" + M_MatchInv_ID + ", newRecord=" + newRecord + ", trxName=" + trxName);
		
		final String qMatchInv_select = "SELECT"
				+ "  C_Invoice_ID"
				+ ", C_InvoiceLine_ID"
				+ ", M_InOutLine_ID"
				+ ", M_Product_ID"
				+ ", M_AttributeSetInstance_ID"
				+ ", Qty AS "+COLUMNNAME_A_QTY_Current
				+ ", InvoiceLine AS "+COLUMNNAME_Line
				+ ", M_Locator_ID"
				+ ", A_CapVsExp"
				+ ", MatchNetAmt AS "+COLUMNNAME_AssetAmtEntered
				+ ", MatchNetAmt AS "+COLUMNNAME_AssetSourceAmt
				+ ", C_Currency_ID"
				+ ", C_ConversionType_ID"
				+ ", MovementDate AS "+COLUMNNAME_DateDoc
		;
		final String qMatchInv_from = " FROM mb_matchinv WHERE M_MatchInv_ID="; //@win change M_MatchInv_ARH to M_MatchInv
		
		String query = qMatchInv_select;
		if (newRecord) {
			query += ", A_Asset_ID, A_CreateAsset";
		}
		query += qMatchInv_from + M_MatchInv_ID;
		
		/*
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			pstmt = DB.prepareStatement(query, trxName);
			DB.setParameters(pstmt, params);
			rs = pstmt.executeQuery();
			updateColumns(models, columnNames, rs);
		}
		catch (SQLException e)
		{
			throw new DBException(e, query);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		*/
		SetGetUtil.updateColumns(model, null, query, trxName);
		
		s_log.fine("Leaving: RETURN TRUE");
		return true;
	}
	
	private final POCacheLocal<MIFixedAsset> m_cacheIFixedAsset = POCacheLocal.newInstance(this, MIFixedAsset.class);
	public MIFixedAsset getI_FixedAsset(boolean requery)
	{
		return m_cacheIFixedAsset.get(requery);
	}
	private void setI_FixedAsset(MIFixedAsset ifa)
	{
		setI_FixedAsset_ID(ifa.get_ID());
		m_cacheIFixedAsset.set(ifa);
	}
	
	/**
	 *	Sets the AssetValueAmt from AssetSourceAmt using C_Currency_ID and C_ConversionRate_ID
	 */
	private void setAssetValueAmt()
	{
		getDateAcct();
		MConversionRateUtil.convertBase(SetGetUtil.wrap(this),
				COLUMNNAME_DateAcct,
				COLUMNNAME_AssetSourceAmt,
				COLUMNNAME_AssetValueAmt,
				null);
	}
	
	public void setSourceAmt(BigDecimal amt)
	{
		setAssetAmtEntered(amt);
		setAssetSourceAmt(amt);
	}
	
	/**
	 *
	 */
	public void setIsApproved()
	{
		if(!isProcessed())
		{
			String str = Env.getContext(getCtx(), "#IsCanApproveOwnDoc");
			boolean isApproved = "Y".equals(str); //ARHIPAC.toBoolean(str, false);
			if (log.isLoggable(Level.FINE)) log.fine("#IsCanApproveOwnDoc=" + str + "=" + isApproved);
			setIsApproved(isApproved);
		}
	}
	
	
	public Timestamp getDateAcct()
	{
		Timestamp dateAcct = super.getDateAcct();
		if (dateAcct == null) {
			dateAcct = getDateDoc();
			setDateAcct(dateAcct);
		}
		return dateAcct;
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
		if (log.isLoggable(Level.INFO)) log.info("unlockIt - " + toString());
	//	setProcessing(false);
		return true;
	}	//	unlockIt
	
	
	public boolean invalidateIt()
	{
		if (log.isLoggable(Level.INFO)) log.info("invalidateIt - " + toString());
		return false;
	}	//	invalidateIt
	
	
	public String prepareIt()
	{
		if (log.isLoggable(Level.INFO)) log.info(toString());
		
		// Call model validators
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
		{
			return DocAction.STATUS_Invalid;
		}
		
		MPeriod.testPeriodOpen(getCtx(), getDateAcct(), "FAA", getAD_Org_ID()); //Goodwill - new asset doctype
		
		// Goodwill - setting Create Asset checkbox
		setA_CreateAsset();
		
		// Check AssetValueAmt != 0
		if (getAssetValueAmt().signum() == 0) {
			m_processMsg="@Invalid@ @AssetValueAmt@=0";
			return DocAction.STATUS_Invalid;
		}
		
		MAsset asset = getA_Asset(true);
		MDepreciationWorkfile assetwk = MDepreciationWorkfile.get(getCtx(), getA_Asset_ID(), getPostingType(), get_TrxName());
		
		// Goodwill - Check asset disposal status
		if (MAsset.A_ASSET_STATUS_Disposed.equals(asset.getA_Asset_Status()))
		{
			m_processMsg = "Asset aldready disposed";
			return DocAction.STATUS_Invalid;
		}// End - check asset disposal status
		
		// Goodwill - Check if asset already depreciated 
		if (!MAsset.A_ASSET_STATUS_New.equals(asset.getA_Asset_Status()) && assetwk != null 
				&& assetwk.getDateAcct() != null && assetwk.isDepreciated(getDateAcct()))
		{
			m_processMsg = "Asset already depreciated for this period";
			return DocAction.STATUS_Invalid;
		}// End - check asset depreciated status
		
		// Goodwill - Validation on Asset Addition Date
		if (getDateDoc().before(asset.getA_Asset_CreateDate()))
		{
			throw new AssetCheckDocumentException("Document is date older than Asset Create Date");
		}
		else if (asset.getAssetServiceDate() != null && getDateDoc().before(asset.getAssetServiceDate()))
		{
			throw new AssetCheckDocumentException("Document is date older than Asset Service Date");
		}
		
		// Additions may be made ​​only on sites that depreciates FA 
		// (WARNING: FA sites scrapped / sold not depreciate)
		
		/* @win temporary comment as some assets are not depreciated
		if(!isA_CreateAsset() && !asset.isDepreciated())
		{
			m_processMsg = "@AssetIsNotDepreciating@";
			return DocAction.STATUS_Invalid;
		}
		*/
		
		// If new assets (not renewals) must have nonzero values
		if (isA_CreateAsset() && hasZeroValues())
		{
			throw new AssetException("New document must have non-zero values");
		}
		
		// Goodwill - can add asset value without adding asset usable life
		if (!isA_CreateAsset() && getDeltaUseLifeYears() < 0)
		{
			throw new AssetException("Delta Use Life Years cannot be negative values");
		}
		
		// Goodwill - Validation on Depreciated Asset
		if (MAsset.A_ASSET_STATUS_Depreciated.equals(asset.getA_Asset_Status()))
		{
			throw new AssetException("Asset is fully depreciated");
		}
		
		// Only New assets can be activated
		if (isA_CreateAsset() && !MAsset.A_ASSET_STATUS_New.equals(asset.getA_Asset_Status()))
		{
			throw new AssetException("Only new assets can be activated");
		}
		//
		
		// Goodwill
		// Validate Source - Invoice
		if (A_SOURCETYPE_Invoice.equals(getA_SourceType()))
		{
			int C_Invoice_ID = getC_Invoice_ID();
			MInvoice invoice = new MInvoice(getCtx(), C_Invoice_ID, get_TrxName());
			if (MInvoice.DOCSTATUS_Voided.equals(invoice.getDocStatus()))
			{
				throw new AssetException("You cannot add asset from voided document(s)");
			}
		}
		// End Validate
		
		// Validate Source - Project
		if (A_SOURCETYPE_Project.equals(getA_SourceType()))
		{
			if (getC_Project_ID() <= 0)
			{
				throw new FillMandatoryException(COLUMNNAME_C_Project_ID);
			}
			final String whereClause = COLUMNNAME_C_Project_ID+"=?"
								+" AND DocStatus IN ('IP','CO','CL')"
								+" AND "+COLUMNNAME_A_Asset_Addition_ID+"<>?";
			List<MAssetAddition> list = new Query(getCtx(), Table_Name, whereClause, get_TrxName())
					.setParameters(new Object[]{getC_Project_ID(), get_ID()})
					.list();
			if (list.size() > 0)
			{
				StringBuilder sb = new StringBuilder("You can not create project for this asset,"
									+" Project already has assets. View: ");
				for (MAssetAddition aa : list)
				{
					sb.append(aa.getDocumentInfo()).append("; ");
				}
				throw new AssetException(sb.toString());
			}
		}
		
		// Call model validators
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
		{
			return DocAction.STATUS_Invalid;
		}

		//	Done
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
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
		//	User Validation
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null) {
			return DocAction.STATUS_Invalid;
		}
		
		//	Implicit Approval
		if (!isApproved())
			approveIt();
		if (log.isLoggable(Level.INFO)) log.info(toString());
		//
		
		// Check/Create ASI:
		checkCreateASI();
		
		//loading asset
		MAsset asset = getA_Asset(!m_justPrepared); // requery if not just prepared
		if (log.isLoggable(Level.FINE)) log.fine("asset=" + asset);
		// Goodwill
		if (asset == null)
		{
			m_processMsg = "Asset not created/selected";
			return DocAction.STATUS_Invalid;
		}
		asset.setM_AttributeSetInstance_ID(getM_AttributeSetInstance_ID());
		// end Goodwill

		//
		// Get/Create Asset Workfile:
		// If there Worksheet creates a new file in this asset
		MDepreciationWorkfile assetwk = MDepreciationWorkfile.get(getCtx(), getA_Asset_ID(), getPostingType(), get_TrxName());
		if (assetwk == null)
		{
			assetwk = new MDepreciationWorkfile(asset, getPostingType(), null);
		}
		if (log.isLoggable(Level.FINE)) log.fine("workfile: " + assetwk);

		// Can not upgrade a previous period
		/* 
		if (!isA_CreateAsset() && assetwk.isDepreciated(getDateAcct()))
		{
			throw new AssetAlreadyDepreciatedException();
		}*/

		// Do we have entries that are not processed and before this date:
		if (this.getA_CapvsExp().equals(A_CAPVSEXP_Capital)) { 
			//@win modification to asset value and use life should be restricted to Capital
			MDepreciationExp.checkExistsNotProcessedEntries(assetwk.getCtx(), assetwk.getA_Asset_ID(), getDateAcct(), assetwk.getPostingType(), assetwk.get_TrxName());
				//
			if (this.getA_Salvage_Value().signum() > 0) {
				assetwk.setA_Salvage_Value(this.getA_Salvage_Value());
			}
			assetwk.adjustCost(getAssetValueAmt(), getA_QTY_Current(), isA_CreateAsset()); // reset if isA_CreateAsset
			assetwk.adjustUseLife(getDeltaUseLifeYears(), getDeltaUseLifeYears_F(), isA_CreateAsset()); // reset if isA_CreateAsset
			assetwk.setDateAcct(getDateAcct());
			assetwk.setProcessed(true);
			assetwk.saveEx();
		}
			
		// Adding input to Asset History tab
		MAssetChange.createAddition(this, assetwk);
		
		// Setting locator if is CreateAsset
		if (isA_CreateAsset() && getM_Locator_ID() > 0)
		{
			asset.setM_Locator_ID(getM_Locator_ID());
		}
		
		// Creating/Updating asset product
		updateA_Asset_Product(false);

		// Changing asset status to Activated or Depreciated
		if (isA_CreateAsset())
		{
			asset.setAssetServiceDate(getDateDoc());
			asset.changeStatus(MAsset.A_ASSET_STATUS_Activated, getDateAcct());
			asset.setA_QTY_Original(getA_QTY_Current()); //Goodwill - first qty entered
		}
		asset.saveEx();
		
		//@win set initial depreciation period = 1 
		if (isA_CreateAsset() && !isA_Accumulated_Depr_Adjust())
		{
			assetwk.setA_Current_Period(1);
			assetwk.saveEx();
		}//
		
		if (isA_CreateAsset() && isA_Accumulated_Depr_Adjust())
		{
			assetwk.setA_Current_Period(getA_Period_Start());
			assetwk.setA_Accumulated_Depr(getA_Accumulated_Depr());
			assetwk.setA_Accumulated_Depr_F(getA_Accumulated_Depr_F());
			assetwk.saveEx();
		}
		
		// Accumulated depreciation (if any):
		/*
		if (isA_Accumulated_Depr_Adjust())
		{
			Collection<MDepreciationExp> expenses = MDepreciationExp.createDepreciation(assetwk,
														1, // PeriodNo
														getDateAcct(),
														getA_Accumulated_Depr(), getA_Accumulated_Depr_F(),
														null,	// Accum Amt
														null,	// Accum Amt (F)
														null,	// Help
														null);
			for (MDepreciationExp exp : expenses)
			{
				exp.setA_Asset_Addition_ID(getA_Asset_Addition_ID());
				exp.process();
			}
			
			if (isA_CreateAsset() && isA_Accumulated_Depr_Adjust())
			{
				assetwk.setA_Current_Period(getA_Period_Start());
				assetwk.saveEx();
			}
		}
		*/
		
		// Rebuild depreciation:
		assetwk.buildDepreciation();
		
		//
		updateSourceDocument(false);
		
		// finish
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		//
		//	User Validation
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (m_processMsg != null) {
			return DocAction.STATUS_Invalid;
		}
		//
		return DocAction.STATUS_Completed;
	}	//	completeIt
	
	
	public boolean voidIt()
	{
		// Before Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (m_processMsg != null)
			return false;
		
		// Goodwill - check incomplete doc status
		if (DOCSTATUS_Drafted.equals(getDocStatus())
			|| DOCSTATUS_Invalid.equals(getDocStatus())
			|| DOCSTATUS_InProgress.equals(getDocStatus())
			|| DOCSTATUS_Approved.equals(getDocStatus())
			|| DOCSTATUS_NotApproved.equals(getDocStatus()))
		{
			setA_CreateAsset(false);
		}
		else
		{
			reverseIt(false);
		}

		//	User Validation
		String errmsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_VOID);
		if (errmsg != null)
		{
			m_processMsg = errmsg;
			return false;
		}
		
		// finish
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	}	//	voidIt
	
	private void reverseIt(boolean isReActivate)
	{
		if (DOCSTATUS_Closed.equals(getDocStatus())
				|| DOCSTATUS_Reversed.equals(getDocStatus())
				|| DOCSTATUS_Voided.equals(getDocStatus()))
		{
			setDocAction(DOCACTION_None);
			throw new AssetException("Document Closed: " + getDocStatus());
		}
		
		// Goodwill - Check asset disposal status
		MAsset asset = getA_Asset(true);
		if (MAsset.A_ASSET_STATUS_Disposed.equals(asset.getA_Asset_Status()))
		{
			setDocAction(DOCACTION_None);
			throw new AssetException("Asset already disposed");
		}// End - check asset disposal status
		

		// Handling Workfile
		MDepreciationWorkfile assetwk = MDepreciationWorkfile.get(getCtx(), getA_Asset_ID(), getPostingType(), get_TrxName());
		if (assetwk == null)
		{
			throw new AssetException("@NotFound@ @A_DepreciationWorkfile_ID");
		}
		
		// Goodwill: Check if there are Additions after this one
		final String additionClause = MAssetAddition.COLUMNNAME_A_Asset_Addition_ID+">?"
			+" AND "+MAssetAddition.COLUMNNAME_A_Asset_ID+"=?"
			+" AND "+MAssetAddition.COLUMNNAME_A_CapvsExp+"=?"
			+" AND "+MAssetAddition.COLUMNNAME_M_AttributeSetInstance_ID+">?"
			+" AND "+MAssetAddition.COLUMNNAME_AssetAmtEntered+">?"
			+" AND "+MAssetAddition.COLUMNNAME_DateAcct+">=?"
			//+" AND "+MAssetAddition.COLUMNNAME_DeltaUseLifeYears+">?"
			+" AND "+MAssetAddition.COLUMNNAME_Processed+"=?";
		
		List<MAssetAddition> listAddition = new Query(getCtx(), MAssetAddition.Table_Name, additionClause, get_TrxName())
				.setParameters(new Object[]{getA_Asset_Addition_ID(),getA_Asset_ID(),A_CAPVSEXP_Capital,getM_AttributeSetInstance_ID(),BigDecimal.ZERO,getDateAcct(),/*BigDecimal.ZERO,*/"Y"})
				.setOrderBy(MAssetAddition.COLUMNNAME_DateAcct+" ASC "
					+","+MAssetAddition.COLUMNNAME_A_Asset_Addition_ID+" ASC "
					+","+MAssetAddition.COLUMNNAME_M_AttributeSetInstance_ID+" ASC ")
				.list();
		// End check
		
		if (assetwk.isFullyDepreciated())
		{
			throw new AssetNotImplementedException("Unable to verify if it is fully depreciated");
		}
		
		// cannot update a previous period
		// cannot reverse already depreciated addition
		if (/*!isA_CreateAsset() &&*/ assetwk.isDepreciated(getDateAcct()))
		{
			throw new AssetAlreadyDepreciatedException();
		}
		
		// adjust the asset value
		assetwk.adjustCost(getAssetValueAmt().negate(), getA_QTY_Current().negate(), false);
		assetwk.adjustUseLife(0 - getDeltaUseLifeYears(), 0 - getDeltaUseLifeYears_F(), false);
		assetwk.saveEx();
		
		if (listAddition.size() == 0)
		{		
			/*
			// Delete Expense Entries that were created by this addition
			{
				final String whereClause = MDepreciationExp.COLUMNNAME_A_Asset_Addition_ID+"=?"
										+" AND "+MDepreciationExp.COLUMNNAME_PostingType+"=?";
				List<MDepreciationExp>
				list = new Query(getCtx(), MDepreciationExp.Table_Name, whereClause, get_TrxName())
							.setParameters(new Object[]{get_ID(), assetwk.getPostingType()})
							.setOrderBy(MDepreciationExp.COLUMNNAME_DateAcct+" DESC, "+MDepreciationExp.COLUMNNAME_A_Depreciation_Exp_ID+" DESC")
							.list();
				for (MDepreciationExp depexp: list)
				{
					depexp.deleteEx(true);
				}
			}*/
			// Update/Delete working file (after all entries were deleted)
			if (isA_CreateAsset())
			{
				assetwk.buildDepreciation();
				assetwk.deleteEx(true);
			}
			else
				assetwk.buildDepreciation();
		}
		else
		{
			assetwk.setA_Current_Period();
			assetwk.saveEx();
			// Goodwill
			assetwk.buildDepreciation();
		}
		
		// Creating/Updating asset product
		updateA_Asset_Product(true);
		
		// Change Asset Status
		if (isA_CreateAsset() && listAddition.size() == 0)
		{
			//MAsset asset = getA_Asset(true);
			asset.changeStatus(MAsset.A_ASSET_STATUS_New, getDateAcct());
			//asset.isDepreciated();
			//asset.setIsDepreciated(true);
			asset.saveEx();
						
			if (!isReActivate)
			{
				setA_CreateAsset(false); // reset flag
			}
		}
		
		MFactAcct.deleteEx(get_Table_ID(), get_ID(), get_TrxName());
    
		updateSourceDocument(true);
	} // reverseIt
	
	
	public boolean closeIt()
	{
		if (log.isLoggable(Level.INFO)) log.info("closeIt - " + toString());
		setDocAction(DOCACTION_None);
		return true;
	}	//	closeIt
	
	
	public boolean reverseCorrectIt()
	{
		throw new AssetNotImplementedException("reverseCorrectIt");
	}	//	reverseCorrectionIt
	
	
	public boolean reverseAccrualIt()
	{
		throw new AssetNotImplementedException("reverseAccrualIt");
	}	//	reverseAccrualIt
	
	
	public boolean reActivateIt()
	{
		// Before
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (m_processMsg != null)
			return false;
		
		reverseIt(true);

		//	User Validation
		String errmsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_REACTIVATE);
		if (errmsg != null) {
			m_processMsg = errmsg;
			return false;
		}
		
		// finish
		setProcessed(false);
		setDocAction(DOCACTION_Complete);
		return true;
	}	//	reActivateIt
	
	
	public String getSummary()
	{
		MAsset asset = getA_Asset(false);
		StringBuilder sb = new StringBuilder();
		sb.append("@DocumentNo@ #").append(getDocumentNo())
			.append(": @A_CreateAsset@=@").append(isA_CreateAsset() ? "Y" : "N").append("@")
		;
		if (asset != null)
		{
			sb.append(", @A_Asset_ID@=").append(asset.getName());
		}
		
		return Msg.parseTranslation(getCtx(), sb.toString());
	}	//	getSummary

	
	public String getProcessMsg()
	{
		return m_processMsg;
	}	//	getProcessMsg
	
	
	public int getDoc_User_ID()
	{
		return getCreatedBy();
	}	//	getDoc_User_ID

	
	public BigDecimal getApprovalAmt()
	{
		return getAssetValueAmt();
	}	//	getApprovalAmt
	
	
	/** Asset Cache */
	private final POCacheLocal<MAsset> m_cacheAsset = POCacheLocal.newInstance(this, MAsset.class);
	
	/**
	 * Get Asset 
	 * @param requery
	 * @return asset
	 */
	public MAsset getA_Asset(boolean requery)
	{
		return m_cacheAsset.get(requery);
	}
	
	/**
	 * Set Asset 
	 * @return asset
	 */
	private void setA_Asset(MAsset asset)
	{
		setA_Asset_ID(asset.getA_Asset_ID());
		m_cacheAsset.set(asset);
	} // setAsset
	
	
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if(!success)
		{
			return false;
		}
		updateSourceDocument(false);
		return true;
	}	//	afterSave

	/**
	 * Update Source Document (Invoice, Project etc) Status
	 * @param isReversal is called from a reversal action (like Void, Reverse-Correct).
	 * 					We need this flag because that when we call the method from voidIt()
	 * 					the document is not marked as voided yet. Same thing applies for reverseCorrectIt too. 
	 */
	private void updateSourceDocument(final boolean isReversalParam)
	{
		boolean isReversal = isReversalParam;
		// Check if this document is reversed/voided
		String docStatus = getDocStatus();
		if (!isReversal && (DOCSTATUS_Reversed.equals(docStatus) || DOCSTATUS_Voided.equals(docStatus)))
		{
			isReversal = true;
		}
		final String sourceType = getA_SourceType();
		//
		// Invoice: mark C_InvoiceLine.A_Processed='Y' and set C_InvoiceLine.A_Asset_ID
		if (A_SOURCETYPE_Invoice.equals(sourceType) && isProcessed())
		{
			int C_InvoiceLine_ID = getC_InvoiceLine_ID();
			MInvoiceLine invoiceLine = new MInvoiceLine(getCtx(), C_InvoiceLine_ID, get_TrxName());
			invoiceLine.setA_Processed(!isReversal);
			invoiceLine.setA_Asset_ID(isReversal ? 0 : getA_Asset_ID());
			invoiceLine.saveEx();
		}
		//
		// Project
		else if (A_SOURCETYPE_Project.equals(sourceType) && isProcessed())
		{
			if (isReversal)
			{
				// Project remains closed. We just void/reverse/reactivate the Addition
			}
			else
			{
				//TODO decide whether to close project first or later
				
				int project_id = getC_Project_ID();
				ProcessInfo pi = new ProcessInfo("", 0, MProject.Table_ID, project_id);
				pi.setAD_Client_ID(getAD_Client_ID());
				pi.setAD_User_ID(Env.getAD_User_ID(getCtx()));
				//
				ProjectClose proc = new ProjectClose();
				proc.startProcess(getCtx(), pi, Trx.get(get_TrxName(), false));
				if (pi.isError())
				{
					throw new AssetException(pi.getSummary());
				}
				
			}
		}
		//
		// Import
		else if (A_SOURCETYPE_Imported.equals(sourceType) && !isProcessed())
		{
			if (is_new() && getI_FixedAsset_ID() > 0)
			{
				MIFixedAsset ifa = getI_FixedAsset(false);
				if (ifa != null)
				{
					ifa.setI_IsImported(true);
					ifa.setA_Asset_ID(getA_Asset_ID());
					ifa.saveEx(get_TrxName());
				}
			}
		}
		//
		// Manual
		else if (A_SOURCETYPE_Manual.equals(sourceType) && isProcessed())
		{
		  // nothing to do
		 log.fine("Nothing to do");
		}
	}
	
	/**
	 * Check/Create ASI for Product (if any). If there is no product, no ASI will be created
	 */
	private void checkCreateASI() 
	{
		MProduct product = MProduct.get(getCtx(), getM_Product_ID());
		// Check/Create ASI:
		MAttributeSetInstance asi = null;
		if (product != null && getM_AttributeSetInstance_ID() == 0)
		{
			asi = new MAttributeSetInstance(getCtx(), 0, get_TrxName());
			asi.setAD_Org_ID(0);
			asi.setM_AttributeSet_ID(product.getM_AttributeSet_ID());
			asi.saveEx();
			setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
		}
	}
	
	/**
	 * Creating/Updating asset product
	 * @param isReversal
	 */
	private void updateA_Asset_Product(boolean isReversal)
	{
		// Skip if no product
		if (getM_Product_ID() <= 0)
		{
			return;
		}
		//
		MAssetProduct assetProduct = MAssetProduct.getCreate(getCtx(),
										getA_Asset_ID(), getM_Product_ID(), getM_AttributeSetInstance_ID(),
										get_TrxName());
		//
		if (assetProduct.get_ID() <= 0 && isReversal)
		{
			log.warning("No Product found "+this+" [IGNORE]");
			return;
		}
		//
		BigDecimal adjQty = getA_QTY_Current();
		
		if (isReversal)
		{
			adjQty = adjQty.negate();
		}
		//
		assetProduct.addA_Qty_Current(adjQty); //Goodwill
		
		MAsset asset = getA_Asset(false);
		assetProduct.addA_Qty_Current(asset.getA_QTY_Current()); //Goodwill - Asset Product is the sum of addition product qty
		assetProduct.setM_Locator_ID(getM_Locator_ID());
		
		//Goodwill - Activated Asset must have quantity at least 1
		if (asset.getA_Asset_Status().equals(MAsset.A_ASSET_STATUS_Activated) 
				&& assetProduct.getA_QTY_Current().compareTo(BigDecimal.ZERO) <= 0
				&& !isReversal)
		{
			assetProduct.addA_Qty_Current(BigDecimal.ONE);
		}//
		
		assetProduct.setAD_Org_ID(getA_Asset().getAD_Org_ID()); 
		assetProduct.saveEx();
		if (isA_CreateAsset())
		{		
			//MAsset asset = getA_Asset(false);
			assetProduct.updateAsset(asset);
			
			// Goodwill - setting asset's quantities for the first time
			asset.setA_QTY_Current(getA_QTY_Current());
			asset.setA_QTY_Original(getA_QTY_Current());
			asset.setQty(getA_QTY_Current());
			// End - setting
			
			asset.saveEx();
		}
		else
		{
			// Goodwill - setting asset's quantities
			asset.setA_QTY_Current(assetProduct.getA_QTY_Current());
			asset.setQty(assetProduct.getA_QTY_Current());
			asset.saveEx();
			// End - setting
		}
	}
	
	public boolean hasZeroValues()
	{
		return
				getDeltaUseLifeYears() <= 0 
				//|| getDeltaUseLifeYears_F() <= 0								//commented by Goodwill
				//|| getDeltaUseLifeYears() != getDeltaUseLifeYears_F() 
				|| getAssetValueAmt().signum() <= 0
		;
	}

	
	public File createPDF ()
	{
		return null;
	}	//	createPDF
	
	
	public String getDocumentInfo()
	{
		return getDocumentNo() + " / " + getDateDoc();
	}	//	getDocumentInfo

	
	public String toString()
	{
		StringBuilder sb = new StringBuilder("@DocumentNo@: " + getDocumentNo());
		MAsset asset = getA_Asset(false);
		if(asset != null && asset.get_ID() > 0)
		{
			sb.append(", @A_Asset_ID@: ").append(asset.getName());
		}
		return sb.toString();
	}	// toString
	
	// Goodwill - check the completed addition, instead of the non-void one
	private void setA_CreateAsset()
	{
		if (DOCSTATUS_Voided.equals(getDocStatus()))
		{
			setA_CreateAsset(false);
		}
		else
		{
			final String sql = "SELECT COUNT(*) FROM A_Asset_Addition WHERE A_Asset_ID=? AND A_CreateAsset='Y'"
							//+" AND DocStatus<>'VO' AND IsActive='Y'"
							+" AND DocStatus IN ('CO','CL') AND IsActive='Y'"      // Goodwill
							+" AND A_Asset_Addition_ID<>?";
			
			int cnt = DB.getSQLValueEx(null, sql, getA_Asset_ID(), getA_Asset_Addition_ID());
			MAsset asset = new MAsset(getCtx(), getA_Asset_ID(), get_TrxName());   // Goodwill
			
			//Goodwill - If Capital type, create asset
			if (A_CAPVSEXP_Capital.equals(getA_CapvsExp()))
				setA_CreateAsset(true);
			//Goodwill - If Capital type and Asset_ID exist, don't create asset
			if (A_CAPVSEXP_Capital.equals(getA_CapvsExp()) && getA_Asset_ID() > 0)
				setA_CreateAsset(false);
			//Goodwill - If Expense type, don't create asset
			if (A_CAPVSEXP_Expense.equals(getA_CapvsExp()))
				setA_CreateAsset(false);
			
			if (isA_CreateAsset())
			{
				// A_CreateAsset='Y' must be unique
				if (cnt >= 1)
				{
					setA_CreateAsset(false);
				}
				else if (cnt == 0)
				{
					setA_CreateAsset(true);
					
					// Goodwill - Check if Asset is Activated, don't Create Asset
					if (asset.getA_Asset_Status().equals(MAsset.A_ASSET_STATUS_Activated)
							&& !asset.getAssetActivationDate().equals(getDateDoc()))
					{
						setA_CreateAsset(false);
					}
				}
			}
			else
			{
				// Succesfull creation of Asset
				if (cnt == 0)
				{
					// Goodwill - Check if Asset is Activated, don't Create Asset
					if (asset.getA_Asset_Status().equals(MAsset.A_ASSET_STATUS_Activated)
							&& !asset.getAssetActivationDate().equals(getDateDoc()))
					{
						setA_CreateAsset(false);
					} // End - Check
					else
						setA_CreateAsset(true);
				}
			}
		}
	}
	
	private void setC_DocType_ID() 
	{
		StringBuilder sql = new StringBuilder ("SELECT C_DocType_ID FROM C_DocType ")
			.append( "WHERE AD_Client_ID=? AND AD_Org_ID IN (0,").append( getAD_Org_ID())
			.append( ") AND DocBaseType='FAA' ")
			.append( "ORDER BY AD_Org_ID DESC, IsDefault DESC");
		int C_DocType_ID = DB.getSQLValue(null, sql.toString(), getAD_Client_ID());
		if (C_DocType_ID <= 0)
			log.severe ("No FAA found for AD_Client_ID=" + getAD_Client_ID ());
		else
		{
			if (log.isLoggable(Level.FINE)) log.fine("(PO) - " + C_DocType_ID);
			set_ValueOfColumn("C_DocType_ID", C_DocType_ID);
		}
	
	}	
}	//	MAssetAddition
