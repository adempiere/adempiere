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

import static org.adempiere.core.domains.models.X_M_AttributeSet.MANDATORYTYPE_NotMandatory;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.adempiere.test.CommonGWData;
import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model")
@Tag("MLot")
@Tag("AttributeRelated")
class IT_MLot_getProductLots extends CommonGWSetup {

    private final int NUMBER_OF_LOTS_CREATED = 3;
    private MProduct product;

    @BeforeEach
    void localSetup() {

        product = createProductWithNoAttributeSet();

    }

    private MProduct createProductWithNoAttributeSet() {

        MProduct product = new MProduct(ctx, 0, trxName);
        product.setName("ProductUnderTest");
        product.setC_UOM_ID(MUOM.getDefault_UOM_ID(ctx));
        product.setM_Product_Category_ID(
                CommonGWData.PRODUCT_CATEGORY_STANDARD_ID);
        product.setC_TaxCategory_ID(CommonGWData.TAX_CATEGORY_STANDARD_ID);
        product.setIsPurchased(true);
        product.setIsSold(true);
        product.setIsStocked(true);
        product.saveEx();
        product.load(trxName);
        return product;

    }

    private MLotCtl createLotControl() {

        MLotCtl lotCtl = new MLotCtl(ctx, 0, trxName);
        lotCtl.setName("TestLotCtl");
        lotCtl.saveEx();
        return lotCtl;

    }

    private MAttributeSet createAttributeSetAndAddToProduct() {

        MAttributeSet as = createAttributeSet(MANDATORYTYPE_NotMandatory);
        product.setM_AttributeSet_ID(as.getM_AttributeSet_ID());
        product.saveEx();
        product.load(trxName);
        return as;

    }

    private void setupProductToUseLots() {

        MLotCtl lotCtl = createLotControl();

        MAttributeSet as = createAttributeSetAndAddToProduct();
        as.setIsLot(true);
        as.setM_LotCtl_ID(lotCtl.getM_LotCtl_ID());
        as.saveEx();

    }

    private MAttributeSet createAttributeSet(String mandatoryType) {

        mandatoryType = Optional.ofNullable(mandatoryType)
                .orElse(MANDATORYTYPE_NotMandatory);
        MAttributeSet as = new MAttributeSet(ctx, 0, trxName);
        as.setMandatoryType(mandatoryType);
        as.setName("TestAttributeSet");
        as.saveEx();
        return as;

    }

    private void createAttributeSetsInstancesWithLots(int numberToCreate) {

        for (int i = 0; i < numberToCreate; i++)
            MAttributeSetInstance.create(ctx, product, trxName);

    }

    private void createAndSaveSomeProductLots(int numberToCreate) {

        setupProductToUseLots();
        createAttributeSetsInstancesWithLots(numberToCreate);

    }

    @Test
    void getProductLots_whenNoLotsCreated_shouldReturnEmptyArray() {

        MLot[] lots = MLot.getProductLots(ctx, product.getM_Product_ID(),
                trxName);

        assertEquals(0, lots.length,
                "No lots were created. None should have been found.");

    }

    @Test
    void getProductLots_shouldReturnTheLotsCreated() {

        createAndSaveSomeProductLots(NUMBER_OF_LOTS_CREATED);

        MLot[] lots = MLot.getProductLots(ctx, product.getM_Product_ID(),
                trxName);

        assertEquals(NUMBER_OF_LOTS_CREATED, lots.length,
                NUMBER_OF_LOTS_CREATED + " lots were created");

    }

}
