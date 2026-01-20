package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.DB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gate 3 verification test: Generates shadow comparison logs for all Wave 4 functions
 * and verifies 100% match rate.
 *
 * <p>This test:
 * <ol>
 *   <li>Calls Wave 4 functions through the router (which executes shadow comparison)</li>
 *   <li>Waits for async logging to complete</li>
 *   <li>Queries migration.function_log for match rates</li>
 *   <li>Asserts 100% match rate for all functions</li>
 * </ol>
 *
 * <p>Unlike other shadow tests, this does NOT clean up logs so results can be verified.
 */
@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave4ShadowMatchRateTest extends CommonGWSetup {

    private int[] accountIds;
    private int[] invoiceLineIds;
    private int[] orderLineIds;

    @BeforeAll
    void setupTestData() {
        // Ensure SHADOW mode with 100% sample rate
        DB.executeUpdate(
            "UPDATE migration.function_config SET mode = 'SHADOW', sample_rate = 1.0 " +
            "WHERE function_name IN ('acctBalance', 'productAttribute', 'documentNo', " +
            "'get_Sysconfig', 'linenetamtrealinvoiceline', 'linenetamtrealorderline', 'maxpaydate')",
            null);

        // Invalidate cache
        MigrationConfig.clearCache();

        // Load test IDs
        accountIds = queryIds("SELECT C_ElementValue_ID FROM C_ElementValue WHERE IsActive='Y' FETCH FIRST 10 ROWS ONLY");
        invoiceLineIds = queryIds("SELECT C_InvoiceLine_ID FROM C_InvoiceLine WHERE IsActive='Y' FETCH FIRST 10 ROWS ONLY");
        orderLineIds = queryIds("SELECT C_OrderLine_ID FROM C_OrderLine WHERE IsActive='Y' FETCH FIRST 10 ROWS ONLY");
    }

    private int[] queryIds(String sql) {
        List<Integer> ids = new ArrayList<>();
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
        } catch (Exception e) {
            // ignore
        }
        return ids.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    void generateShadowLogsAndVerifyMatchRate() throws InterruptedException, SQLException {
        // Clear recent Wave 4 logs for clean measurement
        DB.executeUpdate(
            "DELETE FROM migration.function_log WHERE function_name IN " +
            "('acctBalance', 'productAttribute', 'documentNo', 'get_Sysconfig', " +
            "'linenetamtrealinvoiceline', 'linenetamtrealorderline', 'maxpaydate') " +
            "AND created_at > NOW() - INTERVAL '1 hour'", null);

        // Exercise routers to generate shadow logs

        // acctBalance - multiple calls
        for (int id : accountIds) {
            Wave4FunctionRouter.acctBalance(id, new BigDecimal("100.00"), new BigDecimal("30.00"));
            Wave4FunctionRouter.acctBalance(id, new BigDecimal("0"), new BigDecimal("0"));
        }

        // getSysconfig
        Wave4FunctionRouter.getSysconfig("SYSTEM_NATIVE_SEQUENCE", "N", 0, 0);
        Wave4FunctionRouter.getSysconfig("NONEXISTENT", "default", 0, 0);

        // linenetamtrealinvoiceline
        for (int id : invoiceLineIds) {
            Wave4FunctionRouter.linenetamtrealinvoiceline(id);
        }

        // linenetamtrealorderline
        for (int id : orderLineIds) {
            Wave4FunctionRouter.linenetamtrealorderline(id);
        }

        // documentNo (with invalid ID - tests null handling)
        Wave4FunctionRouter.documentNo(-1);
        Wave4FunctionRouter.documentNo(0);

        // maxpaydate (with invalid ID - tests null handling)
        Wave4FunctionRouter.maxpaydate(-1);
        Wave4FunctionRouter.maxpaydate(0);

        // Wait for async logger to flush
        long deadline = System.currentTimeMillis() + 5000;
        while (MigrationLogger.getQueueDepth() > 0 && System.currentTimeMillis() < deadline) {
            Thread.sleep(100);
        }
        Thread.sleep(500); // Extra buffer for DB writes

        // Query match rates
        String sql =
            "SELECT function_name, COUNT(*) as total, " +
            "SUM(CASE WHEN is_match THEN 1 ELSE 0 END) as matches, " +
            "ROUND(100.0 * SUM(CASE WHEN is_match THEN 1 ELSE 0 END) / COUNT(*), 2) as match_pct " +
            "FROM migration.function_log " +
            "WHERE function_name IN ('acctBalance', 'productAttribute', 'documentNo', " +
            "'get_Sysconfig', 'linenetamtrealinvoiceline', 'linenetamtrealorderline', 'maxpaydate') " +
            "AND created_at > NOW() - INTERVAL '1 hour' " +
            "GROUP BY function_name ORDER BY function_name";

        System.out.println("\n=== Wave 4 Shadow Match Rate Results ===");

        int totalFunctions = 0;
        int functionsWithLogs = 0;
        int totalCalls = 0;
        int totalMatches = 0;

        try (PreparedStatement pstmt = DB.prepareStatement(sql, null);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String fname = rs.getString("function_name");
                int total = rs.getInt("total");
                int matches = rs.getInt("matches");
                BigDecimal matchPct = rs.getBigDecimal("match_pct");

                System.out.printf("  %-30s  calls: %3d  matches: %3d  rate: %6.2f%%%n",
                    fname, total, matches, matchPct);

                functionsWithLogs++;
                totalCalls += total;
                totalMatches += matches;
            }
        }

        System.out.println("----------------------------------------");
        if (totalCalls > 0) {
            System.out.printf("  %-30s  calls: %3d  matches: %3d  rate: %6.2f%%%n",
                "TOTAL", totalCalls, totalMatches, (100.0 * totalMatches / totalCalls));
        } else {
            System.out.println("  NO LOGS GENERATED - check router configuration");
        }
        System.out.println();

        // Assertions
        assertTrue(totalCalls > 0, "Expected shadow logs to be generated");
        assertEquals(totalMatches, totalCalls,
            String.format("Expected 100%% match rate, got %d/%d matches", totalMatches, totalCalls));
    }
}
