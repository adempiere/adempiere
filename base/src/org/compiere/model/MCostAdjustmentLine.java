/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 *****************************************************************************/
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.X_C_CostAdjustmentLine;

/**
 * Cost Adjustment Line — one leg of the inventory / COGS split.
 */
public class MCostAdjustmentLine extends X_C_CostAdjustmentLine {

    private static final long serialVersionUID = 20260908L;

    public static final String ADJUSTMENTLINETYPE_Inventory = "INV";
    public static final String ADJUSTMENTLINETYPE_COGS = "COGS";

    public MCostAdjustmentLine(Properties ctx, int C_CostAdjustmentLine_ID, String trxName) {
        super(ctx, C_CostAdjustmentLine_ID, trxName);
        if (C_CostAdjustmentLine_ID == 0) {
            setLine(10);
        }
    }

    public MCostAdjustmentLine(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
