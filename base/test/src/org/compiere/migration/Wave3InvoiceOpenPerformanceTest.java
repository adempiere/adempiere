package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoicePaySchedule;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.InvoiceFunctions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

/**
 * Performance tests for Wave 3 invoice open functions.
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
public class Wave3InvoiceOpenPerformanceTest extends CommonGWSetup {

    private static final double MAX_LATENCY_RATIO = 1.30;
    private static final int WARMUP_ITERATIONS = 100;
    private static final int TEST_ITERATIONS = 500;
    private static final int MEASUREMENT_ROUNDS = 5;

    private List<MInvoice> testInvoices;
    private MInvoice invoiceWithSchedule;
    private int scheduleId;
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

        // Find an invoice with payment schedule for schedule-specific tests
        for (MInvoice inv : testInvoices) {
            MInvoicePaySchedule[] schedules = MInvoicePaySchedule.getInvoicePaySchedule(
                Env.getCtx(), inv.getC_Invoice_ID(), 0, null);
            if (schedules.length > 0) {
                invoiceWithSchedule = inv;
                scheduleId = schedules[0].getC_InvoicePaySchedule_ID();
                break;
            }
        }
        assumeTrue(invoiceWithSchedule != null, "Need at least one invoice with payment schedule");
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testInvoiceOpenPerformance(RepetitionInfo info) {
        if (info.getCurrentRepetition() == 1) {
            // Warmup
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                MInvoice inv = testInvoices.get(i % testInvoices.size());
                InvoiceFunctions.invoiceOpen(inv.getC_Invoice_ID(), null);
                SqlFunctionCaller.callInvoiceOpen(inv.getC_Invoice_ID(), null);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MInvoice inv = testInvoices.get(i % testInvoices.size());
            InvoiceFunctions.invoiceOpen(inv.getC_Invoice_ID(), null);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MInvoice inv = testInvoices.get(i % testInvoices.size());
            SqlFunctionCaller.callInvoiceOpen(inv.getC_Invoice_ID(), null);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("invoiceOpen Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, Arrays.toString(ratios)));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testInvoiceOpenWithSchedulePerformance(RepetitionInfo info) {
        if (info.getCurrentRepetition() == 1) {
            // Warmup
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                InvoiceFunctions.invoiceOpen(invoiceWithSchedule.getC_Invoice_ID(), scheduleId);
                SqlFunctionCaller.callInvoiceOpen(invoiceWithSchedule.getC_Invoice_ID(), scheduleId);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            InvoiceFunctions.invoiceOpen(invoiceWithSchedule.getC_Invoice_ID(), scheduleId);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callInvoiceOpen(invoiceWithSchedule.getC_Invoice_ID(), scheduleId);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("invoiceOpen (with schedule) Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, Arrays.toString(ratios)));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testInvoiceOpenToDatePerformance(RepetitionInfo info) {
        Timestamp dateAcct = new Timestamp(System.currentTimeMillis());

        if (info.getCurrentRepetition() == 1) {
            // Warmup
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                MInvoice inv = testInvoices.get(i % testInvoices.size());
                InvoiceFunctions.invoiceOpenToDate(inv.getC_Invoice_ID(), null, dateAcct);
                SqlFunctionCaller.callInvoiceOpenToDate(inv.getC_Invoice_ID(), null, dateAcct);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MInvoice inv = testInvoices.get(i % testInvoices.size());
            InvoiceFunctions.invoiceOpenToDate(inv.getC_Invoice_ID(), null, dateAcct);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MInvoice inv = testInvoices.get(i % testInvoices.size());
            SqlFunctionCaller.callInvoiceOpenToDate(inv.getC_Invoice_ID(), null, dateAcct);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("invoiceOpenToDate Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, Arrays.toString(ratios)));
        }
    }
}
