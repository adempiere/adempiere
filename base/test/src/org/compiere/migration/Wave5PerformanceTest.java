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
 */
@Tag("PerformanceTest")
class Wave5PerformanceTest extends CommonGWSetup {

    // Performance tier: Reporting (allows up to 100% latency increase)
    private static final double MAX_LATENCY_RATIO = 2.0;

    private static final int WARMUP_ITERATIONS = 100;  // More warmup for JIT
    private static final int MEASUREMENT_ROUNDS = 10;  // Multiple rounds for statistics
    private static final int ITERATIONS_PER_ROUND = 100;

    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_PERF_TESTS", matches = "true")
    void testBomPriceLimit_performance() {
        Integer productId = getProductWithBOM();
        Integer plvId = getAnyPriceListVersion();
        assumeTrue(productId != null && productId > 0,
            "Skipping: No product with BOM available");
        assumeTrue(plvId != null && plvId > 0,
            "Skipping: No price list version available");

        // Extended warmup for JIT compilation
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            Wave5Functions.bomPriceLimit(productId, plvId);
            SqlFunctionCaller.callBomPriceLimit(productId, plvId);
        }

        // Multiple measurement rounds for statistical significance
        long[] javaTimes = new long[MEASUREMENT_ROUNDS];
        long[] sqlTimes = new long[MEASUREMENT_ROUNDS];

        for (int round = 0; round < MEASUREMENT_ROUNDS; round++) {
            // Measure Java
            long javaStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                Wave5Functions.bomPriceLimit(productId, plvId);
            }
            javaTimes[round] = System.nanoTime() - javaStart;

