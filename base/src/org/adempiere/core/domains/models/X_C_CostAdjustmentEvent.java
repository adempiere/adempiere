/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2026 ADempiere Foundation, All Rights Reserved.         *
 * Generated Model - DO NOT CHANGE (manual stub for Cost Adjustment Event)    *
 *****************************************************************************/
/** Generated Model for C_CostAdjustmentEvent */
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;

/** Generated Model for C_CostAdjustmentEvent
 *  @author Adempiere (generated)
 *  @version Release 3.9.4 - $Id$ */
public class X_C_CostAdjustmentEvent extends PO implements I_C_CostAdjustmentEvent, I_Persistent {

    private static final long serialVersionUID = 20260908L;

    public static final int Table_ID = I_C_CostAdjustmentEvent.Table_ID;
    public static final String Table_Name = I_C_CostAdjustmentEvent.Table_Name;

    /** DocStatus */
    public static final String DOCSTATUS_Drafted = "DR";
    public static final String DOCSTATUS_Completed = "CO";
    public static final String DOCSTATUS_Approved = "AP";
    public static final String DOCSTATUS_Invalid = "IN";
    public static final String DOCSTATUS_NotApproved = "NA";
    public static final String DOCSTATUS_Voided = "VO";
    public static final String DOCSTATUS_Reversed = "RE";
    public static final String DOCSTATUS_Closed = "CL";
    public static final String DOCSTATUS_Unknown = "??";
    public static final String DOCSTATUS_InProgress = "IP";
    public static final String DOCSTATUS_WaitingPayment = "WP";
    public static final String DOCSTATUS_WaitingConfirmation = "WC";
    /** DocAction */
    public static final String DOCACTION_Complete = "CO";
    public static final String DOCACTION_WaitComplete = "WC";
    public static final String DOCACTION_Approve = "AP";
    public static final String DOCACTION_Reject = "RJ";
    public static final String DOCACTION_Post = "PO";
    public static final String DOCACTION_Void = "VO";
    public static final String DOCACTION_Close = "CL";
    public static final String DOCACTION_Reverse_Correct = "RC";
    public static final String DOCACTION_Reverse_Accrual = "RA";
    public static final String DOCACTION_ReActivate = "RE";
    public static final String DOCACTION_None = "--";
    public static final String DOCACTION_Prepare = "PR";
    public static final String DOCACTION_Unlock = "XL";
    public static final String DOCACTION_Invalidate = "IN";
    public static final String DOCACTION_ReOpen = "OP";

    public X_C_CostAdjustmentEvent(Properties ctx, int C_CostAdjustmentEvent_ID, String trxName) {
        super(ctx, C_CostAdjustmentEvent_ID, trxName);
    }

