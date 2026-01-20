# Wave 0 Part 4: Utility Functions

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Parent Plan:** [Wave 0 Implementation Plan](2026-01-02-wave0-implementation-plan.md)
**Part:** 4 of 5
**Tasks:** 11-12 (SqlCompat with round/charAt, firstOf)

**Previous:** [Part 3: DateTime Functions](wave0-part3-datetime-functions.md)

---

## Task 11: Create SqlCompat class with round() and charAt()

**Files:**
- Create: `base/src/org/compiere/util/SqlCompat.java`
- Test: `base/test/src/org/compiere/util/SqlCompatTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/util/SqlCompatTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for SQL-compatible utility functions.
 */
@Tag("UnitTest")
public class SqlCompatTest {

    // round() tests
    @Test
    void testRoundPositiveScale() {
        BigDecimal value = new BigDecimal("123.456789");

        assertEquals(new BigDecimal("123.46"), SqlCompat.round(value, 2));
    }

    @Test
    void testRoundZeroScale() {
        BigDecimal value = new BigDecimal("123.456789");

        assertEquals(new BigDecimal("123"), SqlCompat.round(value, 0));
    }

    @Test
    void testRoundNegativeScale() {
        BigDecimal value = new BigDecimal("1234.56");

        // Rounds to nearest hundred
        assertEquals(new BigDecimal("1200"), SqlCompat.round(value, -2));
    }

    @Test
    void testRoundNullValue() {
        assertNull(SqlCompat.round(null, 2));
    }

    @Test
    void testRoundHalfUp() {
        // Verify HALF_UP rounding mode (standard SQL behavior)
        assertEquals(new BigDecimal("1.5"), SqlCompat.round(new BigDecimal("1.45"), 1));
        assertEquals(new BigDecimal("1.4"), SqlCompat.round(new BigDecimal("1.44"), 1));
        assertEquals(new BigDecimal("2"), SqlCompat.round(new BigDecimal("1.5"), 0));
    }

    // charAt() tests
    @Test
    void testCharAtFirstPosition() {
        assertEquals("H", SqlCompat.charAt("Hello", 1));
    }

    @Test
    void testCharAtMiddlePosition() {
        assertEquals("l", SqlCompat.charAt("Hello", 3));
    }

    @Test
    void testCharAtLastPosition() {
        assertEquals("o", SqlCompat.charAt("Hello", 5));
    }

    @Test
    void testCharAtOutOfBounds() {
        // SQL SUBSTR returns empty string for out of bounds
        assertEquals("", SqlCompat.charAt("Hello", 10));
    }

    @Test
    void testCharAtZeroPosition() {
        // SQL positions are 1-based, 0 is invalid
        assertEquals("", SqlCompat.charAt("Hello", 0));
    }

    @Test
    void testCharAtNegativePosition() {
        // SQL positions are 1-based, negative is invalid
        assertEquals("", SqlCompat.charAt("Hello", -1));
    }

    @Test
    void testCharAtNullString() {
        assertNull(SqlCompat.charAt(null, 1));
    }

    @Test
    void testCharAtEmptyString() {
        assertEquals("", SqlCompat.charAt("", 1));
    }

    @Test
    void testCharAtVeryLargePosition() {
        assertEquals("", SqlCompat.charAt("Hello", Integer.MAX_VALUE));
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.SqlCompatTest" -i`
Expected: FAIL with "cannot find symbol: class SqlCompat"

**Step 3: Write SqlCompat implementation**

