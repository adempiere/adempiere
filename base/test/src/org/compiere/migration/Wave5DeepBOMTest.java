package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

/**
 * Tests for deep BOM traversal and depth limiting.
 */
class Wave5DeepBOMTest {

    @Test
    void testMaxDepthConfiguration() {
        int maxDepth = Wave5Functions.getMaxDepth();
        assertTrue(maxDepth > 0, "Max depth should be positive");
        assertTrue(maxDepth <= 1000, "Max depth should be reasonable");
    }

    @Test
    void testUnlimitedQtyConstant() {
        assertEquals(new BigDecimal("99999"), Wave5Functions.UNLIMITED_QTY);
    }
}
