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

    /** Maximum iterations for business day search (defensive guard against corrupted data). */
    private static final int MAX_BUSINESS_DAY_ITERATIONS = 365;

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

        // Load MPaymentTerm
        MPaymentTerm pt = new MPaymentTerm(Env.getCtx(), paymentTermId, trxName);
        if (pt.get_ID() == 0) {
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

        MPaymentTerm pt = new MPaymentTerm(Env.getCtx(), paymentTermId, trxName);
        if (pt.get_ID() == 0) {
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

        // Determine target month: start with docDate's month, add offset, check cutoff
        LocalDate targetMonth = firstOfMonth.plusMonths(fixMonthOffset);

        // IF (NoDays > FixMonthCutoff) THEN add 1 more month
        if (noDays > fixMonthCutoff) {
            targetMonth = targetMonth.plusMonths(1);
        }

        // Now set the day within the target month
        int maxDay = targetMonth.lengthOfMonth();
        int targetDay = fixMonthDay;

        if (fixMonthDay >= 30) {
            // Special handling for end-of-month (day 30 or 31)
            if (maxDay < 30) {
                // February: clamp to last day (28 or 29)
                targetDay = maxDay;
            } else if (maxDay == 30 && fixMonthDay > 30) {
                // 30-day month but want day 31: skip to next month
                targetMonth = targetMonth.plusMonths(1);
                maxDay = targetMonth.lengthOfMonth();
                targetDay = Math.min(fixMonthDay, maxDay);
            } else if (maxDay == 31 && fixMonthDay >= 30) {
                // 31-day month and want day 30+: use last day (31)
                targetDay = maxDay;
            } else {
                // Other cases: use requested day
                targetDay = fixMonthDay;
            }
        } else if (fixMonthDay > maxDay) {
            // Requested day > maxDay but < 30: clamp to maxDay
            targetDay = maxDay;
        }

        return targetMonth.withDayOfMonth(targetDay);
    }
}
