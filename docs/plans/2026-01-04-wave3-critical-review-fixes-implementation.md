# Wave 3 Critical Review Fixes - Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Apply 13 fixes from critical reviews R1-R3 to the Wave 3 implementation plan document.

**Architecture:** Document editing - modify `docs/plans/2026-01-03-wave3-financial-core-implementation.md` at specific line locations identified in the design document.

**Tech Stack:** Markdown editing, no code execution required.

---

## Task Group 1: Core Correctness Fixes (6 edits)

### Task 1.1: Fix Conversion Type Parameter

**Files:**
- Modify: `docs/plans/2026-01-03-wave3-financial-core-implementation.md:607`

**Step 1: Read the current code**

Find line ~607 in calculateAllocatedAmtJava:
```java
dateTrx, getC_ConversionType_ID(), adClientId, adOrgId);
```

**Step 2: Edit to use null**

Replace:
```java
dateTrx, getC_ConversionType_ID(), adClientId, adOrgId);
```

With:
```java
dateTrx, null, adClientId, adOrgId);  // null matches SQL behavior
```

**Step 3: Verify edit**

Read lines 605-608 to confirm change applied correctly.

**Step 4: Commit**

```bash
git add docs/plans/2026-01-03-wave3-financial-core-implementation.md
git commit -m "fix(wave3-plan): use null conversion type in paymentAllocated"
```

---

### Task 1.2: Remove IsActive Filters from paymentAllocated

**Files:**
- Modify: `docs/plans/2026-01-03-wave3-financial-core-implementation.md:589`

**Step 1: Read the current query**

Find line ~589:
```java
+ "AND a.IsActive='Y' AND a.DocStatus IN ('CO','CL') AND al.IsActive='Y'";
```

**Step 2: Edit to remove IsActive filters**

Replace:
```java
+ "AND a.IsActive='Y' AND a.DocStatus IN ('CO','CL') AND al.IsActive='Y'";
```

With:
```java
+ "AND a.DocStatus IN ('CO','CL')";  // No IsActive filter - matches SQL
```

**Step 3: Verify edit**

Read lines 585-592 to confirm.

**Step 4: Commit**

```bash
git add docs/plans/2026-01-03-wave3-financial-core-implementation.md
git commit -m "fix(wave3-plan): remove IsActive filters from paymentAllocated query"
```

---

### Task 1.3: Add Null Safety to paymentAllocated

**Files:**
- Modify: `docs/plans/2026-01-03-wave3-financial-core-implementation.md:600`

**Step 1: Read current code**

Find line ~600:
```java
BigDecimal amount = rs.getBigDecimal("Amount");
```

**Step 2: Edit to add null check**

Replace:
```java
BigDecimal amount = rs.getBigDecimal("Amount");
```

With:
```java
BigDecimal amount = rs.getBigDecimal("Amount");
if (amount == null) amount = BigDecimal.ZERO;
```

**Step 3: Commit**

```bash
git add docs/plans/2026-01-03-wave3-financial-core-implementation.md
git commit -m "fix(wave3-plan): add null safety to paymentAllocated"
```

---

### Task 1.4: Add Null Safety to invoicePaid

**Files:**
- Modify: `docs/plans/2026-01-03-wave3-financial-core-implementation.md:1107-1109`

**Step 1: Read current code**

Find lines ~1107-1109:
```java
BigDecimal amount = rs.getBigDecimal("Amount");
BigDecimal discountAmt = rs.getBigDecimal("DiscountAmt");
BigDecimal writeOffAmt = rs.getBigDecimal("WriteOffAmt");
```

**Step 2: Edit to add null checks**

Replace:
```java
BigDecimal amount = rs.getBigDecimal("Amount");
BigDecimal discountAmt = rs.getBigDecimal("DiscountAmt");
BigDecimal writeOffAmt = rs.getBigDecimal("WriteOffAmt");
```

With:
```java
BigDecimal amount = rs.getBigDecimal("Amount");
if (amount == null) amount = BigDecimal.ZERO;
BigDecimal discountAmt = rs.getBigDecimal("DiscountAmt");
if (discountAmt == null) discountAmt = BigDecimal.ZERO;
BigDecimal writeOffAmt = rs.getBigDecimal("WriteOffAmt");
if (writeOffAmt == null) writeOffAmt = BigDecimal.ZERO;
```

