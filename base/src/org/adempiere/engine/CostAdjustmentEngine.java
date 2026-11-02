/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2026 Adempiere Foundation. All Rights Reserved.          *
 *****************************************************************************/
package org.adempiere.engine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_C_CommissionDetail;
import org.adempiere.core.domains.models.I_M_Storage;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCommissionAmt;
import org.compiere.model.MCommissionDetail;
import org.compiere.model.MConversionRate;
import org.compiere.model.MCost;
import org.compiere.model.MCostAdjustmentEvent;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostType;
import org.compiere.model.MDocType;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLandedCostAllocation;
import org.compiere.model.MMatchInv;
import org.compiere.model.MPeriod;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

/**
 * Service that records late-known costs as auditable adjustment events.
 *
 * <ul>
 * <li>If the original accounting period is OPEN: callers may still use the
 * standard {@link CostEngine} recalculation path.</li>
 * <li>If the original period is CLOSED: never touch the original
 * Fact_Acct / cost detail. Instead create a {@link MCostAdjustmentEvent}
 * dated in the current open period, split between inventory and COGS.</li>
 * </ul>
 *
 * Covers: negative inventory, late landed costs, vendor price differences,
 * currency differences and reversals.
 */
public class CostAdjustmentEngine {

    private static final CLogger log = CLogger.getCLogger(CostAdjustmentEngine.class);

    /** Singleton */
    private static CostAdjustmentEngine instance = new CostAdjustmentEngine();

    public static CostAdjustmentEngine get() {
        return instance;
    }

    /**
     * Decide whether a late cost must go through the adjustment-event path.
     * @return true when original period is closed (must NOT repost original)
     */
    public boolean isAdjustmentRequired(Properties ctx, Timestamp originalDateAcct,
            String docBaseType, int AD_Org_ID, String trxName) {
        if (originalDateAcct == null)
            return false;
        return !MPeriod.isOpen(ctx, originalDateAcct, docBaseType, AD_Org_ID);
    }

    /**
     * Resolve an open-period accounting date for the adjustment (today or given date).
     * Throws if no open period exists — the adjustment must wait for an open period.
     */
    public Timestamp resolveOpenAdjustmentDate(Properties ctx, Timestamp preferredDate,
            String docBaseType, int AD_Org_ID, String trxName) {
        Timestamp date = preferredDate != null ? preferredDate : new Timestamp(System.currentTimeMillis());
        // Strip time portion
        date = TimeUtil.getDay(date);
        if (date == null)
            date = new Timestamp(System.currentTimeMillis());
        if (!MPeriod.isOpen(ctx, date, docBaseType, AD_Org_ID)) {
            // Fall back to today; if still closed, fail loudly instead of posting into closed period
            Timestamp today = TimeUtil.getDay(new Timestamp(System.currentTimeMillis()));
            if (today != null && MPeriod.isOpen(ctx, today, docBaseType, AD_Org_ID))
                return today;
            throw new AdempiereException("@PeriodClosed@ (" + date + ")");
        }
        return date;
    }

