# Critical Implementation Review: Wave 3 Financial Core

**Plan:** `docs/plans/2026-01-03-wave3-financial-core-implementation.md`
**Reviewer:** Claude (Opus 4.5)
**Date:** 2026-01-03
**Review Type:** Pre-implementation Code Review

---

## 1. Overall Assessment

**Summary:** The plan is well-structured, follows TDD methodology, and leverages existing infrastructure effectively. The dependency chain is correctly identified, and the task grouping is logical. However, there are **critical correctness bugs in the proposed Java implementations**, performance concerns with repeated database queries, and missing edge case handling.

**Strengths:**
- Correct dependency ordering (bottom-up implementation)
- Proper use of existing ShadowExecutor infrastructure
- TDD approach with explicit test-first steps
- Good documentation of the invoiceOpen divergence
- Comprehensive integration tests with parameterized coverage

**Major Concerns:**
- Critical logic bug in `calculateAllocatedAmtJava()` - uses wrong conversion type parameter
- N+1 query pattern in `invoiceOpen` payment schedule handling
- Missing null-safety in critical money calculations
- Inconsistent IsActive filtering between Java and SQL
- Performance tests measure Java with shadow overhead vs pure SQL

---

## 2. Critical Issues

### 2.1 Incorrect Conversion Type in paymentAllocated (Correctness Bug)

**Location:** Task 2.1, Step 4 - `calculateAllocatedAmtJava()` (lines 485-507 in plan)

**Problem:** The proposed Java implementation passes `getC_ConversionType_ID()` to `CurrencyFunctions.currencyConvert()`:
```java
BigDecimal converted = CurrencyFunctions.currencyConvert(
    amount, allocCurrencyId, getC_Currency_ID(),
    dateTrx, getC_ConversionType_ID(), adClientId, adOrgId);  // WRONG
```

**Why it matters:** The SQL function passes `null` for conversion type (line 54 of C_Payment_Allocated.sql):
```sql
currencyConvert(r.Amount, r.C_Currency_ID, p_C_Currency_ID, r.DateTrx, null, ...)
```
Using the payment's conversion type instead of `null` will produce different exchange rates and cause shadow validation failures for multi-currency scenarios.

**Fix:** Pass `null` for conversion type to match SQL behavior:
```java
BigDecimal converted = CurrencyFunctions.currencyConvert(
    amount, allocCurrencyId, getC_Currency_ID(),
    dateTrx, null, adClientId, adOrgId);  // Matches SQL
```

---

### 2.2 Missing IsActive Filter in paymentAllocated (Correctness Bug)

**Location:** Task 2.1, Step 4 - `calculateAllocatedAmtJava()` query

**Problem:** The proposed Java SQL query is:
```java
String sql = "... WHERE al.C_Payment_ID=? "
    + "AND a.IsActive='Y' AND a.DocStatus IN ('CO','CL') AND al.IsActive='Y'";
```

But the actual SQL function (C_Payment_Allocated.sql lines 46-51) does NOT filter by IsActive:
```sql
WHERE al.C_Payment_ID = p_C_Payment_ID
AND a.DocStatus IN('CO', 'CL')
-- NO IsActive filter
```

**Why it matters:** Soft-deleted allocation lines would be excluded by Java but included by SQL, causing mismatches in production data with historical soft-deletes.

**Fix:** Remove `IsActive` filters from Java queries to match SQL exactly:
```java
String sql = "... WHERE al.C_Payment_ID=? AND a.DocStatus IN ('CO','CL')";
```

---

### 2.3 N+1 Query Pattern in invoiceOpen (Performance Risk)

**Location:** Task 4.2, Step 3 - `calculateInvoiceOpenJava()` (lines 1527-1648 in plan)

**Problem:** The implementation executes three separate queries:
1. Header query to `C_Invoice_v` (1 query)
2. Allocation loop with `currencyConvert` (1 query + N conversions if CurrencyFunctions calls DB)
3. Payment schedule loop (1 query)

For invoices with payment schedules, this is 3+ round trips per call. In list views showing 50 invoices, this becomes 150+ queries.

**Why it matters:** The SQL function does all this in a single database call. The performance test may pass the 130% threshold for individual calls, but aggregate performance in realistic workloads will degrade significantly.

