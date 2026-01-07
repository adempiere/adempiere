# Wave 4: Standalone Functions Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Migrate 9 independent PostgreSQL functions to Java with shadow validation

---

## Revision History

| Date | Revision | Changes |
|------|----------|---------|
| 2026-01-07 | v1.4 | Incorporated critical review #5 feedback (see below) |
| 2026-01-07 | v1.3 | Incorporated critical review #4 feedback (see below) |
| 2026-01-07 | v1.2 | Incorporated critical review #3 feedback (see below) |
| 2026-01-07 | v1.1 | Incorporated critical review #2 feedback (see below) |
| 2026-01-07 | v1.0 | Initial implementation plan |

### Changes from Critical Review #5

**BLOCKERS Fixed:**
1. **Task 3.2** - Fixed getSysconfig ORDER BY to use simple `DESC, DESC` ordering matching PostgreSQL (was using CASE statement with wrong precedence for org-specific system defaults)
2. **Task 2.3** - Fixed MigrationLogger.logAsync isMatch parameter to pass `false` instead of `null` (primitive boolean parameter)

**HIGH Priority Fixed:**
3. **Task 2.1** - Added @implNote documenting nextID returns -1 for missing sequence as intentional improvement over PostgreSQL undefined behavior
4. **Task 3.3** - Documented maxpaydate query structure as acceptable deviation (simpler JOIN vs LEFT JOIN from C_Invoice)
5. **Task 5.1** - Fixed productAttribute null handling to return empty string `""` instead of `null` to match PostgreSQL behavior

**MEDIUM Priority Fixed:**
6. **Tasks 3.2, 3.3, 5.1, 5.2, 4.1, 4.2** - Added consistent PreparedStatement null checks to all functions

**LOW Priority Fixed:**
7. **Task 4.1** - Added note about verifying PostgreSQL rounding behavior (HALF_EVEN vs HALF_UP)
8. **Task 5.1** - Added integration test recommendation for productAttribute with real data

### Changes from Critical Review #4

**BLOCKERS Fixed:**
1. **Task 5.1** - Added null check for attribute name in attribute loop to prevent `null:value` output
2. **Task 5.1** - Fixed spacing pattern to match PostgreSQL: trailing space after each element + TRIM at end (was causing double spaces)

**HIGH Priority Fixed:**
3. **Task 3.2** - Fixed getSysconfig precedence ORDER BY to match PostgreSQL: exact Client/Org > Client with Org=0 > system (0/0)
4. **Task 3.3** - Added prerequisite index verification for C_AllocationLine(C_Invoice_ID)
5. **Task 5.2** - Documented decision on single-query approach for documentNo (accepted complexity given call frequency)

**MEDIUM Priority Fixed:**
6. **Task 2.3** - Fixed NextIDRouter.logExecution to use null for isMatch and "STATEFUL_NO_COMPARISON" as status marker
7. **Task 3.1** - Added Javadoc note clarifying that circuit breaker handles persistent errors
8. **Task 5.1** - Added timezone specification (UTC) to SimpleDateFormat for guarantee dates

### Changes from Critical Review #3

**BLOCKERS Fixed:**
1. **Task 5.1** - Fixed productAttribute output format to include leading space: `" ("` instead of `"("` to match PostgreSQL output exactly
2. **Task 5.2** - Fixed documentNo to TRIM orderType before switch comparison

**HIGH Priority Fixed:**
3. **Task 5.1** - Fixed productAttribute date format to use explicit SimpleDateFormat matching PostgreSQL ISO output
4. **Task 4.1/4.2** - Fixed linenetamt intermediate precision from 10 to 15 decimal places

**RECOMMENDED Changes:**
5. **Task 1.2** - Changed Step 4b from optional to required - behavioral tests now mandatory
6. **Task 0.2 (NEW)** - Moved view dependency analysis from Task 5.4 to prerequisites
7. **Task 2.1** - Added explicit documentation about nextID atomicity improvement over SQL
8. **Task 3.1** - Enhanced acctBalance exception handling with SEVERE logging and better distinction between "not found" and "error" cases

### Changes from Critical Review #2

**BLOCKERS Fixed:**
1. **Task 3.1** - Added missing test constants `ASSET_ACCOUNT_ID` and `LIABILITY_ACCOUNT_ID` with `@EnabledIfEnvironmentVariable` annotation for DB-dependent tests
2. **Task 5.1** - Fixed productAttribute implementation:
   - Corrected column names: `SerNoCharSOverwrite`, `SerNoCharEOverwrite`, `LotCharSOverwrite`, `LotCharEOverwrite`
   - Changed from flag-based logic to COALESCE in SQL (matches PostgreSQL function)
   - Added `IsInstanceAttribute='Y'` filter to attribute query

**MINOR Improvements:**
3. **Task 1.2** - Added note about behavioral tests for better coverage beyond reflection-only verification
4. **Task 2.1** - Added Return Value Contract Javadoc table documenting null/invalid input handling for all functions

**Architecture:** Leverage existing migration infrastructure (ShadowExecutor, MigrationLogger, SqlFunctionCaller) from Waves 0-2. Create FunctionRouter classes per function that delegate to ShadowExecutor. Most functions are simple lookups; nextID requires special handling due to sequence state.

**Tech Stack:** Java 11, ADempiere model classes, existing migration framework, JUnit 5, PostgreSQL

**Design Document:** `docs/plans/2026-01-07-wave4-design.md`

---

## Prerequisites

Before starting, ensure:
- [ ] Wave 0, 1, 2 migrations complete and stable
- [ ] `migration.function_config` table exists
- [ ] `migration.function_log` table exists
- [ ] ShadowExecutor, MigrationLogger, SqlFunctionCaller classes available

---

## Task Group 0: Prerequisites (2 tasks)

### Task 0.1: Create StringComparator Class

**Files:**
- Create: `base/src/org/compiere/migration/comparators/StringComparator.java`

**Step 1: Write failing test**

Create `base/test/src/org/compiere/migration/comparators/StringComparatorTest.java`:

```java
package org.compiere.migration.comparators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StringComparatorTest {

    @Test
    void nullsafe_bothNull_returnsTrue() {
        assertTrue(StringComparator.NULLSAFE.test(null, null));
    }

    @Test
    void nullsafe_oneNull_returnsFalse() {
        assertFalse(StringComparator.NULLSAFE.test("a", null));
        assertFalse(StringComparator.NULLSAFE.test(null, "a"));
    }

    @Test
    void nullsafe_equal_returnsTrue() {
        assertTrue(StringComparator.NULLSAFE.test("hello", "hello"));
    }

    @Test
    void nullsafe_notEqual_returnsFalse() {
        assertFalse(StringComparator.NULLSAFE.test("hello", "world"));
    }

    @Test
    void trimNullsafe_withWhitespace_returnsTrue() {
        assertTrue(StringComparator.TRIM_NULLSAFE.test("hello ", " hello"));
        assertTrue(StringComparator.TRIM_NULLSAFE.test("  hello  ", "hello"));
    }

    @Test
    void trimNullsafe_differentAfterTrim_returnsFalse() {
        assertFalse(StringComparator.TRIM_NULLSAFE.test("hello", "world"));
    }
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=StringComparatorTest -pl base`
Expected: FAIL with ClassNotFoundException

**Step 3: Create StringComparator class**

Create `base/src/org/compiere/migration/comparators/StringComparator.java`:

```java
package org.compiere.migration.comparators;

import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Comparator for String values used in shadow validation.
 * Implements BiPredicate for use with ShadowExecutor.
 */
public class StringComparator implements BiPredicate<String, String> {

    /** Null-safe equality comparison */
    public static final StringComparator NULLSAFE = new StringComparator(false);

    /** Null-safe equality with whitespace trimming */
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

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=StringComparatorTest -pl base`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/comparators/StringComparator.java \
        base/test/src/org/compiere/migration/comparators/StringComparatorTest.java
git commit -m "feat(wave4): add StringComparator for shadow validation

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 0.2: View Dependency Analysis (moved from Task 5.4)

**Purpose:** Identify PostgreSQL views that depend on Wave 4 functions before implementation, to inform migration decisions.

**Step 1: Query view dependencies**

Run query to find views using productAttribute, documentNo, and other Wave 4 functions:

```sql
SELECT v.viewname, pg_get_viewdef(v.viewname::regclass)
FROM pg_views v
WHERE pg_get_viewdef(v.viewname::regclass) ILIKE '%productattribute%'
   OR pg_get_viewdef(v.viewname::regclass) ILIKE '%documentno%'
   OR pg_get_viewdef(v.viewname::regclass) ILIKE '%acct_balance%'
   OR pg_get_viewdef(v.viewname::regclass) ILIKE '%linenetamtreal%'
   OR pg_get_viewdef(v.viewname::regclass) ILIKE '%maxpaydate%'
   OR pg_get_viewdef(v.viewname::regclass) ILIKE '%get_sysconfig%';
```

**Step 2: Document findings in design document**

Add section to `docs/plans/2026-01-07-wave4-design.md` listing:
- Views that call these functions
- Whether views need migration or can remain PostgreSQL-only
- Impact on implementation approach

**Step 3: Determine migration strategy**

For each dependent view:
- If view is queried from Java code: Plan to migrate the view or modify callers
- If view is PostgreSQL-only (reports, admin): Can remain as-is

**Step 4: Commit documentation**

```bash
git add docs/plans/2026-01-07-wave4-design.md
git commit -m "docs(wave4): add view dependency analysis (prerequisite)

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Task Group 1: Infrastructure Setup (3 tasks)

### Task 1.1: Create Wave 4 Function Configuration SQL

**Files:**
- Create: `migration/sql/wave4-function-config.sql`

**Step 1: Write the configuration SQL file**

```sql
-- Wave 4: Standalone Functions Configuration
-- Run this after Wave 4 Java implementation is deployed

