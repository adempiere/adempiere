package org.compiere.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model")
@Tag("MPeriod")
@DisplayName("Given the MPeriod class and current database")
class IT_MPeriod extends CommonGWSetup {

    @Test
    final void getMinPeriodStartDate_shouldReturnNonNullResult() {

        Timestamp min = MPeriod.getMinPeriodStartDate(ctx, trxName);
        assertNotNull(min);

    }

    @Test
    final void getMaxPeriodEndDate_shouldReturnNotNullResult() {

        Timestamp max = MPeriod.getMaxPeriodEndDate(ctx, trxName);
        assertNotNull(max);

    }

}
