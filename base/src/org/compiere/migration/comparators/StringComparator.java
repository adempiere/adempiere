package org.compiere.migration.comparators;

import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Comparator for String values used in shadow validation.
 * Implements BiPredicate for use with ShadowExecutor.
 */
public class StringComparator implements BiPredicate<String, String> {

    /** Null-safe equality comparison */
    public static final StringComparator NULLSAFE = new StringComparator(false);

    /** Null-safe equality with whitespace trimming */
    public static final StringComparator TRIM_NULLSAFE = new StringComparator(true);

    private final boolean trim;

    private StringComparator(boolean trim) {
        this.trim = trim;
    }

    @Override
    public boolean test(String java, String sql) {
        if (java == null && sql == null) return true;
        if (java == null || sql == null) return false;
        String a = trim ? java.trim() : java;
        String b = trim ? sql.trim() : sql;
        return Objects.equals(a, b);
    }
}
