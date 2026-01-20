-- Wave 4: Standalone Functions Configuration
-- Run this after Wave 4 Java implementation is deployed

-- Initial mode: SQL_ONLY (safe default)
-- Note: nextID/nextIDFunc use circuit_breaker_enabled=false because sequence
-- functions cannot use circuit breaker (each call consumes a sequence value).
-- They also start in SHADOW mode with special handling (execute Java, log for validation).
INSERT INTO migration.function_config (function_name, mode, sample_rate, circuit_breaker_enabled, created_at, updated_at)
VALUES
    ('nextID', 'SHADOW', 1.0, false, NOW(), NOW()),
    ('nextIDFunc', 'SHADOW', 1.0, false, NOW(), NOW()),
    ('acctBalance', 'SQL_ONLY', 1.0, true, NOW(), NOW()),
    ('productAttribute', 'SQL_ONLY', 1.0, true, NOW(), NOW()),
    ('documentNo', 'SQL_ONLY', 0.1, true, NOW(), NOW()),
    ('get_Sysconfig', 'SQL_ONLY', 1.0, true, NOW(), NOW()),
    ('linenetamtrealinvoiceline', 'SQL_ONLY', 1.0, true, NOW(), NOW()),
    ('linenetamtrealorderline', 'SQL_ONLY', 1.0, true, NOW(), NOW()),
    ('maxpaydate', 'SQL_ONLY', 1.0, true, NOW(), NOW())
ON CONFLICT (function_name) DO UPDATE SET
    mode = EXCLUDED.mode,
    sample_rate = EXCLUDED.sample_rate,
    circuit_breaker_enabled = EXCLUDED.circuit_breaker_enabled,
    updated_at = NOW();

-- Transition to SHADOW mode (run after deployment verified)
-- UPDATE migration.function_config SET mode = 'SHADOW', updated_at = NOW()
-- WHERE function_name IN ('acctBalance', 'productAttribute', 'documentNo',
--                         'get_Sysconfig', 'linenetamtrealinvoiceline',
--                         'linenetamtrealorderline', 'maxpaydate');

-- Transition to JAVA_ONLY (run after 7 days at 99.9% match rate)
-- UPDATE migration.function_config SET mode = 'JAVA_ONLY', updated_at = NOW()
-- WHERE function_name IN ('nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
--                         'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
--                         'linenetamtrealorderline', 'maxpaydate');
