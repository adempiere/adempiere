-- Wave 4: Standalone Functions Configuration
-- Run this after Wave 4 Java implementation is deployed

-- Initial mode configuration:
-- - nextID/nextIDFunc: Start in SHADOW mode with circuit_breaker_enabled=false
--   (sequence functions cannot use circuit breaker as each call consumes a sequence value)
-- - All other functions: Start in SQL_ONLY mode (safe default)
INSERT INTO migration.function_config (function_name, mode, sample_rate, circuit_breaker_enabled)
VALUES
    ('nextID', 'SHADOW', 1.0, false),
    ('nextIDFunc', 'SHADOW', 1.0, false),
    ('acctBalance', 'SQL_ONLY', 1.0, true),
    ('productAttribute', 'SQL_ONLY', 1.0, true),
    ('documentNo', 'SQL_ONLY', 0.1, true),
    ('get_Sysconfig', 'SQL_ONLY', 1.0, true),
    ('linenetamtrealinvoiceline', 'SQL_ONLY', 1.0, true),
    ('linenetamtrealorderline', 'SQL_ONLY', 1.0, true),
    ('maxpaydate', 'SQL_ONLY', 1.0, true)
ON CONFLICT (function_name) DO UPDATE SET
    mode = EXCLUDED.mode,
    sample_rate = EXCLUDED.sample_rate,
    circuit_breaker_enabled = EXCLUDED.circuit_breaker_enabled;

-- ========================================================
-- Enable SHADOW mode for Wave 4 Functions (except nextID/nextIDFunc which start in SHADOW)
-- Run this after code deployment to start validation
-- ========================================================

-- Enable SHADOW mode:
UPDATE migration.function_config
SET mode = 'SHADOW', sample_rate = 1.0
WHERE function_name IN ('acctBalance', 'productAttribute', 'documentNo',
                        'get_Sysconfig', 'linenetamtrealinvoiceline',
                        'linenetamtrealorderline', 'maxpaydate');

-- Verify configuration:
-- SELECT function_name, mode, sample_rate, circuit_breaker_enabled
-- FROM migration.function_config
-- WHERE function_name IN ('nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
--                         'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
--                         'linenetamtrealorderline', 'maxpaydate');

-- ========================================================
-- get_Sysconfig: Already Java-implemented (No Routing Needed)
-- ========================================================
--
-- MSysConfig.getValue() already implements equivalent logic with caching:
-- - Same precedence logic: ORDER BY AD_Client_ID DESC, AD_Org_ID DESC
--   Priority: (client, org) > (client, 0) > (0, org) > (0, 0)
-- - Returns default value when not found
-- - Java enhancement: CCache for performance (no database hit on repeated calls)
--
-- The SQL function (db/ddlutils/postgresql/functions/get_Sysconfig.sql) uses:
--   SELECT Value FROM AD_SysConfig
--   WHERE Name=? AND AD_Client_ID IN (0, ?) AND AD_Org_ID IN (0, ?)
--   ORDER BY AD_Client_ID DESC, AD_Org_ID DESC LIMIT 1
--
-- MSysConfig.getValue() (base/src/org/compiere/model/MSysConfig.java) uses:
--   SELECT Value FROM AD_SysConfig
--   WHERE Name=? AND AD_Client_ID IN (0, ?) AND AD_Org_ID IN (0, ?) AND IsActive='Y'
--   ORDER BY AD_Client_ID DESC, AD_Org_ID DESC
--
-- Set to JAVA_ONLY immediately (no shadow mode needed):
-- UPDATE migration.function_config
-- SET mode = 'JAVA_ONLY', updated = NOW()
-- WHERE function_name = 'get_Sysconfig';

-- ========================================================
-- Cutover to JAVA_ONLY (after 7 days at 99.9% match rate)
-- ========================================================
--
-- QUALITY GATES CHECKLIST - All must be verified before running:
-- [ ] 99.9% match rate for 7 consecutive days
-- [ ] No critical mismatches
-- [ ] Performance within tier budget (5% for CRITICAL, 30% for STANDARD)
-- [ ] Rollback tested
--
-- This SQL should only be run after human verification of the above quality gates.
--
UPDATE migration.function_config
SET mode = 'JAVA_ONLY'
WHERE function_name IN ('nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
                        'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
                        'linenetamtrealorderline', 'maxpaydate');
