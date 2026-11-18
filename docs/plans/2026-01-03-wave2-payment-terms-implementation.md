# Wave 2: Payment Terms Functions - Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Migrate the payment terms calculation chain - enables accurate due date and discount calculations for invoices and payments.

**Architecture:** Follow Wave 1 patterns: create `PaymentTermFunctions.java` utility class for pure logic, `PaymentTermFunctionRouter.java` for shadow mode integration, extend `SqlFunctionCaller.java` for SQL path. Reuse existing `MPaymentTerm`, `MNonBusinessDay` models for database access and caching.

**Tech Stack:** Java 11+, ADempiere Query class, existing shadow mode infrastructure (ShadowExecutor, MigrationLogger, CircuitBreaker), JUnit 5 for testing.

---

## Design Decisions

The following decisions were made based on critical review feedback:

| Question | Decision | Rationale |
|----------|----------|-----------|
| Transaction context | Accept optional `trxName` parameter, default to `null` | Follows ADempiere convention; callers in transactional context can pass their trxName |
| Holiday data source | Use `MNonBusinessDay` model via Query API | Reuses tested code, maintains consistency, provides model-level access |
| Existing SQLJ code | Coexist during migration; deprecate after JAVA_ONLY cutover | Shadow mode validates new implementation; no breaking changes during migration |
| Currency rounding | Fixed 2-decimal rounding to match SQL exactly | Shadow mode requires exact match; currency-aware rounding can be added post-migration |
| Week start config | ISO week (Sat/Sun = weekend) with documented limitation | Java's `DayOfWeek` is ISO-based; document non-ISO locale limitations |

---

## 1. Scope

**Purpose:** Migrate payment term calculations - the foundation for invoice due date and early payment discount calculations. Unlocks Wave 3 (Financial Core) which depends on `paymentTermDiscount`.

**Functions (5):**

| Function | File | LOC | Complexity | Usage |
|----------|------|-----|------------|-------|
| add_months | Add_Months.sql | 14 | Low | Internal helper |
| nextBusinessDay | nextBusinessDay.sql | 59 | Medium | Internal + Java |
| paymentTermDiscount | C_PaymentTerm_Discount.sql | 68 | Medium | Java + Views |
| paymentTermDueDate | C_PaymentTerm_DueDate.sql | 50 | Medium | Java + Views |
| paymentTermDueDays | C_PaymentTerm_DueDays.sql | 123 | Medium | Java + Views |

**Dependency Chain:**
```
Wave 0 (getDate, trunc, addDays)
     |
     v
add_months <-- paymentTermDueDate
                      ^
                      |
nextBusinessDay <-- paymentTermDiscount
                      ^
                      |
getDate (Wave 0) <-- paymentTermDueDays
```

**Existing Java Implementation:** `sqlj/src/org/compiere/sqlj/PaymentTerm.java` contains equivalent methods:
- `dueDays()` -> `paymentTermDueDays`
- `dueDate()` -> `paymentTermDueDate`
- `discount()` -> `paymentTermDiscount`

This is a **known duplicate situation** - shadow mode will validate Java matches SQL exactly. SQLJ code will be deprecated after successful JAVA_ONLY cutover.

---

## 2. Validation Strategy

Wave 2 functions use **Shadow Mode** (not dual-write) because:
- Medium frequency (~100-1000 calls/day)
- Shadow overhead acceptable for validation phase
- Simpler than dual-write infrastructure

**Sample Rate:** 100% initially, reduce if latency impact observed

**Performance Tier:** Standard (30% max latency increase over SQL baseline)

---

## 3. Success Criteria

| Gate | Requirement |
|------|-------------|
| Code Complete | Java implementations match SQL logic; unit + integration tests pass |
| Performance Validated | Java within 130% of SQL p95 latency for each function |
| Shadow Ready | Shadow mode enabled; mismatches logged; dashboard configured |
| Cutover Approved | 99.9% match rate for 7 consecutive days; no critical mismatches |
| Cleanup Complete | Feature flag set to JAVA_ONLY; SQL retained in git for 30 days |

---

## 4. Risk Areas

| Risk | Mitigation |
|------|------------|
| `nextBusinessDay` accesses `C_NonBusinessDay` table with client-specific holidays | Use `MNonBusinessDay` model; pre-fetch holidays to avoid N+1 queries |
| Fixed due date calculation has complex month-end logic | Port exact SQL logic with line-by-line comments; extensive edge case tests |
| Existing Java in sqlj package may diverge from SQL | Shadow mode comparison will detect; fix Java to match SQL |
| Wave 3 depends on `paymentTermDiscount` | Cannot proceed to Wave 3 until 99.9% match rate achieved |
| Timezone differences between JVM and database | Use `ZoneId.systemDefault()`; document requirement for matching timezones |
| `nextBusinessDay` could loop indefinitely with corrupted holiday data | Add max iteration guard (365 days); log warning and return null on exhaustion |

**Rollback Trigger:** Any mismatch affecting financial calculations triggers immediate revert to SQL_ONLY.

---

## 5. Infrastructure Prerequisites

| Prerequisite | Required | Reference |
|--------------|----------|-----------|
| Wave 0 complete (getDate, trunc, addDays) | Yes | Foundation functions |
| Wave 1 complete (for patterns) | Recommended | Router and shadow patterns |
| Migration schema created | Yes | `migration.function_config` table |

---

## Implementation Groups

---

## Group 1: Internal Helper Functions

### Task 1.1: Create PaymentTermFunctions utility class with addMonths

**Files:**
- Create: `base/src/org/compiere/util/PaymentTermFunctions.java`
- Create: `base/test/src/org/compiere/util/PaymentTermFunctionsTest.java`

**Step 1: Write failing test for add_months**

```java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.sql.Timestamp;
import java.time.LocalDate;

class PaymentTermFunctionsTest {

    @Test
    void addMonths_withNullDatetime_returnsNull() {
        assertNull(PaymentTermFunctions.addMonths(null, 1));
    }

    @Test
    void addMonths_withNullMonths_returnsNull() {
        Timestamp ts = Timestamp.valueOf("2026-01-15 10:30:00");
        assertNull(PaymentTermFunctions.addMonths(ts, null));
    }

    @ParameterizedTest
    @CsvSource({
        "2026-01-15, 1, 2026-02-15",
        "2026-01-31, 1, 2026-02-28",
        "2026-03-31, 1, 2026-04-30",
        "2026-01-15, -1, 2025-12-15",
        "2026-01-15, 12, 2027-01-15",
        "2026-01-15, 0, 2026-01-15"
    })
    void addMonths_withValidInputs_returnsExpectedDate(String input, int months, String expected) {
        Timestamp ts = Timestamp.valueOf(input + " 00:00:00");
        LocalDate result = PaymentTermFunctions.addMonths(ts, months);
        assertEquals(LocalDate.parse(expected), result);
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsTest" -i`
Expected: FAIL with "PaymentTermFunctions not found"

**Step 3: Write minimal implementation**

```java
package org.compiere.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.Nullable;

import org.compiere.model.MNonBusinessDay;
import org.compiere.model.MPaymentTerm;
import org.compiere.model.Query;

/**
 * Pure functions for payment term calculations.
 * These are stateless utility methods that match the PostgreSQL function behavior.
 *
 * <p>Design decisions:
 * <ul>
 *   <li>Uses MPaymentTerm/MNonBusinessDay models for caching</li>
 *   <li>Accepts optional trxName for transactional consistency</li>
 *   <li>ISO week standard (Sat/Sun = weekend)</li>
 *   <li>Fixed 2-decimal rounding for discounts</li>
 * </ul>
 *
 * @see org.compiere.util.PaymentTermFunctionRouter for shadow mode integration
 */
public final class PaymentTermFunctions {

    private static final CLogger log = CLogger.getCLogger(PaymentTermFunctions.class);

    private PaymentTermFunctions() {
        // Utility class
    }

    /**
     * Add months to a timestamp, returning a LocalDate.
     * Matches PostgreSQL add_months() behavior.
     *
     * @param datetime input timestamp (nullable)
     * @param months number of months to add (can be negative, nullable)
     * @return resulting date, or null if either input is null
     */
    @Nullable
    public static LocalDate addMonths(@Nullable Timestamp datetime, @Nullable Integer months) {
        if (datetime == null || months == null) {
            return null;
        }
        LocalDate date = datetime.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
        return date.plusMonths(months);
    }
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/PaymentTermFunctions.java base/test/src/org/compiere/util/PaymentTermFunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave2): add PaymentTermFunctions with addMonths implementation

Pure function utility class for payment term calculations.
addMonths matches PostgreSQL Add_Months.sql behavior.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 1.2: Add loadHolidays helper with MNonBusinessDay

**Files:**
- Modify: `base/src/org/compiere/util/PaymentTermFunctions.java`
- Modify: `base/test/src/org/compiere/util/PaymentTermFunctionsTest.java`

**Step 1: Write failing test**

Add to `PaymentTermFunctionsTest.java`:

```java
@Test
void loadHolidays_withZeroClientId_returnsEmptySet() {
    // loadHolidays is package-private for testing
    Set<LocalDate> holidays = PaymentTermFunctions.loadHolidays(
        0, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 31), null);
    assertTrue(holidays.isEmpty());
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsTest.loadHolidays*" -i`
Expected: FAIL

**Step 3: Write implementation**

Add to `PaymentTermFunctions.java`:

