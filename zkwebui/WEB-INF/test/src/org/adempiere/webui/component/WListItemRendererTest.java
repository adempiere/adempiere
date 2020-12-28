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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Vector;

import org.adempiere.test.CommonUnitTestSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author Andrew Kimball
 *
 */
class WListItemRendererTest extends CommonUnitTestSetup {

    WListItemRenderer m_renderer;
    Vector<Object> m_dataValid = new Vector<Object>();
    Vector<Object> m_dataInvalid = new Vector<Object>();

    Vector<String> m_columnNames = new Vector<String>();

    @BeforeEach
    void localSetUp() {

        Vector<Object> dataRowValid = new Vector<Object>();
        Vector<Object> dataRowInvalid = new Vector<Object>();

        m_columnNames.add("Name");
        m_columnNames.add("Age");

        m_renderer = new WListItemRenderer(m_columnNames);

        dataRowValid.add("River Phoenix");
        dataRowValid.add(Integer.valueOf(23));
        m_dataValid.add(dataRowValid);

        dataRowInvalid.add("Elvis Presley");
        dataRowInvalid.add(Integer.valueOf(42));
        dataRowInvalid.add("Graceland");
        m_dataInvalid.add(dataRowInvalid);

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    final void testWListItemRenderer() {

        WListItemRenderer renderer = new WListItemRenderer();
        assertEquals(0, renderer.getNoColumns());

    }

    @Test
    final void testWListItemRendererVectorOfQextendsString() {

        assertEquals(2, m_renderer.getNoColumns());

    }

    @Disabled
    @Test
    final void testRender() throws Exception {
        /*
         * ListModelTable model = new ListModelTable(m_dataValid); WListbox
         * table = new WListbox(); table.setData(model, m_columnNames);
         * 
         * Listitem item = m_renderer.newListitem(table);
         * m_renderer.render(item, table.getModel().get(0));
         */

        fail("Not yet implemented");

    }

    @Test
    final void testUpdateColumn() {

        ListHead head = new ListHead();
        ListHeader header;

        m_renderer.updateColumn(1, "Address");
        assertEquals(2, m_renderer.getNoColumns());

        m_renderer.renderListHead(head);

        header = (ListHeader) head.getChildren().get(1);
        assertEquals("Address", header.getLabel());

    }

    @Test
    final void testAddColumn() {

        m_renderer.addColumn("Address");
        assertEquals(3, m_renderer.getNoColumns());

    }

    @Test
    final void testRenderListHead() {

        ListHead head = new ListHead();
        Object header;

        m_renderer.renderListHead(head);

        assertEquals(2, head.getChildren().size());

        header = head.getChildren().get(1);

        assertTrue(header instanceof ListHeader);
        assertEquals("Age", ((ListHeader) header).getLabel());

    }

    @Disabled
    @Test
    final void testGetRowPosition() {

        fail("Not yet implemented");

    }

    @Disabled
    @Test
    final void testGetColumnPosition() {

        fail("Not yet implemented");

    }

    @Test
    final void testClearColumns() {

        ListHead head = new ListHead();

        m_renderer.clearColumns();
        assertEquals(0, m_renderer.getNoColumns());

        m_renderer.renderListHead(head);

        assertEquals(0, head.getChildren().size());

    }

    @Disabled
    @Test
    final void testClearSelection() {

        fail("Not yet implemented");

    }

    @Disabled
    @Test
    final void testAddTableValueChangeListener() {

        fail("Not yet implemented");

    }

}
