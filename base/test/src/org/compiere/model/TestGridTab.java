package org.compiere.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.adempiere.test.CommonUnitTestSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@Tag("ZK")
@Tag("GridTab")
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
@DisplayName("TestGridTab: Feature: GridTab")
class TestGridTab extends CommonUnitTestSetup {

    GridTabVO gridTabVOMock;
    GridWindow gridWindowMock;
    GridTable gridTableMock;
    GridTab gridTab;

    @BeforeEach
    void setupMocks() {

        gridTabVOMock = mock(GridTabVO.class);
        gridWindowMock = mock(GridWindow.class);
        gridTableMock = mock(GridTable.class);
        gridTab = spy(new GridTab(gridTabVOMock, gridWindowMock));
        gridTab.m_mTable = gridTableMock;
        lenient().doReturn(false).when(gridTableMock).isLoading();

    }
    
    static Stream<Arguments> argProviderTableLimits() {
        
        boolean tableOpen = true;
        boolean tableClosed = false;
        return Stream.of(
                // clipped row output when
                // table open/closed, row count, current row, expected result
                arguments(tableClosed, 2, -1, -1),
                arguments(tableClosed, 2, 0, -1),
                arguments(tableClosed, 2, 1, -1),
                arguments(tableOpen, 0, -1, -1),
                arguments(tableOpen, 0, 0, -1),
                arguments(tableOpen, 0, 1, -1),
                arguments(tableOpen, 1, -1, 0),
                arguments(tableOpen, 1, 0, 0),
                arguments(tableOpen, 1, 1, 0),
                arguments(tableOpen, 1, 2, 0),
                arguments(tableOpen, 2, -1, 0),
                arguments(tableOpen, 2, 0, 0),
                arguments(tableOpen, 2, 1, 1),
                arguments(tableOpen, 2, 2, 1),
                arguments(tableOpen, 2, 3, 1),
                arguments(tableOpen, 3, -1, 0),
                arguments(tableOpen, 3, 0, 0),
                arguments(tableOpen, 3, 1, 1),
                arguments(tableOpen, 3, 2, 2),
                arguments(tableOpen, 3, 3, 2),
                arguments(tableOpen, 3, 4, 2)
                );
    }

    static Stream<Arguments> argProviderGetCurrentRow() {
        
        boolean tableOpen = true;
        boolean tableClosed = false;
        return Stream.of(
                // getCurrentRow output when
                // table open/closed, row count, current row, expected result
                arguments(tableClosed,  2, -1, -1),
                arguments(tableClosed,  2, 0, -1),
                arguments(tableClosed, 2, 1, -1),
                arguments(tableOpen, 0, -1, -1),
                arguments(tableOpen, 0, 0, -1),
                arguments(tableOpen, 0, 1, -1),
                arguments(tableOpen, 1, -1, 0),
                arguments(tableOpen, 1, 0, 0),
                arguments(tableOpen, 1, 1, 0),
                arguments(tableOpen, 1, 2, 0),
                arguments(tableOpen, 2, -1, 1),
                arguments(tableOpen, 2, 0, 0),
                arguments(tableOpen, 2, 1, 1),
                arguments(tableOpen, 2, 2, 1),
                arguments(tableOpen, 2, 3, 1),
                arguments(tableOpen, 3, -1, 2),
                arguments(tableOpen, 3, 0, 0),
                arguments(tableOpen, 3, 1, 1),
                arguments(tableOpen, 3, 2, 2),
                arguments(tableOpen, 3, 3, 2),
                arguments(tableOpen, 3, 4, 2)
                );
    }

    @ParameterizedTest(name="Given table open={0} and has rowCount={1}, when "
            + "targetIndex={2}, limitRowIndexToRange() returns {3}")
    @MethodSource("argProviderTableLimits")
    @DisplayName("Given a table, limitRowIndex clips the target index to "
            + "the table range")
    final void testLimitRowIndexToRange(
            boolean tableOpen, int rowCount, int targetIndex, 
            int limitedIndex) {

        doReturn(tableOpen).when(gridTableMock).isOpen();
        lenient().doReturn(rowCount).when(gridTableMock).getRowCount();
        assertEquals(limitedIndex, gridTab.verifyRow(targetIndex));
        
    }
    
    @ParameterizedTest(name="Given table open={0} and has rowCount={1}, when "
            + "currentRow={2}, getCurrentRow returns {3}")
    @MethodSource("argProviderGetCurrentRow")
    @DisplayName("Given a table, getCurrentRow clips the currentRow index to "
            + "the table range, defaulting to the last record if the "
            + "current row is undefined (-1)")
    final void whengetCurrentRow(
            boolean tableOpen, int rowCount, int currentRow, 
            int resultingRow) {
        lenient().doNothing().when(gridTab).loadDependentInfo();
        doReturn(tableOpen).when(gridTableMock).isOpen();
        lenient().doReturn(rowCount).when(gridTableMock).getRowCount();
        gridTab.m_currentRow = currentRow;
        assertEquals(resultingRow, gridTab.getCurrentRow());
        
    }

}
