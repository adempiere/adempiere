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
