# Wave 0: Foundation Utilities Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Migrate 7 PostgreSQL foundation utility functions to Java, establishing the migration infrastructure pattern for subsequent waves.

**Architecture:** Shadow mode at 100% sampling for all functions (low frequency < 100 calls/day). Functions implemented as static methods in existing utility classes (`TimeUtil`, new `SqlCompat`). Async logging to `migration.function_log` table. All functions are stateless and simple.

**Tech Stack:** Java 11+, ADempiere `Query` class, PostgreSQL JDBC for shadow comparisons, JUnit 5 for testing.

---

## Transaction Unit: TU-001 Foundation Utilities

**Functions in Unit:** 7
**Validation Strategy:** Shadow Mode at 100%
**Performance Tier:** Standard (30% max latency increase)

| Priority | Function | SQL File | Java Location | LOC | Complexity | Overloads Covered |
|----------|----------|----------|---------------|-----|------------|-------------------|
| 1 | getDate | getDate.sql | TimeUtil.getDate() | 7 | Trivial | All |
| 2 | daysBetween | daysBetween.sql | TimeUtil.daysBetweenSql() | 29 | Low | All |
| 3 | addDays (2 of 4) | addDays.sql | TimeUtil.addDaysSql() | 34 | Low | TIMESTAMP only |
| 4 | trunc (3 overloads) | trunc.sql | TimeUtil.truncSql() | 28 | Low | All |
| 5 | round | round.sql | SqlCompat.round() | 10 | Trivial | All |
| 6 | firstOf | firstOf.sql | TimeUtil.firstOf() | 77 | Low | All |
| 7 | charAt | charAt.sql | SqlCompat.charAt() | 35 | Low | All |

---

## Scope Exclusions

The following are explicitly **out of scope** for Wave 0:

### Interval Overloads (addDays/subtractDays)

The SQL file `addDays.sql` defines 4 overloads:

| Overload | In Scope | Rationale |
|----------|----------|-----------|
| `addDays(TIMESTAMP, Numeric) → DATE` | ✅ Yes | Primary usage pattern |
| `subtractDays(TIMESTAMP, Numeric) → DATE` | ✅ Yes | Primary usage pattern |
| `addDays(INTERVAL, Numeric) → INTEGER` | ❌ No | No usages found in codebase |
| `subtractDays(INTERVAL, Numeric) → INTEGER` | ❌ No | No usages found in codebase |

**Verification required:** Before implementation, run:
```sql
-- Search for interval overload usage
SELECT * FROM pg_stat_user_functions
WHERE funcname IN ('adddays', 'subtractdays')
  AND calls > 0;
```

If interval overloads have non-zero calls, add to Wave 1.

### Fractional Days

`addDaysSql()` and `subtractDaysSql()` reject fractional day values with `IllegalArgumentException`. The SQL function returns `DATE` which truncates time anyway, so fractional days would be silently lossy. Failing fast is preferred.

---

## Pre-Implementation: Infrastructure Setup

### Task 0: Create Migration Schema and Configuration Table

**Files:**
- Create: `db/ddlutils/postgresql/migrations/001_create_migration_schema.sql`
- Create: `base/src/org/compiere/migration/MigrationConfig.java`
- Create: `base/src/org/compiere/migration/MigrationMode.java`

**Step 0: Create migrations directory**

```bash
mkdir -p db/ddlutils/postgresql/migrations
```

**Note:** This directory is for migration-specific DDL. These scripts should be run manually during deployment or integrated with your existing DDL deployment process.

**Step 1: Write the migration schema DDL**

```sql
-- db/ddlutils/postgresql/migrations/001_create_migration_schema.sql
CREATE SCHEMA IF NOT EXISTS migration;

CREATE TABLE migration.function_config (
    function_name VARCHAR(100) PRIMARY KEY,
    mode VARCHAR(20) NOT NULL DEFAULT 'SQL_ONLY',
    performance_tier VARCHAR(20) DEFAULT 'STANDARD',
    sql_baseline_p95_ms INT,
    sample_rate DECIMAL(5,4) DEFAULT 1.0,
    circuit_breaker_enabled BOOLEAN DEFAULT true,
    tolerance_config JSONB DEFAULT '{"timestamp_tolerance_ms": 1000, "date_compare_by_string": true, "null_empty_equivalent": true}',
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE migration.function_log (
    id SERIAL PRIMARY KEY,
    function_name VARCHAR(100) NOT NULL,
    input_params JSONB,
    sql_result TEXT,
    java_result TEXT,
    sql_time_ms INT,
    java_time_ms INT,
    is_match BOOLEAN,
    mismatch_reason VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE INDEX idx_function_log_name_created
ON migration.function_log(function_name, created_at);

CREATE INDEX idx_function_log_mismatches
ON migration.function_log(function_name, created_at) WHERE NOT is_match;

-- Insert Wave 0 function configurations
INSERT INTO migration.function_config (function_name, mode, performance_tier, sample_rate)
VALUES
    ('getDate', 'SQL_ONLY', 'STANDARD', 1.0),
    ('daysBetween', 'SQL_ONLY', 'STANDARD', 1.0),
    ('addDays', 'SQL_ONLY', 'STANDARD', 1.0),
    ('subtractDays', 'SQL_ONLY', 'STANDARD', 1.0),
    ('trunc', 'SQL_ONLY', 'STANDARD', 1.0),
    ('round', 'SQL_ONLY', 'STANDARD', 1.0),
    ('firstOf', 'SQL_ONLY', 'STANDARD', 1.0),
    ('charAt', 'SQL_ONLY', 'STANDARD', 1.0);
```

**Step 2: Run DDL to verify it works**

Run: `psql -d adempiere -f db/ddlutils/postgresql/migrations/001_create_migration_schema.sql`
Expected: Schema and tables created without errors

**Step 3: Write MigrationMode enum**

```java
// base/src/org/compiere/migration/MigrationMode.java
package org.compiere.migration;

/**
 * Migration modes for function cutover.
 * DUAL_WRITE intentionally omitted - not needed for stateless Wave 0 functions.
 * Can be added in Wave 2+ if stateful functions require replay capability.
 */
public enum MigrationMode {
    SQL_ONLY,    // Legacy path only
    SHADOW,      // Both execute, compare, return Java
    JAVA_ONLY    // Java only, SQL can be deleted
}
```

**Step 4: Write MigrationConfig class**

```java
// base/src/org/compiere/migration/MigrationConfig.java
package org.compiere.migration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MigrationConfig {
    private static final CLogger log = CLogger.getCLogger(MigrationConfig.class);
    private static final ConcurrentHashMap<String, CachedConfig> cache = new ConcurrentHashMap<>();
    private static final long CACHE_TTL_MS = 60_000; // 60 seconds

    private final String functionName;
    private final MigrationMode mode;
    private final double sampleRate;
    private final boolean circuitBreakerEnabled;

    static {
        validateTimezoneAlignment();
    }

    private static void validateTimezoneAlignment() {
        String sql = "SELECT current_setting('TIMEZONE')";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String pgTimezone = rs.getString(1);
                String jvmTimezone = java.util.TimeZone.getDefault().getID();
                if (!pgTimezone.equals(jvmTimezone)) {
                    log.warning("Timezone mismatch: PostgreSQL=" + pgTimezone +
                               ", JVM=" + jvmTimezone +
                               ". Shadow mode comparisons may show false mismatches.");
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to validate timezone alignment", e);
        }
    }

    private MigrationConfig(String functionName, MigrationMode mode, double sampleRate, boolean circuitBreakerEnabled) {
        this.functionName = functionName;
        this.mode = mode;
        this.sampleRate = sampleRate;
        this.circuitBreakerEnabled = circuitBreakerEnabled;
    }

    public static MigrationConfig get(String functionName) {
        return cache.compute(functionName, (k, v) -> {
            if (v != null && !v.isExpired()) return v;
            return new CachedConfig(loadFromDatabase(functionName));
        }).config;
    }

    private static MigrationConfig loadFromDatabase(String functionName) {
        String sql = "SELECT mode, sample_rate, circuit_breaker_enabled " +
                     "FROM migration.function_config WHERE function_name = ?";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setString(1, functionName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    MigrationMode mode = MigrationMode.valueOf(rs.getString("mode"));
                    double sampleRate = rs.getDouble("sample_rate");
                    boolean circuitBreaker = rs.getBoolean("circuit_breaker_enabled");
                    return new MigrationConfig(functionName, mode, sampleRate, circuitBreaker);
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to load migration config for " + functionName + ", defaulting to SQL_ONLY", e);
        }
        // Default: SQL_ONLY if not found or error
        return new MigrationConfig(functionName, MigrationMode.SQL_ONLY, 1.0, true);
    }

    public static MigrationConfig sqlOnly(String functionName) {
        return new MigrationConfig(functionName, MigrationMode.SQL_ONLY, 1.0, true);
    }

    public String getFunctionName() { return functionName; }
    public MigrationMode getMode() { return mode; }
    public double getSampleRate() { return sampleRate; }
    public boolean isCircuitBreakerEnabled() { return circuitBreakerEnabled; }

    private static class CachedConfig {
        final MigrationConfig config;
        final long cachedAt;

        CachedConfig(MigrationConfig config) {
            this.config = config;
            this.cachedAt = System.currentTimeMillis();
        }

        boolean isExpired() {
            return System.currentTimeMillis() - cachedAt > CACHE_TTL_MS;
        }
    }
}
```