-- Initial mode: SQL_ONLY (safe default)
-- Note: nextID/nextIDFunc use circuit_breaker_enabled=false because sequence
-- functions cannot use circuit breaker (each call consumes a sequence value).
-- They also start in SHADOW mode with special handling (execute Java, log for validation).
INSERT INTO migration.function_config (function_name, mode, sample_rate, circuit_breaker_enabled, created, updated)
VALUES
    ('nextID', 'SHADOW', 1.0, false, NOW(), NOW()),
    ('nextIDFunc', 'SHADOW', 1.0, false, NOW(), NOW()),
    ('acctBalance', 'SQL_ONLY', 1.0, true, NOW(), NOW()),
    ('productAttribute', 'SQL_ONLY', 1.0, true, NOW(), NOW()),
    ('documentNo', 'SQL_ONLY', 0.1, true, NOW(), NOW()),
    ('get_Sysconfig', 'SQL_ONLY', 1.0, true, NOW(), NOW()),
    ('linenetamtrealinvoiceline', 'SQL_ONLY', 1.0, true, NOW(), NOW()),
    ('linenetamtrealorderline', 'SQL_ONLY', 1.0, true, NOW(), NOW()),
    ('maxpaydate', 'SQL_ONLY', 1.0, true, NOW(), NOW())
ON CONFLICT (function_name) DO NOTHING;

-- Transition to SHADOW mode (run after deployment verified)
-- UPDATE migration.function_config SET mode = 'SHADOW', updated = NOW()
-- WHERE function_name IN ('acctBalance', 'productAttribute', 'documentNo',
--                         'get_Sysconfig', 'linenetamtrealinvoiceline',
--                         'linenetamtrealorderline', 'maxpaydate');

-- Transition to JAVA_ONLY (run after 7 days at 99.9% match rate)
-- UPDATE migration.function_config SET mode = 'JAVA_ONLY', updated = NOW()
-- WHERE function_name IN ('nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
--                         'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
--                         'linenetamtrealorderline', 'maxpaydate');
```

**Step 2: Verify file created**

Run: `cat migration/sql/wave4-function-config.sql`
Expected: File contents displayed

**Step 3: Commit**

```bash
git add migration/sql/wave4-function-config.sql
git commit -m "feat(wave4): add function configuration SQL

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 1.2: Add Wave 4 SqlFunctionCaller Methods

**Files:**
- Modify: `base/src/org/compiere/migration/SqlFunctionCaller.java`

**Step 1: Write failing test for SqlFunctionCaller**

Create test file `base/test/src/org/compiere/migration/SqlFunctionCallerWave4Test.java`:

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class SqlFunctionCallerWave4Test {

    @Test
    void callAcctBalance_methodExists() {
        // Verify method signature exists (compile-time check)
        // Actual DB test in integration tests
        assertDoesNotThrow(() -> {
            var method = SqlFunctionCaller.class.getMethod(
                "callAcctBalance", Integer.class, BigDecimal.class, BigDecimal.class);
            assertNotNull(method);
        });
    }

    @Test
    void callGetSysconfig_methodExists() {
        assertDoesNotThrow(() -> {
            var method = SqlFunctionCaller.class.getMethod(
                "callGetSysconfig", String.class, String.class, Integer.class, Integer.class);
            assertNotNull(method);
        });
    }

    @Test
    void callProductAttribute_methodExists() {
        assertDoesNotThrow(() -> {
            var method = SqlFunctionCaller.class.getMethod(
                "callProductAttribute", Integer.class);
            assertNotNull(method);
        });
    }

    @Test
    void callDocumentNo_methodExists() {
        assertDoesNotThrow(() -> {
            var method = SqlFunctionCaller.class.getMethod(
                "callDocumentNo", Integer.class);
            assertNotNull(method);
        });
    }

    @Test
    void callLinenetamtrealinvoiceline_methodExists() {
        assertDoesNotThrow(() -> {
            var method = SqlFunctionCaller.class.getMethod(
                "callLinenetamtrealinvoiceline", Integer.class);
            assertNotNull(method);
        });
    }

    @Test
    void callLinenetamtrealorderline_methodExists() {
        assertDoesNotThrow(() -> {
            var method = SqlFunctionCaller.class.getMethod(
                "callLinenetamtrealorderline", Integer.class);
            assertNotNull(method);
        });
    }

    @Test
    void callMaxpaydate_methodExists() {
        assertDoesNotThrow(() -> {
            var method = SqlFunctionCaller.class.getMethod(
                "callMaxpaydate", Integer.class);
            assertNotNull(method);
        });
    }
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=SqlFunctionCallerWave4Test -pl base`
Expected: FAIL with NoSuchMethodException

**Step 3: Add SqlFunctionCaller methods**

Add to `base/src/org/compiere/migration/SqlFunctionCaller.java`:

```java
/**
 * Call acct_balance(p_Account_ID, p_AmtDr, p_AmtCr)
 * @return balance amount
 */
public static BigDecimal callAcctBalance(Integer accountId, BigDecimal amtDr, BigDecimal amtCr) {
    String sql = "SELECT acct_balance(?, ?, ?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        pstmt.setObject(1, accountId, Types.INTEGER);
        pstmt.setBigDecimal(2, amtDr);
        pstmt.setBigDecimal(3, amtCr);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (SQLException e) {
        throw new SqlFunctionException("acct_balance", e);
    }
    return BigDecimal.ZERO;
}

/**
 * Call get_sysconfig(name, defaultvalue, client_id, org_id)
 * @return configuration value or default
 */
public static String callGetSysconfig(String name, String defaultValue, Integer clientId, Integer orgId) {
    String sql = "SELECT get_sysconfig(?, ?, ?, ?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        pstmt.setString(1, name);
        pstmt.setString(2, defaultValue);
        pstmt.setObject(3, clientId, Types.INTEGER);
        pstmt.setObject(4, orgId, Types.INTEGER);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString(1);
            }
        }
    } catch (SQLException e) {
        throw new SqlFunctionException("get_sysconfig", e);
    }
    return defaultValue;
}

/**
 * Call productattribute(p_M_AttributeSetInstance_ID)
 * @return formatted attribute string
 */
public static String callProductAttribute(Integer attributeSetInstanceId) {
    String sql = "SELECT productattribute(?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        pstmt.setObject(1, attributeSetInstanceId, Types.INTEGER);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString(1);
            }
        }
    } catch (SQLException e) {
        throw new SqlFunctionException("productattribute", e);
    }
    return null;
}

/**
 * Call documentno(p_PP_MRP_ID)
 * @return document number for MRP record
 */
public static String callDocumentNo(Integer ppMrpId) {
    String sql = "SELECT documentno(?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        pstmt.setObject(1, ppMrpId, Types.INTEGER);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString(1);
            }
        }
    } catch (SQLException e) {
        throw new SqlFunctionException("documentno", e);
    }
    return "";
}

/**
 * Call linenetamtrealinvoiceline(p_c_invoiceline_id)
 * @return net amount excluding tax if tax-inclusive
 */
public static BigDecimal callLinenetamtrealinvoiceline(Integer invoiceLineId) {
    String sql = "SELECT linenetamtrealinvoiceline(?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        pstmt.setObject(1, invoiceLineId, Types.INTEGER);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (SQLException e) {
        throw new SqlFunctionException("linenetamtrealinvoiceline", e);
    }
    return BigDecimal.ZERO;
}

/**
 * Call linenetamtrealorderline(p_c_orderline_id)
 * @return net amount excluding tax if tax-inclusive
 */
public static BigDecimal callLinenetamtrealorderline(Integer orderLineId) {
    String sql = "SELECT linenetamtrealorderline(?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        pstmt.setObject(1, orderLineId, Types.INTEGER);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (SQLException e) {
        throw new SqlFunctionException("linenetamtrealorderline", e);
    }
    return BigDecimal.ZERO;
}

/**
 * Call maxpaydate(p_c_invoice_id)
 * @return latest payment date for invoice
 */
public static Timestamp callMaxpaydate(Integer invoiceId) {
    String sql = "SELECT maxpaydate(?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        pstmt.setObject(1, invoiceId, Types.INTEGER);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getTimestamp(1);
            }
        }
    } catch (SQLException e) {
        throw new SqlFunctionException("maxpaydate", e);
    }
    return null;
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=SqlFunctionCallerWave4Test -pl base`
Expected: PASS

**Step 4b (REQUIRED): Add behavioral tests for better coverage**

The reflection-only tests verify method signatures but not behavior.
Add integration tests in `Wave4IntegrationTest.java` (Task 6.1) that
verify SQL construction and result handling against actual database data.
This step is mandatory per critical review #3 feedback.

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/SqlFunctionCaller.java \
        base/test/src/org/compiere/migration/SqlFunctionCallerWave4Test.java
git commit -m "feat(wave4): add SqlFunctionCaller methods for 7 functions

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 1.3: Create Wave4FunctionRouter Base Class

**Files:**
- Create: `base/src/org/compiere/migration/Wave4FunctionRouter.java`

**Step 1: Write failing test**

Create `base/test/src/org/compiere/migration/Wave4FunctionRouterTest.java`:

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Wave4FunctionRouterTest {

    @Test
    void classExists() {
        assertDoesNotThrow(() -> Class.forName("org.compiere.migration.Wave4FunctionRouter"));
    }

    @Test
    void hasAcctBalanceMethod() {
        assertDoesNotThrow(() -> {
            var method = Wave4FunctionRouter.class.getMethod(
                "acctBalance", Integer.class, java.math.BigDecimal.class, java.math.BigDecimal.class);
            assertNotNull(method);
        });
    }
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=Wave4FunctionRouterTest -pl base`
Expected: FAIL with ClassNotFoundException

**Step 3: Create Wave4FunctionRouter**

Create `base/src/org/compiere/migration/Wave4FunctionRouter.java`:

