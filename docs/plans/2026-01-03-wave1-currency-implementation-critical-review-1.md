# Critical Implementation Review: Wave 1 Currency Functions

**Plan Reviewed:** `docs/plans/2026-01-03-wave1-currency-implementation.md`
**Reviewer Role:** Senior Staff Software Engineer / Strict Code Reviewer
**Review Date:** 2026-01-03

---

## 1. Overall Assessment

**Strengths:**
- Well-structured TDD approach with clear incremental development pattern
- Follows established Wave 0 infrastructure (ShadowExecutor, MigrationLogger, CircuitBreaker)
- Comprehensive test coverage planned: unit, integration, and performance tests
- Shadow mode integration enables safe gradual rollout
- Correctly reuses existing `MCurrency`, `MConversionRate`, and `MClientInfo` infrastructure

**Major Concerns:**
- **No logging in CurrencyFunctions class** for diagnostic purposes when lookups fail
- **Semantic mismatch in EMU-to-EMU logic** between planned Java and actual SQL implementation
- **Missing 5-parameter `currencyBase` overload** that is mentioned in appendix but not implemented
- **Performance test methodology flawed** with ThreadLocal pattern that can leak state across test runs
- **Missing tolerance in BigDecimalComparator** for division operations that may produce rounding differences

---

## 2. Critical Issues

### Issue 2.1: Logic Bug in currencyRate EMU-to-EMU Check (Correctness)

**Location:** Task 7, Step 3 - currencyRate implementation, lines 624-631

**Problem:** The Java implementation checks:
```java
if (cfIsEmuMember && ctIsEmuMember
        && cfEmuEntryDate != null && !effectiveDate.before(cfEmuEntryDate)
        && ctEmuEntryDate != null && !effectiveDate.before(ctEmuEntryDate))
```

The SQL function at `db/ddlutils/postgresql/functions/C_Currency_Rate.sql:110-113` has a BUG:
```sql
IF (cf_IsEMUMember = 'Y' AND cf_IsEMUMember ='Y'  -- BUG: should be ct_IsEMUMember
```

**Why it matters:**
- If the goal is 100% SQL parity, the Java should replicate this bug
- If the goal is correct behavior, the SQL should be fixed first
- Either way, the current plan creates a mismatch

**Fix:**
1. Document this as a known SQL bug
2. Decide: replicate bug for parity OR fix SQL first and match correct behavior
3. Add explicit integration test for this edge case to verify chosen behavior

---

### Issue 2.2: Missing Logging in CurrencyFunctions Class (Production Readiness)

**Location:** Task 3, Step 3 and Task 7, Step 3 - CurrencyFunctions.java

**Problem:** The `CurrencyFunctions` class has no `CLogger` instance and no diagnostic logging for:
- Currency not found (returns null silently)
- Rate not found (returns null silently)
- Euro currency not found for EMU conversion
- Client base currency lookup failures

**Why it matters:**
- In production, silent failures make debugging extremely difficult
- The skill explicitly requires "mandatory logging" for production-grade code
- When currency conversions fail, operators need to know WHY

**Fix:** Add logging at WARNING level for all failure paths:
```java
private static final CLogger log = CLogger.getCLogger(CurrencyFunctions.class);

// In currencyRate when rate not found:
log.warning("Currency rate not found: from=" + curFromId + " to=" + curToId
    + " date=" + convDate + " type=" + convTypeId);
```

---

### Issue 2.3: Missing 5-Parameter currencyBase Overload (Completeness)

**Location:** Appendix shows signature but no implementation task exists

**Problem:** The appendix lists:
```sql
currencyBase(amount, curFromId, convDate, clientId, orgId) -- 5-param overload
```

And `SqlFunctionCaller` includes `callCurrencyBaseNoType()` for this, but:
- No corresponding Java method `CurrencyFunctions.currencyBase(5 params)`
- No integration test comparing Java to SQL for this overload
- `CurrencyFunctionRouter` doesn't route this overload

**Why it matters:**
- Incomplete migration - some call sites use 5-param version
- Shadow mode won't cover these calls
- Potential production failures when 5-param version is called

**Fix:** Add Task 12.5 to implement and test the 5-parameter overload:
```java
public static BigDecimal currencyBase(BigDecimal amount, Integer curFromId,
                                       Timestamp convDate, Integer clientId, Integer orgId) {
    return currencyBase(amount, curFromId, convDate, null, clientId, orgId);
}
```

---

### Issue 2.4: BigDecimalComparator Lacks Tolerance for Division Rounding (Correctness)

**Location:** Task 16, Step 2 - BigDecimalComparator.java