    /**
     * Main entry: record a unit-cost correction for a product / cost dimension.
     *
     * @param ctx context
     * @param AD_Org_ID org of the adjustment
     * @param C_AcctSchema_ID accounting schema
     * @param M_CostType_ID cost type
     * @param M_CostElement_ID cost element
     * @param M_Product_ID product
     * @param originalDateAcct accounting date of the original transaction
     * @param originalDocBaseType doc base type of the original document
     * @param oldUnitCost provisional unit cost
     * @param newUnitCost final unit cost
     * @param qtyOnHand qty still on hand affected
     * @param qtyAlreadySold qty already sold with provisional cost
     * @param adjustmentType see {@link MCostAdjustmentEvent} constants
     * @param sourceIds optional source references (inOutLineId, invoiceLineId, landedCostAllocationId, costDetailId)
     * @param description description
     * @param trxName trx
     * @return persisted event (completed) or null when delta is zero
     */
    public MCostAdjustmentEvent recordUnitCostChange(Properties ctx, int AD_Org_ID,
            int C_AcctSchema_ID, int M_CostType_ID, int M_CostElement_ID, int M_Product_ID,
            Timestamp originalDateAcct, String originalDocBaseType,
            BigDecimal oldUnitCost, BigDecimal newUnitCost,
            BigDecimal qtyOnHand, BigDecimal qtyAlreadySold,
            String adjustmentType, int[] sourceIds, String description, String trxName) {
        MAcctSchema as = MAcctSchema.get(ctx, C_AcctSchema_ID);
        int precision = as != null ? as.getCostingPrecision() : CostAdjustmentCalculator.DEFAULT_PRECISION;
        CostAdjustmentResult result = CostAdjustmentCalculator.calculate(
                oldUnitCost, newUnitCost, qtyOnHand, qtyAlreadySold, precision);
        if (result.isZero()) {
            log.fine("No cost delta — no adjustment event created");
            return null;
        }
        boolean mustAdjust = isAdjustmentRequired(ctx, originalDateAcct, originalDocBaseType, AD_Org_ID, trxName);
        Timestamp adjustmentDate;
        if (mustAdjust) {
            adjustmentDate = resolveOpenAdjustmentDate(ctx, null, originalDocBaseType, AD_Org_ID, trxName);
        } else {
            adjustmentDate = originalDateAcct != null ? originalDateAcct
                    : resolveOpenAdjustmentDate(ctx, null, originalDocBaseType, AD_Org_ID, trxName);
        }

        MCostAdjustmentEvent event = new MCostAdjustmentEvent(ctx, 0, trxName);
        event.setAD_Org_ID(AD_Org_ID);
        event.setM_Product_ID(M_Product_ID);
        event.setC_AcctSchema_ID(C_AcctSchema_ID);
        event.setM_CostType_ID(M_CostType_ID);
        event.setM_CostElement_ID(M_CostElement_ID);
        event.setCostAdjustmentType(adjustmentType != null ? adjustmentType : MCostAdjustmentEvent.COSTADJUSTMENTTYPE_Manual);
        event.setOldCostPrice(result.getUnitCostBefore());
        event.setNewCostPrice(result.getUnitCostAfter());
        event.setQtyOnHand(result.getQtyOnHand());
        event.setQtyAlreadySold(result.getQtyAlreadySold());
        event.setDateAcct(adjustmentDate);
        event.setDateTrx(adjustmentDate);
        if (sourceIds != null) {
            if (sourceIds.length > 0)
                event.setM_InOutLine_ID(sourceIds[0]);
            if (sourceIds.length > 1)
                event.setC_InvoiceLine_ID(sourceIds[1]);
            if (sourceIds.length > 2)
                event.setC_LandedCostAllocation_ID(sourceIds[2]);
            if (sourceIds.length > 3)
                event.setM_CostDetail_ID(sourceIds[3]);
        }
        StringBuilder desc = new StringBuilder(description != null ? description : "");
        desc.append(" | OrigDate=").append(originalDateAcct)
            .append(mustAdjust ? " (closed, adjusted in open period " + adjustmentDate + ")" : " (open period)");
        event.setDescription(desc.toString().length() > 255 ? desc.toString().substring(0, 255) : desc.toString());
        event.saveEx();
        event.createSplitLines(result.getInventoryPortion(), result.getCogsPortion(), trxName);
        try {
            event.processIt(MCostAdjustmentEvent.DOCACTION_Complete);
            event.saveEx();
        } catch (Exception e) {
            throw new AdempiereException(e);
        }
        // Keep the live MCost dimension in sync (current average), without rewriting history
        updateLiveAverageCost(ctx, AD_Org_ID, C_AcctSchema_ID, M_CostType_ID, M_CostElement_ID,
                M_Product_ID, result, trxName);
        return event;
    }