**Fix:** Consider fetching payment schedules eagerly alongside header data, or add a batch API for list views:
```java
// Option A: Combined header + schedules query
String sql = "SELECT i.*, ps.C_InvoicePaySchedule_ID, ps.DueAmt, ps.DueDate "
    + "FROM C_Invoice_v i "
    + "LEFT JOIN C_InvoicePaySchedule ps ON (i.C_Invoice_ID = ps.C_Invoice_ID AND ps.IsValid='Y') "
    + "WHERE i.C_Invoice_ID = ? ORDER BY ps.DueDate";

// Option B: Document in performance baseline and defer optimization
```

---

### 2.4 Null Safety in Allocation Amount Calculations (Correctness Bug)

**Location:** Task 3.1 - `calculateInvoicePaidJava()` and Task 4.2 - `calculateInvoiceOpenJava()`

**Problem:** The code does not handle null values from `rs.getBigDecimal()`:
```java
BigDecimal amount = rs.getBigDecimal("Amount");
BigDecimal discountAmt = rs.getBigDecimal("DiscountAmt");
BigDecimal writeOffAmt = rs.getBigDecimal("WriteOffAmt");

BigDecimal total = amount.add(discountAmt).add(writeOffAmt);  // NPE if any null
```

**Why it matters:** In practice, `DiscountAmt` and `WriteOffAmt` may be NULL in the database (not all allocations have discounts). This will cause `NullPointerException` in production.

**Fix:** Add COALESCE or null-safe handling:
```java
BigDecimal amount = Optional.ofNullable(rs.getBigDecimal("Amount")).orElse(BigDecimal.ZERO);
BigDecimal discountAmt = Optional.ofNullable(rs.getBigDecimal("DiscountAmt")).orElse(BigDecimal.ZERO);
BigDecimal writeOffAmt = Optional.ofNullable(rs.getBigDecimal("WriteOffAmt")).orElse(BigDecimal.ZERO);
```
Or use SQL COALESCE:
```java
String sql = "SELECT COALESCE(al.Amount, 0) AS Amount, "
    + "COALESCE(al.DiscountAmt, 0) AS DiscountAmt, ...";
```

---

### 2.5 Flawed Performance Test Methodology (Measurement Error)

**Location:** Task 2.3, Task 3.3, Task 4.4 - All performance tests

**Problem:** The performance tests compare Java implementations that internally call `ShadowExecutor.execute()` against pure SQL calls:
```java
// Java test - goes through ShadowExecutor
long javaStart = System.nanoTime();
p.getAllocatedAmt();  // This includes shadow overhead!
long javaTimeNs = System.nanoTime() - javaStart;

// SQL test - direct call
long sqlStart = System.nanoTime();
SqlFunctionCaller.callPaymentAllocated(...);  // No shadow overhead
long sqlTimeNs = System.nanoTime() - sqlStart;
```

**Why it matters:** In SHADOW mode, Java calls execute BOTH Java AND SQL paths (with comparison and logging). The ratio will be artificially inflated (~200%+), causing false failures. In JAVA_ONLY mode, it's accurate but doesn't reflect production validation state.

**Fix:** Test the Java calculation directly, bypassing ShadowExecutor:
```java
// Test Java implementation directly
long javaStart = System.nanoTime();
payment.calculateAllocatedAmtJava();  // Make package-visible for testing
long javaTimeNs = System.nanoTime() - javaStart;
```
Or ensure tests run with `MigrationMode.JAVA_ONLY`:
```java
@BeforeEach
void setJavaOnlyMode() {
    MigrationConfig.setTestMode("paymentAllocated", MigrationMode.JAVA_ONLY);
}
```

---

### 2.6 Missing Transaction Context in Allocation Queries (Correctness Bug)

**Location:** Multiple tasks - all `calculateXxxJava()` methods

**Problem:** Queries use `DB.prepareStatement(sql, null)` which runs without transaction context:
```java
try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {  // null = no transaction
```

But `MPayment.getAllocatedAmt()` in the existing code uses `get_TrxName()`:
```java
pstmt = DB.prepareStatement(sql, get_TrxName());  // Participates in transaction
```

**Why it matters:** In mid-transaction scenarios (e.g., during allocation creation), the Java implementation may not see uncommitted allocation lines, while the SQL function does (running in the same transaction context). This causes transient validation failures.

