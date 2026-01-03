# Critical Implementation Review: Wave 2 Payment Terms Implementation

**Plan Under Review:** `docs/plans/2026-01-03-wave2-payment-terms-implementation.md`
**Reviewer Role:** Senior Staff Software Engineer (15+ years production experience)
**Review Date:** 2026-01-03
**Review Version:** 1

---

## 1. Overall Assessment

**Strengths:**
- Clear TDD approach with tests preceding implementation
- Follows established Wave 1 patterns (ShadowExecutor, Router, Comparators)
- Incremental commits with well-structured messages
- Good separation of concerns: pure functions vs routers
- Performance testing included at unit level
- Integration tests planned with database comparison

**Major Concerns:**
- **Critical logic bugs** in `nextBusinessDay` and `calculateFixedDueDate` that will cause shadow mismatches
- **N+1 query pattern** in `nextBusinessDay` with repeated DB calls per holiday
- **Missing connection handling** - null trxName risks connection leaks
- **Incomplete test coverage** for fixed due date edge cases
- **Logic divergence** between plan's Java and actual SQL implementations

---

## 2. Critical Issues

### 2.1 BUG: `nextBusinessDay` Loop Logic Incorrect

**Location:** Task 1.2, Step 3 (lines 316-341)

**Description:** The proposed implementation has a logic bug in the weekend/holiday loop:

```java
while (isHoliday) {
    // Skip weekends
    DayOfWeek dow = nextDate.getDayOfWeek();
    if (dow == DayOfWeek.SATURDAY) {
        nextDate = nextDate.plusDays(2);
    } else if (dow == DayOfWeek.SUNDAY) {
        nextDate = nextDate.plusDays(1);
    }

    // Check for holidays...
    isHoliday = false;
    if (clientId > 0) {
        // If holiday found, adds 1 day and sets isHoliday = true
    }
}
```

**Problem:** After incrementing `nextDate` due to a holiday, the loop continues but weekends are only checked at the **top** of the loop. If a holiday falls on Friday, incrementing to Saturday should then skip to Monday, but the weekend check happens **before** the loop continues.

**Why it matters:** Financial calculations will be wrong. If Christmas (Dec 25, 2026) falls on Friday, the next business day should be Monday Dec 28, but this code would return Saturday Dec 26.

**SQL Reference:** The SQL version (`nextBusinessDay.sql:35-52`) correctly handles this by:
1. First adjusting for weekends
2. Then checking holidays
3. Re-looping if a holiday was found (which rechecks weekends)

**Fix:** Move weekend check to the start of the loop body, ensuring it runs after any holiday increment:

```java
while (isHoliday) {
    isHoliday = false;  // Assume not a holiday until proven

    // First: always skip weekends
    while (isWeekend(nextDate)) {
        nextDate = nextDate.plusDays(nextDate.getDayOfWeek() == DayOfWeek.SATURDAY ? 2 : 1);
    }

    // Then: check holidays
    if (clientId > 0) {
        LocalDate holiday = getHolidayOnDate(clientId, nextDate);
        if (holiday != null) {
            nextDate = nextDate.plusDays(1);
            isHoliday = true;  // Will re-check weekends on next iteration
        }
    }
}
```

---

### 2.2 BUG: `calculateFixedDueDate` Logic Diverges from SQL

**Location:** Task 2.2, Step 3 (lines 729-767)

**Description:** The `calculateFixedDueDate` helper has logic that differs from the SQL implementation in `C_PaymentTerm_DueDays.sql`.

**SQL Logic (lines 59-103):**
```sql
FirstDay := TRUNC(DocDate, 'MM');
NoDays := extract (day from (TRUNC(DocDate) - FirstDay));
DueDate := FirstDay + (p.FixMonthDay-1);  -- Starting on 1st
DueDate := DueDate + (p.FixMonthOffset || ' month')::interval;

IF (NoDays > p.FixMonthCutoff) THEN
    DueDate := DueDate + '1 month'::interval;
END IF;
```

**Plan's Java Logic:**
```java
LocalDate calDueDate = docDate;
int maxDayCut = docDate.lengthOfMonth();
if (fixMonthCutoff > maxDayCut) {
    calDueDate = docDate.withDayOfMonth(maxDayCut);
} else {
    calDueDate = docDate.withDayOfMonth(fixMonthCutoff);
}
```

**Problem:** The Java implementation compares `docDate` directly with the cutoff date, but SQL compares `NoDays` (the day-of-month of docDate) with `FixMonthCutoff`. These are fundamentally different comparisons.