**Step 5: Commit infrastructure setup**

```bash
git add db/ddlutils/postgresql/migrations/001_create_migration_schema.sql
git add base/src/org/compiere/migration/MigrationMode.java
git add base/src/org/compiere/migration/MigrationConfig.java
git commit -m "feat: add migration infrastructure schema and config

- Create migration schema with function_config and function_log tables
- Add MigrationMode enum (SQL_ONLY, SHADOW, JAVA_ONLY)
- Add MigrationConfig with 60s TTL cache, SQL_ONLY fallback, and timezone validation

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 1: Create Migration Logger

**Files:**
- Create: `base/src/org/compiere/migration/MigrationLogger.java`
- Test: `base/test/src/org/compiere/migration/MigrationLoggerTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/migration/MigrationLoggerTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
public class MigrationLoggerTest {

    @Test
    void testLogAsyncDoesNotBlock() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            MigrationLogger.logAsync("testFunction", "[\"input" + i + "\"]", "sqlResult", "javaResult", 10, 8, true, null);
        }

        long elapsed = System.currentTimeMillis() - start;
        // Should complete in under 100ms since it's async
        assertTrue(elapsed < 100, "logAsync should not block: took " + elapsed + "ms");
    }

    @Test
    void testQueueOverflowDoesNotBlock() {
        // Fill queue beyond capacity
        for (int i = 0; i < 15000; i++) {
            MigrationLogger.logAsync("overflow", "[\"input" + i + "\"]", "sql", "java", 1, 1, true, null);
        }
        // Should not throw or block
        assertTrue(MigrationLogger.getDroppedCount() > 0, "Some entries should be dropped on overflow");
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.MigrationLoggerTest" -i`
Expected: FAIL with "cannot find symbol: class MigrationLogger"

**Step 3: Write MigrationLogger implementation**

```java
// base/src/org/compiere/migration/MigrationLogger.java
package org.compiere.migration;

import java.sql.PreparedStatement;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MigrationLogger {
    private static final CLogger log = CLogger.getCLogger(MigrationLogger.class);
    private static final int QUEUE_CAPACITY = 10_000;
    private static final BlockingQueue<LogEntry> queue = new LinkedBlockingQueue<>(QUEUE_CAPACITY);
    private static final AtomicLong droppedCount = new AtomicLong(0);
    private static volatile boolean running = true;
    private static final Thread drainThread;

    static {
        drainThread = new Thread(MigrationLogger::drainQueue, "MigrationLogger-Drain");
        drainThread.setDaemon(true);
        drainThread.start();

        // Register shutdown hook to drain remaining entries
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            running = false;
            drainThread.interrupt();
            // Drain remaining entries with 5 second timeout
            long deadline = System.currentTimeMillis() + 5000;
            while (!queue.isEmpty() && System.currentTimeMillis() < deadline) {
                LogEntry entry = queue.poll();
                if (entry != null) {
                    writeToDatabase(entry);
                }
            }
            if (!queue.isEmpty()) {
                log.warning("MigrationLogger shutdown with " + queue.size() + " entries still pending");
            }
        }, "MigrationLogger-Shutdown"));
    }

    public static void logAsync(String functionName, String inputParams, String sqlResult,
                                 String javaResult, long sqlTimeMs, long javaTimeMs,
                                 boolean isMatch, String mismatchReason) {
        LogEntry entry = new LogEntry(functionName, inputParams, sqlResult, javaResult,
                                       sqlTimeMs, javaTimeMs, isMatch, mismatchReason);
        if (!queue.offer(entry)) {
            long dropped = droppedCount.incrementAndGet();
            if (dropped % 1000 == 0) {
                log.warning("Migration log queue full, " + dropped + " entries dropped total");
            }
        }
    }

    public static long getDroppedCount() {
        return droppedCount.get();
    }

    public static int getQueueDepth() {
        return queue.size();
    }

    private static void drainQueue() {
        while (running) {
            try {
                LogEntry entry = queue.take();
                writeToDatabase(entry);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                log.log(Level.WARNING, "Failed to write migration log entry", e);
            }
        }
    }

    private static void writeToDatabase(LogEntry entry) {
        String sql = "INSERT INTO migration.function_log " +
                     "(function_name, input_params, sql_result, java_result, " +
                     "sql_time_ms, java_time_ms, is_match, mismatch_reason) " +
                     "VALUES (?, ?::jsonb, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setString(1, entry.functionName);
            pstmt.setString(2, entry.inputParams);
            pstmt.setString(3, entry.sqlResult);
            pstmt.setString(4, entry.javaResult);
            pstmt.setLong(5, entry.sqlTimeMs);
            pstmt.setLong(6, entry.javaTimeMs);
            pstmt.setBoolean(7, entry.isMatch);
            pstmt.setString(8, entry.mismatchReason);
            pstmt.executeUpdate();
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to insert migration log for " + entry.functionName, e);
        }
    }

    public static void shutdown() {
        running = false;
    }

    private static class LogEntry {
        final String functionName;
        final String inputParams;
        final String sqlResult;
        final String javaResult;
        final long sqlTimeMs;
        final long javaTimeMs;
        final boolean isMatch;
        final String mismatchReason;

        LogEntry(String functionName, String inputParams, String sqlResult,
                 String javaResult, long sqlTimeMs, long javaTimeMs,
                 boolean isMatch, String mismatchReason) {
            this.functionName = functionName;
            this.inputParams = inputParams;
            this.sqlResult = sqlResult;
            this.javaResult = javaResult;
            this.sqlTimeMs = sqlTimeMs;
            this.javaTimeMs = javaTimeMs;
            this.isMatch = isMatch;
            this.mismatchReason = mismatchReason;
        }
    }
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.MigrationLoggerTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/MigrationLogger.java
git add base/test/src/org/compiere/migration/MigrationLoggerTest.java
git commit -m "feat: add async MigrationLogger with bounded queue

- 10K capacity bounded queue with drop-on-overflow
- Background daemon thread drains to migration.function_log
- Shutdown hook drains remaining entries with 5s timeout
- Non-blocking logAsync() for shadow mode comparisons

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 2: Create ParamSerializer

**Files:**
- Create: `base/src/org/compiere/migration/ParamSerializer.java`
- Test: `base/test/src/org/compiere/migration/ParamSerializerTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/migration/ParamSerializerTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
public class ParamSerializerTest {

    @Test
    void testEmptyParams() {
        assertEquals("[]", ParamSerializer.toJson());
    }

    @Test
    void testNullParams() {
        assertEquals("[]", ParamSerializer.toJson((Object[]) null));
    }

    @Test
    void testSingleString() {
        assertEquals("[\"hello\"]", ParamSerializer.toJson("hello"));
    }

    @Test
    void testTimestamp() {
        Timestamp ts = Timestamp.valueOf("2026-01-15 14:30:00");
        assertEquals("[\"2026-01-15 14:30:00.0\"]", ParamSerializer.toJson(ts));
    }

    @Test
    void testBigDecimal() {
        assertEquals("[123.456]", ParamSerializer.toJson(new BigDecimal("123.456")));
    }

    @Test
    void testMixedParams() {
        String result = ParamSerializer.toJson("hello", 42, null, true);
        assertEquals("[\"hello\",42,null,true]", result);
    }

    @Test
    void testEscapesSpecialChars() {
        String result = ParamSerializer.toJson("line1\nline2\ttab\"quote");
        assertEquals("[\"line1\\nline2\\ttab\\\"quote\"]", result);
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.ParamSerializerTest" -i`
Expected: FAIL with "cannot find symbol: class ParamSerializer"

**Step 3: Write ParamSerializer implementation**

```java
// base/src/org/compiere/migration/ParamSerializer.java
package org.compiere.migration;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Simple JSON serialization for shadow mode parameter logging.
 * Avoids external dependencies - produces valid JSON for common types.
 */
public class ParamSerializer {

    public static String toJson(Object... params) {
        if (params == null || params.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < params.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(serializeValue(params[i]));
        }
        sb.append("]");
        return sb.toString();
    }

    private static String serializeValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "\"" + escapeJson((String) value) + "\"";
        } else if (value instanceof Timestamp) {
            return "\"" + value.toString() + "\"";
        } else if (value instanceof Date) {
            return "\"" + value.toString() + "\"";
        } else if (value instanceof BigDecimal) {
            return ((BigDecimal) value).toPlainString();
        } else if (value instanceof Number) {
            return value.toString();
        } else if (value instanceof Boolean) {
            return value.toString();
        } else {
            return "\"" + escapeJson(value.toString()) + "\"";
        }
    }

    private static String escapeJson(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.ParamSerializerTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/ParamSerializer.java
git add base/test/src/org/compiere/migration/ParamSerializerTest.java
git commit -m "feat: add ParamSerializer for shadow mode input logging

- Simple JSON serialization without external dependencies
- Handles Timestamp, Date, BigDecimal, String, Number, Boolean, null
- Escapes special characters in strings

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 3: Create Comparators for Shadow Mode

**Files:**
- Create: `base/src/org/compiere/migration/comparators/TimestampComparator.java`
- Create: `base/src/org/compiere/migration/comparators/DateComparator.java`
- Test: `base/test/src/org/compiere/migration/comparators/ComparatorTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/migration/comparators/ComparatorTest.java
package org.compiere.migration.comparators;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
public class ComparatorTest {

    @Test
    void testTimestampWithinTolerance() {
        TimestampComparator comp = TimestampComparator.withDefaultTolerance();
        Timestamp t1 = new Timestamp(1000000);
        Timestamp t2 = new Timestamp(1000500); // 500ms difference

        assertTrue(comp.test(t1, t2));
    }

    @Test
    void testTimestampOutsideTolerance() {
        TimestampComparator comp = TimestampComparator.withDefaultTolerance();
        Timestamp t1 = new Timestamp(1000000);
        Timestamp t2 = new Timestamp(1002000); // 2000ms difference

        assertFalse(comp.test(t1, t2));
    }

    @Test
    void testTimestampExactMatch() {
        TimestampComparator comp = TimestampComparator.exact();
        Timestamp t1 = new Timestamp(1000000);
        Timestamp t2 = new Timestamp(1000000);

        assertTrue(comp.test(t1, t2));
    }

    @Test
    void testTimestampNullHandling() {
        TimestampComparator comp = TimestampComparator.withDefaultTolerance();

        assertTrue(comp.test(null, null));
        assertFalse(comp.test(null, new Timestamp(1000)));
        assertFalse(comp.test(new Timestamp(1000), null));
    }

    @Test
    void testDateComparator() {
        DateComparator comp = DateComparator.INSTANCE;

        Date d1 = Date.valueOf("2026-01-15");
        Date d2 = Date.valueOf("2026-01-15");
        Date d3 = Date.valueOf("2026-01-16");

        assertTrue(comp.test(d1, d2));
        assertFalse(comp.test(d1, d3));
    }

    @Test
    void testDateNullHandling() {
        DateComparator comp = DateComparator.INSTANCE;

        assertTrue(comp.test(null, null));
        assertFalse(comp.test(null, Date.valueOf("2026-01-15")));
        assertFalse(comp.test(Date.valueOf("2026-01-15"), null));
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.ComparatorTest" -i`
Expected: FAIL with "cannot find symbol"

**Step 3: Write comparator implementations**

```java
// base/src/org/compiere/migration/comparators/TimestampComparator.java
package org.compiere.migration.comparators;

import java.sql.Timestamp;
import java.util.function.BiPredicate;

/**
 * Compares timestamps with timezone-safe logic and configurable tolerance.
 * Uses epoch millis for comparison, which is timezone-agnostic.
 */
public class TimestampComparator implements BiPredicate<Timestamp, Timestamp> {

    private final long toleranceMs;

    public TimestampComparator(long toleranceMs) {
        this.toleranceMs = toleranceMs;
    }

    /** Default: 1 second tolerance for getDate() style comparisons */
    public static TimestampComparator withDefaultTolerance() {
        return new TimestampComparator(1000);
    }

    /** Exact match (0 tolerance) for deterministic functions */
    public static TimestampComparator exact() {
        return new TimestampComparator(0);
    }

    @Override
    public boolean test(Timestamp java, Timestamp sql) {
        if (java == null && sql == null) return true;
        if (java == null || sql == null) return false;

        // Compare as UTC epoch millis - timezone agnostic
        long diff = Math.abs(java.getTime() - sql.getTime());
        return diff <= toleranceMs;
    }
}
```

```java
// base/src/org/compiere/migration/comparators/DateComparator.java
package org.compiere.migration.comparators;

import java.sql.Date;
import java.util.function.BiPredicate;

/**
 * Compares dates by calendar date (year, month, day), ignoring timezone.
 * Uses string representation to avoid timezone-at-midnight issues.
 */
public class DateComparator implements BiPredicate<Date, Date> {

    public static final DateComparator INSTANCE = new DateComparator();

    @Override
    public boolean test(Date java, Date sql) {
        if (java == null && sql == null) return true;
        if (java == null || sql == null) return false;

        // Compare string representation (YYYY-MM-DD) to avoid timezone issues
        return java.toString().equals(sql.toString());
    }
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.ComparatorTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/comparators/TimestampComparator.java
git add base/src/org/compiere/migration/comparators/DateComparator.java
git add base/test/src/org/compiere/migration/comparators/ComparatorTest.java
git commit -m "feat: add timezone-safe comparators for shadow mode

- TimestampComparator with configurable tolerance (default 1s)
- DateComparator uses string comparison to avoid timezone issues
- Both handle null values correctly

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 4: Create CircuitBreaker

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
        CircuitBreaker.reset("testFunc");
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

**Step 3: Write CircuitBreaker implementation**

```java
// base/src/org/compiere/migration/CircuitBreaker.java
package org.compiere.migration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Per-function circuit breaker to prevent cascading failures during SQL outages.
 * Opens after FAILURE_THRESHOLD consecutive failures, resets after RESET_TIMEOUT_MS.
 */
public class CircuitBreaker {

    private static final int FAILURE_THRESHOLD = 5;
    private static final long RESET_TIMEOUT_MS = 60_000; // 1 minute

    private static final ConcurrentHashMap<String, CircuitState> circuits = new ConcurrentHashMap<>();

    public static boolean isOpen(String functionName) {
        CircuitState state = circuits.get(functionName);
        if (state == null) return false;

        if (state.isOpen()) {
            // Check if reset timeout has passed
            if (System.currentTimeMillis() - state.openedAt.get() > RESET_TIMEOUT_MS) {
                state.reset();
                return false;
            }
            return true;
        }
        return false;
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
            state.open();
        }
    }

    /** Reset circuit state for a function (used in testing) */
    public static void reset(String functionName) {
        circuits.remove(functionName);
    }

    private static class CircuitState {
        final AtomicInteger failures = new AtomicInteger(0);
        final AtomicLong openedAt = new AtomicLong(0);

        boolean isOpen() {
            return openedAt.get() > 0;
        }

        void open() {
            openedAt.compareAndSet(0, System.currentTimeMillis());
        }

        void reset() {
            openedAt.set(0);
            failures.set(0);
        }
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

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 5: Create SqlFunctionCaller

**Files:**
- Create: `base/src/org/compiere/migration/SqlFunctionCaller.java`
- Test: `base/test/src/org/compiere/migration/SqlFunctionCallerTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/migration/SqlFunctionCallerTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

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
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.SqlFunctionCallerTest" -PintegrationTest -i`
Expected: FAIL with "cannot find symbol: class SqlFunctionCaller"

**Step 3: Write SqlFunctionCaller implementation**

```java
// base/src/org/compiere/migration/SqlFunctionCaller.java
package org.compiere.migration;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Calls PostgreSQL functions for shadow mode comparison.
 * Each method wraps a SELECT call to the SQL function.
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
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to call getDate()", e);
        }
        return null;
    }

    /** Calls: SELECT daysBetween(?, ?) */
    public static Integer callDaysBetween(Timestamp date1, Timestamp date2) {
        String sql = "SELECT daysBetween(?, ?)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setTimestamp(1, date1);
            pstmt.setTimestamp(2, date2);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to call daysBetween()", e);
        }
        return null;
    }

    /** Calls: SELECT addDays(?, ?) - TIMESTAMP, Numeric -> DATE */
    public static Date callAddDays(Timestamp datetime, BigDecimal days) {
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
        }
        return null;
    }

    /** Calls: SELECT subtractDays(?, ?) - TIMESTAMP, Numeric -> DATE */
    public static Date callSubtractDays(Timestamp datetime, BigDecimal days) {
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
        }
        return null;
    }

    /** Calls: SELECT trunc(?) - TIMESTAMP -> TIMESTAMP */
    public static Timestamp callTrunc(Timestamp datetime) {
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
        }
        return null;
    }

    /** Calls: SELECT trunc(?, ?) - TIMESTAMP, format -> DATE */
    public static Date callTrunc(Timestamp datetime, String format) {
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
        }
        return null;
    }

    /** Calls: SELECT round(?, ?) - NUMERIC, INTEGER -> NUMERIC */
    public static BigDecimal callRound(BigDecimal value, int scale) {
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
        }
        return null;
    }

    /** Calls: SELECT firstOf(?, ?) - TIMESTAMP, VARCHAR -> DATE */
    public static Date callFirstOf(Timestamp datetime, String datePart) {
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
        }
        return null;
    }

    /** Calls: SELECT charAt(?, ?) - VARCHAR, INTEGER -> VARCHAR */
    public static String callCharAt(String str, int position) {
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
- Used by ShadowExecutor to call legacy SQL for comparison

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 6: Create ShadowExecutor

**Files:**
- Create: `base/src/org/compiere/migration/ShadowExecutor.java`
- Test: `base/test/src/org/compiere/migration/ShadowExecutorTest.java`

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
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.ShadowExecutorTest" -i`
Expected: FAIL with "cannot find symbol: class ShadowExecutor"

**Step 3: Write ShadowExecutor implementation**

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
 */
public class ShadowExecutor {
    private static final CLogger log = CLogger.getCLogger(ShadowExecutor.class);

    /**
     * Execute with automatic config lookup.
     */
    public static <T> T execute(String functionName,
                                 Object[] params,
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
                                 Object[] params,
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

        // Execute SQL
        long sqlStart = System.nanoTime();
        T sqlResult = null;
        long sqlTimeMs = 0;
        try {
            sqlResult = sqlPath.get();
            sqlTimeMs = (System.nanoTime() - sqlStart) / 1_000_000;
            if (circuitBreakerEnabled) {
                CircuitBreaker.recordSuccess(functionName);
            }
        } catch (Exception e) {
            if (circuitBreakerEnabled) {
                CircuitBreaker.recordFailure(functionName);
            }
            log.log(Level.WARNING, "Shadow SQL execution failed for " + functionName, e);
            MigrationLogger.logAsync(functionName, serializedParams, "", String.valueOf(javaResult),
                                      0, javaTimeMs, false, "SQL_EXCEPTION: " + e.getMessage());
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
- Integrates CircuitBreaker for resilience
- Sampling support for high-frequency functions
- Async logging with serialized input parameters

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Function Implementations

### Task 7: Implement getDate()

**Files:**
- Modify: `base/src/org/compiere/util/TimeUtil.java` (add getDate() method)
- Test: `base/test/src/org/compiere/util/TimeUtilGetDateTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/util/TimeUtilGetDateTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
public class TimeUtilGetDateTest {

    @Test
    void testGetDateReturnsCurrentTimestamp() {
        long before = System.currentTimeMillis();
        Timestamp result = TimeUtil.getDate();
        long after = System.currentTimeMillis();

        assertNotNull(result);
        assertTrue(result.getTime() >= before, "Should be >= start time");
        assertTrue(result.getTime() <= after, "Should be <= end time");
    }

    @Test
    void testGetDateIsNotTruncated() {
        Timestamp result = TimeUtil.getDate();
        // Unlike getDay(), getDate() should NOT truncate to midnight
        // It returns now() which includes time component
        // This is the SQL behavior: SELECT now()
        assertNotNull(result);
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.TimeUtilGetDateTest" -i`
Expected: FAIL with "cannot find symbol: method getDate()"

**Step 3: Add getDate() method to TimeUtil**

Add after line 63 in TimeUtil.java:

```java
	/**
	 * Get current timestamp (equivalent to PostgreSQL getDate() function).
	 * Unlike getDay(), this returns the full timestamp with time component.
	 * @return current timestamp
	 */
	static public Timestamp getDate() {
		return new Timestamp(System.currentTimeMillis());
	}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.TimeUtilGetDateTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/TimeUtil.java
git add base/test/src/org/compiere/util/TimeUtilGetDateTest.java
git commit -m "feat: add TimeUtil.getDate() for SQL getDate() migration

- Returns current timestamp (equivalent to PostgreSQL now())
- Distinct from getDay() which truncates to midnight

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 8: Implement daysBetween() with SQL matching semantics

**Files:**
- Modify: `base/src/org/compiere/util/TimeUtil.java` (add daysBetweenSql)
- Test: `base/test/src/org/compiere/util/TimeUtilDaysBetweenSqlTest.java`

**Step 1: Write the failing test matching SQL semantics**

```java
// base/test/src/org/compiere/util/TimeUtilDaysBetweenSqlTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for SQL-compatible daysBetween calculation.
 * SQL: CAST(p_date1 AS DATE) - CAST(p_date2 as DATE)
 */
@Tag("UnitTest")
public class TimeUtilDaysBetweenSqlTest {

    @Test
    void testSameDayDifferentTimes() {
        Timestamp date1 = Timestamp.valueOf("2026-01-15 23:59:59");
        Timestamp date2 = Timestamp.valueOf("2026-01-15 00:00:00");

        // SQL: CAST('2026-01-15' AS DATE) - CAST('2026-01-15' AS DATE) = 0
        assertEquals(0, TimeUtil.daysBetweenSql(date1, date2));
    }

    @Test
    void testDate1AfterDate2() {
        Timestamp date1 = Timestamp.valueOf("2026-01-10 12:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-01 08:00:00");

        // SQL: 2026-01-10 - 2026-01-01 = 9
        assertEquals(9, TimeUtil.daysBetweenSql(date1, date2));
    }

    @Test
    void testDate1BeforeDate2() {
        Timestamp date1 = Timestamp.valueOf("2026-01-01 08:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-10 12:00:00");

        // SQL: 2026-01-01 - 2026-01-10 = -9
        assertEquals(-9, TimeUtil.daysBetweenSql(date1, date2));
    }

    @Test
    void testNullDate1ReturnsNull() {
        Timestamp date2 = Timestamp.valueOf("2026-01-01 00:00:00");
        assertNull(TimeUtil.daysBetweenSql(null, date2));
    }

    @Test
    void testNullDate2ReturnsNull() {
        Timestamp date1 = Timestamp.valueOf("2026-01-01 00:00:00");
        assertNull(TimeUtil.daysBetweenSql(date1, null));
    }

    @Test
    void testBothNullReturnsNull() {
        assertNull(TimeUtil.daysBetweenSql(null, null));
    }

    @Test
    void testCrossYearBoundary() {
        Timestamp date1 = Timestamp.valueOf("2027-01-01 00:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-12-31 00:00:00");

        assertEquals(1, TimeUtil.daysBetweenSql(date1, date2));
    }

    @Test
    void testDstTransition() {
        // 2026 DST starts March 8 in US
        Timestamp date1 = Timestamp.valueOf("2026-03-09 12:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-03-07 12:00:00");

        // Should be 2 days regardless of DST (calendar days, not 24-hour periods)
        assertEquals(2, TimeUtil.daysBetweenSql(date1, date2));
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.TimeUtilDaysBetweenSqlTest" -i`
Expected: FAIL with "cannot find symbol: method daysBetweenSql"

**Step 3: Add daysBetweenSql() method to TimeUtil**

Add required imports at top of TimeUtil.java:
```java
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
```

Add after getDate() method:

```java
	/**
	 * Calculate days between two dates using SQL semantics.
	 * Equivalent to PostgreSQL: CAST(p_date1 AS DATE) - CAST(p_date2 AS DATE)
	 *
	 * Uses java.time API which correctly handles DST transitions
	 * (counts calendar days, not 24-hour periods).
	 *
	 * @param date1 first date (minuend)
	 * @param date2 second date (subtrahend)
	 * @return difference in days (date1 - date2), or null if either input is null
	 */
	static public Integer daysBetweenSql(Timestamp date1, Timestamp date2) {
		if (date1 == null || date2 == null) {
			return null;
		}

		// Use java.time API which handles DST correctly
		LocalDate ld1 = date1.toLocalDateTime().toLocalDate();
		LocalDate ld2 = date2.toLocalDateTime().toLocalDate();
		return (int) ChronoUnit.DAYS.between(ld2, ld1);
	}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.TimeUtilDaysBetweenSqlTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/TimeUtil.java
git add base/test/src/org/compiere/util/TimeUtilDaysBetweenSqlTest.java
git commit -m "feat: add TimeUtil.daysBetweenSql() for SQL daysBetween migration

- Matches SQL semantics: CAST(date1 AS DATE) - CAST(date2 AS DATE)
- Uses ChronoUnit.DAYS.between for correct DST handling
- Returns null for null inputs (unlike existing getDaysBetween)

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 9: Implement addDays/subtractDays with SQL semantics

**Files:**
- Modify: `base/src/org/compiere/util/TimeUtil.java`
- Test: `base/test/src/org/compiere/util/TimeUtilAddDaysSqlTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/util/TimeUtilAddDaysSqlTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for SQL-compatible addDays/subtractDays.
 * SQL: cast(date_trunc('day',datetime) + cast(days || ' day' as interval) as date)
 */
@Tag("UnitTest")
public class TimeUtilAddDaysSqlTest {

    @Test
    void testAddDaysPositive() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("5");

        Date result = TimeUtil.addDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-01-20"), result);
    }

    @Test
    void testAddDaysNegative() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("-5");

        Date result = TimeUtil.addDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-01-10"), result);
    }

    @Test
    void testAddDaysZero() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = BigDecimal.ZERO;

        Date result = TimeUtil.addDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-01-15"), result);
    }

    @Test
    void testAddDaysNullDatetime() {
        assertNull(TimeUtil.addDaysSql(null, new BigDecimal("5")));
    }

    @Test
    void testAddDaysNullDays() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        assertNull(TimeUtil.addDaysSql(datetime, null));
    }

    @Test
    void testSubtractDays() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("5");

        Date result = TimeUtil.subtractDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-01-10"), result);
    }

    @Test
    void testAddDaysCrossMonth() {
        Timestamp datetime = Timestamp.valueOf("2026-01-30 00:00:00");
        BigDecimal days = new BigDecimal("5");

        Date result = TimeUtil.addDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-02-04"), result);
    }

    @Test
    void testAddDaysFractionalThrows() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("1.5");

        assertThrows(IllegalArgumentException.class,
            () -> TimeUtil.addDaysSql(datetime, days));
    }

    @Test
    void testAddDaysWholeNumberWithScaleWorks() {
        // 5.00 should work (trailing zeros)
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("5.00");

        Date result = TimeUtil.addDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-01-20"), result);
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.TimeUtilAddDaysSqlTest" -i`
Expected: FAIL with "cannot find symbol: method addDaysSql"

**Step 3: Add addDaysSql() and subtractDaysSql() methods**

Add required imports at top of TimeUtil.java if not already present:
```java
import java.sql.Date;
import java.math.BigDecimal;
```

Add after daysBetweenSql():

```java
	/**
	 * Add days to timestamp, returning a Date (SQL semantics).
	 * Equivalent to PostgreSQL: cast(date_trunc('day',datetime) + cast(days || ' day' as interval) as date)
	 *
	 * @param datetime timestamp to add to
	 * @param days number of days to add (must be whole number)
	 * @return resulting date, or null if either input is null
	 * @throws IllegalArgumentException if days has fractional component
	 */
	static public Date addDaysSql(Timestamp datetime, BigDecimal days) {
		if (datetime == null || days == null) {
			return null;
		}

		// Validate no fractional days - SQL returns DATE so fractions are lost anyway
		BigDecimal stripped = days.stripTrailingZeros();
		if (stripped.scale() > 0) {
			throw new IllegalArgumentException(
				"Fractional days not supported: " + days +
				". SQL addDays returns DATE which truncates time component.");
		}

		LocalDate date = datetime.toLocalDateTime().toLocalDate();
		LocalDate result = date.plusDays(days.longValue());
		return Date.valueOf(result);
	}

	/**
	 * Subtract days from timestamp, returning a Date (SQL semantics).
	 * Equivalent to PostgreSQL: subtractDays(day, days) which calls addDays(day, days * -1)
	 *
	 * @param datetime timestamp to subtract from
	 * @param days number of days to subtract
	 * @return resulting date, or null if either input is null
	 */
	static public Date subtractDaysSql(Timestamp datetime, BigDecimal days) {
		if (days == null) {
			return null;
		}
		return addDaysSql(datetime, days.negate());
	}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.TimeUtilAddDaysSqlTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/TimeUtil.java
git add base/test/src/org/compiere/util/TimeUtilAddDaysSqlTest.java
git commit -m "feat: add TimeUtil.addDaysSql/subtractDaysSql for SQL migration

- Matches SQL: date_trunc('day', datetime) + interval
- Returns java.sql.Date (not Timestamp)
- Rejects fractional days with IllegalArgumentException
- Returns null for null inputs

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 10: Implement trunc() with SQL format codes

**Files:**
- Modify: `base/src/org/compiere/util/TimeUtil.java`
- Test: `base/test/src/org/compiere/util/TimeUtilTruncSqlTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/util/TimeUtilTruncSqlTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for SQL-compatible trunc() with format codes.
 */
@Tag("UnitTest")
public class TimeUtilTruncSqlTest {

    @Test
    void testTruncToDay() {
        Timestamp datetime = Timestamp.valueOf("2026-03-15 14:30:45");
        Timestamp result = TimeUtil.truncSql(datetime);

        // Should truncate to midnight
        assertEquals(Timestamp.valueOf("2026-03-15 00:00:00"), result);
    }

    @Test
    void testTruncToQuarter() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");
        Date result = TimeUtil.truncSql(datetime, "Q");

        // Q2 starts April 1
        assertEquals(Date.valueOf("2026-04-01"), result);
    }

    @Test
    void testTruncToYear() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.truncSql(datetime, "Y"));
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.truncSql(datetime, "YEAR"));
    }

    @Test
    void testTruncToMonth() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.truncSql(datetime, "MM"));
        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.truncSql(datetime, "MONTH"));
    }

    @Test
    void testTruncToDayFormat() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.truncSql(datetime, "DD"));
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.truncSql(datetime, "DY"));
    }

    @Test
    void testTruncUnknownFormatDefaultsToDate() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");
        Date result = TimeUtil.truncSql(datetime, "UNKNOWN");

        assertEquals(Date.valueOf("2026-05-15"), result);
    }

    @Test
    void testTruncNullReturnsNull() {
        assertNull(TimeUtil.truncSql(null));
        assertNull(TimeUtil.truncSql(null, "Q"));
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.TimeUtilTruncSqlTest" -i`
Expected: FAIL with "cannot find symbol: method truncSql"

**Step 3: Add truncSql() methods**

```java
	/**
	 * Truncate timestamp to date (SQL semantics).
	 * Equivalent to PostgreSQL: CAST(datetime AS DATE)
	 *
	 * @param datetime timestamp to truncate
	 * @return truncated timestamp at midnight, or null if input is null
	 */
	static public Timestamp truncSql(Timestamp datetime) {
		if (datetime == null) {
			return null;
		}

		LocalDate date = datetime.toLocalDateTime().toLocalDate();
		return Timestamp.valueOf(date.atStartOfDay());
	}

	/**
	 * Truncate timestamp to specified date part (SQL semantics).
	 * Equivalent to PostgreSQL: trunc(datetime, format)
	 *
	 * Supported formats:
	 * - Q: Quarter
	 * - Y, YEAR: Year
	 * - MM, MONTH: Month
	 * - DD, DY: Day
	 *
	 * @param datetime timestamp to truncate
	 * @param format format code
	 * @return truncated date, or null if datetime is null
	 */
	static public Date truncSql(Timestamp datetime, String format) {
		if (datetime == null) {
			return null;
		}

		LocalDate date = datetime.toLocalDateTime().toLocalDate();
		LocalDate result;

		if ("Q".equals(format)) {
			// Quarter: truncate to first day of quarter
			int quarterMonth = ((date.getMonthValue() - 1) / 3) * 3 + 1;
			result = date.withMonth(quarterMonth).withDayOfMonth(1);
		} else if ("Y".equals(format) || "YEAR".equals(format)) {
			result = date.withDayOfYear(1);
		} else if ("MM".equals(format) || "MONTH".equals(format)) {
			result = date.withDayOfMonth(1);
		} else {
			// DD, DY, or unknown: just truncate to day
			result = date;
		}

		return Date.valueOf(result);
	}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.TimeUtilTruncSqlTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/TimeUtil.java
git add base/test/src/org/compiere/util/TimeUtilTruncSqlTest.java
git commit -m "feat: add TimeUtil.truncSql() for SQL trunc migration

- Single-arg version truncates to midnight
- Two-arg version supports Q, Y, YEAR, MM, MONTH, DD, DY formats
- Uses java.time for clean implementation
- Returns null for null inputs

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 11: Create SqlCompat class with round() and charAt()

**Files:**
- Create: `base/src/org/compiere/util/SqlCompat.java`
- Test: `base/test/src/org/compiere/util/SqlCompatTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/util/SqlCompatTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for SQL-compatible utility functions.
 */
@Tag("UnitTest")
public class SqlCompatTest {

    // round() tests
    @Test
    void testRoundPositiveScale() {
        BigDecimal value = new BigDecimal("123.456789");

        assertEquals(new BigDecimal("123.46"), SqlCompat.round(value, 2));
    }

    @Test
    void testRoundZeroScale() {
        BigDecimal value = new BigDecimal("123.456789");

        assertEquals(new BigDecimal("123"), SqlCompat.round(value, 0));
    }

    @Test
    void testRoundNegativeScale() {
        BigDecimal value = new BigDecimal("1234.56");

        // Rounds to nearest hundred
        assertEquals(new BigDecimal("1200"), SqlCompat.round(value, -2));
    }

    @Test
    void testRoundNullValue() {
        assertNull(SqlCompat.round(null, 2));
    }

    // charAt() tests
    @Test
    void testCharAtFirstPosition() {
        assertEquals("H", SqlCompat.charAt("Hello", 1));
    }

    @Test
    void testCharAtMiddlePosition() {
        assertEquals("l", SqlCompat.charAt("Hello", 3));
    }

    @Test
    void testCharAtLastPosition() {
        assertEquals("o", SqlCompat.charAt("Hello", 5));
    }

    @Test
    void testCharAtOutOfBounds() {
        // SQL SUBSTR returns empty string for out of bounds
        assertEquals("", SqlCompat.charAt("Hello", 10));
    }

    @Test
    void testCharAtZeroPosition() {
        // SQL positions are 1-based, 0 is invalid
        assertEquals("", SqlCompat.charAt("Hello", 0));
    }

    @Test
    void testCharAtNullString() {
        assertNull(SqlCompat.charAt(null, 1));
    }

    @Test
    void testCharAtEmptyString() {
        assertEquals("", SqlCompat.charAt("", 1));
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.SqlCompatTest" -i`
Expected: FAIL with "cannot find symbol: class SqlCompat"

**Step 3: Write SqlCompat implementation**

```java
// base/src/org/compiere/util/SqlCompat.java
package org.compiere.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * SQL-compatible utility functions for function migration.
 * These methods match PostgreSQL function semantics exactly.
 */
public class SqlCompat {

    /**
     * Round a numeric value to specified scale (SQL semantics).
     * Equivalent to PostgreSQL: ROUND($1, cast($2 as integer))
     *
     * @param value value to round
     * @param scale number of decimal places (can be negative)
     * @return rounded value, or null if value is null
     */
    public static BigDecimal round(BigDecimal value, int scale) {
        if (value == null) {
            return null;
        }

        if (scale >= 0) {
            return value.setScale(scale, RoundingMode.HALF_UP);
        } else {
            // Negative scale: round to 10s, 100s, etc.
            BigDecimal multiplier = BigDecimal.TEN.pow(-scale);
            BigDecimal divided = value.divide(multiplier, 0, RoundingMode.HALF_UP);
            return divided.multiply(multiplier);
        }
    }

    /**
     * Get character at position (SQL semantics, 1-based).
     * Equivalent to PostgreSQL: SUBSTR($1, $2, 1)
     *
     * @param str input string
     * @param position 1-based position
     * @return character at position, empty string if out of bounds, null if str is null
     */
    public static String charAt(String str, int position) {
        if (str == null) {
            return null;
        }

        // SQL positions are 1-based
        int index = position - 1;

        if (index < 0 || index >= str.length()) {
            return "";
        }

        return String.valueOf(str.charAt(index));
    }
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.SqlCompatTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/SqlCompat.java
git add base/test/src/org/compiere/util/SqlCompatTest.java
git commit -m "feat: add SqlCompat with round() and charAt() for SQL migration

- round(): matches PostgreSQL ROUND(value, scale), handles negative scale
- charAt(): matches PostgreSQL SUBSTR(str, pos, 1), 1-based indexing
- Both return null for null inputs

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 12: Implement firstOf() with Oracle-compatible format codes

**Files:**
- Modify: `base/src/org/compiere/util/TimeUtil.java`
- Test: `base/test/src/org/compiere/util/TimeUtilFirstOfTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/util/TimeUtilFirstOfTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.DayOfWeek;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for SQL-compatible firstOf() with Oracle date format codes.
 */
@Tag("UnitTest")
public class TimeUtilFirstOfTest {

    @Test
    void testFirstOfYear() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "YYYY"));
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "YEAR"));
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "YYY"));
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "YY"));
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "Y"));
    }

    @Test
    void testFirstOfQuarter() {
        // Q1: Jan-Mar
        assertEquals(Date.valueOf("2026-01-01"),
            TimeUtil.firstOf(Timestamp.valueOf("2026-02-15 00:00:00"), "Q"));
        // Q2: Apr-Jun
        assertEquals(Date.valueOf("2026-04-01"),
            TimeUtil.firstOf(Timestamp.valueOf("2026-05-15 00:00:00"), "Q"));
        // Q3: Jul-Sep
        assertEquals(Date.valueOf("2026-07-01"),
            TimeUtil.firstOf(Timestamp.valueOf("2026-08-15 00:00:00"), "Q"));
        // Q4: Oct-Dec
        assertEquals(Date.valueOf("2026-10-01"),
            TimeUtil.firstOf(Timestamp.valueOf("2026-11-15 00:00:00"), "Q"));
    }

    @Test
    void testFirstOfMonth() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.firstOf(datetime, "MONTH"));
        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.firstOf(datetime, "MON"));
        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.firstOf(datetime, "MM"));
        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.firstOf(datetime, "RM"));
    }

    @Test
    void testFirstOfWeek() {
        // 2026-01-15 is a Thursday. ISO week starts Monday = 2026-01-12
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:45");

        // IW uses ISO week (Monday start)
        assertEquals(Date.valueOf("2026-01-12"), TimeUtil.firstOf(datetime, "IW"));
        // W also uses week
        assertEquals(Date.valueOf("2026-01-12"), TimeUtil.firstOf(datetime, "W"));
    }

    @Test
    void testFirstOfDay() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "DDD"));
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "DD"));
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "J"));
    }

    @Test
    void testFirstOfDayOfWeek() {
        // DAY/DY/D: week start with -1 offset for Oracle compatibility
        // 2026-01-15 is Thursday, ISO Monday = 2026-01-12, -1 = 2026-01-11 (Sun)
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:45");

        Date result = TimeUtil.firstOf(datetime, "DAY");
        assertEquals(Date.valueOf("2026-01-11"), result);
    }

    @Test
    void testFirstOfHour() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        // Hour truncation returns date at that hour (but we return Date, so just the date)
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "HH"));
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "HH12"));
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "HH24"));
    }

    @Test
    void testFirstOfMinute() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "MI"));
    }

    @Test
    void testFirstOfNull() {
        assertNull(TimeUtil.firstOf(null, "YYYY"));
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.TimeUtilFirstOfTest" -i`
Expected: FAIL with "cannot find symbol: method firstOf"

**Step 3: Add firstOf() method**

Add required import at top of TimeUtil.java:
```java
import java.time.DayOfWeek;
```

Add after truncSql() methods:

```java
	/**
	 * Get first date of specified period (SQL/Oracle semantics).
	 * Equivalent to PostgreSQL firstOf() function with Oracle-compatible format codes.
	 * Uses java.time API for locale-independent week calculations.
	 *
	 * Supported formats:
	 * - IYYY, IY, I, SYYYY, YYYY, YEAR, SYEAR, YYY, YY, Y: First of year
	 * - Q: First of quarter
	 * - MONTH, MON, MM, RM: First of month
	 * - IW, W: First of week (ISO week, Monday start)
	 * - DDD, DD, J: Day (unchanged)
	 * - DAY, DY, D: First of week (Sunday start, Oracle compatible)
	 * - HH, HH12, HH24: Hour (returns date at that hour)
	 * - MI: Minute (returns date)
	 *
	 * @param datetime timestamp
	 * @param datePart format code
	 * @return first date of the period, or null if datetime is null
	 */
	static public Date firstOf(Timestamp datetime, String datePart) {
		if (datetime == null) {
			return null;
		}

		LocalDate date = datetime.toLocalDateTime().toLocalDate();
		LocalDate result;

		if (datePart == null || datePart.isEmpty()) {
			result = date;
		} else if ("IYYY".equals(datePart) || "IY".equals(datePart) || "I".equals(datePart) ||
				   "SYYYY".equals(datePart) || "YYYY".equals(datePart) || "YEAR".equals(datePart) ||
				   "SYEAR".equals(datePart) || "YYY".equals(datePart) || "YY".equals(datePart) ||
				   "Y".equals(datePart)) {
			// First of year
			result = date.withDayOfYear(1);
		} else if ("Q".equals(datePart)) {
			// First of quarter
			int quarterMonth = ((date.getMonthValue() - 1) / 3) * 3 + 1;
			result = date.withMonth(quarterMonth).withDayOfMonth(1);
		} else if ("MONTH".equals(datePart) || "MON".equals(datePart) ||
				   "MM".equals(datePart) || "RM".equals(datePart)) {
			// First of month
			result = date.withDayOfMonth(1);
		} else if ("IW".equals(datePart) || "W".equals(datePart)) {
			// ISO week (Monday start)
			result = date.with(DayOfWeek.MONDAY);
		} else if ("DAY".equals(datePart) || "DY".equals(datePart) || "D".equals(datePart)) {
			// Oracle week (Sunday start) = ISO Monday - 1
			result = date.with(DayOfWeek.MONDAY).minusDays(1);
		} else if ("DDD".equals(datePart) || "DD".equals(datePart) || "J".equals(datePart)) {
			// Day - no change
			result = date;
		} else if ("HH".equals(datePart) || "HH12".equals(datePart) || "HH24".equals(datePart) ||
				   "MI".equals(datePart)) {
			// Hour/Minute - just return the date
			result = date;
		} else {
			// Unknown format - return date as-is
			result = date;
		}

		return Date.valueOf(result);
	}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.TimeUtilFirstOfTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/TimeUtil.java
git add base/test/src/org/compiere/util/TimeUtilFirstOfTest.java
git commit -m "feat: add TimeUtil.firstOf() for SQL firstOf migration

- Supports Oracle-compatible format codes (YYYY, Q, MONTH, etc.)
- Uses java.time for locale-independent week calculations
- DAY/DY/D uses Sunday start for Oracle compatibility
- IW/W uses ISO week (Monday start)

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Integration and Performance Testing

### Task 13: Create Shadow Mode Integration Tests

**Files:**
- Test: `base/test/src/org/compiere/migration/Wave0ShadowIntegrationTest.java`

**Step 1: Write integration test comparing Java to SQL**

```java
// base/test/src/org/compiere/migration/Wave0ShadowIntegrationTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.adempiere.test.CommonGWSetup;
import org.compiere.migration.comparators.DateComparator;
import org.compiere.migration.comparators.TimestampComparator;
import org.compiere.util.SqlCompat;
import org.compiere.util.TimeUtil;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Integration tests comparing Java implementations to SQL functions.
 * These tests validate that Java matches SQL exactly.
 */
@Tag("IntegrationTest")
public class Wave0ShadowIntegrationTest extends CommonGWSetup {

    @Test
    void testGetDateMatchesSql() {
        Timestamp javaResult = TimeUtil.getDate();
        Timestamp sqlResult = SqlFunctionCaller.callGetDate();

        // Use comparator with 1 second tolerance
        assertTrue(TimestampComparator.withDefaultTolerance().test(javaResult, sqlResult),
            "getDate() should match within tolerance");
    }

    @Test
    void testDaysBetweenMatchesSql() {
        Timestamp date1 = Timestamp.valueOf("2026-01-15 14:30:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-01 08:00:00");

        Integer javaResult = TimeUtil.daysBetweenSql(date1, date2);
        Integer sqlResult = SqlFunctionCaller.callDaysBetween(date1, date2);

        assertEquals(sqlResult, javaResult);
    }

    @Test
    void testDaysBetweenNegativeMatchesSql() {
        Timestamp date1 = Timestamp.valueOf("2026-01-01 08:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-15 14:30:00");

        Integer javaResult = TimeUtil.daysBetweenSql(date1, date2);
        Integer sqlResult = SqlFunctionCaller.callDaysBetween(date1, date2);

        assertEquals(sqlResult, javaResult);
    }

    @Test
    void testAddDaysMatchesSql() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("10");

        Date javaResult = TimeUtil.addDaysSql(datetime, days);
        Date sqlResult = SqlFunctionCaller.callAddDays(datetime, days);

        assertTrue(DateComparator.INSTANCE.test(javaResult, sqlResult));
    }

    @Test
    void testTruncMatchesSql() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        // Test various format codes
        for (String format : new String[]{"Q", "Y", "YEAR", "MM", "MONTH", "DD"}) {
            Date javaResult = TimeUtil.truncSql(datetime, format);
            Date sqlResult = SqlFunctionCaller.callTrunc(datetime, format);

            assertTrue(DateComparator.INSTANCE.test(javaResult, sqlResult),
                "trunc(" + datetime + ", " + format + ") mismatch");
        }
    }

    @Test
    void testRoundMatchesSql() {
        BigDecimal value = new BigDecimal("123.456789");

        for (int scale = -2; scale <= 4; scale++) {
            BigDecimal javaResult = SqlCompat.round(value, scale);
            BigDecimal sqlResult = SqlFunctionCaller.callRound(value, scale);

            assertEquals(0, sqlResult.compareTo(javaResult),
                "round(" + value + ", " + scale + "): java=" + javaResult + ", sql=" + sqlResult);
        }
    }

    @Test
    void testFirstOfMatchesSql() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        for (String format : new String[]{"YYYY", "Q", "MM", "DD"}) {
            Date javaResult = TimeUtil.firstOf(datetime, format);
            Date sqlResult = SqlFunctionCaller.callFirstOf(datetime, format);

            assertTrue(DateComparator.INSTANCE.test(javaResult, sqlResult),
                "firstOf(" + datetime + ", " + format + ") mismatch");
        }
    }

    @Test
    void testCharAtMatchesSql() {
        String str = "Hello World";

        for (int pos = 1; pos <= str.length() + 2; pos++) {
            String javaResult = SqlCompat.charAt(str, pos);
            String sqlResult = SqlFunctionCaller.callCharAt(str, pos);

            assertEquals(sqlResult, javaResult, "charAt(" + str + ", " + pos + ") mismatch");
        }
    }
}
```

**Step 2: Run integration tests**

Run: `./gradlew :base:test --tests "*.Wave0ShadowIntegrationTest" -PintegrationTest -i`
Expected: All tests PASS

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave0ShadowIntegrationTest.java
git commit -m "test: add Wave 0 shadow integration tests

- Compares all 7 Java implementations against SQL functions
- Uses timezone-safe comparators for robust validation
- Validates exact match for daysBetween, addDays, trunc, round, firstOf, charAt
- Allows 1s tolerance for getDate() timestamp comparison

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 14: Create Performance Tests

**Files:**
- Test: `base/test/src/org/compiere/migration/Wave0PerformanceTest.java`

**Step 1: Write performance tests**

```java
// base/test/src/org/compiere/migration/Wave0PerformanceTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.SqlCompat;
import org.compiere.util.TimeUtil;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Performance tests validating Java implementations meet latency requirements.
 * Standard tier: Java must not exceed 130% of SQL latency.
 */
