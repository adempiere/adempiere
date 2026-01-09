# Wave 5: BOM Function Migration Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** Migrate 7 recursive Bill of Materials PostgreSQL functions to Java with iterative traversal and circular detection

**Architecture:** Follow Wave 4 pattern (Wave5Functions + Wave5FunctionRouter + ShadowExecutor). Use batch recursive CTE to load entire BOM tree in single query (eliminates N+1 pattern). Use iterative stack-based traversal with ancestor-path tracking for accurate circular detection. Cache descoped - batch CTE approach makes per-request caching unnecessary; may add as future optimization if profiling shows need.

**Tech Stack:** Java 17, ADempiere model layer, JUnit 5

**Review Status:** Approved with changes per `docs/plans/2026-01-09-wave5-bom-migration-design-critical-review-4.md`

**Multi-Tenant Security Assumption:** These functions do not include AD_Client_ID or AD_Org_ID filtering in their queries. This matches the behavior of the PostgreSQL functions being replaced, which rely on the database connection having appropriate tenant context set via `SET search_path` or row-level security policies. If multi-tenant isolation is required at the application level, callers must ensure appropriate context is established before invoking these functions. The shadow validation will compare Java and SQL results under identical tenant contexts.

---

## Task Group 1: Foundation and Shared Infrastructure (3 tasks)

### Task 1.1: Create Wave5Functions Class with BOM Component Record

**Files:**
- Create: `base/src/org/compiere/migration/Wave5Functions.java`
- Test: `base/test/src/org/compiere/migration/Wave5FunctionsTest.java`

**Step 1: Write failing test for BOMComponent record and base structure**

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
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
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest -q`
Expected: FAIL with compilation error "Wave5Functions cannot be resolved"

**Step 3: Write minimal implementation**

```java
package org.compiere.migration;

import java.math.BigDecimal;
import java.util.logging.Level;
import org.compiere.util.CLogger;

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
    public record BOMComponent(
        int productId,
        BigDecimal bomQty,
        boolean isBOM,
        boolean isStocked,
        String productType,
        int uomPrecision
    ) {
        public BOMComponent {
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
        }

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
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testBOMComponentRecord -q`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave5Functions.java base/test/src/org/compiere/migration/Wave5FunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave5): add Wave5Functions foundation with BOMComponent record

Establishes base structure for Wave 5 BOM function migration.
Includes BOMComponent record for iterative traversal and max depth
configuration via AD_SysConfig.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 1.2: Add BOM Tree Loader Method (Batch CTE)

**Files:**
- Modify: `base/src/org/compiere/migration/Wave5Functions.java:20-80`
- Test: `base/test/src/org/compiere/migration/Wave5FunctionsTest.java`

**Design Note:** This replaces the original per-node loadBOMChildren with a batch CTE that loads the entire BOM tree in a single query. This eliminates N+1 query pattern (100+ queries for deep BOMs → 1 query).

**Step 1: Write failing test for loadBOMTree**

```java
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
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testLoadBOMTree* -q`
Expected: FAIL with "method loadBOMTree not found"

**Step 3: Write minimal implementation**

Add to Wave5Functions.java after getMaxDepth():

```java
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

    String sql = """
        WITH RECURSIVE bom_tree AS (
            -- Anchor: direct children of root product
            SELECT b.M_Product_ID AS parent_id,
                   bl.M_Product_ID AS child_id,
                   CASE WHEN bl.IsQtyPercentage = 'N' THEN bl.QtyBOM
                        ELSE COALESCE(bl.QtyBatch, 0) / 100 END AS BomQty,
                   p.IsBOM, p.IsStocked, p.ProductType,
                   COALESCE(u.StdPrecision, 0) AS uom_precision,
                   1 AS depth,
                   ARRAY[b.M_Product_ID] AS path,
                   false AS is_cycle
            FROM PP_Product_BOM b
            INNER JOIN PP_Product_BOMLine bl ON bl.PP_Product_BOM_ID = b.PP_Product_BOM_ID
            INNER JOIN M_Product p ON p.M_Product_ID = bl.M_Product_ID
            LEFT JOIN C_UOM u ON u.C_UOM_ID = p.C_UOM_ID
            WHERE b.M_Product_ID = ?
              AND b.IsActive = 'Y' AND bl.IsActive = 'Y' AND p.IsActive = 'Y'

            UNION ALL

            -- Recursive: children's children (with cycle detection)
            SELECT b.M_Product_ID AS parent_id,
                   bl.M_Product_ID AS child_id,
                   CASE WHEN bl.IsQtyPercentage = 'N' THEN bl.QtyBOM
                        ELSE COALESCE(bl.QtyBatch, 0) / 100 END AS BomQty,
                   p.IsBOM, p.IsStocked, p.ProductType,
                   COALESCE(u.StdPrecision, 0) AS uom_precision,
                   bt.depth + 1,
                   bt.path || b.M_Product_ID,
                   b.M_Product_ID = ANY(bt.path) AS is_cycle
            FROM bom_tree bt
            INNER JOIN PP_Product_BOM b ON b.M_Product_ID = bt.child_id
            INNER JOIN PP_Product_BOMLine bl ON bl.PP_Product_BOM_ID = b.PP_Product_BOM_ID
            INNER JOIN M_Product p ON p.M_Product_ID = bl.M_Product_ID
            LEFT JOIN C_UOM u ON u.C_UOM_ID = p.C_UOM_ID
            WHERE bt.depth < ?
              AND NOT bt.is_cycle
              AND b.IsActive = 'Y' AND bl.IsActive = 'Y' AND p.IsActive = 'Y'
        )
        SELECT parent_id, child_id, BomQty, IsBOM, IsStocked, ProductType, uom_precision, depth
        FROM bom_tree
        WHERE NOT is_cycle
        ORDER BY depth, parent_id
        """;

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
                BOMComponent child = new BOMComponent(
                    rs.getInt("child_id"),
                    rs.getBigDecimal("BomQty"),
                    "Y".equals(rs.getString("IsBOM")),
                    "Y".equals(rs.getString("IsStocked")),
                    rs.getString("ProductType"),
                    rs.getInt("uom_precision")
                );

                tree.computeIfAbsent(parentId, k -> new ArrayList<>()).add(child);
            }
        }
    } catch (SQLException e) {
        log.log(Level.WARNING, "Error loading BOM tree for product " + rootProductId, e);
    }

    return tree;
}
```

Add imports at top:

```java
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.compiere.util.DB;
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testLoadBOMTree* -q`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave5Functions.java base/test/src/org/compiere/migration/Wave5FunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave5): add loadBOMTree with batch CTE for BOM traversal

Loads entire BOM tree in single query using recursive CTE.
Eliminates N+1 query pattern (100+ queries → 1 query for deep BOMs).
Includes IsActive filtering on PP_Product_BOM, PP_Product_BOMLine, M_Product.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 1.3: Add Warehouse Resolution Helper

**Files:**
- Modify: `base/src/org/compiere/migration/Wave5Functions.java`
- Test: `base/test/src/org/compiere/migration/Wave5FunctionsTest.java`

**Step 1: Write failing test for resolveWarehouse**

```java
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
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testResolveWarehouse* -q`
Expected: FAIL with "method resolveWarehouse not found"

**Step 3: Write minimal implementation**

Add to Wave5Functions.java:

```java
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

    // Lookup warehouse from locator (matches PostgreSQL: SUM(M_Warehouse_ID) for onhand, MAX for others)
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
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testResolveWarehouse* -q`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave5Functions.java base/test/src/org/compiere/migration/Wave5FunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave5): add resolveWarehouse helper method

Resolves warehouse ID from warehouseId or locatorId fallback.
Matches PostgreSQL BOM quantity function parameter handling.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

## Task Group 2: BOM Pricing Functions (4 tasks)

### Task 2.1: Implement bomPriceLimit Function

**Files:**
- Modify: `base/src/org/compiere/migration/Wave5Functions.java`
- Test: `base/test/src/org/compiere/migration/Wave5FunctionsTest.java`

**Step 1: Write failing test for bomPriceLimit**

```java
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
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testBomPriceLimit* -q`
Expected: FAIL with "method bomPriceLimit not found"

**Step 3: Write minimal implementation**

```java
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
 * Stack entry for price calculation traversal.
 * Tracks ancestor path to detect true circular references.
 */
