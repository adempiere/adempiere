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

-- Enable SHADOW mode for all Wave 3 functions after Java implementation is validated
-- Using 10% sample rate for high-volume functions (invoiceOpen, invoiceOpenToDate)
-- This script is idempotent - safe to run multiple times
DO $$
DECLARE
    expected_count INT := 7;
    actual_count INT;
BEGIN
    -- Update only functions not already in SHADOW mode or with NULL sample_rate
    UPDATE migration.function_config
    SET mode = 'SHADOW',
        sample_rate = CASE
            WHEN function_name IN ('invoiceOpen', 'invoiceOpenToDate') THEN 0.1
            ELSE COALESCE(sample_rate, 1.0)
        END,
        updated_at = NOW()
    WHERE function_name IN (
        'invoiceOpen', 'invoiceOpenToDate', 'invoiceDiscount',
        'invoicePaid', 'invoicePaidToDate',
        'paymentAllocated', 'paymentAvailable'
    )
    AND (mode != 'SHADOW' OR sample_rate IS NULL);

    -- Verify all 7 functions exist and are in SHADOW mode
    SELECT COUNT(*) INTO actual_count
    FROM migration.function_config
    WHERE function_name IN (
        'invoiceOpen', 'invoiceOpenToDate', 'invoiceDiscount',
        'invoicePaid', 'invoicePaidToDate',
        'paymentAllocated', 'paymentAvailable'
    ) AND mode = 'SHADOW';

    IF actual_count != expected_count THEN
        RAISE EXCEPTION 'Expected % functions in SHADOW mode, found %. Ensure 001_create_migration_schema.sql was run first.',
            expected_count, actual_count;
    END IF;

    RAISE NOTICE 'Successfully verified % functions in SHADOW mode', actual_count;
END $$;

-- Display final state for verification
SELECT function_name, mode, sample_rate, updated_at
FROM migration.function_config
WHERE function_name IN (
    'invoiceOpen', 'invoiceOpenToDate', 'invoiceDiscount',
    'invoicePaid', 'invoicePaidToDate',
    'paymentAllocated', 'paymentAvailable'
)
ORDER BY function_name;
