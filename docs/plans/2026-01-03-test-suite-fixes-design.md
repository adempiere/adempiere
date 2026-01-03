# Test Suite Fixes Design

**Date:** 2026-01-03
**Branch:** wave2
**Goal:** Fix 458+ failing tests in the ADempiere test suite

## Executive Summary

The test suite has 467-468 failing tests across 5 categories. All are pre-existing issues unrelated to Wave 2 changes. This design documents fixes for each category, prioritized by impact.

## Fix 1: Mockito Static Mock Support (~453 tests)

**Problem:** Tests use `Mockito.mockStatic()` but `mockito-inline` dependency is missing.

**Root cause:** The `base/test` module depends on `org.adempiere.test` which has `mockito-junit-jupiter:3.6.0`, but not `mockito-inline`. A config file exists in `org.adempiere.test` but isn't on the classpath for `base/test`.

**Solution:**

1. Add dependency to `base/test/build.gradle`:
```groovy
testImplementation 'org.mockito:mockito-inline:3.6.0'
```

2. Create config directory and file:
```
base/test/src/test/resources/mockito-extensions/org.mockito.plugins.MockMaker
```
Contents: `mock-maker-inline`

3. Update `sourceSets` in `base/test/build.gradle` to include test resources:
```groovy
test {
    java {
        srcDirs = ['src']
    }
    resources {
        srcDirs = ['src/test/resources']
    }
}
```

**Expected result:** ~453 tests pass.

## Fix 2: Test Configuration Errors (2 tests)

**Problem:** JUnit 5 requires `@BeforeEach` methods to be non-static.

### Fix 2a: UT_MPriceListVersion.java

**File:** `base/test/src/org/compiere/model/UT_MPriceListVersion.java`

**Changes:**
- Line 37: `static MPriceList priceList` → `private MPriceList priceList`
- Line 41: `static void beforeEach()` → `void beforeEach()`

### Fix 2b: UT_ImpFormat.java

**File:** `base/test/src/org/compiere/impexp/UT_ImFormat.java` (note: filename missing 'p')

**Changes:**
- Line 40: `static MImpFormat importFormat` → `private MImpFormat importFormat`
- Line 44: `static void beforeEach()` → `void beforeEach()`

**Expected result:** 2 tests pass (no more `initializationError`).

## Fix 3: Investigation-Required Tests (3 tests)

**Approach:** Quick investigation (~15 mins each), fix if simple, otherwise `@Disabled` with clear documented reason.

### Fix 3a: IT_PackOut.testPackOut() - NullPointerException

**File:** `base/test/src/org/compiere/pipo/IT_PackOut.java`

**Issue:** `IDFinder.isValidateClient()` returns null

**Investigation:**
1. Check what `IDFinder.isValidateClient()` expects
2. If needs initialization/config, add to test setup
3. If architectural issue, disable with reason

### Fix 3b: IT_DocumentEngine.whenPassedANullResultSetGetDocThrowsException()

**File:** `base/test/src/org/compiere/process/IT_DocumentEngine.java:221-230`

**Issue:** Test expects `AdempiereUserError` when passing null ResultSet, but no exception thrown

**Investigation:**
1. Check if `getDoc()` behavior changed (maybe returns null instead of throwing)
2. If behavior intentionally changed, update test expectation
3. If unclear, disable with reason

### Fix 3c: IT_MRole.testAddAccessSQL() - Flaky

**File:** `base/test/src/org/compiere/model/IT_MRole.java:39-52`

**Issue:** Alternates pass/fail. Expects exact SQL with `AD_Client_ID=0`, `AD_Org_ID=0`, `AD_User_ID=100`

**Investigation:**
1. Check if test context (logged-in user) varies between runs
2. If context-dependent, add explicit context setup
3. If race condition in `MRole.getDefault()`, disable with reason

## Execution Order

| Step | Fix | Tests Fixed |
|------|-----|-------------|
| 1 | Add `mockito-inline` + config | ~453 |
| 2 | Fix `UT_MPriceListVersion` static method | 1 |
| 3 | Fix `UT_ImpFormat` static method | 1 |
| 4 | Investigate `IT_PackOut` | 1 (or disable) |
| 5 | Investigate `IT_DocumentEngine` | 1 (or disable) |
| 6 | Investigate `IT_MRole` flaky | 1 (or disable) |

## Verification

Run after each major fix:
```bash
gradle :base:test:test
```

## Commit Strategy

- One commit per logical fix
- Clear commit messages explaining what was fixed
- Example: `fix(tests): add mockito-inline for static mock support`

## Expected Outcome

- **Best case:** All 458+ tests fixed
- **Realistic:** ~455 tests fixed, 1-3 disabled with documented reasons