**Fix:** For model methods (MPayment, MInvoice), use the model's transaction:
```java
try (PreparedStatement pstmt = DB.prepareStatement(sql, get_TrxName())) {
```

For static utility methods (InvoiceFunctions), accept and use a trxName parameter:
```java
public static BigDecimal invoicePaid(int invoiceId, int currencyId,
                                      BigDecimal multiplierAP, String trxName) {
    // Pass trxName through ShadowExecutor or use directly
}
```

---

### 2.7 invoiceOpen Schedule Logic Divergence (Correctness Bug)

**Location:** Task 4.2, Step 3 - `calculateInvoiceOpenJava()` payment schedule loop

**Problem:** The proposed Java logic:
```java
if (schedId == invoicePayScheduleId) {
    totalOpenAmt = dueAmt.multiply(multiplierCM).subtract(remaining);
    if (dueAmt.subtract(remaining).compareTo(BigDecimal.ZERO) < 0) {
        totalOpenAmt = BigDecimal.ZERO;
    }
    break;
}
```

The SQL logic (C_Invoice_Open.sql lines 91-95):
```sql
IF (s.C_InvoicePaySchedule_ID = p_C_InvoicePaySchedule_ID) THEN
    v_TotalOpenAmt := (s.DueAmt*v_MultiplierCM) - v_Remaining;
    IF (s.DueAmt - v_Remaining < 0) THEN  -- Note: this is before multiplier
        v_TotalOpenAmt := 0;
    END IF;
```

**Why it matters:** The zero-clamping condition is `dueAmt - remaining < 0`, which is correct. But the Java assigns `dueAmt.multiply(multiplierCM).subtract(remaining)` first, then checks `dueAmt.subtract(remaining)`. For credit memos where `multiplierCM = -1`, this is correct. But the condition should be checked BEFORE the multiplier is applied, matching SQL.

Actually, looking more carefully - the SQL checks `s.DueAmt - v_Remaining < 0` (without multiplier) and Java checks `dueAmt.subtract(remaining) < 0` (also without multiplier). This is correct. But the assignment uses multiplier in both. **This specific issue is actually correct on closer inspection.**

However, there's a subtle bug: The SQL loop continues iterating even after finding the target schedule (no `break`), but the `v_TotalOpenAmt` is set only for the target. Java breaks immediately, which is equivalent. **This is actually fine.**

**Revised assessment:** The logic appears correct on careful reading. However, the Java code should be tested with credit memo scenarios to validate.

---

### 2.8 Missing Circuit Breaker in SqlFunctionCaller Pattern (Resilience Gap)

**Location:** Task 1.1, 1.2 - New SqlFunctionCaller methods

**Problem:** The proposed pattern catches exceptions and throws `SqlFunctionException`:
```java
} catch (Exception e) {
    log.log(Level.WARNING, "Failed to call invoiceOpen()", e);
    throw new SqlFunctionException("invoiceOpen", e);
}
```

This is correct and matches existing patterns. However, the plan doesn't address:
- Connection pool exhaustion under load
- Database timeout configuration
- Statement timeout for slow functions

**Why it matters:** If SQL functions are slow or the database is under stress, the shadow execution will add latency to the critical path (Java result is returned, but SQL still runs async-ish via the logging).

**Fix:** Document in quality gates that:
1. Statement timeout should be configured (e.g., 5s max for shadow SQL)
2. Connection pool monitoring should be in place before enabling SHADOW mode

---

## 3. Minor Issues & Improvements

### 3.1 Code Duplication Between invoicePaid and invoicePaidToDate

**Location:** Task 3.1 - `calculateInvoicePaidJava()` and `calculateInvoicePaidToDateJava()`

The implementations are nearly identical except for the `DateAcct` filter. Consider extracting common logic:
```java
private static BigDecimal calculateInvoicePaidJava(int invoiceId, int currencyId,
                                                     BigDecimal multiplierAP,
                                                     Timestamp dateFilter) {
    String dateClause = dateFilter != null ? " AND a.DateAcct <= ?" : "";
    // ... shared logic
}
```

### 3.2 Missing Import for MCurrency in InvoiceFunctions

**Location:** Task 4.2, Step 3 - `calculateInvoiceOpenJava()`

