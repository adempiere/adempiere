/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Victor Perez	                                      * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Victor Perez (victor.perez@e-evolution.com	 )                *
 *                                                                    *
 * Sponsors:                                                          *
 *  - e-Evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/

package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.NoVendorForProductException;
import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.MQuery;
import org.compiere.model.MRefList;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MStorage;
import org.compiere.model.MTable;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.model.PrintInfo;
import org.compiere.model.Query;
import org.compiere.model.X_C_BP_Group;
import org.compiere.model.X_C_DocType;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.wf.MWorkflow;
import org.eevolution.engines.Warehouse.WMRuleEngine;
import org.eevolution.exceptions.NoBPartnerLinkedforOrgException;
import org.eevolution.exceptions.NoPlantForWarehouseException;
import org.eevolution.model.I_WM_InOutBoundLine;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPOrderBOM;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductPlanning;
import org.eevolution.model.MWMInOutBound;
import org.eevolution.model.MWMInOutBoundLine;
import org.eevolution.model.X_DD_Order;
import org.eevolution.model.X_WM_InOutBound;

/**
 *	
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  @version $Id: $
 */
public class ReleaseInOutBound extends SvrProcess
{	
	/** Record ID */
	protected int p_Record_ID = 0;	
	protected int p_M_Locator_ID = 0;
	protected String p_DocAction = null;
	protected String p_DeliveryRule = null;
	protected int p_C_DocType_ID = 0;
	protected int p_WM_Area_Type_ID = 0;
	protected int p_WM_Section_Type_ID = 0;
	protected boolean p_IsPrintPickList = false;
	protected boolean p_IsCreateSupply = false;
	
	private MLocator m_locator = null;
	private int AD_User_ID = 0;
	private Timestamp Today = new Timestamp (System.currentTimeMillis()); 
	private MDDOrder order = null;
	
