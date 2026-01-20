// base/src/org/compiere/migration/comparators/BigDecimalComparator.java
package org.compiere.migration.comparators;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiPredicate;

/**
 * Comparator for BigDecimal values with configurable precision tolerance.
 *
 * <p>Handles null values and allows minor differences due to:
 * <ul>
 *   <li>Different intermediate precision handling between Java and SQL</li>
 *   <li>Different rounding behavior at division boundaries</li>
 *   <li>Scale differences (1.0 vs 1.00)</li>
 * </ul>
 *
 * <p><b>Tolerance Selection:</b>
 * <ul>
 *   <li>EXACT: Use for pure computation functions (no division)</li>
 *   <li>CURRENCY (6 decimals): Use for currency functions with division</li>
 * </ul>
 *
 * @see docs/plans/2026-01-03-wave1-currency-implementation.md Decision 2
 */
public class BigDecimalComparator implements BiPredicate<BigDecimal, BigDecimal> {

    /** Exact comparison - values must be equal (ignoring scale) */
    public static final BigDecimalComparator EXACT = new BigDecimalComparator(-1);

    /** Currency comparison - 6 decimal place tolerance for division rounding */
    public static final BigDecimalComparator CURRENCY = new BigDecimalComparator(6);

    private final int toleranceScale;

    /**
     * Create comparator with specified tolerance.
     *
     * @param toleranceScale number of decimal places to compare, or -1 for exact
     */
    public BigDecimalComparator(int toleranceScale) {
        this.toleranceScale = toleranceScale;
    }

    @Override
    public boolean test(BigDecimal java, BigDecimal sql) {
        if (java == null && sql == null) {
            return true;
        }
        if (java == null || sql == null) {
            return false;
        }

        if (toleranceScale < 0) {
            // Exact comparison using compareTo (ignores scale)
            return java.compareTo(sql) == 0;
        }

        // Compare at specified tolerance scale
        return java.setScale(toleranceScale, RoundingMode.HALF_UP)
                   .compareTo(sql.setScale(toleranceScale, RoundingMode.HALF_UP)) == 0;
    }

    /**
     * Get description for logging.
     */
    public String getDescription() {
        return toleranceScale < 0 ? "exact" : toleranceScale + " decimal places";
    }
}
