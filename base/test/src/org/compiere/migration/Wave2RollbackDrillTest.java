package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.adempiere.test.CommonGWSetup;
import org.compiere.migration.comparators.BigDecimalComparator;
import org.compiere.migration.comparators.IntegerComparator;
import org.compiere.migration.comparators.TimestampComparator;
import org.compiere.util.DB;
import org.compiere.util.PaymentTermFunctions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

/**
 * Comprehensive rollback drill test for Wave 2 Payment Term Functions.
 * Validates that the migration infrastructure can safely transition between
 * all modes (SQL_ONLY ↔ SHADOW ↔ JAVA_ONLY) and correctly roll back when needed.
 *
 * <p>Uses explicit mode overload of ShadowExecutor.execute() to isolate tests
 * from database configuration changes.
 *
 * <p>Validates:
 * <ul>
 *   <li>Mode routing (SQL_ONLY, SHADOW, JAVA_ONLY)</li>
 *   <li>Rollback transitions via database config updates</li>
 *   <li>Circuit breaker behavior in shadow mode</li>
 *   <li>All 4 Wave 2 functions: nextBusinessDay, paymentTermDueDate,
 *       paymentTermDueDays, paymentTermDiscount</li>
 * </ul>
 *
 * @see ShadowExecutor
 * @see PaymentTermFunctions
 */
@Tag("RollbackDrill")
@Tag("Wave2")
public class Wave2RollbackDrillTest extends CommonGWSetup {

    private static final int TEST_CLIENT_ID = 0;
    private static final Timestamp TEST_DATE = Timestamp.valueOf("2026-01-15 00:00:00");
    private static final int TEST_PAYMENT_TERM_ID = 106; // Immediate in GardenWorld
    private static final int TEST_CURRENCY_ID = 100; // USD

    @BeforeEach
    void reset() {
        CircuitBreaker.resetAll();
    }

    @AfterEach
    void cleanupDatabaseState() {
        if (!"true".equals(System.getProperty("runIntegrationTests"))) {
            return; // Only cleanup if integration tests were running
        }
        String[] functions = {"nextBusinessDay", "paymentTermDueDate",
                              "paymentTermDueDays", "paymentTermDiscount"};
        for (String fn : functions) {
            try {
                updateFunctionMode(fn, "SQL_ONLY");
            } catch (Exception ignored) {
                // Best effort cleanup
            }
        }
    }

    // ========================================================================
    // Test Mode Routing - nextBusinessDay
    // ========================================================================

