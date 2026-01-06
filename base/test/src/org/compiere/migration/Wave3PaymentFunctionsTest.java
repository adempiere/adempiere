package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MPayment;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave3PaymentFunctionsTest extends CommonGWSetup {

    private MPayment testPayment;

    @BeforeAll
    void loadTestData() {
        // Find a completed payment with allocations
        testPayment = new Query(Env.getCtx(), MPayment.Table_Name,
            "DocStatus IN ('CO','CL') AND IsAllocated='Y'", null)
            .setOnlyActiveRecords(true)
            .first();
        assumeTrue(testPayment != null, "Need allocated payment for test");
    }

    @Test
    void getAllocatedAmt_matchesSql() {
        int paymentId = testPayment.getC_Payment_ID();
        int currencyId = testPayment.getC_Currency_ID();

        BigDecimal javaResult = testPayment.getAllocatedAmt();
        BigDecimal sqlResult = SqlFunctionCaller.callPaymentAllocated(paymentId, currencyId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("paymentAllocated(%d, %d): java=%s, sql=%s",
                paymentId, currencyId, javaResult, sqlResult));
    }
}
