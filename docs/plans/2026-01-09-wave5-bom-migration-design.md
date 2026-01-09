# Wave 5: BOM Function Migration Design

**Date:** 2026-01-09
**Status:** Design Complete
**Parent Document:** `docs/plans/2026-01-01-postgresql-function-migration-design.md`
**Reference:** `docs/discovery/migration-waves.md`

---

## 1. Scope and Goals

**Goal:** Migrate 7 recursive Bill of Materials functions from PostgreSQL to Java

**Effort:** 9-12 days

**Dependencies:** None (self-contained - can run in parallel with Wave 3)

**Functions:**

| Sub-Wave | Function | File | LOC | Description |
|----------|----------|------|-----|-------------|
| **5a: Pricing** | bomPriceLimit | BOM_PriceLimit.sql | 51 | Recursive price limit calculation |
| | bomPriceList | BOM_PriceList.sql | 61 | Recursive price list calculation |
| | bomPriceStd | BOM_PriceStd.sql | 60 | Recursive standard price calculation |
| **5b: Quantity** | bomQtyOnHand | BOM_Qty_OnHand.sql | 135 | Recursive on-hand quantity |
| | bomQtyReserved | BOM_Qty_Reserved.sql | 141 | Recursive reserved quantity |
| | bomQtyOrdered | BOM_Qty_Ordered.sql | 143 | Recursive ordered quantity |
| | bomQtyAvailable | BOM_Qty_Available.sql | 26 | Combines OnHand - Reserved |

**Validation Strategy:** Shadow Mode at 100% (low-frequency, real-time comparison catches edge cases)

**Performance Tier:** Reporting (allows up to 100% latency increase vs SQL baseline)

---

## 2. Dependency Chain and Architecture

**Internal Dependencies:**

```
Wave 5a (Pricing) - All independent:
bomPriceLimit, bomPriceList, bomPriceStd (no interdependencies)

Wave 5b (Quantity):
bomQtyOnHand ──────┐
                   ├──► bomQtyAvailable
bomQtyReserved ────┘

bomQtyOrdered (independent)
```

**Views Affected:** `RV_WAREHOUSEPRICE.sql` (uses all 7 BOM functions)

**Code Location:**

| Function Type | Java Location |
|---------------|---------------|
| BOM calculations | `MProduct` or dedicated `MProductBOM` class |
| Complex multi-entity logic | Could use `MProductPricing` pattern |

**Validation Flow:**
```
Caller invokes Java method
    → Check feature flag (migration.function_config)
    → SHADOW mode (100% - no sampling)
    → Execute Java logic
    → Execute SQL function via JDBC
    → Compare results
    → Log mismatches (async)
    → Return Java result
```

---

## 3. Recursion Safeguards (Required)

BOM structures can be deep, wide, or circular. These safeguards are **mandatory, not optional**:

| Safeguard | Specification |
|-----------|---------------|
| **Maximum Depth** | 100 levels (configurable via `AD_SysConfig` key `BOM_MAX_DEPTH`) |
| **Circular Detection** | `HashSet<Integer>` of visited `M_Product_ID` per traversal |
| **Memory Limit** | 10,000 cached component calculations per request (LRU eviction) |
| **Exceeded Behavior** | Log warning, return result for traversed portion, mark as partial |

**Implementation Pattern:**
```java
public BigDecimal calculateBomQty(int productId, Set<Integer> visited, int depth) {
    if (depth > getMaxDepth()) {
        log.warn("BOM depth limit exceeded for M_Product_ID={}", productId);
        return partialResult;
    }
    if (!visited.add(productId)) {
        log.warn("Circular BOM detected for M_Product_ID={}", productId);
        return BigDecimal.ZERO;  // Break cycle
    }
    // ... recursion logic
}
```

**Required Integration Tests:**
- Deep BOM: 50+ levels
- Circular reference: A → B → C → A
- Wide BOM: 100+ components at single level
- Combined: deep + wide + near-circular

---

## 4. Cache Strategy

**JVM-Wide Shared Cache** (not per-request) to handle concurrent batch processing:

```java
private static final Cache<BomCacheKey, BigDecimal> bomCache = Caffeine.newBuilder()
    .maximumSize(100_000)                    // JVM-wide limit
    .expireAfterWrite(5, TimeUnit.MINUTES)   // Prevent stale data
    .recordStats()                           // For monitoring
    .build();

public record BomCacheKey(int productId, int warehouseId, String qtyType) {}
```

**Memory Budget:** 100,000 entries × ~150 bytes/entry = ~15MB maximum

