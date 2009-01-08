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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DocTypeNotFoundException;
import org.adempiere.exceptions.FillMandatoryException;
import org.adempiere.exceptions.NoVendorForProductException;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_M_Product;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPeriod;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.MStorage;
import org.compiere.model.MTransaction;
import org.compiere.model.MUOM;
import org.compiere.model.MWarehouse;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.Query;
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
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MPPCostCollector.java,v 1.1 2004/06/19 02:10:34 vpj-cd Exp $
 *  
 *  @author Teo Sarca, www.arhipac.ro
 */
public class MPPCostCollector extends X_PP_Cost_Collector implements DocAction
{
	private static final long serialVersionUID = 1L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param PP_Cost_Collector id
	 */
	public MPPCostCollector(Properties ctx, int PP_Cost_Collector_ID, String trxName)
	{
		super (ctx, PP_Cost_Collector_ID,trxName);
		if (PP_Cost_Collector_ID == 0)
		{
			setC_DocType_ID(0);
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
	public MPPCostCollector(Properties ctx, ResultSet rs,String trxName)
	{
		super (ctx, rs, trxName);
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
	private MPPOrder m_pporder = null;
	
	/** Manufacturing Order Activity **/
	private MPPOrderNode m_orderNode = null;
	
	/** Manufacturing Order BOM Line **/
	private MPPOrderBOMLine m_bomLine = null;

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
		
		MPeriod.testPeriodOpen(getCtx(), getMovementDate(), getC_DocTypeTarget_ID());
		//	Convert/Check DocType
		setC_DocType_ID(getC_DocTypeTarget_ID());
		
		//
		// Operation Activity
		if(isCostCollectorType(COSTCOLLECTORTYPE_ActivityControl))
		{
			MPPOrderNode activity = getPP_Order_Node();
			if(MPPOrderNode.DOCACTION_Complete.equals(activity.getDocStatus()))
			{	
				throw new ActivityProcessedException(activity);
			}
			
			if (activity.isSubcontracting())
			{
				if(activity.getDocStatus().equals(MPPOrderNode.DOCSTATUS_InProgress)
						&& getDocStatus().equals(MPPCostCollector.DOCSTATUS_InProgress))
				{			
					return MPPOrderNode.DOCSTATUS_InProgress;
				}
				else if(activity.getDocStatus().equals(MPPOrderNode.DOCSTATUS_InProgress)
						&& getDocStatus().equals(MPPCostCollector.DOCSTATUS_Drafted))
				{
					throw new ActivityProcessedException(activity);
				}				
				createPOrder(activity);
			}
			
			activity.setDocStatus(DOCSTATUS_InProgress);
			activity.setQtyDelivered(activity.getQtyDelivered().add(getMovementQty()));
			activity.setQtyScrap(activity.getQtyScrap().add(getScrappedQty()));
			activity.setQtyReject(activity.getQtyReject().add(getQtyReject()));
			activity.setDurationReal(activity.getDurationReal()+getDurationReal().intValue());
			activity.setSetupTimeReal(activity.getSetupTimeReal()+getSetupTimeReal().intValue());
			activity.saveEx();

			if (MPPOrderNode.isLastNode(getCtx(), getPP_Order_Node_ID(), get_TrxName()))
			{
				final String whereClause ="PP_Order_ID=? AND PP_Order_Node_ID<>?"
									+" AND NOT EXISTS (SELECT 1 FROM PP_Cost_Collector cc "
									+" WHERE cc.PP_Order_ID=PP_Order_Node.PP_Order_ID"
											+" AND cc.PP_Order_Node_ID=PP_Order_Node.PP_Order_Node_ID)";
				List<MPPOrderNode> nodes = new Query(getCtx(), MPPOrderNode.Table_Name, whereClause, get_TrxName())
				.setParameters(new Object[]{getPP_Order_ID(),getPP_Order_Node_ID()})
				.setOnlyActiveRecords(true)
				.list();
				for (MPPOrderNode node : nodes)
				{
					createNewNode(node);  
				}
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
		
		BigDecimal QtyIssue = Env.ZERO;
		BigDecimal QtyReceipt = Env.ZERO;
		BigDecimal Qty = getMovementQty();
		
		if (isIssue())
		{	
			Qty = Qty.negate();
			QtyIssue = Qty;
		}
		else
		{
			QtyReceipt = Qty.negate();
		}

		//
		//	Update Order Line
		if(!isCostCollectorType(COSTCOLLECTORTYPE_ActivityControl))
		{
			//	Stock Movement 
			MProduct product = MProduct.get(getCtx(), getM_Product_ID());
			if (product != null	&& product.isStocked())
			{
				// Only for Production Issue records
				if (isIssue())
				{
					checkMaterialPolicy(getPP_Order_BOMLine());
				}

				log.fine("Material Transaction");
				MTransaction mtrx = null; 
				int reservationAttributeSetInstance_ID = getM_AttributeSetInstance_ID();

				if (getM_AttributeSetInstance_ID() == 0)
				{
					for (MPPOrderBOMLineMA ma : MPPOrderBOMLineMA.get(getCtx(), getPP_Order_Node_ID(), get_TrxName()))
					{
						BigDecimal QtyMA = ma.getMovementQty();
						if (isIssue())
						{	
							QtyMA = QtyMA.negate();
							QtyIssue = QtyMA;
						}
						else
						{
							QtyReceipt = QtyMA.negate();
						}

						if (!MStorage.add(getCtx(), getM_Warehouse_ID(),
								getM_Locator_ID(),
								getM_Product_ID(), 
								ma.getM_AttributeSetInstance_ID(), reservationAttributeSetInstance_ID,
								QtyMA, QtyIssue, QtyReceipt, get_TrxName()))
						{
							throw new AdempiereException(); //Cannot correct Inventory (MA)
						}

						//	Create Transaction
						mtrx = new MTransaction (getCtx(), this.getAD_Org_ID(),
								getMovementType(), getM_Locator_ID(),
								getM_Product_ID(), ma.getM_AttributeSetInstance_ID(), 
								QtyMA, getMovementDate(), get_TrxName());                                                                                                           
						mtrx.setPP_Order_ID(getPP_Order_ID());
						mtrx.setPP_Order_BOMLine_ID(getPP_Order_BOMLine_ID());
						mtrx.saveEx();
					}
				}
				//	sLine.getM_AttributeSetInstance_ID() != 0
				if (mtrx == null)
				{
					//	Fallback: Update Storage -
					if (!MStorage.add(getCtx(), getM_Warehouse_ID(), 
							getM_Locator_ID(),
							getM_Product_ID(), 
							getM_AttributeSetInstance_ID(), reservationAttributeSetInstance_ID,
							Qty, QtyIssue, QtyReceipt, get_TrxName()))
					{
						throw new AdempiereException(); // Cannot correct Inventory;
					}
					//	FallBack: Create Transaction

					mtrx = new MTransaction (getCtx(),getAD_Org_ID(),
							getMovementType(), getM_Locator_ID(),
							getM_Product_ID(), getM_AttributeSetInstance_ID(),
							Qty, getMovementDate(), get_TrxName());
					mtrx.setPP_Order_ID(getPP_Order_ID());
					mtrx.setPP_Order_BOMLine_ID(getPP_Order_BOMLine_ID());
					mtrx.saveEx();
				}										
			}	//	stock movement


			if (isIssue())				
			{
				//	Update PP Order Line
				MPPOrderBOMLine obomline = getPP_Order_BOMLine();
				obomline.setQtyDelivered(obomline.getQtyDelivered().add(getMovementQty()));
				obomline.setQtyScrap(obomline.getQtyScrap().add(getScrappedQty()));
				obomline.setQtyReject(obomline.getQtyReject().add(getQtyReject()));  
				obomline.setDateDelivered(getMovementDate());	//	overwrite=last	
				obomline.setM_AttributeSetInstance_ID(getM_AttributeSetInstance_ID());
				log.fine("OrderLine - Reserved=" + obomline.getQtyReserved() + ", Delivered=" + obomline.getQtyDelivered());				
				obomline.setQtyReserved(obomline.getQtyReserved().subtract(getMovementQty()));
				obomline.saveEx();
				log.fine("OrderLine -> Reserved="+obomline.getQtyReserved()+", Delivered="+obomline.getQtyDelivered());
			}
			if (isReceipt())
			{
				//	Update PP Order Qtys 
				MPPOrder order = getPP_Order();
				order.setQtyDelivered(order.getQtyDelivered().add(getMovementQty()));                
				order.setQtyScrap(order.getQtyScrap().add(getScrappedQty()));
				order.setQtyReject(order.getQtyReject().add(getQtyReject()));                
				order.setQtyReserved(order.getQtyReserved().subtract(getMovementQty()));
				//
				// Update PP Order Dates
				order.setDateDelivered(getMovementDate()); //	overwrite=last
				if (order.getDateStart() == null)
				{
					order.setDateStart(getMovementDate());
				}
				if (order.getQtyOpen().signum() <= 0)
				{
					order.setDateFinish(getMovementDate());
				}
				order.saveEx();
			}
		}
		else if(isCostCollectorType(COSTCOLLECTORTYPE_ActivityControl))
		{
			MPPOrderNode activity = getPP_Order_Node();
			if(activity.getDocStatus().equals(MPPOrderNode.DOCSTATUS_Completed))
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
				StringBuffer msg = new StringBuffer("The quantity do not is complete for next Purchase Order :");
				for (MOrderLine oline : olines)
				{
					if(oline.getQtyDelivered().compareTo(oline.getQtyOrdered()) < 0)
					{
						DocStatus = MPPOrderNode.DOCSTATUS_InProgress;
					}
					msg.append(oline.getParent().getDocumentNo()).append(",");
				}
				
				if(DocStatus.equals(MPPOrderNode.DOCSTATUS_InProgress))
				{	
					m_processMsg = msg.toString();
					return DocStatus;
				}
				setProcessed(true);
				setDocAction(DOCACTION_Close);
				setDocStatus(DOCSTATUS_Completed);
				activity.setDocStatus(DOCSTATUS_Completed);
				activity.saveEx();
				
				setDocAction(DOCACTION_Complete);
				setDocStatus(DOCSTATUS_InProgress);
				return DOCSTATUS_InProgress;
			}
			else
			{
				if(activity.getQtyDelivered().compareTo(activity.getQtyRequiered()) >= 0)
				{
					activity.setDocStatus(DOCSTATUS_Completed);
					activity.saveEx();									
				}
			}
		} // end Activity Control	
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

		//	Close Not delivered Qty
		// fjviejo e-evolution operation activity
		// last node translates to => I complete node
		if(!MPPOrderNode.isLastNode(getCtx(), getPP_Order_Node_ID(), get_TrxName()))
		{
			MPPOrderNode onodeact = new MPPOrderNode(Env.getCtx(),getPP_Order_Node_ID(),get_TrxName());
			onodeact.setDocStatus(DOCSTATUS_Closed);
			//   onodeact.setAction(DOCACTION_None);
			onodeact.saveEx();
			
			final String whereClause = COLUMNNAME_PP_Order_ID+"=? AND "+COLUMNNAME_PP_Order_Node_ID+"=?";
			List<MPPCostCollector> list = new Query(getCtx(), Table_Name, whereClause, get_TrxName())
											.setOnlyActiveRecords(true)
											.setParameters(new Object[]{getPP_Order_ID(), getPP_Order_Node_ID()})
											.list();
			for (MPPCostCollector cc : list)
			{
				cc.setDocStatus(DOCSTATUS_Closed);
				cc.setDocAction(DOCACTION_None);
				cc.saveEx();
			}
		}
		else
		{
			final String whereClause = MPPOrderNode.COLUMNNAME_PP_Order_ID+"=?"
					+" AND ("+MPPOrderNode.COLUMNNAME_DocStatus+" IS NULL OR "+MPPOrderNode.COLUMNNAME_DocStatus+"<>?)";
			List<MPPOrderNode> list = new Query(getCtx(), MPPOrderNode.Table_Name, whereClause, get_TrxName())
											.setOnlyActiveRecords(true)
											.setParameters(new Object[]{getPP_Order_ID(), MPPOrderNode.DOCSTATUS_Closed})
											.list();
			for (MPPOrderNode onode : list)
			{
				onode.setDocStatus(DOCSTATUS_Closed);
				onode.saveEx();
			}
			closeNew(getPP_Order_ID(), getPP_Order_Node_ID());
		}
		// fjviejo e-evolution operation activity end
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
	public int getC_Currency_ID()
	{
		return 0;
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
 
	private void createNewNode(MPPOrderNode node)
	{
		String whereClause = COLUMNNAME_PP_Order_ID+"=? AND "+COLUMNNAME_PP_Order_Node_ID+"=?";
		boolean exists = new Query(getCtx(), Table_Name, whereClause, get_TrxName())
								.setOnlyActiveRecords(true)
								.setParameters(new Object[]{getPP_Order_ID(), node.getPP_Order_Node_ID()})
								.match();
		if (!exists)
		{
			MPPCostCollector costnew = new MPPCostCollector(getCtx(), 0, get_TrxName());
			costnew.setPP_Order_ID(getPP_Order_ID());
			costnew.setC_DocTypeTarget_ID(getC_DocTypeTarget_ID());
			costnew.setC_DocType_ID(getC_DocType_ID());
			costnew.setS_Resource_ID(getS_Resource_ID());
			costnew.setM_Warehouse_ID(getM_Warehouse_ID());
			costnew.setM_Locator_ID(getM_Locator_ID());
			costnew.setM_Product_ID(getM_Product_ID());
			costnew.setM_AttributeSetInstance_ID(getM_AttributeSetInstance_ID());
			costnew.setPP_Order_Workflow_ID(getPP_Order_Workflow_ID());
			costnew.setAD_User_ID(getAD_User_ID());
			costnew.setMovementDate(getMovementDate());
			costnew.setDateAcct(getDateAcct());
			costnew.setMovementQty(getMovementQty());
			//costnew.setDurationUnit(getDurationUnit());
			costnew.setCostCollectorType(getCostCollectorType());
			costnew.setPP_Order_Node_ID(node.getPP_Order_Node_ID());
			costnew.setDurationReal(new BigDecimal(node.getDuration()));
			costnew.saveEx();
			//    costnew.completeIt();
		}
		else
		{
			log.warning("***** NODE already exists");
		}
		//completenew(getPP_Order_ID(),node);
	}

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
		if (isCostCollectorType(COSTCOLLECTORTYPE_ActivityControl) && getPP_Order_Node_ID() <= 0)
		{
			throw new FillMandatoryException(COLUMNNAME_PP_Order_Node_ID);
		}
		//
		if (isIssue() && getPP_Order_BOMLine_ID() <= 0)
		{
			throw new FillMandatoryException(COLUMNNAME_PP_Order_BOMLine_ID);
		}
		//
		return true;
	}

	private void closeNew(int PP_Order_ID, int PP_Order_Node_ID)
	{
		if(isCostCollectorType(COSTCOLLECTORTYPE_ActivityControl))
		{
			String whereClause = COLUMNNAME_PP_Order_ID+"=?";
			List<MPPCostCollector> list = new Query(getCtx(), Table_Name, whereClause, get_TrxName())
												.setOnlyActiveRecords(true)
												.setParameters(new Object[]{PP_Order_ID})
												.list();
			for (MPPCostCollector cc : list)
			{
				cc.setDocStatus(DOCSTATUS_Closed);
				cc.setDocAction(DOCACTION_None);
				cc.saveEx();
			}
		}
	}

	@Deprecated
	private void completeNew(int PP_Order_ID)
	{
		if(isCostCollectorType(COSTCOLLECTORTYPE_ActivityControl))
		{
			String whereClause = COLUMNNAME_PP_Order_ID+"=?"
			+" AND "+COLUMNNAME_DocStatus+"<>'"+DOCSTATUS_Completed+"'"
			+" AND "+COLUMNNAME_DocStatus+"<>'"+DOCSTATUS_Closed+"'";
			List<MPPCostCollector> list = new Query(getCtx(), Table_Name, whereClause, get_TrxName())
			.setOnlyActiveRecords(true)
			.setParameters(new Object[]{PP_Order_ID})
			.list();
			for (MPPCostCollector cc : list)
			{
				cc.completeIt();
				cc.saveEx();
			}
		}
	}

	/**
	 * Check Material Policy.
	 * Sets line ASI
	 */
	private void checkMaterialPolicy(MPPOrderBOMLine line)
	{
		MPPOrderBOMLineMA.deleteOrderBOMLineMA(line.get_ID(), get_TrxName());

		//	is ASI != 0, MA is not needed
		if (line.getM_AttributeSetInstance_ID() != 0)
		{
			return;
		}

		BigDecimal qty = getMovementQty();
		BigDecimal qtyASI = Env.ZERO ;

		//	Check Line
		MProduct product = MProduct.get(getCtx(), line.getM_Product_ID());
		if (qty.signum() > 0)	//	Incoming Trx -> Create new ASI
		{
			MAttributeSetInstance asi = new MAttributeSetInstance(getCtx(), 0, get_TrxName());
			asi.setM_AttributeSet_ID(product.getM_AttributeSet_ID());
			asi.saveEx();
			line.setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
			line.saveEx();
		}
		else	//	Outgoing Trx
		{
			String MMPolicy = product.getMMPolicy();
			MStorage[] storages = MStorage.getAllWithASI(getCtx(), 
					line.getM_Product_ID(),	line.getM_Locator_ID(), 
					MClient.MMPOLICY_FiFo.equals(MMPolicy), get_TrxName());
			BigDecimal qtyToDeliver = qty.negate();

			for (MStorage storage : storages)
			{
				//consume ASI Zero
				if (storage.getM_AttributeSetInstance_ID() == 0)
				{
					qtyASI = qtyASI.add(storage.getQtyOnHand());
					qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand());
				}
				else if (storage.getQtyOnHand().compareTo(qtyToDeliver) >= 0)
				{
					MPPOrderBOMLineMA ma = new MPPOrderBOMLineMA (line, 
							storage.getM_AttributeSetInstance_ID(),
							qtyToDeliver);
					ma.saveEx();
					qtyToDeliver = Env.ZERO;
					log.fine( ma + ", QtyToDeliver=" + qtyToDeliver);
					break;
				}
				else
				{	
					MPPOrderBOMLineMA ma = new MPPOrderBOMLineMA (line, 
							storage.getM_AttributeSetInstance_ID(),
							storage.getQtyOnHand());
					ma.saveEx();
					qtyToDeliver = qtyToDeliver.subtract(storage.getQtyOnHand());
					log.fine( ma + ", QtyToDeliver=" + qtyToDeliver);		
				}
			} // for storage

			//	No AttributeSetInstance found for remainder
			BigDecimal qtyRemaining = qtyToDeliver.add(qtyASI);
			if (qtyRemaining.signum() != 0)
			{
				MPPOrderBOMLineMA ma = new MPPOrderBOMLineMA (line, 0 , qtyRemaining);
				ma.saveEx();
				log.fine("##: " + ma);
			}
		}	//	outgoing Trx
	}	//	checkMaterialPolicy

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
		m_orderNode.set_TrxName(get_TrxName());
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
			m_pporder = null;
			return null;
		}
		if (m_pporder == null || m_pporder.get_ID() != id)
		{
			m_pporder = new MPPOrder(getCtx(), id, get_TrxName());
		}
		m_pporder.set_TrxName(get_TrxName());
		return m_pporder;
	}

	
	/**
	 * Create Purchase Order (in case of Subcontracting)
	 * @param activity
	 */
	private void createPOrder(MPPOrderNode activity)
	{
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
			if(product.isPurchased() && MProduct.PRODUCTTYPE_Service.equals(product.getProductType()))
			{
				continue;
			}
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
				order.setBPartner(vendor);
				order.setIsSOTrx(false);
				order.setC_DocTypeTarget_ID();
				order.setDatePromised(datePromised);
				order.setDescription(getPP_Order().getDocumentNo());
				order.setDocStatus(MOrder.DOCSTATUS_Drafted);
				order.setDocAction(MOrder.DOCACTION_Complete);
				order.setAD_User_ID(getAD_User_ID());
				//order.setSalesRep_ID(getAD_User_ID());
				order.saveEx();
				addDescription(Msg.translate(getCtx(), "C_Order_ID")+": "+order.getDocumentNo());
				orders.put(C_BPartner_ID, order);
			}
			//
			// Create Order Line: 
			BigDecimal QtyOrdered = subcontract.getQty();
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
	}
	
