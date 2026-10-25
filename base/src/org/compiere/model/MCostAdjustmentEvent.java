/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2026 Adempiere Foundation. All Rights Reserved.          *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_C_CostAdjustmentEvent;
import org.adempiere.core.domains.models.I_C_CostAdjustmentLine;
import org.adempiere.core.domains.models.X_C_CostAdjustmentEvent;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Cost Adjustment Event — auditable late-cost event posted in an open period.
 * Never modifies the original closed-period Fact_Acct; instead it records a new
 * document with inventory / COGS split lines.
 */
public class MCostAdjustmentEvent extends X_C_CostAdjustmentEvent implements DocAction {

    private static final long serialVersionUID = 20260908L;

    /** Adjustment types */
    public static final String COSTADJUSTMENTTYPE_LateInvoicePrice = "LIP";
    public static final String COSTADJUSTMENTTYPE_LandedCost = "LC";
    public static final String COSTADJUSTMENTTYPE_NegativeInventory = "NI";
    public static final String COSTADJUSTMENTTYPE_ExchangeDifference = "FX";
    public static final String COSTADJUSTMENTTYPE_Manual = "MAN";

    public MCostAdjustmentEvent(Properties ctx, int C_CostAdjustmentEvent_ID, String trxName) {
        super(ctx, C_CostAdjustmentEvent_ID, trxName);
        if (C_CostAdjustmentEvent_ID == 0) {
            setProcessed(false);
            setPosted(false);
            setDocStatus(DOCSTATUS_Drafted);
            setDocAction(DOCACTION_Complete);
            setDateTrx(new Timestamp(System.currentTimeMillis()));
            setDateAcct(getDateTrx());
            setAdjustmentAmt(Env.ZERO);
            setInventoryAmt(Env.ZERO);
            setCOGSAmt(Env.ZERO);
            setQtyOnHand(Env.ZERO);
            setQtyAlreadySold(Env.ZERO);
            setOldCostPrice(Env.ZERO);
            setNewCostPrice(Env.ZERO);
        }
    }

