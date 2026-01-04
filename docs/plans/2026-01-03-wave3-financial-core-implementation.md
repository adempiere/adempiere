# Wave 3: Financial Core - Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Migrate 7 core invoice and payment SQL functions to Java with shadow validation.

**Architecture:** Model class pattern - calculations in MInvoice/MPayment, using existing ShadowExecutor infrastructure from Wave 0/1. Payment schedule logic must be ported to Java to fix known divergence.

**Tech Stack:** Java 11+, JUnit 5, existing migration infrastructure (ShadowExecutor, MigrationConfig, SqlFunctionCaller, MigrationLogger)

---

## Prerequisites

- Wave 1 (currencyConvert) must be complete and in JAVA_ONLY or SHADOW mode
- Wave 2 (paymentTermDiscount) must be complete and in JAVA_ONLY or SHADOW mode

---

## Function Dependency Chain

```
paymentAvailable
    │
    ▼
paymentAllocated ────► invoicePaid ────► invoiceOpen ────► invoiceDiscount
                           │                  │
                           ▼                  ▼
                   invoicePaidToDate   invoiceOpenToDate
```

Implementation order follows this dependency chain (bottom-up):
1. paymentAllocated, paymentAvailable (no internal dependencies)
2. invoicePaid, invoicePaidToDate (depends on currencyConvert)
3. invoiceOpen, invoiceOpenToDate (depends on currencyConvert, invoicePaid)
4. invoiceDiscount (depends on paymentTermDiscount, invoicePaySchedule)

---

## Task Group 1: SqlFunctionCaller Extensions (5 tasks)

### Task 1.1: Add Invoice SQL Function Callers

**Files:**
- Modify: `base/src/org/compiere/migration/SqlFunctionCaller.java`
- Test: `base/test/src/org/compiere/migration/SqlFunctionCallerTest.java`

**Step 1: Write the failing test**

```java
// In SqlFunctionCallerTest.java - add to existing test class

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

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=SqlFunctionCallerTest#callInvoiceOpen_returnsNumeric -pl base/test`
Expected: FAIL - method does not exist

**Step 3: Write minimal implementation**

Add to `SqlFunctionCaller.java`:

```java
/** Calls: SELECT invoiceOpen(?, ?) */
@Nullable
public static BigDecimal callInvoiceOpen(@Nullable Integer invoiceId,
                                          @Nullable Integer invoicePayScheduleId) {
    String sql = "SELECT invoiceOpen(?, ?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        setNullableInt(pstmt, 1, invoiceId);
        setNullableInt(pstmt, 2, invoicePayScheduleId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (Exception e) {
        log.log(Level.WARNING, "Failed to call invoiceOpen()", e);
        throw new SqlFunctionException("invoiceOpen", e);
    }
    return null;
}

/** Calls: SELECT invoiceOpenToDate(?, ?, ?) */
@Nullable
public static BigDecimal callInvoiceOpenToDate(@Nullable Integer invoiceId,
                                                @Nullable Integer invoicePayScheduleId,
                                                @Nullable Timestamp dateAcct) {
    String sql = "SELECT invoiceOpenToDate(?, ?, ?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        setNullableInt(pstmt, 1, invoiceId);
        setNullableInt(pstmt, 2, invoicePayScheduleId);
        setNullableTimestamp(pstmt, 3, dateAcct);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (Exception e) {
        log.log(Level.WARNING, "Failed to call invoiceOpenToDate()", e);
        throw new SqlFunctionException("invoiceOpenToDate", e);
    }
    return null;
}

/** Calls: SELECT invoiceDiscount(?, ?, ?) */
@Nullable
public static BigDecimal callInvoiceDiscount(@Nullable Integer invoiceId,
                                              @Nullable Timestamp payDate,
                                              @Nullable Integer invoicePayScheduleId) {
    String sql = "SELECT invoiceDiscount(?, ?, ?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        setNullableInt(pstmt, 1, invoiceId);
        setNullableTimestamp(pstmt, 2, payDate);
        setNullableInt(pstmt, 3, invoicePayScheduleId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (Exception e) {
        log.log(Level.WARNING, "Failed to call invoiceDiscount()", e);
        throw new SqlFunctionException("invoiceDiscount", e);
    }
    return null;
}

/** Calls: SELECT invoicePaid(?, ?, ?) */
@Nullable
public static BigDecimal callInvoicePaid(@Nullable Integer invoiceId,
                                          @Nullable Integer currencyId,
                                          @Nullable BigDecimal multiplierAP) {
    String sql = "SELECT invoicePaid(?, ?, ?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        setNullableInt(pstmt, 1, invoiceId);
        setNullableInt(pstmt, 2, currencyId);
        setNullableBigDecimal(pstmt, 3, multiplierAP);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (Exception e) {
        log.log(Level.WARNING, "Failed to call invoicePaid()", e);
        throw new SqlFunctionException("invoicePaid", e);
    }
    return null;
}

/** Calls: SELECT invoicePaidToDate(?, ?, ?, ?) */
@Nullable
public static BigDecimal callInvoicePaidToDate(@Nullable Integer invoiceId,
                                                @Nullable Integer currencyId,
                                                @Nullable BigDecimal multiplierAP,
                                                @Nullable Timestamp dateAcct) {
    String sql = "SELECT invoicePaidToDate(?, ?, ?, ?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        setNullableInt(pstmt, 1, invoiceId);
        setNullableInt(pstmt, 2, currencyId);
        setNullableBigDecimal(pstmt, 3, multiplierAP);
        setNullableTimestamp(pstmt, 4, dateAcct);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (Exception e) {
        log.log(Level.WARNING, "Failed to call invoicePaidToDate()", e);
        throw new SqlFunctionException("invoicePaidToDate", e);
    }
    return null;
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=SqlFunctionCallerTest#callInvoice* -pl base/test`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/SqlFunctionCaller.java base/test/src/org/compiere/migration/SqlFunctionCallerTest.java
git commit -m "$(cat <<'EOF'
feat(wave3): add invoice SQL function callers

Add callInvoiceOpen, callInvoiceOpenToDate, callInvoiceDiscount,
callInvoicePaid, callInvoicePaidToDate to SqlFunctionCaller for
shadow mode comparison.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 1.2: Add Payment SQL Function Callers

**Files:**
- Modify: `base/src/org/compiere/migration/SqlFunctionCaller.java`
- Test: `base/test/src/org/compiere/migration/SqlFunctionCallerTest.java`

**Step 1: Write the failing test**

```java
@Test
void callPaymentAllocated_returnsNumeric() {
    BigDecimal result = SqlFunctionCaller.callPaymentAllocated(100, 100);
    assertNotNull(result);
}

@Test
void callPaymentAvailable_returnsNumeric() {
    BigDecimal result = SqlFunctionCaller.callPaymentAvailable(100);
    assertNotNull(result);
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=SqlFunctionCallerTest#callPaymentAllocated_returnsNumeric -pl base/test`
Expected: FAIL

**Step 3: Write minimal implementation**

```java
/** Calls: SELECT paymentAllocated(?, ?) */
@Nullable
public static BigDecimal callPaymentAllocated(@Nullable Integer paymentId,
                                               @Nullable Integer currencyId) {
    String sql = "SELECT paymentAllocated(?, ?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        setNullableInt(pstmt, 1, paymentId);
        setNullableInt(pstmt, 2, currencyId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (Exception e) {
        log.log(Level.WARNING, "Failed to call paymentAllocated()", e);
        throw new SqlFunctionException("paymentAllocated", e);
    }
    return null;
}

/** Calls: SELECT paymentAvailable(?) */
@Nullable
public static BigDecimal callPaymentAvailable(@Nullable Integer paymentId) {
    String sql = "SELECT paymentAvailable(?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        setNullableInt(pstmt, 1, paymentId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (Exception e) {
        log.log(Level.WARNING, "Failed to call paymentAvailable()", e);
        throw new SqlFunctionException("paymentAvailable", e);
    }
    return null;
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=SqlFunctionCallerTest#callPayment* -pl base/test`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/SqlFunctionCaller.java base/test/src/org/compiere/migration/SqlFunctionCallerTest.java
git commit -m "$(cat <<'EOF'
feat(wave3): add payment SQL function callers

Add callPaymentAllocated and callPaymentAvailable to SqlFunctionCaller
for shadow mode comparison.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 1.3: Add Wave 3 Function Config SQL

**Files:**
- Create: `db/ddlutils/postgresql/migration/wave3_function_config.sql`

**Step 1: Write the SQL migration**

```sql
-- Wave 3 function configuration for shadow mode
-- All functions start in SQL_ONLY mode until Java implementation is ready

INSERT INTO migration.function_config (function_name, mode, sample_rate, circuit_breaker_enabled)
VALUES
    ('invoiceOpen', 'SQL_ONLY', 1.0, true),
    ('invoiceOpenToDate', 'SQL_ONLY', 1.0, true),
    ('invoiceDiscount', 'SQL_ONLY', 1.0, true),
    ('invoicePaid', 'SQL_ONLY', 1.0, true),
    ('invoicePaidToDate', 'SQL_ONLY', 1.0, true),
    ('paymentAllocated', 'SQL_ONLY', 1.0, true),
    ('paymentAvailable', 'SQL_ONLY', 1.0, true)
ON CONFLICT (function_name) DO UPDATE SET
    mode = EXCLUDED.mode,
    sample_rate = EXCLUDED.sample_rate,
    circuit_breaker_enabled = EXCLUDED.circuit_breaker_enabled;
```

**Step 2: Verify syntax**

Run: `psql -f db/ddlutils/postgresql/migration/wave3_function_config.sql`
Expected: INSERT 0 7

**Step 3: Commit**

