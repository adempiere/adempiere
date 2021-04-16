package org.compiere.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;

import org.adempiere.test.CommonGWData;
import org.adempiere.test.CommonSystemSetup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model")
@Tag("MPeriod")
@DisplayName("Given the MPeriod class and current database")
class IT_MPeriod extends CommonSystemSetup {

    @Test
    final void getMinPeriodStartDate_shouldReturnNonNullResult() {

        Timestamp min = MPeriod.getMinPeriodStartDate(ctx,
                CommonGWData.AD_CLIENT_ID, 0, trxName);
        assertNotNull(min);

    }

    @Test
    final void getMaxPeriodEndDate_shouldReturnNotNullResult() {

        Timestamp max = MPeriod.getMaxPeriodEndDate(ctx,
                CommonGWData.AD_CLIENT_ID, 0, trxName);
        assertNotNull(max);

    }

}
