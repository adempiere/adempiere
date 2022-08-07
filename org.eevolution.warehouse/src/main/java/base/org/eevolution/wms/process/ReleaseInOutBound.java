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
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.adempiere.core.domains.models.X_C_BP_Group;
import org.adempiere.core.domains.models.X_C_DocType;
import org.adempiere.core.domains.models.X_DD_Order;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.NoVendorForProductException;
import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.MRequisition;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MStorage;
import org.compiere.model.MTable;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.print.MPrintFormat;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.wf.MWorkflow;
import org.eevolution.wms.engine.WMRuleEngine;
import org.eevolution.manufacturing.exceptions.NoBPartnerLinkedforOrgException;
import org.eevolution.manufacturing.exceptions.NoPlantForWarehouseException;
import org.eevolution.manufacturing.model.MPPMRP;
import org.eevolution.manufacturing.model.MPPOrder;
import org.eevolution.manufacturing.model.MPPProductBOM;
import org.eevolution.manufacturing.model.MPPProductPlanning;
import org.eevolution.distribution.model.MDDOrder;
import org.eevolution.distribution.model.MDDOrderLine;
import org.eevolution.wms.model.MWMInOutBound;
import org.eevolution.wms.model.MWMInOutBoundLine;

/**
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * @version $Id: $
 */
public class ReleaseInOutBound extends ReleaseInOutBoundAbstract {
    private Timestamp today = new Timestamp(System.currentTimeMillis());
    private MDDOrder orderDistribution;

    /**
     * Get Parameters
     */
    @Override
    protected void prepare() {
        super.prepare();
    }

    /**
     * Process - Generate Export Format
     *
     * @return info
     */
    @Override
    protected String doIt() throws Exception {
        MLocator outBoundLocator = Optional.ofNullable(new MLocator(getCtx(), getLocatorId(), get_TrxName())).orElseThrow(() -> new AdempiereException("@M_Locator_ID@ @NotFound@"));
        List<MWMInOutBoundLine> outBoundLines = (List<MWMInOutBoundLine>) getInstancesForSelection(get_TrxName());
        Hashtable<Integer, MWMInOutBound> outboundOrders = new Hashtable<>();
        //Complete Outbound Order
        outBoundLines.forEach(outboundLine -> {
            outboundLine.setM_LocatorTo_ID(outBoundLocator.getM_Locator_ID());
            outboundLine.saveEx();
            MWMInOutBound outboundOrder = outboundLine.getParent();
            if (!outboundOrders.contains(outboundOrder.get_ID()))
                outboundOrders.put(outboundOrder.get_ID(), outboundOrder);
        });
        // Complete selected order
        outboundOrders.forEach((key, outboundOrder) -> {
            if (DocAction.STATUS_Drafted.equals(outboundOrder.getDocStatus()) || DocAction.STATUS_InProgress.equals(outboundOrder.getDocStatus())) {
                outboundOrder.setDocAction(DocAction.ACTION_Complete);
                outboundOrder.processIt(DocAction.ACTION_Complete);
                outboundOrder.saveEx();
            }
        });

        outBoundLines.forEach(outboundLine -> {
            // if the locator is same to pick then the storage are in outbound locator not is necessary create other Distribution Order
            if (outboundLine.getDD_OrderLine_ID() > 0) {
                MDDOrderLine orderDistributionLine = new MDDOrderLine(getCtx(), outboundLine.getDD_OrderLine_ID(), get_TrxName());
                if (orderDistributionLine.getWM_InOutBoundLine_ID() <= 0) {
                    orderDistributionLine.setWM_InOutBoundLine_ID(orderDistributionLine.getWM_InOutBoundLine_ID());
                    orderDistributionLine.saveEx();
                }
                if (orderDistributionLine.getM_LocatorTo_ID() == outboundLine.getM_LocatorTo_ID())
                    return;
            }
            BigDecimal qtySupply = createDistributionOrder(outboundLine);
            if (isCreateSupply() && qtySupply.signum() > 0) {
                Env.setContext(outboundLine.getCtx(), "IsCreateSupply", "Y");
                createSupply(outboundLine, qtySupply);
            }
        });

        Optional.ofNullable(getDocAction()).flatMap(docAction -> Optional.ofNullable(orderDistribution)).ifPresent(order -> {
            order.setDocAction(getDocAction());
            order.processIt(DocAction.ACTION_Complete);
            order.saveEx();
        });
        Optional.ofNullable(orderDistribution).ifPresent(order -> {
            if (isPrintPickList()) {
            	printDocument(orderDistribution, getPrintFormatId("DistributionOrder_Header  ** TEMPLATE **", "DD_Order_Header_v"), false);
            }
        });
        Optional<String> createdDescription =  Optional.ofNullable(orderDistribution).map(order -> "@Created@ " + order.getDocumentInfo());
        return createdDescription.orElse("");
    }
    