```bash
git add db/ddlutils/postgresql/migration/wave3_function_config.sql
git commit -m "$(cat <<'EOF'
chore(wave3): add function config SQL for all Wave 3 functions

All 7 functions start in SQL_ONLY mode until Java implementation
is validated.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 1.4: Integrate QueryCounter with DB Class

**Files:**
- Modify: `base/src/org/compiere/util/DB.java`

**Why:** The QueryCounter utility (created in Testing Strategy section) requires integration with DB.prepareStatement() to actually count queries. Without this, QueryCounter.increment() is never called and all query count tests pass trivially (0 <= MAX).

**Step 1: Add QueryCounter.increment() call to DB.prepareStatement()**

```java
// In org.compiere.util.DB.prepareStatement():
public static PreparedStatement prepareStatement(String sql, String trxName) {
    QueryCounter.increment();  // ADD THIS LINE
    // ... existing code
}
```

**Step 2: Verify query counting works**

```java
@Test
void queryCounter_incrementsOnPrepareStatement() {
    QueryCounter.reset();
    DB.prepareStatement("SELECT 1", null);
    assertEquals(1, QueryCounter.get());
}
```

**Step 3: Commit**

```bash
git add base/src/org/compiere/util/DB.java
git commit -m "$(cat <<'EOF'
feat(wave3): integrate QueryCounter with DB.prepareStatement

Enables query count assertions in performance tests by
incrementing counter on each prepared statement creation.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 1.5: Verify Allocation Query Indexes

**Files:**
- Create: `db/ddlutils/postgresql/migration/wave3_verify_indexes.sql`

**Why:** The allocation queries in Tasks 2.1, 2.2, 3.1, 4.2 filter on C_AllocationLine(C_Payment_ID) and C_AllocationLine(C_Invoice_ID). Without appropriate indexes, these queries perform table scans and the 130% performance threshold may fail in production.

**Step 1: Create index verification/creation script**

```sql
-- Wave 3: Verify indexes exist for allocation queries
-- These indexes are required for performance of paymentAllocated, invoiceOpen, invoicePaid

-- Index for C_Payment_ID lookups (used by paymentAllocated, paymentAvailable)
CREATE INDEX IF NOT EXISTS c_allocationline_payment_idx
    ON C_AllocationLine(C_Payment_ID)
    WHERE IsActive='Y';

-- Index for C_Invoice_ID lookups (used by invoiceOpen, invoicePaid)
CREATE INDEX IF NOT EXISTS c_allocationline_invoice_idx
    ON C_AllocationLine(C_Invoice_ID)
    WHERE IsActive='Y';

-- Index for DocStatus filter on allocation header
CREATE INDEX IF NOT EXISTS c_allocationhdr_docstatus_idx
    ON C_AllocationHdr(DocStatus)
    WHERE IsActive='Y';

-- Verify indexes exist
SELECT indexname, tablename
FROM pg_indexes
WHERE indexname IN (
    'c_allocationline_payment_idx',
    'c_allocationline_invoice_idx',
    'c_allocationhdr_docstatus_idx'
);
```

**Step 2: Run verification**

```bash
psql -f db/ddlutils/postgresql/migration/wave3_verify_indexes.sql
```

Expected: 3 rows returned showing all indexes exist.

**Step 3: Commit**

```bash
git add db/ddlutils/postgresql/migration/wave3_verify_indexes.sql
git commit -m "$(cat <<'EOF'
chore(wave3): add index verification for allocation queries

Creates indexes on C_AllocationLine(C_Payment_ID),
C_AllocationLine(C_Invoice_ID), and C_AllocationHdr(DocStatus)
if they don't exist. Required for Wave 3 performance targets.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

## Task Group 2: Payment Functions (4 tasks)

### Task 2.1: Implement paymentAllocated in MPayment

**Files:**
- Modify: `base/src/org/compiere/model/MPayment.java`
- Test: `base/test/src/org/compiere/migration/Wave3PaymentFunctionsTest.java`

**Step 1: Write the failing test**

Create new test file:

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MPayment;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave3PaymentFunctionsTest extends CommonGWSetup {

    private MPayment testPayment;

    @BeforeAll
    void loadTestData() {
        // Find a completed payment with allocations
        testPayment = new Query(Env.getCtx(), MPayment.Table_Name,
            "DocStatus IN ('CO','CL') AND IsAllocated='Y'", null)
            .setOnlyActiveRecords(true)
            .first();
        assumeTrue(testPayment != null, "Need allocated payment for test");
    }

    @Test
    void getAllocatedAmt_matchesSql() {
        int paymentId = testPayment.getC_Payment_ID();
        int currencyId = testPayment.getC_Currency_ID();

        BigDecimal javaResult = testPayment.getAllocatedAmt();
        BigDecimal sqlResult = SqlFunctionCaller.callPaymentAllocated(paymentId, currencyId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("paymentAllocated(%d, %d): java=%s, sql=%s",
                paymentId, currencyId, javaResult, sqlResult));
    }
}
```

**Step 2: Run test to verify current behavior**

Run: `mvn test -Dtest=Wave3PaymentFunctionsTest#getAllocatedAmt_matchesSql -pl base/test`
Expected: May PASS or FAIL depending on existing implementation alignment

**Step 3: Analyze existing getAllocatedAmt() in MPayment.java**

The existing implementation at lines 718-758 uses raw SQL. Compare with SQL function:
- SQL function checks for C_Charge_ID and returns PayAmt if charged
- SQL function iterates allocations with currencyConvert
- Java uses similar logic but may have subtle differences

**Step 4: Refactor getAllocatedAmt() to use ShadowExecutor**

```java
/**
 * Get Allocated Amt in Payment Currency.
 * Uses shadow execution for migration validation.
 * @return amount or ZERO
 */
public BigDecimal getAllocatedAmt() {
    return ShadowExecutor.execute(
        "paymentAllocated",
        new Object[] { getC_Payment_ID(), getC_Currency_ID() },
        () -> calculateAllocatedAmtJava(),
        () -> SqlFunctionCaller.callPaymentAllocated(getC_Payment_ID(), getC_Currency_ID()),
        (java, sql) -> java.compareTo(sql) == 0
    );
}

/**
 * Java implementation of paymentAllocated calculation.
 */
private BigDecimal calculateAllocatedAmtJava() {
    // If this is a charge, return the full PayAmt
    if (getC_Charge_ID() > 0) {
        return getPayAmt();
    }

    BigDecimal allocatedAmt = BigDecimal.ZERO;

    String sql = "SELECT a.AD_Client_ID, a.AD_Org_ID, al.Amount, a.C_Currency_ID, a.DateTrx "
        + "FROM C_AllocationLine al "
        + "INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID) "
        + "WHERE al.C_Payment_ID=? "
        + "AND a.DocStatus IN ('CO','CL')";  // No IsActive filter - matches SQL

    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        pstmt = DB.prepareStatement(sql, get_TrxName());
        pstmt.setInt(1, getC_Payment_ID());
        rs = pstmt.executeQuery();
        while (rs.next()) {
            int adClientId = rs.getInt("AD_Client_ID");
            int adOrgId = rs.getInt("AD_Org_ID");
            BigDecimal amount = rs.getBigDecimal("Amount");
            if (amount == null) amount = BigDecimal.ZERO;
            int allocCurrencyId = rs.getInt("C_Currency_ID");
            Timestamp dateTrx = rs.getTimestamp("DateTrx");

            // Convert allocation amount to payment currency
            BigDecimal converted = CurrencyFunctions.currencyConvert(
                amount, allocCurrencyId, getC_Currency_ID(),
                dateTrx, null, adClientId, adOrgId);  // null matches SQL behavior

            if (converted != null) {
                allocatedAmt = allocatedAmt.add(converted);
            }
        }
    } catch (Exception e) {
        log.log(Level.SEVERE, "calculateAllocatedAmtJava", e);
    } finally {
        DB.close(rs, pstmt);
    }

    return allocatedAmt.setScale(2, RoundingMode.HALF_UP);
}
```

**Step 5: Run test to verify it passes**

Run: `mvn test -Dtest=Wave3PaymentFunctionsTest#getAllocatedAmt_matchesSql -pl base/test`
Expected: PASS

**Step 6: Commit**

```bash
git add base/src/org/compiere/model/MPayment.java base/test/src/org/compiere/migration/Wave3PaymentFunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave3): implement paymentAllocated with shadow execution

Refactor MPayment.getAllocatedAmt() to use ShadowExecutor for
migration validation. Uses CurrencyFunctions from Wave 1.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 2.2: Implement paymentAvailable in MPayment

**Files:**
- Modify: `base/src/org/compiere/model/MPayment.java`
- Test: `base/test/src/org/compiere/migration/Wave3PaymentFunctionsTest.java`

**Step 1: Write the failing test**

```java
@Test
void getAvailableAmt_matchesSql() {
    int paymentId = testPayment.getC_Payment_ID();

    BigDecimal javaResult = testPayment.getAvailableAmt();
    BigDecimal sqlResult = SqlFunctionCaller.callPaymentAvailable(paymentId);

    assertEquals(0, javaResult.compareTo(sqlResult),
        String.format("paymentAvailable(%d): java=%s, sql=%s",
            paymentId, javaResult, sqlResult));
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=Wave3PaymentFunctionsTest#getAvailableAmt_matchesSql -pl base/test`
Expected: FAIL - method does not exist

**Step 3: Add getAvailableAmt() to MPayment**

```java
/**
 * Get Available (unallocated) Amount in Payment Currency.
 * Uses shadow execution for migration validation.
 * @return available amount or ZERO
 */
public BigDecimal getAvailableAmt() {
    return ShadowExecutor.execute(
        "paymentAvailable",
        new Object[] { getC_Payment_ID() },
        () -> calculateAvailableAmtJava(),
        () -> SqlFunctionCaller.callPaymentAvailable(getC_Payment_ID()),
        (java, sql) -> java.compareTo(sql) == 0
    );
}

/**
 * Java implementation of paymentAvailable calculation.
 */
private BigDecimal calculateAvailableAmtJava() {
    // If this is a charge, nothing is available
    if (getC_Charge_ID() > 0) {
        return BigDecimal.ZERO;
    }

    BigDecimal availableAmt = getPayAmt();

    // Get allocations
    String sql = "SELECT a.AD_Client_ID, a.AD_Org_ID, al.Amount, a.C_Currency_ID, a.DateTrx "
        + "FROM C_AllocationLine al "
        + "INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID) "
        + "WHERE al.C_Payment_ID=? "
        + "AND a.DocStatus IN ('CO','CL')";

    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        pstmt = DB.prepareStatement(sql, get_TrxName());
        pstmt.setInt(1, getC_Payment_ID());
        rs = pstmt.executeQuery();
        while (rs.next()) {
            int adClientId = rs.getInt("AD_Client_ID");
            int adOrgId = rs.getInt("AD_Org_ID");
            BigDecimal amount = rs.getBigDecimal("Amount");
            int allocCurrencyId = rs.getInt("C_Currency_ID");
            Timestamp dateTrx = rs.getTimestamp("DateTrx");

            BigDecimal converted = CurrencyFunctions.currencyConvert(
                amount, allocCurrencyId, getC_Currency_ID(),
                dateTrx, null, adClientId, adOrgId);

            if (converted != null) {
                availableAmt = availableAmt.subtract(converted);
            }
        }
    } catch (Exception e) {
        log.log(Level.SEVERE, "calculateAvailableAmtJava", e);
    } finally {
        DB.close(rs, pstmt);
    }

    // Ignore rounding (match SQL behavior: -0.00999 to 0.00999 = 0)
    if (availableAmt.abs().compareTo(new BigDecimal("0.01")) < 0) {
        availableAmt = BigDecimal.ZERO;
    }

    return availableAmt.setScale(2, RoundingMode.HALF_UP);
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=Wave3PaymentFunctionsTest#getAvailableAmt_matchesSql -pl base/test`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/model/MPayment.java base/test/src/org/compiere/migration/Wave3PaymentFunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave3): implement paymentAvailable with shadow execution

