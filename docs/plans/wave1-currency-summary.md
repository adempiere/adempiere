# Wave 1: Currency Functions - Migration Summary

**Date:** 2026-01-03
**Status:** Planning
**Reference:** `docs/plans/2026-01-01-postgresql-function-migration-design.md`
**Dependency:** Wave 0 (Foundation) must complete first

---

## 1. Scope

**Purpose:** Migrate the currency conversion chain - the foundation for all financial calculations in ADempiere. Unlocks Wave 3 (Financial Core).

**Functions (4):**

| Function | File | LOC | Complexity |
|----------|------|-----|------------|
| currencyRound | C_Currency_Round.sql | 63 | Low |
| currencyRate | C_Currency_Rate.sql | 179 | High (CURSOR) |
| currencyConvert | C_Currency_Convert.sql | 63 | Medium |
| currencyBase | C_Currency_Base.sql | 34 | Low |

**Dependency Chain:**
```
currencyRound <── currencyConvert <── currencyBase
                       ^
currencyRate ──────────┘
     ^
  getDate (Wave 0)
```

---

## 2. Validation Strategy

Wave 1 functions use **Dual-Write Logging** (not shadow mode) because:
- `currencyConvert` is high-frequency (thousands of calls/transaction)
- Even 1% sampling in shadow mode adds unacceptable latency
- Dual-write provides 100% validation coverage with zero production impact

**Dual-Write Flow:**
```
Request
  -> Execute Java implementation
  -> Log {inputs, output, timestamp} asynchronously
  -> Return Java result immediately

Background replay job:
  -> Read logged executions in batches
  -> Replay against SQL in isolated environment
  -> Compare outputs, report mismatches
```

**Detection Latency:** 5-15 minutes (acceptable tradeoff for zero production impact)

**Performance Tier:** Critical (5% max latency increase over SQL baseline)

---

## 3. Success Criteria

| Gate | Requirement |
|------|-------------|
| Code Complete | Java implementations match SQL logic; unit + integration tests pass |
| Validation Ready | Dual-write logging configured; replay executor running; performance baseline captured |
| Cutover Approved | 99.9% match rate for 7 consecutive days; no critical mismatches |
| Cleanup Complete | Feature flag set to JAVA_ONLY; SQL retained in git for 30 days |

---

## 4. Risk Areas

| Risk | Mitigation |
|------|------------|
| `currencyRate` uses CURSOR for fallback - complex logic | Thorough unit tests for all fallback paths; 100% replay validation |
| `currencyConvert` has 45+ call sites - high impact | Dual-write ensures zero production risk during validation |
| Wave 3 (Financial Core) depends on Wave 1 | Cannot proceed to Wave 3 until 99.9% match rate achieved |

**Rollback Trigger:** Any mismatch in currency calculations affecting financial documents triggers immediate revert to SQL_ONLY.

---

## 5. Infrastructure Prerequisites

| Prerequisite | Required | Reference |
|--------------|----------|-----------|
| Wave 0 complete (getDate dependency) | Yes | Wave 0 provides `getDate` used by `currencyRate` |
| Rollback drill completed | Yes | Design doc Section 5.1 |
| Infrastructure failure drill completed | Yes | Design doc Section 5.1.1 |
| Replay executor deployed | Yes | Background job for dual-write validation |
| Replay database provisioned | Yes | Isolated schema on QA instance |
| Migration schema created | Yes | `migration.function_config`, `migration.function_execution_log` |

**Replay Executor Configuration:**
- Batch size: 1000 entries
- Partitioning: `hash(function_name) % instance_count`
- Health monitoring: heartbeat every 60s
- Backlog alert threshold: 10,000 entries

---

## 6. Implementation Order

| Order | Function | Effort | Notes |
|-------|----------|--------|-------|
| 1 | currencyRound | 0.5 days | Simple rounding logic; no dependencies within wave |
| 2 | currencyRate | 2 days | Complex CURSOR fallback; requires thorough testing |
| 3 | currencyConvert | 1 day | Depends on currencyRound + currencyRate |
| 4 | currencyBase | 0.5 days | Wrapper around currencyConvert |

**Total Effort:** 4-5 days implementation + 7 days validation

---

## 7. Views Affected

These views call Wave 1 functions and will need migration as part of the transaction unit:

- RV_CASH_DETAIL.sql
- RV_PROJECTCYCLE.sql

---

## 8. Downstream Dependencies

Wave 1 completion unblocks:

| Wave | Functions Affected |
|------|-------------------|
| Wave 3 (Financial Core) | `invoiceOpen`, `invoiceDiscount`, all payment functions |

**Critical Path:** Wave 0 -> **Wave 1** -> Wave 3

---

## Appendix: Function Signatures

```sql
-- currencyRound(amount NUMERIC, currencyId INTEGER, costing CHAR) -> NUMERIC
-- currencyRate(currencyFromId INT, currencyToId INT, conversionDate DATE, conversionTypeId INT, clientId INT, orgId INT) -> NUMERIC
-- currencyConvert(amount NUMERIC, currencyFromId INT, currencyToId INT, conversionDate DATE, conversionTypeId INT, clientId INT, orgId INT) -> NUMERIC
-- currencyBase(amount NUMERIC, currencyId INT, conversionDate DATE, clientId INT, orgId INT) -> NUMERIC
```