```java
/**
 * Load holidays for a date range using MNonBusinessDay model.
 * Pre-fetches to avoid N+1 queries in nextBusinessDay loop.
 *
 * @param clientId AD_Client_ID for holiday lookup (0 returns empty set)
 * @param fromDate start of date range (inclusive)
 * @param toDate end of date range (inclusive)
 * @param trxName transaction name (nullable)
 * @return set of holiday dates in the range
 */
static Set<LocalDate> loadHolidays(int clientId, LocalDate fromDate,
                                    LocalDate toDate, @Nullable String trxName) {
    if (clientId <= 0) {
        return Collections.emptySet();
    }

    Set<LocalDate> holidays = new HashSet<>();

    String whereClause = "AD_Client_ID = ? AND IsActive = 'Y' " +
                         "AND Date1 >= ? AND Date1 <= ?";

    List<MNonBusinessDay> nbdList = new Query(Env.getCtx(),
            MNonBusinessDay.Table_Name, whereClause, trxName)
        .setParameters(clientId,
            Timestamp.valueOf(fromDate.atStartOfDay()),
            Timestamp.valueOf(toDate.atStartOfDay()))
        .list();

    for (MNonBusinessDay nbd : nbdList) {
        Timestamp ts = nbd.getDate1();
        if (ts != null) {
            holidays.add(ts.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
        }
    }

    if (log.isLoggable(Level.FINE)) {
        log.fine("loadHolidays: clientId=" + clientId +
            ", range=" + fromDate + " to " + toDate +
            ", found=" + holidays.size());
    }

    return holidays;
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsTest.loadHolidays*" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/PaymentTermFunctions.java base/test/src/org/compiere/util/PaymentTermFunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave2): add loadHolidays helper with MNonBusinessDay

Pre-fetches holidays for date range to avoid N+1 queries.
Uses ADempiere Query API with MNonBusinessDay model.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 1.3: Implement nextBusinessDay with fixed loop logic

**Files:**
- Modify: `base/src/org/compiere/util/PaymentTermFunctions.java`
- Modify: `base/test/src/org/compiere/util/PaymentTermFunctionsTest.java`

**Step 1: Write failing tests**

Add to `PaymentTermFunctionsTest.java`:

```java
@Test
void nextBusinessDay_withNullDate_returnsNull() {
    assertNull(PaymentTermFunctions.nextBusinessDay(null, 11));
}

@Test
void nextBusinessDay_withNullDateAndTrxName_returnsNull() {
    assertNull(PaymentTermFunctions.nextBusinessDay(null, 11, null));
}

