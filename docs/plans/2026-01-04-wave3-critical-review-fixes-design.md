# Wave 3 Critical Review Fixes - Design Document

**Date:** 2026-01-04
**Status:** Ready for implementation
**Scope:** Apply fixes from critical reviews R1, R2, R3 to Wave 3 implementation plan

---

## Summary

Apply 13 unique fixes from 3 critical reviews (R1, R2, R3) to `docs/plans/2026-01-03-wave3-financial-core-implementation.md`. R4 fixes were already applied.

---

## Conflict Resolution

**IsActive filters (R1 vs R3):**
- R1: Remove IsActive filters from paymentAllocated (SQL has none)
- R3: Add IsActive filters to invoiceOpen (for consistency)
- **Resolution:** Follow SQL exactly. NO IsActive filters in Java. Remove from paymentAllocated, don't add to invoiceOpen.

---

## Fixes to Apply

### Phase 1: Core Correctness (P0)

| # | Fix | Location | Change |
|---|-----|----------|--------|
| 1 | Conversion type = null | Task 2.1 ~line 494 | `getC_ConversionType_ID()` → `null` |
| 2 | Remove IsActive filters | Task 2.1 ~line 476 | Delete `AND a.IsActive='Y' AND al.IsActive='Y'` |
| 3 | Null safety | Tasks 2.1, 3.1, 4.2 | Add null checks for Amount, DiscountAmt, WriteOffAmt |
| 4 | Currency precision | Task 3.1 ~lines 1006, 1014 | `setScale(2, ...)` → `setScale(precision, ...)` where precision = `MCurrency.getStdPrecision()` |
| 5 | Scale order | Task 3.1 ~line 1014 | `.setScale(2).multiply(mult)` → `.multiply(mult).setScale(precision)` |
| 6 | Payment schedule loop | Task 4.2 ~lines 1618-1625 | Fix zero-floor check to match SQL |

### Phase 2: Method Signatures

| # | Fix | Location | Change |
|---|-----|----------|--------|
| 7 | trxName parameter | All calculateXxxJava methods | Add `String trxName` param, use in `DB.prepareStatement(sql, trxName)` |

### Phase 3: Missing Implementation

| # | Fix | Location | Change |
|---|-----|----------|--------|
| 8 | invoiceOpenToDate full code | Task 4.3 ~lines 1714-1718 | Replace `"..."` with full implementation (copy invoiceOpen pattern, add DateAcct filter) |

### Phase 4: Test Infrastructure

| # | Fix | Location | Change |
|---|-----|----------|--------|
| 9 | Dynamic test IDs | Task 1.1 tests | Replace hardcoded `109`, `100`, `11` with `@BeforeAll` queries |
| 10 | Performance test bypass | Tasks 2.3, 3.3, 4.4 | Add `JAVA_ONLY` mode or direct method calls |

### Phase 5: Configuration

| # | Fix | Location | Change |
|---|-----|----------|--------|
| 11 | Tolerance comparator | All ShadowExecutor.execute() calls | Replace exact `compareTo(sql) == 0` with 0.01 tolerance |
| 12 | Sample rate preservation | Task 7.2 SQL | Use CASE statement to preserve per-function rates |
| 13 | Wave 2 dependency check | Task 5.1 | Add verification step for PaymentTermFunctions |

---

## Detailed Fix Specifications

### Fix 3: Null Safety Pattern

```java
// Before
BigDecimal amount = rs.getBigDecimal("Amount");
BigDecimal discountAmt = rs.getBigDecimal("DiscountAmt");
BigDecimal writeOffAmt = rs.getBigDecimal("WriteOffAmt");

// After
BigDecimal amount = rs.getBigDecimal("Amount");
if (amount == null) amount = BigDecimal.ZERO;
BigDecimal discountAmt = rs.getBigDecimal("DiscountAmt");
if (discountAmt == null) discountAmt = BigDecimal.ZERO;
BigDecimal writeOffAmt = rs.getBigDecimal("WriteOffAmt");
if (writeOffAmt == null) writeOffAmt = BigDecimal.ZERO;
```

### Fix 4 & 5: Currency Precision and Scale Order

```java
// Before
return paymentAmt.setScale(2, RoundingMode.HALF_UP).multiply(mult);

// After
MCurrency currency = MCurrency.get(Env.getCtx(), currencyId);
int precision = currency != null ? currency.getStdPrecision() : 2;
return paymentAmt.multiply(mult).setScale(precision, RoundingMode.HALF_UP);
```

### Fix 6: Payment Schedule Loop