    private int getPrintFormatId(String formatName, String tableName) {
		return MPrintFormat.getPrintFormat_ID(formatName, MTable.getTable_ID(tableName), getAD_Client_ID());
	}

    /**
     * create Distribution Order to performance a Pick List
     *
     * @param outboundLine Outbound Line
     * @return Quantity that was not covert for inventory
     */
    private BigDecimal createDistributionOrder(MWMInOutBoundLine outboundLine) {
        int shipperId = 0;
        WMRuleEngine engineRule = WMRuleEngine.get();
        List<MStorage> storageList = engineRule.getStorage(outboundLine, getAreaTypeId(), getSectionTypeId());
        AtomicReference<BigDecimal> qtySupply = new AtomicReference<>(BigDecimal.ZERO);
        if (storageList.size() > 0) {
            //get the warehouse in transit
            MLocator outboundLocator = MLocator.get(outboundLine.getCtx(), outboundLine.getM_LocatorTo_ID());
            List<MWarehouse> transitWarehouse = Arrays.asList(MWarehouse.getInTransitForOrg(getCtx(), outboundLocator.getAD_Org_ID()));
            if (transitWarehouse.isEmpty())
                throw new AdempiereException("@M_Warehouse_ID@ @IsInTransit@ @NotFound@");

            //Org Must be linked to BPartner
            MOrg org = MOrg.get(getCtx(), outboundLocator.getAD_Org_ID());
            int partnerId = org.getLinkedC_BPartner_ID(get_TrxName());
            if (partnerId <= 0)
                throw new NoBPartnerLinkedforOrgException(org);

            MBPartner partner = MBPartner.get(getCtx(), partnerId);
            if (orderDistribution == null) {
                orderDistribution = new MDDOrder(getCtx(), 0, get_TrxName());
            orderDistribution.setAD_Org_ID(outboundLocator.getAD_Org_ID());
            orderDistribution.setC_BPartner_ID(partnerId);
            orderDistribution.setDescription(Msg.parseTranslation(getCtx(), "@Generate@ @From@ " +outboundLine.getParent().getDocumentInfo()));
            if (getDocTypeId() > 0)
                orderDistribution.setC_DocType_ID(getDocTypeId());
            else
                orderDistribution.setC_DocType_ID(MDocType.getDocType(X_C_DocType.DOCBASETYPE_DistributionOrder));

            orderDistribution.setM_Warehouse_ID(transitWarehouse.stream().findFirst().get().get_ID());
            orderDistribution.setDocAction(Optional.ofNullable(getDocAction()).orElseGet(() -> X_DD_Order.DOCACTION_Prepare));
            List<MUser> users = Arrays.asList(MUser.getOfBPartner(getCtx(), partner.getC_BPartner_ID(), get_TrxName()));
            if (users.isEmpty())
                throw new AdempiereException("@AD_User_ID@ @NotFound@ @Value@ - @C_BPartner_ID@ : " + partner.getValue() + " - " + partner.getName());

            orderDistribution.setAD_User_ID(users.stream().findFirst().get().getAD_User_ID());
            orderDistribution.setDateOrdered(getToday());
            orderDistribution.setDatePromised(getToday());
            orderDistribution.setM_Shipper_ID(shipperId);
            orderDistribution.setM_FreightCategory_ID(outboundLine.getParent().getM_FreightCategory_ID());
            orderDistribution.setFreightCostRule(outboundLine.getParent().getFreightCostRule());
            orderDistribution.setFreightAmt(outboundLine.getParent().getFreightAmt());
            orderDistribution.setIsInDispute(false);
            orderDistribution.setIsInTransit(false);
            orderDistribution.setSalesRep_ID(getAD_User_ID());
            orderDistribution.setDocStatus(MDDOrder.DOCSTATUS_Drafted);
            orderDistribution.saveEx();
        }

            storageList.stream()
                    .filter(storage -> storage.getQtyOnHand().signum() > 0)
                    .forEach(storage -> {
                            BigDecimal balanceQtyToPick = outboundLine.getQtyToPick().subtract(qtySupply.get());
                            if (balanceQtyToPick.signum() > 0) {
                                MDDOrderLine orderLine = new MDDOrderLine(orderDistribution);
                                orderLine.setM_Locator_ID(storage.getM_Locator_ID());
                                orderLine.setM_LocatorTo_ID(outboundLine.getM_LocatorTo_ID());
                                orderLine.setC_UOM_ID(outboundLine.getC_UOM_ID());
                                orderLine.setM_Product_ID(outboundLine.getM_Product_ID());
                                orderLine.setDateOrdered(getToday());
                                orderLine.setDatePromised(outboundLine.getPickDate());
                                orderLine.setWM_InOutBoundLine_ID(outboundLine.getWM_InOutBoundLine_ID());
                                orderLine.setIsInvoiced(false);

                                if (balanceQtyToPick.compareTo(storage.getQtyOnHand()) < 0) {
                                    orderLine.setConfirmedQty(outboundLine.getQtyToPick());
                                    orderLine.setQtyEntered(outboundLine.getQtyToPick());
                                    orderLine.setQtyOrdered(outboundLine.getQtyToPick());
                                    orderLine.setTargetQty(outboundLine.getQtyToPick());
                                    orderLine.setM_AttributeSetInstance_ID(storage.getM_AttributeSetInstance_ID());
                                    orderLine.setM_AttributeSetInstanceTo_ID(storage.getM_AttributeSetInstance_ID());
                                    qtySupply.updateAndGet(supply -> supply.add(balanceQtyToPick));
                                } else {
                                    orderLine.setConfirmedQty(storage.getQtyOnHand());
                                    orderLine.setQtyEntered(storage.getQtyOnHand());
                                    orderLine.setQtyOrdered(storage.getQtyOnHand());
                                    orderLine.setTargetQty(storage.getQtyOnHand());
                                    orderLine.setM_AttributeSetInstance_ID(storage.getM_AttributeSetInstance_ID());
                                    orderLine.setM_AttributeSetInstanceTo_ID(storage.getM_AttributeSetInstance_ID());
                                    qtySupply.updateAndGet(supply -> supply.add(storage.getQtyOnHand()));
                                }
                                if (qtySupply.get().signum() > 0) {
                                    //Save the last location from a storage found
                                    outboundLine.setM_Locator_ID(storage.getM_Locator_ID());
                                    outboundLine.saveEx();
                                }
                                orderLine.setFreightAmt(outboundLine.getFreightAmt());
                                orderLine.setM_FreightCategory_ID(outboundLine.getM_FreightCategory_ID());
                                orderLine.setM_Shipper_ID(outboundLine.getM_Shipper_ID());
                                orderLine.saveEx();
                            }
                    });
        }
        return outboundLine.getQtyToPick().subtract(qtySupply.get());
    }

