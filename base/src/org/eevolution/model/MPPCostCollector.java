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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MClient;
import org.compiere.model.MDocType;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPeriod;
import org.compiere.model.MProduct;
import org.compiere.model.MStorage;
import org.compiere.model.MTransaction;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.Query;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

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
			setDocAction (DOCACTION_Complete);	// CO
			setDocStatus (DOCSTATUS_Drafted);	// DR
			setMovementDate (new Timestamp(System.currentTimeMillis()));	// @#Date@
			setPosted (false);
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
		
		MPeriod.testPeriodOpen(getCtx(), getMovementDate(), getC_DocType_ID());
		
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
		{
			setDocAction(DOCACTION_Complete);
		}
		
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

		//	Update Order Line
		if(getCostCollectorType() == MPPCostCollector.COSTCOLLECTORTYPE_ActivityControlReport)
		{
			//	Stock Movement 
			MProduct product = MProduct.get(getCtx(), getM_Product_ID());
			if (product != null	&& product.isStocked())
			{
				// Only for Production Issue records
				if (isIssue())
				{
					checkMaterialPolicy(getPP_Order_BOMLine(), getMovementQty());
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
							m_processMsg = "Cannot correct Inventory (MA)";
							return DocAction.STATUS_Invalid;
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
						m_processMsg = "Cannot correct Inventory";
						return DocAction.STATUS_Invalid;
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
				if (obomline != null)
				{
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
			}
			else if  (isReceipt())
			{
				//	Update PP Order 
				MPPOrder order = getPP_Order();
				order.setQtyDelivered(order.getQtyDelivered().add(getMovementQty()));                
				order.setQtyScrap(order.getQtyScrap().add(getScrappedQty()));
				order.setQtyReject(order.getQtyReject().add(getQtyReject()));                
				order.setDateDelivered(getMovementDate());	//	overwrite=last				
				log.fine("OrderLine - Reserved=" + order.getQtyReserved() + ", Delivered=" + order.getQtyDelivered());				
				order.setQtyReserved(order.getQtyReserved().subtract(getMovementQty()));
				order.saveEx();
				log.fine("Order -> Delivered=" + order.getQtyDelivered());										
			}
		}
		// Operation Activity
		if(getCostCollectorType() == MPPCostCollector.COSTCOLLECTORTYPE_ActivityControlReport)
		{
			MPPOrderNode onodeact = getPP_Order_Node();
			onodeact.setDocStatus(DOCSTATUS_Completed);
			onodeact.setQtyScrap(onodeact.getQtyScrap().add(getScrappedQty()));
			onodeact.setQtyReject(onodeact.getQtyReject().add(getQtyReject()));
			onodeact.setQtyDelivered(onodeact.getQtyDelivered().add(getMovementQty()));
			onodeact.setDurationReal(onodeact.getDurationReal()+getDurationReal().intValue());
			onodeact.setSetupTimeReal(onodeact.getSetupTimeReal()+getSetupTimeReal().intValue());
			onodeact.saveEx();

			if (MPPOrderNode.isLastNode(getCtx(), getPP_Order_Node_ID(), get_TrxName()))
			{
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try
				{
					String sql = "SELECT DocStatus,PP_Order_Node_ID,DurationRequiered FROM PP_Order_Node n"
						+ " WHERE IsActive='Y' AND PP_Order_ID=? AND PP_Order_Node_ID<>?"
							+" AND NOT EXISTS (SELECT 1 FROM PP_Cost_Collector cc "
								+" WHERE cc.PP_Order_ID=n.PP_Order_ID AND cc.PP_Order_Node_ID=n.PP_Order_Node_ID)";
								
					pstmt = DB.prepareStatement(sql, get_TrxName());
					pstmt.setInt(1, getPP_Order_ID());
					pstmt.setInt(2, getPP_Order_Node_ID());
					rs = pstmt.executeQuery();
					while (rs.next())
					{
						createNewNode(rs.getInt(2), rs.getBigDecimal(3));  
					}
				}
				catch (SQLException e)
				{
					throw new DBException(e);
				}
				finally
				{
					DB.close(rs, pstmt);
					rs = null; pstmt = null;
				}
			}
			
			if (onodeact.isSubcontracting())
			{
				createPOrder();
			}
		} 

		//	for all lines	
		setProcessed(true);
		setDocAction(DOCACTION_Close);
		setDocStatus(DOCSTATUS_Completed);    //fjv e-evolution set DocStatus
		
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
			String whereClause = MPPOrderNode.COLUMNNAME_PP_Order_ID+"=?"
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

	public String getDocumentNo()
	{
		return "" + get_ID();
	}    

	private void createNewNode(int PP_Order_Node_ID, BigDecimal duration)
	{
		String whereClause = COLUMNNAME_PP_Order_ID+"=? AND "+COLUMNNAME_PP_Order_Node_ID+"=?";
		boolean exists = new Query(getCtx(), Table_Name, whereClause, get_TrxName())
								.setOnlyActiveRecords(true)
								.setParameters(new Object[]{getPP_Order_ID(), PP_Order_Node_ID})
								.match();
		if (!exists)
		{
			MPPCostCollector costnew = new MPPCostCollector(Env.getCtx(), 0, get_TrxName());
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
			//
			costnew.setPP_Order_Node_ID(PP_Order_Node_ID);
			costnew.setDurationReal(duration);
			//
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
		if (newRecord)
		{
			setDocStatus(DOCSTATUS_InProgress);
		}
		return true;
	}

	private void closeNew(int PP_Order_ID, int PP_Order_Node_ID)
	{
		if(getCostCollectorType()==MPPCostCollector.COSTCOLLECTORTYPE_ActivityControlReport)
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

	protected void completeNew(int PP_Order_ID)
	{
		if(getCostCollectorType()==MPPCostCollector.COSTCOLLECTORTYPE_ActivityControlReport)
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
	 * 	Check Material Policy.
	 * 	Sets line ASI
	 */
	private void checkMaterialPolicy(MPPOrderBOMLine line , BigDecimal qty)
	{
		MPPOrderBOMLineMA.deleteOrderBOMLineMA(line.get_ID(), get_TrxName());

		//	is ASI != 0, MA is not needed
		if (line.getM_AttributeSetInstance_ID() != 0)
		{
			return;
		}

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
	
	private MPPOrderNode m_orderNode = null;

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
	private MPPOrderBOMLine m_bomLine = null;
	
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
	private MPPOrder m_pporder = null;

	
	/**
	 * Create Purchase Order (in case of Subcontracting)
	 */
	private void createPOrder()
	{
		// create purchase order on complete
		BigDecimal m_MovementQty = getMovementQty();

		// if(isSubcontracting())

		int M_Product_ID = DB.getSQLValue(get_TrxName(),
				"SELECT M_Product_ID FROM PP_Order_Node WHERE IsActive='Y' AND PP_Order_Node_ID=?",
				getPP_Order_Node_ID());
		if (M_Product_ID <= 0)
		{
			throw new AdempiereException("There is no service associated to this subcontract");
		}
		
		BigDecimal DeliveryTime = DB.getSQLValueBD(get_TrxName(),
				"SELECT DeliveryTime_Promised FROM PP_Product_Planning WHERE IsActive='Y' AND M_Product_ID=?",
				M_Product_ID);

		int C_BPartner_ID = DB.getSQLValue(get_TrxName(),
				"SELECT C_BPartner_ID FROM M_Product_PO WHERE IsActive='Y' AND M_Product_ID=? ",
				M_Product_ID);;
		if (C_BPartner_ID <= 0)
		{
			throw new AdempiereException("There is no vendor associated with this subcontract");
		}

		Timestamp today = new Timestamp(System.currentTimeMillis());
		Timestamp datePromised = TimeUtil.addDays(today, DeliveryTime.intValue()); 

		MOrder order = new MOrder(getCtx(), 0, get_TrxName());
		order.setC_BPartner_ID(C_BPartner_ID);
		order.setIsSOTrx(false);
		order.setC_DocTypeTarget_ID();
		order.setDatePromised(datePromised);
		order.setDescription(getPP_Order().getDocumentNo());
		order.saveEx();

		MOrderLine oline = new MOrderLine(order);
		oline.setM_Product_ID(M_Product_ID);
		oline.setQty(m_MovementQty);
		oline.setDatePromised(datePromised);
		oline.saveEx();
		
		setDescription(order.getDocumentNo());
	}
	
	public boolean isIssue()
	{
		return getMovementType().charAt(1) == '-';
	}
	
	public boolean isReceipt()
	{
		return getMovementType().charAt(1) == '+';
	}
	
	public String getMovementType()
	{
		if (getCostCollectorType()==MPPCostCollector.COSTCOLLECTORTYPE_MaterialReceipt)
			return MTransaction.MOVEMENTTYPE_WorkOrderPlus;
		else if(getCostCollectorType()==MPPCostCollector.COSTCOLLECTORTYPE_ComponentIssue)
			return MTransaction.MOVEMENTTYPE_WorkOrder_;	
		
		return null;
	}
}	//	MPPCostCollector
