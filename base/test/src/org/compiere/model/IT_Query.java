/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.compiere.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.adempiere.core.domains.models.X_AD_Element;
import org.adempiere.exceptions.DBException;
import org.adempiere.test.CommonGWSetup;
import org.compiere.util.DB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

/**
 * Test {@link org.compiere.model.Query} class
 * 
 * @author Teo Sarca, www.arhipac.ro
 */

@Tag("Model")
@Tag("Query")
class IT_Query extends CommonGWSetup {

    private static final int C_INVOICE_TABLE_ID = 318;

    final String sqlFrom =
            "FROM C_InvoiceLine WHERE IsActive='Y' AND AD_Client_ID="
                    + AD_CLIENT_ID;
    static Query agQuery;

    @BeforeAll
    static void localBeforeAll() {

        agQuery = new Query(ctx, "C_InvoiceLine", null, trxName)
                .setOnlyActiveRecords(true)
                .setClient_ID();

    }

    private void assertC_InvoiceM_InOutFound(List<MTable> tables) {

        assertEquals("C_Invoice", tables.get(0).getTableName(),
                "Invalid object " + tables.get(0));
        assertEquals("M_InOut", tables.get(1).getTableName(),
                "Invalid object " + tables.get(1));
        assertEquals(2, tables.size(), "More objects retrived than expected");

    }

    private Query createQueryToReturnTwoResults() {

        Query query = new Query(getCtx(), "AD_Table", "TableName IN (?,?)",
                getTrxName())
                        .setParameters(new Object[] { "C_Invoice", "M_InOut" })
                        .setOrderBy("TableName");
        return query;

    }

    @ParameterizedTest
    @NullAndEmptySource
    void testQuery_NullEmptyTable(String tableName) {

        assertThrows(IllegalArgumentException.class, () -> {
            new Query(ctx, tableName, null, trxName);
        });

    }

    @Test
    void testQuery_NoTable() {

        assertThrows(IllegalArgumentException.class, () -> {
            new Query(ctx, "NO_TABLE_DEFINED", null, trxName);
        });

    }

    @Test
    void testList() {

        List<MTable> list = new Query(getCtx(), "AD_Table",
                "TableName IN (?,?)", getTrxName())
                        .setParameters(new Object[] { "C_Invoice", "M_InOut" })
                        .setOrderBy("TableName")
                        .list();
        assertEquals(2, list.size(), "Invalid list size");
        assertEquals("C_Invoice", list.get(0).getTableName(),
                "Invalid object 1");
        assertEquals(list.get(1).getTableName(), "M_InOut", "Invalid object 2");

    }

    @Test
    void testScroll() {

        POResultSet<MTable> rs = new Query(getCtx(), "AD_Table",
                "TableName IN (?,?)", getTrxName())
                        .setParameters(new Object[] { "C_Invoice", "M_InOut" })
                        .setOrderBy("TableName")
                        .scroll();

        List<MTable> tables = new ArrayList<>();
        try {
            while (rs.hasNext()) {
                tables.add(rs.next());
            }
        } finally {
            DB.close(rs);
            rs = null;
        }

        assertC_InvoiceM_InOutFound(tables);

    }

    @Test
    void testIterate() {

        Iterator<MTable> it = new Query(getCtx(), "AD_Table",
                "TableName IN (?,?)", getTrxName())
                        .setParameters(new Object[] { "C_Invoice", "M_InOut" })
                        .setOrderBy("TableName")
                        .iterate();

        List<MTable> tables = new ArrayList<>();
        while (it.hasNext()) {
            tables.add(it.next());
        }

        assertC_InvoiceM_InOutFound(tables);

    }

    @Test
    void testCount() {

        int count = new Query(getCtx(), "AD_Table", "TableName IN (?,?)",
                getTrxName())
                        .setParameters(new Object[] { "C_Invoice", "M_InOut" })
                        .setOrderBy("TableName")
                        .count();
        assertEquals(2, count, "Invalid count");

    }

    @Test
    void testCount_BadSQL() {

        Query query = new Query(getCtx(), "AD_Table",
                "TableName IN (?,?) AND BAD_SQL", getTrxName())
                        .setParameters(new Object[] { "C_Invoice", "M_InOut" })
                        .setOrderBy("TableName");

        assertThrows(DBException.class, () -> {
            query.count();
        });

    }

    @Test
    void testCount_NoValues() {

        int count =
                new Query(getCtx(), "AD_Table", "1=2", getTrxName()).count();
        assertEquals(0, count, "Counter should be ZERO");

    }

    @Test
    void testFirst() {

        MTable t = new Query(getCtx(), "AD_Table", "TableName IN (?,?)",
                getTrxName())
                        .setParameters(new Object[] { "C_Invoice", "M_InOut" })
                        .setOrderBy("TableName")
                        .first();
        assertEquals("C_Invoice", t.getTableName(), "Invalid object");

    }

    void testFirstId() throws Exception {

        int id = new Query(getCtx(), "AD_Table", "TableName IN (?,?)",
                getTrxName())
                        .setParameters(new Object[] { "C_Invoice", "M_InOut" })
                        .setOrderBy("TableName")
                        .firstId();
        assertEquals(C_INVOICE_TABLE_ID, id, "Invalid ID");

    }

    @Test
    void testFirstOnly_success() {

        MTable t =
                new Query(getCtx(), "AD_Table", "AD_Table_ID=?", getTrxName())
                        .setParameters(new Object[] { C_INVOICE_TABLE_ID })
                        .firstOnly();
        assertEquals(C_INVOICE_TABLE_ID, t.get_ID(), "Invalid table ID");

    }

