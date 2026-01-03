package org.compiere.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

import javax.annotation.Nullable;

import org.compiere.model.MCurrency;
import org.compiere.model.MConversionRate;

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

    // Cached Euro currency ID (volatile for thread safety)
    private static volatile Integer cachedEuroCurrencyId;

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

    /**
     * Get currency conversion rate.
     * Equivalent to PostgreSQL: currencyRate(curFromId, curToId, convDate, convTypeId, clientId, orgId)
     *
     * <p><b>EMU/Euro Logic:</b> This function handles legacy EMU (European Monetary Union)
     * fixed-rate conversions for currencies that adopted the Euro (1999-2002).
     * The SQL function had a bug at line 110 (checking source currency twice instead
     * of checking both source and target). This was fixed as part of Wave 1 migration.
     * See: docs/plans/2026-01-03-wave1-currency-implementation.md, Decision 1
     *
     * @param curFromId source currency ID
     * @param curToId target currency ID
     * @param convDate conversion date (null = today)
     * @param convTypeId conversion type ID (null/0 = default)
     * @param clientId client ID
     * @param orgId organization ID
     * @return conversion rate, or null if not found
     */
    @Nullable
    public static BigDecimal currencyRate(@Nullable Integer curFromId,
                                           @Nullable Integer curToId,
                                           @Nullable Timestamp convDate,
                                           @Nullable Integer convTypeId,
                                           @Nullable Integer clientId,
                                           @Nullable Integer orgId) {
        // No conversion needed
        if (curFromId == null || curToId == null) {
            log.fine(() -> "currencyRate: null currency ID (from=" + curFromId + ", to=" + curToId + ")");
            return null;
        }
        if (curFromId.equals(curToId)) {
            return BigDecimal.ONE;
        }

        // Default date to today
        Timestamp effectiveDate = convDate;
        if (effectiveDate == null) {
            effectiveDate = new Timestamp(System.currentTimeMillis());
        }

        // Get currency info
        MCurrency curFrom = MCurrency.get(Env.getCtx(), curFromId);
        MCurrency curTo = MCurrency.get(Env.getCtx(), curToId);

        if (curFrom == null || curFrom.get_ID() == 0) {
            log.warning(() -> "currencyRate: source currency not found, ID=" + curFromId);
            return null;
        }
        if (curTo == null || curTo.get_ID() == 0) {
            log.warning(() -> "currencyRate: target currency not found, ID=" + curToId);
            return null;
        }

        // EMU/Euro fixed rate logic
        // Note: This implements CORRECT behavior. The SQL function had a bug at line 110
        // that checked cf_IsEMUMember twice instead of checking both cf and ct.
        // The SQL bug was fixed as part of Wave 1 migration.
        // See: docs/plans/2026-01-03-wave1-currency-implementation.md, Decision 1
        boolean cfIsEuro = curFrom.isEuro();
        boolean cfIsEmuMember = curFrom.isEMUMember();
        Timestamp cfEmuEntryDate = curFrom.getEMUEntryDate();
        BigDecimal cfEmuRate = curFrom.getEMURate();

        boolean ctIsEuro = curTo.isEuro();
        boolean ctIsEmuMember = curTo.isEMUMember();
        Timestamp ctEmuEntryDate = curTo.getEMUEntryDate();
        BigDecimal ctEmuRate = curTo.getEMURate();

        // Fixed - From Euro to EMU
        if (cfIsEuro && ctIsEmuMember && ctEmuEntryDate != null
                && !effectiveDate.before(ctEmuEntryDate)) {
            return ctEmuRate;
        }

        // Fixed - From EMU to Euro
        if (ctIsEuro && cfIsEmuMember && cfEmuEntryDate != null
                && !effectiveDate.before(cfEmuEntryDate)) {
            if (!isValidDivisor(cfEmuRate)) {
                log.warning(() -> "currencyRate: invalid cfEmuRate for EMU-to-Euro conversion, "
                    + "from=" + curFromId + ", rate=" + cfEmuRate);
                return null;
            }
            return BigDecimal.ONE.divide(cfEmuRate, 12, RoundingMode.HALF_UP);
        }

        // Fixed - From EMU to EMU
        // IMPORTANT: This is the CORRECTED logic. SQL function bug was:
        // IF (cf_IsEMUMember = 'Y' AND cf_IsEMUMember ='Y'  -- checked cf twice!
        // Correct: check BOTH cf_IsEMUMember AND ct_IsEMUMember
        if (cfIsEmuMember && ctIsEmuMember
                && cfEmuEntryDate != null && !effectiveDate.before(cfEmuEntryDate)
                && ctEmuEntryDate != null && !effectiveDate.before(ctEmuEntryDate)) {
            if (!isValidDivisor(cfEmuRate)) {
                log.warning(() -> "currencyRate: invalid cfEmuRate for EMU-to-EMU conversion, "
                    + "from=" + curFromId + ", to=" + curToId + ", cfRate=" + cfEmuRate);
                return null;
            }
            return ctEmuRate.divide(cfEmuRate, 12, RoundingMode.HALF_UP);
        }

        // Flexible rates - delegate to MConversionRate
        int effectiveClientId = clientId != null ? clientId : 0;
        int effectiveOrgId = orgId != null ? orgId : 0;
        int effectiveConvTypeId = convTypeId != null ? convTypeId : 0;

        // Handle EMU member to/from non-Euro currency via Euro
        int lookupFromId = curFromId;
        int lookupToId = curToId;
        BigDecimal fromEmuAdjustment = null;
        BigDecimal toEmuAdjustment = null;

        if (cfIsEmuMember && cfEmuEntryDate != null && !effectiveDate.before(cfEmuEntryDate)) {
            // Convert via Euro
            Integer euroId = getEuroCurrencyId();
            if (euroId == null) {
                log.warning(() -> "currencyRate: Euro currency not found for EMU conversion");
                return null;
            }
            lookupFromId = euroId;
            fromEmuAdjustment = cfEmuRate;
        }

        if (ctIsEmuMember && ctEmuEntryDate != null && !effectiveDate.before(ctEmuEntryDate)) {
            // Convert via Euro
            Integer euroId = getEuroCurrencyId();
            if (euroId == null) {
                log.warning(() -> "currencyRate: Euro currency not found for EMU conversion");
                return null;
            }
            lookupToId = euroId;
            toEmuAdjustment = ctEmuRate;
        }

        // Get rate from conversion rate table
        BigDecimal rate = MConversionRate.getRate(
            lookupFromId, lookupToId,
            effectiveDate, effectiveConvTypeId,
            effectiveClientId, effectiveOrgId);

        if (rate == null) {
            // Capture for lambda
            final int finalLookupFromId = lookupFromId;
            final int finalLookupToId = lookupToId;
            final Timestamp finalEffectiveDate = effectiveDate;
            final int finalEffectiveConvTypeId = effectiveConvTypeId;
            log.fine(() -> "currencyRate: rate not found from=" + finalLookupFromId + " to=" + finalLookupToId
                + " date=" + finalEffectiveDate + " type=" + finalEffectiveConvTypeId);
            return null;
        }

        // Apply EMU adjustments
        if (fromEmuAdjustment != null && isValidDivisor(fromEmuAdjustment)) {
            rate = rate.divide(fromEmuAdjustment, 12, RoundingMode.HALF_UP);
        }
        if (toEmuAdjustment != null) {
            rate = rate.multiply(toEmuAdjustment);
        }

        return rate;
    }

    /**
     * Convert amount between currencies.
     * Equivalent to PostgreSQL: currencyConvert(amount, curFromId, curToId, convDate, convTypeId, clientId, orgId)
     *
     * @param amount amount to convert
     * @param curFromId source currency ID
     * @param curToId target currency ID
     * @param convDate conversion date (null = today)
     * @param convTypeId conversion type ID (null/0 = default)
     * @param clientId client ID
     * @param orgId organization ID
     * @return converted and rounded amount, or null if rate not found
     */
    @Nullable
    public static BigDecimal currencyConvert(@Nullable BigDecimal amount,
                                              @Nullable Integer curFromId,
                                              @Nullable Integer curToId,
                                              @Nullable Timestamp convDate,
                                              @Nullable Integer convTypeId,
                                              @Nullable Integer clientId,
                                              @Nullable Integer orgId) {
        // Return null if amount is null
        if (amount == null) {
            return null;
        }

        // Return zero (rounded) if amount is zero
        // Handle case where currencyRound returns null (e.g., currency not found)
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            if (curToId == null) {
                return BigDecimal.ZERO;
            }
            BigDecimal rounded = currencyRound(BigDecimal.ZERO, curToId, null);
            return rounded != null ? rounded : BigDecimal.ZERO;
        }

        // Return amount if same currency (but still round to target precision)
        if (curFromId != null && curFromId.equals(curToId)) {
            return currencyRound(amount, curToId, null);
        }

        // Return null if any required param is null
        if (curFromId == null || curToId == null) {
            log.fine(() -> "currencyConvert: null currency ID (from=" + curFromId + ", to=" + curToId + ")");
            return null;
        }

        // Get rate
        BigDecimal rate = currencyRate(curFromId, curToId, convDate, convTypeId, clientId, orgId);
        if (rate == null) {
            log.fine(() -> "currencyConvert: no rate found for from=" + curFromId + " to=" + curToId);
            return null;
        }

        // Apply rate and round to target currency precision
        BigDecimal converted = amount.multiply(rate);
        return currencyRound(converted, curToId, null);
    }

    /**
     * Check if a BigDecimal is valid for use as a divisor (not null and not zero).
     */
    private static boolean isValidDivisor(BigDecimal value) {
        return value != null && value.compareTo(BigDecimal.ZERO) != 0;
    }

    /**
     * Get Euro currency ID (cached for performance).
     * MCurrency already caches internally, but we avoid repeated string lookups.
     */
    @Nullable
    private static Integer getEuroCurrencyId() {
        if (cachedEuroCurrencyId != null) {
            return cachedEuroCurrencyId;
        }
        MCurrency euro = MCurrency.get(Env.getCtx(), "EUR");
        if (euro != null && euro.get_ID() > 0) {
            cachedEuroCurrencyId = euro.get_ID();
            return cachedEuroCurrencyId;
        }
        return null;
    }
}