```java
// base/src/org/compiere/util/SqlCompat.java
package org.compiere.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * SQL-compatible utility functions for function migration.
 * These methods match PostgreSQL function semantics exactly.
 */
public class SqlCompat {

    private SqlCompat() {
        // Utility class - prevent instantiation
    }

    /**
     * Round a numeric value to specified scale (SQL semantics).
     * Equivalent to PostgreSQL: ROUND($1, cast($2 as integer))
     * Uses HALF_UP rounding mode (standard SQL behavior).
     *
     * @param value value to round (may be null)
     * @param scale number of decimal places (can be negative for rounding to 10s, 100s, etc.)
     * @return rounded value, or null if value is null
     */
    public static BigDecimal round(BigDecimal value, int scale) {
        if (value == null) {
            return null;
        }

        if (scale >= 0) {
            return value.setScale(scale, RoundingMode.HALF_UP);
        } else {
            // Negative scale: round to 10s, 100s, etc.
            BigDecimal multiplier = BigDecimal.TEN.pow(-scale);
            BigDecimal divided = value.divide(multiplier, 0, RoundingMode.HALF_UP);
            return divided.multiply(multiplier);
        }
    }

    /**
     * Get character at position (SQL semantics, 1-based).
     * Equivalent to PostgreSQL: SUBSTR($1, $2, 1)
     *
     * @param str input string (may be null)
     * @param position 1-based position (0 or negative returns empty string)
     * @return character at position, empty string if out of bounds, null if str is null
     */
    public static String charAt(String str, int position) {
        if (str == null) {
            return null;
        }

        // SQL positions are 1-based
        int index = position - 1;

        if (index < 0 || index >= str.length()) {
            return "";
        }

        return String.valueOf(str.charAt(index));
    }
}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.SqlCompatTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/SqlCompat.java
git add base/test/src/org/compiere/util/SqlCompatTest.java
git commit -m "feat: add SqlCompat with round() and charAt() for SQL migration

- round(): matches PostgreSQL ROUND(value, scale), handles negative scale
- round(): uses HALF_UP rounding mode (standard SQL behavior)
- charAt(): matches PostgreSQL SUBSTR(str, pos, 1), 1-based indexing
- Both return null for null inputs

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

**Step 6: Run performance test for round**

Run: `./gradlew :base:test --tests "*.Wave0PerformanceTest.testRoundPerformance" -PperformanceTest -i`
Expected: PASS (Java should be significantly faster than SQL)

---

## Task 12: Implement firstOf() with Oracle-compatible format codes

**Files:**
- Modify: `base/src/org/compiere/util/TimeUtil.java`
- Test: `base/test/src/org/compiere/util/TimeUtilFirstOfTest.java`

**Step 1: Write the failing test**

```java
// base/test/src/org/compiere/util/TimeUtilFirstOfTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for SQL-compatible firstOf() with Oracle date format codes.
 */
@Tag("UnitTest")
public class TimeUtilFirstOfTest {

