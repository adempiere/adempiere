/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2020 ADempiere Foundation, All Rights Reserved.         *
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
 *****************************************************************************/
package org.compiere.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;

import org.adempiere.test.CommonGWData;
import org.adempiere.test.CommonGWSetup;
import org.compiere.util.Env;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model")
@Tag("MCostDetail")
class IT_MCostDetail extends CommonGWSetup {

    private MCostDetail cd;

    @BeforeEach
    void localSetup() {

        cd = new MCostDetail(ctx, 0, trxName);

    }

    @Test
    void checkStandardConstructor() {

        assertEquals(0, cd.getM_AttributeSetInstance_ID(),
                "M_AttributeSetInstanceID not set as expected");
        assertFalse(cd.isProcessed(), "New cost detail is marked processed");
        assertEquals(Env.ZERO, cd.getAmt(), "New cost detail amount is not zero");
        assertEquals(Env.ZERO, cd.getQty(), "New cost detail qty is not zero");
        assertEquals(Env.ZERO, cd.getDeltaAmt(), "New cost detail delta amount is not zero");
        assertEquals(Env.ZERO, cd.getDeltaQty(), "New cost detail delta qty is not zero");
        assertFalse(cd.isSOTrx(), "New cost detail should not be sales trx");

    }

    @Test
    void checkCustomConstructor() {

        MAcctSchema as = MClient.get(ctx).getAcctSchema();
        int productId = CommonGWData.PATIOSET_PRODUCT_ID;
        int attributeSetInstanceId = 1;
        BigDecimal amt = BigDecimal.valueOf(40.0);
        BigDecimal amtLL = Env.ZERO;
        BigDecimal qty = Env.ONE;
        String description = "test cost detail";

        MCostElement costElement = MCostElement.getCostElement(ctx, trxName).get(0);
        int costElementId = costElement.getM_CostElement_ID();

        MCostType costType = MCostType.get(ctx, trxName).get(0);
        int costTypeId = costType.getM_CostType_ID();

        String method = costType.getCostingMethod();

        MCostDetail cd = new MCostDetail(as, CommonGWData.AD_ORG_ID, productId,
                attributeSetInstanceId, costElementId, amt, qty, description,
                trxName, costTypeId);

        assertCostDetailValues(productId, attributeSetInstanceId, as, costElementId, costTypeId,
                method, amt, qty, amtLL, cd);
        assertEquals(description, cd.getDescription());

    }

    @Test
    void checkConstructorWithTransaction() {

        MWarehouse warehouse = MWarehouse.get(ctx, CommonGWData.FURNITURE_WAREHOUSE_ID);
        MLocator locator = MLocator.getDefault(warehouse);
        MTransaction transaction = new MTransaction(ctx, 0, trxName);
        transaction.setM_Locator_ID(locator.getM_Locator_ID());
        int productId = CommonGWData.PATIOSET_PRODUCT_ID;
        int attributeSetInstanceId = 2;
        transaction.setM_Product_ID(productId);
        transaction.setM_AttributeSetInstance_ID(2);
        MAcctSchema as = MClient.get(ctx).getAcctSchema();
        int acctSchemaId = as.getC_AcctSchema_ID();
        MCostElement costElement = MCostElement.getCostElement(ctx, trxName).get(0);
        int costElementId = costElement.getM_CostElement_ID();
        MCostType costType = MCostType.get(ctx, trxName).get(0);
        int costTypeId = costType.getM_CostType_ID();
        String method = costType.getCostingMethod();
        BigDecimal amt = BigDecimal.valueOf(40.0);
        BigDecimal qty = Env.ONE;
        BigDecimal amtLL = BigDecimal.valueOf(60.0);
        
        MCostDetail cd = new MCostDetail(transaction, acctSchemaId,
                costTypeId, costElementId, amt, amtLL, qty, trxName);

        assertCostDetailValues(productId, attributeSetInstanceId, as, costElementId, costTypeId,
                method, amt, qty, amtLL, cd);

    }

    private void assertCostDetailValues(int productId, int attributeSetInstanceId, MAcctSchema as,
            int costElementId, int costTypeId, String method, BigDecimal amt, BigDecimal qty,
            BigDecimal amtLL, MCostDetail cd) {

        assertEquals(CommonGWData.AD_CLIENT_ID, cd.getAD_Client_ID(),
                "Client ID not set as expected");
        assertEquals(CommonGWData.AD_ORG_ID, cd.getAD_Org_ID(),
                "Client Org ID not set as expected");
        assertEquals(as.getC_AcctSchema_ID(), cd.getC_AcctSchema_ID(),
                "Client schema not set as expected");
        assertEquals(productId, cd.getM_Product_ID(), "Product ID not set as expected");
        assertEquals(attributeSetInstanceId, cd.getM_AttributeSetInstance_ID(),
                "M_AttributeSetInstanceID not set as expected");
        assertEquals(costElementId, cd.getM_CostElement_ID(),
                "Cost element id not set as expected");
        assertEquals(costTypeId, cd.getM_CostType_ID(), "Cost type id not set as expected");
        assertEquals(method, cd.getCostingMethod(),
                "Costing method doesn't match that of cost type");
        assertFalse(cd.isProcessed(), "New cost detail is marked processed");
        assertEquals(amt, cd.getAmt(), "New cost detail amount");
        assertEquals(amtLL, cd.getAmtLL(), "New cost detail amount lower level");
        assertEquals(qty, cd.getQty(), "New cost detail qty");

    }

}
