package org.adempiere.webui.component;

import static org.adempiere.webui.component.GridPanel_Test.Direction.UP;
import static org.adempiere.webui.component.GridPanel_Test.Direction.DOWN;
import static org.adempiere.webui.component.GridPanel_Test.Direction.NO_CHANGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.adempiere.test.CommonUnitTestSetup;
import org.adempiere.webui.panel.ADTabPanel;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.compiere.model.MSysConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockedStatic;
import org.zkforge.keylistener.Keylistener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;

@Tag("ZK")
@Tag("GridPanel")
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
@DisplayName("TestGridPanel: Feature: GridPanel")
class GridPanel_Test extends CommonUnitTestSetup {

    private static final String PAGE_SIZE_KEY = "ZK_PAGING_SIZE";
    private static final String MODE_LESS_KEY = "ZK_GRID_EDIT_MODELESS";

    enum Move {
        FIRST, LAST, NEXT, PREVIOUS, PAGE_UP, PAGE_DOWN, NO_CHANGE
    };

    enum Key {

        UP(KeyEvent.UP),
        DOWN(KeyEvent.DOWN),
        PAGE_UP(KeyEvent.PAGE_UP),
        PAGE_DOWN(KeyEvent.PAGE_DOWN);

        private final int keyCode;

        private Key(int val) {

            keyCode = val;

        }

        public int getKeyCode() {

            return keyCode;

        }

    };

    enum Direction {

        UP(-1),
        DOWN(1),
        NO_CHANGE(0);

        int _direction;

        private Direction(int value) {

            _direction = value;

        }

        public int getDirection() {

            return _direction;

        }

    }

    Grid listMock;
    GridTab gridTabMock;
    GridPanel gridPanel;
    GridTable gridTableMock;
    ADTabPanel tabPanelMock;
    GridTabRowRenderer rendererMock;

    ArrayList<Row> testRows = new ArrayList<>();

    @Captor
    ArgumentCaptor<Integer> rowIndexCaptor;

    private int fillListWithRows(int tableSize, int currentRowIndex,
            int pageSize) {

        int page;
        if (pageSize == 0) {
            page = 0;
            pageSize = tableSize;
        } else {
            page = currentRowIndex / pageSize;
            pageSize = Math.min(pageSize, tableSize - page * pageSize);
        }
        lenient().doReturn(fillRowsForPage(page, pageSize)).when(listMock)
                .getRows();
        return pageSize;

    }

    private Rows fillRowsForPage(int page, int pageSize) {

        Rows rows = new Rows();
        testRows.clear();
        for (int i = page * pageSize; i < pageSize * (page + 1); i++) {
            Row row = rows.newRow();
            testRows.add(row);
            row.setAttribute("rowNumber", i);
        }

        return rows;

    }

    private int getExpectedRelativeMove(Key key) {

        int move = 0;
        switch (key.getKeyCode()) {

        case KeyEvent.UP:
            move = -1;
            break;

        case KeyEvent.DOWN:
            move = 1;
            break;

        case KeyEvent.PAGE_UP:
            move = -7;
            break;

        case KeyEvent.PAGE_DOWN:
            move = 7;
            break;

        default:
            fail("Unknown keyCode=" + key);
        }

        return move;

    }

    private void sendKeyEvent(Key key) throws Exception {

        Keylistener keyListener = gridPanel.getKeyListener();
        KeyEvent event = mock(KeyEvent.class);
        doReturn(Events.ON_CTRL_KEY).when(event).getName();
        doReturn(keyListener).when(event).getTarget();
        doReturn(key.getKeyCode()).when(event).getKeyCode();
        gridPanel.onEvent(event);

    }

