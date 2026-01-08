# Wave 4 Implementation Plan - Critical Review #2

**Reviewed:** 2026-01-07
**Reviewer:** Senior Staff Engineer
**Baseline:** `docs/plans/2026-01-07-wave4-implementation.md`

---

## 1. Overall Assessment

The implementation plan is well-structured with clear TDD workflow, logical task groupings, and comprehensive coverage of 9 functions. The plan correctly reuses existing infrastructure (ShadowExecutor, MigrationLogger, MigrationConfig) and handles stateful functions appropriately via the specialized NextIDRouter.

**Strengths:**
- Clear TDD red-green-refactor workflow
- Logical task dependency ordering
- Correct reuse of SHADOW mode infrastructure with NextIDRouter for stateful functions
- nextID implementation uses atomic `UPDATE...RETURNING` (improvement over original SQL's separate SELECT/UPDATE)
- Comprehensive rollback procedure documented

**Issues Found:** 2 blocking, 2 minor

---

## 2. Critical Issues

### 2.1 Undefined Test Constants - Tests Won't Compile

**Location:** Task 3.1, Wave4FunctionsTest.java

**Description:**
```java
@Test
void acctBalance_assetDebit_positive() {
    BigDecimal result = Wave4Functions.acctBalance(
        ASSET_ACCOUNT_ID, new BigDecimal("100.00"), new BigDecimal("30.00"));
    assertEquals(new BigDecimal("70.00"), result);
}

@Test
void acctBalance_liabilityCredit_positive() {
    BigDecimal result = Wave4Functions.acctBalance(
        LIABILITY_ACCOUNT_ID, new BigDecimal("30.00"), new BigDecimal("100.00"));
```

`ASSET_ACCOUNT_ID` and `LIABILITY_ACCOUNT_ID` are referenced but never defined in the test class.

**Why It Matters:**
- Tests will not compile
- Blocks TDD workflow for Task 3.1

**Fix:**
Define test constants or use database-independent test approach:
```java
// Option 1: Define constants with known test data IDs
private static final int ASSET_ACCOUNT_ID = 12345; // Must exist in test DB with AccountType='A'
private static final int LIABILITY_ACCOUNT_ID = 12346; // Must exist with AccountType='L'

// Option 2: Use @EnabledIfEnvironmentVariable and load from config
@EnabledIfEnvironmentVariable(named = "RUN_DB_TESTS", matches = "true")
```

---

### 2.2 productAttribute Implementation Does Not Match SQL Function

**Location:** Task 5.1, Wave4Functions.productAttribute()

**Description:**
The plan's implementation has multiple discrepancies from the actual PostgreSQL function:

**Issue A: Wrong column names**

Plan uses:
```java
"aset.SerNoCharStart, aset.SerNoCharEnd, aset.SerNoCharOverwrite, "
"aset.LotCharStart, aset.LotCharEnd, aset.LotCharOverwrite "
```

Actual columns (verified from `I_M_AttributeSet.java`):
```java
COLUMNNAME_SerNoCharSOverwrite = "SerNoCharSOverwrite"
COLUMNNAME_SerNoCharEOverwrite = "SerNoCharEOverwrite"
COLUMNNAME_LotCharSOverwrite = "LotCharSOverwrite"
COLUMNNAME_LotCharEOverwrite = "LotCharEOverwrite"
```

**Issue B: Wrong overwrite logic**

Plan checks a non-existent flag:
```java
if ("Y".equals(rs.getString("SerNoCharOverwrite")) && charStart != null) {
    serNoStart = charStart;
}
```

SQL uses COALESCE directly on the value:
```sql
COALESCE(a.SerNoCharSOverwrite, '#'::CHAR(1))
```

**Issue C: Missing IsInstanceAttribute filter**

SQL filters attributes:
```sql
INNER JOIN M_Attribute a ON (ai.M_Attribute_ID=a.M_Attribute_ID AND a.IsInstanceAttribute='Y')
```

Plan omits this filter:
```java
"INNER JOIN M_Attribute a ON ai.M_Attribute_ID = a.M_Attribute_ID "
```

**Why It Matters:**
- Query will fail with "column does not exist" errors
- Even if column names were fixed, logic produces different results than SQL
- Shadow validation will show 100% mismatch rate

**Fix:**
Rewrite to match SQL function exactly:
```java
String instanceSql = "SELECT asi.Lot, asi.SerNo, asi.GuaranteeDate, "
    + "COALESCE(aset.SerNoCharSOverwrite, '#') AS SerNoStart, "
    + "COALESCE(aset.SerNoCharEOverwrite, '') AS SerNoEnd, "
    + "COALESCE(aset.LotCharSOverwrite, '\u00AB') AS LotStart, "
    + "COALESCE(aset.LotCharEOverwrite, '\u00BB') AS LotEnd "
    + "FROM M_AttributeSetInstance asi "
    + "INNER JOIN M_AttributeSet aset ON asi.M_AttributeSet_ID = aset.M_AttributeSet_ID "
    + "WHERE asi.M_AttributeSetInstance_ID = ?";

// For attribute loop:
String attrSql = "SELECT a.Name, ai.Value "
    + "FROM M_AttributeInstance ai "
    + "INNER JOIN M_Attribute a ON (ai.M_Attribute_ID = a.M_Attribute_ID AND a.IsInstanceAttribute = 'Y') "
    + "WHERE ai.M_AttributeSetInstance_ID = ?";
```

---

## 3. Minor Issues & Improvements

### 3.1 Test Quality - Reflection-Only Verification

`SqlFunctionCallerWave4Test` and `Wave4FunctionRouterTest` only verify method existence via reflection:
```java
var method = SqlFunctionCaller.class.getMethod("callAcctBalance", ...);
assertNotNull(method);
```

**Suggestion:** Consider adding behavior tests or integration tests that verify SQL construction and result handling.

### 3.2 Inconsistent Null/Invalid Return Values

| Function | null input | 0 or negative input |
|----------|------------|---------------------|
| productAttribute | `null` | `""` |
| documentNo | `""` | `""` |
| maxpaydate | `null` | `null` |

**Suggestion:** Document the return value contract in Wave4Functions class Javadoc to ensure it matches PostgreSQL behavior.

---

## 4. Questions for Clarification

### Q1: productAttribute Date Format

The SQL function outputs guarantee date as-is:
```sql
v_NameAdd := v_NameAdd || v_GuaranteeDate || ' ';
```

The plan formats it:
```java
result.append(new SimpleDateFormat("yyyy-MM-dd").format(guaranteeDate));
```

**Question:** What format does PostgreSQL produce for `v_GuaranteeDate || ' '`? The Java should match exactly.

---

## 5. Final Recommendation

**Approve with Changes**

The plan is sound and can proceed after addressing:

1. **Define test constants** for acctBalance tests (Task 3.1)
2. **Fix productAttribute implementation** (Task 5.1):
   - Correct column names: `SerNoCharSOverwrite`, `SerNoCharEOverwrite`, `LotCharSOverwrite`, `LotCharEOverwrite`
   - Use COALESCE logic instead of overwrite flag check
   - Add `a.IsInstanceAttribute = 'Y'` filter to attribute query

---

## Summary of Required Changes

| Priority | Issue | Section | Action |
|----------|-------|---------|--------|
| **BLOCKER** | Undefined test constants | 2.1 | Define ASSET_ACCOUNT_ID, LIABILITY_ACCOUNT_ID |
| **BLOCKER** | productAttribute wrong columns | 2.2 | Use correct column names from M_AttributeSet |
| **BLOCKER** | productAttribute wrong logic | 2.2 | Use COALESCE, add IsInstanceAttribute filter |
| **MINOR** | Test quality | 3.1 | Consider adding behavior tests |
| **MINOR** | Return value consistency | 3.2 | Document null handling contract |

---

## Appendix: Verified Non-Issues

The following were initially flagged but verified as correct:

| Item | Verification |
|------|--------------|
| DUAL_WRITE mode | NextIDRouter correctly handles stateful functions using SHADOW mode with specialized logic |
| nextID concurrency | `UPDATE...RETURNING` is more atomic than original SQL's separate SELECT/UPDATE |
| SimpleDateFormat thread safety | New instance created each call - thread-safe |
| maxpaydate payment status | Original SQL also doesn't check `p.DocStatus` - plan matches correctly |
| documentNo query performance | LEFT JOINs on indexed single-row lookup are optimized by PostgreSQL |
