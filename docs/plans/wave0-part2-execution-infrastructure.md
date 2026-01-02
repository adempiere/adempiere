# Wave 0 Part 2: Execution Infrastructure

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Parent Plan:** [Wave 0 Implementation Plan](2026-01-02-wave0-implementation-plan.md)
**Part:** 2 of 5
**Tasks:** 4-6 + SqlFunctionException (CircuitBreaker, Exception, SqlFunctionCaller, ShadowExecutor)

**Previous:** [Part 1: Core Infrastructure](wave0-part1-core-infrastructure.md)

---

## Task 4: Create CircuitBreaker (with observability logging)

**Files:**
- Create: `base/src/org/compiere/migration/CircuitBreaker.java`
- Test: `base/test/src/org/compiere/migration/CircuitBreakerTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/migration/CircuitBreakerTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
public class CircuitBreakerTest {

    @BeforeEach
    void reset() {
        CircuitBreaker.resetAll(); // Clean all circuits between tests
    }

    @Test
    void testCircuitStartsClosed() {
        assertFalse(CircuitBreaker.isOpen("testFunc"));
    }

    @Test
    void testCircuitOpensAfterThreshold() {
        for (int i = 0; i < 5; i++) {
            CircuitBreaker.recordFailure("testFunc");
        }
        assertTrue(CircuitBreaker.isOpen("testFunc"));
    }

    @Test
    void testCircuitStaysClosedBelowThreshold() {
        for (int i = 0; i < 4; i++) {
            CircuitBreaker.recordFailure("testFunc");
        }
        assertFalse(CircuitBreaker.isOpen("testFunc"));
    }

    @Test
    void testSuccessResetsFailureCount() {
        for (int i = 0; i < 4; i++) {
            CircuitBreaker.recordFailure("testFunc");
        }
        CircuitBreaker.recordSuccess("testFunc");
        CircuitBreaker.recordFailure("testFunc");
        assertFalse(CircuitBreaker.isOpen("testFunc"));
    }

    @Test
    void testCircuitsArePerFunction() {
        for (int i = 0; i < 5; i++) {
            CircuitBreaker.recordFailure("func1");
        }
        assertTrue(CircuitBreaker.isOpen("func1"));
        assertFalse(CircuitBreaker.isOpen("func2"));
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.CircuitBreakerTest" -i`
Expected: FAIL with "cannot find symbol: class CircuitBreaker"

**Step 3: Write CircuitBreaker implementation (with logging)**

