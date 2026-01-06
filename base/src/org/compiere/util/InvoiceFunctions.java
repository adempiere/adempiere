package org.compiere.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import javax.annotation.Nullable;

import org.compiere.migration.ShadowExecutor;
import org.compiere.migration.SqlFunctionCaller;
import org.compiere.model.MCurrency;

/**
 * Invoice calculation functions migrated from SQL.
 * Uses ShadowExecutor for migration validation.
 */
public class InvoiceFunctions {
    private static final CLogger log = CLogger.getCLogger(InvoiceFunctions.class);
    private static final BigDecimal TOLERANCE = new BigDecimal("0.01");

    /**
     * Calculate paid/allocated amount for invoice in specified currency.
     * Equivalent to SQL function invoicePaid(p_C_Invoice_ID, p_C_Currency_ID, p_MultiplierAP).
     *
     * @param invoiceId C_Invoice_ID
     * @param currencyId target C_Currency_ID
     * @param multiplierAP multiplier for AP/AR adjustment (1 or -1)
     * @param trxName transaction name
     * @return paid amount rounded to currency precision
     */
    public static BigDecimal invoicePaid(int invoiceId, int currencyId, @Nullable BigDecimal multiplierAP, String trxName) {
        return ShadowExecutor.execute(
            "invoicePaid",
            new Object[] { invoiceId, currencyId, multiplierAP },
            () -> calculateInvoicePaidJava(invoiceId, currencyId, multiplierAP, trxName),
            () -> SqlFunctionCaller.callInvoicePaid(invoiceId, currencyId, multiplierAP),
            (java, sql) -> {
                if (java == null && sql == null) return true;
                if (java == null || sql == null) return false;
                return java.subtract(sql).abs().compareTo(TOLERANCE) <= 0;
            }
        );
    }

