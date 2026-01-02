# Wave 0 Part 1: Core Infrastructure

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Parent Plan:** [Wave 0 Implementation Plan](2026-01-02-wave0-implementation-plan.md)
**Part:** 1 of 5
**Tasks:** 0-3 (Schema, Logger, ParamSerializer, Comparators)
**Review Status:** Updated per critical-review-2 findings

---

## Prerequisites

Add JSR-305 dependency to `base/build.gradle` for `@Nullable` annotations:

```groovy
dependencies {
    // Add after existing dependencies
    // https://mvnrepository.com/artifact/com.google.code.findbugs/jsr305
    implementation 'com.google.code.findbugs:jsr305:3.0.2'
}
```

**Note:** Tests are in separate project `:base:test` which depends on `:base`. Test commands use `:base:test:unitTest` (not `:base:test`).

---

## Task 0: Create Migration Schema and Configuration Table

**Files:**
- Create: `db/ddlutils/postgresql/migrations/001_create_migration_schema.sql`
- Create: `base/src/org/compiere/migration/MigrationConfig.java`
- Create: `base/src/org/compiere/migration/MigrationMode.java`

**Step 0: Create migrations directory**

```bash
mkdir -p db/ddlutils/postgresql/migrations
```

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

**Step 2.5: Capture baseline SQL performance**

Create and run the baseline capture script. This must be done **before any Java code is written** to measure pure SQL function performance.

```sql
-- db/ddlutils/postgresql/migrations/001b_capture_baselines.sql
-- Captures p95 latency baselines for Wave 0 SQL functions
-- Run this BEFORE writing any Java code

DO $$
DECLARE
    iterations INT := 1000;
    start_ts TIMESTAMP;
    end_ts TIMESTAMP;
    elapsed_ms NUMERIC;
    timings NUMERIC[];
    p95_idx INT;
    i INT;
    dummy_ts TIMESTAMP;
    dummy_date DATE;
    dummy_int INT;
    dummy_numeric NUMERIC;
    dummy_text TEXT;
BEGIN
    -- getDate baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_ts := getDate();
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'getDate';
    RAISE NOTICE 'getDate p95: % ms', CEIL(elapsed_ms);

    -- daysBetween baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_int := daysBetween('2026-01-15 14:30:00'::timestamp, '2026-01-01 08:00:00'::timestamp);
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'daysBetween';
    RAISE NOTICE 'daysBetween p95: % ms', CEIL(elapsed_ms);

    -- addDays baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_date := addDays('2026-01-15 14:30:00'::timestamp, 10);
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'addDays';
    RAISE NOTICE 'addDays p95: % ms', CEIL(elapsed_ms);

    -- subtractDays baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_date := subtractDays('2026-01-15 14:30:00'::timestamp, 10);
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'subtractDays';
    RAISE NOTICE 'subtractDays p95: % ms', CEIL(elapsed_ms);

    -- trunc baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_date := trunc('2026-05-15 14:30:45'::timestamp, 'Q');
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'trunc';
    RAISE NOTICE 'trunc p95: % ms', CEIL(elapsed_ms);

    -- round baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_numeric := round(123.456789, 2);
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'round';
    RAISE NOTICE 'round p95: % ms', CEIL(elapsed_ms);

    -- firstOf baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_date := firstOf('2026-05-15 14:30:45'::timestamp, 'Q');
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'firstOf';
    RAISE NOTICE 'firstOf p95: % ms', CEIL(elapsed_ms);

    -- charAt baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_text := charAt('Hello World', 5);
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'charAt';
    RAISE NOTICE 'charAt p95: % ms', CEIL(elapsed_ms);

END $$;

-- Display captured baselines
SELECT function_name, sql_baseline_p95_ms, mode, performance_tier
FROM migration.function_config
ORDER BY function_name;
```

Run: `psql -d adempiere -f db/ddlutils/postgresql/migrations/001b_capture_baselines.sql`
Expected: NOTICE messages showing p95 latency for each function (typically < 1ms for these simple functions), then a table showing all baselines populated.

