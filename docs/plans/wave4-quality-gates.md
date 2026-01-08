# Wave 4 Quality Gates

This document defines the quality gates for Wave 4 standalone functions migration from PostgreSQL to Java.

**Functions in Scope:**
- `nextID` / `nextIDFunc` (sequence functions - special handling via NextIDRouter)
- `acctBalance`
- `getSysconfig` (note: MSysConfig already implements in Java)
- `productAttribute`
- `documentNo`
- `linenetamtrealinvoiceline`
- `linenetamtrealorderline`
- `maxpaydate`

**Validation Approach:** Three-phase testing (SQL_ONLY → Router → JAVA_ONLY)

---

## Gate 1: Code Complete

- [ ] All 9 Java implementations complete in Wave4Functions.java
- [ ] StringComparator infrastructure created
- [ ] SqlFunctionCaller methods added for all 7 SQL-callable functions
- [ ] NextIDRouter created with execution logging
- [ ] Wave4FunctionRouter created with ShadowExecutor integration
- [ ] Unit tests pass for all functions
- [ ] View dependency analysis complete (Task 0.2)

**Status:** NOT STARTED

**Evidence Required:**
- Commits for each task group (0-5)
- Unit test results: `Wave4FunctionsTest`, `NextIDRouterTest`, `Wave4FunctionRouterTest`
- View dependency documentation in design doc

---

## Gate 2: SQL_ONLY Baseline

All tests must pass against PostgreSQL functions using Garden World test database.

- [ ] All functions configured as SQL_ONLY in migration.function_config
- [ ] **Performance tests created** (`Wave4PerformanceTest.java` extending `CommonGWSetup`)
- [ ] Performance tests use variable threshold approach (from Wave 3)
- [ ] Integration tests pass against SQL functions (Garden World)
- [ ] Performance baseline captured for each function

**Status:** NOT STARTED

**Prerequisites:**
- Gate 1 must be complete
- `Wave4PerformanceTest.java` created with variable threshold logic
- Garden World test database available

**Variable Threshold Approach (from Wave 3):**
```java
// PASS if (overhead < 1.0ms AND ratio <= 3.0x) OR (ratio <= 1.5x)
private static final double RELAXED_RATIO = 3.0;
private static final double STRICT_RATIO = 1.5;
private static final double MAX_OVERHEAD_MS = 1.0;
```

**Test Execution:**
```bash
# Ensure SQL_ONLY mode
psql -c "UPDATE migration.function_config SET mode = 'SQL_ONLY' WHERE function_name IN
         ('nextID', 'nextIDFunc', 'acctBalance', 'productAttribute', 'documentNo',
          'get_Sysconfig', 'linenetamtrealinvoiceline', 'linenetamtrealorderline', 'maxpaydate');"

# Run all Wave 4 tests against Garden World
./gradlew :base:test --tests "Wave4*"
```

**Performance Baseline (SQL_ONLY, Garden World):**

| Function | SQL Avg (ms) | SQL p95 (ms) | Calls | Status |
|----------|--------------|--------------|-------|--------|
| acctBalance | — | — | 500 | — |
| productAttribute | — | — | 500 | — |
| documentNo | — | — | 500 | — |
| linenetamtrealinvoiceline | — | — | 500 | — |
| linenetamtrealorderline | — | — | 500 | — |
| maxpaydate | — | — | 500 | — |

*Note: nextID/nextIDFunc excluded from performance comparison (stateful)*

**Acceptance Criteria:**
- 100% of integration tests pass against Garden World
- Performance baseline documented
- Wave4PerformanceTest infrastructure verified

---

## Gate 3: Router Validation

All tests must pass through NextIDRouter/Wave4FunctionRouter with execution logging.

- [ ] All functions configured in SHADOW mode (routers enabled)
- [ ] Integration tests pass through routers (Garden World)
- [ ] Performance tests pass with variable threshold
- [ ] Execution logging verified in migration.function_log
- [ ] Match rate = 100% (Java matches SQL for all routed calls)
- [ ] No critical mismatches (money/ID fields)

**Status:** NOT STARTED

**Prerequisites:**
- Gate 2 must be complete (SQL baseline established)

**Mode Configuration:**
```sql
-- Enable SHADOW mode for all Wave 4 functions
UPDATE migration.function_config
SET mode = 'SHADOW', updated = NOW()
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
);
```

