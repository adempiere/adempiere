// base/test/src/org/compiere/migration/ShadowExecutorIntegrationTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("IntegrationTest")
public class ShadowExecutorIntegrationTest extends CommonGWSetup {

    @Test
    void testExecuteWithAutoConfigLookup() {
        // Uses the 4-param execute() that looks up config automatically
        // Default config for unknown function is SQL_ONLY
        String result = ShadowExecutor.execute(
            "unknownFunc",
            new Object[]{},
            () -> "java",
            () -> "sql",
            String::equals
        );

        // Default mode is SQL_ONLY, so should return SQL result
        assertEquals("sql", result);
    }

    @Test
    void testExecuteWithKnownFunction() {
        // Test with a function that will be configured in migration.function_config
        // For now, verify it doesn't throw on a known Wave 0 function name
        String result = ShadowExecutor.execute(
            "getDate",
            new Object[]{},
            () -> "java",
            () -> "sql",
            String::equals
        );

        // Result depends on configured mode, just verify no exception
        assertNotNull(result);
    }
}