The code references `MCurrency.get()` but the class header doesn't show this import:
```java
MCurrency currency = MCurrency.get(Env.getCtx(), currencyId);
```

Ensure the import is added: `import org.compiere.model.MCurrency;`

### 3.3 Test Hardcoded Invoice ID 109

**Location:** Task 1.1, Step 1 - `callInvoiceOpen_returnsNumeric()`

The test uses hardcoded ID 109:
```java
BigDecimal result = SqlFunctionCaller.callInvoiceOpen(109, null);
```

This assumes GardenWorld seed data. Consider using:
```java
@BeforeAll
void loadTestInvoice() {
    testInvoice = new Query(Env.getCtx(), MInvoice.Table_Name,
        "DocStatus IN ('CO','CL')", null).first();
}
```

### 3.4 Performance Test Warmup Count May Be Insufficient

**Location:** Task 2.3 - `WARMUP_ITERATIONS = 100`

For JIT compilation to stabilize, 100 iterations may not trigger tiered compilation. Consider:
```java
private static final int WARMUP_ITERATIONS = 1000;  // Or 500 minimum
```

### 3.5 Missing Test for Zero/Negative Amounts

Edge cases not covered in tests:
- Zero amount invoices
- Negative amounts (credit scenarios)
- Same-currency conversions (should be identity)

### 3.6 Sampling Rate for High-Volume Functions

**Location:** Task 4.5 - `sample_rate = 0.1` (10%)

For `invoiceOpen`, 10% sampling is reasonable. However, consider starting at 100% for first 24 hours to catch edge cases, then reducing:
```sql
-- Initial deployment
UPDATE migration.function_config SET sample_rate = 1.0 WHERE function_name = 'invoiceOpen';

-- After 24h validation
UPDATE migration.function_config SET sample_rate = 0.1 WHERE function_name = 'invoiceOpen';
```

---

## 4. Questions for Clarification

### 4.1 Transaction Context for InvoiceFunctions

Q: Should `InvoiceFunctions` be a static utility class, or should it operate on an MInvoice instance to inherit transaction context? The plan shows static methods with `DB.prepareStatement(sql, null)`, which won't see uncommitted data.

### 4.2 MInvoice.getOpenAmt() Caching

Q: The existing `MInvoice.getOpenAmt()` caches results in `openAmount` field (line 1224-1238). The proposed refactor in Task 6.3 delegates to `InvoiceFunctions.invoiceOpen()`. Should the caching be preserved? This affects performance for repeated calls within the same request.

### 4.3 Handling of Reversed Allocations

Q: Are reversed allocations (DocStatus = 'RE') explicitly excluded by the DocStatus filter? The SQL uses `IN ('CO','CL')` which excludes them. Just confirming this is intentional.

### 4.4 Credit Memo Multiplier Logic

Q: The plan mentions `multiplierCM` (from C_Invoice_v) but the existing MInvoice.getOpenAmt() uses `isCreditMemo()` check. Are these equivalent? Should there be a specific test for credit memo scenarios?

---

## 5. Final Recommendation

**Verdict: Major Revisions Needed**

The plan is well-structured but contains critical correctness bugs that will cause shadow validation failures and potential production issues. Before implementation:

### Required Changes

1. **Fix conversion type parameter** in `calculateAllocatedAmtJava()` - pass `null` to match SQL
2. **Remove IsActive filters** from all queries to match SQL behavior exactly
3. **Add null-safety** for `DiscountAmt`, `WriteOffAmt` in allocation calculations
4. **Fix performance test methodology** to measure Java implementation directly, not through ShadowExecutor
5. **Add transaction context** to queries (use `trxName` parameter or model's `get_TrxName()`)

### Recommended Changes

6. Address N+1 query pattern in `invoiceOpen` (document if deferring)
7. Add warmup iterations to performance tests (increase to 500+)
8. Add edge case tests for zero amounts, credit memos, same-currency
9. Start with 100% sampling for first 24h, then reduce

### Pre-Implementation Checklist

- [ ] Apply all 5 required changes above
- [ ] Re-read SQL functions to verify each Java query matches exactly
- [ ] Add test cases for credit memo with payment schedule
- [ ] Verify CurrencyFunctions.currencyConvert() handles null conversion type
- [ ] Update performance test to bypass ShadowExecutor

Once these issues are addressed, the plan should be ready for implementation.
