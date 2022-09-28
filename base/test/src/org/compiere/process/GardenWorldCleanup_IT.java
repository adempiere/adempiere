/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2021 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.compiere.process;

import static org.compiere.process.GardenWorldCleanup.CLIENT_NOT_FOUND_MSG;
import static org.compiere.process.GardenWorldCleanup.NO_CHANGES_MSG;
import static org.compiere.process.GardenWorldCleanup.SUCCESS_MSG;
import static org.compiere.util.Msg.wrapMsg;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import java.util.stream.Stream;

import org.adempiere.core.domains.models.I_M_PriceList_Version;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonGWData;
import org.adempiere.test.CommonSystemSetup;
import org.compiere.acct.Doc;
import org.compiere.model.MBankStatement;
import org.compiere.model.MDiscountSchema;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("GardenWorldCleanup")
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
@DisplayName("IT_GardenWorldCleanup: Given the GardenWorldCleanup process and "
        + "the common GardenWorld setup with the current database")
class GardenWorldCleanup_IT extends CommonSystemSetup {

    private GardenWorldCleanup uut;
    private Timestamp currentDate = TimeUtil.getDay(2021, 07, 01);
    private Timestamp minDate;
    private Timestamp maxDate;
    private Properties gwContext;
    private static CLogger log =
            CLogger.getCLogger(GardenWorldCleanup_IT.class);

    static Stream<Arguments> givenTargetAndActualYearAndExpectedOffset() {

        return Stream.of(
                arguments(2018, 2008, 10),
                arguments(2018, 2017, 1),
                arguments(2018, 2018, 0),
                arguments(2018, 2019, -1),
                arguments(2018, 2028, -10));

    }

    private void createGardenWorldContext() {

        int AD_USER_ID = CommonGWData.AD_USER_ID;
        int AD_CLIENT_ID = CommonGWData.AD_CLIENT_ID;
        int AD_ORG_ID = CommonGWData.AD_ORG_ID;

        gwContext = Env.getCtx();
        gwContext.setProperty("#AD_Org_ID", Integer.toString(AD_ORG_ID));
        gwContext.setProperty("#AD_User_ID", Integer.toString(AD_USER_ID));
        gwContext.setProperty("#AD_Client_ID", Integer.toString(AD_CLIENT_ID));
        gwContext.setProperty("#Date",
                TimeUtil.getDay(System.currentTimeMillis()).toString());
        gwContext.setProperty("#AD_Language", "en");

    }

    private void verifyCalendarOverlapsMinMaxDates() {

        setDateLimits();
        assertTrue(minDate.before(uut.getMinPeriodStartDate()));
        assertTrue(maxDate.after(uut.getMinPeriodStartDate()));
        assertTrue(maxDate.before(uut.getMaxPeriodEndDate()));

    }

    private void setDateLimits() {

        Stream.of(Doc.getDocumentsTableName())
                .forEach(tableName -> findDateLimitsForTable(tableName,
                        Doc.getDateAcctColumnName(tableName)));

    }