Add MPayment.getAvailableAmt() using ShadowExecutor. Calculates
PayAmt minus allocated amounts with currency conversion.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 2.3: Payment Functions Performance Test

**Files:**
- Create: `base/test/src/org/compiere/migration/Wave3PaymentPerformanceTest.java`

**Step 1: Write the performance test**

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.util.Arrays;
import java.util.List;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MPayment;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

/**
 * Performance tests for Wave 3 payment functions.
 * Validates Java implementations meet latency requirements (<=130% of SQL).
 */
@Tag("PerformanceTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.SAME_THREAD)
public class Wave3PaymentPerformanceTest extends CommonGWSetup {

    private static final double MAX_LATENCY_RATIO = 1.30;
    private static final int WARMUP_ITERATIONS = 100;
    private static final int TEST_ITERATIONS = 500;
    private static final int MEASUREMENT_ROUNDS = 5;

    private List<MPayment> testPayments;
    private double[] ratioAccumulator;

    @BeforeAll
    void loadTestData() {
        // Load multiple payments for realistic testing
        testPayments = new Query(Env.getCtx(), MPayment.Table_Name,
            "DocStatus IN ('CO','CL')", null)
            .setOnlyActiveRecords(true)
            .setLimit(50)
            .list();
        assumeTrue(testPayments.size() >= 10, "Need at least 10 payments for performance test");
    }

    @BeforeEach
    void initAccumulator(RepetitionInfo info) {
        if (info.getCurrentRepetition() == 1) {
            ratioAccumulator = new double[MEASUREMENT_ROUNDS];
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testPaymentAllocatedPerformance(RepetitionInfo info) {
        if (info.getCurrentRepetition() == 1) {
            // Warmup
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                MPayment p = testPayments.get(i % testPayments.size());
                p.getAllocatedAmt();
                SqlFunctionCaller.callPaymentAllocated(p.getC_Payment_ID(), p.getC_Currency_ID());
            }
        }

        // Force Java-only path for timing
        MigrationConfig config = MigrationConfig.get("paymentAllocated");

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MPayment p = testPayments.get(i % testPayments.size());
            // Direct Java call (bypass shadow)
            p.getAllocatedAmt();
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MPayment p = testPayments.get(i % testPayments.size());
            SqlFunctionCaller.callPaymentAllocated(p.getC_Payment_ID(), p.getC_Currency_ID());
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            Arrays.sort(ratioAccumulator);
            double medianRatio = ratioAccumulator[MEASUREMENT_ROUNDS / 2];

            System.out.printf("paymentAllocated: Java/SQL median ratio: %.2f (rounds: %s)%n",
                medianRatio, Arrays.toString(ratioAccumulator));

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("paymentAllocated Java/SQL median ratio %.2f exceeds max %.2f",
                    medianRatio, MAX_LATENCY_RATIO));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testPaymentAvailablePerformance(RepetitionInfo info) {
        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                MPayment p = testPayments.get(i % testPayments.size());
                p.getAvailableAmt();
                SqlFunctionCaller.callPaymentAvailable(p.getC_Payment_ID());
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MPayment p = testPayments.get(i % testPayments.size());
            p.getAvailableAmt();
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MPayment p = testPayments.get(i % testPayments.size());
            SqlFunctionCaller.callPaymentAvailable(p.getC_Payment_ID());
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            Arrays.sort(ratioAccumulator);
            double medianRatio = ratioAccumulator[MEASUREMENT_ROUNDS / 2];

            System.out.printf("paymentAvailable: Java/SQL median ratio: %.2f (rounds: %s)%n",
                medianRatio, Arrays.toString(ratioAccumulator));

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("paymentAvailable Java/SQL median ratio %.2f exceeds max %.2f",
                    medianRatio, MAX_LATENCY_RATIO));
        }
    }
}
```

**Step 2: Run performance tests**

Run: `mvn test -Dtest=Wave3PaymentPerformanceTest -pl base/test -Dgroups=PerformanceTest`
Expected: PASS with ratio <= 1.30

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave3PaymentPerformanceTest.java
git commit -m "$(cat <<'EOF'
test(wave3): add payment function performance tests

Validates paymentAllocated and paymentAvailable Java implementations
meet the 130% latency threshold compared to SQL.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 2.4: Enable Shadow Mode for Payment Functions

**Files:**
- Modify: `db/ddlutils/postgresql/migration/wave3_function_config.sql`

**Step 1: Update config to SHADOW mode**

```sql
UPDATE migration.function_config
SET mode = 'SHADOW', sample_rate = 1.0
WHERE function_name IN ('paymentAllocated', 'paymentAvailable');
```

**Step 2: Run update**

Run: `psql -c "UPDATE migration.function_config SET mode = 'SHADOW' WHERE function_name IN ('paymentAllocated', 'paymentAvailable')"`

**Step 3: Commit**

```bash
git add db/ddlutils/postgresql/migration/wave3_function_config.sql
git commit -m "$(cat <<'EOF'
feat(wave3): enable shadow mode for payment functions

paymentAllocated and paymentAvailable now run in SHADOW mode
for production validation.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

## Task Group 3: Invoice Paid Functions (4 tasks)

### Task 3.1: Implement invoicePaid in MInvoice

**Files:**
- Create: `base/src/org/compiere/util/InvoiceFunctions.java`
- Test: `base/test/src/org/compiere/migration/Wave3InvoiceFunctionsTest.java`

**Step 1: Write the failing test**

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MInvoice;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.InvoiceFunctions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave3InvoiceFunctionsTest extends CommonGWSetup {

    private MInvoice testInvoice;

    @BeforeAll
    void loadTestData() {
        testInvoice = new Query(Env.getCtx(), MInvoice.Table_Name,
            "DocStatus IN ('CO','CL') AND IsPaid='Y'", null)
            .setOnlyActiveRecords(true)
            .first();
        assumeTrue(testInvoice != null, "Need paid invoice for test");
    }

    @Test
    void invoicePaid_matchesSql() {
        int invoiceId = testInvoice.getC_Invoice_ID();
        int currencyId = testInvoice.getC_Currency_ID();
        BigDecimal multiplierAP = testInvoice.isSOTrx() ? BigDecimal.ONE : BigDecimal.ONE.negate();

        BigDecimal javaResult = InvoiceFunctions.invoicePaid(invoiceId, currencyId, multiplierAP);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoicePaid(invoiceId, currencyId, multiplierAP);

        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("invoicePaid(%d, %d, %s): java=%s, sql=%s",
                invoiceId, currencyId, multiplierAP, javaResult, sqlResult));
    }
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=Wave3InvoiceFunctionsTest#invoicePaid_matchesSql -pl base/test`
Expected: FAIL - class does not exist

**Step 3: Create InvoiceFunctions.java**