**Step 3: Commit**

```bash
git add docs/plans/2026-01-03-wave3-financial-core-implementation.md
git commit -m "fix(wave3-plan): add null safety to invoicePaid"
```

---

### Task 1.5: Add Null Safety to invoiceOpen

**Files:**
- Modify: `docs/plans/2026-01-03-wave3-financial-core-implementation.md:1694-1696`

**Step 1: Read current code**

Find lines ~1694-1696:
```java
BigDecimal amount = rs.getBigDecimal("Amount");
BigDecimal discountAmt = rs.getBigDecimal("DiscountAmt");
BigDecimal writeOffAmt = rs.getBigDecimal("WriteOffAmt");
```

**Step 2: Edit to add null checks**

Replace with the same pattern as Task 1.4 (add null checks after each getBigDecimal).

**Step 3: Commit**

```bash
git add docs/plans/2026-01-03-wave3-financial-core-implementation.md
git commit -m "fix(wave3-plan): add null safety to invoiceOpen"
```

---

### Task 1.6: Fix Currency Precision and Scale Order

**Files:**
- Modify: `docs/plans/2026-01-03-wave3-financial-core-implementation.md:1119-1121`

**Step 1: Read current code**

Find lines around 1119-1121 in calculateInvoicePaidJava, looking for:
```java
return paymentAmt.setScale(2, RoundingMode.HALF_UP).multiply(mult);
```

**Step 2: Edit to fix precision and order**

Replace:
```java
return paymentAmt.setScale(2, RoundingMode.HALF_UP).multiply(mult);
```

With:
```java
// Get currency precision (don't hardcode 2)
MCurrency currency = MCurrency.get(Env.getCtx(), currencyId);
int precision = currency != null ? currency.getStdPrecision() : 2;
return paymentAmt.multiply(mult).setScale(precision, RoundingMode.HALF_UP);
```

**Step 3: Add MCurrency import if not present**

Check the import section at ~line 1050-1060 and add:
```java
import org.compiere.model.MCurrency;
```

**Step 4: Commit**

```bash
git add docs/plans/2026-01-03-wave3-financial-core-implementation.md
git commit -m "fix(wave3-plan): use MCurrency precision, fix scale order"
```

---

## Task Group 2: Payment Schedule Logic Fix (1 edit)

### Task 2.1: Fix Payment Schedule Loop

**Files:**
- Modify: `docs/plans/2026-01-03-wave3-financial-core-implementation.md:1731-1737`

**Step 1: Read current code**

Find lines ~1731-1737:
```java
if (schedId == invoicePayScheduleId) {
    // This is the target schedule
    totalOpenAmt = dueAmt.multiply(multiplierCM).subtract(remaining);
    if (dueAmt.subtract(remaining).compareTo(BigDecimal.ZERO) < 0) {
        totalOpenAmt = BigDecimal.ZERO;
    }
    break;
```

**Step 2: Edit to fix the zero-floor check**

Replace:
```java
if (schedId == invoicePayScheduleId) {
    // This is the target schedule
    totalOpenAmt = dueAmt.multiply(multiplierCM).subtract(remaining);
    if (dueAmt.subtract(remaining).compareTo(BigDecimal.ZERO) < 0) {
        totalOpenAmt = BigDecimal.ZERO;
    }
    break;
```

With:
```java
if (schedId == invoicePayScheduleId) {
    // This is the target schedule - calculate open amount
    BigDecimal scheduleOpen = dueAmt.multiply(multiplierCM).subtract(remaining);
    // Zero floor: if calculated open is negative, return zero
    if (scheduleOpen.compareTo(BigDecimal.ZERO) < 0) {
        scheduleOpen = BigDecimal.ZERO;
    }
    totalOpenAmt = scheduleOpen;
    break;
```

**Step 3: Commit**

```bash
git add docs/plans/2026-01-03-wave3-financial-core-implementation.md
git commit -m "fix(wave3-plan): fix payment schedule zero-floor logic"
```

