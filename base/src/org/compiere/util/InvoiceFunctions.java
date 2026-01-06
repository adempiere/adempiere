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
            log.log(Level.SEVERE, "calculateInvoicePaidJava - returning null due to error", e);
            return null;
        }

        // Get currency precision and apply rounding before multiplier (matches SQL behavior)
        MCurrency currency = MCurrency.get(Env.getCtx(), currencyId);
        int precision = currency != null ? currency.getStdPrecision() : 2;
        return paymentAmt.setScale(precision, RoundingMode.HALF_UP).multiply(mult);
    }

    /**
     * Calculate paid amount for invoice as of a specific date.
     * Equivalent to SQL function invoicePaidToDate(p_C_Invoice_ID, p_C_Currency_ID, p_MultiplierAP, p_DateAcct).
     *
     * @param invoiceId C_Invoice_ID
     * @param currencyId target C_Currency_ID
     * @param multiplierAP multiplier for AP/AR adjustment (1 or -1)
     * @param dateAcct cutoff date for allocations (if null, treated as current date)
     * @param trxName transaction name
     * @return paid amount rounded to currency precision
     */
    public static BigDecimal invoicePaidToDate(int invoiceId, int currencyId,
                                                @Nullable BigDecimal multiplierAP,
                                                Timestamp dateAcct, String trxName) {
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
                                                              Timestamp dateAcct, String trxName) {
        BigDecimal mult = multiplierAP != null ? multiplierAP : BigDecimal.ONE;
        BigDecimal paymentAmt = BigDecimal.ZERO;

        // Handle null dateAcct by treating as current date
        Timestamp cutoffDate = dateAcct != null ? dateAcct : new Timestamp(System.currentTimeMillis());

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
            pstmt.setTimestamp(2, cutoffDate);
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
            log.log(Level.SEVERE, "calculateInvoicePaidToDateJava - returning null due to error", e);
            return null;
        }

        // Get currency precision and apply rounding before multiplier (matches SQL behavior)
        MCurrency currency = MCurrency.get(Env.getCtx(), currencyId);
        int precision = currency != null ? currency.getStdPrecision() : 2;
        return paymentAmt.setScale(precision, RoundingMode.HALF_UP).multiply(mult);
    }

    /**
     * Calculate open/unpaid amount for invoice in invoice currency.
     * Equivalent to SQL function invoiceOpen(p_C_Invoice_ID, p_C_InvoicePaySchedule_ID).
     *
     * @param invoiceId C_Invoice_ID
     * @param invoicePayScheduleId C_InvoicePaySchedule_ID (null for whole invoice)
     * @return open amount in invoice currency
     */
    public static BigDecimal invoiceOpen(int invoiceId, @Nullable Integer invoicePayScheduleId) {
        return invoiceOpen(invoiceId, invoicePayScheduleId, null);
    }

    /**
     * Calculate open/unpaid amount for invoice in invoice currency.
     * Equivalent to SQL function invoiceOpen(p_C_Invoice_ID, p_C_InvoicePaySchedule_ID).
     *
     * @param invoiceId C_Invoice_ID
     * @param invoicePayScheduleId C_InvoicePaySchedule_ID (null for whole invoice)
     * @param trxName transaction name
     * @return open amount in invoice currency
     */
    public static BigDecimal invoiceOpen(int invoiceId, @Nullable Integer invoicePayScheduleId, String trxName) {
        return ShadowExecutor.execute(
            "invoiceOpen",
            new Object[] { invoiceId, invoicePayScheduleId },
            () -> calculateInvoiceOpenJava(invoiceId, invoicePayScheduleId, trxName),
            () -> SqlFunctionCaller.callInvoiceOpen(invoiceId, invoicePayScheduleId),
            (java, sql) -> {
                if (java == null && sql == null) return true;
                if (java == null || sql == null) return false;
                return java.subtract(sql).abs().compareTo(TOLERANCE) <= 0;
            }
        );
    }

    private static BigDecimal calculateInvoiceOpenJava(int invoiceId, @Nullable Integer invoicePayScheduleId, String trxName) {
        // Get invoice header data from C_Invoice_v
        int currencyId;
        BigDecimal grandTotal;
        BigDecimal multiplierAP;
        BigDecimal multiplierCM;

        String headerSql = "SELECT C_Currency_ID, GrandTotal, MultiplierAP, Multiplier "
            + "FROM C_Invoice_v "
            + "WHERE C_Invoice_ID=?";

        try (PreparedStatement pstmt = DB.prepareStatement(headerSql, trxName)) {
            pstmt.setInt(1, invoiceId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) {
                    log.log(Level.WARNING, "calculateInvoiceOpenJava - invoice not found or in draft: " + invoiceId);
                    return null;
                }
                currencyId = rs.getInt("C_Currency_ID");
                grandTotal = rs.getBigDecimal("GrandTotal");
                if (grandTotal == null) grandTotal = BigDecimal.ZERO;
                multiplierAP = rs.getBigDecimal("MultiplierAP");
                if (multiplierAP == null) multiplierAP = BigDecimal.ZERO;
                multiplierCM = rs.getBigDecimal("Multiplier");
                if (multiplierCM == null) multiplierCM = BigDecimal.ZERO;
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "calculateInvoiceOpenJava - error getting invoice header", e);
            return null;
        }

        // Get currency precision
        MCurrency currency = MCurrency.get(Env.getCtx(), currencyId);
        int precision = currency != null ? currency.getStdPrecision() : 2;
        BigDecimal minAmt = BigDecimal.ONE.divide(BigDecimal.TEN.pow(precision), precision, RoundingMode.HALF_UP);

        // Calculate paid amount (same logic as invoicePaid, but in invoice currency)
        BigDecimal paidAmt = BigDecimal.ZERO;
        String allocSql = "SELECT a.AD_Client_ID, a.AD_Org_ID, "
            + "al.Amount, al.DiscountAmt, al.WriteOffAmt, "
            + "a.C_Currency_ID, a.DateTrx "
            + "FROM C_AllocationLine al "
            + "INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID) "
            + "WHERE al.C_Invoice_ID=? "
            + "AND a.DocStatus IN ('CO','CL')";

        try (PreparedStatement pstmt = DB.prepareStatement(allocSql, trxName)) {
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
                        total.multiply(multiplierAP), allocCurrencyId, currencyId,
                        dateTrx, null, adClientId, adOrgId);

                    if (converted != null) {
                        paidAmt = paidAmt.add(converted);
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "calculateInvoiceOpenJava - error calculating paid amount", e);
            return null;
        }

        BigDecimal totalOpenAmt;

        // Handle payment schedule logic
        if (invoicePayScheduleId != null && invoicePayScheduleId > 0) {
            // Iterate through payment schedules in DueDate order
            BigDecimal remaining = paidAmt;
            totalOpenAmt = BigDecimal.ZERO;

            String scheduleSql = "SELECT C_InvoicePaySchedule_ID, DueAmt "
                + "FROM C_InvoicePaySchedule "
                + "WHERE C_Invoice_ID=? AND IsValid='Y' "
                + "ORDER BY DueDate";

            try (PreparedStatement pstmt = DB.prepareStatement(scheduleSql, trxName)) {
                pstmt.setInt(1, invoiceId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int scheduleId = rs.getInt("C_InvoicePaySchedule_ID");
                        BigDecimal dueAmt = rs.getBigDecimal("DueAmt");
                        if (dueAmt == null) dueAmt = BigDecimal.ZERO;

                        if (scheduleId == invoicePayScheduleId) {
                            // Target schedule: return (DueAmt * MultiplierCM) - remaining
                            totalOpenAmt = dueAmt.multiply(multiplierCM).subtract(remaining);
                            // Ensure non-negative (matches SQL logic at lines 93-95)
                            if (dueAmt.subtract(remaining).compareTo(BigDecimal.ZERO) < 0) {
                                totalOpenAmt = BigDecimal.ZERO;
                            }
                            break;
                        } else {
                            // Earlier schedule: subtract from remaining
                            remaining = remaining.subtract(dueAmt);
                            if (remaining.compareTo(BigDecimal.ZERO) < 0) {
                                remaining = BigDecimal.ZERO;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.log(Level.SEVERE, "calculateInvoiceOpenJava - error processing payment schedule", e);
                return null;
            }
        } else {
            // No payment schedule: simple calculation
            totalOpenAmt = grandTotal.subtract(paidAmt);
        }

        // Ignore rounding (amounts < minAmt become zero)
        if (totalOpenAmt.compareTo(minAmt.negate()) > 0 && totalOpenAmt.compareTo(minAmt) < 0) {
            totalOpenAmt = BigDecimal.ZERO;
        }

        // Round to currency precision
        return totalOpenAmt.setScale(precision, RoundingMode.HALF_UP);
    }

    /**
     * Calculate open/unpaid amount for invoice as of a specific date.
     * Equivalent to SQL function invoiceOpenToDate(p_C_Invoice_ID, p_C_InvoicePaySchedule_ID, p_DateAcct).
     *
     * @param invoiceId C_Invoice_ID
     * @param invoicePayScheduleId C_InvoicePaySchedule_ID (null for whole invoice)
     * @param dateAcct cutoff date for allocations (if null, treated as current date)
     * @return open amount in invoice currency
     */
    public static BigDecimal invoiceOpenToDate(int invoiceId, @Nullable Integer invoicePayScheduleId,
                                                @Nullable Timestamp dateAcct) {
        return invoiceOpenToDate(invoiceId, invoicePayScheduleId, dateAcct, null);
    }

    /**
     * Calculate open/unpaid amount for invoice as of a specific date.
     * Equivalent to SQL function invoiceOpenToDate(p_C_Invoice_ID, p_C_InvoicePaySchedule_ID, p_DateAcct).
     *
     * @param invoiceId C_Invoice_ID
     * @param invoicePayScheduleId C_InvoicePaySchedule_ID (null for whole invoice)
     * @param dateAcct cutoff date for allocations (if null, treated as current date)
     * @param trxName transaction name
     * @return open amount in invoice currency
     */
    public static BigDecimal invoiceOpenToDate(int invoiceId, @Nullable Integer invoicePayScheduleId,
                                                @Nullable Timestamp dateAcct, String trxName) {
        return ShadowExecutor.execute(
            "invoiceOpenToDate",
            new Object[] { invoiceId, invoicePayScheduleId, dateAcct },
            () -> calculateInvoiceOpenToDateJava(invoiceId, invoicePayScheduleId, dateAcct, trxName),
            () -> SqlFunctionCaller.callInvoiceOpenToDate(invoiceId, invoicePayScheduleId, dateAcct),
            (java, sql) -> {
                if (java == null && sql == null) return true;
                if (java == null || sql == null) return false;
                return java.subtract(sql).abs().compareTo(TOLERANCE) <= 0;
            }
        );
    }

    private static BigDecimal calculateInvoiceOpenToDateJava(int invoiceId, @Nullable Integer invoicePayScheduleId,
                                                              @Nullable Timestamp dateAcct, String trxName) {
        // Get invoice header data from C_Invoice_v
        int currencyId;
        BigDecimal grandTotal;
        BigDecimal multiplierAP;
        BigDecimal multiplierCM;

        String headerSql = "SELECT C_Currency_ID, GrandTotal, MultiplierAP, Multiplier "
            + "FROM C_Invoice_v "
            + "WHERE C_Invoice_ID=?";

        try (PreparedStatement pstmt = DB.prepareStatement(headerSql, trxName)) {
            pstmt.setInt(1, invoiceId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) {
                    log.log(Level.WARNING, "calculateInvoiceOpenToDateJava - invoice not found or in draft: " + invoiceId);
                    return null;
                }
                currencyId = rs.getInt("C_Currency_ID");
                grandTotal = rs.getBigDecimal("GrandTotal");
                if (grandTotal == null) grandTotal = BigDecimal.ZERO;
                multiplierAP = rs.getBigDecimal("MultiplierAP");
                if (multiplierAP == null) multiplierAP = BigDecimal.ZERO;
                multiplierCM = rs.getBigDecimal("Multiplier");
                if (multiplierCM == null) multiplierCM = BigDecimal.ZERO;
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "calculateInvoiceOpenToDateJava - error getting invoice header", e);
            return null;
        }

        // Get currency precision
        MCurrency currency = MCurrency.get(Env.getCtx(), currencyId);
        int precision = currency != null ? currency.getStdPrecision() : 2;
        BigDecimal minAmt = BigDecimal.ONE.divide(BigDecimal.TEN.pow(precision), precision, RoundingMode.HALF_UP);

        // Handle null dateAcct by treating as current date
        Timestamp cutoffDate = dateAcct != null ? dateAcct : new Timestamp(System.currentTimeMillis());

        // Calculate paid amount (same logic as invoicePaid, but in invoice currency and with date filter)
        BigDecimal paidAmt = BigDecimal.ZERO;
        String allocSql = "SELECT a.AD_Client_ID, a.AD_Org_ID, "
            + "al.Amount, al.DiscountAmt, al.WriteOffAmt, "
            + "a.C_Currency_ID, a.DateTrx "
            + "FROM C_AllocationLine al "
            + "INNER JOIN C_AllocationHdr a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID) "
            + "WHERE al.C_Invoice_ID=? "
            + "AND a.DocStatus IN ('CO','CL') "
            + "AND a.DateAcct <= ?";

        try (PreparedStatement pstmt = DB.prepareStatement(allocSql, trxName)) {
            pstmt.setInt(1, invoiceId);
            pstmt.setTimestamp(2, cutoffDate);
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
                        total.multiply(multiplierAP), allocCurrencyId, currencyId,
                        dateTrx, null, adClientId, adOrgId);

                    if (converted != null) {
                        paidAmt = paidAmt.add(converted);
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "calculateInvoiceOpenToDateJava - error calculating paid amount", e);
            return null;
        }

        BigDecimal totalOpenAmt;

        // Handle payment schedule logic
        if (invoicePayScheduleId != null && invoicePayScheduleId > 0) {
            // Iterate through payment schedules in DueDate order
            BigDecimal remaining = paidAmt;
            totalOpenAmt = BigDecimal.ZERO;

            String scheduleSql = "SELECT C_InvoicePaySchedule_ID, DueAmt "
                + "FROM C_InvoicePaySchedule "
                + "WHERE C_Invoice_ID=? AND IsValid='Y' "
                + "ORDER BY DueDate";

            try (PreparedStatement pstmt = DB.prepareStatement(scheduleSql, trxName)) {
                pstmt.setInt(1, invoiceId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        int scheduleId = rs.getInt("C_InvoicePaySchedule_ID");
                        BigDecimal dueAmt = rs.getBigDecimal("DueAmt");
                        if (dueAmt == null) dueAmt = BigDecimal.ZERO;

                        if (scheduleId == invoicePayScheduleId) {
                            // Target schedule: return (DueAmt * MultiplierCM) - remaining
                            totalOpenAmt = dueAmt.multiply(multiplierCM).subtract(remaining);
                            // Ensure non-negative (matches SQL logic at lines 93-95)
                            if (dueAmt.subtract(remaining).compareTo(BigDecimal.ZERO) < 0) {
                                totalOpenAmt = BigDecimal.ZERO;
                            }
                            break;
                        } else {
                            // Earlier schedule: subtract from remaining
                            remaining = remaining.subtract(dueAmt);
                            if (remaining.compareTo(BigDecimal.ZERO) < 0) {
                                remaining = BigDecimal.ZERO;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.log(Level.SEVERE, "calculateInvoiceOpenToDateJava - error processing payment schedule", e);
                return null;
            }
        } else {
            // No payment schedule: simple calculation
            totalOpenAmt = grandTotal.subtract(paidAmt);
        }

        // Ignore rounding (amounts < minAmt become zero)
        if (totalOpenAmt.compareTo(minAmt.negate()) > 0 && totalOpenAmt.compareTo(minAmt) < 0) {
            totalOpenAmt = BigDecimal.ZERO;
        }

        // Round to currency precision
        return totalOpenAmt.setScale(precision, RoundingMode.HALF_UP);
    }
}
