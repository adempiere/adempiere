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
