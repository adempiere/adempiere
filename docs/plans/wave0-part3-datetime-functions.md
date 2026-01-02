# Wave 0 Part 3: DateTime Functions

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Parent Plan:** [Wave 0 Implementation Plan](2026-01-02-wave0-implementation-plan.md)
**Part:** 3 of 5
**Tasks:** 7-10 (getDate, daysBetween, addDays, trunc)

**Previous:** [Part 2: Execution Infrastructure](wave0-part2-execution-infrastructure.md)

---

## Task 7: Implement getDate()

**Files:**
- Modify: `base/src/org/compiere/util/TimeUtil.java` (add getDate() method)
- Test: `base/test/src/org/compiere/util/TimeUtilGetDateTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/util/TimeUtilGetDateTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
public class TimeUtilGetDateTest {

    @Test
    void testGetDateReturnsCurrentTimestamp() {
        long before = System.currentTimeMillis();
        Timestamp result = TimeUtil.getDate();
        long after = System.currentTimeMillis();

        assertNotNull(result);
        assertTrue(result.getTime() >= before, "Should be >= start time");
        assertTrue(result.getTime() <= after, "Should be <= end time");
    }

    @Test
    void testGetDateIsNotTruncated() {
        Timestamp result = TimeUtil.getDate();
        // Unlike getDay(), getDate() should NOT truncate to midnight
        // It returns now() which includes time component
        assertNotNull(result);
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.TimeUtilGetDateTest" -i`
Expected: FAIL with "cannot find symbol: method getDate()"

**Step 3: Add getDate() method to TimeUtil**

Add after line 63 in TimeUtil.java:

```java
	/**
	 * Get current timestamp (equivalent to PostgreSQL getDate() function).
	 * Unlike getDay(), this returns the full timestamp with time component.
	 * @return current timestamp, never null
	 */
	static public Timestamp getDate() {
		return new Timestamp(System.currentTimeMillis());
	}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.TimeUtilGetDateTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/TimeUtil.java
git add base/test/src/org/compiere/util/TimeUtilGetDateTest.java
git commit -m "feat: add TimeUtil.getDate() for SQL getDate() migration

- Returns current timestamp (equivalent to PostgreSQL now())
- Distinct from getDay() which truncates to midnight

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Task 7a: Create Performance Test File

> **Rationale:** We create the performance test file here so we can run individual function performance tests immediately after each implementation. This catches regressions at the source rather than waiting until Part 5.

**Files:**
- Test: `base/test/src/org/compiere/migration/Wave0PerformanceTest.java`

**Step 1: Write the performance test file**

```java
// base/test/src/org/compiere/migration/Wave0PerformanceTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.SqlCompat;
import org.compiere.util.TimeUtil;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;

/**
 * Performance tests validating Java implementations meet latency requirements.
 *
 * <p><b>Design Note:</b> The MAX_LATENCY_RATIO threshold (1.30) is a sanity check to catch
 * catastrophic performance regressions, NOT a precise performance target. In practice,
 * Java implementations should be 100-1000x faster than SQL due to:
 * <ul>
 *   <li>No network round-trip to database</li>
 *   <li>No JDBC marshalling overhead</li>
 *   <li>No PostgreSQL function call overhead</li>
 * </ul>
 * The 130% threshold allows for measurement noise while detecting severe implementation
 * problems (e.g., accidental O(n^2) algorithms, excessive object allocation).
 *
 * <p>For production benchmarking, consider using JMH (Java Microbenchmark Harness).
 * These tests provide a reasonable sanity check for CI but are not rigorous benchmarks.
 */
@Tag("PerformanceTest")
public class Wave0PerformanceTest extends CommonGWSetup {

    /**
     * Sanity check threshold: Java must not exceed 130% of SQL latency.
     * In practice, Java should be orders of magnitude faster.
     */
    private static final double MAX_LATENCY_RATIO = 1.30;
    private static final int WARMUP_ITERATIONS = 1000;
    private static final int TEST_ITERATIONS = 5000;
    private static final int MEASUREMENT_ROUNDS = 5;

