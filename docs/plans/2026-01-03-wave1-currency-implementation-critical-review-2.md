# Critical Implementation Review #2: Wave 1 Currency Functions

**Plan Reviewed:** `docs/plans/2026-01-03-wave1-currency-implementation.md`
**Reviewer Role:** Senior Staff Software Engineer / Strict Code Reviewer
**Review Date:** 2026-01-03
**Review Type:** Follow-up review after plan revision

---

## 1. Overall Assessment

**Improvements Since Review #1:**
- Architectural decisions now properly documented (EMU bug fix, tolerance, test data)
- Task 0 added for SQL bug fix before Java implementation
- Task 13 added for 5-parameter `currencyBase` overload
- BigDecimalComparator now has CURRENCY tolerance (6 decimal places)
- Performance tests use instance fields instead of ThreadLocal
- SqlFunctionCaller utility methods added for null handling
- Comprehensive logging added to CurrencyFunctions

**Remaining Concerns:**
- **Task 0 SQL fix is an incomplete template** - cannot be executed as written
- **Thread safety issues in Euro currency caching** - benign race but no cache invalidation
- **ShadowExecutor interface assumptions** - not verified against Wave 0 implementation
- **Missing exception class definition** - SqlFunctionException not defined
- **Performance test parallelization risk** - instance field shared across methods if parallel

---

## 2. Critical Issues

### Issue 2.1: Task 0 SQL Fix Template is Not Executable (Infeasible Step)

**Location:** Task 0, Step 2 (lines 134-161)

**Problem:** The SQL migration script is a template, not executable code:
```sql
CREATE OR REPLACE FUNCTION currencyRate(
    ...
) RETURNS NUMERIC AS $$
-- [Full function body with fix at line 110]
-- Change: cf_IsEMUMember = 'Y' AND cf_IsEMUMember = 'Y'
-- To:     cf_IsEMUMember = 'Y' AND ct_IsEMUMember = 'Y'
$$ LANGUAGE plpgsql;
```

The implementer cannot execute this without:
1. Finding the original function at `db/ddlutils/postgresql/functions/C_Currency_Rate.sql`
2. Copying the entire ~150 line function body
3. Applying the single-line fix at line 110
4. Validating the result compiles

**Why it matters:**
- Task 0 is a prerequisite for correct Java implementation
- Incomplete template makes task infeasible without additional research
- Risk of incorrect copy-paste introducing new bugs

**Fix:** Either:
1. Include the full corrected function body in the migration script
2. Change approach to use sed/patch-style diff that modifies existing function
3. Add explicit step: "Copy content from C_Currency_Rate.sql, then apply fix"

---

### Issue 2.2: Thread Safety in Euro Currency Cache (Race Condition)

**Location:** Task 7, lines 1040-1055 - `cachedEuroCurrencyId` and `getEuroCurrencyId()`

**Problem:** The caching pattern has a check-then-act race:
```java
private static volatile Integer cachedEuroCurrencyId;

private static Integer getEuroCurrencyId() {
    if (cachedEuroCurrencyId != null) {
        return cachedEuroCurrencyId;
    }
    MCurrency euro = MCurrency.get(Env.getCtx(), "EUR");
    if (euro != null && euro.get_ID() > 0) {
        cachedEuroCurrencyId = euroId;  // Multiple threads may reach here
        return cachedEuroCurrencyId;
    }
    return null;
}
```

Issues:
1. **Race condition:** Multiple threads can all see `null`, all lookup Euro, all assign
2. **Benign but wasteful:** Result is correct but performs redundant lookups
3. **No cache invalidation:** If Euro currency is added/modified after JVM starts, stale value used
4. **Code smell:** This pattern suggests AtomicReference or proper double-checked locking

**Why it matters:**
- Wasteful under high concurrency at startup
- More concerning: no way to invalidate cache if data changes
- Code review signal that threading wasn't fully considered

