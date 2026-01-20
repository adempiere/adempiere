-- Wave 5 BOM Function Migration Configuration
-- Requires: 001_create_migration_schema.sql (creates migration.function_config table)
-- Sets all functions to SHADOW mode at 100% sample rate

INSERT INTO migration.function_config (function_name, mode, sample_rate, circuit_breaker_enabled)
VALUES
    ('bomPriceLimit', 'SHADOW', 1.0, true),
    ('bomPriceList', 'SHADOW', 1.0, true),
    ('bomPriceStd', 'SHADOW', 1.0, true),
    ('bomQtyOnHand', 'SHADOW', 1.0, true),
    ('bomQtyReserved', 'SHADOW', 1.0, true),
    ('bomQtyOrdered', 'SHADOW', 1.0, true),
    ('bomQtyAvailable', 'SHADOW', 1.0, true)
ON CONFLICT (function_name) DO UPDATE SET
    mode = EXCLUDED.mode,
    sample_rate = EXCLUDED.sample_rate,
    circuit_breaker_enabled = EXCLUDED.circuit_breaker_enabled;