    private void setupMocksforScenario(int pageSize, int tableSize,
            int currentRowIndex) {

        doReturn(true).when(tabPanelMock).isGridView();
        lenient().doReturn(new ArrayList<Integer>()).when(gridTableMock)
                .getRowChanged();
        lenient().doReturn(false).when(rendererMock).isEditing();
        lenient().doReturn(true).when(gridPanel).dataSave(0);
        lenient().doReturn(gridTableMock).when(gridTabMock).getMTable();
        lenient().doReturn(true).when(gridPanel)
                .isSafeToLeaveRow(currentRowIndex);
        pageSize =
                fillListWithRows(tableSize, currentRowIndex, pageSize);

        gridPanel.setADTabPanel(tabPanelMock);
        gridPanel.pageSize = pageSize;
        gridPanel.renderer = rendererMock;
        gridPanel.isNewRecord = false;
        gridPanel.addKeyListener();
        gridPanel.listbox = listMock;

        lenient().doReturn(tableSize).when(gridTabMock).getRowCount();
        lenient().doReturn(currentRowIndex).when(rendererMock)
                .getCurrentRowIndex();

        lenient().doNothing().when(gridPanel).scrollRowIntoView(any());

    }

    private void assertCorrectNavigationForMoveType(int currentRowIndex,
            int tableSize, Move moveType) {

        switch (moveType) {

        case FIRST:
            assertMovesToFirstRecord(currentRowIndex);
            break;

        case LAST:
            assertMovesToLastRecord(currentRowIndex, tableSize);
            break;

        case NEXT:
            assertNavigatesToNextRecord();
            break;

        case PREVIOUS:
            assertNavigatesToPreviousRecord();
            break;

        case PAGE_UP:
            assertNavigatesPageUp();
            break;

        case PAGE_DOWN:
            assertNavigatesPageDown();
            break;

        case NO_CHANGE:
            assertNoNavigationChange();
        }

    }

    @Captor
    ArgumentCaptor<Integer> moveDistanceCaptor;

    private void assertMovesToFirstRecord(int currentRowIndex) {

        verify(gridTabMock).navigateRelative(moveDistanceCaptor.capture());
        assertTrue(currentRowIndex + moveDistanceCaptor.getValue() <= 0);

    }

    private void assertMovesToLastRecord(int currentRowIndex, int tableSize) {

        verify(gridTabMock).navigateRelative(moveDistanceCaptor.capture());
        assertTrue(currentRowIndex + moveDistanceCaptor.getValue()
                >= tableSize - 1);

    }

    private void assertNavigatesToNextRecord() {

        verify(gridTabMock).navigateRelative(1);

    }

    private void assertNavigatesToPreviousRecord() {

        verify(gridTabMock).navigateRelative(-1);

    }

    private void assertNavigatesPageDown() {

        verify(gridTabMock).navigateRelative(7);

    }

    private void assertNavigatesPageUp() {

        verify(gridTabMock).navigateRelative(-7);

    }

    private void assertNoNavigationChange() {

        verify(gridTabMock, never()).navigateRelative(anyInt());

    }

    /**
     * Provides arguments for the SetUpDownRow tests. Does not test special
     * cases of table size = 0 (no rows), if the current row is not saved or
     * where the current row is a just saved new row.
     * 
     * @return Stream of arguments to test the SetUpDownRow
     */
    Stream<Arguments> argProviderSetUpDownRow() {

        return Stream.of(

        // @formatter:off
        //        Page | Table | Current | Key            | Move
        //        Size | Size  | Row     |                |
        arguments(  0,      1,      0,      Key.DOWN,       Move.LAST),
        arguments(  0,      2,      0,      Key.DOWN,       Move.NEXT),
        arguments(  0,      2,      1,      Key.DOWN,       Move.LAST),
        arguments(  0,     10,      0,      Key.DOWN,       Move.NEXT),
        arguments(  0,     10,      8,      Key.DOWN,       Move.NEXT),
        arguments(  0,     10,      5,      Key.DOWN,       Move.NEXT),
        arguments(  1,      2,      0,      Key.DOWN,       Move.NEXT),
        arguments(  1,      2,      1,      Key.DOWN,       Move.LAST),
        arguments(  0,      1,      0,      Key.UP,         Move.PREVIOUS),
        arguments(  0,      2,      0,      Key.UP,         Move.PREVIOUS),
        arguments(  0,      2,      1,      Key.UP,         Move.PREVIOUS),
        arguments(  0,     10,      0,      Key.UP,         Move.PREVIOUS),
        arguments(  0,     10,      5,      Key.UP,         Move.PREVIOUS),
        arguments(  0,     10,      8,      Key.UP,         Move.PREVIOUS),
        arguments(  1,      2,      0,      Key.UP,         Move.PREVIOUS),
        arguments(  1,      2,      1,      Key.UP,         Move.PREVIOUS),
        arguments(  0,      1,      0,      Key.PAGE_DOWN,  Move.LAST),
        arguments(  0,      2,      0,      Key.PAGE_DOWN,  Move.LAST),
        arguments(  0,      2,      1,      Key.PAGE_DOWN,  Move.LAST),
        arguments(  0,     10,      0,      Key.PAGE_DOWN,  Move.PAGE_DOWN),
        arguments(  0,     10,      5,      Key.PAGE_DOWN,  Move.LAST),
        arguments(  0,     10,      8,      Key.PAGE_DOWN,  Move.LAST),
        arguments(  1,      2,      0,      Key.PAGE_DOWN,  Move.LAST),
        arguments(  1,      2,      1,      Key.PAGE_DOWN,  Move.LAST),
        arguments(  6,     20,      0,      Key.PAGE_DOWN,  Move.PAGE_DOWN),
        arguments(  0,      1,      0,      Key.PAGE_UP,    Move.PAGE_UP),
        arguments(  0,      2,      0,      Key.PAGE_UP,    Move.PAGE_UP),
        arguments(  0,      2,      1,      Key.PAGE_UP,    Move.PAGE_UP),
        arguments(  0,     10,      0,      Key.PAGE_UP,    Move.PAGE_UP),
        arguments(  0,     10,      5,      Key.PAGE_UP,    Move.PAGE_UP),  
        arguments(  0,     10,      8,      Key.PAGE_UP,    Move.PAGE_UP),
        arguments(  1,      2,      0,      Key.PAGE_UP,    Move.PAGE_UP),
        arguments(  1,      2,      1,      Key.PAGE_UP,    Move.PAGE_UP)
        // @formatter:on
        );

    }

