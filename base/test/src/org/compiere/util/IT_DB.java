/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * Copyright (C) 2006-2020 ADempiere Foundation, All Rights Reserved.         *
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
package org.compiere.util;


import io.vavr.control.Try;
import org.adempiere.exceptions.DBException;
import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MTable;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test {@link org.compiere.util.DB} class
 * 
 * @author Teo Sarca, www.arhipac.ro
 */
@Tag("DB")
class IT_DB extends CommonGWSetup {

    @Test
    void getSQLValueEx_ifNotExistantTable_throwsException() {

        assertThrows(DBException.class, () -> {
            DB.getSQLValueEx(null, "SELECT 10 FROM NON-EXISTENT-TABLE");
        });

    }

    @Test
    void getSQLValueEx_simpleSelect() {

        int result = DB.getSQLValueEx(null, "SELECT 10 FROM DUAL");
        assertEquals(10, result);

    }

    @Test
    void getSQLValueEx_noResult() {

        int result =
                DB.getSQLValueEx(null, "SELECT 10 FROM AD_SYSTEM WHERE 1=2");
        assertEquals(-1, result, "No value should be returned");

    }

    @Test
    void getSQLValue_ifNotExistantTable_returnsNull() {

        int result = DB.getSQLValue(null, "SELECT 10 FROM INEXISTENT_TABLE");
        assertEquals(-1, result, "Error should be signaled");

    }

    @Test
    void getSQLValue_simpleSelect() {

        int result = DB.getSQLValue(null, "SELECT 10 FROM DUAL");
        assertEquals(10, result);

    }

    @Test
    void getSQLValue_noResult() {

        int result = DB.getSQLValue(null,
                "SELECT 10 FROM AD_SYSTEM WHERE 1=2");
        assertEquals(-1, result, "No value should be returned");

    }

    @Test
    void getSQLValueBDEx_ifNotExistantTable_throwsException() {

        assertThrows(DBException.class, () -> {
            DB.getSQLValueEx(null, "SELECT 10 FROM INEXISTENT_TABLE");
        });

    }

    @Test
    void getSQLValueBDEx_simpleSelect() {

        BigDecimal result = DB.getSQLValueBDEx(null, "SELECT 10 FROM DUAL");
        assertEquals(BigDecimal.TEN, result);

    }

    @Test
    void getSQLValueBDEx_noResult() {

        BigDecimal result =
                DB.getSQLValueBDEx(null, "SELECT 10 FROM AD_SYSTEM WHERE 1=2");
        assertNull(result, "No value should be returned");

    }

    @Test
    void getSQLValueBD_ifNotExistantTable_returnsNull() {

        BigDecimal result =
                DB.getSQLValueBD(null, "SELECT 10 FROM INEXISTENT_TABLE");
        assertNull(result, "No value should be returned");

    }

    @Test
    void getSQLValueBD_simpleSelect() {

        BigDecimal result = DB.getSQLValueBD(null, "SELECT 10 FROM DUAL");
        assertEquals(BigDecimal.TEN, result);

    }

    @Test
    void getSQLValueBD_noResult() {

        BigDecimal result =
                DB.getSQLValueBD(null, "SELECT 10 FROM AD_SYSTEM WHERE 1=2");
        assertNull(result, "No value should be returned");

    }

    @Test
    void getSQLValueStringEx_ifNotExistantTable_throwsException() {

        assertThrows(DBException.class, () -> {
            DB.getSQLValueEx(null, "SELECT 10 FROM INEXISTENT_TABLE");
        });

    }

    @Test
    void getSQLValueStringEx_simpleSelect() {

        String result =
                DB.getSQLValueStringEx(null, "SELECT 'string' FROM DUAL");
        assertEquals("string", result);

    }

    @Test
    void getSQLValueStringEx_noResult() {

        String result =
                DB.getSQLValueStringEx(null,
                        "SELECT 10 FROM AD_SYSTEM WHERE 1=2");
        assertNull(result, "No value should be returned");

    }

    @Test
    void getSQLValueString_ifNotExistantTable_returnsNull() {

        String result =
                DB.getSQLValueString(null, "SELECT 10 FROM INEXISTENT_TABLE");
        assertNull(result, "No value should be returned");

    }

    @Test
    void getSQLValueString_simpleSelect() {

        String result = DB.getSQLValueString(null,
                "SELECT 'string' FROM DUAL");
        assertEquals("string", result);

    }

    @Test
    void getSQLValueString_noResult() {

        String result =
                DB.getSQLValueString(null,
                        "SELECT 10 FROM AD_SYSTEM WHERE 1=2");
        assertNull(result, "No value should be returned");

    }

    @Test
    void getSQLValueTSEx_ifNotExistantTable_throwsException() {

        assertThrows(DBException.class, () -> {
            DB.getSQLValueEx(null,
                    "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') "
                            + "FROM INEXISTENT_TABLE");
        });

    }

