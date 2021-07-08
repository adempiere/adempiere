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
package org.adempiere.test;

import static org.adempiere.test.TestUtilities.randomString;
import static org.compiere.Adempiere.startup;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.MUOM;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class CommonGWSetup extends CommonIntegrationTestUtilities
        implements IntegrationTestTag {

    protected static Properties ctx;
    protected static final int AD_USER_ID = CommonGWData.AD_USER_ID;
    protected static final int AD_CLIENT_ID = CommonGWData.AD_CLIENT_ID;
    protected static final int AD_ORG_ID = CommonGWData.AD_ORG_ID;
    protected static final boolean IS_CLIENT = CommonGWData.IS_CLIENT;
    protected static final int SEEDFARM_ID = CommonGWData.SEEDFARM_ID;
    protected static final int SEEDFARM_LOCATION_ID =
            CommonGWData.SEEDFARM_LOCATION_ID;
    protected static final int TAX_CATEGORY_STANDARD_ID =
            CommonGWData.TAX_CATEGORY_STANDARD_ID;

    protected static String trxName = null;
    protected static Trx trx = null;
    protected static Savepoint mainSavepoint = null;
    protected Savepoint testSavepoint = null;
    protected static Timestamp today = null;
    protected static int DEFAULT_UOM_ID;

    @BeforeAll
    public static void setUpBeforeClass() {

        today = TimeUtil.getDay(System.currentTimeMillis());
        ctx = Env.getCtx();
        ctx.setProperty("#AD_Org_ID", Integer.toString(AD_ORG_ID));
        ctx.setProperty("#AD_User_ID", Integer.toString(AD_USER_ID));
        ctx.setProperty("#AD_Client_ID", Integer.toString(AD_CLIENT_ID));
        ctx.setProperty("#Date",
                TimeUtil.getDay(System.currentTimeMillis()).toString());
        ctx.setProperty("#AD_Language", "en");

        Ini.setClient(IS_CLIENT);
        Ini.loadProperties(false);
        startup(IS_CLIENT);

        DEFAULT_UOM_ID = MUOM.getDefault_UOM_ID(ctx);

    }

    @AfterAll
    public static void tearDownAfterClass() {

        ctx = null;

    }

    @BeforeEach
    public void setUp() {

        trxName = Trx.createTrxName("TestRun_" + randomString(4));
        trx = Trx.get(trxName, false);

        try {
            testSavepoint = trx.setSavepoint("SingleTest_" + randomString(4));
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @AfterEach
    public void tearDown() {

        if (trx != null && !trx.isActive())
            trx.start();
        
        try {
            tryToRollback(testSavepoint);
        } catch (Exception e) {
        }finally {
            trx.close();
            trx = null;
        }

    }

    private static void tryToRollback(Savepoint savePoint) throws SQLException {

        if (trx != null && trx.isActive() && savePoint != null) {
            trx.rollback(savePoint);
        }

    }

    public String get_TrxName() {

        return trxName;

    }

    public String getTrxName() {

        return trxName;

    }

    public Properties getCtx() {

        return ctx;

    }

}
