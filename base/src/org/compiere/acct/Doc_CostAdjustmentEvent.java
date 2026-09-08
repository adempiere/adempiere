/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2026 Adempiere Foundation. All Rights Reserved.          *
 *****************************************************************************/
package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCostAdjustmentEvent;
import org.compiere.model.MCostAdjustmentLine;
import org.compiere.model.MProduct;
import org.compiere.model.ProductCost;
import org.compiere.util.Env;

/**
 * Post Cost Adjustment Event.
 *
 * <p>An auditable late-cost event posted <b>only</b> in an open period. It never
 * rewrites the original closed-period facts.</p>
 *
 * <pre>
 * Cost increase (positive delta):
 *   Dr Inventory (P_Asset)      inventory portion
 *   Dr COGS (P_Cogs)            cogs portion
 *      Cr Cost Adjustment (InvDifferences)   total
 * Cost decrease (negative delta): mirror entry.
 * Late landed cost is split between remaining stock (Inventory) and
 * already-sold stock (COGS) by the CostAdjustmentEngine.
 * </pre>
 */
public class Doc_CostAdjustmentEvent extends Doc {

    /** Document base type for cost adjustment events (no separate C_DocType required) */
    public static final String DOCTYPE_CostAdjustment = "MCA";

    public Doc_CostAdjustmentEvent(MAcctSchema[] ass, ResultSet rs, String trxName) {
        super(ass, MCostAdjustmentEvent.class, rs, DOCTYPE_CostAdjustment, trxName);
    }

    private MCostAdjustmentEvent m_event = null;

    @Override
    protected String loadDocumentDetails() {
        m_event = (MCostAdjustmentEvent) getPO();
        setDateDoc(m_event.getDateTrx());
        setDateAcct(m_event.getDateAcct());
        // No DocLines in the classic sense; facts are built from adjustment lines
        p_lines = new DocLine[0];
        return null;
    }

    @Override
    public BigDecimal getBalance() {
        return Env.ZERO;
    }

    @Override
    public ArrayList<Fact> createFacts(MAcctSchema as) {
        ArrayList<Fact> facts = new ArrayList<Fact>();
        if (m_event == null)
            m_event = (MCostAdjustmentEvent) getPO();
        // Only post the schema matching the event (multi-schema safe: one event per schema)
        if (m_event.getC_AcctSchema_ID() != 0 && m_event.getC_AcctSchema_ID() != as.getC_AcctSchema_ID())
            return facts;

        BigDecimal inventoryAmt = m_event.getInventoryAmt();
        BigDecimal cogsAmt = m_event.getCOGSAmt();
        if (inventoryAmt == null)
            inventoryAmt = Env.ZERO;
        if (cogsAmt == null)
            cogsAmt = Env.ZERO;
        if (inventoryAmt.signum() == 0 && cogsAmt.signum() == 0)
            return facts;

        Fact fact = new Fact(this, as, Fact.POST_Actual);
        setC_Currency_ID(as.getC_Currency_ID());

        MProduct product = MProduct.get(getCtx(), m_event.getM_Product_ID());
        ProductCost pc = new ProductCost(getCtx(), m_event.getM_Product_ID(), 0, getTrxName());
        MAccount assetAcct = pc.getAccount(ProductCost.ACCTTYPE_P_Asset, as);
        MAccount cogsAcct = pc.getAccount(ProductCost.ACCTTYPE_P_Cogs, as);
        // Cost-adjustment clearing: inventory differences account (auditable, period-specific)
        MAccount adjustmentAcct = getAccount(Doc.ACCTTYPE_InvDifferences, as);
        if (adjustmentAcct == null)
            adjustmentAcct = assetAcct;

        String description = "CostAdj " + m_event.getDocumentNo()
                + " " + (m_event.getCostAdjustmentType() != null ? m_event.getCostAdjustmentType() : "")
                + " prod=" + (product != null ? product.getValue() : String.valueOf(m_event.getM_Product_ID()));

        // Inventory leg
        if (inventoryAmt.signum() != 0) {
            postLeg(fact, as, assetAcct, adjustmentAcct, inventoryAmt, description, "INV");
            if (p_Error != null)
                return null;
        }
        // COGS leg
        if (cogsAmt.signum() != 0) {
            postLeg(fact, as, cogsAcct, adjustmentAcct, cogsAmt, description, "COGS");
            if (p_Error != null)
                return null;
        }

        facts.add(fact);
        return facts;
    }

    /**
     * Post one leg: positive amount = Dr expense/asset, Cr adjustment;
     * negative amount = mirror.
     */
    private void postLeg(Fact fact, MAcctSchema as, MAccount debitAccount, MAccount creditAccount,
            BigDecimal amount, String description, String legType) {
        // Resolve nulls
        if (debitAccount == null || creditAccount == null) {
            p_Error = "No account for cost adjustment leg " + legType;
            log.log(Level.SEVERE, p_Error);
            return;
        }
        DocLine dummy = null; // facts without order line reference; use null-safe createLine via fact
        FactLine dr = null;
        FactLine cr = null;
        if (amount.signum() > 0) {
            dr = fact.createLine(dummy, debitAccount, as.getC_Currency_ID(), amount, null);
            cr = fact.createLine(dummy, creditAccount, as.getC_Currency_ID(), null, amount);
        } else {
            BigDecimal abs = amount.abs();
            dr = fact.createLine(dummy, creditAccount, as.getC_Currency_ID(), abs, null);
            cr = fact.createLine(dummy, debitAccount, as.getC_Currency_ID(), null, abs);
        }
        if (dr == null || cr == null) {
            p_Error = "FactLine not created for cost adjustment leg " + legType;
            log.log(Level.WARNING, p_Error);
            return;
        }
        dr.addDescription(description + " [" + legType + "]");
        cr.addDescription(description + " [" + legType + "]");
        dr.setM_Product_ID(m_event.getM_Product_ID());
        cr.setM_Product_ID(m_event.getM_Product_ID());
        dr.setAD_Org_ID(m_event.getAD_Org_ID());
        cr.setAD_Org_ID(m_event.getAD_Org_ID());
    }
}
