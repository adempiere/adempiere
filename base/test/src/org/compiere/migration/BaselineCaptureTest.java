package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.test.CommonGWSetup;
import org.compiere.util.DB;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Integration test to capture SQL function performance baselines.
 * Run this ONCE before Java implementations to establish p95 latency baselines.
 *
 * Prerequisites: Run 001_create_migration_schema.sql first.
 */
@Tag("IntegrationTest")
public class BaselineCaptureTest extends CommonGWSetup {

    private static final int ITERATIONS = 1000;

    @Test
    void captureAllBaselines() throws Exception {
        // First check if migration schema exists, create if not
        boolean schemaExists = checkSchemaExists();
        if (!schemaExists) {
            System.out.println("Migration schema does not exist. Creating now...");
            createMigrationSchema();
            schemaExists = checkSchemaExists();
            if (!schemaExists) {
                fail("Failed to create migration schema");
                return;
            }
        }

        System.out.println("\n=== Capturing SQL Function Performance Baselines ===\n");

        captureBaseline("getDate", "SELECT getDate()");
        captureBaseline("daysBetween", "SELECT daysBetween('2026-01-15 14:30:00'::timestamp, '2026-01-01 08:00:00'::timestamp)");
        captureBaseline("addDays", "SELECT addDays('2026-01-15 14:30:00'::timestamp with time zone, 10)");
        captureBaseline("subtractDays", "SELECT subtractDays('2026-01-15 14:30:00'::timestamp with time zone, 10)");
        captureBaseline("trunc", "SELECT trunc('2026-05-15 14:30:45'::timestamp with time zone, 'Q')");
        captureBaseline("round", "SELECT round(123.456789, 2)");
        captureBaseline("firstOf", "SELECT firstOf('2026-05-15 14:30:45'::timestamp, 'Q')");
        captureBaseline("charAt", "SELECT charAt('Hello World', 5)");

        // Display results
        System.out.println("\n=== Baseline Capture Results ===\n");
        displayBaselines();
    }

    @Test
    void verifySchemaExists() {
        boolean exists = checkSchemaExists();
        System.out.println("Migration schema exists: " + exists);

        if (exists) {
            displayBaselines();
        }
    }

    private boolean checkSchemaExists() {
        String sql = "SELECT EXISTS(SELECT 1 FROM information_schema.schemata WHERE schema_name = 'migration')";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (Exception e) {
            System.err.println("Error checking schema: " + e.getMessage());
        }
        return false;
    }

    private void createMigrationSchema() {
        // Create schema
        executeSQL("CREATE SCHEMA IF NOT EXISTS migration");

        // Create function_config table
        executeSQL("CREATE TABLE IF NOT EXISTS migration.function_config (" +
            "function_name VARCHAR(100) PRIMARY KEY, " +
            "mode VARCHAR(20) NOT NULL DEFAULT 'SQL_ONLY', " +
            "performance_tier VARCHAR(20) DEFAULT 'STANDARD', " +
            "sql_baseline_p95_ms INT, " +
            "sample_rate DECIMAL(5,4) DEFAULT 1.0, " +
            "circuit_breaker_enabled BOOLEAN DEFAULT true, " +
            "tolerance_config JSONB DEFAULT '{\"timestamp_tolerance_ms\": 1000, \"date_compare_by_string\": true, \"null_empty_equivalent\": true}', " +
            "created_at TIMESTAMP DEFAULT NOW(), " +
            "updated_at TIMESTAMP DEFAULT NOW())");

        // Create function_log table
        executeSQL("CREATE TABLE IF NOT EXISTS migration.function_log (" +
            "id SERIAL PRIMARY KEY, " +
            "function_name VARCHAR(100) NOT NULL, " +
            "input_params JSONB, " +
            "sql_result TEXT, " +
            "java_result TEXT, " +
            "sql_time_ms INT, " +
            "java_time_ms INT, " +
            "is_match BOOLEAN, " +
            "mismatch_reason VARCHAR(255), " +
            "created_at TIMESTAMP DEFAULT NOW())");

        // Create indexes
        executeSQL("CREATE INDEX IF NOT EXISTS idx_function_log_name_created ON migration.function_log(function_name, created_at)");
        executeSQL("CREATE INDEX IF NOT EXISTS idx_function_log_mismatches ON migration.function_log(function_name, created_at) WHERE NOT is_match");

        // Insert Wave 0 function configurations
        String[] functions = {"getDate", "daysBetween", "addDays", "subtractDays", "trunc", "round", "firstOf", "charAt"};
        for (String func : functions) {
            executeSQL("INSERT INTO migration.function_config (function_name, mode, performance_tier, sample_rate) " +
                       "VALUES ('" + func + "', 'SQL_ONLY', 'STANDARD', 1.0) ON CONFLICT DO NOTHING");
        }

        System.out.println("Migration schema created successfully.");
    }

    private void executeSQL(String sql) {
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null)) {
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error executing SQL: " + e.getMessage());
            System.err.println("SQL: " + sql.substring(0, Math.min(80, sql.length())) + "...");
        }
    }

    private void captureBaseline(String functionName, String sql) {
        List<Double> timings = new ArrayList<>(ITERATIONS);

        for (int i = 0; i < ITERATIONS; i++) {
            long start = System.nanoTime();
            try (PreparedStatement pstmt = DB.prepareStatement(sql, null);
                 ResultSet rs = pstmt.executeQuery()) {
                rs.next(); // consume result
            } catch (Exception e) {
                System.err.println("Error executing " + functionName + ": " + e.getMessage());
                return;
            }
            long elapsed = System.nanoTime() - start;
            timings.add(elapsed / 1_000_000.0); // Convert to ms
        }

        // Sort and get p95
        timings.sort(Double::compareTo);
        int p95Index = (int) Math.ceil(0.95 * ITERATIONS) - 1;
        double p95Ms = timings.get(p95Index);
        int p95Rounded = (int) Math.ceil(p95Ms);

        // Update database
        String updateSql = "UPDATE migration.function_config SET sql_baseline_p95_ms = ? WHERE function_name = ?";
        try (PreparedStatement pstmt = DB.prepareStatement(updateSql, null)) {
            pstmt.setInt(1, p95Rounded);
            pstmt.setString(2, functionName);
            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                System.out.printf("%s p95: %d ms (updated)%n", functionName, p95Rounded);
            } else {
                System.out.printf("%s p95: %d ms (function_config row not found)%n", functionName, p95Rounded);
            }
        } catch (Exception e) {
            System.err.println("Error updating baseline for " + functionName + ": " + e.getMessage());
        }
    }

    private void displayBaselines() {
        String sql = "SELECT function_name, sql_baseline_p95_ms, mode, performance_tier " +
                     "FROM migration.function_config ORDER BY function_name";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.printf("%-15s %-10s %-10s %-15s%n", "Function", "P95 (ms)", "Mode", "Tier");
            System.out.println("-------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("%-15s %-10s %-10s %-15s%n",
                    rs.getString("function_name"),
                    rs.getObject("sql_baseline_p95_ms"),
                    rs.getString("mode"),
                    rs.getString("performance_tier"));
            }
        } catch (Exception e) {
            System.err.println("Error displaying baselines: " + e.getMessage());
        }
    }
}
