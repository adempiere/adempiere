# Wave 5 Quality Gates

This document defines the quality gates for Wave 5 BOM function migration from PostgreSQL to Java.

**Functions in Scope:**
- `bomPriceLimit` - BOM price limit calculation
- `bomPriceList` - BOM list price calculation
- `bomPriceStd` - BOM standard price calculation
- `bomQtyOnHand` - BOM quantity on hand (assemblable units)
- `bomQtyReserved` - BOM quantity reserved
- `bomQtyOrdered` - BOM quantity ordered
- `bomQtyAvailable` - BOM quantity available (OnHand - Reserved)

**Performance Tier:** Reporting (allows up to 100% latency increase over SQL)

**Validation Approach:** Three-phase testing (SQL_ONLY → SHADOW → JAVA_ONLY)

**Wave 5 Specific Considerations:**
- Recursive BOM traversal replaced with iterative stack-based approach
- Batch CTE query eliminates N+1 pattern (entire BOM tree in single query)
- Circular BOM detection via ancestor-path tracking
- Max depth configurable via AD_SysConfig `BOM_MAX_DEPTH` (default: 100)

---

## Gate 1: Code Complete

- [x] All 7 Java implementations complete in Wave5Functions.java
- [x] BOMComponent record with validation (null/negative bomQty handling)
- [x] loadBOMTree batch CTE method implemented
- [x] Wave5FunctionRouter created with ShadowExecutor integration
- [x] SqlFunctionCaller methods added for all 7 BOM functions
- [x] Unit tests pass for all functions
- [x] Circular BOM detection tests pass
- [x] Deep BOM traversal tests pass

**Status:** COMPLETE

**Evidence:**
- Implementation complete: 562118e80
- Router: 068d0a318
- Quality gates: 2d0fd261

**Key Commits:**
- `562118e80` - Performance baseline tests
- `950ffbf9c` - Migration config
- `068d0a318` - Wave5FunctionRouter
- `f75aae9de` - bomQtyOnHand implementation
- `5c30d5699` - bomPriceLimit implementation

**Acceptance Criteria:**
- All 21 implementation tasks committed
- `mvn test -pl base -Dtest="Wave5*" -q` passes
- `mvn compile -pl base -q` succeeds

---

## Gate 2: SQL_ONLY Baseline

All tests must pass against PostgreSQL functions using test database.

- [ ] All functions configured as SQL_ONLY in migration.function_config
- [ ] Performance tests created (`Wave5PerformanceTest.java`)
- [ ] Performance tests use Reporting tier threshold (<=2.0x ratio)
- [ ] Integration tests pass against SQL functions
- [ ] Performance baseline captured for each function

**Status:** NOT STARTED

**Prerequisites:**
- Gate 1 must be complete

**SQL Configuration:**
```sql
-- Set all Wave 5 functions to SQL_ONLY mode for baseline
UPDATE migration.function_config
SET mode = 'SQL_ONLY', updated = NOW()
WHERE function_name IN (
    'bomPriceLimit', 'bomPriceList', 'bomPriceStd',
    'bomQtyOnHand', 'bomQtyReserved', 'bomQtyOrdered', 'bomQtyAvailable'
);
```

**Performance Threshold (Reporting Tier):**
```java
// PASS if ratio <= 2.0x (allows 100% latency increase)
private static final double MAX_LATENCY_RATIO = 2.0;
```

**Test Execution:**
```bash
# Run Wave 5 performance tests
RUN_PERF_TESTS=true mvn test -pl base -Dtest=Wave5PerformanceTest -q
```

**Performance Baseline (Java vs SQL):**

