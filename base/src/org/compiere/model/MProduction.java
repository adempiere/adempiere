/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.model;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.PeriodClosedException;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentReversalEnabled;
import org.compiere.process.DocumentEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;
import org.eevolution.service.dsl.ProcessBuilder;

/**
 * Contributed from Adaxa
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/648">
 * 		@see FR [ 648 ] Add Support to document Action on Standard Production window</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/887">
 * 		@see FR [ 887 ] System Config reversal invoice DocNo</a>	
 * @author https://github.com/homebeaver
 *    @see <a href="https://github.com/adempiere/adempiere/issues/1782">
 *    [ 1782 ] check only if MovementDate is in the past</a>  
 */
public class MProduction extends X_M_Production implements DocAction , DocumentReversalEnabled {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8141439503963346402L;

	/** Log								*/
	private static CLogger		log = CLogger.getCLogger (MProduction.class);
	
	private int lineno;
	
	/** Parent Batch	*/
	private MProductionBatch parent = null;
	
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
	
	/**
	 * Create production from order line
	 * @param line
	 */
	public MProduction(MOrderLine line) {
		super( line.getCtx(), 0, line.get_TrxName());
		MProduct product = MProduct.get(getCtx(), line.getM_Product_ID());
		setAD_Client_ID(line.getAD_Client_ID());
		setAD_Org_ID(line.getAD_Org_ID());
		setMovementDate(line.getDatePromised());
		setM_Product_ID(line.getM_Product_ID());
		setProductionQty(line.getQtyOrdered().subtract(line.getQtyDelivered()));
		setDatePromised(line.getDatePromised());
		int locator = product.getM_Locator_ID();
		if(locator == 0){
			locator = MWarehouse.get(getCtx(), line.getM_Warehouse_ID()).getDefaultLocator().get_ID();
		}
		//	Set Locator
		setM_Locator_ID(locator);
		//	Reference to order
		setC_OrderLine_ID(line.getC_OrderLine_ID());
	}
	
	/**
	 * Create production from batch
	 * @param batch
	 */
	public MProduction(MProductionBatch batch) {
		super(batch.getCtx(), 0, batch.get_TrxName());
		setFromBatch(batch);
	}

	/**
	 * Get Parent
	 * reload
	 * @return
	 */
	public MProductionBatch getParent(boolean reload) {
		//	Cache
		if(parent == null
				&& getM_ProductionBatch_ID() != 0) {
			parent = (MProductionBatch) getM_ProductionBatch();
		}
		//	Return
		return parent;
	}
	
	/**
	 * Get Parent without reload
	 * @return
	 */
	public MProductionBatch getParent() {
		return getParent(false);
	}
	