    Stream<Arguments> argProviderRowToMakeVisibleTest() {

        return Stream.of(
        // @formatter:off
        //         page  | rows  | row  | row in| Direction
        //         size  |       | index| page  |
        arguments(  0,     -1,      0,     -1,      UP),                
        arguments(  0,      0,      0,     -1,      UP),                
        arguments(  0,      1,      0,      0,      UP),
        arguments(  0,      1,      0,      0,      DOWN),
        arguments(  0,      1,      0,      0,      NO_CHANGE),
        arguments(  0,      2,      1,      1,      NO_CHANGE),
        arguments(  0,      3,      2,      2,      NO_CHANGE),
        arguments(  1,      1,      0,      0,      NO_CHANGE),
        arguments(  1,      1,      1,     -1,      NO_CHANGE),
        arguments(  2,      2,      0,      0,      NO_CHANGE),
        arguments(  2,      2,      1,      1,      NO_CHANGE),
        arguments(  20,     20,     0,      0,      NO_CHANGE),
        arguments(  20,     20,     19,    19,      NO_CHANGE),
        arguments(  20,     20,     10,    10,      NO_CHANGE),
        arguments(  20,     20,     0,      3,      DOWN),
        arguments(  20,     20,     1,      4,      DOWN),
        arguments(  20,     20,     2,      5,      DOWN),
        arguments(  20,     20,     3,      6,      DOWN),
        arguments(  20,     20,     10,    13,      DOWN),
        arguments(  20,     20,     17,    19,      DOWN),
        arguments(  20,     20,     18,    19,      DOWN),
        arguments(  20,     20,     19,    19,      DOWN),
        arguments(  20,     30,     19,    19,      DOWN),
        arguments(  20,     30,     1,      4,      DOWN),
        arguments(  20,     30,     2,      5,      DOWN),
        arguments(  20,     30,     3,      6,      DOWN),
        arguments(  20,     30,     10,    13,      DOWN),
        arguments(  20,     30,     19,    19,      DOWN),
        arguments(  20,     30,     20,     3,      DOWN),
        arguments(  20,     30,     27,     9,      DOWN),
        arguments(  20,     30,     28,     9,      DOWN),
        arguments(  20,     30,     29,     9,      DOWN),
        arguments(  20,     20,     19,    16,      UP),
        arguments(  20,     20,     10,     7,      UP),
        arguments(  20,     20,      4,     1,      UP),
        arguments(  20,     20,      3,     0,      UP),
        arguments(  20,     20,      2,     0,      UP),
        arguments(  20,     20,      1,     0,      UP),
        arguments(  20,     20,      0,     0,      UP),
        arguments(  20,     30,     29,     6,      UP),
        arguments(  20,     30,     20,     0,      UP),
        arguments(  20,     30,     19,    16,      UP),
        arguments(  20,     30,      3,     0,      UP),
        arguments(  20,     30,      2,     0,      UP),
        arguments(  20,     30,      1,     0,      UP),
        arguments(  20,     30,      0,     0,      UP)
        );
        // @formatter:on
    }