    public X_C_CostAdjustmentEvent(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    protected int get_AccessLevel() {
        return 3;
    }

    protected POInfo initPO(Properties ctx) {
        POInfo poi = POInfo.getPOInfo(ctx, Table_ID, get_TrxName());
        return poi;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("X_C_CostAdjustmentEvent[").append(get_ID()).append("]");
        return sb.toString();
    }

    public void setC_CostAdjustmentEvent_ID(int C_CostAdjustmentEvent_ID) {
        if (C_CostAdjustmentEvent_ID < 1)
            set_ValueNoCheck(COLUMNNAME_C_CostAdjustmentEvent_ID, null);
        else
            set_ValueNoCheck(COLUMNNAME_C_CostAdjustmentEvent_ID, Integer.valueOf(C_CostAdjustmentEvent_ID));
    }

    public int getC_CostAdjustmentEvent_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_C_CostAdjustmentEvent_ID);
        if (ii == null)
            return 0;
        return ii.intValue();
    }

    public void setDocumentNo(String DocumentNo) {
        set_Value(COLUMNNAME_DocumentNo, DocumentNo);
    }

    public String getDocumentNo() {
        return (String) get_Value(COLUMNNAME_DocumentNo);
    }

    public void setC_DocType_ID(int C_DocType_ID) {
        set_Value(COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
    }

    public int getC_DocType_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_C_DocType_ID);
        if (ii == null)
            return 0;
        return ii.intValue();
    }

    public void setDateAcct(Timestamp DateAcct) {
        set_Value(COLUMNNAME_DateAcct, DateAcct);
    }

    public Timestamp getDateAcct() {
        return (Timestamp) get_Value(COLUMNNAME_DateAcct);
    }

    public void setDateTrx(Timestamp DateTrx) {
        set_Value(COLUMNNAME_DateTrx, DateTrx);
    }

    public Timestamp getDateTrx() {
        return (Timestamp) get_Value(COLUMNNAME_DateTrx);
    }

    public void setM_Product_ID(int M_Product_ID) {
        set_Value(COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
    }

    public int getM_Product_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_M_Product_ID);
        if (ii == null)
            return 0;
        return ii.intValue();
    }

    public void setC_AcctSchema_ID(int C_AcctSchema_ID) {
        set_Value(COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
    }

    public int getC_AcctSchema_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_C_AcctSchema_ID);
        if (ii == null)
            return 0;
        return ii.intValue();
    }

    public void setM_CostType_ID(int M_CostType_ID) {
        set_Value(COLUMNNAME_M_CostType_ID, Integer.valueOf(M_CostType_ID));
    }

    public int getM_CostType_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_M_CostType_ID);
        if (ii == null)
            return 0;
        return ii.intValue();
    }

    public void setM_CostElement_ID(int M_CostElement_ID) {
        set_Value(COLUMNNAME_M_CostElement_ID, Integer.valueOf(M_CostElement_ID));
    }

    public int getM_CostElement_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_M_CostElement_ID);
        if (ii == null)
            return 0;
        return ii.intValue();
    }

    public void setCostAdjustmentType(String CostAdjustmentType) {
        set_Value(COLUMNNAME_CostAdjustmentType, CostAdjustmentType);
    }

    public String getCostAdjustmentType() {
        return (String) get_Value(COLUMNNAME_CostAdjustmentType);
    }

    public void setM_InOutLine_ID(int M_InOutLine_ID) {
        set_Value(COLUMNNAME_M_InOutLine_ID, Integer.valueOf(M_InOutLine_ID));
    }

    public int getM_InOutLine_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_M_InOutLine_ID);
        if (ii == null)
            return 0;
        return ii.intValue();
    }

    public void setC_InvoiceLine_ID(int C_InvoiceLine_ID) {
        set_Value(COLUMNNAME_C_InvoiceLine_ID, Integer.valueOf(C_InvoiceLine_ID));
    }

    public int getC_InvoiceLine_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_C_InvoiceLine_ID);
        if (ii == null)
            return 0;
        return ii.intValue();
    }

    public void setC_LandedCostAllocation_ID(int C_LandedCostAllocation_ID) {
        set_Value(COLUMNNAME_C_LandedCostAllocation_ID, Integer.valueOf(C_LandedCostAllocation_ID));
    }

    public int getC_LandedCostAllocation_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_C_LandedCostAllocation_ID);
        if (ii == null)
            return 0;
        return ii.intValue();
    }

    public void setM_CostDetail_ID(int M_CostDetail_ID) {
        set_Value(COLUMNNAME_M_CostDetail_ID, Integer.valueOf(M_CostDetail_ID));
    }

    public int getM_CostDetail_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_M_CostDetail_ID);
        if (ii == null)
            return 0;
        return ii.intValue();
    }

    public void setOldCostPrice(BigDecimal OldCostPrice) {
        set_Value(COLUMNNAME_OldCostPrice, OldCostPrice);
    }

    public BigDecimal getOldCostPrice() {
        BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_OldCostPrice);
        if (bd == null)
            return Env.ZERO;
        return bd;
    }

    public void setNewCostPrice(BigDecimal NewCostPrice) {
        set_Value(COLUMNNAME_NewCostPrice, NewCostPrice);
    }

    public BigDecimal getNewCostPrice() {
        BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_NewCostPrice);
        if (bd == null)
            return Env.ZERO;
        return bd;
    }

    public void setQtyOnHand(BigDecimal QtyOnHand) {
        set_Value(COLUMNNAME_QtyOnHand, QtyOnHand);
    }

    public BigDecimal getQtyOnHand() {
        BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_QtyOnHand);
        if (bd == null)
            return Env.ZERO;
        return bd;
    }

    public void setQtyAlreadySold(BigDecimal QtyAlreadySold) {
        set_Value(COLUMNNAME_QtyAlreadySold, QtyAlreadySold);
    }

    public BigDecimal getQtyAlreadySold() {
        BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_QtyAlreadySold);
        if (bd == null)
            return Env.ZERO;
        return bd;
    }

    public void setAdjustmentAmt(BigDecimal AdjustmentAmt) {
        set_Value(COLUMNNAME_AdjustmentAmt, AdjustmentAmt);
    }

    public BigDecimal getAdjustmentAmt() {
        BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_AdjustmentAmt);
        if (bd == null)
            return Env.ZERO;
        return bd;
    }

    public void setInventoryAmt(BigDecimal InventoryAmt) {
        set_Value(COLUMNNAME_InventoryAmt, InventoryAmt);
    }

    public BigDecimal getInventoryAmt() {
        BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_InventoryAmt);
        if (bd == null)
            return Env.ZERO;
        return bd;
    }

    public void setCOGSAmt(BigDecimal COGSAmt) {
        set_Value(COLUMNNAME_COGSAmt, COGSAmt);
    }

    public BigDecimal getCOGSAmt() {
        BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_COGSAmt);
        if (bd == null)
            return Env.ZERO;
        return bd;
    }

    public void setDescription(String Description) {
        set_Value(COLUMNNAME_Description, Description);
    }

    public String getDescription() {
        return (String) get_Value(COLUMNNAME_Description);
    }

    public void setProcessed(boolean Processed) {
        set_Value(COLUMNNAME_Processed, Boolean.valueOf(Processed));
    }

    public boolean isProcessed() {
        Object oo = get_Value(COLUMNNAME_Processed);
        if (oo == null)
            return false;
        return ((Boolean) oo).booleanValue();
    }

    public void setPosted(boolean Posted) {
        set_Value(COLUMNNAME_Posted, Boolean.valueOf(Posted));
    }

    public boolean isPosted() {
        Object oo = get_Value(COLUMNNAME_Posted);
        if (oo == null)
            return false;
        return ((Boolean) oo).booleanValue();
    }

    public void setDocStatus(String DocStatus) {
        set_Value(COLUMNNAME_DocStatus, DocStatus);
    }

    public String getDocStatus() {
        return (String) get_Value(COLUMNNAME_DocStatus);
    }

    public void setDocAction(String DocAction) {
        set_Value(COLUMNNAME_DocAction, DocAction);
    }

    public String getDocAction() {
        return (String) get_Value(COLUMNNAME_DocAction);
    }

    /** Dummy to satisfy MTable lookups when dictionary not yet synced */
    public static int getTable_ID(String trxName) {
        int id = MTable.getTable_ID(Table_Name);
        return id > 0 ? id : Table_ID;
    }
}
