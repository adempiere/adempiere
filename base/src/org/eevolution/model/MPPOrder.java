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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DocTypeNotFoundException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MDocType;
import org.compiere.model.MProduct;
import org.compiere.model.MProject;
import org.compiere.model.MRefList;
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
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
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
 *  @author Teo Sarca, www.arhipac.ro
 */
public class MPPOrder extends X_PP_Order implements DocAction
{
	private static final long serialVersionUID = 1L;
	
	public static MPPOrder forC_OrderLine_ID(Properties ctx, int C_OrderLine_ID, String trxName)
	{
		return new Query(ctx, MPPOrder.Table_Name, COLUMNNAME_C_OrderLine_ID+"=?", trxName)
								.setParameters(new Object[]{C_OrderLine_ID})
								.firstOnly();
	}


	/**************************************************************************
	 *  Default Constructor
	 *  @param ctx context
	 *  @param  C_Order_ID    order to load, (0 create new order)
	 */
	public MPPOrder(Properties ctx, int PP_Order_ID, String trxName)
	{
		super(ctx, PP_Order_ID, trxName);
		//  New
		if (PP_Order_ID == 0)
		{
			setQtyDelivered(Env.ZERO);
			setQtyReject(Env.ZERO);
			setQtyScrap(Env.ZERO);                                                        
			setIsSelected(false);
			setIsSOTrx(false);
			setIsApproved(false);
			setIsPrinted(false);
			setProcessed(false);
			setProcessing(false);
			setPosted(false);
			//
			setC_DocType_ID(0);
			setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_ManufacturingOrder);	
			//
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
	public MPPOrder(MProject project, int PP_Product_BOM_ID, int AD_Workflow_ID)
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
	 * @return Open Qty (Ordered - Delivered - Scrap)
	 */
	public BigDecimal getQtyOpen()
	{
		return getQtyOrdered().subtract(getQtyDelivered()).subtract(getQtyScrap());
	}

	/**
	 * Get BOM Lines of PP Order
	 * @param requery
	 * @return Order BOM Lines
	 */
	public MPPOrderBOMLine[] getLines(boolean requery)
	{
		if (m_lines != null && !requery)
		{
			set_TrxName(m_lines, get_TrxName());
			return m_lines;
		}
		String whereClause = MPPOrderBOMLine.COLUMNNAME_PP_Order_ID+"=?";
		List<MPPOrderBOMLine> list = new Query(getCtx(), MPPOrderBOMLine.Table_Name, whereClause, get_TrxName())
										.setParameters(new Object[]{getPP_Order_ID()})
										.setOrderBy(MPPOrderBOMLine.COLUMNNAME_Line)
										.list();
		m_lines = list.toArray(new MPPOrderBOMLine[list.size()]);
		return m_lines;
	}
	private MPPOrderBOMLine[] m_lines = null;

	/**
	 * Get BOM Lines of PP Order
	 * @return Order BOM Lines
	 */
	public MPPOrderBOMLine[] getLines()
	{
		return getLines(true);
	}
	
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
		
		if( is_ValueChanged(MPPOrder.COLUMNNAME_QtyEntered) && getDocStatus().equals(MPPOrder.DOCSTATUS_Drafted))
		{
			deleteWorkflowAndBOM();
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
			//
			deletePO(MPPOrderCost.Table_Name, whereClause, params);
			deleteWorkflowAndBOM();
		}                
		return true;
	} //	beforeDelete
	
	private void deleteWorkflowAndBOM()
	{
		// New record, nothing to do
		if (get_ID() <= 0)
		{
			return;
		}
		//
		String whereClause = "PP_Order_ID=? AND AD_Client_ID=?";
		Object[] params = new Object[]{get_ID(), getAD_Client_ID()};
		// Reset workflow start node
		DB.executeUpdateEx("UPDATE PP_Order_Workflow SET PP_Order_Node_ID=NULL WHERE "+whereClause, params, get_TrxName());
		// Delete workflow:
		deletePO(X_PP_Order_Node_Asset.Table_Name, whereClause, params);
		deletePO(X_PP_Order_Node_Product.Table_Name, whereClause, params);
		deletePO(MPPOrderNodeNext.Table_Name, whereClause, params);
		deletePO(MPPOrderNode.Table_Name, whereClause, params);
		deletePO(MPPOrderWorkflow.Table_Name, whereClause, params);
		// Delete BOM:
		deletePO(MPPOrderBOMLine.Table_Name, whereClause, params);
		deletePO(MPPOrderBOM.Table_Name, whereClause, params);
		
	}

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

