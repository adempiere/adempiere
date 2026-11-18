package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.DB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Integration tests for Wave 5 BOM functions.
 * Validates Java implementations against real database records.
 *
 * <p><b>Test Data Requirements:</b>
 * <ul>
 *   <li>At least one active M_Locator record for resolveWarehouse tests</li>
 *   <li>At least one active PP_Product_BOM with BOM lines for loadBOMTree tests</li>
 * </ul>
 *
 * <p>Tests dynamically discover valid IDs from the database and skip if none are found.
 */
@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave5IntegrationTest extends CommonGWSetup {

    // Dynamically discovered test data
    private int[] testLocatorIds;
    private int[] testLocatorWarehouseIds;
    private int[] testBOMProductIds;

    @BeforeAll
    void loadTestData() {
        // Discover locators with their warehouses
        List<int[]> locatorData = queryLocatorData();
        if (!locatorData.isEmpty()) {
            testLocatorIds = new int[locatorData.size()];
            testLocatorWarehouseIds = new int[locatorData.size()];
            for (int i = 0; i < locatorData.size(); i++) {
                testLocatorIds[i] = locatorData.get(i)[0];
                testLocatorWarehouseIds[i] = locatorData.get(i)[1];
            }
        } else {
            testLocatorIds = new int[0];
            testLocatorWarehouseIds = new int[0];
        }

        // Discover products with BOMs
        testBOMProductIds = queryExistingIds(
            "SELECT DISTINCT b.M_Product_ID FROM PP_Product_BOM b " +
            "INNER JOIN PP_Product_BOMLine bl ON b.PP_Product_BOM_ID = bl.PP_Product_BOM_ID " +
            "WHERE b.IsActive = 'Y' AND bl.IsActive = 'Y' " +
            "FETCH FIRST 5 ROWS ONLY");
    }

    private List<int[]> queryLocatorData() {
        List<int[]> results = new ArrayList<>();
        String sql = "SELECT M_Locator_ID, M_Warehouse_ID FROM M_Locator " +
                     "WHERE IsActive = 'Y' AND M_Warehouse_ID IS NOT NULL " +
                     "FETCH FIRST 5 ROWS ONLY";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                results.add(new int[] { rs.getInt(1), rs.getInt(2) });
            }
        } catch (Exception e) {
            // Return empty list if query fails
        }
        return results;
    }

    private int[] queryExistingIds(String sql) {
        List<Integer> ids = new ArrayList<>();
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
        } catch (Exception e) {
            // Return empty array if query fails
        }
        return ids.stream().mapToInt(Integer::intValue).toArray();
    }

    // ==================== resolveWarehouse Tests ====================

    @Test
    void testResolveWarehouse_locatorLookup() {
        assumeTrue(testLocatorIds != null && testLocatorIds.length > 0,
            "No test locators found - skipping test");

        for (int i = 0; i < testLocatorIds.length; i++) {
            int locatorId = testLocatorIds[i];
            int expectedWarehouseId = testLocatorWarehouseIds[i];

            // Call with null warehouseId to force locator lookup
            Integer result = Wave5Functions.resolveWarehouse(null, locatorId);

            assertNotNull(result, "resolveWarehouse should find warehouse for locator " + locatorId);
            assertEquals(expectedWarehouseId, result.intValue(),
                "Warehouse mismatch for locator " + locatorId);
        }
    }

    @Test
    void testResolveWarehouse_nonExistentLocator() {
        // Test with a locator ID that doesn't exist
        Integer result = Wave5Functions.resolveWarehouse(null, 999999999);
        assertNull(result, "Should return null for non-existent locator");
    }

    @Test
    void testResolveWarehouse_warehouseTakesPrecedence() {
        assumeTrue(testLocatorIds != null && testLocatorIds.length > 0,
            "No test locators found - skipping test");

        int locatorId = testLocatorIds[0];
        int providedWarehouseId = 12345;

        // When warehouseId is provided, it should be returned without DB lookup
        Integer result = Wave5Functions.resolveWarehouse(providedWarehouseId, locatorId);

        assertEquals(providedWarehouseId, result.intValue(),
            "Provided warehouseId should take precedence over locator lookup");
    }

    // ==================== loadBOMTree Tests ====================

    @Test
    void testLoadBOMTree_realProduct() {
        assumeTrue(testBOMProductIds != null && testBOMProductIds.length > 0,
            "No products with BOMs found - skipping test");

        int productId = testBOMProductIds[0];

        var tree = Wave5Functions.loadBOMTree(productId);

        assertNotNull(tree, "Tree should not be null");
        assertFalse(tree.isEmpty(),
            "Tree should have entries for product " + productId + " which has BOM lines");
        assertTrue(tree.containsKey(productId),
            "Tree should contain the root product " + productId + " as a key");
    }

    @Test
    void testLoadBOMTree_matchesSqlCount() {
        assumeTrue(testBOMProductIds != null && testBOMProductIds.length > 0,
            "No products with BOMs found - skipping test");

        int productId = testBOMProductIds[0];

        // Count direct children via SQL
        int sqlCount = DB.getSQLValue(null,
            "SELECT COUNT(*) FROM PP_Product_BOM b " +
            "INNER JOIN PP_Product_BOMLine bl ON b.PP_Product_BOM_ID = bl.PP_Product_BOM_ID " +
            "INNER JOIN M_Product p ON p.M_Product_ID = bl.M_Product_ID " +
            "WHERE b.M_Product_ID = ? " +
            "AND b.IsActive = 'Y' AND bl.IsActive = 'Y' AND p.IsActive = 'Y'",
            productId);

        var tree = Wave5Functions.loadBOMTree(productId);
        int javaCount = tree.getOrDefault(productId, java.util.Collections.emptyList()).size();

        assertEquals(sqlCount, javaCount,
            "Java loadBOMTree should return same number of direct children as SQL for product " + productId);
    }
}