	/**
	 * 	Get Parameters
	 */
	@Override
	protected void prepare ()
	{
		AD_User_ID = Env.getAD_User_ID(getCtx());
		p_Record_ID = getRecord_ID();
		for (ProcessInfoParameter para:getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals("WM_Area_Type_ID"))
			{
				p_WM_Area_Type_ID = para.getParameterAsInt();
			}
			else if (name.equals("WM_Section_Type_ID"))
			{
				p_WM_Section_Type_ID = para.getParameterAsInt();
			}
			else if (name.equals("DeliveryRule"))
			{
				p_DeliveryRule = (String)para.getParameter();
			}
			else if (name.equals("DocAction"))
			{
				p_DocAction = (String)para.getParameter();
			}
			else if (name.equals("C_DocType_ID"))
			{
				p_C_DocType_ID = para.getParameterAsInt();
			}
			else if (name.equals("M_Locator_ID"))
			{
				p_M_Locator_ID = para.getParameterAsInt();
				m_locator = new MLocator(getCtx(), p_M_Locator_ID, get_TrxName());
			}
			else if (name.equals("IsPrintPickList"))
			{
				p_IsPrintPickList = "Y".equals(para.getParameter());
			}
			else if (name.equals("IsCreateSupply"))
			{
				p_IsCreateSupply = "Y".equals(para.getParameter());
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	/**
	 * 	Process - Generate Export Format
	 *	@return info
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected String doIt () throws Exception
	{
		String whereClause = "EXISTS (SELECT T_Selection_ID FROM T_Selection WHERE  T_Selection.AD_PInstance_ID=? AND T_Selection.T_Selection_ID=WM_InOutBoundLine.WM_InOutboundLine_ID)";		
		Collection <MWMInOutBoundLine> lines = new Query(getCtx(), I_WM_InOutBoundLine.Table_Name, whereClause, get_TrxName())
										.setClient_ID()
										.setParameters(new Object[]{getAD_PInstance_ID()})
										.list();
		
		int seq = 10;
		for (MWMInOutBoundLine boundline: lines)
		{
			MWMInOutBound bound = boundline.getParent();
			if(X_WM_InOutBound.DOCSTATUS_Completed.equals(bound.getDocStatus()) 
			|| X_WM_InOutBound.DOCSTATUS_Closed.equals(bound.getDocStatus())
			|| X_WM_InOutBound.DOCSTATUS_Voided.equals(bound.getDocStatus()))
			{
				continue;
			}
			
			if(boundline.isProcessed())
			{
				continue;
			}
			/*else
			{
				bound.completeIt();
				bound.saveEx();
			}*/
			
			BigDecimal QtySupply = createDDOrder(boundline);
			
			if(p_IsCreateSupply && QtySupply.signum() > 0)
			{
				Env.setContext(boundline.getCtx(),"IsCreateSupply", "Y");
				createSupply(boundline, QtySupply);
			}
			seq ++;
		}
		
		if(order != null && p_DocAction != null)
		{
			order.setDocAction(p_DocAction);
			order.setDocStatus(DocAction.STATUS_InProgress);
			order.completeIt();
			order.save();
		}	
		
		if(p_IsPrintPickList && order != null)
		{
			// Get Format & Data
			ReportEngine re = this.getReportEngine("DistributionOrder_Header  ** TEMPLATE **","DD_Order_Header_v", order.getDD_Order_ID());
			if(re == null )
			{
				return"";
			}
			//ReportCtl.preview(re);
			re.print(); // prints only original
		}
		
		return "" ;//@DocumentNo@ " + order.getDocumentNo();
	}

	
	/**
	 * create Distribution Order as Pick List
	 * @param boundline Out bound Line
	 * @return Quantity that was not covert for inventory
	 */
	protected BigDecimal createDDOrder(MWMInOutBoundLine boundline)
	{				
		WMRuleEngine engineRule = WMRuleEngine.get();
		Collection<MStorage> storages = engineRule.getMStorage(boundline, p_WM_Area_Type_ID, p_WM_Section_Type_ID);
		
		int M_Shipper_ID = 0;
		BigDecimal qtySupply = Env.ZERO;
		if(storages != null && storages.size() > 0)
		{	
			//get the warehouse in transit
			MWarehouse[] wsts = MWarehouse.getInTransitForOrg(getCtx(), m_locator.getAD_Org_ID());
			if (wsts == null || wsts.length == 0)
			{	
				throw new AdempiereException("Do not exist Transit Warehouse");
			}

			//Org Must be linked to BPartner
			MOrg org = MOrg.get(getCtx(),  m_locator.getAD_Org_ID());
			int C_BPartner_ID = org.getLinkedC_BPartner_ID(get_TrxName()); 
			if (C_BPartner_ID == 0)
			{
				throw new NoBPartnerLinkedforOrgException (org);
			}
				
			MBPartner bp = MBPartner.get(getCtx(), C_BPartner_ID);
			
			if(order == null)
			{
				order = new MDDOrder(getCtx() , 0 , get_TrxName());
				order.setAD_Org_ID(m_locator.getAD_Org_ID());
				order.setC_BPartner_ID(C_BPartner_ID);
				if(p_C_DocType_ID > 0)
				{	
					order.setC_DocType_ID(p_C_DocType_ID);
				}	
				else
				{
					order.setC_DocType_ID(MDocType.getDocType(X_C_DocType.DOCBASETYPE_DistributionOrder));
				}
				
				order.setM_Warehouse_ID(wsts[0].get_ID());
				if(p_DocAction != null)
				{	
					order.setDocAction(p_DocAction);
				}
				else
				{
					order.setDocAction(X_DD_Order.DOCACTION_Prepare);
				}
				
				MUser[] users = MUser.getOfBPartner(getCtx(), bp.getC_BPartner_ID(), get_TrxName());
				if (users == null || users.length == 0)
				{	
					throw new AdempiereException("Do not exist Users for this Business Partner"+ bp.getName());						
				}
				
				order.setDateOrdered(getToday());                       
				order.setDatePromised(getToday());
		
				order.setAD_User_ID(users[0].getAD_User_ID());
				order.setM_Shipper_ID(M_Shipper_ID);	    	                
				order.setIsInDispute(false);
				order.setIsInTransit(false);
				order.setSalesRep_ID(bp.getPrimaryAD_User_ID());
				order.saveEx();
			}

			for (MStorage storage: storages)
			{			
				MDDOrderLine oline = new MDDOrderLine(order);
				oline.setM_Locator_ID(storage.getM_Locator_ID());
				oline.setM_LocatorTo_ID(p_M_Locator_ID);
				oline.setC_UOM_ID(boundline.getC_UOM_ID());
				oline.setM_Product_ID(boundline.getM_Product_ID());
				oline.setDateOrdered(getToday());                       
				oline.setDatePromised(boundline.getPickDate());
				oline.set_ValueOfColumn(I_WM_InOutBoundLine.COLUMNNAME_WM_InOutBoundLine_ID, boundline.getWM_InOutBoundLine_ID());
				oline.setIsInvoiced(false);
			
				
				if (boundline.getQtyToPick().subtract(qtySupply).compareTo(storage.getQtyOnHand()) < 0)
				{
					oline.setConfirmedQty(boundline.getQtyToPick());
					oline.setQtyEntered(boundline.getQtyToPick());
					oline.setQtyOrdered(boundline.getQtyToPick());
					oline.setTargetQty(boundline.getQtyToPick());
					qtySupply = qtySupply.add(boundline.getQtyToPick());					
				}
				else
				{
					oline.setConfirmedQty(storage.getQtyOnHand());
					oline.setQtyEntered(storage.getQtyOnHand());
					oline.setQtyOrdered(storage.getQtyOnHand());
					oline.setTargetQty(storage.getQtyOnHand());						
					qtySupply = qtySupply.add(storage.getQtyOnHand());				
				}				
				
				oline.saveEx();
			}
		}
		else
		{
			qtySupply = boundline.getQtyToPick().subtract(qtySupply);
		}
		return qtySupply;
	}
	
