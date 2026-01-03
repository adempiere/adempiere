package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.sql.Timestamp;
import java.time.LocalDate;

@Tag("UnitTest")
class PaymentTermFunctionsTest {

    @Test
    void addMonths_withNullDatetime_returnsNull() {
        assertNull(PaymentTermFunctions.addMonths(null, 1));
    }

    @Test
    void addMonths_withNullMonths_returnsNull() {
        Timestamp ts = Timestamp.valueOf("2026-01-15 10:30:00");
        assertNull(PaymentTermFunctions.addMonths(ts, null));
    }

    @ParameterizedTest
    @CsvSource({
        "2026-01-15, 1, 2026-02-15",
        "2026-01-31, 1, 2026-02-28",
        "2026-03-31, 1, 2026-04-30",
        "2026-01-15, -1, 2025-12-15",
        "2026-01-15, 12, 2027-01-15",
        "2026-01-15, 0, 2026-01-15"
    })
    void addMonths_withValidInputs_returnsExpectedDate(String input, int months, String expected) {
        Timestamp ts = Timestamp.valueOf(input + " 00:00:00");
        LocalDate result = PaymentTermFunctions.addMonths(ts, months);
        assertEquals(LocalDate.parse(expected), result);
    }
}