		String docBaseType = MDocType.get(getCtx(), getC_DocType_ID()).getDocBaseType();
		if (MDocType.DOCBASETYPE_QualityOrder.equals(docBaseType))
		{
			; // nothing
		}
		// ManufacturingOrder, MaintenanceOrder
		else
		{
			reserveStock(lines);
			orderStock();
		}
		
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		m_justPrepared = true;
		return DocAction.STATUS_InProgress;
	} //	prepareIt

	private void orderStock()
	{
		MProduct product = getM_Product();
		if (product.isStocked())
		{
			BigDecimal target = getQtyOrdered();
			BigDecimal difference = target.subtract(getQtyReserved()).subtract(getQtyDelivered());
			if (difference.signum() == 0)
				return ;
			BigDecimal ordered = difference;
			
			int M_Locator_ID = getM_Locator_ID(ordered);
			// Necessary to clear order quantities when called from closeIt - 4Layers
			if (DOCACTION_Close.equals(getDocAction()))
			{
				if (!MStorage.add(getCtx(), getM_Warehouse_ID(), M_Locator_ID,
						getM_Product_ID(), getM_AttributeSetInstance_ID(),
						getM_AttributeSetInstance_ID(), Env.ZERO, Env.ZERO, ordered, get_TrxName()))
				{
					throw new AdempiereException();
				}
			}
			else
			{
				//	Update Storage
				if (!MStorage.add(getCtx(), getM_Warehouse_ID(), M_Locator_ID,
						getM_Product_ID(), getM_AttributeSetInstance_ID(),
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
		final int header_M_Warehouse_ID = getM_Warehouse_ID();

		//	Always check and (un) Reserve Inventory		
		for (MPPOrderBOMLine line : lines)
		{
			//	Check/set WH/Org
			if (header_M_Warehouse_ID != 0) //	enforce WH
			{
				if (header_M_Warehouse_ID != line.getM_Warehouse_ID())
					line.setM_Warehouse_ID(header_M_Warehouse_ID);
				if (getAD_Org_ID() != line.getAD_Org_ID())
					line.setAD_Org_ID(getAD_Org_ID());
			}
			//
			final BigDecimal target = line.getQtyRequiered();
			final BigDecimal difference = target.subtract(line.getQtyReserved()).subtract(line.getQtyDelivered());
			if (difference.signum() == 0)
			{
				continue;
			}
			log.fine("Line=" + line.getLine() + " - Target=" + target + ",Difference=" + difference + " - Requiered=" + line.getQtyRequiered()
					+ ",Reserved=" + line.getQtyReserved() + ",Delivered=" + line.getQtyDelivered());

			//	Check Product - Stocked and Item
			MProduct product = line.getM_Product();
			if (!product.isStocked())
			{
				continue;
			}
			BigDecimal ordered = Env.ZERO;
			BigDecimal reserved = difference;
			int M_Locator_ID = getM_Locator_ID(ordered);
			//	Update Storage
			if (!MStorage.add(getCtx(), line.getM_Warehouse_ID(), M_Locator_ID,
					line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(),
					line.getM_AttributeSetInstance_ID(), Env.ZERO, reserved, ordered, get_TrxName()))
			{
				throw new AdempiereException();
			}
			//	update line
			line.setQtyReserved(line.getQtyReserved().add(difference));
			line.saveEx(get_TrxName());
		}
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
		
		MPPOrderBOM obom = (MPPOrderBOM)getMPPOrderBOM();

		// Auto receipt and issue for kit
		if (MPPOrderBOM.BOMTYPE_Make_To_Kit.equals(obom.getBOMType()) && MPPOrderBOM.BOMUSE_Manufacturing.equals(obom.getBOMUse()))
		{				
			Timestamp today = new Timestamp(System.currentTimeMillis());
			ArrayList[][] issue = new ArrayList[m_lines.length][1];
			
			for (int i = 0; i < getLines().length ; i++)
			{
				MPPOrderBOMLine line =  m_lines[i];
				
				KeyNamePair id = null;
				
				if(MPPOrderBOMLine.ISSUEMETHOD_Backflush.equals(line.getIssueMethod()))
				{
					id = new KeyNamePair(line.get_ID(),"Y");
				}
				else
					id = new KeyNamePair(line.get_ID(),"N");
					
					ArrayList<Object> data = new ArrayList<Object>();
					
					data.add(id); 				  		//0 - ASI
					data.add(line.isCritical());  		//1 - Critical
					MProduct product = (MProduct) line.getM_Product();
					data.add(product.getValue()); 		//2 - Value
					KeyNamePair productKey = new KeyNamePair(product.get_ID(),product.getName());
					data.add(productKey); 				//3 - KeyNamePair Product
					data.add(line.getQtyRequiered()); 	//4 - QtyToDeliver			
					data.add(Env.ZERO); 				//5 - QtyScrapComponent
					issue[i][0] = data;	
				
			}
			
			boolean isCompleteQtyDeliver = MPPOrder.isAvailableQty(this, issue ,today);	
			if (!isCompleteQtyDeliver)
			{
					throw new AdempiereException("@NoQtyAvailable@");
			}	
			
			for(int i = 0; i < issue.length; i++ )
			{
				KeyNamePair key = (KeyNamePair) issue[i][0].get(0);
				Boolean isCritical = (Boolean) issue[i][0].get(1);
				String value = (String)issue[i][0].get(2);
				KeyNamePair productkey = (KeyNamePair) issue[i][0].get(3);			
				int M_Product_ID = productkey.getKey();
				BigDecimal qtyToDeliver = (BigDecimal)issue[i][0].get(4);	
				BigDecimal qtyScrapComponent = (BigDecimal) issue[i][0].get(5);	
				
				MStorage[] storages = MPPOrder.getStorages(getCtx(),
						M_Product_ID,
						getM_Warehouse_ID(),
						0,
						getM_AttributeSetInstance_ID(),
						1, today);
				
				MPPOrder.createIssue(
						this, 
						key.getKey(), 
						today, qtyToDeliver,
						qtyScrapComponent, 
						Env.ZERO, 
						storages, 
						get_TrxName());
			}	
			MPPOrder.createReceipt(
					this, 
					today , 
					this.getQtyDelivered(), 
					this.getQtyOpen(), 
					this.getQtyScrap(), 
					this.getQtyReject(), 
					this.getM_Locator_ID(), 
					this.getM_AttributeSetInstance_ID(), true, get_TrxName());
			return DocAction.ACTION_None;
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

	/**
	 * Check if the Quantity from all BOM Lines is available (QtyOnHand >= QtyRequired)
	 * @return true if entire Qty is available for this Order 
	 */
	public boolean isAvailable()
	{
		String whereClause = "QtyOnHand >= QtyRequiered AND PP_Order_ID=?";
		boolean available = new Query(getCtx(), "RV_PP_Order_Storage", whereClause, get_TrxName())
										.setParameters(new Object[]{get_ID()})
										.match();
		return available;
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
		if(MPPOrder.DOCSTATUS_Closed.equals(getDocStatus()))
			return true;
		
		if(!MPPOrder.DOCSTATUS_Completed.equals(this.getDocStatus()))
		{
			String DocStatus = completeIt();
			setDocStatus(DocStatus);
			setDocAction(MPPOrder.ACTION_Close);
		}
		// Before Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
			return false;
		
		orderStock(); // Clear Ordered Quantities
		reserveStock(getLines()); //	Clear Reservations
		
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
		// After reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (m_processMsg != null)
			return false;
		
		setDocAction(DOCACTION_Complete);
		setProcessed(false);
		return true;
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
		if (QtyOrdered != null)
		{
			int precision = getM_Product().getUOMPrecision();
			QtyOrdered = QtyOrdered.setScale(precision, BigDecimal.ROUND_HALF_UP);
		}
		super.setQtyOrdered(QtyOrdered);
	}	//	setQtyOrdered
	
//	@Override
	public MProduct getM_Product()
	{
		return MProduct.get (getCtx(), getM_Product_ID());
	}	//	getProduct
	
	public MPPOrderBOM getMPPOrderBOM()
	{
		final String whereClause = MPPOrderBOM.COLUMNNAME_PP_Order_ID+"=?";
		return new Query(getCtx(), MPPOrderBOM.Table_Name, whereClause, get_TrxName())
				.setParameters(new Object[]{getPP_Order_ID()})
				.firstOnly();
	}
	
	public MPPOrderWorkflow getMPPOrderWorkflow()
	{
			final String whereClause = MPPOrderWorkflow.COLUMNNAME_PP_Order_ID+"=?";
			return new Query(getCtx(), MPPOrderWorkflow.Table_Name, whereClause, get_TrxName())
					.setParameters(new Object[]{getPP_Order_ID()})
					.firstOnly();
	}
	
	/**
	 * Create PP_Order_BOM from PP_Product_BOM.
	 * Create PP_Order_Workflow from AD_Workflow.
	 */
	private void explotion()
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
						MPPOrderNodeNext nodenext = new MPPOrderNodeNext(AD_WF_NodeNext, PP_Order_Node);
						nodenext.setAD_Org_ID(getAD_Org_ID());
						nodenext.saveEx();
					}// for NodeNext
					
					for (MPPWFNodeProduct wfnp : MPPWFNodeProduct.forAD_WF_Node_ID(getCtx(), AD_WF_Node.get_ID()))
					{
						MPPOrderNodeProduct nodeorderproduct = new MPPOrderNodeProduct(wfnp, PP_Order_Node);
						nodeorderproduct.setAD_Org_ID(getAD_Org_ID());
						nodeorderproduct.saveEx();
					}
					
					for (MPPWFNodeAsset wfna : MPPWFNodeAsset.forAD_WF_Node_ID(getCtx(), AD_WF_Node.get_ID()))
					{
						MPPOrderNodeAsset nodeorderasset = new MPPOrderNodeAsset(wfna, PP_Order_Node);
						nodeorderasset.setAD_Org_ID(getAD_Org_ID());
						nodeorderasset.saveEx();
					}					
				}// for node 

			}
			// Update transitions nexts and set first node
			PP_Order_Workflow.loadNodes();
			for (MPPOrderNode orderNode : PP_Order_Workflow.getNodes(false, getAD_Client_ID()))
			{
				// set workflow start node
				if (PP_Order_Workflow.getAD_WF_Node_ID() == orderNode.getAD_WF_Node_ID())
				{
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
	
	/**
	 * Create Receipt Finish Good
	 * @param pp_order
	 * @param movementDate
	 * @param qtyDelivered
	 * @param qtyToDeliver
	 * @param qtyScrap
	 * @param qtyReject
	 * @param M_Locator_ID
	 * @param M_AttributeSetInstance_ID
	 * @param IsCloseDocument
	 * @param trxName
	 */
	static public void createReceipt(MPPOrder pp_order,
			Timestamp movementDate,
			BigDecimal qtyDelivered,
			BigDecimal qtyToDeliver, 
			BigDecimal qtyScrap,
			BigDecimal qtyReject,
			int M_Locator_ID,
			int M_AttributeSetInstance_ID,
			boolean IsCloseDocument,
			String trxName)
	{
		
		if (qtyToDeliver.signum() != 0 || qtyScrap.signum() != 0 || qtyReject.signum() != 0)
		{
			createCollector(pp_order, pp_order.getM_Product_ID(),
					M_Locator_ID,
					M_AttributeSetInstance_ID,
					movementDate,
					qtyToDeliver, qtyScrap, qtyReject,
					MDocType.getDocType(MDocType.DOCBASETYPE_ManufacturingOrder),
					0, // PP_Order_BOMLine_ID
					MPPCostCollector.COSTCOLLECTORTYPE_MaterialReceipt,
					trxName);
		}

		if (IsCloseDocument)
		{
			pp_order.setDateFinish(movementDate);
			pp_order.closeIt();
			pp_order.saveEx();
		}

		pp_order.setDateDelivered(movementDate);
		if (pp_order.getDateStart() == null)
		{
			pp_order.setDateStart(movementDate);
		}

		BigDecimal DQ = qtyDelivered;
		BigDecimal SQ = qtyScrap;
		BigDecimal OQ = qtyToDeliver;
		if (DQ.add(SQ).compareTo(OQ) >= 0)
		{
			pp_order.setDateFinish(movementDate);
		}

		pp_order.saveEx(trxName);

	}
	
	/**
	 * Create Issue
	 * @param PP_OrderBOMLine_ID
	 * @param movementdate
	 * @param qty
	 * @param qtyScrap
	 * @param qtyReject
	 * @param storages
	 */
	public static void createIssue(MPPOrder pp_order, int PP_OrderBOMLine_ID,
			Timestamp movementdate,
			BigDecimal qty, BigDecimal qtyScrap, BigDecimal qtyReject,
			MStorage[] storages,
			String trxName)
	{
		if (qty.signum() == 0)
			return;

		BigDecimal toIssue = qty.add(qtyScrap);
		for (MStorage storage : storages)
		{
			//	TODO Selection of ASI

			if (storage.getQtyOnHand().signum() == 0)
				continue;

			BigDecimal qtyIssue = toIssue.min(storage.getQtyOnHand());
			//log.fine("ToIssue: " + issue);
			MPPOrderBOMLine PP_orderbomLine = new MPPOrderBOMLine(pp_order.getCtx(), PP_OrderBOMLine_ID, trxName);
			//create record for negative and positive transaction
			if (qtyIssue.signum() != 0 || qtyScrap.signum() != 0 || qtyReject.signum() != 0)
			{
				String CostCollectorType = MPPCostCollector.COSTCOLLECTORTYPE_ComponentIssue;
				// Method Variance
				if (PP_orderbomLine.getQtyBatch().signum() == 0
						&& PP_orderbomLine.getQtyBOM().signum() == 0)
				{
					CostCollectorType = MPPCostCollector.COSTCOLLECTORTYPE_MethodChangeVariance;
				}
				//
				createCollector (pp_order,
						PP_orderbomLine.getM_Product_ID(),
						storage.getM_Locator_ID(),
						storage.getM_AttributeSetInstance_ID(),
						movementdate,
						qtyIssue, qtyScrap, qtyReject,
						MDocType.getDocType(MDocType.DOCBASETYPE_ManufacturingOrder),
						PP_OrderBOMLine_ID,
						CostCollectorType, // Production "-"
						trxName
				);

			}

			toIssue = toIssue.subtract(qtyIssue);
			if (toIssue.signum() == 0)
				break;  
		}
		//
		if (toIssue.signum() != 0)
		{
			// should not happen because we validate Qty On Hand on start of this process
			throw new AdempiereException("Should not happen toIssue="+toIssue);
		}
	}
	
	/**
	 * Create & Complete Cost Collector 
	 * @param pp_order
	 * @param M_Product_ID
	 * @param M_Locator_ID
	 * @param M_AttributeSetInstance_ID
	 * @param movementdate
	 * @param qty
	 * @param scrap
	 * @param reject
	 * @param C_DocType_ID
	 * @param PP_Order_BOMLine_ID
	 * @param CostCollectorType
	 * @param trxName
	 * @return completed cost collector
	 */
	private static MPPCostCollector createCollector (MPPOrder pp_order,
			int M_Product_ID,
			int M_Locator_ID,
			int M_AttributeSetInstance_ID,
			Timestamp movementdate,
			BigDecimal qty,
			BigDecimal scrap,
			BigDecimal reject,
			int C_DocType_ID,
			int PP_Order_BOMLine_ID,
			String CostCollectorType,
			String trxName
		)
	{
		MPPCostCollector cc = new MPPCostCollector(pp_order.getCtx(), 0, trxName);
		cc.setPP_Order_ID(pp_order.getPP_Order_ID());
		cc.setPP_Order_BOMLine_ID(PP_Order_BOMLine_ID);
		cc.setAD_OrgTrx_ID(pp_order.getAD_OrgTrx_ID());
		cc.setC_Activity_ID(pp_order.getC_Activity_ID());
		cc.setC_Campaign_ID(pp_order.getC_Campaign_ID());
		cc.setC_DocType_ID(C_DocType_ID);
		cc.setC_DocTypeTarget_ID(C_DocType_ID);
		cc.setCostCollectorType(CostCollectorType);
		cc.setC_Project_ID(pp_order.getC_Project_ID());
		cc.setDescription(pp_order.getDescription());
		cc.setDocAction(MPPCostCollector.ACTION_Complete);
		cc.setDocStatus(MPPCostCollector.DOCSTATUS_Drafted);
		cc.setIsActive(true);
		cc.setM_Warehouse_ID(pp_order.getM_Warehouse_ID());
		cc.setM_Locator_ID(M_Locator_ID);
		cc.setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
		cc.setS_Resource_ID(pp_order.getS_Resource_ID());
		cc.setMovementDate(movementdate);
		cc.setDateAcct(movementdate);
		cc.setMovementQty(qty);
		cc.setScrappedQty(scrap);
		cc.setQtyReject(reject);
		cc.setPosted(false);
		cc.setProcessed(false);
		cc.setProcessing(false);
		cc.setUser1_ID(pp_order.getUser1_ID());
		cc.setUser2_ID(pp_order.getUser2_ID());
		cc.setM_Product_ID(M_Product_ID);
		cc.saveEx();
		if (!cc.processIt(MPPCostCollector.DOCACTION_Complete))
		{
			throw new AdempiereException(cc.getProcessMsg());
		}
		cc.saveEx();
		return cc;
	}
	
	public static boolean isAvailableQty(MPPOrder order, I_PP_Order_BOMLine line)
	{
		MProduct product = MProduct.get(order.getCtx(), line.getM_Product_ID());
		if (product == null || !product.isStocked())
		{
			return true;
		}
		
		BigDecimal qtyToDeliver = line.getQtyRequiered();
		BigDecimal qtyScrap = line.getQtyScrap();
		BigDecimal qtyRequired = qtyToDeliver.add(qtyScrap);
		BigDecimal qtyAvailable = MStorage.getQtyAvailable(order.getM_Warehouse_ID(), 0,
												line.getM_Product_ID(), line.getM_AttributeSetInstance_ID(),
												order.get_TrxName());
		return qtyAvailable.compareTo(qtyRequired) >= 0;
	}
	
	/**
	 * get if Component is Available
	 * @param MPPOrdrt Manufacturing order
	 * @param ArrayList Issues
	 * @param minGuaranteeDate Guarantee Date
	 * @return true when the qty available is enough
	 */
	public static boolean isAvailableQty(MPPOrder order ,ArrayList[][] issue,Timestamp minGuaranteeDate)
	{
		boolean isCompleteQtyDeliver = false; 
		int ANY_ASI = 1;
		
		for(int i = 0; i < issue.length; i++ )
		{
			KeyNamePair key = (KeyNamePair) issue[i][0].get(0);
			boolean isSelected = key.getName().equals("Y"); 
			if (key == null || !isSelected)
			{
				continue;
			}
			
			String value = (String)issue[i][0].get(2);
			KeyNamePair productkey = (KeyNamePair) issue[i][0].get(3);			
			int M_Product_ID = productkey.getKey();
			BigDecimal qtyToDeliver = (BigDecimal)issue[i][0].get(4);	
			BigDecimal qtyScrapComponent = (BigDecimal) issue[i][0].get(5);	
			
			MProduct product = MProduct.get(order.getCtx(), M_Product_ID);
			if (product != null && product.isStocked()) 
			{
				int M_AttributeSetInstance_ID = ANY_ASI;
				if (value == null && isSelected)
				{
					M_AttributeSetInstance_ID = (Integer)key.getKey();
				}
				
				MStorage[] storages = MPPOrder.getStorages(order.getCtx(),
						M_Product_ID,
						order.getM_Warehouse_ID(),
						M_AttributeSetInstance_ID,
						order.getM_AttributeSetInstance_ID(),
						ANY_ASI, 
						minGuaranteeDate);
				
				if (M_AttributeSetInstance_ID == ANY_ASI)
				{					
					BigDecimal toIssue = qtyToDeliver.add(qtyScrapComponent);
					for (MStorage storage : storages) 
					{
						//	TODO Selection of ASI
						if (storage.getQtyOnHand().signum() == 0)
							continue;
						BigDecimal issueActual = toIssue.min(storage.getQtyOnHand());
						toIssue = toIssue.subtract(issueActual);
						if (toIssue.signum() <= 0)
							break;
					}
				}
				else
				{
					BigDecimal qtydelivered = qtyToDeliver;
					qtydelivered.setScale(4, BigDecimal.ROUND_HALF_UP);
					qtydelivered = Env.ZERO;
				}
		
				BigDecimal onHand = Env.ZERO;
				for (MStorage storage : storages)
				{
					onHand = onHand.add(storage.getQtyOnHand());
				}
		
				isCompleteQtyDeliver = onHand.compareTo(qtyToDeliver.add(qtyScrapComponent)) >= 0;
				if (!isCompleteQtyDeliver)
					break;
				
			}
		} // for each line
	
		return isCompleteQtyDeliver;
	}
	
	public static MStorage[] getStorages(
			Properties ctx,
			int M_Product_ID,
			int M_Warehouse_ID,
			int M_ASI_ID,
			int O_ASI_ID,
			int ANY_ASI,
			Timestamp minGuaranteeDate)
	{
		MProduct product = MProduct.get(ctx, M_Product_ID);
		if (product != null && product.isStocked())
		{
			String MMPolicy = product.getMMPolicy();
			return MStorage.getWarehouse(ctx,
					M_Warehouse_ID,
					M_Product_ID,
					M_ASI_ID == ANY_ASI ? O_ASI_ID : M_ASI_ID,
					product.getM_AttributeSet_ID(),
					true, // all attribute set instances
					minGuaranteeDate,
					MClient.MMPOLICY_FiFo.equals(MMPolicy),
					null // no trx
			);
		}
		else
		{
			return new MStorage[0];
		}
	}
	
	/**
	 * @return Default Locator for current Warehouse
	 */
	private int getM_Locator_ID()
	{
		MWarehouse wh = MWarehouse.get(getCtx(), getM_Warehouse_ID());
		return wh.getDefaultLocator().getM_Locator_ID();
	}
	
	/**
	 * @param qty
	 * @return Storage locator for current product/asi/warehouse and qty
	 * @see MStorage#getM_Locator_ID(int, int, int, BigDecimal, String)
	 */
	private int getM_Locator_ID(BigDecimal qty)
	{
		int M_Locator_ID = 0;
		int M_ASI_ID = getM_AttributeSetInstance_ID();
		// Get existing Locator
		if (M_ASI_ID != 0)
		{
			M_Locator_ID = MStorage.getM_Locator_ID(getM_Warehouse_ID(), getM_Product_ID(), M_ASI_ID, qty, get_TrxName());
		}
		// Get Default
		if (M_Locator_ID == 0)
		{
			M_Locator_ID = getM_Locator_ID();
		}
		return M_Locator_ID;
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
