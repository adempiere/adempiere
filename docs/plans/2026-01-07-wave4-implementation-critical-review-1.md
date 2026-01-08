# Critical Implementation Review: Wave 4 Implementation Plan

**Reviewed:** 2026-01-07
**Plan File:** `docs/plans/2026-01-07-wave4-implementation.md`
**Reviewer Role:** Senior Staff Software Engineer

---

## 1. Overall Assessment

The plan demonstrates a **solid understanding** of the migration framework and follows established patterns from Waves 0-2. The task decomposition is logical, the TDD approach is appropriate, and the rollback strategy is sound.

**Strengths:**
- Clear task grouping with logical dependencies
- Consistent use of existing infrastructure (ShadowExecutor, MigrationLogger, SqlFunctionCaller)
- Appropriate risk classification (High for nextID, Medium/Low for others)
- Good test coverage strategy with both unit and integration tests

**Major Concerns:**
- Plan references non-existent `DUAL_WRITE` mode - should use `SHADOW` instead (easy fix)
- Missing StringComparator class used in Wave4FunctionRouter
- nextID Java implementation has race condition vulnerabilities
- MigrationLogger.logAsync API signature mismatch in NextIDRouter
- Several SQL logic translation errors in proposed implementations

---

## 2. Critical Issues

### 2.1 DUAL_WRITE Mode References Must Use SHADOW Instead

**Description:** The plan references `MigrationMode.DUAL_WRITE` for nextID/nextIDFunc handling (Tasks 2.3, 2.4), but the `MigrationMode` enum at `base/src/org/compiere/migration/MigrationMode.java:8-12` only contains:
- `SQL_ONLY`
- `SHADOW`
- `JAVA_ONLY`

**Why it matters:** The NextIDRouter class will not compile if it references `MigrationMode.DUAL_WRITE`.

**Fix:** Since NextIDRouter has its own routing logic and doesn't use ShadowExecutor, it can interpret SHADOW mode appropriately for stateful functions:

1. Update Task 1.1 SQL config to use `'SHADOW'` instead of `'DUAL_WRITE'` for nextID/nextIDFunc
2. Update NextIDRouter to check for `MigrationMode.SHADOW` instead of `DUAL_WRITE`

The semantic difference (SHADOW = "execute both" vs "execute Java + log") is handled by the router implementation, not the enum. NextIDRouter interprets SHADOW as "execute Java, log for validation" because running both would consume extra sequence values.

```java
// NextIDRouter interprets SHADOW for stateful functions
if (config.getMode() == MigrationMode.SHADOW) {
    logExecution(adSequenceId, system, result, durationNanos, error);
}
```

**Severity reduced:** Not a build blocker - just requires updating references from DUAL_WRITE to SHADOW in the plan.

---

### 2.2 StringComparator Class Does Not Exist

**Description:** Task 1.3 (Wave4FunctionRouter) uses `StringComparator.TRIM_NULLSAFE` and `StringComparator.NULLSAFE` for getSysconfig and productAttribute comparisons, but no StringComparator class exists in the codebase.

**Grep result:** `class StringComparator` returns no files found.

**Why it matters:** Wave4FunctionRouter will not compile without this comparator.

**Fix:** Add a prerequisite task to create StringComparator:

```java
package org.compiere.migration.comparators;

import java.util.Objects;
import java.util.function.BiPredicate;

public class StringComparator implements BiPredicate<String, String> {

    public static final StringComparator NULLSAFE = new StringComparator(false);
    public static final StringComparator TRIM_NULLSAFE = new StringComparator(true);

    private final boolean trim;

    private StringComparator(boolean trim) {
        this.trim = trim;
    }

    @Override
    public boolean test(String java, String sql) {
        if (java == null && sql == null) return true;
        if (java == null || sql == null) return false;
        String a = trim ? java.trim() : java;
        String b = trim ? sql.trim() : sql;
        return Objects.equals(a, b);
    }
}
```

---

### 2.3 nextID Race Condition - Missing FOR UPDATE Locking

**Description:** The proposed `Wave4Functions.nextID()` implementation (Task 2.1, lines 608-652) performs a SELECT then UPDATE pattern, but the PostgreSQL function at `db/ddlutils/postgresql/functions/nextID.sql` does **not use FOR UPDATE** - it relies on PostgreSQL's internal behavior within a PL/pgSQL block.

The Java implementation proposes:
```java
String selectSql = "SELECT " + columnName + ", IncrementNo FROM AD_Sequence "
    + "WHERE AD_Sequence_ID = ? FOR UPDATE";
```