    @BeforeEach
    void setupMocks() {

        rendererMock = mock(GridTabRowRenderer.class);
        gridTableMock = mock(GridTable.class);
        gridTabMock = mock(GridTab.class);
        tabPanelMock = mock(ADTabPanel.class);
        listMock = mock(Grid.class);
        gridPanel = spy(GridPanel.class);
        doNothing().when(gridPanel).setupColumns();
        doNothing().when(gridPanel).render();
        doReturn(gridTableMock).when(gridTabMock).getTableModel();

    }

    @Nested
    @DisplayName("Given SysConfig defaults")
    class GivenSysConfigDefaults {

        @ParameterizedTest(
                name = "MSysConfig value for " + MODE_LESS_KEY
                        + " = {0}")
        @ValueSource(booleans = { true, false })
        @DisplayName("When GridPanel.init(gridTab) is called, then the "
                + "modeless field is set to the System Config value")
        final void testDefaultModelessKeyEqualsFalse(boolean sysConfigValue) {

            try (MockedStatic<MSysConfig> sysConfigMockStatic =
                    mockStatic(MSysConfig.class)) {

                sysConfigMockStatic.when(() -> {
                    MSysConfig.getBooleanValue(MODE_LESS_KEY, false);
                }).thenReturn(sysConfigValue);

                gridPanel.init(gridTabMock);

                assertEquals(sysConfigValue, gridPanel.modeless);

            }

        }

        @ParameterizedTest(
                name = "MSysConfig value for " + PAGE_SIZE_KEY
                        + " = {0}")
        @ValueSource(ints = { 0, 10, 20, 100 })
        @DisplayName("When GridPanel.init(gridTab) is called, then the "
                + "pageSize is set to the System Config value")
        final void testDefaultPageSizeKeyEqualsTrue(int sysConfigValue) {

            try (MockedStatic<MSysConfig> sysConfigMockStatic =
                    mockStatic(MSysConfig.class)) {

                sysConfigMockStatic.when(() -> {
                    MSysConfig.getIntValue(PAGE_SIZE_KEY, 100);
                }).thenReturn(sysConfigValue);

                gridPanel.init(gridTabMock);

                assertEquals(sysConfigValue, gridPanel.pageSize);

            }

        }

        @ParameterizedTest(
                name = "MSysConfig value for " + PAGE_SIZE_KEY
                        + " = {0}")
        @ValueSource(ints = { 0, 20 })
        @DisplayName("When GridPanel.pageSize is > 0, then GridPanel.init(gridTab)"
                + " will not set the pageSize")
        final void testPageSizeAlreadySet(int sysConfigValue) {

            try (MockedStatic<MSysConfig> sysConfigMockStatic =
                    mockStatic(MSysConfig.class)) {

                sysConfigMockStatic.when(() -> {
                    MSysConfig.getIntValue(PAGE_SIZE_KEY, 100);
                }).thenReturn(sysConfigValue);

                gridPanel.setPageSize(30);
                gridPanel.init(gridTabMock);

                assertEquals(30, gridPanel.pageSize);

            }

        }

    }

    @Nested
    @DisplayName("Given a GridTab")
    @TestInstance(Lifecycle.PER_CLASS)
    class GivenAGridTab {

        @BeforeEach
        void setupMocks() {

            doReturn(100).when(gridPanel)
                    .getSysConfigPageSizeOrDefault(anyInt());
            doReturn(false).when(gridPanel)
                    .getSysConfigModelessOrDefault(anyBoolean());

            gridPanel.init(gridTabMock);

        }

