package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MInvoice;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.InvoiceFunctions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

/**
 * Performance tests for Wave 3 invoice paid functions.
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
public class Wave3InvoicePaidPerformanceTest extends CommonGWSetup {

    private static final double MAX_LATENCY_RATIO = 1.30;
    private static final int WARMUP_ITERATIONS = 100;
    private static final int TEST_ITERATIONS = 500;
    private static final int MEASUREMENT_ROUNDS = 5;

    private List<MInvoice> testInvoices;
    private static final ThreadLocal<double[]> ratioAccumulator = ThreadLocal.withInitial(() -> new double[MEASUREMENT_ROUNDS]);

    @BeforeAll
    void loadTestData() {
        // Load multiple invoices for realistic testing
        testInvoices = new Query(Env.getCtx(), MInvoice.Table_Name,
            "DocStatus IN ('CO','CL')", null)
            .setOnlyActiveRecords(true)
            .setLimit(50)
            .list();
        assumeTrue(testInvoices.size() >= 10, "Need at least 10 invoices for performance test");
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testInvoicePaidPerformance(RepetitionInfo info) {
        BigDecimal mult = BigDecimal.ONE;

        if (info.getCurrentRepetition() == 1) {
            // Warmup
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                MInvoice inv = testInvoices.get(i % testInvoices.size());
                InvoiceFunctions.invoicePaid(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult, null);
                SqlFunctionCaller.callInvoicePaid(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MInvoice inv = testInvoices.get(i % testInvoices.size());
            InvoiceFunctions.invoicePaid(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult, null);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MInvoice inv = testInvoices.get(i % testInvoices.size());
            SqlFunctionCaller.callInvoicePaid(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("invoicePaid Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, Arrays.toString(ratios)));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testInvoicePaidToDatePerformance(RepetitionInfo info) {
        BigDecimal mult = BigDecimal.ONE;
        Timestamp dateAcct = new Timestamp(System.currentTimeMillis());

        if (info.getCurrentRepetition() == 1) {
            // Warmup
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                MInvoice inv = testInvoices.get(i % testInvoices.size());
                InvoiceFunctions.invoicePaidToDate(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult, dateAcct, null);
                SqlFunctionCaller.callInvoicePaidToDate(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult, dateAcct);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MInvoice inv = testInvoices.get(i % testInvoices.size());
            InvoiceFunctions.invoicePaidToDate(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult, dateAcct, null);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MInvoice inv = testInvoices.get(i % testInvoices.size());
            SqlFunctionCaller.callInvoicePaidToDate(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult, dateAcct);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("invoicePaidToDate Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, Arrays.toString(ratios)));
        }
    }
}
