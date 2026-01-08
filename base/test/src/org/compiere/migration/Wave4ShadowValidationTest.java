package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.DB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * Tests verifying shadow validation behavior for Wave 4 functions.
 *
 * <p>These tests validate that the shadow execution infrastructure correctly:
 * <ul>
 *   <li>Logs comparison results in SHADOW mode</li>
 *   <li>Skips logging in SQL_ONLY and JAVA_ONLY modes</li>
 *   <li>Respects sample rate configuration</li>
 * </ul>
 *
 * <p><b>Infrastructure Requirements:</b>
 * <ul>
 *   <li>migration.function_config table with mode, sample_rate columns</li>
 *   <li>migration.function_log table for logging</li>
 *   <li>MigrationConfig.get(functionName) returns current config</li>
 * </ul>
 *
 * @see ShadowExecutor
 * @see MigrationConfig
 * @see MigrationLogger
 */
@Tag("IntegrationTest")
public class Wave4ShadowValidationTest extends CommonGWSetup {

    private static final String TEST_FUNCTION = "acctBalance";
    private String originalMode;
    private double originalSampleRate;
    private boolean originalCircuitBreaker;
    private boolean configExisted;

    @BeforeEach
    void saveOriginalConfig() {
        // Save original config for restoration after test
        originalMode = DB.getSQLValueString(null,
            "SELECT mode FROM migration.function_config WHERE function_name = ?",
            TEST_FUNCTION);

        if (originalMode == null) {
            configExisted = false;
            // Insert config if doesn't exist
            DB.executeUpdate(
                "INSERT INTO migration.function_config (function_name, mode, sample_rate, circuit_breaker_enabled, created, updated) " +
                "VALUES (?, 'SQL_ONLY', 1.0, true, NOW(), NOW()) ON CONFLICT (function_name) DO NOTHING",
                new Object[]{TEST_FUNCTION}, false, null);
            originalMode = "SQL_ONLY";
            originalSampleRate = 1.0;
            originalCircuitBreaker = true;
        } else {
            configExisted = true;
            originalSampleRate = DB.getSQLValueBD(null,
                "SELECT sample_rate FROM migration.function_config WHERE function_name = ?",
                TEST_FUNCTION).doubleValue();
            originalCircuitBreaker = "Y".equals(DB.getSQLValueString(null,
                "SELECT CASE WHEN circuit_breaker_enabled THEN 'Y' ELSE 'N' END FROM migration.function_config WHERE function_name = ?",
                TEST_FUNCTION));
        }
    }

    @AfterEach
    void restoreOriginalConfig() {
        // Restore original config after each test
        if (configExisted) {
            DB.executeUpdate(
                "UPDATE migration.function_config SET mode = ?, sample_rate = ?, circuit_breaker_enabled = ?, updated = NOW() " +
                "WHERE function_name = ?",
                new Object[]{originalMode, originalSampleRate, originalCircuitBreaker, TEST_FUNCTION}, false, null);
        } else {
            // Delete if we created it
            DB.executeUpdate(
                "DELETE FROM migration.function_config WHERE function_name = ?",
                new Object[]{TEST_FUNCTION}, false, null);
        }

        // Clear any test log entries (recent ones only)
        DB.executeUpdate(
            "DELETE FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '5 minutes'",
            new Object[]{TEST_FUNCTION}, false, null);
    }

    /**
     * Wait for MigrationLogger to flush its async queue.
     * The logger uses a background thread that polls every 100ms.
     */
    private void waitForLoggerFlush() throws InterruptedException {
        // Give the async logger time to flush
        long deadline = System.currentTimeMillis() + 2000; // 2 second timeout
        while (MigrationLogger.getQueueDepth() > 0 && System.currentTimeMillis() < deadline) {
            Thread.sleep(50);
        }
        // Extra time for database write to complete
        Thread.sleep(200);
    }

