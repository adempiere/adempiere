/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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

import static org.adempiere.test.TestUtilities.randomString;

import java.math.BigDecimal;
import java.util.Random;
import java.util.logging.Level;

import org.adempiere.test.CommonGWData;
import org.compiere.Adempiere;
import org.compiere.process.DocAction;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Login;
import org.compiere.util.Trx;

/**
 * Order Test Example
 * 
 * @author Jorg Janke
 * @version $Id: OrderTest.java,v 1.2 2006/07/30 00:51:57 jjanke Exp $
 */
class MOrder_Performance implements Runnable {

    private int threadNo = 0;
    private int numberOfOrders = 5;
    private int maxLines = 20;
    private int errorCount = 0;
    private Trx trx = null;

    private static CLogger log = CLogger.getCLogger(MOrder_Performance.class);

    public MOrder_Performance(int testSerNo, int numberOfOrders, int maxLines) {

        this.threadNo = testSerNo;
        this.numberOfOrders = numberOfOrders;
        this.maxLines = maxLines;

    }

    public static void main(String[] args) {

        int NUMBER_OF_TESTS = 2;
        int NUMBER_OF_ORDERS_PER_TEST = 3;
        int MAX_LINES_PER_ORDER = 20;

        Adempiere.startup(true);
        CLogMgt.setLoggerLevel(Level.INFO, null);
        CLogMgt.setLevel(Level.INFO);

        Ini.setProperty(Ini.P_UID, "SuperUser");
        Ini.setProperty(Ini.P_PWD, "System");
        Ini.setProperty(Ini.P_ROLE, "GardenWorld Admin");
        Ini.setProperty(Ini.P_CLIENT, "GardenWorld");
        Ini.setProperty(Ini.P_ORG, "HQ");
        Ini.setProperty(Ini.P_WAREHOUSE, "HQ Warehouse");
        Ini.setProperty(Ini.P_LANGUAGE, "English");
        Login login = new Login(Env.getCtx());
        if (!login.batchLogin(null))
            System.exit(1);

        CLogMgt.setLoggerLevel(Level.WARNING, null);
        CLogMgt.setLevel(Level.WARNING);

        configureBPartner();

        long time = System.currentTimeMillis();
        Thread[] tests = new Thread[NUMBER_OF_TESTS];
        for (int i = 0; i < tests.length; i++) {
            tests[i] = new Thread(
                    new MOrder_Performance(i, NUMBER_OF_ORDERS_PER_TEST,
                            MAX_LINES_PER_ORDER));
            tests[i].start();
        }
        // Wait
        for (int i = 0; i < tests.length; i++) {
            try {
                tests[i].join();
            } catch (InterruptedException e) {
                log.severe("Could not join test " + i);
            }
        }
        time = System.currentTimeMillis() - time;

        log.warning("Time (ms)=" + time);

    }

    private static void configureBPartner() {

        MBPartner bp = new MBPartner(Env.getCtx(), 117, null);
        bp.setSOCreditStatus(MBPartner.SOCREDITSTATUS_NoCreditCheck);
        bp.saveEx();

    }

    public void run() {

        long time = System.currentTimeMillis();
        int count = 0;
        String randomDocNo = randomString(4);

        for (int i = 0; i < numberOfOrders; i++) {

            createAndStartTransaction();
            MOrder order = createOrderHeader(i, randomDocNo);
            createRandomNumberOfLines(order);
            processOrder(order);
            commitAndCloseTransaction();
            log.warning("Thread " + threadNo + ": " + order.toString());
            count++;

        }
        reportResults(time, count);

    }

    private void processOrder(MOrder order) {

        order.setDocAction(DocAction.ACTION_Complete);
        if (!order.processIt(DocAction.ACTION_Complete)) {
            errorCount++;
        }
        order.saveEx();

    }

    private void createRandomNumberOfLines(MOrder order) {

        Random r = new Random();
        int linesNumber = r.nextInt(maxLines) + 1;
        for (int j = 0; j < linesNumber; j++) {
            MOrderLine line = new MOrderLine(order);
            line.setM_Product_ID(123); // Oak Tree
            line.setQty(new BigDecimal(5));
            line.saveEx();
        }

    }

    private MOrder createOrderHeader(int testNo, String randomDocNo) {

        MOrder order = new MOrder(Env.getCtx(), 0, trx.getTrxName());
        order.setDocumentNo("Test " + testNo + " " + randomDocNo);
        order.setDescription("#" + threadNo + "_" + testNo);
        order.setC_DocTypeTarget_ID(135); // POS
        order.setC_BPartner_ID(117); // C&W
        order.setSalesRep_ID(101); // GardenAdmin
        order.setM_Warehouse_ID(CommonGWData.HQ_WAREHOUSE_ID);
        order.setDeliveryRule(MOrder.DELIVERYRULE_Force);
        order.saveEx();
        return order;

    }

    private void reportResults(long startTime, int count) {

        long duration = System.currentTimeMillis() - startTime;
        log.warning("#" + threadNo + ", Errors=" + errorCount
                + ", Count=" + count
                + " " + ((float) count * 100 / numberOfOrders)
                + "% - " + duration + "ms - ea " + ((float) duration / count)
                + "ms");

    }

    private void commitAndCloseTransaction() {

        trx.commit();
        trx.close();
        trx = null;

    }

    private void createAndStartTransaction() {

        trx = Trx.get(Trx.createTrxName("Test_" + threadNo), true);
        trx.start();

    }

}
