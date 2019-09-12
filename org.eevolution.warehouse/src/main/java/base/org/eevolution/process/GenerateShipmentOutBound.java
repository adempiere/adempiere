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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MMovement;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MStorage;
import org.compiere.process.ProcessInfo;
import org.eevolution.model.I_DD_Order;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;
import org.eevolution.model.MPPCostCollector;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPOrderBOMLine;
import org.eevolution.model.MWMInOutBound;
import org.eevolution.model.MWMInOutBoundLine;
import org.eevolution.service.dsl.ProcessBuilder;

/**
 *	
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  @version $Id: $
 */
public class GenerateShipmentOutBound extends GenerateShipmentOutBoundAbstract
{
	protected Hashtable<Integer, MInOut> shipments;
	protected Hashtable<Integer, I_DD_Order>  distributionOrders;
	protected Hashtable<Integer, MPPCostCollector> manufacturingIssues;
	
	/**
	 * 	Get Parameters
	 */
	protected void prepare ()
	{
		super.prepare();

	}

	/**
	 * 	Process - Generate Export Format
	 *	@return info
	 */
	protected String doIt () throws Exception {

		manufacturingIssues = new Hashtable<>();
		distributionOrders = new Hashtable<Integer, I_DD_Order>();
		shipments  = new Hashtable<Integer, MInOut>();
		// Overwrite table RV_WM_InOutBoundLine by WM_InOutBoundLine
		getProcessInfo().setTableSelectionId(MWMInOutBoundLine.Table_ID);
		List<MWMInOutBoundLine> outBoundLines = (List<MWMInOutBoundLine>) getInstancesForSelection(get_TrxName());
		outBoundLines.stream()
				.filter(outBoundLine -> outBoundLine.getQtyToDeliver().signum() > 0 || isIncludeNotAvailable())
				.forEach(outBoundLine -> createShipment(outBoundLine));

		//Processing Shipments
		processingShipments();

		//Processing Movements
		processingMovements();

		//Processing Issues
		processingIssues();

		return "@Ok@";
	}

	/**
	 * Create Shipment to Out Bound Order
	 * @param outboundLine
	 */
	public void createShipment(MWMInOutBoundLine outboundLine)
	{
		// Generate Shipment based on Outbound Order
		if (outboundLine.getC_OrderLine_ID() > 0) {
			MOrderLine orderLine = outboundLine.getOrderLine();
			if (outboundLine.getPickedQty().subtract(orderLine.getQtyDelivered()).signum() <= 0 && !isIncludeNotAvailable())
				return;

			BigDecimal qtyDelivered = getQtyDelivered(outboundLine , orderLine.getQtyDelivered());
			MInOut shipment = getShipment(orderLine, outboundLine.getParent());
			MInOutLine shipmentLine = new MInOutLine(outboundLine.getCtx(), 0 , outboundLine.get_TrxName());
			shipmentLine.setM_InOut_ID(shipment.getM_InOut_ID());
			shipmentLine.setM_Locator_ID(outboundLine.getM_LocatorTo_ID());
			shipmentLine.setM_Product_ID(outboundLine.getM_Product_ID());
			shipmentLine.setC_UOM_ID(outboundLine.getC_UOM_ID());
			shipmentLine.setQtyEntered(qtyDelivered);
			shipmentLine.setMovementQty(qtyDelivered);
			shipmentLine.setC_OrderLine_ID(orderLine.getC_OrderLine_ID());
			shipmentLine.setM_Shipper_ID(outboundLine.getM_Shipper_ID());
			shipmentLine.setM_FreightCategory_ID(outboundLine.getM_FreightCategory_ID());
			shipmentLine.setFreightAmt(outboundLine.getFreightAmt());
			shipmentLine.setWM_InOutBoundLine_ID(outboundLine.getWM_InOutBoundLine_ID());
			shipmentLine.saveEx();
		}
		// Generate Delivery Movement
		if (outboundLine.getDD_OrderLine_ID() > 0) {
			MDDOrderLine distributionOrderLine = (MDDOrderLine) outboundLine.getDD_OrderLine();

			if (distributionOrders.get(distributionOrderLine.getDD_Order_ID()) == null)
				distributionOrders.put(distributionOrderLine.getDD_Order_ID() , distributionOrderLine.getDD_Order());

			distributionOrderLine.setConfirmedQty(outboundLine.getPickedQty());
			distributionOrderLine.saveEx();
		}

		// Generate Delivery Manufacturing Order
		if (outboundLine.getPP_Order_BOMLine_ID() > 0) {
			MPPOrderBOMLine orderBOMLine = (MPPOrderBOMLine) outboundLine.getPP_Order_BOMLine();
			if (outboundLine.getPickedQty().subtract(orderBOMLine.getQtyDelivered()).signum() <= 0 && !isIncludeNotAvailable())
				return;

			MStorage[] storage = MStorage.getAll(getCtx(), orderBOMLine.getM_Product_ID() , outboundLine.getM_LocatorTo_ID() , get_TrxName());

			BigDecimal qtyDelivered = getQtyDelivered(outboundLine , orderBOMLine.getQtyDelivered());
			List<MPPCostCollector> issues = MPPOrder.createIssue(
					orderBOMLine.getParent(),
					orderBOMLine ,
					getMovementDate() ,
					qtyDelivered  ,
					BigDecimal.ZERO ,
					BigDecimal.ZERO ,
					storage ,
					true);

			issues.stream().forEach(costCollector ->  {
				if (manufacturingIssues.get(costCollector.getPP_Cost_Collector_ID()) == null)
					manufacturingIssues.put(costCollector.getPP_Cost_Collector_ID(), costCollector);
			});
		}
	}