@Tag("PerformanceTest")
public class Wave0PerformanceTest extends CommonGWSetup {

    private static final double MAX_LATENCY_RATIO = 1.30; // 30% max increase
    private static final int WARMUP_ITERATIONS = 100;
    private static final int TEST_ITERATIONS = 1000;

    @Test
    void testDaysBetweenPerformance() {
        Timestamp date1 = Timestamp.valueOf("2026-01-15 14:30:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-01 08:00:00");

        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            TimeUtil.daysBetweenSql(date1, date2);
            SqlFunctionCaller.callDaysBetween(date1, date2);
        }

        // Measure Java
        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            TimeUtil.daysBetweenSql(date1, date2);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        // Measure SQL
        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callDaysBetween(date1, date2);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        assertTrue(ratio <= MAX_LATENCY_RATIO,
            String.format("daysBetween Java/SQL ratio %.2f exceeds max %.2f (java=%dms, sql=%dms)",
                ratio, MAX_LATENCY_RATIO, javaTimeNs/1_000_000, sqlTimeNs/1_000_000));
    }

    @Test
    void testAddDaysPerformance() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("10");

        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            TimeUtil.addDaysSql(datetime, days);
            SqlFunctionCaller.callAddDays(datetime, days);
        }

        // Measure Java
        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            TimeUtil.addDaysSql(datetime, days);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        // Measure SQL
        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callAddDays(datetime, days);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        assertTrue(ratio <= MAX_LATENCY_RATIO,
            String.format("addDays Java/SQL ratio %.2f exceeds max %.2f", ratio, MAX_LATENCY_RATIO));
    }

    @Test
    void testRoundPerformance() {
        BigDecimal value = new BigDecimal("123.456789");
        int scale = 2;

        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            SqlCompat.round(value, scale);
            SqlFunctionCaller.callRound(value, scale);
        }

        // Measure Java
        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlCompat.round(value, scale);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        // Measure SQL
        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callRound(value, scale);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        assertTrue(ratio <= MAX_LATENCY_RATIO,
            String.format("round Java/SQL ratio %.2f exceeds max %.2f", ratio, MAX_LATENCY_RATIO));
    }
}
```

**Step 2: Run performance tests**

Run: `./gradlew :base:test --tests "*.Wave0PerformanceTest" -PperformanceTest -i`
Expected: All tests PASS (Java should be significantly faster than SQL for these simple functions)

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave0PerformanceTest.java
git commit -m "test: add Wave 0 performance tests

- Validates Java latency <= 130% of SQL latency
- Tests daysBetween, addDays, round
- Uses warmup iterations for JIT stabilization

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Shadow Mode Activation

### Task 15: Enable Shadow Mode for Wave 0 Functions

**Step 1: Update function configurations to SHADOW**

```sql
-- db/ddlutils/postgresql/migrations/002_enable_wave0_shadow.sql
UPDATE migration.function_config SET mode = 'SHADOW', updated_at = NOW()
WHERE function_name IN ('getDate', 'daysBetween', 'addDays', 'subtractDays',
                        'trunc', 'round', 'firstOf', 'charAt');