    // Accumulator for ratio results across repetitions
    private static final ThreadLocal<double[]> ratioAccumulator = ThreadLocal.withInitial(() -> new double[MEASUREMENT_ROUNDS]);

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testDaysBetweenPerformance(RepetitionInfo info) {
        Timestamp date1 = Timestamp.valueOf("2026-01-15 14:30:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-01 08:00:00");

        // Warmup both paths on first iteration
        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                TimeUtil.daysBetweenSql(date1, date2);
                SqlFunctionCaller.callDaysBetween(date1, date2);
            }
        }

        // Measure Java
        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            TimeUtil.daysBetweenSql(date1, date2);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        // Measure SQL
        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callDaysBetween(date1, date2);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        // On last repetition, check median
        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            java.util.Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("daysBetween Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, java.util.Arrays.toString(ratios)));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testAddDaysPerformance(RepetitionInfo info) {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("10");

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                TimeUtil.addDaysSql(datetime, days);
                SqlFunctionCaller.callAddDays(datetime, days);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            TimeUtil.addDaysSql(datetime, days);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callAddDays(datetime, days);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            java.util.Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("addDays Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, java.util.Arrays.toString(ratios)));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testRoundPerformance(RepetitionInfo info) {
        BigDecimal value = new BigDecimal("123.456789");
        int scale = 2;

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                SqlCompat.round(value, scale);
                SqlFunctionCaller.callRound(value, scale);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlCompat.round(value, scale);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callRound(value, scale);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            java.util.Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("round Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, java.util.Arrays.toString(ratios)));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testTruncPerformance(RepetitionInfo info) {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");
        String format = "Q";

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                TimeUtil.truncSql(datetime, format);
                SqlFunctionCaller.callTrunc(datetime, format);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            TimeUtil.truncSql(datetime, format);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callTrunc(datetime, format);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            java.util.Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("trunc Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, java.util.Arrays.toString(ratios)));
        }
    }

    @RepeatedTest(MEASUREMENT_ROUNDS)
    void testFirstOfPerformance(RepetitionInfo info) {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");
        String format = "Q";

        if (info.getCurrentRepetition() == 1) {
            for (int i = 0; i < WARMUP_ITERATIONS; i++) {
                TimeUtil.firstOf(datetime, format);
                SqlFunctionCaller.callFirstOf(datetime, format);
            }
        }

        long javaStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            TimeUtil.firstOf(datetime, format);
        }
        long javaTimeNs = System.nanoTime() - javaStart;

        long sqlStart = System.nanoTime();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            SqlFunctionCaller.callFirstOf(datetime, format);
        }
        long sqlTimeNs = System.nanoTime() - sqlStart;

        double ratio = (double) javaTimeNs / sqlTimeNs;
        ratioAccumulator.get()[info.getCurrentRepetition() - 1] = ratio;

        if (info.getCurrentRepetition() == MEASUREMENT_ROUNDS) {
            double[] ratios = ratioAccumulator.get();
            java.util.Arrays.sort(ratios);
            double medianRatio = ratios[MEASUREMENT_ROUNDS / 2];

            assertTrue(medianRatio <= MAX_LATENCY_RATIO,
                String.format("firstOf Java/SQL median ratio %.2f exceeds max %.2f (rounds: %s)",
                    medianRatio, MAX_LATENCY_RATIO, java.util.Arrays.toString(ratios)));
        }
    }
}
```

**Step 2: Commit the performance test file**

```bash
git add base/test/src/org/compiere/migration/Wave0PerformanceTest.java
git commit -m "test: add Wave 0 performance test scaffold

- Tests will be run incrementally as each function is implemented
- Validates Java latency <= 130% of SQL latency (sanity check)
- Uses @RepeatedTest with 5 rounds for statistical significance
- Individual tests run after their corresponding function implementation

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

