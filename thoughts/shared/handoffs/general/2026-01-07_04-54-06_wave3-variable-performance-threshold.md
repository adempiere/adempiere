---
date: 2026-01-07T04:54:06+00:00
researcher: Claude
git_commit: d5ddf58e98a17251c5cc2e249eb416a7fcc9852a
branch: wave3
repository: adempiere
topic: "Wave 3 Variable Performance Threshold Implementation"
tags: [performance, wave3, testing, threshold]
status: in_progress
last_updated: 2026-01-07
last_updated_by: Claude
type: implementation_strategy
---

# Handoff: Wave 3 Variable Performance Threshold Implementation

## Task(s)

| Task | Status |
|------|--------|
| Add absolute time output to performance tests | **Completed** |
| Analyze performance test results | **Completed** |
| Analyze usage patterns of Wave 3 functions | **Completed** |
| Design variable threshold approach | **Completed** - User approved Option 1 |
| Implement variable threshold in Wave3InvoiceOpenPerformanceTest | **Completed** |
| Implement variable threshold in Wave3InvoiceDiscountPerformanceTest | **Completed** |
| Implement variable threshold in Wave3InvoicePaidPerformanceTest | **Completed** |
| Implement variable threshold in Wave3PaymentPerformanceTest | **In Progress** - constants updated, helper methods NOT added yet |
| Run tests to verify all pass | Planned |

## Critical References

1. **Previous handoff:** `thoughts/shared/handoffs/general/2026-01-07_04-18-09_wave3-performance-optimization-design.md` - Contains root cause analysis of N+1 query pattern
2. **Quality gates:** `docs/plans/wave3-quality-gates.md` - Defines Gate 2 performance requirements

## Recent changes

All changes are uncommitted. Modified files:

- `base/test/src/org/compiere/migration/Wave3InvoiceOpenPerformanceTest.java` - Fully updated with variable threshold
- `base/test/src/org/compiere/migration/Wave3InvoiceDiscountPerformanceTest.java` - Fully updated with variable threshold
- `base/test/src/org/compiere/migration/Wave3InvoicePaidPerformanceTest.java` - Fully updated with variable threshold
- `base/test/src/org/compiere/migration/Wave3PaymentPerformanceTest.java` - Partially updated (constants only)

## Learnings

### Variable Threshold Decision (User Approved)

User chose **Option 1** - threshold based on absolute overhead:

```
PASS if:
  (Java - SQL per-call < 1.0 ms) AND (ratio <= 3.0x)
  OR
  (ratio <= 1.5x)
```

**Constants to use:**
```java
private static final double RELAXED_RATIO = 3.0;
private static final double STRICT_RATIO = 1.5;
private static final double MAX_OVERHEAD_MS = 1.0;
```

**Helper methods to add:**
```java
private boolean meetsThreshold(double overheadMs, double ratio) {
    if (overheadMs < MAX_OVERHEAD_MS && ratio <= RELAXED_RATIO) {
        return true;
    }
    return ratio <= STRICT_RATIO;
}

private String getThresholdRule(double overheadMs, double ratio) {
    if (overheadMs < MAX_OVERHEAD_MS) {
        return String.format("overhead %.3fms < %.1fms, ratio %.2f <= %.1f",
            overheadMs, MAX_OVERHEAD_MS, ratio, RELAXED_RATIO);
    }
    return String.format("ratio %.2f <= %.1f", ratio, STRICT_RATIO);
}
```

### Performance Test Results (from initial run)

All 9 tests ran successfully. Per-call overhead is 0.28-0.32ms - all will pass with new threshold:

| Function | SQL per-call | Java per-call | Overhead | Ratio |
|----------|--------------|---------------|----------|-------|
| invoiceOpen | 0.60 ms | 0.92 ms | 0.32 ms | 1.54x |
| invoiceOpen (schedule) | 0.58 ms | 0.88 ms | 0.30 ms | 1.51x |
| invoiceOpenToDate | 0.62 ms | 0.95 ms | 0.32 ms | 1.51x |
| invoiceDiscount | 0.45 ms | 0.74 ms | 0.29 ms | 1.64x |
| invoiceDiscount (schedule) | 0.33 ms | 0.62 ms | 0.29 ms | 1.88x |
| invoicePaid | 0.28 ms | 0.57 ms | 0.29 ms | 2.02x |
| invoicePaidToDate | 0.35 ms | 0.63 ms | 0.27 ms | 1.77x |
| paymentAllocated | 0.29 ms | 0.57 ms | 0.28 ms | 1.99x |
| paymentAvailable | 0.29 ms | 0.59 ms | 0.30 ms | 2.01x |

### Key Insight: Two Usage Patterns

1. **Java method calls** (affected by Wave 3) - `MInvoice.getOpenAmt()` → `InvoiceFunctions.invoiceOpen()`
2. **SQL-embedded calls** (NOT affected) - Direct SQL like `invoiceOpen(C_Invoice_ID, ...)` in reports

High-volume processes (Aging, Dunning, PaySelection) use SQL directly - Java optimization won't help them.

## Artifacts

- `base/test/src/org/compiere/migration/Wave3InvoiceOpenPerformanceTest.java` - Updated (complete)
- `base/test/src/org/compiere/migration/Wave3InvoiceDiscountPerformanceTest.java` - Updated (complete)
- `base/test/src/org/compiere/migration/Wave3InvoicePaidPerformanceTest.java` - Updated (complete)
- `base/test/src/org/compiere/migration/Wave3PaymentPerformanceTest.java` - Partially updated

## Action Items & Next Steps

1. **Complete Wave3PaymentPerformanceTest update:**
   - Add helper methods `meetsThreshold()` and `getThresholdRule()` after `savedAvailableMode` field
   - Update both test method assertions (`testPaymentAllocatedPerformance`, `testPaymentAvailablePerformance`)
   - Follow exact same pattern as the other 3 files

2. **Run performance tests:**
   ```bash
   gradle :base:test:test --tests "Wave3*PerformanceTest" --info
   ```

3. **Verify all 9 tests pass** with the new variable threshold

4. **Commit changes** with message describing the variable threshold approach

5. **Update quality gates document** to reflect the new threshold logic

## Other Notes

### Test Output Format

Each test now outputs a formatted table like:
```
┌─────────────────────────────────────────────────────────────────────────────┐
│ PERFORMANCE: invoiceOpen                                                    │
├─────────────────────────────────────────────────────────────────────────────┤
│ Metric               │          SQL │         Java │         Diff │   Status │
├─────────────────────────────────────────────────────────────────────────────┤
│ Total (500 calls)    │   300.22 ms │   461.30 ms │   +161.08 ms │     PASS │
│ Per call             │     0.600 ms │     0.923 ms │    +0.322 ms │          │
│ Ratio                │              │              │          54% │          │
│ Rule: overhead 0.322ms < 1.0ms, ratio 1.54 <= 3.0                           │
└─────────────────────────────────────────────────────────────────────────────┘
```

### Decision Context

User decided NOT to optimize the N+1 query pattern because:
- Per-call overhead is sub-millisecond (imperceptible)
- High-volume scenarios use SQL directly anyway
- Engineering cost exceeds benefit
