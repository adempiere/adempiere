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

import static org.adempiere.core.domains.models.X_M_Product.PRODUCTTYPE_Item;
import static org.adempiere.test.CommonGWData.AZALEA_BUSH_PRODUCT_ID;
import static org.compiere.model.ModelTestUtilities.verifyExceptionForMissingMandatoryField;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonGWData;
import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("Model")
@Tag("MProduct")
class IT_MProduct_GetAndConstructors extends CommonGWSetup {

    private static int c_uom_id;
    private final int productCategoryStandardId =
            CommonGWData.PRODUCT_CATEGORY_STANDARD_ID;
    private final int taxCategoryStandardId =
            CommonGWData.TAX_CATEGORY_STANDARD_ID;

    @BeforeAll
    static void localBeforeAllSetup() {

        c_uom_id = MUOM.getDefault_UOM_ID(ctx);

    }

    private void assertProductDefaultsAreSetAsExpected(MProduct product) {

        assertEquals(0, product.getM_Product_ID(),
                "Product ID for a new instance should be zero");
        assertEquals(PRODUCTTYPE_Item, product.getProductType());
        assertEquals(false, product.isBOM(), "Default isBOM not as expected");
        assertEquals(false, product.isInvoicePrintDetails(),
                "Default isInvoicePrintDetails is not as expected");
        assertEquals(false, product.isPickListPrintDetails(),
                "Default isPickListPrintDetails is not as expected");
        assertEquals(true, product.isPurchased(),
                "Default isPurchased is not as expected");
        assertEquals(true, product.isSold(),
                "Default isSold is not as expected");
        assertEquals(true, product.isStocked(),
                "Default isStocked is not as expected");
        assertEquals(false, product.isSummary(),
                "Default isSummary is not as expected");
        assertEquals(false, product.isVerified(),
                "Default isVerified is not as expected");
        assertEquals(false, product.isWebStoreFeatured(),
                "Default isWebStoreFeatured is not as expected");
        assertEquals(true, product.isSelfService(),
                "Default isSelfService is not as expected");
        assertEquals(false, product.isExcludeAutoDelivery(),
                "Default isExcludeAutoDelivery is not as expected");
        assertEquals(false, product.isProcessing(),
                "Default isProcessing is not as expected");
        assertEquals(0, product.getLowLevel(),
                "Default getLowLevel is not as expected");

    }

    private Exception createProductWithoutField(String fieldName) {

        MProduct product = new MProduct(ctx, 0, trxName);
        if (!fieldName.equals("name"))
            product.setName("testProduct");
        if (!fieldName.equals("c_uom_id"))
            product.setC_UOM_ID(c_uom_id);
        if (!fieldName.equals("m_product_category_id"))
            product.setM_Product_Category_ID(
                    productCategoryStandardId);
        if (!fieldName.equals("c_taxcategory_id"))
            product.setC_TaxCategory_ID(taxCategoryStandardId);

        Exception e = assertThrows(AdempiereException.class, () -> {
            product.saveEx();
        });
        return e;

    }

    @Test
    void getProduct_forKnownProduct() {

        MProduct product = MProduct.get(ctx, AZALEA_BUSH_PRODUCT_ID);
        assertEquals(AZALEA_BUSH_PRODUCT_ID, product.get_ID());

    }

    @Test
    void getProduct_forUnknownProduct_shouldReturnNewInstance() {

        MProduct product = MProduct.get(ctx, 9999997);
        assertEquals(0, product.get_ID(),
                "When passed unknown but positive ID, "
                        + "should return new MProduct instance");

    }

    @Test
    void getProduct_forNewProduct_getWithoutTrxNameShouldReturnNewInstance() {

        MProduct product = createDefaultProduct();
        MProduct searchedProduct = MProduct.get(ctx, product.getM_Product_ID());
        assertEquals(0, searchedProduct.getM_Product_ID(),
                "MProduct.get should have returned a new "
                        + "instance when trxName was null");

    }

    @Test
    void getProduct_forNewProduct_getWithTrxNameShouldNewProduct() {

        MProduct product = createDefaultProduct();
        MProduct searchedProduct = MProduct.get(ctx, product.getM_Product_ID(),
                trxName);
        assertEquals(product.getM_Product_ID(),
                searchedProduct.getM_Product_ID(),
                "MProduct.get with trxName should have "
                        + "returned the same product");

    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    void getProduct_whenPassedNegativeOrZero_shouldReturnNull(int id) {

        assertNull(MProduct.get(ctx, id),
                "When passed negative or zero, should return null");

    }

    @ParameterizedTest
    @ValueSource(strings= {"name", "c_uom_id", "m_product_category_id", "c_taxcategory_id"})
    void constructor_checkMandatoryName(String fieldName) {

        Exception e = createProductWithoutField(fieldName);
        verifyExceptionForMissingMandatoryField(fieldName, e);

    }

    @Test
    void constructor_success() {

        MProduct product = createDefaultProduct();

        assertTrue(product.getM_Product_ID() > 0,
                "Product id of newly saved instance is not set > 0");

    }

    private MProduct createDefaultProduct() {

        String name = "test_product";
        MProduct product = createProduct(name, c_uom_id,
                productCategoryStandardId,
                taxCategoryStandardId);
        return product;

    }

    private MProduct createProduct(String name, int uomId,
            int productCategoryId,
            int taxCategoryId) {

        MProduct product = new MProduct(ctx, 0, trxName);
        product.setName(name);
        product.setC_UOM_ID(uomId);
        product.setM_Product_Category_ID(
                productCategoryId);
        product.setC_TaxCategory_ID(taxCategoryId);
        product.saveEx();
        return product;

    }

    @Test
    void constructor_success_checkDefaults() {

        MProduct product = new MProduct(ctx, 0, trxName);

        assertProductDefaultsAreSetAsExpected(product);

    }

}