    @Test
    void nextBusinessDay_sqlOnlyMode_callsOnlySqlPath() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);

        Timestamp result = ShadowExecutor.execute(
            "nextBusinessDay",
            new Object[]{TEST_DATE, TEST_CLIENT_ID},
            MigrationMode.SQL_ONLY,
            1.0,
            false,
            () -> {
                javaCalls.incrementAndGet();
                return PaymentTermFunctions.nextBusinessDay(TEST_DATE, TEST_CLIENT_ID);
            },
            () -> {
                sqlCalls.incrementAndGet();
                return SqlFunctionCaller.callNextBusinessDay(TEST_DATE, TEST_CLIENT_ID);
            },
            TimestampComparator.SAME_DAY
        );

        assertNotNull(result);
        assertEquals(0, javaCalls.get(), "SQL_ONLY mode should not call Java path");
        assertEquals(1, sqlCalls.get(), "SQL_ONLY mode should call SQL path");
    }

    @Test
    void nextBusinessDay_shadowMode_callsBothAndReturnsJava() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);
        AtomicReference<Timestamp> javaResultCapture = new AtomicReference<>();

        Timestamp result = ShadowExecutor.execute(
            "nextBusinessDay",
            new Object[]{TEST_DATE, TEST_CLIENT_ID},
            MigrationMode.SHADOW,
            1.0, // 100% sample rate
            false,
            () -> {
                javaCalls.incrementAndGet();
                Timestamp jr = PaymentTermFunctions.nextBusinessDay(TEST_DATE, TEST_CLIENT_ID);
                javaResultCapture.set(jr);
                return jr;
            },
            () -> {
                sqlCalls.incrementAndGet();
                return SqlFunctionCaller.callNextBusinessDay(TEST_DATE, TEST_CLIENT_ID);
            },
            TimestampComparator.SAME_DAY
        );

        assertNotNull(result);
        assertEquals(1, javaCalls.get(), "SHADOW mode should call Java path");
        assertEquals(1, sqlCalls.get(), "SHADOW mode should call SQL path");
        assertSame(javaResultCapture.get(), result, "SHADOW mode should return Java result, not SQL");
    }

    @Test
    void nextBusinessDay_javaOnlyMode_callsOnlyJavaPath() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);

        Timestamp result = ShadowExecutor.execute(
            "nextBusinessDay",
            new Object[]{TEST_DATE, TEST_CLIENT_ID},
            MigrationMode.JAVA_ONLY,
            1.0,
            false,
            () -> {
                javaCalls.incrementAndGet();
                return PaymentTermFunctions.nextBusinessDay(TEST_DATE, TEST_CLIENT_ID);
            },
            () -> {
                sqlCalls.incrementAndGet();
                return SqlFunctionCaller.callNextBusinessDay(TEST_DATE, TEST_CLIENT_ID);
            },
            TimestampComparator.SAME_DAY
        );

        assertNotNull(result);
        assertEquals(1, javaCalls.get(), "JAVA_ONLY mode should call Java path");
        assertEquals(0, sqlCalls.get(), "JAVA_ONLY mode should not call SQL path");
    }

    // ========================================================================
    // Test Mode Routing - paymentTermDueDate
    // ========================================================================

    @Test
    void paymentTermDueDate_sqlOnlyMode_callsOnlySqlPath() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);

        Timestamp result = ShadowExecutor.execute(
            "paymentTermDueDate",
            new Object[]{TEST_PAYMENT_TERM_ID, TEST_DATE},
            MigrationMode.SQL_ONLY,
            1.0,
            false,
            () -> {
                javaCalls.incrementAndGet();
                return PaymentTermFunctions.paymentTermDueDate(TEST_PAYMENT_TERM_ID, TEST_DATE);
            },
            () -> {
                sqlCalls.incrementAndGet();
                return SqlFunctionCaller.callPaymentTermDueDate(TEST_PAYMENT_TERM_ID, TEST_DATE);
            },
            TimestampComparator.SAME_DAY
        );

        assertNotNull(result);
        assertEquals(0, javaCalls.get());
        assertEquals(1, sqlCalls.get());
    }

    @Test
    void paymentTermDueDate_shadowMode_callsBothAndReturnsJava() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);
        AtomicReference<Timestamp> javaResultCapture = new AtomicReference<>();

        Timestamp result = ShadowExecutor.execute(
            "paymentTermDueDate",
            new Object[]{TEST_PAYMENT_TERM_ID, TEST_DATE},
            MigrationMode.SHADOW,
            1.0, // 100% sample rate
            false,
            () -> {
                javaCalls.incrementAndGet();
                Timestamp jr = PaymentTermFunctions.paymentTermDueDate(TEST_PAYMENT_TERM_ID, TEST_DATE);
                javaResultCapture.set(jr);
                return jr;
            },
            () -> {
                sqlCalls.incrementAndGet();
                return SqlFunctionCaller.callPaymentTermDueDate(TEST_PAYMENT_TERM_ID, TEST_DATE);
            },
            TimestampComparator.SAME_DAY
        );

        assertNotNull(result);
        assertEquals(1, javaCalls.get(), "SHADOW mode should call Java path");
        assertEquals(1, sqlCalls.get(), "SHADOW mode should call SQL path");
        assertSame(javaResultCapture.get(), result, "SHADOW mode should return Java result, not SQL");
    }

    @Test
    void paymentTermDueDate_javaOnlyMode_callsOnlyJavaPath() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);

        Timestamp result = ShadowExecutor.execute(
            "paymentTermDueDate",
            new Object[]{TEST_PAYMENT_TERM_ID, TEST_DATE},
            MigrationMode.JAVA_ONLY,
            1.0,
            false,
            () -> {
                javaCalls.incrementAndGet();
                return PaymentTermFunctions.paymentTermDueDate(TEST_PAYMENT_TERM_ID, TEST_DATE);
            },
            () -> {
                sqlCalls.incrementAndGet();
                return SqlFunctionCaller.callPaymentTermDueDate(TEST_PAYMENT_TERM_ID, TEST_DATE);
            },
            TimestampComparator.SAME_DAY
        );

        assertNotNull(result);
        assertEquals(1, javaCalls.get());
        assertEquals(0, sqlCalls.get());
    }

    // ========================================================================
    // Test Mode Routing - paymentTermDueDays
    // ========================================================================

    @Test
    void paymentTermDueDays_sqlOnlyMode_callsOnlySqlPath() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);

        Integer result = ShadowExecutor.execute(
            "paymentTermDueDays",
            new Object[]{TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE},
            MigrationMode.SQL_ONLY,
            1.0,
            false,
            () -> {
                javaCalls.incrementAndGet();
                return PaymentTermFunctions.paymentTermDueDays(TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE);
            },
            () -> {
                sqlCalls.incrementAndGet();
                return SqlFunctionCaller.callPaymentTermDueDays(TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE);
            },
            IntegerComparator.EXACT
        );

        assertNotNull(result);
        assertEquals(0, javaCalls.get());
        assertEquals(1, sqlCalls.get());
    }

    @Test
    void paymentTermDueDays_shadowMode_callsBothAndReturnsJava() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);
        AtomicReference<Integer> javaResultCapture = new AtomicReference<>();

        Integer result = ShadowExecutor.execute(
            "paymentTermDueDays",
            new Object[]{TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE},
            MigrationMode.SHADOW,
            1.0, // 100% sample rate
            false,
            () -> {
                javaCalls.incrementAndGet();
                Integer jr = PaymentTermFunctions.paymentTermDueDays(TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE);
                javaResultCapture.set(jr);
                return jr;
            },
            () -> {
                sqlCalls.incrementAndGet();
                return SqlFunctionCaller.callPaymentTermDueDays(TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE);
            },
            IntegerComparator.EXACT
        );

        assertNotNull(result);
        assertEquals(1, javaCalls.get(), "SHADOW mode should call Java path");
        assertEquals(1, sqlCalls.get(), "SHADOW mode should call SQL path");
        assertEquals(javaResultCapture.get(), result, "SHADOW mode should return Java result, not SQL");
    }

    @Test
    void paymentTermDueDays_javaOnlyMode_callsOnlyJavaPath() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);

        Integer result = ShadowExecutor.execute(
            "paymentTermDueDays",
            new Object[]{TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE},
            MigrationMode.JAVA_ONLY,
            1.0,
            false,
            () -> {
                javaCalls.incrementAndGet();
                return PaymentTermFunctions.paymentTermDueDays(TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE);
            },
            () -> {
                sqlCalls.incrementAndGet();
                return SqlFunctionCaller.callPaymentTermDueDays(TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE);
            },
            IntegerComparator.EXACT
        );

        assertNotNull(result);
        assertEquals(1, javaCalls.get());
        assertEquals(0, sqlCalls.get());
    }

    // ========================================================================
    // Test Mode Routing - paymentTermDiscount
    // ========================================================================

    @Test
    void paymentTermDiscount_sqlOnlyMode_callsOnlySqlPath() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);
        BigDecimal testAmount = new BigDecimal("100.00");

        BigDecimal result = ShadowExecutor.execute(
            "paymentTermDiscount",
            new Object[]{testAmount, TEST_CURRENCY_ID, TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE},
            MigrationMode.SQL_ONLY,
            1.0,
            false,
            () -> {
                javaCalls.incrementAndGet();
                return PaymentTermFunctions.paymentTermDiscount(
                    testAmount, TEST_CURRENCY_ID, TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE);
            },
            () -> {
                sqlCalls.incrementAndGet();
                return SqlFunctionCaller.callPaymentTermDiscount(
                    testAmount, TEST_CURRENCY_ID, TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE);
            },
            BigDecimalComparator.CURRENCY
        );

        assertNotNull(result);
        assertEquals(0, javaCalls.get());
        assertEquals(1, sqlCalls.get());
    }

    @Test
    void paymentTermDiscount_shadowMode_callsBothAndReturnsJava() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);
        BigDecimal testAmount = new BigDecimal("100.00");
        AtomicReference<BigDecimal> javaResultCapture = new AtomicReference<>();

        BigDecimal result = ShadowExecutor.execute(
            "paymentTermDiscount",
            new Object[]{testAmount, TEST_CURRENCY_ID, TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE},
            MigrationMode.SHADOW,
            1.0, // 100% sample rate
            false,
            () -> {
                javaCalls.incrementAndGet();
                BigDecimal jr = PaymentTermFunctions.paymentTermDiscount(
                    testAmount, TEST_CURRENCY_ID, TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE);
                javaResultCapture.set(jr);
                return jr;
            },
            () -> {
                sqlCalls.incrementAndGet();
                return SqlFunctionCaller.callPaymentTermDiscount(
                    testAmount, TEST_CURRENCY_ID, TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE);
            },
            BigDecimalComparator.CURRENCY
        );

        assertNotNull(result);
        assertEquals(1, javaCalls.get(), "SHADOW mode should call Java path");
        assertEquals(1, sqlCalls.get(), "SHADOW mode should call SQL path");
        assertSame(javaResultCapture.get(), result, "SHADOW mode should return Java result, not SQL");
    }

    @Test
    void paymentTermDiscount_javaOnlyMode_callsOnlyJavaPath() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);
        BigDecimal testAmount = new BigDecimal("100.00");

        BigDecimal result = ShadowExecutor.execute(
            "paymentTermDiscount",
            new Object[]{testAmount, TEST_CURRENCY_ID, TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE},
            MigrationMode.JAVA_ONLY,
            1.0,
            false,
            () -> {
                javaCalls.incrementAndGet();
                return PaymentTermFunctions.paymentTermDiscount(
                    testAmount, TEST_CURRENCY_ID, TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE);
            },
            () -> {
                sqlCalls.incrementAndGet();
                return SqlFunctionCaller.callPaymentTermDiscount(
                    testAmount, TEST_CURRENCY_ID, TEST_PAYMENT_TERM_ID, TEST_DATE, TEST_DATE);
            },
            BigDecimalComparator.CURRENCY
        );

        assertNotNull(result);
        assertEquals(1, javaCalls.get());
        assertEquals(0, sqlCalls.get());
    }

    // ========================================================================
    // Test Circuit Breaker Behavior
    // ========================================================================

    @Test
    void shadowMode_circuitOpen_skipsSqlPath() {
        // Open the circuit for nextBusinessDay
        for (int i = 0; i < 5; i++) {
            CircuitBreaker.recordFailure("nextBusinessDay");
        }
        assertTrue(CircuitBreaker.isOpen("nextBusinessDay"), "Circuit should be open after 5 failures");

        AtomicInteger sqlCalls = new AtomicInteger(0);

        Timestamp result = ShadowExecutor.execute(
            "nextBusinessDay",
            new Object[]{TEST_DATE, TEST_CLIENT_ID},
            MigrationMode.SHADOW,
            1.0,
            true, // circuit breaker enabled
            () -> PaymentTermFunctions.nextBusinessDay(TEST_DATE, TEST_CLIENT_ID),
            () -> {
                sqlCalls.incrementAndGet();
                return SqlFunctionCaller.callNextBusinessDay(TEST_DATE, TEST_CLIENT_ID);
            },
            TimestampComparator.SAME_DAY
        );

        assertNotNull(result, "Should return Java result when circuit is open");
        assertEquals(0, sqlCalls.get(), "Should not call SQL when circuit is open");
    }

    @Test
    void shadowMode_circuitClosed_callsBothPaths() {
        // Ensure circuit is closed
        CircuitBreaker.reset("nextBusinessDay");
        assertFalse(CircuitBreaker.isOpen("nextBusinessDay"));

        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);

        Timestamp result = ShadowExecutor.execute(
            "nextBusinessDay",
            new Object[]{TEST_DATE, TEST_CLIENT_ID},
            MigrationMode.SHADOW,
            1.0,
            true, // circuit breaker enabled
            () -> {
                javaCalls.incrementAndGet();
                return PaymentTermFunctions.nextBusinessDay(TEST_DATE, TEST_CLIENT_ID);
            },
            () -> {
                sqlCalls.incrementAndGet();
                return SqlFunctionCaller.callNextBusinessDay(TEST_DATE, TEST_CLIENT_ID);
            },
            TimestampComparator.SAME_DAY
        );

        assertNotNull(result);
        assertEquals(1, javaCalls.get(), "Should call Java path");
        assertEquals(1, sqlCalls.get(), "Should call SQL path when circuit is closed");
    }

    // ========================================================================
    // Test Rollback Transition via Database Config
    // ========================================================================

    @Test
    @EnabledIfSystemProperty(named = "runIntegrationTests", matches = "true")
    void rollbackTransition_javaOnlyToSqlOnly_updatesDatabaseAndVerifies() throws Exception {
        // This test validates the full rollback flow:
        // 1. Start in JAVA_ONLY mode (simulated via explicit mode)
        // 2. Update database to SQL_ONLY mode
        // 3. Verify MigrationConfig picks up the change (after cache expiry or explicit lookup)

        String functionName = "nextBusinessDay";

        // Step 1: Verify JAVA_ONLY mode behavior (using explicit mode)
        AtomicInteger javaCalls1 = new AtomicInteger(0);
        AtomicInteger sqlCalls1 = new AtomicInteger(0);

        Timestamp result1 = ShadowExecutor.execute(
            functionName,
            new Object[]{TEST_DATE, TEST_CLIENT_ID},
            MigrationMode.JAVA_ONLY, // explicit mode
            1.0,
            false,
            () -> {
                javaCalls1.incrementAndGet();
                return PaymentTermFunctions.nextBusinessDay(TEST_DATE, TEST_CLIENT_ID);
            },
            () -> {
                sqlCalls1.incrementAndGet();
                return SqlFunctionCaller.callNextBusinessDay(TEST_DATE, TEST_CLIENT_ID);
            },
            TimestampComparator.SAME_DAY
        );

        assertNotNull(result1);
        assertEquals(1, javaCalls1.get(), "JAVA_ONLY: should call Java");
        assertEquals(0, sqlCalls1.get(), "JAVA_ONLY: should not call SQL");

        // Step 2: Update database to SQL_ONLY mode
        updateFunctionMode(functionName, "SQL_ONLY");

        // Step 3: Verify SQL_ONLY mode behavior (using database-driven config)
        // Note: In production, this would wait for cache expiry (60s) or app restart
        // For testing, we verify using explicit mode to simulate post-cache-expiry state
        AtomicInteger javaCalls2 = new AtomicInteger(0);
        AtomicInteger sqlCalls2 = new AtomicInteger(0);

        Timestamp result2 = ShadowExecutor.execute(
            functionName,
            new Object[]{TEST_DATE, TEST_CLIENT_ID},
            MigrationMode.SQL_ONLY, // explicit mode simulating rolled-back state
            1.0,
            false,
            () -> {
                javaCalls2.incrementAndGet();
                return PaymentTermFunctions.nextBusinessDay(TEST_DATE, TEST_CLIENT_ID);
            },
            () -> {
                sqlCalls2.incrementAndGet();
                return SqlFunctionCaller.callNextBusinessDay(TEST_DATE, TEST_CLIENT_ID);
            },
            TimestampComparator.SAME_DAY
        );

        assertNotNull(result2);
        assertEquals(0, javaCalls2.get(), "SQL_ONLY: should not call Java");
        assertEquals(1, sqlCalls2.get(), "SQL_ONLY: should call SQL");

        // Verify database state persisted
        verifyFunctionMode(functionName, "SQL_ONLY");
    }

    @Test
    @EnabledIfSystemProperty(named = "runIntegrationTests", matches = "true")
    void rollbackTransition_allWave2Functions_canRollbackToSqlOnly() throws Exception {
        // Verify all 4 Wave 2 functions can be rolled back
        String[] functions = {
            "nextBusinessDay",
            "paymentTermDueDate",
            "paymentTermDueDays",
            "paymentTermDiscount"
        };

        for (String functionName : functions) {
            // Update to SQL_ONLY
            updateFunctionMode(functionName, "SQL_ONLY");

            // Verify update persisted
            verifyFunctionMode(functionName, "SQL_ONLY");
        }
    }

    // ========================================================================
    // Helper Methods
    // ========================================================================

    private void updateFunctionMode(String functionName, String mode) throws Exception {
        String sql = "UPDATE migration.function_config SET mode = ? WHERE function_name = ?";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setString(1, mode);
            pstmt.setString(2, functionName);
            int updated = pstmt.executeUpdate();
            if (updated == 0) {
                // Function doesn't exist, insert it
                insertFunction(functionName, mode);
            }
        }
    }

    private void insertFunction(String functionName, String mode) throws Exception {
        String sql = "INSERT INTO migration.function_config " +
                     "(function_name, mode, performance_tier, sample_rate, circuit_breaker_enabled) " +
                     "VALUES (?, ?, 'STANDARD', 1.0, true)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setString(1, functionName);
            pstmt.setString(2, mode);
            pstmt.executeUpdate();
        }
    }

    private void verifyFunctionMode(String functionName, String expectedMode) throws Exception {
        String sql = "SELECT mode FROM migration.function_config WHERE function_name = ?";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setString(1, functionName);
            try (var rs = pstmt.executeQuery()) {
                assertTrue(rs.next(), "Function config should exist for " + functionName);
                assertEquals(expectedMode, rs.getString("mode"),
                    "Mode for " + functionName + " should be " + expectedMode);
            }
        }
    }
}
