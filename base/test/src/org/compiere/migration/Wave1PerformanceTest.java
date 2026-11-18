// base/test/src/org/compiere/migration/Wave1PerformanceTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MCurrency;
import org.compiere.util.CurrencyFunctions;
import org.compiere.util.Env;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

/**
 * Performance tests for Wave 1 currency functions.
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
 * <p><b>Note:</b> Currency functions involve database lookups for precision
 * and rates, so the performance advantage over SQL is smaller than Wave 0
 * pure-computation functions.
 *
 * <p><b>Test Data Requirements:</b>
 * <ul>
 *   <li>USD currency must exist</li>
 * </ul>
 *
 * <p><b>Parallelization:</b> This test uses instance fields for ratio accumulation
 * across repeated test runs. Must run in same thread to prevent race conditions
 * when JUnit parallel execution is enabled.
 */
@Tag("PerformanceTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.SAME_THREAD)  // Prevent parallel execution - uses shared instance state
public class Wave1PerformanceTest extends CommonGWSetup {

    /** Max ratio when per-call overhead is < MAX_OVERHEAD_MS */
    private static final double RELAXED_RATIO = 3.0;
    /** Max ratio when per-call overhead is >= MAX_OVERHEAD_MS */
    private static final double STRICT_RATIO = 1.5;
    /** Threshold in ms: if overhead below this, use RELAXED_RATIO */
    private static final double MAX_OVERHEAD_MS = 1.0;
    private static final int WARMUP_ITERATIONS = 500;
    private static final int TEST_ITERATIONS = 2000;
    private static final int MEASUREMENT_ROUNDS = 5;

    private Integer usdCurrencyId;

    // Instance fields for accumulation (not static ThreadLocal)
    // Reset before each test method's repeated runs
    private double[] ratioAccumulator;
    private long[] javaTimeAccumulator;
    private long[] sqlTimeAccumulator;

    @BeforeAll
    void loadTestData() {
        MCurrency usd = MCurrency.get(Env.getCtx(), "USD");
        assumeTrue(usd != null && usd.get_ID() > 0, "USD currency required for performance tests");
        usdCurrencyId = usd.get_ID();
    }

    @BeforeEach
    void initAccumulator(RepetitionInfo info) {
        if (info.getCurrentRepetition() == 1) {
            ratioAccumulator = new double[MEASUREMENT_ROUNDS];
            javaTimeAccumulator = new long[MEASUREMENT_ROUNDS];
            sqlTimeAccumulator = new long[MEASUREMENT_ROUNDS];
        }
    }

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

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testCurrencyRoundPerformance(RepetitionInfo info) {
        BigDecimal amount = new BigDecimal("123.456789");
        String costing = "N";

        if (info.getCurrentRepetition() == 1) {
            // Warmup on first repetition only
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                CurrencyFunctions.currencyRound(amount, usdCurrencyId, costing);
                SqlFunctionCaller.callCurrencyRound(amount, usdCurrencyId, costing);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            CurrencyFunctions.currencyRound(amount, usdCurrencyId, costing);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callCurrencyRound(amount, usdCurrencyId, costing);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        int idx = info.getCurrentRepetition() - 1;
        ratioAccumulator[idx] = ratio;
        javaTimeAccumulator[idx] = javaTimeNs;
        sqlTimeAccumulator[idx] = sqlTimeNs;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.clone();
            long[] javaTimes = javaTimeAccumulator.clone();
            long[] sqlTimes = sqlTimeAccumulator.clone();
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
            System.out.println("│ PERFORMANCE: currencyRound                                                  │");
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
                String.format("currencyRound: SQL=%.2fms, Java=%.2fms, overhead=%.3fms, ratio=%.2f - %s",
                    medianSqlMs, medianJavaMs, overheadMs, medianRatio, rule));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testCurrencyRatePerformance(RepetitionInfo info) {
        // Same currency for consistent results (avoids rate lookup variability)
        Timestamp convDate = null;
        Integer convTypeId = null;
        Integer clientId = 11;
        Integer orgId = 0;

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                CurrencyFunctions.currencyRate(usdCurrencyId, usdCurrencyId,
                    convDate, convTypeId, clientId, orgId);
                SqlFunctionCaller.callCurrencyRate(usdCurrencyId, usdCurrencyId,
                    convDate, convTypeId, clientId, orgId);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            CurrencyFunctions.currencyRate(usdCurrencyId, usdCurrencyId,
                convDate, convTypeId, clientId, orgId);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callCurrencyRate(usdCurrencyId, usdCurrencyId,
                convDate, convTypeId, clientId, orgId);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        int idx = info.getCurrentRepetition() - 1;
        ratioAccumulator[idx] = ratio;
        javaTimeAccumulator[idx] = javaTimeNs;
        sqlTimeAccumulator[idx] = sqlTimeNs;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.clone();
            long[] javaTimes = javaTimeAccumulator.clone();
            long[] sqlTimes = sqlTimeAccumulator.clone();
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
            System.out.println("│ PERFORMANCE: currencyRate                                                   │");
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
                String.format("currencyRate: SQL=%.2fms, Java=%.2fms, overhead=%.3fms, ratio=%.2f - %s",
                    medianSqlMs, medianJavaMs, overheadMs, medianRatio, rule));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testCurrencyConvertPerformance(RepetitionInfo info) {
        BigDecimal amount = new BigDecimal("100.00");
        // Same currency for consistent results
        Timestamp convDate = null;
        Integer convTypeId = null;
        Integer clientId = 11;
        Integer orgId = 0;

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                CurrencyFunctions.currencyConvert(amount, usdCurrencyId, usdCurrencyId,
                    convDate, convTypeId, clientId, orgId);
                SqlFunctionCaller.callCurrencyConvert(amount, usdCurrencyId, usdCurrencyId,
                    convDate, convTypeId, clientId, orgId);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            CurrencyFunctions.currencyConvert(amount, usdCurrencyId, usdCurrencyId,
                convDate, convTypeId, clientId, orgId);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callCurrencyConvert(amount, usdCurrencyId, usdCurrencyId,
                convDate, convTypeId, clientId, orgId);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        int idx = info.getCurrentRepetition() - 1;
        ratioAccumulator[idx] = ratio;
        javaTimeAccumulator[idx] = javaTimeNs;
        sqlTimeAccumulator[idx] = sqlTimeNs;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.clone();
            long[] javaTimes = javaTimeAccumulator.clone();
            long[] sqlTimes = sqlTimeAccumulator.clone();
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
            System.out.println("│ PERFORMANCE: currencyConvert                                                │");
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
                String.format("currencyConvert: SQL=%.2fms, Java=%.2fms, overhead=%.3fms, ratio=%.2f - %s",
                    medianSqlMs, medianJavaMs, overheadMs, medianRatio, rule));
        }
    }
}
