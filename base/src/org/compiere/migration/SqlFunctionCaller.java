package org.compiere.migration;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import javax.annotation.Nullable;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Calls PostgreSQL functions for shadow mode comparison.
 * Each method wraps a SELECT call to the SQL function.
 * Throws SqlFunctionException on database errors to allow proper circuit breaker handling.
 *
 * <p><b>Transaction Context:</b> These are read-only SELECT queries that use
 * auto-commit semantics via DB.prepareStatement(sql, null). They do not
 * participate in the caller's transaction.</p>
 *
 * <p><b>Sampling Note:</b> When ShadowExecutor sampling skips a call, no log
 * entry is generated. This is intentional to reduce log volume. For
 * high-volume functions, consider adding periodic summaries.</p>
 */
public class SqlFunctionCaller {
    private static final CLogger log = CLogger.getCLogger(SqlFunctionCaller.class);

    /** Calls: SELECT getDate() */
    @Nullable
    public static Timestamp callGetDate() {
        String sql = "SELECT getDate()";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getTimestamp(1);
            }
            // Scalar functions should always return a row
            log.warning("getDate() returned no rows - possible connection issue");
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to call getDate()", e);
            throw new SqlFunctionException("getDate", e);
        }
        return null;
    }

    /** Calls: SELECT daysBetween(?, ?) */
    @Nullable
    public static Integer callDaysBetween(@Nullable Timestamp date1, @Nullable Timestamp date2) {
        String sql = "SELECT daysBetween(?, ?)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setTimestamp(1, date1);
            pstmt.setTimestamp(2, date2);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int result = rs.getInt(1);
                    return rs.wasNull() ? null : result;
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to call daysBetween()", e);
            throw new SqlFunctionException("daysBetween", e);
        }
        return null;
    }

    /** Calls: SELECT addDays(?, ?) - TIMESTAMP, Numeric -> DATE */
    @Nullable
    public static Date callAddDays(@Nullable Timestamp datetime, @Nullable BigDecimal days) {
        String sql = "SELECT addDays(?::TIMESTAMP WITH TIME ZONE, ?)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setTimestamp(1, datetime);
            pstmt.setBigDecimal(2, days);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDate(1);
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to call addDays(timestamp, numeric)", e);
            throw new SqlFunctionException("addDays", e);
        }
        return null;
    }

    /** Calls: SELECT subtractDays(?, ?) - TIMESTAMP, Numeric -> DATE */
    @Nullable
    public static Date callSubtractDays(@Nullable Timestamp datetime, @Nullable BigDecimal days) {
        String sql = "SELECT subtractDays(?::TIMESTAMP WITH TIME ZONE, ?)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setTimestamp(1, datetime);
            pstmt.setBigDecimal(2, days);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDate(1);
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to call subtractDays(timestamp, numeric)", e);
            throw new SqlFunctionException("subtractDays", e);
        }
        return null;
    }

    /**
     * Calls: SELECT trunc(?) - TIMESTAMP -> TIMESTAMP
     * Note: PostgreSQL trunc(timestamp) returns TIMESTAMP WITH TIME ZONE per the
     * function signature, though internally it casts to DATE. We preserve this
     * signature for compatibility. The result will have 00:00:00 time component.
     */
    @Nullable
    public static Timestamp callTrunc(@Nullable Timestamp datetime) {
        String sql = "SELECT trunc(?::TIMESTAMP WITH TIME ZONE)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setTimestamp(1, datetime);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getTimestamp(1);
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to call trunc(timestamp)", e);
            throw new SqlFunctionException("trunc", e);
        }
        return null;
    }

    /** Calls: SELECT trunc(?, ?) - TIMESTAMP, format -> DATE */
    @Nullable
    public static Date callTrunc(@Nullable Timestamp datetime, @Nullable String format) {
        String sql = "SELECT trunc(?::TIMESTAMP WITH TIME ZONE, ?)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setTimestamp(1, datetime);
            pstmt.setString(2, format);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDate(1);
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to call trunc(timestamp, format)", e);
            throw new SqlFunctionException("trunc", e);
        }
        return null;
    }

    /** Calls: SELECT round(?, ?) - NUMERIC, INTEGER -> NUMERIC */
    @Nullable
    public static BigDecimal callRound(@Nullable BigDecimal value, int scale) {
        String sql = "SELECT round(?, ?)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setBigDecimal(1, value);
            pstmt.setInt(2, scale);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal(1);
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to call round()", e);
            throw new SqlFunctionException("round", e);
        }
        return null;
    }

    /** Calls: SELECT firstOf(?, ?) - TIMESTAMP, VARCHAR -> DATE */
    @Nullable
    public static Date callFirstOf(@Nullable Timestamp datetime, @Nullable String datePart) {
        String sql = "SELECT firstOf(?, ?)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setTimestamp(1, datetime);
            pstmt.setString(2, datePart);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDate(1);
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to call firstOf()", e);
            throw new SqlFunctionException("firstOf", e);
        }
        return null;
    }

    /** Calls: SELECT charAt(?, ?) - VARCHAR, INTEGER -> VARCHAR */
    @Nullable
    public static String callCharAt(@Nullable String str, int position) {
        String sql = "SELECT charAt(?, ?)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setString(1, str);
            pstmt.setInt(2, position);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to call charAt()", e);
            throw new SqlFunctionException("charAt", e);
        }
        return null;
    }
}