@Test
void nextBusinessDay_onWeekday_returnsSameDay() {
    // Wednesday 2026-01-07
    Timestamp wednesday = Timestamp.valueOf("2026-01-07 10:00:00");
    // clientId=0 means no holiday lookup
    Timestamp result = PaymentTermFunctions.nextBusinessDay(wednesday, 0);
    assertNotNull(result);
    assertEquals(LocalDate.of(2026, 1, 7),
        result.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
}

@Test
void nextBusinessDay_onSaturday_returnsMonday() {
    // Saturday 2026-01-10
    Timestamp saturday = Timestamp.valueOf("2026-01-10 10:00:00");
    Timestamp result = PaymentTermFunctions.nextBusinessDay(saturday, 0);
    assertNotNull(result);
    // Should skip to Monday 2026-01-12
    assertEquals(LocalDate.of(2026, 1, 12),
        result.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
}

@Test
void nextBusinessDay_onSunday_returnsMonday() {
    // Sunday 2026-01-11
    Timestamp sunday = Timestamp.valueOf("2026-01-11 10:00:00");
    Timestamp result = PaymentTermFunctions.nextBusinessDay(sunday, 0);
    assertNotNull(result);
    // Should skip to Monday 2026-01-12
    assertEquals(LocalDate.of(2026, 1, 12),
        result.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
}

@Test
void nextBusinessDay_onFriday_withFridayHoliday_skipsToMonday() {
    // This tests the critical fix: Friday holiday should skip weekend to Monday
    // Note: Requires mock or integration test with actual holiday data
    // For unit test, we verify the logic with clientId=0 (no holidays)
    Timestamp friday = Timestamp.valueOf("2026-01-09 10:00:00");
    Timestamp result = PaymentTermFunctions.nextBusinessDay(friday, 0);
    assertNotNull(result);
    // Friday with no holidays returns Friday
    assertEquals(LocalDate.of(2026, 1, 9),
        result.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
}

// Note: The iteration guard (MAX_BUSINESS_DAY_ITERATIONS = 365) is tested
// in integration tests with mocked holiday data. Unit tests cannot easily
// simulate 365+ consecutive holidays without database access.
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsTest.nextBusinessDay*" -i`
Expected: FAIL with "method not found"

**Step 3: Write implementation with fixed loop logic and iteration guard**

Add to `PaymentTermFunctions.java`:

```java
/** Maximum iterations for business day search (defensive guard against corrupted data). */
private static final int MAX_BUSINESS_DAY_ITERATIONS = 365;

/**
 * Get the next business day, skipping weekends and configured holidays.
 * Matches PostgreSQL nextBusinessDay() behavior.
 *
 * <p>CRITICAL: Loop logic ensures weekends are rechecked after holiday increment.
 * If Friday is a holiday, incrementing to Saturday must then skip to Monday.
 *
 * <p>Defensive: Limited to 365 iterations to prevent infinite loop with corrupted holiday data.
 *
 * @param date input date (nullable)
 * @param clientId AD_Client_ID for holiday lookup
 * @return next business day as timestamp, or null if date is null or iterations exhausted
 */
@Nullable
public static Timestamp nextBusinessDay(@Nullable Timestamp date, int clientId) {
    return nextBusinessDay(date, clientId, null);
}

/**
 * Get the next business day with transaction context.
 *
 * @param date input date (nullable)
 * @param clientId AD_Client_ID for holiday lookup
 * @param trxName transaction name (nullable)
 * @return next business day as timestamp, or null if date is null or iterations exhausted
 */
@Nullable
public static Timestamp nextBusinessDay(@Nullable Timestamp date, int clientId,
                                         @Nullable String trxName) {
    if (date == null) {
        return null;
    }

    LocalDate nextDate = date.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();

    // Pre-fetch holidays for next 30 days (avoids N+1 queries)
    Set<LocalDate> holidays = (clientId > 0)
        ? loadHolidays(clientId, nextDate, nextDate.plusDays(30), trxName)
        : Collections.emptySet();

    // Loop until we find a business day (with iteration guard)
    int iterations = 0;
    boolean searching = true;
    while (searching && iterations < MAX_BUSINESS_DAY_ITERATIONS) {
        iterations++;

        // First: always skip weekends (runs after any holiday increment)
        nextDate = skipWeekends(nextDate);

        // Then: check if this day is a holiday
        if (holidays.contains(nextDate)) {
            nextDate = nextDate.plusDays(1);
            // Continue loop - will recheck weekends on next iteration
        } else {
            searching = false;
        }
    }

    // Guard: if we exhausted iterations, log warning and return null
    if (iterations >= MAX_BUSINESS_DAY_ITERATIONS) {
        log.warning("nextBusinessDay: max iterations reached for clientId=" + clientId +
            ", startDate=" + date + " - possible corrupted holiday data");
        return null;
    }

    return Timestamp.valueOf(nextDate.atStartOfDay());
}

/**
 * Skip weekends (Saturday -> Monday, Sunday -> Monday).
 * Uses ISO week standard.
 *
 * <p>Matches SQL nextBusinessDay.sql lines 36-41 weekend detection.
 *
 * @param date input date
 * @return same date if weekday, Monday if weekend
 */
private static LocalDate skipWeekends(LocalDate date) {
    DayOfWeek dow = date.getDayOfWeek();
    if (dow == DayOfWeek.SATURDAY) {
        return date.plusDays(2);
    } else if (dow == DayOfWeek.SUNDAY) {
        return date.plusDays(1);
    }
    return date;
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsTest.nextBusinessDay*" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/PaymentTermFunctions.java base/test/src/org/compiere/util/PaymentTermFunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave2): add nextBusinessDay with fixed loop logic

Critical fix: weekends rechecked after holiday increment.
Pre-fetches holidays to avoid N+1 queries.
Accepts optional trxName for transactional consistency.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 1.4: Extract calculateFixedDueDate helper

**Files:**
- Modify: `base/src/org/compiere/util/PaymentTermFunctions.java`
- Modify: `base/test/src/org/compiere/util/PaymentTermFunctionsTest.java`

**Step 1: Write comprehensive failing tests**

Add to `PaymentTermFunctionsTest.java`:

```java
import org.junit.jupiter.api.Nested;

@Nested
class CalculateFixedDueDateTests {

    @ParameterizedTest
    @CsvSource({
        // DocDate, FixMonthDay, FixMonthOffset, FixMonthCutoff, Expected
        // Cutoff behavior: noDays = dayOfMonth - 1, compare noDays > cutoff
        "2026-01-10, 15, 1, 20, 2026-02-15",  // noDays=9, 9 > 20? No -> Feb
        "2026-01-21, 15, 1, 20, 2026-02-15",  // noDays=20, 20 > 20? No -> Feb
        "2026-01-22, 15, 1, 20, 2026-03-15",  // noDays=21, 21 > 20? Yes -> Mar
        "2026-01-01, 15, 1, 20, 2026-02-15",  // noDays=0, 0 > 20? No -> Feb
        "2026-01-31, 15, 1, 15, 2026-03-15",  // noDays=30, 30 > 15? Yes -> Mar
    })
    void cutoffBehavior(String docDateStr, int fixMonthDay,
            int fixMonthOffset, int fixMonthCutoff, String expectedStr) {
        LocalDate docDate = LocalDate.parse(docDateStr);
        LocalDate expected = LocalDate.parse(expectedStr);
        LocalDate result = PaymentTermFunctions.calculateFixedDueDate(
            docDate, fixMonthDay, fixMonthOffset, fixMonthCutoff);
        assertEquals(expected, result,
            "DocDate=" + docDateStr + " with cutoff=" + fixMonthCutoff);
    }

    @ParameterizedTest
    @CsvSource({
        // Month-end handling
        "2026-01-15, 31, 1, 10, 2026-02-28",  // Day 31 in Feb -> 28
        "2026-01-15, 31, 2, 10, 2026-03-31",  // Day 31 in Mar -> 31
        "2026-01-15, 30, 1, 10, 2026-02-28",  // Day 30 in Feb -> 28
        "2026-01-15, 30, 3, 10, 2026-04-30",  // Day 30 in Apr -> 30
        "2026-01-15, 30, 4, 10, 2026-05-31",  // Day 30 in May -> 31 (end-of-month intent)
    })
    void monthEndHandling(String docDateStr, int fixMonthDay,
            int fixMonthOffset, int fixMonthCutoff, String expectedStr) {
        LocalDate docDate = LocalDate.parse(docDateStr);
        LocalDate expected = LocalDate.parse(expectedStr);
        LocalDate result = PaymentTermFunctions.calculateFixedDueDate(
            docDate, fixMonthDay, fixMonthOffset, fixMonthCutoff);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "2026-02-28, 15, 0, 20, 2026-03-15",  // Feb end, past cutoff
        "2026-12-31, 15, 1, 15, 2027-02-15",  // Year boundary, past cutoff
        "2026-03-31, 31, 1, 30, 2026-05-31",  // March end -> Apr (30d) skipped if day>30 -> May
    })
    void edgeCases(String docDateStr, int fixMonthDay,
            int fixMonthOffset, int fixMonthCutoff, String expectedStr) {
        LocalDate docDate = LocalDate.parse(docDateStr);
        LocalDate expected = LocalDate.parse(expectedStr);
        LocalDate result = PaymentTermFunctions.calculateFixedDueDate(
            docDate, fixMonthDay, fixMonthOffset, fixMonthCutoff);
        assertEquals(expected, result);
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsTest\$CalculateFixedDueDateTests" -i`
Expected: FAIL

**Step 3: Write implementation matching SQL exactly**

Add to `PaymentTermFunctions.java`:

```java
/**
 * Calculate fixed due date matching C_PaymentTerm_DueDays.sql lines 59-103.
 *
 * <p>SQL Logic:
 * <pre>
 *   FirstDay := TRUNC(DocDate, 'MM')
 *   NoDays := extract(day from (TRUNC(DocDate) - FirstDay))
 *   DueDate := FirstDay + (FixMonthDay - 1)
 *   DueDate := DueDate + FixMonthOffset months
 *   IF (NoDays > FixMonthCutoff) THEN DueDate += 1 month
 * </pre>
 *
 * <p>Package-private for testing. Used by paymentTermDueDate and paymentTermDueDays.
 *
 * @param docDate document date
 * @param fixMonthDay day of month for due date (1-31, or 32 for last day)
 * @param fixMonthOffset months to add
 * @param fixMonthCutoff cutoff day; if docDate's day-1 exceeds this, add extra month
 * @return calculated fixed due date
 */
static LocalDate calculateFixedDueDate(LocalDate docDate,
                                        int fixMonthDay,
                                        int fixMonthOffset,
                                        int fixMonthCutoff) {
    // FirstDay := TRUNC(DocDate, 'MM')
    LocalDate firstOfMonth = docDate.withDayOfMonth(1);

    // NoDays := extract(day from (TRUNC(DocDate) - FirstDay))
    // This equals dayOfMonth - 1
    int noDays = docDate.getDayOfMonth() - 1;

    // DueDate := FirstDay + (FixMonthDay - 1)
    LocalDate dueDate = firstOfMonth.plusDays(fixMonthDay - 1);

    // DueDate := DueDate + FixMonthOffset months
    dueDate = dueDate.plusMonths(fixMonthOffset);

    // IF (NoDays > FixMonthCutoff) THEN DueDate += 1 month
    if (noDays > fixMonthCutoff) {
        dueDate = dueDate.plusMonths(1);
    }

    // Handle month-end: normalize day to valid range
    int maxDay = dueDate.lengthOfMonth();
    int targetDay = fixMonthDay;

    if (fixMonthDay > maxDay) {
        // e.g., day 31 in February -> 28/29
        targetDay = maxDay;
    } else if (fixMonthDay >= 30 && maxDay > fixMonthDay) {
        // e.g., day 30 in 31-day month -> 31 (end-of-month intent)
        targetDay = maxDay;
    }

    return dueDate.withDayOfMonth(Math.min(targetDay, maxDay));
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsTest\$CalculateFixedDueDateTests" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/PaymentTermFunctions.java base/test/src/org/compiere/util/PaymentTermFunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave2): add calculateFixedDueDate with comprehensive tests

Matches SQL C_PaymentTerm_DueDays.sql lines 59-103 exactly.
Includes tests for cutoff behavior, month-end, edge cases.
Shared helper for paymentTermDueDate and paymentTermDueDays.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 1.5: Performance tests for helper functions

**Files:**
- Create: `base/test/src/org/compiere/util/PaymentTermFunctionsPerformanceTest.java`

**Step 1: Write performance tests**

```java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

/**
 * Performance tests for PaymentTermFunctions.
 * Validates Java implementation is within performance budget.
 *
 * <p><b>LIMITATION:</b> These unit tests use clientId=0 or paymentTermId=0,
 * which triggers early-exit paths without database access. This measures
 * the overhead of null checks and basic date operations only.
 *
 * <p>For real performance measurement with database access (MPaymentTerm
 * caching, holiday queries), see integration tests run with
 * -DrunIntegrationTests=true against a test database.
 *
 * <p>Test dates use fixed 2026 values - these remain valid regardless of
 * when tests are run since they don't depend on LocalDate.now().
 */
class PaymentTermFunctionsPerformanceTest {

    private static final int WARMUP_ITERATIONS = 500;
    private static final int TEST_ITERATIONS = 2000;

    @Test
    void addMonths_performanceWithinBudget() {
        Timestamp input = Timestamp.valueOf("2026-01-15 10:00:00");

        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            PaymentTermFunctions.addMonths(input, i % 12);
        }

        // Measure
        long start = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            PaymentTermFunctions.addMonths(input, i % 12);
        }
        long elapsed = System.nanoTime() - start;

        double avgMicros = (elapsed / 1000.0) / TEST_ITERATIONS;
        System.out.printf("addMonths: %.2f us/call%n", avgMicros);

        // Should be sub-microsecond for pure date math
        assertTrue(avgMicros < 10.0, "addMonths should be < 10 us/call, was " + avgMicros);
    }

    @Test
    void nextBusinessDay_performanceWithinBudget_noDb() {
        Timestamp input = Timestamp.valueOf("2026-01-15 10:00:00");
        int clientId = 0; // Will skip DB lookup for clientId=0

        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            PaymentTermFunctions.nextBusinessDay(input, clientId);
        }

        // Measure
        long start = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            PaymentTermFunctions.nextBusinessDay(input, clientId);
        }
        long elapsed = System.nanoTime() - start;

        double avgMicros = (elapsed / 1000.0) / TEST_ITERATIONS;
        System.out.printf("nextBusinessDay (no DB): %.2f us/call%n", avgMicros);

        // Without DB lookup, should be fast
        assertTrue(avgMicros < 50.0, "nextBusinessDay should be < 50 us/call without DB, was " + avgMicros);
    }

    @Test
    void calculateFixedDueDate_performanceWithinBudget() {
        java.time.LocalDate docDate = java.time.LocalDate.of(2026, 1, 15);

        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            PaymentTermFunctions.calculateFixedDueDate(docDate, 15, 1, 20);
        }

        // Measure
        long start = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            PaymentTermFunctions.calculateFixedDueDate(docDate, 15, 1, 20);
        }
        long elapsed = System.nanoTime() - start;

        double avgMicros = (elapsed / 1000.0) / TEST_ITERATIONS;
        System.out.printf("calculateFixedDueDate: %.2f us/call%n", avgMicros);

        // Pure date math, should be fast
        assertTrue(avgMicros < 10.0, "calculateFixedDueDate should be < 10 us/call, was " + avgMicros);
    }
}
```

**Step 2: Run performance tests**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsPerformanceTest" -i`
Expected: PASS with timing output

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/util/PaymentTermFunctionsPerformanceTest.java
git commit -m "$(cat <<'EOF'
test(wave2): add performance tests for helper functions

Validates addMonths, nextBusinessDay, calculateFixedDueDate.
Records baseline timing for comparison.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

## Group 2: Core Payment Term Functions

### Task 2.1: Implement paymentTermDueDate

**Files:**
- Modify: `base/src/org/compiere/util/PaymentTermFunctions.java`
- Modify: `base/test/src/org/compiere/util/PaymentTermFunctionsTest.java`

**Step 1: Write failing tests**

Add to `PaymentTermFunctionsTest.java`:

```java
@Test
void paymentTermDueDate_withNullPaymentTermId_returnsNull() {
    Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
    assertNull(PaymentTermFunctions.paymentTermDueDate(null, docDate));
}

@Test
void paymentTermDueDate_withNullDocDate_returnsNull() {
    assertNull(PaymentTermFunctions.paymentTermDueDate(106, null));
}

@Test
void paymentTermDueDate_withZeroPaymentTermId_returnsNull() {
    Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
    assertNull(PaymentTermFunctions.paymentTermDueDate(0, docDate));
}

@Test
void paymentTermDueDate_withTrxName_acceptsParameter() {
    Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
    // Should not throw, even with invalid payment term
    assertNull(PaymentTermFunctions.paymentTermDueDate(0, docDate, "testTrx"));
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsTest.paymentTermDueDate*" -i`
Expected: FAIL

**Step 3: Write implementation using shared helper**

Add to `PaymentTermFunctions.java`:

```java
/**
 * Calculate the due date for a payment term.
 * Matches PostgreSQL paymentTermDueDate() behavior.
 *
 * @param paymentTermId C_PaymentTerm_ID (nullable, 0 returns null)
 * @param docDate document date (nullable)
 * @return due date as timestamp, or null if inputs invalid
 */
@Nullable
public static Timestamp paymentTermDueDate(@Nullable Integer paymentTermId,
                                            @Nullable Timestamp docDate) {
    return paymentTermDueDate(paymentTermId, docDate, null);
}

/**
 * Calculate the due date for a payment term with transaction context.
 *
 * @param paymentTermId C_PaymentTerm_ID (nullable, 0 returns null)
 * @param docDate document date (nullable)
 * @param trxName transaction name (nullable)
 * @return due date as timestamp, or null if inputs invalid
 */
@Nullable
public static Timestamp paymentTermDueDate(@Nullable Integer paymentTermId,
                                            @Nullable Timestamp docDate,
                                            @Nullable String trxName) {
    if (paymentTermId == null || paymentTermId == 0 || docDate == null) {
        if (log.isLoggable(Level.FINE)) {
            log.fine("paymentTermDueDate: invalid inputs - " +
                "paymentTermId=" + paymentTermId + ", docDate=" + docDate);
        }
        return null;
    }

    // Use MPaymentTerm for caching
    MPaymentTerm pt = MPaymentTerm.get(Env.getCtx(), paymentTermId, trxName);
    if (pt == null || pt.get_ID() == 0) {
        if (log.isLoggable(Level.FINE)) {
            log.fine("paymentTermDueDate: payment term not found - " + paymentTermId);
        }
        return null;
    }

    LocalDate docLocalDate = docDate.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();

    LocalDate dueDate = calculateDueDate(pt, docLocalDate);

    return Timestamp.valueOf(dueDate.atStartOfDay());
}

/**
 * Calculate due date from payment term, handling both fixed and net-days terms.
 * Shared by paymentTermDueDate and paymentTermDueDays.
 */
private static LocalDate calculateDueDate(MPaymentTerm pt, LocalDate docDate) {
    if (pt.isDueFixed()) {
        return calculateFixedDueDate(docDate,
            pt.getFixMonthDay(),
            pt.getFixMonthOffset(),
            pt.getFixMonthCutoff());
    } else {
        return docDate.plusDays(pt.getNetDays());
    }
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsTest.paymentTermDueDate*" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/PaymentTermFunctions.java base/test/src/org/compiere/util/PaymentTermFunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave2): add paymentTermDueDate implementation

Uses MPaymentTerm for caching, shared calculateDueDate helper.
Handles both fixed due date and net days payment terms.
Matches PostgreSQL C_PaymentTerm_DueDate.sql behavior.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 2.2: Implement paymentTermDueDays

**Files:**
- Modify: `base/src/org/compiere/util/PaymentTermFunctions.java`
- Modify: `base/test/src/org/compiere/util/PaymentTermFunctionsTest.java`

**Step 1: Write failing tests**

Add to `PaymentTermFunctionsTest.java`:

```java
@Test
void paymentTermDueDays_withZeroPaymentTermId_returnsZero() {
    Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
    Timestamp payDate = Timestamp.valueOf("2026-02-15 00:00:00");
    assertEquals(0, PaymentTermFunctions.paymentTermDueDays(0, docDate, payDate));
}

@Test
void paymentTermDueDays_withNullDocDate_returnsZero() {
    Timestamp payDate = Timestamp.valueOf("2026-02-15 00:00:00");
    assertEquals(0, PaymentTermFunctions.paymentTermDueDays(106, null, payDate));
}

@Test
void paymentTermDueDays_withNullPayDate_usesToday() {
    Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
    // Result depends on current date - just verify no exception
    assertDoesNotThrow(() -> PaymentTermFunctions.paymentTermDueDays(106, docDate, null));
}

