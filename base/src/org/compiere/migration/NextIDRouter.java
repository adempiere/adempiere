package org.compiere.migration;

import java.util.logging.Level;
import org.compiere.util.CLogger;

/**
 * Router for nextID/nextIDFunc with shadow logging.
 *
 * Unlike regular shadow mode, sequence functions cannot be dual-executed
 * because each call consumes a sequence value. Instead, we interpret
 * SHADOW mode for stateful functions as:
 * 1. Execute Java implementation
 * 2. Log execution details for offline validation
 * 3. Return Java result
 *
 * This allows monitoring without consuming extra sequence values.
 */
public class NextIDRouter {

    private static final CLogger log = CLogger.getCLogger(NextIDRouter.class);

    private NextIDRouter() {
        // Static methods only
    }

    /**
     * Route nextID call with shadow logging.
     */
    public static int nextID(Integer adSequenceId, String system, String trxName) {
        MigrationConfig config = MigrationConfig.get("nextID");

        // If SQL_ONLY, call legacy stored procedure
        if (config.getMode() == MigrationMode.SQL_ONLY) {
            return callLegacyNextID(adSequenceId, system, trxName);
        }

        // Execute Java implementation
        long startTime = System.nanoTime();
        int result;
        Exception error = null;
        boolean usedSqlFallback = false;

        try {
            result = Wave4Functions.nextID(adSequenceId, system, trxName);
        } catch (Exception e) {
            error = e;
            log.log(Level.SEVERE, "nextID Java implementation failed", e);
            // Fallback to SQL if in SHADOW mode (still validating)
            if (config.getMode() == MigrationMode.SHADOW) {
                result = callLegacyNextID(adSequenceId, system, trxName);
                usedSqlFallback = true;
            } else {
                throw e;
            }
        }

        long durationNanos = System.nanoTime() - startTime;

        // Log for offline validation (SHADOW mode for stateful functions)
        if (config.getMode() == MigrationMode.SHADOW) {
            logExecution(adSequenceId, system, result, durationNanos, error, usedSqlFallback);
        }

        return result;
    }

    /**
     * Route nextIDFunc call (wrapper for nextID).
     */
    public static int nextIDFunc(Integer adSequenceId, String system, String trxName) {
        return nextID(adSequenceId, system, trxName);
    }

    private static int callLegacyNextID(Integer adSequenceId, String system, String trxName) {
        // Call PostgreSQL function via JDBC
        // Note: PostgreSQL nextID is a procedure with OUT param
        String sql = "SELECT nextid(?, ?)";
        try (java.sql.PreparedStatement pstmt = org.compiere.util.DB.prepareStatement(sql, trxName)) {
            pstmt.setInt(1, adSequenceId);
            pstmt.setString(2, system);
            try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (java.sql.SQLException e) {
            log.log(Level.SEVERE, "Legacy nextID failed", e);
        }
        return -1;
    }

    private static void logExecution(Integer adSequenceId, String system, int result,
                                      long durationNanos, Exception error, boolean usedSqlFallback) {
        // Use correct MigrationLogger API signature
        // Note: isMatch is false for stateful functions (no comparison possible)
        // Status markers for monitoring dashboards:
        //   - STATEFUL_NO_COMPARISON: Normal operation, Java executed successfully
        //   - JAVA_FAILED_SQL_FALLBACK: Java threw exception, result is from SQL fallback
        String statusMarker;
        if (error != null) {
            statusMarker = "JAVA_FAILED_SQL_FALLBACK: " + error.getMessage();
        } else if (usedSqlFallback) {
            statusMarker = "SQL_FALLBACK_USED";
        } else {
            statusMarker = "STATEFUL_NO_COMPARISON";
        }

        MigrationLogger.logAsync(
            "nextID",                                           // functionName
            ParamSerializer.toJson(adSequenceId, system),       // inputParams (JSON string)
            usedSqlFallback ? String.valueOf(result) : null,    // sqlResult (only if fallback used)
            usedSqlFallback ? null : String.valueOf(result),    // javaResult (only if Java succeeded)
            0L,                                                 // sqlTimeMs (not measured)
            durationNanos / 1_000_000,                          // javaTimeMs
            false,                                              // isMatch (always false - no comparison for stateful)
            statusMarker
        );
    }
}
