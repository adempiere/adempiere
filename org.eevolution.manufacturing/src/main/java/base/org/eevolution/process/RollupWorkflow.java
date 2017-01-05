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
 *                 Bogdan Ioan, www.arhipac.ro                                *
 *****************************************************************************/

package org.eevolution.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.adempiere.engine.CostEngine;
import org.adempiere.engine.CostDimension;
import org.adempiere.engine.CostEngineFactory;
import org.adempiere.engine.StandardCostingMethod;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostType;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWorkflow;
import org.eevolution.model.MPPProductPlanning;
import org.eevolution.model.RoutingService;
import org.eevolution.model.RoutingServiceFactory;

/**
 * RollUp of Cost Manufacturing Workflow
 * This process calculate the Labor, Overhead, Burden Cost
 *
 * @author Victor Perez, e-Evolution, S.C.
 * @author Bogdan Ioan, www.arhipac.ro
 *         <li>BF [ 2093001 ] Error in Cost Workflow & Process Details
 * @version $Id: RollupWorkflow.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class RollupWorkflow extends RollupWorkflowAbstract {
    /* Costing Method 	*/
    private RoutingService routingService;


    protected void prepare() {
        super.prepare();
    }    //	prepare

    protected String doIt() throws Exception {
        //Get account schema
        MAcctSchema accountSchema = MAcctSchema.get(getCtx(), getAccountingSchemaId());
        //Get cost type
        MCostType costType = MCostType.get(getCtx(), getCostTypeId());
        //Get cost element to process
        final List<MCostElement>  costElements = getCostElementId() > 0 ?
                Arrays.asList(MCostElement.get(getCtx(), getCostElementId())) :
                MCostElement.getCostElement(getCtx(), get_TrxName());
        routingService = RoutingServiceFactory.get().getRoutingService(getAD_Client_ID());
        //Iterate product ids based on parameters
        Arrays.stream(getProductIds())
                .filter(productId -> productId > 0)
                .forEach(productId -> {
                    MProduct product = MProduct.get(getCtx() , productId);
                    log.info("Product: " + product);
                    int workflowId = 0;
                    MPPProductPlanning productPlanning = null;
                    if (workflowId <= 0)
                        workflowId = MWorkflow.getWorkflowSearchKey(product);
                    if (workflowId <= 0) {
                        productPlanning = MPPProductPlanning.find(getCtx(), getOrganizationId(), getWarehouseId(), getResourcePlantId(), product.get_ID(), get_TrxName());

                        if (productPlanning != null)
                            workflowId = productPlanning.getAD_Workflow_ID();
                        else
                            createNotice(product, "@NotFound@ @PP_Product_Planning_ID@");
                    }

                    if (workflowId <= 0)
                        createNotice(product, "@NotFound@ @AD_Workflow_ID@");
                    else {
                        //Execute rollup transaction
                        Trx.run(new TrxRunnable() {

                            MAcctSchema accountSchema;
                            MCostType costType;
                            MProduct product;
                            MPPProductPlanning productPlanning;
                            int workflowId;

                            public TrxRunnable setParameters(MAcctSchema accountSchema , MCostType costType, MProduct product, MPPProductPlanning productPlanning, int workflowId) {
                                this.accountSchema = accountSchema;
                                this.costType = costType;
                                this.product = product;
                                this.productPlanning = productPlanning;
                                this.workflowId = workflowId;
                                return this;
                            }

                            public void run(String trxName) {
                                MWorkflow workflow = new MWorkflow(getCtx(), workflowId, trxName);
                                //Iterate Cost elements
                                costElements.stream()
                                        .filter(costElement -> costElement != null && CostEngine.isActivityControlElement(costElement))
                                        .forEach(costElement -> {
                                            rollup(accountSchema , costType , costElement, product, workflow, trxName);
                                        });

                                if (productPlanning != null) {
                                    productPlanning.load(trxName);
                                    productPlanning.setYield(workflow.getYield());
                                    productPlanning.saveEx();
                                }
                            }
                        }.setParameters(accountSchema , costType , product, productPlanning, workflowId));
                    }
                });
        return "@OK@";
    }

    /**
     * get products
     * @return
     */
    private int[] getProductIds() {
        List<Object> params = new ArrayList<Object>();
        StringBuffer whereClause = new StringBuffer("AD_Client_ID=?");
        params.add(getAD_Client_ID());

        whereClause.append(" AND (").append(MProduct.COLUMNNAME_ProductType).append("=?");
        params.add(MProduct.PRODUCTTYPE_Item);

        whereClause.append(" OR ").append(MProduct.COLUMNNAME_ProductType).append("=?");
        params.add(MProduct.PRODUCTTYPE_Resource);

        whereClause.append(") AND ").append(MProduct.COLUMNNAME_IsBOM).append("=?");
        params.add(true);

        if (getProductId() > 0) {
            whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_ID).append("=?");
            params.add(getProductId());
        }
        if (getProductCategoryId() > 0) {
            whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_Category_ID).append("=?");
            params.add(getProductCategoryId());
        }
        if (getProductClassId() > 0)
        {
            whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_Class_ID).append("=?");
            params.add(getProductClassId());
        }
        if (getProductGroupId() >0)
        {
            whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_Group_ID).append("=?");
            params.add(getProductGroupId());
        }
        if (getProductClassificationId() > 0)
        {
            whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_Classification_ID).append("=?");
            params.add(getProductClassificationId());
        }

        return new Query(getCtx(), MProduct.Table_Name, whereClause.toString(), get_TrxName())
                .setOrderBy(MProduct.COLUMNNAME_LowLevel)
                .setParameters(params)
                .getIDs();
    }

    /**
     * Execute rollup process
     * @param accountSchema
     * @param costType
     * @param costElement
     * @param product
     * @param workflow
     * @param trxName
     */
    protected void rollup(MAcctSchema accountSchema , MCostType costType , MCostElement costElement ,  MProduct product, MWorkflow workflow, String trxName) {
        log.info("Workflow: " + workflow);
        workflow.setCost(Env.ZERO);
        double yield = 1;
        int queuingTime = 0;
        int setupTime = 0;
        int duration = 0;
        int waitingTime = 0;
        int movingTime = 0;
        int workingTime = 0;

        MWFNode[] nodes = workflow.getNodes(false, getAD_Client_ID());
        for (MWFNode node : nodes) {
            node.setCost(Env.ZERO);
            if (node.getYield() != 0) {
                yield = yield * ((double) node.getYield() / 100);
            }
            // We use node.getDuration() instead of m_routingService.estimateWorkingTime(node) because
            // this will be the minimum duration of this node. So even if the node have defined units/cycle
            // we consider entire duration of the node.
            long nodeDuration = node.getDuration();
            queuingTime += node.getQueuingTime();
            setupTime += node.getSetupTime();
            duration += nodeDuration;
            waitingTime += node.getWaitingTime();
            movingTime += node.getMovingTime();
            workingTime += node.getWorkingTime();
        }
        workflow.setCost(Env.ZERO);
        workflow.setYield((int) (yield * 100));
        workflow.setQueuingTime(queuingTime);
        workflow.setSetupTime(setupTime);
        workflow.setDuration(duration);
        workflow.setWaitingTime(waitingTime);
        workflow.setMovingTime(movingTime);
        workflow.setWorkingTime(workingTime);

        final CostDimension costDimension = new CostDimension(
                product,
                accountSchema,
                costType.getM_CostType_ID(),
                getOrganizationId(),
                getWarehouseId(),
                0,
                costElement.get_ID());
        MCost cost = MCost.getOrCreate(product, 0 , accountSchema , getOrganizationId() , getWarehouseId() , costType.getM_CostType_ID() , costElement.getM_CostElement_ID());
        cost.setFutureCostPrice(BigDecimal.ZERO);
        if (!cost.isCostFrozen())
            cost.setCurrentCostPrice(BigDecimal.ZERO);

        AtomicReference<BigDecimal> segmentCost = new AtomicReference<>(Env.ZERO);
        Arrays.stream(nodes)
                .filter(node -> node != null)
                .forEach(node -> {
                    final CostEngine costEngine = CostEngineFactory.getCostEngine(node.getAD_Client_ID());
                    final BigDecimal rate = StandardCostingMethod.getResourceActualCostRate(node.getS_Resource_ID(), costDimension, trxName);
                    final BigDecimal baseValue = routingService.getResourceBaseValue(node.getS_Resource_ID(), node);
                    BigDecimal nodeCostPrecision = baseValue.multiply(rate);
                    BigDecimal nodeCost;
                    if (nodeCostPrecision.scale() > accountSchema.getCostingPrecision())
                        nodeCost = nodeCostPrecision.setScale(accountSchema.getCostingPrecision(), RoundingMode.HALF_UP);
                    else
                        nodeCost = nodeCostPrecision;

                    segmentCost.updateAndGet(costAmt -> costAmt.add(nodeCost));
                    log.info(Msg.parseTranslation(getCtx(), " @M_CostElement_ID@ : ") + costElement.getName() + ", Node=" + node
                            + ", BaseValue=" + baseValue + ", rate=" + rate
                            + ", nodeCost=" + nodeCost + " => Cost=" + segmentCost);
                    // Update AD_WF_Node.Cost:
                    node.setCost(node.getCost().add(nodeCost));

                });
        cost.setFutureCostPrice(segmentCost.get());
        if (!cost.isCostFrozen())
            cost.setCurrentCostPrice(segmentCost.get());
        cost.saveEx();
        // Update Workflow cost
        workflow.setCost(workflow.getCost().add(segmentCost.get()));

        // Save Workflow & Nodes
        Arrays.stream(nodes)
                .filter(node -> node != null)
                .forEach(node -> node.saveEx());
        workflow.saveEx();
        log.info("Product: " + product.getName() + " WFCost: " + workflow.getCost());
    }

    /**
     * Create Cost Rollup Notice
     * @param product
     * @param msg
     */
    private void createNotice(MProduct product, String msg) {
        String productValue = product != null ? product.getValue() : "-";
        addLog("WARNING: Product " + productValue + ": " + msg);
    }
}
