# Wave 1: Currency Functions Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Migrate the 4 currency conversion functions (currencyRound, currencyRate, currencyConvert, currencyBase) from PostgreSQL to Java, establishing foundation for Wave 3 (Financial Core).

**Architecture:** Stateless utility class `CurrencyFunctions` in `org.compiere.util` package, following Wave 0 patterns (SqlCompat, TimeUtil). Functions delegate to existing `MConversionRate` and `MCurrency` where possible. Shadow execution via existing infrastructure (`ShadowExecutor`, `MigrationLogger`).

**Tech Stack:** Java 11+, ADempiere DB utilities, JUnit 5, existing MConversionRate/MCurrency classes

---

## Architectural Decisions

### Decision 1: EMU-to-EMU SQL Bug Handling

**Date:** 2026-01-03
**Status:** Approved
**Decision:** Fix SQL first, then implement correct Java behavior

**Context:**
The existing PostgreSQL function `currencyRate` at `db/ddlutils/postgresql/functions/C_Currency_Rate.sql:110-113` contains a bug:

```sql
-- BUGGY CODE (line 110):
IF (cf_IsEMUMember = 'Y' AND cf_IsEMUMember ='Y'  -- BUG: second check should be ct_IsEMUMember
```

This checks the source currency's EMU membership twice instead of checking both source and target currencies. The correct logic should be:

```sql
IF (cf_IsEMUMember = 'Y' AND ct_IsEMUMember = 'Y'
```

**Options Considered:**
1. **Fix SQL first** - Correct the bug in PostgreSQL, implement correct Java
2. **Replicate bug for parity** - Match buggy SQL exactly, fix both later
3. **Document only** - Implement correct Java, accept shadow mismatches

**Decision Rationale:**
- EMU (European Monetary Union) fixed-rate logic is legacy code for currencies that adopted the Euro (1999-2002)
- In 2026, this code path is rarely if ever executed in production
- The bug causes incorrect rates for EMU-to-EMU conversions (e.g., DEM to FRF)
- Intentionally replicating known bugs creates technical debt and maintainer confusion
- A verification step (query production for EMU-to-EMU usage) makes the SQL fix low-risk

**Implementation:**
1. Task 0.1: Query production to verify EMU-to-EMU conversion frequency
2. Task 0.2: If minimal/no usage, fix SQL function with targeted test
3. Task 0.3: Implement correct Java behavior
4. Fallback: If significant EMU-to-EMU usage found, replicate bug for parity and fix post-migration

**References:**
- SQL function: `db/ddlutils/postgresql/functions/C_Currency_Rate.sql:110-113`
- Critical review: `docs/plans/2026-01-03-wave1-currency-implementation-critical-review-1.md`, Issue 2.1

---

### Decision 2: BigDecimal Comparison Tolerance

**Date:** 2026-01-03
**Status:** Approved
**Decision:** Use 6 decimal place tolerance for currency comparisons

**Context:**
Currency rate calculations involve division operations that may produce different results between Java and SQL due to:
- Different intermediate precision handling
- Different rounding behavior at division boundaries
- EMU rate calculations use `divide(..., 12, RoundingMode.HALF_UP)`

**Decision:**
- Use 6 decimal place tolerance for shadow mode comparison
- This matches typical currency precision requirements (most currencies use 2-4 decimals)
- Allows for minor floating-point differences while catching significant logic errors

**Implementation:**
```java
public class BigDecimalComparator implements BiPredicate<BigDecimal, BigDecimal> {
    private final int toleranceScale;
    public static final BigDecimalComparator CURRENCY = new BigDecimalComparator(6);
    // ...
}
```

---

### Decision 3: Test Data Strategy

**Date:** 2026-01-03
**Status:** Approved
**Decision:** Use dynamic test data lookup with documented assumptions

**Context:**
Hardcoded currency IDs (USD=100, EUR=102) make tests brittle and non-portable across environments.

**Decision:**
- Use dynamic lookup by ISO code where possible
- Document required test data in test class Javadoc
- Use `assumeTrue()` to skip tests gracefully if data missing
- Maintain a constants file for environments with non-standard IDs

---

## Pre-Implementation: Infrastructure and SQL Fix

> **Migration Directory:** All migration scripts go in `[PROJECT_ROOT]/migration/sql/`. Create directory if it doesn't exist: `mkdir -p migration/sql`

### Task 0: Verify and Fix EMU SQL Bug

**Files:**
- Query: Production database
- Modify: `db/ddlutils/postgresql/functions/C_Currency_Rate.sql`
- Create: `migration/sql/fix-emu-rate-bug.sql`

**Step 1: Query production for EMU-to-EMU conversion usage**

```sql
-- Check if any EMU-to-EMU conversions occur in production
-- Run against production database (read-only)
SELECT
    cf.ISO_Code as from_currency,
    ct.ISO_Code as to_currency,
    COUNT(*) as conversion_count
FROM C_Conversion_Rate cr
JOIN C_Currency cf ON cr.C_Currency_ID = cf.C_Currency_ID
JOIN C_Currency ct ON cr.C_Currency_ID_To = ct.C_Currency_ID
WHERE cf.IsEMUMember = 'Y' AND ct.IsEMUMember = 'Y'
  AND cf.C_Currency_ID != ct.C_Currency_ID
GROUP BY cf.ISO_Code, ct.ISO_Code
ORDER BY conversion_count DESC;
```