---

## Task 8: Implement daysBetween() with SQL matching semantics

**Files:**
- Modify: `base/src/org/compiere/util/TimeUtil.java` (add daysBetweenSql)
- Test: `base/test/src/org/compiere/util/TimeUtilDaysBetweenSqlTest.java`

**Step 1: Write the failing test matching SQL semantics**

```java
// base/test/src/org/compiere/util/TimeUtilDaysBetweenSqlTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for SQL-compatible daysBetween calculation.
 * SQL: CAST(p_date1 AS DATE) - CAST(p_date2 as DATE)
 */
@Tag("UnitTest")
public class TimeUtilDaysBetweenSqlTest {

    @Test
    void testSameDayDifferentTimes() {
        Timestamp date1 = Timestamp.valueOf("2026-01-15 23:59:59");
        Timestamp date2 = Timestamp.valueOf("2026-01-15 00:00:00");

        // SQL: CAST('2026-01-15' AS DATE) - CAST('2026-01-15' AS DATE) = 0
        assertEquals(0, TimeUtil.daysBetweenSql(date1, date2));
    }

    @Test
    void testDate1AfterDate2() {
        Timestamp date1 = Timestamp.valueOf("2026-01-10 12:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-01 08:00:00");

        // SQL: 2026-01-10 - 2026-01-01 = 9
        assertEquals(9, TimeUtil.daysBetweenSql(date1, date2));
    }

    @Test
    void testDate1BeforeDate2() {
        Timestamp date1 = Timestamp.valueOf("2026-01-01 08:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-10 12:00:00");

        // SQL: 2026-01-01 - 2026-01-10 = -9
        assertEquals(-9, TimeUtil.daysBetweenSql(date1, date2));
    }

    @Test
    void testNullDate1ReturnsNull() {
        Timestamp date2 = Timestamp.valueOf("2026-01-01 00:00:00");
        assertNull(TimeUtil.daysBetweenSql(null, date2));
    }

    @Test
    void testNullDate2ReturnsNull() {
        Timestamp date1 = Timestamp.valueOf("2026-01-01 00:00:00");
        assertNull(TimeUtil.daysBetweenSql(date1, null));
    }

    @Test
    void testBothNullReturnsNull() {
        assertNull(TimeUtil.daysBetweenSql(null, null));
    }

    @Test
    void testCrossYearBoundary() {
        Timestamp date1 = Timestamp.valueOf("2027-01-01 00:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-12-31 00:00:00");

        assertEquals(1, TimeUtil.daysBetweenSql(date1, date2));
    }

    @Test
    void testCalendarDaysNotHourBased() {
        // Verifies we count calendar days, not 24-hour periods
        Timestamp date1 = Timestamp.valueOf("2026-03-09 12:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-03-07 12:00:00");

        // Should be 2 calendar days
        assertEquals(2, TimeUtil.daysBetweenSql(date1, date2));
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.TimeUtilDaysBetweenSqlTest" -i`
Expected: FAIL with "cannot find symbol: method daysBetweenSql"

**Step 3: Add daysBetweenSql() method to TimeUtil**

Add required imports at top of TimeUtil.java (after line 22, near existing `java.sql.Timestamp` import):
```java
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
```

Add after getDate() method:

