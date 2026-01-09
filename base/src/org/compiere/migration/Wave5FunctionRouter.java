package org.compiere.migration;

import java.math.BigDecimal;
import org.compiere.migration.comparators.BigDecimalComparator;

/**
 * Router for Wave 5 BOM functions.
 * Delegates to ShadowExecutor for shadow validation.
 */
public class Wave5FunctionRouter {

    private Wave5FunctionRouter() {
        // Static methods only
    }

    /**
     * Route bomPriceLimit function call.
     */
    public static BigDecimal bomPriceLimit(Integer productId, Integer priceListVersionId) {
        return ShadowExecutor.execute(
            "bomPriceLimit",
            new Object[]{productId, priceListVersionId},
            () -> Wave5Functions.bomPriceLimit(productId, priceListVersionId),
            () -> SqlFunctionCaller.callBomPriceLimit(productId, priceListVersionId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route bomPriceList function call.
     */
    public static BigDecimal bomPriceList(Integer productId, Integer priceListVersionId) {
        return ShadowExecutor.execute(
            "bomPriceList",
            new Object[]{productId, priceListVersionId},
            () -> Wave5Functions.bomPriceList(productId, priceListVersionId),
            () -> SqlFunctionCaller.callBomPriceList(productId, priceListVersionId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route bomPriceStd function call.
     */
    public static BigDecimal bomPriceStd(Integer productId, Integer priceListVersionId) {
        return ShadowExecutor.execute(
            "bomPriceStd",
            new Object[]{productId, priceListVersionId},
            () -> Wave5Functions.bomPriceStd(productId, priceListVersionId),
            () -> SqlFunctionCaller.callBomPriceStd(productId, priceListVersionId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route bomQtyOnHand function call.
     */
    public static BigDecimal bomQtyOnHand(Integer productId, Integer warehouseId, Integer locatorId) {
        return ShadowExecutor.execute(
            "bomQtyOnHand",
            new Object[]{productId, warehouseId, locatorId},
            () -> Wave5Functions.bomQtyOnHand(productId, warehouseId, locatorId),
            () -> SqlFunctionCaller.callBomQtyOnHand(productId, warehouseId, locatorId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route bomQtyReserved function call.
     */
    public static BigDecimal bomQtyReserved(Integer productId, Integer warehouseId, Integer locatorId) {
        return ShadowExecutor.execute(
            "bomQtyReserved",
            new Object[]{productId, warehouseId, locatorId},
            () -> Wave5Functions.bomQtyReserved(productId, warehouseId, locatorId),
            () -> SqlFunctionCaller.callBomQtyReserved(productId, warehouseId, locatorId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route bomQtyOrdered function call.
     */
    public static BigDecimal bomQtyOrdered(Integer productId, Integer warehouseId, Integer locatorId) {
        return ShadowExecutor.execute(
            "bomQtyOrdered",
            new Object[]{productId, warehouseId, locatorId},
            () -> Wave5Functions.bomQtyOrdered(productId, warehouseId, locatorId),
            () -> SqlFunctionCaller.callBomQtyOrdered(productId, warehouseId, locatorId),
            BigDecimalComparator.CURRENCY
        );
    }

    /**
     * Route bomQtyAvailable function call.
     */
    public static BigDecimal bomQtyAvailable(Integer productId, Integer warehouseId, Integer locatorId) {
        return ShadowExecutor.execute(
            "bomQtyAvailable",
            new Object[]{productId, warehouseId, locatorId},
            () -> Wave5Functions.bomQtyAvailable(productId, warehouseId, locatorId),
            () -> SqlFunctionCaller.callBomQtyAvailable(productId, warehouseId, locatorId),
            BigDecimalComparator.CURRENCY
        );
    }
}