        @Test
        @DisplayName("When isSafeToLeaveRow is called, then "
                + "the tableModel checkField method is called")
        final void testIsSafeToLeaveRow() {

            doReturn(true).when(gridTableMock).checkField(anyInt());
            assertTrue(gridPanel.isSafeToLeaveRow(0));
            verify(gridTableMock).checkField(rowIndexCaptor.capture());
            assertEquals(0, rowIndexCaptor.getValue());

        }

        @Test
        @DisplayName("Given the table model has columns, when createNewRow "
                + "is called, then the GridTab dataNew method is called")
        final void testCreateNewRow() {

            doNothing().when(gridPanel).onPostSelectedRowChanged();
            doNothing().when(gridPanel).addKeyListener();
            doReturn(1).when(rendererMock).getTotalColumns();
            doReturn(true).when(gridTabMock).dataNew(false);
            gridPanel.renderer = rendererMock;

            gridPanel.createANewRow();

            verify(gridTabMock).dataNew(false);
            // Twice since it is called once in the init
            verify(gridPanel, times(2)).updateListIndex();
            verify(gridPanel).addKeyListener();
            verify(gridPanel).onPostSelectedRowChanged();

        }

        @Test
        @DisplayName("Given the table model has no columns, when createNewRow "
                + "is called, then the GridTab dataNew method is not called")
        final void testCreateNewRowWhenNoTableColumns() {

            doNothing().when(gridPanel).onPostSelectedRowChanged();
            doReturn(-1).when(rendererMock).getTotalColumns();
            gridPanel.renderer = rendererMock;
            gridPanel.createANewRow();
            verify(gridTabMock, never()).dataNew(false);
            verify(gridPanel).onPostSelectedRowChanged();

        }

        Stream<Arguments> localArgProviderSetUpDownRow() {

            return argProviderSetUpDownRow();

        }

        @ParameterizedTest(
                name = "For page size={0}, table size={1}, the current row={2}, "
                        + "when the key code={3}, then the move type is {4}")
        @MethodSource("localArgProviderSetUpDownRow")
        @DisplayName("Given a variety of non-zero table sizes")
        final void testSetUpDownRow(int pageSize, int tableSize,
                int currentRowIndex, Key key, Move moveType) throws Exception {

            setupMocksforScenario(pageSize, tableSize, currentRowIndex);

            sendKeyEvent(key);
            verify(gridPanel).setUpDownRow(getExpectedRelativeMove(key));
            assertCorrectNavigationForMoveType(currentRowIndex, tableSize,
                    moveType);

        }

        Stream<Arguments> localArgProviderRowToMakeVisibleTest() {

            return argProviderRowToMakeVisibleTest();

        }

        @ParameterizedTest(
                name = "When page size is {0}, total row count is {1}, the row is "
                        + "{2} and the direction is {4}, "
                        + "then the row in page to make visible should be {3}")
        @MethodSource("localArgProviderRowToMakeVisibleTest")
        final void testRowToMakeVisible(int pageSize, int totalRowCount,
                int rowIndex,
                int rowToMakeVisible, Direction direction) {

            doReturn(totalRowCount).when(gridTabMock).getRowCount();
            
            gridPanel.setPageSize(pageSize);
            gridPanel.init(gridTabMock);
            assertEquals(rowToMakeVisible,
                    gridPanel.getRowPageIndexToMakeSpacingVisible(rowIndex,
                            direction.getDirection()));

        }

        @Nested
        @DisplayName("Given a table with at least one row")
        class GivenATableWithAtLeastOneRow {

            @BeforeEach
            void setupTableWithTwoRows() {

                setupMocksforScenario(0, 2, 0);

            }

            @Test
            @DisplayName("When GridTab has a non-zero row count, "
                    + "hasNoRows returns false")
            final void whenGridTabHasRowCount_hasNoRowsReturnsFalse() {

                assertFalse(gridPanel.hasNoRows());

            }

            @Test
            @DisplayName("When isSafeToLeaveRow is false, then setUpDownRow "
                    + "performs no navigation")
            final void whenIsNotSafeToLeaveRow_thenSetUpDownRowMakesNoChange()
                    throws Exception {

                doReturn(false).when(gridPanel).isSafeToLeaveRow(anyInt());
                sendKeyEvent(Key.DOWN);
                assertNoNavigationChange();

            }

        }

        @Nested
        @DisplayName("Given a table with no rows")
        class GivenATableWithNoRows {

