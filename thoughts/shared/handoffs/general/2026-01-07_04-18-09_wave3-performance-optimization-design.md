---
date: 2026-01-07T04:18:09+00:00
researcher: Claude
git_commit: d5ddf58e98a17251c5cc2e249eb416a7fcc9852a
branch: wave3
repository: adempiere
topic: "Wave 3 Performance Optimization Design"
tags: [performance, wave3, currency-conversion, n-plus-one, brainstorming]
status: in_progress
last_updated: 2026-01-07
last_updated_by: Claude
type: implementation_strategy
---

# Handoff: Wave 3 Financial Core Performance Optimization Design

## Task(s)

| Task | Status |
|------|--------|
| Analyze Wave 3 performance regression root cause | **Completed** |
| Design optimization approach | **In Progress** - awaiting user decision on approach |
| Implement optimization | Planned |

**Context:** Wave 3 migrates 7 SQL functions (invoiceOpen, invoiceOpenToDate, invoiceDiscount, invoicePaid, invoicePaidToDate, paymentAllocated, paymentAvailable) to Java. Functional correctness is verified (Gate 1 passed), but performance fails - Java is 1.5-2x slower than SQL (target: ≤1.30x), blocking Gate 2.

## Critical References

1. **Quality Gates Document:** `docs/plans/wave3-quality-gates.md` - Defines the 130% performance threshold and current failure status
2. **Implementation Plan:** `docs/plans/2026-01-03-wave3-financial-core-implementation.md` - Full Wave 3 implementation details
3. **SQL Function Reference:** `db/ddlutils/postgresql/functions/C_Invoice_Open.sql` - Original SQL implementation to match

## Recent changes

No code changes made in this session - this was a design/analysis session.

## Learnings

### Root Cause: N+1 Query Pattern in Currency Conversion

The primary performance bottleneck is `MConversionRate.getRate()` at `base/src/org/compiere/model/MConversionRate.java:229-240`. This method executes a fresh DB query on every call with **no caching**.

**Execution flow comparison:**

**SQL Function (efficient):**
- `invoiceOpen()` executes entirely server-side
- `currencyConvert()` called from PL/pgSQL stays in same DB context
- No network round-trips between function calls

**Java Implementation (slow):**
- For each allocation row, calls `CurrencyFunctions.currencyConvert()`
- Which calls `currencyRate()`
- Which calls `MConversionRate.getRate()` - **uncached DB query every time**

**Impact quantification:**
- Invoice with 5 allocation lines: SQL ~3 queries vs Java 8 queries
- Invoice with 10 allocation lines: SQL ~3 queries vs Java 13 queries

### Key Code Paths Analyzed

1. **InvoiceFunctions.java** (`base/src/org/compiere/util/InvoiceFunctions.java`):
   - `calculateInvoiceOpenJava()` lines 213-343 - loops through allocations calling currencyConvert per row
   - Same pattern in `calculateInvoicePaidJava()`, `calculateInvoiceOpenToDateJava()`

2. **CurrencyFunctions.java** (`base/src/org/compiere/util/CurrencyFunctions.java`):
   - `currencyRate()` lines 104-244 - checks same-currency early (line 115-116), but for different currencies hits MConversionRate
   - Same-currency cases return `BigDecimal.ONE` immediately - already optimized

3. **MConversionRate.java** (`base/src/org/compiere/model/MConversionRate.java`):
   - `getRate()` lines 215-245 - **THE BOTTLENECK** - executes SQL query every call, no caching

4. **MCurrency.java** (`base/src/org/compiere/model/MCurrency.java`):
   - Uses `CCache` at line 92-94 - already cached, not a bottleneck

5. **MConversionType.java** (`base/src/org/compiere/model/MConversionType.java`):
   - `getDefault()` line 53-72 - uses cache, not a bottleneck after first call

## Artifacts

- `docs/plans/wave3-quality-gates.md` - Read, defines performance requirements
- `docs/plans/2026-01-03-wave3-financial-core-implementation.md` - Read, implementation context
- `docs/plans/wave3-performance-baseline.md` - Read, shows tests pending execution
- `base/src/org/compiere/util/InvoiceFunctions.java` - Read, current Java implementation
- `base/src/org/compiere/util/CurrencyFunctions.java` - Read, currency conversion logic
- `base/src/org/compiere/model/MConversionRate.java` - Read, identified as bottleneck
- `db/ddlutils/postgresql/functions/C_Invoice_Open.sql` - Read, SQL reference implementation

## Action Items & Next Steps

### Immediate Decision Required

User must choose optimization approach (question was asked at end of session):

**Option A: Use SQL currencyConvert in queries**
- Push currency conversion INTO the SQL query: `SELECT currencyConvert(...) AS converted_amount FROM C_AllocationLine ...`
- Java just sums pre-converted results
- Matches SQL function behavior exactly
- Simple, but maintains dependency on SQL `currencyConvert` function

**Option B: Pre-fetch all needed rates, then convert in Java**
- Before processing allocations, collect unique (fromCurrency, toCurrency, date) tuples
- Fetch all needed rates in single query
- Use local map for lookups during processing
- More complex, but fully Java-based

**Option C: Add rate caching to MConversionRate.getRate()**
- Add `CCache` to `MConversionRate.getRate()` method
- Simplest code change
- Doesn't eliminate round-trips for first occurrence of each rate pair

### After Decision

1. Implement chosen optimization approach
2. Run performance tests to verify ≤1.30x ratio achieved
3. Update `docs/plans/wave3-performance-baseline.md` with results
4. Mark Gate 2 as passed in `docs/plans/wave3-quality-gates.md`
5. Proceed to Gate 3 (Shadow Validation)

## Other Notes

### Test Environment Issue

Performance tests require GUI environment (headless exception). See `docs/plans/wave3-performance-baseline.md` for details on the `HeadlessException` from `Adempiere.startup()`. Tests can be run with:
- X11 forwarding
- Virtual display (xvfb)
- `-Djava.awt.headless=true` with modified test framework

### Relevant Test Files

- `base/test/src/org/compiere/migration/Wave3ShadowIntegrationTest.java` - 75 functional tests passing
- `base/test/src/org/compiere/migration/Wave3*PerformanceTest.java` - Performance tests (4 files)

### SQL Function Location

All PostgreSQL functions are in `db/ddlutils/postgresql/functions/`:
- `C_Invoice_Open.sql`
- `C_Invoice_OpenToDate.sql`
- `C_Currency_Convert.sql`
- `C_Currency_Rate.sql`
