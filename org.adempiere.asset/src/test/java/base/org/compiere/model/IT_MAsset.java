/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.adempiere.test.CommonGWData;
import org.adempiere.test.CommonGWSetup;
import org.compiere.FA.model.MAsset;
import org.compiere.FA.model.MAssetGroup;
import org.compiere.util.Env;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model")
@Tag("MAsset")
class IT_MAsset extends CommonGWSetup {

    private MAssetGroup assetGroup;
    private MProductCategory productCategory;
    private MProduct assetProduct;

    private MAssetGroup createAssetGroup(final String groupName) {

        assetGroup = new MAssetGroup(ctx, 0, trxName);
        assetGroup.setName(groupName);
        assetGroup.saveEx();
        return assetGroup;

    }

    private MProductCategory createProductCategory(
            final String prodCategoryName, MAssetGroup assetGroup) {

        productCategory =
                new MProductCategory(ctx, 0, trxName);
        productCategory.setName(prodCategoryName);
        productCategory.setA_Asset_Group_ID(assetGroup.getA_Asset_Group_ID());
        productCategory.saveEx();
        return productCategory;

    }

    private MProduct createAssetProduct(final String name,
            MProductCategory productCategory) {

        assetProduct = new MProduct(ctx, 0, trxName);
        assetProduct.setName(name);
        assetProduct.setM_Product_Category_ID(
                productCategory.getM_Product_Category_ID());
        assetProduct.setIsStocked(true);
        assetProduct.setC_UOM_ID(DEFAULT_UOM_ID);
        assetProduct.setC_TaxCategory_ID(TAX_CATEGORY_STANDARD_ID);
        assetProduct.saveEx();
        return assetProduct;

    }

    private MInOutLine receiveAssetProduct(MProduct assetProduct,
            final BigDecimal qtyReceived) {

        MInOut mr = new MInOut(ctx, 0, trxName);
        mr.setC_BPartner_ID(SEEDFARM_ID);
        mr.setC_BPartner_Location_ID(SEEDFARM_LOCATION_ID);
        mr.setM_Warehouse_ID(CommonGWData.HQ_WAREHOUSE_ID);
        mr.setIsSOTrx(false);
        mr.setC_DocType_ID();
        mr.setMovementType(X_M_InOut.MOVEMENTTYPE_VendorReceipts);
        mr.saveEx();

        MWarehouse hqWarehouse =
                new MWarehouse(ctx, CommonGWData.HQ_WAREHOUSE_ID, trxName);
        int hq_default_locator_id =
                MLocator.getDefault(hqWarehouse).getM_Locator_ID();
        MInOutLine mrLine = new MInOutLine(mr);
        mrLine.setM_Product_ID(assetProduct.getM_Product_ID());
        mrLine.setQty(qtyReceived);
        mrLine.setM_Locator_ID(hq_default_locator_id);
        mrLine.saveEx();
        return mrLine;

    }

    private void recordInvoiceForAsset(MProduct assetProduct,
            final BigDecimal qty, final BigDecimal price,
            MInOutLine mrLine) {

        MInvoice assetInvoice = new MInvoice(ctx, 0, trxName);
        assetInvoice.setIsSOTrx(false);
        assetInvoice.setC_BPartner_ID(SEEDFARM_ID);
        assetInvoice.setC_BPartner_Location_ID(SEEDFARM_LOCATION_ID);
        assetInvoice.saveEx();

        MInvoiceLine invoiceLine = new MInvoiceLine(assetInvoice);
        invoiceLine.setM_Product_ID(assetProduct.getM_Product_ID());
        invoiceLine.setIsFixedAssetInvoice(true);
        invoiceLine.setA_CapvsExp(X_C_InvoiceLine.A_CAPVSEXP_Capital);

        int id = assetProduct.getM_Product_Category().getA_Asset_Group_ID();
        assertTrue(id>0);
        invoiceLine.setA_Asset_Group_ID(id);
        
        
        invoiceLine.setA_CreateAsset(true);
        invoiceLine.setIsCollectiveAsset(false);
        invoiceLine.setQty(qty);
        invoiceLine.setPrice(price);
        invoiceLine.setM_InOutLine_ID(mrLine.getM_InOutLine_ID());
        invoiceLine.saveEx();

        assetInvoice.prepareIt();
        assetInvoice.completeIt();

    }

    @Test
    void purchaseAndRecieveAsset_ShouldCreateAsset() {

        BigDecimal price = BigDecimal.valueOf(100000.0);

        createAssetGroup("testGroup");
        createProductCategory("assetRelatedProductCategory", assetGroup);
        createAssetProduct("assetProduct", productCategory);
        MInOutLine mrLine = receiveAssetProduct(assetProduct, Env.ONE);
        recordInvoiceForAsset(assetProduct, Env.ONE, price, mrLine);
        MAsset asset = MAsset.getFromShipment(ctx, mrLine.getM_InOutLine_ID(),
                trxName);

        assertNotNull(asset);
        assertTrue(asset.getA_Asset_ID() > 0);

    }

}