    @Test
    void router_executesInShadowMode() throws InterruptedException {
        // Set mode to SHADOW
        DB.executeUpdate(
            "UPDATE migration.function_config SET mode = 'SHADOW', sample_rate = 1.0, updated = NOW() WHERE function_name = ?",
            new Object[]{TEST_FUNCTION}, false, null);

        // Clear recent logs
        DB.executeUpdate(
            "DELETE FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '1 minute'",
            new Object[]{TEST_FUNCTION}, false, null);

        // Call router - should execute both Java and SQL, then log comparison
        BigDecimal result = Wave4FunctionRouter.acctBalance(
            1, // arbitrary account ID
            new BigDecimal("100.00"),
            new BigDecimal("30.00"));

        // Verify result is returned (not null or error)
        assertNotNull(result, "Router should return a result in SHADOW mode");

        // Wait for async logging to complete
        waitForLoggerFlush();

        // Verify logging occurred
        int logCount = DB.getSQLValue(null,
            "SELECT COUNT(*) FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '1 minute'",
            TEST_FUNCTION);

        assertTrue(logCount > 0, "Shadow execution should log to migration.function_log");
    }

    @Test
    void router_logsMatchStatus() throws InterruptedException {
        // Set mode to SHADOW with 100% sample rate
        DB.executeUpdate(
            "UPDATE migration.function_config SET mode = 'SHADOW', sample_rate = 1.0, updated = NOW() WHERE function_name = ?",
            new Object[]{TEST_FUNCTION}, false, null);

        // Clear recent logs
        DB.executeUpdate(
            "DELETE FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '1 minute'",
            new Object[]{TEST_FUNCTION}, false, null);

        // Call router with inputs that should produce matching results
        Wave4FunctionRouter.acctBalance(null, BigDecimal.TEN, BigDecimal.ONE);

        // Wait for async logging to complete
        waitForLoggerFlush();

        // Verify log entry has is_match field populated
        String isMatch = DB.getSQLValueString(null,
            "SELECT CASE WHEN is_match THEN 'true' ELSE 'false' END FROM migration.function_log " +
            "WHERE function_name = ? ORDER BY created_at DESC LIMIT 1",
            TEST_FUNCTION);

        assertNotNull(isMatch, "Log entry should have is_match status");
        assertTrue("true".equals(isMatch) || "false".equals(isMatch),
            "is_match should be true or false, got: " + isMatch);
    }

    @Test
    void router_sqlOnlyMode_skipsLogging() throws InterruptedException {
        // Set mode to SQL_ONLY
        DB.executeUpdate(
            "UPDATE migration.function_config SET mode = 'SQL_ONLY', updated = NOW() WHERE function_name = ?",
            new Object[]{TEST_FUNCTION}, false, null);

        // Clear recent logs
        DB.executeUpdate(
            "DELETE FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '1 minute'",
            new Object[]{TEST_FUNCTION}, false, null);

        // Call router
        Wave4FunctionRouter.acctBalance(1, BigDecimal.TEN, BigDecimal.ONE);

        // Wait to ensure any potential logging would have completed
        waitForLoggerFlush();

        // Verify NO logging occurred (SQL_ONLY mode doesn't log)
        int logCount = DB.getSQLValue(null,
            "SELECT COUNT(*) FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '1 minute'",
            TEST_FUNCTION);

        assertEquals(0, logCount, "SQL_ONLY mode should not log to function_log");
    }

    @Test
    void router_javaOnlyMode_skipsLogging() throws InterruptedException {
        // Set mode to JAVA_ONLY
        DB.executeUpdate(
            "UPDATE migration.function_config SET mode = 'JAVA_ONLY', updated = NOW() WHERE function_name = ?",
            new Object[]{TEST_FUNCTION}, false, null);

        // Clear recent logs
        DB.executeUpdate(
            "DELETE FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '1 minute'",
            new Object[]{TEST_FUNCTION}, false, null);

        // Call router
        Wave4FunctionRouter.acctBalance(1, BigDecimal.TEN, BigDecimal.ONE);

        // Wait to ensure any potential logging would have completed
        waitForLoggerFlush();

        // Verify NO logging occurred (JAVA_ONLY mode doesn't log)
        int logCount = DB.getSQLValue(null,
            "SELECT COUNT(*) FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '1 minute'",
            TEST_FUNCTION);

        assertEquals(0, logCount, "JAVA_ONLY mode should not log to function_log");
    }