    /**
     * Late landed cost arriving after receipt/sale: split proportionally.
     */
    public MCostAdjustmentEvent recordLateLandedCost(Properties ctx, MLandedCostAllocation allocation,
            BigDecimal landedCostTotal, BigDecimal qtyOfReceipt, BigDecimal qtyOnHand, String trxName) {
        MInOutLine ioLine = allocation != null ? (MInOutLine) allocation.getM_InOutLine() : null;
        int M_Product_ID = allocation != null ? allocation.getM_Product_ID()
                : (ioLine != null ? ioLine.getM_Product_ID() : 0);
        int AD_Org_ID = allocation != null ? allocation.getAD_Org_ID() : 0;
        MAcctSchema[] schemas = MAcctSchema.getClientAcctSchema(ctx, Env.getAD_Client_ID(ctx), trxName);
        MCostAdjustmentEvent last = null;
        for (MAcctSchema as : schemas) {
            List<MCostType> types = MCostType.get(ctx, trxName);
            for (MCostType type : types) {
                CostAdjustmentResult split = CostAdjustmentCalculator.splitLandedCost(
                        landedCostTotal, qtyOnHand, qtyOfReceipt, as.getCostingPrecision());
                if (split.isZero())
                    continue;
                Timestamp origDate = ioLine != null ? ioLine.getParent().getDateAcct() : allocation.getCreated();
                String docBaseType = MDocType.DOCBASETYPE_MaterialReceipt;
                Timestamp adjDate = resolveOpenAdjustmentDate(ctx, null, docBaseType, AD_Org_ID, trxName);
                MCostAdjustmentEvent event = new MCostAdjustmentEvent(ctx, 0, trxName);
                event.setAD_Org_ID(AD_Org_ID);
                event.setM_Product_ID(M_Product_ID);
                event.setC_AcctSchema_ID(as.getC_AcctSchema_ID());
                event.setM_CostType_ID(type.getM_CostType_ID());
                event.setM_CostElement_ID(allocation.getM_CostElement_ID());
                event.setCostAdjustmentType(MCostAdjustmentEvent.COSTADJUSTMENTTYPE_LandedCost);
                event.setQtyOnHand(split.getQtyOnHand());
                event.setQtyAlreadySold(split.getQtyAlreadySold());
                event.setDateAcct(adjDate);
                event.setDateTrx(adjDate);
                event.setM_InOutLine_ID(ioLine != null ? ioLine.getM_InOutLine_ID() : 0);
                event.setC_InvoiceLine_ID(allocation.getC_InvoiceLine_ID());
                event.setC_LandedCostAllocation_ID(allocation.getC_LandedCostAllocation_ID());
                event.setDescription("Late landed cost C_LandedCostAllocation_ID="
                        + allocation.getC_LandedCostAllocation_ID()
                        + " orig=" + origDate + " openAdj=" + adjDate);
                event.saveEx();
                event.createSplitLines(split.getInventoryPortion(), split.getCogsPortion(), trxName);
                try {
                    event.processIt(MCostAdjustmentEvent.DOCACTION_Complete);
                    event.saveEx();
                } catch (Exception e) {
                    throw new AdempiereException(e);
                }
                last = event;
            }
        }
        return last;
    }

    /**
     * Negative-inventory correction: sale happened before replenishment with a
     * provisional cost; once the purchase cost is known, adjust COGS in the open period.
     */
    public MCostAdjustmentEvent recordNegativeInventoryCorrection(Properties ctx, int AD_Org_ID,
            int C_AcctSchema_ID, int M_CostType_ID, int M_CostElement_ID, int M_Product_ID,
            Timestamp provisionalDateAcct, String docBaseType,
            BigDecimal provisionalUnitCost, BigDecimal finalUnitCost,
            BigDecimal qtySoldShort, BigDecimal qtyOnHandNow, String trxName) {
        // Entire shortfall was sold with provisional cost; remaining stock (if any) also revalued
        return recordUnitCostChange(ctx, AD_Org_ID, C_AcctSchema_ID, M_CostType_ID, M_CostElement_ID,
                M_Product_ID, provisionalDateAcct, docBaseType, provisionalUnitCost, finalUnitCost,
                qtyOnHandNow, qtySoldShort,
                MCostAdjustmentEvent.COSTADJUSTMENTTYPE_NegativeInventory, null,
                "Negative inventory correction", trxName);
    }

