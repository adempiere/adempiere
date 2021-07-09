/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
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
package org.compiere.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Properties;
import java.util.stream.Stream;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonGWSetup;
import org.compiere.util.DB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for {@link org.compiere.model.PO} class.
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
@Tag("PO")
@Tag("Model")
class IT_PO_AfterSave extends CommonGWSetup {

    private MTest testPO;
    private static String bigString;
    private int maxLength;

    public static class MyTestPO extends MTest {

        private static final long serialVersionUID = -6861171283806782985L;
        protected boolean failOnSave = false;

        private MyTestPO m_parent = null;
        private MyTestPO m_dependentRecord = null;

        public static String getName(int Test_ID, String trxName) {

            String sql = "SELECT " + COLUMNNAME_Name + " FROM " + Table_Name
                    + " WHERE " + COLUMNNAME_Test_ID + "=?";
            return DB.getSQLValueStringEx(trxName, sql, Test_ID);

        }

        public static boolean exists(int Test_ID, String trxName) {

            final String sql =
                    "SELECT " + COLUMNNAME_Test_ID + " FROM " + Table_Name
                            + " WHERE " + COLUMNNAME_Test_ID + "=?";
            int id = DB.getSQLValueEx(trxName, sql, Test_ID);
            return id > 0 && id == Test_ID;

        }

        public MyTestPO(Properties ctx, boolean failOnSave, String trxName) {

            super(ctx, "Test_" + System.currentTimeMillis(), 10);
            this.set_TrxName(trxName);
            this.setDescription("" + getClass());
            this.failOnSave = failOnSave;

        }

        public MyTestPO(Properties ctx, int id, String trxName) {

            super(ctx, id, trxName);

        }

        @Override
        protected boolean afterSave(boolean newRecord, boolean success) {

            if (m_parent == null) {
                m_dependentRecord =
                        new MyTestPO(getCtx(), false, get_TrxName());
                m_dependentRecord.m_parent = this;
                m_dependentRecord.setName("D_" + this.getName());
                m_dependentRecord.saveEx();
            }

            if (this.failOnSave)
                throw new RuntimeException("Never save this object [trxName="
                        + get_TrxName() + ", success=" + success + "]");
            return true;

        }

        public int getDependent_ID() {

            return (m_dependentRecord != null ? m_dependentRecord.get_ID()
                    : -1);

        }

    };

    @BeforeAll
    static void localBeforeAll() {

        bigString = createAHugeStringForTesting();

    }

    @BeforeEach
    void localSetup() {

        testPO = new MTest(getCtx(), getClass().getName(), 1);
        testPO.set_TrxName(getTrxName());

        POInfo info = POInfo.getPOInfo(getCtx(), MTest.Table_ID);
        maxLength =
                info.getFieldLength(info.getColumnIndex(MTest.COLUMNNAME_Name));

    }

