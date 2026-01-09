package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.DB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * Integration tests for Wave 5 BOM pricing functions.
 * Compares Java implementation against PostgreSQL function results.
 *
 * <p><b>Test Data Requirements:</b>
 * <ul>
 *   <li>At least one M_ProductPrice record with PriceStd > 0</li>
 *   <li>At least one active M_PriceList_Version</li>
 * </ul>
 *
 * <p>Tests dynamically discover valid IDs from the database and skip if none are found.
 */
@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Wave5PricingIntegrationTest extends CommonGWSetup {

    // Dynamically discovered test data
    private int[] testProductIds;
    private int[] testPriceListVersionIds;

    @BeforeAll
    void loadTestData() {
        // Discover products with prices
        List<int[]> productPriceData = queryProductPriceData();
        if (!productPriceData.isEmpty()) {
            testProductIds = new int[productPriceData.size()];
            testPriceListVersionIds = new int[productPriceData.size()];
            for (int i = 0; i < productPriceData.size(); i++) {
                testProductIds[i] = productPriceData.get(i)[0];
                testPriceListVersionIds[i] = productPriceData.get(i)[1];
            }
        } else {
            testProductIds = new int[0];
            testPriceListVersionIds = new int[0];
        }
    }

    private List<int[]> queryProductPriceData() {
        List<int[]> results = new ArrayList<>();
        String sql = "SELECT pp.M_Product_ID, pp.M_PriceList_Version_ID " +
                     "FROM M_ProductPrice pp " +
                     "INNER JOIN M_PriceList_Version plv ON plv.M_PriceList_Version_ID = pp.M_PriceList_Version_ID " +
                     "WHERE pp.PriceStd > 0 AND plv.IsActive = 'Y' " +
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

    // ==================== bomPriceLimit Tests ====================

    @Test
    void testBomPriceLimit_matchesSql() {
        assumeTrue(testProductIds != null && testProductIds.length > 0,
            "Skipping: No product with price available in test database");

        Integer productId = testProductIds[0];
        Integer plvId = testPriceListVersionIds[0];

        BigDecimal javaResult = Wave5Functions.bomPriceLimit(productId, plvId);
        BigDecimal sqlResult = callSqlFunction("bompricelimit", productId, plvId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> "bomPriceLimit mismatch for product " + productId +
                  ": Java=" + javaResult + ", SQL=" + sqlResult);
    }

    @Test
    void testBomPriceLimit_multipleProducts() {
        assumeTrue(testProductIds != null && testProductIds.length > 0,
            "Skipping: No products with prices available");

        for (int i = 0; i < testProductIds.length; i++) {
            Integer productId = testProductIds[i];
            Integer plvId = testPriceListVersionIds[i];

            BigDecimal javaResult = Wave5Functions.bomPriceLimit(productId, plvId);
            BigDecimal sqlResult = callSqlFunction("bompricelimit", productId, plvId);

            assertEquals(0, javaResult.compareTo(sqlResult),
                () -> "bomPriceLimit mismatch for product " + productId +
                      ": Java=" + javaResult + ", SQL=" + sqlResult);
        }
    }

    // ==================== bomPriceList Tests ====================

    @Test
    void testBomPriceList_matchesSql() {
        assumeTrue(testProductIds != null && testProductIds.length > 0,
            "Skipping: No product with price available");

        Integer productId = testProductIds[0];
        Integer plvId = testPriceListVersionIds[0];

        BigDecimal javaResult = Wave5Functions.bomPriceList(productId, plvId);
        BigDecimal sqlResult = callSqlFunction("bompricelist", productId, plvId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> "bomPriceList mismatch: Java=" + javaResult + ", SQL=" + sqlResult);
    }

    @Test
    void testBomPriceList_multipleProducts() {
        assumeTrue(testProductIds != null && testProductIds.length > 0,
            "Skipping: No products with prices available");

        for (int i = 0; i < testProductIds.length; i++) {
            Integer productId = testProductIds[i];
            Integer plvId = testPriceListVersionIds[i];

            BigDecimal javaResult = Wave5Functions.bomPriceList(productId, plvId);
            BigDecimal sqlResult = callSqlFunction("bompricelist", productId, plvId);

            assertEquals(0, javaResult.compareTo(sqlResult),
                () -> "bomPriceList mismatch for product " + productId +
                      ": Java=" + javaResult + ", SQL=" + sqlResult);
        }
    }

    // ==================== bomPriceStd Tests ====================

    @Test
    void testBomPriceStd_matchesSql() {
        assumeTrue(testProductIds != null && testProductIds.length > 0,
            "Skipping: No product with price available");

        Integer productId = testProductIds[0];
        Integer plvId = testPriceListVersionIds[0];

        BigDecimal javaResult = Wave5Functions.bomPriceStd(productId, plvId);
        BigDecimal sqlResult = callSqlFunction("bompricestd", productId, plvId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> "bomPriceStd mismatch: Java=" + javaResult + ", SQL=" + sqlResult);
    }

    @Test
    void testBomPriceStd_multipleProducts() {
        assumeTrue(testProductIds != null && testProductIds.length > 0,
            "Skipping: No products with prices available");

        for (int i = 0; i < testProductIds.length; i++) {
            Integer productId = testProductIds[i];
            Integer plvId = testPriceListVersionIds[i];

            BigDecimal javaResult = Wave5Functions.bomPriceStd(productId, plvId);
            BigDecimal sqlResult = callSqlFunction("bompricestd", productId, plvId);

            assertEquals(0, javaResult.compareTo(sqlResult),
                () -> "bomPriceStd mismatch for product " + productId +
                      ": Java=" + javaResult + ", SQL=" + sqlResult);
        }
    }

    // ==================== Edge Cases ====================

    @Test
    void testBomPriceLimit_invalidProduct_returnsZero() {
        BigDecimal result = Wave5Functions.bomPriceLimit(Integer.valueOf(-1), Integer.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.ZERO),
            "bomPriceLimit should return ZERO for invalid product ID");
    }

    @Test
    void testBomPriceList_invalidProduct_returnsZero() {
        BigDecimal result = Wave5Functions.bomPriceList(Integer.valueOf(-1), Integer.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.ZERO),
            "bomPriceList should return ZERO for invalid product ID");
    }

    @Test
    void testBomPriceStd_invalidProduct_returnsZero() {
        BigDecimal result = Wave5Functions.bomPriceStd(Integer.valueOf(-1), Integer.valueOf(1));
        assertEquals(0, result.compareTo(BigDecimal.ZERO),
            "bomPriceStd should return ZERO for invalid product ID");
    }

    @Test
    void testBomPriceLimit_nullInputs_returnsZero() {
        BigDecimal result = Wave5Functions.bomPriceLimit((Integer) null, (Integer) null);
        assertEquals(0, result.compareTo(BigDecimal.ZERO),
            "bomPriceLimit should return ZERO for null inputs");
    }

    @Test
    void testBomPriceList_nullInputs_returnsZero() {
        BigDecimal result = Wave5Functions.bomPriceList((Integer) null, (Integer) null);
        assertEquals(0, result.compareTo(BigDecimal.ZERO),
            "bomPriceList should return ZERO for null inputs");
    }

    @Test
    void testBomPriceStd_nullInputs_returnsZero() {
        BigDecimal result = Wave5Functions.bomPriceStd((Integer) null, (Integer) null);
        assertEquals(0, result.compareTo(BigDecimal.ZERO),
            "bomPriceStd should return ZERO for null inputs");
    }

    // ==================== Helper Methods ====================

    private BigDecimal callSqlFunction(String functionName, int productId, int plvId) {
        String sql = "SELECT " + functionName + "(?, ?)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setInt(1, productId);
            pstmt.setInt(2, plvId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    BigDecimal value = rs.getBigDecimal(1);
                    return value != null ? value : BigDecimal.ZERO;
                }
            }
        } catch (Exception e) {
            fail("SQL function call failed: " + e.getMessage());
        }
        return BigDecimal.ZERO;
    }
}
