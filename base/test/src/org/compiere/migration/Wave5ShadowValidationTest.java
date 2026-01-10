package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.math.BigDecimal;
import org.adempiere.test.CommonGWSetup;
import org.compiere.util.DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Shadow validation tests for Wave 5 BOM functions.
 * Compares Java vs SQL results to ensure parity.
 */
@Tag("IntegrationTest")
class Wave5ShadowValidationTest extends CommonGWSetup {

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        MigrationConfig.clearCache();
    }

    @Test
    void testBomPriceLimit_shadowValidation() {
        Integer productId = getProductWithBOM();
        Integer plvId = getAnyPriceListVersion();

        assumeTrue(productId != null && productId > 0, "Skipping: No product with BOM available");
        assumeTrue(plvId != null && plvId > 0, "Skipping: No price list version available");

        BigDecimal javaResult = Wave5Functions.bomPriceLimit(productId, plvId);
        BigDecimal sqlResult = SqlFunctionCaller.callBomPriceLimit(productId, plvId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> "bomPriceLimit mismatch for product " + productId +
                  ": Java=" + javaResult + ", SQL=" + sqlResult);
    }

    @Test
    void testBomQtyOnHand_shadowValidation() {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();

        assumeTrue(productId != null && productId > 0, "Skipping: No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0, "Skipping: No warehouse available");

        BigDecimal javaResult = Wave5Functions.bomQtyOnHand(productId, warehouseId, null);
        BigDecimal sqlResult = SqlFunctionCaller.callBomQtyOnHand(productId, warehouseId, null);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> "bomQtyOnHand mismatch for product " + productId +
                  ": Java=" + javaResult + ", SQL=" + sqlResult);
    }

    @Test
    void testBomQtyAvailable_shadowValidation() {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();

        assumeTrue(productId != null && productId > 0, "Skipping: No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0, "Skipping: No warehouse available");

        BigDecimal javaResult = Wave5Functions.bomQtyAvailable(productId, warehouseId, null);
        BigDecimal sqlResult = SqlFunctionCaller.callBomQtyAvailable(productId, warehouseId, null);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> "bomQtyAvailable mismatch for product " + productId +
                  ": Java=" + javaResult + ", SQL=" + sqlResult);
    }

    @Test
    void testBomQtyReserved_shadowValidation() {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();

        assumeTrue(productId != null && productId > 0, "Skipping: No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0, "Skipping: No warehouse available");

        BigDecimal javaResult = Wave5Functions.bomQtyReserved(productId, warehouseId, null);
        BigDecimal sqlResult = SqlFunctionCaller.callBomQtyReserved(productId, warehouseId, null);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> "bomQtyReserved mismatch for product " + productId +
                  ": Java=" + javaResult + ", SQL=" + sqlResult);
    }

    @Test
    void testBomQtyOrdered_shadowValidation() {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();

        assumeTrue(productId != null && productId > 0, "Skipping: No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0, "Skipping: No warehouse available");

        BigDecimal javaResult = Wave5Functions.bomQtyOrdered(productId, warehouseId, null);
        BigDecimal sqlResult = SqlFunctionCaller.callBomQtyOrdered(productId, warehouseId, null);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> "bomQtyOrdered mismatch for product " + productId +
                  ": Java=" + javaResult + ", SQL=" + sqlResult);
    }

    @Test
    void testCircularBOM_handledGracefully() {
        // Verify that circular BOMs don't cause infinite loops or exceptions
        // The CTE's cycle detection should prevent duplicate rows
        Integer productId = getProductWithCircularBOM();

        assumeTrue(productId != null && productId > 0, "Skipping: No circular BOM in test data");

        Integer warehouseId = getAnyWarehouse();
        assumeTrue(warehouseId != null && warehouseId > 0, "Skipping: No warehouse available");

        // Should complete without hanging or throwing
        BigDecimal result = Wave5Functions.bomQtyOnHand(productId, warehouseId, null);
        assertNotNull(result, "Should return a result even for circular BOM");
    }

    private Integer getProductWithCircularBOM() {
        // Look for a product that appears in its own BOM hierarchy
        String sql = """
            WITH RECURSIVE bom_check AS (
                SELECT b.M_Product_ID AS root, bl.M_Product_ID AS child, 1 AS depth
                FROM PP_Product_BOM b
                JOIN PP_Product_BOMLine bl ON b.PP_Product_BOM_ID = bl.PP_Product_BOM_ID
                WHERE b.IsActive = 'Y' AND bl.IsActive = 'Y'

                UNION ALL

                SELECT bc.root, bl.M_Product_ID, bc.depth + 1
                FROM bom_check bc
                JOIN PP_Product_BOM b ON b.M_Product_ID = bc.child
                JOIN PP_Product_BOMLine bl ON b.PP_Product_BOM_ID = bl.PP_Product_BOM_ID
                WHERE bc.depth < 10 AND b.IsActive = 'Y' AND bl.IsActive = 'Y'
            )
            SELECT root FROM bom_check WHERE root = child LIMIT 1
            """;
        return DB.getSQLValue(null, sql);
    }

    private Integer getProductWithBOM() {
        String sql = "SELECT b.M_Product_ID FROM PP_Product_BOM b "
            + "JOIN PP_Product_BOMLine bl ON b.PP_Product_BOM_ID = bl.PP_Product_BOM_ID "
            + "WHERE b.IsActive = 'Y' AND bl.IsActive = 'Y' "
            + "LIMIT 1";
        return DB.getSQLValue(null, sql);
    }

    private Integer getAnyPriceListVersion() {
        return DB.getSQLValue(null,
            "SELECT M_PriceList_Version_ID FROM M_PriceList_Version WHERE IsActive='Y' LIMIT 1");
    }

    private Integer getAnyWarehouse() {
        return DB.getSQLValue(null,
            "SELECT M_Warehouse_ID FROM M_Warehouse WHERE IsActive='Y' LIMIT 1");
    }
}
