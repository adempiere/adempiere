package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.DB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Test that validates circular BOM detection by creating actual circular BOM data.
 *
 * <p>This test uses existing products and creates temporary circular BOM entries
 * (A -> B -> A) to verify that:
 * <ul>
 *   <li>Wave5Functions methods do not hang or loop infinitely</li>
 *   <li>A valid result is returned</li>
 * </ul>
 *
 * <p>Circular detection is handled by the SQL CTE's built-in CYCLE detection,
 * which prevents circular rows from being returned.
 *
 * <p>Test data is cleaned up after each test, even on failure.
 */
@Tag("IntegrationTest")
class Wave5CircularBOMTest extends CommonGWSetup {

    // Use existing products from the database
    private int productA_ID = 0;
    private int productB_ID = 0;

    // IDs of test BOMs we create - used for cleanup
    private int bomA_ID = 0;
    private int bomB_ID = 0;
    private int bomLineA_ID = 0;
    private int bomLineB_ID = 0;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        MigrationConfig.clearCache();
    }

    @AfterEach
    @Override
    public void tearDown() {
        // Clean up test data in reverse order of creation
        cleanupTestData();

        super.tearDown();
    }

    /**
     * Test that creates actual circular BOM data and verifies bomQtyOnHand
     * handles it without hanging.
     *
     * Creates: Product A -> (BOM) -> Product B -> (BOM) -> Product A (cycle)
     *
     * Note: Circular detection is primarily handled by the SQL CTE's CYCLE detection,
     * which prevents circular rows from being returned. Java-side detection is a fallback.
     */
    @Test
    @Timeout(value = 30) // Test should complete within 30 seconds
    void testCircularBOM_actualCircularData() {
        // 1. Find two existing products without BOMs
        findTestProducts();
        assumeTrue(productA_ID > 0 && productB_ID > 0,
            "Skipping: Need at least 2 products without existing BOMs");

        // 2. Create circular BOM structure: A -> B -> A
        createCircularBOM();
        assumeTrue(bomA_ID > 0 && bomB_ID > 0,
            "Skipping: Failed to create circular BOM structure");

        // 3. Get a warehouse for testing
        Integer warehouseId = getAnyWarehouse();
        assumeTrue(warehouseId != null && warehouseId > 0,
            "Skipping: No warehouse available");

        // 4. Call Wave5Functions.bomQtyOnHand - should NOT hang
        BigDecimal result = Wave5Functions.bomQtyOnHand(productA_ID, warehouseId, null);

        // 5. Verify result is returned (not null) - this proves no infinite loop
        // If we got here without timeout, circular detection is working
        assertNotNull(result, "bomQtyOnHand should return a result even for circular BOM");
    }

    /**
     * Test bomQtyAvailable with circular BOM.
     * Verifies function completes without hanging.
     */
    @Test
    @Timeout(value = 30)
    void testCircularBOM_bomQtyAvailable() {
        findTestProducts();
        assumeTrue(productA_ID > 0 && productB_ID > 0,
            "Skipping: Need at least 2 products without existing BOMs");

        createCircularBOM();
        assumeTrue(bomA_ID > 0 && bomB_ID > 0,
            "Skipping: Failed to create circular BOM structure");

        Integer warehouseId = getAnyWarehouse();
        assumeTrue(warehouseId != null && warehouseId > 0,
            "Skipping: No warehouse available");

        BigDecimal result = Wave5Functions.bomQtyAvailable(productA_ID, warehouseId, null);

        assertNotNull(result, "bomQtyAvailable should return a result even for circular BOM");
        // If we got here, the circular BOM was handled without infinite loop
    }

    /**
     * Test bomQtyReserved with circular BOM.
     * Verifies function completes without hanging.
     */
    @Test
    @Timeout(value = 30)
    void testCircularBOM_bomQtyReserved() {
        findTestProducts();
        assumeTrue(productA_ID > 0 && productB_ID > 0,
            "Skipping: Need at least 2 products without existing BOMs");

        createCircularBOM();
        assumeTrue(bomA_ID > 0 && bomB_ID > 0,
            "Skipping: Failed to create circular BOM structure");

        Integer warehouseId = getAnyWarehouse();
        assumeTrue(warehouseId != null && warehouseId > 0,
            "Skipping: No warehouse available");

        BigDecimal result = Wave5Functions.bomQtyReserved(productA_ID, warehouseId, null);

        assertNotNull(result, "bomQtyReserved should return a result even for circular BOM");
        // If we got here, the circular BOM was handled without infinite loop
    }

    /**
     * Test bomPriceLimit with circular BOM.
     * Verifies function completes without hanging.
     */
    @Test
    @Timeout(value = 30)
    void testCircularBOM_bomPriceLimit() {
        findTestProducts();
        assumeTrue(productA_ID > 0 && productB_ID > 0,
            "Skipping: Need at least 2 products without existing BOMs");

        createCircularBOM();
        assumeTrue(bomA_ID > 0 && bomB_ID > 0,
            "Skipping: Failed to create circular BOM structure");

        Integer plvId = getAnyPriceListVersion();
        assumeTrue(plvId != null && plvId > 0,
            "Skipping: No price list version available");

        BigDecimal result = Wave5Functions.bomPriceLimit(productA_ID, plvId);

        assertNotNull(result, "bomPriceLimit should return a result even for circular BOM");
        // If we got here, the circular BOM was handled without infinite loop
    }

    // ==================== Helper Methods ====================

    /**
     * Find two existing products that don't already have BOMs.
     * This avoids needing to create products which requires proper sequence setup.
     */
    private void findTestProducts() {
        // Find products without existing BOMs, marked as IsBOM='Y' (can have BOMs)
        // We need products that are active and can have BOMs attached
        String sql = "SELECT p.M_Product_ID FROM M_Product p " +
            "WHERE p.IsActive = 'Y' " +
            "AND p.ProductType = 'I' " +
            "AND NOT EXISTS (SELECT 1 FROM PP_Product_BOM b WHERE b.M_Product_ID = p.M_Product_ID AND b.IsActive = 'Y') " +
            "ORDER BY p.M_Product_ID " +
            "FETCH FIRST 2 ROWS ONLY";

        int[] products = new int[2];
        int idx = 0;
        try (var pstmt = DB.prepareStatement(sql, null);
             var rs = pstmt.executeQuery()) {
            while (rs.next() && idx < 2) {
                products[idx++] = rs.getInt(1);
            }
        } catch (Exception e) {
            // Ignore - products will stay 0
        }

        if (idx >= 2) {
            productA_ID = products[0];
            productB_ID = products[1];
        }
    }

    private void createCircularBOM() {
        int clientId = AD_CLIENT_ID;
        int orgId = AD_ORG_ID;
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // Get max IDs and add 1 for new records (safer than sequences for testing)
        bomA_ID = getMaxId("PP_Product_BOM", "PP_Product_BOM_ID") + 1;
        bomB_ID = bomA_ID + 1;
        bomLineA_ID = getMaxId("PP_Product_BOMLine", "PP_Product_BOMLine_ID") + 1;
        bomLineB_ID = bomLineA_ID + 1;

        // Create BOM for Product A with Product B as component
        String insertBOM = "INSERT INTO PP_Product_BOM " +
            "(PP_Product_BOM_ID, AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, " +
            "M_Product_ID, Value, Name, ValidFrom, IsDefault) " +
            "VALUES (?, ?, ?, 'Y', NOW(), 0, NOW(), 0, ?, ?, ?, ?, 'Y')";

        String valueA = "BOM_CIRC_A_" + System.currentTimeMillis();
        int rowsA = DB.executeUpdate(insertBOM, new Object[] {
            bomA_ID, clientId, orgId, productA_ID, valueA, "Circular BOM A", now
        }, false, trxName);

        if (rowsA != 1) {
            bomA_ID = 0;
            return;
        }

        // Create BOM Line: A contains B
        String insertLine = "INSERT INTO PP_Product_BOMLine " +
            "(PP_Product_BOMLine_ID, AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy, " +
            "PP_Product_BOM_ID, M_Product_ID, Line, QtyBOM, IsQtyPercentage, ValidFrom, IssueMethod) " +
            "VALUES (?, ?, ?, 'Y', NOW(), 0, NOW(), 0, ?, ?, 10, 1, 'N', ?, '1')";

        int rowsLineA = DB.executeUpdate(insertLine, new Object[] {
            bomLineA_ID, clientId, orgId, bomA_ID, productB_ID, now
        }, false, trxName);

        if (rowsLineA != 1) {
            bomLineA_ID = 0;
            return;
        }

        // Create BOM for Product B with Product A as component (completing the cycle)
        String valueB = "BOM_CIRC_B_" + System.currentTimeMillis();
        int rowsB = DB.executeUpdate(insertBOM, new Object[] {
            bomB_ID, clientId, orgId, productB_ID, valueB, "Circular BOM B", now
        }, false, trxName);

        if (rowsB != 1) {
            bomB_ID = 0;
            return;
        }

        // Create BOM Line: B contains A (completing the cycle!)
        int rowsLineB = DB.executeUpdate(insertLine, new Object[] {
            bomLineB_ID, clientId, orgId, bomB_ID, productA_ID, now
        }, false, trxName);

        if (rowsLineB != 1) {
            bomLineB_ID = 0;
            return;
        }

        // Mark both products as having BOMs (required for BOM traversal)
        DB.executeUpdate("UPDATE M_Product SET IsBOM = 'Y' WHERE M_Product_ID IN (?, ?)",
            new Object[] { productA_ID, productB_ID }, false, trxName);
    }

    /**
     * Get max ID from a table for generating new test IDs.
     */
    private int getMaxId(String tableName, String columnName) {
        int max = DB.getSQLValue(trxName, "SELECT COALESCE(MAX(" + columnName + "), 0) FROM " + tableName);
        return max > 0 ? max : 1000000; // Start from 1M if table is empty
    }

    private void cleanupTestData() {
        // Delete in reverse order of creation (foreign key constraints)
        // Note: We don't delete products since we're using existing ones
        if (bomLineB_ID > 0) {
            DB.executeUpdate("DELETE FROM PP_Product_BOMLine WHERE PP_Product_BOMLine_ID = ?",
                new Object[] { bomLineB_ID }, false, trxName);
        }
        if (bomLineA_ID > 0) {
            DB.executeUpdate("DELETE FROM PP_Product_BOMLine WHERE PP_Product_BOMLine_ID = ?",
                new Object[] { bomLineA_ID }, false, trxName);
        }
        if (bomB_ID > 0) {
            DB.executeUpdate("DELETE FROM PP_Product_BOM WHERE PP_Product_BOM_ID = ?",
                new Object[] { bomB_ID }, false, trxName);
        }
        if (bomA_ID > 0) {
            DB.executeUpdate("DELETE FROM PP_Product_BOM WHERE PP_Product_BOM_ID = ?",
                new Object[] { bomA_ID }, false, trxName);
        }
    }

    private Integer getAnyWarehouse() {
        return DB.getSQLValue(null, "SELECT M_Warehouse_ID FROM M_Warehouse WHERE IsActive='Y' FETCH FIRST 1 ROWS ONLY");
    }

    private Integer getAnyPriceListVersion() {
        return DB.getSQLValue(null,
            "SELECT M_PriceList_Version_ID FROM M_PriceList_Version WHERE IsActive='Y' FETCH FIRST 1 ROWS ONLY");
    }
}
