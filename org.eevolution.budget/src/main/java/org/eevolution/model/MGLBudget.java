package org.eevolution.model;

import org.compiere.model.X_GL_Budget;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created by e-Evolution on 12/11/16.
 */
public class MGLBudget extends X_GL_Budget {
    public MGLBudget(Properties ctx, int GL_Budget_ID, String trxName) {
        super(ctx, GL_Budget_ID, trxName);
    }

    public MGLBudget(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