            @BeforeEach
            void setupTableWithNoRows() {

                setupMocksforScenario(0, 0, 0);

            }

            @Test
            @DisplayName("When GridTab has zero row count, hasNoRows returns true")
            final void whenGridTabHasZeroRowCount_hasNoRowsReturnsTrue() {

                doReturn(0).when(gridTabMock).getRowCount();
                assertTrue(gridPanel.hasNoRows());

            }

            @ParameterizedTest(
                    name = "When the key code = {0}, then a new row is created")
            @EnumSource(names = { "PAGE_DOWN", "PAGE_UP" })
            @DisplayName("For Page UP and Page DOWN")
            final void testSetUpDownRow(Key key) throws Exception {

                sendKeyEvent(key);
                verify(gridPanel).setUpDownRow(getExpectedRelativeMove(key));
                assertCorrectNavigationForMoveType(-1, 0, Move.NO_CHANGE);
                verify(gridPanel).createANewRow();

            }

            @ParameterizedTest(
                    name = "When the key code = {0}, then setUpDownRow is never called!")
            @EnumSource(names = { "DOWN", "UP" })
            @DisplayName("For UP and DOWN")
            final void testSetUpDownRowNotCalled(Key key) throws Exception {

                sendKeyEvent(key);
                verify(gridPanel, never()).setUpDownRow(anyInt());

            }

        }

        @Nested
        @DisplayName("Given a table with rows and the last row is the "
                + "current row")
        class GivenATableWithRowsAndTheLastRowIsCurrent {

            @BeforeEach
            void setupTableWithRows() {

                setupMocksforScenario(0, 2, 1);

            }

            @Test
            @DisplayName("Given the record is new, when a down key event occurs"
                    + " then no new record is created")
            final void whenNewAndSaveFails_thenNoNewRecord() throws Exception {

                gridPanel.isNewRecord = true;
                sendKeyEvent(Key.DOWN);
                verify(gridPanel, never()).onNew();

            }

            @Test
            @DisplayName("Given the record is not new and the save succeeds, "
                    + "when a down key event occurs, then no new record is created")
            final void whenNotNewAndSaveSucceeds_thenNoNewRecord()
                    throws Exception {

                gridPanel.isNewRecord = false;
                doReturn(true).when(gridPanel).dataSave(anyInt());
                sendKeyEvent(Key.DOWN);
                verify(gridPanel, never()).onNew();

            }

            @Test
            @DisplayName("Given the record is not new and the save fails, "
                    + "when a down key event occurs, then a new record is "
                    + "created")
            final void whenNotNewAndSaveFails_thenANewRecord()
                    throws Exception {

                gridPanel.isNewRecord = false;
                doReturn(false).when(gridPanel).dataSave(anyInt());
                sendKeyEvent(Key.DOWN);
                verify(gridPanel).onNew();

            }

            @Test
            @DisplayName("Given the record is not new, the save fails and the "
                    + "current column is the last column, then the current "
                    + "column will be set to zero")
            final void whenNotNewAndSaveFailsAndLastColumn_thenColZero()
                    throws Exception {

                gridPanel.isNewRecord = false;
                doReturn(false).when(gridPanel).dataSave(anyInt());
                doReturn(1).when(gridTabMock).getCurrentCol();
                doReturn(true).when(rendererMock).isLastColumn();
                sendKeyEvent(Key.DOWN);
                verify(rendererMock).setCurrentColumn(0);

            }

        }

        @Nested
        @DisplayName("Given a table with rows and the last row is not the "
                + "current row")
        class GivenATableWithRowsAndTheLastRowIsNotCurrent {

            @BeforeEach
            void setupTableWithRows() {

                setupMocksforScenario(0, 20, 10);

            }

            @ParameterizedTest(
                    name = "When the key code = {0}, then no navigation occurs")
            @EnumSource(names = { "UP", "DOWN", "PAGE_DOWN", "PAGE_UP" })
            @DisplayName("Given the record is new")
            final void givenNewRecord_thenNoNavigation(Key key)
                    throws Exception {

                doReturn(true).when(gridTabMock).isNew();
                sendKeyEvent(key);
                verify(gridTabMock, never()).navigateRelative(anyInt());
                verify(rendererMock, never()).setCurrentRowOnPage(anyInt());

            }

        }

    }

}
