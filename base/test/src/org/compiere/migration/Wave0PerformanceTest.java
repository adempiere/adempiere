// base/test/src/org/compiere/migration/Wave0PerformanceTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.SqlCompat;
import org.compiere.util.TimeUtil;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;

/**
 * Performance tests for Wave 0 utility functions (date math, rounding).
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
public class Wave0PerformanceTest extends CommonGWSetup {

    /** Max ratio when per-call overhead is < MAX_OVERHEAD_MS */
    private static final double RELAXED_RATIO = 3.0;
    /** Max ratio when per-call overhead is >= MAX_OVERHEAD_MS */
    private static final double STRICT_RATIO = 1.5;
    /** Threshold in ms: if overhead below this, use RELAXED_RATIO */
    private static final double MAX_OVERHEAD_MS = 1.0;
    private static final int WARMUP_ITERATIONS = 1000;
    private static final int TEST_ITERATIONS = 5000;
    private static final int MEASUREMENT_ROUNDS = 5;

    private static final ThreadLocal<double[]> ratioAccumulator = ThreadLocal.withInitial(() -> new double[MEASUREMENT_ROUNDS]);
    private static final ThreadLocal<long[]> javaTimeAccumulator = ThreadLocal.withInitial(() -> new long[MEASUREMENT_ROUNDS]);
    private static final ThreadLocal<long[]> sqlTimeAccumulator = ThreadLocal.withInitial(() -> new long[MEASUREMENT_ROUNDS]);

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
    void testDaysBetweenPerformance(RepetitionInfo info) {
        Timestamp date1 = Timestamp.valueOf("2026-01-15 14:30:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-01 08:00:00");

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                TimeUtil.daysBetweenSql(date1, date2);
                SqlFunctionCaller.callDaysBetween(date1, date2);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            TimeUtil.daysBetweenSql(date1, date2);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callDaysBetween(date1, date2);
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
            java.util.Arrays.sort(ratios);
            java.util.Arrays.sort(javaTimes);
            java.util.Arrays.sort(sqlTimes);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];
            double medianJavaMs = javaTimes[MEASUREMENT_ROUNDS / 2] / 1_000_000.0;
            double medianSqlMs = sqlTimes[MEASUREMENT_ROUNDS / 2] / 1_000_000.0;
            double perCallJavaMs = medianJavaMs / TEST_ITERATIONS;
            double perCallSqlMs = medianSqlMs / TEST_ITERATIONS;

            double overheadMs = perCallJavaMs - perCallSqlMs;
            boolean passed = meetsThreshold(overheadMs, medianRatio);
            String rule = getThresholdRule(overheadMs, medianRatio);

            System.out.println("\n┌─────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("│ PERFORMANCE: daysBetween                                                    │");
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
                String.format("daysBetween: SQL=%.2fms, Java=%.2fms, overhead=%.3fms, ratio=%.2f - %s",
                    medianSqlMs, medianJavaMs, overheadMs, medianRatio, rule));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testAddDaysPerformance(RepetitionInfo info) {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("10");

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                TimeUtil.addDaysSql(datetime, days);
                SqlFunctionCaller.callAddDays(datetime, days);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            TimeUtil.addDaysSql(datetime, days);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callAddDays(datetime, days);
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
            java.util.Arrays.sort(ratios);
            java.util.Arrays.sort(javaTimes);
            java.util.Arrays.sort(sqlTimes);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];
            double medianJavaMs = javaTimes[MEASUREMENT_ROUNDS / 2] / 1_000_000.0;
            double medianSqlMs = sqlTimes[MEASUREMENT_ROUNDS / 2] / 1_000_000.0;
            double perCallJavaMs = medianJavaMs / TEST_ITERATIONS;
            double perCallSqlMs = medianSqlMs / TEST_ITERATIONS;

            double overheadMs = perCallJavaMs - perCallSqlMs;
            boolean passed = meetsThreshold(overheadMs, medianRatio);
            String rule = getThresholdRule(overheadMs, medianRatio);

            System.out.println("\n┌─────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("│ PERFORMANCE: addDays                                                        │");
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
                String.format("addDays: SQL=%.2fms, Java=%.2fms, overhead=%.3fms, ratio=%.2f - %s",
                    medianSqlMs, medianJavaMs, overheadMs, medianRatio, rule));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testRoundPerformance(RepetitionInfo info) {
        BigDecimal value = new BigDecimal("123.456789");
        int scale = 2;

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                SqlCompat.round(value, scale);
                SqlFunctionCaller.callRound(value, scale);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlCompat.round(value, scale);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callRound(value, scale);
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
            java.util.Arrays.sort(ratios);
            java.util.Arrays.sort(javaTimes);
            java.util.Arrays.sort(sqlTimes);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];
            double medianJavaMs = javaTimes[MEASUREMENT_ROUNDS / 2] / 1_000_000.0;
            double medianSqlMs = sqlTimes[MEASUREMENT_ROUNDS / 2] / 1_000_000.0;
            double perCallJavaMs = medianJavaMs / TEST_ITERATIONS;
            double perCallSqlMs = medianSqlMs / TEST_ITERATIONS;

            double overheadMs = perCallJavaMs - perCallSqlMs;
            boolean passed = meetsThreshold(overheadMs, medianRatio);
            String rule = getThresholdRule(overheadMs, medianRatio);

            System.out.println("\n┌─────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("│ PERFORMANCE: round                                                          │");
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
                String.format("round: SQL=%.2fms, Java=%.2fms, overhead=%.3fms, ratio=%.2f - %s",
                    medianSqlMs, medianJavaMs, overheadMs, medianRatio, rule));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testTruncPerformance(RepetitionInfo info) {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");
        String format = "Q";

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                TimeUtil.truncSql(datetime, format);
                SqlFunctionCaller.callTrunc(datetime, format);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            TimeUtil.truncSql(datetime, format);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callTrunc(datetime, format);
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
            java.util.Arrays.sort(ratios);
            java.util.Arrays.sort(javaTimes);
            java.util.Arrays.sort(sqlTimes);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];
            double medianJavaMs = javaTimes[MEASUREMENT_ROUNDS / 2] / 1_000_000.0;
            double medianSqlMs = sqlTimes[MEASUREMENT_ROUNDS / 2] / 1_000_000.0;
            double perCallJavaMs = medianJavaMs / TEST_ITERATIONS;
            double perCallSqlMs = medianSqlMs / TEST_ITERATIONS;

            double overheadMs = perCallJavaMs - perCallSqlMs;
            boolean passed = meetsThreshold(overheadMs, medianRatio);
            String rule = getThresholdRule(overheadMs, medianRatio);

            System.out.println("\n┌─────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("│ PERFORMANCE: trunc                                                          │");
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
                String.format("trunc: SQL=%.2fms, Java=%.2fms, overhead=%.3fms, ratio=%.2f - %s",
                    medianSqlMs, medianJavaMs, overheadMs, medianRatio, rule));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testFirstOfPerformance(RepetitionInfo info) {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");
        String format = "Q";

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                TimeUtil.firstOf(datetime, format);
                SqlFunctionCaller.callFirstOf(datetime, format);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            TimeUtil.firstOf(datetime, format);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callFirstOf(datetime, format);
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
            java.util.Arrays.sort(ratios);
            java.util.Arrays.sort(javaTimes);
            java.util.Arrays.sort(sqlTimes);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];
            double medianJavaMs = javaTimes[MEASUREMENT_ROUNDS / 2] / 1_000_000.0;
            double medianSqlMs = sqlTimes[MEASUREMENT_ROUNDS / 2] / 1_000_000.0;
            double perCallJavaMs = medianJavaMs / TEST_ITERATIONS;
            double perCallSqlMs = medianSqlMs / TEST_ITERATIONS;

            double overheadMs = perCallJavaMs - perCallSqlMs;
            boolean passed = meetsThreshold(overheadMs, medianRatio);
            String rule = getThresholdRule(overheadMs, medianRatio);

            System.out.println("\n┌─────────────────────────────────────────────────────────────────────────────┐");
            System.out.println("│ PERFORMANCE: firstOf                                                        │");
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
                String.format("firstOf: SQL=%.2fms, Java=%.2fms, overhead=%.3fms, ratio=%.2f - %s",
                    medianSqlMs, medianJavaMs, overheadMs, medianRatio, rule));
        }
    }
}