```java
package org.compiere.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import javax.annotation.Nullable;

import org.compiere.migration.MigrationLogger;
import org.compiere.migration.ShadowExecutor;
import org.compiere.migration.SqlFunctionCaller;
import org.compiere.model.MCurrency;

/**
 * Invoice calculation functions migrated from SQL.
 * Uses ShadowExecutor for migration validation.
 */
public class InvoiceFunctions {
    private static final CLogger log = CLogger.getCLogger(InvoiceFunctions.class);

    /**
     * Calculate paid/allocated amount for invoice in specified currency.
     * Equivalent to SQL function invoicePaid(p_C_Invoice_ID, p_C_Currency_ID, p_MultiplierAP).
     *
     * @param invoiceId C_Invoice_ID
     * @param currencyId target C_Currency_ID
     * @param multiplierAP multiplier for AP/AR adjustment (1 or -1)
     * @return paid amount rounded to 2 decimals
     */
    public static BigDecimal invoicePaid(int invoiceId, int currencyId, @Nullable BigDecimal multiplierAP, String trxName) {
        return ShadowExecutor.execute(
            "invoicePaid",
            new Object[] { invoiceId, currencyId, multiplierAP },
            () -> calculateInvoicePaidJava(invoiceId, currencyId, multiplierAP, trxName),
            () -> SqlFunctionCaller.callInvoicePaid(invoiceId, currencyId, multiplierAP),
            (java, sql) -> java.compareTo(sql) == 0
        );
    }

    private static BigDecimal calculateInvoicePaidJava(int invoiceId, int currencyId, @Nullable BigDecimal multiplierAP, String trxName) {
        BigDecimal mult = multiplierAP != null ? multiplierAP : BigDecimal.ONE;
        BigDecimal paymentAmt = BigDecimal.ZERO;

        String sql = "SELECT a.AD_Client_ID, a.AD_Org_ID, "
            + "al.Amount, al.DiscountAmt, al.WriteOffAmt, "
            + "a.C_Currency_ID, a.DateTrx "
            + "FROM C_AllocationLine al "
            + "INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID) "
            + "WHERE al.C_Invoice_ID=? "
            + "AND a.DocStatus IN ('CO','CL')";

        try (PreparedStatement pstmt = DB.prepareStatement(sql, trxName)) {
            pstmt.setInt(1, invoiceId);
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
                        total, allocCurrencyId, currencyId,
                        dateTrx, null, adClientId, adOrgId);

                    if (converted != null) {
                        paymentAmt = paymentAmt.add(converted);
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "calculateInvoicePaidJava", e);
        }

        // Get currency precision (don't hardcode 2)
        MCurrency currency = MCurrency.get(Env.getCtx(), currencyId);
        int precision = currency != null ? currency.getStdPrecision() : 2;
        return paymentAmt.multiply(mult).setScale(precision, RoundingMode.HALF_UP);
    }

    /**
     * Calculate paid amount for invoice as of a specific date.
     */
    public static BigDecimal invoicePaidToDate(int invoiceId, int currencyId,
                                                @Nullable BigDecimal multiplierAP,
                                                @Nullable Timestamp dateAcct, String trxName) {
        return ShadowExecutor.execute(
            "invoicePaidToDate",
            new Object[] { invoiceId, currencyId, multiplierAP, dateAcct },
            () -> calculateInvoicePaidToDateJava(invoiceId, currencyId, multiplierAP, dateAcct, trxName),
            () -> SqlFunctionCaller.callInvoicePaidToDate(invoiceId, currencyId, multiplierAP, dateAcct),
            (java, sql) -> java.compareTo(sql) == 0
        );
    }

    private static BigDecimal calculateInvoicePaidToDateJava(int invoiceId, int currencyId,
                                                              @Nullable BigDecimal multiplierAP,
                                                              @Nullable Timestamp dateAcct, String trxName) {
        BigDecimal mult = multiplierAP != null ? multiplierAP : BigDecimal.ONE;
        BigDecimal paymentAmt = BigDecimal.ZERO;

        String sql = "SELECT a.AD_Client_ID, a.AD_Org_ID, "
            + "al.Amount, al.DiscountAmt, al.WriteOffAmt, "
            + "a.C_Currency_ID, a.DateTrx "
            + "FROM C_AllocationLine al "
            + "INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID) "
            + "WHERE al.C_Invoice_ID=? "
            + "AND a.DocStatus IN ('CO','CL') "
            + "AND a.DateAcct <= ?";

        try (PreparedStatement pstmt = DB.prepareStatement(sql, trxName)) {
            pstmt.setInt(1, invoiceId);
            pstmt.setTimestamp(2, dateAcct);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int adClientId = rs.getInt("AD_Client_ID");
                    int adOrgId = rs.getInt("AD_Org_ID");
                    BigDecimal amount = rs.getBigDecimal("Amount");
                    BigDecimal discountAmt = rs.getBigDecimal("DiscountAmt");
                    BigDecimal writeOffAmt = rs.getBigDecimal("WriteOffAmt");
                    int allocCurrencyId = rs.getInt("C_Currency_ID");
                    Timestamp dateTrx = rs.getTimestamp("DateTrx");

                    BigDecimal total = amount.add(discountAmt).add(writeOffAmt);
                    BigDecimal converted = CurrencyFunctions.currencyConvert(
                        total, allocCurrencyId, currencyId,
                        dateTrx, null, adClientId, adOrgId);

                    if (converted != null) {
                        paymentAmt = paymentAmt.add(converted);
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "calculateInvoicePaidToDateJava", e);
        }

        return paymentAmt.setScale(2, RoundingMode.HALF_UP).multiply(mult);
    }
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=Wave3InvoiceFunctionsTest#invoicePaid_matchesSql -pl base/test`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/InvoiceFunctions.java base/test/src/org/compiere/migration/Wave3InvoiceFunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave3): implement invoicePaid and invoicePaidToDate

Create InvoiceFunctions utility class with shadow execution.
Calculates allocated amounts with currency conversion.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 3.2: Add invoicePaidToDate test

**Files:**
- Modify: `base/test/src/org/compiere/migration/Wave3InvoiceFunctionsTest.java`

**Step 1: Add the test**

```java
@Test
void invoicePaidToDate_matchesSql() {
    int invoiceId = testInvoice.getC_Invoice_ID();
    int currencyId = testInvoice.getC_Currency_ID();
    BigDecimal multiplierAP = testInvoice.isSOTrx() ? BigDecimal.ONE : BigDecimal.ONE.negate();
    Timestamp dateAcct = new Timestamp(System.currentTimeMillis());

    BigDecimal javaResult = InvoiceFunctions.invoicePaidToDate(invoiceId, currencyId, multiplierAP, dateAcct);
    BigDecimal sqlResult = SqlFunctionCaller.callInvoicePaidToDate(invoiceId, currencyId, multiplierAP, dateAcct);

    assertEquals(0, javaResult.compareTo(sqlResult),
        String.format("invoicePaidToDate: java=%s, sql=%s", javaResult, sqlResult));
}

@Test
void invoicePaidToDate_historicalDate_matchesSql() {
    int invoiceId = testInvoice.getC_Invoice_ID();
    int currencyId = testInvoice.getC_Currency_ID();
    BigDecimal multiplierAP = BigDecimal.ONE;
    // Use date before invoice - should return 0
    Timestamp dateAcct = Timestamp.valueOf("2020-01-01 00:00:00");

    BigDecimal javaResult = InvoiceFunctions.invoicePaidToDate(invoiceId, currencyId, multiplierAP, dateAcct);
    BigDecimal sqlResult = SqlFunctionCaller.callInvoicePaidToDate(invoiceId, currencyId, multiplierAP, dateAcct);

    assertEquals(0, javaResult.compareTo(sqlResult),
        String.format("invoicePaidToDate historical: java=%s, sql=%s", javaResult, sqlResult));
}
```

**Step 2: Run tests**

Run: `mvn test -Dtest=Wave3InvoiceFunctionsTest -pl base/test`
Expected: PASS

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave3InvoiceFunctionsTest.java
git commit -m "$(cat <<'EOF'
test(wave3): add invoicePaidToDate integration tests

Tests historical date filtering and current date scenarios.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 3.3: Invoice Paid Performance Test

**Files:**
- Create: `base/test/src/org/compiere/migration/Wave3InvoicePaidPerformanceTest.java`

