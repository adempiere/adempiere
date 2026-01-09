package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class Wave5FunctionsTest {

    @Test
    void testBOMComponentRecord() {
        Wave5Functions.BOMComponent comp = new Wave5Functions.BOMComponent(
            100, BigDecimal.ONE, true, true, "I", 2);

        assertEquals(100, comp.productId());
        assertEquals(BigDecimal.ONE, comp.bomQty());
        assertTrue(comp.isBOM());
        assertTrue(comp.isStocked());
        assertEquals("I", comp.productType());
        assertEquals(2, comp.uomPrecision());
    }

    @Test
    void testBOMComponent_nullBomQtyDefaultsToOne() {
        Wave5Functions.BOMComponent comp = new Wave5Functions.BOMComponent(
            100, null, true, true, "I", 0);
        assertEquals(BigDecimal.ONE, comp.bomQty());
    }

    @Test
    void testBOMComponent_negativeBomQtyThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Wave5Functions.BOMComponent(100, new BigDecimal("-1"), true, true, "I", 0));
    }

    @Test
    void testBOMComponent_handlesPercentageQuantityCalculation() {
        // When IsQtyPercentage = 'Y', CTE calculates: QtyBatch / 100
        // Example: QtyBatch = 50 means 0.50 (50%)
        BigDecimal percentageQty = new BigDecimal("0.50");
        Wave5Functions.BOMComponent comp = new Wave5Functions.BOMComponent(
            100, percentageQty, false, true, "I", 2);

        assertEquals(new BigDecimal("0.50"), comp.bomQty());
    }

    @Test
    void testLoadBOMTree_nullProduct() {
        Map<Integer, List<Wave5Functions.BOMComponent>> tree = Wave5Functions.loadBOMTree(null);
        assertTrue(tree.isEmpty());
    }

    @Test
    void testLoadBOMTree_invalidProduct() {
        Map<Integer, List<Wave5Functions.BOMComponent>> tree = Wave5Functions.loadBOMTree(-1);
        assertTrue(tree.isEmpty());
    }

    @Test
    void testLoadBOMTree_returnsMapStructure() {
        // Even with no BOM, should return empty map, not null
        Map<Integer, List<Wave5Functions.BOMComponent>> tree = Wave5Functions.loadBOMTree(99999999);
        assertNotNull(tree);
    }

    @Test
    void testResolveWarehouse_warehouseProvided() {
        Integer result = Wave5Functions.resolveWarehouse(100, 200);
        assertEquals(100, result);
    }

    @Test
    void testResolveWarehouse_bothNull() {
        Integer result = Wave5Functions.resolveWarehouse(null, null);
        assertNull(result);
    }

    @Test
    void testBomPriceLimit_nullInputs() {
        BigDecimal result = Wave5Functions.bomPriceLimit(null, null);
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void testBomPriceLimit_invalidInputs() {
        BigDecimal result = Wave5Functions.bomPriceLimit(-1, -1);
        assertEquals(BigDecimal.ZERO, result);
    }
}