	@Override
	public I_M_Product getM_Product()
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
		return isCostCollectorType(COSTCOLLECTORTYPE_ComponentIssue);
	}
	
	public boolean isReceipt()
	{
		return isCostCollectorType(COSTCOLLECTORTYPE_MaterialReceipt);
	}
	
	public String getMovementType()
	{
		if (isCostCollectorType(COSTCOLLECTORTYPE_MaterialReceipt))
			return MTransaction.MOVEMENTTYPE_WorkOrderPlus;
		else if(isCostCollectorType(COSTCOLLECTORTYPE_ComponentIssue))
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
	
	/**
	 * 	Test
	 *	@param args ignored
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 *
	public static void main (String[] args) throws FileNotFoundException, IOException
	{

		// Test: General
		//Properties testProperties = null;

		// Test Context
		Properties m_Ctx = null;

		// IsClient
		String isClient_Key = "isClient";
		String isClient_DefaultValue = "Y";
		boolean isClient_Value = true;

		// AD_User
		final String AD_User_ID_Key = "AD_User_ID";
		String AD_User_ID_DefaultValue = "100"; //SuperUser
		int AD_User_ID_Value = 0;

		// AD_Client
		final String AD_Client_ID_Key = "AD_Client_ID";
		String AD_Client_ID_DefaultValue = "11"; //GardenWorld
		int AD_Client_ID_Value = 11;

		// LogLevel:
	    final String LogLevel_Key = "LogLevel";
	    String LogLevel_DefaultValue = Level.FINEST.toString();
		Level LogLevel_Value = Level.FINEST;

		// Trx name
		String trxName = "test";
	

		Ini.setClient (isClient_Value);
		Adempiere.startup(isClient_Value);
		
		m_Ctx = Env.getCtx();
		m_Ctx.setProperty("#AD_User_ID", new Integer(AD_User_ID_Value).toString());
		m_Ctx.setProperty("#AD_Client_ID", new Integer(AD_Client_ID_Value).toString());

//		if (fileName_Value.length() < 1) {
//			throw new AdempiereException("Please specify path to Adempiere.properties file!");
//		}
//
//		System.setProperty("PropertyFile", fileName_Value);


		//CLogMgt.setLevel(LogLevel_Value);
		CLogMgt.setLevel(Level.ALL);
		List <MPPCostCollector> ccs =new Query(m_Ctx, MPPCostCollector.Table_Name, "", null ).list();
		for (MPPCostCollector cc : ccs)
		{
			System.out.println("Cost Collector" + cc.getDocumentNo());
			MAcctSchema[] m_ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), 11);
			Doc doc = Doc.get (m_ass, MPPCostCollector.Table_ID, cc.get_ID(), null);
			if (doc == null)
			{
				throw new AdempiereException("Documento no creado");
			}
			else
			{
				String error = doc.post(true, true);   //  post no force/repost
			}
		}
		
	}
	/**/
}	//	MPPCostCollector