However, the actual SQL function does:
```sql
SELECT CurrentNextSys INTO o_NextID FROM AD_Sequence WHERE AD_Sequence_ID=p_AD_Sequence_ID;
UPDATE AD_Sequence SET CurrentNextSys = CurrentNextSys + IncrementNo WHERE AD_Sequence_ID=p_AD_Sequence_ID;
```

**Why it matters:**
1. The FOR UPDATE approach changes the locking semantics - Java will hold a row lock between SELECT and UPDATE, while SQL performs both atomically within a transaction block.
2. More critically: **if the caller does not provide a transaction (`trxName=null`)**, the FOR UPDATE lock is released immediately after the SELECT query completes (auto-commit), creating a TOCTOU race condition where two threads could read the same value before either updates.

**Fix:** Two options:

**Option A (Recommended):** Use a single atomic UPDATE...RETURNING:
```java
public static int nextID(Integer adSequenceId, String system, String trxName) {
    boolean isSystem = "Y".equalsIgnoreCase(system);
    String columnName = isSystem ? "CurrentNextSys" : "CurrentNext";

    // Atomic: read current value and increment in one statement
    String sql = "UPDATE AD_Sequence SET " + columnName + " = " + columnName + " + IncrementNo, "
        + "Updated = CURRENT_TIMESTAMP WHERE AD_Sequence_ID = ? "
        + "RETURNING " + columnName + " - IncrementNo";

    try (PreparedStatement pstmt = DB.prepareStatement(sql, trxName)) {
        pstmt.setInt(1, adSequenceId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    } catch (SQLException e) {
        log.log(Level.SEVERE, "nextID failed for sequence " + adSequenceId, e);
    }
    return -1;
}
```

**Option B:** Require a transaction context (reject null trxName) and document this behavioral change.

---

### 2.4 MigrationLogger.logAsync API Mismatch

**Description:** NextIDRouter (Task 2.3, lines 958-969) calls `MigrationLogger.logAsync()` with 8 parameters:
```java
MigrationLogger.logAsync(
    "nextID",
    new Object[]{adSequenceId, system},  // Object[]
    result,                               // int
    null,
    true,
    durationNanos,
    0L,
    error != null ? error.getMessage() : null
);
```

But the actual MigrationLogger signature (`MigrationLogger.java:64-68`) is:
```java
public static void logAsync(String functionName, String inputParams, String sqlResult,
                             String javaResult, long sqlTimeMs, long javaTimeMs,
                             boolean isMatch, String mismatchReason)
```

**Why it matters:** This will not compile. The parameter types and order are completely different.

**Fix:** Update NextIDRouter to match the actual API:
```java
MigrationLogger.logAsync(
    "nextID",
    ParamSerializer.toJson(new Object[]{adSequenceId, system}),
    null,  // sqlResult (not available in dual-write)
    String.valueOf(result),
    0L,    // sqlTimeMs
    durationNanos / 1_000_000,  // convert to ms
    true,
    error != null ? error.getMessage() : null
);
```

---

### 2.5 acctBalance Logic Mismatch - Natural Sign Handling

**Description:** The proposed acctBalance implementation (Task 3.1, lines 1119-1133) has inverted logic compared to the actual SQL:

**Plan proposes (lines 1124-1127):**
```java
if ("D".equals(accountSign)) {
    isDebit = true;
} else if ("C".equals(accountSign)) {
    isDebit = false;
} else {
    // Natural sign based on account type
    isDebit = "A".equals(accountType) || "E".equals(accountType);
}
```

**Actual SQL (`Acct_Balance.sql:17-28`):**
```sql
IF (v_AccountSign='N') THEN
    IF (v_AccountType IN ('A','E')) THEN
        v_AccountSign := 'D';   -- Natural sign for A/E becomes Debit
    ELSE
        v_AccountSign := 'C';   -- Natural sign for L/O/R becomes Credit
    END IF;
END IF;
IF (v_AccountSign = 'C') THEN   -- Credit sign means flip the balance
    v_balance := p_AmtCr - p_AmtDr;
END IF;
```

**Why it matters:** The SQL logic checks `v_AccountSign = 'C'` to flip the balance, but the Java code checks `isDebit` which inverts the condition. The final calculation uses:
- SQL: Credit → `AmtCr - AmtDr`
- Java: `!isDebit` → `cr.subtract(dr)` (same, but intermediate logic is confusing)

