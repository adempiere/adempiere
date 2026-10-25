/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2026 Adempiere Foundation. All Rights Reserved.          *
 *****************************************************************************/
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

/** Generated Interface for C_CostAdjustmentEvent (manual stub, dictionary synced by migration) */
public interface I_C_CostAdjustmentEvent {

    String Table_Name = "C_CostAdjustmentEvent";

    int Table_ID = 54001;

    String COLUMNNAME_C_CostAdjustmentEvent_ID = "C_CostAdjustmentEvent_ID";
    String COLUMNNAME_AD_Client_ID = "AD_Client_ID";
    String COLUMNNAME_AD_Org_ID = "AD_Org_ID";
    String COLUMNNAME_DocumentNo = "DocumentNo";
    String COLUMNNAME_C_DocType_ID = "C_DocType_ID";
    String COLUMNNAME_DateAcct = "DateAcct";
    String COLUMNNAME_DateTrx = "DateTrx";
    String COLUMNNAME_M_Product_ID = "M_Product_ID";
    String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";
    String COLUMNNAME_M_CostType_ID = "M_CostType_ID";
    String COLUMNNAME_M_CostElement_ID = "M_CostElement_ID";
    String COLUMNNAME_CostAdjustmentType = "CostAdjustmentType";
    String COLUMNNAME_M_InOutLine_ID = "M_InOutLine_ID";
    String COLUMNNAME_C_InvoiceLine_ID = "C_InvoiceLine_ID";
    String COLUMNNAME_C_LandedCostAllocation_ID = "C_LandedCostAllocation_ID";
    String COLUMNNAME_M_CostDetail_ID = "M_CostDetail_ID";
    String COLUMNNAME_OldCostPrice = "OldCostPrice";
    String COLUMNNAME_NewCostPrice = "NewCostPrice";
    String COLUMNNAME_QtyOnHand = "QtyOnHand";
    String COLUMNNAME_QtyAlreadySold = "QtyAlreadySold";
    String COLUMNNAME_AdjustmentAmt = "AdjustmentAmt";
    String COLUMNNAME_InventoryAmt = "InventoryAmt";
    String COLUMNNAME_COGSAmt = "COGSAmt";
    String COLUMNNAME_Description = "Description";
    String COLUMNNAME_Processed = "Processed";
    String COLUMNNAME_Posted = "Posted";
    String COLUMNNAME_DocStatus = "DocStatus";
    String COLUMNNAME_DocAction = "DocAction";
    String COLUMNNAME_IsActive = "IsActive";

    int getC_CostAdjustmentEvent_ID();

    String getDocumentNo();

    Timestamp getDateAcct();

    Timestamp getDateTrx();

    int getM_Product_ID();

    int getC_AcctSchema_ID();

    BigDecimal getAdjustmentAmt();

    BigDecimal getInventoryAmt();

    BigDecimal getCOGSAmt();

    boolean isProcessed();

    String getDocStatus();
}
