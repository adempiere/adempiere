package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Performance tests for PaymentTermFunctions.
 * Validates Java implementation is within performance budget.
 *
 * <p><b>LIMITATION:</b> These unit tests use clientId=0 or paymentTermId=0,
 * which triggers early-exit paths without database access. This measures
 * the overhead of null checks and basic date operations only.
 *
 * <p>For real performance measurement with database access (MPaymentTerm
 * caching, holiday queries), see integration tests run with
 * -DrunIntegrationTests=true against a test database.
 *
 * <p>Test dates use fixed 2026 values - these remain valid regardless of
 * when tests are run since they don't depend on LocalDate.now().
 */
@Tag("UnitTest")
class PaymentTermFunctionsPerformanceTest {

    private static final int WARMUP_ITERATIONS = 500;
    private static final int TEST_ITERATIONS = 2000;

    @Test
    void addMonths_performanceWithinBudget() {
        Timestamp input = Timestamp.valueOf("2026-01-15 10:00:00");

        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            PaymentTermFunctions.addMonths(input, i % 12);
        }

        // Measure
        long start = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            PaymentTermFunctions.addMonths(input, i % 12);
        }
        long elapsed = System.nanoTime() - start;

        double avgMicros = (elapsed / 1000.0) / TEST_ITERATIONS;
        System.out.printf("addMonths: %.2f us/call%n", avgMicros);

        // Should be sub-microsecond for pure date math
        assertTrue(avgMicros < 10.0, "addMonths should be < 10 us/call, was " + avgMicros);
    }

    @Test
    void nextBusinessDay_performanceWithinBudget_noDb() {
        Timestamp input = Timestamp.valueOf("2026-01-15 10:00:00");
        int clientId = 0; // Will skip DB lookup for clientId=0

        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            PaymentTermFunctions.nextBusinessDay(input, clientId);
        }

        // Measure
        long start = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            PaymentTermFunctions.nextBusinessDay(input, clientId);
        }
        long elapsed = System.nanoTime() - start;

        double avgMicros = (elapsed / 1000.0) / TEST_ITERATIONS;
        System.out.printf("nextBusinessDay (no DB): %.2f us/call%n", avgMicros);

        // Without DB lookup, should be fast
        assertTrue(avgMicros < 50.0, "nextBusinessDay should be < 50 us/call without DB, was " + avgMicros);
    }

    @Test
    void calculateFixedDueDate_performanceWithinBudget() {
        java.time.LocalDate docDate = java.time.LocalDate.of(2026, 1, 15);

        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            PaymentTermFunctions.calculateFixedDueDate(docDate, 15, 1, 20);
        }

        // Measure
        long start = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            PaymentTermFunctions.calculateFixedDueDate(docDate, 15, 1, 20);
        }
        long elapsed = System.nanoTime() - start;

        double avgMicros = (elapsed / 1000.0) / TEST_ITERATIONS;
        System.out.printf("calculateFixedDueDate: %.2f us/call%n", avgMicros);

        // Pure date math, should be fast
        assertTrue(avgMicros < 10.0, "calculateFixedDueDate should be < 10 us/call, was " + avgMicros);
    }

    @Test
    void paymentTermDueDate_performanceWithinBudget() {
        Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
        Integer paymentTermId = 0; // Invalid ID to avoid DB lookup in unit test

        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            PaymentTermFunctions.paymentTermDueDate(paymentTermId, docDate);
        }

        // Measure (null return path is still valid for perf)
        long start = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            PaymentTermFunctions.paymentTermDueDate(paymentTermId, docDate);
        }
        long elapsed = System.nanoTime() - start;

        double avgMicros = (elapsed / 1000.0) / TEST_ITERATIONS;
        System.out.printf("paymentTermDueDate (no DB): %.2f us/call%n", avgMicros);

        // Without DB lookup, should be fast
        assertTrue(avgMicros < 50.0, "paymentTermDueDate should be < 50 us/call without DB, was " + avgMicros);
    }

    @Test
    void paymentTermDueDays_performanceWithinBudget() {
        Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
        Timestamp payDate = Timestamp.valueOf("2026-02-15 00:00:00");
        int paymentTermId = 0; // Invalid ID

        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            PaymentTermFunctions.paymentTermDueDays(paymentTermId, docDate, payDate);
        }

        // Measure
        long start = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            PaymentTermFunctions.paymentTermDueDays(paymentTermId, docDate, payDate);
        }
        long elapsed = System.nanoTime() - start;

        double avgMicros = (elapsed / 1000.0) / TEST_ITERATIONS;
        System.out.printf("paymentTermDueDays (no DB): %.2f us/call%n", avgMicros);

        assertTrue(avgMicros < 50.0, "paymentTermDueDays should be < 50 us/call without DB, was " + avgMicros);
    }

    @Test
    void paymentTermDiscount_performanceWithinBudget() {
        BigDecimal amount = new BigDecimal("1000.00");
        Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
        Timestamp payDate = Timestamp.valueOf("2026-01-20 00:00:00");
        int paymentTermId = 0; // Invalid ID
        int currencyId = 100;

        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            PaymentTermFunctions.paymentTermDiscount(amount, currencyId, paymentTermId, docDate, payDate);
        }

        // Measure
        long start = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            PaymentTermFunctions.paymentTermDiscount(amount, currencyId, paymentTermId, docDate, payDate);
        }
        long elapsed = System.nanoTime() - start;

        double avgMicros = (elapsed / 1000.0) / TEST_ITERATIONS;
        System.out.printf("paymentTermDiscount (no DB): %.2f us/call%n", avgMicros);

        assertTrue(avgMicros < 50.0, "paymentTermDiscount should be < 50 us/call without DB, was " + avgMicros);
    }
}
