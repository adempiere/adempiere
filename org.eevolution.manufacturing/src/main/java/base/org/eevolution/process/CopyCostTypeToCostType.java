/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.process;

import org.adempiere.engine.CostDimension;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostType;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.Trx;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Generated Process for (Copy Cost Type to Standard Cost)
 *
 * @author eEvolution www.e-evolutin.com , Victor Perez <victor.perez@e-evolution.com>
 * @version Release 3.8.0
 */
public class CopyCostTypeToCostType extends CopyCostTypeToCostTypeAbstract {


    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {
        if (getCostTypeId() == getCostTypeIdTo())
            throw new AdempiereException("@M_CostType_ID@ @NotValid@");

        MAcctSchema accountSchema = MAcctSchema.get(getCtx(), getAccountingSchemaId());
        MCostType costTypeFrom = MCostType.get(getCtx(), getCostTypeId());
        MCostType costTypeTo = MCostType.get(getCtx(), getCostTypeIdTo());
        MCostElement costElementFrom = MCostElement.get(getCtx(), getCostElementId());
        MCostElement costElementTo = MCostElement.get(getCtx(), getCostElementIdTo());

        Arrays.stream(getProductIds()).filter(productId -> productId > 0).forEach(productId -> {
            Trx.run(trxName -> {
                copyCostTypeToCostType(productId, accountSchema, costTypeFrom, costTypeTo, costElementFrom, costElementTo, trxName);
            });
        });

        return "@Ok@";
    }

    /**
     * Copy Cost Type to Cost Type
     *
     * @param productId
     * @param accountSchema
     * @param costTypeFrom
     * @param costTypeTo
     * @param costElementFrom
     * @param costElementTo
     * @param trxName
     */
    private void copyCostTypeToCostType(int productId, MAcctSchema accountSchema, MCostType costTypeFrom, MCostType costTypeTo, MCostElement costElementFrom, MCostElement costElementTo, String trxName) {
        MProduct product = MProduct.get(getCtx(), productId);
        CostDimension costDimensionFrom = new CostDimension(product, accountSchema, costTypeFrom.get_ID(), getOrganizationId(), getWarehouseId(), 0, costElementFrom.get_ID());
        Optional<MCost> costDimensionFromOptional = Optional.ofNullable(costDimensionFrom.toQuery(MCost.class, trxName).first());
        CostDimension costDimensionTo = new CostDimension(product, accountSchema, costTypeTo.get_ID(), getOrganizationId(), getWarehouseId(), 0, costElementTo.get_ID());
        Optional<MCost> costDimensionToOptional = Optional.ofNullable(costDimensionTo.toQuery(MCost.class, trxName).first());
        if (isUpdateCosting()) {
            // exist cost form and cost to or not exist cost to and exit cost from
            if (costDimensionToOptional.isPresent() && costDimensionFromOptional.isPresent()) {
                MCost costTo = costDimensionToOptional.get();
                if (MCostType.COSTINGMETHOD_StandardCosting.equals(costTypeFrom.getCostingMethod()) && costTo.isCostFrozen())
                    ;
                else {
                    costTo.setCurrentCostPrice(costDimensionFromOptional.get().getCurrentCostPrice());
                    costTo.saveEx();
                }
            } else if (!costDimensionToOptional.isPresent() && costDimensionFromOptional.isPresent()) {
                MCost costTo = MCost.getOrCreate(product, 0, accountSchema, getOrganizationId(), getWarehouseId(), costTypeTo.get_ID(), costElementTo.get_ID());
                if (MCostType.COSTINGMETHOD_StandardCosting.equals(costTypeFrom.getCostingMethod()) && costTo.isCostFrozen())
                    ;
                else {
                    costDimensionFromOptional.ifPresent(costFrom -> costTo.setCurrentCostPrice(costFrom.getCurrentCostPrice()));
                    costTo.saveEx();
                }
            } else if (costDimensionToOptional.isPresent() && !costDimensionFromOptional.isPresent()) // cost to and not exist cost from
            {
                MCost costTo = costDimensionToOptional.get();
                costTo.setCurrentCostPrice(BigDecimal.ZERO);
                costTo.saveEx();
            } else if (!costDimensionToOptional.isPresent() && !costDimensionFromOptional.isPresent()) {
                MCost costTo = MCost.getOrCreate(product, 0, accountSchema, getOrganizationId(), getWarehouseId(), costTypeTo.get_ID(), costElementTo.get_ID());
                costTo.setCurrentCostPrice(BigDecimal.ZERO);
                costTo.saveEx();
            }
        } else if (!costDimensionToOptional.isPresent()) {
            MCost costTo = MCost.getOrCreate(product, 0, accountSchema, getOrganizationId(), getWarehouseId(), costTypeTo.get_ID(), costElementTo.get_ID());
            costDimensionFromOptional.ifPresent(costFrom -> costTo.setCurrentCostPrice(costFrom.getCurrentCostPrice()));
            costTo.saveEx();
        }
    }

    private int[] getProductIds() {
        StringBuilder whereClause = new StringBuilder("1=1 ");
        List<Object> parameters = new ArrayList<>();
        if (getProductId() > 0) {
            whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_ID).append("=? ");
            parameters.add(getProductId());
        }
        if (getProductCategoryId() > 0) {
            whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_Category_ID).append("=? ");
            parameters.add(getProductCategoryId());
        }
        if (getProductGroupId() > 0) {
            whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_Group_ID).append("=? ");
            parameters.add(getProductGroupId());
        }
        if (getProductClassId() > 0) {
            whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_Class_ID).append("=? ");
            parameters.add(getProductClassId());
        }
        if (getProductClassificationId() > 0) {
            whereClause.append(" AND ").append(MProduct.COLUMNNAME_M_Product_Classification_ID).append("=? ");
            parameters.add(getProductClassificationId());
        }

        return new Query(getCtx(), MProduct.Table_Name, whereClause.toString(), get_TrxName()).setClient_ID().setParameters(parameters).getIDs();
    }
}