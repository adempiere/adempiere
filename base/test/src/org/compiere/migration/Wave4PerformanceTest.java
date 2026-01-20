package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * Performance tests for Wave 4 standalone functions.
 * Establishes SQL baseline and validates Java implementations meet performance requirements.
 *
 * <p><b>Variable Threshold Approach:</b>
 * PASS if (overhead &lt; 1.0ms AND ratio &lt;= 3.0x) OR (ratio &lt;= 1.5x)
 * This accommodates fast functions where small absolute differences produce high ratios.
 *
 * <p><b>Functions Tested:</b>
 * <ul>
 *   <li>acctBalance</li>
 *   <li>productAttribute</li>
 *   <li>documentNo</li>
 *   <li>linenetamtrealinvoiceline</li>
 *   <li>linenetamtrealorderline</li>
 *   <li>maxpaydate</li>
 * </ul>
 *
 * <p><b>Excluded:</b> nextID/nextIDFunc (stateful, not suitable for performance comparison)
 *
 * <p><b>Test Data Requirements:</b>
 * <ul>
 *   <li>At least one active C_ElementValue for acctBalance tests</li>
 *   <li>At least one M_AttributeSetInstance with Lot/SerNo for productAttribute tests</li>
 *   <li>At least one PP_MRP record for documentNo tests</li>
 *   <li>At least one C_InvoiceLine for linenetamtrealinvoiceline tests</li>
 *   <li>At least one C_OrderLine for linenetamtrealorderline tests</li>
 *   <li>At least one C_Invoice with payments for maxpaydate tests</li>
 * </ul>
 *
 * <p><b>Parallelization:</b> This test uses instance fields for ratio accumulation
 * across repeated test runs. Must run in same thread to prevent race conditions.
 */
