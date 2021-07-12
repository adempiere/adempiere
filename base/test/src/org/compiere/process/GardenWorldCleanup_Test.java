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
import static org.compiere.util.Msg.wrapMsg;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.stream.Stream;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonUnitTestSetup;
import org.compiere.model.MClient;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.CPreparedStatement;
import org.compiere.util.TimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Tag("GardenWorldCleanup")
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
@DisplayName("TestGardenWorldCleanup: Given the GardenWorldCleanup process "
        + "and the Unit Test setup")
class GardenWorldCleanup_Test extends CommonUnitTestSetup {

    private GardenWorldCleanup uut;
    Timestamp currentDate = TimeUtil.getDay(2021, 07, 01);
    static CLogger logMock = mock(CLogger.class);

    private void setQueryToReturnClient(MClient client) {

        Query queryMock = mock(Query.class);
        doReturn(queryMock).when(queryMock).setParameters(11);
        doReturn(client).when(queryMock).first();
        doReturn(queryMock).when(uut).getQuery(any(),
                anyString(), anyString(), any());

    }

    private void setGardenWorldClientDoesNotExists() {

        setQueryToReturnClient(null);

    }

    private void setGardenWorldClientExists() {

        MClient clientMock = mock(MClient.class);
        setQueryToReturnClient(clientMock);

    }

    private static Stream<Arguments>
            givenTargetAndActualYearAndExpectedOffset() {

        return Stream.of(
                arguments(2018, 2008, 10),
                arguments(2018, 2017, 1),
                arguments(2018, 2018, 0),
                arguments(2018, 2019, -1),
                arguments(2018, 2028, -10));

    }

    @BeforeEach
    void localSetup() {

        uut = spy(GardenWorldCleanup.class);
        uut.log = logMock;

    }

    @Test
    @DisplayName("The abstract class getters and setters provide the expected data")
    final void testGettersAndSetters() {

        assertEquals(53733, GardenWorldCleanupAbstract.getProcessId());
        assertEquals("GardenWorldCleanup",
                GardenWorldCleanupAbstract.getProcessValue());
        assertEquals("Garden World Cleanup",
                GardenWorldCleanupAbstract.getProcessName());

    }

    @Test
    @DisplayName("When the GardenWorld client does not exist, "
            + "the GardenWorldCleanUp process returns an error")
    final void ifGardenWorldDoesNotExist_ReturnsError() {

        setGardenWorldClientDoesNotExists();

        String message = uut.doIt();
        assertEquals(wrapMsg("ERROR") + " " + wrapMsg(CLIENT_NOT_FOUND_MSG),
                message);

    }

    @Test
    @DisplayName("When the GardenWorld client isUpToDate, "
            + "the GardenWorldCleanUp process returns a message")
    final void ifGardenWorldIsUpToDate_ReturnsMsg() {

        setGardenWorldClientExists();
        doNothing().when(uut).clearSessionLog();
        doNothing().when(uut).determinePeriodOffset();
        doNothing().when(uut).determineDocDateOffset();
        doNothing().when(uut).findDocDateLimits();
        doReturn(null).when(uut).getMinPeriodStartDate();
        doReturn(null).when(uut).getMaxPeriodEndDate();
        doReturn(null).when(uut).translate(anyString());
        uut.setYearOffSet(0);
        uut.setDocMonthOffset(0);

        String message = uut.doIt();
        assertEquals(wrapMsg(NO_CHANGES_MSG),
                message);

    }

    @Test
    @DisplayName("When there is an sql exception, then the doIt method will "
            + "throw an AdempiereException")
    final void whenThereIsAnSQLException_ThenThrowsException()
            throws Exception {

        CPreparedStatement psMock = mock(CPreparedStatement.class);
        setGardenWorldClientExists();
        doNothing().when(uut).clearSessionLog();
        doNothing().when(uut).determinePeriodOffset();
        uut.setYearOffSet(1);
        doReturn(psMock).when(uut).getPreparedStatement(anyString(), any());
        doThrow(SQLException.class).when(psMock).executeQuery();

        assertThrows(AdempiereException.class, () -> {
            uut.doIt();
        });

    }