The logic may work but is harder to verify. More critically, the Java version handles `null` account differently - SQL defaults to `AmtDr - AmtCr` in the exception handler when account not found; Java checks `accountId <= 0` but proceeds to query for null accounts, potentially causing NPE.

**Fix:** Match SQL behavior exactly:
```java
public static BigDecimal acctBalance(Integer accountId, BigDecimal amtDr, BigDecimal amtCr) {
    BigDecimal dr = amtDr != null ? amtDr : BigDecimal.ZERO;
    BigDecimal cr = amtCr != null ? amtCr : BigDecimal.ZERO;
    BigDecimal balance = dr.subtract(cr);  // Default: Debit balance

    if (accountId == null || accountId <= 0) {
        return balance;
    }

    String sql = "SELECT AccountType, AccountSign FROM C_ElementValue WHERE C_ElementValue_ID = ?";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        pstmt.setInt(1, accountId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (!rs.next()) {
                return balance;  // Account not found
            }
            String accountType = rs.getString("AccountType");
            String accountSign = rs.getString("AccountSign");

            // Natural sign resolution (match SQL exactly)
            if ("N".equals(accountSign)) {
                if ("A".equals(accountType) || "E".equals(accountType)) {
                    accountSign = "D";
                } else {
                    accountSign = "C";
                }
            }

            // Credit balance = flip the calculation
            if ("C".equals(accountSign)) {
                balance = cr.subtract(dr);
            }
        }
    } catch (SQLException e) {
        log.log(Level.WARNING, "Error fetching account " + accountId, e);
        // Match SQL EXCEPTION behavior: return default balance
    }
    return balance;
}
```

---

### 2.6 linenetamtrealinvoiceline - Incorrect Rate Check Logic

**Description:** The proposed implementation (Task 4.1, lines 1470-1471) has:
```java
if (!isTaxIncluded || rate == null || rate.compareTo(BigDecimal.ZERO) == 0) {
    return lineNetAmt;
}
```

But the SQL function (`linenetamtrealinvoiceline.sql:7`) has:
```sql
case when pl.istaxincluded = 'Y' AND t.rate <> 0 then ...
```

**Why it matters:** The Java code returns early when `rate == 0`, but the helper method `calculateTaxExclusiveAmount` would correctly return `lineNetAmt` in that case anyway (divide by 1). The real issue is that the SQL uses `<>` (not equal) which in PostgreSQL is equivalent to `IS DISTINCT FROM` for NULL handling in some contexts. The Java code's `rate == null` check diverges from SQL behavior.

**Fix:** Minor - but should also handle the case where the query returns no rows:
```java
public static BigDecimal linenetamtrealinvoiceline(Integer invoiceLineId) {
    if (invoiceLineId == null || invoiceLineId <= 0) {
        return BigDecimal.ZERO;
    }
    // ... existing query ...
    // Handle no row found:
    return BigDecimal.ZERO;  // COALESCE(v_amt, 0) in SQL
}
```

The implementation already does this correctly via the method's structure.

---

### 2.7 getSysconfig - LIMIT 1 Portability and Null Org Handling

**Description:** The proposed getSysconfig implementation (Task 3.2, lines 1212-1219) uses:
```sql
SELECT Value FROM AD_SysConfig
WHERE Name = ? AND AD_Client_ID IN (0, ?) AND AD_Org_ID IN (0, ?) AND IsActive = 'Y'
ORDER BY AD_Client_ID DESC, AD_Org_ID DESC
LIMIT 1
```

**Why it matters:**
1. `LIMIT 1` is PostgreSQL-specific. If ADempiere's DB abstraction aims for Oracle compatibility, this should use `DB.getDatabase().getLimitString()` or similar.
2. The precedence logic is correct (highest client/org wins), but the plan doesn't verify this matches the actual SQL function.

**Fix:** Verify against actual `get_Sysconfig.sql` (not found in codebase during review - may need to locate it). Consider using DB abstraction for limit clause if multi-database support is needed.

---

## 3. Minor Issues & Improvements

### 3.1 Test Method Naming

The tests use reflection to verify method signatures exist (e.g., `callAcctBalance_methodExists`). While functional, this provides weak guarantees - a simple interface check or mock compilation would be more robust.

**Suggestion:** Replace signature tests with actual invocation tests using mocks or test doubles.

### 3.2 DRY Violation in SqlFunctionCaller Methods

Task 1.2 proposes 7 new methods with repetitive try-catch-finally patterns. The existing SqlFunctionCaller already has utility methods (`setNullableInt`, `setNullableBigDecimal`, etc.) but doesn't have a generic query executor.