    private void findDateLimitsForTable(String tableName, String columnName) {

        String sql = "SELECT MIN(" + columnName + "), MAX(" + columnName
                + ") from " + tableName
                + " WHERE AD_Client_ID=11 ";

        log.config("Looking at table/column "
                + tableName + "/" + columnName);
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = DB.prepareStatement(sql, get_TrxName());
            rs = pstm.executeQuery();
            while (rs.next()) {

                final Timestamp minDateValue = rs.getTimestamp(1);
                final Timestamp maxDateValue = rs.getTimestamp(2);

                if (minDate == null)
                    minDate = minDateValue;

                if (maxDate == null)
                    maxDate = maxDateValue;

                if (timestampIsNotNullOrZero(minDateValue)
                        && minDate.after(minDateValue)) {
                    minDate = minDateValue;
                }
                if (timestampIsNotNullOrZero(maxDateValue) &&
                        !maxDateValue.after(currentDate)
                        && maxDate.before(maxDateValue)) {
                    maxDate = maxDateValue;
                }
            }

        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getLocalizedMessage());
            throw new AdempiereException("Problem finding the date limits", e);
        } finally {
            DB.close(rs, pstm);
        }

    }

    private void shiftCalendarToThreeYearsAheadOfMinDate() {

        setDateLimits();
        Timestamp initialMinDate = minDate;
        Timestamp currentDate = TimeUtil.addYears(initialMinDate, 3);
        uut.setCurrentDate(currentDate);
        uut.determinePeriodOffset();
        uut.adjustCalendarYearAndPeriods();

    }

    private boolean timestampIsNotNullOrZero(Timestamp ts) {

        return ts != null && ts.getTime() != 0L;

    }

    @BeforeEach
    void localSetup() {

        createGardenWorldContext();

    }

    @ParameterizedTest
    @DisplayName("Then the message translations should exist")
    @ValueSource(
            strings = { SUCCESS_MSG, NO_CHANGES_MSG, CLIENT_NOT_FOUND_MSG })
    void checkTranslations(String msgKey) {

        assertEnEsMessageTranslationsExist(msgKey);

    }

    @Nested
    @DisplayName("When the GardenWorldCleanUp process has the transaction name")
    class GivenTransaction {

        @BeforeEach
        void localSetup() {

            uut = spy(GardenWorldCleanup.class);
            doReturn(trxName).when(uut).get_TrxName();

        }

        @Nested
        @DisplayName("When the GardenWorldCleanUp process has the context"
                + " and transaction name")
        class GivenContext {

            @BeforeEach
            void localSetup() {

                doReturn(ctx).when(uut).getCtx();

            }

            @Test
            @DisplayName("Then the doIt process runs successfully with the "
                    + "current data")
            final void doItReturnsSuccess() {

                String message = uut.doIt();
                assertTrue(wrapMsg(SUCCESS_MSG)
                        .equals(message)
                        || wrapMsg(NO_CHANGES_MSG).equals(message));

            }

            @Test
            @DisplayName("Then the earliest period start date can be found")
            final void findsTheEarliestPeriodStartDate() {

                Timestamp earliestDate = uut.getMinPeriodStartDate();
                assertNotNull(earliestDate);

            }

            @Test
            @DisplayName("Then bank statement names should match the statement date")
            void thenBankStatementNamesShouldMatchStatementDates() {

                MBankStatement statement =
                        new MBankStatement(gwContext, 0, trxName);
                statement.setName("SomeName");
                statement.setStatementDate(TimeUtil.getDay(2018, 10, 20));
                statement.setC_BankAccount_ID(
                        CommonGWData.MONEYBANK_BankAccount_ID);
                statement.saveEx();

                uut.cleanUp();

                statement.load(trxName);

                assertEquals("2018-10-20", statement.getName());

            }

            @Test
            @DisplayName("Then price list schema names should match the valid from date")
            void thenPriceListSchemaNamesShouldMatchValidFromDates() {

                MDiscountSchema schema =
                        new MDiscountSchema(gwContext, 0, trxName);
                schema.setName("SomeName 2003");
                schema.setValidFrom(TimeUtil.getDay(2018, 05, 01));
                schema.saveEx();

                uut.cleanUp();

                schema.load(trxName);

                assertEquals("SomeName 2018", schema.getName());

            }

            @Test
            @DisplayName("Then price list version names should match the valid from date")
            void thenPriceListVersionNamesShouldMatchValidFromDates() {

                uut.cleanUp();

                new Query(gwContext, I_M_PriceList_Version.Table_Name, null,
                        trxName)
                                .setClient_ID()
                                .list(MPriceListVersion.class)
                                .stream()
                                .forEach(plVersion -> {
                                    String name = plVersion.getName();
                                    String preName =
                                            name.substring(0,
                                                    name.indexOf(" "));
                                    int year = TimeUtil.getYearFromTimestamp(
                                            plVersion.getValidFrom());
                                    assertEquals(preName + " " + year, name);
                                });

            }

            @ParameterizedTest
            @ValueSource(ints = { -2, -1, 0, 1, 2 })
            @DisplayName("Then the GardenWorld calendar can be adjusted by a "
                    + "year offset")
            final void givenAnOffset_adjustsTheCalendarAndPeriods(int offset) {

                Timestamp originalEarliestDate = uut.getMinPeriodStartDate();
                uut.setYearOffSet(offset);

                uut.adjustCalendarYearAndPeriods();

                Timestamp adjustedEarliestDate = uut.getMinPeriodStartDate();

                assertEquals(offset,
                        TimeUtil.getYearsBetween(originalEarliestDate,
                                adjustedEarliestDate));

            }

            @Test
            @DisplayName("Then the GardenWold calendar covers "
                    + "the required date range")
            final void gardenWorldCalendarCoversNecessaryDatesRangeinYears() {

                setDateLimits();
                int dateYears = TimeUtil.getYearsBetween(minDate, maxDate);

                Timestamp minStart = uut.getMinPeriodStartDate();
                Timestamp maxEnd = uut.getMaxPeriodEndDate();
                int calendarYears = TimeUtil.getYearsBetween(minStart, maxEnd);

                assertTrue(calendarYears >= dateYears,
                        "The GardenWorld calendar "
                                + "does not have enough years to cover the required date "
                                + "range of the GardenWorld data.  Check the dates or add "
                                + "more years to the calendar");

            }

            @Test
            @DisplayName("Then the calendar can be shifted to partially overlap "
                    + "the record dates")
            final void whenCalendarIsShifted_AcctDateRangeOverlaps() {

                shiftCalendarToThreeYearsAheadOfMinDate();
                verifyCalendarOverlapsMinMaxDates();

            }

        }

    }

}