```

**Step 2: Run the update**

Run: `psql -d adempiere -f db/ddlutils/postgresql/migrations/002_enable_wave0_shadow.sql`
Expected: UPDATE 8

**Step 3: Commit**

```bash
git add db/ddlutils/postgresql/migrations/002_enable_wave0_shadow.sql
git commit -m "chore: enable shadow mode for Wave 0 functions

- Sets all 8 Wave 0 functions to SHADOW mode
- 100% sample rate for full validation coverage

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Verification and Monitoring

### Task 16: Create Monitoring Dashboard Queries

**Files:**
- Create: `docs/runbooks/wave0-monitoring.md`

**Step 1: Write the monitoring runbook**

```markdown
# Wave 0 Monitoring Runbook

## Prerequisites

- JVM timezone must match PostgreSQL session timezone
- Verify with: `SELECT current_setting('TIMEZONE')` vs `TimeZone.getDefault().getID()`
- If mismatched, set JVM timezone: `-Duser.timezone=America/New_York`

## Dashboard Queries

### Match Rate by Function (Last 24 Hours)

```sql
SELECT
    function_name,
    COUNT(*) as total_calls,
    SUM(CASE WHEN is_match THEN 1 ELSE 0 END) as matches,
    ROUND(100.0 * SUM(CASE WHEN is_match THEN 1 ELSE 0 END) / COUNT(*), 2) as match_rate_pct
