package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.DB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests verifying that Wave5FunctionRouter correctly logs to migration.function_log in SHADOW mode.
 *
 * <p><b>Purpose:</b> Ensures that calls through Wave5FunctionRouter trigger
 * ShadowExecutor which logs comparison results to the database.
 *
 * <p><b>Problem Fixed:</b> Tests were calling Wave5Functions directly instead of
 * through Wave5FunctionRouter, so no shadow logging occurred.
 *
 * <p><b>Infrastructure Requirements:</b>
 * <ul>
 *   <li>migration.function_config table with mode, sample_rate columns</li>
 *   <li>migration.function_log table for logging</li>
 *   <li>Wave5 BOM SQL functions deployed (bomPriceLimit, bomQtyOnHand, etc.)</li>
 * </ul>
 *
 * @see Wave5FunctionRouter
 * @see ShadowExecutor
 * @see MigrationLogger
 */
@Tag("IntegrationTest")
public class Wave5ShadowLoggingTest extends CommonGWSetup {

    // Wave 5 BOM functions to test
    private static final String[] WAVE5_FUNCTIONS = {
        "bomPriceLimit", "bomPriceList", "bomPriceStd",
        "bomQtyOnHand", "bomQtyReserved", "bomQtyOrdered", "bomQtyAvailable"
    };

    private List<FunctionConfig> originalConfigs = new ArrayList<>();

    private record FunctionConfig(String name, String mode, BigDecimal sampleRate, boolean circuitBreaker, boolean existed) {}

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();

        // Save original configs for all Wave5 functions
        for (String fn : WAVE5_FUNCTIONS) {
            String mode = DB.getSQLValueString(null,
                "SELECT mode FROM migration.function_config WHERE function_name = ?", fn);

            if (mode == null) {
                // Insert config if doesn't exist
                DB.executeUpdate(
                    "INSERT INTO migration.function_config (function_name, mode, sample_rate, circuit_breaker_enabled, created_at, updated_at) " +
                    "VALUES (?, 'SQL_ONLY', 1.0, true, NOW(), NOW()) ON CONFLICT (function_name) DO NOTHING",
                    new Object[]{fn}, false, null);
                originalConfigs.add(new FunctionConfig(fn, "SQL_ONLY", BigDecimal.ONE, true, false));
            } else {
                BigDecimal sampleRate = DB.getSQLValueBD(null,
                    "SELECT sample_rate FROM migration.function_config WHERE function_name = ?", fn);
                boolean cb = "Y".equals(DB.getSQLValueString(null,
                    "SELECT CASE WHEN circuit_breaker_enabled THEN 'Y' ELSE 'N' END FROM migration.function_config WHERE function_name = ?", fn));
                originalConfigs.add(new FunctionConfig(fn, mode, sampleRate, cb, true));
            }
        }