```java
// base/src/org/compiere/migration/CircuitBreaker.java
package org.compiere.migration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.compiere.util.CLogger;

/**
 * Per-function circuit breaker to prevent cascading failures during SQL outages.
 * Opens after FAILURE_THRESHOLD consecutive failures, resets after RESET_TIMEOUT_MS.
 * Logs state transitions for operational visibility.
 *
 * <p><b>Design Note:</b> This is a simplified two-state breaker (CLOSED/OPEN) without
 * a HALF_OPEN state. After timeout, the circuit closes and allows all requests through.
 * This is acceptable for Wave 0's low-volume functions (&lt;100 calls/day). For high-volume
 * functions, consider implementing the full pattern with HALF_OPEN state that allows
 * a single test request before fully closing.</p>
 *
 * <p><b>Memory Note:</b> The circuits map is bounded by the number of migrated functions
 * (currently 8 for Wave 0). In a future with dynamic function names, consider adding
 * periodic cleanup of stale closed circuits.</p>
 *
 * <p><b>Scope:</b> Circuit state is per-JVM. In clustered deployments, each instance
 * maintains independent state. This prevents a single failing node from tripping
 * circuits cluster-wide.</p>
 */
public class CircuitBreaker {
    private static final CLogger log = CLogger.getCLogger(CircuitBreaker.class);
    private static final int FAILURE_THRESHOLD = 5;
    private static final long RESET_TIMEOUT_MS = Long.getLong(
        "migration.circuit.reset.timeout.ms", 60_000); // 1 minute default

    private static final ConcurrentHashMap<String, CircuitState> circuits = new ConcurrentHashMap<>();

    /**
     * Check if circuit is open for the given function.
     * Uses compare-and-swap for thread-safe timeout reset.
     */
    public static boolean isOpen(String functionName) {
        CircuitState state = circuits.get(functionName);
        if (state == null) return false;

        long openedTime = state.openedAt.get();
        if (openedTime == 0) return false; // Not open

        if (System.currentTimeMillis() - openedTime > RESET_TIMEOUT_MS) {
            // Atomic reset - only one thread succeeds in logging
            if (state.openedAt.compareAndSet(openedTime, 0)) {
                state.failures.set(0);
                log.info("Circuit CLOSED for function: " + functionName + " (timeout elapsed)");
            }
            return false;
        }
        return true;
    }

    public static void recordSuccess(String functionName) {
        CircuitState state = circuits.get(functionName);
        if (state != null) {
            state.failures.set(0);
        }
    }

    public static void recordFailure(String functionName) {
        CircuitState state = circuits.computeIfAbsent(functionName, k -> new CircuitState());
        int failures = state.failures.incrementAndGet();
        if (failures >= FAILURE_THRESHOLD) {
            // compareAndSet ensures we only log once when opening
            if (state.openedAt.compareAndSet(0, System.currentTimeMillis())) {
                log.warning("Circuit OPENED for function: " + functionName +
                           " after " + failures + " consecutive failures");
            }
        }
    }

    /** Reset circuit state for a function (used in testing) */
    public static void reset(String functionName) {
        circuits.remove(functionName);
    }

    /** Reset all circuit state (used in testing) */
    public static void resetAll() {
        circuits.clear();
    }

    private static class CircuitState {
        final AtomicInteger failures = new AtomicInteger(0);
        final AtomicLong openedAt = new AtomicLong(0);
    }
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.CircuitBreakerTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/CircuitBreaker.java
git add base/test/src/org/compiere/migration/CircuitBreakerTest.java
git commit -m "feat: add CircuitBreaker for shadow mode resilience

- Opens after 5 consecutive SQL failures
- Auto-resets after 60 seconds
- Per-function isolation prevents cross-contamination
- Logs WARNING on open, INFO on close for operational visibility

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Task 4.5: Create SqlFunctionException

**Files:**
- Create: `base/src/org/compiere/migration/SqlFunctionException.java`

**Step 1: Write SqlFunctionException**

```java
// base/src/org/compiere/migration/SqlFunctionException.java
package org.compiere.migration;

/**
 * Exception thrown when SQL function execution fails.
 * Allows ShadowExecutor to distinguish database failures from NULL results.
 * This is a RuntimeException to avoid polluting method signatures,
 * but should be caught explicitly in ShadowExecutor.
 */
public class SqlFunctionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String functionName;

    public SqlFunctionException(String functionName, Throwable cause) {
        super("SQL function failed: " + functionName, cause);
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }
}
```

**Step 2: Commit**

```bash
git add base/src/org/compiere/migration/SqlFunctionException.java
git commit -m "feat: add SqlFunctionException for SQL failure signaling

- Distinguishes database failures from SQL NULL results
- Carries function name for error context
- RuntimeException to avoid signature pollution

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Task 5: Create SqlFunctionCaller (with exception throwing)