    /**
     * Vendor invoice price difference after receipt (MatchInv arriving in a later
     * period, possibly after the original period closed or after sale).
     */
    public MCostAdjustmentEvent recordInvoicePriceDifference(Properties ctx, MMatchInv matchInv,
            BigDecimal receiptUnitCost, BigDecimal invoiceUnitCost, BigDecimal qtyOnHand,
            BigDecimal qtyAlreadySold, String trxName) {
        int M_Product_ID = matchInv.getM_Product_ID();
        int AD_Org_ID = matchInv.getAD_Org_ID();
        MAcctSchema[] schemas = MAcctSchema.getClientAcctSchema(ctx, matchInv.getAD_Client_ID(), trxName);
        MCostAdjustmentEvent last = null;
        for (MAcctSchema as : schemas) {
            List<MCostType> types = MCostType.get(ctx, trxName);
            for (MCostType type : types) {
                // Average-PO vs Average-Invoice semantics stay in CostEngine; here we record the delta event
                MInOutLine ioLine = (MInOutLine) matchInv.getM_InOutLine();
                Timestamp origDate = ioLine != null ? ioLine.getParent().getDateAcct() : matchInv.getDateTrx();
                MCostElement materialElement = MCostElement.getMaterialCostElement(as);
                if (materialElement == null)
                    continue;
                last = recordUnitCostChange(ctx, AD_Org_ID, as.getC_AcctSchema_ID(),
                        type.getM_CostType_ID(),
                        materialElement.getM_CostElement_ID(),
                        M_Product_ID, origDate, MDocType.DOCBASETYPE_MaterialReceipt,
                        receiptUnitCost, invoiceUnitCost, qtyOnHand, qtyAlreadySold,
                        MCostAdjustmentEvent.COSTADJUSTMENTTYPE_LateInvoicePrice,
                        new int[]{ioLine != null ? ioLine.getM_InOutLine_ID() : 0, matchInv.getC_InvoiceLine_ID(), 0, 0},
                        "Invoice price difference MatchInv " + matchInv.getDocumentNo(), trxName);
            }
        }
        return last;
    }

    /**
     * Currency-difference correction: convert the same foreign amount with the final
     * rate vs the provisional rate.
     */
    public MCostAdjustmentEvent recordCurrencyDifference(Properties ctx, int AD_Org_ID,
            int C_AcctSchema_ID, int M_CostType_ID, int M_CostElement_ID, int M_Product_ID,
            Timestamp originalDateAcct, String docBaseType, BigDecimal foreignUnitCost,
            BigDecimal provisionalRate, BigDecimal finalRate,
            BigDecimal qtyOnHand, BigDecimal qtyAlreadySold, String trxName) {
        if (foreignUnitCost == null || provisionalRate == null || finalRate == null)
            throw new AdempiereException("Invalid currency correction parameters");
        MAcctSchema as = MAcctSchema.get(ctx, C_AcctSchema_ID);
        int precision = as != null ? as.getCostingPrecision() : CostAdjustmentCalculator.DEFAULT_PRECISION;
        BigDecimal oldCost = foreignUnitCost.multiply(provisionalRate).setScale(precision, RoundingMode.HALF_UP);
        BigDecimal newCost = foreignUnitCost.multiply(finalRate).setScale(precision, RoundingMode.HALF_UP);
        return recordUnitCostChange(ctx, AD_Org_ID, C_AcctSchema_ID, M_CostType_ID, M_CostElement_ID,
                M_Product_ID, originalDateAcct, docBaseType, oldCost, newCost,
                qtyOnHand, qtyAlreadySold,
                MCostAdjustmentEvent.COSTADJUSTMENTTYPE_ExchangeDifference, null,
                "Currency difference correction", trxName);
    }