```java
// Before (buggy)
if (schedId == invoicePayScheduleId) {
    totalOpenAmt = dueAmt.multiply(multiplierCM).subtract(remaining);
    if (dueAmt.subtract(remaining).compareTo(BigDecimal.ZERO) < 0) {
        totalOpenAmt = BigDecimal.ZERO;
    }
    break;
}

// After (matches SQL)
if (schedId == invoicePayScheduleId) {
    BigDecimal scheduleOpen = dueAmt.multiply(multiplierCM).subtract(remaining);
    if (scheduleOpen.compareTo(BigDecimal.ZERO) < 0) {
        scheduleOpen = BigDecimal.ZERO;
    }
    totalOpenAmt = scheduleOpen;
    break;
}
```

### Fix 9: Dynamic Test IDs

```java
// Before
BigDecimal result = SqlFunctionCaller.callInvoiceOpen(109, null);

// After
private static int testInvoiceId;
private static int testPaymentId;

@BeforeAll
static void findTestData() {
    testInvoiceId = new Query(Env.getCtx(), "C_Invoice", "DocStatus IN ('CO','CL')", null)
        .setOnlyActiveRecords(true).firstId();
    Assume.assumeTrue(testInvoiceId > 0, "Need completed invoice for test");

    testPaymentId = new Query(Env.getCtx(), "C_Payment", "DocStatus IN ('CO','CL')", null)
        .setOnlyActiveRecords(true).firstId();
    Assume.assumeTrue(testPaymentId > 0, "Need completed payment for test");
}

@Test
void callInvoiceOpen_returnsNumeric() {
    assertDoesNotThrow(() -> SqlFunctionCaller.callInvoiceOpen(testInvoiceId, null));
}
```

### Fix 11: Tolerance Comparator

```java
// Before
ShadowExecutor.execute(
    "invoiceOpen",
    () -> calculateInvoiceOpenJava(...),
    () -> SqlFunctionCaller.callInvoiceOpen(...),
    (java, sql) -> java.compareTo(sql) == 0
);

// After
private static final BigDecimal TOLERANCE = new BigDecimal("0.01");

ShadowExecutor.execute(
    "invoiceOpen",
    () -> calculateInvoiceOpenJava(...),
    () -> SqlFunctionCaller.callInvoiceOpen(...),
    (java, sql) -> {
        if (java == null && sql == null) return true;
        if (java == null || sql == null) return false;
        return java.subtract(sql).abs().compareTo(TOLERANCE) <= 0;
    }
);
```

### Fix 12: Sample Rate Preservation

```sql
-- Before
UPDATE migration.function_config
SET mode = 'SHADOW'
WHERE function_name IN ('invoiceOpen', 'invoiceOpenToDate', ...);

-- After
UPDATE migration.function_config
SET mode = 'SHADOW',
    sample_rate = CASE
        WHEN function_name IN ('invoiceOpen', 'invoiceOpenToDate') THEN 0.1
        ELSE COALESCE(sample_rate, 1.0)
    END
WHERE function_name IN ('invoiceOpen', 'invoiceOpenToDate', ...);
```

---

## Design Decisions

1. **invoiceOpenToDate implementation:** Follow existing pattern (separate method mirroring invoiceOpen with DateAcct filter). Matches invoicePaid/invoicePaidToDate structure. Refactor to shared helper deferred per YAGNI.

2. **IsActive filters:** Removed to match SQL exactly. SQL functions do not filter by IsActive, so Java shouldn't either.

3. **Tolerance threshold:** 0.01 chosen as standard financial precision. Catches real bugs while tolerating minor rounding differences.

---

## Excluded Items

These were identified in reviews but deferred:

- **N+1 query optimization** - Document and defer. Current implementation acceptable for initial release.
- **Circuit breaker implementation** - Documentation only. Infrastructure exists but Java logic deferred.
- **Code duplication refactoring** - Minor issue, address in future cleanup.

---

## References

- `docs/plans/2026-01-03-wave3-financial-core-implementation.md` - Target plan
- `docs/plans/2026-01-03-wave3-financial-core-implementation-critical-review-1.md` - R1
- `docs/plans/2026-01-03-wave3-financial-core-implementation-critical-review-2.md` - R2
- `docs/plans/2026-01-03-wave3-financial-core-implementation-critical-review-3.md` - R3
- `docs/plans/2026-01-03-wave3-financial-core-implementation-critical-review-4.md` - R4 (already applied)
- `thoughts/shared/handoffs/general/2026-01-04_00-51-04_wave3-apply-critical-reviews.md` - Handoff doc
