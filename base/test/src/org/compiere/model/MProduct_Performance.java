package org.compiere.model;

import static org.adempiere.test.TestUtilities.randomString;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.test.CommonGWData;
import org.compiere.Adempiere;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Login;
import org.compiere.util.Trx;

public class MProduct_Performance {

    private static Properties ctx = null;
    private Trx trx = null;
    private MProduct product = null;
    private String testNumber = "all";
    private static int C_UOM_ID;
    private static CLogger log = CLogger.getCLogger(MOrder_Performance.class);
    private NumberFormat formatter = new DecimalFormat("00000");

    public static void main(String[] args) {

        int numberOfTests = 20;
        boolean singleTransaction = false;

        setupEnvironment();
        MProduct_Performance test = new MProduct_Performance();
        test.testMProductCreation(numberOfTests, singleTransaction);

    }

    private static void setupEnvironment() {

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

        ctx = Env.getCtx();
        C_UOM_ID = MUOM.getDefault_UOM_ID(ctx);

        CLogMgt.setLoggerLevel(Level.WARNING, null);
        CLogMgt.setLevel(Level.WARNING);

    }

    private void testMProductCreation(int numberOfTests,
            boolean oneTrxPerTest) {

        String randomString = randomString(4);

        long startTime = reportStart(oneTrxPerTest);
        if (!oneTrxPerTest)
            createAndStartTransaction();

        for (int idx = 1; idx <= numberOfTests; idx++)
            createSingleProductTest(idx, randomString, oneTrxPerTest);

        if (!oneTrxPerTest)
            commitAndCloseTransaction();

        reportResults(startTime, numberOfTests);

    }

    private long reportStart(boolean oneTrxPerTest) {

        long startTime = System.currentTimeMillis();
        log.warning("Start Time = " + new Timestamp(startTime));
        log.warning("One Transaction Per Test: " + oneTrxPerTest);
        log.warning("Running ...");
        return startTime;

    }

    private void reportResults(long startTime, int count) {
    
        long duration = System.currentTimeMillis() - startTime;
        log.warning("... Finished");
        log.warning("Number of products=" + count
                + ". Total duration " + duration + "ms. Time per product "
                + ((float) duration / count + ".")
                + "ms");
    
    }

    private void createSingleProductTest(int index, String uniqueName,
            boolean oneTrxPerTest) {

        if (oneTrxPerTest) {
            createAndStartTransaction();
            testNumber = String.valueOf(index);
        }

        String formattedIdx = formatter.format(index);
        final String valueOrName =
                "Test-Product-" + uniqueName + formattedIdx;

        if (!createProduct(valueOrName))
            log.warning("Product not saved!");

        if (oneTrxPerTest)
            commitAndCloseTransaction();

    }

    private boolean createProduct(final String valueOrName) {

        product = new MProduct(ctx, 0, trx.getTrxName());
        product.setValue(valueOrName);
        product.setName(valueOrName);
        product.setAD_Org_ID(0);
        product.setM_Product_Category_ID(
                CommonGWData.PRODUCT_CATEGORY_STANDARD_ID);
        product.setC_TaxCategory_ID(CommonGWData.TAX_CATEGORY_STANDARD_ID);
        product.setProductType(MProduct.PRODUCTTYPE_Item);
        product.setC_UOM_ID(C_UOM_ID);
        boolean saveResult = product.save();
        return saveResult;

    }

    private void commitAndCloseTransaction() {

        trx.commit();
        trx.close();
        trx = null;

    }

    private void createAndStartTransaction() {

        trx = Trx.get(Trx.createTrxName("Test_" + testNumber), true);
        trx.start();

    }

}
