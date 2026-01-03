# Wave 3: Financial Core - Implementation Summary

**Date:** 2026-01-03
**Wave:** 3 of 6
**Goal:** Migrate core invoice and payment functions to Java
**Dependencies:** Wave 1 (currencyConvert), Wave 2 (paymentTermDiscount)

---

## Functions to Migrate

| Priority | Function | SQL File | LOC | Complexity | Known Duplicate |
|----------|----------|----------|-----|------------|-----------------|
| 1 | invoiceOpen | C_Invoice_Open.sql | 121 | High | MInvoice.getOpenAmt() |
| 2 | invoiceOpenToDate | C_Invoice_OpenToDate.sql | 126 | High | - |
| 3 | invoiceDiscount | C_Invoice_Discount.sql | 83 | Medium | - |
| 4 | invoicePaid | C_Invoice_Paid.sql | 65 | Medium | - |
| 5 | invoicePaidToDate | C_Invoice_PaidToDate.sql | 74 | Medium | - |
| 6 | paymentAllocated | C_Payment_Allocated.sql | 64 | Medium | - |
| 7 | paymentAvailable | C_Payment_Available.sql | 66 | Medium | - |

---

## Dependency Chain

```
currencyConvert (Wave 1) <-- invoiceOpen --> invoiceOpenToDate
                               ^
                          invoicePaid --> invoicePaidToDate
                               ^
                     paymentAllocated
                               ^
                     paymentAvailable

paymentTermDiscount (Wave 2) <-- invoiceDiscount
```

---

## Critical Issue: invoiceOpen Divergence

`MInvoice.getOpenAmt()` (Java) diverges from `C_Invoice_Open` (SQL):
- Java has empty TODO blocks for payment schedule logic
- SQL implements ~30 lines of payment schedule handling

**Resolution:** Enhance Java to match SQL behavior:
1. Port `C_InvoicePaySchedule` iteration logic from SQL to Java
2. Use request-scoped cache (not field-level) to avoid invalidation complexity
3. Add cache scope configuration for batch processing contexts

---

## Validation Strategy

| Function | Strategy | Sample Rate | Rationale |
|----------|----------|-------------|-----------|
| invoiceOpen | Shadow | 10% | High call volume, known divergence to fix |
| invoiceOpenToDate | Shadow | 10% | Same pattern as invoiceOpen |
| invoiceDiscount | Shadow | 100% | Lower frequency |
| invoicePaid | Shadow | 100% | Lower frequency |
| invoicePaidToDate | Shadow | 100% | Lower frequency |
| paymentAllocated | Shadow | 100% | Lower frequency |
| paymentAvailable | Shadow | 100% | Lower frequency |

---

## Views Affected

Views that must migrate with Wave 3 functions:

| View | Depends On |
|------|------------|
| RV_OPENITEM.sql | invoiceOpen, currencyConvert |
| RV_BPARTNEROPEN.sql | invoiceOpen |
| RV_PAYMENT.sql | paymentAllocated, paymentAvailable |

---

## Implementation Pattern

All functions follow the Model class pattern:

```java
// MInvoice.java
public BigDecimal getOpenAmt() {
    return ShadowExecutor.execute(
        "C_Invoice_Open",
        () -> calculateOpenAmtJava(),
        () -> SqlFunctionCaller.invokeOpen(getC_Invoice_ID()),
        (j, s) -> j.compareTo(s) == 0
    );
}

// MPayment.java
public BigDecimal getAllocatedAmt() {
    return ShadowExecutor.execute(
        "C_Payment_Allocated",
        () -> calculateAllocatedJava(),
        () -> SqlFunctionCaller.invokePaymentAllocated(getC_Payment_ID()),
        (j, s) -> j.compareTo(s) == 0
    );
}
```

---

## Cache Strategy

Request-scoped cache with configurable scope:

```java
public enum CacheScope {
    REQUEST,      // Clear on HTTP request completion (default)
    TRANSACTION,  // Clear on commit/rollback
    BATCH,        // No caching (for long-running batch jobs)
    EXPLICIT      // Clear only via explicit invalidate() call
}
```

For batch processing (ImportInvoice, AllocationAuto):
```java
RequestCache.setScope(CacheScope.BATCH);  // Disables caching
```

---

## Success Criteria

- [ ] `invoiceOpen` shadow validation against `MInvoice.getOpenAmt()` - mismatches resolved
- [ ] Payment schedule logic ported to Java
- [ ] All allocation calculations match SQL
- [ ] 99.9% match rate for 7 consecutive days
- [ ] Performance within 30% of SQL baseline (p95)

---

## Risk Areas

| Risk | Severity | Mitigation |
|------|----------|------------|
| invoiceOpen has known Java duplicate with missing payment schedule logic | High | Port SQL logic to Java before shadow mode |
| High call volume for invoiceOpen/invoicePaid | Medium | 10% sampling, circuit breaker protection |
| Views depend on accurate open amount calculations | High | Migrate views together as transaction unit |

---

## Quality Gates

### Gate 1: Code Complete
- [ ] Java implementation matches SQL logic for all 7 functions
- [ ] Payment schedule logic implemented in MInvoice.getOpenAmt()
- [ ] Unit tests cover edge cases (multi-currency, partial payments, schedules)
- [ ] Integration tests pass

### Gate 2: Validation Ready
- [ ] Feature flags set to SHADOW for all functions
- [ ] Performance baseline captured
- [ ] Java within 30% of SQL p95 for all functions
- [ ] Rollback drill completed

### Gate 3: Cutover Approved
- [ ] 99.9% match rate achieved for all functions
- [ ] 7 consecutive days stable
- [ ] No critical mismatches unresolved
- [ ] Dependent views migrated (RV_OPENITEM, RV_BPARTNEROPEN, RV_PAYMENT)

### Gate 4: Cleanup Complete
- [ ] Feature flags set to JAVA_ONLY
- [ ] SQL functions retained in git (deleted after 30 days stable)
- [ ] Monitoring confirms no errors

---

## Performance Tier

All Wave 3 functions are **Standard** tier (30% max latency increase).

Exception: If invoiceOpen call frequency exceeds 1000/day in production, consider switching to dual-write logging strategy.

---

## Appendix: Function Signatures

```sql
-- C_Invoice_Open(p_C_Invoice_ID, p_C_InvoicePaySchedule_ID)
-- Returns open amount for invoice, optionally filtered by payment schedule

-- C_Invoice_OpenToDate(p_C_Invoice_ID, p_date)
-- Returns open amount as of specific date

-- C_Invoice_Discount(p_C_Invoice_ID, p_PayDate, p_C_InvoicePaySchedule_ID)
-- Returns available discount amount

-- C_Invoice_Paid(p_C_Invoice_ID, p_C_Currency_ID, p_MultiplierAP)
-- Returns paid amount in specified currency

-- C_Invoice_PaidToDate(p_C_Invoice_ID, p_date, p_C_Currency_ID, p_MultiplierAP)
-- Returns paid amount as of specific date

-- C_Payment_Allocated(p_C_Payment_ID)
-- Returns allocated amount for payment

-- C_Payment_Available(p_C_Payment_ID)
-- Returns available (unallocated) amount for payment
```