**Fix:** Use lazy holder pattern or explicit synchronization:
```java
private static volatile Integer cachedEuroCurrencyId;
private static final Object EURO_LOCK = new Object();

private static Integer getEuroCurrencyId() {
    Integer result = cachedEuroCurrencyId;
    if (result != null) {
        return result;
    }
    synchronized (EURO_LOCK) {
        result = cachedEuroCurrencyId;
        if (result != null) {
            return result;
        }
        MCurrency euro = MCurrency.get(Env.getCtx(), "EUR");
        if (euro != null && euro.get_ID() > 0) {
            cachedEuroCurrencyId = euro.get_ID();
            return cachedEuroCurrencyId;
        }
    }
    return null;
}
```

Or accept MCurrency's internal caching and remove the additional layer.

---

### Issue 2.3: ShadowExecutor Interface Not Verified (Compilation Risk)

**Location:** Task 18 - CurrencyFunctionRouter.java (lines 2205-2290)

**Problem:** The router assumes ShadowExecutor has this exact signature:
```java
ShadowExecutor.execute(
    String functionName,
    Object[] args,
    Supplier<BigDecimal> javaImpl,
    Supplier<BigDecimal> sqlImpl,
    BiPredicate<BigDecimal, BigDecimal> comparator
);
```

But the Wave 0 ShadowExecutor implementation is not verified in this plan. If the signature differs:
- Different parameter order
- Missing comparator parameter
- Different return type handling

The router won't compile.

**Why it matters:**
- Task 18 is critical for shadow mode validation
- Compilation failure discovered late in implementation
- May require Wave 0 ShadowExecutor modifications

**Fix:** Add verification step to Task 18:
```java
// Step 0: Verify ShadowExecutor interface
// Expected signature (from Wave 0):
// public static <T> T execute(String name, Object[] args,
//     Supplier<T> java, Supplier<T> sql, BiPredicate<T, T> comparator)
//
// If signature differs, update router or request Wave 0 enhancement
```

---

### Issue 2.4: SqlFunctionException Class Not Defined (Missing Dependency)

**Location:** Task 4, lines 611-614; similar patterns in Tasks 8, 11, 14

**Problem:** SQL caller methods throw `SqlFunctionException`:
```java
} catch (Exception e) {
    log.log(Level.WARNING, "Failed to call currencyRound()", e);
    throw new SqlFunctionException("currencyRound", e);
}
```

But this exception class is never defined in the plan. Is it:
- Part of Wave 0 infrastructure?
- A standard ADempiere exception?
- Needs to be created?

**Why it matters:**
- Code won't compile if exception class doesn't exist
- Exception handling strategy unclear

**Fix:** Either:
1. Verify `SqlFunctionException` exists in Wave 0 and import it
2. Add task to create the exception class:
```java
public class SqlFunctionException extends RuntimeException {
    private final String functionName;
    public SqlFunctionException(String functionName, Throwable cause) {
        super("SQL function call failed: " + functionName, cause);
        this.functionName = functionName;
    }
    public String getFunctionName() { return functionName; }
}
```

---

### Issue 2.5: Performance Tests Not Parallelization-Safe (Test Reliability)

**Location:** Task 15 - Wave1PerformanceTest.java (lines 1838-1983)

**Problem:** The revised approach uses instance field:
```java
private double[] ratioAccumulator;

@BeforeEach
void initAccumulator(RepetitionInfo info) {
    if (info.getCurrentRepetition() == 1) {
        ratioAccumulator = new double[MEASUREMENT_ROUNDS];
    }
}
```

With `@TestInstance(Lifecycle.PER_CLASS)`, the test instance is shared. If JUnit runs test methods in parallel:
- `testCurrencyRoundPerformance` starts, creates accumulator
- `testCurrencyRatePerformance` starts, overwrites accumulator
- Both tests write to same array, corrupting results

**Why it matters:**
- Modern test frameworks default to parallel execution for speed
- Flaky tests that pass in isolation but fail in CI pipelines
- Incorrect performance metrics lead to false confidence

