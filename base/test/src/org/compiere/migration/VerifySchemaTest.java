package org.compiere.migration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.compiere.util.DB;

@Tag("IntegrationTest")
public class VerifySchemaTest extends CommonGWSetup {
    @Test
    void verifySchemaIsolation() throws Exception {
        // Check what schema the migration tables are in
        String sql = "SELECT table_schema, table_name FROM information_schema.tables " +
                     "WHERE table_name IN ('function_config', 'function_log') ORDER BY table_schema, table_name";
        try (PreparedStatement pstmt = DB.prepareStatement(sql, null);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("\n=== Migration Tables Location ===");
            while (rs.next()) {
                System.out.printf("Schema: %-15s Table: %s%n", rs.getString(1), rs.getString(2));
            }
        }

        // Check ADempiere core tables schema
        String sql2 = "SELECT table_schema, table_name FROM information_schema.tables " +
                      "WHERE table_name IN ('ad_client', 'ad_org', 'c_bpartner') ORDER BY table_schema, table_name";
        try (PreparedStatement pstmt = DB.prepareStatement(sql2, null);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("\n=== ADempiere Core Tables Location ===");
            while (rs.next()) {
                System.out.printf("Schema: %-15s Table: %s%n", rs.getString(1), rs.getString(2));
            }
        }

        // List all schemas
        String sql3 = "SELECT schema_name FROM information_schema.schemata WHERE schema_name NOT LIKE 'pg_%' AND schema_name != 'information_schema' ORDER BY schema_name";
        try (PreparedStatement pstmt = DB.prepareStatement(sql3, null);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("\n=== All User Schemas ===");
            while (rs.next()) {
                System.out.println("  " + rs.getString(1));
            }
        }
    }
}
