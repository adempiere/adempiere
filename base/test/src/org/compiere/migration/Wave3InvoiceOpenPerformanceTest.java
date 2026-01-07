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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

/**
 * Performance tests for Wave 3 invoice open functions.
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
public class Wave3InvoiceOpenPerformanceTest extends CommonGWSetup {

    /** Max ratio when per-call overhead is < MAX_OVERHEAD_MS */
    private static final double RELAXED_RATIO = 3.0;
    /** Max ratio when per-call overhead is >= MAX_OVERHEAD_MS */
    private static final double STRICT_RATIO = 1.5;
    /** Threshold in ms: if overhead below this, use RELAXED_RATIO */
    private static final double MAX_OVERHEAD_MS = 1.0;
    private static final int WARMUP_ITERATIONS = 100;
    private static final int TEST_ITERATIONS = 500;
    private static final int MEASUREMENT_ROUNDS = 5;

    private List<MInvoice> testInvoices;
    private MInvoice invoiceWithSchedule;
    private int scheduleId;
    private static final ThreadLocal<double[]> ratioAccumulator = ThreadLocal.withInitial(() -> new double[MEASUREMENT_ROUNDS]);
    private static final ThreadLocal<long[]> javaTimeAccumulator = ThreadLocal.withInitial(() -> new long[MEASUREMENT_ROUNDS]);
    private static final ThreadLocal<long[]> sqlTimeAccumulator = ThreadLocal.withInitial(() -> new long[MEASUREMENT_ROUNDS]);
    private MigrationMode savedOpenMode;
    private MigrationMode savedOpenToDateMode;

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
        // Load multiple invoices for realistic testing
        testInvoices = new Query(Env.getCtx(), MInvoice.Table_Name,
            "DocStatus IN ('CO','CL')", null)
            .setOnlyActiveRecords(true)
            .setLimit(50)
            .list();
        assumeTrue(testInvoices.size() >= 3,
            String.format("SKIPPED: Need at least 3 completed invoices for performance test, found %d. " +
                "Performance tests require enough data for statistically meaningful results.", testInvoices.size()));

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
        assumeTrue(invoiceWithSchedule != null,
            "SKIPPED: No invoices with payment schedules found. Need invoice with IsPayScheduleValid='Y' to test scheduled invoice performance.");

        // Save current modes and switch to JAVA_ONLY for accurate performance measurement
        savedOpenMode = MigrationConfig.get("invoiceOpen").getMode();
        savedOpenToDateMode = MigrationConfig.get("invoiceOpenToDate").getMode();
        setModeInDatabase("invoiceOpen", MigrationMode.JAVA_ONLY);
        setModeInDatabase("invoiceOpenToDate", MigrationMode.JAVA_ONLY);
    }

    @AfterAll
    void restoreModes() {
        // Restore original modes
        setModeInDatabase("invoiceOpen", savedOpenMode);
        setModeInDatabase("invoiceOpenToDate", savedOpenToDateMode);
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
            System.out.println("│ PERFORMANCE: invoiceOpen                                                    │");
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
                String.format("invoiceOpen: SQL=%.2fms, Java=%.2fms, overhead=%.3fms, ratio=%.2f - %s",
                    medianSqlMs, medianJavaMs, overheadMs, medianRatio, rule));
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
            System.out.println("│ PERFORMANCE: invoiceOpen (with schedule)                                    │");
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
                String.format("invoiceOpen (with schedule): SQL=%.2fms, Java=%.2fms, overhead=%.3fms, ratio=%.2f - %s",
                    medianSqlMs, medianJavaMs, overheadMs, medianRatio, rule));
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
            System.out.println("│ PERFORMANCE: invoiceOpenToDate                                              │");
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
                String.format("invoiceOpenToDate: SQL=%.2fms, Java=%.2fms, overhead=%.3fms, ratio=%.2f - %s",
                    medianSqlMs, medianJavaMs, overheadMs, medianRatio, rule));
        }
    }
}