**Fix:** Use method-local accumulator with final report:
```java
@RepeatedTest(MEASUREMENT_ROUNDS)
void testCurrencyRoundPerformance(RepetitionInfo info, TestReporter reporter) {
    // ... timing code ...

    if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
        // Access accumulated values from static map keyed by test name
        // Or use TestInfo to store/retrieve per-method state
    }
}
```

Or disable parallel execution in test configuration:
```java
@Execution(ExecutionMode.SAME_THREAD)
public class Wave1PerformanceTest ...
```

---

## 3. Minor Issues & Improvements

### 3.1: Missing EMU-to-EMU Integration Test After SQL Fix

**Location:** Task 9 - Wave1ShadowIntegrationTest (lines 1176-1237)

The integration tests don't include a specific test for EMU-to-EMU conversion, which was the exact bug path fixed in Task 0. Add:
```java
@Test
void currencyRate_emuToEmu_matchesSqlAfterBugFix() {
    // This tests the exact path that had the SQL bug at line 110
    // DEM -> FRF (both EMU members)
    MCurrency dem = MCurrency.get(Env.getCtx(), "DEM");
    MCurrency frf = MCurrency.get(Env.getCtx(), "FRF");
    assumeTrue(dem != null && dem.isEMUMember(), "DEM (EMU member) required");
    assumeTrue(frf != null && frf.isEMUMember(), "FRF (EMU member) required");

    // Use post-EMU date
    Timestamp convDate = Timestamp.valueOf("2002-01-01 00:00:00");

    BigDecimal javaResult = CurrencyFunctions.currencyRate(...);
    BigDecimal sqlResult = SqlFunctionCaller.callCurrencyRate(...);

    // Both should return FRF_rate / DEM_rate
    assertNotNull(javaResult, "EMU-to-EMU should have rate after entry date");
    assertEquals(0, javaResult.setScale(6, RoundingMode.HALF_UP)
                              .compareTo(sqlResult.setScale(6, RoundingMode.HALF_UP)));
}
```

---

### 3.2: Test Data Lookup API Needs Verification

**Location:** Task 2, lines 271-279 - test setup uses:
```java
MCurrency usd = MCurrency.get(Env.getCtx(), "USD");
```

But the first review suggested:
```java
new Query(Env.getCtx(), I_C_Currency.Table_Name, "ISO_Code=?", null)
    .setParameters("USD").firstId();
```

Need to verify `MCurrency.get(ctx, isoCode)` exists. Checking ADempiere MCurrency class signatures:
- `MCurrency.get(Properties ctx, int C_Currency_ID)` - by ID
- `MCurrency.get(Properties ctx, String ISO_Code)` - by ISO code (may not exist!)

If the ISO code overload doesn't exist, tests will fail to compile.

**Fix:** Verify MCurrency API or use Query-based lookup as fallback.

---

### 3.3: Migration Directory Structure Unclear

**Location:** Task 0, Task 1, Task 19 - all create files in `migration/sql/`

The directory `migration/sql/` is created without context:
- Is `migration/` at project root? Under `db/`?
- Does this directory exist or need creation?
- How are these scripts executed in deployment pipeline?

**Fix:** Add clarifying note:
```
# Directory: migration/sql/
# Location: [PROJECT_ROOT]/migration/sql/ (create if not exists)
# Deployment: Scripts are executed by deployment pipeline in alphabetical order
```

---

### 3.4: currencyConvert Zero-Amount Edge Case

**Location:** Task 10, lines 1321-1323

```java
if (amount.compareTo(BigDecimal.ZERO) == 0) {
    return curToId != null ? currencyRound(BigDecimal.ZERO, curToId, null) : BigDecimal.ZERO;
}
```

If `currencyRound` returns null (e.g., currency not found), this returns null for a zero-amount conversion. But the SQL might return `0.00` (zero with precision). This could cause shadow mismatch.

**Fix:** Handle currencyRound null case:
```java
if (amount.compareTo(BigDecimal.ZERO) == 0) {
    if (curToId == null) return BigDecimal.ZERO;
    BigDecimal rounded = currencyRound(BigDecimal.ZERO, curToId, null);
    return rounded != null ? rounded : BigDecimal.ZERO;
}
```

