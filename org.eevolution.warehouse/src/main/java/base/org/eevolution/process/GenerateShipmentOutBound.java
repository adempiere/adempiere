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
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MStorage;
import org.compiere.model.MWarehouse;
import org.compiere.process.ProcessInfo;
import org.eevolution.model.I_DD_Order;
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

		distributionOrders = new Hashtable<Integer, I_DD_Order>();
		shipments  = new Hashtable<Integer, MInOut>();

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
	 * @param outBoundLine
	 */
	public void createShipment(MWMInOutBoundLine outBoundLine)
	{
		// Generate Shipment based on Outbound Order
		if (outBoundLine.getC_OrderLine_ID() > 0) {
			MOrderLine orderLine = outBoundLine.getOrderLine();
			if (outBoundLine.getPickedQty().subtract(orderLine.getQtyDelivered()).signum() <= 0 && !isIncludeNotAvailable())
				return;

			MLocator standing = getStandingLocator(outBoundLine);
			BigDecimal qtyDelivered = getQtyDelivered(outBoundLine , orderLine.getQtyDelivered());

			MInOut shipment = getShipment(orderLine);

			MInOutLine shipmentLine = new MInOutLine(outBoundLine.getCtx(), 0 , outBoundLine.get_TrxName());
			shipmentLine.setM_InOut_ID(shipment.getM_InOut_ID());
			shipmentLine.setM_Locator_ID(standing.getM_Locator_ID());
			shipmentLine.setM_Product_ID(outBoundLine.getM_Product_ID());
			shipmentLine.setQtyEntered(qtyDelivered);
			shipmentLine.setMovementQty(qtyDelivered);
			shipmentLine.setC_OrderLine_ID(orderLine.getC_OrderLine_ID());
			shipmentLine.saveEx();
		}
		// Generate Delivery Movement
		if (outBoundLine.getDD_OrderLine_ID() > 0) {
			MDDOrderLine distributionOrderLine = (MDDOrderLine) outBoundLine.getDD_OrderLine();

			if (distributionOrders.get(distributionOrderLine.getDD_Order_ID()) == null)
				distributionOrders.put(distributionOrderLine.getDD_Order_ID() , distributionOrderLine.getDD_Order());

			distributionOrderLine.setConfirmedQty(outBoundLine.getPickedQty());
			distributionOrderLine.saveEx();
		}

		// Generate Delivery Manufacturing Order
		if (outBoundLine.getPP_Order_BOMLine_ID() > 0) {
			MPPOrderBOMLine orderBOMLine = (MPPOrderBOMLine) outBoundLine.getPP_Order_BOMLine();
			if (outBoundLine.getPickedQty().subtract(orderBOMLine.getQtyDelivered()).signum() <= 0 && !isIncludeNotAvailable())
				return;

			MLocator standing = getStandingLocator(outBoundLine);

			MStorage[] storage = MStorage.getAll(getCtx(), orderBOMLine.getM_Product_ID() , standing.getM_Locator_ID() , get_TrxName());

			BigDecimal qtyDelivered = getQtyDelivered(outBoundLine , orderBOMLine.getQtyDelivered());
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

	private MLocator getStandingLocator(MWMInOutBoundLine outBoundLine)
	{
		MLocator standing;

		if(isIncludeNotAvailable())
			standing =  MLocator.getDefault((MWarehouse)outBoundLine.getParent().getM_Warehouse());
		else
			standing = outBoundLine.getLocator();

		return standing;
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
		manufacturingIssues.entrySet().stream().forEach(entry  -> {
			MPPCostCollector issue = entry.getValue();
			issue.setDocAction(getDocumentAction());
			issue.processIt(getDocumentAction());
			if (!issue.processIt(getDocumentAction())) {
				addLog("@ProcessFailed@ : " + issue.getDocumentInfo());
				log.warning("@ProcessFailed@ :" + issue.getDocumentInfo());
			}
			issue.saveEx();
		});
	}

	public void processingShipments()
	{
		shipments.entrySet().stream().forEach(entry -> {
			MInOut shipment = entry.getValue();
			shipment.setDocAction(getDocumentAction());
			shipment.processIt(getDocumentAction());
			if (!shipment.processIt(getDocumentAction())) {
				addLog("@ProcessFailed@ : " + shipment.getDocumentInfo());
				log.warning("@ProcessFailed@ :" + shipment.getDocumentInfo());
			}
			shipment.saveEx();
		});
	}



	public void processingMovements()
	{
		distributionOrders.entrySet().stream().forEach(entry -> {
			I_DD_Order distributionOrder = entry.getValue();
			List<Integer> orderIds = new ArrayList<Integer>();
			orderIds.add(distributionOrder.getDD_Order_ID());

			ProcessInfo processInfo = ProcessBuilder.create(getCtx())
					.process(MovementGenerate.getProcessId())
					.withSelectedRecordsIds(orderIds)
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
					GenerateMovement.printDocument(movement, "Inventory Move Hdr (Example)", processInfo.getWindowNo());
				else
					throw new AdempiereException("@M_Movement_ID@ @NotFound@");
			});
		});
	}

	/**
	 * Create Shipment heder
	 * @param orderLine Sales Order Line
	 * @return MInOut return the Shipment header
	 */
	private MInOut getShipment(MOrderLine orderLine)
	{
		MInOut shipment = shipments.get(orderLine.getC_Order_ID());
		if(shipment != null)
			return shipment;

		MOrder order = orderLine.getParent();
		int docTypeId = MDocType.getDocType(MDocType.DOCBASETYPE_MaterialDelivery , orderLine.getAD_Org_ID());
		shipment = new MInOut(order,docTypeId, getMovementDate());
		shipment.setIsSOTrx(true);
		shipment.saveEx();

		shipments.put(order.getC_Order_ID(), shipment);
		return shipment;
	}
}