**Suggestion:** Consider a template method pattern:
```java
private static <T> T executeSqlFunction(String functionName, String sql,
        PreparedStatementSetter setter, ResultSetExtractor<T> extractor) {
    // Common exception handling
}
```

### 3.3 productAttribute Unicode Characters

The implementation uses hard-coded Unicode characters (lines 1678-1679):
```java
String lotStart = "\u00AB"; // <<
String lotEnd = "\u00BB";   // >>
```

These should ideally be constants or match the actual SQL function's behavior (needs verification).

### 3.4 documentNo Complex Query

The documentNo implementation (Task 5.2, lines 1836-1846) uses a single complex query with multiple LEFT JOINs where only one will match based on OrderType. This is functionally correct but potentially inefficient.

**Suggestion:** Consider a simpler approach - query PP_MRP first for OrderType, then query the appropriate table:
```java
String orderType = getOrderType(ppMrpId);
switch (orderType) {
    case "FTC": return getForecastName(ppMrpId);
    case "POO": return getPODocumentNo(ppMrpId);
    // ...
}
```

However, this increases round-trips. Keep the original if performance is acceptable.

### 3.5 Wave4FunctionRouter Should Not Be Final

The Wave4FunctionRouter class uses a private constructor with static methods only. This is fine, but consider making the class `final` to prevent subclassing:
```java
public final class Wave4FunctionRouter {
```

### 3.6 Configuration SQL Initial Values Inconsistency

Task 1.1 sets `nextID` and `nextIDFunc` to `DUAL_WRITE` with `circuit_breaker_enabled = false`, but other functions get `circuit_breaker_enabled = true`. This is intentional per the design (sequence functions can't use circuit breaker), but should be documented in the SQL file with a comment.

---

## 4. Questions for Clarification

1. **MSysConfig Existing Implementation (Task 3.4):** The plan states "MSysConfig.getValue() already implements equivalent logic with caching" and suggests no routing is needed. Has this been verified? The plan should either:
   - Confirm MSysConfig matches get_Sysconfig.sql exactly, or
   - Identify any behavioral differences (e.g., caching semantics, tenant isolation)

2. **View Dependencies (Task 5.4):** The plan defers view dependency discovery to implementation time. Should views be inventoried upfront to size the effort correctly? The design document mentions `productAttribute` and `documentNo` have view dependencies.

3. **nextID Transaction Context:** The plan assumes `trxName` can be null. Is this the actual caller behavior? If callers always provide transactions, the race condition concern is mitigated.

4. **Test Sequence ID (999999):** NextIDConcurrencyTest uses a hardcoded test sequence ID. How will this be created/cleaned in CI environments?

5. **Replay Validation (Dual-Write):** Section 2.3 mentions "Background replay job" for dual-write validation, but no task creates this job. Is it out of scope for this plan?

---

## 5. Final Recommendation

**Major revisions needed.**

The plan cannot be executed as-is due to:

1. **Missing infrastructure:** StringComparator class must be created first
2. **Correctness issues:** nextID race condition, acctBalance logic clarity, MigrationLogger API mismatch
3. **Compilation blockers:** API mismatches and missing classes will prevent the code from building
4. **Easy fixes:** DUAL_WRITE references should be changed to SHADOW throughout the plan

**Required changes before proceeding:**

| Priority | Change | Tasks Affected |
|----------|--------|----------------|
| P0 | Create StringComparator class | 1.3 |
| P0 | Fix MigrationLogger.logAsync calls in NextIDRouter | 2.3 |
| P0 | Use atomic UPDATE...RETURNING for nextID | 2.1 |
| P0 | Replace DUAL_WRITE references with SHADOW in plan | 1.1, 2.3, 2.4 |
| P1 | Align acctBalance logic with SQL exactly | 3.1 |
| P2 | Verify getSysconfig matches SQL function | 3.2 |
| P2 | Add missing "Task 0: Prerequisites" for StringComparator | 1.3 |

**Suggested plan restructure:**
1. Insert "Task 0: Create StringComparator" before Task Group 1
2. Update Task 1.1 SQL to use 'SHADOW' instead of 'DUAL_WRITE' for nextID/nextIDFunc
3. Update NextIDRouter to check for SHADOW instead of DUAL_WRITE
4. Update all affected code snippets

---

*Review completed: 2026-01-07*
*Reviewer: Claude (Senior Staff Software Engineer)*