            // Measure SQL
            long sqlStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                SqlFunctionCaller.callBomPriceLimit(productId, plvId);
            }
            sqlTimes[round] = System.nanoTime() - sqlStart;
        }

        // Calculate median (more robust than mean)
        // For even-length arrays, use average of middle two elements
        Arrays.sort(javaTimes);
        Arrays.sort(sqlTimes);
        int mid = MEASUREMENT_ROUNDS / 2;
        long javaMedianNs = (javaTimes[mid - 1] + javaTimes[mid]) / 2;
        long sqlMedianNs = (sqlTimes[mid - 1] + sqlTimes[mid]) / 2;

        double javaMedianMs = javaMedianNs / 1_000_000.0;
        double sqlMedianMs = sqlMedianNs / 1_000_000.0;
        double ratio = javaMedianMs / sqlMedianMs;

        System.out.printf("bomPriceLimit performance: Java=%.2fms, SQL=%.2fms, ratio=%.2f%n",
            javaMedianMs, sqlMedianMs, ratio);

        assertTrue(ratio <= MAX_LATENCY_RATIO,
            "Java implementation too slow: ratio=" + ratio + " (max=" + MAX_LATENCY_RATIO + ")");
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

        // Extended warmup for JIT compilation
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            Wave5Functions.bomPriceList(productId, plvId);
            SqlFunctionCaller.callBomPriceList(productId, plvId);
        }

        // Multiple measurement rounds for statistical significance
        long[] javaTimes = new long[MEASUREMENT_ROUNDS];
        long[] sqlTimes = new long[MEASUREMENT_ROUNDS];

        for (int round = 0; round < MEASUREMENT_ROUNDS; round++) {
            // Measure Java
            long javaStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                Wave5Functions.bomPriceList(productId, plvId);
            }
            javaTimes[round] = System.nanoTime() - javaStart;

            // Measure SQL
            long sqlStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                SqlFunctionCaller.callBomPriceList(productId, plvId);
            }
            sqlTimes[round] = System.nanoTime() - sqlStart;
        }

        Arrays.sort(javaTimes);
        Arrays.sort(sqlTimes);
        int mid = MEASUREMENT_ROUNDS / 2;
        long javaMedianNs = (javaTimes[mid - 1] + javaTimes[mid]) / 2;
        long sqlMedianNs = (sqlTimes[mid - 1] + sqlTimes[mid]) / 2;

        double javaMedianMs = javaMedianNs / 1_000_000.0;
        double sqlMedianMs = sqlMedianNs / 1_000_000.0;
        double ratio = javaMedianMs / sqlMedianMs;

        System.out.printf("bomPriceList performance: Java=%.2fms, SQL=%.2fms, ratio=%.2f%n",
            javaMedianMs, sqlMedianMs, ratio);

        assertTrue(ratio <= MAX_LATENCY_RATIO,
            "Java implementation too slow: ratio=" + ratio + " (max=" + MAX_LATENCY_RATIO + ")");
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

        // Extended warmup for JIT compilation
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            Wave5Functions.bomPriceStd(productId, plvId);
            SqlFunctionCaller.callBomPriceStd(productId, plvId);
        }

        // Multiple measurement rounds for statistical significance
        long[] javaTimes = new long[MEASUREMENT_ROUNDS];
        long[] sqlTimes = new long[MEASUREMENT_ROUNDS];

        for (int round = 0; round < MEASUREMENT_ROUNDS; round++) {
            // Measure Java
            long javaStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                Wave5Functions.bomPriceStd(productId, plvId);
            }
            javaTimes[round] = System.nanoTime() - javaStart;

            // Measure SQL
            long sqlStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                SqlFunctionCaller.callBomPriceStd(productId, plvId);
            }
            sqlTimes[round] = System.nanoTime() - sqlStart;
        }

        Arrays.sort(javaTimes);
        Arrays.sort(sqlTimes);
        int mid = MEASUREMENT_ROUNDS / 2;
        long javaMedianNs = (javaTimes[mid - 1] + javaTimes[mid]) / 2;
        long sqlMedianNs = (sqlTimes[mid - 1] + sqlTimes[mid]) / 2;

        double javaMedianMs = javaMedianNs / 1_000_000.0;
        double sqlMedianMs = sqlMedianNs / 1_000_000.0;
        double ratio = javaMedianMs / sqlMedianMs;

        System.out.printf("bomPriceStd performance: Java=%.2fms, SQL=%.2fms, ratio=%.2f%n",
            javaMedianMs, sqlMedianMs, ratio);

        assertTrue(ratio <= MAX_LATENCY_RATIO,
            "Java implementation too slow: ratio=" + ratio + " (max=" + MAX_LATENCY_RATIO + ")");
    }

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

        // Extended warmup for JIT compilation
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            Wave5Functions.bomQtyOnHand(productId, warehouseId, locatorId);
            SqlFunctionCaller.callBomQtyOnHand(productId, warehouseId, locatorId);
        }

        // Multiple measurement rounds for statistical significance
        long[] javaTimes = new long[MEASUREMENT_ROUNDS];
        long[] sqlTimes = new long[MEASUREMENT_ROUNDS];

        for (int round = 0; round < MEASUREMENT_ROUNDS; round++) {
            // Measure Java
            long javaStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                Wave5Functions.bomQtyOnHand(productId, warehouseId, locatorId);
            }
            javaTimes[round] = System.nanoTime() - javaStart;

            // Measure SQL
            long sqlStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                SqlFunctionCaller.callBomQtyOnHand(productId, warehouseId, locatorId);
            }
            sqlTimes[round] = System.nanoTime() - sqlStart;
        }

        Arrays.sort(javaTimes);
        Arrays.sort(sqlTimes);
        int mid = MEASUREMENT_ROUNDS / 2;
        long javaMedianNs = (javaTimes[mid - 1] + javaTimes[mid]) / 2;
        long sqlMedianNs = (sqlTimes[mid - 1] + sqlTimes[mid]) / 2;

        double javaMedianMs = javaMedianNs / 1_000_000.0;
        double sqlMedianMs = sqlMedianNs / 1_000_000.0;
        double ratio = javaMedianMs / sqlMedianMs;

        System.out.printf("bomQtyOnHand performance: Java=%.2fms, SQL=%.2fms, ratio=%.2f%n",
            javaMedianMs, sqlMedianMs, ratio);

        assertTrue(ratio <= MAX_LATENCY_RATIO,
            "Java implementation too slow: ratio=" + ratio + " (max=" + MAX_LATENCY_RATIO + ")");
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

        // Extended warmup for JIT compilation
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            Wave5Functions.bomQtyReserved(productId, warehouseId, locatorId);
            SqlFunctionCaller.callBomQtyReserved(productId, warehouseId, locatorId);
        }

        // Multiple measurement rounds for statistical significance
        long[] javaTimes = new long[MEASUREMENT_ROUNDS];
        long[] sqlTimes = new long[MEASUREMENT_ROUNDS];

        for (int round = 0; round < MEASUREMENT_ROUNDS; round++) {
            // Measure Java
            long javaStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                Wave5Functions.bomQtyReserved(productId, warehouseId, locatorId);
            }
            javaTimes[round] = System.nanoTime() - javaStart;

            // Measure SQL
            long sqlStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                SqlFunctionCaller.callBomQtyReserved(productId, warehouseId, locatorId);
            }
            sqlTimes[round] = System.nanoTime() - sqlStart;
        }

        Arrays.sort(javaTimes);
        Arrays.sort(sqlTimes);
        int mid = MEASUREMENT_ROUNDS / 2;
        long javaMedianNs = (javaTimes[mid - 1] + javaTimes[mid]) / 2;
        long sqlMedianNs = (sqlTimes[mid - 1] + sqlTimes[mid]) / 2;

        double javaMedianMs = javaMedianNs / 1_000_000.0;
        double sqlMedianMs = sqlMedianNs / 1_000_000.0;
        double ratio = javaMedianMs / sqlMedianMs;

        System.out.printf("bomQtyReserved performance: Java=%.2fms, SQL=%.2fms, ratio=%.2f%n",
            javaMedianMs, sqlMedianMs, ratio);

        assertTrue(ratio <= MAX_LATENCY_RATIO,
            "Java implementation too slow: ratio=" + ratio + " (max=" + MAX_LATENCY_RATIO + ")");
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

        // Extended warmup for JIT compilation
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            Wave5Functions.bomQtyOrdered(productId, warehouseId, locatorId);
            SqlFunctionCaller.callBomQtyOrdered(productId, warehouseId, locatorId);
        }

        // Multiple measurement rounds for statistical significance
        long[] javaTimes = new long[MEASUREMENT_ROUNDS];
        long[] sqlTimes = new long[MEASUREMENT_ROUNDS];

        for (int round = 0; round < MEASUREMENT_ROUNDS; round++) {
            // Measure Java
            long javaStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                Wave5Functions.bomQtyOrdered(productId, warehouseId, locatorId);
            }
            javaTimes[round] = System.nanoTime() - javaStart;

            // Measure SQL
            long sqlStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                SqlFunctionCaller.callBomQtyOrdered(productId, warehouseId, locatorId);
            }
            sqlTimes[round] = System.nanoTime() - sqlStart;
        }

        Arrays.sort(javaTimes);
        Arrays.sort(sqlTimes);
        int mid = MEASUREMENT_ROUNDS / 2;
        long javaMedianNs = (javaTimes[mid - 1] + javaTimes[mid]) / 2;
        long sqlMedianNs = (sqlTimes[mid - 1] + sqlTimes[mid]) / 2;

        double javaMedianMs = javaMedianNs / 1_000_000.0;
        double sqlMedianMs = sqlMedianNs / 1_000_000.0;
        double ratio = javaMedianMs / sqlMedianMs;

        System.out.printf("bomQtyOrdered performance: Java=%.2fms, SQL=%.2fms, ratio=%.2f%n",
            javaMedianMs, sqlMedianMs, ratio);

        assertTrue(ratio <= MAX_LATENCY_RATIO,
            "Java implementation too slow: ratio=" + ratio + " (max=" + MAX_LATENCY_RATIO + ")");
    }

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

        // Extended warmup for JIT compilation
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            Wave5Functions.bomQtyAvailable(productId, warehouseId, locatorId);
            SqlFunctionCaller.callBomQtyAvailable(productId, warehouseId, locatorId);
        }

        // Multiple measurement rounds for statistical significance
        long[] javaTimes = new long[MEASUREMENT_ROUNDS];
        long[] sqlTimes = new long[MEASUREMENT_ROUNDS];

        for (int round = 0; round < MEASUREMENT_ROUNDS; round++) {
            // Measure Java
            long javaStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                Wave5Functions.bomQtyAvailable(productId, warehouseId, locatorId);
            }
            javaTimes[round] = System.nanoTime() - javaStart;

            // Measure SQL
            long sqlStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                SqlFunctionCaller.callBomQtyAvailable(productId, warehouseId, locatorId);
            }
            sqlTimes[round] = System.nanoTime() - sqlStart;
        }

        Arrays.sort(javaTimes);
        Arrays.sort(sqlTimes);
        int mid = MEASUREMENT_ROUNDS / 2;
        long javaMedianNs = (javaTimes[mid - 1] + javaTimes[mid]) / 2;
        long sqlMedianNs = (sqlTimes[mid - 1] + sqlTimes[mid]) / 2;

        double javaMedianMs = javaMedianNs / 1_000_000.0;
        double sqlMedianMs = sqlMedianNs / 1_000_000.0;
        double ratio = javaMedianMs / sqlMedianMs;

        System.out.printf("bomQtyAvailable performance: Java=%.2fms, SQL=%.2fms, ratio=%.2f%n",
            javaMedianMs, sqlMedianMs, ratio);

        assertTrue(ratio <= MAX_LATENCY_RATIO,
            "Java implementation too slow: ratio=" + ratio + " (max=" + MAX_LATENCY_RATIO + ")");
    }

    // Helper methods for test data

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