	/**
	 * Process Document from Production Batch
	 */
	private String processFromBatch() {
		if(getParent() == null)
			return "@M_ProductionBatch_ID@ @NotFound@";
		//	
		if (parent.isProcessed()) {
			if (parent.getDocStatus().equals(MProductionBatch.DOCACTION_Close))
				return "@M_ProductionBatch_ID@ @closed@";
			else if (parent.getDocStatus().equals(MProductionBatch.DOCACTION_Void))
				return "@M_ProductionBatch_ID@ @Voided@";
			else if (parent.getDocStatus().equals(MProductionBatch.DOCACTION_Complete)
					&& parent.getQtyCompleted().compareTo(parent.getTargetQty()) > 0)
				return "@QtyCompleted@ > @TargetQty@";
		} else {
			return "@M_ProductionBatch_ID@ @Unprocessed@";//	TODO: Missing message translation
		}
		
		StringBuilder errors = new StringBuilder();
		MProductionLine[] lines = getLines_OrderedByIsEndProduct();

		for (MProductionLine line : lines) {
			MProduct product = (MProduct) line.getM_Product();
			MAttributeSet as = product.getAttributeSet();
			if (as != null) {
				if ((as.isMandatoryAlways() ||
						(as.isSerNo() && as.isSerNoMandatory()) ||
						(as.isLot() && as.isLotMandatory())) 
						&& line.getM_AttributeSetInstance_ID() == 0) {
					errors.append("@M_AttributeSet_ID@ @IsMandatory@ (@Line@ #" + line.getLine() +
							", @M_Product_ID@ = " + product.getValue() + ")").append(Env.NL);
				}
			}
			// Check if Production only possible when sufficient quantity in stock
			if (isMustBeStocked() && !line.isEndProduct() && getReversal_ID()==0 
					 && product.isStocked() && line.isActive()) {
				String MMPolicy = product.getMMPolicy();
				MStorage[] storages = MStorage.getWarehouse (getCtx(), 0,
						product.getM_Product_ID(), line.getM_AttributeSetInstance_ID(),
					null, MClient.MMPOLICY_FiFo.equals(MMPolicy), true, line.getM_Locator_ID(), get_TrxName());
				
				//	Qty On Hand
				BigDecimal qtyOnHand = Env.ZERO;
				if(storages != null
						&& storages.length > 0) {
					for(MStorage storage : storages) {
						qtyOnHand = qtyOnHand.add(storage.getQtyOnHand());
					}
				}
				//	Validate
				log.config("qtyOnHand=" + qtyOnHand + " movementQty=" + line.getMovementQty() + " "+product);
				if (qtyOnHand.add(line.getMovementQty()).compareTo(Env.ZERO)<0) {
					errors.append(Msg.translate(getCtx(), "NotEnoughStocked") + " " + product.getName()
					+ ": " + Msg.translate(getCtx(), "QtyAvailable") + " " + qtyOnHand.toString() + ".\n" );
				}
			}
		}
		//	Validate error lines
		if (errors.length() > 0) {
			return errors.toString();
		}
		//	
		MMovement[] moves = parent.getMovements(true);
		for (MMovement move : moves) {
			if (!move.isProcessed()) {
				errors.append("@M_Movement_ID@ =" + move.getDocumentNo() + " @Unprocessed@");
			}
		}
		//	Validate error lines
		if (errors.length() > 0) {
			return errors.toString();
		}
		//	Create Transaction
		errors.append(createTransactionFromLines(lines));
		if (errors.length() > 0) {
			return errors.toString();
		}
		//	Validate error lines
		if (errors.length() > 0) {
			return errors.toString();
		}
		//	Update Header
		updateQtyHeader(false);
		//	
		parent.createComplementProduction();
		//	
		return null;
	}
	
	/**
	 * Update Order Batch
	 * @param isVoid
	 */
	private void updateQtyHeader(boolean isVoid) {
		//	For No Parent
		if(getParent() == null)
			return;
		//	
		BigDecimal productionQty = getProductionQty();
		if(isVoid) {
			productionQty = productionQty.negate();
		}
		//	Set to Parent
		parent.setQtyReserved(parent.getQtyReserved().subtract(productionQty));
		parent.setQtyCompleted(parent.getQtyCompleted().add(productionQty));
		parent.saveEx(get_TrxName());
	}
	
	/**
	 * Process Document from Production Plan
	 */
	private String processFromPlan() {
		return null;
	}
	
	/**
	 * Create Transaction
	 * @param lines
	 * @return
	 */
	private String createTransactionFromLines(MProductionLine[] lines) {
		StringBuilder errors = new StringBuilder();
		String error = "";
		for (MProductionLine pLine:lines) {
			if (!pLine.createTransaction(pLine))				
				error = "@M_Transaction_ID@ @no@ @Created@";
			if (!Util.isEmpty(error)) {
				errors.append(error);
			} else {
				pLine.setQtyReserved(pLine.getQtyReserved().add(pLine.getMovementQty()));
				pLine.setProcessed(true);
				pLine.saveEx(get_TrxName());
			}
		}
		return errors.toString();
	}
	
	@Override
	public void setProcessed(boolean Processed) {
		super.setProcessed(Processed);
		//	Processed Plan
		for(MProductionPlan plan : getProductionPlan()) {
			plan.setProcessed(Processed);
			plan.save();
		}
		//	Processed Line
		for(MProductionLine line : getLines()) {
			line.setProcessed(Processed);
			line.saveEx();
		}
	}

	/**
	 * Get Production Plan Lines
	 * @return
	 */
	public List<MProductionPlan> getProductionPlan() {
		String whereClause= "M_Production_ID=? ";
		return new Query(getCtx(), MProductionPlan.Table_Name, whereClause, get_TrxName())
										.setParameters(getM_Production_ID())
										.setOrderBy(MProductionPlan.COLUMNNAME_Line)
										.list();
	}
	
	/**
	 * Get Lines
	 * @return
	 */
	public MProductionLine[] getLines() {
		String whereClause= "M_Production_ID=? ";
		List<MProductionLine> list = new Query(getCtx(), MProductionLine.Table_Name, whereClause, get_TrxName())
										.setParameters(getM_Production_ID())
										.setOrderBy(MProductionLine.COLUMNNAME_Line)
										.list();
		return list.toArray(new MProductionLine[list.size()]);
	}
	