**Files:**
- Create: `base/src/org/compiere/migration/SqlFunctionCaller.java`
- Test: `base/test/src/org/compiere/migration/SqlFunctionCallerTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/migration/SqlFunctionCallerTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("IntegrationTest")
public class SqlFunctionCallerTest extends CommonGWSetup {

    @Test
    void testCallGetDate() {
        Timestamp result = SqlFunctionCaller.callGetDate();
        assertNotNull(result);
        // Should be within last second
        long diff = System.currentTimeMillis() - result.getTime();
        assertTrue(Math.abs(diff) < 2000, "getDate() should return current time");
    }

    @Test
    void testCallDaysBetween() {
        Timestamp date1 = Timestamp.valueOf("2026-01-10 12:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-01 08:00:00");

        Integer result = SqlFunctionCaller.callDaysBetween(date1, date2);
        assertEquals(9, result);
    }

    @Test
    void testCallDaysBetweenReverse() {
        Timestamp date1 = Timestamp.valueOf("2026-01-01 08:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-10 12:00:00");

        Integer result = SqlFunctionCaller.callDaysBetween(date1, date2);
        assertEquals(-9, result);
    }

    @Test
    void testCallDaysBetweenNullInput() {
        assertNull(SqlFunctionCaller.callDaysBetween(null, Timestamp.valueOf("2026-01-01 00:00:00")));
        assertNull(SqlFunctionCaller.callDaysBetween(Timestamp.valueOf("2026-01-01 00:00:00"), null));
    }

    @Test
    void testCallAddDays() {
        Timestamp base = Timestamp.valueOf("2026-01-01 00:00:00");
        Date result = SqlFunctionCaller.callAddDays(base, new BigDecimal("5"));
        assertEquals(Date.valueOf("2026-01-06"), result);
    }

    @Test
    void testCallAddDaysNullInput() {
        assertNull(SqlFunctionCaller.callAddDays(null, new BigDecimal("5")));
    }

    @Test
    void testCallSubtractDays() {
        Timestamp base = Timestamp.valueOf("2026-01-10 00:00:00");
        Date result = SqlFunctionCaller.callSubtractDays(base, new BigDecimal("5"));
        assertEquals(Date.valueOf("2026-01-05"), result);
    }

    @Test
    void testCallSubtractDaysNullInput() {
        assertNull(SqlFunctionCaller.callSubtractDays(null, new BigDecimal("5")));
    }

    @Test
    void testCallTruncTimestamp() {
        Timestamp ts = Timestamp.valueOf("2026-01-15 14:30:45");
        Timestamp result = SqlFunctionCaller.callTrunc(ts);
        assertEquals(Timestamp.valueOf("2026-01-15 00:00:00"), result);
    }

    @Test
    void testCallTruncWithFormat() {
        Timestamp ts = Timestamp.valueOf("2026-01-15 14:30:45");
        Date result = SqlFunctionCaller.callTrunc(ts, "MM");
        assertEquals(Date.valueOf("2026-01-01"), result);
    }

    @Test
    void testCallTruncNullInput() {
        assertNull(SqlFunctionCaller.callTrunc(null));
        assertNull(SqlFunctionCaller.callTrunc(null, "MM"));
    }

    @Test
    void testCallRound() {
        BigDecimal value = new BigDecimal("123.456");
        BigDecimal result = SqlFunctionCaller.callRound(value, 2);
        assertEquals(new BigDecimal("123.46"), result);
    }

    @Test
    void testCallRoundNullInput() {
        assertNull(SqlFunctionCaller.callRound(null, 2));
    }

    @Test
    void testCallFirstOf() {
        Timestamp ts = Timestamp.valueOf("2026-01-15 14:30:45");
        Date result = SqlFunctionCaller.callFirstOf(ts, "MM");
        assertEquals(Date.valueOf("2026-01-01"), result);
    }

    @Test
    void testCallFirstOfYear() {
        Timestamp ts = Timestamp.valueOf("2026-03-15 14:30:45");
        Date result = SqlFunctionCaller.callFirstOf(ts, "YY");
        assertEquals(Date.valueOf("2026-01-01"), result);
    }

    @Test
    void testCallFirstOfNullInput() {
        assertNull(SqlFunctionCaller.callFirstOf(null, "MM"));
    }

    @Test
    void testCallCharAt() {
        String result = SqlFunctionCaller.callCharAt("Hello", 1);
        assertEquals("H", result);
    }

    @Test
    void testCallCharAtMiddle() {
        String result = SqlFunctionCaller.callCharAt("Hello", 3);
        assertEquals("l", result);
    }

    @Test
    void testCallCharAtNullInput() {
        assertNull(SqlFunctionCaller.callCharAt(null, 1));
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.SqlFunctionCallerTest" -PintegrationTest -i`
Expected: FAIL with "cannot find symbol: class SqlFunctionCaller"

**Step 3: Write SqlFunctionCaller implementation (throws SqlFunctionException)**

```java
// base/src/org/compiere/migration/SqlFunctionCaller.java
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
        String sql = "SELECT addDays(?, ?)";
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
        String sql = "SELECT subtractDays(?, ?)";
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
        String sql = "SELECT trunc(?)";
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
        String sql = "SELECT trunc(?, ?)";
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
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.SqlFunctionCallerTest" -PintegrationTest -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/SqlFunctionCaller.java
git add base/test/src/org/compiere/migration/SqlFunctionCallerTest.java
git commit -m "feat: add SqlFunctionCaller for shadow mode SQL execution

- Wrapper methods for all Wave 0 SQL functions
- Throws SqlFunctionException on database errors (not null)
- Uses rs.wasNull() to properly detect SQL NULL values
- Used by ShadowExecutor to call legacy SQL for comparison

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Task 6: Create ShadowExecutor (with improved exception handling)

**Files:**
- Create: `base/src/org/compiere/migration/ShadowExecutor.java`
- Test (Unit): `base/test/src/org/compiere/migration/ShadowExecutorTest.java`
- Test (Integration): `base/test/src/org/compiere/migration/ShadowExecutorIntegrationTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/migration/ShadowExecutorTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
public class ShadowExecutorTest {

