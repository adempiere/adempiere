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
 * Copyright (C) 2003-2012 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.process;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.PO;
import org.compiere.model.X_C_BP_Group;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.MDDOrder;
import org.eevolution.model.MDDOrderLine;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPOrder;

/**
 * @author victor.perez@e-evolution.com , www.e-evolution.com
 */
public class MRPApproval extends MRPApprovalAbstract {

    protected String EXECUTION_MODE = MPPMRP.ORDERTYPE_ManufacturingOrder;

    protected void prepare() {
        super.prepare();
        int AD_Process_ID = getProcessInfo().getAD_Process_ID();
        // Manufacturing Order Approval
        if (AD_Process_ID == 53322)
            EXECUTION_MODE = MPPMRP.ORDERTYPE_ManufacturingOrder;
        // Distribution Order Approval
        if (AD_Process_ID == 53323)
            EXECUTION_MODE = MPPMRP.ORDERTYPE_DistributionOrder;
        // Requisition Approval
        if (AD_Process_ID == 53321)
            EXECUTION_MODE = MPPMRP.ORDERTYPE_MaterialRequisition;
    } // prepare

    /**
     * Perform process.
     *
     * @return Message (clear text)
     * @throws Exception if not successful
     */
    protected String doIt() throws Exception {
        getSelectionKeys()
                .stream()
                .filter(mrpId -> mrpId > 0)
                .forEach(mrpId -> {
                    MPPMRP mrp = new MPPMRP(getCtx(), mrpId, get_TrxName());
                    saveBrowseValues(mrp, "MRP");
                    if (getPriority() != null)
                        mrp.setPriority(getPriority());

                    if (getOrderType() != null && !mrp.getOrderType().equals(getOrderType())) {
                        createSupply(mrp, getOrderType());
                    } else {
                        if (EXECUTION_MODE.equals(MPPMRP.ORDERTYPE_ManufacturingOrder))
                            executeManufacturingOrderApproval(mrp);

                        if (EXECUTION_MODE.equals(MPPMRP.ORDERTYPE_DistributionOrder))
                            executeDistributionOrderApproval(mrp);

                        if (EXECUTION_MODE.equals(MPPMRP.ORDERTYPE_MaterialRequisition)) {
                            if (getBusinessPartnerId() > 0)
                                mrp.setC_BPartner_ID(getBusinessPartnerId());

                            executeRequisitionApproval(mrp);
                        }
                    }
                });

        return null;
    } // doIt

    private void createSupply(MPPMRP mrp, String orderType) {

        PO document = null;
        if (MPPMRP.ORDERTYPE_MaterialRequisition.equals(orderType)) {
            if (getBusinessPartnerId() <= 0)
                throw new AdempiereException("@BPartnerNotFound@");

            document = createRequisition(mrp);

        }
        if (MPPMRP.ORDERTYPE_ManufacturingOrder.equals(orderType)
                && getResourcePlantId() > 0 && getBOMFormulaId() > 0
                && getWorkflowId() > 0) {

            document = createManufacturingOrder(mrp);
        }

        if (MPPMRP.ORDERTYPE_DistributionOrder.equals(orderType)
                && getWarehouseinTransitId() > 0 && getShipperId() > 0
                && getLocatorId() > 0 && getLocatorToId() > 0
                && getBusinessPartnerId() > 0) {

            document = createDistributionOrder(mrp);
        }

        if (MPPMRP.ORDERTYPE_MaterialRequisition.equals(mrp.getOrderType()) && document != null && document.get_ID() > 0) {
            MRequisition requisition = (MRequisition) mrp.getM_Requisition();
            requisition.deleteEx(true);
        }
        if (MPPMRP.ORDERTYPE_ManufacturingOrder.equals(mrp.getOrderType()) && document != null && document.get_ID() > 0) {
            MPPOrder order = (MPPOrder) mrp.getPP_Order();
            order.deleteEx(true);
        }
        if (MPPMRP.ORDERTYPE_DistributionOrder.equals(mrp.getOrderType()) && document != null && document.get_ID() > 0) {
            MDDOrder order = (MDDOrder) mrp.getDD_Order();
            order.deleteEx(true);
        }

    }

