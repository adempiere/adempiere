package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Tag("UnitTest")
class PaymentTermFunctionRouterTest {

    @Test
    void nextBusinessDay_withNullDate_returnsNull() {
        assertNull(PaymentTermFunctionRouter.nextBusinessDay(null, 0));
    }

    @Test
    void paymentTermDueDate_withNullInputs_returnsNull() {
        assertNull(PaymentTermFunctionRouter.paymentTermDueDate(null, null));
    }

    @Test
    void paymentTermDueDays_withZeroPaymentTermId_returnsZero() {
        Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
        assertEquals(0, PaymentTermFunctionRouter.paymentTermDueDays(0, docDate, null));
    }

    @Test
    void paymentTermDiscount_withNullAmount_returnsZero() {
        Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
        assertEquals(BigDecimal.ZERO, PaymentTermFunctionRouter.paymentTermDiscount(
            null, 100, 106, docDate, docDate));
    }
}
