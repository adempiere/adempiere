-- Wave 1 Currency Functions Configuration
-- Execute after Wave 0 is complete

-- Initial configuration (SQL_ONLY mode - safe default)
INSERT INTO migration.function_config (function_name, mode, sample_rate, circuit_breaker_enabled)
VALUES
    ('currencyRound', 'SQL_ONLY', 1.0, true),
    ('currencyRate', 'SQL_ONLY', 1.0, true),
    ('currencyConvert', 'SQL_ONLY', 1.0, true),
    ('currencyBase', 'SQL_ONLY', 1.0, true)
ON CONFLICT (function_name) DO UPDATE SET
    mode = EXCLUDED.mode,
    sample_rate = EXCLUDED.sample_rate,
    circuit_breaker_enabled = EXCLUDED.circuit_breaker_enabled;

-- ========================================================
-- Enable SHADOW mode for Wave 1 Currency Functions
-- Run this after code deployment to start validation
-- ========================================================

-- Uncomment to enable SHADOW mode:
-- UPDATE migration.function_config
-- SET mode = 'SHADOW', sample_rate = 1.0
-- WHERE function_name IN ('currencyRound', 'currencyRate', 'currencyConvert', 'currencyBase');

-- Verify configuration:
-- SELECT function_name, mode, sample_rate, circuit_breaker_enabled
-- FROM migration.function_config
-- WHERE function_name LIKE 'currency%';

-- ========================================================
-- Cutover to JAVA_ONLY (after 7 days successful SHADOW)
-- ========================================================

-- Uncomment after successful SHADOW validation:
-- UPDATE migration.function_config
-- SET mode = 'JAVA_ONLY'
-- WHERE function_name IN ('currencyRound', 'currencyRate', 'currencyConvert', 'currencyBase');
