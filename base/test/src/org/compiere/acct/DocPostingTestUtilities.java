package org.compiere.acct;

import java.sql.Timestamp;
import java.util.Properties;
import java.util.stream.Stream;

import org.adempiere.core.domains.models.I_AD_SysConfig;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.process.FactAcctReset;
import org.compiere.util.TimeUtil;
import org.eevolution.services.dsl.ProcessBuilder;

public class DocPostingTestUtilities {

    private static final String CLIENT_ACCOUNTING_IMMEDIATE = "I";
    private static final String CLIENT_ACCOUNTING_DISABLED = "D";

    private boolean createdNew;
    private int initialClientAcctConfigId;
    private String initialClientAcctValue;

    private void setInitialConfigId(int id) {

        initialClientAcctConfigId = id;

    }

    private void setInitialConfigValue(String value) {

        initialClientAcctValue = value;

    }

    public DocPostingTestUtilities() {

    }

    private Stream<MSysConfig> clientAccountingConfigs(Properties ctx,
            int clientId, String trxName) {

        String where = "Name=? AND AD_Client_ID IN (0, ?)";
        return new Query(ctx, I_AD_SysConfig.Table_Name, where, trxName)
                .setOnlyActiveRecords(true)
                .setParameters("CLIENT_ACCOUNTING", clientId)
                .setOrderBy("AD_Client_ID DESC, AD_Org_ID DESC")
                .list(MSysConfig.class)
                .stream();

    }

    public MSysConfig getOrCreateClientAcctConfig(Properties ctx, int clientId,
            String trxName) {

        createdNew = false;
        MSysConfig gwAccounting =
                clientAccountingConfigs(ctx, clientId, trxName)
                        .filter(config -> config.getAD_Client_ID() == clientId)
                        .findFirst()
                        .orElseGet(() -> {
                            MSysConfig config = new MSysConfig(ctx, 0, trxName);
                            config.setName("CLIENT_ACCOUNTING");
                            createdNew = true;
                            return config;
                        });
        gwAccounting.saveEx();
        setInitialConfigId(gwAccounting.get_ID());
        setInitialConfigValue(gwAccounting.getValue());
        return gwAccounting;

    }

    public void disableClientAccounting(Properties ctx, int clientId,
            String trxName) {

        MSysConfig gwAccounting =
                getOrCreateClientAcctConfig(ctx, clientId, trxName);
        gwAccounting.setValue(CLIENT_ACCOUNTING_DISABLED);
        gwAccounting.saveEx();

    }

    public void setClientAccounting(Properties ctx, int clientId,
            String acctType, String trxName) {

        MSysConfig gwAccounting =
                getOrCreateClientAcctConfig(ctx, clientId, trxName);
        gwAccounting.setValue(acctType);
        gwAccounting.saveEx();

    }

    public void resetClientAccounting(Properties ctx, int clientId,
            String trxName) {

        resetClientAccounting(ctx, clientId, 0, trxName);

    }

    public void resetClientAccounting(Properties ctx, int clientId, int tableId,
            String trxName) {

        ProcessBuilder.create(ctx)
                .process(org.compiere.process.FactAcctReset.class)
                .withTitle("FactAcctReset")
                .withParameter(FactAcctReset.AD_CLIENT_ID, clientId)
                .withParameter(FactAcctReset.DELETEPOSTING, true)
                .withParameter(FactAcctReset.AD_TABLE_ID, tableId)
                .withParameter(FactAcctReset.DATEACCT,
                        TimeUtil.getDay(1999, 01, 01),
                        new Timestamp(System.currentTimeMillis()))
                .execute(trxName);

    }

    public void resetInitialConfig(Properties ctx, String trxName) {

        if (createdNew) {
            MSysConfig config =
                    new MSysConfig(ctx, initialClientAcctConfigId, trxName);
            config.deleteEx(true);
        } else {
            resetInitialConfigValue(ctx, trxName);
        }

    }

    public void resetInitialConfigValue(Properties ctx, String trxName) {

        MSysConfig config =
                new MSysConfig(ctx, initialClientAcctConfigId, trxName);
        config.setValue(initialClientAcctValue);
        config.saveEx();

    }

}
