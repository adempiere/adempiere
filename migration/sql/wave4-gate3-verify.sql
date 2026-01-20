-- Wave 4: Gate 3 - Verification Queries
-- ======================================
--
-- Run this script to verify SHADOW mode is configured correctly
-- and to check the match rates from shadow execution logging.
--
-- Run: psql -h localhost -d adempiere -f migration/sql/wave4-gate3-verify.sql
--
-- ======================================

-- ===========================================
-- SECTION 1: Configuration Verification
-- ===========================================

\echo ''
\echo '=== 1. Wave 4 Function Configuration Status ==='
SELECT
    function_name,
    mode,
    sample_rate,
    circuit_breaker_enabled,
    CASE
        WHEN mode = 'SHADOW' AND sample_rate = 1.0 THEN 'READY'
        WHEN mode = 'SHADOW' AND sample_rate < 1.0 THEN 'READY (sampling)'
        WHEN mode = 'SQL_ONLY' THEN 'SQL ONLY (not routing)'
        WHEN mode = 'JAVA_ONLY' THEN 'JAVA ONLY (no shadow)'
        ELSE 'UNKNOWN'
    END AS status
FROM migration.function_config
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
)
ORDER BY function_name;

\echo ''
\echo '=== 2. Configuration Summary ==='
SELECT
    mode,
    COUNT(*) as function_count,
    string_agg(function_name, ', ' ORDER BY function_name) as functions
FROM migration.function_config
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
)
GROUP BY mode
ORDER BY mode;

-- ===========================================
-- SECTION 2: Execution Logging Verification
-- ===========================================

\echo ''
\echo '=== 3. Recent Shadow Execution Logs (last hour) ==='
SELECT
    function_name,
    COUNT(*) AS total_calls,
    SUM(CASE WHEN is_match THEN 1 ELSE 0 END) AS matches,
    SUM(CASE WHEN NOT is_match THEN 1 ELSE 0 END) AS mismatches,
    CASE
        WHEN COUNT(*) = 0 THEN 'N/A'
        ELSE ROUND(100.0 * SUM(CASE WHEN is_match THEN 1 ELSE 0 END) / COUNT(*), 2)::text || '%'
    END AS match_rate
FROM migration.function_log
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
)
AND created_at > NOW() - INTERVAL '1 hour'
GROUP BY function_name
ORDER BY function_name;

\echo ''
\echo '=== 4. Match Rate Summary (last hour) ==='
SELECT
    CASE
        WHEN COUNT(*) = 0 THEN 'NO DATA: No shadow executions logged in the last hour'
        WHEN SUM(CASE WHEN NOT is_match THEN 1 ELSE 0 END) = 0 THEN 'PASS: 100% match rate'
        ELSE 'ATTENTION: ' || SUM(CASE WHEN NOT is_match THEN 1 ELSE 0 END) || ' mismatches detected'
    END AS overall_status,
    COUNT(*) AS total_calls,
    SUM(CASE WHEN is_match THEN 1 ELSE 0 END) AS total_matches,
    SUM(CASE WHEN NOT is_match THEN 1 ELSE 0 END) AS total_mismatches
FROM migration.function_log
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
)
AND created_at > NOW() - INTERVAL '1 hour';

-- ===========================================
-- SECTION 3: Mismatch Analysis
-- ===========================================

\echo ''
\echo '=== 5. Recent Mismatches (last hour, limit 20) ==='
SELECT
    function_name,
    input_params,
    sql_result,
    java_result,
    mismatch_reason,
    created_at
FROM migration.function_log
WHERE NOT is_match
AND function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
)
AND created_at > NOW() - INTERVAL '1 hour'
ORDER BY created_at DESC
LIMIT 20;

\echo ''
\echo '=== 6. Mismatch Reasons Breakdown ==='
SELECT
    function_name,
    mismatch_reason,
    COUNT(*) as occurrences
FROM migration.function_log
WHERE NOT is_match
AND function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
)
AND created_at > NOW() - INTERVAL '1 hour'
GROUP BY function_name, mismatch_reason
ORDER BY function_name, occurrences DESC;

-- ===========================================
-- SECTION 4: Performance Metrics
-- ===========================================

\echo ''
\echo '=== 7. Performance Comparison (last hour) ==='
SELECT
    function_name,
    COUNT(*) AS calls,
    ROUND(AVG(sql_time_ms), 2) AS avg_sql_ms,
    ROUND(AVG(java_time_ms), 2) AS avg_java_ms,
    ROUND(AVG(java_time_ms) - AVG(sql_time_ms), 2) AS overhead_ms,
    CASE
        WHEN AVG(sql_time_ms) > 0 THEN ROUND(AVG(java_time_ms) / AVG(sql_time_ms), 2)
        ELSE NULL
    END AS ratio
FROM migration.function_log
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
)
AND created_at > NOW() - INTERVAL '1 hour'
GROUP BY function_name
ORDER BY function_name;

-- ===========================================
-- SECTION 5: Gate 3 Checklist
-- ===========================================

\echo ''
\echo '=== 8. Gate 3 Checklist ==='
\echo ''

-- Check 1: All functions in SHADOW mode
SELECT
    '[ ] All functions in SHADOW mode' AS requirement,
    CASE
        WHEN COUNT(*) FILTER (WHERE mode = 'SHADOW') = 9 THEN 'PASS'
        ELSE 'FAIL (' || COUNT(*) FILTER (WHERE mode = 'SHADOW') || '/9 in SHADOW)'
    END AS status
FROM migration.function_config
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
);

-- Check 2: Shadow execution logging working
SELECT
    '[ ] Execution logging verified' AS requirement,
    CASE
        WHEN COUNT(*) > 0 THEN 'PASS (' || COUNT(*) || ' entries logged)'
        ELSE 'PENDING (no logs yet - run tests first)'
    END AS status
FROM migration.function_log
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
)
AND created_at > NOW() - INTERVAL '1 hour';

-- Check 3: 100% match rate
SELECT
    '[ ] Match rate = 100%' AS requirement,
    CASE
        WHEN COUNT(*) = 0 THEN 'PENDING (no logs yet)'
        WHEN SUM(CASE WHEN NOT is_match THEN 1 ELSE 0 END) = 0 THEN 'PASS'
        ELSE 'FAIL (' || ROUND(100.0 * SUM(CASE WHEN is_match THEN 1 ELSE 0 END) / COUNT(*), 2) || '% match rate)'
    END AS status
FROM migration.function_log
WHERE function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
)
AND created_at > NOW() - INTERVAL '1 hour';

-- Check 4: No critical mismatches
SELECT
    '[ ] No critical mismatches' AS requirement,
    CASE
        WHEN COUNT(*) = 0 THEN 'PASS (no mismatches)'
        ELSE 'REVIEW (' || COUNT(*) || ' mismatches need analysis)'
    END AS status
FROM migration.function_log
WHERE NOT is_match
AND function_name IN (
    'nextID', 'nextIDFunc', 'acctBalance', 'productAttribute',
    'documentNo', 'get_Sysconfig', 'linenetamtrealinvoiceline',
    'linenetamtrealorderline', 'maxpaydate'
)
AND created_at > NOW() - INTERVAL '1 hour';

\echo ''
\echo '=== Verification complete ==='
\echo ''