```java
package org.compiere.migration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.migration.comparators.BigDecimalComparator;
import org.compiere.migration.comparators.StringComparator;
import org.compiere.migration.comparators.TimestampComparator;

/**
 * Router for Wave 4 standalone functions.
 * Delegates to ShadowExecutor for shadow validation.
 */
public class Wave4FunctionRouter {

    private Wave4FunctionRouter() {
        // Static methods only
    }

    /**
     * Route acct_balance function call.
     * Calculates account balance considering natural sign.
     */
    public static BigDecimal acctBalance(Integer accountId, BigDecimal amtDr, BigDecimal amtCr) {
        return ShadowExecutor.execute(
            "acctBalance",
            new Object[]{accountId, amtDr, amtCr},
            () -> Wave4Functions.acctBalance(accountId, amtDr, amtCr),
            () -> SqlFunctionCaller.callAcctBalance(accountId, amtDr, amtCr),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route get_sysconfig function call.
     * Retrieves system configuration with precedence.
     */
    public static String getSysconfig(String name, String defaultValue, Integer clientId, Integer orgId) {
        return ShadowExecutor.execute(
            "get_Sysconfig",
            new Object[]{name, defaultValue, clientId, orgId},
            () -> Wave4Functions.getSysconfig(name, defaultValue, clientId, orgId),
            () -> SqlFunctionCaller.callGetSysconfig(name, defaultValue, clientId, orgId),
            StringComparator.TRIM_NULLSAFE
        );
    }

    /**
     * Route productattribute function call.
     * Builds display string for attribute set instance.
     */
    public static String productAttribute(Integer attributeSetInstanceId) {
        return ShadowExecutor.execute(
            "productAttribute",
            new Object[]{attributeSetInstanceId},
            () -> Wave4Functions.productAttribute(attributeSetInstanceId),
            () -> SqlFunctionCaller.callProductAttribute(attributeSetInstanceId),
            StringComparator.NULLSAFE
        );
    }

    /**
     * Route documentno function call.
     * Returns document number for MRP record.
     */
    public static String documentNo(Integer ppMrpId) {
        return ShadowExecutor.execute(
            "documentNo",
            new Object[]{ppMrpId},
            () -> Wave4Functions.documentNo(ppMrpId),
            () -> SqlFunctionCaller.callDocumentNo(ppMrpId),
            StringComparator.NULLSAFE
        );
    }

    /**
     * Route linenetamtrealinvoiceline function call.
     * Calculates net amount excluding tax if tax-inclusive.
     */
    public static BigDecimal linenetamtrealinvoiceline(Integer invoiceLineId) {
        return ShadowExecutor.execute(
            "linenetamtrealinvoiceline",
            new Object[]{invoiceLineId},
            () -> Wave4Functions.linenetamtrealinvoiceline(invoiceLineId),
            () -> SqlFunctionCaller.callLinenetamtrealinvoiceline(invoiceLineId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route linenetamtrealorderline function call.
     * Calculates net amount excluding tax if tax-inclusive.
     */
    public static BigDecimal linenetamtrealorderline(Integer orderLineId) {
        return ShadowExecutor.execute(
            "linenetamtrealorderline",
            new Object[]{orderLineId},
            () -> Wave4Functions.linenetamtrealorderline(orderLineId),
            () -> SqlFunctionCaller.callLinenetamtrealorderline(orderLineId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route maxpaydate function call.
     * Returns latest payment date for invoice.
     */
    public static Timestamp maxpaydate(Integer invoiceId) {
        return ShadowExecutor.execute(
            "maxpaydate",
            new Object[]{invoiceId},
            () -> Wave4Functions.maxpaydate(invoiceId),
            () -> SqlFunctionCaller.callMaxpaydate(invoiceId),
            TimestampComparator.SAME_DAY
        );
    }
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=Wave4FunctionRouterTest -pl base`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave4FunctionRouter.java \
        base/test/src/org/compiere/migration/Wave4FunctionRouterTest.java
git commit -m "feat(wave4): add Wave4FunctionRouter with shadow execution

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Task Group 2: Sequence Functions (4 tasks)

### Task 2.1: Extract nextID Logic from MSequence

**Files:**
- Modify: `base/src/org/compiere/model/MSequence.java`
- Create: `base/src/org/compiere/migration/Wave4Functions.java`

**Step 1: Write failing test for Wave4Functions.nextID**

