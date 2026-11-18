-- db/ddlutils/postgresql/migrations/002_enable_wave0_shadow.sql
-- Enable shadow mode for all Wave 0 functions at 100% sampling
-- This script is idempotent - safe to run multiple times

DO $$
DECLARE
    expected_count INT := 8;
    actual_count INT;
BEGIN
    -- Update only functions not already in SHADOW mode
    UPDATE migration.function_config
    SET mode = 'SHADOW', updated_at = NOW()
    WHERE function_name IN (
        'getDate', 'daysBetween', 'addDays', 'subtractDays',
        'trunc', 'round', 'firstOf', 'charAt'
    )
    AND mode != 'SHADOW';

    -- Verify all 8 functions exist and are in SHADOW mode
    SELECT COUNT(*) INTO actual_count
    FROM migration.function_config
    WHERE function_name IN (
        'getDate', 'daysBetween', 'addDays', 'subtractDays',
        'trunc', 'round', 'firstOf', 'charAt'
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
    'getDate', 'daysBetween', 'addDays', 'subtractDays',
    'trunc', 'round', 'firstOf', 'charAt'
)
ORDER BY function_name;
