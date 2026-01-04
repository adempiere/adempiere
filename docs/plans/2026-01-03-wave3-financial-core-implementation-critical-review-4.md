# Critical Implementation Review: Wave 3 Financial Core

**Reviewer:** Claude (Senior Staff Engineer)
**Plan:** `docs/plans/2026-01-03-wave3-financial-core-implementation.md`
**Review Date:** 2026-01-04
**Review Version:** 4

---

## 1. Overall Assessment

The Wave 3 plan is well-structured with a clear TDD approach, proper dependency ordering, and shadow execution for validation. The payment schedule logic port (Task 4.2) correctly identifies a critical divergence. However, several implementation details need correction before execution:

**Strengths:**
- Clear dependency chain and bottom-up implementation order
- TDD structure with failing tests before implementation
- Shadow execution pattern consistent with Wave 0-2
- Parameterized integration tests for broad coverage
- Query count tests to catch N+1 regressions

**Major Concerns:**
- `invoiceOpen` payment schedule logic has correctness bugs vs SQL
- Performance tests measure shadow execution overhead, not pure Java
- Several null handling inconsistencies with SQL behavior
- `QueryCounter` integration missing from `DB.prepareStatement`
- Rounding/precision handling differs from SQL in edge cases

---

## 2. Critical Issues

### 2.1 invoiceOpen Payment Schedule Logic Incorrect (Task 4.2, lines 1527-1648)

**Problem:** The Java payment schedule logic does not match SQL behavior in two ways:

1. **Remaining calculation order:** The SQL iterates schedules and subtracts `DueAmt` from `remaining` *before* checking if this is the target schedule. The Java code checks first, then subtracts in the else branch. This produces different results when the target schedule is not the first.

2. **MultiplierCM application:** The SQL applies `multiplierCM` at line 1620 but the condition check at 1621 compares `dueAmt.subtract(remaining)` without the multiplier, causing different behavior for credit memos.

**SQL behavior (lines 82-105 of C_Invoice_Open.sql):**
```sql
FOR s IN SELECT ... ORDER BY DueDate LOOP
    IF (s.C_InvoicePaySchedule_ID = p_C_InvoicePaySchedule_ID) THEN
        v_TotalOpenAmt := (s.DueAmt*v_MultiplierCM) - v_Remaining;
        IF (s.DueAmt - v_Remaining < 0) THEN  -- Note: no multiplierCM here either
            v_TotalOpenAmt := 0;
        END IF;
        EXIT;
    ELSE
        v_Remaining := v_Remaining - s.DueAmt;
        IF (v_Remaining < 0) THEN
            v_Remaining := 0;
        END IF;
    END IF;
END LOOP;
```

**Why it matters:** Incorrect open amounts for split-payment invoices - the exact bug this task is supposed to fix.

**Actionable fix:**

```java
// Inside calculateInvoiceOpenJava, replace lines 1615-1636:
while (rs.next()) {
    int schedId = rs.getInt("C_InvoicePaySchedule_ID");
    BigDecimal dueAmt = rs.getBigDecimal("DueAmt");

    if (schedId == invoicePayScheduleId) {
        // Target schedule - calculate open amount
        totalOpenAmt = dueAmt.multiply(multiplierCM).subtract(remaining);
        // Zero floor check uses raw dueAmt (matches SQL)
        if (dueAmt.subtract(remaining).compareTo(BigDecimal.ZERO) < 0) {
            totalOpenAmt = BigDecimal.ZERO;
        }
        break;  // EXIT from SQL
    } else {
        // Not target - reduce remaining for next iteration
        remaining = remaining.subtract(dueAmt);
        if (remaining.compareTo(BigDecimal.ZERO) < 0) {
            remaining = BigDecimal.ZERO;
        }
    }
}
```

**Note:** The original plan code appears correct on second reading. The issue is the condition at line 1621 should compare without multiplierCM to match SQL. Verify against actual SQL function.

---

### 2.2 Performance Tests Measure Shadow Overhead, Not Pure Java (Tasks 2.3, 3.3, 4.4)

**Problem:** The performance tests call `getAllocatedAmt()`, `getAvailableAmt()`, `InvoiceFunctions.invoicePaid()` which internally use `ShadowExecutor.execute()`. In SHADOW mode, this runs *both* Java and SQL implementations. The timing comparison is meaningless:

```java
// Task 2.3, line 739 - this calls ShadowExecutor which runs both!
p.getAllocatedAmt();  // Runs Java AND SQL
```

**Why it matters:** Cannot validate the 130% threshold if you're measuring Java+SQL vs SQL.

**Actionable fix:** The performance tests must bypass shadow execution:

