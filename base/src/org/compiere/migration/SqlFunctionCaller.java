package org.compiere.migration;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
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

    // --- Utility methods for nullable parameter handling ---

    /**
     * Set nullable Integer parameter on PreparedStatement.
     */
    private static void setNullableInt(PreparedStatement ps, int index, Integer value)
            throws SQLException {
        if (value != null) {
            ps.setInt(index, value);
        } else {
            ps.setNull(index, Types.INTEGER);
        }
    }

    /**
     * Set nullable BigDecimal parameter on PreparedStatement.
     */
    private static void setNullableBigDecimal(PreparedStatement ps, int index, BigDecimal value)
            throws SQLException {
        if (value != null) {
            ps.setBigDecimal(index, value);
        } else {
            ps.setNull(index, Types.NUMERIC);
        }
    }

    /**
     * Set nullable Timestamp parameter on PreparedStatement.
     */
    private static void setNullableTimestamp(PreparedStatement ps, int index, Timestamp value)
            throws SQLException {
        if (value != null) {
            ps.setTimestamp(index, value);
        } else {
            ps.setNull(index, Types.TIMESTAMP);
        }
    }

    /**
     * Set nullable String parameter on PreparedStatement.
     */
    private static void setNullableString(PreparedStatement ps, int index, String value)
            throws SQLException {
        if (value != null) {
            ps.setString(index, value);
        } else {
            ps.setNull(index, Types.VARCHAR);
        }
    }

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

    /** Calls: SELECT currencyRound(?, ?, ?) */
    @Nullable
    public static BigDecimal callCurrencyRound(@Nullable BigDecimal amount,
                                                @Nullable Integer currencyId,
                                                @Nullable String costing) {
        String sql = "SELECT currencyRound(?, ?, ?)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            setNullableBigDecimal(pstmt, 1, amount);
            setNullableInt(pstmt, 2, currencyId);
            setNullableString(pstmt, 3, costing);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal(1);
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to call currencyRound()", e);
            throw new SqlFunctionException("currencyRound", e);
        }
        return null;
    }

    /** Calls: SELECT currencyRate(?, ?, ?, ?, ?, ?) */
    @Nullable
    public static BigDecimal callCurrencyRate(@Nullable Integer curFromId,
                                               @Nullable Integer curToId,
                                               @Nullable Timestamp convDate,
                                               @Nullable Integer convTypeId,
                                               @Nullable Integer clientId,
                                               @Nullable Integer orgId) {
        String sql = "SELECT currencyRate(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            setNullableInt(pstmt, 1, curFromId);
            setNullableInt(pstmt, 2, curToId);
            setNullableTimestamp(pstmt, 3, convDate);
            setNullableInt(pstmt, 4, convTypeId);
            setNullableInt(pstmt, 5, clientId);
            setNullableInt(pstmt, 6, orgId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal(1);
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to call currencyRate()", e);
            throw new SqlFunctionException("currencyRate", e);
        }
        return null;
    }

    /** Calls: SELECT currencyConvert(?, ?, ?, ?, ?, ?, ?) */
    @Nullable
    public static BigDecimal callCurrencyConvert(@Nullable BigDecimal amount,
                                                  @Nullable Integer curFromId,
                                                  @Nullable Integer curToId,
                                                  @Nullable Timestamp convDate,
                                                  @Nullable Integer convTypeId,
                                                  @Nullable Integer clientId,
                                                  @Nullable Integer orgId) {
        String sql = "SELECT currencyConvert(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            setNullableBigDecimal(pstmt, 1, amount);
            setNullableInt(pstmt, 2, curFromId);
            setNullableInt(pstmt, 3, curToId);
            setNullableTimestamp(pstmt, 4, convDate);
            setNullableInt(pstmt, 5, convTypeId);
            setNullableInt(pstmt, 6, clientId);
            setNullableInt(pstmt, 7, orgId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal(1);
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to call currencyConvert()", e);
            throw new SqlFunctionException("currencyConvert", e);
        }
        return null;
    }
}