    /**
     * Create supply based in Out bound Line
     *
     * @param outBoundOrderLine Out bound Line
     * @param qtySupply         Quantity Supply
     */
    private void createSupply(MWMInOutBoundLine outBoundOrderLine, BigDecimal qtySupply) {
        Optional<MProduct> maybeProduct = Optional.ofNullable(MProduct.get(outBoundOrderLine.getCtx(), outBoundOrderLine.getM_Product_ID()));
        maybeProduct.ifPresent(product -> {
            if (product.isBOM())
                createManufacturingOrder(outBoundOrderLine, product, qtySupply);
            else if (product.isPurchased())
                createRequisition(outBoundOrderLine, product, qtySupply);
        });
    }

    /**
     * Create Requisition when the Is create supply is define as yes
     *
     * @param outboundLine OutboundLine
     * @param product      Product
     * @param QtyPlanned   Qty Planned
     */
    private MRequisition createRequisition(MWMInOutBoundLine outboundLine, MProduct product, BigDecimal QtyPlanned) {
        List<MProductPO> productPOs = Arrays.asList(MProductPO.getOfProduct(getCtx(), product.getM_Product_ID(), get_TrxName()));
        Optional<MProductPO> maybeProductPO = productPOs.stream().filter(productPO -> productPO.isCurrentVendor() && productPO.getC_BPartner_ID() > 0).findFirst();
        return maybeProductPO.map(productPO -> {
            final String sql = "SELECT COALESCE(bp." + MBPartner.COLUMNNAME_PO_PriceList_ID
                    + ",bpg." + X_C_BP_Group.COLUMNNAME_PO_PriceList_ID + ")"
                    + " FROM C_BPartner bp"
                    + " INNER JOIN C_BP_Group bpg ON (bpg.C_BP_Group_ID=bp.C_BP_Group_ID)"
                    + " WHERE bp.C_BPartner_ID=?";
            int priceListId = DB.getSQLValueEx(get_TrxName(), sql, productPO.getC_BPartner_ID());
            MLocator outboundLocator = MLocator.get(outboundLine.getCtx(), outboundLine.getM_LocatorTo_ID());
            MRequisition requisition = new MRequisition(getCtx(), 0, get_TrxName());
            requisition.setAD_Org_ID(outboundLocator.getAD_Org_ID());
            requisition.setAD_User_ID(getAD_User_ID());
            requisition.setDateRequired(outboundLine.getPickDate());
            requisition.setDescription(Msg.parseTranslation(getCtx(), "@Generated@ @From@ @WM_InOutBound_ID@"+outboundLine.getParent().getDocumentInfo()));
            requisition.setM_Warehouse_ID(outboundLocator.getM_Warehouse_ID());
            requisition.setC_DocType_ID(MDocType.getDocType(MDocType.DOCBASETYPE_PurchaseRequisition));
            if (priceListId > 0)
                requisition.setM_PriceList_ID(priceListId);
            requisition.saveEx();

            MRequisitionLine requisitionLine = new MRequisitionLine(requisition);
            requisitionLine.setLine(10);
            requisitionLine.setAD_Org_ID(outboundLocator.getAD_Org_ID());
            requisitionLine.setC_BPartner_ID(productPO.getC_BPartner_ID());
            requisitionLine.setM_Product_ID(product.getM_Product_ID());
            requisitionLine.setPrice();
            requisitionLine.setPriceActual(Env.ZERO);
            requisitionLine.setQty(QtyPlanned);
            requisitionLine.saveEx();


            Optional<MOrderLine> maybeOrderLine = Optional.ofNullable(new MOrderLine(getCtx(), outboundLine.getC_OrderLine_ID(), get_TrxName()));
            maybeOrderLine.ifPresent(orderLine -> {
                StringBuilder descriptionLine = new StringBuilder(Optional.ofNullable(orderLine.getDescription()).orElse(""));
                descriptionLine.append(" ").append(Msg.translate(getCtx(), MRequisition.COLUMNNAME_M_Requisition_ID)).append(" : ").append(requisition.getDocumentNo());
                orderLine.setDescription(descriptionLine.toString());
                orderLine.saveEx();
                StringBuilder descriptionOutboundLine = new StringBuilder(Optional.ofNullable(outboundLine.getDescription()).orElse(""));
                descriptionOutboundLine.append(" ").append(Msg.translate(outboundLine.getCtx(), MRequisition.COLUMNNAME_M_Requisition_ID)).append(" : ").append(requisition.getDocumentNo());
                outboundLine.setDescription(descriptionOutboundLine.toString());
                outboundLine.saveEx();
            });
            return requisition;
        }).orElseThrow(() -> new NoVendorForProductException(""));
    }

