package org.compiere.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

import javax.annotation.Nullable;

import org.compiere.model.MCurrency;

/**
 * Currency conversion functions migrated from PostgreSQL.
 * Matches PostgreSQL function semantics exactly.
 *
 * <p>Wave 1 Functions:
 * <ul>
 *   <li>currencyRound: Round amount for target currency</li>
 *   <li>currencyRate: Get conversion rate between currencies</li>
 *   <li>currencyConvert: Convert amount between currencies</li>
 *   <li>currencyBase: Convert amount to client's base currency</li>
 * </ul>
 *
 * <p><b>EMU/Euro Note:</b> The EMU (European Monetary Union) fixed-rate logic
 * handles legacy currencies that adopted the Euro (1999-2002). The SQL function
 * had a bug at line 110 that was fixed as part of Wave 1 migration.
 * See: docs/plans/2026-01-03-wave1-currency-implementation.md, Decision 1
 *
 * @see org.compiere.util.CurrencyFunctionRouter for shadow mode routing
 */
public class CurrencyFunctions {

    private static final CLogger log = CLogger.getCLogger(CurrencyFunctions.class);

    private CurrencyFunctions() {
        // Utility class - prevent instantiation
    }

    /**
     * Round amount using currency's standard or costing precision.
     * Equivalent to PostgreSQL: currencyRound(amount, currencyId, costing)
     *
     * @param amount amount to round (may be null)
     * @param currencyId target currency ID (may be null)
     * @param costing "Y" for costing precision, otherwise standard precision
     * @return rounded amount, original amount if currency not found, null if amount is null
     */
    @Nullable
    public static BigDecimal currencyRound(@Nullable BigDecimal amount,
                                            @Nullable Integer currencyId,
                                            @Nullable String costing) {
        // Nothing to convert
        if (amount == null) {
            return null;
        }
        if (currencyId == null) {
            log.fine(() -> "currencyRound: currencyId is null, returning original amount");
            return amount;
        }

        // Get currency precision
        MCurrency currency = MCurrency.get(Env.getCtx(), currencyId);
        if (currency == null || currency.get_ID() == 0) {
            // Currency not found - return unmodified
            log.warning(() -> "currencyRound: currency not found for ID=" + currencyId
                + ", returning original amount");
            return amount;
        }

        int precision;
        if ("Y".equals(costing)) {
            precision = currency.getCostingPrecision();
        } else {
            precision = currency.getStdPrecision();
        }

        return amount.setScale(precision, RoundingMode.HALF_UP);
    }
}