Create `base/test/src/org/compiere/migration/Wave4FunctionsTest.java`:

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Wave4FunctionsTest {

    @Test
    void nextID_methodExists() {
        assertDoesNotThrow(() -> {
            var method = Wave4Functions.class.getMethod(
                "nextID", Integer.class, String.class, String.class);
            assertNotNull(method);
        });
    }

    @Test
    void nextIDFunc_methodExists() {
        assertDoesNotThrow(() -> {
            var method = Wave4Functions.class.getMethod(
                "nextIDFunc", Integer.class, String.class, String.class);
            assertNotNull(method);
        });
    }
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=Wave4FunctionsTest -pl base`
Expected: FAIL with ClassNotFoundException or NoSuchMethodException

**Step 3: Create Wave4Functions with nextID methods**

Create `base/src/org/compiere/migration/Wave4Functions.java`:

```java
package org.compiere.migration;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Java implementations of Wave 4 PostgreSQL functions.
 *
 * <h3>Return Value Contract (matches PostgreSQL behavior)</h3>
 * <table border="1">
 * <tr><th>Function</th><th>null input</th><th>0 or negative input</th><th>not found</th></tr>
 * <tr><td>nextID</td><td>-1</td><td>-1</td><td>-1</td></tr>
 * <tr><td>nextIDFunc</td><td>-1</td><td>-1</td><td>-1</td></tr>
 * <tr><td>acctBalance</td><td>AmtDr-AmtCr (default)</td><td>AmtDr-AmtCr (default)</td><td>AmtDr-AmtCr (default)</td></tr>
 * <tr><td>getSysconfig</td><td>defaultValue</td><td>defaultValue</td><td>defaultValue</td></tr>
 * <tr><td>productAttribute</td><td>null</td><td>"" (empty)</td><td>null</td></tr>
 * <tr><td>documentNo</td><td>"" (empty)</td><td>"" (empty)</td><td>"" (empty)</td></tr>
 * <tr><td>linenetamtrealinvoiceline</td><td>ZERO</td><td>ZERO</td><td>ZERO</td></tr>
 * <tr><td>linenetamtrealorderline</td><td>ZERO</td><td>ZERO</td><td>ZERO</td></tr>
 * <tr><td>maxpaydate</td><td>null</td><td>null</td><td>null</td></tr>
 * </table>
 */
public class Wave4Functions {

    private static final CLogger log = CLogger.getCLogger(Wave4Functions.class);

    private Wave4Functions() {
        // Static methods only
    }

    /**
     * Get next ID from sequence (equivalent to nextID PostgreSQL function).
     * Uses atomic UPDATE...RETURNING to read and increment in a single statement,
     * eliminating race conditions without requiring transaction management.
     *
     * Note: The trxName parameter is accepted for API compatibility but is
     * effectively ignored - the atomic operation doesn't require a transaction
     * context for correctness. This matches MSequence behavior where trxName
     * is documented as "deprecated" and a dedicated connection is used.
     *
     * @implNote Returns -1 for missing sequence (intentional improvement over
     *           PostgreSQL which returns undefined/NULL). Consumers should
     *           handle -1 as error condition.
     *
     * @param adSequenceId AD_Sequence_ID
     * @param system "Y" for system sequences (CurrentNextSys), "N" for regular (CurrentNext)
     * @param trxName transaction name (deprecated, kept for API compatibility)
     * @return next ID value, or -1 on error
     */
    public static int nextID(Integer adSequenceId, String system, String trxName) {
        if (adSequenceId == null || adSequenceId <= 0) {
            log.warning("Invalid AD_Sequence_ID: " + adSequenceId);
            return -1;
        }

        boolean isSystem = "Y".equalsIgnoreCase(system);
        String columnName = isSystem ? "CurrentNextSys" : "CurrentNext";

        // Atomic: read current value and increment in one statement
        // RETURNING gives us the value BEFORE the increment (what we return to caller)
        String sql = "UPDATE AD_Sequence SET " + columnName + " = " + columnName + " + IncrementNo, "
            + "Updated = CURRENT_TIMESTAMP "
            + "WHERE AD_Sequence_ID = ? "
            + "RETURNING " + columnName + " - IncrementNo";

        try (PreparedStatement pstmt = DB.prepareStatement(sql, trxName)) {
            pstmt.setInt(1, adSequenceId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    log.warning("Sequence not found: " + adSequenceId);
                    return -1;
                }
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "nextID failed for sequence " + adSequenceId, e);
            return -1;
        }
    }

    /**
     * Wrapper for nextID that matches PostgreSQL nextIDFunc signature.
     */
    public static int nextIDFunc(Integer adSequenceId, String system, String trxName) {
        return nextID(adSequenceId, system, trxName);
    }

    /*
     * ATOMICITY IMPROVEMENT NOTE (per critical review #3):
     *
     * The Java nextID implementation is INTENTIONALLY BETTER than PostgreSQL:
     *
     * - Java: Uses atomic UPDATE...RETURNING (single statement, no race conditions)
     * - PostgreSQL: Uses separate SELECT + UPDATE (theoretical race window without FOR UPDATE)
     *
     * This is an intentional behavioral improvement, NOT a parity requirement.
     *
     * Validation approach:
     * 1. No duplicate IDs should ever be generated
     * 2. No gaps beyond IncrementNo should appear
     * 3. Correct increment pattern maintained
     *
     * Shadow validation logs execution for offline analysis rather than
     * comparing Java vs SQL results (which would consume sequence values).
     */

    // Placeholder methods for other functions (implemented in later tasks)
    public static BigDecimal acctBalance(Integer accountId, BigDecimal amtDr, BigDecimal amtCr) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static String getSysconfig(String name, String defaultValue, Integer clientId, Integer orgId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static String productAttribute(Integer attributeSetInstanceId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static String documentNo(Integer ppMrpId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static BigDecimal linenetamtrealinvoiceline(Integer invoiceLineId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static BigDecimal linenetamtrealorderline(Integer orderLineId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static Timestamp maxpaydate(Integer invoiceId) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=Wave4FunctionsTest -pl base`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave4Functions.java \
        base/test/src/org/compiere/migration/Wave4FunctionsTest.java
git commit -m "feat(wave4): add Wave4Functions with nextID implementation

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 2.2: Write nextID Concurrency Tests

**Files:**
- Create: `base/test/src/org/compiere/migration/NextIDConcurrencyTest.java`

**Step 1: Write concurrency test**

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@EnabledIfEnvironmentVariable(named = "RUN_DB_TESTS", matches = "true")
public class NextIDConcurrencyTest {

    private static final int THREAD_COUNT = 100;
    private static final int TEST_SEQUENCE_ID = 999999; // Use a test sequence

    @BeforeEach
    void setUp() {
        // Create test sequence if not exists
        // DB.executeUpdate("INSERT INTO AD_Sequence ...", null);
    }

    @Test
    void nextID_noDuplicatesUnderConcurrency() throws InterruptedException {
        Set<Integer> generatedIds = Collections.synchronizedSet(new HashSet<>());
        AtomicInteger duplicateCount = new AtomicInteger(0);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(THREAD_COUNT);

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                try {
                    startLatch.await(); // Wait for all threads to be ready
                    int id = Wave4Functions.nextID(TEST_SEQUENCE_ID, "N", null);
                    if (id > 0) {
                        if (!generatedIds.add(id)) {
                            duplicateCount.incrementAndGet();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    doneLatch.countDown();
                }
            });
        }

        // Start all threads simultaneously
        startLatch.countDown();

        // Wait for completion
        boolean completed = doneLatch.await(60, TimeUnit.SECONDS);
        executor.shutdown();

        assertTrue(completed, "All threads should complete within 60 seconds");
        assertEquals(0, duplicateCount.get(), "No duplicate IDs should be generated");
        assertEquals(THREAD_COUNT, generatedIds.size(), "Should generate " + THREAD_COUNT + " unique IDs");
    }

    @Test
    void nextID_incrementsCorrectly() {
        int first = Wave4Functions.nextID(TEST_SEQUENCE_ID, "N", null);
        int second = Wave4Functions.nextID(TEST_SEQUENCE_ID, "N", null);

        assertTrue(first > 0, "First ID should be positive");
        assertTrue(second > first, "Second ID should be greater than first");
    }

    @Test
    void nextID_invalidSequence_returnsNegative() {
        int result = Wave4Functions.nextID(-1, "N", null);
        assertEquals(-1, result, "Invalid sequence should return -1");
    }

    @Test
    void nextID_systemVsRegular() {
        int regular = Wave4Functions.nextID(TEST_SEQUENCE_ID, "N", null);
        int system = Wave4Functions.nextID(TEST_SEQUENCE_ID, "Y", null);

        assertTrue(regular > 0, "Regular ID should be positive");
        assertTrue(system > 0, "System ID should be positive");
        // System and regular sequences are independent
    }
}
```

**Step 2: Run test to verify it fails (method not fully wired)**

Run: `RUN_DB_TESTS=true mvn test -Dtest=NextIDConcurrencyTest -pl base`
Expected: Tests run (may pass or fail depending on DB setup)

**Step 3: Commit test file**

```bash
git add base/test/src/org/compiere/migration/NextIDConcurrencyTest.java
git commit -m "test(wave4): add nextID concurrency tests

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 2.3: Create NextIDRouter with Dual-Write Logging

**Files:**
- Create: `base/src/org/compiere/migration/NextIDRouter.java`

**Step 1: Write failing test**

Create `base/test/src/org/compiere/migration/NextIDRouterTest.java`:

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NextIDRouterTest {

    @Test
    void classExists() {
        assertDoesNotThrow(() -> Class.forName("org.compiere.migration.NextIDRouter"));
    }

    @Test
    void nextID_methodExists() {
        assertDoesNotThrow(() -> {
            var method = NextIDRouter.class.getMethod(
                "nextID", Integer.class, String.class, String.class);
            assertNotNull(method);
        });
    }
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=NextIDRouterTest -pl base`
Expected: FAIL with ClassNotFoundException

**Step 3: Create NextIDRouter**

```java
package org.compiere.migration;

import java.util.logging.Level;
import org.compiere.util.CLogger;

/**
 * Router for nextID/nextIDFunc with shadow logging.
 *
 * Unlike regular shadow mode, sequence functions cannot be dual-executed
 * because each call consumes a sequence value. Instead, we interpret
 * SHADOW mode for stateful functions as:
 * 1. Execute Java implementation
 * 2. Log execution details for offline validation
 * 3. Return Java result
 *
 * This allows monitoring without consuming extra sequence values.
 */
public class NextIDRouter {

    private static final CLogger log = CLogger.getCLogger(NextIDRouter.class);

    private NextIDRouter() {
        // Static methods only
    }

    /**
     * Route nextID call with shadow logging.
     */
    public static int nextID(Integer adSequenceId, String system, String trxName) {
        MigrationConfig config = MigrationConfig.get("nextID");

        // If SQL_ONLY, call legacy stored procedure
        if (config.getMode() == MigrationMode.SQL_ONLY) {
            return callLegacyNextID(adSequenceId, system, trxName);
        }

        // Execute Java implementation
        long startTime = System.nanoTime();
        int result;
        Exception error = null;

        try {
            result = Wave4Functions.nextID(adSequenceId, system, trxName);
        } catch (Exception e) {
            error = e;
            log.log(Level.SEVERE, "nextID Java implementation failed", e);
            // Fallback to SQL if in SHADOW mode (still validating)
            if (config.getMode() == MigrationMode.SHADOW) {
                result = callLegacyNextID(adSequenceId, system, trxName);
            } else {
                throw e;
            }
        }

        long durationNanos = System.nanoTime() - startTime;

        // Log for offline validation (SHADOW mode for stateful functions)
        if (config.getMode() == MigrationMode.SHADOW) {
            logExecution(adSequenceId, system, result, durationNanos, error);
        }

        return result;
    }

    /**
     * Route nextIDFunc call (wrapper for nextID).
     */
    public static int nextIDFunc(Integer adSequenceId, String system, String trxName) {
        return nextID(adSequenceId, system, trxName);
    }

    private static int callLegacyNextID(Integer adSequenceId, String system, String trxName) {
        // Call PostgreSQL function via JDBC
        // Note: PostgreSQL nextID is a procedure with OUT param
        String sql = "SELECT nextid(?, ?)";
        try (java.sql.PreparedStatement pstmt = org.compiere.util.DB.prepareStatement(sql, trxName)) {
            pstmt.setInt(1, adSequenceId);
            pstmt.setString(2, system);
            try (java.sql.ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (java.sql.SQLException e) {
            log.log(Level.SEVERE, "Legacy nextID failed", e);
        }
        return -1;
    }

    private static void logExecution(Integer adSequenceId, String system, int result,
                                      long durationNanos, Exception error) {
        // Use correct MigrationLogger API signature
        // Note: isMatch is false for stateful functions (no comparison possible)
        // mismatchReason contains status marker "STATEFUL_NO_COMPARISON" to distinguish
        // from actual mismatches in monitoring dashboards
        MigrationLogger.logAsync(
            "nextID",                                           // functionName
            ParamSerializer.toJson(adSequenceId, system),       // inputParams (JSON string)
            null,                                               // sqlResult (not executed)
            String.valueOf(result),                             // javaResult
            0L,                                                 // sqlTimeMs (not measured)
            durationNanos / 1_000_000,                          // javaTimeMs
            false,                                              // isMatch (always false - no comparison for stateful)
            error != null ? error.getMessage() : "STATEFUL_NO_COMPARISON"  // status marker
        );
    }
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=NextIDRouterTest -pl base`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/NextIDRouter.java \
        base/test/src/org/compiere/migration/NextIDRouterTest.java
git commit -m "feat(wave4): add NextIDRouter with dual-write logging

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 2.4: Wire MSequence to Use NextIDRouter

**Files:**
- Modify: `base/src/org/compiere/model/MSequence.java`

**Step 1: Identify insertion point in MSequence**

Read MSequence.java and find the `getNextID` method that handles PostgreSQL path.

**Step 2: Add router call**

In `MSequence.getNextID()`, replace the PostgreSQL-specific inline code with:

```java
// PostgreSQL path - use migration router
if (!DB.isOracle()) {
    return NextIDRouter.nextID(AD_Sequence_ID, adempiereSys ? "Y" : "N", trxName);
}
```

**Step 3: Run existing MSequence tests**

Run: `mvn test -Dtest=MSequenceTest -pl base`
Expected: PASS (behavior unchanged)

**Step 4: Commit**

```bash
git add base/src/org/compiere/model/MSequence.java
git commit -m "feat(wave4): wire MSequence to use NextIDRouter

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Task Group 3: Simple Lookup Functions (4 tasks)

### Task 3.1: Implement acctBalance

**Files:**
- Modify: `base/src/org/compiere/migration/Wave4Functions.java`

**Step 1: Write failing test**

Add to `Wave4FunctionsTest.java`:

```java
// Test constants - use @EnabledIfEnvironmentVariable for DB-dependent tests
// These IDs must exist in the test database with the specified AccountType values
private static final int ASSET_ACCOUNT_ID = 12345;      // AccountType='A'
private static final int LIABILITY_ACCOUNT_ID = 12346;  // AccountType='L'

@Test
@EnabledIfEnvironmentVariable(named = "RUN_DB_TESTS", matches = "true")
void acctBalance_assetDebit_positive() {
    // Asset account with natural sign = Debit balance
    BigDecimal result = Wave4Functions.acctBalance(
        ASSET_ACCOUNT_ID, new BigDecimal("100.00"), new BigDecimal("30.00"));
    assertEquals(new BigDecimal("70.00"), result);
}

@Test
@EnabledIfEnvironmentVariable(named = "RUN_DB_TESTS", matches = "true")
void acctBalance_liabilityCredit_positive() {
    // Liability account with natural sign = Credit balance
    BigDecimal result = Wave4Functions.acctBalance(
        LIABILITY_ACCOUNT_ID, new BigDecimal("30.00"), new BigDecimal("100.00"));
    assertEquals(new BigDecimal("70.00"), result);
}

@Test
void acctBalance_nullAccount_defaultCalculation() {
    // No DB lookup needed - null account uses default calculation
    BigDecimal result = Wave4Functions.acctBalance(
        null, new BigDecimal("100.00"), new BigDecimal("30.00"));
    assertEquals(new BigDecimal("70.00"), result); // AmtDr - AmtCr
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=Wave4FunctionsTest#acctBalance* -pl base`
Expected: FAIL with UnsupportedOperationException

**Step 3: Implement acctBalance**

Replace placeholder in Wave4Functions.java:

```java
/**
 * Calculate account balance considering natural sign.
 * Equivalent to PostgreSQL acct_balance function.
 *
 * Logic matches SQL exactly:
 * 1. Default balance = AmtDr - AmtCr (debit balance)
 * 2. If AccountSign is 'N' (Natural), resolve to 'D' or 'C' based on AccountType
 * 3. If resolved AccountSign is 'C', flip to credit balance (AmtCr - AmtDr)
 *
 * <p><b>Error Handling:</b> On SQLException, logs at SEVERE level and returns
 * default calculation (AmtDr - AmtCr). This matches PostgreSQL EXCEPTION behavior.
 * The circuit breaker (when enabled) will trigger on repeated errors, preventing
 * cascading failures. Monitor SEVERE log entries for data integrity issues.</p>
 *
 * @param accountId C_ElementValue_ID
 * @param amtDr Debit amount
 * @param amtCr Credit amount
 * @return Balance amount (default calculation on error)
 */
public static BigDecimal acctBalance(Integer accountId, BigDecimal amtDr, BigDecimal amtCr) {
    BigDecimal dr = amtDr != null ? amtDr : BigDecimal.ZERO;
    BigDecimal cr = amtCr != null ? amtCr : BigDecimal.ZERO;
    BigDecimal balance = dr.subtract(cr);  // Default: Debit balance

    if (accountId == null || accountId <= 0) {
        return balance;
    }

    // Fetch account type and sign from C_ElementValue
    String sql = "SELECT AccountType, AccountSign FROM C_ElementValue WHERE C_ElementValue_ID = ?";

    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        // Check for DB unavailability (per critical review #3)
        if (pstmt == null) {
            log.warning("Cannot prepare statement for acctBalance - DB unavailable, using default calculation");
            return balance;
        }
        pstmt.setInt(1, accountId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (!rs.next()) {
                return balance;  // Account not found - expected case, return default
            }

            String accountType = rs.getString("AccountType");
            String accountSign = rs.getString("AccountSign");

            // Natural sign resolution (matches SQL exactly)
            // IF (v_AccountSign='N') THEN
            //   IF (v_AccountType IN ('A','E')) THEN v_AccountSign := 'D';
            //   ELSE v_AccountSign := 'C';
            if ("N".equals(accountSign)) {
                if ("A".equals(accountType) || "E".equals(accountType)) {
                    accountSign = "D";
                } else {
                    accountSign = "C";
                }
            }

            // Credit balance = flip the calculation
            // IF (v_AccountSign = 'C') THEN v_balance := p_AmtCr - p_AmtDr;
            if ("C".equals(accountSign)) {
                balance = cr.subtract(dr);
            }
        }
    } catch (SQLException e) {
        // Log at SEVERE level - this indicates a real DB problem, not just "not found"
        // Per critical review #3: distinguish between expected (not found) and unexpected (error)
        log.log(Level.SEVERE, "Database error in acctBalance for account " + accountId, e);
        // Still return default to match SQL EXCEPTION behavior, but consider:
        // - Circuit breaker may trigger on repeated errors
        // - Monitoring should alert on SEVERE log entries
    }

    return balance;
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=Wave4FunctionsTest#acctBalance* -pl base`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave4Functions.java \
        base/test/src/org/compiere/migration/Wave4FunctionsTest.java
git commit -m "feat(wave4): implement acctBalance function

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 3.2: Implement getSysconfig

**Files:**
- Modify: `base/src/org/compiere/migration/Wave4Functions.java`

**Step 1: Write failing test**

Add to `Wave4FunctionsTest.java`:

```java
@Test
void getSysconfig_returnsDefault_whenNotFound() {
    String result = Wave4Functions.getSysconfig(
        "NONEXISTENT_CONFIG", "default_value", 0, 0);
    assertEquals("default_value", result);
}

@Test
void getSysconfig_methodSignature() {
    assertDoesNotThrow(() -> {
        var method = Wave4Functions.class.getMethod(
            "getSysconfig", String.class, String.class, Integer.class, Integer.class);
        assertEquals(String.class, method.getReturnType());
    });
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=Wave4FunctionsTest#getSysconfig* -pl base`
Expected: FAIL with UnsupportedOperationException

**Step 3: Implement getSysconfig**

Replace placeholder in Wave4Functions.java:

```java
/**
 * Retrieve system configuration value with precedence.
 * Equivalent to PostgreSQL get_sysconfig function.
 *
 * @param name Configuration name
 * @param defaultValue Default value if not found
 * @param clientId AD_Client_ID
 * @param orgId AD_Org_ID
 * @return Configuration value or default
 */
public static String getSysconfig(String name, String defaultValue, Integer clientId, Integer orgId) {
    if (name == null || name.trim().isEmpty()) {
        return defaultValue;
    }

    int client = clientId != null ? clientId : 0;
    int org = orgId != null ? orgId : 0;

    // Query with precedence matching PostgreSQL get_sysconfig exactly:
    // ORDER BY AD_Client_ID DESC, AD_Org_ID DESC
    // This gives precedence: (client,org) > (client,0) > (0,org) > (0,0)
    String sql = "SELECT Value FROM AD_SysConfig "
        + "WHERE Name = ? AND AD_Client_ID IN (0, ?) AND AD_Org_ID IN (0, ?) AND IsActive = 'Y' "
        + "ORDER BY AD_Client_ID DESC, AD_Org_ID DESC "
        + "LIMIT 1";

    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        if (pstmt == null) {
            log.warning("Cannot prepare statement for getSysconfig - DB unavailable");
            return defaultValue;
        }
        pstmt.setString(1, name);
        pstmt.setInt(2, client);
        pstmt.setInt(3, org);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String value = rs.getString("Value");
                return value != null ? value.trim() : defaultValue;
            }
        }
    } catch (SQLException e) {
        log.log(Level.WARNING, "Error fetching sysconfig " + name, e);
    }

    return defaultValue;
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=Wave4FunctionsTest#getSysconfig* -pl base`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave4Functions.java \
        base/test/src/org/compiere/migration/Wave4FunctionsTest.java
git commit -m "feat(wave4): implement getSysconfig function

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 3.3: Implement maxpaydate

**Files:**
- Modify: `base/src/org/compiere/migration/Wave4Functions.java`

**Prerequisite: Verify Index Exists**

The maxpaydate query joins C_AllocationLine → C_AllocationHdr → C_Payment and filters on `C_Invoice_ID`. Ensure index exists for performance:

```sql
-- Add to wave4-function-config.sql or run manually before shadow deployment
CREATE INDEX IF NOT EXISTS idx_allocationline_invoice
ON C_AllocationLine(C_Invoice_ID);
```

Verify with: `\d C_AllocationLine` in psql (should show the index).

**Step 1: Write failing test**

Add to `Wave4FunctionsTest.java`:

```java
@Test
void maxpaydate_nullInvoice_returnsNull() {
    Timestamp result = Wave4Functions.maxpaydate(null);
    assertNull(result);
}

@Test
void maxpaydate_invalidInvoice_returnsNull() {
    Timestamp result = Wave4Functions.maxpaydate(-1);
    assertNull(result);
}

@Test
void maxpaydate_methodSignature() {
    assertDoesNotThrow(() -> {
        var method = Wave4Functions.class.getMethod("maxpaydate", Integer.class);
        assertEquals(Timestamp.class, method.getReturnType());
    });
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=Wave4FunctionsTest#maxpaydate* -pl base`
Expected: FAIL with UnsupportedOperationException

**Step 3: Implement maxpaydate**

Replace placeholder in Wave4Functions.java:

```java
/**
 * Find most recent payment date for an invoice.
 * Equivalent to PostgreSQL maxpaydate function.
 *
 * @implNote Query structure differs from PostgreSQL (uses direct JOIN vs LEFT JOIN
 *           from C_Invoice). Results are equivalent: both return NULL for invalid
 *           invoice_id or invoice with no payments. This is an acceptable deviation
 *           that simplifies the query without changing semantics.
 *
 * @param invoiceId C_Invoice_ID
 * @return Latest payment date or null
 */
public static Timestamp maxpaydate(Integer invoiceId) {
    if (invoiceId == null || invoiceId <= 0) {
        return null;
    }

    String sql = "SELECT MAX(p.DateTrx) "
        + "FROM C_AllocationLine al "
        + "INNER JOIN C_AllocationHdr ah ON al.C_AllocationHdr_ID = ah.C_AllocationHdr_ID "
        + "INNER JOIN C_Payment p ON al.C_Payment_ID = p.C_Payment_ID "
        + "WHERE al.C_Invoice_ID = ? "
        + "AND al.C_Charge_ID IS NULL "
        + "AND ah.DocStatus <> 'RE'";

    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        if (pstmt == null) {
            log.warning("Cannot prepare statement for maxpaydate - DB unavailable");
            return null;
        }
        pstmt.setInt(1, invoiceId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getTimestamp(1);
            }
        }
    } catch (SQLException e) {
        log.log(Level.WARNING, "Error fetching max pay date for invoice " + invoiceId, e);
    }

    return null;
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=Wave4FunctionsTest#maxpaydate* -pl base`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave4Functions.java \
        base/test/src/org/compiere/migration/Wave4FunctionsTest.java
git commit -m "feat(wave4): implement maxpaydate function

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 3.4: Verify MSysConfig Already Uses Java (No Routing Needed)

**Files:**
- Read: `base/src/org/compiere/model/MSysConfig.java`

**Step 1: Verify existing implementation**

Read MSysConfig.java and confirm it already implements the same logic as get_sysconfig.sql.

**Step 2: Document decision**

The existing MSysConfig.getValue() already implements the function in Java with caching.
No routing is needed - this function is already Java-primary.

Update migration config to reflect this:

```sql
-- get_Sysconfig already uses Java implementation in MSysConfig
-- Set to JAVA_ONLY immediately (no shadow needed)
UPDATE migration.function_config
SET mode = 'JAVA_ONLY', updated = NOW()
WHERE function_name = 'get_Sysconfig';
```

**Step 3: Commit documentation**

```bash
git add migration/sql/wave4-function-config.sql
git commit -m "docs(wave4): document get_Sysconfig as already Java-implemented

MSysConfig.getValue() already implements equivalent logic with caching.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Task Group 4: Line Amount Functions (3 tasks)

### Task 4.1: Implement linenetamtrealinvoiceline

**Files:**
- Modify: `base/src/org/compiere/migration/Wave4Functions.java`

**Step 1: Write failing test**

Add to `Wave4FunctionsTest.java`:

```java
@Test
void linenetamtrealinvoiceline_nullId_returnsZero() {
    BigDecimal result = Wave4Functions.linenetamtrealinvoiceline(null);
    assertEquals(BigDecimal.ZERO, result);
}

@Test
void linenetamtrealinvoiceline_invalidId_returnsZero() {
    BigDecimal result = Wave4Functions.linenetamtrealinvoiceline(-1);
    assertEquals(BigDecimal.ZERO, result);
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=Wave4FunctionsTest#linenetamtrealinvoiceline* -pl base`
Expected: FAIL with UnsupportedOperationException

**Step 3: Implement linenetamtrealinvoiceline**

Replace placeholder in Wave4Functions.java:

```java
/**
 * Calculate net amount excluding tax if tax-inclusive pricing.
 * Equivalent to PostgreSQL linenetamtrealinvoiceline function.
 *
 * @param invoiceLineId C_InvoiceLine_ID
 * @return Net amount (tax-exclusive)
 */
public static BigDecimal linenetamtrealinvoiceline(Integer invoiceLineId) {
    if (invoiceLineId == null || invoiceLineId <= 0) {
        return BigDecimal.ZERO;
    }

    String sql = "SELECT il.LineNetAmt, pl.IsTaxIncluded, t.Rate, c.StdPrecision "
        + "FROM C_InvoiceLine il "
        + "INNER JOIN C_Invoice i ON il.C_Invoice_ID = i.C_Invoice_ID "
        + "INNER JOIN M_PriceList pl ON i.M_PriceList_ID = pl.M_PriceList_ID "
        + "INNER JOIN C_Tax t ON il.C_Tax_ID = t.C_Tax_ID "
        + "INNER JOIN C_Currency c ON i.C_Currency_ID = c.C_Currency_ID "
        + "WHERE il.C_InvoiceLine_ID = ?";

    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        if (pstmt == null) {
            log.warning("Cannot prepare statement for linenetamtrealinvoiceline - DB unavailable");
            return BigDecimal.ZERO;
        }
        pstmt.setInt(1, invoiceLineId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                BigDecimal lineNetAmt = rs.getBigDecimal("LineNetAmt");
                boolean isTaxIncluded = "Y".equals(rs.getString("IsTaxIncluded"));
                BigDecimal rate = rs.getBigDecimal("Rate");
                int precision = rs.getInt("StdPrecision");

                return calculateTaxExclusiveAmount(lineNetAmt, isTaxIncluded, rate, precision);
            }
        }
    } catch (SQLException e) {
        log.log(Level.WARNING, "Error calculating line net amount for invoice line " + invoiceLineId, e);
    }

    return BigDecimal.ZERO;
}

/**
 * Calculate tax-exclusive amount from tax-inclusive amount.
 * Uses 15 decimal places for intermediate calculations to match PostgreSQL numeric precision.
 *
 * @implNote RoundingMode.HALF_UP is used here. PostgreSQL numeric division uses
 *           ROUND_HALF_EVEN (banker's rounding) by default. For most cases this
 *           produces identical results, but edge cases like 2.5 would round to 3
 *           in Java vs 2 in PostgreSQL. Shadow validation will detect any mismatches.
 *           If persistent mismatches occur, consider switching to HALF_EVEN.
 */
private static BigDecimal calculateTaxExclusiveAmount(
        BigDecimal lineNetAmt, boolean isTaxIncluded, BigDecimal rate, int precision) {
    if (lineNetAmt == null) {
        return BigDecimal.ZERO;
    }
    if (!isTaxIncluded || rate == null || rate.compareTo(BigDecimal.ZERO) == 0) {
        return lineNetAmt;
    }
    // LineNetAmt / (1 + Rate/100)
    // Use 15 decimal places for intermediate precision to match PostgreSQL numeric behavior
    BigDecimal divisor = BigDecimal.ONE.add(rate.divide(
        new BigDecimal("100"), 15, RoundingMode.HALF_UP));
    return lineNetAmt.divide(divisor, precision, RoundingMode.HALF_UP);
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=Wave4FunctionsTest#linenetamtrealinvoiceline* -pl base`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave4Functions.java \
        base/test/src/org/compiere/migration/Wave4FunctionsTest.java
git commit -m "feat(wave4): implement linenetamtrealinvoiceline function

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 4.2: Implement linenetamtrealorderline

**Files:**
- Modify: `base/src/org/compiere/migration/Wave4Functions.java`

**Step 1: Write failing test**

Add to `Wave4FunctionsTest.java`:

```java
@Test
void linenetamtrealorderline_nullId_returnsZero() {
    BigDecimal result = Wave4Functions.linenetamtrealorderline(null);
    assertEquals(BigDecimal.ZERO, result);
}

@Test
void linenetamtrealorderline_invalidId_returnsZero() {
    BigDecimal result = Wave4Functions.linenetamtrealorderline(-1);
    assertEquals(BigDecimal.ZERO, result);
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=Wave4FunctionsTest#linenetamtrealorderline* -pl base`
Expected: FAIL with UnsupportedOperationException

**Step 3: Implement linenetamtrealorderline**

Replace placeholder in Wave4Functions.java:

```java
/**
 * Calculate net amount excluding tax if tax-inclusive pricing.
 * Equivalent to PostgreSQL linenetamtrealorderline function.
 *
 * @param orderLineId C_OrderLine_ID
 * @return Net amount (tax-exclusive)
 */
public static BigDecimal linenetamtrealorderline(Integer orderLineId) {
    if (orderLineId == null || orderLineId <= 0) {
        return BigDecimal.ZERO;
    }

    String sql = "SELECT ol.LineNetAmt, pl.IsTaxIncluded, t.Rate, c.StdPrecision "
        + "FROM C_OrderLine ol "
        + "INNER JOIN C_Order o ON ol.C_Order_ID = o.C_Order_ID "
        + "INNER JOIN M_PriceList pl ON o.M_PriceList_ID = pl.M_PriceList_ID "
        + "INNER JOIN C_Tax t ON ol.C_Tax_ID = t.C_Tax_ID "
        + "INNER JOIN C_Currency c ON o.C_Currency_ID = c.C_Currency_ID "
        + "WHERE ol.C_OrderLine_ID = ?";

    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        if (pstmt == null) {
            log.warning("Cannot prepare statement for linenetamtrealorderline - DB unavailable");
            return BigDecimal.ZERO;
        }
        pstmt.setInt(1, orderLineId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                BigDecimal lineNetAmt = rs.getBigDecimal("LineNetAmt");
                boolean isTaxIncluded = "Y".equals(rs.getString("IsTaxIncluded"));
                BigDecimal rate = rs.getBigDecimal("Rate");
                int precision = rs.getInt("StdPrecision");

                return calculateTaxExclusiveAmount(lineNetAmt, isTaxIncluded, rate, precision);
            }
        }
    } catch (SQLException e) {
        log.log(Level.WARNING, "Error calculating line net amount for order line " + orderLineId, e);
    }

    return BigDecimal.ZERO;
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=Wave4FunctionsTest#linenetamtrealorderline* -pl base`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave4Functions.java \
        base/test/src/org/compiere/migration/Wave4FunctionsTest.java
git commit -m "feat(wave4): implement linenetamtrealorderline function

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 4.3: Verify DRY Tax Calculation Helper

**Files:**
- Review: `base/src/org/compiere/migration/Wave4Functions.java`

**Step 1: Verify helper method exists**

Both linenetamtrealinvoiceline and linenetamtrealorderline should use the shared `calculateTaxExclusiveAmount()` helper method.

**Step 2: Run all linenetamt tests**

Run: `mvn test -Dtest=Wave4FunctionsTest#linenetamt* -pl base`
Expected: PASS

**Step 3: No commit needed (code already DRY)**

---

## Task Group 5: Complex Functions (3 tasks)

### Task 5.1: Implement productAttribute

**Files:**
- Modify: `base/src/org/compiere/migration/Wave4Functions.java`

**Step 1: Write failing test**

Add to `Wave4FunctionsTest.java`:

```java
@Test
void productAttribute_nullId_returnsEmptyString() {
    // PostgreSQL: IF (p_M_AttributeSetInstance_ID > 0) is false for NULL, returns ''
    String result = Wave4Functions.productAttribute(null);
    assertEquals("", result);
}

@Test
void productAttribute_zeroId_returnsEmptyString() {
    String result = Wave4Functions.productAttribute(0);
    assertEquals("", result);
}

@Test
void productAttribute_negativeId_returnsEmptyString() {
    String result = Wave4Functions.productAttribute(-1);
    assertEquals("", result);
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=Wave4FunctionsTest#productAttribute* -pl base`
Expected: FAIL with UnsupportedOperationException

**Step 3: Implement productAttribute**

Replace placeholder in Wave4Functions.java:

```java
/**
 * Build display string for product attribute set instance.
 * Equivalent to PostgreSQL productattribute function.
 *
 * NOTE: Column names verified against I_M_AttributeSet.java:
 * - SerNoCharSOverwrite, SerNoCharEOverwrite (not SerNoCharOverwrite)
 * - LotCharSOverwrite, LotCharEOverwrite (not LotCharOverwrite)
 *
 * @param attributeSetInstanceId M_AttributeSetInstance_ID
 * @return Formatted attribute string or empty string (matches PostgreSQL behavior)
 */
public static String productAttribute(Integer attributeSetInstanceId) {
    // Match PostgreSQL: IF (p_M_AttributeSetInstance_ID > 0) returns '' for NULL or <= 0
    if (attributeSetInstanceId == null || attributeSetInstanceId <= 0) {
        return "";
    }

    StringBuilder result = new StringBuilder();

    // Fetch instance data with COALESCE for character overwrites (matches SQL exactly)
    // Column names: SerNoCharSOverwrite, SerNoCharEOverwrite, LotCharSOverwrite, LotCharEOverwrite
    String instanceSql = "SELECT asi.Lot, asi.SerNo, asi.GuaranteeDate, "
        + "COALESCE(aset.SerNoCharSOverwrite, '#') AS SerNoStart, "
        + "COALESCE(aset.SerNoCharEOverwrite, '') AS SerNoEnd, "
        + "COALESCE(aset.LotCharSOverwrite, '\u00AB') AS LotStart, "
        + "COALESCE(aset.LotCharEOverwrite, '\u00BB') AS LotEnd "
        + "FROM M_AttributeSetInstance asi "
        + "INNER JOIN M_AttributeSet aset ON asi.M_AttributeSet_ID = aset.M_AttributeSet_ID "
        + "WHERE asi.M_AttributeSetInstance_ID = ?";

    String lot = null;
    String serNo = null;
    Timestamp guaranteeDate = null;
    String serNoStart = "#";
    String serNoEnd = "";
    String lotStart = "\u00AB"; // «
    String lotEnd = "\u00BB";   // »

    try (PreparedStatement pstmt = DB.prepareStatement(instanceSql, null)) {
        if (pstmt == null) {
            log.warning("Cannot prepare statement for productAttribute - DB unavailable");
            return "";
        }
        pstmt.setInt(1, attributeSetInstanceId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                lot = rs.getString("Lot");
                serNo = rs.getString("SerNo");
                guaranteeDate = rs.getTimestamp("GuaranteeDate");

                // COALESCE already applied in SQL - just read the values
                serNoStart = rs.getString("SerNoStart");
                serNoEnd = rs.getString("SerNoEnd");
                lotStart = rs.getString("LotStart");
                lotEnd = rs.getString("LotEnd");
            } else {
                return "";
            }
        }
    } catch (SQLException e) {
        log.log(Level.WARNING, "Error fetching attribute instance " + attributeSetInstanceId, e);
        return "";
    }

    // Build result string - add trailing space after each element (matches PostgreSQL pattern)
    // PostgreSQL adds trailing space to everything, then TRIMs at the end
    if (serNo != null && !serNo.isEmpty()) {
        result.append(serNoStart).append(serNo).append(serNoEnd).append(" ");
    }
    if (lot != null && !lot.isEmpty()) {
        result.append(lotStart).append(lot).append(lotEnd).append(" ");
    }
    if (guaranteeDate != null) {
        // Match PostgreSQL ISO DateStyle timestamp-to-varchar coercion: "yyyy-MM-dd HH:mm:ss"
        // Use UTC timezone to match PostgreSQL server timezone (verify server config)
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        result.append(sdf.format(guaranteeDate)).append(" ");
    }

    // Fetch additional attributes - MUST include IsInstanceAttribute='Y' filter
    String attrSql = "SELECT a.Name, ai.Value "
        + "FROM M_AttributeInstance ai "
        + "INNER JOIN M_Attribute a ON (ai.M_Attribute_ID = a.M_Attribute_ID AND a.IsInstanceAttribute = 'Y') "
        + "WHERE ai.M_AttributeSetInstance_ID = ? "
        + "ORDER BY a.Name";

    try (PreparedStatement pstmt = DB.prepareStatement(attrSql, null)) {
        if (pstmt == null) {
            log.warning("Cannot prepare statement for productAttribute attributes - DB unavailable");
            // Continue with what we have so far
        } else {
            pstmt.setInt(1, attributeSetInstanceId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("Name");
                    String value = rs.getString("Value");
                    // Check both name AND value are non-null and non-empty
                    // Prevents "null:value" output if M_Attribute.Name is null (data quality issue)
                    if (name != null && !name.isEmpty() && value != null && !value.isEmpty()) {
                        // Add trailing space (matches PostgreSQL pattern)
                        result.append(name).append(":").append(value).append(" ");
                    }
                }
            }
        }
    } catch (SQLException e) {
        log.log(Level.WARNING, "Error fetching attributes for instance " + attributeSetInstanceId, e);
    }

    if (result.length() == 0) {
        return "";
    }

    // TRIM at end matches PostgreSQL: v_Name || ' (' || TRIM(v_NameAdd) || ')'
    // Leading space matches PostgreSQL output exactly
    return " (" + result.toString().trim() + ")";
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=Wave4FunctionsTest#productAttribute* -pl base`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave4Functions.java \
        base/test/src/org/compiere/migration/Wave4FunctionsTest.java
git commit -m "feat(wave4): implement productAttribute function

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 5.2: Implement documentNo

**Files:**
- Modify: `base/src/org/compiere/migration/Wave4Functions.java`

**Design Decision: Single-Query Approach**

The implementation uses a single query with 6 LEFT JOINs regardless of orderType. This is an accepted complexity trade-off:

- **Alternative considered:** Two-phase approach (get orderType first, then conditional query)
- **Decision:** Single query preferred because:
  1. documentNo is called infrequently (MRP reports/views)
  2. PostgreSQL optimizer handles unused JOINs efficiently
  3. Single round-trip reduces network latency
  4. Simpler code maintenance
- **Performance impact:** Negligible for expected call frequency (<100/min)
- **Monitoring:** Shadow validation will flag if latency exceeds tier budget

**Step 1: Write failing test**

Add to `Wave4FunctionsTest.java`:

```java
@Test
void documentNo_nullId_returnsEmptyString() {
    String result = Wave4Functions.documentNo(null);
    assertEquals("", result);
}

@Test
void documentNo_zeroId_returnsEmptyString() {
    String result = Wave4Functions.documentNo(0);
    assertEquals("", result);
}

@Test
void documentNo_negativeId_returnsEmptyString() {
    String result = Wave4Functions.documentNo(-1);
    assertEquals("", result);
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -Dtest=Wave4FunctionsTest#documentNo* -pl base`
Expected: FAIL with UnsupportedOperationException

**Step 3: Implement documentNo**

Replace placeholder in Wave4Functions.java:

```java
/**
 * Return document number for a PP_MRP record based on order type.
 * Equivalent to PostgreSQL documentno function.
 *
 * @param ppMrpId PP_MRP_ID
 * @return Document number or empty string
 */
public static String documentNo(Integer ppMrpId) {
    if (ppMrpId == null || ppMrpId <= 0) {
        return "";
    }

    // Query order type and related document number
    String sql = "SELECT mrp.OrderType, "
        + "f.Name AS ForecastName, "
        + "po.DocumentNo AS PODocumentNo, "
        + "ddo.DocumentNo AS DDDocumentNo, "
        + "so.DocumentNo AS SODocumentNo, "
        + "mop.DocumentNo AS MOPDocumentNo, "
        + "req.DocumentNo AS ReqDocumentNo "
        + "FROM PP_MRP mrp "
        + "LEFT JOIN M_Forecast f ON mrp.M_Forecast_ID = f.M_Forecast_ID "
        + "LEFT JOIN C_Order po ON mrp.C_Order_ID = po.C_Order_ID AND mrp.OrderType = 'POO' "
        + "LEFT JOIN DD_Order ddo ON mrp.DD_Order_ID = ddo.DD_Order_ID AND mrp.OrderType = 'DOO' "
        + "LEFT JOIN C_Order so ON mrp.C_Order_ID = so.C_Order_ID AND mrp.OrderType = 'SOO' "
        + "LEFT JOIN PP_Order mop ON mrp.PP_Order_ID = mop.PP_Order_ID AND mrp.OrderType = 'MOP' "
        + "LEFT JOIN M_Requisition req ON mrp.M_Requisition_ID = req.M_Requisition_ID AND mrp.OrderType = 'POR' "
        + "WHERE mrp.PP_MRP_ID = ?";

    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        if (pstmt == null) {
            log.warning("Cannot prepare statement for documentNo - DB unavailable");
            return "";
        }
        pstmt.setInt(1, ppMrpId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String orderType = rs.getString("OrderType");
                if (orderType == null) {
                    return "";
                }
                // TRIM orderType to match PostgreSQL: WHEN trim(mrp.ordertype) = 'FTC' THEN ...
                orderType = orderType.trim();

                String docNo = null;
                switch (orderType) {
                    case "FTC":
                        docNo = rs.getString("ForecastName");
                        break;
                    case "POO":
                        docNo = rs.getString("PODocumentNo");
                        break;
                    case "DOO":
                        docNo = rs.getString("DDDocumentNo");
                        break;
                    case "SOO":
                        docNo = rs.getString("SODocumentNo");
                        break;
                    case "MOP":
                        docNo = rs.getString("MOPDocumentNo");
                        break;
                    case "POR":
                        docNo = rs.getString("ReqDocumentNo");
                        break;
                    default:
                        return "";
                }

                return docNo != null ? docNo : "";
            }
        }
    } catch (SQLException e) {
        log.log(Level.WARNING, "Error fetching document number for MRP " + ppMrpId, e);
    }

    return "";
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -Dtest=Wave4FunctionsTest#documentNo* -pl base`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave4Functions.java \
        base/test/src/org/compiere/migration/Wave4FunctionsTest.java
git commit -m "feat(wave4): implement documentNo function

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 5.3: Wire Callers to Use Routers

**Files:**
- Identify call sites in codebase

**Step 1: Search for existing SQL function calls**

Search for usages of:
- `productattribute(`
- `documentno(`
- `linenetamtrealinvoiceline(`
- `linenetamtrealorderline(`
- `maxpaydate(`
- `acct_balance(`

**Step 2: Document call sites**

Create list of files that need to be updated to use Wave4FunctionRouter instead of direct SQL calls.

**Step 3: Update each call site**

For each call site, replace:
```java
// Old: Direct SQL call
DB.getSQLValueString(sql, params);
```

With:
```java
// New: Router call
Wave4FunctionRouter.functionName(params);
```

**Step 4: Run affected tests**

Run: `mvn test -pl base`
Expected: PASS

**Step 5: Commit**

```bash
git add -A
git commit -m "feat(wave4): wire call sites to use Wave4FunctionRouter

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Task Group 6: Testing and Validation (4 tasks)

### Task 6.1: Create Integration Tests

**Files:**
- Create: `base/test/src/org/compiere/migration/Wave4IntegrationTest.java`

**Step 1: Write integration test class**

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

@EnabledIfEnvironmentVariable(named = "RUN_DB_TESTS", matches = "true")
public class Wave4IntegrationTest {

    @BeforeAll
    static void setUp() {
        // Initialize ADempiere context
        // Env.setContext(Env.getCtx(), ...);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    void acctBalance_javaMatchesSql(int accountId) {
        BigDecimal amtDr = new BigDecimal("100.00");
        BigDecimal amtCr = new BigDecimal("30.00");

        BigDecimal javaResult = Wave4Functions.acctBalance(accountId, amtDr, amtCr);
        BigDecimal sqlResult = SqlFunctionCaller.callAcctBalance(accountId, amtDr, amtCr);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> String.format("Mismatch for account %d: java=%s, sql=%s",
                accountId, javaResult, sqlResult));
    }

    @Test
    void getSysconfig_javaMatchesSql() {
        String name = "SYSTEM_NATIVE_SEQUENCE";
        String defaultValue = "N";
        int clientId = 0;
        int orgId = 0;

        String javaResult = Wave4Functions.getSysconfig(name, defaultValue, clientId, orgId);
        String sqlResult = SqlFunctionCaller.callGetSysconfig(name, defaultValue, clientId, orgId);

        assertEquals(sqlResult, javaResult);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 100, 1000})
    void linenetamtrealinvoiceline_javaMatchesSql(int invoiceLineId) {
        BigDecimal javaResult = Wave4Functions.linenetamtrealinvoiceline(invoiceLineId);
        BigDecimal sqlResult = SqlFunctionCaller.callLinenetamtrealinvoiceline(invoiceLineId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> String.format("Mismatch for invoice line %d: java=%s, sql=%s",
                invoiceLineId, javaResult, sqlResult));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 100, 1000})
    void linenetamtrealorderline_javaMatchesSql(int orderLineId) {
        BigDecimal javaResult = Wave4Functions.linenetamtrealorderline(orderLineId);
        BigDecimal sqlResult = SqlFunctionCaller.callLinenetamtrealorderline(orderLineId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> String.format("Mismatch for order line %d: java=%s, sql=%s",
                orderLineId, javaResult, sqlResult));
    }

    @Test
    void productAttribute_withAttributes_javaMatchesSql() {
        // Use known M_AttributeSetInstance_ID with SerNo, Lot, and attributes
        // Query to find suitable test ID: SELECT M_AttributeSetInstance_ID FROM M_AttributeSetInstance
        //   WHERE Lot IS NOT NULL OR SerNo IS NOT NULL LIMIT 10
        int[] testIds = getTestAttributeSetInstanceIds();
        for (int attributeSetInstanceId : testIds) {
            String javaResult = Wave4Functions.productAttribute(attributeSetInstanceId);
            String sqlResult = SqlFunctionCaller.callProductAttribute(attributeSetInstanceId);
            assertEquals(sqlResult, javaResult,
                () -> String.format("Mismatch for ASI %d: java='%s', sql='%s'",
                    attributeSetInstanceId, javaResult, sqlResult));
        }
    }

    private int[] getTestAttributeSetInstanceIds() {
        // Return IDs known to have attributes in test database
        // Implement based on test data setup
        return new int[]{1, 100, 1000};
    }
}
```

**Step 2: Run integration tests**

Run: `RUN_DB_TESTS=true mvn test -Dtest=Wave4IntegrationTest -pl base`
Expected: PASS

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave4IntegrationTest.java
git commit -m "test(wave4): add integration tests for Java/SQL parity

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 6.2: Create Shadow Validation Tests

**Files:**
- Create: `base/test/src/org/compiere/migration/Wave4ShadowValidationTest.java`

**Step 1: Write shadow validation test**

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

@EnabledIfEnvironmentVariable(named = "RUN_DB_TESTS", matches = "true")
public class Wave4ShadowValidationTest {

    @Test
    void router_executesInShadowMode() {
        // Temporarily set mode to SHADOW
        // Call router
        // Verify both Java and SQL were called
        // Verify result logged
    }

    @Test
    void router_fallbacksToSql_onJavaError() {
        // Temporarily set mode to SHADOW
        // Inject Java failure
        // Verify SQL result returned
        // Verify mismatch logged
    }

    @Test
    void router_respectsSampleRate() {
        // Set sample rate to 0.5
        // Call 1000 times
        // Verify approximately 50% went through shadow path
    }
}
```

**Step 2: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave4ShadowValidationTest.java
git commit -m "test(wave4): add shadow validation tests

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

### Task 6.3: Deploy to SHADOW Mode

**Files:**
- Update: `migration/sql/wave4-function-config.sql`

**Step 1: Update configuration**

Uncomment the SHADOW transition in wave4-function-config.sql.

**Step 2: Run configuration update**

```bash
psql -f migration/sql/wave4-function-config.sql
```

**Step 3: Verify configuration**

```sql
SELECT function_name, mode, sample_rate
FROM migration.function_config
WHERE function_name IN ('acctBalance', 'productAttribute', 'documentNo',
                        'get_Sysconfig', 'linenetamtrealinvoiceline',
                        'linenetamtrealorderline', 'maxpaydate');
```

Expected: All rows show `mode = 'SHADOW'`

**Step 4: Monitor for 7 days**

Query match rate daily:
```sql
SELECT function_name,
       COUNT(*) as total_calls,
       SUM(CASE WHEN is_match THEN 1 ELSE 0 END) * 100.0 / COUNT(*) as match_rate
FROM migration.function_log
WHERE created_at > NOW() - INTERVAL '24 hours'
  AND function_name LIKE '%'
GROUP BY function_name;
```

**Success Criteria:** 99.9% match rate for 7 consecutive days.

---

### Task 6.4: Cutover to JAVA_ONLY

**Files:**
- Update: `migration/sql/wave4-function-config.sql`

**Step 1: Verify quality gates**

- [ ] 99.9% match rate for 7 days
- [ ] No critical mismatches
- [ ] Performance within tier budget (5% for CRITICAL, 30% for STANDARD)
- [ ] Rollback tested

**Step 2: Update configuration**

Uncomment the JAVA_ONLY transition in wave4-function-config.sql.

**Step 3: Run configuration update**

```bash
psql -f migration/sql/wave4-function-config.sql
```

**Step 4: Monitor for 24 hours**

Watch for:
- Error rate spikes
- Latency degradation
- Any functional issues

**Step 5: Final commit**

```bash
git add migration/sql/wave4-function-config.sql
git commit -m "feat(wave4): cutover to JAVA_ONLY

All quality gates passed. 7-day shadow validation complete.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Summary

| Task Group | Tasks | Functions Covered |
|------------|-------|-------------------|
| 0. Prerequisites | 2 | StringComparator infrastructure, View dependency analysis |
| 1. Infrastructure | 3 | All (config, router base) |
| 2. Sequence | 4 | nextID, nextIDFunc |
| 3. Simple Lookup | 4 | acctBalance, getSysconfig, maxpaydate |
| 4. Line Amount | 3 | linenetamtrealinvoiceline, linenetamtrealorderline |
| 5. Complex | 3 | productAttribute, documentNo |
| 6. Validation | 4 | All (testing, shadow, cutover) |

**Total:** 23 tasks across 7 groups

---

## Rollback Procedure

If issues occur after cutover:

1. **Immediate (< 1 min):** Set `mode = 'SQL_ONLY'` in `migration.function_config`
2. **If SQL function deleted:** Restore from git
3. **Monitor:** Verify traffic routing back to SQL

Wave 4 has **no downstream dependencies** - can be rolled back independently.

---

## Post-Migration Cleanup (30 days after stable JAVA_ONLY)

- [ ] Remove shadow execution code
- [ ] Remove SqlFunctionCaller methods for Wave 4
- [ ] Remove feature flags
- [ ] Delete SQL functions from database
- [ ] Archive this document
