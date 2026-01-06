package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MInvoice;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.InvoiceFunctions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave3InvoiceFunctionsTest extends CommonGWSetup {

    private MInvoice testInvoice;

    @BeforeAll
    void loadTestData() {
        testInvoice = new Query(Env.getCtx(), MInvoice.Table_Name,
            "DocStatus IN ('CO','CL') AND IsPaid='Y'", null)
            .setOnlyActiveRecords(true)
            .first();
        assumeTrue(testInvoice != null, "Need paid invoice for test");
    }

    @Test
    void invoicePaid_matchesSql() {
        int invoiceId = testInvoice.getC_Invoice_ID();
        int currencyId = testInvoice.getC_Currency_ID();
        BigDecimal multiplierAP = testInvoice.isSOTrx() ? BigDecimal.ONE : BigDecimal.ONE.negate();

        BigDecimal javaResult = InvoiceFunctions.invoicePaid(invoiceId, currencyId, multiplierAP, null);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoicePaid(invoiceId, currencyId, multiplierAP);

        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("invoicePaid(%d, %d, %s): java=%s, sql=%s",
                invoiceId, currencyId, multiplierAP, javaResult, sqlResult));
    }
}
