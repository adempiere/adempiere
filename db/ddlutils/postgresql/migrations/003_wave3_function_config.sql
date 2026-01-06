-- db/ddlutils/postgresql/migrations/003_wave3_function_config.sql
-- Wave 3 function configuration for shadow mode
-- All functions start in SQL_ONLY mode until Java implementation is ready

INSERT INTO migration.function_config (function_name, mode, performance_tier, sample_rate, circuit_breaker_enabled)
VALUES
    ('invoiceOpen', 'SQL_ONLY', 'STANDARD', 1.0, true),
    ('invoiceOpenToDate', 'SQL_ONLY', 'STANDARD', 1.0, true),
    ('invoiceDiscount', 'SQL_ONLY', 'STANDARD', 1.0, true),
    ('invoicePaid', 'SQL_ONLY', 'STANDARD', 1.0, true),
    ('invoicePaidToDate', 'SQL_ONLY', 'STANDARD', 1.0, true),
    ('paymentAllocated', 'SQL_ONLY', 'STANDARD', 1.0, true),
    ('paymentAvailable', 'SQL_ONLY', 'STANDARD', 1.0, true)
ON CONFLICT (function_name) DO UPDATE SET
    mode = EXCLUDED.mode,
    performance_tier = EXCLUDED.performance_tier,
    sample_rate = EXCLUDED.sample_rate,
    circuit_breaker_enabled = EXCLUDED.circuit_breaker_enabled;

-- Enable SHADOW mode for payment functions after Java implementation is validated
UPDATE migration.function_config
SET mode = 'SHADOW', sample_rate = 1.0
WHERE function_name IN ('paymentAllocated', 'paymentAvailable');
