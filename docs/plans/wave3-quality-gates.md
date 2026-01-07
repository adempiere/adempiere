# Wave 3 Quality Gates

This document defines the quality gates that must be passed before cutover from SQL to Java for the Wave 3 financial core functions.

**Functions in Scope:**
- `invoiceOpen` / `invoiceOpenToDate`
- `invoicePaid` / `invoicePaidToDate`
- `invoiceDiscount`
- `paymentAllocated` / `paymentAvailable`

---

## Gate 1: Code Complete

- [x] Java implementation matches SQL logic for all 7 functions
- [x] Payment schedule logic ported to invoiceOpen
- [x] Unit tests cover edge cases
- [x] Integration tests pass (Wave3ShadowIntegrationTest)

**Status:** COMPLETE

**Evidence:**
- Commit `30f1e64df` - All 7 functions implemented with shadow execution
- Integration test: `Wave3ShadowIntegrationTest` - 75 tests passing
- Unit tests: `Wave3InvoiceFunctionsTest` - 10 tests passing
- Unit tests: `Wave3PaymentFunctionsTest` - 4 tests passing (2 aborted due to test data)

---

## Gate 2: Validation Ready

- [x] All functions in SHADOW mode
- [x] Performance baseline captured
- [ ] **All functions <= 130% of SQL p95** ⚠️ **BLOCKED - KNOWN ISSUE**
- [x] Sampling rates configured (10% for high-volume)

**Status:** BLOCKED (Performance issue)

**Evidence:**
- Commit `46a78a82d` - SHADOW mode enabled for all 7 functions
- Migration script: `003_wave3_function_config.sql` with 10% sampling for invoiceOpen/invoiceOpenToDate
- Performance baseline: See `docs/plans/wave3-performance-baseline.md`

**Known Issues:**

### Performance Regression (BLOCKING)

All performance tests are **FAILING**. Java implementations are 1.5-2x slower than SQL (target: ≤1.30x).

**Measured Ratios (Median):**

| Function | Java/SQL Ratio | Target | Status |
|----------|----------------|--------|--------|
| invoiceDiscount | 1.85x | ≤1.30x | FAIL |
| invoiceDiscount (with schedule) | 1.90x | ≤1.30x | FAIL |
| invoiceOpen | ~1.5-1.8x | ≤1.30x | FAIL |
| invoiceOpen (with schedule) | ~1.6-2.0x | ≤1.30x | FAIL |
| invoicePaid | ~1.5-1.7x | ≤1.30x | FAIL |
| paymentAllocated | ~1.5-1.8x | ≤1.30x | FAIL |
| paymentAvailable | ~1.5-1.7x | ≤1.30x | FAIL |

**Root Cause Analysis Required:**
- Database query inefficiency (N+1 queries?)
- Excessive object allocation
- Missing caching/memoization
- Suboptimal JDBC usage

**This gate CANNOT be passed until performance is optimized to meet the 130% threshold.**

---

## Gate 3: Shadow Validation

- [ ] 7 days of shadow execution complete
- [ ] Match rate >= 99.9% for all functions
- [ ] No critical mismatches (money/ID fields)
- [ ] Performance stable (no degradation trend)

**Status:** NOT STARTED (Blocked by Gate 2)

**Prerequisites:**
- Gate 2 must pass (performance threshold met)
- Shadow mode enabled in production environment
- Monitoring dashboards deployed

**Validation Procedure:**
1. Enable SHADOW mode for all 7 functions in production
2. Monitor `migration.function_log` table for 7 days
3. Calculate match rate: `COUNT(java_result = sql_result) / COUNT(*)`
4. Investigate any mismatches in money/ID fields as P0 bugs
5. Verify p95 latency remains stable over 7 days

**Acceptance Criteria:**
- Match rate >= 99.9% for each function individually
- Zero critical mismatches (money amounts, IDs differ)
- No performance degradation trend (p95 <= 130% baseline)

---

## Gate 4: Cutover Approved

- [ ] Shadow validation passed
- [ ] Rollback procedure tested
- [ ] Stakeholder sign-off obtained
- [ ] Monitoring dashboards ready

**Status:** NOT STARTED (Blocked by Gate 2)

**Prerequisites:**
- Gate 3 must pass (shadow validation successful)
- Rollback plan documented and tested
- Business stakeholders informed of cutover plan