	/**
	 * Get lines order by Is End Product
	 * @return
	 */
	public MProductionLine[] getLines_OrderedByIsEndProduct() {
		String whereClause= "M_Production_ID=? ";
		List<MProductionLine> list = new Query(getCtx(), MProductionLine.Table_Name, whereClause, get_TrxName())
										.setParameters(getM_Production_ID())
										.setOrderBy(MProductionLine.COLUMNNAME_IsEndProduct + "," + MProductionLine.COLUMNNAME_Line)
										.list();
		return list.toArray(new MProductionLine[list.size()]);
	}
	
	/**
	 * Delete lines of production
	 * @param trxName
	 */
	private void deleteLines() {
		for (MProductionLine line : getLines()) {
			line.deleteEx(true);
		}

	}// deleteLines
	
	@Override
	protected boolean beforeDelete() {
		//	
		if(isProcessed())
			return false;
		//	
		deleteLines();
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
	public String completeIt()  {
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
		//	Validate
		m_processMsg = validateHeader();
		//	For production created from Batch
		if(getM_ProductionBatch_ID() != 0) {
			m_processMsg = processFromBatch();
		} else {
			m_processMsg = processFromPlan();
		}
		//	Validate process message
		if(m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
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

	@Override
	public String prepareIt()
	{
		if (log.isLoggable(Level.INFO))
			log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		// Std Period open? @see https://github.com/adempiere/adempiere/issues/1782
		// and https://github.com/adempiere/adempiere/pull/1826#issuecomment-412618464
		MPeriod.testPeriodOpen(getCtx(), getMovementDate(), MDocType.DOCBASETYPE_ManufacturingOrder, getAD_Org_ID());
		//	
		m_processMsg = validateEndProduct(getM_Product_ID());
		if (!Util.isEmpty(m_processMsg))
		{
			return DocAction.STATUS_Invalid;
		}
		//	Validate Header
		m_processMsg = validateHeader();
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		//	Create Lines
		if (!isReversal())
			createLines();
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}

	/**
	 * Validate Production Header
	 * @return
	 */
	private String validateHeader() {
		if(getM_Product_ID() != 0) {
			if(getProductionQty() == null
					|| getProductionQty().equals(Env.ZERO))
				return "@ProductionQty@ = 0";
			else if(getM_Locator_ID() == 0)
				return "@M_Locator_ID@ @NotFound@";
		}
		//	Default Return
		return null;
	}
	
	/**
	 * Validate End Product
	 * @param M_Product_ID
	 * @return
	 */
	private String validateEndProduct(int M_Product_ID)
	{
		String msg = isBOM(M_Product_ID);
		if (!Util.isEmpty(msg))
			return msg;
		//	
		return null;
	}

	/**
	 * Verify if is BOM
	 * @param M_Product_ID
	 * @return
	 */
	private String isBOM(int M_Product_ID) {
		MProduct product = MProduct.get(getCtx(), M_Product_ID);
		//	Verify if is BOM
		if(!product.isBOM()) {
			return "@NotBOM@ [" + product.getValue() + "-" + product.getName() + "]";	//	TODO: Translation for message (Attempt to create product line for Non Bill Of Materials)
		}
		//	
		if(MPPProductBOM.getDefault(product, get_TrxName()) == null) {
			return "@NotBOMProducts@";	//	TODO: Translation for message (Attempt to create product line for Bill Of Materials with no BOM Products)
		}
		//	
		return null;
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
			deleteLines();
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

	/**
	 * Reverse it
	 * @param isAccrual
	 * @return
	 */
	public MProduction reverseIt(boolean isAccrual)
	{
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		Optional<Timestamp> loginDateOptional = Optional.of(Env.getContextAsDate(getCtx(),"#Date"));
		Timestamp reversalDate =  isAccrual ? loginDateOptional.orElse(currentDate) : getMovementDate();
		MPeriod.testPeriodOpen(getCtx(), reversalDate , getC_DocType_ID(), getAD_Org_ID());
		MProduction reversal = null;
		reversal = copyFrom(reversalDate);
		MDocType docType = MDocType.get(getCtx(), getC_DocType_ID());
		if(docType.isCopyDocNoOnReversal()) {
			reversal.setDocumentNo(getDocumentNo() + Msg.getMsg(getCtx(), "^"));
		}

		StringBuilder msgadd = new StringBuilder("{->").append(getDocumentNo()).append(")");
		reversal.addDescription(msgadd.toString());
		reversal.setReversal_ID(getM_Production_ID());
		reversal.saveEx(get_TrxName());

		if (!reversal.processIt(DocAction.ACTION_Complete))
		{
			m_processMsg = "@Reversal@ @Error@ " + reversal.getProcessMsg();
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

		MProductionBatch pBatch = getParent();
		pBatch.saveEx(get_TrxName());

		return reversal;
	}

	/**
	 * Copy from other
	 * @param reversalDate
	 * @return
	 */
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
		to.setPosted(false);
		to.setProcessing(false);
		to.setProcessed(false);
		to.setReversal(true);
		to.setProductionQty(getProductionQty().negate());
		to.saveEx();
		for (MProductionLine fline : getLines())
		{
			MProductionLine tline = new MProductionLine(to);
			PO.copyValues(fline, tline, getAD_Client_ID(), getAD_Org_ID());
			tline.setM_Production_ID(to.getM_Production_ID());
			tline.setMovementQty(fline.getMovementQty().negate());
			tline.setPlannedQty(fline.getPlannedQty().negate());
			tline.setQtyUsed(fline.getQtyUsed().negate());
			tline.setReversalLine_ID(fline.getM_ProductionLine_ID());
			tline.saveEx();
			//We need to copy MA
			if (tline.getM_AttributeSetInstance_ID() == 0)
			{
				MProductionLineMA mas[] = MProductionLineMA.get(getCtx(), fline.getM_ProductionLine_ID(), get_TrxName());
				for (int j = 0; j < mas.length; j++)
				{
					MProductionLineMA ma = new MProductionLineMA (tline,
							mas[j].getM_AttributeSetInstance_ID(),
							mas[j].getMovementQty().negate());
					ma.saveEx();
				}
			}
		}
		return to;
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

		MProduction reversal = reverseIt(false);
		if (reversal == null)
			return false;
		
		// After reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (m_processMsg != null)
			return false;

		m_processMsg = reversal.getDocumentNo();

		return true;
	}

	@Override
	public boolean reverseAccrualIt()
	{
		if (log.isLoggable(Level.INFO))
			log.info(toString());
		// Before reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;

		MProduction reversal = reverseIt(true);
		if (reversal == null)
			return false;

		// After reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;

		m_processMsg = reversal.getDocumentNo();

		return true;
	}

	/**
	 * Create Movement
	 * @return
	 * @throws Exception
	 */
	public MMovement createMovement() throws Exception
	{
		MProductionLine[] lines = getLines();
		if (lines.length == 0) {
			//nothing to create;
			return null;
		}
		MProductionBatch batch = getParent();
		MMovement move = new MMovement(getCtx(), 0, get_TrxName());
		MWarehouse wh = (MWarehouse) getM_Locator().getM_Warehouse();
		boolean allowSameLocator = wh.get_ValueAsBoolean("IsAllowSameLocatorMove");
		move.setClientOrg(this);
		move.setDescription(Msg.parseTranslation(getCtx(), 
				"@Created@ @from@ @M_ProductionBatch_ID@ " + batch.getDocumentNo()));
		//	
		move.set_Value("M_Warehouse_ID", wh.getM_Warehouse_ID());
		move.set_Value("M_Warehouse_To_ID", wh.getM_Warehouse_ID());
		//set fields
		move.set_Value("M_ProductionBatch_ID", batch.getM_ProductionBatch_ID());
		//	Save Movement
		move.saveEx();
		//	
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
				throw new AdempiereUserError("CannotUseSameLocator");
			}
			
			MMovementLine moveLine = new MMovementLine(move);
			moveLine.setM_LocatorTo_ID(getM_Locator_ID());
			moveLine.setM_Locator_ID(line.getM_Product().getM_Locator_ID());
			moveLine.setM_Product_ID(line.getM_Product_ID());
			moveLine.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
			moveLine.setM_AttributeSetInstanceTo_ID(line.getM_AttributeSetInstance_ID());
			moveLine.setMovementQty(line.getMovementQty().negate()); //skip UOM check
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
		// For reversals, do not change anything
		if(getReversal_ID()!=0)
			return true;
		
		//	For Document Type
		if(getC_DocType_ID() == 0) {
			int C_DocType_ID = MDocType.getDocType(MDocType.DOCBASETYPE_MaterialProduction, getAD_Org_ID());
			if (C_DocType_ID == 0)
				C_DocType_ID = MDocType.getDocType(MDocType.DOCBASETYPE_MaterialProduction);
			if (C_DocType_ID == 0)
				throw new AdempiereException("@C_DocType_ID@ @NotFound@");
			setC_DocType_ID(C_DocType_ID);
		}
		
		//	For Production Batch
		if(is_ValueChanged(COLUMNNAME_M_ProductionBatch_ID)) {
			if(!isProcessed()
					&& !isReversal()) {
				setFromBatch(getParent(true));
			}
		}
		//	For quantity
		if(is_ValueChanged(COLUMNNAME_ProductionQty)) {
			if(!isProcessed()
					&& isCreated()
					&& !isReversal()) {
				setIsCreated(false);
			}
		}
		//	
		return true;
	}
	
	/**
	 * Set values from parent
	 */
	private void setFromBatch(MProductionBatch batch) {
		setM_ProductionBatch_ID(batch.getM_ProductionBatch_ID());
		setClientOrg(batch);
		setM_Product_ID(batch.getM_Product_ID());
		setDatePromised(batch.getMovementDate());
		setMovementDate(batch.getMovementDate());
		setM_Locator_ID(batch.getM_Locator_ID());
		setProductionQty(batch.getTargetQty().subtract(batch.getQtyCompleted()));
		//	Add Reference
		setC_Activity_ID(batch.getC_Activity_ID());
		setC_Project_ID(batch.getC_Project_ID());
		setC_Campaign_ID(batch.getC_Campaign_ID());
		setUser1_ID(batch.getUser1_ID());
		setUser2_ID(batch.getUser2_ID());
		//	Set Description
		if(getDescription() == null) {
			setDescription(Msg.parseTranslation(getCtx(), 
					"@Created@ @from@ @M_ProductionBatch_ID@ " + batch.getDocumentNo()));
		}
		log.info("M_Production_ID=" + getM_Production_ID() + " created");
	}

	/** Reversal Flag		*/
	private boolean m_reversal = false;
	
	/**
	 * 	Set Reversal
	 *	@param reversal reversal
	 */
	public void setReversal(boolean reversal)
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
	
	/**
	 * Check Material Plicity
	 * @param pLine
	 * @param MovementType
	 */
	public void checkMaterialPolicy(MProductionLine pLine, String MovementType)
	{
		int no = pLine.deleteMA();
		if (no > 0)
			log.config("Delete old #" + no);

		//	Incoming Trx
		boolean needSave = false;
		MProduct product = pLine.getProduct();

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
			MAttributeSet.validateAttributeSetInstanceMandatory(product, I_M_ProductionLine.Table_ID , false , pLine.getM_AttributeSetInstance_ID());
			//deliver using new asi
			MAttributeSetInstance asi = MAttributeSetInstance.create(getCtx(), product, get_TrxName());
			int M_AttributeSetInstance_ID = asi.getM_AttributeSetInstance_ID();
			MProductionLineMA ma = new MProductionLineMA (pLine, M_AttributeSetInstance_ID, qtyToDeliver);
			ma.saveEx();
			log.fine("##: " + ma);
		}

		if (needSave)
		{
			pLine.saveEx();
		}
	}	//	checkMaterialPolicy

	/**
	 * Create Lines from batch
	 */
	private void createLines() {
		//	If it already created then ignore
		if (isCreated())
			return;
		isBOM(getM_Product_ID());
		//	Recalculate
		
		recalculate();
		// Check batch having production planned Qty.
		BigDecimal cntQty = Env.ZERO;
		MProductionBatch pBatch = (MProductionBatch) getM_ProductionBatch();
		for (MProduction p : pBatch.getProductionArray(true)) {
			if (p.getM_Production_ID() != getM_Production_ID())
				cntQty = cntQty.add(p.getProductionQty());
		}

		BigDecimal maxPlanQty = pBatch.getTargetQty().subtract(cntQty);
		if (getProductionQty().compareTo(maxPlanQty) > 0) {
			DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Quantity);
			throw new AdempiereException("@Total@ @ProductionQty@ > @TargetQty@ [@TargetQty@ = " + format.format(pBatch.getTargetQty())
					+ " @Total@ @ProductionQty@ = " + format.format(cntQty)
					+ " @Max@ = " + format.format(maxPlanQty));
		}
		//	Delete before process
		deleteLines();
		createLines(isMustBeStocked());
		//	Set flag created
		setIsCreated(true);
		saveEx();
		//jobrian - update Production Batch
		MProductionBatch batch = getParent();
		if(batch != null) {
			batch.setQtyOrdered(getProductionQty());
			batch.saveEx();
		}
	}
	
	/**
	 * Create Lines
	 * @param mustBeStocked
	 * @return
	 */
	public String createLines(boolean mustBeStocked)  {
		
		lineno = 100;
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
				}
		}
		else
		{

			MProductionLine line = new MProductionLine(this);
			line.setLine(lineno);
			line.setM_Product_ID(finishedProduct.getM_Product_ID());
			line.setM_Locator_ID(getM_Locator_ID());
			line.setMovementQty(getProductionQty());
			line.setPlannedQty(getProductionQty());
			line.saveEx();
		}
		//
		error = createBOM(mustBeStocked, finishedProduct, getProductionQty());
		//	
		return error;
	}
	
	/**
	 * Create Lines from finished product
	 * @param mustBeStocked
	 * @param finishedProduct
	 * @param requiredQty
	 * @return
	 */
	private String createBOM(boolean mustBeStocked, MProduct finishedProduct, BigDecimal requiredQty)  {
		int defaultLocator = 0;		
		MPPProductBOM bom = MPPProductBOM.getDefault(finishedProduct, get_TrxName());
		for (MPPProductBOMLine bLine : bom.getLines())
		{			
			lineno = lineno + 10;
			BigDecimal BOMMovementQty = getQty(bLine,true).multiply(requiredQty);	
			int precision = bLine.getPrecision();
			if (BOMMovementQty.scale() > precision)
			{
				BOMMovementQty = BOMMovementQty.setScale(precision, RoundingMode.HALF_UP);
			}
			MProduct bomproduct = bLine.getProduct();
			if ( bomproduct.isBOM() && bomproduct.isPhantom() )
			{
				createBOM(mustBeStocked, bomproduct, BOMMovementQty);
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
				} // for available storages

			}			
		}
		return "";
	}
	
	/**
	 * Recalculate quantity will be produced
	 * @return
	 */
	private String recalculate() {
		MProduct product = MProduct.get(getCtx(), getM_Product_ID());
		MAcctSchema as = MClient.get(getCtx()).getAcctSchema();
		MCostType ct = MCostType.getByMethodCosting(as, as.getCostingMethod());
		String costingLevel = product.getCostingLevel(as);
		if (!as.getM_CostType().getCostingMethod().equals(MCostType.COSTINGMETHOD_StandardCosting))
			return "";
		int AD_Org_ID = costingLevel.equals(MAcctSchema.COSTINGLEVEL_Organization)?getAD_Org_ID():0;
		int M_Warehouse_ID = costingLevel.equals(MAcctSchema.COSTINGLEVEL_Warehouse)?getM_Locator().getM_Warehouse_ID():0;
		if (!as.getM_CostType().getCostingMethod().equals(MCostType.COSTINGMETHOD_StandardCosting))
			return "";
		ProcessInfo processInfo = ProcessBuilder.create(getCtx())
				//.process(RollupBillOfMaterial.getProcessId())
				.process(53062)
				.withRecordId(MProduction.Table_ID, getM_Product_ID())
				.withParameter("C_AcctSchema_ID", as.getC_AcctSchema_ID())
				.withParameter("S_Resource_ID", as.getC_AcctSchema_ID())
				.withParameter("", as.getCostingMethod())
				.withParameter("M_CostType_ID", ct.getM_CostType_ID())
				.withParameter("ADOrg_ID", AD_Org_ID)
				.withParameter("M_Warehouse_ID", M_Warehouse_ID)
				.withParameter("CostingMethod", as.getCostingMethod())
				.withoutTransactionClose()
				.execute(get_TrxName());
		if (processInfo.isError())
			throw new AdempiereException(processInfo.getSummary());
		//	Log
		log.info(processInfo.getSummary());
		return "";
	}
	
	public BigDecimal getQty(MPPProductBOMLine bLine, boolean includeScrapQty)
	{
		int precision = bLine.getPrecision();
		BigDecimal qty;
		if (bLine.isQtyPercentage())
		{
			precision += 2;
			qty = bLine.getQtyBatch().divide(Env.ONEHUNDRED, precision, RoundingMode.HALF_UP);
		}
		else
		{
			qty = bLine.getQtyBOM();
		}
		//
		if (includeScrapQty)
		{
			BigDecimal scrapDec = bLine.getScrap().divide(Env.ONEHUNDRED, 12, BigDecimal.ROUND_UP);
			qty = qty.divide(Env.ONE.subtract(scrapDec), precision, BigDecimal.ROUND_HALF_UP);
		}
		return qty;
	}

}
