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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.adempiere.core.domains.models.X_M_BOM.BOMTYPE_CurrentActive;
import static org.adempiere.core.domains.models.X_M_BOM.BOMTYPE_Make_To_Kit;
import static org.adempiere.core.domains.models.X_M_BOM.BOMTYPE_Make_To_Order;
import static org.adempiere.core.domains.models.X_M_BOM.BOMUSE_Engineering;
import static org.adempiere.core.domains.models.X_M_BOM.BOMUSE_Master;
import static org.compiere.model.ModelTestUtilities.verifyExceptionForMissingMandatoryField;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonGWData;
import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class IT_MBOM extends CommonGWSetup {

    private MBOM bom = null;
    private MProduct product;
    
    @BeforeEach
    void localSetup() {
        product = createProduct();
    }
    
    @Test
    void constructor_withId_checkDefaults() {

        MBOM bom = new MBOM(ctx, 0, trxName);
        assertEquals(BOMTYPE_CurrentActive, bom.getBOMType(),
                "Default type of current active expected");
        assertEquals(BOMUSE_Master, bom.getBOMUse(),
                "Default use of master expected");

    }

    @Test
    void onSave_checkProductIDIsMandatory() {

        MBOM bom = new MBOM(ctx, 0, trxName);
        Exception e = assertThrows(AdempiereException.class, () -> {
            bom.saveEx();
        });

        verifyExceptionForMissingMandatoryField("m_product_id", e);

    }

    @Test
    void onSave_checkProductNameIsMandatory() {

        MBOM bom = new MBOM(ctx, 0, trxName);
        bom.setM_Product_ID(product.getM_Product_ID());
        Exception e = assertThrows(AdempiereException.class, () -> {
            bom.saveEx();
        });

        verifyExceptionForMissingMandatoryField("name", e);

    }
    
    @Test
    void changesAreNotOverwritenOnSave() {
        
        MBOM bom = new MBOM(ctx, 0, trxName);
        bom.setName("TestBOM");
        bom.setM_Product_ID(product.getM_Product_ID());
        bom.saveEx();
        
        MBOM bom2 = new MBOM(ctx, bom.get_ID(), trxName);
        bom2.setBOMType(BOMTYPE_Make_To_Kit);
        bom2.setBOMUse(BOMUSE_Engineering);
        bom2.saveEx();
        
        assertEquals(BOMTYPE_Make_To_Kit, bom2.getBOMType(),
                "BOMType changed on save of existing record");
        assertEquals(BOMUSE_Engineering, bom2.getBOMUse(),
                "BOMUse changed on save of existing record");

        
    }
    
    @Test
    void shouldOnlyHaveOneCurrentActiveBOM() {

        createBOMForProduct(product, BOMTYPE_CurrentActive);

        Exception e = assertThrows(AdempiereException.class, () -> {
            createBOMForProduct(product, BOMTYPE_CurrentActive);
        });

        assertEquals(
                "Can only have one Current Active BOM for Product BOM Use (A)",
                e.getMessage(), "Exception message not as expected");

    }

    @Test
    void shouldOnlyHaveOneMakeToOrderBOM() {

        createBOMForProduct(product, BOMTYPE_CurrentActive);

        Exception e = assertThrows(AdempiereException.class, () -> {
            createBOMForProduct(product, BOMTYPE_Make_To_Order);
        });

        assertEquals(
                "Can only have single Make-to-Order BOM for Product",
                e.getMessage(), "Exception message not as expected");

    }

    @Test
    void shouldOnlyHaveOneCurrentActiveBOM_afterChange() {

        createBOMForProduct(product, BOMTYPE_CurrentActive);
        MBOM bom2 = createBOMForProduct(product, BOMTYPE_Make_To_Kit);
        bom2.setBOMType(BOMTYPE_CurrentActive);
        Exception e = assertThrows(AdempiereException.class, () -> {
            bom2.saveEx();
        });

        assertEquals(
                "Can only have one Current Active BOM for Product BOM Use (A)",
                e.getMessage(), "Exception message not as expected");

    }

    @Test
    @Disabled // Fails. Check if Make To Kit rules are specific to the state!!
    void makeToKitBOM_addingAnotherBOMTypeShouldThrowException() {

        createBOMForProduct(product, BOMTYPE_Make_To_Kit);
        Exception e = assertThrows(AdempiereException.class, () -> {
            createBOMForProduct(product, BOMTYPE_CurrentActive);
        });

        assertEquals(
                "Can only have single Make-to-Order BOM for Product",
                e.getMessage(), "Exception message not as expected");

    }

    private MBOM createBOMForProduct(MProduct product, String bomType) {

        MBOM bom = new MBOM(ctx, 0, trxName);
        bom.setName("TestBOM");
        bom.setM_Product_ID(product.getM_Product_ID());
        bom.setBOMType(bomType);
        bom.saveEx();
        return bom;

    }

    private MProduct createProduct() {

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

    @Test
    void getBOMLines_() {

        MProductBOM[] lines = MProductBOM.getBOMLines(getCtx(), 145,
                getTrxName());
        assertTrue(lines.length > 0, "ProductBOM should have lines");

    }

    @Test
    void testBOMCreation() {

        bom = new MBOM(getCtx(), 0, getTrxName());
        bom.setM_Product_ID(134);
        bom.setBOMType("A");
        bom.setName("PatioTable");

        boolean saveResult = bom.save(); //
        assertTrue(saveResult, "MBOM.save()");

    }

}
