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
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MStorage;
import org.compiere.model.MTable;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.model.PrintInfo;
import org.compiere.model.X_C_BP_Group;
import org.compiere.model.X_C_DocType;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.wf.MWorkflow;
import org.eevolution.engine.warehouse.WMRuleEngine;
import org.eevolution.exceptions.NoBPartnerLinkedforOrgException;
import org.eevolution.exceptions.NoPlantForWarehouseException;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductPlanning;
import org.eevolution.model.MWMInOutBound;
import org.eevolution.model.MWMInOutBoundLine;
import org.eevolution.model.X_DD_Order;

/**
 *	
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  @version $Id: $
 */
public class ReleaseInOutBound extends ReleaseInOutBoundAbstract
{
	private Timestamp today = new Timestamp (System.currentTimeMillis());
	private MDDOrder orderDistribution;
	
	/**
	 * 	Get Parameters
	 */
	@Override
	protected void prepare ()
	{
		super.prepare();
	}

	/**
	 * 	Process - Generate Export Format
	 *	@return info
	 */
	@Override
	protected String doIt () throws Exception
	{
		MLocator outBoundLocator = new MLocator(getCtx(), getLocatorId() , get_TrxName());
		if (outBoundLocator == null || outBoundLocator.getM_Locator_ID() <= 0)
			throw new AdempiereException("@M_Locator_ID@ @NotFound@");

		List<MWMInOutBoundLine> outBoundLines = (List<MWMInOutBoundLine>) getInstancesForSelection(get_TrxName());
		Hashtable<Integer, MWMInOutBound> outboundOrders = new Hashtable<>();

		//Complete Outbound Order
		outBoundLines.stream().forEach(outboundLine -> {
						outboundLine.setM_LocatorTo_ID(outBoundLocator.getM_Locator_ID());
						outboundLine.saveEx();
						MWMInOutBound outboundOrder = outboundLine.getParent();
						if (!outboundOrders.contains(outboundOrder.get_ID()))
							outboundOrders.put(outboundOrder.get_ID(),outboundOrder);
		});

		// Complete selected order
		outboundOrders.entrySet().forEach( order ->	{
			MWMInOutBound outboundOrder = order.getValue();
			outboundOrder.setDocAction(DocAction.ACTION_Complete);
			outboundOrder.processIt(DocAction.ACTION_Complete);
			outboundOrder.saveEx();
		});



		outBoundLines.stream().forEach(outboundLine -> {
			// if the locator is same to pick then the storage are in outbound locator not is necessary create other Distribution Order
			if (outboundLine.getDD_OrderLine_ID() > 0) {
				MDDOrderLine orderDistributionLine = (MDDOrderLine) outboundLine.getDD_OrderLine();
				if (orderDistributionLine.getWM_InOutBoundLine_ID() <= 0) {
					orderDistributionLine.setWM_InOutBoundLine_ID(orderDistributionLine.getWM_InOutBoundLine_ID());
					orderDistributionLine.saveEx();
				}
				if (orderDistributionLine.getM_LocatorTo_ID() == outboundLine.getM_LocatorTo_ID())
					return;
			}
			BigDecimal qtySupply = createDistributionOrder(outboundLine);
			if(isCreateSupply() && qtySupply.signum() > 0)
			{
				Env.setContext(outboundLine.getCtx(),"IsCreateSupply", "Y");
				createSupply(outboundLine, qtySupply);
			}
		});
		
		if(orderDistribution != null && getDocAction() != null)
		{
			orderDistribution.setDocAction(getDocAction());
			orderDistribution.setDocStatus(DocAction.STATUS_InProgress);
			orderDistribution.completeIt();
			orderDistribution.save();
		}	
		
		if(isPrintPickList() && orderDistribution != null)
		{
			// Get Format & Data
			ReportEngine reportEngine = getReportEngine("DistributionOrder_Header  ** TEMPLATE **","DD_Order_Header_v", orderDistribution.getDD_Order_ID());
			if(reportEngine == null )
				throw new AdempiereException("@NotFound@ @AD_PrintFormat_ID@");

			ReportCtl.preview(reportEngine);
			reportEngine.print(); // prints only original
		}
		return "@Ok@ ";
	}
	
