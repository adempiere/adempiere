package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;

import org.adempiere.test.CommonGWSetup;
import org.compiere.migration.SqlFunctionCaller;
import org.compiere.model.MPaymentTerm;
import org.compiere.model.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

/**
 * Integration tests comparing Java and SQL implementations.
 * Requires database connection - run with -DrunIntegrationTests=true
 *
 * <p>Payment term IDs are queried dynamically to work with any test database.
 * Tests are skipped if required payment term types are not found.
 *
 * <p>Test dates use fixed 2026 values (Wednesday 2026-01-07, etc.) which
 * remain valid calendar dates regardless of when tests are run.
 */
@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnabledIfSystemProperty(named = "runIntegrationTests", matches = "true")
public class PaymentTermFunctionsIntegrationTest extends CommonGWSetup {

    private static int netDaysPaymentTermId;
    private static int fixedDueDatePaymentTermId;
    private static int clientId;

    @BeforeAll
    void loadTestData() {
        // Query for a net-days payment term (IsDueFixed = 'N')
        MPaymentTerm netDaysTerm = new Query(Env.getCtx(), MPaymentTerm.Table_Name,
            "IsDueFixed = 'N' AND IsActive = 'Y'", null)
            .first();
        netDaysPaymentTermId = (netDaysTerm != null) ? netDaysTerm.get_ID() : 0;

        // Query for a fixed due date payment term (IsDueFixed = 'Y')
        MPaymentTerm fixedTerm = new Query(Env.getCtx(), MPaymentTerm.Table_Name,
            "IsDueFixed = 'Y' AND IsActive = 'Y'", null)
            .first();
        fixedDueDatePaymentTermId = (fixedTerm != null) ? fixedTerm.get_ID() : 0;

        // Get client ID from the net-days term, or default to 11 (GardenWorld)
        clientId = (netDaysTerm != null) ? netDaysTerm.getAD_Client_ID() : 11;
    }

    @Test
    void nextBusinessDay_javaMatchesSql() {
        // Wednesday 2026-01-07 - fixed calendar date
        Timestamp wednesday = Timestamp.valueOf("2026-01-07 10:00:00");

        Timestamp javaResult = PaymentTermFunctions.nextBusinessDay(wednesday, clientId);
        Timestamp sqlResult = SqlFunctionCaller.callNextBusinessDay(wednesday, clientId);

        assertNotNull(javaResult, "Java result should not be null");
        assertNotNull(sqlResult, "SQL result should not be null");

        LocalDate javaDate = javaResult.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate sqlDate = sqlResult.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        assertEquals(sqlDate, javaDate, "Next business days should match");
    }

    @Test
    void paymentTermDueDate_javaMatchesSql() {
        assumeTrue(netDaysPaymentTermId > 0,
            "Skipping: no net-days payment term found in database");

        Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");

        Timestamp javaResult = PaymentTermFunctions.paymentTermDueDate(netDaysPaymentTermId, docDate);
        Timestamp sqlResult = SqlFunctionCaller.callPaymentTermDueDate(netDaysPaymentTermId, docDate);

        assertNotNull(javaResult, "Java result should not be null");
        assertNotNull(sqlResult, "SQL result should not be null");

        // Compare dates (ignore time)
        LocalDate javaDate = javaResult.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate sqlDate = sqlResult.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        assertEquals(sqlDate, javaDate, "Due dates should match for paymentTermId=" + netDaysPaymentTermId);
    }

    @Test
    void paymentTermDueDays_javaMatchesSql() {
        assumeTrue(netDaysPaymentTermId > 0,
            "Skipping: no net-days payment term found in database");

        Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
        Timestamp payDate = Timestamp.valueOf("2026-02-15 00:00:00");

        int javaResult = PaymentTermFunctions.paymentTermDueDays(netDaysPaymentTermId, docDate, payDate);
        int sqlResult = SqlFunctionCaller.callPaymentTermDueDays(netDaysPaymentTermId, docDate, payDate);

        assertEquals(sqlResult, javaResult, "Due days should match for paymentTermId=" + netDaysPaymentTermId);
    }

    @Test
    void paymentTermDiscount_javaMatchesSql() {
        assumeTrue(netDaysPaymentTermId > 0,
            "Skipping: no net-days payment term found in database");

        BigDecimal amount = new BigDecimal("1000.00");
        int currencyId = 100; // USD typically
        Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
        Timestamp payDate = Timestamp.valueOf("2026-01-20 00:00:00");

        BigDecimal javaResult = PaymentTermFunctions.paymentTermDiscount(
            amount, currencyId, netDaysPaymentTermId, docDate, payDate);
        BigDecimal sqlResult = SqlFunctionCaller.callPaymentTermDiscount(
            amount, currencyId, netDaysPaymentTermId, docDate, payDate);

        assertEquals(0, javaResult.compareTo(sqlResult),
            "Discounts should match: Java=" + javaResult + ", SQL=" + sqlResult);
    }

    @Test
    void paymentTermDueDate_fixedDueDate_javaMatchesSql() {
        assumeTrue(fixedDueDatePaymentTermId > 0,
            "Skipping: no fixed due date payment term found in database");

        // Past typical cutoff to test the cutoff logic
        Timestamp docDate = Timestamp.valueOf("2026-01-22 00:00:00");

        Timestamp javaResult = PaymentTermFunctions.paymentTermDueDate(fixedDueDatePaymentTermId, docDate);
        Timestamp sqlResult = SqlFunctionCaller.callPaymentTermDueDate(fixedDueDatePaymentTermId, docDate);

        assertNotNull(javaResult, "Java result should not be null for fixed term");
        assertNotNull(sqlResult, "SQL result should not be null for fixed term");

        LocalDate javaDate = javaResult.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate sqlDate = sqlResult.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        assertEquals(sqlDate, javaDate, "Fixed due dates should match for paymentTermId=" + fixedDueDatePaymentTermId);
    }
}