**Important:** If any function doesn't exist yet in the database, this script will fail. Ensure all Wave 0 SQL functions are deployed before running.

**Step 3: Write MigrationMode enum**

```java
// base/src/org/compiere/migration/MigrationMode.java
package org.compiere.migration;

/**
 * Migration modes for function cutover.
 * DUAL_WRITE intentionally omitted - not needed for stateless Wave 0 functions.
 */
public enum MigrationMode {
    SQL_ONLY,    // Legacy path only
    SHADOW,      // Both execute, compare, return Java
    JAVA_ONLY    // Java only, SQL can be deleted
}
```

**Step 4: Write MigrationConfig class (with lazy init and fallback handling)**

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

    // Lazy timezone validation (not in static initializer - DB may not be ready)
    private static volatile boolean timezoneValidated = false;

    private final String functionName;
    private final MigrationMode mode;
    private final double sampleRate;
    private final boolean circuitBreakerEnabled;

    private MigrationConfig(String functionName, MigrationMode mode, double sampleRate, boolean circuitBreakerEnabled) {
        this.functionName = functionName;
        this.mode = mode;
        this.sampleRate = sampleRate;
        this.circuitBreakerEnabled = circuitBreakerEnabled;
    }

    /**
     * Get configuration for a function with caching.
     * Uses double-check pattern to prevent cache stampede under high concurrency.
     */
    public static MigrationConfig get(String functionName) {
        // Lazy timezone validation on first call when DB is ready
        if (!timezoneValidated && DB.isConnected()) {
            validateTimezoneAlignmentOnce();
        }

        // Fast path: check cache without locking
        CachedConfig cached = cache.get(functionName);
        if (cached != null && !cached.isExpired()) {
            return cached.config;
        }

        // Slow path: compute under lock (per-key)
        return cache.compute(functionName, (k, v) -> {
            // Double-check inside compute (now under lock for this key)
            if (v != null && !v.isExpired()) return v;
            return loadFromDatabase(functionName);
        }).config;
    }

    private static synchronized void validateTimezoneAlignmentOnce() {
        if (timezoneValidated) return;
        try {
            String sql = "SELECT current_setting('TIMEZONE')";
            PreparedStatement pstmt = DB.prepareStatement(sql, null);
            if (pstmt == null) {
                log.warning("Cannot validate timezone - statement preparation failed");
                return;
            }
            try (pstmt; ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String pgTimezone = rs.getString(1);
                    String jvmTimezone = java.util.TimeZone.getDefault().getID();
                    if (!pgTimezone.equals(jvmTimezone)) {
                        log.warning("Timezone mismatch: PostgreSQL=" + pgTimezone +
                                   ", JVM=" + jvmTimezone +
                                   ". Shadow mode comparisons may show false mismatches.");
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to validate timezone alignment", e);
        } finally {
            timezoneValidated = true;
        }
    }

    private static CachedConfig loadFromDatabase(String functionName) {
        String sql = "SELECT mode, sample_rate, circuit_breaker_enabled " +
                     "FROM migration.function_config WHERE function_name = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            if (pstmt == null) {
                log.warning("Cannot prepare statement, defaulting to SQL_ONLY for " + functionName);
                return new CachedConfig(
                    new MigrationConfig(functionName, MigrationMode.SQL_ONLY, 1.0, true),
                    true); // isFallback = true
            }
            pstmt.setString(1, functionName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    MigrationMode mode = MigrationMode.valueOf(rs.getString("mode"));
                    double sampleRate = rs.getDouble("sample_rate");
                    if (sampleRate < 0.0 || sampleRate > 1.0) {
                        log.warning("Invalid sample_rate " + sampleRate + " for " + functionName + ", using 1.0");
                        sampleRate = 1.0;
                    }
                    boolean circuitBreaker = rs.getBoolean("circuit_breaker_enabled");
                    return new CachedConfig(
                        new MigrationConfig(functionName, mode, sampleRate, circuitBreaker),
                        false); // isFallback = false
                }
            }
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to load migration config for " + functionName + ", defaulting to SQL_ONLY", e);
        } finally {
            DB.close(pstmt);
        }
        // Default: SQL_ONLY if not found or error (marked as fallback)
        return new CachedConfig(
            new MigrationConfig(functionName, MigrationMode.SQL_ONLY, 1.0, true),
            true); // isFallback = true
    }

    public static MigrationConfig sqlOnly(String functionName) {
        return new MigrationConfig(functionName, MigrationMode.SQL_ONLY, 1.0, true);
    }

    public String getFunctionName() { return functionName; }
    public MigrationMode getMode() { return mode; }
    /** Used by ShadowExecutor in Part 2 for probabilistic sampling. */
    public double getSampleRate() { return sampleRate; }
    public boolean isCircuitBreakerEnabled() { return circuitBreakerEnabled; }

    private static class CachedConfig {
        final MigrationConfig config;
        final long cachedAt;
        final boolean isFallback;

        CachedConfig(MigrationConfig config, boolean isFallback) {
            this.config = config;
            this.cachedAt = System.currentTimeMillis();
            this.isFallback = isFallback;
        }

        boolean isExpired() {
            // Fallback configs expire immediately so we retry on next call
            if (isFallback) return true;
            return System.currentTimeMillis() - cachedAt > CACHE_TTL_MS;
        }
    }
}
```

**Step 5: Commit infrastructure setup**

```bash
git add db/ddlutils/postgresql/migrations/001_create_migration_schema.sql
git add db/ddlutils/postgresql/migrations/001b_capture_baselines.sql
git add base/src/org/compiere/migration/MigrationMode.java
git add base/src/org/compiere/migration/MigrationConfig.java
git commit -m "feat: add migration infrastructure schema and config

