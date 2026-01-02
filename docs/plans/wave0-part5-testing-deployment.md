# Wave 0 Part 5: Testing & Deployment

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Parent Plan:** [Wave 0 Implementation Plan](2026-01-02-wave0-implementation-plan.md)
**Part:** 5 of 5
**Tasks:** 13, 15-16 (Integration Tests, Enable Shadow, Monitoring)

> **Note:** Performance tests (Task 14) have been moved to Parts 3 & 4. Each function's performance test now runs immediately after its implementation, catching regressions at the source. See Task 7a in Part 3 for the performance test file creation.

**Previous:** [Part 4: Utility Functions](wave0-part4-utility-functions.md)

---

## Task 13: Create Shadow Mode Integration Tests (with flakiness fix)

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
        // Execute both as close together as possible to minimize drift
        long beforeJava = System.currentTimeMillis();
        Timestamp javaResult = TimeUtil.getDate();
        Timestamp sqlResult = SqlFunctionCaller.callGetDate();
        long afterSql = System.currentTimeMillis();

        // Use actual elapsed time as tolerance, plus 500ms buffer
        long maxDrift = (afterSql - beforeJava) + 500;
        long actualDrift = Math.abs(javaResult.getTime() - sqlResult.getTime());

        assertTrue(actualDrift <= maxDrift,
            String.format("getDate() drift %dms exceeded max %dms: java=%s, sql=%s",
                actualDrift, maxDrift, javaResult, sqlResult));
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
    void testSubtractDaysMatchesSql() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("10");

        Date javaResult = TimeUtil.subtractDaysSql(datetime, days);
        Date sqlResult = SqlFunctionCaller.callSubtractDays(datetime, days);

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
                "trunc(" + datetime + ", " + format + ") mismatch: java=" + javaResult + ", sql=" + sqlResult);
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
                "firstOf(" + datetime + ", " + format + ") mismatch: java=" + javaResult + ", sql=" + sqlResult);
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

- Compares all 8 Java implementations against SQL functions
- Uses timezone-safe comparators for robust validation
- getDate() test uses dynamic tolerance based on execution time
- Validates exact match for daysBetween, addDays, subtractDays, trunc, round, firstOf, charAt

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Task 14: Performance Tests (Moved to Parts 3 & 4)

> **This task has been relocated.** Performance tests are now run incrementally after each function implementation to catch regressions at the source:
>
> - **Task 7a (Part 3):** Creates `Wave0PerformanceTest.java` with all 5 performance tests
> - **Task 8 Step 6:** Runs `testDaysBetweenPerformance` after daysBetween implementation
> - **Task 9 Step 6:** Runs `testAddDaysPerformance` after addDays implementation
> - **Task 10 Step 6:** Runs `testTruncPerformance` after trunc implementation
> - **Task 11 Step 6 (Part 4):** Runs `testRoundPerformance` after round implementation
> - **Task 12 Step 6 (Part 4):** Runs `testFirstOfPerformance` after firstOf implementation
>
> This approach ensures each function's performance is validated immediately after implementation, rather than batching all performance tests at the end.

---

## Task 15: Enable Shadow Mode for Wave 0 Functions

**Step 1: Create the update script (idempotent with validation)**

```sql
-- db/ddlutils/postgresql/migrations/002_enable_wave0_shadow.sql
-- Enable shadow mode for all Wave 0 functions at 100% sampling
-- This script is idempotent - safe to run multiple times

DO $$
DECLARE
    expected_count INT := 8;
    actual_count INT;
BEGIN
    -- Update only functions not already in SHADOW mode
    UPDATE migration.function_config
    SET mode = 'SHADOW', updated_at = NOW()
    WHERE function_name IN (
        'getDate', 'daysBetween', 'addDays', 'subtractDays',
        'trunc', 'round', 'firstOf', 'charAt'
    )
    AND mode != 'SHADOW';

    -- Verify all 8 functions exist and are in SHADOW mode
    SELECT COUNT(*) INTO actual_count
    FROM migration.function_config
    WHERE function_name IN (
        'getDate', 'daysBetween', 'addDays', 'subtractDays',
        'trunc', 'round', 'firstOf', 'charAt'
    ) AND mode = 'SHADOW';

    IF actual_count != expected_count THEN
        RAISE EXCEPTION 'Expected % functions in SHADOW mode, found %. Ensure 001_create_migration_schema.sql was run first.',
            expected_count, actual_count;
    END IF;

    RAISE NOTICE 'Successfully verified % functions in SHADOW mode', actual_count;
END $$;

-- Display final state for verification
SELECT function_name, mode, sample_rate, updated_at
FROM migration.function_config
WHERE function_name IN (
    'getDate', 'daysBetween', 'addDays', 'subtractDays',
    'trunc', 'round', 'firstOf', 'charAt'
)
ORDER BY function_name;
```