	/**
	 * create Distribution Order to performance a Pick List
	 * @param outboundLine Out bound Line
	 * @return Quantity that was not covert for inventory
	 */
	protected BigDecimal createDistributionOrder(MWMInOutBoundLine outboundLine)
	{				
		WMRuleEngine engineRule = WMRuleEngine.get();
		List<MStorage> storageList = engineRule.getStorage(outboundLine, getAreaTypeId(), getSectionTypeId());
		int shipperId = 0;
		AtomicReference<BigDecimal> qtySupply = new AtomicReference<>(BigDecimal.ZERO);
		if(storageList != null && storageList.size() > 0)
		{	
			//get the warehouse in transit
			MLocator outboundLocator = MLocator.get(outboundLine.getCtx() , outboundLine.getM_LocatorTo_ID());
			MWarehouse[] wsts = MWarehouse.getInTransitForOrg(getCtx(), outboundLocator.getAD_Org_ID());
			if (wsts == null || wsts.length == 0)
				throw new AdempiereException("@M_Warehouse_ID@ @IsInTransit@ @NotFound@");

			//Org Must be linked to BPartner
			MOrg org = MOrg.get(getCtx(),  outboundLocator.getAD_Org_ID());
			int partnerId = org.getLinkedC_BPartner_ID(get_TrxName());
			if (partnerId == 0)
				throw new NoBPartnerLinkedforOrgException (org);
				
			MBPartner partner = MBPartner.get(getCtx(), partnerId);
			if(orderDistribution == null)
			{
				orderDistribution = new MDDOrder(getCtx() , 0 , get_TrxName());
				orderDistribution.setAD_Org_ID(outboundLocator.getAD_Org_ID());
				orderDistribution.setC_BPartner_ID(partnerId);
				if(getDocTypeId() > 0)
					orderDistribution.setC_DocType_ID(getDocTypeId());
				else
					orderDistribution.setC_DocType_ID(MDocType.getDocType(X_C_DocType.DOCBASETYPE_DistributionOrder));

				orderDistribution.setM_Warehouse_ID(wsts[0].get_ID());
				if(getDocAction() != null)
					orderDistribution.setDocAction(getDocAction());
				else
					orderDistribution.setDocAction(X_DD_Order.DOCACTION_Prepare);
				
				MUser[] users = MUser.getOfBPartner(getCtx(), partner.getC_BPartner_ID(), get_TrxName());
				if (users == null || users.length == 0)
					throw new AdempiereException("@AD_User_ID@ @NotFound@ @Value@ - @C_BPartner_ID@ : "+ partner.getValue() + " - "+ partner.getName());

				orderDistribution.setAD_User_ID(users[0].getAD_User_ID());
				orderDistribution.setDateOrdered(getToday());
				orderDistribution.setDatePromised(getToday());
				orderDistribution.setM_Shipper_ID(shipperId);
				orderDistribution.setIsInDispute(false);
				orderDistribution.setIsInTransit(false);
				orderDistribution.setSalesRep_ID(getAD_User_ID());
				orderDistribution.saveEx();
			}

			storageList.stream().forEach(storage -> {
				MDDOrderLine orderLine = new MDDOrderLine(orderDistribution);
				orderLine.setM_Locator_ID(storage.getM_Locator_ID());
				orderLine.setM_LocatorTo_ID(outboundLine.getM_LocatorTo_ID());
				orderLine.setC_UOM_ID(outboundLine.getC_UOM_ID());
				orderLine.setM_Product_ID(outboundLine.getM_Product_ID());
				orderLine.setDateOrdered(getToday());
				orderLine.setDatePromised(outboundLine.getPickDate());
				orderLine.setWM_InOutBoundLine_ID(outboundLine.getWM_InOutBoundLine_ID());
				orderLine.setIsInvoiced(false);
				
				if (outboundLine.getQtyToPick().subtract(qtySupply.get()).compareTo(storage.getQtyOnHand()) < 0)
				{
					orderLine.setConfirmedQty(outboundLine.getQtyToPick());
					orderLine.setQtyEntered(outboundLine.getQtyToPick());
					orderLine.setQtyOrdered(outboundLine.getQtyToPick());
					orderLine.setTargetQty(outboundLine.getQtyToPick());
					qtySupply.updateAndGet(supply -> supply.add(outboundLine.getQtyToPick()));
				}
				else
				{
					orderLine.setConfirmedQty(storage.getQtyOnHand());
					orderLine.setQtyEntered(storage.getQtyOnHand());
					orderLine.setQtyOrdered(storage.getQtyOnHand());
					orderLine.setTargetQty(storage.getQtyOnHand());
					qtySupply.updateAndGet(supply  -> supply.add(storage.getQtyOnHand()));
				}
				if (qtySupply.get().signum() > 0) {
					//Save the last location from a storage found
					outboundLine.setM_Locator_ID(storage.getM_Locator_ID());
					outboundLine.saveEx();
				}
				orderLine.saveEx();

			});
		}
		else
		{
			qtySupply.updateAndGet(supply ->  outboundLine.getQtyToPick().subtract(supply));
		}

		return qtySupply.get();
	}
	
