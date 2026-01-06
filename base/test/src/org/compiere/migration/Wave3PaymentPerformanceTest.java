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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

/**
 * Performance tests for Wave 3 payment functions.
 * Validates Java implementations meet latency requirements (<=130% of SQL).
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave3PaymentPerformanceTest extends CommonGWSetup {

    private static final double MAX_LATENCY_RATIO = 1.30;
    private static final int WARMUP_ITERATIONS = 100;
    private static final int TEST_ITERATIONS = 500;
    private static final int MEASUREMENT_ROUNDS = 5;

    private List<MPayment> testPayments;
    private static final ThreadLocal<double[]> ratioAccumulator = ThreadLocal.withInitial(() -> new double[MEASUREMENT_ROUNDS]);

    @BeforeAll
    void loadTestData() {
        // Load multiple payments for realistic testing
        testPayments = new Query(Env.getCtx(), MPayment.Table_Name,
            "DocStatus IN ('CO','CL')", null)
            .setOnlyActiveRecords(true)
            .setLimit(50)
            .list();
        assumeTrue(testPayments.size() >= 10, "Need at least 10 payments for performance test");
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
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("paymentAllocated Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, Arrays.toString(ratios)));
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
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("paymentAvailable Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, Arrays.toString(ratios)));
        }
    }
}