| Function | SQL Avg (ms) | Java Avg (ms) | Overhead (ms) | Ratio | Threshold | Status |
|----------|--------------|---------------|---------------|-------|-----------|--------|
| bomPriceLimit | — | — | — | — | <=2.0x | — |
| bomPriceList | — | — | — | — | <=2.0x | — |
| bomPriceStd | — | — | — | — | <=2.0x | — |
| bomQtyOnHand | — | — | — | — | <=2.0x | — |
| bomQtyReserved | — | — | — | — | <=2.0x | — |
| bomQtyOrdered | — | — | — | — | <=2.0x | — |
| bomQtyAvailable | — | — | — | — | <=2.0x | — |

**Known Issues:**
- *To be documented during baseline testing*

**Acceptance Criteria:**
- 100% of integration tests pass against test database
- Performance baseline documented for all functions
- Wave5PerformanceTest infrastructure verified

---

## Gate 3: Router Validation (SHADOW Mode)

All tests must pass through Wave5FunctionRouter with shadow execution and logging.

- [ ] All functions configured in SHADOW mode (100% sample rate)
- [ ] Integration tests pass through router
- [ ] Performance tests pass with Reporting tier threshold
- [ ] Execution logging verified in migration.function_log
- [ ] Match rate = 100% (Java matches SQL for all routed calls)
- [ ] No critical mismatches (quantity/price fields)
- [ ] Circular BOM handling validated (no hangs, graceful detection)
- [ ] Deep BOM traversal validated (depths up to MAX_BOM_DEPTH)

**Status:** NOT STARTED

**Prerequisites:**
- Gate 2 must be complete (SQL baseline established)

**SQL Configuration:**
```sql
-- Enable SHADOW mode for all Wave 5 functions
UPDATE migration.function_config
SET mode = 'SHADOW', sample_rate = 1.0, updated = NOW()
WHERE function_name IN (
    'bomPriceLimit', 'bomPriceList', 'bomPriceStd',
    'bomQtyOnHand', 'bomQtyReserved', 'bomQtyOrdered', 'bomQtyAvailable'
);
```

**Test Execution:**
```bash
# Run all Wave 5 tests through routers
RUN_DB_TESTS=true mvn test -pl base -Dtest="Wave5*" -q

# Verify logging occurred
psql -c "SELECT function_name, COUNT(*),
         SUM(CASE WHEN is_match THEN 1 ELSE 0 END) as matches
         FROM migration.function_log
         WHERE function_name LIKE 'bom%'
         AND created_at > NOW() - INTERVAL '1 hour'
         GROUP BY function_name;"
```

**Router Validation Results:**

| Function | Total Calls | Matches | Match Rate | Status |
|----------|-------------|---------|------------|--------|
| bomPriceLimit | — | — | — | — |
| bomPriceList | — | — | — | — |
| bomPriceStd | — | — | — | — |
| bomQtyOnHand | — | — | — | — |
| bomQtyReserved | — | — | — | — |
| bomQtyOrdered | — | — | — | — |
| bomQtyAvailable | — | — | — | — |
| **TOTAL** | **—** | **—** | **—** | — |

**Circular BOM Validation:**

| Test Case | Expected Behavior | Actual | Status |
|-----------|-------------------|--------|--------|
| Product in own BOM | Graceful skip, warning logged | — | — |
| Multi-level cycle (A→B→C→A) | Detected at C, no infinite loop | — | — |
| Diamond pattern (shared components) | No false positive, both paths traversed | — | — |

**Performance Comparison (Router/Java vs SQL Baseline):**

| Function | SQL Avg (ms) | Java Avg (ms) | Overhead (ms) | Ratio | Threshold | Status |
|----------|--------------|---------------|---------------|-------|-----------|--------|
| bomPriceLimit | — | — | — | — | <=2.0x | — |
| bomPriceList | — | — | — | — | <=2.0x | — |
| bomPriceStd | — | — | — | — | <=2.0x | — |
| bomQtyOnHand | — | — | — | — | <=2.0x | — |
| bomQtyReserved | — | — | — | — | <=2.0x | — |
| bomQtyOrdered | — | — | — | — | <=2.0x | — |
| bomQtyAvailable | — | — | — | — | <=2.0x | — |