    @Test
    void router_respectsSampleRate() throws InterruptedException {
        // Set mode to SHADOW with 50% sample rate
        DB.executeUpdate(
            "UPDATE migration.function_config SET mode = 'SHADOW', sample_rate = 0.5, updated = NOW() WHERE function_name = ?",
            new Object[]{TEST_FUNCTION}, false, null);

        // Clear recent logs
        DB.executeUpdate(
            "DELETE FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '5 minutes'",
            new Object[]{TEST_FUNCTION}, false, null);

        // Call router many times
        int callCount = 100;
        for (int i = 0; i < callCount; i++) {
            Wave4FunctionRouter.acctBalance(1, BigDecimal.TEN, BigDecimal.ONE);
        }

        // Wait for async logging to complete
        waitForLoggerFlush();

        // Verify approximately 50% were logged (allow 20-80% range for randomness)
        int logCount = DB.getSQLValue(null,
            "SELECT COUNT(*) FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '5 minutes'",
            TEST_FUNCTION);

        // With 100 calls at 50% rate, expect 20-80 logs (allowing for statistical variance)
        assertTrue(logCount >= 20 && logCount <= 80,
            String.format("Sample rate 0.5 should log ~50%% of %d calls, got %d logs (expected 20-80)",
                callCount, logCount));
    }

    @Test
    void router_zeroSampleRate_noLogging() throws InterruptedException {
        // Set mode to SHADOW with 0% sample rate
        DB.executeUpdate(
            "UPDATE migration.function_config SET mode = 'SHADOW', sample_rate = 0.0, updated = NOW() WHERE function_name = ?",
            new Object[]{TEST_FUNCTION}, false, null);

        // Clear recent logs
        DB.executeUpdate(
            "DELETE FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '1 minute'",
            new Object[]{TEST_FUNCTION}, false, null);

        // Call router multiple times
        for (int i = 0; i < 10; i++) {
            Wave4FunctionRouter.acctBalance(1, BigDecimal.TEN, BigDecimal.ONE);
        }

        // Wait to ensure any potential logging would have completed
        waitForLoggerFlush();

        // Verify NO logging occurred (0% sample rate)
        int logCount = DB.getSQLValue(null,
            "SELECT COUNT(*) FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '1 minute'",
            TEST_FUNCTION);

        assertEquals(0, logCount, "0% sample rate should not log any calls");
    }

    @Test
    void router_fullSampleRate_logsAll() throws InterruptedException {
        // Set mode to SHADOW with 100% sample rate
        DB.executeUpdate(
            "UPDATE migration.function_config SET mode = 'SHADOW', sample_rate = 1.0, updated = NOW() WHERE function_name = ?",
            new Object[]{TEST_FUNCTION}, false, null);

        // Clear recent logs
        DB.executeUpdate(
            "DELETE FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '1 minute'",
            new Object[]{TEST_FUNCTION}, false, null);

        // Call router multiple times
        int callCount = 5;
        for (int i = 0; i < callCount; i++) {
            Wave4FunctionRouter.acctBalance(1, BigDecimal.TEN, BigDecimal.ONE);
        }

        // Wait for async logging to complete
        waitForLoggerFlush();

        // Verify ALL calls were logged (100% sample rate)
        int logCount = DB.getSQLValue(null,
            "SELECT COUNT(*) FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '1 minute'",
            TEST_FUNCTION);

        assertEquals(callCount, logCount, "100% sample rate should log all calls");
    }
}
