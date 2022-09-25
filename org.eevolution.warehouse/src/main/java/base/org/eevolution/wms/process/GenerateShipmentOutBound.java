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

package org.eevolution.wms.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.adempiere.core.domains.models.I_DD_Order;
import org.adempiere.core.domains.models.X_C_Order;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MMovement;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MStorage;
import org.compiere.model.PO;
import org.compiere.process.ProcessInfo;
import org.eevolution.distribution.model.MDDOrder;
import org.eevolution.distribution.model.MDDOrderLine;
import org.eevolution.distribution.process.MovementGenerate;
import org.eevolution.manufacturing.model.MPPCostCollector;
import org.eevolution.manufacturing.model.MPPOrder;
import org.eevolution.manufacturing.model.MPPOrderBOMLine;
import org.eevolution.wms.model.MWMInOutBound;
import org.eevolution.wms.model.MWMInOutBoundLine;
import org.eevolution.services.dsl.ProcessBuilder;

/**
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * @version $Id: $
 */
public class GenerateShipmentOutBound extends GenerateShipmentOutBoundAbstract {
    private Hashtable<Integer, MInOut> shipments = new Hashtable<Integer, MInOut>();
    private Hashtable<Integer, I_DD_Order> distributionOrders = new Hashtable<Integer, I_DD_Order>();
    private Hashtable<Integer, MPPCostCollector> manufacturingIssues = new Hashtable<>();
    private int documentCreated = 0;

    /**
     * Get Parameters
     */
    protected void prepare() {
        super.prepare();
    }

    /**
     * Process - Generate Shipment from OutBound
     *
     * @return info
     */
    protected String doIt() throws Exception {
        // Overwrite table RV_WM_InOutBoundLine by WM_InOutBoundLine domain model
        getProcessInfo().setTableSelectionId(MWMInOutBoundLine.Table_ID);
        List<MWMInOutBoundLine> outBoundLines = (List<MWMInOutBoundLine>) getInstancesForSelection(get_TrxName());
        outBoundLines.stream()
                .filter(outBoundLine -> outBoundLine.getQtyToDeliver().signum() > 0 || isIncludeNotAvailable())
                .forEach(this::createShipment);

        //Processing Shipments
        processingShipments();

        //Processing Movements
        processingMovements();

        //Processing Issues
        processingIssues();

        StringBuilder documentGenerated = new StringBuilder();
        shipments.forEach((key, value) -> documentGenerated.append(" , ").append(value.getDocumentInfo()));
        return "@Created@ " + documentCreated + documentGenerated.toString();
    }

    /**
     * Create Shipment to Out Bound Order
     *
     * @param outboundLine Outbound Order Line
     */
    private void createShipment(MWMInOutBoundLine outboundLine) {
        // Generate Shipment based on Outbound Order
        if (outboundLine.getC_OrderLine_ID() > 0) {
            MOrderLine orderLine = outboundLine.getOrderLine();
            if (orderLine.getQtyToDelivery().subtract(outboundLine.getPickedQty()).signum() < 0 && !isIncludeNotAvailable())
                return;

            BigDecimal qtyToDelivery = getSalesOrderQtyToDelivery(outboundLine, orderLine);
            MInOut shipment = getShipment(orderLine, outboundLine.getParent());
            MInOutLine shipmentLine = new MInOutLine(outboundLine.getCtx(), 0, outboundLine.get_TrxName());
            shipmentLine.setM_InOut_ID(shipment.getM_InOut_ID());
            shipmentLine.setM_Locator_ID(outboundLine.getM_LocatorTo_ID());
            shipmentLine.setM_Product_ID(outboundLine.getM_Product_ID());
            shipmentLine.setDescription(outboundLine.getDescription());
            shipmentLine.setC_UOM_ID(outboundLine.getC_UOM_ID());
            shipmentLine.setQtyEntered(qtyToDelivery);
            shipmentLine.setMovementQty(qtyToDelivery);
            shipmentLine.setC_OrderLine_ID(orderLine.getC_OrderLine_ID());
            shipmentLine.setM_Shipper_ID(outboundLine.getM_Shipper_ID());
            shipmentLine.setM_FreightCategory_ID(outboundLine.getM_FreightCategory_ID());
            shipmentLine.setFreightAmt(outboundLine.getFreightAmt());
            shipmentLine.setM_AttributeSetInstance_ID(outboundLine.getM_AttributeSetInstance_ID());
            shipmentLine.setWM_InOutBoundLine_ID(outboundLine.getWM_InOutBoundLine_ID());
            shipmentLine.saveEx();
        }
        // Generate Delivery Movement
        if (outboundLine.getDD_OrderLine_ID() > 0) {
            MDDOrderLine distributionOrderLine = new MDDOrderLine(outboundLine.getCtx(), outboundLine.getDD_OrderLine_ID(), outboundLine.get_TrxName());
            distributionOrderLine.setDescription(outboundLine.getDescription());

            if (distributionOrders.get(distributionOrderLine.getDD_Order_ID()) == null)
                distributionOrders.put(distributionOrderLine.getDD_Order_ID(), distributionOrderLine.getParent());

            distributionOrderLine.setConfirmedQty(getDistributionOrderQtyToDelivery(outboundLine, distributionOrderLine));
            distributionOrderLine.saveEx();
        }

        // Generate Delivery Manufacturing Order
        if (outboundLine.getPP_Order_BOMLine_ID() > 0) {
            MPPOrderBOMLine orderBOMLine = (MPPOrderBOMLine) outboundLine.getPP_Order_BOMLine();
            if (outboundLine.getPickedQty().subtract(orderBOMLine.getQtyDelivered()).signum() < 0 && !isIncludeNotAvailable())
                return;

            MStorage[] storage = MStorage.getAll(getCtx(), orderBOMLine.getM_Product_ID(), outboundLine.getM_LocatorTo_ID(), get_TrxName());

            BigDecimal qtyDelivered = getManufacturingOrderQtyToDelivery(outboundLine , orderBOMLine);
            List<MPPCostCollector> issues = MPPOrder.createIssue(
                    orderBOMLine.getParent(),
                    orderBOMLine,
                    getMovementDate(),
                    qtyDelivered,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    storage,
                    true);

            issues.forEach(costCollector -> {
                costCollector.setDescription(outboundLine.getDescription());
                costCollector.saveEx();
                if (manufacturingIssues.get(costCollector.getPP_Cost_Collector_ID()) == null)
                    manufacturingIssues.put(costCollector.getPP_Cost_Collector_ID(), costCollector);
            });
        }
    }

