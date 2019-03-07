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
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Env;

/**
 * Contributed from Adaxa
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/648">
 * 		@see FR [ 648 ] Add Support to document Action on Standard Production window</a>	
 * @author https://github.com/homebeaver
 *    @see <a href="https://github.com/adempiere/adempiere/issues/1782">
 *    [ 1782 ] prepareIt: check only if MovementDate is in the past , toString-Methoda</a>  
 */
public class MProductionBatch extends X_M_ProductionBatch implements DocAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -151106993468792872L;
	/**	Lines					*/
	private MProduction[]	m_productions = null;
	private MMovement[]	 m_moves = null;
	/** Process Message */
	private String				m_processMsg			= null;
	/** Just Prepared Flag */
	private boolean				m_justPrepared			= false;
	private MProductionBatchLine[] productionBatchLines = null;
	
	public MProductionBatch(Properties ctx, int M_ProductionBatch_ID,
			String trxName) {
		super(ctx, M_ProductionBatch_ID, trxName);
	}
	
	
	public MProductionBatch(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public boolean hasOpenOrder() {
		
	//	Query.
		return false;
	}
	
	/**
	 * Get Production like a Array
	 * @param requery
	 * @return
	 */
	public MProduction[] getProductionArray(boolean requery)
	{
		if (m_productions != null && !requery) {
			set_TrxName(m_productions, get_TrxName());
			return m_productions;
		}
		List<MProduction> list = new Query(getCtx(), I_M_Production.Table_Name, "M_ProductionBatch_ID = ?", get_TrxName())
			.setParameters(getM_ProductionBatch_ID())
			.setOrderBy(MProduction.COLUMNNAME_M_Production_ID)
			.list();
		//
		m_productions = new MProduction[list.size()];
		list.toArray(m_productions);
		return m_productions;
	}	//	getHeaders

	/**
	 * Get Movements
	 * @param requery
	 * @return
	 */
	public MMovement[] getMovements(boolean requery)
	{
		if (m_moves != null && !requery) {
			set_TrxName(m_moves, get_TrxName());
			return m_moves;
		}
		List<MMovement> list = new Query(getCtx(), I_M_Movement.Table_Name, "M_ProductionBatch_ID=?", get_TrxName())
			.setParameters(getM_ProductionBatch_ID())
			.setOrderBy(MMovement.COLUMNNAME_M_Movement_ID)
			.list();
		//
		m_moves = new MMovement[list.size()];
		list.toArray(m_moves);
		return m_moves;
	}	//	getHeaders

	/**
	 * Get Open orders
	 * @return
	 */
	public MProduction getOpenOrder() {
		MProduction[] productions = getProductionArray(true);
		for (MProduction production : productions) {
			if (!production.isProcessed()) {
				return production;
			}
		}
		return null;
	}

	@Override
	protected boolean beforeSave(boolean newRecord)
	{
		return super.beforeSave(newRecord);
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success)
	{
		if (this.is_ValueChanged(COLUMNNAME_QtyReserved))
		{
			//setReservationOnBatchLine(this.getQtyReserved());
		}

		return super.afterSave(newRecord, success);
	}
	
	@Override
	public boolean processIt(String action) throws Exception
	{
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(action, getDocAction());
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
	public boolean reverseCorrectIt()
	{
		return false;
	}

	@Override
	public boolean reverseAccrualIt()
	{
		return false;
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
	public String prepareIt()
	{
		if (log.isLoggable(Level.INFO))
			log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		// Std Period open? // homebeaver: check only if MovementDate is in the past 
		// @see https://github.com/adempiere/adempiere/issues/1782
		if(getMovementDate().before(new Timestamp(System.currentTimeMillis()))) {
			MPeriod.testPeriodOpen(getCtx(), getMovementDate(), MDocType.DOCBASETYPE_ManufacturingOrder, getAD_Org_ID());
		}

		if (getTargetQty().compareTo(Env.ZERO) == 0)
		{
			m_processMsg = "@TargetQty@ = 0";
			return DocAction.STATUS_Invalid;
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);

		return DocAction.STATUS_InProgress;
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
		
		//	Create Production
		m_processMsg = createAutomaticProduction();
		//	
		if(m_processMsg != null) {
			return DocAction.STATUS_Invalid;
		}
		// User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null) {
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		//	
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		setDocStatus(DOCSTATUS_Completed);
		return DocAction.STATUS_Completed;
	}
	
	/**
	 * Create Production
	 * @return
	 */
	public String createAutomaticProduction() {
		//	Validate if is allow create automatic production
		if(!isAutoProduction())
			return null;
		// Production Order Processes
		MProduction[] headers = getProductionArray(true);
		MProduction header = null;
		if (headers.length == 0) {
			header = new MProduction(this);
			header.setDocAction(DOCACTION_Prepare);
			header.saveEx();
			header.processIt(DocAction.ACTION_Prepare);
		} else if (headers.length == 1) {
			header = headers[0];
			if (getTargetQty().compareTo(header.getProductionQty()) == 0) {
				if (!header.isCreated()) {
					header.setDocAction(DOCACTION_Prepare);
					header.processIt(DocAction.ACTION_Prepare);
				}
			} else {
				//	
				header.setProductionQty(getTargetQty());
				header.setDocAction(DOCACTION_Prepare);
				header.processIt(DocAction.ACTION_Prepare);
			}
		} else {
			return "ProductionBatchNotOne";
		}
		//	
		header.saveEx(get_TrxName());
		//	Return Error
		return null;
	}
	
	/**
	 * Create Production Header from it
	 * @return
	 */
	public MProduction createComplementProduction() {
		//	Validate if is allow create automatic production
		if(!isAutoProduction())
			return null;
		MProduction[] productions = getProductionArray(true);
		BigDecimal qtyOrder = getTargetQty();
		BigDecimal qtyCompleted = BigDecimal.ZERO;
		int orderCount = 1;
		MProduction newProdOrder = null;
		
		for (MProduction production : productions) {
			qtyOrder = qtyOrder.subtract(production.getProductionQty());
			qtyCompleted = qtyCompleted.add(production.getProductionQty());
			orderCount++;
		}
		setQtyOrdered(qtyOrder);
		setQtyCompleted(qtyCompleted);
		if (qtyOrder.compareTo(BigDecimal.ZERO) > 0) {
			setCountOrder(orderCount);
			newProdOrder = new MProduction(this);
			newProdOrder.setDocStatus(STATUS_Drafted);
			newProdOrder.saveEx();
			newProdOrder.processIt(DocAction.ACTION_Prepare);
		}
		//	
		return newProdOrder;
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

		// Stock Reserve or Release
		if (getQtyReserved().compareTo(Env.ZERO) != 0)
		{
			freeStock();
			//orderedStock(getM_Product(), getQtyReserved().negate());
			setQtyReserved(Env.ZERO);
		}

		setProcessed(true);
		setDocAction(DOCACTION_None);

		// After Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
			return false;
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

		for (MProduction production: getProductionArray(true))
		{
			if (!(MProduction.DOCSTATUS_Closed.equals(production.getDocStatus())
					|| MProduction.DOCSTATUS_Reversed.equals(production.getDocStatus()) || MProduction.DOCSTATUS_Voided
					.equals(production.getDocStatus())))
			{
				if ((MProduction.DOCSTATUS_Drafted.equals(production.getDocStatus())
						|| MProduction.DOCSTATUS_InProgress.equals(production.getDocStatus()) || MProduction.DOCSTATUS_Invalid
						.equals(production.getDocStatus())))
					//reserveStock((MProduct)getM_Product(), getTargetQty(), production.getM_Production_ID());
				{
					if (!production.voidIt())
					{
						m_processMsg = "Document Not Voided: " + production.getDocumentNo();
						return false;
					}
					production.saveEx(get_TrxName());
				}
			}
		}
		
		MProductionBatch pBatch = new MProductionBatch(getCtx(), get_ID(), get_TrxName());

		if (pBatch.getQtyReserved() != null && pBatch.getQtyReserved().compareTo(Env.ZERO) > 0)
		{
			if (!reserveStock((MProduct)getM_Product(), pBatch.getQtyReserved().negate(),0))
			{
				m_processMsg = "Issue while releasing reserved stock";
				return false;
			}
			setQtyReserved(Env.ZERO);

		// After Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_VOID);
		if (m_processMsg != null)
			return false;
		}
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
		
	}

	/**
	 * (un)Reserving stock of components
	 * 
	 * @param fProduct
	 * @param qty
	 * @return
	 */
	private boolean reserveStock(MProduct fProduct, BigDecimal qty, int M_Production_ID)
	{		
		String whereClause = "EXISTS(SELECT 1 FROM M_Production "
				+ "WHERE M_Production_ID = M_ProductionLine.M_Production_ID "
				+ "AND M_ProductionBatch_ID =?)";
		if (M_Production_ID !=0)
			whereClause = whereClause + " AND M_Production_ID = " + M_Production_ID;
		List<MProductionLine> list= new Query(getCtx(), MProductionLine.Table_Name, whereClause, get_TrxName())
				.setParameters(getM_ProductionBatch_ID())
				.list();
		for (MProductionLine pLine:list)
		{	
			//	Check/set WH/Org
			//	Binding
			if (pLine.isEndProduct())
				continue;
			BigDecimal target = pLine.getMovementQty();
			BigDecimal difference = (qty.signum()==-1 && getDocAction().equals(MProductionBatch.DOCACTION_Close))
					|| getDocAction().equals(MProductionBatch.DOCACTION_Void)? target
					:target.negate();
			if (difference.signum() == 0)
			{
				continue;
			}
			//	Check Product - Stocked and Item
			MProduct product = pLine.getProduct();
			if (product != null) 
			{
				if (product.isStocked())
				{
					BigDecimal reserved = difference;
					int M_Locator_ID = 0; 
					//	Get Locator to reserve
					if (pLine.getM_AttributeSetInstance_ID() != 0)	//	Get existing Location
						M_Locator_ID = MStorage.getM_Locator_ID (pLine.getM_Locator().getM_Warehouse_ID(), 
								pLine.getM_Product_ID(), pLine.getM_AttributeSetInstance_ID(), 
							pLine.getMovementQty(), get_TrxName());
					//	Get default Location
					if (M_Locator_ID == 0)
					{
						// try to take default locator for product first
						// if it is from the selected warehouse
						MWarehouse wh = MWarehouse.get(getCtx(), pLine.getM_Locator().getM_Warehouse_ID());
						M_Locator_ID = product.getM_Locator_ID();
						if (M_Locator_ID!=0) {
							MLocator locator = new MLocator(getCtx(), product.getM_Locator_ID(), get_TrxName());
							//product has default locator defined but is not from the order warehouse
							if(locator.getM_Warehouse_ID()!=wh.get_ID()) {
								M_Locator_ID = wh.getDefaultLocator().getM_Locator_ID();
							}
						} else {
							M_Locator_ID = wh.getDefaultLocator().getM_Locator_ID();
						}
					}
					//	Update Storage
					if (!MStorage.add(getCtx(), pLine.getM_Locator().getM_Warehouse_ID(), M_Locator_ID, 
							pLine.getM_Product_ID(), 
							0, pLine.getM_AttributeSetInstance_ID(),
						Env.ZERO, reserved, Env.ZERO, get_TrxName()))
						return false;
					
				}	//	stockec
				//	update line
				
				if (!pLine.save(get_TrxName()))
					return false;
				//
			}	//	product
		}	//	reserve inventory
		
		return true;
	}
	
	/**
	 * Get Batch Line
	 * @param requery
	 * @return
	 */
	public MProductionBatchLine[] getProductionBatchLines(Boolean requery)
	{
		if (productionBatchLines != null && !requery) {
			set_TrxName(productionBatchLines, get_TrxName());
			return productionBatchLines;
		}
		//
		List<MProductionBatchLine> list = new Query(getCtx(), MProductionBatchLine.Table_Name, "M_ProductionBatch_ID=?", get_TrxName())
			.setParameters(get_ID())
			.list();
		productionBatchLines = list.toArray(new MProductionBatchLine[list.size()]);
		return productionBatchLines;
	}	//	getLines} // getProductionBatchLines
	
	/**
	 * Free Stock
	 */
	private void freeStock()
	{
		int reservationAttributeSetInstance_ID = 0;
		BigDecimal reservationQty = Env.ZERO;
		for (MProductionBatchLine pBatchLine: getProductionBatchLines(true))
		{
			if (pBatchLine.getQtyReserved().signum()==0)
				continue;
			if (pBatchLine.isEndProduct())
				continue;
			reservationQty = pBatchLine.getQtyReserved().negate();
			if (!MStorage.add(getCtx(), getM_Locator().getM_Warehouse_ID(),
					getM_Locator_ID(), pBatchLine.getM_Product_ID(), 0, reservationAttributeSetInstance_ID, 
					Env.ZERO, reservationQty, Env.ZERO, get_TrxName()))
				return ;
		}
	}

	public String toString() {
		MDocType mDocType = new MDocType(getCtx(), getC_DocType_ID(), get_TrxName());
		StringBuffer sb = new StringBuffer("MProductionBatch[");
		sb.append(get_ID())
			.append("-").append(mDocType.getName())
			.append(" ").append(getDocumentNo())
			.append("]");
		return sb.toString();
	}
}