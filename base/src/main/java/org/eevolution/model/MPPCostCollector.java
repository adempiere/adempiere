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
 *                 Teo Sarca, www.arhipac.ro                                  *
 *****************************************************************************/
package org.eevolution.model;


import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.*;
import org.adempiere.engine.*;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DocTypeNotFoundException;
import org.adempiere.exceptions.FillMandatoryException;
import org.adempiere.exceptions.NoVendorForProductException;
import org.compiere.model.*;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.eevolution.exceptions.ActivityProcessedException;

/**
 *	PP Cost Collector Model
 *	
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 *			<li> Original contributor of Manufacturing Standard Cost
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org 
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962 
 *  
 *  @author Teo Sarca, www.arhipac.ro 
 *  @version $Id: MPPCostCollector.java,v 1.1 2004/06/19 02:10:34 vpj-cd Exp $
 */
public class MPPCostCollector extends X_PP_Cost_Collector implements DocAction , IDocumentLine
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2329378809441860241L;

	/**
	 * 
	 */
	
	public static MPPCostCollector createVarianceCostCollector(MPPCostCollector cc,
			String CostCollectorType) {
		MPPCostCollector costCollectorVariance = new MPPCostCollector(cc.getCtx(), 0,
				cc.get_TrxName());
		MPPCostCollector.copyValues(cc, costCollectorVariance);
		costCollectorVariance.setProcessing(false);
		costCollectorVariance.setProcessed(false);
		costCollectorVariance.setDocStatus(MPPCostCollector.STATUS_Drafted);
		costCollectorVariance.setDocAction(MPPCostCollector.ACTION_Complete);
		costCollectorVariance.setCostCollectorType(CostCollectorType);
		costCollectorVariance.setDocumentNo(null); // reset
		costCollectorVariance.saveEx();
		return costCollectorVariance;
	}

	/**
	 * get Cost Collector That not was generate by inventory transaction
	 * @param productId
	 * @param dateAccount
	 * @param dateAccountTo
	 * @param trxName
	 * @return Collection the Cost Collector
	 */
	public static List<MPPCostCollector> getCostCollectorNotTransaction(
			Properties ctx,
			int productId,
			Timestamp dateAccount,
			Timestamp dateAccountTo,
			String trxName)
	{
		List<Object> params = new ArrayList();
		final StringBuffer whereClause = new StringBuffer();
		whereClause.append(MPPCostCollector.COLUMNNAME_CostCollectorType +" NOT IN ('100','110') AND ");
		if(productId > 0)
		{	
		  whereClause.append(MPPCostCollector.COLUMNNAME_M_Product_ID + "=? AND ");
		  params.add(productId);
		}	 
		if (dateAccount == null || dateAccountTo == null)
			throw new AdempiereException("@DateAcct@ @NotFound@");

		whereClause.append(MPPCostCollector.COLUMNNAME_DateAcct + ">=? AND ");
		params.add(dateAccount);

		whereClause.append(MPPCostCollector.COLUMNNAME_DateAcct + "<=?");
		params.add(dateAccountTo);
		 
		return new Query(ctx, I_PP_Cost_Collector.Table_Name, whereClause.toString() , trxName)
					.setClient_ID()
					.setParameters(params)
					.list();
								 
	}
	
    /**
     * Create & Complete Cost Collector 
     * @param order
     * @param productId
     * @param locatorId
     * @param attributeSetInstanceId
     * @param resourceId
     * @param orderBOMLineId
     * @param orderNodeId
     * @param docTypeId
     * @param costCollectorType
     * @param movementDate
     * @param qty
     * @param scrap
     * @param reject
     * @param durationSetup
     * @param duration
     * @return completed cost collector
     */
	public static MPPCostCollector createCollector (MPPOrder order,
			int productId,
			int locatorId,
			int attributeSetInstanceId,
			int resourceId,
			int orderBOMLineId,
			int orderNodeId,
			int docTypeId,
			String costCollectorType,
			Timestamp movementDate,
			BigDecimal qty,
			BigDecimal scrap,
			BigDecimal reject,
			BigDecimal durationSetup,
			BigDecimal duration
		)
	{
		MPPCostCollector cc = new MPPCostCollector(order);
		cc.setPP_Order_BOMLine_ID(orderBOMLineId);
		cc.setPP_Order_Node_ID(orderNodeId);
		cc.setC_DocType_ID(docTypeId);
		cc.setC_DocTypeTarget_ID(docTypeId);
		cc.setCostCollectorType(costCollectorType);
		cc.setDocAction(MPPCostCollector.DOCACTION_Complete);
		cc.setDocStatus(MPPCostCollector.DOCSTATUS_Drafted);
		cc.setIsActive(true);
		cc.setM_Locator_ID(locatorId);
		cc.setM_AttributeSetInstance_ID(attributeSetInstanceId);
		cc.setS_Resource_ID(resourceId);
		cc.setMovementDate(movementDate);
		cc.setDateAcct(movementDate);
		cc.setMovementQty(qty);
		cc.setScrappedQty(scrap);
		cc.setQtyReject(reject);
		cc.setSetupTimeReal(durationSetup);
		cc.setDurationReal(duration);
		cc.setPosted(false);
		cc.setProcessed(false);
		cc.setProcessing(false);
		cc.setUser1_ID(order.getUser1_ID());
		cc.setUser2_ID(order.getUser2_ID());
		cc.setUser3_ID(order.getUser3_ID());
		cc.setUser4_ID(order.getUser4_ID());
		cc.setM_Product_ID(productId);
		if(orderNodeId > 0)
		{	
			cc.setIsSubcontracting(orderNodeId);
		}
		// If this is an material issue, we should use BOM Line's UOM
		if (orderBOMLineId > 0)
		{
			cc.setC_UOM_ID(0); // we set the BOM Line UOM on beforeSave
		}
		cc.saveEx();
		if (!cc.processIt(MPPCostCollector.DOCACTION_Complete))
		{
			throw new AdempiereException(cc.getProcessMsg());
		}
		cc.saveEx();
		return cc;
	}
	
	public static void setPP_Order(I_PP_Cost_Collector cc, MPPOrder order)
	{
		cc.setPP_Order_ID(order.getPP_Order_ID());
		cc.setPP_Order_Workflow_ID(order.getMPPOrderWorkflow().get_ID());
		cc.setAD_Org_ID(order.getAD_Org_ID());
		cc.setM_Warehouse_ID(order.getM_Warehouse_ID());
		cc.setAD_OrgTrx_ID(order.getAD_OrgTrx_ID());
		cc.setC_Activity_ID(order.getC_Activity_ID());
		cc.setC_Campaign_ID(order.getC_Campaign_ID());
		cc.setC_Project_ID(order.getC_Project_ID());
		cc.setDescription(order.getDescription());
		cc.setS_Resource_ID(order.getS_Resource_ID());
		cc.setM_Product_ID(order.getM_Product_ID());
		cc.setC_UOM_ID(order.getC_UOM_ID());
		cc.setM_AttributeSetInstance_ID(order.getM_AttributeSetInstance_ID());
		cc.setMovementQty(order.getQtyOrdered());
	}
	


	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param costCollectorId id
	 */
	public MPPCostCollector(Properties ctx, int costCollectorId, String trxName)
	{
		super (ctx, costCollectorId,trxName);
		if (costCollectorId == 0)
		{
			//setC_DocType_ID(0);
			setDocStatus (DOCSTATUS_Drafted);	// DR
			setDocAction (DOCACTION_Complete);	// CO
			setMovementDate (new Timestamp(System.currentTimeMillis()));	// @#Date@
			setIsActive(true);
			setPosted (false);
			setProcessing (false);
			setProcessed (false);
		}	
	}	//	MPPCostCollector

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MPPCostCollector(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MPPCostCollector
	
	/**
	 * 	Load Constructor
	 *	@param order
	 */
	public MPPCostCollector(MPPOrder order)
	{
		this(order.getCtx(), 0 , order.get_TrxName());
		setPP_Order(this, order);
		m_order = order;	
	}	//	MPPCostCollector

	

	/**
	 * 	Add to Description
	 *	@param description text
	 */
	public void addDescription (String description)
	{
		String desc = getDescription();
		if (desc == null)
			setDescription(description);
		else
			setDescription(desc + " | " + description);
	}	//	addDescription
	
	
	public void setC_DocTypeTarget_ID(String docBaseType)
	{
		MDocType[] doc = MDocType.getOfDocBaseType(getCtx(), docBaseType);	
		if(doc == null)
		{
			throw new DocTypeNotFoundException(docBaseType, "");
		}
		else
		{	
			setC_DocTypeTarget_ID(doc[0].get_ID());
		}
	}

//	@Override
	public void setProcessed (boolean processed)
	{
		super.setProcessed (processed);
		if (get_ID() == 0)
			return;
		final String sql = "UPDATE PP_Cost_Collector SET Processed=? WHERE PP_Cost_Collector_ID=?";
		int noLine = DB.executeUpdateEx(sql, new Object[]{processed, get_ID()}, get_TrxName());
		log.fine("setProcessed - " + processed + " - Lines=" + noLine);
	}	//	setProcessed


//	@Override
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
	
	/** Manufacturing Order **/
	private MPPOrder m_order = null;
	
	/** Manufacturing Order Activity **/
	private MPPOrderNode m_orderNode = null;
	
	/** Manufacturing Order BOM Line **/
	private MPPOrderBOMLine m_bomLine = null;
	/** Actual Cost 				 **/
	private BigDecimal priceActual = Env.ZERO;

//	@Override
	public boolean unlockIt()
	{
		log.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	}	//	unlockIt

//	@Override
	public boolean invalidateIt()
	{
		log.info("invalidateIt - " + toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	}	//	invalidateIt

//	@Override
	public String prepareIt()
	{
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
		{
			return DocAction.STATUS_Invalid;
		}
		
		MPeriod.testPeriodOpen(getCtx(), getDateAcct(), getC_DocTypeTarget_ID(), getAD_Org_ID());
		//	Convert/Check DocType
		setC_DocType_ID(getC_DocTypeTarget_ID());
		
		//
		// Operation Activity
		if(isActivityControl())
		{
			MPPOrderNode activity = getPP_Order_Node();
			if(MPPOrderNode.DOCACTION_Complete.equals(activity.getDocStatus()))
			{	
				throw new ActivityProcessedException(activity);
			}
			
			if (activity.isSubcontracting())
			{
				if(MPPOrderNode.DOCSTATUS_InProgress.equals(activity.getDocStatus())
						&& MPPCostCollector.DOCSTATUS_InProgress.equals(getDocStatus()))
				{			
					return MPPOrderNode.DOCSTATUS_InProgress;
				}
				else if(MPPOrderNode.DOCSTATUS_InProgress.equals(activity.getDocStatus())
						&& MPPCostCollector.DOCSTATUS_Drafted.equals(getDocStatus()))
				{
					throw new ActivityProcessedException(activity);
				}				
				m_processMsg = createPO(activity);
				m_justPrepared = false;
				activity.setInProgress(this);
				activity.saveEx();
				return DOCSTATUS_InProgress;
			}
			
			activity.setInProgress(this);
			activity.setQtyDelivered(activity.getQtyDelivered().add(getMovementQty()));
			activity.setQtyScrap(activity.getQtyScrap().add(getScrappedQty()));
			activity.setQtyReject(activity.getQtyReject().add(getQtyReject()));
			activity.setDurationReal(activity.getDurationReal().add(getDurationReal()));
			activity.setSetupTimeReal(activity.getSetupTimeReal().add(getSetupTimeReal()));
			activity.saveEx();

			// report all activity previews to milestone activity
			if(activity.isMilestone())
			{
				MPPOrderWorkflow order_workflow = activity.getMPPOrderWorkflow();
				order_workflow.closeActivities(activity, getMovementDate(), true);
			}
		}
		// Issue
		else if (isIssue())
		{
			MProduct product = getM_Product();
			if (getM_AttributeSetInstance_ID() == 0 && product.isASIMandatory(false, getAD_Org_ID()))
			{
				throw new AdempiereException("@M_AttributeSet_ID@ @IsMandatory@ @M_Product_ID@=" + product.getValue());
			}
		}
		// Receipt
		else if (isReceipt())
		{
			MProduct product = getM_Product();
			if (getM_AttributeSetInstance_ID() == 0 && product.isASIMandatory(true,getAD_Org_ID()))
			{
				throw new AdempiereException("@M_AttributeSet_ID@ @IsMandatory@ @M_Product_ID@=" + product.getValue());
			}
		}
		
		m_justPrepared = true;
		setDocAction(DOCACTION_Complete);
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
		{
			return DocAction.STATUS_Invalid;
		}

		return DocAction.STATUS_InProgress;
	}	//	prepareIt

//	@Override
	public boolean  approveIt()
	{
		log.info("approveIt - " + toString());
		//setIsApproved(true);
		return true;
	}	//	approveIt

//	@Override
	public boolean rejectIt()
	{
		log.info("rejectIt - " + toString());
		//setIsApproved(false);
		return true;
	}	//	rejectIt

//	@Override
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
		
		//
		// Material Issue (component issue, method change variance, mix variance)
		// Material Receipt
		if(isIssue() || isReceipt())
		{
			//	Stock Movement 
			MProduct product = getM_Product();
			if (product != null	&& product.isStocked() && !isVariance())
			{
				StorageEngine.createTrasaction(
						this,
						getMovementType() , 
						getMovementDate() , 
						getMovementQty() , 
						false,											// IsReversal=false
						getM_Warehouse_ID(), 
						getPP_Order().getM_AttributeSetInstance_ID(),	// Reservation ASI
						getPP_Order().getM_Warehouse_ID(),				// Reservation Warehouse
						false											// IsSOTrx=false
						);
			}	//	stock movement
			
			if (isIssue() && !isVariance())
			{
				//	Update PP Order Line
				MPPOrderBOMLine obomline = getPP_Order_BOMLine();
				obomline.setQtyDelivered(obomline.getQtyDelivered().add(getMovementQty()));
				obomline.setQtyScrap(obomline.getQtyScrap().add(getScrappedQty()));
				obomline.setQtyReject(obomline.getQtyReject().add(getQtyReject()));  
				obomline.setDateDelivered(getMovementDate());	//	overwrite=last	

				log.fine("OrderLine - Reserved=" + obomline.getQtyReserved() + ", Delivered=" + obomline.getQtyDelivered());				
				obomline.saveEx();
				log.fine("OrderLine -> Reserved="+obomline.getQtyReserved()+", Delivered="+obomline.getQtyDelivered());
			}
			if (isReceipt())
			{
				//	Update PP Order Qtys 
				final MPPOrder order = getPP_Order();
				order.setQtyDelivered(order.getQtyDelivered().add(getMovementQty()));                
				order.setQtyScrap(order.getQtyScrap().add(getScrappedQty()));
				order.setQtyReject(order.getQtyReject().add(getQtyReject()));                
				order.setQtyReserved(order.getQtyReserved().subtract(getMovementQty()));
				
				//
				// Update PP Order Dates
				order.setDateDelivered(getMovementDate()); //	overwrite=last
				if (order.getDateStart() == null)
				{
					order.setDateStart(getDateStart());
				}
				if (order.getQtyOpen().signum() <= 0)
				{
					order.setDateFinish(getDateFinish());
				}
				order.saveEx();
			}
		}
		//
		// Activity Control
		else if(isActivityControl())
		{
			MPPOrderNode activity = getPP_Order_Node();
			if(activity.isProcessed())
			{
				throw new ActivityProcessedException(activity);
			}
			
			if(isSubcontracting())
			{	
				String whereClause = MOrderLine.COLUMNNAME_PP_Cost_Collector_ID+"=?";
				Collection<MOrderLine> olines = new Query(getCtx(), MOrderLine.Table_Name, whereClause, get_TrxName())
													.setParameters(new Object[]{get_ID()})
													.list();
				String DocStatus = MPPOrderNode.DOCSTATUS_Completed;
				StringBuffer msg = new StringBuffer("The quantity do not is complete for next Purchase Order : ");
				for (MOrderLine oline : olines)
				{
					if(oline.getQtyDelivered().compareTo(oline.getQtyOrdered()) < 0)
					{
						DocStatus = MPPOrderNode.DOCSTATUS_InProgress;
					}
					msg.append(oline.getParent().getDocumentNo()).append(",");
				}
				
				if(MPPOrderNode.DOCSTATUS_InProgress.equals(DocStatus))
				{	
					m_processMsg = msg.toString();
					return DocStatus;
				}
				setProcessed(true);
				setDocAction(MPPOrderNode.DOCACTION_Close);
				setDocStatus(MPPOrderNode.DOCSTATUS_Completed);
				activity.completeIt();
				activity.saveEx();
				m_processMsg = Msg.translate(getCtx(), "PP_Order_ID")
				+": "+ getPP_Order().getDocumentNo()
				+" "+ Msg.translate(getCtx(),"PP_Order_Node_ID")
				+": "+getPP_Order_Node().getValue();
				return DocStatus;
			}
			else
			{
				final StandardCostingMethod standardCostingMethod = (StandardCostingMethod) CostingMethodFactory.get()
						.getCostingMethod(X_M_CostType.COSTINGMETHOD_StandardCosting);

				standardCostingMethod.createActivityControl(this);
				if(activity.getQtyDelivered().compareTo(activity.getQtyRequired()) >= 0)
				{
					activity.closeIt();
					activity.saveEx();									
				}
			}
		}
		//
		// Usage Variance (material)
		else if (isCostCollectorType(COSTCOLLECTORTYPE_UsegeVariance) && getPP_Order_BOMLine_ID() > 0)
		{
			MPPOrderBOMLine orderBOMLine = getPP_Order_BOMLine();
			orderBOMLine.setQtyScrap(orderBOMLine.getQtyScrap().add(getScrappedQty()));
			orderBOMLine.setQtyReject(orderBOMLine.getQtyReject().add(getQtyReject()));
			log.fine("OrderLine - Reserved=" + orderBOMLine.getQtyReserved() + ", Delivered=" + orderBOMLine.getQtyDelivered());
			orderBOMLine.saveEx();
			log.fine("OrderLine -> Reserved=" + orderBOMLine.getQtyReserved() + ", Delivered=" + orderBOMLine.getQtyDelivered());
			final StandardCostingMethod standardCostingMethod = (StandardCostingMethod) CostingMethodFactory.get()
					.getCostingMethod(X_M_CostType.COSTINGMETHOD_StandardCosting);
			standardCostingMethod.createUsageVariances(this);
		}
		//
		// Usage Variance (resource)
		else if (isCostCollectorType(COSTCOLLECTORTYPE_UsegeVariance) && getPP_Order_Node_ID() > 0)
		{
			MPPOrderNode activity = getPP_Order_Node();
			activity.setDurationReal(activity.getDurationReal().add(getDurationReal()));
			activity.setSetupTimeReal(activity.getSetupTimeReal().add(getSetupTimeReal()));
			activity.saveEx();
			final StandardCostingMethod standardCostingMethod = (StandardCostingMethod) CostingMethodFactory.get()
					.getCostingMethod(X_M_CostType.COSTINGMETHOD_StandardCosting);
			standardCostingMethod.createActivityControl(this);
		}
		else
		{
			; // nothing
		}
		//
		//CostEngineFactory.getCostEngine(getAD_Client_ID()).createRateVariances(this);
		//CostEngineFactory.getCostEngine(getAD_Client_ID()).createMethodVariances(this);

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		//	
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		setDocStatus(DOCSTATUS_Completed);
		
		return DocAction.STATUS_Completed;
	}	//	completeIt

//	@Override
	public boolean voidIt()
	{
		return false;
	}	//	voidIt

//	@Override
	public boolean closeIt()
	{
		log.info("closeIt - " + toString());
		setDocAction(DOCACTION_None);
		return true;
	}	//	closeIt

//	@Override
	public boolean reverseCorrectIt()
	{
		return false;
	}

//	@Override
	public boolean reverseAccrualIt()
	{
		return false;
	}

//	@Override
	public boolean reActivateIt()
	{
		return false;
	}

//	@Override
	public String getSummary()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getDescription());
		return sb.toString();
	}