    private BigDecimal getSalesOrderQtyToDelivery(MWMInOutBoundLine outboundLine, MOrderLine orderLine) {
        BigDecimal qtyToDelivery;
        if (isIncludeNotAvailable())
            qtyToDelivery = outboundLine.getQtyToPick();
        else {
            //Sales Order Qty To Delivery
            BigDecimal salesOrderQtyToDelivery = orderLine.getQtyToDelivery();
            //Outbound Order Qty To Delivery
            BigDecimal outboundOrderQtyToDelivery = outboundLine.getPickedQty().subtract(outboundLine.getShipmentQtyDelivered());
            //The quantity to delivery of the Outbound order cannot be greater than the pending quantity to delivery  of the sales order.
            if (outboundOrderQtyToDelivery.compareTo(salesOrderQtyToDelivery) > 0)
                qtyToDelivery = salesOrderQtyToDelivery;
            else if (!X_C_Order.DELIVERYRULE_Force.equals(orderLine.getParent().getDeliveryRule()) &&
                     !X_C_Order.DELIVERYRULE_Manual.equals(orderLine.getParent().getDeliveryRule()))
                qtyToDelivery = outboundOrderQtyToDelivery;
            else
                qtyToDelivery = salesOrderQtyToDelivery;
        }
        return qtyToDelivery;
    }

    private BigDecimal getManufacturingOrderQtyToDelivery(MWMInOutBoundLine outboundLine, MPPOrderBOMLine orderBOMLine) {
        BigDecimal qtyToDelivery;
        if (isIncludeNotAvailable())
            qtyToDelivery = outboundLine.getQtyToPick();
        else {
            //Sales Order Qty To Delivery
            BigDecimal manufacturingOrderQtyToDelivery = orderBOMLine.getQtyRequired().subtract(orderBOMLine.getQtyRequired());
            //Outbound Order Qty To Delivery
            BigDecimal outboundOrderQtyToDelivery = outboundLine.getPickedQty().subtract(outboundLine.getManufacturingOrderQtyDelivered());
            //The quantity to delivery of the Outbound order cannot be greater than the pending quantity to delivery  of the sales order.
            if (outboundOrderQtyToDelivery.compareTo(manufacturingOrderQtyToDelivery) > 0)
                qtyToDelivery = manufacturingOrderQtyToDelivery;
            else if (!X_C_Order.DELIVERYRULE_Force.equals(orderBOMLine.getParent().getDeliveryRule()) &&
                     !X_C_Order.DELIVERYRULE_Manual.equals(orderBOMLine.getParent().getDeliveryRule()))
                qtyToDelivery = outboundOrderQtyToDelivery;
            else
                qtyToDelivery = manufacturingOrderQtyToDelivery;
        }
        return qtyToDelivery;
    }

