-- db/ddlutils/postgresql/migrations/004_wave3_verify_indexes.sql
-- Wave 3: Verify required indexes exist for allocation queries
-- These indexes should already exist from base schema (Adempiere.sql)

DO $$
BEGIN
    -- Check C_AllocationLine_Invoice exists
    IF NOT EXISTS (
        SELECT 1 FROM pg_indexes
        WHERE lower(indexname) = 'c_allocationline_invoice'
    ) THEN
        RAISE WARNING 'Index C_AllocationLine_Invoice not found - consider running base schema';
    END IF;

    -- Check C_AllocationLine_Payment exists
    IF NOT EXISTS (
        SELECT 1 FROM pg_indexes
        WHERE lower(indexname) = 'c_allocationline_payment'
    ) THEN
        RAISE WARNING 'Index C_AllocationLine_Payment not found - consider running base schema';
    END IF;

    RAISE NOTICE 'Wave 3 index verification complete';
END $$;

-- Display current indexes on allocation tables for verification
SELECT tablename, indexname, indexdef
FROM pg_indexes
WHERE tablename IN ('c_allocationline', 'c_allocationhdr')
ORDER BY tablename, indexname;
