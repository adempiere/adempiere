/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 * *
 * Copyright (C) Victor Perez	                                      *
 * Copyright (C) Contributors                                         *
 * *
 * This program is free software; you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation; either version 2     *
 * of the License, or (at your option) any later version.             *
 * *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 * *
 * You should have received a copy of the GNU General Public License  *
 * along with this program; if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 * *
 * Contributors:                                                      *
 * - Victor Perez (victor.perez@e-evolution.com	 )                *
 * *
 * Sponsors:                                                          *
 * - e-Evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/

package org.eevolution.process;

import java.util.List;

import org.adempiere.exceptions.DocTypeNotFoundException;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MOrderLine;
import org.compiere.util.DB;
import org.eevolution.model.I_DD_OrderLine;
import org.eevolution.model.I_PP_Order_BOMLine;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MWMInOutBound;
import org.eevolution.model.MWMInOutBoundLine;

/**
 * Generate Outbound Document based Sales Order Lines and the Smart Browser
 * Filter
 *
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * @version $Id: $
 */
public class GenerateInOutBound extends GenerateInOutBoundAbstract {

    /**
     * Get Parameters
     */
    protected void prepare() {
       super.prepare();
    }

    /**
     * Process - Generate Export Format
     * @return info
     */
    protected String doIt() throws Exception {

        // Create Outbound Order
        MWMInOutBound outBoundOrder = null;
        // Based on Sales Order Line
        if ("ol".equals(getAliasForTableSelection())) {
            outBoundOrder = createOutBoundOrder();
            createBasedOnSalesOrders(outBoundOrder, (List<MOrderLine>) getInstancesForSelection(get_TrxName()));
        }
        // Based on MRP
        if ( "demand".equals(getAliasForTableSelection())) {
            getProcessInfo().setTableSelectionId(MPPMRP.Table_ID);
            outBoundOrder = createOutBoundOrder();
            createBasedOnDemand(outBoundOrder, (List<MPPMRP>) getInstancesForSelection(get_TrxName()));
        }
        return "@DocumentNo@ " + outBoundOrder.getDocumentNo();
    }

    private MWMInOutBound createOutBoundOrder() {

        MLocator locator = MLocator.get(getCtx(), getLocatorId());
        MWMInOutBound outBoundOrder = new MWMInOutBound(getCtx(), 0, get_TrxName());
        outBoundOrder.setShipDate(getShipDate());
        outBoundOrder.setPickDate(getPickDate());
        if (getOrderReference() != null)
            outBoundOrder.setPOReference(getOrderReference());

        if (getDeliveryRule() != null)
            outBoundOrder.setDeliveryRule(getDeliveryRule());
        if (getDeliveryVia() != null)
            outBoundOrder.setDeliveryViaRule(getDeliveryVia());

        if (getDocumentTypeId() > 0)
            outBoundOrder.setC_DocType_ID(getDocumentTypeId());
        else {
            int docTypeId = MDocType.getDocType(MDocType.DOCBASETYPE_WarehouseManagementOrder);
            if (docTypeId <= 0)
                throw new DocTypeNotFoundException(MDocType.DOCBASETYPE_WarehouseManagementOrder, "");
            else
                outBoundOrder.setC_DocType_ID(docTypeId);
        }

        if (getDocumentAction() != null)
            outBoundOrder.setDocAction(getDocumentAction());
        else
            outBoundOrder.setDocAction(MWMInOutBound.ACTION_Prepare);

        outBoundOrder.setDocStatus(MWMInOutBound.DOCSTATUS_Drafted);
        outBoundOrder.setM_Warehouse_ID(locator.getM_Warehouse_ID());
        outBoundOrder.setIsSOTrx(true);
        outBoundOrder.saveEx();
        return outBoundOrder;
    }

