/**
 *
 */
package org.compiere.FA.process;

import org.compiere.FA.model.MDepreciationWorkfile;
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
public class A_Depreciation_Workfile_Build extends A_Depreciation_Workfile_BuildAbstract {


    protected void prepare() {
        super.prepare();
    }

    protected String doIt() throws Exception {
        AtomicInteger recordsProcessed = new AtomicInteger(0);
        if (isAllAssets()) {
            int[] depreciationWorkfileIds = getDepreciationWorkfileIds();
            Arrays.stream(depreciationWorkfileIds).forEach(id -> Trx.run(trxName -> buildDepreciation(id, recordsProcessed, trxName)));
            return "@Processed@ #" + recordsProcessed.get() + " @of@ " + depreciationWorkfileIds.length;
        } else Trx.run(trxName -> buildDepreciation(getRecord_ID(), recordsProcessed ,trxName));

        return "@Processed@ # "+recordsProcessed.get() +" @of@ 1";
    }

    private int[] getDepreciationWorkfileIds() {
        return new Query(getCtx(), MDepreciationWorkfile.Table_Name, MDepreciationWorkfile.COLUMNNAME_IsDepreciated + "='Y'", get_TrxName()).getIDs();
    }

    private void buildDepreciation(Integer depreciationWorkfileId, AtomicInteger recordProcessed, String trxName) {
        MDepreciationWorkfile depreciationWorkfile = new MDepreciationWorkfile(getCtx(), depreciationWorkfileId, trxName);
        depreciationWorkfile.buildDepreciation();
        depreciationWorkfile.saveEx();
        recordProcessed.getAndUpdate(processed -> processed + 1);
    }
}