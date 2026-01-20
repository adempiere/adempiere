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
 * Validates Java implementations meet latency requirements (<=130% of SQL).
 *
 * <p><b>Note:</b> Currency functions involve database lookups for precision
 * and rates, so the performance advantage over SQL is smaller than Wave 0
 * pure-computation functions. The 130% threshold catches regressions.
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

    private static final double MAX_LATENCY_RATIO = 1.30;
    private static final int WARMUP_ITERATIONS = 500;
    private static final int TEST_ITERATIONS = 2000;
    private static final int MEASUREMENT_ROUNDS = 5;

    private Integer usdCurrencyId;

    // Instance field for ratio accumulation (not static ThreadLocal)
    // Reset before each test method's repeated runs
    private double[] ratioAccumulator;

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
        }
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
        ratioAccumulator[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            Arrays.sort(ratioAccumulator);
            double medianRatio = ratioAccumulator[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("currencyRound Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, Arrays.toString(ratioAccumulator)));
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
        ratioAccumulator[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            Arrays.sort(ratioAccumulator);
            double medianRatio = ratioAccumulator[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("currencyRate Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, Arrays.toString(ratioAccumulator)));
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
        ratioAccumulator[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            Arrays.sort(ratioAccumulator);
            double medianRatio = ratioAccumulator[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("currencyConvert Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, Arrays.toString(ratioAccumulator)));
        }
    }
}