    public MCostAdjustmentEvent(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public MCostAdjustmentLine[] getLines() {
        List<MCostAdjustmentLine> list = new Query(getCtx(), I_C_CostAdjustmentLine.Table_Name,
                I_C_CostAdjustmentLine.COLUMNNAME_C_CostAdjustmentEvent_ID + "=?", get_TrxName())
                .setParameters(getC_CostAdjustmentEvent_ID())
                .setOrderBy(I_C_CostAdjustmentLine.COLUMNNAME_Line)
                .list();
        MCostAdjustmentLine[] ret = new MCostAdjustmentLine[list.size()];
        list.toArray(ret);
        return ret;
    }

    /**
     * Create inventory / COGS split lines. Must sum to AdjustmentAmt.
     * @param inventoryAmt portion staying in inventory (may be negative)
     * @param cogsAmt portion hitting COGS (may be negative)
     */
    public void createSplitLines(BigDecimal inventoryAmt, BigDecimal cogsAmt, String trxName) {
        if (inventoryAmt == null)
            inventoryAmt = Env.ZERO;
        if (cogsAmt == null)
            cogsAmt = Env.ZERO;
        int lineNo = 10;
        if (inventoryAmt.signum() != 0) {
            MCostAdjustmentLine line = new MCostAdjustmentLine(getCtx(), 0, get_TrxName());
            line.setC_CostAdjustmentEvent_ID(getC_CostAdjustmentEvent_ID());
            line.setLine(lineNo);
            line.setAdjustmentLineType(MCostAdjustmentLine.ADJUSTMENTLINETYPE_Inventory);
            line.setAmt(inventoryAmt);
            line.setQty(getQtyOnHand());
            line.saveEx();
            lineNo += 10;
        }
        if (cogsAmt.signum() != 0) {
            MCostAdjustmentLine line = new MCostAdjustmentLine(getCtx(), 0, get_TrxName());
            line.setC_CostAdjustmentEvent_ID(getC_CostAdjustmentEvent_ID());
            line.setLine(lineNo);
            line.setAdjustmentLineType(MCostAdjustmentLine.ADJUSTMENTLINETYPE_COGS);
            line.setAmt(cogsAmt);
            line.setQty(getQtyAlreadySold());
            line.saveEx();
        }
        setInventoryAmt(inventoryAmt);
        setCOGSAmt(cogsAmt);
        setAdjustmentAmt(inventoryAmt.add(cogsAmt));
        saveEx();
    }

    // --- DocAction boilerplate (minimal complete/void/close) ---

    public boolean processIt(String action) throws Exception {
        DocumentEngine engine = new DocumentEngine(this, getDocStatus());
        return engine.processIt(action, getDocAction());
    }

    public boolean unlockIt() {
        log.info(toString());
        setProcessed(false);
        return true;
    }

    public boolean invalidateIt() {
        log.info(toString());
        setDocAction(DOCACTION_Prepare);
        return true;
    }

    public String prepareIt() {
        log.info(toString());
        // Must have open period on DateAcct
        Timestamp dateAcct = getDateAcct() != null ? getDateAcct() : getDateTrx();
        MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
        String docBaseType = dt != null && dt.getDocBaseType() != null ? dt.getDocBaseType() : MDocType.DOCBASETYPE_MaterialReceipt;
        if (!MPeriod.isOpen(getCtx(), dateAcct, docBaseType, getAD_Org_ID())) {
            m_processMsg = "@PeriodClosed@";
            return DOCSTATUS_Invalid;
        }
        if (getAdjustmentAmt().signum() == 0 && getLines().length == 0) {
            m_processMsg = "@NoLines@";
            return DOCSTATUS_Invalid;
        }
        setDocAction(DOCACTION_Complete);
        return DOCSTATUS_InProgress;
    }

    public boolean approveIt() {
        log.info(toString());
        return true;
    }

    public boolean rejectIt() {
        log.info(toString());
        return true;
    }

    public String completeIt() {
        // Re-validate open period at completion time
        String status = prepareIt();
        if (!DOCSTATUS_InProgress.equals(status))
            return status;
        setProcessed(true);
        setDocStatus(DOCSTATUS_Completed);
        setDocAction(DOCACTION_Close);
        // Posting is done by AcctProcessor via Doc_CostAdjustmentEvent
        return DOCSTATUS_Completed;
    }

    public boolean voidIt() {
        // Void = reversing event in current open period; never delete history
        if (DOCSTATUS_Completed.equals(getDocStatus()) || DOCSTATUS_Closed.equals(getDocStatus())) {
            // Reverse by creating a mirror event
            MCostAdjustmentEvent reversal = new MCostAdjustmentEvent(getCtx(), 0, get_TrxName());
            PO.copyValues(this, reversal);
            reversal.setC_CostAdjustmentEvent_ID(0);
            reversal.setDocumentNo(getDocumentNo() + "^");
            reversal.setAdjustmentAmt(getAdjustmentAmt().negate());
            reversal.setInventoryAmt(getInventoryAmt().negate());
            reversal.setCOGSAmt(getCOGSAmt().negate());
            reversal.setDescription("Reversal of " + getDocumentNo());
            reversal.setProcessed(true);
            reversal.setDocStatus(DOCSTATUS_Voided);
            reversal.saveEx();
            // Reverse Fact_Acct of this event only (original periods untouched)
            MFactAcct.deleteEx(get_Table_ID(), get_ID(), get_TrxName());
            setPosted(false);
        }
        setProcessed(true);
        setDocStatus(DOCSTATUS_Voided);
        setDocAction(DOCACTION_None);
        return true;
    }

    public boolean closeIt() {
        log.info(toString());
        setDocAction(DOCACTION_None);
        return true;
    }

    public boolean reverseCorrectIt() {
        return voidIt();
    }

    public boolean reverseAccrualIt() {
        return voidIt();
    }

    public boolean reActivateIt() {
        log.info(toString());
        return false;
    }

    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDocumentNo()).append(": Amt=").append(getAdjustmentAmt())
                .append(" (Inv=").append(getInventoryAmt())
                .append(", COGS=").append(getCOGSAmt()).append(")");
        return sb.toString();
    }

    public String getDocumentInfo() {
        return getSummary();
    }

    public java.io.File createPDF() {
        return null;
    }

    public String getProcessMsg() {
        return m_processMsg;
    }

    public int getDoc_User_ID() {
        return getCreatedBy();
    }

    public int getC_Currency_ID() {
        MAcctSchema as = MAcctSchema.get(getCtx(), getC_AcctSchema_ID());
        if (as != null)
            return as.getC_Currency_ID();
        return Env.getContextAsInt(getCtx(), "$C_Currency_ID");
    }

    public BigDecimal getApprovalAmt() {
        return getAdjustmentAmt();
    }

    private String m_processMsg = null;

    public String getDocBaseType() {
        return null;
    }

    protected boolean beforeSave(boolean newRecord) {
        if (getDateAcct() == null && getDateTrx() != null)
            setDateAcct(getDateTrx());
        if (getDocumentNo() == null || getDocumentNo().length() == 0) {
            String no = DB.getDocumentNo(getC_DocType_ID() > 0 ? getC_DocType_ID() : 0, get_TrxName(), false, this);
            if (no != null)
                setDocumentNo(no);
        }
        return true;
    }

    /** Find events referencing an original cost detail (audit trail). */
    public static List<MCostAdjustmentEvent> getByCostDetail(Properties ctx, int M_CostDetail_ID, String trxName) {
        return new Query(ctx, I_C_CostAdjustmentEvent.Table_Name,
                I_C_CostAdjustmentEvent.COLUMNNAME_M_CostDetail_ID + "=?", trxName)
                .setParameters(M_CostDetail_ID)
                .setOrderBy(I_C_CostAdjustmentEvent.COLUMNNAME_C_CostAdjustmentEvent_ID)
                .list();
    }
}