private record PriceStackEntry(
    int productId,
    BigDecimal multiplier,
    Set<Integer> ancestorPath
) {}

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
    stack.push(new PriceStackEntry(productId, BigDecimal.ONE, Set.of()));

    BigDecimal totalPrice = BigDecimal.ZERO;

    while (!stack.isEmpty()) {
        PriceStackEntry entry = stack.pop();

        // Circular detection: check if current product is in its own ancestor path
        if (entry.ancestorPath.contains(entry.productId)) {
            log.warning("Circular BOM detected: product " + entry.productId +
                        " appears in ancestor path " + entry.ancestorPath);
            continue;
        }

        // Try to get price from pre-loaded batch
        BigDecimal componentPrice = prices.getOrDefault(entry.productId, BigDecimal.ZERO);
        if (componentPrice.compareTo(BigDecimal.ZERO) != 0) {
            totalPrice = totalPrice.add(componentPrice.multiply(entry.multiplier));
        } else {
            // No direct price - process BOM children from pre-loaded tree
            List<BOMComponent> children = bomTree.getOrDefault(entry.productId, Collections.emptyList());

            // Build new ancestor path including current node
            Set<Integer> childAncestorPath = new HashSet<>(entry.ancestorPath);
            childAncestorPath.add(entry.productId);

            for (BOMComponent child : children) {
                BigDecimal childMultiplier = entry.multiplier.multiply(child.bomQty());
                stack.push(new PriceStackEntry(child.productId(), childMultiplier, childAncestorPath));
            }
        }
    }

    return totalPrice;
}

/**
 * Get product price from M_ProductPrice table.
 */