FROM migration.function_log
WHERE created_at > NOW() - INTERVAL '24 hours'
GROUP BY function_name
ORDER BY match_rate_pct ASC;
```

### Mismatches Detail (Last 24 Hours)

```sql
SELECT
    function_name,
    input_params,
    sql_result,
    java_result,
    mismatch_reason,
    created_at
FROM migration.function_log
WHERE NOT is_match
  AND created_at > NOW() - INTERVAL '24 hours'
ORDER BY created_at DESC
LIMIT 100;
```

### Performance Comparison (Last 24 Hours)

```sql
SELECT
    function_name,
    PERCENTILE_CONT(0.50) WITHIN GROUP (ORDER BY sql_time_ms) as sql_p50,
    PERCENTILE_CONT(0.95) WITHIN GROUP (ORDER BY sql_time_ms) as sql_p95,
    PERCENTILE_CONT(0.50) WITHIN GROUP (ORDER BY java_time_ms) as java_p50,
    PERCENTILE_CONT(0.95) WITHIN GROUP (ORDER BY java_time_ms) as java_p95,
    ROUND(100.0 * AVG(java_time_ms) / NULLIF(AVG(sql_time_ms), 0), 1) as java_vs_sql_pct
FROM migration.function_log
WHERE created_at > NOW() - INTERVAL '24 hours'
GROUP BY function_name;
```

### Queue Health

```sql
SELECT
    function_name,
    COUNT(*) as last_hour_entries,
    MAX(created_at) as last_entry