    private static String createAHugeStringForTesting() {

        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= 1000; i++) {
            sb.append("0123456789");
        }
        String bigString = sb.toString();
        return bigString;

    }

    static Stream<Arguments> isChangedValueProvider() {

        return Stream.of(
                arguments("a", "", false),
                arguments("a", "-changed", true),
                arguments("Test", "", false),
                arguments("Test", "-changed", true));

    }

    /**
     * Tests the following methods:
     * <ul>
     * <li>{@link org.compiere.model.PO#is_Changed()}
     * <li>{@link org.compiere.model.PO#is_ValueChanged(String)}
     * </ul>
     * Applies to following bugs:
     * <ul>
     * <li>[ 1704828 ] PO.is_Changed() and PO.is_ValueChanged are not consistent
     * </ul>
     * 
     * @throws Exception
     */
    @ParameterizedTest
    @MethodSource("isChangedValueProvider")
    void test_is_Changed(String stringToSet, String changeToAdd,
            boolean expectedFlag) {

        testPO.setHelp(stringToSet);
        testPO.saveEx();
        String info = "testString=[" + stringToSet + changeToAdd + "]"
                + ", originalString=[" + stringToSet + "]";

        testPO.setHelp(stringToSet + changeToAdd);
        assertEquals(expectedFlag,
                testPO.is_ValueChanged(MTest.COLUMNNAME_Help), info);
        assertEquals(expectedFlag, testPO.is_Changed(), info);

    }

    @Test
    void test_is_Changed_whenChangedBackIsFalse() {

        testPO.setHelp("TestString");
        testPO.saveEx();

        testPO.setHelp("ChangedString");
        testPO.setHelp("TestString");

        assertFalse(testPO.is_ValueChanged(MTest.COLUMNNAME_Help),
                "Should be false when changed back");
        assertFalse(testPO.is_Changed(), "Should be false when changed back");

    }

    /**
     * <li>BF [ 1990856 ] PO.set_Value* : truncate string more than needed
     */
    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1 })
    void testTruncatedStrings_set_ValueOfColumn(int truncAmount) {

        String testString;
        if (truncAmount >= 0)
            testString = bigString.substring(0, maxLength - truncAmount);
        else {
            testString = bigString;
            truncAmount = 0;
        }

        testPO.set_ValueOfColumn(MTest.COLUMNNAME_Name, testString);

        String resultString =
                (String) testPO.get_Value(MTest.COLUMNNAME_Name);
        assertEquals(maxLength - truncAmount, resultString.length(),
                "String was not truncated correctly");

    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 1 })
    void testTruncatedStrings_setName(int truncAmount) {

        String testString;
        if (truncAmount >= 0)
            testString = bigString.substring(0, maxLength - truncAmount);
        else {
            testString = bigString;
            truncAmount = 0;
        }

        testPO.setName(testString);
        assertEquals(maxLength - truncAmount, testPO.getName().length(),
                "String was not truncated correctly");

    }

    /**
     * Object should NOT be saved if afterSave fails EVEN if is outside
     * transaction (trxName=null)
     */
    @Test
    void testAfterSaveError_newObjects() {

        MyTestPO test = new MyTestPO(ctx, true, null);
        assertFalse(test.save(), "Object should not be saved -- " + test);
        assertTrue(test.get_ID() > 0, "Object should have ID -- " + test);
        assertFalse(MyTestPO.exists(test.get_ID(), null),
                "Object should not be saved -- " + test);

    }

    //
    @Test
    void testAfterSaveError_oldObjects() {

        MyTestPO test = new MyTestPO(getCtx(), false, trxName);
        test.saveEx();
        int id = test.get_ID();
        MyTestPO test2 = new MyTestPO(getCtx(), id, trxName);
        assertTrue(test2.save(), "Object *should* be saved -- " + test);
        assertTrue(test2.get_ID() > 0, "Object should be saved -- " + test);
        assertTrue(MyTestPO.exists(test2.get_ID(), trxName),
                "Object should be saved -- " + test);

    }

    @Test
    void testAfterSaveError_findExisting() {

        MyTestPO test = new MyTestPO(getCtx(), false, trxName);
        MyTestPO test2 = new MyTestPO(getCtx(), test.get_ID(), trxName);
        assertEquals(test.get_ID(), test2.get_ID(), "Object not found");

    }

    @Test
    void testAfterSaveError_existingChangedAndSaveFails() {

        MyTestPO test = new MyTestPO(getCtx(), false, trxName);
        MyTestPO test2 = new MyTestPO(getCtx(), test.get_ID(), trxName);
        String name = MyTestPO.getName(test2.get_ID(), trxName);
        test2.failOnSave = true;
        test2.setName(test2.getName() + "_2");
        assertFalse(test2.save(), "Object should not be saved -- " + test2);
        assertNotEquals(name, test2.getName(),
                "Object should not be modified -- id=" + test2);

    }

    @Test
    void testSaveEX_success() {

        MyTestPO t1 = new MyTestPO(getCtx(), false, getTrxName());
        t1.saveEx();
        assertTrue(MyTestPO.exists(t1.get_ID(), getTrxName()),
                "Object not found - t1=" + t1);
        assertTrue(MyTestPO.exists(t1.getDependent_ID(), getTrxName()),
                "Object not found - t1(dep)=" + t1);

    }

    @Test
    void testSaveEx_failure() {

        final MyTestPO t2 = new MyTestPO(getCtx(), true, getTrxName());

        assertThrows(AdempiereException.class, () -> {
            t2.saveEx();
        });

        assertFalse(MyTestPO.exists(t2.get_ID(), getTrxName()),
                "Object found - t2=" + t2);
        assertFalse(MyTestPO.exists(t2.getDependent_ID(), getTrxName()),
                "Object found - t2(dep)=" + t2);

    }

    /**
     * If one object fails on after save we should not revert all transaction.
     * BF [ 2849122 ] PO.AfterSave is not rollback on error
     * https://sourceforge.net/tracker/index.php?func=detail&aid=2849122&group_id=176962&atid=879332#
     * 
     * @throws Exception
     */
    @Test
    void afterSave_BF2849122_failedSaveShouldntRollbackPrevious() {

        MyTestPO t1 = new MyTestPO(getCtx(), false, getTrxName());
        t1.saveEx();

        final MyTestPO t2 = new MyTestPO(getCtx(), true, getTrxName());
        try {
            t2.saveEx();
        } catch (Exception e) {
        }
        assertTrue(MyTestPO.exists(t1.get_ID(), getTrxName()),
                "Object not found - t1=" + t1);
        assertTrue(MyTestPO.exists(t1.getDependent_ID(), getTrxName()),
                "Object not found - t1(dep)=" + t1);
        assertFalse(MyTestPO.exists(t2.get_ID(), getTrxName()),
                "Object found - t2=" + t2);
        assertFalse(MyTestPO.exists(t2.getDependent_ID(), getTrxName()),
                "Object found - t2(dep)=" + t2);

    }

    @Test
    void afterSave_BF2849122_successFailSuccessShouldNotCreateFailed() {

        MyTestPO t1 = new MyTestPO(getCtx(), false, getTrxName());
        t1.saveEx();

        final MyTestPO t2 = new MyTestPO(getCtx(), true, getTrxName());
        try {
            t2.saveEx();
        } catch (Exception e) {
        }

        final MyTestPO t3 = new MyTestPO(getCtx(), false, getTrxName());
        t3.saveEx();

        assertTrue(MyTestPO.exists(t1.get_ID(), getTrxName()),
                "Object not found - t1=" + t1);
        assertTrue(MyTestPO.exists(t1.getDependent_ID(), getTrxName()),
                "Object not found - t1(dep)=" + t1);
        assertFalse(MyTestPO.exists(t2.get_ID(), getTrxName()),
                "Object found - t2=" + t2);
        assertFalse(MyTestPO.exists(t2.getDependent_ID(), getTrxName()),
                "Object found - t2(dep)=" + t2);
        assertTrue(MyTestPO.exists(t3.get_ID(), getTrxName()),
                "Object not found - t3=" + t3);
        assertTrue(MyTestPO.exists(t3.getDependent_ID(), getTrxName()),
                "Object not found - t3(dep)=" + t3);

    }

    /**
     * BF [ 2859125 ] Can't set AD_OrgBP_ID
     * https://sourceforge.net/tracker/index.php?func=detail&aid=2859125&group_id=176962&atid=879332#
     */
    @Test
    void testBF2859125_errorReadingAD_OrgBP_ID_Int() {

        MBPartner bp = new MBPartner(getCtx(), 50004, getTrxName()); 
        
        final int old_org_id = bp.getAD_OrgBP_ID_Int();
        int new_org_id = 50005; // Store East Org
        if (old_org_id == new_org_id) {
            new_org_id = 12; // Store Central
        }
        bp.setAD_OrgBP_ID(new_org_id);

        // Following line throws:
        // java.lang.ClassCastException: java.lang.Integer cannot be cast to
        // java.lang.String
        // at
        // org.compiere.model.X_C_BPartner.getAD_OrgBP_ID(X_C_BPartner.java:165)
        // at
        // org.compiere.model.MBPartner.getAD_OrgBP_ID_Int(MBPartner.java:602)
        // at test.functional.POTest.testAD_OrgBP_ID_Issue(POTest.java:192)
        bp.getAD_OrgBP_ID_Int();

        bp.saveEx();

        assertEquals(new_org_id, bp.getAD_OrgBP_ID_Int(),
                "New value not being read correctly");

    }

}