    @Test
    void testSqlOnlyModeOnlyCallsSql() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);

        String result = ShadowExecutor.execute(
            "testFunc",
            new Object[]{},
            MigrationMode.SQL_ONLY,
            1.0,
            false,
            () -> { javaCalls.incrementAndGet(); return "java"; },
            () -> { sqlCalls.incrementAndGet(); return "sql"; },
            String::equals
        );

        assertEquals("sql", result);
        assertEquals(0, javaCalls.get());
        assertEquals(1, sqlCalls.get());
    }

    @Test
    void testJavaOnlyModeOnlyCallsJava() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);

        String result = ShadowExecutor.execute(
            "testFunc",
            new Object[]{},
            MigrationMode.JAVA_ONLY,
            1.0,
            false,
            () -> { javaCalls.incrementAndGet(); return "java"; },
            () -> { sqlCalls.incrementAndGet(); return "sql"; },
            String::equals
        );

        assertEquals("java", result);
        assertEquals(1, javaCalls.get());
        assertEquals(0, sqlCalls.get());
    }

    @Test
    void testShadowModeCallsBothAndReturnsJava() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);

        String result = ShadowExecutor.execute(
            "testFunc",
            new Object[]{},
            MigrationMode.SHADOW,
            1.0, // 100% sample rate
            false,
            () -> { javaCalls.incrementAndGet(); return "java"; },
            () -> { sqlCalls.incrementAndGet(); return "sql"; },
            String::equals
        );

        assertEquals("java", result);
        assertEquals(1, javaCalls.get());
        assertEquals(1, sqlCalls.get());
    }

    @Test
    void testShadowModeSkipsSqlWhenCircuitOpen() {
        // Open the circuit
        for (int i = 0; i < 5; i++) {
            CircuitBreaker.recordFailure("circuitTest");
        }

        AtomicInteger sqlCalls = new AtomicInteger(0);

        String result = ShadowExecutor.execute(
            "circuitTest",
            new Object[]{},
            MigrationMode.SHADOW,
            1.0,
            true, // circuit breaker enabled
            () -> "java",
            () -> { sqlCalls.incrementAndGet(); return "sql"; },
            String::equals
        );

        assertEquals("java", result);
        assertEquals(0, sqlCalls.get());

        // Cleanup
        CircuitBreaker.reset("circuitTest");
    }

    @Test
    void testSqlFunctionExceptionTripsCircuitBreaker() {
        CircuitBreaker.reset("exceptionTest");

        // Should not throw, just log and return Java result
        String result = ShadowExecutor.execute(
            "exceptionTest",
            new Object[]{},
            MigrationMode.SHADOW,
            1.0,
            true,
            () -> "java",
            () -> { throw new SqlFunctionException("test", new RuntimeException("db error")); },
            String::equals
        );

        assertEquals("java", result);
        // After one failure, circuit should still be closed (threshold is 5)
        assertFalse(CircuitBreaker.isOpen("exceptionTest"));

        // Cleanup
        CircuitBreaker.reset("exceptionTest");
    }

    @Test
    void testNullParamsHandledGracefully() {
        // Verify null params array doesn't cause NPE
        String result = ShadowExecutor.execute(
            "nullParamsTest",
            null, // null params
            MigrationMode.JAVA_ONLY,
            1.0,
            false,
            () -> "java",
            () -> "sql",
            String::equals
        );

        assertEquals("java", result);
    }
}

// Additional integration test class for config lookup
// base/test/src/org/compiere/migration/ShadowExecutorIntegrationTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("IntegrationTest")
public class ShadowExecutorIntegrationTest extends CommonGWSetup {