FROM migration.function_log
WHERE created_at > NOW() - INTERVAL '1 hour'
GROUP BY function_name;
```

### Circuit Breaker Status

Check application logs for:
- `"Migration log queue full"` - indicates queue overflow
- `"CIRCUIT_OPEN"` entries in function_log - indicates circuit breaker triggered

## Success Criteria

Wave 0 is ready for cutover when:

- [ ] All functions have >= 99.9% match rate for 7 consecutive days
- [ ] No critical mismatches (row count, null handling) in last 24h
- [ ] Java p95 latency <= 130% of SQL p95 for all functions
- [ ] Integration tests passing
- [ ] Rollback procedure tested

## Cutover Procedure

1. Update each function to JAVA_ONLY:
   ```sql
   UPDATE migration.function_config
   SET mode = 'JAVA_ONLY', updated_at = NOW()
   WHERE function_name = '<function>';
   ```

2. Monitor for 24 hours with shadow still logging

3. After 7 days stable, SQL functions can be deprecated

## Rollback Procedure

If issues are detected after enabling SHADOW or JAVA_ONLY mode:

### Step 1: Revert to SQL_ONLY
```sql
UPDATE migration.function_config
SET mode = 'SQL_ONLY', updated_at = NOW()
WHERE function_name = '<function_name>';
```

### Step 2: Verify Reversion
```sql
SELECT function_name, mode, updated_at
FROM migration.function_config
WHERE function_name = '<function_name>';
```

### Step 3: Clear Config Cache
The MigrationConfig cache has 60s TTL. Wait 60 seconds or restart the application
for immediate effect.

### Step 4: Investigate
Review mismatches in function_log:
```sql
SELECT * FROM migration.function_log
WHERE function_name = '<function_name>'
  AND NOT is_match