**Test Execution:**
```bash
# Run all Wave 4 tests through routers
./gradlew :base:test --tests "Wave4*"

# Verify logging occurred
psql -c "SELECT function_name, COUNT(*),
         SUM(CASE WHEN is_match THEN 1 ELSE 0 END) as matches
         FROM migration.function_log
         WHERE function_name LIKE '%'
         AND created_at > NOW() - INTERVAL '1 hour'
         GROUP BY function_name;"
```

**Router Validation Results (Garden World):**

| Function | Total Calls | Matches | Match Rate | Status |
|----------|-------------|---------|------------|--------|
| nextID | — | — | N/A (logged only) | — |
| nextIDFunc | — | — | N/A (logged only) | — |
| acctBalance | — | — | — | — |
| productAttribute | — | — | — | — |
| documentNo | — | — | — | — |
| linenetamtrealinvoiceline | — | — | — | — |
| linenetamtrealorderline | — | — | — | — |
| maxpaydate | — | — | — | — |

**Performance Comparison (Router vs SQL Baseline):**

| Function | SQL Avg (ms) | Router Avg (ms) | Overhead (ms) | Ratio | Threshold | Status |
|----------|--------------|-----------------|---------------|-------|-----------|--------|
| acctBalance | — | — | — | — | — | — |
| productAttribute | — | — | — | — | — | — |
| documentNo | — | — | — | — | — | — |
| linenetamtrealinvoiceline | — | — | — | — | — | — |
| linenetamtrealorderline | — | — | — | — | — | — |
| maxpaydate | — | — | — | — | — | — |

**Acceptance Criteria:**
- 100% of integration tests pass through routers
- Match rate = 100% for all comparison-capable functions
- Performance within variable threshold limits
- nextID/nextIDFunc execution logging verified (no comparison)

---

## Gate 4: JAVA_ONLY Cutover

All tests must pass against pure Java implementations (no SQL fallback).

- [ ] All functions configured in JAVA_ONLY mode
- [ ] Integration tests pass with Java-only execution (Garden World)
- [ ] Performance tests pass with variable threshold
- [ ] Rollback procedure documented and tested
- [ ] Stakeholder sign-off obtained

**Status:** NOT STARTED

**Prerequisites:**
- Gate 3 must be complete (router validation passed)
- 100% match rate confirmed in Gate 3

**Mode Configuration:**
```sql
-- Switch to JAVA_ONLY mode (production cutover)
UPDATE migration.function_config
SET mode = 'JAVA_ONLY', updated = NOW()
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
);
```

**Test Execution:**
```bash
# Run all Wave 4 tests with Java-only execution
./gradlew :base:test --tests "Wave4*"
```

**JAVA_ONLY Results (Garden World):**

| Function | Tests | Passed | Failed | Status |
|----------|-------|--------|--------|--------|
| nextID | — | — | — | — |
| nextIDFunc | — | — | — | — |
| acctBalance | — | — | — | — |
| productAttribute | — | — | — | — |
| documentNo | — | — | — | — |
| linenetamtrealinvoiceline | — | — | — | — |
| linenetamtrealorderline | — | — | — | — |
| maxpaydate | — | — | — | — |

**Performance (JAVA_ONLY vs SQL Baseline):**

| Function | SQL Avg (ms) | Java Avg (ms) | Overhead (ms) | Ratio | Threshold | Status |
|----------|--------------|---------------|---------------|-------|-----------|--------|
| acctBalance | — | — | — | — | — | — |
| productAttribute | — | — | — | — | — | — |
| documentNo | — | — | — | — | — | — |
| linenetamtrealinvoiceline | — | — | — | — | — | — |
| linenetamtrealorderline | — | — | — | — | — | — |
| maxpaydate | — | — | — | — | — | — |

**Rollback Procedure:**
```sql
-- Emergency rollback: Switch all functions back to SQL_ONLY
UPDATE migration.function_config
SET mode = 'SQL_ONLY', updated = NOW()
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
);
```

**Acceptance Criteria:**
- 100% of tests pass in JAVA_ONLY mode
- Performance within variable threshold limits
- Rollback tested in staging environment
- Stakeholder approval documented

---

## Gate 5: Post-Cutover

