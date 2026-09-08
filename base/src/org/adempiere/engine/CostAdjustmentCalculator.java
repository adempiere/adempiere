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
import java.math.RoundingMode;

import org.compiere.util.Env;

/**
 * Pure (DB-free) calculator for auditable cost adjustments.
 *
 * <p>Core principle: a closed period must remain immutable. A late-known cost
 * is recorded as a <b>new</b> economic event in an open period, split between
 * remaining inventory and already-sold quantities (COGS).</p>
 *
 * <p>Reproducible scenario from the feature request:</p>
 * <pre>
 * March: 10 units @ 30 on hand, sell 20 units @ 50 (provisional COGS 600), close period.
 * April: purchase 20 units @ 40.
 * Final COGS = 700, adjustment = +100 in April, final inventory 10 units @ 40.
 * </pre>
 */
public class CostAdjustmentCalculator {

    /** Default precision when caller does not provide one. */
    public static final int DEFAULT_PRECISION = 4;

    private CostAdjustmentCalculator() {
    }

    /**
     * Compute adjustment for a unit-cost change.
     *
     * @param unitCostBefore provisional unit cost used originally
     * @param unitCostAfter final (true) unit cost
     * @param qtyOnHand quantity still in stock affected by the change
     * @param qtyAlreadySold quantity already shipped/invoiced with provisional cost
     * @param precision costing precision
     * @return split result
     */
    public static CostAdjustmentResult calculate(BigDecimal unitCostBefore, BigDecimal unitCostAfter,
            BigDecimal qtyOnHand, BigDecimal qtyAlreadySold, int precision) {
        if (unitCostBefore == null)
            unitCostBefore = Env.ZERO;
        if (unitCostAfter == null)
            unitCostAfter = Env.ZERO;
        if (qtyOnHand == null)
            qtyOnHand = Env.ZERO;
        if (qtyAlreadySold == null)
            qtyAlreadySold = Env.ZERO;
        if (precision < 0)
            precision = DEFAULT_PRECISION;

        BigDecimal unitDelta = unitCostAfter.subtract(unitCostBefore);
        BigDecimal inventoryPortion = round(unitDelta.multiply(qtyOnHand), precision);
        BigDecimal cogsPortion = round(unitDelta.multiply(qtyAlreadySold), precision);
        return new CostAdjustmentResult(unitCostBefore, unitCostAfter,
                qtyOnHand, qtyAlreadySold, inventoryPortion, cogsPortion);
    }

    public static CostAdjustmentResult calculate(BigDecimal unitCostBefore, BigDecimal unitCostAfter,
            BigDecimal qtyOnHand, BigDecimal qtyAlreadySold) {
        return calculate(unitCostBefore, unitCostAfter, qtyOnHand, qtyAlreadySold, DEFAULT_PRECISION);
    }

    /**
     * Recompute average cost after a late receipt when negative inventory existed.
     * average = (onHandValue + receiptQty * receiptCost) / (onHandQty + receiptQty)
     * where onHandQty may be negative (sale before replenishment).
     *
     * @param onHandQty current накопительный quantity (may be negative)
     * @param onHandValue current inventory value (qty * provisional average)
     * @param receiptQty incoming quantity (&gt; 0)
     * @param receiptUnitCost incoming unit cost
     * @param precision costing precision
     * @return new average unit cost (zero if resulting quantity is zero)
     */
    public static BigDecimal averageAfterReceipt(BigDecimal onHandQty, BigDecimal onHandValue,
            BigDecimal receiptQty, BigDecimal receiptUnitCost, int precision) {
        if (onHandQty == null)
            onHandQty = Env.ZERO;
        if (onHandValue == null)
            onHandValue = Env.ZERO;
        if (receiptQty == null)
            receiptQty = Env.ZERO;
        if (receiptUnitCost == null)
            receiptUnitCost = Env.ZERO;
        if (precision < 0)
            precision = DEFAULT_PRECISION;
        BigDecimal newQty = onHandQty.add(receiptQty);
        if (newQty.signum() == 0)
            return Env.ZERO;
        BigDecimal newValue = onHandValue.add(receiptQty.multiply(receiptUnitCost));
        return newValue.divide(newQty, precision, RoundingMode.HALF_UP);
    }

    /**
     * Compute provisional COGS charged with a provisional average cost, and the
     * final COGS once the true average is known. Difference = COGS adjustment.
     */
    public static BigDecimal cogsDelta(BigDecimal qtySold, BigDecimal provisionalUnitCost,
            BigDecimal finalUnitCost, int precision) {
        if (qtySold == null || provisionalUnitCost == null || finalUnitCost == null)
            return Env.ZERO;
        BigDecimal delta = finalUnitCost.subtract(provisionalUnitCost).multiply(qtySold);
        return round(delta, precision);
    }

    /**
     * Commission delta when commission is margin based:
     * commission = margin * rate, margin = revenue - cogs.
     * A COGS increase of X reduces commission by X * rate.
     *
     * @param cogsAdjustment positive when cost increased
     * @param commissionRate e.g. 0.10 for 10%
     * @param precision precision
     * @return commission adjustment (negative when cost increased)
     */
    public static BigDecimal commissionDelta(BigDecimal cogsAdjustment, BigDecimal commissionRate, int precision) {
        if (cogsAdjustment == null || commissionRate == null)
            return Env.ZERO;
        if (precision < 0)
            precision = 2;
        // margin delta = -cogsAdjustment ; commission delta = margin delta * rate
        return round(cogsAdjustment.negate().multiply(commissionRate), precision);
    }

    /**
     * Split a late landed cost between inventory and COGS proportionally.
     *
     * @param landedCostTotal total late landed cost
     * @param qtyOnHand qty still on hand from the original receipt
     * @param qtyOfReceipt original receipt quantity
     * @param precision precision
     * @return result where unit costs are zero and portions hold the split
     */
    public static CostAdjustmentResult splitLandedCost(BigDecimal landedCostTotal,
            BigDecimal qtyOnHand, BigDecimal qtyOfReceipt, int precision) {
        if (landedCostTotal == null)
            landedCostTotal = Env.ZERO;
        if (qtyOnHand == null)
            qtyOnHand = Env.ZERO;
        if (qtyOfReceipt == null || qtyOfReceipt.signum() == 0)
            return new CostAdjustmentResult(Env.ZERO, Env.ZERO, qtyOnHand, Env.ZERO, Env.ZERO, landedCostTotal);
        if (precision < 0)
            precision = DEFAULT_PRECISION;
        BigDecimal onHand = qtyOnHand.max(Env.ZERO);
        BigDecimal sold = qtyOfReceipt.subtract(onHand).max(Env.ZERO);
        BigDecimal inventoryPortion = Env.ZERO;
        BigDecimal cogsPortion = Env.ZERO;
        if (qtyOfReceipt.signum() != 0) {
            inventoryPortion = round(landedCostTotal.multiply(onHand)
                    .divide(qtyOfReceipt, 10, RoundingMode.HALF_UP), precision);
            cogsPortion = round(landedCostTotal.subtract(inventoryPortion), precision);
        }
        return new CostAdjustmentResult(Env.ZERO, Env.ZERO, onHand, sold, inventoryPortion, cogsPortion);
    }

    private static BigDecimal round(BigDecimal value, int precision) {
        if (value == null)
            return Env.ZERO;
        if (value.scale() > precision)
            return value.setScale(precision, RoundingMode.HALF_UP);
        return value;
    }
}
