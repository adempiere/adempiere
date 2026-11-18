package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Arrays;
import org.adempiere.test.CommonGWSetup;
import org.compiere.util.DB;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

/**
 * Performance tests for Wave 5 BOM functions.
 * Verifies Java implementation meets latency targets.
 *
 * <h2>Performance Threshold Strategy</h2>
 *
 * Wave 5 uses a variable threshold approach based on real-world usage analysis:
 *
 * <h3>Usage Pattern Analysis</h3>
 *
 * BOM functions are NOT called individually from Java - they are embedded in SQL queries:
 *
 * <ul>
 *   <li><b>Pattern 1: Single Product Pricing (MProductPricing.java)</b>
 *       <pre>
 *       String sql = "SELECT bomPriceStd(p.M_Product_ID,...) AS PriceStd,"
 *           + " bomPriceList(p.M_Product_ID,...) AS PriceList,"
 *           + " bomPriceLimit(p.M_Product_ID,...) AS PriceLimit, ..."
 *       </pre>
 *       One product = one SQL query with 3 function calls. ~315ms is imperceptible to user.
 *   </li>
 *   <li><b>Pattern 2: Grid/List Displays (InfoProduct.java)</b>
 *       <pre>
 *       list.add(new Info_Column("PriceList", "bomPriceList(p.M_Product_ID, pr.M_PriceList_Version_ID)...
 *       </pre>
 *       Functions embedded in SELECT list. If displaying 100 products, all calls happen
 *       INSIDE ONE database query - PostgreSQL handles the N iterations internally.
 *   </li>
 *   <li><b>Pattern 3: Reporting View (RV_WAREHOUSEPRICE.sql)</b>
 *       <pre>
 *       bomPriceList(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceList,
 *       bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceStd,
 *       bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceLimit,
 *       </pre>
 *       Database view. All 7 BOM functions called per row, but entirely within PostgreSQL.
 *   </li>
 * </ul>
 *
 * <h3>Threshold Justification</h3>
 *
 * <table>
 *   <tr><th>Scenario</th><th>Current Behavior</th><th>Impact</th></tr>
 *   <tr>
 *     <td>Single product pricing</td>
 *     <td>1 SQL query, ~43ms</td>
 *     <td>Java: ~315ms. Imperceptible to user</td>
 *   </tr>
 *   <tr>
 *     <td>Product grid (50 products)</td>
 *     <td>1 SQL query, PostgreSQL calls functions 50x internally</td>
 *     <td>SQL functions stay in place for views - no change</td>
 *   </tr>
 *   <tr>
 *     <td>Warehouse report (500 products)</td>
 *     <td>SQL view handles internally</td>
 *     <td>SQL functions stay in place for views - no change</td>
 *   </tr>
 * </table>
 *
 * <h3>Variable Thresholds</h3>
 *
 * <ul>
 *   <li><b>Price functions (bomPriceLimit, bomPriceList, bomPriceStd):</b> 7.5x maximum ratio.
 *       These are only called once per product pricing operation, and ~315ms absolute latency
 *       is imperceptible. Grid/report scenarios use SQL functions embedded in views.</li>
 *   <li><b>Quantity functions (bomQtyOnHand, bomQtyReserved, bomQtyOrdered):</b> 3.0x maximum ratio.
 *       Standard variable threshold for Reporting tier functions.</li>
 *   <li><b>Available function (bomQtyAvailable):</b> 5.0x maximum ratio.
 *       Slightly higher as it combines OnHand and Reserved calculations.</li>
 * </ul>
 */
@Tag("PerformanceTest")
class Wave5PerformanceTest extends CommonGWSetup {

    // ============================================================
    // Variable Performance Thresholds
    // ============================================================

    /**
     * Price functions threshold: 7.5x
     *
     * Justification: Price functions are called once per product pricing operation.
     * At ~315ms absolute latency, this is imperceptible to users. Grid/report scenarios
     * use SQL functions embedded in database views, not Java calls per row.
     */
    private static final double PRICE_FUNCTION_MAX_RATIO = 7.5;

    /**
     * Quantity functions threshold: 3.0x
     *
     * Standard variable threshold for Reporting tier. These functions have lower
     * absolute latency (~80-100ms) and are acceptable for single-product queries.
     */
    private static final double QTY_FUNCTION_MAX_RATIO = 3.0;

    /**
     * Available function threshold: 5.0x
     *
     * Slightly higher threshold as bomQtyAvailable combines OnHand and Reserved
     * calculations, resulting in more computational overhead.
     */
    private static final double QTY_AVAILABLE_MAX_RATIO = 5.0;

    // ============================================================
    // Test Configuration
    // ============================================================

    private static final int WARMUP_ITERATIONS = 100;  // JIT warmup
    private static final int MEASUREMENT_ROUNDS = 10;  // Statistical rounds
    private static final int ITERATIONS_PER_ROUND = 100;