@Tag("PerformanceTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.SAME_THREAD)
public class Wave4PerformanceTest extends CommonGWSetup {

    private static final CLogger log = CLogger.getCLogger(Wave4PerformanceTest.class);

    // Variable threshold constants (from quality gates)
    private static final double RELAXED_RATIO = 3.0;
    private static final double STRICT_RATIO = 1.5;
    private static final double MAX_OVERHEAD_MS = 1.0;

    // Known issue: documentNo uses 6 LEFT JOINs (design trade-off for simpler code).
    // Accepted in Gate 2 as low-priority optimization opportunity.
    // Function is called infrequently (MRP reports/views only), overhead <1ms.
    // See: docs/plans/wave4-quality-gates.md "Known Issues" section
    private static final double DOCUMENTNO_ACCEPTED_RATIO = 4.0;

    // Test configuration
    private static final int WARMUP_ITERATIONS = 500;
    private static final int TEST_ITERATIONS = 500;
    private static final int MEASUREMENT_ROUNDS = 5;

    // Dynamically discovered test IDs
    private int[] testAccountIds;
    private int[] testAttributeSetInstanceIds;
    private int[] testMrpIds;
    private int[] testInvoiceLineIds;
    private int[] testOrderLineIds;
    private int[] testInvoiceIds;

    // Test parameters
    private BigDecimal testAmtDr;
    private BigDecimal testAmtCr;

    // Ratio accumulator for repeated tests
    private double[] ratioAccumulator;

    // Metrics for baseline reporting
    private long[] sqlTimesNs;
    private long[] javaTimesNs;

    @BeforeAll
    void loadTestData() {
        // Initialize test amounts
        testAmtDr = new BigDecimal("100.00");
        testAmtCr = new BigDecimal("30.00");

        // Dynamically discover valid test IDs from database
        testAccountIds = queryExistingIds(
            "SELECT C_ElementValue_ID FROM C_ElementValue WHERE IsActive='Y' FETCH FIRST 5 ROWS ONLY");

        testAttributeSetInstanceIds = queryExistingIds(
            "SELECT M_AttributeSetInstance_ID FROM M_AttributeSetInstance " +
            "WHERE (Lot IS NOT NULL OR SerNo IS NOT NULL) FETCH FIRST 5 ROWS ONLY");

        testMrpIds = queryExistingIds(
            "SELECT PP_MRP_ID FROM PP_MRP WHERE IsActive='Y' FETCH FIRST 5 ROWS ONLY");

        testInvoiceLineIds = queryExistingIds(
            "SELECT C_InvoiceLine_ID FROM C_InvoiceLine WHERE IsActive='Y' FETCH FIRST 5 ROWS ONLY");

        testOrderLineIds = queryExistingIds(
            "SELECT C_OrderLine_ID FROM C_OrderLine WHERE IsActive='Y' FETCH FIRST 5 ROWS ONLY");

        // Find invoices with allocations/payments for maxpaydate
        testInvoiceIds = queryExistingIds(
            "SELECT DISTINCT al.C_Invoice_ID FROM C_AllocationLine al " +
            "INNER JOIN C_AllocationHdr ah ON al.C_AllocationHdr_ID = ah.C_AllocationHdr_ID " +
            "WHERE al.C_Payment_ID IS NOT NULL AND ah.DocStatus <> 'RE' " +
            "FETCH FIRST 5 ROWS ONLY");
    }

    private int[] queryExistingIds(String sql) {
        List<Integer> ids = new ArrayList<>();
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to query test IDs: " + sql, e);
        }
        return ids.stream().mapToInt(Integer::intValue).toArray();
    }

    @BeforeEach
    void initAccumulator(RepetitionInfo info) {
        if (info.getCurrentRepetition() == 1) {
            ratioAccumulator = new double[MEASUREMENT_ROUNDS];
            sqlTimesNs = new long[MEASUREMENT_ROUNDS];
            javaTimesNs = new long[MEASUREMENT_ROUNDS];
        }
    }

    /**
     * Validate performance using variable threshold approach.
     * PASS if (overhead &lt; MAX_OVERHEAD_MS AND ratio &lt;= RELAXED_RATIO) OR (ratio &lt;= STRICT_RATIO)
     */
    private void validatePerformance(String functionName, RepetitionInfo info,
                                      long javaTimeNs, long sqlTimeNs) {
        double ratio = (sqlTimeNs > 0) ? (double) javaTimeNs / sqlTimeNs : Double.MAX_VALUE;
        ratioAccumulator[info.getCurrentRepetition() - 1] = ratio;
        sqlTimesNs[info.getCurrentRepetition() - 1] = sqlTimeNs;
        javaTimesNs[info.getCurrentRepetition() - 1] = javaTimeNs;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            // Calculate median ratio
            Arrays.sort(ratioAccumulator);
            double medianRatio = ratioAccumulator[MEASUREMENT_ROUNDS / 2];

            // Calculate average times for baseline metrics
            double sqlAvgMs = Arrays.stream(sqlTimesNs).average().orElse(0) / 1_000_000.0 / TEST_ITERATIONS;
            double javaAvgMs = Arrays.stream(javaTimesNs).average().orElse(0) / 1_000_000.0 / TEST_ITERATIONS;
            double overheadMs = javaAvgMs - sqlAvgMs;

            // Print baseline metrics
            System.out.printf("%n=== %s Performance Baseline ===%n", functionName);
            System.out.printf("SQL avg:      %.4f ms/call%n", sqlAvgMs);
            System.out.printf("Java avg:     %.4f ms/call%n", javaAvgMs);
            System.out.printf("Overhead:     %.4f ms%n", overheadMs);
            System.out.printf("Median ratio: %.2fx%n", medianRatio);
            System.out.printf("All ratios:   %s%n", Arrays.toString(ratioAccumulator));

            // Apply variable threshold
            boolean passStrict = medianRatio <= STRICT_RATIO;
            boolean passRelaxed = (overheadMs < MAX_OVERHEAD_MS) && (medianRatio <= RELAXED_RATIO);

            // Known issue: documentNo has accepted higher threshold (see Gate 2 Known Issues)
            boolean passDocumentNoException = "documentNo".equals(functionName)
                && (overheadMs < MAX_OVERHEAD_MS)
                && (medianRatio <= DOCUMENTNO_ACCEPTED_RATIO);

            boolean pass = passStrict || passRelaxed || passDocumentNoException;

            String thresholdUsed = passStrict ? "STRICT (<=1.5x)" :
                                   passRelaxed ? "RELAXED (<1ms overhead, <=3x)" :
                                   passDocumentNoException ? "ACCEPTED EXCEPTION (<1ms overhead, <=4x)" : "FAILED";
            System.out.printf("Threshold:    %s%n", thresholdUsed);
            System.out.printf("Status:       %s%n", pass ? "PASS" : "FAIL");

            assertTrue(pass,
                String.format("%s Java/SQL median ratio %.2fx with %.4fms overhead - " +
                    "must satisfy (ratio<=%.1f) OR (overhead<%.1fms AND ratio<=%.1f)" +
                    (functionName.equals("documentNo") ? " OR documentNo exception (overhead<%.1fms AND ratio<=%.1f)" : ""),
                    functionName, medianRatio, overheadMs,
                    STRICT_RATIO, MAX_OVERHEAD_MS, RELAXED_RATIO,
                    MAX_OVERHEAD_MS, DOCUMENTNO_ACCEPTED_RATIO));
        }
    }

    // ==================== acctBalance ====================

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testAcctBalancePerformance(RepetitionInfo info) {
        assumeTrue(testAccountIds != null && testAccountIds.length > 0,
            "No test accounts found - skipping acctBalance performance test");

        int accountId = testAccountIds[0];

        if (info.getCurrentRepetition() == 1) {
            // Warmup
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                Wave4Functions.acctBalance(accountId, testAmtDr, testAmtCr);
                SqlFunctionCaller.callAcctBalance(accountId, testAmtDr, testAmtCr);
            }
        }

        // Measure Java
        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Wave4Functions.acctBalance(accountId, testAmtDr, testAmtCr);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        // Measure SQL
        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callAcctBalance(accountId, testAmtDr, testAmtCr);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        validatePerformance("acctBalance", info, javaTimeNs, sqlTimeNs);
    }

    // ==================== productAttribute ====================

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testProductAttributePerformance(RepetitionInfo info) {
        assumeTrue(testAttributeSetInstanceIds != null && testAttributeSetInstanceIds.length > 0,
            "No test attribute set instances found - skipping productAttribute performance test");

        int asiId = testAttributeSetInstanceIds[0];

        if (info.getCurrentRepetition() == 1) {
            // Warmup
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                Wave4Functions.productAttribute(asiId);
                SqlFunctionCaller.callProductAttribute(asiId);
            }
        }

        // Measure Java
        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Wave4Functions.productAttribute(asiId);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        // Measure SQL
        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callProductAttribute(asiId);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        validatePerformance("productAttribute", info, javaTimeNs, sqlTimeNs);
    }

    // ==================== documentNo ====================

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testDocumentNoPerformance(RepetitionInfo info) {
        assumeTrue(testMrpIds != null && testMrpIds.length > 0,
            "No test MRP records found - skipping documentNo performance test");

        int mrpId = testMrpIds[0];

        if (info.getCurrentRepetition() == 1) {
            // Warmup
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                Wave4Functions.documentNo(mrpId);
                SqlFunctionCaller.callDocumentNo(mrpId);
            }
        }

        // Measure Java
        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Wave4Functions.documentNo(mrpId);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        // Measure SQL
        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callDocumentNo(mrpId);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        validatePerformance("documentNo", info, javaTimeNs, sqlTimeNs);
    }

    // ==================== linenetamtrealinvoiceline ====================

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testLinenetamtrealinvoicelinePerformance(RepetitionInfo info) {
        assumeTrue(testInvoiceLineIds != null && testInvoiceLineIds.length > 0,
            "No test invoice lines found - skipping linenetamtrealinvoiceline performance test");

        int invoiceLineId = testInvoiceLineIds[0];

        if (info.getCurrentRepetition() == 1) {
            // Warmup
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                Wave4Functions.linenetamtrealinvoiceline(invoiceLineId);
                SqlFunctionCaller.callLinenetamtrealinvoiceline(invoiceLineId);
            }
        }

        // Measure Java
        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Wave4Functions.linenetamtrealinvoiceline(invoiceLineId);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        // Measure SQL
        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callLinenetamtrealinvoiceline(invoiceLineId);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        validatePerformance("linenetamtrealinvoiceline", info, javaTimeNs, sqlTimeNs);
    }

    // ==================== linenetamtrealorderline ====================

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testLinenetamtrealorderlinePerformance(RepetitionInfo info) {
        assumeTrue(testOrderLineIds != null && testOrderLineIds.length > 0,
            "No test order lines found - skipping linenetamtrealorderline performance test");

        int orderLineId = testOrderLineIds[0];

        if (info.getCurrentRepetition() == 1) {
            // Warmup
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                Wave4Functions.linenetamtrealorderline(orderLineId);
                SqlFunctionCaller.callLinenetamtrealorderline(orderLineId);
            }
        }

        // Measure Java
        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Wave4Functions.linenetamtrealorderline(orderLineId);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        // Measure SQL
        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callLinenetamtrealorderline(orderLineId);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        validatePerformance("linenetamtrealorderline", info, javaTimeNs, sqlTimeNs);
    }

    // ==================== maxpaydate ====================

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testMaxpaydatePerformance(RepetitionInfo info) {
        assumeTrue(testInvoiceIds != null && testInvoiceIds.length > 0,
            "No test invoices with payments found - skipping maxpaydate performance test");

        int invoiceId = testInvoiceIds[0];

        if (info.getCurrentRepetition() == 1) {
            // Warmup
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                Wave4Functions.maxpaydate(invoiceId);
                SqlFunctionCaller.callMaxpaydate(invoiceId);
            }
        }

        // Measure Java
        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Wave4Functions.maxpaydate(invoiceId);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        // Measure SQL
        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callMaxpaydate(invoiceId);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        validatePerformance("maxpaydate", info, javaTimeNs, sqlTimeNs);
    }
}
