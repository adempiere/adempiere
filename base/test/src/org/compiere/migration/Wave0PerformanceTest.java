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
 * Performance tests validating Java implementations meet latency requirements.
 *
 * <p><b>Design Note:</b> The MAX_LATENCY_RATIO threshold (1.30) is a sanity check to catch
 * catastrophic performance regressions, NOT a precise performance target. In practice,
 * Java implementations should be 100-1000x faster than SQL due to:
 * <ul>
 *   <li>No network round-trip to database</li>
 *   <li>No JDBC marshalling overhead</li>
 *   <li>No PostgreSQL function call overhead</li>
 * </ul>
 * The 130% threshold allows for measurement noise while detecting severe implementation
 * problems (e.g., accidental O(n^2) algorithms, excessive object allocation).
 *
 * <p>For production benchmarking, consider using JMH (Java Microbenchmark Harness).
 * These tests provide a reasonable sanity check for CI but are not rigorous benchmarks.
 */
@Tag("PerformanceTest")
public class Wave0PerformanceTest extends CommonGWSetup {

    /**
     * Sanity check threshold: Java must not exceed 130% of SQL latency.
     * In practice, Java should be orders of magnitude faster.
     */
    private static final double MAX_LATENCY_RATIO = 1.30;
    private static final int WARMUP_ITERATIONS = 1000;
    private static final int TEST_ITERATIONS = 5000;
    private static final int MEASUREMENT_ROUNDS = 5;

    // Accumulator for ratio results across repetitions
    private static final ThreadLocal<double[]> ratioAccumulator = ThreadLocal.withInitial(() -> new double[MEASUREMENT_ROUNDS]);

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testDaysBetweenPerformance(RepetitionInfo info) {
        Timestamp date1 = Timestamp.valueOf("2026-01-15 14:30:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-01 08:00:00");

        // Warmup both paths on first iteration
        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                TimeUtil.daysBetweenSql(date1, date2);
                SqlFunctionCaller.callDaysBetween(date1, date2);
            }
        }

        // Measure Java
        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            TimeUtil.daysBetweenSql(date1, date2);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        // Measure SQL
        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callDaysBetween(date1, date2);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        // On last repetition, check median
        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            java.util.Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("daysBetween Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, java.util.Arrays.toString(ratios)));
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
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            java.util.Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("addDays Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, java.util.Arrays.toString(ratios)));
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
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            java.util.Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("round Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, java.util.Arrays.toString(ratios)));
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
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            java.util.Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("trunc Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, java.util.Arrays.toString(ratios)));
        }
    }

    // TODO: Enable when TimeUtil.firstOf is implemented
    /*
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
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            java.util.Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("firstOf Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, java.util.Arrays.toString(ratios)));
        }
    }
    */
}
