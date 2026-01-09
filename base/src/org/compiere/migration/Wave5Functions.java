package org.compiere.migration;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    /**
     * Stack entry for price calculation traversal.
     * Tracks ancestor path to detect true circular references.
     */
    private static final class PriceStackEntry {
        private final int productId;
        private final BigDecimal multiplier;
        private final Set<Integer> ancestorPath;

        PriceStackEntry(int productId, BigDecimal multiplier, Set<Integer> ancestorPath) {
            this.productId = productId;
            this.multiplier = multiplier;
            this.ancestorPath = ancestorPath;
        }

        int productId() { return productId; }
        BigDecimal multiplier() { return multiplier; }
        Set<Integer> ancestorPath() { return ancestorPath; }
    }

    /**
     * Calculate BOM price limit by recursively summing component price limits.
     * Equivalent to PostgreSQL bompricelimit function.
     *
     * Algorithm:
     * 1. Try to get PriceLimit from M_ProductPrice directly
     * 2. If price is 0, traverse BOM and sum (component PriceLimit * BomQty)
     * 3. Uses iterative approach with explicit stack to avoid stack overflow
     *
     * @param productId M_Product_ID
     * @param priceListVersionId M_PriceList_Version_ID
     * @return Sum of price limits, ZERO if not found or invalid inputs
     */
    public static BigDecimal bomPriceLimit(Integer productId, Integer priceListVersionId) {
        if (productId == null || productId <= 0 || priceListVersionId == null || priceListVersionId <= 0) {
            return BigDecimal.ZERO;
        }
        return calculateBomPrice(productId, priceListVersionId, "PriceLimit");
    }

    /**
     * Calculate BOM list price by recursively summing component list prices.
     * Equivalent to PostgreSQL bompricelist function.
     *
     * @param productId M_Product_ID
     * @param priceListVersionId M_PriceList_Version_ID
     * @return Sum of list prices, ZERO if not found or invalid inputs
     */
    public static BigDecimal bomPriceList(Integer productId, Integer priceListVersionId) {
        if (productId == null || productId <= 0 || priceListVersionId == null || priceListVersionId <= 0) {
            return BigDecimal.ZERO;
        }
        return calculateBomPrice(productId, priceListVersionId, "PriceList");
    }

    /**
     * Generic BOM price calculator supporting PriceLimit, PriceList, and PriceStd.
     * Uses batch-loaded tree with ancestor-path circular detection.
     *
     * Query optimization:
     * - 1 query: Load BOM tree (CTE)
     * - 1 query: Batch load all product prices
     * - Total: 2 queries regardless of BOM depth or component count
     *
     * Note: Retry/circuit breaker logic is handled by ShadowExecutor.
     * Individual function methods fail fast; ShadowExecutor manages fallback to SQL.
     */
    private static BigDecimal calculateBomPrice(int productId, int priceListVersionId, String priceColumn) {
        // Load entire BOM tree in single query
        Map<Integer, List<BOMComponent>> bomTree = loadBOMTree(productId);

        // Pre-load all product prices in single query (eliminates N+1 pattern)
        Set<Integer> allProductIds = collectAllProductIds(bomTree);
        allProductIds.add(productId); // Include root
        Map<Integer, BigDecimal> prices = getProductPricesBatch(allProductIds, priceListVersionId, priceColumn);

        // Check root price first
        BigDecimal directPrice = prices.getOrDefault(productId, BigDecimal.ZERO);
        if (directPrice.compareTo(BigDecimal.ZERO) != 0) {
            return directPrice;
        }

        if (bomTree.isEmpty()) {
            return BigDecimal.ZERO;
        }

        // Iterative BOM traversal with ancestor-path circular detection
        Deque<PriceStackEntry> stack = new ArrayDeque<>();
        stack.push(new PriceStackEntry(productId, BigDecimal.ONE, Collections.emptySet()));

        BigDecimal totalPrice = BigDecimal.ZERO;

        while (!stack.isEmpty()) {
            PriceStackEntry entry = stack.pop();

            // Circular detection: check if current product is in its own ancestor path
            if (entry.ancestorPath().contains(entry.productId())) {
                log.warning("Circular BOM detected: product " + entry.productId() +
                            " appears in ancestor path " + entry.ancestorPath());
                continue;
            }

            // Try to get price from pre-loaded batch
            BigDecimal componentPrice = prices.getOrDefault(entry.productId(), BigDecimal.ZERO);
            if (componentPrice.compareTo(BigDecimal.ZERO) != 0) {
                totalPrice = totalPrice.add(componentPrice.multiply(entry.multiplier()));
            } else {
                // No direct price - process BOM children from pre-loaded tree
                List<BOMComponent> children = bomTree.getOrDefault(entry.productId(), Collections.emptyList());

                // Build new ancestor path including current node
                Set<Integer> childAncestorPath = new HashSet<>(entry.ancestorPath());
                childAncestorPath.add(entry.productId());

                for (BOMComponent child : children) {
                    BigDecimal childMultiplier = entry.multiplier().multiply(child.bomQty());
                    stack.push(new PriceStackEntry(child.productId(), childMultiplier, childAncestorPath));
                }
            }
        }

        return totalPrice;
    }

    /**
     * Load product prices for multiple products in single query.
     * Eliminates N+1 pattern for price lookups during BOM traversal.
     *
     * Uses PostgreSQL array binding for scalability.
     *
     * @param productIds Set of M_Product_IDs to query
     * @param priceListVersionId M_PriceList_Version_ID
     * @param priceColumn PriceLimit, PriceList, or PriceStd
     * @return Map of productId -> price
     */
    private static Map<Integer, BigDecimal> getProductPricesBatch(
            Set<Integer> productIds, int priceListVersionId, String priceColumn) {
        if (productIds.isEmpty()) {
            return Collections.emptyMap();
        }

        if (!priceColumn.matches("^(PriceLimit|PriceList|PriceStd)$")) {
            throw new IllegalArgumentException("Invalid price column: " + priceColumn);
        }

        String sql = "SELECT M_Product_ID, COALESCE(SUM(" + priceColumn + "), 0) AS price " +
            "FROM M_ProductPrice " +
            "WHERE M_PriceList_Version_ID = ? " +
            "AND M_Product_ID = ANY(?) " +
            "GROUP BY M_Product_ID";

        Map<Integer, BigDecimal> result = new HashMap<>();
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            if (pstmt == null) return result;
            pstmt.setInt(1, priceListVersionId);
            Integer[] productArray = productIds.toArray(new Integer[0]);
            pstmt.setArray(2, pstmt.getConnection().createArrayOf("integer", productArray));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    result.put(rs.getInt("M_Product_ID"), rs.getBigDecimal("price"));
                }
            }
        } catch (SQLException e) {
            log.log(Level.WARNING, "Error batch loading product prices", e);
        }
        return result;
    }

    /**
     * Collect all product IDs from pre-loaded BOM tree (not just stocked).
     * Used for batch price loading.
     */
    private static Set<Integer> collectAllProductIds(Map<Integer, List<BOMComponent>> bomTree) {
        Set<Integer> result = new HashSet<>(bomTree.keySet()); // All parents
        for (List<BOMComponent> children : bomTree.values()) {
            for (BOMComponent child : children) {
                result.add(child.productId());
            }
        }
        return result;
    }
}