**Step 2: Run the update**

Run: `psql -d adempiere -f db/ddlutils/postgresql/migrations/002_enable_wave0_shadow.sql`
Expected: NOTICE showing 8 functions in SHADOW mode, then a table displaying all 8 functions

**Step 3: Commit**

```bash
git add db/ddlutils/postgresql/migrations/002_enable_wave0_shadow.sql
git commit -m "chore: enable shadow mode for Wave 0 functions

- Sets all 8 Wave 0 functions to SHADOW mode
- 100% sample rate for full validation coverage
- Script is idempotent - safe to run multiple times
- Validates all functions exist before completing

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Task 16: Create Monitoring Dashboard Queries

**Files:**
- Create: `docs/runbooks/wave0-monitoring.md`

**Step 1: Write the monitoring runbook**

```markdown
# Wave 0 Monitoring Runbook

## Prerequisites

- JVM timezone must match PostgreSQL session timezone
- Verify with: `SELECT current_setting('TIMEZONE')` vs `TimeZone.getDefault().getID()`
- If mismatched, set JVM timezone: `-Duser.timezone=America/New_York`

---

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
- `"Circuit OPENED for function"` - indicates circuit breaker triggered
- `"Circuit CLOSED for function"` - indicates circuit breaker recovered
- `"Migration log queue full"` - indicates queue overflow

Also check function_log for:
```sql
SELECT function_name, COUNT(*) as circuit_open_count
FROM migration.function_log
WHERE mismatch_reason = 'CIRCUIT_OPEN'
  AND created_at > NOW() - INTERVAL '24 hours'
GROUP BY function_name;
```

---

## Alerting

### Match Rate Alert
Alert if any function drops below 99.9% match rate over a 1-hour window:
- **Query:** Use "Match Rate by Function" query filtered to last 1 hour
- **Threshold:** `match_rate_pct < 99.9`
- **Duration:** 15 minutes sustained
- **Severity:** Critical
- **Action:** Page on-call, investigate mismatches immediately

### Circuit Breaker Alert
Alert on log message containing "Circuit OPENED":
- **Log pattern:** `"Circuit OPENED for function"`
- **Severity:** Warning
- **Action:** Check database connectivity, review recent errors

### Queue Overflow Alert
Alert on log message containing "Migration log queue full":
- **Log pattern:** `"Migration log queue full"`
- **Severity:** Warning
- **Action:** Check if writer thread is blocked, consider increasing queue size

### Latency Regression Alert
Alert if Java p95 latency exceeds 130% of SQL p95:
- **Query:** Use "Performance Comparison" query
- **Threshold:** `java_vs_sql_pct > 130`
- **Duration:** 30 minutes sustained
- **Severity:** Warning
- **Action:** Profile Java implementation, check for GC issues

---

## Success Criteria

Wave 0 is ready for cutover when:

- [ ] All functions have >= 99.9% match rate for 7 consecutive days
- [ ] No critical mismatches (row count, null handling) in last 24h
- [ ] Java p95 latency <= 130% of SQL p95 for all functions
- [ ] Integration tests passing
- [ ] Rollback procedure tested

---

## Cutover Procedure

### Step 1: Update Each Function to JAVA_ONLY

```sql
UPDATE migration.function_config
SET mode = 'JAVA_ONLY', updated_at = NOW()
WHERE function_name = '<function_name>';
```

### Step 2: Verify Mode Change

```sql
SELECT function_name, mode, updated_at
FROM migration.function_config
WHERE function_name = '<function_name>';
```

### Step 3: Monitor for 24 Hours

Continue monitoring match rates and performance with shadow still logging.

### Step 4: After 7 Days Stable

SQL functions can be deprecated and removed from the database.

---

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
SELECT
    input_params,
    sql_result,
    java_result,
    mismatch_reason,
    sql_time_ms,
    java_time_ms,
    created_at
FROM migration.function_log
WHERE function_name = '<function_name>'
  AND NOT is_match
ORDER BY created_at DESC
LIMIT 100;
```

---

## Log Maintenance

### Automated Cleanup with pg_cron

If pg_cron extension is available, schedule automatic cleanup:

```sql
-- Install pg_cron if not already installed
-- CREATE EXTENSION pg_cron;

-- Schedule daily cleanup at 2 AM, keeping 30 days of data
SELECT cron.schedule('migration-log-cleanup', '0 2 * * *', $$
    DELETE FROM migration.function_log
    WHERE created_at < NOW() - INTERVAL '30 days';
$$);

-- Verify the job is scheduled
SELECT * FROM cron.job WHERE jobname = 'migration-log-cleanup';
```

### Manual Purge (Keep 30 Days)

```sql
DELETE FROM migration.function_log
WHERE created_at < NOW() - INTERVAL '30 days';