    private MDDOrder createDistributionOrder(MPPMRP mrp) {

        MLocator locatorFrom = MLocator.get(mrp.getCtx(), getLocatorId());
        MLocator locatorTo = MLocator.get(mrp.getCtx(), getLocatorToId());
        int docTypeId = MPPMRP.getDocType(getCtx(),
                MDocType.DOCBASETYPE_DistributionOrder,
                locatorTo.getAD_Org_ID(), Env.getAD_User_ID(getCtx()),
                get_TrxName());
        MBPartner partner = MBPartner.get(mrp.getCtx(), getBusinessPartnerId());

        MDDOrder order = new MDDOrder(getCtx(), 0, get_TrxName());
        order.setAD_Org_ID(mrp.getAD_Org_ID());
        order.addDescription("Generated by MRP");
        order.setC_BPartner_ID(getBusinessPartnerId());
        order.setAD_User_ID(partner.getPrimaryAD_User_ID());
        order.setC_DocType_ID(docTypeId);
        order.setM_Warehouse_ID(getWarehouseinTransitId());
        if (getReferenceNo() != null)
            order.setPOReference(getReferenceNo());
        order.setDocStatus(MDDOrder.DOCSTATUS_Drafted);
        order.setDocAction(MDDOrder.DOCACTION_Complete);
        order.setDateOrdered(mrp.getDateFinishSchedule());
        order.setDatePromised(mrp.getDatePromised());
        order.setM_Shipper_ID(getShipperId());
        if (getPriority() != null)
            order.setPriorityRule(getPriority());
        order.setIsInDispute(false);
        order.setIsInTransit(false);
        order.setSalesRep_ID(mrp.getPlanner_ID());
        order.saveEx();

        MDDOrderLine orderLine = new MDDOrderLine(getCtx(), 0, get_TrxName());
        orderLine.setDD_Order_ID(order.getDD_Order_ID());
        orderLine.setAD_Org_ID(locatorTo.getAD_Org_ID());
        orderLine.setM_Locator_ID(locatorFrom.getM_Locator_ID());
        orderLine.setM_LocatorTo_ID(locatorTo.getM_Locator_ID());
        orderLine.setM_Product_ID(mrp.getM_Product_ID());
        orderLine.setDateOrdered(order.getDateOrdered());
        orderLine.setDatePromised(mrp.getDatePromised());
        orderLine.setQtyEntered(getSelectionAsBigDecimal(mrp.getPP_MRP_ID(), "MRP_" + MPPMRP.COLUMNNAME_Qty));
        orderLine.setQtyOrdered(getSelectionAsBigDecimal(mrp.getPP_MRP_ID(), "MRP_" + MPPMRP.COLUMNNAME_Qty));
        orderLine.setConfirmedQty(getSelectionAsBigDecimal(mrp.getPP_MRP_ID(), "DL_" + MDDOrderLine.COLUMNNAME_ConfirmedQty));

        orderLine.setTargetQty(MPPMRP.getQtyReserved(getCtx(), locatorTo.getM_Warehouse_ID(), mrp.getM_Product_ID(), mrp.getDateStartSchedule(), get_TrxName()));
        orderLine.setIsInvoiced(false);
        orderLine.saveEx();

        order.processIt(DocAction.ACTION_Prepare);
        order.saveEx();
        return order;
    }