//	@Override
	public String getProcessMsg()
	{
		return m_processMsg;
	}

//	@Override
	public int getDoc_User_ID()
	{
		return getCreatedBy();
	}

//	@Override
	public BigDecimal getApprovalAmt()
	{
		return Env.ZERO;
	}

//	@Override
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
		ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.ORDER, getPP_Order_ID());
		if (re == null)
			return null;
		return re.getPDF(file);
	}	//	createPDF

//	@Override
	public String getDocumentInfo()
	{
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	}	//	getDocumentInfo

	@Override
	protected boolean beforeSave(boolean newRecord)
	{
		// Set default locator, if not set and we have the warehouse:
		if (getM_Locator_ID() <= 0 && getM_Warehouse_ID() > 0)
		{
			MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
			MLocator loc = wh.getDefaultLocator();
			if (loc != null)
			{
				setM_Locator_ID(loc.get_ID());
			}
		}
		//
		if (isIssue())
		{
			if (getPP_Order_BOMLine_ID() <= 0)
			{
				throw new FillMandatoryException(COLUMNNAME_PP_Order_BOMLine_ID);
			}
			// If no UOM, use the UOM from BOMLine
			if (getC_UOM_ID() <= 0)
			{
				setC_UOM_ID(getPP_Order_BOMLine().getC_UOM_ID());
			}
			// If Cost Collector UOM differs from BOM Line UOM then throw exception because this conversion is not supported yet
			if (getC_UOM_ID() != getPP_Order_BOMLine().getC_UOM_ID())
			{
				throw new AdempiereException("@PP_Cost_Collector_ID@ @C_UOM_ID@ <> @PP_Order_BOMLine_ID@ @C_UOM_ID@");
			}
		}
		//
		if (isActivityControl() && getPP_Order_Node_ID() <= 0)
		{
			throw new FillMandatoryException(COLUMNNAME_PP_Order_Node_ID);
		}
		return true;
	}

	@Override
	public MPPOrderNode getPP_Order_Node()
	{
		int node_id = getPP_Order_Node_ID();
		if (node_id <= 0)
		{
			m_orderNode = null;
			return null;
		}
		if (m_orderNode == null || m_orderNode.get_ID() != node_id)
		{
			m_orderNode = new MPPOrderNode(getCtx(), node_id, get_TrxName());
		}
		return m_orderNode;
	}

	@Override
	public MPPOrderBOMLine getPP_Order_BOMLine()
	{
		int id = getPP_Order_BOMLine_ID();
		if (id <= 0)
		{
			m_bomLine = null;
			return null;
		}
		if (m_bomLine == null || m_bomLine.get_ID() != id)
		{
			m_bomLine = new MPPOrderBOMLine(getCtx(), id, get_TrxName());
		}
		m_bomLine.set_TrxName(get_TrxName());
		return m_bomLine;
	}
	
	@Override
	public MPPOrder getPP_Order()
	{
		int id = getPP_Order_ID();
		if (id <= 0)
		{
			m_order = null;
			return null;
		}
		if (m_order == null || m_order.get_ID() != id)
		{
			m_order = new MPPOrder(getCtx(), id, get_TrxName());
		}
		return m_order;
	}
	
	/**
	 * Get Duration Base in Seconds
	 * @return duration unit in seconds
	 * @see MPPOrderWorkflow#getDurationBaseSec()
	 */
	public long getDurationBaseSec()
	{
		return getPP_Order().getMPPOrderWorkflow().getDurationBaseSec();
	}

	/**
	 * @return Activity Control Report Start Date
	 */
	public Timestamp getDateStart()
	{
		double duration = getDurationReal().doubleValue();
		if (duration != 0)
		{
			long durationMillis = (long)(getDurationReal().doubleValue() * getDurationBaseSec() * 1000.0);
			return new Timestamp(getMovementDate().getTime() - durationMillis);
		}
		else
		{
			return getMovementDate();
		}
	}
	
	/**
	 * @return Activity Control Report End Date
	 */
	public Timestamp getDateFinish()
	{
		return getMovementDate();
	}

	
	/**
	 * Create Purchase Order (in case of Subcontracting)
	 * @param activity
	 */
	private String createPO(MPPOrderNode activity)
	{
		String msg = "";
		HashMap<Integer,MOrder> orders = new HashMap<Integer,MOrder>();
		//
		String whereClause = MPPOrderNodeProduct.COLUMNNAME_PP_Order_Node_ID+"=?"
							+" AND "+MPPOrderNodeProduct.COLUMNNAME_IsSubcontracting+"=?";
		Collection<MPPOrderNodeProduct> subcontracts = new Query(getCtx(), MPPOrderNodeProduct.Table_Name, whereClause, get_TrxName())
				.setParameters(new Object[]{activity.get_ID(), true})
				.setOnlyActiveRecords(true)
				.list();
		
		for (MPPOrderNodeProduct subcontract : subcontracts)
		{
			//
			// If Product is not Purchased or is not Service, then it is not a subcontracting candidate [SKIP]
			MProduct product = MProduct.get(getCtx(), subcontract.getM_Product_ID());
			if(!product.isPurchased() || !MProduct.PRODUCTTYPE_Service.equals(product.getProductType()))
				throw new AdempiereException("The Product: " + product.getName() + " Do not is Purchase or Service Type");

			//
			// Find Vendor and Product PO data
			int C_BPartner_ID = activity.getC_BPartner_ID();
			MProductPO product_po = null;
			for (MProductPO ppo : MProductPO.getOfProduct(getCtx(), product.get_ID(), null))
			{
				if(C_BPartner_ID == ppo.getC_BPartner_ID())
				{
					C_BPartner_ID = ppo.getC_BPartner_ID();
					product_po = ppo;
					break;
				}
				if (ppo.isCurrentVendor() && ppo.getC_BPartner_ID() != 0)
				{
					C_BPartner_ID = ppo.getC_BPartner_ID();
					product_po = ppo;
					break;
				}
			}
			if(C_BPartner_ID <= 0 || product_po == null)
			{
				throw new NoVendorForProductException(product.getName());
			}
			//
			// Calculate Lead Time
			Timestamp today = new Timestamp(System.currentTimeMillis());
			Timestamp datePromised = TimeUtil.addDays(today, product_po.getDeliveryTime_Promised()); 
			//
			// Get/Create Purchase Order Header
			MOrder order = orders.get(C_BPartner_ID);
			if(order == null)
			{
				order = new MOrder(getCtx(), 0, get_TrxName());
				MBPartner vendor = MBPartner.get(getCtx(), C_BPartner_ID);
				order.setAD_Org_ID(getAD_Org_ID());
				order.setBPartner(vendor);
				order.setIsSOTrx(false);
				order.setC_DocTypeTarget_ID();
				order.setDatePromised(datePromised);
				order.setDescription(Msg.translate(getCtx(), MPPOrder.COLUMNNAME_PP_Order_ID) +":"+getPP_Order().getDocumentNo());
				order.setDocStatus(MOrder.DOCSTATUS_Drafted);
				order.setDocAction(MOrder.DOCACTION_Complete);
				order.setAD_User_ID(getAD_User_ID());
				order.setM_Warehouse_ID(getM_Warehouse_ID());
				//order.setSalesRep_ID(getAD_User_ID());
				order.saveEx();
				addDescription(Msg.translate(getCtx(), "C_Order_ID")+": "+order.getDocumentNo());
				orders.put(C_BPartner_ID, order);
				msg = msg +  Msg.translate(getCtx(), "C_Order_ID")
				+" : "+ order.getDocumentNo() 
				+" - "
				+Msg.translate(getCtx(),"C_BPartner_ID")
				+" : "+vendor.getName()+" , ";
			}
			//
			// Create Order Line: 
			BigDecimal QtyOrdered = getMovementQty().multiply(subcontract.getQty());
			// Check Order Min 
			if(product_po.getOrder_Min().signum() > 0)
			{    
				QtyOrdered = QtyOrdered.max(product_po.getOrder_Min());
			}				
			// Check Order Pack
			if (product_po.getOrder_Pack().signum() > 0 && QtyOrdered.signum() > 0)
			{
				QtyOrdered = product_po.getOrder_Pack().multiply(QtyOrdered.divide(product_po.getOrder_Pack(), 0 , BigDecimal.ROUND_UP));
			}
			MOrderLine oline = new MOrderLine(order);
			oline.setM_Product_ID(product.getM_Product_ID());
			oline.setDescription(activity.getDescription());
			oline.setM_Warehouse_ID(getM_Warehouse_ID());
			oline.setQty(QtyOrdered);
			//line.setPrice(m_product_po.getPricePO());
			//oline.setPriceList(m_product_po.getPriceList());
			oline.setPP_Cost_Collector_ID(get_ID());			
			oline.setDatePromised(datePromised);
			oline.saveEx();
			//
			// TODO: Mark this as processed? 
			setProcessed(true);
		} // each subcontracting line
		return msg;
	}
	
	@Override
	public MProduct getM_Product()
	{
		return MProduct.get(getCtx(), getM_Product_ID());
	}
	
	@Override
	public I_C_UOM getC_UOM()
	{
		return MUOM.get(getCtx(), getC_UOM_ID());
	}

	public boolean isIssue()
	{
		return
		isCostCollectorType(COSTCOLLECTORTYPE_ComponentIssue)
		|| (isCostCollectorType(COSTCOLLECTORTYPE_MethodChangeVariance) && getPP_Order_BOMLine_ID() > 0) // need inventory adjustment
		|| (isCostCollectorType(COSTCOLLECTORTYPE_MixVariance) && getPP_Order_BOMLine_ID() > 0)  // need inventory adjustment
		;
	}
	
	public boolean isReceipt()
	{
		return isCostCollectorType(COSTCOLLECTORTYPE_MaterialReceipt);
	}
	
	public boolean isActivityControl()
	{
		return isCostCollectorType(COSTCOLLECTORTYPE_ActivityControl);
	}
	
	public boolean isVariance()
	{
		return isCostCollectorType(COSTCOLLECTORTYPE_MethodChangeVariance
				, COSTCOLLECTORTYPE_UsegeVariance
				, COSTCOLLECTORTYPE_RateVariance
				, COSTCOLLECTORTYPE_MixVariance);
	}
	
	public String getMovementType()
	{
		if (isReceipt())
			return MTransaction.MOVEMENTTYPE_WorkOrderPlus;
		else if(isIssue())
			return MTransaction.MOVEMENTTYPE_WorkOrder_;
		else
			return null;
	}
	
	/**
	 * Check if CostCollectorType is equal with any of provided types
	 * @param types
	 * @return 
	 */
	public boolean isCostCollectorType(String ... types)
	{
		String type = getCostCollectorType();
		for (String t : types)
		{
			if (type.equals(t))
				return true;
		}
		return false;
	}
	
	
	public boolean isFloorStock()
	{
		final String whereClause = MPPOrderBOMLine.COLUMNNAME_PP_Order_BOMLine_ID+"=?"
									+" AND "+MPPOrderBOMLine.COLUMNNAME_IssueMethod+"=?";
		boolean isFloorStock = new Query(getCtx(), MPPOrderBOMLine.Table_Name, whereClause, get_TrxName())
						.setOnlyActiveRecords(true)
						.setParameters(new Object[]{getPP_Order_BOMLine_ID(), MPPOrderBOMLine.ISSUEMETHOD_FloorStock})
						.match();
		return isFloorStock;
	}
	
	/**
	 * set Is SubContracting
	 * @param PP_Order_Node_ID
	 **/
	public void setIsSubcontracting(int PP_Order_Node_ID)
	{
		
		setIsSubcontracting(MPPOrderNode.get(getCtx(), PP_Order_Node_ID, get_TrxName()).isSubcontracting());
	}

	@Override
	public int getM_LocatorTo_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getM_AttributeSetInstanceTo_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isSOTrx() {
		return false;
	}

	@Override
	public int getReversalLine_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getPriceActual() {
		// TODO Auto-generated method stub
		return priceActual ;
	}
	
	public void setPriceActual(BigDecimal cost)
	{
		priceActual =  cost;
	}

	@Override
	public IDocumentLine getReversalDocumentLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getPriceActualCurrency() {
		return BigDecimal.ZERO;
	}

	@Override
	public int getC_Currency_ID ()
	{
		MClient client  = MClient.get(getCtx());
		return client.getC_Currency_ID();
	}

	@Override
	public int getC_ConversionType_ID()
	{
		return  MConversionType.getDefault(getAD_Client_ID());
	}

}	//	MPPCostCollector