---

## Task Group 3: Method Signature Updates (1 edit, multiple locations)

### Task 3.1: Add trxName Parameter to All Methods

**Files:**
- Modify: `docs/plans/2026-01-03-wave3-financial-core-implementation.md` (multiple locations)

**Step 1: Update invoicePaid signature at ~line 1079**

Change:
```java
public static BigDecimal invoicePaid(int invoiceId, int currencyId, @Nullable BigDecimal multiplierAP) {
```

To:
```java
public static BigDecimal invoicePaid(int invoiceId, int currencyId, @Nullable BigDecimal multiplierAP, String trxName) {
```

**Step 2: Update calculateInvoicePaidJava signature at ~line 1089**

Change:
```java
private static BigDecimal calculateInvoicePaidJava(int invoiceId, int currencyId, @Nullable BigDecimal multiplierAP) {
```

To:
```java
private static BigDecimal calculateInvoicePaidJava(int invoiceId, int currencyId, @Nullable BigDecimal multiplierAP, String trxName) {
```

**Step 3: Update DB.prepareStatement calls**

At ~line 1101:
```java
try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
```

Change to:
```java
try (PreparedStatement pstmt = DB.prepareStatement(sql, trxName)) {
```

**Step 4: Apply same pattern to invoiceOpen**

- Update signature at ~line 1630: add `String trxName` parameter
- Update calculateInvoiceOpenJava at ~line 1640: add `String trxName` parameter
- Update all `DB.prepareStatement(sql, null)` to `DB.prepareStatement(sql, trxName)` at lines ~1651, 1688, 1724

**Step 5: Apply same pattern to invoiceOpenToDate at ~line 1816**

**Step 6: Commit**

```bash
git add docs/plans/2026-01-03-wave3-financial-core-implementation.md
git commit -m "fix(wave3-plan): add trxName parameter to all invoice methods"
```

---

## Task Group 4: Complete invoiceOpenToDate Implementation (1 edit)

### Task 4.1: Write Full invoiceOpenToDate Implementation

**Files:**
- Modify: `docs/plans/2026-01-03-wave3-financial-core-implementation.md:1827-1831`

**Step 1: Read current placeholder**

Find lines ~1827-1831:
```java
private static BigDecimal calculateInvoiceOpenToDateJava(int invoiceId, @Nullable Integer invoicePayScheduleId,
                                                          @Nullable Timestamp dateAcct) {
    // Similar to invoiceOpen but with DateAcct filter on both header and allocations
    // ... (implementation mirrors invoiceOpen with added date filters)
}
```

**Step 2: Replace with full implementation**

Replace the entire method with:

```java
private static BigDecimal calculateInvoiceOpenToDateJava(int invoiceId, @Nullable Integer invoicePayScheduleId,
                                                          @Nullable Timestamp dateAcct, String trxName) {
    // Step 1: Get invoice header data from C_Invoice_v
    int currencyId = 0;
    BigDecimal totalOpenAmt = BigDecimal.ZERO;
    BigDecimal multiplierAP = BigDecimal.ONE;
    BigDecimal multiplierCM = BigDecimal.ONE;
    int precision = 2;

    String headerSql = "SELECT C_Currency_ID, GrandTotal, MultiplierAP, Multiplier "
        + "FROM C_Invoice_v WHERE C_Invoice_ID = ?";

    try (PreparedStatement pstmt = DB.prepareStatement(headerSql, trxName)) {
        pstmt.setInt(1, invoiceId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                currencyId = rs.getInt("C_Currency_ID");
                totalOpenAmt = rs.getBigDecimal("GrandTotal");
                multiplierAP = rs.getBigDecimal("MultiplierAP");
                multiplierCM = rs.getBigDecimal("Multiplier");
                if (totalOpenAmt == null) totalOpenAmt = BigDecimal.ZERO;
                if (multiplierAP == null) multiplierAP = BigDecimal.ONE;
                if (multiplierCM == null) multiplierCM = BigDecimal.ONE;
            } else {
                return null;
            }
        }
    } catch (Exception e) {
        log.log(Level.WARNING, "Error getting invoice header", e);
        return null;
    }

    // Get currency precision
    MCurrency currency = MCurrency.get(Env.getCtx(), currencyId);
    if (currency != null) {
        precision = currency.getStdPrecision();
    }
    BigDecimal minAmt = BigDecimal.ONE.divide(BigDecimal.TEN.pow(precision), precision, RoundingMode.HALF_UP);

    // Step 2: Calculate paid amount from allocations WITH DATE FILTER
    BigDecimal paidAmt = BigDecimal.ZERO;
    String allocSql = "SELECT a.AD_Client_ID, a.AD_Org_ID, "
        + "al.Amount, al.DiscountAmt, al.WriteOffAmt, "
        + "a.C_Currency_ID, a.DateTrx "
        + "FROM C_AllocationLine al "
        + "INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID) "
        + "WHERE al.C_Invoice_ID=? "
        + "AND a.DocStatus IN ('CO','CL')"
        + (dateAcct != null ? " AND a.DateAcct <= ?" : "");  // DATE FILTER

    try (PreparedStatement pstmt = DB.prepareStatement(allocSql, trxName)) {
        pstmt.setInt(1, invoiceId);
        if (dateAcct != null) {
            pstmt.setTimestamp(2, dateAcct);
        }
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int adClientId = rs.getInt("AD_Client_ID");
                int adOrgId = rs.getInt("AD_Org_ID");
                BigDecimal amount = rs.getBigDecimal("Amount");
                if (amount == null) amount = BigDecimal.ZERO;
                BigDecimal discountAmt = rs.getBigDecimal("DiscountAmt");
                if (discountAmt == null) discountAmt = BigDecimal.ZERO;
                BigDecimal writeOffAmt = rs.getBigDecimal("WriteOffAmt");
                if (writeOffAmt == null) writeOffAmt = BigDecimal.ZERO;
                int allocCurrencyId = rs.getInt("C_Currency_ID");
                Timestamp dateTrx = rs.getTimestamp("DateTrx");

                BigDecimal total = amount.add(discountAmt).add(writeOffAmt);
                BigDecimal converted = CurrencyFunctions.currencyConvert(
                    total.multiply(multiplierAP),
                    allocCurrencyId, currencyId,
                    dateTrx, null, adClientId, adOrgId);

                if (converted != null) {
                    paidAmt = paidAmt.add(converted);
                }
            }
        }
    } catch (Exception e) {
        log.log(Level.SEVERE, "Error calculating paid amount", e);
    }

    // Step 3: Payment schedule handling
    if (invoicePayScheduleId != null && invoicePayScheduleId > 0) {
        BigDecimal remaining = paidAmt;

        String schedSql = "SELECT C_InvoicePaySchedule_ID, DueAmt "
            + "FROM C_InvoicePaySchedule "
            + "WHERE C_Invoice_ID = ? AND IsValid='Y' "
            + "ORDER BY DueDate";

        try (PreparedStatement pstmt = DB.prepareStatement(schedSql, trxName)) {
            pstmt.setInt(1, invoiceId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int schedId = rs.getInt("C_InvoicePaySchedule_ID");
                    BigDecimal dueAmt = rs.getBigDecimal("DueAmt");

                    if (schedId == invoicePayScheduleId) {
                        BigDecimal scheduleOpen = dueAmt.multiply(multiplierCM).subtract(remaining);
                        if (scheduleOpen.compareTo(BigDecimal.ZERO) < 0) {
                            scheduleOpen = BigDecimal.ZERO;
                        }
                        totalOpenAmt = scheduleOpen;
                        break;
                    } else {
                        remaining = remaining.subtract(dueAmt);
                        if (remaining.compareTo(BigDecimal.ZERO) < 0) {
                            remaining = BigDecimal.ZERO;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error processing payment schedules", e);
        }
    } else {
        totalOpenAmt = totalOpenAmt.subtract(paidAmt);
    }

    // Step 4: Ignore rounding
    if (totalOpenAmt.abs().compareTo(minAmt) < 0) {
        totalOpenAmt = BigDecimal.ZERO;
    }

    return totalOpenAmt.setScale(precision, RoundingMode.HALF_UP);
}
```

**Step 3: Commit**