**Why it matters:** Invoice due dates will be calculated incorrectly, potentially affecting cash flow and payment schedules.

**Fix:** Match the SQL logic exactly:

```java
private static LocalDate calculateFixedDueDate(LocalDate docDate,
                                                int fixMonthDay,
                                                int fixMonthOffset,
                                                int fixMonthCutoff) {
    LocalDate firstOfMonth = docDate.withDayOfMonth(1);
    int dayOfMonth = docDate.getDayOfMonth();  // This is NoDays + 1
    int noDays = dayOfMonth - 1;  // Days since start of month

    // Calculate initial due date
    LocalDate dueDate = firstOfMonth.plusDays(fixMonthDay - 1);
    dueDate = dueDate.plusMonths(fixMonthOffset);

    // If past cutoff, add another month
    if (noDays > fixMonthCutoff) {
        dueDate = dueDate.plusMonths(1);
    }

    // Handle month-end adjustments
    int maxDay = dueDate.lengthOfMonth();
    if (fixMonthDay > maxDay) {
        dueDate = dueDate.withDayOfMonth(maxDay);
    } else if (fixMonthDay >= 30 && maxDay > fixMonthDay) {
        dueDate = dueDate.withDayOfMonth(maxDay);
    } else {
        dueDate = dueDate.withDayOfMonth(fixMonthDay);
    }

    return dueDate;
}
```

---

### 2.3 N+1 Query Pattern in `nextBusinessDay`

**Location:** Task 1.2, Step 3 (lines 346-371)

**Description:** The `getNonBusinessDays()` method is called inside a while loop:

```java
while (isHoliday) {
    // ...
    if (clientId > 0) {
        List<LocalDate> holidays = getNonBusinessDays(clientId, nextDate);  // DB query!
        for (LocalDate holiday : holidays) {
            if (nextDate.equals(holiday)) {
                nextDate = nextDate.plusDays(1);
                isHoliday = true;
                break;
            }
        }
    }
}
```

**Why it matters:** If there are N consecutive holidays, this makes N database calls. In extreme cases (e.g., holiday weeks in some countries), this degrades performance significantly and increases database load.

**Fix:** Query holidays once upfront for a reasonable date range (e.g., next 30 days):

```java
@Nullable
public static Timestamp nextBusinessDay(@Nullable Timestamp date, int clientId) {
    if (date == null) return null;

    LocalDate nextDate = date.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();

    // Pre-fetch holidays for next 30 days (covers any realistic case)
    Set<LocalDate> holidays = (clientId > 0)
        ? getNonBusinessDays(clientId, nextDate, nextDate.plusDays(30))
        : Collections.emptySet();

    boolean foundBusinessDay = false;
    while (!foundBusinessDay) {
        // Skip weekends
        nextDate = skipWeekends(nextDate);

        // Check holidays
        if (holidays.contains(nextDate)) {
            nextDate = nextDate.plusDays(1);
        } else {
            foundBusinessDay = true;
        }
    }

    return Timestamp.valueOf(nextDate.atStartOfDay());
}
```

---

### 2.4 Missing Connection/Transaction Handling

**Location:** All DB calls (lines 353, 557, 689, 864, etc.)

**Description:** All database calls use `DB.prepareStatement(sql, null)` with null trxName:

```java
try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
```

**Why it matters:**
1. Null trxName may create a new connection each time, potentially leading to connection pool exhaustion under load
2. No transaction context means these reads are independent, which could lead to inconsistent data if payment term is modified during shadow comparison
3. Doesn't follow the pattern used in `sqlj/PaymentTerm.java` which uses `Adempiere.prepareStatement(sql)`

**Fix:** Either:
1. Accept trxName as a parameter for transactional consistency
2. Use thread-local transaction context if available
3. At minimum, document this limitation and ensure connection pooling is properly configured

```java
// Option 1: Accept trxName parameter
public static Timestamp paymentTermDueDate(@Nullable Integer paymentTermId,
                                            @Nullable Timestamp docDate,
                                            @Nullable String trxName) {
    // ...
    try (PreparedStatement pstmt = DB.prepareStatement(sql, trxName)) {
```

---

### 2.5 Missing `nextBusinessDay` SQL Caller

**Location:** Task 3.1

**Description:** The plan adds SQL callers for `paymentTermDueDate`, `paymentTermDueDays`, and `paymentTermDiscount`, but not for `nextBusinessDay`. However, `nextBusinessDay` is used internally by `paymentTermDiscount` and should also be callable for shadow comparison.

