---
date: 2026-01-04T00:51:04+00:00
researcher: Claude
git_commit: f6631b2b5940ad5d04ac9199b7f4d0a38a4d8be1
branch: wave3
repository: adempiere
topic: "Wave 3 Financial Core - Apply Critical Review Fixes to Implementation Plan"
tags: [implementation, wave3, financial-functions, critical-review]
status: in_progress
last_updated: 2026-01-04
last_updated_by: Claude
type: implementation_strategy
---

# Handoff: Wave 3 - Apply Critical Reviews 1-4 to Implementation Plan

## Task(s)

**Status: In Progress**

The task is to apply fixes from 4 critical reviews to the Wave 3 implementation plan. The reviews identified bugs, gaps, and improvements that need to be fixed in the implementation plan before execution.

**Completed:**
- Applied Review 4 fixes to implementation plan:
  - Task 1.4: QueryCounter integration with DB.prepareStatement()
  - Task 1.5: Index verification for allocation queries
  - Wave3ScalingTest transaction isolation (trxName with rollback)
  - Wave3TestDataGenerator updated to accept trxName parameter
  - Summary section updated to reflect 5 tasks in Group 1

**Not Started:**
- Apply Review 1 fixes (conversion type, IsActive filters, null safety, transaction context, performance test methodology)
- Apply Review 2 fixes (invoiceOpenToDate implementation, scaling order, circuit breaker, error handling)
- Apply Review 3 fixes (tolerance comparator, payment schedule logic, currency precision, Wave 2 dependency verification, sample rate config)

## Critical References

1. `docs/plans/2026-01-03-wave3-financial-core-implementation.md` - The implementation plan being updated
2. `docs/plans/2026-01-03-wave3-financial-core-implementation-critical-review-1.md` through `...-critical-review-4.md` - The 4 critical reviews containing issues to fix

## Recent changes

- `docs/plans/2026-01-03-wave3-financial-core-implementation.md:371-480` - Added Task 1.4 (QueryCounter) and Task 1.5 (Index verification)
- `docs/plans/2026-01-03-wave3-financial-core-implementation.md:40` - Updated Task Group 1 header to "5 tasks"
- `docs/plans/2026-01-03-wave3-financial-core-implementation.md:2261-2347` - Updated Wave3ScalingTest with transaction isolation
- `docs/plans/2026-01-03-wave3-financial-core-implementation.md:2044-2072` - Updated Wave3TestDataGenerator constructor with trxName
- `docs/plans/2026-01-03-wave3-financial-core-implementation.md:2105-2170` - Updated createInvoice and createPaymentAndAllocation with trxName
- `docs/plans/2026-01-03-wave3-financial-core-implementation.md:2790` - Updated Summary to show Tasks 1.1-1.5

## Learnings

1. **Summary vs Implementation Plan**: Critical review fixes should be applied to the implementation plan, NOT the summary document. The summary is just a tracking document.

2. **Review conflicts**: R1 and R3 conflict on IsActive filters:
   - R1 says REMOVE IsActive filters from paymentAllocated (to match SQL which has no IsActive)
   - R3 says ADD IsActive filters to invoiceOpen (noting inconsistency)
   - Correct approach: Follow R1 - SQL functions do NOT have IsActive filters, so Java shouldn't either. Remove from paymentAllocated, leave invoiceOpen without.

3. **Duplicate issues across reviews**: Many issues repeat across R1-R4 because the implementation plan was not updated between reviews. Only genuinely new issues from each review need to be applied.

## Artifacts

- `docs/plans/2026-01-03-wave3-financial-core-implementation.md` - Updated implementation plan (R4 fixes applied)
- `docs/plans/2026-01-03-wave3-financial-core-implementation-critical-review-1.md` - Review 1 (not yet applied)
- `docs/plans/2026-01-03-wave3-financial-core-implementation-critical-review-2.md` - Review 2 (not yet applied)
- `docs/plans/2026-01-03-wave3-financial-core-implementation-critical-review-3.md` - Review 3 (not yet applied)
- `docs/plans/2026-01-03-wave3-financial-core-implementation-critical-review-4.md` - Review 4 (applied)
- `docs/plans/2026-01-03-wave3-financial-core-summary.md` - Summary doc (should NOT be updated with review fixes)

## Action Items & Next Steps

### From Review 1 (apply to implementation plan):
1. **Task 2.1 line ~494**: Change `getC_ConversionType_ID()` to `null` in currencyConvert call
2. **Task 2.1 line ~476**: Remove `AND a.IsActive='Y' AND al.IsActive='Y'` from SQL query
3. **Tasks 3.1, 4.2**: Add null-safe BigDecimal handling for Amount, DiscountAmt, WriteOffAmt
4. **All calculateXxxJava methods**: Add trxName parameter and use in DB.prepareStatement()
5. **Performance tests**: Add code to bypass ShadowExecutor for accurate timing

### From Review 2:
1. **Task 4.3 line ~1714-1718**: Replace placeholder "..." with full invoiceOpenToDate implementation
2. **Task 3.1 line ~1014**: Change `.setScale(2).multiply(mult)` to `.multiply(mult).setScale(precision)`
3. Add circuit breaker implementation or remove from config
4. **Tasks 1.1**: Replace hardcoded IDs (109, 100, 11) with dynamic test data queries

### From Review 3:
1. **All ShadowExecutor.execute() calls**: Replace exact comparator with tolerance-based (0.01 threshold)
2. **Task 4.2 lines ~1618-1625**: Fix payment schedule loop logic
3. **Tasks 3.1, others**: Replace hardcoded precision `2` with `MCurrency.getStdPrecision()`
4. **Task 5.1**: Add verification that PaymentTermFunctions exists from Wave 2
5. **Task 7.2**: Fix sample rate configuration to preserve per-function rates

## Other Notes

- The implementation plan is ~2800 lines, so edits should target specific line ranges
- The plan uses TDD pattern: write failing test, implement, verify, commit
- Key code locations in plan:
  - Task 2.1 (paymentAllocated): lines ~485-507
  - Task 3.1 (invoicePaid): lines ~976-1076
  - Task 4.2 (invoiceOpen): lines ~1527-1648
  - Task 4.3 (invoiceOpenToDate): lines ~1700-1720
  - Performance tests: Tasks 2.3, 3.3, 4.4
  - Testing Strategy section: lines ~1873-2252