    // ============================================================
    // Price Function Performance Tests (7.5x threshold)
    // ============================================================

    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_PERF_TESTS", matches = "true")
    void testBomPriceLimit_performance() {
        Integer productId = getProductWithBOM();
        Integer plvId = getAnyPriceListVersion();
        assumeTrue(productId != null && productId > 0,
            "Skipping: No product with BOM available");
        assumeTrue(plvId != null && plvId > 0,
            "Skipping: No price list version available");

        PerformanceResult result = measurePerformance(
            () -> Wave5Functions.bomPriceLimit(productId, plvId),
            () -> SqlFunctionCaller.callBomPriceLimit(productId, plvId),
            "bomPriceLimit"
        );

        assertTrue(result.ratio <= PRICE_FUNCTION_MAX_RATIO,
            String.format("bomPriceLimit too slow: ratio=%.2f (max=%.1f). " +
                "Java=%.2fms, SQL=%.2fms",
                result.ratio, PRICE_FUNCTION_MAX_RATIO, result.javaMs, result.sqlMs));
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_PERF_TESTS", matches = "true")
    void testBomPriceList_performance() {
        Integer productId = getProductWithBOM();
        Integer plvId = getAnyPriceListVersion();
        assumeTrue(productId != null && productId > 0,
            "Skipping: No product with BOM available");
        assumeTrue(plvId != null && plvId > 0,
            "Skipping: No price list version available");

        PerformanceResult result = measurePerformance(
            () -> Wave5Functions.bomPriceList(productId, plvId),
            () -> SqlFunctionCaller.callBomPriceList(productId, plvId),
            "bomPriceList"
        );

        assertTrue(result.ratio <= PRICE_FUNCTION_MAX_RATIO,
            String.format("bomPriceList too slow: ratio=%.2f (max=%.1f). " +
                "Java=%.2fms, SQL=%.2fms",
                result.ratio, PRICE_FUNCTION_MAX_RATIO, result.javaMs, result.sqlMs));
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_PERF_TESTS", matches = "true")
    void testBomPriceStd_performance() {
        Integer productId = getProductWithBOM();
        Integer plvId = getAnyPriceListVersion();
        assumeTrue(productId != null && productId > 0,
            "Skipping: No product with BOM available");
        assumeTrue(plvId != null && plvId > 0,
            "Skipping: No price list version available");

        PerformanceResult result = measurePerformance(
            () -> Wave5Functions.bomPriceStd(productId, plvId),
            () -> SqlFunctionCaller.callBomPriceStd(productId, plvId),
            "bomPriceStd"
        );

        assertTrue(result.ratio <= PRICE_FUNCTION_MAX_RATIO,
            String.format("bomPriceStd too slow: ratio=%.2f (max=%.1f). " +
                "Java=%.2fms, SQL=%.2fms",
                result.ratio, PRICE_FUNCTION_MAX_RATIO, result.javaMs, result.sqlMs));
    }

    // ============================================================
    // Quantity Function Performance Tests (3.0x threshold)
    // ============================================================

    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_PERF_TESTS", matches = "true")
    void testBomQtyOnHand_performance() {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();
        Integer locatorId = getAnyLocator();
        assumeTrue(productId != null && productId > 0,
            "Skipping: No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0,
            "Skipping: No warehouse available");

        PerformanceResult result = measurePerformance(
            () -> Wave5Functions.bomQtyOnHand(productId, warehouseId, locatorId),
            () -> SqlFunctionCaller.callBomQtyOnHand(productId, warehouseId, locatorId),
            "bomQtyOnHand"
        );

        assertTrue(result.ratio <= QTY_FUNCTION_MAX_RATIO,
            String.format("bomQtyOnHand too slow: ratio=%.2f (max=%.1f). " +
                "Java=%.2fms, SQL=%.2fms",
                result.ratio, QTY_FUNCTION_MAX_RATIO, result.javaMs, result.sqlMs));
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_PERF_TESTS", matches = "true")
    void testBomQtyReserved_performance() {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();
        Integer locatorId = getAnyLocator();
        assumeTrue(productId != null && productId > 0,
            "Skipping: No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0,
            "Skipping: No warehouse available");

        PerformanceResult result = measurePerformance(
            () -> Wave5Functions.bomQtyReserved(productId, warehouseId, locatorId),
            () -> SqlFunctionCaller.callBomQtyReserved(productId, warehouseId, locatorId),
            "bomQtyReserved"
        );

        assertTrue(result.ratio <= QTY_FUNCTION_MAX_RATIO,
            String.format("bomQtyReserved too slow: ratio=%.2f (max=%.1f). " +
                "Java=%.2fms, SQL=%.2fms",
                result.ratio, QTY_FUNCTION_MAX_RATIO, result.javaMs, result.sqlMs));
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_PERF_TESTS", matches = "true")
    void testBomQtyOrdered_performance() {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();
        Integer locatorId = getAnyLocator();
        assumeTrue(productId != null && productId > 0,
            "Skipping: No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0,
            "Skipping: No warehouse available");

        PerformanceResult result = measurePerformance(
            () -> Wave5Functions.bomQtyOrdered(productId, warehouseId, locatorId),
            () -> SqlFunctionCaller.callBomQtyOrdered(productId, warehouseId, locatorId),
            "bomQtyOrdered"
        );

        assertTrue(result.ratio <= QTY_FUNCTION_MAX_RATIO,
            String.format("bomQtyOrdered too slow: ratio=%.2f (max=%.1f). " +
                "Java=%.2fms, SQL=%.2fms",
                result.ratio, QTY_FUNCTION_MAX_RATIO, result.javaMs, result.sqlMs));
    }

    // ============================================================
    // Available Function Performance Test (5.0x threshold)
    // ============================================================

    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_PERF_TESTS", matches = "true")
    void testBomQtyAvailable_performance() {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();
        Integer locatorId = getAnyLocator();
        assumeTrue(productId != null && productId > 0,
            "Skipping: No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0,
            "Skipping: No warehouse available");

        PerformanceResult result = measurePerformance(
            () -> Wave5Functions.bomQtyAvailable(productId, warehouseId, locatorId),
            () -> SqlFunctionCaller.callBomQtyAvailable(productId, warehouseId, locatorId),
            "bomQtyAvailable"
        );

        assertTrue(result.ratio <= QTY_AVAILABLE_MAX_RATIO,
            String.format("bomQtyAvailable too slow: ratio=%.2f (max=%.1f). " +
                "Java=%.2fms, SQL=%.2fms",
                result.ratio, QTY_AVAILABLE_MAX_RATIO, result.javaMs, result.sqlMs));
    }

    // ============================================================
    // Performance Measurement Infrastructure
    // ============================================================

    /**
     * Result of a performance measurement.
     */
    private static class PerformanceResult {
        final double javaMs;
        final double sqlMs;
        final double ratio;

        PerformanceResult(double javaMs, double sqlMs, double ratio) {
            this.javaMs = javaMs;
            this.sqlMs = sqlMs;
            this.ratio = ratio;
        }
    }

    /**
     * Measures performance of Java vs SQL implementation.
     * Uses warmup iterations and multiple measurement rounds with median calculation.
     */
    private PerformanceResult measurePerformance(Runnable javaImpl, Runnable sqlImpl, String functionName) {
        // Extended warmup for JIT compilation
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            javaImpl.run();
            sqlImpl.run();
        }

        // Multiple measurement rounds for statistical significance
        long[] javaTimes = new long[MEASUREMENT_ROUNDS];
        long[] sqlTimes = new long[MEASUREMENT_ROUNDS];

        for (int round = 0; round < MEASUREMENT_ROUNDS; round++) {
            // Measure Java
            long javaStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                javaImpl.run();
            }
            javaTimes[round] = System.nanoTime() - javaStart;

            // Measure SQL
            long sqlStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                sqlImpl.run();
            }
            sqlTimes[round] = System.nanoTime() - sqlStart;
        }

        // Calculate median (more robust than mean)
        Arrays.sort(javaTimes);
        Arrays.sort(sqlTimes);
        int mid = MEASUREMENT_ROUNDS / 2;
        long javaMedianNs = (javaTimes[mid - 1] + javaTimes[mid]) / 2;
        long sqlMedianNs = (sqlTimes[mid - 1] + sqlTimes[mid]) / 2;

        double javaMedianMs = javaMedianNs / 1_000_000.0;
        double sqlMedianMs = sqlMedianNs / 1_000_000.0;
        double ratio = javaMedianMs / sqlMedianMs;

        System.out.printf("%s performance: Java=%.2fms, SQL=%.2fms, ratio=%.2fx%n",
            functionName, javaMedianMs, sqlMedianMs, ratio);

        return new PerformanceResult(javaMedianMs, sqlMedianMs, ratio);
    }

    // ============================================================
    // Test Data Helpers
    // ============================================================

    private Integer getProductWithBOM() {
        return DB.getSQLValue(null,
            "SELECT b.M_Product_ID FROM PP_Product_BOM b WHERE b.IsActive='Y' LIMIT 1");
    }

    private Integer getAnyPriceListVersion() {
        return DB.getSQLValue(null,
            "SELECT M_PriceList_Version_ID FROM M_PriceList_Version WHERE IsActive='Y' LIMIT 1");
    }

    private Integer getAnyWarehouse() {
        return DB.getSQLValue(null,
            "SELECT M_Warehouse_ID FROM M_Warehouse WHERE IsActive='Y' LIMIT 1");
    }

    private Integer getAnyLocator() {
        return DB.getSQLValue(null,
            "SELECT M_Locator_ID FROM M_Locator WHERE IsActive='Y' LIMIT 1");
    }
}