    @Test
    void getSQLValueTSEx_simpleSelect() {

        final Timestamp target = TimeUtil.getDay(2008, 01, 01);

        Timestamp result = DB.getSQLValueTSEx(null,
                "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') FROM AD_SYSTEM");
        assertEquals(target, result);

    }

    @Test
    void getSQLValueTSEx_noResult() {

        Timestamp result =
                DB.getSQLValueTSEx(null,
                        "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') "
                                + "FROM AD_SYSTEM WHERE 1=2");
        assertNull(result, "No value should be returned");

    }

    @Test
    void getSQLValueTS_ifNotExistantTable_returnsNull() {

        Timestamp result = DB.getSQLValueTS(null,
                "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') "
                        + "FROM INEXISTENT_TABLE");
        assertNull(result, "Error should be signaled");

    }

    @Test
    void getSQLValueTS_simpleSelect() {

        final Timestamp target = TimeUtil.getDay(2008, 01, 01);

        Timestamp result = DB.getSQLValueTS(null,
                "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') FROM AD_SYSTEM");
        assertEquals(target, result);

    }

    @Test
    void getSQLValueTS_noResult() {

        Timestamp result =
                DB.getSQLValueTS(null,
                        "SELECT TO_DATE('2008-01-01','YYYY-MM-DD') "
                                + "FROM AD_SYSTEM WHERE 1=2");
        assertNull(result, "No value should be returned");

    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    void test_getValueNamePairs_withOptionalItem(boolean withOptional) {

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(MTable.ACCESSLEVEL_AD_Reference_ID);
        final String sql =
                "SELECT Value, Name FROM AD_Ref_List "
                        + "WHERE AD_Reference_ID=? ORDER BY Value";

        ValueNamePair[] arr = DB.getValueNamePairs(sql, withOptional, params);
        assertValues(arr, withOptional);

    }

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    void test_getKeyNamePairs_withOptionalItem(boolean withOptional) {

        ArrayList<Object> params = new ArrayList<Object>();
        params.add(MTable.ACCESSLEVEL_AD_Reference_ID);
        final String sql =
                "SELECT AD_Ref_List_ID, Value FROM AD_Ref_List "
                        + "WHERE AD_Reference_ID=? ORDER BY Value";
        // Get (with optional item)
        KeyNamePair[] arr = DB.getKeyNamePairs(sql, withOptional, params);
        assertNames(arr, withOptional);

    }

    @Test
    void test_getResultSetListParamWithFunctionThrowsException() {
        Trx.run(trxName -> {
                    String sql = "SELECT DocumentNo FROM C_InvoiceNotExist WHERE C_Invoice_ID=? AND DocStatus=?";
                    io.vavr.collection.List<Object> params = io.vavr.collection.List.of(100,"CO");
                    Try<Void> result = DB.runResultSetFunction.apply(trxName , sql, params, rows -> {
                        while (rows.next()) {
                            String documentNo = rows.getString("documentNo");
                            assertEquals("200000", documentNo);
                        }
                    });
                    assertThrows(SQLException.class , () -> {
                        throw result.getCause();
                    });

                }
        );
    }

    @Test
    void test_getResultSetListParamWithFunction() {
        Trx.run(trxName -> {
                    String sql = "SELECT DocumentNo FROM C_Invoice WHERE C_Invoice_ID=? AND DocStatus=?";
                    io.vavr.collection.List<Object> params = io.vavr.collection.List.of(100,"CO");
                    Try<Void> result = DB.runResultSetFunction.apply(trxName, sql, params, rows -> {
                        while (rows.next()) {
                            String documentNo = rows.getString("documentNo");
                            assertEquals("200000", documentNo);
                        }
                    });

                    result.onFailure(exception -> {
                        assertEquals("200000", exception.getMessage());
                    });
                }
        );
    }

    private void assertValues(ValueNamePair[] arr, boolean withOptional) {

        int i = withOptional ? 1 : 0;
        assertEquals(6 + i, arr.length, "Invalid size");
        if (withOptional)
            assertEquals(ValueNamePair.EMPTY, arr[0],
                    "First value should be EMPTY");
        assertEquals("1", arr[i++].getValue());
        assertEquals("2", arr[i++].getValue());
        assertEquals("3", arr[i++].getValue());
        assertEquals("4", arr[i++].getValue());
        assertEquals("6", arr[i++].getValue());
        assertEquals("7", arr[i++].getValue());

    }

    private void assertNames(KeyNamePair[] arr, boolean withOptional) {

        int i = withOptional ? 1 : 0;
        assertEquals(6 + i, arr.length, "Invalid size");
        if (withOptional)
            assertEquals(KeyNamePair.EMPTY, arr[0],
                    "First value should be EMPTY");
        assertEquals("1", arr[i++].getName());
        assertEquals("2", arr[i++].getName());
        assertEquals("3", arr[i++].getName());
        assertEquals("4", arr[i++].getName());
        assertEquals("6", arr[i++].getName());
        assertEquals("7", arr[i++].getName());

    }

    @Test
    final void testGetCurrentTimeFromDatabase() {

        assertNotNull(DB.getCurrentTimeFromDatabase());
        
    }

}
