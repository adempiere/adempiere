/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2026 Adempiere Foundation. All Rights Reserved.          *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 *****************************************************************************/
package org.adempiere.engine;

import java.math.BigDecimal;

import org.compiere.util.Env;

/**
 * Result of a late-cost computation.
 * Immutable value object: total delta is split between on-hand inventory
 * (still in stock) and already-sold quantity (COGS).
 *
 * @author Adempiere
 */
public class CostAdjustmentResult {

    private final BigDecimal unitCostBefore;
    private final BigDecimal unitCostAfter;
    private final BigDecimal qtyOnHand;
    private final BigDecimal qtyAlreadySold;
    private final BigDecimal inventoryPortion;
    private final BigDecimal cogsPortion;
    private final BigDecimal totalDelta;
    private final boolean costIncrease;

    public CostAdjustmentResult(BigDecimal unitCostBefore, BigDecimal unitCostAfter,
            BigDecimal qtyOnHand, BigDecimal qtyAlreadySold,
            BigDecimal inventoryPortion, BigDecimal cogsPortion) {
        this.unitCostBefore = unitCostBefore == null ? Env.ZERO : unitCostBefore;
        this.unitCostAfter = unitCostAfter == null ? Env.ZERO : unitCostAfter;
        this.qtyOnHand = qtyOnHand == null ? Env.ZERO : qtyOnHand;
        this.qtyAlreadySold = qtyAlreadySold == null ? Env.ZERO : qtyAlreadySold;
        this.inventoryPortion = inventoryPortion == null ? Env.ZERO : inventoryPortion;
        this.cogsPortion = cogsPortion == null ? Env.ZERO : cogsPortion;
        this.totalDelta = this.inventoryPortion.add(this.cogsPortion);
        this.costIncrease = this.totalDelta.signum() > 0;
    }

    public BigDecimal getUnitCostBefore() {
        return unitCostBefore;
    }

    public BigDecimal getUnitCostAfter() {
        return unitCostAfter;
    }

    public BigDecimal getQtyOnHand() {
        return qtyOnHand;
    }

    public BigDecimal getQtyAlreadySold() {
        return qtyAlreadySold;
    }

    /** Portion attributable to remaining stock (Dr Inventory when positive). */
    public BigDecimal getInventoryPortion() {
        return inventoryPortion;
    }

    /** Portion attributable to already sold stock (Dr COGS when positive). */
    public BigDecimal getCogsPortion() {
        return cogsPortion;
    }

    public BigDecimal getTotalDelta() {
        return totalDelta;
    }

    public boolean isCostIncrease() {
        return costIncrease;
    }

    public boolean isCostDecrease() {
        return totalDelta.signum() < 0;
    }

    public boolean isZero() {
        return totalDelta.signum() == 0;
    }

    @Override
    public String toString() {
        return "CostAdjustmentResult[before=" + unitCostBefore + ", after=" + unitCostAfter
                + ", onHand=" + qtyOnHand + ", sold=" + qtyAlreadySold
                + ", inv=" + inventoryPortion + ", cogs=" + cogsPortion + "]";
    }
}
