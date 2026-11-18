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