**Required Artifacts:**
1. **Shadow Validation Report** - 7-day analysis showing match rates, performance, mismatches
2. **Rollback Plan** - Procedure to revert to SQL_ONLY mode if issues arise
3. **Monitoring Setup** - Dashboards for real-time match rate, performance tracking
4. **Stakeholder Sign-Off** - Written approval from business owners

**Rollback Procedure:**
```sql
-- Emergency rollback: Switch all functions back to SQL_ONLY
UPDATE migration.function_config
SET mode = 'SQL_ONLY'
WHERE function_name IN (
    'invoiceOpen', 'invoiceOpenToDate', 'invoiceDiscount',
    'invoicePaid', 'invoicePaidToDate',
    'paymentAllocated', 'paymentAvailable'
);
```

Test rollback in staging environment before production cutover.

---

## Gate 5: Post-Cutover

- [ ] JAVA_ONLY mode enabled
- [ ] 7 days stable operation
- [ ] Shadow execution disabled
- [ ] SQL functions retained (30-day retention)

**Status:** NOT STARTED (Blocked by Gate 2)

**Prerequisites:**
- Gate 4 must pass (cutover approved and executed)
- Production system running in JAVA_ONLY mode
- No incidents or rollbacks triggered

**Cutover Execution:**
```sql
-- Switch to JAVA_ONLY mode (production cutover)
UPDATE migration.function_config
SET mode = 'JAVA_ONLY',
    updated_at = NOW()
WHERE function_name IN (
    'invoiceOpen', 'invoiceOpenToDate', 'invoiceDiscount',
    'invoicePaid', 'invoicePaidToDate',
    'paymentAllocated', 'paymentAvailable'
);
```

**Post-Cutover Monitoring (7 days):**
- Monitor application logs for exceptions
- Track performance metrics (p95 latency)
- Verify business metrics unchanged (revenue, invoice counts)
- Monitor database load (should decrease)

**Cleanup (After 30 days):**
- Disable shadow execution entirely (remove ShadowExecutor wrapping)
- SQL functions can be retained indefinitely for emergency rollback
- Archive shadow validation logs to long-term storage

---

## Current Status Summary

| Gate | Status | Blocker |
|------|--------|---------|
| Gate 1: Code Complete | ✅ COMPLETE | None |
| Gate 2: Validation Ready | ⚠️ BLOCKED | Performance regression (1.5-2x slower) |
| Gate 3: Shadow Validation | ❌ NOT STARTED | Blocked by Gate 2 |
| Gate 4: Cutover Approved | ❌ NOT STARTED | Blocked by Gate 2 |
| Gate 5: Post-Cutover | ❌ NOT STARTED | Blocked by Gate 2 |

**Next Action:** Performance optimization work required before proceeding to shadow validation.

---

## Test Results

### Functional Tests (PASSING)

```
Wave3InvoiceFunctionsTest:        10 tests, 0 failures ✅
Wave3PaymentFunctionsTest:         4 tests, 0 failures, 2 aborted (test data)
Wave3ShadowIntegrationTest:       75 tests, 0 failures ✅
```

### Performance Tests (FAILING)

```
Wave3InvoiceDiscountPerformanceTest: 10 tests, 2 failures ❌
Wave3InvoiceOpenPerformanceTest:     15 tests, 3 failures ❌
Wave3InvoicePaidPerformanceTest:     10 tests, 2 failures ❌
Wave3PaymentPerformanceTest:         10 tests, 2 failures ❌
```

**Failure Reason:** Java implementations exceed 1.30x performance threshold (median ratios: 1.5-2.0x).

---

## References

- **Implementation Plan:** `docs/plans/2026-01-03-wave3-financial-core-implementation.md`
- **Performance Baseline:** `docs/plans/wave3-performance-baseline.md`
- **Migration Config:** `db/ddlutils/postgresql/migrations/003_wave3_function_config.sql`
- **Integration Test:** `base/test/src/org/compiere/migration/Wave3ShadowIntegrationTest.java`
- **Architecture Design:** `docs/plans/2026-01-01-postgresql-function-migration-design.md`

---

## Change Log

| Date | Commit | Change | Author |
|------|--------|--------|--------|
| 2026-01-07 | `46a78a82d` | Enable SHADOW mode for all 7 functions | Claude |
| 2026-01-07 | `0c5fbe383` | Add performance test infrastructure | Claude |
| 2026-01-07 | `2b3d69f1a` | Add comprehensive shadow integration test | Claude |
| 2026-01-07 | TBD | Create quality gate checklist (this document) | Claude |
