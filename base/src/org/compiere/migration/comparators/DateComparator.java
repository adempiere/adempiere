// base/src/org/compiere/migration/comparators/DateComparator.java
package org.compiere.migration.comparators;

import java.sql.Date;
import java.util.function.BiPredicate;

import javax.annotation.Nullable;

/**
 * Compares dates by calendar date (year, month, day), ignoring timezone.
 * Uses LocalDate conversion to avoid timezone-at-midnight issues.
 */
public class DateComparator implements BiPredicate<Date, Date> {

    public static final DateComparator INSTANCE = new DateComparator();

    @Override
    public boolean test(@Nullable Date java, @Nullable Date sql) {
        if (java == null && sql == null) return true;
        if (java == null || sql == null) return false;

        // Compare as LocalDate - explicit and timezone-safe
        return java.toLocalDate().equals(sql.toLocalDate());
    }
}
