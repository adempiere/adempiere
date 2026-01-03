// base/src/org/compiere/migration/comparators/TimestampComparator.java
package org.compiere.migration.comparators;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.function.BiPredicate;

import javax.annotation.Nullable;

/**
 * Compares timestamps with timezone-safe logic and configurable tolerance.
 * Uses epoch millis for comparison, which is timezone-agnostic.
 */
public class TimestampComparator implements BiPredicate<Timestamp, Timestamp> {

    private final long toleranceMs;

    public TimestampComparator(long toleranceMs) {
        this.toleranceMs = toleranceMs;
    }

    /** Default: 1 second tolerance for getDate() style comparisons */
    public static TimestampComparator withDefaultTolerance() {
        return new TimestampComparator(1000);
    }

    /** Exact match (0 tolerance) for deterministic functions */
    public static TimestampComparator exact() {
        return new TimestampComparator(0);
    }

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

    @Override
    public boolean test(@Nullable Timestamp java, @Nullable Timestamp sql) {
        if (java == null && sql == null) return true;
        if (java == null || sql == null) return false;

        // Compare as UTC epoch millis - timezone agnostic
        long diff = Math.abs(java.getTime() - sql.getTime());
        return diff <= toleranceMs;
    }
}