    private BigDecimal getDistributionOrderQtyToDelivery(MWMInOutBoundLine outboundLine, MDDOrderLine orderLine) {
        BigDecimal qtyToDelivery;
        if (isIncludeNotAvailable())
            qtyToDelivery = outboundLine.getQtyToPick();
        else {
            //Sales Order Qty To Delivery
            BigDecimal distributionOrderQtyToDelivery = orderLine.getQtyToDeliver();
            //Outbound Order Qty To Delivery
            BigDecimal outboundOrderQtyToDelivery = outboundLine.getPickedQty().subtract(outboundLine.getDistributionOrderQtyDelivered());
            //The quantity to delivery of the Outbound order cannot be greater than the pending quantity to delivery  of the sales order.
            if (outboundOrderQtyToDelivery.compareTo(distributionOrderQtyToDelivery) > 0)
                qtyToDelivery = distributionOrderQtyToDelivery;
            else if (!X_C_Order.DELIVERYRULE_Force.equals(orderLine.getParent().getDeliveryRule()) &&
                     !X_C_Order.DELIVERYRULE_Manual.equals(orderLine.getParent().getDeliveryRule()))
                qtyToDelivery = outboundOrderQtyToDelivery;
            else
                qtyToDelivery = distributionOrderQtyToDelivery;
        }
        return qtyToDelivery;
    }

    private void processingIssues() {
        manufacturingIssues.entrySet().stream().filter(Objects::nonNull)
                .forEach(entry -> {
                    MPPCostCollector issue = entry.getValue();
                    if (MPPCostCollector.DOCSTATUS_Drafted.equals(issue.getDocStatus()) ||
                        MPPCostCollector.DOCSTATUS_InProgress.equals(issue.getDocStatus())) {
                        if (!issue.processIt(MPPCostCollector.DOCACTION_Complete)) {
                            addLog("@ProcessFailed@ : " + issue.getDocumentInfo());
                            log.warning("@ProcessFailed@ :" + issue.getDocumentInfo());
                        }
                        issue.saveEx();
                    }
                });
    }

    private void processingShipments() {
        List<PO> shipmentsToPrint = new ArrayList<PO>();
        shipments.entrySet().stream().filter(Objects::nonNull).forEach(entry -> {
            MInOut shipment = entry.getValue();
            if (!shipment.processIt(getDocAction())) {
                addLog("@ProcessFailed@ : " + shipment.getDocumentInfo());
                log.warning("@ProcessFailed@ :" + shipment.getDocumentInfo());
            }
            shipment.saveEx();
            documentCreated++;
            addLog(shipment.getDocumentInfo());
            shipmentsToPrint.add(shipment);
        });
        //	Print documents
        printDocument(shipmentsToPrint, true);
    }


    private void processingMovements() {
        distributionOrders.entrySet().stream().filter(Objects::nonNull).forEach(entry -> {
            I_DD_Order distributionOrder = entry.getValue();
            List<Integer> orderIds = new ArrayList<Integer>();
            orderIds.add(distributionOrder.getDD_Order_ID());

            ProcessInfo processInfo = ProcessBuilder.create(getCtx())
                    .process(MovementGenerate.getProcessId())
                    .withSelectedRecordsIds(MDDOrder.Table_ID, orderIds)
                    .withParameter(MWMInOutBound.COLUMNNAME_M_Warehouse_ID, distributionOrder.getM_Warehouse_ID())
                    .withParameter(MMovement.COLUMNNAME_MovementDate, getMovementDate())
                    .withoutTransactionClose()
                    .execute(get_TrxName());
            if (processInfo.isError())
                throw new AdempiereException(processInfo.getSummary());

            addLog(processInfo.getSummary());
            Arrays.stream(processInfo.getIDs()).forEach(recordId -> {
                Optional<MMovement> maybeMovement = Optional.ofNullable(new MMovement(getCtx(), recordId, get_TrxName()));
                maybeMovement.ifPresent(movement -> {
                    documentCreated++;
                    printDocument(movement, true);
                });
            });
        });
    }

    /**
     * Create Shipment header
     *
     * @param orderLine Sales Order Line
     * @param outbound  Outbound Order
     * @return MInOut return the Shipment header
     */
    private MInOut getShipment(MOrderLine orderLine, MWMInOutBound outbound) {
        MInOut shipment = shipments.get(orderLine.getC_Order_ID());
        if (shipment != null)
            return shipment;

        MOrder order = orderLine.getParent();
        MDocType orderDocumentType = (MDocType) order.getC_DocType();
        int docTypeId = orderDocumentType.getC_DocTypeShipment_ID();
        if (docTypeId == 0) {
            docTypeId = MDocType.getDocType(MDocType.DOCBASETYPE_MaterialDelivery, orderLine.getAD_Org_ID());
        }
        shipment = new MInOut(order, docTypeId, getMovementDate());
        shipment.setIsSOTrx(true);
        shipment.setM_Shipper_ID(outbound.getM_Shipper_ID());
        shipment.setDescription(outbound.getDescription());
        shipment.setM_FreightCategory_ID(outbound.getM_FreightCategory_ID());
        shipment.setFreightCostRule(outbound.getFreightCostRule());
        shipment.setFreightAmt(outbound.getFreightAmt());
        shipment.setDocAction(MInOut.DOCACTION_Complete);
        shipment.setDocStatus(MInOut.DOCSTATUS_Drafted);
        shipment.saveEx();

        shipments.put(order.getC_Order_ID(), shipment);
        return shipment;
    }
}