---

### 3.5: N+1 Query Risk in Shadow Mode

**Location:** Task 18 - CurrencyFunctionRouter

In SHADOW mode, both Java and SQL implementations are called. For `currencyRate`:
- Java: 2x MCurrency.get() + potential MConversionRate lookup
- SQL: 1x currencyRate() function call

This is 3+ DB round trips per shadow invocation. If MCurrency cache misses, performance degrades significantly.

**Recommendation:** Document this is expected in shadow mode and will improve in JAVA_ONLY mode. Consider sample_rate < 1.0 during high-traffic periods.

---

### 3.6: getClientBaseCurrency Potential NPE

**Location:** Task 12, lines 1547-1567

```java
MClientInfo clientInfo = MClientInfo.get(Env.getCtx(), clientId);
if (clientInfo == null) {
    return null;
}
int acctSchemaId = clientInfo.getC_AcctSchema1_ID();
```

If `MClientInfo.get()` returns a non-null object with uninitialized fields (constructor created but not populated), `getC_AcctSchema1_ID()` might throw NPE or return unexpected values.

**Fix:** Add explicit null check on the returned value:
```java
int acctSchemaId = clientInfo.getC_AcctSchema1_ID();
if (acctSchemaId <= 0) {
    log.fine(() -> "currencyBase: no primary acct schema for client=" + clientId);
    return null;
}
```
(Already present - verified this is handled correctly)

---

### 3.7: Performance Baseline Template Has TBD Values

**Location:** Task 16, lines 2026-2050

The baseline template has all `TBD` values which is expected, but there's no actual execution step to populate them. Add explicit step:
```bash
# Step 3: Populate baseline values
# After running performance tests, extract median values from log:
grep -E "median ratio" /tmp/wave1-perf.log | while read line; do
    echo "| $(echo $line | awk '{print $1}') | ... |"
done
```

---

## 4. Questions for Clarification

1. **ShadowExecutor Compatibility:** Does the Wave 0 ShadowExecutor accept a BiPredicate comparator parameter? If not, how should tolerance comparison be integrated?

2. **SqlFunctionException:** Does this class exist in Wave 0 infrastructure, or should it be created as part of Wave 1?

3. **MCurrency.get(ctx, isoCode):** Does this method signature exist in the current MCurrency class? The plan relies on it for dynamic test data lookup.

4. **EMU Test Data Availability:** Do test environments have DEM, FRF, and other EMU member currencies configured with proper EMU rates? Without these, EMU path tests will be skipped.

5. **Parallel Test Execution:** Is JUnit configured for parallel execution in the ADempiere test suite? This affects the performance test fix approach.

---

## 5. Final Recommendation

**Approve with Changes**

The plan has substantially improved since the first review, with most critical issues addressed. The remaining issues are less severe but should still be fixed before implementation:

### Must Fix (Blocking):

1. **Complete Task 0 SQL fix script** - Either include full function body or explicit copy instructions (Issue 2.1)
2. **Verify ShadowExecutor interface** - Add compilation check step (Issue 2.3)
3. **Define or verify SqlFunctionException** - Code won't compile without it (Issue 2.4)
4. **Fix performance test parallelization risk** - Add `@Execution(SAME_THREAD)` or use test-local storage (Issue 2.5)

### Should Fix (Non-Blocking):

1. Add EMU-to-EMU integration test to validate SQL bug fix (Issue 3.1)
2. Verify MCurrency.get(ctx, isoCode) API exists (Issue 3.2)
3. Document migration directory location (Issue 3.3)
4. Handle currencyRound null in zero-amount case (Issue 3.4)

### Acceptable as-is:

1. Thread safety in Euro cache - benign race, low priority (Issue 2.2)
2. N+1 query in shadow mode - expected behavior (Issue 3.5)

---

**Summary:** Plan is nearly ready for implementation. Address the 4 blocking issues (SQL fix completeness, interface verification, exception class, test parallelization) before proceeding. The plan demonstrates solid engineering practices with proper TDD, logging, and gradual rollout strategy.