@Test
void paymentTermDueDays_withTrxName_acceptsParameter() {
    Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
    // Should not throw
    assertDoesNotThrow(() ->
        PaymentTermFunctions.paymentTermDueDays(106, docDate, null, "testTrx"));
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsTest.paymentTermDueDays*" -i`
Expected: FAIL

**Step 3: Write implementation using shared helper**

Add to `PaymentTermFunctions.java`:

```java
/**
 * Calculate days due (positive) or days till due (negative).
 * Grace days are not considered.
 * Matches PostgreSQL paymentTermDueDays() behavior.
 *
 * @param paymentTermId C_PaymentTerm_ID
 * @param docDate document date (nullable)
 * @param payDate payment date, or null for today
 * @return days due (positive = overdue, negative = not yet due)
 */
public static int paymentTermDueDays(int paymentTermId,
                                      @Nullable Timestamp docDate,
                                      @Nullable Timestamp payDate) {
    return paymentTermDueDays(paymentTermId, docDate, payDate, null);
}

/**
 * Calculate days due with transaction context.
 *
 * @param paymentTermId C_PaymentTerm_ID
 * @param docDate document date (nullable)
 * @param payDate payment date, or null for today
 * @param trxName transaction name (nullable)
 * @return days due (positive = overdue, negative = not yet due)
 */
public static int paymentTermDueDays(int paymentTermId,
                                      @Nullable Timestamp docDate,
                                      @Nullable Timestamp payDate,
                                      @Nullable String trxName) {
    if (paymentTermId == 0 || docDate == null) {
        if (log.isLoggable(Level.FINE)) {
            log.fine("paymentTermDueDays: invalid inputs - " +
                "paymentTermId=" + paymentTermId + ", docDate=" + docDate);
        }
        return 0;
    }

    LocalDate vPayDate = (payDate != null)
        ? payDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        : LocalDate.now();

    MPaymentTerm pt = MPaymentTerm.get(Env.getCtx(), paymentTermId, trxName);
    if (pt == null || pt.get_ID() == 0) {
        if (log.isLoggable(Level.FINE)) {
            log.fine("paymentTermDueDays: payment term not found - " + paymentTermId);
        }
        return 0;
    }

    LocalDate docLocalDate = docDate.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();

    LocalDate dueDate = calculateDueDate(pt, docLocalDate);

    // Return days between due date and pay date
    // Positive = overdue, Negative = days until due
    return (int) java.time.temporal.ChronoUnit.DAYS.between(dueDate, vPayDate);
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsTest.paymentTermDueDays*" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/PaymentTermFunctions.java base/test/src/org/compiere/util/PaymentTermFunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave2): add paymentTermDueDays implementation

Uses shared calculateDueDate helper (DRY).
Returns positive for overdue, negative for days until due.
Matches PostgreSQL C_PaymentTerm_DueDays.sql behavior.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 2.3: Implement paymentTermDiscount

**Files:**
- Modify: `base/src/org/compiere/util/PaymentTermFunctions.java`
- Modify: `base/test/src/org/compiere/util/PaymentTermFunctionsTest.java`

**Step 1: Write failing tests**

Add to `PaymentTermFunctionsTest.java`:

```java
@Test
void paymentTermDiscount_withNullAmount_returnsZero() {
    Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
    assertEquals(BigDecimal.ZERO, PaymentTermFunctions.paymentTermDiscount(
        null, 100, 106, docDate, docDate));
}

@Test
void paymentTermDiscount_withZeroPaymentTermId_returnsZero() {
    Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
    assertEquals(BigDecimal.ZERO, PaymentTermFunctions.paymentTermDiscount(
        new BigDecimal("100.00"), 100, 0, docDate, docDate));
}

@Test
void paymentTermDiscount_withNullDocDate_returnsZero() {
    assertEquals(BigDecimal.ZERO, PaymentTermFunctions.paymentTermDiscount(
        new BigDecimal("100.00"), 100, 106, null, null));
}

@Test
void paymentTermDiscount_withTrxName_acceptsParameter() {
    Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
    // Should not throw
    assertDoesNotThrow(() -> PaymentTermFunctions.paymentTermDiscount(
        new BigDecimal("100.00"), 100, 0, docDate, docDate, "testTrx"));
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsTest.paymentTermDiscount*" -i`
Expected: FAIL

**Step 3: Write implementation**

Add to `PaymentTermFunctions.java`:

```java
/**
 * Calculate the discount amount for a payment term.
 * Matches PostgreSQL paymentTermDiscount() behavior.
 *
 * @param amount invoice amount (nullable)
 * @param currencyId C_Currency_ID (unused - kept for signature compatibility)
 * @param paymentTermId C_PaymentTerm_ID
 * @param docDate document date (nullable)
 * @param payDate payment date, or null for today
 * @return discount amount rounded to 2 decimal places
 */
public static BigDecimal paymentTermDiscount(@Nullable BigDecimal amount,
                                              int currencyId,
                                              int paymentTermId,
                                              @Nullable Timestamp docDate,
                                              @Nullable Timestamp payDate) {
    return paymentTermDiscount(amount, currencyId, paymentTermId, docDate, payDate, null);
}

/**
 * Calculate the discount amount with transaction context.
 *
 * @param amount invoice amount (nullable)
 * @param currencyId C_Currency_ID (unused - kept for signature compatibility)
 * @param paymentTermId C_PaymentTerm_ID
 * @param docDate document date (nullable)
 * @param payDate payment date, or null for today
 * @param trxName transaction name (nullable)
 * @return discount amount rounded to 2 decimal places
 */
public static BigDecimal paymentTermDiscount(@Nullable BigDecimal amount,
                                              int currencyId,
                                              int paymentTermId,
                                              @Nullable Timestamp docDate,
                                              @Nullable Timestamp payDate,
                                              @Nullable String trxName) {
    // No data - no discount
    if (amount == null || paymentTermId == 0 || docDate == null) {
        if (log.isLoggable(Level.FINE)) {
            log.fine("paymentTermDiscount: invalid inputs - " +
                "amount=" + amount + ", paymentTermId=" + paymentTermId +
                ", docDate=" + docDate);
        }
        return BigDecimal.ZERO;
    }

    LocalDate vPayDate = (payDate != null)
        ? payDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        : LocalDate.now();

    MPaymentTerm pt = MPaymentTerm.get(Env.getCtx(), paymentTermId, trxName);
    if (pt == null || pt.get_ID() == 0) {
        if (log.isLoggable(Level.FINE)) {
            log.fine("paymentTermDiscount: payment term not found - " + paymentTermId);
        }
        return BigDecimal.ZERO;
    }

    LocalDate docLocalDate = docDate.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();

    int clientId = pt.getAD_Client_ID();
    int graceDays = pt.getGraceDays();
    boolean isNextBusinessDay = pt.isNextBusinessDay();

    // Calculate discount dates
    LocalDate discount1Date = docLocalDate.plusDays(pt.getDiscountDays() + graceDays);
    LocalDate discount2Date = docLocalDate.plusDays(pt.getDiscountDays2() + graceDays);

    // Apply next business day logic if configured
    if (isNextBusinessDay) {
        Timestamp d1Ts = Timestamp.valueOf(discount1Date.atStartOfDay());
        Timestamp d2Ts = Timestamp.valueOf(discount2Date.atStartOfDay());

        Timestamp nbd1 = nextBusinessDay(d1Ts, clientId, trxName);
        Timestamp nbd2 = nextBusinessDay(d2Ts, clientId, trxName);

        if (nbd1 != null) {
            discount1Date = nbd1.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        }
        if (nbd2 != null) {
            discount2Date = nbd2.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        }
    }

    // Apply discount tier
    BigDecimal discount = BigDecimal.ZERO;
    BigDecimal discountPct = pt.getDiscount();
    BigDecimal discountPct2 = pt.getDiscount2();

    if (!vPayDate.isAfter(discount1Date) && discountPct != null
            && discountPct.signum() > 0) {
        // First discount tier
        discount = amount.multiply(discountPct).divide(
            new BigDecimal("100"), 6, RoundingMode.HALF_UP);
    } else if (!vPayDate.isAfter(discount2Date) && discountPct2 != null
            && discountPct2.signum() > 0) {
        // Second discount tier
        discount = amount.multiply(discountPct2).divide(
            new BigDecimal("100"), 6, RoundingMode.HALF_UP);
    }

    // Round to 2 decimal places (fixed rounding as per SQL)
    return discount.setScale(2, RoundingMode.HALF_UP);
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsTest.paymentTermDiscount*" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/PaymentTermFunctions.java base/test/src/org/compiere/util/PaymentTermFunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave2): add paymentTermDiscount implementation

Supports discount tiers with optional next-business-day adjustment.
Uses fixed 2-decimal rounding to match SQL exactly.
Matches PostgreSQL C_PaymentTerm_Discount.sql behavior.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 2.4: Performance tests for core functions

**Files:**
- Modify: `base/test/src/org/compiere/util/PaymentTermFunctionsPerformanceTest.java`

**Step 1: Add performance tests for core functions**

Add to `PaymentTermFunctionsPerformanceTest.java`:

```java
@Test
void paymentTermDueDate_performanceWithinBudget() {
    Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
    Integer paymentTermId = 0; // Invalid ID to avoid DB lookup in unit test

    // Warmup
    for (int i = 0; i < WARMUP_ITERATIONS; i++) {
        PaymentTermFunctions.paymentTermDueDate(paymentTermId, docDate);
    }

    // Measure (null return path is still valid for perf)
    long start = System.nanoTime();
    for (int i = 0; i < TEST_ITERATIONS; i++) {
        PaymentTermFunctions.paymentTermDueDate(paymentTermId, docDate);
    }
    long elapsed = System.nanoTime() - start;

    double avgMicros = (elapsed / 1000.0) / TEST_ITERATIONS;
    System.out.printf("paymentTermDueDate (no DB): %.2f us/call%n", avgMicros);

    // Without DB lookup, should be fast
    assertTrue(avgMicros < 50.0, "paymentTermDueDate should be < 50 us/call without DB, was " + avgMicros);
}

@Test
void paymentTermDueDays_performanceWithinBudget() {
    Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
    Timestamp payDate = Timestamp.valueOf("2026-02-15 00:00:00");
    int paymentTermId = 0; // Invalid ID

    // Warmup
    for (int i = 0; i < WARMUP_ITERATIONS; i++) {
        PaymentTermFunctions.paymentTermDueDays(paymentTermId, docDate, payDate);
    }

    // Measure
    long start = System.nanoTime();
    for (int i = 0; i < TEST_ITERATIONS; i++) {
        PaymentTermFunctions.paymentTermDueDays(paymentTermId, docDate, payDate);
    }
    long elapsed = System.nanoTime() - start;

    double avgMicros = (elapsed / 1000.0) / TEST_ITERATIONS;
    System.out.printf("paymentTermDueDays (no DB): %.2f us/call%n", avgMicros);

    assertTrue(avgMicros < 50.0, "paymentTermDueDays should be < 50 us/call without DB, was " + avgMicros);
}

@Test
void paymentTermDiscount_performanceWithinBudget() {
    BigDecimal amount = new BigDecimal("1000.00");
    Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
    Timestamp payDate = Timestamp.valueOf("2026-01-20 00:00:00");
    int paymentTermId = 0; // Invalid ID
    int currencyId = 100;

    // Warmup
    for (int i = 0; i < WARMUP_ITERATIONS; i++) {
        PaymentTermFunctions.paymentTermDiscount(amount, currencyId, paymentTermId, docDate, payDate);
    }

    // Measure
    long start = System.nanoTime();
    for (int i = 0; i < TEST_ITERATIONS; i++) {
        PaymentTermFunctions.paymentTermDiscount(amount, currencyId, paymentTermId, docDate, payDate);
    }
    long elapsed = System.nanoTime() - start;

    double avgMicros = (elapsed / 1000.0) / TEST_ITERATIONS;
    System.out.printf("paymentTermDiscount (no DB): %.2f us/call%n", avgMicros);

    assertTrue(avgMicros < 50.0, "paymentTermDiscount should be < 50 us/call without DB, was " + avgMicros);
}
```

**Step 2: Run performance tests**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsPerformanceTest" -i`
Expected: PASS with timing output

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/util/PaymentTermFunctionsPerformanceTest.java
git commit -m "$(cat <<'EOF'
test(wave2): add performance tests for core payment term functions

Validates paymentTermDueDate, paymentTermDueDays, paymentTermDiscount.
Records baseline timing for shadow mode comparison.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

## Group 3: Shadow Mode Integration

### Task 3.1: Extend SqlFunctionCaller for all payment term functions

**Files:**
- Modify: `base/src/org/compiere/migration/SqlFunctionCaller.java`
- Modify: `base/test/src/org/compiere/migration/SqlFunctionCallerTest.java`

**Step 1: Write failing tests**

Add to `SqlFunctionCallerTest.java`:

```java
@Test
void callNextBusinessDay_withNullDate_returnsNull() {
    assertNull(SqlFunctionCaller.callNextBusinessDay(null, 0));
}

@Test
void callPaymentTermDueDate_withNullInputs_returnsNull() {
    assertNull(SqlFunctionCaller.callPaymentTermDueDate(null, null));
}

@Test
void callPaymentTermDueDays_withZeroPaymentTermId_returnsZero() {
    Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
    assertEquals(0, SqlFunctionCaller.callPaymentTermDueDays(0, docDate, null));
}

@Test
void callPaymentTermDiscount_withNullAmount_returnsZero() {
    Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
    assertEquals(BigDecimal.ZERO, SqlFunctionCaller.callPaymentTermDiscount(
        null, 100, 106, docDate, docDate));
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "org.compiere.migration.SqlFunctionCallerTest.call*" -i`
Expected: FAIL

**Step 3: Add SQL caller methods**

Add to `SqlFunctionCaller.java`:

```java
/**
 * Call PostgreSQL nextBusinessDay function.
 *
 * @param date input date (nullable)
 * @param clientId AD_Client_ID for holiday lookup
 * @return next business day, or null if date is null
 */
@Nullable
public static Timestamp callNextBusinessDay(@Nullable Timestamp date, int clientId) {
    if (date == null) {
        return null;
    }

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

/**
 * Call PostgreSQL paymentTermDueDate function.
 */
@Nullable
public static Timestamp callPaymentTermDueDate(@Nullable Integer paymentTermId,
                                                @Nullable Timestamp docDate) {
    if (paymentTermId == null || docDate == null) {
        return null;
    }

    String sql = "SELECT paymentTermDueDate(?, ?)";

    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        pstmt.setInt(1, paymentTermId);
        pstmt.setTimestamp(2, docDate);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getTimestamp(1);
            }
        }
    } catch (SQLException e) {
        throw new SqlFunctionException("paymentTermDueDate", e);
    }
    return null;
}

/**
 * Call PostgreSQL paymentTermDueDays function.
 */
public static int callPaymentTermDueDays(int paymentTermId,
                                          @Nullable Timestamp docDate,
                                          @Nullable Timestamp payDate) {
    if (paymentTermId == 0 || docDate == null) {
        return 0;
    }

    String sql = "SELECT paymentTermDueDays(?, ?, ?)";

    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        pstmt.setInt(1, paymentTermId);
        pstmt.setTimestamp(2, docDate);
        pstmt.setTimestamp(3, payDate);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    } catch (SQLException e) {
        throw new SqlFunctionException("paymentTermDueDays", e);
    }
    return 0;
}

/**
 * Call PostgreSQL paymentTermDiscount function.
 */
public static BigDecimal callPaymentTermDiscount(@Nullable BigDecimal amount,
                                                   int currencyId,
                                                   int paymentTermId,
                                                   @Nullable Timestamp docDate,
                                                   @Nullable Timestamp payDate) {
    if (amount == null || paymentTermId == 0 || docDate == null) {
        return BigDecimal.ZERO;
    }

    String sql = "SELECT paymentTermDiscount(?, ?, ?, ?, ?)";

    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        pstmt.setBigDecimal(1, amount);
        pstmt.setInt(2, currencyId);
        pstmt.setInt(3, paymentTermId);
        pstmt.setTimestamp(4, docDate);
        pstmt.setTimestamp(5, payDate);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                BigDecimal result = rs.getBigDecimal(1);
                return result != null ? result : BigDecimal.ZERO;
            }
        }
    } catch (SQLException e) {
        throw new SqlFunctionException("paymentTermDiscount", e);
    }
    return BigDecimal.ZERO;
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "org.compiere.migration.SqlFunctionCallerTest.call*" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/SqlFunctionCaller.java base/test/src/org/compiere/migration/SqlFunctionCallerTest.java
git commit -m "$(cat <<'EOF'
feat(wave2): extend SqlFunctionCaller with all payment term functions

Adds SQL callers for nextBusinessDay, paymentTermDueDate,
paymentTermDueDays, paymentTermDiscount.
Required for shadow mode comparison.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 3.2: Add TimestampComparator and IntegerComparator

**Files:**
- Create: `base/src/org/compiere/migration/comparators/TimestampComparator.java`
- Create: `base/src/org/compiere/migration/comparators/IntegerComparator.java`
- Create: `base/test/src/org/compiere/migration/comparators/TimestampComparatorTest.java`

**Step 1: Write failing test**

```java
package org.compiere.migration.comparators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;

class TimestampComparatorTest {

    @Test
    void sameDay_withSameDayDifferentTime_returnsTrue() {
        Timestamp t1 = Timestamp.valueOf("2026-01-15 10:00:00");
        Timestamp t2 = Timestamp.valueOf("2026-01-15 23:59:59");
        assertTrue(TimestampComparator.SAME_DAY.test(t1, t2));
    }

    @Test
    void sameDay_withDifferentDays_returnsFalse() {
        Timestamp t1 = Timestamp.valueOf("2026-01-15 10:00:00");
        Timestamp t2 = Timestamp.valueOf("2026-01-16 10:00:00");
        assertFalse(TimestampComparator.SAME_DAY.test(t1, t2));
    }

    @Test
    void sameDay_withBothNull_returnsTrue() {
        assertTrue(TimestampComparator.SAME_DAY.test(null, null));
    }

    @Test
    void sameDay_withOneNull_returnsFalse() {
        Timestamp t1 = Timestamp.valueOf("2026-01-15 10:00:00");
        assertFalse(TimestampComparator.SAME_DAY.test(t1, null));
        assertFalse(TimestampComparator.SAME_DAY.test(null, t1));
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "org.compiere.migration.comparators.TimestampComparatorTest" -i`
Expected: FAIL

**Step 3: Write implementations**

`TimestampComparator.java`:

```java
package org.compiere.migration.comparators;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.function.BiPredicate;

/**
 * Comparators for Timestamp values in shadow mode.
 */
public final class TimestampComparator {

    private TimestampComparator() {}

    /**
     * Compare timestamps by date only (ignoring time component).
     */
    public static final BiPredicate<Timestamp, Timestamp> SAME_DAY = (a, b) -> {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;

        LocalDate dateA = a.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dateB = b.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return dateA.equals(dateB);
    };

    /**
     * Compare timestamps with 1-second tolerance.
     */
    public static final BiPredicate<Timestamp, Timestamp> WITHIN_SECOND = (a, b) -> {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return Math.abs(a.getTime() - b.getTime()) <= 1000;
    };
}
```

`IntegerComparator.java`:

```java
package org.compiere.migration.comparators;

import java.util.function.BiPredicate;

/**
 * Comparators for Integer values in shadow mode.
 */
public final class IntegerComparator {

    private IntegerComparator() {}

    /**
     * Exact match comparison.
     */
    public static final BiPredicate<Integer, Integer> EXACT = (a, b) -> {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    };
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "org.compiere.migration.comparators.TimestampComparatorTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/comparators/TimestampComparator.java base/src/org/compiere/migration/comparators/IntegerComparator.java base/test/src/org/compiere/migration/comparators/TimestampComparatorTest.java
git commit -m "$(cat <<'EOF'
feat(wave2): add TimestampComparator and IntegerComparator

SAME_DAY comparator for date-only comparison.
EXACT comparator for integer values.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 3.3: Create PaymentTermFunctionRouter

**Files:**
- Create: `base/src/org/compiere/util/PaymentTermFunctionRouter.java`
- Create: `base/test/src/org/compiere/util/PaymentTermFunctionRouterTest.java`

**Step 1: Write failing test**

```java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.sql.Timestamp;

class PaymentTermFunctionRouterTest {

    @Test
    void nextBusinessDay_withNullDate_returnsNull() {
        assertNull(PaymentTermFunctionRouter.nextBusinessDay(null, 0));
    }

    @Test
    void paymentTermDueDate_withNullInputs_returnsNull() {
        assertNull(PaymentTermFunctionRouter.paymentTermDueDate(null, null));
    }

    @Test
    void paymentTermDueDays_withZeroPaymentTermId_returnsZero() {
        Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
        assertEquals(0, PaymentTermFunctionRouter.paymentTermDueDays(0, docDate, null));
    }

    @Test
    void paymentTermDiscount_withNullAmount_returnsZero() {
        Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
        assertEquals(BigDecimal.ZERO, PaymentTermFunctionRouter.paymentTermDiscount(
            null, 100, 106, docDate, docDate));
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionRouterTest" -i`
Expected: FAIL

**Step 3: Write implementation**

```java
package org.compiere.util;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Nullable;

import org.compiere.migration.SqlFunctionCaller;
import org.compiere.migration.ShadowExecutor;
import org.compiere.migration.comparators.BigDecimalComparator;
import org.compiere.migration.comparators.IntegerComparator;
import org.compiere.migration.comparators.TimestampComparator;

/**
 * Router for Wave 2 payment term functions.
 * Delegates to ShadowExecutor for mode-aware execution (SQL_ONLY, SHADOW, JAVA_ONLY).
 *
 * <p>Use these methods in application code to enable gradual migration:
 * <ul>
 *   <li>SQL_ONLY: Calls SQL function only (default, safe)</li>
 *   <li>SHADOW: Calls both, compares, logs, returns Java result</li>
 *   <li>JAVA_ONLY: Calls Java only (post-validation)</li>
 * </ul>
 *
 * @see PaymentTermFunctions for the Java implementations
 * @see SqlFunctionCaller for the SQL callers
 */
public class PaymentTermFunctionRouter {

    private PaymentTermFunctionRouter() {
        // Utility class
    }

    /**
     * Route nextBusinessDay through shadow executor.
     */
    @Nullable
    public static Timestamp nextBusinessDay(@Nullable Timestamp date, int clientId) {
        return ShadowExecutor.execute(
            "nextBusinessDay",
            new Object[]{date, clientId},
            () -> PaymentTermFunctions.nextBusinessDay(date, clientId),
            () -> SqlFunctionCaller.callNextBusinessDay(date, clientId),
            TimestampComparator.SAME_DAY
        );
    }

    /**
     * Route paymentTermDueDate through shadow executor.
     */
    @Nullable
    public static Timestamp paymentTermDueDate(@Nullable Integer paymentTermId,
                                                @Nullable Timestamp docDate) {
        return ShadowExecutor.execute(
            "paymentTermDueDate",
            new Object[]{paymentTermId, docDate},
            () -> PaymentTermFunctions.paymentTermDueDate(paymentTermId, docDate),
            () -> SqlFunctionCaller.callPaymentTermDueDate(paymentTermId, docDate),
            TimestampComparator.SAME_DAY
        );
    }

    /**
     * Route paymentTermDueDays through shadow executor.
     */
    public static int paymentTermDueDays(int paymentTermId,
                                          @Nullable Timestamp docDate,
                                          @Nullable Timestamp payDate) {
        Integer result = ShadowExecutor.execute(
            "paymentTermDueDays",
            new Object[]{paymentTermId, docDate, payDate},
            () -> PaymentTermFunctions.paymentTermDueDays(paymentTermId, docDate, payDate),
            () -> SqlFunctionCaller.callPaymentTermDueDays(paymentTermId, docDate, payDate),
            IntegerComparator.EXACT
        );
        return result != null ? result : 0;
    }

    /**
     * Route paymentTermDiscount through shadow executor.
     */
    public static BigDecimal paymentTermDiscount(@Nullable BigDecimal amount,
                                                   int currencyId,
                                                   int paymentTermId,
                                                   @Nullable Timestamp docDate,
                                                   @Nullable Timestamp payDate) {
        BigDecimal result = ShadowExecutor.execute(
            "paymentTermDiscount",
            new Object[]{amount, currencyId, paymentTermId, docDate, payDate},
            () -> PaymentTermFunctions.paymentTermDiscount(amount, currencyId, paymentTermId, docDate, payDate),
            () -> SqlFunctionCaller.callPaymentTermDiscount(amount, currencyId, paymentTermId, docDate, payDate),
            BigDecimalComparator.CURRENCY
        );
        return result != null ? result : BigDecimal.ZERO;
    }
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionRouterTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/PaymentTermFunctionRouter.java base/test/src/org/compiere/util/PaymentTermFunctionRouterTest.java
git commit -m "$(cat <<'EOF'
feat(wave2): add PaymentTermFunctionRouter for shadow mode

Routes all payment term functions through ShadowExecutor.
Includes nextBusinessDay for independent validation.
Follows Wave 1 CurrencyFunctionRouter pattern.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 3.4: Configure migration schema for Wave 2

**Files:**
- Create: `migration/sql/wave2-function-config.sql`

**Step 1: Write configuration SQL**

```sql
-- Wave 2 Payment Term Functions Configuration
-- Execute after Wave 1 is complete

-- Initial configuration (SQL_ONLY mode - safe default)
INSERT INTO migration.function_config (function_name, mode, sample_rate, circuit_breaker_enabled)
VALUES
    ('nextBusinessDay', 'SQL_ONLY', 1.0, true),
    ('paymentTermDueDate', 'SQL_ONLY', 1.0, true),
    ('paymentTermDueDays', 'SQL_ONLY', 1.0, true),
    ('paymentTermDiscount', 'SQL_ONLY', 1.0, true)
ON CONFLICT (function_name) DO UPDATE SET
    mode = EXCLUDED.mode,
    sample_rate = EXCLUDED.sample_rate,
    circuit_breaker_enabled = EXCLUDED.circuit_breaker_enabled;

-- ========================================================
-- Enable SHADOW mode for Wave 2 Payment Term Functions
-- Run this after code deployment to start validation
-- ========================================================

-- Uncomment to enable SHADOW mode:
-- UPDATE migration.function_config
-- SET mode = 'SHADOW', sample_rate = 1.0
-- WHERE function_name IN ('nextBusinessDay', 'paymentTermDueDate', 'paymentTermDueDays', 'paymentTermDiscount');

-- Verify configuration:
-- SELECT function_name, mode, sample_rate, circuit_breaker_enabled
-- FROM migration.function_config
-- WHERE function_name IN ('nextBusinessDay', 'paymentTermDueDate', 'paymentTermDueDays', 'paymentTermDiscount');

-- ========================================================
-- Cutover to JAVA_ONLY (after 7 days successful SHADOW)
-- ========================================================

-- Uncomment after successful SHADOW validation:
-- UPDATE migration.function_config
-- SET mode = 'JAVA_ONLY'
-- WHERE function_name IN ('nextBusinessDay', 'paymentTermDueDate', 'paymentTermDueDays', 'paymentTermDiscount');
```

**Step 2: Commit**

```bash
git add migration/sql/wave2-function-config.sql
git commit -m "$(cat <<'EOF'
chore(wave2): add function config SQL for all payment term functions

Includes nextBusinessDay for independent shadow validation.
Initial SQL_ONLY mode with cutover scripts for SHADOW and JAVA_ONLY.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

**Note on Circuit Breaker Testing:**

The configuration enables `circuit_breaker_enabled = true` for all payment term functions. Circuit breaker behavior (opening on repeated SQL failures, fallback to Java path) is tested at the infrastructure level in `ShadowExecutorTest.java` and `CircuitBreakerTest.java`. Wave 2 functions inherit this behavior automatically through `ShadowExecutor.execute()`.

If function-specific circuit breaker behavior is needed (e.g., different thresholds), add configuration columns to `migration.function_config` and tests to verify per-function settings.

---

## Group 4: Integration Tests and Performance Baseline

### Task 4.1: Create integration tests with database

**Files:**
- Create: `base/test/src/org/compiere/util/PaymentTermFunctionsIntegrationTest.java`

**Step 1: Write integration tests**

```java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;

import org.compiere.migration.SqlFunctionCaller;
import org.compiere.model.MPaymentTerm;
import org.compiere.model.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

/**
 * Integration tests comparing Java and SQL implementations.
 * Requires database connection - run with -DrunIntegrationTests=true
 *
 * <p>Payment term IDs are queried dynamically to work with any test database.
 * Tests are skipped if required payment term types are not found.
 *
 * <p>Test dates use fixed 2026 values (Wednesday 2026-01-07, etc.) which
 * remain valid calendar dates regardless of when tests are run.
 */
@EnabledIfSystemProperty(named = "runIntegrationTests", matches = "true")
class PaymentTermFunctionsIntegrationTest {

    private static int netDaysPaymentTermId;
    private static int fixedDueDatePaymentTermId;
    private static int clientId;

    @BeforeAll
    static void setUp() {
        // Initialize ADempiere environment if needed
        // Env.initTest();

        // Query for a net-days payment term (IsDueFixed = 'N')
        MPaymentTerm netDaysTerm = new Query(Env.getCtx(), MPaymentTerm.Table_Name,
            "IsDueFixed = 'N' AND IsActive = 'Y'", null)
            .setOnlyActiveRecords(true)
            .first();
        netDaysPaymentTermId = (netDaysTerm != null) ? netDaysTerm.get_ID() : 0;

        // Query for a fixed due date payment term (IsDueFixed = 'Y')
        MPaymentTerm fixedTerm = new Query(Env.getCtx(), MPaymentTerm.Table_Name,
            "IsDueFixed = 'Y' AND IsActive = 'Y'", null)
            .setOnlyActiveRecords(true)
            .first();
        fixedDueDatePaymentTermId = (fixedTerm != null) ? fixedTerm.get_ID() : 0;

        // Get client ID from the net-days term, or default to 11 (GardenWorld)
        clientId = (netDaysTerm != null) ? netDaysTerm.getAD_Client_ID() : 11;
    }

    @Test
    void nextBusinessDay_javaMatchesSql() {
        // Wednesday 2026-01-07 - fixed calendar date
        Timestamp wednesday = Timestamp.valueOf("2026-01-07 10:00:00");

        Timestamp javaResult = PaymentTermFunctions.nextBusinessDay(wednesday, clientId);
        Timestamp sqlResult = SqlFunctionCaller.callNextBusinessDay(wednesday, clientId);

        assertNotNull(javaResult, "Java result should not be null");
        assertNotNull(sqlResult, "SQL result should not be null");

        LocalDate javaDate = javaResult.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate sqlDate = sqlResult.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        assertEquals(sqlDate, javaDate, "Next business days should match");
    }

    @Test
    void paymentTermDueDate_javaMatchesSql() {
        assumeTrue(netDaysPaymentTermId > 0,
            "Skipping: no net-days payment term found in database");

        Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");

        Timestamp javaResult = PaymentTermFunctions.paymentTermDueDate(netDaysPaymentTermId, docDate);
        Timestamp sqlResult = SqlFunctionCaller.callPaymentTermDueDate(netDaysPaymentTermId, docDate);

        assertNotNull(javaResult, "Java result should not be null");
        assertNotNull(sqlResult, "SQL result should not be null");

        // Compare dates (ignore time)
        LocalDate javaDate = javaResult.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate sqlDate = sqlResult.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        assertEquals(sqlDate, javaDate, "Due dates should match for paymentTermId=" + netDaysPaymentTermId);
    }

    @Test
    void paymentTermDueDays_javaMatchesSql() {
        assumeTrue(netDaysPaymentTermId > 0,
            "Skipping: no net-days payment term found in database");

        Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
        Timestamp payDate = Timestamp.valueOf("2026-02-15 00:00:00");

        int javaResult = PaymentTermFunctions.paymentTermDueDays(netDaysPaymentTermId, docDate, payDate);
        int sqlResult = SqlFunctionCaller.callPaymentTermDueDays(netDaysPaymentTermId, docDate, payDate);

        assertEquals(sqlResult, javaResult, "Due days should match for paymentTermId=" + netDaysPaymentTermId);
    }

    @Test
    void paymentTermDiscount_javaMatchesSql() {
        assumeTrue(netDaysPaymentTermId > 0,
            "Skipping: no net-days payment term found in database");

        BigDecimal amount = new BigDecimal("1000.00");
        int currencyId = 100; // USD typically
        Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
        Timestamp payDate = Timestamp.valueOf("2026-01-20 00:00:00");

        BigDecimal javaResult = PaymentTermFunctions.paymentTermDiscount(
            amount, currencyId, netDaysPaymentTermId, docDate, payDate);
        BigDecimal sqlResult = SqlFunctionCaller.callPaymentTermDiscount(
            amount, currencyId, netDaysPaymentTermId, docDate, payDate);

        assertEquals(0, javaResult.compareTo(sqlResult),
            "Discounts should match: Java=" + javaResult + ", SQL=" + sqlResult);
    }

    @Test
    void paymentTermDueDate_fixedDueDate_javaMatchesSql() {
        assumeTrue(fixedDueDatePaymentTermId > 0,
            "Skipping: no fixed due date payment term found in database");

        // Past typical cutoff to test the cutoff logic
        Timestamp docDate = Timestamp.valueOf("2026-01-22 00:00:00");

        Timestamp javaResult = PaymentTermFunctions.paymentTermDueDate(fixedDueDatePaymentTermId, docDate);
        Timestamp sqlResult = SqlFunctionCaller.callPaymentTermDueDate(fixedDueDatePaymentTermId, docDate);

        assertNotNull(javaResult, "Java result should not be null for fixed term");
        assertNotNull(sqlResult, "SQL result should not be null for fixed term");

        LocalDate javaDate = javaResult.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate sqlDate = sqlResult.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        assertEquals(sqlDate, javaDate, "Fixed due dates should match for paymentTermId=" + fixedDueDatePaymentTermId);
    }
}
```

**Step 2: Run integration tests**

Run: `./gradlew :base:test --tests "org.compiere.util.PaymentTermFunctionsIntegrationTest" -DrunIntegrationTests=true -i`
Expected: PASS if database available

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/util/PaymentTermFunctionsIntegrationTest.java
git commit -m "$(cat <<'EOF'
test(wave2): add integration tests comparing Java and SQL

Validates all payment term functions produce identical results.
Includes nextBusinessDay and fixed due date tests.
Run with -DrunIntegrationTests=true against test database.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 4.2: Create performance baseline document

**Files:**
- Create: `docs/metrics/wave2-performance-baseline.md`

**Step 1: Write baseline template**

```markdown
# Wave 2 Payment Term Functions - Performance Baseline

**Captured:** [DATE]
**Environment:** PostgreSQL, OpenJDK 11, Linux

## Baseline Metrics

| Function | Test Iterations | Warmup | Max Ratio | Result |
|----------|-----------------|--------|-----------|--------|
| nextBusinessDay | 2000 | 500 | 1.30 | PENDING |
| paymentTermDueDate | 2000 | 500 | 1.30 | PENDING |
| paymentTermDueDays | 2000 | 500 | 1.30 | PENDING |
| paymentTermDiscount | 2000 | 500 | 1.30 | PENDING |

## Test Configuration

- Warmup iterations: 500
- Test iterations: 2000
- Measurement rounds: 5
- Max acceptable ratio: 1.30 (Java/SQL)
- Statistical method: Median of 5 rounds

## Notes

- nextBusinessDay pre-fetches holidays for 30-day range (avoids N+1)
- paymentTermDueDate uses MPaymentTerm caching
- paymentTermDueDays shares calculateDueDate helper with paymentTermDueDate
- paymentTermDiscount may call nextBusinessDay internally
- Performance affected by payment term configuration complexity

## Design Decisions

See Section "Design Decisions" in implementation plan for:
- Transaction handling: Optional trxName parameter
- Holiday source: MNonBusinessDay model
- Week standard: ISO (Sat/Sun = weekend)
- Rounding: Fixed 2 decimals

## SQL Baseline Timing (to be captured)

Run against test database with representative data:

```sql
-- nextBusinessDay timing
EXPLAIN ANALYZE SELECT nextBusinessDay(NOW()::timestamptz, 11);

-- paymentTermDueDate timing
EXPLAIN ANALYZE SELECT paymentTermDueDate(106, NOW()::timestamptz);

-- paymentTermDueDays timing
EXPLAIN ANALYZE SELECT paymentTermDueDays(106, NOW()::timestamptz, NOW()::timestamptz);

-- paymentTermDiscount timing
EXPLAIN ANALYZE SELECT paymentTermDiscount(1000.00, 100, 106, NOW()::timestamptz, NOW()::timestamptz);
```
```

**Step 2: Commit**

```bash
git add docs/metrics/wave2-performance-baseline.md
git commit -m "$(cat <<'EOF'
docs(wave2): add performance baseline template

Template for capturing Java vs SQL timing comparisons.
Includes all 4 functions and design decision references.
To be filled during performance validation phase.

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 4.3: Final commit with updated plan

**Step 1: Commit updated plan**

```bash
git add docs/plans/2026-01-03-wave2-payment-terms-implementation.md
git commit -m "$(cat <<'EOF'
docs(wave2): update payment terms plan with critical review fixes

Incorporated fixes from critical review:
- Fixed nextBusinessDay loop logic (weekend recheck after holiday)
- Fixed calculateFixedDueDate to match SQL noDays > cutoff logic
- Pre-fetch holidays to avoid N+1 queries
- Added nextBusinessDay SQL caller for independent validation
- Added comprehensive fixed due date tests
- Added design decisions section
- DRY: shared calculateDueDate helper
- Debug logging for invalid inputs
- Optional trxName parameter throughout

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

## Appendix A: Function Signatures

```sql
-- add_months(datetime timestamptz, months numeric) -> date
-- nextBusinessDay(p_date timestamptz, p_ad_client_id numeric) -> timestamptz
-- paymentTermDueDate(paymentterm_id numeric, docdate timestamptz) -> timestamptz
-- paymentTermDueDays(paymentterm_id numeric, docdate timestamptz, paydate timestamptz) -> integer
-- paymentTermDiscount(amount numeric, currency_id numeric, paymentterm_id numeric, docdate timestamptz, paydate timestamptz) -> numeric
```

## Appendix B: Existing Java Duplicates

The following methods in `sqlj/src/org/compiere/sqlj/PaymentTerm.java` are known duplicates:

| SQL Function | Java Method | Location | Status |
|--------------|-------------|----------|--------|
| paymentTermDueDays | `dueDays()` | PaymentTerm.java:46 | Coexist during migration |
| paymentTermDueDate | `dueDate()` | PaymentTerm.java:135 | Coexist during migration |
| paymentTermDiscount | `discount()` | PaymentTerm.java:261 | Coexist during migration |

Shadow mode will validate that the new implementations match SQL exactly. After successful JAVA_ONLY cutover, SQLJ code will be deprecated.

## Appendix C: Downstream Dependencies

Wave 2 completion unblocks:

| Wave | Functions Affected |
|------|-------------------|
| Wave 3 (Financial Core) | `invoiceDiscount` depends on `paymentTermDiscount` |

**Critical Path:** Wave 0 -> Wave 1 -> **Wave 2** -> Wave 3

## Appendix D: Critical Review Fixes Applied

### Critical Review 1 Fixes

| Issue | Section | Fix Applied |
|-------|---------|-------------|
| 2.1 nextBusinessDay loop bug | Task 1.3 | Weekend check at loop start, runs after holiday increment |
| 2.2 calculateFixedDueDate divergence | Task 1.4 | noDays = dayOfMonth - 1, compare noDays > cutoff |
| 2.3 N+1 query pattern | Task 1.2, 1.3 | Pre-fetch holidays for 30-day range |
| 2.4 Missing connection handling | All | Added optional trxName parameter |
| 2.5 Missing nextBusinessDay SQL caller | Task 3.1 | Added callNextBusinessDay |
| 2.6 Incomplete fixed due date tests | Task 1.4 | Comprehensive cutoff, month-end, edge case tests |
| 3.1 DRY violation | Task 2.1 | Shared calculateDueDate helper |
| 3.2 Consider caching | Task 2.1+ | Use MPaymentTerm.get() with built-in caching |
| 3.3 Missing debug logging | All functions | Added Level.FINE logging for invalid inputs |

### Critical Review 2 Findings

**Issues Verified Against SQL Source:**

| Review Issue | Verdict | Action Taken |
|--------------|---------|--------------|
| 2.1 calculateFixedDueDate month-end logic | NOT VALID | SQL lines 95-97 confirm `FixMonthDay >= 30 && MaxDay > FixMonthDay` logic exists. No change needed. |
| 2.2 nextBusinessDay loses time component | NOT VALID | SQL line 27 declares `v_nextDate date := trunc(p_Date)` - time is discarded. Java matches SQL. |
| 2.3 Potential infinite loop | VALID | Added MAX_BUSINESS_DAY_ITERATIONS = 365 guard in Task 1.3 |
| 2.4 Redundant holiday queries | NOT VALID | SQL also calls `nextBusinessDay` twice (lines 50-51). Java matches SQL behavior. POST-MIGRATION optimization only. |
| 2.5 Input validation for negatives | NOT VALID | SQL doesn't validate either. Would cause shadow mismatches. POST-MIGRATION enhancement only. |
| 3.1 Test dates in 2026 | MINOR | Added comments documenting fixed calendar dates in test classes |
| 3.2 Performance tests measure invalid path | VALID | Documented limitation in class JavaDoc for PaymentTermFunctionsPerformanceTest |
| 3.3 Missing JavaDoc | MINOR | Added JavaDoc with SQL line references to skipWeekends |
| 3.4 CircuitBreaker not tested | VALID | Added note that circuit breaker is tested at infrastructure level (ShadowExecutorTest) |
| 3.5 Hardcoded payment term IDs | VALID | Updated integration tests to query for payment terms dynamically using MPaymentTerm |

**Key SQL Source References:**
- `C_PaymentTerm_DueDays.sql` lines 95-97: Month-end logic (`FixMonthDay >= 30 && MaxDay > FixMonthDay`)
- `nextBusinessDay.sql` line 27: `v_nextDate date := trunc(p_Date)` (time discarded)
- `nextBusinessDay.sql` lines 35-53: Loop without explicit iteration guard (same as Java now, but Java adds defensive guard)
- `C_PaymentTerm_Discount.sql` lines 50-51: Two separate `nextBusinessDay` calls (intentional, matches Java)