-- Reclaim space
VACUUM migration.function_log;
```

### Archive Before Purge (Optional)

```sql
-- Create archive table if not exists
CREATE TABLE IF NOT EXISTS migration.function_log_archive (LIKE migration.function_log);

-- Move old records to archive
INSERT INTO migration.function_log_archive
SELECT * FROM migration.function_log
WHERE created_at < NOW() - INTERVAL '30 days';

-- Then delete
DELETE FROM migration.function_log
WHERE created_at < NOW() - INTERVAL '30 days';
```

---

## Appendix: Design Decisions

This section documents key design decisions for reference. See the main design plan for full context.

### Database Transaction Boundaries
`MigrationLogger.writeToDatabase()` uses its own connection from the pool via
`DB.prepareStatement(sql, null)`. The `null` transaction name means it auto-commits.
This is intentional - shadow logging should not affect application transactions.

### Timezone Mismatch Handling
If timezone mismatch is detected in `MigrationConfig` static initializer, a WARNING
is logged but shadow mode continues. This allows operators to see mismatches in logs
while the system remains functional. To disable shadow mode on mismatch, set the
function to SQL_ONLY in the database.

### Integration Test Database
Integration tests run against the Garden World database configured in
`base/test/resources/test.properties`. Test data is not automatically cleaned;
tests should be idempotent or use unique test data.

### Fractional Days
Rejecting fractional days with `IllegalArgumentException` is a permanent design
decision. The SQL function returns DATE which truncates time anyway, so accepting
fractions would silently lose precision. Fail-fast is preferred.

### Week Calculation Validation
The `firstOf()` DAY/DY/D calculation (ISO Monday - 1 for Sunday start) matches
PostgreSQL's Oracle-compatible `firstOf()` implementation. Validated against:
- `SELECT firstOf('2026-01-15'::timestamp, 'DAY')` returns `2026-01-11`
```

**Step 2: Commit**

```bash
mkdir -p docs/runbooks
git add docs/runbooks/wave0-monitoring.md
git commit -m "docs: add Wave 0 monitoring runbook

- Dashboard queries for match rate, mismatches, performance
- Alerting section with thresholds and severity levels
- Success criteria checklist for cutover approval
- Cutover procedure with JAVA_ONLY transition
- Rollback procedure with cache considerations
- Log maintenance with pg_cron automation example
- Design decisions appendix for reference

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Summary

### Files Created Across All Parts

**Part 1 - Core Infrastructure:**
- `db/ddlutils/postgresql/migrations/001_create_migration_schema.sql`
- `base/src/org/compiere/migration/MigrationMode.java`
- `base/src/org/compiere/migration/MigrationConfig.java`
- `base/src/org/compiere/migration/MigrationLogger.java`
- `base/src/org/compiere/migration/ParamSerializer.java`
- `base/src/org/compiere/migration/comparators/TimestampComparator.java`
- `base/src/org/compiere/migration/comparators/DateComparator.java`

**Part 2 - Execution Infrastructure:**
- `base/src/org/compiere/migration/CircuitBreaker.java`
- `base/src/org/compiere/migration/SqlFunctionException.java`
- `base/src/org/compiere/migration/SqlFunctionCaller.java`
- `base/src/org/compiere/migration/ShadowExecutor.java`

**Part 3 - DateTime Functions:**
- Modified: `base/src/org/compiere/util/TimeUtil.java` (getDate, daysBetweenSql, addDaysSql, subtractDaysSql, truncSql)
- `base/test/src/org/compiere/migration/Wave0PerformanceTest.java` (created in Task 7a, tests run incrementally)

**Part 4 - Utility Functions:**
- `base/src/org/compiere/util/SqlCompat.java`
- Modified: `base/src/org/compiere/util/TimeUtil.java` (firstOf)

**Part 5 - Testing & Deployment:**
- `base/test/src/org/compiere/migration/Wave0ShadowIntegrationTest.java`
- `db/ddlutils/postgresql/migrations/002_enable_wave0_shadow.sql`
- `docs/runbooks/wave0-monitoring.md`

### Test Categories

| Tag | Purpose | Base Class | Run Command |
|-----|---------|------------|-------------|
| `@Tag("UnitTest")` | Pure logic, no DB | None | `./gradlew :base:test` |
| `@Tag("IntegrationTest")` | Requires DB | `CommonGWSetup` | `./gradlew :base:test -PintegrationTest` |
| `@Tag("PerformanceTest")` | Latency validation | `CommonGWSetup` | `./gradlew :base:test -PperformanceTest` |

---

**All Parts Complete!**

- [Part 1: Core Infrastructure](wave0-part1-core-infrastructure.md)
- [Part 2: Execution Infrastructure](wave0-part2-execution-infrastructure.md)
- [Part 3: DateTime Functions](wave0-part3-datetime-functions.md)
- [Part 4: Utility Functions](wave0-part4-utility-functions.md)
- Part 5: Testing & Deployment (this file)