    private void createBasedOnSalesOrders(MWMInOutBound outBoundOrder , List<MOrderLine> orderLines) {
        orderLines.stream().forEach(orderLine -> {
            MWMInOutBoundLine outBoundOrderLine = new MWMInOutBoundLine(outBoundOrder);
            outBoundOrderLine.setLine(getLineNo(outBoundOrder));
            outBoundOrderLine.setM_Product_ID(orderLine.getM_Product_ID());
            outBoundOrderLine.setM_AttributeSetInstance_ID(orderLine.getM_AttributeSetInstance_ID());
            outBoundOrderLine.setMovementQty(orderLine.getQtyOrdered().subtract(orderLine.getQtyDelivered()));
            outBoundOrderLine.setC_UOM_ID(orderLine.getC_UOM_ID());
            outBoundOrderLine.setDescription(orderLine.getDescription());
            outBoundOrderLine.setC_Order_ID(orderLine.getC_Order_ID());
            outBoundOrderLine.setC_OrderLine_ID(orderLine.getC_OrderLine_ID());
            outBoundOrderLine.setPickDate(outBoundOrder.getPickDate());
            outBoundOrderLine.setShipDate(outBoundOrder.getShipDate());
            outBoundOrderLine.saveEx();
        });
    }

    private void createBasedOnDemand(MWMInOutBound outBoundOrder, List<MPPMRP> demands) {
        demands.stream().forEach( demand -> {
            MWMInOutBoundLine outBoundOrderLine = new MWMInOutBoundLine(outBoundOrder);
            outBoundOrderLine.setLine(getLineNo(outBoundOrder));
            outBoundOrderLine.setMovementQty(demand.getQty());
            outBoundOrderLine.setDescription(demand.getDescription());
            outBoundOrderLine.setPP_MRP_ID(demand.getPP_MRP_ID());
            outBoundOrderLine.setM_Product_ID(demand.getM_Product_ID());
            if (MPPMRP.ORDERTYPE_SalesOrder.equals(demand.getOrderType())) {
                I_C_OrderLine orderLine = demand.getC_OrderLine();
                outBoundOrderLine.setC_OrderLine_ID(demand.getC_OrderLine_ID());
                outBoundOrderLine.setC_Order_ID(demand.getC_Order_ID());
                outBoundOrderLine.setC_UOM_ID(orderLine.getC_UOM_ID());
                outBoundOrderLine.setM_AttributeSetInstance_ID(orderLine.getM_AttributeSetInstance_ID());
            }
            if (MPPMRP.ORDERTYPE_DistributionOrder.equals(demand.getOrderType())) {
                I_DD_OrderLine orderLine = demand.getDD_OrderLine();
                outBoundOrderLine.setDD_Order_ID(demand.getDD_Order_ID());
                outBoundOrderLine.setDD_OrderLine_ID(demand.getDD_OrderLine_ID());
                outBoundOrderLine.setC_UOM_ID(orderLine.getC_UOM_ID());
                outBoundOrderLine.setM_AttributeSetInstance_ID(orderLine.getM_AttributeSetInstance_ID());
            }
            if (MPPMRP.ORDERTYPE_ManufacturingOrder.equals(demand.getOrderType())) {
                I_PP_Order_BOMLine orderBomLine = demand.getPP_Order_BOMLine();
                outBoundOrderLine.setPP_Order_ID(demand.getPP_Order_ID());
                outBoundOrderLine.setPP_Order_BOMLine_ID(demand.getPP_Order_BOMLine_ID());
                outBoundOrderLine.setC_UOM_ID(orderBomLine.getC_UOM_ID());
                outBoundOrderLine.setM_AttributeSetInstance_ID(orderBomLine.getM_AttributeSetInstance_ID());
            }
            outBoundOrderLine.setPickDate(outBoundOrder.getPickDate());
            outBoundOrderLine.setShipDate(outBoundOrder.getShipDate());
            outBoundOrderLine.saveEx();
        });
    }

    private int getLineNo(MWMInOutBound outbound) {
        return DB.getSQLValueEx(
                get_TrxName(),
                "SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM WM_InOutBoundLine WHERE WM_InOutBound_ID=?",
                outbound.getWM_InOutBound_ID());
    }
}