    @Test
    void testExecuteWithAutoConfigLookup() {
        // Uses the 4-param execute() that looks up config automatically
        // Default config for unknown function is SQL_ONLY
        String result = ShadowExecutor.execute(
            "unknownFunc",
            new Object[]{},
            () -> "java",
            () -> "sql",
            String::equals
        );

        // Default mode is SQL_ONLY, so should return SQL result
        assertEquals("sql", result);
    }

    @Test
    void testExecuteWithKnownFunction() {
        // Test with a function that will be configured in migration.function_config
        // For now, verify it doesn't throw on a known Wave 0 function name
        String result = ShadowExecutor.execute(
            "getDate",
            new Object[]{},
            () -> "java",
            () -> "sql",
            String::equals
        );

        // Result depends on configured mode, just verify no exception
        assertNotNull(result);
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.ShadowExecutorTest" -i`
Expected: FAIL with "cannot find symbol: class ShadowExecutor"

**Step 3: Write ShadowExecutor implementation (with typed exception handling)**

```java
// base/src/org/compiere/migration/ShadowExecutor.java
package org.compiere.migration;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.logging.Level;

import org.compiere.util.CLogger;

/**
 * Orchestrates shadow execution for function migration.
 * Executes both Java and SQL implementations, compares results, logs mismatches.
 * Handles exceptions with proper circuit breaker integration.
 */
public class ShadowExecutor {
    private static final CLogger log = CLogger.getCLogger(ShadowExecutor.class);

    /**
     * Execute with automatic config lookup.
     */
    public static <T> T execute(String functionName,
                                 @Nullable Object[] params,
                                 Supplier<T> javaPath,
                                 Supplier<T> sqlPath,
                                 BiPredicate<T, T> comparator) {
        MigrationConfig config = MigrationConfig.get(functionName);
        return execute(functionName, params, config.getMode(), config.getSampleRate(),
                       config.isCircuitBreakerEnabled(), javaPath, sqlPath, comparator);
    }

    /**
     * Execute with explicit mode and sample rate (for testing).
     */
    public static <T> T execute(String functionName,
                                 @Nullable Object[] params,
                                 MigrationMode mode,
                                 double sampleRate,
                                 boolean circuitBreakerEnabled,
                                 Supplier<T> javaPath,
                                 Supplier<T> sqlPath,
                                 BiPredicate<T, T> comparator) {

        String serializedParams = ParamSerializer.toJson(params);

        if (mode == MigrationMode.SQL_ONLY) {
            return sqlPath.get();
        }

        // Execute Java
        long javaStart = System.nanoTime();
        T javaResult = javaPath.get();
        long javaTimeMs = (System.nanoTime() - javaStart) / 1_000_000;

        if (mode == MigrationMode.JAVA_ONLY) {
            return javaResult;
        }

        // SHADOW mode: check circuit breaker first
        if (circuitBreakerEnabled && CircuitBreaker.isOpen(functionName)) {
            MigrationLogger.logAsync(functionName, serializedParams, "", String.valueOf(javaResult),
                                      0, javaTimeMs, false, "CIRCUIT_OPEN");
            return javaResult;
        }

        // SHADOW mode: apply sampling
        if (!shouldSample(sampleRate)) {
            return javaResult;
        }

        // Execute SQL with proper exception handling
        long sqlStart = System.nanoTime();
        T sqlResult = null;
        long sqlTimeMs = 0;

        try {
            sqlResult = sqlPath.get();
            sqlTimeMs = (System.nanoTime() - sqlStart) / 1_000_000;
            if (circuitBreakerEnabled) {
                CircuitBreaker.recordSuccess(functionName);
            }
        } catch (SqlFunctionException e) {
            // Database/SQL issue - record failure for circuit breaker
            sqlTimeMs = (System.nanoTime() - sqlStart) / 1_000_000;
            if (circuitBreakerEnabled) {
                CircuitBreaker.recordFailure(functionName);
            }
            log.log(Level.WARNING, "Shadow SQL execution failed for " + functionName, e);
            MigrationLogger.logAsync(functionName, serializedParams, "", String.valueOf(javaResult),
                                      sqlTimeMs, javaTimeMs, false, "SQL_EXCEPTION: " + e.getMessage());
            return javaResult;
        } catch (RuntimeException e) {
            // Programming error - log but don't trip circuit breaker
            sqlTimeMs = (System.nanoTime() - sqlStart) / 1_000_000;
            log.log(Level.SEVERE, "Unexpected error in shadow execution for " + functionName, e);
            MigrationLogger.logAsync(functionName, serializedParams, "", String.valueOf(javaResult),
                                      sqlTimeMs, javaTimeMs, false, "UNEXPECTED_ERROR: " + e.getClass().getName());
            return javaResult;
        }

        // Compare
        boolean match = false;
        String mismatchReason = null;
        try {
            match = comparator.test(javaResult, sqlResult);
            if (!match) {
                mismatchReason = "VALUE_MISMATCH";
            }
        } catch (Exception e) {
            mismatchReason = "COMPARATOR_EXCEPTION: " + e.getMessage();
        }

        // Log async
        MigrationLogger.logAsync(functionName,
                                  serializedParams,
                                  String.valueOf(sqlResult),
                                  String.valueOf(javaResult),
                                  sqlTimeMs,
                                  javaTimeMs,
                                  match,
                                  mismatchReason);

        return javaResult;
    }

    private static boolean shouldSample(double sampleRate) {
        if (sampleRate >= 1.0) {
            return true;
        }
        if (sampleRate <= 0.0) {
            return false;
        }
        return ThreadLocalRandom.current().nextDouble() < sampleRate;
    }
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.ShadowExecutorTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/ShadowExecutor.java
git add base/test/src/org/compiere/migration/ShadowExecutorTest.java
git commit -m "feat: add ShadowExecutor for shadow mode orchestration

- Supports SQL_ONLY, SHADOW, JAVA_ONLY modes
- Catches SqlFunctionException for circuit breaker (transient failures)
- Catches RuntimeException separately (programming errors, no circuit trip)
- Sampling support for high-frequency functions
- Async logging with serialized input parameters

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Critical Review Decisions

This plan was updated based on [wave0-part2-execution-infrastructure-critical-review-1.md](wave0-part2-execution-infrastructure-critical-review-1.md).

### Design Decisions (from Questions for Clarification)

1. **Half-open state:** Accepted simplified two-state circuit breaker (CLOSED/OPEN) for Wave 0. The low call frequency (<100 calls/day) makes burst recovery after timeout acceptable. Documented in CircuitBreaker javadoc.

2. **Transaction context:** SqlFunctionCaller uses read-only auto-commit queries via `DB.prepareStatement(sql, null)`. These do not participate in the caller's transaction. Documented in SqlFunctionCaller javadoc.

3. **Circuit breaker scope:** Per-JVM (static) circuit state is intentional. In clustered deployments, each instance maintains independent state, preventing a single failing node from tripping circuits cluster-wide. Documented in CircuitBreaker javadoc.

4. **Sampling logging:** Silent drop (no log for sampled-out executions) is acceptable for Wave 0. This reduces log volume. For high-volume functions in future waves, consider adding periodic summaries. Documented in SqlFunctionCaller javadoc.

### Changes Made

**Required (Critical):**
- ✅ Fixed CircuitBreaker race condition with compare-and-swap in `isOpen()` (Issue 2.1)
- ✅ Added test coverage for all 8 SqlFunctionCaller methods including null handling (Issue 2.4)
- ✅ Added ShadowExecutorIntegrationTest for config lookup path (Issue 2.6)

**Recommended (Important):**
- ✅ Documented CircuitBreaker's simplified two-state design (Issue 2.2)
- ✅ Added `@Nullable` annotation to `Object[] params` in ShadowExecutor (Issue 2.7)
- ✅ Added `serialVersionUID` to SqlFunctionException (Issue 3.1)
- ✅ Fixed CircuitBreaker test cleanup using `resetAll()` (Issue 3.4)

**Optional (Nice-to-have):**
- ✅ Made RESET_TIMEOUT_MS configurable via `migration.circuit.reset.timeout.ms` (Issue 3.2)
- ✅ Added memory growth documentation in CircuitBreaker javadoc (Issue 2.3)
- ✅ Verified trunc() return types match PostgreSQL signatures (Issue 3.6)
- ✅ Added empty ResultSet warning log to callGetDate() (Issue 2.5)
- ✅ Added null params handling test (Issue 2.7)

---

**Next:** [Part 3: DateTime Functions](wave0-part3-datetime-functions.md) (Tasks 7-10)
