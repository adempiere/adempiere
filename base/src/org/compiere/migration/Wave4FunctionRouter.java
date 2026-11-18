package org.compiere.migration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.migration.comparators.BigDecimalComparator;
import org.compiere.migration.comparators.StringComparator;
import org.compiere.migration.comparators.TimestampComparator;

/**
 * Router for Wave 4 standalone functions.
 * Delegates to ShadowExecutor for shadow validation.
 */
public class Wave4FunctionRouter {

    private Wave4FunctionRouter() {
        // Static methods only
    }

    /**
     * Route acct_balance function call.
     * Calculates account balance considering natural sign.
     */
    public static BigDecimal acctBalance(Integer accountId, BigDecimal amtDr, BigDecimal amtCr) {
        return ShadowExecutor.execute(
            "acctBalance",
            new Object[]{accountId, amtDr, amtCr},
            () -> Wave4Functions.acctBalance(accountId, amtDr, amtCr),
            () -> SqlFunctionCaller.callAcctBalance(accountId, amtDr, amtCr),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route get_sysconfig function call.
     * Retrieves system configuration with precedence.
     */
    public static String getSysconfig(String name, String defaultValue, Integer clientId, Integer orgId) {
        return ShadowExecutor.execute(
            "get_Sysconfig",
            new Object[]{name, defaultValue, clientId, orgId},
            () -> Wave4Functions.getSysconfig(name, defaultValue, clientId, orgId),
            () -> SqlFunctionCaller.callGetSysconfig(name, defaultValue, clientId, orgId),
            StringComparator.TRIM_NULLSAFE
        );
    }

    /**
     * Route productattribute function call.
     * Builds display string for attribute set instance.
     */
    public static String productAttribute(Integer attributeSetInstanceId) {
        return ShadowExecutor.execute(
            "productAttribute",
            new Object[]{attributeSetInstanceId},
            () -> Wave4Functions.productAttribute(attributeSetInstanceId),
            () -> SqlFunctionCaller.callProductAttribute(attributeSetInstanceId),
            StringComparator.NULLSAFE
        );
    }

    /**
     * Route documentno function call.
     * Returns document number for MRP record.
     */
    public static String documentNo(Integer ppMrpId) {
        return ShadowExecutor.execute(
            "documentNo",
            new Object[]{ppMrpId},
            () -> Wave4Functions.documentNo(ppMrpId),
            () -> SqlFunctionCaller.callDocumentNo(ppMrpId),
            StringComparator.NULLSAFE
        );
    }

    /**
     * Route linenetamtrealinvoiceline function call.
     * Calculates net amount excluding tax if tax-inclusive.
     */
    public static BigDecimal linenetamtrealinvoiceline(Integer invoiceLineId) {
        return ShadowExecutor.execute(
            "linenetamtrealinvoiceline",
            new Object[]{invoiceLineId},
            () -> Wave4Functions.linenetamtrealinvoiceline(invoiceLineId),
            () -> SqlFunctionCaller.callLinenetamtrealinvoiceline(invoiceLineId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route linenetamtrealorderline function call.
     * Calculates net amount excluding tax if tax-inclusive.
     */
    public static BigDecimal linenetamtrealorderline(Integer orderLineId) {
        return ShadowExecutor.execute(
            "linenetamtrealorderline",
            new Object[]{orderLineId},
            () -> Wave4Functions.linenetamtrealorderline(orderLineId),
            () -> SqlFunctionCaller.callLinenetamtrealorderline(orderLineId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route maxpaydate function call.
     * Returns latest payment date for invoice.
     *
     * Note: Uses EXACT comparison initially for shadow validation to catch any
     * timezone issues. Can be relaxed to SAME_DAY after validation confirms no
     * timestamp discrepancies.
     */
    public static Timestamp maxpaydate(Integer invoiceId) {
        return ShadowExecutor.execute(
            "maxpaydate",
            new Object[]{invoiceId},
            () -> Wave4Functions.maxpaydate(invoiceId),
            () -> SqlFunctionCaller.callMaxpaydate(invoiceId),
            TimestampComparator.exact()  // Use EXACT initially; relax to SAME_DAY after shadow validation
        );
    }
}
