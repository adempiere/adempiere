-- Wave 1 Currency Functions Configuration
-- Execute after Wave 0 is complete

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
