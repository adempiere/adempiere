/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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

package org.adempiere.webui.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Vector;

import org.adempiere.test.CommonUnitTestSetup;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link org.adempiere.webui.component.ListModelTable}.
 * 
 * @author Andrew Kimball
 *
 */
class ListModelTableTest extends CommonUnitTestSetup
        implements WTableModelListener {

    private static final Integer ms_number0 = Integer.valueOf(0);
    private static final Integer ms_number1 = Integer.valueOf(1);
    private static final Integer ms_number2 = Integer.valueOf(2);
    private static final Integer ms_number3 = Integer.valueOf(3);
    private static final Integer ms_number4 = Integer.valueOf(4);
    private static final Integer ms_number5 = Integer.valueOf(5);
    private static final Integer ms_number6 = Integer.valueOf(6);

    private ListModelTable<Integer> table;
    private boolean m_isListenerCalled = false;

    public void tableChanged(WTableModelEvent event) {
    
        m_isListenerCalled = true;
    
    }

    @BeforeEach
    void localSetUp() {

        Vector<Object> data = addRowsToData(createRow0(), createRow1());
        table = new ListModelTable<>(data);

    }

    private Vector<Object> addRowsToData(Vector<Integer> row0, Vector<Integer> row1) {

        Vector<Object> data = new Vector<Object>();
        data.add(row0);
        data.add(row1);
        return data;

    }

    private Vector<Integer> createRow0() {

        Vector<Integer> row0 = new Vector<>();;
        row0.add(ms_number0);
        row0.add(ms_number1);
        return row0;

    }

    private Vector<Integer> createRow1() {

        Vector<Integer> row1 = new Vector<>();;
        row1.add(ms_number2);
        row1.add(ms_number3);
        return row1;

    }

    @Test
    final void testListModelTable_ifDataEmptyThrowsException() {

        Vector<Object> data = new Vector<Object>();
        ListModelTable<Integer> table = new ListModelTable<>(data);
        assertThrows(IllegalArgumentException.class, () -> {
            table.getDataAt(0, 0);
        });

    }

    @Test
    final void testListModelTableCollection_ifInvalidRow_throwsException() {

        final int invalidRow = 2;
        final int noColumns = 2;

        assertEquals(noColumns, table.getNoColumns());
        assertEquals(noColumns, table.getSize());
        assertEquals(Integer.valueOf(0), table.getDataAt(0, 0));

        assertThrows(IllegalArgumentException.class, () -> {
            table.getDataAt(invalidRow, 0);
        });

    }

    @Test
    final void testAddColumn() {

        final int noColumns = table.getNoColumns();
        table.addColumn();

        assertEquals(noColumns+1, table.getNoColumns());
        assertNull(table.getDataAt(0, noColumns));

    }

    @Test
    final void testSetNoColumns() {

        final int noColumns = table.getNoColumns();
        table.setNoColumns(noColumns+1);

        assertEquals(noColumns+1, table.getNoColumns());
        assertNull(table.getDataAt(0, noColumns));

    }

    @Test
    final void testGetDataAt() {

        assertEquals(ms_number0, table.getDataAt(0, 0));
        assertEquals(ms_number3, table.getDataAt(1, 1));

    }

    @Test
    final void testSetDataAt_validRow() {

        table.setDataAt(ms_number4, 0, 0);
        table.setDataAt(ms_number5, 1, 1);

        assertEquals(ms_number4, table.getDataAt(0, 0));
        assertEquals(ms_number5, table.getDataAt(1, 1));

    }

    @Test
    final void testSetDataAt_invalidRow_throwsException() {

        final int invalidRow = 2;

        assertThrows(IllegalArgumentException.class, () -> {
            table.setDataAt(ms_number6, invalidRow, 0);
        });
    }

    @Test
    final void testSetNoRows() {

        final int noRows = table.getSize();
        table.setNoRows(noRows + 1);

        assertEquals(noRows+1, table.getSize());
        assertEquals(ms_number3, table.getDataAt(1, 1));
        assertNull(table.getDataAt(noRows, 1));

    }

    @Test
    final void testAddTableModelListener() {

        table.addTableModelListener(this);
        table.setDataAt(ms_number4, 0, 0);

        assertTrue(m_isListenerCalled);

    }

}