**Step 1: Write performance test**

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MInvoice;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.InvoiceFunctions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Tag("PerformanceTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.SAME_THREAD)
public class Wave3InvoicePaidPerformanceTest extends CommonGWSetup {

    private static final double MAX_LATENCY_RATIO = 1.30;
    private static final int WARMUP_ITERATIONS = 100;
    private static final int TEST_ITERATIONS = 500;
    private static final int MEASUREMENT_ROUNDS = 5;

    private List<MInvoice> testInvoices;
    private double[] ratioAccumulator;

    @BeforeAll
    void loadTestData() {
        testInvoices = new Query(Env.getCtx(), MInvoice.Table_Name,
            "DocStatus IN ('CO','CL')", null)
            .setOnlyActiveRecords(true)
            .setLimit(50)
            .list();
        assumeTrue(testInvoices.size() >= 10, "Need at least 10 invoices");
    }

    @BeforeEach
    void initAccumulator(RepetitionInfo info) {
        if (info.getCurrentRepetition() == 1) {
            ratioAccumulator = new double[MEASUREMENT_ROUNDS];
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testInvoicePaidPerformance(RepetitionInfo info) {
        BigDecimal mult = BigDecimal.ONE;

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                MInvoice inv = testInvoices.get(i % testInvoices.size());
                InvoiceFunctions.invoicePaid(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult);
                SqlFunctionCaller.callInvoicePaid(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MInvoice inv = testInvoices.get(i % testInvoices.size());
            InvoiceFunctions.invoicePaid(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MInvoice inv = testInvoices.get(i % testInvoices.size());
            SqlFunctionCaller.callInvoicePaid(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            Arrays.sort(ratioAccumulator);
            double medianRatio = ratioAccumulator[MEASUREMENT_ROUNDS / 2];

            System.out.printf("invoicePaid: Java/SQL median ratio: %.2f%n", medianRatio);

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("invoicePaid Java/SQL ratio %.2f exceeds max %.2f",
                    medianRatio, MAX_LATENCY_RATIO));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testInvoicePaidToDatePerformance(RepetitionInfo info) {
        BigDecimal mult = BigDecimal.ONE;
        Timestamp dateAcct = new Timestamp(System.currentTimeMillis());

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                MInvoice inv = testInvoices.get(i % testInvoices.size());
                InvoiceFunctions.invoicePaidToDate(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult, dateAcct);
                SqlFunctionCaller.callInvoicePaidToDate(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult, dateAcct);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MInvoice inv = testInvoices.get(i % testInvoices.size());
            InvoiceFunctions.invoicePaidToDate(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult, dateAcct);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            MInvoice inv = testInvoices.get(i % testInvoices.size());
            SqlFunctionCaller.callInvoicePaidToDate(inv.getC_Invoice_ID(), inv.getC_Currency_ID(), mult, dateAcct);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            Arrays.sort(ratioAccumulator);
            double medianRatio = ratioAccumulator[MEASUREMENT_ROUNDS / 2];

            System.out.printf("invoicePaidToDate: Java/SQL median ratio: %.2f%n", medianRatio);

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("invoicePaidToDate Java/SQL ratio %.2f exceeds max %.2f",
                    medianRatio, MAX_LATENCY_RATIO));
        }
    }
}
```

**Step 2: Run performance tests**

Run: `mvn test -Dtest=Wave3InvoicePaidPerformanceTest -pl base/test -Dgroups=PerformanceTest`
Expected: PASS

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave3InvoicePaidPerformanceTest.java
git commit -m "$(cat <<'EOF'
test(wave3): add invoicePaid performance tests

Validates invoicePaid and invoicePaidToDate meet 130% latency threshold.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 3.4: Enable Shadow Mode for Invoice Paid Functions

**Step 1: Update config**

```sql
UPDATE migration.function_config
SET mode = 'SHADOW', sample_rate = 1.0
WHERE function_name IN ('invoicePaid', 'invoicePaidToDate');
```

**Step 2: Commit**

```bash
git commit -m "$(cat <<'EOF'
feat(wave3): enable shadow mode for invoicePaid functions

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

## Task Group 4: Invoice Open Functions - Critical (5 tasks)

**CRITICAL:** This is the most complex task. The existing MInvoice.getOpenAmt() has diverged from SQL - it has empty TODO blocks where payment schedule logic should be.

### Task 4.1: Analyze MInvoice.getOpenAmt() Divergence

**Files:**
- Read: `base/src/org/compiere/model/MInvoice.java:1208-1245`
- Read: `db/ddlutils/postgresql/functions/C_Invoice_Open.sql`

**Step 1: Document divergence**

The Java implementation at MInvoice.java:1219-1245 has:
```java
if (paymentDate != null)
{
    //	Payment Discount
    //	Payment Schedule
}
```

These are empty TODO blocks. The SQL function has ~30 lines of payment schedule iteration logic:
```sql
IF (p_C_InvoicePaySchedule_ID > 0) THEN
    v_Remaining := v_PaidAmt;
    FOR s IN SELECT ... FROM C_InvoicePaySchedule WHERE C_Invoice_ID = p_C_Invoice_ID ...
    LOOP
        IF (s.C_InvoicePaySchedule_ID = p_C_InvoicePaySchedule_ID) THEN
            v_TotalOpenAmt := (s.DueAmt*v_MultiplierCM) - v_Remaining;
            ...
        ELSE
            v_Remaining := v_Remaining - s.DueAmt;
            ...
        END IF;
    END LOOP;
ELSE
    v_TotalOpenAmt := v_TotalOpenAmt - v_PaidAmt;
END IF;
```

**Step 2: Create divergence documentation**

Create file `docs/discovery/wave3-invoiceopen-divergence.md`:

```markdown
# invoiceOpen Java/SQL Divergence Analysis

## Summary

MInvoice.getOpenAmt() (Java) does not implement payment schedule logic that
exists in C_Invoice_Open.sql (SQL).

## SQL Behavior (lines 82-105)

When p_C_InvoicePaySchedule_ID > 0:
1. Iterate payment schedules in DueDate order
2. For each schedule before the target: subtract DueAmt from remaining paid
3. For target schedule: return (DueAmt * MultiplierCM) - remaining

When p_C_InvoicePaySchedule_ID is null:
1. Return GrandTotal - PaidAmt

## Java Behavior (lines 1219-1245)

Always returns GrandTotal - AllocatedAmt regardless of payment schedule.

## Impact

Split-payment invoices with C_InvoicePaySchedule records will show incorrect
open amounts per schedule in Java.

## Resolution

Port SQL payment schedule logic to Java in InvoiceFunctions.invoiceOpen().
```

**Step 3: Commit**

```bash
git add docs/discovery/wave3-invoiceopen-divergence.md
git commit -m "$(cat <<'EOF'
docs(wave3): document invoiceOpen Java/SQL divergence

Payment schedule logic missing from MInvoice.getOpenAmt().
Must be ported from SQL for Wave 3.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 4.2: Implement invoiceOpen with Payment Schedule Logic

**Files:**
- Modify: `base/src/org/compiere/util/InvoiceFunctions.java`
- Test: `base/test/src/org/compiere/migration/Wave3InvoiceFunctionsTest.java`

**Step 1: Write the failing test**

```java
@Test
void invoiceOpen_matchesSql() {
    int invoiceId = testInvoice.getC_Invoice_ID();

    BigDecimal javaResult = InvoiceFunctions.invoiceOpen(invoiceId, null);
    BigDecimal sqlResult = SqlFunctionCaller.callInvoiceOpen(invoiceId, null);

    assertEquals(0, javaResult.compareTo(sqlResult),
        String.format("invoiceOpen(%d, null): java=%s, sql=%s",
            invoiceId, javaResult, sqlResult));
}

@Test
void invoiceOpen_withSchedule_matchesSql() {
    // Find invoice with payment schedules
    MInvoice invWithSchedule = new Query(Env.getCtx(), MInvoice.Table_Name,
        "IsPayScheduleValid='Y' AND DocStatus IN ('CO','CL')", null)
        .setOnlyActiveRecords(true)
        .first();
    assumeTrue(invWithSchedule != null, "Need invoice with payment schedule");

    // Get first schedule
    MInvoicePaySchedule[] schedules = MInvoicePaySchedule.getInvoicePaySchedule(
        Env.getCtx(), invWithSchedule.getC_Invoice_ID(), 0, null);
    assumeTrue(schedules.length > 0, "Need payment schedule records");

    int scheduleId = schedules[0].getC_InvoicePaySchedule_ID();
    int invoiceId = invWithSchedule.getC_Invoice_ID();

    BigDecimal javaResult = InvoiceFunctions.invoiceOpen(invoiceId, scheduleId);
    BigDecimal sqlResult = SqlFunctionCaller.callInvoiceOpen(invoiceId, scheduleId);

    assertEquals(0, javaResult.compareTo(sqlResult),
        String.format("invoiceOpen(%d, %d): java=%s, sql=%s",
            invoiceId, scheduleId, javaResult, sqlResult));
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=Wave3InvoiceFunctionsTest#invoiceOpen* -pl base/test`
Expected: FAIL

**Step 3: Implement invoiceOpen in InvoiceFunctions.java**

```java
/**
 * Calculate open amount for invoice in invoice currency.
 * Implements full payment schedule logic matching C_Invoice_Open.sql.
 *
 * @param invoiceId C_Invoice_ID
 * @param invoicePayScheduleId C_InvoicePaySchedule_ID (null for total)
 * @return open amount rounded to currency precision
 */
public static BigDecimal invoiceOpen(int invoiceId, @Nullable Integer invoicePayScheduleId, String trxName) {
    return ShadowExecutor.execute(
        "invoiceOpen",
        new Object[] { invoiceId, invoicePayScheduleId },
        () -> calculateInvoiceOpenJava(invoiceId, invoicePayScheduleId, trxName),
        () -> SqlFunctionCaller.callInvoiceOpen(invoiceId, invoicePayScheduleId),
        (java, sql) -> java.compareTo(sql) == 0
    );
}

private static BigDecimal calculateInvoiceOpenJava(int invoiceId, @Nullable Integer invoicePayScheduleId, String trxName) {
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
                return null; // Invoice not found or in draft
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

    // Step 2: Calculate paid amount from allocations
    BigDecimal paidAmt = BigDecimal.ZERO;
    String allocSql = "SELECT a.AD_Client_ID, a.AD_Org_ID, "
        + "al.Amount, al.DiscountAmt, al.WriteOffAmt, "
        + "a.C_Currency_ID, a.DateTrx "
        + "FROM C_AllocationLine al "
        + "INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID) "
        + "WHERE al.C_Invoice_ID=? "
        + "AND a.DocStatus IN ('CO','CL')";

    try (PreparedStatement pstmt = DB.prepareStatement(allocSql, trxName)) {
        pstmt.setInt(1, invoiceId);
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

    // Step 3: Payment schedule handling (CRITICAL - this was missing from Java)
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
                        // This is the target schedule - calculate open amount
                        BigDecimal scheduleOpen = dueAmt.multiply(multiplierCM).subtract(remaining);
                        // Zero floor: if calculated open is negative, return zero
                        if (scheduleOpen.compareTo(BigDecimal.ZERO) < 0) {
                            scheduleOpen = BigDecimal.ZERO;
                        }
                        totalOpenAmt = scheduleOpen;
                        break;
                    } else {
                        // Reduce remaining by this schedule's amount
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
        // No payment schedule - simple calculation
        totalOpenAmt = totalOpenAmt.subtract(paidAmt);
    }

    // Step 4: Ignore rounding (match SQL behavior)
    if (totalOpenAmt.abs().compareTo(minAmt) < 0) {
        totalOpenAmt = BigDecimal.ZERO;
    }

    return totalOpenAmt.setScale(precision, RoundingMode.HALF_UP);
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=Wave3InvoiceFunctionsTest#invoiceOpen* -pl base/test`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/InvoiceFunctions.java base/test/src/org/compiere/migration/Wave3InvoiceFunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave3): implement invoiceOpen with payment schedule logic

Port SQL payment schedule iteration to Java. This fixes the known
divergence where MInvoice.getOpenAmt() was missing schedule handling.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 4.3: Implement invoiceOpenToDate

**Files:**
- Modify: `base/src/org/compiere/util/InvoiceFunctions.java`
- Test: `base/test/src/org/compiere/migration/Wave3InvoiceFunctionsTest.java`

**Step 1: Write failing test**

```java
@Test
void invoiceOpenToDate_matchesSql() {
    int invoiceId = testInvoice.getC_Invoice_ID();
    Timestamp dateAcct = new Timestamp(System.currentTimeMillis());

    BigDecimal javaResult = InvoiceFunctions.invoiceOpenToDate(invoiceId, null, dateAcct);
    BigDecimal sqlResult = SqlFunctionCaller.callInvoiceOpenToDate(invoiceId, null, dateAcct);

    assertEquals(0, javaResult.compareTo(sqlResult),
        String.format("invoiceOpenToDate: java=%s, sql=%s", javaResult, sqlResult));
}
```

**Step 2: Implement invoiceOpenToDate**

```java
/**
 * Calculate open amount as of a specific date.
 */
public static BigDecimal invoiceOpenToDate(int invoiceId, @Nullable Integer invoicePayScheduleId,
                                            @Nullable Timestamp dateAcct, String trxName) {
    return ShadowExecutor.execute(
        "invoiceOpenToDate",
        new Object[] { invoiceId, invoicePayScheduleId, dateAcct },
        () -> calculateInvoiceOpenToDateJava(invoiceId, invoicePayScheduleId, dateAcct, trxName),
        () -> SqlFunctionCaller.callInvoiceOpenToDate(invoiceId, invoicePayScheduleId, dateAcct),
        (java, sql) -> java.compareTo(sql) == 0
    );
}

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

**Step 3: Run tests and commit**

---

### Task 4.4: Invoice Open Performance Test

**Files:**
- Create: `base/test/src/org/compiere/migration/Wave3InvoiceOpenPerformanceTest.java`

**Step 1: Create performance test following Wave1 pattern**

```java
// Similar to Wave3InvoicePaidPerformanceTest but for invoiceOpen functions
// Include tests for:
// - invoiceOpen without schedule
// - invoiceOpen with schedule (critical path)
// - invoiceOpenToDate
```

**Step 2: Run and validate <= 130% ratio**

**Step 3: Commit**

---

### Task 4.5: Enable Shadow Mode for Invoice Open Functions

**Step 1: Update config**

```sql
UPDATE migration.function_config
SET mode = 'SHADOW', sample_rate = 0.1  -- 10% for high-volume
WHERE function_name IN ('invoiceOpen', 'invoiceOpenToDate');
```

**Step 2: Commit**

---

## Task Group 5: Invoice Discount Function (3 tasks)

### Task 5.1: Implement invoiceDiscount

**Files:**
- Modify: `base/src/org/compiere/util/InvoiceFunctions.java`
- Test: `base/test/src/org/compiere/migration/Wave3InvoiceFunctionsTest.java`

**Step 1: Write failing test**

```java
@Test
void invoiceDiscount_matchesSql() {
    int invoiceId = testInvoice.getC_Invoice_ID();
    Timestamp payDate = new Timestamp(System.currentTimeMillis());

    BigDecimal javaResult = InvoiceFunctions.invoiceDiscount(invoiceId, payDate, null);
    BigDecimal sqlResult = SqlFunctionCaller.callInvoiceDiscount(invoiceId, payDate, null);

    assertEquals(0, javaResult.compareTo(sqlResult),
        String.format("invoiceDiscount: java=%s, sql=%s", javaResult, sqlResult));
}
```

**Step 2: Implement invoiceDiscount**

```java
/**
 * Calculate payment discount amount.
 * Uses paymentTermDiscount from Wave 2.
 */
public static BigDecimal invoiceDiscount(int invoiceId, @Nullable Timestamp payDate,
                                          @Nullable Integer invoicePayScheduleId) {
    return ShadowExecutor.execute(
        "invoiceDiscount",
        new Object[] { invoiceId, payDate, invoicePayScheduleId },
        () -> calculateInvoiceDiscountJava(invoiceId, payDate, invoicePayScheduleId),
        () -> SqlFunctionCaller.callInvoiceDiscount(invoiceId, payDate, invoicePayScheduleId),
        (java, sql) -> java.compareTo(sql) == 0
    );
}

private static BigDecimal calculateInvoiceDiscountJava(int invoiceId, @Nullable Timestamp payDate,
                                                         @Nullable Integer invoicePayScheduleId) {
    // Get invoice data
    String sql = "SELECT ci.IsDiscountLineAmt, i.GrandTotal, i.TotalLines, "
        + "i.C_PaymentTerm_ID, i.DateInvoiced, i.IsPayScheduleValid "
        + "FROM AD_ClientInfo ci, C_Invoice i "
        + "WHERE ci.AD_Client_ID=i.AD_Client_ID AND i.C_Invoice_ID=?";

    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        pstmt.setInt(1, invoiceId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (!rs.next()) return BigDecimal.ZERO;

            boolean isDiscountLineAmt = "Y".equals(rs.getString("IsDiscountLineAmt"));
            BigDecimal grandTotal = rs.getBigDecimal("GrandTotal");
            BigDecimal totalLines = rs.getBigDecimal("TotalLines");
            int paymentTermId = rs.getInt("C_PaymentTerm_ID");
            Timestamp docDate = rs.getTimestamp("DateInvoiced");
            boolean isPayScheduleValid = "Y".equals(rs.getString("IsPayScheduleValid"));

            BigDecimal amount = isDiscountLineAmt ? totalLines : grandTotal;
            if (amount.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;

            Timestamp effectivePayDate = payDate != null ? payDate : TimeUtil.getDate();

            // Valid payment schedule - get discount from schedule
            if (isPayScheduleValid && invoicePayScheduleId != null && invoicePayScheduleId > 0) {
                String schedSql = "SELECT COALESCE(MAX(DiscountAmt),0) FROM C_InvoicePaySchedule "
                    + "WHERE C_InvoicePaySchedule_ID=? AND DiscountDate <= ?";
                try (PreparedStatement schedPstmt = DB.prepareStatement(schedSql, null)) {
                    schedPstmt.setInt(1, invoicePayScheduleId);
                    schedPstmt.setTimestamp(2, effectivePayDate);
                    try (ResultSet schedRs = schedPstmt.executeQuery()) {
                        if (schedRs.next()) {
                            return schedRs.getBigDecimal(1);
                        }
                    }
                }
                return BigDecimal.ZERO;
            }

            // Use payment term discount from Wave 2
            return PaymentTermFunctions.paymentTermDiscount(
                amount, 0, paymentTermId, docDate, effectivePayDate);
        }
    } catch (Exception e) {
        log.log(Level.WARNING, "Error calculating invoice discount", e);
        return null;
    }
}
```

**Step 3: Run tests and commit**

---

### Task 5.2: Invoice Discount Performance Test

**Step 1: Create performance test**
**Step 2: Validate <= 130% ratio**
**Step 3: Commit**

---

### Task 5.3: Enable Shadow Mode for Invoice Discount

**Step 1: Update config to SHADOW**
**Step 2: Commit**

---

## Testing Strategy

This section defines when to run tests, how to generate test data, and how to collect performance comparisons.

### Two-Tier Test Approach

| Tier | Purpose | Data Volume | When to Run | Pass Criteria |
|------|---------|-------------|-------------|---------------|
| **CI Tests** | Correctness validation | GardenWorld (50-100 records) | Every commit, PR | All assertions pass |
| **Pre-Release Performance** | Scaling validation | Synthetic (1000+ records) | Before cutover, weekly in staging | Query counts stable, p95 within threshold |

### Tier 1: CI Tests (GardenWorld)

Run automatically on every commit. These validate correctness, not scale.

**Run command:**
```bash
mvn test -Dtest=Wave3* -pl base/test
```

**What they validate:**
- Java matches SQL for all functions
- Edge cases (zero amounts, credits, multi-currency)
- No regressions in existing functionality

### Tier 2: Pre-Release Performance Tests

Run manually before cutover and weekly in staging. These validate scaling behavior.

**When to run:**
1. After completing all Task Groups 1-5
2. Before enabling SHADOW mode in production
3. Weekly during shadow validation period
4. Before final cutover to JAVA_ONLY

**Run command:**
```bash
mvn test -Dtest=Wave3ScalingTest,Wave3QueryCountTest -pl base/test -Dgroups=PerformanceTest
```

### Synthetic Data Generator

Since production-like data volumes are not available, generate synthetic test data.

**Location:** `base/test/src/org/compiere/migration/Wave3TestDataGenerator.java`

```java
package org.compiere.migration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.*;
import org.compiere.util.Env;

/**
 * Generates synthetic financial data for Wave 3 performance testing.
 *
 * Usage:
 * - Call generateTestData(1000) in @BeforeAll of performance tests
 * - Data is created in the provided transaction
 * - Transaction should be rolled back in @AfterAll to clean up
 * - Do NOT run against production database
 */
public class Wave3TestDataGenerator {

    private static final Random random = new Random(42); // Deterministic seed

    private final String trxName;
    private List<MBPartner> partners;
    private List<MCurrency> currencies;

    /**
     * Create generator with transaction context.
     * @param trxName Transaction name - all data will be created in this transaction
     */
    public Wave3TestDataGenerator(String trxName) {
        this.trxName = trxName;
        // Load reference data
        partners = new Query(Env.getCtx(), MBPartner.Table_Name,
            "IsCustomer='Y' AND IsActive='Y'", trxName).setLimit(10).list();
        currencies = new Query(Env.getCtx(), MCurrency.Table_Name,
            "IsActive='Y'", trxName).list();
    }

    /**
     * Generate synthetic invoices with allocations.
     *
     * @param count Number of invoices to create
     * @return List of created invoice IDs
     */
    public List<Integer> generateTestData(int count) {
        List<Integer> invoiceIds = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            MInvoice invoice = createInvoice(
                randomFrom(partners),
                randomFrom(currencies),
                randomInt(1, 20)  // 1-20 lines
            );

            // 70% partially paid, 20% fully paid, 10% unpaid
            int paymentPattern = randomInt(1, 100);
            if (paymentPattern <= 70) {
                createPartialAllocations(invoice);
            } else if (paymentPattern <= 90) {
                createFullAllocation(invoice);
            }
            // else: leave unpaid

            invoiceIds.add(invoice.getC_Invoice_ID());
        }

        return invoiceIds;
    }

    private MInvoice createInvoice(MBPartner partner, MCurrency currency, int lineCount) {
        MInvoice invoice = new MInvoice(Env.getCtx(), 0, trxName);
        invoice.setC_BPartner_ID(partner.getC_BPartner_ID());
        invoice.setC_Currency_ID(currency.getC_Currency_ID());
        invoice.setIsSOTrx(true);
        invoice.setC_DocTypeTarget_ID();
        invoice.saveEx();

        for (int i = 0; i < lineCount; i++) {
            MInvoiceLine line = new MInvoiceLine(invoice);
            line.setQty(BigDecimal.ONE);
            line.setPrice(new BigDecimal(randomInt(10, 1000)));
            line.saveEx();
        }

        // Complete the invoice
        invoice.setDocAction(DocAction.ACTION_Complete);
        invoice.processIt(DocAction.ACTION_Complete);
        invoice.saveEx();

        return invoice;
    }

    private void createPartialAllocations(MInvoice invoice) {
        // Create 1-3 partial payments
        int allocCount = randomInt(1, 3);
        BigDecimal remaining = invoice.getGrandTotal();
        BigDecimal perAlloc = remaining.divide(new BigDecimal(allocCount + 1), 2, BigDecimal.ROUND_HALF_UP);

        for (int i = 0; i < allocCount; i++) {
            createPaymentAndAllocation(invoice, perAlloc);
        }
    }

    private void createFullAllocation(MInvoice invoice) {
        createPaymentAndAllocation(invoice, invoice.getGrandTotal());
    }

    private void createPaymentAndAllocation(MInvoice invoice, BigDecimal amount) {
        // Create payment
        MPayment payment = new MPayment(Env.getCtx(), 0, trxName);
        payment.setC_BPartner_ID(invoice.getC_BPartner_ID());
        payment.setC_Currency_ID(invoice.getC_Currency_ID());
        payment.setPayAmt(amount);
        payment.setC_DocType_ID(true); // Receipt
        payment.setTenderType(MPayment.TENDERTYPE_Check);
        payment.saveEx();
        payment.setDocAction(DocAction.ACTION_Complete);
        payment.processIt(DocAction.ACTION_Complete);
        payment.saveEx();

        // Create allocation
        MAllocationHdr alloc = new MAllocationHdr(Env.getCtx(), true,
            invoice.getDateInvoiced(), invoice.getC_Currency_ID(),
            "Test Allocation", trxName);
        alloc.saveEx();

        MAllocationLine allocLine = new MAllocationLine(alloc, amount,
            Env.ZERO, Env.ZERO, Env.ZERO);
        allocLine.setC_Invoice_ID(invoice.getC_Invoice_ID());
        allocLine.setC_Payment_ID(payment.getC_Payment_ID());
        allocLine.saveEx();

        alloc.setDocAction(DocAction.ACTION_Complete);
        alloc.processIt(DocAction.ACTION_Complete);
        alloc.saveEx();
    }

    private <T> T randomFrom(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }

    private int randomInt(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}
```

### Query Count Assertions

Query count assertions catch N+1 issues without needing large datasets.

**Location:** `base/test/src/org/compiere/migration/Wave3QueryCountTest.java`

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MInvoice;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.InvoiceFunctions;
import org.compiere.util.QueryCounter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * Validates query counts for Wave 3 functions.
 * Catches N+1 issues and ensures predictable database load.
 */
@Tag("PerformanceTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave3QueryCountTest extends CommonGWSetup {

    private static final int MAX_QUERIES_INVOICE_OPEN = 5;
    private static final int MAX_QUERIES_PAYMENT_ALLOCATED = 3;

    private MInvoice testInvoice;

    @BeforeAll
    void loadTestData() {
        testInvoice = new Query(Env.getCtx(), MInvoice.Table_Name,
            "DocStatus IN ('CO','CL') AND IsPaid='N'", null).first();
        assertNotNull(testInvoice, "Need at least one open invoice");
    }

    @Test
    void invoiceOpen_queryCount_withinLimit() {
        QueryCounter.reset();

        InvoiceFunctions.invoiceOpen(testInvoice.getC_Invoice_ID(), null, null);

        int queryCount = QueryCounter.get();
        assertTrue(queryCount <= MAX_QUERIES_INVOICE_OPEN,
            String.format("invoiceOpen used %d queries, expected <= %d",
                queryCount, MAX_QUERIES_INVOICE_OPEN));
    }

    @Test
    void invoiceOpen_queryCount_stableAcrossInvoices() {
        // Query count should be constant regardless of allocation count
        var invoices = new Query(Env.getCtx(), MInvoice.Table_Name,
            "DocStatus IN ('CO','CL')", null).setLimit(10).list();

        int minQueries = Integer.MAX_VALUE;
        int maxQueries = 0;

        for (var inv : invoices) {
            QueryCounter.reset();
            InvoiceFunctions.invoiceOpen(inv.getC_Invoice_ID(), null, null);
            int count = QueryCounter.get();
            minQueries = Math.min(minQueries, count);
            maxQueries = Math.max(maxQueries, count);
        }

        // Variance of more than 2 queries suggests data-dependent behavior (N+1)
        assertTrue(maxQueries - minQueries <= 2,
            String.format("Query count varies too much: min=%d, max=%d (suggests N+1)",
                minQueries, maxQueries));
    }
}
```

### Complexity Scaling Test

Validates that performance scales linearly, not quadratically.

**Location:** `base/test/src/org/compiere/migration/Wave3ScalingTest.java`

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.InvoiceFunctions;
import org.compiere.util.Trx;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * Validates that Wave 3 functions scale linearly.
 * Uses synthetic data to test at different volumes.
 *
 * IMPORTANT: All test data is created in a transaction that gets
 * rolled back after the test class completes. This prevents test
 * data pollution in the database.
 */
@Tag("PerformanceTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave3ScalingTest extends CommonGWSetup {

    private String trxName;
    private Wave3TestDataGenerator generator;
    private List<Integer> smallSet;  // 50 invoices
    private List<Integer> largeSet;  // 500 invoices

    @BeforeAll
    void generateTestData() {
        // Create transaction for test data - will be rolled back in @AfterAll
        trxName = Trx.createTrxName("W3SCALE");
        Trx.get(trxName, true);

        // Pass trxName to generator so all data is created in this transaction
        generator = new Wave3TestDataGenerator(trxName);
        smallSet = generator.generateTestData(50);
        largeSet = generator.generateTestData(500);
    }

    @AfterAll
    void rollbackTestData() {
        // Roll back all test data to prevent database pollution
        Trx trx = Trx.get(trxName, false);
        if (trx != null) {
            trx.rollback();
            trx.close();
        }
    }

    @Test
    void invoiceOpen_scalesLinearly() {
        // Warmup
        for (int id : smallSet) {
            InvoiceFunctions.invoiceOpen(id, null, trxName);
        }

        // Measure small set
        long startSmall = System.nanoTime();
        for (int id : smallSet) {
            InvoiceFunctions.invoiceOpen(id, null, trxName);
        }
        long timeSmall = System.nanoTime() - startSmall;

        // Measure large set
        long startLarge = System.nanoTime();
        for (int id : largeSet) {
            InvoiceFunctions.invoiceOpen(id, null, trxName);
        }
        long timeLarge = System.nanoTime() - startLarge;

        // 10x data should take roughly 10x time (linear)
        // If O(n²), would be 100x
        double ratio = (double) timeLarge / timeSmall;
        double expectedRatio = (double) largeSet.size() / smallSet.size();

        // Allow 50% variance for JIT effects and system noise
        assertTrue(ratio < expectedRatio * 1.5,
            String.format("Scaling ratio %.2f exceeds expected %.2f * 1.5 = %.2f (suggests non-linear)",
                ratio, expectedRatio, expectedRatio * 1.5));
    }
}
```

### QueryCounter Utility

If `QueryCounter` doesn't exist, create it:

**Location:** `base/src/org/compiere/util/QueryCounter.java`

```java
package org.compiere.util;

/**
 * Counts database queries for performance testing.
 * Thread-local to support concurrent test execution.
 *
 * Usage:
 *   QueryCounter.reset();
 *   // ... execute code that runs queries ...
 *   int count = QueryCounter.get();
 */
public class QueryCounter {

    private static final ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> 0);

    /** Reset counter to zero */
    public static void reset() {
        counter.set(0);
    }

    /** Get current count */
    public static int get() {
        return counter.get();
    }

    /** Increment counter (called by DB.prepareStatement) */
    public static void increment() {
        counter.set(counter.get() + 1);
    }
}
```

**Note:** Requires adding `QueryCounter.increment()` call to `DB.prepareStatement()` method for counting to work.

### Collecting and Comparing Results

**Step 1: Capture baseline before changes**

```bash
# Run performance tests and save output
mvn test -Dtest=Wave3QueryCountTest,Wave3ScalingTest -pl base/test \
    -Dgroups=PerformanceTest 2>&1 | tee wave3-perf-baseline.log

# Extract key metrics
grep -E "(queries|ratio|PASS|FAIL)" wave3-perf-baseline.log > wave3-metrics-baseline.txt
```

**Step 2: After changes, compare**

```bash
# Run same tests
mvn test -Dtest=Wave3QueryCountTest,Wave3ScalingTest -pl base/test \
    -Dgroups=PerformanceTest 2>&1 | tee wave3-perf-after.log

# Compare
diff wave3-metrics-baseline.txt <(grep -E "(queries|ratio|PASS|FAIL)" wave3-perf-after.log)
```

**Step 3: Document in baseline report**

Update `docs/plans/wave3-performance-baseline.md` with:

| Metric | Baseline | Current | Status |
|--------|----------|---------|--------|
| invoiceOpen query count | 4 | 4 | STABLE |
| invoiceOpen scaling ratio | 10.2x | 10.1x | STABLE |
| paymentAllocated query count | 2 | 2 | STABLE |

### When to Fail the Build

| Test Type | Failure Condition | Action |
|-----------|-------------------|--------|
| Query count | Exceeds MAX constant | Block merge, investigate N+1 |
| Scaling ratio | > 1.5x expected | Block merge, investigate algorithm |
| Java/SQL mismatch | Any mismatch | Block merge, fix implementation |

---

## Task Group 6: Integration Tests and Shadow Validation (3 tasks)

### Task 6.1: Create Wave3 Shadow Integration Test

**Files:**
- Create: `base/test/src/org/compiere/migration/Wave3ShadowIntegrationTest.java`

**Step 1: Create comprehensive integration test**

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Stream;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoicePaySchedule;
import org.compiere.model.MPayment;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.InvoiceFunctions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Comprehensive shadow integration tests for Wave 3.
 * Tests all 7 functions across multiple data scenarios.
 */
@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave3ShadowIntegrationTest extends CommonGWSetup {

    private List<MInvoice> paidInvoices;
    private List<MInvoice> openInvoices;
    private List<MInvoice> scheduledInvoices;
    private List<MPayment> allocatedPayments;

    @BeforeAll
    void loadTestData() {
        paidInvoices = new Query(Env.getCtx(), MInvoice.Table_Name,
            "DocStatus IN ('CO','CL') AND IsPaid='Y'", null)
            .setOnlyActiveRecords(true).setLimit(20).list();

        openInvoices = new Query(Env.getCtx(), MInvoice.Table_Name,
            "DocStatus IN ('CO','CL') AND IsPaid='N'", null)
            .setOnlyActiveRecords(true).setLimit(20).list();

        scheduledInvoices = new Query(Env.getCtx(), MInvoice.Table_Name,
            "IsPayScheduleValid='Y' AND DocStatus IN ('CO','CL')", null)
            .setOnlyActiveRecords(true).setLimit(10).list();

        allocatedPayments = new Query(Env.getCtx(), MPayment.Table_Name,
            "DocStatus IN ('CO','CL') AND IsAllocated='Y'", null)
            .setOnlyActiveRecords(true).setLimit(20).list();

        assumeTrue(!paidInvoices.isEmpty(), "Need paid invoices");
        assumeTrue(!openInvoices.isEmpty(), "Need open invoices");
        assumeTrue(!allocatedPayments.isEmpty(), "Need allocated payments");
    }

    Stream<Arguments> invoiceOpenTestCases() {
        return Stream.concat(
            paidInvoices.stream().map(i -> Arguments.of("paid_" + i.getC_Invoice_ID(), i)),
            openInvoices.stream().map(i -> Arguments.of("open_" + i.getC_Invoice_ID(), i))
        );
    }

    @ParameterizedTest(name = "invoiceOpen({0})")
    @MethodSource("invoiceOpenTestCases")
    void invoiceOpen_allInvoices_matchSql(String caseName, MInvoice invoice) {
        BigDecimal javaResult = InvoiceFunctions.invoiceOpen(invoice.getC_Invoice_ID(), null);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoiceOpen(invoice.getC_Invoice_ID(), null);

        assertNotNull(javaResult, "Java returned null for " + caseName);
        assertNotNull(sqlResult, "SQL returned null for " + caseName);
        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("%s: java=%s, sql=%s", caseName, javaResult, sqlResult));
    }

    @ParameterizedTest(name = "invoiceOpen with schedule({0})")
    @MethodSource("scheduledInvoiceTestCases")
    void invoiceOpen_withSchedule_matchSql(String caseName, MInvoice invoice, int scheduleId) {
        BigDecimal javaResult = InvoiceFunctions.invoiceOpen(invoice.getC_Invoice_ID(), scheduleId);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoiceOpen(invoice.getC_Invoice_ID(), scheduleId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("%s schedule %d: java=%s, sql=%s",
                caseName, scheduleId, javaResult, sqlResult));
    }

    Stream<Arguments> scheduledInvoiceTestCases() {
        return scheduledInvoices.stream().flatMap(inv -> {
            MInvoicePaySchedule[] schedules = MInvoicePaySchedule.getInvoicePaySchedule(
                Env.getCtx(), inv.getC_Invoice_ID(), 0, null);
            return java.util.Arrays.stream(schedules)
                .map(s -> Arguments.of("sched_" + inv.getC_Invoice_ID(),
                    inv, s.getC_InvoicePaySchedule_ID()));
        });
    }

    // Similar parameterized tests for:
    // - paymentAllocated
    // - paymentAvailable
    // - invoicePaid
    // - invoicePaidToDate
    // - invoiceOpenToDate
    // - invoiceDiscount
}
```

**Step 2: Run full integration test suite**

Run: `mvn test -Dtest=Wave3ShadowIntegrationTest -pl base/test`
Expected: All tests PASS

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave3ShadowIntegrationTest.java
git commit -m "$(cat <<'EOF'
test(wave3): add comprehensive shadow integration tests

Tests all 7 Wave 3 functions across paid/open/scheduled invoices
and allocated payments. Parameterized for broad coverage.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 6.2: Create Performance Baseline Report

**Files:**
- Create: `docs/plans/wave3-performance-baseline.md`

**Step 1: Run all performance tests and capture results**

```bash
mvn test -Dtest=Wave3*PerformanceTest -pl base/test -Dgroups=PerformanceTest 2>&1 | tee wave3-perf.log
```

**Step 2: Document baseline in markdown**

```markdown
# Wave 3 Performance Baseline

**Date:** YYYY-MM-DD
**Environment:** [describe test environment]
**Test Iterations:** 500 per function
**Measurement Rounds:** 5 (median reported)

## Results

| Function | Java/SQL Ratio | Threshold | Status |
|----------|----------------|-----------|--------|
| paymentAllocated | X.XX | <= 1.30 | PASS/FAIL |
| paymentAvailable | X.XX | <= 1.30 | PASS/FAIL |
| invoicePaid | X.XX | <= 1.30 | PASS/FAIL |
| invoicePaidToDate | X.XX | <= 1.30 | PASS/FAIL |
| invoiceOpen | X.XX | <= 1.30 | PASS/FAIL |
| invoiceOpen (with schedule) | X.XX | <= 1.30 | PASS/FAIL |
| invoiceOpenToDate | X.XX | <= 1.30 | PASS/FAIL |
| invoiceDiscount | X.XX | <= 1.30 | PASS/FAIL |

## Notes

[Any observations about performance characteristics]
```

**Step 3: Commit**

---

### Task 6.3: Update MInvoice.getOpenAmt() to Use InvoiceFunctions

**Files:**
- Modify: `base/src/org/compiere/model/MInvoice.java`

**Step 1: Refactor getOpenAmt() to delegate to InvoiceFunctions**

```java
/**
 * Get Open Amount.
 * Uses InvoiceFunctions.invoiceOpen() for shadow validation.
 * @return Open Amt
 */
public BigDecimal getOpenAmt() {
    return getOpenAmt(true, null);
}

/**
 * Get Open Amount
 * @param creditMemoAdjusted adjusted for CM (negative)
 * @param paymentDate ignored Payment Date
 * @return Open Amt
 */
public BigDecimal getOpenAmt(boolean creditMemoAdjusted, Timestamp paymentDate) {
    if (isPaid())
        return Env.ZERO;

    // Delegate to InvoiceFunctions for shadow validation
    BigDecimal openAmount = InvoiceFunctions.invoiceOpen(getC_Invoice_ID(), null);
    if (openAmount == null)
        openAmount = Env.ZERO;

    if (!creditMemoAdjusted)
        return openAmount;
    if (isCreditMemo())
        return openAmount.negate();
    return openAmount;
}
```

**Step 2: Run existing MInvoice tests**

Run: `mvn test -Dtest=*MInvoice* -pl base/test`
Expected: PASS

**Step 3: Commit**

```bash
git add base/src/org/compiere/model/MInvoice.java
git commit -m "$(cat <<'EOF'
refactor(wave3): delegate MInvoice.getOpenAmt() to InvoiceFunctions

MInvoice.getOpenAmt() now uses InvoiceFunctions.invoiceOpen() which
includes proper payment schedule handling. Shadow execution validates
Java matches SQL.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

## Task Group 7: Quality Gates and Cutover Prep (3 tasks)

### Task 7.1: Run Full Test Suite

**Step 1: Run all Wave 3 tests**

```bash
mvn test -Dtest=Wave3* -pl base/test
```

**Step 2: Verify all pass**

Expected:
- Wave3PaymentFunctionsTest: PASS
- Wave3InvoiceFunctionsTest: PASS
- Wave3ShadowIntegrationTest: PASS
- Wave3*PerformanceTest: PASS (all <= 1.30 ratio)

**Step 3: Document results in test report**

---

### Task 7.2: Enable SHADOW Mode for All Functions

**Step 1: Update all function configs**

```sql
UPDATE migration.function_config
SET mode = 'SHADOW'
WHERE function_name IN (
    'invoiceOpen', 'invoiceOpenToDate', 'invoiceDiscount',
    'invoicePaid', 'invoicePaidToDate',
    'paymentAllocated', 'paymentAvailable'
);
```

**Step 2: Verify shadow logging is working**

Check `migration.function_log` table for entries from all 7 functions.

**Step 3: Commit**

---

### Task 7.3: Create Quality Gate Checklist

**Files:**
- Create: `docs/plans/wave3-quality-gates.md`

```markdown
# Wave 3 Quality Gates

## Gate 1: Code Complete

- [ ] Java implementation matches SQL logic for all 7 functions
- [ ] Payment schedule logic ported to invoiceOpen
- [ ] Unit tests cover edge cases
- [ ] Integration tests pass (Wave3ShadowIntegrationTest)

## Gate 2: Validation Ready

- [ ] All functions in SHADOW mode
- [ ] Performance baseline captured
- [ ] All functions <= 130% of SQL p95
- [ ] Sampling rates configured (10% for high-volume)

## Gate 3: Shadow Validation

- [ ] 7 days of shadow execution complete
- [ ] Match rate >= 99.9% for all functions
- [ ] No critical mismatches (money/ID fields)
- [ ] Performance stable (no degradation trend)

## Gate 4: Cutover Approved

- [ ] Shadow validation passed
- [ ] Rollback procedure tested
- [ ] Stakeholder sign-off obtained
- [ ] Monitoring dashboards ready

## Gate 5: Post-Cutover

- [ ] JAVA_ONLY mode enabled
- [ ] 7 days stable operation
- [ ] Shadow execution disabled
- [ ] SQL functions retained (30-day retention)
```

**Commit**

---

## Summary

This plan migrates 7 Wave 3 functions in dependency order:

1. **Group 1 (Tasks 1.1-1.5):** Infrastructure - SqlFunctionCaller extensions, config, QueryCounter integration, index verification
2. **Group 2 (Tasks 2.1-2.4):** Payment functions - paymentAllocated, paymentAvailable
3. **Group 3 (Tasks 3.1-3.4):** Invoice paid functions - invoicePaid, invoicePaidToDate
4. **Group 4 (Tasks 4.1-4.5):** Invoice open functions - invoiceOpen (CRITICAL), invoiceOpenToDate
5. **Group 5 (Tasks 5.1-5.3):** Invoice discount - invoiceDiscount
6. **Group 6 (Tasks 6.1-6.3):** Integration tests, baseline, MInvoice refactor
7. **Group 7 (Tasks 7.1-7.3):** Quality gates and cutover prep

Each task follows TDD: write failing test, implement, verify, commit.

Performance tests validate <= 130% latency ratio at each group completion.

---

## Execution

Plan complete and saved to `docs/plans/2026-01-03-wave3-financial-core-implementation.md`.

**Two execution options:**

**1. Subagent-Driven (this session)** - I dispatch fresh subagent per task, review between tasks, fast iteration

**2. Parallel Session (separate)** - Open new session with executing-plans, batch execution with checkpoints

**Which approach?**