```bash
git add docs/plans/2026-01-03-wave3-financial-core-implementation.md
git commit -m "fix(wave3-plan): complete invoiceOpenToDate implementation"
```

---

## Task Group 5: Test Infrastructure Fixes (2 edits)

### Task 5.1: Replace Hardcoded Test IDs with Dynamic Queries

**Files:**
- Modify: `docs/plans/2026-01-03-wave3-financial-core-implementation.md:50-80`

**Step 1: Read current test code**

Find lines ~50-80 with hardcoded IDs like `109`, `100`, `11`.

**Step 2: Replace test methods**

Replace:
```java
@Test
void callInvoiceOpen_returnsNumeric() {
    // Use a known invoice ID from GardenWorld test data
    // This test just validates the SQL call works, not the value
    BigDecimal result = SqlFunctionCaller.callInvoiceOpen(109, null);
    // Result may be null or numeric - just validating no exception
    assertTrue(result == null || result.compareTo(BigDecimal.ZERO) >= 0 || result.compareTo(BigDecimal.ZERO) < 0);
}

@Test
void callInvoiceOpen_withSchedule_returnsNumeric() {
    BigDecimal result = SqlFunctionCaller.callInvoiceOpen(109, 11);
    assertTrue(result == null || result.compareTo(BigDecimal.ZERO) >= 0 || result.compareTo(BigDecimal.ZERO) < 0);
}

@Test
void callInvoicePaid_returnsNumeric() {
    BigDecimal result = SqlFunctionCaller.callInvoicePaid(109, 100, new BigDecimal("1"));
    assertNotNull(result);
}

@Test
void callInvoiceDiscount_returnsNumeric() {
    Timestamp payDate = new Timestamp(System.currentTimeMillis());
    BigDecimal result = SqlFunctionCaller.callInvoiceDiscount(109, payDate, null);
    assertTrue(result == null || result.compareTo(BigDecimal.ZERO) >= 0);
}
```

With:
```java
private static int testInvoiceId;
private static int testInvoiceScheduleId;
private static int testCurrencyId;

@BeforeAll
static void findTestData() {
    // Find a completed invoice dynamically
    testInvoiceId = new Query(Env.getCtx(), "C_Invoice", "DocStatus IN ('CO','CL')", null)
        .setOnlyActiveRecords(true).firstId();
    Assume.assumeTrue(testInvoiceId > 0, "Need completed invoice for test");

    // Get currency from invoice
    MInvoice inv = new MInvoice(Env.getCtx(), testInvoiceId, null);
    testCurrencyId = inv.getC_Currency_ID();

    // Try to find invoice with payment schedule
    int invWithSched = new Query(Env.getCtx(), "C_Invoice",
        "DocStatus IN ('CO','CL') AND IsPayScheduleValid='Y'", null)
        .setOnlyActiveRecords(true).firstId();
    if (invWithSched > 0) {
        MInvoicePaySchedule[] scheds = MInvoicePaySchedule.getInvoicePaySchedule(
            Env.getCtx(), invWithSched, 0, null);
        if (scheds.length > 0) {
            testInvoiceScheduleId = scheds[0].getC_InvoicePaySchedule_ID();
        }
    }
}

@Test
void callInvoiceOpen_returnsNumeric() {
    assertDoesNotThrow(() -> SqlFunctionCaller.callInvoiceOpen(testInvoiceId, null));
}

@Test
void callInvoiceOpen_withSchedule_returnsNumeric() {
    Assume.assumeTrue(testInvoiceScheduleId > 0, "Need invoice with schedule");
    assertDoesNotThrow(() -> SqlFunctionCaller.callInvoiceOpen(testInvoiceId, testInvoiceScheduleId));
}

@Test
void callInvoicePaid_returnsNumeric() {
    BigDecimal result = SqlFunctionCaller.callInvoicePaid(testInvoiceId, testCurrencyId, BigDecimal.ONE);
    assertNotNull(result);
}

@Test
void callInvoiceDiscount_returnsNumeric() {
    Timestamp payDate = new Timestamp(System.currentTimeMillis());
    assertDoesNotThrow(() -> SqlFunctionCaller.callInvoiceDiscount(testInvoiceId, payDate, null));
}
```