	/**
	 * Create supply based in Out bound Line
	 * @param outBoundOrderLine  Out bound Line
	 * @param qtySupply Quantity Supply
	 */
	public void createSupply(MWMInOutBoundLine outBoundOrderLine, BigDecimal qtySupply)
	{
		MProduct product = MProduct.get(outBoundOrderLine.getCtx(), outBoundOrderLine.getM_Product_ID());
		if (product != null && product.isBOM())
			createManufacturingOrder(outBoundOrderLine, product , qtySupply);
		else if(product.isPurchased())
			createRequisition(outBoundOrderLine, product, qtySupply);
	}
	
	/**
	 * Create Requisition when the Is create supply is define as yes
	 * @param outboundLocator
	 * @param outboundLine
	 * @param product Product
	 * @param QtyPlanned Qty Planned
	 */
	public  void createRequisition(MWMInOutBoundLine outboundLine, MProduct product, BigDecimal QtyPlanned)
	{
		//s_log.info("Create Requisition");
		int partnerId = 0;
		int priceListId = 0;
		MProductPO productPOLast;
		MProductPO[] productPOs = MProductPO.getOfProduct(getCtx(), product.getM_Product_ID(), null);
		Arrays.stream(productPOs).forEach(productPO -> {
			if (productPO.isCurrentVendor() && productPO.getC_BPartner_ID() != 0)
			{
				//partnerId = productPO.getC_BPartner_ID();
				//productPOLast = productPO;
				//break;
			}
		});
		
		if (partnerId == 0 && productPOs.length > 0)
		{
			partnerId = productPOs[0].getC_BPartner_ID();
		}
		if (partnerId == 0)
		{
			throw new NoVendorForProductException(product.getName());
		}
		
		final String sql = "SELECT COALESCE(bp."+MBPartner.COLUMNNAME_PO_PriceList_ID
		+",bpg."+X_C_BP_Group.COLUMNNAME_PO_PriceList_ID+")"
		+" FROM C_BPartner bp"
		+" INNER JOIN C_BP_Group bpg ON (bpg.C_BP_Group_ID=bp.C_BP_Group_ID)"
		+" WHERE bp.C_BPartner_ID=?";
		priceListId = DB.getSQLValueEx(get_TrxName(), sql, partnerId);
		MLocator outboundLocator = MLocator.get(outboundLine.getCtx() , outboundLine.getM_LocatorTo_ID());
		MRequisition requisition = new  MRequisition(getCtx(),0, get_TrxName());
		requisition.setAD_Org_ID(outboundLocator.getAD_Org_ID());
		requisition.setAD_User_ID(getAD_User_ID());
		requisition.setDateRequired(outboundLine.getPickDate());
		requisition.setDescription("Generate from Outbound Order"); // TODO: add translation
		requisition.setM_Warehouse_ID(outboundLocator.getM_Warehouse_ID());
		requisition.setC_DocType_ID(MDocType.getDocType(MDocType.DOCBASETYPE_PurchaseRequisition));
		if (priceListId > 0)
			requisition.setM_PriceList_ID(priceListId);
		requisition.saveEx();

		MRequisitionLine reqline = new  MRequisitionLine(requisition);
		reqline.setLine(10);
		reqline.setAD_Org_ID(outboundLocator.getAD_Org_ID());
		reqline.setC_BPartner_ID(partnerId);
		reqline.setM_Product_ID(product.getM_Product_ID());
		reqline.setPrice();
		reqline.setPriceActual(Env.ZERO);
		reqline.setQty(QtyPlanned);
		reqline.saveEx();
		
		MOrderLine orderLine = new MOrderLine(getCtx(), outboundLine.getC_OrderLine_ID(), get_TrxName());
		orderLine.setDescription(orderLine.getDescription()
				+ " "
				+ Msg.translate(getCtx(),MRequisition.COLUMNNAME_M_Requisition_ID) 
				+ " : "
				+ requisition.getDocumentNo());
		orderLine.saveEx();
		
		outboundLine.setDescription(outboundLine.getDescription()
				+ " "
				+ Msg.translate(outboundLine.getCtx(), MRequisition.COLUMNNAME_M_Requisition_ID)
				+ " : "
				+ requisition.getDocumentNo());
	}
	