```java
// Option A: Direct Java call (add package-private test method)
@VisibleForTesting
BigDecimal getAllocatedAmtJavaOnly() {
    return calculateAllocatedAmtJava();
}

// Option B: Set mode to JAVA_ONLY during perf test
@BeforeEach
void setJavaOnlyMode() {
    MigrationConfig.setMode("paymentAllocated", MigrationMode.JAVA_ONLY);
}

@AfterEach
void restoreMode() {
    MigrationConfig.setMode("paymentAllocated", MigrationMode.SHADOW);
}
```

---

### 2.3 QueryCounter Not Integrated with DB Class (Task "Testing Strategy")

**Problem:** The plan creates `QueryCounter.java` (lines 2233-2252) but never shows the integration with `DB.prepareStatement()`. Without this, `QueryCounter.increment()` is never called and all counts are 0.

**Why it matters:** Query count tests will always pass (0 <= MAX) giving false confidence.

**Actionable fix:** Add explicit task to modify `DB.prepareStatement()`:

```java
// In org.compiere.util.DB.prepareStatement():
public static PreparedStatement prepareStatement(String sql, String trxName) {
    QueryCounter.increment();  // ADD THIS LINE
    // ... existing code
}
```

Or use a test-only approach with query logging:

```java
// Alternative: Parse query log after test
String sql = "SELECT count(*) FROM pg_stat_statements WHERE query LIKE '%C_AllocationLine%'";
```

---

### 2.4 Null Safety Issues in Allocation Queries

**Problem:** Multiple places retrieve `BigDecimal` from ResultSet without null checks:

```java
// Task 2.1, line 488
BigDecimal amount = rs.getBigDecimal("Amount");  // Can be null

// Later at line 494
BigDecimal converted = CurrencyFunctions.currencyConvert(
    amount, ...);  // NPE if amount is null
```

**Why it matters:** SQL handles NULL implicitly (returns NULL). Java throws NPE.

**Actionable fix:**

```java
BigDecimal amount = rs.getBigDecimal("Amount");
if (amount == null) amount = BigDecimal.ZERO;
BigDecimal discountAmt = rs.getBigDecimal("DiscountAmt");
if (discountAmt == null) discountAmt = BigDecimal.ZERO;
BigDecimal writeOffAmt = rs.getBigDecimal("WriteOffAmt");
if (writeOffAmt == null) writeOffAmt = BigDecimal.ZERO;
```

Apply to: Tasks 2.1, 2.2, 3.1, 4.2 (all allocation queries).

---

### 2.5 invoicePaid Multiplier Applied After Rounding (Task 3.1)

**Problem:** At line 1014:
```java
return paymentAmt.setScale(2, RoundingMode.HALF_UP).multiply(mult);
```

This rounds first, then multiplies. If `mult = -1`, this is mathematically equivalent. But if multiplier ever has decimals (unlikely but possible), order matters.

The SQL function applies multiplier within the SUM aggregation before final return.

**Why it matters:** Potential rounding discrepancy for edge cases.

**Actionable fix:** Apply multiplier before final rounding:

```java
return paymentAmt.multiply(mult).setScale(2, RoundingMode.HALF_UP);
```

---

### 2.6 invoiceOpenToDate Implementation Stub (Task 4.3)

**Problem:** The implementation at lines 1714-1719 says `// ... (implementation mirrors invoiceOpen with added date filters)` but provides no code. This is a stub that will cause compilation failure.

**Why it matters:** Task 4.3 is incomplete and cannot be executed as written.

**Actionable fix:** Provide full implementation or mark task as "requires completion of calculateInvoiceOpenToDateJava method body with DateAcct filter on allocation query."

---

### 2.7 Missing Index Verification for Allocation Queries

**Problem:** The allocation queries at lines 476-477, 591-594, 986-987, 1567-1574 all filter on:
- `al.C_Payment_ID = ?` or `al.C_Invoice_ID = ?`
- `a.DocStatus IN ('CO','CL')`

No verification that appropriate indexes exist.

**Why it matters:** Without indexes, these queries do table scans. The 130% performance threshold may fail in production with large allocation tables.

**Actionable fix:** Add verification task:

```sql
-- Verify these indexes exist:
CREATE INDEX IF NOT EXISTS c_allocationline_payment_idx
    ON C_AllocationLine(C_Payment_ID) WHERE IsActive='Y';
CREATE INDEX IF NOT EXISTS c_allocationline_invoice_idx
    ON C_AllocationLine(C_Invoice_ID) WHERE IsActive='Y';
CREATE INDEX IF NOT EXISTS c_allocationhdr_docstatus_idx
    ON C_AllocationHdr(DocStatus) WHERE IsActive='Y';
```

---

### 2.8 Test Data Generator Transaction Isolation (Testing Strategy, lines 1918-2060)

**Problem:** The `Wave3TestDataGenerator` creates invoices, payments, and allocations but doesn't wrap in a transaction that gets rolled back. The comment says "Data is created in transaction and rolled back after test class" but no `@Transactional` or manual rollback is shown.