**Problem:** The comparator uses exact `compareTo()`:
```java
return java.compareTo(sql) == 0;
```

But `currencyRate` EMU calculations involve division:
```java
return BigDecimal.ONE.divide(cfEmuRate, 12, RoundingMode.HALF_UP);
```

SQL and Java may produce slightly different results due to:
- Different intermediate precision handling
- Different rounding behavior at division boundaries

**Why it matters:**
- Shadow mode will report false mismatches
- Alert fatigue will cause real mismatches to be ignored
- Team loses confidence in migration validation

**Fix:** Add configurable tolerance to BigDecimalComparator:
```java
public class BigDecimalComparator implements BiPredicate<BigDecimal, BigDecimal> {
    private final int toleranceScale;  // e.g., 10 = 10 decimal places

    public static final BigDecimalComparator CURRENCY = new BigDecimalComparator(6);

    @Override
    public boolean test(BigDecimal java, BigDecimal sql) {
        if (java == null && sql == null) return true;
        if (java == null || sql == null) return false;
        return java.setScale(toleranceScale, RoundingMode.HALF_UP)
                   .compareTo(sql.setScale(toleranceScale, RoundingMode.HALF_UP)) == 0;
    }
}
```

---

### Issue 2.5: Performance Test ThreadLocal Pattern Flawed (Test Reliability)

**Location:** Task 14 - Wave1PerformanceTest.java, lines 1418-1420

**Problem:**
```java
private static final ThreadLocal<double[]> ratioAccumulator =
    ThreadLocal.withInitial(() -> new double[MEASUREMENT_ROUNDS]);
```

Issues:
1. JUnit 5 may run `@RepeatedTest` methods on different threads, breaking accumulation
2. ThreadLocal values persist between test classes in the same JVM
3. `static ThreadLocal` with mutable arrays is a memory leak pattern

**Why it matters:**
- Performance tests may produce incorrect median calculations
- Flaky tests that pass/fail inconsistently
- Memory leak in long-running test suites

**Fix:** Use instance field with test lifecycle management:
```java
private double[] ratioAccumulator;

@BeforeEach
void initAccumulator(RepetitionInfo info) {
    if (info.getCurrentRepetition() == 1) {
        ratioAccumulator = new double[MEASUREMENT_ROUNDS];
    }
}
```

---

### Issue 2.6: Hardcoded Test Data Assumptions (Test Brittleness)

**Location:** Throughout test classes

**Problem:** Tests assume specific IDs:
```java
// USD (C_Currency_ID=100) has StdPrecision=2
BigDecimal result = CurrencyFunctions.currencyRound(amount, 100, "N");
```

Also: EUR=102, client 11 exists, client 11 base currency is USD

**Why it matters:**
- Tests will fail on any environment with different seed data
- Makes tests non-portable to customer environments
- Violates test isolation principles

**Fix:**
1. Query test data dynamically:
```java
private Integer usdCurrencyId;

@BeforeAll
void loadTestData() {
    usdCurrencyId = new Query(Env.getCtx(), I_C_Currency.Table_Name, "ISO_Code=?", null)
        .setParameters("USD")
        .firstId();
    assumeTrue(usdCurrencyId > 0, "USD currency must exist");
}
```

2. Or use constants file with environment-specific values

---

### Issue 2.7: Division by Zero Not Handled in EMU Rate Logic (Robustness)

**Location:** Task 7, Step 3 - currencyRate, lines 617-619 and 629

**Problem:** Code checks for null/zero before division:
```java
if (cfEmuRate == null || cfEmuRate.compareTo(BigDecimal.ZERO) == 0) {
    return null;
}
return BigDecimal.ONE.divide(cfEmuRate, 12, RoundingMode.HALF_UP);
```

But this doesn't handle `ctEmuRate` being zero in lines 627-631:
```java
return ctEmuRate.divide(cfEmuRate, 12, RoundingMode.HALF_UP);
```

If `ctEmuRate` is zero, result is 0 (correct), but if `cfEmuRate` is zero, ArithmeticException.

**Why it matters:** Data quality issues in EMU configuration could crash the application.

**Fix:** Consolidate zero-check before all division operations:
```java
// Fixed - From EMU to EMU
if (cfIsEmuMember && ctIsEmuMember ...) {
    if (cfEmuRate == null || cfEmuRate.compareTo(BigDecimal.ZERO) == 0) {
        log.warning("Invalid cfEmuRate for EMU-to-EMU conversion");
        return null;
    }
    return ctEmuRate.divide(cfEmuRate, 12, RoundingMode.HALF_UP);
}
```

---

## 3. Minor Issues & Improvements

### 3.1: `getEuroCurrencyId()` Could Be Cached

