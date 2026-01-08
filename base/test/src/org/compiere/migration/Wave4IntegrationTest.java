package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.DB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Integration tests for Wave 4 functions.
 * Validates that Java implementations match PostgreSQL function outputs for real database records.
 *
 * <p><b>Test Data Requirements:</b>
 * <ul>
 *   <li>At least one active C_ElementValue record for acctBalance tests</li>
 *   <li>At least one active C_InvoiceLine record for linenetamtrealinvoiceline tests</li>
 *   <li>At least one active C_OrderLine record for linenetamtrealorderline tests</li>
 *   <li>At least one M_AttributeSetInstance with Lot or SerNo for productAttribute tests</li>
 * </ul>
 *
 * <p>Tests dynamically discover valid IDs from the database and skip if none are found.
 */
@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave4IntegrationTest extends CommonGWSetup {

    // Dynamically discovered test IDs
    private int[] testAccountIds;
    private int[] testInvoiceLineIds;
    private int[] testOrderLineIds;
    private int[] testAttributeSetInstanceIds;

    @BeforeAll
    void loadTestData() {
        // Dynamically discover valid test IDs from database
        testAccountIds = queryExistingIds(
            "SELECT C_ElementValue_ID FROM C_ElementValue WHERE IsActive='Y' FETCH FIRST 5 ROWS ONLY");
        testInvoiceLineIds = queryExistingIds(
            "SELECT C_InvoiceLine_ID FROM C_InvoiceLine WHERE IsActive='Y' FETCH FIRST 5 ROWS ONLY");
        testOrderLineIds = queryExistingIds(
            "SELECT C_OrderLine_ID FROM C_OrderLine WHERE IsActive='Y' FETCH FIRST 5 ROWS ONLY");
        testAttributeSetInstanceIds = queryExistingIds(
            "SELECT M_AttributeSetInstance_ID FROM M_AttributeSetInstance " +
            "WHERE (Lot IS NOT NULL OR SerNo IS NOT NULL) FETCH FIRST 10 ROWS ONLY");
    }

    private int[] queryExistingIds(String sql) {
        List<Integer> ids = new ArrayList<>();
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
        } catch (Exception e) {
            // Return empty array if query fails
        }
        return ids.stream().mapToInt(Integer::intValue).toArray();
    }

    Stream<Integer> testAccountIdProvider() {
        assumeTrue(testAccountIds != null && testAccountIds.length > 0,
            "No test accounts found - skipping tests");
        return java.util.Arrays.stream(testAccountIds).boxed();
    }

    Stream<Integer> testInvoiceLineIdProvider() {
        assumeTrue(testInvoiceLineIds != null && testInvoiceLineIds.length > 0,
            "No test invoice lines found - skipping tests");
        return java.util.Arrays.stream(testInvoiceLineIds).boxed();
    }

    Stream<Integer> testOrderLineIdProvider() {
        assumeTrue(testOrderLineIds != null && testOrderLineIds.length > 0,
            "No test order lines found - skipping tests");
        return java.util.Arrays.stream(testOrderLineIds).boxed();
    }

    Stream<Integer> testAttributeSetInstanceIdProvider() {
        assumeTrue(testAttributeSetInstanceIds != null && testAttributeSetInstanceIds.length > 0,
            "No test attribute set instances found - skipping tests");
        return java.util.Arrays.stream(testAttributeSetInstanceIds).boxed();
    }

    @ParameterizedTest
    @MethodSource("testAccountIdProvider")
    void acctBalance_javaMatchesSql(int accountId) {
        BigDecimal amtDr = new BigDecimal("100.00");
        BigDecimal amtCr = new BigDecimal("30.00");

        BigDecimal javaResult = Wave4Functions.acctBalance(accountId, amtDr, amtCr);
        BigDecimal sqlResult = SqlFunctionCaller.callAcctBalance(accountId, amtDr, amtCr);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> String.format("Mismatch for account %d: java=%s, sql=%s",
                accountId, javaResult, sqlResult));
    }

    @Test
    void getSysconfig_javaMatchesSql() {
        String name = "SYSTEM_NATIVE_SEQUENCE";
        String defaultValue = "N";
        int clientId = 0;
        int orgId = 0;

        String javaResult = Wave4Functions.getSysconfig(name, defaultValue, clientId, orgId);
        String sqlResult = SqlFunctionCaller.callGetSysconfig(name, defaultValue, clientId, orgId);

        assertEquals(sqlResult, javaResult,
            () -> String.format("getSysconfig mismatch for '%s': java='%s', sql='%s'",
                name, javaResult, sqlResult));
    }

    @ParameterizedTest
    @MethodSource("testInvoiceLineIdProvider")
    void linenetamtrealinvoiceline_javaMatchesSql(int invoiceLineId) {
        BigDecimal javaResult = Wave4Functions.linenetamtrealinvoiceline(invoiceLineId);
        BigDecimal sqlResult = SqlFunctionCaller.callLinenetamtrealinvoiceline(invoiceLineId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> String.format("Mismatch for invoice line %d: java=%s, sql=%s",
                invoiceLineId, javaResult, sqlResult));
    }

    @ParameterizedTest
    @MethodSource("testOrderLineIdProvider")
    void linenetamtrealorderline_javaMatchesSql(int orderLineId) {
        BigDecimal javaResult = Wave4Functions.linenetamtrealorderline(orderLineId);
        BigDecimal sqlResult = SqlFunctionCaller.callLinenetamtrealorderline(orderLineId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> String.format("Mismatch for order line %d: java=%s, sql=%s",
                orderLineId, javaResult, sqlResult));
    }

    @ParameterizedTest
    @MethodSource("testAttributeSetInstanceIdProvider")
    void productAttribute_javaMatchesSql(int attributeSetInstanceId) {
        String javaResult = Wave4Functions.productAttribute(attributeSetInstanceId);
        String sqlResult = SqlFunctionCaller.callProductAttribute(attributeSetInstanceId);

        assertEquals(sqlResult, javaResult,
            () -> String.format("Mismatch for ASI %d: java='%s', sql='%s'",
                attributeSetInstanceId, javaResult, sqlResult));
    }

    @Test
    void documentNo_invalidId_javaMatchesSql() {
        String javaResult = Wave4Functions.documentNo(-1);
        String sqlResult = SqlFunctionCaller.callDocumentNo(-1);
        assertEquals(sqlResult, javaResult,
            () -> String.format("documentNo mismatch for id=-1: java='%s', sql='%s'",
                javaResult, sqlResult));
    }

    @Test
    void maxpaydate_invalidId_javaMatchesSql() {
        Timestamp javaResult = Wave4Functions.maxpaydate(-1);
        Timestamp sqlResult = SqlFunctionCaller.callMaxpaydate(-1);
        assertEquals(sqlResult, javaResult,
            () -> String.format("maxpaydate mismatch for id=-1: java=%s, sql=%s",
                javaResult, sqlResult));
    }
}
