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
