-- Wave 4: Gate 3 - Enable SHADOW Mode for All Functions
-- =====================================================
--
-- This script enables SHADOW mode for all Wave 4 functions to begin
-- router validation (Gate 3 of the quality gates).
--
-- Prerequisites:
--   - Gate 2 (SQL_ONLY Baseline) must be complete
--   - migration.function_config table exists with Wave 4 function entries
--   - Java implementations deployed (Wave4FunctionRouter, NextIDRouter)
--
-- Run: psql -h localhost -d adempiere -f migration/sql/wave4-gate3-shadow-mode.sql
--
-- =====================================================

-- Step 1: Show current configuration (before)
\echo ''
\echo '=== BEFORE: Current Wave 4 Function Configuration ==='
SELECT function_name, mode, sample_rate, circuit_breaker_enabled
FROM migration.function_config
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
)
ORDER BY function_name;

-- Step 2: Enable SHADOW mode for all Wave 4 functions
-- Note: nextID/nextIDFunc should already be in SHADOW mode from initial config
\echo ''
\echo '=== Enabling SHADOW mode for all Wave 4 functions ==='

UPDATE migration.function_config
SET mode = 'SHADOW',
    sample_rate = 1.0,  -- 100% sampling for full validation
    updated_at = NOW()
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
);

-- Show affected rows
\echo 'Rows updated:' :ROW_COUNT

-- Step 3: Show updated configuration (after)
\echo ''
\echo '=== AFTER: Updated Wave 4 Function Configuration ==='
SELECT function_name, mode, sample_rate, circuit_breaker_enabled, updated_at
FROM migration.function_config
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
)
ORDER BY function_name;

-- Step 4: Verify all are now in SHADOW mode
\echo ''
\echo '=== VERIFICATION: Checking all functions are in SHADOW mode ==='
SELECT
    CASE
        WHEN COUNT(*) = 9 AND COUNT(*) FILTER (WHERE mode = 'SHADOW') = 9
        THEN 'PASS: All 9 Wave 4 functions are in SHADOW mode'
        ELSE 'FAIL: Not all functions in SHADOW mode - check configuration'
    END AS verification_result,
    COUNT(*) AS total_functions,
    COUNT(*) FILTER (WHERE mode = 'SHADOW') AS shadow_count,
    COUNT(*) FILTER (WHERE mode != 'SHADOW') AS non_shadow_count
FROM migration.function_config
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
);

-- Step 5: Show any functions NOT in SHADOW mode (for debugging)
\echo ''
\echo '=== Functions NOT in SHADOW mode (should be empty) ==='
SELECT function_name, mode, sample_rate
FROM migration.function_config
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
)
AND mode != 'SHADOW';

\echo ''
\echo '=== Gate 3 SHADOW mode configuration complete ==='
\echo 'Next steps:'
\echo '  1. Run Wave 4 tests: ./gradlew :base:test --tests "Wave4*"'
\echo '  2. Verify logging: SELECT * FROM migration.function_log ORDER BY created_at DESC LIMIT 20;'
\echo '  3. Check match rates with wave4-gate3-verify.sql'
\echo ''
