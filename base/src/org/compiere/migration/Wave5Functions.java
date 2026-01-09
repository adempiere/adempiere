package org.compiere.migration;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Java implementations of Wave 5 BOM PostgreSQL functions.
 *
 * All functions use iterative traversal with explicit stack to avoid
 * stack overflow on deep BOMs. Circular references are detected via
 * HashSet tracking per traversal.
 */
public class Wave5Functions {

    private static final CLogger log = CLogger.getCLogger(Wave5Functions.class);

    /** Maximum BOM depth to prevent runaway traversals */
    private static final int MAX_BOM_DEPTH = 100;

    /** Sentinel value for unlimited quantity (matches PostgreSQL 99999) */
    static final BigDecimal UNLIMITED_QTY = new BigDecimal("99999");

    private Wave5Functions() {
        // Static methods only
    }

    /**
     * Represents a BOM component with its properties.
     * Used during iterative BOM traversal.
     *
     * Invariants:
     * - bomQty is never null (defaults to ONE if database returns null)
     * - bomQty is never negative (throws IllegalArgumentException)
     * - uomPrecision defaults to 0 if not available
     */
    public static final class BOMComponent {
        private final int productId;
        private final BigDecimal bomQty;
        private final boolean isBOM;
        private final boolean isStocked;
        private final String productType;
        private final int uomPrecision;

        public BOMComponent(
                int productId,
                BigDecimal bomQty,
                boolean isBOM,
                boolean isStocked,
                String productType,
                int uomPrecision) {
            // Null handling: default to 1 (one unit of component)
            if (bomQty == null) {
                log.fine("BOM component " + productId + " has null bomQty, defaulting to 1");
                bomQty = BigDecimal.ONE;
            }
            // Negative validation
            if (bomQty.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException(
                    "BOM component " + productId + " has negative bomQty: " + bomQty);
            }
            this.productId = productId;
            this.bomQty = bomQty;
            this.isBOM = isBOM;
            this.isStocked = isStocked;
            this.productType = productType;
            this.uomPrecision = uomPrecision;
        }

        public int productId() { return productId; }
        public BigDecimal bomQty() { return bomQty; }
        public boolean isBOM() { return isBOM; }
        public boolean isStocked() { return isStocked; }
        public String productType() { return productType; }
        public int uomPrecision() { return uomPrecision; }

        public boolean isStockedItem() {
            return "I".equals(productType) && isStocked;
        }
    }

    /**
     * Get max BOM depth from SysConfig or use default.
     */
    static int getMaxDepth() {
        try {
            String value = Wave4Functions.getSysconfig("BOM_MAX_DEPTH", "100", 0, 0);
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return MAX_BOM_DEPTH;
        }
    }