    /**
     * Create Manufacturing Order when the Is create supply is define as yes
     *
     * @param outBoundOrderLine Bound Line
     * @param product           Product
     * @param qtySupply         Quantity to Supply
     * @return
     */
    private MPPOrder createManufacturingOrder(MWMInOutBoundLine outBoundOrderLine, MProduct product, BigDecimal qtySupply) {
        Optional<MPPOrder> maybeOrder = Optional.ofNullable(
                MPPOrder.forC_OrderLine_ID(outBoundOrderLine.getCtx(), outBoundOrderLine.getC_OrderLine_ID(),
                        product.get_ID(),
                        outBoundOrderLine.get_TrxName()));
        return maybeOrder.map(order -> {
            order.setM_Shipper_ID(outBoundOrderLine.getParent().getM_Shipper_ID());
            order.setM_FreightCategory_ID(outBoundOrderLine.getParent().getM_FreightCategory_ID());
            order.setFreightCostRule(outBoundOrderLine.getParent().getFreightCostRule());
            return order;
        }).orElseGet(() -> {
            Optional<MPPProductPlanning> maybeProductPlanning = Optional.ofNullable(
                    MPPProductPlanning.find(getCtx(), outBoundOrderLine.getAD_Org_ID(), outBoundOrderLine.getM_Warehouse_ID(), 0, product.getM_Product_ID(), get_TrxName())
            );
            // get Product BOM
            MPPProductBOM bom = maybeProductPlanning.map(MPPProductPlanning::getPP_Product_BOM).orElseGet(() -> {
                Optional<MPPProductBOM> maybeDefaultBOM = Optional.ofNullable(MPPProductBOM.getDefault(product, get_TrxName()));
                return maybeDefaultBOM.orElseThrow(() -> new AdempiereException("@PP_Product_BOM_ID@ @NotFound@"));
            });
            // get Manufacturing Workflow
            MWorkflow workflow = maybeProductPlanning.map(MPPProductPlanning::getAD_Workflow).orElseGet(() -> {
                int workflowId =  MWorkflow.getWorkflowSearchKey(product);
                Optional<MWorkflow> maybeWorkflow = Optional.ofNullable(workflowId > 0 ? MWorkflow.get(getCtx(), MWorkflow.getWorkflowSearchKey(product)) : null);
                return maybeWorkflow.orElseThrow(() -> new AdempiereException("@AD_Workflow_ID@ @NotFound@ @To@ @M_Product_ID@  @Value@ : " + product.getValue() + " @Name@: " + product.getName()));
            });

            int plantId = MPPProductPlanning.getPlantForWarehouse(outBoundOrderLine.getM_Warehouse_ID());
            if (plantId <= 0)
                throw new NoPlantForWarehouseException(outBoundOrderLine.getM_Warehouse_ID());

            StringBuilder description = new StringBuilder(Msg.parseTranslation(getCtx(), "@Generated@ @From@ @WM_InOutBound_ID@"));
            description.append("  ").append(outBoundOrderLine.getParent().getDocumentInfo());
            //Create temporary Product Planning to Create Manufacturing Order
            MPPProductPlanning productPlanning = new MPPProductPlanning(getCtx(), 0, get_TrxName());
            productPlanning.setAD_Org_ID(outBoundOrderLine.getAD_Org_ID());
            productPlanning.setM_Product_ID(product.getM_Product_ID());
            productPlanning.setPlanner_ID(outBoundOrderLine.getParent().getSalesRep_ID());
            productPlanning.setPP_Product_BOM_ID(bom.getPP_Product_BOM_ID());
            productPlanning.setAD_Workflow_ID(workflow.getAD_Workflow_ID());
            productPlanning.setM_Warehouse_ID(outBoundOrderLine.getM_Warehouse_ID());
            productPlanning.setS_Resource_ID(plantId);
            MPPOrder order = MPPMRP.createMO(
                    productPlanning,
                    outBoundOrderLine.getC_OrderLine_ID(),
                    outBoundOrderLine.getM_AttributeSetInstance_ID(),
                    qtySupply,
                    outBoundOrderLine.getPickDate(),
                    outBoundOrderLine.getShipDate(),
                    description.toString()
            );
            Optional<MOrderLine> maybeOrderLine = Optional.ofNullable(new MOrderLine(getCtx(), outBoundOrderLine.getC_OrderLine_ID(), get_TrxName()));
            maybeOrderLine.ifPresent(orderLine -> {
                Optional.ofNullable(orderLine.getDescription()).ifPresent(descriptionLine -> description.append(descriptionLine));
                StringBuilder descriptionOrderLine = new StringBuilder(Msg.translate(orderLine.getCtx(), MPPOrder.COLUMNNAME_PP_Order_ID));
                descriptionOrderLine.append(" : ").append(Optional.ofNullable(order.getDocumentNo()).orElse(""));
                orderLine.setDescription(descriptionOrderLine.toString());
                orderLine.saveEx();
            });
            StringBuilder boundDescription = new StringBuilder(Optional.ofNullable(outBoundOrderLine.getDescription()).orElse(""));
            boundDescription.append(Msg.translate(getCtx(), MPPOrder.COLUMNNAME_PP_Order_ID)).append(" : ").append(Optional.ofNullable(order.getDocumentNo()).orElse(""));
            outBoundOrderLine.setDescription(boundDescription.toString());
            outBoundOrderLine.saveEx();
            return order;
        });
    }

    /**
     * get Today
     *
     * @return Today
     */
    protected Timestamp getToday() {
        return this.today;
    }
}