	/**
	 * Create Manufacturing Order when the Is create supply is define as yes
	 * @param outBoundOrderLine Bound Line
	 * @param product Product
	 * @param qtySupply Quantity to Supply
	 */
	public void createManufacturingOrder(MWMInOutBoundLine outBoundOrderLine, MProduct product, BigDecimal qtySupply)
	{
		MPPOrder order = MPPOrder.forC_OrderLine_ID(outBoundOrderLine.getCtx(), outBoundOrderLine.getC_OrderLine_ID(), product.get_ID(), outBoundOrderLine.get_TrxName());
		if(order == null)
		{	
			MPPProductBOM bom = MPPProductBOM.getDefault(product, get_TrxName());
			if (bom != null) 
			{		
				MPPProductPlanning productPlanning = null;
				//Validate the BOM based in planning data 
				if(bom == null)
				{
					productPlanning = MPPProductPlanning.find(getCtx(), outBoundOrderLine.getAD_Org_ID(), 0, 0, product.getM_Product_ID(), null);
					if(productPlanning != null)
					{	
						bom = productPlanning.getPP_Product_BOM();
					}
				}
				if (bom != null) 
				{		  
					final int plant_id = MPPProductPlanning.getPlantForWarehouse(outBoundOrderLine.getM_Warehouse_ID());
					if(plant_id <= 0)
					{
						throw new NoPlantForWarehouseException(outBoundOrderLine.getM_Warehouse_ID());
					}
					MWorkflow workflow = MWorkflow.get(getCtx(), MWorkflow.getWorkflowSearchKey(product));
					//Validate the workflow based in planning data 						
					if(workflow == null && productPlanning != null)
					{
						workflow = productPlanning.getAD_Workflow();
					}
					
					if (plant_id > 0 && workflow != null)
					{
						String description = Msg.translate(getCtx(), MWMInOutBound.COLUMNNAME_WM_InOutBound_ID) 
						+ " : "
						+ outBoundOrderLine.getParent().getDocumentNo();
						
						//Create temporary Product Planning to Create Manufacturing Order 
						productPlanning = new MPPProductPlanning(getCtx(), 0 , get_TrxName());
						productPlanning.setAD_Org_ID(outBoundOrderLine.getAD_Org_ID());
						productPlanning.setM_Product_ID(product.getM_Product_ID());
						productPlanning.setPlanner_ID(outBoundOrderLine.getParent().getSalesRep_ID());
						productPlanning.setPP_Product_BOM_ID(bom.getPP_Product_BOM_ID());
						productPlanning.setAD_Workflow_ID(workflow.getAD_Workflow_ID());
						productPlanning.setM_Warehouse_ID(outBoundOrderLine.getM_Warehouse_ID());
						productPlanning.setS_Resource_ID(plant_id);
						
						order = MPPMRP.createMO( productPlanning,
										outBoundOrderLine.getC_OrderLine_ID(),
										outBoundOrderLine.getM_AttributeSetInstance_ID(),
										qtySupply, 
										outBoundOrderLine.getPickDate(),
										outBoundOrderLine.getShipDate(),
										description
										);
						MOrderLine orderLine = new MOrderLine(getCtx(), outBoundOrderLine.getC_OrderLine_ID(), get_TrxName());
						
						description = "";
						if(orderLine.getDescription() != null)
							description = orderLine.getDescription();
						
						description = description 
									+ " "
									+ Msg.translate(orderLine.getCtx(), MPPOrder.COLUMNNAME_PP_Order_ID)
									+ " : "
								    + order.getDocumentNo();
						orderLine.setDescription(description);
						orderLine.saveEx();
						
						String boundDescription = "";
						if(outBoundOrderLine.getDescription() != null)
							boundDescription = outBoundOrderLine.getDescription();
						
						boundDescription = boundDescription 
										 + " "
										 + Msg.translate(getCtx(), MPPOrder.COLUMNNAME_PP_Order_ID) 
										 + " : "
										 + order.getDocumentNo();
						outBoundOrderLine.setDescription(boundDescription);
					}
				}	
			}	
		}
	}
	
	/*
	 * get the a Report Engine Instance using the view table 
	 * @param tableName
	 */
	private ReportEngine getReportEngine(String formatName, String tableName,int recordId)
	{
		// Get Format & Data
		int formatId= MPrintFormat.getPrintFormat_ID(formatName, MTable.getTable_ID(tableName), getAD_Client_ID());
		MPrintFormat format = MPrintFormat.get(getCtx(), formatId, true);
		if (format == null)
		{
			addLog("@NotFound@ @AD_PrintFormat_ID@");
			return null;
		}
		// query
		MQuery query = new MQuery(tableName);
		query.addRestriction(MDDOrder.COLUMNNAME_DD_Order_ID, MQuery.EQUAL, recordId);
		// Engine
		PrintInfo info = new PrintInfo(tableName,  MTable.getTable_ID(tableName), recordId);
		ReportEngine reportEngine = new ReportEngine(getCtx(), format, query, info);
		return reportEngine;
	}
	
	/**
	 * get Today
	 * @return Today
	 */
	protected Timestamp getToday()
	{
		return this.today;
	}
}