    /**
     * Load entire BOM tree in single query using recursive CTE.
     * Returns map of parent -> children for O(1) lookups during traversal.
     *
     * This approach:
     * - Executes single database round-trip regardless of BOM depth
     * - Includes IsActive filtering on all tables
     * - Enforces MAX_BOM_DEPTH in SQL for efficiency
     * - Works identically for shallow and deep BOMs
     *
     * @param rootProductId M_Product_ID of the root BOM product
     * @return Map where key = parent product ID, value = list of child components
     */
    public static Map<Integer, List<BOMComponent>> loadBOMTree(Integer rootProductId) {
        if (rootProductId == null || rootProductId <= 0) {
            return Collections.emptyMap();
        }

        int maxDepth = getMaxDepth();

        String sql =
            "WITH RECURSIVE bom_tree AS (" +
            "    -- Anchor: direct children of root product" +
            "    SELECT b.M_Product_ID AS parent_id," +
            "           bl.M_Product_ID AS child_id," +
            "           CASE WHEN bl.IsQtyPercentage = 'N' THEN bl.QtyBOM" +
            "                ELSE COALESCE(bl.QtyBatch, 0) / 100 END AS BomQty," +
            "           p.IsBOM, p.IsStocked, p.ProductType," +
            "           COALESCE(u.StdPrecision, 0) AS uom_precision," +
            "           1 AS depth," +
            "           ARRAY[b.M_Product_ID] AS path," +
            "           false AS is_cycle" +
            "    FROM PP_Product_BOM b" +
            "    INNER JOIN PP_Product_BOMLine bl ON bl.PP_Product_BOM_ID = b.PP_Product_BOM_ID" +
            "    INNER JOIN M_Product p ON p.M_Product_ID = bl.M_Product_ID" +
            "    LEFT JOIN C_UOM u ON u.C_UOM_ID = p.C_UOM_ID" +
            "    WHERE b.M_Product_ID = ?" +
            "      AND b.IsActive = 'Y' AND bl.IsActive = 'Y' AND p.IsActive = 'Y'" +
            "    UNION ALL" +
            "    -- Recursive: children's children (with cycle detection)" +
            "    SELECT b.M_Product_ID AS parent_id," +
            "           bl.M_Product_ID AS child_id," +
            "           CASE WHEN bl.IsQtyPercentage = 'N' THEN bl.QtyBOM" +
            "                ELSE COALESCE(bl.QtyBatch, 0) / 100 END AS BomQty," +
            "           p.IsBOM, p.IsStocked, p.ProductType," +
            "           COALESCE(u.StdPrecision, 0) AS uom_precision," +
            "           bt.depth + 1," +
            "           bt.path || b.M_Product_ID," +
            "           b.M_Product_ID = ANY(bt.path) AS is_cycle" +
            "    FROM bom_tree bt" +
            "    INNER JOIN PP_Product_BOM b ON b.M_Product_ID = bt.child_id" +
            "    INNER JOIN PP_Product_BOMLine bl ON bl.PP_Product_BOM_ID = b.PP_Product_BOM_ID" +
            "    INNER JOIN M_Product p ON p.M_Product_ID = bl.M_Product_ID" +
            "    LEFT JOIN C_UOM u ON u.C_UOM_ID = p.C_UOM_ID" +
            "    WHERE bt.depth < ?" +
            "      AND NOT bt.is_cycle" +
            "      AND b.IsActive = 'Y' AND bl.IsActive = 'Y' AND p.IsActive = 'Y'" +
            ") " +
            "SELECT parent_id, child_id, BomQty, IsBOM, IsStocked, ProductType, uom_precision, depth " +
            "FROM bom_tree " +
            "WHERE NOT is_cycle " +
            "ORDER BY depth, parent_id";

        Map<Integer, List<BOMComponent>> tree = new HashMap<>();

        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            if (pstmt == null) {
                log.warning("Cannot prepare statement for loadBOMTree - DB unavailable");
                return Collections.emptyMap();
            }
            pstmt.setInt(1, rootProductId);
            pstmt.setInt(2, maxDepth);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int parentId = rs.getInt("parent_id");
                    int childId = rs.getInt("child_id");
                    try {
                        BOMComponent child = new BOMComponent(
                            childId,
                            rs.getBigDecimal("BomQty"),
                            "Y".equals(rs.getString("IsBOM")),
                            "Y".equals(rs.getString("IsStocked")),
                            rs.getString("ProductType"),
                            rs.getInt("uom_precision")
                        );
                        tree.computeIfAbsent(parentId, k -> new ArrayList<>()).add(child);
                    } catch (IllegalArgumentException e) {
                        // Skip components with invalid data (e.g., negative bomQty)
                        log.warning("Skipping invalid BOM component " + childId + ": " + e.getMessage());
                    }
                }
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "Error loading BOM tree for product " + rootProductId, e);
        } catch (NullPointerException e) {
            // DB.prepareStatement throws NPE when database connection is unavailable
            log.warning("DB unavailable for loadBOMTree: " + e.getMessage());
        }

        return tree;
    }

    /**
     * Resolve warehouse ID from parameters.
     * If warehouseId is null but locatorId is provided, looks up warehouse from locator.
     * Matches PostgreSQL logic: IF (myWarehouse_ID IS NULL) THEN ... FROM M_LOCATOR ...
     *
     * @param warehouseId M_Warehouse_ID (may be null)
     * @param locatorId M_Locator_ID (fallback, may be null)
     * @return Resolved warehouse ID, or null if cannot resolve
     */
    public static Integer resolveWarehouse(Integer warehouseId, Integer locatorId) {
        if (warehouseId != null) {
            return warehouseId;
        }
        if (locatorId == null) {
            return null;
        }

        // Lookup warehouse from locator
        String sql = "SELECT M_Warehouse_ID FROM M_Locator WHERE M_Locator_ID = ?";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            if (pstmt == null) {
                return null;
            }
            pstmt.setInt(1, locatorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("M_Warehouse_ID");
                }
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "Error resolving warehouse from locator " + locatorId, e);
        }
        return null;
    }
}