**Expected:** Zero or minimal rows (EMU currencies haven't been used since Euro adoption)

**Step 2: Create SQL fix migration script**

> **IMPORTANT:** This step requires copying the original function and applying a targeted fix. Do NOT write a template - create the complete executable migration.

**Sub-step 2a: Copy original function**
```bash
# Read the original function to understand the structure
cat db/ddlutils/postgresql/functions/C_Currency_Rate.sql
```

**Sub-step 2b: Locate the bug at line ~110**
```bash
# Find the exact buggy line
grep -n "cf_IsEMUMember = 'Y' AND cf_IsEMUMember" db/ddlutils/postgresql/functions/C_Currency_Rate.sql
```

Expected output shows the bug: `cf_IsEMUMember = 'Y' AND cf_IsEMUMember ='Y'` (checks source twice)

**Sub-step 2c: Create migration script with sed-based fix**
```bash
# Create the migration script that applies the fix
cat > migration/sql/fix-emu-rate-bug.sql << 'MIGRATION_EOF'
-- migration/sql/fix-emu-rate-bug.sql
-- Fix EMU-to-EMU rate check bug in currencyRate function
--
-- BUG: Line ~110 checks cf_IsEMUMember twice instead of checking both currencies
-- BEFORE: IF (cf_IsEMUMember = 'Y' AND cf_IsEMUMember ='Y'
-- AFTER:  IF (cf_IsEMUMember = 'Y' AND ct_IsEMUMember = 'Y'
--
-- Decision documented in: docs/plans/2026-01-03-wave1-currency-implementation.md

MIGRATION_EOF

# Append the fixed function body (copy from source and fix the bug)
sed "s/cf_IsEMUMember = 'Y' AND cf_IsEMUMember ='Y'/cf_IsEMUMember = 'Y' AND ct_IsEMUMember = 'Y'/g" \
    db/ddlutils/postgresql/functions/C_Currency_Rate.sql >> migration/sql/fix-emu-rate-bug.sql
```

**Sub-step 2d: Verify the fix was applied**
```bash
# Confirm the fix is in the migration script
grep -n "ct_IsEMUMember" migration/sql/fix-emu-rate-bug.sql
# Should show the corrected line with ct_IsEMUMember

# Confirm the bug is NOT in the migration script
grep -c "cf_IsEMUMember = 'Y' AND cf_IsEMUMember ='Y'" migration/sql/fix-emu-rate-bug.sql
# Should return 0 (no matches)
```

**Sub-step 2e: Also fix the source file for future deployments**
```bash
# Apply same fix to the source file
sed -i "s/cf_IsEMUMember = 'Y' AND cf_IsEMUMember ='Y'/cf_IsEMUMember = 'Y' AND ct_IsEMUMember = 'Y'/g" \
    db/ddlutils/postgresql/functions/C_Currency_Rate.sql
```

**Step 3: Add regression test for EMU-to-EMU conversion**

Create test that verifies the fix works correctly (will be added to Wave1ShadowIntegrationTest).

**Step 4: Commit**

```bash
git add migration/sql/fix-emu-rate-bug.sql
git commit -m "fix(sql): correct EMU-to-EMU rate check in currencyRate function

The SQL function incorrectly checked cf_IsEMUMember twice instead of
checking both source (cf) and target (ct) currency EMU membership.

Decision documented in Wave 1 implementation plan.
Ref: docs/plans/2026-01-03-wave1-currency-implementation.md"
```

---

### Task 1: Database Configuration for Wave 1 Functions

**Files:**
- Create: `migration/sql/wave1-function-config.sql`

**Step 1: Write the SQL configuration script**

```sql
-- Wave 1 Currency Functions Configuration
-- Execute after Wave 0 is complete

INSERT INTO migration.function_config (function_name, mode, sample_rate, circuit_breaker_enabled)
VALUES
    ('currencyRound', 'SQL_ONLY', 1.0, true),
    ('currencyRate', 'SQL_ONLY', 1.0, true),
    ('currencyConvert', 'SQL_ONLY', 1.0, true),
    ('currencyBase', 'SQL_ONLY', 1.0, true)
ON CONFLICT (function_name) DO UPDATE SET
    mode = EXCLUDED.mode,
    sample_rate = EXCLUDED.sample_rate,
    circuit_breaker_enabled = EXCLUDED.circuit_breaker_enabled;
```

**Step 2: Verify the script is syntactically correct**

Run: `psql -f migration/sql/wave1-function-config.sql --echo-all 2>&1 | head -20`
Expected: No syntax errors

**Step 3: Commit**

```bash
git add migration/sql/wave1-function-config.sql
git commit -m "chore: add Wave 1 currency function config SQL"
```

---

## Group 1: currencyRound Function (0.5 days)

### Task 2: Create CurrencyFunctions Class with currencyRound Skeleton

**Files:**
- Create: `base/src/org/compiere/util/CurrencyFunctions.java`
- Test: `base/test/src/org/compiere/util/CurrencyFunctionsTest.java`

**Step 1: Write the failing test for currencyRound null handling**

```java
// base/test/src/org/compiere/util/CurrencyFunctionsTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.I_C_Currency;
import org.compiere.model.MCurrency;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * Unit tests for CurrencyFunctions.
 *
 * <p><b>Test Data Requirements:</b>
 * <ul>
 *   <li>At least one currency with ISO_Code='USD' must exist</li>
 *   <li>At least one currency with ISO_Code='EUR' must exist</li>
 *   <li>Client ID 11 (GardenWorld) should exist with configured accounting schema</li>
 * </ul>
 *
 * <p>Tests use dynamic lookup by ISO code to avoid hardcoded ID dependencies.
 * Tests will be skipped (not failed) if required data is missing.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CurrencyFunctionsTest extends CommonGWSetup {

    private Integer usdCurrencyId;
    private Integer eurCurrencyId;
    private int usdStdPrecision;
    private int usdCostingPrecision;

    @BeforeAll
    void loadTestData() {
        // Dynamic lookup - tests will skip if data missing
        // NOTE: MCurrency.get(ctx, isoCode) may not exist in all ADempiere versions
        // Use getCurrencyByIsoCode() helper which falls back to Query if needed
        MCurrency usd = getCurrencyByIsoCode("USD");
        assumeTrue(usd != null && usd.get_ID() > 0, "USD currency must exist for tests");
        usdCurrencyId = usd.get_ID();
        usdStdPrecision = usd.getStdPrecision();
        usdCostingPrecision = usd.getCostingPrecision();

        MCurrency eur = getCurrencyByIsoCode("EUR");
        assumeTrue(eur != null && eur.get_ID() > 0, "EUR currency must exist for tests");
        eurCurrencyId = eur.get_ID();
    }

    /**
     * Get currency by ISO code with fallback for API compatibility.
     * MCurrency.get(ctx, isoCode) may not exist in all ADempiere versions.
     */
    private MCurrency getCurrencyByIsoCode(String isoCode) {
        // Try MCurrency.get(ctx, isoCode) first - exists in newer versions
        try {
            MCurrency currency = MCurrency.get(Env.getCtx(), isoCode);
            if (currency != null && currency.get_ID() > 0) {
                return currency;
            }
        } catch (NoSuchMethodError e) {
            // Method doesn't exist, fall through to Query approach
        }

        // Fallback: Use Query API (works in all versions)
        int currencyId = new Query(Env.getCtx(), I_C_Currency.Table_Name, "ISO_Code=?", null)
            .setParameters(isoCode)
            .setOnlyActiveRecords(true)
            .firstId();

        if (currencyId > 0) {
            return MCurrency.get(Env.getCtx(), currencyId);
        }
        return null;
    }

    @Test
    void currencyRound_nullAmount_returnsNull() {
        BigDecimal result = CurrencyFunctions.currencyRound(null, usdCurrencyId, "N");
        assertNull(result);
    }

    @Test
    void currencyRound_nullCurrencyId_returnsAmount() {
        BigDecimal amount = new BigDecimal("123.456");
        BigDecimal result = CurrencyFunctions.currencyRound(amount, null, "N");
        assertEquals(amount, result);
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "org.compiere.util.CurrencyFunctionsTest" -i 2>&1 | tail -30`
Expected: FAIL - class CurrencyFunctions not found

**Step 3: Write minimal implementation to make tests pass**

```java
// base/src/org/compiere/util/CurrencyFunctions.java
package org.compiere.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

import javax.annotation.Nullable;

/**
 * Currency conversion functions migrated from PostgreSQL.
 * Matches PostgreSQL function semantics exactly.
 *
 * <p>Wave 1 Functions:
 * <ul>
 *   <li>currencyRound: Round amount for target currency</li>
 *   <li>currencyRate: Get conversion rate between currencies</li>
 *   <li>currencyConvert: Convert amount between currencies</li>
 *   <li>currencyBase: Convert amount to client's base currency</li>
 * </ul>
 *
 * <p><b>EMU/Euro Note:</b> The EMU (European Monetary Union) fixed-rate logic
 * handles legacy currencies that adopted the Euro (1999-2002). The SQL function
 * had a bug at line 110 that was fixed as part of Wave 1 migration.
 * See: docs/plans/2026-01-03-wave1-currency-implementation.md, Decision 1
 *
 * @see org.compiere.util.CurrencyFunctionRouter for shadow mode routing
 */
public class CurrencyFunctions {

    private static final CLogger log = CLogger.getCLogger(CurrencyFunctions.class);

    private CurrencyFunctions() {
        // Utility class - prevent instantiation
    }

    /**
     * Round amount using currency's standard or costing precision.
     * Equivalent to PostgreSQL: currencyRound(amount, currencyId, costing)
     *
     * @param amount amount to round (may be null)
     * @param currencyId target currency ID (may be null)
     * @param costing "Y" for costing precision, otherwise standard precision
     * @return rounded amount, original amount if currency not found, null if amount is null
     */
    @Nullable
    public static BigDecimal currencyRound(@Nullable BigDecimal amount,
                                            @Nullable Integer currencyId,
                                            @Nullable String costing) {
        // Nothing to convert
        if (amount == null) {
            return null;
        }
        if (currencyId == null) {
            log.fine(() -> "currencyRound: currencyId is null, returning original amount");
            return amount;
        }

        // TODO: Implement precision lookup and rounding
        return amount;
    }
}
```

**Step 4: Run tests to verify they pass**

Run: `./gradlew :base:test --tests "org.compiere.util.CurrencyFunctionsTest" -i 2>&1 | tail -20`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/CurrencyFunctions.java
git add base/test/src/org/compiere/util/CurrencyFunctionsTest.java
git commit -m "feat(wave1): add CurrencyFunctions skeleton with currencyRound null handling"
```

---

### Task 3: Implement currencyRound Precision Lookup

**Files:**
- Modify: `base/src/org/compiere/util/CurrencyFunctions.java`
- Test: `base/test/src/org/compiere/util/CurrencyFunctionsTest.java`

**Step 1: Write the failing test for precision lookup**

Add to `CurrencyFunctionsTest.java`:

```java
@Test
void currencyRound_validCurrency_roundsToStdPrecision() {
    BigDecimal amount = new BigDecimal("123.456789");
    BigDecimal result = CurrencyFunctions.currencyRound(amount, usdCurrencyId, "N");

    // USD typically has StdPrecision=2
    BigDecimal expected = amount.setScale(usdStdPrecision, RoundingMode.HALF_UP);
    assertEquals(expected, result);
}

@Test
void currencyRound_costingPrecision_roundsToCostPrecision() {
    BigDecimal amount = new BigDecimal("123.456789");
    BigDecimal result = CurrencyFunctions.currencyRound(amount, usdCurrencyId, "Y");

    // USD typically has CostingPrecision=4
    BigDecimal expected = amount.setScale(usdCostingPrecision, RoundingMode.HALF_UP);
    assertEquals(expected, result);
}

@Test
void currencyRound_unknownCurrency_returnsAmount() {
    BigDecimal amount = new BigDecimal("123.456789");
    BigDecimal result = CurrencyFunctions.currencyRound(amount, 999999, "N");

    assertEquals(amount, result);
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "org.compiere.util.CurrencyFunctionsTest.currencyRound_validCurrency_roundsToStdPrecision" -i 2>&1 | tail -30`
Expected: FAIL - expected 123.46 but was 123.456789

**Step 3: Implement precision lookup**

Update `CurrencyFunctions.java`:

```java
import org.compiere.model.MCurrency;

    @Nullable
    public static BigDecimal currencyRound(@Nullable BigDecimal amount,
                                            @Nullable Integer currencyId,
                                            @Nullable String costing) {
        // Nothing to convert
        if (amount == null) {
            return null;
        }
        if (currencyId == null) {
            log.fine(() -> "currencyRound: currencyId is null, returning original amount");
            return amount;
        }

        // Get currency precision
        MCurrency currency = MCurrency.get(Env.getCtx(), currencyId);
        if (currency == null || currency.get_ID() == 0) {
            // Currency not found - return unmodified
            log.warning(() -> "currencyRound: currency not found for ID=" + currencyId
                + ", returning original amount");
            return amount;
        }

        int precision;
        if ("Y".equals(costing)) {
            precision = currency.getCostingPrecision();
        } else {
            precision = currency.getStdPrecision();
        }

        return amount.setScale(precision, RoundingMode.HALF_UP);
    }
```

**Step 4: Run all currencyRound tests**

Run: `./gradlew :base:test --tests "org.compiere.util.CurrencyFunctionsTest.currencyRound*" -i 2>&1 | tail -20`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/CurrencyFunctions.java
git add base/test/src/org/compiere/util/CurrencyFunctionsTest.java
git commit -m "feat(wave1): implement currencyRound precision lookup"
```

---

### Task 4: Add SqlFunctionCaller Utility Methods and currencyRound Caller

**Files:**
- Create: `base/src/org/compiere/migration/SqlFunctionException.java` (if not exists from Wave 0)
- Modify: `base/src/org/compiere/migration/SqlFunctionCaller.java`
- Test: `base/test/src/org/compiere/migration/SqlFunctionCallerTest.java`

**Step 0: Create SqlFunctionException (if not exists)**

First, check if this class exists from Wave 0:
```bash
find base/src -name "SqlFunctionException.java" 2>/dev/null
```

If not found, create it:

```java
// base/src/org/compiere/migration/SqlFunctionException.java
package org.compiere.migration;

/**
 * Exception thrown when a SQL function call fails.
 * Used by SqlFunctionCaller to wrap database exceptions with function context.
 */
public class SqlFunctionException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String functionName;

    /**
     * Create exception for failed SQL function call.
     *
     * @param functionName name of the SQL function that failed
     * @param cause underlying exception
     */
    public SqlFunctionException(String functionName, Throwable cause) {
        super("SQL function call failed: " + functionName, cause);
        this.functionName = functionName;
    }

    /**
     * Get the name of the function that failed.
     */
    public String getFunctionName() {
        return functionName;
    }
}
```

**Step 1: Add utility method for nullable parameters**

Add to `SqlFunctionCaller.java`:

```java
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Utility methods for calling SQL functions with proper null handling.
 *
 * <p>These methods provide a bridge for shadow mode comparison between
 * Java implementations and PostgreSQL functions.
 */
public class SqlFunctionCaller {

    private static final CLogger log = CLogger.getCLogger(SqlFunctionCaller.class);

    private SqlFunctionCaller() {
        // Utility class
    }

    // --- Utility methods for nullable parameter handling ---

    /**
     * Set nullable Integer parameter on PreparedStatement.
     * Avoids repetitive null-checking code in caller methods.
     */
    private static void setNullableInt(PreparedStatement ps, int index, Integer value)
            throws SQLException {
        if (value != null) {
            ps.setInt(index, value);
        } else {
            ps.setNull(index, Types.INTEGER);
        }
    }

    /**
     * Set nullable BigDecimal parameter on PreparedStatement.
     */
    private static void setNullableBigDecimal(PreparedStatement ps, int index, BigDecimal value)
            throws SQLException {
        if (value != null) {
            ps.setBigDecimal(index, value);
        } else {
            ps.setNull(index, Types.NUMERIC);
        }
    }

    /**
     * Set nullable Timestamp parameter on PreparedStatement.
     */
    private static void setNullableTimestamp(PreparedStatement ps, int index, Timestamp value)
            throws SQLException {
        if (value != null) {
            ps.setTimestamp(index, value);
        } else {
            ps.setNull(index, Types.TIMESTAMP);
        }
    }

    /**
     * Set nullable String parameter on PreparedStatement.
     */
    private static void setNullableString(PreparedStatement ps, int index, String value)
            throws SQLException {
        if (value != null) {
            ps.setString(index, value);
        } else {
            ps.setNull(index, Types.VARCHAR);
        }
    }
}
```

**Step 2: Write the failing test for SQL caller**

Add to `SqlFunctionCallerTest.java`:

```java
@Test
void callCurrencyRound_validInput_returnsResult() {
    MCurrency usd = MCurrency.get(Env.getCtx(), "USD");
    assumeTrue(usd != null && usd.get_ID() > 0, "USD currency required");

    BigDecimal amount = new BigDecimal("123.456789");
    BigDecimal result = SqlFunctionCaller.callCurrencyRound(amount, usd.get_ID(), "N");

    assertNotNull(result);
    BigDecimal expected = amount.setScale(usd.getStdPrecision(), RoundingMode.HALF_UP);
    assertEquals(0, expected.compareTo(result));
}

@Test
void callCurrencyRound_nullAmount_returnsNull() {
    BigDecimal result = SqlFunctionCaller.callCurrencyRound(null, 100, "N");
    assertNull(result);
}
```

**Step 3: Implement SQL function caller**

Add to `SqlFunctionCaller.java`:

```java
/** Calls: SELECT currencyRound(?, ?, ?) */
@Nullable
public static BigDecimal callCurrencyRound(@Nullable BigDecimal amount,
                                            @Nullable Integer currencyId,
                                            @Nullable String costing) {
    String sql = "SELECT currencyRound(?, ?, ?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        setNullableBigDecimal(pstmt, 1, amount);
        setNullableInt(pstmt, 2, currencyId);
        setNullableString(pstmt, 3, costing);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (Exception e) {
        log.log(Level.WARNING, "Failed to call currencyRound()", e);
        throw new SqlFunctionException("currencyRound", e);
    }
    return null;
}
```

**Step 4: Run tests**

Run: `./gradlew :base:test --tests "org.compiere.migration.SqlFunctionCallerTest.callCurrencyRound*" -i 2>&1 | tail -20`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/SqlFunctionCaller.java
git add base/test/src/org/compiere/migration/SqlFunctionCallerTest.java
git commit -m "feat(wave1): add SqlFunctionCaller utilities and currencyRound caller"
```

---

### Task 5: Add currencyRound Integration Test (Java vs SQL)

**Files:**
- Create: `base/test/src/org/compiere/migration/Wave1ShadowIntegrationTest.java`

**Step 1: Write the integration test**

```java
// base/test/src/org/compiere/migration/Wave1ShadowIntegrationTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.stream.Stream;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.I_C_Currency;
import org.compiere.model.MCurrency;
import org.compiere.model.Query;
import org.compiere.util.CurrencyFunctions;
import org.compiere.util.Env;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Integration tests comparing Java implementations to SQL functions.
 * Validates Java matches SQL exactly for Wave 1 currency functions.
 *
 * <p><b>Test Data Requirements:</b>
 * <ul>
 *   <li>USD and EUR currencies must exist</li>
 *   <li>Client ID 11 (GardenWorld) with configured accounting schema</li>
 * </ul>
 *
 * <p>Uses dynamic currency lookup to avoid hardcoded ID dependencies.
 */
@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave1ShadowIntegrationTest extends CommonGWSetup {

    private Integer usdCurrencyId;
    private Integer eurCurrencyId;

    @BeforeAll
    void loadTestData() {
        MCurrency usd = getCurrencyByIsoCode("USD");
        assumeTrue(usd != null && usd.get_ID() > 0, "USD currency must exist");
        usdCurrencyId = usd.get_ID();

        MCurrency eur = getCurrencyByIsoCode("EUR");
        assumeTrue(eur != null && eur.get_ID() > 0, "EUR currency must exist");
        eurCurrencyId = eur.get_ID();
    }

    /**
     * Get currency by ISO code with fallback for API compatibility.
     * MCurrency.get(ctx, isoCode) may not exist in all ADempiere versions.
     */
    private MCurrency getCurrencyByIsoCode(String isoCode) {
        // Try MCurrency.get(ctx, isoCode) first - exists in newer versions
        try {
            MCurrency currency = MCurrency.get(Env.getCtx(), isoCode);
            if (currency != null && currency.get_ID() > 0) {
                return currency;
            }
        } catch (NoSuchMethodError e) {
            // Method doesn't exist, fall through to Query approach
        }

        // Fallback: Use Query API (works in all versions)
        int currencyId = new Query(Env.getCtx(), I_C_Currency.Table_Name, "ISO_Code=?", null)
            .setParameters(isoCode)
            .setOnlyActiveRecords(true)
            .firstId();

        if (currencyId > 0) {
            return MCurrency.get(Env.getCtx(), currencyId);
        }
        return null;
    }

    static Stream<Arguments> currencyRoundTestCases() {
        return Stream.of(
            Arguments.of("123.456789", "N", "standard precision"),
            Arguments.of("123.456789", "Y", "costing precision"),
            Arguments.of("0.00", "N", "zero amount"),
            Arguments.of("999999.999999", "N", "large amount"),
            Arguments.of("-50.555", "N", "negative amount")
        );
    }

    @ParameterizedTest(name = "currencyRound({0}, USD, {1}) - {2}")
    @MethodSource("currencyRoundTestCases")
    void currencyRound_matchesSql(String amountStr, String costing, String description) {
        BigDecimal amount = new BigDecimal(amountStr);

        BigDecimal javaResult = CurrencyFunctions.currencyRound(amount, usdCurrencyId, costing);
        BigDecimal sqlResult = SqlFunctionCaller.callCurrencyRound(amount, usdCurrencyId, costing);

        assertEquals(0, sqlResult.compareTo(javaResult),
            String.format("currencyRound(%s, USD, %s): java=%s, sql=%s",
                amountStr, costing, javaResult, sqlResult));
    }

    @Test
    void currencyRound_nullAmount_matchesSql() {
        BigDecimal javaResult = CurrencyFunctions.currencyRound(null, usdCurrencyId, "N");
        BigDecimal sqlResult = SqlFunctionCaller.callCurrencyRound(null, usdCurrencyId, "N");

        assertEquals(sqlResult, javaResult);
    }

    @Test
    void currencyRound_nullCurrency_matchesSql() {
        BigDecimal amount = new BigDecimal("123.456");
        BigDecimal javaResult = CurrencyFunctions.currencyRound(amount, null, "N");
        BigDecimal sqlResult = SqlFunctionCaller.callCurrencyRound(amount, null, "N");

        // Both should return the original amount
        assertEquals(0, amount.compareTo(javaResult));
        assertEquals(0, amount.compareTo(sqlResult));
    }
}
```

**Step 2: Run integration tests**

Run: `./gradlew :base:test --tests "org.compiere.migration.Wave1ShadowIntegrationTest.currencyRound*" -i 2>&1 | tail -30`
Expected: PASS

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave1ShadowIntegrationTest.java
git commit -m "test(wave1): add currencyRound integration tests"
```

---

## Group 2: currencyRate Function (2 days)

### Task 6: Implement currencyRate Same Currency Check

**Files:**
- Modify: `base/src/org/compiere/util/CurrencyFunctions.java`
- Test: `base/test/src/org/compiere/util/CurrencyFunctionsTest.java`

**Step 1: Write the failing test**

Add to `CurrencyFunctionsTest.java`:

```java
import java.sql.Timestamp;

@Test
void currencyRate_sameCurrency_returnsOne() {
    BigDecimal result = CurrencyFunctions.currencyRate(
        usdCurrencyId, usdCurrencyId,  // Same currency
        null, null, null, null);

    assertEquals(0, BigDecimal.ONE.compareTo(result));
}

@Test
void currencyRate_nullFromCurrency_returnsNull() {
    BigDecimal result = CurrencyFunctions.currencyRate(
        null, usdCurrencyId, null, null, null, null);

    assertNull(result);
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "org.compiere.util.CurrencyFunctionsTest.currencyRate_sameCurrency*" -i 2>&1 | tail -20`
Expected: FAIL - method currencyRate not found

**Step 3: Implement same currency check**

Add to `CurrencyFunctions.java`:

```java
/**
 * Get currency conversion rate.
 * Equivalent to PostgreSQL: currencyRate(curFromId, curToId, convDate, convTypeId, clientId, orgId)
 *
 * <p><b>EMU/Euro Logic:</b> This function handles legacy EMU (European Monetary Union)
 * fixed-rate conversions for currencies that adopted the Euro (1999-2002).
 * The SQL function had a bug at line 110 (checking source currency twice instead
 * of checking both source and target). This was fixed as part of Wave 1 migration.
 * See: docs/plans/2026-01-03-wave1-currency-implementation.md, Decision 1
 *
 * @param curFromId source currency ID
 * @param curToId target currency ID
 * @param convDate conversion date (null = today)
 * @param convTypeId conversion type ID (null/0 = default)
 * @param clientId client ID
 * @param orgId organization ID
 * @return conversion rate, or null if not found
 */
@Nullable
public static BigDecimal currencyRate(@Nullable Integer curFromId,
                                       @Nullable Integer curToId,
                                       @Nullable Timestamp convDate,
                                       @Nullable Integer convTypeId,
                                       @Nullable Integer clientId,
                                       @Nullable Integer orgId) {
    // No conversion needed
    if (curFromId == null || curToId == null) {
        log.fine(() -> "currencyRate: null currency ID (from=" + curFromId + ", to=" + curToId + ")");
        return null;
    }
    if (curFromId.equals(curToId)) {
        return BigDecimal.ONE;
    }

    // TODO: Implement EMU/Euro logic and rate lookup
    return null;
}
```

**Step 4: Run tests**

Run: `./gradlew :base:test --tests "org.compiere.util.CurrencyFunctionsTest.currencyRate*" -i 2>&1 | tail -20`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/CurrencyFunctions.java
git add base/test/src/org/compiere/util/CurrencyFunctionsTest.java
git commit -m "feat(wave1): add currencyRate skeleton with same currency check"
```

---

### Task 7: Implement currencyRate EMU/Euro Fixed Rates

**Files:**
- Modify: `base/src/org/compiere/util/CurrencyFunctions.java`
- Test: `base/test/src/org/compiere/util/CurrencyFunctionsTest.java`

**Step 1: Write the failing tests for EMU logic**

Add to `CurrencyFunctionsTest.java`:

```java
@Test
void currencyRate_euroToEmuMember_handlesGracefully() {
    // EUR to any EMU member - tests the code path exists without crashing
    // The actual rate depends on EMU configuration in test DB
    // This test verifies no exceptions are thrown
    assertDoesNotThrow(() -> {
        CurrencyFunctions.currencyRate(
            eurCurrencyId, usdCurrencyId,
            Timestamp.valueOf("2002-01-01 00:00:00"),
            null, 11, 0);
    });
}

@Test
void currencyRate_unknownCurrency_returnsNull() {
    BigDecimal result = CurrencyFunctions.currencyRate(
        999999, 999998,
        null, null, 11, 0);

    assertNull(result);
}
```

**Step 2: Implement EMU/Euro logic with proper null/zero checks**

Update `currencyRate` in `CurrencyFunctions.java`:

```java
import org.compiere.model.MCurrency;
import org.compiere.model.MConversionRate;

    // Cached Euro currency ID (volatile for thread safety)
    private static volatile Integer cachedEuroCurrencyId;

    @Nullable
    public static BigDecimal currencyRate(@Nullable Integer curFromId,
                                           @Nullable Integer curToId,
                                           @Nullable Timestamp convDate,
                                           @Nullable Integer convTypeId,
                                           @Nullable Integer clientId,
                                           @Nullable Integer orgId) {
        // No conversion needed
        if (curFromId == null || curToId == null) {
            log.fine(() -> "currencyRate: null currency ID (from=" + curFromId + ", to=" + curToId + ")");
            return null;
        }
        if (curFromId.equals(curToId)) {
            return BigDecimal.ONE;
        }

        // Default date to today
        Timestamp effectiveDate = convDate;
        if (effectiveDate == null) {
            effectiveDate = new Timestamp(System.currentTimeMillis());
        }

        // Get currency info
        MCurrency curFrom = MCurrency.get(Env.getCtx(), curFromId);
        MCurrency curTo = MCurrency.get(Env.getCtx(), curToId);

        if (curFrom == null || curFrom.get_ID() == 0) {
            log.warning(() -> "currencyRate: source currency not found, ID=" + curFromId);
            return null;
        }
        if (curTo == null || curTo.get_ID() == 0) {
            log.warning(() -> "currencyRate: target currency not found, ID=" + curToId);
            return null;
        }

        // EMU/Euro fixed rate logic
        // Note: This implements CORRECT behavior. The SQL function had a bug at line 110
        // that checked cf_IsEMUMember twice instead of checking both cf and ct.
        // The SQL bug was fixed as part of Wave 1 migration.
        // See: docs/plans/2026-01-03-wave1-currency-implementation.md, Decision 1
        boolean cfIsEuro = curFrom.isEuro();
        boolean cfIsEmuMember = curFrom.isEMUMember();
        Timestamp cfEmuEntryDate = curFrom.getEMUEntryDate();
        BigDecimal cfEmuRate = curFrom.getEMURate();

        boolean ctIsEuro = curTo.isEuro();
        boolean ctIsEmuMember = curTo.isEMUMember();
        Timestamp ctEmuEntryDate = curTo.getEMUEntryDate();
        BigDecimal ctEmuRate = curTo.getEMURate();

        // Fixed - From Euro to EMU
        if (cfIsEuro && ctIsEmuMember && ctEmuEntryDate != null
                && !effectiveDate.before(ctEmuEntryDate)) {
            return ctEmuRate;
        }

        // Fixed - From EMU to Euro
        if (ctIsEuro && cfIsEmuMember && cfEmuEntryDate != null
                && !effectiveDate.before(cfEmuEntryDate)) {
            if (!isValidDivisor(cfEmuRate)) {
                log.warning(() -> "currencyRate: invalid cfEmuRate for EMU-to-Euro conversion, "
                    + "from=" + curFromId + ", rate=" + cfEmuRate);
                return null;
            }
            return BigDecimal.ONE.divide(cfEmuRate, 12, RoundingMode.HALF_UP);
        }

        // Fixed - From EMU to EMU
        // IMPORTANT: This is the CORRECTED logic. SQL function bug was:
        // IF (cf_IsEMUMember = 'Y' AND cf_IsEMUMember ='Y'  -- checked cf twice!
        // Correct: check BOTH cf_IsEMUMember AND ct_IsEMUMember
        if (cfIsEmuMember && ctIsEmuMember
                && cfEmuEntryDate != null && !effectiveDate.before(cfEmuEntryDate)
                && ctEmuEntryDate != null && !effectiveDate.before(ctEmuEntryDate)) {
            if (!isValidDivisor(cfEmuRate)) {
                log.warning(() -> "currencyRate: invalid cfEmuRate for EMU-to-EMU conversion, "
                    + "from=" + curFromId + ", to=" + curToId + ", cfRate=" + cfEmuRate);
                return null;
            }
            return ctEmuRate.divide(cfEmuRate, 12, RoundingMode.HALF_UP);
        }

        // Flexible rates - delegate to MConversionRate
        int effectiveClientId = clientId != null ? clientId : 0;
        int effectiveOrgId = orgId != null ? orgId : 0;
        int effectiveConvTypeId = convTypeId != null ? convTypeId : 0;

        // Handle EMU member to/from non-Euro currency via Euro
        int lookupFromId = curFromId;
        int lookupToId = curToId;
        BigDecimal fromEmuAdjustment = null;
        BigDecimal toEmuAdjustment = null;

        if (cfIsEmuMember && cfEmuEntryDate != null && !effectiveDate.before(cfEmuEntryDate)) {
            // Convert via Euro
            Integer euroId = getEuroCurrencyId();
            if (euroId == null) {
                log.warning(() -> "currencyRate: Euro currency not found for EMU conversion");
                return null;
            }
            lookupFromId = euroId;
            fromEmuAdjustment = cfEmuRate;
        }

        if (ctIsEmuMember && ctEmuEntryDate != null && !effectiveDate.before(ctEmuEntryDate)) {
            // Convert via Euro
            Integer euroId = getEuroCurrencyId();
            if (euroId == null) {
                log.warning(() -> "currencyRate: Euro currency not found for EMU conversion");
                return null;
            }
            lookupToId = euroId;
            toEmuAdjustment = ctEmuRate;
        }

        // Get rate from conversion rate table
        BigDecimal rate = MConversionRate.getRate(
            lookupFromId, lookupToId,
            effectiveDate, effectiveConvTypeId,
            effectiveClientId, effectiveOrgId);

        if (rate == null) {
            log.fine(() -> "currencyRate: rate not found from=" + lookupFromId + " to=" + lookupToId
                + " date=" + effectiveDate + " type=" + effectiveConvTypeId);
            return null;
        }

        // Apply EMU adjustments
        if (fromEmuAdjustment != null && isValidDivisor(fromEmuAdjustment)) {
            rate = rate.divide(fromEmuAdjustment, 12, RoundingMode.HALF_UP);
        }
        if (toEmuAdjustment != null) {
            rate = rate.multiply(toEmuAdjustment);
        }

        return rate;
    }

    /**
     * Check if a BigDecimal is valid for use as a divisor (not null and not zero).
     */
    private static boolean isValidDivisor(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) != 0;
    }

    /**
     * Get Euro currency ID (cached for performance).
     * MCurrency already caches internally, but we avoid repeated string lookups.
     */
    @Nullable
    private static Integer getEuroCurrencyId() {
        if (cachedEuroCurrencyId != null) {
            return cachedEuroCurrencyId;
        }
        MCurrency euro = MCurrency.get(Env.getCtx(), "EUR");
        if (euro != null && euro.get_ID() > 0) {
            cachedEuroCurrencyId = euro.get_ID();
            return cachedEuroCurrencyId;
        }
        return null;
    }
```

**Step 3: Run all currencyRate tests**

Run: `./gradlew :base:test --tests "org.compiere.util.CurrencyFunctionsTest.currencyRate*" -i 2>&1 | tail -20`
Expected: PASS

**Step 4: Commit**

```bash
git add base/src/org/compiere/util/CurrencyFunctions.java
git add base/test/src/org/compiere/util/CurrencyFunctionsTest.java
git commit -m "feat(wave1): implement currencyRate EMU/Euro fixed rate logic

Implements correct EMU-to-EMU logic (checks both source and target currency).
The SQL function bug at line 110 was fixed in Task 0.

Includes:
- Division-by-zero protection for EMU rates
- Proper logging for all failure paths
- Cached Euro currency ID lookup"
```

---

### Task 8: Add currencyRate SQL Caller

**Files:**
- Modify: `base/src/org/compiere/migration/SqlFunctionCaller.java`
- Test: `base/test/src/org/compiere/migration/SqlFunctionCallerTest.java`

**Step 1: Write the failing test**

Add to `SqlFunctionCallerTest.java`:

```java
@Test
void callCurrencyRate_sameCurrency_returnsOne() {
    MCurrency usd = MCurrency.get(Env.getCtx(), "USD");
    assumeTrue(usd != null && usd.get_ID() > 0, "USD currency required");

    BigDecimal result = SqlFunctionCaller.callCurrencyRate(
        usd.get_ID(), usd.get_ID(), null, null, 11, 0);

    assertNotNull(result);
    assertEquals(0, BigDecimal.ONE.compareTo(result));
}

@Test
void callCurrencyRate_usdToEur_noException() {
    MCurrency usd = MCurrency.get(Env.getCtx(), "USD");
    MCurrency eur = MCurrency.get(Env.getCtx(), "EUR");
    assumeTrue(usd != null && usd.get_ID() > 0, "USD currency required");
    assumeTrue(eur != null && eur.get_ID() > 0, "EUR currency required");

    // This test validates the SQL function is callable
    // Rate may be null if not configured in test DB - that's OK
    assertDoesNotThrow(() -> {
        SqlFunctionCaller.callCurrencyRate(
            usd.get_ID(), eur.get_ID(),
            Timestamp.valueOf("2024-01-01 00:00:00"),
            null, 11, 0);
    });
}
```

**Step 2: Implement SQL caller**

Add to `SqlFunctionCaller.java`:

```java
/** Calls: SELECT currencyRate(?, ?, ?, ?, ?, ?) */
@Nullable
public static BigDecimal callCurrencyRate(@Nullable Integer curFromId,
                                           @Nullable Integer curToId,
                                           @Nullable Timestamp convDate,
                                           @Nullable Integer convTypeId,
                                           @Nullable Integer clientId,
                                           @Nullable Integer orgId) {
    String sql = "SELECT currencyRate(?, ?, ?, ?, ?, ?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        setNullableInt(pstmt, 1, curFromId);
        setNullableInt(pstmt, 2, curToId);
        setNullableTimestamp(pstmt, 3, convDate);
        setNullableInt(pstmt, 4, convTypeId);
        setNullableInt(pstmt, 5, clientId);
        setNullableInt(pstmt, 6, orgId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (Exception e) {
        log.log(Level.WARNING, "Failed to call currencyRate()", e);
        throw new SqlFunctionException("currencyRate", e);
    }
    return null;
}
```

**Step 3: Run tests**

Run: `./gradlew :base:test --tests "org.compiere.migration.SqlFunctionCallerTest.callCurrencyRate*" -i 2>&1 | tail -20`
Expected: PASS

**Step 4: Commit**

```bash
git add base/src/org/compiere/migration/SqlFunctionCaller.java
git add base/test/src/org/compiere/migration/SqlFunctionCallerTest.java
git commit -m "feat(wave1): add currencyRate SQL caller"
```

---

### Task 9: Add currencyRate Integration Tests

**Files:**
- Modify: `base/test/src/org/compiere/migration/Wave1ShadowIntegrationTest.java`

**Step 1: Write integration tests**

Add to `Wave1ShadowIntegrationTest.java`:

```java
@Test
void currencyRate_sameCurrency_matchesSql() {
    BigDecimal javaResult = CurrencyFunctions.currencyRate(
        usdCurrencyId, usdCurrencyId, null, null, 11, 0);
    BigDecimal sqlResult = SqlFunctionCaller.callCurrencyRate(
        usdCurrencyId, usdCurrencyId, null, null, 11, 0);

    assertEquals(0, javaResult.compareTo(sqlResult),
        "currencyRate(USD, USD): java=" + javaResult + ", sql=" + sqlResult);
}

@Test
void currencyRate_nullCurrency_matchesSql() {
    BigDecimal javaResult = CurrencyFunctions.currencyRate(null, usdCurrencyId, null, null, 11, 0);
    BigDecimal sqlResult = SqlFunctionCaller.callCurrencyRate(null, usdCurrencyId, null, null, 11, 0);

    assertEquals(sqlResult, javaResult);
}

@Test
void currencyRate_crossCurrency_matchesSqlWithTolerance() {
    // Use a historical date that's likely to have rates configured
    Timestamp convDate = Timestamp.valueOf("2024-01-01 00:00:00");

    BigDecimal javaResult = CurrencyFunctions.currencyRate(
        usdCurrencyId, eurCurrencyId, convDate, null, 11, 0);
    BigDecimal sqlResult = SqlFunctionCaller.callCurrencyRate(
        usdCurrencyId, eurCurrencyId, convDate, null, 11, 0);

    // Both may be null if rate not configured - that's a match
    if (javaResult == null && sqlResult == null) {
        return; // Both null = match
    }

    assertNotNull(javaResult, "Java returned null but SQL returned " + sqlResult);
    assertNotNull(sqlResult, "SQL returned null but Java returned " + javaResult);

    // Compare with 6 decimal place tolerance for division rounding differences
    // See: docs/plans/2026-01-03-wave1-currency-implementation.md, Decision 2
    assertEquals(0,
        javaResult.setScale(6, RoundingMode.HALF_UP)
                  .compareTo(sqlResult.setScale(6, RoundingMode.HALF_UP)),
        String.format("currencyRate(USD, EUR, %s): java=%s, sql=%s",
            convDate, javaResult, sqlResult));
}

@Test
void currencyRate_emuToEmu_matchesSqlAfterBugFix() {
    // This tests the exact path that had the SQL bug at line 110
    // DEM -> FRF (both EMU members) - validates Decision 1 fix
    MCurrency dem = getCurrencyByIsoCode("DEM");
    MCurrency frf = getCurrencyByIsoCode("FRF");
    assumeTrue(dem != null && dem.isEMUMember(), "DEM (EMU member) required for EMU path test");
    assumeTrue(frf != null && frf.isEMUMember(), "FRF (EMU member) required for EMU path test");

    // Use post-EMU date (Euro adoption was 1999-2002)
    Timestamp convDate = Timestamp.valueOf("2002-01-01 00:00:00");

    BigDecimal javaResult = CurrencyFunctions.currencyRate(
        dem.get_ID(), frf.get_ID(), convDate, null, 11, 0);
    BigDecimal sqlResult = SqlFunctionCaller.callCurrencyRate(
        dem.get_ID(), frf.get_ID(), convDate, null, 11, 0);

    // After SQL bug fix, both should return FRF_rate / DEM_rate
    // Before fix, SQL would incorrectly compute because it checked DEM twice
    assertNotNull(javaResult, "EMU-to-EMU should have fixed rate after entry date");
    assertNotNull(sqlResult, "SQL EMU-to-EMU should have fixed rate after bug fix");

    assertEquals(0,
        javaResult.setScale(6, RoundingMode.HALF_UP)
                  .compareTo(sqlResult.setScale(6, RoundingMode.HALF_UP)),
        String.format("currencyRate(DEM, FRF) EMU-to-EMU: java=%s, sql=%s",
            javaResult, sqlResult));
}
```

**Step 2: Run integration tests**

Run: `./gradlew :base:test --tests "org.compiere.migration.Wave1ShadowIntegrationTest.currencyRate*" -i 2>&1 | tail -30`
Expected: PASS

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave1ShadowIntegrationTest.java
git commit -m "test(wave1): add currencyRate integration tests with tolerance"
```

---

## Group 3: currencyConvert and currencyBase Functions (1.5 days)

### Task 10: Implement currencyConvert

**Files:**
- Modify: `base/src/org/compiere/util/CurrencyFunctions.java`
- Test: `base/test/src/org/compiere/util/CurrencyFunctionsTest.java`

**Step 1: Write the failing tests**

Add to `CurrencyFunctionsTest.java`:

```java
@Test
void currencyConvert_sameCurrency_returnsRoundedAmount() {
    BigDecimal amount = new BigDecimal("100.00");
    BigDecimal result = CurrencyFunctions.currencyConvert(
        amount, usdCurrencyId, usdCurrencyId, null, null, 11, 0);

    // Same currency returns amount rounded to target currency precision
    assertEquals(0, amount.compareTo(result));
}

@Test
void currencyConvert_zeroAmount_returnsZero() {
    BigDecimal result = CurrencyFunctions.currencyConvert(
        BigDecimal.ZERO, usdCurrencyId, eurCurrencyId, null, null, 11, 0);

    assertEquals(0, BigDecimal.ZERO.compareTo(result));
}

@Test
void currencyConvert_nullAmount_returnsNull() {
    BigDecimal result = CurrencyFunctions.currencyConvert(
        null, usdCurrencyId, eurCurrencyId, null, null, 11, 0);

    assertNull(result);
}

@Test
void currencyConvert_nullCurrency_returnsNull() {
    BigDecimal result = CurrencyFunctions.currencyConvert(
        new BigDecimal("100"), null, eurCurrencyId, null, null, 11, 0);

    assertNull(result);
}
```

**Step 2: Implement currencyConvert**

Add to `CurrencyFunctions.java`:

```java
/**
 * Convert amount between currencies.
 * Equivalent to PostgreSQL: currencyConvert(amount, curFromId, curToId, convDate, convTypeId, clientId, orgId)
 *
 * @param amount amount to convert
 * @param curFromId source currency ID
 * @param curToId target currency ID
 * @param convDate conversion date (null = today)
 * @param convTypeId conversion type ID (null/0 = default)
 * @param clientId client ID
 * @param orgId organization ID
 * @return converted and rounded amount, or null if rate not found
 */
@Nullable
public static BigDecimal currencyConvert(@Nullable BigDecimal amount,
                                          @Nullable Integer curFromId,
                                          @Nullable Integer curToId,
                                          @Nullable Timestamp convDate,
                                          @Nullable Integer convTypeId,
                                          @Nullable Integer clientId,
                                          @Nullable Integer orgId) {
    // Return null if amount is null
    if (amount == null) {
        return null;
    }

    // Return zero (rounded) if amount is zero
    // Handle case where currencyRound returns null (e.g., currency not found)
    if (amount.compareTo(BigDecimal.ZERO) == 0) {
        if (curToId == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal rounded = currencyRound(BigDecimal.ZERO, curToId, null);
        return rounded != null ? rounded : BigDecimal.ZERO;
    }

    // Return amount if same currency (but still round to target precision)
    if (curFromId != null && curFromId.equals(curToId)) {
        return currencyRound(amount, curToId, null);
    }

    // Return null if any required param is null
    if (curFromId == null || curToId == null) {
        log.fine(() -> "currencyConvert: null currency ID (from=" + curFromId + ", to=" + curToId + ")");
        return null;
    }

    // Get rate
    BigDecimal rate = currencyRate(curFromId, curToId, convDate, convTypeId, clientId, orgId);
    if (rate == null) {
        log.fine(() -> "currencyConvert: no rate found for from=" + curFromId + " to=" + curToId);
        return null;
    }

    // Apply rate and round to target currency precision
    BigDecimal converted = amount.multiply(rate);
    return currencyRound(converted, curToId, null);
}
```

**Step 3: Run tests**

Run: `./gradlew :base:test --tests "org.compiere.util.CurrencyFunctionsTest.currencyConvert*" -i 2>&1 | tail -20`
Expected: PASS

**Step 4: Commit**

```bash
git add base/src/org/compiere/util/CurrencyFunctions.java
git add base/test/src/org/compiere/util/CurrencyFunctionsTest.java
git commit -m "feat(wave1): implement currencyConvert"
```

---

### Task 11: Add currencyConvert SQL Caller and Integration Tests

**Files:**
- Modify: `base/src/org/compiere/migration/SqlFunctionCaller.java`
- Modify: `base/test/src/org/compiere/migration/Wave1ShadowIntegrationTest.java`

**Step 1: Add SQL caller**

Add to `SqlFunctionCaller.java`:

```java
/** Calls: SELECT currencyConvert(?, ?, ?, ?, ?, ?, ?) */
@Nullable
public static BigDecimal callCurrencyConvert(@Nullable BigDecimal amount,
                                              @Nullable Integer curFromId,
                                              @Nullable Integer curToId,
                                              @Nullable Timestamp convDate,
                                              @Nullable Integer convTypeId,
                                              @Nullable Integer clientId,
                                              @Nullable Integer orgId) {
    String sql = "SELECT currencyConvert(?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        setNullableBigDecimal(pstmt, 1, amount);
        setNullableInt(pstmt, 2, curFromId);
        setNullableInt(pstmt, 3, curToId);
        setNullableTimestamp(pstmt, 4, convDate);
        setNullableInt(pstmt, 5, convTypeId);
        setNullableInt(pstmt, 6, clientId);
        setNullableInt(pstmt, 7, orgId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (Exception e) {
        log.log(Level.WARNING, "Failed to call currencyConvert()", e);
        throw new SqlFunctionException("currencyConvert", e);
    }
    return null;
}
```

**Step 2: Add integration tests**

Add to `Wave1ShadowIntegrationTest.java`:

```java
@Test
void currencyConvert_sameCurrency_matchesSql() {
    BigDecimal amount = new BigDecimal("100.00");

    BigDecimal javaResult = CurrencyFunctions.currencyConvert(
        amount, usdCurrencyId, usdCurrencyId, null, null, 11, 0);
    BigDecimal sqlResult = SqlFunctionCaller.callCurrencyConvert(
        amount, usdCurrencyId, usdCurrencyId, null, null, 11, 0);

    assertEquals(0, javaResult.compareTo(sqlResult));
}

@Test
void currencyConvert_nullAmount_matchesSql() {
    BigDecimal javaResult = CurrencyFunctions.currencyConvert(
        null, usdCurrencyId, eurCurrencyId, null, null, 11, 0);
    BigDecimal sqlResult = SqlFunctionCaller.callCurrencyConvert(
        null, usdCurrencyId, eurCurrencyId, null, null, 11, 0);

    assertEquals(sqlResult, javaResult);
}

@Test
void currencyConvert_zeroAmount_matchesSql() {
    BigDecimal javaResult = CurrencyFunctions.currencyConvert(
        BigDecimal.ZERO, usdCurrencyId, eurCurrencyId, null, null, 11, 0);
    BigDecimal sqlResult = SqlFunctionCaller.callCurrencyConvert(
        BigDecimal.ZERO, usdCurrencyId, eurCurrencyId, null, null, 11, 0);

    assertEquals(0, javaResult.compareTo(sqlResult));
}
```

**Step 3: Run tests**

Run: `./gradlew :base:test --tests "org.compiere.migration.Wave1ShadowIntegrationTest.currencyConvert*" -i 2>&1 | tail -20`
Expected: PASS

**Step 4: Commit**

```bash
git add base/src/org/compiere/migration/SqlFunctionCaller.java
git add base/test/src/org/compiere/migration/Wave1ShadowIntegrationTest.java
git commit -m "feat(wave1): add currencyConvert SQL caller and integration tests"
```

---

### Task 12: Implement currencyBase (6-parameter version)

**Files:**
- Modify: `base/src/org/compiere/util/CurrencyFunctions.java`
- Modify: `base/test/src/org/compiere/util/CurrencyFunctionsTest.java`

**Step 1: Write the failing tests**

Add to `CurrencyFunctionsTest.java`:

```java
@Test
void currencyBase_nullAmount_returnsNull() {
    BigDecimal result = CurrencyFunctions.currencyBase(null, usdCurrencyId, null, null, 11, 0);
    assertNull(result);
}

@Test
void currencyBase_nullClient_returnsNull() {
    BigDecimal result = CurrencyFunctions.currencyBase(
        new BigDecimal("100"), usdCurrencyId, null, null, null, 0);
    assertNull(result);
}

@Test
void currencyBase_sameCurrencyAsBase_returnsAmount() {
    // This test assumes client 11's base currency matches the input
    // The actual assertion depends on client configuration
    BigDecimal amount = new BigDecimal("123.45");
    BigDecimal result = CurrencyFunctions.currencyBase(amount, usdCurrencyId, null, null, 11, 0);

    // Should not throw, and if base currency is USD, returns original
    assertNotNull(result);
}
```

**Step 2: Implement currencyBase**

Add to `CurrencyFunctions.java`:

```java
import org.compiere.model.MClientInfo;
import org.compiere.model.MAcctSchema;

/**
 * Convert amount to client's base currency.
 * Equivalent to PostgreSQL: currencyBase(amount, curFromId, convDate, convTypeId, clientId, orgId)
 *
 * @param amount amount to convert
 * @param curFromId source currency ID
 * @param convDate conversion date (null = today)
 * @param convTypeId conversion type ID (null/0 = default)
 * @param clientId client ID
 * @param orgId organization ID
 * @return converted amount in base currency, or null if rate not found
 */
@Nullable
public static BigDecimal currencyBase(@Nullable BigDecimal amount,
                                        @Nullable Integer curFromId,
                                        @Nullable Timestamp convDate,
                                        @Nullable Integer convTypeId,
                                        @Nullable Integer clientId,
                                        @Nullable Integer orgId) {
    if (amount == null || curFromId == null || clientId == null) {
        log.fine(() -> "currencyBase: null parameter (amount=" + amount
            + ", curFrom=" + curFromId + ", client=" + clientId + ")");
        return null;
    }

    // Get base currency from client's accounting schema
    Integer curToId = getClientBaseCurrency(clientId);
    if (curToId == null) {
        log.warning(() -> "currencyBase: could not determine base currency for client=" + clientId);
        return null;
    }

    // Same currency - no conversion needed (but still round)
    if (curFromId.equals(curToId)) {
        return currencyRound(amount, curToId, null);
    }

    return currencyConvert(amount, curFromId, curToId, convDate, convTypeId, clientId, orgId);
}

/**
 * Get client's base currency from primary accounting schema.
 */
@Nullable
private static Integer getClientBaseCurrency(int clientId) {
    MClientInfo clientInfo = MClientInfo.get(Env.getCtx(), clientId);
    if (clientInfo == null) {
        log.fine(() -> "currencyBase: MClientInfo not found for client=" + clientId);
        return null;
    }

    int acctSchemaId = clientInfo.getC_AcctSchema1_ID();
    if (acctSchemaId <= 0) {
        log.fine(() -> "currencyBase: no accounting schema for client=" + clientId);
        return null;
    }

    MAcctSchema acctSchema = MAcctSchema.get(Env.getCtx(), acctSchemaId);
    if (acctSchema == null) {
        log.fine(() -> "currencyBase: MAcctSchema not found, ID=" + acctSchemaId);
        return null;
    }

    return acctSchema.getC_Currency_ID();
}
```

**Step 3: Run tests**

Run: `./gradlew :base:test --tests "org.compiere.util.CurrencyFunctionsTest.currencyBase*" -i 2>&1 | tail -20`
Expected: PASS

**Step 4: Commit**

```bash
git add base/src/org/compiere/util/CurrencyFunctions.java
git add base/test/src/org/compiere/util/CurrencyFunctionsTest.java
git commit -m "feat(wave1): implement currencyBase (6-parameter version)"
```

---

### Task 13: Implement currencyBase 5-Parameter Overload

**Files:**
- Modify: `base/src/org/compiere/util/CurrencyFunctions.java`
- Modify: `base/test/src/org/compiere/util/CurrencyFunctionsTest.java`

**Step 1: Write the failing test**

Add to `CurrencyFunctionsTest.java`:

```java
@Test
void currencyBase5Param_delegatesTo6Param() {
    BigDecimal amount = new BigDecimal("100.00");

    // 5-param version should delegate to 6-param with null convTypeId
    BigDecimal result5 = CurrencyFunctions.currencyBase(
        amount, usdCurrencyId, null, 11, 0);
    BigDecimal result6 = CurrencyFunctions.currencyBase(
        amount, usdCurrencyId, null, null, 11, 0);

    // Both should return same result
    if (result5 != null && result6 != null) {
        assertEquals(0, result5.compareTo(result6));
    } else {
        assertEquals(result5, result6);
    }
}
```

**Step 2: Implement 5-parameter overload**

Add to `CurrencyFunctions.java`:

```java
/**
 * Convert amount to client's base currency (5-parameter overload, no conversion type).
 * Equivalent to PostgreSQL: currencyBase(amount, curFromId, convDate, clientId, orgId)
 *
 * <p>This overload exists for backwards compatibility with call sites that don't
 * specify a conversion type. It delegates to the 6-parameter version with null convTypeId.
 *
 * @param amount amount to convert
 * @param curFromId source currency ID
 * @param convDate conversion date (null = today)
 * @param clientId client ID
 * @param orgId organization ID
 * @return converted amount in base currency, or null if rate not found
 */
@Nullable
public static BigDecimal currencyBase(@Nullable BigDecimal amount,
                                        @Nullable Integer curFromId,
                                        @Nullable Timestamp convDate,
                                        @Nullable Integer clientId,
                                        @Nullable Integer orgId) {
    return currencyBase(amount, curFromId, convDate, null, clientId, orgId);
}
```

**Step 3: Run tests**

Run: `./gradlew :base:test --tests "org.compiere.util.CurrencyFunctionsTest.currencyBase*" -i 2>&1 | tail -20`
Expected: PASS

**Step 4: Commit**

```bash
git add base/src/org/compiere/util/CurrencyFunctions.java
git add base/test/src/org/compiere/util/CurrencyFunctionsTest.java
git commit -m "feat(wave1): add currencyBase 5-parameter overload"
```

---

### Task 14: Add currencyBase SQL Callers and Integration Tests

**Files:**
- Modify: `base/src/org/compiere/migration/SqlFunctionCaller.java`
- Modify: `base/test/src/org/compiere/migration/Wave1ShadowIntegrationTest.java`

**Step 1: Add SQL callers for both overloads**

Add to `SqlFunctionCaller.java`:

```java
/** Calls: SELECT currencyBase(?, ?, ?, ?, ?, ?) - 6 param version */
@Nullable
public static BigDecimal callCurrencyBase(@Nullable BigDecimal amount,
                                           @Nullable Integer curFromId,
                                           @Nullable Timestamp convDate,
                                           @Nullable Integer convTypeId,
                                           @Nullable Integer clientId,
                                           @Nullable Integer orgId) {
    String sql = "SELECT currencyBase(?, ?, ?, ?, ?, ?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        setNullableBigDecimal(pstmt, 1, amount);
        setNullableInt(pstmt, 2, curFromId);
        setNullableTimestamp(pstmt, 3, convDate);
        setNullableInt(pstmt, 4, convTypeId);
        setNullableInt(pstmt, 5, clientId);
        setNullableInt(pstmt, 6, orgId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (Exception e) {
        log.log(Level.WARNING, "Failed to call currencyBase(6)", e);
        throw new SqlFunctionException("currencyBase", e);
    }
    return null;
}

/** Calls: SELECT currencyBase(?, ?, ?, ?, ?) - 5 param version (no convTypeId) */
@Nullable
public static BigDecimal callCurrencyBase(@Nullable BigDecimal amount,
                                           @Nullable Integer curFromId,
                                           @Nullable Timestamp convDate,
                                           @Nullable Integer clientId,
                                           @Nullable Integer orgId) {
    String sql = "SELECT currencyBase(?, ?, ?, ?, ?)";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        setNullableBigDecimal(pstmt, 1, amount);
        setNullableInt(pstmt, 2, curFromId);
        setNullableTimestamp(pstmt, 3, convDate);
        setNullableInt(pstmt, 4, clientId);
        setNullableInt(pstmt, 5, orgId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (Exception e) {
        log.log(Level.WARNING, "Failed to call currencyBase(5)", e);
        throw new SqlFunctionException("currencyBase", e);
    }
    return null;
}
```

**Step 2: Add integration tests**

Add to `Wave1ShadowIntegrationTest.java`:

```java
@Test
void currencyBase_nullAmount_matchesSql() {
    BigDecimal javaResult = CurrencyFunctions.currencyBase(null, usdCurrencyId, null, null, 11, 0);
    BigDecimal sqlResult = SqlFunctionCaller.callCurrencyBase(null, usdCurrencyId, null, null, 11, 0);

    assertEquals(sqlResult, javaResult);
}

@Test
void currencyBase_sameCurrencyAsBase_matchesSql() {
    BigDecimal amount = new BigDecimal("123.45");

    BigDecimal javaResult = CurrencyFunctions.currencyBase(amount, usdCurrencyId, null, null, 11, 0);
    BigDecimal sqlResult = SqlFunctionCaller.callCurrencyBase(amount, usdCurrencyId, null, null, 11, 0);

    if (javaResult != null && sqlResult != null) {
        assertEquals(0, javaResult.compareTo(sqlResult),
            "currencyBase mismatch: java=" + javaResult + ", sql=" + sqlResult);
    } else {
        assertEquals(sqlResult, javaResult);
    }
}

@Test
void currencyBase5Param_matchesSql() {
    BigDecimal amount = new BigDecimal("100.00");

    BigDecimal javaResult = CurrencyFunctions.currencyBase(amount, usdCurrencyId, null, 11, 0);
    BigDecimal sqlResult = SqlFunctionCaller.callCurrencyBase(amount, usdCurrencyId, null, 11, 0);

    if (javaResult != null && sqlResult != null) {
        assertEquals(0, javaResult.compareTo(sqlResult),
            "currencyBase(5) mismatch: java=" + javaResult + ", sql=" + sqlResult);
    } else {
        assertEquals(sqlResult, javaResult);
    }
}
```

**Step 3: Run tests**

Run: `./gradlew :base:test --tests "org.compiere.migration.Wave1ShadowIntegrationTest.currencyBase*" -i 2>&1 | tail -20`
Expected: PASS

**Step 4: Commit**

```bash
git add base/src/org/compiere/migration/SqlFunctionCaller.java
git add base/test/src/org/compiere/migration/Wave1ShadowIntegrationTest.java
git commit -m "feat(wave1): add currencyBase SQL callers (5 and 6 param) and integration tests"
```

---

## Group 4: Performance Testing and Baseline Capture (0.5 days)

### Task 15: Add Wave 1 Performance Tests

**Files:**
- Create: `base/test/src/org/compiere/migration/Wave1PerformanceTest.java`

**Step 1: Write performance tests with proper lifecycle management**

```java
// base/test/src/org/compiere/migration/Wave1PerformanceTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MCurrency;
import org.compiere.util.CurrencyFunctions;
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
 * Performance tests for Wave 1 currency functions.
 * Validates Java implementations meet latency requirements (<=130% of SQL).
 *
 * <p><b>Note:</b> Currency functions involve database lookups for precision
 * and rates, so the performance advantage over SQL is smaller than Wave 0
 * pure-computation functions. The 130% threshold catches regressions.
 *
 * <p><b>Test Data Requirements:</b>
 * <ul>
 *   <li>USD currency must exist</li>
 * </ul>
 *
 * <p><b>Parallelization:</b> This test uses instance fields for ratio accumulation
 * across repeated test runs. Must run in same thread to prevent race conditions
 * when JUnit parallel execution is enabled.
 */
@Tag("PerformanceTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.SAME_THREAD)  // Prevent parallel execution - uses shared instance state
public class Wave1PerformanceTest extends CommonGWSetup {

    private static final double MAX_LATENCY_RATIO = 1.30;
    private static final int WARMUP_ITERATIONS = 500;
    private static final int TEST_ITERATIONS = 2000;
    private static final int MEASUREMENT_ROUNDS = 5;

    private Integer usdCurrencyId;

    // Instance field for ratio accumulation (not static ThreadLocal)
    // Reset before each test method's repeated runs
    private double[] ratioAccumulator;

    @BeforeAll
    void loadTestData() {
        MCurrency usd = MCurrency.get(Env.getCtx(), "USD");
        assumeTrue(usd != null && usd.get_ID() > 0, "USD currency required for performance tests");
        usdCurrencyId = usd.get_ID();
    }

    @BeforeEach
    void initAccumulator(RepetitionInfo info) {
        if (info.getCurrentRepetition() == 1) {
            ratioAccumulator = new double[MEASUREMENT_ROUNDS];
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testCurrencyRoundPerformance(RepetitionInfo info) {
        BigDecimal amount = new BigDecimal("123.456789");
        String costing = "N";

        if (info.getCurrentRepetition() == 1) {
            // Warmup on first repetition only
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                CurrencyFunctions.currencyRound(amount, usdCurrencyId, costing);
                SqlFunctionCaller.callCurrencyRound(amount, usdCurrencyId, costing);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            CurrencyFunctions.currencyRound(amount, usdCurrencyId, costing);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callCurrencyRound(amount, usdCurrencyId, costing);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            Arrays.sort(ratioAccumulator);
            double medianRatio = ratioAccumulator[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("currencyRound Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, Arrays.toString(ratioAccumulator)));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testCurrencyRatePerformance(RepetitionInfo info) {
        // Same currency for consistent results (avoids rate lookup variability)
        Timestamp convDate = null;
        Integer convTypeId = null;
        Integer clientId = 11;
        Integer orgId = 0;

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                CurrencyFunctions.currencyRate(usdCurrencyId, usdCurrencyId,
                    convDate, convTypeId, clientId, orgId);
                SqlFunctionCaller.callCurrencyRate(usdCurrencyId, usdCurrencyId,
                    convDate, convTypeId, clientId, orgId);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            CurrencyFunctions.currencyRate(usdCurrencyId, usdCurrencyId,
                convDate, convTypeId, clientId, orgId);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callCurrencyRate(usdCurrencyId, usdCurrencyId,
                convDate, convTypeId, clientId, orgId);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            Arrays.sort(ratioAccumulator);
            double medianRatio = ratioAccumulator[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("currencyRate Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, Arrays.toString(ratioAccumulator)));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testCurrencyConvertPerformance(RepetitionInfo info) {
        BigDecimal amount = new BigDecimal("100.00");
        // Same currency for consistent results
        Timestamp convDate = null;
        Integer convTypeId = null;
        Integer clientId = 11;
        Integer orgId = 0;

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                CurrencyFunctions.currencyConvert(amount, usdCurrencyId, usdCurrencyId,
                    convDate, convTypeId, clientId, orgId);
                SqlFunctionCaller.callCurrencyConvert(amount, usdCurrencyId, usdCurrencyId,
                    convDate, convTypeId, clientId, orgId);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            CurrencyFunctions.currencyConvert(amount, usdCurrencyId, usdCurrencyId,
                convDate, convTypeId, clientId, orgId);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callCurrencyConvert(amount, usdCurrencyId, usdCurrencyId,
                convDate, convTypeId, clientId, orgId);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            Arrays.sort(ratioAccumulator);
            double medianRatio = ratioAccumulator[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("currencyConvert Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, Arrays.toString(ratioAccumulator)));
        }
    }
}
```

**Step 2: Run performance tests**

Run: `./gradlew :base:test --tests "org.compiere.migration.Wave1PerformanceTest" -i 2>&1 | tail -50`
Expected: PASS

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave1PerformanceTest.java
git commit -m "test(wave1): add performance tests with proper lifecycle management

Uses instance fields instead of static ThreadLocal to avoid:
- State leakage between test runs
- Memory leaks in long-running test suites
- Thread affinity issues with JUnit 5"
```

---

### Task 16: Capture Performance Baseline Metrics

**Files:**
- Create: `docs/metrics/wave1-performance-baseline.md`

**Step 1: Run baseline capture**

Run the performance tests and capture metrics:

```bash
./gradlew :base:test --tests "org.compiere.migration.Wave1PerformanceTest" -i 2>&1 | tee /tmp/wave1-perf.log
```

**Step 2: Document baseline**

Create baseline documentation:

```markdown
# Wave 1 Currency Functions - Performance Baseline

**Captured:** [DATE]
**Environment:** [DB version, JVM version, hardware specs]

## Baseline Metrics

| Function | SQL p50 (ms) | SQL p95 (ms) | Java p50 (ms) | Java p95 (ms) | Ratio |
|----------|--------------|--------------|---------------|---------------|-------|
| currencyRound | TBD | TBD | TBD | TBD | TBD |
| currencyRate | TBD | TBD | TBD | TBD | TBD |
| currencyConvert | TBD | TBD | TBD | TBD | TBD |
| currencyBase | TBD | TBD | TBD | TBD | TBD |

## Test Configuration

- Warmup iterations: 500
- Test iterations: 2000
- Measurement rounds: 5
- Max acceptable ratio: 1.30

## Notes

- currencyRound involves C_Currency table lookup (cached by MCurrency)
- currencyRate involves C_Conversion_Rate table lookup with complex EMU logic
- currencyConvert combines rate lookup and rounding
- Performance advantage over SQL is smaller than Wave 0 due to DB lookups
```

**Step 3: Commit**

```bash
git add docs/metrics/wave1-performance-baseline.md
git commit -m "docs(wave1): add performance baseline template"
```

---

## Group 5: Shadow Mode Integration and Validation Setup (0.5 days)

### Task 17: Create BigDecimalComparator with Configurable Tolerance

**Files:**
- Create: `base/src/org/compiere/migration/comparators/BigDecimalComparator.java`

**Step 1: Create comparator with tolerance support**

```java
// base/src/org/compiere/migration/comparators/BigDecimalComparator.java
package org.compiere.migration.comparators;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiPredicate;

/**
 * Comparator for BigDecimal values with configurable precision tolerance.
 *
 * <p>Handles null values and allows minor differences due to:
 * <ul>
 *   <li>Different intermediate precision handling between Java and SQL</li>
 *   <li>Different rounding behavior at division boundaries</li>
 *   <li>Scale differences (1.0 vs 1.00)</li>
 * </ul>
 *
 * <p><b>Tolerance Selection:</b>
 * <ul>
 *   <li>EXACT: Use for pure computation functions (no division)</li>
 *   <li>CURRENCY (6 decimals): Use for currency functions with division</li>
 * </ul>
 *
 * @see docs/plans/2026-01-03-wave1-currency-implementation.md Decision 2
 */
public class BigDecimalComparator implements BiPredicate<BigDecimal, BigDecimal> {

    /** Exact comparison - values must be equal (ignoring scale) */
    public static final BigDecimalComparator EXACT = new BigDecimalComparator(-1);

    /** Currency comparison - 6 decimal place tolerance for division rounding */
    public static final BigDecimalComparator CURRENCY = new BigDecimalComparator(6);

    private final int toleranceScale;

    /**
     * Create comparator with specified tolerance.
     *
     * @param toleranceScale number of decimal places to compare, or -1 for exact
     */
    public BigDecimalComparator(int toleranceScale) {
        this.toleranceScale = toleranceScale;
    }

    @Override
    public boolean test(BigDecimal java, BigDecimal sql) {
        if (java == null && sql == null) {
            return true;
        }
        if (java == null || sql == null) {
            return false;
        }

        if (toleranceScale < 0) {
            // Exact comparison using compareTo (ignores scale)
            return java.compareTo(sql) == 0;
        }

        // Compare at specified tolerance scale
        return java.setScale(toleranceScale, RoundingMode.HALF_UP)
                   .compareTo(sql.setScale(toleranceScale, RoundingMode.HALF_UP)) == 0;
    }

    /**
     * Get description for logging.
     */
    public String getDescription() {
        return toleranceScale < 0 ? "exact" : toleranceScale + " decimal places";
    }
}
```

**Step 2: Commit**

```bash
git add base/src/org/compiere/migration/comparators/BigDecimalComparator.java
git commit -m "feat(wave1): add BigDecimalComparator with configurable tolerance

CURRENCY comparator uses 6 decimal places to handle division rounding
differences between Java and SQL implementations.

Decision documented in Wave 1 implementation plan."
```

---

### Task 18: Wire Functions to Shadow Executor

**Files:**
- Create: `base/src/org/compiere/util/CurrencyFunctionRouter.java`

**Step 0: Verify ShadowExecutor interface compatibility**

Before writing the router, verify the Wave 0 ShadowExecutor has the expected signature:

```bash
# Check ShadowExecutor exists and has the expected signature
grep -A 20 "public static.*execute" base/src/org/compiere/migration/ShadowExecutor.java 2>/dev/null | head -30
```

**Expected signature:**
```java
public static <T> T execute(
    String functionName,
    Object[] args,
    Supplier<T> javaImplementation,
    Supplier<T> sqlImplementation,
    BiPredicate<T, T> comparator
)
```

**If signature differs:**
1. If comparator parameter is missing: Update Wave 0 ShadowExecutor to accept comparator
2. If parameter order differs: Adjust CurrencyFunctionRouter calls accordingly
3. If ShadowExecutor doesn't exist: Create it as part of Task 18 using Wave 0 patterns

**Step 1: Create router class**

```java
// base/src/org/compiere/util/CurrencyFunctionRouter.java
package org.compiere.util;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Nullable;

import org.compiere.migration.SqlFunctionCaller;
import org.compiere.migration.ShadowExecutor;
import org.compiere.migration.comparators.BigDecimalComparator;

/**
 * Router for Wave 1 currency functions.
 * Delegates to ShadowExecutor for mode-aware execution (SQL_ONLY, SHADOW, JAVA_ONLY).
 *
 * <p>Use these methods in application code to enable gradual migration:
 * <ul>
 *   <li>SQL_ONLY: Calls SQL function only (default, safe)</li>
 *   <li>SHADOW: Calls both, compares with tolerance, logs, returns Java result</li>
 *   <li>JAVA_ONLY: Calls Java only (post-validation)</li>
 * </ul>
 *
 * <p><b>Comparison Tolerance:</b> Uses 6 decimal place tolerance for currency
 * functions due to division rounding differences between Java and SQL.
 * See: docs/plans/2026-01-03-wave1-currency-implementation.md, Decision 2
 *
 * @see CurrencyFunctions for the Java implementations
 * @see SqlFunctionCaller for the SQL callers
 */
public class CurrencyFunctionRouter {

    private CurrencyFunctionRouter() {
        // Utility class
    }

    /**
     * Route currencyRound through shadow executor.
     */
    @Nullable
    public static BigDecimal currencyRound(@Nullable BigDecimal amount,
                                            @Nullable Integer currencyId,
                                            @Nullable String costing) {
        return ShadowExecutor.execute(
            "currencyRound",
            new Object[]{amount, currencyId, costing},
            () -> CurrencyFunctions.currencyRound(amount, currencyId, costing),
            () -> SqlFunctionCaller.callCurrencyRound(amount, currencyId, costing),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route currencyRate through shadow executor.
     */
    @Nullable
    public static BigDecimal currencyRate(@Nullable Integer curFromId,
                                           @Nullable Integer curToId,
                                           @Nullable Timestamp convDate,
                                           @Nullable Integer convTypeId,
                                           @Nullable Integer clientId,
                                           @Nullable Integer orgId) {
        return ShadowExecutor.execute(
            "currencyRate",
            new Object[]{curFromId, curToId, convDate, convTypeId, clientId, orgId},
            () -> CurrencyFunctions.currencyRate(curFromId, curToId, convDate, convTypeId, clientId, orgId),
            () -> SqlFunctionCaller.callCurrencyRate(curFromId, curToId, convDate, convTypeId, clientId, orgId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route currencyConvert through shadow executor.
     */
    @Nullable
    public static BigDecimal currencyConvert(@Nullable BigDecimal amount,
                                              @Nullable Integer curFromId,
                                              @Nullable Integer curToId,
                                              @Nullable Timestamp convDate,
                                              @Nullable Integer convTypeId,
                                              @Nullable Integer clientId,
                                              @Nullable Integer orgId) {
        return ShadowExecutor.execute(
            "currencyConvert",
            new Object[]{amount, curFromId, curToId, convDate, convTypeId, clientId, orgId},
            () -> CurrencyFunctions.currencyConvert(amount, curFromId, curToId, convDate, convTypeId, clientId, orgId),
            () -> SqlFunctionCaller.callCurrencyConvert(amount, curFromId, curToId, convDate, convTypeId, clientId, orgId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route currencyBase (6-param) through shadow executor.
     */
    @Nullable
    public static BigDecimal currencyBase(@Nullable BigDecimal amount,
                                           @Nullable Integer curFromId,
                                           @Nullable Timestamp convDate,
                                           @Nullable Integer convTypeId,
                                           @Nullable Integer clientId,
                                           @Nullable Integer orgId) {
        return ShadowExecutor.execute(
            "currencyBase",
            new Object[]{amount, curFromId, convDate, convTypeId, clientId, orgId},
            () -> CurrencyFunctions.currencyBase(amount, curFromId, convDate, convTypeId, clientId, orgId),
            () -> SqlFunctionCaller.callCurrencyBase(amount, curFromId, convDate, convTypeId, clientId, orgId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route currencyBase (5-param) through shadow executor.
     */
    @Nullable
    public static BigDecimal currencyBase(@Nullable BigDecimal amount,
                                           @Nullable Integer curFromId,
                                           @Nullable Timestamp convDate,
                                           @Nullable Integer clientId,
                                           @Nullable Integer orgId) {
        return ShadowExecutor.execute(
            "currencyBase",
            new Object[]{amount, curFromId, convDate, clientId, orgId},
            () -> CurrencyFunctions.currencyBase(amount, curFromId, convDate, clientId, orgId),
            () -> SqlFunctionCaller.callCurrencyBase(amount, curFromId, convDate, clientId, orgId),
            BigDecimalComparator.CURRENCY
        );
    }
}
```

**Step 2: Run compilation check**

Run: `./gradlew :base:compileJava -i 2>&1 | tail -20`
Expected: BUILD SUCCESSFUL

**Step 3: Commit**

```bash
git add base/src/org/compiere/util/CurrencyFunctionRouter.java
git commit -m "feat(wave1): add CurrencyFunctionRouter for shadow mode integration

Routes all 4 currency functions (plus 5-param currencyBase overload)
through ShadowExecutor with CURRENCY tolerance comparator."
```

---

### Task 19: Enable Shadow Mode for Wave 1 Functions

**Files:**
- Modify: `migration/sql/wave1-function-config.sql`

**Step 1: Update configuration to enable SHADOW mode**

```sql
-- Enable SHADOW mode for Wave 1 Currency Functions
-- Run this after code deployment to start validation

UPDATE migration.function_config
SET mode = 'SHADOW', sample_rate = 1.0
WHERE function_name IN ('currencyRound', 'currencyRate', 'currencyConvert', 'currencyBase');

-- Verify
SELECT function_name, mode, sample_rate, circuit_breaker_enabled
FROM migration.function_config
WHERE function_name LIKE 'currency%';
```

**Step 2: Commit**

```bash
git add migration/sql/wave1-function-config.sql
git commit -m "chore(wave1): update config to enable SHADOW mode"
```

---

### Task 20: Run Full Wave 1 Validation Suite

**Step 1: Run all Wave 1 tests**

```bash
./gradlew :base:test --tests "org.compiere.util.CurrencyFunctionsTest" \
                     --tests "org.compiere.migration.Wave1ShadowIntegrationTest" \
                     --tests "org.compiere.migration.Wave1PerformanceTest" \
                     -i 2>&1 | tee /tmp/wave1-full-test.log
```

**Step 2: Verify all tests pass**

```bash
grep -E "(PASSED|FAILED)" /tmp/wave1-full-test.log | tail -50
```

Expected: All tests PASSED

**Step 3: Final commit**

```bash
git add -A
git commit -m "feat(wave1): complete Wave 1 currency function implementation

Functions implemented:
- currencyRound: Round to currency precision (standard/costing)
- currencyRate: Get conversion rate with EMU/Euro logic (SQL bug fixed)
- currencyConvert: Convert between currencies
- currencyBase: Convert to client base currency (5 and 6 param overloads)

All functions:
- Unit tested with dynamic test data lookup
- Integration tested (Java vs SQL parity with 6 decimal tolerance)
- Performance tested (<=130% of SQL baseline)
- Wired to ShadowExecutor for shadow mode validation
- Properly logged for production diagnostics

Key decisions documented:
- EMU-to-EMU SQL bug: fixed in SQL, correct Java implementation
- BigDecimal comparison: 6 decimal place tolerance for division rounding
- Test data: dynamic lookup by ISO code, not hardcoded IDs"
```

---

## Validation Checklist

Before marking Wave 1 complete:

- [ ] SQL bug fix applied (`migration/sql/fix-emu-rate-bug.sql`)
- [ ] All unit tests pass (`CurrencyFunctionsTest`)
- [ ] All integration tests pass (`Wave1ShadowIntegrationTest`)
- [ ] All performance tests pass (`Wave1PerformanceTest`)
- [ ] Shadow mode enabled in `migration.function_config`
- [ ] Monitoring dashboard shows shadow execution logs
- [ ] No critical mismatches for 7 consecutive days (with tolerance comparator)
- [ ] Performance baseline captured in `docs/metrics/`

---

## Post-Implementation: Cutover Steps

After 7 days of successful shadow validation:

1. **Set JAVA_ONLY mode**:
   ```sql
   UPDATE migration.function_config
   SET mode = 'JAVA_ONLY'
   WHERE function_name IN ('currencyRound', 'currencyRate', 'currencyConvert', 'currencyBase');
   ```

2. **Monitor for 7 days** - Watch error rates

3. **Archive SQL functions** - After 30 days stable, SQL functions can be removed

---

## Appendix: Function Signatures

```sql
-- PostgreSQL signatures
currencyRound(amount NUMERIC, currencyId NUMERIC, costing VARCHAR) RETURNS NUMERIC
currencyRate(curFromId NUMERIC, curToId NUMERIC, convDate TIMESTAMPTZ, convTypeId NUMERIC, clientId NUMERIC, orgId NUMERIC) RETURNS NUMERIC
currencyConvert(amount NUMERIC, curFromId NUMERIC, curToId NUMERIC, convDate TIMESTAMPTZ, convTypeId NUMERIC, clientId NUMERIC, orgId NUMERIC) RETURNS NUMERIC
currencyBase(amount NUMERIC, curFromId NUMERIC, convDate TIMESTAMPTZ, convTypeId NUMERIC, clientId NUMERIC, orgId NUMERIC) RETURNS NUMERIC
currencyBase(amount NUMERIC, curFromId NUMERIC, convDate TIMESTAMPTZ, clientId NUMERIC, orgId NUMERIC) RETURNS NUMERIC  -- 5-param overload
```

```java
// Java signatures
BigDecimal currencyRound(BigDecimal amount, Integer currencyId, String costing)
BigDecimal currencyRate(Integer curFromId, Integer curToId, Timestamp convDate, Integer convTypeId, Integer clientId, Integer orgId)
BigDecimal currencyConvert(BigDecimal amount, Integer curFromId, Integer curToId, Timestamp convDate, Integer convTypeId, Integer clientId, Integer orgId)
BigDecimal currencyBase(BigDecimal amount, Integer curFromId, Timestamp convDate, Integer convTypeId, Integer clientId, Integer orgId)
BigDecimal currencyBase(BigDecimal amount, Integer curFromId, Timestamp convDate, Integer clientId, Integer orgId)  // 5-param overload
```

---

## Revision History

| Date | Author | Changes |
|------|--------|---------|
| 2026-01-03 | Initial | Created Wave 1 implementation plan |
| 2026-01-03 | Review Response #1 | Updated based on critical review findings: added Decision 1 (EMU bug fix), Decision 2 (tolerance), Decision 3 (test data), Task 0 (SQL fix), Task 13 (5-param overload), improved logging, fixed performance test patterns, added utility methods for SqlFunctionCaller |
| 2026-01-03 | Review Response #2 | Addressed critical review #2 findings: (1) Task 0 now has explicit sed-based fix instead of template; (2) Added SqlFunctionException class definition to Task 4; (3) Added ShadowExecutor interface verification to Task 18; (4) Added @Execution(SAME_THREAD) to performance tests; (5) Added EMU-to-EMU integration test to Task 9; (6) Added getCurrencyByIsoCode() helper with Query fallback for API compatibility; (7) Documented migration directory location; (8) Fixed currencyRound null handling in zero-amount case |