- Create migration schema with function_config and function_log tables
- Add baseline capture script to measure SQL function p95 latency
- Add MigrationMode enum (SQL_ONLY, SHADOW, JAVA_ONLY)
- Add MigrationConfig with 60s TTL cache, lazy timezone validation
- Fallback configs expire immediately to retry when DB becomes available
- Null-safe PreparedStatement handling throughout

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Task 1: Create Migration Logger (with batch processing)

**Files:**
- Create: `base/src/org/compiere/migration/MigrationLogger.java`
- Test: `base/test/src/org/compiere/migration/MigrationLoggerTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/migration/MigrationLoggerTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
public class MigrationLoggerTest {

    @BeforeEach
    void resetLogger() {
        MigrationLogger.resetForTesting();
    }

    @Test
    void testLogAsyncDoesNotBlock() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            MigrationLogger.logAsync("testFunction", "[\"input" + i + "\"]", "sqlResult", "javaResult", 10, 8, true, null);
        }

        long elapsed = System.currentTimeMillis() - start;
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

Run: `./gradlew :base:test:unitTest --tests "*.MigrationLoggerTest" -i`
Expected: FAIL with "cannot find symbol: class MigrationLogger"

**Step 3: Write MigrationLogger implementation (with batch processing and safe shutdown)**

```java
// base/src/org/compiere/migration/MigrationLogger.java
package org.compiere.migration;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MigrationLogger {
    private static final CLogger log = CLogger.getCLogger(MigrationLogger.class);
    private static final int QUEUE_CAPACITY = 10_000;
    private static final int BATCH_SIZE = 100;
    private static final long SHUTDOWN_DRAIN_TIMEOUT_MS = Long.getLong(
        "migration.shutdown.timeout.ms", 5000);
    private static final BlockingQueue<LogEntry> queue = new LinkedBlockingQueue<>(QUEUE_CAPACITY);
    private static final AtomicLong droppedCount = new AtomicLong(0);
    private static volatile boolean running = true;
    private static volatile boolean databaseEnabled = true;
    private static final Thread drainThread;

    static {
        drainThread = new Thread(MigrationLogger::drainQueue, "MigrationLogger-Drain");
        drainThread.setDaemon(true);
        drainThread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            running = false;
            drainThread.interrupt();

            // Wait for drain thread to stop before we drain ourselves
            try {
                drainThread.join(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Batch drain remaining entries
            List<LogEntry> batch = new ArrayList<>(BATCH_SIZE);
            long deadline = System.currentTimeMillis() + SHUTDOWN_DRAIN_TIMEOUT_MS;
            int drained = 0;
            while (!queue.isEmpty() && System.currentTimeMillis() < deadline) {
                batch.clear();
                queue.drainTo(batch, BATCH_SIZE);
                if (!batch.isEmpty()) {
                    drained += batch.size();
                    writeBatchToDatabase(batch);
                }
            }
            if (!queue.isEmpty()) {
                log.warning("MigrationLogger shutdown: drained " + drained + ", " + queue.size() + " entries still pending");
            } else if (drained > 0) {
                log.info("MigrationLogger shutdown: drained " + drained + " entries successfully");
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

    /** Disable database writes for unit testing. */
    static void setDatabaseEnabled(boolean enabled) {
        databaseEnabled = enabled;
    }

    /** Reset state for unit testing. Package-private to limit scope. */
    static void resetForTesting() {
        queue.clear();
        droppedCount.set(0);
        databaseEnabled = false;
    }

    private static void drainQueue() {
        List<LogEntry> batch = new ArrayList<>(BATCH_SIZE);
        while (running) {
            try {
                // Wait for first entry (shorter timeout during shutdown)
                LogEntry entry = queue.poll(running ? 100 : 10, TimeUnit.MILLISECONDS);
                if (entry != null) {
                    batch.add(entry);
                    // Drain up to BATCH_SIZE - 1 more entries
                    queue.drainTo(batch, BATCH_SIZE - 1);
                    writeBatchToDatabase(batch);
                    batch.clear();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                log.log(Level.WARNING, "Failed to write migration log batch", e);
                batch.clear();
            }
        }
    }

    private static void writeBatchToDatabase(List<LogEntry> entries) {
        if (entries.isEmpty()) return;

        if (!databaseEnabled) {
            return; // Skip DB writes during unit tests
        }

        if (!DB.isConnected()) {
            log.warning("Database not connected, " + entries.size() + " migration log entries dropped");
            return;
        }

        String sql = "INSERT INTO migration.function_log " +
                     "(function_name, input_params, sql_result, java_result, " +
                     "sql_time_ms, java_time_ms, is_match, mismatch_reason) " +
                     "VALUES (?, ?::jsonb, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = DB.prepareStatement(sql, null);
        if (pstmt == null) {
            log.warning("Failed to prepare statement, " + entries.size() + " entries dropped");
            return;
        }

        try (pstmt) {
            for (LogEntry entry : entries) {
                pstmt.setString(1, entry.functionName);
                pstmt.setString(2, entry.inputParams);
                pstmt.setString(3, entry.sqlResult);
                pstmt.setString(4, entry.javaResult);
                pstmt.setLong(5, entry.sqlTimeMs);
                pstmt.setLong(6, entry.javaTimeMs);
                pstmt.setBoolean(7, entry.isMatch);
                pstmt.setString(8, entry.mismatchReason);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to insert migration log batch of " + entries.size(), e);
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

Run: `./gradlew :base:test:unitTest --tests "*.MigrationLoggerTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/MigrationLogger.java
git add base/test/src/org/compiere/migration/MigrationLoggerTest.java
git commit -m "feat: add async MigrationLogger with batch processing

- 10K capacity bounded queue with drop-on-overflow
- Batch processing (100 entries) for efficiency
- Background daemon thread drains to migration.function_log
- Configurable shutdown timeout via migration.shutdown.timeout.ms (default 5s)
- Safe shutdown: join drain thread before hook drains queue
- Null-safe PreparedStatement and DB connectivity checks

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Task 2: Create ParamSerializer (with java.sql.Date support)

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
    void testSqlDate() {
        java.sql.Date date = java.sql.Date.valueOf("2026-01-15");
        assertEquals("[\"2026-01-15\"]", ParamSerializer.toJson(date));
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

Run: `./gradlew :base:test:unitTest --tests "*.ParamSerializerTest" -i`
Expected: FAIL with "cannot find symbol: class ParamSerializer"

**Step 3: Write ParamSerializer implementation**

```java
// base/src/org/compiere/migration/ParamSerializer.java
package org.compiere.migration;

import java.math.BigDecimal;
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
        } else if (value instanceof java.sql.Date) {
            return "\"" + value.toString() + "\"";
        } else if (value instanceof java.util.Date) {
            // Handle java.util.Date (convert to Timestamp format)
            return "\"" + new Timestamp(((java.util.Date) value).getTime()).toString() + "\"";
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

Run: `./gradlew :base:test:unitTest --tests "*.ParamSerializerTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/ParamSerializer.java
git add base/test/src/org/compiere/migration/ParamSerializerTest.java
git commit -m "feat: add ParamSerializer for shadow mode input logging

- Simple JSON serialization without external dependencies
- Handles Timestamp, java.sql.Date, java.util.Date, BigDecimal, String, Number, Boolean, null
- Escapes special characters in strings

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Task 3: Create Comparators for Shadow Mode (with LocalDate comparison)

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

Run: `./gradlew :base:test:unitTest --tests "*.ComparatorTest" -i`
Expected: FAIL with "cannot find symbol"

**Step 3: Write comparator implementations**

```java
// base/src/org/compiere/migration/comparators/TimestampComparator.java
package org.compiere.migration.comparators;

import java.sql.Timestamp;
import java.util.function.BiPredicate;

import javax.annotation.Nullable;

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
    public boolean test(@Nullable Timestamp java, @Nullable Timestamp sql) {
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

import javax.annotation.Nullable;

/**
 * Compares dates by calendar date (year, month, day), ignoring timezone.
 * Uses LocalDate conversion to avoid timezone-at-midnight issues.
 */
public class DateComparator implements BiPredicate<Date, Date> {

    public static final DateComparator INSTANCE = new DateComparator();

    @Override
    public boolean test(@Nullable Date java, @Nullable Date sql) {
        if (java == null && sql == null) return true;
        if (java == null || sql == null) return false;

        // Compare as LocalDate - explicit and timezone-safe
        return java.toLocalDate().equals(sql.toLocalDate());
    }
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test:unitTest --tests "*.ComparatorTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/comparators/TimestampComparator.java
git add base/src/org/compiere/migration/comparators/DateComparator.java
git add base/test/src/org/compiere/migration/comparators/ComparatorTest.java
git commit -m "feat: add timezone-safe comparators for shadow mode

- TimestampComparator with configurable tolerance (default 1s)
- DateComparator uses LocalDate comparison for timezone safety
- Both handle null values correctly with @Nullable annotations

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Notes

**Integration Tests:** Unit tests in this part verify behavior without database connectivity. Integration tests that verify actual database writes are deferred to [Part 5: Testing & Deployment](wave0-part5-testing-deployment.md) where the full test infrastructure is established.

**Configurable Properties:**
- `migration.shutdown.timeout.ms` - Shutdown drain timeout in milliseconds (default: 5000)

---

## Configuration Constants

| Constant | Value | Location | Description |
|----------|-------|----------|-------------|
| `CACHE_TTL_MS` | 60,000 (60s) | MigrationConfig | How long config entries stay cached before refresh |
| `QUEUE_CAPACITY` | 10,000 | MigrationLogger | Max pending log entries before dropping |
| `BATCH_SIZE` | 100 | MigrationLogger | Entries written per DB batch insert |
| `migration.shutdown.timeout.ms` | 5,000 (5s) | MigrationLogger | System property to control shutdown drain timeout |

**Tuning guidance:**
- `QUEUE_CAPACITY`: Wave 0 functions are called < 100/day. 10K capacity provides ~100x headroom.
- `BATCH_SIZE`: 100 entries balances latency vs. DB round-trips for low-volume logging.
- `CACHE_TTL_MS`: 60s allows config changes to propagate within a minute without hammering the DB.

---

**Next:** [Part 2: Execution Infrastructure](wave0-part2-execution-infrastructure.md) (Tasks 4-6 + SqlFunctionException)