    @Test
    void testFirstOfYear() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "YYYY"));
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "YEAR"));
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "YYY"));
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "YY"));
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "Y"));
    }

    @Test
    void testFirstOfQuarter() {
        // Q1: Jan-Mar
        assertEquals(Date.valueOf("2026-01-01"),
            TimeUtil.firstOf(Timestamp.valueOf("2026-02-15 00:00:00"), "Q"));
        // Q2: Apr-Jun
        assertEquals(Date.valueOf("2026-04-01"),
            TimeUtil.firstOf(Timestamp.valueOf("2026-05-15 00:00:00"), "Q"));
        // Q3: Jul-Sep
        assertEquals(Date.valueOf("2026-07-01"),
            TimeUtil.firstOf(Timestamp.valueOf("2026-08-15 00:00:00"), "Q"));
        // Q4: Oct-Dec
        assertEquals(Date.valueOf("2026-10-01"),
            TimeUtil.firstOf(Timestamp.valueOf("2026-11-15 00:00:00"), "Q"));
    }

    @Test
    void testFirstOfMonth() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.firstOf(datetime, "MONTH"));
        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.firstOf(datetime, "MON"));
        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.firstOf(datetime, "MM"));
        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.firstOf(datetime, "RM"));
    }

    @Test
    void testFirstOfWeek() {
        // 2026-01-15 is a Thursday. ISO week starts Monday = 2026-01-12
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:45");

        // IW uses ISO week (Monday start)
        assertEquals(Date.valueOf("2026-01-12"), TimeUtil.firstOf(datetime, "IW"));
        // W also uses week
        assertEquals(Date.valueOf("2026-01-12"), TimeUtil.firstOf(datetime, "W"));
    }

    @Test
    void testFirstOfDay() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "DDD"));
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "DD"));
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "J"));
    }

    @Test
    void testFirstOfDayOfWeek() {
        // DAY/DY/D: week start with -1 offset for Oracle compatibility
        // 2026-01-15 is Thursday, ISO Monday = 2026-01-12, -1 = 2026-01-11 (Sun)
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:45");

        Date result = TimeUtil.firstOf(datetime, "DAY");
        assertEquals(Date.valueOf("2026-01-11"), result);
    }

    @Test
    void testFirstOfHour() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        // Hour truncation returns date at that hour (but we return Date, so just the date)
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "HH"));
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "HH12"));
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "HH24"));
    }

    @Test
    void testFirstOfMinute() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "MI"));
    }

    @Test
    void testFirstOfNull() {
        assertNull(TimeUtil.firstOf(null, "YYYY"));
    }

    @Test
    void testFirstOfNullFormat() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");
        // Null format should return the date as-is
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, null));
    }

    @Test
    void testFirstOfCaseInsensitive() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");
        // Format codes should be case-insensitive
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "yyyy"));
        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.firstOf(datetime, "month"));
        assertEquals(Date.valueOf("2026-04-01"), TimeUtil.firstOf(datetime, "q"));
    }
}
```

**Step 2: Run test to verify it fails**

Run: `./gradlew :base:test --tests "*.TimeUtilFirstOfTest" -i`
Expected: FAIL with "cannot find symbol: method firstOf"

**Step 3: Add firstOf() method**

Add required import at top of TimeUtil.java:
```java
import java.time.DayOfWeek;
```

Add after truncSql() methods:

```java
	/**
	 * Get first date of specified period (SQL/Oracle semantics).
	 * Equivalent to PostgreSQL firstOf() function with Oracle-compatible format codes.
	 * Uses java.time API for locale-independent week calculations.
	 * Format codes are case-insensitive.
	 *
	 * Supported formats:
	 * - IYYY, IY, I, SYYYY, YYYY, YEAR, SYEAR, YYY, YY, Y: First of year
	 * - Q: First of quarter
	 * - MONTH, MON, MM, RM: First of month
	 * - IW, W: First of week (ISO week, Monday start)
	 * - DDD, DD, J: Day (unchanged)
	 * - DAY, DY, D: First of week (Sunday start, Oracle compatible)
	 * - HH, HH12, HH24: Hour (returns date at that hour)
	 * - MI: Minute (returns date)
	 *
	 * @param datetime timestamp (may be null)
	 * @param datePart format code (may be null, returns date as-is)
	 * @return first date of the period, or null if datetime is null
	 */
	static public Date firstOf(Timestamp datetime, String datePart) {
		if (datetime == null) {
			return null;
		}

		LocalDate date = datetime.toLocalDateTime().toLocalDate();

		if (datePart == null || datePart.isEmpty()) {
			return Date.valueOf(date);
		}

		String fmt = datePart.toUpperCase();
		LocalDate result;

		if ("IYYY".equals(fmt) || "IY".equals(fmt) || "I".equals(fmt) ||
				   "SYYYY".equals(fmt) || "YYYY".equals(fmt) || "YEAR".equals(fmt) ||
				   "SYEAR".equals(fmt) || "YYY".equals(fmt) || "YY".equals(fmt) ||
				   "Y".equals(fmt)) {
			// First of year
			result = date.withDayOfYear(1);
		} else if ("Q".equals(fmt)) {
			// First of quarter
			int quarterMonth = ((date.getMonthValue() - 1) / 3) * 3 + 1;
			result = date.withMonth(quarterMonth).withDayOfMonth(1);
		} else if ("MONTH".equals(fmt) || "MON".equals(fmt) ||
				   "MM".equals(fmt) || "RM".equals(fmt)) {
			// First of month
			result = date.withDayOfMonth(1);
		} else if ("IW".equals(fmt) || "W".equals(fmt)) {
			// ISO week (Monday start)
			result = date.with(DayOfWeek.MONDAY);
		} else if ("DAY".equals(fmt) || "DY".equals(fmt) || "D".equals(fmt)) {
			// Oracle week (Sunday start) = ISO Monday - 1
			result = date.with(DayOfWeek.MONDAY).minusDays(1);
		} else if ("DDD".equals(fmt) || "DD".equals(fmt) || "J".equals(fmt)) {
			// Day - no change
			result = date;
		} else if ("HH".equals(fmt) || "HH12".equals(fmt) || "HH24".equals(fmt) ||
				   "MI".equals(fmt)) {
			// Hour/Minute - just return the date
			result = date;
		} else {
			// Unknown format - return date as-is
			result = date;
		}

		return Date.valueOf(result);
	}
```

**Step 4: Run test to verify it passes**

Run: `./gradlew :base:test --tests "*.TimeUtilFirstOfTest" -i`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/util/TimeUtil.java
git add base/test/src/org/compiere/util/TimeUtilFirstOfTest.java
git commit -m "feat: add TimeUtil.firstOf() for SQL firstOf migration

- Supports Oracle-compatible format codes (YYYY, Q, MONTH, etc.)
- Format codes are case-insensitive
- Uses java.time for locale-independent week calculations
- DAY/DY/D uses Sunday start for Oracle compatibility
- IW/W uses ISO week (Monday start)
- Returns null for null inputs

🤖 Generated with [Claude Code](https://claude.com/claude-code)

Co-Authored-By: Claude <noreply@anthropic.com>"
```

**Step 6: Run performance test for firstOf**

Run: `./gradlew :base:test --tests "*.Wave0PerformanceTest.testFirstOfPerformance" -PperformanceTest -i`
Expected: PASS (Java should be significantly faster than SQL)

---

**Next:** [Part 5: Testing & Deployment](wave0-part5-testing-deployment.md) (Tasks 13, 15-16)
