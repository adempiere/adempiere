package org.adempiere.webui.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.adempiere.test.CommonUnitTestSetup;
import org.compiere.model.GridTab;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.zkoss.zul.Paging;

@Tag("ZK")
@Tag("GridTabRowRenderer")
@DisplayName("TestGridTabRowRenderer: Feature: Rendering of rows in a GridTab")
class TestGridTabRowRenderer extends CommonUnitTestSetup {

    int numberOfRows = 100;
    ArrayList<Row> testRows = new ArrayList<>();

    Grid gridMock;
    GridTab gridTabMock;
    Paging pagingMock;
    GridTabRowRenderer renderer;

    @BeforeEach
    void setupRowsAndMocks() {

        gridMock = mock(Grid.class);
        gridTabMock = mock(GridTab.class);
        pagingMock = mock(Paging.class);

        renderer = new GridTabRowRenderer(gridTabMock, 0);

        int page = 0;
        int pageSize = this.numberOfRows;
        fillRowsForPage(page, pageSize);

    }

    private void fillRowsForPage(int page, int pageSize) {

        Rows rows = new Rows();
        testRows.clear();
        for (int i = page * pageSize; i < pageSize; i++) {
            Row row = rows.newRow();
            testRows.add(row);
            row.setAttribute("rowNumber", i);
        }

    }

    @Nested
    @DisplayName("When paging is null")
    class givenNoPaging {

        @BeforeEach
        void setupPaging() {

            renderer.setPaging(null);

            fillRowsForPage(0, 20);

        }

        @Test
        @DisplayName("Then the absolute index and the index in page are equal")
        final void thenRowIndexShouldNotBeChanged() {

            for (Row row : testRows) {

                int absoluteIndex = (Integer) row.getAttribute("rowNumber");
                int indexInPage = row.getParent().getChildren().indexOf(row);
                int index = renderer.getRowIndexAcrossAllPages(indexInPage);
                assertEquals(absoluteIndex, index);

            }

        }

        @Test
        @DisplayName("Then the index in page equals the absolute index")
        final void givetestGetRowIndexInPage() {

            assertEquals(2, renderer.getRowIndexInPage(2));

        }

    }

    @Nested
    @DisplayName("When paging size is zero")
    class givenZeroPageSize {

        @BeforeEach
        void setupPaging() {

            doReturn(0).when(pagingMock).getPageSize();
            renderer.setPaging(pagingMock);

            fillRowsForPage(0, 20);

        }

        @Test
        @DisplayName("Then the absolute index and the index in page are equal")
        final void givenPagingWithZeroSize_rowIndexShouldNotBeChanged() {

            for (Row row : testRows) {

                int absoluteIndex = (Integer) row.getAttribute("rowNumber");
                int indexInPage = row.getParent().getChildren().indexOf(row);
                int index = renderer.getRowIndexAcrossAllPages(indexInPage);
                assertEquals(absoluteIndex, index);

            }

        }

        @Test
        @DisplayName("Then the index in page equals the absolute index")
        final void testGetRowIndexInPage() {

            assertEquals(2, renderer.getRowIndexInPage(2));

        }

    }

    @Nested
    @DisplayName("Given paging with 20 rows per page")
    class givenPagingWith20RowsPerPage {

        @BeforeEach
        void setupPaging() {

            renderer.setPaging(pagingMock);
            doReturn(20).when(pagingMock).getPageSize();
            doReturn(2).when(pagingMock).getActivePage();

            fillRowsForPage(2, 20);


        }

        @Test
        @DisplayName("When on page 2, the absolute index should be 40 + "
                + "index in page")
        final void whenOnPage2_rowIndexShouldReflectThePage() {

            for (Row row : testRows) {

                int indexInPage = row.getParent().getChildren().indexOf(row);
                int index = renderer.getRowIndexAcrossAllPages(indexInPage);
                assertEquals(40 + indexInPage, index);

            }

        }

        @Test
        @DisplayName("When the absolute 42nd row is displayed, it will appear "
                + "in row 2 on its page")
        final void testGetRowIndexInPage() {

            assertEquals(2, renderer.getRowIndexInPage(42));

        }

        @Test
        @DisplayName("Renderer setCurrentCell method should set the gridTab "
                + "setCurrentRow to the same value")
        final void rendererSetCurrentCellShouldSetGridTabCurrentRow() {

            renderer.setCurrentCell(47);
            verify(gridTabMock).setCurrentRow(47);

        }

    }

}