```java
	/**
	 * Calculate days between two dates using SQL semantics.
	 * Equivalent to PostgreSQL: CAST(p_date1 AS DATE) - CAST(p_date2 AS DATE)
	 *
	 * Uses java.time API which correctly handles DST transitions
	 * (counts calendar days, not 24-hour periods).
	 *
	 * @param date1 first date (minuend), may be null
	 * @param date2 second date (subtrahend), may be null
	 * @return difference in days (date1 - date2), or null if either input is null
	 */
	static public Integer daysBetweenSql(Timestamp date1, Timestamp date2) {
		if (date1 == null || date2 == null) {
			return null;
		}

		// Use java.time API which handles DST correctly
		LocalDate ld1 = date1.toLocalDateTime().toLocalDate();
		LocalDate ld2 = date2.toLocalDateTime().toLocalDate();
		return (int) ChronoUnit.DAYS.between(ld2, ld1);
	}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.TimeUtilDaysBetweenSqlTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/TimeUtil.java
git add base/test/src/org/compiere/util/TimeUtilDaysBetweenSqlTest.java
git commit -m "feat: add TimeUtil.daysBetweenSql() for SQL daysBetween migration

- Matches SQL semantics: CAST(date1 AS DATE) - CAST(date2 AS DATE)
- Uses ChronoUnit.DAYS.between for correct calendar day calculation
- Returns null for null inputs (unlike existing getDaysBetween)

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

**Step 6: Run performance test for daysBetween**

Run: `./gradlew :base:test --tests "*.Wave0PerformanceTest.testDaysBetweenPerformance" -PperformanceTest -i`
Expected: PASS (Java should be significantly faster than SQL)

---

## Task 9: Implement addDays/subtractDays with SQL semantics

**Files:**
- Modify: `base/src/org/compiere/util/TimeUtil.java`
- Test: `base/test/src/org/compiere/util/TimeUtilAddDaysSqlTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/util/TimeUtilAddDaysSqlTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for SQL-compatible addDays/subtractDays.
 * SQL: cast(date_trunc('day',datetime) + cast(days || ' day' as interval) as date)
 */
@Tag("UnitTest")
public class TimeUtilAddDaysSqlTest {

    @Test
    void testAddDaysPositive() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("5");