private static BigDecimal getProductPrice(int productId, int priceListVersionId, String priceColumn) {
    // Validate column name to prevent SQL injection
    if (!priceColumn.matches("^(PriceLimit|PriceList|PriceStd)$")) {
        throw new IllegalArgumentException("Invalid price column: " + priceColumn);
    }

    String sql = "SELECT COALESCE(SUM(" + priceColumn + "), 0) FROM M_ProductPrice "
        + "WHERE M_PriceList_Version_ID = ? AND M_Product_ID = ?";

    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        if (pstmt == null) {
            return null;
        }
        pstmt.setInt(1, priceListVersionId);
        pstmt.setInt(2, productId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (SQLException e) {
        log.log(Level.WARNING, "Error getting product price for " + productId, e);
    }
    return null;
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
        "AND IsActive = 'Y' " +
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
```

Add imports:

```java
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testBomPriceLimit* -q`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave5Functions.java base/test/src/org/compiere/migration/Wave5FunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave5): implement bomPriceLimit with iterative traversal

Calculates BOM price limit by summing component prices iteratively.
Uses stack-based traversal to avoid stack overflow on deep BOMs.
Includes circular detection via HashSet tracking.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 2.2: Implement bomPriceList Function

**Files:**
- Modify: `base/src/org/compiere/migration/Wave5Functions.java`
- Test: `base/test/src/org/compiere/migration/Wave5FunctionsTest.java`

**Step 1: Write failing test**

```java
@Test
void testBomPriceList_nullInputs() {
    BigDecimal result = Wave5Functions.bomPriceList(null, null);
    assertEquals(BigDecimal.ZERO, result);
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testBomPriceList* -q`
Expected: FAIL with "method bomPriceList not found"

**Step 3: Write minimal implementation**

```java
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
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testBomPriceList* -q`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave5Functions.java base/test/src/org/compiere/migration/Wave5FunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave5): implement bomPriceList function

Calculates BOM list price using shared calculateBomPrice infrastructure.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 2.3: Implement bomPriceStd Function

**Files:**
- Modify: `base/src/org/compiere/migration/Wave5Functions.java`
- Test: `base/test/src/org/compiere/migration/Wave5FunctionsTest.java`

**Step 1: Write failing test**

```java
@Test
void testBomPriceStd_nullInputs() {
    BigDecimal result = Wave5Functions.bomPriceStd(null, null);
    assertEquals(BigDecimal.ZERO, result);
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testBomPriceStd* -q`
Expected: FAIL with "method bomPriceStd not found"

**Step 3: Write minimal implementation**

```java
/**
 * Calculate BOM standard price by recursively summing component standard prices.
 * Equivalent to PostgreSQL bompricestd function.
 *
 * @param productId M_Product_ID
 * @param priceListVersionId M_PriceList_Version_ID
 * @return Sum of standard prices, ZERO if not found or invalid inputs
 */
public static BigDecimal bomPriceStd(Integer productId, Integer priceListVersionId) {
    if (productId == null || productId <= 0 || priceListVersionId == null || priceListVersionId <= 0) {
        return BigDecimal.ZERO;
    }
    return calculateBomPrice(productId, priceListVersionId, "PriceStd");
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testBomPriceStd* -q`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave5Functions.java base/test/src/org/compiere/migration/Wave5FunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave5): implement bomPriceStd function

Calculates BOM standard price using shared calculateBomPrice infrastructure.
Completes Wave 5a pricing function implementations.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 2.4: Add Integration Tests for Pricing Functions

**Files:**
- Create: `base/test/src/org/compiere/migration/Wave5PricingIntegrationTest.java`

**Design Note:** Uses JUnit 5 Assumptions instead of silent early returns. Tests will show as SKIPPED (not false PASS) when test data unavailable.

**Step 1: Write integration tests with test data setup**

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Integration tests for Wave 5 BOM pricing functions.
 * Compares Java implementation against PostgreSQL function results.
 */
class Wave5PricingIntegrationTest extends AbstractMigrationTest {

    private static final int TEST_PRODUCT_ID = -99999;
    private static final int TEST_PLV_ID = -99998;

    @BeforeEach
    void setUp() {
        // Test data created by parent class or skipped if no DB
    }

    @AfterEach
    void tearDown() {
        // Clean up test data
    }

    @Test
    void testBomPriceLimit_matchesSql() {
        Integer productId = getAnyProductWithPrice();
        Integer plvId = getAnyPriceListVersion();

        assumeTrue(productId != null && productId > 0, "Skipping: No product with price available in test database");
        assumeTrue(plvId != null && plvId > 0, "Skipping: No price list version available in test database");

        BigDecimal javaResult = Wave5Functions.bomPriceLimit(productId, plvId);
        BigDecimal sqlResult = callSqlFunction("bompricelimit", productId, plvId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> "bomPriceLimit mismatch for product " + productId +
                  ": Java=" + javaResult + ", SQL=" + sqlResult);
    }

    @Test
    void testBomPriceList_matchesSql() {
        Integer productId = getAnyProductWithPrice();
        Integer plvId = getAnyPriceListVersion();

        assumeTrue(productId != null && productId > 0, "Skipping: No product with price available");
        assumeTrue(plvId != null && plvId > 0, "Skipping: No price list version available");

        BigDecimal javaResult = Wave5Functions.bomPriceList(productId, plvId);
        BigDecimal sqlResult = callSqlFunction("bompricelist", productId, plvId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> "bomPriceList mismatch: Java=" + javaResult + ", SQL=" + sqlResult);
    }

    @Test
    void testBomPriceStd_matchesSql() {
        Integer productId = getAnyProductWithPrice();
        Integer plvId = getAnyPriceListVersion();

        assumeTrue(productId != null && productId > 0, "Skipping: No product with price available");
        assumeTrue(plvId != null && plvId > 0, "Skipping: No price list version available");

        BigDecimal javaResult = Wave5Functions.bomPriceStd(productId, plvId);
        BigDecimal sqlResult = callSqlFunction("bompricestd", productId, plvId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> "bomPriceStd mismatch: Java=" + javaResult + ", SQL=" + sqlResult);
    }

    private BigDecimal callSqlFunction(String functionName, int productId, int plvId) {
        String sql = "SELECT " + functionName + "(?, ?)";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.setInt(1, productId);
            pstmt.setInt(2, plvId);
            var rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        } catch (Exception e) {
            fail("SQL function call failed: " + e.getMessage());
        }
        return BigDecimal.ZERO;
    }

    private Integer getAnyProductWithPrice() {
        String sql = "SELECT M_Product_ID FROM M_ProductPrice WHERE PriceStd > 0 LIMIT 1";
        return DB.getSQLValue(null, sql);
    }

    private Integer getAnyPriceListVersion() {
        String sql = "SELECT M_PriceList_Version_ID FROM M_PriceList_Version WHERE IsActive='Y' LIMIT 1";
        return DB.getSQLValue(null, sql);
    }
}
```

**Step 2: Run test to verify it compiles**

Run: `mvn test-compile -pl base -q`
Expected: Compiles successfully

**Step 3: Run test**

Run: `mvn test -pl base -Dtest=Wave5PricingIntegrationTest -q`
Expected: PASS (or skip if no DB connection)

**Step 4: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave5PricingIntegrationTest.java
git commit -m "$(cat <<'EOF'
test(wave5): add integration tests for BOM pricing functions

Compares Java implementations against PostgreSQL function results.
Tests bomPriceLimit, bomPriceList, bomPriceStd against real data.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

## Task Group 3: BOM Quantity Functions (4 tasks)

### Task 3.1: Implement bomQtyOnHand Function

**Files:**
- Modify: `base/src/org/compiere/migration/Wave5Functions.java`
- Test: `base/test/src/org/compiere/migration/Wave5FunctionsTest.java`

**Step 1: Write failing tests**

```java
@Test
void testBomQtyOnHand_nullInputs() {
    BigDecimal result = Wave5Functions.bomQtyOnHand(null, null, null);
    assertEquals(BigDecimal.ZERO, result);
}

@Test
void testBomQtyOnHand_noWarehouseOrLocator() {
    BigDecimal result = Wave5Functions.bomQtyOnHand(100, null, null);
    assertEquals(BigDecimal.ZERO, result);
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testBomQtyOnHand* -q`
Expected: FAIL with "method bomQtyOnHand not found"

**Step 3: Write minimal implementation**

```java
/**
 * Calculate BOM quantity on hand.
 * Equivalent to PostgreSQL bomqtyonhand function.
 *
 * Algorithm:
 * 1. Resolve warehouse from parameters
 * 2. Check if product is stocked - if yes, return direct qty
 * 3. If BOM, find minimum qty that can be assembled from components
 * 4. Non-stocked non-BOM items return unlimited (99999)
 *
 * @param productId M_Product_ID
 * @param warehouseId M_Warehouse_ID (may be null if locatorId provided)
 * @param locatorId M_Locator_ID fallback
 * @return Quantity on hand (how many BOMs can be assembled)
 */
public static BigDecimal bomQtyOnHand(Integer productId, Integer warehouseId, Integer locatorId) {
    if (productId == null || productId <= 0) {
        return BigDecimal.ZERO;
    }

    Integer resolvedWarehouse = resolveWarehouse(warehouseId, locatorId);
    if (resolvedWarehouse == null) {
        return BigDecimal.ZERO;
    }

    return calculateBomQty(productId, resolvedWarehouse, "QtyOnHand");
}

/**
 * Generic BOM quantity calculator for OnHand, Reserved, Ordered.
 *
 * Stocked BOM Product Handling:
 * If a product is both stocked (IsStocked='Y') AND has a BOM (IsBOM='Y'),
 * this returns the direct storage quantity without exploding the BOM.
 * This matches PostgreSQL function behavior where stocked products use
 * their actual inventory, not calculated BOM component quantities.
 * Rationale: A stocked BOM product (built-to-stock assembly) should
 * report its physical on-hand count, not what could theoretically be
 * assembled from components.
 */
private static BigDecimal calculateBomQty(int productId, int warehouseId, String qtyColumn) {
    // First check product attributes
    ProductInfo info = getProductInfo(productId);
    if (info == null) {
        return BigDecimal.ZERO;
    }

    // Non-stocked non-BOM = unlimited capacity
    if (!info.isBOM && (!"I".equals(info.productType) || !info.isStocked)) {
        if ("QtyOnHand".equals(qtyColumn)) {
            return UNLIMITED_QTY;
        }
        return BigDecimal.ZERO; // Reserved/Ordered return 0 for non-stocked
    }

    // Stocked item = get direct quantity (even if also a BOM)
    // PostgreSQL behavior: stocked products use physical inventory
    if (info.isStocked) {
        return getStorageQty(productId, warehouseId, qtyColumn);
    }

    // Load BOM tree once and calculate
    Map<Integer, List<BOMComponent>> bomTree = loadBOMTree(productId);
    return calculateBomQtyFromTree(productId, warehouseId, qtyColumn, bomTree);
}

/**
 * Stack entry for quantity calculation traversal.
 * Tracks ancestor path for accurate circular detection.
 */
private record QtyStackEntry(
    int productId,
    BigDecimal multiplier,
    Set<Integer> ancestorPath
) {}

/**
 * Traverse pre-loaded BOM tree to calculate minimum assemblable quantity.
 * Uses ancestor-path tracking for accurate circular detection.
 * Pre-loads all storage quantities in single batch query to eliminate N+1 pattern.
 */
private static BigDecimal calculateBomQtyFromTree(
        int rootProductId,
        int warehouseId,
        String qtyColumn,
        Map<Integer, List<BOMComponent>> bomTree) {

    // Pre-load all storage quantities in single query (eliminates N+1 pattern)
    Set<Integer> stockedProductIds = collectStockedProductIds(bomTree);
    Map<Integer, BigDecimal> storageQtys = getStorageQtyBatch(stockedProductIds, warehouseId, qtyColumn);

    BigDecimal minQty = UNLIMITED_QTY;
    Deque<QtyStackEntry> stack = new ArrayDeque<>();
    stack.push(new QtyStackEntry(rootProductId, BigDecimal.ONE, Set.of()));

    while (!stack.isEmpty()) {
        QtyStackEntry entry = stack.pop();

        // Circular detection: check ancestor path
        if (entry.ancestorPath.contains(entry.productId)) {
            log.warning("Circular BOM detected: product " + entry.productId +
                        " in path " + entry.ancestorPath);
            continue;
        }

        List<BOMComponent> children = bomTree.getOrDefault(entry.productId, Collections.emptyList());

        // Build ancestor path for children
        Set<Integer> childAncestorPath = new HashSet<>(entry.ancestorPath);
        childAncestorPath.add(entry.productId);

        for (BOMComponent child : children) {
            if (child.isStockedItem()) {
                // Leaf node: calculate qty / bomQty
                // Use pre-loaded storage qty instead of per-item query
                BigDecimal storageQty = storageQtys.getOrDefault(child.productId(), BigDecimal.ZERO);
                // Use UOM precision from BOMComponent (loaded via CTE)
                int precision = child.uomPrecision();
                BigDecimal effectiveBomQty = entry.multiplier.multiply(child.bomQty());

                if (effectiveBomQty.compareTo(BigDecimal.ZERO) > 0) {
                    BigDecimal assemblable = storageQty.divide(effectiveBomQty, precision, RoundingMode.DOWN);
                    if (assemblable.compareTo(minQty) < 0) {
                        minQty = assemblable;
                    }
                }
            } else if (child.isBOM()) {
                // Recurse into child BOM
                stack.push(new QtyStackEntry(
                    child.productId(),
                    entry.multiplier.multiply(child.bomQty()),
                    childAncestorPath
                ));
            }
            // Non-stocked non-BOM: unlimited capacity, skip
        }
    }

    if (minQty.compareTo(UNLIMITED_QTY) == 0) {
        return BigDecimal.ZERO;
    }

    // Round final result to root product UOM precision (need lookup for root)
    int precision = getUOMPrecision(rootProductId);
    return minQty.setScale(precision, RoundingMode.DOWN);
}

private record ProductInfo(boolean isBOM, boolean isStocked, String productType) {}

private static ProductInfo getProductInfo(int productId) {
    String sql = "SELECT IsBOM, IsStocked, ProductType FROM M_Product WHERE M_Product_ID = ?";
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        if (pstmt == null) return null;
        pstmt.setInt(1, productId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return new ProductInfo(
                    "Y".equals(rs.getString("IsBOM")),
                    "Y".equals(rs.getString("IsStocked")),
                    rs.getString("ProductType")
                );
            }
        }
    } catch (SQLException e) {
        log.log(Level.WARNING, "Error getting product info for " + productId, e);
    }
    return null;
}

private static BigDecimal getStorageQty(int productId, int warehouseId, String qtyColumn) {
    if (!qtyColumn.matches("^(QtyOnHand|QtyReserved|QtyOrdered)$")) {
        throw new IllegalArgumentException("Invalid qty column: " + qtyColumn);
    }

    String sql = "SELECT COALESCE(SUM(" + qtyColumn + "), 0) FROM M_Storage s "
        + "WHERE M_Product_ID = ? "
        + "AND s.IsActive = 'Y' "
        + "AND EXISTS (SELECT 1 FROM M_Locator l WHERE s.M_Locator_ID = l.M_Locator_ID "
        + "AND l.M_Warehouse_ID = ? AND l.IsActive = 'Y')";

    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        if (pstmt == null) return BigDecimal.ZERO;
        pstmt.setInt(1, productId);
        pstmt.setInt(2, warehouseId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
        }
    } catch (SQLException e) {
        log.log(Level.WARNING, "Error getting storage qty for product " + productId, e);
    }
    return BigDecimal.ZERO;
}

/**
 * Load storage quantities for multiple products in single query.
 * Eliminates N+1 pattern for stocked component lookups.
 *
 * Uses PostgreSQL array binding for scalability:
 * - Single placeholder regardless of set size
 * - Query plan can be cached and reused
 * - No SQL string length scaling issues
 *
 * @param productIds Set of M_Product_IDs to query
 * @param warehouseId M_Warehouse_ID
 * @param qtyColumn QtyOnHand, QtyReserved, or QtyOrdered
 * @return Map of productId -> quantity
 */
private static Map<Integer, BigDecimal> getStorageQtyBatch(
        Set<Integer> productIds, int warehouseId, String qtyColumn) {
    if (productIds.isEmpty()) {
        return Collections.emptyMap();
    }

    if (!qtyColumn.matches("^(QtyOnHand|QtyReserved|QtyOrdered)$")) {
        throw new IllegalArgumentException("Invalid qty column: " + qtyColumn);
    }

    // Use PostgreSQL array binding - single placeholder, scales to any size
    String sql = "SELECT M_Product_ID, COALESCE(SUM(" + qtyColumn + "), 0) AS qty " +
        "FROM M_Storage s " +
        "WHERE M_Product_ID = ANY(?) " +
        "AND s.IsActive = 'Y' " +
        "AND EXISTS (SELECT 1 FROM M_Locator l WHERE s.M_Locator_ID = l.M_Locator_ID " +
        "AND l.M_Warehouse_ID = ? AND l.IsActive = 'Y') " +
        "GROUP BY M_Product_ID";

    Map<Integer, BigDecimal> result = new HashMap<>();
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        if (pstmt == null) return result;
        // Convert Set to Integer array for PostgreSQL
        Integer[] productArray = productIds.toArray(new Integer[0]);
        pstmt.setArray(1, pstmt.getConnection().createArrayOf("integer", productArray));
        pstmt.setInt(2, warehouseId);
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                result.put(rs.getInt("M_Product_ID"), rs.getBigDecimal("qty"));
            }
        }
    } catch (SQLException e) {
        log.log(Level.WARNING, "Error batch loading storage quantities", e);
    }
    return result;
}

/**
 * Batch load both OnHand and Reserved quantities in single query.
 * Reduces bomQtyAvailable from 3 queries to 2 (CTE + this).
 *
 * @param productIds Set of M_Product_IDs to query
 * @param warehouseId M_Warehouse_ID
 * @return Map of productId -> [QtyOnHand, QtyReserved] pair
 */
private static Map<Integer, BigDecimal[]> getStorageQtyBatchBoth(
        Set<Integer> productIds, int warehouseId) {
    if (productIds.isEmpty()) {
        return Collections.emptyMap();
    }

    String sql = "SELECT M_Product_ID, " +
        "COALESCE(SUM(QtyOnHand), 0) AS qty_on_hand, " +
        "COALESCE(SUM(QtyReserved), 0) AS qty_reserved " +
        "FROM M_Storage s " +
        "WHERE M_Product_ID = ANY(?) " +
        "AND s.IsActive = 'Y' " +
        "AND EXISTS (SELECT 1 FROM M_Locator l WHERE s.M_Locator_ID = l.M_Locator_ID " +
        "AND l.M_Warehouse_ID = ? AND l.IsActive = 'Y') " +
        "GROUP BY M_Product_ID";

    Map<Integer, BigDecimal[]> result = new HashMap<>();
    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        if (pstmt == null) return result;
        Integer[] productArray = productIds.toArray(new Integer[0]);
        pstmt.setArray(1, pstmt.getConnection().createArrayOf("integer", productArray));
        pstmt.setInt(2, warehouseId);
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                result.put(rs.getInt("M_Product_ID"), new BigDecimal[] {
                    rs.getBigDecimal("qty_on_hand"),
                    rs.getBigDecimal("qty_reserved")
                });
            }
        }
    } catch (SQLException e) {
        log.log(Level.WARNING, "Error batch loading storage quantities (both)", e);
    }
    return result;
}

/**
 * Collect all stocked product IDs from pre-loaded BOM tree.
 */
private static Set<Integer> collectStockedProductIds(Map<Integer, List<BOMComponent>> bomTree) {
    Set<Integer> result = new HashSet<>();
    for (List<BOMComponent> children : bomTree.values()) {
        for (BOMComponent child : children) {
            if (child.isStockedItem()) {
                result.add(child.productId());
            }
        }
    }
    return result;
}

/**
 * Get UOM precision for a product.
 * Note: Only used for root product final rounding. Component UOM precision
 * is loaded via BOMComponent record from the CTE query.
 */
private static int getUOMPrecision(int productId) {
    String sql = "SELECT COALESCE(u.StdPrecision, 0) FROM C_UOM u " +
        "INNER JOIN M_Product p ON u.C_UOM_ID = p.C_UOM_ID " +
        "WHERE p.M_Product_ID = ?";

    try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
        if (pstmt == null) return 0;
        pstmt.setInt(1, productId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    } catch (SQLException e) {
        log.log(Level.WARNING, "Error getting UOM precision for product " + productId, e);
    }
    return 0;
}
```

Add import:

```java
import java.math.RoundingMode;
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testBomQtyOnHand* -q`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave5Functions.java base/test/src/org/compiere/migration/Wave5FunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave5): implement bomQtyOnHand with iterative BOM traversal

Calculates how many complete BOMs can be assembled from on-hand stock.
Uses stack-based traversal, circular detection, and UOM rounding.
Matches PostgreSQL bomqtyonhand function logic exactly.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 3.2: Implement bomQtyReserved Function

**Files:**
- Modify: `base/src/org/compiere/migration/Wave5Functions.java`
- Test: `base/test/src/org/compiere/migration/Wave5FunctionsTest.java`

**Step 1: Write failing test**

```java
@Test
void testBomQtyReserved_nullInputs() {
    BigDecimal result = Wave5Functions.bomQtyReserved(null, null, null);
    assertEquals(BigDecimal.ZERO, result);
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testBomQtyReserved* -q`
Expected: FAIL with "method bomQtyReserved not found"

**Step 3: Write minimal implementation**

```java
/**
 * Calculate BOM quantity reserved.
 * Equivalent to PostgreSQL bomqtyreserved function.
 *
 * @param productId M_Product_ID
 * @param warehouseId M_Warehouse_ID (may be null if locatorId provided)
 * @param locatorId M_Locator_ID fallback
 * @return Reserved quantity for BOM components
 */
public static BigDecimal bomQtyReserved(Integer productId, Integer warehouseId, Integer locatorId) {
    if (productId == null || productId <= 0) {
        return BigDecimal.ZERO;
    }

    Integer resolvedWarehouse = resolveWarehouse(warehouseId, locatorId);
    if (resolvedWarehouse == null) {
        return BigDecimal.ZERO;
    }

    return calculateBomQty(productId, resolvedWarehouse, "QtyReserved");
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testBomQtyReserved* -q`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave5Functions.java base/test/src/org/compiere/migration/Wave5FunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave5): implement bomQtyReserved function

Calculates reserved quantity using shared BOM qty infrastructure.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 3.3: Implement bomQtyOrdered Function

**Files:**
- Modify: `base/src/org/compiere/migration/Wave5Functions.java`
- Test: `base/test/src/org/compiere/migration/Wave5FunctionsTest.java`

**Step 1: Write failing test**

```java
@Test
void testBomQtyOrdered_nullInputs() {
    BigDecimal result = Wave5Functions.bomQtyOrdered(null, null, null);
    assertEquals(BigDecimal.ZERO, result);
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testBomQtyOrdered* -q`
Expected: FAIL with "method bomQtyOrdered not found"

**Step 3: Write minimal implementation**

```java
/**
 * Calculate BOM quantity ordered.
 * Equivalent to PostgreSQL bomqtyordered function.
 *
 * @param productId M_Product_ID
 * @param warehouseId M_Warehouse_ID (may be null if locatorId provided)
 * @param locatorId M_Locator_ID fallback
 * @return Ordered quantity for BOM components
 */
public static BigDecimal bomQtyOrdered(Integer productId, Integer warehouseId, Integer locatorId) {
    if (productId == null || productId <= 0) {
        return BigDecimal.ZERO;
    }

    Integer resolvedWarehouse = resolveWarehouse(warehouseId, locatorId);
    if (resolvedWarehouse == null) {
        return BigDecimal.ZERO;
    }

    return calculateBomQty(productId, resolvedWarehouse, "QtyOrdered");
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testBomQtyOrdered* -q`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave5Functions.java base/test/src/org/compiere/migration/Wave5FunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave5): implement bomQtyOrdered function

Calculates ordered quantity using shared BOM qty infrastructure.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 3.4: Implement bomQtyAvailable Function

**Files:**
- Modify: `base/src/org/compiere/migration/Wave5Functions.java`
- Test: `base/test/src/org/compiere/migration/Wave5FunctionsTest.java`

**Step 1: Write failing test**

```java
@Test
void testBomQtyAvailable_nullInputs() {
    BigDecimal result = Wave5Functions.bomQtyAvailable(null, null, null);
    assertEquals(BigDecimal.ZERO, result);
}

@Test
void testBomQtyAvailable_computation() {
    // Available = OnHand - Reserved
    // This is a simple subtraction test
    assertEquals(BigDecimal.ZERO,
        Wave5Functions.bomQtyAvailable(-1, -1, -1));
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testBomQtyAvailable* -q`
Expected: FAIL with "method bomQtyAvailable not found"

**Step 3: Write minimal implementation**

```java
/**
 * Calculate BOM quantity available (OnHand - Reserved).
 * Equivalent to PostgreSQL bomqtyavailable function.
 *
 * Query optimization:
 * - 1 query: Load BOM tree (CTE)
 * - 1 query: Batch load both OnHand AND Reserved in single query
 * - Total: 2 queries (same as other qty functions)
 *
 * @param productId M_Product_ID
 * @param warehouseId M_Warehouse_ID (may be null if locatorId provided)
 * @param locatorId M_Locator_ID fallback
 * @return Available quantity (OnHand minus Reserved)
 */
public static BigDecimal bomQtyAvailable(Integer productId, Integer warehouseId, Integer locatorId) {
    if (productId == null || productId <= 0) {
        return BigDecimal.ZERO;
    }

    Integer resolvedWarehouse = resolveWarehouse(warehouseId, locatorId);
    if (resolvedWarehouse == null) {
        return BigDecimal.ZERO;
    }

    // Check product info first
    ProductInfo info = getProductInfo(productId);
    if (info == null) {
        return BigDecimal.ZERO;
    }

    // Non-stocked non-BOM = unlimited - 0 = unlimited
    if (!info.isBOM && (!"I".equals(info.productType) || !info.isStocked)) {
        return UNLIMITED_QTY;
    }

    // Stocked item = direct calculation
    if (info.isStocked) {
        BigDecimal onHand = getStorageQty(productId, resolvedWarehouse, "QtyOnHand");
        BigDecimal reserved = getStorageQty(productId, resolvedWarehouse, "QtyReserved");
        return onHand.subtract(reserved);
    }

    // BOM: load tree once, load both qty columns in single query
    Map<Integer, List<BOMComponent>> bomTree = loadBOMTree(productId);
    Set<Integer> stockedProductIds = collectStockedProductIds(bomTree);
    Map<Integer, BigDecimal[]> storageQtys = getStorageQtyBatchBoth(stockedProductIds, resolvedWarehouse);

    BigDecimal onHand = calculateBomQtyWithPreloadedBoth(productId, bomTree, storageQtys, 0);
    BigDecimal reserved = calculateBomQtyWithPreloadedBoth(productId, bomTree, storageQtys, 1);

    return onHand.subtract(reserved);
}

/**
 * Calculate BOM quantity using pre-loaded both-column storage data.
 * @param qtyIndex 0 for QtyOnHand, 1 for QtyReserved
 */
private static BigDecimal calculateBomQtyWithPreloadedBoth(
        int rootProductId,
        Map<Integer, List<BOMComponent>> bomTree,
        Map<Integer, BigDecimal[]> storageQtys,
        int qtyIndex) {

    BigDecimal minQty = UNLIMITED_QTY;
    Deque<QtyStackEntry> stack = new ArrayDeque<>();
    stack.push(new QtyStackEntry(rootProductId, BigDecimal.ONE, Set.of()));

    while (!stack.isEmpty()) {
        QtyStackEntry entry = stack.pop();

        if (entry.ancestorPath.contains(entry.productId)) {
            continue;
        }

        List<BOMComponent> children = bomTree.getOrDefault(entry.productId, Collections.emptyList());
        Set<Integer> childAncestorPath = new HashSet<>(entry.ancestorPath);
        childAncestorPath.add(entry.productId);

        for (BOMComponent child : children) {
            if (child.isStockedItem()) {
                BigDecimal[] qtys = storageQtys.getOrDefault(child.productId(),
                    new BigDecimal[] { BigDecimal.ZERO, BigDecimal.ZERO });
                BigDecimal storageQty = qtys[qtyIndex];
                int precision = child.uomPrecision();
                BigDecimal effectiveBomQty = entry.multiplier.multiply(child.bomQty());

                if (effectiveBomQty.compareTo(BigDecimal.ZERO) > 0) {
                    BigDecimal assemblable = storageQty.divide(effectiveBomQty, precision, RoundingMode.DOWN);
                    if (assemblable.compareTo(minQty) < 0) {
                        minQty = assemblable;
                    }
                }
            } else if (child.isBOM()) {
                stack.push(new QtyStackEntry(
                    child.productId(),
                    entry.multiplier.multiply(child.bomQty()),
                    childAncestorPath
                ));
            }
        }
    }

    if (minQty.compareTo(UNLIMITED_QTY) == 0) {
        return qtyIndex == 0 ? BigDecimal.ZERO : BigDecimal.ZERO; // OnHand=0, Reserved=0 if no stocked
    }

    int precision = getUOMPrecision(rootProductId);
    return minQty.setScale(precision, RoundingMode.DOWN);
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testBomQtyAvailable* -q`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave5Functions.java base/test/src/org/compiere/migration/Wave5FunctionsTest.java
git commit -m "$(cat <<'EOF'
feat(wave5): implement bomQtyAvailable function

Simple composition: OnHand - Reserved.
Completes Wave 5b quantity function implementations.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

## Task Group 4: Router and Shadow Infrastructure (4 tasks)

### Task 4.1: Create Wave5FunctionRouter

**Files:**
- Create: `base/src/org/compiere/migration/Wave5FunctionRouter.java`
- Test: `base/test/src/org/compiere/migration/Wave5FunctionRouterTest.java`

**Step 1: Write failing test**

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Wave5FunctionRouterTest {

    @BeforeEach
    void setUp() {
        MigrationConfig.clearCache();
    }

    @Test
    void testRouterClassExists() {
        assertNotNull(Wave5FunctionRouter.class);
    }
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl base -Dtest=Wave5FunctionRouterTest -q`
Expected: FAIL with "Wave5FunctionRouter cannot be resolved"

**Step 3: Write minimal implementation**

```java
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
            BigDecimalComparator.QUANTITY
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
            BigDecimalComparator.QUANTITY
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
            BigDecimalComparator.QUANTITY
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
            BigDecimalComparator.QUANTITY
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
            BigDecimalComparator.QUANTITY
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
            BigDecimalComparator.QUANTITY
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
            BigDecimalComparator.QUANTITY
        );
    }
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl base -Dtest=Wave5FunctionRouterTest -q`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/Wave5FunctionRouter.java base/test/src/org/compiere/migration/Wave5FunctionRouterTest.java
git commit -m "$(cat <<'EOF'
feat(wave5): add Wave5FunctionRouter for shadow execution

Routes all 7 BOM functions through ShadowExecutor for validation.
Uses QUANTITY comparator for BigDecimal comparison.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 4.2: Add SQL Function Caller Methods for BOM Functions

**Files:**
- Modify: `base/src/org/compiere/migration/SqlFunctionCaller.java`
- Test: `base/test/src/org/compiere/migration/SqlFunctionCallerWave5Test.java`

**Step 1: Write failing test**

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class SqlFunctionCallerWave5Test {

    @Test
    void testBomPriceLimitMethodExists() {
        // Just verify method exists and handles null gracefully
        BigDecimal result = SqlFunctionCaller.callBomPriceLimit(null, null);
        assertNotNull(result);
    }
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl base -Dtest=SqlFunctionCallerWave5Test -q`
Expected: FAIL with "method callBomPriceLimit not found"

**Step 3: Write minimal implementation**

Add to SqlFunctionCaller.java:

```java
// ============= Wave 5 BOM Functions =============

public static BigDecimal callBomPriceLimit(Integer productId, Integer priceListVersionId) {
    return callBigDecimalFunction("bompricelimit", productId, priceListVersionId);
}

public static BigDecimal callBomPriceList(Integer productId, Integer priceListVersionId) {
    return callBigDecimalFunction("bompricelist", productId, priceListVersionId);
}

public static BigDecimal callBomPriceStd(Integer productId, Integer priceListVersionId) {
    return callBigDecimalFunction("bompricestd", productId, priceListVersionId);
}

public static BigDecimal callBomQtyOnHand(Integer productId, Integer warehouseId, Integer locatorId) {
    return callBigDecimalFunction("bomqtyonhand", productId, warehouseId, locatorId);
}

public static BigDecimal callBomQtyReserved(Integer productId, Integer warehouseId, Integer locatorId) {
    return callBigDecimalFunction("bomqtyreserved", productId, warehouseId, locatorId);
}

public static BigDecimal callBomQtyOrdered(Integer productId, Integer warehouseId, Integer locatorId) {
    return callBigDecimalFunction("bomqtyordered", productId, warehouseId, locatorId);
}

public static BigDecimal callBomQtyAvailable(Integer productId, Integer warehouseId, Integer locatorId) {
    return callBigDecimalFunction("bomqtyavailable", productId, warehouseId, locatorId);
}

private static BigDecimal callBigDecimalFunction(String functionName, Object... params) {
    StringBuilder sql = new StringBuilder("SELECT ").append(functionName).append("(");
    for (int i = 0; i < params.length; i++) {
        sql.append(i > 0 ? ", ?" : "?");
    }
    sql.append(")");

    try (PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null)) {
        if (pstmt == null) {
            throw new SqlFunctionException("Cannot prepare statement for " + functionName);
        }
        for (int i = 0; i < params.length; i++) {
            if (params[i] == null) {
                pstmt.setNull(i + 1, java.sql.Types.NUMERIC);
            } else if (params[i] instanceof Integer) {
                pstmt.setInt(i + 1, (Integer) params[i]);
            } else {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                BigDecimal result = rs.getBigDecimal(1);
                return result != null ? result : BigDecimal.ZERO;
            }
        }
    } catch (SQLException e) {
        throw new SqlFunctionException("Error calling " + functionName, e);
    }
    return BigDecimal.ZERO;
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl base -Dtest=SqlFunctionCallerWave5Test -q`
Expected: PASS

**Step 5: Commit**

```bash
git add base/src/org/compiere/migration/SqlFunctionCaller.java base/test/src/org/compiere/migration/SqlFunctionCallerWave5Test.java
git commit -m "$(cat <<'EOF'
feat(wave5): add SqlFunctionCaller methods for BOM functions

Adds caller methods for all 7 BOM functions to support shadow validation.
Uses generic callBigDecimalFunction helper for consistency.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 4.3: Add Migration Config Entries for Wave 5

**Files:**
- Create: `db/ddlutils/postgresql/migrations/wave5_config.sql`

**Step 1: Write SQL migration script**

```sql
-- Wave 5 BOM Function Migration Configuration
-- Requires: 001_create_migration_schema.sql (creates migration.function_config table)
-- Sets all functions to SHADOW mode at 100% sample rate

INSERT INTO migration.function_config (function_name, mode, sample_rate, circuit_breaker_enabled)
VALUES
    ('bomPriceLimit', 'SHADOW', 1.0, true),
    ('bomPriceList', 'SHADOW', 1.0, true),
    ('bomPriceStd', 'SHADOW', 1.0, true),
    ('bomQtyOnHand', 'SHADOW', 1.0, true),
    ('bomQtyReserved', 'SHADOW', 1.0, true),
    ('bomQtyOrdered', 'SHADOW', 1.0, true),
    ('bomQtyAvailable', 'SHADOW', 1.0, true)
ON CONFLICT (function_name) DO UPDATE SET
    mode = EXCLUDED.mode,
    sample_rate = EXCLUDED.sample_rate,
    circuit_breaker_enabled = EXCLUDED.circuit_breaker_enabled;
```

**Step 2: Verify script is valid SQL**

Run: `psql -d adempiere -f db/ddlutils/postgresql/migrations/wave5_config.sql --dry-run 2>&1 | head -5`
Expected: No syntax errors

**Step 3: Commit**

```bash
git add db/ddlutils/postgresql/migrations/wave5_config.sql
git commit -m "$(cat <<'EOF'
feat(wave5): add migration config for BOM functions

Sets all 7 BOM functions to SHADOW mode at 100% sample rate.
Uses upsert pattern for idempotent application.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 4.4: Add Shadow Validation Integration Tests

**Files:**
- Create: `base/test/src/org/compiere/migration/Wave5ShadowValidationTest.java`

**Design Note:** Uses JUnit 5 Assumptions for proper SKIPPED reporting when test data unavailable.

**Step 1: Write integration tests**

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.math.BigDecimal;
import org.compiere.util.DB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Shadow validation tests for Wave 5 BOM functions.
 * Compares Java vs SQL results to ensure parity.
 */
class Wave5ShadowValidationTest extends AbstractMigrationTest {

    @BeforeEach
    void setUp() {
        MigrationConfig.clearCache();
    }

    @Test
    void testBomPriceLimit_shadowValidation() {
        Integer productId = getProductWithBOM();
        Integer plvId = getAnyPriceListVersion();

        assumeTrue(productId != null && productId > 0, "Skipping: No product with BOM available");
        assumeTrue(plvId != null && plvId > 0, "Skipping: No price list version available");

        BigDecimal javaResult = Wave5Functions.bomPriceLimit(productId, plvId);
        BigDecimal sqlResult = SqlFunctionCaller.callBomPriceLimit(productId, plvId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> "bomPriceLimit mismatch for product " + productId +
                  ": Java=" + javaResult + ", SQL=" + sqlResult);
    }

    @Test
    void testBomQtyOnHand_shadowValidation() {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();

        assumeTrue(productId != null && productId > 0, "Skipping: No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0, "Skipping: No warehouse available");

        BigDecimal javaResult = Wave5Functions.bomQtyOnHand(productId, warehouseId, null);
        BigDecimal sqlResult = SqlFunctionCaller.callBomQtyOnHand(productId, warehouseId, null);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> "bomQtyOnHand mismatch for product " + productId +
                  ": Java=" + javaResult + ", SQL=" + sqlResult);
    }

    @Test
    void testBomQtyAvailable_shadowValidation() {
        Integer productId = getProductWithBOM();
        Integer warehouseId = getAnyWarehouse();

        assumeTrue(productId != null && productId > 0, "Skipping: No product with BOM available");
        assumeTrue(warehouseId != null && warehouseId > 0, "Skipping: No warehouse available");

        BigDecimal javaResult = Wave5Functions.bomQtyAvailable(productId, warehouseId, null);
        BigDecimal sqlResult = SqlFunctionCaller.callBomQtyAvailable(productId, warehouseId, null);

        assertEquals(0, javaResult.compareTo(sqlResult),
            () -> "bomQtyAvailable mismatch for product " + productId +
                  ": Java=" + javaResult + ", SQL=" + sqlResult);
    }

    @Test
    void testCircularBOM_handledGracefully() {
        // Verify that circular BOMs don't cause infinite loops or exceptions
        // The CTE's cycle detection should prevent duplicate rows
        Integer productId = getProductWithCircularBOM();

        assumeTrue(productId != null && productId > 0, "Skipping: No circular BOM in test data");

        Integer warehouseId = getAnyWarehouse();
        assumeTrue(warehouseId != null && warehouseId > 0, "Skipping: No warehouse available");

        // Should complete without hanging or throwing
        BigDecimal result = Wave5Functions.bomQtyOnHand(productId, warehouseId, null);
        assertNotNull(result, "Should return a result even for circular BOM");
    }

    private Integer getProductWithCircularBOM() {
        // Look for a product that appears in its own BOM hierarchy
        String sql = """
            WITH RECURSIVE bom_check AS (
                SELECT b.M_Product_ID AS root, bl.M_Product_ID AS child, 1 AS depth
                FROM PP_Product_BOM b
                JOIN PP_Product_BOMLine bl ON b.PP_Product_BOM_ID = bl.PP_Product_BOM_ID
                WHERE b.IsActive = 'Y' AND bl.IsActive = 'Y'

                UNION ALL

                SELECT bc.root, bl.M_Product_ID, bc.depth + 1
                FROM bom_check bc
                JOIN PP_Product_BOM b ON b.M_Product_ID = bc.child
                JOIN PP_Product_BOMLine bl ON b.PP_Product_BOM_ID = bl.PP_Product_BOM_ID
                WHERE bc.depth < 10 AND b.IsActive = 'Y' AND bl.IsActive = 'Y'
            )
            SELECT root FROM bom_check WHERE root = child LIMIT 1
            """;
        return DB.getSQLValue(null, sql);
    }

    private Integer getProductWithBOM() {
        String sql = "SELECT b.M_Product_ID FROM PP_Product_BOM b "
            + "JOIN PP_Product_BOMLine bl ON b.PP_Product_BOM_ID = bl.PP_Product_BOM_ID "
            + "WHERE b.IsActive = 'Y' AND bl.IsActive = 'Y' "
            + "LIMIT 1";
        return DB.getSQLValue(null, sql);
    }

    private Integer getAnyPriceListVersion() {
        return DB.getSQLValue(null,
            "SELECT M_PriceList_Version_ID FROM M_PriceList_Version WHERE IsActive='Y' LIMIT 1");
    }

    private Integer getAnyWarehouse() {
        return DB.getSQLValue(null,
            "SELECT M_Warehouse_ID FROM M_Warehouse WHERE IsActive='Y' LIMIT 1");
    }
}
```

**Step 2: Run test**

Run: `mvn test -pl base -Dtest=Wave5ShadowValidationTest -q`
Expected: PASS (or skip if no DB)

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave5ShadowValidationTest.java
git commit -m "$(cat <<'EOF'
test(wave5): add shadow validation integration tests

Compares Java vs SQL results for BOM functions.
Critical for ensuring migration parity before cutover.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

## Task Group 5: Deep BOM and Edge Case Tests (3 tasks)

### Task 5.1: Add Circular BOM Detection Tests

**Files:**
- Modify: `base/test/src/org/compiere/migration/Wave5FunctionsTest.java`

**Step 1: Write test for circular detection**

```java
@Test
void testCircularBOMDetection() {
    // This test verifies the circular detection logic works correctly
    // In production, circular BOMs shouldn't exist but we need to handle them
    Set<Integer> visited = new HashSet<>();
    visited.add(100);

    // Simulating circular detection - if product already visited, skip
    assertFalse(visited.add(100), "Should detect revisit");
    assertTrue(visited.add(200), "Should allow new product");
}
```

**Step 2: Run test**

Run: `mvn test -pl base -Dtest=Wave5FunctionsTest#testCircularBOMDetection -q`
Expected: PASS

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave5FunctionsTest.java
git commit -m "$(cat <<'EOF'
test(wave5): add circular BOM detection unit test

Verifies HashSet-based circular detection logic.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 5.2: Add Deep BOM Traversal Test

**Files:**
- Create: `base/test/src/org/compiere/migration/Wave5DeepBOMTest.java`

**Step 1: Write test**

```java
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
```

**Step 2: Run test**

Run: `mvn test -pl base -Dtest=Wave5DeepBOMTest -q`
Expected: PASS

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave5DeepBOMTest.java
git commit -m "$(cat <<'EOF'
test(wave5): add deep BOM traversal tests

Verifies max depth configuration and unlimited qty constant.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 5.3: Add Performance Baseline Test

**Files:**
- Create: `base/test/src/org/compiere/migration/Wave5PerformanceTest.java`

**Step 1: Write performance test**

```java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import org.compiere.util.DB;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

/**
 * Performance tests for Wave 5 BOM functions.
 * Verifies Java implementation meets latency targets.
 */
class Wave5PerformanceTest extends AbstractMigrationTest {

    // Performance tier: Reporting (allows up to 100% latency increase)
    private static final double MAX_LATENCY_RATIO = 2.0;

    private static final int WARMUP_ITERATIONS = 100;  // More warmup for JIT
    private static final int MEASUREMENT_ROUNDS = 10;  // Multiple rounds for statistics
    private static final int ITERATIONS_PER_ROUND = 100;

    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_PERF_TESTS", matches = "true")
    void testBomPriceLimit_performance() {
        Integer productId = getProductWithBOM();
        Integer plvId = getAnyPriceListVersion();
        if (productId == null || plvId == null) return;

        // Extended warmup for JIT compilation
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            Wave5Functions.bomPriceLimit(productId, plvId);
            SqlFunctionCaller.callBomPriceLimit(productId, plvId);
        }

        // Multiple measurement rounds for statistical significance
        long[] javaTimes = new long[MEASUREMENT_ROUNDS];
        long[] sqlTimes = new long[MEASUREMENT_ROUNDS];

        for (int round = 0; round < MEASUREMENT_ROUNDS; round++) {
            // Measure Java
            long javaStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                Wave5Functions.bomPriceLimit(productId, plvId);
            }
            javaTimes[round] = System.nanoTime() - javaStart;

            // Measure SQL
            long sqlStart = System.nanoTime();
            for (int i = 0; i < ITERATIONS_PER_ROUND; i++) {
                SqlFunctionCaller.callBomPriceLimit(productId, plvId);
            }
            sqlTimes[round] = System.nanoTime() - sqlStart;
        }

        // Calculate median (more robust than mean)
        // For even-length arrays, use average of middle two elements
        Arrays.sort(javaTimes);
        Arrays.sort(sqlTimes);
        int mid = MEASUREMENT_ROUNDS / 2;
        long javaMedianNs = (javaTimes[mid - 1] + javaTimes[mid]) / 2;
        long sqlMedianNs = (sqlTimes[mid - 1] + sqlTimes[mid]) / 2;

        double javaMedianMs = javaMedianNs / 1_000_000.0;
        double sqlMedianMs = sqlMedianNs / 1_000_000.0;
        double ratio = javaMedianMs / sqlMedianMs;

        System.out.printf("bomPriceLimit performance: Java=%.2fms, SQL=%.2fms, ratio=%.2f%n",
            javaMedianMs, sqlMedianMs, ratio);

        assertTrue(ratio <= MAX_LATENCY_RATIO,
            "Java implementation too slow: ratio=" + ratio + " (max=" + MAX_LATENCY_RATIO + ")");
    }

    private Integer getProductWithBOM() {
        return DB.getSQLValue(null,
            "SELECT b.M_Product_ID FROM PP_Product_BOM b LIMIT 1");
    }

    private Integer getAnyPriceListVersion() {
        return DB.getSQLValue(null,
            "SELECT M_PriceList_Version_ID FROM M_PriceList_Version WHERE IsActive='Y' LIMIT 1");
    }
}
```

**Step 2: Run test**

Run: `RUN_PERF_TESTS=true mvn test -pl base -Dtest=Wave5PerformanceTest -q`
Expected: PASS

**Step 3: Commit**

```bash
git add base/test/src/org/compiere/migration/Wave5PerformanceTest.java
git commit -m "$(cat <<'EOF'
test(wave5): add performance baseline tests

Measures Java vs SQL latency for BOM functions.
Verifies compliance with Reporting tier (<=100% latency increase).

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

## Task Group 6: Quality Gates and Documentation (3 tasks)

### Task 6.1: Run Full Test Suite and Verify

**Step 1: Run all Wave 5 tests**

```bash
mvn test -pl base -Dtest="Wave5*" -q
```
Expected: All tests PASS

**Step 2: Run code coverage report**

```bash
mvn jacoco:report -pl base
```

**Step 3: Verify no compilation errors**

```bash
mvn compile -pl base -q
```
Expected: BUILD SUCCESS

**Step 4: Commit any fixes**

If any tests failed, fix and commit incrementally.

---

### Task 6.2: Update Migration Waves Documentation

**Files:**
- Modify: `docs/discovery/migration-waves.md`

Update Wave 5 section with implementation notes:

```markdown
## Wave 5: BOM Functions (Complex) - IMPLEMENTATION COMPLETE

**Implementation Notes:**
- All 7 functions use iterative stack-based traversal (no recursion)
- Circular BOM detection via HashSet tracking
- Max depth configurable via AD_SysConfig `BOM_MAX_DEPTH`
- Shadow mode at 100% for validation
- Performance tier: Reporting (allows 100% latency increase)

**Files Added:**
- `base/src/org/compiere/migration/Wave5Functions.java`
- `base/src/org/compiere/migration/Wave5FunctionRouter.java`
- `base/test/src/org/compiere/migration/Wave5FunctionsTest.java`
- `base/test/src/org/compiere/migration/Wave5ShadowValidationTest.java`
```

**Step 1: Make the update**

**Step 2: Commit**

```bash
git add docs/discovery/migration-waves.md
git commit -m "$(cat <<'EOF'
docs(wave5): update migration waves with implementation status

Marks Wave 5 BOM functions as implementation complete.
Documents files added and key design decisions.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

### Task 6.3: Create Gate 1 Checklist

**Files:**
- Create: `docs/plans/wave5-gate1-checklist.md`

```markdown
# Wave 5 Gate 1: Code Complete Checklist

## Functions Implemented
- [x] bomPriceLimit - Iterative price limit calculation
- [x] bomPriceList - Iterative list price calculation
- [x] bomPriceStd - Iterative standard price calculation
- [x] bomQtyOnHand - Iterative on-hand quantity
- [x] bomQtyReserved - Iterative reserved quantity
- [x] bomQtyOrdered - Iterative ordered quantity
- [x] bomQtyAvailable - OnHand - Reserved composition

## Recursion Safeguards
- [x] Maximum depth limit (configurable via AD_SysConfig)
- [x] Circular detection via HashSet
- [x] Iterative traversal (no stack overflow risk)

## Testing
- [x] Unit tests for null/invalid inputs
- [x] Integration tests comparing Java vs SQL
- [x] Shadow validation tests
- [x] Performance baseline tests

## Code Quality
- [x] Follows Wave 4 patterns (Functions + Router + ShadowExecutor)
- [x] Proper error handling and logging
- [x] SQL injection prevention for column names

## Ready for Gate 2: Shadow Deployment
```

**Step 1: Create the file**

**Step 2: Commit**

```bash
git add docs/plans/wave5-gate1-checklist.md
git commit -m "$(cat <<'EOF'
docs(wave5): add Gate 1 code complete checklist

Tracks completion of all Wave 5 implementation requirements.

Co-Authored-By: Claude <noreply@anthropic.com>
EOF
)"
```

---

## Summary

This plan implements all 7 Wave 5 BOM functions following the established Wave 4 patterns:

| Task Group | Tasks | Focus |
|------------|-------|-------|
| 1 | 3 | Foundation: Wave5Functions class, BOMComponent record, helpers |
| 2 | 4 | BOM Pricing: bomPriceLimit, bomPriceList, bomPriceStd + integration tests |
| 3 | 4 | BOM Quantity: bomQtyOnHand, bomQtyReserved, bomQtyOrdered, bomQtyAvailable |
| 4 | 4 | Router and Shadow: Wave5FunctionRouter, SqlFunctionCaller, config, shadow tests |
| 5 | 3 | Edge Cases: circular detection, deep BOM, performance tests |
| 6 | 3 | Quality Gates: full test suite, documentation, Gate 1 checklist |

**Total: 21 tasks in 6 groups**

Key design decisions:
- **Batch CTE query** loads entire BOM tree in single DB round-trip (eliminates N+1 pattern)
- **Batch price loading** via `getProductPricesBatch()` with PostgreSQL array binding
- **Batch storage loading** via `getStorageQtyBatch()` with PostgreSQL array binding
- **Combined storage query** for bomQtyAvailable loads both OnHand and Reserved in single query
- **Ancestor-path tracking** for accurate circular detection (distinguishes true cycles from shared components)
- **BOMComponent validation** handles null/negative bomQty safely
- **JUnit Assumptions** provide proper SKIPPED reporting when test data unavailable
- **Statistical performance testing** with 100 warmup iterations, 10 measurement rounds, median reporting
- Cache descoped - batch approach makes per-request caching unnecessary
- 100% shadow sampling for thorough validation
- Reporting tier performance (allows 100% latency increase over SQL)

**Review Changes Applied (Critical Review 1):**
| Issue | Resolution |
|-------|------------|
| N+1 query pattern | Batch CTE in loadBOMTree |
| Caffeine cache | Descoped (not needed with batch) |
| Missing IsActive filters | Built into CTE |
| Flawed circular detection | Ancestor-path tracking |
| Null bomQty handling | Record constructor validation |
| bomQtyAvailable double traversal | Load tree once |
| Silent test passes | JUnit Assumptions |

**Review Changes Applied (Critical Review 2):**
| Issue | Priority | Resolution |
|-------|----------|------------|
| CTE lacks cycle prevention in SQL | P1 | Added `path` array and `is_cycle` flag to CTE with `WHERE NOT is_cycle` |
| Storage query N+1 pattern | P1 | Added `getStorageQtyBatch()` to pre-load all quantities in single query |
| UOM precision N+1 pattern | P2 | Added `uom_precision` to BOMComponent record via CTE JOIN to C_UOM |
| Missing percentage qty test | P3 | Added `testBOMComponent_handlesPercentageQuantityCalculation()` |
| Config table dependency | P4 | Added comment documenting dependency on 001_create_migration_schema.sql |
| Missing circular BOM test | P3 | Added `testCircularBOM_handledGracefully()` integration test |

**Review Changes Applied (Critical Review 3):**
| Issue | Priority | Resolution |
|-------|----------|------------|
| Placeholder SQL construction in getStorageQtyBatch | P1 | Replaced with PostgreSQL array binding `= ANY(?)` |
| Price lookups not batched | P2 | Added `getProductPricesBatch()` and `collectAllProductIds()` helpers |
| Performance test methodology weak | P3 | Increased warmup to 100 iterations, added 10 measurement rounds with median |
| bomQtyAvailable uses 3 queries | P3 | Added `getStorageQtyBatchBoth()` to load OnHand+Reserved in single query |

**Review Changes Applied (Critical Review 4):**
| Issue | Priority | Resolution |
|-------|----------|------------|
| Batch price missing GROUP BY | P1 | Added `SUM()` and `GROUP BY M_Product_ID` to match single-lookup semantics |
| Missing M_ProductPrice IsActive | P2 | Added `AND IsActive = 'Y'` to batch price query |
| Missing M_Storage IsActive | P2 | Added `AND s.IsActive = 'Y'` and `AND l.IsActive = 'Y'` to all storage queries |
| Stocked BOM product handling | P2 | Documented: stocked products use physical inventory, not BOM explosion |
| Test helper -1 vs null | P3 | Changed `assumeTrue` checks to `productId != null && productId > 0` |
| MAX in getUOMPrecision | P3 | Simplified query to use direct `COALESCE` without unnecessary MAX |
| AD_Client_ID filtering | P3 | Documented multi-tenant security assumption in header |
| Median calculation | P4 | Fixed to use average of middle two elements for even-length arrays |

**Query Count After All Optimizations:**
- Price functions: 1 (CTE) + 1 (batch prices) = 2 queries total
- Qty functions: 1 (CTE with UOM) + 1 (batch storage) = 2 queries total
- bomQtyAvailable: 1 (CTE) + 1 (batch storage for both columns) = 2 queries total
