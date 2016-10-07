package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.PeriodClosedException;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;

public class MProduction extends X_M_Production implements DocAction {

	/**
	 * 
	 */
	/** Log								*/
	@SuppressWarnings("unused")
	private static CLogger		m_log = CLogger.getCLogger (MProduction.class);
	private static final long serialVersionUID = 1L;
	private int lineno;
	private int count;

	public static final String	SQL_GET_DOCTYPE			= "SELECT C_DocType_ID FROM C_DocType WHERE Name LIKE ? AND AD_Client_ID = ?";
	public static final String	SQL_GET_BOM_INFO		= "SELECT M_ProductBom_ID, BOMQty FROM M_Product_BOM	WHERE M_Product_ID=? ORDER BY Line";
	
	private int					docType_PlannedOrder	= 0;
	private MPPProductBOM bom = null;
	
	/**	Process Message 			*/
	private String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean		m_justPrepared = false;
	
	
	public MProduction(Properties ctx, int M_Production_ID, String trxName) {
		super(ctx, M_Production_ID, trxName);
		if (M_Production_ID == 0) {
			setDocStatus(DOCSTATUS_Drafted);
			setDocAction (DOCACTION_Prepare);
		}
	}

	public MProduction(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public MProduction( MOrderLine line ) {
		super( line.getCtx(), 0, line.get_TrxName());
		setAD_Client_ID(line.getAD_Client_ID());
		setAD_Org_ID(line.getAD_Org_ID());
		setMovementDate( line.getDatePromised() );
	}

	public String automaticProduction(MInOutLine line) throws Exception{
		MInOut move = new MInOut(line.getCtx(),line.getM_InOut_ID(), line.get_TrxName());
		
		setAD_Client_ID(line.getAD_Client_ID());
		setAD_Org_ID(line.getAD_Org_ID());
		setC_Activity_ID(move.getC_Activity_ID());
		setMovementDate(move.getMovementDate());
		setName(move.getDocumentNo());
		setM_Locator_ID(line.getM_Locator_ID());
		setM_Product_ID(line.getM_Product_ID());
		setProductionQty(line.getMovementQty());
		saveEx();
		createLines(true);
		try {
			completeIt(getMovementDate(), true);
		} catch (AdempiereSystemError e) {
			e.printStackTrace();
		}
		
		return ("@M_Production_ID@ = " + getName());	
	}
	
	@Override
	public String completeIt() 
	{
		// Re-Check
		if (!m_justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		String batchStatus = checkProductionBatchDocStatus();
		if (!Util.isEmpty(batchStatus, true))
			throw new AdempiereException(batchStatus);
		
		StringBuilder errors = new StringBuilder();
		int processed = 0;

		MProductionLine[] lines = getLines_OrderedByIsEndProduct();
		
		for (MProductionLine line : lines )
		{
				MProduct product = (MProduct) line.getM_Product();
				MAttributeSet as = product.getAttributeSet();
				if ( as != null )
				{
					if ( (as.isMandatoryAlways() ||
							(as.isSerNo() && as.isSerNoMandatory()) ||
							(as.isLot() && as.isLotMandatory())) 
							&& line.getM_AttributeSetInstance_ID() == 0 )
					{
						errors.append("@M_AttributeSet_ID@ @IsMandatory@ (@Line@ #" + line.getLine() +
						", @M_Product_ID@=" + product.getValue() + ")\n");
					}
				}
		}
		if (errors.length() > 0)
		{
			m_processMsg = errors.toString();
			return DocAction.STATUS_Invalid;
		}
		MProductionBatch batch = (MProductionBatch) getM_Production_Batch();
		MMovement[] moves = batch.getMMovements(true);
		for (MMovement move : moves) {
			if (!move.isProcessed()) {
				errors.append("Inventory Move=" + move.getDocumentNo() + " is not complete.");
			}
		}		
		errors.append(processLines(lines));
		if (errors.length() > 0)
		{
			m_processMsg = errors.toString();
			return DocAction.STATUS_Invalid;
		}
		processed = processed + lines.length;

		batch.setQtyReserved(batch.getQtyReserved().subtract(getProductionQty()));
		batch.setQtyCompleted(batch.getQtyCompleted().add(getProductionQty()));
		batch.saveEx(get_TrxName());
		
		//create another production to complete the target qty
		MProduction newMPO = batch.createProductionHeader(false);
		if (newMPO != null) {
			String error =newMPO.createLines(false) ;
			if (error != "")
			{
				m_processMsg = error;
				return DocAction.STATUS_Invalid;
			}				
			if (newMPO.getLines().length>0) {
				newMPO.setIsCreated(true);
				newMPO.saveEx();
			}
		}
		// User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}

		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}
	
	private Object processLines(MProductionLine[] lines)
	{
		StringBuilder errors = new StringBuilder();
		String error = "";
		for (MProductionLine pLine:lines)
		{
			if (!pLine.createTransaction(pLine))				
				error = "No transaction created";
			if (!Util.isEmpty(error))
			{
				errors.append(error);
			}
			else
			{
				pLine.setQtyReserved(pLine.getQtyReserved().add(pLine.getMovementQty()));
				pLine.setProcessed(true);
				pLine.saveEx(get_TrxName());
			}
		}
		return errors.toString();
	}
	
	public String completeIt(Timestamp movedate, boolean mustBeStocked) throws Exception {
		
		int processed = 0;
		setMovementDate(movedate);
		MProductionLine[] lines = getLines_OrderedByIsEndProduct();
		StringBuffer errors = new StringBuffer();
		for ( MProductionLine pLine:lines) {
			errors.append(pLine.createTransaction(pLine));
			pLine.setProcessed( true );
			pLine.saveEx(get_TrxName());
			processed++;
		}
		if ( errors.toString().compareTo("") != 0 ) {
			log.log(Level.WARNING, errors.toString() );
			throw new AdempiereSystemError(errors.toString());
		}
		
		setProcessed(true);
		
		saveEx(get_TrxName());
		return processed + " production lines were processed";

	}


	public MProductionLine[] getLines() {
		String whereClause= "M_Production_ID=? ";
		List<MProductionLine> list = new Query(getCtx(), MProductionLine.Table_Name, whereClause, get_TrxName())
										.setParameters(getM_Production_ID())
										.setOrderBy(MProductionLine.COLUMNNAME_Line)
										.list();
		return list.toArray(new MProductionLine[list.size()]);
		}
	
	
	public MProductionLine[] getLines_OrderedByIsEndProduct() {
		String whereClause= "M_Production_ID=? ";
		List<MProductionLine> list = new Query(getCtx(), MProductionLine.Table_Name, whereClause, get_TrxName())
										.setParameters(getM_Production_ID())
										.setOrderBy(MProductionLine.COLUMNNAME_IsEndProduct + "," + MProductionLine.COLUMNNAME_Line)
										.list();
		return list.toArray(new MProductionLine[list.size()]);}
	
	public void deleteLines(String trxName) {

		for (MProductionLine line : getLines())
		{
			line.deleteEx(true);
		}

	}// deleteLines

	public String createLines( Boolean mustBeStocked)  {
		
		lineno = 100;

		count = 0;
		String error ="";
		// product to be produced	
		MProduct finishedProduct = new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
		
		MAttributeSet as = finishedProduct.getAttributeSet();
		if ( as != null && as.isSerNo())
		{
				for ( int i = 0; i < getProductionQty().intValue(); i++)
				{
					if ( i > 0 )
						lineno = lineno + 10;
					MProductionLine line = new MProductionLine( this );
					line.setLine( lineno );
					line.setM_Product_ID( finishedProduct.get_ID() );
					line.setM_Locator_ID( getM_Locator_ID() );
					line.setMovementQty( Env.ONE );
					line.setPlannedQty( Env.ONE );
					line.saveEx();
					count++;
				}
		}
		else
		{

			MProductionLine line = new MProductionLine( this );
			line.setLine( lineno );
			line.setM_Product_ID( finishedProduct.get_ID() );
			line.setM_Locator_ID( getM_Locator_ID() );
			line.setMovementQty( getProductionQty());
			line.setPlannedQty(getProductionQty());
			line.saveEx();
			count++;
		}

		//
		
		error = createLines(mustBeStocked, finishedProduct, getProductionQty());
		
		return error;
	}

	private String createLines(boolean mustBeStocked, MProduct finishedProduct, BigDecimal requiredQty)  {
		String error = "";
		int defaultLocator = 0;		
		MLocator finishedLocator = MLocator.get(getCtx(), getM_Locator_ID());		
		int M_Warehouse_ID = finishedLocator.getM_Warehouse_ID();		
		int asi = 0;
		MPPProductBOM bom = MPPProductBOM.getDefault(finishedProduct, get_TrxName());
		for (MPPProductBOMLine bLine : bom.getLines())
		{
			
			lineno = lineno + 10;
			//int BOMProduct_ID = rs.getInt(1);
			//BigDecimal BOMQty = rs.getBigDecimal(2);
			BigDecimal BOMMovementQty = bLine.getQty(true).multiply(requiredQty);			
			MProduct bomproduct = bLine.getProduct();
			if ( bomproduct.isBOM() && bomproduct.isPhantom() )
			{
				createLines(mustBeStocked, bomproduct, BOMMovementQty);
			}
			else
			{
				defaultLocator = bomproduct.getM_Locator_ID();
				if ( defaultLocator == 0 )
					defaultLocator = getM_Locator_ID();

				if (!bomproduct.isStocked())
				{					
					MProductionLine BOMLine = null;
					BOMLine = new MProductionLine( this );
					BOMLine.setLine( lineno );
					BOMLine.setM_Product_ID(  bomproduct.getM_Product_ID()  );
					BOMLine.setM_Locator_ID( defaultLocator );  
					BOMLine.setQtyUsed(BOMMovementQty );
					BOMLine.setPlannedQty( BOMMovementQty );
					BOMLine.setMovementQty(BOMMovementQty.negate());
					BOMLine.saveEx(get_TrxName());

					lineno = lineno + 10;
					count++;					
				}
				else if (BOMMovementQty.signum() == 0) 
				{
					MProductionLine BOMLine = null;
					BOMLine = new MProductionLine( this );
					BOMLine.setLine( lineno );
					BOMLine.setM_Product_ID( bomproduct.getM_Product_ID() );
					BOMLine.setM_Locator_ID( defaultLocator );  
					BOMLine.setQtyUsed( BOMMovementQty );
					BOMLine.setPlannedQty( BOMMovementQty );
					BOMLine.saveEx(get_TrxName());

					lineno = lineno + 10;
					count++;
				}
				else
				{					
					MProductionLine BOMLine = null;
					BOMLine = new MProductionLine( this );
					BOMLine.setLine( lineno );
					BOMLine.setM_Product_ID(  bomproduct.getM_Product_ID()  );
					BOMLine.setM_Locator_ID( defaultLocator );  
					BOMLine.setPlannedQty( BOMMovementQty );
					BOMLine.setQtyReserved(BOMMovementQty);
					BOMLine.setMovementQty(BOMMovementQty.negate());
					BOMLine.saveEx(get_TrxName());
					lineno = lineno + 10;
					count++;					
				} // for available storages

			}			
		}
		return "";
	}
	
	@Override
	protected boolean beforeDelete() {
		
		if (isProcessed())
			return false;
		
		deleteLines(get_TrxName());
		return true;
	}

	@Override
	public boolean processIt(String processAction)
	{
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(processAction, getDocAction());
	}

	@Override
	public boolean unlockIt()
	{
		if (log.isLoggable(Level.INFO))
			log.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	}

	@Override
	public boolean invalidateIt()
	{
		if (log.isLoggable(Level.INFO))
			log.info(toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	}

	@Override
	public String prepareIt()
	{
		
		String batchStatus = checkProductionBatchDocStatus();
		if (!Util.isEmpty(batchStatus, true))
			throw new AdempiereException(batchStatus);

		if (log.isLoggable(Level.INFO))
			log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		// Std Period open?
		MPeriod.testPeriodOpen(getCtx(), getMovementDate(), MDocType.DOCBASETYPE_ManufacturingOrder, getAD_Org_ID());

		if (!isCreated())
		{
			m_processMsg = "Not created";
			return DocAction.STATUS_Invalid;
		}

		{
			m_processMsg = validateEndProduct(getM_Product_ID());
			if (!Util.isEmpty(m_processMsg))
			{
				return DocAction.STATUS_Invalid;
			}
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}

	/**
	 * Check document status of production batch
	 */
	public String checkProductionBatchDocStatus()
	{
		MProductionBatch pBatch = (MProductionBatch) getM_Production_Batch();
		if (pBatch.isProcessed())
		{
			if (pBatch.getDocStatus().equals(MProductionBatch.DOCACTION_Close))
				return "Can not process, Production Batch is closed";
			else if (pBatch.getDocStatus().equals(MProductionBatch.DOCACTION_Void))
				return "Can not process, Production Batch is voided";
			else if (pBatch.getDocStatus().equals(MProductionBatch.DOCACTION_Complete))
				if (pBatch.getQtyCompleted().compareTo(pBatch.getTargetQty()) > 0)
					return "Already processed target qty of production batch";
		}
		else
			return "Please first confirm/complete the production batch";

		return null;
	}

	protected String validateEndProduct(int M_Product_ID)
	{
		String msg = isBom(M_Product_ID);
		if (!Util.isEmpty(msg))
			return msg;
/*
		try
		{
			if (!costsOK(M_Product_ID))
			{
				msg = "Excessive difference in standard costs";
				if (MSysConfig.getBooleanValue("MFG_ValidateCostsDifferenceOnCreate", false, getAD_Client_ID()))
				{
					return msg;
				}
				else
				{
					log.warning(msg);
				}
			}
		}
		catch (AdempiereUserError e)
		{
			throw new AdempiereException(e);
		}*/

		return null;
	}

	protected String isBom(int M_Product_ID)
	{
		String bom = DB.getSQLValueString(get_TrxName(), "SELECT isbom FROM M_Product WHERE M_Product_ID = ?",
				M_Product_ID);
		if ("N".compareTo(bom) == 0)
		{
			return "Attempt to create product line for Non Bill Of Materials";
		}
		int materials = DB.getSQLValue(get_TrxName(),
				"SELECT count(pp_Product_BOM_ID) FROM PP_Product_BOM WHERE M_Product_ID = ?", M_Product_ID);
		if (materials == 0)
		{
			return "Attempt to create product line for Bill Of Materials with no BOM Products";
		}
		return null;
	}

	protected boolean costsOK(int M_Product_ID) throws AdempiereUserError
	{
		
		MProduct product = MProduct.get(getCtx(), M_Product_ID);
		String costingMethod = product.getCostingMethod(MClient.get(getCtx()).getAcctSchema());
		if (!costingMethod.equals(MAcctSchema.COSTINGMETHOD_StandardCosting))
			return true;
		// will not work if non-standard costing is used
		if (MAcctSchema.COSTINGMETHOD_StandardCosting.equals(costingMethod))
		{
			String sql = "SELECT ABS(((cc.currentcostprice-(SELECT SUM(c.currentcostprice*bom.bomqty)"
					+ " FROM m_cost c"
					+ " INNER JOIN pp_product_bom bom ON (c.m_product_id=bom.pp_product_id)"
					+ " INNER JOIN m_costelement ce ON (c.m_costelement_id = ce.m_costelement_id AND ce.costingmethod = 'S')"
					+ " WHERE bom.m_product_id = pp.m_product_id)" + " )/cc.currentcostprice))" + " FROM m_product pp"
					+ " INNER JOIN m_cost cc on (cc.m_product_id=pp.m_product_id)"
					+ " INNER JOIN m_costelement ce ON (cc.m_costelement_id=ce.m_costelement_id)"
					+ " WHERE cc.currentcostprice > 0 AND pp.M_Product_ID = ?" + " AND ce.costingmethod='S'";

			BigDecimal costPercentageDiff = DB.getSQLValueBD(get_TrxName(), sql, M_Product_ID);

			if (costPercentageDiff == null)
			{
				costPercentageDiff = Env.ZERO;
				String msg = "Could not retrieve costs";
				if (MSysConfig.getBooleanValue("MFG_ValidateCostsOnCreate", false, getAD_Client_ID()))
				{
					throw new AdempiereUserError(msg);
				}
				else
				{
					log.warning(msg);
				}
			}

			if ((costPercentageDiff.compareTo(new BigDecimal("0.005"))) < 0)
				return true;

			return false;
		}
		return true;
	}

	@Override
	public boolean approveIt()
	{
		return true;
	}

	@Override
	public boolean rejectIt()
	{
		return true;
	}

	@Override
	public boolean voidIt()
	{

		if (log.isLoggable(Level.INFO))
			log.info(toString());
		// Before Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_VOID);
		if (m_processMsg != null)
			return false;

		if (DOCSTATUS_Closed.equals(getDocStatus()) || DOCSTATUS_Reversed.equals(getDocStatus())
				|| DOCSTATUS_Voided.equals(getDocStatus()))
		{
			m_processMsg = "Document Closed: " + getDocStatus();
			setDocAction(DOCACTION_None);
			return false;
		}

		// Not Processed
		if (DOCSTATUS_Drafted.equals(getDocStatus()) || DOCSTATUS_Invalid.equals(getDocStatus())
				|| DOCSTATUS_Approved.equals(getDocStatus()) || DOCSTATUS_NotApproved.equals(getDocStatus()))
		{
			setIsCreated(false);
			deleteLines(get_TrxName());
			setProductionQty(BigDecimal.ZERO);
		}
		else
		{
			boolean accrual = false;
			try
			{
				MPeriod.testPeriodOpen(getCtx(), getMovementDate(), MDocType.DOCBASETYPE_ManufacturingOrder, getAD_Org_ID());
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
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_VOID);
		if (m_processMsg != null)
			return false;

		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	}

	@Override
	public boolean closeIt()
	{
		if (log.isLoggable(Level.INFO))
			log.info(toString());
		// Before Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
			return false;

		setProcessed(true);
		setDocAction(DOCACTION_None);

		// After Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
			return false;
		
		return true;
	}

	@Override
	public boolean reverseCorrectIt()
	{
		if (log.isLoggable(Level.INFO))
			log.info(toString());
		// Before reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (m_processMsg != null)
			return false;

		MProduction reversal = new MProduction(getCtx(), 0, get_TrxName());
		copyValues(this, reversal, getAD_Client_ID(), getAD_Org_ID());
		reversal.setDocStatus(DOCSTATUS_Drafted);
		reversal.setDocAction(DOCACTION_Complete);
		reversal.setPosted(false);
		reversal.setProcessed(false);
		reversal.setProductionQty(getProductionQty().negate());		reversal.addDescription("{->" + getDocumentNo() + ")");
		//FR1948157
		reversal.setReversal_ID(getM_Production_ID());
		reversal.setReversal(true);
		reversal.saveEx();

		for (MProductionLine oLine:getLines())
		{
			MProductionLine rLine = new MProductionLine(reversal);
			copyValues(oLine, rLine, oLine.getAD_Client_ID(), oLine.getAD_Org_ID());
			rLine.setMovementQty(oLine.getMovementQty().negate());
			rLine.setPlannedQty(oLine.getPlannedQty().negate());
			rLine.setQtyUsed(oLine.getQtyUsed().negate());
			rLine.setM_Production_ID(reversal.getM_Production_ID());
			rLine.saveEx();
			//AZ Goodwill
			// store original (voided/reversed) document line
			rLine.setReversalLine_ID(oLine.getM_ProductionLine_ID());//
			
			rLine.saveEx();
			//We need to copy MA
			if (rLine.getM_AttributeSetInstance_ID() == 0)
			{
				MProductionLineMA mas[] = MProductionLineMA.get(getCtx(), oLine.getM_ProductionLine_ID(), get_TrxName());
				for (int j = 0; j < mas.length; j++)
				{
					MProductionLineMA ma = new MProductionLineMA (rLine, 
							mas[j].getM_AttributeSetInstance_ID(),
							mas[j].getMovementQty().negate());
					ma.saveEx();
				}
			}
		}

		//
		if (!reversal.processIt(DocAction.ACTION_Complete))
		{
			m_processMsg = "Reversal ERROR: " + reversal.getProcessMsg();
			return false;
		}
		reversal.closeIt();
		reversal.setDocStatus(DOCSTATUS_Reversed);
		reversal.setDocAction(DOCACTION_None);
		reversal.saveEx();
		m_processMsg = reversal.getDocumentNo();

		//	Update Reversed (this)
		addDescription("(" + reversal.getDocumentNo() + "<-)");
		// After reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (m_processMsg != null)
			return false;

		m_processMsg = reversal.getDocumentNo();

		return true;
	}

	private MProduction reverse(boolean accrual)
	{
		Timestamp reversalDate = accrual ? Env.getContextAsDate(getCtx(), "#Date") : getMovementDate();
		if (reversalDate == null)
		{
			reversalDate = new Timestamp(System.currentTimeMillis());
		}

		MPeriod.testPeriodOpen(getCtx(), reversalDate, MDocType.DOCBASETYPE_ManufacturingOrder, getAD_Org_ID());
		MProduction reversal = null;
		reversal = copyFrom(reversalDate);

		StringBuilder msgadd = new StringBuilder("{->").append(getDocumentNo()).append(")");
		reversal.addDescription(msgadd.toString());
		reversal.setReversal_ID(getM_Production_ID());
		reversal.saveEx(get_TrxName());

		if (!reversal.processIt(DocAction.ACTION_Complete))
		{
			m_processMsg = "Reversal ERROR: " + reversal.getProcessMsg();
			return null;
		}

		reversal.closeIt();
		reversal.setProcessing(false);
		reversal.setDocStatus(DOCSTATUS_Reversed);
		reversal.setDocAction(DOCACTION_None);
		reversal.saveEx(get_TrxName());

		msgadd = new StringBuilder("(").append(reversal.getDocumentNo()).append("<-)");
		addDescription(msgadd.toString());

		setProcessed(true);
		setReversal_ID(reversal.getM_Production_ID());
		setDocStatus(DOCSTATUS_Reversed); // may come from void
		setDocAction(DOCACTION_None);
		
		MProductionBatch pBatch = (MProductionBatch) getM_Production_Batch();
		//pBatch.reserveStock((MProduct)reversal.getM_Product(), getProductionQty(), getM_Production_ID());
		//pBatch.orderedStock(reversal.getM_Product(), getProductionQty());
		pBatch.saveEx(get_TrxName());
		
		return reversal;
	}

	private MProduction copyFrom(Timestamp reversalDate)
	{
		MProduction to = new MProduction(getCtx(), 0, get_TrxName());
		PO.copyValues(this, to, getAD_Client_ID(), getAD_Org_ID());

		to.set_ValueNoCheck("DocumentNo", null);
		//
		to.setDocStatus(DOCSTATUS_Drafted); // Draft
		to.setDocAction(DOCACTION_Complete);
		to.setMovementDate(reversalDate);
		to.setIsComplete("N");
		to.setIsCreated(true);
		to.setProcessing(false);
		to.setProcessed(false);
		to.setProductionQty(getProductionQty().negate());
		to.saveEx();
		MProductionLine[] flines = getLines();
		for (MProductionLine fline : flines)
		{
			MProductionLine tline = new MProductionLine(to);
			PO.copyValues(fline, tline, getAD_Client_ID(), getAD_Org_ID());
			tline.setM_Production_ID(to.getM_Production_ID());
			tline.setMovementQty(fline.getMovementQty().negate());
			tline.setPlannedQty(fline.getPlannedQty().negate());
			tline.setQtyUsed(fline.getQtyUsed().negate());
			tline.saveEx();
		}

		return to;
	}

	public MMovement createMovement() throws Exception
	{
		MProductionLine[] lines = getLines();
		if (lines.length == 0) {
			//nothing to create;
			return null;
		}
		I_M_Production_Batch batch = getM_Production_Batch();
		MMovement move = new MMovement(getCtx(), 0, get_TrxName());
		MWarehouse wh = (MWarehouse) getM_Locator().getM_Warehouse();
		boolean allowSameLocator = wh.get_ValueAsBoolean("IsAllowSameLocatorMove");
		move.setClientOrg(this);
		move.setDescription("Material move created from Production Batch#" + batch.getDocumentNo());
		try {
			move.set_Value("M_Warehouse_ID", wh.getM_Warehouse_ID());
			move.set_Value("M_Warehouse_To_ID", wh.getM_Warehouse_ID());
		} catch (Exception e) {
			//in case columns are not defined
		}
		//set fields
		move.set_Value("M_Production_Batch_ID", batch.getM_Production_Batch_ID());
		
		move.saveEx();
		log.fine("Movement Documentno=" + move.getDocumentNo() + " created for Production Batch=" + batch.getDocumentNo() );
		
		for (MProductionLine line : lines) {
			if (line.isEndProduct() || line.getM_Product().isBOM() || !line.getM_Product().isStocked()) {
				log.fine("End Product. No need to move." + line.getM_Product().getValue());
				continue;
			}
			else if (Env.ZERO.compareTo(line.getMovementQty()) == 0)  {
				log.fine("No quantity to to move." + line.getM_Product().getValue());
				continue;
			}
			if (getM_Locator_ID() == line.getM_Product().getM_Locator_ID() && !allowSameLocator) {
				throw new AdempiereUserError("Cannot use same locator. Please Allow Same Locator in Warehouse Settings");
			}
			
			MMovementLine moveLine = new MMovementLine(move);
			moveLine.setM_LocatorTo_ID(getM_Locator_ID());
			moveLine.setM_Locator_ID(line.getM_Product().getM_Locator_ID());
			moveLine.setM_Product_ID(line.getM_Product_ID());
			moveLine.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
			moveLine.setM_AttributeSetInstanceTo_ID(line.getM_AttributeSetInstance_ID());
			//moveLine.setMovementQty(line.getMovementQty().negate());
			moveLine.set_Value("MovementQty", line.getMovementQty().negate()); //skip UOM check
			if (moveLine.getMovementQty().compareTo(line.getMovementQty().negate()) != 0) {
				throw new AdempiereException("Please adjust standard precision for " + line.getM_Product().getC_UOM().getName() );
			}
			moveLine.saveEx();
		}	
		return move;
	}
	
	/**
	 * Add to Description
	 * 
	 * @param description text
	 */
	public void addDescription(String description)
	{
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
		{
			StringBuilder msgd = new StringBuilder(desc).append(" | ").append(description);
			setDescription(msgd.toString());
		}
	} // addDescription

	@Override
	public boolean reverseAccrualIt()
	{
		if (log.isLoggable(Level.INFO))
			log.info(toString());
		// Before reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;

		MProduction reversal = reverse(true);
		if (reversal == null)
			return false;

		// After reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;

		m_processMsg = reversal.getDocumentNo();

		return true;
	}

	@Override
	public boolean reActivateIt()
	{
		return false;
	}

	@Override
	public String getSummary()
	{
		return getDocumentNo();
	}

	@Override
	public String getDocumentInfo()
	{
		return getDocumentNo();
	}

	@Override
	public File createPDF()
	{
		return null;
	}

	@Override
	public String getProcessMsg()
	{
		return m_processMsg;
	}

	@Override
	public int getDoc_User_ID()
	{
		return getCreatedBy();
	}

	@Override
	public int getC_Currency_ID()
	{
		return MClient.get(getCtx()).getC_Currency_ID();
	}

	@Override
	public BigDecimal getApprovalAmt()
	{
		return BigDecimal.ZERO;
	}
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		
		if (newRecord && getM_Production_Batch_ID() == 0) {
			MProductionBatch batch = new MProductionBatch(getCtx(), 0, get_TrxName());
			batch.setClientOrg(this);
			batch.setMovementDate(getMovementDate());
			batch.setTargetQty(getProductionQty());
			batch.setQtyOrdered(getProductionQty());
			batch.setDescription(getDescription());
			batch.setC_DocType_ID(getC_DocType_ID());
			batch.setQtyCompleted(Env.ZERO);
			batch.setM_Product_ID(getM_Product_ID());
			batch.setM_Locator_ID(getM_Locator_ID());
			batch.setDocumentNo(getDocumentNo());
			batch.setProcessed(false);
			batch.setCountOrder(1);
			batch.saveEx();
			
			if (batch.get_ID() == 0) {
				return false;
			}
			setM_Production_Batch_ID(batch.get_ID());
			setDocumentNo(batch.getDocumentNo() + "-01");
			return true;
		}

		return true;
	}

	public String costsOK()  {
		String costingMethod = "";
		MAcctSchema[] acctschemas= MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID());
		for (MAcctSchema as: acctschemas)
		{
			costingMethod = as.getCostingMethod();
			continue;			
		}		
		if (!costingMethod.equals(MAcctSchema.COSTINGMETHOD_StandardCosting))
			return "";
		String whereClause = "m_productionline_ID in (select m_productionline_ID from m_productionline where m_production_ID = ?)";
		BigDecimal costPercentageDiff = new Query(getCtx(), MCostDetail.Table_Name, whereClause, get_TrxName())
				.setOnlyActiveRecords(true)
				.setParameters(getM_Production_ID())
				.aggregate("CostAmt", Query.AGGREGATE_SUM);
				
		if (costPercentageDiff == null)
		{
			return "Could not retrieve costs";
		}
		
		MClientInfo ci = MClientInfo.get(getCtx(), getAD_Client_ID());
		BigDecimal percentDiffMax =  (BigDecimal) ci.get_Value("PercentDiffMax");
		if (percentDiffMax == null) {
			percentDiffMax = new BigDecimal(0.005);
		}
		
		if ( (costPercentageDiff.compareTo(percentDiffMax))< 0 )
			return "";		
		return  "Excessive difference in standard costs";
	}

	/** Reversal Flag		*/
	private boolean m_reversal = false;
	
	/**
	 * 	Set Reversal
	 *	@param reversal reversal
	 */
	private void setReversal(boolean reversal)
	{
		m_reversal = reversal;
	}	//	setReversal
	/**
	 * 	Is Reversal
	 *	@return reversal
	 */
	public boolean isReversal()
	{
		return m_reversal;
	}	//	isReversal
	

	public void checkMaterialPolicy(MProductionLine pLine, String MovementType)
	{
		int no = pLine.deleteMA();
		if (no > 0)
			log.config("Delete old #" + no);

		//	Incoming Trx
		boolean inTrx = MovementType.charAt(1) == '+';	
		boolean needSave = false;
		MProduct product = pLine.getProduct();
		if (inTrx)
		{
		}

		//	Attribute Set Instance
		//  Create an  Attribute Set Instance to any receipt FIFO/LIFO
		//if (product != null && line.getM_AttributeSetInstance_ID() == 0)
		else
		{
			// Create consume the Attribute Set Instance using policy FIFO/LIFO
			{
				String MMPolicy = product.getMMPolicy();
				Timestamp minGuaranteeDate = getMovementDate();
				MStorage[] storages = MStorage.getWarehouse(getCtx(), pLine.getM_Locator().getM_Warehouse_ID(), pLine.getM_Product_ID(), pLine.getM_AttributeSetInstance_ID(),
						minGuaranteeDate, MClient.MMPOLICY_FiFo.equals(MMPolicy), true, pLine.getM_Locator_ID(), get_TrxName());
				BigDecimal qtyToDeliver = pLine.getMovementQty().negate();
				for (MStorage storage: storages)
				{
					if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0)
					{
						MProductionLineMA ma = new MProductionLineMA (pLine,
								storage.getM_AttributeSetInstance_ID(),
								qtyToDeliver);
						ma.saveEx();
						qtyToDeliver = Env.ZERO;
					}
					else
					{
						MProductionLineMA ma = new MProductionLineMA (pLine,
								storage.getM_AttributeSetInstance_ID(),
								storage.getQtyOnHand());
						ma.saveEx();
						qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand());
						log.fine( ma + ", QtyToDeliver=" + qtyToDeliver);
					}

					if (qtyToDeliver.signum() == 0)
						break;
				}

				if (qtyToDeliver.signum() != 0)
				{
					//deliver using new asi
					MAttributeSetInstance asi = MAttributeSetInstance.create(getCtx(), product, get_TrxName());
					int M_AttributeSetInstance_ID = asi.getM_AttributeSetInstance_ID();
					MProductionLineMA ma = new MProductionLineMA (pLine, M_AttributeSetInstance_ID, qtyToDeliver);
					ma.saveEx();
					log.fine("##: " + ma);
				}
			}	//	outgoing Trx
		}	//	attributeSetInstance

		if (needSave)
		{
			pLine.saveEx();
		}
	}	//	checkMaterialPolicy

}