**Per-Request Tracking** (circular detection remains thread-local):
```java
public BigDecimal calculateBomQty(int productId, Set<Integer> visited, int depth) {
    // Circular detection still per-request (thread-local)
    if (!visited.add(productId)) {
        return BigDecimal.ZERO;
    }

    // Cache lookup is JVM-wide
    BomCacheKey key = new BomCacheKey(productId, warehouseId, "ON_HAND");
    return bomCache.get(key, k -> computeBomQtyInternal(productId, visited, depth));
}
```

**Monitoring Metrics:**

| Metric | Alert Threshold |
|--------|-----------------|
| `bom.cache.hit_rate` | Target > 80% during batch |
| `bom.cache.evictions` | > 10,000/minute (cache too small) |
| `bom.cache.size` | Current entry count |

---

## 5. Implementation Strategy

**Convert Recursion to Iteration** (avoids stack overflow):

```java
public BigDecimal bomQtyOnHand(int productId, int warehouseId, int locatorId) {
    Deque<BOMComponent> stack = new ArrayDeque<>();
    stack.push(new BOMComponent(productId, BigDecimal.ONE));
    Set<Integer> visited = new HashSet<>();

    BigDecimal total = BigDecimal.ZERO;
    int depth = 0;

    while (!stack.isEmpty()) {
        BOMComponent comp = stack.pop();
        if (!visited.add(comp.productId())) continue;  // Circular check
        if (++depth > getMaxDepth()) break;            // Depth check

        if (isLeafComponent(comp)) {
            total = total.add(getQtyOnHand(comp, warehouseId, locatorId));
        } else {
            for (BOMComponent child : getChildren(comp)) {
                stack.push(child);
            }
        }
    }
    return total;
}
```

**Batch Loading** (reduce N+1 queries):
- Load entire BOM tree in single query using recursive CTE or multiple levels
- Process in memory
- Cache intermediate results

**Migration Order within Wave 5:**

| Phase | Functions | Days |
|-------|-----------|------|
| 5a | bomPriceLimit, bomPriceList, bomPriceStd | 4-5 |
| 5b | bomQtyOnHand, bomQtyReserved, bomQtyOrdered, bomQtyAvailable | 5-7 |

**Note:** `bomQtyAvailable` depends on `bomQtyOnHand` and `bomQtyReserved`, so migrate those first.

---

## 6. Quality Gates

### Gate 1: Code Complete
- [ ] All 7 functions implemented in Java with iteration (not recursion)
- [ ] Recursion safeguards in place (depth, circular, memory)
- [ ] Unit tests cover edge cases (deep, wide, circular BOMs)
- [ ] Integration tests pass

### Gate 2: Shadow Ready
- [ ] Feature flags set to SHADOW for all 7 functions
- [ ] Performance baseline captured
- [ ] Java within 100% of SQL p95 (Reporting tier)
- [ ] Rollback drill completed

### Gate 3: Cutover Approved
- [ ] 99.9% match rate for 7 consecutive days
- [ ] No critical mismatches unresolved
- [ ] `RV_WAREHOUSEPRICE.sql` view migrated to Java query method

### Gate 4: Cleanup Complete
- [ ] Feature flags set to JAVA_ONLY
- [ ] SQL functions retained in git (deleted after 30 days stable)
- [ ] Monitoring confirms no errors

---

## 7. Success Criteria Summary

- Recursive traversal produces identical results to SQL
- No stack overflow with deep BOMs (tested to 50+ levels)
- Performance within 100% of SQL baseline
- Circular BOM detection works correctly
- Cache hit rate > 80% during batch processing

---

## 8. Risk Areas

| Risk | Mitigation |
|------|------------|
| Deep BOM causes stack overflow | Use iterative approach with explicit stack |
| Circular BOM causes infinite loop | HashSet tracking of visited products |
| Memory pressure during batch | JVM-wide cache with 100K limit, LRU eviction |
| Performance regression | Batch loading, caching, 100% latency budget |
| Shadow mode adds latency | Acceptable for low-frequency BOM functions |

---

## Appendix: SQL Function Files

| Function | Path |
|----------|------|
| bomPriceLimit | `db/ddlutils/postgresql/functions/BOM_PriceLimit.sql` |
| bomPriceList | `db/ddlutils/postgresql/functions/BOM_PriceList.sql` |
| bomPriceStd | `db/ddlutils/postgresql/functions/BOM_PriceStd.sql` |
| bomQtyOnHand | `db/ddlutils/postgresql/functions/BOM_Qty_OnHand.sql` |
| bomQtyReserved | `db/ddlutils/postgresql/functions/BOM_Qty_Reserved.sql` |
| bomQtyOrdered | `db/ddlutils/postgresql/functions/BOM_Qty_Ordered.sql` |
| bomQtyAvailable | `db/ddlutils/postgresql/functions/BOM_Qty_Available.sql` |