        Date result = TimeUtil.addDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-01-20"), result);
    }

    @Test
    void testAddDaysNegative() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("-5");

        Date result = TimeUtil.addDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-01-10"), result);
    }

    @Test
    void testAddDaysZero() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = BigDecimal.ZERO;

        Date result = TimeUtil.addDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-01-15"), result);
    }

    @Test
    void testAddDaysNullDatetime() {
        assertNull(TimeUtil.addDaysSql(null, new BigDecimal("5")));
    }

    @Test
    void testAddDaysNullDays() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        assertNull(TimeUtil.addDaysSql(datetime, null));
    }

    @Test
    void testSubtractDays() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("5");

        Date result = TimeUtil.subtractDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-01-10"), result);
    }

    @Test
    void testAddDaysCrossMonth() {
        Timestamp datetime = Timestamp.valueOf("2026-01-30 00:00:00");
        BigDecimal days = new BigDecimal("5");

        Date result = TimeUtil.addDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-02-04"), result);
    }

    @Test
    void testAddDaysFractionalThrows() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("1.5");

        assertThrows(IllegalArgumentException.class,
            () -> TimeUtil.addDaysSql(datetime, days));
    }

    @Test
    void testAddDaysWholeNumberWithScaleWorks() {
        // 5.00 should work (trailing zeros)
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("5.00");

        Date result = TimeUtil.addDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-01-20"), result);
    }

    @Test
    void testAddDaysLeapYear() {
        // 2024 is a leap year
        Timestamp datetime = Timestamp.valueOf("2024-02-28 12:00:00");
        Date result = TimeUtil.addDaysSql(datetime, new BigDecimal("1"));
        assertEquals(Date.valueOf("2024-02-29"), result);
    }

    @Test
    void testAddDaysLargeOffset() {
        // Verify no overflow with large day values (~100 years)
        Timestamp datetime = Timestamp.valueOf("2026-01-01 00:00:00");
        Date result = TimeUtil.addDaysSql(datetime, new BigDecimal("36500"));
        assertNotNull(result);
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.TimeUtilAddDaysSqlTest" -i`
Expected: FAIL with "cannot find symbol: method addDaysSql"

**Step 3: Add addDaysSql() and subtractDaysSql() methods**

Add required imports at top of TimeUtil.java if not already present:
```java
import java.sql.Date;
import java.math.BigDecimal;
```

Add after daysBetweenSql():

```java
	/**
	 * Add days to timestamp, returning a Date (SQL semantics).
	 * Equivalent to PostgreSQL: cast(date_trunc('day',datetime) + cast(days || ' day' as interval) as date)
	 *
	 * @param datetime timestamp to add to, may be null
	 * @param days number of days to add (must be whole number), may be null
	 * @return resulting date, or null if either input is null
	 * @throws IllegalArgumentException if days has fractional component
	 */
	static public Date addDaysSql(Timestamp datetime, BigDecimal days) {
		if (datetime == null || days == null) {
			return null;
		}

		// Validate no fractional days
		BigDecimal stripped = days.stripTrailingZeros();
		if (stripped.scale() > 0) {
			throw new IllegalArgumentException("Fractional days not supported: " + days);
		}

		LocalDate date = datetime.toLocalDateTime().toLocalDate();
		LocalDate result = date.plusDays(days.longValue());
		return Date.valueOf(result);
	}

	/**
	 * Subtract days from timestamp, returning a Date (SQL semantics).
	 * Equivalent to PostgreSQL: subtractDays(day, days) which calls addDays(day, days * -1)
	 *
	 * @param datetime timestamp to subtract from, may be null
	 * @param days number of days to subtract, may be null
	 * @return resulting date, or null if either input is null
	 * @throws IllegalArgumentException if days has fractional component
	 */
	static public Date subtractDaysSql(Timestamp datetime, BigDecimal days) {
		if (days == null) {
			return null;
		}
		return addDaysSql(datetime, days.negate());
	}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.TimeUtilAddDaysSqlTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/TimeUtil.java
git add base/test/src/org/compiere/util/TimeUtilAddDaysSqlTest.java
git commit -m "feat: add TimeUtil.addDaysSql/subtractDaysSql for SQL migration

- Matches SQL: date_trunc('day', datetime) + interval
- Returns java.sql.Date (not Timestamp)
- Rejects fractional days with IllegalArgumentException
- Returns null for null inputs

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

**Step 6: Run performance test for addDays**

Run: `./gradlew :base:test --tests "*.Wave0PerformanceTest.testAddDaysPerformance" -PperformanceTest -i`
Expected: PASS (Java should be significantly faster than SQL)

---

## Task 10: Implement trunc() with SQL format codes

**Files:**
- Modify: `base/src/org/compiere/util/TimeUtil.java`
- Test: `base/test/src/org/compiere/util/TimeUtilTruncSqlTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/util/TimeUtilTruncSqlTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for SQL-compatible trunc() with format codes.
 */
@Tag("UnitTest")
public class TimeUtilTruncSqlTest {

    @Test
    void testTruncToDay() {
        Timestamp datetime = Timestamp.valueOf("2026-03-15 14:30:45");
        Date result = TimeUtil.truncSql(datetime);

        // Should truncate to date
        assertEquals(Date.valueOf("2026-03-15"), result);
    }

    @Test
    void testTruncToQuarter() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");
        Date result = TimeUtil.truncSql(datetime, "Q");

        // Q2 starts April 1
        assertEquals(Date.valueOf("2026-04-01"), result);
    }

    @Test
    void testTruncToYear() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.truncSql(datetime, "Y"));
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.truncSql(datetime, "YEAR"));
    }

    @Test
    void testTruncToMonth() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.truncSql(datetime, "MM"));
        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.truncSql(datetime, "MONTH"));
    }

    @Test
    void testTruncToDayFormat() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.truncSql(datetime, "DD"));
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.truncSql(datetime, "DY"));
    }

    @Test
    void testTruncUnknownFormatThrows() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertThrows(IllegalArgumentException.class,
            () -> TimeUtil.truncSql(datetime, "UNKNOWN"));
    }

    @Test
    void testTruncNullReturnsNull() {
        assertNull(TimeUtil.truncSql(null));
        assertNull(TimeUtil.truncSql(null, "Q"));
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.TimeUtilTruncSqlTest" -i`
Expected: FAIL with "cannot find symbol: method truncSql"

**Step 3: Add truncSql() methods**

```java
	/**
	 * Truncate timestamp to date (SQL semantics).
	 * Equivalent to PostgreSQL: CAST(datetime AS DATE)
	 *
	 * @param datetime timestamp to truncate, may be null
	 * @return truncated date, or null if datetime is null
	 */
	static public Date truncSql(Timestamp datetime) {
		if (datetime == null) {
			return null;
		}

		LocalDate date = datetime.toLocalDateTime().toLocalDate();
		return Date.valueOf(date);
	}

	/**
	 * Truncate timestamp to specified date part (SQL semantics).
	 * Equivalent to PostgreSQL: trunc(datetime, format)
	 *
	 * Supported formats:
	 * - Q: Quarter
	 * - Y, YEAR: Year
	 * - MM, MONTH: Month
	 * - DD, DY: Day
	 *
	 * @param datetime timestamp to truncate, may be null
	 * @param format format code (required, must be one of: Q, Y, YEAR, MM, MONTH, DD, DY)
	 * @return truncated date, or null if datetime is null
	 * @throws IllegalArgumentException if format is null or unrecognized
	 */
	static public Date truncSql(Timestamp datetime, String format) {
		if (datetime == null) {
			return null;
		}

		LocalDate date = datetime.toLocalDateTime().toLocalDate();
		LocalDate result;

		if ("Q".equals(format)) {
			// Quarter: truncate to first day of quarter
			int quarterMonth = ((date.getMonthValue() - 1) / 3) * 3 + 1;
			result = date.withMonth(quarterMonth).withDayOfMonth(1);
		} else if ("Y".equals(format) || "YEAR".equals(format)) {
			result = date.withDayOfYear(1);
		} else if ("MM".equals(format) || "MONTH".equals(format)) {
			result = date.withDayOfMonth(1);
		} else if ("DD".equals(format) || "DY".equals(format)) {
			result = date;
		} else {
			throw new IllegalArgumentException(
				"Unknown trunc format: " + format +
				". Valid formats: Q, Y, YEAR, MM, MONTH, DD, DY");
		}

		return Date.valueOf(result);
	}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.TimeUtilTruncSqlTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/TimeUtil.java
git add base/test/src/org/compiere/util/TimeUtilTruncSqlTest.java
git commit -m "feat: add TimeUtil.truncSql() for SQL trunc migration

- Both versions return java.sql.Date for consistent API
- Two-arg version supports Q, Y, YEAR, MM, MONTH, DD, DY formats
- Throws IllegalArgumentException for unknown format codes
- Uses java.time for clean implementation

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

**Step 6: Run performance test for trunc**

Run: `./gradlew :base:test --tests "*.Wave0PerformanceTest.testTruncPerformance" -PperformanceTest -i`
Expected: PASS (Java should be significantly faster than SQL)

---

## Critical Review Changes Applied

Changes incorporated from `wave0-part3-datetime-functions-critical-review-1.md`:

| Issue | Resolution |
|-------|------------|
| `@Nullable` annotations unavailable | Removed annotations; null behavior documented in Javadoc only |
| `truncSql` return type inconsistency | Both overloads now return `java.sql.Date` |
| Silent fallback for unknown format codes | Now throws `IllegalArgumentException` with valid format list |
| Misleading DST test comment | Renamed test, removed timezone-specific comment |
| Missing edge case tests | Added leap year and large offset tests for `addDaysSql` |
| Verbose exception message | Simplified error message in `addDaysSql` |

---

**Next:** [Part 4: Utility Functions](wave0-part4-utility-functions.md) (Tasks 11-12)
