/**
 *
 */
package org.compiere.asset.process;

import org.compiere.asset.model.MDepreciationWorkfile;
import org.compiere.model.Query;
import org.compiere.util.Trx;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Create Depreciation
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * @author Victor Perez , victor.perez@e-evolution.com e-Evolution Consultant
 * <li> #1803 Allows evaluate of default value based on the other parameter context
 * <li> https://github.com/adempiere/adempiere/issues/1803
 */
public class DepreciationWorkfileBuild extends DepreciationWorkfileBuildAbstract {


    protected void prepare() {
        super.prepare();
    }

    protected String doIt() throws Exception {
        AtomicInteger recordsProcessed = new AtomicInteger(0);
        if (isAllAssets()) {
            int[] depreciationWorkFileIds = getDepreciationWorkFileIds();
            Arrays.stream(depreciationWorkFileIds).forEach(id -> Trx.run(trxName -> buildDepreciation(id, recordsProcessed, trxName)));
            return "@Processed@ #" + recordsProcessed.get() + " @of@ " + depreciationWorkFileIds.length;
        } else Trx.run(trxName -> buildDepreciation(getRecord_ID(), recordsProcessed ,trxName));

        return "@Processed@ # "+recordsProcessed.get() +" @of@ 1";
    }

    private int[] getDepreciationWorkFileIds() {
        return new Query(getCtx(), MDepreciationWorkfile.Table_Name, MDepreciationWorkfile.COLUMNNAME_IsDepreciated + "='Y'", get_TrxName())
                .setClient_ID()
                .getIDs();
    }

    private void buildDepreciation(Integer depreciationWorkFileId, AtomicInteger recordProcessed, String trxName) {
        MDepreciationWorkfile depreciationWorkFile = new MDepreciationWorkfile(getCtx(), depreciationWorkFileId, trxName);
        depreciationWorkFile.buildDepreciation();
        depreciationWorkFile.saveEx();
        recordProcessed.getAndUpdate(processed -> processed + 1);
    }
}