    @Test
    void testFirstOnly_throwsExceptionWhenMoreThanOne() {

        Query query = createQueryToReturnTwoResults();

        assertThrows(DBException.class, () -> {
            query.firstOnly();
        });

    }

    @Test
    void testFirstIdOnly_success() {

        int expectedId = C_INVOICE_TABLE_ID;
        int id = new Query(getCtx(), "AD_Table", "AD_Table_ID=?", getTrxName())
                .setParameters(new Object[] { expectedId })
                .firstIdOnly();
        assertEquals(expectedId, id, "Invalid table ID");

    }

    @Test
    void testFirstIdOnly_throwsExceptionIfMoreThanOne() {

        Query query = createQueryToReturnTwoResults();

        assertThrows(DBException.class, () -> {
            query.firstIdOnly();
        });

    }

    @Test
    void testSetClient_ID_whenSet() {

        String sql = new Query(getCtx(), "C_Invoice", "1=1", getTrxName())
                .setOnlyActiveRecords(true)
                .setClient_ID()
                .getSQL();

        assertTrue(sql.contains("AD_Client_ID=?"),
                "Client ID clause not added to SQL as requested");

    }

    @Test
    void testSetClient_ID_whenNotSet() {

        String sql = new Query(getCtx(), "C_Invoice", "1=1", getTrxName())
                .setOnlyActiveRecords(true)
                .getSQL();

        assertFalse(sql.contains("AD_Client_ID=?"),
                "Client ID clause was added to SQL when not requested");

    }

    @Test
    void testGet_IDs() {

        final String whereClause = "AD_Element_ID IN (101, 102)";
        int[] ids = new Query(getCtx(), "AD_Element", whereClause, getTrxName())
                .setOrderBy("AD_Element_ID")
                .getIDs();
        assertNotNull(ids);
        assertEquals(2, ids.length);
        assertEquals(101, ids[0]);
        assertEquals(102, ids[1]);

    }

    static Stream<Arguments> aggregateTestProvider() {

        return Stream.of(
                arguments(Query.AGGREGATE_COUNT, null, "SELECT COUNT(*)",
                        BigDecimal.class),
                arguments(Query.AGGREGATE_SUM, "LineNetAmt+TaxAmt",
                        "SELECT SUM(LineNetAmt+TaxAmt)", BigDecimal.class),
                arguments(Query.AGGREGATE_MIN, "LineNetAmt",
                        "SELECT MIN(LineNetAmt)", BigDecimal.class),
                arguments(Query.AGGREGATE_MAX, "LineNetAmt",
                        "SELECT MAX(LineNetAmt)", BigDecimal.class),
                arguments(Query.AGGREGATE_MAX, "LineNetAmt",
                        "SELECT MAX(LineNetAmt)", BigDecimal.class),
                arguments(Query.AGGREGATE_MAX, "Description",
                        "SELECT MAX(Description)", String.class),
                arguments(Query.AGGREGATE_MAX, "Updated", "SELECT MAX(Updated)",
                        Timestamp.class));

    }

    @ParameterizedTest
    @MethodSource("aggregateTestProvider")
    void testAggregate_count(String function, String sqlExpression,
            String selectEquivalent, Class<?> returnType) {

        Object value = null;
        if (BigDecimal.class.equals(returnType))
            value = DB.getSQLValueBDEx(getTrxName(),
                    selectEquivalent + " " + sqlFrom);
        else if (String.class.equals(returnType))
            value = DB.getSQLValueStringEx(getTrxName(),
                    selectEquivalent + " " + sqlFrom);
        else if (Timestamp.class.equals(returnType))
            value = DB.getSQLValueTSEx(getTrxName(),
                    selectEquivalent + " " + sqlFrom);
        else
            fail("Need to add DB.getSQLValue for the supplied class "
                    + returnType.getName());

        assertEquals(value,
                agQuery.aggregate(sqlExpression, function, returnType),
                function + " does not match (" + returnType.getName() + ")");

    }

    @ParameterizedTest
    @NullAndEmptySource
    void testAggregateWithNullAndEmptyFunction(String function) {

        assertThrows(DBException.class, () -> {
            agQuery.aggregate("*", function);
        });

    }

    @ParameterizedTest
    @NullAndEmptySource
    void testAggregateWithNullAndEmptyExpression(String expression) {

        assertThrows(DBException.class, () -> {
            agQuery.aggregate(expression, Query.AGGREGATE_SUM);
        });

    }

    @Test
    void testOnlySelection() {

        // Get one AD_PInstance_ID
        int AD_PInstance_ID = DB.getSQLValueEx(null,
                "SELECT MAX(AD_PInstance_ID) FROM AD_PInstance");
        assertTrue(AD_PInstance_ID > 0);

        // Create selection list
        List<Integer> elements = new ArrayList<Integer>();
        elements.add(102); // AD_Element_ID=102 => AD_Client_ID
        elements.add(104); // AD_Element_ID=104 => AD_Column_ID
        DB.executeUpdateEx("DELETE FROM T_Selection WHERE AD_PInstance_ID="
                + AD_PInstance_ID, getTrxName());
        DB.createT_Selection(AD_PInstance_ID, elements, getTrxName());

        String whereClause = "1=1"; // some dummy where clause
        int[] ids = new Query(getCtx(), X_AD_Element.Table_Name, whereClause,
                getTrxName())
                        .setOnlySelection(AD_PInstance_ID)
                        .setOrderBy(X_AD_Element.COLUMNNAME_AD_Element_ID)
                        .getIDs();
        assertEquals(elements.size(), ids.length,
                "Resulting number of elements differ");

        for (int i = 0; i < elements.size(); i++) {
            int expected = elements.get(i);
            assertEquals(expected, ids[i], "Element " + i + " not equals");
        }

    }

}
