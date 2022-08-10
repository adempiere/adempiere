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
package org.eevolution.manufacturing.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;

import org.adempiere.core.domains.models.I_PP_MRP;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * MRP Engine Test Case
 * 
 * @author Teo Sarca, www.arhipac.ro
 */
@Tag("MRP")
@Tag("Model")
@Tag("eevolution")
@Tag("Manufacturing")
class IT_MRP extends CommonGWSetup {

    @Test
    void test01() throws Exception {

        InputStream in = null;
        if (System.getProperty("MRPTestFile") != null) {
            in = new FileInputStream(System.getProperty("MRPTestFile"));
        }
        if (in == null) {
            in = getClass().getClassLoader()
                    .getResourceAsStream("test/functional/mrp/MRPTests.csv");
        }
        if (System.getProperty("UseArhipacURL") != null || in == null) {
            String url =
                    "http://spreadsheets.google.com/pub?key=p_F3GDtQxWTArVGQnNvicVw&output=csv&gid=0";
            in = new URL(url).openStream();
        }
        if (in == null) {
            throw new AdempiereException("No input test file found");
        }

        CSVFactory factory = new CSVFactory();
        Collection<TestableMRP> tests = factory.read(in);

        for (TestableMRP test : tests)
            runTest(test);

    }

    private void runTest(TestableMRP test) {

        boolean ok = false;
        test.trxName = getTrxName();
        try {
            try {
                test.doIt();
            } catch (Exception e) {
                fail("Unable to processTest: " + e.getMessage());
            }
            test.dumpStatus();
            //
            assertEquals(test.expectedMRP.size(), test.actualMRP.size(),
                    test.name + ": MRP Records# not match");
            for (int i = 0; i < test.expectedMRP.size(); i++) {
                assertMRPRecordsAreEqual(test.expectedMRP.get(i),
                        test.actualMRP.get(i),
                        test.name + ": MRP Record not match");
            }
            //
            assertEquals(test.expectedNotices.size(),
                    test.actualNotices.size());
            for (int i = 0; i < test.expectedNotices.size(); i++) {
                assertEquals(test.expectedNotices.get(i),
                        test.actualNotices.get(i),
                        test.name + ": MRP Record not match");
            }
            //
            ok = true;
        } finally {
            if (!ok) {
                System.err.println(
                        "ERRROR_______________________________________");
                test.dumpStatus();
            }
        }

    }

    private void assertMRPRecordsAreEqual(I_PP_MRP expected, I_PP_MRP actual,
            String message) {

        boolean equals = expected.getAD_Client_ID() == actual.getAD_Client_ID()
                && expected.getAD_Org_ID() == actual.getAD_Org_ID()
                && expected.getM_Warehouse_ID() == actual.getM_Warehouse_ID()
                && expected.getM_Product_ID() == actual.getM_Product_ID()
                && expected.getQty().equals(actual.getQty())
                && expected.getTypeMRP().equals(actual.getTypeMRP())
                && expected.getDocStatus().equals(actual.getDocStatus())
                && expected.getDatePromised().equals(actual.getDatePromised())
                && expected.getDateStartSchedule()
                        .equals(actual.getDateStartSchedule())
                && expected.getDateFinishSchedule()
                        .equals(actual.getDateFinishSchedule())
                && expected.getDateOrdered().equals(actual.getDateOrdered());
        //
        StringBuffer sb = new StringBuffer(message)
                .append(": expected=" + expected)
                .append(", actual=" + actual);

        assertTrue(equals, sb.toString());

    }

}
