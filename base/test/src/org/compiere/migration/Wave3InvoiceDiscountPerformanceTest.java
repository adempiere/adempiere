package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;
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
 * Performance tests for Wave 3 invoice discount function.
 * Validates Java implementation meets latency requirements (<=130% of SQL).
 */
@Tag("PerformanceTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave3InvoiceDiscountPerformanceTest extends CommonGWSetup {

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
        testInvoices = new Query(Env.getCtx(), MInvoice.Table_Name,
            "DocStatus IN ('CO','CL')", null)
            .setOnlyActiveRecords(true)
            .setLimit(50)
            .list();
        assumeTrue(testInvoices.size() >= 10, "Need at least 10 invoices for performance test");

        // Find invoice with payment schedule
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
    void testInvoiceDiscountPerformance(RepetitionInfo info) {
        Timestamp payDate = new Timestamp(System.currentTimeMillis());

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                MInvoice inv = testInvoices.get(i % testInvoices.size());
                InvoiceFunctions.invoiceDiscount(inv.getC_Invoice_ID(), payDate, null);
                SqlFunctionCaller.callInvoiceDiscount(inv.getC_Invoice_ID(), payDate, null);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MInvoice inv = testInvoices.get(i % testInvoices.size());
            InvoiceFunctions.invoiceDiscount(inv.getC_Invoice_ID(), payDate, null);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MInvoice inv = testInvoices.get(i % testInvoices.size());
            SqlFunctionCaller.callInvoiceDiscount(inv.getC_Invoice_ID(), payDate, null);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("invoiceDiscount Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, Arrays.toString(ratios)));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testInvoiceDiscountWithSchedulePerformance(RepetitionInfo info) {
        Timestamp payDate = new Timestamp(System.currentTimeMillis());

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                InvoiceFunctions.invoiceDiscount(invoiceWithSchedule.getC_Invoice_ID(), payDate, scheduleId);
                SqlFunctionCaller.callInvoiceDiscount(invoiceWithSchedule.getC_Invoice_ID(), payDate, scheduleId);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            InvoiceFunctions.invoiceDiscount(invoiceWithSchedule.getC_Invoice_ID(), payDate, scheduleId);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callInvoiceDiscount(invoiceWithSchedule.getC_Invoice_ID(), payDate, scheduleId);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("invoiceDiscount (with schedule) Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, Arrays.toString(ratios)));
        }
    }
}