	/**
	 * Create supply based in Out bound Line 
	 * @param boundline  Out bound Line 
	 * @param qtySupply Quantity Supply
	 */
	public void createSupply(MWMInOutBoundLine boundline, BigDecimal qtySupply)
	{
		MProduct product = MProduct.get(boundline.getCtx(), boundline.getM_Product_ID());
		if (product.isBOM())
		{			
			createMO(boundline, product , qtySupply);
		}
		else if(product.isPurchased())
		{
			createRequisition(boundline, product, qtySupply);
		}
	}
	
	/**
	 * Create Requisition when the Is create supply is define as yes
	 * @param product Product
	 * @param QtyPlanned Qty Planned
	 */
	public  void createRequisition(MWMInOutBoundLine boundline, MProduct product, BigDecimal QtyPlanned)
	{
		//s_log.info("Create Requisition");
		int C_BPartner_ID = 0;
		int M_PriceList_ID = 0;
		MProductPO po = null;
		MProductPO[] ppos = MProductPO.getOfProduct(getCtx(), product.getM_Product_ID(), null);
		for (MProductPO ppo : ppos)
		{
			if (ppo.isCurrentVendor() && ppo.getC_BPartner_ID() != 0)
			{
				C_BPartner_ID = ppo.getC_BPartner_ID();
				po = ppo;
				break;
			}
		}
		
		if (C_BPartner_ID == 0 && ppos.length > 0)
		{
			C_BPartner_ID = ppos[0].getC_BPartner_ID();
		}
		if (C_BPartner_ID == 0)
		{
			throw new NoVendorForProductException(product.getName());
		}
		
		final String sql = "SELECT COALESCE(bp."+MBPartner.COLUMNNAME_PO_PriceList_ID
		+",bpg."+X_C_BP_Group.COLUMNNAME_PO_PriceList_ID+")"
		+" FROM C_BPartner bp"
		+" INNER JOIN C_BP_Group bpg ON (bpg.C_BP_Group_ID=bp.C_BP_Group_ID)"
		+" WHERE bp.C_BPartner_ID=?";
		M_PriceList_ID = DB.getSQLValueEx(get_TrxName(), sql, C_BPartner_ID);

		MRequisition req = new  MRequisition(getCtx(),0, get_TrxName()); 
		req.setAD_Org_ID(m_locator.getAD_Org_ID());
		req.setAD_User_ID(AD_User_ID);                                                        
		req.setDateRequired(boundline.getPickDate());
		req.setDescription("Generate from Outbound Order"); // TODO: add translation
		req.setM_Warehouse_ID(m_locator.getM_Warehouse_ID());
		req.setC_DocType_ID(MDocType.getDocType(MDocType.DOCBASETYPE_PurchaseRequisition));
		if (M_PriceList_ID > 0)
			req.setM_PriceList_ID(M_PriceList_ID);
		req.saveEx();

		MRequisitionLine reqline = new  MRequisitionLine(req);
		reqline.setLine(10);
		reqline.setAD_Org_ID(m_locator.getAD_Org_ID());
		reqline.setC_BPartner_ID(C_BPartner_ID);
		reqline.setM_Product_ID(product.getM_Product_ID());
		reqline.setPrice();
		reqline.setPriceActual(Env.ZERO);
		reqline.setQty(QtyPlanned);
		reqline.saveEx();
		
		MOrderLine oline = new MOrderLine(getCtx(), boundline.getC_OrderLine_ID(), get_TrxName());
		oline.setDescription(oline.getDescription() 
				+ " "
				+ Msg.translate(getCtx(),MRequisition.COLUMNNAME_M_Requisition_ID) 
				+ " : "
				+ req.getDocumentNo());
		oline.saveEx();
		
		boundline.setDescription(boundline.getDescription()
				+ " "
				+ Msg.translate(boundline.getCtx(), MRequisition.COLUMNNAME_M_Requisition_ID) 
				+ " : "
				+ req.getDocumentNo());
	}
	
