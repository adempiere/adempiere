-- db/ddlutils/postgresql/migrations/001b_capture_baselines.sql
-- Captures p95 latency baselines for Wave 0 SQL functions
-- Run this BEFORE writing any Java code

DO $$
DECLARE
    iterations INT := 1000;
    start_ts TIMESTAMP;
    end_ts TIMESTAMP;
    elapsed_ms NUMERIC;
    timings NUMERIC[];
    p95_idx INT;
    i INT;
    dummy_ts TIMESTAMP;
    dummy_date DATE;
    dummy_int INT;
    dummy_numeric NUMERIC;
    dummy_text TEXT;
BEGIN
    -- getDate baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_ts := getDate();
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'getDate';
    RAISE NOTICE 'getDate p95: % ms', CEIL(elapsed_ms);

    -- daysBetween baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_int := daysBetween('2026-01-15 14:30:00'::timestamp, '2026-01-01 08:00:00'::timestamp);
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'daysBetween';
    RAISE NOTICE 'daysBetween p95: % ms', CEIL(elapsed_ms);

    -- addDays baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_date := addDays('2026-01-15 14:30:00'::timestamp, 10);
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'addDays';
    RAISE NOTICE 'addDays p95: % ms', CEIL(elapsed_ms);

    -- subtractDays baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_date := subtractDays('2026-01-15 14:30:00'::timestamp, 10);
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'subtractDays';
    RAISE NOTICE 'subtractDays p95: % ms', CEIL(elapsed_ms);

    -- trunc baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_date := trunc('2026-05-15 14:30:45'::timestamp, 'Q');
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'trunc';
    RAISE NOTICE 'trunc p95: % ms', CEIL(elapsed_ms);

    -- round baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_numeric := round(123.456789, 2);
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'round';
    RAISE NOTICE 'round p95: % ms', CEIL(elapsed_ms);

    -- firstOf baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_date := firstOf('2026-05-15 14:30:45'::timestamp, 'Q');
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'firstOf';
    RAISE NOTICE 'firstOf p95: % ms', CEIL(elapsed_ms);

    -- charAt baseline
    timings := ARRAY[]::NUMERIC[];
    FOR i IN 1..iterations LOOP
        start_ts := clock_timestamp();
        dummy_text := charAt('Hello World', 5);
        end_ts := clock_timestamp();
        elapsed_ms := EXTRACT(EPOCH FROM (end_ts - start_ts)) * 1000;
        timings := array_append(timings, elapsed_ms);
    END LOOP;
    SELECT timings[idx] INTO elapsed_ms FROM (
        SELECT unnest(timings) as val, row_number() OVER (ORDER BY unnest(timings)) as idx
    ) t WHERE idx = CEIL(0.95 * iterations);
    UPDATE migration.function_config SET sql_baseline_p95_ms = CEIL(elapsed_ms)
    WHERE function_name = 'charAt';
    RAISE NOTICE 'charAt p95: % ms', CEIL(elapsed_ms);

END $$;

-- Display captured baselines
SELECT function_name, sql_baseline_p95_ms, mode, performance_tier
FROM migration.function_config
ORDER BY function_name;