        // Clear config cache
        MigrationConfig.clearCache();
    }

    @AfterEach
    void restoreOriginalConfigs() {
        for (FunctionConfig cfg : originalConfigs) {
            if (cfg.existed()) {
                DB.executeUpdate(
                    "UPDATE migration.function_config SET mode = ?, sample_rate = ?, circuit_breaker_enabled = ?::boolean, updated_at = NOW() " +
                    "WHERE function_name = ?",
                    new Object[]{cfg.mode(), cfg.sampleRate(), cfg.circuitBreaker(), cfg.name()}, false, null);
            } else {
                DB.executeUpdate(
                    "DELETE FROM migration.function_config WHERE function_name = ?",
                    new Object[]{cfg.name()}, false, null);
            }
            MigrationConfig.invalidateCache(cfg.name());
        }
        originalConfigs.clear();

        // Clean up test log entries
        for (String fn : WAVE5_FUNCTIONS) {
            DB.executeUpdate(
                "DELETE FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '10 minutes'",
                new Object[]{fn}, false, null);
        }
    }

    /**
     * Wait for MigrationLogger to flush its async queue.
     * The logger uses a background thread that polls every 100ms.
     */
    private void waitForLoggerFlush() throws InterruptedException {
        long deadline = System.currentTimeMillis() + 2000; // 2 second timeout
        while (MigrationLogger.getQueueDepth() > 0 && System.currentTimeMillis() < deadline) {
            Thread.sleep(50);
        }
        // Extra time for database write to complete
        Thread.sleep(200);
    }

    private void setShadowMode(String functionName) {
        DB.executeUpdate(
            "UPDATE migration.function_config SET mode = 'SHADOW', sample_rate = 1.0, updated_at = NOW() WHERE function_name = ?",
            new Object[]{functionName}, false, null);
        MigrationConfig.invalidateCache(functionName);
    }

    private void clearRecentLogs(String functionName) {
        DB.executeUpdate(
            "DELETE FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '1 minute'",
            new Object[]{functionName}, false, null);
    }

    private int getRecentLogCount(String functionName) {
        return DB.getSQLValue(null,
            "SELECT COUNT(*) FROM migration.function_log WHERE function_name = ? AND created_at > NOW() - INTERVAL '1 minute'",
            functionName);
    }

    private Integer getProductWithBOM() {
        return DB.getSQLValue(null,
            "SELECT b.M_Product_ID FROM PP_Product_BOM b " +
            "JOIN PP_Product_BOMLine bl ON b.PP_Product_BOM_ID = bl.PP_Product_BOM_ID " +
            "WHERE b.IsActive = 'Y' AND bl.IsActive = 'Y' LIMIT 1");
    }

    private Integer getAnyPriceListVersion() {
        return DB.getSQLValue(null,
            "SELECT M_PriceList_Version_ID FROM M_PriceList_Version WHERE IsActive='Y' LIMIT 1");
    }

    private Integer getAnyWarehouse() {
        return DB.getSQLValue(null,
            "SELECT M_Warehouse_ID FROM M_Warehouse WHERE IsActive='Y' LIMIT 1");
    }

    // ==================== Router Logging Tests ====================

    @Test
    void bomPriceLimit_routerLogsInShadowMode() throws InterruptedException {
        Integer productId = getProductWithBOM();
        Integer plvId = getAnyPriceListVersion();
        assumeTrue(productId != null && productId > 0, "No product with BOM available");
        assumeTrue(plvId != null && plvId > 0, "No price list version available");

        String fn = "bomPriceLimit";
        setShadowMode(fn);
        clearRecentLogs(fn);

        // Call through ROUTER (not Wave5Functions directly!)
        BigDecimal result = Wave5FunctionRouter.bomPriceLimit(productId, plvId);
        assertNotNull(result, "Router should return a result");

        waitForLoggerFlush();

        int logCount = getRecentLogCount(fn);
        assertTrue(logCount > 0,
            "SHADOW mode call through Wave5FunctionRouter should log to migration.function_log");
    }

    @Test
    void bomPriceList_routerLogsInShadowMode() throws InterruptedException {
        Integer productId = getProductWithBOM();
        Integer plvId = getAnyPriceListVersion();
        assumeTrue(productId != null && productId > 0, "No product with BOM available");
        assumeTrue(plvId != null && plvId > 0, "No price list version available");

        String fn = "bomPriceList";
        setShadowMode(fn);
        clearRecentLogs(fn);

        BigDecimal result = Wave5FunctionRouter.bomPriceList(productId, plvId);
        assertNotNull(result, "Router should return a result");

        waitForLoggerFlush();

        int logCount = getRecentLogCount(fn);
        assertTrue(logCount > 0,
            "SHADOW mode call through Wave5FunctionRouter should log to migration.function_log");
    }

    @Test
    void bomPriceStd_routerLogsInShadowMode() throws InterruptedException {
        Integer productId = getProductWithBOM();
        Integer plvId = getAnyPriceListVersion();
        assumeTrue(productId != null && productId > 0, "No product with BOM available");
        assumeTrue(plvId != null && plvId > 0, "No price list version available");

        String fn = "bomPriceStd";
        setShadowMode(fn);
        clearRecentLogs(fn);

        BigDecimal result = Wave5FunctionRouter.bomPriceStd(productId, plvId);
        assertNotNull(result, "Router should return a result");

        waitForLoggerFlush();

        int logCount = getRecentLogCount(fn);
        assertTrue(logCount > 0,
            "SHADOW mode call through Wave5FunctionRouter should log to migration.function_log");
    }

    @Test
    void bomQtyOnHand_routerLogsInShadowMode() throws InterruptedException {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();
        assumeTrue(productId != null && productId > 0, "No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0, "No warehouse available");

        String fn = "bomQtyOnHand";
        setShadowMode(fn);
        clearRecentLogs(fn);

        BigDecimal result = Wave5FunctionRouter.bomQtyOnHand(productId, warehouseId, null);
        assertNotNull(result, "Router should return a result");

        waitForLoggerFlush();

        int logCount = getRecentLogCount(fn);
        assertTrue(logCount > 0,
            "SHADOW mode call through Wave5FunctionRouter should log to migration.function_log");
    }

    @Test
    void bomQtyReserved_routerLogsInShadowMode() throws InterruptedException {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();
        assumeTrue(productId != null && productId > 0, "No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0, "No warehouse available");

        String fn = "bomQtyReserved";
        setShadowMode(fn);
        clearRecentLogs(fn);

        BigDecimal result = Wave5FunctionRouter.bomQtyReserved(productId, warehouseId, null);
        assertNotNull(result, "Router should return a result");

        waitForLoggerFlush();

        int logCount = getRecentLogCount(fn);
        assertTrue(logCount > 0,
            "SHADOW mode call through Wave5FunctionRouter should log to migration.function_log");
    }

    @Test
    void bomQtyOrdered_routerLogsInShadowMode() throws InterruptedException {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();
        assumeTrue(productId != null && productId > 0, "No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0, "No warehouse available");

        String fn = "bomQtyOrdered";
        setShadowMode(fn);
        clearRecentLogs(fn);

        BigDecimal result = Wave5FunctionRouter.bomQtyOrdered(productId, warehouseId, null);
        assertNotNull(result, "Router should return a result");

        waitForLoggerFlush();

        int logCount = getRecentLogCount(fn);
        assertTrue(logCount > 0,
            "SHADOW mode call through Wave5FunctionRouter should log to migration.function_log");
    }

    @Test
    void bomQtyAvailable_routerLogsInShadowMode() throws InterruptedException {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();
        assumeTrue(productId != null && productId > 0, "No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0, "No warehouse available");

        String fn = "bomQtyAvailable";
        setShadowMode(fn);
        clearRecentLogs(fn);

        BigDecimal result = Wave5FunctionRouter.bomQtyAvailable(productId, warehouseId, null);
        assertNotNull(result, "Router should return a result");

        waitForLoggerFlush();

        int logCount = getRecentLogCount(fn);
        assertTrue(logCount > 0,
            "SHADOW mode call through Wave5FunctionRouter should log to migration.function_log");
    }

    // ==================== Match Status Verification ====================

    @Test
    void router_logsMatchStatus() throws InterruptedException {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();
        assumeTrue(productId != null && productId > 0, "No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0, "No warehouse available");

        String fn = "bomQtyOnHand";
        setShadowMode(fn);
        clearRecentLogs(fn);

        Wave5FunctionRouter.bomQtyOnHand(productId, warehouseId, null);
        waitForLoggerFlush();

        // Verify log entry has is_match field populated
        String isMatch = DB.getSQLValueString(null,
            "SELECT CASE WHEN is_match THEN 'true' ELSE 'false' END FROM migration.function_log " +
            "WHERE function_name = ? ORDER BY created_at DESC LIMIT 1", fn);

        assertNotNull(isMatch, "Log entry should have is_match status");
        assertTrue("true".equals(isMatch) || "false".equals(isMatch),
            "is_match should be true or false, got: " + isMatch);
    }

    // ==================== Mode Behavior Tests ====================

    @Test
    void router_sqlOnlyMode_skipsLogging() throws InterruptedException {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();
        assumeTrue(productId != null && productId > 0, "No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0, "No warehouse available");

        String fn = "bomQtyOnHand";
        DB.executeUpdate(
            "UPDATE migration.function_config SET mode = 'SQL_ONLY', updated_at = NOW() WHERE function_name = ?",
            new Object[]{fn}, false, null);
        MigrationConfig.invalidateCache(fn);
        clearRecentLogs(fn);

        Wave5FunctionRouter.bomQtyOnHand(productId, warehouseId, null);
        waitForLoggerFlush();

        int logCount = getRecentLogCount(fn);
        assertEquals(0, logCount, "SQL_ONLY mode should not log to function_log");
    }

    @Test
    void router_javaOnlyMode_skipsLogging() throws InterruptedException {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();
        assumeTrue(productId != null && productId > 0, "No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0, "No warehouse available");

        String fn = "bomQtyOnHand";
        DB.executeUpdate(
            "UPDATE migration.function_config SET mode = 'JAVA_ONLY', updated_at = NOW() WHERE function_name = ?",
            new Object[]{fn}, false, null);
        MigrationConfig.invalidateCache(fn);
        clearRecentLogs(fn);

        Wave5FunctionRouter.bomQtyOnHand(productId, warehouseId, null);
        waitForLoggerFlush();

        int logCount = getRecentLogCount(fn);
        assertEquals(0, logCount, "JAVA_ONLY mode should not log to function_log");
    }

    // ==================== Sample Rate Tests ====================

    @Test
    void router_fullSampleRate_logsAllCalls() throws InterruptedException {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();
        assumeTrue(productId != null && productId > 0, "No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0, "No warehouse available");

        String fn = "bomQtyOnHand";
        setShadowMode(fn); // Sets sample_rate = 1.0
        clearRecentLogs(fn);

        int callCount = 5;
        for (int i = 0; i < callCount; i++) {
            Wave5FunctionRouter.bomQtyOnHand(productId, warehouseId, null);
        }

        waitForLoggerFlush();

        int logCount = getRecentLogCount(fn);
        assertEquals(callCount, logCount, "100% sample rate should log all " + callCount + " calls");
    }

    @Test
    void router_zeroSampleRate_noLogging() throws InterruptedException {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();
        assumeTrue(productId != null && productId > 0, "No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0, "No warehouse available");

        String fn = "bomQtyOnHand";
        DB.executeUpdate(
            "UPDATE migration.function_config SET mode = 'SHADOW', sample_rate = 0.0, updated_at = NOW() WHERE function_name = ?",
            new Object[]{fn}, false, null);
        MigrationConfig.invalidateCache(fn);
        clearRecentLogs(fn);

        for (int i = 0; i < 10; i++) {
            Wave5FunctionRouter.bomQtyOnHand(productId, warehouseId, null);
        }

        waitForLoggerFlush();

        int logCount = getRecentLogCount(fn);
        assertEquals(0, logCount, "0% sample rate should not log any calls");
    }
}