ORDER BY created_at DESC
LIMIT 100;
```
```

**Step 2: Commit**

```bash
git add docs/runbooks/wave0-monitoring.md
git commit -m "docs: add Wave 0 monitoring runbook

- Dashboard queries for match rate, mismatches, performance
- Success criteria checklist for cutover approval
- Cutover procedure with JAVA_ONLY transition
- Rollback procedure with cache considerations

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Summary

### Files Created

**Infrastructure:**
- `db/ddlutils/postgresql/migrations/001_create_migration_schema.sql`
- `base/src/org/compiere/migration/MigrationMode.java`
- `base/src/org/compiere/migration/MigrationConfig.java`
- `base/src/org/compiere/migration/MigrationLogger.java`
- `base/src/org/compiere/migration/ParamSerializer.java`
- `base/src/org/compiere/migration/CircuitBreaker.java`
- `base/src/org/compiere/migration/SqlFunctionCaller.java`
- `base/src/org/compiere/migration/ShadowExecutor.java`
- `base/src/org/compiere/migration/comparators/TimestampComparator.java`
- `base/src/org/compiere/migration/comparators/DateComparator.java`

**Implementations:**
- Modified: `base/src/org/compiere/util/TimeUtil.java` (added getDate, daysBetweenSql, addDaysSql, subtractDaysSql, truncSql, firstOf)
- `base/src/org/compiere/util/SqlCompat.java` (round, charAt)

**Tests:**
- `base/test/src/org/compiere/migration/MigrationLoggerTest.java`
- `base/test/src/org/compiere/migration/ParamSerializerTest.java`
- `base/test/src/org/compiere/migration/CircuitBreakerTest.java`
- `base/test/src/org/compiere/migration/comparators/ComparatorTest.java`
- `base/test/src/org/compiere/migration/SqlFunctionCallerTest.java`
- `base/test/src/org/compiere/migration/ShadowExecutorTest.java`
- `base/test/src/org/compiere/util/TimeUtilGetDateTest.java`
- `base/test/src/org/compiere/util/TimeUtilDaysBetweenSqlTest.java`
- `base/test/src/org/compiere/util/TimeUtilAddDaysSqlTest.java`
- `base/test/src/org/compiere/util/TimeUtilTruncSqlTest.java`
- `base/test/src/org/compiere/util/SqlCompatTest.java`
- `base/test/src/org/compiere/util/TimeUtilFirstOfTest.java`
- `base/test/src/org/compiere/migration/Wave0ShadowIntegrationTest.java`
- `base/test/src/org/compiere/migration/Wave0PerformanceTest.java`

**Documentation:**
- `docs/runbooks/wave0-monitoring.md`

### Test Categories

| Tag | Purpose | Base Class | Run Command |
|-----|---------|------------|-------------|
| `@Tag("UnitTest")` | Pure logic, no DB | None | `./gradlew :base:test` |
| `@Tag("IntegrationTest")` | Requires DB | `CommonGWSetup` | `./gradlew :base:test -PintegrationTest` |
| `@Tag("PerformanceTest")` | Latency validation | `CommonGWSetup` | `./gradlew :base:test -PperformanceTest` |

### Commit History

1. `feat: add migration infrastructure schema and config`
2. `feat: add async MigrationLogger with bounded queue`
3. `feat: add ParamSerializer for shadow mode input logging`
4. `feat: add timezone-safe comparators for shadow mode`
5. `feat: add CircuitBreaker for shadow mode resilience`
6. `feat: add SqlFunctionCaller for shadow mode SQL execution`
7. `feat: add ShadowExecutor for shadow mode orchestration`
8. `feat: add TimeUtil.getDate() for SQL getDate() migration`
9. `feat: add TimeUtil.daysBetweenSql() for SQL daysBetween migration`
10. `feat: add TimeUtil.addDaysSql/subtractDaysSql for SQL migration`
11. `feat: add TimeUtil.truncSql() for SQL trunc migration`
12. `feat: add SqlCompat with round() and charAt() for SQL migration`
13. `feat: add TimeUtil.firstOf() for SQL firstOf migration`
14. `test: add Wave 0 shadow integration tests`
15. `test: add Wave 0 performance tests`
16. `chore: enable shadow mode for Wave 0 functions`
17. `docs: add Wave 0 monitoring runbook`