    private MRequisition createRequisition(MPPMRP mrp) {
        if (MPPMRP.ORDERTYPE_ManufacturingOrder.equals(mrp.getOrderType())) {
            int docTypeId = MPPMRP.getDocType(getCtx(),
                    MDocType.DOCBASETYPE_PurchaseRequisition,
                    mrp.getAD_Org_ID(), Env.getAD_User_ID(getCtx()),
                    get_TrxName());
            // Get PriceList from BPartner/Group - teo_sarca, FR [ 2829476 ]
            int priceListId = -1;
            final String sql = "SELECT COALESCE(bp."
                    + MBPartner.COLUMNNAME_PO_PriceList_ID
                    + ",bpg."
                    + X_C_BP_Group.COLUMNNAME_PO_PriceList_ID
                    + ")"
                    + " FROM C_BPartner bp"
                    + " INNER JOIN C_BP_Group bpg ON (bpg.C_BP_Group_ID=bp.C_BP_Group_ID)"
                    + " WHERE bp.C_BPartner_ID=?";
            priceListId = DB.getSQLValueEx(get_TrxName(), sql, getBusinessPartnerId());

            MRequisition requisition = new MRequisition(getCtx(), 0, get_TrxName());
            requisition.setAD_Org_ID(mrp.getAD_Org_ID());
            requisition.setAD_User_ID(mrp.getPlanner_ID());
            requisition.setDateDoc(mrp.getDateStartSchedule());
            requisition.setDateRequired(mrp.getDatePromised());
            // req.setDescription(""); // TODO: add translation
            requisition.setM_Warehouse_ID(mrp.getM_Warehouse_ID());
            requisition.setC_DocType_ID(docTypeId);
            if (priceListId > 0)
                requisition.setM_PriceList_ID(priceListId);
            requisition.saveEx();

            MRequisitionLine requisitionLine = new MRequisitionLine(requisition);
            requisitionLine.setLine(10);
            requisitionLine.setAD_Org_ID(mrp.getAD_Org_ID());
            requisitionLine.setC_BPartner_ID(mrp.getC_BPartner_ID());
            requisitionLine.setM_Product_ID(mrp.getM_Product_ID());
            requisitionLine.setPrice();
            if (getReferenceNo() != null)
                requisitionLine.setDescription(getReferenceNo());
            requisitionLine.setPriceActual(Env.ZERO);
            requisitionLine.setQty(mrp.getQty());
            requisitionLine.saveEx();

            requisition.processIt(DocAction.ACTION_Prepare);
            requisition.saveEx();
            return  requisition;
        }
        return null;
    }

    private MPPOrder createManufacturingOrder(MPPMRP mrp) {
        int docTypeId = MPPMRP.getDocType(
                getCtx(),
                MDocType.DOCBASETYPE_ManufacturingOrder,
                mrp.getAD_Org_ID(),
                mrp.getPlanner_ID(),
                get_TrxName());

        MPPOrder order = new MPPOrder(getCtx(), 0, get_TrxName());
        if (getReferenceNo() != null)
            order.addDescription(getReferenceNo());
        order.setAD_Org_ID(mrp.getAD_Org_ID());
        order.setLine(10);
        order.setC_DocTypeTarget_ID(docTypeId);
        order.setC_DocType_ID(docTypeId);
        if (getPriority() != null)
            order.setPriorityRule(getPriority());

        order.setS_Resource_ID(getResourcePlantId());
        order.setM_Warehouse_ID(mrp.getM_Warehouse_ID());
        order.setM_Product_ID(mrp.getM_Product_ID());
        order.setM_AttributeSetInstance_ID(0);
        order.setPP_Product_BOM_ID(getBOMFormulaId());
        order.setAD_Workflow_ID(getWorkflowId());
        order.setPlanner_ID(mrp.getPlanner_ID());
        order.setDateOrdered(mrp.getDateOrdered());
        order.setDatePromised(mrp.getDatePromised());
        order.setDateStartSchedule(mrp.getDateStartSchedule());
        order.setDateFinishSchedule(mrp.getDateFinishSchedule());
        order.setQty(getSelectionAsBigDecimal(mrp.getPP_MRP_ID(), "MRP_" + MPPMRP.COLUMNNAME_Qty));
        order.setC_UOM_ID(mrp.getM_Product().getC_UOM_ID());
        order.setYield(Env.ZERO);
        order.setScheduleType(MPPMRP.TYPEMRP_Demand);
        order.setPriorityRule(MPPOrder.PRIORITYRULE_Medium);
        order.setDocAction(MPPOrder.DOCACTION_Complete);
        order.saveEx();

        order.processIt(DocAction.ACTION_Prepare);
        order.saveEx();
        return order;
    }