**Location:** Task 7, lines 688-696

The Euro currency ID is looked up on every EMU conversion. Consider caching:
```java
private static volatile Integer cachedEuroCurrencyId;

private static Integer getEuroCurrencyId() {
    if (cachedEuroCurrencyId != null) return cachedEuroCurrencyId;
    // ... lookup ...
    cachedEuroCurrencyId = euroId;
    return euroId;
}
```

Note: MCurrency already caches, so this is low priority.

---

### 3.2: SQL Callers Have Repetitive Null-Handling Code

**Location:** Task 4, 8, 11, 13 - SqlFunctionCaller methods

Each method repeats:
```java
if (currencyId != null) {
    pstmt.setInt(2, currencyId);
} else {
    pstmt.setNull(2, java.sql.Types.INTEGER);
}
```

Consider utility method:
```java
private static void setNullableInt(PreparedStatement ps, int index, Integer value) throws SQLException {
    if (value != null) ps.setInt(index, value);
    else ps.setNull(index, Types.INTEGER);
}
```

---

### 3.3: Missing `@Test` Annotation Import in Test Examples

**Location:** Task 6, Step 1 - test code examples don't show imports

The test code snippets omit imports for `Timestamp`. First occurrence at Task 6 should include:
```java
import java.sql.Timestamp;
```

---

### 3.4: Integration Test Date Uses Future Year

**Location:** Task 9, lines 851-853

```java
Timestamp convDate = Timestamp.valueOf("2026-01-01 00:00:00");
```

Using future date for conversion rate lookup may not find rates in test DB. Consider using current date or known historical date with test data.

---

### 3.5: `currencyConvert` Returns Original Amount for Zero, Not Rounded

**Location:** Task 10, lines 969-972

```java
if (amount != null && (amount.compareTo(BigDecimal.ZERO) == 0
        || (curFromId != null && curFromId.equals(curToId)))) {
    return amount;
}
```

When amount is zero, it returns the original BigDecimal with its current scale. The SQL function likely returns a rounded zero. Verify SQL behavior matches.

---

### 3.6: Performance Test Threshold May Be Too Loose

**Location:** Task 14, line 1406

```java
private static final double MAX_LATENCY_RATIO = 1.30;
```

130% threshold means Java can be 30% slower than SQL. For cache-backed operations like currency lookup, Java should be faster. Consider:
- 0.8 for pure cache operations (currencyRound)
- 1.0 for rate lookups with potential DB hits

---

## 4. Questions for Clarification

1. **SQL Bug Handling:** The SQL `currencyRate` function has a bug at line 110 (`cf_IsEMUMember` repeated instead of `ct_IsEMUMember`). Should the Java implementation replicate this bug for parity, or should the SQL be fixed first?

2. **5-Parameter Overload Usage:** How many call sites use the 5-parameter `currencyBase(amount, curFromId, convDate, clientId, orgId)` overload? Is migrating it essential for Wave 1?

3. **Test Data Strategy:** Should tests use hardcoded IDs (faster, fragile) or dynamic lookup (slower, portable)? Is there a standard test data fixture for ADempiere?

4. **Precision Requirements:** For shadow comparison, what's the acceptable tolerance for rate calculations? 6 decimal places? 10? The current plan uses exact comparison which may cause false positives.

5. **getDate() vs System.currentTimeMillis():** The SQL uses ADempiere's `getdate()` function for default date. Does this behave identically to Java's `new Timestamp(System.currentTimeMillis())`? Timezone differences?

---

## 5. Final Recommendation

**Approve with Changes**

The plan is well-structured and follows established patterns, but requires the following changes before implementation:

### Must Fix (Blocking):
1. **Add logging to CurrencyFunctions class** (Issue 2.2)
2. **Fix BigDecimalComparator to use tolerance** (Issue 2.4)
3. **Add missing 5-parameter currencyBase overload** (Issue 2.3)
4. **Fix performance test ThreadLocal pattern** (Issue 2.5)
5. **Document or fix EMU-to-EMU SQL bug** (Issue 2.1)

### Should Fix (Non-Blocking):
1. Fix division-by-zero edge case in EMU logic (Issue 2.7)
2. Make test data dynamic or document ID requirements (Issue 2.6)
3. Add utility method for nullable PreparedStatement parameters (Issue 3.2)

### Verify Before Cutover:
1. Confirm `getdate()` vs Java date semantics match
2. Run shadow mode for 7 days with tolerance-based comparator
3. Verify 5-param currencyBase call sites are covered

---

**Summary:** Solid plan requiring targeted improvements to logging, comparison tolerance, and completeness before production deployment.