    // ------------------------------------------------------------------
    // Commission handling
    // ------------------------------------------------------------------

    /**
     * When commission is margin based, a COGS adjustment changes the margin.
     * Never rewrite a closed commission run: instead create a new detail line in
     * the first open run (or return the computed delta when no open run exists
     * so the caller can queue it).
     *
     * @param cogsAdjustment positive when cost increased
     * @param commissionRate e.g. 0.10
     * @param currencyPrecision precision for money
     * @return commission delta (negative when cost increased)
     */
    public BigDecimal calculateCommissionDelta(BigDecimal cogsAdjustment, BigDecimal commissionRate,
            int currencyPrecision) {
        return CostAdjustmentCalculator.commissionDelta(cogsAdjustment, commissionRate, currencyPrecision);
    }

    /**
     * Append a commission-adjustment detail to the given commission amount.
     * The caller must ensure the parent run is in an open period.
     * @param commissionAmt open-period parent amount line
     * @param currencyId currency for the detail (taken from source document,
     *        since C_CommissionAmt carries no currency column)
     */
    public MCommissionDetail createCommissionAdjustmentDetail(MCommissionAmt commissionAmt,
            int currencyId, BigDecimal commissionDelta, BigDecimal qty, String description) {
        if (commissionAmt == null || commissionDelta == null || commissionDelta.signum() == 0)
            return null;
        int effectiveCurrencyId = currencyId > 0 ? currencyId
                : Env.getContextAsInt(commissionAmt.getCtx(), "$C_Currency_ID");
        MCommissionDetail detail = new MCommissionDetail(commissionAmt,
                effectiveCurrencyId, commissionDelta, qty != null ? qty : Env.ONE);
        if (description != null)
            detail.setInfo(description.length() > 60 ? description.substring(0, 60) : description);
        detail.saveEx();
        // Roll up
        BigDecimal current = commissionAmt.getCommissionAmt();
        if (current == null)
            current = Env.ZERO;
        commissionAmt.setCommissionAmt(current.add(commissionDelta));
        commissionAmt.saveEx();
        return detail;
    }

    /**
     * Append a commission-adjustment detail to the given commission amount.
     * The caller must ensure the parent run is in an open period.
     * @deprecated use {@link #createCommissionAdjustmentDetail(MCommissionAmt, int, BigDecimal, BigDecimal, String)}
     */
    public MCommissionDetail createCommissionAdjustmentDetail(MCommissionAmt commissionAmt,
            BigDecimal commissionDelta, BigDecimal qty, String description) {
        return createCommissionAdjustmentDetail(commissionAmt, 0, commissionDelta, qty, description);
    }

    /**
     * Find details of a closed commission run affected by a product COGS change.
     * Used for audit reporting (which invoices need an open-period adjustment).
     */
    public List<MCommissionDetail> findClosedCommissionDetails(Properties ctx, int C_CommissionRun_ID,
            int M_Product_ID, String trxName) {
        return new Query(ctx, I_C_CommissionDetail.Table_Name,
                "C_CommissionAmt_ID IN (SELECT C_CommissionAmt_ID FROM C_CommissionAmt WHERE C_CommissionRun_ID=?)"
                + " AND M_Product_ID=?", trxName)
                .setParameters(C_CommissionRun_ID, M_Product_ID)
                .list();
    }

    // ------------------------------------------------------------------
    // Helpers
    // ------------------------------------------------------------------