Monitor production stability for 7 days after JAVA_ONLY cutover.

- [ ] JAVA_ONLY mode enabled in production
- [ ] Post-cutover verification passed (5 sequential test runs)
- [ ] 7 days stable operation (monitor until DATE_TBD)
- [ ] No rollbacks triggered
- [ ] Shadow execution disabled
- [ ] SQL functions retained (30-day retention for emergency rollback)

**Status:** NOT STARTED

**Prerequisites:**
- Gate 4 must be complete (cutover approved and executed)
- Production system running in JAVA_ONLY mode

**Post-Cutover Verification (5 Sequential Runs):**

| Run | Tests | Passed | Failed | Duration | Result |
|-----|-------|--------|--------|----------|--------|
| 1   | —     | —      | —      | —        | —      |
| 2   | —     | —      | —      | —        | —      |
| 3   | —     | —      | —      | —        | —      |
| 4   | —     | —      | —      | —        | —      |
| 5   | —     | —     | —      | —        | —      |

**7-Day Monitoring Checklist:**

| Day | Date | Error Rate | p95 Latency | Issues | Status |
|-----|------|------------|-------------|--------|--------|
| 1   | —    | —          | —           | —      | —      |
| 2   | —    | —          | —           | —      | —      |
| 3   | —    | —          | —           | —      | —      |
| 4   | —    | —          | —           | —      | —      |
| 5   | —    | —          | —           | —      | —      |
| 6   | —    | —          | —           | —      | —      |
| 7   | —    | —          | —           | —      | —      |

**Monitoring Queries:**
```sql
-- Daily error rate check
SELECT function_name,
       COUNT(*) as total,
       SUM(CASE WHEN error IS NOT NULL THEN 1 ELSE 0 END) as errors,
       ROUND(100.0 * SUM(CASE WHEN error IS NOT NULL THEN 1 ELSE 0 END) / COUNT(*), 2) as error_pct
FROM migration.function_log
WHERE created_at > NOW() - INTERVAL '24 hours'
  AND function_name IN ('nextID', 'acctBalance', 'productAttribute',
                        'documentNo', 'linenetamtrealinvoiceline',
                        'linenetamtrealorderline', 'maxpaydate')
GROUP BY function_name;

-- Performance check (p95 latency)
SELECT function_name,
       PERCENTILE_CONT(0.95) WITHIN GROUP (ORDER BY java_time_ms) as p95_ms
FROM migration.function_log
WHERE created_at > NOW() - INTERVAL '24 hours'
GROUP BY function_name;
```

**Cleanup (After 30 days stable):**
- [ ] Disable shadow execution code paths
- [ ] Remove SqlFunctionCaller methods for Wave 4
- [ ] Archive shadow validation logs
- [ ] SQL functions retained for emergency rollback (do not delete)

**Acceptance Criteria:**
- Zero rollbacks during 7-day period
- Error rate < 0.1% for all functions
- p95 latency stable (no degradation trend)
- No critical production incidents

---

## Current Status Summary

| Gate | Status | Blocker |
|------|--------|---------|
| Gate 1: Code Complete | NOT STARTED | Implementation in progress |
| Gate 2: SQL_ONLY Baseline | NOT STARTED | Requires Gate 1, Performance tests needed |
| Gate 3: Router Validation | NOT STARTED | Requires Gate 2 |
| Gate 4: JAVA_ONLY Cutover | NOT STARTED | Requires Gate 3 |
| Gate 5: Post-Cutover | NOT STARTED | Requires Gate 4 |

**Next Action:** Complete Gate 1 (all 23 tasks in implementation plan)

---

## References

- **Implementation Plan:** `docs/plans/2026-01-07-wave4-implementation.md`
- **Design Document:** `docs/plans/2026-01-07-wave4-design.md`
- **Call Sites:** `docs/plans/wave4-call-sites.md`
- **Migration Config:** `migration/sql/wave4-function-config.sql`
- **Performance Threshold Design:** `docs/plans/2026-01-07-wave3-performance-threshold-design.md` (Wave 3, applies to Wave 4)
- **Wave 3 Quality Gates:** `docs/plans/wave3-quality-gates.md` (template)

---

## Change Log

| Date | Change | Author |
|------|--------|--------|
| 2026-01-08 | Initial quality gates document created | Claude |
