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
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MDocType;
import org.compiere.model.MProduct;
import org.compiere.model.MProject;
import org.compiere.model.MResource;
import org.compiere.model.MStorage;
import org.compiere.model.MTable;
import org.compiere.model.MUOM;
import org.compiere.model.MWarehouse;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWFNodeNext;
import org.compiere.wf.MWorkflow;

/**
 *  Order Model.
 * 	Please do not set DocStatus and C_DocType_ID directly.
 * 	They are set in the process() method.
 * 	Use DocAction and C_DocTypeTarget_ID instead.
 *
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MOrder.java,v 1.57 2004/05/21 02:27:38 vpj-cd Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 */
public class MPPOrder extends X_PP_Order implements DocAction
{
	private static final long serialVersionUID = 1L;
	/**	Product					*/
	private MProduct 		m_product = null;

	/**
	 * 	Create new Order by copying
	 * 	@param ctx context
	 *	@param C_Order_ID invoice
	 * 	@param dateDoc date of the document date
	 * 	@param counter create counter links
	 *	@return Order
	 */
	public static MPPOrder copyFrom(MPPOrder from, Timestamp dateDoc, int C_DocTypeTarget_ID,
									boolean isSOTrx, boolean counter)
	{
		MPPOrder to = new MPPOrder(from.getCtx(), 0, "PP_Order");
		PO.copyValues(from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
		to.setPP_Order_ID(0);
		to.set_ValueNoCheck("DocumentNo", null);
		//
		to.setDocStatus(DOCSTATUS_Drafted); //	Draft
		to.setDocAction(DOCACTION_Prepare);
		//
		//to.setC_DocType_ID(this.C_DOCTYPE_ID_ManufacturingOrder);
		//to.setC_DocTypeTarget_ID(C_DocTypeTarget_ID);
		to.setIsSOTrx(isSOTrx);
		//
		to.setIsSelected(false);
		/*to.setDateOrdered (dateDoc);
		 to.setDateAcct (dateDoc);
		 to.setDatePromised (dateDoc);
		 to.setDatePrinted(null);
		 to.setIsPrinted (false);
		 //*/
		to.setIsApproved(false);
		/*to.setIsCreditApproved(false);
		 to.setC_Payment_ID(0);
		 to.setC_CashLine_ID(0);
		 //	Amounts are updated  when adding lines
		 to.setGrandTotal(Env.ZERO);
		 to.setTotalLines(Env.ZERO);
		 //
		 to.setIsDelivered(false);
		 to.setIsInvoiced(false);
		 to.setIsSelfService(false);
		 to.setIsTransferred (false);*/
		to.setPosted(false);
		to.setProcessed(false);
		/*if (counter)
		 to.setRef_Order_ID(from.getC_Order_ID());
		 else
		 to.setRef_Order_ID(0);
		 //
		 */
		to.saveEx();
		/*if (counter)
		 from.setRef_Order_ID(to.getC_Order_ID());

		 if (to.copyLinesFrom(from, counter) == 0)
		 throw new IllegalStateException("Could not create Order Lines");
		 */
		return to;
	} //	copyFrom

	/**************************************************************************
	 *  Default Constructor
	 *  @param ctx context
	 *  @param  C_Order_ID    order to load, (0 create new order)
	 */
	public MPPOrder(Properties ctx, int PP_Order_ID, String trxName)
	{
		super(ctx, PP_Order_ID, trxName);
		//  New
		if (PP_Order_ID == 0) {
			setIsSelected(false);
			setIsSOTrx(false);
			setIsApproved(false);
			setIsPrinted(false);
			setProcessed(false);
			setProcessing(false);
			setPosted(false);
			MDocType[] doc = MDocType.getOfDocBaseType(getCtx(), MDocType.DOCBASETYPE_ManufacturingOrder);	
			if(doc == null)
			{
				throw new IllegalArgumentException ("C_DocType_ID is mandatory.");
			}
			else
			{	
				setC_DocType_ID(doc[0].getC_DocType_ID());
				setC_DocTypeTarget_ID(doc[0].getC_DocType_ID());
			}
			
			//set_ValueNoCheck("DocumentNo", null);
			setDocStatus(DOCSTATUS_Drafted);
			setDocAction(DOCACTION_Prepare);
		}
	} //	PP_Order

	/**************************************************************************
	 *  Project Constructor
	 *  @param  project Project to create Order from
	 * 	@param	DocSubTypeSO if SO DocType Target (default DocSubTypeSO_OnCredit)
	 */
	public MPPOrder(MProject project, int PP_Product_BOM_ID,int AD_Workflow_ID)
	{
		this(project.getCtx(), 0, project.get_TrxName());
		setAD_Client_ID(project.getAD_Client_ID());
		setAD_Org_ID(project.getAD_Org_ID());
		setC_Campaign_ID(project.getC_Campaign_ID());
		setC_Project_ID(project.getC_Project_ID());
		setDescription(project.getName());
		setLine(10);
		setPriorityRule(MPPOrder.PRIORITYRULE_Medium);
		if (project.getDateContract() == null)
			throw new IllegalStateException("Date Contract is mandatory for Manufacturing Order.");
		if (project.getDateFinish() == null)
			throw new IllegalStateException("Date Finish is mandatory for Manufacturing Order.");
		
		Timestamp ts = project.getDateContract();
		Timestamp df= project.getDateContract();
		
		if (ts != null) setDateOrdered(ts);
		if (ts != null) this.setDateStartSchedule(ts);
		ts = project.getDateFinish();
		if (df != null) setDatePromised(df);
		setM_Warehouse_ID(project.getM_Warehouse_ID());
		setPP_Product_BOM_ID(PP_Product_BOM_ID);
		setAD_Workflow_ID(AD_Workflow_ID);
		setQtyEntered(Env.ONE);
		setQtyOrdered(Env.ONE);
		MPPProductBOM bom = new MPPProductBOM(project.getCtx(), PP_Product_BOM_ID, project.get_TrxName());
		MProduct product = MProduct.get(project.getCtx(), bom.getM_Product_ID());
		setC_UOM_ID(product.getC_UOM_ID());
		
		setM_Product_ID(bom.getM_Product_ID());
		
		String where = MResource.COLUMNNAME_IsManufacturingResource   +" = 'Y' AND "+ 
					   MResource.COLUMNNAME_ManufacturingResourceType +" = '" + MResource.MANUFACTURINGRESOURCETYPE_Plant + "' AND " +
					   MResource.COLUMNNAME_M_Warehouse_ID + " = " + project.getM_Warehouse_ID();
		MResource resoruce = (MResource) MTable.get(project.getCtx(), MResource.Table_ID).getPO( where , project.get_TrxName());
		if (resoruce == null)
			throw new IllegalStateException("Resource is mandatory.");
		setS_Resource_ID(resoruce.getS_Resource_ID());

	} //	MOrder

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MPPOrder(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	} //	MOrder

	/**
	 * @return Open Qty
	 */
	public BigDecimal getQtyOpen()
	{
		return getQtyOrdered().subtract(getQtyDelivered()).subtract(getQtyScrap());
	}

	/**
	 * Get BOM Lines of PP Order
	 * @return Order BOM Lines
	 */
	public MPPOrderBOMLine[] getLines()
	{
		String whereClause = MPPOrderBOMLine.COLUMNNAME_PP_Order_ID+"=?";
		//
		List<MPPOrderBOMLine> list = new Query(getCtx(), MPPOrderBOMLine.Table_Name, whereClause, get_TrxName())
										.setParameters(new Object[]{getPP_Order_ID()})
										.setOrderBy(MPPOrderBOMLine.COLUMNNAME_Line)
										.list();
		return list.toArray(new MPPOrderBOMLine[list.size()]);
	} //	getLines

	@Override
	public void setProcessed(boolean processed)
	{
		super.setProcessed(processed);
		
		// Update DB:
		if (get_ID() <= 0)
			return;
		final String sql = "UPDATE PP_Order SET Processed=? WHERE PP_Order_ID=?";
		DB.executeUpdateEx(sql, new Object[]{processed, get_ID()}, get_TrxName());
	} //	setProcessed

	@Override
	protected boolean beforeSave(boolean newRecord)
	{
		if (getAD_Client_ID() == 0)
		{
			m_processMsg = "AD_Client_ID = 0";
			return false;
		}
		if (getAD_Org_ID() == 0)
		{
			int context_AD_Org_ID = Env.getAD_Org_ID(getCtx());
			if (context_AD_Org_ID == 0) {
				m_processMsg = "AD_Org_ID = 0";
				return false;
			}
			setAD_Org_ID(context_AD_Org_ID);
			log.warning("beforeSave - Changed Org to Context=" + context_AD_Org_ID);
		}
		if (getM_Warehouse_ID() == 0)
		{
			int ii = Env.getContextAsInt(getCtx(), "#M_Warehouse_ID");
			if (ii != 0)
			{
				setM_Warehouse_ID(ii);
			}
		}
		// If DateFinishSchedule is not filled, use DatePromised
		if (getDateFinishSchedule() == null)
		{
			setDateFinishSchedule(getDatePromised());
		}				

		return true;
	}

	@Override
	protected boolean afterSave(boolean newRecord, boolean success)
	{
		if (!success)
		{
			return false;
		}
		
		if( is_ValueChanged(MPPOrder.COLUMNNAME_QtyEntered) && 
				getDocStatus().equals(MPPOrder.DOCSTATUS_Drafted))
		{
			for(MPPOrderBOMLine line : getLines())
			{
				line.deleteEx(true);
			}
			MPPOrderBOM bom = getMPPOrderBOM();
			if(bom != null)
				bom.deleteEx(true, get_TrxName());

			MPPOrderWorkflow PP_Order_Workflow = getMPPOrderWorkflow();
			if (PP_Order_Workflow != null)
			{	
				PP_Order_Workflow.setPP_Order_Node_ID(0);
				PP_Order_Workflow.saveEx();
				for(MPPOrderNode node : PP_Order_Workflow.getNodes(true, getAD_Client_ID()))
				{	
					for(MPPOrderNodeNext next : node.getTransitions(getAD_Client_ID()))
					{
						next.deleteEx(true);
					}
					node.deleteEx(true);
				}		
				PP_Order_Workflow.deleteEx(true);
			}	
			explotion();
		}
		if( is_ValueChanged(MPPOrder.COLUMNNAME_QtyEntered) && !getDocStatus().equals(MPPOrder.DOCSTATUS_Drafted))
		{
			throw new AdempiereException("Cannot Change Quantity, Only is allow with Draft Status"); // TODO: Create Message for Translation
		}
		
		if (!newRecord)
		{
			return success;
		}
		
		explotion();
		return true;
	} //	beforeSave

	@Override
	protected boolean beforeDelete()
	{
		// OrderBOMLine
		if (getDocStatus().equals(DOCSTATUS_Drafted) || getDocStatus().equals(DOCSTATUS_InProgress))
		{
			String whereClause = "PP_Order_ID=? AND AD_Client_ID=?";
			Object[] params = new Object[]{get_ID(), getAD_Client_ID()};
			
			// Delete Cost:
			deletePO(MPPOrderCost.Table_Name, whereClause, params);
			// Delete workflow:
			DB.executeUpdateEx("UPDATE PP_Order_Workflow SET PP_Order_Node_ID=NULL WHERE "+whereClause, params, get_TrxName()); //	Reset workflow start node
			deletePO(X_PP_Order_Node_Asset.Table_Name, whereClause, params);
			deletePO(X_PP_Order_Node_Product.Table_Name, whereClause, params);
			deletePO(MPPOrderNodeNext.Table_Name, whereClause, params);
			deletePO(MPPOrderNode.Table_Name, whereClause, params);
			deletePO(MPPOrderWorkflow.Table_Name, whereClause, params);
			// Delete BOM:
			deletePO(MPPOrderBOMLine.Table_Name, whereClause, params);
			deletePO(MPPOrderBOM.Table_Name, whereClause, params);
		}                
		return true;
	} //	beforeDelete

	public boolean processIt(String processAction)
	{
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(processAction, getDocAction());
	} //	processIt

	/**	Process Message 			*/
	private String m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean m_justPrepared = false;

	public boolean unlockIt()
	{
		log.info("unlockIt - " + toString());
		setProcessing(false);
		return true;
	} //	unlockIt

	public boolean invalidateIt()
	{
		log.info("invalidateIt - " + toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	} //	invalidateIt

	public String prepareIt()
	{
		log.info("prepareIt - " + toString());
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		//	Lines
		MPPOrderBOMLine[] lines = getLines();
		if (lines.length == 0)
		{
			m_processMsg = "@NoLines@";
			return DocAction.STATUS_Invalid;
		}

		//	Cannot change Std to anything else if different warehouses
		if (getC_DocType_ID() != 0)
		{
			for (int i = 0; i < lines.length; i++)
			{
				if (lines[i].getM_Warehouse_ID() != getM_Warehouse_ID())
				{
					log.warning("different Warehouse " + lines[i]);
					m_processMsg = "@CannotChangeDocType@";
					return DocAction.STATUS_Invalid;
				}
			}
		}

		//	New or in Progress/Invalid
		if (DOCSTATUS_Drafted.equals(getDocStatus())
				|| DOCSTATUS_InProgress.equals(getDocStatus())
				|| DOCSTATUS_Invalid.equals(getDocStatus())
				|| getC_DocType_ID() == 0)
		{
			setC_DocType_ID(getC_DocTypeTarget_ID());
		}

		MDocType doc = MDocType.get(getCtx(), getC_DocType_ID());
		if (doc.getDocBaseType().equals(MDocType.DOCBASETYPE_QualityOrder))
		{
			return DocAction.STATUS_InProgress;
		}

		reserveStock(lines);
		orderStock();
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		m_justPrepared = true;
		return DocAction.STATUS_InProgress;
	} //	prepareIt

	private void orderStock()
	{
		MProduct product = MProduct.get(getCtx(), getM_Product_ID());
		if (product != null && product.isStocked())
		{
			BigDecimal target = getQtyOrdered();
			BigDecimal difference = target.subtract(getQtyReserved()).subtract(getQtyDelivered());
			if (difference.signum() == 0)
				return ;
			BigDecimal ordered = difference;
			
			int M_Locator_ID = 0;
			//	Get Locator to reserve
			if (getM_AttributeSetInstance_ID() != 0) //	Get existing Location
				M_Locator_ID = MStorage.getM_Locator_ID(getM_Warehouse_ID(), getM_Product_ID(), getM_AttributeSetInstance_ID(), ordered,
						get_TrxName());
			//	Get default Location
			if (M_Locator_ID == 0) {
				MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
				M_Locator_ID = wh.getDefaultLocator().getM_Locator_ID();
			}
			//4Layers - Necessary to clear order quantities when called from closeIt
			if (DOCACTION_Close.equals(getDocAction()))
			{
				if (!MStorage.add(getCtx(), getM_Warehouse_ID(), M_Locator_ID, getM_Product_ID(), getM_AttributeSetInstance_ID(),
						getM_AttributeSetInstance_ID(), Env.ZERO, Env.ZERO, ordered, get_TrxName()))
				{
					throw new AdempiereException();
				}
			}
			else
			{
				//	Update Storage
				if (!MStorage.add(getCtx(), getM_Warehouse_ID(), M_Locator_ID, getM_Product_ID(), getM_AttributeSetInstance_ID(),
						getM_AttributeSetInstance_ID(), Env.ZERO, Env.ZERO, ordered, get_TrxName()))
				{
					throw new AdempiereException();
				}
			}

			setQtyReserved(getQtyReserved().add(difference));
			saveEx(get_TrxName());
		}
	}

	/**
	 * Reserve Inventory.
	 * @param lines order lines (ordered by M_Product_ID for deadlock prevention)
	 * @return true if (un) reserved
	 */
	private void reserveStock(MPPOrderBOMLine[] lines)
	{
		int header_M_Warehouse_ID = getM_Warehouse_ID();

		//	Always check and (un) Reserve Inventory		
		for (int i = 0; i < lines.length; i++) {
			MPPOrderBOMLine line = lines[i];
			//	Check/set WH/Org
			if (header_M_Warehouse_ID != 0) //	enforce WH
			{
				if (header_M_Warehouse_ID != line.getM_Warehouse_ID())
					line.setM_Warehouse_ID(header_M_Warehouse_ID);
				if (getAD_Org_ID() != line.getAD_Org_ID())
					line.setAD_Org_ID(getAD_Org_ID());
			}
			//	Binding
			//vpj BigDecimal target = binding ? line.getQtyOrdered() : Env.ZERO;
			BigDecimal target = line.getQtyRequiered();
			BigDecimal difference = target.subtract(line.getQtyReserved()).subtract(line.getQtyDelivered());
			if (difference.signum() == 0)
				continue;

			log.fine("Line=" + line.getLine() + " - Target=" + target + ",Difference=" + difference + " - Requiered=" + line.getQtyRequiered()
					+ ",Reserved=" + line.getQtyReserved() + ",Delivered=" + line.getQtyDelivered());

			//	Check Product - Stocked and Item
			MProduct product = line.getM_Product();
			if (product != null)
			{
				if (product.isStocked())
				{
					//vpj BigDecimal ordered = isSOTrx ? Env.ZERO : difference;
					BigDecimal ordered = Env.ZERO;
					//BigDecimal reserved = isSOTrx ? difference : Env.ZERO;
					BigDecimal reserved = difference;
					int M_Locator_ID = 0;
					//	Get Locator to reserve
					if (line.getM_AttributeSetInstance_ID() != 0) //	Get existing Location
						M_Locator_ID = MStorage.getM_Locator_ID(line.getM_Warehouse_ID(), line.getM_Product_ID(),
								line.getM_AttributeSetInstance_ID(), ordered, get_TrxName());
					//	Get default Location
					if (M_Locator_ID == 0) {
						MWarehouse wh = MWarehouse.get(getCtx(), line.getM_Warehouse_ID());
						M_Locator_ID = wh.getDefaultLocator().getM_Locator_ID();
					}
					//	Update Storage
					if (!MStorage.add(getCtx(), line.getM_Warehouse_ID(), M_Locator_ID, line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(),
							line.getM_AttributeSetInstance_ID(), Env.ZERO, reserved, ordered, get_TrxName()))
					{
						throw new AdempiereException();
					}
				} //	stockec
				//	update line
				line.setQtyReserved(line.getQtyReserved().add(difference));
				line.saveEx(get_TrxName());
			}
		} //	reverse inventory
	} //	reserveStock

	public boolean approveIt()
	{
		log.info("approveIt - " + toString());
		MDocType doc = MDocType.get(getCtx(), getC_DocType_ID());
		if (doc.getDocBaseType().equals(MDocType.DOCBASETYPE_QualityOrder))
		{
			String whereClause = COLUMNNAME_PP_Product_BOM_ID+"=? AND "+COLUMNNAME_AD_Workflow_ID+"=?";
			MQMSpecification qms = new Query(getCtx(), MQMSpecification.Table_Name, whereClause, get_TrxName())
										.setParameters(new Object[]{getPP_Product_BOM_ID(), getAD_Workflow_ID()})
										.first();
			return qms != null ? qms.isValid(getM_AttributeSetInstance_ID()) : true;
		}
		else
		{
			setIsApproved(true);
		}

		return true;
	} //	approveIt

	public boolean rejectIt()
	{
		log.info("rejectIt - " + toString());
		setIsApproved(false);
		return true;
	} //	rejectIt

	public String completeIt()
	{
		//	Just prepare
		if (DOCACTION_Prepare.equals(getDocAction()))
		{
			setProcessed(false);
			return DocAction.STATUS_InProgress;
		}

		//	Re-Check
		if (!m_justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
		{
			return DocAction.STATUS_Invalid;
		}
		
		//	Implicit Approval
		if (!isApproved())
		{
			approveIt();
		}
		
		MAcctSchema acctSchema = MClient.get(getCtx(), getAD_Client_ID()).getAcctSchema();
		log.info("Cost_Group_ID" + acctSchema.getM_CostType_ID());

		//
		// Create Standard Costs for Order 
		MCost[] costs = MCost.getCosts(getCtx(), getAD_Client_ID(), getAD_Org_ID(), getM_Product_ID(),
										acctSchema.getM_CostType_ID(), acctSchema.get_ID(),
										get_TrxName());
		for (MCost cost : costs)
		{
			MPPOrderCost PP_Order_Cost = new MPPOrderCost(cost, get_ID(), get_TrxName());
			PP_Order_Cost.saveEx();
		}

		//
		// Create Standard Costs for Order BOM Line
		for (MPPOrderBOMLine line : getLines())
		{
			costs = MCost.getCosts(getCtx(), getAD_Client_ID(), getAD_Org_ID(), line.getM_Product_ID(),
									acctSchema.getM_CostType_ID(), acctSchema.get_ID(),
									get_TrxName());
			for (MCost cost : costs)
			{
				MPPOrderCost PP_Order_Cost = new MPPOrderCost(cost, get_ID(), get_TrxName());
				PP_Order_Cost.saveEx();
			}
		}

		setProcessed(true);
		setDocAction(DOCACTION_Close);

		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		return DocAction.STATUS_Completed;
	} //	completeIt

	public boolean isAvailable()
	{
		String whereClause = "QtyOnHand - QtyRequiered < 0 AND PP_Order_ID=?";
		boolean notAvailable = new Query(getCtx(), X_RV_PP_Order_Storage.Table_Name, whereClause, get_TrxName())
										.setParameters(new Object[]{get_ID()})
										.match();
		return !notAvailable;
	}

	public boolean voidIt()
	{
		log.info(toString());
		// Before Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (m_processMsg != null)
			return false;
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (m_processMsg != null)
			return false;
		
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	} //	voidIt

	public boolean closeIt()
	{
		log.info(toString());
		
		// Before Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
			return false;

		//	Close Not delivered Qty - SO/PO
		MPPOrderBOMLine[] lines = getLines();
		/*
		 for (int i = 0; i < lines.length; i++)
		 {
		 MPPOrderBOMLine line = lines[i];
		 BigDecimal old = line.getQtyRequiered();
		 if (old.compareTo(line.getQtyDelivered()) != 0)
		 {
		 //line.setQtyLostSales(line.getQtyRequiered().subtract(line.getQtyDelivered()));
		 line.setQtyRequiered(line.getQtyDelivered());
		 //	QtyEntered unchanged
		 //line.addDescription("Close (" + old + ")");
		 line.save(get_TrxName());
		 }
		 }*/

		orderStock(); // Clear Ordered Quantities
		reserveStock(lines); //	Clear Reservations
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
			return false;
		
		setProcessed(true);
		setDocAction(DOCACTION_None);
		return true;
	} //	closeIt

	public boolean reverseCorrectIt()
	{
		log.info("reverseCorrectIt - " + toString());
		return voidIt();
	} //	reverseCorrectionIt

	public boolean reverseAccrualIt()
	{
		log.info("reverseAccrualIt - " + toString());
		return false;
	} //	reverseAccrualIt

	public boolean reActivateIt()
	{
		log.info("reActivateIt - " + toString());
		return false;
	} //	reActivateIt

	public int getDoc_User_ID()
	{
		return getPlanner_ID();
	} //	getDoc_User_ID

	public BigDecimal getApprovalAmt()
	{
		return Env.ZERO;
	} //	getApprovalAmt

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
		return "" + getDocumentNo() + "/" + getDatePromised();
	}

	public File createPDF()
	{
		try {
			File temp = File.createTempFile(get_TableName() + get_ID() + "_", ".pdf");
			return createPDF(temp);
		}
		catch (Exception e) {
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
	} //	getPDF

	/**
	 * 	Create PDF file
	 *	@param file output file
	 *	@return file if success
	 */
	public File createPDF(File file)
	{
		ReportEngine re = ReportEngine.get(getCtx(), ReportEngine.MANUFACTURING_ORDER, getPP_Order_ID());
		if (re == null)
			return null;
		return re.getPDF(file);
	} //	createPDF

	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	} //	getDocumentInfo
	
	private void deletePO(String tableName, String whereClause, Object[] params)
	{
		// TODO: refactor this method and move it to org.compiere.model.Query class
		POResultSet<PO> rs = new Query(getCtx(), tableName, whereClause, get_TrxName())
									.setParameters(params)
									.scroll();
		try
		{
			while(rs.hasNext())
			{
				rs.next().deleteEx(true);
			}
		}
		finally
		{
			rs.close();
		}
	}
	
	/**
	 * 	Set Qty Entered/Ordered.
	 * 	Use this Method if the Line UOM is the Product UOM 
	 *	@param Qty QtyOrdered/Entered
	 */
	public void setQty (BigDecimal Qty)
	{
		super.setQtyEntered (Qty);
		super.setQtyOrdered (getQtyEntered());
	}	//	setQty
	
	/**
	 * 	Set Qty Entered - enforce entered UOM 
	 *	@param QtyEntered
	 */
	public void setQtyEntered (BigDecimal QtyEntered)
	{
		if (QtyEntered != null && getC_UOM_ID() != 0)
		{
			int precision = MUOM.getPrecision(getCtx(), getC_UOM_ID());
			QtyEntered = QtyEntered.setScale(precision, BigDecimal.ROUND_HALF_UP);
		}
		super.setQtyEntered (QtyEntered);
	}	//	setQtyEntered

	/**
	 * 	Set Qty Ordered - enforce Product UOM 
	 *	@param QtyOrdered
	 */
	public void setQtyOrdered (BigDecimal QtyOrdered)
	{
		MProduct product = getProduct();
		if (QtyOrdered != null && product != null)
		{
			int precision = product.getUOMPrecision();
			QtyOrdered = QtyOrdered.setScale(precision, BigDecimal.ROUND_HALF_UP);
		}
		super.setQtyOrdered(QtyOrdered);
	}	//	setQtyOrdered
	
	/**
	 * 	Get Product
	 *	@return product or null
	 */
	public MProduct getProduct()
	{
		if (m_product == null && getM_Product_ID() != 0)
			m_product =  MProduct.get (getCtx(), getM_Product_ID());
		return m_product;
	}	//	getProduct
	
	public MPPOrderBOM getMPPOrderBOM()
	{
		final String whereClause = MPPOrderBOM.COLUMNNAME_PP_Order_ID+"=?";
		return new Query(getCtx(), MPPOrderBOM.Table_Name, whereClause, get_TrxName())
				.setParameters(new Object[]{getPP_Order_ID()})
				.first();
	}
	
	public MPPOrderWorkflow getMPPOrderWorkflow()
	{
			final String whereClause = MPPOrderWorkflow.COLUMNNAME_PP_Order_ID+"=?";
			return new Query(getCtx(), MPPOrderWorkflow.Table_Name, whereClause, get_TrxName())
					.setParameters(new Object[]{getPP_Order_ID()})
					.first();
	}
	
	public void explotion()
	{
		// Create BOM Head
		MPPProductBOM PP_Product_BOM = MPPProductBOM.get(getCtx(), getPP_Product_BOM_ID());
		if (PP_Product_BOM.isValidFromTo(getDateStartSchedule()))
		{
			MPPOrderBOM PP_Order_BOM = new MPPOrderBOM(PP_Product_BOM, getPP_Order_ID(), get_TrxName());
			PP_Order_BOM.setAD_Org_ID(getAD_Org_ID());
			PP_Order_BOM.saveEx();

			for (MPPProductBOMLine PP_Product_BOMline : PP_Product_BOM.getLines())
			{
				if (PP_Product_BOMline.isValidFromTo(getDateStartSchedule()))
				{
					MPPOrderBOMLine PP_Order_BOMLine = new MPPOrderBOMLine(PP_Product_BOMline,
																getPP_Order_ID(), PP_Order_BOM.get_ID(),
																getM_Warehouse_ID(),
																get_TrxName());
					PP_Order_BOMLine.setAD_Org_ID(getAD_Org_ID());
					PP_Order_BOMLine.setQtyOrdered(getQtyOrdered());
					PP_Order_BOMLine.saveEx();
				} // end if valid From / To    
			} // end Create Order BOM

		} // end if From / To parent

		// Create Workflow (Routing & Process
		MWorkflow AD_Workflow = MWorkflow.get(getCtx(), getAD_Workflow_ID());
		if (AD_Workflow.isValidFromTo(getDateStartSchedule()))
		{
			MPPOrderWorkflow PP_Order_Workflow = new MPPOrderWorkflow(AD_Workflow, get_ID(), get_TrxName());
			PP_Order_Workflow.setAD_Org_ID(getAD_Org_ID());
			PP_Order_Workflow.saveEx();
			for (MWFNode AD_WF_Node : AD_Workflow.getNodes(false, getAD_Client_ID()))
			{
				if (AD_WF_Node.isValidFromTo(getDateStartSchedule()))
				{
					MPPOrderNode PP_Order_Node = new MPPOrderNode(AD_WF_Node, PP_Order_Workflow,
															getQtyOrdered(),
															get_TrxName());
					PP_Order_Node.setAD_Org_ID(getAD_Org_ID());
					PP_Order_Node.saveEx();
					
					for (MWFNodeNext AD_WF_NodeNext : AD_WF_Node.getTransitions(getAD_Client_ID()))
					{
						MPPOrderNodeNext nodenext = new MPPOrderNodeNext(AD_WF_NodeNext, PP_Order_Node, get_TrxName());
						nodenext.setAD_Org_ID(getAD_Org_ID());
						nodenext.saveEx();
					}// for NodeNext
					
					for (MPPWFNodeProduct wfnp : MPPWFNodeProduct.get(getCtx(), AD_WF_Node.get_ID() , get_TrxName()))
					{
						MPPOrderNodeProduct nodeorderproduct = new MPPOrderNodeProduct(wfnp);
						nodeorderproduct.setAD_Org_ID(getAD_Org_ID());
						nodeorderproduct.setPP_Order_ID(getPP_Order_ID());
						nodeorderproduct.setPP_Order_Workflow_ID(PP_Order_Node.getPP_Order_Workflow_ID());
						nodeorderproduct.setPP_Order_Node_ID(PP_Order_Node.getPP_Order_Node_ID());
						nodeorderproduct.saveEx();
					}// for NodeNext
					
					for (MPPWFNodeAsset wfna : MPPWFNodeAsset.get(getCtx(), AD_WF_Node.get_ID() , get_TrxName()))
					{
						MPPOrderNodeAsset nodeorderasset = new MPPOrderNodeAsset(wfna);
						nodeorderasset.setAD_Org_ID(getAD_Org_ID());
						nodeorderasset.setPP_Order_ID(getPP_Order_ID());
						nodeorderasset.setPP_Order_Workflow_ID(PP_Order_Node.getPP_Order_Workflow_ID());
						nodeorderasset.setPP_Order_Node_ID(PP_Order_Node.getPP_Order_Node_ID());
						nodeorderasset.saveEx();
					}					
				}// for node 

			}
			// Update transitions nexts and set first node
			PP_Order_Workflow.loadNodes();
			for (MPPOrderNode orderNode : PP_Order_Workflow.getNodes(false, getAD_Client_ID()))
			{
				// set workflow start node
				if (PP_Order_Workflow.getAD_WF_Node_ID() == orderNode.getAD_WF_Node_ID()) {
					PP_Order_Workflow.setPP_Order_Node_ID(orderNode.getPP_Order_Node_ID());
				}
				// set node next
				for (MPPOrderNodeNext next : orderNode.getTransitions(getAD_Client_ID()))
				{
					next.setPP_Order_Next_ID();
					next.saveEx();
				}
			}
			PP_Order_Workflow.saveEx();
			
			BigDecimal QtyBatchs = null;
			BigDecimal QtyBatchSize = PP_Order_Workflow.getQtyBatchSize().setScale(0, RoundingMode.UP); 

			if (QtyBatchSize.signum()==0)
				QtyBatchs = Env.ONE;
			else   
				QtyBatchs = getQtyOrdered().divide(QtyBatchSize , 0, BigDecimal.ROUND_UP); 
			
			setQtyBatchs(QtyBatchs);
			setQtyBatchSize(QtyBatchSize);
			
		} // workflow valid from/to 
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer("MPPOrder[").append(get_ID())
								.append("-").append(getDocumentNo())
								.append(",IsSOTrx=").append(isSOTrx())
								.append(",C_DocType_ID=").append(getC_DocType_ID())
								.append("]");
		return sb.toString();
	} //	toString
} // MPPOrder