    @ParameterizedTest(
            name = "When the target year is {0} and the actual year "
                    + "is {1}, the offset should be {2}")
    @MethodSource("givenTargetAndActualYearAndExpectedOffset")
    @DisplayName("When provided with target and actual minimum period"
            + " start dates, the year offset is set correctly")
    final void givenTargetAndActualPeriodStartsDates_offsetIsSetCorrectly(
            int targetYear, int actualYear, int expectedOffset) {

        doReturn(TimeUtil.getDay(targetYear, 1, 1)).when(uut)
                .getTargetEarliestPeriodStartDate();
        doReturn(TimeUtil.getDay(actualYear, 1, 1)).when(uut)
                .getMinPeriodStartDate();
        doReturn(TimeUtil.getDay(actualYear + 6, 12, 31)).when(uut)
                .getMaxPeriodEndDate();

        uut.determinePeriodOffset();

        assertEquals(expectedOffset, uut.getYearOffSet());

    }

    @Test
    @DisplayName("When minimum period can't be found"
            + " then determinePeriodOffset throws an exception")
    final void givenNullMinPeriod_throwsException() {

        doReturn(TimeUtil.getDay(2020, 1, 1)).when(uut)
                .getTargetEarliestPeriodStartDate();
        doReturn(null).when(uut)
                .getMinPeriodStartDate();

        assertThrows(NullPointerException.class, () -> {
            uut.determinePeriodOffset();
        });

    }

    @Test
    @DisplayName("When provided a table name, then the process should know if "
            + "the table has a C_Period_ID but no associated date field")
    final void whenProvidedATable_ThenKnowsIfHasPeriodButNoDate() {

        assertTrue(uut.isTableKnownToHavePeriodButNoDate("M_ForecastLine"));
        assertFalse(uut.isTableKnownToHavePeriodButNoDate("M_InOut"));

    }

    @Test
    @DisplayName("When the table has DateAcct column, then "
            + "findDateColumnAssociatedWithPeriod will return the columnName")
    final void whenTableHasDateAcctColumn_thenReturnTheColumnName() {

        MTable tableMock = mock(MTable.class);
        doReturn("SomeTable").when(tableMock).getTableName();

        doReturn(false).when(uut)
                .isTableKnownToHavePeriodButNoDate(anyString());
        doReturn("SomeColumnName").when(uut).getDateAcctColumnName(anyString());

        String result = uut.findDateColumnAssociatedWithPeriod(tableMock);

        assertEquals("SomeColumnName", result);

    }

    @Test
    @DisplayName("When the table is known to have a C_Period_ID but no "
            + "DateAcct column, then findDateColumnAssociatedWithPeriod "
            + "will return null")
    final void whenTableIsKnownToNotHaveDateAcctColumn_thenReturnNull() {

        MTable tableMock = mock(MTable.class);
        doReturn("SomeTable").when(tableMock).getTableName();

        doReturn(true).when(uut).isTableKnownToHavePeriodButNoDate(anyString());

        String result = uut.findDateColumnAssociatedWithPeriod(tableMock);

        assertNull(result, "Expected a null result");

    }

    @Test
    @DisplayName("When the table name is not a document or doesn't have a "
            + "DateAcct column, then findDateColumnAssociatedWithPeriod "
            + "will log a warning and return null")
    final void whenTableHasNotDateAcctColumn_thenLogsAWarningAndReturnsNull() {

        MTable tableMock = mock(MTable.class);
        doReturn(null).when(tableMock).getColumn(anyString());
        doReturn("SomeTable").when(tableMock).getTableName();

        doReturn(false).when(uut)
                .isTableKnownToHavePeriodButNoDate(anyString());
        doReturn(null).when(uut).getDateAcctColumnName(anyString());

        String result = uut.findDateColumnAssociatedWithPeriod(tableMock);

        assertNull(result, "Expected a null result");
        verify(logMock).warning(anyString());

    }

}