    /** Current on-hand qty from storage (all locators, or one warehouse when given). */
    public BigDecimal getQtyOnHand(Properties ctx, int M_Product_ID, int M_Warehouse_ID, String trxName) {
        StringBuilder where = new StringBuilder(I_M_Storage.COLUMNNAME_M_Product_ID + "=?");
        java.util.ArrayList<Object> params = new java.util.ArrayList<Object>();
        params.add(M_Product_ID);
        if (M_Warehouse_ID > 0) {
            where.append(" AND EXISTS (SELECT 1 FROM M_Locator l WHERE l.M_Locator_ID=M_Storage.M_Locator_ID"
                    + " AND l.M_Warehouse_ID=?)");
            params.add(M_Warehouse_ID);
        }
        BigDecimal qty = new Query(ctx, I_M_Storage.Table_Name, where.toString(), trxName)
                .setParameters(params)
                .sum(I_M_Storage.COLUMNNAME_QtyOnHand);
        return qty != null ? qty : Env.ZERO;
    }

    /** Convert amount to schema currency. */
    public BigDecimal convertToSchemaCurrency(Properties ctx, MAcctSchema as, int C_Currency_ID,
            Timestamp dateAcct, int C_ConversionType_ID, int AD_Client_ID, int AD_Org_ID,
            BigDecimal amount, String trxName) {
        if (amount == null)
            return Env.ZERO;
        if (C_Currency_ID == as.getC_Currency_ID())
            return amount;
        BigDecimal converted = MConversionRate.convert(ctx, amount, C_Currency_ID, as.getC_Currency_ID(),
                dateAcct, C_ConversionType_ID, AD_Client_ID, AD_Org_ID);
        return converted != null ? converted : Env.ZERO;
    }

    /**
     * Move the live MCost average forward by the adjustment without rewriting
     * historical cost details. Keeps future issues valued at the corrected cost.
     */
    private void updateLiveAverageCost(Properties ctx, int AD_Org_ID, int C_AcctSchema_ID,
            int M_CostType_ID, int M_CostElement_ID, int M_Product_ID,
            CostAdjustmentResult result, String trxName) {
        try {
            MProduct product = MProduct.get(ctx, M_Product_ID);
            MAcctSchema as = MAcctSchema.get(ctx, C_AcctSchema_ID);
            if (product == null || as == null)
                return;
            // Resolve dimension per costing level
            String costingLevel = product.getCostingLevel(as, AD_Org_ID);
            int orgId = AD_Org_ID;
            int warehouseId = 0;
            int asiId = 0;
            if (MAcctSchema.COSTINGLEVEL_Client.equals(costingLevel))
                orgId = 0;
            else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(costingLevel))
                orgId = 0;
            MCost cost = MCost.getDimension(product, C_AcctSchema_ID, orgId, warehouseId, asiId,
                    M_CostType_ID, M_CostElement_ID);
            if (cost == null)
                return;
            // New average = corrected unit cost (provisional + delta applied to full affected base)
            BigDecimal affectedBase = result.getQtyOnHand().add(result.getQtyAlreadySold());
            if (affectedBase.signum() == 0)
                return;
            // Only move average when there is remaining stock; COGS-only corrections
            // leave the average at the final unit cost for future transactions.
            cost.setCurrentCostPrice(result.getUnitCostAfter());
            cost.setCumulatedAmt(cost.getCumulatedAmt().add(result.getInventoryPortion()));
            cost.setCumulatedQty(cost.getCumulatedQty());
            cost.setCurrentQty(cost.getCumulatedQty());
            cost.saveEx(trxName);
        } catch (Exception e) {
            // Never fail the adjustment because the live average sync failed; log only
            log.warning("Could not sync live average cost: " + e.getMessage());
        }
    }

    /**
     * Guard used by costing processes: refuse to delete/recreate history when the
     * original period is closed. Returns false when the caller must use
     * {@link #recordUnitCostChange} instead.
     */
    public boolean assertRepostingAllowed(Properties ctx, Timestamp dateAcct, String docBaseType,
            int AD_Org_ID) {
        if (dateAcct == null)
            return true;
        boolean open = MPeriod.isOpen(ctx, dateAcct, docBaseType, AD_Org_ID);
        if (!open)
            log.warning("Reposting blocked: period closed for " + dateAcct
                    + " docBaseType=" + docBaseType + ". Use CostAdjustmentEvent instead.");
        return open;
    }
}