    private static BigDecimal calculateInvoicePaidJava(int invoiceId, int currencyId, @Nullable BigDecimal multiplierAP, String trxName) {
        BigDecimal mult = multiplierAP != null ? multiplierAP : BigDecimal.ONE;
        BigDecimal paymentAmt = BigDecimal.ZERO;

        String sql = "SELECT a.AD_Client_ID, a.AD_Org_ID, "
            + "al.Amount, al.DiscountAmt, al.WriteOffAmt, "
            + "a.C_Currency_ID, a.DateTrx "
            + "FROM C_AllocationLine al "
            + "INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID) "
            + "WHERE al.C_Invoice_ID=? "
            + "AND a.DocStatus IN ('CO','CL')";

        try (PreparedStatement pstmt = DB.prepareStatement(sql, trxName)) {
            pstmt.setInt(1, invoiceId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int adClientId = rs.getInt("AD_Client_ID");
                    int adOrgId = rs.getInt("AD_Org_ID");
                    BigDecimal amount = rs.getBigDecimal("Amount");
                    if (amount == null) amount = BigDecimal.ZERO;
                    BigDecimal discountAmt = rs.getBigDecimal("DiscountAmt");
                    if (discountAmt == null) discountAmt = BigDecimal.ZERO;
                    BigDecimal writeOffAmt = rs.getBigDecimal("WriteOffAmt");
                    if (writeOffAmt == null) writeOffAmt = BigDecimal.ZERO;
                    int allocCurrencyId = rs.getInt("C_Currency_ID");
                    Timestamp dateTrx = rs.getTimestamp("DateTrx");

                    BigDecimal total = amount.add(discountAmt).add(writeOffAmt);
                    BigDecimal converted = CurrencyFunctions.currencyConvert(
                        total, allocCurrencyId, currencyId,
                        dateTrx, null, adClientId, adOrgId);

                    if (converted != null) {
                        paymentAmt = paymentAmt.add(converted);
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "calculateInvoicePaidJava", e);
        }

        // Get currency precision (don't hardcode 2)
        MCurrency currency = MCurrency.get(Env.getCtx(), currencyId);
        int precision = currency != null ? currency.getStdPrecision() : 2;
        return paymentAmt.multiply(mult).setScale(precision, RoundingMode.HALF_UP);
    }

    /**
     * Calculate paid amount for invoice as of a specific date.
     * Equivalent to SQL function invoicePaidToDate(p_C_Invoice_ID, p_C_Currency_ID, p_MultiplierAP, p_DateAcct).
     *
     * @param invoiceId C_Invoice_ID
     * @param currencyId target C_Currency_ID
     * @param multiplierAP multiplier for AP/AR adjustment (1 or -1)
     * @param dateAcct cutoff date for allocations
     * @param trxName transaction name
     * @return paid amount rounded to currency precision
     */
    public static BigDecimal invoicePaidToDate(int invoiceId, int currencyId,
                                                @Nullable BigDecimal multiplierAP,
                                                @Nullable Timestamp dateAcct, String trxName) {
        return ShadowExecutor.execute(
            "invoicePaidToDate",
            new Object[] { invoiceId, currencyId, multiplierAP, dateAcct },
            () -> calculateInvoicePaidToDateJava(invoiceId, currencyId, multiplierAP, dateAcct, trxName),
            () -> SqlFunctionCaller.callInvoicePaidToDate(invoiceId, currencyId, multiplierAP, dateAcct),
            (java, sql) -> {
                if (java == null && sql == null) return true;
                if (java == null || sql == null) return false;
                return java.subtract(sql).abs().compareTo(TOLERANCE) <= 0;
            }
        );
    }

    private static BigDecimal calculateInvoicePaidToDateJava(int invoiceId, int currencyId,
                                                              @Nullable BigDecimal multiplierAP,
                                                              @Nullable Timestamp dateAcct, String trxName) {
        BigDecimal mult = multiplierAP != null ? multiplierAP : BigDecimal.ONE;
        BigDecimal paymentAmt = BigDecimal.ZERO;

        String sql = "SELECT a.AD_Client_ID, a.AD_Org_ID, "
            + "al.Amount, al.DiscountAmt, al.WriteOffAmt, "
            + "a.C_Currency_ID, a.DateTrx "
            + "FROM C_AllocationLine al "
            + "INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID) "
            + "WHERE al.C_Invoice_ID=? "
            + "AND a.DocStatus IN ('CO','CL') "
            + "AND a.DateAcct <= ?";

        try (PreparedStatement pstmt = DB.prepareStatement(sql, trxName)) {
            pstmt.setInt(1, invoiceId);
            pstmt.setTimestamp(2, dateAcct);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int adClientId = rs.getInt("AD_Client_ID");
                    int adOrgId = rs.getInt("AD_Org_ID");
                    BigDecimal amount = rs.getBigDecimal("Amount");
                    if (amount == null) amount = BigDecimal.ZERO;
                    BigDecimal discountAmt = rs.getBigDecimal("DiscountAmt");
                    if (discountAmt == null) discountAmt = BigDecimal.ZERO;
                    BigDecimal writeOffAmt = rs.getBigDecimal("WriteOffAmt");
                    if (writeOffAmt == null) writeOffAmt = BigDecimal.ZERO;
                    int allocCurrencyId = rs.getInt("C_Currency_ID");
                    Timestamp dateTrx = rs.getTimestamp("DateTrx");

                    BigDecimal total = amount.add(discountAmt).add(writeOffAmt);
                    BigDecimal converted = CurrencyFunctions.currencyConvert(
                        total, allocCurrencyId, currencyId,
                        dateTrx, null, adClientId, adOrgId);

                    if (converted != null) {
                        paymentAmt = paymentAmt.add(converted);
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "calculateInvoicePaidToDateJava", e);
        }

        MCurrency currency = MCurrency.get(Env.getCtx(), currencyId);
        int precision = currency != null ? currency.getStdPrecision() : 2;
        return paymentAmt.multiply(mult).setScale(precision, RoundingMode.HALF_UP);
    }
}
