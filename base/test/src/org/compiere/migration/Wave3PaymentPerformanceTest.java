// base/test/src/org/compiere/migration/Wave3PaymentPerformanceTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.util.Arrays;
import java.util.List;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MPayment;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

/**
 * Performance tests for Wave 3 payment functions.
 * Uses a variable threshold based on absolute overhead:
 * <ul>
 *   <li>If per-call overhead < 1.0ms: allow up to 3.0x ratio (imperceptible difference)</li>
 *   <li>Otherwise: require ratio <= 1.5x</li>
 * </ul>
 *
 * <p>This approach recognizes that a 3x ratio is acceptable when the absolute
 * difference is sub-millisecond (e.g., 0.3ms vs 0.9ms), but tighter control
 * is needed when operations take longer.
 *
 * <p>For production benchmarking, consider using JMH (Java Microbenchmark Harness).
 */
@Tag("PerformanceTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave3PaymentPerformanceTest extends CommonGWSetup {

    /** Max ratio when per-call overhead is < MAX_OVERHEAD_MS */
    private static final double RELAXED_RATIO = 3.0;
    /** Max ratio when per-call overhead is >= MAX_OVERHEAD_MS */
    private static final double STRICT_RATIO = 1.5;
    /** Threshold in ms: if overhead below this, use RELAXED_RATIO */
    private static final double MAX_OVERHEAD_MS = 1.0;
    private static final int WARMUP_ITERATIONS = 100;
    private static final int TEST_ITERATIONS = 500;
    private static final int MEASUREMENT_ROUNDS = 5;

    private List<MPayment> testPayments;
    private static final ThreadLocal<double[]> ratioAccumulator = ThreadLocal.withInitial(() -> new double[MEASUREMENT_ROUNDS]);
    private static final ThreadLocal<long[]> javaTimeAccumulator = ThreadLocal.withInitial(() -> new long[MEASUREMENT_ROUNDS]);
    private static final ThreadLocal<long[]> sqlTimeAccumulator = ThreadLocal.withInitial(() -> new long[MEASUREMENT_ROUNDS]);
    private MigrationMode savedAllocatedMode;
    private MigrationMode savedAvailableMode;

    /**
     * Checks if performance meets the variable threshold criteria.
     * PASS if: (overhead < MAX_OVERHEAD_MS AND ratio <= RELAXED_RATIO) OR (ratio <= STRICT_RATIO)
     */
    private boolean meetsThreshold(double overheadMs, double ratio) {
        if (overheadMs < MAX_OVERHEAD_MS && ratio <= RELAXED_RATIO) {
            return true;
        }
        return ratio <= STRICT_RATIO;
    }

    private String getThresholdRule(double overheadMs, double ratio) {
        if (overheadMs < MAX_OVERHEAD_MS) {
            return String.format("overhead %.3fms < %.1fms, ratio %.2f <= %.1f",
                overheadMs, MAX_OVERHEAD_MS, ratio, RELAXED_RATIO);
        }
        return String.format("ratio %.2f <= %.1f", ratio, STRICT_RATIO);
    }

    @BeforeAll
    void loadTestData() {
        // Load multiple payments for realistic testing
        testPayments = new Query(Env.getCtx(), MPayment.Table_Name,
            "DocStatus IN ('CO','CL')", null)
            .setOnlyActiveRecords(true)
            .setLimit(50)
            .list();
        assumeTrue(testPayments.size() >= 3,
            String.format("SKIPPED: Need at least 3 completed payments for performance test, found %d. " +
                "Performance tests require enough data for statistically meaningful results.", testPayments.size()));

        // Save current modes and switch to JAVA_ONLY for accurate performance measurement
        // Shadow mode would execute BOTH Java and SQL, skewing results
        savedAllocatedMode = MigrationConfig.get("paymentAllocated").getMode();
        savedAvailableMode = MigrationConfig.get("paymentAvailable").getMode();
        setModeInDatabase("paymentAllocated", MigrationMode.JAVA_ONLY);
        setModeInDatabase("paymentAvailable", MigrationMode.JAVA_ONLY);
    }

    @AfterAll
    void restoreModes() {
        // Restore original modes
        setModeInDatabase("paymentAllocated", savedAllocatedMode);
        setModeInDatabase("paymentAvailable", savedAvailableMode);
    }

    private void setModeInDatabase(String functionName, MigrationMode mode) {
        String sql = "UPDATE migration.function_config SET mode = ? WHERE function_name = ?";
        try (java.sql.PreparedStatement pstmt = org.compiere.util.DB.prepareStatement(sql, null)) {
            pstmt.setString(1, mode.name());
            pstmt.setString(2, functionName);
            pstmt.executeUpdate();
            // Clear cache so next call to MigrationConfig.get() reloads from database
            MigrationConfig.clearCache(functionName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set mode for " + functionName, e);
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testPaymentAllocatedPerformance(RepetitionInfo info) {
        if (info.getCurrentRepetition() == 1) {
            // Warmup
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                MPayment p = testPayments.get(i % testPayments.size());
                p.getAllocatedAmt();
                SqlFunctionCaller.callPaymentAllocated(p.getC_Payment_ID(), p.getC_Currency_ID());
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MPayment p = testPayments.get(i % testPayments.size());
            p.getAllocatedAmt();
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MPayment p = testPayments.get(i % testPayments.size());
            SqlFunctionCaller.callPaymentAllocated(p.getC_Payment_ID(), p.getC_Currency_ID());
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        int idx = info.getCurrentRepetition() - 1;
        ratioAccumulator.get()[idx] = ratio;
        javaTimeAccumulator.get()[idx] = javaTimeNs;
        sqlTimeAccumulator.get()[idx] = sqlTimeNs;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get().clone();
            long[] javaTimes = javaTimeAccumulator.get().clone();
            long[] sqlTimes = sqlTimeAccumulator.get().clone();
            Arrays.sort(ratios);
            Arrays.sort(javaTimes);
            Arrays.sort(sqlTimes);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];
            double medianJavaMs = javaTimes[MEASUREMENT_ROUNDS / 2] / 1_000_000.0;
            double medianSqlMs = sqlTimes[MEASUREMENT_ROUNDS / 2] / 1_000_000.0;
            double perCallJavaMs = medianJavaMs / TEST_ITERATIONS;
            double perCallSqlMs = medianSqlMs / TEST_ITERATIONS;

            double overheadMs = perCallJavaMs - perCallSqlMs;
            boolean passed = meetsThreshold(overheadMs, medianRatio);
            String rule = getThresholdRule(overheadMs, medianRatio);

            System.out.println("\n┌─────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("│ PERFORMANCE: paymentAllocated                                               │");
            System.out.println("├─────────────────────────────────────────────────────────────────────────────┤");
            System.out.printf("│ %-20s │ %12s │ %12s │ %12s │ %8s │%n", "Metric", "SQL", "Java", "Diff", "Status");
            System.out.println("├─────────────────────────────────────────────────────────────────────────────┤");
            System.out.printf("│ %-20s │ %9.2f ms │ %9.2f ms │ %+9.2f ms │ %8s │%n",
                "Total (" + TEST_ITERATIONS + " calls)", medianSqlMs, medianJavaMs, medianJavaMs - medianSqlMs,
                passed ? "PASS" : "FAIL");
            System.out.printf("│ %-20s │ %9.3f ms │ %9.3f ms │ %+9.3f ms │          │%n",
                "Per call", perCallSqlMs, perCallJavaMs, overheadMs);
            System.out.printf("│ %-20s │ %12s │ %12s │ %11.0f%% │          │%n",
                "Ratio", "", "", (medianRatio - 1.0) * 100);
            System.out.printf("│ Rule: %-71s │%n", rule);
            System.out.println("└─────────────────────────────────────────────────────────────────────────────┘");

            assertTrue(passed,
                String.format("paymentAllocated: SQL=%.2fms, Java=%.2fms, overhead=%.3fms, ratio=%.2f - %s",
                    medianSqlMs, medianJavaMs, overheadMs, medianRatio, rule));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testPaymentAvailablePerformance(RepetitionInfo info) {
        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                MPayment p = testPayments.get(i % testPayments.size());
                p.getAvailableAmt();
                SqlFunctionCaller.callPaymentAvailable(p.getC_Payment_ID());
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MPayment p = testPayments.get(i % testPayments.size());
            p.getAvailableAmt();
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MPayment p = testPayments.get(i % testPayments.size());
            SqlFunctionCaller.callPaymentAvailable(p.getC_Payment_ID());
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        int idx = info.getCurrentRepetition() - 1;
        ratioAccumulator.get()[idx] = ratio;
        javaTimeAccumulator.get()[idx] = javaTimeNs;
        sqlTimeAccumulator.get()[idx] = sqlTimeNs;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get().clone();
            long[] javaTimes = javaTimeAccumulator.get().clone();
            long[] sqlTimes = sqlTimeAccumulator.get().clone();
            Arrays.sort(ratios);
            Arrays.sort(javaTimes);
            Arrays.sort(sqlTimes);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];
            double medianJavaMs = javaTimes[MEASUREMENT_ROUNDS / 2] / 1_000_000.0;
            double medianSqlMs = sqlTimes[MEASUREMENT_ROUNDS / 2] / 1_000_000.0;
            double perCallJavaMs = medianJavaMs / TEST_ITERATIONS;
            double perCallSqlMs = medianSqlMs / TEST_ITERATIONS;

            double overheadMs = perCallJavaMs - perCallSqlMs;
            boolean passed = meetsThreshold(overheadMs, medianRatio);
            String rule = getThresholdRule(overheadMs, medianRatio);

            System.out.println("\n┌─────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("│ PERFORMANCE: paymentAvailable                                               │");
            System.out.println("├─────────────────────────────────────────────────────────────────────────────┤");
            System.out.printf("│ %-20s │ %12s │ %12s │ %12s │ %8s │%n", "Metric", "SQL", "Java", "Diff", "Status");
            System.out.println("├─────────────────────────────────────────────────────────────────────────────┤");
            System.out.printf("│ %-20s │ %9.2f ms │ %9.2f ms │ %+9.2f ms │ %8s │%n",
                "Total (" + TEST_ITERATIONS + " calls)", medianSqlMs, medianJavaMs, medianJavaMs - medianSqlMs,
                passed ? "PASS" : "FAIL");
            System.out.printf("│ %-20s │ %9.3f ms │ %9.3f ms │ %+9.3f ms │          │%n",
                "Per call", perCallSqlMs, perCallJavaMs, overheadMs);
            System.out.printf("│ %-20s │ %12s │ %12s │ %11.0f%% │          │%n",
                "Ratio", "", "", (medianRatio - 1.0) * 100);
            System.out.printf("│ Rule: %-71s │%n", rule);
            System.out.println("└─────────────────────────────────────────────────────────────────────────────┘");

            assertTrue(passed,
                String.format("paymentAvailable: SQL=%.2fms, Java=%.2fms, overhead=%.3fms, ratio=%.2f - %s",
                    medianSqlMs, medianJavaMs, overheadMs, medianRatio, rule));
        }
    }
}
