/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 *****************************************************************************/
package org.adempiere.core.domains.models;

import java.math.BigDecimal;

/** Generated Interface for C_CostAdjustmentLine (manual stub) */
public interface I_C_CostAdjustmentLine {

    String Table_Name = "C_CostAdjustmentLine";

    int Table_ID = 54002;

    String COLUMNNAME_C_CostAdjustmentLine_ID = "C_CostAdjustmentLine_ID";
    String COLUMNNAME_C_CostAdjustmentEvent_ID = "C_CostAdjustmentEvent_ID";
    String COLUMNNAME_AD_Client_ID = "AD_Client_ID";
    String COLUMNNAME_AD_Org_ID = "AD_Org_ID";
    String COLUMNNAME_Line = "Line";
    String COLUMNNAME_AdjustmentLineType = "AdjustmentLineType";
    String COLUMNNAME_Amt = "Amt";
    String COLUMNNAME_Qty = "Qty";
    String COLUMNNAME_Description = "Description";
    String COLUMNNAME_IsActive = "IsActive";

    int getC_CostAdjustmentLine_ID();

    int getC_CostAdjustmentEvent_ID();

    BigDecimal getAmt();
}
