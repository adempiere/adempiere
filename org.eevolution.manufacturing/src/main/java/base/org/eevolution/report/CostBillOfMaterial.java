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
package org.eevolution.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.engine.CostEngine;
import org.adempiere.engine.CostEngineFactory;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;
import org.eevolution.model.X_T_BOMLine;

/**
 * Cost Multi-Level BOM & Formula Review
 *
 * @author victor.perez@e-evolution.com
 * @author Teo Sarca, www.arhipac.ro
 */
public class CostBillOfMaterial extends CostBillOfMaterialAbstract {
    private static final String LEVELS = "....................";
    private boolean isImplosion = false;
    private int levelNo = 0;
    private int seqNo = 0;

    protected void prepare() {
        super.prepare();
    }

    /**
     * Execute process
     *
     * @return
     * @throws Exception
     */
    protected String doIt() throws Exception {
        MAcctSchema accountSchema = MAcctSchema.get(getCtx(), getAccountingSchemaId());
        explodeProduct(accountSchema, getProductId(), false);
        return "@Ok@";
    } // doIt

    /**
     * * Generate an Explosion for this product
     *
     * @param accountSchema
     * @param productId
     * @param isComponent
     */
    private void explodeProduct(MAcctSchema accountSchema, int productId, boolean isComponent) {
        MProduct product = MProduct.get(getCtx(), productId);
        List<MPPProductBOM> boms = getBOMs(product, isComponent);
        if (!isComponent && boms.size() == 0) {
            throw new AdempiereException("@Error@ Product is not a BOM");
        }
        boms.stream()
                .filter(bom -> bom != null)
                .forEach(bom -> {
                    // Create header
                    if (!isComponent)
                        createLines(accountSchema, bom, null);
                    levelNo++;
                    // Create Lines:
                    Arrays.stream(bom.getLines())
                            .filter(bomLine -> bomLine != null && bomLine.isActive())
                            .forEach(bomLine -> {
                                createLines(accountSchema, bom, bomLine);
                                explodeProduct(accountSchema, bomLine.getM_Product_ID(), true);
                            });
                    levelNo--;
                });
    }

    /**
     * Get BOMs for given product
     *
     * @param product
     * @param includeAlternativeBOMs
     * @return list of MPPProductBOM
     */
    private List<MPPProductBOM> getBOMs(MProduct product, boolean includeAlternativeBOMs) {
        ArrayList<Object> params = new ArrayList<Object>();
        StringBuffer whereClause = new StringBuffer();
        whereClause.append(MPPProductBOM.COLUMNNAME_M_Product_ID).append("=?");
        params.add(product.get_ID());
        // Allow alternative BOMs
        if (includeAlternativeBOMs) {
            whereClause.append(" AND ").append(MPPProductBOM.COLUMNNAME_Value).append("=?");
            params.add(product.getValue());
        }
        return new Query(getCtx(), MPPProductBOM.Table_Name, whereClause.toString(), null)
                .setParameters(params)
                .setOnlyActiveRecords(true)
                .setOrderBy(MPPProductBOM.COLUMNNAME_Value)
                .list();
    }

    /**
     * createLines
     *
     * @param bom
     * @param bomLine
     */
    private void createLines(MAcctSchema accountSchema, MPPProductBOM bom, MPPProductBOMLine bomLine) {
        MProduct product;
        BigDecimal qty;
        if (bomLine != null) {
            product = MProduct.get(getCtx(), bomLine.getM_Product_ID());
            qty = bomLine.getQty();
        } else if (bom != null) {
            product = MProduct.get(getCtx(), bom.getM_Product_ID());
            qty = Env.ONE;
        } else {
            throw new AdempiereException("@NotFound@ @PP_Product_BOM_ID@");
        }
        //for (MCostElement costElement : getCostElements())
        getCostElements().stream()
                .filter(costElement -> costElement != null)
                .forEach(costElement -> {
                    X_T_BOMLine reportBOMLine = new X_T_BOMLine(getCtx(), 0, get_TrxName());
                    reportBOMLine.setAD_Org_ID(getOrganizationId());
                    reportBOMLine.setM_Warehouse_ID(getWarehouseId());
                    reportBOMLine.setSel_Product_ID(getProductId());
                    reportBOMLine.setImplosion(isImplosion);
                    reportBOMLine.setC_AcctSchema_ID(getAccountingSchemaId());
                    reportBOMLine.setM_CostType_ID(getCostTypeId());
                    reportBOMLine.setCostingMethod(getCostingMethod());
                    reportBOMLine.setAD_PInstance_ID(getAD_PInstance_ID());
                    reportBOMLine.setM_CostElement_ID(costElement.get_ID());
                    reportBOMLine.setM_Product_ID(product.get_ID());
                    reportBOMLine.setM_Warehouse_ID(getWarehouseId());
                    reportBOMLine.setQtyBOM(qty);
                    //
                    reportBOMLine.setSeqNo(seqNo);
                    reportBOMLine.setLevelNo(levelNo);
                    reportBOMLine.setLevels(LEVELS.substring(0, levelNo) + levelNo);
                    //
                    // Set Costs:
                    BigDecimal currentCostPrice = Env.ZERO;
                    BigDecimal currentCostPriceLL = Env.ZERO;
                    BigDecimal futureCostPrice = Env.ZERO;
                    BigDecimal futureCostPriceLL = Env.ZERO;
                    final CostEngine engine = CostEngineFactory.getCostEngine(getAD_Client_ID());
                    List<MCost> costs = MCost.getByElement(
                            product,
                            accountSchema,
                            getCostTypeId(),
                            getOrganizationId(),
                            getWarehouseId(), // Warehouse
                            0, // ASI
                            costElement.getM_CostElement_ID());
                    boolean isCostFrozen = false;
                    for (MCost cost : costs) {
                        currentCostPrice = currentCostPrice.add(cost.getCurrentCostPrice());
                        currentCostPriceLL = currentCostPriceLL.add(cost.getCurrentCostPriceLL());
                        futureCostPrice = futureCostPrice.add(cost.getFutureCostPrice());
                        futureCostPriceLL = futureCostPriceLL.add(cost.getFutureCostPriceLL());
                        isCostFrozen = cost.isCostFrozen();
                    }
                    reportBOMLine.setCurrentCostPrice(currentCostPrice);
                    reportBOMLine.setCurrentCostPriceLL(currentCostPriceLL);
                    reportBOMLine.setFutureCostPrice(currentCostPrice);
                    reportBOMLine.setFutureCostPriceLL(currentCostPriceLL);
                    reportBOMLine.setIsCostFrozen(isCostFrozen);
                    //
                    // Reference
                    if (bomLine != null) {
                        reportBOMLine.setPP_Product_BOM_ID(bomLine.getPP_Product_BOM_ID());
                        reportBOMLine.setPP_Product_BOMLine_ID(bomLine.getPP_Product_BOMLine_ID());
                    } else if (bom != null) {
                        reportBOMLine.setPP_Product_BOM_ID(bom.getPP_Product_BOM_ID());
                    }
                    //
                    reportBOMLine.saveEx();
                    seqNo++;
                });
    }

    private List<MCostElement> costElements = null;

    public List<MCostElement> getCostElements() {
        if (costElements == null)
            costElements = MCostElement.getCostElement(getCtx(), get_TrxName());

        return costElements;
    }
}