    private void executeRequisitionApproval(MPPMRP mrp) {
        MRequisition requisition = (MRequisition) mrp.getM_Requisition();
        Timestamp dateRequired = getSelectionAsTimestamp(mrp.getPP_MRP_ID(), "R_" + MRequisition.COLUMNNAME_DateRequired);
        if (dateRequired != null)
            requisition.setDateRequired(dateRequired);

        if (mrp.is_Changed())
            ;
        {
            validateChanges(mrp, MPPMRP.COLUMNNAME_Priority, requisition, MRequisition.COLUMNNAME_PriorityRule);
            requisition.saveEx();

            MRequisitionLine requisitionLine = (MRequisitionLine) mrp.getM_RequisitionLine();
            validateChanges(mrp, MPPMRP.COLUMNNAME_C_BPartner_ID, requisitionLine, MRequisitionLine.COLUMNNAME_C_BPartner_ID);
            validateChanges(mrp, MPPMRP.COLUMNNAME_Qty, requisitionLine, MRequisitionLine.COLUMNNAME_Qty);
            requisitionLine.saveEx();
        }

        requisition.processIt(DocAction.ACTION_Prepare);
        requisition.saveEx();
    }

    private void executeDistributionOrderApproval(MPPMRP mrp) {
        MDDOrder order = (MDDOrder) mrp.getDD_Order();
        if (mrp.is_Changed()) {
            validateChanges(mrp, MPPMRP.COLUMNNAME_Priority, order, MDDOrder.COLUMNNAME_PriorityRule);
            order.saveEx();

            MDDOrderLine orderLine = (MDDOrderLine) mrp.getDD_OrderLine();
            orderLine.setQty(getSelectionAsBigDecimal(mrp.getPP_MRP_ID(), "MRP_" + MPPMRP.COLUMNNAME_Qty));
            Timestamp datePromised = getSelectionAsTimestamp(mrp.getPP_MRP_ID(), MDDOrder.COLUMNNAME_DatePromised);
            if (datePromised != null)
                orderLine.setDatePromised(datePromised);
            orderLine.saveEx();
        }

        if (getShipperId() > 0)
            order.setM_Shipper_ID(getShipperId());

        order.processIt(DocAction.ACTION_Prepare);
        order.saveEx();

    }

    private void executeManufacturingOrderApproval(MPPMRP mrp) {
        boolean createMO = false;
        if (getBOMFormulaId() > 0)
            createMO = true;
        if (getWorkflowId() > 0)
            createMO = true;
        MPPOrder currentMfgOrder = (MPPOrder) mrp.getPP_Order();
        MPPOrder newMfgOrder;
        if (createMO) {
            newMfgOrder = new MPPOrder(mrp.getCtx(), 0, get_TrxName());
            newMfgOrder.copyValues(currentMfgOrder, newMfgOrder);
            if (getBOMFormulaId() > 0)
                newMfgOrder.setPP_Product_BOM_ID(getBOMFormulaId());
            if (getWorkflowId() > 0)
                newMfgOrder.setAD_Workflow_ID(getWorkflowId());
            newMfgOrder.saveEx();
            currentMfgOrder.deleteEx(true);
            currentMfgOrder = newMfgOrder;

        }

        if (mrp.is_Changed()) {
            validateChanges(mrp, MPPMRP.COLUMNNAME_Priority, currentMfgOrder, MPPOrder.COLUMNNAME_PriorityRule);
            validateChanges(mrp, MPPMRP.COLUMNNAME_DateStartSchedule, currentMfgOrder, MPPOrder.COLUMNNAME_DateStartSchedule);
            validateChanges(mrp, MPPMRP.COLUMNNAME_DatePromised, currentMfgOrder, MPPOrder.COLUMNNAME_DatePromised);
            currentMfgOrder.setQty(getSelectionAsBigDecimal(mrp.getPP_MRP_ID(), "MRP_" + MPPMRP.COLUMNNAME_Qty));
            currentMfgOrder.saveEx();
        }
        currentMfgOrder.processIt(DocAction.ACTION_Prepare);
        currentMfgOrder.saveEx();
    }

    private void saveBrowseValues(PO po, String alias) {
        LinkedHashMap<String, Object> values = getSelectionValues().get(po.get_ID());
        for (Entry<String, Object> entry : values.entrySet()) {
            String columnName = entry.getKey();
            if (columnName.contains(alias.toUpperCase() + "_")) {
                columnName = columnName.substring(columnName.indexOf("_") + 1);
                po.set_ValueOfColumn(columnName, entry.getValue());
            }
        }
    }

    private void validateChanges(MPPMRP mrp, String columnSource, PO po,
                                 String columnTarget) {
        if (mrp.is_ValueChanged(columnSource))
            po.set_ValueOfColumn(columnTarget, mrp.get_Value(columnSource));
    }
} // Create