	/**
	 * Create Manufacturing Order when the Is create supply is define as yes
	 * @param boundline Bound Line
	 * @param product Product
	 * @param qtySupply Quantity to Supply
	 */
	public void createMO(MWMInOutBoundLine boundline, MProduct product, BigDecimal qtySupply)
	{
		MPPOrder order = MPPOrder.forC_OrderLine_ID(boundline.getCtx(), boundline.getC_OrderLine_ID(), boundline.get_TrxName());
		if(order == null)
		{	
			MPPProductBOM bom = MPPProductBOM.getDefault(product, get_TrxName());
			if (bom != null) 
			{		
				MPPProductPlanning pp = null;
				//Validate the BOM based in planning data 
				if(bom == null)
				{
					pp = MPPProductPlanning.find(getCtx(), boundline.getAD_Org_ID(), 0, 0, product.getM_Product_ID(), null); 
					if(pp != null)
					{	
						bom = (MPPProductBOM) pp.getPP_Product_BOM();
					}
				}
				if (bom != null) 
				{		  
					final int plant_id = MPPProductPlanning.getPlantForWarehouse(boundline.getM_Warehouse_ID());
					if(plant_id <= 0)
					{
						throw new NoPlantForWarehouseException(boundline.getM_Warehouse_ID());
					}
					MWorkflow workflow = MWorkflow.get(getCtx(), MWorkflow.getWorkflowSearchKey(product));
					//Validate the workflow based in planning data 						
					if(workflow == null && pp != null)
					{
						workflow = pp.getAD_Workflow();
					}
					
					if (plant_id > 0 && workflow != null)
					{
						String description = Msg.translate(getCtx(), MWMInOutBound.COLUMNNAME_WM_InOutBound_ID) 
						+ " : "
						+ boundline.getParent().getDocumentNo();
						
						//Create temporary Product Planning to Create Manufacturing Order 
						pp = new MPPProductPlanning(getCtx(), 0 , get_TrxName());
						pp.setAD_Org_ID(boundline.getAD_Org_ID());
						pp.setM_Product_ID(product.getM_Product_ID());
						pp.setPlanner_ID(boundline.getParent().getSalesRep_ID());
						pp.setPP_Product_BOM_ID(bom.getPP_Product_BOM_ID());
						pp.setAD_Workflow_ID(workflow.getAD_Workflow_ID());
						pp.setM_Warehouse_ID(boundline.getM_Warehouse_ID());
						pp.setS_Resource_ID(plant_id);
						
						order = MPPMRP.createMO( pp, 
										boundline.getC_OrderLine_ID(),
										boundline.getM_AttributeSetInstance_ID(), 
										qtySupply, 
										boundline.getPickDate(), 
										boundline.getShipDate(),
										description
										);
						MOrderLine oline = new MOrderLine(getCtx(), boundline.getC_OrderLine_ID(), get_TrxName());
						
						description = "";
						if(oline.getDescription() != null)
							description = oline.getDescription();
						
						description = description 
									+ " "
									+ Msg.translate(oline.getCtx(), MPPOrder.COLUMNNAME_PP_Order_ID) 
									+ " : "
								    + order.getDocumentNo();
						oline.setDescription(description);
						oline.saveEx();
						
						String boundDescription = "";
						if(boundline.getDescription() != null)
							boundDescription = boundline.getDescription();
						
						boundDescription = boundDescription 
										 + " "
										 + Msg.translate(getCtx(), MPPOrder.COLUMNNAME_PP_Order_ID) 
										 + " : "
										 + order.getDocumentNo();
						boundline.setDescription(boundDescription);
					}
				}	
			}	
		}	
		if(order != null)
		{	
			boundline.setProcessed(true);
			boundline.saveEx();
		}
	}
	
	/*
	 * get the a Report Engine Instance using the view table 
	 * @param tableName
	 */
	private ReportEngine getReportEngine(String formatName, String tableName,int record_id)
	{
		// Get Format & Data
		int format_id= MPrintFormat.getPrintFormat_ID(formatName, MTable.getTable_ID(tableName), getAD_Client_ID());
		MPrintFormat format = MPrintFormat.get(getCtx(), format_id, true);
		if (format == null)
		{
			addLog("@NotFound@ @AD_PrintFormat_ID@");
			return null;
		}
		// query
		MQuery query = new MQuery(tableName);
		query.addRestriction("DD_Order_ID", MQuery.EQUAL, record_id);
		// Engine
		PrintInfo info = new PrintInfo(tableName,  MTable.getTable_ID(tableName), record_id);
		ReportEngine re = new ReportEngine(getCtx(), format, query, info);
		return re;
	}
	
	/**
	 * get Today
	 * @return Today
	 */
	protected Timestamp getToday()
	{
		return this.Today;
	}
}