**Acceptance Criteria:**
- 100% of integration tests pass through routers
- Match rate = 100% for all functions
- Performance within Reporting tier limits (<=2.0x)
- Circular BOM detection verified (no hangs, correct results)

---

## Gate 4: JAVA_ONLY Cutover

All tests must pass against pure Java implementations (no SQL fallback).

- [ ] All functions configured in JAVA_ONLY mode
- [ ] Integration tests pass with Java-only execution
- [ ] Performance tests pass with Reporting tier threshold
- [ ] Rollback procedure documented and tested
- [ ] Stakeholder sign-off obtained

**Status:** NOT STARTED

**Prerequisites:**
- Gate 3 must be complete (router validation passed)
- 100% match rate confirmed in Gate 3

**SQL Configuration:**
```sql
-- Switch to JAVA_ONLY mode (production cutover)
UPDATE migration.function_config
SET mode = 'JAVA_ONLY', updated = NOW()
WHERE function_name IN (
    'bomPriceLimit', 'bomPriceList', 'bomPriceStd',
    'bomQtyOnHand', 'bomQtyReserved', 'bomQtyOrdered', 'bomQtyAvailable'
);
```

**Test Execution:**
```bash
# Run all Wave 5 tests with Java-only execution
mvn test -pl base -Dtest="Wave5*" -q
```

**JAVA_ONLY Results:**

| Function | Tests | Passed | Failed | Status |
|----------|-------|--------|--------|--------|
| bomPriceLimit | — | — | — | — |
| bomPriceList | — | — | — | — |
| bomPriceStd | — | — | — | — |
| bomQtyOnHand | — | — | — | — |
| bomQtyReserved | — | — | — | — |
| bomQtyOrdered | — | — | — | — |
| bomQtyAvailable | — | — | — | — |
| **TOTAL** | **—** | **—** | **—** | — |

**Performance (JAVA_ONLY vs SQL Baseline):**

| Function | SQL Avg (ms) | Java Avg (ms) | Overhead (ms) | Ratio | Threshold | Status |
|----------|--------------|---------------|---------------|-------|-----------|--------|
| bomPriceLimit | — | — | — | — | <=2.0x | — |
| bomPriceList | — | — | — | — | <=2.0x | — |
| bomPriceStd | — | — | — | — | <=2.0x | — |
| bomQtyOnHand | — | — | — | — | <=2.0x | — |
| bomQtyReserved | — | — | — | — | <=2.0x | — |
| bomQtyOrdered | — | — | — | — | <=2.0x | — |
| bomQtyAvailable | — | — | — | — | <=2.0x | — |

**Rollback Procedure:**
```sql
-- Emergency rollback: Switch all functions back to SQL_ONLY
UPDATE migration.function_config
SET mode = 'SQL_ONLY', updated = NOW()
WHERE function_name IN (
    'bomPriceLimit', 'bomPriceList', 'bomPriceStd',
    'bomQtyOnHand', 'bomQtyReserved', 'bomQtyOrdered', 'bomQtyAvailable'
);
```

**Rollback Verification:**
- [ ] Rollback tested in staging environment
- [ ] Rollback completes in < 1 minute
- [ ] All functions resume SQL execution after rollback

**Acceptance Criteria:**
- 100% of tests pass in JAVA_ONLY mode
- Performance within Reporting tier limits (<=2.0x)
- Rollback tested in staging environment
- Stakeholder approval documented

---

## Gate 5: Post-Cutover

Monitor production stability for 7 days after JAVA_ONLY cutover.

- [ ] JAVA_ONLY mode enabled in production
- [ ] Post-cutover verification passed (5 sequential test runs)
- [ ] 7 days stable operation
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
| 5   | —     | —      | —      | —        | —      |

**7-Day Monitoring Checklist:**

