-- db/ddlutils/postgresql/migrations/001_create_migration_schema.sql
CREATE SCHEMA IF NOT EXISTS migration;

CREATE TABLE migration.function_config (
    function_name VARCHAR(100) PRIMARY KEY,
    mode VARCHAR(20) NOT NULL DEFAULT 'SQL_ONLY',
    performance_tier VARCHAR(20) DEFAULT 'STANDARD',
    sql_baseline_p95_ms INT,
    sample_rate DECIMAL(5,4) DEFAULT 1.0,
    circuit_breaker_enabled BOOLEAN DEFAULT true,
    tolerance_config JSONB DEFAULT '{"timestamp_tolerance_ms": 1000, "date_compare_by_string": true, "null_empty_equivalent": true}',
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE migration.function_log (
    id SERIAL PRIMARY KEY,
    function_name VARCHAR(100) NOT NULL,
    input_params JSONB,
    sql_result TEXT,
    java_result TEXT,
    sql_time_ms INT,
    java_time_ms INT,
    is_match BOOLEAN,
    mismatch_reason VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE INDEX idx_function_log_name_created
ON migration.function_log(function_name, created_at);

CREATE INDEX idx_function_log_mismatches
ON migration.function_log(function_name, created_at) WHERE NOT is_match;

-- Insert Wave 0 function configurations
INSERT INTO migration.function_config (function_name, mode, performance_tier, sample_rate)
VALUES
    ('getDate', 'SQL_ONLY', 'STANDARD', 1.0),
    ('daysBetween', 'SQL_ONLY', 'STANDARD', 1.0),
    ('addDays', 'SQL_ONLY', 'STANDARD', 1.0),
    ('subtractDays', 'SQL_ONLY', 'STANDARD', 1.0),
    ('trunc', 'SQL_ONLY', 'STANDARD', 1.0),
    ('round', 'SQL_ONLY', 'STANDARD', 1.0),
    ('firstOf', 'SQL_ONLY', 'STANDARD', 1.0),
    ('charAt', 'SQL_ONLY', 'STANDARD', 1.0);
