// base/src/org/compiere/util/CurrencyFunctionRouter.java
package org.compiere.util;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Nullable;

import org.compiere.migration.SqlFunctionCaller;
import org.compiere.migration.ShadowExecutor;
import org.compiere.migration.comparators.BigDecimalComparator;

/**
 * Router for Wave 1 currency functions.
 * Delegates to ShadowExecutor for mode-aware execution (SQL_ONLY, SHADOW, JAVA_ONLY).
 *
 * <p>Use these methods in application code to enable gradual migration:
 * <ul>
 *   <li>SQL_ONLY: Calls SQL function only (default, safe)</li>
 *   <li>SHADOW: Calls both, compares with tolerance, logs, returns Java result</li>
 *   <li>JAVA_ONLY: Calls Java only (post-validation)</li>
 * </ul>
 *
 * <p><b>Comparison Tolerance:</b> Uses 6 decimal place tolerance for currency
 * functions due to division rounding differences between Java and SQL.
 * See: docs/plans/2026-01-03-wave1-currency-implementation.md, Decision 2
 *
 * @see CurrencyFunctions for the Java implementations
 * @see SqlFunctionCaller for the SQL callers
 */
public class CurrencyFunctionRouter {

    private CurrencyFunctionRouter() {
        // Utility class
    }

    /**
     * Route currencyRound through shadow executor.
     */
    @Nullable
    public static BigDecimal currencyRound(@Nullable BigDecimal amount,
                                            @Nullable Integer currencyId,
                                            @Nullable String costing) {
        return ShadowExecutor.execute(
            "currencyRound",
            new Object[]{amount, currencyId, costing},
            () -> CurrencyFunctions.currencyRound(amount, currencyId, costing),
            () -> SqlFunctionCaller.callCurrencyRound(amount, currencyId, costing),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route currencyRate through shadow executor.
     */
    @Nullable
    public static BigDecimal currencyRate(@Nullable Integer curFromId,
                                           @Nullable Integer curToId,
                                           @Nullable Timestamp convDate,
                                           @Nullable Integer convTypeId,
                                           @Nullable Integer clientId,
                                           @Nullable Integer orgId) {
        return ShadowExecutor.execute(
            "currencyRate",
            new Object[]{curFromId, curToId, convDate, convTypeId, clientId, orgId},
            () -> CurrencyFunctions.currencyRate(curFromId, curToId, convDate, convTypeId, clientId, orgId),
            () -> SqlFunctionCaller.callCurrencyRate(curFromId, curToId, convDate, convTypeId, clientId, orgId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route currencyConvert through shadow executor.
     */
    @Nullable
    public static BigDecimal currencyConvert(@Nullable BigDecimal amount,
                                              @Nullable Integer curFromId,
                                              @Nullable Integer curToId,
                                              @Nullable Timestamp convDate,
                                              @Nullable Integer convTypeId,
                                              @Nullable Integer clientId,
                                              @Nullable Integer orgId) {
        return ShadowExecutor.execute(
            "currencyConvert",
            new Object[]{amount, curFromId, curToId, convDate, convTypeId, clientId, orgId},
            () -> CurrencyFunctions.currencyConvert(amount, curFromId, curToId, convDate, convTypeId, clientId, orgId),
            () -> SqlFunctionCaller.callCurrencyConvert(amount, curFromId, curToId, convDate, convTypeId, clientId, orgId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route currencyBase (6-param) through shadow executor.
     */
    @Nullable
    public static BigDecimal currencyBase(@Nullable BigDecimal amount,
                                           @Nullable Integer curFromId,
                                           @Nullable Timestamp convDate,
                                           @Nullable Integer convTypeId,
                                           @Nullable Integer clientId,
                                           @Nullable Integer orgId) {
        return ShadowExecutor.execute(
            "currencyBase",
            new Object[]{amount, curFromId, convDate, convTypeId, clientId, orgId},
            () -> CurrencyFunctions.currencyBase(amount, curFromId, convDate, convTypeId, clientId, orgId),
            () -> SqlFunctionCaller.callCurrencyBase(amount, curFromId, convDate, convTypeId, clientId, orgId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route currencyBase (5-param) through shadow executor.
     */
    @Nullable
    public static BigDecimal currencyBase(@Nullable BigDecimal amount,
                                           @Nullable Integer curFromId,
                                           @Nullable Timestamp convDate,
                                           @Nullable Integer clientId,
                                           @Nullable Integer orgId) {
        return ShadowExecutor.execute(
            "currencyBase",
            new Object[]{amount, curFromId, convDate, clientId, orgId},
            () -> CurrencyFunctions.currencyBase(amount, curFromId, convDate, clientId, orgId),
            () -> SqlFunctionCaller.callCurrencyBase(amount, curFromId, convDate, clientId, orgId),
            BigDecimalComparator.CURRENCY
        );
    }
}
