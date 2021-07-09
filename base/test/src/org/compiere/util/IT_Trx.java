/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
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
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MTest;
import org.compiere.model.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test {@link Trx} class
 * 
 * @author Teo Sarca, http://www.arhipac.ro
 */
@Tag("Trx")
class IT_Trx extends CommonGWSetup {

    private int testId = -1;

    class TestRunnable implements TrxRunnable {

        private boolean throwException = false;

        public TestRunnable(boolean fail) {

            throwException = fail;

        }

        public void run(String trxName) {

            testId = createTest(trxName).get_ID();
            if (throwException)
                throw new AdempiereException("FORCE");

        }

    }

    private void runTestOutsideTrx(boolean fail) {

        Trx.run(new TestRunnable(fail));

    }

    private void runTestInsideTrx(String trxName, boolean fail) {

        Trx.run(trxName, new TestRunnable(fail));

    }

    private final MTest createTest(String trxName) {

        MTest test = new MTest(getCtx(), "test-" + getClass(), 10);
        test.set_TrxName(trxName);
        test.saveEx();
        return test;

    }

    private void assertTestExists(int test_id, boolean existsTarget,
            String trxName) {

        String whereClause = MTest.COLUMNNAME_Test_ID + "=?";
        boolean exists =
                new Query(getCtx(), MTest.Table_Name, whereClause, trxName)
                        .setParameters(new Object[] { test_id })
                        .match();
        assertEquals(existsTarget, exists, "Test " + test_id + " [trxName="
                + trxName + "] - existance issue");

    }
    
    private void assertTestExists(int test_id, String trxName) {
        assertTestExists(test_id, true, trxName);
    }

    private void assertTestDoesNotExists(int test_id, String trxName) {
        assertTestExists(test_id, false, trxName);
    }

    @BeforeEach
    void localSetup() {

        testId = -1;

    }

    @Test
    void runTrxRunnable_outsideTrx_success() {

        runTestOutsideTrx(false);
        assertTestExists(testId, null);
        new MTest(getCtx(), testId, null).deleteEx(true);

    }

    @Test
    void runTrxRunnable_outsideTrx_fails() {

        assertThrows(AdempiereException.class, () -> {
            runTestOutsideTrx(true);

        });
        assertTestDoesNotExists(testId, null);

    }

    @Test
    void runTrxRunnable_insideTrx_success() {

        MTest test1 = createTest(trxName);
        runTestInsideTrx(trxName, false);
        assertTestExists(testId, trxName);
        assertTestExists(test1.get_ID(), trxName);

    }

    @Test
    void runTrxRunnable_insideTrx_fails() {

        MTest test1 = createTest(trxName);
        assertThrows(AdempiereException.class, () -> {
            runTestInsideTrx(trxName, true);
        });
        assertTestDoesNotExists(testId, trxName);
        assertTestExists(test1.get_ID(), trxName);

    }

}