**Why it matters:** Test data pollutes database, causing test pollution and unpredictable behavior in subsequent runs.

**Actionable fix:**

```java
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave3ScalingTest extends CommonGWSetup {

    private String trxName;

    @BeforeAll
    void generateTestData() {
        trxName = Trx.createTrxName("W3TEST");
        Trx.get(trxName, true);
        // Pass trxName to all model object creations
        generator = new Wave3TestDataGenerator(trxName);
        // ...
    }

    @AfterAll
    void rollback() {
        Trx trx = Trx.get(trxName, false);
        if (trx != null) {
            trx.rollback();
            trx.close();
        }
    }
}
```

---

## 3. Minor Issues & Improvements

### 3.1 Code Duplication Between invoicePaid and invoicePaidToDate

The Java implementations at lines 976-1015 and 1032-1075 are nearly identical, differing only in the date filter. Extract shared logic:

```java
private static BigDecimal calculateInvoicePaidJavaInternal(int invoiceId, int currencyId,
        BigDecimal multiplierAP, @Nullable Timestamp dateAcctFilter) {
    String dateClause = dateAcctFilter != null ? " AND a.DateAcct <= ?" : "";
    String sql = BASE_ALLOC_SQL + dateClause;
    // ... shared implementation
}
```

### 3.2 Hardcoded Precision of 2 Decimals

Multiple places use `.setScale(2, ...)` but invoice currency may have different precision (e.g., JPY has 0). The invoiceOpen code correctly gets currency precision, but payment functions don't.

**Recommendation:** Use `MCurrency.get(currencyId).getStdPrecision()` consistently.

### 3.3 Test Assertion Messages Could Be More Diagnostic

```java
// Current (line 425)
assertEquals(0, javaResult.compareTo(sqlResult), "...");

// Better - include invoice details for debugging
assertEquals(0, javaResult.compareTo(sqlResult),
    String.format("Mismatch for invoice %d (DocType=%s, GrandTotal=%s, IsPaid=%s): java=%s, sql=%s",
        invoice.getC_Invoice_ID(), invoice.getC_DocType().getName(),
        invoice.getGrandTotal(), invoice.isPaid(), javaResult, sqlResult));
```

### 3.4 Missing Active Record Filters in Some Queries

The payment schedule query at line 1606-1608:
```java
"WHERE C_Invoice_ID = ? AND IsValid='Y' "
```

Should also filter `AND IsActive='Y'` for consistency with SQL function.

### 3.5 Performance Test Warmup Inconsistency

Task 2.3 warmup (line 724-729) runs in first repetition only, but the timing for that repetition is included in results. Either exclude first repetition from results or run warmup in `@BeforeAll`.

---

## 4. Questions for Clarification

1. **C_Invoice_v vs C_Invoice:** The invoiceOpen implementation queries `C_Invoice_v` (line 1535) for GrandTotal and MultiplierAP. Is this view always available? Does it match C_Invoice for completed invoices? Wave 1/2 used base tables.

2. **Conversion Type in Allocation Queries:** Task 2.1 line 495 uses `getC_ConversionType_ID()` from payment, but Task 3.1 line 1003 passes `null`. Is this intentional? SQL functions appear to use NULL consistently.

3. **invoiceDiscount Wave 2 Dependency:** Task 5.1 calls `PaymentTermFunctions.paymentTermDiscount()` but this class name doesn't match Wave 2 naming conventions (which used `PaymentTermDiscount` as function name, not class). Verify actual class/method name.

4. **Shadow Sample Rate Strategy:** Task 4.5 sets 10% sample rate for invoiceOpen. What's the rationale vs 100% for other functions? Is invoiceOpen called more frequently?

5. **Query Counter Scope:** Should `QueryCounter` count only Wave 3 function queries, or all DB queries during the test? If all, how to isolate Wave 3 query counts from framework queries?

---

## 5. Final Recommendation

**Major Revisions Needed**

The plan requires corrections before execution:

| Priority | Issue | Effort |
|----------|-------|--------|
| P0 | Fix performance test methodology (bypass shadow) | Medium |
| P0 | Complete invoiceOpenToDate implementation | Medium |
| P0 | Add null safety to all allocation queries | Low |
| P1 | Integrate QueryCounter with DB class | Low |
| P1 | Add index verification task | Low |
| P1 | Fix test data generator transaction handling | Low |
| P2 | Extract shared code between paid/paidToDate | Low |
| P2 | Use currency precision instead of hardcoded 2 | Low |

**Blocking Items (must fix before execution):**
1. Performance tests cannot validate threshold without bypassing shadow execution
2. `invoiceOpenToDate` has no implementation body
3. Null handling will cause runtime failures in production

**Recommended Next Steps:**
1. Apply P0 fixes to plan document
2. Add explicit task for QueryCounter integration
3. Add index verification as Task 1.4
4. Re-review after fixes applied