**Why it matters:** Cannot independently validate `nextBusinessDay` in shadow mode, making it harder to isolate issues.

**Fix:** Add SQL caller method:

```java
/**
 * Call PostgreSQL nextBusinessDay function.
 */
@Nullable
public static Timestamp callNextBusinessDay(@Nullable Timestamp date, int clientId) {
    if (date == null) return null;

    String sql = "SELECT nextBusinessDay(?, ?)";

    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        pstmt.setTimestamp(1, date);
        pstmt.setInt(2, clientId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getTimestamp(1);
            }
        }
    } catch (SQLException e) {
        throw new SqlFunctionException("nextBusinessDay", e);
    }
    return null;
}
```

---

### 2.6 Incomplete Test Coverage for Fixed Due Date

**Location:** Task 2.1 and 2.2 tests

**Description:** The tests only cover null/zero input cases. There are no tests for:
- Fixed due date with cutoff behavior
- Month-end handling (Jan 31 + 1 month = Feb 28)
- The `FixMonthDay >= 30 && maxDay > FixMonthDay` edge case
- Negative month offset scenarios

**Why it matters:** The most complex logic (fixed due date calculation) has the weakest test coverage. This is where bugs are most likely.

**Fix:** Add comprehensive fixed due date tests:

```java
@ParameterizedTest
@CsvSource({
    // DocDate, FixMonthDay, FixMonthOffset, FixMonthCutoff, Expected
    "2026-01-10, 15, 1, 20, 2026-02-15",  // Before cutoff -> next month
    "2026-01-25, 15, 1, 20, 2026-03-15",  // After cutoff -> +1 extra month
    "2026-01-31, 31, 1, 15, 2026-03-31",  // Month-end handling
    "2026-01-31, 32, 1, 15, 2026-02-28",  // FixMonthDay > days in month
    "2026-01-15, 30, 1, 10, 2026-03-31",  // FixMonthDay=30 in 31-day month -> 31
})
void paymentTermDueDate_fixedDueDate_calculatesCorrectly(
        String docDateStr, int fixMonthDay, int fixMonthOffset,
        int fixMonthCutoff, String expectedStr) {
    // Test implementation with mock payment term
}
```

---

### 2.7 Discount Comparison Logic Inverted

**Location:** Task 2.3, Step 3 (lines 906-911)

**Description:** The Java implementation uses:

```java
if (!vPayDate.isAfter(discount1Date) && discountPct != null) {
    discount = amount.multiply(discountPct).divide(...);
}
```

But the SQL uses:

```sql
IF (Discount1Date >= TRUNC(PayDate)) THEN
    Discount := Amount * p.Discount / 100;
```

**Problem:** `!vPayDate.isAfter(discount1Date)` is equivalent to `vPayDate <= discount1Date`, which is the same as SQL's `Discount1Date >= PayDate`. This is correct.

However, the SQL checks `Discount1Date >= TRUNC(PayDate)` (truncating PayDate), while Java doesn't truncate vPayDate in the comparison. If payDate has time component, results may differ.

**Fix:** Ensure vPayDate is truncated before comparison (it already is at line 858, but verify the comparison uses truncated value):

```java
LocalDate vPayDate = (payDate != null)
    ? payDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()  // Already truncated to date
    : LocalDate.now();  // This should also be date-only
```

---

## 3. Minor Issues & Improvements

### 3.1 DRY Violation: Duplicate Fixed Due Date Calculation

**Location:** `paymentTermDueDate` (Task 2.1) and `paymentTermDueDays` (Task 2.2)

**Description:** Both functions need fixed due date calculation. The plan correctly extracts `calculateFixedDueDate` in Task 2.2, but `paymentTermDueDate` in Task 2.1 has inline logic.

**Fix:** Implement Task 2.2 first or refactor Task 2.1 to also use the helper.

---

### 3.2 Consider Caching Payment Term Data

**Description:** Each call to `paymentTermDueDate/DueDays/Discount` queries `C_PaymentTerm`. Given ~100-1000 calls/day and a finite set of payment terms, caching would improve performance.

**Fix:** Use `MPaymentTerm.get(ctx, paymentTermId, trxName)` which has built-in caching, or implement a TTL cache:

```java
private static final LoadingCache<Integer, PaymentTermData> CACHE = CacheBuilder.newBuilder()
    .expireAfterWrite(5, TimeUnit.MINUTES)
    .build(CacheLoader.from(PaymentTermFunctions::loadPaymentTerm));
```

---

### 3.3 Missing Debug Logging for Invalid Inputs

**Description:** Functions silently return null/0 for invalid inputs with no logging. This makes debugging production issues difficult.

**Fix:** Add debug-level logging:

```java
if (paymentTermId == null || paymentTermId == 0 || docDate == null) {
    if (log.isLoggable(Level.FINE)) {
        log.fine("paymentTermDueDate called with invalid inputs: " +
            "paymentTermId=" + paymentTermId + ", docDate=" + docDate);
    }
    return null;
}
```

---

### 3.4 Performance Tests Don't Test DB Path

**Location:** Task 1.3, Task 2.4

**Description:** Performance tests use `clientId=0` to skip DB lookup, or use hardcoded payment term IDs that may not exist. This doesn't validate actual production performance.

**Fix:** Add separate integration performance tests that run against actual database:

```java
@Test
@EnabledIfSystemProperty(named = "runIntegrationTests", matches = "true")
void paymentTermDueDate_performanceWithDb() {
    // Query actual payment term IDs from database
    // Run timing comparison with SQL path
}
```

---

### 3.5 Timezone Handling Fragility

**Description:** Code uses `ZoneId.systemDefault()` throughout. If JVM timezone differs from database timezone, dates could be off by a day.

**Fix:** Either:
1. Document timezone requirements
2. Use explicit UTC timezone
3. Accept timezone as configuration

---

### 3.6 `@Nullable` Annotation Inconsistency

**Description:** Some parameters use `@Nullable` (e.g., `paymentTermId` in `paymentTermDueDate`) but the method returns null for `paymentTermId == 0`, implying the Integer can also be 0 as an invalid value.

**Fix:** Document or handle consistently:

```java
/**
 * @param paymentTermId C_PaymentTerm_ID (null or 0 returns null)
 */
```

---

## 4. Questions for Clarification

1. **Transaction Context:** Should these functions participate in the caller's transaction, or are independent reads acceptable? This affects connection handling.

2. **Holiday Data Source:** The SQL queries `C_NonBusinessDay` with `AD_Client_ID`. For shadow mode, should Java use the same query or could it use existing `MNonBusinessDay` model methods?

3. **Existing SQLJ Code:** The plan notes that `sqlj/PaymentTerm.java` has equivalent methods. Should the new implementation:
   - Replace the SQLJ code entirely?
   - Delegate to the new code?
   - Coexist during migration?

4. **Currency Rounding in Discount:** The SQL uses `ROUND(discount, 2)` with fixed 2 decimals. The existing Java uses `Currency.round(discount, currencyId, "N")`. Which behavior is correct for Wave 2?

5. **Week Start Configuration:** The SQL uses locale-specific week start detection via `TO_CHAR(TO_DATE('2000-01-01'), 'D')`. Java's `DayOfWeek.SATURDAY/SUNDAY` is ISO-based. Is this a potential mismatch source in non-ISO locales?

---

## 5. Final Recommendation

**Major Revisions Needed**

The plan has solid structure and follows good patterns from Wave 1, but contains critical logic bugs that will cause shadow mode mismatches:

1. **MUST FIX:** `nextBusinessDay` loop logic (Section 2.1)
2. **MUST FIX:** `calculateFixedDueDate` divergence from SQL (Section 2.2)
3. **MUST FIX:** Add comprehensive fixed due date tests (Section 2.6)
4. **SHOULD FIX:** N+1 query in holiday lookup (Section 2.3)
5. **SHOULD FIX:** Add `nextBusinessDay` SQL caller (Section 2.5)

Before implementation:
- Review and update the code snippets to match SQL logic exactly
- Add test cases for all fixed due date edge cases
- Decide on caching and connection handling strategy

Once these issues are addressed, the plan will be ready for implementation.

---

**Appendix: Key SQL Lines to Match**

| Function | Critical SQL Lines | Key Logic |
|----------|-------------------|-----------|
| `nextBusinessDay` | 35-52 | Weekend detection using locale-aware 'D' format |
| `paymentTermDueDate` | 33-43 | Uses ADD_MONTHS function, not plusMonths |
| `paymentTermDueDays` | 59-103 | Complex calDueDate calculation with maxDayCut |
| `paymentTermDiscount` | 45-60 | Discount dates add days first, then nextBusinessDay |