| Day | Date | Error Rate | p95 Latency | BOM Depth Issues | Status |
|-----|------|------------|-------------|------------------|--------|
| 1   | —    | —          | —           | —                | —      |
| 2   | —    | —          | —           | —                | —      |
| 3   | —    | —          | —           | —                | —      |
| 4   | —    | —          | —           | —                | —      |
| 5   | —    | —          | —           | —                | —      |
| 6   | —    | —          | —           | —                | —      |
| 7   | —    | —          | —           | —                | —      |

**Monitoring Queries:**
```sql
-- Daily error rate check
SELECT function_name,
       COUNT(*) as total,
       SUM(CASE WHEN error IS NOT NULL THEN 1 ELSE 0 END) as errors,
       ROUND(100.0 * SUM(CASE WHEN error IS NOT NULL THEN 1 ELSE 0 END) / COUNT(*), 2) as error_pct
FROM migration.function_log
WHERE created_at > NOW() - INTERVAL '24 hours'
  AND function_name IN ('bomPriceLimit', 'bomPriceList', 'bomPriceStd',
                        'bomQtyOnHand', 'bomQtyReserved', 'bomQtyOrdered', 'bomQtyAvailable')
GROUP BY function_name;

-- Performance check (p95 latency)
SELECT function_name,
       PERCENTILE_CONT(0.95) WITHIN GROUP (ORDER BY java_time_ms) as p95_ms
FROM migration.function_log
WHERE created_at > NOW() - INTERVAL '24 hours'
  AND function_name LIKE 'bom%'
GROUP BY function_name;

-- BOM depth distribution (Wave 5 specific)
SELECT function_name,
       MAX(COALESCE((params->>'bom_depth')::int, 0)) as max_depth_seen,
       AVG(COALESCE((params->>'bom_depth')::int, 0)) as avg_depth
FROM migration.function_log
WHERE created_at > NOW() - INTERVAL '24 hours'
  AND function_name LIKE 'bom%'
GROUP BY function_name;

-- Circular BOM detection events
SELECT created_at, function_name, params, error
FROM migration.function_log
WHERE created_at > NOW() - INTERVAL '24 hours'
  AND function_name LIKE 'bom%'
  AND error LIKE '%circular%'
ORDER BY created_at DESC
LIMIT 20;
```

**Cleanup (After 30 days stable):**
- [ ] Disable shadow execution code paths
- [ ] Remove SqlFunctionCaller methods for Wave 5
- [ ] Archive shadow validation logs
- [ ] SQL functions retained for emergency rollback (do not delete)

**Acceptance Criteria:**
- Zero rollbacks during 7-day period
- Error rate < 0.1% for all functions
- p95 latency stable (no degradation trend)
- No circular BOM detection issues in production
- No critical production incidents

---

## Current Status Summary

| Gate | Status | Blocker |
|------|--------|---------|
| Gate 1: Code Complete | COMPLETE | — |
| Gate 2: SQL_ONLY Baseline | NOT STARTED | — |
| Gate 3: Router Validation | NOT STARTED | Depends on Gate 2 |
| Gate 4: JAVA_ONLY Cutover | NOT STARTED | Depends on Gate 3 |
| Gate 5: Post-Cutover | NOT STARTED | Depends on Gate 4 |

**Next Action:** Begin Gate 2: SQL_ONLY Baseline testing

---

## References

- **Implementation Plan:** `docs/plans/2026-01-09-wave5-bom-migration-design.md`
- **Wave 4 Quality Gates (template):** `docs/plans/wave4-quality-gates.md`
- **Migration Config:** `db/ddlutils/postgresql/migrations/wave5_config.sql`
- **Wave 4 Call Sites (pattern reference):** `docs/plans/wave4-call-sites.md`

---

## Change Log

| Date | Change | Author |
|------|--------|--------|
| 2026-01-10 | Initial quality gates document created | Claude |