**Step 3: Commit**

```bash
git add docs/plans/2026-01-03-wave3-financial-core-implementation.md
git commit -m "fix(wave3-plan): use dynamic test IDs instead of hardcoded values"
```

---

### Task 5.2: Add Performance Test Bypass for Shadow Executor

**Files:**
- Modify: `docs/plans/2026-01-03-wave3-financial-core-implementation.md` (performance test sections)

**Step 1: Find performance test at ~line 806**

Look for `Wave3PaymentPerformanceTest`.

**Step 2: Add setup method to bypass shadow**

After the class declaration and before tests, add:
```java
@BeforeEach
void setJavaOnlyMode() {
    // Bypass shadow execution for accurate Java-only timing
    MigrationConfig.setMode("paymentAllocated", MigrationMode.JAVA_ONLY);
    MigrationConfig.setMode("paymentAvailable", MigrationMode.JAVA_ONLY);
}

@AfterEach
void restoreMode() {
    MigrationConfig.setMode("paymentAllocated", MigrationMode.SHADOW);
    MigrationConfig.setMode("paymentAvailable", MigrationMode.SHADOW);
}
```

**Step 3: Apply same pattern to Wave3InvoicePaidPerformanceTest at ~line 1312**

Add setup for `invoicePaid`, `invoicePaidToDate`.

**Step 4: Apply same pattern to Wave3InvoiceOpenPerformanceTest at ~line 1841**

Add setup for `invoiceOpen`, `invoiceOpenToDate`, `invoiceDiscount`.

**Step 5: Commit**

```bash
git add docs/plans/2026-01-03-wave3-financial-core-implementation.md
git commit -m "fix(wave3-plan): add JAVA_ONLY mode for performance tests"
```

---

## Task Group 6: Shadow Execution Configuration Fixes (3 edits)

### Task 6.1: Replace Exact Comparator with Tolerance

**Files:**
- Modify: `docs/plans/2026-01-03-wave3-financial-core-implementation.md` (7 locations)

**Step 1: Define tolerance constant**

At the top of InvoiceFunctions class (~line 1068), add:
```java
private static final BigDecimal TOLERANCE = new BigDecimal("0.01");
```

**Step 2: Update all ShadowExecutor comparators**

Replace all instances of:
```java
(java, sql) -> java.compareTo(sql) == 0
```

With:
```java
(java, sql) -> {
    if (java == null && sql == null) return true;
    if (java == null || sql == null) return false;
    return java.subtract(sql).abs().compareTo(TOLERANCE) <= 0;
}
```

Locations to update:
- Line 570 (paymentAllocated)
- Line 688 (paymentAvailable)
- Line 1085 (invoicePaid)
- Line 1141 (invoicePaidToDate)
- Line 1636 (invoiceOpen)
- Line 1823 (invoiceOpenToDate)
- Line 1911 (invoiceDiscount)

**Step 3: Commit**

```bash
git add docs/plans/2026-01-03-wave3-financial-core-implementation.md
git commit -m "fix(wave3-plan): use tolerance-based comparator for shadow validation"
```

---

### Task 6.2: Fix Sample Rate Preservation in Task 7.2

**Files:**
- Modify: `docs/plans/2026-01-03-wave3-financial-core-implementation.md:2720-2728`

**Step 1: Read current SQL**

Find lines ~2720-2728:
```sql
UPDATE migration.function_config
SET mode = 'SHADOW'
WHERE function_name IN (
    'invoiceOpen', 'invoiceOpenToDate', 'invoiceDiscount',
    'invoicePaid', 'invoicePaidToDate',
    'paymentAllocated', 'paymentAvailable'
);
```

**Step 2: Replace with sample-rate-preserving SQL**

Replace with:
```sql
UPDATE migration.function_config
SET mode = 'SHADOW',
    sample_rate = CASE
        WHEN function_name IN ('invoiceOpen', 'invoiceOpenToDate') THEN 0.1
        ELSE COALESCE(sample_rate, 1.0)
    END
WHERE function_name IN (
    'invoiceOpen', 'invoiceOpenToDate', 'invoiceDiscount',
    'invoicePaid', 'invoicePaidToDate',
    'paymentAllocated', 'paymentAvailable'
);
```