	private BigDecimal getQtyDelivered(MWMInOutBoundLine outBoundLine, BigDecimal qtyDemandDelivered)
	{
		BigDecimal qtyDelivered;

		if(isIncludeNotAvailable())
			qtyDelivered  = outBoundLine.getQtyToPick().subtract(qtyDemandDelivered);
		else
			qtyDelivered  = outBoundLine.getPickedQty().subtract(qtyDemandDelivered);

		return qtyDelivered;
	}

	public void processingIssues()
	{
		manufacturingIssues.entrySet().stream().filter(entry -> entry != null)
			.forEach(entry  -> {
			MPPCostCollector issue = entry.getValue();
			issue.setDocAction(getDocAction());
			issue.processIt(getDocAction());
			if (!issue.processIt(getDocAction())) {
				addLog("@ProcessFailed@ : " + issue.getDocumentInfo());
				log.warning("@ProcessFailed@ :" + issue.getDocumentInfo());
			}
			issue.saveEx();
		});
	}

	public void processingShipments()
	{
		shipments.entrySet().stream().filter(entry -> entry != null).forEach(entry -> {
			MInOut shipment = entry.getValue();
			shipment.setDocAction(getDocAction());
			shipment.processIt(getDocAction());
			if (!shipment.processIt(getDocAction())) {
				addLog("@ProcessFailed@ : " + shipment.getDocumentInfo());
				log.warning("@ProcessFailed@ :" + shipment.getDocumentInfo());
			}
			shipment.saveEx();
		});
	}



	public void processingMovements()
	{
		distributionOrders.entrySet().stream().filter(entry -> entry != null).forEach(entry -> {
			I_DD_Order distributionOrder = entry.getValue();
			List<Integer> orderIds = new ArrayList<Integer>();
			orderIds.add(distributionOrder.getDD_Order_ID());

			ProcessInfo processInfo = ProcessBuilder.create(getCtx())
					.process(MovementGenerate.getProcessId())
					.withSelectedRecordsIds(MDDOrder.Table_ID , orderIds)
					.withParameter(MWMInOutBound.COLUMNNAME_M_Warehouse_ID, distributionOrder.getM_Warehouse_ID())
					.withParameter(MMovement.COLUMNNAME_MovementDate, getMovementDate())
					.withoutTransactionClose()
					.execute(get_TrxName());
			if (processInfo.isError())
				throw new AdempiereException(processInfo.getSummary());

			addLog(processInfo.getSummary());
			Arrays.stream(processInfo.getIDs()).forEach(recordId -> {
				MMovement movement = new MMovement(getCtx(), recordId, get_TrxName());
				if (movement != null && movement.get_ID() > 0)
					printDocument(movement, "Inventory Move Hdr (Example)");
				else
					throw new AdempiereException("@M_Movement_ID@ @NotFound@");
			});
		});
	}

	/**
	 * Create Shipment heder
	 * @param orderLine Sales Order Line
	 * @param outbound Outbound Order
	 * @return MInOut return the Shipment header
	 */
	private MInOut getShipment(MOrderLine orderLine, MWMInOutBound outbound)
	{
		MInOut shipment = shipments.get(orderLine.getC_Order_ID());
		if(shipment != null)
			return shipment;

		MOrder order = orderLine.getParent();
		MDocType orderDocumentType = (MDocType) order.getC_DocType();
		int docTypeId = orderDocumentType.getC_DocTypeShipment_ID();
		if(docTypeId == 0) {
			docTypeId = MDocType.getDocType(MDocType.DOCBASETYPE_MaterialDelivery , orderLine.getAD_Org_ID());
		}
		shipment = new MInOut(order,docTypeId, getMovementDate());
		shipment.setIsSOTrx(true);
		shipment.setM_Shipper_ID(outbound.getM_Shipper_ID());
		shipment.setM_FreightCategory_ID(outbound.getM_FreightCategory_ID());
		shipment.setFreightCostRule(outbound.getFreightCostRule());
		shipment.setFreightAmt(outbound.getFreightAmt());
		shipment.saveEx();

		shipments.put(order.getC_Order_ID(), shipment);
		return shipment;
	}
}