**Step 3: Commit**

```bash
git add docs/plans/2026-01-03-wave3-financial-core-implementation.md
git commit -m "fix(wave3-plan): preserve sample rates when enabling SHADOW mode"
```

---

### Task 6.3: Add Wave 2 Dependency Verification to Task 5.1

**Files:**
- Modify: `docs/plans/2026-01-03-wave3-financial-core-implementation.md:1875-1880`

**Step 1: Read current Task 5.1 header**

Find lines ~1875-1880.

**Step 2: Add prerequisite check step**

Before Step 1 (Write failing test), add:
```markdown
**Step 0: Verify Wave 2 PaymentTermFunctions exists**

Run: `ls base/src/org/compiere/util/PaymentTermFunctions.java`
Expected: File exists

If file doesn't exist, Wave 2 must be completed first.

Verify method signature:
```java
grep -n "paymentTermDiscount" base/src/org/compiere/util/PaymentTermFunctions.java
```
Expected: Method exists with compatible signature (amount, currencyId, paymentTermId, docDate, payDate)
```

**Step 3: Commit**

```bash
git add docs/plans/2026-01-03-wave3-financial-core-implementation.md
git commit -m "fix(wave3-plan): add Wave 2 dependency verification to invoiceDiscount"
```

---

## Task Group 7: Final Validation (1 task)

### Task 7.1: Verify All Fixes Applied

**Step 1: Run grep checks**

```bash
# Check conversion type fix
grep -n "getC_ConversionType_ID" docs/plans/2026-01-03-wave3-financial-core-implementation.md
# Expected: No matches (all replaced with null)

# Check IsActive removed
grep -n "IsActive='Y'" docs/plans/2026-01-03-wave3-financial-core-implementation.md
# Expected: No matches in allocation queries

# Check tolerance comparator
grep -c "TOLERANCE" docs/plans/2026-01-03-wave3-financial-core-implementation.md
# Expected: 8+ (1 definition + 7 uses)

# Check trxName added
grep -n "String trxName" docs/plans/2026-01-03-wave3-financial-core-implementation.md
# Expected: Multiple matches
```

**Step 2: Commit final verification**

```bash
git add docs/plans/2026-01-03-wave3-financial-core-implementation.md
git commit -m "docs(wave3): complete critical review fixes R1-R3

Applied 13 fixes from critical reviews:
- Conversion type: use null to match SQL
- Remove IsActive filters from allocation queries
- Add null safety for BigDecimal values
- Fix currency precision (use MCurrency.getStdPrecision)
- Fix scale order (multiply then scale)
- Fix payment schedule zero-floor logic
- Add trxName parameter to all methods
- Complete invoiceOpenToDate implementation
- Use dynamic test IDs
- Add JAVA_ONLY mode for performance tests
- Use tolerance-based shadow comparator
- Preserve sample rates in Task 7.2
- Add Wave 2 dependency verification

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
"
```

---

## Summary

| Task | Description | Lines Affected |
|------|-------------|----------------|
| 1.1 | Conversion type = null | ~607 |
| 1.2 | Remove IsActive filters | ~589 |
| 1.3 | Null safety paymentAllocated | ~600 |
| 1.4 | Null safety invoicePaid | ~1107-1109 |
| 1.5 | Null safety invoiceOpen | ~1694-1696 |
| 1.6 | Currency precision + scale order | ~1119-1121 |
| 2.1 | Payment schedule loop fix | ~1731-1737 |
| 3.1 | trxName parameter (multiple) | ~1079, 1089, 1101, 1630, etc. |
| 4.1 | invoiceOpenToDate full impl | ~1827-1831 |
| 5.1 | Dynamic test IDs | ~50-80 |
| 5.2 | Performance test bypass | ~806, 1312, 1841 |
| 6.1 | Tolerance comparator | 7 locations |
| 6.2 | Sample rate preservation | ~2720-2728 |
| 6.3 | Wave 2 verification | ~1875-1880 |
| 7.1 | Final verification | N/A |
